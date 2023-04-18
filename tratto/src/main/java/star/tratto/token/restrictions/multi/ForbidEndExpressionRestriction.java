package star.tratto.token.restrictions.multi;

import org.javatuples.Pair;
import star.tratto.dataset.oracles.OracleDatapoint;
import star.tratto.oraclegrammar.custom.Parser;
import star.tratto.oraclegrammar.trattoGrammar.ClauseWithVars;
import star.tratto.oraclegrammar.trattoGrammar.EqIneqComparisonElement;
import star.tratto.oraclegrammar.trattoGrammar.OtherComparisonElement;
import star.tratto.token.Tokens;
import star.tratto.util.JavaTypes;

import java.util.ArrayList;
import java.util.List;

import static star.tratto.oraclegrammar.custom.Parser.*;
import static star.tratto.oraclegrammar.custom.Splitter.split;
import static star.tratto.util.javaparser.JavaParserUtils.getReturnTypeOfExpression;
import static star.tratto.util.javaparser.JavaParserUtils.isType1InstanceOfType2;
import static star.tratto.util.StringUtils.compactExpression;
import static star.tratto.util.StringUtils.fullyQualifiedClassName;

/**
 * This ContextRestriction works differently from the rest. It is applicable whenever a LogicalOperator
 * (&& or ||) is a possible next legal token. Placing such token would mean that the former Clause (i.e.,
 * (expression) is finished. Finishing a Clause has several implications:
 *
 * <ul>
 *     <li>The Clause must evaluate to boolean.</li>
 *     <li>If the Clause contains a comparison, the right element must be type-compatible with the left element.</li>
 *     <li>If the Clause contains a bitwise/arithmetical operation, the right element must be type-compatible with the left element.</li>
 * </ul>
 *
 * When this ContextRestriction is applicable, it is necessary to check for all the three conditions
 * above. Depending on which are true, some tokens or others may be forbidden. Therefore, the
 * <code>restrictedTokens</code> attribute varies everytime this ContextRestriction is enabled.
 */
public class ForbidEndExpressionRestriction extends MultiTokenRestriction {

    private static ForbidEndExpressionRestriction instance;
    private List<String> commonRestrictedTokens;
    private List<String> operationRestrictedTokens;
    private List<String> clauseRestrictedTokens;
    private List<String> comparisonRestrictedTokens;

    private static final Parser parser = Parser.getInstance();

    private ForbidEndExpressionRestriction() {
        this.commonRestrictedTokens = List.of("&&", "||", "?", ":", ";", ")");

        this.operationRestrictedTokens = new ArrayList<>(this.commonRestrictedTokens);

        this.clauseRestrictedTokens = new ArrayList<>(this.commonRestrictedTokens);

        this.comparisonRestrictedTokens = new ArrayList<>(this.commonRestrictedTokens);
        this.comparisonRestrictedTokens.addAll(Tokens.RULES_TOKENS.get("ArithmeticalOperator"));
        this.comparisonRestrictedTokens.addAll(Tokens.RULES_TOKENS.get("BitwiseLogicalOperator"));
        this.comparisonRestrictedTokens.addAll(Tokens.RULES_TOKENS.get("BitwiseShiftOperator"));
    }

    static ForbidEndExpressionRestriction getInstance() {
        if (instance == null) {
            instance = new ForbidEndExpressionRestriction();
        }
        return instance;
    }

    @Override
    public Boolean isEnabled(List<String> nextLegalTokens, List<String> partialExpressionTokens, OracleDatapoint oracleDatapoint) {
        if (!appliesTo(nextLegalTokens)) {
            return false;
        }
        restrictedTokens = new ArrayList<>();

        // The partialExpression contains for sure a ClauseWithVars, retrieve the last one to check conditions on it
        ClauseWithVars clause = parser.findLastClauseWithVars(String.join(" ", partialExpressionTokens));

        // Check if the clause contains a comparison
        if (clause.getFirstEqOperator() != null || clause.getSecondEqOperator() != null ||
                clause.getIneqOperator() != null || clause.getNonEqIneqOperator() != null) {
            Object rightElemOfComp = getRightElementOfComparison(clause);
            if (rightElemOfComp != null) {
                // Check if the clause contains a bitwise/arithmetical operation
                OtherComparisonElement otherCompElem = null;
                String operationOperator = null;
                if (rightElemOfComp instanceof OtherComparisonElement) {
                    otherCompElem = (OtherComparisonElement) rightElemOfComp;
                    operationOperator = getOperationOperator(otherCompElem);
                }
                if (operationOperator != null) {
                    return isOperationIncomplete(otherCompElem, oracleDatapoint);
                } else { // If it doesn't, the clause contains only a comparison
                    return isComparisonIncomplete(clause, oracleDatapoint);
                }
            }
        } else if (clause.getFirstInstanceOfOperator() == null && clause.getSecondInstanceOfOperator() == null) {
            return isClauseIncomplete(clause, oracleDatapoint); // If there are no instanceof operators, the clause has only a left element
        }

        return false;
    }

    @Override
    protected Boolean appliesTo(List<String> tokens) {
        return tokens.contains("&&");
    }

