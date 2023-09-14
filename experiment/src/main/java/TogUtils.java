import com.github.javaparser.JavaParser;
import data.Defects4JOutput;
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
     * Converts the JSON output generated by JDoctor into a list of
     * {@link OracleOutput} records. Saves the output to
     * "output/jdoctor/oracle".
     *
     * @see OracleOutput
     */
    public static void jDoctorToOracleOutput() {
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
     * Converts Tratto output into a JSON file of {@link OracleOutput}
     * records. Saves the output to "output/tratto/oracle".
     *
     * @param srcDirPath the source directory of the project under test
     * @see OracleOutput
     */
    public static void trattoToOracleOutput(Path srcDirPath) {
    }

    /**
     * Creates a {@link TestOutput} record for a given TOG. The output
     * contains information about the TOG which generate the test, the fully
     * qualified name of the class under test, the project source directory,
     * the project system binaries, the number of passing tests, the number
     * of failing tests, and a list of each test generated by the TOG (with a
     * label indicating whether the test passes or fails).
     *
     * @param togType a tog
     * @param className the class under test
     * @param srcDir the source directory of the project under test
     * @param binDir the system binaries of the project under test
     * @param fileName the name of the saved TestOutput record
     */
    public static void writeTestOutput(
            TogType togType,
            String className,
            Path srcDir,
            Path binDir,
            String fileName
    ) {
    }

    /**
     * Creates a {@link Defects4JOutput} record for a given TOG and bug in
     * Defects4J. Uses two {@link TestOutput} records (one for the buggy
     * version and one for the fixed version) to classify tests as false
     * positives, false negatives, true positives, or true negatives. See
     * the top-level README for further detail on the classification process.
     *
     * @param togType a tog
     * @param projectName the name of the project under test
     * @param bugID the bug id
     * @param fullyQualifiedName the class under test
     */
    public static void writeDefects4JOutput(
            TogType togType,
            String projectName,
            int bugID,
            String fullyQualifiedName
    ) {
    }

    /**
     * Combines all {@link Defects4JOutput} records for a given TOG into one
     * record.
     *
     * @param togType a tog
     */
    public static void combineDefects4JOutput(
        TogType togType
    ) {
    }
}
