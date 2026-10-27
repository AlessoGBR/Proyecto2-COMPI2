package Backend.semantica;

import Backend.Ast.*;
import Backend.Ast.declaration.*;
import Backend.Ast.expression.*;
import Backend.Ast.statement.*;
import Backend.errores.GestorErrores;
import Backend.simbolos.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AnalizadorSemantico implements AstVisitor<Type, Ambito> {

    private final GestorErrores gestorErrores;
    private final TablaSimbolos tablaSimbolos;
    private Type tipoRetornoFuncionActual = null;
    private int contadorCiclos = 0;
    private int contadorSwitch = 0;
    private Simbolo claseActual = null;
    private boolean superPermitido = false;
    private int siguienteIdClase = 1;
    private final Set<ClassDecl> clasesRegistradas = java.util.Collections.newSetFromMap(new IdentityHashMap<>());
    private final Map<FunctionDecl, Simbolo> simboloDeMetodo = new IdentityHashMap<>();
    private final TablaCompatibilidad.RelacionSubtipo relacion = this::esSubtipo;

    public AnalizadorSemantico(GestorErrores gestorErrores) {
        this.gestorErrores = gestorErrores != null ? gestorErrores : new GestorErrores();
        this.tablaSimbolos = new TablaSimbolos();
    }

    public TablaSimbolos getTablaSimbolos() {
        return tablaSimbolos;
    }

    public GestorErrores getGestorErrores() {
        return gestorErrores;
    }

    public boolean analizar(ProgramNode programa) {
        if (programa == null) return false;

        recolectarDefinicionesGlobales(programa);

        programa.accept(this, tablaSimbolos.getAmbitoGlobal());

        return !gestorErrores.hayErrores();
    }


    private void recolectarDefinicionesGlobales(ProgramNode programa) {
        Ambito global = tablaSimbolos.getAmbitoGlobal();

        // ESTRUCUTURAS
        for (StructDecl structDecl : programa.getStructs()) {
            String archivoAnterior = entrarAlArchivoDe(structDecl);
            Type structType = Type.createCustomType(structDecl.getName(), false);
            Simbolo simStruct = new Simbolo(structDecl.getName(), structType, RolSimbolo.ESTRUCTURA, structDecl.getLine(), structDecl.getColumn());
            int indiceCampo = 0;
            for (StructField f : structDecl.getFields()) {
                if (simStruct.buscarMiembro(f.getName()) != null) {
                    gestorErrores.agregarSemantico("EL CAMPO: " + f.getName() + "YA EXISTE EN LA ESTRUCTURA: "
                            + structDecl.getName(), f.getLine(), f.getColumn());
                    continue;
                }
                Simbolo simCampo = new Simbolo(f.getName(), f.getType(), RolSimbolo.VARIABLE, f.getLine(), f.getColumn());
                simCampo.setDireccionRelativa(indiceCampo++);
                simStruct.agregarMiembro(simCampo);
            }
            if (!global.insertarDefinicion(simStruct)) {
                gestorErrores.agregarSemantico("LA ESTRUCUTRA: " + structDecl.getName() + " YA FUE DECLARADA", structDecl.getLine(), structDecl.getColumn());
            }
            gestorErrores.setArchivo(archivoAnterior);
        }

        // CLASES: PRIMERO TODOS LOS NOMBRES, LUEGO CONTINUAMOS CON LA HERENCIA Y AL FINAL MIEMBROS
        Map<String, ClassDecl> clases = new LinkedHashMap<>();
        for (ClassDecl classDecl : programa.getClasses()) {
            String archivoAnterior = entrarAlArchivoDe(classDecl);
            Type classType = Type.createCustomType(classDecl.getName(), true);
            Simbolo simClase = new Simbolo(classDecl.getName(), classType, RolSimbolo.CLASE, classDecl.getLine(), classDecl.getColumn());
            simClase.setModificador(classDecl.isPublic() ? ModificadorAcceso.PUBLICO : ModificadorAcceso.DEFECTO);
            if (!global.insertarDefinicion(simClase)) {
                gestorErrores.agregarSemantico("LA CLASE: " + classDecl.getName() + " YA FUE DECLARADA", classDecl.getLine(), classDecl.getColumn());
            } else {
                simClase.setIdClase(siguienteIdClase++);
                clases.put(classDecl.getName(), classDecl);
                clasesRegistradas.add(classDecl);
            }
            gestorErrores.setArchivo(archivoAnterior);
        }

        resolverHerencia(clases);
        List<ClassDecl> ordenHerencia = new ArrayList<>(clases.values());
        ordenHerencia.sort(Comparator.comparingInt(c -> profundidadHerencia(global.buscarEnActual(c.getName()))));
        for (ClassDecl classDecl : ordenHerencia) {
            registrarMiembrosDeClase(classDecl);
        }
        for (ClassDecl classDecl : clases.values()) {
            validarSobrescrituras(classDecl);
        }

        // FUNCIONES
        for (FunctionDecl func : programa.getFunctions()) {
            String archivoAnterior = entrarAlArchivoDe(func);
            Simbolo simFunc =new Simbolo(func.getName(), func.getReturnType(), RolSimbolo.FUNCION, func.getLine(), func.getColumn());
            for (Parameter p : func.getParameters()) {
                Simbolo simP = new Simbolo(p.getName(), p.getType(), RolSimbolo.PARAMETRO, p.getLine(), p.getColumn());
                simP.setEsPorReferencia(p.isByReference());
                simFunc.agregarParametro(simP);
            }
            if (!global.insertarFuncion(simFunc)) {
                gestorErrores.agregarSemantico("LA FUNCION: " + func.getName() + " YA FUE DECLARADA EN EL AMBITO GLOBAL", func.getLine(), func.getColumn());
            }
            simFunc.setEtiquetaC3D("func_" + func.getName());
            func.setEtiquetaC3D("func_" + func.getName());
            gestorErrores.setArchivo(archivoAnterior);
        }

        for (StructDecl structDecl : programa.getStructs()) {
            String archivoAnterior = entrarAlArchivoDe(structDecl);
            for (StructField f : structDecl.getFields()) {
                validarTipoDeclarado(f.getType(), f.getLine(), f.getColumn());
                for (Expression tamanio : f.getArraySizes()) {
                    if (!(tamanio instanceof LiteralExpr literal)
                            || !(literal.getValue() instanceof Integer valor) || valor <= 0) {
                        gestorErrores.agregarSemantico("EL TAMANIO DEL ARREGLO: " + f.getName()
                                + " DENTRO DE LA ESTRUCTURA: " + structDecl.getName()
                                + " DEBE SER UN ENTERO CONSTANTE MAYOR A 0", tamanio.getLine(), tamanio.getColumn());
                    }
                }
            }
            gestorErrores.setArchivo(archivoAnterior);
        }
        for (ClassDecl classDecl : programa.getClasses()) {
            String archivoAnterior = entrarAlArchivoDe(classDecl);
            for (VarDeclStmt attr : classDecl.getAttributes()) {
                validarTipoDeclarado(attr.getType(), attr.getLine(), attr.getColumn());
            }
            gestorErrores.setArchivo(archivoAnterior);
        }
    }

    // HERENCIA

    private void resolverHerencia(Map<String, ClassDecl> clases) {
        Ambito global = tablaSimbolos.getAmbitoGlobal();
        for (ClassDecl decl : clases.values()) {
            if (!decl.tieneSuperClase()) {
                continue;
            }
            String archivoAnterior = entrarAlArchivoDe(decl);
            if (decl.getSuperClases().size() > 1) {
                gestorErrores.agregarSemantico("HERENCIA MULTIPLE NO PERMITIDA: LA CLASE: " + decl.getName()
                        + " SOLO PUEDE HEREDAR DE UNA CLASE Y SE INDICARON: " + String.join(", ", decl.getSuperClases()),
                        decl.getLine(), decl.getColumn());
            }
            Simbolo hija = global.buscarEnActual(decl.getName());
            Simbolo padre = global.buscarEnActual(decl.getSuperClase());
            if (decl.getSuperClase().equals(decl.getName())) {
                gestorErrores.agregarSemantico("LA CLASE: " + decl.getName() + " NO PUEDE HEREDAR DE SI MISMA",
                        decl.getLine(), decl.getColumn());
            } else if (padre == null) {
                gestorErrores.agregarSemantico("LA CLASE PADRE: " + decl.getSuperClase() + " DE LA CLASE: " + decl.getName()
                        + " NO EXISTE, DEBE ESTAR DEFINIDA EN EL ARCHIVO " + decl.getSuperClase() + ".z DENTRO DEL PROYECTO",
                        decl.getLine(), decl.getColumn());
            } else if (padre.getRol() != RolSimbolo.CLASE) {
                gestorErrores.agregarSemantico(decl.getSuperClase() + " NO ES UNA CLASE, LA CLASE: " + decl.getName()
                        + " NO PUEDE HEREDAR DE ELLA", decl.getLine(), decl.getColumn());
            } else {
                hija.setSuperClase(padre);
            }
            gestorErrores.setArchivo(archivoAnterior);
        }

        // A EXTENDS B, B EXTENDS A, SE REPORTA Y CORTA ENLACE PARA EVITAR CICLOS
        for (ClassDecl decl : clases.values()) {
            Simbolo inicio = global.buscarEnActual(decl.getName());
            StringBuilder cadena = new StringBuilder(inicio.getIdentificador());
            Set<String> vistos = new HashSet<>();
            Simbolo actual = inicio.getSuperClase();
            while (actual != null && vistos.add(actual.getIdentificador())) {
                cadena.append(" -> ").append(actual.getIdentificador());
                if (actual == inicio) {
                    String archivoAnterior = entrarAlArchivoDe(decl);
                    gestorErrores.agregarSemantico("HERENCIA CICLICA: " + cadena, decl.getLine(), decl.getColumn());
                    gestorErrores.setArchivo(archivoAnterior);
                    inicio.setSuperClase(null);
                    break;
                }
                actual = actual.getSuperClase();
            }
        }
    }

    private int profundidadHerencia(Simbolo clase) {
        int profundidad = 0;
        for (Simbolo actual = clase != null ? clase.getSuperClase() : null; actual != null; actual = actual.getSuperClase()) {
            profundidad++;
        }
        return profundidad;
    }

    // Distribucion del objeto en el heap: [0] id de la clase, luego los atributos del
    // padre (en el mismo orden que en el padre) y despues los propios
    private void registrarMiembrosDeClase(ClassDecl classDecl) {
        String archivoAnterior = entrarAlArchivoDe(classDecl);
        Simbolo simClase = tablaSimbolos.getAmbitoGlobal().buscarEnActual(classDecl.getName());
        Type classType = simClase.getTipo();

        Simbolo padre = simClase.getSuperClase();
        if (padre != null) {
            for (Simbolo heredado : padre.getMiembros().values()) {
                simClase.agregarMiembro(heredado);
            }
        }

        for (VarDeclStmt attr : classDecl.getAttributes()) {
            Simbolo existente = simClase.buscarMiembro(attr.getName());
            if (existente != null) {
                if (classDecl.getName().equals(existente.getClaseDuenia())) {
                    gestorErrores.agregarSemantico("EL ATRIBUTO: " + attr.getName() + " YA EXISTE EN LA CLASE: "
                            + classDecl.getName(), attr.getLine(), attr.getColumn());
                } else {
                    gestorErrores.agregarSemantico("EL ATRIBUTO: " + attr.getName() + " YA ESTA DEFINIDO EN LA CLASE PADRE: "
                            + existente.getClaseDuenia() + ", NO SE PUEDE VOLVER A DECLARAR EN: " + classDecl.getName(),
                            attr.getLine(), attr.getColumn());
                }
                continue;
            }
            Simbolo simAttr = new Simbolo(attr.getName(), attr.getType(), RolSimbolo.VARIABLE, attr.getLine(), attr.getColumn());
            simAttr.setDireccionRelativa(1 + simClase.getMiembros().size());
            simAttr.setModificador(attr.getModificador());
            simAttr.setClaseDuenia(classDecl.getName());
            simAttr.setEsMiembroDeObjeto(true);
            simClase.agregarMiembro(simAttr);
        }

        for (FunctionDecl m : classDecl.getMethods()) {
            Simbolo simMetodo = new Simbolo(m.getName(), m.getReturnType(), RolSimbolo.METODO, m.getLine(), m.getColumn());
            simMetodo.setModificador(m.getModificador());
            simMetodo.setClaseDuenia(classDecl.getName());
            simMetodo.setEsOverride(m.esOverride());
            for (Parameter p : m.getParameters()) {
                Simbolo simP = new Simbolo(p.getName(), p.getType(), RolSimbolo.PARAMETRO, p.getLine(), p.getColumn());
                simP.setEsPorReferencia(p.isByReference());
                simMetodo.agregarParametro(simP);
            }
            if (existeMismaFirma(simClase.buscarMetodos(m.getName()), simMetodo)) {
                gestorErrores.agregarSemantico("EL METODO: " + simMetodo.getFirma() + " YA FUE DECLARADO EN LA CLASE: "
                        + classDecl.getName(), m.getLine(), m.getColumn());
            } else {
                String etiqueta = "metodo_" + classDecl.getName() + "_" + m.getName()
                        + sufijoDeParametros(simMetodo.getParametros());
                simMetodo.setEtiquetaC3D(etiqueta);
                simClase.agregarMetodo(simMetodo);
                m.setEtiquetaC3D(etiqueta);
                simboloDeMetodo.put(m, simMetodo);
            }
        }

        for (ConstructorDecl c : classDecl.getConstructors()) {
            Simbolo simCtor = new Simbolo(c.getClassName(), classType, RolSimbolo.CONSTRUCTOR, c.getLine(), c.getColumn());
            simCtor.setModificador(c.getModificador());
            simCtor.setClaseDuenia(classDecl.getName());
            for (Parameter p : c.getParameters()) {
                Simbolo simP = new Simbolo(p.getName(), p.getType(), RolSimbolo.PARAMETRO, p.getLine(), p.getColumn());
                simP.setEsPorReferencia(p.isByReference());
                simCtor.agregarParametro(simP);
            }
            if (!c.getClassName().equals(classDecl.getName())) {
                gestorErrores.agregarSemantico("EL METODO: " + c.getClassName() + " NO TIENE TIPO DE RETORNO; SI ES UN CONSTRUCTOR "
                        + "DEBE LLAMARSE IGUAL QUE LA CLASE: " + classDecl.getName(), c.getLine(), c.getColumn());
            } else if (existeMismaFirma(simClase.getConstructores(), simCtor)) {
                gestorErrores.agregarSemantico("EL CONSTRUCTOR: " + simCtor.getFirma() + " YA FUE DECLARADO",
                        c.getLine(), c.getColumn());
            } else {
                String etiqueta = "constructor_" + classDecl.getName()
                        + sufijoDeParametros(simCtor.getParametros());
                simCtor.setEtiquetaC3D(etiqueta);
                simClase.agregarConstructor(simCtor);
                c.setEtiquetaC3D(etiqueta);
            }
        }
        gestorErrores.setArchivo(archivoAnterior);
    }


    // @Override ES OBLIGATORIO PARA SOBRESCRIBIR, SE EXIGE CUANDO EXISTE UN PADRE
    private void validarSobrescrituras(ClassDecl classDecl) {
        String archivoAnterior = entrarAlArchivoDe(classDecl);
        Simbolo simClase = tablaSimbolos.getAmbitoGlobal().buscarEnActual(classDecl.getName());
        Simbolo padre = simClase.getSuperClase();

        for (FunctionDecl m : classDecl.getMethods()) {
            Simbolo simMetodo = simboloDeMetodo.get(m);
            if (simMetodo == null) {
                continue;
            }
            String firma = simMetodo.getFirma();
            int linea = m.getLine();
            int columna = m.getColumn();

            if (m.getAnotacion() != null && !m.esOverride()) {
                gestorErrores.agregarSemantico("ANOTACION DESCONOCIDA: " + m.getAnotacion()
                        + " EN EL METODO: " + firma + ", SOLO SE ADMITE @Override", linea, columna);
                continue;
            }

            Simbolo sobrescrito = padre != null ? padre.buscarImplementacion(firma) : null;
            if (m.esOverride()) {
                if (padre == null) {
                    gestorErrores.agregarSemantico("EL METODO: " + firma + " TIENE @Override PERO LA CLASE: "
                            + classDecl.getName() + " NO HEREDA DE NINGUNA CLASE", linea, columna);
                } else if (sobrescrito == null) {
                    gestorErrores.agregarSemantico("EL METODO: " + firma + " TIENE @Override PERO NINGUNA CLASE PADRE DE: "
                            + classDecl.getName() + " DEFINE UN METODO CON ESA FIRMA", linea, columna);
                } else if (sobrescrito.getModificador() == ModificadorAcceso.PRIVADO) {
                    gestorErrores.agregarSemantico("EL METODO: " + firma + " DE LA CLASE: " + sobrescrito.getClaseDuenia()
                            + " ES PRIVATE, NO SE PUEDE SOBRESCRIBIR", linea, columna);
                } else {
                    if (!mismoTipoDeRetorno(sobrescrito.getTipo(), simMetodo.getTipo())) {
                        gestorErrores.agregarSemantico("EL METODO: " + firma + " SOBRESCRIBE A " + sobrescrito.getClaseDuenia()
                                + "." + firma + " PERO CAMBIA EL TIPO DE RETORNO DE " + sobrescrito.getTipo()
                                + " A " + simMetodo.getTipo(), linea, columna);
                    }
                    if (simMetodo.getModificador().getNivel() < sobrescrito.getModificador().getNivel()) {
                        gestorErrores.agregarSemantico("EL METODO: " + firma + " NO PUEDE REDUCIR LA VISIBILIDAD AL SOBRESCRIBIR "
                                + sobrescrito.getClaseDuenia() + "." + firma + " (DE " + sobrescrito.getModificador()
                                + " A " + simMetodo.getModificador() + ")", linea, columna);
                    }
                }
            } else if (sobrescrito != null && sobrescrito.getModificador() != ModificadorAcceso.PRIVADO) {
                gestorErrores.agregarSemantico("EL METODO: " + firma + " SOBRESCRIBE AL METODO DE LA CLASE: "
                        + sobrescrito.getClaseDuenia() + ", DEBE LLEVAR LA ANOTACION @Override", linea, columna);
            }
        }
        gestorErrores.setArchivo(archivoAnterior);
    }

    // EL RETORNO DEBE DE SER EL MISMO
    private boolean mismoTipoDeRetorno(Type padre, Type hija) {
        if (padre == null || hija == null) {
            return padre == hija;
        }
        if (padre.equals(hija)) {
            return true;
        }
        return padre.esDefinidoPorUsuario() && hija.esDefinidoPorUsuario()
                && esSubtipo(hija.getTypeName(), padre.getTypeName());
    }

    private boolean esSubtipo(String hija, String ancestro) {
        Simbolo clase = tablaSimbolos.getAmbitoGlobal().buscarEnActual(hija);
        return clase != null && clase.getRol() == RolSimbolo.CLASE && clase.esSubclaseDe(ancestro);
    }

    // Metodos con ese nombre que ve una clase: los propios y los heredados que no sobrescribio
    private List<Simbolo> metodosVisibles(Simbolo clase, String nombre) {
        List<Simbolo> visibles = new ArrayList<>();
        Set<String> firmas = new LinkedHashSet<>();
        for (Simbolo actual = clase; actual != null; actual = actual.getSuperClase()) {
            for (Simbolo metodo : actual.buscarMetodos(nombre)) {
                if (firmas.add(metodo.getFirma())) {
                    visibles.add(metodo);
                }
            }
        }
        return visibles;
    }

    // ENCAPSULAMIENTO

    private boolean esAccesible(ModificadorAcceso modificador, String claseDuenia) {
        if (modificador == null || claseDuenia == null) {
            return true;
        }
        return switch (modificador) {
            case PRIVADO -> claseActual != null && claseDuenia.equals(claseActual.getIdentificador());
            case PROTEGIDO -> claseActual != null && claseActual.esSubclaseDe(claseDuenia);
            case PUBLICO, DEFECTO -> true;
        };
    }

    private void validarAcceso(Simbolo miembro, String tipoMiembro, int linea, int columna) {
        if (miembro == null || esAccesible(miembro.getModificador(), miembro.getClaseDuenia())) {
            return;
        }
        String duenia = miembro.getClaseDuenia();
        String regla = miembro.getModificador() == ModificadorAcceso.PRIVADO
                ? "ES PRIVATE, SOLO ES ACCESIBLE DENTRO DE LA CLASE " + duenia
                : "ES PROTECTED, SOLO ES ACCESIBLE DENTRO DE LA CLASE " + duenia + " Y DE SUS CLASES HIJAS";
        String desde = claseActual != null ? "LA CLASE " + claseActual.getIdentificador() : "FUERA DE UNA CLASE";
        gestorErrores.agregarSemantico("NO SE PUEDE ACCEDER AL " + tipoMiembro + ": " + miembro.getIdentificador()
                + " DE LA CLASE " + duenia + " DESDE " + desde + ": " + regla, linea, columna);
    }

    // ayuda a entender por que Padre -> Hija no se puede asignar
    private String pistaHerencia(Type destino, Type origen) {
        if (destino != null && origen != null && destino.esDefinidoPorUsuario() && origen.esDefinidoPorUsuario()
                && esSubtipo(destino.getTypeName(), origen.getTypeName())) {
            return " (UNA VARIABLE DE LA CLASE HIJA " + destino + " NO PUEDE GUARDAR UN OBJETO DE LA CLASE PADRE " + origen + ")";
        }
        return "";
    }

    private String entrarAlArchivoDe(Declaration declaracion) {
        String anterior = gestorErrores.getArchivo();
        String origen = declaracion.getArchivoOrigen();
        if (origen != null && !origen.isEmpty()) {
            gestorErrores.setArchivo(origen);
        }
        return anterior;
    }

    private String sufijoDeParametros(List<Simbolo> parametros) {
        if (parametros.isEmpty()) {
            return "_0";
        }
        StringBuilder sb = new StringBuilder();
        for (Simbolo p : parametros) {
            sb.append('_').append(nombreCortoDeTipo(p.getTipo()));
        }
        return sb.toString();
    }

    private String nombreCortoDeTipo(Type tipo) {
        if (tipo == null) {
            return "x";
        }
        if (tipo.getCategory() == Type.TypeCategory.ARRAY) {
            return "arr" + nombreCortoDeTipo(tipo.getBaseType());
        }
        String nombre = tipo.getTypeName() == null ? tipo.getCategory().name() : tipo.getTypeName();
        return nombre.replaceAll("[^A-Za-z0-9_]", "");
    }

    private boolean existeMismaFirma(List<Simbolo> candidatos, Simbolo nuevo) {
        for (Simbolo candidato : candidatos) {
            if (candidato.getFirma().equals(nuevo.getFirma())) {
                return true;
            }
        }
        return false;
    }

    private void validarTipoDeclarado(Type tipo, int linea, int columna) {
        if (tipo == null) {
            return;
        }
        Type base = tipo;
        while (base.getCategory() == Type.TypeCategory.ARRAY && base.getBaseType() != null) {
            base = base.getBaseType();
        }
        if (base.getCategory() != Type.TypeCategory.STRUCT && base.getCategory() != Type.TypeCategory.CLASS) {
            return;
        }
        Simbolo def = tablaSimbolos.getAmbitoGlobal().buscarEnActual(base.getTypeName());
        if (def == null || (def.getRol() != RolSimbolo.ESTRUCTURA && def.getRol() != RolSimbolo.CLASE)) {
            gestorErrores.agregarSemantico("EL TIPO: " + base.getTypeName() + " NO ESTA DECLARADO "
                    + " SE DEBE DEFINIR O IMPORTAR", linea, columna);
        }
    }

    private Simbolo resolverSobrecarga(List<Simbolo> candidatos, List<Type> tiposArgumentos) {
        Simbolo compatible = null;
        for (Simbolo candidato : candidatos) {
            List<Simbolo> params = candidato.getParametros();
            if (params.size() != tiposArgumentos.size()) {
                continue;
            }
            boolean exacto = true;
            boolean asignable = true;
            for (int i = 0; i < params.size(); i++) {
                Type esperado = params.get(i).getTipo();
                Type recibido = tiposArgumentos.get(i);
                if (esperado == null || recibido == null || !esperado.equals(recibido)) {
                    exacto = false;
                }
                if (!TablaCompatibilidad.esAsignable(esperado, recibido, relacion)) {
                    asignable = false;
                }
            }
            if (exacto) {
                return candidato;
            }
            if (asignable && compatible == null) {
                compatible = candidato;
            }
        }
        return compatible;
    }

    // VISITOR DE PROGRAMA

    @Override
    public Type visitProgramNode(ProgramNode node, Ambito context) {
        for (VarDeclStmt v : node.getGlobalVariables()) {
            v.accept(this, context);
        }
        node.setTamanioAreaGlobal(tablaSimbolos.getAmbitoGlobal().getTamanioMarco());

        for (FunctionDecl f : node.getFunctions()) {
            f.accept(this, context);
        }

        for (ClassDecl c : node.getClasses()) {
            if (clasesRegistradas.contains(c)) {
                c.accept(this, context);
            }
        }

        if (node.getMainBlock() != null) {
            Ambito ambitoMain = tablaSimbolos.abrirMarcoFuncion("Principal");
            node.getMainBlock().accept(this, ambitoMain);
            node.setTamanioMarcoPrincipal(ambitoMain.getTamanioMarco());
            tablaSimbolos.cerrarAmbito();
        }

        return Type.VOID;
    }

    // DECLARACIONES

    @Override
    public Type visitClassDecl(ClassDecl node, Ambito context) {
        String archivoAnterior = entrarAlArchivoDe(node);
        claseActual = tablaSimbolos.getAmbitoGlobal().buscarEnActual(node.getName());

        Simbolo padre = claseActual.getSuperClase();
        int heredados = padre != null ? padre.getMiembros().size() : 0;
        Ambito ambitoClase = tablaSimbolos.abrirMarcoClase("Clase_" + node.getName(), 1 + heredados);

        if (padre != null) {
            Set<String> propios = new HashSet<>();
            for (VarDeclStmt attr : node.getAttributes()) {
                propios.add(attr.getName());
            }
            for (Simbolo heredado : padre.getMiembros().values()) {
                if (propios.contains(heredado.getIdentificador())) {
                    continue;
                }
                Simbolo copia = new Simbolo(heredado.getIdentificador(), heredado.getTipo(), heredado.getRol(),
                        heredado.getLinea(), heredado.getColumna());
                copia.setDireccionRelativa(heredado.getDireccionRelativa());
                copia.setEsMiembroDeObjeto(true);
                copia.setModificador(heredado.getModificador());
                copia.setClaseDuenia(heredado.getClaseDuenia());
                ambitoClase.insertarConDireccion(copia);
            }
        }

        for (VarDeclStmt attr : node.getAttributes()) {
            attr.accept(this, ambitoClase);
            if (attr.getSimbolo() != null) {
                attr.getSimbolo().setModificador(attr.getModificador());
                attr.getSimbolo().setClaseDuenia(node.getName());
            }
        }

        for (ConstructorDecl constructor : node.getConstructors()) {
            constructor.accept(this, ambitoClase);
        }

        for (FunctionDecl method : node.getMethods()) {
            method.accept(this, ambitoClase);
        }

        claseActual = null;
        tablaSimbolos.cerrarAmbito();
        gestorErrores.setArchivo(archivoAnterior);
        return Type.VOID;
    }

    @Override
    public Type visitConstructorDecl(ConstructorDecl node, Ambito context) {
        Ambito ambitoConstructor = tablaSimbolos.abrirMarcoFuncion("Constructor_" + node.getClassName());

        for (Parameter p : node.getParameters()) {
            p.accept(this, ambitoConstructor);
        }

        tipoRetornoFuncionActual = Type.VOID;
        List<Statement> instrucciones = node.getBody().getStatements();
        for (int i = 0; i < instrucciones.size(); i++) {
            superPermitido = i == 0 && ConstructorDecl.esLlamadaSuper(instrucciones.get(i));
            instrucciones.get(i).accept(this, ambitoConstructor);
        }
        superPermitido = false;
        tipoRetornoFuncionActual = null;

        node.setTamanioMarco(ambitoConstructor.getTamanioMarco());
        if (node.getEtiquetaC3D() == null) {
            node.setEtiquetaC3D("constructor_" + node.getClassName() + "_" + node.getParameters().size());
        }
        tablaSimbolos.cerrarAmbito();
        return Type.VOID;
    }

    @Override
    public Type visitFunctionDecl(FunctionDecl node, Ambito context) {
        String archivoAnterior = entrarAlArchivoDe(node);
        Ambito ambitoFuncion = tablaSimbolos.abrirMarcoFuncion(
                (node.isMethod() ? "Metodo_" : "Funcion_") + node.getName());

        for (Parameter p : node.getParameters()) {
            p.accept(this, ambitoFuncion);
        }

        validarTipoDeclarado(node.getReturnType(), node.getLine(), node.getColumn());

        tipoRetornoFuncionActual = node.getReturnType();
        node.getBody().accept(this, ambitoFuncion);
        tipoRetornoFuncionActual = null;

        node.setTamanioMarco(ambitoFuncion.getTamanioMarco());
        if (node.getEtiquetaC3D() == null) {
            node.setEtiquetaC3D("func_" + node.getName());
        }
        tablaSimbolos.cerrarAmbito();
        gestorErrores.setArchivo(archivoAnterior);
        return Type.VOID;
    }

    @Override
    public Type visitParameter(Parameter node, Ambito context) {
        validarTipoDeclarado(node.getType(), node.getLine(), node.getColumn());

        Simbolo param = new Simbolo(node.getName(), node.getType(), RolSimbolo.PARAMETRO, node.getLine(), node.getColumn());
        param.setEsPorReferencia(node.isByReference());
        if (!context.insertar(param)) {
            gestorErrores.agregarSemantico("PARAMETRO: " + node.getName() + " DUPLICADO", node.getLine(), node.getColumn());
        }
        node.setSimbolo(param);
        return node.getType();
    }

    @Override
    public Type visitStructDecl(StructDecl node, Ambito context) {
        return Type.VOID;
    }

    @Override
    public Type visitStructField(StructField node, Ambito context) {
        return node.getType();
    }

    @Override
    public Type visitImportDecl(ImportDecl node, Ambito context) {
        return Type.VOID;
    }

    // SENTENCIAS

    @Override
    public Type visitBlockStmt(BlockStmt node, Ambito context) {
        for (Statement s : node.getStatements()) {
            s.accept(this, context);
        }
        return Type.VOID;
    }

    @Override
    public Type visitVarDeclStmt(VarDeclStmt node, Ambito context) {
        String name = node.getName();
        Type varType = node.getType();

        if (context.buscarEnActual(name) != null) {
            gestorErrores.agregarSemantico("LA VARIABLE: " + name + " YA FUE DECLARADA EN ESTE AMBITO", node.getLine(), node.getColumn());
            return Type.ERROR;
        }

        validarTipoDeclarado(varType, node.getLine(), node.getColumn());

        for (Expression dim : node.getArrayDimensions()) {
            Type dimType = dim.accept(this, context);
            if (dimType.getCategory() != Type.TypeCategory.INT && dimType.getCategory() != Type.TypeCategory.ERROR) {
                gestorErrores.agregarSemantico("EL TAMANIO DEL ARREGLO DEBE DE SER ENTERO, SE OBTUVO: " + dimType,
                        dim.getLine(), dim.getColumn());
            }
        }

        if (node.getInitialValue() != null) {
            Type initType = node.getInitialValue().accept(this, context);
            if (!TablaCompatibilidad.esAsignable(varType, initType, relacion)) {
                gestorErrores.agregarSemantico("NO SE PUDE INICIAR LA VARIABLE: " + name + " DE TIPO: " + varType + " CON UN VALOR DE TIPO " + initType + pistaHerencia(varType, initType), node.getLine(), node.getColumn());
            }
        }

        RolSimbolo rol = node.isArray() ? RolSimbolo.ARREGLO : RolSimbolo.VARIABLE;
        Simbolo sim = new Simbolo(name, varType, rol, node.getLine(), node.getColumn());
        context.insertar(sim);
        if (claseActual != null && context.getNombre().startsWith("Clase_")) {
            sim.setEsMiembroDeObjeto(true);
        }
        node.setSimbolo(sim);
        return Type.VOID;
    }

    @Override
    public Type visitAssignStmt(AssignStmt node, Ambito context) {
        Type targetType = node.getTarget().accept(this, context);
        node.setValue(adaptarLiteral(node.getValue(), targetType));
        Type valType = node.getValue().accept(this, context);

        if (!TablaCompatibilidad.esAsignableCompuesta(targetType, node.getOperator(), valType, relacion)) {
            gestorErrores.agregarSemantico("NO SE PUDE ASIGNAR UN VALOR DE TIPO: " + valType + " A UNA EXPRESION DE TIPO: " + targetType + " USANDO OPERADOR: " + node.getOperator().getSymbol() + pistaHerencia(targetType, valType), node.getLine(), node.getColumn());
        }
        return Type.VOID;
    }

    @Override
    public Type visitIfStmt(IfStmt node, Ambito context) {
        Type condType = node.getCondition().accept(this, context);
        if (condType.getCategory() != Type.TypeCategory.BOOLEAN && condType.getCategory() != Type.TypeCategory.ERROR) {
            gestorErrores.agregarSemantico("LA CONDICION DEL  'si / if' DEBE DE SER BOOL, SE TIENE: " + condType, node.getLine(), node.getColumn());
        }

        Ambito ambitoThen = tablaSimbolos.abrirAmbito("Bloque_Si");
        node.getThenBranch().accept(this, ambitoThen);
        tablaSimbolos.cerrarAmbito();

        for (ElseIfBranch eib : node.getElseIfBranches()) {
            eib.accept(this, context);
        }

        if (node.hasElseBranch()) {
            Ambito ambitoElse = tablaSimbolos.abrirAmbito("Bloque_Sino");
            node.getElseBranch().accept(this, ambitoElse);
            tablaSimbolos.cerrarAmbito();
        }

        return Type.VOID;
    }

    @Override
    public Type visitElseIfBranch(ElseIfBranch node, Ambito context) {
        Type condType = node.getCondition().accept(this, context);
        if (condType.getCategory() != Type.TypeCategory.BOOLEAN && condType.getCategory() != Type.TypeCategory.ERROR) {
            gestorErrores.agregarSemantico("LA CONDICION DEL 'sino / else if' DEBE DE SER BOOL, SE TIENE: " + condType, node.getLine(), node.getColumn());
        }

        Ambito ambitoElseIf = tablaSimbolos.abrirAmbito("Bloque_Sino_Si");
        node.getBody().accept(this, ambitoElseIf);
        tablaSimbolos.cerrarAmbito();
        return Type.VOID;
    }

    @Override
    public Type visitSwitchStmt(SwitchStmt node, Ambito context) {
        Type exprType = node.getExpression().accept(this, context);
        contadorSwitch++;
        Ambito ambitoSwitch = tablaSimbolos.abrirAmbito("Bloque_Switch");

        for (CaseBranch cb : node.getCases()) {
            cb.accept(this, ambitoSwitch);
            if (!cb.isDefault() && cb.getCaseExpression() != null) {
                Type caseType = cb.getCaseExpression().getEvaluatedType();
                if (caseType != null && !TablaCompatibilidad.esAsignable(exprType, caseType, relacion)) {
                    gestorErrores.agregarSemantico("EL VALOR DEL CASO (" + caseType + ") NO COINCIDE CON EL TIPO SWITCH (" + exprType + ")", cb.getLine(), cb.getColumn());
                }
            }
        }

        tablaSimbolos.cerrarAmbito();
        contadorSwitch--;
        return Type.VOID;
    }

    @Override
    public Type visitCaseBranch(CaseBranch node, Ambito context) {
        if (node.getCaseExpression() != null) {
            node.getCaseExpression().accept(this, context);
        }
        for (Statement s : node.getStatements()) {
            s.accept(this, context);
        }
        return Type.VOID;
    }

    @Override
    public Type visitWhileStmt(WhileStmt node, Ambito context) {
        Type condType = node.getCondition().accept(this, context);
        if (condType.getCategory() != Type.TypeCategory.BOOLEAN && condType.getCategory() != Type.TypeCategory.ERROR) {
            gestorErrores.agregarSemantico("LA CONDICION DEL CICLO 'mientras / while / dum' DEBE DE SER BOOL, SE OBTUVO: " + condType, node.getLine(), node.getColumn());
        }

        contadorCiclos++;
        Ambito ambitoWhile = tablaSimbolos.abrirAmbito("Ciclo_Mientras");
        node.getBody().accept(this, ambitoWhile);
        tablaSimbolos.cerrarAmbito();
        contadorCiclos--;

        return Type.VOID;
    }

    @Override
    public Type visitDoWhileStmt(DoWhileStmt node, Ambito context) {
        contadorCiclos++;
        Ambito ambitoDo = tablaSimbolos.abrirAmbito("Ciclo_Hacer_Mientras");
        node.getBody().accept(this, ambitoDo);
        tablaSimbolos.cerrarAmbito();
        contadorCiclos--;

        Type condType = node.getCondition().accept(this, context);
        if (condType.getCategory() != Type.TypeCategory.BOOLEAN && condType.getCategory() != Type.TypeCategory.ERROR) {
            gestorErrores.agregarSemantico("LA CONDICION DEL CICLO 'hacer-mientras / do-while' DEBE DE SER BOOL, SE OBTUVO: " + condType, node.getLine(), node.getColumn());
        }

        return Type.VOID;
    }

    @Override
    public Type visitForStmt(ForStmt node, Ambito context) {
        Ambito ambitoFor = tablaSimbolos.abrirAmbito("Ciclo_Para");

        if (node.getInitializer() != null) {
            node.getInitializer().accept(this, ambitoFor);
        }

        if (node.getCondition() != null) {
            Type condType = node.getCondition().accept(this, ambitoFor);
            if (condType.getCategory() != Type.TypeCategory.BOOLEAN && condType.getCategory() != Type.TypeCategory.ERROR) {
                gestorErrores.agregarSemantico("LA CONDICION DEL CICLO 'para / for / per' DEBE DE SER BOOL, SE OBTUVO: " + condType, node.getLine(), node.getColumn());
            }
        }

        if (node.getUpdate() != null) {
            node.getUpdate().accept(this, ambitoFor);
        }

        contadorCiclos++;
        node.getBody().accept(this, ambitoFor);
        contadorCiclos--;

        tablaSimbolos.cerrarAmbito();
        return Type.VOID;
    }

    @Override
    public Type visitBreakStmt(BreakStmt node, Ambito context) {
        if (contadorCiclos <= 0 && contadorSwitch <= 0) {
            gestorErrores.agregarSemantico(
                    "INSTRUCCION 'romper / break / interrumpe' FUERA DE CICLO",
                    node.getLine(), node.getColumn());
        }
        return Type.VOID;
    }

    @Override
    public Type visitContinueStmt(ContinueStmt node, Ambito context) {
        if (contadorCiclos <= 0) {
            gestorErrores.agregarSemantico("INSTRUCCION 'continuar / continue / perge' FUERA DE CICLO", node.getLine(), node.getColumn());
        }
        return Type.VOID;
    }

    @Override
    public Type visitReturnStmt(ReturnStmt node, Ambito context) {
        if (tipoRetornoFuncionActual == null) {
            gestorErrores.agregarSemantico("INSTRUCCION 'retornar / return / reddere' FUERA DE FUCION-METODO", node.getLine(), node.getColumn());
            return Type.ERROR;
        }

        Type valType = node.hasValue() ? node.getValue().accept(this, context) : Type.VOID;
        if (!TablaCompatibilidad.esAsignable(tipoRetornoFuncionActual, valType, relacion)) {
            gestorErrores.agregarSemantico("EL TIPO RETORNADO (" + valType + ") NO COINCIDE CON EL TIPO DE RETORNO DE (" + tipoRetornoFuncionActual + ")", node.getLine(), node.getColumn());
        }
        return Type.VOID;
    }

    @Override
    public Type visitPrintStmt(PrintStmt node, Ambito context) {
        for (Expression e : node.getExpressions()) {
            e.accept(this, context);
        }
        return Type.VOID;
    }

    @Override
    public Type visitReadStmt(ReadStmt node, Ambito context) {
        if (node.hasTarget()) {
            node.getTarget().accept(this, context);
        }
        return Type.VOID;
    }

    @Override
    public Type visitExprStmt(ExprStmt node, Ambito context) {
        if (node.getExpression() != null) {
            node.getExpression().accept(this, context);
        }
        return Type.VOID;
    }

    // EXPRESIONES

    @Override
    public Type visitBinaryExpr(BinaryExpr node, Ambito context) {
        Type izq = node.getLeft().accept(this, context);
        Type der = node.getRight().accept(this, context);

        Type resultado = TablaCompatibilidad.obtenerTipoBinario(izq, node.getOperator(), der, relacion);
        if (resultado.getCategory() == Type.TypeCategory.ERROR) {
            gestorErrores.agregarSemantico("OPERACION NO VALIDA: " + izq + " " + node.getOperator().getSymbol() + " " + der, node.getLine(), node.getColumn());
        }

        node.setEvaluatedType(resultado);
        return resultado;
    }

    @Override
    public Type visitUnaryExpr(UnaryExpr node, Ambito context) {
        Type opType = node.getOperand().accept(this, context);
        Type resultado = TablaCompatibilidad.obtenerTipoUnario(node.getOperator(), opType);

        if (resultado.getCategory() == Type.TypeCategory.ERROR) {
            gestorErrores.agregarSemantico("OPERACION UNUARIA NO VALIDA: " + node.getOperator().getSymbol() + opType, node.getLine(), node.getColumn());
        }

        node.setEvaluatedType(resultado);
        return resultado;
    }

    @Override
    public Type visitTernaryExpr(TernaryExpr node, Ambito context) {
        Type cond = node.getCondition().accept(this, context);
        if (cond.getCategory() != Type.TypeCategory.BOOLEAN) {
            gestorErrores.agregarSemantico("LA CONDICION DEL OPERADOR TERNARIO DEBE DE SER BOOL", node.getLine(), node.getColumn());
        }
        Type t1 = node.getThenExpr().accept(this, context);
        Type t2 = node.getElseExpr().accept(this, context);

        Type resultado = t1.equals(t2) ? t1 : (TablaCompatibilidad.esAsignable(t1, t2, relacion) ? t1 : (TablaCompatibilidad.esAsignable(t2, t1, relacion) ? t2 : Type.ERROR));
        if (resultado.getCategory() == Type.TypeCategory.ERROR) {
            gestorErrores.agregarSemantico("TIPOS INCOMPATIBLES EN OPERACION TERNARIA " + t1 + " Y " + t2, node.getLine(), node.getColumn());
        }

        node.setEvaluatedType(resultado);
        return resultado;
    }

    @Override
    public Type visitLiteralExpr(LiteralExpr node, Ambito context) {
        Type t = node.getLiteralType();
        node.setEvaluatedType(t);
        return t;
    }

    @Override
    public Type visitIdentifierExpr(IdentifierExpr node, Ambito context) {
        Simbolo sim = context.buscar(node.getName());
        if (sim == null) {
            gestorErrores.agregarSemantico("ID NO DECLARADO: " + node.getName(), node.getLine(), node.getColumn());
            node.setEvaluatedType(Type.ERROR);
            return Type.ERROR;
        }
        if (sim.esMiembroDeObjeto()) {
            validarAcceso(sim, "ATRIBUTO", node.getLine(), node.getColumn());
        }
        node.setSimbolo(sim);
        node.setEvaluatedType(sim.getTipo());
        return sim.getTipo();
    }

    @Override
    public Type visitMemberAccessExpr(MemberAccessExpr node, Ambito context) {
        Type targetType = node.getTarget().accept(this, context);
        if (targetType.getCategory() == Type.TypeCategory.ERROR) {
            node.setEvaluatedType(Type.ERROR);
            return Type.ERROR;
        }
        if (targetType.getCategory() != Type.TypeCategory.STRUCT && targetType.getCategory() != Type.TypeCategory.CLASS) {
            gestorErrores.agregarSemantico("NO SE PUEDE ACCEDER AL MIEMBRO: " + node.getMemberName() + " EN UN TIPO QUE NO ES OBJETO O ESTRUCTURA: " + targetType, node.getLine(), node.getColumn());
            node.setEvaluatedType(Type.ERROR);
            return Type.ERROR;
        }

        Simbolo def = tablaSimbolos.getAmbitoGlobal().buscar(targetType.getTypeName());
        if (def != null) {
            Simbolo miembro = def.buscarMiembro(node.getMemberName());
            if (miembro != null) {
                if (def.getRol() == RolSimbolo.CLASE) {
                    validarAcceso(miembro, "ATRIBUTO", node.getLine(), node.getColumn());
                }
                node.setEvaluatedType(miembro.getTipo());
                return miembro.getTipo();
            }
        }

        gestorErrores.agregarSemantico("EL MIEMBRO: " + node.getMemberName() + " NO EXISTE EN " + targetType.getTypeName(), node.getLine(), node.getColumn());
        node.setEvaluatedType(Type.ERROR);
        return Type.ERROR;
    }

    @Override
    public Type visitArrayAccessExpr(ArrayAccessExpr node, Ambito context) {
        List<Expression> indices = new ArrayList<>();
        Expression arreglo = ArrayAccessExpr.recolectarIndices(node, indices);
        Type targetType = arreglo.accept(this, context);

        for (Expression indice : indices) {
            Type idxType = indice.accept(this, context);
            if (idxType.getCategory() != Type.TypeCategory.INT && idxType.getCategory() != Type.TypeCategory.ERROR) {
                gestorErrores.agregarSemantico("EL INDICE DEL ARREGLO DEBE DE SER ENTERO SE OBTUVO: " + idxType, indice.getLine(), indice.getColumn());
            }
        }

        if (targetType.getCategory() != Type.TypeCategory.ARRAY) {
            if (targetType.getCategory() != Type.TypeCategory.ERROR) {
                gestorErrores.agregarSemantico("LA EXPRESION NO ES UN ARREGLO " + targetType, node.getLine(), node.getColumn());
            }
            node.setEvaluatedType(Type.ERROR);
            return Type.ERROR;
        }

        if (!targetType.tieneDimensionesLibres() && indices.size() != targetType.getDimensions()) {
            gestorErrores.agregarSemantico("EL ARREGLO ES DE " + targetType.getDimensions()
                    + " DIMENSION(ES) Y SE ACCEDIO CON " + indices.size() + " INDICE(S), SE DEBEN INDICAR TODOS",
                    node.getLine(), node.getColumn());
            node.setEvaluatedType(Type.ERROR);
            return Type.ERROR;
        }

        Type resultado = targetType.getBaseType();
        node.setEvaluatedType(resultado);
        return resultado;
    }

    @Override
    public Type visitCallExpr(CallExpr node, Ambito context) {
        boolean esSuperConstructor = node.esLlamadaConstructorPadre();
        boolean superEnPosicionValida = superPermitido;
        superPermitido = false;

        List<Type> tiposArgumentos = new ArrayList<>();
        for (Expression arg : node.getArguments()) {
            tiposArgumentos.add(arg.accept(this, context));
        }

        if (esSuperConstructor) {
            return analizarLlamadaConstructorPadre(node, tiposArgumentos, superEnPosicionValida);
        }

        List<Simbolo> candidatos = new ArrayList<>();
        String descripcion = node.getFunctionName();
        String claseEstatica = null;

        if (node.getTarget() != null) {
            Type targetType = node.getTarget().accept(this, context);
            if (targetType.getCategory() == Type.TypeCategory.ERROR) {
                node.setEvaluatedType(Type.ERROR);
                return Type.ERROR;
            }
            if (targetType.getCategory() != Type.TypeCategory.CLASS
                    && targetType.getCategory() != Type.TypeCategory.STRUCT) {
                gestorErrores.agregarSemantico("NO SE PUEDE INVOCAR: " + node.getFunctionName()
                        + " SOBRE UN VALOR DE TIPO " + targetType, node.getLine(), node.getColumn());
                node.setEvaluatedType(Type.ERROR);
                return Type.ERROR;
            }
            Simbolo definicion = tablaSimbolos.getAmbitoGlobal().buscarEnActual(targetType.getTypeName());
            if (definicion != null && definicion.getRol() == RolSimbolo.CLASE) {
                candidatos = metodosVisibles(definicion, node.getFunctionName());
                claseEstatica = definicion.getIdentificador();
            } else if (definicion != null) {
                candidatos = definicion.buscarMetodos(node.getFunctionName());
            }
            descripcion = targetType.getTypeName() + "." + node.getFunctionName();
        } else {
            Simbolo funcion = context.buscarFuncion(node.getFunctionName());
            if (funcion != null) {
                candidatos.add(funcion);
            } else if (claseActual != null) {
                candidatos = metodosVisibles(claseActual, node.getFunctionName());
                claseEstatica = claseActual.getIdentificador();
            }
        }

        if (candidatos.isEmpty()) {
            gestorErrores.agregarSemantico("FUNCION O METODO NO ENCONTRADO: " + descripcion,
                    node.getLine(), node.getColumn());
            node.setEvaluatedType(Type.ERROR);
            return Type.ERROR;
        }

        Simbolo elegido = resolverSobrecarga(candidatos, tiposArgumentos);
        if (elegido == null) {
            Simbolo referencia = candidatos.get(0);
            if (candidatos.size() == 1 && referencia.getParametros().size() != tiposArgumentos.size()) {
                gestorErrores.agregarSemantico("LA FUNCION: " + descripcion + " ESPERA"
                        + referencia.getParametros().size() + " ARGUMENTOS PERO SE ENVIARON: "
                        + tiposArgumentos.size(), node.getLine(), node.getColumn());
            } else {
                gestorErrores.agregarSemantico("NINGUNA VERSION DE: " + descripcion
                        + " ACEPTA ARGUMENTOS: " + tiposArgumentos, node.getLine(), node.getColumn());
            }
            node.setEvaluatedType(Type.ERROR);
            return Type.ERROR;
        }

        if (elegido.getRol() == RolSimbolo.METODO) {
            validarAcceso(elegido, "METODO", node.getLine(), node.getColumn());
            node.setClaseEstatica(claseEstatica);
        }
        node.setSimboloFuncion(elegido);
        node.setEvaluatedType(elegido.getTipo());
        return elegido.getTipo();
    }

    private Type analizarLlamadaConstructorPadre(CallExpr node, List<Type> tiposArgumentos, boolean enPosicionValida) {
        node.setEvaluatedType(Type.VOID);
        if (!enPosicionValida || claseActual == null) {
            gestorErrores.agregarSemantico("super(...) SOLO SE PUEDE USAR COMO PRIMERA INSTRUCCION DE UN CONSTRUCTOR",
                    node.getLine(), node.getColumn());
            return Type.ERROR;
        }
        Simbolo padre = claseActual.getSuperClase();
        if (padre == null) {
            gestorErrores.agregarSemantico("LA CLASE: " + claseActual.getIdentificador()
                    + " NO HEREDA DE NINGUNA CLASE, NO PUEDE LLAMAR A super(...)", node.getLine(), node.getColumn());
            return Type.ERROR;
        }
        if (padre.getConstructores().isEmpty()) {
            if (!tiposArgumentos.isEmpty()) {
                gestorErrores.agregarSemantico("LA CLASE PADRE: " + padre.getIdentificador() + " NO DECLARA UN CONSTRUCTOR QUE RECIBA "
                        + tiposArgumentos.size() + " ARGUMENTO(S)", node.getLine(), node.getColumn());
            }
            return Type.VOID;
        }
        Simbolo constructor = resolverSobrecarga(padre.getConstructores(), tiposArgumentos);
        if (constructor == null) {
            gestorErrores.agregarSemantico("NINGUN CONSTRUCTOR DE LA CLASE PADRE: " + padre.getIdentificador()
                    + " ACEPTA LOS ARGUMENTOS " + tiposArgumentos, node.getLine(), node.getColumn());
            return Type.ERROR;
        }
        validarAcceso(constructor, "CONSTRUCTOR", node.getLine(), node.getColumn());
        node.setSimboloFuncion(constructor);
        return Type.VOID;
    }

    @Override
    public Type visitNewObjectExpr(NewObjectExpr node, Ambito context) {
        Simbolo classSim = tablaSimbolos.getAmbitoGlobal().buscarEnActual(node.getClassName());

        List<Type> tiposArgumentos = new ArrayList<>();
        for (Expression arg : node.getArguments()) {
            tiposArgumentos.add(arg.accept(this, context));
        }

        if (classSim == null || classSim.getRol() != RolSimbolo.CLASE) {
            gestorErrores.agregarSemantico("CLASE NO DECLARADA: " + node.getClassName(),
                    node.getLine(), node.getColumn());
            node.setEvaluatedType(Type.ERROR);
            return Type.ERROR;
        }

        if (classSim.getConstructores().isEmpty()) {
            if (!tiposArgumentos.isEmpty()) {
                gestorErrores.agregarSemantico("LA CLASE: " + node.getClassName()
                                + " NO DECLARA UN CONSTRUCTOR QUE RECIBA " + tiposArgumentos.size() + " ARGUMENTO(S)",
                        node.getLine(), node.getColumn());
            }
        } else {
            Simbolo constructor = resolverSobrecarga(classSim.getConstructores(), tiposArgumentos);
            if (constructor == null) {
                gestorErrores.agregarSemantico("NINGUN CONSTRUCTOR DE: " + node.getClassName()
                        + " ACEPTA LOS ARGUMENTOS " + tiposArgumentos, node.getLine(), node.getColumn());
            } else {
                validarAcceso(constructor, "CONSTRUCTOR", node.getLine(), node.getColumn());
                node.setSimboloConstructor(constructor);
            }
        }

        Type res = Type.createCustomType(node.getClassName(), true);
        node.setEvaluatedType(res);
        return res;
    }

    @Override
    public Type visitNewArrayExpr(NewArrayExpr node, Ambito context) {
        for (Expression dim : node.getDimensionSizes()) {
            Type dType = dim.accept(this, context);
            if (dType.getCategory() != Type.TypeCategory.INT) {
                gestorErrores.agregarSemantico("EL TAMANIO DEL ARREGLO DEBE DE SER ENTERO", node.getLine(), node.getColumn());
            }
        }

        Type elemType = node.getElementType();
        int dimensiones = Math.max(1, node.getDimensionSizes().size());

        if (!node.getInitialValues().isEmpty()) {
            List<Integer> forma = NewArrayExpr.formaDeLiteral(node);
            if (forma == null) {
                gestorErrores.agregarSemantico("LAS FILAS DEL ARREGLO DEBEN TENER TODAS EL MISMO TAMANIO",
                        node.getLine(), node.getColumn());
                forma = List.of(node.getInitialValues().size());
            }
            dimensiones = forma.size();
            for (Expression val : NewArrayExpr.hojasDeLiteral(node)) {
                Type vType = val.accept(this, context);
                if (elemType == null) {
                    elemType = vType;
                } else if (node.getElementType() != null
                        && !TablaCompatibilidad.esAsignable(elemType, vType, relacion) && vType.getCategory() != Type.TypeCategory.ERROR) {
                    gestorErrores.agregarSemantico("EL VALOR DE TIPO " + vType + " NO PUEDE IR EN UN ARREGLO DE " + elemType,
                            val.getLine(), val.getColumn());
                }
            }
        }

        Type res = Type.createArrayType(elemType != null ? elemType : Type.INT, dimensiones);
        node.setEvaluatedType(res);
        return res;
    }

    @Override
    public Type visitNewStructExpr(NewStructExpr node, Ambito context) {
        String structName = node.getStructName();

        Simbolo structDef = structName != null
                ? tablaSimbolos.getAmbitoGlobal().buscarEnActual(structName)
                : null;

        if (structDef != null && structDef.getRol() == RolSimbolo.ESTRUCTURA) {
            List<Simbolo> campos = new ArrayList<>(structDef.getMiembros().values());
            for (int i = 0; i < Math.min(campos.size(), node.getValues().size()); i++) {
                node.setValue(i, adaptarLiteral(node.getValues().get(i), campos.get(i).getTipo()));
            }
        }

        List<Type> tiposValores = new ArrayList<>();
        for (Expression val : node.getValues()) {
            tiposValores.add(val.accept(this, context));
        }

        if (structName != null && structDef == null) {
            gestorErrores.agregarSemantico("LA ESTRUCTURA: " + structName + " NO ESTA DECLARADA "
                    + "DEBE DE DEFINIRSE O IMPORTARSE DEL ARCHIVO .y", node.getLine(), node.getColumn());
        } else if (structDef != null && structDef.getRol() == RolSimbolo.ESTRUCTURA) {
            validarValoresDeEstructura(node, structDef, tiposValores);
        }

        Type res = structName != null
                ? Type.createCustomType(structName, false)
                : Type.createCustomType("AnonStruct", false);
        node.setEvaluatedType(res);
        return res;
    }

    private void validarValoresDeEstructura(NewStructExpr node, Simbolo structDef, List<Type> tiposValores) {
        List<Simbolo> campos = new ArrayList<>(structDef.getMiembros().values());

        if (campos.size() != tiposValores.size()) {
            gestorErrores.agregarSemantico("LA ESTRUCTURA: " + structDef.getIdentificador() + " TIENE "
                            + campos.size() + " CAMPOS PERO EL LITERAL TRAE: " + tiposValores.size() + " VALORES",
                    node.getLine(), node.getColumn());
            return;
        }

        for (int i = 0; i < campos.size(); i++) {
            Type tipoCampo = campos.get(i).getTipo();
            Type tipoValor = tiposValores.get(i);
            Expression valor = node.getValues().get(i);

            if (!TablaCompatibilidad.esAsignable(tipoCampo, tipoValor, relacion)) {
                gestorErrores.agregarSemantico("EL CAMPO: " + campos.get(i).getIdentificador()
                        + " DE: " + structDef.getIdentificador() + " ES DE TIPO " + tipoCampo
                        + " Y SE ASIGNO " + tipoValor, valor.getLine(), valor.getColumn());
            }
        }
    }

    private Expression adaptarLiteral(Expression valor, Type esperado) {
        if (esperado == null) {
            return valor;
        }
        if (valor instanceof NewStructExpr literal && literal.getStructName() == null) {
            if (esperado.getCategory() == Type.TypeCategory.STRUCT) {
                return new NewStructExpr(esperado.getTypeName(), literal.getValues(), literal.getLine(), literal.getColumn());
            }
            if (esperado.getCategory() == Type.TypeCategory.ARRAY && esperado.getBaseType() != null) {
                return literalComoArreglo(literal.getValues(), esperado.getBaseType(),
                        Math.max(1, esperado.getDimensions()), literal.getLine(), literal.getColumn());
            }
        }
        if (valor instanceof NewArrayExpr arreglo && !arreglo.isExplicitAllocation()
                && arreglo.getElementType() == null && esperado.getCategory() == Type.TypeCategory.STRUCT) {
            return new NewStructExpr(esperado.getTypeName(), arreglo.getInitialValues(), arreglo.getLine(), arreglo.getColumn());
        }
        return valor;
    }

    private NewArrayExpr literalComoArreglo(List<Expression> valores, Type tipoElemento, int dimensiones, int linea, int columna) {
        List<Expression> elementos = new ArrayList<>();
        for (Expression valor : valores) {
            if (dimensiones > 1 && valor instanceof NewStructExpr fila && fila.getStructName() == null) {
                elementos.add(literalComoArreglo(fila.getValues(), tipoElemento, dimensiones - 1, fila.getLine(), fila.getColumn()));
            } else {
                elementos.add(adaptarLiteral(valor, tipoElemento));
            }
        }
        return new NewArrayExpr(tipoElemento, List.of(), elementos, linea, columna);
    }

    @Override
    public Type visitReadExpr(ReadExpr node, Ambito context) {
        Type res = Type.STRING;
        node.setEvaluatedType(res);
        return res;
    }

    @Override
    public Type visitThisExpr(ThisExpr node, Ambito context) {
        if (claseActual == null) {
            gestorErrores.agregarSemantico("'this' SOLO SE PUEDE USAR DENTRO DE UN METODO O CONSTRUCTOR",
                    node.getLine(), node.getColumn());
            node.setEvaluatedType(Type.ERROR);
            return Type.ERROR;
        }
        node.setEvaluatedType(claseActual.getTipo());
        return claseActual.getTipo();
    }

    @Override
    public Type visitSuperExpr(SuperExpr node, Ambito context) {
        if (claseActual == null) {
            gestorErrores.agregarSemantico("'super' SOLO SE PUEDE USAR DENTRO DE UN METODO O CONSTRUCTOR",
                    node.getLine(), node.getColumn());
            node.setEvaluatedType(Type.ERROR);
            return Type.ERROR;
        }
        Simbolo padre = claseActual.getSuperClase();
        if (padre == null) {
            gestorErrores.agregarSemantico("LA CLASE: " + claseActual.getIdentificador()
                    + " NO HEREDA DE NINGUNA CLASE, NO SE PUEDE USAR 'super'", node.getLine(), node.getColumn());
            node.setEvaluatedType(Type.ERROR);
            return Type.ERROR;
        }
        node.setEvaluatedType(padre.getTipo());
        return padre.getTipo();
    }
}
