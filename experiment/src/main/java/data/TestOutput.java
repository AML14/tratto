package data;

/**
 * This record represents a generated test case and contextual information.
 *
 * @param className the binary name of the declaring class under test
 * @param methodSignature the method under test
 * @param isPositive a boolean value indicating if the test passes using the
 *                   current implementation
 * @param test the generated test case (prefix and oracle)
 */
public record TestOutput(
        String className,
        String methodSignature,
        boolean isPositive,
        String test
) {}
