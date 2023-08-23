import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.CatchClause;
import com.github.javaparser.ast.stmt.EmptyStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.TryStmt;
import com.github.javaparser.ast.type.Type;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
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
    /** A unique id for placeholder variable names */
    private static int variableID = 0;
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
     * Gets the type names of all parameters from the method signature.
     *
     * @param methodSignature a method signature
     * @return a list of all parameter type names in the method signature
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
     * Gets the variable names of all parameters from the method signature.
     *
     * @param methodSignature a method signature
     * @return a list of all variable parameter names in the method signature
     */
    private static List<String> getParameterNames(String methodSignature) {
        String parameters = methodSignature.substring(methodSignature.indexOf('(') + 1, methodSignature.indexOf(')'));
        if (parameters.length() == 0) {
            return new ArrayList<>();
        }
        return Stream.of(parameters.split(","))
                .map(p -> p.split(" ")[1].trim())
                .toList();
    }

    /**
     * Gets the package name of a given JavaParser type. This method assumes
     * that all types (including types from the same package) have
     * corresponding import statements in the compilation unit. This is TRUE
     * for all compilation units generated by EvoSuite. If no matching import
     * statement is found, then the type is assumed to be in the "java.lang"
     * package.
     *
     * @param cu the Java file importing the given type
     * @param type a type
     * @return the package of the given type
     */
    private static String getPackageName(CompilationUnit cu, Type type) {
        if (type.isReferenceType()) {
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
            return "java.lang";
        } else {
            return "";
        }
    }

    /**
     * Gets the fully qualified name of a given type.
     *
     * @param cu the Java file importing the given type
     * @param type a type
     * @return the fully qualified name of the given type
     */
    private static String getFullyQualifiedName(CompilationUnit cu, Type type) {
        if (type.isReferenceType()) {
            return getPackageName(cu, type) + "." + type.asString();
        } else {
            return type.asString();
        }
    }

    /**
     * Gets the type of a given variable.
     *
     * @param varName a variable name
     * @param body all statements in a method body
     * @return the type of a given variable name
     */
    private static Type getTypeOfName(List<Statement> body, String varName) {
        for (Statement statement : body) {
            if (!statement.isExpressionStmt()) {
                continue;
            }
            Expression expression = statement.asExpressionStmt().getExpression();
            if (!expression.isVariableDeclarationExpr()) {
                continue;
            }
            for (VariableDeclarator variableDeclarator : expression.asVariableDeclarationExpr().getVariables()) {
                if (varName.equals(variableDeclarator.getNameAsString())) {
                    return variableDeclarator.getType();
                }
            }
        }
        throw new IllegalArgumentException("Unable to find the type of the variable " + varName);
    }

    /**
     * Gets the type names of all parameters in a JavaParser method call.
     *
     * @param cu a Java file declaring the method corresponding to
     *           {@code body}
     * @param body a list of statements in a method body
     * @param methodCallExpr a method call in the given method
     * @return a list of all parameter type names in the given method
     */
    private static List<String> getParameterTypeNames(
            CompilationUnit cu,
            List<Statement> body,
            MethodCallExpr methodCallExpr
    ) {
        return methodCallExpr.getArguments()
                .stream()
                .map(arg -> {
                    Type type = getTypeOfName(body, arg.asNameExpr().getNameAsString());
                    return getFullyQualifiedName(cu, type);
                })
                .toList();
    }

    /**
     * Checks if a given JavaParser method call corresponds to the same method
     * as a given signature.
     *
     * @param jpFile the Java file declaring
     * @param jpBody a test case in the test file
     * @param jpMethodCall a method call in the test case
     * @param expectedSignature an expected method signature
     * @return true iff the given method call matches the method signature
     */
    private static boolean isMatchingMethod(
            CompilationUnit jpFile,
            List<Statement> jpBody,
            MethodCallExpr jpMethodCall,
            String expectedSignature
    ) {
        String expectedName = getMethodName(expectedSignature);
        List<String> expectedTypes = getParameterTypeNames(expectedSignature);
        String jpName = jpMethodCall.getNameAsString();
        List<String> jpTypes = getParameterTypeNames(jpFile, jpBody, jpMethodCall);
        return expectedName.equals(jpName) && expectedTypes.equals(jpTypes);
    }

    /**
     * Gets all oracles applicable to a Java statement. An oracle is
     * applicable to a statement if it corresponds to a method call in the
     * given statement.
     *
     * @param stmt a Java statement
     * @param body all statements in the parent method
     * @param allOracles all possible oracles
     * @return all oracles corresponding to methods calls in the given
     * statement
     */
    private static List<OracleOutput> getRelatedOracles(
            CompilationUnit cu,
            List<Statement> body,
            Statement stmt,
            List<OracleOutput> allOracles
    ) {
        List<MethodCallExpr> methodCalls = getAllMethodCallsOfStatement(stmt);
        Set<OracleOutput> relatedOracles = new HashSet<>();
        for (MethodCallExpr methodCall : methodCalls) {
            relatedOracles.addAll(allOracles
                    .stream()
                    .filter(o -> isMatchingMethod(cu, body, methodCall, o.methodSignature()))
                    .toList()
            );
        }
        return relatedOracles
                .stream()
                .toList();
    }

    /**
     * Gets the Class of a given primitive type name.
     *
     * @param primitiveName a primitive type name
     * @return the Class corresponding to the primitive type
     * @throws IllegalArgumentException if the given type name is not a
     * primitive type
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
     * @return the Class corresponding to the type name
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
     * @see TestUtils#getClassOfName(String)
     */
    private static List<Class<?>> getClassesOfNames(List<String> classNames) {
        List<Class<?>> classes = new ArrayList<>();
        for (String className : classNames) {
            classes.add(getClassOfName(className));
        }
        return classes;
    }

    /**
     * Gets the reflection Method representation of a method.
     *
     * @param className the fully qualified name of the declaring class
     * @param methodSignature the method signature
     * @return the reflection representation of the method
     */
    private static Method getReflectionMethod(String className, String methodSignature) {
        String methodName = getMethodName(methodSignature);
        List<Class<?>> parameterTypes = getClassesOfNames(getParameterTypeNames(methodSignature));
        Class<?> receiverObjectID = getClassOfName(className);
        try {
            return receiverObjectID.getMethod(methodName, parameterTypes.toArray(Class[]::new));
        } catch (NoSuchMethodException e) {
            throw new Error("Unable to find method " + methodSignature + " in " + className);
        }
    }

    /**
     * Gets the return type of a method.
     *
     * @param className the fully qualified name of the declaring class
     * @param methodSignature the method signature
     * @return the return type of the given method
     */
    private static Type getReturnType(String className, String methodSignature) {
        Method method = getReflectionMethod(className, methodSignature);
        Class<?> returnType = method.getReturnType();
        return StaticJavaParser.parseType(returnType.getName());
    }

    /**
     * Checks if a given method is static.
     *
     * @param className the fully qualified name of the declaring class
     * @param methodSignature the method signature
     * @return true iff the given method is static
     */
    private static boolean isStatic(String className, String methodSignature) {
        Method method = getReflectionMethod(className, methodSignature);
        return Modifier.isStatic(method.getModifiers());
    }

    /**
     * Replaces all instances of an original name in a given oracle with their
     * corresponding context names. The original names
     *
     * @param originalNames the original axiomatic names
     * @param contextNames the contextual names
     * @param oracle the original axiomatic oracle
     * @return the contextualized oracle
     */
    private static String replaceName(
            List<String> originalNames,
            List<String> contextNames,
            String oracle
    ) {
        Expression oracleExpression = StaticJavaParser.parseExpression(oracle);
        oracleExpression.walk(NameExpr.class, name -> {
            int originalIdx = originalNames.indexOf(name.getNameAsString());
            if (originalIdx != -1) {
                name.replace(new NameExpr(contextNames.get(originalIdx)));
            }
        });
        return oracleExpression.toString();
    }

    /**
     * Replaces all instances of "receiverObjectID" in a given oracle, with
     * the corresponding object name in source code.
     *
     * @param testStmt the contextual test statement
     * @param oracleOutput the original oracle
     * @return the axiomatic oracle with names replaced
     */
    private static OracleOutput getReceiverObjectID(
            Statement testStmt,
            OracleOutput oracleOutput
    ) {
        String className = oracleOutput.className();
        String methodSignature = oracleOutput.methodSignature();
        if (isStatic(className, methodSignature)) {
            // if the method is static, then the receiverObjectID is not necessary.
            return oracleOutput;
        }
        String originalName = "receiverObjectID";
        String contextName = getAllMethodCallsOfStatement(testStmt).get(0)
                .getScope().orElseThrow().toString();
        String contextOracle = replaceName(List.of(originalName), List.of(contextName), oracleOutput.oracle());
        return new OracleOutput(
                oracleOutput.className(),
                oracleOutput.methodSignature(),
                oracleOutput.oracleType(),
                oracleOutput.prefix(),
                contextOracle,
                oracleOutput.exception()
        );
    }

    /**
     * Replaces all instances of "methodResultID" in a given oracle with
     * the corresponding object name in source code.
     *
     * @param initStmt the initialization of the method result
     * @param oracleOutput the original oracle
     * @return the axiomatic oracle with names replaced
     */
    private static OracleOutput getMethodResultID(
            Statement initStmt,
            OracleOutput oracleOutput
    ) {
        String className = oracleOutput.className();
        String methodSignature = oracleOutput.methodSignature();
        Type returnType = getReturnType(className, methodSignature);
        if (returnType.isVoidType()) {
            // if the method has a void type, then the methodResultID is not necessary.
            return oracleOutput;
        }
        String originalName = "methodResultID";
        String contextName = initStmt
                .asExpressionStmt().getExpression()
                .asVariableDeclarationExpr().getVariables().get(0)
                .getNameAsString();
        String contextOracle = replaceName(List.of(originalName), List.of(contextName), oracleOutput.oracle());
        return new OracleOutput(
                oracleOutput.className(),
                oracleOutput.methodSignature(),
                oracleOutput.oracleType(),
                oracleOutput.prefix(),
                contextOracle,
                oracleOutput.exception()
        );
    }

    /**
     * Replaces all variable names in the original oracle with the names from
     * the test case.
     *
     * @param testStmt a Java statement
     * @param oracleOutput an oracle record
     * @return an oracle record with contextual parameter names
     */
    private static OracleOutput getParameterID(
            Statement testStmt,
            OracleOutput oracleOutput
    ) {
        List<String> originalNames = getParameterNames(oracleOutput.methodSignature());
        List<String> contextNames = getAllMethodCallsOfStatement(testStmt).get(0).getArguments()
                .stream()
                .map(expression -> expression.asNameExpr().getNameAsString())
                .toList();
        String contextOracle = replaceName(originalNames, contextNames, oracleOutput.oracle());
        return new OracleOutput(
                oracleOutput.className(),
                oracleOutput.methodSignature(),
                oracleOutput.oracleType(),
                oracleOutput.prefix(),
                contextOracle,
                oracleOutput.exception()
        );
    }

    /**
     * Substitutes contextual variable names into an oracle.
     *
     * @param initStmt a list of statements in a test case
     * @param oracleOutput an oracle
     * @return the same oracle with names from the test body
     */
    private static OracleOutput contextualizeOracle(
            Statement initStmt,
            Statement testStmt,
            OracleOutput oracleOutput
    )  {
        oracleOutput = getParameterID(testStmt, oracleOutput);
        oracleOutput = getReceiverObjectID(testStmt, oracleOutput);
        oracleOutput = getMethodResultID(initStmt, oracleOutput);
        return oracleOutput;
    }

    private static Statement getInitialization(
            Expression expression,
            Type returnType
    ) {
        if (expression.isVariableDeclarationExpr()) {
            VariableDeclarator variableDeclarator = expression.asVariableDeclarationExpr().getVariable(0);
            VariableDeclarationExpr variableDeclarationExpr = new VariableDeclarationExpr(
                    returnType,
                    variableDeclarator.getNameAsString()
            );
            return new ExpressionStmt(variableDeclarationExpr);
        } else if (expression.isMethodCallExpr()) {
            String placeholderName = "default" + variableID;
            VariableDeclarationExpr variableDeclarationExpr = new VariableDeclarationExpr(
                    returnType,
                    placeholderName
            );
            variableID++;
            return new ExpressionStmt(variableDeclarationExpr);
        } else {
            return new EmptyStmt();
        }
    }

    private static Statement getInitialization(
            Statement statement,
            List<OracleOutput> oracles
    ) {
        String className = oracles.get(0).className();
        String methodSignature = oracles.get(0).methodSignature();
        Type returnType = getReturnType(className, methodSignature);
        if (returnType.isVoidType() || !statement.isExpressionStmt()) {
            return new EmptyStmt();
        }
        Expression expression = statement.asExpressionStmt().getExpression();
        return getInitialization(expression, returnType);
    }

    private static Statement getPostStatement(
            Statement initStmt,
            Statement testStmt
    ) {
        if (initStmt.isEmptyStmt() || !testStmt.isExpressionStmt()) {
            return testStmt;
        }
        Expression testExpression = testStmt
                .asExpressionStmt()
                .getExpression();

        if (!testExpression.isVariableDeclarationExpr() && !testExpression.isMethodCallExpr()) {
            return testStmt;
        }
        Expression initExpression = initStmt
                .asExpressionStmt()
                .getExpression();
        String varName = initExpression.asVariableDeclarationExpr().getVariable(0).getNameAsString();
        Expression methodCall = getAllMethodCallsOfStatement(testStmt).get(0);
        AssignExpr assignExpr = new AssignExpr(
                new NameExpr(varName),
                methodCall,
                AssignExpr.Operator.ASSIGN
        );
        return new ExpressionStmt(assignExpr);
    }

    private static NodeList<Statement> getPreConditions(
            List<OracleOutput> oracles
    ) {
        return new NodeList<>(oracles
                .stream()
                .map(o -> StaticJavaParser.parseStatement("assertTrue(" + o.oracle() + ");"))
                .toList());
    }

    private static TryStmt getTryCatchBlock(
            Statement statement,
            OracleOutput oracleOutput
    ) {
        Type exceptionType = StaticJavaParser.parseType(oracleOutput.exception());
        Parameter exceptionParameter = new Parameter(exceptionType, "e");
        CatchClause catchClause = new CatchClause(exceptionParameter, new BlockStmt());
        return new TryStmt();
    }

    private static IfStmt getThrowsConditions(
            Statement postStatement,
            List<OracleOutput> oracles
    ) {
        if (oracles.size() == 0) {
            return null;
        }
        List<Expression> conditions = oracles
                .stream()
                .map(o -> (Expression) StaticJavaParser.parseExpression(o.oracle()))
                .toList();
        List<TryStmt> tryStmts = oracles
                .stream()
                .map(o -> getTryCatchBlock(postStatement, o))
                .toList();
        return new IfStmt();
    }

    private static Statement getPostConditions(
            IfStmt base,
            boolean condition
    ) {
        return base;
    }

    private static NodeList<Statement> getOracleStatements(
            CompilationUnit testFile,
            List<Statement> testBody,
            Statement testStmt,
            List<OracleOutput> oracles
    ) {
        NodeList<Statement> oracleStatements = new NodeList<>();
        Statement initStmt = getInitialization(testStmt.clone(), oracles);
        Statement postStmt = getPostStatement(initStmt.clone(), testStmt.clone());
        oracles = oracles
                .stream()
                .map(o -> contextualizeOracle(initStmt, testStmt, o))
                .toList();
        List<OracleOutput> preConditions = oracles
                .stream()
                .filter(o -> o.oracleType().equals(OracleType.PRE))
                .toList();
        List<OracleOutput> throwsConditions = oracles
                .stream()
                .filter(o -> o.oracleType().equals(OracleType.EXCEPT_POST))
                .toList();
        List<OracleOutput> postConditions = oracles
                .stream()
                .filter(o -> o.oracleType().equals(OracleType.NORMAL_POST))
                .toList();
        oracleStatements.addAll(getPreConditions(preConditions));
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
                    NodeList<Statement> otherStatements = getOracleStatements(testFile, originalBody, testStatement, relatedOracles);
                    System.out.println(otherStatements);
//                    newBody.addAll(getOracleStatements(testFile, originalBody, testStatement, relatedOracles));
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
