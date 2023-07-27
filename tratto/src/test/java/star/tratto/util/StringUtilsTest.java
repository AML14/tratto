package star.tratto.util;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static star.tratto.util.StringUtils.getCorrespondingClosingParenthesisIndex;
import static star.tratto.util.StringUtils.semanticSimilarity;

public class StringUtilsTest {

    @Test
    public void getCorrespondingClosingParenthesisIndex_IndexEqualToListSizeThrowExceptionTest()  {
        List<String> oracleTokens = List.of("this", ".", "someMethod", "(", "someArg");
        int openingParenthesisIndex = 5;
        try {
            getCorrespondingClosingParenthesisIndex(oracleTokens, openingParenthesisIndex);
            fail("Expected IllegalArgumentException");
        } catch (IndexOutOfBoundsException e) {
            assertEquals("openingParenthesisIndex=5, oracle tokens list size=5", e.getMessage());
        }
    }

    @Test
    public void getCorrespondingClosingParenthesisIndex_IndexIsNotOpeningParenthesisThrowExceptionTest()  {
        List<String> oracleTokens = List.of("this", ".", "someMethod", "(", "someArg");
        int openingParenthesisIndex = 2;
        try {
            getCorrespondingClosingParenthesisIndex(oracleTokens, openingParenthesisIndex);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("The token at the openingParenthesisIndex (" + openingParenthesisIndex + ") is not an opening parenthesis. " +
                    "Token: " + oracleTokens.get(openingParenthesisIndex), e.getMessage());
        }
    }

    @Test
    public void getCorrespondingClosingParenthesisIndex_Test1() {
        List<String> oracleTokens = List.of("this", ".", "someMethod", "(", "someArg");
        int openingParenthesisIndex = 3;
        assertNull(getCorrespondingClosingParenthesisIndex(oracleTokens, openingParenthesisIndex));
    }

    @Test
    public void getCorrespondingClosingParenthesisIndex_Test2() {
        List<String> oracleTokens = List.of("this", ".", "someMethod", "(", "someArg", ")");
        int openingParenthesisIndex = 3;
        assertEquals(5, getCorrespondingClosingParenthesisIndex(oracleTokens, openingParenthesisIndex));
    }

    @Test
    public void getCorrespondingClosingParenthesisIndex_Test3() {
        List<String> oracleTokens = List.of("this", ".", "someMethod", "(", "(", "someArg", ")");
        int openingParenthesisIndex1 = 3;
        int openingParenthesisIndex2 = 4;
        assertNull(getCorrespondingClosingParenthesisIndex(oracleTokens, openingParenthesisIndex1));
        assertEquals(6, getCorrespondingClosingParenthesisIndex(oracleTokens, openingParenthesisIndex2));
    }

    @Test
    public void getCorrespondingClosingParenthesisIndex_Test4() {
        List<String> oracleTokens = List.of("(", "this", ".", "someMethod", "(", "someArg", ")", "&&", "(", "(", "methodResultID", "||", "someOtherArg",  ")", "&&", "hello", ")");
        int openingParenthesisIndex1 = 0;
        int openingParenthesisIndex2 = 4;
        int openingParenthesisIndex3 = 8;
        int openingParenthesisIndex4 = 9;
        assertNull(getCorrespondingClosingParenthesisIndex(oracleTokens, openingParenthesisIndex1));
        assertEquals(6, getCorrespondingClosingParenthesisIndex(oracleTokens, openingParenthesisIndex2));
        assertEquals(16, getCorrespondingClosingParenthesisIndex(oracleTokens, openingParenthesisIndex3));
        assertEquals(13, getCorrespondingClosingParenthesisIndex(oracleTokens, openingParenthesisIndex4));
    }

    @Test
    public void semanticSimilarityTest() {
        assertEquals(1.0, semanticSimilarity("the", "the"));
        assertEquals(0.0, semanticSimilarity("the", "bratwurst"));
    }
}
