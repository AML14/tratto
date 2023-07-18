package star.tratto.util.javaparser;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class provides a collection of static methods to convert between the
 * field descriptor and source code formats of a primitive type. The field
 * descriptor (or JVML) format, used by JDoctor, uses single-letter
 * representations of primitive types. The source code format, used by
 * JavaParser, uses the string representation in source code to describe
 * primitive types.
 * See <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-4.html#:~:text=4.3.2.%C2%A0Field-,Descriptors,-A%20field%20descriptor">full table</a> for a list of conversions.
 */
public class PrimitiveTypeUtils {
    // list of all primitive types.
    private static final List<Class<?>> primitiveList = List.of(
            boolean.class,
            byte.class,
            char.class,
            short.class,
            int.class,
            long.class,
            float.class,
            double.class
    );
    // private constructor to avoid creating an instance of this class.
    private PrimitiveTypeUtils() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    /**
     * @param fieldDescriptor a field descriptor String representation of a primitive type
     * @return the primitive Class corresponding to the field descriptor
     */
    private static Class<?> fieldDescriptorToClass(String fieldDescriptor) {
        switch (fieldDescriptor) {
            case "Z" -> {
                return boolean.class;
            }
            case "B" -> {
                return byte.class;
            }
            case "C" -> {
                return char.class;
            }
            case "S" -> {
                return short.class;
            }
            case "I" -> {
                return int.class;
            }
            case "J" -> {
                return long.class;
            }
            case "F" -> {
                return float.class;
            }
            case "D" -> {
                return double.class;
            }
        }
        throw new IllegalArgumentException("Unrecognized primitive field descriptor: " + fieldDescriptor);
    }

    /**
     * @param primitiveType a source code String representation of a primitive type
     * @return the primitive Class corresponding to the source code
     */
    private static Class<?> primitiveTypeToClass(String primitiveType) {
        switch (primitiveType) {
            case "boolean" -> {
                return boolean.class;
            }
            case "byte" -> {
                return byte.class;
            }
            case "char" -> {
                return char.class;
            }
            case "short" -> {
                return short.class;
            }
            case "int" -> {
                return int.class;
            }
            case "long" -> {
                return long.class;
            }
            case "float" -> {
                return float.class;
            }
            case "double" -> {
                return double.class;
            }
        }
        throw new IllegalArgumentException("Unrecognized primitive source code: " + primitiveType);
    }

    /**
     * @param primitiveClass a Class representation of a primitive type
     * @return the field descriptor String representation of {@code primitiveClass}
     */
    private static String classToFieldDescriptor(Class<?> primitiveClass) {
        if (primitiveClass.equals(boolean.class)) {
            return "Z";
        } else if (primitiveClass.equals(byte.class)) {
            return "B";
        } else if (primitiveClass.equals(char.class)) {
            return "C";
        } else if (primitiveClass.equals(short.class)) {
            return "S";
        } else if (primitiveClass.equals(int.class)) {
            return "I";
        } else if (primitiveClass.equals(long.class)) {
            return "J";
        } else if (primitiveClass.equals(float.class)) {
            return "F";
        } else if (primitiveClass.equals(double.class)) {
            return "D";
        }
        throw new IllegalArgumentException("Unrecognized class: " + primitiveClass);
    }

    /**
     * @param primitiveClass a Class representation of a primitive type
     * @return the source code String representation of {@code primitiveClass}
     */
    private static String classToPrimitiveType(Class<?> primitiveClass) {
        return primitiveClass.getName();
    }

    /**
     * Converts a field descriptor String representation of a primitive type
     * to a source code String representation. See class JavaDoc for full list
     * of conversions.
     *
     * @param fieldDescriptor a field descriptor String representation of a
     *                        primitive type
     * @return the corresponding source code String representation
     */
    public static String convertFieldDescriptorToPrimitiveType(String fieldDescriptor) {
        Class<?> primitiveClass = fieldDescriptorToClass(fieldDescriptor);
        return classToPrimitiveType(primitiveClass);
    }

    /**
     * Converts a source code String representation of a primitive type to a
     * field descriptor String representation. See class JavaDoc for full list
     * of conversions.
     *
     * @param primitiveType a source code String representation of a primitive
     *                      type
     * @return the corresponding field descriptor String representation
     */
    public static String convertPrimitiveTypeToFieldDescriptor(String primitiveType) {
        Class<?> primitiveClass = primitiveTypeToClass(primitiveType);
        return classToFieldDescriptor(primitiveClass);
    }

    /**
     * @return field descriptor String representation of all primitive types
     */
    public static List<String> getAllFieldDescriptors() {
        return primitiveList.stream().map(PrimitiveTypeUtils::classToFieldDescriptor).collect(Collectors.toList());
    }

    /**
     *
     * @return a regex of all possible field descriptor String representations
     * of a primitive type. Used to find field descriptors in a given string.
     */
    public static String getAllFieldDescriptorsRegex() {
        return "[" + String.join("", getAllFieldDescriptors()) + "]";
    }
}
