package star.tratto.util.javaparser;

import com.github.javaparser.ast.nodeTypes.NodeWithSimpleName;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import star.tratto.identifiers.ConditionPrimitiveType;
import star.tratto.identifiers.JPType;

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

public class JDoctorUtils {
    /**
     * The method converts the list of JDoctor type names {@code jDoctorTypeNames} into a list of JavaParser type names.
     * For example, the JDoctor type name <span>[D</span> represents a list of doubles, and the corresponding type name in
     * JavaParser is <span>double[]</span>. The method apply these conversions from JDoctor type names to JavaParser type
     * names.
     *
     * @param jDoctorTypeNames the list of JDoctor type names to convert
     * @return the list of the corresponding JavaParser type names
     */
    public static List<String> convertJDoctorConditionTypeNames2JavaParserTypeNames(
            List<String> jDoctorTypeNames
    ) {
        return jDoctorTypeNames
                .stream()
                .map(JDoctorUtils::convertConditionParameterType)
                .collect(Collectors.toList());
    }

    public static String convertConditionToArrayType(String arrayType) {
        int howMany = 0;
        for (char c : arrayType.toCharArray()) {
            if (c == '[') {
                howMany++;
            } else {
                break;
            }
        }
        return convertToArrayType(arrayType.replaceAll("^\\[+",""), howMany);
    }

    public static String convertToArrayType(String arrayType, int howMany) {
        return arrayType + "[]".repeat(howMany);
    }

    public static String convertConditionParameterType(String conditionParameterType) {
        List<String> primitiveConditionsValues = ConditionPrimitiveType.getConditionValues();
        conditionParameterType = removeSpuriousCharacters(conditionParameterType);
        if (primitiveConditionsValues.contains(conditionParameterType.replaceAll("[^a-zA-Z]+", ""))) {
            conditionParameterType = convertToPrimitiveType(conditionParameterType);
        }
        if (conditionParameterType.startsWith("[")) {
            conditionParameterType = convertConditionToArrayType(conditionParameterType);
        }
        List<String> paramPathList = getPathList(conditionParameterType);
        return getClassNameFromPathList(paramPathList);
    }

    public static String convertToPrimitiveType(String primitiveType) {
        List<String> primitiveConditionsValues = ConditionPrimitiveType.getConditionValues();
        if (primitiveConditionsValues.contains(primitiveType.replaceAll("[^a-zA-Z]+", ""))) {
            String conditionPrimitiveRegex = ConditionPrimitiveType.getConditionRegexValues();
            String regex = String.format(
                    "[^A-Za-z0-9_]*(%s)[^A-Za-z0-9_]*",
                    conditionPrimitiveRegex
            );

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(primitiveType);

            if (matcher.find()) {
                String extractedPrimitiveType = matcher.group(1);
                ConditionPrimitiveType conditionParamEnum = ConditionPrimitiveType.convertValue(extractedPrimitiveType);
                ConditionPrimitiveType conditionParamConverted = ConditionPrimitiveType.condition2jp(conditionParamEnum);
                return primitiveType.replaceAll(conditionPrimitiveRegex, conditionParamConverted.getValue());
            } else {
                String errMsg = String.format(
                        "The condition parameter does not match any primitive type: %s",
                        primitiveType
                );
                throw new IllegalArgumentException(errMsg);
            }
        }
        String errMsg = String.format("The string passed to the function (%s) does not represent a primitive type", primitiveType);
        throw new IllegalArgumentException(errMsg);
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

    public static String getPackageNameFromPackageList(
            List<String> packageList
    ) {
        return String.join(".", packageList);
    }

    public static List<String> getPackageList(
            List<String> pathList
    ) {
        return pathList.subList(0, pathList.size() - 1);
    }

    public static List<String> getPathList(
            String path
    ) {
        String regex = "[.$]";
        return Arrays.asList(path.split(regex));
    }

    public static String getJPTypeName(
            TypeDeclaration<?> primaryType,
            CallableDeclaration<?> jpCallable,
            Parameter jpTypeParam
    ) {
        String jpTypeName = removeSpuriousCharacters(jpTypeParam.getType().asString());
        JPType jpMethodType = JPType.getJPTypeDeclaration(jpTypeParam.getType());

        if (jpMethodType == JPType.CLASS_OR_INTERFACE_TYPE) {
            jpTypeName = removeSpuriousCharacters(jpTypeParam.getType().asClassOrInterfaceType().getName().asString());
        }
        if (hasJPTypeEllipsis(jpTypeParam.toString())) {
            jpTypeName = convertToArrayType(jpTypeName, 1);
        }
        if (hasJPTypeExtends(jpCallable.getTokenRange().get().toString(), jpTypeName)) {
            jpTypeName = getExtendsType(jpCallable.getTokenRange().get().toString(), jpTypeName);
        }
        if (primaryType.isClassOrInterfaceDeclaration()) {
            ClassOrInterfaceDeclaration jpClass = primaryType.asClassOrInterfaceDeclaration();
            if (hasJPClassTypeGenerics(jpClass)) {
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

    public static boolean isGenericCondition(String conditionType) {
        return conditionType.equals("Object") || conditionType.equals("Comparable");
    }

    public static boolean isGenericConditionArray(String conditionType) {
        return conditionType.equals("Object[]") || conditionType.equals("Comparable[]");
    }

    public static boolean hasJPClassTypeGenerics(ClassOrInterfaceDeclaration jpClass) {
        return jpClass.getTypeParameters().size() > 0;
    }

    public static boolean hasJPTypeEllipsis(String jpParamString) {
        return jpParamString.contains("...");
    }

    public static boolean hasJPTypeExtends(String jpMethodString, String jpParamString) {
        String regex = String.format("%s\\s+extends\\s+([A-Za-z0-9_]+)[<[A-Za-z0-9_,]+]*", jpParamString.replaceAll("\\[\\]",""));
        // Using the Pattern and Matcher classes
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(jpMethodString);
        return matcher.find();
    }

    public static String removeSpuriousCharacters(String dirtyType) {
        String regex = "(super|\\s|\\?|;|<.*?>)";
        return dirtyType.replaceAll(regex, "");
    }
}
