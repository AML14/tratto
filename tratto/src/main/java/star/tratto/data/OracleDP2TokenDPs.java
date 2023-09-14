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
    private static List<String> currentTokenClassesSoFar;
    private static long tokenIndex = 0;
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
        currentTokenClassesSoFar = new ArrayList<>();

        // TokenDatapoints for when oracleSoFarTokens = []:
        List<TokenDatapoint> tokenDatapoints = oracleSoFarAndTokenToTokenDatapoints(oracleDatapoint, oracleSoFarTokens, oracleTokens.get(0), tokenDPType);

        // Skip last token because it is not followed by anything
        for (int i = 0; i < oracleTokens.size() - 1; i++) {
            String oracleToken = oracleTokens.get(i);
            String nextOracleToken = oracleTokens.get(i + 1);

            // Create partial TrattoGrammar expression
            oracleSoFarTokens.add(oracleToken);

            // Add TokenDatapoints related to current token
            tokenDatapoints.addAll(oracleSoFarAndTokenToTokenDatapoints(oracleDatapoint, oracleSoFarTokens, nextOracleToken, tokenDPType));
        }
        return tokenDatapoints;
    }

    /**
     * Transform an oracleSoFar + nextOracleToken referring to an OracleDatapoint into multiple TokenDatapoints.
     * @param oracleDatapoint OracleDatapoint from which the oracleSoFar and nextOracleToken come from
     * @param oracleSoFarTokens tokens of partial TrattoGrammar expression for which next legal tokens will be
     *                          computed. For each next legal token, a TokenDatapoint will be created.
     * @param nextOracleToken actual token that goes next after oracleSoFar. This is needed to know the
     *                        label of each TokenDatapoint created (true if nextOracleToken is the token of that
     *                        TokenDatapoint, false otherwise). This may be set to the empty string "" at
     *                        oracle-generation time (i.e., the next token is not known a priori).
     */
    public static List<TokenDatapoint> oracleSoFarAndTokenToTokenDatapoints(OracleDatapoint oracleDatapoint, List<String> oracleSoFarTokens, String nextOracleToken, TokenDPType tokenDPType) {
        // Compute next legal tokens
        List<Triplet<String, String, List<String>>> nextLegalTokensWithContext = getNextLegalTokensWithContextPlusInfo(oracleSoFarTokens, oracleDatapoint);
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
                TokenDatapoint tokenDatapoint = new TokenDatapoint(tokenIndex++, label, oracleDatapoint, compactExpression(oracleSoFarTokens), legalToken, legalTokenClass, legalTokenInfo);
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

        // Update tokenClassesSoFar for all tokenDatapoints and update currentTokenClassesSoFar for next iteration
        List<String> tokenClassesSoFar = new ArrayList<>(currentTokenClassesSoFar);
        tokenDatapoints.forEach(tdp -> tdp.setTokenClassesSoFar(new ArrayList<>(tokenClassesSoFar)));
        currentTokenClassesSoFar.add(nextOracleTokenClass);

        assertTokenLegal(nextTokenActuallyLegal, nextOracleToken, oracleSoFarTokens);
        return tokenDatapoints;
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
