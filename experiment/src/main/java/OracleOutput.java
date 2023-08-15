/**
 * This record represents a generated oracle and contextual information.
 *
 * @param className the binary name of the declaring class under test
 * @param methodSignature the method under test
 * @param isExceptional a boolean which indicates if the oracle represents
 *                      an exceptional behavior
 * @param prefix a test prefix corresponding to the generated oracle. An empty
 *               string if the oracle is axiomatic.
 * @param oracle a generated oracle
 */
public record OracleOutput(
        String className,
        String methodSignature,
        boolean isExceptional,
        String prefix,
        String oracle
) {}
