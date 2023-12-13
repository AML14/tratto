package star.tratto.util.javaparser;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.CallableDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.TypeDeclaration;
import org.junit.jupiter.api.Test;
import star.tratto.data.OracleDatapoint;
import star.tratto.data.JPClassNotFoundException;
import star.tratto.data.records.AttributeTokens;
import star.tratto.data.records.ValueTokens;
import star.tratto.data.records.MethodArgumentTokens;
import star.tratto.data.records.MethodTokens;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static star.tratto.TestUtilities.readOracleDatapointsFromOraclesDataset;
import static star.tratto.util.javaparser.DatasetUtils.*;
import static star.tratto.util.javaparser.JavaParserUtils.*;

public class DatasetUtilsTest {
    private static final List<OracleDatapoint> oracleDatapoints = readOracleDatapointsFromOraclesDataset();
    private static final JavaParser javaParser = JavaParserUtils.getJavaParser();

    @Test
    public void removeDuplicatesTest() {
        List<String> original = List.of("first", "second", "first", "first", "third", "third");
        List<String> expected = List.of("first", "second", "third");
        assertEquals(expected, withoutDuplicates(original));
    }

    @Test
    public void getClassJavadocTest() {
        OracleDatapoint oracleDatapoint = oracleDatapoints.get(1);
        TypeDeclaration<?> jpClass = getClassOrInterface(oracleDatapoint.getClassSourceCode(), oracleDatapoint.getClassName());
        assertEquals(oracleDatapoint.getClassJavadoc(), getClassJavadoc(jpClass));
    }

    @Test
    public void getCallableJavadocTest() {
        OracleDatapoint oracleDatapoint = oracleDatapoints.get(1);
        TypeDeclaration<?> jpClass = getClassOrInterface(oracleDatapoint.getClassSourceCode(), oracleDatapoint.getClassName());
        String methodName = "value";
        List<String> methodArgs = List.of("DerivativeStructure");
        CallableDeclaration<?> jpCallable = getCallableDeclaration(jpClass, methodName, methodArgs);
        assertNotNull(jpCallable);
        String actual = getCallableJavadoc(jpCallable).trim();
        String expected = oracleDatapoint.getMethodJavadoc();
        assertEquals(expected, actual);
    }

    @Test
    public void getJavadocValuesTest() {
        OracleDatapoint oracleDatapoint = oracleDatapoints.get(1);
        TypeDeclaration<?> jpClass = getClassOrInterface(oracleDatapoint.getClassSourceCode(), oracleDatapoint.getClassName());
        String methodName = "value";
        List<String> methodArgs = List.of("DerivativeStructure");
        CallableDeclaration<?> jpCallable = getCallableDeclaration(jpClass, methodName, methodArgs);
        assertNotNull(jpCallable);
        String javadoc = getCallableJavadoc(jpCallable).trim();
        List<ValueTokens> expected = oracleDatapoint.getTokensMethodJavadocValues();
        List<ValueTokens> actual = getJavadocLiterals(javadoc);
        assertEquals(expected, actual);
    }

    @Test
    public void getTokensMethodArgumentsTest() {
        OracleDatapoint oracleDatapoint = oracleDatapoints.get(1);
        TypeDeclaration<?> jpClass = getClassOrInterface(oracleDatapoint.getClassSourceCode(), oracleDatapoint.getClassName());
        String methodName = "value";
        List<String> methodArgs = List.of("DerivativeStructure");
        CallableDeclaration<?> jpCallable = getCallableDeclaration(jpClass, methodName, methodArgs);
        assertNotNull(jpCallable);
        List<MethodArgumentTokens> expected = oracleDatapoint.getTokensMethodArguments();
        List<MethodArgumentTokens> actual = getMethodArgumentTokens(jpClass, jpCallable);
        assertEquals(expected, actual);
    }

