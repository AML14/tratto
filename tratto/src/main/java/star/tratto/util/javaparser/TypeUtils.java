package star.tratto.util.javaparser;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.body.CallableDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.type.TypeParameter;
import org.plumelib.reflection.Signatures;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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
    /** All primitive field descriptors. */
    private final static List<String> allPrimitiveFieldDescriptors = List.of("Z", "B", "C", "S", "I", "J", "F", "D");

    /** Private constructor to avoid creating an instance of this class. */
    private TypeUtils() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    /**
     * Removes type arguments and semicolons from a parameterized type name.
     *
     * @param parameterizedType a field descriptor or source code
     *                          representation of a type
     *                          (they are equivalent for parameterized objects)
     * @return the raw type without type arguments
     */
    public static String removeTypeArgumentsAndSemicolon(String parameterizedType) {
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
     * Splits a binary name into segments. Splits name based on "." (package)
     * and "$" (member).
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
     * Returns innermost class of the name segments.
     *
     * @param nameSegments name segments. Must represent a class.
     * @return innermost class of the name segments 
     * @see TypeUtils#getNameSegments(String)
     */
    public static String getInnermostClassNameFromNameSegments(
            List<String> nameSegments
    ) {
        return nameSegments.get(nameSegments.size() - 1);
    }

    /** Returns the array level of the type.
@param typeName a field descriptor or source code type representation
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

    /** Returns true iff the field descriptor represents a primitive type or an
     * array of primitive types.
@param fieldDescriptor a field descriptor of a type (can be an array)
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
        fieldDescriptor = removeTypeArgumentsAndSemicolon(fieldDescriptor);
        if (hasPrimitive(fieldDescriptor)) {
            // use plume-lib to convert between primitive field descriptors and binary names
            fieldDescriptor = Signatures.fieldDescriptorToBinaryName(fieldDescriptor);
        } else {
            // manually convert array and then get class name
            fieldDescriptor = fieldDescriptorArrayToSourceFormatArray(fieldDescriptor);
            fieldDescriptor = getInnermostClassNameFromNameSegments(getNameSegments(fieldDescriptor));
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

    /** Returns true iff the type represents a vararg (e.g. uses "...").
@param typeName a source code format type name
     * @return true iff the type represents a vararg (e.g. uses "...") 
     */
    public static boolean hasEllipsis(String typeName) {
        return typeName.contains("...");
    }

    /**
     * Checks if a given type extends a supertype in source code (e.g. has an
     * upper bound).
     * NOTE: We only check "extends" and not "implements" for parameterized
     * types.
     *
     * @param sourceCode the method or class source code in which
     * {@code typeName} is used
     * @param typeName a source code format type name
     * @return true iff the given type name extends another type
     */
    private static boolean hasSupertype(String sourceCode, String typeName) {
        String regex = String.format("%s\\s+extends\\s+([A-Za-z0-9_]+)[<[A-Za-z0-9_,]+]*", typeName.replaceAll("\\[]",""));
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(sourceCode);
        return matcher.find();
    }

    /**
     * Finds the supertype of a given type name in source code. The given
     * type name must use the source code format representation.
     *
     * @param sourceCode the method or class source code in which
     * {@code typeName} is used
     * @param typeName a source code format type name
     * @return the name of the supertype of {@code typeName}
     * @throws IllegalArgumentException if {@code typeName} does not extend
     * a type
     */
    private static String getSupertype(String sourceCode, String typeName) {
        String regex = String.format("%s\\s+extends\\s+([A-Za-z0-9_]+)[<[A-Za-z0-9_,]+]*", typeName.replaceAll("\\[]",""));
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(sourceCode);
        if (matcher.find()) {
            String typeBound = removeTypeArgumentsAndSemicolon(matcher.group(1));
            return addArrayLevel(typeBound, getArrayLevel(typeName));
        } else {
            throw new IllegalArgumentException(String.format(
                    "The JavaParser source code %s does not match the regex built with the JavaParser type name %s.",
                    sourceCode,
                    typeName
            ));
        }
    }

    /**
     * Gets the upper bound of a generic type from the method or class source.
     *
     * @param jpDeclaration the declaring class
     * @param jpCallable the method using {@code typeName}
     * @param typeName a type name represented in source code format
     * @return the upper bound (if it exists) from either the relevant method
     * or declaring class
     */
    private static String getGenericUpperBound(
            TypeDeclaration<?> jpDeclaration,
            CallableDeclaration<?> jpCallable,
            String typeName
    ) {
        // get upper bound from method/constructor declaration
        Optional<TokenRange> callableTokenRange = jpCallable.getTokenRange();
        if (callableTokenRange.isPresent() && hasSupertype(callableTokenRange.get().toString(), typeName)) {
            typeName = getSupertype(callableTokenRange.get().toString(), typeName);
        }
        // get upper bound from class declaration
        if (jpDeclaration.isClassOrInterfaceDeclaration()) {
            for (TypeParameter jpGeneric : jpDeclaration.asClassOrInterfaceDeclaration().getTypeParameters()) {
                if (jpGeneric.getNameAsString().equals(typeName.replaceAll("\\[]", ""))) {
                    if (hasSupertype(jpGeneric.toString(), typeName)) {
                        typeName = getSupertype(jpGeneric.toString(), typeName);
                    }
                }
            }
        }
        return typeName;
    }

    /**
     * Gets the raw name of a type in source code. Used to manage edge cases
     * with generic types in source code. Also ensures name is consistent with
     * the field descriptor format for direct comparison.
     *
     * @param jpDeclaration the declaring type (e.g. class, enum, etc.)
     * @param jpCallable the method using {@code jpParam}
     * @param jpParam a parameter
     * @return the raw name of the parameter in source code
     */
    public static String getRawTypeName(
            TypeDeclaration<?> jpDeclaration,
            CallableDeclaration<?> jpCallable,
            Parameter jpParam
    ) {
        // get type name
        String typeName;
        if (jpParam.getType().isClassOrInterfaceType()) {
            typeName = removeTypeArgumentsAndSemicolon(jpParam.getType().asClassOrInterfaceType().getNameAsString());
        } else {
            typeName = removeTypeArgumentsAndSemicolon(jpParam.getType().asString());
        }
        // add array level for varargs
        if (hasEllipsis(jpParam.toString())) {
            typeName = addArrayLevel(typeName, 1);
        }
        // use upper bound, if possible
        typeName = getGenericUpperBound(jpDeclaration, jpCallable, typeName);
        return typeName;
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
