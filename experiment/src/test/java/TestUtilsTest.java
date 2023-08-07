import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.AssertStmt;
import com.github.javaparser.ast.stmt.Statement;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestUtilsTest {
    private static final Path examplePath = Paths.get("src/test/java/example");
    private static final Path outputPath = Paths.get("output");

    @Test
    public void removeOraclesTest() {
        TestUtils.removeOracles(examplePath);
        FileUtils.deleteDirectory(outputPath.resolve("evosuite-prefix"));
    }
}
