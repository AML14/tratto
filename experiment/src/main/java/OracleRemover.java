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
     * This method does not modify the actual source file.
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
        String newName = original.getNameAsString() + testID;
        testID++;
        return original.clone()
                .setBody(new BlockStmt(newBody))
                .setName(newName);
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
        NodeList<Statement> originalBody = testCase.getBody().orElseThrow().getStatements();
        NodeList<Statement> newBody = new NodeList<>();
        int currentIdx = 0;
        for (Statement testStmt : originalBody) {
            if (isJUnitAssertion(testStmt)) {
                if (currentIdx == assertionIdx) {
                    newBody.add(testStmt);
                    break;
                }
                currentIdx++;
            } else {
                newBody.add(testStmt);
            }
        }
        return createRelatedMethod(testCase, newBody);
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
        NodeList<Statement> testBody = testCase.getBody().orElseThrow().getStatements();
        int numAssertions = 0;
        for (Statement testStmt : testBody) {
            if (isJUnitAssertion(testStmt)) {
                numAssertions++;
            }
        }
        return numAssertions;
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
        TypeDeclaration<?> testClass = testFile.getType(0);
        List<MethodDeclaration> testCases = testFile.findAll(MethodDeclaration.class);
        for (MethodDeclaration testCase : testCases) {
            int numAssertions = getNumberOfAssertions(testCase);
            for (int i = 0; i < numAssertions; i++) {
                MethodDeclaration simpleTest = getSimpleTestCase(testCase, i);
                testClass.addMember(simpleTest);
            }
            // keep exceptional oracles
            if (numAssertions == 0) {
                NodeList<Statement> originalBody = testCase.getBody().orElseThrow()
                        .getStatements();
                MethodDeclaration exceptionalTest = createRelatedMethod(testCase, originalBody);
                testClass.addMember(exceptionalTest);
            }
            testCase.remove();
        }
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
        Path fqnPath = FileUtils.getRelativePathFromFullyQualifiedClassName(baseDir + "." + fullyQualifiedName);
        int classNameIdx = fqnPath.getNameCount() - 1;
        return output.resolve(fqnPath.subpath(0, classNameIdx));
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
        Path simplePath = getFQNOutputPath("evosuite-tests-simple", fullyQualifiedName);
        Path prefixPath = getFQNOutputPath("evosuite-prefix", fullyQualifiedName);
        FileUtils.copy(dir, simplePath);
        FileUtils.copy(dir, prefixPath);
        try (Stream<Path> walk = Files.walk(prefixPath)) {
            walk
                    .filter(FileUtils::isJavaFile)
                    .filter(p -> !FileUtils.isScaffolding(p))
                    .forEach(testFile -> {
                        try {
                            CompilationUnit cu = StaticJavaParser.parse(testFile);
                            // save simple tests to separate output for later analysis
                            splitTests(cu);
                            Path simpleTestPath = FileUtils.getRelativePath(prefixPath, simplePath, testFile);
                            FileUtils.writeString(simpleTestPath, cu.toString());
                            // then, remove oracles for future insertion
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
}
