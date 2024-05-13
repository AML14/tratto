import data.OperationType;
import data.TogType;
import data.TogaInputType;

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
            Path outputDir,
            Path srcDir,
            Path jarDir
    ) {
        if (togType == TogType.TOGA) {
            TogUtils.generateTOGAInput(srcDir, jarDir, outputDir, fullyQualifiedName, TogaInputType.LAST_OCCURRENCE);
        }
    }

    /**
     * Converts a TOG output to a list of OracleOutput records.
     *
     * @param togType a TOG
     * @param togOraclesPath a path to a file or directory for generating OracleOutputs
     * @param outputDirPath the path to the output folder (must not be null if the tog type is Toga)
     * @param jarDirPath the directory to the jars of the project under test and its dependencies (must not be null if
     *                   the tog type is Tratto).
     */
    private static void generateOracleOutput(
            TogType togType,
            Path togOraclesPath,
            Path outputDirPath,
            Path jarDirPath
    ) {
        if (togType == TogType.JDOCTOR) {
            TogUtils.jDoctorToOracleOutput(togOraclesPath);
        } else if (togType == TogType.TOGA) {
            TogUtils.togaToOracleOutput(togOraclesPath, outputDirPath);
        } else if (togType == TogType.TRATTO) {
            TogUtils.trattoToOracleOutput(togOraclesPath, jarDirPath);
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
                    Paths.get(""),
                    args[3]
            );
            case GENERATE_TOG_INPUT -> generateTogInput(
                    TogType.valueOf(args[1].toUpperCase()),
                    args[2],
                    Paths.get(args[3]),
                    Paths.get(args[4]),
                    Paths.get(args[5])
            );
            case GENERATE_ORACLE_OUTPUT -> generateOracleOutput(
                    TogType.valueOf(args[1].toUpperCase()),
                    Paths.get(args[2]),
                    args.length > 3 && TogType.valueOf(args[1].toUpperCase()) == TogType.TOGA ? Paths.get(args[3]) : null,
                    args.length > 3 && TogType.valueOf(args[1].toUpperCase()) == TogType.TRATTO ? Paths.get(args[3]) : null
            );
            case GENERATE_DEFECTS4J_OUTPUT -> TogUtils.generateDefects4JOutput(
                    TogType.valueOf(args[1].toUpperCase()),
                    args[2],
                    args[3],
                    args[4],
                    Paths.get(args[5]),
                    Paths.get(args[6])
            );
            case COUNT_TEST_METHODS -> TogUtils.countTestMethods(
                    Paths.get(args[1]),
                    Paths.get(args[2]),
                    args[3],
                    args[4],
                    args[5]
            );
            default -> throw new IllegalArgumentException("Unknown operation " + operationType);
        }
    }
}
