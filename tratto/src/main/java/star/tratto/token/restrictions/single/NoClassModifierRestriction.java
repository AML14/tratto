package star.tratto.token.restrictions.single;

import star.tratto.data.OracleDatapoint;

import java.util.List;

/**
 * Forbid "class" modifier if 2nd-to-last token is not a project class.
 */
public class NoClassModifierRestriction extends SingleTokenRestriction {

    private static NoClassModifierRestriction instance;

    private NoClassModifierRestriction() {
        this.restrictedToken = "class";
    }

    static NoClassModifierRestriction getInstance() {
        if (instance == null) {
            instance = new NoClassModifierRestriction();
        }
        return instance;
    }

    @Override
    public Boolean isEnabled(String nextLegalToken, List<String> partialExpressionTokens, OracleDatapoint oracleDatapoint) {
        if (!isRestrictedToken(nextLegalToken)) {
            return false;
        }

        // If "class" is suggested as a next legal token, then previous token is "." and token before that is an ID (var or class)
        return !oracleDatapoint.isProjectClass(partialExpressionTokens.get(partialExpressionTokens.size() - 2));
    }
}
