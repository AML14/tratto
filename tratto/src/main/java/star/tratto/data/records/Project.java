package star.tratto.data.records;

/**
 * This record stores the basic file I/O information for a project in the
 * resources directory.
 *
 * @param projectName the name of the Java project
 * @param projectPath the path to the Java project root folder
 * @param jarPath the path to the folder that contains the source code of the
 *                project
 * @param jDoctorConditionsPath the path to the folder that contains the
 *                              JDoctor conditions
 * @param srcPath the path to the folder that contains the jar file of the
 *                Java project
 */
public record Project(
        String projectName,
        String projectPath,
        String jarPath,
        String jDoctorConditionsPath,
        String srcPath
) {}
