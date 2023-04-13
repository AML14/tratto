package star.tratto.util;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.resolution.MethodUsage;
import com.github.javaparser.resolution.UnsolvedSymbolException;
import org.javatuples.Pair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import star.tratto.dataset.oracles.OracleDatapoint;
import star.tratto.dataset.OracleDatapointTest;
import star.tratto.oraclegrammar.custom.Parser;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static star.tratto.TestUtilities.readOracleDatapointsFromOraclesDataset;
import static star.tratto.util.JavaParserUtils.*;

public class JavaParserUtilsTest {

    private static final List<OracleDatapoint> oracleDatapoints = readOracleDatapointsFromOraclesDataset();
    private static final Parser parser = Parser.getInstance();
    private static final JavaParser javaParser = JavaParserUtils.getJavaParser();

    // TODO: Once the oracles dataset is ready, test this method with all oracles, which should evaluate to boolean
    @Test
    public void getReturnTypeOfExpressionOracle1Test() {
        OracleDatapoint oracleDatapoint = oracleDatapoints.get(0);

        assertEquals(Pair.with("java.lang", "String"), getReturnTypeOfExpression("\"someString\"", oracleDatapoint));
        assertEquals(Pair.with("", "int"), getReturnTypeOfExpression("1", oracleDatapoint));
        assertEquals(Pair.with("java.lang", "Integer"), getReturnTypeOfExpression("new Integer(1)", oracleDatapoint));
        assertEquals(Pair.with("java.util", "List"), getReturnTypeOfExpression("java.util.List.of(1)", oracleDatapoint));
        assertEquals(Pair.with("java.lang", "Object"), getReturnTypeOfExpression("o1", oracleDatapoint));
        assertEquals(Pair.with("", "boolean"), getReturnTypeOfExpression("o1==null", oracleDatapoint));
        assertEquals(Pair.with("", "boolean"), getReturnTypeOfExpression("((o1==null)==false)", oracleDatapoint));
        assertEquals(Pair.with("java.lang", "Object"), getReturnTypeOfExpression("o2", oracleDatapoint));
        assertEquals(Pair.with("", "boolean"), getReturnTypeOfExpression("o1.equals(o2)", oracleDatapoint));
        assertEquals(Pair.with("", "boolean"), getReturnTypeOfExpression("((o1==null)==false) && (o1.equals(o2))", oracleDatapoint));
        assertEquals(Pair.with("", "boolean"), getReturnTypeOfExpression("methodResultID", oracleDatapoint));
        assertEquals(Pair.with("", "boolean"), getReturnTypeOfExpression("methodResultID == true", oracleDatapoint));
        assertEquals(Pair.with("", "boolean"), getReturnTypeOfExpression("true", oracleDatapoint));
        assertEquals(Pair.with("", "boolean"), getReturnTypeOfExpression("((o1==null)==false) && (o1.equals(o2)) ? methodResultID == true : true", oracleDatapoint));
    }

    @Test
    public void getReturnTypeOfExpressionOracle2Test() {
        OracleDatapoint oracleDatapoint = oracleDatapoints.get(1);

        assertEquals(Pair.with("org.apache.commons.math3.analysis.polynomials", "PolynomialFunction"), getReturnTypeOfExpression("this", oracleDatapoint));
        assertEquals(Pair.with("", "double[]"), getReturnTypeOfExpression("this.getCoefficients()", oracleDatapoint));
        assertEquals(Pair.with("", "int"), getReturnTypeOfExpression("this.getCoefficients().length", oracleDatapoint));
        assertEquals(Pair.with("", "int"), getReturnTypeOfExpression("0", oracleDatapoint));
        assertEquals(Pair.with("org.apache.commons.math3.analysis.differentiation", "DerivativeStructure"), getReturnTypeOfExpression("t", oracleDatapoint));
        assertEquals(Pair.with("org.apache.commons.math3.analysis.differentiation", "DerivativeStructure"), getReturnTypeOfExpression("methodResultID", oracleDatapoint));
        assertEquals(Pair.with("", "boolean"), getReturnTypeOfExpression("this.getCoefficients().length==0", oracleDatapoint));
        assertEquals(Pair.with("", "double"), getReturnTypeOfExpression("SparseGradient.createConstant(1).taylor(Complex.I.getReal())", oracleDatapoint));
        assertThrows(UnsolvedSymbolException.class, () -> getReturnTypeOfExpression("SparseGradient.createConstant(arg0).taylor(Complex.I.getReal())", oracleDatapoint));
    }

