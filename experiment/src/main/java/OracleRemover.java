import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.stmt.Statement;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides the functionality for removing oracles from a test
 * suite.
 */
public class OracleRemover {
    /** A global unique ID to avoid duplicate test names. */
    private static int testID = 0;
    /** The path to the output directory. */
    private static final Path output = Paths.get("output");
    /** A list of all JUnit Assertions assert methods. */
    private static final List<String> allJUnitAssertionsMethods = List.of(
            "assertArrayEquals",
            "assertEquals",
            "assertFalse",
            "assertNotNull",
            "assertNotSame",
            "assertNull",
            "assertSame",
            "assertThat",
            "assertTrue"
    );

    /** Private constructor to avoid creating an instance of this class. */
    private OracleRemover() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    /**
     * Checks if a given statement represents a method call to a JUnit Assert
     * method (e.g. {@code assertEquals}). This does NOT include {@code fail},
     * which is used by exceptional oracles.
     *
     * @param statement a Java statement
     * @return true iff the statement is a method call of a JUnit Assertions
     * assert method
     * @see OracleRemover#allJUnitAssertionsMethods
     * @see OracleRemover#isFail(Statement)
     */
    private static boolean isJUnitAssertion(Statement statement) {
        return false;
    }

    /**
     * Checks if a given statement is a JUnit Assertions fail statement. These
     * are used in exceptional oracles to ensure that a program state throws
     * an exception.
     *
     * @param statement a Java statement
     * @return true iff the statement is a {@code fail()} method call
     */
    private static boolean isFail(Statement statement) {
        return false;
    }


    /**
     * Gets all method calls in a Java statement.
     *
     * @param statement a Java statement
     * @return all method calls present in the statement
     */
    private static List<MethodCallExpr> getAllMethodCallsOfStatement(Statement statement) {
        return new ArrayList<>();
    }

    /**
     * Returns the method call in a JUnit Assertions condition. Returns null
     * if the condition does not have a method call. For example,
     * <pre>
     *     {@code assertEquals(stack.isEmpty()}    -&gt;    {@code stack.isEmpty()}
     * or,
     *     {@code assertTrue(booleanVar}    -&gt;    {@code null}
     * </pre>
     * This method assumes that a JUnit Assertions only has a single method
     * call in its condition.
     *
     * @param jUnitAssertion a JUnit Assertion statement
     * @return the method call in the JUnit Assertion condition. Returns null
     * if the condition does not have a method call.
     * @throws IllegalArgumentException if the given statement is not a JUnit
     * Assertion
     */
    private static MethodCallExpr getMethodCallOfJUnitAssertion(Statement jUnitAssertion) {
        return null;
    }

    /**
     * Removes all assertion oracles from a given test file, represented by a
     * JavaParser compilation unit. Assertion oracles are represented by JUnit
     * Assertions method calls (e.g. {@code assertEquals}). If a JUnit
     * Assertions condition contains a method call, then the method call is
     * kept in the prefix. For example,
     *     {@code assertTrue(stack.isEmpty())}    -&gt;    {@code stack.isEmpty()}.
     * This method does not modify the actual source file.
     *
     * @param testFile a JavaParser representation of a test file
     */
    private static void removeAssertionOracles(CompilationUnit testFile) {
    }

    /**
     * Removes all exceptional oracles from a given test file, represented by
     * a JavaParser compilation unit. The prefix in the try/catch block is
     * kept, but any {@code fail()} method calls are removed. For example,
     * <pre>
     * {@code
     *     int x = 5;
     *     try {
     *         int y = 10;
     *         fail();
     *     } catch (Exception e) {}
     * }
     * becomes,
     * {@code
     *     int x = 5;
     *     int y = 10;
     * }
     * </pre>
     * This method does not modify the actual source file.
     *
     * @param testFile a JavaParser representation of a test file
     */
    private static void removeExceptionalOracles(CompilationUnit testFile) {
    }

    /**
     * Creates a related method based on a given original method. The original
     * method is given a new body and a new name. The new name is the same as
     * the original method name, but with a global ID appended to avoid
     * repeating a method name.
     *
     * @param original the original method
     * @param newBody the new method body
     * @return the new method
     */
    private static MethodDeclaration createRelatedMethod(
            MethodDeclaration original,
            NodeList<Statement> newBody
    ) {
        return new MethodDeclaration();
    }

    /**
     * Gets a new test case corresponding to a specific assertion in the
     * original test case. An EvoSuite test may contain multiple assertions.
     * For compatibility with TOGA, each test is split into multiple subtests,
     * each corresponding to a single assertion in the original test case.
     *
     * @param testCase a test case
     * @param assertionIdx the index of the assertion in the test case. Must
     *                     be less than the number of assertions in the test
     *                     case.
     * @return a test case with a single assertion, corresponding to the given
     * assertion index
     */
    private static MethodDeclaration getSimpleTestCase(MethodDeclaration testCase, int assertionIdx) {
        return new MethodDeclaration();
    }

    /**
     * Gets the number of JUnit Assertions assert method calls in a given test
     * case. This method does NOT count {@code fail()} calls.
     *
     * @param testCase a test case
     * @return the number of JUnit Assertions assert method calls in the test
     * case
     */
    private static int getNumberOfAssertions(MethodDeclaration testCase) {
        return -1;
    }

    /**
     * Splits all test cases in a given test file into smaller subtests, each
     * with a single assertion from the original test case. If a test case
     * does not contain a JUnit Assertions assert method call (e.g.
     * exceptional oracle), then it is not modified. The original tests with
     * multiple assertions are removed. This method does not modify the actual
     * source file.
     *
     * @param testFile a JavaParser representation of a test file
     */
    private static void splitTests(CompilationUnit testFile) {
    }

    /**
     * Gets the path to the output directory for a given fully qualified name.
     * The FQN path converts the package names as subdirectories for a given
     * output base directory. For example,
     * {@code "baseDir", "com.example.MyClass"}    -&gt;
     * {@code output/baseDir/com/example/MyClass}
     *
     * @param fullyQualifiedName a fully qualified name
     * @return the output path for a given fully qualified name
     */
    private static Path getFQNOutputPath(String baseDir, String fullyQualifiedName) {
        return null;
    }

    /**
     * Removes all assertions from all test files in a given directory. The
     * approach for removing oracles depends on whether an oracle is
     * exceptional (e.g. throws an exception) or a normal assertion. Firstly,
     * this method splits any test case with multiple assertions into multiple
     * simple tests, each with a single JUnit assertion. These smaller
     * subtests are saved in "output/evosuite-tests-simple/". Then, all
     * oracles are removed from each test case to create test prefixes. The
     * test prefixes are saved in "output/evosuite-prefix". This method does
     * not override the original test files.
     *
     * @param dir a directory with Java test files
     * @see OracleRemover#splitTests(CompilationUnit)
     * @see OracleRemover#removeExceptionalOracles(CompilationUnit)
     * @see OracleRemover#removeAssertionOracles(CompilationUnit)
     */
    public static void removeOracles(Path dir, String fullyQualifiedName) {
    }
}
