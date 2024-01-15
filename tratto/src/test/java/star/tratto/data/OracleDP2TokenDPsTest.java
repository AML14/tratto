package star.tratto.data;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static star.tratto.TestUtilities.readOracleDatapointsFromOraclesDataset;
import static star.tratto.util.StringUtils.compactExpression;

public class OracleDP2TokenDPsTest {

    private static final List<OracleDatapoint> oracleDatapoints = readOracleDatapointsFromOraclesDataset();

    @ParameterizedTest(name = "{0}")
    @MethodSource("oracleSoFarAndTokenToTokenDatapointsParameterizedTestData")
    public void oracleSoFarAndTokenToTokenDatapointsTest(String testName, OracleDatapoint oracleDatapoint, List<String> oracleSoFarTokens, List<String> tokenClassesSoFar, String nextOracleToken, String nextOracleTokenClass, TokenDPType tokenDPType) {
        List<String> oracleSoFarTokensCopy = new ArrayList<>(oracleSoFarTokens);
        List<String> tokenClassesSoFarCopy = new ArrayList<>(tokenClassesSoFar);

        List<TokenDatapoint> tokenDatapoints = OracleDP2TokenDPs.oracleSoFarAndTokenToTokenDatapoints(oracleDatapoint, oracleSoFarTokens, tokenClassesSoFar, nextOracleToken, tokenDPType);

        tokenDatapoints.forEach(tdp -> {
            assertTrue(!tdp.getLabel() || tdp.getToken().equals(nextOracleToken));
            assertEquals(oracleDatapoint.getId(), tdp.getOracleId());
            assertEquals(oracleDatapoint.getOracleType(), tdp.getOracleType());
            assertEquals(oracleDatapoint.getProjectName(), tdp.getProjectName());
            assertEquals(oracleDatapoint.getPackageName(), tdp.getPackageName());
            assertEquals(oracleDatapoint.getClassName(), tdp.getClassName());
            assertEquals(oracleDatapoint.getJavadocTag(), tdp.getJavadocTag());
            assertEquals(oracleDatapoint.getMethodJavadoc(), tdp.getMethodJavadoc());
            assertEquals(oracleDatapoint.getMethodSourceCode(), tdp.getMethodSourceCode());
            assertEquals(oracleDatapoint.getClassJavadoc(), tdp.getClassJavadoc());
            assertEquals(oracleDatapoint.getClassSourceCode(), tdp.getClassSourceCode());
            assertEquals(compactExpression(oracleSoFarTokensCopy), tdp.getOracleSoFar());
            assertEquals(tokenClassesSoFarCopy, tdp.getTokenClassesSoFar());
            assertTrue(tdp.getToken() != null && !tdp.getToken().equals(""));
            assertTrue(tdp.getTokenClass() != null && !tdp.getTokenClass().equals(""));
        });

        assertEquals(oracleSoFarTokensCopy.size() + 1, oracleSoFarTokens.size());
        assertEquals(tokenClassesSoFarCopy.size() + 1, tokenClassesSoFar.size());
        assertEquals(oracleSoFarTokens.get(oracleSoFarTokens.size() - 1), nextOracleToken);
        assertEquals(tokenClassesSoFar.get(tokenClassesSoFar.size() - 1), nextOracleTokenClass);
    }

    private static Stream<Arguments> oracleSoFarAndTokenToTokenDatapointsParameterizedTestData() {
        return Stream.of(
                Arguments.of("oracleSoFarAndTokenToTokenDatapointsTest1", oracleDatapoints.get(0), new ArrayList<>(), new ArrayList<>(), "(", "OpeningParenthesis", TokenDPType.TOKEN),
                Arguments.of("oracleSoFarAndTokenToTokenDatapointsTest2", oracleDatapoints.get(0), new ArrayList<>(), new ArrayList<>(), "(", "OpeningParenthesis", TokenDPType.TOKEN),
                Arguments.of("oracleSoFarAndTokenToTokenDatapointsTest3", oracleDatapoints.get(0), new ArrayList<>(), new ArrayList<>(), "(", "OpeningParenthesis", TokenDPType.TOKEN_CLASS),
                Arguments.of("oracleSoFarAndTokenToTokenDatapointsTest4", oracleDatapoints.get(0), new ArrayList<>(), new ArrayList<>(), "(", "OpeningParenthesis", TokenDPType.TOKEN_VALUE),
                Arguments.of("oracleSoFarAndTokenToTokenDatapointsTest5", oracleDatapoints.get(0), new ArrayList<>(List.of("(")), new ArrayList<>(List.of("OpeningParenthesis")), "(", "OpeningParenthesis", TokenDPType.TOKEN),
                Arguments.of("oracleSoFarAndTokenToTokenDatapointsTest6", oracleDatapoints.get(0),
                        new ArrayList<>(List.of("(", "(", "o1")),
                        new ArrayList<>(List.of("OpeningParenthesis", "OpeningParenthesis", "MethodArgument")),
                        "==", "EqOperator", TokenDPType.TOKEN)
        );
    }
}
