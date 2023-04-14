package star.tratto.token;

import org.eclipse.emf.ecore.resource.Resource;
import org.javatuples.Pair;
import org.javatuples.Triplet;
import star.tratto.dataset.oracles.OracleDatapoint;
import star.tratto.oracle.OracleType;
import star.tratto.oraclegrammar.custom.Parser;
import star.tratto.token.restrictions.multi.MultiTokenRestriction;
import star.tratto.token.restrictions.multi.MultiTokenRestrictions;
import star.tratto.token.restrictions.single.SingleTokenRestriction;
import star.tratto.token.restrictions.single.SingleTokenRestrictions;

import java.util.*;
import java.util.stream.Collectors;

import static star.tratto.token.TokenEnricher.getEnrichedTokensPlusInfo;
import static star.tratto.token.Tokens.*;
import static star.tratto.token.restrictions.single.SingleTokenRestrictions.getSingleTokenRestriction;
import static star.tratto.util.JavaParserUtils.getReturnTypeOfExpression;
import static star.tratto.util.StringUtils.*;

/**
 * Suggest legal tokens after a given partial TrattoGrammar expression.
 * TODO: Implement OracleGenerationWorkflow class, that keeps track of the current oracle being generated and
 *  avoid having to evaluate all ContextRestrictions for every token suggestion. This should be much more efficient.
 */
public class TokenSuggester {

    private static final Parser parser = Parser.getInstance();

    /**
     * Suggest legal tokens + info after a given partial TrattoGrammar expression. The additional info is passed in
     * the second and third entries of the returned Triplets. The tokens returned are not simply tokens belonging to
     * the TrattoGrammar DSL, but they are actual tokens obtained based on the context of the oracle being generated.
     * For instance, this method will never return the token "someVarOrClassOrFieldOrMethod", but instead it will
     * return the actual names of the method arguments, classes, fields or methods. As another example, this method
     * will not suggest the token "true" as the first token if the oracle being generated is a precondition (this
     * type of behavior is controlled by ContextRestrictionTypes).
     *
     * @param partialExpressionTokens List of tokens of the partial TrattoGrammar expression, e.g., ["(", "this"]
     * @param oracleDatapoint         OracleDatapoint containing all required contextual information, including the oracle
     *                                type (e.g., PRE) and contextual tokens available (e.g., class names and method
     *                                arguments).
     * @return List of triplets containing tokens, their classes (for vars, as dictated by
     * {@link TokenEnricher}) and their additional info, i.e., [package.of.class; Class;
     * method signature or attribute declaration, if applicable].
     */
    public static List<Triplet<String, String, List<String>>> getNextLegalTokensWithContextPlusInfo(List<String> partialExpressionTokens, OracleDatapoint oracleDatapoint) {
        List<Triplet<String, String, List<String>>> nextLegalTokensWithContextPlusInfo = new ArrayList<>();
        List<String> tokensWithoutRestrictions = getNextLegalTokensWithoutRestrictions(partialExpressionTokens, oracleDatapoint);

        // TODO: Filter out tokens based on partialExpression and MultiTokenRestrictions

        // TODO: Forbid closing parenthesis if just opened method call and no method without arguments exists
        // TODO: Forbid "class" modifier after "." if current method argument is not a class
        // TODO: Forbid "stream" if previous expression is not instanceof java.util.Collection
        // TODO: Forbid "Arrays" if nor this, methodResultID or some method argument is an array

        for (String token : tokensWithoutRestrictions) {
            switch (token) {
                case "\"someString\"":
                    nextLegalTokensWithContextPlusInfo.addAll(getStringTokens(oracleDatapoint)
                            .stream()
                            .map(stringToken -> new Triplet<>(stringToken, "S_STRING", List.<String>of()))
                            .collect(Collectors.toList()));
                    break;
                case "1":
                    nextLegalTokensWithContextPlusInfo.addAll(getIntTokens(oracleDatapoint)
                            .stream()
                            .map(stringToken -> new Triplet<>(stringToken, "S_INT", List.<String>of()))
                            .collect(Collectors.toList()));
                    break;
                case "1.0":
                    nextLegalTokensWithContextPlusInfo.addAll(getDoubleTokens(oracleDatapoint)
                            .stream()
                            .map(stringToken -> new Triplet<>(stringToken, "DOUBLE", List.<String>of()))
                            .collect(Collectors.toList()));
                    break;
                case "someVarOrClassOrFieldOrMethod":
                    nextLegalTokensWithContextPlusInfo.addAll(getEnrichedTokensPlusInfo(partialExpressionTokens, oracleDatapoint));
                    break;
                case "methodResultID":
                    nextLegalTokensWithContextPlusInfo.add(new Triplet<>(token, "MethodResultID", getAdditionalInfoOfMethodResultID(oracleDatapoint)));
                    break;
                case "this":
                    nextLegalTokensWithContextPlusInfo.add(new Triplet<>(token, "This", List.of(oracleDatapoint.getPackageName(), oracleDatapoint.getClassName())));
                    break;
                default:
                    nextLegalTokensWithContextPlusInfo.add(new Triplet<>(token, getUniqueTokenClass(token, partialExpressionTokens), List.<String>of()));
            }
        }

        return nextLegalTokensWithContextPlusInfo;
    }

