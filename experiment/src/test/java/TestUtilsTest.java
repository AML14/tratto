import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestUtilsTest {
    private static final Path examplePath = Paths.get("src/test/java/example");
    private static final Path outputPath = Paths.get("output");

    @Test
    public void removeOraclesTest() {
        TestUtils.removeOracles(examplePath);
//        FileUtils.deleteDirectory(outputPath.resolve("evosuite-prefix"));
    }
}
