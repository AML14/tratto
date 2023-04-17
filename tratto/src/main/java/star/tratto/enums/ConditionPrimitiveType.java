package star.tratto.enums;

import java.util.Arrays;
import java.util.List;

public enum ConditionPrimitiveType {
    CONDITION_BOOLEAN("Z"),
    CONDITION_BYTE("B"),
    CONDITION_CHAR("C"),
    CONDITION_DOUBLE("D"),
    CONDITION_FLOAT("F"),
    CONDITION_INTEGER("I"),
    CONDITION_LONG("J"),
    CONDITION_SHORT("S"),
    JP_BOOLEAN("boolean"),
    JP_BYTE("byte"),
    JP_CHAR("char"),
    JP_DOUBLE("double"),
    JP_FLOAT("float"),
    JP_INTEGER("int"),
    JP_LONG("long"),
    JP_SHORT("short");


    private final String type;

    private ConditionPrimitiveType(String name) {
        this.type = name;
    }

    public static ConditionPrimitiveType condition2jp(ConditionPrimitiveType conditionType) {
        switch (conditionType) {
            case CONDITION_BOOLEAN:
                return JP_BOOLEAN;
            case CONDITION_BYTE:
                return JP_BYTE;
            case CONDITION_CHAR:
                return JP_CHAR;
            case CONDITION_DOUBLE:
                return JP_DOUBLE;
            case CONDITION_FLOAT:
                return JP_FLOAT;
            case CONDITION_INTEGER:
                return JP_INTEGER;
            case CONDITION_LONG:
                return JP_LONG;
            case CONDITION_SHORT:
                return JP_SHORT;
        }
        String errMsg = String.format("Unknown primitive type: %s", conditionType.getValue());
        throw new IllegalArgumentException(errMsg);
    }

    public static ConditionPrimitiveType convertValue(String parameter) {
        switch (parameter) {
            case "Z":
                return CONDITION_BOOLEAN;
            case "B":
                return CONDITION_BYTE;
            case "C":
                return CONDITION_CHAR;
            case "D":
                return CONDITION_DOUBLE;
            case "F":
                return CONDITION_FLOAT;
            case "I":
                return CONDITION_INTEGER;
            case "J":
                return CONDITION_LONG;
            case "S":
                return CONDITION_SHORT;
            case "boolean":
                return JP_BOOLEAN;
            case "byte":
                return JP_BYTE;
            case "char":
                return JP_CHAR;
            case "double":
                return JP_DOUBLE;
            case "float":
                return JP_FLOAT;
            case "integer":
                return JP_INTEGER;
            case "long":
                return JP_LONG;
            case "short":
                return JP_SHORT;
        }
        String errMsg = String.format("Unknown primitive parameter: %s", parameter);
        throw new IllegalArgumentException(errMsg);
    }

    public static String getConditionRegexValues() {
        List<String> conditionList = getConditionValues();
        String regex = "[" + String.join("", conditionList) + "]";
        return regex;
    }

    public static List<String> getConditionValues() {
        List<String> conditionList =  Arrays
                .stream(ConditionPrimitiveType.values())
                .filter(c -> c.name().startsWith("CONDITION"))
                .map(c -> c.getValue())
                .toList();
        return conditionList;
    }

    public static List<String> getJPValues() {
        List<String> jpList =  Arrays
                .stream(ConditionPrimitiveType.values())
                .filter(c -> c.name().startsWith("JP"))
                .map(c -> c.getValue())
                .toList();
        return jpList;
    }

    public String getValue() {
        return this.type;
    }

    public static ConditionPrimitiveType jp2condition(ConditionPrimitiveType conditionType) {
        switch (conditionType) {
            case JP_BOOLEAN:
                return CONDITION_BOOLEAN;
            case JP_BYTE:
                return CONDITION_BYTE;
            case JP_CHAR:
                return CONDITION_CHAR;
            case JP_DOUBLE:
                return CONDITION_DOUBLE;
            case JP_FLOAT:
                return CONDITION_FLOAT;
            case JP_INTEGER:
                return CONDITION_INTEGER;
            case JP_LONG:
                return CONDITION_LONG;
            case JP_SHORT:
                return CONDITION_SHORT;
        }
        String errMsg = String.format("Unknown primitive type: %s", conditionType.getValue());
        throw new IllegalArgumentException(errMsg);
    }
}
