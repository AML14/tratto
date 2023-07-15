package star.tratto.identifiers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IOPathTest {
    @Test
    public void pathTest() {
        String userDir = System.getProperty("user.dir");
        /* TODO: variable no longer exists
        assertEquals(userDir + "/src/main/java/data/collection/javaparser", IOPath.JAVA_PARSER.getValue());
        */
        assertEquals(userDir + "/target/output", IOPath.OUTPUT.getValue());
        assertEquals(userDir + "/src/main/resources/projects-source", IOPath.RESOURCES.getValue());
        assertEquals(userDir + "/src/main/java/star/tratto/data/repos", IOPath.REPOS.getValue());
    }
}
