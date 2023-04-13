package star.tratto.token.restrictions.single;

import org.eclipse.emf.ecore.EObject;
import org.javatuples.Pair;
import star.tratto.dataset.oracles.OracleDatapoint;
import star.tratto.oraclegrammar.custom.Parser;
import star.tratto.oraclegrammar.trattoGrammar.ClassFieldWithPeriod;
import star.tratto.token.restrictions.multi.LastMethodNameRestriction;

import java.util.List;

import static star.tratto.oraclegrammar.custom.Splitter.split;
import static star.tratto.util.JavaParserUtils.getReturnTypeOfExpression;
import static star.tratto.util.StringUtils.compactExpression;

/**
 * "." is forbidden if last token is a method name, or if previous VarOrClassWithModifiers is not a class,
 * or if its return type is primitive.
 * This ContextRestriction cannot be evaluated before {@link LastMethodNameRestriction}, otherwise an
 * exception will be thrown.
 */
public class NoPeriodRestriction extends SingleTokenRestriction {

    private static NoPeriodRestriction instance;
    private static final Parser parser = Parser.getInstance();

    private NoPeriodRestriction() {
        this.restrictedToken = ".";
    }

    static NoPeriodRestriction getInstance() {
        if (instance == null) {
            instance = new NoPeriodRestriction();
        }
        return instance;
    }

    @Override
    public Boolean isEnabled(String nextLegalToken, List<String> partialExpressionTokens, OracleDatapoint oracleDatapoint) {
        if (!isRestrictedToken(nextLegalToken)) {
            return false;
        }

        // Append "." at the end of the partial oracle and retrieve last element. This restriction applies only if it is a ClassFieldWithPeriod
        EObject lastElement = parser.getLastEObject(String.join(" ", partialExpressionTokens) + " .");
        if (!(lastElement instanceof ClassFieldWithPeriod)) {
            return false;
        }

        // This rule is not enabled if preceding expression is a class, or if last token is not a class attribute, or if return type of preceding expression
        // is not primitive (arrays of primitives are NOT primitives!). In all three cases, a period can follow
        List<String> precedingExprTokensWithPeriod = split(lastElement.eContainer().eContainer());
        if (precedingExprTokensWithPeriod.size() == 2 && oracleDatapoint.isProjectClass(precedingExprTokensWithPeriod.get(0))) {
            return false; // Expression preceding period is a class because it's only 1 token (2 with the period) and it's a class name
        } else {
            String precedingExpr = compactExpression(precedingExprTokensWithPeriod.subList(0, precedingExprTokensWithPeriod.size() - 1));
            Pair<String, String> returnType = getReturnTypeOfExpression(precedingExpr, oracleDatapoint);
            if (!returnType.getValue0().isEmpty() || returnType.getValue1().contains("[]")) { // Return type has package or type is an array
                return false; // Expression preceding period does not evaluate to a primitive type
            }
        }

        // If this point is reached, it means that the return type of the last element of the oracle is primitive, so a period cannot follow
        return true;
    }
}
