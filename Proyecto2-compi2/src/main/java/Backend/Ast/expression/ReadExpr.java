package Backend.Ast.expression;

import Backend.Ast.AstVisitor;

public class ReadExpr extends Expression {

    public ReadExpr(int line, int column) {
        super(line, column);
    }

    @Override
    public <R, C> R accept(AstVisitor<R, C> visitor, C context) {
        return visitor.visitReadExpr(this, context);
    }
}

