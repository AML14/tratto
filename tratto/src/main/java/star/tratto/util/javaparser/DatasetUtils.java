package star.tratto.util.javaparser;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.CallableDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.javadoc.Javadoc;
import com.github.javaparser.javadoc.JavadocBlockTag;
import com.github.javaparser.resolution.MethodUsage;
import com.github.javaparser.resolution.UnsolvedSymbolException;
import com.github.javaparser.resolution.declarations.ResolvedFieldDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.resolution.types.ResolvedType;
import com.github.javaparser.symbolsolver.javaparsermodel.declarations.JavaParserFieldDeclaration;
import com.github.javaparser.symbolsolver.reflectionmodel.ReflectionFieldDeclaration;
import org.javatuples.Pair;
import org.javatuples.Quartet;
import org.javatuples.Sextet;
import org.javatuples.Triplet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import star.tratto.data.OracleType;
import star.tratto.data.JPClassNotFoundException;
import star.tratto.data.PackageDeclarationNotFoundException;
import star.tratto.data.ResolvedTypeNotFound;
import star.tratto.data.TrattoPath;
import star.tratto.data.oracles.JDoctorCondition.Operation;
import star.tratto.oraclegrammar.custom.Parser;
import star.tratto.oraclegrammar.custom.Splitter;
import star.tratto.util.FileUtils;

import java.io.File;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * This class provides core utilities for the generation of the oracles
 * dataset and conversion of JavaParser objects into interpretable inputs.
 */
public class DatasetUtils {
    private static final Logger logger = LoggerFactory.getLogger(DatasetUtils.class);

    // private constructor to avoid creating an instance of this class.
    private DatasetUtils() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    /**
     * The method removes all the duplicates from a list.
     *
     * @param list the list from which remove the duplicates
     * @return a new list that does not contain any duplicates elements
     * @param <T> the generic type of the list.
     */
    public static <T> List<T> removeDuplicates(List<T> list) {
        Set<T> set = new LinkedHashSet<>(list);
        return new ArrayList<>(set);
    }

    /**
     * Gets a list of each class name, and corresponding package, name for all
     * classes in a compilation unit.
     *
     * @param cu the compilation unit of a Java file
     * @return a list of (className, packageName) pairs
     * @throws PackageDeclarationNotFoundException if the package cannot be
     * retrieved
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
     * @param jpBody a member in a Java class {@link BodyDeclaration}
     * @return the matched JavaDoc comment (empty string if not found)
     */
    private static String getJavadocByPattern(BodyDeclaration<?> jpBody) {
        String input = jpBody.toString();
        Pattern pattern = Pattern.compile("/\\*\\*(.*?)\\*/", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            String content = matcher.group(1);
            // change prefix/suffix depending on the type of the member.
            if (jpBody instanceof TypeDeclaration<?>) {
                return "/**" + content + "*/";
            } else {
                return "    /**" + content + "*/";
            }
        }
        return "";
    }

    /**
     * Gets the Javadoc comment of a class {@link TypeDeclaration}.
     *
     * @param jpClass a JavaParser class {@link TypeDeclaration}
     * @return a string representation of the Javadoc comment
     */
    public static String getClassJavadoc(
            TypeDeclaration<?> jpClass
    ) {
        Optional<JavadocComment> optionalJavadocComment = jpClass.getJavadocComment();
        if (optionalJavadocComment.isEmpty()) return getJavadocByPattern(jpClass);
        return "/**" + optionalJavadocComment.get().getContent() + "*/";
    }

    /**
     * Gets the Javadoc comment of a function {@link CallableDeclaration}.
     *
     * @param jpCallable a JavaParser function {@link CallableDeclaration}
     * @return a string representation the Javadoc comment
     */
    public static String getCallableJavadoc(
            CallableDeclaration<?> jpCallable
    ) {
        Optional<JavadocComment> optionalJavadocComment = jpCallable.getJavadocComment();
        if (optionalJavadocComment.isEmpty()) return getJavadocByPattern(jpCallable);
        return "    /**" + optionalJavadocComment.get().getContent() + "*/";
    }

