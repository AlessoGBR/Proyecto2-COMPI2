package Backend.c3d;

import Backend.Ast.AstVisitor;
import Backend.Ast.ModificadorAcceso;
import Backend.Ast.ProgramNode;
import Backend.Ast.Type;
import Backend.Ast.declaration.*;
import Backend.Ast.expression.*;
import Backend.Ast.statement.*;
import Backend.estructuras.Pila;
import Backend.simbolos.Ambito;
import Backend.simbolos.RolSimbolo;
import Backend.simbolos.Simbolo;
import Backend.simbolos.TablaSimbolos;

import java.util.*;
import java.util.function.Consumer;

public class GeneradorC3D implements AstVisitor<String, Ambito> {

    private final ControladorC3D controlador;
    private final TablaSimbolos tablaSimbolos;

    private final Pila<Integer> marcosActivos = new Pila<>();

    // DECLARACIONES DE ESTRUCTURAS POR NIOMBRE
    private final Map<String, StructDecl> estructuras = new HashMap<>();

    // DECLARACIONES DE CLASES POR NOMBRE 
    private final Map<String, ClassDecl> clases = new HashMap<>();
    private Simbolo claseEnGeneracion;

    //PROCEDIMIENTOS NATIVOS C3D
    private final NativasC3D nativas;

    public GeneradorC3D(ControladorC3D controlador, TablaSimbolos tablaSimbolos) {
        this.controlador = controlador != null ? controlador : new ControladorC3D();
        this.tablaSimbolos = tablaSimbolos != null ? tablaSimbolos : new TablaSimbolos();
        this.nativas = new NativasC3D(this.controlador);
    }

    public ControladorC3D getControlador() {
        return controlador;
    }

    public void generar(ProgramNode programa) {
        if (programa == null) {
            return;
        }
        programa.accept(this, tablaSimbolos.getAmbitoGlobal());
    }

    private int marcoActual() {
        Integer marco = marcosActivos.cimaONulo();
        return marco != null ? marco : 1;
    }

    // PROGRAMA

    @Override
    public String visitProgramNode(ProgramNode node, Ambito context) {
        controlador.setTamanioAreaGlobal(node.getTamanioAreaGlobal());
        for (StructDecl estructura : node.getStructs()) {
            estructuras.put(estructura.getName(), estructura);
        }
        for (ClassDecl clase : node.getClasses()) {
            clases.putIfAbsent(clase.getName(), clase);
        }

        // FUNCIONES Y METODOS
        for (FunctionDecl f : node.getFunctions()) {
            f.accept(this, context);
        }
        for (ClassDecl c : node.getClasses()) {
            c.accept(this, context);
        }

        // BLOQUE PRINCIPAL
        controlador.agregar(OperadorC3D.INICIO_FUNCION, "", "", "main_programa");
        marcosActivos.apilar(Math.max(1, node.getTamanioMarcoPrincipal()));

        for (VarDeclStmt v : node.getGlobalVariables()) {
            v.accept(this, context);
        }
        if (node.getMainBlock() != null) {
            node.getMainBlock().accept(this, context);
        } else {
            generarLlamadaAlPuntoDeEntrada(node);
        }

        marcosActivos.desapilar();
        controlador.agregar(OperadorC3D.RETURN, "", "", "");
        controlador.agregar(OperadorC3D.FIN_FUNCION, "", "", "main_programa");

        nativas.generarUsadas();
        return null;
    }

    private void generarLlamadaAlPuntoDeEntrada(ProgramNode node) {
        for (FunctionDecl f : node.getFunctions()) {
            if (f.getParameters().isEmpty()
                    && (f.getName().equals("principal") || f.getName().equals("main"))) {
                controlador.agregarComentario("PUNTO DE ENTRADA: " + f.getName() + "()");
                invocar(f.getEtiquetaC3D() != null ? f.getEtiquetaC3D() : "func_" + f.getName(), List.of());
                return;
            }
        }

        for (ClassDecl c : node.getClasses()) {
            for (FunctionDecl m : c.getMethods()) {
                if (m.getParameters().isEmpty() && m.getName().equals("main")) {
                    controlador.agregarComentario("PUNTO DE ENTRADA: " + c.getName() + ".main()");

                    Simbolo clase = simboloDeClase(c.getName());
                    String tObjeto = clase != null ? reservarObjeto(clase) : "0";
                    controlador.agregarAsignacion("THIS", tObjeto);
                    if (clase != null) {
                        construirPorDefecto(clase);
                    }
                    invocar(m.getEtiquetaC3D() != null ? m.getEtiquetaC3D()
                            : "metodo_" + c.getName() + "_main_0", List.of());
                    return;
                }
            }
        }
    }

    // DECLARACIONES

    @Override
    public String visitClassDecl(ClassDecl node, Ambito context) {
        Simbolo anterior = claseEnGeneracion;
        claseEnGeneracion = simboloDeClase(node.getName());

        if (claseEnGeneracion != null && tieneValoresIniciales(claseEnGeneracion)) {
            generarInicializador(node, claseEnGeneracion, context);
        }
        for (ConstructorDecl c : node.getConstructors()) {
            c.accept(this, context);
        }
        for (FunctionDecl m : node.getMethods()) {
            m.accept(this, context);
        }

        claseEnGeneracion = anterior;
        return null;
    }

    // init_<Clase>: pone los valores iniciales de los atributos propios (int x = 5;) sobre
    // THIS. Como en Java, cada constructor lo ejecuta despues de super(...) y antes de su
    // cuerpo, asi un valor inicial ya ve lo que dejo el constructor del padre
    private void generarInicializador(ClassDecl node, Simbolo clase, Ambito context) {
        String etiqueta = etiquetaInicializador(clase);
        controlador.agregar(OperadorC3D.INICIO_FUNCION, "", "", etiqueta);
        marcosActivos.apilar(1);

        for (VarDeclStmt atributo : node.getAttributes()) {
            Simbolo miembro = clase.buscarMiembro(atributo.getName());
            if (atributo.getInitialValue() == null || miembro == null) {
                continue;
            }
            String valor = atributo.getInitialValue().accept(this, context);
            String tDir = controlador.nuevoTemporal();
            controlador.agregar(OperadorC3D.SUMA, "THIS", String.valueOf(miembro.getDireccionRelativa()), tDir);
            controlador.agregar(OperadorC3D.ASIG_HEAP, valor, "", tDir);
        }

        marcosActivos.desapilar();
        controlador.agregar(OperadorC3D.RETURN, "", "", "");
        controlador.agregar(OperadorC3D.FIN_FUNCION, "", "", etiqueta);
    }

