package star.tratto.util.javaparser;

import com.github.javaparser.ast.CompilationUnit;
import star.tratto.dataset.oracles.JDoctorCondition.*;
import star.tratto.exceptions.PrimaryTypeNotFoundException;
import star.tratto.identifiers.file.*;
import star.tratto.identifiers.JavadocValueType;
import static star.tratto.util.javaparser.JavaParserUtils.*;

import org.javatuples.Pair;

import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DatasetUtils {

    private <T> List<T> removeDuplicates(List<T> list) {
        Set<T> set = new LinkedHashSet<>(list);
        return new ArrayList<>(set);
    }

    public static String getSourceCode(
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

    public static Optional<CompilationUnit> getClassCompilationUnit(
            Operation operation,
            String sourcePath
    ) {
        List<String> pathList = Arrays.asList(operation.getClassName().split("\\."));
        String classPath = Paths.get(sourcePath, pathList.toArray(String[]::new)) + FileFormat.JAVA.getValue();
        return getCompilationUnitFromFilePath(classPath);
    }
}
