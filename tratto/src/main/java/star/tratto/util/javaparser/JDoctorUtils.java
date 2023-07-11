package star.tratto.util.javaparser;

import star.tratto.identifiers.CommonPrimitiveType;

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
 * This class manages the differences between JDoctor and JavaParser
 * representations of variables, as well as other utilities for both type
 * representations.
 */
public class JDoctorUtils {
    /**
     * @param jDoctorTypeName a JDoctor representation of a type
     * @return the array level of the type
     */
    private static int getJDoctorArrayLevel(String jDoctorTypeName) {
        int arrayLevel = 0;
        for (char c : jDoctorTypeName.toCharArray()) {
            if (c == '[') {
                arrayLevel++;
            } else {
                break;
            }
        }
        return arrayLevel;
    }

    /**
     * Adds brackets to a given type, increasing the array level by a
     * specified amount.
     *
     * @param typeName a component type name
     * @param arrayLevel the number of added array levels
     * @return the new type name with the added number of array levels
     */
    private static String addArrayLevel(String typeName, int arrayLevel) {
        return typeName + "[]".repeat(arrayLevel);
    }

    /**
     * Converts a JDoctor array type name to a JavaParser array type name.
     * JDoctor represents arrays with single square brackets preceding the
     * name, whereas JavaParser uses pairs of square brackets after the name.
     *
     * @param jDoctorTypeName a JDoctor type name
     * @return the corresponding JavaParser type name
     */
    private static String convertJDoctorArrayToJavaParserArray(String jDoctorTypeName) {
        int arrayLevel = getJDoctorArrayLevel(jDoctorTypeName);
        return addArrayLevel(jDoctorTypeName.substring(arrayLevel), arrayLevel);
    }

    /**
     * Converts a JDoctor representation of a primitive type to a JavaParser
     * representation.
     *
     * @param jDoctorPrimitive a JDoctor primitive type name
     * @return the corresponding JavaParser primitive type name
     * @throws IllegalArgumentException if {@code jDoctorPrimitive} does not
     * match a known JDoctor primitive representation
     * @throws IllegalArgumentException if {@code jDoctorPrimitive} does not
     * represent a primitive type
     */
    private static String convertJDoctorPrimitiveToJavaParserPrimitive(String jDoctorPrimitive) {
        List<String> primitiveJDoctorValues = CommonPrimitiveType.getAllJDoctorPrimitiveTypeNames();
        if (primitiveJDoctorValues.contains(jDoctorPrimitive.replaceAll("[^a-zA-z]+", ""))) {
            // match `jDoctorPrimitive` to a known JDoctor primitive representation.
            String jDoctorRegex = CommonPrimitiveType.getJDoctorPrimitivesRegex();
            String regex = String.format(
                    "[^A-Za-z0-9_]*(%s)[^A-Za-z0-9_]*",
                    jDoctorRegex
            );
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(jDoctorPrimitive);
            if (matcher.find()) {
                String foundPrimitiveType = matcher.group(1);
                CommonPrimitiveType jDoctorCommonPrimitiveType = CommonPrimitiveType.convertTypeNameToCommonPrimitiveType(foundPrimitiveType);
                CommonPrimitiveType jpCommonPrimitiveType = CommonPrimitiveType.jDoctorToJP(jDoctorCommonPrimitiveType);
                return jDoctorPrimitive.replaceAll(jDoctorRegex, jpCommonPrimitiveType.getTypeName());
            } else {
                // `jDoctorPrimitive` does not match any known JDoctor representation.
                throw new IllegalArgumentException(String.format(
                        "The condition parameter does not match any primitive type: %s",
                        jDoctorPrimitive
                ));
            }
        }
        throw new IllegalArgumentException(String.format(
                "The string passed to the function (%s) does not represent a primitive type",
                jDoctorPrimitive
        ));
    }

    private static String convertConditionParameterType(String jDoctorTypeName) {
        jDoctorTypeName = removeSpuriousCharacters(jDoctorTypeName);
        // checks if the given JDoctor type name is a primitive type.
        List<String> primitiveJDoctorValues = CommonPrimitiveType.getAllJDoctorPrimitiveTypeNames();
        if (primitiveJDoctorValues.contains(jDoctorTypeName.replaceAll("[^a-zA-z]+", ""))) {
            jDoctorTypeName = convertJDoctorPrimitiveToJavaParserPrimitive(jDoctorTypeName);
        }
        // checks if the given JDoctor type name is an array type.
        if (jDoctorTypeName.startsWith("[")) {
            jDoctorTypeName = convertJDoctorArrayToJavaParserArray(jDoctorTypeName);
        }
        //
        List<String> paramPathList = getPathList(jDoctorTypeName);
        return getClassNameFromPathList(paramPathList);
    }

    public static String getClassNameFromPathList(
            List<String> pathList
    ) {
        return pathList.get(pathList.size() - 1);
    }

    public static String getExtendsType(String jpMethodString, String jpParamString) {
        // count dimension of array
        int howMany = 0;
        for (char c : jpParamString.toCharArray()) {
            if (c == '[') {
                howMany++;
            }
        }
        String regex = String.format("%s\\s+extends\\s+([A-Za-z0-9_]+)[<[A-Za-z0-9_,]+]*", jpParamString.replaceAll("\\[\\]",""));

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(jpMethodString);

        if (matcher.find()) {
            return removeSpuriousCharacters(matcher.group(1)) + "[]".repeat(howMany);
        } else {
            String errMsg = String.format(
                    "The jp method string %s does not match the regex built with jp param string %s",
                    jpMethodString,
                    jpParamString
            );
            throw new IllegalArgumentException(errMsg);
        }
    }

