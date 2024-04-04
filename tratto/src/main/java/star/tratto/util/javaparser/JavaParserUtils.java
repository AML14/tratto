package star.tratto.util.javaparser;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.ast.body.AnnotationDeclaration;
import com.github.javaparser.ast.AccessSpecifier;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.CallableDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.type.ReferenceType;
import com.github.javaparser.ast.type.TypeParameter;
import com.github.javaparser.javadoc.Javadoc;
import com.github.javaparser.javadoc.JavadocBlockTag;
import com.github.javaparser.resolution.MethodUsage;
import com.github.javaparser.resolution.TypeSolver;
import com.github.javaparser.resolution.UnsolvedSymbolException;
import com.github.javaparser.resolution.declarations.ResolvedFieldDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedMethodDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedTypeParameterDeclaration;
import com.github.javaparser.resolution.types.ResolvedType;
import com.github.javaparser.resolution.types.ResolvedWildcard;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.javaparsermodel.declarations.JavaParserMethodDeclaration;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ClassLoaderTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;
import com.github.javaparser.symbolsolver.javassistmodel.JavassistMethodDeclaration;
import com.github.javaparser.symbolsolver.reflectionmodel.ReflectionMethodDeclaration;
import com.github.javaparser.symbolsolver.utils.SymbolSolverCollectionStrategy;
import com.github.javaparser.ast.ImportDeclaration;

import org.javatuples.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import star.tratto.data.OracleDatapoint;
import star.tratto.data.JPClassNotFoundException;
import star.tratto.data.PackageDeclarationNotFoundException;
import star.tratto.oraclegrammar.custom.Parser;
import star.tratto.util.JavaTypes;

import java.io.IOException;
import java.nio.file.Path;
import java.lang.reflect.Field;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.plumelib.util.CollectionsPlume.mapList;
import static star.tratto.util.JavaTypes.isAssignableToNumeric;
import static star.tratto.util.StringUtils.containsWord;
import static star.tratto.util.StringUtils.fullyQualifiedClassName;

/**
 * This class provides static methods for JavaParser
 * utilities.
 */
public class JavaParserUtils {
    private static final Logger logger = LoggerFactory.getLogger(JavaParserUtils.class);
    private static final String ROOT = "src/main/resources/projects-packaged";
    private static JavaParser javaParser = getJavaParser();
    private static final Parser oracleParser = Parser.getInstance();
    // artificial source code used to parse arbitrary source code expressions using JavaParser
    /** Artificial class name. */
    private static final String SYNTHETIC_CLASS_NAME = "Tratto__AuxiliaryClass";
    /** Artificial class source code. */
    private static final String SYNTHETIC_CLASS_SOURCE = "public class " + SYNTHETIC_CLASS_NAME + " {}";
    /** Artificial method name. */
    private static final String SYNTHETIC_METHOD_NAME = "__tratto__auxiliaryMethod";
    /** Cache ResolvedType of Object to make subsequent accesses free. */
    private static ResolvedType objectType;
    /** Cache of Object methods to make subsequent accesses free. */
    private static Set<MethodUsage> objectMethods;

    /** Do not instantiate this class. */
    private JavaParserUtils() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    public static JavaParser getJavaParser() {
        if (javaParser == null) {
            ParserConfiguration parserConfiguration = new ParserConfiguration();
            parserConfiguration.setLanguageLevel(ParserConfiguration.LanguageLevel.JAVA_17);
            CombinedTypeSolver typeSolver = createCombinedTypeSolver(ROOT);
            parserConfiguration.setSymbolResolver(new JavaSymbolSolver(typeSolver));
            javaParser = new JavaParser(parserConfiguration);
        }
        return javaParser;
    }

