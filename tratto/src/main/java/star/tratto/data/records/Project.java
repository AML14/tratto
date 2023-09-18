package star.tratto.data.records;

import java.nio.file.Path;

/**
 * This record stores the basic file I/O information for a project in the
 * resources directory.
 */
public record Project(
        /* The name of the Java project. */
        String projectName,
        /* The project root directory. */
        Path rootPath,
        /* The directory with the project JAR file. */
        Path jarPath,
        /* The directory of JDoctor conditions for the project.  */
        Path conditionsPath,
        /* The project source code directory. */
        Path srcPath
) {}
