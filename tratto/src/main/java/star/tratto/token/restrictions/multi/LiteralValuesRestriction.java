package star.tratto.token.restrictions.multi;

import com.github.javaparser.resolution.MethodUsage;
import org.eclipse.emf.ecore.EObject;
import org.javatuples.Pair;
import star.tratto.dataset.oracles.OracleDatapoint;
import star.tratto.oraclegrammar.custom.Parser;
import star.tratto.oraclegrammar.trattoGrammar.CanEvaluateToPrimitive;
import star.tratto.oraclegrammar.trattoGrammar.MethodCall;
import star.tratto.util.JavaTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static star.tratto.oraclegrammar.custom.Parser.findLastMethodCall;
import static star.tratto.oraclegrammar.custom.Parser.getNArgumentsSoFar;
import static star.tratto.oraclegrammar.custom.Splitter.split;
import static star.tratto.token.restrictions.RestrictionsUtils.getApplicableMethodsOfPrecedingExpr;
import static star.tratto.util.javaparser.JavaParserUtils.*;
import static star.tratto.util.StringUtils.compactExpression;

/**
 * Like {@link ForbidEndExpressionRestriction}, this ContextRestriction works differently from the rest.
 * It is applicable whenever a literal value (null, true, false, 1, 1.0, or "someString") is a possible
 * next legal token. Since no modifiers can follow these tokens, we must make sure that such value is
 * type-compatible at that position (i.e., either with the preceding expression if it's a comparison,
 * or if it's a method argument).
 * <br>
 * The <code>restrictedTokens</code> attribute varies everytime this ContextRestriction is enabled.
 * <br>
 * TODO: Restrict numeric values according to the type (e.g., 128 cannot be assigned to a byte).
 */
public class LiteralValuesRestriction extends MultiTokenRestriction {

        private static LiteralValuesRestriction instance;
        private static final String NULL = "null";
        private static final String TRUE = "true";
        private static final String FALSE = "false";
        private static final String INT = "1";
        private static final String DOUBLE = "1.0";
        private static final String STRING = "\"someString\"";
        private static final Pair<String, String> NULL_TYPE = Pair.with("", "null");
        private static final Pair<String, String> BOOLEAN_TYPE = Pair.with("", "boolean");
        private static final Pair<String, String> INT_TYPE = Pair.with("", "byte"); // Byte to make it less restrictive
        private static final Pair<String, String> DOUBLE_TYPE = Pair.with("", "float"); // Float to make it less restrictive
        private static final Pair<String, String> STRING_TYPE = Pair.with("java.lang", "String");
        private final List<String> possiblyRestrictedTokens = List.of(NULL, TRUE, FALSE, INT, DOUBLE, STRING);

        private static final Parser parser = Parser.getInstance();

        private LiteralValuesRestriction() {}

        static LiteralValuesRestriction getInstance() {
            if (instance == null) {
                instance = new LiteralValuesRestriction();
            }
            return instance;
        }

        @Override
        public Boolean isEnabled(List<String> nextLegalTokens, List<String> partialExpressionTokens, OracleDatapoint oracleDatapoint) {
            if (!appliesTo(nextLegalTokens)) {
                return false;
            }
            restrictedTokens = new ArrayList<>();
            List<String> allowedTokens = new ArrayList<>();

            // Check if we are within method argument or within comparison/operation. String is suggested <=> we are within method argument.
            if (nextLegalTokens.contains(STRING)) {
                EObject lastElementWithUnfinishedMethod = parser.findLastElementWithUnfinishedMethodModifiers(String.join(" ", partialExpressionTokens));
                MethodCall methodCall = findLastMethodCall(lastElementWithUnfinishedMethod);
                String methodName = methodCall.getMethodName(); // Can assume methodCall is not null
                int nArgsSoFar = getNArgumentsSoFar(methodCall);
                int nextArgIndex = nArgsSoFar==0 ? 0 : nArgsSoFar-1;

                // See if there's at least one method whose nextArg is type-compatible with each literal value (null, string, int, etc.)
                List<MethodUsage> matchingMethods = getApplicableMethodsOfPrecedingExpr(lastElementWithUnfinishedMethod, methodName, oracleDatapoint);
                matchingMethods.forEach(m -> {
                    if (m.getNoParams() > nextArgIndex) {
                        Pair<String, String> nextArgType = getTypeFromResolvedType(m.getParamType(nextArgIndex));
                        updateAllowedTokens(allowedTokens, nextArgType, true, oracleDatapoint);
                    }
                });
            } else if (nextLegalTokens.contains(INT)) { // We are within comparison/operation
                CanEvaluateToPrimitive leftElementOfComparison = parser.findLastCanEvalToPrimInClauseWithVars(String.join(" ", partialExpressionTokens));
                Pair<String, String> leftType = getReturnTypeOfExpression(compactExpression(split(leftElementOfComparison)), oracleDatapoint);
                updateAllowedTokens(allowedTokens, leftType, false, oracleDatapoint);
            } else {
                return false; // This may happen if false is suggested but it's not a comparison (e.g., "(a==b)==false") or if true is suggested as a whole predicate
            }

            restrictedTokens.addAll(possiblyRestrictedTokens.stream().filter(t -> !allowedTokens.contains(t)).collect(Collectors.toList()));

            return !restrictedTokens.isEmpty();
        }

        @Override
        protected Boolean appliesTo(List<String> tokens) {
            return possiblyRestrictedTokens.stream().anyMatch(tokens::contains);
        }

        private void updateAllowedTokens(List<String> allowedTokens, Pair<String, String> typeConstraint, boolean isMethodArg, OracleDatapoint oracleDatapoint) {
            if (!allowedTokens.contains(NULL) && isType1AssignableToType2(NULL_TYPE, typeConstraint, oracleDatapoint)) {
                allowedTokens.add(NULL);
            }
            if (!allowedTokens.contains(TRUE) && isType1AssignableToType2(BOOLEAN_TYPE, typeConstraint, oracleDatapoint)) {
                allowedTokens.add(TRUE);
                allowedTokens.add(FALSE);
            }
            // --- Necessary preprocessing: If the typeConstraint is a numeric primitive wrapper, we must convert it to its primitive type
            if (JavaTypes.PRIMITIVE_WRAPPERS.contains(typeConstraint) && JavaTypes.NUMBERS.contains(typeConstraint)) {
                typeConstraint = JavaTypes.WRAPPERS_TO_PRIMITIVES.get(typeConstraint);
            }
            // ---
            if (!allowedTokens.contains(INT) && isType1AssignableToType2(INT_TYPE, typeConstraint, oracleDatapoint)) {
                allowedTokens.add(INT);
            }
            if (!allowedTokens.contains(DOUBLE) && isType1AssignableToType2(DOUBLE_TYPE, typeConstraint, oracleDatapoint)) {
                allowedTokens.add(DOUBLE);
            }
            if (!allowedTokens.contains(STRING) && isType1AssignableToType2(STRING_TYPE, typeConstraint, oracleDatapoint) && isMethodArg) {
                allowedTokens.add(STRING); // Strings are only allowed as method arguments
            }
        }

    public List<String> getPossiblyRestrictedTokens() {
        return possiblyRestrictedTokens;
    }
}
