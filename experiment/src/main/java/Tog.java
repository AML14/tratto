import data.TogType;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Tog {

    public static void main(String[] args) {
        TogType togType = TogType.valueOf(args[0].toUpperCase());
        if ((!(togType == TogType.TOGA) && args.length != 4) || (togType == TogType.TOGA && (args.length != 4 && args.length != 2))) {
            throw new RuntimeException(String.format("Illegal number of arguments provided to the program (expected: 4, actual: %d)", args.length));
        }
        try{
            if (togType == TogType.JDOCTOR) {

            }
            if (togType == TogType.TOGA) {
                if (args.length == 4) {
                    Path sourceDir = Paths.get(args[1]);
                    Path targetClassPath = Paths.get(args[2]);
                    Path testClassPath = Paths.get(args[3]);
                    TogUtils.generateTOGAInput(sourceDir, targetClassPath, testClassPath);
                } else if (args.length == 2) {
                    Path sourceDir = Paths.get(args[1]);
                    TogUtils.mapTOGAOutputToOracleOutput(sourceDir);
                }
            }
            if (togType == TogType.TRATTO) {

            }
        } catch (IllegalArgumentException e) {
            String errMsg = String.format("Invalid TOG value provided. Accepted values: [jdoctor, toga, tratto]. Actual: %s", args[0]);
            System.err.println(errMsg);
        }
    }
}
