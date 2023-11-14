package star.tratto.records;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record RepositoryClass(
        @JsonProperty("name") String name,
        @JsonProperty("jDoctorConditions") List<JDoctorConditionDocument> jDoctorConditionDocuments
) { }
