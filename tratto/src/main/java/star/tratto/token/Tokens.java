package star.tratto.token;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.AbstractMap.SimpleEntry;
import java.util.stream.Collectors;

/**
 * Helper class that contains all static constants related to tokens.
 * TODO: Replace Strings for rules and tokens with enums.
 */
public class Tokens {

    private static final Logger logger = LoggerFactory.getLogger(Tokens.class);

    public static Map<String, List<String>> GLOBAL_DICTIONARY = new HashMap<>(); // key: type, value: tokens

    static {
        try {
            GLOBAL_DICTIONARY = new ObjectMapper().readValue(new File("src/main/resources/global-dictionary.json"), new TypeReference<>() {});
        } catch (IOException e) {
            logger.error("Error reading global dictionary: {}" + e.getMessage());
        }
    }

    /**
     * Map containing TrattoGrammar rules as keys and the tokens that belong to each rule as values.
     * TODO: Generate automatically based on Xtext grammar.
     */
    public static final Map<String, List<String>> RULES_TOKENS = Map.<String, List<String>>ofEntries(
            new SimpleEntry<>("Model", List.of()),
            new SimpleEntry<>("Oracle", List.of()),
            new SimpleEntry<>("NL", List.of()),
            new SimpleEntry<>("S_STRING", List.of(
                    "\"someString\""
            )),
            new SimpleEntry<>("DOUBLE_QUOTE", List.of()),
            new SimpleEntry<>("ID", List.of()),
            new SimpleEntry<>("Predicate", List.of()),
            new SimpleEntry<>("PredicateNoTrue", List.of()),
            new SimpleEntry<>("Clause", List.of()),
            new SimpleEntry<>("ClauseContinuation", List.of()),
            new SimpleEntry<>("ClauseTrue", List.of()),
            new SimpleEntry<>("ArrayStreamClauseFromClass", List.of()),
            new SimpleEntry<>("ArrayStreamClauseFromVar", List.of()),
            new SimpleEntry<>("ArrayStreamClauseContinuation", List.of()),
            new SimpleEntry<>("ClauseWithVars", List.of()),
            new SimpleEntry<>("ClassName", List.of(
                    "someVarOrClassOrFieldOrMethod"
            )),
            new SimpleEntry<>("EqIneqComparisonElement", List.of()),
            new SimpleEntry<>("OtherComparisonElement", List.of()),
            new SimpleEntry<>("BitCompatibleElement", List.of()),
            new SimpleEntry<>("DoubleCompatibleElement", List.of()),
            new SimpleEntry<>("VarOrClassWithModifiers", List.of()),
            new SimpleEntry<>("IsolableVarOrClassWithModifiers", List.of()),
            new SimpleEntry<>("ThisWithMandatoryModifiers", List.of()),
            new SimpleEntry<>("CanEvaluateToPrimitive", List.of()),
            new SimpleEntry<>("VarOrClassModifier", List.of()),
            new SimpleEntry<>("ClassFieldWithPeriod", List.of()),
            new SimpleEntry<>("ClassField", List.of(
                    "someVarOrClassOrFieldOrMethod"
            )),
            new SimpleEntry<>("MethodCallWithPeriod", List.of()),
            new SimpleEntry<>("MethodCall", List.of()),
            new SimpleEntry<>("MethodName", List.of(
                    "someVarOrClassOrFieldOrMethod"
            )),
            new SimpleEntry<>("MethodContent", List.of()),
            new SimpleEntry<>("MethodContentContinuation", List.of()),
            new SimpleEntry<>("MethodArgument", List.of()),
            new SimpleEntry<>("IsolableVarOrClass", List.of()),
            new SimpleEntry<>("GeneralVarOrClass", List.of()),
            new SimpleEntry<>("SpecificVarOrClass", List.of(
                    "someVarOrClassOrFieldOrMethod"
            )),
            new SimpleEntry<>("This", List.of(
                    "this"
            )),
            new SimpleEntry<>("MethodResultID", List.of(
                    "methodResultID"
            )),
            new SimpleEntry<>("ClassModifier", List.of(
                    "class"
            )),
            new SimpleEntry<>("InstanceOfOperator", List.of(
                    "instanceof"
            )),
            new SimpleEntry<>("EqOperator", List.of(
                    "=="
            )),
            new SimpleEntry<>("IneqOperator", List.of(
                    "!="
            )),
            new SimpleEntry<>("NonEqIneqOperator", List.of(
                    ">=",
                    "<=",
                    ">",
                    "<"
            )),
            new SimpleEntry<>("LogicalOperator", List.of(
                    "&&",
                    "||"
            )),
            new SimpleEntry<>("ArithmeticalOperator", List.of(
                    "+",
                    "-",
                    "/",
                    "*",
                    "%"
            )),
            new SimpleEntry<>("BitwiseLogicalOperator", List.of(
                    "|",
                    "&",
                    "^"
            )),
            new SimpleEntry<>("BitwiseShiftOperator", List.of(
                    "<<",
                    ">>",
                    ">>>"
            )),
            new SimpleEntry<>("BitwiseNegateOperator", List.of(
                    "~"
            )),
            new SimpleEntry<>("MatchMethod", List.of(
                    "allMatch",
                    "noneMatch",
                    "anyMatch"
            )),
            new SimpleEntry<>("ArraysClass", List.of(
                    "Arrays"
            )),
            new SimpleEntry<>("StreamMethod", List.of(
                    "stream"
            )),
            new SimpleEntry<>("MatchMethodVar", List.of(
                    "jdVar"
            )),
            new SimpleEntry<>("RightArrow", List.of(
                    "->"
            )),
            new SimpleEntry<>("QuestionMark", List.of(
                    "?"
            )),
            new SimpleEntry<>("Colon", List.of(
                    ":"
            )),
            new SimpleEntry<>("Semicolon", List.of(
                    ";"
            )),
            new SimpleEntry<>("OpeningParenthesis", List.of(
                    "("
            )),
            new SimpleEntry<>("ClosingParenthesis", List.of(
                    ")"
            )),
            new SimpleEntry<>("Period", List.of(
                    "."
            )),
            new SimpleEntry<>("Comma", List.of(
                    ","
            )),
            new SimpleEntry<>("DOUBLE", List.of(
                    "1.0"
            )),
            new SimpleEntry<>("S_INT", List.of(
                    "1"
            )),
            new SimpleEntry<>("TRUE", List.of(
                    "true"
            )),
            new SimpleEntry<>("FALSE", List.of(
                    "false"
            )),
            new SimpleEntry<>("BOOLEAN", List.of(
                    "true",
                    "false"
            )),
            new SimpleEntry<>("NULL", List.of(
                    "null"
            ))
    );

