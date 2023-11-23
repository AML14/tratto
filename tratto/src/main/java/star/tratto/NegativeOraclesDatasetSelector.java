package star.tratto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import star.tratto.data.OracleDatapoint;
import star.tratto.data.OracleType;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Given an oracles-dataset, this class selects a subset of data points without
 * associated oracles (negative oracles). The selection is done by selecting the
 * same amount of positive oracles, and the same amount of PRE, POST and EXCEPT
 * conditions. Then, for each category, a set of diverse oracles is selected.
 * <br>
 * The output JSON file is a list of objects with the same properties as the oracle
 * data points, but without those related to the tokens.
 */
public class NegativeOraclesDatasetSelector {

    private static final Logger logger = LoggerFactory.getLogger(NegativeOraclesDatasetSelector.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static String ORACLES_DATASET_FOLDER = "src/main/resources/oracles-dataset/";
    public static String NEGATIVE_ORACLES_FILE = "src/main/resources/negative_oracles.json";

    public static void main(String[] args) throws IOException {
        // Set up files and folders
        Path oraclesDatasetFolder = Paths.get(ORACLES_DATASET_FOLDER);
        DirectoryStream<Path> oraclesDatasetStream = Files.newDirectoryStream(oraclesDatasetFolder);

        // Set up indexes and lists to keep track of positive and negative PRE, POST and EXCEPT oracles
        List<Map> negativeOracles = new ArrayList<>();
        int positivePreOracles = 0;
        int positivePostOracles = 0;
        int positiveExceptOracles = 0;
        List<Map> negativePreOracles = new ArrayList<>();
        List<Map> negativePostOracles = new ArrayList<>();
        List<Map> negativeExceptOracles = new ArrayList<>();

        // Assume that only dataset files are in the folder
        for (Path oraclesDatasetFile : oraclesDatasetStream) {
            logger.info("------------------------------------------------------------");
            logger.info("Processing file: {}", oraclesDatasetFile.getFileName());
            logger.info("------------------------------------------------------------");

            List<Map> rawOracleDatapoints = objectMapper.readValue(oraclesDatasetFile.toFile(), List.class);
            for (Map rawOracleDatapoint : rawOracleDatapoints) {
                OracleDatapoint oracleDatapoint = new OracleDatapoint(rawOracleDatapoint);
                logger.info("Processing oracle: {}", oracleDatapoint.getOracle());

                // Count positive oracles
                if (!oracleDatapoint.getOracle().equals(";")) {
                    if (oracleDatapoint.getOracleType().equals(OracleType.PRE)) {
                        positivePreOracles++;
                    } else if (oracleDatapoint.getOracleType().equals(OracleType.NORMAL_POST)) {
                        positivePostOracles++;
                    } else if (oracleDatapoint.getOracleType().equals(OracleType.EXCEPT_POST)) {
                        positiveExceptOracles++;
                    } else {
                        throw new IllegalStateException("Unknown oracle type: " + oracleDatapoint.getOracleType());
                    }
                    continue; // Skip positive oracles
                }

                // Add negative oracles to lists for later selection
                if (oracleDatapoint.getOracleType().equals(OracleType.PRE)) {
                    negativePreOracles.add(getOracleDatapointMap(oracleDatapoint));
                } else if (oracleDatapoint.getOracleType().equals(OracleType.NORMAL_POST)) {
                    negativePostOracles.add(getOracleDatapointMap(oracleDatapoint));
                } else if (oracleDatapoint.getOracleType().equals(OracleType.EXCEPT_POST)) {
                    negativeExceptOracles.add(getOracleDatapointMap(oracleDatapoint));
                } else {
                    throw new IllegalStateException("Unknown oracle type: " + oracleDatapoint.getOracleType());
                }
            }
        }

        logger.info("------------------------------------------------------------");
        logger.info("Finished processing oracles dataset.");
        logger.info("Total positive PRE oracles: {}", positivePreOracles);
        logger.info("Total positive POST oracles: {}", positivePostOracles);
        logger.info("Total positive EXCEPT oracles: {}", positiveExceptOracles);
        logger.info("Total negative PRE oracles: {}", negativePreOracles.size());
        logger.info("Total negative POST oracles: {}", negativePostOracles.size());
        logger.info("Total negative EXCEPT oracles: {}", negativeExceptOracles.size());
        logger.info("------------------------------------------------------------");

        // Select negative oracles
        logger.info("Selecting negative oracles...");
        logger.info("Selecting negative PRE oracles...");
        negativeOracles.addAll(selectNegativeOracles(negativePreOracles, positivePreOracles));
        logger.info("Selecting negative POST oracles...");
        negativeOracles.addAll(selectNegativeOracles(negativePostOracles, positivePostOracles));
        logger.info("Selecting negative EXCEPT oracles...");
        negativeOracles.addAll(selectNegativeOracles(negativeExceptOracles, positiveExceptOracles));

        if (negativeOracles.size() < positivePreOracles + positivePostOracles + positiveExceptOracles) {
            throw new IllegalStateException("There are not enough negative oracles to match the number " +
                    "of positive oracles of each category. Please check the input dataset. A possible " +
                    "alternative may be to modify the program code to select more oracles from other " +
                    "categories.");
        }

        // Write negative oracles to file
        objectMapper.writeValue(new File(NEGATIVE_ORACLES_FILE), negativeOracles);
    }

    private static Map getOracleDatapointMap(OracleDatapoint oracleDatapoint) {
        Map oracleDatapointMap = new HashMap();
        oracleDatapointMap.put("id", oracleDatapoint.getId());
        oracleDatapointMap.put("oracle", oracleDatapoint.getOracle());
        oracleDatapointMap.put("oracleType", oracleDatapoint.getOracleType());
        oracleDatapointMap.put("projectName", oracleDatapoint.getProjectName());
        oracleDatapointMap.put("packageName", oracleDatapoint.getPackageName());
        oracleDatapointMap.put("className", oracleDatapoint.getClassName());
        oracleDatapointMap.put("javadocTag", oracleDatapoint.getJavadocTag());
        oracleDatapointMap.put("methodJavadoc", oracleDatapoint.getMethodJavadoc());
        oracleDatapointMap.put("methodSourceCode", oracleDatapoint.getMethodSourceCode());
        oracleDatapointMap.put("classJavadoc", oracleDatapoint.getClassJavadoc());
        oracleDatapointMap.put("classSourceCode", oracleDatapoint.getClassSourceCode());
        return oracleDatapointMap;
    }

    /**
     * Selects a diverse enough set of {@code n} oracles from the {@code negativeOracles}
     * list. The selection is done by selecting evenly spaced oracles from three lists
     * in terms of the size of the javadocTag, methodJavadoc and methodSourceCode.
     * If additional oracles are needed, they are chosen based on the overall length
     * of the oracle (javadocTag + methodJavadoc + methodSourceCode).
     */
    private static List<Map> selectNegativeOracles(List<Map> negativeOracles, int n) {
        // Preliminary check: if there are less than n oracles, return all of them
        if (negativeOracles.size() <= n) {
            return negativeOracles;
        }

        List<Map> sortedByJavadocTag = negativeOracles.stream().sorted(Comparator.comparingInt(o -> ((String)o.get("javadocTag")).length())).toList();
        List<Map> sortedByMethodJavadoc = negativeOracles.stream().sorted(Comparator.comparingInt(o -> ((String)o.get("methodJavadoc")).length())).toList();
        List<Map> sortedByMethodSourceCode = negativeOracles.stream().sorted(Comparator.comparingInt(o -> ((String) o.get("methodSourceCode")).length())).toList();
        List<Map> sortedByAll = negativeOracles.stream().sorted(Comparator.comparingInt(o -> (oracleToString(o).length()))).collect(Collectors.toList());

        List<Map> selectedOracles = new ArrayList<>();
        double step = (double) negativeOracles.size() / n;
        int index;
        for (int i = 0; i < n; i += 3) {
            index = (int) Math.round(i * step);
            selectedOracles.add(sortedByJavadocTag.get(index));
            selectedOracles.add(sortedByMethodJavadoc.get(index));
            selectedOracles.add(sortedByMethodSourceCode.get(index));
            sortedByAll.remove(sortedByJavadocTag.get(index));
            sortedByAll.remove(sortedByMethodJavadoc.get(index));
            sortedByAll.remove(sortedByMethodSourceCode.get(index));
        }
        // Remove possible duplicates
        selectedOracles = selectedOracles.stream().distinct().collect(Collectors.toList());
        // Fill with oracles from sortedByAll
        int remaining = n - selectedOracles.size();
        step = (double) sortedByAll.size() / remaining;
        for (int i = 0; i < remaining; i++) {
            selectedOracles.add(sortedByAll.get((int) Math.round(i * step)));
        }

        return selectedOracles;
    }

    private static String oracleToString(Map oracle) {
        String javadocTag = (String) oracle.get("javadocTag");
        String methodJavadoc = (String) oracle.get("methodJavadoc");
        String methodSourceCode = (String) oracle.get("methodSourceCode");
        return javadocTag + " " + methodJavadoc + " " + methodSourceCode;
    }
}
