package star.tratto.utils.javaparser;

import com.github.javaparser.ast.body.CallableDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.TypeParameter;

import java.util.List;

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
}
