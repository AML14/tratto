import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.CallableDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import data.JDoctorOutput;
import data.JDoctorOutput.ParamTag;
import data.JDoctorOutput.ReturnTag;
import data.JDoctorOutput.ThrowsTag;
import data.OracleOutput;
import data.TogType;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * This class provides static methods for pre-processing inputs for a given
 * TOG and converting its output into other {@link data} outputs.
 */
public class TogUtils {
    /** The path of the output directory. */
    private static final Path output = Paths.get("output");
    /** The path of the EvoSuite prefixes directory. */
    private static final Path evosuitePrefixPath = output.resolve("evosuite-prefixes");
    /** The path of the EvoSuite simple tests directory (tests with one assertion).  */
    private static final Path evosuiteTestsSimplePath = output.resolve("evosuite-simple-tests");
    /** A regex pattern to extract a text prefix (removes comments and decorators). */
    private static final Pattern testPrefixPattern = Pattern.compile("(public|protected|private)(.| )*");
    /** A JavaParser used to pre-process source code to be converted into TOGA input. */
    private static JavaParser javaParser;

    /** Private constructor to avoid creating an instance of this class. */
    private TogUtils() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    /**
     * Creates a JavaParser object capable of resolving symbols from a given
     * source directory.
     *
     * @param srcDirPath a project source directory
     * @return the corresponding JavaParser
     */
    public static JavaParser getJavaParser(Path srcDirPath) {
        return new JavaParser();
    }

    /**
     * Generates the input file for the TOGA tog. As required by TOGA, the input is a .csv file
     * composed of three columns:
     * <ol>
     *     <li>the focal method (i.e. the signature of the method for which
     *     TOGA has to generate oracles)</li>
     *     <li>the test prefix of the method under test</li>
     *     <li>the docstring of the method under test</li>
     * </ol>
     * Extracts each method under test from the test prefixes and generate triples of
     * (focalMethod, testPrefix, docstring).
     * Saves the output file in output/toga/input.
     *
     * @param srcDirPath the path to the source code of the project under test
     * @param fullyQualifiedClassName the fully qualified name of the class under test
     *
     */
    public static void generateTOGAInput(Path srcDirPath, String fullyQualifiedClassName) {
    }

    /**
     * Logs error in parsing prefix test and generating TOGA input.
     *
     * @param testPrefix the test prefix that triggers an error.
     * @param fullyQualifiedClassName the fully qualified name of the class under test
     * @param errStr the error to log
     * @return A string in the forms of a csv row (separated by semicolons), containing the
     * test prefix, the fully qualified name, and the error.
     */
    private static String generateLogTogaInputError(MethodDeclaration testPrefix, String fullyQualifiedClassName, String errStr) {
        return "";
    }

    /**
     * Gets the Javadoc comment of a given function.
     *
     * @param jpCallable a JavaParser function
     * @return the method/constructor Javadoc comment
     */
    public static String getCallableJavadoc(
            CallableDeclaration<?> jpCallable
    ) {
        return "";
    }

    /**
     * Gets the Javadoc comment of a body declaration using regex patterns.
     * Use ONLY IF Javadoc comment is not recoverable using JavaParser API.
     *
     * @param jpBody a member in a Java class
     * @return the matched Javadoc comment (empty string if not found)
     */
    private static String getJavadocByPattern(BodyDeclaration<?> jpBody) {
        return "";
    }

    /**
     * Returns the signature of a JavaParser method declaration.
     *
     * @param methodDeclaration a JavaParser method declaration
     * @return a string representation of the signature. Signature follows the
     * format:
     *  "[modifiers] [type] [methodName]([parameters]) throws [exceptions]"
     */
    public static String getMethodSignature(MethodDeclaration methodDeclaration) {
        return "";
    }

    /**
     * Returns the signature of a JavaParser callable declaration. Uses the
     * method source code and removes method body, contained comments, the
     * Javadoc comment, and other special characters (e.g. "\n").
     *
     * @param jpCallable a JavaParser callable declaration
     * @return a string representation of the signature. A signature follows
     * the format:
     *     "[modifiers] [typeParameters] [type] [methodName]([parameters]) throws [exceptions]"
     */
    public static String getCallableSignature(
            CallableDeclaration<?> jpCallable
    ) {
        return "";
    }

    /**
     * Transforms the output from the TOGA script (a .csv file where each row contains the
     * assertion for a given focal method and test prefix) into a json file containing a
     * corresponding list of {@link OracleOutput} records.
     * Saves the output file to "output/toga-oracles/package-name/MyClass_Oracle.json".
     * @see OracleOutput
     * @param togaPath the output JSON file generated by JDoctor
     */
    public static void togaToOracleOutput(Path togaPath) {
    }

