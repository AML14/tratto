package data;

/**
 * A record for pre-processed context of the system under test. This context
 * is then passed to TOGA as input.
 *
 * @param className the declaring class of {@code methodSignature}
 * @param methodSignature the method under test
 * @param prefix a test prefix
 * @param testName the name of the test
 */
public record TogaInput(
        String className,
        String methodSignature,
        String prefix,
        String testName
) {}
