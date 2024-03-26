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
    public void oracleSoFarAndTokenToTokenDatapointsTest(String testName, OracleDatapoint oracleDatapoint, List<String> oracleSoFarTokens, String nextOracleToken) {
        List<String> oracleSoFarTokensCopy = new ArrayList<>(oracleSoFarTokens);

        TokenDatapoint tokenDatapoint = OracleDP2TokenDPs.oracleSoFarAndTokenToTokenDatapoint(oracleDatapoint, oracleSoFarTokens, nextOracleToken);

        assertEquals(tokenDatapoint.getToken(), nextOracleToken);
        assertEquals(oracleDatapoint.getId(), tokenDatapoint.getOracleId());
        assertEquals(oracleDatapoint.getOracleType(), tokenDatapoint.getOracleType());
        assertEquals(oracleDatapoint.getProjectName(), tokenDatapoint.getProjectName());
        assertEquals(oracleDatapoint.getPackageName(), tokenDatapoint.getPackageName());
        assertEquals(oracleDatapoint.getClassName(), tokenDatapoint.getClassName());
        assertEquals(oracleDatapoint.getJavadocTag(), tokenDatapoint.getJavadocTag());
        assertEquals(oracleDatapoint.getMethodJavadoc(), tokenDatapoint.getMethodJavadoc());
        assertEquals(oracleDatapoint.getMethodSourceCode(), tokenDatapoint.getMethodSourceCode());
        assertEquals(oracleDatapoint.getClassJavadoc(), tokenDatapoint.getClassJavadoc());
        assertEquals(oracleDatapoint.getClassSourceCode(), tokenDatapoint.getClassSourceCode());
        assertEquals(compactExpression(oracleSoFarTokensCopy), tokenDatapoint.getOracleSoFar());

        assertEquals(oracleSoFarTokensCopy.size(), oracleSoFarTokens.size());
    }

    private static Stream<Arguments> oracleSoFarAndTokenToTokenDatapointsParameterizedTestData() {
        return Stream.of(
                Arguments.of("oracleSoFarAndTokenToTokenDatapointsTest1", oracleDatapoints.get(0), new ArrayList<>(), "("),
                Arguments.of("oracleSoFarAndTokenToTokenDatapointsTest2", oracleDatapoints.get(0), new ArrayList<>(), "("),
                Arguments.of("oracleSoFarAndTokenToTokenDatapointsTest3", oracleDatapoints.get(0), new ArrayList<>(), "("),
                Arguments.of("oracleSoFarAndTokenToTokenDatapointsTest4", oracleDatapoints.get(0), new ArrayList<>(), "("),
                Arguments.of("oracleSoFarAndTokenToTokenDatapointsTest5", oracleDatapoints.get(0), new ArrayList<>(List.of("(")), "("),
                Arguments.of("oracleSoFarAndTokenToTokenDatapointsTest6", oracleDatapoints.get(0), new ArrayList<>(List.of("(", "(", "o1")), "==")
        );
    }
}
