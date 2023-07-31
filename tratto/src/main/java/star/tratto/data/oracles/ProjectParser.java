package star.tratto.data.oracles;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.module.SimpleModule;
import star.tratto.data.oracles.Project;
import star.tratto.data.oracles.ProjectDeserializer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A helper class that deserializes the project's information in JSON format
 * and generates the corresponding Java representations.
 */
public class ProjectParser {
    /**
     * Gets a list of project objects from a JSON list of project paths.
     *
     * @param jsonProjects file of projects to analyze
     * @return list of project information
     */
    public static List<Project> initialize(Path jsonProjects) {
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
                    jsonProjects.toFile(),
                    new TypeReference<>() {}
            );
            return projectList
                    .stream()
                    .filter(p -> {
                        Path projectDir = Paths.get(p.getProjectPath());
                        return Files.exists(projectDir) && Files.isDirectory(projectDir);
                    })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println("Unexpected error in processing the JSON file of the input projects.");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
