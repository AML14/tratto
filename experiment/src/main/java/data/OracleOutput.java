package data;

/**
 * This record represents a generated oracle and contextual information.
 *
 * @param className the fully qualified name of the declaring class under test
 * @param methodSignature the method under test
 * @param oracleType the type of oracle
 * @param prefix the test prefix corresponding to a non-axiomatic oracle
 * @param oracle a generated oracle
 * @param exception the exception class thrown by an exceptional oracle. An
 *                  empty string if the oracle is not exceptional.
 * @param testName the name of the test (prefix) from which the assertion has
 *                 been produced. An empty string if the oracle is axiomatic.
 */
public record OracleOutput(
        String className,
        String methodSignature,
        OracleType oracleType,
        String prefix,
        String oracle,
        String exception,
        String testName
) {}
