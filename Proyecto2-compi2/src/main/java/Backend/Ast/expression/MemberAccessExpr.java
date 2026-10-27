package Backend.Ast.expression;

import Backend.Ast.AstVisitor;

public class MemberAccessExpr extends Expression {
    private final Expression target;
    private final String memberName;

    public MemberAccessExpr(Expression target, String memberName, int line, int column) {
        super(line, column);
        this.target = target;
        this.memberName = memberName;
    }

    public Expression getTarget() {
        return target;
    }

    public String getMemberName() {
        return memberName;
    }

    @Override
    public <R, C> R accept(AstVisitor<R, C> visitor, C context) {
        return visitor.visitMemberAccessExpr(this, context);
    }
}

