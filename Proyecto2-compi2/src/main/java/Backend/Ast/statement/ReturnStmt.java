package Backend.Ast.statement;

import Backend.Ast.AstVisitor;
import Backend.Ast.expression.Expression;

public class ReturnStmt extends Statement {
    private final Expression value; 
    public ReturnStmt(Expression value, int line, int column) {
        super(line, column);
        this.value = value;
    }

    public Expression getValue() {
        return value;
    }

    public boolean hasValue() {
        return value != null;
    }

    @Override
    public <R, C> R accept(AstVisitor<R, C> visitor, C context) {
        return visitor.visitReturnStmt(this, context);
    }
}

