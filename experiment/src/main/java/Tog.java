import data.OperationType;
import data.TogType;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This class is the main file used in the build {@code experiment.jar}, with
 * all necessary operations for the experiment pipeline, including:
 * <ul>
 *     <li>Removing oracles</li>
 *     <li>Inserting oracles</li>
 *     <li>Preprocessing TOG input</li>
 *     <li>Postprocessing TOG output</li>
 *     <li>Summarizing test suite results</li>
 *     <li>Summarizing Defects4J results</li>
 *     <li>Combining Defects4J results into a complete summary</li>
 * </ul>
 * The various operations require different arguments, following the format:
 * <ul>
 *     <li>{@code java -jar experiment.jar remove_oracles
 *     [fullyQualifiedName]}</li>
 *     <li>{@code java -jar experiment.jar insert_oracles
 *     [togType] [jarPath]}</li>
 *     <li>{@code java -jar experiment.jar generate_tog_input
 *     [togType] [fullyQualifiedName] [srcDir]}</li>
 *     <li>{@code java -jar experiment.jar generate_oracle_output
 *     [togType] [srcDir]}</li>
 *     <li>{@code java -jar experiment.jar generate_test_output
 *     [togType] [fullyQualifiedName] [srcDir] [binDir]}</li>
 *     <li>{@code java -jar experiment.jar generate_defects4j_output
 *     [togType] [projectName] [bugID] [fullyQualifiedName]}</li>
 *     <li>{@code java -jar experiment.jar combine_defects4j_output
 *     [togType]}</li>
 * </ul>
 */
public class Tog {
    /**
     * Removes all oracles from the EvoSuite tests generated for a given
     * class.
     *
     * @param fullyQualifiedName the class under test
     */
    private static void removeOracles(
            String fullyQualifiedName
    ) {
    }

    /**
     * Inserts the OracleOutput records generated by a TOG into the test
     * prefixes generated by EvoSuite.
     *
     * @param togType a TOG
     * @param jar the JAR of the project under test
     */
    private static void insertOracles(
            TogType togType,
            Path jar
    ) {
    }

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
     * @param srcDir the source directory of the project under test
     */
    private static void generateOracleOutput(
            TogType togType,
            Path srcDir
    ) {
    }

    /**
     * Creates a TestOutput record to summarize a test suite generated by a
     * TOG.
     *
     * @param togType a TOG
     * @param fullyQualifiedName the class under test
     * @param srcDir the source directory of the project under test
     * @param binDir the system binaries of the project under test
     * @param fileName the name of the saved TestOutput record
     */
    private static void generateTestOutput(
            TogType togType,
            String fullyQualifiedName,
            Path srcDir,
            Path binDir,
            String fileName
    ) {
    }

    /**
     * Creates a Defects4JOutput record to get statistics for a test suite
     * generated by a TOG for a class in a given bug in a given project
     *
     * @param togType a TOG
     * @param projectName the name of the project under test
     * @param bugID the bug id
     * @param fullyQualifiedName the class under test
     */
    private static void generateDefects4JOutput(
            TogType togType,
            String projectName,
            int bugID,
            String fullyQualifiedName
    ) {
    }

    /**
     * Combines all Defects4JOutputs into a singular Defects4JOutput record
     * that summarizes all bugs in all projects.
     *
     * @param togType a TOG
     */
    private static void combineDefects4JOutput(
            TogType togType
    ) {
    }

    /**
     * The main method handles all invocations of the JAR file.
     *
     * @param args arguments passed for each JAR invocation
     */
    public static void main(String[] args) {
        OperationType operationType = OperationType.valueOf(args[0].toUpperCase());
        switch (operationType) {
            case REMOVE_ORACLES -> removeOracles(
                    args[2]
            );
            case INSERT_ORACLES -> insertOracles(
                    TogType.valueOf(args[1].toUpperCase()),
                    Paths.get(args[2])
            );
            case GENERATE_TOG_INPUT -> generateTogInput(
                    TogType.valueOf(args[1].toUpperCase()),
                    args[2],
                    Paths.get(args[3])
            );
            case GENERATE_ORACLE_OUTPUT -> generateOracleOutput(
                    TogType.valueOf(args[1].toUpperCase()),
                    Paths.get(args[2])
            );
            case GENERATE_TEST_OUTPUT -> generateTestOutput(
                    TogType.valueOf(args[1].toUpperCase()),
                    args[2],
                    Paths.get(args[3]),
                    Paths.get(args[4]),
                    args[5]
            );
            case GENERATE_DEFECTS4J_OUTPUT -> generateDefects4JOutput(
                    TogType.valueOf(args[1].toUpperCase()),
                    args[2],
                    Integer.parseInt(args[3]),
                    args[4]
            );
            case COMBINE_DEFECTS4J_OUTPUT -> combineDefects4JOutput(
                    TogType.valueOf(args[1].toUpperCase())
            );
            default -> throw new IllegalArgumentException("Unknown operation " + operationType);
        }
    }
}
