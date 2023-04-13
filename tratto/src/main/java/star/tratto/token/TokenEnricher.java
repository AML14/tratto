package star.tratto.token;

import org.javatuples.Pair;
import org.javatuples.Triplet;
import star.tratto.dataset.oracles.OracleDatapoint;
import star.tratto.oraclegrammar.custom.Parser;
import star.tratto.oraclegrammar.trattoGrammar.CanEvaluateToPrimitive;
import star.tratto.util.JavaTypes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static star.tratto.oraclegrammar.custom.Splitter.split;
import static star.tratto.util.JavaParserUtils.getReturnTypeOfExpression;
import static star.tratto.util.JavaParserUtils.isType1InstanceOfType2;
import static star.tratto.util.StringUtils.compactExpression;
import static star.tratto.util.StringUtils.fullyQualifiedClassName;

/**
 * This class serves the purpose of augmenting the list of legal tokens generated
 * by the {@link TokenSuggester} with additional tokens that don't belong to the
 * grammar, i.e., variable names. This includes classes, attributes, methods and
 * arguments of the method under test. These tokens replace the placeholder token
 * "someVarOrClassOrFieldOrMethod", used for the following rules of the grammar:
 * ClassName, ClassField, MethodName, SpecificVarOrClass (see {@link Tokens}).
 * <br>
 * The list of legal tokens is augmented by taking into account the oracle generated
 * so far (e.g., if an attribute or method should next, after a period) and the
 * oracle datapoint (e.g., retrieving the specific attributes and methods available
 * at that point in the oracle).
 */
public class TokenEnricher {

    private static final Parser parser = Parser.getInstance();

    /**
     * Flag to indicate whether to add method arguments and classes to the list of legal tokens. This happens
     * ONLY IF none of the other enriching rules is triggered (i.e., {@link #getClassNamesAfterInstanceOf},
     * {@link #getStaticAttributesAndMethods} and {@link #getNonStaticAttributesAndMethods}).
     */
    private static boolean addMethodArgumentsAndClasses = true;

    /**
     * This method takes as input a list of tokens composing a partial oracle <b>after which a variable
     * (class/attribute/method/argument) could go</b> and an oracle datapoint containing additional
     * information, and returns a list of tokens to replace the placeholder token "someVarOrClassOrFieldOrMethod",
     * extracted from the oracle datapoint (including token class and additional info). For information
     * regarding the arguments and return value, see {@link TokenSuggester#getNextLegalTokensWithContextPlusInfo}.
     */
    public static List<Triplet<String, String, List<String>>> getEnrichedTokensPlusInfo(List<String> partialExpressionTokens, OracleDatapoint oracleDatapoint) {
        List<Triplet<String, String, List<String>>> enrichedTokensPlusInfo = new ArrayList<>();

        addMethodArgumentsAndClasses = true;
        enrichedTokensPlusInfo.addAll(getClassNamesAfterInstanceOf(partialExpressionTokens, oracleDatapoint));
        enrichedTokensPlusInfo.addAll(getStaticAttributesAndMethods(partialExpressionTokens, oracleDatapoint));
        enrichedTokensPlusInfo.addAll(getNonStaticAttributesAndMethods(partialExpressionTokens, oracleDatapoint));
        enrichedTokensPlusInfo.addAll(getMethodArgumentsAndClassNames(partialExpressionTokens, oracleDatapoint));

        return enrichedTokensPlusInfo;
    }

