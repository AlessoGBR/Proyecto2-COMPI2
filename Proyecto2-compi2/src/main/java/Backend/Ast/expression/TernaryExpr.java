package Backend.Ast.expression;

import Backend.Ast.AstVisitor;

public class TernaryExpr extends Expression {
    private final Expression condition;
    private final Expression thenExpr;
    private final Expression elseExpr;

    public TernaryExpr(Expression condition, Expression thenExpr, Expression elseExpr, int line, int column) {
        super(line, column);
        this.condition = condition;
        this.thenExpr = thenExpr;
        this.elseExpr = elseExpr;
    }

    public Expression getCondition() {
        return condition;
    }

    public Expression getThenExpr() {
        return thenExpr;
    }

    public Expression getElseExpr() {
        return elseExpr;
    }

    @Override
    public <R, C> R accept(AstVisitor<R, C> visitor, C context) {
        return visitor.visitTernaryExpr(this, context);
    }
}

