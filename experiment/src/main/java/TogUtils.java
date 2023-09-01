import com.fasterxml.jackson.core.type.TypeReference;
import com.github.javaparser.JavaParser;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.CallableDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.expr.ArrayAccessExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.symbolsolver.utils.SymbolSolverCollectionStrategy;
import data.JDoctorOutput;
import data.JDoctorOutput.Parameter;
import data.JDoctorOutput.ParamTag;
import data.JDoctorOutput.ReturnTag;
import data.JDoctorOutput.ThrowsTag;
import data.OracleDatapoint;
import data.OracleOutput;
import data.OracleType;
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
import java.util.stream.Collectors;

/**
 * This class provides static methods for generating the input for each specific TOG.
 */
public class TogUtils {
    /** The path to the output directory */
    private static final Path output = Paths.get("output");
    private static final Path evosuitePath = output.resolve("evosuite-tests");
    private static final Path evosuitePrefixPath = output.resolve("evosuite-prefix");
    private static final Path evosuiteTestsSimplePath = output.resolve("evosuite-tests-simple");

    /** The pattern to extract the text prefix (removing comments and decorators) */
    private static final Pattern testPrefixPattern = Pattern.compile("(public|protected|private)(.|\s)*");
    private static JavaParser javaParser;

    /** Private constructor to avoid creating an instance of this class. */
    private TogUtils() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    /**
     * Generates the input file for the TOGA tog. As required by TOGA, the input is a .csv file
     * composed of three columns:
     * <ol>
     *     <li>the focal method (i.e. the signature of the method for which TOGA have to generate
     *     the exceptional and assertion oracles).</li>
     *     <li>the test prefix of the method under test.</li>
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
        Path togaInputPath = prefixPath.resolve("toga_input.csv");
        Path togaMetadataPath = prefixPath.resolve("toga_metadata.csv");
        Path togaInfoPath = prefixPath.resolve("toga_info.json");
        Path fullyQualifiedClassNamePath = FileUtils.getRelativePathFromFullyQualifiedClassName(fullyQualifiedClassName);
        String className = FileUtils.getClassNameFromFullyQualifiedName(fullyQualifiedClassName);
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
                classFilePath = FileUtils.searchClassFile(srcDirPath, fullyQualifiedClassNamePath);
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
            togaInputBuilder.append("focal_method,test_prefix,docstring\n");
            togaMetadataBuilder.append("project,bug_num,test_name,exception_bug,assertion_bug,exception_lbl,assertion_lbl,assert_err\n");
            cuTestClassPrefixFile.findAll(MethodDeclaration.class).forEach(testPrefix -> {
                List<MethodCallExpr> methodCallExpressions = testPrefix.getBody().orElseThrow().findAll(MethodCallExpr.class);
                MethodCallExpr lastMethodCallExpression = methodCallExpressions.get(methodCallExpressions.size() - 1);
                try {
                    String testName = testPrefix.getNameAsString();
                    Map<String,String> togaRow = new HashMap<>();
                    MethodDeclaration mut = (MethodDeclaration) lastMethodCallExpression.resolve().toAst().orElseThrow();
                    MethodDeclaration test = cuTestClassFile
                            .findAll(MethodDeclaration.class)
                            .stream()
                            .filter(t -> t.getNameAsString().equals(testName))
                            .collect(Collectors.toList())
                            .get(0);
                    String methodName = mut.getNameAsString();
                    String focalMethod = getMethodSignature(mut);
                    String javadocString = getCallableJavadoc(mut).replaceAll("\"", "'");
                    String testStr = test.toString().replaceAll("\"", "'");
                    String testPrefixStr = testPrefix.toString();
                    Matcher matcher = testPrefixPattern.matcher(testPrefix.toString());
                    if (matcher.find()) {
                        int startIdx = matcher.start();
                        testStr = testStr.substring(startIdx);
                    }
                    togaInputBuilder.append(String.format("\"%s {}\",\"%s\",\"%s\"\n",focalMethod, testStr, javadocString));
                    togaMetadataBuilder.append(String.format("project,0,%s,0,0,False,\"\",\"\"\n", testName));
                    togaRow.put("className", cuClassFile.getPrimaryType().orElseThrow().getFullyQualifiedName().orElseThrow());
                    togaRow.put("methodName", methodName);
                    togaRow.put("methodSignature", focalMethod);
                    togaRow.put("testPrefix", testPrefixStr);
                    togaRow.put("testName", testName);
                    togaInfo.put(testName,togaRow);
                } catch (NoSuchElementException e) {
                    String errMsg = String.format(
                            "Focal method %s not found for class %s",
                            lastMethodCallExpression.getNameAsString(),
                            fullyQualifiedClassName
                    );
                    System.err.println(errMsg);
                }
            });
            FileUtils.writeJSON(togaInfoPath, togaInfo);
            FileUtils.writeString(togaInputPath, togaInputBuilder.toString());
            FileUtils.writeString(togaMetadataPath, togaMetadataBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
            throw new Error(e.getMessage());
        }
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

    public static JavaParser getJavaParser(Path srcDirPath) {
        SymbolSolverCollectionStrategy strategy = new SymbolSolverCollectionStrategy();
        strategy.collect(srcDirPath);
        JavaParser javaParser = new JavaParser();
        javaParser.getParserConfiguration().setSymbolResolver(strategy.getParserConfiguration().getSymbolResolver().get());
        return javaParser;
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
     * Transforms the output from the TOGA script (a .csv file where each row contains the
     * assertion for a given focal method and test prefix) into a json file containing a
     * corresponding list of {@code OracleOutput} objects.
     * Saves the output file in output/toga/oracle.
     * @see OracleOutput
     * @param srcDirPath the path to the source code of the project under test
     */
    public static void mapTOGAOutputToOracleOutput(Path srcDirPath) {
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
                    togaTest.get("testPrefix"),
                    assertPred,
                    isException ? "java.lang.Exception" : "",
                    testName
            );
            oracleOutputs.add(oracleOutput);
        }
        FileUtils.writeJSON(prefixPath.resolve("oracle_outputs.json"), oracleOutputs);
    }

    /**
     * Replaces all instances of {@code args[i]} in an oracle with the
     * corresponding parameter names from the method signature.
     *
     * @param jDoctorOutput the parent JDoctor condition of {@code oracle}
     * @param oracle an oracle generated by JDoctor
     * @return the same oracle with parameter names from the method signature
     */
    private static String contextualizeOracle(JDoctorOutput jDoctorOutput, String oracle) {
        Expression oracleExpression = StaticJavaParser.parseExpression(oracle);
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
        if (paramTag.condition().isEmpty()) {
            return null;
        }
        String oracle = contextualizeOracle(jDoctorOutput, paramTag.condition());
        return new OracleOutput(
                jDoctorOutput.targetClass(),
                jDoctorOutput.signature(),
                OracleType.PRE,
                "",
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
        return new OracleOutput(
                jDoctorOutput.targetClass(),
                jDoctorOutput.signature(),
                OracleType.NORMAL_POST,
                "",
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
        if (throwsTag.condition().isEmpty()) {
            return null;
        }
        String oracle = contextualizeOracle(jDoctorOutput, throwsTag.condition());
        return new OracleOutput(
                jDoctorOutput.targetClass(),
                jDoctorOutput.signature(),
                OracleType.EXCEPT_POST,
                "",
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
     * {@link OracleOutput} records and saves the output to
     * "output/jdoctor/oracle_outputs.json".
     *
     * @param jDoctorPath path to the JDoctor JSON output
     * @throws IllegalArgumentException if jDoctorPath does not exist
     */
    public static void jDoctorToOracleOutput(Path jDoctorPath) {
        if (!Files.exists(jDoctorPath)) {
            throw new IllegalArgumentException("Unable to find JDoctor output file " + jDoctorPath);
        }
        Path oraclePath = FileUtils.getProjectRoot(jDoctorPath.toAbsolutePath())
                .resolve("output")
                .resolve("jdoctor")
                .resolve("oracle_outputs.json");
        List<JDoctorOutput> jDoctorOutputs = FileUtils.readJSONList(jDoctorPath, JDoctorOutput.class);
        List<OracleOutput> oracleOutputs = jDoctorOutputs
                .stream()
                .map(TogUtils::conditionToOracleOutput)
                .flatMap(List::stream)
                .toList();
        FileUtils.writeJSON(oraclePath, oracleOutputs);
    }
<<<<<<< HEAD
=======

    /**
     * Transforms the output from the Tratto script into a json file containing a
     * corresponding list of {@code OracleOutput} objects.
     * Saves the output file in output/tratto/experiment.
     * @see OracleOutput
     * @param srcDirPath the path to the source code of the project under test
     */
    public static void mapTrattoOutputToOracleOutput(Path srcDirPath) {
        javaParser = getJavaParser(srcDirPath);
        Path prefixPath = output.resolve(Paths.get("tratto", "oracle"));
        Path outputPath = output.resolve(Paths.get("tratto", "output"));
        TypeReference<List<OracleDatapoint>> typeReference = new TypeReference<>(){};
        List<OracleDatapoint> oracleDatapoints = (List<OracleDatapoint>) FileUtils.readJSON(
                outputPath.resolve("oracle_datapoints.json"),
                typeReference
        );
        List<OracleOutput> oracleOutputs = new ArrayList<>();
        for (OracleDatapoint oracleDatapoint : oracleDatapoints) {
            CompilationUnit cu = new CompilationUnit();
            // Parse the method string and add it to the CompilationUnit

            MethodDeclaration mut = javaParser.parseMethodDeclaration(oracleDatapoint.methodSourceCode()).getResult().orElseThrow();
            String methodSignature = getMethodSignature(mut);
            OracleOutput oracleOutput = new OracleOutput(
                    oracleDatapoint.className(),
                    methodSignature,
                    oracleDatapoint.oracleType(),
                    "",
                    oracleDatapoint.oracleType() != OracleType.EXCEPT_POST ? oracleDatapoint.oracle() : "",
                    oracleDatapoint.oracleType() == OracleType.EXCEPT_POST ? oracleDatapoint.oracle() : "",
                    ""
            );
            oracleOutputs.add(oracleOutput);
        }
        FileUtils.writeJSON(prefixPath.resolve("oracle_outputs.json"), oracleOutputs);
    }
>>>>>>> experiment-end-to-end-Davide
}
