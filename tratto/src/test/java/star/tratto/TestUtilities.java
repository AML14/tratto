package star.tratto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import star.tratto.data.OracleDatapoint;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestUtilities {

    private static final Logger logger = LoggerFactory.getLogger(TestUtilities.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static final String ORACLES_DATASET_PATH = "src/test/resources/oracles-dataset/test-dataset.json";

    public static List<String> readOraclesFromExternalFiles() {
        // Read oracles from external files
        List<String> stringOracles = new ArrayList<>();
        try {
            Path file1Path = Paths.get("src/test/resources/jdoctorOracles.tr");
            Path file2Path = Paths.get("src/test/resources/additionalSamples.tr");
            stringOracles.addAll(Files.readAllLines(file1Path));
            stringOracles.addAll(Files.readAllLines(file2Path));
        } catch (IOException e) {
            logger.error("Error reading the file: {}" + e.getMessage());
        }

        return stringOracles;
    }

    public static List<OracleDatapoint> readOracleDatapointsFromOraclesDataset() {
        List<OracleDatapoint> oracleDatapoints;
        try {
            oracleDatapoints = ((List<Map>)objectMapper.readValue(new File(ORACLES_DATASET_PATH), List.class))
                    .stream()
                    .map(OracleDatapoint::new)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            logger.error("Error reading the oracles dataset file");
            throw new RuntimeException(e);
        }
        return oracleDatapoints;
    }
}
