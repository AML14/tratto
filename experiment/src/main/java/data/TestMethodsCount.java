package data;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This record represents the JSON output of JDoctor.
 *
 * @param className the name of the class
 * @param projectName the name of the project
 * @param projectBugID the bug ID of the project
 * @param methodCount the number of test methods
 */
public record TestMethodsCount(
        @JsonProperty("className") String className,
        @JsonProperty("projectName") String projectName,
        @JsonProperty("projectBugID") String projectBugID,
        @JsonProperty("methodCount") int methodCount
) {}
