package data.collection.parsers;

import data.collection.models.Project;
import data.collection.models.JDocCondition;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.Arrays;

/**
 * A helper class that deserialize the JDoctor conditions in JSON format, generating the corresponding Java record
 * representations.
 */
public class JDocConditionParser {
    /**
     * Given a Java project the method reads all the JSON files containing JDoctor conditions in JSON format,
     * and returns an array of {@link JDocCondition} objects, representing the corresponding Java records of all the
     * JDoctor conditions of the project.
     *
     * @param project The Java project associated to the JDoctor conditions to parse.
     * @return An array of {@link JDocCondition} objects, representing the Java record representations of the original
     * JDoctor conditions in the JSON format
     */
    public JDocCondition[] parseJDocConditions(Project project) {
        File jDocConditionsDir = new File(project.jDocConditionsPath());
        File[] jDocConditionsFiles = jDocConditionsDir.listFiles();

        JDocCondition[] jDocConditions = Arrays.stream(jDocConditionsFiles)
                .filter(jDocConditionFile -> jDocConditionFile.getName().endsWith(".json"))
                .map(jDocConditionFile -> json2java(jDocConditionFile))
                .flatMap(Arrays::stream)
                .toArray(JDocCondition[]::new);
        return jDocConditions;
    }

    /**
     * The method reads a JSON file containing a list of JDoctor conditions, and maps it to a Java array of {@link JDocCondition}.
     *
     * @param jDoctorConditionsFile A file {@link File} that points to the JSON file that contains the list of JDoctor
     *                              conditions.
     * @return An array of {@link JDocCondition} representing the Java record representations of the original JDoctor
     * conditions in the JSON format, of the file passed to the function.
     */
    private JDocCondition[] json2java(File jDoctorConditionsFile) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JDocCondition[] conditions = objectMapper.readValue(
                    jDoctorConditionsFile,
                    JDocCondition[].class
            );
            return conditions;
        } catch (IOException e) {
            e.printStackTrace();
            return new JDocCondition[0];
        }
    }
}
