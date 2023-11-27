package star.tratto;

import star.tratto.data.OracleDatapoint;
import star.tratto.data.ProjectInitializer;
import star.tratto.data.TrattoPath;
import star.tratto.data.records.JDoctorCondition;
import star.tratto.data.records.Project;
import star.tratto.data.oracles.ProjectOracleGenerator;
import star.tratto.util.FileUtils;
import star.tratto.util.javaparser.DatasetUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OraclesDataset {
    private static final ProjectOracleGenerator oracleDPGenerator = new ProjectOracleGenerator();
    /**
     * The number of OracleDatapoints per JSON file generated by the main
     * method (avoids generating one absurdly large JSON file as output). If
     * -1, then all OracleDatapoints are aggregated into one file.
     */
    private static final int chunkSize = 100;

    /**
     * Reads all JSON files containing JDoctor conditions of a given project.
     *
     * @param project a project with JDoctor conditions to parse
     * @return a list of conditions, representing the original JSON conditions
     */
    private static List<JDoctorCondition> getConditions(Project project) {
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
     * Returns the oracle datapoints of the project.
     * @param project a project
     * @return the oracle datapoints of the project 
     */
    private static List<OracleDatapoint> getProjectOracleDatapoints(Project project) {
        System.out.printf("%nCollecting data from: " + project.projectName() + "%n");
        List<JDoctorCondition> jDoctorConditions = getConditions(project);
        return oracleDPGenerator.generate(project, jDoctorConditions);
    }

    /**
     * Writes {@code oracleDPChunks} to individual JSON files. Creates new
     * files and parent directories if necessary. If files already exist,
     * overrides any previous content.
     *
     * @param path base path for all chunks. The i-th chunk will be written to
     *             the path "[path]_i.json" (0-indexed).
     * @param oracleDPChunks oracle datapoints to write
     * @throws Error if unable to create files/directories or unable to write
     * content to file
     * @see FileUtils#write
     */
    private static void writeChunks(Path path, List<List<OracleDatapoint>> oracleDPChunks) {
        for (int i = 0; i < oracleDPChunks.size(); i++) {
            List<OracleDatapoint> chunk = oracleDPChunks.get(i);
            // removes ".json" from original path to avoid adding ".json" into the middle of the file name.
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
        List<List<OracleDatapoint>> oracleDPSubLists = DatasetUtils.splitListIntoSubLists(oracleDPs, chunkSize);
        String oracleDPFileName = String.format("oracle_datapoints_%s.json", project.projectName());
        Path oracleDPPath = TrattoPath.OUTPUT_DATASET.getPath().resolve(oracleDPFileName);
        writeChunks(oracleDPPath, oracleDPSubLists);
    }

    /**
     * Writes the generated OracleDatapoints objects to the target output
     * directory as one contiguous file. Writes a separate file for all
     * oracles.
     *
     * @param oracleDPs oracle datapoints
     */
    private static void writeOracleDatapoints(List<OracleDatapoint> oracleDPs) {
        // write raw oracles as separate file
        List<String> oracles = oracleDPs.stream().map(OracleDatapoint::getOracle).toList();
        String oraclesFileName = "oracles_list.json";
        Path oraclesPath = TrattoPath.OUTPUT.getPath().resolve(oraclesFileName);
        FileUtils.write(oraclesPath, oracles);
        // write oracle datapoints
        String oracleDPFileName = "oracle_datapoints.json";
        Path oracleDPPath = TrattoPath.OUTPUT_DATASET.getPath().resolve(oracleDPFileName);
        FileUtils.write(oracleDPPath, oracleDPs);
    }

    public static void main(String[] args) throws IOException {
        // clean output directories
        FileUtils.deleteDirectory(TrattoPath.OUTPUT.getPath());
        FileUtils.deleteDirectory(TrattoPath.ORACLES_DATASET.getPath());
        // get oracle datapoints
        List<Project> projects = ProjectInitializer.getProjects(TrattoPath.INPUT_PROJECTS.getPath());
        List<OracleDatapoint> allOracleDPs = new ArrayList<>();
        for (Project project : projects) {
            List<OracleDatapoint> projectOracleDPs = getProjectOracleDatapoints(project);
            // aggregate Oracle DPs if using one output file. Otherwise, split into multiple files.
            if (chunkSize != -1) {
                writeProjectOracleDatapoints(projectOracleDPs, project);
            } else {
                allOracleDPs.addAll(projectOracleDPs);
            }
        }
        // write all OracleDatapoint to one file.
        if (chunkSize == -1) {
            writeOracleDatapoints(allOracleDPs);
        }
        // move oracles dataset from target to resources folder for TokensDataset
        FileUtils.move(TrattoPath.OUTPUT_DATASET.getPath(), TrattoPath.ORACLES_DATASET.getPath());
    }
}
