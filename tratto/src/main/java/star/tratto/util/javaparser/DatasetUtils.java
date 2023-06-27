package star.tratto.util.javaparser;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.javadoc.JavadocBlockTag;
import com.github.javaparser.resolution.MethodUsage;
import com.github.javaparser.resolution.UnsolvedSymbolException;
import com.github.javaparser.resolution.declarations.ResolvedFieldDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.resolution.types.ResolvedType;
import com.github.javaparser.symbolsolver.javaparsermodel.declarations.JavaParserFieldDeclaration;
import org.javatuples.Pair;
import org.javatuples.Quartet;
import org.javatuples.Quintet;
import org.javatuples.Triplet;
import star.tratto.data.OracleType;
import star.tratto.dataset.oracles.JDoctorCondition.*;
import star.tratto.exceptions.JPClassNotFoundException;
import star.tratto.exceptions.PackageDeclarationNotFoundException;
import star.tratto.exceptions.ResolvedTypeNotFound;
import star.tratto.identifiers.JPCallableType;
import star.tratto.identifiers.Javadoc;
import star.tratto.identifiers.file.*;
import star.tratto.identifiers.path.Path;
import star.tratto.oraclegrammar.custom.Parser;
import star.tratto.oraclegrammar.custom.Splitter;
import star.tratto.util.FileUtils;

