package star.tratto.util.javaparser;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.stmt.BlockStmt;
import org.javatuples.Pair;
import org.javatuples.Quartet;
import star.tratto.dataset.oracles.JDoctorCondition.*;
import star.tratto.exceptions.PackageDeclarationNotFoundException;
import star.tratto.identifiers.JPCallableType;
import star.tratto.identifiers.Javadoc;
import star.tratto.identifiers.file.*;
import star.tratto.identifiers.path.Path;
import star.tratto.util.FileUtils;

import static star.tratto.util.javaparser.JavaParserUtils.*;

import java.io.File;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DatasetUtils {
    public static String getPackageName(
            Operation operation
    ) {
        List<String> pathList = JDoctorUtils.getPathList(operation.getClassName());
        List<String> packageList = JDoctorUtils.getPackageList(pathList);
        return JDoctorUtils.getPackageNameFromPackageList(packageList);
    }

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
     * @param jpClass a JavaParser class.
     * @return A string {@link String} representing the Javadoc comment.
     */
    public static String getClassJavadoc(
            TypeDeclaration<?> jpClass
    ) {
        Optional<JavadocComment> jpJavadocComment = jpClass.getJavadocComment();
        if (jpJavadocComment.isEmpty()) return getJavadocByPattern(jpClass);
        return Javadoc.CLASS_PREFIX.getValue() + jpJavadocComment.get().getContent() + Javadoc.CLASS_SUFFIX.getValue();
    }

    /**
     * Gets the Javadoc comment of the BodyDeclaration.
     *
     * @param jpCallable a JavaParser function.
     * @return A string {@link String} representing the Javadoc comment.
     */
    public static String getCallableJavadoc(
            CallableDeclaration<?> jpCallable
    ) {
        Optional<JavadocComment> jpJavadocComment = jpCallable.getJavadocComment();
        if (jpJavadocComment.isEmpty()) return getJavadocByPattern(jpCallable);
        return Javadoc.METHOD_PREFIX.getValue() + jpJavadocComment.get().getContent() + Javadoc.METHOD_SUFFIX;
    }

    public static String getCallableSourceCode(
            CallableDeclaration<?> jpCallable
    ) {
        JPCallableType jpCallableType = jpCallable.isConstructorDeclaration() ? JPCallableType.CONSTRUCTOR : JPCallableType.METHOD;
        String jpSignature = JavaParserUtils.getCallableSignature(jpCallable);
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
                            JavaParserUtils.getCallableSignature(jpMethod)
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
                .collect(Collectors.toList());
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

    public static String getClassName(
            Operation operation
    ) {
        List<String> pathList = JDoctorUtils.getPathList(operation.getClassName());
        return JDoctorUtils.getClassNameFromPathList(pathList);
    }

    public static String getCallableName(
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
     * @param jDoctorParamList list of JDoctor parameters.
     * @param jpParamList list of JavaParser parameters.
     * @param jpCallable CallableDeclaration corresponding to jpParamList.
     * @param jpClass TypeDeclaration corresponding to jpCallable.
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
            )) return false;
        }
        return true;
    }

    /**
     * Gets the CallableDeclaration from a given TypeDeclaration with a
     * specified name and parameters.
     *
     * @param jpClass the TypeDeclaration containing the method.
     * @param targetName the name of the desired method.
     * @param targetParamList the parameters of the desired method.
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
     * @param cu the compilation unit of a file.
     * @param className the name of the desired class.
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
     * @param operation a JDoctor operation object of a JDoctor condition.
     * @param sourcePath the source path of the relevant project.
     * @return An optional JavaParser compilation unit {@link CompilationUnit}
     * corresponding to the class of the JDoctor condition, if it is found.
     * Otherwise, the method returns an empty optional.
     */
    public static Optional<CompilationUnit> getClassCompilationUnit(
            Operation operation,
            String sourcePath
    ) {
        List<String> pathList = Arrays.asList(operation.getClassName().split("\\."));
        String classPath = Paths.get(sourcePath, pathList.toArray(String[]::new)) + FileFormat.JAVA.getValue();
        return getCompilationUnitFromFilePath(classPath);
    }
}
