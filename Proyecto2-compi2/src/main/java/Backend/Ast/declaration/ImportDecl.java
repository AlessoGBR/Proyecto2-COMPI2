package Backend.Ast.declaration;

import Backend.Ast.AstVisitor;

public class ImportDecl extends Declaration {
    private final String importPath;

    public ImportDecl(String importPath, int line, int column) {
        super(line, column);
        this.importPath = importPath;
    }

    public String getImportPath() {
        return importPath;
    }

    public String getTargetExtension() {
        if (importPath.endsWith(".z")) return "z";
        if (importPath.endsWith(".y")) return "y";
        if (importPath.endsWith(".pig")) return "pig";
        return "";
    }

    @Override
    public <R, C> R accept(AstVisitor<R, C> visitor, C context) {
        return visitor.visitImportDecl(this, context);
    }
}

