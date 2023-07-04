package star.tratto;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import star.tratto.data.OracleDP2TokenDPs;
import star.tratto.data.TokenDPType;
import star.tratto.identifiers.path.Path;
import star.tratto.token.TokenSuggesterTest;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static star.tratto.TestUtilities.readOraclesFromExternalFiles;

public class E2ETests {

    /**
     * This test checks that the whole dataset generation pipeline works as expected.
     * In particular, it proceeds as follows:
     * <ul>
     *     <li>It generates the oracles dataset based on the projects source and the
     *     Jdoctor conditions.</li>
     *     <li>It generates the tokens dataset based on the oracles dataset.</li>
     *     <li>For every oracle in the oracles dataset, it checks that they can be
     *     reconstructed from the tokens dataset. If not, the test fails.</li>
     * </ul>
     */
    @Test
//    @Disabled
    public void datasetsE2ETest() throws IOException {
        // Config for E2E test
        OracleDP2TokenDPs.CRASH_WRONG_ORACLE = false; // TODO: Set to true after crashing oracles are fixed
        TokensDataset.ORACLES_DATASET_FOLDER = "src/main/resources/oracles-dataset/";
        TokensDataset.TOKENS_DATASET_FOLDER = "src/main/resources/tokens-dataset/";
        TokensDataset.DATASET_TYPE = TokenDPType.TOKEN_VALUE; // To reduce the size of the generated dataset

        // Generate the datasets (assertions done in TokensDataset.main)
        OraclesDataset.main(new String[] {});
        TokensDataset.main(new String[] {});

        // Delete datasets and recreate folders
        File oraclesDatasetFolder = new File(Path.ORACLES_DATASET.getValue());
        File tokensDatasetFolder = new File(TokensDataset.TOKENS_DATASET_FOLDER);
        FileUtils.deleteDirectory(oraclesDatasetFolder);
        FileUtils.deleteDirectory(tokensDatasetFolder);
        oraclesDatasetFolder.mkdir();
        tokensDatasetFolder.mkdir();
    }

    /**
     * This test is to check that, for all TrattoGrammar expressions contained in
     * the resources files, the TokenSuggester always returns as next legal tokens
     * the tokens that actually go next in the partial TrattoGrammar expression.
     * For instance, given the expression "occurrences<0;", this test checks that
     * after the partialExpression "", the token "someVarOrClassOrFieldOrMethod"
     * is returned, after "occurrences", the token "<" is returned, after
     * "occurrences<", the token "0" is returned, and after "occurrences<0", the
     * token ";" is returned.
     */
    @Test
//    @Disabled
    public void tokenSuggesterE2ETest() {
        List<String> stringOracles = readOraclesFromExternalFiles();
        TokenSuggesterTest.getNextLegalTokensAuxTest(stringOracles);
    }
}
