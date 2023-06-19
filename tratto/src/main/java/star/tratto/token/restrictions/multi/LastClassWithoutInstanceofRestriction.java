package star.tratto.token.restrictions.multi;

import star.tratto.data.OracleDatapoint;
import star.tratto.token.Tokens;

import java.util.List;
import java.util.stream.Collectors;

import static star.tratto.util.StringUtils.compactExpression;

/**
 * If last token is class name, and second to last token is not "instanceof", the next token
 * MUST be a "." (i.e., forbids any other token)
 */
public class LastClassWithoutInstanceofRestriction extends MultiTokenRestriction {

    private static LastClassWithoutInstanceofRestriction instance;

    private LastClassWithoutInstanceofRestriction() {
        this.restrictedTokens = Tokens.TOKENS.stream().filter(token -> !token.equals(".")).toList();
        this.nonRestrictedTokens = List.of(".");
    }

    static LastClassWithoutInstanceofRestriction getInstance() {
        if (instance == null) {
            instance = new LastClassWithoutInstanceofRestriction();
        }
        return instance;
    }

    @Override
    public Boolean isEnabled(List<String> nextLegalTokens, List<String> partialExpressionTokens, OracleDatapoint oracleDatapoint) {
        if (!appliesTo(nextLegalTokens)) {
            return false;
        }

        int nTokens = partialExpressionTokens.size();
        return (
                nTokens >= 1 &&
                !Tokens.TOKENS.contains(partialExpressionTokens.get(nTokens - 1)) && // Last token must be a class name, i.e., not from the grammar
                (nTokens == 1 || !partialExpressionTokens.get(nTokens - 2).equals("instanceof")) && // Second to last token cannot be "instanceof"
                oracleDatapoint.isProjectClass(partialExpressionTokens.get(nTokens - 1)) // Last token is a class from the project
        );
    }
}
