package Backend.proyecto;

import Backend.Ast.ProgramNode;
import Backend.Ast.declaration.ClassDecl;
import Backend.Ast.declaration.FunctionDecl;
import Backend.Ast.declaration.ImportDecl;
import Backend.Ast.declaration.StructDecl;
import Backend.Ast.statement.VarDeclStmt;
import Backend.errores.ErrorCompilacion;
import Backend.errores.GestorErrores;
import Backend.errores.TipoError;
import Backend.visitor.AstBuilder;
import Backend.visitor.ResultadoAnalisis;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class CompiladorProyecto {

    private static final Set<String> CARPETAS_IGNORADAS = Set.of("target", "build", "out", "node_modules");
    private static final int PROFUNDIDAD_MAXIMA_BUSQUEDA = 12;

    private CompiladorProyecto() {
    }

    public static ResultadoProyecto compilar(Path archivoRaiz) throws IOException {
        Path carpeta = archivoRaiz.toAbsolutePath().normalize().getParent();
        return compilar(archivoRaiz, carpeta);
    }

    public static ResultadoProyecto compilar(Path archivoRaiz, Path raizProyecto) throws IOException {
        Path archivo = archivoRaiz.toAbsolutePath().normalize();
        Path raiz = raizProyecto != null ? raizProyecto.toAbsolutePath().normalize() : null;
        if (raiz == null || !archivo.startsWith(raiz)) {
            raiz = archivo.getParent();
        }

        GestorErrores errores = new GestorErrores(archivoRaiz.getFileName().toString());
        Set<String> yaAnalizados = new LinkedHashSet<>();
        List<ResultadoAnalisis> modulos = new ArrayList<>();

        ResultadoAnalisis resultadoRaiz = analizar(archivo, raiz, errores, yaAnalizados, modulos);
        if (resultadoRaiz == null || resultadoRaiz.getPrograma() == null) {
            return new ResultadoProyecto(null, errores, modulos);
        }

        ProgramNode combinado = fusionar(resultadoRaiz, modulos);
        return new ResultadoProyecto(combinado, errores, modulos);
    }


    private static ResultadoAnalisis analizar(Path archivo,
                                              Path raiz,
                                              GestorErrores errores,
                                              Set<String> yaAnalizados,
                                              List<ResultadoAnalisis> modulos) throws IOException {
        String clave = archivo.toAbsolutePath().normalize().toString();
        if (!yaAnalizados.add(clave)) {
            return null;
        }

        if (!Files.exists(archivo)) {
            errores.agregar(TipoError.SEMANTICO,
                    "NO SE ENCONTRO EL ARCHIVO IMPORTADO: " + archivo, 0, 0);
            return null;
        }

        ResultadoAnalisis resultado = AstBuilder.analizarArchivo(archivo);
        errores.agregarTodos(resultado.getGestorErrores());
        modulos.add(resultado);

        ProgramNode programa = resultado.getPrograma();
        if (programa == null) {
            return resultado;
        }

        validarNombreDeArchivo(archivo, resultado, errores);

        Path carpeta = archivo.toAbsolutePath().getParent();
        for (ImportDecl importacion : programa.getImports()) {
            Path destino = resolverRuta(carpeta, importacion.getImportPath());
            if (destino == null) {
                errores.agregar(TipoError.SEMANTICO,
                        "IMPORTACION INVALIDA: " + importacion.getImportPath() ,
                        importacion.getLine(), importacion.getColumn());
                continue;
            }
            if (!Files.exists(destino)) {
                errores.agregar(TipoError.SEMANTICO,
                        "NO SE ENCONTRO EL ARCHIVO IMPORTADO: " + importacion.getImportPath()
                                + " (SE BUSCO EN " + destino + ")",
                        importacion.getLine(), importacion.getColumn());
                continue;
            }
            analizar(destino, raiz, errores, yaAnalizados, modulos);
        }

        for (String referencia : programa.getReferenciasClase()) {
            Path destino = buscarArchivoDeClase(referencia, carpeta, raiz);
            if (destino != null) {
                analizar(destino, raiz, errores, yaAnalizados, modulos);
            }
        }

        return resultado;
    }

    // SE BUSCA LA CARPETA DEL ARCHIVO
    static Path buscarArchivoDeClase(String clase, Path carpetaActual, Path raiz) {
        String nombreArchivo = clase + ".z";
        Path yaRevisada = null;
        for (Path base = carpetaActual; base != null && base.startsWith(raiz); base = base.getParent()) {
            Path encontrado = buscarPorNiveles(base, nombreArchivo, yaRevisada);
            if (encontrado != null) {
                return encontrado;
            }
            yaRevisada = base;
        }
        return null;
    }

    // RECORRIDO POR NIVELES
    private static Path buscarPorNiveles(Path inicio, String nombreArchivo, Path omitir) {
        Deque<Path> pendientes = new ArrayDeque<>();
        Deque<Integer> profundidades = new ArrayDeque<>();
        pendientes.add(inicio);
        profundidades.add(0);
        while (!pendientes.isEmpty()) {
            Path carpeta = pendientes.poll();
            int profundidad = profundidades.poll();
            Path candidato = carpeta.resolve(nombreArchivo);
            if (Files.isRegularFile(candidato)) {
                return candidato;
            }
            if (profundidad >= PROFUNDIDAD_MAXIMA_BUSQUEDA) {
                continue;
            }
            try (Stream<Path> hijos = Files.list(carpeta)) {
                hijos.filter(Files::isDirectory)
                        .filter(h -> !h.equals(omitir))
                        .filter(h -> !h.getFileName().toString().startsWith("."))
                        .filter(h -> !CARPETAS_IGNORADAS.contains(h.getFileName().toString()))
                        .sorted()
                        .forEach(h -> {
                            pendientes.add(h);
                            profundidades.add(profundidad + 1);
                        });
            } catch (IOException ignorada) {
            }
        }
        return null;
    }

    static Path resolverRuta(Path carpetaBase, String rutaImport) {
        if (rutaImport == null || rutaImport.isBlank()) {
            return null;
        }
        String[] partes = rutaImport.split("\\.");
        if (partes.length < 2) {
            return null;
        }

        String extension = partes[partes.length - 1];
        String nombre = partes[partes.length - 2] + "." + extension;

        Path destino = carpetaBase;
        for (int i = 0; i < partes.length - 2; i++) {
            destino = destino.resolve(partes[i]);
        }
        return destino.resolve(nombre);
    }

    private static void validarNombreDeArchivo(Path archivo, ResultadoAnalisis resultado, GestorErrores errores) {
        ProgramNode programa = resultado.getPrograma();
        if (programa.getLanguage() != ProgramNode.SourceLanguage.ZETARIANO || programa.getClasses().isEmpty()) {
            return;
        }

        String nombreArchivo = archivo.getFileName().toString();
        int punto = nombreArchivo.lastIndexOf('.');
        String esperado = punto > 0 ? nombreArchivo.substring(0, punto) : nombreArchivo;

        ClassDecl clase = programa.getClasses().get(0);
        if (!clase.getName().equals(esperado)) {
            errores.agregar(new ErrorCompilacion(TipoError.SEMANTICO,
                    "EL ARCHIVO: " + nombreArchivo + " DEBE LLAMARSE IGUAL QUE LA CLASE QUE DEFINE ( "
                            + clase.getName() + " )", clase.getLine(), clase.getColumn(), nombreArchivo, ""));
        }
    }

    private static ProgramNode fusionar(ResultadoAnalisis raiz, List<ResultadoAnalisis> modulos) {
        ProgramNode programaRaiz = raiz.getPrograma();
        ProgramNode combinado = new ProgramNode(programaRaiz.getLanguage(),
                programaRaiz.getLine(), programaRaiz.getColumn());

        for (ResultadoAnalisis modulo : modulos) {
            ProgramNode programa = modulo.getPrograma();
            if (programa == null) {
                continue;
            }
            for (StructDecl s : programa.getStructs()) {
                combinado.addStruct(s);
            }
            for (ClassDecl c : programa.getClasses()) {
                combinado.addClass(c);
            }
            for (FunctionDecl f : programa.getFunctions()) {
                combinado.addFunction(f);
            }
        }

        for (ImportDecl i : programaRaiz.getImports()) {
            combinado.addImport(i);
        }
        for (VarDeclStmt v : programaRaiz.getGlobalVariables()) {
            combinado.addGlobalVariable(v);
        }
        combinado.setMainBlock(programaRaiz.getMainBlock());

        return combinado;
    }
}
