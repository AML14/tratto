package star.tratto.util.javaparser;

import static org.plumelib.util.CollectionsPlume.mapList;

import com.github.javaparser.ast.body.CallableDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.TypeParameter;
import org.plumelib.reflection.Signatures;

import java.util.List;

/**
 * This class provides static methods to convert between the ClassGetName and
 * ClassGetSimpleName forms of Java types, as well as other utilities for
 * analyzing various type representations.
 */
public class TypeUtils {
    /**
     * All primitive field descriptors. The ClassGetName form of a type uses
     * field descriptor for arrays of primitive types.
     */
    private final static List<String> primitiveFieldDescriptors = List.of("Z", "B", "C", "S", "I", "J", "F", "D");

    /** Private constructor to avoid creating an instance of this class. */
    private TypeUtils() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    /**
     * Gets the package name from a ClassGetName form of a type. If the
     * package does not exist (e.g. default or primitive), then the method
     * returns an empty string. This method works properly for inner classes,
     * because the ClassGetName syntax uses "$" between inner and outer
     * classes. This method does NOT work for array classes.
     *
     * @param classGetName a ClassGetName form of a type
     * @return the package name of the class. Returns an empty string for
     * primitive types.
     */
    public static String getPackageNameFromClassGetName(String classGetName) {
        int packageIdx = classGetName.lastIndexOf(".");
        if (packageIdx == -1) {
            return "";
        }
        return classGetName.substring(0, packageIdx);
    }

    /**
     * Gets the innermost class from a ClassGetName form of a type. This
     * method does NOT work for array classes.
     *
     * @param classGetName a ClassGetName form of a type
     * @return the innermost class of the type
     */
    public static String getInnermostClassNameFromClassGetName(String classGetName) {
        int classIdx = classGetName.lastIndexOf(".");
        if (classGetName.contains("$")) {
            classIdx = classGetName.lastIndexOf("$");
        }
        if (classIdx == -1) {
            return classGetName;
        }
        return classGetName.substring(classIdx + 1);
    }

