package Backend.Ast.declaration;

import Backend.Ast.Node;
import Backend.Ast.AstVisitor;
import Backend.Ast.Type;
import Backend.Ast.expression.Expression;

import java.util.List;

public class StructField extends Node {
    private final String name;
    private final Type type;
    private final List<Expression> arraySizes;

    public StructField(String name, Type type, List<Expression> arraySizes, int line, int column) {
        super(line, column);
        this.name = name;
        this.type = type;
        this.arraySizes = arraySizes != null ? arraySizes : List.of();
    }

    public StructField(String name, Type type, int line, int column) {
        this(name, type, List.of(), line, column);
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public boolean isArray() {
        return !arraySizes.isEmpty();
    }

    public List<Expression> getArraySizes() {
        return arraySizes;
    }

    @Override
    public <R, C> R accept(AstVisitor<R, C> visitor, C context) {
        return visitor.visitStructField(this, context);
    }
}
