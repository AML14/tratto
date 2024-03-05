import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.ArrayInitializerExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.LambdaExpr;
import com.github.javaparser.ast.expr.LiteralExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.MethodReferenceExpr;
import com.github.javaparser.ast.expr.SuperExpr;
import com.github.javaparser.ast.expr.ThisExpr;
import com.github.javaparser.ast.stmt.EmptyStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.TryStmt;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.type.VoidType;
import data.OracleOutput;
import data.OracleType;
import data.TogType;

import java.lang.reflect.Method;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * This class provides the functionality for inserting oracles into test
 * prefixes.
 */
public class OracleInserter {
    /** A ClassLoader used to load classes outside the JVM. */
    private static ClassLoader classLoader;
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

    /** Private constructor to avoid creating an instance of this class. */
    private OracleInserter() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
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
     * Removes type arguments from a parameterized type name.
     *
     * @param parameterizedType a type name
     * @return the base type without type arguments
     */
    private static String removeTypeParameters(String parameterizedType) {
        return "";
    }

    /**
     * Gets the type names of all parameters from the method signature. If a
     * parameter is a generic type parameter, then uses "java.lang.Object".
     *
     * @param methodSignature a method signature
     * @return all parameter type names in the method signature
     */
    private static List<String> getParameterTypeNames(String methodSignature) {
        return new ArrayList<>();
    }

    /**
     * Gets the variable names of all parameters from the method signature.
     *
     * @param methodSignature a method signature
     * @return all variable parameter names in the method signature
     */
    private static List<String> getParameterNames(String methodSignature) {
        return new ArrayList<>();
    }

    /**
     * Gets the array level of a fully qualified name.
     *
     * @param fullyQualifiedName a fully qualified name
     * @return the array level of the fully qualified name
     */
    private static int getArrayLevel(String fullyQualifiedName) {
        return -1;
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
        return "";
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
        return "";
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
        return null;
    }

    /**
     * Gets the Class of a given type name.
     *
     * @param className a fully qualified type name
     * @return the Class corresponding to the type name
     */
    private static Class<?> getClass(String className) {
        return null;
    }

    /**
     * Gets all Class objects of a given list of types.
     *
     * @param classNames a list of fully qualified type names
     * @return the Class objects corresponding to the type names
     * @see OracleInserter#getClass(String)
     */
    private static List<Class<?>> getClasses(List<String> classNames) {
        return new ArrayList<>();
    }

    /**
     * Gets the reflection {@link Method} representation of a method.
     *
     * @param className the fully qualified name of the declaring class
     * @param methodSignature the method signature
     * @return the reflection representation of the method
     */
    private static Method getMethod(String className, String methodSignature) {
        return null;
    }

    /**
     * Checks if a given method is static.
     *
     * @param className the fully qualified name of the declaring class
     * @param methodSignature the method signature
     * @return true iff the given method is static
     */
    private static boolean isStatic(String className, String methodSignature) {
        return false;
    }

    /**
     * Gets the return type of a method.
     *
     * @param className the fully qualified name of the declaring class
     * @param methodSignature the method signature
     * @return the return type of the given method
     */
    private static Type getReturnType(String className, String methodSignature) {
        return new VoidType();
    }

