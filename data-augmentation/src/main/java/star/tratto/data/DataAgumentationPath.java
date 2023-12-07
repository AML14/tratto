package star.tratto.data;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * An enum to store useful directories and file names used in the database generation process.
 */
public enum DataAgumentationPath {
    REPOS(Paths.get("src", "main", "java", "star", "tratto", "data", "repos")),

    RESOURCES(Paths.get("src", "main", "resources")),

    PROJECTS_SOURCE(DataAgumentationPath.RESOURCES.getPath().resolve("projects-source")),
    DATA_ENTRIES(Paths.get("src", "main", "resources", "data-entries")),
    INPUT_PROJECTS(DataAgumentationPath.REPOS.getPath().resolve("input_projects.json")),
    IGNORE_FILE(DataAgumentationPath.REPOS.getPath().resolve("ignore_file.json"));

    private final Path path;

    DataAgumentationPath(Path path) {
        this.path = path;
    }

    public Path getPath() {
        return this.path;
    }
}
