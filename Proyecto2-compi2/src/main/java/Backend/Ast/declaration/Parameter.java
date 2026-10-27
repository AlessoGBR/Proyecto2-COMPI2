package Backend.Ast.declaration;

import Backend.Ast.Node;
import Backend.Ast.AstVisitor;
import Backend.Ast.Type;

public class Parameter extends Node {
    private final String name;
    private final Type type;
    private final boolean isByReference;

    public Parameter(String name, Type type, boolean isByReference, int line, int column) {
        super(line, column);
        this.name = name;
        this.type = type;
        this.isByReference = isByReference;
    }

    public Parameter(String name, Type type, int line, int column) {
        this(name, type, false, line, column);
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public boolean isByReference() {
        return isByReference;
    }

    private Backend.simbolos.Simbolo simbolo;

    public Backend.simbolos.Simbolo getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(Backend.simbolos.Simbolo simbolo) {
        this.simbolo = simbolo;
    }

    @Override
    public <R, C> R accept(AstVisitor<R, C> visitor, C context) {
        return visitor.visitParameter(this, context);
    }
}

