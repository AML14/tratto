package star.tratto;

import star.tratto.data.OracleDatapoint;
import star.tratto.data.TrattoPath;
import star.tratto.data.ProjectInitializer;
import star.tratto.data.records.JDoctorCondition;
import star.tratto.data.records.Project;
import star.tratto.data.oracles.ProjectOracleGenerator;
import star.tratto.util.FileUtils;
import star.tratto.util.javaparser.DatasetUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OraclesDataset {
    private static final ProjectOracleGenerator oracleDPGenerator = new ProjectOracleGenerator();
    // number of oracle datapoints per file (avoids generating absurdly large files).
    private static final int chunkSize = 100;

    /**
     * Reads all JSON files containing JDoctor conditions of a given project.
     *
     * @param project a project with JDoctor conditions to parse
     * @return a list of conditions, representing the original JSON conditions
     */
    private static List<JDoctorCondition> getProjectConditions(Project project) {
        Path conditionsDir = project.conditionsPath();
        try (Stream<Path> walk = Files.walk(conditionsDir)) {
            return walk
                    .filter(path -> path.toString().endsWith(".json"))
                    .map(path -> FileUtils.readJSONList(path, JDoctorCondition.class))
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new Error("Error in collecting all files from " + conditionsDir, e);
        }
    }

    /**
     * @param project a project under analysis
     * @return the corresponding oracle datapoints of the project
     */
    private static List<OracleDatapoint> getProjectOracleDatapoints(Project project) {
        System.out.println("\nCollecting data from: " + project.projectName());
        List<JDoctorCondition> jDoctorConditions = getProjectConditions(project);
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

    /**
     * Writes the generated OracleDatapoint objects to the target output
     * directory. Writes a separate file for all oracles.
     *
     * @param oracleDPs the oracle datapoints of a project
     * @param project the corresponding project
     */
    private static void writeProjectOracleDatapoints(
            List<OracleDatapoint> oracleDPs,
            Project project
    ) {
        // write raw oracles as separate file
        List<String> oracles = oracleDPs.stream().map(OracleDatapoint::getOracle).toList();
        String oraclesFileName = String.format("oracles_list_%s.json", project.projectName());
        Path oraclesPath = TrattoPath.OUTPUT.getPath().resolve(project.projectName()).resolve(oraclesFileName);
        FileUtils.write(oraclesPath, oracles);
        // write oracle datapoints as chunks
        List<List<OracleDatapoint>> oracleDPChunks = DatasetUtils.splitListIntoChunks(oracleDPs, chunkSize);
        String oracleDPFileName = String.format("oracle_datapoints_%s.json", project.projectName());
        Path oracleDPPath = TrattoPath.OUTPUT_DATASET.getPath().resolve(oracleDPFileName);
        writeChunks(oracleDPPath, oracleDPChunks);
    }

    public static void main(String[] args) throws IOException {
        // clean output directories.
        FileUtils.deleteDirectory(TrattoPath.OUTPUT.getPath());
        FileUtils.deleteDirectory(TrattoPath.ORACLES_DATASET.getPath());
        // load projects.
        List<Project> projects = ProjectInitializer.initialize(TrattoPath.INPUT_PROJECTS.getPath());
        for (Project project : projects) {
            // get oracle data points.
            List<OracleDatapoint> oracleDPs = getProjectOracleDatapoints(project);
            writeProjectOracleDatapoints(oracleDPs, project);
        }
        // move oracles dataset from target to resources folder for TokensDataset.
        FileUtils.move(TrattoPath.OUTPUT_DATASET.getPath(), TrattoPath.ORACLES_DATASET.getPath());
    }
}