    /**
     * List containing all possible rules of TrattoGrammar
     */
    public static final List<String> RULES = new ArrayList<>(RULES_TOKENS.keySet());

    /**
     * List containing all possible tokens of TrattoGrammar
     */
    public static final List<String> TOKENS = RULES_TOKENS
            .values()
            .stream()
            .flatMap(List::stream)
            .distinct()
            .toList();

    /**
     * Map containing TrattoGrammar tokens as keys and the rules they belong to as values.
     * Only rules that resolve to tokens are considered
     */
    public static final Map<String, List<String>> TOKENS_RULES = RULES_TOKENS
            .entrySet()
            .stream()
            .flatMap(entry -> entry.getValue().stream().map(token -> new SimpleEntry<>(token, entry.getKey())))
            .collect(Collectors.groupingBy(SimpleEntry::getKey, Collectors.mapping(SimpleEntry::getValue, Collectors.toList())));

    /**
     * Map containing TrattoGrammar rules as keys and a representative token of the rules they belong to as values.
     * Only rules that resolve to tokens are considered
     */
    public static final Map<String, String> RULES_REPRESENTATIVE_TOKENS = RULES_TOKENS
            .entrySet()
            .stream()
            .filter(entry -> !entry.getValue().isEmpty())
            .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().get(0)));

    // Three private auxiliary variables to create the map RULES_REPRESENTATIVE_TOKENS_UNIQUE:
    private static final String TO_REMOVE = "TO_REMOVE";
    private static final List<String> tokensFromRules = RULES_TOKENS
            .values()
            .stream()
            .flatMap(List::stream)
            .toList();
    private static final Set<String> duplicateTokens = new HashSet<>();

    /**
     * Map containing TrattoGrammar rules as keys and a representative token of the rules they belong to as values.
     * This map is especially designed to contain the minimum amount of rules possible such that, when obtaining the
     * tokens of the rules via the RULES_TOKENS map, all tokens of the grammar will be obtained. It is also designed
     * such that the representative token associated to each rule is as restrictive as possible. For instance, the
     * representative token of the EqIneqOperator rule is "!=" instead of "==", because in this way, if we find that
     * "!=" is a legal token at a certain position of the oracle, we can also assume that "==" is legal. This is not
     * possible if we use "==" as representative token, because "==" could also be referring to the EqOperator rule.
     * The same thing occurs with the BOOLEAN rule, whose tokens are "true" and "false". We already have the TRUE
     * (token "true") and FALSE (token "false") rules, which are more restrictive, so the BOOLEAN rule is not included
     * in this map.
     */
    public static final Map<String, String> RULES_REPRESENTATIVE_TOKENS_UNIQUE = RULES_TOKENS
            .entrySet()
            .stream()
            .filter(entry -> !entry.getValue().isEmpty())
            .collect(Collectors.toMap(
                    Map.Entry::getKey,
                    entry -> entry
                            .getValue()
                            .stream()
                            .filter(token -> Collections.frequency(tokensFromRules, token) == 1)
                            .findFirst()
                            .orElse(entry.getValue().size() == 1 ? entry.getValue().get(0) : TO_REMOVE)))
            .entrySet()
            .stream()
            .filter(entry -> !entry.getValue().equals(TO_REMOVE))
            .filter(entry -> duplicateTokens.add(entry.getValue()))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

}
