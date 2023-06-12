package star.tratto.dataset.tokens;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.javatuples.Triplet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import star.tratto.dataset.oracles.OracleDatapoint;
import star.tratto.oraclegrammar.custom.Parser;
import star.tratto.oraclegrammar.trattoGrammar.Oracle;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static star.tratto.oraclegrammar.custom.Splitter.split;
import static star.tratto.token.TokenSuggester.getNextLegalTokensWithContextPlusInfo;
import static star.tratto.util.StringUtils.compactExpression;

public class TokensDataset {

    private static final Logger logger = LoggerFactory.getLogger(TokensDataset.class);

    private static final Parser parser = Parser.getInstance();
    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static String ORACLES_DATASET_FOLDER = "src/main/resources/oracles-dataset/";
    public static String TOKENS_DATASET_FOLDER = "src/main/resources/tokens-dataset/";
    private static int tokenIndex = 0;
    private static int fileIndex = 0;
    private static int negativeSamples = 0;
    private static int positiveSamples = 0;
    private static final boolean crashOnWrongOracle = false;

    public static void main(String[] args) throws IOException {
        File tokensDatasetFolder = new File(TOKENS_DATASET_FOLDER);
        FileUtils.deleteDirectory(tokensDatasetFolder);
        tokensDatasetFolder.mkdir();
        File[] oraclesDatasetFiles = new File(ORACLES_DATASET_FOLDER).listFiles();
        for (File oraclesDatasetFile : oraclesDatasetFiles) { // Assume that only dataset files are in the folder
            logger.info("------------------------------------------------------------");
            logger.info("Processing file: {}", oraclesDatasetFile.getName());
            logger.info("------------------------------------------------------------");
            File tokensDatasetFile = new File(TOKENS_DATASET_FOLDER + fileIndex++ + "_" + oraclesDatasetFile.getName());
            tokensDatasetFile.delete();
            FileOutputStream tokensDatasetOutputStream = new FileOutputStream(tokensDatasetFile, true);

            List<Map> rawOracleDatapoints = objectMapper.readValue(oraclesDatasetFile, List.class);
            for (Map rawOracleDatapoint : rawOracleDatapoints) {
                OracleDatapoint oracleDatapoint = new OracleDatapoint(rawOracleDatapoint);
                logger.info("Processing oracle: {}", oracleDatapoint.getOracle());
                List<TokenDatapoint> tokenDatapoints;
                try {
                    tokenDatapoints = oracleDatapointToTokenDatapoints(oracleDatapoint);
                } catch (MissingTokenException e) {
                    logger.error(e.getMessage());
                    continue;
                }

                for (TokenDatapoint tokenDatapoint : tokenDatapoints) {
                    if (tokensDatasetFile.length() == 0) {
                        tokensDatasetOutputStream.write("[".getBytes());
                    } else if (tokensDatasetFile.length() / 1000 / 1000 > 48) { // Limit file size to 50 MB
                        tokensDatasetOutputStream.write("]".getBytes());
                        tokensDatasetOutputStream.close();
                        tokensDatasetFile = new File(TOKENS_DATASET_FOLDER + fileIndex++ + "_" + oraclesDatasetFile.getName());
                        tokensDatasetFile.delete();
                        tokensDatasetOutputStream = new FileOutputStream(tokensDatasetFile, true);
                        tokensDatasetOutputStream.write("[".getBytes());
                    } else {
                        tokensDatasetOutputStream.write(",".getBytes());
                    }
                    tokensDatasetOutputStream.write(objectMapper.writeValueAsBytes(tokenDatapoint));
                }
            }
            tokensDatasetOutputStream.write("]".getBytes());
            tokensDatasetOutputStream.close();
        }

        logger.info("------------------------------------------------------------");
        logger.info("Finished generating tokens dataset.");
        logger.info("Total samples: {}", positiveSamples + negativeSamples);
        logger.info("Total positive samples: {}", positiveSamples);
        logger.info("Total negative samples: {}", negativeSamples);
        logger.info("------------------------------------------------------------");
    }

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
    private static List<TokenDatapoint> oracleDatapointToTokenDatapoints(OracleDatapoint oracleDatapoint) {
        // Split oracle into tokens
        String stringOracle = oracleDatapoint.getOracle();
        Oracle oracle = parser.getOracle(stringOracle);
        List<String> oracleTokens = split(oracle);
        List<String> oracleSoFarTokens = new ArrayList<>();

        // TokenDatapoints for when oracleSoFarTokens = []:
        List<TokenDatapoint> tokenDatapoints = oracleSoFarAndTokenToTokenDatapoints(oracleDatapoint, oracleSoFarTokens, oracleTokens.get(0));

        for (int i = 0; i < oracleTokens.size() - 1; i++) { // Skip last token because it is not followed by anything
            String oracleToken = oracleTokens.get(i);
            String nextOracleToken = oracleTokens.get(i + 1);

            // Create partial TrattoGrammar expression
            oracleSoFarTokens.add(oracleToken);

            // Add TokenDatapoints related to current token
            tokenDatapoints.addAll(oracleSoFarAndTokenToTokenDatapoints(oracleDatapoint, oracleSoFarTokens, nextOracleToken));
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
     *                        TokenDatapoint, false otherwise).
     */
    private static List<TokenDatapoint> oracleSoFarAndTokenToTokenDatapoints(OracleDatapoint oracleDatapoint, List<String> oracleSoFarTokens, String nextOracleToken) {
        // Compute next legal tokens
        List<Triplet<String, String, List<String>>> nextLegalTokensWithContext = getNextLegalTokensWithContextPlusInfo(oracleSoFarTokens, oracleDatapoint);

        List<TokenDatapoint> tokenDatapoints = new ArrayList<>();

        // Create a new TokenDatapoint for each next legal token and add it to the list
        boolean nextTokenActuallyLegal = false;
        for (Triplet<String, String, List<String>> legalTokenWithContext : nextLegalTokensWithContext) {
            String legalToken = legalTokenWithContext.getValue0();
            String legalTokenClass = legalTokenWithContext.getValue1();
            List<String> legalTokenInfo = legalTokenWithContext.getValue2();
            Boolean label = legalToken.equals(nextOracleToken);
            TokenDatapoint tokenDatapoint = new TokenDatapoint(tokenIndex++, label, oracleDatapoint, compactExpression(oracleSoFarTokens), legalToken, legalTokenClass, legalTokenInfo);
            tokenDatapoints.add(tokenDatapoint);
            if (label) {
                nextTokenActuallyLegal = true;
                positiveSamples++;
            } else {
                negativeSamples++;
            }
        }
        assertTokenLegal(nextTokenActuallyLegal, nextOracleToken, oracleSoFarTokens);
        return tokenDatapoints;
    }

    private static void assertTokenLegal(boolean nextTokenActuallyLegal, String token, List<String> oracleSoFarTokens) {
        if (!nextTokenActuallyLegal) {
            String message = "Token '" + token + "' is not legal after partial oracle '" + compactExpression(oracleSoFarTokens) + "'";
            if (crashOnWrongOracle) {
                throw new RuntimeException(message);
            } else {
                throw new MissingTokenException(message);
            }
        }
    }

    public static class MissingTokenException extends RuntimeException {
        public MissingTokenException(String message) {
            super(message);
        }
    }
}
