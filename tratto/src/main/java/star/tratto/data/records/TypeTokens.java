package star.tratto.data.records;

import java.util.List;

/**
 * A Java type. Includes the simple name and the package name (empty
 * string if primitive type).
 */
public record TypeTokens(
        /* The simple name of a Java type. */
        String typeName,
        /* The package of the Java class or empty string if primitive type. */
        String packageName
) {
    /** Non-canonical constructor using List to read JSON. */
    public TypeTokens(List<String> tokens) {
        this(tokens.get(0), tokens.get(1));
    }

    /** Converts record to List for JSON compatibility. */
    public List<String> toList() {
        return List.of(this.typeName, this.packageName);
    }
}
