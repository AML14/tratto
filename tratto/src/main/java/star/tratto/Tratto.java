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
import star.tratto.util.FileUtils;
import star.tratto.util.javaparser.JavaParserUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static star.tratto.data.OracleDP2TokenDPs.oracleSoFarAndTokenToTokenDatapoints;
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
        Path fullyQualifiedClassNamePath = FileUtils.getRelativePathFromFullyQualifiedClassName(args[0]);
        Path projectSourcePath = Paths.get(args[1]);
        Path projectJarPath = Paths.get(args[2]);
        Path classPath = projectSourcePath.resolve(fullyQualifiedClassNamePath);

        if (!Files.exists(classPath)){
            Path commonPatternPath = Paths.get(projectSourcePath.toString(), "main", "java");
            classPath = commonPatternPath.resolve(fullyQualifiedClassNamePath);
            if (!Files.exists(classPath)) {
                classPath = FileUtils.searchClassFile(projectSourcePath, fullyQualifiedClassNamePath);
                if (classPath == null) {
                    throw new RuntimeException("The full path to the class file has not been found.");
                }
            }
        }

        logger.info("Starting Tratto...");
        logger.info("Starting up ML models local server...");
        // TODO: Start up ML models local server
        logger.info("Generating oracles for: \n\tClass: {}\n\tProject source: {}\n\tProject JAR: {}", classPath, projectSourcePath, projectJarPath);

        // Configure JavaParser to resolve symbols from project under test
        JavaParserUtils.updateSymbolSolver(projectJarPath.toString());

        // Set up OracleDatapointBuilder within ClassAnalyzer based on project and class under test
        String className = getClassNameFromPath(classPath.toString());
        String classSourceCode = readString(Paths.get(classPath.toString()));
        CompilationUnit classCu = javaParser.parse(classSourceCode).getResult().get();
        TypeDeclaration<?> classTd = getClassOrInterface(classCu, className);
        classAnalyzer.setProjectPath(projectSourcePath.toString());
        classAnalyzer.setClassFeatures(className, classSourceCode, classCu, classTd);

        List<OracleDatapoint> oracleDatapoints = classAnalyzer.getOracleDatapointsFromClass();
        // Update each OracleDatapoint until the oracle is complete (ends with ';')
        for (OracleDatapoint oracleDatapoint : oracleDatapoints) {
            List<String> oracleSoFarTokens = new ArrayList<>();
            List<String> tokenClassesSoFar = new ArrayList<>();

            logger.info("-------------------------------------------------------------------------");
            logger.info("Generating oracle for:\n\tMethod: {}\n\tJavadoc tag: {}\n\tOracle type: {}",
                    oracleDatapoint.getMethodSourceCode().split("\\{")[0],
                    oracleDatapoint.getJavadocTag(),
                    oracleDatapoint.getOracleType());

            while (!(oracleDatapoint.getOracle().endsWith(";") && oracleDatapoint.getOracle().length() < 50)) {
                // Generate token datapoints and save to file
                List<TokenDatapoint> tokenDatapoints = oracleSoFarAndTokenToTokenDatapoints(oracleDatapoint, oracleSoFarTokens, tokenClassesSoFar, "", TokenDPType.TOKEN);
                FileOutputStream tokenDatapointsOutputStream = new FileOutputStream(TOKEN_DATAPOINTS_PATH);
                tokenDatapointsOutputStream.write(objectMapper.writeValueAsBytes(tokenDatapoints));
                tokenDatapointsOutputStream.close();

                Pair<String, String> nextTokenValueClass = getNextTokenValueClass(); // HTTP call to ML-model API to get next token and class

                oracleDatapoint.setOracle(compactExpression(oracleDatapoint.getOracle() + " " + nextTokenValueClass.getValue0()));
                oracleSoFarTokens.add(nextTokenValueClass.getValue0());
                tokenClassesSoFar.add(nextTokenValueClass.getValue1());

                logger.info("Oracle so far: {}", oracleDatapoint.getOracle());
            }

            if (oracleDatapoint.getOracle().endsWith(";")) {
                logger.info("Final oracle: {}", oracleDatapoint.getOracle());
                logger.info("-------------------------------------------------------------------------");
            } else {
                logger.info("Unable to generate oracle. Oracle removed.");
                logger.info("-------------------------------------------------------------------------");
                oracleDatapoint.setOracle(null);
            }
        }
        // Remove all the oracle datapoints for which it has been impossible to produce an oracle
        oracleDatapoints = oracleDatapoints.stream().filter(oracleDatapoint -> oracleDatapoint.getOracle() != null).collect(Collectors.toList());
        // Save final oracleDatapoints to file
        File oracleDatapointsFile = new File(ORACLE_DATAPOINTS_PATH);
        FileOutputStream oracleDatapointsOutputStream = new FileOutputStream(oracleDatapointsFile);
        oracleDatapointsOutputStream.write(objectMapper.writeValueAsBytes(oracleDatapoints));
        oracleDatapointsOutputStream.close();
        new File(TOKEN_DATAPOINTS_PATH).delete();

        logger.info("Finished generating oracles for: \n\tClass: {}\n\tProject source: {}\n\tProject JAR: {}", classPath, projectSourcePath, projectJarPath);
    }

    private static Pair<String, String> getNextTokenValueClass() throws IOException {
        HttpURLConnection mlModelApiConn = (HttpURLConnection) new URL(ML_MODEL_API_URL).openConnection();
        mlModelApiConn.setRequestMethod("GET");
        mlModelApiConn.connect();
        Pair<String, String> nextTokenValueClass = Pair.fromArray(new String(mlModelApiConn.getInputStream().readAllBytes(), StandardCharsets.UTF_8).split("\n"));
        mlModelApiConn.disconnect();
        return nextTokenValueClass;
    }
}
