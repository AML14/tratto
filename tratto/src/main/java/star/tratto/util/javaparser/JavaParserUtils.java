package star.tratto.util.javaparser;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.nodeTypes.NodeWithSimpleName;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.resolution.MethodUsage;
import com.github.javaparser.resolution.UnsolvedSymbolException;
import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedValueDeclaration;
import com.github.javaparser.resolution.types.ResolvedType;
import com.github.javaparser.symbolsolver.utils.SymbolSolverCollectionStrategy;
import org.javatuples.Pair;
import org.javatuples.Triplet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import star.tratto.dataset.oracles.OracleDatapoint;
import star.tratto.exceptions.PackageDeclarationNotFoundException;
import star.tratto.oraclegrammar.custom.Parser;
import star.tratto.util.JavaTypes;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static star.tratto.util.JavaTypes.isNumeric1AssignableToNumeric2;
import static star.tratto.util.StringUtils.fullyQualifiedClassName;

public class JavaParserUtils {
    private static final Logger logger = LoggerFactory.getLogger(JavaParserUtils.class);
    private static JavaParser javaParser = getJavaParser();
    private static final Parser parser = Parser.getInstance();
    private static final String SYNTHETIC_CLASS_NAME = "Tratto__AuxiliaryClass";
    private static final String SYNTHETIC_CLASS_SOURCE = "public class " + SYNTHETIC_CLASS_NAME + " {}";
    private static final String SYNTHETIC_METHOD_NAME = "__tratto__auxiliaryMethod";
    private static final Pattern METHOD_SIGNATURE = Pattern.compile("^ReflectionMethodDeclaration\\{method=(.*) \\S+ \\S+\\(.*\\}$");
    private static final Pattern PACKAGE_CLASS = Pattern.compile("[a-zA-Z_][a-zA-Z\\d_]*(\\.[a-zA-Z_][a-zA-Z\\d_]*)*"); // e.g., "a.b.C"

    public static JavaParser getJavaParser() {
        if (javaParser == null) {
            String root = "src/main/resources/projects-sources";
            SymbolSolverCollectionStrategy strategy = new SymbolSolverCollectionStrategy();
            strategy.collect(Paths.get(root));
            javaParser = new JavaParser();
            javaParser.getParserConfiguration().setSymbolResolver(strategy.getParserConfiguration().getSymbolResolver().get());
        }
        return javaParser;
    }

    /**
     * Given a syntactically valid Java expression, evaluates its return type, including package and class.
     * If the expression is not syntactically valid, the method will throw an exception.
     * @param expression The expression to evaluate, e.g., "methodResultID.negate().value(null).getField()".
     *                   Must conform to TrattoGrammar.
     * @param oracleDatapoint OracleDatapoint containing additional necessary information for resolving types,
     *                        e.g., class and method names and sources. NOTE: To handle Java expressions
     *                        containing the token "jdVar", the oracleDatapoint must have its "oracle" field
     *                        populated, even if it's a partial oracle (e.g., it's the oracle being currently
     *                        generated). Then, the last occurring jdVar clause is looked for and its type is
     *                        resolved, which will be used to resolve the type of the expression.
     * @return Pair of strings, where the first string is the package and the second string is the class.
     */
    public static Pair<String, String> getReturnTypeOfExpression(String expression, OracleDatapoint oracleDatapoint) {
        // Handle null
        if ("null".equals(expression)) {
            return JavaTypes.NULL;
        }

        // Save information from method under test, extracted from oracleDatapoint
        String className = oracleDatapoint.getClassName();
        MethodDeclaration method = javaParser.parseMethodDeclaration(oracleDatapoint.getMethodSourceCode()).getResult().get();
        String methodName = method.getNameAsString();
        String methodReturnType = method.getType().asString();
        List<Triplet<String, String, String>> methodArguments = oracleDatapoint.getTokensMethodArguments(); // <name, package, class>

        // Parse class that contains method under test and add synthetic method
        CompilationUnit cu = javaParser.parse(oracleDatapoint.getClassSourceCode()).getResult().get();
        BlockStmt syntheticMethodBody = cu.getLocalDeclarationFromClassname(className).get(0).addMethod(SYNTHETIC_METHOD_NAME).getBody().get();

        // Add one statement per method argument
        for (Triplet<String, String, String> methodArgument : methodArguments) {
            syntheticMethodBody.addStatement(methodArgument.getValue2() + " " + methodArgument.getValue0() + ";");
        }

        // If the method is not void, add statement to save methodResultID
        if (!"void".equals(methodReturnType)) {
            syntheticMethodBody.addStatement(methodReturnType + " methodResultID = " + methodName +
                    "(" + methodArguments.stream().map(Triplet::getValue0).collect(Collectors.joining(", ")) + ");");
        }

        // Handle jdVar if necessary
        handleJdVarIfNecessary(syntheticMethodBody, expression, oracleDatapoint);

        // Add last statement where the expression will be evaluated
        syntheticMethodBody.addStatement("var returnType = " + expression + ";");

        // Get return type of expression
        ResolvedType returnType;
        try {
            returnType = getReturnTypeOfLastStatementInSyntheticMethod(cu, className);
        } catch (UnsolvedSymbolException e) { // The resolution may fail if imports are missing, try to fix it by adding them
            addImports(cu, expression, oracleDatapoint);
            returnType = getReturnTypeOfLastStatementInSyntheticMethod(cu, className);
        }

        return getTypeFromResolvedType(returnType);
    }

