package Backend.Ast.expression;

import Backend.Ast.AstVisitor;

public class IdentifierExpr extends Expression {
    private final String name;

    public IdentifierExpr(String name, int line, int column) {
        super(line, column);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private Backend.simbolos.Simbolo simbolo;

    public Backend.simbolos.Simbolo getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(Backend.simbolos.Simbolo simbolo) {
        this.simbolo = simbolo;
    }

    @Override
    public <R, C> R accept(AstVisitor<R, C> visitor, C context) {
        return visitor.visitIdentifierExpr(this, context);
    }
}

