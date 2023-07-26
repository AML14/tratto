package star.tratto.util.javaparser;

import org.plumelib.reflection.Signatures;

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
    // all primitive field descriptors.
    private final static List<String> allPrimitiveFieldDescriptors = List.of("Z", "B", "C", "S", "I", "J", "F", "D");

    // private constructor to avoid creating an instance of this class.
    private TypeUtils() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    /**
     * Removes any type arguments from a parameterized type name. Also removes
     * semicolons, since this method is used when transforming field descriptors
     * to source code format.
     *
     * @param parameterizedType a field descriptor or source code
     *                          representation of a type
     * @return the raw type without type arguments
     */
    public static String removeTypeArgumentsAndSemicolon(String parameterizedType) {
        // regex to match type arguments in angle brackets.
        String regex = "<[^<>]*>|;";
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
     * @see TypeUtils#getNameSegments(String)
     */
    public static String getPackageNameFromNameSegments(
            List<String> nameSegments
    ) {
        return String.join(".", nameSegments.subList(0, nameSegments.size() - 1));
    }

    /**
     * @param nameSegments name segments. Must represent a class.
     * @return innermost class of the name segments
     * @see TypeUtils#getNameSegments(String)
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
     * @param typeName a type name
     * @param arrayLevel the number of array levels to add
     * @return the new type name with the added number of array levels
     */
    private static String addArrayLevel(String typeName, int arrayLevel) {
        return typeName + ("[]").repeat(arrayLevel);
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
     * @param fieldDescriptor a field descriptor of a type (can be an array)
     * @return true iff the field descriptor represents a primitive type or an
     * array of primitive types
     */
    private static boolean hasPrimitive(String fieldDescriptor) {
        return allPrimitiveFieldDescriptors.contains(fieldDescriptor.replaceAll("[^a-zA-Z]+", ""));
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
        if (hasPrimitive(fieldDescriptor)) {
            // convert primitive using plume-lib.
            fieldDescriptor = Signatures.fieldDescriptorToBinaryName(fieldDescriptor);
        } else {
            // convert object to format compatible with XText grammar.
            fieldDescriptor = fieldDescriptorArrayToSourceFormatArray(fieldDescriptor);
            fieldDescriptor = getClassNameFromNameSegments(getNameSegments(fieldDescriptor));
    private static String fieldDescriptorNameToSourceCodeName(String fieldDescriptor) {
        fieldDescriptor = removeTypeArgumentsAndSemicolon(fieldDescriptor);
        // converts primitive type.
        List<String> primitiveJDoctorValues = PrimitiveTypeUtils.getAllPrimitiveFieldDescriptors();
        if (primitiveJDoctorValues.contains(fieldDescriptor.replaceAll("[^a-zA-Z]+", ""))) {
            fieldDescriptor = fieldDescriptorPrimitiveToSourceCodePrimitive(fieldDescriptor);
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

    /**
     * @param jpClass a JavaParser class or interface declaration
     * @return true iff the given class or interface uses generic types
     */
    public static boolean hasGenerics(ClassOrInterfaceDeclaration jpClass) {
        return jpClass.getTypeParameters().size() > 0;
    }

    /**
     * @param typeName a source code format type name
     * @return true iff the parameter name includes "..."
     */
    public static boolean hasEllipsis(String typeName) {
        return typeName.contains("...");
    }

    /**
     * Checks if a given type extends a supertype in source code.
     * NOTE: We only check "extends" and not "implements" for parameterized
     * types.
     *
     * @param sourceCode the method or class source code in which
     * {@code typeName} is used
     * @param typeName a source code format type name
     * @return true iff the type name extends another type
     */
    public static boolean hasSupertype(String sourceCode, String typeName) {
        String regex = String.format("%s\\s+extends\\s+([A-Za-z0-9_]+)[<[A-Za-z0-9_,]+]*", typeName.replaceAll("\\[]",""));
        // Using the Pattern and Matcher classes
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(sourceCode);
        return matcher.find();
    }

    /**
     * Finds the supertype of a given type name in source code. We analyze
     * source code using JavaParser, such that the given type name must use
     * the source code format representation.
     *
     * @param sourceCode the method or class source code in which
     * {@code typeName} is used
     * @param typeName a source code format type name
     * @return the name of the supertype of {@code typeName}
     * @throws IllegalArgumentException if {@code typeName} does not extend
     * a type
     */
    private static String getSupertype(String sourceCode, String typeName) {
        int arrayLevel = getArrayLevel(typeName);
        // finds the supertype.
        String regex = String.format("%s\\s+extends\\s+([A-Za-z0-9_]+)[<[A-Za-z0-9_,]+]*", typeName.replaceAll("\\[]", ""));
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(sourceCode);
        if (matcher.find()) {
            return removeTypeArgumentsAndSemicolon(matcher.group(1)) + ("[]").repeat(arrayLevel);
        } else {
            throw new IllegalArgumentException(String.format(
                    "The JavaParser source code %s does not match the regex built with the JavaParser type name %s.",
                    sourceCode,
                    typeName
            ));
        }
    }

    /**
     * Gets the raw name of a type in source code. Used to manage edge cases
     * with generic types in source code. Also ensures name is consistent with
     * the field descriptor format for direct comparison.
     *
     * @param jpClass the declaring class
     * @param jpCallable the method using {@code jpParam}
     * @param jpParam a parameter
     * @return the raw name of the parameter in source code
     */
    public static String getRawTypeName(
            TypeDeclaration<?> jpClass,
            CallableDeclaration<?> jpCallable,
            Parameter jpParam
    ) {
        String jpTypeName = removeTypeArgumentsAndSemicolon(jpParam.getType().asString());
        // get class name.
        if (jpParam.getType().isClassOrInterfaceType()) {
            jpTypeName = removeTypeArgumentsAndSemicolon(jpParam.getType().asClassOrInterfaceType().getNameAsString());
        }
        // handle ellipsis.
        if (hasEllipsis(jpParam.toString())) {
            jpTypeName = addSourceCodeArrayLevel(jpTypeName, 1);
        }
        // use upper bound if possible.
        if (hasSupertype(jpCallable.getTokenRange().get().toString(), jpTypeName)) {
            jpTypeName = getSupertype(jpCallable.getTokenRange().get().toString(), jpTypeName);
        }
        // handle generic upper bound separately due to naming.
        if (jpClass.isClassOrInterfaceDeclaration()) {
            ClassOrInterfaceDeclaration jpClassDeclaration =  jpClass.asClassOrInterfaceDeclaration();
            if (hasGenerics(jpClassDeclaration)) {
                for (TypeParameter jpGeneric : jpClassDeclaration.getTypeParameters()) {
                    if (jpGeneric.getNameAsString().equals(jpTypeName.replaceAll("\\[]", ""))) {
                        if (hasSupertype(jpGeneric.toString(), jpTypeName)) {
                            jpTypeName = getSupertype(jpGeneric.toString(), jpTypeName);
                        }
                    }
                }
            }
        }
        return jpTypeName;
    }

    /**
     * We define a "standard" type as a type which implements either the
     * "Object" or "Comparable" interfaces, which require extra consideration
     * when comparing arguments to check equality.
     * See `jpParamEqualsJDoctorParam` in DatasetUtils for elaboration.
     *
     * @param typeName name of the JDoctor or JavaParser type
     * @return true iff the given type name is "Object" or "Comparable"
     */
    public static boolean isStandardType(String typeName) {
        return typeName.equals("Object") || typeName.equals("Comparable");
    }

    /**
     * Checks if a given type name is a "standard" array. By definition, this
     * includes the "Object[]" and "Comparable[]" types.
     * See above method {@code isStandardType} for further elaboration.
     *
     * @param typeName name of the JDoctor or JavaParser type
     * @return true iff the given type name is "Object[]" or "Comparable[]"
     * @see TypeUtils#isStandardType(String)
     */
    public static boolean isStandardTypeArray(String typeName) {
        return typeName.equals("Object[]") || typeName.equals("Comparable[]");
    }
}
