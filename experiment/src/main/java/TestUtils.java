import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.CatchClause;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.TryStmt;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

/**
 * This class provides static methods for removing oracles from a test suite,
 * and inserting oracles into test prefixes.
 */
public class TestUtils {
    /** The path to the output directory */
    private static final Path output = Paths.get("output");
    /** A list of all JUnit Assertions assert methods */
    private static final List<String> allJUnitAssertMethods = List.of(
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

    // private constructor to avoid creating an instance of this class.
    private TestUtils() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    /**
     * Checks if a given statement is an assertion.
     *
     * @param statement a code statement
     * @return true iff the statement uses the "assert" keyword or uses a
     * JUnit assert method (e.g. assertEquals)
     */
    private static boolean isAssertion(Statement statement) {
        // check if statement uses "assert" keyword
        if (statement.isAssertStmt()) {
            return true;
        }
        // check if statement uses JUnit Assertions assert method
        if (statement.isExpressionStmt()) {
            Expression expression = statement.asExpressionStmt().getExpression();
            if (expression.isMethodCallExpr()) {
                MethodCallExpr methodCallExpr = expression.asMethodCallExpr();
                return allJUnitAssertMethods.contains(methodCallExpr.getNameAsString());
            }
        }
        return false;
    }

    /**
     * Checks if a given statement is a fail statement. These are used in
     * exceptional oracles to ensure that a given prefix throws an exception.
     *
     * @param statement a code statement
     * @return true iff the statement represents a "fail" method call
     */
    private static boolean isFail(Statement statement) {
        if (statement.isExpressionStmt()) {
            Expression expression = statement.asExpressionStmt().getExpression();
            if (expression.isMethodCallExpr()) {
                MethodCallExpr methodCallExpr = expression.asMethodCallExpr();
                return methodCallExpr.getNameAsString().equals("fail");
            }
        }
        return false;
    }

    /**
     * Removes all normal assertion oracles from a given test file.
     *
     * @param testFile a JavaParser representation of a test file
     */
    private static void removeAssertionOracles(CompilationUnit testFile) {
        testFile.findAll(Statement.class).forEach(statement -> {
            if (isAssertion(statement)) {
                statement.remove();
            }
        });
    }

    /**
     * Removes all exceptional oracles from a given test case.
     *
     * @param testFile a JavaParser representation of a test case as a method
     */
    static void removeExceptionalOracles(CompilationUnit testFile) {
        // remove all try/catch blocks
        testFile.findAll(TryStmt.class).forEach(tryStmt -> {
            BlockStmt testCase = (BlockStmt) tryStmt.getParentNode().orElseThrow();
            NodeList<Statement> prefix = tryStmt.getTryBlock().getStatements();
            int prefixLocation = testCase.getStatements().indexOf(tryStmt);
            testCase.getStatements().addAll(prefixLocation, prefix);
            tryStmt.remove();
        });
        // remove "fail()" statements
        testFile.findAll(Statement.class).forEach(statement -> {
            if (isFail(statement)) {
                statement.remove();
            }
        });
    }

    /**
     * Removes all assertions from all test files in a given directory. Our
     * approach for removing oracles depends on whether an oracle is
     * exceptional (e.g. throws an exception) or a normal assertion. Saves the
     * modified test cases in output/evosuite-prefix/. Does not override
     * original test files at the given path.
     *
     * @param dir a directory with Java test files
     * @see TestUtils#removeAssertionOracles(CompilationUnit)
     * @see TestUtils#removeExceptionalOracles(CompilationUnit)
     */
    public static void removeOracles(Path dir) {
        Path prefixPath = output.resolve("evosuite-prefix");
        FileUtils.copy(dir, prefixPath);
        try (Stream<Path> walk = Files.walk(prefixPath)) {
            walk
                    .filter(FileUtils::isJavaFile)
                    .forEach(testFile -> {
                        try {
                            CompilationUnit cu = StaticJavaParser.parse(testFile);
                            removeAssertionOracles(cu);
                            removeExceptionalOracles(cu);
                            FileUtils.writeString(testFile, cu.toString());
                        } catch (IOException e) {
                            throw new Error("Unable to parse test file " + testFile.getFileName().toString());
                        }
                    });
        } catch (IOException e) {
            throw new Error("Unable to parse files in directory " + dir.toString());
        }
    }

    /**
     * Adds axiomatic oracles to test prefixes in a given directory. Axiomatic
     * oracles are not specific to a given test prefix. Therefore, we insert
     * the axiomatic oracles wherever they may be applicable in source code.
     * For example, if an axiomatic oracle involves class Foo, then the oracle
     * is added after every instance of Foo in all test prefixes.
     *
     * @param dir a directory with Java test prefixes
     * @param oracles a list of test oracles made by an axiomatic tog
     */
    private static void insertAxiomaticOracles(Path dir, List<OracleOutput> oracles) {

    }

    /**
     * Adds non-axiomatic oracles to test prefixes in a given directory.
     * Non-axiomatic oracles are specific to a given test prefix. We assume
     * the tests in the directory appear in the same order as the oracles.
     * Each oracle is added as an assertion at the end of the test prefix.
     *
     * @param dir a directory with Java test prefixes
     * @param oracles a list of test oracles made by a non-axiomatic tog
     */
    private static void insertNonAxiomaticOracles(Path dir, List<OracleOutput> oracles) {

    }

    /**
     * @param tog a test oracle generator
     * @return true iff the given tog generates axiomatic test oracles (known
     * a priori)
     */
    private static boolean isAxiomatic(String tog) {
        return false;
    }

    /**
     * Adds oracles to test prefixes in a given directory. Our approach for
     * adding oracles varies based on whether the oracles are axiomatic or
     * non-axiomatic. Saves the modified test prefixes in
     * output/tog-test/[tog], where [tog] is the given test oracle generator.
     * Does not override original test prefixes.
     *
     * @param dir a directory with Java test prefixes
     * @param tog the name of the test oracle generator being analyzed
     * @param oracles a list of test oracles made by the given tog
     * @see TestUtils#insertAxiomaticOracles(Path, List)
     * @see TestUtils#insertNonAxiomaticOracles(Path, List)
     */
    public static void insertOracles(Path dir, String tog, List<OracleOutput> oracles) {

    }
}
