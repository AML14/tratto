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
import star.tratto.dataset.oracles.JDoctorCondition.*;
import star.tratto.exceptions.JPClassNotFoundException;
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

    public static String getClassJavadoc(
            Operation operation,
            String sourcePath
    ) {
        String javadoc = "";
        // get class of compilation unit.
        Optional<CompilationUnit> cu = getClassCompilationUnit(operation, sourcePath);
        if (cu.isPresent()) {
            try {
                javadoc = JavaParserUtils.getJavadocFromCompilationUnit(cu.get());
            } catch (PrimaryTypeNotFoundException e) {
                // return empty source code if class is not found.
                e.printStackTrace();
                return javadoc;
            }
        }
        return javadoc;
    }

    public static String getClassSourceCode(
            Operation operation,
            String sourcePath
    ) {
        String source = "";
        // get class of compilation unit.
        Optional<CompilationUnit> cu = getClassCompilationUnit(operation, sourcePath);
        if (cu.isPresent()) {
            try {
                source = JavaParserUtils.getSourceFromCompilationUnit(cu.get());
            } catch (PrimaryTypeNotFoundException e) {
                // return empty source code if class is not found.
                e.printStackTrace();
                return source;
            }
        }
        return source;
    }

    public static String getPackageName(
            Operation operation
    ) {
        List<String> pathList = JDoctorUtils.getPathList(operation.getClassName());
        List<String> packageList = JDoctorUtils.getPackageList(pathList);
        return JDoctorUtils.getPackageNameFromPackageList(packageList);
    }

    private static List<Pair<String, String>> getClassNameAndPackageFromCompilationUnit(
            CompilationUnit cu
    ) throws PackageDeclarationNotFoundException {
        List<Pair<String, String>> pairList = new ArrayList<>();
        // get all classes in compilation unit.
        List<TypeDeclaration<?>> jpClasses = cu.getTypes();
        // get package.
        PackageDeclaration jpPackage = getPackageDeclarationFromCompilationUnit(cu);
        String packageName = jpPackage.getNameAsString();
        // add pair for each class.
        for (TypeDeclaration<?> jpClass : jpClasses) {
            String className = jpClass.resolve().getClassName();
            pairList.add(new Pair<>(className, packageName));
        }
        return pairList;
    }

    public static List<Pair<String, String>> getTokensProjectClasses(
            String sourcePath
    ) {
        // get all files from source.
        File sourceDir = new File(sourcePath);
        List<File> javaFiles = FileUtils.extractJavaFilesFromDirectory(sourceDir);
        List<Pair<String, String>> projectClasses = new ArrayList<>();
        // get list of files to ignore.
        String ignoreFilePath = Paths.get(
                Path.REPOS.getValue(),
                FileName.IGNORE_FILE.getValue() + FileFormat.JSON.getValue()
        ).toString();
        List<String> ignoreFileList = FileUtils.readJSONList(ignoreFilePath)
                .stream()
                .map(e -> (String) e)
                .collect(Collectors.toList());
        // iterate through each file and add class tokens.
        for (File javaFile : javaFiles) {
            String javaFilename = javaFile.getName().replace(FileFormat.JAVA.getValue(), "");
            // ignore specified files.
            if (ignoreFileList.contains(javaFilename)) {
                continue;
            }
            String filePath = javaFile.getAbsolutePath();
            Optional<CompilationUnit> cu = JavaParserUtils.getCompilationUnitFromFilePath(filePath);
            // ignore if CompilationUnit is empty.
            if (cu.isEmpty()) {
                continue;
            }
            // add (package, class) token pairs.
            try {
                projectClasses.addAll(getClassNameAndPackageFromCompilationUnit(cu.get()));
            } catch (PackageDeclarationNotFoundException e) {
                e.printStackTrace();
            }
        }
        return projectClasses;
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