    /**
     * Suggest legal tokens after a given partial TrattoGrammar expression, taking into account both Multi and Single
     * Token restrictions. The workflow is as follows: 1) Get next legal tokens according to grammar -> 2) Filter out
     * tokens based on MultiTokenRestrictions -> 3) Filter out tokens based on SingleTokenRestrictions.
     */
    static List<String> getNextLegalTokensWithoutRestrictions(List<String> partialExpressionTokens, OracleDatapoint oracleDatapoint) {
        List<String> nextLegalTokensWithoutMultiTokenRestrictions = getNextLegalTokensWithoutMultiTokenRestrictions(partialExpressionTokens, oracleDatapoint);
        return getNextLegalTokensWithoutSingleTokenRestrictions(nextLegalTokensWithoutMultiTokenRestrictions, partialExpressionTokens, oracleDatapoint);
    }

    private static List<String> getNextLegalTokensWithoutMultiTokenRestrictions(List<String> partialExpressionTokens, OracleDatapoint oracleDatapoint) {
        List<String> nextLegalTokensAccordingToGrammar = getNextLegalTokensAccordingToGrammar(partialExpressionTokens);
        for (MultiTokenRestriction multiTokenRestriction : MultiTokenRestrictions.DEFAULT_MULTI_RESTRICTIONS) {
            if (multiTokenRestriction.isEnabled(nextLegalTokensAccordingToGrammar, partialExpressionTokens, oracleDatapoint)) {
                nextLegalTokensAccordingToGrammar.removeAll(multiTokenRestriction.getRestrictedTokens());
            }
        }
        return nextLegalTokensAccordingToGrammar;
    }

    /**
     * @param nextLegalTokensWithoutMultiTokenRestrictions This list of tokens MUST NOT contain tokens that are restricted
     *                                                     by MultiTokenRestrictions. If so, the behavior of this method
     *                                                     is undefined and the program may crash. Therefore, before calling
     *                                                     this method, this argument must be set with the result of
     *                                                     {@link #getNextLegalTokensWithoutMultiTokenRestrictions}.
     */
    private static List<String> getNextLegalTokensWithoutSingleTokenRestrictions(List<String> nextLegalTokensWithoutMultiTokenRestrictions, List<String> partialExpressionTokens, OracleDatapoint oracleDatapoint) {
        nextLegalTokensWithoutMultiTokenRestrictions.removeIf(token -> !isTokenLegalBasedOnSingleTokenRestrictions(token, partialExpressionTokens, oracleDatapoint));
        return nextLegalTokensWithoutMultiTokenRestrictions;
    }

