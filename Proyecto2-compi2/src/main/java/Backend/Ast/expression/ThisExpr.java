package Backend.Ast.expression;

import Backend.Ast.AstVisitor;

public class ThisExpr extends Expression {

    public ThisExpr(int line, int column) {
        super(line, column);
    }

    @Override
    public <R, C> R accept(AstVisitor<R, C> visitor, C context) {
        return visitor.visitThisExpr(this, context);
    }
}
