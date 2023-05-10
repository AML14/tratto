package star.tratto.dataset.oracles;

import star.tratto.dataset.oracles.json.parsers.JDoctorConditionParser;
import star.tratto.dataset.oracles.json.parsers.ProjectParser;
import star.tratto.identifiers.file.FileFormat;
import star.tratto.identifiers.file.FileName;
import star.tratto.identifiers.path.Path;
import star.tratto.util.FileUtils;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class OraclesDataset {

    public static void main(String[] args) {
        // Specify the path to JSON file with the list of the input projects and
        // the information, to initialize each of them
        String projectsPath = FileUtils.getAbsolutePathToFile(Path.REPOS.getValue(), FileName.INPUT_PROJECTS, FileFormat.JSON);
        List<Project> projects = ProjectParser.initialize(projectsPath);
        JDoctorConditionParser jDoctorConditionParser = new JDoctorConditionParser();
        ProjectOracleGenerator oracleDPGenerator = new ProjectOracleGenerator();

        for (Project project : projects) {
            System.out.println("Collecting data from: " + project.getProjectName());
            // get JDoctor conditions and load project to generator.
            List<JDoctorCondition> jDoctorConditions = jDoctorConditionParser.parseJDoctorConditions(project);
            oracleDPGenerator.loadProject(project, jDoctorConditions);
            List<OracleDatapoint> oracleDPList = oracleDPGenerator.generate();
            /* Additional code for writing data points to file.
            try {
                FileUtils.write(
                        Paths.get(Path.OUTPUT.getValue(), project.getProjectName()).toString(),
                        fileName,
                        FileFormat.JSON,
                        oracleDPList,
                        true
                );
            } catch (IOException e) {
                String errMsg = String.format(
                    "Error while saving the data-points of project %s in JSON format.",
                    project.getProjectName()
                );
                System.err.println(errMsg);
            }
             */
        }
    }
}
