package star.tratto.data.records;

import java.util.List;

public record EligibleToken(
        String token,
        String tokenClass,
        List<String> tokenInfo
) {
    /** Constructor used when reading JSON. */
    public EligibleToken(List<Object> tokens) {
        this((String)tokens.get(0), (String)tokens.get(1), (List<String>)tokens.get(2));
    }

    /** Converts record to List for JSON compatibility. */
    public List<Object> toList() {
        return List.of(this.token, this.tokenClass, this.tokenInfo);
    }
}
