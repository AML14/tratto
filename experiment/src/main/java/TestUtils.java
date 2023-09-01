import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.comments.LineComment;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.BooleanLiteralExpr;
import com.github.javaparser.ast.expr.CharLiteralExpr;
import com.github.javaparser.ast.expr.DoubleLiteralExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.IntegerLiteralExpr;
import com.github.javaparser.ast.expr.LiteralExpr;
import com.github.javaparser.ast.expr.LongLiteralExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.NullLiteralExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.expr.TextBlockLiteralExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.CatchClause;
import com.github.javaparser.ast.stmt.EmptyStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.TryStmt;
import com.github.javaparser.ast.type.PrimitiveType;
import com.github.javaparser.ast.type.Type;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import data.OracleOutput;
import data.OracleType;
import data.TogType;

/**
 * This class provides static methods for removing oracles from a test suite,
 * and inserting oracles into test prefixes.
 */
public class TestUtils {
    /** A ClassLoader used to load classes outside the JVM. */
    private static ClassLoader classLoader;
    /** A global unique ID to avoid duplicate test names. */
    private static int testID = 0;
    /** A unique id for placeholder variable names when inserting oracles. */
    private static int variableID = 0;
    /** All primitive fully qualified type names.  */
    private static final List<String> primitiveTypes = List.of(
            "boolean",
            "byte",
            "char",
            "short",
            "int",
            "long",
            "float",
            "double"
    );
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
    /** A list of all supported axiomatic test oracle generators. */
    private static final List<TogType> axiomaticTogs = List.of(TogType.JDOCTOR, TogType.TRATTO);

