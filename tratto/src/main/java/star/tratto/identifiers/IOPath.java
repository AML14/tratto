package star.tratto.identifiers;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * An enum to store useful directories and file names used in the dataset
 * generation process.
 */
public enum IOPath {
    // directories
    ORACLES_DATASET(Paths.get("src", "main", "resources", "oracles-dataset")),
    OUTPUT(Paths.get("target", "output")),
    OUTPUT_DATASET(Paths.get("target", "output", "dataset")),
    REPOS(Paths.get("src", "main", "java", "star", "tratto", "data", "repos")),
    RESOURCES(Paths.get("src", "main", "resources", "projects-source")),
    // files
    ARRAY_METHODS(IOPath.REPOS.getPath().resolve("array_methods.json")),
    IGNORE_FILE(IOPath.REPOS.getPath().resolve("ignore_file.json")),
    INPUT_PROJECTS(IOPath.REPOS.getPath().resolve("input_projects.json")),
    NON_VARIABLE_TOKENS(IOPath.REPOS.getPath().resolve("non_variable_tokens")),
    ORACLE_DATAPOINTS(IOPath.REPOS.getPath().resolve("oracle_datapoints")),
    TOKENS_GENERAL_VALUES(IOPath.REPOS.getPath().resolve("tokens_general_values")),
    TOKENS_GRAMMAR(IOPath.REPOS.getPath().resolve("tokens_grammar"));

    private final Path path;

    IOPath(Path path) {
        this.path = path;
    }

    public Path getPath() {
        return this.path;
    }
}