    /**
     * This method is a bit cumbersome, but it is the only way I could think of to properly set up
     * the SymbolSolver for JavaParser. Given a path which may contain both source code and JAR files,
     * we need the SymbolSolver to consider all those. This can be done using the
     * {@link SymbolSolverCollectionStrategy}, which creates a {@link CombinedTypeSolver} composed of
     * multiple {@link TypeSolver}s plus one {@link ReflectionTypeSolver}. The latter is needed to
     * resolve the types of the JDK classes. The problem is that the ReflectionTypeSolver, by default,
     * includes not only JDK classes but also all the classes in the classpath. Since the
     * javaparser-symbol-solver project includes Guava as a dependency, it collides with the Guava
     * JAR that we use for generating the datasets (located at src/main/resources/projects-packaged),
     * which is a lower version.
     * <br><br>
     * The alternative is to create a ReflectionTypeSolver with the parameter {@code jreOnly} set to
     * {@code true}, and include it within the CombinedTypeSolver. To do this, we need to use
     * reflection to first extract all TypeSolvers generated by the SymbolSolverCollectionStrategy,
     * modify them, and add them to our custom CombinedTypeSolver. In addition to the ReflectionTypeSolver,
     * we also need to add a {@link ClassLoaderTypeSolver} that resolves additional classes from the
     * jdk.* packages.
     */
    private static CombinedTypeSolver createCombinedTypeSolver(String root) {
        CombinedTypeSolver combinedTypeSolver = new CombinedTypeSolver();
        combinedTypeSolver.add(new ReflectionTypeSolver(true));
        combinedTypeSolver.add(new ClassLoaderTypeSolver(ClassLoader.getPlatformClassLoader()));
        SymbolSolverCollectionStrategy strategy = new SymbolSolverCollectionStrategy();
        strategy.collect(Paths.get(root));
        try {
            Field typeSolverField = strategy.getClass().getDeclaredField("typeSolver");
            typeSolverField.setAccessible(true);
            CombinedTypeSolver strategyTypeSolver = (CombinedTypeSolver) typeSolverField.get(strategy);
            Field elementsField = strategyTypeSolver.getClass().getDeclaredField("elements");
            elementsField.setAccessible(true);
            List<TypeSolver> strategyTypeSolvers = (List<TypeSolver>) elementsField.get(strategyTypeSolver);
            strategyTypeSolvers.stream().filter(ts -> !(ts instanceof ReflectionTypeSolver)).forEach(ts -> {
                try {
                    Field parentField = ts.getClass().getDeclaredField("parent");
                    parentField.setAccessible(true);
                    parentField.set(ts, null);
                    parentField.setAccessible(false);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                combinedTypeSolver.add(ts);
            });
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return combinedTypeSolver;
    }

    public static void updateSymbolSolver(String root) {
        javaParser.getParserConfiguration().setSymbolResolver(new JavaSymbolSolver(createCombinedTypeSolver(root)));
    }

    public static void resetSymbolSolver() {
        javaParser.getParserConfiguration().setSymbolResolver(new JavaSymbolSolver(createCombinedTypeSolver(ROOT)));
    }

    /**
     * Returns a new, empty JavaParser class.
     *
     * @return an empty JavaParser class
     */
    private static TypeDeclaration<?> createNewClass() {
        return javaParser.parse(SYNTHETIC_CLASS_SOURCE).getResult().orElseThrow()
                .getLocalDeclarationFromClassname(SYNTHETIC_CLASS_NAME).get(0);
    }

    /**
     * Adds a new, empty method to a given class, and returns its body.
     *
     * @param typeDeclaration the class to add a method to
     * @return the body of the new method
     */
    private static BlockStmt createNewMethodBody(TypeDeclaration<?> typeDeclaration) {
        return typeDeclaration
                .addMethod(SYNTHETIC_METHOD_NAME)
                .getBody()
                .orElseThrow();
    }

    /**
     * Creates a "java.lang.Object" type if it has not been previously created
     * (see {@link #objectType}), otherwise returns the previously created type.
     *
     * @return a "java.lang.Object" type
     */
    public static ResolvedType getObjectType() {
        if (objectType == null) {
            TypeDeclaration<?> syntheticClass = createNewClass();
            BlockStmt syntheticMethod = createNewMethodBody(syntheticClass);
            objectType = syntheticMethod
                    .addStatement("java.lang.Object objectVar;")
                    .getStatements().getLast().orElseThrow()
                    .asExpressionStmt().getExpression()
                    .asVariableDeclarationExpr().getVariables().get(0)
                    .resolve().getType();
        }
        return objectType;
    }

    /**
     * Creates a set containing all methods of "java.lang.Object" if it has not
     * been previously created (see {@link #objectMethods}), otherwise returns
     * the previously created set.
     *
     * @return a set of "java.lang.Object" methods
     */
    public static Set<MethodUsage> getObjectMethods() {
        if (objectMethods == null) {
            objectMethods = getResolvedReferenceTypeDeclaration(getObjectType()).getAllMethods();
        }
        return objectMethods;
    }

    /**
     * @return pair of strings, where the first string is the package and the second string is the class
     */
    public static Pair<String, String> getReturnTypeOfExpression(String expression, OracleDatapoint oracleDatapoint) {
        return getTypePairFromResolvedType(getResolvedTypeOfExpression(expression, oracleDatapoint));
    }

    /**
     * Given a syntactically valid Java expression, evaluates its return type, including package and class.
     * If the expression is not syntactically valid, the method will throw an exception.
     * @param expression the expression to evaluate, e.g., "methodResultID.negate().value(null).getField()".
     *                   Must conform to TrattoGrammar
     * @param oracleDatapoint OracleDatapoint containing additional necessary information for resolving types,
     *                        e.g., class and method names and sources. NOTE: To handle Java expressions
     *                        containing the token "jdVar" (see {@link star.tratto.oraclegrammar.custom}), the
     *                        oracleDatapoint must have its "oracle" field populated, even if it's a partial
     *                        oracle (e.g., it's the oracle being currently generated). Then, the last occurring
     *                        jdVar clause is looked for and its type is resolved, which will be used to resolve
     *                        the type of the expression
     * @return resolved type of the expression
     */
    public static ResolvedType getResolvedTypeOfExpression(String expression, OracleDatapoint oracleDatapoint) {
        // Handle null
        if ("null".equals(expression)) {
            return null;
        }

        // Generate synthetic method
        CompilationUnit cu = javaParser.parse(oracleDatapoint.getClassSourceCode()).getResult().get();
        String className = oracleDatapoint.getClassName();
        BodyDeclaration<?> method = getMethodOrConstructorDeclaration(oracleDatapoint.getMethodSourceCode());
        MethodDeclaration syntheticMethod = createSyntheticMethod(
                getClassOrInterface(cu, className),
                method
        );
        BlockStmt syntheticMethodBody = syntheticMethod.getBody().get();

        String methodType = method.isMethodDeclaration() ? method.asMethodDeclaration().getType().asString() : null;
        String methodName = method.isMethodDeclaration() ? method.asMethodDeclaration().getNameAsString() : method.asConstructorDeclaration().getNameAsString();
        addMethodResultIDStatementToMethod(
                syntheticMethodBody,
                methodType,
                methodName,
                syntheticMethod.getParameters()
                        .stream()
                        .map(Parameter::getNameAsString)
                        .toList()
        );

        // Handle jdVar if necessary
        handleJdVarIfNecessary(syntheticMethodBody, expression, oracleDatapoint);

        // Add last statement where the expression will be evaluated
        syntheticMethodBody.addStatement("var returnType = " + expression + ";");

        // Get return type of expression
        try {
            return getReturnTypeOfLastStatementInSyntheticMethod(cu, className);
        } catch (UnsolvedSymbolException e) { // The resolution may fail if imports are missing, try to fix it by adding them
            addImports(cu, expression, oracleDatapoint);
            return getReturnTypeOfLastStatementInSyntheticMethod(cu, className);
        }
    }

    /**
     * Add a statement to parentMethod that saves the result of a method call to a variable.
     * @param parentMethod the method to which the statement will be added
     * @param methodReturnType the return type of the method to add. If "void", no statement will be added.
     *                         If null, the method passed is a constructor
     * @param methodName the name of the method to add
     * @param methodParameters the parameters of the method to add
     */
    private static void addMethodResultIDStatementToMethod(BlockStmt parentMethod, String methodReturnType, String methodName, List<String> methodParameters) {
        if (!"void".equals(methodReturnType)) {
            String type = methodReturnType == null ? methodName : methodReturnType;
            String methodCall = methodReturnType == null ? "new " + methodName : methodName;
            parentMethod.addStatement(type + " methodResultID = " + methodCall +
                    "(" + String.join(", ", methodParameters) + ");");
        }
    }

    /**
     * Given a method, creates a synthetic method
     * with the same signature (in terms of type parameters and arguments).
     * Inserts the synthetic method into the class and returns it.
     */
    private static MethodDeclaration createSyntheticMethod(TypeDeclaration<? extends TypeDeclaration<?>> classUnderTest, BodyDeclaration<?> methodUnderTest) {
        MethodDeclaration syntheticMethod = classUnderTest.addMethod(SYNTHETIC_METHOD_NAME);
        syntheticMethod.setTypeParameters(getTypeParameters(methodUnderTest));
        syntheticMethod.setParameters(getParameters(methodUnderTest));
        return syntheticMethod;
    }

    /**
     * Pass a null oracleDatapoint to create a synthetic method without any particularities. Otherwise,
     * if the oracleDatapoint is not null, the method will be created with
     * {@link #createSyntheticMethod(TypeDeclaration, BodyDeclaration)} (see its documentation).
     */
    private static MethodDeclaration createSyntheticMethod(CompilationUnit cu, OracleDatapoint oracleDatapoint) {
        if (oracleDatapoint != null) {
            return createSyntheticMethod(
                    getClassOrInterface(cu, oracleDatapoint.getClassName()),
                    getMethodOrConstructorDeclaration(oracleDatapoint.getMethodSourceCode())
            );
        } else {
            TypeDeclaration<?> clazz = getClassOrInterface(cu, SYNTHETIC_CLASS_NAME);
            return clazz.addMethod(SYNTHETIC_METHOD_NAME);
        }
    }

    /**
     * When evaluating the return type of an expression, such expression may contain the
     * token "jdVar" (see {@link star.tratto.oraclegrammar.custom}). If so, this method
     * adds a statement to the synthetic method where the expression is evaluated, to declare
     * a variable "jdVar" with its type.
     * @param syntheticMethodBody parent method where an expression will be evaluated and
     *                            where the jdVar clause will be added if necessary
     * @param expression the expression to evaluate, potentially containing "jdVar"
     * @param oracleDatapoint OracleDatapoint containing additional necessary information
     *                        to resolve the type of "jdVar"
     */
    private static void handleJdVarIfNecessary(BlockStmt syntheticMethodBody, String expression, OracleDatapoint oracleDatapoint) {
        if (!expression.contains("jdVar")) {
            return;
        }
        String jdVarArrayElement = oracleParser.getLastJdVarArrayElement(oracleDatapoint.getOracle());
        if (jdVarArrayElement == null) {
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
            if (containsWord(expression, projectClass.className())) {
                cu.addImport(fullyQualifiedClassName(projectClass.packageName(), projectClass.className()));
            }
        });
        if (expression.contains("\\bArrays\\.")) {
            cu.addImport("java.util.Arrays");
        }
    }

    /**
     * Note: if the class is something like "{@code List<String>}", this method will return "List" as the class name.
     */
    public static Pair<String, String> getTypePairFromResolvedType(ResolvedType resolvedType) {
        if (resolvedType == null) {
            return JavaTypes.NULL;
        }
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
        } else if (resolvedType.isWildcard()) { // e.g., in Collection<? extends String>, "? extends String" is a wildcard
            ResolvedWildcard type = resolvedType.asWildcard();
            ResolvedType boundedType = type.getBoundedType();
            if (boundedType != null) {
                if (boundedType.isReferenceType()) {
                    ResolvedReferenceTypeDeclaration resolvedBoundedType = boundedType.asReferenceType().getTypeDeclaration().get();
                    return Pair.with(resolvedBoundedType.getPackageName(), resolvedBoundedType.getClassName());
                } else {
                    return Pair.with("", boundedType.describe());
                }
            } else { // e.g., in Collection<?>, "?" can be anything, so we return Object
                return Pair.with("java.lang", "Object");
            }
        } else {
            return Pair.with("", resolvedType.describe()); // Primitive type
        }
    }

