package star.tratto;

import star.tratto.data.OracleDatapoint;
import star.tratto.data.TrattoPath;
import star.tratto.data.oracles.Project;
import star.tratto.data.oracles.ProjectOracleGenerator;
import star.tratto.data.oracles.JDoctorConditionParser;
import star.tratto.data.oracles.ProjectParser;
import star.tratto.data.records.JDoctorCondition;
import star.tratto.util.FileUtils;
import star.tratto.util.javaparser.DatasetUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * Writes {@code oracleDPChunks} to individual JSON files. Creates new
     * files and parent directories if necessary. If files already exist,
     * overrides any previous content.
     *
     * @param path base path for all chunks. The i-th chunk will be written to
     *             the path "[path]_i.json".
     * @param oracleDPChunks oracle datapoints to write
     * @throws Error if unable to create files/directories or unable to write
     * content to file
     * @see FileUtils#write
     */
    private static void writeChunks(Path path, List<List<OracleDatapoint>> oracleDPChunks) {
        for (int i = 0; i < oracleDPChunks.size(); i++) {
            List<OracleDatapoint> chunk = oracleDPChunks.get(i);
            String chunkFileName = String.format("%s_%d.json", path.getFileName().toString().replace(".json", ""), i);
            Path chunkPath = path.getParent().resolve(chunkFileName);
            FileUtils.write(chunkPath, chunk.stream().map(OracleDatapoint::toMapAndLists).collect(Collectors.toList()));
        }
    }

    public static void main(String[] args) throws IOException {
        // clean output directories.
        FileUtils.deleteDirectory(TrattoPath.OUTPUT.getPath());
        FileUtils.deleteDirectory(TrattoPath.ORACLES_DATASET.getPath());
        // load projects.
        List<Project> projects = ProjectParser.initialize(TrattoPath.INPUT_PROJECTS.getPath());
        for (Project project : projects) {
            // get oracle data points.
            List<OracleDatapoint> oracleDPs = getProjectOracleDatapoints(project);
            List<String> oracles = oracleDPs.stream().map(OracleDatapoint::getOracle).toList();
            // save all oracles in target output.
            String oraclesFileName = String.format("oracles_list_%s.json", project.getProjectName());
            Path oraclesPath = TrattoPath.OUTPUT.getPath().resolve(project.getProjectName()).resolve(oraclesFileName);
            FileUtils.write(oraclesPath, oracles);
            // save oracle datapoint information in chunks.
            List<List<OracleDatapoint>> oracleDPChunks = DatasetUtils.splitListIntoChunks(oracleDPs, chunkSize);
            String oracleDPFileName = String.format("oracle_datapoints_%s.json", project.getProjectName());
            Path oracleDPPath = TrattoPath.OUTPUT_DATASET.getPath().resolve(oracleDPFileName);
            writeChunks(oracleDPPath, oracleDPChunks);
        }
        // move oracles dataset from target to resources folder for TokensDataset.
        FileUtils.move(TrattoPath.OUTPUT_DATASET.getPath(), TrattoPath.ORACLES_DATASET.getPath());
    }
}
