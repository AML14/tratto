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
import com.github.javaparser.ast.type.ReferenceType;
import com.github.javaparser.ast.type.TypeParameter;
import com.github.javaparser.resolution.SymbolResolver;
import com.github.javaparser.resolution.UnsolvedSymbolException;
import com.github.javaparser.symbolsolver.utils.SymbolSolverCollectionStrategy;
import data.JDoctorOutput;
import data.JDoctorOutput.Parameter;
import data.JDoctorOutput.ParamTag;
import data.JDoctorOutput.ReturnTag;
import data.JDoctorOutput.ThrowsTag;
import data.TrattoOutput;
import data.OracleOutput;
import data.OracleType;
import data.TestCase;
import data.TestOutput;
import data.TogType;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

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

    /** Private constructor to avoid creating an instance of this class. */
    private TogUtils() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
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
        Path prefixPath = output.resolve(Paths.get("toga", "input"));
        Path errorPath = output.resolve(Paths.get("toga", "err"));
        Path togaInputPath = prefixPath.resolve("toga_input.csv");
        Path togaMetadataPath = prefixPath.resolve("toga_metadata.csv");
        Path togaInfoPath = prefixPath.resolve("toga_info.json");
        Path fullyQualifiedClassNamePath = FileUtils.getFQNPath(fullyQualifiedClassName);
        Path togaErrorPath = errorPath.resolve(fullyQualifiedClassNamePath.resolve("err.csv"));
        String className = FileUtils.getSimpleNameFromFQN(fullyQualifiedClassName);
        int classNameIdx = fullyQualifiedClassNamePath.getNameCount() - 1;
        Path fullyQualifiedTestClassNamePath = classNameIdx > 0 ?
                fullyQualifiedClassNamePath.subpath(0, classNameIdx).resolve(className + "Test.java") :
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
            CompilationUnit cuClassFile = javaParser.parse(classFilePath).getResult().orElseThrow();
            CompilationUnit cuTestClassFile = javaParser.parse(testClassFilePath).getResult().orElseThrow();
            CompilationUnit cuTestClassPrefixFile = javaParser.parse(testClassPrefixFilePath).getResult().orElseThrow();
            Map<String, Map<String,String>> togaInfo = new HashMap<>();
            StringBuilder togaInputBuilder = new StringBuilder();
            StringBuilder togaMetadataBuilder = new StringBuilder();
            StringBuilder togaErrBuilder = new StringBuilder();
            togaInputBuilder.append("focal_method,test_prefix,docstring\n");
            togaMetadataBuilder.append("project,bug_num,test_name,exception_bug,assertion_bug,exception_lbl,assertion_lbl,assert_err\n");
            cuTestClassPrefixFile.findAll(MethodDeclaration.class).forEach(testPrefix -> {
                CallableDeclaration mut = null;
                String testName = testPrefix.getNameAsString();
                Map<String, String> togaRow = new HashMap<>();
                try {
                    List<MethodCallExpr> methodCallExpressions = testPrefix.getBody().orElseThrow().findAll(MethodCallExpr.class);
                    if (methodCallExpressions.size() > 0) {
                        MethodCallExpr lastMethodCallExpression = methodCallExpressions.get(methodCallExpressions.size() - 1);

                        try {
                            mut = (MethodDeclaration) lastMethodCallExpression.resolve().toAst().orElse(null);
                        } catch (UnsolvedSymbolException e) {
                            mut = null;
                        }

                        if (mut == null) {
                            List<MethodDeclaration> classMethodsList = cuClassFile.getPrimaryType().get().getMethods();
                            List<MethodDeclaration> candidatesMethods = new ArrayList<>();

                            for (MethodDeclaration method : classMethodsList) {
                                // Check if the method name is the same
                                if (method.getNameAsString().equals(lastMethodCallExpression.getNameAsString())) {
                                    // Check if the number of parameters is the same
                                    if (method.getParameters().size() == lastMethodCallExpression.getArguments().size()) {
                                        // Add method to candidates if the previous conditions are satisfied
                                        candidatesMethods.add(method);
                                    }
                                }
                            }

                            if (candidatesMethods.size() == 0) {
                                throw new NoSuchElementException();
                            } else if (candidatesMethods.size() == 1) {
                                mut = candidatesMethods.get(0);
                            } else {
                                int bestParamsTypesInCommon = -1;
                                boolean multipleCandidates = false;
                                MethodDeclaration mostSimilar = null;
                                for (MethodDeclaration method : candidatesMethods) {
                                    NodeList<com.github.javaparser.ast.body.Parameter> paramsList = method.getParameters();
                                    int paramsTypesInCommon = 0;
                                    for (int i = 0; i < paramsList.size(); i++) {
                                        com.github.javaparser.ast.body.Parameter param = paramsList.get(i);
                                        String lastMethodTypeName = lastMethodCallExpression.getArguments().get(i).calculateResolvedType().describe();
                                        String paramTypeName = param.getTypeAsString();
                                        if (lastMethodTypeName.endsWith(paramTypeName)) {
                                            paramsTypesInCommon += 1;
                                        }
                                    }
                                    if (paramsTypesInCommon >= bestParamsTypesInCommon) {
                                        if (paramsTypesInCommon == bestParamsTypesInCommon) {
                                            multipleCandidates = true;
                                        } else {
                                            bestParamsTypesInCommon = paramsTypesInCommon;
                                            multipleCandidates = false;
                                            mostSimilar = method;
                                        }
                                    }
                                }
                                if (!(multipleCandidates || mostSimilar == null)) {
                                    mut = mostSimilar;
                                } else {
                                    // Multiple candidates, impossible to discern
                                    throw new NoSuchElementException();
                                }
                            }
                        }
                    } else {
                        List<ObjectCreationExpr> constructorCallExpressions = testPrefix.getBody().get().findAll(ObjectCreationExpr.class);
                        if (constructorCallExpressions.size() > 0 ) {
                            ObjectCreationExpr lastConstructorCallExpression = constructorCallExpressions.get(constructorCallExpressions.size() - 1);
                            try {
                                mut = (ConstructorDeclaration) lastConstructorCallExpression.resolve().toAst().orElse(null);
                            } catch (UnsolvedSymbolException e) {
                                mut = null;
                            }
                        } else {
                            String testPrefixStr = testPrefix.toString().replace("\"", "\"\"");
                            togaErrBuilder.append(generateLogTogaInputError(testPrefix, fullyQualifiedClassName, "NoMethodCallException"));
                            String errMsg = String.format(
                                    "Method call not found in test %s for class %s",
                                    testPrefix.getNameAsString(),
                                    fullyQualifiedClassName
                            );
                            System.err.println(errMsg);
                        }
                    }

                    if (mut != null) {
                        MethodDeclaration test = cuTestClassFile
                                .findAll(MethodDeclaration.class)
                                .stream()
                                .filter(t -> t.getNameAsString().equals(testName))
                                .toList()
                                .get(0);
                        String methodName = mut.getNameAsString();
                        String focalMethod = getCallableSignature(mut);
                        String javadocString = getCallableJavadoc(mut).replace("\"", "\"\"");
                        String testStr = test.toString().replace("\"", "\"\"");
                        String testPrefixStr = testPrefix.toString();
                        Matcher matcher = testPrefixPattern.matcher(testPrefix.toString());
                        if (matcher.find()) {
                            int startIdx = matcher.start();
                            testStr = testStr.substring(startIdx);
                        }
                        togaInputBuilder.append(String.format("\"%s {}\",\"%s\",\"%s\"\n", focalMethod, testStr, javadocString));
                        togaMetadataBuilder.append(String.format("project,0,%s,0,0,False,\"\",\"\"\n", testName));
                        togaRow.put("className", cuClassFile.getPrimaryType().orElseThrow().getFullyQualifiedName().orElseThrow());
                        togaRow.put("methodName", methodName);
                        togaRow.put("methodSignature", focalMethod);
                        togaRow.put("testPrefix", testPrefixStr);
                        togaRow.put("testName", testName);
                        togaInfo.put(testName, togaRow);
                    }
                } catch (NoSuchElementException | UnsolvedSymbolException e) {
                    togaErrBuilder.append(generateLogTogaInputError(testPrefix, fullyQualifiedClassName, e.getClass().getName()));
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
     * @param errStr the error to log
     * @return A string in the forms of a csv row (separated by semicolons), containing the
     * test prefix, the fully qualified name, and the error.
     */
    private static String generateLogTogaInputError(MethodDeclaration testPrefix, String fullyQualifiedClassName, String errStr) {
        String testPrefixStr = testPrefix.toString().replace("\"", "\"\"");
        return String.format("%s,%s,%s\n", fullyQualifiedClassName, testPrefixStr, errStr);
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
     * Transforms the output from the TOGA script (a .csv file where each row contains the
     * assertion for a given focal method and test prefix) into a json file containing a
     * corresponding list of {@code OracleOutput} objects.
     * Saves the output file in output/toga/oracle.
     * @see OracleOutput
     * @param srcDirPath the path to the source code of the project under test
     */
    public static void togaToOracleOutput(Path srcDirPath) {
        javaParser = getJavaParser(srcDirPath);
        Path prefixPath = output.resolve(Paths.get("toga", "oracle"));
        Path inputPath = output.resolve(Paths.get("toga", "input"));
        Path outputPath = output.resolve(Paths.get("toga", "output"));
        TypeReference<Map<String, Map<String, String>>> typeReference = new TypeReference<>(){};
        Map<String, Map<String, String>> togaInfo = (Map<String, Map<String, String>>) FileUtils.readJSON(
                inputPath.resolve("toga_info.json"),
                typeReference
        );
        CSVParser csvParser = FileUtils.readCSV(outputPath.resolve("oracle_preds.csv"));
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
        FileUtils.writeJSON(prefixPath.resolve("oracle_output.json"), oracleOutputs);
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
     * Converts the JSON output generated by JDoctor into a list of
     * {@link OracleOutput} records. Saves the output to
     * "output/jdoctor/oracle".
     *
     * @see OracleOutput
     */
    public static void jDoctorToOracleOutput() {
        Path jDoctorPath = output.resolve(Paths.get("jdoctor", "output", "jdoctor_output.json"));
        Path oraclePath = output.resolve(Paths.get("jdoctor", "oracle", "oracle_output.json"));
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
     * @param srcDirPath the path to the source code of the project under test
     */
    public static void trattoToOracleOutput(Path srcDirPath) {
        javaParser = getJavaParser(srcDirPath);
        Path prefixPath = output.resolve(Paths.get("tratto", "oracle"));
        Path outputPath = output.resolve(Paths.get("tratto", "output"));
        List<TrattoOutput> trattoOutputs = FileUtils.readJSONList(
                outputPath.resolve("oracle_datapoints.json"),
                TrattoOutput.class
        );
        List<OracleOutput> oracleOutputs = new ArrayList<>();
        for (TrattoOutput trattoOutput : trattoOutputs) {
            // Parse the method string as a compilation unit
            CompilationUnit cu = javaParser.parse("public class " + trattoOutput.className() + "{ " + trattoOutput.methodSourceCode() + " }").getResult().get();
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
        FileUtils.writeJSON(prefixPath.resolve("oracle_output.json"), oracleOutputs);
    }

    /**
     * Gets the names of all failing tests from an EvoSuite JUnit output. This
     * method assumes that the {@code runner.sh} script has already been run
     * and generated a corresponding {@code test_fails.txt} file.
     *
     * @param failPath the path to all fail lines in the EvoSuite JUnit output
     * @return a list of all failing test names
     * @throws IllegalArgumentException if unable to find the TOG's failing
     * tests, generated by {@code runner.sh}
     */
    private static List<String> getTestFails(
            Path failPath
    ) {
        if (!Files.exists(failPath)) {
            throw new IllegalArgumentException("Unable to find test fails log " + failPath);
        }
        List<String> lines = List.of(FileUtils.readString(failPath).split("\n"));
        List<String> testFails = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\) (\\w+)\\(");
        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                testFails.add(matcher.group(1));
            }
        }
        return testFails;
    }

    private static List<TestCase> getTestCases(
            CompilationUnit cu,
            List<String> testFails
    ) {
        List<TestCase> testCases = new ArrayList<>();
        cu.findAll(MethodDeclaration.class).forEach(test -> {
            if (testFails.contains(test.getNameAsString())) {
                testCases.add(new TestCase(
                        test.getNameAsString(),
                        test.toString(),
                        TestCase.TestResult.FAIL
                ));
            } else {
                testCases.add(new TestCase(
                        test.getNameAsString(),
                        test.toString(),
                        TestCase.TestResult.PASS
                ));
            }
        });
        return testCases;
    }

    private static List<TestCase> getTestCases(
            Path testPath,
            List<String> testFails
    ) {
        List<TestCase> testCases = new ArrayList<>();
        try (Stream<Path> walk = Files.walk(testPath)) {
            walk
                    .filter(FileUtils::isJavaFile)
                    .filter(p -> !FileUtils.isScaffolding(p))
                    .forEach(testFile -> {
                        try {
                            CompilationUnit cu = StaticJavaParser.parse(testFile);
                            testCases.addAll(getTestCases(cu, testFails));
                        } catch (IOException e) {
                            throw new Error("Unable to parse test file " + testFile, e);
                        }
                    });
        } catch (IOException e) {
            throw new Error("Error when parsing files in directory " + testPath, e);
        }
        return testCases;
    }

    /**
     * Writes a {@link TestOutput} object to a given path. The output contains
     * information about the TOG which generated the test, the fully qualified
     * name of the class under test, the project source directory, the project
     * binaries directory, the number of passing test, the number of failing
     * tests, and a list of each test generated by the tog (with a label
     * corresponding to whether the test passes or fails).
     *
     * @param togType the tog which generated the tests
     * @param className the fully qualified name of the class under test
     * @param srcDir the path to the project source directory
     * @param binDir the path to the project system binaries
     */
    public static void writeTestOutput(
            TogType togType,
            String className,
            Path srcDir,
            Path binDir
    ) {
        String togName = togType.toString().toLowerCase();
        Path togTestPath = output.resolve(Paths.get("tog-tests", togName));
        Path outputPath = output.resolve(Paths.get(togName, "test-output.json"));
        List<String> testFails = getTestFails(togTestPath.resolve("test_fails.txt"));
        List<TestCase> testCases = getTestCases(togTestPath, testFails);
        TestOutput testOutput = new TestOutput(
                togName,
                className,
                srcDir.toString(),
                binDir.toString(),
                testCases.size() - testFails.size(),
                testFails.size(),
                testCases
        );
        FileUtils.writeJSON(outputPath, testOutput);
    }
}
