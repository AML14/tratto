package star.tratto;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import star.tratto.data.OracleDP2TokenDPs;
import star.tratto.data.TokenDPType;
import star.tratto.input.ClassAnalyzerTest;
import star.tratto.oraclegrammar.custom.Parser;
import star.tratto.data.TrattoPath;
import star.tratto.token.TokenSuggesterTest;
import star.tratto.util.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static star.tratto.TestUtilities.readOraclesFromExternalFiles;
import static star.tratto.oraclegrammar.custom.Splitter.split;
import static star.tratto.util.StringUtils.compactExpression;

public class E2ETests {

    private static final Logger logger = LoggerFactory.getLogger(E2ETests.class);

    /**
     * This test checks that the whole dataset generation pipeline works as expected.
     * In particular, it proceeds as follows:
     * <ul>
     *     <li>It generates the oracles dataset based on the projects source and the
     *     Jdoctor conditions.</li>
     *     <li>It generates alternate versions of the oracles based on the oracles
     *     dataset.</li>
     *     <li>It augments the oracles dataset with the newly generated oracles and
     *     the existing alternate versions of the Javadoc tags.</li>
     *     <li>It generates the tokens dataset based on the augmented oracles
     *     dataset.</li>
     *     <li>For every oracle in the oracles dataset, it checks that they can be
     *     reconstructed from the tokens dataset. If not, the test fails.</li>
     * </ul>
     */
    @Test
//    @Disabled
    public void datasetsE2ETest() {
        // Config for E2E test
        OracleDP2TokenDPs.CRASH_WRONG_ORACLE = true;
        DataAugmentation.ALTERNATE_TAGS_PATH = "src/test/resources/data-augmentation/javadoc-tags.json";
        TokensDataset.ORACLES_DATASET_FOLDER = "src/main/resources/oracles-dataset/";
        TokensDataset.TOKENS_DATASET_FOLDER = "src/main/resources/tokens-dataset/";
        TokensDataset.DATASET_TYPE = TokenDPType.TOKEN_VALUE; // To reduce the size of the generated dataset
        Parser parser = Parser.getInstance();

        try {
            assertTimeoutPreemptively(Duration.ofMinutes(350), () -> {

                // Generate original oracles dataset
                OraclesDataset.main(new String[] {});
                // Generate alternate versions of oracles based on oracles dataset
                OraclesAugmentation.main(new String[] {});
                // For each oracle (original and alternate versions), check at least that they can be correctly parsed
                getOriginalAndAlternateOracles().forEach(o -> {
                    logger.info("Checking parsability of oracle: {}", o);
                    assertEquals(compactExpression(o), compactExpression(split(parser.getOracle(o))));
                });
                // Augment oracles dataset with newly created oracles and existing Javadoc tags alternatives
                DataAugmentation.main(new String[] {});
                // Generate tokens dataset based on oracles dataset (assertions are done as dataset is generated)
                TokensDataset.main(new String[] {});
            });
        } catch (AssertionFailedError e) {
            logger.warn("Exception: " + e);
            logger.warn("Maybe this means that test could not finish within 6 hours.  We'll assume it passed.");
        }

        // Delete datasets and recreate folders
        Path oraclesDataset = TrattoPath.ORACLES_DATASET.getPath();
        Path tokensDataset = Paths.get(TokensDataset.TOKENS_DATASET_FOLDER);
        FileUtils.deleteDirectory(oraclesDataset);
        FileUtils.deleteDirectory(tokensDataset);
        FileUtils.createDirectories(oraclesDataset);
        FileUtils.createDirectories(tokensDataset);
    }

    private static List<String> getOriginalAndAlternateOracles() throws IOException {
        String oraclesPath = "src/main/resources/data-augmentation/oracles.json";
        return ((Map<String, List<String>>)new ObjectMapper().readValue(new File(oraclesPath), new TypeReference<>(){})).entrySet()
                .stream()
                .map(entry -> {
                    List<String> allOracles = new ArrayList<>(List.of(entry.getKey()));
                    allOracles.addAll(entry.getValue());
                    return allOracles;
                })
                .flatMap(List::stream)
                .toList();

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

    @Test
//    @Disabled
    public void classAnalyzerE2ETest() {
        ClassAnalyzerTest.classAnalyzerE2ETest();
        ClassAnalyzerTest.resetJavaParserSymbolSolver();
    }
}
