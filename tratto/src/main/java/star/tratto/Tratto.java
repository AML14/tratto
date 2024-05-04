package star.tratto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.TypeDeclaration;
import org.javatuples.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import star.tratto.data.*;
import star.tratto.input.ClassAnalyzer;
import star.tratto.util.javaparser.JavaParserUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import static star.tratto.data.OracleDP2TokenDPs.oracleSoFarAndTokenToTokenDatapoint;
import static star.tratto.util.FileUtils.readString;
import static star.tratto.util.StringUtils.getClassNameFromPath;
import static star.tratto.util.javaparser.JavaParserUtils.getClassOrInterface;
import static star.tratto.util.StringUtils.compactExpression;

public class Tratto {

    private static final Logger logger = LoggerFactory.getLogger(Tratto.class);

    private static final JavaParser javaParser = JavaParserUtils.getJavaParser();
    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static final Path REPOS_DIR = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "repos");
    public static final Path TRATTO_OUTPUT = Paths.get(System.getProperty("user.dir"), "tratto_output");
    public static Path TOKEN_DATAPOINTS_PATH = TRATTO_OUTPUT.resolve("token_datapoints.json");
    public static Path ORACLE_DATAPOINTS_PATH = TRATTO_OUTPUT.resolve("oracle_datapoints.json");

    public static void main(String[] args) throws IOException {
        generateRepos();

        ClassAnalyzer classAnalyzer = ClassAnalyzer.getInstance();
        String fullyQualifiedClassName = args[0];
        String projectSourcePath = args[1];
        String classPath = projectSourcePath + "/" + fullyQualifiedClassName.replace(".", "/") + ".java";
        String projectJarPath = args[2];
        String serverPort = args[3];
        String mlModelAPIUrl = String.format("http://127.0.0.1:%s/api/next_token?filename=%s/token_datapoints.json", serverPort, TRATTO_OUTPUT);

        if (!Files.exists(TRATTO_OUTPUT)) {
            Files.createDirectories(TRATTO_OUTPUT);
        }

        logger.info("Starting Tratto...");
        logger.info("Starting up ML models local server...");
        // TODO: Start up ML models local server
        logger.info("Generating oracles for: \n\tClass: {}\n\tProject source: {}\n\tProject JAR: {}", classPath, projectSourcePath, projectJarPath);

        // Configure JavaParser to resolve symbols from project under test
        JavaParserUtils.updateSymbolSolver(projectJarPath);

        // Set up OracleDatapointBuilder within ClassAnalyzer based on project and class under test
        String className = getClassNameFromPath(classPath);
        String classSourceCode = readString(Paths.get(classPath));
        CompilationUnit classCu = javaParser.parse(classSourceCode).getResult().get();
        TypeDeclaration<?> classTd = getClassOrInterface(classCu, className);
        classAnalyzer.setProjectPath(projectSourcePath);
        classAnalyzer.setClassFeatures(className, classSourceCode, classCu, classTd);

        List<OracleDatapoint> oracleDatapoints = classAnalyzer.getOracleDatapointsFromClass();
        // Update each OracleDatapoint until the oracle is complete (ends with ';')
        for (OracleDatapoint oracleDatapoint : oracleDatapoints) {
            List<String> oracleSoFarTokens = new ArrayList<>();

            logger.info("-------------------------------------------------------------------------");
            logger.info("Generating oracle for:\n\tMethod: {}\n\tJavadoc tag: {}\n\tOracle type: {}",
                    oracleDatapoint.getMethodSourceCode().split("\\{")[0],
                    oracleDatapoint.getJavadocTag(),
                    oracleDatapoint.getOracleType());

            while (!oracleDatapoint.getOracle().endsWith(";")) {
                // Generate token datapoints and save to file
                try {
                    TokenDatapoint tokenDatapoint = oracleSoFarAndTokenToTokenDatapoint(oracleDatapoint, oracleSoFarTokens, "");
                    FileOutputStream tokenDatapointsOutputStream = new FileOutputStream(TOKEN_DATAPOINTS_PATH.toString());
                    tokenDatapointsOutputStream.write(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(tokenDatapoint));
                    tokenDatapointsOutputStream.close();
                    String nextToken = getNextToken(mlModelAPIUrl);
                    oracleDatapoint.setOracle(compactExpression(oracleDatapoint.getOracle() + " " + nextToken));
                    oracleSoFarTokens.add(nextToken);
                    logger.info("Oracle so far: {}", oracleDatapoint.getOracle());
                } catch (TokenNotFoundException e) {
                    logger.error(e.getMessage());
                    oracleDatapoint.setOracle(";");
                    break;
                }// HTTP call to ML-model API to get next token and class
            }
            logger.info("Final oracle: {}", oracleDatapoint.getOracle());
            logger.info("-------------------------------------------------------------------------");
        }

        // Save final oracleDatapoints to file
        File oracleDatapointsFile = new File(ORACLE_DATAPOINTS_PATH.toString());
        FileOutputStream oracleDatapointsOutputStream = new FileOutputStream(oracleDatapointsFile);
        oracleDatapointsOutputStream.write(objectMapper.writeValueAsBytes(oracleDatapoints));
        oracleDatapointsOutputStream.close();
        new File(TOKEN_DATAPOINTS_PATH.toString()).delete();

        logger.info("Finished generating oracles for: \n\tClass: {}\n\tProject source: {}\n\tProject JAR: {}", classPath, projectSourcePath, projectJarPath);
    }

    private static String getNextToken(String mlModelAPIUrl) throws IOException {
        HttpURLConnection mlModelApiConn = (HttpURLConnection) new URL(mlModelAPIUrl).openConnection();
        mlModelApiConn.setRequestMethod("GET");
        mlModelApiConn.connect();

        int responseCode = mlModelApiConn.getResponseCode();
        String nextToken;

        if (responseCode == HttpURLConnection.HTTP_OK) {
            nextToken = new String(mlModelApiConn.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        } else if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
            // Handle 404 response
            String errorResponse = new String(mlModelApiConn.getErrorStream().readAllBytes(), StandardCharsets.UTF_8);
            throw new TokenNotFoundException(errorResponse);
        } else {
            throw new IOException("Unexpected response code: " + responseCode);
        }

        mlModelApiConn.disconnect();
        return nextToken;
    }

    private static void generateRepos() throws IOException {
        List<Path> reposFilesPaths = new ArrayList<>();
        reposFilesPaths.add(TrattoPath.ARRAY_METHODS.getRelativePath());
        reposFilesPaths.add(TrattoPath.INPUT_PROJECTS.getRelativePath());
        reposFilesPaths.add(TrattoPath.NON_VARIABLE_TOKENS.getRelativePath());
        reposFilesPaths.add(TrattoPath.TOKENS_GENERAL_VALUES.getRelativePath());
        reposFilesPaths.add(TrattoPath.TOKENS_GRAMMAR.getRelativePath());
        reposFilesPaths.add(TrattoPath.GLOBAL_DICTIONARY.getRelativePath());

        for (Path reposFilePath : reposFilesPaths) {
            String reposFilePathString = Paths.get("/","repos", reposFilePath.getFileName().toString()).toString();
            // Load the resource from the JAR file
            InputStream inputStream = Tratto.class.getResourceAsStream(reposFilePathString);
            if (inputStream == null) {
                throw new FileNotFoundException("Resource not found: " + reposFilePathString);
            }

            // Prepare the target directory
            if (!Files.exists(REPOS_DIR)) {
                Files.createDirectories(REPOS_DIR);
            }

            // Define the target file path
            Path targetFile = REPOS_DIR.resolve(reposFilePath.getFileName());

            // Copy the contents of the resource to the target file
            Files.copy(inputStream, targetFile, StandardCopyOption.REPLACE_EXISTING);

            // Close the input stream
            inputStream.close();
        }
    }
}
