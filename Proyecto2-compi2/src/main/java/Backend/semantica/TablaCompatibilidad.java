package Backend.semantica;

import Backend.Ast.Type;
import Backend.Ast.expression.BinaryOp;
import Backend.Ast.expression.UnaryOp;
import Backend.Ast.statement.AssignOp;

public class TablaCompatibilidad {

    @FunctionalInterface
    public interface RelacionSubtipo {
        boolean esSubtipo(String hija, String ancestro);
    }

    private static final RelacionSubtipo SIN_HERENCIA = (hija, ancestro) -> false;

    private static boolean sonClasesRelacionadas(Type a, Type b, RelacionSubtipo relacion) {
        String nombreA = a.getTypeName();
        String nombreB = b.getTypeName();
        return nombreA.equals(nombreB)
                || relacion.esSubtipo(nombreA, nombreB)
                || relacion.esSubtipo(nombreB, nombreA);
    }

    public static Type obtenerTipoBinario(Type izquierda, BinaryOp operador, Type derecha) {
        return obtenerTipoBinario(izquierda, operador, derecha, SIN_HERENCIA);
    }

    public static Type obtenerTipoBinario(Type izquierda, BinaryOp operador, Type derecha, RelacionSubtipo relacion) {
        if (izquierda == null || derecha == null) {
            return Type.ERROR;
        }

        Type.TypeCategory catIzq = izquierda.getCategory();
        Type.TypeCategory catDer = derecha.getCategory();

        switch (operador) {
            case ADD:
                if (catIzq == Type.TypeCategory.STRING || catDer == Type.TypeCategory.STRING) {
                    if (catIzq != Type.TypeCategory.VOID && catDer != Type.TypeCategory.VOID &&
                            catIzq != Type.TypeCategory.ERROR && catDer != Type.TypeCategory.ERROR) {
                        return Type.STRING;
                    }
                    return Type.ERROR;
                }
                if (catIzq == Type.TypeCategory.INT && catDer == Type.TypeCategory.INT) return Type.INT;
                if (catIzq == Type.TypeCategory.INT && catDer == Type.TypeCategory.FLOAT) return Type.FLOAT;
                if (catIzq == Type.TypeCategory.FLOAT && catDer == Type.TypeCategory.INT) return Type.FLOAT;
                if (catIzq == Type.TypeCategory.FLOAT && catDer == Type.TypeCategory.FLOAT) return Type.FLOAT;
                if (catIzq == Type.TypeCategory.CHAR && catDer == Type.TypeCategory.INT) return Type.INT;
                if (catIzq == Type.TypeCategory.INT && catDer == Type.TypeCategory.CHAR) return Type.INT;
                return Type.ERROR;

            case SUB:
            case MUL:
                if (catIzq == Type.TypeCategory.INT && catDer == Type.TypeCategory.INT) return Type.INT;
                if (catIzq == Type.TypeCategory.INT && catDer == Type.TypeCategory.FLOAT) return Type.FLOAT;
                if (catIzq == Type.TypeCategory.FLOAT && catDer == Type.TypeCategory.INT) return Type.FLOAT;
                if (catIzq == Type.TypeCategory.FLOAT && catDer == Type.TypeCategory.FLOAT) return Type.FLOAT;
                return Type.ERROR;

            case DIV:
                if ((catIzq == Type.TypeCategory.INT || catIzq == Type.TypeCategory.FLOAT) &&
                        (catDer == Type.TypeCategory.INT || catDer == Type.TypeCategory.FLOAT)) {
                    if (catIzq == Type.TypeCategory.INT && catDer == Type.TypeCategory.INT) {
                        return Type.INT;
                    }
                    return Type.FLOAT;
                }
                return Type.ERROR;

            case MOD:
                if (catIzq == Type.TypeCategory.INT && catDer == Type.TypeCategory.INT) {
                    return Type.INT;
                }
                return Type.ERROR;

            case LESS_THAN:
            case LESS_EQUAL:
            case GREATER_THAN:
            case GREATER_EQUAL:
                if ((catIzq == Type.TypeCategory.INT || catIzq == Type.TypeCategory.FLOAT) &&
                        (catDer == Type.TypeCategory.INT || catDer == Type.TypeCategory.FLOAT)) {
                    return Type.BOOLEAN;
                }
                if (catIzq == Type.TypeCategory.CHAR && catDer == Type.TypeCategory.CHAR) {
                    return Type.BOOLEAN;
                }
                return Type.ERROR;

            case EQUALS:
            case NOT_EQUALS:
                if (izquierda.esDefinidoPorUsuario() && derecha.esDefinidoPorUsuario()) {
                    return sonClasesRelacionadas(izquierda, derecha, relacion) ? Type.BOOLEAN : Type.ERROR;
                }
                if (catIzq == catDer) {
                    return Type.BOOLEAN;
                }
                if ((catIzq == Type.TypeCategory.INT || catIzq == Type.TypeCategory.FLOAT) &&
                        (catDer == Type.TypeCategory.INT || catDer == Type.TypeCategory.FLOAT)) {
                    return Type.BOOLEAN;
                }
                if ((catIzq == Type.TypeCategory.CLASS || catIzq == Type.TypeCategory.STRUCT || catIzq == Type.TypeCategory.ARRAY) &&
                        catDer == Type.TypeCategory.NULL) {
                    return Type.BOOLEAN;
                }
                if (catIzq == Type.TypeCategory.NULL &&
                        (catDer == Type.TypeCategory.CLASS || catDer == Type.TypeCategory.STRUCT || catDer == Type.TypeCategory.ARRAY)) {
                    return Type.BOOLEAN;
                }
                return Type.ERROR;

            case AND:
            case OR:
                if (catIzq == Type.TypeCategory.BOOLEAN && catDer == Type.TypeCategory.BOOLEAN) {
                    return Type.BOOLEAN;
                }
                return Type.ERROR;

            default:
                return Type.ERROR;
        }
    }

