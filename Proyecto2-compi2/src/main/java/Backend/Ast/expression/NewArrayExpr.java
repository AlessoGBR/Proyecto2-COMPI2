package Backend.Ast.expression;

import Backend.Ast.AstVisitor;
import Backend.Ast.Type;
import java.util.ArrayList;
import java.util.List;

public class NewArrayExpr extends Expression {
    private final Type elementType;
    private final List<Expression> dimensionSizes;
    private final List<Expression> initialValues;

    public NewArrayExpr(Type elementType, List<Expression> dimensionSizes, List<Expression> initialValues, int line, int column) {
        super(line, column);
        this.elementType = elementType;
        this.dimensionSizes = dimensionSizes != null ? dimensionSizes : List.of();
        this.initialValues = initialValues != null ? initialValues : List.of();
    }

    public Type getElementType() {
        return elementType;
    }

    public List<Expression> getDimensionSizes() {
        return dimensionSizes;
    }

    public List<Expression> getInitialValues() {
        return initialValues;
    }

    public boolean isExplicitAllocation() {
        return !dimensionSizes.isEmpty();
    }

    public static List<Integer> formaDeLiteral(NewArrayExpr literal) {
        List<Expression> valores = literal.getInitialValues();
        List<Integer> forma = new ArrayList<>();
        forma.add(valores.size());
        if (valores.isEmpty() || !(valores.get(0) instanceof NewArrayExpr primera) || primera.isExplicitAllocation()) {
            return forma;
        }
        List<Integer> formaFila = formaDeLiteral(primera);
        if (formaFila == null) {
            return null;
        }
        for (Expression fila : valores) {
            if (!(fila instanceof NewArrayExpr f) || f.isExplicitAllocation() || !formaFila.equals(formaDeLiteral(f))) {
                return null;
            }
        }
        forma.addAll(formaFila);
        return forma;
    }

    public static List<Expression> hojasDeLiteral(NewArrayExpr literal) {
        List<Expression> hojas = new ArrayList<>();
        for (Expression valor : literal.getInitialValues()) {
            if (valor instanceof NewArrayExpr fila && !fila.isExplicitAllocation()) {
                hojas.addAll(hojasDeLiteral(fila));
            } else {
                hojas.add(valor);
            }
        }
        return hojas;
    }

    @Override
    public <R, C> R accept(AstVisitor<R, C> visitor, C context) {
        return visitor.visitNewArrayExpr(this, context);
    }
}

