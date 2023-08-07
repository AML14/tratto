import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.Statement;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestUtilsTest {
    private static final Path examplePath = Paths.get("src/test/java/example");
    private static final Path outputPath = Paths.get("output");

    @Test
    public void removeAssertionOraclesTest() throws IOException {
        CompilationUnit cu = StaticJavaParser.parse(examplePath.resolve("ExampleTest.java"));
        System.out.println(cu);
//        // Creating the method body as a list of statements
//        List<Statement> statements = List.of(
//                new ExpressionStmt(
//                        StaticJavaParser.parseExpression("List<String> nonEmptyList = List.of(\"example\");")),
//                new ExpressionStmt(
//                        StaticJavaParser.parseExpression("assertEquals(\"example\", nonEmptyList.get(0));"))
//        );
//
//        // Creating the method declaration
//        MethodDeclaration methodDeclaration = new MethodDeclaration(
//                NodeList.nodeList(Modifier.publicModifier()),
//                new NodeList<AnnotationExpr>().add(new NameExpr("Test")),
//                new NodeList<>(),
//                StaticJavaParser.parseType("void"),
//                "assertionTest",
//                new NodeList<>(),
//                new BlockStmt(statements)
//        );

        // Print the generated method declaration
//        System.out.println(methodDeclaration.toString());
    }

    @Test
    public void removeOraclesTest() {
        TestUtils.removeOracles(examplePath);
//        FileUtils.deleteDirectory(outputPath.resolve("evosuite-prefix"));
    }
}