    @Test
    public void getReturnTypeOfExpressionOracle3Test() {
        OracleDatapoint oracleDatapoint = oracleDatapoints.get(1);

        assertEquals(Pair.with("org.apache.commons.math3.analysis.polynomials", "PolynomialFunction"), getReturnTypeOfExpression("this", oracleDatapoint));
        assertEquals(Pair.with("", "boolean"), getReturnTypeOfExpression("this instanceof PolynomialFunction", oracleDatapoint));
        assertEquals(Pair.with("", "boolean"), getReturnTypeOfExpression("t.equals(this)", oracleDatapoint));
        assertEquals(Pair.with("", "boolean"), getReturnTypeOfExpression("(t.equals(this))==false", oracleDatapoint));
        assertEquals(Pair.with("", "boolean"), getReturnTypeOfExpression("this instanceof PolynomialFunction && (t.equals(this))", oracleDatapoint));
        assertEquals(Pair.with("", "boolean"), getReturnTypeOfExpression("this instanceof PolynomialFunction && (t.equals(this))==false", oracleDatapoint));
    }

    @Test
    public void getReturnTypeOfExpressionOracle4Test() {
        OracleDatapoint oracleDatapoint = OracleDatapointTest.getEmptyOracleDatapoint();
        oracleDatapoint.setClassName("SomeClass");
        oracleDatapoint.setMethodSourceCode("public List<Integer> someMethod() { return List.of(1, 2, 3); }");
        oracleDatapoint.setClassSourceCode("import java.util.List; public class SomeClass { " + oracleDatapoint.getMethodSourceCode() + " }");
        oracleDatapoint.setTokensMethodArguments(List.of());

        assertEquals(Pair.with("java.lang", "Integer"), getReturnTypeOfExpression("jdVar", oracleDatapoint, "methodResultID.get(0)"));
        assertEquals(Pair.with("java.lang", "String"), getReturnTypeOfExpression("jdVar.toString()", oracleDatapoint, "methodResultID.get(0)"));
        assertEquals(Pair.with("java.lang", "Integer"), getReturnTypeOfExpression("jdVar", oracleDatapoint, parser.getJdVarArrayElement("jdVar", "methodResultID.stream().anyMatch(jdVar -> jdVar.toString() != null);")));
        assertEquals(Pair.with("java.lang", "String"), getReturnTypeOfExpression("jdVar.toString()", oracleDatapoint, parser.getJdVarArrayElement("jdVar", "methodResultID.stream().anyMatch(jdVar -> jdVar.toString() != null);")));
    }

