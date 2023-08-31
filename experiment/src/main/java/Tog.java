import data.OperationType;
import data.TogType;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Tog {

    public static void main(String[] args) {
        TogType togType = TogType.valueOf(args[0].toUpperCase());
        OperationType operationType = OperationType.valueOf(args[1].toUpperCase());
        if (operationType == OperationType.REMOVE_ORACLES) {
            Path sourceDir = Paths.get(args[2]);
            String fullyQualifiedClassName = args[3];
            TestUtils.removeOracles(sourceDir, fullyQualifiedClassName);
        } else if (operationType == OperationType.GENERATE_TOG_INPUTS) {
            if (togType == TogType.JDOCTOR) { }
            if (togType == TogType.TOGA) {
                Path sourceDir = Paths.get(args[2]);
                String fullyQualifiedClassName = args[3];
                TogUtils.generateTOGAInput(sourceDir,fullyQualifiedClassName);
            }
            if (togType == TogType.TRATTO) { }
        } else if (operationType == OperationType.GENERATE_ORACLE_OUTPUTS) {
            if (togType == TogType.JDOCTOR) { }
            if (togType == TogType.TOGA) {
                Path sourceDir = Paths.get(args[2]);
                TogUtils.mapTOGAOutputToOracleOutput(sourceDir);
            }
            if (togType == TogType.TRATTO) {
                Path sourceDir = Paths.get(args[2]);
                TogUtils.mapTrattoOutputToOracleOutput(sourceDir);
            }
        } else if (operationType == OperationType.INSERT_ORACLES) { }
    }
}
