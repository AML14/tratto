package star.tratto.records;

import java.nio.file.Path;
import java.util.List;

/**
 * This record stores the basic file I/O information for a project in the resources directory.
 *
 * @param projectName the name of the Java project
 * @param rootPath the path to the project root folder
 * @param srcPath the path to the project source code directory
 */
public record Project(
        String projectName,
        String githubLink,
        Path rootPath,
        Path srcPath,
        List<String> fullyQualifiedClassNameList
) {}
