package star.tratto.token.restrictions.multi;

import star.tratto.token.TokenSuggester;

public class ForbidEndExpressionRestrictionTest {
    /**
     * Used for integration tests in {@link TokenSuggester}.
     */
    public static ForbidEndExpressionRestriction getForbidEndExpressionRestriction() {
        return ForbidEndExpressionRestriction.getInstance();
    }
}
