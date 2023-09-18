package star.tratto.token.restrictions;

import com.github.javaparser.resolution.MethodUsage;
import org.eclipse.emf.ecore.EObject;
import org.javatuples.Pair;
import star.tratto.data.OracleDatapoint;
import star.tratto.data.records.AttributeTokens;
import star.tratto.data.records.TypeTokens;
import star.tratto.data.records.MethodTokens;
import star.tratto.oraclegrammar.custom.Parser;
import star.tratto.oraclegrammar.trattoGrammar.MethodCall;
import star.tratto.token.Tokens;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static star.tratto.oraclegrammar.custom.Parser.*;
import static star.tratto.oraclegrammar.custom.Splitter.split;
import static star.tratto.util.javaparser.JavaParserUtils.*;
import static star.tratto.util.StringUtils.compactExpression;
import static star.tratto.util.StringUtils.fullyQualifiedClassName;

public class RestrictionsUtils {

    private static final Parser parser = Parser.getInstance();

    /**
     * The {@link star.tratto.token.restrictions.single.SingleTokenRestriction#isEnabled} method of
     * {@link star.tratto.token.restrictions.single.NoClosingParenthesisRestriction} and
     * {@link star.tratto.token.restrictions.single.NoCommaRestriction} are very similar. This method
     * is used to avoid code duplication.
     */
    public static Boolean isEnabledNoCommaOrNoClosingParenthesis(String nextLegalToken, List<String> partialExpressionTokens, OracleDatapoint oracleDatapoint) {
        // Get last element with unfinished method modifiers. If "," is suggested as a next legal token, for sure there will be one.
        // If instead ")" is the next legal token, it may be possible that there's no unfinished method call, so this restriction does not apply
        EObject lastElementWithUnfinishedMethod = parser.findLastElementWithUnfinishedMethodModifiers(String.join(" ", partialExpressionTokens));
        if (nextLegalToken.equals(")") && lastElementWithUnfinishedMethod == null) {
            return false;
        }

        // To analyze type constraints of method arguments, we need:
        // 1) preceding expression (from where method is called)
        // 2) method name
        // 3) arguments so far
        MethodCall methodCall = findLastMethodCall(lastElementWithUnfinishedMethod);
        String methodName = methodCall.getMethodName(); // Can assume methodCall is not null
        int nArgsSoFar = getNArgumentsSoFar(methodCall);

        List<MethodUsage> matchingMethods = getApplicableMethodsOfPrecedingExpr(lastElementWithUnfinishedMethod, methodName, oracleDatapoint);

        // Previous check: if nArgsSoFar==0, make sure that there's at least one method with no arguments, otherwise return true
        if (nArgsSoFar == 0) {
            return matchingMethods.stream().noneMatch(m -> m.getNoParams() == 0);
        }

        // If nextLegalToken==",", do not consider matchingMethods with <= nArgsSoFar. If nextLegalToken==")", do not consider matchingMethods with != nArgsSoFar
        // Also, the type of the current argument must be type-compatible with the type of the nArgsSoFar-th argument of the matchingMethod
        matchingMethods.removeIf(m -> {
            if ((nextLegalToken.equals(",") && m.getNoParams() <= nArgsSoFar) || (nextLegalToken.equals(")") && m.getNoParams() != nArgsSoFar)) {
                return true;
            } else {
                Pair<String, String> currentArgType = getReturnTypeOfExpression(compactExpression(split(getLastArgument(methodCall))), oracleDatapoint);
                Pair<String, String> nArgsArgType = getTypePairFromResolvedType(m.getParamType(nArgsSoFar-1));
                return !isType1AssignableToType2(currentArgType, nArgsArgType, oracleDatapoint);
            }
        });

        return matchingMethods.isEmpty();
    }

