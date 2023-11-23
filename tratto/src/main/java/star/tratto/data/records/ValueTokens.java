package star.tratto.data.records;

import java.util.List;

/**
 * This record stores basic information about a value. Includes the value and
 * the type of the value. For example,
 *     "1"    =>    ("1", "int")
 * Used for XText grammar.
 */
public record ValueTokens(
        /* The value. */
        String value,
        /*
         * The type of the value (only numerical or string). Does not include
         * package name.
         */
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
