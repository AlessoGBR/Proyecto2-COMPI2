package Backend.Ast.expression;

import Backend.Ast.Node;
import Backend.Ast.Type;

public abstract class Expression extends Node {
    private Type evaluatedType;

    public Expression(int line, int column) {
        super(line, column);
    }

    public Expression() {
        super();
    }

    public Type getEvaluatedType() {
        return evaluatedType;
    }

    public void setEvaluatedType(Type evaluatedType) {
        this.evaluatedType = evaluatedType;
    }
}

