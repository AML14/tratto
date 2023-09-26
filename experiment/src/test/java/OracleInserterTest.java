import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import data.OracleOutput;
import data.OracleType;
import data.TogType;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class OracleInserterTest {
    private static final Path output = Paths.get("output");
    private static final Path resourcesPath = Paths.get("src", "test", "resources");
    private static final Path projectPath = resourcesPath.resolve("project");
    private static final Path projectJarPath = projectPath.resolve("target").resolve("Tutorial_Stack-1.0-SNAPSHOT.jar");

    private void setup() {
        FileUtils.deleteDirectory(output);
        FileUtils.createDirectories(output.resolve("evosuite-prefixes").resolve("example"));
        FileUtils.copyFile(
                resourcesPath.resolve("prefix").resolve("tutorial").resolve("StackTest.java"),
                output.resolve("evosuite-prefixes").resolve("tutorial").resolve("StackTest.java")
        );
    }

    private void cleanup() {
        FileUtils.deleteDirectory(output);
    }

    private List<OracleOutput> getAxiomaticOracles() {
        return List.of(
                new OracleOutput(
                        "java.lang.Integer",
                        "valueOf(int i)",
                        OracleType.PRE,
                        "i > 0",
                        "",
                        ""
                ),
                new OracleOutput(
                        "java.lang.Integer",
                        "parseInt(java.lang.String s)",
                        OracleType.EXCEPT_POST,
                        "s == null",
                        "java.lang.NumberFormatException",
                        ""
                ),
                new OracleOutput(
                        "java.lang.Integer",
                        "intValue()",
                        OracleType.NORMAL_POST,
                        "(receiverObjectID == null) == false",
                        "",
                        ""
                ),
                new OracleOutput(
                        "java.lang.Class",
                        "getMethod(java.lang.String name, java.lang.Class<?>[] parameterTypes)",
                        OracleType.PRE,
                        "(name == null) == false",
                        "",
                        ""
                ),
                new OracleOutput(
                        "java.lang.Class",
                        "getMethod(java.lang.String name, java.lang.Class<?>[] parameterTypes)",
                        OracleType.PRE,
                        "(parameterTypes == null) == false",
                        "",
                        ""
                ),
                new OracleOutput(
                        "java.lang.Class",
                        "getMethod(java.lang.String name, java.lang.Class<?>[] parameterTypes)",
                        OracleType.EXCEPT_POST,
                        "name == null",
                        "java.lang.IllegalArgumentException",
                        ""
                ),
                new OracleOutput(
                        "java.lang.Class",
                        "getMethod(java.lang.String name, java.lang.Class<?>[] parameterTypes)",
                        OracleType.EXCEPT_POST,
                        "parameterTypes == null",
                        "java.lang.IllegalArgumentException",
                        ""
                ),
                new OracleOutput(
                        "java.lang.Class",
                        "getMethod(java.lang.String name, java.lang.Class<?>[] parameterTypes)",
                        OracleType.NORMAL_POST,
                        "(methodResultID == null) == false",
                        "",
                        ""
                ),
                new OracleOutput(
                        "java.lang.Class",
                        "getMethod(java.lang.String name, java.lang.Class<?>[] parameterTypes)",
                        OracleType.NORMAL_POST,
                        "methodResultID.getDeclaringClass() == receiverObjectID",
                        "",
                        ""
                ),
                new OracleOutput(
                        "java.lang.String",
                        "getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin)",
                        OracleType.NORMAL_POST,
                        "receiverObjectID.charAt(srcBegin) == dst[dstBegin]",
                        "",
                        ""
                ),
                new OracleOutput(
                        "java.lang.String",
                        "substring(int beginIndex, int endIndex)",
                        OracleType.NORMAL_POST,
                        "(methodResultID == null) == false",
                        "",
                        ""
                ),
                new OracleOutput(
                        "java.util.List",
                        "add(E e)",
                        OracleType.NORMAL_POST,
                        "methodResultID == true",
                        "",
                        ""
                ),
                new OracleOutput(
                        "java.util.List",
                        "get(int index)",
                        OracleType.EXCEPT_POST,
                        "index >= receiverObjectID.size()",
                        "java.lang.IndexOutOfBoundsException",
                        ""
                )
        );
    }

    @Test
    public void insertAxiomaticOraclesTest() throws Throwable {
        setup();
        List<OracleOutput> axiomaticOracles = getAxiomaticOracles();
        OracleInserter.insertOracles(TogType.JDOCTOR, "tutorial.Stack", axiomaticOracles, projectJarPath);
        try {
            CompilationUnit cu = StaticJavaParser.parse(output.resolve("tog-tests/jdoctor/example/ExamplePrefix.java"));
            List<MethodDeclaration> testCases = cu.findAll(MethodDeclaration.class);
            MethodDeclaration assertionTest = testCases.get(0);
            String expectedAssertionTest = """
                    @Test
                    @Disabled
                    public void assertionTest() throws Throwable {
                        int primitiveInt = 5;
                        assertTrue(primitiveInt > 0);
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
                                // Successfully thrown exception
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
                        Class<?>[] parameters = { int.class, int.class };
                        assertTrue(("compare" == null) == false);
                        assertTrue((parameters == null) == false);
                        java.lang.reflect.Method method;
                        if ("compare" == null) {
                            try {
                                method = clazz.getMethod("compare", parameters);
                                fail();
                            } catch (java.lang.IllegalArgumentException e) {
                                // Successfully thrown exception
                            }
                        } else if (parameters == null) {
                            try {
                                method = clazz.getMethod("compare", parameters);
                                fail();
                            } catch (java.lang.IllegalArgumentException e) {
                                // Successfully thrown exception
                            }
                        } else {
                            method = clazz.getMethod("compare", parameters);
                            assertTrue((method == null) == false);
                            assertTrue(method.getDeclaringClass() == clazz);
                        }
                    }""";
            assertEquals(expectedEverythingTest, everythingTest.toString());
            MethodDeclaration assertionVoidTest = testCases.get(4);
            String expectedAssertionVoidTest = """
                    @Test
                    @Disabled
                    public void assertionVoidTest() throws Throwable {
                        String input = "input";
                        char[] dst = new char[5];
                        input.getChars(0, 2, dst, 0);
                        assertTrue(input.charAt(0) == dst[0]);
                    }""";
            assertEquals(expectedAssertionVoidTest, assertionVoidTest.toString());
            MethodDeclaration assertionPreInitializedTest = testCases.get(5);
            String expectedAssertionPreInitializedTest = """
                    @Test
                    @Disabled
                    public void assertionPreInitializedTest() throws Throwable {
                        String input = "input";
                        input = input.substring(0, 2);
                        assertTrue((input == null) == false);
                    }""";
            assertEquals(expectedAssertionPreInitializedTest, assertionPreInitializedTest.toString());
        } catch (IOException e) {
            fail();
        }
        FileUtils.deleteDirectory(output);
    }

    private List<OracleOutput> getNonAxiomaticOracles() {
        return List.of(
                new OracleOutput(
                        "java.lang.Integer",
                        "valueOf(int i)",
                        OracleType.NON_AXIOMATIC,
                        "assertTrue(primitiveInt == objectInt.intValue())",
                        "",
                        "assertionTest"
                ),
                new OracleOutput(
                        "java.lang.Integer",
                        "parseInt(java.lang.String s)",
                        OracleType.NON_AXIOMATIC,
                        "",
                        "java.lang.NumberFormatException",
                        "exceptionalTest"
                )
        );
    }

    @Test
    public void insertNonAxiomaticOraclesTest() throws Throwable {
        setup();
        List<OracleOutput> nonAxiomaticOracles = getNonAxiomaticOracles();
        OracleInserter.insertOracles(TogType.TOGA, "tutorial.Stack", nonAxiomaticOracles, projectJarPath);
        String expectedExceptionTest = """
                                @Test
                                @Disabled
                                public void exceptionalTest() throws Throwable {
                                    try {
                                        String integerToParse = null;
                                        int correspondingInteger = Integer.parseInt(integerToParse);
                                        fail();
                                    } catch (java.lang.NumberFormatException e) {
                                    }
                                }""";
        Path testPath = Paths.get("output", "tog-tests", "toga", "tutorial", "StackTest.java");
        CompilationUnit cu = FileUtils.getCompilationUnit(testPath);
        List<MethodDeclaration> testCases = cu.findAll(MethodDeclaration.class);
        MethodDeclaration assertionTest = testCases.get(5);
        assertEquals(
                """
                @Test
                @Disabled
                public void assertionTest() throws Throwable {
                    int primitiveInt = 5;
                    Integer objectInt = Integer.valueOf(primitiveInt);
                    assertTrue(primitiveInt == objectInt.intValue());
                }""",
                assertionTest.toString()
        );
        MethodDeclaration exceptionTest = testCases.get(7);
        assertEquals(
        """
                @Test
                @Disabled
                public void exceptionalTest() throws Throwable {
                    try {
                        String integerToParse = null;
                        int correspondingInteger = Integer.parseInt(integerToParse);
                        fail();
                    } catch (java.lang.NumberFormatException e) {
                    }
                }""",
                exceptionTest.toString()
        );
        FileUtils.deleteDirectory(output);
    }
}
