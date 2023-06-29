package star.tratto;

import star.tratto.data.OracleDatapoint;
import star.tratto.data.oracles.JDoctorCondition;
import star.tratto.data.oracles.Project;
import star.tratto.data.oracles.ProjectOracleGenerator;
import star.tratto.data.oracles.json.parsers.JDoctorConditionParser;
import star.tratto.data.oracles.json.parsers.ProjectParser;
import star.tratto.identifiers.file.FileFormat;
import star.tratto.identifiers.file.FileName;
import star.tratto.identifiers.path.Path;
import star.tratto.util.FileUtils;
import star.tratto.util.javaparser.DatasetUtils;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class OraclesDataset {
    public static final int chunkSize = 100;
    public static final boolean sampleEmptyOracles = true;

    public static void main(String[] args) {
        // Specify the path to JSON file with the list of the input projects and
        // the information, to initialize each of them.
        String projectsPath = FileUtils.getAbsolutePathToFile(Path.REPOS.getValue(), FileName.INPUT_PROJECTS, FileFormat.JSON);
        List<Project> projects = ProjectParser.initialize(projectsPath);
        JDoctorConditionParser jDoctorConditionParser = new JDoctorConditionParser();
        ProjectOracleGenerator oracleDPGenerator = new ProjectOracleGenerator();

        for (Project project : projects) {
            System.out.println("\nCollecting data from: " + project.getProjectName());
            // get JDoctor conditions and load project to generator.
            List<JDoctorCondition> jDoctorConditions = jDoctorConditionParser.parseJDoctorConditions(project);
            oracleDPGenerator.loadProject(project, jDoctorConditions);
            // get oracle data points.
            List<OracleDatapoint> oracleDPList = oracleDPGenerator.generate();
            List<String> oraclesString = oracleDPList.stream().map(OracleDatapoint::getOracle).toList();
            // randomly sample empty oracles.
            if (sampleEmptyOracles) {
                int numSamples = 10;
                List<OracleDatapoint> emptySample = DatasetUtils.randomSample(oracleDPList, true, numSamples);
                System.out.printf("Randomly sampling %d empty oracles:%n", numSamples);
                for (OracleDatapoint sample : emptySample) {
                    System.out.println(sample.getJavadocTag());
                }
            }
            // save oracles.
            String oracleStringFileName = String.format(
                    "oracle_list_%s", project.getProjectName()
            );
            FileUtils.appendToFile(
                    Path.OUTPUT.getValue(),
                    oracleStringFileName,
                    FileFormat.JSON,
                    project.getProjectName(),
                    oraclesString
            );
            // save OracleDatapoint chunks.
            List<List<OracleDatapoint>> oracleDPChunks = splitListIntoChunks(oracleDPList);
            for (int i = 0; i < oracleDPChunks.size(); i++) {
                List<OracleDatapoint> chunk = oracleDPChunks.get(i);
                String fileName = String.format(
                        "%s_%s_%d",
                        FileName.ORACLE_DATAPOINTS.getValue(),
                        project.getProjectName(),
                        i
                );
                FileUtils.appendToFile(
                        Paths.get(Path.OUTPUT.getValue(),"dataset").toString(),
                        fileName,
                        FileFormat.JSON,
                        project.getProjectName(),
                        chunk
                );
            }
        }
    }

    private static <T> List<List<T>> splitListIntoChunks(List<T> list) {
        List<List<T>> chunks = new ArrayList<>();
        for (int i = 0; i < list.size(); i += chunkSize) {
            int endIndex = Math.min(i + chunkSize, list.size());
            chunks.add(list.subList(i, endIndex));
        }
        return chunks;
    }
}
