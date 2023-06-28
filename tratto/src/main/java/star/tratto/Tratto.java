package star.tratto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import star.tratto.data.OracleDatapoint;
import star.tratto.data.TokenDPType;
import star.tratto.data.TokenDatapoint;
import star.tratto.input.ClassAnalyzer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static star.tratto.data.OracleDP2TokenDPs.oracleSoFarAndTokenToTokenDatapoints;
import static star.tratto.util.JavaParserUtils.getClassOrInterface;
import static star.tratto.util.StringUtils.compactExpression;

public class Tratto {

    private static final Logger logger = LoggerFactory.getLogger(Tratto.class);

    private static final ClassAnalyzer classAnalyzer = ClassAnalyzer.getInstance();
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String ML_MODEL_API_URL = "TODO";
    public static String CLASS_PATH = "src/main/resources/classes-under-test/TODO.java";
    public static String PROJECT_PATH = "src/main/resources/projects-sources/TODO/";
    public static String TOKEN_DATAPOINTS_PATH = "src/main/resources/token_datapoints.json";
    public static String ORACLE_DATAPOINTS_PATH = "src/main/resources/oracle_datapoints.json";

    public static void main(String[] args) throws IOException {
        logger.info("Starting Tratto...");
        logger.info("Generating oracles for class {} in project {}", CLASS_PATH, PROJECT_PATH);

        // General setup (tokens and oracles files and HTTP connection)
        File tokenDatapointsFile = new File(TOKEN_DATAPOINTS_PATH);
        FileOutputStream tokenDatapointsOutputStream = new FileOutputStream(tokenDatapointsFile, true);
        File oracleDatapointsFile = new File(ORACLE_DATAPOINTS_PATH);
        FileOutputStream oracleDatapointsOutputStream = new FileOutputStream(oracleDatapointsFile, true);
        HttpURLConnection mlModelApiConn = (HttpURLConnection) new URL(ML_MODEL_API_URL).openConnection();
        mlModelApiConn.setRequestMethod("GET");

        // Set up base OracleDatapoint within ClassAnalyzer based on class and project under test
        classAnalyzer.setClassPath(CLASS_PATH);
        classAnalyzer.setProjectPath(PROJECT_PATH);

        // TODO: Use FileUtils to get class name and source code
        String className = "TODO";
        String classSourceCode = "TODO";

        List<OracleDatapoint> oracleDatapoints = classAnalyzer.getOracleDatapointsFromClass(getClassOrInterface(classSourceCode, className));
        for (OracleDatapoint oracleDatapoint : oracleDatapoints) { // Update each OracleDatapoint until the oracle is complete (ends with ';')
            List<String> oracleSoFarTokens = new ArrayList<>();
            while (!oracleDatapoint.getOracle().endsWith(";")) {
                // Generate token datapoints and save to file
                List<TokenDatapoint> tokenDatapoints = oracleSoFarAndTokenToTokenDatapoints(oracleDatapoint, oracleSoFarTokens, "", TokenDPType.TOKEN);
                tokenDatapointsFile.delete();
                tokenDatapointsOutputStream.write(objectMapper.writeValueAsBytes(tokenDatapoints));

                // HTTP call to ML-model API to get next token
                mlModelApiConn.connect();
                String nextToken = objectMapper.readValue(mlModelApiConn.getInputStream(), String.class);

                // Update oracleDatapoint.oracle and oracleSoFarTokens with next token
                oracleDatapoint.setOracle(compactExpression(oracleDatapoint.getOracle() + " " + nextToken));
                oracleSoFarTokens.add(nextToken);
            }
        }

        oracleDatapointsFile.delete();
        oracleDatapointsOutputStream.write(objectMapper.writeValueAsBytes(oracleDatapoints));

        logger.info("Finished generating oracles for class {} in project {}", CLASS_PATH, PROJECT_PATH);
    }
}
