import data.OperationType;
import data.OracleOutput;
import data.TogType;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * This class represents the main file used in {@code experiment.jar}, with
 * all necessary operations for the end-to-end experiment process, including:
 * <ul>
 *     <li>Removing oracles</li>
 *     <li>Inserting oracles</li>
 *     <li>Preprocessing TOG input</li>
 *     <li>Postprocessing TOG output</li>
 * </ul>
 * The various operations require different arguments, following the format:
 * <ul>
 *     <li>{@code java -jar experiment.jar toga remove_oracles
 *     "path/to/evosuite-tests" tutorial.Stack}</li>
 *     <li>{@code java -jar experiment.jar jdoctor insert_oracles
 *     "path/to/src/main" "path/to/oracle/output.json"}</li>
 *     <li>{@code java -jar experiment.jar toga generate_tog_inputs
 *     "path/to/src/main" tutorial.Stack}</li>
 *     <li>{@code java -jar experiment.jar jdoctor generate_oracle_outputs
 *     path/to/jdoctor/output.json}</li>
 * </ul>
 */
public class Tog {
    /**
     * Inserts the OracleOutput records generated by a TOG into the prefixes
     * generated by EvoSuite.
     *
     * @param togType a TOG
     * @param oraclesPath the path to the OracleOutput JSON file
     */
    private static void insertOraclesOperation(TogType togType, Path binDir, Path oraclesPath) {
        Path prefixPath = Paths.get("output", "evosuite-prefix");
        List<OracleOutput> oracleOutputs = FileUtils.readJSONList(oraclesPath, OracleOutput.class);
        TestUtils.insertOracles(binDir, prefixPath, togType, oracleOutputs);
    }

    /**
     * Preprocesses the given input to be valid for the specified TOG.
     *
     * @param togType a TOG
     * @param srcDir the directory to the project source code
     * @param fullyQualifiedName the fully qualified name of the class under
     *                           test
     */
    private static void generateTogInputOperation(TogType togType, Path srcDir, String fullyQualifiedName) {
        if (togType == TogType.TOGA) {
            TogUtils.generateTOGAInput(srcDir, fullyQualifiedName);
        }
    }

    /**
     * Converts the output of a given TOG to a list of OracleOutput records.
     *
     * @param togType a TOG
     * @param dir the directory for mapping outputs
     */
    private static void generateOracleOutputOperation(TogType togType, Path dir) {
        if (togType == TogType.TOGA) {
            TogUtils.mapTOGAOutputToOracleOutput(dir);
        } else if (togType == TogType.JDOCTOR) {
            TogUtils.jDoctorToOracleOutput(dir);
        } else if (togType == TogType.TRATTO) {
            TogUtils.mapTrattoOutputToOracleOutput(dir);
        }
    }

    /**
     * The main method handles all invocations of the JAR file.
     *
     * @param args arguments passed for each JAR invocation
     */
    public static void main(String[] args) {
        TogType togType = TogType.valueOf(args[0].toUpperCase());
        OperationType operationType = OperationType.valueOf(args[1].toUpperCase());
        switch (operationType) {
            case REMOVE_ORACLES -> TestUtils.removeOracles(Paths.get(args[2]), args[3]);
            case INSERT_ORACLES -> insertOraclesOperation(togType, Paths.get(args[2]), Paths.get(args[3]));
            case GENERATE_TOG_INPUTS -> generateTogInputOperation(togType, Paths.get(args[2]), args[3]);
            case GENERATE_ORACLE_OUTPUTS -> generateOracleOutputOperation(togType, Paths.get(args[2]));
            default -> throw new IllegalArgumentException("Unknown operation " + operationType);
        }
    }
}
