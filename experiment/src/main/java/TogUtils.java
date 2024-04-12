import com.fasterxml.jackson.core.type.TypeReference;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseProblemException;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.nodeTypes.NodeWithSimpleName;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.type.ReferenceType;
import com.github.javaparser.ast.type.TypeParameter;
import com.github.javaparser.resolution.MethodUsage;
import com.github.javaparser.resolution.SymbolResolver;
import com.github.javaparser.resolution.UnsolvedSymbolException;
import com.github.javaparser.resolution.declarations.ResolvedMethodDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedTypeParameterDeclaration;
import com.github.javaparser.resolution.types.ResolvedType;
import com.github.javaparser.symbolsolver.javaparsermodel.declarations.JavaParserMethodDeclaration;
import com.github.javaparser.symbolsolver.javassistmodel.JavassistMethodDeclaration;
import com.github.javaparser.symbolsolver.reflectionmodel.ReflectionMethodDeclaration;
import com.github.javaparser.symbolsolver.utils.SymbolSolverCollectionStrategy;
import com.github.javaparser.utils.Pair;
import data.*;
import data.JDoctorOutput.Parameter;
import data.JDoctorOutput.ParamTag;
import data.JDoctorOutput.ReturnTag;
import data.JDoctorOutput.ThrowsTag;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.plumelib.util.CollectionsPlume.mapList;

/**
 * This class provides static methods for pre-processing inputs for a given
 * TOG and converting its output into other {@link data} outputs.
 */
public class TogUtils {
    /** The path of the output directory. */
    private static final Path output = Paths.get("output");
    /** The path of the EvoSuite prefixes directory. */
    private static final Path evosuitePrefixPath = output.resolve("evosuite-prefixes");
    /** The path of the EvoSuite simple tests directory (tests with one assertion).  */
    private static final Path evosuiteTestsSimplePath = output.resolve("evosuite-simple-tests");
    /** A regex pattern to extract a text prefix (removes comments and decorators). */
    private static final Pattern testPrefixPattern = Pattern.compile("(public|protected|private)(.| )*");
    /** A JavaParser used to pre-process source code to be converted into TOGA input. */
    private static JavaParser javaParser;

    /** Regex to match {@link ReflectionMethodDeclaration#toString()}. */
    private static final Pattern REFLECTION_METHOD_DECLARATION = Pattern.compile(
            "^ReflectionMethodDeclaration\\{method=((.*) )?\\S+ \\S+\\(.*}$"
    );

    /** Regex to match {@link JavassistMethodDeclaration#toString()}. */
    private static final Pattern JAVASSIST_METHOD_DECLARATION = Pattern.compile(
            "^JavassistMethodDeclaration\\{ctMethod=.*\\[((.*) )?\\S+ \\(.*\\).*]}$"
    );

    /** Artificial class name. */
    private static final String SYNTHETIC_CLASS_NAME = "Tratto__AuxiliaryClass";
    /** Artificial class source code. */
    private static final String SYNTHETIC_CLASS_SOURCE = "public class " + SYNTHETIC_CLASS_NAME + " {}";
    /** Artificial method name. */
    private static final String SYNTHETIC_METHOD_NAME = "__tratto__auxiliaryMethod";
    /** Regex to match the binary name of a class (e.g. "package.submodule.InnerClass$OuterClass") */
    private static final Pattern PACKAGE_CLASS = Pattern.compile("[a-zA-Z_][a-zA-Z\\d_]*(\\.[a-zA-Z_][a-zA-Z\\d_]*)*");
    /** Regex to match the Javadoc of a method */
    private static Pattern FOCAL_METHOD = Pattern.compile("(\\s*\\/\\*\\*[.\\s\\S]*?\\*\\/\\s+)?([.\\s\\S]*)");

    /** Private constructor to avoid creating an instance of this class. */
    private TogUtils() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    private enum ExpressionType {
        METHOD_CALL,
        CONSTRUCTOR_CALL
    }

    /**
     * Creates a JavaParser object capable of resolving symbols from a given
     * source directory.
     *
     * @param srcDirPath a project source directory
     * @return the corresponding JavaParser
     */
    public static JavaParser getJavaParser(Path srcDirPath) {
        SymbolSolverCollectionStrategy strategy = new SymbolSolverCollectionStrategy();
        strategy.collect(srcDirPath);
        SymbolResolver symbolResolver = strategy.getParserConfiguration().getSymbolResolver().orElseThrow();
        JavaParser javaParser = new JavaParser();
        javaParser.getParserConfiguration().setSymbolResolver(symbolResolver);
        return javaParser;
    }

