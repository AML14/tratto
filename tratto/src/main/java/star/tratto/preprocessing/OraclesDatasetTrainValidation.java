package star.tratto.preprocessing;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.javatuples.Triplet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import star.tratto.data.TrattoPath;
import star.tratto.util.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * This class splits the oracles located at src/main/resources/oracles-dataset into
 * train (90%) and validation (10%) sets, with a margin of +/- 2%. The split is
 * done randomly, but aiming to keep a balanced distribution of positive and negative
 * oracles in both sets (50% +/- 5%) and preventing data leakage (this is ensured
 * by placing all oracles of every file in a single set).
 */
public class OraclesDatasetTrainValidation {
    private static final Logger logger = LoggerFactory.getLogger(OraclesDatasetTrainValidation.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Random random = new Random(42); // Change seed to achieve desired randomness
    public static String ORACLES_DATASET_FOLDER = "src/main/resources/oracles-dataset/";
    public static String ORACLES_DATASET_TRAIN_FOLDER = "src/main/resources/oracles-dataset-train/";
    public static String ORACLES_DATASET_VALIDATION_FOLDER = "src/main/resources/oracles-dataset-validation/";

    // The train and validation sets must contain at least one file of each project, including both positive and negative oracles
    private static List<String> projects = FileUtils.readJSONList(TrattoPath.INPUT_PROJECTS.getPath())
            .stream()
            .map(p -> (String) ((Map)p).get("projectName"))
            .toList();


    public static float TV_RATIO = 0.9f; // Train/Validation ratio
    public static float PN_RATIO = 0.5f; // Positive/negative ratio
    public static float DELTA_TV = 0.01f; // Margin of error for train/validation ratio
    public static float DELTA_PN = 0.02f; // Margin of error for positive/negative ratio

    public static void main(String[] args) throws IOException {
        // Set up files and folders
        FileUtils.deleteDirectory(Paths.get(ORACLES_DATASET_TRAIN_FOLDER));
        FileUtils.createDirectories(Paths.get(ORACLES_DATASET_TRAIN_FOLDER));
        FileUtils.deleteDirectory(Paths.get(ORACLES_DATASET_VALIDATION_FOLDER));
        FileUtils.createDirectories(Paths.get(ORACLES_DATASET_VALIDATION_FOLDER));
        Path oraclesDatasetFolder = Paths.get(ORACLES_DATASET_FOLDER);
        List<Path> oraclesFiles = StreamSupport.stream(Files.newDirectoryStream(oraclesDatasetFolder).spliterator(), false).collect(Collectors.toList());
        Collections.shuffle(oraclesFiles, random);
        List<Triplet<String, Long, Long>> trainFiles = new ArrayList<>(); // File name, positive oracles, negative oracles
        List<Triplet<String, Long, Long>> validationFiles = new ArrayList<>(); // File name, positive oracles, negative oracles

        // Keep track of the number of oracles in each set. Initialize to 1 to avoid division by 0
        long to = 1; // Train oracles
        long vo = 1; // Validation oracles

        // Assume that only dataset files are in the folder
        for (Path oraclesFile : oraclesFiles) {
            logger.info("------------------------------------------------------------");
            logger.info("Processing file: {}", oraclesFile.getFileName());

            List<Map> rawOracleDatapoints = objectMapper.readValue(oraclesFile.toFile(), List.class);
            long currentOracles = rawOracleDatapoints.size();
            long currentPositiveOracles = rawOracleDatapoints.stream().filter(o -> !o.get("oracle").equals(";")).count();
            long currentNegativeOracles = currentOracles - currentPositiveOracles;


            if ((double) to / (to + vo) < TV_RATIO) { // Keep adding to train set
                trainFiles.add(Triplet.with(oraclesFile.getFileName().toString(), currentPositiveOracles, currentNegativeOracles));
                to += currentOracles;
            } else { // Keep adding to validation set
                validationFiles.add(Triplet.with(oraclesFile.getFileName().toString(), currentPositiveOracles, currentNegativeOracles));
                vo += currentOracles;
            }

            logger.info("Train oracles: {}", to);
            logger.info("Validation oracles: {}", vo);
            logger.info("Train/Validation ratio: {}", (double) to / (to + vo));
        }

        // Now ensure that the distribution of positive and negative oracles is balanced with a margin of +/- 5%,
        // while keeping the 90/10 split with a margin of +/- 2%
        int iterations = 0;
        do {
            logger.info("----------------------------------------------");
            logger.info("Performing balancing iteration number {}...", ++iterations);

            long tpo = trainFiles.stream().mapToLong(Triplet::getValue1).sum(); // Train positive oracles
            long tno = trainFiles.stream().mapToLong(Triplet::getValue2).sum(); // Train negative oracles
            to = tpo + tno;
            long vpo = validationFiles.stream().mapToLong(Triplet::getValue1).sum(); // Validation positive oracles
            long vno = validationFiles.stream().mapToLong(Triplet::getValue2).sum(); // Validation negative oracles
            vo = vpo + vno;
            double tvRatio = (double) to / (to + vo); // Train/Validation ratio
            double tpnRatio = (double) tpo / (tpo + tno); // Train positive/negative ratio
            double vpnRatio = (double) vpo / (vpo + vno); // Validation positive/negative ratio

            logger.info("Train/Validation ratio: {}", tvRatio);
            logger.info("Train positive/negative ratio: {}", tpnRatio);
            logger.info("Validation positive/negative ratio: {}", vpnRatio);

            // Compute number of files and oracles in each set
            logger.info("Train files: {}", trainFiles.size());
            logger.info("Train oracles: {}", to);
            logger.info("Train positive oracles: {}", tpo);
            logger.info("Train negative oracles: {}", tno);
            logger.info("Validation files: {}", validationFiles.size());
            logger.info("Validation oracles: {}", vo);
            logger.info("Validation positive oracles: {}", vpo);
            logger.info("Validation negative oracles: {}", vno);
            logger.info("----------------------------------------------");

            // Compute highest deviation from the desired ratios
            double tvRatioDeviation = Math.abs(tvRatio - TV_RATIO) - DELTA_TV;
            double tpnRatioDeviation = Math.abs(tpnRatio - PN_RATIO) - DELTA_PN;
            double vpnRatioDeviation = Math.abs(vpnRatio - PN_RATIO) - DELTA_PN;
            double maxDeviation = Math.max(tvRatioDeviation, Math.max(tpnRatioDeviation, vpnRatioDeviation));

            if (tvRatio < TV_RATIO - DELTA_TV && maxDeviation == tvRatioDeviation) { // Move one file from validation to train
                moveFile(validationFiles, trainFiles, null);
            } else if (tvRatio > TV_RATIO + DELTA_TV && maxDeviation == tvRatioDeviation) { // Move one file from train to validation
                moveFile(trainFiles, validationFiles, null);
            } else if (tpnRatio < PN_RATIO - DELTA_PN && maxDeviation == tpnRatioDeviation) { // Move one file from validation to train, which has more positive oracles
                moveFile(validationFiles, trainFiles, true);
            } else if (tpnRatio > PN_RATIO + DELTA_PN && maxDeviation == tpnRatioDeviation) { // Move one file from train to validation, which has more positive oracles
                moveFile(trainFiles, validationFiles, true);
            } else if (vpnRatio < PN_RATIO - DELTA_PN && maxDeviation == vpnRatioDeviation) { // Move one file from validation to train, which has less positive oracles
                moveFile(validationFiles, trainFiles, false);
            } else if (vpnRatio > PN_RATIO + DELTA_PN && maxDeviation == vpnRatioDeviation) { // Move one file from train to validation, which has less positive oracles
                moveFile(trainFiles, validationFiles, false);
            }
            else if (
                    tvRatio < TV_RATIO + DELTA_TV &&
                    tvRatio > TV_RATIO - DELTA_TV &&
                    tpnRatio < PN_RATIO + DELTA_PN &&
                    tpnRatio > PN_RATIO - DELTA_PN &&
                    vpnRatio < PN_RATIO + DELTA_PN &&
                    vpnRatio > PN_RATIO - DELTA_PN
            ) {
                boolean allOK = true;
                for (String project : projects) {
                    if (
                            trainFiles.stream().noneMatch(f -> f.getValue0().contains(project)) ||
                            trainFiles.stream().filter(f -> f.getValue0().contains(project)).allMatch(f -> f.getValue1() == 0) ||
                            trainFiles.stream().filter(f -> f.getValue0().contains(project)).allMatch(f -> f.getValue2() == 0)
                    ) {
                        logger.info("Moving one file from validation to train to ensure that both sets contain at least one file of " +
                                "each project, and that both sets contain both positive and negative oracles of those projects...");
                        moveFile(validationFiles, trainFiles, null);
                        allOK = false;
                        break;
                    } else if (
                            validationFiles.stream().noneMatch(f -> f.getValue0().contains(project)) ||
                            validationFiles.stream().filter(f -> f.getValue0().contains(project)).allMatch(f -> f.getValue1() == 0) ||
                            validationFiles.stream().filter(f -> f.getValue0().contains(project)).allMatch(f -> f.getValue2() == 0)
                    ) {
                        logger.info("Moving one file from train to validation to ensure that both sets contain at least one file of " +
                                "each project, and that both sets contain both positive and negative oracles of those projects...");
                        moveFile(trainFiles, validationFiles, null);
                        allOK = false;
                        break;
                    }
                }
                if (allOK) {
                    logger.info("All ratios are within the desired range. Stopping balancing iterations.");
                    break;
                }
            }
        } while (true);

        // Time to move the files to their respective folders!
        logger.info("Moving training files to their folder...");
        for (Triplet<String, Long, Long> trainFile : trainFiles) {
            logger.info("Moving file: {}", trainFile.getValue0());
            Path source = Paths.get(ORACLES_DATASET_FOLDER + trainFile.getValue0());
            Path destination = Paths.get(ORACLES_DATASET_TRAIN_FOLDER + trainFile.getValue0());
            FileUtils.move(source, destination);
        }
        logger.info("Moving validation files to their folder...");
        for (Triplet<String, Long, Long> validationFile : validationFiles) {
            logger.info("Moving file: {}", validationFile.getValue0());
            Path source = Paths.get(ORACLES_DATASET_FOLDER + validationFile.getValue0());
            Path destination = Paths.get(ORACLES_DATASET_VALIDATION_FOLDER + validationFile.getValue0());
            FileUtils.move(source, destination);
        }

        logger.info("Finished splitting oracles dataset into train and validation sets.");
    }

    /**
     * @param filter null for no filter, true for filtering positive ratio, false for filtering negative ratio
     */
    private static void moveFile(List<Triplet<String, Long, Long>> source, List<Triplet<String, Long, Long>> destination, Boolean filter) {
        Collections.shuffle(source, random);
        Triplet<String, Long, Long> file = source.get(source.size() - 1);
        if (filter != null) {
            List<Triplet<String, Long, Long>> filteredFiles = source
                    .stream()
                    .filter(f -> filter ? f.getValue1() > f.getValue2() : f.getValue1() < f.getValue2())
                    .toList();
            file = filteredFiles.get(filteredFiles.size() - 1);
        }
        source.remove(file);
        destination.add(file);
    }
}


