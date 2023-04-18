package star.tratto.identifiers;

import org.junit.jupiter.api.Test;
import star.tratto.identifiers.path.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PathTest {
    @Test
    public void testPath() {
        String userDir = System.getProperty("user.dir");
        assertEquals(userDir + "/src/main/java/data/collection/javaparser", Path.JAVA_PARSER.getValue());
        assertEquals(userDir + "/target/output", Path.OUTPUT.getValue());
        assertEquals(userDir + "/src/main/resources", Path.RESOURCES.getValue());
        assertEquals(userDir + "/src/main/repos", Path.REPOS.getValue());
    }
}