    private static Pair<MethodUsage,List<Exception>> searchCandidateMethodUsage(CompilationUnit cuClassFile, Expression lastCallExpr) {
        List<MethodUsage> method_not_converted = new ArrayList<>();
        List<MethodDeclaration> method_converted = new ArrayList<>();
        // Define the focal method. Initially null.
        MethodUsage focalMethod = null;
        // Define a list of candidate methods/constructors (methods/constructors with the same name and number of parameters).
        // Initially empty.
        List<MethodUsage> candidatesMethodUsage = new ArrayList<>();
        // Define list of warnings
        List<Exception> warnings = new ArrayList<>();
        // Get the name of the focal method
        String focalMethodName = ((MethodCallExpr) lastCallExpr).getNameAsString();
        // Get the list of arguments of the focal method
        NodeList<Expression> focalMethodArgs = ((MethodCallExpr) lastCallExpr).getArguments();

        for(MethodUsage m : cuClassFile.getPrimaryType().get().resolve().getAllMethods()) {
            try {
                method_converted.add((MethodDeclaration) m.getDeclaration().toAst().get());
            } catch(Exception e) {
                method_not_converted.add(m);
            }
        }

        // Iterate over the list of methods/constructors in the class file
        for (MethodUsage methodUsage : method_not_converted) {
            // Check if the method/constructor name is the same
            if (methodUsage.getName().equals(focalMethodName)) {
                // Check if the number of parameters is the same
                if (methodUsage.getParamTypes().size() == focalMethodArgs.size()) {
                    // Add method/constructor to candidates if the previous conditions are satisfied
                    candidatesMethodUsage.add(methodUsage);
                }
            }
        }

        if (candidatesMethodUsage.size() == 0) {
            throw new NoSuchElementException("[ERROR] - Focal method not found. No candidate methods found in the original class, analyzing both the name and the number of parameters.");
        } else if (candidatesMethodUsage.size() == 1) {
            // If only one candidate method/constructor is found, set the focal method to the candidate method/constructor
            focalMethod = candidatesMethodUsage.get(0);
        } else {
            for (MethodUsage m : candidatesMethodUsage) {
                // If multiple candidate methods are found, try to discern the focal method by analyzing
                // the types of the parameters of the focal method call expression and the parameters of the
                // candidate methods/constructors.

                // Initialize the number of parameters in common to -1 (no parameters in common)
                int bestParamsTypesInCommon = -1;
                // Initialize the boolean flag multipleCandidates to false (only one candidate method/constructor is
                // the most similar to the method call expression)
                boolean multipleCandidates = false;
                // Initialize the most similar candidate method/constructor to null
                MethodUsage mostSimilar = null;
                // Iterate over the list of candidate methods/constructors
                for (MethodUsage candidate : candidatesMethodUsage) {
                    // Get the list of parameters of the candidate method/constructor
                    List<ResolvedType> paramsTypeList = candidate.getParamTypes();
                    // Initialize the number of parameters in common with the current candidate to 0
                    int paramsTypesInCommon = 0;
                    // Iterate over the list of parameters of the current candidate method/constructor
                    for (int i = 0; i < paramsTypeList.size(); i++) {
                        // Get the i-th parameter
                        ResolvedType paramType = paramsTypeList.get(i);
                        // Get the type of the i-th parameter of the focal method call in the test
                        // prefix, as a string
                        String focalMethodTypeName = focalMethodArgs.get(i).calculateResolvedType().describe();
                        // Get the type of the i-th parameter of the current candidate method, as a string
                        String paramTypeName = paramType.describe();
                        // Compare the parameters type names
                        if (focalMethodTypeName.endsWith(paramTypeName)) {
                            // If the type names corresponds, increment the number of parameters in common
                            paramsTypesInCommon += 1;
                        }
                    }
                    // If the number of parameters in common with the current candidate method/constructor is
                    // greater or equal to the best number of parameters in common found so far, update the best
                    // number of parameters in common and assign the current candidate method/constructor to the
                    // most similar candidate method/constructor
                    if (paramsTypesInCommon >= bestParamsTypesInCommon) {
                        if (paramsTypesInCommon == bestParamsTypesInCommon) {
                            multipleCandidates = true;
                        } else {
                            bestParamsTypesInCommon = paramsTypesInCommon;
                            multipleCandidates = false;
                            mostSimilar = candidate;
                        }
                    }
                }
                // If only one candidate method/constructor is the most similar to the focal method call expression, set
                // the focal method to the most similar candidate method
                if (!(multipleCandidates || mostSimilar == null)) {
                    focalMethod = mostSimilar;
                    if (bestParamsTypesInCommon < focalMethodArgs.size()) {
                        // If the number of parameters in common is less than the number of parameters of
                        // the method call expression, log a warning
                        warnings.add(new IllegalStateException("[WARNING] - Some parameters are not matched in the method call. Check if it is a false or true positive."));
                    }
                } else {
                    // Multiple candidates, impossible to discern
                    throw new NoSuchElementException("[ERROR] - Multiple candidate methods/constructors found. Impossible to discern the focal method.");
                }
            }
        }
        return new Pair<>(focalMethod, warnings);
    }

    private static Pair<CallableDeclaration,List<Exception>> searchCandidateCallableDeclaration(CompilationUnit cuClassFile, Expression lastCallExpr, ExpressionType exprType) {
        // Define the list of all the callable methods/constructors in the class file. Initially empty.
        List<? extends CallableDeclaration<?>> classCallableList = new ArrayList<>();
        // Define the name of the focal method. Initially null.
        String focalMethodName = null;
        // Define the list of arguments of the focal method. Initially empty.
        NodeList<Expression> focalMethodArgs = new NodeList<>();
        // Define the focal method. Initially null.
        CallableDeclaration focalMethod = null;
        // Define a list of candidate methods/constructors (methods/constructors with the same name and number of parameters).
        // Initially empty.
        List<CallableDeclaration> candidatesCallables = new ArrayList<>();
        // Define list of warnings
        List<Exception> warnings = new ArrayList<>();

        if (exprType == ExpressionType.METHOD_CALL) {
            classCallableList = cuClassFile.getPrimaryType().orElseThrow().getMethods();
            focalMethodName = ((MethodCallExpr) lastCallExpr).getNameAsString();
            focalMethodArgs = ((MethodCallExpr) lastCallExpr).getArguments();
        } else if (exprType == ExpressionType.CONSTRUCTOR_CALL) {
            classCallableList = cuClassFile.getPrimaryType().orElseThrow().getConstructors();
            focalMethodName = ((ObjectCreationExpr) lastCallExpr).getTypeAsString();
            focalMethodArgs = ((ObjectCreationExpr) lastCallExpr).getArguments();
        }

        // Iterate over the list of methods/constructors in the class file
        for (CallableDeclaration callable : classCallableList) {
            // Check if the method/constructor name is the same
            if (callable.getNameAsString().equals(focalMethodName)) {
                // Check if the number of parameters is the same
                if (callable.getParameters().size() == focalMethodArgs.size()) {
                    // Add method/constructor to candidates if the previous conditions are satisfied
                    candidatesCallables.add(callable);
                }
            }
        }
        // If no candidate methods are found, throw an exception (focal method not found)
        if (candidatesCallables.size() == 0) {
            return new Pair<>(focalMethod, new ArrayList<>());
        } else if (candidatesCallables.size() == 1) {
            // If only one candidate method/constructor is found, set the focal method to the candidate method/constructor
            focalMethod = candidatesCallables.get(0);
        } else {
            // If multiple candidate methods are found, try to discern the focal method by analyzing
            // the types of the parameters of the focal method call expression and the parameters of the
            // candidate methods/constructors.

            // Initialize the number of parameters in common to -1 (no parameters in common)
            int bestParamsTypesInCommon = -1;
            // Initialize the boolean flag multipleCandidates to false (only one candidate method/constructor is
            // the most similar to the method call expression)
            boolean multipleCandidates = false;
            // Initialize the most similar candidate method/constructor to null
            CallableDeclaration mostSimilar = null;
            // Iterate over the list of candidate methods/constructors
            for (CallableDeclaration callable : candidatesCallables) {
                // Get the list of parameters of the candidate method/constructor
                NodeList<com.github.javaparser.ast.body.Parameter> paramsList = callable.getParameters();
                // Initialize the number of parameters in common with the current candidate to 0
                int paramsTypesInCommon = 0;
                // Iterate over the list of parameters of the current candidate method/constructor
                for (int i = 0; i < paramsList.size(); i++) {
                    // Get the i-th parameter
                    com.github.javaparser.ast.body.Parameter param = paramsList.get(i);
                    // Get the type of the i-th parameter of the focal method call in the test
                    // prefix, as a string
                    String focalMethodTypeName = focalMethodArgs.get(i).calculateResolvedType().describe();
                    // Get the type of the i-th parameter of the current candidate method, as a string
                    String paramTypeName = param.getTypeAsString();
                    // Compare the parameters type names
                    if (focalMethodTypeName.endsWith(paramTypeName)) {
                        // If the type names corresponds, increment the number of parameters in common
                        paramsTypesInCommon += 1;
                    }
                }
                // If the number of parameters in common with the current candidate method/constructor is
                // greater or equal to the best number of parameters in common found so far, update the best
                // number of parameters in common and assign the current candidate method/constructor to the
                // most similar candidate method/constructor
                if (paramsTypesInCommon >= bestParamsTypesInCommon) {
                    if (paramsTypesInCommon == bestParamsTypesInCommon) {
                        multipleCandidates = true;
                    } else {
                        bestParamsTypesInCommon = paramsTypesInCommon;
                        multipleCandidates = false;
                        mostSimilar = callable;
                    }
                }
            }
            // If only one candidate method/constructor is the most similar to the focal method call expression, set
            // the focal method to the most similar candidate method
            if (!(multipleCandidates || mostSimilar == null)) {
                focalMethod = mostSimilar;
                if (bestParamsTypesInCommon < focalMethodArgs.size()) {
                    // If the number of parameters in common is less than the number of parameters of
                    // the method call expression, log a warning
                    warnings.add(new IllegalStateException("[WARNING] - Some parameters are not matched in the method call. Check if it is a false or true positive."));
                }
            } else {
                // Multiple candidates, impossible to discern
                throw new NoSuchElementException("[ERROR] - Multiple candidate methods/constructors found. Impossible to discern the focal method.");
            }
        }
        return new Pair<>(focalMethod, warnings);
    }

