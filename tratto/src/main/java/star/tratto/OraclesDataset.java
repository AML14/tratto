package star.tratto;

import star.tratto.data.OracleDatapoint;
import star.tratto.data.oracles.JDoctorCondition;
import star.tratto.data.oracles.Project;
import star.tratto.data.oracles.ProjectOracleGenerator;
import star.tratto.data.oracles.json.JDoctorConditionParser;
import star.tratto.data.oracles.json.ProjectParser;
import star.tratto.data.IOPath;
import star.tratto.util.FileUtils;
import star.tratto.util.javaparser.DatasetUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class OraclesDataset {
    // parser objects used to generate oracles dataset.
    private static final JDoctorConditionParser jDoctorConditionParser = new JDoctorConditionParser();
    private static final ProjectOracleGenerator oracleDPGenerator = new ProjectOracleGenerator();
    // number of oracle datapoints per file (avoids generating absurdly large files).
    private static final int chunkSize = 100;

    /**
     * @param project a project under analysis
     * @return the corresponding oracle datapoints of the project
     */
    private static List<OracleDatapoint> getProjectOracleDatapoints(Project project) {
        System.out.println("\nCollecting data from: " + project.getProjectName());
        List<JDoctorCondition> jDoctorConditions = jDoctorConditionParser.parseJDoctorConditions(project);
        oracleDPGenerator.loadProject(project, jDoctorConditions);
        return oracleDPGenerator.generate();
    }

    public static void main(String[] args) throws IOException {
        // clean output directories.
        FileUtils.deleteDirectory(IOPath.OUTPUT.getPath());
        FileUtils.deleteDirectory(IOPath.ORACLES_DATASET.getPath());
        // load projects.
        List<Project> projects = ProjectParser.initialize(IOPath.INPUT_PROJECTS.getPath());
        for (Project project : projects) {
            // get oracle data points.
            List<OracleDatapoint> oracleDPs = getProjectOracleDatapoints(project);
            List<String> oracles = oracleDPs.stream().map(OracleDatapoint::getOracle).toList();
            // save all oracles in target output.
            String oraclesFileName = String.format("oracles_list_%s.json", project.getProjectName());
            Path oraclesPath = IOPath.OUTPUT.getPath().resolve(project.getProjectName()).resolve(oraclesFileName);
            FileUtils.write(oraclesPath, oracles);
            // save oracle datapoint information in chunks.
            List<List<OracleDatapoint>> oracleDPChunks = DatasetUtils.splitListIntoChunks(oracleDPs, chunkSize);
            String oracleDPFileName = String.format("oracle_datapoints_%s.json", project.getProjectName());
            Path oracleDPPath = IOPath.OUTPUT_DATASET.getPath().resolve(project.getProjectName()).resolve(oracleDPFileName);
            FileUtils.writeChunks(oracleDPPath, oracleDPChunks);
        }
        // move oracles dataset from target to resources folder for TokensDataset.
        FileUtils.move(IOPath.OUTPUT_DATASET.getPath(), IOPath.ORACLES_DATASET.getPath());
    }
}