    @Test
    public void getCallableSourceCodeTest() {
        OracleDatapoint oracleDatapoint = oracleDatapoints.get(1);
        TypeDeclaration<?> jpClass = getClassOrInterface(oracleDatapoint.getClassSourceCode(), oracleDatapoint.getClassName());
        String methodName = "value";
        List<String> methodArgs = List.of("DerivativeStructure");
        CallableDeclaration<?> jpCallable = getCallableDeclaration(jpClass, methodName, methodArgs);
        assertNotNull(jpCallable);
        String expected = oracleDatapoint.getMethodSourceCode().replaceAll("\\s+", "");
        String actual = getCallableSourceCode(jpCallable).replaceAll("\\s+", "");
        assertEquals(expected, actual);
    }

    @Test
    public void getMethodsFromTypeTest() {
        OracleDatapoint oracleDatapoint = oracleDatapoints.get(1);
        TypeDeclaration<?> jpClass = getClassOrInterface(oracleDatapoint.getClassSourceCode(), oracleDatapoint.getClassName());
        String methodName = "value";
        List<String> methodArgs = List.of("DerivativeStructure");
        CallableDeclaration<?> jpCallable = getCallableDeclaration(jpClass, methodName, methodArgs);
        assertNotNull(jpCallable);
        Parameter jpParam = jpCallable.getParameters().get(0);
        assertEquals(jpParam.getType().asString(), "DerivativeStructure");
        List<MethodTokens> actualList = getMethodsFromType(jpParam.getType().resolve());
        List<String> possiblePackageNames = List.of(
                "org.apache.commons.math3.analysis.differentiation",
                "java.lang",
                "org.apache.commons.math3"
        );
        List<String> possibleClassNames = List.of(
                "DerivativeStructure",
                "Object",
                "RealFieldElement",
                "FieldElement"
        );
        for (MethodTokens method : actualList) {
            // assert package name and class name are within valid possibilities.
            assertTrue(possiblePackageNames.contains(method.packageName()));
            assertTrue(possibleClassNames.contains(method.className()));
            // assert methods are not private and not static.
            assertFalse(method.methodSignature().contains("private"));
            assertFalse(method.methodSignature().contains("static"));
        }
    }

    @Test
    public void getFieldsFromTypeTest() {
        OracleDatapoint oracleDatapoint = oracleDatapoints.get(1);
        TypeDeclaration<?> jpClass = getClassOrInterface(oracleDatapoint.getClassSourceCode(), oracleDatapoint.getClassName());
        String methodName = "value";
        List<String> methodArgs = List.of("DerivativeStructure");
        CallableDeclaration<?> jpCallable = getCallableDeclaration(jpClass, methodName, methodArgs);
        assertNotNull(jpCallable);
        Parameter jpParam = jpCallable.getParameters().get(0);
        List<AttributeTokens> expected = new ArrayList<>();
        List<AttributeTokens> actual = getFieldsFromType(jpParam.getType().resolve());
        assertEquals(expected, actual);
    }

    @Test
    public void getFieldsFromParameterTest() {
        OracleDatapoint oracleDatapoint = oracleDatapoints.get(1);
        TypeDeclaration<?> jpClass = getClassOrInterface(oracleDatapoint.getClassSourceCode(), oracleDatapoint.getClassName());
        String methodName = "value";
        List<String> methodArgs = List.of("DerivativeStructure");
        CallableDeclaration<?> jpCallable = getCallableDeclaration(jpClass, methodName, methodArgs);
        assertNotNull(jpCallable);
        Parameter jpParam = jpCallable.getParameters().get(0);
        List<AttributeTokens> expected = new ArrayList<>();
        List<AttributeTokens> actual = getFieldsFromParameter(jpParam);
        assertEquals(expected, actual);
    }

