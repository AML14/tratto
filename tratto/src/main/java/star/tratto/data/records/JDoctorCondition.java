package star.tratto.data.records;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * A JDoctor condition for a method.
 * Note: Both the overall data structure (including all precondition,
 * post-condition, and exceptional oracles) and the oracle itself are
 * referred to as a "condition". This is a consequence of the JDoctor output
 * used to generate the Oracles Dataset and should NOT be changed.
 */
public record JDoctorCondition(
        /* The identifying information of the method under test (e.g. declaring class, name, etc.) */
        @JsonProperty("operation") Operation operation,
        /* The names used to represent parameters, the return type, and the declaring class. */
        @JsonProperty("identifiers") Identifiers identifiers,
        /* Exceptional post-conditions. */
        @JsonProperty("throws") List<ThrowsCondition> throwsConditions,
        /* Normal post-conditions. */
        @JsonProperty("post") List<PostCondition> postConditions,
        /* Pre-conditions. */
        @JsonProperty("pre") List<PreCondition> preConditions
) {
    /**
     * Represents the method under test.
     */
    public record Operation(
            /*
             * The fully qualified name for the declaring class of the method
             * which the JDoctor condition refers to.
             */
            @JsonProperty("classname") String className,
            /*
             * The fully qualified name of the method which the JDoctor
             * condition refers to.
             */
            @JsonProperty("name") String methodName,
            /*
             * The list of parameter fully qualified names for the method
             * which the JDoctor condition refers to.
             */
            @JsonProperty("parameterTypes") List<String> parameterTypes
    ) {}

    /**
     * The names of variables in the JDoctor condition.
     */
    public record Identifiers(
            /*
             * The parameter names for the method.
             */
            @JsonProperty("parameters") List<String> parameters,
            /*
             * The name of the receiver, within the oracle generated by
             * JDoctor (always "receiverObjectID").
             */
            @JsonProperty("receiverName") String receiverName,
            /*
             * The method return type, within the oracle generated by JDoctor
             * (always "methodResultID").
             */
            @JsonProperty("returnName") String returnName
    ) {}

    /**
     * Defines an exceptional JDoctor condition.
     */
    public record ThrowsCondition(
            /* The exception captured by JDoctor. */
            @JsonProperty("exception") String exception,
            /*
             * The @throws tag description of the exception in the
             * corresponding Javadoc comment of the method to which the
             * JDoctor condition refers.
             */
            @JsonProperty("description") String description,
            /*
             * The guard of the condition. It contains the oracle generated
             * from the description of the exceptional condition, in the
             * Javadoc comment, and the textual representation of the oracle,
             * i.e. the exact substring of the description that indicates the
             * condition for which the exception is thrown.
             */
            @JsonProperty("guard") Guard guard
    ) {}

    /**
     * A JDoctor post-condition.
     */
    public record PostCondition(
            /*
             * The property of the JDoctor post-condition. It contains the
             * oracle and the textual description of the JDoctor
             * post-condition.
             */
            @JsonProperty("property") Property property,
            /* The textual description of the JDoctor post-condition. */
            @JsonProperty("description") String description,
            /*
             * The guard of the JDoctor post-condition. It contains the
             * condition for which the oracle of the post-condition must be
             * verified. If the guard condition is true, the corresponding
             * oracle must be true as well. Moreover, it contains the textual
             * description of the guard condition.
             */
            @JsonProperty("guard") Guard guard
    ) {}

    /**
     * A JDoctor pre-condition.
     */
    public record PreCondition(
            /* The textual description of the JDoctor pre-condition. */
            @JsonProperty("description") String description,
            /*
             * The guard of the JDoctor pre-condition. It contains the
             * condition for which the JDoctor pre-condition is verified.
             * Moreover, it contains the textual description of the guard.
             */
            @JsonProperty("guard") Guard guard
    ) {}

    /**
     * A guard of a JDoctor condition.
     */
    public record Guard(
            /*
             * The condition for which a JDoctor pre-condition must be true,
             * or a JDoctor post-condition must be true, or a JDoctor
             * exceptional condition must be thrown
             */
            @JsonProperty("condition") String condition,
            /* The textual description of the guard condition. */
            @JsonProperty("description") String description
    ) {}

    /**
     * A property of a JDoctor post-condition.
     */
    public record Property(
            /* The oracle that must be true if the corresponding Guard condition is true. */
            @JsonProperty("condition") String condition,
            /* The textual description of the JDoctor post-condition oracle. */
            @JsonProperty("description") String description
    ) {}
}