    /**
     * If last token is "instanceof", return all compatible class names. Otherwise, return empty list.
     */
    static List<Triplet<String, String, List<String>>> getClassNamesAfterInstanceOf(List<String> partialExpressionTokens, OracleDatapoint oracleDatapoint) {
        List<Triplet<String, String, List<String>>> enrichedTokensPlusInfo = new ArrayList<>();

        // Check if this token enriching rule applies. If so, set addMethodArgumentsAndClasses to false and continue. If not, return empty list
        if (partialExpressionTokens.isEmpty() || !partialExpressionTokens.get(partialExpressionTokens.size() - 1).equals("instanceof")) {
            return enrichedTokensPlusInfo;
        }
        addMethodArgumentsAndClasses = false;

        // Get return type of expression preceding "instanceof"
        Object objectExprPrecedingInstanceOf = parser.findElementPrecedingLastInstanceOf(compactExpression(partialExpressionTokens));
        String exprPrecedingInstanceOf;
        if ("this".equals(objectExprPrecedingInstanceOf)) {
            exprPrecedingInstanceOf = "this";
        } else if (objectExprPrecedingInstanceOf instanceof CanEvaluateToPrimitive) {
            exprPrecedingInstanceOf = compactExpression(split(objectExprPrecedingInstanceOf));
        } else {
            throw new IllegalArgumentException("The element preceding the \"instanceof\" token must be either \"this\" or " +
                    "a CanEvaluateToPrimitive element, but found \"" + objectExprPrecedingInstanceOf.getClass().getName() + "\"");
        }
        Pair<String, String> exprReturnType = getReturnTypeOfExpression(exprPrecedingInstanceOf, oracleDatapoint);

        // For each project class, check if return type of preceding expression is instanceof it. If so, add it to the list
        enrichedTokensPlusInfo.addAll(oracleDatapoint.getTokensProjectClasses()
                .stream()
                .filter(pair -> isType1InstanceOfType2(fullyQualifiedClassName(exprReturnType.getValue0(), exprReturnType.getValue1()), fullyQualifiedClassName(pair.getValue1(), pair.getValue0())))
                .map(pair -> Triplet.with(pair.getValue0(), "Class", List.of(pair.getValue1(), pair.getValue0())))
                .collect(Collectors.toList())
        );

        // Add default Java classes
        enrichedTokensPlusInfo.addAll(JavaTypes.TYPICAL
                .stream()
                .filter(pair -> isType1InstanceOfType2(fullyQualifiedClassName(exprReturnType.getValue0(), exprReturnType.getValue1()), fullyQualifiedClassName(pair.getValue0(), pair.getValue1())))
                .map(pair -> Triplet.with(pair.getValue1(), "Class", List.of(pair.getValue0(), pair.getValue1())))
                .collect(Collectors.toList())
        );

        return enrichedTokensPlusInfo;
    }

    /**
     * If last token is "." and previous token is class name, return static attributes and methods of that class.
     * Otherwise, return empty list.
     */
    static List<Triplet<String, String, List<String>>> getStaticAttributesAndMethods(List<String> partialExpressionTokens, OracleDatapoint oracleDatapoint) {
        List<Triplet<String, String, List<String>>> enrichedTokensPlusInfo = new ArrayList<>();
        int nTokens = partialExpressionTokens.size();
        String lastToken;
        String previousToken;

        // Check if this token enriching rule applies. If so, set addMethodArgumentsAndClasses to false and continue. If not, return empty list
        if (nTokens >= 2) {
            lastToken = partialExpressionTokens.get(nTokens - 1);
            previousToken = partialExpressionTokens.get(nTokens - 2);
            if (!".".equals(lastToken) || !oracleDatapoint.getTokensProjectClasses().stream().map(Pair::getValue0).collect(Collectors.toList()).contains(previousToken)) {
                return enrichedTokensPlusInfo;
            }
        } else {
            return enrichedTokensPlusInfo;
        }
        addMethodArgumentsAndClasses = false;

        // Get static attributes of class
        enrichedTokensPlusInfo.addAll(oracleDatapoint.getTokensProjectClassesNonPrivateStaticAttributes()
                .stream()
                .filter(quartet -> quartet.getValue2().equals(previousToken))
                .map(quartet -> Triplet.with(quartet.getValue0(), "ClassField", List.of(quartet.getValue1(), quartet.getValue2(), quartet.getValue3())))
                .collect(Collectors.toList())
        );

        // Get static methods of class
        enrichedTokensPlusInfo.addAll(oracleDatapoint.getTokensProjectClassesNonPrivateStaticNonVoidMethods()
                .stream()
                .filter(quartet -> quartet.getValue2().equals(previousToken))
                .map(quartet -> Triplet.with(quartet.getValue0(), "MethodName", List.of(quartet.getValue1(), quartet.getValue2(), quartet.getValue3())))
                .collect(Collectors.toList())
        );

        return enrichedTokensPlusInfo;
    }