    /**
     * Suggest legal tokens according to the TrattoGrammar grammar, after a given partial expression.
     * @param partialExpressionTokens List of tokens of partial TrattoGrammar expression, e.g., ["(", "this"]
     * @return List of legal tokens.
     */
    static List<String> getNextLegalTokensAccordingToGrammar(List<String> partialExpressionTokens) {
        List<String> nextLegalTokens = new ArrayList<>();

        for (Map.Entry<String, String> ruleRepresentativeToken1: Tokens.RULES_REPRESENTATIVE_TOKENS_UNIQUE.entrySet()) {
            for (Map.Entry<String, String> ruleRepresentativeToken2: Tokens.RULES_REPRESENTATIVE_TOKENS_UNIQUE.entrySet()) {
                if (isToken1Legal(partialExpressionTokens, ruleRepresentativeToken1.getValue(), ruleRepresentativeToken2.getValue())) {
                    nextLegalTokens.addAll(RULES_TOKENS.get(ruleRepresentativeToken1.getKey()));
                    break;
                }
            }
        }

        return nextLegalTokens.stream().distinct().collect(Collectors.toList());
    }

    /**
     * This method checks the legality of a (in principal legal) token against all
     * {@link SingleTokenRestrictions#DEFAULT_SINGLE_RESTRICTIONS}.
     * This method is NOT supposed to be called for a token that is not legal for the given partialExpression.
     * If you do this, the method will return true, although it should return false. This is because this method
     * assumes that the token passed is, in principle, legal, but its legality must be checked against
     * ContextRestrictions.
     * @param token Must be legal for the given partialExpression.
     * @param partialExpressionTokens
     * @param oracleDatapoint
     * @return
     */
    static Boolean isTokenLegalBasedOnSingleTokenRestrictions(String token, List<String> partialExpressionTokens, OracleDatapoint oracleDatapoint) {
        OracleType oracleType = oracleDatapoint.getOracleType();
        if (SingleTokenRestrictions.RESTRICTED_TOKENS.get(oracleType).contains(token)) {
            SingleTokenRestriction singleTokenRestriction = getSingleTokenRestriction(oracleType, token);
            return !singleTokenRestriction.isEnabled(token, partialExpressionTokens, oracleDatapoint); // Restriction enabled -> token illegal
        }
        return true;
    }

    /**
     * Returns true if token1 is legal after partialExpression and considering that token2 is the next token.
     * token1 is legal if its error column is not repeated when adding token2. There's a couple of exceptions
     * though:
     * 1) if the oracle so far ends with a relational operator different from "==" and token1 is "this" and token2
     * is ".", the token "this" is actually legal;
     * 2) if the oracle so far ends with a bitwise operator (plus an optional bitwise negate operator) and
     * token1 is "this" and token2 is ".", the token "this" is actually legal.
     */
    private static boolean isToken1Legal(List<String> partialExpressionTokens, String token1, String token2) {
        return !isColumnsRepeated(getNextTwoTokensErrorColumns(partialExpressionTokens, token1, token2), 2)
                ||
                (token1.equals("this") && token2.equals(".") && partialExpressionTokens.get(partialExpressionTokens.size()-1).matches(">=|<=|>|<|!="))
                ||
                (token1.equals("this") && token2.equals(".") && partialExpressionTokens.size() >= 2 &&
                (partialExpressionTokens.get(partialExpressionTokens.size()-1).matches("\\||\\^|&|>>>|>>|<<") ||
                (partialExpressionTokens.get(partialExpressionTokens.size()-2).matches("\\||\\^|&|>>>|>>|<<") &&
                partialExpressionTokens.get(partialExpressionTokens.size()-1).equals("~"))));
    }

    /**
     * When parsing an expression, the parser will return a list of errors with the columns where the errors are located.
     * To identify legal tokens, we need to compare the errorColumns of the partialExpression +1 and +2 tokens. This
     * method returns a list of two lists, each containing the error columns of the partialExpression +1 and +2 tokens.
     * This allows to check the legality of token1.
     */
    private static List<List<Integer>> getNextTwoTokensErrorColumns(List<String> partialExpressionTokens, String token1, String token2) {
        List<List<Integer>> errorColumns = Arrays.asList(new ArrayList<>(), new ArrayList<>());

        List<String> partialExpressionTokensPlus1 = new ArrayList<>(partialExpressionTokens);
        partialExpressionTokensPlus1.add(token1);
        updateErrorColumns(partialExpressionTokensPlus1, errorColumns, 0);

        List<String> partialExpressionTokensPlus2 = new ArrayList<>(partialExpressionTokensPlus1);
        partialExpressionTokensPlus2.add(token2);
        updateErrorColumns(partialExpressionTokensPlus2, errorColumns, 1);

        return errorColumns;
    }