    private String etiquetaInicializador(Simbolo clase) {
        return "init_" + clase.getIdentificador();
    }

    // TRUE SI LA CLASE DECLARA ATRIBUTOS CON VALOR INICIAL
    private boolean tieneValoresIniciales(Simbolo clase) {
        ClassDecl declaracion = clases.get(clase.getIdentificador());
        if (declaracion == null) {
            return false;
        }
        for (VarDeclStmt atributo : declaracion.getAttributes()) {
            if (atributo.getInitialValue() != null) {
                return true;
            }
        }
        return false;
    }

    private void ejecutarValoresIniciales(Simbolo clase) {
        if (tieneValoresIniciales(clase)) {
            invocar(etiquetaInicializador(clase), List.of());
        }
    }

    // CONSTRUCTOR POR DEFECTO, EL SUPER IMPLICITO Y DESPUES LOS VALORES INICIALES PROPIOS
    private void construirPorDefecto(Simbolo clase) {
        construirPadre(clase);
        ejecutarValoresIniciales(clase);
    }

    private Simbolo simboloDeClase(String nombre) {
        Simbolo simbolo = nombre != null ? tablaSimbolos.getAmbitoGlobal().buscarEnActual(nombre) : null;
        return simbolo != null && simbolo.getRol() == RolSimbolo.CLASE ? simbolo : null;
    }

    private String reservarObjeto(Simbolo clase) {
        int atributos = clase.getMiembros().size();
        controlador.agregarComentario("NUEVO OBJETO " + clase.getIdentificador() + ": [0]=ID CLASE ("
                + clase.getIdClase() + ") + " + atributos + " ATRIBUTO(S)");

        String tObjeto = controlador.nuevoTemporal();
        controlador.agregar(OperadorC3D.ASIG, "H", "", tObjeto);
        controlador.agregar(OperadorC3D.ASIG_HEAP, String.valueOf(clase.getIdClase()), "", "H");
        controlador.agregar(OperadorC3D.SUMA, "H", "1", "H");
        for (int i = 0; i < atributos; i++) {
            controlador.agregar(OperadorC3D.ASIG_HEAP, "0", "", "H");
            controlador.agregar(OperadorC3D.SUMA, "H", "1", "H");
        }
        return tObjeto;
    }

    // SUPER IMPLICITO, EL CONSTRUCTOR SIN PARAMETROS DEL PADRE, SI EL PADRE NO LO TIEN SE CONSTRUYE POR DEFECTO
    private void construirPadre(Simbolo clase) {
        Simbolo padre = clase.getSuperClase();
        if (padre == null) {
            return;
        }
        for (Simbolo constructor : padre.getConstructores()) {
            if (constructor.getParametros().isEmpty()) {
                controlador.agregarComentario("super() IMPLICITO -> " + padre.getIdentificador());
                invocar(constructor.getEtiquetaC3D(), List.of());
                return;
            }
        }
        construirPorDefecto(padre);
    }

    private List<Simbolo> clasesDelPrograma() {
        List<Simbolo> resultado = new ArrayList<>();
        for (Simbolo simbolo : tablaSimbolos.getAmbitoGlobal().getTabla().values()) {
            if (simbolo.getRol() == RolSimbolo.CLASE) {
                resultado.add(simbolo);
            }
        }
        return resultado;
    }

    private String despachar(Simbolo metodo, String claseEstatica, String receptor, List<String> argumentos) {
        Simbolo estatica = simboloDeClase(claseEstatica);
        String etiquetaBase = metodo.getEtiquetaC3D();
        if (estatica == null || metodo.getModificador() == ModificadorAcceso.PRIVADO) {
            return invocar(etiquetaBase, argumentos);
        }

        String firma = metodo.getFirma();
        Map<String, List<Simbolo>> clasesPorImplementacion = new LinkedHashMap<>();
        for (Simbolo clase : clasesDelPrograma()) {
            if (!clase.esSubclaseDe(estatica.getIdentificador())) {
                continue;
            }
            Simbolo implementacion = clase.buscarImplementacion(firma);
            if (implementacion != null && !implementacion.getEtiquetaC3D().equals(etiquetaBase)) {
                clasesPorImplementacion.computeIfAbsent(implementacion.getEtiquetaC3D(), k -> new ArrayList<>()).add(clase);
            }
        }
        if (clasesPorImplementacion.isEmpty()) {
            return invocar(etiquetaBase, argumentos);
        }

        controlador.agregarComentario("DESPACHO DINAMICO: " + estatica.getIdentificador() + "." + firma);
        String tClase = controlador.nuevoTemporal();
        controlador.agregar(OperadorC3D.ACCESO_HEAP, receptor, "", tClase);

        Map<String, String> saltos = new LinkedHashMap<>();
        for (Map.Entry<String, List<Simbolo>> entrada : clasesPorImplementacion.entrySet()) {
            String etiquetaSalto = controlador.nuevaEtiqueta();
            saltos.put(entrada.getKey(), etiquetaSalto);
            for (Simbolo clase : entrada.getValue()) {
                controlador.agregar(OperadorC3D.IF_EQ, tClase, String.valueOf(clase.getIdClase()), etiquetaSalto);
            }
        }

        String tResultado = controlador.nuevoTemporal();
        String lFin = controlador.nuevaEtiqueta();
        controlador.agregarAsignacion(tResultado, invocar(etiquetaBase, argumentos));
        controlador.agregarGoto(lFin);
        for (Map.Entry<String, String> salto : saltos.entrySet()) {
            controlador.agregarEtiqueta(salto.getValue());
            controlador.agregarAsignacion(tResultado, invocar(salto.getKey(), argumentos));
            controlador.agregarGoto(lFin);
        }
        controlador.agregarEtiqueta(lFin);
        return tResultado;
    }

