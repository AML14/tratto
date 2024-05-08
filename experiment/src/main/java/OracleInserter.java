import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.comments.LineComment;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.ArrayAccessExpr;
import com.github.javaparser.ast.expr.ArrayCreationExpr;
import com.github.javaparser.ast.expr.ArrayInitializerExpr;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.BooleanLiteralExpr;
import com.github.javaparser.ast.expr.CastExpr;
import com.github.javaparser.ast.expr.CharLiteralExpr;
import com.github.javaparser.ast.expr.ClassExpr;
import com.github.javaparser.ast.expr.ConditionalExpr;
import com.github.javaparser.ast.expr.DoubleLiteralExpr;
import com.github.javaparser.ast.expr.EnclosedExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.InstanceOfExpr;
import com.github.javaparser.ast.expr.IntegerLiteralExpr;
import com.github.javaparser.ast.expr.LambdaExpr;
import com.github.javaparser.ast.expr.LiteralExpr;
import com.github.javaparser.ast.expr.LongLiteralExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.MethodReferenceExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.NullLiteralExpr;
import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.expr.SuperExpr;
import com.github.javaparser.ast.expr.TextBlockLiteralExpr;
import com.github.javaparser.ast.expr.ThisExpr;
import com.github.javaparser.ast.expr.TypeExpr;
import com.github.javaparser.ast.expr.UnaryExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.CatchClause;
import com.github.javaparser.ast.stmt.EmptyStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.ThrowStmt;
import com.github.javaparser.ast.stmt.TryStmt;
import com.github.javaparser.ast.type.PrimitiveType;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.type.VoidType;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JarTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;
import data.OracleOutput;
import data.OracleType;
import data.TogType;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * This class provides the functionality for inserting oracles into test
 * prefixes.
 */
