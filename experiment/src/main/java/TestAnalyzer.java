import data.TestOutput;

import java.nio.file.Path;
import java.util.List;

/**
 * This class provides static methods for calculating the mutation score of a
 * given test suite, and classifying each test case based on whether a test
 * passes or fails.
 */
public class TestAnalyzer {
    /** Private constructor to avoid creating an instance of this class. */
    private TestAnalyzer() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    /**
     * Calculates the mutation score of a test suite in a given path. Uses
     * PITest.
     *
     * @param dir a directory with Java test files
     * @return the mutation score
     */
    public static double getMutationScore(Path dir) {
        return -1.0;
    }

    /**
     * Runs each test in a given directory and returns a list of information
     * for each test case (including whether the test case passes or fails).
     *
     * @param dir a directory with Java test files
     * @see TestOutput
     */
    public static List<TestOutput> getTestClassification(Path dir) {
        return null;
    }
}
