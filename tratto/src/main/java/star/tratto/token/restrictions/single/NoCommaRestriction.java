package star.tratto.token.restrictions.single;

import star.tratto.data.OracleDatapoint;

import java.util.List;

import static star.tratto.token.restrictions.RestrictionsUtils.isEnabledNoCommaOrNoClosingParenthesis;

/**
 * If current method argument does not comply with type, forbid comma.
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
public class NoCommaRestriction extends SingleTokenRestriction {
    private static NoCommaRestriction instance;

    private NoCommaRestriction() {
        this.restrictedToken = ",";
    }

    static NoCommaRestriction getInstance() {
        if (instance == null) {
            instance = new NoCommaRestriction();
        }
        return instance;
    }

    /**
     * This restriction cannot be evaluated if the last token of partialExpressionTokens is a method name or a class
     * name. If so, it will throw an exception, or the behavior will be undefined. Recall that ContextRestrictions are
     * not meant to be evaluated in isolation, but rather in conjunction with other ContextRestrictions, and in a
     * certain order.
     */
    @Override
    public Boolean isEnabled(String nextLegalToken, List<String> partialExpressionTokens, OracleDatapoint oracleDatapoint) {
        if (!isRestrictedToken(nextLegalToken)) {
            return false;
        }
        return isEnabledNoCommaOrNoClosingParenthesis(nextLegalToken, partialExpressionTokens, oracleDatapoint);
    }
}
