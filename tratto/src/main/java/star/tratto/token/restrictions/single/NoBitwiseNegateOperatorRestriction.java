package star.tratto.token.restrictions.single;

import org.javatuples.Pair;
import star.tratto.data.OracleDatapoint;
import star.tratto.oraclegrammar.custom.Parser;
import star.tratto.oraclegrammar.trattoGrammar.CanEvaluateToPrimitive;
import star.tratto.token.Tokens;
import star.tratto.util.JavaTypes;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static star.tratto.oraclegrammar.custom.Splitter.split;
import static star.tratto.util.javaparser.JavaParserUtils.getReturnTypeOfExpression;
import static star.tratto.util.StringUtils.compactExpression;

/**
 * If left element is not integral, forbid "~"
 */
public class NoBitwiseNegateOperatorRestriction extends SingleTokenRestriction {

    private static NoBitwiseNegateOperatorRestriction instance;
    private static final Parser parser = Parser.getInstance();
    private static final List<String> disablingPreviousTokens = Stream.concat(
            Tokens.RULES_TOKENS.get("BitwiseLogicalOperator").stream(),
            Tokens.RULES_TOKENS.get("BitwiseShiftOperator").stream()
    ).collect(Collectors.toList());

    private NoBitwiseNegateOperatorRestriction() {
        this.restrictedToken = "~";
    }

    static NoBitwiseNegateOperatorRestriction getInstance() {
        if (instance == null) {
            instance = new NoBitwiseNegateOperatorRestriction();
        }
        return instance;
    }

    @Override
    public Boolean isEnabled(String nextLegalToken, List<String> partialExpressionTokens, OracleDatapoint oracleDatapoint) {
        if (!isRestrictedToken(nextLegalToken)) {
            return false;
        }
        if (disablingPreviousTokens.contains(partialExpressionTokens.get(partialExpressionTokens.size() - 1))) {
            return false; // If last token is BitwiseLogicalOperator or BitwiseShiftOperator, "~" can always follow
        }

        CanEvaluateToPrimitive previousElement = parser.findLastCanEvalToPrimInClauseWithVars(String.join(" ", partialExpressionTokens));
        // Can assume that there's an element, so no need to check for nullness
        Pair<String, String> previousElementReturnType = getReturnTypeOfExpression(compactExpression(split(previousElement)), oracleDatapoint);

        return !JavaTypes.INTEGRALS.contains(previousElementReturnType); // If previous element is not integral, rule is enabled
    }
}
