package star.tratto.token.restrictions.single;

import star.tratto.dataset.oracles.OracleDatapoint;
import star.tratto.oracle.OracleType;

import java.util.List;

/**
 * Preconditions must mention the argument of the method they refer to and/or this.
 */
public class PreYesArgumentOrThisRestriction extends SingleTokenRestriction {

    private static PreYesArgumentOrThisRestriction instance;

    private PreYesArgumentOrThisRestriction() {
        this.oracleType = OracleType.PRE;
        this.restrictedToken = ";";
        this.enablerTokens = List.of();
    }

    public static PreYesArgumentOrThisRestriction getInstance() {
        if (instance == null) {
            instance = new PreYesArgumentOrThisRestriction();
        }
        return instance;
    }

    @Override
    public Boolean isEnabled(String nextLegalToken, List<String> partialExpressionTokens, OracleDatapoint oracleDatapoint) {
        if (!isRestrictedToken(nextLegalToken) || oracleType != oracleDatapoint.getOracleType()) { // Restriction only applicable to PRE oracles
            return false;
        }

        String methodArgument;
        try {
            methodArgument = oracleDatapoint.getJavadocTag().split("^ *@param +")[1].split(" +")[0];
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("The provided oracleDatapoint does not contain a Javadoc tag with the correct format. The format should be " +
                    "\"@param <methodArgument> <description>\". Javadoc tag: " + oracleDatapoint.getJavadocTag(), e);
        }

        // This ContextRestriction can be treated as a standard ContextRestriction by including the method argument and "this" as disabler tokens
        StandardSingleTokenRestriction standardRestriction = new StandardSingleTokenRestriction(oracleType, restrictedToken, enablerTokens, List.of(methodArgument, "this"), false);

        return standardRestriction.isEnabled(nextLegalToken, partialExpressionTokens, oracleDatapoint);
    }
}
