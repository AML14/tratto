package star.tratto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import star.tratto.data.OracleDatapoint;
import star.tratto.input.ClassAnalyzer;

import java.util.List;

import static star.tratto.util.JavaParserUtils.getClassOrInterface;

public class Tratto {

    private static final Logger logger = LoggerFactory.getLogger(Tratto.class);

    private static final ClassAnalyzer classAnalyzer = ClassAnalyzer.getInstance();
    public static String classPath = "src/main/resources/classes-under-test/TODO.java";
    public static String projectPath = "src/main/resources/projects-sources/TODO/";

    public static void main(String[] args) {
        logger.info("Starting Tratto...");
        logger.info("Generating oracles for class {} in project {}", classPath, projectPath);

        classAnalyzer.setClassPath(classPath);
        classAnalyzer.setProjectPath(projectPath);

        // TODO: Use FileUtils to get class name and source code
        String className = "TODO";
        String classSourceCode = "TODO";

        List<OracleDatapoint> oracleDatapoints = classAnalyzer.getOracleDatapointsFromClass(getClassOrInterface(classSourceCode, className));

        for (OracleDatapoint oracleDatapoint : oracleDatapoints) { // Update each OracleDatapoint until the oracle is complete (ends with ';')
            while (!oracleDatapoint.getOracle().endsWith(";")) {
                // Generate token datapoints and save to folder

                // HTTP call to ML-model API to get next token

                // Update oracle with next token
            }
        }
    }
}
