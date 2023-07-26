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
 * field descriptor and source code formats of all Java types. For our
 * purposes, we note that JDoctor uses field descriptors to represent types,
 * whereas JavaParser uses source code format to represent types.
 */
public class TypeUtilsTemp {
    // all field descriptor String representations of a primitive type.
    private final static List<String> allPrimitiveFieldDescriptors = List.of(
            "Z",
            "B",
            "C",
            "S",
            "I",
            "J",
            "F",
            "D"
    );
    // a regex of all possible field descriptor String representations of a primitive type.
    private final static String allPrimitiveFieldDescriptorsRegex = "[ZBCSIJFD]";

    // private constructor to avoid creating an instance of this class.
    private TypeUtilsTemp() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    /**
     * Removes any type arguments from a parameterized type name.
     *
     * @param parameterizedType a field descriptor or source code type
     *                          representation
     * @return the same type representation without type arguments
     */
    public static String removeTypeArguments(String parameterizedType) {
        // regex to match type arguments in angle brackets.
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
     * Splits a binary name (of a class or method) into segments. Splits name
     * based on "." (package name) and "$" (inner class).
     *
     * @param name a binary method/class name
     * @return name segments. Includes: all outer packages, all outer classes,
     * and the innermost class.
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
     *  [outerClass package].[outerClass]
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
     * array level by a specified amount.
     *
     * @param typeName a type name (in any format)
     * @param arrayLevel the number of array levels to add
     * @return the new type name with the added number of array levels
     * (in source code format)
     */
    private static String addSourceCodeArrayLevel(String typeName, int arrayLevel) {
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
    private static String fieldDescriptorArrayToSourceCodeArray(String fieldDescriptor) {
        int arrayLevel = getArrayLevel(fieldDescriptor);
        return addSourceCodeArrayLevel(fieldDescriptor.substring(arrayLevel), arrayLevel);
    }

    private static String getEquivalentPrimitiveType(String fieldDescriptor) {
        switch (fieldDescriptor) {
            case "Z" -> {
                return "boolean";
            }
            case "B" -> {
                return "byte";
            }
            case "C" -> {
                return "char";
            }
            case "S" -> {
                return "short";
            }
            case "I" -> {
                return "int";
            }
            case "J" -> {
                return "long";
            }
            case "F" -> {
                return "float";
            }
            case "D" -> {
                return "double";
            }
        }
        throw new IllegalArgumentException("Unrecognized primitive field descriptor: " + fieldDescriptor);
    }

    /**
     * Converts a field descriptor representation of a primitive type to a
     * source code format representation of a primitive type. Builds on top of
     * {@link TypeUtilsTemp#getEquivalentPrimitiveType(String)} to
     * include array types.
     *
     * @param fieldDescriptor a field descriptor primitive type name. Can
     *                        be an array.
     * @return the corresponding source code format primitive type name
     * @throws IllegalArgumentException if {@code fieldDescriptor} does not
     * match a known field descriptor primitive representation
     */
    private static String fieldDescriptorPrimitiveToSourceCodePrimitive(String fieldDescriptor) {
        // match `fieldDescriptor` to a known primitive field descriptor.
        String regex = String.format(
                "[^A-Za-z0-9_]*(%s)[^A-Za-z0-9_]*",
                allPrimitiveFieldDescriptorsRegex
        );
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(fieldDescriptor);
        if (matcher.find()) {
            String rawFieldDescriptor = matcher.group(1);
            String sourceCodePrimitiveType = getEquivalentPrimitiveType(rawFieldDescriptor);
            return fieldDescriptor.replaceAll(allPrimitiveFieldDescriptorsRegex, sourceCodePrimitiveType);
        } else {
            // `fieldDescriptor` does not match any known field descriptor.
            throw new IllegalArgumentException(String.format(
                    "The given type name does not match any field descriptor primitive type: %s",
                    fieldDescriptor
            ));
        }
    }

    /**
     * Converts a field descriptor representation of a type name to its
     * corresponding source code format representation of a type name.
     *
     * @param fieldDescriptor a field descriptor representation of a type
     * @return the corresponding source code format representation of a type
     */
    private static String fieldDescriptorNameToSourceCodeName(String fieldDescriptor) {
        fieldDescriptor = removeTypeArguments(fieldDescriptor);
        // converts primitive type.
        if (allPrimitiveFieldDescriptors.contains(fieldDescriptor.replaceAll("[^a-zA-Z]+", ""))) {
            fieldDescriptor = fieldDescriptorPrimitiveToSourceCodePrimitive(fieldDescriptor);
        }
        // converts array type.
        if (fieldDescriptor.startsWith("[")) {
            fieldDescriptor = fieldDescriptorArrayToSourceCodeArray(fieldDescriptor);
        }
        // gets innermost class.
        return getClassNameFromNameSegments(getNameSegments(fieldDescriptor));
    }

    /**
     * Converts a list of field descriptors {@code fieldDescriptors} to a list
     * of source code format type names.
     *
     * @param fieldDescriptors field descriptors to convert
     * @return the corresponding source code format type names
     */
    public static List<String> fieldDescriptorNamesToSourceCodeNames(
            List<String> fieldDescriptors
    ) {
        return fieldDescriptors
                .stream()
                .map(TypeUtilsTemp::fieldDescriptorNameToSourceCodeName)
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
            return removeTypeArguments(matcher.group(1)) + ("[]").repeat(arrayLevel);
        } else {
            throw new IllegalArgumentException(String.format(
                    "The source code %s does not match the regex built with the type name %s.",
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
        String jpTypeName = removeTypeArguments(jpParam.getType().asString());
        // get class name.
        if (jpParam.getType().isClassOrInterfaceType()) {
            jpTypeName = removeTypeArguments(jpParam.getType().asClassOrInterfaceType().getNameAsString());
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
     */
    public static boolean isStandardTypeArray(String typeName) {
        return typeName.equals("Object[]") || typeName.equals("Comparable[]");
    }
}
