package star.tratto.dataset.oracles.json.parsers;

import com.fasterxml.jackson.databind.ObjectMapper;
import star.tratto.dataset.oracles.JDoctorCondition;
import star.tratto.dataset.oracles.Project;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * A helper class that deserialize the JDoctor conditions in JSON format, generating the corresponding Java record
 * representations.
 */
public class JDoctorConditionParser {
    /**
     * Given a Java project the method reads all the JSON files containing JDoctor conditions in JSON format,
     * and returns an array of {@link JDoctorCondition} objects, representing the corresponding Java records of all the
     * JDoctor conditions of the project.
     *
     * @param project The Java project associated to the JDoctor conditions to parse.
     * @return An array of {@link JDoctorCondition} objects, representing the Java record representations of the original
     * JDoctor conditions in the JSON format
     */
    public JDoctorCondition[] parseJDoctorConditions(Project project) {
        File jDocConditionsDir = new File(project.getjDoctorConditionsPath());
        File[] jDocConditionsFiles = jDocConditionsDir.listFiles();

        JDoctorCondition[] jDocConditions = Arrays.stream(jDocConditionsFiles)
                .filter(jDocConditionFile -> jDocConditionFile.getName().endsWith(".json"))
                .map(jDocConditionFile -> json2java(jDocConditionFile))
                .flatMap(Arrays::stream)
                .toArray(JDoctorCondition[]::new);
        return jDocConditions;
    }

    /**
     * The method reads a JSON file containing a list of JDoctor conditions, and maps it to a Java array of {@link JDoctorCondition}.
     *
     * @param jDoctorConditionsFile A file {@link File} that points to the JSON file that contains the list of JDoctor
     *                              conditions.
     * @return An array of {@link JDoctorCondition} representing the Java record representations of the original JDoctor
     * conditions in the JSON format, of the file passed to the function.
     */
    private JDoctorCondition[] json2java(File jDoctorConditionsFile) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JDoctorCondition[] conditions = objectMapper.readValue(
                    jDoctorConditionsFile,
                    JDoctorCondition[].class
            );
            return conditions;
        } catch (IOException e) {
            e.printStackTrace();
            return new JDoctorCondition[0];
        }
    }
}
