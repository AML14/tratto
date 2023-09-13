import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class OracleRemoverTest {
    private static final Path testPath = Paths.get("src", "test", "resources", "test");

    @Test
    public void removeOraclesTest() {
        TestUtils.removeOracles(testPath, "example.ExampleTest");
        try {
            CompilationUnit cu = StaticJavaParser.parse(Paths.get("output", "evosuite-prefix", "example", "ExampleTest.java"));
            List<MethodDeclaration> testCases = cu.findAll(MethodDeclaration.class);
            assertEquals(7, testCases.size());
            String expectedAssertionTest0 = """
                    @Test
                    @Disabled
                    public void assertionTest0() {
                        // an example of an assertion oracle
                        List<String> nonEmptyList = List.of("example");
                        nonEmptyList.get(0);
                    }""";
            assertEquals(expectedAssertionTest0, testCases.get(0).toString());
        } catch (IOException e) {
            fail();
        }
        FileUtils.deleteDirectory(Paths.get("output"));
    }
}
