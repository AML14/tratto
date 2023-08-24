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
        TestUtils.removeOracles(resourcesPath.resolve("test"));
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

    private List<OracleOutput> getAxiomaticOracles() {
        return List.of(
                new OracleOutput(
                        "java.lang.Integer",
                        "valueOf(int i)",
                        OracleType.PRE,
                        "",
                        "(i == null) == false",
                        ""
                ),
                new OracleOutput(
                        "java.lang.Integer",
                        "parseInt(java.lang.String s)",
                        OracleType.EXCEPT_POST,
                        "",
                        "s == null",
                        "java.lang.NumberFormatException"
                ),
                new OracleOutput(
                        "java.lang.Integer",
                        "intValue()",
                        OracleType.NORMAL_POST,
                        "",
                        "(receiverObjectID == null) == false",
                        ""
                ),
                new OracleOutput(
                        "java.lang.Class",
                        "getMethod(java.lang.String name, java.lang.Class<?>[] parameterTypes)",
                        OracleType.PRE,
                        "",
                        "(name == null) == false",
                        ""
                ),
                new OracleOutput(
                        "java.lang.Class",
                        "getMethod(java.lang.String name, java.lang.Class<?>[] parameterTypes)",
                        OracleType.PRE,
                        "",
                        "(parameterTypes == null) == false",
                        ""
                ),
                new OracleOutput(
                        "java.lang.Class",
                        "getMethod(java.lang.String name, java.lang.Class<?>[] parameterTypes)",
                        OracleType.EXCEPT_POST,
                        "",
                        "name == null",
                        "java.lang.IllegalArgumentException"
                ),
                new OracleOutput(
                        "java.lang.Class",
                        "getMethod(java.lang.String name, java.lang.Class<?>[] parameterTypes)",
                        OracleType.EXCEPT_POST,
                        "",
                        "parameterTypes == null",
                        "java.lang.IllegalArgumentException"
                ),
                new OracleOutput(
                        "java.lang.Class",
                        "getMethod(java.lang.String name, java.lang.Class<?>[] parameterTypes)",
                        OracleType.NORMAL_POST,
                        "",
                        "(methodResultID == null) == false",
                        ""
                ),
                new OracleOutput(
                        "java.lang.Class",
                        "getMethod(java.lang.String name, java.lang.Class<?>[] parameterTypes)",
                        OracleType.NORMAL_POST,
                        "",
                        "methodResultID.getDeclaringClass() == receiverObjectID",
                        ""
                )
        );
    }

    @Test
    public void insertAxiomaticOraclesTest() {
        List<OracleOutput> axiomaticOracles = getAxiomaticOracles();
        TestUtils.insertOracles(resourcesPath.resolve("prefix"), "jdoctor", axiomaticOracles);
        try {
            CompilationUnit cu = StaticJavaParser.parse(outputPath.resolve("tog-tests/jdoctor/ExamplePrefix.java"));
            List<MethodDeclaration> testCases = cu.findAll(MethodDeclaration.class);
            MethodDeclaration assertionTest = testCases.get(0);
            String expectedAssertionTest = """
                    @Test
                    @Disabled
                    public void assertionTest() throws Throwable {
                        int primitiveInt = 5;
                        assertTrue((primitiveInt == null) == false);
                        java.lang.Integer objectInt;
                        objectInt = Integer.valueOf(primitiveInt);
                    }""";
            assertEquals(expectedAssertionTest, assertionTest.toString());
            MethodDeclaration assertionNonStaticTest = testCases.get(1);
            String expectedAssertionNonStaticTest = """
                    @Test
                    @Disabled
                    public void assertionNonStaticTest() throws Throwable {
                        Integer objectInt = 5;
                        int default0;
                        default0 = objectInt.intValue();
                        assertTrue((objectInt == null) == false);
                    }""";
            assertEquals(expectedAssertionNonStaticTest, assertionNonStaticTest.toString());
            MethodDeclaration exceptionalTest = testCases.get(2);
            String expectedExceptionalTest = """
                    @Test
                    @Disabled
                    public void exceptionalTest() throws Throwable {
                        String integerToParse = null;
                        int correspondingInteger;
                        if (integerToParse == null) {
                            try {
                                correspondingInteger = Integer.parseInt(integerToParse);
                                fail();
                            } catch (java.lang.NumberFormatException e) {
                            }
                        } else {
                            correspondingInteger = Integer.parseInt(integerToParse);
                        }
                    }""";
            assertEquals(expectedExceptionalTest, exceptionalTest.toString());
            MethodDeclaration everythingTest = testCases.get(3);
            String expectedEverythingTest = """
                    @Test
                    @Disabled
                    public void everythingTest() throws Throwable {
                        Class<?> clazz = Integer.class;
                        String methodName = "compare";
                        Class<?>[] parameters = { int.class, int.class };
                        assertTrue((methodName == null) == false);
                        assertTrue((parameters == null) == false);
                        java.lang.reflect.Method method;
                        if (methodName == null) {
                            try {
                                method = clazz.getMethod(methodName, parameters);
                                fail();
                            } catch (java.lang.IllegalArgumentException e) {
                            }
                        } else if (parameters == null) {
                            try {
                                method = clazz.getMethod(methodName, parameters);
                                fail();
                            } catch (java.lang.IllegalArgumentException e) {
                            }
                        } else {
                            method = clazz.getMethod(methodName, parameters);
                            assertTrue(method.getDeclaringClass() == clazz);
                            assertTrue((method == null) == false);
                        }
                    }""";
            assertEquals(expectedEverythingTest, everythingTest.toString());
        } catch (IOException e) {
            fail();
        }
        FileUtils.deleteDirectory(outputPath.resolve("tog-tests"));
    }

    private List<OracleOutput> getNonAxiomaticOracles() {
        return List.of(
                new OracleOutput(
                        "java.lang.Integer",
                        "valueOf(int)",
                        OracleType.NON_AXIOMATIC,
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
                        "parseInt(java.lang.String)",
                        OracleType.NON_AXIOMATIC,
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
        TestUtils.insertOracles(resourcesPath.resolve("prefix"), "toga", nonAxiomaticOracles);
        String expectedAssertionTest = """
                                @Test
                                @Disabled
                                public void assertionTest() {
                                    int primitiveInt = 5;
                                    Integer objectInt = Integer.valueOf(primitiveInt);
                                    assertTrue(primitiveInt == objectInt.intValue());
                                }""";
        String expectedExceptionTest = """
                                @Test
                                @Disabled
                                public void exceptionalTest() {
                                    try {
                                        String integerToParse = null;
                                        int correspondingInteger = Integer.parseInt(integerToParse);
                                        fail();
                                    } catch (java.lang.NumberFormatException e) {
                                    }
                                }""";
        try {
            CompilationUnit cu = StaticJavaParser.parse(outputPath.resolve("tog-tests/toga/ExamplePrefix.java"));
            List<MethodDeclaration> testCases = cu.findAll(MethodDeclaration.class);
            MethodDeclaration assertionTest = testCases.get(0);
            assertEquals(expectedAssertionTest, assertionTest.toString());
            MethodDeclaration exceptionTest = testCases.get(1);
            assertEquals(expectedExceptionTest, exceptionTest.toString());
        } catch (IOException e) {
            fail();
        }
        FileUtils.deleteDirectory(outputPath.resolve("tog-tests"));
    }
}
