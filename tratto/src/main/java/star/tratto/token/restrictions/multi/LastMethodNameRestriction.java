package star.tratto.token.restrictions.multi;

import star.tratto.dataset.oracles.OracleDatapoint;
import star.tratto.token.Tokens;

import java.util.List;
import java.util.stream.Collectors;

import static star.tratto.token.restrictions.RestrictionsUtils.methodOrAttribute;

/**
 * If the last token is a method name and not an attribute name, the next token
 * MUST be a "(" (i.e., forbids any other token)
 */
public class LastMethodNameRestriction extends MultiTokenRestriction {

    private static LastMethodNameRestriction instance;

    private LastMethodNameRestriction() {
        this.restrictedTokens = Tokens.TOKENS.stream().filter(token -> !token.equals("(")).collect(Collectors.toList());
        this.nonRestrictedTokens = List.of("(");
    }

    static LastMethodNameRestriction getInstance() {
        if (instance == null) {
            instance = new LastMethodNameRestriction();
        }
        return instance;
    }

    @Override
    public Boolean isEnabled(List<String> nextLegalTokens, List<String> partialExpressionTokens, OracleDatapoint oracleDatapoint) {
        if (!appliesTo(nextLegalTokens)) {
            return false;
        }

        return methodOrAttribute(partialExpressionTokens, oracleDatapoint) == 1;
    }
}
