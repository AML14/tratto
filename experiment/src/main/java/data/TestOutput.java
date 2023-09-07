package data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record TestOutput(
        @JsonProperty("tog") String tog,
        @JsonProperty("className") String className,
        @JsonProperty("sourceDir") String sourceDir,
        @JsonProperty("binDir") String binDir,
        @JsonProperty("numPass") int numPass,
        @JsonProperty("numFail") int numFail,
        @JsonProperty("mutationScore") double mutationScore,
        @JsonProperty("tests") List<TestCase> tests
) {
}