    /** Regex to match the binary name of a class (e.g. "package.submodule.InnerClass$OuterClass") */
    private static final Pattern PACKAGE_CLASS = Pattern.compile("[a-zA-Z_][a-zA-Z\\d_]*(\\.[a-zA-Z_][a-zA-Z\\d_]*)*");

    /**
     * Removes the package name from a fully qualified name of a type for
     * compatibility with the XText grammar. Also removes package from type
     * parameters.
     *
     * @param fullyQualifiedName a fully qualified name of a type
     * @return the type name without packages. Includes outer classes, e.g.,
     *     package.Outer.Inner    =>    Outer.Inner
     */
    public static String getTypeWithoutPackages(String fullyQualifiedName) {
        // regex is used instead of String.lastIndexOf to avoid removing outer classes
        Matcher matcher = PACKAGE_CLASS.matcher(fullyQualifiedName);
        // continuously remove packages until none remain
        while (matcher.find()) {
            if (matcher.group().contains(".")) {
                // gets the class name using a JavaParser ResolvedReferenceTypeDeclaration
                String classNameWithoutPackages = getResolvedReferenceTypeDeclaration(matcher.group())
                        .getClassName();
                // replaces all instances of the fully qualified name with the JavaParser type name
                fullyQualifiedName = fullyQualifiedName.replaceAll(
                        matcher.group(),
                        classNameWithoutPackages
                );
            }
        }
        return fullyQualifiedName;
    }

    /**
     * A resolved type may be void, primitive, an array, a reference type, etc.
     * (including arrays of reference types). If the type involves a reference
     * type, this method returns the fully qualified name without packages.
     *
     * @param resolvedType JavaParser resolved type
     * @return the name of a type without packages. If the resolved type is an
     * array of reference types, then this method removes the packages from
     * the fully qualified name of the element types.
     */
    public static String getTypeWithoutPackages(ResolvedType resolvedType) {
        String typeName = resolvedType.describe();
        ResolvedType elementType = getElementType(resolvedType);
        if (elementType.isReferenceType()) {
            // use the original type name to avoid removing array brackets
            return getTypeWithoutPackages(typeName);
        } else {
            return typeName;
        }
    }

    /**
     * Returns the {@link ResolvedReferenceTypeDeclaration} of a given fully
     * qualified type name.
     *
     * @param fqName fully qualified type name, e.g., {@code java.util.List}
     * @return the corresponding JavaParser ResolvedReferenceTypeDeclaration
     * @throws UnsolvedSymbolException if the type cannot be resolved
     * @throws UnsupportedOperationException if the type is an array or
     * primitive type
     */
    public static ResolvedReferenceTypeDeclaration getResolvedReferenceTypeDeclaration(String fqName) throws UnsolvedSymbolException, UnsupportedOperationException {
        return getResolvedType(fqName).asReferenceType().getTypeDeclaration().get();
    }

    /**
     * @throws UnsupportedOperationException if the type is an array or
     * primitive type
     */
    private static ResolvedReferenceTypeDeclaration getResolvedReferenceTypeDeclaration(ResolvedType resolvedType) throws UnsupportedOperationException {
        return resolvedType.asReferenceType().getTypeDeclaration().get();
    }

    // TODO: Check that this method is not called with binary names as argument
    /**
     * @param fqName fully qualified name, e.g., {@code java.util.List}
     */
    public static ResolvedType getResolvedType(String fqName) throws UnsupportedOperationException {
        CompilationUnit cu = javaParser.parse(SYNTHETIC_CLASS_SOURCE).getResult().get();
        BlockStmt syntheticMethodBody = getClassOrInterface(cu, SYNTHETIC_CLASS_NAME).addMethod(SYNTHETIC_METHOD_NAME).getBody().get();
        syntheticMethodBody.addStatement(fqName + " type1Var;");
        return getClassOrInterface(cu, SYNTHETIC_CLASS_NAME)
                .getMethodsByName(SYNTHETIC_METHOD_NAME).get(0)
                .getBody().get()
                .getStatements().getLast().get()
                .asExpressionStmt().getExpression()
                .asVariableDeclarationExpr().getVariables().get(0)
                .resolve().getType();
    }

