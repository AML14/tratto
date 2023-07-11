package star.tratto.data.oracles.json.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import star.tratto.data.oracles.Project;
import star.tratto.identifiers.IOPath;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * This class deserializes JSON objects into {@link Project} objects.
 */
public class ProjectDeserializer extends JsonDeserializer<Project> {
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
        // Extract each information from the input project JSON object
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        // Get the project name
        String projectName = node.get("projectName").asText();
        // Get the list of folder names to navigate from the project root folder to the directory of the project
        List<String> projetDirPathList = deserializeList((ArrayNode) node.get("dirPathList"));
        // Get the list of folder names to navigate from the project root folder to the directory that contains the jar
        // file of the project
        List<String> jarPathList = deserializeList((ArrayNode) node.get("jarPathList"));
        // Get the list of folder names to navigate from the project root folder to the directory that contains the
        // JDoctor conditions of the project
        List<String> jDocConditionsPathList = deserializeList((ArrayNode) node.get("jDocConditionsPathList"));
        // Get the list of folder names to navigate from the project root folder to the directory that contains the
        // source code of the project
        List<String> srcPathList = deserializeList((ArrayNode) node.get("srcPathList"));
        // Transform the lists into string representations of the paths
        String projectPath = Paths.get(IOPath.RESOURCES.getValue(), projetDirPathList.toArray(new String[0])).toString();
        String jarPath = Paths.get(projectPath, jarPathList.toArray(new String[0])).toString();
        String jDocConditionsPath = Paths.get(projectPath, jDocConditionsPathList.toArray(new String[0])).toString();
        String srcPath = Paths.get(projectPath, srcPathList.toArray(String[]::new)).toString();
        // Generate an instance of InputProject
        Project project = new Project(
                projectName,
                projectPath,
                jarPath,
                jDocConditionsPath,
                srcPath
        );
        // Return the project generated
        return project;
    }

    /**
     * The method deserializes a JSON list of strings into a Java list of strings.
     * @param attributeArrayNode the Jackson array node {@link ArrayNode} representing the JSON list of strings
     *                           to deserialize
     * @return a Java list of strings representing the deserialization of the JSON list of strings
     */
    private List<String> deserializeList(ArrayNode attributeArrayNode) {
        List<String> attributePath = new ArrayList<>();
        for (JsonNode element : attributeArrayNode) {
            attributePath.add(element.asText());
        }
        return attributePath;
    }
}
