package data.collection;

import data.collection.enums.FileFormat;
import data.collection.enums.FileName;
import data.collection.enums.Path;
import data.collection.generators.OracleDPGenerator;
import data.collection.models.JDocCondition;
import data.collection.models.OracleDP;
import data.collection.parsers.JDocConditionParser;
import data.collection.parsers.ProjectParser;
import data.collection.models.Project;
import data.collection.utils.FileUtils;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

/**
 * Main class that starts the program to generate the first dataset.
 */
public class Main {
    /**
     * The main method starts the program that parse the input projects and generate
     * the dataset of oracles.
     * @param args optional arguments passed to the function
     */
    public static void main(String[] args) {
        // Specify the path to JSON file with the list of the input projects and
        // the information, to initialize each of them
        String projectsPath = FileUtils.getAbsolutePathToFile(Path.REPOS.getValue(), FileName.INPUT_PROJECTS, FileFormat.JSON);
        List<Project> projects = ProjectParser.initialize(projectsPath);
        JDocConditionParser jDocConditionParser = new JDocConditionParser();
        OracleDPGenerator oracleDPGenerator = new OracleDPGenerator();

        for (Project project : projects) {
            System.out.println("Collecting data from: " + project.projectName());
            JDocCondition[] jDocConditions = jDocConditionParser.parseJDocConditions(project);
            oracleDPGenerator.loadProject(
                    project,
                    jDocConditions
            );
            List<OracleDP> oracleDPList = oracleDPGenerator.generate();
            String fileName = String.format(
                    "%s_%s",
                    FileName.ORACLE_DATAPOINTS.getValue(),
                    project.projectName()
            );
            FileUtils.appendToFile(
                Path.OUTPUT.getValue(),
                fileName, FileFormat.JSON,
                project.projectName(),
                oracleDPList
            );
        }

    }

}