    /**
     * Replaces all instances of {@code args[i]} in an oracle with the
     * corresponding parameter names from the method signature.
     *
     * @param jDoctorOutput the parent JDoctor condition of {@code oracle}
     * @param oracle an oracle generated by JDoctor
     * @return the same oracle with parameter names from the method signature.
     * Returns null if unable to parse oracle string.
     */
    private static String contextualizeOracle(JDoctorOutput jDoctorOutput, String oracle) {
        return "";
    }

    /**
     * Converts a JDoctor ParamTag to an OracleOutput record.
     *
     * @param jDoctorOutput the parent JDoctor condition of {@code paramTag}
     * @param paramTag a JDoctor precondition
     * @return the corresponding OracleOutput of {@code paramTag}. Returns
     * null if the oracle is an empty string.
     */
    private static OracleOutput paramTagToOracleOutput(JDoctorOutput jDoctorOutput, ParamTag paramTag) {
        return null;
    }

    /**
     * Converts a JDoctor ReturnTag to an OracleOutput record.
     *
     * @param jDoctorOutput the parent JDoctor condition of {@code returnTag}
     * @param returnTag a JDoctor normal post-condition
     * @return the corresponding OracleOutput of {@code returnTag}. Returns
     * null if {@code returnTag} is null or the oracle is an empty string.
     */
    private static OracleOutput returnTagToOracleOutput(JDoctorOutput jDoctorOutput, ReturnTag returnTag) {
        return null;
    }

    /**
     * Converts a JDoctor ThrowsTag to an OracleOutput record.
     *
     * @param jDoctorOutput the parent JDoctor condition of {@code throwsTag}
     * @param throwsTag a JDoctor exceptional post-condition
     * @return the corresponding OracleOutput of {@code throwsTag}. Returns
     * null if the oracle is an empty string.
     */
    private static OracleOutput throwsTagToOracleOutput(JDoctorOutput jDoctorOutput, ThrowsTag throwsTag) {
        return null;
    }

    /**
     * Converts a JDoctorOutput record to a list of OracleOutput records. A
     * single JDoctorOutput record may contain multiple preconditions, throws
     * conditions, and post-conditions, each necessitating an individual
     * OracleOutput record.
     *
     * @param jDoctorOutput a JDoctor JSON condition
     * @return the corresponding OracleOutputs from the condition
     */
    private static List<OracleOutput> conditionToOracleOutput(JDoctorOutput jDoctorOutput) {
        return new ArrayList<>();
    }

    /**
     * Gets the simple class name from the path to a JDoctor output. The
     * output path is presumed to follow the format:
     * "output/jdoctor-oracles/package-name/MyClass_jdoctor_output.json"
     *
     * @param jDoctorPath a path to JDoctor output
     * @return the simple clas name
     */
    private static String getClassNameFromJDoctorOutput(Path jDoctorPath) {
        return "";
    }

    /**
     * Converts the JSON output generated by JDoctor into a list of
     * {@link OracleOutput} records. Saves the output to
     * "output/jdoctor-oracles/package-name/MyClass_Oracle.json"
     *
     * @param jDoctorPath the output JSON file generated by JDoctor
     */
    public static void jDoctorToOracleOutput(Path jDoctorPath) {
    }

    /**
     * Transforms the output from the Tratto script into a json file containing a
     * corresponding list of {@code OracleOutput} objects.
     * Saves the output file in output/tratto/experiment.
     * @see OracleOutput
     * @param trattoPath the output JSON file generated by Tratto
     * @param srcDirPath the path to the source code of the project under test
     */
    public static void trattoToOracleOutput(Path trattoPath, Path srcDirPath) {
    }

    /**
     * Gets the substring between two specified words in a given input string.
     * If the prefix or suffix occur multiple times, this method will return
     * the substring between the first instance of the prefix and the first
     * instance of the suffix. If prefix is null, uses the start of the base
     * string. If the suffix is null, uses the end of the base string.
     *
     * @param base the input string from which to extract the substring
     * @param prefix the word that marks the start of the substring
     * @param suffix the word that marks the end of the substring
     * @return the substring between the starting and ending words
     * @throws Error if either the prefix or the suffix do not appear in the
     * base string
     */
    private static String getSubstringBetweenWords(String base, String prefix, String suffix) {
        return "";
    }

    /**
     * Gets the failing tests from a Defects4J bug detection log. Each log
     * denotes a failing test in the form:
     * <pre>
     * {@code --- [fullyQualifiedName]_ESTest::[testName]}
     * </pre>
     *
     * @param logPath the path to a bug detection log
     * @return all failing tests for the bug
     */
    private static List<String> getFailingTestsFromLog(Path logPath) {
        return new ArrayList<>();
    }

    /**
     * Generates the Defects4JOutput record to get statistics for a test suite
     * generated by a TOG for the Defects4J database.
     *
     * @param tog a TOG
     * @param testDir test suite directory
     * @param resultsDir test output directory
     */
    public static void generateDefects4JOutput(
            TogType tog,
            Path testDir,
            Path resultsDir
    ) {
    }
}
