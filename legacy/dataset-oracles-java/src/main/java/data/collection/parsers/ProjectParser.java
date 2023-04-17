package data.collection.parsers;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.module.SimpleModule;
import data.collection.deserializers.ProjectDeserializer;
import data.collection.models.Project;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A helper class that deserialize the projects information in JSON format, generating the corresponding Java record
 * representations.
 */
public class ProjectParser {

    /**
     * Given the path to the JSON file that lists the Java projects to analyze, the method deserializes the list of
     * JSON project objects contained in the file and generates a list of corresponding Java project records {@link Project}
     * @return A list of Java project records {@link Project}, representing the deserialization of the list of the JSON
     * project objects contained in the JSON file pointed by the string path passed to the function.
     *
     * @throws IOException If the JSON file cannot be read.
     */
    public static List<Project> initialize(String jsonFilePath) {
        // Instantiate an *ObjectMapper* to parse a JSON file
        ObjectMapper objectMapper = new ObjectMapper();
        // Generate a specific deserializer for the list of input projects JSON objects
        // that the system must parse
        SimpleModule module = new SimpleModule();
        module.setDeserializerModifier(new BeanDeserializerModifier() {
            @Override
            public JsonDeserializer<?> modifyDeserializer(
                    DeserializationConfig config,
                    BeanDescription beanDesc,
                    JsonDeserializer<?> deserializer
            ) {
                if (beanDesc.getBeanClass() == Project.class) {
                    return new ProjectDeserializer();
                }
                return super.modifyDeserializer(config, beanDesc, deserializer);
            }
        });
        // Register the generated deserializer
        objectMapper.registerModule(module);
        // Parse the JSON file and generate a list of input projects
        try {
            List<Project> projectList = objectMapper.readValue(
                    new File(jsonFilePath),
                    new TypeReference<List<Project>>() {
                    }
            );
            // Filter the projects that effectively exists in the resources
            List<Project> existingProjects = checkProjectsExist(projectList);
            return existingProjects;
        } catch (IOException e) {
            System.err.println("Unexpected error in processing the JSON file of the input projects.");
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * The method checks that each Java project record in the list passed to function points to a real Java project
     * saved within the current Java program.
     * @param projects The list of Java project records to check.
     * @return A list containing the only Java project records that point to real Java project saved within the current
     * Java program.
     */
    private static List<Project> checkProjectsExist(List<Project> projects) {
        List<Project> existingProjects = new ArrayList<Project>();
        for(Project project : projects) {
            File projectDir = new File(project.projectPath());
            if (projectDir.exists() && projectDir.isDirectory()) {
                existingProjects.add(project);
            } else {
                String errorMessage = String.format(
                        "The project %s is not present within the resources folder.",
                        project.projectName()
                );
                System.err.println(errorMessage);
            }
        }
        return existingProjects;
    }
}
