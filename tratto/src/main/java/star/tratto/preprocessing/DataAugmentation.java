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
    private static final String ALTERNATE_ORACLES_FILENAME = "src/main/resources/data-augmentation/oracles.json";
    public static String ALTERNATE_TAGS_FILENAME = "src/main/resources/data-augmentation/javadoc-tags.json"; // public for testing purposes
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
        logger.info("Reading alternate oracles from: {}", ALTERNATE_ORACLES_FILENAME);
        logger.info("Reading alternate Javadoc tags from: {}", ALTERNATE_TAGS_FILENAME);

        List<OracleDatapoint> newOracleDatapoints = new ArrayList<>();
        alternateOracles = objectMapper.readValue(new File(ALTERNATE_ORACLES_FILENAME), new TypeReference<>(){});
        alternateTags = objectMapper.readValue(new File(ALTERNATE_TAGS_FILENAME), new TypeReference<>(){});

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
     * The returned list WILL contain the original oracle.
     * <br>
     * The returned list MAY contain up to 10 oracles.
     * <br>
     * The returned list MAY contain the following variations IF possible:
     * <ul>
     *     <li>Case 1: Original.</li>
     *     <li>Case 2: Without Javadoc tag + oracle1.</li>
     *     <li>Case 3: Without Javadoc + oracle2.</li>
     *     <li>Case 4: Without method body + oracle3 + Javadoc tag1.</li>
     *     <li>Case 5: Without method body and without Javadoc tag + oracle4.</li>
     *     <li>Case 6: Without method body and without Javadoc + oracle5.</li>
     *     <li>Case 7: Oracle6 + Javadoc tag2.</li>
     *     <li>Case 8: Without method body + oracle7 + Javadoc tag3</li>
     *     <li>Case 9: Oracle8 + Javadoc tag4.</li>
     *     <li>Case 10: Without method body + oracle9 + Javadoc tag5</li>
     * </ul>
     */
    private static List<OracleDatapoint> getAlternateOracleDPs(OracleDatapoint oracleDatapoint) {
        List<Quartet<String, String, String, String>> oracleCombos = new ArrayList<>(); // Quartet: javadocTag, methodJavadoc, oracle, methodSourceCode
        List<OracleDatapoint> alternateOracleDPs = new ArrayList<>();

        String javadocTag = oracleDatapoint.getJavadocTag();
        String javadoc = oracleDatapoint.getMethodJavadoc();
        String oracle = compactExpression(oracleDatapoint.getOracle());
        String methodSourceCode = oracleDatapoint.getMethodSourceCode();
        String javadocEmptyTag = updateMethodJavadoc(javadoc, javadocTag, "");

        // Prepare list of Javadoc tags
        List<String> currentAlternateTags = new ArrayList<>(alternateTags.getOrDefault(javadocTag, List.of()));
        Collections.shuffle(currentAlternateTags, random);
        currentAlternateTags.add(javadocTag); // Add default Javadoc tag
        int tagIndex = 0;
        int nTags = currentAlternateTags.size();

        // Prepare list of method Javadoc
        List<String> currentAlternateJavadocs = currentAlternateTags
                .stream()
                .map(tag -> updateMethodJavadoc(javadoc, javadocTag, tag))
                .toList();

        // Prepare list of oracles
        List<String> currentAlternateOracles = new ArrayList<>(alternateOracles.getOrDefault(oracle, List.of()));
        Collections.shuffle(currentAlternateOracles, random);
        currentAlternateOracles.add(oracle); // Add default oracle
        int oracleIndex = 0;
        int nOracles = currentAlternateOracles.size();

        // Prepare alternatives of method source code
        String methodSignature = methodSourceCode; // If method is constructor, we cannot remove method body
        if (getMethodDeclaration(methodSourceCode) != null) {
            methodSignature = methodSourceCode.split("\\{")[0];
            methodSignature = methodSignature.stripTrailing();
            methodSignature = methodSignature.endsWith(";") ? methodSignature : methodSignature + ";";
        }

        // Case 1: Original
        oracleCombos.add(Quartet.with(javadocTag, javadoc, oracle, methodSourceCode));

        // Case 2: Without Javadoc tag + oracle1
        oracleCombos.add(Quartet.with("", javadocEmptyTag, currentAlternateOracles.get(oracleIndex), methodSourceCode));
        oracleIndex = (oracleIndex + 1) % nOracles;

        // Case 3: Without Javadoc + oracle2
        oracleCombos.add(Quartet.with("", "", currentAlternateOracles.get(oracleIndex), methodSourceCode));
        oracleIndex = (oracleIndex + 1) % nOracles;

        // Case 4: Without method body + oracle3 + Javadoc tag1
        oracleCombos.add(Quartet.with(currentAlternateTags.get(tagIndex), currentAlternateJavadocs.get(tagIndex), currentAlternateOracles.get(oracleIndex), methodSignature));
        tagIndex = (tagIndex + 1) % nTags;
        oracleIndex = (oracleIndex + 1) % nOracles;

        // Case 5: Without method body and without Javadoc tag + oracle4
        oracleCombos.add(Quartet.with("", javadocEmptyTag, currentAlternateOracles.get(oracleIndex), methodSignature));
        oracleIndex = (oracleIndex + 1) % nOracles;

        // Case 6: Without method body and without Javadoc + oracle5
        oracleCombos.add(Quartet.with("", "", currentAlternateOracles.get(oracleIndex), methodSignature));
        oracleIndex = (oracleIndex + 1) % nOracles;

        // Case 7: Oracle6 + Javadoc tag2
        oracleCombos.add(Quartet.with(currentAlternateTags.get(tagIndex), currentAlternateJavadocs.get(tagIndex), currentAlternateOracles.get(oracleIndex), methodSourceCode));
        tagIndex = (tagIndex + 1) % nTags;
        oracleIndex = (oracleIndex + 1) % nOracles;

        // Case 8: Without method body + oracle7 + Javadoc tag3
        oracleCombos.add(Quartet.with(currentAlternateTags.get(tagIndex), currentAlternateJavadocs.get(tagIndex), currentAlternateOracles.get(oracleIndex), methodSignature));
        tagIndex = (tagIndex + 1) % nTags;
        oracleIndex = (oracleIndex + 1) % nOracles;

        // Case 9: Oracle8 + Javadoc tag4
        oracleCombos.add(Quartet.with(currentAlternateTags.get(tagIndex), currentAlternateJavadocs.get(tagIndex), currentAlternateOracles.get(oracleIndex), methodSourceCode));
        tagIndex = (tagIndex + 1) % nTags;
        oracleIndex = (oracleIndex + 1) % nOracles;

        // Case 10: Without method body + oracle9 + Javadoc tag5
        oracleCombos.add(Quartet.with(currentAlternateTags.get(tagIndex), currentAlternateJavadocs.get(tagIndex), currentAlternateOracles.get(oracleIndex), methodSignature));
//        tagIndex = (tagIndex + 1) % nTags;
//        oracleIndex = (oracleIndex + 1) % nOracles;

        // Remove possible duplicates
        oracleCombos = oracleCombos.stream().distinct().toList();

        // Create OracleDatapoint objects
        for (Quartet<String, String, String, String> oracleCombo : oracleCombos) {
            OracleDatapoint alternateOracleDatapoint = new OracleDatapoint(oracleDatapoint);
            alternateOracleDatapoint.setJavadocTag(oracleCombo.getValue0());
            alternateOracleDatapoint.setMethodJavadoc(oracleCombo.getValue1());
            alternateOracleDatapoint.setOracle(oracleCombo.getValue2());
            alternateOracleDatapoint.setMethodSourceCode(oracleCombo.getValue3());
            alternateOracleDPs.add(alternateOracleDatapoint);
        }

        if (alternateOracleDPs.size() > 1) {
            oracleDPsAllowingAugmentation++;
        }

        return alternateOracleDPs;
    }
}
