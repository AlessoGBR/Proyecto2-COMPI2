package Backend.Ast.statement;

import Backend.Ast.AstVisitor;
import Backend.Ast.Node;
import Backend.Ast.expression.Expression;

public class ForStmt extends Statement {
    private final Node initializer; // VarDeclStmt, AssignStmt, o Expr
    private final Expression condition;
    private final Node update; // AssignStmt, Expr, etc.
    private final Statement body;

    public ForStmt(Node initializer, Expression condition, Node update, Statement body, int line, int column) {
        super(line, column);
        this.initializer = initializer;
        this.condition = condition;
        this.update = update;
        this.body = body;
    }

    public Node getInitializer() {
        return initializer;
    }

    public Expression getCondition() {
        return condition;
    }

    public Node getUpdate() {
        return update;
    }

    public Statement getBody() {
        return body;
    }

    @Override
    public <R, C> R accept(AstVisitor<R, C> visitor, C context) {
        return visitor.visitForStmt(this, context);
    }
}

