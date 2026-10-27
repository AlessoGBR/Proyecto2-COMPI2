package Backend.Ast;

public abstract class Node {
    private int line;
    private int column;

    public Node(int line, int column) {
        this.line = line;
        this.column = column;
    }

    public Node() {
        this(0, 0);
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public abstract <R, C> R accept(AstVisitor<R, C> visitor, C context);
}

