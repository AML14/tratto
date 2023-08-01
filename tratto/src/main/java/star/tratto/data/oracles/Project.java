package star.tratto.data.oracles;

import java.nio.file.Path;

/**
 * Information about a Java project.
 */
public class Project {
    // the name of the Java project
    private final String projectName;
    // the path to the project root folder
    private final Path rootPath;
    // the path to the directory with the project jar file
    private final Path jarPath;
    // the path to the directory of JDoctor conditions for the project
    private final Path conditionsPath;
    // the path to the project source code directory
    private final Path srcPath;

    /**
     * Creates a new {@link Project} object.
     *
     * @param projectName the name of the Java project
     * @param rootPath the path to the Java project root folder
     * @param jarPath the path to the folder that contains the jar file of the Java project
     * @param conditionsPath the path to the folder that contains the JDoctor conditions produced for the
     *                              Java project
     * @param srcPath the path to the folder that contains the source code of the project
     */
    public Project(
            String projectName,
            Path rootPath,
            Path jarPath,
            Path conditionsPath,
            Path srcPath
    ) {
        this.projectName = projectName;
        this.rootPath = rootPath;
        this.jarPath = jarPath;
        this.conditionsPath = conditionsPath;
        this.srcPath = srcPath;
    }

    public String getProjectName() {
        return this.projectName;
    }

    public Path getRootPath() {
        return this.rootPath;
    }

    public Path getJarPath() {
        return this.jarPath;
    }

    public Path getConditionsPath() {
        return this.conditionsPath;
    }

    public Path getSrcPath() {
        return this.srcPath;
    }
}

