package star.tratto.data.records;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * A full-specification JDoctor condition for a method, containing all
 * pre-, post-, and exceptional conditions.
 * Note: Both the overall data structure (including all precondition,
 * post-condition, and exceptional oracles) and the oracle itself (a string)
 * are referred to as a "condition". This is a consequence of the JDoctor
 * output used to generate the Oracles Dataset and should NOT be changed. This
 * class refers to the overall data structure.
 */
public record JDoctorCondition(
        /* The method under test. */
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
     * Represents a method.
     */
    public record Operation(
            /*
             * The fully qualified name for the declaring class of the method.
             */
            @JsonProperty("classname") String className,
            /*
             * The method name.
             */
            @JsonProperty("name") String methodName,
            /*
             * The Class.getName() names of all parameters in the method. For
             * example,
             *     "toArray(Object)"    &rarr;    ["[Ljava.lang.Object;"]
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
             * The method return value, within the oracle generated by JDoctor
             * (always "methodResultID").
             */
            @JsonProperty("returnName") String returnName
    ) {}

    /**
     * An exceptional JDoctor post-condition.
     */
    public record ThrowsCondition(
            /* The fully qualified name of the exception type. */
            @JsonProperty("exception") String exception,
            /* The @throws tag corresponding to the oracle (includes type). */
            @JsonProperty("description") String description,
            /* The guard of the condition. */
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
             * The guard of the JDoctor post-condition.
             */
            @JsonProperty("guard") Guard guard
    ) {}

    /**
     * A JDoctor pre-condition.
     */
    public record PreCondition(
            /*
             * The textual description of the JDoctor pre-condition
             * (without tag).
             */
            @JsonProperty("description") String description,
            /*
             * The guard of the JDoctor pre-condition.
             */
            @JsonProperty("guard") Guard guard
    ) {}

    /**
     * A guard of a JDoctor condition.
     */
    public record Guard(
            /* The oracle that must be true. */
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
