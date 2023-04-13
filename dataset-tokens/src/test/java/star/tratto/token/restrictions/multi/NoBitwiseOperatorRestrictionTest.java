package star.tratto.token.restrictions.multi;

import star.tratto.token.TokenSuggester;

public class NoBitwiseOperatorRestrictionTest {
    /**
     * Used for integration tests in {@link TokenSuggester}.
     */
    public static NoBitwiseOperatorRestriction getNoBitwiseOperatorRestriction() {
        return NoBitwiseOperatorRestriction.getInstance();
    }
}
