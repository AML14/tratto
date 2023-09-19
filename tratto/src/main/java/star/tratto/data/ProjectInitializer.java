package star.tratto.data;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ArrayNode;
import star.tratto.data.records.Project;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A helper class that provides a static method to initialize all projects as
 * a list of {@link Project} records from a JSON file.
 */
public class ProjectInitializer {
    /**
     * This helper class uses the Jackson framework to deserialize a JSON
     * object into a {@link Project}.
     */
    private static class ProjectDeserializer extends JsonDeserializer<Project> {
        /**
         * Gets a Path from a JSON list of path elements.
         *
         * @param arrayNode an array node representing a list of path elements
         * @return the path corresponding to the joined elements of the JSON list
         */
        private Path arrayNodeToPath(ArrayNode arrayNode) {
            List<String> pathElements = new ArrayList<>();
            for (JsonNode element : arrayNode) {
                pathElements.add(element.asText());
            }
            String pathString = String.join(FileSystems.getDefault().getSeparator(), pathElements);
            return FileSystems.getDefault().getPath(pathString);
        }

        /**
         * Deserializes a JSON object into a {@link Project} record.
         *
         * @param jsonParser a parser used to deserialize a JSON object
         * @param deserializationContext the deserialization context requested
         *                               to override the method
         * @return a {@link Project} record, corresponding to the given JSON
         * object
         * @throws IOException if an error occurs reading the tree of the
         * json parser
         */
        @Override
        public Project deserialize(
                JsonParser jsonParser,
                DeserializationContext deserializationContext
        ) throws IOException {
            JsonNode node = jsonParser.getCodec().readTree(jsonParser);
            String projectName = node.get("projectName").asText();
            Path projectPath = TrattoPath.RESOURCES.getPath().resolve(arrayNodeToPath((ArrayNode) node.get("dirPathList")));
            Path jarPath = projectPath.resolve(arrayNodeToPath((ArrayNode) node.get("jarPathList")));
            Path conditionsPath = projectPath.resolve(arrayNodeToPath((ArrayNode) node.get("jDocConditionsPathList")));
            Path srcPath = projectPath.resolve(arrayNodeToPath((ArrayNode) node.get("srcPathList")));
            return new Project(
                    projectName,
                    projectPath,
                    jarPath,
                    conditionsPath,
                    srcPath
            );
        }
    }

    /** Private constructor to avoid creating an instance of this class. */
    private ProjectInitializer() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    /**
     * Gets a list of {@link Project} records from a JSON file.
     *
     * @param jsonPath path to the JSON representation of projects
     * @return the corresponding {@link Project} records
     */
    public static List<Project> initialize(Path jsonPath) {
        // initialize ObjectMapper with custom deserializer
        ObjectMapper objectMapper = new ObjectMapper();
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
        objectMapper.registerModule(module);
        // read JSON object to Project
        try {
            List<Project> projectList = objectMapper.readValue(
                    jsonPath.toFile(),
                    new TypeReference<>() {}
            );
            return projectList
                    .stream()
                    .filter(project -> {
                        Path rootDir = project.rootPath();
                        return Files.exists(rootDir) && Files.isDirectory(rootDir);
                    })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new Error("Error in processing the JSON file " + jsonPath, e);
        }
    }
}
