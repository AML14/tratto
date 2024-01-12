import data.OracleOutput;
import data.OracleType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@ExtendWith({OutputManager.class})
public class OracleInserterTest {
    private static final Path output = Paths.get("output");
    private static final Path resourcesPath = Paths.get("src", "test", "resources");
    private static final Path projectJarPath = Paths.get("src", "test", "resources", "project", "target", "Tutorial_Stack-1.0-SNAPSHOT.jar");
    private static final String projectClasspath = projectJarPath.toAbsolutePath().toString();

    private List<OracleOutput> getAxiomaticOracles() {
        return List.of(
                new OracleOutput(
                        "tutorial.Stack",
                        "push(java.lang.Object o)",
                        OracleType.PRE,
                        "(o == null) == false",
                        "",
                        ""
                ),
                new OracleOutput(
                        "tutorial.Stack",
                        "push(java.lang.Object o)",
                        OracleType.EXCEPT_POST,
                        "o == null",
                        "java.lang.IllegalArgumentException",
                        ""
                )
        );
    }

    private List<OracleOutput> getNonAxiomaticOracles() {
        return List.of(
                new OracleOutput(
                        "java.lang.Integer",
                        "valueOf(int i)",
                        OracleType.NON_AXIOMATIC,
                        "assertTrue(primitiveInt == objectInt.intValue())",
                        "",
                        "assertionTest"
                ),
                new OracleOutput(
                        "java.lang.Integer",
                        "parseInt(java.lang.String s)",
                        OracleType.NON_AXIOMATIC,
                        "",
                        "java.lang.NumberFormatException",
                        "exceptionalTest"
                )
        );
    }

    private void setup(boolean isAxiomatic) {
        FileUtils.deleteDirectory(output);
        // copy test suite
        FileUtils.copy(
                resourcesPath.resolve("prefix"),
                output.resolve("evosuite-prefixes")
        );
        // get oracles
        List<OracleOutput> oracles;
        if (isAxiomatic) {
            oracles = getAxiomaticOracles();
        } else {
            oracles = getNonAxiomaticOracles();
        }
        // write oracles
        FileUtils.writeJSON(Paths.get("output", "jdoctor-oracles", "tutorial", "Stack_Oracle.json"), oracles);
    }

    private void cleanup() {
        FileUtils.deleteDirectory(output);
    }

    @Test
    public void insertAxiomaticOraclesTest() {
        setup(true);
        OracleInserter.insertOracles(
                Paths.get("output", "evosuite-prefixes"),
                Paths.get("output", "jdoctor-oracles"),
                projectClasspath
        );
//        cleanup();
    }

    @Test
    public void insertNonAxiomaticOraclesTest() {
        setup(false);
        OracleInserter.insertOracles(
                Paths.get("output", "evosuite-prefixes"),
                Paths.get("output", "toga-tests"),
                projectClasspath
        );
        cleanup();
    }
}
