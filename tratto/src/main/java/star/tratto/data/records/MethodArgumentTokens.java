package star.tratto.data.records;

import java.util.List;

/**
 * This record stores basic information for a method argument. Includes the
 * name of the argument, the package name of the corresponding type, and the
 * name of the corresponding type.
 *
 * @param argumentName the argument variable name
 * @param packageName the package of the corresponding type
 * @param typeName the name of the corresponding type
 */
public record MethodArgumentTokens(String argumentName, String packageName, String typeName) {


    // Non-canonical constructor using List to read from file
    public MethodArgumentTokens(List<String> tokens) {
        this(tokens.get(0), tokens.get(1), tokens.get(2));
    }

    // Converts record to List to write to file
    public List<String> toList() {
        return List.of(this.argumentName, this.packageName, this.typeName);
    }
}