    /**
     * Given a fully qualified class name, returns all methods that can be called on that type.
     * <ul>
     * <li>If the type is a regular reference type that can be resolved, retrieves applicable methods.
     * <li>If
     * the type is a reference type but cannot be resolved (e.g., a generic type), retrieves methods
     * from java.lang.Object.
     * <li>If the type is an array, retrieves methods from java.lang.Object.
     * <li>If the
     * type is a primitive, throws an IllegalArgumentException.
     * </ul>
     * @throws IllegalArgumentException if the type is not a reference type or an array
     */
    public static Set<MethodUsage> getMethodsOfType(String referenceType) throws IllegalArgumentException {
        ResolvedType resolvedType = null;
        ResolvedReferenceTypeDeclaration resolvedReferenceTypeDeclaration = null;
        Set<MethodUsage> methods = new HashSet<>();
        boolean useObjectMethods = true;
        try {
            resolvedType = getResolvedType(referenceType);
            if (!resolvedType.isArray() && !resolvedType.isReferenceType()) {
                throw new IllegalArgumentException("getMethodsOfType: not a reference type or an array: " + referenceType);
            } else if (resolvedType.isReferenceType()) {
                resolvedReferenceTypeDeclaration = getResolvedReferenceTypeDeclaration(resolvedType);
                methods.addAll(resolvedReferenceTypeDeclaration.getAllMethods());
                // Interfaces do not always inherit from Object
                if (!resolvedReferenceTypeDeclaration.isInterface()) {
                    useObjectMethods = false;
                }
            }
        } catch (UnsolvedSymbolException e) {
            // Happens mostly for type parameters, may also happen in corner cases for unknown classes
            logger.warn("Unresolvable type: {}", referenceType);
        }
        if (useObjectMethods) {
            // Always add object methods for arrays or types that couldn't be resolved (e.g., generics)
            getObjectMethods().forEach(om -> {
                if (methods.stream().noneMatch(m -> m.getName().equals(om.getName()) && m.getParamTypes().equals(om.getParamTypes()))) {
                    methods.add(om);
                }
            });
        }
        return methods;
    }

    /** Returns true if a variable of type1 is an instance of type2, false otherwise.
     * @param type1 fully qualified type, e.g., "java.util.List"
     * @param type2 fully qualified type, e.g., "java.lang.Object"
     * @param oracleDatapoint may be null. If not null, it is used to check if some type is generic.
     * @return true if type1 is an instance of type2, false otherwise
     */
    public static boolean isInstanceOf(String type1, String type2, OracleDatapoint oracleDatapoint) {
        return isInstanceOf(type1, type2, oracleDatapoint, true);
    }

    /**
     * Compared to {@link #isInstanceOf(String, String, OracleDatapoint)}, this method returns
     * true if type1 and type2 can be compared using the instanceof operator. To better understand this
     * difference, consider the following use cases:
     * <ul>
     *     <li>{@code isInstanceOf("String", "Object", null)} returns {@code true}</li>
     *     <li>{@code isInstanceOf("Object", "String", null)} returns {@code false}</li>
     *     <li>{@code doesInstanceofCompile("String", "Object", null)} returns {@code true}</li>
     *     <li>{@code doesInstanceofCompile("Object", "String", null)} returns {@code true}</li>
     *     <li>{@code doesInstanceofCompile("String", "Integer", null)} returns {@code false}</li>
     * </ul>
     * In other words, this method returns true if the expression {@code var1 instanceof type2} would compile,
     * where var1 is a variable of type1.
     * The Java compiler rejects {@code var1 instanceof type2} if type1 and type2 are unrelated in
     * the type hierarchy.
     */
    public static boolean doesInstanceofCompile(String type1, String type2, OracleDatapoint oracleDatapoint) {
        ResolvedType resolvedType2 = getResolvedTypeOrNull(type2);
        if (resolvedType2 == null) {
            return false; // Either type2 is generic, or an unknown class. In both cases, type1 cannot be instanceof it.
        }
        try {
            List<String> classTypeParameters = getClassOrInterface(oracleDatapoint.getClassSourceCode(), oracleDatapoint.getClassName())
                    .resolve().getTypeParameters().stream().map(ResolvedTypeParameterDeclaration::getName).toList();
            if (classTypeParameters.contains(type1)) {
                // Special case: type1 is a type parameter of the oracle datapoint class, so "type1Var instanceof type2" compiles for any type2
                // Example: The OracleDatapoint class is Equator<T>, and type1 is T. Then, this method returns true regardless of type2.
                return true;
            }
        } catch (UnsupportedOperationException|NoSuchElementException|NullPointerException ignored) {
            // Previous check is only intended for special case, it may throw these exceptions for normal cases, which are handled below
        }
        return isInstanceOf(type1, type2, oracleDatapoint, false) || isInstanceOf(type2, type1, null, false);
    }

    /**
     * This method implements the underlying logic used both to check if a variable of type1 is an
     * instance of type2 ({@link #isInstanceOf(String, String, OracleDatapoint)}), and to check if
     * type1 and type2 can be compared using the instanceof operator ({@link #doesInstanceofCompile}).
     * This is an auxiliary private method to avoid code duplication, and it should ONLY be called
     * from the two aforementioned methods. Also, depending on where it is called from, the parameter
     * checkEquality must be true or false (see its documentation).
     * @param checkEquality if true, returns true if type1 is equal to type2. If false, this check is
     *                      not performed at all. Must be true if checking {@code isInstanceOf}.
     *                      Must be false if checking {@code doesInstanceofCompile}. This is because
     *                      type1 and type2 may be generics or unresolvable classes, and in those cases
     *                      we cannot use the instanceof operator in a generated oracle, because it
     *                      would not compile.
     */
    private static boolean isInstanceOf(String type1, String type2, OracleDatapoint oracleDatapoint, boolean checkEquality) {
        // Preliminary checks
        if (JavaTypes.PRIMITIVE_TYPES.contains(type1) || JavaTypes.PRIMITIVE_TYPES.contains(type2)) {
            return false;
        }
        if (checkEquality && type1.equals(type2)) {
            return true;
        }
        ResolvedType resolvedType2 = getResolvedTypeOrNull(type2);
        if (resolvedType2 == null) {
            return false; // Type2 is generic or an unknown class. In both cases, type1 cannot be instanceof it
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
        BlockStmt syntheticMethodBody = createSyntheticMethod(cu, oracleDatapoint).getBody().get();
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
            logger.warn("Failed to evaluate instanceof within method. Expression: \"type1Var instanceof {}\". Method: {}", type2, syntheticMethodBody.getParentNode().get());
            return false;
        }
    }

