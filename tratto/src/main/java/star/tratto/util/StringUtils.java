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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StringUtils {
    private static StanfordCoreNLP stanfordCoreNLP = getStanfordCoreNLP();

    public static StanfordCoreNLP getStanfordCoreNLP() {
        if (stanfordCoreNLP == null) {
            // Initialize the CoreNLP pipeline for lemmatization
            Properties props = new Properties();
            props.setProperty("annotators", "tokenize, pos, lemma");
            stanfordCoreNLP = new StanfordCoreNLP(props);
        }
        return stanfordCoreNLP;
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
     * @return null if no corresponding closing parenthesis is found, otherwise returns the index of the corresponding closing parenthesis
     */
    public static Integer getCorrespondingClosingParenthesisIndex(List<String> oracleTokens, int openingParenthesisIndex) {
        if (openingParenthesisIndex >= oracleTokens.size()) {
            throw new IllegalArgumentException("The openingParenthesisIndex (" + openingParenthesisIndex + ") must be strictly less than " +
                    "the size of the list (" + oracleTokens.size() + ")");
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

    /**
     * @param oracleTokens list of tokens in the (partial) oracle
     * @param tokens list of tokens to find in the oracle
     * @return the indexes of the oracleTokens list where then tokens are found. Empty if tokens is null.
     * All indexes if tokens is empty.
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
        if (packageClassPair.getValue0().isEmpty()) {
            return packageClassPair.getValue1();
        }
        return packageClassPair.getValue0() + "." + packageClassPair.getValue1();
    }

    /**
     * Computes the semantic similarity of two strings by the cosine
     * similarity of word frequencies in the input.
     *
     * @param s1 a string
     * @param s2 a string
     * @return the cosine similarity of the two strings represented by a
     * double between 0.0 and 1.0
     */
    public static double semanticSimilarity(String s1, String s2) {
        s1 = s1.replaceAll("[^a-zA-Z ]", "").toLowerCase();
        s2 = s2.replaceAll("[^a-zA-Z ]", "").toLowerCase();
        // Convert the words into lemmas.
        List<String> tokens1 = lemmatize(stanfordCoreNLP, s1);
        List<String> tokens2 = lemmatize(stanfordCoreNLP, s2);
        // Compute the cosine similarity of the two vectors of word frequencies.
        return cosineSimilarity(tokens1, tokens2);
    }

    /**
     * Converts a string into a list of lemmas using the CoreNLP library.
     */
    private static List<String> lemmatize(StanfordCoreNLP pipeline, String documentText) {
        List<String> lemmas = new ArrayList<>();
        Annotation document = new Annotation(documentText);
        pipeline.annotate(document);
        List<CoreLabel> tokens = document.get(TokensAnnotation.class);
        for (CoreLabel token : tokens) {
            String lemma = token.get(LemmaAnnotation.class);
            lemmas.add(lemma);
        }
        return lemmas;
    }

    /**
     * Computes the cosine similarity of two lists of words.
     *
     * @param list1 list of strings
     * @param list2 list of strings
     * @return the cosine similarity (double between 0 and 1)
     */
    private static double cosineSimilarity(List<String> list1, List<String> list2) {
        Map<String, Integer> map1 = wordFrequencies(list1);
        Map<String, Integer> map2 = wordFrequencies(list2);
        Set<String> intersection = new HashSet<>(map1.keySet());
        intersection.retainAll(map2.keySet());
        RealVector vector1 = toRealVector(map1, intersection);
        RealVector vector2 = toRealVector(map2, intersection);
        double denominator = vector1.getNorm() * vector2.getNorm();
        return denominator > 0.0 ? vector1.dotProduct(vector2) / (denominator) : 0.0;
    }

    /**
     * Computes the frequency of each string in a list of strings.
     *
     * @param words a list of strings
     * @return a map of word frequencies, where the keys are strings and the
     * values are the number of occurrences
     */
    private static Map<String, Integer> wordFrequencies(List<String> words) {
        Map<String, Integer> frequencies = new HashMap<>();
        for (String word : words) {
            frequencies.put(word, frequencies.getOrDefault(word, 0) + 1);
        }
        return frequencies;
    }

    /**
     * Converts a map of word frequencies to a vector.
     *
     * @param map a map of word frequencies
     * @param words the set of all possible words
     * @return a vector representation of the word frequencies
     */
    private static RealVector toRealVector(Map<String, Integer> map, Set<String> words) {
        double[] vector = new double[words.size()];
        int i = 0;
        for (String word : words) {
            vector[i++] = map.getOrDefault(word, 0);
        }
        return new ArrayRealVector(vector);
    }
}