    /**
     * If last token is "." and previous token is not a class name, return non-static attributes and methods
     * of that class. Otherwise, return empty list.
     */
    static List<Triplet<String, String, List<String>>> getNonStaticAttributesAndMethods(List<String> partialExpressionTokens, OracleDatapoint oracleDatapoint) {
        List<Triplet<String, String, List<String>>> enrichedTokensPlusInfo = new ArrayList<>();
        int nTokens = partialExpressionTokens.size();
        String lastToken;
        String previousToken;

        // Check if this token enriching rule applies. If so, set addMethodArgumentsAndClasses to false and continue. If not, return empty list
        if (nTokens >= 2) {
            lastToken = partialExpressionTokens.get(nTokens - 1);
            previousToken = partialExpressionTokens.get(nTokens - 2);
            if (!".".equals(lastToken) || oracleDatapoint.getTokensProjectClasses().stream().map(Pair::getValue0).collect(Collectors.toList()).contains(previousToken)) {
                return enrichedTokensPlusInfo;
            }
        } else {
            return enrichedTokensPlusInfo;
        }
        addMethodArgumentsAndClasses = false;

        // Get return type of expression preceding "."
        List<String> lastElementWithModifierTokens = split(parser.findLastElementWithModifiers(String.join(" ", partialExpressionTokens)));
        String precedingExpr = compactExpression(lastElementWithModifierTokens.subList(0, lastElementWithModifierTokens.size()-1));
        Pair<String, String> precedingExprReturnType = getReturnTypeOfExpression(precedingExpr, oracleDatapoint);

        // Get non-static attributes of class, including those whose this class is instance of
        enrichedTokensPlusInfo.addAll(oracleDatapoint.getTokensMethodVariablesNonPrivateNonStaticAttrributes() // Attributes applicable to this, methodResultID and method arguments
                .stream()
                .filter(quartet -> isType1InstanceOfType2(fullyQualifiedClassName(precedingExprReturnType.getValue0(), precedingExprReturnType.getValue1()), fullyQualifiedClassName(quartet.getValue1(), quartet.getValue2())))
                .map(quartet -> Triplet.with(quartet.getValue0(), "ClassField", List.of(quartet.getValue1(), quartet.getValue2(), quartet.getValue3())))
                .collect(Collectors.toList()));
        enrichedTokensPlusInfo.addAll(oracleDatapoint.getTokensOracleVariablesNonPrivateNonStaticAttributes() // Attributes applicable to elements of the oracle
                .stream()
                .filter(quartet -> isType1InstanceOfType2(fullyQualifiedClassName(precedingExprReturnType.getValue0(), precedingExprReturnType.getValue1()), fullyQualifiedClassName(quartet.getValue1(), quartet.getValue2())))
                .map(quartet -> Triplet.with(quartet.getValue0(), "ClassField", List.of(quartet.getValue1(), quartet.getValue2(), quartet.getValue3())))
                .collect(Collectors.toList()));

        // Get non-static methods of class, including those whose this class is instance of
        enrichedTokensPlusInfo.addAll(oracleDatapoint.getTokensMethodVariablesNonPrivateNonStaticNonVoidMethods() // Methods applicable to this, methodResultID and method arguments
                .stream()
                .filter(quartet -> isType1InstanceOfType2(fullyQualifiedClassName(precedingExprReturnType.getValue0(), precedingExprReturnType.getValue1()), fullyQualifiedClassName(quartet.getValue1(), quartet.getValue2())))
                .map(quartet -> Triplet.with(quartet.getValue0(), "MethodName", List.of(quartet.getValue1(), quartet.getValue2(), quartet.getValue3())))
                .collect(Collectors.toList()));
        enrichedTokensPlusInfo.addAll(oracleDatapoint.getTokensOracleVariablesNonPrivateNonStaticNonVoidMethods() // Methods applicable to elements of the oracle
                .stream()
                .filter(quartet -> isType1InstanceOfType2(fullyQualifiedClassName(precedingExprReturnType.getValue0(), precedingExprReturnType.getValue1()), fullyQualifiedClassName(quartet.getValue1(), quartet.getValue2())))
                .map(quartet -> Triplet.with(quartet.getValue0(), "MethodName", List.of(quartet.getValue1(), quartet.getValue2(), quartet.getValue3())))
                .collect(Collectors.toList()));

        return enrichedTokensPlusInfo.stream().distinct().collect(Collectors.toList()); // Possible duplicates among "variables" and "oracle" columns
    }

    static Collection<Triplet<String, String, List<String>>> getMethodArgumentsAndClassNames(List<String> partialExpressionTokens, OracleDatapoint oracleDatapoint) {
        List<Triplet<String, String, List<String>>> enrichedTokensPlusInfo = new ArrayList<>();

        // Check if this token enriching rule applies. If not, set addMethodArgumentsAndClasses to true and return empty list
        if (!addMethodArgumentsAndClasses) {
            addMethodArgumentsAndClasses = true;
            return enrichedTokensPlusInfo;
        }

        // Get method arguments
        enrichedTokensPlusInfo.addAll(oracleDatapoint.getTokensMethodArguments()
                .stream()
                .map(triplet -> Triplet.with(triplet.getValue0(), "MethodArgument", List.of(triplet.getValue1(), triplet.getValue2())))
                .collect(Collectors.toList())
        );

        // Get class names
        enrichedTokensPlusInfo.addAll(oracleDatapoint.getTokensProjectClasses()
                .stream()
                .map(pair -> Triplet.with(pair.getValue0(), "Class", List.of(pair.getValue1(), pair.getValue0())))
                .collect(Collectors.toList())
        );

        return enrichedTokensPlusInfo;
    }
}
