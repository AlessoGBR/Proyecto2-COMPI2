package Backend.Ast.expression;

import Backend.Ast.AstVisitor;

public class SuperExpr extends Expression {

    public SuperExpr(int line, int column) {
        super(line, column);
    }

    @Override
    public <R, C> R accept(AstVisitor<R, C> visitor, C context) {
        return visitor.visitSuperExpr(this, context);
    }
}
