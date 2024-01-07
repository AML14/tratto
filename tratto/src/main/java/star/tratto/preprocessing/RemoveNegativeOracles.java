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
 * This class removes all negative oracles, keeping only positive oracles.
 */
public class RemoveNegativeOracles {

    private static final Logger logger = LoggerFactory.getLogger(NegativeOraclesDataset.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static String ORACLES_DATASET_FOLDER = "src/main/resources/oracles-dataset/";

    public static void main(String[] args) throws IOException {
        Path oraclesDatasetFolder = Paths.get(ORACLES_DATASET_FOLDER);
        DirectoryStream<Path> oraclesDatasetStream = Files.newDirectoryStream(oraclesDatasetFolder);

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

                if (!oracleDatapoint.getOracle().equals(";")) {
                    keptOracleDatapoints.add(oracleDatapoint);
                }
            }

            objectMapper.writerWithDefaultPrettyPrinter().writeValue(oraclesDatasetFile.toFile(), keptOracleDatapoints.stream().map(OracleDatapoint::toMapAndLists).toList());
        }
    }
}
