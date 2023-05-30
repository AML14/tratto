package star.tratto.dataset.oracles;

/**
 * Information about a Java project.
 */
public class Project {
    private String projectName;
    private String projectPath;
    private String jarPath;
    private String jDoctorConditionsPath;
    private String srcPath;

    /**
     * Constructor: instantiate a new {@link Project} object.
     *
     * @param projectName The name of the Java project.
     * @param projectPath The path to the Java project root folder.
     * @param jarPath The path to the folder that contains the jar file of the Java project.
     * @param jDoctorConditionsPath The path to the folder that contains the JDoctor conditions produced for the
     *                              Java project.
     * @param srcPath The path to the folder that contains the source code of the project.
     */
    public Project(
            String projectName,
            String projectPath,
            String jarPath,
            String jDoctorConditionsPath,
            String srcPath
    ) {
        this.projectName = projectName;
        this.projectPath = projectPath;
        this.jarPath = jarPath;
        this.jDoctorConditionsPath = jDoctorConditionsPath;
        this.srcPath = srcPath;
    }

    /** @return the path to the folder that contains the source code of the project */
    public String getJarPath() {
        return jarPath;
    }
    /** @return the path to the folder that contains the JDoctor conditions */
    public String getjDoctorConditionsPath() {
        return jDoctorConditionsPath;
    }
    /** @return the name of the Java project */
    public String getProjectName() {
        return projectName;
    }
    /** @return the path to the Java project root folder */
    public String getProjectPath() {
        return projectPath;
    }
    /** @return the path to the folder that contains the jar file of the Java project */
    public String getSrcPath() {
        return srcPath;
    }
}

