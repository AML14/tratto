package star.tratto.util;

import org.javatuples.Pair;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class JavaTypes {

    public static final Pair<String, String> NULL = Pair.with("", "null");
    public static final String NULL_TYPE = "null";

    public static final List<Pair<String, String>> PRIMITIVES = List.of(
            Pair.with("", "boolean"),
            Pair.with("", "int"),
            Pair.with("", "long"),
            Pair.with("", "float"),
            Pair.with("", "double"),
            Pair.with("", "byte"),
            Pair.with("", "short"),
            Pair.with("", "char")
    );
    public static final List<String> PRIMITIVE_TYPES = PRIMITIVES.stream().map(Pair::getValue1).collect(Collectors.toList());

    public static final List<Pair<String, String>> PRIMITIVE_WRAPPERS = List.of(
            Pair.with("java.lang", "Boolean"),
            Pair.with("java.lang", "Integer"),
            Pair.with("java.lang", "Long"),
            Pair.with("java.lang", "Float"),
            Pair.with("java.lang", "Double"),
            Pair.with("java.lang", "Byte"),
            Pair.with("java.lang", "Short"),
            Pair.with("java.lang", "Character")
    );
    public static final List<String> PRIMITIVE_WRAPPER_TYPES = PRIMITIVES.stream().map(Pair::getValue1).collect(Collectors.toList());

    public static final Map<Pair<String, String>, Pair<String, String>> PRIMITIVES_TO_WRAPPERS = IntStream.range(0, PRIMITIVES.size())
            .boxed()
            .collect(Collectors.toMap(PRIMITIVES::get, PRIMITIVE_WRAPPERS::get));

    public static final Map<Pair<String, String>, Pair<String, String>> WRAPPERS_TO_PRIMITIVES = IntStream.range(0, PRIMITIVES.size())
            .boxed()
            .collect(Collectors.toMap(PRIMITIVE_WRAPPERS::get, PRIMITIVES::get));

    public static final List<Pair<String, String>> INTEGRALS = List.of(
            Pair.with("", "int"),
            Pair.with("", "long"),
            Pair.with("", "byte"),
            Pair.with("", "short"),
            Pair.with("", "char"),
            Pair.with("java.lang", "Integer"),
            Pair.with("java.lang", "Long"),
            Pair.with("java.lang", "Byte"),
            Pair.with("java.lang", "Short"),
            Pair.with("java.lang", "Character")
    );
    public static final List<String> INTEGRAL_TYPES = INTEGRALS.stream().map(Pair::getValue1).collect(Collectors.toList());

    public static final List<Pair<String, String>> NUMBERS = List.of(
            Pair.with("", "int"),
            Pair.with("", "long"),
            Pair.with("", "float"),
            Pair.with("", "double"),
            Pair.with("", "byte"),
            Pair.with("", "short"),
            Pair.with("", "char"),
            Pair.with("java.lang", "Integer"),
            Pair.with("java.lang", "Long"),
            Pair.with("java.lang", "Float"),
            Pair.with("java.lang", "Double"),
            Pair.with("java.lang", "Byte"),
            Pair.with("java.lang", "Short"),
            Pair.with("java.lang", "Character")
    );
    public static final List<String> NUMBER_TYPES = NUMBERS.stream().map(Pair::getValue1).collect(Collectors.toList());

    public static final List<Pair<String, String>> BOOLEANS = List.of(
            Pair.with("", "boolean"),
            Pair.with("java.lang", "Boolean")
    );
    public static final List<String> BOOLEAN_TYPES = BOOLEANS.stream().map(Pair::getValue1).collect(Collectors.toList());

    /**
     * Typical classes from java.lang and java.util packages against which one may want to check if a variable is an instance of.
     */
    public static final List<Pair<String, String>> LANG_UTIL = List.of(
            Pair.with("java.lang", "String"),
            Pair.with("java.lang", "Integer"),
            Pair.with("java.lang", "Double"),
            Pair.with("java.lang", "Long"),
            Pair.with("java.lang", "Float"),
            Pair.with("java.lang", "Boolean"),
            Pair.with("java.util", "List"),
            Pair.with("java.util", "Map"),
            Pair.with("java.util", "Set")
    );
    public static final List<String> LANG_UTIL_TYPES = LANG_UTIL.stream().map(Pair::getValue1).collect(Collectors.toList());

    public static boolean isAssignableToNumeric(Pair<String, String> numeric1, Pair<String, String> numeric2) {
        if (!JavaTypes.NUMBERS.contains(numeric1) || !JavaTypes.NUMBERS.contains(numeric2)) {
            throw new IllegalArgumentException("Both types must be numeric");
        }
        String i = numeric1.getValue1();
        String j = numeric2.getValue1();

        switch (i) {
            case "char":
                return List.of("char", "int", "long", "float", "double", "Character").contains(j);
            case "Character":
                return List.of("Character", "char").contains(j);
            case "byte":
            case "Byte":
                return List.of("byte", "short", "int", "long", "float", "double", "Byte").contains(j);
            case "short":
            case "Short":
                return List.of("short", "int", "long", "float", "double", "Short").contains(j);
            case "int":
            case "Integer":
                return List.of("int", "long", "float", "double", "Integer").contains(j);
            case "long":
            case "Long":
                return List.of("long", "float", "double", "Long").contains(j);
            case "float":
            case "Float":
                return List.of("float", "double", "Float").contains(j);
            case "double":
            case "Double":
                return List.of("double", "Double").contains(j);
            default:
                throw new IllegalStateException("This exception should never be thrown");
        }
    }

}
