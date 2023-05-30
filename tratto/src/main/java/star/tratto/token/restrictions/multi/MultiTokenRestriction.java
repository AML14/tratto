package star.tratto.token.restrictions.multi;

import star.tratto.dataset.oracles.OracleDatapoint;
import star.tratto.token.Tokens;
import star.tratto.token.restrictions.ContextRestriction;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class defines a ContextRestriction that restricts multiple tokens.
 * <br>
 * The MultiTokenRestriction class has the following attributes:
 * <ul>
 *     <li>restrictedTokens: The tokens that may be restricted by this context restriction.</li>
 * </ul>
 */
public abstract class MultiTokenRestriction implements ContextRestriction {

    protected List<String> restrictedTokens;
    protected List<String> nonRestrictedTokens;

    /**
     * This method assumes that, according to the grammar, nextLegalTokens are legal after partialExpressionTokens.
     * @return true if the restricted tokens are forbidden (i.e., the restriction is enabled), false otherwise
     */
    public abstract Boolean isEnabled(List<String> nextLegalTokens, List<String> partialExpressionTokens, OracleDatapoint oracleDatapoint);

    /**
     * This method must always be called at the beginning of {@link #isEnabled}. In a nutshell, for a
     * MultiTokenRestriction to be applicable, the next legal tokens must contain both restricted tokens (because
     * they would need to be forbidden) and non-restricted tokens (because otherwise it would not make sense to
     * evaluate this restriction).
     * If it returns false, then {@link #isEnabled} should return false.
     */
    protected Boolean appliesTo(List<String> tokens) {
        return tokens.stream().anyMatch(token -> restrictedTokens.contains(token)) && tokens.stream().anyMatch(token -> nonRestrictedTokens.contains(token));
    }

    /**
     * restrictedTokens must be set before calling this method.
     */
    protected void setNonRestrictedTokens() {
        this.nonRestrictedTokens = Tokens.TOKENS.stream().filter(token -> !this.restrictedTokens.contains(token)).collect(Collectors.toList());
    }

    public List<String> getRestrictedTokens() {
        return restrictedTokens;
    }

    public void setRestrictedTokens(List<String> restrictedTokens) {
        this.restrictedTokens = restrictedTokens;
    }

    public List<String> getNonRestrictedTokens() {
        return nonRestrictedTokens;
    }

    public void setNonRestrictedTokens(List<String> nonRestrictedTokens) {
        this.nonRestrictedTokens = nonRestrictedTokens;
    }
}
