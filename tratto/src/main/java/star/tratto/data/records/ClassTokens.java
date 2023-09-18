package star.tratto.data.records;

import java.util.List;

/**
 * A Java class. Includes the class name (className) and package name
 * (packageName).
 */
public record ClassTokens(
        /* The name of a Java class. */
        String className,
        /* The package of the Java class. */
        String packageName
) {
    /** Non-canonical constructor using List to read JSON. */
    public ClassTokens(List<String> tokens) {
        this(tokens.get(0), tokens.get(1));
    }

    /** Converts record to List for JSON compatibility. */
    public List<String> toList() {
        return List.of(this.className, this.packageName);
    }
}
