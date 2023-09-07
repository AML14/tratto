package data;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TestCase(
        @JsonProperty("test") String testCase,
        @JsonProperty("result") TestResult testResult
) {
    public enum TestResult {
        PASS,
        FAIL
    }
}
