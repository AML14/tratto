package star.tratto.token.restrictions.single;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.body.MethodDeclaration;
import star.tratto.dataset.oracles.OracleDatapoint;
import star.tratto.oracle.OracleType;
import star.tratto.util.JavaParserUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static star.tratto.util.JavaParserUtils.getReturnTypeOfExpression;
import static star.tratto.util.StringUtils.compactExpression;

/**
 * This ContextRestriction is a bit different compared to the rest because it needs to handle three
 * scenarios, two applicable to all oracles, and one applicable depending on the oracle type. The three
 * scenarios are as follows:
 * 1) Forbid "methodResultID" if method under test is void.
 * 2) Forbid "methodResultID" after "Arrays.stream(" if it is not an array.
 * 3) Preconditions cannot mention methodResultID, normal postconditions cannot mention methodResultID
 * in the guard, and exceptional postconditions cannot mention methodResultID:
 */
public class NoMethodResultIDRestriction extends SingleTokenRestriction {

    private static NoMethodResultIDRestriction instance;
    private final Map<OracleType, StandardSingleTokenRestriction> oracleSpecificRestrictions;
    private final JavaParser javaParser = JavaParserUtils.getJavaParser();

    private NoMethodResultIDRestriction() {
        this.restrictedToken = "methodResultID";
        this.oracleSpecificRestrictions = new HashMap<>();
        this.oracleSpecificRestrictions.put(OracleType.PRE, new StandardSingleTokenRestriction(OracleType.PRE, "methodResultID", null, null, true));
        this.oracleSpecificRestrictions.put(OracleType.NORMAL_POST, new StandardSingleTokenRestriction(OracleType.NORMAL_POST, "methodResultID", null, List.of("?"), true));
        this.oracleSpecificRestrictions.put(OracleType.EXCEPT_POST, new StandardSingleTokenRestriction(OracleType.EXCEPT_POST, "methodResultID", null, null, true));
    }

    static NoMethodResultIDRestriction getInstance() {
        if (instance == null) {
            instance = new NoMethodResultIDRestriction();
        }
        return instance;
    }

    @Override
    public Boolean isEnabled(String nextLegalToken, List<String> partialExpressionTokens, OracleDatapoint oracleDatapoint) {
        if (!isRestrictedToken(nextLegalToken)) {
            return false;
        }

        MethodDeclaration methodDeclaration;
        try {
            methodDeclaration = javaParser.parseMethodDeclaration(oracleDatapoint.getMethodSourceCode()).getResult().get();
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("The provided methodSourceCode cannot be parsed by JavaParser. Method source code:\n\n" + oracleDatapoint.getMethodSourceCode(), e);
        }

        return
                methodDeclaration.getType().isVoidType()
                ||
                oracleSpecificRestrictions.get(oracleDatapoint.getOracleType()).isEnabled(nextLegalToken, partialExpressionTokens, oracleDatapoint)
                ||
                (
                        compactExpression(partialExpressionTokens).endsWith("Arrays.stream(")
                        &&
                        !getReturnTypeOfExpression("methodResultID", oracleDatapoint).getValue1().contains("[]")
                );

    }
}