    private static ResolvedType getResolvedTypeOrNull(String type2) {
        ResolvedType resolvedType2;
        try {
            resolvedType2 = getResolvedType(type2);
        } catch (UnsolvedSymbolException e) {
            return null;
        }
        return resolvedType2;
    }

    /**
     * This method is different from {@link #isInstanceOf(String, String, OracleDatapoint)} in that it
     * can be used to compare primitive types and primitive wrapper types. For instance, a boolean is
     * assignable to a Boolean, and vice versa, but this cannot be checked with the instanceof operator.
     * Note that this method takes as input pairs of package and class name, instead of fully qualified
     * types.
     * @param type1 pair with &lt;package, class&gt;, e.g., &lt;"java.util", "List"&gt;
     * @param type2 pair with &lt;package, class&gt;, e.g., &lt;"java.lang", "Object"&gt;
     * @param oracleDatapoint may be null. If not null, it is used to check if some type is generic.
     * @return true if a variable of type1 can be assigned to a variable of type2, false otherwise
     */
    public static boolean isType1AssignableToType2(Pair<String, String> type1, Pair<String, String> type2, OracleDatapoint oracleDatapoint) {
        if (type1.equals(type2)) {
            // 1) left and right types are the same
            return true;
        }
        if (type1.equals(JavaTypes.NULL) && !JavaTypes.PRIMITIVES.contains(type2)) {
            // 2) left type is the null/void type and right type is not primitive
            return true;
        }
        if (JavaTypes.PRIMITIVES.contains(type1) && JavaTypes.PRIMITIVES_TO_WRAPPERS.get(type1).equals(type2)) {
            // 3) left type is primitive and right type is wrapper
            return true;
        }
        if (JavaTypes.PRIMITIVES.contains(type2) && JavaTypes.PRIMITIVES_TO_WRAPPERS.get(type2).equals(type1)) {
            // 4) right type is primitive and left type is wrapper
            return true;
        }
        if (JavaTypes.NUMBERS.contains(type1) && JavaTypes.NUMBERS.contains(type2) && isAssignableToNumeric(type1, type2)) {
            // 5) both types are numeric and left type is assignable to right type
            return true;
        }
        if (isInstanceOf(fullyQualifiedClassName(type1), fullyQualifiedClassName(type2), oracleDatapoint)) {
            // 6) left type is instance of right type (both types are non-primitive)
            return true;
        }
        return false;
    }

    public static TypeDeclaration<?> getClassOrInterface(CompilationUnit cu, String name) {
        try {
            List<ClassOrInterfaceDeclaration> classOrInterfaceList = cu.getLocalDeclarationFromClassname(name);
            if (!classOrInterfaceList.isEmpty()) {
                return classOrInterfaceList.get(0);
            }
        } catch (NoSuchElementException ignored) {
            // There are other alternatives to retrieve class, interface, etc., as below
        }
        Optional<ClassOrInterfaceDeclaration> classOrInterface = cu.getClassByName(name);
        if (classOrInterface.isPresent()) {
            return classOrInterface.get();
        }
        Optional<ClassOrInterfaceDeclaration> interface0 = cu.getInterfaceByName(name);
        if (interface0.isPresent()) {
            return interface0.get();
        }
        Optional<EnumDeclaration> enum0 = cu.getEnumByName(name);
        if (enum0.isPresent()) {
            return enum0.get();
        }
        Optional<AnnotationDeclaration> annotation = cu.getAnnotationDeclarationByName(name);
        if (annotation.isPresent()) {
            return annotation.get();
        }
        throw new RuntimeException("Could not find class, interface, enum or annotation '" + name + "' in compilation unit.");
    }

    public static TypeDeclaration<?> getClassOrInterface(String classSourceCode, String name) {
        CompilationUnit cu = javaParser.parse(classSourceCode).getResult().get();
        return getClassOrInterface(cu, name);
    }

    /**
     * Returns the String variable declaration(s) represented by a JavaParser
     * VariableDeclarator. Recall that a JavaParser field declaration may
     * declare multiple variables. Each variable is separated into individual
     * declarations.
     *
     * @param field the JavaParser field declaration. The parent field is
     *              necessary, as a {@link VariableDeclarator} does not store
     *              modifier information.
     * @param variable the JavaParser variable declaration
     * @return a string representation of the declaration of {@code variable}.
     * The declaration follows the format:
     *  "(modifiers) (type) (name)( = initial value);"
     */
    public static String getVariableDeclaration(FieldDeclaration field, VariableDeclarator variable) {
//        return (String.join("", mapList(Node::toString, field.getModifiers())) +
//                variable.getTypeAsString() + " " +
//                variable.getNameAsString() +
//                (variable.getInitializer().isPresent()
//                        ? " = " + variable.getInitializer().get()
//                        : "") +
//                ";");
        return field.toString();
    }

    /**
     * Gets the String field declaration represented by a JavaParser
     * {@link ResolvedFieldDeclaration}, but without the modifiers.
     *
     * @param resolvedField a field declaration
     * @return a string representation of the declaration. The declaration
     * follows the format:
     *  "(type) (name);"
     */
    private static String getFieldDeclarationWithoutModifiers(
            ResolvedFieldDeclaration resolvedField
    ) {
        return
                getTypeWithoutPackages(resolvedField.getType()) + " " +
                resolvedField.getName() +
                ";";
    }

    /**
     * Returns the String field declaration represented by a JavaParser
     * {@link ResolvedFieldDeclaration} as it would appear in source code.
     * Package names are removed for compatibility with the XText grammar.
     *
     * @param resolvedField a field declaration
     * @return a string representation of the declaration of
     * {@code resolvedField}. The declaration follows the format:
     *  "(access-specifier) (static) (type) (name);"
     */
    public static String getFieldDeclaration(
            ResolvedFieldDeclaration resolvedField
    ) {
        String accessSpecifier = resolvedField.accessSpecifier().asString();
        if (!accessSpecifier.equals("")) {
            accessSpecifier += " ";
        }
        boolean isStatic = resolvedField.isStatic();
        return accessSpecifier +
               (isStatic ? "static " : "") +
               getFieldDeclarationWithoutModifiers(resolvedField);
    }

    /**
     * Gets the String field declaration represented by a JavaParser
     * {@link ResolvedFieldDeclaration}. Package names are removed for
     * compatibility with the XText grammar.
     *
     * @param resolvedField a field declaration
     * @param modifier an integer representing the field modifiers
     * @return a string representation of the declaration. The declaration
     * follows the format:
     *  "(modifiers) (type) (name);"
     */
    public static String getFieldDeclaration(
            ResolvedFieldDeclaration resolvedField,
            int modifier
    ) {
        return ((modifier == 0) ? "" : (java.lang.reflect.Modifier.toString(modifier) + " ")) +
               getFieldDeclarationWithoutModifiers(resolvedField);
    }

