package star.tratto.token.restrictions.multi;

import star.tratto.token.TokenSuggester;

public class NoNonEqIneqOperatorRestrictionTest {
    /**
     * Used for integration tests in {@link TokenSuggester}.
     */
    public static NoNonEqIneqOperatorRestriction getNoNonEqIneqOperatorRestriction() {
        return NoNonEqIneqOperatorRestriction.getInstance();
    }
}
