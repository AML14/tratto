import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class OracleRemoverTest {
    /** The path to the output directory. */
    private static final Path output = Paths.get("output");

    @Test
    public void removeOraclesTest() throws Throwable {
        FileUtils.deleteDirectory(output);
        FileUtils.createDirectories(Paths.get("output", "evosuite-tests", "example"));
        Files.copy(
                Paths.get("src", "test", "resources", "test", "ExampleTest.java"),
                Paths.get("output", "evosuite-tests", "example", "ExampleTest.java")
        );
        OracleRemover.removeOracles("example.ExampleTest");
        try {
            CompilationUnit cu = StaticJavaParser.parse(Paths.get("output", "evosuite-prefixes", "example", "ExampleTest.java"));
            List<MethodDeclaration> testCases = cu.findAll(MethodDeclaration.class);
            assertEquals(4, testCases.size());
            String expectedAssertionTest0 = """
                    @Test
                    @Disabled
                    public void assertionTest0() {
                        // an example of an assertion oracle
                        List<String> nonEmptyList = List.of("example");
                        nonEmptyList.get(0);
                    }""";
            assertEquals(expectedAssertionTest0, testCases.get(0).toString());
            String expectedMultipleAssertionTest1 = """
                    @Test
                    @Disabled
                    public void multipleAssertionTest1() {
                        List<String> nonEmptyList = new ArrayList<>(List.of("example"));
                        nonEmptyList.get(0);
                    }""";
            assertEquals(expectedMultipleAssertionTest1, testCases.get(1).toString());
            String expectedMultipleAssertionTest2 = """
                    @Test
                    @Disabled
                    public void multipleAssertionTest2() {
                        List<String> nonEmptyList = new ArrayList<>(List.of("example"));
                        nonEmptyList.add("Random statement to complicate things");
                    }""";
            assertEquals(expectedMultipleAssertionTest2, testCases.get(2).toString());
            String expectedExceptionalTest3 = """
                    @Test
                    @Disabled
                    public void exceptionalTest3() {
                        List<String> emptyList = new ArrayList<>();
                        String element = emptyList.get(0);
                    }""";
            assertEquals(expectedExceptionalTest3, testCases.get(3).toString());
        } catch (IOException e) {
            fail();
        }
        FileUtils.deleteDirectory(output);
    }
}
