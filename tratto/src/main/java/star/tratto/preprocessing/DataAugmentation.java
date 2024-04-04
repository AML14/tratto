package star.tratto.preprocessing;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.javatuples.Quartet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import star.tratto.data.OracleDatapoint;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static star.tratto.data.TrattoPath.ORACLES_DATASET;
import static star.tratto.util.StringUtils.compactExpression;
import static star.tratto.util.javaparser.JavaParserUtils.getMethodDeclaration;
import static star.tratto.util.javaparser.JavaParserUtils.updateMethodJavadoc;

/**
 * This class augments the oracles dataset by reading all existing oracles from
 * src/main/resources/oracles-dataset and, for each oracle, proceeding as detailed
 * in the {@link #getAlternateOracleDPs(OracleDatapoint)} method. Also, the oracles
 * contained in each file are randomly shuffled and split into two files. This is
 * useful for later splitting the dataset into training and test sets.
 */
public class DataAugmentation {

    private static final Logger logger = LoggerFactory.getLogger(DataAugmentation.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Random random = new Random(42); // To make augmented datasets deterministic
    private static final String ALTERNATE_ORACLES_PATH = "src/main/resources/data-augmentation/oracles.json";
    public static String ALTERNATE_TAGS_PATH = "src/main/resources/data-augmentation/javadoc-tags.json"; // public for testing purposes
    private static final String AUGMENTED_SUFFIX = "-augmented.json";
    private static Map<String, List<String>> alternateOracles;
    private static Map<String, List<String>> alternateTags;
    private static int oracleDPsAllowingAugmentation = 0;
    private static int oracleDPsOriginal = 0;
    private static int oracleDPsTotal = 0;

    /**
     * For testing purposes, this class accepts an optional argument that specifies
     * the probability of augmenting an oracle. This is needed because, in CI, the
     * server runs out of disk space if we augment all oracles.
     *
     * @param args Optional argument (float) that specifies the probability of augmenting
     *             an oracle. If not specified, it defaults to 1.0.
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Float augmentProb = null;
        try {
            augmentProb = Float.parseFloat(args[0]);
        } catch (Exception e) {
            logger.info("No augment probability specified. Defaulting to 1.0");
        }

        logger.info("Augmenting oracles dataset...");
        logger.info("Reading oracle data points from: {}", ORACLES_DATASET.getPath());
        logger.info("Reading alternate oracles from: {}", ALTERNATE_ORACLES_PATH);
        logger.info("Reading alternate Javadoc tags from: {}", ALTERNATE_TAGS_PATH);

        List<OracleDatapoint> newOracleDatapoints = new ArrayList<>();
        alternateOracles = objectMapper.readValue(new File(ALTERNATE_ORACLES_PATH), new TypeReference<>(){});
        alternateTags = objectMapper.readValue(new File(ALTERNATE_TAGS_PATH), new TypeReference<>(){});

        DirectoryStream<Path> oraclesDatasetStream = Files.newDirectoryStream(ORACLES_DATASET.getPath());
        for (Path oraclesDatasetFile : oraclesDatasetStream) {
            if (oraclesDatasetFile.toString().endsWith(AUGMENTED_SUFFIX)) {
                continue; // Skip already augmented files
            }
            if (augmentProb != null && new Random().nextFloat() > augmentProb) {
                logger.info("Skipping file: {}", oraclesDatasetFile.getFileName());
                continue;
            }
            logger.info("Augmenting file: {}", oraclesDatasetFile.getFileName());
            List<Map> rawOracleDatapoints = objectMapper.readValue(oraclesDatasetFile.toFile(), List.class);
            Collections.shuffle(rawOracleDatapoints, random);
            for (int i = 1; i <= 2; i++) {
                List<Map> rawOracleDatapointsHalf = rawOracleDatapoints.subList((i - 1) * rawOracleDatapoints.size() / 2, i * rawOracleDatapoints.size() / 2);
                for (Map rawOracleDatapoint : rawOracleDatapointsHalf) {
                    OracleDatapoint oracleDatapoint = new OracleDatapoint(rawOracleDatapoint);
                    List<OracleDatapoint> alternateOracleDPs = getAlternateOracleDPs(oracleDatapoint);
                    newOracleDatapoints.addAll(alternateOracleDPs);
                    oracleDPsTotal += alternateOracleDPs.size();
                    oracleDPsOriginal++;
                }

                String newOracleDatapointsFile = oraclesDatasetFile.toString().replace(".json", "-" + i + AUGMENTED_SUFFIX);
                objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(newOracleDatapointsFile), newOracleDatapoints.stream().map(OracleDatapoint::toMapAndLists).toList());
                newOracleDatapoints.clear();
            }

            // Delete original file
            Files.delete(oraclesDatasetFile);
        }

        logger.info("Finished augmenting oracles dataset.");
        logger.info("Oracle data points original: {}", oracleDPsOriginal);
        logger.info("Oracle data points allowing augmentation: {}", oracleDPsAllowingAugmentation);
        logger.info("Oracle data points augmented: {}", oracleDPsTotal - oracleDPsOriginal);
        logger.info("Oracle data points total: {}", oracleDPsTotal);
    }

    /**
     * Augment oracle datapoints with new tags and corresponding Javadoc.
     */
    private static List<OracleDatapoint> getAlternateOracleDPs(OracleDatapoint oracleDatapoint) {
        List<Quartet<String, String, String, String>> oracleCombos = new ArrayList<>(); // Quartet: javadocTag, methodJavadoc, oracle, methodSourceCode
        List<OracleDatapoint> alternateOracleDPs = new ArrayList<>();

        String javadocTag = oracleDatapoint.getJavadocTag();
        String javadoc = oracleDatapoint.getMethodJavadoc();
        List<String> currentAlternateTags = new ArrayList<>(alternateTags.getOrDefault(javadocTag, List.of()));

        alternateOracleDPs.add(oracleDatapoint);
        for (String tag : currentAlternateTags) {
            OracleDatapoint alternateOracleDatapoint = new OracleDatapoint(oracleDatapoint);
            alternateOracleDatapoint.setJavadocTag(tag);
            alternateOracleDatapoint.setMethodJavadoc(updateMethodJavadoc(javadoc, javadocTag, tag));
            alternateOracleDPs.add(alternateOracleDatapoint);
        }

        return alternateOracleDPs;
    }
}
