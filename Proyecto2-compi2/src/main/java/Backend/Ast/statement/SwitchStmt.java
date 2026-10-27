package Backend.Ast.statement;

import Backend.Ast.AstVisitor;
import Backend.Ast.expression.Expression;
import java.util.List;

public class SwitchStmt extends Statement {
    private final Expression expression;
    private final List<CaseBranch> cases;

    public SwitchStmt(Expression expression, List<CaseBranch> cases, int line, int column) {
        super(line, column);
        this.expression = expression;
        this.cases = cases != null ? cases : List.of();
    }

    public Expression getExpression() {
        return expression;
    }

    public List<CaseBranch> getCases() {
        return cases;
    }

    @Override
    public <R, C> R accept(AstVisitor<R, C> visitor, C context) {
        return visitor.visitSwitchStmt(this, context);
    }
}

