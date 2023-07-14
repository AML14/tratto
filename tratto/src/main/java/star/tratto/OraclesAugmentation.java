package star.tratto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import star.tratto.data.OracleDatapoint;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static star.tratto.identifiers.IOPath.ORACLES_DATASET;
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
    private static String ALTERNATE_ORACLES_PATH = "src/main/resources/data-augmentation/oracles.json";

    public static void main(String[] args) throws IOException {
        logger.info("Generating alternate versions of existing oracles...");
        logger.info("Reading oracles from: {}", ORACLES_DATASET.getValue());
        logger.info("Writing alternate oracles to: {}", ALTERNATE_ORACLES_PATH);
        Map<String, List<String>> alternateOracles = new HashMap<>();

        File[] oraclesDatasetFiles = new File(ORACLES_DATASET.getValue()).listFiles();
        for (File oraclesDatasetFile : oraclesDatasetFiles) { // Assume that only dataset files are in the folder
            List<Map> rawOracleDatapoints = objectMapper.readValue(oraclesDatasetFile, List.class);
            for (Map rawOracleDatapoint : rawOracleDatapoints) {
                OracleDatapoint oracleDatapoint = new OracleDatapoint(rawOracleDatapoint);
                if (!alternateOracles.containsKey(oracleDatapoint.getOracle())) {
                    alternateOracles.put(oracleDatapoint.getOracle(), getAlternateOracles(oracleDatapoint));
                }
            }
        }

        objectMapper.writeValue(new File(ALTERNATE_ORACLES_PATH), alternateOracles);

        logger.info("Finished generating alternate versions of existing oracles.");
    }

    private static List<String> getAlternateOracles(OracleDatapoint oracle) {
        List<String> alternateOracles = new ArrayList<>();
        String oracleString = compactExpression(oracle.getOracle());

        alternateOracles.addAll(getAlternateOraclePostcondition(oracleString));
        alternateOracles.addAll(getAlternateOraclesClausesWithParentheses(oracleString));
        alternateOracles.addAll(getAlternateOraclesRemoveEqualsTrue(oracleString));
        alternateOracles.addAll(getAlternateOraclesSwitchIneqOp(oracleString));
        alternateOracles.addAll(getAlternateOraclesSwitchEqOp(oracleString));
        alternateOracles.addAll(getAlternateOraclesSwitchNonEqIneqOp(oracleString));

        return alternateOracles;
    }
}
