package Backend.Ast.expression;

import Backend.Ast.AstVisitor;

import java.util.List;

public class ArrayAccessExpr extends Expression {
    private final Expression target;
    private final Expression index;

    public ArrayAccessExpr(Expression target, Expression index, int line, int column) {
        super(line, column);
        this.target = target;
        this.index = index;
    }

    public Expression getTarget() {
        return target;
    }

    public Expression getIndex() {
        return index;
    }

    public static Expression recolectarIndices(ArrayAccessExpr acceso, List<Expression> indices) {
        Expression actual = acceso;
        while (actual instanceof ArrayAccessExpr a) {
            indices.add(0, a.getIndex());
            actual = a.getTarget();
        }
        return actual;
    }

    @Override
    public <R, C> R accept(AstVisitor<R, C> visitor, C context) {
        return visitor.visitArrayAccessExpr(this, context);
    }
}

