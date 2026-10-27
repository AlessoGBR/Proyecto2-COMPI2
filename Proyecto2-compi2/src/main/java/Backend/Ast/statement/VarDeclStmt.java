package Backend.Ast.statement;

import Backend.Ast.AstVisitor;
import Backend.Ast.Type;
import Backend.Ast.expression.Expression;
import java.util.ArrayList;
import java.util.List;

public class VarDeclStmt extends Statement {
    private final Type type;
    private final String name;
    private final Expression initialValue;
    private final List<Expression> arrayDimensions; 
    private final boolean isArray;

    public VarDeclStmt(Type type, String name, Expression initialValue, List<Expression> arrayDimensions, boolean isArray, int line, int column) {
        super(line, column);
        this.type = type;
        this.name = name;
        this.initialValue = initialValue;
        this.arrayDimensions = arrayDimensions != null ? arrayDimensions : new ArrayList<>();
        this.isArray = isArray;
    }

    public VarDeclStmt(Type type, String name, Expression initialValue, int line, int column) {
        this(type, name, initialValue, null, false, line, column);
    }

    public Type getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Expression getInitialValue() {
        return initialValue;
    }

    public List<Expression> getArrayDimensions() {
        return arrayDimensions;
    }

    public boolean isArray() {
        return isArray;
    }

    // Solo aplica a atributos de clase; las variables locales y globales lo ignoran
    private Backend.Ast.ModificadorAcceso modificador = Backend.Ast.ModificadorAcceso.DEFECTO;

    public Backend.Ast.ModificadorAcceso getModificador() {
        return modificador;
    }

    public void setModificador(Backend.Ast.ModificadorAcceso modificador) {
        this.modificador = modificador != null ? modificador : Backend.Ast.ModificadorAcceso.DEFECTO;
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
        return visitor.visitVarDeclStmt(this, context);
    }
}

