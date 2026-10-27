package Backend.Ast.declaration;

import Backend.Ast.AstVisitor;
import Backend.Ast.ModificadorAcceso;
import Backend.Ast.Type;
import Backend.Ast.statement.BlockStmt;
import java.util.List;

public class FunctionDecl extends Declaration {
    private final String name;
    private final Type returnType;
    private final List<Parameter> parameters;
    private final BlockStmt body;
    private final boolean isMethod;
    private final boolean isPublic;

    public FunctionDecl(String name, Type returnType, List<Parameter> parameters, BlockStmt body, boolean isMethod, boolean isPublic, int line, int column) {
        super(line, column);
        this.name = name;
        this.returnType = returnType != null ? returnType : Type.VOID;
        this.parameters = parameters != null ? parameters : List.of();
        this.body = body;
        this.isMethod = isMethod;
        this.isPublic = isPublic;
    }

    public FunctionDecl(String name, Type returnType, List<Parameter> parameters, BlockStmt body, int line, int column) {
        this(name, returnType, parameters, body, false, true, line, column);
    }

    public String getName() {
        return name;
    }

    public Type getReturnType() {
        return returnType;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public BlockStmt getBody() {
        return body;
    }

    public boolean isMethod() {
        return isMethod;
    }

    public boolean isPublic() {
        return modificador == ModificadorAcceso.PUBLICO;
    }

    private ModificadorAcceso modificador;
    private String anotacion;

    public ModificadorAcceso getModificador() {
        return modificador != null ? modificador
                : (isPublic ? ModificadorAcceso.PUBLICO : ModificadorAcceso.DEFECTO);
    }

    public void setModificador(ModificadorAcceso modificador) {
        this.modificador = modificador;
    }

    public String getAnotacion() {
        return anotacion;
    }

    public void setAnotacion(String anotacion) {
        this.anotacion = anotacion;
    }

    public boolean esOverride() {
        return "@Override".equals(anotacion);
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
        return visitor.visitFunctionDecl(this, context);
    }
}