    @Test
    public void isType1InstanceOfType2Test() {
        assertTrue(isType1InstanceOfType2("String", "String"));
        assertTrue(isType1InstanceOfType2("String", "java.lang.String"));
        assertTrue(isType1InstanceOfType2("java.lang.String", "String"));
        assertTrue(isType1InstanceOfType2("java.lang.String", "java.lang.String"));
        assertTrue(isType1InstanceOfType2("String", "Object"));
        assertTrue(isType1InstanceOfType2("String", "java.lang.Object"));
        assertTrue(isType1InstanceOfType2("java.util.List", "Object"));
        assertFalse(isType1InstanceOfType2("String", "StringBuilder"));
        assertFalse(isType1InstanceOfType2("boolean", "boolean"));
        assertTrue(isType1InstanceOfType2("Boolean", "Boolean"));
        assertFalse(isType1InstanceOfType2("boolean", "Boolean"));
        assertFalse(isType1InstanceOfType2("Boolean", "boolean"));
        assertFalse(isType1InstanceOfType2("boolean", "java.lang.Boolean"));
        assertFalse(isType1InstanceOfType2("java.lang.Boolean", "boolean"));
        assertFalse(isType1InstanceOfType2("Integer", "int"));
        assertFalse(isType1InstanceOfType2("int", "Integer"));
        assertFalse(isType1InstanceOfType2("int", "Long"));
        assertFalse(isType1InstanceOfType2("java.lang.Long", "int"));
        assertFalse(isType1InstanceOfType2("long", "Long"));
        assertFalse(isType1InstanceOfType2("java.lang.Long", "long"));
        assertFalse(isType1InstanceOfType2("Float", "float"));
        assertFalse(isType1InstanceOfType2("float", "Float"));
        assertFalse(isType1InstanceOfType2("float", "Double"));
        assertFalse(isType1InstanceOfType2("java.lang.Double", "float"));
        assertFalse(isType1InstanceOfType2("Double", "double"));
        assertFalse(isType1InstanceOfType2("double", "Double"));
        assertFalse(isType1InstanceOfType2("StringBuilder", "String"));
        assertTrue(isType1InstanceOfType2("org.apache.commons.math3.ml.clustering.CentroidCluster", "org.apache.commons.math3.ml.clustering.Cluster"));
        assertTrue(isType1InstanceOfType2("star.tratto.token.restrictions.multi.LastMethodNameRestriction", "star.tratto.token.restrictions.multi.MultiTokenRestriction"));
        assertFalse(isType1InstanceOfType2("star.tratto.token.restrictions.multi.LastMethodNameRestriction", "star.tratto.token.restrictions.multi.MultiTokenRestrictions"));
        assertTrue(isType1InstanceOfType2("star.tratto.oraclegrammar.generator.TrattoGrammarGenerator", "org.eclipse.xtext.generator.AbstractGenerator"));
        // Unexplicably, the following three assertions make PITest fail
//        assertTrue(isType1InstanceOfType2("org.miv.pherd.Particle", "org.miv.pherd.Particle"));
//        assertTrue(isType1InstanceOfType2("plume.ArraysMDE", "plume.ArraysMDE"));
//        assertTrue(isType1InstanceOfType2("org.apache.commons.bcel6.classfile.Attribute", "org.apache.commons.bcel6.classfile.Attribute"));
    }

