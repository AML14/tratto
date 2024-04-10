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
        if (togType == TogType.TOGA) {
            TogUtils.generateTOGAInput(srcDir, fullyQualifiedName);
        }
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
        if (togType == TogType.JDOCTOR) {
            TogUtils.jDoctorToOracleOutput(path);
        } else if (togType == TogType.TOGA) {
            TogUtils.togaToOracleOutput(path);
        } else if (togType == TogType.TRATTO) {
            TogUtils.trattoToOracleOutput(path, srcPath);
        }
    }

    /**
     * The main method handles all invocations of the JAR file.
     *
     * @param args arguments passed for each JAR invocation
     */
    public static void main(String[] args) {
        OperationType operationType = OperationType.valueOf(args[0].toUpperCase());
        switch (operationType) {
            case REMOVE_ORACLES -> OracleRemover.removeOracles(Paths.get(args[1]));
            case INSERT_ORACLES -> OracleInserter.insertOracles(
                    Paths.get(args[1]),
                    Paths.get(args[2]),
                    args[3]
            );
            case GENERATE_TOG_INPUT -> generateTogInput(
                    TogType.valueOf(args[1].toUpperCase()),
                    args[2],
                    Paths.get(args[3])
            );
            case GENERATE_ORACLE_OUTPUT -> generateOracleOutput(
                    TogType.valueOf(args[1].toUpperCase()),
                    Paths.get(args[2]),
                    args.length > 3 ? Paths.get(args[3]) : null
            );
            case GENERATE_DEFECTS4J_OUTPUT -> TogUtils.generateDefects4JOutput(
                    TogType.valueOf(args[1].toUpperCase()),
                    Paths.get(args[2]),
                    Paths.get(args[3])
            );
            case COUNT_TEST_METHODS -> TogUtils.countTestMethods(
                    Paths.get(args[1]),
                    args[2],
                    args[3],
                    args[4]
            );
            default -> throw new IllegalArgumentException("Unknown operation " + operationType);
        }
    }
}
