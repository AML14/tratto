package star.tratto.data;

import java.nio.file.Path;
import java.nio.file.Paths;

public enum TrattoPath {
    ORACLES_DATASET("oracles-dataset"),
    OUTPUT("output"),
    OUTPUT_DATASET("output/dataset"),
    REPOS("repos"),
    RESOURCES("projects-source"),
    ARRAY_METHODS("repos/array_methods.json"),
    INPUT_PROJECTS("repos/input_projects.json"),
    NON_VARIABLE_TOKENS("repos/non_variable_tokens.json"),
    TOKENS_GENERAL_VALUES("repos/tokens_general_values.json"),
    TOKENS_GRAMMAR("repos/tokens_grammar.json");

    private final String relativePath;

    TrattoPath(String relativePath) {
        this.relativePath = relativePath;
    }

    public Path getPath() {
        return Paths.get(getAbsoluteDirectory(), relativePath);
    }

    private String getAbsoluteDirectory() {
        try {
            return Paths.get(TrattoPath.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent().toString();
        } catch (Exception e) {
            e.printStackTrace();
            return ""; // Handle exception properly in your code
        }
    }
}