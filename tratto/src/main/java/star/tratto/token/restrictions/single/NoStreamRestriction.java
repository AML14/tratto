package star.tratto.token.restrictions.single;

import org.javatuples.Pair;
import star.tratto.dataset.oracles.OracleDatapoint;

import java.util.List;

import static star.tratto.util.JavaParserUtils.getReturnTypeOfExpression;
import static star.tratto.util.JavaParserUtils.isType1InstanceOfType2;
import static star.tratto.util.StringUtils.fullyQualifiedClassName;

/**
 * Forbid "stream" if it is called upon a variable and nor this, methodResultID or some method argument
 * is an array.
 */
public class NoStreamRestriction extends SingleTokenRestriction {

    private static NoStreamRestriction instance;

    private NoStreamRestriction() {
        this.restrictedToken = "stream";
    }

    static NoStreamRestriction getInstance() {
        if (instance == null) {
            instance = new NoStreamRestriction();
        }
        return instance;
    }

    @Override
    public Boolean isEnabled(String nextLegalToken, List<String> partialExpressionTokens, OracleDatapoint oracleDatapoint) {
        if (!isRestrictedToken(nextLegalToken)) {
            return false;
        }
        if (partialExpressionTokens.get(partialExpressionTokens.size() - 2).equals("Arrays")) {
            return false; // This restriction applies only if "stream" is called upon a variable, not upon the Arrays class
        }

        // If any of the variables is a collection, then "stream()" should be a method available within the oracleDatapoint
        boolean noneIsCollection = oracleDatapoint.getTokensMethodVariablesNonPrivateNonStaticNonVoidMethods().stream().noneMatch(m ->
                m.getValue0().equals("stream") &&
                m.getValue1().equals("java.util") &&
                m.getValue2().equals("Collection")
        );
        if (noneIsCollection) {
            return true;
        }

        // If "stream" is suggested as next legal token, then previous token is "." and token before that is a variable. Get its type
        Pair<String, String> streamVariableType = getReturnTypeOfExpression(partialExpressionTokens.get(partialExpressionTokens.size() - 2), oracleDatapoint);
        return !isType1InstanceOfType2(fullyQualifiedClassName(streamVariableType), "java.util.Collection", oracleDatapoint); // If var is not Collection, "stream" is forbidden
    }
}