public class OracleInserter {
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
    /** All primitive field descriptor type names. */
    private static final List<String> allPrimitiveFieldDescriptors = List.of(
            "Z",
            "B",
            "C",
            "S",
            "I",
            "J",
            "F",
            "D"
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
        List<MethodCallExpr> methodCalls = new ArrayList<>();
        statement
                .clone()
                .walk(MethodCallExpr.class, methodCalls::add);
        return methodCalls;
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
     * Gets the type names of all parameters from the method signature. If a
     * parameter is a generic type parameter, then uses "java.lang.Object".
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
                    String[] paramParts = p.trim().split(" ");
                    StringBuilder paramTypeFqnBuilder = new StringBuilder();
                    for (int i = 0; i < paramParts.length - 1; i++) {
                        paramTypeFqnBuilder
                                .append(paramParts[i].trim())
                                .append(" ");
                    }
                    String paramTypeFqn = paramTypeFqnBuilder.toString().trim();
                    Class<?> paramClass = getClass(paramTypeFqn);
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
     * Gets the fully qualified name of a given primitive field descriptor
     * type name.
     *
     * @param primitiveFD a primitive field descriptor type name
     * @return the fully qualified name corresponding to the primitive type
     * @throws IllegalArgumentException if the given type name is not a
     * recognized primitive type
     */
    private static String fieldDescriptorToFQN(String primitiveFD) {
        switch (primitiveFD) {
            case "Z" -> {
                return "boolean";
            }
            case "B" -> {
                return "byte";
            }
            case "C" -> {
                return "char";
            }
            case "S" -> {
                return "short";
            }
            case "I" -> {
                return "int";
            }
            case "J" -> {
                return "long";
            }
            case "F" -> {
                return "float";
            }
            case "D" -> {
                return "double";
            }
            default -> throw new IllegalArgumentException("Unrecognized primitive field descriptor " + primitiveFD);
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
    private static Class<?> getClass(String className) {
        if (className.endsWith("...")) {
            className = className.substring(0, className.length() - 3) + "[]";
        }
        if (primitiveTypes.contains(className)) {
            return getPrimitiveClass(className);
        } else {
            try {
                className = removeTypeParameters(className);
                className = fqnToClassGetName(className);
                return Class.forName(className);
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
     * @see OracleInserter#getClass(String)
     */
    private static List<Class<?>> getClasses(List<String> classNames) {
        List<Class<?>> classes = new ArrayList<>();
        for (String className : classNames) {
            classes.add(getClass(className));
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
    private static Method getMethod(String className, String methodSignature) {
        String methodName = getMethodName(methodSignature);
        List<Class<?>> parameterTypes = getClasses(getParameterTypeNames(methodSignature));
        Class<?> receiverObjectID = getClass(className);
        try {
            return receiverObjectID.getDeclaredMethod(methodName, parameterTypes.toArray(Class[]::new));
        } catch (NoSuchMethodException e) {
            throw new Error("Unable to find method " + methodSignature + " in " + className);
        }
    }

    /**
     * Checks if a given method is static.
     *
     * @param className the fully qualified name of the declaring class
     * @param methodSignature the method signature
     * @return true iff the given method is static
     */
    private static boolean isStatic(String className, String methodSignature) {
        Method method = getMethod(className, methodSignature);
        return Modifier.isStatic(method.getModifiers());
    }

    /**
     * Gets the return type of a method.
     *
     * @param className the fully qualified name of the declaring class
     * @param methodSignature the method signature
     * @return the return type of the given method
     */
    private static Type getReturnType(String className, String methodSignature) {
        Method method = getMethod(className, methodSignature);
        Class<?> returnType = method.getReturnType();
        String returnTypeName = returnType.getName();
        if (returnTypeName.startsWith("[")) {
            int arrayLevel = getArrayLevel(returnTypeName);
            String elementGetName = returnTypeName.substring(arrayLevel);
            String elementFqn;
            if (allPrimitiveFieldDescriptors.contains(elementGetName)) {
                elementFqn = fieldDescriptorToFQN(elementGetName);
            } else {
                elementFqn = elementGetName.substring(1, elementGetName.length() - 1);
            }
            return StaticJavaParser.parseType(elementFqn + "[]".repeat(arrayLevel));
        }
        return StaticJavaParser.parseType(returnTypeName);
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
        while (type.isArrayType()) {
            type = type.asArrayType().getComponentType();
        }
        return type;
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
        if (getElementType(type).isClassOrInterfaceType()) {
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
     * @param body all statements in the parent method that declares
     *             {@code name}
     * @return the type of {@code name}
     */
    private static Type getTypeOfName(List<Statement> body, String name) {
        for (Statement stmt : body) {
            if (!stmt.isExpressionStmt()) {
                continue;
            }
            Expression expr = stmt.asExpressionStmt().getExpression();
            if (!expr.isVariableDeclarationExpr()) {
                continue;
            }
            for (VariableDeclarator varDecl : expr.asVariableDeclarationExpr().getVariables()) {
                if (name.equals(varDecl.getNameAsString())) {
                    return varDecl.getType();
                }
            }
        }
        throw new IllegalArgumentException("Unable to find the type of the variable " + name);
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
        // return VoidType if the given type is not supported
        if (unsupportedExpr.contains(expr.getClass())) {
            return new VoidType();
        }
        // search all supported Expression types
        if (expr instanceof ArrayAccessExpr) {
            Type arrayType = getTypeOfName(body, expr.asArrayAccessExpr().getName().asNameExpr().getNameAsString());
            return arrayType.asArrayType().getElementType();
        } else if (expr instanceof ArrayCreationExpr) {
            return expr.asArrayCreationExpr().createdType();
        } else if (expr instanceof AssignExpr) {
            return getTypeOfExpression(body, expr.asAssignExpr().getTarget());
        } else if (expr instanceof BinaryExpr) {
            return PrimitiveType.booleanType();
        } else if (expr instanceof CastExpr) {
            Type baseType = getTypeOfExpression(body, expr.asCastExpr().getExpression());
            if (baseType == null) {
                return null;
            } else {
                return expr.asCastExpr().getType();
            }
        } else if (expr instanceof ClassExpr) {
            return expr.asClassExpr().getType();
        } else if (expr instanceof ConditionalExpr) {
            Type thenType = getTypeOfExpression(body, expr.asConditionalExpr().getThenExpr());
            Type elseType = getTypeOfExpression(body, expr.asConditionalExpr().getElseExpr());
            if (thenType == null || elseType == null) {
                return null;
            }
            if (thenType.equals(elseType)) {
                return thenType;
            } else {
                return StaticJavaParser.parseType("Object");
            }
        } else if (expr instanceof EnclosedExpr) {
            return getTypeOfExpression(body, expr.asEnclosedExpr().getInner());
        } else if (expr instanceof InstanceOfExpr) {
            return PrimitiveType.booleanType();
        } else if (expr instanceof LiteralExpr) {
            return getTypeOfLiteral(expr.asLiteralExpr());
        } else if (expr instanceof NameExpr) {
            return getTypeOfName(body, expr.asNameExpr().getNameAsString());
        } else if (expr instanceof ObjectCreationExpr) {
            return expr.asObjectCreationExpr().getType();
        } else if (expr instanceof TypeExpr) {
            return expr.asTypeExpr().getType();
        } else if (expr instanceof UnaryExpr) {
            return getTypeOfExpression(body, expr.asUnaryExpr().getExpression());
        } else if (expr instanceof VariableDeclarationExpr) {
            return expr.asVariableDeclarationExpr().getVariable(0).getType();
        }
        throw new IllegalArgumentException("Unrecognized Expression type " + expr.getClass());
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
        if (methodSignatureTypes.size() != methodCallTypes.size()) {
            return false;
        }
        for (int i = 0; i < methodSignatureTypes.size(); i++) {
            String signatureType = methodSignatureTypes.get(i);
            String callType = methodCallTypes.get(i);
            // handle null value case
            if (callType == null) {
                if (primitiveTypes.contains(signatureType)) {
                    return false;
                } else {
                    continue;
                }
            }
            // handle generic type parameter case
            int signatureArrayLevel = getArrayLevel(signatureType);
            int callArrayLevel = getArrayLevel(callType);
            String signatureElementType = signatureType.replaceAll("\\[]", "");
            String callElementType = callType.replaceAll("\\[]", "");
            if (signatureElementType.equals("java.lang.Object")) {
                if (callArrayLevel > signatureArrayLevel) {
                    // if the call type has a larger array level than the given signature, then continue
                    continue;
                } else if (callArrayLevel == signatureArrayLevel && !primitiveTypes.contains(callElementType)) {
                    // if base types are both objects of equal array level, then continue
                    continue;
                }
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
                .map(expr -> {
                    if (expr.isLiteralExpr() || (expr.isCastExpr() && expr.asCastExpr().getExpression().isLiteralExpr())) {
                        return "(" + expr + ")";
                    } else {
                        return expr.toString();
                    }
                }).toList();
        String contextOracle = replaceNames(originalNames, contextNames, oracleOutput.oracle());
        return new OracleOutput(
                oracleOutput.className(),
                oracleOutput.methodSignature(),
                oracleOutput.oracleType(),
                contextOracle,
                oracleOutput.exception(),
                oracleOutput.testName(),
                oracleOutput.statementIndex()
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
                contextOracle,
                oracleOutput.exception(),
                oracleOutput.testName(),
                oracleOutput.statementIndex()
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
                contextOracle,
                oracleOutput.exception(),
                oracleOutput.testName(),
                oracleOutput.statementIndex()
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

    /** The statement used to indicate that a pre-condition has been failed. */
    private static final BlockStmt preConditionFailStmt = new BlockStmt(new NodeList<>(new ThrowStmt(
            new ObjectCreationExpr()
                    .setType(StaticJavaParser.parseClassOrInterfaceType("Error"))
                    .addArgument(new StringLiteralExpr("TrattoError: Precondition failed, invalid test."))
    )));

    /**
     * Gets all assertions corresponding to preconditions by wrapping oracles
     * as the condition of an {@code assertTrue} method call.
     *
     * @param oracles all relevant precondition oracles
     * @return all Java statements of JUnit assertions for the given oracles
     */
    private static Statement getPreConditions(
            List<OracleOutput> oracles
    ) {
        if (oracles.size() == 0) {
            return new EmptyStmt();
        }
        List<Expression> conditions = oracles
                .stream()
                .map(o -> (Expression) StaticJavaParser.parseExpression("!(" + o.oracle() + ")"))
                .toList();
        IfStmt ifStmt = new IfStmt(conditions.get(0), preConditionFailStmt, new BlockStmt());
        IfStmt currentIfStmt = ifStmt;
        for (int i = 1; i < conditions.size(); i++) {
            IfStmt nextIfStmt = new IfStmt(conditions.get(i), preConditionFailStmt, new BlockStmt());
            currentIfStmt.setElseStmt(nextIfStmt);
            currentIfStmt = nextIfStmt;
        }
        // wrap preconditions in a try/catch block
        Type exceptionType = StaticJavaParser.parseType("java.lang.Exception");
        Parameter exceptionParameter = new Parameter(exceptionType, "e");
        CatchClause catchClause = new CatchClause(exceptionParameter, preConditionFailStmt);
        return new TryStmt()
                .setTryBlock(new BlockStmt(new NodeList<>(ifStmt)))
                .setCatchClauses(new NodeList<>(catchClause));
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
            IfStmt nextIfStmt = new IfStmt(conditions.get(i), tryStmts.get(i), new BlockStmt());
            currentIfStmt.setElseStmt(nextIfStmt);
            currentIfStmt = nextIfStmt;
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
        Statement preBlock = getPreConditions(preConditions);
        IfStmt throwsBlock = getThrowsConditions(postStmt, throwsConditions);
        NodeList<Statement> postBlock = getPostConditions(throwsBlock, postStmt, postConditions);
        oracleStatements.add(preBlock);
        oracleStatements.add(initStmt);
        oracleStatements.addAll(postBlock);
        return new NodeList<>(oracleStatements
                .stream()
                .filter(stmt -> !stmt.isEmptyStmt())
                .toList());
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
        testFile.findAll(MethodDeclaration.class).forEach(testCase -> {
            NodeList<Statement> newBody = new NodeList<>();
            NodeList<Statement> originalBody = testCase
                    .getBody()
                    .orElseThrow()
                    .getStatements();
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
     * Adds an assertion to the end of a given test prefix. This method does
     * nothing if the assertion is an empty string.
     *
     * @param testBody the body of a test case
     * @param assertion the assertion to add
     */
    private static void insertNonAxiomaticAssertion(BlockStmt testBody, String assertion) {
        if (!assertion.isEmpty()) {
            testBody
                    .getStatements()
                    .add(StaticJavaParser.parseStatement(assertion + ";"));
        }
    }

    /**
     * Wraps a test prefix with a try/catch block, where the catch block
     * expects a given exception type.
     *
     * @param testBody the body of a test case
     * @param exception the exception to catch
     */
    private static void insertNonAxiomaticException(BlockStmt testBody, String exception) {
        // Create catch block
        Parameter exceptionType = new Parameter()
                .setName("e")
                .setType(StaticJavaParser.parseType(exception));
        CatchClause catchClause = new CatchClause()
                .setParameter(exceptionType);
        List<TryStmt> tryStmts = testBody.findAll(TryStmt.class);

        if (tryStmts.size() > 1) {
            throw new IllegalStateException("Unexpected multiple try/catch within test prefix.");
        }

        TryStmt tryStmt = tryStmts.isEmpty() ? new TryStmt() : tryStmts.get(0);

        if (tryStmts.isEmpty()) {
            ExpressionStmt failStatement = StaticJavaParser.parseStatement("fail();").asExpressionStmt();
            tryStmt.setTryBlock(testBody.addStatement(failStatement));
        }

        tryStmt
                .getCatchClauses()
                .add(catchClause);
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
    private static List<OracleOutput> getOraclesWithTestName(String testName, List<OracleOutput> oracles) {
        List<OracleOutput> testOracles = oracles
                .stream()
                .filter(o -> o.testName().equals(testName) && !o.oracle().isEmpty())
                .toList();
        return testOracles;
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
        testFile.findAll(MethodDeclaration.class)
                .forEach(testCase -> {
                    String testName = testCase.getNameAsString();
                    List<OracleOutput> testOracles = getOraclesWithTestName(testName, oracles);
                    BlockStmt testBody = new BlockStmt();
                    int stmtIdx = 0;
                    for (Statement testStatement : testCase.getBody().orElseThrow().getStatements()) {
                        final int currentStmtIdx = stmtIdx;
                        testBody.addStatement(testStatement);
                        List<OracleOutput> stmtOracles = testOracles
                                .stream()
                                .filter(o -> Integer.parseInt(o.statementIndex()) == currentStmtIdx)
                                .toList();
                        List<OracleOutput> exceptionOracles = stmtOracles
                                .stream()
                                .filter(o -> o.isExceptional())
                                .toList();
                        List<OracleOutput> assertionOracles = stmtOracles
                                .stream()
                                .filter(o -> !o.isExceptional())
                                .toList();
                        for (OracleOutput exceptionOracle : exceptionOracles) {
                            insertNonAxiomaticException(testBody, exceptionOracle.exception());
                        }
                        for (OracleOutput assertionOracle : assertionOracles) {
                            insertNonAxiomaticAssertion(testBody, assertionOracle.oracle());
                        }
                        stmtIdx++;
                    }
                    testCase.setBody(testBody);
                });
    }

    /**
     * Recursively gets all OracleOutputs from all JSON files in a given
     * directory and its subdirectories.
     *
     * @param pathToOracles a directory containing OracleOutput JSON files
     * @return the corresponding OracleOutput objects
     */
    private static List<OracleOutput> getOracleOutputs(Path pathToOracles) {
        List<OracleOutput> oracleOutputs = new ArrayList<>();
        try (Stream<Path> walk = Files.walk(pathToOracles)) {
            walk.forEach(p -> {
                if (p.toString().endsWith("_Oracle.json")) {
                    oracleOutputs.addAll(FileUtils.readJSONList(p, OracleOutput.class));
                }
            });
        } catch (IOException e) {
            throw new Error("Unable to traverse directory " + pathToOracles);
        }
        return oracleOutputs;
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
        Matcher matcher = oracleTogPattern.matcher(pathToOracles.toString());
        if (!matcher.find()) {
            throw new Error(
                    "Poorly formatted path " + pathToOracles + ". " +
                    "Path should include [tog]-oracles"
            );
        }
        String togTestSegment = matcher.group(0);
        TogType tog = TogType.valueOf(togTestSegment.substring(0, togTestSegment.indexOf("-oracles")).toUpperCase());
        if (matcher.find()) {
            throw new Error(
                    "Poorly formatted path " + pathToOracles + ". " +
                    "Path should not include more than one instance of [tog]-oracles"
            );
        }
        return tog;
    }

    /**
     * Sets the SymbolSolver used by the StaticJavaParser for a given
     * project. This method should be called before attempting to resolve any
     * types from the project under analysis
     *
     * @param srcDir the project source directory
     * @param classpath a classpath
     */
    public static void setJavaParser(Path srcDir, String classpath) {
        CombinedTypeSolver typeSolver = new CombinedTypeSolver();
        typeSolver.add(new ReflectionTypeSolver());
        typeSolver.add(new JavaParserTypeSolver(srcDir));
        String[] classpathElements = classpath.split(":");
        for (String classPathElement : classpathElements) {
            if (classPathElement.endsWith(".jar")) {
                try {
                    typeSolver.add(new JarTypeSolver(classPathElement));
                } catch (IOException e) {
                    throw new Error("Unable to resolve jar " + classPathElement, e);
                }
            } else {
                typeSolver.add(new JavaParserTypeSolver(classPathElement));
            }
        }
        JavaSymbolSolver symbolSolver = new JavaSymbolSolver(typeSolver);
        StaticJavaParser
                .getParserConfiguration()
                .setSymbolResolver(symbolSolver);
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
     * @param pathToSrc the path to the project source directory
     * @param classpath the classpath of the project under analysis
     */
    public static void insertOracles(
            Path pathToPrefixes,
            Path pathToOracles,
            Path pathToSrc,
            String classpath
    ) {
        setJavaParser(pathToSrc, classpath);
        List<OracleOutput> oracleOutputs = getOracleOutputs(pathToOracles);
        TogType tog = getTogFromOraclePath(pathToOracles);
        String togName = tog.toString().toLowerCase();
        Path pathToTests = FileUtils.swapParentDirectory(pathToOracles, togName + "-oracles", togName + "-tests");
        FileUtils.copy(pathToPrefixes, pathToTests);
        try (Stream<Path> walk = Files.walk(pathToTests)) {
            walk.forEach(p -> {
                if (!Files.isDirectory(p) && !FileUtils.isScaffolding(p)) {
                    CompilationUnit cu = FileUtils.getCompilationUnit(p);
                    if (tog.isAxiomatic()) {
                        insertAxiomaticOracles(cu, oracleOutputs);
                    } else {
                        insertNonAxiomaticOracles(cu, oracleOutputs);
                    }
                    FileUtils.writeString(p, cu.toString());
                }
            });
        } catch (IOException e) {
            throw new Error("Unable to traverse directory " + pathToTests);
        }
    }
}
