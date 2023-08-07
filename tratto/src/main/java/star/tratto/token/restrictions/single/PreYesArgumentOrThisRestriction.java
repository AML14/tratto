package star.tratto.token.restrictions.single;

import star.tratto.data.OracleDatapoint;
import star.tratto.data.OracleType;
import star.tratto.data.records.MethodArgumentTokens;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Preconditions must mention some argument of the method they refer to and/or this.
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
        if (!isRestrictedToken(nextLegalToken) || oracleType != oracleDatapoint.getOracleType()) {
            // Restriction only applicable to PRE oracles
            return false;
        }

        List<String> disablerTokens = oracleDatapoint.getTokensMethodArguments().stream().map(MethodArgumentTokens::argumentName).collect(Collectors.toList());
        disablerTokens.add("this");

        // This ContextRestriction can be treated as a standard ContextRestriction by including the method argument and "this" as disabler tokens
        StandardSingleTokenRestriction standardRestriction = new StandardSingleTokenRestriction(oracleType, restrictedToken, enablerTokens, disablerTokens, false);

        return standardRestriction.isEnabled(nextLegalToken, partialExpressionTokens, oracleDatapoint);
    }
}
