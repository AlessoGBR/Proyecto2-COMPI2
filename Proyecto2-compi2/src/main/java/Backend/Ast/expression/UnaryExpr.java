package Backend.Ast.expression;

import Backend.Ast.AstVisitor;

public class UnaryExpr extends Expression {
    private final UnaryOp operator;
    private final Expression operand;
    private final boolean isPostfix;

    public UnaryExpr(UnaryOp operator, Expression operand, boolean isPostfix, int line, int column) {
        super(line, column);
        this.operator = operator;
        this.operand = operand;
        this.isPostfix = isPostfix;
    }

    public UnaryOp getOperator() {
        return operator;
    }

    public Expression getOperand() {
        return operand;
    }

    public boolean isPostfix() {
        return isPostfix;
    }

    @Override
    public <R, C> R accept(AstVisitor<R, C> visitor, C context) {
        return visitor.visitUnaryExpr(this, context);
    }
}

