package data;

public record TestCase(
        String testCase,
        TestResult testResult
) {
    public enum TestResult {
        PASS,
        FAIL
    }
}
