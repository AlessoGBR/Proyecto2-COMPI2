package Backend.Ast.declaration;

import Backend.Ast.AstVisitor;
import java.util.List;

public class StructDecl extends Declaration {
    private final String name;
    private final List<StructField> fields;

    public StructDecl(String name, List<StructField> fields, int line, int column) {
        super(line, column);
        this.name = name;
        this.fields = fields != null ? fields : List.of();
    }

    public String getName() {
        return name;
    }

    public List<StructField> getFields() {
        return fields;
    }

    @Override
    public <R, C> R accept(AstVisitor<R, C> visitor, C context) {
        return visitor.visitStructDecl(this, context);
    }
}

