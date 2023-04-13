package star.tratto.token.restrictions.multi;

import org.junit.jupiter.api.Test;
import star.tratto.token.TokenSuggester;
import star.tratto.token.Tokens;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class LastMethodNameRestrictionTest {

    @Test
    public void getRestrictedTokensTest() {
        LastMethodNameRestriction restriction = LastMethodNameRestriction.getInstance();
        assertEquals(Tokens.TOKENS.size()-1, restriction.getRestrictedTokens().size());
        assertFalse(restriction.getRestrictedTokens().contains("("));
    }

    /**
     * Used for integration tests in {@link TokenSuggester}.
     */
    public static LastMethodNameRestriction getLastMethodNameRestriction() {
        return LastMethodNameRestriction.getInstance();
    }
}
