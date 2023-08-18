package star.tratto.data.records;

import java.nio.file.Path;

/**
 * This record stores the basic file I/O information for a project in the
 * resources directory.
 *
 * @param projectName the name of the Java project
 * @param rootPath the path to the project root folder
 * @param jarPath the path to the directory with the project jar file
 * @param conditionsPath the path to the directory of JDoctor conditions for
 *                       the project
 * @param srcPath the path to the project source code directory
 */
public record Project(
        String projectName,
        Path rootPath,
        Path jarPath,
        Path conditionsPath,
        Path srcPath
) {}
