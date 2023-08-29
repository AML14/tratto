import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
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
    private static final Path resourcesPath = Paths.get("src/test/resources");
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
        TestUtils.removeOracles(resourcesPath.resolve("test"), "ExampleTest");
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
            List<MethodDeclaration> testCases = cu.getType(0).getMethods();
            assertEquals(7, testCases.size());
        } catch (IOException e) {
            fail();
        }
        FileUtils.deleteDirectory(outputPath.resolve("evosuite-prefix"));
    }

}