    /**
     * Gets all numeric values in a JavaDoc comment represented as a pair of
     * strings.
     *
     * @param javadocComment the string representation of a JavaDoc comment
     * @return a list of pairs of strings representing all numeric values
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
                    logger.error(String.format("Number exceed maximum float value: %s%n", match));
                }
            } else {
                // integer (no decimal).
                try {
                    long longIntValue = Long.parseLong(match);
                    numericValues.add(new Pair<>(Long.toString(longIntValue), "int"));
                } catch (NumberFormatException e) {
                    logger.error(String.format("Number exceed maximum integer value: %s", match));
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
     * @param jpJavadoc the string representation of a JavaDoc comment
     * @return a list of pairs of strings representing the string values in
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
     * @param jpJavadoc the JavaDoc comment
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
     * @param jpClass the class declaring the method
     * @param jpCallable the method containing the type
     * @param jpParameter the given type
     * @return the type name of the given parameter
     */
    private static Optional<String> getParameterTypeName(
            TypeDeclaration<?> jpClass,
            CallableDeclaration<?> jpCallable,
            Parameter jpParameter
    ) {
        Type jpParameterType = jpParameter.getType();
        boolean hasEllipsis = TypeUtils.hasEllipsis(jpParameter.toString());
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
                if (JavaParserUtils.isTypeParameter(jpResolvedParameterType)) {
                    className = TypeUtils.getRawTypeName(jpClass, jpCallable, jpParameter);
                } else {
                    className = JavaParserUtils.getTypeWithoutPackages(jpResolvedParameterType.asReferenceType());
                }
            } else if (jpResolvedParameterType.isArray()) {
                // special case: return early if type is an array to avoid redundant brackets.
                return Optional.of(JavaParserUtils.getTypeWithoutPackages(jpResolvedParameterType.asArrayType()));
            } else {
                // unknown type.
                assert false;
                logger.error(String.format("Unexpected type when evaluating %s parameter type.", jpParameterType));
            }
            // check if type is an array.
            if (hasEllipsis) {
                className += "[]";
            }
            // return class name.
            return Optional.of(className);
        } catch (UnsolvedSymbolException e) {
            logger.error(String.format("UnsolvedSymbolException when evaluating %s parameter type.", jpParameterType));
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
     * @param jpClass the declaring class {@link CallableDeclaration}
     * @param jpCallable a method {@link TypeDeclaration}
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
                        argumentList.add(Triplet.with(jpParameter.getNameAsString(), "", jpParameterClassName.get()));
                    } else if (jpParameterType.resolve().isReferenceType()) {
                        String typeName = TypeUtils.getRawTypeName(jpClass, jpCallable, jpParameter);
                        if (JavaParserUtils.isTypeParameter(jpParameterType.resolve())) {
                            // if reference object is a generic type, ignore package name.
                            argumentList.add(Triplet.with(jpParameter.getNameAsString(), "", typeName));
                        } else {
                            // otherwise, retrieve necessary package information.
                            String className = JavaParserUtils.getTypeWithoutPackages(jpParameterType.resolve().asReferenceType());
                            String parameterPackageName = jpParameterType.resolve().asReferenceType().getQualifiedName()
                                    .replace(String.format(".%s", className), "");
                            argumentList.add(Triplet.with(
                                    jpParameter.getNameAsString(),
                                    parameterPackageName,
                                    jpParameterClassName.get()
                            ));
                        }
                    }
                } catch (UnsolvedSymbolException e) {
                    logger.error(String.format("Unable to generate triplet for argument %s.", jpParameterType));
                }
            }
        }
        return argumentList;
    }

    /**
     * Reconstructs the original tag in source code from a list of tag
     * information.
     *
     * @param jpTag a sextet of tag information, including: file source code,
     *              JavaParser class, JavaParser method/constructor, oracle
     *              type, name, and content.
     * @return the original tag in source code as a String.
     */
    public static String reconstructTag(
            Sextet<String, TypeDeclaration<?>, CallableDeclaration<?>, OracleType, String, String> jpTag
    ) {
        String tagString = switch (jpTag.getValue3()) {
            case PRE -> "@param ";
            case NORMAL_POST -> "@return ";
            case EXCEPT_POST -> "@throws ";
        };
        tagString += !jpTag.getValue4().equals("") ?  jpTag.getValue4() + " " : "";
        tagString += jpTag.getValue5();
        return tagString;
    }

    /**
     * Gets the source code of a given function {@link CallableDeclaration}.
     *
     * @param jpCallable a method or constructor
     * @return a string representation of the source code
     */
    public static String getCallableSourceCode(
            CallableDeclaration<?> jpCallable
    ) {
        String jpSignature = JavaParserUtils.getCallableSignature(jpCallable);
        Optional<BlockStmt> jpBody = jpCallable instanceof MethodDeclaration ?
                ((MethodDeclaration) jpCallable).getBody() :
                Optional.ofNullable(((ConstructorDeclaration) jpCallable).getBody());
        return jpSignature + (jpBody.isEmpty() ? ";" : jpBody.get().toString());
    }

    /**
     * Collects information about all non-private, static, non-void methods
     * of a given compilation unit.
     *
     * @param cu a compilation unit {@link CompilationUnit} of a Java file
     * @return a list of information about each method. Each entry has the
     * form:
     *  [methodName, packageName, className, methodSignature]
     * @throws PackageDeclarationNotFoundException if the package
     * {@link PackageDeclaration} of the compilation unit is not found
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
                    methodList.add(Quartet.with(
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
     * Collects information about all non-private, static attributes of a
     * given compilation unit.
     *
     * @param cu a compilation unit {@link CompilationUnit} of a Java file
     * @return a list of information about each attribute. Each entry has the
     * form:
     *  [variableName, packageName, className, variableSignature]
     * @throws PackageDeclarationNotFoundException if the package
     * {@link PackageDeclaration} of the compilation unit is not found
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
                        attributeList.add(Quartet.with(
                                jpVariable.getNameAsString(),
                                packageName,
                                className,
                                JavaParserUtils.getVariableDeclaration(jpField, jpVariable)
                        ));
                    }
                }
            }
        }
        return attributeList;
    }

    /**
     * Collects information about all JavaDoc tags in a given compilation
     * unit.
     *
     * @param cu a compilation unit {@link CompilationUnit} of a Java file
     * @param fileContent the content of the Java file
     * @return a list of information about each tag. Each entry has the form:
     *  [typeDeclaration, callableDeclaration, oracleType, name, content]
     * where a JavaDoc tag is interpreted as:
     *  "@tag name content"
     * and the value of "@tag" determines "oracleType".
     * @throws PackageDeclarationNotFoundException if the package
     * {@link PackageDeclaration} of the compilation unit is not found
     */
    private static List<Sextet<String, TypeDeclaration<?>, CallableDeclaration<?>, OracleType, String, String>> getCuTags(
            CompilationUnit cu,
            String fileContent
    ) throws PackageDeclarationNotFoundException {
        List<Sextet<String, TypeDeclaration<?>, CallableDeclaration<?>, OracleType, String, String>> tagList = new ArrayList<>();
        // iterate through each class.
        List<TypeDeclaration<?>> jpClasses = cu.getTypes();
        for (TypeDeclaration<?> jpClass : jpClasses) {
            // iterate through each function.
            List<CallableDeclaration<?>> jpCallables = new ArrayList<>();
            jpCallables.addAll(jpClass.getMethods());
            jpCallables.addAll(jpClass.getConstructors());
            for (CallableDeclaration<?> jpCallable : jpCallables) {
                // iterate through each JavaDoc tag.
                Optional<Javadoc> optionalJavadoc = jpCallable.getJavadoc();
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
                        tagList.add(Sextet.with(
                                fileContent,
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
     * Finds all ".java" files in a given directory. Files are filtered by an
     * ad-hoc list of files to ignore (see dataset/repos/ignore_file.json).
     *
     * @param sourcePath the path to the project root directory
     * @return a list of all valid files {@link File}
     */
    private static List<Path> getValidJavaFiles(String sourcePath) {
        // Get list of all Java files.
        Path sourceDir = Path.of(sourcePath);
        List<Path> allFiles = FileUtils.getAllJavaFilesFromDirectory(sourceDir);
        // Get list of files to ignore.
        Path ignoreFilePath = TrattoPath.IGNORE_FILE.getPath();
        List<String> ignoreFileList = FileUtils.readJSONList(ignoreFilePath, String.class);
        // filter files.
        return allFiles
                .stream()
                .filter(f -> !ignoreFileList.contains(f.getFileName().toString()))
                .collect(Collectors.toList());
    }

    /**
     * Collects information about all classes in a project from a given
     * source path.
     *
     * @param sourcePath the project root directory
     * @return a list of (className, packageName) pairs
     */
    public static List<Pair<String, String>> getProjectClassesTokens(
            String sourcePath
    ) {
        List<Pair<String, String>> projectClasses = new ArrayList<>();
        List<Path> javaFiles = getValidJavaFiles(sourcePath);
        // iterate through each file and add class tokens.
        for (Path javaFile : javaFiles) {
            Optional<CompilationUnit> cu = JavaParserUtils.getCompilationUnit(javaFile.toAbsolutePath());
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
     * Collects information about all non-private, static, non-void methods
     * in a project from a given source path.
     *
     * @param sourcePath the project root directory
     * @return a list of information about each method. Each entry has the
     * form:
     *  [methodName, packageName, className, methodSignature]
     */
    public static List<Quartet<String, String, String, String>> getProjectNonPrivateStaticNonVoidMethodsTokens(
            String sourcePath
    ) {
        List<Quartet<String, String, String, String>> projectMethods = new ArrayList<>();
        List<Path> javaFiles = getValidJavaFiles(sourcePath);
        // iterate through each file and add method tokens.
        for (Path javaFile : javaFiles) {
            Optional<CompilationUnit> cu = JavaParserUtils.getCompilationUnit(javaFile.toAbsolutePath());
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
     * Collects information about all non-private, static attributes
     * in a project from a given source path.
     *
     * @param sourcePath the project root directory
     * @return a list of information about each attribute. Each entry has the
     * form:
     *  [variableName, packageName, className, variableSignature]
     */
    public static List<Quartet<String, String, String, String>> getProjectNonPrivateStaticAttributesTokens(
            String sourcePath
    ) {
        List<Quartet<String, String, String, String>> attributeList = new ArrayList<>();
        List<Path> javaFiles = getValidJavaFiles(sourcePath);
        // iterate through each file and add attribute tokens.
        for (Path javaFile : javaFiles) {
            Optional<CompilationUnit> cu = JavaParserUtils.getCompilationUnit(javaFile.toAbsolutePath());
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
     * Collects information about all JavaDoc tags in a project from a
     * given source path.
     *
     * @param sourcePath the project root directory
     * @return a list of information about each tag. Each entry has the form:
     *  [typeDeclaration, callableDeclaration, oracleType, name, content]
     * where a JavaDoc tag is interpreted as:
     *  "@tag name content"
     * and the value of "@tag" determines "oracleType".
     */
    public static List<Sextet<String, TypeDeclaration<?>, CallableDeclaration<?>, OracleType, String, String>> getProjectTagsTokens(
            String sourcePath
    ) {
        List<Sextet<String, TypeDeclaration<?>, CallableDeclaration<?>, OracleType, String, String>> tagList = new ArrayList<>();
        List<Path> javaFiles = getValidJavaFiles(sourcePath);
        // iterate through each file and add JavaDoc tags.
        for (Path javaFile : javaFiles) {
            Path absoluteJavaFile = javaFile.toAbsolutePath();
            String fileContent = FileUtils.readString(absoluteJavaFile);
            Optional<CompilationUnit> cu = JavaParserUtils.getCompilationUnit(absoluteJavaFile);
            if (cu.isPresent()) {
                try {
                    tagList.addAll(getCuTags(cu.get(), fileContent));
                } catch (PackageDeclarationNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return tagList;
    }

    /**
     * Converts a list of methods {@link MethodUsage} to a list of string
     * quartets where each entry has the form:
     *  [methodName, packageName, className, methodSignature]
     * where "className" refers to the class in which the method is declared.
     * "methodSignature" includes access specifiers, non-access modifiers,
     * generic type parameters, return type, method signature, parameters,
     * and exceptions (unless otherwise un-recoverable).
     */
    private static List<Quartet<String, String, String, String>> convertMethodUsageToQuartet(
            List<MethodUsage> jpMethods
    ) {
        return new ArrayList<>(jpMethods)
                .stream()
                .map(jpMethod -> Quartet.with(
                        jpMethod.getName(),
                        jpMethod.declaringType().getPackageName(),
                        jpMethod.declaringType().getClassName(),
                        JavaParserUtils.getMethodSignature(jpMethod)
                ))
                .toList();
    }

    /**
     * Collects information for all non-private, non-static, non-void methods
     * visible to a given type. Handles three cases: base type (e.g. class),
     * generic type, and array type.
     *
     * @param jpResolvedType the given type {@link ResolvedType}
     * @return a list of information about each method. Each entry has the
     * form:
     *  [methodName, packageName, className, methodSignature]
     */
    public static List<Quartet<String, String, String, String>> getMethodsFromType(
            ResolvedType jpResolvedType
    ) {
        List<Quartet<String, String, String, String>> methodList = new ArrayList<>();
        if (jpResolvedType.isArray()) {
            // array type (see dataset/repose/array_methods.json).
            List<List<String>> arrayMethods;
            arrayMethods = FileUtils.readJSONList(TrattoPath.ARRAY_METHODS.getPath())
                    .stream()
                    .map(e -> ((List<?>) e)
                            .stream()
                            .map(o -> (String) o)
                            .toList())
                    .toList();
            methodList.addAll(arrayMethods
                    .stream()
                    .map(m -> Quartet.with(m.get(0), "", jpResolvedType.describe(), m.get(1)))
                    .toList());
        } else if (JavaParserUtils.isTypeParameter(jpResolvedType)) {
            // generic type.
            List<MethodUsage> genericMethods = JavaParserUtils.getObjectType().asReferenceType().getAllMethods()
                    .stream()
                    .map(MethodUsage::new)
                    .filter(JavaParserUtils::isNonPrivateNonStaticNonVoidMethod)
                    .toList();
            methodList.addAll(convertMethodUsageToQuartet(genericMethods));
        } else if (jpResolvedType.isReferenceType()) {
            // base type.
            List<MethodUsage> allMethods = jpResolvedType.asReferenceType().getAllMethods()
                    .stream()
                    .map(MethodUsage::new)
                    .filter(JavaParserUtils::isNonPrivateNonStaticNonVoidMethod)
                    .toList();
            methodList.addAll(convertMethodUsageToQuartet(allMethods));
        }
        return methodList;
    }

    /**
     * Wrapper method which first attempts to resolve a given type. Returns
     * an empty list if an error occurs. See public "getMethodsFromType()"
     * method above for further detail.
     */
    private static List<Quartet<String, String, String, String>> getMethodsFromType(
            Type jpType
    ) {
        try {
            ResolvedType jpResolvedType = jpType.resolve();
            return getMethodsFromType(jpResolvedType);
        } catch (UnsolvedSymbolException e) {
            logger.error(String.format("Unable to generate method quartet list from type %s", jpType));
            return new ArrayList<>();
        }
    }

    /**
     * Uses methods and fields of JavaParserFieldDeclaration to get a more
     * detailed field signature (includes all modifiers and value).
     */
    private static List<Quartet<String, String, String, String>> convertJavaParserFieldDeclarationToQuartet(
            JavaParserFieldDeclaration resolvedField
    ) {
        List<Quartet<String, String, String, String>> attributeList = new ArrayList<>();
        FieldDeclaration jpField = resolvedField.getWrappedNode();
        for (VariableDeclarator jpVariable : jpField.getVariables()) {
            attributeList.add(Quartet.with(
                    jpVariable.getNameAsString(),
                    resolvedField.declaringType().getPackageName(),
                    resolvedField.declaringType().getClassName(),
                    JavaParserUtils.getVariableDeclaration(jpField, jpVariable)
            ));
        }
        return attributeList;
    }

    /**
     * Uses methods and fields of ReflectionFieldDeclaration to get a more
     * detailed field signature (includes all modifiers).
     */
    private static List<Quartet<String, String, String, String>> convertReflectionFieldDeclarationToQuartet(
            ReflectionFieldDeclaration resolvedField
    ) {
        List<Quartet<String, String, String, String>> attributeList = new ArrayList<>();
        String signature;
        try {
            Field f = resolvedField.getClass().getDeclaredField("field");
            f.setAccessible(true);
            Field field = (Field) f.get(resolvedField);
            signature = JavaParserUtils.getFieldDeclaration(resolvedField, field.getModifiers());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            signature = JavaParserUtils.getFieldDeclaration(resolvedField);
        }
        attributeList.add(Quartet.with(
                resolvedField.getName(),
                resolvedField.declaringType().getPackageName(),
                resolvedField.declaringType().getClassName(),
                signature
        ));
        return attributeList;
    }

    /**
     * Converts a list of fields {@link ResolvedFieldDeclaration} to a list
     * of string quartets where each entry has the form:
     *  [fieldName, packageName, className, fieldSignature]
     * where "className" refers to the name of the field type. If possible,
     * declarations with multiple fields are split into individual quartets.
     */
    private static List<Quartet<String, String, String, String>> convertFieldDeclarationToQuartet(
            List<ResolvedFieldDeclaration> resolvedFields
    ) {
        List<Quartet<String, String, String, String>> fieldList = new ArrayList<>();
        for (ResolvedFieldDeclaration resolvedField : resolvedFields) {
            if (resolvedField instanceof JavaParserFieldDeclaration) {
                // use JavaParserFieldDeclaration to get a more detailed signature.
                fieldList.addAll(convertJavaParserFieldDeclarationToQuartet((JavaParserFieldDeclaration) resolvedField));
            } else if (resolvedField instanceof ReflectionFieldDeclaration) {
                // use ReflectionFieldDeclaration to get a more detailed signature.
                fieldList.addAll(convertReflectionFieldDeclarationToQuartet((ReflectionFieldDeclaration) resolvedField));
            } else {
                // use default ResolvedFieldDeclaration.
                fieldList.add(Quartet.with(
                        resolvedField.getName(),
                        resolvedField.declaringType().getPackageName(),
                        resolvedField.declaringType().getClassName(),
                        JavaParserUtils.getFieldDeclaration(resolvedField)
                ));
            }
        }
        return fieldList;
    }

    /**
     * Collects information for all non-private, non-static attributes visible
     * to a given type.
     *
     * @param jpResolvedType the given type {@link ResolvedType}
     * @return a list of information about each attribute. Each entry has the
     * form:
     *  [fieldName, packageName, className, fieldSignature]
     * where "className" refers to the name of the field type. If possible,
     * declarations with multiple fields are split into individual quartets.
     */
    public static List<Quartet<String, String, String, String>> getFieldsFromType(
            ResolvedType jpResolvedType
    ) {
        List<Quartet<String, String, String, String>> fieldList = new ArrayList<>();
        if (jpResolvedType.isArray()) {
            // add array field (length).
            Pair<String, String> packageAndClass = JavaParserUtils.getTypeFromResolvedType(jpResolvedType);
            fieldList.add(Quartet.with(
                    "length",
                    packageAndClass.getValue0(),
                    packageAndClass.getValue1(),
                    "public final int length;"
            ));
        } else if (jpResolvedType.isReferenceType()) {
            // add all fields accessible to the class.
            Optional<ResolvedReferenceTypeDeclaration> jpResolvedDeclaration = jpResolvedType.asReferenceType().getTypeDeclaration();
            if (jpResolvedDeclaration.isPresent()) {
                List<ResolvedFieldDeclaration> jpResolvedFields = jpResolvedDeclaration.get().getAllFields()
                        .stream()
                        .filter(JavaParserUtils::isNonPrivateNonStaticAttribute)
                        .toList();
                fieldList.addAll(convertFieldDeclarationToQuartet(jpResolvedFields));
            } else {
                // unable to recover type declaration.
                logger.error(String.format(
                        "Unable to analyze the resolved type %s: " +
                                "resolved type declaration not found.", jpResolvedType
                ));
            }
        } else if (!(jpResolvedType.isPrimitive() || jpResolvedType.isVoid() || jpResolvedType.isTypeVariable())) {
            // unknown type.
            logger.error(String.format(
                    "Return type %s different from ReferenceType, PrimitiveType, " +
                            "ArrayType, TypeVariable, and VoidType not yet supported%n", jpResolvedType
            ));
        }
        return fieldList;
    }

    /**
     * Wrapper method which first attempts to resolve a given type. Returns
     * an empty list if an error occurs. See public "getFieldsFromType()"
     * method above for further detail.
     */
    private static List<Quartet<String, String, String, String>> getFieldsFromType(
            Type jpType
    ) {
        try {
            ResolvedType jpResolvedType = jpType.resolve();
            return getFieldsFromType(jpResolvedType);
        } catch (UnsolvedSymbolException e) {
            logger.error(String.format("Unable to generate attribute quartet list from type %s", jpType));
            return new ArrayList<>();
        }
    }

    /**
     * Like previous method, but to be used with method arguments. This function
     * handles those cases where the argument is an array but expressed using ellipsis,
     * e.g., {@code foo(String... bar)}. If the method argument is not an array
     * of this kind, the method simply calls the previous method.
     */
    public static List<Quartet<String, String, String, String>> getFieldsFromParameter(
            Parameter jpParameter
    ) {
        if (TypeUtils.hasEllipsis(jpParameter.toString())) {
            Pair<String, String> packageAndClass = JavaParserUtils.getTypeFromResolvedType(jpParameter.getType().resolve());
            return List.of(Quartet.with(
                    "length",
                    packageAndClass.getValue0(),
                    packageAndClass.getValue1() + "[]",
                    "public final int length;"
            ));
        }
        return getFieldsFromType(jpParameter.getType());
    }

    /**
     * Collects information for all non-private, non-static, non-void methods
     * for a given method variable. Includes methods visible to:
     *  (1) the base class (this).
     *  (2) the arguments of the method.
     *  (3) the class of the method return type.
     *
     * @param jpClass the declaring class {@link TypeDeclaration}
     * @param jpCallable a function {@link CallableDeclaration}
     * @return a list of information about each method. Each entry has the
     * form:
     *  [methodName, packageName, className, methodSignature]
     * @throws JPClassNotFoundException if the declaring class is not
     * resolvable
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
            methodList.addAll(getMethodsFromType(jpParam.getType()));
        }
        // add all methods of return type.
        if (jpCallable instanceof MethodDeclaration) {
            methodList.addAll(getMethodsFromType(((MethodDeclaration) jpCallable).getType()));
        }
        // add Object methods.
        methodList.addAll(convertMethodUsageToQuartet(
                JavaParserUtils.getObjectType().asReferenceType().getAllMethods()
                        .stream()
                        .map(MethodUsage::new)
                        .filter(JavaParserUtils::isNonPrivateNonStaticNonVoidMethod)
                        .toList()
        ));
        return removeDuplicates(methodList);
    }

    /**
     * Collects information for all non-private, non-static attributes
     * for a given method variable. Includes attributes visible to:
     *  (1) the base class (this).
     *  (2) the arguments of the method.
     *  (3) the class of the method return type.
     *
     * @param jpClass the declaring class {@link TypeDeclaration}
     * @param jpCallable a function {@link CallableDeclaration}
     * @return a list of information about each attribute. Each entry has the
     * form:
     *  [fieldName, packageName, className, fieldSignature]
     * @throws JPClassNotFoundException if the declaring class is not
     * resolvable
     */
    public static List<Quartet<String, String, String, String>> getTokensMethodVariablesNonPrivateNonStaticAttributes(
            TypeDeclaration<?> jpClass,
            CallableDeclaration<?> jpCallable
    ) throws JPClassNotFoundException {
        // add all fields of the base class (receiverObjectID -> this).
        List<ResolvedFieldDeclaration> allReceiverFields = JavaParserUtils.getAllAvailableResolvedFields(jpClass)
                .stream()
                .filter(JavaParserUtils::isNonPrivateNonStaticAttribute)
                .toList();
        List<Quartet<String, String, String, String>> attributeList = new ArrayList<>(convertFieldDeclarationToQuartet(allReceiverFields));
        // add all fields of parameters.
        for (Parameter jpParam : jpCallable.getParameters()) {
            attributeList.addAll(getFieldsFromParameter(jpParam));
        }
        // add all fields of return type.
        if (jpCallable instanceof MethodDeclaration) {
            attributeList.addAll(getFieldsFromType(((MethodDeclaration) jpCallable).getType()));
        }
        return removeDuplicates(attributeList);
    }

    /**
     * Collects information for all non-private, non-static, non-void methods
     * for a given oracle. Includes methods visible to each sub-expression
     * within an oracle.
     *
     * @param jpClass the declaring class {@link TypeDeclaration}
     * @param jpCallable a function {@link CallableDeclaration}
     * @param methodArgs the arguments of the function
     * @param oracle an oracle corresponding to the function
     * @return a list of information about each method. Each entry has the
     * form:
     *  [methodName, packageName, className, methodSignature]
     */
    public static List<Quartet<String, String, String, String>> getTokensOracleVariablesNonPrivateNonStaticNonVoidMethods(
            TypeDeclaration<?> jpClass,
            CallableDeclaration<?> jpCallable,
            List<Triplet<String, String, String>> methodArgs,
            String oracle
    ) {
        List<Quartet<String, String, String, String>> methodList = new ArrayList<>();
        List<LinkedList<String>> oracleSubExpressions = Parser.getInstance().getAllMethodsAndAttributes(oracle)
                .stream()
                .map(e -> new LinkedList<>(Splitter.split(e)))
                .toList();
        for (LinkedList<String> oracleSubexpression : oracleSubExpressions) {
            String subexpression = String.join("", oracleSubexpression).replaceAll("receiverObjectID", "this");
            try {
                ResolvedType resolvedType = JavaParserUtils.getResolvedTypeOfExpression(
                        jpClass,
                        jpCallable,
                        methodArgs,
                        subexpression
                );
                if (!resolvedType.isPrimitive()) {
                    methodList.addAll(getMethodsFromType(JavaParserUtils.getObjectType()));
                }
                methodList.addAll(getMethodsFromType(resolvedType));
            } catch (UnsolvedSymbolException | ResolvedTypeNotFound e) {
                ResolvedType genericType = JavaParserUtils.getObjectType();
                methodList.addAll(getMethodsFromType(genericType));
            }
        }
        return removeDuplicates(methodList);
    }

    /**
     * Collects information for all non-private, non-static attributes for a
     * given oracle. Includes attributes visible to each sub-expression within
     * an oracle.
     *
     * @param jpClass the declaring class {@link TypeDeclaration}
     * @param jpCallable a function {@link CallableDeclaration}
     * @param methodArgs the arguments of the function
     * @param oracle an oracle corresponding to the function
     * @return a list of information about each attribute. Each entry has the
     * form:
     *  [fieldName, packageName, className, fieldSignature]
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
                ResolvedType resolvedType = JavaParserUtils.getResolvedTypeOfExpression(
                        jpClass,
                        jpCallable,
                        methodArgs,
                        subexpression
                );
                attributeList.addAll(getFieldsFromType(resolvedType));
            } catch (UnsolvedSymbolException | ResolvedTypeNotFound e) {
                ResolvedType genericType = JavaParserUtils.getObjectType();
                attributeList.addAll(getFieldsFromType(genericType));
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
        boolean jDoctorParamIsStandard = TypeUtils.isStandardType(jDoctorParam);
        boolean jDoctorParamIsStandardArray = TypeUtils.isStandardTypeArray(jDoctorParam);
        boolean jpParamIsStandard = TypeUtils.isStandardType(jpParam);
        boolean jpParamIsArray = jpParam.endsWith("[]");
        boolean jpParamIsGeneric = JavaParserUtils.isTypeParameter(jpParam, jpCallable, jpClass);
        return (jDoctorParamIsStandard && jpParamIsStandard) ||
                ((jpParamIsGeneric && !jpParamIsArray) && jDoctorParamIsStandard) ||
                ((jpParamIsGeneric && jpParamIsArray) && jDoctorParamIsStandardArray);
    }

    /**
     * Evaluates whether a list of JDoctor parameters and a list of JavaParser
     * parameters are equal. Primarily handles issues regarding different
     * representations of generic types between JDoctor and JavaParser.
     *
     * @param jDoctorParamList list of JDoctor parameters
     * @param jpParamList list of JavaParser parameters
     * @param jpCallable method corresponding to jpParamList
     * @param jpClass the declaring class of jpCallable
     * @return true iff the two lists represent the same parameters
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
     * @param jpClass the declaring class {@link TypeDeclaration}
     * @param targetName the name of the method {@link CallableDeclaration}
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
                            .map(p -> TypeUtils.getRawTypeName(jpClass, currentCallable, p))
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
     * @param cu the compilation unit of a file
     * @param className the name of the desired class
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
     * @param operation an operation of a JDoctor condition
     * @param sourcePath the source path of the relevant project
     * @return the path of the class in the JDoctor condition
     */
    private static Path getClassPath(
            Operation operation,
            String sourcePath
    ) {
        return Paths.get(sourcePath, operation.getClassName().replace(".", "/") + ".java");
    }

    /**
     * Gets the compilation unit {@link CompilationUnit} corresponding to the
     * class of a JDoctor condition.
     *
     * @param operation an operation representation of a JDoctor condition
     * @param sourcePath the source path of the relevant project
     * @return an optional JavaParser compilation unit {@link CompilationUnit}
     * corresponding to the class of the JDoctor condition, if it is found.
     * Otherwise, the method returns an empty optional.
     */
    public static Optional<CompilationUnit> getOperationCompilationUnit(
            Operation operation,
            String sourcePath
    ) {
        Path classPath = getClassPath(operation, sourcePath);
        return JavaParserUtils.getCompilationUnit(classPath);
    }

    /**
     * Like previous method, but instead of retrieving the compilation unit,
     * retrieves the source code of the class.
     */
    public static Optional<String> getOperationClassSource(
            Operation operation,
            String sourcePath
    ) {
        try {
            Path classPath = getClassPath(operation, sourcePath);
            String classSource = FileUtils.readString(classPath);
            return Optional.of(classSource);
        } catch (Error e) {
            return Optional.empty();
        }
    }

    /**
     * Gets the package name of an operation.
     */
    public static String getOperationPackageName(
            Operation operation
    ) {
        List<String> pathList = TypeUtils.getNameSegments(operation.getClassName());
        return TypeUtils.getPackageNameFromNameSegments(pathList);
    }

    /**
     * Gets the class name of an operation.
     */
    public static String getOperationClassName(
            Operation operation
    ) {
        List<String> pathList = TypeUtils.getNameSegments(operation.getClassName());
        return TypeUtils.getClassNameFromNameSegments(pathList);
    }

    /**
     * Gets the method/constructor name of an operation.
     */
    public static String getOperationCallableName(
            Operation operation
    ) {
        List<String> pathList = TypeUtils.getNameSegments(operation.getName());
        return TypeUtils.getClassNameFromNameSegments(pathList);
    }

    /**
     * Chunks a list of objects into multiple lists.
     *
     * @param list the flattened list of objects
     * @param chunkSize the number of objects per chunk
     * @return a list of lists of objects
     * @param <T> an arbitrary object
     */
    public static <T> List<List<T>> splitListIntoChunks(List<T> list, int chunkSize) {
        List<List<T>> chunks = new ArrayList<>();
        for (int i = 0; i < list.size(); i += chunkSize) {
            int endIndex = Math.min(i + chunkSize, list.size());
            chunks.add(list.subList(i, endIndex));
        }
        return chunks;
    }
}
