package star.tratto.util.javaparser;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.CallableDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.BlockStmt;
import star.tratto.dataset.oracles.JDoctorCondition.*;
import star.tratto.exceptions.PrimaryTypeNotFoundException;
import star.tratto.identifiers.JPCallableType;
import star.tratto.identifiers.file.*;
import static star.tratto.util.javaparser.JavaParserUtils.*;

import java.nio.file.Paths;
import java.util.*;

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

    public static String getClassName(
            Operation operation
    ) {
        List<String> pathList = JDoctorUtils.getPathList(operation.getClassName());
        return JDoctorUtils.getClassNameFromPathList(pathList);
    }

    public static Optional<CompilationUnit> getClassCompilationUnit(
            Operation operation,
            String sourcePath
    ) {
        List<String> pathList = Arrays.asList(operation.getClassName().split("\\."));
        String classPath = Paths.get(sourcePath, pathList.toArray(String[]::new)) + FileFormat.JAVA.getValue();
        return getCompilationUnitFromFilePath(classPath);
    }
}
