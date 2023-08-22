import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.TryStmt;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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

    /** Private constructor to avoid creating an instance of this class. */
    private TestUtils() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    /**
     * Checks if a given statement is a JUnit Assertions assert method call
     * (e.g. assertEquals). This does NOT include "fail()", which is used by
     * exceptional oracles.
     *
     * @param statement a code statement
     * @return true iff the statement uses an "assert..." method from the
     * JUnit Assertions class
     */
    private static boolean isJUnitAssertion(Statement statement) {
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
     * exceptional oracles to ensure that a prefix throws an exception.
     *
     * @param statement a code statement
     * @return true iff the statement is a "fail" method call
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
     * Gets all method calls of a Java statement.
     *
     * @param statement a Java statement
     * @return a list of all methods referenced by the statement
     */
    private static List<MethodCallExpr> getAllMethodCallsOfStatement(Statement statement) {
        List<MethodCallExpr> methodCallExprs = new ArrayList<>();
        statement.walk(MethodCallExpr.class, methodCallExprs::add);
        return methodCallExprs;
    }

    /**
     * Returns the method call in a JUnit assertion condition. Returns null if
     * the condition does not have a method call. For example,
     *     "{@code assertEquals(stack.isEmpty())}" =>
     *     "{@code stack.isEmpty()}".
     * This method assumes that a JUnit assertion only has a single method
     * call in its condition.
     *
     * @param jUnitAssertion a JUnit assertion
     * @return the method call in a given JUnit assertion. Returns null if the
     * JUnit condition does not have a method call.
     * @throws IllegalArgumentException if the given statement is not a JUnit
     * assertion
     */
    private static MethodCallExpr getMethodCallOfJUnitAssertion(Statement jUnitAssertion) {
        if (!isJUnitAssertion(jUnitAssertion)) {
            throw new IllegalArgumentException(jUnitAssertion + " is not a statement.");
        }
        List<MethodCallExpr> nonJUnitMethods = getAllMethodCallsOfStatement(jUnitAssertion)
                .stream()
                .filter(methodCallExpr -> !allJUnitAssertMethods.contains(methodCallExpr.getNameAsString()))
                .toList();
        if (nonJUnitMethods.isEmpty()) {
            return null;
        } else {
            return nonJUnitMethods.get(0);
        }
    }

    /**
     * Creates a duplicate method based on a given original method. The
     * original method is given a new body, and a new name, corresponding
     * to the given index.
     *
     * @param original the original method
     * @param newBody the new method body
     * @param assertionIdx the duplicate index
     * @return a duplicate method test case
     */
    private static MethodDeclaration createMethodDuplicate(
            MethodDeclaration original,
            NodeList<Statement> newBody,
            int assertionIdx
    ) {
        String duplicateName = original.getNameAsString() + "_" + assertionIdx;
        MethodDeclaration duplicate = original.clone();
        duplicate.setBody(new BlockStmt(newBody));
        duplicate.setName(duplicateName);
        return duplicate;
    }

    /**
     * Gets a new method prefix corresponding to a given assertion in the
     * original test case. An EvoSuite test may contain multiple assertions.
     * Each test is split into multiple prefixes, where each prefix
     * corresponds to a single assertion in the original test case.
     *
     * @param testCase a test case
     * @param assertionIdx the assertion number
     * @return a test prefix corresponding to the "assertionIdx"-th assertion
     */
    private static MethodDeclaration getNextPrefix(MethodDeclaration testCase, int assertionIdx) {
        NodeList<Statement> originalBody = testCase.getBody().orElseThrow().getStatements();
        NodeList<Statement> newBody = new NodeList<>();
        int currentIdx = 0;
        for (Statement statement : originalBody) {
            if (isJUnitAssertion(statement)) {
                if (currentIdx == assertionIdx) {
                    MethodCallExpr conditionMethodCall = getMethodCallOfJUnitAssertion(statement);
                    if (conditionMethodCall != null) {
                        newBody.add(new ExpressionStmt(conditionMethodCall));
                    }
                    break;
                }
                currentIdx++;
            } else {
                newBody.add(statement);
            }
        }
        return createMethodDuplicate(testCase, newBody, assertionIdx);
    }

    /**
     * Gets the number of JUnit assertions in a given test case.
     *
     * @param testCase a test case
     * @return the number of JUnit assertions in the test case
     */
    private static int getNumberOfAssertions(MethodDeclaration testCase) {
        NodeList<Statement> testBody = testCase.getBody().orElseThrow().getStatements();
        int numAssertions = 0;
        for (Statement statement : testBody) {
            if (isJUnitAssertion(statement)) {
                numAssertions++;
            }
        }
        return numAssertions;
    }

    /**
     * Removes all assertion oracles from a given test file, represented by a
     * JavaParser compilation unit. This method generates an individual prefix
     * per each assertion in the original test case. This method does not
     * modify the actual source file.
     *
     * @param testFile a JavaParser representation of a test file
     */
    private static void removeAssertionOracles(CompilationUnit testFile) {
        testFile.findAll(MethodDeclaration.class).forEach(testCase -> {
            TypeDeclaration<?> testClass = testFile.getType(0);
            int numAssertions = getNumberOfAssertions(testCase);
            for (int i = 0; i < numAssertions; i++) {
                MethodDeclaration prefix = getNextPrefix(testCase, i);
                testClass.addMember(prefix);
            }
            testCase.remove();
        });
    }

    /**
     * Removes all exceptional oracles from a given test file, represented by
     * a JavaParser compilation unit. The prefix in the try/catch block is
     * preserved, but any "fail" calls are removed. For example:
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
     * This method does not modify the actual source file.
     *
     * @param testFile a JavaParser representation of a test file
     */
    private static void removeExceptionalOracles(CompilationUnit testFile) {
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
                            removeExceptionalOracles(cu);
                            removeAssertionOracles(cu);
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
