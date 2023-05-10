package star.tratto.dataset.oracles.json.parsers;

import com.fasterxml.jackson.databind.ObjectMapper;
import star.tratto.dataset.oracles.JDoctorCondition;
import star.tratto.dataset.oracles.Project;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A helper class that deserialize the JDoctor conditions in JSON format, generating the corresponding Java record
 * representations.
 */
public class JDoctorConditionParser {
    /**
     * Given a Java project the method reads all the JSON files containing
     * JDoctor conditions in JSON format, and returns an array of
     * {@link JDoctorCondition} objects, representing the corresponding Java
     * records of all the JDoctor conditions of the project.
     *
     * @param project The Java project associated to the JDoctor conditions
     *                to parse.
     * @return An array of {@link JDoctorCondition} objects, representing the
     * Java record representations of the original JDoctor conditions in the
     * JSON format.
     */
    public List<JDoctorCondition> parseJDoctorConditions(Project project) {
        // get path of JDoctor conditions.
        File jDoctorConditionsDir = new File(project.getjDoctorConditionsPath());
        // get all JSON files related to JDoctor conditions.
        File[] jDoctorConditionsFiles = jDoctorConditionsDir.listFiles();
        // convert each JSON file to list of JDoctorConditions.
        if (jDoctorConditionsFiles == null) return new ArrayList<>();
        return Arrays.stream(jDoctorConditionsFiles)
                .filter(jDoctorConditionsFile -> jDoctorConditionsFile.getName().endsWith(".json"))
                .map(this::json2java)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    /**
     * The method reads a JSON file containing a list of JDoctor conditions, and maps it to a Java array of {@link JDoctorCondition}.
     *
     * @param jDoctorConditionsFile A file {@link File} that points to the JSON file that contains the list of JDoctor
     *                              conditions.
     * @return An array of {@link JDoctorCondition} representing the Java record representations of the original JDoctor
     * conditions in the JSON format, of the file passed to the function.
     */
    private List<JDoctorCondition> json2java(File jDoctorConditionsFile) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JDoctorCondition[] conditions = objectMapper.readValue(
                    jDoctorConditionsFile,
                    JDoctorCondition[].class
            );
            return Arrays.asList(conditions);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
