package star.tratto.util.javaparser;

import com.github.javaparser.ast.body.CallableDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.type.TypeParameter;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * This class provides a collection of static methods to convert between the
 * field descriptor and source code formats of Java types. For our purposes,
 * we note that JDoctor uses field descriptors to represent types, whereas
 * JavaParser uses the source code format to represent types.
 */
public class TypeUtils {
    // private constructor to avoid creating an instance of this class.
    private TypeUtils() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    /**
     * Removes any type arguments from a parameterized type name.
     *
     * @param parameterizedType a field descriptor or source code
     *                          representation of a type
     * @return the raw type without type arguments
     */
    public static String removeTypeArguments(String parameterizedType) {
        // regex to match type arguments (e.g. <T>).
        String regex = "<[^<>]*>";
        // repeatedly remove all type arguments.
        String previous;
        String current = parameterizedType;
        do {
            previous = current;
            current = previous.replaceAll(regex, "");
        } while (!current.equals(previous));
        return current;
    }

    /**
     * Splits a binary name (of a class or member) into segments. Splits name
     * based on "." (package) and "$" (member).
     *
     * @param name a binary method/class name
     * @return name segments. Includes: all outer packages, all outer classes,
     * the innermost class, and the member name (if {@code name} is a member).
     */
    public static List<String> getNameSegments(
            String name
    ) {
        String regex = "[.$]";
        return Arrays.asList(name.split(regex));
    }

    /**
     * @param nameSegments name segments. Must represent a class.
     * @return name segments joined by ".", representing the package name.
     * NOTE: For inner classes, we represent the package name as:
     *  [outerClass package].[outerClass(es)]
     * for compatibility with the XText grammar.
     * @see TypeUtilsTemp#getNameSegments(String)
     */
    public static String getPackageNameFromNameSegments(
            List<String> nameSegments
    ) {
        return String.join(".", nameSegments.subList(0, nameSegments.size() - 1));
    }

    /**
     * @param nameSegments name segments. Must represent a class.
     * @return innermost class of the name segments
     * @see TypeUtilsTemp#getNameSegments(String)
     */
    public static String getClassNameFromNameSegments(
            List<String> nameSegments
    ) {
        return nameSegments.get(nameSegments.size() - 1);
    }

    /**
     * @param typeName a field descriptor or source code type representation
     * @return the array level of the type
     */
    private static int getArrayLevel(String typeName) {
        int arrayLevel = 0;
        for (char c : typeName.toCharArray()) {
            if (c == '[') {
                arrayLevel++;
            }
        }
        return arrayLevel;
    }

    /**
     * Adds pairs of square brackets to a given type name, increasing the
     * array level by a given amount.
     *
     * @param typeName a component type name
     * @param arrayLevel the number of added array levels
     * @return the new type name with the added number of array levels
     */
    private static String addArrayLevel(String typeName, int arrayLevel) {
        return typeName + ("[]").repeat(arrayLevel);
    }

    /**
     * @param fieldDescriptor a field descriptor of a type
     * @return true iff the field descriptor represents an array type
     */
    private static boolean isArray(String fieldDescriptor) {
        return fieldDescriptor.startsWith("[");
    }

    /**
     * @param fieldDescriptor a field descriptor of a type
     * @return true iff the field descriptor represents a primitive type
     */
    private static boolean isPrimitive(String fieldDescriptor) {
        return false;
    }

    /**
     * Converts a field descriptor array to a source code format array. This
     * method ONLY changes the square brackets, and will not change the
     * component type name. For example:
     *  [Object => Object[]
     *  [[D     => D[][]
     *
     * @param fieldDescriptor a field descriptor array type
     * @return the corresponding source code format array type
     */
    private static String fieldDescriptorArrayToSourceFormatArray(String fieldDescriptor) {
        int arrayLevel = getArrayLevel(fieldDescriptor);
        return addArrayLevel(fieldDescriptor.substring(arrayLevel), arrayLevel);
    }

    /**
     * Converts a field descriptor representation of a primitive type to its
     * corresponding source code format representation.
     *
     * @param fieldDescriptor a field descriptor of a primitive type. Can be
     *                        an array.
     * @return the corresponding source code format primitive type name
     * @throws IllegalArgumentException if the field descriptor does not match
     * a known primitive field descriptor
     */
    private static String fieldDescriptorPrimitiveToSourceFormatPrimitive(String fieldDescriptor) {
        return fieldDescriptor;
    }

    /**
     * Converts a field descriptor representation of a type name to its
     * corresponding source code format representation.
     *
     * @param fieldDescriptor a field descriptor representation of a type
     * @return the corresponding source code format representation of the type
     */
    private static String fieldDescriptorToSourceFormat(
            String fieldDescriptor
    ) {
        fieldDescriptor = removeTypeArguments(fieldDescriptor);
        if (isArray(fieldDescriptor)) {
            fieldDescriptor = fieldDescriptorArrayToSourceFormatArray(fieldDescriptor);
        }
        if (isPrimitive(fieldDescriptor)) {
            fieldDescriptor = fieldDescriptorPrimitiveToSourceFormatPrimitive(fieldDescriptor);
        } else {
            fieldDescriptor = getClassNameFromNameSegments(getNameSegments(fieldDescriptor));
        }
        return fieldDescriptor;
    }

    /**
     * Converts a list of field descriptors {@code fieldDescriptors} to a list
     * of source code format type names.
     *
     * @param fieldDescriptors field descriptors to convert
     * @return the corresponding source code format type names
     */
    public static List<String> fieldDescriptorsToSourceFormats(
            List<String> fieldDescriptors
    ) {
        return fieldDescriptors
                .stream()
                .map(TypeUtils::fieldDescriptorToSourceFormat)
                .collect(Collectors.toList());
    }
}