    /**
     * Gets the name of a method from the method signature.
     *
     * @param methodSignature a method signature
     * @return the method name
     */
    private static String getMethodName(String methodSignature) {
        return "";
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
     * @param cu the Java file that imports {@code type}
     * @param type a type
     * @return the package of {@code type}. Returns an empty string for
     * primitive types.
     */
    private static String getPackageName(CompilationUnit cu, Type type) {
        return "";
    }

    /**
     * Gets the element type of a given type. If the type is an array, then
     * all outer arrays are removed. Otherwise, the type is not modified. For
     * example,
     *     "int[][]"    ->    "int"
     *     "Object"    ->    "Object"
     *     "T[]"    ->    "T"
     *
     * @param type a type
     * @return the element type of the given type
     */
    private static Type getElementType(Type type) {
        return new VoidType();
    }

    /**
     * Gets the fully qualified name of a given type.
     *
     * @param cu the Java file that imports {@code type}
     * @param type a type
     * @return the fully qualified name of {@code type}
     * @see OracleInserter#getPackageName(CompilationUnit, Type)
     */
    private static String getFullyQualifiedName(CompilationUnit cu, Type type) {
        return "";
    }

    /**
     * Gets the type of a given variable. Iterates through all statements in
     * the parent method to find the variable declaration.
     *
     * @param name a variable name
     * @param body all statements in the parent method that declares
     *             {@code name}
     * @return the type of {@code name}
     */
    private static Type getTypeOfName(List<Statement> body, String name) {
        return new VoidType();
    }

    /**
     * Gets the type of a literal expression. For example,
     *     {@code 0}    ->    {@code int}
     *     {@code "hello"}    ->    {@code java.lang.String}
     * If the literal is {@code null}, then this method returns null, as
     * JavaParser does not have a {@link Type} representation for null types.
     *
     * @param literalExpr a literal value expression
     * @return the corresponding type of the expression. Returns null if the
     * literal is a null expression.
     * @throws IllegalArgumentException if the literal expression is an
     * unknown type
     */
    private static Type getTypeOfLiteral(LiteralExpr literalExpr) {
        return new VoidType();
    }

    /** All expression types that cannot be parsed to find a Type. */
    private static final List<Class<?>> unsupportedExpr = List.of(
            AnnotationExpr.class,
            ArrayInitializerExpr.class,
            FieldAccessExpr.class,
            LambdaExpr.class,
            MethodCallExpr.class,
            MethodReferenceExpr.class,
            SuperExpr.class,
            ThisExpr.class
    );

    /**
     * Gets the type of a given expression. This method handles four types of
     * expressions:
     * <ul>
     *     <li>Cast (e.g. "{@code (double) 1}"): If the value
     *     being cast is null, then returns null. Otherwise, returns the type
     *     of the cast expression.</li>
     *     <li>Name (e.g. "{@code x}"): Searches the body of the declaring
     *     method to find the variable type.</li>
     *     <li>Binary (e.g. "{@code y == null}"): Returns boolean.</li>
     *     <li>Literal (e.g. "{@code "Hello, world"}): Parses the type of the
     *     literal expression. Returns null if the literal value is null.</li>
     * </ul>
     *
     * @param body all statements in the parent method
     * @param expr a Java variable or literal expression
     * @return the type of the given expression. Returns VoidType if the given
     * Expression cannot be parsed to find a Type.
     * @see OracleInserter#getTypeOfName(List, String)
     * @see OracleInserter#getTypeOfLiteral(LiteralExpr)
     */
    private static Type getTypeOfExpression(List<Statement> body, Expression expr) {
        return new VoidType();
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
        return new ArrayList<>();
    }

    /**
     * Checks if a list of parameter types from a method signature is
     * equivalent to a list of parameters from a method call. These two lists
     * cannot be directly compared (e.g.
     * {@code methodSignatureTypes.equals(methodCallTypes)}) as the method
     * call may use null literal expressions, which may apply to any object
     * types. Similarly, the method signature may use generic type parameters,
     * which also may apply to any object types.
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
        return false;
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
        return false;
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
        return new ArrayList<>();
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
        return new EmptyStmt();
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
        return new EmptyStmt();
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
        return new EmptyStmt();
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
        return "";
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
        return null;
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
        return null;
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
        return null;
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
        return null;
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
        return new NodeList<>();
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
        return new TryStmt();
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
        return new IfStmt();
    }

    /**
     * Sets the body of the last "else" statement in an if-block.
     *
     * @param ifStmt an if-block
     * @param postConditions a list of Java statements
     */
    private static void setLastElseBranch(IfStmt ifStmt, NodeList<Statement> postConditions) {
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
        return new NodeList<>();
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
        return new NodeList<>();
    }

    /**
     * Adds axiomatic oracles to test prefixes in a given Java file.
     * Axiomatic oracles are not specific to a given test prefix. The oracles
     * are inserted wherever they may be applicable in source code. For
     * example, if a TOG generates an axiomatic post-condition for a method
     * "foo", then the oracle is added after every call to "foo" in the test
     * prefix. The approach for inserting an axiomatic oracle differs based on
     * the {@link OracleType} (PRE, NORMAL_POST, EXCEPT_POST). This method
     * iterates through each line in each test case, and adds all related
     * oracles.
     *
     * @param testFile a Java test file
     * @param oracles a list of test oracles made by an axiomatic tog
     */
    private static void insertAxiomaticOracles(CompilationUnit testFile, List<OracleOutput> oracles) {
    }

    /**
     * Adds an assertion to the end of a given test prefix. This method does
     * nothing if the assertion is an empty string.
     *
     * @param testCase a test case
     * @param assertion the assertion to add
     */
    private static void insertNonAxiomaticAssertion(MethodDeclaration testCase, String assertion) {
    }

    /**
     * Wraps a test prefix with a try/catch block, where the catch block
     * expects a given exception type.
     *
     * @param testCase a test case
     * @param exception the exception to catch
     */
    private static void insertNonAxiomaticException(MethodDeclaration testCase, String exception) {
    }

    /**
     * Gets the non-axiomatic oracle corresponding to a given test from a list
     * of oracles.
     *
     * @param testName a test name
     * @param oracles a list of oracle records
     * @return the oracle record with the given test name. Returns null if no
     * such oracle exists.
     */
    private static OracleOutput getOracleWithTestName(String testName, List<OracleOutput> oracles) {
        return null;
    }

    /**
     * Adds non-axiomatic oracles to test prefixes in a given test file. Each
     * oracle is matched to its corresponding test prefix using the
     * {@link OracleOutput#testName()} field.
     *
     * @param testFile a Java file of test prefixes
     * @param oracles a list of test oracles made by a non-axiomatic tog
     */
    private static void insertNonAxiomaticOracles(CompilationUnit testFile, List<OracleOutput> oracles) {
    }

    /**
     * Sets the ClassLoader corresponding to a given classpath.
     *
     * @param classpath the classpath of a project under analysis.
     */
    private static void setClassLoader(String classpath) {
    }

    /**
     * Sets the SymbolSolver used by the StaticJavaParser for a given
     * classpath. This method must be called before attempting to resolve
     * types from the given classpath.
     *
     * @param classpath a classpath
     */
    public static void setJavaParser(String classpath) {
    }

    /**
     * Recursively gets all OracleOutputs from all JSON files in a given
     * directory and its subdirectories.
     *
     * @param pathToOracles a directory containing OracleOutput JSON files
     * @return the corresponding OracleOutput objects
     */
    private static List<OracleOutput> getOracleOutputs(Path pathToOracles) {
        return new ArrayList<>();
    }

    /** A pattern used to find the "[tog]-oracles" segment of a path. */
    private static final Pattern oracleTogPattern = Pattern.compile("\\b[a-zA-Z]+-oracles\\b");

    /**
     * Gets the TOG corresponding to an arbitrary path to generated oracles.
     * This method assumes that the path has the format:
     * "path/to/[tog]-oracles/com/example"
     * and returns the TogType corresponding to the "[tog]".
     *
     * @param pathToOracles a path to oracles generated by a TOG
     * @return the corresponding TOG that generated the oracles
     */
    private static TogType getTogFromOraclePath(Path pathToOracles) {
        return null;
    }

    /**
     * Adds a given collection of oracles to a given collection of test
     * prefixes generated by EvoSuite. The approach for adding oracles varies
     * based on whether the oracles are axiomatic or non-axiomatic. Saves the
     * new test suites to "output/[tog]-tests/..." where [tog] is the
     * corresponding test oracle generator. The TOG is extracted from the
     * {@code pathToOracles} parameter. This method will throw an error if the
     * path does not contain a TOG name (or contains multiple tog names). This
     * method does NOT override the original test prefixes.
     *
     * @param pathToPrefixes the path to the test prefixes
     * @param pathToOracles the path to the TOG oracles
     * @param classpath the classpath of the project under analysis
     */
    public static void insertOracles(
            Path pathToPrefixes,
            Path pathToOracles,
            String classpath
    ) {
    }
}
