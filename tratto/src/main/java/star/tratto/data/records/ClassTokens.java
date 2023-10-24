package star.tratto.data.records;

import java.util.List;

/**
 * A Java class. Includes the class name and package name.
 */
public record ClassTokens(
        /* The name of a Java class. */
        String className,
        /* The package of the Java class. */
        String packageName
) {
    /** Constructor used when reading JSON. */
    public ClassTokens(List<String> tokens) {
        this(tokens.get(0), tokens.get(1));
    }

    /** Converts record to List for JSON compatibility. */
    public List<String> toList() {
        return List.of(this.className, this.packageName);
    }
}
