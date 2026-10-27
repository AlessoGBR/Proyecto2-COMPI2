package Backend.Ast.expression;

import Backend.Ast.AstVisitor;
import java.util.List;

public class NewObjectExpr extends Expression {
    private final String className;
    private final List<Expression> arguments;

    public NewObjectExpr(String className, List<Expression> arguments, int line, int column) {
        super(line, column);
        this.className = className;
        this.arguments = arguments != null ? arguments : List.of();
    }

    public String getClassName() {
        return className;
    }

    public List<Expression> getArguments() {
        return arguments;
    }

    private Backend.simbolos.Simbolo simboloConstructor;

    public Backend.simbolos.Simbolo getSimboloConstructor() {
        return simboloConstructor;
    }

    public void setSimboloConstructor(Backend.simbolos.Simbolo simboloConstructor) {
        this.simboloConstructor = simboloConstructor;
    }

    @Override
    public <R, C> R accept(AstVisitor<R, C> visitor, C context) {
        return visitor.visitNewObjectExpr(this, context);
    }
}

