package star.tratto.dataset.oracles;

/**
 * A record that contains all the relevant information related to a Java project.
 * The attributes of the class are:
 * <ol>
 *     <li><span>projectName</span> The name of the Java project.</li>
 *     <li><span>projectPath</span> The path to the Java project root folder.</li>
 *     <li><span>jarPath</span> The path to the folder that contains the jar file of the Java project.</li>
 *     <li><span>jDocConditionsPath</span> The path to the folder that contains the JDoctor conditions produced for the
 *     Java project.</li>
 *     <li><span>srcPath</span> The path to the folder that contains the source code of the project.</li>
 * </ol>
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

    public String getJarPath() {
        return jarPath;
    }
    public String getjDoctorConditionsPath() {
        return jDoctorConditionsPath;
    }
    public String getProjectName() {
        return projectName;
    }
    public String getProjectPath() {
        return projectPath;
    }
    public String getSrcPath() {
        return srcPath;
    }
}

