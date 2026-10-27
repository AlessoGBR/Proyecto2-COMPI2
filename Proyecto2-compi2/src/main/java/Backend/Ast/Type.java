package Backend.Ast;

import java.util.Objects;

public class Type {

    public enum TypeCategory {
        INT,
        FLOAT,
        CHAR,
        BOOLEAN,
        STRING,
        VOID,
        STRUCT,
        CLASS,
        ARRAY,
        NULL,
        ERROR
    }

    private final TypeCategory category;
    private final String typeName;
    private final Type baseType; 
    private final int dimensions;
    public Type(TypeCategory category, String typeName, Type baseType, int dimensions) {
        this.category = category;
        this.typeName = typeName;
        this.baseType = baseType;
        this.dimensions = dimensions;
    }

    public Type(TypeCategory category, String typeName) {
        this(category, typeName, null, 0);
    }

    public Type(TypeCategory category) {
        this(category, category.name(), null, 0);
    }

    public static Type createArrayType(Type baseType, int dimensions) {
        return new Type(TypeCategory.ARRAY, baseType.getTypeName() + "[]".repeat(dimensions), baseType, dimensions);
    }

    public static Type crearArregloSinDimensiones(Type baseType) {
        return new Type(TypeCategory.ARRAY, baseType.getTypeName() + "[]", baseType, 0);
    }

    public static Type createCustomType(String name, boolean isClass) {
        return new Type(isClass ? TypeCategory.CLASS : TypeCategory.STRUCT, name, null, 0);
    }

    public static final Type INT = new Type(TypeCategory.INT, "int");
    public static final Type FLOAT = new Type(TypeCategory.FLOAT, "float");
    public static final Type CHAR = new Type(TypeCategory.CHAR, "char");
    public static final Type BOOLEAN = new Type(TypeCategory.BOOLEAN, "boolean");
    public static final Type STRING = new Type(TypeCategory.STRING, "String");
    public static final Type VOID = new Type(TypeCategory.VOID, "void");
    public static final Type NULL = new Type(TypeCategory.NULL, "null");
    public static final Type ERROR = new Type(TypeCategory.ERROR, "error");

    public TypeCategory getCategory() {
        return category;
    }

    public String getTypeName() {
        return typeName;
    }

    public Type getBaseType() {
        return baseType;
    }

    public int getDimensions() {
        return dimensions;
    }

    public boolean tieneDimensionesLibres() {
        return category == TypeCategory.ARRAY && dimensions == 0;
    }

    public boolean isNumeric() {
        return category == TypeCategory.INT || category == TypeCategory.FLOAT;
    }

    public boolean isPrimitive() {
        return category == TypeCategory.INT || category == TypeCategory.FLOAT ||
               category == TypeCategory.CHAR || category == TypeCategory.BOOLEAN ||
               category == TypeCategory.STRING;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Type type)) return false;
        if (esDefinidoPorUsuario() && type.esDefinidoPorUsuario()) {
            return Objects.equals(typeName, type.typeName);
        }
        if (category != type.category) return false;
        if (category == TypeCategory.ARRAY) {
            return dimensions == type.dimensions && Objects.equals(baseType, type.baseType);
        }
        if (category == TypeCategory.STRUCT || category == TypeCategory.CLASS) {
            return Objects.equals(typeName, type.typeName);
        }
        return true;
    }

    public boolean esDefinidoPorUsuario() {
        return category == TypeCategory.STRUCT || category == TypeCategory.CLASS;
    }

    @Override
    public int hashCode() {
        if (esDefinidoPorUsuario()) {
            return Objects.hash(TypeCategory.STRUCT, typeName);
        }
        return Objects.hash(category, typeName, baseType, dimensions);
    }

    @Override
    public String toString() {
        return typeName;
    }
}

