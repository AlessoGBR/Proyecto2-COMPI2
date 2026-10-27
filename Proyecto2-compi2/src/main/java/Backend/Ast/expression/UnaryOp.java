package Backend.Ast.expression;

public enum UnaryOp {
    NEGATION("-"),
    POSITIVE("+"),
    NOT("!"),
    INCREMENT("++"),
    DECREMENT("--");

    private final String symbol;

    UnaryOp(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}

