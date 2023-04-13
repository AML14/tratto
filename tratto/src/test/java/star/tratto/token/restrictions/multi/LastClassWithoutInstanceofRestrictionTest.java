package star.tratto.token.restrictions.multi;

import star.tratto.token.TokenSuggester;

public class LastClassWithoutInstanceofRestrictionTest {
    /**
     * Used for integration tests in {@link TokenSuggester}.
     */
    public static LastClassWithoutInstanceofRestriction getLastClassWithoutInstanceofRestriction() {
        return LastClassWithoutInstanceofRestriction.getInstance();
    }
}
