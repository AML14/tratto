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
    REPOS(Paths.get("src", "main", "resources", "repos")),
    RESOURCES(Paths.get("src", "main", "resources", "projects-source")),
    // files
    ARRAY_METHODS(TrattoPath.REPOS.getRelativePath().resolve("array_methods.json")),
    INPUT_PROJECTS(TrattoPath.REPOS.getRelativePath().resolve("input_projects.json")),
    NON_VARIABLE_TOKENS(TrattoPath.REPOS.getRelativePath().resolve("non_variable_tokens.json")),
    TOKENS_GENERAL_VALUES(TrattoPath.REPOS.getRelativePath().resolve("tokens_general_values.json")),
    TOKENS_GRAMMAR(TrattoPath.REPOS.getRelativePath().resolve("tokens_grammar.json")),
    GLOBAL_DICTIONARY(TrattoPath.REPOS.getRelativePath().resolve("global-dictionary.json"));

    private final Path path;

    TrattoPath(Path path) {
        this.path = path;
    }

    public Path getPath() {
        return Paths.get(System.getProperty("user.dir"), this.path.toString());
    }

    public Path getRelativePath() {
        String relativePath = this.path.toString().replace(System.getProperty("user.dir"), "");
        return Paths.get(relativePath);
    }
}
