package Backend.Ast.statement;

import Backend.Ast.AstVisitor;
import Backend.Ast.expression.Expression;

public class AssignStmt extends Statement {
    private final Expression target;
    private final AssignOp operator;
    private Expression value;

    public AssignStmt(Expression target, AssignOp operator, Expression value, int line, int column) {
        super(line, column);
        this.target = target;
        this.operator = operator;
        this.value = value;
    }

    public AssignStmt(Expression target, Expression value, int line, int column) {
        this(target, AssignOp.ASSIGN, value, line, column);
    }

    public Expression getTarget() {
        return target;
    }

    public AssignOp getOperator() {
        return operator;
    }

    public Expression getValue() {
        return value;
    }

    public void setValue(Expression value) {
        this.value = value;
    }

    @Override
    public <R, C> R accept(AstVisitor<R, C> visitor, C context) {
        return visitor.visitAssignStmt(this, context);
    }
}

