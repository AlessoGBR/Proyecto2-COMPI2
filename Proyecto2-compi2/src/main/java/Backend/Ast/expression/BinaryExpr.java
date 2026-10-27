package Backend.Ast.expression;

import Backend.Ast.AstVisitor;

public class BinaryExpr extends Expression {
    private final Expression left;
    private final BinaryOp operator;
    private final Expression right;

    public BinaryExpr(Expression left, BinaryOp operator, Expression right, int line, int column) {
        super(line, column);
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    public Expression getLeft() {
        return left;
    }

    public BinaryOp getOperator() {
        return operator;
    }

    public Expression getRight() {
        return right;
    }

    @Override
    public <R, C> R accept(AstVisitor<R, C> visitor, C context) {
        return visitor.visitBinaryExpr(this, context);
    }
}