    /**
     * Given an expression like "SomeClass.someField.someMethod(someArg", this function returns all
     * applicable methods that can be invoked on top of "SomeClass.someField".
     */
    public static List<MethodUsage> getApplicableMethodsOfPrecedingExpr(EObject lastElementWithUnfinishedMethod, String methodName, OracleDatapoint oracleDatapoint) {
        String precedingExpr = compactExpression(split(removeLastVarOrClassModifier(lastElementWithUnfinishedMethod)));

        // If preceding expression is class, get all matching classes (they may have different packages) of the project
        // and, from them, extract all methods with the same name that are static, non-private and non-void
        // Else, get return type of preceding expression and extract all methods with the same name that are non-static,
        // non-private and non-void
        List<MethodUsage> matchingMethods = new ArrayList<>(); // TODO: Don't consider method under test

        if (!precedingExpr.contains(".") && oracleDatapoint.isProjectClass(precedingExpr)) {
            // Preceding expression is a project class
            List<TypeTokens> matchingClasses = oracleDatapoint.getTokensProjectClasses()
                    .stream()
                    .filter(c -> c.typeName().equals(precedingExpr))
                    .collect(Collectors.toList());
            matchingClasses.forEach(c -> matchingMethods.addAll(
                    getMethodsOfType(fullyQualifiedClassName(c.packageName(), c.typeName()))
                            .stream()
                            .filter(m -> m.getName().equals(methodName) && isNonPrivateStaticNonVoidMethod(m))
                            .collect(Collectors.toList())
            ));
        } else {
            Pair<String, String> precedingExprReturnType = getReturnTypeOfExpression(precedingExpr, oracleDatapoint);
            matchingMethods.addAll(
                    getMethodsOfType(fullyQualifiedClassName(precedingExprReturnType))
                            .stream()
                            .filter(m -> m.getName().equals(methodName) && isNonPrivateNonStaticNonVoidMethod(m))
                            .collect(Collectors.toList())
            );
        }

        // At this point there should be at least one matching method
        assert !matchingMethods.isEmpty();

        return matchingMethods;
    }

