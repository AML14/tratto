package data;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * This record represents a generated oracle and its corresponding contextual
 * information.
 *
 * @param className the fully qualified name of the declaring class of
 *                  {@code methodSignature}
 * @param methodSignature the method under test
 * @param oracleType the type of oracle
 * @param oracle a generated oracle
 * @param exception the type of exception thrown by an exceptional oracle.
 *                  This field is an empty String if the oracle is not
 *                  exceptional.
 * @param testName the name of the test corresponding to a non-axiomatic
 *                 oracle. This field is an empty String if the oracle is
 *                 axiomatic.
 */
public record OracleOutput(
        String className,
        String methodSignature,
        OracleType oracleType,
        String oracle,
        String exception,
        String testName
) {
    /**
     * Checks if an OracleOutput corresponds to an exceptional oracle.
     *
     * @return true iff the record represents an exceptional oracle. This
     * corresponds to an empty string in the {@link OracleOutput#exception()}
     * value.
     */
    @JsonIgnore
    public boolean isExceptional() {
        return !this.exception.equals("");
    }
}
