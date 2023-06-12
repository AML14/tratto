package star.tratto.token.restrictions.multi;

import star.tratto.dataset.oracles.OracleDatapoint;
import star.tratto.oraclegrammar.custom.Parser;
import star.tratto.oraclegrammar.trattoGrammar.CanEvaluateToPrimitive;
import star.tratto.token.Tokens;
import star.tratto.util.javaparser.JavaParserUtils;
import star.tratto.util.JavaTypes;

import java.util.List;

import static star.tratto.oraclegrammar.custom.Splitter.split;
import static star.tratto.util.StringUtils.compactExpression;

/**
 * NonEqIneqOperator tokens ("{@code <}", "{@code >}", "{@code <=}", "{@code >=}") are forbidden if previous element's return type is not number-like
 * (int, long, float, double, and corresponding wrappers).
 * This ContextRestriction cannot be evaluated before {@link LastMethodNameRestriction}, otherwise an
 * exception will be thrown.
 */
public class NoNonEqIneqOperatorRestriction extends MultiTokenRestriction {

    private static NoNonEqIneqOperatorRestriction instance;
    private static final Parser parser = Parser.getInstance();

    private NoNonEqIneqOperatorRestriction() {
        this.restrictedTokens = Tokens.RULES_TOKENS.get("NonEqIneqOperator");
        setNonRestrictedTokens();
    }

    static NoNonEqIneqOperatorRestriction getInstance() {
        if (instance == null) {
            instance = new NoNonEqIneqOperatorRestriction();
        }
        return instance;
    }

    /**
     * This restriction cannot be evaluated if the last token of partialExpressionTokens is a method name or a class
     * name. If so, it will throw an exception. Recall that ContextRestrictions are not meant to be evaluated in
     * isolation, but rather in conjunction with other ContextRestrictions, and in a certain order.
     */
    @Override
    public Boolean isEnabled(List<String> nextLegalTokens, List<String> partialExpressionTokens, OracleDatapoint oracleDatapoint) {
        if (!appliesTo(nextLegalTokens)) {
            return false;
        }

        // Otherwise, append ">" at the end and retrieve element preceding ">" (CanEvaluateToPrimitive)
        String exprWithNonEqIneqOp = String.join(" ", partialExpressionTokens) + " >";
        CanEvaluateToPrimitive elementPrecedingNonEqIneqOp = parser.findElementPrecedingLastNonEqIneqOp(exprWithNonEqIneqOp);
        List<String> elementPrecedingNonEqIneqOpTokens = split(elementPrecedingNonEqIneqOp);

        if (elementPrecedingNonEqIneqOpTokens.size() == 1 && oracleDatapoint.isProjectClass(elementPrecedingNonEqIneqOpTokens.get(0))) {
            return true; // If element is class name, NonEqIneqOperator cannot follow
        } else { // Otherwise, evaluate return type. If it's number-like, rule is disabled
            return !JavaTypes.NUMBERS.contains(JavaParserUtils.getReturnTypeOfExpression(compactExpression(elementPrecedingNonEqIneqOpTokens), oracleDatapoint));
        }
    }
}
