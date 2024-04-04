package star.tratto.data;

import star.tratto.data.records.EligibleToken;
import star.tratto.oraclegrammar.custom.Parser;
import star.tratto.oraclegrammar.trattoGrammar.Oracle;

import java.util.ArrayList;
import java.util.List;

import static star.tratto.oraclegrammar.custom.Splitter.split;
import static star.tratto.token.TokenSuggester.getNextLegalTokensWithContextPlusInfo;
import static star.tratto.util.StringUtils.compactExpression;
import static star.tratto.util.javaparser.JavaParserUtils.getImports;

/**
 * Handle conversions from OracleDatapoints to TokenDatapoints. These conversions may be performed
 * when generating the datasets or when generating oracles.
 * <br>
 * When generating the datasets, this class also keeps track of the number of positive and negative
 * samples generated. In addition, it has a configuration parameter ({@code CRASH_WRONG_ORACLE}) to
 * decide whether to crash when an oracle is not valid (i.e., when it cannot be reconstructed token
 * by token according to the grammar and the context restrictions).
 */
public class OracleDP2TokenDPs {

    private static final Parser parser = Parser.getInstance();
    private static long tokenIndex = 0;
    public static boolean CRASH_WRONG_ORACLE = true; // TODO: make it configurable

    /**
     * Transform the contents of an OracleDatapoint (obtained from a row from oracles dataset) into multiple
     * TokenDatapoints (which will be transformed into rows in tokens dataset), in the following way:
     * <ol>
     *     <li>Split oracle into tokens.</li>
     *     <li>For each token, create partial TrattoGrammar expression.</li>
     *     <li>For each partial expression, compute next legal tokens.</li>
     *     <li>For each next legal token, create a new TokenDatapoint. If the next legal token is the one that
     *     actually goes next, label is true, otherwise it is false.</li>
     * </ol>
     */
    public static List<TokenDatapoint> oracleDatapointToTokenDatapoints(OracleDatapoint oracleDatapoint) {
        // Split oracle into tokens
        String stringOracle = oracleDatapoint.getOracle();
        Oracle oracle = parser.getOracle(stringOracle);
        List<String> oracleTokens = split(oracle);
        List<String> oracleSoFarTokens = new ArrayList<>();
        List<TokenDatapoint> tokenDatapoints = new ArrayList<>();

        for (String nextOracleToken : oracleTokens) { // Skip last token because it is not followed by anything
            tokenDatapoints.add(oracleSoFarAndTokenToTokenDatapoint(oracleDatapoint, oracleSoFarTokens, nextOracleToken));
            oracleSoFarTokens.add(nextOracleToken);
        }
        return tokenDatapoints;
    }

    /**
     * Transform an oracleSoFar + nextOracleToken referring to an OracleDatapoint into multiple TokenDatapoints.
     * @param oracleDatapoint OracleDatapoint from which the oracleSoFar and nextOracleToken come from
     * @param oracleSoFarTokens tokens of partial TrattoGrammar expression for which next legal tokens will be
     *                          computed. For each next legal token, a TokenDatapoint will be created.
     *                          <strong>ATTENTION: at dataset-generation time, this list will be modified by
     *                          this method, while at oracle-generation time, this list will need to be manually
     *                          modified afterwards, based on the token returned by the neural module.</strong>
     * @param nextOracleToken actual token that goes next after oracleSoFar. This is needed to know the
     *                        label of each TokenDatapoint created (true if nextOracleToken is the token of that
     *                        TokenDatapoint, false otherwise). This may be set to the empty string "" at
     *                        oracle-generation time (i.e., the next token is not known a priori).
     */
    public static TokenDatapoint oracleSoFarAndTokenToTokenDatapoint(OracleDatapoint oracleDatapoint, List<String> oracleSoFarTokens, String nextOracleToken) {
        List<String> oracleSoFarTokensCopy = new ArrayList<>(oracleSoFarTokens);
        String oraclePackage = oracleDatapoint.getPackageName();
        List<EligibleToken> eligibleTokens = getNextLegalTokensWithContextPlusInfo(oracleSoFarTokensCopy, oracleDatapoint)
                .stream()
                .map(t -> new EligibleToken(t.getValue0(), t.getValue1(), t.getValue2()))
                .filter(t -> filterClassTokens(t, oracleDatapoint))
                // Sort eligible tokens so that classes are at the end of the list, while keeping classes from the oracle package at the beginning
                .sorted((t1, t2) -> {
                    if (t1.tokenClass().equals("Class") && t2.tokenClass().equals("Class")) {
                        if (t1.tokenInfo().get(0).equals(oraclePackage)) {
                            return -1;
                        } else if (t2.tokenInfo().get(0).equals(oraclePackage)) {
                            return 1;
                        } else {
                            return 0;
                        }
                    } else if (t1.tokenClass().equals("Class")) {
                        return 1;
                    } else if (t2.tokenClass().equals("Class")) {
                        return -1;
                    } else {
                        return 0;
                    }
                })
                .toList();
        assertTokenLegal(eligibleTokens.stream().map(EligibleToken::token).toList().contains(nextOracleToken), nextOracleToken, oracleSoFarTokensCopy);
        return new TokenDatapoint(tokenIndex++, oracleDatapoint, compactExpression(oracleSoFarTokensCopy), eligibleTokens, nextOracleToken);
    }

    private static void assertTokenLegal(boolean nextTokenActuallyLegal, String token, List<String> oracleSoFarTokens) {
        // If token is "", this is oracle-generation time, so the next token is not known a priori.
        if (!nextTokenActuallyLegal && !token.equals("")) {
            String message = "Token '" + token + "' is not legal after partial oracle '" + compactExpression(oracleSoFarTokens) + "'";
            if (CRASH_WRONG_ORACLE) {
                throw new RuntimeException(message);
            } else {
                throw new MissingTokenException(message);
            }
        }
    }

    /**
     * For class tokens, keep only those that fulfill one of the following conditions:
     * <ol>
     *     <li>They contain the string "Utils" in their name.</li>
     *     <li>Their package is the same as the oracle datapoint.</li>
     *     <li>They are imported in the class source code of the oracle datapoint.</li>
     * </ol>
     */
    private static boolean filterClassTokens(EligibleToken token, OracleDatapoint oracleDatapoint) {
        if (!token.tokenClass().equals("Class")) {
            return true;
        }

        String tokenPackage = token.tokenInfo().get(0);
        String tokenClass = token.tokenInfo().get(1);
        String fqTokenClass = tokenPackage + "." + tokenClass;
        String oraclePackage = oracleDatapoint.getPackageName();
        String oracleClassSource = oracleDatapoint.getClassSourceCode();
        List<String> oracleClassImports = getImports(oracleClassSource);

        return tokenClass.contains("Utils") || tokenPackage.equals(oraclePackage) || oracleClassImports.contains(fqTokenClass);
    }

    public static long getTokenIndex() {
        return tokenIndex;
    }
}
