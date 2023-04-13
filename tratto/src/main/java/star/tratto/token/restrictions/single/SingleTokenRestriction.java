package star.tratto.token.restrictions.single;

import star.tratto.dataset.oracles.OracleDatapoint;
import star.tratto.oracle.OracleType;
import star.tratto.token.restrictions.ContextRestriction;

import java.util.List;

/**
 * This class defines a ContextRestriction that restricts only a single token.
 * <br>
 * The SingleTokenRestriction class has the following attributes:
 * <ul>
 *     <li>oracleType: The oracle type to which this restriction applies, i.e., PRE, NORMAL_POST or EXCEPT_POST;
 *     null if this restriction applies to all types of oracles.</li>
 *     <li>restrictedToken: The token that may be restricted by this context restriction.</li>
 *     <li>enablerTokens: The tokens that enable this context restriction, i.e., when they appear in the oracle, the
 *     restricted token is forbidden.</li>
 *     <li>disablerTokens: The tokens that disable this context restriction, i.e., when they appear in the oracle, the
 *     restricted token can be used again.</li>
 *     <li>enabledByDefault: Whether this context restriction is enabled by default. If it's enabled by default, then
 *     the restricted token is forbidden even when no token has been generated for the oracle yet.</li>
 * </ul>
 *
 * Conventions for the enablerTokens and disablerTokens attributes:
 * <ul>
 *     <li>If null, the restriction cannot be enabled/disabled.</li>
 *     <li>If empty list, the restriction is enabled/disabled by any token.</li>
 * </ul>
 *
 * General conventions:
 * <ul>
 *     <li>It is forbidden that both the enablerTokens and disablerTokens are empty lists.</li>
 * </ul>
 */
public abstract class SingleTokenRestriction implements ContextRestriction {

    protected OracleType oracleType;
    protected String restrictedToken;
    protected List<String> enablerTokens;
    protected List<String> disablerTokens;
    protected Boolean enabledByDefault;

    /**
     * IMPORTANT: This method assumes that nextLegalToken is legal after partialExpressionTokens according to the
     * grammar and after having applied all {@link star.tratto.token.restrictions.multi.MultiTokenRestriction}s. In
     * other words,
     * @return true if the restricted token is forbidden (i.e., the restriction is enabled), false otherwise.
     */
    public abstract Boolean isEnabled(String nextLegalToken, List<String> partialExpressionTokens, OracleDatapoint oracleDatapoint);

    /**
     * This method must always be called at the beginning of {@link #isEnabled}.
     * If it returns false, then {@link #isEnabled} should return false.
     */
    protected Boolean isRestrictedToken(String token) {
        return token.equals(this.restrictedToken);
    }

    public OracleType getOracleType() {
        return oracleType;
    }

    public void setOracleType(OracleType oracleType) {
        this.oracleType = oracleType;
    }

    public String getRestrictedToken() {
        return restrictedToken;
    }

    public void setRestrictedToken(String restrictedToken) {
        this.restrictedToken = restrictedToken;
    }

    public List<String> getEnablerTokens() {
        return enablerTokens;
    }

    public void setEnablerTokens(List<String> enablerTokens) {
        this.enablerTokens = enablerTokens;
    }

    public List<String> getDisablerTokens() {
        return disablerTokens;
    }

    public void setDisablerTokens(List<String> disablerTokens) {
        this.disablerTokens = disablerTokens;
    }

    public Boolean getEnabledByDefault() {
        return enabledByDefault;
    }

    public void setEnabledByDefault(Boolean enabledByDefault) {
        this.enabledByDefault = enabledByDefault;
    }
}
