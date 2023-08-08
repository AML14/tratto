package star.tratto.util;

import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * This class provides a collection of static methods for manipulating and
 * evaluating strings, such as removing unnecessary spaces or computing the
 * semantic similarity of two inputs.
 */
public class StringUtils {
    // a suite of NLP tools for pre-processing text inputs
    private static final StanfordCoreNLP stanfordCoreNLP = getStanfordCoreNLP();

    // private constructor to avoid creating an instance of this class
    private StringUtils() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    /**
     * @return a new StanfordCoreNLP object with the necessary properties for
     * preprocessing two strings for semantic comparison. We use the following
     * modifiers:
     *  - tokenize: splits input into tokens (e.g. words, punctuation)
     *  - pos: assigns a part-of-speech to each token
     *  - lemma: converts each word to its base root (e.g. "running" -> "run")
     */
    private static StanfordCoreNLP getStanfordCoreNLP() {
        Properties properties = new Properties();
        properties.setProperty("annotators", "tokenize, pos, lemma");
        return new StanfordCoreNLP(properties);
    }

    /**
     * Remove spaces and add spaces around "instanceof".
     */
    public static String compactExpression(String expression) {
        return expression.replace(" ", "").replace("instanceof", " instanceof ");
    }

    public static String compactExpression(List<String> expressionTokens) {
        return compactExpression(String.join(" ", expressionTokens));
    }

    /**
     * @throws IllegalArgumentException if openingParenthesisIndex is greater than or equal to the size of oracleTokens,
     *                                  or if oracleTokens does not contain the "(" token at the openingParenthesisIndex
     *                                  position
     * @return null if no corresponding closing parenthesis is found, otherwise returns the index (in {@code oracleTokens}) of the corresponding closing parenthesis
     */
    public static Integer getCorrespondingClosingParenthesisIndex(List<String> oracleTokens, int openingParenthesisIndex) {
        if (openingParenthesisIndex >= oracleTokens.size()) {
            throw new IndexOutOfBoundsException("openingParenthesisIndex=" + openingParenthesisIndex + ", oracle tokens list size=" + oracleTokens.size());
        } else if (!oracleTokens.get(openingParenthesisIndex).equals("(")) {
            throw new IllegalArgumentException("The token at the openingParenthesisIndex (" + openingParenthesisIndex + ") is not an opening parenthesis. " +
                    "Token: " + oracleTokens.get(openingParenthesisIndex));
        }

        Integer closingParenthesisIndex = null;
        int openingParenthesisCounter = 1;
        for (int i = openingParenthesisIndex + 1; i < oracleTokens.size(); i++) {
            String token = oracleTokens.get(i);
            if (token.equals("(")) {
                openingParenthesisCounter++;
            } else if (token.equals(")")) {
                openingParenthesisCounter--;
            }
            if (openingParenthesisCounter == 0) {
                closingParenthesisIndex = i;
                break;
            }
        }

        return closingParenthesisIndex;
    }

    /** Returns the indexes of the oracleTokens list where then tokens are found. Empty if tokens is null.
     * All indexes if tokens is empty.
@param oracleTokens list of tokens in the (partial) oracle
     * @param tokens list of tokens to find in the oracle
     * @return the indexes of the oracleTokens list where then tokens are found. Empty if tokens is null.
     * All indexes if tokens is empty 
     */
    public static List<Integer> getIndexesOfTokensInOracleTokens(List<String> oracleTokens, List<String> tokens) {
        List<Integer> indexesOfTokensInOracle = new ArrayList<>();

        if (tokens == null) {
            return indexesOfTokensInOracle;
        } else if (tokens.isEmpty()) {
            return IntStream.rangeClosed(0, oracleTokens.size() - 1).boxed().collect(Collectors.toList());
        }

        for (int i = 0; i < oracleTokens.size(); i++) {
            if (tokens.contains(oracleTokens.get(i))) {
                indexesOfTokensInOracle.add(i);
            }
        }

        return indexesOfTokensInOracle;
    }

    public static String fullyQualifiedClassName(String packageName, String className) {
        if (packageName.isEmpty()) {
            return className;
        }
        return packageName + "." + className;
    }

    public static String fullyQualifiedClassName(Pair<String, String> packageClassPair) {
        return fullyQualifiedClassName(packageClassPair.getValue0(), packageClassPair.getValue1());
    }

    /** Returns removes all non-alphabetic and space characters in a String,
     * and sets all alphabetic characters to lower case.
@return removes all non-alphabetic and space characters in a String,
     * and sets all alphabetic characters to lower case 
     */
    private static String toAllLowerCaseLetters(String s) {
        return s.replaceAll("[^a-zA-Z ]", "").toLowerCase();
    }