    private static void handleJdVarIfNecessary(BlockStmt syntheticMethodBody, String expression, OracleDatapoint oracleDatapoint) {
        if (!expression.contains("jdVar")) {
            return;
        }
        String jdVarArrayElement = parser.getLastJdVarArrayElement(oracleDatapoint.getOracle());
        if (jdVarArrayElement == null) { // Should never happen, but just in case
            throw new IllegalStateException("Could not find a jdVar clause in the oracle, but the expression contains jdVar. " +
                    "Expression: " + expression + ". Oracle: " + oracleDatapoint.getOracle());
        }
        syntheticMethodBody.addStatement("var jdVar = " + jdVarArrayElement + ";");
    }

    private static ResolvedType getReturnTypeOfLastStatementInSyntheticMethod(CompilationUnit cu, String className) {
        return cu.getLocalDeclarationFromClassname(className).get(0)
                .getMethodsByName(SYNTHETIC_METHOD_NAME).get(0)
                .getBody().get()
                .getStatements().getLast().get()
                .asExpressionStmt().getExpression()
                .asVariableDeclarationExpr().getVariables().get(0)
                .getInitializer().get()
                .calculateResolvedType();
    }

    private static void addImports(CompilationUnit cu, String expression, OracleDatapoint oracleDatapoint) {
        oracleDatapoint.getTokensProjectClasses().forEach(projectClass -> {
            if (expression.contains(projectClass.getValue0())) {
                cu.addImport(fullyQualifiedClassName(projectClass.getValue1(), projectClass.getValue0()));
            }
        });
        if (expression.contains("Arrays")) {
            cu.addImport("java.util.Arrays");
        }
    }

    /**
     * Note: if the class is something like "List&lt;String&gt;", this method will return "List" as the class name.
     */
    public static Pair<String, String> getTypeFromResolvedType(ResolvedType resolvedType) {
        if (resolvedType.isReferenceType()) {
            ResolvedReferenceTypeDeclaration type = resolvedType.asReferenceType().getTypeDeclaration().get();
            return Pair.with(type.getPackageName(), type.getClassName());
        } else {
            return Pair.with("", resolvedType.describe()); // Primitive type
        }
    }

    /**
     * A resolved type may be void, primitive, an array of primitives or a reference type (including
     * package and class). It the type is a reference type, this method returns the fully qualified
     * type without packages. A fully qualified type may contain more than one package, for example:
     * <code>java.util.Comparator&lt;java.util.Map.Entry&lt;K, V&gt;&gt;</code>
     * For such example, this method would return the following:
     * <code>Comparator&lt;Map.Entry&lt;K, V&gt;&gt;</code>
     * @param resolvedType JavaParser ResolvedType (usually obtained when using Java Symbol Solver).
     * @return String representation of the type without packages.
     */
    public static String getTypeWithoutPackages(ResolvedType resolvedType) {
        String type = resolvedType.describe();
        if (resolvedType.isReferenceType()) {
            type = getTypeWithoutPackages(type);
        } else if (resolvedType.isArray()) {
            ResolvedType arrayElement = resolvedType.asArrayType().getComponentType();
            while (arrayElement.isArray()) {
                arrayElement = arrayElement.asArrayType().getComponentType();
            }
            if (arrayElement.isReferenceType()) {
                type = getTypeWithoutPackages(type);
            }
        }
        return type;
    }

