package star.tratto.preprocessing;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import star.tratto.data.OracleDatapoint;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * After running {@link NegativeOraclesDataset}, this class removes all negative oracles
 * that were not selected and stored in the JSON file located at src/main/resources/negative_oracles.json.
 * It also keeps positive oracles, so that the resulting dataset at src/main/resources/oracles-dataset
 * is comprehensive.
 */
public class RemoveUnusedNegativeOracles {

    private static final Logger logger = LoggerFactory.getLogger(NegativeOraclesDataset.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static String ORACLES_DATASET_FOLDER = "src/main/resources/oracles-dataset/";
    public static String NEGATIVE_ORACLES_FILE = "src/main/resources/negative_oracles.json";

    public static void main(String[] args) throws IOException {
        Path oraclesDatasetFolder = Paths.get(ORACLES_DATASET_FOLDER);
        DirectoryStream<Path> oraclesDatasetStream = Files.newDirectoryStream(oraclesDatasetFolder);

        // Load negative oracle IDs from file
        List<Long> negativeOracleIDs = objectMapper.readValue(new File(NEGATIVE_ORACLES_FILE), List.class).stream().map(o -> ((Map)o).get("id")).toList();

        // Assume that only dataset files are in the folder
        for (Path oraclesDatasetFile : oraclesDatasetStream) {
            logger.info("------------------------------------------------------------");
            logger.info("Processing file: {}", oraclesDatasetFile.getFileName());
            logger.info("------------------------------------------------------------");

            List<Map> rawOracleDatapoints = objectMapper.readValue(oraclesDatasetFile.toFile(), List.class);
            List<OracleDatapoint> keptOracleDatapoints = new ArrayList<>();
            for (Map rawOracleDatapoint : rawOracleDatapoints) {
                OracleDatapoint oracleDatapoint = new OracleDatapoint(rawOracleDatapoint);
                logger.info("Processing oracle: {}", oracleDatapoint.getOracle());

                if (!oracleDatapoint.getOracle().equals(";") || negativeOracleIDs.contains(oracleDatapoint.getId())) {
                    keptOracleDatapoints.add(oracleDatapoint); // Keep both positive oracles and used negative oracles
                }
            }

            objectMapper.writeValue(oraclesDatasetFile.toFile(), keptOracleDatapoints.stream().map(OracleDatapoint::toMapAndLists).toList());
        }
    }
}
