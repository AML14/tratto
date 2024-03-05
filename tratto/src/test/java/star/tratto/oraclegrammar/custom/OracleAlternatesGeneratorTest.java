package star.tratto.oraclegrammar.custom;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static star.tratto.oraclegrammar.custom.OracleAlternatesGenerator.getAlternateOraclePostcondition;
import static star.tratto.oraclegrammar.custom.Splitter.split;
import static star.tratto.util.StringUtils.compactExpression;

class OracleAlternatesGeneratorTest {

    Parser parser = Parser.getInstance();

    @Test
    public void getAlternateOraclePostconditionTest() {
        String oracle = "string==null||string.isEmpty()?methodResultID==true:methodResultID==false;";
        List<String> alternateOracles = getAlternateOraclePostcondition(oracle);
        assertEquals(compactExpression(alternateOracles.get(0)), compactExpression(split(parser.getOracle(alternateOracles.get(0)))));
    }
}