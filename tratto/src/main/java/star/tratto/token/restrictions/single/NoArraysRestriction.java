package star.tratto.token.restrictions.single;

import org.javatuples.Triplet;
import star.tratto.dataset.oracles.OracleDatapoint;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static star.tratto.util.JavaParserUtils.getMethodDeclaration;
import static star.tratto.util.JavaParserUtils.getReturnTypeOfExpression;

/**
 * Forbid "Arrays" if nor methodResultID or some method argument is an array.
 */
public class NoArraysRestriction extends SingleTokenRestriction {

    private static NoArraysRestriction instance;

    private NoArraysRestriction() {
        this.restrictedToken = "Arrays";
    }

    static NoArraysRestriction getInstance() {
        if (instance == null) {
            instance = new NoArraysRestriction();
        }
        return instance;
    }

    @Override
    public Boolean isEnabled(String nextLegalToken, List<String> partialExpressionTokens, OracleDatapoint oracleDatapoint) {
        if (!isRestrictedToken(nextLegalToken)) {
            return false;
        }

        boolean methodResultIDIsArray = !getMethodDeclaration(oracleDatapoint.getMethodSourceCode()).getType().isVoidType() &&
                getReturnTypeOfExpression("methodResultID", oracleDatapoint).getValue1().contains("[]");
        List<String> methodArgumentsClasses = oracleDatapoint.getTokensMethodArguments().stream().map(Triplet::getValue2).collect(Collectors.toList());

        return !methodResultIDIsArray && methodArgumentsClasses.stream().noneMatch(c -> c.contains("[]"));
    }
}
