package Backend.Ast.statement;

import Backend.Ast.AstVisitor;

public class ContinueStmt extends Statement {

    public ContinueStmt(int line, int column) {
        super(line, column);
    }

    @Override
    public <R, C> R accept(AstVisitor<R, C> visitor, C context) {
        return visitor.visitContinueStmt(this, context);
    }
}