    private static String getTypeWithoutPackages(String type) {
        Matcher matcher = PACKAGE_CLASS.matcher(type);
        while (matcher.find()) {
            if (matcher.group().contains(".")) {
                type = type.replaceAll(matcher.group(), getResolvedReferenceTypeDeclaration(matcher.group()).getClassName());
            }
        }
        return type;
    }

    /**
     * Given a fully qualified class name, returns the corresponding ResolvedReferenceTypeDeclaration.
     * This is useful to perform other operations on top of the returned object, such as getting all
     * methods and fields.
     * @param type Fully qualified type, e.g., "java.util.List"
     */
    public static ResolvedReferenceTypeDeclaration getResolvedReferenceTypeDeclaration(String type) {
        CompilationUnit cu = javaParser.parse(SYNTHETIC_CLASS_SOURCE).getResult().get();
        BlockStmt syntheticMethodBody = cu.getLocalDeclarationFromClassname(SYNTHETIC_CLASS_NAME).get(0).addMethod(SYNTHETIC_METHOD_NAME).getBody().get();
        syntheticMethodBody.addStatement(type + " type1Var;");
        return getResolvedType(cu
                .getLocalDeclarationFromClassname(SYNTHETIC_CLASS_NAME).get(0)
                .getMethodsByName(SYNTHETIC_METHOD_NAME).get(0)
                .getBody().get()
                .getStatements().getLast().get()
                .asExpressionStmt().getExpression()
                .asVariableDeclarationExpr().getVariables().get(0)
                .resolve()).asReferenceType().getTypeDeclaration().get();
    }

    /**
     * @param type1 Fully qualified type, e.g., "java.util.List"
     * @param type2 Fully qualified type, e.g., "java.lang.Object"
     * @return true if type1 is an instance of type2, false otherwise.
     */
    public static boolean isType1InstanceOfType2(String type1, String type2) {
        // If type1 or type2 is primitive, the instanceof operator cannot be used, so return false
        if (JavaTypes.PRIMITIVE_TYPES.contains(type1) || JavaTypes.PRIMITIVE_TYPES.contains(type2)) {
            return false;
        }

        // Parse class that contains method under test and add synthetic method that will contain the instanceof expression
        CompilationUnit cu = javaParser.parse(SYNTHETIC_CLASS_SOURCE).getResult().get();
        BlockStmt syntheticMethodBody = cu.getLocalDeclarationFromClassname(SYNTHETIC_CLASS_NAME).get(0).addMethod(SYNTHETIC_METHOD_NAME).getBody().get();
        syntheticMethodBody.addStatement(type1 + " type1Var;");
        syntheticMethodBody.addStatement(type2 + " type2Var;");

        // Get result of instanceof expression
        return getResolvedType(cu
                .getLocalDeclarationFromClassname(SYNTHETIC_CLASS_NAME).get(0)
                .getMethodsByName(SYNTHETIC_METHOD_NAME).get(0)
                .getBody().get()
                .getStatements().getLast().get()
                .asExpressionStmt().getExpression()
                .asVariableDeclarationExpr().getVariables().get(0)
                .resolve()).isAssignableBy(getResolvedType(cu
                        .getLocalDeclarationFromClassname(SYNTHETIC_CLASS_NAME).get(0)
                        .getMethodsByName(SYNTHETIC_METHOD_NAME).get(0)
                        .getBody().get()
                        .getStatements().getFirst().get()
                        .asExpressionStmt().getExpression()
                        .asVariableDeclarationExpr().getVariables().get(0)
                        .resolve()));
    }

