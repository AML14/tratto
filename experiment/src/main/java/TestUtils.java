import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.CatchClause;
import com.github.javaparser.ast.stmt.ExpressionStmt;
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
            // do not remove exceptional oracles.
            if (numAssertions != 0) {
                testCase.remove();
            }
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
     * Gets the name of a method from the method signature.
     *
     * @param methodSignature a method signature
     * @return the method name
     */
    private static String getMethodName(String methodSignature) {
        return methodSignature.substring(0, methodSignature.indexOf('('));
    }

    /**
     * Gets the name of all parameter types from the method signature.
     *
     * @param methodSignature a method signature
     * @return the name of all types in the method parameters
     */
    private static List<String> getParameterTypeNames(String methodSignature) {
        String parameters = methodSignature.substring(methodSignature.indexOf('(') + 1, methodSignature.indexOf(')'));
        if (parameters.length() == 0) {
            return new ArrayList<>();
        }
        return Stream.of(parameters.split(","))
                .map(p -> p.split(" ")[0].trim())
                .toList();
    }

    /**
     * Gets the fully qualified name of a given simple type name.
     *
     * @param cu the test file using type
     * @param type a simple type name
     * @return the fully qualified name of the type name
     */
    private static String getFullyQualifiedName(
            CompilationUnit cu,
            Type type
    ) {
        if (type.isReferenceType()) {
            List<ImportDeclaration> importDeclarations = cu.getImports()
                    .stream()
                    .filter(id -> !id.isStatic())
                    .toList();
            for (ImportDeclaration importDeclaration : importDeclarations) {
                String packageName = importDeclaration.getName().asString();
                if (packageName.endsWith(type.asString())) {
                    return packageName + "." + type.asString();
                }
            }
            return "java.lang." + type.asString();
        } else {
            return type.asString();
        }
    }

    /**
     * Gets the type of a given variable name.
     *
     * @param variableName a variable name
     * @param testBody all statements in a method
     * @return the type of a given variable name
     */
    private static Type getTypeOfName(
            List<Statement> testBody,
            String variableName
    ) {
        for (Statement statement : testBody) {
            if (!statement.isExpressionStmt()) {
                continue;
            }
            Expression expression = statement.asExpressionStmt().getExpression();
            if (!expression.isVariableDeclarationExpr()) {
                continue;
            }
            for (VariableDeclarator variableDeclarator : expression.asVariableDeclarationExpr().getVariables()) {
                if (variableName.equals(variableDeclarator.getNameAsString())) {
                    return variableDeclarator.getType();
                }
            }
        }
        throw new IllegalArgumentException("Unable to find type of variable " + variableName);
    }

    /**
     * Checks if a given method call in a test file matches an expected method
     * signature.
     *
     * @param testFile a Java test file
     * @param testBody a test case in the test file
     * @param methodCall a method call in the test case
     * @param expectedSignature an expected method signature
     * @return true iff the given method call matches the method signature
     */
    private static boolean isMatchingMethodCall(
            CompilationUnit testFile,
            List<Statement> testBody,
            MethodCallExpr methodCall,
            String expectedSignature
    ) {
        String methodName = getMethodName(expectedSignature);
        if (!methodName.equals(methodCall.getName().asString())) {
            return false;
        }
        List<String> methodArgTypes = methodCall.getArguments()
                .stream()
                .map(arg -> {
                    Type argType = getTypeOfName(testBody, arg.asNameExpr().getNameAsString());
                    return getFullyQualifiedName(testFile, argType);
                })
                .toList();
        List<String> expectedTypes = getParameterTypeNames(expectedSignature);
        return methodArgTypes.equals(expectedTypes);
    }

    /**
     * Gets all oracles applicable to a Java statement.
     *
     * @param testStatement a Java statement
     * @param testBody all statements in a method
     * @param allOracles all possible oracle records
     * @return all oracles involving method calls in the given statement
     */
    private static List<OracleOutput> getRelatedOracles(
            CompilationUnit testFile,
            List<Statement> testBody,
            Statement testStatement,
            List<OracleOutput> allOracles
    ) {
        List<MethodCallExpr> methodCalls = getAllMethodCallsOfStatement(testStatement);
        Set<OracleOutput> relatedOracles = new HashSet<>();
        for (MethodCallExpr methodCall : methodCalls) {
            relatedOracles.addAll(allOracles
                    .stream()
                    .filter(o -> isMatchingMethodCall(testFile, testBody, methodCall, o.methodSignature()))
                    .toList()
            );
        }
        return relatedOracles.stream().toList();
    }

    /**
     * Gets the Class of a given primitive type name.
     *
     * @param primitiveName a primitive type name
     * @return the Class corresponding to the primitive type
     * @throws IllegalArgumentException if the given type name does not
     * correspond to a known primitive type
     */
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

    /**
     * Gets the Class of a given type name.
     *
     * @param className a fully qualified type name
     * @return the Class corresponding to a type name
     */
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

    /**
     * Gets all Class objects of a given list of types.
     *
     * @param classNames a list of fully qualified type names
     * @return the Classes corresponding to the type names
     */
    private static List<Class<?>> getClassOfName(List<String> classNames) {
        List<Class<?>> classes = new ArrayList<>();
        for (String className : classNames) {
            classes.add(getClassOfName(className));
        }
        return classes;
    }

    /**
     * Gets the return type of a method.
     *
     * @param className the fully qualified name of the declaring class
     * @param methodSignature the method signature
     * @return the return type of the given method
     */
    private static Type getReturnType(
            String className,
            String methodSignature
    ) {
        String methodName = getMethodName(methodSignature);
        List<Class<?>> parameterTypes = getClassOfName(getParameterTypeNames(methodSignature));
        Class<?> receiverObjectID = getClassOfName(className);
        try {
            Method method = receiverObjectID.getMethod(methodName, parameterTypes.toArray(Class[]::new));
            Class<?> returnType = method.getReturnType();
            return StaticJavaParser.parseType(returnType.getName());
        } catch (NoSuchMethodException e) {
            throw new Error("Unable to parse return type of " + methodSignature + " in " + className);
        }
    }

    private static String getDeclaringClass(
            CompilationUnit testFile,
            MethodCallExpr methodCall
    ) {
        String methodName = getMethodName(methodCall.toString());
        if (methodName.contains(".")) {
            String declaringClass = methodName.substring(0, methodName.lastIndexOf('.'));
            Type type = StaticJavaParser.parseType(declaringClass);
            return getFullyQualifiedName(testFile, type);
        }
        List<ImportDeclaration> importDeclarations = testFile.getImports();
        for (ImportDeclaration importDeclaration : importDeclarations) {
            String packageName = importDeclaration.getNameAsString();
            System.out.println(packageName);
        }
        throw new Error("");
    }

    private static Statement getInitialization(
            Statement statement,
            List<OracleOutput> oracles
    ) {
        String className = oracles.get(0).className();
        String methodSignature = oracles.get(0).methodSignature();
        Type returnType = getReturnType(className, methodSignature);
        Expression expression = statement.asExpressionStmt().getExpression();
        return new ExpressionStmt();
    }

    private static NodeList<Statement> getOracleStatements(
            CompilationUnit testFile,
            List<Statement> testBody,
            Statement statement,
            List<OracleOutput> oracles
    ) {
        NodeList<Statement> oracleStatements = new NodeList<>();

        List<MethodCallExpr> methodCalls = getAllMethodCallsOfStatement(statement);
        oracleStatements.add(getInitialization(statement, oracles));
        return oracleStatements;
    }

    /**
     * Adds axiomatic oracles to test prefixes in a given Java file. Axiomatic
     * oracles are not specific to a given test prefix. This method iterates
     * through each line in each test case, and adds all applicable oracles.
     *
     * @param testFile a Java test file
     * @param oracles a list of test oracles made by an axiomatic tog
     */
    private static void insertAxiomaticOracles(CompilationUnit testFile, List<OracleOutput> oracles) {
        testFile.findAll(MethodDeclaration.class).forEach(testCase -> {
            NodeList<Statement> newBody = new NodeList<>();
            NodeList<Statement> originalBody = testCase.getBody().orElseThrow().getStatements();
            for (Statement testStatement : originalBody) {
                List<OracleOutput> relatedOracles = getRelatedOracles(testFile, originalBody, testStatement, oracles);
                if (relatedOracles.size() != 0) {
                    newBody.addAll(getOracleStatements(testFile, originalBody, testStatement, relatedOracles));
                } else {
                    newBody.add(testStatement);
                }
            }
            testCase.setBody(new BlockStmt(newBody));
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
