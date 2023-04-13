package star.tratto.token.restrictions.multi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import star.tratto.dataset.OracleDatapoint;
import star.tratto.oraclegrammar.custom.Parser;
import star.tratto.token.TokenSuggester;
import star.tratto.token.Tokens;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static star.tratto.TestUtilities.readOracleDatapointsFromOraclesDataset;
import static star.tratto.oraclegrammar.custom.Splitter.split;

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
