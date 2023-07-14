package star.tratto.identifiers;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class converts between the field descriptor and source code formats of
 * a primitive type. The field descriptor (or JVML) format, used by JDoctor,
 * uses single-letter representations of primitive types. The source code
 * format, used by JavaParser, uses the string representation in source code
 * to describe primitive types.
 */
public class CommonPrimitiveType {
    // base primitive types.
    private static final Class<Boolean> booleanClass = boolean.class;
    private static final Class<Byte> byteClass = byte.class;
    private static final Class<Character> charClass = char.class;
    private static final Class<Short> shortClass = short.class;
    private static final Class<Integer> intClass = int.class;
    private static final Class<Long> longClass = long.class;
    private static final Class<Float> floatClass = float.class;
    private static final Class<Double> doubleClass = double.class;
    // list of all primitive types.
    private static final List<Class<?>> primitiveList = List.of(
            booleanClass,
            byteClass,
            charClass,
            shortClass,
            intClass,
            longClass,
            floatClass,
            doubleClass
    );

    private static Class<?> fieldDescriptorToClass(String fieldDescriptor) {
        switch (fieldDescriptor) {
            case "Z" -> {
                return booleanClass;
            }
            case "B" -> {
                return byteClass;
            }
            case "C" -> {
                return charClass;
            }
            case "S" -> {
                return shortClass;
            }
            case "I" -> {
                return intClass;
            }
            case "J" -> {
                return longClass;
            }
            case "F" -> {
                return floatClass;
            }
            case "D" -> {
                return doubleClass;
            }
        }
        throw new IllegalArgumentException("Unrecognized primitive field descriptor: " + fieldDescriptor);
    }

    private static Class<?> sourceCodeToClass(String sourceCode) {
        switch (sourceCode) {
            case "boolean" -> {
                return booleanClass;
            }
            case "byte" -> {
                return byteClass;
            }
            case "char" -> {
                return charClass;
            }
            case "short" -> {
                return shortClass;
            }
            case "int" -> {
                return intClass;
            }
            case "long" -> {
                return longClass;
            }
            case "float" -> {
                return floatClass;
            }
            case "double" -> {
                return doubleClass;
            }
        }
        throw new IllegalArgumentException("Unrecognized primitive source code: " + sourceCode);
    }

    private static String classToFieldDescriptor(Class<?> primitiveClass) {
        if (primitiveClass.equals(booleanClass)) {
            return "Z";
        } else if (primitiveClass.equals(byteClass)) {
            return "B";
        } else if (primitiveClass.equals(charClass)) {
            return "C";
        } else if (primitiveClass.equals(shortClass)) {
            return "S";
        } else if (primitiveClass.equals(intClass)) {
            return "I";
        } else if (primitiveClass.equals(longClass)) {
            return "J";
        } else if (primitiveClass.equals(floatClass)) {
            return "F";
        } else if (primitiveClass.equals(doubleClass)) {
            return "D";
        }
        throw new IllegalArgumentException("Unrecognized class: " + primitiveClass);
    }

    private static String classToSourceCode(Class<?> primitiveClass) {
        return primitiveClass.getName();
    }

    public static String convertFieldDescriptorToSourceCode(String fieldDescriptor) {
        Class<?> primitiveClass = fieldDescriptorToClass(fieldDescriptor);
        return classToSourceCode(primitiveClass);
    }

    public static String convertSourceCodeToFieldDescriptor(String sourceCode) {
        Class<?> primitiveClass = sourceCodeToClass(sourceCode);
        return classToFieldDescriptor(primitiveClass);
    }

    public static List<String> getAllFieldDescriptors() {
        return primitiveList.stream().map(CommonPrimitiveType::classToFieldDescriptor).collect(Collectors.toList());
    }

    public static String getAllFieldDescriptorsRegex() {
        return "[" + String.join("", getAllFieldDescriptors()) + "]";
    }
}
