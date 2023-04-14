package star.tratto.token.restrictions.multi;

import star.tratto.token.TokenSuggester;

public class LiteralValuesRestrictionTest {
    /**
     * Used for integration tests in {@link TokenSuggester}.
     */
    public static LiteralValuesRestriction getLiteralValuesRestriction() {
        return LiteralValuesRestriction.getInstance();
    }
}
