package data.collection.utils;

import data.collection.enums.ConditionPrimitiveType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StringUtilsTest {
    @Test
    public void testCompactExpression() {
        String expression = "if (x < 5) System.out.println(\"small\");";
        String expected = "if(x<5)System.out.println(\"small\");";
        assertEquals(expected, StringUtils.compactExpression(expression));
    }

    @Test
    public void testCompactExpressionWithInstanceOf() {
        String expression = "if (x instanceof Integer) System.out.println(\"integer\");";
        String expected = "if(x instanceof Integer)System.out.println(\"integer\");";
        assertEquals(expected, StringUtils.compactExpression(expression));
    }

    @Test
    public void testCompactExpressionList() {
        // build list of expressions.
        List<String> expressions = new ArrayList<>();
        expressions.add("System.out.println(\"initializing\");");
        expressions.add("int  x  =  5;");
        expressions.add("if (x  instanceof  Integer) System.out.println(\"integer\");");
        // build expected output.
        String expected = "System.out.println(\"initializing\");" +
                "intx=5;" +
                "if(x instanceof Integer)System.out.println(\"integer\");";
        assertEquals(expected, StringUtils.compactExpression(expressions));
    }

    @Test
    public void testAppendToken() {
        String partialExpression = "if (x <";
        String token = "methodArgument";
        String expected = "if (x < methodArgument";
        assertEquals(expected, StringUtils.appendToken(partialExpression, token));
    }

    @Test
    public void testHandleToken() {
        String token = "methodArgument";
        assertEquals(" " + token, StringUtils.handleToken(token));
    }

    @Test
    public void testGetCorrespondingParenthesisIndexNull() {
        List<String> oracleTokens = new ArrayList<>();
        oracleTokens.add("if");
        oracleTokens.add("(");
        oracleTokens.add("methodArgument");
        int openingParenthesisIndex = 1;
        assertNull(StringUtils.getCorrespondingClosingParenthesisIndex(oracleTokens, openingParenthesisIndex));
    }

    @Test
    public void testGetCorrespondingParenthesisIndex() {
        List<String> oracleTokens = new ArrayList<>();
        oracleTokens.add("if");
        oracleTokens.add("(");
        oracleTokens.add("methodArgument");
        oracleTokens.add(")");
        oracleTokens.add("return");
        int openingParenthesisIndex = 1;
        assertEquals(3, StringUtils.getCorrespondingClosingParenthesisIndex(oracleTokens, openingParenthesisIndex));
    }

    @Test
    public void testGetCorrespondingParenthesisIndexInvalidSize() {
        List<String> oracleTokens = new ArrayList<>();
        oracleTokens.add("if");
        oracleTokens.add("(");
        oracleTokens.add("methodArgument");
        oracleTokens.add(")");
        oracleTokens.add("return");
        int openingParenthesisIndex = 10;
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> StringUtils.getCorrespondingClosingParenthesisIndex(oracleTokens, openingParenthesisIndex)
        );
        assertTrue(thrown.getMessage().contains("The openingParenthesisIndex"));
    }

    @Test
    public void testGetCorrespondingParenthesisIndexInvalidIndex() {
        List<String> oracleTokens = new ArrayList<>();
        oracleTokens.add("if");
        oracleTokens.add("(");
        oracleTokens.add("methodArgument");
        oracleTokens.add(")");
        oracleTokens.add("return");
        int openingParenthesisIndex = 2;
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> StringUtils.getCorrespondingClosingParenthesisIndex(oracleTokens, openingParenthesisIndex)
        );
        assertTrue(thrown.getMessage().contains("The token at the openingParenthesisIndex"));
    }

    @Test
    public void testGetIndexesOfTokensInOracleTokens() {
        List<String> oracleTokens = new ArrayList<>();
        oracleTokens.add("if");
        oracleTokens.add("(");
        oracleTokens.add("methodArgument");
        oracleTokens.add(")");
        oracleTokens.add("return");
        List<String> tokens = new ArrayList<>();
        tokens.add("if");
        tokens.add("methodArgument");
        List<Integer> expected = new ArrayList<>();
        expected.add(0);
        expected.add(2);
        assertEquals(expected, StringUtils.getIndexesOfTokensInOracleTokens(oracleTokens, tokens));
    }

    @Test
    public void testGetIndexesOfTokensInOracleTokensNull() {
        List<String> oracleTokens = new ArrayList<>();
        oracleTokens.add("if");
        oracleTokens.add("(");
        oracleTokens.add("methodArgument");
        oracleTokens.add(")");
        oracleTokens.add("return");
        assertEquals(new ArrayList<>(), StringUtils.getIndexesOfTokensInOracleTokens(oracleTokens, null));
    }

    @Test
    public void testGetIndexesOfTokensInOracleTokensEmpty() {
        List<String> oracleTokens = new ArrayList<>();
        oracleTokens.add("if");
        oracleTokens.add("(");
        oracleTokens.add("methodArgument");
        oracleTokens.add(")");
        oracleTokens.add("return");
        List<Integer> expected = new ArrayList<>();
        expected.add(0);
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(4);
        assertEquals(expected, StringUtils.getIndexesOfTokensInOracleTokens(oracleTokens, new ArrayList<>()));
    }

    @Test
    public void testGetPackageName() {
        String packageName = "tratto.datasetoracles";
        String className = "OracleGenerator";
        String expected = "tratto.datasetoracles.OracleGenerator";
        assertEquals(expected, StringUtils.fullyQualifiedClassName(packageName, className));
    }

    @Test
    public void testGetPackageNameEmpty() {
        String packageName = "";
        String className = "OracleGenerator";
        assertEquals(className, StringUtils.fullyQualifiedClassName(packageName, className));
    }
}
