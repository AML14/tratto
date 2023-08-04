import java.nio.file.Path;
import java.util.List;

/**
 * This method provides
 */
public class TestUtils {

    /**
     * Removes all assertions from all test files in a given directory. Saves
     * the modified test cases in output/evosuite-prefix/. Does not override
     * original test files.
     *
     * @param dir a directory with Java test files
     */
    public static void removeOracles(Path dir) {

    }

    /**
     * Adds axiomatic oracles to test prefixes in a given directory. Axiomatic
     * oracles are not specific to a given test prefix. Therefore, we insert
     * the axiomatic oracles wherever they may be applicable in source code.
     * For example, if an axiomatic oracle involves class Foo, then the oracle
     * is added after every instance of Foo in all test prefixes.
     *
     * @param dir a directory with Java test prefixes
     * @param oracles a list of test oracles made by an axiomatic tog
     */
    private static void insertAxiomaticOracles(Path dir, List<String> oracles) {

    }

    /**
     * Adds non-axiomatic oracles to test prefixes in a given directory.
     * Non-axiomatic oracles are specific to a given test prefix. We assume
     * the tests in the directory appear in the same order as the oracles.
     * Each oracle is added as an assertion at the end of the test prefix.
     *
     * @param dir a directory with Java test prefixes
     * @param oracles a list of test oracles made by a non-axiomatic tog
     */
    private static void insertNonAxiomaticOracles(Path dir, List<String> oracles) {

    }

    /**
     * @param tog a test oracle generator
     * @return true iff the given tog generates axiomatic test oracles (known
     * a priori)
     */
    private static boolean isAxiomatic(String tog) {
        switch (tog) {
            case "jdoctor", "tratto" -> {
                return true;
            }
            case "toga" -> {
                return false;
            }
            default -> throw new IllegalArgumentException("Unrecognized test oracle generator: " + tog);
        }
    }

    /**
     * Adds oracles to test prefixes in a given directory. Our approach for
     * adding oracles varies based on whether the oracles are axiomatic or
     * non-axiomatic. Saves the modified test prefixes in
     * output/tog-test/[tog], where [tog] is the given test oracle generator.
     * Does not override original test prefixes.
     *
     * @param dir a directory with Java test prefixes
     * @param tog the name of the test oracle generator being analyzed
     * @param oracles a list of test oracles made by the given tog
     * @see TestUtils#insertAxiomaticOracles(Path, List)
     * @see TestUtils#insertNonAxiomaticOracles(Path, List)
     */
    public static void insertOracles(Path dir, String tog, List<String> oracles) {
        if (isAxiomatic(tog)) {
            insertAxiomaticOracles(dir, oracles);
        } else {
            insertNonAxiomaticOracles(dir, oracles);
        }
    }
}
