package Backend.Ast.statement;

import Backend.Ast.AstVisitor;
import Backend.Ast.expression.Expression;

public class DoWhileStmt extends Statement {
    private final Statement body;
    private final Expression condition;

    public DoWhileStmt(Statement body, Expression condition, int line, int column) {
        super(line, column);
        this.body = body;
        this.condition = condition;
    }

    public Statement getBody() {
        return body;
    }

    public Expression getCondition() {
        return condition;
    }

    @Override
    public <R, C> R accept(AstVisitor<R, C> visitor, C context) {
        return visitor.visitDoWhileStmt(this, context);
    }
}

