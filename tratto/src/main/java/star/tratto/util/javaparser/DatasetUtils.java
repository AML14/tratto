package star.tratto.util.javaparser;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.javadoc.JavadocBlockTag;
import com.github.javaparser.resolution.MethodUsage;
import com.github.javaparser.resolution.UnsolvedSymbolException;
import com.github.javaparser.resolution.declarations.ResolvedFieldDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.resolution.types.ResolvedType;
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
import star.tratto.identifiers.JavadocValueType;
import star.tratto.identifiers.file.*;
import star.tratto.identifiers.path.Path;
import star.tratto.oraclegrammar.custom.Parser;
import star.tratto.oraclegrammar.custom.Splitter;
import star.tratto.util.FileUtils;

import static star.tratto.util.javaparser.JavaParserUtils.*;

import java.io.File;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DatasetUtils {
    /**
     * The method removes all the duplicates from a list.
     * @param list The list from which remove the duplicates.
     * @return A new list that does not contain the duplicates of the list passed to the function.
     * @param <T> The generic type of the list.
     */
    private static <T> List<T> removeDuplicates(List<T> list) {
        Set<T> set = new LinkedHashSet<>(list);
        return new ArrayList<>(set);
    }

    /**
     * Gets the name and package of all classes in a compilation unit.
     *
     * @param cu the compilation unit of a java file
     * @return a list of (className, packageName) pairs
     * @throws PackageDeclarationNotFoundException if the package cannot be
     * retrieved
     */
    private static List<Pair<String, String>> getClassNameAndPackage(
            CompilationUnit cu
    ) throws PackageDeclarationNotFoundException {
        List<Pair<String, String>> classList = new ArrayList<>();
        // get package name.
        String packageName = JavaParserUtils.getPackageDeclarationFromCompilationUnit(cu).getNameAsString();
        // get all classes in compilation unit.
        List<TypeDeclaration<?>> jpClasses = cu.getTypes();
        // iterate over all classes.
        for (TypeDeclaration<?> jpClass : jpClasses) {
            classList.add(new Pair<>(jpClass.getNameAsString(), packageName));
        }
        return classList;
    }

    /**
     * If a JavaDoc is not immediately recoverable, we attempt to find the
     * JavaDoc using pattern matching.
     *
     * @param jpNode a member in a java class
     * @return the matched JavaDoc comment (empty string if not found)
     */
    private static String getJavadocByPattern(BodyDeclaration<?> jpNode) {
        String input = jpNode.toString();
        Pattern pattern = Pattern.compile("/\\*\\*(.*?)\\*/", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(input);
        // if pattern is found, extract information.
        if (matcher.find()) {
            String content = matcher.group(1);
            return Javadoc.METHOD_PREFIX.getValue() + content + Javadoc.METHOD_SUFFIX.getValue();
        }
        return "";
    }

    /**
     * Gets the Javadoc comment of the TypeDeclaration.
     *
     * @param jpClass a JavaParser class
     * @return a string {@link String} representing the Javadoc comment
     */
    public static String getClassJavadoc(
            TypeDeclaration<?> jpClass
    ) {
        return jpClass.getJavadocComment().map(javadocComment -> Javadoc.CLASS_PREFIX.getValue() + javadocComment.getContent() + Javadoc.CLASS_SUFFIX.getValue()).orElseGet(() -> getJavadocByPattern(jpClass));
    }

    /**
     * Gets the Javadoc comment of the BodyDeclaration.
     *
     * @param jpCallable a JavaParser function
     * @return a string {@link String} representing the Javadoc comment
     */
    public static String getCallableJavadoc(
            CallableDeclaration<?> jpCallable
    ) {
        return jpCallable.getJavadocComment().map(javadocComment -> Javadoc.METHOD_PREFIX.getValue() + javadocComment.getContent() + Javadoc.METHOD_SUFFIX.getValue()).orElseGet(() -> getJavadocByPattern(jpCallable));
    }

    public static List<Pair<String, String>> findAllNumericValuesInJavadoc(
            String javadocComment
    ) {
        // Defines regex to match integers and floats within a string.
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        Matcher matcher = pattern.matcher(javadocComment);
        // Get list of (numericType, numericValue) pairs.
        List<Pair<String, String>> numericValues = new ArrayList<>();
        while (matcher.find()) {
            String match = matcher.group();
            if (match.contains(".")) {
                // float value.
                try {
                    double realValue = Double.parseDouble(match);
                    numericValues.add(new Pair<>(Double.toString(realValue), JavadocValueType.REAL.getValue()));
                } catch (Exception e) {
                    String errMsg = String.format("Number exceed maximum real value: %s", match);
                    System.err.println(errMsg);
                }
            } else {
                // integer value.
                try {
                    long longIntValue = Long.parseLong(match);
                    numericValues.add(new Pair<>(Long.toString(longIntValue), JavadocValueType.INTEGER.getValue()));
                } catch (NumberFormatException e) {
                    String errMsg = String.format("Number exceed maximum integer value: %s", match);
                    System.err.println(errMsg);
                }
            }
        }
        // Return the list of the collected integer values
        return numericValues;
    }

    private static List<Pair<String, String>> findAllStringValuesInJavadoc(
            String jpJavadoc
    ) {
        // Defines regex to match values within a string.
        Pattern pattern = Pattern.compile("\\\"(.*?)\\\"|\\\'(.*?)\\\'");
        Matcher matcher = pattern.matcher(jpJavadoc);
        // Get list of (stringValue, "String") pairs.
        List<Pair<String, String>> stringValues = new ArrayList<>();
        while (matcher.find()) {
            String match = String.format("\"%s\"",!(matcher.group(1) == null) ? matcher.group(1) : matcher.group(2));
            stringValues.add(new Pair<>(match, "String"));
        }
        // Return the list of the collected string values
        return stringValues;
    }

    public static List<Pair<String, String>> getValuesFromJavadoc(
            String jpJavadoc
    ) {
        List<Pair<String, String>> pairList = new ArrayList<>();
        pairList.addAll(findAllNumericValuesInJavadoc(jpJavadoc));
        pairList.addAll(findAllStringValuesInJavadoc(jpJavadoc));
        return pairList;
    }

    private static Optional<String> getClassNameFromCallableDeclaration(
            TypeDeclaration<?> jpClass,
            CallableDeclaration<?> jpCallable,
            Parameter jpParameter
    ) {
        Type jpParameterType = jpParameter.getType();
        try {
            if (jpParameterType.resolve().isTypeVariable()) {
                // generic type.
                String className = ((ClassOrInterfaceType) jpParameterType).getNameAsString();
                if (JDoctorUtils.hasJPTypeEllipsis(jpParameter.toString())) {
                    className += "[]";
                }
                return Optional.of(className);
            } else if (jpParameterType.resolve().isPrimitive()) {
                // primitive type.
                String className = jpParameterType.asPrimitiveType().toString();
                if (JDoctorUtils.hasJPTypeEllipsis(jpParameter.toString())) {
                    className += "[]";
                }
                return Optional.of(className);
            } else if (jpParameterType.resolve().isArray()) {
                // array type.
                String qualifiedName = jpParameterType.resolve().asArrayType().describe();
                return Optional.of(getTypeWithoutPackages(qualifiedName));
            } else if (jpParameterType.resolve().isReferenceType()) {
                String jpTypeName = JDoctorUtils.getJPTypeName(jpClass, jpCallable, jpParameter);
                if (isGenericType(jpTypeName, jpCallable, jpClass)) {
                    String className = jpTypeName;
                    if (JDoctorUtils.hasJPTypeEllipsis(jpParameter.toString())) {
                        className += "[]";
                    }
                    return Optional.of(className);
                } else {
                    String qualifiedName = jpParameterType.resolve().asReferenceType().getQualifiedName();
                    String className = getTypeWithoutPackages(qualifiedName);
                    if (JDoctorUtils.hasJPTypeEllipsis(jpParameter.toString())) {
                        className += "[]";
                    }
                    return Optional.of(className);
                }
            } else {
                assert false;
                String errMsg = String.format("Unexpected type when evaluating %s parameter type.", jpParameterType);
                System.err.println(errMsg);
            }
        } catch (UnsolvedSymbolException e) {
            String errMsg = String.format("UnsolvedSymbolException when evaluating %s parameter type.", jpParameterType);
            System.err.println(errMsg);
            String className = ((ClassOrInterfaceType) jpParameterType).getNameAsString();
            if (JDoctorUtils.hasJPTypeEllipsis(jpParameter.toString())) {
                className += "[]";
            }
            return Optional.of(className);
        }
        return Optional.empty();
    }

    public static List<Triplet<String, String, String>> getTokensMethodArguments(
            TypeDeclaration<?> jpClass,
            CallableDeclaration<?> jpCallable
    ) {
        List<Triplet<String, String, String>> argumentList = new ArrayList<>();
        List<Parameter> jpParameters = jpCallable.getParameters();
        for (Parameter jpParameter : jpParameters) {
            Type jpParameterType = jpParameter.getType();
            Optional<String> jpParameterClassName = getClassNameFromCallableDeclaration(jpClass, jpCallable, jpParameter);
            if (jpParameterClassName.isPresent()) {
                try {
                    if (
                            jpParameterType.resolve().isTypeVariable() ||
                            jpParameterType.resolve().isPrimitive() ||
                            jpParameterType.resolve().isArray()
                    ) {
                        argumentList.add(new Triplet<>(jpParameter.getNameAsString(), "", jpParameterClassName.get()));
                    } else if (jpParameterType.resolve().isReferenceType()) {
                        String typeName = JDoctorUtils.getJPTypeName(jpClass, jpCallable, jpParameter);
                        if (isGenericType(typeName, jpCallable, jpClass)) {
                            argumentList.add(new Triplet<>(jpParameter.getNameAsString(), "", typeName));
                        } else {
                            String fullyQualifiedName = jpParameterType.resolve().asReferenceType().getQualifiedName();
                            String className = getTypeWithoutPackages(fullyQualifiedName);
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

    private static List<Quartet<String, String, String, String>> getNonPrivateStaticNonVoidMethods(
            CompilationUnit cu
    ) throws PackageDeclarationNotFoundException {
        List<Quartet<String, String, String, String>> methodList = new ArrayList<>();
        // get package name.
        String packageName = JavaParserUtils.getPackageDeclarationFromCompilationUnit(cu).getNameAsString();
        // get all classes in compilation unit.
        List<TypeDeclaration<?>> jpClasses = cu.getTypes();
        // iterate over all classes.
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
            // iterate over all field declarations in a clas.
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
        List<File> validFiles = new ArrayList<>();
        for (File file : allFiles) {
            String filename = file.getName().replace(FileFormat.JAVA.getValue(), "");
            if (!ignoreFileList.contains(filename)) {
                validFiles.add(file);
            }
        }
        return validFiles;
    }

    public static List<Pair<String, String>> getTokensProjectClasses(
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

    public static List<Quartet<String, String, String, String>> getTokensProjectClassesNonPrivateStaticNonVoidMethods(
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

    public static List<Quartet<String, String, String, String>> getTokensProjectClassesNonPrivateStaticAttributes(
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

    public static List<Quintet<TypeDeclaration<?>, CallableDeclaration<?>, OracleType, String, String>> getTokensProjectClassesTags(
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

    private static List<Quartet<String, String, String, String>> convertMethodUsageToQuartet(
            List<MethodUsage> jpMethods
    ) {
        return new ArrayList<>(jpMethods)
                .stream()
                .map(jpMethod -> new Quartet<>(
                        jpMethod.getName(),
                        jpMethod.declaringType().getClassName(),
                        jpMethod.declaringType().getPackageName(),
                        JavaParserUtils.getMethodSignature(jpMethod)
                ))
                .toList();
    }

    private static List<Quartet<String, String, String, String>> getMethodsFromType(
            TypeDeclaration<?> jpClass,
            CallableDeclaration<?> jpCallable,
            ResolvedType jpType
    ) {
        List<Quartet<String, String, String, String>> methodList = new ArrayList<>();
        // handle base type.
        if (jpType.isReferenceType()) {
            Optional<ResolvedReferenceTypeDeclaration> jpTypeDeclaration = jpType.asReferenceType().getTypeDeclaration();
            if (jpTypeDeclaration.isPresent()) {
                List<MethodUsage> allMethods = jpTypeDeclaration.get().getAllMethods()
                        .stream()
                        .filter(JavaParserUtils::isNonStaticNonVoidNonPrivateMethod)
                        .collect(Collectors.toList());
                methodList.addAll(convertMethodUsageToQuartet(allMethods));
            }
        }
        // handle generic type.
        if (JavaParserUtils.isGenericType(jpType.describe(), jpCallable, jpClass)) {
            List<MethodUsage> genericMethods = JavaParserUtils.getGenericType().asReferenceType().getAllMethods()
                    .stream()
                    .map(MethodUsage::new)
                    .filter(JavaParserUtils::isNonStaticNonVoidNonPrivateMethod)
                    .collect(Collectors.toList());
            methodList.addAll(convertMethodUsageToQuartet(genericMethods));
        }
        // handle array type.
        if (jpType.isArray()) {
            String arraysMethodJsonPath = Paths.get(
                    Path.REPOS.getValue(),
                    FileName.ARRAY_METHODS.getValue() + FileFormat.JSON.getValue()
            ).toString();
            List<List<String>> arrayMethods = FileUtils.readJSONList(arraysMethodJsonPath)
                    .stream()
                    .map(e -> ((List<?>) e)
                            .stream()
                            .map(o -> (String) o)
                            .collect(Collectors.toList()))
                    .toList();
            methodList.addAll(arrayMethods
                    .stream()
                    .map(m -> new Quartet<>(m.get(0), "", jpType.describe(), m.get(1)))
                    .toList());
        }
        return methodList;
    }

    /**
     * Converts a list of resolved field declarations
     * {@link ResolvedFieldDeclaration} into a quartet of the form:
     *      "fieldName, packageOfField, classOfField, fieldSignature"
     *
     * @param jpFields a list of fields {@link ResolvedFieldDeclaration}
     * @return the list of quartets of fields
     */
    private static List<Quartet<String, String, String, String>> convertFieldDeclarationToQuartet(
            List<ResolvedFieldDeclaration> jpFields
    ) {
        return new ArrayList<>(jpFields)
                .stream()
                .map(jpField -> new Quartet<>(
                        jpField.declaringType().getClassName(),
                        jpField.declaringType().getPackageName(),
                        jpField.getName(),
                        JavaParserUtils.getFieldSignature(jpField)
                ))
                .toList();
    }

    /**
     * Gets all non-private, non-static attributes visible to a given type.
     *
     * @param jpType a resolved JavaParser type {@link ResolvedType}
     * @return a list of non-private, non-static attributes
     */
    private static List<Quartet<String, String, String, String>> getFieldsFromType(
            ResolvedType jpType
    ) {
        List<Quartet<String, String, String, String>> fieldList = new ArrayList<>();
        // check that type is not primitive.
        if (jpType.isReferenceType()) {
            Optional<ResolvedReferenceTypeDeclaration> jpTypeDeclaration = jpType.asReferenceType().getTypeDeclaration();
            // get reference declaration of type.
            if (jpTypeDeclaration.isPresent()) {
                Optional<Node> jpNode = jpTypeDeclaration.get().toAst();
                // check if node is found and is a class.
                if (jpNode.isPresent() && jpNode.get() instanceof TypeDeclaration<?>) {
                    List<ResolvedFieldDeclaration> jpFields = ((TypeDeclaration<?>) jpNode.get()).getFields()
                            .stream()
                            .map(FieldDeclaration::resolve)
                            .filter(JavaParserUtils::isNonPrivateNonStaticAttribute)
                            .collect(Collectors.toList());
                    return convertFieldDeclarationToQuartet(jpFields);
                }
                return convertFieldDeclarationToQuartet(jpTypeDeclaration.get().getAllFields());
            } else if (!(
                    jpType.isPrimitive() || jpType.isVoid() || jpType.isTypeVariable() || jpType.isArray()
            )) {
                System.err.printf(
                        "Return type %s different from ReferenceType, PrimitiveType, " +
                        "ArrayType, TypeVariable, and VoidType not yet supported%n", jpType
                );
            }
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
        List<MethodUsage> allReceiverMethods = JavaParserUtils.getAllAvailableMethodUsages(jpClass);
        List<Quartet<String, String, String, String>> methodList = new ArrayList<>(convertMethodUsageToQuartet(allReceiverMethods));
        // add all methods of parameters.
        for (Parameter jpParam : jpCallable.getParameters()) {
            methodList.addAll(getMethodsFromType(
                    jpClass,
                    jpCallable,
                    jpParam.getType().resolve()
            ));
        }
        // add all methods of return type.
        if (jpCallable instanceof MethodDeclaration) {
            methodList.addAll(getMethodsFromType(
                    jpClass,
                    jpCallable,
                    ((MethodDeclaration) jpCallable).getType().resolve()
            ));
        }
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
    ) {
        // add all fields of the base class (receiverObjectID -> this).
        List<ResolvedFieldDeclaration> jpReceiverFields = jpClass.getFields()
                .stream()
                .map(FieldDeclaration::resolve)
                .collect(Collectors.toList());
        List<Quartet<String, String, String, String>> attributeList = new ArrayList<>(convertFieldDeclarationToQuartet(jpReceiverFields));
        // add all fields of parameters.
        for (Parameter jpParam : jpCallable.getParameters()) {
            attributeList.addAll(getFieldsFromType(
                    jpParam.getType().resolve()
            ));
        }
        // add all fields of return type.
        if (jpCallable instanceof MethodDeclaration) {
            attributeList.addAll(getFieldsFromType(
                    ((MethodDeclaration) jpCallable).getType().resolve()
            ));
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
                methodList.addAll(getMethodsFromType(jpClass, jpCallable, jpType));
            } catch (UnsolvedSymbolException | ResolvedTypeNotFound e) {
                ResolvedType fieldType = JavaParserUtils.getGenericType();
                // Get all methods from Object
                List<MethodUsage> jpReturnTypeMethods = fieldType.asReferenceType().getAllMethods()
                        .stream()
                        .map(MethodUsage::new)
                        .collect(Collectors.toList());
                methodList.addAll(convertMethodUsageToQuartet(jpReturnTypeMethods));
            }
        }
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
     * Get the package name of an operation.
     */
    public static String getOperationPackageName(
            Operation operation
    ) {
        List<String> pathList = JDoctorUtils.getPathList(operation.getClassName());
        List<String> packageList = JDoctorUtils.getPackageList(pathList);
        return JDoctorUtils.getPackageNameFromPackageList(packageList);
    }

    /**
     * Get the class name of an operation.
     */
    public static String getOperationClassName(
            Operation operation
    ) {
        List<String> pathList = JDoctorUtils.getPathList(operation.getClassName());
        return JDoctorUtils.getClassNameFromPathList(pathList);
    }

    /**
     * Get the method/constructor name of an operation.
     */
    public static String getOperationCallableName(
            Operation operation
    ) {
        List<String> pathList = JDoctorUtils.getPathList(operation.getName());
        return JDoctorUtils.getClassNameFromPathList(pathList);
    }

    /**
     * Evaluates whether a list of JDoctor parameters and a list of JavaParser
     * parameters are equal. Primarily handles issues regarding different
     * representations of generic types between lists.
     *
     * @param jDoctorParamList list of JDoctor parameters
     * @param jpParamList list of JavaParser parameters
     * @param jpCallable CallableDeclaration corresponding to jpParamList
     * @param jpClass TypeDeclaration corresponding to jpCallable
     * @return true iff the two lists represent the same parameters
     */
    private static boolean jpParamListEqualsJDoctorParamList(
            List<String> jDoctorParamList,
            List<String> jpParamList,
            CallableDeclaration<?> jpCallable,
            TypeDeclaration<?> jpClass
    ) {
        if (jDoctorParamList.size() != jpParamList.size()) {
          return false;
        }
        for (int i = 0; i < jDoctorParamList.size(); i++) {
            String jDoctorParam = jDoctorParamList.get(i);
            String jpParam = jpParamList.get(i);
            // if parameters are identical, then continue.
            if (jDoctorParam.equals(jpParam)) continue;
            // otherwise, check if parameters are generics.
            boolean jDoctorParamIsStandard = JDoctorUtils.isGenericCondition(jDoctorParam);
            boolean jDoctorParamIsStandardArray = JDoctorUtils.isGenericConditionArray(jDoctorParam);
            boolean jpParamIsStandard = JDoctorUtils.isGenericCondition(jpParam);
            boolean jpParamIsArray = jpParam.endsWith("[]");
            boolean jpParamIsGeneric = JavaParserUtils.isGenericType(jpParam, jpCallable, jpClass);
            // case 1: both conditions represent generic objects (e.g. Object, Comparable).
            // case 2: both conditions represent generic object arrays (e.g. Object[], Comparable[]).
            if (!(
                (jDoctorParamIsStandard && jpParamIsStandard) ||
                (jpParamIsGeneric && (
                        jDoctorParamIsStandard || (jDoctorParamIsStandardArray && jpParamIsArray)
                ))
            )) {
              return false;
            }
        }
        return true;
    }

    /**
     * Gets the CallableDeclaration from a given TypeDeclaration with a
     * specified name and parameters.
     *
     * @param jpClass the TypeDeclaration containing the method
     * @param targetName the name of the desired method
     * @param targetParamList the parameters of the desired method
     * @return the corresponding method (if it exists). Returns null if no
     * such method exists.
     */
    public static CallableDeclaration<?> getCallableDeclaration(
            TypeDeclaration<?> jpClass,
            String targetName,
            List<String> targetParamList
    ) {
        // iterate through each BodyDeclaration in the class.
        for (BodyDeclaration<?> member : jpClass.getMembers()) {
            // check if member is a function.
            if (member.isCallableDeclaration()) {
                // get function representation of member.
                CallableDeclaration<?> currentCallable = member.asCallableDeclaration();
                // check if function name is equal to callableName.
                if (currentCallable.getNameAsString().equals(targetName)) {
                    // get parameters of function.
                    List<String> currentParamList = currentCallable.getParameters()
                            .stream()
                            .map(p -> JDoctorUtils.getJPTypeName(jpClass, currentCallable, p))
                            .collect(Collectors.toList());
                    // if parameters are equal, then return the current function.
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
     * Gets the TypeDeclaration of a class from the given CompilationUnit.
     *
     * @param cu the compilation unit of a file
     * @param className the name of the desired class
     * @return returns the TypeDeclaration corresponding to ``className`` in
     * ``cu`` (if it exists). Returns null if no such class exists.
     */
    public static TypeDeclaration<?> getTypeDeclaration(
            CompilationUnit cu,
            String className
    ) {
        // get all classes in the compilation unit.
        NodeList<TypeDeclaration<?>> typeList = cu.getTypes();
        // throw error if no classes are found.
        if (typeList == null) {
            return null;
        }
        // iterate through classes to find corresponding class.
        for (TypeDeclaration<?> jpClass : typeList) {
            if (jpClass.getNameAsString().equals(className)) {
                return jpClass;
            }
        }
        return null;
    }

    /**
     * Gets the JavaParser compilation unit {@link CompilationUnit}
     * corresponding to the class of the JDoctor condition.
     *
     * @param operation a JDoctor operation object of a JDoctor condition
     * @param sourcePath the source path of the relevant project
     * @return an optional JavaParser compilation unit {@link CompilationUnit}
     * corresponding to the class of the JDoctor condition, if it is found.
     * Otherwise, the method returns an empty optional.
     */
    public static Optional<CompilationUnit> getClassCompilationUnit(
            Operation operation,
            String sourcePath
    ) {
        List<String> pathList = Arrays.asList(operation.getClassName().split("\\."));
        String classPath = Paths.get(sourcePath, pathList.toArray(String[]::new)) + FileFormat.JAVA.getValue();
        return JavaParserUtils.getCompilationUnitFromFilePath(classPath);
    }
}