    /**
     * Returns the signature of a JavaParser callable declaration. Uses the
     * method source code and removes method body, contained comments, the
     * Javadoc comment, and other special characters (e.g. "\n").
     *
     * @param jpCallable a JavaParser callable declaration
     * @return a string representation of the signature. A signature follows
     * the format:
     *     "[modifiers] [typeParameters] [type] [methodName]([parameters]) throws [exceptions]"
     */
    public static String getCallableSignature(
            CallableDeclaration<?> jpCallable
    ) {
        StringBuilder sb = new StringBuilder();
        // add modifiers
        List<String> modifiers = jpCallable.getModifiers()
                .stream()
                .map(Modifier::toString)
                .toList();
        sb.append(String.join("", modifiers));
        // add type parameters
        List<String> typeParameters = jpCallable.getTypeParameters()
                .stream()
                .map(TypeParameter::toString)
                .toList();
        if (!typeParameters.isEmpty()) {
            sb.append("<")
                    .append(String.join(", ", typeParameters))
                    .append(">")
                    .append(" ");
        }
        // add return type (if not a constructor)
        if (jpCallable.isMethodDeclaration()) {
            sb.append(jpCallable.asMethodDeclaration().getType())
                    .append(" ");
        }
        // add method name
        sb.append(jpCallable.getNameAsString());
        // add formal parameters
        List<String> parameters = jpCallable.getParameters()
                .stream()
                .map(Parameter::toString)
                .toList();
        sb.append("(")
                .append(String.join(", ", parameters))
                .append(")");
        // add exceptions
        List<String> exceptions = jpCallable.getThrownExceptions()
                .stream()
                .map(ReferenceType::asString)
                .toList();
        if (!exceptions.isEmpty()) {
            sb.append(" throws ")
                    .append(String.join(", ", exceptions));
        }
        return sb.toString().replaceAll(" +", " ").trim();
    }

    /**
     * Returns the signature of a JavaParser method declaration.
     *
     * @param methodDeclaration a JavaParser method declaration
     * @return a string representation of the signature. Signature follows the
     * format:
     *  "[modifiers] [type] [methodName]([parameters]) throws [exceptions]"
     */
    public static String getMethodSignature(MethodDeclaration methodDeclaration) {
//        String method = methodDeclaration.toString();
//        if (methodDeclaration.getBody().isPresent()) {
//            // Remove body
//            method = method.replace(methodDeclaration.getBody().get().toString(), "");
//        }
//        for (Node comment: methodDeclaration.getAllContainedComments()) {
//            // Remove comments within method signature
//            method = method.replace(comment.toString(), "");
//        }
//        // Last line is method signature, remove everything before that
//        method = method.replaceAll("[\\s\\S]*\n", "");
//        return method.trim().replaceAll(";$", "");
        return methodDeclaration.toString();
    }

    /**
     * Gets all type parameters of a given method as declared in source code.
     */
    private static List<String> getTypeParameters(MethodUsage methodUsage) {
        return methodUsage.getDeclaration().getTypeParameters()
                .stream()
                .map(ResolvedTypeParameterDeclaration::getName)
                .toList();
    }

    /**
     * Gets all formal parameters in the method definition. This method
     * returns the type of each parameter, followed by an artificial name. For
     * example,
     *     "MethodUsage[get(int i)]"    &rarr;    "List.of("int arg1")"
     */
    private static List<String> getParameters(MethodUsage methodUsage) {
        ResolvedMethodDeclaration methodDeclaration = methodUsage.getDeclaration();
        // iterate through each parameter in the method declaration.
        List<String> methodParameters = new ArrayList<>();
        for (int i = 0; i < methodDeclaration.getNumberOfParams(); i++) {
            methodParameters.add(getTypeWithoutPackages(methodDeclaration.getParam(i).getType()) + " arg" + i);
        }
        return methodParameters;
    }

    /**
     * Gets the simple names of all exceptions that can be thrown by a given
     * method.
     */
    private static List<String> getExceptions(MethodUsage methodUsage) {
        List<ResolvedType> exceptions = methodUsage.getDeclaration().getSpecifiedExceptions();
        return mapList(JavaParserUtils::getTypeWithoutPackages, exceptions);
    }

    /** Regex to match {@link ReflectionMethodDeclaration#toString()}. */
    private static final Pattern REFLECTION_METHOD_DECLARATION = Pattern.compile(
            "^ReflectionMethodDeclaration\\{method=((.*) )?\\S+ \\S+\\(.*}$"
    );

    /** Regex to match {@link JavassistMethodDeclaration#toString()}. */
    private static final Pattern JAVASSIST_METHOD_DECLARATION = Pattern.compile(
            "^JavassistMethodDeclaration\\{ctMethod=.*\\[((.*) )?\\S+ \\(.*\\).*]}$"
    );

    /**
     * Gets the modifiers of a JavaParser MethodUsage. Unfortunately, the
     * implementations of ResolvedMethodDeclaration have different approaches
     * for storing modifier information. This method uses regexes to handle
     * retrieving this information from {@link ReflectionMethodDeclaration} or
     * {@link JavassistMethodDeclaration} implementations of
     * ResolvedMethodDeclaration.
     *
     * @param methodDeclaration a resolved method declaration. Must be either
     *                          {@link ReflectionMethodDeclaration} or
     *                          {@link JavassistMethodDeclaration}.
     * @return the modifiers of the method
     */
    private static String getMethodModifiers(ResolvedMethodDeclaration methodDeclaration) {
        Matcher reflectionMatcher = REFLECTION_METHOD_DECLARATION.matcher(methodDeclaration.toString());
        Matcher javassistMatcher = JAVASSIST_METHOD_DECLARATION.matcher(methodDeclaration.toString());
        if (!reflectionMatcher.find() && !javassistMatcher.find()) {
            throw new IllegalStateException("Could not parse method signature: " + methodDeclaration);
        }
        String methodModifiers;
        if (methodDeclaration instanceof ReflectionMethodDeclaration) {
            methodModifiers = reflectionMatcher.group(1);
        } else {
            methodModifiers = javassistMatcher.group(1);
        }
        if (methodModifiers == null) {
            methodModifiers = "";
        }
        return methodModifiers;
    }

