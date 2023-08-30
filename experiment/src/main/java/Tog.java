import data.OperationType;
import data.TogType;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Tog {
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
     * @param srcDir the directory to the project source code
     */
    private static void generateOracleOutputOperation(TogType togType, Path srcDir) {
        if (togType == TogType.TOGA) {
            TogUtils.mapTOGAOutputToOracleOutput(srcDir);
        } else if (togType == TogType.JDOCTOR) {
            TogUtils.jDoctorToOracleOutput();
        }
    }

    public static void main(String[] args) {
        TogType togType = TogType.valueOf(args[0].toUpperCase());
        OperationType operationType = OperationType.valueOf(args[1].toUpperCase());
        Path srcDir = Paths.get(args[2]);
        String fullyQualifiedName = args[3];
        switch (operationType) {
            case REMOVE_ORACLES -> TestUtils.removeOracles(srcDir, fullyQualifiedName);
            case INSERT_ORACLES -> throw new IllegalArgumentException("Currently invalid operation");
            case GENERATE_TOG_INPUTS -> generateTogInputOperation(togType, srcDir, fullyQualifiedName);
            case GENERATE_ORACLE_OUTPUTS -> generateOracleOutputOperation(togType, srcDir);
            default -> throw new IllegalArgumentException("Unknown operation " + operationType);
        }
    }
}
