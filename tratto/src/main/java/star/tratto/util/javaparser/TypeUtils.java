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

    /**
     * Returns the array level of the type.
     *
     * @param typeName a ClassGetName or ClassGetSimpleName form of a type
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
     * Adds pairs of square brackets to a given ClassGetSimpleName type.
     *
     * @param classGetSimpleName a ClassGetSimpleName form of a type
     * @param arrayLevel number of array levels to add
     * @return the simple type name with the added number of array levels
     */
    private static String addArrayLevel(String classGetSimpleName, int arrayLevel) {
        return classGetSimpleName + ("[]").repeat(arrayLevel);
    }

    /**
     * Returns true iff the ClassGetName type is a primitive type.
     *
     * @param classGetName a ClassGetName form of a type
     * @return true iff the ClassGetName type represents a primitive type
     */
    private static boolean isPrimitive(String classGetName) {
        return allPrimitiveFieldDescriptors.contains(classGetName.replaceAll("[^a-zA-Z]+", ""));
    }

    /**
     * Converts a ClassGetName form of a type to its corresponding
     * ClassGetSimpleName form.
     *
     * @param classGetName a ClassGetName form of a type
     * @return the corresponding ClassGetSimpleName form of a type
     */
    private static String classGetNameToClassGetSimpleName(
            String classGetName
    ) {
        int arrayLevel = getArrayLevel(classGetName);
        System.out.println(classGetName);
        classGetName = classGetName.replaceAll("\\[", "");
        classGetName = classGetName.replaceAll(";", "");
        if (isPrimitive(classGetName)) {
            classGetName = Signatures.fieldDescriptorToBinaryName(classGetName);
        } else {
            if (arrayLevel > 0) {
                classGetName = classGetName.substring(1);
            }
            classGetName = getInnermostClassNameFromNameSegments(getNameSegments(classGetName));
        }
        return addArrayLevel(classGetName, arrayLevel);
    }

    /**
     * Converts a list of ClassGetName forms of types to their corresponding
     * ClassGetSimpleName forms.
     *
     * @param classGetNames ClassGetName forms of types
     * @return the corresponding ClassGetSimpleName forms of types
     */
    public static List<String> classGetNameToClassGetSimpleName(
            List<String> classGetNames
    ) {
        return classGetNames
                .stream()
                .map(TypeUtils::classGetNameToClassGetSimpleName)
                .collect(Collectors.toList());
    }

    /**
     * Returns true iff the type represents a vararg (e.g. uses "...").
     *
     * @param typeName a source code format type name. May include
     *                 variable name (e.g. "Integer someValue").
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
     * Checks if a given type is an Object or Comparable object.
     *
     * @param typeName name of the JDoctor or JavaParser type
     * @return true iff the given type name is "Object" or "Comparable"
     */
    public static boolean isObjectOrComparable(String typeName) {
        return typeName.equals("Object") || typeName.equals("Comparable");
    }

    /**
     * Checks if a given type is an array of Object or Comparable objects.
     *
     * @param typeName name of the JDoctor or JavaParser type
     * @return true iff the given type name is "Object[]" or "Comparable[]"
     * @see TypeUtils#isObjectOrComparable(String)
     */
    public static boolean isObjectOrComparableArray(String typeName) {
        return typeName.equals("Object[]") || typeName.equals("Comparable[]");
    }
}