    public static Type obtenerTipoUnario(UnaryOp operador, Type operando) {
        if (operando == null) return Type.ERROR;
        Type.TypeCategory cat = operando.getCategory();

        switch (operador) {
            case POSITIVE:
            case NEGATION:
                if (cat == Type.TypeCategory.INT || cat == Type.TypeCategory.FLOAT) {
                    return operando;
                }
                return Type.ERROR;

            case NOT:
                if (cat == Type.TypeCategory.BOOLEAN) {
                    return Type.BOOLEAN;
                }
                return Type.ERROR;

            case INCREMENT:
            case DECREMENT:
                if (cat == Type.TypeCategory.INT || cat == Type.TypeCategory.FLOAT) {
                    return operando;
                }
                return Type.ERROR;

            default:
                return Type.ERROR;
        }
    }


    public static boolean esAsignable(Type destino, Type origen) {
        return esAsignable(destino, origen, SIN_HERENCIA);
    }

    public static boolean esAsignable(Type destino, Type origen, RelacionSubtipo relacion) {
        if (destino == null || origen == null) return false;
        if (destino.getCategory() == Type.TypeCategory.ERROR || origen.getCategory() == Type.TypeCategory.ERROR) {
            return false;
        }

        if (destino.equals(origen)) {
            return true;
        }

        if (destino.getCategory() == Type.TypeCategory.FLOAT && origen.getCategory() == Type.TypeCategory.INT) {
            return true;
        }

        if (origen.getCategory() == Type.TypeCategory.CHAR
                && (destino.getCategory() == Type.TypeCategory.INT
                || destino.getCategory() == Type.TypeCategory.FLOAT)) {
            return true;
        }

        if (origen.getCategory() == Type.TypeCategory.NULL) {
            return destino.getCategory() == Type.TypeCategory.CLASS ||
                    destino.getCategory() == Type.TypeCategory.STRUCT ||
                    destino.getCategory() == Type.TypeCategory.ARRAY;
        }

        if (destino.getCategory() == Type.TypeCategory.ARRAY && origen.getCategory() == Type.TypeCategory.ARRAY) {
            boolean mismasDimensiones = destino.getDimensions() == origen.getDimensions()
                    || destino.tieneDimensionesLibres() || origen.tieneDimensionesLibres();
            return mismasDimensiones &&
                    (destino.getBaseType() == null || esAsignable(destino.getBaseType(), origen.getBaseType(), relacion));
        }

        if (destino.esDefinidoPorUsuario() && origen.esDefinidoPorUsuario()) {
            return destino.getTypeName().equals(origen.getTypeName())
                    || relacion.esSubtipo(origen.getTypeName(), destino.getTypeName());
        }

        return false;
    }

    public static boolean esAsignableCompuesta(Type destino, AssignOp op, Type valor) {
        return esAsignableCompuesta(destino, op, valor, SIN_HERENCIA);
    }

    public static boolean esAsignableCompuesta(Type destino, AssignOp op, Type valor, RelacionSubtipo relacion) {
        BinaryOp binOp = switch (op) {
            case ADD_ASSIGN -> BinaryOp.ADD;
            case SUB_ASSIGN -> BinaryOp.SUB;
            case MUL_ASSIGN -> BinaryOp.MUL;
            case DIV_ASSIGN -> BinaryOp.DIV;
            case MOD_ASSIGN -> BinaryOp.MOD;
            default -> null;
        };
        if (binOp == null) {
            return esAsignable(destino, valor, relacion);
        }
        Type tipoResultado = obtenerTipoBinario(destino, binOp, valor, relacion);
        return esAsignable(destino, tipoResultado, relacion);
    }
}
