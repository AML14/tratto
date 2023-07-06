package star.tratto.token.restrictions.single;

import star.tratto.data.OracleDatapoint;
import star.tratto.token.Tokens;

import java.util.List;

import static star.tratto.util.StringUtils.getCorrespondingClosingParenthesisIndex;
import static star.tratto.util.StringUtils.getIndexesOfTokensInOracleTokens;

/**
 * "jdVar" is only allowed within the argument of a method performed on a stream (e.g., anyMatch),
 * otherwise it is forbidden.
 */
public class NoJdVarRestriction extends SingleTokenRestriction {

    private static NoJdVarRestriction instance;

    private NoJdVarRestriction() {
        this.restrictedToken = "jdVar";
        this.disablerTokens = Tokens.RULES_TOKENS.get("MatchMethod");
        this.enabledByDefault = true;
    }

    static NoJdVarRestriction getInstance() {
        if (instance == null) {
            instance = new NoJdVarRestriction();
        }
        return instance;
    }

    @Override
    public Boolean isEnabled(String nextLegalToken, List<String> partialExpressionTokens, OracleDatapoint oracleDatapoint) {
        if (!isRestrictedToken(nextLegalToken)) {
            return false;
        }

        List<Integer> disablerTokensIndexes = getIndexesOfTokensInOracleTokens(partialExpressionTokens, disablerTokens);

        // Since this ContextRestriction is enabled by default, the token "jdVar" MAY be used only if a disablerToken
        // (i.e., a MatchMethod) has been found
        if (disablerTokensIndexes.isEmpty()) {
            return true;
        } else { // "jdVar" will be legal if the MatchMethod closing parenthesis is not found
            int lastDisablerTokenIndex = disablerTokensIndexes.get(disablerTokensIndexes.size() - 1); // MatchMethod index
            // "jdVar" is legal, at least, up to lastDisablerTokenIndex + 6 tokens. Example: "anyMatch(jdVar -> jdVar) &&"
            if (partialExpressionTokens.size() <= lastDisablerTokenIndex + 6) {
                return false;
            } else { // If the oracle is longer, we need to look for the closing parenthesis
                int matchMethodOpeningParenthesisIndex = lastDisablerTokenIndex + 1; // There MUST be an opening parenthesis right after the MatchMethod
                Integer matchMethodClosingParenthesisIndex = getCorrespondingClosingParenthesisIndex(partialExpressionTokens, matchMethodOpeningParenthesisIndex);
                return matchMethodClosingParenthesisIndex != null; // If closing parenthesis, "jdVar" is illegal so restriction is enabled
            }
        }
    }
}
