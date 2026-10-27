package Backend.Ast.statement;

import Backend.Ast.AstVisitor;
import Backend.Ast.expression.Expression;

public class ExprStmt extends Statement {
    private final Expression expression;

    public ExprStmt(Expression expression, int line, int column) {
        super(line, column);
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public <R, C> R accept(AstVisitor<R, C> visitor, C context) {
        return visitor.visitExprStmt(this, context);
    }
}

