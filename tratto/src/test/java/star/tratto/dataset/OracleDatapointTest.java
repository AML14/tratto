package star.tratto.dataset;

import org.apache.poi.ss.usermodel.Sheet;
import org.junit.jupiter.api.Test;
import star.tratto.dataset.oracles.OracleDatapoint;

import static star.tratto.dataset.ExcelManager.getFirstSheet;

public class OracleDatapointTest {

    private static final String oraclesDatasetPath = "src/main/resources/oracles-dataset.xlsx";

    @Test
    public void oracleDatapointTest() {
        Sheet oraclesDatasetSheet = getFirstSheet(oraclesDatasetPath);
        OracleDatapoint oracleDatapoint = new OracleDatapoint(oraclesDatasetSheet.getRow(1));

        // TODO: Add assertions
        // For the moment, the test passes as long as no exceptions are thrown
    }

    /**
     * Helper method for integration testing purposes.
     */
    public static OracleDatapoint getEmptyOracleDatapoint() {
        return new OracleDatapoint();
    }
}
