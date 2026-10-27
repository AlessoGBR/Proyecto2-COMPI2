package Backend.Ast.expression;

public enum BinaryOp {
    // ARITMETICOS
    ADD("+"),
    SUB("-"),
    MUL("*"),
    DIV("/"),
    MOD("%"),

    // RELACIONALES
    EQUALS("=="),
    NOT_EQUALS("!="),
    LESS_THAN("<"),
    LESS_EQUAL("<="),
    GREATER_THAN(">"),
    GREATER_EQUAL(">="),

    // LOGICOS
    AND("&&"),
    OR("||");

    private final String symbol;

    BinaryOp(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}