    /**
     * This function is needed to handle cases where the type of a variable cannot be resolved. This
     * should never happen, but it WILL happen for variables using generics (T, E, etc.). In those cases,
     * we will return "java.lang.Object" as the type.
     */
    private static ResolvedType getResolvedType(ResolvedValueDeclaration resolvedValueDeclaration) {
        ResolvedType resolvedType;
        try {
            resolvedType = resolvedValueDeclaration.getType();
        } catch (UnsolvedSymbolException e) {
            logger.warn("Unresolvable type for variable {}", resolvedValueDeclaration);
            resolvedType = javaParser.parse(SYNTHETIC_CLASS_SOURCE).getResult().get()
                    .getLocalDeclarationFromClassname(SYNTHETIC_CLASS_NAME).get(0)
                    .addMethod(SYNTHETIC_METHOD_NAME).getBody().get()
                    .addStatement("java.lang.Object objectVar;")
                    .getStatements().getLast().get()
                    .asExpressionStmt().getExpression()
                    .asVariableDeclarationExpr().getVariables().get(0)
                    .resolve().getType();
        }
        return resolvedType;
    }

    /**
     * This method is different from {@link #isType1InstanceOfType2} in that it can be used to compare
     * primitive types and primitive wrapper types. For instance, a boolean is assignable to a Boolean,
     * but not the other way around. Note that this method takes as input pairs of package and class
     * name, instead of fully qualified types.
     * @param type1 Pair with &lt;package, class&gt;, e.g., "&lt;java.util, List&gt;"
     * @param type2 Pair with &lt;package, class&gt;, e.g., "&lt;java.lang, Object&gt;"
     * @return true if a variable of type1 can be assigned to a variable of type2, false otherwise.
     */
    public static boolean isType1AssignableToType2(Pair<String, String> type1, Pair<String, String> type2) {
        // Cases to consider:
        // 1) left and right types are the same
        // 2) left type is null and right type is not primitive
        // 3) left type is primitive and right type is wrapper
        // 4) both types are numeric and left type is assignable to right type
        // 5) left type is instance of right type (both types are non-primitive)
        return type1.equals(type2) ||
                (type1.equals(JavaTypes.NULL) && !JavaTypes.PRIMITIVES.contains(type2)) ||
                (JavaTypes.PRIMITIVES.contains(type1) && JavaTypes.PRIMITIVES_TO_WRAPPERS.get(type1).equals(type2)) ||
                (JavaTypes.NUMBERS.contains(type1) && JavaTypes.NUMBERS.contains(type2) && isNumeric1AssignableToNumeric2(type1, type2)) ||
                isType1InstanceOfType2(fullyQualifiedClassName(type1), fullyQualifiedClassName(type2));
    }

    /**
     * Gets the signature of a JavaParser variable declarator
     * {@link VariableDeclarator}, and return its string representation.
     *
     * @param field the JP field declaration {@link FieldDeclaration}
     * @param variable the JP variable declaration {@link VariableDeclarator}
     * @return a string representation of the signature of the JavaParser
     * variable declarator {@link VariableDeclarator}.
     */
    public static String getVariableSignature(FieldDeclaration field, VariableDeclarator variable) {
        String signature = "";
        signature += field.getAccessSpecifier().asString();
        signature += field.isStatic() ? " static " : " ";
        signature += field.isFinal() ? " final " : "";
        signature += String.format("%s ", variable.getTypeAsString());
        signature += String.format("%s", variable.getNameAsString());
        signature += variable.getInitializer().isPresent() ? String.format(" = %s;", variable.getInitializer().get()) : ";";
        return signature;
    }

    /**
     * The JavaParser method getDeclarationAsString() may return leading or trailing spaces, this method
     * fixes that.
     */
    public static String getCallableSignature(CallableDeclaration<?> callableDeclaration) {
        return callableDeclaration.getDeclarationAsString().trim();
    }