    @Override
    public String visitConstructorDecl(ConstructorDecl node, Ambito context) {
        String etiqueta = node.getEtiquetaC3D() != null
                ? node.getEtiquetaC3D()
                : "constructor_" + node.getClassName() + "_" + node.getParameters().size();

        controlador.agregar(OperadorC3D.INICIO_FUNCION, "", "", etiqueta);
        marcosActivos.apilar(Math.max(1, node.getTamanioMarco()));

        Simbolo clase = simboloDeClase(node.getClassName());
        List<Statement> instrucciones = node.getBody().getStatements();
        int primera = 0;
        if (clase != null) {
            if (node.tieneLlamadaSuperExplicita()) {
                instrucciones.get(0).accept(this, context);
                primera = 1;
            } else {
                construirPadre(clase);
            }
            ejecutarValoresIniciales(clase);
        }
        for (int i = primera; i < instrucciones.size(); i++) {
            instrucciones.get(i).accept(this, context);
        }

        marcosActivos.desapilar();
        controlador.agregar(OperadorC3D.RETURN, "", "", "");
        controlador.agregar(OperadorC3D.FIN_FUNCION, "", "", etiqueta);
        return null;
    }

    @Override
    public String visitFunctionDecl(FunctionDecl node, Ambito context) {
        String etiqueta = node.getEtiquetaC3D() != null ? node.getEtiquetaC3D() : "func_" + node.getName();

        controlador.agregar(OperadorC3D.INICIO_FUNCION, "", "", etiqueta);
        marcosActivos.apilar(Math.max(1, node.getTamanioMarco()));

        node.getBody().accept(this, context);

        marcosActivos.desapilar();
        controlador.agregar(OperadorC3D.RETURN, "", "", "");
        controlador.agregar(OperadorC3D.FIN_FUNCION, "", "", etiqueta);
        return null;
    }

    @Override
    public String visitParameter(Parameter node, Ambito context) {
        return null;
    }

    @Override
    public String visitStructDecl(StructDecl node, Ambito context) {
        return null;
    }

    @Override
    public String visitStructField(StructField node, Ambito context) {
        return null;
    }

    @Override
    public String visitImportDecl(ImportDecl node, Ambito context) {
        return null;
    }

    //SENTENCIAS

    @Override
    public String visitBlockStmt(BlockStmt node, Ambito context) {
        for (Statement s : node.getStatements()) {
            s.accept(this, context);
        }
        return null;
    }

    @Override
    public String visitVarDeclStmt(VarDeclStmt node, Ambito context) {
        String valor;

        if (node.getInitialValue() != null) {
            valor = node.getInitialValue().accept(this, context);
        } else if (node.isArray() && !node.getArrayDimensions().isEmpty()) {
            List<String> dimensiones = new ArrayList<>();
            for (Expression dimension : node.getArrayDimensions()) {
                dimensiones.add(dimension.accept(this, context));
            }
            valor = reservarArreglo(dimensiones, node.getType().getBaseType(), new HashSet<>());
        } else if (esEstructura(node.getType())) {
            valor = nuevaInstanciaEstructura(node.getType().getTypeName(), new HashSet<>());
        } else {
            valor = "0";
        }

        guardarEnSimbolo(node.getSimbolo(), valor);
        return null;
    }

    private String reservarArreglo(List<String> dimensiones, Type tipoElemento, Set<String> enConstruccion) {
        String tInicio = controlador.nuevoTemporal();
        controlador.agregar(OperadorC3D.ASIG, "H", "", tInicio);
        for (String dimension : dimensiones) {
            controlador.agregar(OperadorC3D.ASIG_HEAP, dimension, "", "H");
            controlador.agregar(OperadorC3D.SUMA, "H", "1", "H");
        }

        String tamanio = dimensiones.get(0);
        for (int i = 1; i < dimensiones.size(); i++) {
            String tProducto = controlador.nuevoTemporal();
            controlador.agregar(OperadorC3D.MULT, tamanio, dimensiones.get(i), tProducto);
            tamanio = tProducto;
        }

        String tElementos = controlador.nuevoTemporal();
        controlador.agregar(OperadorC3D.ASIG, "H", "", tElementos);
        generarCiclo(tamanio, contador -> {
            controlador.agregar(OperadorC3D.ASIG_HEAP, "0", "", "H");
            controlador.agregar(OperadorC3D.SUMA, "H", "1", "H");
        });

        if (esEstructura(tipoElemento)) {
            generarCiclo(tamanio, contador -> {
                String tInstancia = nuevaInstanciaEstructura(tipoElemento.getTypeName(), enConstruccion);
                String tDir = controlador.nuevoTemporal();
                controlador.agregar(OperadorC3D.SUMA, tElementos, contador, tDir);
                controlador.agregar(OperadorC3D.ASIG_HEAP, tInstancia, "", tDir);
            });
        }
        return tInicio;
    }

    private void generarCiclo(String veces, Consumer<String> cuerpo) {
        String tContador = controlador.nuevoTemporal();
        String lCondicion = controlador.nuevaEtiqueta();
        String lFin = controlador.nuevaEtiqueta();

        controlador.agregarAsignacion(tContador, "0");
        controlador.agregarEtiqueta(lCondicion);
        controlador.agregar(OperadorC3D.IF_GE, tContador, veces, lFin);
        cuerpo.accept(tContador);
        controlador.agregar(OperadorC3D.SUMA, tContador, "1", tContador);
        controlador.agregarGoto(lCondicion);
        controlador.agregarEtiqueta(lFin);
    }

    private boolean esEstructura(Type tipo) {
        if (tipo == null || !tipo.esDefinidoPorUsuario()) {
            return false;
        }
        Simbolo definicion = tablaSimbolos.getAmbitoGlobal().buscarEnActual(tipo.getTypeName());
        return definicion != null && definicion.getRol() == RolSimbolo.ESTRUCTURA;
    }