    /** Private constructor to avoid creating an instance of this class. */
    private TestUtils() {
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
     * @see TestUtils#allJUnitAssertionsMethods
     * @see TestUtils#isFail(Statement)
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
     * @see TestUtils#splitTests(CompilationUnit)
     * @see TestUtils#removeExceptionalOracles(CompilationUnit)
     * @see TestUtils#removeAssertionOracles(CompilationUnit)
     */
    public static void removeOracles(Path dir, String fullyQualifiedName) {
        Path fullyQualifiedNamePath = FileUtils.getRelativePathFromFullyQualifiedClassName(fullyQualifiedName);
        int classNameIdx = fullyQualifiedNamePath.getNameCount() - 1;
        Path simplePath = classNameIdx > 0 ?
                output.resolve("evosuite-tests-simple").resolve(fullyQualifiedNamePath.subpath(0, classNameIdx)) :
                output.resolve("evosuite-tests-simple");
        Path prefixPath = classNameIdx > 0 ?
                output.resolve("evosuite-prefix").resolve(fullyQualifiedNamePath.subpath(0, classNameIdx)) :
                output.resolve("evosuite-prefix");
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
        if (primitiveTypes.contains(className)) {
            return getPrimitiveClass(className);
        } else {
            try {
                className = removeTypeParameters(className);
                className = fqnToClassGetName(className);
                return Class.forName(className, true, classLoader);
            } catch (ClassNotFoundException e) {
                // return "java.lang.Object" for type parameters
                if (!className.contains(".")) {
                    return Object.class;
                }
                throw new Error("Unable to find class " + className);
            }
        }
    }

    /**
     * Gets all Class objects of a given list of types.
     *
     * @param classNames a list of fully qualified type names
     * @return the Class objects corresponding to the type names
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
     * Gets the reflection {@link Method} representation of a method.
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
     * Removes type arguments from a parameterized type name.
     *
     * @param parameterizedType a type name
     * @return the base type without type arguments
     */
    private static String removeTypeParameters(String parameterizedType) {
        String regex = "<[^<>]*>";
        // repeatedly remove all type arguments.
        String previous;
        String current = parameterizedType;
        do {
            previous = current;
            current = previous.replaceAll(regex, "");
        } while (!current.equals(previous));
        return current;
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
     * @return all parameter type names in the method signature
     */
    private static List<String> getParameterTypeNames(String methodSignature) {
        String parameters = methodSignature.substring(methodSignature.indexOf('(') + 1, methodSignature.indexOf(')'));
        if (parameters.length() == 0) {
            return new ArrayList<>();
        }
        return Stream.of(parameters.split(","))
                .map(p -> {
                    String paramTypeFQN = p.trim().split(" ")[0].trim();
                    Class<?> paramClass = getClassOfName(paramTypeFQN);
                    return paramClass.getTypeName();
                })
                .toList();
    }

    /**
     * Gets the variable names of all parameters from the method signature.
     *
     * @param methodSignature a method signature
     * @return all variable parameter names in the method signature
     */
    private static List<String> getParameterNames(String methodSignature) {
        String parameters = methodSignature.substring(methodSignature.indexOf('(') + 1, methodSignature.indexOf(')'));
        if (parameters.length() == 0) {
            return new ArrayList<>();
        }
        return Stream.of(parameters.split(","))
                .map(p -> p.trim().split(" ")[1].trim())
                .toList();
    }

    /**
     * Gets the array level of a fully qualified name.
     *
     * @param fullyQualifiedName a fully qualified name
     * @return the array level of the fully qualified name
     */
    private static int getArrayLevel(String fullyQualifiedName) {
        int arrayLevel = 0;
        for (int i = 0; i < fullyQualifiedName.length(); i++) {
            if (fullyQualifiedName.charAt(i) == '[') {
                arrayLevel++;
            }
        }
        return arrayLevel;
    }

    /**
     * Gets the field descriptor of a given fully qualified primitive type
     * name.
     *
     * @param primitiveName a fully qualified primitive type name
     * @return the field descriptor corresponding to the primitive type
     * @throws IllegalArgumentException if the given type name is not a
     * primitive type
     */
    private static String fqnToFieldDescriptor(String primitiveName) {
        switch (primitiveName) {
            case "boolean" -> {
                return "Z";
            }
            case "byte" -> {
                return "B";
            }
            case "char" -> {
                return "C";
            }
            case "short" -> {
                return "S";
            }
            case "int" -> {
                return "I";
            }
            case "long" -> {
                return "J";
            }
            case "float" -> {
                return "F";
            }
            case "double" -> {
                return "D";
            }
            default -> throw new IllegalArgumentException("Unrecognized primitive type " + primitiveName);
        }
    }

    /**
     * Converts a fully qualified name to the {@code Class.getName()} form of
     * a name. This method does not distinguish between package names and
     * inner classes, such that no "$" symbols are added.
     *
     * @param fullyQualifiedName a fully qualified name
     * @return the {@code Class.getName()} form of a name
     */
    private static String fqnToClassGetName(String fullyQualifiedName) {
        int arrayLevel = getArrayLevel(fullyQualifiedName);
        if (arrayLevel == 0) {
            return fullyQualifiedName;
        }
        String componentType = fullyQualifiedName.replaceAll("\\[]", "");
        StringBuilder sb = new StringBuilder();
        if (primitiveTypes.contains(componentType)) {
            sb.append(fqnToFieldDescriptor(componentType));
            sb.insert(0, "[".repeat(arrayLevel));
        } else {
            sb.append(componentType);
            sb.insert(0, "L");
            sb.insert(0, "[".repeat(arrayLevel));
            sb.append(";");
        }
        return sb.toString();
    }

    /**
     * Gets the package name of a given JavaParser type. This method assumes
     * that all types (including types from the same package) have
     * corresponding import statements in the compilation unit. This is TRUE
     * for all compilation units generated by EvoSuite. If no matching import
     * statement is found, then the type is assumed to be in the "java.lang"
     * package. EvoSuite also requires each class to have a package, which
     * avoids the default package.
     *
     * @param cu the Java file importing the given type
     * @param type a type
     * @return the package of the given type. Returns an empty string for
     * primitive types.
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
     * Gets the component type of a given type. If the type is an array, then
     * all outer arrays are removed. Otherwise, the type is not modified. For
     * example,
     *     "int[][]"    ->    "int"
     *     "Object"    ->    "Object"
     *     "T[]"    ->    "T"
     *
     * @param type a type
     * @return the base component type of the given type
     */
    private static Type getComponentType(Type type) {
        while (type.isArrayType()) {
            type = type.asArrayType().getComponentType();
        }
        return type;
    }

    /**
     * Gets the fully qualified name of a given type.
     *
     * @param cu the Java file importing the given type
     * @param type a type
     * @return the fully qualified name of the given type
     * @see TestUtils#getPackageName(CompilationUnit, Type)
     */
    private static String getFullyQualifiedName(CompilationUnit cu, Type type) {
        if (getComponentType(type).isClassOrInterfaceType()) {
            return getPackageName(cu, type) + "." + type.asString();
        } else {
            return type.asString();
        }
    }

    /**
     * Gets the type of a given variable. Iterates through all statements in
     * the parent method to find the variable declaration.
     *
     * @param name a variable name
     * @param body all statements in the parent method
     * @return the type of the given variable name
     */
    private static Type getTypeOfName(List<Statement> body, String name) {
        for (Statement statement : body) {
            if (!statement.isExpressionStmt()) {
                continue;
            }
            Expression expression = statement.asExpressionStmt().getExpression();
            if (!expression.isVariableDeclarationExpr()) {
                continue;
            }
            for (VariableDeclarator variableDeclarator : expression.asVariableDeclarationExpr().getVariables()) {
                if (name.equals(variableDeclarator.getNameAsString())) {
                    return variableDeclarator.getType();
                }
            }
        }
        throw new IllegalArgumentException("Unable to find the type of the variable " + name);
    }

    /**
     * Gets the type of a literal expression. For example,
     *     {@code 0}    ->    {@code int}
     *     {@code "hello"}    ->    {@code java.lang.String}
     * If the literal is "null", then this method returns null, as JavaParser
     * does not have a {@link Type} representation for null types.
     *
     * @param literalExpr a literal value expression
     * @return the corresponding type of the expression. Returns null if the
     * literal is a null expression.
     * @throws IllegalArgumentException if the literal expression is an
     * unknown type
     */
    private static Type getTypeOfLiteral(LiteralExpr literalExpr) {
        if (literalExpr instanceof BooleanLiteralExpr) {
            return PrimitiveType.booleanType();
        } else if (literalExpr instanceof NullLiteralExpr) {
            return null;
        } else if (literalExpr instanceof CharLiteralExpr) {
            return PrimitiveType.charType();
        } else if (literalExpr instanceof DoubleLiteralExpr) {
            return PrimitiveType.doubleType();
        } else if (literalExpr instanceof IntegerLiteralExpr) {
            return PrimitiveType.intType();
        } else if (literalExpr instanceof LongLiteralExpr) {
            return PrimitiveType.longType();
        } else if (
                literalExpr instanceof StringLiteralExpr ||
                literalExpr instanceof TextBlockLiteralExpr
        ) {
            return StaticJavaParser.parseType("String");
        } else {
            throw new IllegalArgumentException("Unknown literal expression type " + literalExpr);
        }
    }

    /**
     * Gets the type of a given expression.
     *
     * @param body all statements in the parent method
     * @param expr a Java variable or literal expression
     * @return the type of the given expression
     */
    private static Type getTypeOfExpression(List<Statement> body, Expression expr) {
        if (expr.isCastExpr()) {
            Type baseType = getTypeOfExpression(body, expr.asCastExpr().getExpression());
            // null types should always be null, otherwise use cast type
            if (baseType == null) {
                return null;
            } else {
                return expr.asCastExpr().getType();
            }
        } else if (expr.isNameExpr()) {
            // get type corresponding to variable name
            return getTypeOfName(body, expr.asNameExpr().getNameAsString());
        } else if (expr.isBinaryExpr()) {
            // binary expressions are booleans
            return PrimitiveType.booleanType();
        } else {
            // get literal value
            return getTypeOfLiteral(expr.asLiteralExpr());
        }
    }

    /**
     * Gets the type names of all parameters in a JavaParser method call. If
     * the method call uses a null literal expression as an argument, then the
     * corresponding entry in the returned list is null.
     *
     * @param cu the parent Java file of {@code body}
     * @param body a list of statements in a method body
     * @param methodCall a method call in the given method body
     * @return all parameter type names in the given method. May contain null
     * values for null literal expressions.
     */
    private static List<String> getParameterTypeNames(
            CompilationUnit cu,
            List<Statement> body,
            MethodCallExpr methodCall
    ) {
        return methodCall.getArguments()
                .stream()
                .map(arg -> {
                    Type type = getTypeOfExpression(body, arg);
                    if (type == null) {
                        return null;
                    }
                    String fqn = getFullyQualifiedName(cu, type);
                    return removeTypeParameters(fqn);
                })
                .toList();
    }

    /**
     * Checks if a list of parameter types from a method signature is
     * equivalent to a list of parameters from a method call. These two lists
     * cannot be directly compared (e.g.
     * {@code methodSignatureTypes.equals(methodCallTypes)}) because the
     * method call may use null literal expressions, which may apply to any
     * object type.
     *
     * @param methodSignatureTypes all parameter types from a method signature
     * @param methodCallTypes all parameter types from a method call. May
     *                        contain null values.
     * @return true iff the two lists represent equivalent types
     */
    private static boolean isEqualParameterTypes(
            List<String> methodSignatureTypes,
            List<String> methodCallTypes
    ) {
        if (methodSignatureTypes.size() != methodCallTypes.size()) {
            return false;
        }
        for (int i = 0; i < methodSignatureTypes.size(); i++) {
            String signatureType = methodSignatureTypes.get(i);
            String callType = methodCallTypes.get(i);
            // handle null value case
            if (callType == null) {
                if (primitiveTypes.contains(signatureType)) {
                    continue;
                } else {
                    return false;
                }
            }
            // handle type parameter case
            int signatureArrayLevel = getArrayLevel(signatureType);
            int callArrayLevel = getArrayLevel(callType);
            if (callArrayLevel != signatureArrayLevel) {
                return false;
            }
            String signatureElementType = signatureType.replaceAll("\\[]", "");
            String callElementType = callType.replaceAll("\\[]", "");
            if (signatureElementType.equals("java.lang.Object") && !primitiveTypes.contains(callElementType)) {
                continue;
            }
            // handle base case
            if (!signatureType.equals(callType)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if a given JavaParser method call corresponds to the same method
     * as a given signature. This method only checks the method signatures and
     * does not consider the declaring classes.
     *
     * @param jpFile the parent Java file of {@code jpBody}
     * @param jpBody a list of statements in a method body
     * @param jpMethodCall a method call in the given method body
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
        return expectedName.equals(jpName) && isEqualParameterTypes(expectedTypes, jpTypes);
    }

    /**
     * Gets all oracles applicable to a Java statement. An oracle is
     * applicable to a statement if and only if it corresponds to the method
     * call in the given statement.
     *
     * @param cu the parent Java file of {@code body}
     * @param body the parent method of {@code stmt}
     * @param stmt a Java statement
     * @param allOracles all possible oracles
     * @return all oracles corresponding to the method call in the given
     * statement
     */
    private static List<OracleOutput> getRelatedOracles(
            CompilationUnit cu,
            List<Statement> body,
            Statement stmt,
            List<OracleOutput> allOracles
    ) {
        List<MethodCallExpr> methodCalls = getAllMethodCallsOfStatement(stmt);
        if (methodCalls.isEmpty()) {
            return new ArrayList<>();
        }
        return allOracles
                .stream()
                .filter(o -> isMatchingMethod(cu, body, methodCalls.get(0), o.methodSignature()))
                .toList();
    }

    /**
     * Gets a statement that initializes the variable returned by a given
     * expression. There are three possible cases depending on the type of
     * expression:
     * <ul>
     *     <li>Variable Declaration: Instantiate the variable using the given
     *     name, with no initial assignment</li>
     *     <li>Method Call (ignored return type): Instantiate a dummy
     *     placeholder variable</li>
     *     <li>Other: Do nothing</li>
     * </ul>
     * If the expression is not a variable declaration or method call, then
     * the relevant variable must already be initialized, and does not require
     * an additional initialization statement (e.g. {@code x += getCount();}).
     *
     * @param testExpr a Java expression with a method call
     * @param returnType the return type of the method call in
     *                   {@code testExpr}
     * @return a Java statement that initializes the return type of the
     * original expression. Returns an empty statement if a variable does not
     * need to be initialized.
     */
    private static Statement getInitStmt(
            Expression testExpr,
            Type returnType
    ) {
        if (testExpr.isVariableDeclarationExpr()) {
            VariableDeclarator variableDeclarator = testExpr.asVariableDeclarationExpr().getVariable(0);
            VariableDeclarationExpr variableDeclarationExpr = new VariableDeclarationExpr(
                    returnType,
                    variableDeclarator.getNameAsString()
            );
            return new ExpressionStmt(variableDeclarationExpr);
        } else if (testExpr.isMethodCallExpr()) {
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

    /**
     * Gets a statement that initializes the variable returned by the method
     * call of a given statement. If the method call has a void return type or
     * the variable affected by the statement is already initialized, then
     * this method returns an empty statement. If the return type of the
     * expression is ignored in the original statement, then a dummy variable
     * is initialized for any possible post-conditions.
     *
     * @param testStmt a Java statement with a method call
     * @param oracles all oracles related to the method call of
     *                {@code testStmt}
     * @return a Java statement that initializes a variable for the return
     * type of the original statement. Returns an empty statement if a
     * variable does not need to be initialized.
     */
    private static Statement getInitStmt(
            Statement testStmt,
            List<OracleOutput> oracles
    ) {
        String className = oracles.get(0).className();
        String methodSignature = oracles.get(0).methodSignature();
        Type returnType = getReturnType(className, methodSignature);
        if (returnType.isVoidType() || !testStmt.isExpressionStmt()) {
            return new EmptyStmt();
        }
        Expression testExpr = testStmt.asExpressionStmt().getExpression();
        return getInitStmt(testExpr, returnType);
    }

    /**
     * Gets a statement that assigns the value returned by the method call
     * in a given statement under test, to the variable initialized in a given
     * Java initialization statement. The return type of the method call in
     * the test statement is equal to the type of the variable in the
     * initialization statement.
     *
     * @param initStmt a Java statement that initializes a variable for the
     *                 return type of the method call in {@code testStmt}
     * @param testStmt a Java statement with a method call
     * @return a Java statement that assigns the return type of the method
     * call in {@code testStmt} to the variable initialized by
     * {@code initStmt}. Returns {@code testStmt} if {@code initStmt} is
     * empty (e.g. void method).
     */
    private static Statement getPostStmt(
            Statement initStmt,
            Statement testStmt
    ) {
        if (initStmt.isEmptyStmt()) {
            // if no variable is initialized, then use original statement.
            return testStmt;
        }
        Expression initExpr = initStmt
                .asExpressionStmt()
                .getExpression();
        Expression testExpr = getAllMethodCallsOfStatement(testStmt)
                .get(0);
        String name = initExpr
                .asVariableDeclarationExpr()
                .getVariable(0)
                .getNameAsString();
        AssignExpr assignExpr = new AssignExpr(
                new NameExpr(name),
                testExpr,
                AssignExpr.Operator.ASSIGN
        );
        return new ExpressionStmt(assignExpr);
    }

    /**
     * Replaces all instances of original names in a given Java expression
     * with corresponding new names. Each entry in {@code originalNames} has a
     * corresponding entry in {@code newNames} at the same index. If a
     * variable name in {@code stringExpression} does not appear in
     * {@code originalNames}, then it is not modified.
     *
     * @param originalNames the original names
     * @param newNames the new names. May contain literal values.
     * @param stringExpression a Java expression
     * @return an equivalent Java expression String with new names
     * @throws IllegalArgumentException if {@code originalNames} and
     * {@code newNames} do not have equal sizes
     */
    private static String replaceNames(
            List<String> originalNames,
            List<String> newNames,
            String stringExpression
    ) {
        if (originalNames.size() != newNames.size()) {
            throw new IllegalArgumentException(originalNames + " and " + newNames + " are not equal sizes");
        }
        Expression jpExpression = StaticJavaParser.parseExpression(stringExpression);
        jpExpression.walk(NameExpr.class, name -> {
            int idx = originalNames.indexOf(name.getNameAsString());
            if (idx != -1) {
                name.replace(new NameExpr(newNames.get(idx)));
            }
        });
        return jpExpression.toString();
    }

    /**
     * Replaces all parameter variable names in an oracle with names or
     * literal expressions from a method call of the method under test.
     *
     * @param testStmt a Java test statement
     * @param oracleOutput an oracle record
     * @return an equivalent oracle record with relevant parameter names or
     * literal values
     */
    private static OracleOutput getParameterID(
            Statement testStmt,
            OracleOutput oracleOutput
    ) {
        List<String> originalNames = getParameterNames(oracleOutput.methodSignature());
        List<String> contextNames = getAllMethodCallsOfStatement(testStmt).get(0).getArguments()
                .stream()
                .map(Expression::toString)
                .toList();
        String contextOracle = replaceNames(originalNames, contextNames, oracleOutput.oracle());
        return new OracleOutput(
                oracleOutput.className(),
                oracleOutput.methodSignature(),
                oracleOutput.oracleType(),
                oracleOutput.prefix(),
                contextOracle,
                oracleOutput.exception(),
                oracleOutput.testName()
        );
    }

    /**
     * Replaces all instances of "receiverObjectID" in an oracle with the
     * corresponding object name in source code. The "receiverObjectID"
     * corresponds to the name of the instance of the declaring class for the
     * method under test. If the method under test is a static method, then
     * the "receiverObjectID" does not exist, and the given oracle is not
     * modified.
     *
     * @param testStmt a Java test statement
     * @param oracleOutput an oracle record
     * @return an equivalent oracle record with the relevant object name
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
        String contextOracle = replaceNames(List.of(originalName), List.of(contextName), oracleOutput.oracle());
        return new OracleOutput(
                oracleOutput.className(),
                oracleOutput.methodSignature(),
                oracleOutput.oracleType(),
                oracleOutput.prefix(),
                contextOracle,
                oracleOutput.exception(),
                oracleOutput.testName()
        );
    }

    /**
     * Replaces all instances of "methodResultID" in an oracle with the
     * corresponding object name in source code. The "methodResultID"
     * corresponds to the name of the variable returned by the method under
     * test. If the method under test has a void return type (no corresponding
     * variable assignment), then the "methodResultID" does not exist, and the
     * given oracle is not modified.
     *
     * @param postStmt a Java statement that assigns the return value of the
     *                 method under test to a variable
     * @param oracleOutput the original oracle
     * @return an equivalent oracle record with the relevant object name
     */
    private static OracleOutput getMethodResultID(
            Statement postStmt,
            OracleOutput oracleOutput
    ) {
        if (!postStmt.isExpressionStmt()) {
            return oracleOutput;
        }
        Expression postExpr = postStmt.asExpressionStmt().getExpression();
        if (!postExpr.isAssignExpr()) {
            return oracleOutput;
        }
        String originalName = "methodResultID";
        String contextName = postExpr
                .asAssignExpr()
                .getTarget()
                .asNameExpr()
                .getNameAsString();
        String contextOracle = replaceNames(List.of(originalName), List.of(contextName), oracleOutput.oracle());
        return new OracleOutput(
                oracleOutput.className(),
                oracleOutput.methodSignature(),
                oracleOutput.oracleType(),
                oracleOutput.prefix(),
                contextOracle,
                oracleOutput.exception(),
                oracleOutput.testName()
        );
    }

    /**
     * Replaces all instances of original names in an oracle with their
     * corresponding instance names in a test statement. The original oracle
     * uses the parameter names from the method signature, "receiverObjectID"
     * for the instance of the declaring class, and "methodResultID" for the
     * instance of the method return type.
     *
     * @param postStmt a Java statement that assigns the return value of the
     *                 method under test to a variable
     * @param testStmt a Java test statement
     * @param oracleOutput an original oracle record
     * @return an equivalent oracle record with contextual names from a test
     * statement
     */
    private static OracleOutput contextualizeOracle(
            Statement postStmt,
            Statement testStmt,
            OracleOutput oracleOutput
    )  {
        oracleOutput = getParameterID(testStmt, oracleOutput);
        oracleOutput = getReceiverObjectID(testStmt, oracleOutput);
        oracleOutput = getMethodResultID(postStmt, oracleOutput);
        return oracleOutput;
    }

    /**
     * Gets all assertions corresponding to preconditions by wrapping oracles
     * as the condition of an {@code assertTrue} method call.
     *
     * @param oracles all relevant precondition oracles
     * @return all Java statements of JUnit assertions for the given oracles
     */
    private static NodeList<Statement> getPreConditions(
            List<OracleOutput> oracles
    ) {
        return new NodeList<>(oracles
                .stream()
                .map(o -> StaticJavaParser.parseStatement("assertTrue(" + o.oracle() + ");"))
                .toList());
    }

    /**
     * Gets a try/catch block corresponding to the body of an exceptional
     * axiomatic oracle. The try/catch block calls {@code postStmt} and
     * catches the expected exception. If the expected exception is not
     * thrown, then the test fails.
     *
     * @param postStmt a Java statement that calls the method under test and
     *                 assigns its output to a variable
     * @param oracleOutput an exceptional oracle
     * @return a try/catch block corresponding to the exceptional oracle
     */
    private static TryStmt getTryCatchBlock(
            Statement postStmt,
            OracleOutput oracleOutput
    ) {
        Type exceptionType = StaticJavaParser.parseType(oracleOutput.exception());
        Parameter exceptionParameter = new Parameter(exceptionType, "e");
        Comment comment = new LineComment(" Successfully thrown exception");
        BlockStmt catchBody = new BlockStmt();
        catchBody.addOrphanComment(comment);
        CatchClause catchClause = new CatchClause(exceptionParameter, catchBody);
        NodeList<Statement> tryBody = new NodeList<>(
                postStmt,
                StaticJavaParser.parseStatement("fail();")
        );
        return new TryStmt()
                .setTryBlock(new BlockStmt(tryBody))
                .setCatchClauses(new NodeList<>(catchClause));
    }

    /**
     * Gets an if-block representing all exceptional axiomatic oracles. Each
     * condition in an if (or else if) statement corresponds to a program
     * state in which an exception should be thrown. The body of each if
     * statement has a try/catch block corresponding to the expected exception
     * after the post statement is executed.
     *
     * @param postStmt a Java statement that calls the method under test and
     *                 assigns its output to a variable
     * @param oracles all relevant exceptional oracles
     * @return an if-block representing all exceptional oracles. Returns null
     * if there are no exceptional post conditions.
     */
    private static IfStmt getThrowsConditions(
            Statement postStmt,
            List<OracleOutput> oracles
    ) {
        if (oracles.size() == 0) {
            return null;
        }
        List<Expression> conditions = oracles
                .stream()
                .map(o -> (Expression) StaticJavaParser.parseExpression(o.oracle()))
                .toList();
        List<BlockStmt> tryStmts = oracles
                .stream()
                .map(o -> new BlockStmt().addStatement(getTryCatchBlock(postStmt, o)))
                .toList();
        assert conditions.size() == tryStmts.size();
        IfStmt ifStmt = new IfStmt(conditions.get(0), tryStmts.get(0), new BlockStmt());
        IfStmt currentIfStmt = ifStmt;
        for (int i = 1; i < conditions.size(); i++) {
            IfStmt nextIfStmt = new IfStmt(conditions.get(i), tryStmts.get(1), new BlockStmt());
            currentIfStmt.setElseStmt(nextIfStmt);
            currentIfStmt = new IfStmt();
        }
        return ifStmt;
    }

    /**
     * Sets the body of the last "else" statement in an if-block.
     *
     * @param ifStmt an if-block
     * @param postConditions a list of Java statements
     */
    private static void setLastElseBranch(IfStmt ifStmt, NodeList<Statement> postConditions) {
        IfStmt currentStatement = ifStmt;
        while (currentStatement.getElseStmt().orElseThrow() instanceof IfStmt) {
            currentStatement = (IfStmt) currentStatement.getElseStmt().orElseThrow();
        }
        currentStatement.setElseStmt(new BlockStmt(postConditions));
    }

    /**
     * Gets a list of statements corresponding to all axiomatic post
     * conditions of a method under test. If the given base is null (e.g. no
     * exceptional post conditions), then this method returns all normal post
     * conditions as a list of assertions (similar to the pre-conditions).
     * Otherwise, this method appends the normal post conditions as the body
     * of the "else" branch in the base if-block.
     *
     * @param base an if-block representing all exceptional oracles
     * @param postStmt a Java statement that calls the method under test and
     *                 assigns its output to a variable
     * @param oracles all relevant normal post-condition oracles
     * @return an if-block representing all post-conditions (normal and
     * exceptional) or a list of normal post-conditions
     */
    private static NodeList<Statement> getPostConditions(
            IfStmt base,
            Statement postStmt,
            List<OracleOutput> oracles
    ) {
        NodeList<Statement> postConditions = new NodeList<>();
        postConditions.add(postStmt);
        postConditions.addAll(oracles
                .stream()
                .map(o -> StaticJavaParser.parseStatement("assertTrue(" + o.oracle() + ");"))
                .toList());
        if (base == null) {
            return postConditions;
        }
        setLastElseBranch(base, postConditions);
        return new NodeList<>(base);
    }

    /**
     * Gets all assertions from the relevant oracles of a given test
     * statement. The approach for inserting oracles varies based on the
     * oracle type:
     * <ul>
     *     <li>Pre-condition: added as assertions before the method under test
     *     is called</li>
     *     <li>Throws condition: added as if-statements to check the program
     *     state before the method under test is called</li>
     *     <li>Post-condition: added to the "else" block of the aforementioned
     *     if-statements, or added as assertions after the method under test
     *     is called (if no exceptional conditions are present)</li>
     * </ul>
     *
     * @param testStmt a Java statement with a method call for the method
     *                 under test
     * @param oracles all oracles related to the method under test
     * @return a list of Java statements representing the axiomatic test
     * assertions
     */
    private static NodeList<Statement> getOracleStatements(
            Statement testStmt,
            List<OracleOutput> oracles
    ) {
        NodeList<Statement> oracleStatements = new NodeList<>();
        Statement initStmt = getInitStmt(testStmt, oracles);
        Statement postStmt = getPostStmt(initStmt, testStmt);
        oracles = oracles
                .stream()
                .map(o -> contextualizeOracle(postStmt, testStmt, o))
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
        NodeList<Statement> preBlock = getPreConditions(preConditions);
        IfStmt throwsBlock = getThrowsConditions(postStmt, throwsConditions);
        NodeList<Statement> postBlock = getPostConditions(throwsBlock, postStmt, postConditions);
        oracleStatements.addAll(preBlock);
        oracleStatements.add(initStmt);
        oracleStatements.addAll(postBlock);
        return new NodeList<>(oracleStatements
                .stream()
                .filter(stmt -> !stmt.isEmptyStmt())
                .toList());
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
                    newBody.addAll(getOracleStatements(testStatement, relatedOracles));
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
                    .filter(p -> !FileUtils.isScaffolding(p))
                    .forEach(testFile -> {
                        try {
                            CompilationUnit cu = StaticJavaParser.parse(testFile);
                            insertAxiomaticOracles(cu, oracles);
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
     * Adds an assertion to a given test case.
     *
     * @param testCase a method representation of a test case
     * @param assertion the string representation of the assertion to add
     */
    private static void insertNonAxiomaticAssertion(MethodDeclaration testCase, String assertion) {
        ExpressionStmt statement = new ExpressionStmt(StaticJavaParser.parseExpression(assertion));
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
     * @return the oracle record with the given prefix. Returns null if no
     * such oracle exists.
     */
    private static OracleOutput getOracleWithPrefix(String prefix, List<OracleOutput> oracles) {
        List<String> allPrefix = oracles
                .stream()
                .map(OracleOutput::prefix)
                .map(String::trim)
                .toList();
        int indexOfOracle = allPrefix.indexOf(prefix.trim());
        if (indexOfOracle == -1) {
            return null;
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
                    if (oracle != null) {
                        if (isExceptional(oracle)) {
                            insertNonAxiomaticException(testCase, oracle.exception());
                        } else {
                            insertNonAxiomaticAssertion(testCase, oracle.oracle());
                        }
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
                    .filter(p -> !FileUtils.isScaffolding(p))
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
     * Checks if a given TOG is axiomatic.
     *
     * @param tog a test oracle generator
     * @return true iff the given tog generates axiomatic test oracles (known
     * a priori)
     * @see TestUtils#axiomaticTogs
     */
    private static boolean isAxiomatic(TogType tog) {
        return axiomaticTogs.contains(tog);
    }

    private static ClassLoader getClassLoader(Path binDir) {
        try {
            URL binaryURL = binDir.toUri().toURL();
            return new URLClassLoader(new URL[]{binaryURL});
        } catch (MalformedURLException e) {
            throw new Error("Unable to get URL for system binaries " + binDir);
        }
    }

    /**
     * Adds oracles to test prefixes in a given directory. The approach for
     * adding oracles varies based on whether the oracles are axiomatic or
     * non-axiomatic. Saves the modified test prefixes in
     * output/tog-test/[tog], where [tog] is the given test oracle generator.
     * Does not override original test prefixes.
     *
     * @param binDir path to the system binaries
     * @param prefixDir a directory with Java test prefixes
     * @param tog a test oracle generator
     * @param oracles a list of test oracles made by the given tog
     * @see TestUtils#insertAxiomaticOracles(Path, List)
     * @see TestUtils#insertNonAxiomaticOracles(Path, List)
     */
    public static void insertOracles(Path binDir, Path prefixDir, TogType tog, List<OracleOutput> oracles) {
        classLoader = getClassLoader(binDir);
        Path testPath = output.resolve("tog-tests/" + tog);
        FileUtils.copy(prefixDir, testPath);
        if (isAxiomatic(tog)) {
            insertAxiomaticOracles(testPath, oracles);
        } else {
            insertNonAxiomaticOracles(testPath, oracles);
        }
    }
}
