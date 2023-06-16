package star.tratto.token.restrictions.single;

import star.tratto.data.OracleDatapoint;
import star.tratto.oracle.OracleType;

import java.util.Collections;
import java.util.List;

import static star.tratto.util.StringUtils.compactExpression;
import static star.tratto.util.StringUtils.getIndexesOfTokensInOracleTokens;

/**
 * Standard context restrictions can be disabled ONLY once during the oracle generation. Once they are
 * disabled, they cannot be enabled again. This may change in the future if new context restrictions
 * need to be added. There are several SingleTokenRestrictions that are not standard because they need
 * to implement some domain-specific logic regarding how to be enabled/disabled and whether they are
 * enabled by default or not, for example, {@link NoJdVarRestriction}, {@link NoThisRestriction} and
 * {@link PreYesArgumentOrThisRestriction}.
 */
public class StandardSingleTokenRestriction extends SingleTokenRestriction {

    StandardSingleTokenRestriction(OracleType oracleType, String restrictedToken, List<String> enablerTokens, List<String> disablerTokens, Boolean enabledByDefault) {
        this.oracleType = oracleType;
        this.restrictedToken = restrictedToken;
        this.enablerTokens = enablerTokens;
        this.disablerTokens = disablerTokens;
        this.enabledByDefault = enabledByDefault;
    }

    @Override
    public Boolean isEnabled(String nextLegalToken, List<String> partialExpressionTokens, OracleDatapoint oracleDatapoint) {
        if (!isRestrictedToken(nextLegalToken) || oracleType != oracleDatapoint.getOracleType()) { // The token may be the same, but not applicable to this oracleType
            return false;
        }

        List<Integer> enablerTokensIndexes = getIndexesOfTokensInOracleTokens(partialExpressionTokens, enablerTokens); // Positions where enabler tokens are
        List<Integer> disablerTokensIndexes = getIndexesOfTokensInOracleTokens(partialExpressionTokens, disablerTokens); // Positions where disabler tokens are

        // If any token can enable (/disable) the restriction (i.e., it's an empty list), then we need to remove the indexes where there's a disabler (/enabler) token
        if (enablerTokens != null && enablerTokens.isEmpty()) {
            // The following condition is to handle the case where the restriction is disabled by default, any token enables, and only the first token disables
            if (!(!enabledByDefault && disablerTokensIndexes.size() == 1 && disablerTokensIndexes.get(0) == 0)) {
                enablerTokensIndexes.removeAll(disablerTokensIndexes);
            }
        }
        if (disablerTokens != null && disablerTokens.isEmpty()) {
            disablerTokensIndexes.removeAll(enablerTokensIndexes);
        }

        // We need to know when a restriction has been disabled. To this end, we need to compare the indexes of the
        // first enabler token and the last disabler token.
        int minEnablerTokenIndex = enablerTokensIndexes.isEmpty() ? -1 : Collections.min(enablerTokensIndexes);
        int maxDisablerTokenIndex = disablerTokensIndexes.isEmpty() ? -1 : Collections.max(disablerTokensIndexes);

        if (minEnablerTokenIndex != maxDisablerTokenIndex) {
            return maxDisablerTokenIndex < minEnablerTokenIndex; // If false, the restriction was disabled at some point
        } else {
            if (minEnablerTokenIndex == -1) { // Both indexes are -1, meaning that the partialExpression is empty. Apply default value.
                return enabledByDefault;
            } else if (enablerTokens.size() != disablerTokens.size()) { // The last token enables (/disables) the restriction and any token can disable (/enable) it
                return disablerTokens.size() <= enablerTokens.size(); // If any token can enable the restriction, the last token disables it, or vice versa
            } else {
                // This should never happen when checking the legality of a token based on standard context restrictions
                throw new IllegalArgumentException("Could not determine if token '" + restrictedToken + "' is valid based on partialExpression '" +
                        compactExpression(partialExpressionTokens) + "' and oracleType '" + oracleType + "'. This may happen if both enablerTokens and disablerTokens are empty lists.");
            }
        }
    }
}
