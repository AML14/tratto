package star.tratto.token.restrictions.multi;

import org.javatuples.Pair;
import star.tratto.dataset.oracles.OracleDatapoint;
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
 * In a comparison, if previous element does not evaluate to integral, forbid BitwiseOperators
 */
public class NoBitwiseOperatorRestriction extends MultiTokenRestriction {

    private static NoBitwiseOperatorRestriction instance;
    private static final Parser parser = Parser.getInstance();

    private NoBitwiseOperatorRestriction() {
        this.restrictedTokens = Stream.concat(
                Tokens.RULES_TOKENS.get("BitwiseLogicalOperator").stream(),
                Tokens.RULES_TOKENS.get("BitwiseShiftOperator").stream()
        ).collect(Collectors.toList());
        setNonRestrictedTokens();
    }

    static NoBitwiseOperatorRestriction getInstance() {
        if (instance == null) {
            instance = new NoBitwiseOperatorRestriction();
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

        return !JavaTypes.INTEGRALS.contains(previousElementReturnType); // If previous element is not integral, rule is enabled
    }
}