    /**
     * Gets the method signature from a JavaParser MethodUsage. Unfortunately,
     * depending on the implementation of the ResolvedMethodDeclaration, it is
     * not possible to recover specific features, such as:
     * <ul>
     *     <li>Modifiers</li>
     *     <li>Annotations</li>
     *     <li>Parameter names</li>
     * </ul>
     * This method considers three implementations of
     * ResolvedMethodDeclaration: JavaParserMethodDeclaration,
     * ReflectionMethodDeclaration, and JavassistMethodDeclaration. A
     * signature follows the format:
     *     "[modifiers] [typeParameters] [type] [methodName]([parameters]) throws [exceptions]"
     * All type names do not use package names for compatibility with the
     * XText grammar.
     */
    public static String getMethodSignature(
            MethodUsage methodUsage
    ) {
        ResolvedMethodDeclaration methodDeclaration = methodUsage.getDeclaration();
        // Consider JavaParserMethodDeclaration.
        if (methodDeclaration instanceof JavaParserMethodDeclaration jpMethodDeclaration) {
            MethodDeclaration jpMethod = jpMethodDeclaration.getWrappedNode();
//            return getMethodSignature(jpMethod);
            return jpMethod.toString();
        }
        String methodModifiers = getMethodModifiers(methodDeclaration);
        List<String> typeParameterList = getTypeParameters(methodUsage);
        List<String> formalParameterList = getParameters(methodUsage);
        List<String> exceptionList = getExceptions(methodUsage);
        return (methodModifiers + " " + (typeParameterList.isEmpty() ? "" : "<" + String.join(", ", typeParameterList) + ">") +
                " " + getTypeWithoutPackages(methodDeclaration.getReturnType()) +
                " " + methodDeclaration.getName() +
                "(" + String.join(", ", formalParameterList) + ")" +
                (exceptionList.isEmpty() ? "" : " throws " + String.join(", ", exceptionList)))
                .replaceAll(" +", " ").trim();
    }

    /**
     * Gets the JavaParser package declaration of a given compilation unit.
     *
     * @param cu a compilation unit
     * @return the JavaParser package declaration {@link PackageDeclaration}
     * of the compilation unit
     * @throws PackageDeclarationNotFoundException if the JavaParser package
     * declaration {@link PackageDeclaration} cannot be found or is unnamed
     */
    public static PackageDeclaration getPackageDeclaration(
            CompilationUnit cu
    ) throws PackageDeclarationNotFoundException {
        Optional<PackageDeclaration> jpPackage = cu.getPackageDeclaration();
        if (jpPackage.isEmpty()) {
            throw new PackageDeclarationNotFoundException("The compilation unit has no package declaration");
        }
        return jpPackage.get();
    }

    /**
     * Returns the element of a resolved type. Recursively strips all array
     * variables. For example:
     *     Object[][] => Object
     *
     * @param resolvedType a type
     * @return the element type
     */
    public static ResolvedType getElementType(ResolvedType resolvedType) {
        while (resolvedType.isArray()) {
            resolvedType = resolvedType.asArrayType().getComponentType();
        }
        return resolvedType;
    }

    /**
     * Checks if a type is a type variable.
     *
     * @param resolvedType a JavaParser resolved type
     * @return true iff a given type is a type variable of a generic class or
     * method. If the given type is an array, then this method checks the
     * element type.
     */
    public static boolean isTypeVariable(
            ResolvedType resolvedType
    ) {
        ResolvedType elementType = getElementType(resolvedType);
        return elementType.isTypeVariable();
    }