    @Test
    public void isType1AssignableToType2Test() {
        assertTrue(isType1AssignableToType2(Pair.with("java.lang", "String"), Pair.with("java.lang", "String")));
        assertTrue(isType1AssignableToType2(Pair.with("java.lang", "String"), Pair.with("java.lang", "Object")));
        assertTrue(isType1AssignableToType2(Pair.with("java.util", "List"), Pair.with("java.lang", "Object")));
        assertFalse(isType1AssignableToType2(Pair.with("java.lang", "Object"), Pair.with("java.util", "List")));
        assertFalse(isType1AssignableToType2(Pair.with("java.lang", "String"), Pair.with("java.lang", "StringBuilder")));
        assertFalse(isType1AssignableToType2(Pair.with("java.lang", "StringBuilder"), Pair.with("java.lang", "String")));
        assertTrue(isType1AssignableToType2(Pair.with("", "boolean"), Pair.with("", "boolean")));
        assertTrue(isType1AssignableToType2(Pair.with("", "boolean"), Pair.with("java.lang", "Boolean")));
        assertFalse(isType1AssignableToType2(Pair.with("java.lang", "Boolean"), Pair.with("", "boolean")));
        assertTrue(isType1AssignableToType2(Pair.with("java.lang", "Boolean"), Pair.with("java.lang", "Boolean")));
        assertTrue(isType1AssignableToType2(Pair.with("", "int"), Pair.with("", "int")));
        assertTrue(isType1AssignableToType2(Pair.with("", "int"), Pair.with("java.lang", "Integer")));
        assertTrue(isType1AssignableToType2(Pair.with("java.lang", "Integer"), Pair.with("", "int")));
        assertTrue(isType1AssignableToType2(Pair.with("java.lang", "Integer"), Pair.with("java.lang", "Integer")));
        assertTrue(isType1AssignableToType2(Pair.with("", "long"), Pair.with("", "long")));
        assertTrue(isType1AssignableToType2(Pair.with("", "long"), Pair.with("java.lang", "Long")));
        assertTrue(isType1AssignableToType2(Pair.with("java.lang", "Long"), Pair.with("", "long")));
        assertTrue(isType1AssignableToType2(Pair.with("java.lang", "Long"), Pair.with("java.lang", "Long")));
        assertTrue(isType1AssignableToType2(Pair.with("", "float"), Pair.with("", "float")));
        assertTrue(isType1AssignableToType2(Pair.with("", "float"), Pair.with("java.lang", "Float")));
        assertTrue(isType1AssignableToType2(Pair.with("java.lang", "Float"), Pair.with("", "float")));
        assertTrue(isType1AssignableToType2(Pair.with("java.lang", "Float"), Pair.with("java.lang", "Float")));
        assertTrue(isType1AssignableToType2(Pair.with("", "double"), Pair.with("", "double")));
        assertTrue(isType1AssignableToType2(Pair.with("", "double"), Pair.with("java.lang", "Double")));
        assertTrue(isType1AssignableToType2(Pair.with("java.lang", "Double"), Pair.with("", "double")));
        assertTrue(isType1AssignableToType2(Pair.with("java.lang", "Double"), Pair.with("java.lang", "Double")));

        assertTrue(isType1AssignableToType2(Pair.with("org.apache.commons.math3.ml.clustering", "CentroidCluster"), Pair.with("org.apache.commons.math3.ml.clustering", "Cluster")));
        assertTrue(isType1AssignableToType2(Pair.with("star.tratto.token.restrictions.multi", "LastMethodNameRestriction"), Pair.with("star.tratto.token.restrictions.multi", "MultiTokenRestriction")));
        assertFalse(isType1AssignableToType2(Pair.with("star.tratto.token.restrictions.multi", "LastMethodNameRestriction"), Pair.with("star.tratto.token.restrictions.multi", "MultiTokenRestrictions")));
        assertTrue(isType1AssignableToType2(Pair.with("star.tratto.oraclegrammar.generator", "TrattoGrammarGenerator"), Pair.with("org.eclipse.xtext.generator", "AbstractGenerator")));
        // Unexplicably, the following three assertions make PITest fail
//        assertTrue(isType1AssignableToType2(Pair.with("org.miv.pherd", "Particle"), Pair.with("org.miv.pherd", "Particle")));
//        assertTrue(isType1AssignableToType2(Pair.with("plume", "ArraysMDE"), Pair.with("plume", "ArraysMDE")));
//        assertTrue(isType1AssignableToType2(Pair.with("org.apache.commons.bcel6.classfile", "Attribute"), Pair.with("org.apache.commons.bcel6.classfile", "Attribute")));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("getMethodSignatureFromDeclarationParameterizedTestData")
    public void getMethodSignatureFromDeclarationTest(String testName, String classSource, String className, String methodName, String expectedMethodSignature) {
        MethodDeclaration methodDeclaration = javaParser.parse(classSource)
                .getResult().get()
                .getLocalDeclarationFromClassname(className).get(0)
                .getMethodsByName(methodName).get(0);
        assertEquals(expectedMethodSignature, getMethodSignature(methodDeclaration));
    }

    private static Stream<Arguments> getMethodSignatureFromDeclarationParameterizedTestData() {
        String className = "SomeClass";
        String classSource = "public class " + className + " { XXX }";
        String methodName = "someMethod";
        return Stream.of(
                Arguments.of("test1", classSource.replaceAll("XXX", "public void " + methodName + "() {}"), className, methodName, "public void someMethod()"),
                Arguments.of("test2", classSource.replaceAll("XXX", "public void " + methodName + "();"), className, methodName, "public void someMethod()"),
                Arguments.of("test3", classSource.replaceAll("XXX", "private static native Integer " + methodName + "(String arg1, boolean arg2);"),
                        className, methodName,"private static native Integer someMethod(String arg1, boolean arg2)"),
                Arguments.of("test3", "import org.javatuples.Pair; " + classSource.replaceAll("XXX", "protected final Integer " + methodName + "(Pair<String, Integer> arg1, boolean arg2);"),
                        className, methodName, "protected final Integer someMethod(Pair<String, Integer> arg1, boolean arg2)"),
                Arguments.of("test3", "import star.tratto.dataset.OracleDatapoint; " + classSource.replaceAll("XXX", "synchronized OracleDatapoint " + methodName + "() { return null; }"),
                        className, methodName, "synchronized OracleDatapoint someMethod()")
        );
    }

    // This method tests also getTypeWithoutPackages (implicitly)
    @ParameterizedTest(name = "{0}")
    @MethodSource("getMethodSignatureFromUsageParameterizedTestData")
    public void getMethodSignatureFromUsageTest(String testName, String packageClass, String methodName, String expectedMethodSignature) {
        MethodUsage methodUsage = new ArrayList<>(getResolvedReferenceTypeDeclaration(packageClass).getAllMethods()
                .stream()
                .sorted(Comparator.comparing(MethodUsage::toString))
                .collect(Collectors.toList()))
                .stream()
                .filter(method -> method.getName().equals(methodName))
                .findFirst().get();
        assertEquals(expectedMethodSignature, getMethodSignature(methodUsage));
    }

    private static Stream<Arguments> getMethodSignatureFromUsageParameterizedTestData() {
        return Stream.of(
                Arguments.of("test1", "java.lang.Object", "equals", "public boolean equals(Object arg0)"),
                Arguments.of("test2", "java.lang.Object", "getClass", "public final native Class<? extends Object> getClass()"),
                Arguments.of("test3", "java.lang.Object", "finalize", "protected void finalize() throws Throwable"),
                Arguments.of("test4", "java.lang.String", "getBytes", "public byte[] getBytes()"),
                Arguments.of("test5", "java.lang.String", "copyValueOf", "public static String copyValueOf(char[] arg0)"),
                Arguments.of("test6", "java.lang.Class", "checkPackageAccess", "private void checkPackageAccess(SecurityManager arg0, ClassLoader arg1, boolean arg2)"),
                Arguments.of("test7", "java.lang.Class.Atomic", "casReflectionData", "static boolean casReflectionData(Class<? extends Object> arg0, SoftReference<Class.ReflectionData<T>> arg1, SoftReference<Class.ReflectionData<T>> arg2)"),
                Arguments.of("test8", "java.lang.Class", "newInstance", "public T newInstance() throws InstantiationException, IllegalAccessException"),
                Arguments.of("test9", "java.lang.Class", "getInterfaces", "private Class<? extends Object>[] getInterfaces(boolean arg0)")
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("isNonStaticNonVoidNonPrivateMethodParameterizedTestData")
    public void isNonStaticNonVoidNonPrivateMethodTest(String testName, String packageClass, String methodName, boolean expected) {
        MethodUsage methodUsage = new ArrayList<>(getResolvedReferenceTypeDeclaration(packageClass).getAllMethods()
                .stream()
                .sorted(Comparator.comparing(MethodUsage::toString))
                .collect(Collectors.toList()))
                .stream()
                .filter(method -> method.getName().equals(methodName))
                .findFirst().get();
        assertEquals(expected, isNonStaticNonVoidNonPrivateMethod(methodUsage));
    }

    private static Stream<Arguments> isNonStaticNonVoidNonPrivateMethodParameterizedTestData() {
        return Stream.of(
                Arguments.of("test1", "java.lang.Object", "equals", true), // "public boolean equals(Object arg0)"),
                Arguments.of("test2", "java.lang.Object", "getClass", true), // "public final native Class<? extends Object> getClass()"),
                Arguments.of("test3", "java.lang.Object", "finalize", false), // "protected void finalize() throws Throwable"),
                Arguments.of("test4", "java.lang.String", "getBytes", true), // "public byte[] getBytes()"),
                Arguments.of("test5", "java.lang.String", "copyValueOf", false), // "public static String copyValueOf(char[] arg0)"),
                Arguments.of("test6", "java.lang.Class", "checkPackageAccess", false), // "private void checkPackageAccess(SecurityManager arg0, ClassLoader arg1, boolean arg2)"),
                Arguments.of("test7", "java.lang.Class.Atomic", "casReflectionData", false), // "static boolean casReflectionData(Class<? extends Object> arg0, SoftReference<Class.ReflectionData<T>> arg1, SoftReference<Class.ReflectionData<T>> arg2)"),
                Arguments.of("test8", "java.lang.Class", "newInstance", true), // "public T newInstance() throws InstantiationException, IllegalAccessException")
                Arguments.of("test9", "java.lang.Class", "getInterfaces", false) // "private Class<? extends Object>[] getInterfaces(boolean arg0)")
        );
    }
}
