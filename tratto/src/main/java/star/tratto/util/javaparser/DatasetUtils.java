package star.tratto.util.javaparser;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.CallableDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.stmt.BlockStmt;
import org.javatuples.Pair;
import org.javatuples.Quartet;
import star.tratto.dataset.oracles.JDoctorCondition.*;
import star.tratto.exceptions.PackageDeclarationNotFoundException;
import star.tratto.exceptions.PrimaryTypeNotFoundException;
import star.tratto.identifiers.JPCallableType;
import star.tratto.identifiers.file.*;
import star.tratto.identifiers.path.Path;
import star.tratto.util.FileUtils;

import static star.tratto.util.javaparser.JavaParserUtils.*;

import java.io.File;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class DatasetUtils {
    private String getCallableSourceCode(
            CallableDeclaration<?> jpCallable,
            JPCallableType jpCallableType
    ) {
        String jpSignature = JavaParserUtils.getCallableSignature(jpCallable);
        Optional<BlockStmt> jpBody = jpCallableType == JPCallableType.CONSTRUCTOR ?
                Optional.ofNullable(((ConstructorDeclaration) jpCallable).getBody()) :
                ((MethodDeclaration) jpCallable).getBody();
        return jpSignature + (jpBody.isEmpty() ? ";" : jpBody.get().toString());
    }

    /*
    public static String getMethodSourceCode(
            Operation operation,
            String sourcePath
    ) {
        String className = operation.getClassName();
        String methodName = operation.getName();
        List<String> jDoctorConditionParamTypeNames = operation.getParameterTypes();
        String methodSourceCode = "";
        Optional<CompilationUnit> cu = getClassCompilationUnit(operation, sourcePath);

        if (cu.isPresent()) {
            if (className.equals(methodName)) {
                // get constructor source code.
            } else {
                // get method source code.
                // MethodDeclaration jpMethod = getMethodDeclaration(cu, methodName, jDoctorConditionParamTypeNames);
                // methodSourceCode = JavaParserUtils.
            }
        }

        return methodSourceCode;
    }
    */

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

    public static String getClassName(
            Operation operation
    ) {
        List<String> pathList = JDoctorUtils.getPathList(operation.getClassName());
        return JDoctorUtils.getClassNameFromPathList(pathList);
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
