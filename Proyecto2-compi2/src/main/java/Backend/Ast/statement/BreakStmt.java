package Backend.Ast.statement;

import Backend.Ast.AstVisitor;

public class BreakStmt extends Statement {

    public BreakStmt(int line, int column) {
        super(line, column);
    }

    @Override
    public <R, C> R accept(AstVisitor<R, C> visitor, C context) {
        return visitor.visitBreakStmt(this, context);
    }
}