    /**
     * Transforms a String of words to a list of the corresponding lemmas. A
     * lemma is a dictionary-defined canonical form of a word. StanfordCoreNLP
     * uses WordNet to determine the canonical forms. For example:
     * <pre>
     *  "running"   ->  "run"
     *  "better"    ->  "good"
     * </pre>
     * When calculating semantic similarity, we use lemmas (rather than words)
     * to avoid treating similar words as entirely separate.
     *
     * @param words lowercase alphabetic characters separated by spaces
     * @return lemma corresponding to words in the original String
     */
    private static List<String> toLemmas(String words) {
        Annotation processedText = new Annotation(words);
        stanfordCoreNLP.annotate(processedText);
        List<CoreLabel> tokens = processedText.get(TokensAnnotation.class);
        return tokens
                .stream()
                .map(t -> t.get(LemmaAnnotation.class))
                .collect(Collectors.toList());
    }

    /**
     * Creates a map from each word to its corresponding frequency in a given
     * list of words.
     *
     * @param words a list of words
     * @return a map of word frequencies, where the keys are unique words and
     * the values are the number of occurrences in {@code words}
     */
    private static Map<String, Integer> getWordFrequencies(List<String> words) {
        Map<String, Integer> wordFrequencies = new HashMap<>();
        for (String word : words) {
            int currentCount = wordFrequencies.getOrDefault(word, 0);
            wordFrequencies.put(word, currentCount + 1);
        }
        return wordFrequencies;
    }

    /**
     * Converts a map of word frequencies to a vector.
     *
     * @param frequencies a map of word frequencies
     * @param words the set of all words to be considered in the vector
     * @return a vector representation of the word frequencies. Each entry
     * corresponds to a different word, where the value of the entry
     * corresponds to the word frequency.
     */
    private static RealVector wordFrequencyToVector(Map<String, Integer> frequencies, TreeSet<String> words) {
        double[] vector = new double[words.size()];
        int i = 0;
        for (String word : words) {
            vector[i] = frequencies.getOrDefault(word, 0);
            i++;
        }
        return new ArrayRealVector(vector);
    }

    /** Returns the words in both sets.
@param set1 a set of words
     * @param set2 a set of words
     * @return the words in both sets 
     */
    private static TreeSet<String> getSetIntersection(Set<String> set1, Set<String> set2) {
        TreeSet<String> intersectionKeys = new TreeSet<>(set1);
        intersectionKeys.retainAll(set2);
        return intersectionKeys;
    }

    /**
     * Computes the cosine similarity of two vectors. Imitates behavior of
     * {@link RealVector#cosine} without throwing exceptions. If the norm of
     * either vector is 0.0, then the method returns 0.0.
     *
     * @param vector1 a vector
     * @param vector2 a vector
     * @return the cosine similarity of the two vectors
     */
    private static double getCosineSimilarity(RealVector vector1, RealVector vector2) {
        double denominator = vector1.getNorm() * vector2.getNorm();
        if (denominator == 0.0) {
          return 0.0;
        }
        return vector1.dotProduct(vector2) / denominator;
    }

    /**
     * Computes the cosine similarity from two lists of lemmas.
     *
     * @param lemmas1 list of lemmas
     * @param lemmas2 list of lemmas
     * @return the cosine similarity (double between 0.0 and 1.0)
     */
    private static double lemmasToCosineSimilarity(List<String> lemmas1, List<String> lemmas2) {
        Map<String, Integer> wordsFreq1 = getWordFrequencies(lemmas1);
        Map<String, Integer> wordsFreq2 = getWordFrequencies(lemmas2);
        TreeSet<String> intersectionKeys = getSetIntersection(wordsFreq1.keySet(), wordsFreq2.keySet());
        RealVector wordVector1 = wordFrequencyToVector(wordsFreq1, intersectionKeys);
        RealVector wordVector2 = wordFrequencyToVector(wordsFreq2, intersectionKeys);
        return getCosineSimilarity(wordVector1, wordVector2);
    }

    /**
     * Computes the semantic similarity of two strings by the cosine
     * similarity of their word frequency vectors. We define a word as a
     * sequence of alphabetic characters separated by a space. We convert
     * words into their canonical forms using the StanfordCoreNLP toolkit.
     *
     * @param s1 a string of words
     * @param s2 a string of words
     * @return the cosine similarity of the two strings represented as a
     * double between 0.0 and 1.0
     */
    public static double semanticSimilarity(String s1, String s2) {
        s1 = toAllLowerCaseLetters(s1);
        s2 = toAllLowerCaseLetters(s2);
        List<String> lemmas1 = toLemmas(s1);
        List<String> lemmas2 = toLemmas(s2);
        return lemmasToCosineSimilarity(lemmas1, lemmas2);
    }
}
