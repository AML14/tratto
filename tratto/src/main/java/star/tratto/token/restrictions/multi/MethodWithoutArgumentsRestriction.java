package star.tratto.token.restrictions.multi;

import com.github.javaparser.resolution.MethodUsage;
import org.eclipse.emf.ecore.EObject;
import star.tratto.dataset.oracles.OracleDatapoint;
import star.tratto.oraclegrammar.custom.Parser;
import star.tratto.token.Tokens;

import java.util.List;
import java.util.stream.Collectors;

import static star.tratto.token.restrictions.RestrictionsUtils.getApplicableMethodsOfPrecedingExpr;

/**
 * Forbid all tokens except ")" if previous tokens are method name and "(" and method has no arguments.
 */
public class MethodWithoutArgumentsRestriction extends MultiTokenRestriction {

    private static MethodWithoutArgumentsRestriction instance;
    private static final Parser parser = Parser.getInstance();

    private MethodWithoutArgumentsRestriction() {
        this.restrictedTokens = Tokens.TOKENS.stream().filter(token -> !token.equals(")")).collect(Collectors.toList());
        this.nonRestrictedTokens = List.of(")");
    }

    static MethodWithoutArgumentsRestriction getInstance() {
        if (instance == null) {
            instance = new MethodWithoutArgumentsRestriction();
        }
        return instance;
    }

    @Override
    public Boolean isEnabled(List<String> nextLegalTokens, List<String> partialExpressionTokens, OracleDatapoint oracleDatapoint) {
        if (!appliesTo(nextLegalTokens)) {
            return false;
        }

        // Check if previous tokens are method name and "("
        if (!hasExpectedFormat(partialExpressionTokens)) {
            return false;
        }

        // Retrieve applicable methods of preceding expression, i.e., with the same name
        EObject lastElementWithUnfinishedMethod = parser.findLastElementWithUnfinishedMethodModifiers(String.join(" ", partialExpressionTokens));
        String methodName = partialExpressionTokens.get(partialExpressionTokens.size() - 2);
        List<MethodUsage> applicableMethods = getApplicableMethodsOfPrecedingExpr(lastElementWithUnfinishedMethod, methodName, oracleDatapoint);

        applicableMethods.removeIf(m -> m.getNoParams() == 0); // Remove methods without arguments to see if there are any left

        return applicableMethods.isEmpty(); // If there are no methods with arguments, then this method has no arguments and ")" MUST follow
    }

    private boolean hasExpectedFormat(List<String> partialExpressionTokens) {
        int nTokens = partialExpressionTokens.size();
        return (
                nTokens >= 4 && // e.g., "SomeClass.someMethod("
                partialExpressionTokens.get(nTokens - 1).equals("(") && // Last token is "("
                !Tokens.TOKENS.contains(partialExpressionTokens.get(nTokens - 2)) // 2nd to last token is method name, i.e., not from the grammar
        );
    }
}