    private String nuevaInstanciaEstructura(String nombre, Set<String> enConstruccion) {
        Simbolo definicion = tablaSimbolos.getAmbitoGlobal().buscarEnActual(nombre);
        StructDecl declaracion = estructuras.get(nombre);

        String tInstancia = controlador.nuevoTemporal();
        controlador.agregar(OperadorC3D.ASIG, "H", "", tInstancia);
        int campos = definicion != null ? definicion.getMiembros().size() : 0;
        for (int i = 0; i < campos; i++) {
            controlador.agregar(OperadorC3D.ASIG_HEAP, "0", "", "H");
            controlador.agregar(OperadorC3D.SUMA, "H", "1", "H");
        }
        if (declaracion == null || definicion == null) {
            return tInstancia;
        }

        enConstruccion.add(nombre);
        for (StructField campo : declaracion.getFields()) {
            String valor = null;
            if (campo.isArray()) {
                List<String> dimensiones = new ArrayList<>();
                for (Expression tamanio : campo.getArraySizes()) {
                    dimensiones.add(tamanio.accept(this, null));
                }
                valor = reservarArreglo(dimensiones, campo.getType().getBaseType(), enConstruccion);
            } else if (esEstructura(campo.getType()) && !enConstruccion.contains(campo.getType().getTypeName())) {
                valor = nuevaInstanciaEstructura(campo.getType().getTypeName(), enConstruccion);
            }
            if (valor != null) {
                String tDir = controlador.nuevoTemporal();
                controlador.agregar(OperadorC3D.SUMA, tInstancia, String.valueOf(definicion.obtenerIndiceMiembro(campo.getName())), tDir);
                controlador.agregar(OperadorC3D.ASIG_HEAP, valor, "", tDir);
            }
        }
        enConstruccion.remove(nombre);
        return tInstancia;
    }

    @Override
    public String visitAssignStmt(AssignStmt node, Ambito context) {
        String valor;

        if (node.getOperator() == AssignOp.ASSIGN) {
            valor = node.getValue().accept(this, context);
        } else if (node.getOperator() == AssignOp.ADD_ASSIGN && esCadena(node.getTarget().getEvaluatedType())) {
            // texto += valor  ->  texto = texto + valor (concatenacion)
            String actual = node.getTarget().accept(this, context);
            String operando = comoCadena(node.getValue(), context);
            valor = llamarNativa(NativasC3D.Nativa.CONCATENAR, actual, operando);
        } else {
            String actual = node.getTarget().accept(this, context);
            String operando = node.getValue().accept(this, context);
            valor = controlador.nuevoTemporal();
            operacionAritmetica(operadorDeAsignacion(node.getOperator(), node.getTarget().getEvaluatedType()),
                    actual, operando, valor);
        }

        almacenarEnDestino(node.getTarget(), valor, context);
        return null;
    }

    private OperadorC3D operadorDeAsignacion(AssignOp op, Type tipoDestino) {
        return switch (op) {
            case ADD_ASSIGN -> OperadorC3D.SUMA;
            case SUB_ASSIGN -> OperadorC3D.RESTA;
            case MUL_ASSIGN -> OperadorC3D.MULT;
            case DIV_ASSIGN -> esEntero(tipoDestino) ? OperadorC3D.DIV_ENT : OperadorC3D.DIV;
            case MOD_ASSIGN -> OperadorC3D.MOD;
            case ASSIGN -> OperadorC3D.ASIG;
        };
    }

    // resultado = a op b SI ES DIVISION SE VALIDA QUE B NO SE A 0
    private void operacionAritmetica(OperadorC3D op, String a, String b, String resultado) {
        boolean divide = op == OperadorC3D.DIV || op == OperadorC3D.DIV_ENT || op == OperadorC3D.MOD;
        if (divide && !esConstanteDistintaDeCero(b)) {
            String lValido = controlador.nuevaEtiqueta();
            controlador.agregar(OperadorC3D.IF_NEQ, b, "0", lValido);
            llamarNativa(op == OperadorC3D.MOD ? NativasC3D.Nativa.ERROR_MODULO : NativasC3D.Nativa.ERROR_DIVISION);
            controlador.agregarEtiqueta(lValido);
        }
        controlador.agregar(op, a, b, resultado);
    }

