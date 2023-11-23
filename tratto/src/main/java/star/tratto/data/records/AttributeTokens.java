package star.tratto.data.records;

import java.util.List;

/**
 * This project uses the term "attribute" to refer to fields in Java classes.
 * This is done for compatibility with the XText grammar. This record defines
 * various attribute information. The attribute declaration follows the format:
 *     "[modifiers] [type] [name][ = initial value];"
 */
public record AttributeTokens(
        /* The name of the attribute. */
        String attributeName,
        /* The name of the package of the declaring class. */
        String packageName,
        /* The name of the declaring class. */
        String className,
        /* The attribute declaration. */
        String attributeDeclaration
) {
    /** Constructor used when reading JSON. */
    public AttributeTokens(List<String> tokens) {
        this(tokens.get(0), tokens.get(1), tokens.get(2), tokens.get(3));
    }

    /** Converts record to List for JSON compatibility. */
    public List<String> toList() {
        return List.of(this.attributeName, this.packageName, this.className, this.attributeDeclaration);
    }
}