import java.io.File;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DatasetUtils {
    /**
     * The method removes all the duplicates from a list.
     *
     * @param list The list from which remove the duplicates.
     * @return A new list that does not contain any duplicates elements.
     * @param <T> The generic type of the list.
     */
    public static <T> List<T> removeDuplicates(List<T> list) {
        Set<T> set = new LinkedHashSet<>(list);
        return new ArrayList<>(set);
    }

    /**
     * Gets a list of each class name, and corresponding package, name for all
     * classes in a compilation unit.
     *
     * @param cu the compilation unit of a java file.
     * @return a list of (className, packageName) pairs.
     * @throws PackageDeclarationNotFoundException if the package cannot be
     * retrieved.
     */
    private static List<Pair<String, String>> getClassNameAndPackage(
            CompilationUnit cu
    ) throws PackageDeclarationNotFoundException {
        List<Pair<String, String>> classList = new ArrayList<>();
        String packageName = JavaParserUtils.getPackageDeclarationFromCompilationUnit(cu).getNameAsString();
        // iterate through each class in the compilation unit.
        List<TypeDeclaration<?>> jpClasses = cu.getTypes();
        for (TypeDeclaration<?> jpClass : jpClasses) {
            classList.add(new Pair<>(jpClass.getNameAsString(), packageName));
        }
        return classList;
    }

    /**
     * Gets the JavaDoc comment of a body declaration using regex patterns.
     * Use ONLY IF JavaDoc comment is not recoverable using JavaParser API.
     *
     * @param jpBody a member in a java class {@link BodyDeclaration}.
     * @return the matched JavaDoc comment (empty string if not found).
     */
    private static String getJavadocByPattern(BodyDeclaration<?> jpBody) {
        String input = jpBody.toString();
        Pattern pattern = Pattern.compile("/\\*\\*(.*?)\\*/", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            String content = matcher.group(1);
            // change prefix/suffix depending on the type of the member.
            if (jpBody instanceof TypeDeclaration<?>) {
                return Javadoc.CLASS_PREFIX.getValue() + content + Javadoc.CLASS_SUFFIX.getValue();
            } else {
                return Javadoc.METHOD_PREFIX.getValue() + content + Javadoc.METHOD_SUFFIX.getValue();
            }
        }
        return "";
    }

    /**
     * Gets the Javadoc comment of a class {@link TypeDeclaration}.
     *
     * @param jpClass a JavaParser class {@link TypeDeclaration}.
     * @return a string representation of the Javadoc comment.
     */
    public static String getClassJavadoc(
            TypeDeclaration<?> jpClass
    ) {
        Optional<JavadocComment> optionalJavadocComment = jpClass.getJavadocComment();
        if (optionalJavadocComment.isEmpty()) return getJavadocByPattern(jpClass);
        return Javadoc.CLASS_PREFIX.getValue() + optionalJavadocComment.get().getContent() + Javadoc.CLASS_SUFFIX.getValue();
    }

    /**
     * Gets the Javadoc comment of a function {@link CallableDeclaration}.
     *
     * @param jpCallable a JavaParser function {@link CallableDeclaration}.
     * @return a string representation the Javadoc comment.
     */
    public static String getCallableJavadoc(
            CallableDeclaration<?> jpCallable
    ) {
        Optional<JavadocComment> optionalJavadocComment = jpCallable.getJavadocComment();
        if (optionalJavadocComment.isEmpty()) return getJavadocByPattern(jpCallable);
        return Javadoc.METHOD_PREFIX.getValue() + optionalJavadocComment.get().getContent() + Javadoc.METHOD_SUFFIX.getValue();
    }

    /**
     * Gets all numeric values in a JavaDoc comment represented as a pair of
     * strings.
     *
     * @param javadocComment The string representation of a JavaDoc comment.
     * @return A list of pairs of strings representing all numeric values
     * in the JavaDoc comment. The first element is the numeric value, and the
     * second element is the type of numeric value ("int" or "double").
     */
    private static List<Pair<String, String>> findAllNumericValuesInJavadoc(
            String javadocComment
    ) {
        // Defines regex to find integers and doubles within a string.
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        Matcher matcher = pattern.matcher(javadocComment);
        // Iterate through all occurrences.
        List<Pair<String, String>> numericValues = new ArrayList<>();
        while (matcher.find()) {
            String match = matcher.group();
            if (match.contains(".")) {
                // double (decimal).
                try {
                    double realValue = Double.parseDouble(match);
                    numericValues.add(new Pair<>(Double.toString(realValue), "double"));
                } catch (Exception e) {
                    System.err.printf("Number exceed maximum float value: %s%n", match);
                }
            } else {
                // integer (no decimal).
                try {
                    long longIntValue = Long.parseLong(match);
                    numericValues.add(new Pair<>(Long.toString(longIntValue), "int"));
                } catch (NumberFormatException e) {
                    System.err.printf("Number exceed maximum integer value: %s", match);
                }
            }
        }
        return numericValues;
    }

    /**
     * Gets all string values in a JavaDoc comment represented as a pair of
     * strings. The second value may seem redundant, but is added for
     * consistency with the numeric JavaDoc values.
     *
     * @param jpJavadoc The string representation of a JavaDoc comment.
     * @return A list of pairs of strings representing the string values in
     * the JavaDoc comment. The first element is the string value, and the
     * second element is the type of value (always "String").
     */
    private static List<Pair<String, String>> findAllStringValuesInJavadoc(
            String jpJavadoc
    ) {
        // Defines regex to match values within a string.
        Pattern pattern = Pattern.compile("\\\"(.*?)\\\"|\\\'(.*?)\\\'");
        Matcher matcher = pattern.matcher(jpJavadoc);
        // Iterate through all occurrences.
        List<Pair<String, String>> stringValues = new ArrayList<>();
        while (matcher.find()) {
            String match = String.format("\"%s\"",!(matcher.group(1) == null) ? matcher.group(1) : matcher.group(2));
            stringValues.add(new Pair<>(match, "String"));
        }
        return stringValues;
    }

    /**
     * Gets all numerical and string values from a given JavaDoc comment via
     * pattern matching.
     *
     * @param jpJavadoc the JavaDoc comment.
     * @return a list of values describing each numerical and string value.
     * Each entry has the form:
     *  [value, valueType]
     * For example: [["name", "String"], ["64", "int"]]
     */
    public static List<Pair<String, String>> getJavadocValues(
            String jpJavadoc
    ) {
        List<Pair<String, String>> pairList = new ArrayList<>();
        pairList.addAll(findAllNumericValuesInJavadoc(jpJavadoc));
        pairList.addAll(findAllStringValuesInJavadoc(jpJavadoc));
        return pairList;
    }

    /**
     * Gets the class name of a given parameter type. Handles generics,
     * primitives, arrays, and reference types.
     *
     * @param jpClass the class declaring the method.
     * @param jpCallable the method containing the type.
     * @param jpParameter the given type.
     * @return the type name of the given parameter.
     */
    private static Optional<String> getParameterTypeName(
            TypeDeclaration<?> jpClass,
            CallableDeclaration<?> jpCallable,
            Parameter jpParameter
    ) {
        Type jpParameterType = jpParameter.getType();
        boolean hasEllipsis = JDoctorUtils.hasJPTypeEllipsis(jpParameter.toString());
        try {
            ResolvedType jpResolvedParameterType = jpParameterType.resolve();
            String className = "";
            // get base type.
            if (jpResolvedParameterType.isTypeVariable()) {
                className = jpParameterType.asClassOrInterfaceType().getNameAsString();
            } else if (jpResolvedParameterType.isPrimitive()) {
                className = jpParameterType.asPrimitiveType().asString();
            } else if (jpResolvedParameterType.isReferenceType()) {
                // if object is generic, use generic name.
                if (JavaParserUtils.isGenericType(jpResolvedParameterType)) {
                    className = JDoctorUtils.getJPTypeName(jpClass, jpCallable, jpParameter);
                } else {
                    className = JavaParserUtils.getTypeWithoutPackages(jpResolvedParameterType.asReferenceType().getQualifiedName());
                }
            } else if (jpResolvedParameterType.isArray()) {
                // special case: return early if type is an array to avoid redundant brackets.
                return Optional.of(JavaParserUtils.getTypeWithoutPackages(jpResolvedParameterType.asArrayType().describe()));
            } else {
                // unknown type.
                assert false;
                String errMsg = String.format("Unexpected type when evaluating %s parameter type.", jpParameterType);
                System.err.println(errMsg);
            }
            // check if type is an array.
            if (hasEllipsis) {
                className += "[]";
            }
            // return class name.
            return Optional.of(className);
        } catch (UnsolvedSymbolException e) {
            String errMsg = String.format("UnsolvedSymbolException when evaluating %s parameter type.", jpParameterType);
            System.err.println(errMsg);
            String className = jpParameterType.asClassOrInterfaceType().getNameAsString();
            if (hasEllipsis) {
                className += "[]";
            }
            return Optional.of(className);
        }
    }

    /**
     * Collects information about each argument of a given method.
     *
     * @param jpClass the class in which the method is declared.
     * @param jpCallable the method being analyzed.
     * @return a list of information about each argument. Each entry has the
     * form:
     *  [parameterName, packageName, parameterTypeName]
     * where "packageName" refers to the package of the parameter type (empty
     * if the parameter is not a reference type).
     */
    public static List<Triplet<String, String, String>> getTokensMethodArguments(
            TypeDeclaration<?> jpClass,
            CallableDeclaration<?> jpCallable
    ) {
        List<Triplet<String, String, String>> argumentList = new ArrayList<>();
        List<Parameter> jpParameters = jpCallable.getParameters();
        // iterate through each parameter in the method arguments.
        for (Parameter jpParameter : jpParameters) {
            Type jpParameterType = jpParameter.getType();
            Optional<String> jpParameterClassName = getParameterTypeName(jpClass, jpCallable, jpParameter);
            if (jpParameterClassName.isPresent()) {
                try {
                    if (
                            jpParameterType.resolve().isTypeVariable() ||
                            jpParameterType.resolve().isPrimitive() ||
                            jpParameterType.resolve().isArray()
                    ) {
                        // if not a reference type, ignore package name (e.g. primitives do not have packages).
                        argumentList.add(new Triplet<>(jpParameter.getNameAsString(), "", jpParameterClassName.get()));
                    } else if (jpParameterType.resolve().isReferenceType()) {
                        String typeName = JDoctorUtils.getJPTypeName(jpClass, jpCallable, jpParameter);
                        if (JavaParserUtils.isGenericType(jpParameterType.resolve())) {
                            // if reference object is a generic type, ignore package name.
                            argumentList.add(new Triplet<>(jpParameter.getNameAsString(), "", typeName));
                        } else {
                            // otherwise, retrieve necessary package information.
                            String fullyQualifiedName = jpParameterType.resolve().asReferenceType().getQualifiedName();
                            String className = JavaParserUtils.getTypeWithoutPackages(fullyQualifiedName);
                            String parameterPackageName = fullyQualifiedName
                                    .replace(String.format(".%s", className), "");
                            argumentList.add(new Triplet<>(
                                    jpParameter.getNameAsString(),
                                    parameterPackageName,
                                    jpParameterClassName.get()
                            ));
                        }
                    }
                } catch (UnsolvedSymbolException e) {
                    String errMsg = String.format("Unable to generate triplet for argument %s.", jpParameterType);
                    System.err.println(errMsg);
                }
            }
        }
        return argumentList;
    }

    /**
     * Gets the source code of the CallableDeclaration.
     *
     * @param jpCallable a method or constructor.
     * @return a string representation of the callable source code.
     */
    public static String getCallableSourceCode(
            CallableDeclaration<?> jpCallable
    ) {
        JPCallableType jpCallableType = jpCallable.isConstructorDeclaration() ? JPCallableType.CONSTRUCTOR : JPCallableType.METHOD;
        String jpSignature = JavaParserUtils.getCallableSignature(jpCallable, jpCallableType);
        Optional<BlockStmt> jpBody = (jpCallableType == JPCallableType.CONSTRUCTOR) ?
                Optional.ofNullable(((ConstructorDeclaration) jpCallable).getBody()) :
                ((MethodDeclaration) jpCallable).getBody();
        return jpSignature + (jpBody.isEmpty() ? ";" : jpBody.get().toString());
    }

    /**
     * Gets all non-private, non-static, non-void methods in a given compilation unit.
     */
    private static List<Quartet<String, String, String, String>> getNonPrivateStaticNonVoidMethods(
            CompilationUnit cu
    ) throws PackageDeclarationNotFoundException {
        List<Quartet<String, String, String, String>> methodList = new ArrayList<>();
        // get package name.
        String packageName = JavaParserUtils.getPackageDeclarationFromCompilationUnit(cu).getNameAsString();
        // iterate over each class in the compilation unit.
        List<TypeDeclaration<?>> jpClasses = cu.getTypes();
        for (TypeDeclaration<?> jpClass : jpClasses) {
            String className = jpClass.getNameAsString();
            List<MethodDeclaration> jpMethods = jpClass.findAll(MethodDeclaration.class);
            for (MethodDeclaration jpMethod : jpMethods) {
                if (!jpMethod.isPrivate() && jpMethod.isStatic() && !jpMethod.getType().isVoidType()) {
                    methodList.add(new Quartet<>(
                            jpMethod.getNameAsString(),
                            packageName,
                            className,
                            JavaParserUtils.getMethodSignature(jpMethod)
                    ));
                }
            }
        }
        return methodList;
    }

    /**
     * Gets all non-private, non-static attributes in a given compilation unit.
     */
    private static List<Quartet<String, String, String, String>> getNonPrivateStaticAttributes(
            CompilationUnit cu
    ) throws PackageDeclarationNotFoundException {
        List<Quartet<String, String, String, String>> attributeList = new ArrayList<>();
        // get package name.
        String packageName = JavaParserUtils.getPackageDeclarationFromCompilationUnit(cu).getNameAsString();
        // get all classes in compilation unit.
        List<TypeDeclaration<?>> jpClasses = cu.getTypes();
        // iterate over all classes.
        for (TypeDeclaration<?> jpClass : jpClasses) {
            String className = jpClass.getNameAsString();
            List<FieldDeclaration> jpFields = jpClass.findAll(FieldDeclaration.class);
            // add all non-private, static attributes.
            for (FieldDeclaration jpField : jpFields) {
                // check if field declaration is non-private and static.
                if (!jpField.isPrivate() && jpField.isStatic()) {
                    // add each variable in declaration.
                    for (VariableDeclarator jpVariable : jpField.getVariables()) {
                        attributeList.add(new Quartet<>(
                                jpVariable.getNameAsString(),
                                packageName,
                                className,
                                JavaParserUtils.getVariableSignature(jpField, jpVariable)
                        ));
                    }
                }
            }
        }
        return attributeList;
    }

    /**
     * Gets all JavaDoc tags in a given compilation unit.
     */
    private static List<Quintet<TypeDeclaration<?>, CallableDeclaration<?>, OracleType, String, String>> getCuTags(
            CompilationUnit cu
    ) throws PackageDeclarationNotFoundException {
        List<Quintet<TypeDeclaration<?>, CallableDeclaration<?>, OracleType, String, String>> tagList = new ArrayList<>();
        // iterate through each class.
        List<TypeDeclaration<?>> jpClasses = cu.getTypes();
        for (TypeDeclaration<?> jpClass : jpClasses) {
            // iterate through each method.
            List<CallableDeclaration<?>> jpCallables = new ArrayList<>();
            jpCallables.addAll(jpClass.getMethods());
            jpCallables.addAll(jpClass.getConstructors());
            for (CallableDeclaration<?> jpCallable : jpCallables) {
                // iterate through each JavaDoc tag.
                Optional<com.github.javaparser.javadoc.Javadoc> optionalJavadoc = jpCallable.getJavadoc();
                if (optionalJavadoc.isPresent()) {
                    List<JavadocBlockTag> blockTags = optionalJavadoc.get().getBlockTags();
                    for (JavadocBlockTag blockTag : blockTags) {
                        // get info for each JavaDoc tag.
                        String name = blockTag.getName().orElse("");
                        String content = blockTag.getContent().toText();
                        OracleType oracleType = switch (blockTag.getTagName()) {
                            case "param" -> OracleType.PRE;
                            case "return" -> OracleType.NORMAL_POST;
                            case "throws", "exception" -> OracleType.EXCEPT_POST;
                            default -> null;
                        };
                        if (oracleType == null) continue;
                        // add new tag.
                        tagList.add(new Quintet<>(
                                jpClass,
                                jpCallable,
                                oracleType,
                                name,
                                content
                        ));
                    }
                }
            }
        }
        return tagList;
    }

    /**
     * Finds all ".java" files in a given directory. Files are filtered based
     * on a list of files to ignore (see dataset/repos/ignoreFile.json).
     *
     * @param sourcePath the path to the project root directory.
     * @return a list of all valid files {@link File}.
     */
    private static List<File> getValidJavaFiles(String sourcePath) {
        // get all java files from source.
        File sourceDir = new File(sourcePath);
        List<File> allFiles = FileUtils.extractJavaFilesFromDirectory(sourceDir);
        // get list of files to ignore.
        String ignoreFilePath = Paths.get(
                Path.REPOS.getValue(),
                FileName.IGNORE_FILE.getValue() + FileFormat.JSON.getValue()
        ).toString();
        List<String> ignoreFileList = FileUtils.readJSONList(ignoreFilePath)
                .stream()
                .map(e -> (String) e)
                .toList();
        // filter files.
        return allFiles
                .stream()
                .filter(file -> {
                    String filename = file.getName().replace(FileFormat.JAVA.getValue(), "");
                    return !ignoreFileList.contains(filename);
                })
                .toList();
    }

    /**
     * Gets all classes in a project from a given source path.
     *
     * @param sourcePath the path of the project root directory.
     * @return a list of information describing each method.
     */
    public static List<Pair<String, String>> getProjectClassesTokens(
            String sourcePath
    ) {
        List<Pair<String, String>> projectClasses = new ArrayList<>();
        List<File> javaFiles = getValidJavaFiles(sourcePath);
        // iterate through each file and add class tokens.
        for (File javaFile : javaFiles) {
            String filePath = javaFile.getAbsolutePath();
            Optional<CompilationUnit> cu = JavaParserUtils.getCompilationUnitFromFilePath(filePath);
            if (cu.isPresent()) {
                try {
                    projectClasses.addAll(getClassNameAndPackage(cu.get()));
                } catch (PackageDeclarationNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return projectClasses;
    }

    /**
     * Finds all methods in a project from a given source path.
     *
     * @param sourcePath the path of the project root directory.
     * @return a list of information describing each method.
     */
    public static List<Quartet<String, String, String, String>> getProjectNonPrivateStaticNonVoidMethodsTokens(
            String sourcePath
    ) {
        List<Quartet<String, String, String, String>> projectMethods = new ArrayList<>();
        List<File> javaFiles = getValidJavaFiles(sourcePath);
        // iterate through each file and add method tokens.
        for (File javaFile : javaFiles) {
            String filePath = javaFile.getAbsolutePath();
            Optional<CompilationUnit> cu = JavaParserUtils.getCompilationUnitFromFilePath(filePath);
            if (cu.isPresent()) {
                try {
                    projectMethods.addAll(getNonPrivateStaticNonVoidMethods(cu.get()));
                } catch (PackageDeclarationNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return projectMethods;
    }

    /**
     * Finds all attributes in a project from a given source path.
     *
     * @param sourcePath the path of the project root directory.
     * @return a list of information describing each attribute.
     */
    public static List<Quartet<String, String, String, String>> getProjectNonPrivateStaticAttributesTokens(
            String sourcePath
    ) {
        List<Quartet<String, String, String, String>> attributeList = new ArrayList<>();
        List<File> javaFiles = getValidJavaFiles(sourcePath);
        // iterate through each file and add attribute tokens.
        for (File javaFile : javaFiles) {
            String filePath = javaFile.getAbsolutePath();
            Optional<CompilationUnit> cu = JavaParserUtils.getCompilationUnitFromFilePath(filePath);
            if (cu.isPresent()) {
                try {
                    attributeList.addAll(getNonPrivateStaticAttributes(cu.get()));
                } catch (PackageDeclarationNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return attributeList;
    }

    /**
     * Finds all JavaDoc tags of a project from a given source path.
     *
     * @param sourcePath the path to the project root directory.
     * @return a list of information describing each JavaDoc tag.
     */
    public static List<Quintet<TypeDeclaration<?>, CallableDeclaration<?>, OracleType, String, String>> getProjectTagsTokens(
            String sourcePath
    ) {
        List<Quintet<TypeDeclaration<?>, CallableDeclaration<?>, OracleType, String, String>> tagList = new ArrayList<>();
        List<File> javaFiles = getValidJavaFiles(sourcePath);
        // iterate through each file and add JavaDoc tags.
        for (File javaFile : javaFiles) {
            String filePath = javaFile.getAbsolutePath();
            Optional<CompilationUnit> cu = JavaParserUtils.getCompilationUnitFromFilePath(filePath);
            if (cu.isPresent()) {
                try {
                    tagList.addAll(getCuTags(cu.get()));
                } catch (PackageDeclarationNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return tagList;
    }

    /**
     * Converts a list of method usages {@link MethodUsage} to a quartet
     * of strings where each entry has the form:
     *  [methodName, packageName, className, methodSignature]
     * where class name refers to the class in which the method is declared.
     * "methodSignature" includes access specifiers, non-access modifiers,
     * generic type parameters, return type, method signature, parameters,
     * and exceptions.
     */
    private static List<Quartet<String, String, String, String>> convertMethodUsageToQuartet(
            List<MethodUsage> jpMethods
    ) {
        return new ArrayList<>(jpMethods)
                .stream()
                .map(jpMethod -> new Quartet<>(
                        jpMethod.getName(),
                        jpMethod.declaringType().getPackageName(),
                        jpMethod.declaringType().getClassName(),
                        JavaParserUtils.getMethodSignature(jpMethod)
                ))
                .toList();
    }

    /**
     * Gets a list of information for all methods visible to a given type.
     * Handles three cases: simple base type, generic type, and array type.
     *
     * @param jpType the given type.
     * @return a list of information for all methods accessible to the
     * given type. Each entry has the form:
     *  [methodName, className, packageName, methodSignature]
     */
    public static List<Quartet<String, String, String, String>> getMethodsFromType(
            ResolvedType jpType
    ) {
        List<Quartet<String, String, String, String>> methodList = new ArrayList<>();
        if (jpType.isArray()) {
            // handle array type.
            String arraysMethodJsonPath = Paths.get(
                    Path.REPOS.getValue(),
                    FileName.ARRAY_METHODS.getValue() + FileFormat.JSON.getValue()
            ).toString();
            List<List<String>> arrayMethods = FileUtils.readJSONList(arraysMethodJsonPath)
                    .stream()
                    .map(e -> ((List<?>) e)
                            .stream()
                            .map(o -> (String) o)
                            .toList())
                    .toList();
            methodList.addAll(arrayMethods
                    .stream()
                    .map(m -> new Quartet<>(m.get(0), "", jpType.describe(), m.get(1)))
                    .toList());
        } else if (JavaParserUtils.isGenericType(jpType)) {
            // handle generic type.
            List<MethodUsage> genericMethods = JavaParserUtils.getGenericType().asReferenceType().getAllMethods()
                    .stream()
                    .map(MethodUsage::new)
                    .filter(JavaParserUtils::isNonPrivateNonStaticNonVoidMethod)
                    .toList();
            methodList.addAll(convertMethodUsageToQuartet(genericMethods));
        } else if (jpType.isReferenceType()) {
            // handle base type.
            Optional<ResolvedReferenceTypeDeclaration> jpTypeDeclaration = jpType.asReferenceType().getTypeDeclaration();
            if (jpTypeDeclaration.isPresent()) {
                List<MethodUsage> allMethods = jpTypeDeclaration.get().getAllMethods()
                        .stream()
                        .filter(JavaParserUtils::isNonPrivateNonStaticNonVoidMethod)
                        .toList();
                methodList.addAll(convertMethodUsageToQuartet(allMethods));
            }
        }
        return methodList;
    }

    /**
     * Converts a list of resolved field declarations
     * {@link ResolvedFieldDeclaration} into a quartet of the form:
     *      "fieldName, packageOfField, classOfField, fieldSignature"
     *
     * @param resolvedFields a list of fields {@link ResolvedFieldDeclaration}
     * @return the list of quartets of fields
     */
    private static List<Quartet<String, String, String, String>> convertFieldDeclarationToQuartet(
            List<ResolvedFieldDeclaration> resolvedFields
    ) {
        List<Quartet<String, String, String, String>> quartetList = new ArrayList<>();
        for (ResolvedFieldDeclaration resolvedField : resolvedFields) {
            if (resolvedField instanceof JavaParserFieldDeclaration) {
                FieldDeclaration jpField = ((JavaParserFieldDeclaration) resolvedField).getWrappedNode();
                for (VariableDeclarator jpVariable : jpField.getVariables()) {
                    quartetList.add(new Quartet<>(
                            jpVariable.getNameAsString(),
                            resolvedField.declaringType().getPackageName(),
                            resolvedField.declaringType().getClassName(),
                            JavaParserUtils.getVariableSignature(jpField, jpVariable)
                    ));
                }
            } else {
                quartetList.add(new Quartet<>(
                        resolvedField.getName(),
                        resolvedField.declaringType().getPackageName(),
                        resolvedField.declaringType().getClassName(),
                        JavaParserUtils.getFieldSignature(resolvedField)
                ));
            }
        }
        return quartetList;
    }

    /**
     * Gets all non-private, non-static attributes visible to a given type.
     *
     * @param jpResolvedType a resolved JavaParser type {@link ResolvedType}.
     * @return a list of non-private, non-static attributes.
     */
    public static List<Quartet<String, String, String, String>> getFieldsFromType(
            ResolvedType jpResolvedType
    ) {
        List<Quartet<String, String, String, String>> fieldList = new ArrayList<>();
        if (jpResolvedType.isArray()) {
            // add array field (length).
            fieldList.add(new Quartet<>(
                    "length",
                    "",
                    jpResolvedType.describe(),
                    "public final int length;"
            ));
        } else if (jpResolvedType.isReferenceType()) {
            // add all fields accessible to the class.
            Optional<ResolvedReferenceTypeDeclaration> jpTypeDeclaration = jpResolvedType.asReferenceType().getTypeDeclaration();
            if (jpTypeDeclaration.isPresent()) {
                List<ResolvedFieldDeclaration> jpResolvedFields = jpTypeDeclaration.get().getAllFields()
                        .stream()
                        .filter(JavaParserUtils::isNonPrivateNonStaticAttribute)
                        .toList();
                fieldList.addAll(convertFieldDeclarationToQuartet(jpResolvedFields));
            } else {
                // unable to recover type declaration.
                System.err.printf(
                        "Unable to analyze the resolved type %s: " +
                        "resolved type declaration not found.", jpResolvedType
                );
            }
        } else if (!(jpResolvedType.isPrimitive() || jpResolvedType.isVoid() || jpResolvedType.isTypeVariable())) {
            // unknown type.
            System.err.printf(
                    "Return type %s different from ReferenceType, PrimitiveType, " +
                    "ArrayType, TypeVariable, and VoidType not yet supported%n", jpResolvedType
            );
        }
        return fieldList;
    }

    /**
     * Gets all non-private, non-static, non-void functions visible to the
     * class of a given function, the arguments of the function, and the
     * function return type.
     *
     * @param jpClass the class where the function is defined
     * @param jpCallable the function under analysis
     * @return a list of quartets of strings representing the non-private,
     * non-static, non-void methods
     */
    public static List<Quartet<String, String, String, String>> getTokensMethodVariablesNonPrivateNonStaticNonVoidMethods(
            TypeDeclaration<?> jpClass,
            CallableDeclaration<?> jpCallable
    ) throws JPClassNotFoundException {
        // add all methods of the base class (receiverObjectID -> this).
        List<MethodUsage> allReceiverMethods = JavaParserUtils.getAllAvailableMethodUsages(jpClass)
                .stream()
                .filter(JavaParserUtils::isNonPrivateNonStaticNonVoidMethod)
                .toList();
        List<Quartet<String, String, String, String>> methodList = new ArrayList<>(convertMethodUsageToQuartet(allReceiverMethods));
        // add all methods of parameters.
        for (Parameter jpParam : jpCallable.getParameters()) {
            methodList.addAll(getMethodsFromType(jpParam.getType().resolve()));
        }
        // add all methods of return type.
        if (jpCallable instanceof MethodDeclaration) {
            methodList.addAll(getMethodsFromType(((MethodDeclaration) jpCallable).getType().resolve()));
        }
        // add Object methods.
        methodList.addAll(convertMethodUsageToQuartet(
                JavaParserUtils.getGenericType().asReferenceType().getAllMethods()
                        .stream()
                        .map(MethodUsage::new)
                        .filter(JavaParserUtils::isNonPrivateNonStaticNonVoidMethod)
                        .toList()
        ));
        return removeDuplicates(methodList);
    }

    /**
     * Gets all non-private, non-static attributes visible to the class of a
     * given function, the arguments of the function, and the function return
     * type.
     *
     * @param jpClass the class where the function is defined
     * @param jpCallable the function under analysis
     * @return a list of quartets of strings representing the non-private,
     * non-static attributes
     */
    public static List<Quartet<String, String, String, String>> getTokensMethodVariablesNonPrivateNonStaticAttributes(
            TypeDeclaration<?> jpClass,
            CallableDeclaration<?> jpCallable
    ) throws JPClassNotFoundException {
        // add all fields of the base class (receiverObjectID -> this).
        List<ResolvedFieldDeclaration> jpReceiverFields = JavaParserUtils.getAllAvailableResolvedFields(jpClass)
                .stream()
                .filter(JavaParserUtils::isNonPrivateNonStaticAttribute)
                .toList();
        List<Quartet<String, String, String, String>> attributeList = new ArrayList<>(convertFieldDeclarationToQuartet(jpReceiverFields));
        // add all fields of parameters.
        for (Parameter jpParam : jpCallable.getParameters()) {
            attributeList.addAll(getFieldsFromType(jpParam.getType().resolve()));
        }
        // add all fields of return type.
        if (jpCallable instanceof MethodDeclaration) {
            attributeList.addAll(getFieldsFromType(((MethodDeclaration) jpCallable).getType().resolve()));
        }
        return removeDuplicates(attributeList);
    }

    /**
     * Gets all non-private, non-static, non-void methods visible to the
     * return types of each sub-expression in an oracle.
     *
     * @param jpClass the class where the function is defined
     * @param jpCallable the function under analysis
     * @param methodArgs the arguments of the function under analysis
     * @param oracle the oracle defined on the function
     * @return a list of quartets of strings representing the non-private,
     * non-static, non-void methods
     */
    public static List<Quartet<String, String, String, String>> getTokensOracleVariablesNonPrivateNonStaticNonVoidMethods(
            TypeDeclaration<?> jpClass,
            CallableDeclaration<?> jpCallable,
            List<Triplet<String, String, String>> methodArgs,
            String oracle
    ) {
        List<Quartet<String, String, String, String>> methodList = new ArrayList<>();
        List<LinkedList<String>> oracleSubexpressions = Parser.getInstance().getAllMethodsAndAttributes(oracle)
                .stream()
                .map(e -> new LinkedList<>(Splitter.split(e)))
                .toList();
        for (LinkedList<String> oracleSubexpression : oracleSubexpressions) {
            String subexpression = String.join("", oracleSubexpression).replaceAll("receiverObjectID", "this");
            try {
                ResolvedType jpType = JavaParserUtils.getResolvedTypeOfExpression(
                        jpClass,
                        jpCallable,
                        methodArgs,
                        subexpression
                );
                methodList.addAll(getMethodsFromType(jpType));
            } catch (UnsolvedSymbolException | ResolvedTypeNotFound e) {
                ResolvedType fieldType = JavaParserUtils.getGenericType();
                // Get all methods from Object
                List<MethodUsage> jpReturnTypeMethods = fieldType.asReferenceType().getAllMethods()
                        .stream()
                        .map(MethodUsage::new)
                        .toList();
                methodList.addAll(convertMethodUsageToQuartet(jpReturnTypeMethods));
            }
        }
        // add Object methods.
        methodList.addAll(convertMethodUsageToQuartet(
                JavaParserUtils.getGenericType().asReferenceType().getAllMethods()
                        .stream()
                        .map(MethodUsage::new)
                        .filter(JavaParserUtils::isNonPrivateNonStaticNonVoidMethod)
                        .toList()
        ));
        return removeDuplicates(methodList);
    }

    /**
     * Gets all non-private, non-static attributes visible to the return types
     * of each sub-expression in an oracle.
     *
     * @param jpClass the class where the function is defined
     * @param jpCallable the function under analysis
     * @param methodArgs the arguments of the function under analysis
     * @param oracle the oracle defined on the function
     * @return a list of quartets of strings representing the non-private
     * non-static attributes
     */
    public static List<Quartet<String, String, String, String>> getTokensOracleVariablesNonPrivateNonStaticAttributes(
            TypeDeclaration<?> jpClass,
            CallableDeclaration<?> jpCallable,
            List<Triplet<String, String, String>> methodArgs,
            String oracle
    ) {
        List<Quartet<String, String, String, String>> attributeList = new ArrayList<>();
        List<LinkedList<String>> oracleSubexpressions = Parser.getInstance().getAllMethodsAndAttributes(oracle)
                .stream()
                .map(e -> new LinkedList<>(Splitter.split(e)))
                .toList();
        for (LinkedList<String> oracleSubexpression : oracleSubexpressions) {
            String subexpression = String.join("", oracleSubexpression).replaceAll("receiverObjectID", "this");
            try {
                ResolvedType jpType = JavaParserUtils.getResolvedTypeOfExpression(
                        jpClass,
                        jpCallable,
                        methodArgs,
                        subexpression
                );
                attributeList.addAll(getFieldsFromType(jpType));
            } catch (UnsolvedSymbolException | ResolvedTypeNotFound e) {
                ResolvedType jpType = JavaParserUtils.getGenericType();
                attributeList.addAll(getFieldsFromType(jpType));
            }
        }
        return removeDuplicates(attributeList);
    }

    /**
     * Returns true iff a given JDoctor parameter and JavaParser parameter
     * are equivalent. Handles four cases:
     *  (1) jDoctorParam and jpParam are equal.
     *  (2) Both jDoctorParam and jpParam represent standard objects
     *      (e.g. Object, Comparable).
     *  (3) jpParam is a generic (not an array) and jDoctorParam is a
     *      standard object.
     *  (4) jpParam is a generic (array) and jDoctorParam is an array
     *      of standard objects.
     * Returns true if any of the above conditions hold.
     */
    private static boolean jpParamEqualsJDoctorParam(
            String jDoctorParam,
            String jpParam,
            CallableDeclaration<?> jpCallable,
            TypeDeclaration<?> jpClass
    ) {
        if (jDoctorParam.equals(jpParam)) return true;
        boolean jDoctorParamIsStandard = JDoctorUtils.isStandardType(jDoctorParam);
        boolean jDoctorParamIsStandardArray = JDoctorUtils.isStandardTypeArray(jDoctorParam);
        boolean jpParamIsStandard = JDoctorUtils.isStandardType(jpParam);
        boolean jpParamIsArray = jpParam.endsWith("[]");
        boolean jpParamIsGeneric = JavaParserUtils.isGenericType(jpParam, jpCallable, jpClass);
        return (jDoctorParamIsStandard && jpParamIsStandard) ||
                ((jpParamIsGeneric && !jpParamIsArray) && jDoctorParamIsStandard) ||
                ((jpParamIsGeneric && jpParamIsArray) && jDoctorParamIsStandardArray);
    }

    /**
     * Evaluates whether a list of JDoctor parameters and a list of JavaParser
     * parameters are equal. Primarily handles issues regarding different
     * representations of generic types between JDoctor and JavaParser.
     *
     * @param jDoctorParamList list of JDoctor parameters.
     * @param jpParamList list of JavaParser parameters.
     * @param jpCallable method corresponding to jpParamList.
     * @param jpClass the declaring class of jpCallable.
     * @return true iff the two lists represent the same parameters.
     */
    private static boolean jpParamListEqualsJDoctorParamList(
            List<String> jDoctorParamList,
            List<String> jpParamList,
            CallableDeclaration<?> jpCallable,
            TypeDeclaration<?> jpClass
    ) {
        if (jDoctorParamList.size() != jpParamList.size()) return false;
        for (int i = 0; i < jDoctorParamList.size(); i++) {
            String jDoctorParam = jDoctorParamList.get(i);
            String jpParam = jpParamList.get(i);
            if (!jpParamEqualsJDoctorParam(jDoctorParam, jpParam, jpCallable, jpClass)) return false;
        }
        return true;
    }

    /**
     * Gets the method/constructor {@link CallableDeclaration} from a given
     * class {@link TypeDeclaration} given a specific name and a list of
     * parameters.
     *
     * @param jpClass the declaring class {@link TypeDeclaration}.
     * @param targetName the name of the method {@link CallableDeclaration}.
     * @param targetParamList the parameters of the desired method.
     *                        Parameter type names follow JDoctor format.
     * @return the corresponding method (if it exists). Returns null if no
     * such method exists.
     */
    public static CallableDeclaration<?> getCallableDeclaration(
            TypeDeclaration<?> jpClass,
            String targetName,
            List<String> targetParamList
    ) {
        // iterate through each member in the class.
        for (BodyDeclaration<?> member : jpClass.getMembers()) {
            // check if member is a function (method or constructor).
            if (member.isCallableDeclaration()) {
                CallableDeclaration<?> currentCallable = member.asCallableDeclaration();
                // check if the function names are equal.
                if (currentCallable.getNameAsString().equals(targetName)) {
                    // check if parameters are equal.
                    List<String> currentParamList = currentCallable.getParameters()
                            .stream()
                            .map(p -> JDoctorUtils.getJPTypeName(jpClass, currentCallable, p))
                            .toList();
                    if (jpParamListEqualsJDoctorParamList(
                            targetParamList,
                            currentParamList,
                            currentCallable,
                            jpClass
                    )) {
                        return currentCallable;
                    }
                }
            }
        }
        // return null if no such function is found.
        return null;
    }

    /**
     * Gets the type declaration {@link TypeDeclaration} of a class from a
     * given compilation unit {@link CompilationUnit}.
     *
     * @param cu the compilation unit of a file.
     * @param className the name of the desired class.
     * @return returns the type declaration corresponding to the given class
     * name in a compilation unit (if it exists). Returns null if no such
     * class exists.
     */
    public static TypeDeclaration<?> getTypeDeclaration(
            CompilationUnit cu,
            String className
    ) {
        // get all classes in the compilation unit.
        List<TypeDeclaration<?>> typeList = cu.getTypes();
        // throw error if no classes are found.
        if (typeList == null) {
            return null;
        }
        // iterate through classes to find the corresponding class.
        for (TypeDeclaration<?> jpClass : typeList) {
            if (jpClass.getNameAsString().equals(className)) {
                return jpClass;
            }
        }
        return null;
    }

    /**
     * Gets the compilation unit {@link CompilationUnit} corresponding to the
     * class of a JDoctor condition.
     *
     * @param operation an operation representation of a JDoctor condition.
     * @param sourcePath the source path of the relevant project.
     * @return an optional JavaParser compilation unit {@link CompilationUnit}
     * corresponding to the class of the JDoctor condition, if it is found.
     * Otherwise, the method returns an empty optional.
     */
    public static Optional<CompilationUnit> getOperationCompilationUnit(
            Operation operation,
            String sourcePath
    ) {
        List<String> pathList = Arrays.asList(operation.getClassName().split("\\."));
        String classPath = Paths.get(sourcePath, pathList.toArray(String[]::new)) + FileFormat.JAVA.getValue();
        return JavaParserUtils.getCompilationUnitFromFilePath(classPath);
    }

    /**
     * Gets the package name of an operation.
     */
    public static String getOperationPackageName(
            Operation operation
    ) {
        List<String> pathList = JDoctorUtils.getPathList(operation.getClassName());
        List<String> packageList = JDoctorUtils.getPackageList(pathList);
        return JDoctorUtils.getPackageNameFromPackageList(packageList);
    }

    /**
     * Gets the class name of an operation.
     */
    public static String getOperationClassName(
            Operation operation
    ) {
        List<String> pathList = JDoctorUtils.getPathList(operation.getClassName());
        return JDoctorUtils.getClassNameFromPathList(pathList);
    }

    /**
     * Gets the method/constructor name of an operation.
     */
    public static String getOperationCallableName(
            Operation operation
    ) {
        List<String> pathList = JDoctorUtils.getPathList(operation.getName());
        return JDoctorUtils.getClassNameFromPathList(pathList);
    }
}
