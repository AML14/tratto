package star.tratto.token.restrictions.multi;

import star.tratto.token.TokenSuggester;

public class NoArithmeticalOperatorRestrictionTest {
    /**
     * Used for integration tests in {@link TokenSuggester}.
     */
    public static NoArithmeticalOperatorRestriction getNoArithmeticalOperatorRestriction() {
        return NoArithmeticalOperatorRestriction.getInstance();
    }
}
