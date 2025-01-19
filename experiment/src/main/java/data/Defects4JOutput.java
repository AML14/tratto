package data;

/**
 * This record represents the performance of a given TOG on Defects4J.
 *
 * @param tog a test oracle generator
 * @param numBugsFound the number of Defects4J bugs with a true positive
 * @param numTruePositive the number of tests that fail on the buggy version,
 *                        but pass on the fixed version
 * @param numFalsePositive the number of tests that fail on the buggy version
 *                         and fail on the fixed version
 * @param numTrueNegative the number of tests that pass on the buggy version
 *                        and pass on the fixed version
 * @param numFalseNegative the number of tests that pass on the buggy version,
 *                         but fail on the fixed version
 */
public record Defects4JOutput(
        String tog,
        int numBugsFound,
        int numTruePositive,
        int numFalsePositive,
        int numTrueNegative,
        int numFalseNegative
) {}
