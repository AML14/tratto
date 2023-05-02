package star.tratto.token.restrictions.single;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.MethodDeclaration;
import star.tratto.dataset.oracles.OracleDatapoint;
import star.tratto.util.JavaParserUtils;

import java.util.List;

import static star.tratto.util.JavaParserUtils.getMethodDeclaration;

/**
 * "this" is forbidden if the method under test is static.
 */
public class NoThisRestriction extends SingleTokenRestriction {

    private static NoThisRestriction instance;

    private NoThisRestriction() {
        this.restrictedToken = "this";
    }

    static NoThisRestriction getInstance() {
        if (instance == null) {
            instance = new NoThisRestriction();
        }
        return instance;
    }

    @Override
    public Boolean isEnabled(String nextLegalToken, List<String> partialExpressionTokens, OracleDatapoint oracleDatapoint) {
        if (!isRestrictedToken(nextLegalToken)) {
            return false;
        }

        MethodDeclaration methodDeclaration = getMethodDeclaration(oracleDatapoint.getMethodSourceCode());
        // If methodDeclaration is null, the method under test is a constructor, so "this" would be forbidden
        return methodDeclaration == null || methodDeclaration.getModifiers()
                .stream()
                .anyMatch(modifier -> modifier.getKeyword() == Modifier.Keyword.STATIC);
    }
}
