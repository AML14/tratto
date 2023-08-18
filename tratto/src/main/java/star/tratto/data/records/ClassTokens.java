package star.tratto.data.records;

import java.util.List;

/**
 * This record stores basic information of a Java class. Includes the class
 * name (className) and package name (packageName).
 */
public record ClassTokens(
        /* the name of a Java class */
        String className,
        /* the corresponding package of the Java class */
        String packageName
) {
    // Non-canonical constructor using List to read from file
    public ClassTokens(List<String> tokens) {
        this(tokens.get(0), tokens.get(1));
    }

    // Converts record to List to write to file
    public List<String> toList() {
        return List.of(this.className, this.packageName);
    }
}
