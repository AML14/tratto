package star.tratto.data.records;

import java.util.List;

/**
 * A method argument.
 *
 * @param argumentName the argument variable name
 * @param packageName the package of the corresponding type
 * @param typeName the simple name of the corresponding type
 */
public record MethodArgumentTokens(String argumentName, String packageName, String typeName) {
    /** Constructor used when reading JSON. */
    public MethodArgumentTokens(List<String> tokens) {
        this(tokens.get(0), tokens.get(1), tokens.get(2));
    }

    /** Converts record to List for JSON compatibility. */
    public List<String> toList() {
        return List.of(this.argumentName, this.packageName, this.typeName);
    }
}
