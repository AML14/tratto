package star.tratto.token.restrictions.single;

import star.tratto.dataset.OracleDatapoint;
import star.tratto.oraclegrammar.custom.Parser;

import java.util.List;

import static star.tratto.token.restrictions.RestrictionsUtils.methodOrAttribute;

/**
 * If the last token is an attribute name and not a method name, the next token cannot be a "(".
 */
public class NoOpeningParenthesisRestriction extends SingleTokenRestriction {

    private static NoOpeningParenthesisRestriction instance;
    private static final Parser parser = Parser.getInstance();

    private NoOpeningParenthesisRestriction() {
        this.restrictedToken = "(";
    }

    static NoOpeningParenthesisRestriction getInstance() {
        if (instance == null) {
            instance = new NoOpeningParenthesisRestriction();
        }
        return instance;
    }

    @Override
    public Boolean isEnabled(String nextLegalToken, List<String> partialExpressionTokens, OracleDatapoint oracleDatapoint) {
        if (!isRestrictedToken(nextLegalToken)) {
            return false;
        }

        return methodOrAttribute(partialExpressionTokens, oracleDatapoint) == 2;
    }
}
