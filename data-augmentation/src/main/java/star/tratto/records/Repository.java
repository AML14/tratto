package star.tratto.records;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record Repository(
        @JsonProperty("projectName") String projectName,
        @JsonProperty("githubLink") String githubLink,
        @JsonProperty("commit") String commit,
        @JsonProperty("srcPathList") List<String> srcPathList,
        @JsonProperty("rootPathList") List<String> rootPathList
) {}
