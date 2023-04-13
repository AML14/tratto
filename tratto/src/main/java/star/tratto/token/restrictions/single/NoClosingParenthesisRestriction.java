package star.tratto.token.restrictions.single;

import star.tratto.dataset.oracles.OracleDatapoint;

import java.util.List;

import static star.tratto.token.restrictions.RestrictionsUtils.isEnabledNoCommaOrNoClosingParenthesis;

/**
 * If last method argument does not comply with type, forbid closing parenthesis.
 * <br>
 * Limitations:
 * <ul>
 *     <li>We cannot handle properly methods using "..." (varargs) as arguments. JavaParser will
 *     understand that there's only one argument, when in fact multiple are possible.</li>
 *     <li>We cannot handle properly methods with generic arguments (e.g., T, E, etc.). In those
 *     cases, the generic type will be considered as java.lang.Object, i.e., any type will be valid.
 *     This means that invalid oracles may be generated (due to type constraints being violated),
 *     we'll need to handle this.</li>
 * </ul>
 */
public class NoClosingParenthesisRestriction extends SingleTokenRestriction {
    private static NoClosingParenthesisRestriction instance;

    private NoClosingParenthesisRestriction() {
        this.restrictedToken = ")";
    }

    static NoClosingParenthesisRestriction getInstance() {
        if (instance == null) {
            instance = new NoClosingParenthesisRestriction();
        }
        return instance;
    }

    @Override
    public Boolean isEnabled(String nextLegalToken, List<String> partialExpressionTokens, OracleDatapoint oracleDatapoint) {
        if (!isRestrictedToken(nextLegalToken)) {
            return false;
        }
        return isEnabledNoCommaOrNoClosingParenthesis(nextLegalToken, partialExpressionTokens, oracleDatapoint);
    }
}
