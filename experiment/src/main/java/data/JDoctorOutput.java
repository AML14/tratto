package data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * This record represents the JSON output of JDoctor.
 *
 * @param signature the signature of the method under test
 * @param name the name of the method under test
 * @param containingClass the fully qualified name of the declaring
 *                        class {@link Type}
 * @param targetClass the fully qualified declaring class name
 * @param isVarArgs true iff the method is a varargs
 * @param returnType the method return type
 * @param parameters the method parameters
 * @param paramTags the method param tags
 * @param returnTag the method return tag
 * @param throwsTags the method throws tags
 * @see Type
 * @see Parameter
 * @see ParamTag
 * @see ReturnTag
 * @see ThrowsTag
 */
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
            @JsonProperty("parameter") Parameter parameter,
            @JsonProperty("comment") String comment,
            @JsonProperty("kind") String kind,
            @JsonProperty("condition") String condition

    ) {}

    public record ThrowsTag(
            @JsonProperty("exceptionType") Type exceptionType,
            @JsonProperty("codeTags") List<String> codeTags,
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
