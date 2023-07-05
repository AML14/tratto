package star.tratto.util.javaparser;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.*;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.nodeTypes.NodeWithSimpleName;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.type.TypeParameter;
import com.github.javaparser.resolution.MethodUsage;
import com.github.javaparser.resolution.UnsolvedSymbolException;
import com.github.javaparser.resolution.declarations.ResolvedFieldDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedMethodDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedTypeParameterDeclaration;
import com.github.javaparser.resolution.types.ResolvedType;
import com.github.javaparser.symbolsolver.javaparsermodel.declarations.JavaParserMethodDeclaration;
import com.github.javaparser.symbolsolver.utils.SymbolSolverCollectionStrategy;
import org.javatuples.Pair;
import org.javatuples.Triplet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import star.tratto.data.OracleDatapoint;
import star.tratto.exceptions.JPClassNotFoundException;
import star.tratto.exceptions.PackageDeclarationNotFoundException;
import star.tratto.exceptions.ResolvedTypeNotFound;
import star.tratto.identifiers.JPCallableType;
import star.tratto.oraclegrammar.custom.Parser;
import star.tratto.util.JavaTypes;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.*;
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
    private static final Pattern METHOD_SIGNATURE = Pattern.compile("^ReflectionMethodDeclaration\\{method=((.*) )?\\S+ \\S+\\(.*\\}$|^JavassistMethodDeclaration\\{ctMethod\\=.*\\[((.*) )?\\S+ \\(.*\\).*\\]}$");
    private static final Pattern PACKAGE_CLASS = Pattern.compile("[a-zA-Z_][a-zA-Z\\d_]*(\\.[a-zA-Z_][a-zA-Z\\d_]*)*"); // e.g., "a.b.C"

    public static JavaParser getJavaParser() {
        if (javaParser == null) {
            String root = "src/main/resources/projects-packaged";
            SymbolSolverCollectionStrategy strategy = new SymbolSolverCollectionStrategy();
            strategy.collect(Paths.get(root));
            javaParser = new JavaParser();
            javaParser.getParserConfiguration().setSymbolResolver(strategy.getParserConfiguration().getSymbolResolver().get());
        }
        return javaParser;
    }

    /**
     * Gets the type of a given expression. For example:
     *  "jpClass.getMethods().get(0)" => MethodDeclaration
     *
     * @param jpClass the declaring class {@link TypeDeclaration}.
     * @param jpCallable a method {@link CallableDeclaration}.
     * @param methodArgs the arguments of the method.
     * @param expression a java expression (e.g. "jpClass.getMethods()").
     * @return the type of the expression {@link ResolvedType}.
     * @throws ResolvedTypeNotFound if the type is not found.
     */
    public static ResolvedType getResolvedTypeOfExpression(
            TypeDeclaration<?> jpClass,
            CallableDeclaration<?> jpCallable,
            List<Triplet<String, String, String>> methodArgs,
            String expression
    ) throws ResolvedTypeNotFound {
        String SYNTHETIC_METHOD_NAME = "__tratto__auxiliaryMethod";
        if (jpClass instanceof ClassOrInterfaceDeclaration) {
            BlockStmt syntheticMethodBody = jpClass.addMethod(SYNTHETIC_METHOD_NAME).getBody().get();
            // add statement per method argument.
            for (Triplet<String, String, String> methodArg : methodArgs) {
                syntheticMethodBody.addStatement(methodArg.getValue2() + " " + methodArg.getValue0() + ";");
            }
            // get method declaration.
            if (!jpCallable.getNameAsString().equals(jpClass.getNameAsString())) {
                String jpMethodType = ((MethodDeclaration) jpCallable).getType().asString();
                if (!jpMethodType.equals("void")) {
                    syntheticMethodBody.addStatement(
                            jpMethodType + " methodResultID = " + jpCallable.getNameAsString() + "(" +
                                    methodArgs
                                            .stream()
                                            .map(Triplet::getValue0)
                                            .collect(Collectors.joining(", "))
                                    + ");"
                    );
                }
                syntheticMethodBody.addStatement("var returnType = " + expression + ";");
                return jpClass.asClassOrInterfaceDeclaration()
                        .getMethodsByName(SYNTHETIC_METHOD_NAME).get(0)
                        .getBody().get()
                        .getStatements().getLast().get()
                        .asExpressionStmt().getExpression()
                        .asVariableDeclarationExpr().getVariables().get(0)
                        .getInitializer().get()
                        .calculateResolvedType();
            }
        }
        throw new ResolvedTypeNotFound(String.format(
                "ResolvedType of expression %s of class %s and method %s not found",
                expression,
                jpClass.getNameAsString(),
                jpCallable.getNameAsString()
        ));
    }

    /**
     * Given a syntactically valid Java expression, evaluates its return type, including package and class.
     * If the expression is not syntactically valid, the method will throw an exception.
     * @param expression the expression to evaluate, e.g., "methodResultID.negate().value(null).getField()".
     *                   Must conform to TrattoGrammar.
     * @param oracleDatapoint OracleDatapoint containing additional necessary information for resolving types,
     *                        e.g., class and method names and sources. NOTE: To handle Java expressions
     *                        containing the token "jdVar", the oracleDatapoint must have its "oracle" field
     *                        populated, even if it's a partial oracle (e.g., it's the oracle being currently
     *                        generated). Then, the last occurring jdVar clause is looked for and its type is
     *                        resolved, which will be used to resolve the type of the expression.
     * @return pair of strings, where the first string is the package and the second string is the class
     */
    public static Pair<String, String> getReturnTypeOfExpression(String expression, OracleDatapoint oracleDatapoint) {
        // Handle null
        if ("null".equals(expression)) {
            return JavaTypes.NULL;
        }

        // Generate synthetic method
        CompilationUnit cu = javaParser.parse(oracleDatapoint.getClassSourceCode()).getResult().get();
        String className = oracleDatapoint.getClassName();
        MethodDeclaration syntheticMethod = getSyntheticMethod(
                getClassOrInterface(cu, className),
                getMethodOrConstructorDeclaration(oracleDatapoint.getMethodSourceCode())
        );
        BlockStmt syntheticMethodBody = syntheticMethod.getBody().get();

        // If the method is not constructor and not void, add statement to save methodResultID
        MethodDeclaration method = getMethodDeclaration(oracleDatapoint.getMethodSourceCode());
        if (method != null) {
            String methodName = method.getNameAsString();
            String methodReturnType = method.getType().asString();
            if (!"void".equals(methodReturnType)) {
                syntheticMethodBody.addStatement(methodReturnType + " methodResultID = " + methodName +
                        "(" + syntheticMethod.getParameters().stream().map(Parameter::getNameAsString).collect(Collectors.joining(", ")) + ");");
            }
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

    /**
     * Given the class and method under test, inserts into the class and returns a synthetic method
     * with the same signature (in terms of type parameters and arguments) as the method under test.
     */
    private static MethodDeclaration getSyntheticMethod(TypeDeclaration<? extends TypeDeclaration<?>> classUnderTest, BodyDeclaration<?> methodUnderTest) {
        MethodDeclaration syntheticMethod = classUnderTest.addMethod(SYNTHETIC_METHOD_NAME);
        syntheticMethod.setTypeParameters(getTypeParameters(methodUnderTest));
        syntheticMethod.setParameters(getParameters(methodUnderTest));
        return syntheticMethod;
    }

    /**
     * Pass a null oracleDatapoint to create a synthetic method without any particularities. Otherwise,
     * if the oracleDatapoint is not null, the method will be created with
     * {@link #getSyntheticMethod(TypeDeclaration, BodyDeclaration)} (see its documentation).
     */
    private static MethodDeclaration getSyntheticMethod(CompilationUnit cu, OracleDatapoint oracleDatapoint) {
        if (oracleDatapoint != null) {
            return getSyntheticMethod(
                    getClassOrInterface(cu, oracleDatapoint.getClassName()),
                    getMethodOrConstructorDeclaration(oracleDatapoint.getMethodSourceCode())
            );
        } else {
            return getClassOrInterface(cu, SYNTHETIC_CLASS_NAME).addMethod(SYNTHETIC_METHOD_NAME);
        }
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
        return getClassOrInterface(cu, className)
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
     * Note: if the class is something like "{@code List<String>}", this method will return "List" as the class name.
     */
    public static Pair<String, String> getTypeFromResolvedType(ResolvedType resolvedType) {
        if (resolvedType.isReferenceType()) {
            ResolvedReferenceTypeDeclaration type = resolvedType.asReferenceType().getTypeDeclaration().get();
            return Pair.with(type.getPackageName(), type.getClassName());
        } else if (resolvedType.isArray()) {
            StringBuilder suffix = new StringBuilder("[]");
            ResolvedType arrayElement = resolvedType.asArrayType().getComponentType();
            while (arrayElement.isArray()) {
                arrayElement = arrayElement.asArrayType().getComponentType();
                suffix.append("[]");
            }
            if (arrayElement.isReferenceType()) {
                ResolvedReferenceTypeDeclaration arrayElementType = arrayElement.asReferenceType().getTypeDeclaration().get();
                return Pair.with(arrayElementType.getPackageName(), arrayElementType.getClassName() + suffix);
            } else {
                return Pair.with("", arrayElement.describe() + suffix);
            }
        } else {
            return Pair.with("", resolvedType.describe()); // Primitive type
        }
    }

    /**
     * A resolved type may be void, primitive, an array of primitives or a reference type (including
     * package and class). It the type is a reference type, this method returns the fully qualified
     * type without packages. A fully qualified type may contain more than one package, for example:
     * {@code java.util.Comparator<java.util.Map.Entry<K, V>>}
     * For such example, this method would return the following:
     * {@code Comparator<Map.Entry<K, V>>}
     * @param resolvedType JavaParser ResolvedType (usually obtained when using Java Symbol Solver)
     * @return string representation of the type without packages
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

    public static String getTypeWithoutPackages(String type) {
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
     * @param type fully qualified type, e.g., "java.util.List"
     * @throws UnsolvedSymbolException if the type cannot be resolved
     * @throws UnsupportedOperationException if the type is an array or a primitive type
     */
    public static ResolvedReferenceTypeDeclaration getResolvedReferenceTypeDeclaration(String type) throws UnsolvedSymbolException, UnsupportedOperationException {
        return getResolvedType(type).asReferenceType().getTypeDeclaration().get();
    }

    private static ResolvedReferenceTypeDeclaration getResolvedReferenceTypeDeclaration(ResolvedType resolvedType) throws UnsupportedOperationException {
        return resolvedType.asReferenceType().getTypeDeclaration().get();
    }

    /**
     * @throws UnsupportedOperationException if the type is an array or a primitive type
     */
    private static ResolvedType getResolvedType(String type) throws UnsupportedOperationException {
        CompilationUnit cu = javaParser.parse(SYNTHETIC_CLASS_SOURCE).getResult().get();
        BlockStmt syntheticMethodBody = getClassOrInterface(cu, SYNTHETIC_CLASS_NAME).addMethod(SYNTHETIC_METHOD_NAME).getBody().get();
        syntheticMethodBody.addStatement(type + " type1Var;");
        return getClassOrInterface(cu, SYNTHETIC_CLASS_NAME)
                .getMethodsByName(SYNTHETIC_METHOD_NAME).get(0)
                .getBody().get()
                .getStatements().getLast().get()
                .asExpressionStmt().getExpression()
                .asVariableDeclarationExpr().getVariables().get(0)
                .resolve().getType();
    }

    /**
     * Given a fully qualified class name, returns all methods that can be called on top of that type.
     * If the type is a regular reference type that can be resolved, retrieves applicable methods. If
     * the type is a reference type but cannot be resolved (e.g., a generic type), retrieves methods
     * from java.lang.Object. If the type is an array, retrieves methods from java.lang.Object. If the
     * type is a primitive, throws an IllegalArgumentException.
     * @throws IllegalArgumentException if the type is not a reference type or an array
     */
    public static Set<MethodUsage> getMethodsOfType(String type) throws IllegalArgumentException {
        ResolvedType resolvedType = null;
        ResolvedReferenceTypeDeclaration resolvedReferenceTypeDeclaration = null;
        Set<MethodUsage> methods = new HashSet<>();
        boolean useObjectMethods = true;
        try {
            resolvedType = getResolvedType(type);
            resolvedReferenceTypeDeclaration = getResolvedReferenceTypeDeclaration(resolvedType);
            methods.addAll(resolvedReferenceTypeDeclaration.getAllMethods());
            if (!resolvedReferenceTypeDeclaration.isInterface()) { // Interfaces do not always inherit from Object
                useObjectMethods = false;
            }
        } catch (UnsupportedOperationException e) {
            if (!resolvedType.isArray()) {
                throw new IllegalArgumentException("Trying to retrieve available methods from a type that is not " +
                        "a reference type or an array: " + type, e);
            }
        } catch (UnsolvedSymbolException e) {
            logger.warn("Unresolvable type: {}", type);
        } finally {
            if (useObjectMethods) {
                Set<MethodUsage> objectMethods = getResolvedReferenceTypeDeclaration("java.lang.Object").getAllMethods();
                objectMethods.forEach(om -> {
                    if (methods.stream().noneMatch(m -> m.getName().equals(om.getName()) && m.getParamTypes().equals(om.getParamTypes()))) {
                        methods.add(om);
                    }
                });
            }
        }
        return methods;
    }

    /**
     * @param type1 fully qualified type, e.g., "java.util.List"
     * @param type2 fully qualified type, e.g., "java.lang.Object"
     * @param oracleDatapoint may be null. If not null, it is used to check if some type is generic.
     * @return true if type1 is an instance of type2, false otherwise
     */
    public static boolean isType1InstanceOfType2(String type1, String type2, OracleDatapoint oracleDatapoint) {
        return isType1InstanceOfType2(type1, type2, oracleDatapoint, true);
    }

    /**
     * Compared to {@link #isType1InstanceOfType2(String, String, OracleDatapoint)}, this method returns
     * true if type1 and type2 can be compared using the instanceof operator. To better understand this
     * difference, consider the following use cases:
     * <ul>
     *     <li>{@code isType1InstanceOfType2("String", "Object", null)} returns {@code true}</li>
     *     <li>{@code isType1InstanceOfType2("Object", "String", null)} returns {@code false}</li>
     *     <li>{@code canType1BeInstanceOfType2("String", "Object", null)} returns {@code true}</li>
     *     <li>{@code canType1BeInstanceOfType2("Object", "String", null)} returns {@code true}</li>
     * </ul>
     * In other words, this method returns true if the expression "var1 instanceof type2" would compile,
     * where var1 is a variable of type1.
     */
    public static boolean canType1BeInstanceOfType2(String type1, String type2, OracleDatapoint oracleDatapoint) {
        ResolvedType resolvedType2 = tryToGetResolvedType(type2);
        if (resolvedType2 == null) {
            return false; // Either type2 is generic, or an unknown class. In both cases, type1 cannot be instanceof it
        }
        try {
            List<String> type2TypeParameters = resolvedType2.asReferenceType().getTypeDeclaration().get().getTypeParameters()
                    .stream().map(ResolvedTypeParameterDeclaration::getName).collect(Collectors.toList());
            if (type2TypeParameters.contains(type1)) {
                return true; // Special case: type1 is a generic and a type parameter of type2
            }
        } catch (UnsupportedOperationException|NoSuchElementException|NullPointerException ignored) {}
        return isType1InstanceOfType2(type1, type2, oracleDatapoint, false) || isType1InstanceOfType2(type2, type1, null, false);
    }

    /**
     * Auxiliary method used both by {@link #isType1InstanceOfType2(String, String, OracleDatapoint)}
     * and {@link #canType1BeInstanceOfType2}.
     * @param checkEquality if true, returns true if type1 is equal to type2. If false, this check is
     *                      not performed at all. Must be true if checking if type1 IS instanceof type2.
     *                      Must be false if checking if type1 CAN BE instanceof type2. This is because
     *                      type1 and type2 may be generics or unresolvable classes, and in those cases
     *                      we cannot use the instanceof operator in a generated oracle, because it
     *                      would not compile.
     */
    private static boolean isType1InstanceOfType2(String type1, String type2, OracleDatapoint oracleDatapoint, boolean checkEquality) {
        // Preliminary checks
        if (JavaTypes.PRIMITIVE_TYPES.contains(type1) || JavaTypes.PRIMITIVE_TYPES.contains(type2)) {
            return false;
        }
        if (checkEquality && type1.equals(type2)) {
            return true;
        }
        ResolvedType resolvedType2 = tryToGetResolvedType(type2);
        if (resolvedType2 == null) {
            return false; // Either type2 is generic, or an unknown class. In both cases, type1 cannot be instanceof it
        }

        String classSourceCode;
        String className;
        if (oracleDatapoint != null) {
            classSourceCode = oracleDatapoint.getClassSourceCode();
            className = oracleDatapoint.getClassName();
        } else {
            classSourceCode = SYNTHETIC_CLASS_SOURCE;
            className = SYNTHETIC_CLASS_NAME;
        }

        // Parse class that contains method under test and add synthetic method to get resolved type 1 (we already have type 2)
        CompilationUnit cu = javaParser.parse(classSourceCode).getResult().get();
        BlockStmt syntheticMethodBody = getSyntheticMethod(cu, oracleDatapoint).getBody().get();
        syntheticMethodBody.addStatement(type1 + " type1Var;");

        // Get result of instanceof expression
        try {
            return resolvedType2.isAssignableBy(
                    getClassOrInterface(cu, className)
                            .getMethodsByName(SYNTHETIC_METHOD_NAME).get(0)
                            .getBody().get()
                            .getStatements().getFirst().get()
                            .asExpressionStmt().getExpression()
                            .asVariableDeclarationExpr().getVariables().get(0)
                            .resolve().getType());
        } catch (UnsolvedSymbolException e) {
            logger.warn("Failed to evaluate instanceof within method. Expression: \"type1Var instanceof {}\". Method: \n{}", type2, syntheticMethodBody.getParentNode().get());
            return false;
        }
    }

    /**
     * @return a generic "java.lang.Object" type {@link ResolvedType}.
     */
    public static ResolvedType getGenericType() {
        return javaParser.parse(SYNTHETIC_CLASS_SOURCE).getResult().get()
                .getLocalDeclarationFromClassname(SYNTHETIC_CLASS_NAME).get(0)
                .addMethod(SYNTHETIC_METHOD_NAME).getBody().get()
                .addStatement("java.lang.Object objectVar;")
                .getStatements().getLast().get()
                .asExpressionStmt().getExpression()
                .asVariableDeclarationExpr().getVariables().get(0)
                .resolve().getType();
    }

    private static ResolvedType tryToGetResolvedType(String type2) {
        ResolvedType resolvedType2;
        try {
            resolvedType2 = getResolvedType(type2);
        } catch (UnsolvedSymbolException e) {
            return null;
        }
        return resolvedType2;
    }

    /**
     * This method is different from {@link #isType1InstanceOfType2} in that it can be used to compare
     * primitive types and primitive wrapper types. For instance, a boolean is assignable to a Boolean,
     * but not the other way around. Note that this method takes as input pairs of package and class
     * name, instead of fully qualified types.
     * @param type1 pair with &lt;package, class&gt;, e.g., &lt;"java.util", "List"&gt;
     * @param type2 pair with &lt;package, class&gt;, e.g., &lt;"java.lang", "Object"&gt;
     * @param oracleDatapoint may be null. If not null, it is used to check if some type is generic.
     * @return true if a variable of type1 can be assigned to a variable of type2, false otherwise
     */
    public static boolean isType1AssignableToType2(Pair<String, String> type1, Pair<String, String> type2, OracleDatapoint oracleDatapoint) {
        // Cases to consider:
        // 1) left and right types are the same
        // 2) left type is null and right type is not primitive
        // 3) left type is primitive and right type is wrapper
        // 4) both types are numeric and left type is assignable to right type
        // 5) left type is instance of right type (both types are non-primitive)
        return
                type1.equals(type2) ||
                (type1.equals(JavaTypes.NULL) && !JavaTypes.PRIMITIVES.contains(type2)) ||
                (JavaTypes.PRIMITIVES.contains(type1) && JavaTypes.PRIMITIVES_TO_WRAPPERS.get(type1).equals(type2)) ||
                (JavaTypes.NUMBERS.contains(type1) && JavaTypes.NUMBERS.contains(type2) && isNumeric1AssignableToNumeric2(type1, type2)) ||
                isType1InstanceOfType2(fullyQualifiedClassName(type1), fullyQualifiedClassName(type2), oracleDatapoint);
    }

    public static TypeDeclaration<?> getClassOrInterface(CompilationUnit cu, String name) {
        try {
            return cu.getLocalDeclarationFromClassname(name).get(0);
        } catch (NoSuchElementException|IndexOutOfBoundsException ignored) {}
        try {
            return cu.getClassByName(name).get();
        } catch (NoSuchElementException ignored) {}
        try {
            return cu.getInterfaceByName(name).get();
        } catch (NoSuchElementException ignored) {}
        try {
            return cu.getEnumByName(name).get();
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Could not find class or interface " + name + " in compilation unit.", e);
        }
    }

    public static TypeDeclaration<?> getClassOrInterface(String classSourceCode, String name) {
        return getClassOrInterface(javaParser.parse(classSourceCode).getResult().get(), name);
    }

    /**
     * Gets the signature of a JavaParser variable declarator
     * {@link VariableDeclarator}, and return its string representation.
     *
     * @param field the JP field declaration {@link FieldDeclaration}
     * @param variable the JP variable declaration {@link VariableDeclarator}
     * @return a string representation of the signature of the JavaParser
     * variable declarator {@link VariableDeclarator}
     */
    public static String getVariableSignature(FieldDeclaration field, VariableDeclarator variable) {
        String signature = "";
        signature += field.getAccessSpecifier().asString();
        signature += field.isStatic() ? " static " : " ";
        signature += field.isFinal() ? "final " : "";
        signature += String.format("%s ", variable.getTypeAsString());
        signature += String.format("%s", variable.getNameAsString());
        signature += variable.getInitializer().isPresent() ? String.format(" = %s;", variable.getInitializer().get()) : ";";
        return signature.trim();
    }

    /**
     * Gets the signature of a JavaParser resolved field declaration
     * {@link ResolvedFieldDeclaration} and return its string representation.
     *
     * @param resolvedField resolved field declaration.
     * @return a string representation of the signature of the declaration.
     */
    public static String getFieldSignature(
            ResolvedFieldDeclaration resolvedField
    ) {
        boolean hasAccessSpecifier = !resolvedField.accessSpecifier().asString().equals("");
        boolean isStatic = resolvedField.isStatic();
        String signature = "";
        signature += hasAccessSpecifier ? resolvedField.accessSpecifier().asString() + " " : "";
        signature += isStatic ? "static" + " " : "";
        signature += getTypeWithoutPackages(resolvedField.getType().describe()) + " ";
        signature += resolvedField.getName();
        signature += ";";
        return signature.trim();
    }

    /**
     * Gets the signature of a JavaParser resolved field declaration
     * {@link ResolvedFieldDeclaration} and return its string representation.
     * Uses a given modifier value to determine the modifiers.
     *
     * @param resolvedField resolved field declaration.
     * @param modifier an integer representing the field modifiers.
     * @return a string representation of the signature of the declaration.
     */
    public static String getFieldSignature(
            ResolvedFieldDeclaration resolvedField,
            int modifier
    ) {
        String signature = "";
        signature += (modifier == 0) ? "" : (java.lang.reflect.Modifier.toString(modifier) + " ");
        signature += getTypeWithoutPackages(resolvedField.getType().describe()) + " ";
        signature += resolvedField.getName();
        signature += ";";
        return signature.trim();
    }

    /**
     * Generates the signature of a JavaParser callable declaration
     * {@link CallableDeclaration}, and returns its string representation.
     *
     * @param jpCallable The JavaParser callable declaration.
     * @param jpCallableType Type of declaration (e.g. method or constructor).
     * @return A string representation of the signature.
     */
    public static String getCallableSignature(
            CallableDeclaration<?> jpCallable,
            JPCallableType jpCallableType
    ) {
        String methodSignature = jpCallable.toString();
        Optional<BlockStmt> methodBody = jpCallableType.equals(JPCallableType.METHOD) ?
                ((MethodDeclaration) jpCallable).getBody() :
                Optional.ofNullable(((ConstructorDeclaration) jpCallable).getBody());
        if (methodBody.isPresent()) {
            methodSignature = methodSignature.replace(methodBody.get().toString(), "");
        }
        for (Node comment: jpCallable.getAllContainedComments()) {
            methodSignature = methodSignature.replace(comment.toString(), "");
        }
        if (jpCallable.getComment().isPresent()) {
            methodSignature = methodSignature.replaceAll("[\\s\\S]*\n", "");
        }
        methodSignature = methodSignature.replaceAll("/\\*\\*([\\s\\S]*?)\\*/(\\n|\\r|\\t)*", "");
        return methodSignature.trim().replaceAll(";$", "");
    }

    public static String getMethodSignature(MethodDeclaration methodDeclaration) {
        String method = methodDeclaration.toString();
        if (methodDeclaration.getBody().isPresent()) { // Remove body
            method = method.replace(methodDeclaration.getBody().get().toString(), "");
        }
        for (Node comment: methodDeclaration.getAllContainedComments()) { // Remove comments within method signature
            method = method.replace(comment.toString(), "");
        }
        if (methodDeclaration.getComment().isPresent()) { // At this point, last line is method signature. Remove everything before that
            method = method.replaceAll("[\\s\\S]*\n", "");
        }
        return method.trim().replaceAll(";$", "");
    }

    /**
     * Get all generic types of a given method.
     */
    private static List<String> getMethodUsageTypeParameters(MethodUsage methodUsage) {
        return methodUsage.getDeclaration().getTypeParameters()
                .stream()
                .map(ResolvedTypeParameterDeclaration::getName)
                .toList();
    }

    /**
     * Get all parameters in the method arguments.
     */
    private static List<String> getMethodUsageParameters(MethodUsage methodUsage) {
        ResolvedMethodDeclaration methodDeclaration = methodUsage.getDeclaration();
        // iterate through each parameter in the method declaration.
        List<String> methodParameters = new ArrayList<>();
        for (int i = 0; i < methodDeclaration.getNumberOfParams(); i++) {
            methodParameters.add(getTypeWithoutPackages(methodDeclaration.getParam(i).getType()) + " arg" + i);
        }
        return methodParameters;
    }

    /**
     * Get all exceptions that can be thrown by a given method.
     */
    private static List<String> getMethodUsageExceptions(MethodUsage methodUsage) {
        return methodUsage.getDeclaration().getSpecifiedExceptions()
                .stream()
                .map(JavaParserUtils::getTypeWithoutPackages)
                .toList();
    }

    /**
     * Unfortunately, the MethodUsage class does not provide a function to obtain the method signature
     * as it was written, so the best that we can do is to reconstruct it to a certain extent. This
     * has the following limitations:
     * <ul>
     *     <li>We lose modifiers and annotations before parameters, e.g., the "final" and "@NotNull" in
     *     the signature {@code "... addAll(@NotNull final T[] elements)"}</li>
     *     <li>We lose parameter names, which are replaced by "arg0", "arg1", etc.</li>
     * </ul>
     * We consider three types of ResolvedMethodDeclaration's: JavaParserMethodDeclaration,
     * ReflectionMethodDeclaration, and JavassistMethodDeclaration.
     */
    public static String getMethodSignature(
            MethodUsage methodUsage
    ) {
        ResolvedMethodDeclaration methodDeclaration = methodUsage.getDeclaration();
        // Consider JavaParserMethodDeclaration.
        if (methodDeclaration.toString().startsWith("JavaParserMethodDeclaration")) {
            JavaParserMethodDeclaration jpMethodDeclaration = (JavaParserMethodDeclaration) methodDeclaration;
            MethodDeclaration jpMethod = jpMethodDeclaration.getWrappedNode();
            return getMethodSignature(jpMethod);
        }
        // Consider ReflectionMethodDeclaration or JavassistMethodDeclaration
        Matcher matcher = METHOD_SIGNATURE.matcher(methodDeclaration.toString());
        if (!matcher.find()) {
            throw new IllegalStateException("Could not parse method signature: " + methodDeclaration);
        }
        String methodModifiers = methodDeclaration.toString().startsWith("ReflectionMethodDeclaration") ? matcher.group(1) : matcher.group(2);
        // Take into account the case in which the method declaration refers to a method without an access specifier.
        if (methodModifiers == null) {
            methodModifiers = "";
        }
        List<String> typeParameterList = getMethodUsageTypeParameters(methodUsage);
        List<String> parameterList = getMethodUsageParameters(methodUsage);
        List<String> exceptionList = getMethodUsageExceptions(methodUsage);
        return (methodModifiers + " " + (typeParameterList.isEmpty() ? "" : "<" + String.join(", ", typeParameterList) + ">") +
                " " + getTypeWithoutPackages(methodDeclaration.getReturnType()) +
                " " + methodDeclaration.getName() +
                "(" + String.join(", ", parameterList) + ")" +
                (exceptionList.isEmpty() ? "" : " throws " + String.join(", ", exceptionList)))
                .replaceAll(" +", " ").trim();
    }

    /**
     * Gets the package of a given compilation unit.
     *
     * @param cu a compilation unit {@link CompilationUnit}.
     * @return the package of the compilation unit {@link PackageDeclaration}.
     * @throws PackageDeclarationNotFoundException if the package declaration
     * cannot be found.
     */
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

    /**
     * Returns the base element type of a resolved type {@link ResolvedType}.
     * Recursively strips all array variables. For example:
     *  Object[][] => Object
     *
     * @param resolvedType a type {@link ResolvedType}.
     * @return the base component type.
     */
    public static ResolvedType removeArray(ResolvedType resolvedType) {
        if (resolvedType.isArray()) {
            return removeArray(resolvedType.asArrayType().getComponentType());
        }
        return resolvedType;
    }

    /**
     * @param resolvedType a JavaParser resolved type {@link ResolvedType}.
     * @return true iff a given type is generic. Ignores any wrapped arrays.
     */
    public static boolean isGenericType(
            ResolvedType resolvedType
    ) {
        ResolvedType componentType = removeArray(resolvedType);
        return componentType.isTypeVariable();
    }

    /**
     * Returns true iff a given type name represents a generic type.
     *
     * @param jpTypeName the name of a type.
     * @param jpCallable a method {@link CallableDeclaration}.
     * @param jpClass the declaring class {@link TypeDeclaration}.
     * @return true iff a given type is generic. Ignores any wrapped arrays.
     */
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
                            .toList()
            );
        }
        return jpClassGenericTypes.contains(jpTypeName.replaceAll("\\[\\]", ""));
    }

    /**
     * Get all methods available to a given class (including superclasses).
     *
     * @param jpClass object class.
     * @return list of MethodUsage objects.
     * @throws JPClassNotFoundException if jpClass is not resolvable.
     */
    public static List<MethodUsage> getAllAvailableMethodUsages(
            TypeDeclaration<?> jpClass
    ) throws JPClassNotFoundException {
        try {
            return new ArrayList<>(jpClass.resolve().getAllMethods());
        } catch (UnsolvedSymbolException | IllegalArgumentException e) {
            String errMsg = String.format(
                    "Impossible to get all the methods of class %s.",
                    jpClass.getNameAsString()
            );
            logger.error(errMsg);
            throw new JPClassNotFoundException(errMsg);
        }
    }

    /**
     * Get all fields available to a given class (including superclasses).
     *
     * @param jpClass object class.
     * @return list of ResolvedFieldDeclaration objects.
     * @throws JPClassNotFoundException if jpClass is not resolvable.
     */
    public static List<ResolvedFieldDeclaration> getAllAvailableResolvedFields(
            TypeDeclaration<?> jpClass
    ) throws JPClassNotFoundException {
        try {
            return jpClass.resolve().getAllFields();
        } catch (UnsolvedSymbolException | IllegalArgumentException e) {
            String errMsg = String.format(
                    "Impossible to get all the methods of class %s.",
                    jpClass.getNameAsString()
            );
            logger.error(errMsg);
            throw new JPClassNotFoundException(errMsg);
        }
    }

    /**
     * Get corresponding JavaParser compilation unit {@link CompilationUnit}
     * from the given file path {@link String}.
     *
     * @param filePath the absolute path to the file
     * @return an optional JavaParser compilation unit {@link CompilationUnit}
     */
    public static Optional<CompilationUnit> getCompilationUnitFromFilePath(String filePath) {
        File file = new File(filePath);
        try {
            return javaParser.parse(file).getResult();
        } catch (FileNotFoundException e) {
            return Optional.empty();
        }
    }

    private static boolean isNonPrivateNonVoidMethod(MethodUsage methodUsage) {
        return !methodUsage.getDeclaration().getReturnType().isVoid() && !methodUsage.getDeclaration().accessSpecifier().equals(AccessSpecifier.PRIVATE);
    }

    public static boolean isNonPrivateNonStaticAttribute(ResolvedFieldDeclaration fieldDeclaration) {
        return !fieldDeclaration.accessSpecifier().equals(AccessSpecifier.PRIVATE) && !fieldDeclaration.isStatic();
    }

    public static boolean isStaticNonPrivateNonVoidMethod(MethodUsage methodUsage) {
        return methodUsage.getDeclaration().isStatic() && isNonPrivateNonVoidMethod(methodUsage);
    }

    public static boolean isNonPrivateNonStaticNonVoidMethod(MethodUsage methodUsage) {
        return !methodUsage.getDeclaration().isStatic() && isNonPrivateNonVoidMethod(methodUsage);
    }

    /**
     * Unfortunately, JavaParser does not allow to parse constructors as method declarations. Since we don't
     * need the constructor declaration for anything, if the passed methodSourceCode is from a constructor,
     * this method returns null. This behavior must be handled by the caller.
     * @return null if the passed method source code is actually a constructor
     * @throws IllegalArgumentException if the provided methodSourceCode cannot be parsed by JavaParser
     */
    public static MethodDeclaration getMethodDeclaration(String methodSourceCode) throws IllegalArgumentException {
        try {
            return javaParser.parseBodyDeclaration(methodSourceCode).getResult().get().asMethodDeclaration();
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("The provided methodSourceCode cannot be parsed by JavaParser. Method source code:\n\n" + methodSourceCode, e);
        } catch (IllegalStateException e) {
            return null; // This happens when the methodSourceCode is actually a constructor
        }
    }

    private static BodyDeclaration<?> getMethodOrConstructorDeclaration(String methodSourceCode) {
        BodyDeclaration<?> bodyDeclaration = javaParser.parseBodyDeclaration(methodSourceCode).getResult().get();
        if (bodyDeclaration.isMethodDeclaration() || bodyDeclaration.isConstructorDeclaration()) {
            return bodyDeclaration;
        } else {
            throw new IllegalArgumentException("The provided methodSourceCode is neither a constructor nor a method. Method source code:\n\n" + methodSourceCode);
        }
    }

    private static NodeList<TypeParameter> getTypeParameters(BodyDeclaration<?> bodyDeclaration) {
        if (bodyDeclaration.isMethodDeclaration()) {
            return bodyDeclaration.asMethodDeclaration().getTypeParameters();
        } else if (bodyDeclaration.isConstructorDeclaration()) {
            return bodyDeclaration.asConstructorDeclaration().getTypeParameters();
        } else {
            throw new IllegalArgumentException("The provided bodyDeclaration is neither a constructor nor a method. Body declaration:\n\n" + bodyDeclaration);
        }
    }

    private static NodeList<Parameter> getParameters(BodyDeclaration<?> bodyDeclaration) {
        if (bodyDeclaration.isMethodDeclaration()) {
            return bodyDeclaration.asMethodDeclaration().getParameters();
        } else if (bodyDeclaration.isConstructorDeclaration()) {
            return bodyDeclaration.asConstructorDeclaration().getParameters();
        } else {
            throw new IllegalArgumentException("The provided bodyDeclaration is neither a constructor nor a method. Body declaration:\n\n" + bodyDeclaration);
        }
    }
}