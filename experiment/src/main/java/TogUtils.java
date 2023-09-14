import com.github.javaparser.JavaParser;
import data.OracleOutput;
import data.TestOutput;
import data.TogType;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This class provides static methods for pre-processing inputs for a given
 * TOG and converting its output into other {@link data} output records.
 */
public class TogUtils {
    /** The path of the output directory. */
    private static final Path output = Paths.get("output");
    /** The path of the EvoSuite prefixes directory. */
    private static final Path evosuitePrefixPath = output.resolve("evosuite-prefix");
    /** The path of the EvoSuite simple tests directory (tests with one assertion).  */
    private static final Path evosuiteTestsSimplePath = output.resolve("evosuite-tests-simple");
    /** A JavaParser used to pre-process source code to be converted into TOGA input. */
    private static JavaParser javaParser;

    /** Private constructor to avoid creating an instance of this class. */
    private TogUtils() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    /**
     * Creates a JavaParser object able to resolve symbols from a given source
     * directory.
     *
     * @param srcDirPath a project source directory
     * @return the corresponding JavaParser
     */
    public static JavaParser getJavaParser(Path srcDirPath) {
        return null;
    }

    /**
     * Generates the input file for TOGA. As required by TOGA, the input is a
     * .csv file composed of three columns:
     * <ol>
     *     <li>the focal method (i.e. the signature of the method for which
     *     TOGA has to generate oracles)</li>
     *     <li>the test prefix of the method under test</li>
     *     <li>the docstring of the method under test</li>
     * </ol>
     * This method extracts each method under test from the test prefixes and
     * generates triples of (focalMethod, testPrefix, docString). Saves the
     * output to "output/toga/input".
     *
     * @param srcDirPath the source directory of the project under test
     * @param fullyQualifiedName the class under test
     *
     */
    public static void generateTOGAInput(Path srcDirPath, String fullyQualifiedName) {
    }

    /**
     * Converts TOGA output (a .csv file where each row contains an assertion
     * for a given focal method and test prefix) to a JSON file of
     * {@link OracleOutput} records. Saves the output to "output/toga/oracle".
     *
     * @param srcDirPath the source directory of the project under test
     * @see OracleOutput
     */
    public static void togaToOracleOutput(Path srcDirPath) {
    }

    /**
     * Converts the JSON output generated by JDoctor into a list of
     * {@link OracleOutput} records and saves the output to
     * "output/jdoctor/oracle".
     *
     * @param jDoctorPath path to the JDoctor JSON output
     * @throws IllegalArgumentException if jDoctorPath does not exist
     */
    public static void jDoctorToOracleOutput(Path jDoctorPath) {
    }

    /**
     * Transforms the output from the Tratto script into a json file containing a
     * corresponding list of {@code OracleOutput} objects.
     * Saves the output file in output/tratto/experiment.
     * @see OracleOutput
     * @param srcDirPath the path to the source code of the project under test
     */
    public static void trattoToOracleOutput(Path srcDirPath) {
    }

    /**
     * Writes a {@link TestOutput} object to a given path. The output contains
     * information about the TOG which generated the test, the fully qualified
     * name of the class under test, the project source directory, the project
     * binaries directory, the number of passing test, the number of failing
     * tests, and a list of each test generated by the tog (with a label
     * corresponding to whether the test passes or fails).
     *
     * @param relativeRoot the relative path to the project root directory
     * @param togType the tog which generated the tests
     * @param className the fully qualified name of the class under test
     * @param srcDir the path to the project source directory
     * @param binDir the path to the project system binaries
     */
    public static void writeTestOutput(
            Path relativeRoot,
            TogType togType,
            String className,
            Path srcDir,
            Path binDir
    ) {
    }

    public static void writeDefects4JOutput(
    ) {
    }
}
