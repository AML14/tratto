import data.ExperimentOperationType;
import data.TogType;

import java.nio.file.Path;

/**
 * This class is the main file used in the build {@code experiment.jar}, with
 * all necessary operations for the experiment pipeline, including:
 * <ul>
 *     <li>Removing oracles</li>
 *     <li>Inserting oracles</li>
 *     <li>Preprocessing TOG input</li>
 *     <li>Postprocessing TOG output</li>
 *     <li>Summarizing Defects4J results</li>
 * </ul>
 * The various operations require different arguments, following the format:
 * <ul>
 *     <li>{@code java -jar experiment.jar remove_oracles
 *     [testDir]}</li>
 *     <li>{@code java -jar experiment.jar insert_oracles
 *     [pathToPrefixes] [pathToOracles] [classpath]}</li>
 *     <li>{@code java -jar experiment.jar generate_tog_input
 *     [togType] [fullyQualifiedName] [srcDir]}</li>
 *     <li>{@code java -jar experiment.jar generate_oracle_output
 *     [togType] [srcDir]}</li>
 *     <li>{@code java -jar experiment.jar generate_defects4j_output
 *     [togType] [projectName] [bugID] [fullyQualifiedName]}</li>
 * </ul>
 */
public class Tog {
    /**
     * Preprocess the input to the necessary format for a given TOG.
     *
     * @param togType a TOG
     * @param fullyQualifiedName the class under test
     * @param srcDir the source directory of the project under test
     */
    private static void generateTogInput(
            TogType togType,
            String fullyQualifiedName,
            Path srcDir
    ) {
    }

    /**
     * Converts a TOG output to a list of OracleOutput records.
     *
     * @param togType a TOG
     * @param path a path to a file or directory for generating OracleOutputs
     * @param srcPath the source directory of the project under test (can be null)
     */
    private static void generateOracleOutput(
            TogType togType,
            Path path,
            Path srcPath
    ) {
    }

    /**
     * The main method handles all invocations of the JAR file.
     *
     * @param args arguments passed for each JAR invocation
     */
    public static void main(String[] args) {
        ExperimentOperationType experimentOperationType = ExperimentOperationType.valueOf(args[0].toUpperCase());
        switch (experimentOperationType) {
            case REMOVE_ORACLES -> System.out.println("REMOVE_ORACLES");
            case INSERT_ORACLES -> System.out.println("INSERT_ORACLES");
            case GENERATE_TOG_INPUT -> System.out.println("GENERATE_TOG_INPUT");
            case GENERATE_ORACLE_OUTPUT -> System.out.println("GENERATE_ORACLE_OUTPUT");
            case GENERATE_DEFECTS4J_OUTPUT -> System.out.println("GENERATE_DEFECTS4J_OUTPUT");
            default -> throw new IllegalArgumentException("Unknown operation " + experimentOperationType);
        }
    }
}
