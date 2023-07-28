package star.tratto.data.records;

import java.util.List;

/**
 * This record stores basic information of a Javadoc value. Includes the
 * value (value) and type of value (type). For example,
 *  "1"    =>    ("1", "int")
 *
 * @param value the Javadoc value
 * @param type the type of the Javadoc value
 */
public record JavadocValueTokens(String value, String type) {
    // Non-canonical constructor using List to read from file
    public JavadocValueTokens(List<String> tokens) {
        this(tokens.get(0), tokens.get(1));
    }

    // Converts record to List to write to file
    public List<String> toList() {
        return List.of(this.value, this.type);
    }
}
