package star.tratto.dataset;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.javatuples.Triplet;
import star.tratto.oraclegrammar.custom.Parser;
import star.tratto.oraclegrammar.trattoGrammar.Oracle;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static star.tratto.dataset.ExcelManager.getFirstSheet;
import static star.tratto.oraclegrammar.custom.Splitter.split;
import static star.tratto.token.TokenSuggester.*;
import static star.tratto.util.StringUtils.compactExpression;

public class TokensDataset {

    private static final Parser parser = Parser.getInstance();
    public static String ORACLES_DATASET_PATH = "src/main/resources/oracles-dataset.xlsx";
    public static String TOKENS_DATASET_PATH = "src/main/resources/tokens-dataset.xlsx";
    private static int tokenIndex = 0;

    public static void main(String[] args) throws IOException {
        // Read oracles dataset (Excel file) from src/main/resources/oracles-dataset.xlsx
        Sheet oraclesDatasetSheet = getFirstSheet(ORACLES_DATASET_PATH);

        // Create tokens dataset Sheet
        Sheet tokensDatasetSheet = new XSSFWorkbook().createSheet("Sheet1");

        // Set tokens dataset header
        Row tokensDatasetHeaderRow = tokensDatasetSheet.createRow(0);
        for (String tokensDatasetHeaderRowCellName : TokenDatapoint.ATTRIBUTES) {
            Cell headerRowCell = tokensDatasetHeaderRow.createCell(TokenDatapoint.ATTRIBUTES.indexOf(tokensDatasetHeaderRowCellName));
            headerRowCell.setCellValue(tokensDatasetHeaderRowCellName);
        }

        for (Row oraclesDatasetRow : oraclesDatasetSheet) {
            if (oraclesDatasetRow.getRowNum() == 0) { // Skip header row
                continue;
            }
            for (TokenDatapoint tokenDatapoint : oracleDatapointToTokenDatapoints(new OracleDatapoint(oraclesDatasetRow))) {
                int lastRowNum = tokensDatasetSheet.getLastRowNum();
                Row tokensDatasetRow = tokensDatasetSheet.createRow(lastRowNum + 1); // Append row at the end
                tokenDatapoint.updateRow(tokensDatasetRow);
            }

        }

        // End. Write contents of tokens dataset workbook to src/main/resources/tokens-dataset.xlsx and close resources
        FileOutputStream outputStream = new FileOutputStream(TOKENS_DATASET_PATH);
        tokensDatasetSheet.getWorkbook().write(outputStream);

        oraclesDatasetSheet.getWorkbook().close();
        tokensDatasetSheet.getWorkbook().close();
        outputStream.close();
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
     * @param oracleDatapoint OracleDatapoint from which the oracleSoFar and nextOracleToken come from.
     * @param oracleSoFarTokens Tokens of partial TrattoGrammar expression for which next legal tokens will be
     *                          computed. For each next legal token, a TokenDatapoint will be created.
     * @param nextOracleToken Actual token that goes next after oracleSoFar. This is needed to know the
     *                        label of each TokenDatapoint created (true if nextOracleToken is the token of that
     *                        TokenDatapoint, false otherwise).
     */
    private static List<TokenDatapoint> oracleSoFarAndTokenToTokenDatapoints(OracleDatapoint oracleDatapoint, List<String> oracleSoFarTokens, String nextOracleToken) {
        // Compute next legal tokens
        List<Triplet<String, String, List<String>>> nextLegalTokensWithContext = getNextLegalTokensWithContextPlusInfo(oracleSoFarTokens, oracleDatapoint);

        List<TokenDatapoint> tokenDatapoints = new ArrayList<>();

        // Create a new TokenDatapoint for each next legal token and add it to the list
        for (Triplet<String, String, List<String>> legalTokenWithContext : nextLegalTokensWithContext) {
            String legalToken = legalTokenWithContext.getValue0();
            String legalTokenClass = legalTokenWithContext.getValue1();
            List<String> legalTokenInfo = legalTokenWithContext.getValue2();
            Boolean label = legalToken.equals(nextOracleToken);
            TokenDatapoint tokenDatapoint = new TokenDatapoint(tokenIndex++, label, oracleDatapoint, compactExpression(oracleSoFarTokens), legalToken, legalTokenClass, legalTokenInfo);
            tokenDatapoints.add(tokenDatapoint);
        }
        return tokenDatapoints;
    }
}
