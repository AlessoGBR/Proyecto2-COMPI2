package Backend.Ast.statement;

import Backend.Ast.AstVisitor;
import Backend.Ast.expression.Expression;

public class ReadStmt extends Statement {
    private final Expression target; // null si es lectura sin guardar (ej. "<<" o "leer()")

    public ReadStmt(Expression target, int line, int column) {
        super(line, column);
        this.target = target;
    }

    public Expression getTarget() {
        return target;
    }

    public boolean hasTarget() {
        return target != null;
    }

    @Override
    public <R, C> R accept(AstVisitor<R, C> visitor, C context) {
        return visitor.visitReadStmt(this, context);
    }
}

