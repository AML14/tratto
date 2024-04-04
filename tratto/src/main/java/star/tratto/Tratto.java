package star.tratto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.TypeDeclaration;
import org.javatuples.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import star.tratto.data.OracleDatapoint;
import star.tratto.data.OracleType;
import star.tratto.data.TokenDPType;
import star.tratto.data.TokenDatapoint;
import star.tratto.input.ClassAnalyzer;
import star.tratto.util.javaparser.JavaParserUtils;
import star.tratto.data.TokenNotFoundException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static star.tratto.data.OracleDP2TokenDPs.oracleSoFarAndTokenToTokenDatapoint;
import static star.tratto.util.FileUtils.readString;
import static star.tratto.util.StringUtils.getClassNameFromPath;
import static star.tratto.util.javaparser.JavaParserUtils.getClassOrInterface;
import static star.tratto.util.StringUtils.compactExpression;

public class Tratto {

    private static final Logger logger = LoggerFactory.getLogger(Tratto.class);

    private static final ClassAnalyzer classAnalyzer = ClassAnalyzer.getInstance();
    private static final JavaParser javaParser = JavaParserUtils.getJavaParser();
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String ML_MODEL_API_URL = String.format("http://127.0.0.1:5000/api/next_token?filename=%s/src/main/resources/token_datapoints.json", System.getProperty("user.dir"));
    public static String CLASS_PATH = "src/main/resources/projects-source/commons-collections4-4.1/raw/src/main/java/org/apache/commons/collections4/BagUtils.java";
    public static String PROJECT_SOURCE_PATH = "src/main/resources/projects-source/commons-collections4-4.1/raw/src/main/java/";
    public static String PROJECT_JAR_PATH = "src/main/resources/projects-packaged/commons-collections4-4.1-jar-with-dependencies.jar";
    public static String TOKEN_DATAPOINTS_PATH = "src/main/resources/token_datapoints.json";
    public static String ORACLE_DATAPOINTS_PATH = "src/main/resources/oracle_datapoints.json";

    public static void main(String[] args) throws IOException {
        logger.info("Starting Tratto...");
        logger.info("Starting up ML models local server...");
        // TODO: Start up ML models local server
        logger.info("Generating oracles for: \n\tClass: {}\n\tProject source: {}\n\tProject JAR: {}", CLASS_PATH, PROJECT_SOURCE_PATH, PROJECT_JAR_PATH);

        // Configure JavaParser to resolve symbols from project under test
        JavaParserUtils.updateSymbolSolver(PROJECT_JAR_PATH);

        // Set up OracleDatapointBuilder within ClassAnalyzer based on project and class under test
        String className = getClassNameFromPath(CLASS_PATH);
        String classSourceCode = readString(Paths.get(CLASS_PATH));
        CompilationUnit classCu = javaParser.parse(classSourceCode).getResult().get();
        TypeDeclaration<?> classTd = getClassOrInterface(classCu, className);
        classAnalyzer.setProjectPath(PROJECT_SOURCE_PATH);
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
                    FileOutputStream tokenDatapointsOutputStream = new FileOutputStream(TOKEN_DATAPOINTS_PATH);
                    tokenDatapointsOutputStream.write(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(tokenDatapoint));
                    tokenDatapointsOutputStream.close();
                    String nextToken = getNextToken();
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
        File oracleDatapointsFile = new File(ORACLE_DATAPOINTS_PATH);
        FileOutputStream oracleDatapointsOutputStream = new FileOutputStream(oracleDatapointsFile);
        oracleDatapointsOutputStream.write(objectMapper.writeValueAsBytes(oracleDatapoints));
        oracleDatapointsOutputStream.close();
        new File(TOKEN_DATAPOINTS_PATH).delete();

        logger.info("Finished generating oracles for: \n\tClass: {}\n\tProject source: {}\n\tProject JAR: {}", CLASS_PATH, PROJECT_SOURCE_PATH, PROJECT_JAR_PATH);
    }

    private static String getNextToken() throws IOException {
        HttpURLConnection mlModelApiConn = (HttpURLConnection) new URL(ML_MODEL_API_URL).openConnection();
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
}
