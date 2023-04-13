package star.tratto;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import star.tratto.dataset.oracles.OracleDatapoint;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static star.tratto.dataset.ExcelManager.getFirstSheet;

public class TestUtilities {

    private static final Logger logger = LoggerFactory.getLogger(TestUtilities.class);

    private static final String ORACLES_DATASET_PATH = "src/test/resources/oracles-dataset.xlsx";

    public static List<String> readOraclesFromExternalFiles() {
        // Read oracles from external files
        List<String> stringOracles = new ArrayList<>();
        try {
            Path file1Path = Paths.get("src/test/resources/jdoctorOracles.tr");
            Path file2Path = Paths.get("src/test/resources/additionalSamples.tr");
            stringOracles.addAll(Files.readAllLines(file1Path));
            stringOracles.addAll(Files.readAllLines(file2Path));
        } catch (IOException e) {
            logger.error("Error reading the file: {}" + e.getMessage());
        }

        return stringOracles;
    }

    public static List<OracleDatapoint> readOracleDatapointsFromOraclesDataset() {
        List<OracleDatapoint> oracleDatapoints = new ArrayList<>();
        Sheet oraclesDatasetSheet = getFirstSheet(ORACLES_DATASET_PATH);
        for (Row oraclesDatasetRow : oraclesDatasetSheet) {
            if (oraclesDatasetRow.getRowNum() == 0) { // Skip header row
                continue;
            }
            oracleDatapoints.add(new OracleDatapoint(oraclesDatasetRow));
        }
        try {
            oraclesDatasetSheet.getWorkbook().close();
        } catch (IOException e) {
            logger.error("Error closing the oracles dataset file: {}" + e.getMessage());
        }
        return oracleDatapoints;
    }
}