    private static boolean isColumnsRepeated(List<List<Integer>> errorColumns, int repetitions) {
        return errorColumns
                .stream()
                .flatMap(List::stream) // Assuming that only one error is returned per column
                .collect(Collectors.groupingBy(column -> column, Collectors.counting()))
                .values()
                .stream()
                .anyMatch(count -> count >= repetitions);
    }

    /**
     * @param partialExpressionTokens Tokens of partial TrattoGrammar expression to parse
     * @param errorColumns List of lists of error columns for the partial expression +1 and +2 tokens
     * @param errorColumnsIndex Index of the list of error columns to update
     */
    private static void updateErrorColumns(List<String> partialExpressionTokens, List<List<Integer>> errorColumns, int errorColumnsIndex) {
        parser.parseOracle(String.join(" ", partialExpressionTokens));
        errorColumns.set(errorColumnsIndex, parser.getResource().getErrors().stream().map(Resource.Diagnostic::getColumn).collect(Collectors.toList()));
    }

    /**
     * A token may belong to multiple classes, e.g., "true" is both BOOLEAN and TRUE. This method returns the
     * unique class they belong to, by checking if all tokens from each class that are not the token under
     * evaluation are also legal given the partialExpression. The unique class of the token is the class with
     * the greatest number of tokens which are all legal.
     */
    private static String getUniqueTokenClass(String token, List<String> partialExpressionTokens) {
        List<String> tokenClasses = TOKENS_RULES.get(token);
        if (tokenClasses.size() == 1) {
            return tokenClasses.get(0);
        } else {
            // Order token classes by number of tokens they contain, in descending order. For each class,
            // check if all tokens are legal. If so, return the class
            List<String> orderedTokenClasses = tokenClasses.stream()
                    .sorted(Comparator.comparingInt(tokenClass -> RULES_TOKENS.get(tokenClass).size()).reversed())
                    .collect(Collectors.toList());
            for (String tokenClass : orderedTokenClasses) {
                if (new HashSet<>(getNextLegalTokensAccordingToGrammar(partialExpressionTokens)).containsAll(RULES_TOKENS.get(tokenClass))) {
                    return tokenClass;
                }
            }
        }

        // Should never happen:
        throw new IllegalStateException("Could not find unique token class for token " + token + " and partial expression " + compactExpression(partialExpressionTokens));
    }

    private static List<String> getAdditionalInfoOfMethodResultID(OracleDatapoint oracleDatapoint) {
        return getReturnTypeOfExpression("methodResultID", oracleDatapoint).toList().stream().map(Object::toString).collect(Collectors.toList());
    }

    private static List<String> getStringTokens(OracleDatapoint oracleDatapoint) {
        List<String> stringTokens =  oracleDatapoint.getTokensMethodJavadocValues()
                .stream()
                .filter(value -> value.getValue1().equals("String"))
                .map(Pair::getValue0).collect(Collectors.toList());
        stringTokens.addAll(GLOBAL_DICTIONARY.get("String"));
        return stringTokens;
    }

    private static List<String> getIntTokens(OracleDatapoint oracleDatapoint) {
        List<String> intTokens = oracleDatapoint.getTokensMethodJavadocValues()
                .stream()
                .filter(value -> value.getValue1().equals("int"))
                .map(Pair::getValue0).collect(Collectors.toList());
        intTokens.addAll(GLOBAL_DICTIONARY.get("int"));
        return intTokens;
    }

    private static List<String> getDoubleTokens(OracleDatapoint oracleDatapoint) {
        List<String> doubleTokens = oracleDatapoint.getTokensMethodJavadocValues()
                .stream()
                .filter(value -> value.getValue1().equals("double") || value.getValue1().equals("int"))
                .map(Pair::getValue0).collect(Collectors.toList());
        doubleTokens.addAll(GLOBAL_DICTIONARY.get("double"));
        doubleTokens.addAll(GLOBAL_DICTIONARY.get("int"));
        return doubleTokens;
    }
}
