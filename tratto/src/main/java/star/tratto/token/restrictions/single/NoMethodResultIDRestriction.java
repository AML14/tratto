package star.tratto.token.restrictions.single;

import star.tratto.dataset.oracles.OracleDatapoint;
import star.tratto.oracle.OracleType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static star.tratto.util.javaparser.JavaParserUtils.getReturnTypeOfExpression;
import static star.tratto.util.StringUtils.compactExpression;

/**
 * This ContextRestriction is a bit different compared to the rest because it needs to handle two scenarios,
 * one applicable to all oracles, and another applicable depending on the oracle type. The two scenarios
 * are as follows:
 * 1) Forbid "methodResultID" after "Arrays.stream(" if it is not an array.
 * 2) Preconditions cannot mention methodResultID, normal postconditions cannot mention methodResultID
 * in the guard, and exceptional postconditions cannot mention methodResultID:
 */
public class NoMethodResultIDRestriction extends SingleTokenRestriction {

    private static NoMethodResultIDRestriction instance;
    private final Map<OracleType, StandardSingleTokenRestriction> oracleSpecificRestrictions;

    private NoMethodResultIDRestriction() {
        this.restrictedToken = "methodResultID";
        this.oracleSpecificRestrictions = new HashMap<>();
        this.oracleSpecificRestrictions.put(OracleType.PRE, new StandardSingleTokenRestriction(OracleType.PRE, "methodResultID", null, null, true));
        this.oracleSpecificRestrictions.put(OracleType.NORMAL_POST, new StandardSingleTokenRestriction(OracleType.NORMAL_POST, "methodResultID", null, List.of("?"), true));
        this.oracleSpecificRestrictions.put(OracleType.EXCEPT_POST, new StandardSingleTokenRestriction(OracleType.EXCEPT_POST, "methodResultID", null, null, true));
    }

    static NoMethodResultIDRestriction getInstance() {
        if (instance == null) {
            instance = new NoMethodResultIDRestriction();
        }
        return instance;
    }

    @Override
    public Boolean isEnabled(String nextLegalToken, List<String> partialExpressionTokens, OracleDatapoint oracleDatapoint) {
        if (!isRestrictedToken(nextLegalToken)) {
            return false;
        }
        return oracleSpecificRestrictions.get(oracleDatapoint.getOracleType()).isEnabled(nextLegalToken, partialExpressionTokens, oracleDatapoint)
                ||
                (
                        compactExpression(partialExpressionTokens).endsWith("Arrays.stream(")
                        &&
                        !getReturnTypeOfExpression("methodResultID", oracleDatapoint).getValue1().contains("[]")
                );

    }
}
