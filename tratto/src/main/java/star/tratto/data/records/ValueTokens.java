package star.tratto.data.records;

import java.util.List;

/**
 * This record stores basic information of a variable value. Includes the
 * value (value) and the type of the value (type). For example,
 *  "1"    =>    ("1", "int")
 * Used for XText grammar.
 *
 * @param value the Javadoc value
 * @param type the type of the Javadoc value
 */
public record ValueTokens(String value, String type) {
    // Non-canonical constructor using List to read from file
    public ValueTokens(List<String> tokens) {
        this(tokens.get(0), tokens.get(1));
    }

    // Converts record to List to write to file
    public List<String> toList() {
        return List.of(this.value, this.type);
    }
}