    @Test
    public void getFieldsFromParameterArrayWithEllipsisTest() {
        String classSourceCode = """
                public class SomeClass {
                    public static String someMethod(String... arg1, int... arg2) {
                        return "";
                    }
                }
                """;
        String className = "SomeClass";
        String methodName = "someMethod";

        TypeDeclaration<?> jpClass = getClassOrInterface(classSourceCode, className);
        List<String> methodArgs = List.of("String[]", "int[]");
        CallableDeclaration<?> jpCallable = getCallableDeclaration(jpClass, methodName, methodArgs);
        assertNotNull(jpCallable);
        Parameter jpParam1 = jpCallable.getParameters().get(0);
        Parameter jpParam2 = jpCallable.getParameters().get(1);
        List<AttributeTokens> expected1 = List.of(new AttributeTokens("length", "java.lang", "String[]", "public final int length;"));
        List<AttributeTokens> expected2 = List.of(new AttributeTokens("length", "", "int[]", "public final int length;"));
        List<AttributeTokens> actual1 = getFieldsFromParameter(jpParam1);
        List<AttributeTokens> actual2 = getFieldsFromParameter(jpParam2);
        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
    }

    @Test
    public void getTokensMethodVariablesNonPrivateNonStaticNonVoidMethodsTest() {
        OracleDatapoint oracleDatapoint = oracleDatapoints.get(1);
        TypeDeclaration<?> jpClass = getClassOrInterface(oracleDatapoint.getClassSourceCode(), oracleDatapoint.getClassName());
        String methodName = "value";
        List<String> methodArgs = List.of("DerivativeStructure");
        CallableDeclaration<?> jpCallable = getCallableDeclaration(jpClass, methodName, methodArgs);
        assertNotNull(jpCallable);
        try {
            List<MethodTokens> actualList = getTokensMethodVariablesNonPrivateNonStaticNonVoidMethods(
                    jpClass,
                    jpCallable
            );
            List<String> possiblePackageNames = List.of(
                    "org.apache.commons.math3.analysis.differentiation",
                    "org.apache.commons.math3.analysis.polynomials",
                    "org.apache.commons.math3.analysis",
                    "org.apache.commons.math3",
                    "java.io",
                    "java.lang"
            );
            List<String> possibleClassNames = List.of(
                    "PolynomialFunction",
                    "UnivariateDifferentiableFunction",
                    "UnivariateFunction",
                    "DifferentiableUnivariateFunction",
                    "DerivativeStructure",
                    "RealFieldElement",
                    "FieldElement",
                    "Object"
            );
            for (MethodTokens method : actualList) {
                // assert package name and class name are within valid possibilities.
                assertTrue(possiblePackageNames.contains(method.packageName()));
                assertTrue(possibleClassNames.contains(method.className()));
                // assert methods are not private and not static.
                assertFalse(method.methodSignature().contains("private"));
                assertFalse(method.methodSignature().contains("static"));
            }
        } catch (JPClassNotFoundException e) {
            fail();
        }
    }

    @Test
    public void getTokensMethodVariablesNonPrivateNonStaticAttributesTest() {
        OracleDatapoint oracleDatapoint = oracleDatapoints.get(1);
        TypeDeclaration<?> jpClass = getClassOrInterface(oracleDatapoint.getClassSourceCode(), oracleDatapoint.getClassName());
        String methodName = "value";
        List<String> methodArgs = List.of("DerivativeStructure");
        CallableDeclaration<?> jpCallable = getCallableDeclaration(jpClass, methodName, methodArgs);
        assertNotNull(jpCallable);
        try {
            List<AttributeTokens> actualList = DatasetUtils.getTokensMethodVariablesNonPrivateNonStaticAttributes(
                    jpClass,
                    jpCallable
            );
            assertEquals(new ArrayList<>(), actualList);
        } catch (JPClassNotFoundException e) {
            fail();
        }
    }