    /**
     * Unfortunately, the MethodUsage class does not provide a function to obtain the method signature
     * as it was written, so the best that we can do is to reconstruct it to a certain extent. This
     * has the following limitations:
     * <ul>
     *     <li>We lose generics info that goes before the return type, e.g., the "&lt;T&gt;" in the
     *     signature <code>"public static &lt;T&gt; boolean addAll ..."</code></li>
     *     <li>We lose modifiers and annotations before parameters, e.g., the "final" and "@NotNull" in
     *     the signature <code>"... addAll(@NotNull final T[] elements)"</code></li>
     *     <li>We lose parameter names, which are replaced by "arg0", "arg1", etc.</li>
     * </ul>
     */
    public static String getCallableSignature(MethodUsage methodUsage) {
        Matcher matcher = METHOD_SIGNATURE.matcher(methodUsage.getDeclaration().toString());
        if (!matcher.find()) {
            throw new IllegalStateException("Could not parse method signature: " + methodUsage.getDeclaration().toString());
        }
        String methodModifiers = matcher.group(1); // Previous regex is designed to match JUST the method modifiers in group 1
        String methodReturnType = getTypeWithoutPackages(methodUsage.returnType());
        String methodName = methodUsage.getName();
        List<String> methodParameters = new ArrayList<>();
        for (int i=0; i < methodUsage.getNoParams(); i++) {
            methodParameters.add(getTypeWithoutPackages(methodUsage.getParamType(i)) + " arg" + i);
        }
        List<String> methodExceptions = new ArrayList<>();
        for (ResolvedType exceptionType : methodUsage.exceptionTypes()) {
            methodExceptions.add(getTypeWithoutPackages(exceptionType));
        }

        return methodModifiers + " " + methodReturnType + " " + methodName + "(" + String.join(", ", methodParameters) + ")" +
                (methodExceptions.isEmpty() ? "" : " throws " + String.join(", ", methodExceptions));
    }

    public static PackageDeclaration getPackageDeclarationFromCompilationUnit(
            CompilationUnit cu
    ) throws PackageDeclarationNotFoundException {
        Optional<PackageDeclaration> jpPackage = cu.getPackageDeclaration();
        if (jpPackage.isEmpty()) {
            throw new PackageDeclarationNotFoundException(
                    "The Java Parser package declaration of the compilation unit is empty"
            );
        }
        return jpPackage.get();
    }

    public static boolean isGenericType(
            String jpTypeName,
            CallableDeclaration<?> jpCallable,
            TypeDeclaration<?> jpClass
    ) {
        List<String> jpClassGenericTypes = jpCallable.getTypeParameters()
                .stream()
                .map(NodeWithSimpleName::getNameAsString)
                .collect(Collectors.toList());
        if (jpClass instanceof ClassOrInterfaceDeclaration) {
            jpClassGenericTypes.addAll(
                    jpClass.asClassOrInterfaceDeclaration().getTypeParameters()
                            .stream()
                            .map(NodeWithSimpleName::getNameAsString)
                            .collect(Collectors.toList())
            );
        }
        return jpClassGenericTypes.contains(jpTypeName.replaceAll("\\[\\]", ""));
    }

    /**
     * Get corresponding JavaParser compilation unit {@link CompilationUnit}
     * from the given file path {@link String}.
     *
     * @param filePath the absolute path to the file.
     * @return An optional JavaParser compilation unit {@link CompilationUnit}.
     */
    public static Optional<CompilationUnit> getCompilationUnitFromFilePath(String filePath) {
        File file = new File(filePath);
        try {
            return javaParser.parse(file).getResult();
        } catch (FileNotFoundException e) {
            return Optional.empty();
        }
    }

    public static boolean isStaticNonVoidNonPrivateMethod(MethodUsage methodUsage) {
        return methodUsage.getDeclaration().isStatic() && isNonVoidNonPrivateMethod(methodUsage);
    }

    public static boolean isNonStaticNonVoidNonPrivateMethod(MethodUsage methodUsage) {
        return !methodUsage.getDeclaration().isStatic() && isNonVoidNonPrivateMethod(methodUsage);
    }

    private static boolean isNonVoidNonPrivateMethod(MethodUsage methodUsage) {
        boolean isVoid = getTypeWithoutPackages(methodUsage.returnType()).equals("void");
        boolean isPrivate = methodUsage.getDeclaration().toString().matches(".*(method=private |method=.* private ).*");
        return !isVoid && !isPrivate;
    }
}