    /**
     * This method assumes that the OtherComparisonElement passed contains an operation. If the left
     * and right elements of the operation are type-compatible, the operation is considered complete,
     * so this method returns false. Otherwise, if the left and right elements are not type-compatible,
     * the operation is considered incomplete, so restrictedTokens is set to operationRestrictedTokens
     * and this method returns true.
     */
    private Boolean isOperationIncomplete(OtherComparisonElement otherCompElem, OracleDatapoint oracleDatapoint) {
        boolean incomplete = false;

        // There's an arithm/bit operator and '&&' is a next legal token, so can assume that there's a right element in the operation
        String operator = getOperationOperator(otherCompElem);
        Object rightElement = getRightElementOfOperation(otherCompElem);

        if (Tokens.RULES_TOKENS.get("BitwiseLogicalOperator").contains(operator) || Tokens.RULES_TOKENS.get("BitwiseShiftOperator").contains(operator)) {
            if (!JavaTypes.INTEGRALS.contains(getReturnTypeOfExpression(compactExpression(split(rightElement)), oracleDatapoint))) {
                incomplete = true; // The operator is bitwise and the left element is not integral, so the operation is incomplete
            }
        } else if (Tokens.RULES_TOKENS.get("ArithmeticalOperator").contains(operator)) {
            if (!JavaTypes.NUMBERS.contains(getReturnTypeOfExpression(compactExpression(split(rightElement)), oracleDatapoint))) {
                incomplete = true; // The operator is arithmetical and the left element is not numeric, so the operation is incomplete
            }
        } else {
            throw new IllegalArgumentException("The OtherComparisonElement should contain and arithmetical or bitwise operator: " + compactExpression(split(otherCompElem)));
        }

        if (incomplete) {
            restrictedTokens = operationRestrictedTokens;
        }
        return incomplete;
    }

    /**
     * This method assumes that the ClauseWithVars passed contains a comparison but not an operation
     * in the right element of the comparison. If the left and right elements of the comparison are
     * type-compatible, the comparison is considered complete, so this method returns false. Otherwise,
     * if the left and right elements are not type-compatible, the comparison is considered incomplete,
     * so restrictedTokens is set to comparisonRestrictedTokens and this method returns true.
     */
    private Boolean isComparisonIncomplete(ClauseWithVars clauseWithVars, OracleDatapoint oracleDatapoint) {
        // There's a relational operator and '&&' is a next legal token, so can assume that there's a right element in the comparison
        Object leftElement = getLeftVar(clauseWithVars);
        Object rightElement = getRightElementOfComparison(clauseWithVars);
        if (rightElement instanceof EqIneqComparisonElement) { // Right element might be null or boolean, so comparison would be complete
            return false;
        }
        Pair<String, String> leftType = getReturnTypeOfExpression(compactExpression(split(leftElement)), oracleDatapoint);
        Pair<String, String> rightType = getReturnTypeOfExpression(compactExpression(split(rightElement)), oracleDatapoint);

        boolean leftPrimitive = JavaTypes.PRIMITIVES.contains(leftType);
        boolean rightPrimitive = JavaTypes.PRIMITIVES.contains(rightType);

        // Cases to consider, in this order:
        // 1) left and right types are the same -> false
        // 2) left and right types are both numeric -> false
        // 3) left type is primitive and right type is wrapper -> false
        // 4) right type is primitive and left type is wrapper -> false
        // 5) left type is instance of right type (both types are non-primitive) -> false
        // 6) right type is instance of left type (both types are non-primitive) -> false
        // 7) any type is primitive and they are different -> true
        // 8) neither type is instance of the other (both types are non-primitive) -> true
        if (
                !(
                        (leftType.equals(rightType)) || // 1)
                        (JavaTypes.NUMBERS.contains(leftType) && JavaTypes.NUMBERS.contains(rightType)) || // 2)
                        (leftPrimitive && JavaTypes.PRIMITIVES_TO_WRAPPERS.get(leftType).equals(rightType)) || // 3)
                        (rightPrimitive && JavaTypes.PRIMITIVES_TO_WRAPPERS.get(rightType).equals(leftType)) || // 4)
                        (!leftPrimitive && !rightPrimitive && isType1InstanceOfType2(fullyQualifiedClassName(leftType), fullyQualifiedClassName(rightType))) || // 5)
                        (!leftPrimitive && !rightPrimitive && isType1InstanceOfType2(fullyQualifiedClassName(rightType), fullyQualifiedClassName(leftType))) // 6)
                ) && (
                        ((leftPrimitive || rightPrimitive) && !leftType.equals(rightType)) || // 7)
                        (!leftPrimitive && !rightPrimitive &&
                                !isType1InstanceOfType2(fullyQualifiedClassName(leftType), fullyQualifiedClassName(rightType)) &&
                                !isType1InstanceOfType2(fullyQualifiedClassName(rightType), fullyQualifiedClassName(leftType))
                        ) // 8
                )
        ) {
            restrictedTokens = comparisonRestrictedTokens;
            return true;
        }

        return false;
    }

    /**
     * This method assumes that the ClauseWithVars passed does not contain a comparison. If the clause
     * evaluates to boolean, it is considered complete, so this method returns false. Otherwise, if
     * it does not evaluate to boolean, the clause is considered incomplete, so restrictedTokens is
     * set to clauseRestrictedTokens and this method returns true.
     */
    private Boolean isClauseIncomplete(ClauseWithVars clauseWithVars, OracleDatapoint oracleDatapoint) {
        Object leftElement = getLeftVar(clauseWithVars);
        Pair<String, String> leftType = getReturnTypeOfExpression(compactExpression(split(leftElement)), oracleDatapoint);
        if (!JavaTypes.BOOLEANS.contains(leftType)) {
            restrictedTokens = clauseRestrictedTokens;
            return true;
        }
        return false;
    }
}
