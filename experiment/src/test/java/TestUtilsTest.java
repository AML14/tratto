import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.stmt.Statement;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestUtilsTest {
    private static final Path testPath = Paths.get("src/test/resources");
    private static final Path outputPath = Paths.get("output");
    private static final List<String> allJUnitTestMethods = List.of(
            "assertArrayEquals",
            "assertEquals",
            "assertFalse",
            "assertNotNull",
            "assertNotSame",
            "assertNull",
            "assertSame",
            "assertThat",
            "assertTrue",
            "fail"
    );

    @Test
    public void removeOraclesTest() {
        TestUtils.removeOracles(testPath);
        try {
            CompilationUnit cu = StaticJavaParser.parse(outputPath.resolve("evosuite-prefix/ExampleTest.java"));
            cu.findAll(Statement.class)
                    .forEach(statement -> {
                        // check all assert statements have been removed
                        if (statement.isAssertStmt()) {
                            fail();
                        }
                        // check all JUnit methods have been removed
                        if (statement.isExpressionStmt()) {
                            Expression expression = statement.asExpressionStmt().getExpression();
                            if (expression.isMethodCallExpr()) {
                                MethodCallExpr methodCallExpr = expression.asMethodCallExpr();
                                if (allJUnitTestMethods.contains(methodCallExpr.getNameAsString())) {
                                    fail();
                                }
                            }
                        }
                    });
        } catch (IOException e) {
            fail();
        }
        FileUtils.deleteDirectory(outputPath.resolve("evosuite-prefix"));
    }

    private List<OracleOutput> getAxiomaticOracles() {
        return List.of(
                new OracleOutput(
                        "java.lang.Integer",
                        "valueOf(int i)",
                        "PRE",
                        "",
                        "(i == null) == false",
                        ""
                ),
                new OracleOutput(
                        "java.lang.Integer",
                        "parseInt(java.lang.String s)",
                        "EXCEPT_POST",
                        "",
                        "(s == null) == false",
                        "java.lang.NumberFormatException"
                )
        );
    }

    @Test
    public void insertAxiomaticOraclesTest() {
        List<OracleOutput> axiomaticOracles = getAxiomaticOracles();
        TestUtils.insertOracles(testPath.resolve("prefix"), "jdoctor", axiomaticOracles);
    }

    private List<OracleOutput> getNonAxiomaticOracles() {
        return List.of(
                new OracleOutput(
                        "java.lang.Integer",
                        "valueOf(int i)",
                        "NORMAL_POST",
                        """
                                @Test
                                @Disabled
                                public void assertionTest() {
                                    int primitiveInt = 5;
                                    Integer objectInt = Integer.valueOf(primitiveInt);
                                }""",
                        "assertTrue(primitiveInt == objectInt.intValue());",
                        ""
                ),
                new OracleOutput(
                        "java.lang.Integer",
                        "parseInt(java.lang.String s)",
                        "",
                        """
                                @Test
                                @Disabled
                                public void exceptionalTest() {
                                    String integerToParse = null;
                                    int correspondingInteger = Integer.parseInt(integerToParse);
                                }""",
                        "",
                        "java.lang.NumberFormatException"
                )
        );
    }

    @Test
    public void insertNonAxiomaticOraclesTest() {
        List<OracleOutput> nonAxiomaticOracles = getNonAxiomaticOracles();
        TestUtils.insertOracles(testPath.resolve("prefix"), "toga", nonAxiomaticOracles);
    }
}
