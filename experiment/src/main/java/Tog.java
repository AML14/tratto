import data.TogType;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Tog {

    public static void main(String[] args) {
        if (args.length != 4) {
            throw new RuntimeException(String.format("Illegal number of arguments provided to the program (expected: 4, actual: %d)", args.length));
        }
        try{
            TogType togType = TogType.valueOf(args[0].toUpperCase());
            Path sourceDir = Paths.get(args[1]);
            Path targetClassPath = Paths.get(args[2]);
            Path testClassPath = Paths.get(args[3]);
            if (togType == TogType.JDOCTOR) {

            }
            if (togType == TogType.TOGA) {
                TogUtils.generateTOGAInput(sourceDir, targetClassPath, testClassPath);
            }
            if (togType == TogType.TRATTO) {

            }
        } catch (IllegalArgumentException e) {
            String errMsg = String.format("Invalid TOG value provided. Accepted values: [jdoctor, toga, tratto]. Actual: %s", args[0]);
            System.err.println(errMsg);
        }
    }
}
