import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.stmt.Statement;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides the functionality for removing oracles from a test
 * suite.
 */
public class OracleRemover {
    /** A global unique ID to avoid duplicate test names. */
    private static int testID = 0;
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
     * This method assumes each JUnit Assertions method call contains at most
     * one other method call.
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
     * This method assumes each JUnit Assertions method call contains at most
     * one method call. This method does not modify the actual source file.
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
     * Gets a new test case corresponding to a specific oracle in the original
     * EvoSuite test case. An EvoSuite test may contain multiple oracles. For
     * compatibility with TOGA, each test is split into multiple subtests,
     * each corresponding to a single oracle in the original test case.
     *
     * @param testCase a test case
     * @param oracleIdx the index of the oracle in the test case. Must be
     *                  less than the number of oracles in the test case.
     * @return a test case with a single oracle, corresponding to the given
     * index
     */
    private static MethodDeclaration getSimpleTestCase(MethodDeclaration testCase, int oracleIdx) {
        return null;
    }

    /**
     * Gets the number of oracles in a given EvoSuite test case. In an
     * EvoSuite test case, an assertion oracle is represented by a JUnit
     * Assertions assert method call and an exceptional oracle is represented
     * by a try/catch block.
     *
     * @param testCase a test case
     * @return the number of oracles in the test case
     */
    private static int getNumberOfOracles(MethodDeclaration testCase) {
        return -1;
    }

    /**
     * Splits all test cases in a given test file into smaller subtests, each
     * with a single oracle from the original test case. The original tests
     * with multiple assertions are removed. This method does not modify the
     * actual source file.
     *
     * @param testFile a JavaParser representation of a test file
     */
    private static void splitTests(CompilationUnit testFile) {
    }

    /**
     * Removes all assertions from all EvoSuite tests generated for a given
     * class. The approach for removing oracles depends on whether an oracle
     * is exceptional or a normal assertion. Firstly, this method splits any
     * test case with multiple assertions into multiple simple tests, each
     * with a single JUnit assertion. The smaller subtests are saved in
     * "output/evosuite-simple-tests" and the test prefixes are saved in
     * "output/evosuite-prefixes". This method does not override the original
     * test files.
     *
     * @param fullyQualifiedName the fully qualified name of the class under
     *                           test
     * @see OracleRemover#splitTests(CompilationUnit)
     * @see OracleRemover#removeExceptionalOracles(CompilationUnit)
     * @see OracleRemover#removeAssertionOracles(CompilationUnit)
     */
    public static void removeOracles(String fullyQualifiedName) {
    }
}
