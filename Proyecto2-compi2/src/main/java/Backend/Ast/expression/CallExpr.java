package Backend.Ast.expression;

import Backend.Ast.AstVisitor;
import java.util.List;

public class CallExpr extends Expression {
    // nombre que usa el AST para super(...), no choca con metodos porque 'super' es palabra reservada
    public static final String NOMBRE_CONSTRUCTOR_PADRE = "super";

    private final Expression target;
    private final String functionName;
    private final List<Expression> arguments;

    public CallExpr(Expression target, String functionName, List<Expression> arguments, int line, int column) {
        super(line, column);
        this.target = target;
        this.functionName = functionName;
        this.arguments = arguments != null ? arguments : List.of();
    }

    public Expression getTarget() {
        return target;
    }

    public String getFunctionName() {
        return functionName;
    }

    public List<Expression> getArguments() {
        return arguments;
    }

    // super(args)
    public boolean esLlamadaConstructorPadre() {
        return target instanceof SuperExpr && NOMBRE_CONSTRUCTOR_PADRE.equals(functionName);
    }

    // super.metodo(args): llama a la version del padre sin despacho dinamico
    public boolean esLlamadaMetodoPadre() {
        return target instanceof SuperExpr && !NOMBRE_CONSTRUCTOR_PADRE.equals(functionName);
    }

    private Backend.simbolos.Simbolo simboloFuncion;

    public Backend.simbolos.Simbolo getSimboloFuncion() {
        return simboloFuncion;
    }

    public void setSimboloFuncion(Backend.simbolos.Simbolo simboloFuncion) {
        this.simboloFuncion = simboloFuncion;
    }

    // Clase con la que se tipo el receptor (tipo estatico). El despacho dinamico
    // solo considera esa clase y sus descendientes.
    private String claseEstatica;

    public String getClaseEstatica() {
        return claseEstatica;
    }

    public void setClaseEstatica(String claseEstatica) {
        this.claseEstatica = claseEstatica;
    }

    @Override
    public <R, C> R accept(AstVisitor<R, C> visitor, C context) {
        return visitor.visitCallExpr(this, context);
    }
}
