package star.tratto.data.records;

import java.nio.file.Path;

/**
 * This record stores the basic file I/O information for a project in the
 * resources directory.
 *
 * @param projectName the name of the Java project
 * @param rootPath the project root directory
 * @param jarPath the directory with the project jar file
 * @param conditionsPath the directory of JDoctor conditions for the project
 * @param srcPath the project source code directory
 */
public record Project(
        String projectName,
        Path rootPath,
        Path jarPath,
        Path conditionsPath,
        Path srcPath
) {}
