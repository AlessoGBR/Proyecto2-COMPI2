package Backend.c3d;

public enum OperadorC3D {
    SUMA("+"),
    RESTA("-"),
    MULT("*"),
    DIV("/"),
    DIV_ENT("/"),
    MOD("%"),
    A_ENTERO("(int)"),
    ASIG("="),

    GOTO("goto"),
    IF_EQ("=="),
    IF_NEQ("!="),
    IF_LT("<"),
    IF_LE("<="),
    IF_GT(">"),
    IF_GE(">="),
    IF_FALSE("if_false"),

    CALL("call"),
    PARAM("param"),
    RETURN("return"),

    // entrada y salida basicas
    PRINT_INT("print_int"),
    PRINT_CHAR("print_char"),
    LEER_CARACTER("read_char"),
    SALIR("exit"),

    ACCESO_STACK("stack[]"),
    ASIG_STACK("stack[]="),
    ACCESO_HEAP("heap[]"),
    ASIG_HEAP("heap[]="),

    ETIQUETA("etiqueta"),
    COMENTARIO("//"),
    INICIO_FUNCION("inicio_func"),
    FIN_FUNCION("fin_func");

    private final String simbolo;

    OperadorC3D(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public boolean esSaltoCondicional() {
        return this == IF_EQ || this == IF_NEQ || this == IF_LT
                || this == IF_LE || this == IF_GT || this == IF_GE;
    }
}
