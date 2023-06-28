package star.tratto.identifiers;

import com.github.javaparser.ast.type.Type;

public enum JPType {
    ARRAY_TYPE("ARRAY_TYPE"),
    CLASS_OR_INTERFACE_TYPE("CLASS_OR_INTERFACE_TYPE"),
    INTERSECTION_TYPE("INTERSECTION_TYPE"),
    PRIMITIVE_TYPE("PRIMITIVE_TYPE"),
    REFERENCE_TYPE("REFERENCE_TYPE"),
    TYPE_PARAMETER("TYPE_PARAMETER"),
    UNION_TYPE("UNION_TYPE"),
    UNKNOWN_TYPE("UNKNOWN_TYPE"),
    VAR_TYPE("VAR_TYPE"),
    VOID_TYPE("VOID_TYPE"),
    WILDCARD("WILDCARD");

    private final String type;

    private JPType(String type) {
        this.type = type;
    }

    public String getValue() {
        return this.type;
    }

    public static JPType getJPTypeDeclaration(Type jpType) {
        if (jpType.isArrayType()) {
            return JPType.ARRAY_TYPE;
        }
        if (jpType.isClassOrInterfaceType()) {
            return JPType.CLASS_OR_INTERFACE_TYPE;
        }
        if (jpType.isTypeParameter()) {
            return JPType.TYPE_PARAMETER;
        }
        if (jpType.isIntersectionType()) {
            return JPType.INTERSECTION_TYPE;
        }
        if (jpType.isPrimitiveType()) {
            return JPType.PRIMITIVE_TYPE;
        }
        if (jpType.isReferenceType()) {
            return JPType.REFERENCE_TYPE;
        }
        if (jpType.isUnionType()) {
            return JPType.UNION_TYPE;
        }
        if (jpType.isUnknownType()) {
            return JPType.UNKNOWN_TYPE;
        }
        if (jpType.isVarType()) {
            return JPType.VAR_TYPE;
        }
        if (jpType.isVoidType()) {
            return JPType.VOID_TYPE;
        }
        if (jpType.isWildcardType()) {
            return JPType.WILDCARD;
        }
        String errMsg = String.format("JavaParser parameter type not recognized: %s", jpType.asString());
        throw new IllegalArgumentException(errMsg);
    }
}
