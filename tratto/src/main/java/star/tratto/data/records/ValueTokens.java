package star.tratto.data.records;

import java.util.List;

/**
 * This record stores basic information of a simple value. Includes the value
 * (value) and the type of the value (type). For example,
 *     "1"    =>    ("1", "int")
 * Used for XText grammar. Only considers basic Javadoc values, such as
 * numerical values or strings. Therefore, the XText grammar does not use a
 * package name when processing this record.
 */
public record ValueTokens(
        /* The value. */
        String value,
        /* The type of the value. */
        String type
) {
    /** Constructor used when reading JSON. */
    public ValueTokens(List<String> tokens) {
        this(tokens.get(0), tokens.get(1));
    }

    /** Converts record to List for JSON compatibility. */
    public List<String> toList() {
        return List.of(this.value, this.type);
    }
}
