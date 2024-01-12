import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import data.OracleOutput;
import data.OracleType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith({OutputManager.class})
public class OracleInserterTest {
    private static final Path output = Paths.get("output");
    private static final Path resourcesPath = Paths.get("src", "test", "resources");
    private static final Path projectJarPath = Paths.get("src", "test", "resources", "project", "target", "Tutorial_Stack-1.0-SNAPSHOT.jar");
    private static final String projectClasspath = projectJarPath.toAbsolutePath().toString();

    private List<OracleOutput> getAxiomaticOracles() {
        return List.of(
                new OracleOutput(
                        "tutorial.Stack",
                        "push(java.lang.Object o)",
                        OracleType.PRE,
                        "(o == null) == false",
                        "",
                        ""
                ),
                new OracleOutput(
                        "tutorial.Stack",
                        "push(java.lang.Object o)",
                        OracleType.EXCEPT_POST,
                        "o == null",
                        "java.lang.IllegalArgumentException",
                        ""
                )
        );
    }

    private List<OracleOutput> getNonAxiomaticOracles() {
        return List.of(
                new OracleOutput(
                        "tutorial.Stack",
                        "",
                        OracleType.NON_AXIOMATIC,
                        "assertTrue(stack0.isEmpty())",
                        "",
                        "test0"
                )
        );
    }

    private void setup(boolean isAxiomatic) {
        FileUtils.deleteDirectory(output);
        // copy test suite
        FileUtils.copy(
                resourcesPath.resolve("prefix"),
                output.resolve("evosuite-prefixes")
        );
        // get oracles
        List<OracleOutput> oracles;
        String tog;
        if (isAxiomatic) {
            oracles = getAxiomaticOracles();
            tog = "jdoctor";
        } else {
            oracles = getNonAxiomaticOracles();
            tog = "toga";
        }
        // write oracles
        FileUtils.writeJSON(Paths.get("output", tog + "-oracles", "tutorial", "Stack_Oracle.json"), oracles);
    }

    private void cleanup() {
        FileUtils.deleteDirectory(output);
    }

    @Test
    public void insertAxiomaticOraclesTest() {
        setup(true);
        OracleInserter.insertOracles(
                Paths.get("output", "evosuite-prefixes"),
                Paths.get("output", "jdoctor-oracles"),
                projectClasspath
        );
        Path testPath = Paths.get("output", "jdoctor-tests", "tutorial", "Stack_ESTest.java");
        CompilationUnit cu = FileUtils.getCompilationUnit(testPath);
        List<MethodDeclaration> testCases = cu.findAll(MethodDeclaration.class);
        assertEquals(
        """
                @Test(timeout = 4000)
                public void test0() throws Throwable {
                    Stack<String> stack0 = new Stack<String>();
                }""",
                testCases.get(0).toString()
        );
        assertEquals(
        """
                @Test(timeout = 4000)
                public void test1() throws Throwable {
                    Stack<Object> stack0 = new Stack<Object>();
                    assertTrue(((Object) null == null) == false);
                    if ((Object) null == null) {
                        try {
                            stack0.push((Object) null);
                            fail();
                        } catch (java.lang.IllegalArgumentException e) {
                            // Successfully thrown exception
                        }
                    } else {
                        stack0.push((Object) null);
                    }
                }""",
                testCases.get(1).toString()
        );
        cleanup();
    }

    @Test
    public void insertNonAxiomaticOraclesTest() {
        setup(false);
        OracleInserter.insertOracles(
                Paths.get("output", "evosuite-prefixes"),
                Paths.get("output", "toga-oracles"),
                projectClasspath
        );
        Path testPath = Paths.get("output", "toga-tests", "tutorial", "Stack_ESTest.java");
        CompilationUnit cu = FileUtils.getCompilationUnit(testPath);
        List<MethodDeclaration> testCases = cu.findAll(MethodDeclaration.class);
        assertEquals(
                """
                        @Test(timeout = 4000)
                        public void test0() throws Throwable {
                            Stack<String> stack0 = new Stack<String>();
                            assertTrue(stack0.isEmpty());
                        }""",
                testCases.get(0).toString()
        );
        cleanup();
    }
}
