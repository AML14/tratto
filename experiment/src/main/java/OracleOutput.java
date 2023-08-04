/**
 * This record represents a generated oracle and contextual information.
 *
 * @param className the binary name of the declaring class under test
 * @param methodSignature the method under test
 * @param oracle a generated oracle
 */
public record OracleOutput(
        String className,
        String methodSignature,
        String oracle
) {}
