package Backend.Ast.statement;

import Backend.Ast.AstVisitor;
import Backend.Ast.expression.Expression;
import java.util.ArrayList;
import java.util.List;

public class IfStmt extends Statement {
    private final Expression condition;
    private final Statement thenBranch;
    private final List<ElseIfBranch> elseIfBranches;
    private final Statement elseBranch; // null si no tiene rama else/contrario

    public IfStmt(Expression condition, Statement thenBranch, List<ElseIfBranch> elseIfBranches, Statement elseBranch, int line, int column) {
        super(line, column);
        this.condition = condition;
        this.thenBranch = thenBranch;
        this.elseIfBranches = elseIfBranches != null ? elseIfBranches : new ArrayList<>();
        this.elseBranch = elseBranch;
    }

    public Expression getCondition() {
        return condition;
    }

    public Statement getThenBranch() {
        return thenBranch;
    }

    public List<ElseIfBranch> getElseIfBranches() {
        return elseIfBranches;
    }

    public Statement getElseBranch() {
        return elseBranch;
    }

    public boolean hasElseBranch() {
        return elseBranch != null;
    }

    @Override
    public <R, C> R accept(AstVisitor<R, C> visitor, C context) {
        return visitor.visitIfStmt(this, context);
    }
}

