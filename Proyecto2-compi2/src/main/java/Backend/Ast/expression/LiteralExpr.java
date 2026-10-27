package Backend.Ast.expression;

import Backend.Ast.AstVisitor;
import Backend.Ast.Type;

public class LiteralExpr extends Expression {
    private final Object value;
    private final Type literalType;

    public LiteralExpr(Object value, Type literalType, int line, int column) {
        super(line, column);
        this.value = value;
        this.literalType = literalType;
        setEvaluatedType(literalType);
    }

    public Object getValue() {
        return value;
    }

    public Type getLiteralType() {
        return literalType;
    }

    @Override
    public <R, C> R accept(AstVisitor<R, C> visitor, C context) {
        return visitor.visitLiteralExpr(this, context);
    }
}

