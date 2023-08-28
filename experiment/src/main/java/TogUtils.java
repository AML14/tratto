import com.fasterxml.jackson.core.type.TypeReference;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.CallableDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.symbolsolver.utils.SymbolSolverCollectionStrategy;
import data.OracleOutput;
import data.OracleType;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class provides static methods for generating the input for each specific TOG.
 */
public class TogUtils {
    /** The path to the output directory */
    private static final Path output = Paths.get("output");

    /** The pattern to extract the text prefix (removing comments and decorators) */
    private static final Pattern testPrefixPattern = Pattern.compile("(public|protected|private)(.|\s)*");
    private static JavaParser javaParser;

    // private constructor to avoid creating an instance of this class.
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
     * @param classFilePath the path to the class under test
     * @param testClassFilePath the path to the corresponding test class, containing all the test prefixes
     *                          (i.e. the test methods without assertions).
     *
     */
    public static void generateTOGAInput(Path srcDirPath, Path classFilePath, Path testClassFilePath) {
        Path prefixPath = output.resolve(Paths.get("toga", "input"));
        Path togaInputPath = prefixPath.resolve("toga_input.csv");
        Path togaMetadataPath = prefixPath.resolve("toga_metadata.csv");
        Path togaInfoPath = prefixPath.resolve("toga_info.json");
        javaParser = getJavaParser(srcDirPath);
        try {
            CompilationUnit cuClassFile = javaParser.parse(classFilePath).getResult().orElseThrow();
            CompilationUnit cuTestClassFile = javaParser.parse(testClassFilePath).getResult().orElseThrow();
            //Map<String, List<Map<String,String>>> togaInfo = new HashMap<>();
            Map<String, Map<String,String>> togaInfo = new HashMap<>();
            StringBuilder togaInputBuilder = new StringBuilder();
            StringBuilder togaMetadataBuilder = new StringBuilder();
            togaInputBuilder.append("focal_method,test_prefix,docstring\n");
            togaMetadataBuilder.append("project,bug_num,test_name,exception_bug,assertion_bug,exception_lbl,assertion_lbl,assert_err\n");
            cuTestClassFile.findAll(MethodDeclaration.class).forEach(testprefix -> {
                List<MethodCallExpr> methodCallExpressions = testprefix.getBody().orElseThrow().findAll(MethodCallExpr.class);
                MethodCallExpr lastMethodCallExpression = methodCallExpressions.get(methodCallExpressions.size() - 1);
                try {
                    Map<String,String> togaRow = new HashMap<>();
                    MethodDeclaration mut = (MethodDeclaration) lastMethodCallExpression.resolve().toAst().orElseThrow();
                    String methodName = mut.getNameAsString();
                    String testName = testprefix.getNameAsString();
                    String focalMethod = getMethodSignature(mut);
                    String javadocString = getCallableJavadoc(mut).replaceAll("\"", "'");
                    String testPrefixStr = testprefix.toString().replaceAll("\"", "'");
                    Matcher matcher = testPrefixPattern.matcher(testprefix.toString());
                    if (matcher.find()) {
                        int startIdx = matcher.start();
                        testPrefixStr = testPrefixStr.substring(startIdx);
                    }
                    togaInputBuilder.append(String.format("\"%s {}\",\"%s\",\"%s\"\n",focalMethod, testPrefixStr, javadocString));
                    togaMetadataBuilder.append(String.format("project,0,%s,0,0,False,\"\",\"\"\n", testName));
                    togaRow.put("className", cuClassFile.getPrimaryType().orElseThrow().getFullyQualifiedName().orElseThrow());
                    togaRow.put("methodName", methodName);
                    togaRow.put("methodSignature", focalMethod);
                    togaRow.put("testPrefix", testPrefixStr);
                    togaRow.put("testName", testName);
                    /*List<Map<String, String>> togaTestList = new ArrayList<>();
                    if (togaInfo.containsKey(testName)) {
                        togaTestList = togaInfo.get(testName);
                    }
                    togaTestList.add(togaRow);
                    togaInfo.put(testName, togaTestList);*/
                    togaInfo.put(testName,togaRow);
                } catch (NoSuchElementException e) {
                    String errMsg = String.format(
                            "Focal method %s not found for class %s",
                            lastMethodCallExpression.getNameAsString(),
                            classFilePath
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
     * Saves the output file in output/toga/experiment.
     * @see OracleOutput
     * @param srcDirPath the path to the source code of the project under test
     * @param inputTogaFilePath the path to the input file to the TOGA script.
     * @param outputTogaFilePath the path to the output file generated by TOGA script.
     */
    public static void mapTOGAOutputToOracleOutput(Path srcDirPath) {
        Path prefixPath = output.resolve(Paths.get("toga", "experiment"));
        Path inputPath = output.resolve(Paths.get("toga", "input"));
        Path outputPath = output.resolve(Paths.get("toga", "output"));
        javaParser = getJavaParser(srcDirPath);
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
                    isException ? OracleType.EXCEPT_POST.toString() : OracleType.NORMAL_POST.toString(),
                    togaTest.get("testPrefix"),
                    assertPred,
                    "",
                    testName
            );
            oracleOutputs.add(oracleOutput);
        }
        FileUtils.writeJSON(prefixPath.resolve("oracle_outputs.json"), oracleOutputs);
    }

    public static void main(String[] args) {
        String root = "src/test/resources/project/src";
        SymbolSolverCollectionStrategy strategy = new SymbolSolverCollectionStrategy();
        strategy.collect(Paths.get(root));
        JavaParser javaParser = new JavaParser();
        javaParser.getParserConfiguration().setSymbolResolver(strategy.getParserConfiguration().getSymbolResolver().get());

        /*generateTOGAInput(
            Paths.get(root),
            Paths.get(System.getProperty("user.dir"), "src", "test","resources","Stack.java"),
            Paths.get(System.getProperty("user.dir"), "src", "test","resources","Stack_ESTestPrefix.java")
        );*/
        mapTOGAOutputToOracleOutput(Paths.get(root));
    }
}
