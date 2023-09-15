import data.OracleOutput;
import data.OracleType;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TogUtilsTest {
    private void setupJDoctorOutput() throws IOException, InterruptedException {
        Path jDoctorSh = Paths.get("generator", "jdoctor.sh");
        Path projectSrc = Paths.get("src", "test", "resources", "project", "src", "main", "java");
        Path projectBin = Paths.get("src", "test", "resources", "project", "target", "classes");
        ProcessBuilder processBuilder = new ProcessBuilder(
                "bash",
                jDoctorSh.toString(),
                "tutorial.Stack",
                projectSrc.toString(),
                projectBin.toString()
        );
        processBuilder.start().waitFor();
    }

    @Test
    public void jDoctorToOracleOutputTest() throws Throwable {
        setupJDoctorOutput();
        TogUtils.jDoctorToOracleOutput();
        Path oraclePath = Paths.get("output", "jdoctor", "oracle", "oracle_output.json");
        List<OracleOutput> oracleOutputList = FileUtils.readJSONList(oraclePath, OracleOutput.class);
        for (OracleOutput oracleOutput : oracleOutputList) {
            assertEquals("tutorial.Stack", oracleOutput.className());
            assertTrue(List.of("push(T o)", "pop()").contains(oracleOutput.methodSignature()));
            assertTrue(List.of(OracleType.PRE, OracleType.EXCEPT_POST).contains(oracleOutput.oracleType()));
            assertTrue(oracleOutput.testName().isEmpty());
        }
        FileUtils.deleteDirectory(Paths.get("output"));
    }
}