    /**
     * Checks if a type name represents a type variable declared in the given
     * class or method. This method checks both the method where the type is
     * used and its declaring class.
     *
     * @param typeName a type name
     * @param jpCallable the method using {@code typeName}
     * @param jpClass the declaring class of {@code jpCallable}
     * @return true iff a given type is a type variable of the declaring class
     * or method. If the given type is an array, then this method checks the
     * element type.
     */
    public static boolean isTypeVariable(
            String typeName,
            CallableDeclaration<?> jpCallable,
            TypeDeclaration<?> jpClass
    ) {
        String elementType = typeName.replaceAll("\\[]", "");
        for (TypeParameter typeParameter : jpCallable.getTypeParameters()) {
            if (typeParameter.getNameAsString().equals(elementType)) {
                return true;
            }
        }
        if (jpClass instanceof ClassOrInterfaceDeclaration) {
            for (TypeParameter typeParameter : jpClass.asClassOrInterfaceDeclaration().getTypeParameters()) {
                if (typeParameter.getNameAsString().equals(elementType)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Get all methods available to a given class (including those defined in
     * superclasses).
     *
     * @param typeDeclaration object class
     * @return list of MethodUsage objects
     * @throws JPClassNotFoundException if {@code typeDeclaration} is not
     * resolvable
     */
    public static List<MethodUsage> getAllMethods(
            TypeDeclaration<?> typeDeclaration
    ) throws JPClassNotFoundException {
        try {
            return new ArrayList<>(typeDeclaration.resolve().getAllMethods());
        } catch (UnsolvedSymbolException | IllegalArgumentException e) {
            String errMsg = String.format(
                    "Unable to get all the methods of the class %s.%n%s.",
                    typeDeclaration.getNameAsString(), e
            );
            logger.error(errMsg);
            throw new JPClassNotFoundException(errMsg, e);
        }
    }

    /**
     * Get all fields available (non-private) to a given class instance
     * (including those defined in superclasses). This method only considers
     * an instance of a class, such that private fields are not accessible.
     *
     * @param typeDeclaration object class
     * @return list of ResolvedFieldDeclaration objects
     * @throws JPClassNotFoundException if {@code typeDeclaration} is not
     * resolvable
     */
    public static List<ResolvedFieldDeclaration> getAllFields(
            TypeDeclaration<?> typeDeclaration
    ) throws JPClassNotFoundException {
        try {
            return typeDeclaration.resolve().getAllFields();
        } catch (UnsolvedSymbolException | IllegalArgumentException e) {
            String errMsg = String.format(
                    "Unable to get all the methods of class %s.%n%s.",
                    typeDeclaration.getNameAsString(), e
            );
            logger.error(errMsg);
            throw new JPClassNotFoundException(errMsg, e);
        }
    }

    /**
     * Gets a compilation unit from a Java file path.
     *
     * @param path a Java file
     * @return the corresponding JavaParser compilation unit. Returns
     * {@code Optional.empty()} if an error occurs while attempting to parse
     * the file.
     */
    public static Optional<CompilationUnit> getCompilationUnit(Path path) {
        try {
            return javaParser.parse(path).getResult();
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    /**
     * Checks if a method is non-private and non-void.
     *
     * @param methodUsage a method
     * @return true iff the method is non-private and non-void
     */
    private static boolean isNonPrivateNonVoidMethod(MethodUsage methodUsage) {
        return !methodUsage.getDeclaration().getReturnType().isVoid() &&
                !methodUsage.getDeclaration().accessSpecifier().equals(AccessSpecifier.PRIVATE);
    }

    /**
     * Checks if an attribute is non-private and non-static. An attribute
     * is defined as any class field.
     *
     * @param fieldDeclaration a class attribute
     * @return true iff the attribute is non-private and non-static
     */
    public static boolean isNonPrivateNonStaticAttribute(ResolvedFieldDeclaration fieldDeclaration) {
        return !fieldDeclaration.accessSpecifier().equals(AccessSpecifier.PRIVATE) &&
                !fieldDeclaration.isStatic();
    }

    public static boolean isNonPrivateStaticNonVoidMethod(MethodUsage methodUsage) {
        return methodUsage.getDeclaration().isStatic() &&
                isNonPrivateNonVoidMethod(methodUsage);
    }

    public static boolean isNonPrivateNonStaticNonVoidMethod(MethodUsage methodUsage) {
        return !methodUsage.getDeclaration().isStatic() &&
                isNonPrivateNonVoidMethod(methodUsage);
    }

    public static List<String> getImports(String classSourceCode) {
        CompilationUnit cu = javaParser.parse(classSourceCode).getResult().get();
        return cu.getImports().stream().map(ImportDeclaration::getNameAsString).toList();
    }

    /**
     * Unfortunately, JavaParser does not allow to parse constructors as method declarations. Since we don't
     * need the constructor declaration for anything, if the passed methodSourceCode is from a constructor,
     * this method returns null. This behavior must be handled by the caller.
     * @return null if the passed method source code is actually a constructor
     * @throws IllegalArgumentException if the provided methodSourceCode cannot be parsed by JavaParser or
     * if it is not a constructor or method
     */
    public static MethodDeclaration getMethodDeclaration(String methodSourceCode) throws IllegalArgumentException {
        Optional<BodyDeclaration<?>> parseResult = javaParser.parseBodyDeclaration(methodSourceCode).getResult();
        if (parseResult.isPresent()) {
            BodyDeclaration<?> bodyDeclaration = parseResult.get();
            if (bodyDeclaration.isMethodDeclaration()) {
                return bodyDeclaration.asMethodDeclaration();
            } else if (bodyDeclaration.isConstructorDeclaration()) {
                return null;
            } else {
                throw new IllegalArgumentException("Not a constructor or method:" + System.lineSeparator() + methodSourceCode);
            }
        } else {
            throw new IllegalArgumentException("JavaParser cannot parse:" + System.lineSeparator() + methodSourceCode);
        }
    }

    private static BodyDeclaration<?> getMethodOrConstructorDeclaration(String methodSourceCode) {
        BodyDeclaration<?> bodyDeclaration = javaParser.parseBodyDeclaration(methodSourceCode).getResult().get();
        if (bodyDeclaration.isMethodDeclaration() || bodyDeclaration.isConstructorDeclaration()) {
            return bodyDeclaration;
        } else {
            throw new IllegalArgumentException("Not a constructor or method:" + System.lineSeparator() + methodSourceCode);
        }
    }

    private static NodeList<TypeParameter> getTypeParameters(BodyDeclaration<?> bodyDeclaration) {
        if (bodyDeclaration.isMethodDeclaration()) {
            return bodyDeclaration.asMethodDeclaration().getTypeParameters();
        } else if (bodyDeclaration.isConstructorDeclaration()) {
            return bodyDeclaration.asConstructorDeclaration().getTypeParameters();
        } else {
            throw new IllegalArgumentException("Not a constructor or method body:"+ System.lineSeparator() + bodyDeclaration);
        }
    }

    private static NodeList<Parameter> getParameters(BodyDeclaration<?> bodyDeclaration) {
        if (bodyDeclaration.isMethodDeclaration()) {
            return bodyDeclaration.asMethodDeclaration().getParameters();
        } else if (bodyDeclaration.isConstructorDeclaration()) {
            return bodyDeclaration.asConstructorDeclaration().getParameters();
        } else {
            throw new IllegalArgumentException("Not a constructor or method body:" + System.lineSeparator() + bodyDeclaration);
        }
    }

    // Variables used in the next method
    private static final String javadocBeginning = "/**\n * ";
    private static final String javadocEnding = "\n */";
    private static final String javadocMethod = "\nvoid someMethod();";
    private static final String javadocIndent = "    ";
    /**
     * Change the Javadoc of a method so that it contains the new Javadoc tag instead
     * of the old one.
     * @param methodJavadoc the whole Javadoc of the method
     * @param oldJavadocTag the Javadoc tag that we want to replace
     * @param newJavadocTag the new Javadoc tag to replace the old one
     * @throws IllegalArgumentException if the old Javadoc tag is not present in the method Javadoc
     * @throws IllegalStateException should never be thrown, if it is, this method is probably buggy
     * @return the new Javadoc of the method
     */
    public static String updateMethodJavadoc(String methodJavadoc, String oldJavadocTag, String newJavadocTag) {
        if (oldJavadocTag.equals(newJavadocTag)) {
            return methodJavadoc;
        }

        // Can't update Javadoc as String, so need to parse, modify and stringify it
        Javadoc javadoc = javaParser.parseMethodDeclaration(methodJavadoc + javadocMethod).getResult().get().getJavadoc().get();
        List<JavadocBlockTag> oldJavadocBlockTags = javadoc.getBlockTags();
        JavadocBlockTag oldJavadocBlockTag = oldJavadocBlockTags.stream()
                .filter(t -> t.toText().equals(oldJavadocTag))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("The old Javadoc tag is not present in the method Javadoc."));

        if (newJavadocTag.equals("")) {
            // Remove tag from Javadoc
            oldJavadocBlockTags.remove(oldJavadocBlockTag);
        } else {
            // Replace tag in Javadoc
            JavadocBlockTag newJavadocBlockTag = javaParser.parseMethodDeclaration(javadocBeginning + newJavadocTag + javadocEnding + javadocMethod)
                    .getResult().get().getJavadoc().get().getBlockTags().get(0);
            oldJavadocBlockTags.set(oldJavadocBlockTags.indexOf(oldJavadocBlockTag), newJavadocBlockTag);
        }
        if (!javadoc.toText().contains(newJavadocTag) ||
                (javadoc.toText().contains(oldJavadocTag) && !newJavadocTag.contains(oldJavadocTag) && methodJavadoc.split(oldJavadocTag).length == 2)) {
            // After ||: If the new Javadoc still contains the old tag, it might be because the new tag actually contains
            // the old tag (is a substring) or because there's another tag whose param name contains the old param name
            throw new IllegalStateException("The Javadoc tag could not be correctly updated.");
        }

        return javadocIndent + javadoc.toComment(javadocIndent).asString();
    }
}