    @Test
    public void getTokensOracleVariablesNonPrivateNonStaticNonVoidMethodsTest() {
        OracleDatapoint oracleDatapoint = oracleDatapoints.get(1);
        TypeDeclaration<?> jpClass = getClassOrInterface(oracleDatapoint.getClassSourceCode(), oracleDatapoint.getClassName());
        String methodName = "value";
        List<String> methodArgs = List.of("DerivativeStructure");
        CallableDeclaration<?> jpCallable = getCallableDeclaration(jpClass, methodName, methodArgs);
        assertNotNull(jpCallable);
        List<MethodTokens> actualList = DatasetUtils.getTokensOracleVariablesNonPrivateNonStaticNonVoidMethods(
                jpClass,
                jpCallable,
                oracleDatapoint.getTokensMethodArguments(),
                oracleDatapoint.getOracle()
        );
        List<String> possiblePackageNames = List.of(
                "java.lang",
                ""
        );
        List<String> possibleClassNames = List.of(
                "Object",
                "double[]"
        );
        for (MethodTokens method : actualList) {
            // assert package name and class name are within valid possibilities.
            assertTrue(possiblePackageNames.contains(method.packageName()));
            assertTrue(possibleClassNames.contains(method.className()));
            // assert methods are not private and not static.
            assertFalse(method.methodSignature().contains("private"));
            assertFalse(method.methodSignature().contains("static"));
        }
    }

    @Test
    public void getTokensOracleVariablesNonPrivateNonStaticAttributesTest() {
        OracleDatapoint oracleDatapoint = oracleDatapoints.get(1);
        TypeDeclaration<?> jpClass = getClassOrInterface(oracleDatapoint.getClassSourceCode(), oracleDatapoint.getClassName());
        String methodName = "value";
        List<String> methodArgs = List.of("DerivativeStructure");
        CallableDeclaration<?> jpCallable = getCallableDeclaration(jpClass, methodName, methodArgs);
        assertNotNull(jpCallable);
        List<AttributeTokens> actualList = DatasetUtils.getTokensOracleVariablesNonPrivateNonStaticAttributes(
                jpClass,
                jpCallable,
                oracleDatapoint.getTokensMethodArguments(),
                oracleDatapoint.getOracle()
        );
        assertEquals(List.of(new AttributeTokens("length", "", "double[]", "public final int length;")), actualList);
    }

    @Test
    public void getCallableDeclarationTest() {
        OracleDatapoint oracleDatapoint = oracleDatapoints.get(1);
        String className = oracleDatapoint.getClassName();
        String classSourceCode = oracleDatapoint.getClassSourceCode();
        String methodName = "value";
        List<String> methodArgs = List.of("DerivativeStructure");
        Optional<CompilationUnit> cuOptional = javaParser.parse(classSourceCode).getResult();
        assertTrue(cuOptional.isPresent());
        CompilationUnit cu = cuOptional.get();
        TypeDeclaration<?> jpClass = getTypeDeclaration(cu, className);
        assertNotNull(jpClass);
        CallableDeclaration<?> jpCallable = getCallableDeclaration(jpClass, methodName, methodArgs);
        assertNotNull(jpCallable);
        MethodDeclaration jpMethod = (MethodDeclaration) jpCallable;
        assertEquals("value", jpMethod.getNameAsString());
        assertEquals("DerivativeStructure", jpMethod.getType().asString());
    }

    @Test
    public void getTypeDeclarationTest() {
        OracleDatapoint oracleDatapoint = oracleDatapoints.get(1);
        String className = oracleDatapoint.getClassName();
        String classSourceCode = oracleDatapoint.getClassSourceCode();
        Optional<CompilationUnit> cuOptional = javaParser.parse(classSourceCode).getResult();
        assertTrue(cuOptional.isPresent());
        CompilationUnit cu = cuOptional.get();
        TypeDeclaration<?> jpClass = getTypeDeclaration(cu, className);
        assertNotNull(jpClass);
        assertEquals(jpClass.getNameAsString(), oracleDatapoint.getClassName());
    }
}
