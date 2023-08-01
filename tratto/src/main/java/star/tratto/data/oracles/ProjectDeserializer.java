package star.tratto.data.oracles;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import star.tratto.data.TrattoPath;
import star.tratto.data.records.Project;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * This class deserializes JSON objects into {@link Project} objects.
 */
public class ProjectDeserializer extends JsonDeserializer<Project> {
    /**
     * Gets a Path from a JSON list of strings.
     *
     * @param arrayNode the Jackson array node representing the JSON list of
     *                  strings to deserialize
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
     * The method deserializes a JSON project object and generate a corresponding Java Project record {@link Project}.
     * @param jsonParser the json parser used to deserialize the JSON project object
     * @param deserializationContext the deserialization context requested
     *                               to override the method (not used in our case)
     * @return a Java Project record {@link Project}, corresponding to the Java deserialization of a JSON project object
     * @throws IOException if there is an error in reading the tree of the json Parser {@link JsonParser} object
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
