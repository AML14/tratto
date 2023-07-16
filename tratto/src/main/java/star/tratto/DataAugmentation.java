package star.tratto;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.javatuples.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import star.tratto.data.OracleDatapoint;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static star.tratto.identifiers.IOPath.ORACLES_DATASET;
import static star.tratto.util.StringUtils.compactExpression;

/**
 * This class augments the oracles dataset as follows: 1) it reads the existing oracles
 * dataset; 2) for each oracle datapoint, it retrieves all alternate versions of
 * the oracles and the Javadoc tags; 3) it combines both up to the maximum number
 * of either oracles or Javadoc tags, whichever is higher (if different numbers,
 * the default oracle or Javadoc tag will be combined with remaining ones); 4) it
 * writes new oracle datapoints for each combination of oracle - Javadoc tag.
 */
public class DataAugmentation {

    private static final Logger logger = LoggerFactory.getLogger(DataAugmentation.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String ALTERNATE_ORACLES_PATH = "src/main/resources/data-augmentation/oracles.json";
    private static final String ALTERNATE_TAGS_PATH = "src/main/resources/data-augmentation/javadoc-tags.json";
    private static final String AUGMENTED_SUFFIX = "-augmented.json";
    private static Map<String, List<String>> alternateOracles;
    private static Map<String, List<String>> alternateTags;
    private static int oracleDPsAllowingAugmentation = 0;
    private static int oracleDPsOriginal = 0;
    private static int oracleDPsAugmented = 0;

    public static void main(String[] args) throws IOException {
        logger.info("Augmenting oracles dataset...");
        logger.info("Reading oracle data points from: {}", ORACLES_DATASET.getValue());
        logger.info("Reading alternate oracles from: {}", ALTERNATE_ORACLES_PATH);
        logger.info("Reading alternate Javadoc tags from: {}", ALTERNATE_TAGS_PATH);

        List<OracleDatapoint> newOracleDatapoints = new ArrayList<>();
        alternateOracles = objectMapper.readValue(new File(ALTERNATE_ORACLES_PATH), new TypeReference<>(){});
        alternateTags = objectMapper.readValue(new File(ALTERNATE_TAGS_PATH), new TypeReference<>(){});

        Path oraclesDatasetPath = Path.of(ORACLES_DATASET.getValue());
        DirectoryStream<Path> oraclesDatasetStream = Files.newDirectoryStream(oraclesDatasetPath);
        for (Path oraclesDatasetFile : oraclesDatasetStream) {
            if (oraclesDatasetFile.toString().endsWith(AUGMENTED_SUFFIX)) {
                continue; // Skip already augmented files
            }
            logger.info("Augmenting file: {}", oraclesDatasetFile.getFileName());
            List<Map> rawOracleDatapoints = objectMapper.readValue(oraclesDatasetFile.toFile(), List.class);
            for (Map rawOracleDatapoint : rawOracleDatapoints) {
                OracleDatapoint oracleDatapoint = new OracleDatapoint(rawOracleDatapoint);
                List<Pair<String, String>> oracleTagCombos = getOracleTagCombos(oracleDatapoint);
                for (Pair<String, String> oracleTagCombo : oracleTagCombos) {
                    OracleDatapoint newOracleDatapoint = new OracleDatapoint(oracleDatapoint);
                    newOracleDatapoint.setOracle(oracleTagCombo.getValue0());
                    newOracleDatapoint.setJavadocTag(oracleTagCombo.getValue1());
                    newOracleDatapoints.add(newOracleDatapoint);
                    oracleDPsAugmented++;
                }
                oracleDPsOriginal++;
            }

            String newOracleDatapointsFile = oraclesDatasetFile.toString().replace(".json", AUGMENTED_SUFFIX);
            objectMapper.writeValue(new File(newOracleDatapointsFile), newOracleDatapoints.stream().map(OracleDatapoint::toMapAndLists).toList());
            newOracleDatapoints.clear();
        }

        logger.info("Finished augmenting oracles dataset.");
        logger.info("Oracle data points original: {}", oracleDPsOriginal);
        logger.info("Oracle data points allowing augmentation: {}", oracleDPsAllowingAugmentation);
        logger.info("Oracle data points augmented: {}", oracleDPsAugmented);
        logger.info("Oracle data points total: {}", oracleDPsOriginal + oracleDPsAugmented);
    }

    /**
     * @return List of pairs oracle-tag
     */
    private static List<Pair<String, String>> getOracleTagCombos(OracleDatapoint oracleDatapoint) {
        List<Pair<String, String>> oracleTagCombos = new ArrayList<>();
        String compactOracle = compactExpression(oracleDatapoint.getOracle());
        String javaDocTag = oracleDatapoint.getJavadocTag();
        List<String> currentAlternateOracles = new ArrayList<>(alternateOracles.getOrDefault(compactOracle, List.of()));
        currentAlternateOracles.add(compactOracle); // Add default oracle
        List<String> currentAlternateTags = new ArrayList<>(alternateTags.getOrDefault(javaDocTag, List.of()));
        currentAlternateTags.add(javaDocTag); // Add default Javadoc tag
        // Randomize lists so that combination patterns are varied:
        Collections.shuffle(currentAlternateOracles);
        Collections.shuffle(currentAlternateTags);

        int max = Math.max(currentAlternateOracles.size(), currentAlternateTags.size());
        for (int i = 0; i < max; i++) {
            String oracle = currentAlternateOracles.get(i % currentAlternateOracles.size());
            String tag = currentAlternateTags.get(i % currentAlternateTags.size());
            oracleTagCombos.add(Pair.with(oracle, tag));
        }

        // Original oracle and Javadoc tag may be combined. This is already in the dataset. Remove it.
        oracleTagCombos.remove(Pair.with(compactOracle, javaDocTag));
        if (oracleTagCombos.size() > 0) {
            oracleDPsAllowingAugmentation++;
        }

        return oracleTagCombos;
    }
}
