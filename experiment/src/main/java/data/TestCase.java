package data;

/**
 * This record represents to a Java test case, with fields for the test name,
 * test body, and whether the test passes or fails.
 *
 * @param testName the name of the method test case
 * @param testBody the test body
 * @param testResult the test result (either
 */
public record TestCase(
        String testName,
        String testBody,
        TestResult testResult
) {
    /**
     * An enum representing whether a test passes or fails.
     */
    public enum TestResult {
        PASS,
        FAIL
    }
}
