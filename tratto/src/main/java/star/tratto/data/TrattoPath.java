package star.tratto.data;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * An enum to store useful directories and file names used in the dataset
 * generation process.
 */
public enum TrattoPath {
    // directories
    ORACLES_DATASET(Paths.get("src", "main", "resources", "oracles-dataset")),
    OUTPUT(Paths.get("target", "output")),
    OUTPUT_DATASET(Paths.get("target", "output", "dataset")),
    REPOS(Paths.get("src", "main", "java", "star", "tratto", "data", "repos")),
    RESOURCES(Paths.get("src", "main", "resources", "projects-source")),
    // files
    ARRAY_METHODS(TrattoPath.REPOS.getPath().resolve("array_methods.json")),
    INPUT_PROJECTS(TrattoPath.REPOS.getPath().resolve("input_projects.json")),
    NON_VARIABLE_TOKENS(TrattoPath.REPOS.getPath().resolve("non_variable_tokens.json")),
    TOKENS_GENERAL_VALUES(TrattoPath.REPOS.getPath().resolve("tokens_general_values.json")),
    TOKENS_GRAMMAR(TrattoPath.REPOS.getPath().resolve("tokens_grammar.json"));

    private final Path path;

    TrattoPath(Path path) {
        this.path = path;
    }

    public Path getPath() {
        return this.path;
    }
}
