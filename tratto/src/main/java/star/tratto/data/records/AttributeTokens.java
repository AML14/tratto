package star.tratto.data.records;

import java.util.List;

/**
 * This record stores the basic information of a Java attribute. Includes the
 * attribute name, the package of the declaring class, the name of the
 * declaring class, and the attribute declaration. The attribute declaration
 * follows the format:
 *  "[modifiers] [type] [name][ = initial value];"
 */
public record AttributeTokens(
        /* the name of the attribute */
        String attributeName,
        /* the name of the package of the declaring class */
        String packageName,
        /* the name of the declaring class */
        String className,
        /* the attribute declaration (as defined in the record Javadoc) */
        String attributeDeclaration
) {
    // Non-canonical constructor using List to read from file
    public AttributeTokens(List<String> tokens) {
        this(tokens.get(0), tokens.get(1), tokens.get(2), tokens.get(3));
    }

    // Converts record to List to write to file
    public List<String> toList() {
        return List.of(this.attributeName, this.packageName, this.className, this.attributeDeclaration);
    }
}
