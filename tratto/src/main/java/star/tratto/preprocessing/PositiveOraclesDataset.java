package star.tratto.preprocessing;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import star.tratto.data.OracleDatapoint;
import static star.tratto.preprocessing.NegativeOraclesDataset.getOracleDatapointMap;

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
 * Given an oracles-dataset, this class selects all positive oracles (i.e., those
 * with an associated oracle attribute).
 * <br>
 * The output JSON file is a list of objects with the same properties as the oracle
 * data points, but without those related to the tokens. As such, this file is only
 *  useful for training the binary classifier of oracles.
 */
public class PositiveOraclesDataset {
    private static final Logger logger = LoggerFactory.getLogger(PositiveOraclesDataset.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static String ORACLES_DATASET_FOLDER = "src/main/resources/oracles-dataset/";
    public static String POSITIVE_ORACLES_FILE = "src/main/resources/positive_oracles.json";

    public static void main(String[] args) throws IOException {
        logger.info("Selecting positive oracles...");
        logger.info("Reading oracles from: {}", ORACLES_DATASET_FOLDER);
        logger.info("Writing positive oracles to: {}", POSITIVE_ORACLES_FILE);
        List<Map> positiveOracles = new ArrayList<>();

        Path oraclesDatasetFolder = Paths.get(ORACLES_DATASET_FOLDER);
        DirectoryStream<Path> oraclesDatasetStream = Files.newDirectoryStream(oraclesDatasetFolder);
        for (Path oraclesDatasetFile : oraclesDatasetStream) { // Assume that only dataset files are in the folder
            logger.info("------------------------------------------------------------");
            logger.info("Processing file: {}", oraclesDatasetFile.getFileName());
            logger.info("------------------------------------------------------------");

            List<Map> rawOracleDatapoints = objectMapper.readValue(oraclesDatasetFile.toFile(), List.class);
            for (Map rawOracleDatapoint : rawOracleDatapoints) {
                OracleDatapoint oracleDatapoint = new OracleDatapoint(rawOracleDatapoint);
                logger.info("Processing oracle: {}", oracleDatapoint.getOracle());
                if (!oracleDatapoint.getOracle().equals(";")) {
                    positiveOracles.add(getOracleDatapointMap(oracleDatapoint));
                }
            }
        }

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(POSITIVE_ORACLES_FILE), positiveOracles);
        logger.info("Finished selecting positive oracles.");
    }
}
