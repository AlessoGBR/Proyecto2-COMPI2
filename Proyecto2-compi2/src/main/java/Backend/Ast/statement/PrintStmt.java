package Backend.Ast.statement;

import Backend.Ast.AstVisitor;
import Backend.Ast.expression.Expression;
import java.util.List;

public class PrintStmt extends Statement {
    private final List<Expression> expressions;
    private final boolean addNewline;

    public PrintStmt(List<Expression> expressions, boolean addNewline, int line, int column) {
        super(line, column);
        this.expressions = expressions != null ? expressions : List.of();
        this.addNewline = addNewline;
    }

    public List<Expression> getExpressions() {
        return expressions;
    }

    public boolean isAddNewline() {
        return addNewline;
    }

    @Override
    public <R, C> R accept(AstVisitor<R, C> visitor, C context) {
        return visitor.visitPrintStmt(this, context);
    }
}

