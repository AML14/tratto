package data.collection.models;

/**
 * A record that contains all the relevant information related to a Java project.
 * @param projectName The name of the Java project.
 * @param projectPath The path to the Java project root folder.
 * @param jarPath The path to the folder that contains the jar file of the Java project.
 * @param jDocConditionsPath The path to the folder that contains the JDoctor conditions produced for the Java project.
 * @param srcPath The path to the folder that contains the source code of the project.
 */
public record Project(
        String projectName,
        String projectPath,
        String jarPath,
        String jDocConditionsPath,
        String srcPath
){}