    /**
     * Returns the array level of the type. ClassGetName arrays have "["
     * characters are at the beginning. ClassGetSimpleName arrays have "[]" at
     * the end.
     *
     * @param typeName a ClassGetName or ClassGetSimpleName form of a type
     * @return the array level of the type
     */
    private static int getArrayLevel(String typeName) {
        int arrayLevel = 0;
        for (int i = 0; i < typeName.length(); i++) {
            if (typeName.charAt(i) == '[') {
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
        return classGetSimpleName + "[]".repeat(arrayLevel);
    }

    /**
     * Returns true iff the ClassGetName component type is a primitive type.
     * The ClassGetName form of a type uses field descriptors for arrays of
     * primitive types.
     *
     * @param classGetNameComponent a ClassGetName type
     * @return true iff the ClassGetName component is a field descriptor
     * @see TypeUtils#primitiveFieldDescriptors
     */
    private static boolean isPrimitiveFieldDescriptor(String classGetNameComponent) {
        return primitiveFieldDescriptors.contains(classGetNameComponent);
    }

    /**
     * Gets the ClassGetName form of the element type for a given ClassGetName
     * array type. For example,
     * <pre>
     *     "[[I"    =&gt;    "I"
     *     "[Ljava.lang.String;"    =&gt;    "java.lang.String"
     * </pre>
     *
     * @param classGetNameArray a ClassGetName form of an array type
     * @return the ClassGetName form of the component type without arrays
     */
    private static String classGetNameElementType(String classGetNameArray) {
        assert Signatures.isClassGetName(classGetNameArray);
        int arrayLevel = getArrayLevel(classGetNameArray);
        if (arrayLevel == 0) {
            return classGetNameArray;
        }
        String classGetNameElement = classGetNameArray.replaceAll("\\[", "");
        if (isPrimitiveFieldDescriptor(classGetNameElement)) {
            return classGetNameElement;
        } else {
            // for arrays of objects, remove "L" prefix and ";" suffix.
            return classGetNameElement.substring(1, classGetNameElement.length() - 1);
        }
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
        classGetName = classGetNameElementType(classGetName);
        if (isPrimitiveFieldDescriptor(classGetName)) {
            // ClassGetName uses field descriptors for arrays of primitive types
            classGetName = Signatures.fieldDescriptorToBinaryName(classGetName);
        } else {
            classGetName = getInnermostClassNameFromClassGetName(classGetName);
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
        return mapList(TypeUtils::classGetNameToClassGetSimpleName, classGetNames);
    }

    /**
     * Removes type arguments from a parameterized type name. The given type
     * name should originate from source code, as neither ClassGetSimpleName
     * nor ClassGetName forms include type parameters.
     *
     * @param parameterizedType a type name from source code
     * @return the base type without type arguments
     */
    public static String removeTypeArguments(String parameterizedType) {
        String previous;
        String current = parameterizedType;
        do {
            previous = current;
            current = previous.replaceAll("<[^<>]*>", "");
        } while (!current.equals(previous));
        return current;
    }

    /**
     * Attempts to get the upper bound of a type parameter from a given method
     * and declaring class. This method checks both the method using the given
     * type and its declaring class to find a possible upper bound. <br> This
     * method does not YET check enclosing classes or methods for potential
     * upper bounds.
     *
     * @param jpClass the declaring class
     * @param jpCallable the method using {@code typeName}
     * @param typeName a type parameter (e.g. "T", "E")
     * @return the type name of the upper bound of {@code typeName}. Returns
     * {@code typeName} if unable to find an upper bound. Returns the first
     * upper bound if a type parameter has multiple bounds.
     */
    private static String getUpperBound(
            TypeDeclaration<?> jpClass,
            CallableDeclaration<?> jpCallable,
            String typeName
    ) {
        int arrayLevel = TypeUtils.getArrayLevel(typeName);
        String elementType = typeName.replaceAll("\\[]", "");
        // check method for matching type parameters
        ClassOrInterfaceType upperBound = jpCallable
                .getTypeParameters()
                .stream()
                .filter(typeParam -> typeParam.getName().getIdentifier().equals(elementType))
                .findFirst()
                .map(TypeParameter::getTypeBound)
                .flatMap(bound -> bound.stream().findFirst())
                .orElse(null);
        // check declaring class for matching type parameters
        if (upperBound == null && jpClass.isClassOrInterfaceDeclaration()) {
            upperBound = jpClass
                    .asClassOrInterfaceDeclaration()
                    .getTypeParameters()
                    .stream()
                    .filter(typeParam -> typeParam.getName().getIdentifier().equals(elementType))
                    .findFirst()
                    .map(TypeParameter::getTypeBound)
                    .flatMap(bound -> bound.stream().findFirst())
                    .orElse(null);
        }
        if (upperBound == null) {
            return typeName;
        } else {
            return addArrayLevel(upperBound.getNameAsString(), arrayLevel);
        }
    }

    /**
     * Gets the pre-processed JDoctor parameter type name from source code.
     * JDoctor applies several pre-processing steps for parameter type names.
     * In general, JDoctor uses the ClassGetSimpleName form of a type. If a
     * parameter is a vararg, then it is represented by an array. For example,
     *     "int... numbers" => "int[]"
     * Additionally, if a parameter is a type parameter with an upper bound,
     * then it is represented by its upper bound.
     *
     * @param jpDeclaration the declaring type (e.g. class, enum)
     * @param jpCallable the method with the parameter {@code jpParam}
     * @param jpParam a parameter
     * @return the pre-processed JDoctor simple name
     */
    public static String getJDoctorSimpleNameFromSourceCode(
            TypeDeclaration<?> jpDeclaration,
            CallableDeclaration<?> jpCallable,
            Parameter jpParam
    ) {
        // get simple name
        String typeName;
        if (jpParam.getType().isClassOrInterfaceType()) {
            typeName = removeTypeArguments(jpParam.getType().asClassOrInterfaceType().getNameAsString());
        } else {
            typeName = removeTypeArguments(jpParam.getType().asString());
        }
        // represent varargs as an array
        if (jpParam.isVarArgs()) {
            typeName = addArrayLevel(typeName, 1);
        }
        // use upper bound (if possible)
        typeName = getUpperBound(jpDeclaration, jpCallable, typeName);
        return typeName;
    }

    /**
     * Checks if a ClassGetSimpleName is "Object" or "Comparable".
     *
     * @param typeName a simple type name
     * @return true iff the given type name is "Object" or "Comparable"
     */
    public static boolean isObjectOrComparable(String typeName) {
        return typeName.equals("Object") || typeName.equals("Comparable");
    }

    /**
     * Checks if a ClassGetSimpleName is "Object[]" or "Comparable[]".
     *
     * @param typeName name of the JDoctor or JavaParser type
     * @return true iff the given type name is "Object[]" or "Comparable[]"
     * @see TypeUtils#isObjectOrComparable(String)
     */
    public static boolean isObjectOrComparableArray(String typeName) {
        return typeName.equals("Object[]") || typeName.equals("Comparable[]");
    }
}
