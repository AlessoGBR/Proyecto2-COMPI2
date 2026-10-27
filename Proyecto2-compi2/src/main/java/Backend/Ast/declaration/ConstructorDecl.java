package Backend.Ast.declaration;

import Backend.Ast.AstVisitor;
import Backend.Ast.ModificadorAcceso;
import Backend.Ast.expression.CallExpr;
import Backend.Ast.statement.BlockStmt;
import Backend.Ast.statement.ExprStmt;
import Backend.Ast.statement.Statement;
import java.util.List;

public class ConstructorDecl extends Declaration {
    private final String className;
    private final List<Parameter> parameters;
    private final BlockStmt body;
    private final boolean isPublic;
    private ModificadorAcceso modificador;

    public ConstructorDecl(String className, List<Parameter> parameters, BlockStmt body, boolean isPublic, int line, int column) {
        super(line, column);
        this.className = className;
        this.parameters = parameters != null ? parameters : List.of();
        this.body = body;
        this.isPublic = isPublic;
    }

    public String getClassName() {
        return className;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public BlockStmt getBody() {
        return body;
    }

    public boolean isPublic() {
        return getModificador() == ModificadorAcceso.PUBLICO;
    }

    public ModificadorAcceso getModificador() {
        return modificador != null ? modificador
                : (isPublic ? ModificadorAcceso.PUBLICO : ModificadorAcceso.DEFECTO);
    }

    public void setModificador(ModificadorAcceso modificador) {
        this.modificador = modificador;
    }

    // super(...) solo es valido como primera instruccion; si no esta, se llama
    // implicitamente al constructor sin parametros de la clase padre
    public boolean tieneLlamadaSuperExplicita() {
        if (body == null || body.getStatements().isEmpty()) {
            return false;
        }
        return esLlamadaSuper(body.getStatements().get(0));
    }

    public static boolean esLlamadaSuper(Statement instruccion) {
        return instruccion instanceof ExprStmt es
                && es.getExpression() instanceof CallExpr llamada
                && llamada.esLlamadaConstructorPadre();
    }

    private int tamanioMarco = 1;

    public int getTamanioMarco() {
        return tamanioMarco;
    }

    public void setTamanioMarco(int tamanioMarco) {
        this.tamanioMarco = tamanioMarco;
    }

    private String etiquetaC3D;

    public String getEtiquetaC3D() {
        return etiquetaC3D;
    }

    public void setEtiquetaC3D(String etiquetaC3D) {
        this.etiquetaC3D = etiquetaC3D;
    }

    @Override
    public <R, C> R accept(AstVisitor<R, C> visitor, C context) {
        return visitor.visitConstructorDecl(this, context);
    }
}
