package star.tratto.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import star.tratto.data.OracleDatapoint;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static star.tratto.TestUtilities.ORACLES_DATASET_FILENAME;

public class OracleDatapointTest {

    @Test
    public void oracleDatapointTest() throws IOException {
        List<Map> rawOracleDatapoints = new ObjectMapper().readValue(new File(ORACLES_DATASET_FILENAME), List.class);
        OracleDatapoint oracleDatapoint = new OracleDatapoint(rawOracleDatapoints.get(0));

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
