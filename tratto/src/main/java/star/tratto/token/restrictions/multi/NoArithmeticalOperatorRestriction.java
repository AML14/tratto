package star.tratto.token.restrictions.multi;

import org.javatuples.Pair;
import star.tratto.data.OracleDatapoint;
import star.tratto.oraclegrammar.custom.Parser;
import star.tratto.oraclegrammar.trattoGrammar.CanEvaluateToPrimitive;
import star.tratto.token.Tokens;
import star.tratto.util.JavaTypes;

import java.util.List;

import static star.tratto.oraclegrammar.custom.Splitter.split;
import static star.tratto.util.JavaParserUtils.getReturnTypeOfExpression;
import static star.tratto.util.StringUtils.compactExpression;

/**
 * In a comparison, if previous element does not evaluate to number-like, forbid ArithmeticalOperators
 */
public class NoArithmeticalOperatorRestriction extends MultiTokenRestriction {

    private static NoArithmeticalOperatorRestriction instance;
    private static final Parser parser = Parser.getInstance();

    private NoArithmeticalOperatorRestriction() {
        this.restrictedTokens = Tokens.RULES_TOKENS.get("ArithmeticalOperator");
        setNonRestrictedTokens();
    }

    static NoArithmeticalOperatorRestriction getInstance() {
        if (instance == null) {
            instance = new NoArithmeticalOperatorRestriction();
        }
        return instance;
    }

    @Override
    public Boolean isEnabled(List<String> nextLegalTokens, List<String> partialExpressionTokens, OracleDatapoint oracleDatapoint) {
        if (!appliesTo(nextLegalTokens)) {
            return false;
        }

        CanEvaluateToPrimitive previousElement = parser.findLastCanEvalToPrimInOtherCompElem(String.join(" ", partialExpressionTokens));
        // Can assume that there's an element, so no need to check for nullness
        Pair<String, String> previousElementReturnType = getReturnTypeOfExpression(compactExpression(split(previousElement)), oracleDatapoint);

        return !JavaTypes.NUMBERS.contains(previousElementReturnType); // If previous element is not number, rule is enabled
    }
}
