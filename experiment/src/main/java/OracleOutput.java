/**
 * This record represents a generated oracle and contextual information.
 *
 * @param className the fully qualified name of the declaring class under test
 * @param methodSignature the method under test. The method signature is
 *                        follows the format:
 *                            {@code [methodName]([paramType] [paramName], ...)}
 * @param oracleType the type of oracle
 * @param prefix a test prefix corresponding to the generated oracle. An empty
 *               string if the oracle is axiomatic.
 * @param oracle a generated oracle
 * @param exception the exception class thrown by an exceptional oracle. An
 *                  empty string if the oracle is not exceptional.
 */
public record OracleOutput(
        String className,
        String methodSignature,
        OracleType oracleType,
        String prefix,
        String oracle,
        String exception
) {}
