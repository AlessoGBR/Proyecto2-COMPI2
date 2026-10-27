package Backend.Ast.statement;

import Backend.Ast.Node;
import Backend.Ast.AstVisitor;
import Backend.Ast.expression.Expression;
import java.util.List;

public class CaseBranch extends Node {
    private final Expression caseExpression;
    private final List<Statement> statements;
    private final boolean isDefault;

    public CaseBranch(Expression caseExpression, List<Statement> statements, boolean isDefault, int line, int column) {
        super(line, column);
        this.caseExpression = caseExpression;
        this.statements = statements != null ? statements : List.of();
        this.isDefault = isDefault;
    }

    public Expression getCaseExpression() {
        return caseExpression;
    }

    public List<Statement> getStatements() {
        return statements;
    }

    public boolean isDefault() {
        return isDefault;
    }

    @Override
    public <R, C> R accept(AstVisitor<R, C> visitor, C context) {
        return visitor.visitCaseBranch(this, context);
    }
}