    /**
     *
     *
     * @param packageList
     * @return
     */
    public static String getPackageNameFromPackageList(
            List<String> packageList
    ) {
        return String.join(".", packageList);
    }

    /**
     *
     *
     * @param pathList
     * @return
     */
    public static List<String> getPackageList(
            List<String> pathList
    ) {
        return pathList.subList(0, pathList.size() - 1);
    }

    /**
     *
     *
     * @param path
     * @return
     */
    public static List<String> getPathList(
            String path
    ) {
        String regex = "[.$]";
        return Arrays.asList(path.split(regex));
    }

    /**
     * The method converts a list of JDoctor type names {@code jDoctorTypeNames} into a list of JavaParser type names.
     * For example, the JDoctor type name {@code [D} represents a list of doubles, and the corresponding type name in
     * JavaParser is {@code double[]}. The method apply these conversions from JDoctor type names to JavaParser type
     * names. See CommonPrimitiveType {@link CommonPrimitiveType} for all possible conversions.
     *
     * @param jDoctorTypeNames JDoctor type names to convert
     * @return a list of the corresponding JavaParser type names
     */
    public static List<String> convertJDoctorTypeNamesToJavaParserTypeNames(
            List<String> jDoctorTypeNames
    ) {
        return jDoctorTypeNames
                .stream()
                .map(JDoctorUtils::convertConditionParameterType)
                .collect(Collectors.toList());
    }

    /**
     *
     *
     * @param primaryType
     * @param jpCallable
     * @param jpTypeParam
     * @return
     */
    public static String getJPTypeName(
            TypeDeclaration<?> primaryType,
            CallableDeclaration<?> jpCallable,
            Parameter jpTypeParam
    ) {
        //
        String jpTypeName = removeSpuriousCharacters(jpTypeParam.getType().asString());
        //
        if (jpTypeParam.getType().isClassOrInterfaceType()) {
            jpTypeName = removeSpuriousCharacters(jpTypeParam.getType().asClassOrInterfaceType().getName().asString());
        }
        //
        if (hasJPTypeEllipsis(jpTypeParam.toString())) {
            jpTypeName = addArrayLevel(jpTypeName, 1);
        }
        //
        if (hasJPTypeExtends(jpCallable.getTokenRange().get().toString(), jpTypeName)) {
            jpTypeName = getExtendsType(jpCallable.getTokenRange().get().toString(), jpTypeName);
        }
        if (primaryType.isClassOrInterfaceDeclaration()) {
            ClassOrInterfaceDeclaration jpClass = primaryType.asClassOrInterfaceDeclaration();
            if (hasGenerics(jpClass)) {
                for (TypeParameter jpClassType : jpClass.getTypeParameters()) {
                    if (jpClassType.getNameAsString().equals(jpTypeName.replaceAll("\\[\\]",""))) {
                        if (hasJPTypeExtends(jpClassType.toString(), jpTypeName)) {
                            jpTypeName = getExtendsType(jpClassType.toString(), jpTypeName);
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
     * @param conditionType name of the JDoctor or JavaParser type
     * @return true iff the given type name is "Object" or "Comparable"
     */
    public static boolean isStandardType(String conditionType) {
        return conditionType.equals("Object") || conditionType.equals("Comparable");
    }

    /**
     * Checks if a given type name is a "standard" array. By definition, this
     * includes the "Object[]" and "Comparable[]" types.
     * See above method {@code isStandardType} for further elaboration.
     *
     * @param conditionType name of the JDoctor or JavaParser type
     * @return true iff the given type name is "Object[]" or "Comparable[]"
     */
    public static boolean isStandardTypeArray(String conditionType) {
        return conditionType.equals("Object[]") || conditionType.equals("Comparable[]");
    }

    /**
     * @param jpClass a JavaParser class or interface declaration
     * @return true iff the given class or interface uses generic types
     */
    private static boolean hasGenerics(ClassOrInterfaceDeclaration jpClass) {
        return jpClass.getTypeParameters().size() > 0;
    }

    /**
     * @param jpParamString name of a JavaParser parameter {@link Parameter}
     * @return true iff the parameter name includes "..."
     */
    public static boolean hasJPTypeEllipsis(String jpParamString) {
        return jpParamString.contains("...");
    }

    /**
     * Checks if a given method string has a type
     *
     * @param jpMethodString
     * @param jpParamString
     * @return
     */
    public static boolean hasJPTypeExtends(String jpMethodString, String jpParamString) {
        String regex = String.format("%s\\s+extends\\s+([A-Za-z0-9_]+)[<[A-Za-z0-9_,]+]*", jpParamString.replaceAll("\\[\\]",""));
        // Using the Pattern and Matcher classes
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(jpMethodString);
        return matcher.find();
    }

    /**
     * Removes any type arguments from a parameterized type name.
     *
     * @param dirtyType a JDoctor or JavaParser type name
     * @return the same type name without any type arguments
     */
    public static String removeSpuriousCharacters(String dirtyType) {
        String regex = "(\bsuper\b|\\s|\\?|;|<.*?>)";
        return dirtyType.replaceAll(regex, "");
    }
}
