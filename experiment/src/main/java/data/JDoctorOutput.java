package data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record JDoctorOutput(
        @JsonProperty("signature") String signature,
        @JsonProperty("name") String name,
        @JsonProperty("containingClass") Type containingClass,
        @JsonProperty("targetClass") String targetClass,
        @JsonProperty("isVarArgs") boolean isVarArgs,
        @JsonProperty("returnType") Type returnType,
        @JsonProperty("parameters") List<Parameter> parameters,
        @JsonProperty("paramTags") List<ParamTag> paramTags,
        @JsonProperty("returnTag") ReturnTag returnTag,
        @JsonProperty("throwsTags") List<ThrowsTag> throwsTags
) {
    public record ParamTag(
            @JsonProperty("comment") String comment,
            @JsonProperty("kind") String kind,
            @JsonProperty("condition") String condition

    ) {}

    public record ThrowsTag(
            @JsonProperty("comment") String comment,
            @JsonProperty("kind") String kind,
            @JsonProperty("condition") String condition
    ) {}

    public record ReturnTag(
            @JsonProperty("comment") String comment,
            @JsonProperty("kind") String kind,
            @JsonProperty("condition") String condition
    ) {}

    public record Parameter(
            @JsonProperty("type") Type type,
            @JsonProperty("name") String name,
            @JsonProperty("nullable") boolean isNullable
    ) {}

    public record Type(
            @JsonProperty("qualifiedName") String fullyQualifiedName,
            @JsonProperty("name") String name,
            @JsonProperty("isArray") boolean isArray
    ) {}
}
