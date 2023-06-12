package star.tratto.util;

import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StringUtils {
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
}
