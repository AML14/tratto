package star.tratto.data.records;

import java.util.List;

/**
 * A Java method. The method signature follows the format:
 *     "[modifiers] [type parameters] [return type] [name]([parameters]) throws [exceptions]"
 *
 * @param methodName the name of the method
 * @param packageName the name of the package of the declaring class
 * @param className the simple name of the declaring class
 * @param methodSignature the method signature (as defined above)
 */
public record MethodTokens(String methodName, String packageName, String className, String methodSignature) {
    /** Non-canonical constructor using List to read JSON. */
    public MethodTokens(List<String> tokens) {
        this(tokens.get(0), tokens.get(1), tokens.get(2), tokens.get(3));
    }

    /** Converts record to List for JSON compatibility. */
    public List<String> toList() {
        return List.of(this.methodName, this.packageName, this.className, this.methodSignature);
    }
}