    private static Pair<CallableDeclaration,List<Exception>> resolveCallExpression(CompilationUnit cuClassFile, Expression callExpr, ExpressionType exprType) {
        // Define the focal method. Initially null.
        CallableDeclaration focalMethod = null;
        try {
            if (exprType == ExpressionType.METHOD_CALL) {
                // Try to resolve the method call expression to a method declaration
                MethodCallExpr methodCallExpr = callExpr.asMethodCallExpr();
                focalMethod = (MethodDeclaration) methodCallExpr.resolve().toAst().orElseThrow(() -> new UnsolvedSymbolException(methodCallExpr.getNameAsString()));
                return new Pair<>(focalMethod, new ArrayList<>());
            } else if (exprType == ExpressionType.CONSTRUCTOR_CALL) {
                // Try to resolve the constructor call expression to a constructor declaration
                ObjectCreationExpr constructorCallExpr = callExpr.asObjectCreationExpr();
                focalMethod = (ConstructorDeclaration) constructorCallExpr.resolve().toAst().orElse(null);
                return new Pair<>(focalMethod, new ArrayList<>());
            }
        } catch (UnsolvedSymbolException e) {
            // The method call expression could not be resolved to a method declaration by
            // Java Symbol Solver
            focalMethod = null;
        }
        // If the method call expression could not be resolved to a method declaration by Java
        // Symbol Solver, try to find the method declaration by comparing the method call expression
        // with the list of methods in the class file (analyzing the signature and the number and types
        // of the parameters.
        if (focalMethod == null) {
            return searchCandidateCallableDeclaration(cuClassFile, callExpr, exprType);
        }
        return new Pair<>(focalMethod, new ArrayList<>());
    }
    
    private static Pair<CallableDeclaration,List<Exception>> getCallableFocalMethod(CompilationUnit cuClassFile, Expression lastCallExpr)  throws IllegalStateException {
        // Define the focal method. Initially null.
        CallableDeclaration focalMethod = null;
        // Define list of warnings
        List<Exception> warnings = new ArrayList<>();

        Pair<Expression, ExpressionType> result = parseExpression(lastCallExpr);
        Expression parsedExpr = result.a;
        ExpressionType exprType = result.b;
        // Resolve the expression to a method/constructor declaration
        return resolveCallExpression(cuClassFile, parsedExpr, exprType);
    }

    private static Pair<Expression,ExpressionType> parseExpression(Expression expr) {
        Expression parsedExpr = expr;
        if (expr.isAssignExpr()) {
            // Get the value of the assignment expression
            parsedExpr = expr.asAssignExpr().getValue();
        } else if (expr.isVariableDeclarationExpr()) {
            parsedExpr = expr.asVariableDeclarationExpr().getVariable(0).getInitializer().orElseThrow(() -> new IllegalStateException(String.format("[ERROR] - Last statement in test is a variable declaration, but the inizializer cannot be resolved.")));
        }
        if (parsedExpr.isCastExpr()) {
            // Get the expression of the cast expression
            parsedExpr = parsedExpr.asCastExpr().getExpression();
        }
        // Check if the last expression is a method call or a constructor call
        if (parsedExpr.isMethodCallExpr()) {
            return new Pair<>(parsedExpr, ExpressionType.METHOD_CALL);
        } else if (parsedExpr.isObjectCreationExpr()) {
            return new Pair<>(parsedExpr, ExpressionType.CONSTRUCTOR_CALL);
        } else {
            throw new IllegalStateException(String.format("[ERROR] - Unable to parse last statement as a MethodCallExpr or an ObjectCreationExpr statement."));
        }
    }

    private static String getClassFullyQualifiedNameFromExpression(Expression expr) {
        try {
            if (expr.isMethodCallExpr()) {
                String methodFQN = expr.asMethodCallExpr().resolve().getQualifiedName();
                if (methodFQN.contains("java.lang.Object")) {
                    throw new IllegalStateException("[ERROR] - Unable to manage built-in java classes.");
                }
                return methodFQN.substring(0, methodFQN.lastIndexOf("."));
            } else if (expr.isObjectCreationExpr()) {
                return expr.asObjectCreationExpr().getType().resolve().describe();
            } else {
                throw new IllegalStateException("[ERROR] - Unable to get the fully qualified name from the expression.");
            }
        } catch (UnsolvedSymbolException e) {
            throw new UnsolvedSymbolException("[ERROR] - Unable to resolve the expression and get the fully qualified name of the corresponding class.");
        }
    }

