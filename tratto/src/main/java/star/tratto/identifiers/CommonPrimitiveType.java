package star.tratto.identifiers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * JDoctor and JavaParser store different representations of the same
 * primitive types. This class acts as a wrapper to store and convert between
 * representations.
 */
public enum CommonPrimitiveType {
    // JDoctor representations.
    CONDITION_BOOLEAN("Z"),
    CONDITION_BYTE("B"),
    CONDITION_CHAR("C"),
    CONDITION_DOUBLE("D"),
    CONDITION_FLOAT("F"),
    CONDITION_INTEGER("I"),
    CONDITION_LONG("J"),
    CONDITION_SHORT("S"),
    // JavaParser representations.
    JP_BOOLEAN("boolean"),
    JP_BYTE("byte"),
    JP_CHAR("char"),
    JP_DOUBLE("double"),
    JP_FLOAT("float"),
    JP_INTEGER("int"),
    JP_LONG("long"),
    JP_SHORT("short");

    private final String type;
    private static final List<String> allJDoctorPrimitiveTypes = collectAllJDoctorPrimitiveTypeNames();
    private static final List<String> allJPPrimitiveTypes = collectAllJPPrimitiveTypeNames();

    CommonPrimitiveType(String name) {
        this.type = name;
    }

    /**
     * Converts a String name of a primitive type to a ConditionPrimitiveType
     * enum. Given name may use either JavaParser or JDoctor format.
     *
     * @param parameter a name of a parameter (e.g. "Z", "boolean", "D", "double")
     * @return the ConditionPrimitiveType whose value corresponds to the given
     * parameter name
     */
    public static CommonPrimitiveType convertTypeNameToCommonPrimitiveType(String parameter) {
        switch (parameter) {
            case "Z" -> {
                return CONDITION_BOOLEAN;
            }
            case "B" -> {
                return CONDITION_BYTE;
            }
            case "C" -> {
                return CONDITION_CHAR;
            }
            case "D" -> {
                return CONDITION_DOUBLE;
            }
            case "F" -> {
                return CONDITION_FLOAT;
            }
            case "I" -> {
                return CONDITION_INTEGER;
            }
            case "J" -> {
                return CONDITION_LONG;
            }
            case "S" -> {
                return CONDITION_SHORT;
            }
            case "boolean" -> {
                return JP_BOOLEAN;
            }
            case "byte" -> {
                return JP_BYTE;
            }
            case "char" -> {
                return JP_CHAR;
            }
            case "double" -> {
                return JP_DOUBLE;
            }
            case "float" -> {
                return JP_FLOAT;
            }
            case "integer", "int" -> {
                return JP_INTEGER;
            }
            case "long" -> {
                return JP_LONG;
            }
            case "short" -> {
                return JP_SHORT;
            }
        }
        String errMsg = String.format("Unknown primitive parameter: %s", parameter);
        throw new IllegalArgumentException(errMsg);
    }

    /**
     * Converts a JDoctor primitive type to a JavaParser primitive type.
     *
     * @param conditionType the JDoctor primitive type
     * @return the corresponding JavaParser primitive type
     */
    public static CommonPrimitiveType jDoctorToJP(CommonPrimitiveType conditionType) {
        switch (conditionType) {
            case CONDITION_BOOLEAN -> {
                return JP_BOOLEAN;
            }
            case CONDITION_BYTE -> {
                return JP_BYTE;
            }
            case CONDITION_CHAR -> {
                return JP_CHAR;
            }
            case CONDITION_DOUBLE -> {
                return JP_DOUBLE;
            }
            case CONDITION_FLOAT -> {
                return JP_FLOAT;
            }
            case CONDITION_INTEGER -> {
                return JP_INTEGER;
            }
            case CONDITION_LONG -> {
                return JP_LONG;
            }
            case CONDITION_SHORT -> {
                return JP_SHORT;
            }
        }
        String errMsg = String.format("Unknown primitive type: %s", conditionType.getTypeName());
        throw new IllegalArgumentException(errMsg);
    }

    /**
     * Converts a JavaParser primitive type to a JDoctor primitive type.
     *
     * @param conditionType the JavaParser primitive type
     * @return the corresponding JDoctor primitive type
     */
    public static CommonPrimitiveType jpToJDoctor(CommonPrimitiveType conditionType) {
        switch (conditionType) {
            case JP_BOOLEAN -> {
                return CONDITION_BOOLEAN;
            }
            case JP_BYTE -> {
                return CONDITION_BYTE;
            }
            case JP_CHAR -> {
                return CONDITION_CHAR;
            }
            case JP_DOUBLE -> {
                return CONDITION_DOUBLE;
            }
            case JP_FLOAT -> {
                return CONDITION_FLOAT;
            }
            case JP_INTEGER -> {
                return CONDITION_INTEGER;
            }
            case JP_LONG -> {
                return CONDITION_LONG;
            }
            case JP_SHORT -> {
                return CONDITION_SHORT;
            }
        }
        String errMsg = String.format("Unknown primitive type: %s", conditionType.getTypeName());
        throw new IllegalArgumentException(errMsg);
    }

    /**
     * Gets all JDoctor types as a list of Strings.
     */
    private static List<String> collectAllJDoctorPrimitiveTypeNames() {
        return Arrays
                .stream(CommonPrimitiveType.values())
                .filter(c -> c.name().startsWith("CONDITION"))
                .map(CommonPrimitiveType::getTypeName)
                .collect(Collectors.toList());
    }

    /**
     * Gets all JavaParser types as a list of Strings.
     */
    private static List<String> collectAllJPPrimitiveTypeNames() {
        return Arrays
                .stream(CommonPrimitiveType.values())
                .filter(c -> c.name().startsWith("JP"))
                .map(CommonPrimitiveType::getTypeName)
                .collect(Collectors.toList());
    }

    /**
     * @return a list of all possible JDoctor primitive types
     */
    public static List<String> getAllJDoctorPrimitiveTypeNames() {
        return allJDoctorPrimitiveTypes;
    }

    /**
     * @return a list of all possible JavaParser primitive types
     */
    public static List<String> getAllJPPrimitiveTypeNames() {
        return allJPPrimitiveTypes;
    }

    /**
     * @return a regex representation of all possible JDoctor primitive types.
     * Used to find JDoctor types in an arbitrary string.
     */
    public static String getJDoctorPrimitivesRegex() {
        return "[" + String.join("", allJDoctorPrimitiveTypes) + "]";
    }

    /**
     * @return a regex representation of all possible JavaParser primitive
     * types
     */
    public static String getJPPrimitivesRegex() {
        return "[" + String.join("", allJPPrimitiveTypes) + "]";
    }

    /**
     * @return the corresponding JDoctor or JavaParser type name
     * (e.g. "Z", "boolean", "D", "double)
     */
    public String getTypeName() {
        return this.type;
    }
}
