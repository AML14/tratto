package star.tratto.data.records;

import java.util.List;

/**
 * This record stores the basic information of a Java method. Includes the
 * method name, the package of the declaring class, the name of the declaring
 * class, and the method signature. The method signature follows the format:
 *  "[modifiers] [type parameters] [return type] [name]([parameters]) throws [exceptions]"
 *
 * @param methodName the name of the method
 * @param packageName the name of the package of the declaring class
 * @param className the name of the declaring class
 * @param methodSignature the method signature (as defined above)
 */
public record MethodTokens(String methodName, String packageName, String className, String methodSignature) {
    // Non-canonical constructor using List to read from file
    public MethodTokens(List<String> tokens) {
        this(tokens.get(0), tokens.get(1), tokens.get(2), tokens.get(3));
    }

    // Converts record to List to write to file
    public List<String> toList() {
        return List.of(this.methodName, this.packageName, this.className, this.methodSignature);
    }
}
