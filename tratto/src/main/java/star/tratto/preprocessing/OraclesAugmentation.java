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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static star.tratto.data.TrattoPath.ORACLES_DATASET;
import static star.tratto.oraclegrammar.custom.OracleAlternatesGenerator.*;
import static star.tratto.util.StringUtils.compactExpression;

/**
 * This class handles the conversion of oracles into different but semantically-equivalent
 * formats. This is useful to augment the oracles dataset and, in turn, the tokens datasets
 * as well.
 */
public class OraclesAugmentation {

    private static final Logger logger = LoggerFactory.getLogger(OraclesAugmentation.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String ALTERNATE_ORACLES_PATH = "src/main/resources/data-augmentation/oracles.json";
    private static final String AUGMENTED_SUFFIX = "-augmented.json";

    public static void main(String[] args) throws IOException {
        logger.info("Generating alternate versions of existing oracles...");
        logger.info("Reading oracles from: {}", ORACLES_DATASET.getPath());
        logger.info("Writing alternate oracles to: {}", ALTERNATE_ORACLES_PATH);
        Map<String, List<String>> alternateOracles = new HashMap<>();

        DirectoryStream<Path> oraclesDatasetStream = Files.newDirectoryStream(ORACLES_DATASET.getPath());
        for (Path oraclesDatasetFile : oraclesDatasetStream) { // Assume that only dataset files are in the folder
            if (oraclesDatasetFile.toString().endsWith(AUGMENTED_SUFFIX)) {
                continue; // Skip already augmented files
            }
            logger.info("Generating alternate versions of oracles in file: {}", oraclesDatasetFile.getFileName());
            List<Map> rawOracleDatapoints = objectMapper.readValue(oraclesDatasetFile.toFile(), List.class);
            for (Map rawOracleDatapoint : rawOracleDatapoints) {
                OracleDatapoint oracleDatapoint = new OracleDatapoint(rawOracleDatapoint);
                String compactOracle = compactExpression(oracleDatapoint.getOracle());
                if (!alternateOracles.containsKey(compactOracle)) {
                    alternateOracles.put(compactOracle, getAlternateOracles(compactOracle));
                }
            }
        }

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(ALTERNATE_ORACLES_PATH), alternateOracles);
        logger.info("Finished generating alternate versions of existing oracles.");
    }

    private static List<String> getAlternateOracles(String oracle) {
        List<String> alternateOracles = new ArrayList<>();
        String oracleString = compactExpression(oracle);

        alternateOracles.addAll(getAlternateOraclePostcondition(oracleString));
        alternateOracles.addAll(getAlternateOraclesClausesWithParentheses(oracleString));
        alternateOracles.addAll(getAlternateOraclesRemoveEqualsTrue(oracleString));
        alternateOracles.addAll(getAlternateOraclesSwitchIneqOp(oracleString));
        alternateOracles.addAll(getAlternateOraclesSwitchEqOp(oracleString));
        alternateOracles.addAll(getAlternateOraclesSwitchNonEqIneqOp(oracleString));

        return alternateOracles;
    }
}
