import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.CatchClause;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.TryStmt;
import com.github.javaparser.ast.type.Type;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
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
    /** A list of all supported axiomatic test oracle generators */
    private static final List<String> allAxiomaticTogs = List.of(
            "jdoctor",
            "tratto"
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
     * Removes all assertion oracles from a given test file, represented by a
     * JavaParser compilation unit. Removes JUnit Assertions methods (e.g.
     * assertEquals). This method does not modify the actual source file.
     *
     * @param testFile a JavaParser representation of a test file
     */
    private static void removeAssertionOracles(CompilationUnit testFile) {
        testFile.findAll(MethodDeclaration.class).forEach(testCase -> {
            NodeList<Statement> newBody = new NodeList<>();
            List<Statement> statements = testCase.getBody().orElseThrow().getStatements();
            for (Statement statement : statements) {
                if (isJUnitAssertion(statement)) {
                    MethodCallExpr conditionMethodCall = getMethodCallOfJUnitAssertion(statement);
                    if (conditionMethodCall != null) {
                        newBody.add(new ExpressionStmt(conditionMethodCall));
                    }
                } else {
                    newBody.add(statement);
                }
            }
            testCase.setBody(new BlockStmt(newBody));
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

    private static String getFullyQualifiedName(
            CompilationUnit cu,
            Type type
    ) {
        List<ImportDeclaration> importDeclarations = cu.getImports()
                .stream()
                .filter(id -> !id.isStatic())
                .toList();
        for (ImportDeclaration importDeclaration : importDeclarations) {
            String packageName = importDeclaration.getName().asString();
            if (packageName.endsWith(type.asString())) {
                return packageName;
            }
        }
        return "java.lang." + type.asString();
    }

    /**
     * Gets the type name of a variable represented by a {@link NameExpr}.
     *
     * @param nameExpr a variable name
     * @param testBody all statements in a method
     * @return the type name of the given variable
     */
    private static String getTypeOfName(
            CompilationUnit testFile,
            List<Statement> testBody,
            NameExpr nameExpr
    ) {
        String argumentName = nameExpr.getNameAsString();
        for (Statement statement : testBody) {
            if (!statement.isExpressionStmt()) {
                continue;
            }
            Expression expression = statement.asExpressionStmt().getExpression();
            if (!expression.isVariableDeclarationExpr()) {
                continue;
            }
            for (VariableDeclarator variableDeclarator : expression.asVariableDeclarationExpr().getVariables()) {
                if (argumentName.equals(variableDeclarator.getNameAsString())) {
                    Type variableType = variableDeclarator.getType();
                    if (variableType.isReferenceType()) {
                        return getFullyQualifiedName(testFile, variableDeclarator.getType());
                    } else {
                        return variableType.asString();
                    }
                }
            }
        }
        throw new IllegalArgumentException("Unable to find type of variable " + nameExpr.getNameAsString());
    }

    private static String getMethodSignature(
            CompilationUnit testFile,
            List<Statement> testBody,
            MethodCallExpr methodCallExpr
    ) {
        StringBuilder sb = new StringBuilder();
        List<Expression> arguments = methodCallExpr.getArguments();
        sb.append(methodCallExpr.getName()).append("(");
        for (int i = 0; i < arguments.size(); i++) {
            NameExpr argumentName = arguments.get(i).asNameExpr();
            sb.append(getTypeOfName(testFile, testBody, argumentName));
            if (i < arguments.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append(")");
        return sb.toString();
    }

    /**
     * Gets all oracles applicable to a Java statement.
     *
     * @param statement a Java statement
     * @param testBody all statements in a method
     * @param allOracles all possible oracle records
     * @return all oracles involving method calls in the given statement
     */
    private static List<OracleOutput> getRelatedOracles(
            CompilationUnit testFile,
            List<Statement> testBody,
            Statement statement,
            List<OracleOutput> allOracles
    ) {
        List<MethodCallExpr> methodCalls = getAllMethodCallsOfStatement(statement);
        Set<OracleOutput> relatedOracles = new HashSet<>();
        for (MethodCallExpr methodCallExpr : methodCalls) {
            String methodSignature = getMethodSignature(
                    testFile,
                    testBody,
                    methodCallExpr
            );
            relatedOracles.addAll(allOracles
                    .stream()
                    .filter(o -> o.methodSignature().equals(methodSignature))
                    .toList()
            );
        }
        return relatedOracles.stream().toList();
    }

    private static Class<?> getPrimitiveClass(String primitiveName) {
        switch (primitiveName) {
            case "boolean" -> {
                return boolean.class;
            }
            case "byte" -> {
                return byte.class;
            }
            case "char" -> {
                return char.class;
            }
            case "short" -> {
                return short.class;
            }
            case "int" -> {
                return int.class;
            }
            case "long" -> {
                return long.class;
            }
            case "float" -> {
                return float.class;
            }
            case "double" -> {
                return double.class;
            }
            default -> throw new IllegalArgumentException("Unrecognized primitive type " + primitiveName);
        }
    }

    private static Class<?> getClassOfName(String className) {
        List<String> allPrimitiveTypes = List.of("boolean", "byte", "char", "short", "int", "long", "float", "double");
        if (allPrimitiveTypes.contains(className)) {
            return getPrimitiveClass(className);
        } else {
            try {
                return Class.forName(className);
            } catch (ClassNotFoundException e) {
                throw new Error("Unable to find class " + className);
            }
        }
    }

    private static List<Class<?>> getParameterTypes(String methodSignature) {
        String parameters = methodSignature.substring(methodSignature.indexOf('(') + 1, methodSignature.indexOf(')'));
        if (parameters.length() == 0) {
            return new ArrayList<>();
        }
        List<Class<?>> parameterTypes = new ArrayList<>();
        for (String parameterName : parameters.split(",")) {
            parameterName = parameterName.trim();
            parameterTypes.add(getClassOfName(parameterName));
        }
        return parameterTypes;
    }

    private static Type getReturnType(
            String className,
            String methodSignature
    ) {
        String methodName = methodSignature.substring(0, methodSignature.indexOf('('));
        List<Class<?>> parameterTypes = getParameterTypes(methodSignature);
        Class<?> receiverObjectID = getClassOfName(className);
        try {
            Method method = receiverObjectID.getMethod(methodName, parameterTypes.toArray(Class[]::new));
            Class<?> returnType = method.getReturnType();
            return StaticJavaParser.parseType(returnType.getName());
        } catch (NoSuchMethodException e) {
            throw new Error("");
        }
    }

    private static Statement addInitialization(
            Statement statement
    ) {
        return new ExpressionStmt();
    }

    private static NodeList<Statement> addPreConditions(
            List<OracleOutput> oracles
    ) {
        NodeList<Statement> preConditions = new NodeList<>();
        for (OracleOutput oracle : oracles) {
            preConditions.add(StaticJavaParser.parseStatement(oracle.oracle()));
        }
        return preConditions;
    }

    private static IfStmt addThrowsConditions(
            List<OracleOutput> oracles
    ) {
        return new IfStmt();
    }

    private static NodeList<Statement> addPostConditions(
            List<OracleOutput> oracles
    ) {
        return new NodeList<>();
    }

    private static NodeList<Statement> buildConditionStatements(
            List<Statement> testBody,
            Statement statement,
            List<OracleOutput> oracles
    ) {
        NodeList<Statement> conditionStatements = new NodeList<>();
        conditionStatements.addAll(addPreConditions(
                oracles.stream().filter(o -> o.oracleType().equals(OracleType.PRE)).toList()
        ));
        conditionStatements.add(addThrowsConditions(
                oracles.stream().filter(o -> o.oracleType().equals(OracleType.EXCEPT_POST)).toList()
        ));
        conditionStatements.addAll(addPostConditions(
                oracles.stream().filter(o -> o.oracleType().equals(OracleType.NORMAL_POST)).toList()
        ));
        for (Statement line : conditionStatements) {
            System.out.println(line);
        }
        return conditionStatements;
    }

    private static void insertAxiomaticOracles(CompilationUnit testFile, List<OracleOutput> oracles) {
        testFile.findAll(MethodDeclaration.class).forEach(testCase -> {
            List<Statement> statements = testCase.getBody().orElseThrow().getStatements();
            for (int i = 0; i < statements.size(); i++) {
                List<OracleOutput> relatedOracles = getRelatedOracles(
                        testFile,
                        statements,
                        statements.get(i),
                        oracles
                );
                if (relatedOracles.size() != 0) {
                    NodeList<Statement> conditionStatement = buildConditionStatements(
                            statements,
                            statements.get(i),
                            relatedOracles
                    );
                }
            }
        });
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
        try (Stream<Path> walk = Files.walk(dir)) {
            walk
                    .filter(FileUtils::isJavaFile)
                    .forEach(testFile -> {
                        try {
                            CompilationUnit cu = StaticJavaParser.parse(testFile);
                            insertAxiomaticOracles(cu, oracles);
//                            FileUtils.writeString(testFile, cu.toString());
                        } catch (IOException e) {
                            throw new Error("Unable to parse test file " + testFile.getFileName().toString());
                        }
                    });
        } catch (IOException e) {
            throw new Error("Error when parsing files in directory " + dir, e);
        }
    }

    /**
     * Adds an assertion to a given test case.
     *
     * @param testCase a method representation of a test case
     * @param assertion the string representation of the assertion to add
     */
    private static void insertNonAxiomaticAssertion(MethodDeclaration testCase, String assertion) {
        ExpressionStmt statement = StaticJavaParser.parseStatement(assertion).asExpressionStmt();
        testCase
                .getBody().orElseThrow()
                .getStatements().add(statement);
    }

    /**
     * Wraps a given test case in a try/catch block where the catch block
     * expects a given exception class.
     *
     * @param testCase a test case
     * @param exception the exception to catch
     */
    private static void insertNonAxiomaticException(MethodDeclaration testCase, String exception) {
        // create catch block
        Parameter exceptionType = new Parameter()
                .setName("e")
                .setType(StaticJavaParser.parseType(exception));
        CatchClause catchClause = new CatchClause()
                .setParameter(exceptionType);
        // create try block
        BlockStmt testPrefix = testCase
                .getBody().orElseThrow()
                .clone();
        ExpressionStmt failStatement = StaticJavaParser.parseStatement("fail();").asExpressionStmt();
        TryStmt tryStatement = new TryStmt()
                .setTryBlock(testPrefix.addStatement(failStatement));
        tryStatement.getCatchClauses().add(catchClause);
        // set new test body to wrapped try/catch block
        testCase.setBody(new BlockStmt(NodeList.nodeList(tryStatement)));
    }

    /**
     * Checks if an oracle record represents an exceptional oracle.
     *
     * @param oracle an oracle record
     * @return true iff the oracle represents an exceptional oracle. This
     * corresponds to an empty string in the {@link OracleOutput#exception()}
     * value.
     */
    private static boolean isExceptional(OracleOutput oracle) {
        return !oracle.exception().equals("");
    }

    /**
     * Gets the oracle corresponding to a given prefix from a list of oracles.
     *
     * @param prefix a test prefix
     * @param oracles a list of oracle records
     * @return the oracle record with the given prefix
     * @throws NoSuchElementException if no such oracle exists in the list
     */
    private static OracleOutput getOracleWithPrefix(String prefix, List<OracleOutput> oracles) {
        List<String> allPrefix = oracles
                .stream()
                .map(OracleOutput::prefix)
                .map(String::trim)
                .toList();
        int indexOfOracle = allPrefix.indexOf(prefix.trim());
        if (indexOfOracle == -1) {
            throw new NoSuchElementException("Unable to find an oracle with the prefix " + prefix);
        }
        return oracles.get(indexOfOracle);
    }

    /**
     * Adds non-axiomatic oracles to test prefixes in a given test file.
     * Non-axiomatic oracles are specific to a given test prefix. Each oracle
     * is matched to its corresponding prefix using the
     * {@link OracleOutput#prefix()} value.
     *
     * @param testFile a Java file of test prefixes
     * @param oracles a list of test oracles made by a non-axiomatic tog
     */
    private static void insertNonAxiomaticOracles(CompilationUnit testFile, List<OracleOutput> oracles) {
        testFile.findAll(MethodDeclaration.class)
                .forEach(testCase -> {
                    String prefix = testCase.toString();
                    OracleOutput oracle = getOracleWithPrefix(prefix, oracles);
                    if (isExceptional(oracle)) {
                        insertNonAxiomaticException(testCase, oracle.exception());
                    } else {
                        insertNonAxiomaticAssertion(testCase, oracle.oracle());
                    }
                });
    }

    /**
     * Adds non-axiomatic oracles to test prefixes in a given directory.
     * Non-axiomatic oracles are specific to a given test prefix. Each oracle
     * is matched to its corresponding prefix using the
     * {@link OracleOutput#prefix()} value.
     *
     * @param dir a directory with Java test prefixes
     * @param oracles a list of test oracles made by a non-axiomatic tog
     */
    private static void insertNonAxiomaticOracles(Path dir, List<OracleOutput> oracles) {
        try (Stream<Path> walk = Files.walk(dir)) {
            walk
                    .filter(FileUtils::isJavaFile)
                    .forEach(testFile -> {
                        try {
                            CompilationUnit cu = StaticJavaParser.parse(testFile);
                            insertNonAxiomaticOracles(cu, oracles);
                            FileUtils.writeString(testFile, cu.toString());
                        } catch (IOException e) {
                            throw new Error("Unable to parse test file " + testFile.getFileName().toString());
                        }
                    });
        } catch (IOException e) {
            throw new Error("Error when parsing files in directory " + dir, e);
        }
    }

    /**
     * @param tog a test oracle generator
     * @return true iff the given tog generates axiomatic test oracles (known
     * a priori)
     */
    private static boolean isAxiomatic(String tog) {
        return allAxiomaticTogs.contains(tog);
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
        Path testPath = output.resolve("tog-tests/" + tog);
        FileUtils.copy(dir, testPath);
        if (isAxiomatic(tog)) {
            insertAxiomaticOracles(testPath, oracles);
        } else {
            insertNonAxiomaticOracles(testPath, oracles);
        }
    }
}