    /**
     * Generates the input file for the TOGA tog. As required by TOGA, the input is a .csv file
     * composed of three columns:
     * <ol>
     *     <li>the focal method (i.e. the signature of the method for which
     *     TOGA has to generate oracles)</li>
     *     <li>the test prefix of the method under test</li>
     *     <li>the docstring of the method under test</li>
     * </ol>
     * Extracts each method under test from the test prefixes and generate triples of
     * (focalMethod, testPrefix, docstring).
     * Saves the output file in output/toga/input.
     *
     * @param srcDirPath the path to the source code of the project under test
     * @param fullyQualifiedClassName the fully qualified name of the class under test
     *
     */
    public static void generateTOGAInput(Path srcDirPath, String fullyQualifiedClassName) {
        javaParser = getJavaParser(srcDirPath);
        Path prefixPath = output.resolve(Paths.get("toga-input"));
        Path errorPath = output.resolve(Paths.get("toga-err"));
        Path togaInputPath = prefixPath.resolve("toga_input.csv");
        Path togaMetadataPath = prefixPath.resolve("toga_metadata.csv");
        Path togaInfoPath = prefixPath.resolve("toga_info.json");
        Path fullyQualifiedClassNamePath = FileUtils.getFQNPath(fullyQualifiedClassName);
        Path togaErrorPath = errorPath.resolve(fullyQualifiedClassNamePath.resolve("err.csv"));
        String className = FileUtils.getSimpleNameFromFQN(fullyQualifiedClassName);
        int classNameIdx = fullyQualifiedClassNamePath.getNameCount() - 1;
        Path fullyQualifiedTestClassNamePath = classNameIdx > 0 ?
                fullyQualifiedClassNamePath.subpath(0, classNameIdx).resolve(className + "_ESTest.java") :
                Paths.get(className);
        Path classFilePath = srcDirPath.resolve(fullyQualifiedClassNamePath);
        Path testClassFilePath = evosuiteTestsSimplePath.resolve(fullyQualifiedTestClassNamePath);
        Path testClassPrefixFilePath = evosuitePrefixPath.resolve(fullyQualifiedTestClassNamePath);

        if (!Files.exists(classFilePath)){
            Path commonPatternPath = Paths.get(srcDirPath.toString(), "main", "java");
            classFilePath = commonPatternPath.resolve(fullyQualifiedClassNamePath);
            if (!Files.exists(classFilePath)) {
                classFilePath = FileUtils.findClassPath(srcDirPath, fullyQualifiedClassNamePath);
                if (classFilePath == null) {
                    throw new RuntimeException("The full path to the class file has not been found.");
                }
            }
        }

        try {
            final CompilationUnit cuClassFile = javaParser.parse(classFilePath).getResult().orElseThrow();
            CompilationUnit cuTestClassFile = javaParser.parse(testClassFilePath).getResult().orElseThrow();
            CompilationUnit cuTestClassPrefixFile = javaParser.parse(testClassPrefixFilePath).getResult().orElseThrow();
            Map<String, Map<String,String>> togaInfo = new HashMap<>();
            StringBuilder togaInputBuilder = new StringBuilder();
            StringBuilder togaMetadataBuilder = new StringBuilder();
            StringBuilder togaErrBuilder = new StringBuilder();
            togaInputBuilder.append("focal_method,test_prefix,docstring\n");
            togaMetadataBuilder.append("project,bug_num,test_name,exception_bug,assertion_bug,exception_lbl,assertion_lbl,assert_err\n");

            cuTestClassPrefixFile.findAll(MethodDeclaration.class).forEach(testPrefix -> {
                // Define the focal method to find (the last method call in the test). Initially null.
                // The focal method must be a method declaration or a constructor declaration.
                CallableDeclaration focalMethod = null;
                // The name of the focal method. Initially empty.
                String methodName = "";
                // The signature of the focal method. Initially empty.
                String focalMethodSignature = "";
                // The entire method body of the focal method. Initially empty.
                String focalMethodBody = "";
                // The Javadoc of the focal method. Initially empty.
                String javadocString = "";
                // Get the name of the test
                String testName = testPrefix.getNameAsString();
                // Map representing each row of the toga_info.json file. Each row is the input to the toga model.
                // The key is the test name, and the value is string composed of the test prefix, the focal method, and
                // the Javadoc, if present.
                Map<String, String> togaRow = new HashMap<>();
                // Assume the compilation unit of the last statement to be the one of the class under test.
                // It will be updated if the last statement is a method call or a constructor call of another class.
                CompilationUnit cuLastStatementClassFile = cuClassFile;
                try {
                    // Get the list of all the statements within the test
                    NodeList<Statement> statements = testPrefix
                            .getBody()
                            .orElseThrow(() -> new IllegalStateException(String.format("Cannot retrieve block statements from test %s.", testName)))
                            .getStatements();
                    // Throw an exception if no statements are found (empty test)
                    if (statements.size() <= 0) {
                        throw new IllegalStateException(String.format("No statements found in test %s.", testName));
                    }
                    // Define the last expression statement in the test. Initially null.
                    ExpressionStmt lastExpressionStmt = null;
                    // Check if the last statement is an expression statement (a method call or a constructor call must
                    // be an expression statement.
                    for (int i = statements.size() - 1; i >= 0; i--) {
                        Statement lastStatement = statements.get(i);
                        if (lastStatement.isExpressionStmt()) {
                            lastExpressionStmt = lastStatement.asExpressionStmt();
                            if (i < (statements.size() - 1)) {
                                // If the last statement is an expression statement, log a warning
                                togaErrBuilder.append(generateLogTogaInputError(testPrefix, fullyQualifiedClassName, "WARNING", new IllegalStateException("[WARNING] - The last statement in the test is not the last statement in the block. Check if the focal method is correct")));
                            }
                            break;
                        }
                    }
                    // If no expression statement is found, throw an exception (empty test)
                    if (lastExpressionStmt == null) {
                        throw new IllegalStateException(String.format("No expression statement found in test %s.", testName));
                    }
                    // Get the expression in the last expression statement
                    Expression lastExpression = lastExpressionStmt.getExpression();

                    try {
                        String fullyQualifiedNameLastExpression = getClassFullyQualifiedNameFromExpression(parseExpression(lastExpression).a);

                        if (!fullyQualifiedNameLastExpression.equals(fullyQualifiedClassName)) {
                            Path fullyQualifiedClassNameLastExpressionPath = FileUtils.getFQNPath(fullyQualifiedNameLastExpression);
                            Path classFileLastExpressionPath = srcDirPath.resolve(fullyQualifiedClassNameLastExpressionPath);
                            cuLastStatementClassFile = javaParser.parse(classFileLastExpressionPath).getResult().orElseThrow(() -> new IllegalStateException(String.format("Cannot parse class file %s of last statement.", fullyQualifiedClassNameLastExpressionPath.toString())));
                            togaErrBuilder.append(generateLogTogaInputError(testPrefix, fullyQualifiedClassName, "WARNING", new IllegalStateException("[WARNING] - The last statement of the test is part of another class with respect to the class under test. Check if the focal method is correct.")));
                        }
                    } catch (UnsolvedSymbolException | IllegalStateException e) {}

                    // Get the focal method
                    Pair<CallableDeclaration, List<Exception>> result = getCallableFocalMethod(cuLastStatementClassFile, lastExpression);
                    focalMethod = result.a;
                    togaErrBuilder.append(result.b.stream().map(e -> generateLogTogaInputError(testPrefix, fullyQualifiedClassName, "WARNING", e)).collect(Collectors.joining()));
                    // If the focal method is still not found, search it within the inherited methods
                    if (focalMethod == null) {
                        Pair<MethodUsage,List<Exception>> resultMethodUsage = searchCandidateMethodUsage(cuLastStatementClassFile, lastExpression);
                        MethodUsage focalMethodUsage = resultMethodUsage.a;
                        togaErrBuilder.append(resultMethodUsage.b.stream().map(e -> generateLogTogaInputError(testPrefix, fullyQualifiedClassName, "WARNING", e)).collect(Collectors.joining()));
                        getMethodSignature(focalMethodUsage);
                        // If the focal method is found, get the information of the focal method
                        methodName = focalMethodUsage.getName();
                        focalMethodSignature = getMethodSignature(focalMethodUsage);
                        Matcher matcher = FOCAL_METHOD.matcher(focalMethodUsage.toString());
                        if (matcher.find()) {
                            // Extract the Javadoc comment
                            if (matcher.group(1) != null) {
                                javadocString = matcher.group(1).replace("\"", "\"\"").trim();;
                            }
                            focalMethodBody = matcher.group(2).replace("\"", "\"\"").trim();
                        }
                    } else {
                        methodName = focalMethod.getNameAsString();
                        focalMethodSignature = getCallableSignature(focalMethod);
                        Matcher matcher = FOCAL_METHOD.matcher(focalMethod.toString());
                        if (matcher.find()) {
                            // Extract the Javadoc comment
                            if (matcher.group(1) != null) {
                                javadocString = matcher.group(1).replace("\"", "\"\"").trim();;
                            }
                            focalMethodBody = matcher.group(2).replace("\"", "\"\"").trim();
                        }
                    }
                    // If the focal method is found, get the test and the Javadoc of the focal method
                    if (focalMethod != null) {
                        List<MethodDeclaration> tests = cuTestClassFile
                                .findAll(MethodDeclaration.class)
                                .stream()
                                .filter(t -> t.getNameAsString().equals(testName))
                                .toList();
                        if (tests.size() > 0) {
                            MethodDeclaration test = tests.get(0);
                            String testStr = test.toString().replace("\"", "\"\"");
                            String testPrefixStr = testPrefix.toString();
                            Matcher matcher = testPrefixPattern.matcher(testPrefix.toString());
                            if (matcher.find()) {
                                int startIdx = matcher.start();
                                testStr = testStr.substring(startIdx);
                            }
                            togaInputBuilder.append(String.format("\"%s\",\"%s\",\"%s\"\n", focalMethodBody, testStr, javadocString));
                            togaMetadataBuilder.append(String.format("project,0,%s,0,0,False,\"\",\"\"\n", testName));
                            togaRow.put("className", cuClassFile.getPrimaryType().orElseThrow().getFullyQualifiedName().orElseThrow());
                            togaRow.put("methodName", methodName);
                            togaRow.put("methodSignature", focalMethodSignature);
                            togaRow.put("testPrefix", testPrefixStr);
                            togaRow.put("testName", testName);
                            togaInfo.put(testName, togaRow);
                        }
                    } else {
                        Pair<MethodUsage,List<Exception>> resultMethodUsage = searchCandidateMethodUsage(cuLastStatementClassFile, lastExpression);
                        MethodUsage focalMethodUsage = resultMethodUsage.a;
                        togaErrBuilder.append(resultMethodUsage.b.stream().map(e -> generateLogTogaInputError(testPrefix, fullyQualifiedClassName, "WARNING", e)).collect(Collectors.joining()));
                        getMethodSignature(focalMethodUsage);
                    }
                } catch (IllegalStateException e) {
                    togaErrBuilder.append(generateLogTogaInputError(testPrefix, fullyQualifiedClassName, "ERROR", e));
                    System.err.println(e.getMessage());
                } catch (Exception e) {
                    togaErrBuilder.append(generateLogTogaInputError(testPrefix, fullyQualifiedClassName, "ERROR",e));
                    String errMsg = String.format(
                            "Focal method not found in test %s for class %s",
                            testPrefix.getNameAsString(),
                            fullyQualifiedClassName
                    );
                    System.err.println(errMsg);
                }
            });
            FileUtils.writeJSON(togaInfoPath, togaInfo);
            FileUtils.writeString(togaInputPath, togaInputBuilder.toString());
            FileUtils.writeString(togaMetadataPath, togaMetadataBuilder.toString());
            FileUtils.writeString(togaErrorPath, togaErrBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
            throw new Error(e.getMessage());
        }
    }

    /**
     * Logs error in parsing prefix test and generating TOGA input.
     *
     * @param testPrefix the test prefix that triggers an error.
     * @param fullyQualifiedClassName the fully qualified name of the class under test
     * @param errorType the type of error
     * @param e the Exception
     * @return A string in the forms of a csv row (separated by semicolons), containing the
     * test prefix, the fully qualified name, and the error.
     */
    private static String generateLogTogaInputError(MethodDeclaration testPrefix, String fullyQualifiedClassName, String errorType, Exception e) {
        String testPrefixStr = testPrefix.toString().replace("\"", "\"\"");
        return String.format("%s,%s,%s,%s,%s\n", fullyQualifiedClassName, testPrefixStr, errorType, e.getClass().getName(), e.getMessage());
    }

    /**
     * Gets the Javadoc comment of a given function.
     *
     * @param jpCallable a JavaParser function
     * @return the method/constructor Javadoc comment
     */
    public static String getCallableJavadoc(
            CallableDeclaration<?> jpCallable
    ) {
        Optional<JavadocComment> optionalJavadocComment = jpCallable.getJavadocComment();
        return optionalJavadocComment
                .map(javadocComment -> "    /**" + javadocComment.getContent() + "*/")
                .orElseGet(() -> getJavadocByPattern(jpCallable));
    }

    /**
     * Gets the Javadoc comment of a body declaration using regex patterns.
     * Use ONLY IF Javadoc comment is not recoverable using JavaParser API.
     *
     * @param jpBody a member in a Java class
     * @return the matched Javadoc comment (empty string if not found)
     */
    private static String getJavadocByPattern(BodyDeclaration<?> jpBody) {
        String input = jpBody.toString();
        Pattern pattern = Pattern.compile("/\\*\\*(.*?)\\*/", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            String content = matcher.group(1);
            if (jpBody instanceof TypeDeclaration<?>) {
                // class javadoc format
                return "/**" + content + "*/";
            } else {
                // method javadoc format
                return "    /**" + content + "*/";
            }
        }
        return "";
    }

    public static void countTestMethods(Path srcDirPath, String projectName, String projectBugID, String fullyQualifiedClassName) {
        javaParser = getJavaParser(srcDirPath);
        Path filePath = output.resolve(Paths.get("test_method_count.json"));
        List<TestMethodsCount> testMethodsCountList = new ArrayList<>();
        try {
            testMethodsCountList = FileUtils.readJSONList(filePath, TestMethodsCount.class);
        } catch (Error e) {}

        Path fullyQualifiedClassNamePath = FileUtils.getFQNPath(fullyQualifiedClassName);
        String className = FileUtils.getSimpleNameFromFQN(fullyQualifiedClassName);
        int classNameIdx = fullyQualifiedClassNamePath.getNameCount() - 1;
        Path fullyQualifiedTestClassNamePath = classNameIdx > 0 ?
                fullyQualifiedClassNamePath.subpath(0, classNameIdx).resolve(className + "_ESTest.java") :
                Paths.get(className);
        Path testClassFilePath = evosuitePrefixPath.resolve(fullyQualifiedTestClassNamePath);
        try {
            CompilationUnit cuTestClassFile = javaParser.parse(testClassFilePath).getResult().orElseThrow();
            int methodsNum = cuTestClassFile.findAll(MethodDeclaration.class).size();
            testMethodsCountList.add(new TestMethodsCount(fullyQualifiedClassName, projectName, projectBugID, methodsNum));
            FileUtils.writeJSON(filePath, testMethodsCountList);
        } catch (IOException e) {
            e.printStackTrace();
            throw new Error(e.getMessage());
        }
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
        String method = methodDeclaration.toString();
        if (methodDeclaration.getBody().isPresent()) {
            // Remove body
            method = method.replace(methodDeclaration.getBody().get().toString(), "");
        }
        for (Node comment: methodDeclaration.getAllContainedComments()) {
            // Remove comments within method signature
            method = method.replace(comment.toString(), "");
        }
        // Last line is method signature, remove everything before that
        method = method.replaceAll("[\\s\\S]*\n", "");
        return method.trim().replaceAll(";$", "");
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
                .map(com.github.javaparser.ast.body.Parameter::toString)
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
            return getMethodSignature(jpMethod);
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
        return mapList(TogUtils::getTypeWithoutPackages, exceptions);
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
     * Transforms the output from the TOGA script (a .csv file where each row contains the
     * assertion for a given focal method and test prefix) into a json file containing a
     * corresponding list of {@link OracleOutput} records.
     * Saves the output file to "output/toga-oracles/package-name/MyClass_Oracle.json".
     * @see OracleOutput
     * @param togaPath the output JSON file generated by JDoctor
     */
    public static void togaToOracleOutput(Path togaPath) {
        String className = getClassNameFromJDoctorOutput(togaPath);
        Path oraclePath = togaPath.resolveSibling(className + "_Oracle.json");
        Path inputPath = output.resolve("toga-input");
        TypeReference<Map<String, Map<String, String>>> typeReference = new TypeReference<>(){};
        Map<String, Map<String, String>> togaInfo = (Map<String, Map<String, String>>) FileUtils.readJSON(
                inputPath.resolve("toga_info.json"),
                typeReference
        );
        CSVParser csvParser = FileUtils.readCSV(togaPath);
        Map<String, Integer> columnIndexMap = new HashMap<>();
        CSVRecord header = csvParser.iterator().next();
        // Populate the columnIndexMap with column names and their indexes
        for (int i = 0; i < header.size(); i++) {
            columnIndexMap.put(header.get(i), i);
        }
        List<OracleOutput> oracleOutputs = new ArrayList<>();
        for (CSVRecord record : csvParser) {
            String testName = record.get(columnIndexMap.get("test_name"));
            String exceptPred = record.get(columnIndexMap.get("except_pred"));
            String assertPred = record.get(columnIndexMap.get("assert_pred"));
            Map<String,String> togaTest = togaInfo.get(testName);
            boolean isException = Boolean.parseBoolean(exceptPred);
            OracleOutput oracleOutput = new OracleOutput(
                    togaTest.get("className"),
                    togaTest.get("methodSignature"),
                    OracleType.NON_AXIOMATIC,
                    assertPred,
                    isException ? "java.lang.Exception" : "",
                    testName
            );
            oracleOutputs.add(oracleOutput);
        }
        FileUtils.writeJSON(oraclePath, oracleOutputs);
    }

    /**
     * Replaces all instances of {@code args[i]} in an oracle with the
     * corresponding parameter names from the method signature.
     *
     * @param jDoctorOutput the parent JDoctor condition of {@code oracle}
     * @param oracle an oracle generated by JDoctor
     * @return the same oracle with parameter names from the method signature.
     * Returns null if unable to parse oracle string.
     */
    private static String contextualizeOracle(JDoctorOutput jDoctorOutput, String oracle) {
        // add clause to ternary statements if necessary
        if (oracle.contains("?") && !oracle.contains(":")) {
            oracle += ": true";
        }
        Expression oracleExpression;
        try {
            oracleExpression = StaticJavaParser.parseExpression(oracle);
        } catch (ParseProblemException e) {
            System.err.println("Unable to parse oracle " + oracle);
            System.err.println(e.getMessage());
            return null;
        }
        List<String> parameterNames = jDoctorOutput.parameters()
                .stream()
                .map(Parameter::name)
                .toList();
        oracleExpression.walk(ArrayAccessExpr.class, arrayAccess -> {
            if (arrayAccess.getName().toString().equals("args")) {
                int index = Integer.parseInt(arrayAccess.getIndex().toString());
                arrayAccess.replace(new NameExpr(parameterNames.get(index)));
            }
        });
        return oracleExpression.toString();
    }

    /**
     * Converts a JDoctor ParamTag to an OracleOutput record.
     *
     * @param jDoctorOutput the parent JDoctor condition of {@code paramTag}
     * @param paramTag a JDoctor precondition
     * @return the corresponding OracleOutput of {@code paramTag}. Returns
     * null if the oracle is an empty string.
     */
    private static OracleOutput paramTagToOracleOutput(JDoctorOutput jDoctorOutput, ParamTag paramTag) {
        if (paramTag == null || paramTag.condition().isEmpty()) {
            return null;
        }
        String oracle = contextualizeOracle(jDoctorOutput, paramTag.condition());
        if (oracle == null) {
            return null;
        }
        return new OracleOutput(
                jDoctorOutput.targetClass(),
                jDoctorOutput.signature(),
                OracleType.PRE,
                oracle,
                "",
                ""
        );
    }

    /**
     * Converts a JDoctor ReturnTag to an OracleOutput record.
     *
     * @param jDoctorOutput the parent JDoctor condition of {@code returnTag}
     * @param returnTag a JDoctor normal post-condition
     * @return the corresponding OracleOutput of {@code returnTag}. Returns
     * null if {@code returnTag} is null or the oracle is an empty string.
     */
    private static OracleOutput returnTagToOracleOutput(JDoctorOutput jDoctorOutput, ReturnTag returnTag) {
        if (returnTag == null || returnTag.condition().isEmpty()) {
            return null;
        }
        String oracle = contextualizeOracle(jDoctorOutput, returnTag.condition());
        if (oracle == null) {
            return null;
        }
        return new OracleOutput(
                jDoctorOutput.targetClass(),
                jDoctorOutput.signature(),
                OracleType.NORMAL_POST,
                oracle,
                "",
                ""
        );
    }

    /**
     * Converts a JDoctor ThrowsTag to an OracleOutput record.
     *
     * @param jDoctorOutput the parent JDoctor condition of {@code throwsTag}
     * @param throwsTag a JDoctor exceptional post-condition
     * @return the corresponding OracleOutput of {@code throwsTag}. Returns
     * null if the oracle is an empty string.
     */
    private static OracleOutput throwsTagToOracleOutput(JDoctorOutput jDoctorOutput, ThrowsTag throwsTag) {
        if (throwsTag == null || throwsTag.condition().isEmpty()) {
            return null;
        }
        String oracle = contextualizeOracle(jDoctorOutput, throwsTag.condition());
        if (oracle == null) {
            return null;
        }
        return new OracleOutput(
                jDoctorOutput.targetClass(),
                jDoctorOutput.signature(),
                OracleType.EXCEPT_POST,
                oracle,
                throwsTag.exceptionType().fullyQualifiedName(),
                ""
        );
    }

    /**
     * Converts a JDoctorOutput record to a list of OracleOutput records. A
     * single JDoctorOutput record may contain multiple preconditions, throws
     * conditions, and post-conditions, each necessitating an individual
     * OracleOutput record.
     *
     * @param jDoctorOutput a JDoctor JSON condition
     * @return the corresponding OracleOutputs from the condition
     */
    private static List<OracleOutput> conditionToOracleOutput(JDoctorOutput jDoctorOutput) {
        List<OracleOutput> oracleOutputs = new ArrayList<>();
        jDoctorOutput.paramTags().forEach(paramTag -> oracleOutputs.add(
                paramTagToOracleOutput(jDoctorOutput, paramTag)
        ));
        oracleOutputs.add(
                returnTagToOracleOutput(jDoctorOutput, jDoctorOutput.returnTag())
        );
        jDoctorOutput.throwsTags().forEach(throwsTag -> oracleOutputs.add(
                throwsTagToOracleOutput(jDoctorOutput, throwsTag)
        ));
        return oracleOutputs
                .stream()
                .filter(Objects::nonNull)
                .toList();
    }

    /**
     * Gets the simple class name from the path to a JDoctor output. The
     * output path is presumed to follow the format:
     * "output/jdoctor-oracles/package-name/MyClass_jdoctor_output.json"
     *
     * @param jDoctorPath a path to JDoctor output
     * @return the simple clas name
     */
    private static String getClassNameFromJDoctorOutput(Path jDoctorPath) {
        String fileName = jDoctorPath.getFileName().toString();
        String[] fileSegments = fileName.split("_");
        return fileSegments[0];
    }

    /**
     * Converts the JSON output generated by JDoctor into a list of
     * {@link OracleOutput} records. Saves the output to
     * "output/jdoctor-oracles/package-name/MyClass_Oracle.json"
     *
     * @param jDoctorPath the output JSON file generated by JDoctor
     */
    public static void jDoctorToOracleOutput(Path jDoctorPath) {
        String className = getClassNameFromJDoctorOutput(jDoctorPath);
        Path oraclePath = jDoctorPath.resolveSibling(className + "_Oracle.json");
        List<JDoctorOutput> jDoctorOutputs = FileUtils.readJSONList(jDoctorPath, JDoctorOutput.class);
        List<OracleOutput> oracleOutputs = jDoctorOutputs
                .stream()
                .map(TogUtils::conditionToOracleOutput)
                .flatMap(List::stream)
                .toList();
        FileUtils.writeJSON(oraclePath, oracleOutputs);
    }

    /**
     * Transforms the output from the Tratto script into a json file containing a
     * corresponding list of {@code OracleOutput} objects.
     * Saves the output file in output/tratto/experiment.
     * @see OracleOutput
     * @param trattoPath the output JSON file generated by Tratto
     * @param srcDirPath the path to the source code of the project under test
     */
    public static void trattoToOracleOutput(Path trattoPath, Path srcDirPath) {
        javaParser = getJavaParser(srcDirPath);
        String className = getClassNameFromJDoctorOutput(trattoPath);
        Path oraclePath = trattoPath.resolveSibling(className + "_Oracle.json");
        List<TrattoOutput> trattoOutputs = FileUtils.readJSONList(
                trattoPath,
                TrattoOutput.class
        );
        List<OracleOutput> oracleOutputs = new ArrayList<>();
        for (TrattoOutput trattoOutput : trattoOutputs) {
            // Parse the method string as a compilation unit
            CompilationUnit cu = javaParser.parse("public class " + trattoOutput.className() + "{ " + trattoOutput.methodSourceCode() + " }").getResult().orElseThrow();
            CallableDeclaration<?> mut = cu.findFirst(CallableDeclaration.class).orElseThrow();
            String signature = getCallableSignature(mut);
            OracleOutput oracleOutput = new OracleOutput(
                    trattoOutput.className(),
                    signature,
                    trattoOutput.oracleType(),
                    trattoOutput.oracleType() != OracleType.EXCEPT_POST ? trattoOutput.oracle() : "",
                    trattoOutput.oracleType() == OracleType.EXCEPT_POST ? trattoOutput.oracle() : "",
                    ""
            );
            oracleOutputs.add(oracleOutput);
        }
        FileUtils.writeJSON(oraclePath, oracleOutputs);
    }

    /**
     * Gets the substring between two specified words in a given input string.
     * If the prefix or suffix occur multiple times, this method will return
     * the substring between the first instance of the prefix and the first
     * instance of the suffix. If prefix is null, uses the start of the base
     * string. If the suffix is null, uses the end of the base string.
     *
     * @param base the input string from which to extract the substring
     * @param prefix the word that marks the start of the substring
     * @param suffix the word that marks the end of the substring
     * @return the substring between the starting and ending words
     * @throws Error if either the prefix or the suffix do not appear in the
     * base string
     */
    private static String getSubstringBetweenWords(String base, String prefix, String suffix) {
        int beginIdx = 0;
        int endIdx = base.length();
        if (prefix != null) {
            beginIdx = base.indexOf(prefix);
            if (beginIdx == -1) {
                throw new Error("Unable to find delimiter \"" + prefix + "\" in: " + base);
            }
            beginIdx += prefix.length();
        }
        if (suffix != null) {
            endIdx = base.indexOf(suffix);
            if (endIdx == -1) {
                throw new Error("Unable to find delimiter \"" + suffix + "\" in: " + base);
            }
        }
        return base.substring(beginIdx, endIdx);
    }

    /**
     * Gets the failing tests from a Defects4J bug detection log. Each log
     * denotes a failing test in the form:
     * <pre>
     * {@code --- [fullyQualifiedName]_ESTest::[testName]}
     * </pre>
     *
     * @param logPath the path to a bug detection log
     * @return all failing tests for the bug
     */
    private static List<String> getFailingTestsFromLog(Path logPath) {
        return Arrays.stream(FileUtils.readString(logPath)
                .split("\n"))
                .toList()
                .stream()
                .filter(l -> l.startsWith("---"))
                .map(l -> getSubstringBetweenWords(l, "--- ", null))
                .collect(Collectors.toList());
    }

    private static Map<String, List<String>> getFailingTests(
            Path resultsDir,
            boolean isBuggyVersion
    ) {
        Map<String, List<String>> allFailingTests = new HashMap<>();
        String bugSuffix = isBuggyVersion ? "b" : "f";
        String logSuffix = bugSuffix + ".1.trigger.log";
        try (Stream<Path> walk = Files.walk(resultsDir)) {
            walk
                    .filter(p -> p.toString().endsWith(logSuffix))
                    .forEach(p -> {
                        String projectName = getSubstringBetweenWords(p.toString(), "bug_detection_log/", "/evosuite");
                        String bugNumber = getSubstringBetweenWords(p.toString(), "evosuite/", logSuffix);
                        String bugKey = projectName + "_" + bugNumber;
                        List<String> failingTests = getFailingTestsFromLog(p);
                        allFailingTests.put(bugKey, failingTests);
                    });
        } catch (IOException e) {
            throw new Error("Unable to traverse directory " + resultsDir, e);
        }
        return allFailingTests;
    }

    private static List<String> getInvalidTestsFromLog(Path logPath) {
        List<String> logLines = Arrays.stream(FileUtils.readString(logPath).split("\n")).toList();
        List<Integer> invalidTestIdxs = logLines
                .stream()
                .filter(l -> l.equals("java.lang.Error: TrattoError: Precondition failed, invalid test."))
                .map(l -> logLines.indexOf(l) - 1)
                .toList();
        return logLines
                .stream()
                .filter(l -> invalidTestIdxs.contains(logLines.indexOf(l)))
                .map(l -> getSubstringBetweenWords(l, "--- ", null))
                .collect(Collectors.toList());
    }

    private static String getBugNumber(String fileName) {
        int endIdx = 0;
        for (int i = 0; i < fileName.length(); i++) {
            if (!Character.isDigit(fileName.charAt(i))) {
                endIdx = i;
                break;
            }
        }
        return fileName.substring(0, endIdx);
    }

    private static Map<String, List<String>> getInvalidTests(Path resultsDir) {
        Map<String, List<String>> allInvalidTests = new HashMap<>();
        try (Stream<Path> walk = Files.walk(resultsDir)) {
            walk
                    .filter(p -> p.toString().endsWith("1.trigger.log"))
                    .forEach(p -> {
                        String projectName = getSubstringBetweenWords(p.toString(), "bug_detection_log/", "/evosuite");
                        String bugNumber = getBugNumber(p.getFileName().toString());
                        String bugKey = projectName + "_" + bugNumber;
                        List<String> invalidTests = getInvalidTestsFromLog(p);
                        allInvalidTests.put(bugKey, invalidTests);
                    });
        } catch (IOException e) {
            throw new Error("Unable to traverse directory " + resultsDir, e);
        }
        return allInvalidTests;
    }

    private static String getBugKeyFromTestPath(Path p) {
        List<String> pathSegments = Arrays.stream(p.toString().split("/")).collect(Collectors.toList());
        List<String> outputPathSegments = pathSegments.subList(pathSegments.indexOf("output"), pathSegments.size());
        String projectName = outputPathSegments.get(2);
        String bugNumber = outputPathSegments.get(3);
        return projectName + "_" + bugNumber;
    }

    private static List<String> getAllTestsFromFile(Path testFile, String bugNumber) {
        String fullyQualifiedName = getSubstringBetweenWords(testFile.toString(), bugNumber + "/", ".java")
                .replaceAll("/", ".");
        CompilationUnit cu;
        try {
            cu = StaticJavaParser.parse(testFile);
        } catch (IOException e) {
            throw new Error("Unable to parse test file " + testFile, e);
        }
        List<String> methodNames = cu.findAll(MethodDeclaration.class)
                .stream()
                .map(NodeWithSimpleName::getNameAsString)
                .filter(m -> m.startsWith("test"))
                .toList();
        return methodNames
                .stream()
                .map(m -> fullyQualifiedName + "::" + m)
                .collect(Collectors.toList());
    }

    private static Map<String, List<String>> getAllTests(Path testDir) {
        Map<String, List<String>> allTests = new HashMap<>();
        try (Stream<Path> walk = Files.walk(testDir)) {
            walk
                    .filter(p -> !Files.isDirectory(p) && !FileUtils.isScaffolding(p))
                    .forEach(p -> {
                        String bugKey = getBugKeyFromTestPath(p);
                        String bugNumber = getSubstringBetweenWords(bugKey, "_", null);
                        List<String> bugTests = getAllTestsFromFile(p, bugNumber);
                        if (allTests.containsKey(bugKey)) {
                            // group tests from all modified classes of a bug
                            List<String> otherTests = allTests.get(bugKey);
                            otherTests.addAll(bugTests);
                            allTests.put(bugKey, otherTests);
                        } else {
                            allTests.put(bugKey, bugTests);
                        }
                    });
        } catch (IOException e) {
            throw new Error("Unable to traverse directory " + testDir, e);
        }
        return allTests;
    }

    private static String classifyTest(
            String test,
            List<String> buggyFailingTests,
            List<String> fixedFailingTests,
            List<String> invalidTests
    ) {
        String trueFalse;
        String positiveNegative;
        if (invalidTests.contains(test)) {
            return "INV";
        }
        if (buggyFailingTests.contains(test)) {
            positiveNegative = "P";
        } else {
            positiveNegative = "N";
        }
        if (fixedFailingTests.contains(test)) {
            trueFalse = "F";
        } else {
            trueFalse = "T";
        }
        return trueFalse + positiveNegative;
    }

    private static Defects4JOutput getDefects4JOutput(
            TogType tog,
            Map<String, List<String>> allTests,
            Map<String, List<String>> allBuggyFailingTests,
            Map<String, List<String>> allFixedFailingTests,
            Map<String, List<String>> allInvalidTests
    ) {
        int numBugsFound = 0;
        int numTruePositive = 0;
        int numFalsePositive = 0;
        int numTrueNegative = 0;
        int numFalseNegative = 0;
        int numInvalid = 0;
        for (String bugKey : allTests.keySet()) {
            boolean bugFound = false;
            List<String> tests = allTests.get(bugKey);
            List<String> buggyFailingTests = allBuggyFailingTests.get(bugKey);
            List<String> fixedFailingTests = allFixedFailingTests.get(bugKey);
            List<String> invalidTests = allInvalidTests.get(bugKey);
            for (String test : tests) {
                String testClassification = classifyTest(test, buggyFailingTests, fixedFailingTests, invalidTests);
                switch (testClassification) {
                    case "TP" -> numTruePositive += 1;
                    case "FP" -> numFalsePositive += 1;
                    case "TN" -> numTrueNegative += 1;
                    case "FN" -> numFalseNegative += 1;
                    case "INV" -> numInvalid += 1;
                }
                if (testClassification.equals("TP")) {
                    bugFound = true;
                }
            }
            if (bugFound) {
                numBugsFound += 1;
            }
        }
        return new Defects4JOutput(
                tog.toString(),
                numBugsFound,
                numTruePositive,
                numFalsePositive,
                numTrueNegative,
                numFalseNegative,
                numInvalid
        );
    }

    /**
     * Generates the Defects4JOutput record to get statistics for a test suite
     * generated by a TOG for the Defects4J database.
     *
     * @param tog a TOG
     * @param testDir test suite directory
     * @param resultsDir test output directory
     */
    public static void generateDefects4JOutput(
            TogType tog,
            Path testDir,
            Path resultsDir
    ) {
        Map<String, List<String>> allTests = getAllTests(testDir);
        Map<String, List<String>> buggyFailingTests = getFailingTests(resultsDir, true);
        Map<String, List<String>> fixedFailingTests = getFailingTests(resultsDir, false);
        Map<String, List<String>> invalidTests = getInvalidTests(resultsDir);
        Defects4JOutput defects4JOutput = getDefects4JOutput(
                tog,
                allTests,
                buggyFailingTests,
                fixedFailingTests,
                invalidTests
        );
        Path defects4JOutputPath = testDir.resolveSibling(tog.toString().toLowerCase() + "_defects4joutput.json");
        FileUtils.writeJSON(defects4JOutputPath, defects4JOutput);
    }
}
