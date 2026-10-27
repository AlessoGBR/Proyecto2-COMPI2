package Backend.visitor;

import Backend.Ast.Node;
import Backend.Ast.ProgramNode;
import Backend.Ast.declaration.ClassDecl;
import Backend.Ast.declaration.FunctionDecl;
import Backend.Ast.declaration.StructDecl;
import Backend.antlr.*;
import Backend.errores.GestorErrores;
import Backend.errores.ListenerErroresAntlr;
import Backend.lexer.YIndentTokenSource;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class AstBuilder {

    private AstBuilder() {
    }

    // PIG LATIN

    public static ResultadoAnalisis analizarPigLatin(String entrada, String archivo) {
        GestorErrores errores = new GestorErrores(archivo);

        pigLatinLexer lexer = new pigLatinLexer(CharStreams.fromString(entrada));
        pigLatinParser parser = new pigLatinParser(new CommonTokenStream(lexer));
        conectarErrores(lexer, parser, errores);

        ParseTree arbol = parser.programa();
        ProgramNode programa = construir(new PigLatinAstVisitor(), arbol, errores);
        return new ResultadoAnalisis(programa, errores, ProgramNode.SourceLanguage.PIG_LATIN, archivo);
    }

    // ZETARIO

    public static ResultadoAnalisis analizarZetariano(String entrada, String archivo) {
        GestorErrores errores = new GestorErrores(archivo);

        ZetarianoLexer lexer = new ZetarianoLexer(CharStreams.fromString(entrada));
        ZetarianoParser parser = new ZetarianoParser(new CommonTokenStream(lexer));
        conectarErrores(lexer, parser, errores);

        ParseTree arbol = parser.programa();
        ProgramNode programa = construir(new ZetarianoAstVisitor(), arbol, errores);
        return new ResultadoAnalisis(programa, errores, ProgramNode.SourceLanguage.ZETARIANO, archivo);
    }

    // Y

    public static ResultadoAnalisis analizarY(String entrada, String archivo) {
        GestorErrores errores = new GestorErrores(archivo);

        CharStream flujo = CharStreams.fromString(entrada);
        YLexer lexer = new YLexer(flujo);
        YIndentTokenSource fuenteIndentada = new YIndentTokenSource(lexer);
        YParser parser = new YParser(new CommonTokenStream(fuenteIndentada));
        conectarErrores(lexer, parser, errores);

        ParseTree arbol = parser.programa();
        ProgramNode programa = construir(new YAstVisitor(), arbol, errores);
        return new ResultadoAnalisis(programa, errores, ProgramNode.SourceLanguage.Y_LANG, archivo);
    }


    public static ResultadoAnalisis analizar(String entrada, ProgramNode.SourceLanguage lenguaje, String archivo) {
        return switch (lenguaje) {
            case PIG_LATIN -> analizarPigLatin(entrada, archivo);
            case ZETARIANO -> analizarZetariano(entrada, archivo);
            case Y_LANG -> analizarY(entrada, archivo);
        };
    }

    public static ResultadoAnalisis analizarArchivo(Path ruta) throws IOException {
        String contenido = Files.readString(ruta);
        String nombre = ruta.getFileName().toString();
        return analizar(contenido, detectarLenguaje(nombre), nombre);
    }

    public static ProgramNode.SourceLanguage detectarLenguaje(String nombreArchivo) {
        String nombre = nombreArchivo.toLowerCase();
        if (nombre.endsWith(".pig")) {
            return ProgramNode.SourceLanguage.PIG_LATIN;
        }
        if (nombre.endsWith(".z")) {
            return ProgramNode.SourceLanguage.ZETARIANO;
        }
        if (nombre.endsWith(".y")) {
            return ProgramNode.SourceLanguage.Y_LANG;
        }
        throw new IllegalArgumentException("EXTENSION NO SOPORTADA: " + nombreArchivo);
    }


    public static ProgramNode buildFromPigLatin(String entrada) {
        return analizarPigLatin(entrada, "").getPrograma();
    }

    public static ProgramNode buildFromZetariano(String entrada) {
        return analizarZetariano(entrada, "").getPrograma();
    }

    public static ProgramNode buildFromY(String entrada) {
        return analizarY(entrada, "").getPrograma();
    }

    public static ProgramNode build(String entrada, ProgramNode.SourceLanguage lenguaje) {
        return analizar(entrada, lenguaje, "").getPrograma();
    }

    public static ProgramNode buildFromFile(Path ruta) throws IOException {
        return analizarArchivo(ruta).getPrograma();
    }

    private static void conectarErrores(Lexer lexer, Parser parser, GestorErrores errores) {
        ListenerErroresAntlr listener = new ListenerErroresAntlr(errores);

        lexer.removeErrorListeners();
        lexer.addErrorListener(listener);

        parser.removeErrorListeners();
        parser.addErrorListener(listener);
    }

    private static ProgramNode construir(ParseTreeVisitor<Node> visitante, ParseTree arbol, GestorErrores errores) {
        try {
            ProgramNode programa = (ProgramNode) visitante.visit(arbol);
            if (programa != null) {
                marcarArchivoOrigen(programa, errores.getArchivo());
            }
            return programa;
        } catch (RuntimeException ex) {
            errores.agregarSintactico(
                    "NO FUE POSIBLE CONSTRUIR EL AST: " + ex.getMessage(), 0, 0, "");
            return null;
        }
    }

    private static void marcarArchivoOrigen(ProgramNode programa, String archivo) {
        for (StructDecl s : programa.getStructs()) {
            s.setArchivoOrigen(archivo);
        }
        for (ClassDecl c : programa.getClasses()) {
            c.setArchivoOrigen(archivo);
        }
        for (FunctionDecl f : programa.getFunctions()) {
            f.setArchivoOrigen(archivo);
        }
    }
}
