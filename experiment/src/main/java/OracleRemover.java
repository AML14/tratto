import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.TryStmt;

import java.io.IOException;
import java.nio.file.Path;
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
        if (statement.isExpressionStmt()) {
            Expression expression = statement.asExpressionStmt().getExpression();
            if (expression.isMethodCallExpr()) {
                MethodCallExpr methodCall = expression.asMethodCallExpr();
                return allJUnitAssertionsMethods.contains(methodCall.getNameAsString());
            }
        }
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
        if (statement.isExpressionStmt()) {
            Expression expression = statement.asExpressionStmt().getExpression();
            if (expression.isMethodCallExpr()) {
                MethodCallExpr methodCall = expression.asMethodCallExpr();
                return methodCall.getNameAsString().equals("fail");
            }
        }
        return false;
    }

    /**
     * Gets all method calls in a Java statement.
     *
     * @param statement a Java statement
     * @return all method calls present in the statement
     */
    private static List<MethodCallExpr> getAllMethodCallsOfStatement(Statement statement) {
        List<MethodCallExpr> methodCalls = new ArrayList<>();
        statement.clone().walk(MethodCallExpr.class, methodCalls::add);
        return methodCalls;
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
        if (!isJUnitAssertion(jUnitAssertion)) {
            throw new IllegalArgumentException(jUnitAssertion + " is not a JUnit Assertion method call");
        }
        List<MethodCallExpr> nonJUnitMethods = getAllMethodCallsOfStatement(jUnitAssertion)
                .stream()
                .filter(methodCallExpr -> !allJUnitAssertionsMethods.contains(methodCallExpr.getNameAsString()))
                .toList();
        if (nonJUnitMethods.isEmpty()) {
            return null;
        } else {
            return nonJUnitMethods.get(0);
        }
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
        testFile.findAll(Statement.class).forEach(testStmt -> {
            if (isJUnitAssertion(testStmt)) {
                MethodCallExpr conditionMethodCall = getMethodCallOfJUnitAssertion(testStmt);
                if (conditionMethodCall != null) {
                    testStmt.replace(new ExpressionStmt(conditionMethodCall));
                } else {
                    testStmt.remove();
                }
            }
        });
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
        // remove all try/catch blocks
        testFile.findAll(TryStmt.class).forEach(tryStmt -> {
            BlockStmt testCase = (BlockStmt) tryStmt.getParentNode().orElseThrow();
            NodeList<Statement> prefix = tryStmt.getTryBlock().getStatements();
            int prefixLocation = testCase.getStatements().indexOf(tryStmt);
            testCase.getStatements().addAll(prefixLocation, prefix);
            tryStmt.remove();
        });
        // remove "fail()" statements
        testFile.findAll(Statement.class).forEach(stmt -> {
            if (isFail(stmt)) {
                stmt.remove();
            }
        });
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
        NodeList<Statement> originalBody = testCase.getBody().orElseThrow().getStatements();
        NodeList<Statement> newBody = new NodeList<>();
        int currentIdx = 0;
        for (Statement testStmt : originalBody) {
            if (isJUnitAssertion(testStmt) || testStmt.isTryStmt()) {
                if (currentIdx == oracleIdx) {
                    newBody.add(testStmt);
                    break;
                }
                currentIdx++;
            } else {
                newBody.add(testStmt);
            }
        }
        String simpleTestName = testCase.getNameAsString() + testID++;
        return testCase.clone()
                .setBody(new BlockStmt(newBody))
                .setName(simpleTestName);
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
        List<Statement> testStmts = testCase.getBody().orElseThrow().getStatements();
        int numOracles = 0;
        for (Statement testStmt : testStmts) {
            if (isJUnitAssertion(testStmt) || testStmt.isTryStmt()) {
                numOracles++;
            }
        }
        return numOracles;
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
        TypeDeclaration<?> testClass = testFile.getPrimaryType().orElseThrow();
        List<MethodDeclaration> testCases = testFile.findAll(MethodDeclaration.class);
        for (MethodDeclaration testCase : testCases) {
            int numOracles = getNumberOfOracles(testCase);
            for (int i = 0; i < numOracles; i++) {
                MethodDeclaration simpleTest = getSimpleTestCase(testCase, i);
                testClass.addMember(simpleTest);
            }
            testCase.remove();
        }
    }

    /**
     * Removes all import statements from the {@code org.evosuite} package.
     * This method does not override the original file.
     *
     * @param testFile a JavaParser representation of a test file
     */
    private static void removeEvosuiteImports(CompilationUnit testFile) {
        NodeList<ImportDeclaration> nonEvoImports = new NodeList<>();
        for (ImportDeclaration importDeclaration : testFile.getImports()) {
            if (!importDeclaration.getNameAsString().startsWith("org.evosuite")) {
                nonEvoImports.add(importDeclaration);
            }
        }
        testFile.setImports(nonEvoImports);
    }

    /**
     * Removes all import statements, annotations, and superclasses from an
     * EvoSuite test. This method does not override the original file.
     *
     * @param testFile a JavaParser representation of a test file
     */
    private static void removeEvosuiteDependency(CompilationUnit testFile) {
        removeEvosuiteImports(testFile);
        ClassOrInterfaceDeclaration testClass = testFile.getPrimaryType()
                .orElseThrow()
                .asClassOrInterfaceDeclaration();
        testClass.setAnnotations(new NodeList<>());
        testClass.setExtendedTypes(new NodeList<>());
    }

    /**
     * Removes all assertions from all EvoSuite tests generated for a given
     * class. The approach for removing oracles depends on whether an oracle
     * is exceptional or a normal assertion. Firstly, this method splits any
     * test case with multiple assertions into multiple simple tests, each
     * with a single JUnit assertion. The smaller subtests are saved in
     * "output/evosuite-tests-simple" and the test prefixes are saved in
     * "output/evosuite-prefix". This method does not override the original
     * test file.
     *
     * @param fullyQualifiedName the fully qualified name of the class under
     *                           test
     * @see OracleRemover#splitTests(CompilationUnit)
     * @see OracleRemover#removeExceptionalOracles(CompilationUnit)
     * @see OracleRemover#removeAssertionOracles(CompilationUnit)
     */
    public static void removeOracles(String fullyQualifiedName) {
        Path testPath = FileUtils.getFQNOutputPath("evosuite-tests", fullyQualifiedName);
        Path simplePath = FileUtils.getFQNOutputPath("evosuite-tests-simple", fullyQualifiedName);
        Path prefixPath = FileUtils.getFQNOutputPath("evosuite-prefix", fullyQualifiedName);
        CompilationUnit cu;
        try {
            cu = StaticJavaParser.parse(testPath);
        } catch (IOException e) {
            throw new Error("Unable to parse EvoSuite test " + testPath);
        }
        // split EvoSuite tests into simple tests
        splitTests(cu);
        FileUtils.writeString(simplePath, cu.toString());
        // remove EvoSuite dependencies
        removeEvosuiteDependency(cu);
        // remove oracles
        removeExceptionalOracles(cu);
        removeAssertionOracles(cu);
        FileUtils.writeString(prefixPath, cu.toString());
    }
}
