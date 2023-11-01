package star.tratto.records;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record Repository(
        @JsonProperty("name") String name,
        @JsonProperty("githubLink") String githubLink
) {}
