import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OracleRemoverTest {
    /** The path to the output directory. */
    private static final Path output = Paths.get("output");

    private void setup() {
        FileUtils.deleteDirectory(output);
        FileUtils.createDirectories(Paths.get("output", "evosuite-tests", "tutorial"));
        FileUtils.copy(
                Paths.get("src", "test", "resources", "test"),
                Paths.get("output", "evosuite-tests")
        );
    }

    private void verifySimpleTests() {
        Path simplePath = FileUtils.getFQNOutputPath("tutorial.Stack", "evosuite-simple-tests");
        CompilationUnit cu;
        try {
            cu = StaticJavaParser.parse(simplePath);
        } catch (IOException e) {
            throw new IllegalArgumentException("Unable to parse simple test " + simplePath);
        }
        List<MethodDeclaration> simpleTests = cu.findAll(MethodDeclaration.class);
        assertEquals(5, simpleTests.size());
        assertEquals(
                """
                        @Test(timeout = 4000)
                        public void test00() throws Throwable {
                            Stack<String> stack0 = new Stack<String>();
                            assertTrue(stack0.isEmpty());
                        }""",
                simpleTests.get(0).toString()
        );
        assertEquals(
                """
                        @Test(timeout = 4000)
                        public void test01() throws Throwable {
                            Stack<String> stack0 = new Stack<String>();
                            stack0.push("x$P bOeg1/`gL<|");
                            stack0.push("fI3?H~2wGr~\\"[9VZ");
                            String string0 = stack0.pop();
                            assertEquals("fI3?H~2wGr~\\"[9VZ", string0);
                        }""",
                simpleTests.get(1).toString()
        );
        assertEquals(
                """
                        @Test(timeout = 4000)
                        public void test12() throws Throwable {
                            Stack<String> stack0 = new Stack<String>();
                            stack0.push("i.%");
                            stack0.pop();
                            boolean boolean0 = stack0.isEmpty();
                            assertTrue(boolean0);
                        }""",
                simpleTests.get(2).toString()
        );
        assertEquals(
                """
                        @Test(timeout = 4000)
                        public void test23() throws Throwable {
                            Stack<Object> stack0 = new Stack<Object>();
                            // Undeclared exception!
                            try {
                                stack0.push((Object) null);
                                fail("Expecting exception: IllegalArgumentException");
                            } catch (IllegalArgumentException e) {
                                //
                                // The given object is null!
                                //
                                verifyException("tutorial.Stack", e);
                            }
                        }""",
                simpleTests.get(3).toString()
        );
        assertEquals(
                """
                        @Test(timeout = 4000)
                        public void test34() throws Throwable {
                            Stack<String> stack0 = new Stack<String>();
                            stack0.push("x$P bOeg1/`gL<|");
                            stack0.push("fI3?H~2wGr~\\"[9VZ");
                            stack0.push("x$P bOeg1/`gL<|");
                            stack0.push("x$P bOeg1/`gL<|");
                            stack0.push("");
                            stack0.push("x$P bOeg1/`gL<|");
                            stack0.push("");
                            stack0.push("");
                            stack0.push("");
                            stack0.push("");
                            // Undeclared exception!
                            try {
                                stack0.push("");
                                fail("Expecting exception: RuntimeException");
                            } catch (RuntimeException e) {
                                //
                                // Stack exceeded capacity!
                                //
                                verifyException("tutorial.Stack", e);
                            }
                        }""",
                simpleTests.get(4).toString()
        );
    }

    private void verifyPrefixes() {
        Path simplePath = FileUtils.getFQNOutputPath("tutorial.Stack", "evosuite-prefixes");
        CompilationUnit cu;
        try {
            cu = StaticJavaParser.parse(simplePath);
        } catch (IOException e) {
            throw new IllegalArgumentException("Unable to parse simple test " + simplePath);
        }
        List<MethodDeclaration> simpleTests = cu.findAll(MethodDeclaration.class);
        assertEquals(5, simpleTests.size());
        assertEquals(
                """
                        @Test(timeout = 4000)
                        public void test00() throws Throwable {
                            Stack<String> stack0 = new Stack<String>();
                            stack0.isEmpty();
                        }""",
                simpleTests.get(0).toString()
        );
        assertEquals(
                """
                        @Test(timeout = 4000)
                        public void test01() throws Throwable {
                            Stack<String> stack0 = new Stack<String>();
                            stack0.push("x$P bOeg1/`gL<|");
                            stack0.push("fI3?H~2wGr~\\"[9VZ");
                            String string0 = stack0.pop();
                        }""",
                simpleTests.get(1).toString()
        );
        assertEquals(
                """
                        @Test(timeout = 4000)
                        public void test12() throws Throwable {
                            Stack<String> stack0 = new Stack<String>();
                            stack0.push("i.%");
                            stack0.pop();
                            boolean boolean0 = stack0.isEmpty();
                        }""",
                simpleTests.get(2).toString()
        );
        assertEquals(
                """
                        @Test(timeout = 4000)
                        public void test23() throws Throwable {
                            Stack<Object> stack0 = new Stack<Object>();
                            stack0.push((Object) null);
                        }""",
                simpleTests.get(3).toString()
        );
        assertEquals(
                """
                        @Test(timeout = 4000)
                        public void test34() throws Throwable {
                            Stack<String> stack0 = new Stack<String>();
                            stack0.push("x$P bOeg1/`gL<|");
                            stack0.push("fI3?H~2wGr~\\"[9VZ");
                            stack0.push("x$P bOeg1/`gL<|");
                            stack0.push("x$P bOeg1/`gL<|");
                            stack0.push("");
                            stack0.push("x$P bOeg1/`gL<|");
                            stack0.push("");
                            stack0.push("");
                            stack0.push("");
                            stack0.push("");
                            stack0.push("");
                        }""",
                simpleTests.get(4).toString()
        );
    }

    @Test
    public void removeOraclesTest() {
        setup();
        OracleRemover.removeOracles("tutorial.Stack");
        verifySimpleTests();
        verifyPrefixes();
        FileUtils.deleteDirectory(output);
    }
}