    private boolean esConstanteDistintaDeCero(String operando) {
        try {
            return Double.parseDouble(operando) != 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private String llamarNativa(NativasC3D.Nativa nativa, String... argumentos) {
        return invocar(nativas.usar(nativa), List.of(argumentos));
    }

    private void almacenarEnDestino(Expression destino, String valor, Ambito context) {
        if (destino instanceof IdentifierExpr ide) {
            guardarEnSimbolo(ide.getSimbolo(), valor);

        } else if (destino instanceof MemberAccessExpr mae) {
            String dirObjeto = mae.getTarget().accept(this, context);
            int indice = obtenerIndiceMiembro(mae.getTarget().getEvaluatedType(), mae.getMemberName());
            String tDir = controlador.nuevoTemporal();
            controlador.agregar(OperadorC3D.SUMA, dirObjeto, String.valueOf(indice), tDir);
            controlador.agregar(OperadorC3D.ASIG_HEAP, valor, "", tDir);

        } else if (destino instanceof ArrayAccessExpr aae) {
            String tDir = direccionElementoArreglo(aae, context);
            controlador.agregar(OperadorC3D.ASIG_HEAP, valor, "", tDir);
        }
    }

    private void guardarEnSimbolo(Simbolo simbolo, String valor) {
        if (simbolo == null) {
            return;
        }
        String tDir = direccionDeSimbolo(simbolo);
        if (simbolo.esMiembroDeObjeto()) {
            controlador.agregar(OperadorC3D.ASIG_HEAP, valor, "", tDir);
        } else {
            controlador.agregar(OperadorC3D.ASIG_STACK, valor, "", tDir);
        }
    }

    private String direccionDeSimbolo(Simbolo simbolo) {
        String tDir = controlador.nuevoTemporal();
        int offset = simbolo.getDireccionRelativa();

        if (simbolo.esMiembroDeObjeto()) {
            controlador.agregar(OperadorC3D.SUMA, "THIS", String.valueOf(offset), tDir);
        } else if (simbolo.esGlobal()) {
            controlador.agregar(OperadorC3D.ASIG, String.valueOf(offset), "", tDir);
        } else {
            controlador.agregar(OperadorC3D.SUMA, "P", String.valueOf(offset), tDir);
        }
        return tDir;
    }

    private String direccionElementoArreglo(ArrayAccessExpr node, Ambito context) {
        List<Expression> indices = new ArrayList<>();
        Expression arreglo = ArrayAccessExpr.recolectarIndices(node, indices);
        String dirArreglo = arreglo.accept(this, context);

        String desplazamiento = indices.get(0).accept(this, context);
        for (int k = 1; k < indices.size(); k++) {
            String tDirDimension = controlador.nuevoTemporal();
            String tDimension = controlador.nuevoTemporal();
            String tProducto = controlador.nuevoTemporal();
            controlador.agregar(OperadorC3D.SUMA, dirArreglo, String.valueOf(k), tDirDimension);
            controlador.agregar(OperadorC3D.ACCESO_HEAP, tDirDimension, "", tDimension);
            controlador.agregar(OperadorC3D.MULT, desplazamiento, tDimension, tProducto);

            String indice = indices.get(k).accept(this, context);
            String tSuma = controlador.nuevoTemporal();
            controlador.agregar(OperadorC3D.SUMA, tProducto, indice, tSuma);
            desplazamiento = tSuma;
        }

        String tDatos = controlador.nuevoTemporal();
        String tDir = controlador.nuevoTemporal();
        controlador.agregar(OperadorC3D.SUMA, dirArreglo, String.valueOf(indices.size()), tDatos);
        controlador.agregar(OperadorC3D.SUMA, tDatos, desplazamiento, tDir);
        return tDir;
    }

    private int obtenerIndiceMiembro(Type tipoObjeto, String miembro) {
        if (tipoObjeto == null) {
            return 0;
        }
        Simbolo definicion = tablaSimbolos.getAmbitoGlobal().buscarEnActual(tipoObjeto.getTypeName());
        if (definicion == null) {
            return 0;
        }
        // NE ESTRUCTURAS ES LA PISICIN DEL CAMBIO Y EN CALSE YA CUENTA LA CABECERA Y LO HEREDADO
        Simbolo simboloMiembro = definicion.buscarMiembro(miembro);
        return simboloMiembro != null ? simboloMiembro.getDireccionRelativa() : 0;
    }

    @Override
    public String visitIfStmt(IfStmt node, Ambito context) {
        String lFin = controlador.nuevaEtiqueta();

        String condicion = node.getCondition().accept(this, context);
        String lSiguiente = controlador.nuevaEtiqueta();
        controlador.agregar(OperadorC3D.IF_FALSE, condicion, "", lSiguiente);
        node.getThenBranch().accept(this, context);
        controlador.agregarGoto(lFin);
        controlador.agregarEtiqueta(lSiguiente);

        for (ElseIfBranch rama : node.getElseIfBranches()) {
            String condicionRama = rama.getCondition().accept(this, context);
            String lSiguienteRama = controlador.nuevaEtiqueta();
            controlador.agregar(OperadorC3D.IF_FALSE, condicionRama, "", lSiguienteRama);
            rama.getBody().accept(this, context);
            controlador.agregarGoto(lFin);
            controlador.agregarEtiqueta(lSiguienteRama);
        }

        if (node.hasElseBranch()) {
            node.getElseBranch().accept(this, context);
        }

        controlador.agregarEtiqueta(lFin);
        return null;
    }

    @Override
    public String visitElseIfBranch(ElseIfBranch node, Ambito context) {
        return null;
    }

    @Override
    public String visitSwitchStmt(SwitchStmt node, Ambito context) {
        String valor = node.getExpression().accept(this, context);
        String lFin = controlador.nuevaEtiqueta();

        List<CaseBranch> casos = node.getCases();
        List<String> etiquetas = new ArrayList<>();
        String lPorDefecto = null;

        for (CaseBranch caso : casos) {
            String etiqueta = controlador.nuevaEtiqueta();
            etiquetas.add(etiqueta);
            if (caso.isDefault()) {
                lPorDefecto = etiqueta;
            } else {
                String valorCaso = caso.getCaseExpression().accept(this, context);
                controlador.agregar(OperadorC3D.IF_EQ, valor, valorCaso, etiqueta);
            }
        }
        controlador.agregarGoto(lPorDefecto != null ? lPorDefecto : lFin);

        controlador.apilarCiclo(lFin, lFin);
        for (int i = 0; i < casos.size(); i++) {
            controlador.agregarEtiqueta(etiquetas.get(i));
            for (Statement s : casos.get(i).getStatements()) {
                s.accept(this, context);
            }
        }
        controlador.desapilarCiclo();

        controlador.agregarEtiqueta(lFin);
        return null;
    }

    @Override
    public String visitCaseBranch(CaseBranch node, Ambito context) {
        return null;
    }

    @Override
    public String visitWhileStmt(WhileStmt node, Ambito context) {
        String lCondicion = controlador.nuevaEtiqueta();
        String lFin = controlador.nuevaEtiqueta();

        controlador.apilarCiclo(lFin, lCondicion);

        controlador.agregarEtiqueta(lCondicion);
        String condicion = node.getCondition().accept(this, context);
        controlador.agregar(OperadorC3D.IF_FALSE, condicion, "", lFin);

        node.getBody().accept(this, context);
        controlador.agregarGoto(lCondicion);

        controlador.agregarEtiqueta(lFin);
        controlador.desapilarCiclo();
        return null;
    }

    @Override
    public String visitDoWhileStmt(DoWhileStmt node, Ambito context) {
        String lCuerpo = controlador.nuevaEtiqueta();
        String lCondicion = controlador.nuevaEtiqueta();
        String lFin = controlador.nuevaEtiqueta();

        controlador.apilarCiclo(lFin, lCondicion);

        controlador.agregarEtiqueta(lCuerpo);
        node.getBody().accept(this, context);

        controlador.agregarEtiqueta(lCondicion);
        String condicion = node.getCondition().accept(this, context);
        controlador.agregar(OperadorC3D.IF_NEQ, condicion, "0", lCuerpo);

        controlador.agregarEtiqueta(lFin);
        controlador.desapilarCiclo();
        return null;
    }

    @Override
    public String visitForStmt(ForStmt node, Ambito context) {
        if (node.getInitializer() != null) {
            node.getInitializer().accept(this, context);
        }

        String lCondicion = controlador.nuevaEtiqueta();
        String lActualizacion = controlador.nuevaEtiqueta();
        String lFin = controlador.nuevaEtiqueta();

        controlador.apilarCiclo(lFin, lActualizacion);

        controlador.agregarEtiqueta(lCondicion);
        if (node.getCondition() != null) {
            String condicion = node.getCondition().accept(this, context);
            controlador.agregar(OperadorC3D.IF_FALSE, condicion, "", lFin);
        }

        node.getBody().accept(this, context);

        controlador.agregarEtiqueta(lActualizacion);
        if (node.getUpdate() != null) {
            node.getUpdate().accept(this, context);
        }
        controlador.agregarGoto(lCondicion);

        controlador.agregarEtiqueta(lFin);
        controlador.desapilarCiclo();
        return null;
    }

    @Override
    public String visitBreakStmt(BreakStmt node, Ambito context) {
        String etiqueta = controlador.getEtiquetaBreakActual();
        if (etiqueta != null) {
            controlador.agregarGoto(etiqueta);
        }
        return null;
    }

    @Override
    public String visitContinueStmt(ContinueStmt node, Ambito context) {
        String etiqueta = controlador.getEtiquetaContinueActual();
        if (etiqueta != null) {
            controlador.agregarGoto(etiqueta);
        }
        return null;
    }

    @Override
    public String visitReturnStmt(ReturnStmt node, Ambito context) {
        if (node.hasValue()) {
            String valor = node.getValue().accept(this, context);
            controlador.agregar(OperadorC3D.ASIG_STACK, valor, "", "P");
        }
        controlador.agregar(OperadorC3D.RETURN, "", "", "");
        return null;
    }

    @Override
    public String visitPrintStmt(PrintStmt node, Ambito context) {
        for (Expression e : node.getExpressions()) {
            String valor = e.accept(this, context);
            imprimirValor(valor, e.getEvaluatedType());
        }
        if (node.isAddNewline()) {
            controlador.agregar(OperadorC3D.PRINT_CHAR, "10", "", "");
        }
        return null;
    }

    private void imprimirValor(String valor, Type tipo) {
        Type.TypeCategory categoria = tipo != null ? tipo.getCategory() : Type.TypeCategory.INT;
        switch (categoria) {
            case STRING -> llamarNativa(NativasC3D.Nativa.IMPRIMIR_CADENA, valor);
            case CHAR -> controlador.agregar(OperadorC3D.PRINT_CHAR, valor, "", "");
            case BOOLEAN -> llamarNativa(NativasC3D.Nativa.IMPRIMIR_BOOLEANO, valor);
            case FLOAT -> llamarNativa(NativasC3D.Nativa.IMPRIMIR_DECIMAL, valor);
            default -> controlador.agregar(OperadorC3D.PRINT_INT, valor, "", "");
        }
    }

    @Override
    public String visitReadStmt(ReadStmt node, Ambito context) {
        boolean esTexto = node.hasTarget()
                && node.getTarget().getEvaluatedType() != null
                && node.getTarget().getEvaluatedType().getCategory() == Type.TypeCategory.STRING;

        String tLeido = llamarNativa(esTexto ? NativasC3D.Nativa.LEER_CADENA : NativasC3D.Nativa.LEER_NUMERO);

        if (node.hasTarget()) {
            almacenarEnDestino(node.getTarget(), tLeido, context);
        }
        return tLeido;
    }

    @Override
    public String visitExprStmt(ExprStmt node, Ambito context) {
        if (node.getExpression() != null) {
            node.getExpression().accept(this, context);
        }
        return null;
    }

    //EXPRESIONES

    @Override
    public String visitBinaryExpr(BinaryExpr node, Ambito context) {
        BinaryOp operador = node.getOperator();

        if (operador == BinaryOp.ADD && esCadena(node.getEvaluatedType())) {
            String izquierda = comoCadena(node.getLeft(), context);
            String derecha = comoCadena(node.getRight(), context);
            return llamarNativa(NativasC3D.Nativa.CONCATENAR, izquierda, derecha);
        }

        if (operador == BinaryOp.AND || operador == BinaryOp.OR) {
            return generarLogicoCortoCircuito(node, context);
        }

        String izquierda = node.getLeft().accept(this, context);
        String derecha = node.getRight().accept(this, context);
        String temp = controlador.nuevoTemporal();

        OperadorC3D op = switch (operador) {
            case ADD -> OperadorC3D.SUMA;
            case SUB -> OperadorC3D.RESTA;
            case MUL -> OperadorC3D.MULT;
            case DIV -> esEntero(node.getEvaluatedType()) ? OperadorC3D.DIV_ENT : OperadorC3D.DIV;
            case MOD -> OperadorC3D.MOD;
            case EQUALS -> OperadorC3D.IF_EQ;
            case NOT_EQUALS -> OperadorC3D.IF_NEQ;
            case LESS_THAN -> OperadorC3D.IF_LT;
            case LESS_EQUAL -> OperadorC3D.IF_LE;
            case GREATER_THAN -> OperadorC3D.IF_GT;
            case GREATER_EQUAL -> OperadorC3D.IF_GE;
            default -> OperadorC3D.SUMA;
        };

        if (op.esSaltoCondicional()) {
            String lVerdadero = controlador.nuevaEtiqueta();
            String lFin = controlador.nuevaEtiqueta();
            controlador.agregar(op, izquierda, derecha, lVerdadero);
            controlador.agregarAsignacion(temp, "0");
            controlador.agregarGoto(lFin);
            controlador.agregarEtiqueta(lVerdadero);
            controlador.agregarAsignacion(temp, "1");
            controlador.agregarEtiqueta(lFin);
        } else {
            operacionAritmetica(op, izquierda, derecha, temp);
        }

        return temp;
    }

    private String generarLogicoCortoCircuito(BinaryExpr node, Ambito context) {
        String temp = controlador.nuevoTemporal();
        String lFin = controlador.nuevaEtiqueta();

        if (node.getOperator() == BinaryOp.AND) {
            controlador.agregarAsignacion(temp, "0");
            String izquierda = node.getLeft().accept(this, context);
            controlador.agregar(OperadorC3D.IF_EQ, izquierda, "0", lFin);
            String derecha = node.getRight().accept(this, context);
            controlador.agregar(OperadorC3D.IF_EQ, derecha, "0", lFin);
            controlador.agregarAsignacion(temp, "1");
        } else {
            controlador.agregarAsignacion(temp, "1");
            String izquierda = node.getLeft().accept(this, context);
            controlador.agregar(OperadorC3D.IF_NEQ, izquierda, "0", lFin);
            String derecha = node.getRight().accept(this, context);
            controlador.agregar(OperadorC3D.IF_NEQ, derecha, "0", lFin);
            controlador.agregarAsignacion(temp, "0");
        }

        controlador.agregarEtiqueta(lFin);
        return temp;
    }

    private boolean esCadena(Type tipo) {
        return tipo != null && tipo.getCategory() == Type.TypeCategory.STRING;
    }

    private boolean esEntero(Type tipo) {
        return tipo != null && tipo.getCategory() == Type.TypeCategory.INT;
    }

    private String comoCadena(Expression expresion, Ambito context) {
        String valor = expresion.accept(this, context);
        Type tipo = expresion.getEvaluatedType();

        if (tipo == null || tipo.getCategory() == Type.TypeCategory.STRING) {
            return valor;
        }

        return switch (tipo.getCategory()) {
            case CHAR -> caracterACadena(valor);
            case BOOLEAN -> llamarNativa(NativasC3D.Nativa.BOOLEANO_A_CADENA, valor);
            case FLOAT -> llamarNativa(NativasC3D.Nativa.DECIMAL_A_CADENA, valor);
            default -> llamarNativa(NativasC3D.Nativa.ENTERO_A_CADENA, valor);
        };
    }

    // CADENA DE UN SOLO CARACTER HEAP[H] = C, HEAP [H+1] = -1
    private String caracterACadena(String caracter) {
        String inicio = controlador.nuevoTemporal();
        controlador.agregarAsignacion(inicio, "H");
        controlador.agregar(OperadorC3D.ASIG_HEAP, caracter, "", "H");
        controlador.agregar(OperadorC3D.SUMA, "H", "1", "H");
        controlador.agregar(OperadorC3D.ASIG_HEAP, "-1", "", "H");
        controlador.agregar(OperadorC3D.SUMA, "H", "1", "H");
        return inicio;
    }

    @Override
    public String visitUnaryExpr(UnaryExpr node, Ambito context) {
        String operando = node.getOperand().accept(this, context);
        String temp = controlador.nuevoTemporal();
        switch (node.getOperator()) {
            case NEGATION -> controlador.agregar(OperadorC3D.RESTA, "0", operando, temp);
            case POSITIVE -> controlador.agregarAsignacion(temp, operando);
            case NOT -> {
                String lVerdadero = controlador.nuevaEtiqueta();
                String lFin = controlador.nuevaEtiqueta();
                controlador.agregar(OperadorC3D.IF_EQ, operando, "0", lVerdadero);
                controlador.agregarAsignacion(temp, "0");
                controlador.agregarGoto(lFin);
                controlador.agregarEtiqueta(lVerdadero);
                controlador.agregarAsignacion(temp, "1");
                controlador.agregarEtiqueta(lFin);
            }
            case INCREMENT -> {
                controlador.agregar(OperadorC3D.SUMA, operando, "1", temp);
                almacenarEnDestino(node.getOperand(), temp, context);
            }
            case DECREMENT -> {
                controlador.agregar(OperadorC3D.RESTA, operando, "1", temp);
                almacenarEnDestino(node.getOperand(), temp, context);
            }
        }
        return temp;
    }

    @Override
    public String visitTernaryExpr(TernaryExpr node, Ambito context) {
        String condicion = node.getCondition().accept(this, context);
        String temp = controlador.nuevoTemporal();
        String lFalso = controlador.nuevaEtiqueta();
        String lFin = controlador.nuevaEtiqueta();

        controlador.agregar(OperadorC3D.IF_FALSE, condicion, "", lFalso);
        String valorVerdadero = node.getThenExpr().accept(this, context);
        controlador.agregarAsignacion(temp, valorVerdadero);
        controlador.agregarGoto(lFin);

        controlador.agregarEtiqueta(lFalso);
        String valorFalso = node.getElseExpr().accept(this, context);
        controlador.agregarAsignacion(temp, valorFalso);

        controlador.agregarEtiqueta(lFin);
        return temp;
    }

    @Override
    public String visitLiteralExpr(LiteralExpr node, Ambito context) {
        Type tipo = node.getLiteralType();
        if (tipo == null) {
            return "0";
        }
        return switch (tipo.getCategory()) {
            case INT, FLOAT -> String.valueOf(node.getValue());
            case BOOLEAN -> Boolean.TRUE.equals(node.getValue()) ? "1" : "0";
            case CHAR -> String.valueOf((int) (Character) node.getValue());
            case STRING -> controlador.guardarCadenaEnHeap(String.valueOf(node.getValue()));
            default -> "0";
        };
    }

    @Override
    public String visitIdentifierExpr(IdentifierExpr node, Ambito context) {
        Simbolo simbolo = node.getSimbolo();
        if (simbolo == null) {
            return "0";
        }
        String tDir = direccionDeSimbolo(simbolo);
        String tValor = controlador.nuevoTemporal();
        controlador.agregar(simbolo.esMiembroDeObjeto() ? OperadorC3D.ACCESO_HEAP : OperadorC3D.ACCESO_STACK,
                tDir, "", tValor);
        return tValor;
    }

    @Override
    public String visitMemberAccessExpr(MemberAccessExpr node, Ambito context) {
        String dirObjeto = node.getTarget().accept(this, context);
        int indice = obtenerIndiceMiembro(node.getTarget().getEvaluatedType(), node.getMemberName());
        String tDir = controlador.nuevoTemporal();
        String tValor = controlador.nuevoTemporal();
        controlador.agregar(OperadorC3D.SUMA, dirObjeto, String.valueOf(indice), tDir);
        controlador.agregar(OperadorC3D.ACCESO_HEAP, tDir, "", tValor);
        return tValor;
    }

    @Override
    public String visitArrayAccessExpr(ArrayAccessExpr node, Ambito context) {
        String tDir = direccionElementoArreglo(node, context);
        String tValor = controlador.nuevoTemporal();
        controlador.agregar(OperadorC3D.ACCESO_HEAP, tDir, "", tValor);
        return tValor;
    }

    @Override
    public String visitCallExpr(CallExpr node, Ambito context) {
        Simbolo funcion = node.getSimboloFuncion();

        // super(args) CONSTRUCTOR DEL PADRE SOBRE EL MISMO THIS
        if (node.esLlamadaConstructorPadre()) {
            List<String> argumentos = evaluarArgumentos(node.getArguments(), context);
            if (funcion != null) {
                invocar(funcion.getEtiquetaC3D(), argumentos);
            } else if (claseEnGeneracion != null) {
                // super() hacia un padre sin constructores declarados
                construirPadre(claseEnGeneracion);
            }
            return "0";
        }

        // VERSION DEL PADRE, SIN DESPACHO DINAMICO Y CON EL MISMO THIS
        if (node.esLlamadaMetodoPadre()) {
            List<String> argumentos = evaluarArgumentos(node.getArguments(), context);
            return invocar(etiquetaDeLlamada(node, funcion), argumentos);
        }

        String etiqueta = etiquetaDeLlamada(node, funcion);
        boolean esMetodo = funcion != null && funcion.getRol() == RolSimbolo.METODO;

        String dirObjeto = node.getTarget() != null ? node.getTarget().accept(this, context) : null;
        List<String> argumentos = evaluarArgumentos(node.getArguments(), context);

        String tThisAnterior = null;
        if (dirObjeto != null) {
            tThisAnterior = controlador.nuevoTemporal();
            controlador.agregarAsignacion(tThisAnterior, "THIS");
            controlador.agregarAsignacion("THIS", dirObjeto);
        }

        String tRetorno = esMetodo
                ? despachar(funcion, node.getClaseEstatica(), "THIS", argumentos)
                : invocar(etiqueta, argumentos);

        if (tThisAnterior != null) {
            controlador.agregarAsignacion("THIS", tThisAnterior);
        }
        return tRetorno;
    }

    private String etiquetaDeLlamada(CallExpr node, Simbolo funcion) {
        if (funcion != null && funcion.getEtiquetaC3D() != null) {
            return funcion.getEtiquetaC3D();
        }
        return "func_" + node.getFunctionName();
    }

    private List<String> evaluarArgumentos(List<Expression> argumentos, Ambito context) {
        List<String> valores = new ArrayList<>();
        for (Expression argumento : argumentos) {
            valores.add(argumento.accept(this, context));
        }
        return valores;
    }

    private String invocar(String etiqueta, List<String> argumentos) {
        int marco = marcoActual();

        controlador.agregar(OperadorC3D.SUMA, "P", String.valueOf(marco), "P");

        for (int i = 0; i < argumentos.size(); i++) {
            String tPosicion = controlador.nuevoTemporal();
            controlador.agregar(OperadorC3D.SUMA, "P", String.valueOf(i + 1), tPosicion);
            controlador.agregar(OperadorC3D.ASIG_STACK, argumentos.get(i), "", tPosicion);
            controlador.agregar(OperadorC3D.PARAM, argumentos.get(i), "", tPosicion);
        }

        controlador.agregar(OperadorC3D.CALL, "", "", etiqueta);

        String tRetorno = controlador.nuevoTemporal();
        controlador.agregar(OperadorC3D.ACCESO_STACK, "P", "", tRetorno);
        controlador.agregar(OperadorC3D.RESTA, "P", String.valueOf(marco), "P");

        return tRetorno;
    }

    @Override
    // new Clase(args): argumentos -> reservar objeto -> constructor, que a su vez hace
    // super(...), pone los valores iniciales propios y ejecuta su cuerpo
    public String visitNewObjectExpr(NewObjectExpr node, Ambito context) {
        Simbolo clase = simboloDeClase(node.getClassName());
        if (clase == null) {
            return "0";
        }

        List<String> argumentos = evaluarArgumentos(node.getArguments(), context);
        String tObjeto = reservarObjeto(clase);

        String tThisAnterior = controlador.nuevoTemporal();
        controlador.agregarAsignacion(tThisAnterior, "THIS");
        controlador.agregarAsignacion("THIS", tObjeto);

        Simbolo constructor = node.getSimboloConstructor();
        if (constructor != null) {
            String etiquetaCtor = constructor.getEtiquetaC3D() != null
                    ? constructor.getEtiquetaC3D()
                    : "constructor_" + node.getClassName() + "_" + constructor.getParametros().size();
            invocar(etiquetaCtor, argumentos);
        } else {
            construirPorDefecto(clase);
        }

        controlador.agregarAsignacion("THIS", tThisAnterior);
        return tObjeto;
    }

    @Override
    public String visitNewArrayExpr(NewArrayExpr node, Ambito context) {
        if (node.getInitialValues().isEmpty() && node.isExplicitAllocation()) {
            List<String> dimensiones = new ArrayList<>();
            for (Expression dimension : node.getDimensionSizes()) {
                dimensiones.add(dimension.accept(this, context));
            }
            return reservarArreglo(dimensiones, node.getElementType(), new HashSet<>());
        }

        List<Integer> forma = NewArrayExpr.formaDeLiteral(node);
        if (forma == null) {
            forma = List.of(node.getInitialValues().size());
        }
        List<String> valores = new ArrayList<>();
        for (Expression valor : NewArrayExpr.hojasDeLiteral(node)) {
            valores.add(valor.accept(this, context));
        }

        String tInicio = controlador.nuevoTemporal();
        controlador.agregar(OperadorC3D.ASIG, "H", "", tInicio);

        for (Integer tamanio : forma) {
            controlador.agregar(OperadorC3D.ASIG_HEAP, String.valueOf(tamanio), "", "H");
            controlador.agregar(OperadorC3D.SUMA, "H", "1", "H");
        }

        for (String valor : valores) {
            controlador.agregar(OperadorC3D.ASIG_HEAP, valor, "", "H");
            controlador.agregar(OperadorC3D.SUMA, "H", "1", "H");
        }

        return tInicio;
    }

    @Override
    public String visitNewStructExpr(NewStructExpr node, Ambito context) {
        List<String> valores = new ArrayList<>();
        for (Expression valor : node.getValues()) {
            valores.add(valor.accept(this, context));
        }

        String tInicio = controlador.nuevoTemporal();
        controlador.agregar(OperadorC3D.ASIG, "H", "", tInicio);

        for (String valor : valores) {
            controlador.agregar(OperadorC3D.ASIG_HEAP, valor, "", "H");
            controlador.agregar(OperadorC3D.SUMA, "H", "1", "H");
        }

        return tInicio;
    }

    @Override
    public String visitReadExpr(ReadExpr node, Ambito context) {
        return llamarNativa(NativasC3D.Nativa.LEER_CADENA);
    }

    @Override
    public String visitThisExpr(ThisExpr node, Ambito context) {
        String tObjeto = controlador.nuevoTemporal();
        controlador.agregarAsignacion(tObjeto, "THIS");
        return tObjeto;
    }

    // USA EL MISMO OBJETO QUE THIS SOLO CAMBIA EL TIPO QUE SE VE
    @Override
    public String visitSuperExpr(SuperExpr node, Ambito context) {
        String tObjeto = controlador.nuevoTemporal();
        controlador.agregarAsignacion(tObjeto, "THIS");
        return tObjeto;
    }
}
