package star.tratto.data.records;

import java.util.List;

/**
 * A Java attribute, defined as a field of a Java class. Includes the
 * attribute name, the package of the declaring class, the name of the
 * declaring class, and the attribute declaration. The attribute declaration
 * follows the format:
 *     "[modifiers] [type] [name][ = initial value];"
 */
public record AttributeTokens(
        /* The name of the attribute. */
        String attributeName,
        /* The name of the package of the declaring class. */
        String packageName,
        /* The name of the declaring class. */
        String className,
        /* The attribute declaration (as defined in the record Javadoc). */
        String attributeDeclaration
) {
    /** Non-canonical constructor using List to read JSON. */
    public AttributeTokens(List<String> tokens) {
        this(tokens.get(0), tokens.get(1), tokens.get(2), tokens.get(3));
    }

    /** Converts record to List for JSON compatibility. */
    public List<String> toList() {
        return List.of(this.attributeName, this.packageName, this.className, this.attributeDeclaration);
    }
}