    /**
     * Utility function used both by LastMethodNameRestriction (multi) and
     * NoOpeningParenthesisRestriction (single), to check whether the last token
     * of a partial expression is a method name, an attribute name, both, or none.
     * <br>
     * <strong>
     * NOTE: This function assumes that any of the two ContextRestrictions are
     * applicable (i.e., the appliesTo or isRestrictedToken methods must be called
     * before calling this function).
     * </strong>
     *
     * @return
     * <ul>
     *     <li>0 if the last token is nor a method name nor an attribute name.</li>
     *     <li>1 if the last token is a method name.</li>
     *     <li>2 if the last token is an attribute name.</li>
     *     <li>3 if the last token is both a method name and an attribute name.</li>
     * </ul>
     */
    public static int methodOrAttribute(List<String> partialExpressionTokens, OracleDatapoint oracleDatapoint) {
        if (!hasExpectedFormat(partialExpressionTokens)) {
            return 0;
        }
        List<String> lastElementWithModifiersTokens = split(parser.findLastElementWithModifiers(String.join(" ", partialExpressionTokens)));
        String possibleMethodName = lastElementWithModifiersTokens.get(lastElementWithModifiersTokens.size()-1);
        List<String> compatibleMethodNames = new ArrayList<>();
        List<String> compatibleAttributeNames = new ArrayList<>();

        // To check if last element can only be a method name, we need to:
        // 1.- Check if preceding Java expression (i.e, removing last "." and name) is a class, after which only static methods could go
        // 2.- If not, get return type A and look for methods whose return type B fulfill the condition "A instanceof B" and whose name is not also a class attribute
        String precedingExpr = compactExpression(lastElementWithModifiersTokens.subList(0, lastElementWithModifiersTokens.size()-2));
        if (lastElementWithModifiersTokens.size() == 3 && oracleDatapoint.isProjectClass(precedingExpr)) {
            compatibleMethodNames.addAll(oracleDatapoint.getTokensProjectClassesNonPrivateStaticNonVoidMethods() // Static methods from all classes
                    .stream()
                    .filter(quartet -> quartet.className().equals(precedingExpr))
                    .map(MethodTokens::methodName)
                    .collect(Collectors.toList()));
            compatibleAttributeNames.addAll(oracleDatapoint.getTokensProjectClassesNonPrivateStaticAttributes() // Static attributes from all classes
                    .stream()
                    .filter(quartet -> quartet.className().equals(precedingExpr))
                    .map(AttributeTokens::attributeName)
                    .collect(Collectors.toList()));
        } else {
            Pair<String, String> precedingExprReturnType = getReturnTypeOfExpression(precedingExpr, oracleDatapoint);
            compatibleMethodNames.addAll(oracleDatapoint.getTokensMethodVariablesNonPrivateNonStaticNonVoidMethods() // Methods applicable to this, methodResultID and method arguments
                    .stream()
                    .filter(quartet -> isInstanceOf(fullyQualifiedClassName(precedingExprReturnType.getValue0(), precedingExprReturnType.getValue1()), fullyQualifiedClassName(quartet.packageName(), quartet.className()), oracleDatapoint))
                    .map(MethodTokens::methodName)
                    .collect(Collectors.toList()));
            compatibleMethodNames.addAll(oracleDatapoint.getTokensOracleVariablesNonPrivateNonStaticNonVoidMethods() // Methods applicable to elements of the oracle
                    .stream()
                    .filter(quartet -> isInstanceOf(fullyQualifiedClassName(precedingExprReturnType.getValue0(), precedingExprReturnType.getValue1()), fullyQualifiedClassName(quartet.packageName(), quartet.className()), oracleDatapoint))
                    .map(MethodTokens::methodName)
                    .collect(Collectors.toList()));
            compatibleAttributeNames.addAll(oracleDatapoint.getTokensMethodVariablesNonPrivateNonStaticAttributes() // Attributes applicable to this, methodResultID and method arguments
                    .stream()
                    .filter(quartet -> isInstanceOf(fullyQualifiedClassName(precedingExprReturnType.getValue0(), precedingExprReturnType.getValue1()), fullyQualifiedClassName(quartet.packageName(), quartet.className()), oracleDatapoint))
                    .map(AttributeTokens::attributeName)
                    .collect(Collectors.toList()));
            compatibleAttributeNames.addAll(oracleDatapoint.getTokensOracleVariablesNonPrivateNonStaticAttributes() // Attributes applicable to elements of the oracle
                    .stream()
                    .filter(quartet -> isInstanceOf(fullyQualifiedClassName(precedingExprReturnType.getValue0(), precedingExprReturnType.getValue1()), fullyQualifiedClassName(quartet.packageName(), quartet.className()), oracleDatapoint))
                    .map(AttributeTokens::attributeName)
                    .collect(Collectors.toList()));
        }

        if (compatibleMethodNames.contains(possibleMethodName) && compatibleAttributeNames.contains(possibleMethodName)) {
            return 3;
        } else if (compatibleMethodNames.contains(possibleMethodName)) {
            return 1;
        } else if (compatibleAttributeNames.contains(possibleMethodName)) {
            return 2;
        } else {
            throw new IllegalStateException("The last token of the partial expression is neither a method name nor an attribute name. " +
                    "This may happen if you call this method before checking whether this ContextRestriction is applicable or not " +
                    "or if the oracles dataset (and this oracle datapoint in particular) is not correctly populated.");
        }
    }

    /**
     * Checks whether the partialExpression has the expected format to be subject to the LastMethodNameRestriction
     * or NoOpeningParenthesisRestriction. For the last token to be a method name, the partialExpression
     * must have the following format:
     * 1) "(classOrField|this|methodResultID).methodName", or 2) "methodName().methodName"
     * @param partialExpressionTokens
     * @return true if the partialExpression has the expected format, false otherwise
     */
    private static boolean hasExpectedFormat(List<String> partialExpressionTokens) {
        int nTokens = partialExpressionTokens.size();
        return (
                nTokens >= 3 // partialExpression must be at least 3 tokens long to contain a method name
                        && !Tokens.TOKENS.contains(partialExpressionTokens.get(nTokens - 1)) // Last token must be a method name, i.e., not from the grammar
                        && partialExpressionTokens.get(nTokens - 2).equals(".") // 2nd to last token must be "."
                        && (
                        List.of(")", "this", "methodResultID").contains(partialExpressionTokens.get(nTokens - 3)) // 3rd to last token must be ")", "this", "methodResultID"
                                || !Tokens.TOKENS.contains(partialExpressionTokens.get(nTokens - 3)) // or class/field name, i.e., not from the grammar
                )
        );
    }
}
