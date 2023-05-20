package star.tratto.token.restrictions.single;

import star.tratto.dataset.oracles.OracleDatapoint;
import star.tratto.oraclegrammar.custom.Parser;
import star.tratto.oraclegrammar.trattoGrammar.CanEvaluateToPrimitive;
import star.tratto.token.restrictions.multi.LastMethodNameRestriction;
import star.tratto.util.JavaTypes;

import java.util.List;

import static star.tratto.oraclegrammar.custom.Splitter.split;
import static star.tratto.util.javaparser.JavaParserUtils.getReturnTypeOfExpression;
import static star.tratto.util.StringUtils.compactExpression;

/**
 * "instanceof" is forbidden if previous element return type is not a class. This means that it is also
 * forbidden after a class name.
 * This ContextRestriction cannot be evaluated before {@link LastMethodNameRestriction}, otherwise an
 * exception will be thrown.
 */
public class NoInstanceofRestriction extends SingleTokenRestriction {

    private static NoInstanceofRestriction instance;
    private static final Parser parser = Parser.getInstance();

    private NoInstanceofRestriction() {
        this.restrictedToken = "instanceof";
    }

    static NoInstanceofRestriction getInstance() {
        if (instance == null) {
            instance = new NoInstanceofRestriction();
        }
        return instance;
    }

    @Override
    public Boolean isEnabled(String nextLegalToken, List<String> partialExpressionTokens, OracleDatapoint oracleDatapoint) {
        if (!isRestrictedToken(nextLegalToken)) {
            return false;
        }
        if (partialExpressionTokens.get(partialExpressionTokens.size() - 1).equals("this")) {
            return false; // If last token is "this", "instanceof" can always follow, so rule is disabled
        }

        // Otherwise, append "instanceof" at the end and retrieve element preceding "instanceof" (CanEvaluateToPrimitive)
        String exprWithInstanceof = String.join(" ", partialExpressionTokens) + " instanceof";
        CanEvaluateToPrimitive elementPrecedingInstanceof = (CanEvaluateToPrimitive) parser.findElementPrecedingLastInstanceOf(exprWithInstanceof);
        List<String> elementPrecedingInstanceofTokens = split(elementPrecedingInstanceof);

        if (elementPrecedingInstanceofTokens.size() == 1 && oracleDatapoint.isProjectClass(elementPrecedingInstanceofTokens.get(0))) {
            return true; // If element is class name, "instanceof" cannot follow
        } else { // Otherwise, evaluate return type. If it's primitive, rule is enabled, otherwise rule is disabled
            return JavaTypes.PRIMITIVES.contains(getReturnTypeOfExpression(compactExpression(elementPrecedingInstanceofTokens), oracleDatapoint));
        }


    }
}
