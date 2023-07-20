package star.tratto.data;

import org.javatuples.Triplet;
import star.tratto.oraclegrammar.custom.Parser;
import star.tratto.oraclegrammar.trattoGrammar.Oracle;

import java.util.ArrayList;
import java.util.List;

import static star.tratto.oraclegrammar.custom.Splitter.split;
import static star.tratto.token.TokenSuggester.getNextLegalTokensWithContextPlusInfo;
import static star.tratto.util.StringUtils.compactExpression;

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
    private static int tokenIndex = 0;
    private static int negativeSamples = 0;
    private static int positiveSamples = 0;
    public static boolean CRASH_WRONG_ORACLE = false; // TODO: make it configurable

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
    public static List<TokenDatapoint> oracleDatapointToTokenDatapoints(OracleDatapoint oracleDatapoint, TokenDPType tokenDPType) {
        // Split oracle into tokens
        String stringOracle = oracleDatapoint.getOracle();
        Oracle oracle = parser.getOracle(stringOracle);
        List<String> oracleTokens = split(oracle);
        List<String> oracleSoFarTokens = new ArrayList<>();
        List<String> tokenClassesSoFar = new ArrayList<>();

        List<TokenDatapoint> tokenDatapoints = oracleSoFarAndTokenToTokenDatapoints(oracleDatapoint, oracleSoFarTokens, tokenClassesSoFar, oracleTokens.get(0), tokenDPType);
        for (int i = 0; i < oracleTokens.size() - 1; i++) { // Skip last token because it is not followed by anything
            String nextOracleToken = oracleTokens.get(i + 1);
            tokenDatapoints.addAll(oracleSoFarAndTokenToTokenDatapoints(oracleDatapoint, oracleSoFarTokens, tokenClassesSoFar, nextOracleToken, tokenDPType));
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
     * @param tokenClassesSoFar token classes of the passed values in oracleSoFarTokens. The sizes of both lists
     *                          must be the same. <strong>ATTENTION: at dataset-generation time, this list will
     *                          be modified by this method, while at oracle-generation time, this list will need
     *                          to be manually modified afterwards, based on the token returned by the neural
     *                          module.</strong>
     * @param nextOracleToken actual token that goes next after oracleSoFar. This is needed to know the
     *                        label of each TokenDatapoint created (true if nextOracleToken is the token of that
     *                        TokenDatapoint, false otherwise). This may be set to the empty string "" at
     *                        oracle-generation time (i.e., the next token is not known a priori).
     * @param tokenDPType type of TokenDatapoints to generate (token, class or value).
     */
    public static List<TokenDatapoint> oracleSoFarAndTokenToTokenDatapoints(OracleDatapoint oracleDatapoint, List<String> oracleSoFarTokens, List<String> tokenClassesSoFar, String nextOracleToken, TokenDPType tokenDPType) {
        assert oracleSoFarTokens.size() == tokenClassesSoFar.size();

        // Create copies of oracleSoFarTokens and tokenClassesSoFar since they may be modified
        List<String> oracleSoFarTokensCopy = new ArrayList<>(oracleSoFarTokens);
        List<String> tokenClassesSoFarCopy = new ArrayList<>(tokenClassesSoFar);

        // Compute next legal tokens
        List<Triplet<String, String, List<String>>> nextLegalTokensWithContext = getNextLegalTokensWithContextPlusInfo(oracleSoFarTokensCopy, oracleDatapoint);
        List<TokenDatapoint> tokenDatapoints = new ArrayList<>();

        // Create a new TokenDatapoint for each next legal token and add it to the list
        boolean nextTokenActuallyLegal = false;
        String nextOracleTokenClass = null;
        List<String> addedTokenClasses = new ArrayList<>();
        for (Triplet<String, String, List<String>> legalTokenWithContext : nextLegalTokensWithContext) {
            String legalToken = legalTokenWithContext.getValue0();
            String legalTokenClass = legalTokenWithContext.getValue1();
            List<String> legalTokenInfo = legalTokenWithContext.getValue2();

            boolean label = legalToken.equals(nextOracleToken);
            if (label) {
                nextTokenActuallyLegal = true;
                nextOracleTokenClass = legalTokenClass;
                positiveSamples++;
            }

            // For tokens or token-values dataset, add all tokens. For token-classes dataset, add only one token per class
            // (except for right class, where one with label=true and one with label=false are added)
            if (!tokenDPType.equals(TokenDPType.TOKEN_CLASS) || (label || !addedTokenClasses.contains(legalTokenClass))) {
                TokenDatapoint tokenDatapoint = new TokenDatapoint(tokenIndex++, label, oracleDatapoint, compactExpression(oracleSoFarTokensCopy), tokenClassesSoFarCopy, legalToken, legalTokenClass, legalTokenInfo);
                tokenDatapoints.add(tokenDatapoint);
                addedTokenClasses.add(legalTokenClass);
                if (!label) {
                    negativeSamples++;
                }
            }
        }

        // If token-classes or token-values, we need to remove some negative token datapoints and update negativeSamples accordingly
        if (!tokenDPType.equals(TokenDPType.TOKEN)) {
            int oldSize = tokenDatapoints.size();
            String finalNextOracleTokenClass = nextOracleTokenClass;
            tokenDatapoints.removeIf(tdp ->
                    (tokenDPType.equals(TokenDPType.TOKEN_VALUE) && !tdp.getTokenClass().equals(finalNextOracleTokenClass)) ||
                            (tokenDPType.equals(TokenDPType.TOKEN_CLASS) && !tdp.getLabel() && tdp.getTokenClass().equals(finalNextOracleTokenClass))
            );
            negativeSamples -= oldSize - tokenDatapoints.size();
        }

        // Update original oracleSoFarTokens and tokenClassesSoFar for next iteration
        if (!nextOracleToken.equals("")) {
            assert nextOracleTokenClass != null;
            oracleSoFarTokens.add(nextOracleToken);
            tokenClassesSoFar.add(nextOracleTokenClass);
        } else {
            assert nextOracleTokenClass == null;
        }

        assertTokenLegal(nextTokenActuallyLegal, nextOracleToken, oracleSoFarTokensCopy);
        return tokenDatapoints;
    }

    private static void assertTokenLegal(boolean nextTokenActuallyLegal, String token, List<String> oracleSoFarTokens) {
        if (!nextTokenActuallyLegal && !token.equals("")) { // If token is "", this is oracle-generation time, so the next token is not known a priori.
            String message = "Token '" + token + "' is not legal after partial oracle '" + compactExpression(oracleSoFarTokens) + "'";
            if (CRASH_WRONG_ORACLE) {
                throw new RuntimeException(message);
            } else {
                throw new MissingTokenException(message);
            }
        }
    }

    public static void resetSampleIndexes() {
        negativeSamples = 0;
        positiveSamples = 0;
    }

    public static int getNegativeSamples() {
        return negativeSamples;
    }

    public static int getPositiveSamples() {
        return positiveSamples;
    }
}
