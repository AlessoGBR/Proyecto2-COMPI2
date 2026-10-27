package Backend.Ast.statement;

import Backend.Ast.AstVisitor;
import Backend.Ast.expression.Expression;

public class WhileStmt extends Statement {
    private final Expression condition;
    private final Statement body;

    public WhileStmt(Expression condition, Statement body, int line, int column) {
        super(line, column);
        this.condition = condition;
        this.body = body;
    }

    public Expression getCondition() {
        return condition;
    }

    public Statement getBody() {
        return body;
    }

    @Override
    public <R, C> R accept(AstVisitor<R, C> visitor, C context) {
        return visitor.visitWhileStmt(this, context);
    }
}

