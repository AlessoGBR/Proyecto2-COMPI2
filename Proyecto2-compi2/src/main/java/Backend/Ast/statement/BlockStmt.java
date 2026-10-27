package Backend.Ast.statement;

import Backend.Ast.AstVisitor;
import java.util.ArrayList;
import java.util.List;

public class BlockStmt extends Statement {
    private final List<Statement> statements;

    public BlockStmt(List<Statement> statements, int line, int column) {
        super(line, column);
        this.statements = statements != null ? statements : new ArrayList<>();
    }

    public BlockStmt(int line, int column) {
        this(new ArrayList<>(), line, column);
    }

    public List<Statement> getStatements() {
        return statements;
    }

    public void addStatement(Statement statement) {
        if (statement != null) {
            statements.add(statement);
        }
    }

    @Override
    public <R, C> R accept(AstVisitor<R, C> visitor, C context) {
        return visitor.visitBlockStmt(this, context);
    }
}

