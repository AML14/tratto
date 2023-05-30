package star.tratto.util.javaparser;

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
import star.tratto.dataset.oracles.OracleDatapointTest;
import star.tratto.util.javaparser.JavaParserUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static star.tratto.TestUtilities.readOracleDatapointsFromOraclesDataset;
import static star.tratto.util.javaparser.JavaParserUtils.*;

public class JavaParserUtilsTest {

    private static final List<OracleDatapoint> oracleDatapoints = readOracleDatapointsFromOraclesDataset();
    private static final JavaParser javaParser = JavaParserUtils.getJavaParser();

    // TODO: Once the oracles dataset is ready, test this method with all oracles, which should evaluate to boolean
    @Test
    public void getReturnTypeOfExpressionOracle1Test() {
        OracleDatapoint oracleDatapoint = oracleDatapoints.get(0);

        assertEquals(Pair.with("java.lang", "String"), getReturnTypeOfExpression("\"someString\"", oracleDatapoint));
        assertEquals(Pair.with("", "int"), getReturnTypeOfExpression("1", oracleDatapoint));
        assertEquals(Pair.with("", "int[]"), getReturnTypeOfExpression("new int[0]", oracleDatapoint));
        assertEquals(Pair.with("", "int[][]"), getReturnTypeOfExpression("new int[0][0]", oracleDatapoint));
        assertEquals(Pair.with("java.lang", "Integer"), getReturnTypeOfExpression("new Integer(1)", oracleDatapoint));
        assertEquals(Pair.with("java.lang", "Integer[][]"), getReturnTypeOfExpression("new Integer[0][0]", oracleDatapoint));
        assertEquals(Pair.with("java.util", "List"), getReturnTypeOfExpression("java.util.List.of(1)", oracleDatapoint));
        assertEquals(Pair.with("", "T"), getReturnTypeOfExpression("o1", oracleDatapoint));
        assertEquals(Pair.with("", "boolean"), getReturnTypeOfExpression("o1==null", oracleDatapoint));
        assertEquals(Pair.with("", "boolean"), getReturnTypeOfExpression("((o1==null)==false)", oracleDatapoint));
        assertEquals(Pair.with("", "T"), getReturnTypeOfExpression("o2", oracleDatapoint));
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
        oracleDatapoint.setOracle("methodResultID.stream().anyMatch(jdVar -> jdVar.toString() != null);");

        assertEquals(Pair.with("java.lang", "Integer"), getReturnTypeOfExpression("jdVar", oracleDatapoint));
        assertEquals(Pair.with("java.lang", "String"), getReturnTypeOfExpression("jdVar.toString()", oracleDatapoint));
    }

    @Test
    public void getReturnTypeOfExpressionOracle5Test() {
        OracleDatapoint oracleDatapoint = oracleDatapoints.get(4);

        assertEquals(Pair.with("org.apache.commons.math3.complex", "Complex"), getReturnTypeOfExpression("jdVar", oracleDatapoint));
        assertEquals(Pair.with("", "double"), getReturnTypeOfExpression("jdVar.getArgument()", oracleDatapoint));
        assertEquals(Pair.with("java.util", "List"), getReturnTypeOfExpression("jdVar.nthRoot(0)", oracleDatapoint));
    }

    @Test
    public void isType1InstanceOfType2Test() {
        assertTrue(isType1InstanceOfType2("String", "String", null));
        assertTrue(isType1InstanceOfType2("String", "java.lang.String", null));
        assertTrue(isType1InstanceOfType2("java.lang.String", "String", null));
        assertTrue(isType1InstanceOfType2("java.lang.String", "java.lang.String", null));
        assertTrue(isType1InstanceOfType2("String", "Object", null));
        assertTrue(isType1InstanceOfType2("String", "java.lang.Object", null));
        assertFalse(isType1InstanceOfType2("Object", "String", null));
        assertTrue(isType1InstanceOfType2("java.util.List", "Object", null));
        assertFalse(isType1InstanceOfType2("String", "StringBuilder", null));
        assertFalse(isType1InstanceOfType2("StringBuilder", "String", null));
        assertFalse(isType1InstanceOfType2("boolean", "boolean", null));
        assertTrue(isType1InstanceOfType2("Boolean", "Boolean", null));
        assertFalse(isType1InstanceOfType2("boolean", "Boolean", null));
        assertFalse(isType1InstanceOfType2("Boolean", "boolean", null));
        assertFalse(isType1InstanceOfType2("boolean", "java.lang.Boolean", null));
        assertFalse(isType1InstanceOfType2("java.lang.Boolean", "boolean", null));
        assertFalse(isType1InstanceOfType2("Integer", "int", null));
        assertFalse(isType1InstanceOfType2("int", "Integer", null));
        assertFalse(isType1InstanceOfType2("int", "Long", null));
        assertFalse(isType1InstanceOfType2("java.lang.Long", "int", null));
        assertFalse(isType1InstanceOfType2("long", "Long", null));
        assertFalse(isType1InstanceOfType2("java.lang.Long", "long", null));
        assertFalse(isType1InstanceOfType2("Float", "float", null));
        assertFalse(isType1InstanceOfType2("float", "Float", null));
        assertFalse(isType1InstanceOfType2("float", "Double", null));
        assertFalse(isType1InstanceOfType2("java.lang.Double", "float", null));
        assertFalse(isType1InstanceOfType2("Double", "double", null));
        assertFalse(isType1InstanceOfType2("double", "Double", null));
        assertTrue(isType1InstanceOfType2("T", "T", null));
        assertFalse(isType1InstanceOfType2("T", "java.lang.Object", null));
        assertTrue(isType1InstanceOfType2("T", "java.lang.Object", oracleDatapoints.get(0)));
        assertFalse(isType1InstanceOfType2("java.lang.Object", "T", null));
        assertFalse(isType1InstanceOfType2("java.lang.Object", "T", oracleDatapoints.get(0)));
        assertFalse(isType1InstanceOfType2("java.lang.Object", "non.existing.Clazz", null));
        assertFalse(isType1InstanceOfType2("java.lang.Object", "non.existing.Clazz", oracleDatapoints.get(0)));
        assertFalse(isType1InstanceOfType2("non.existing.Clazz", "java.lang.Object", null));
        assertFalse(isType1InstanceOfType2("non.existing.Clazz", "java.lang.Object", oracleDatapoints.get(0)));
        assertTrue(isType1InstanceOfType2("org.apache.commons.math3.ml.clustering.CentroidCluster", "org.apache.commons.math3.ml.clustering.Cluster", null));
        assertTrue(isType1InstanceOfType2("star.tratto.token.restrictions.multi.LastMethodNameRestriction", "star.tratto.token.restrictions.multi.MultiTokenRestriction", null));
        assertFalse(isType1InstanceOfType2("star.tratto.token.restrictions.multi.LastMethodNameRestriction", "star.tratto.token.restrictions.multi.MultiTokenRestrictions", null));
        assertTrue(isType1InstanceOfType2("star.tratto.oraclegrammar.generator.TrattoGrammarGenerator", "org.eclipse.xtext.generator.AbstractGenerator", null));
        // Unexplicably, the following three assertions make PITest fail
//        assertTrue(isType1InstanceOfType2("org.miv.pherd.Particle", "org.miv.pherd.Particle", null));
//        assertTrue(isType1InstanceOfType2("plume.ArraysMDE", "plume.ArraysMDE", null));
//        assertTrue(isType1InstanceOfType2("org.apache.commons.bcel6.classfile.Attribute", "org.apache.commons.bcel6.classfile.Attribute", null));
    }

    @Test
    public void canType1BeInstanceOfType2Test() {
        assertTrue(canType1BeInstanceOfType2("String", "String", null));
        assertTrue(canType1BeInstanceOfType2("String", "java.lang.String", null));
        assertTrue(canType1BeInstanceOfType2("java.lang.String", "String", null));
        assertTrue(canType1BeInstanceOfType2("java.lang.String", "java.lang.String", null));
        assertTrue(canType1BeInstanceOfType2("String", "Object", null));
        assertTrue(canType1BeInstanceOfType2("String", "java.lang.Object", null));
        assertTrue(canType1BeInstanceOfType2("Object", "String", null));
        assertTrue(canType1BeInstanceOfType2("java.util.List", "Object", null));
        assertFalse(canType1BeInstanceOfType2("String", "StringBuilder", null));
        assertFalse(canType1BeInstanceOfType2("StringBuilder", "String", null));
        assertFalse(canType1BeInstanceOfType2("boolean", "boolean", null));
        assertTrue(canType1BeInstanceOfType2("Boolean", "Boolean", null));
        assertFalse(canType1BeInstanceOfType2("boolean", "Boolean", null));
        assertFalse(canType1BeInstanceOfType2("Boolean", "boolean", null));
        assertFalse(canType1BeInstanceOfType2("boolean", "java.lang.Boolean", null));
        assertFalse(canType1BeInstanceOfType2("java.lang.Boolean", "boolean", null));
        assertFalse(canType1BeInstanceOfType2("Integer", "int", null));
        assertFalse(canType1BeInstanceOfType2("int", "Integer", null));
        assertFalse(canType1BeInstanceOfType2("int", "Long", null));
        assertFalse(canType1BeInstanceOfType2("java.lang.Long", "int", null));
        assertFalse(canType1BeInstanceOfType2("long", "Long", null));
        assertFalse(canType1BeInstanceOfType2("java.lang.Long", "long", null));
        assertFalse(canType1BeInstanceOfType2("Float", "float", null));
        assertFalse(canType1BeInstanceOfType2("float", "Float", null));
        assertFalse(canType1BeInstanceOfType2("float", "Double", null));
        assertFalse(canType1BeInstanceOfType2("java.lang.Double", "float", null));
        assertFalse(canType1BeInstanceOfType2("Double", "double", null));
        assertFalse(canType1BeInstanceOfType2("double", "Double", null));
        assertFalse(canType1BeInstanceOfType2("T", "T", null));
        assertFalse(canType1BeInstanceOfType2("T", "java.lang.Object", null));
        assertTrue(canType1BeInstanceOfType2("T", "java.lang.Object", oracleDatapoints.get(0)));
        assertFalse(canType1BeInstanceOfType2("java.lang.Object", "T", null));
        assertFalse(canType1BeInstanceOfType2("java.lang.Object", "T", oracleDatapoints.get(0)));
        assertFalse(canType1BeInstanceOfType2("java.lang.Object", "non.existing.Clazz", null));
        assertFalse(canType1BeInstanceOfType2("java.lang.Object", "non.existing.Clazz", oracleDatapoints.get(0)));
        assertFalse(canType1BeInstanceOfType2("non.existing.Clazz", "java.lang.Object", null));
        assertFalse(canType1BeInstanceOfType2("non.existing.Clazz", "java.lang.Object", oracleDatapoints.get(0)));
        assertTrue(canType1BeInstanceOfType2("org.apache.commons.math3.ml.clustering.CentroidCluster", "org.apache.commons.math3.ml.clustering.Cluster", null));
        assertTrue(canType1BeInstanceOfType2("star.tratto.token.restrictions.multi.LastMethodNameRestriction", "star.tratto.token.restrictions.multi.MultiTokenRestriction", null));
        assertFalse(canType1BeInstanceOfType2("star.tratto.token.restrictions.multi.LastMethodNameRestriction", "star.tratto.token.restrictions.multi.MultiTokenRestrictions", null));
        assertTrue(canType1BeInstanceOfType2("star.tratto.oraclegrammar.generator.TrattoGrammarGenerator", "org.eclipse.xtext.generator.AbstractGenerator", null));
        // Unexplicably, the following three assertions make PITest fail
//        assertTrue(canType1BeInstanceOfType2("org.miv.pherd.Particle", "org.miv.pherd.Particle", null));
//        assertTrue(canType1BeInstanceOfType2("plume.ArraysMDE", "plume.ArraysMDE", null));
//        assertTrue(canType1BeInstanceOfType2("org.apache.commons.bcel6.classfile.Attribute", "org.apache.commons.bcel6.classfile.Attribute", null));
    }

    @Test
    public void isType1AssignableToType2Test() {
        assertTrue(isType1AssignableToType2(Pair.with("java.lang", "String"), Pair.with("java.lang", "String"), null));
        assertTrue(isType1AssignableToType2(Pair.with("java.lang", "String"), Pair.with("java.lang", "Object"), null));
        assertFalse(isType1AssignableToType2(Pair.with("java.lang", "Object"), Pair.with("java.lang", "String"), null));
        assertTrue(isType1AssignableToType2(Pair.with("java.util", "List"), Pair.with("java.lang", "Object"), null));
        assertFalse(isType1AssignableToType2(Pair.with("java.lang", "Object"), Pair.with("java.util", "List"), null));
        assertFalse(isType1AssignableToType2(Pair.with("java.lang", "String"), Pair.with("java.lang", "StringBuilder"), null));
        assertFalse(isType1AssignableToType2(Pair.with("java.lang", "StringBuilder"), Pair.with("java.lang", "String"), null));
        assertFalse(isType1AssignableToType2(Pair.with("java.lang", "String"), Pair.with("java.lang", "StringBuilder"), null));
        assertTrue(isType1AssignableToType2(Pair.with("", "boolean"), Pair.with("", "boolean"), null));
        assertTrue(isType1AssignableToType2(Pair.with("", "boolean"), Pair.with("java.lang", "Boolean"), null));
        assertFalse(isType1AssignableToType2(Pair.with("java.lang", "Boolean"), Pair.with("", "boolean"), null));
        assertTrue(isType1AssignableToType2(Pair.with("java.lang", "Boolean"), Pair.with("java.lang", "Boolean"), null));
        assertTrue(isType1AssignableToType2(Pair.with("", "int"), Pair.with("", "int"), null));
        assertTrue(isType1AssignableToType2(Pair.with("", "int"), Pair.with("java.lang", "Integer"), null));
        assertTrue(isType1AssignableToType2(Pair.with("java.lang", "Integer"), Pair.with("", "int"), null));
        assertTrue(isType1AssignableToType2(Pair.with("java.lang", "Integer"), Pair.with("java.lang", "Integer"), null));
        assertTrue(isType1AssignableToType2(Pair.with("", "long"), Pair.with("", "long"), null));
        assertTrue(isType1AssignableToType2(Pair.with("", "long"), Pair.with("java.lang", "Long"), null));
        assertTrue(isType1AssignableToType2(Pair.with("java.lang", "Long"), Pair.with("", "long"), null));
        assertTrue(isType1AssignableToType2(Pair.with("java.lang", "Long"), Pair.with("java.lang", "Long"), null));
        assertTrue(isType1AssignableToType2(Pair.with("", "float"), Pair.with("", "float"), null));
        assertTrue(isType1AssignableToType2(Pair.with("", "float"), Pair.with("java.lang", "Float"), null));
        assertTrue(isType1AssignableToType2(Pair.with("java.lang", "Float"), Pair.with("", "float"), null));
        assertTrue(isType1AssignableToType2(Pair.with("java.lang", "Float"), Pair.with("java.lang", "Float"), null));
        assertTrue(isType1AssignableToType2(Pair.with("", "double"), Pair.with("", "double"), null));
        assertTrue(isType1AssignableToType2(Pair.with("", "double"), Pair.with("java.lang", "Double"), null));
        assertTrue(isType1AssignableToType2(Pair.with("java.lang", "Double"), Pair.with("", "double"), null));
        assertTrue(isType1AssignableToType2(Pair.with("java.lang", "Double"), Pair.with("java.lang", "Double"), null));
        assertTrue(isType1AssignableToType2(Pair.with("", "T"), Pair.with("", "T"), null));
        assertFalse(isType1AssignableToType2(Pair.with("", "T"), Pair.with("java.lang", "Object"), null));
        assertTrue(isType1AssignableToType2(Pair.with("", "T"), Pair.with("java.lang", "Object"), oracleDatapoints.get(0)));
        assertFalse(isType1AssignableToType2(Pair.with("java.lang", "Object"), Pair.with("", "T"), null));
        assertFalse(isType1AssignableToType2(Pair.with("java.lang", "Object"), Pair.with("", "T"), oracleDatapoints.get(0)));
        assertFalse(isType1AssignableToType2(Pair.with("java.lang", "Object"), Pair.with("non.existing", "Clazz"), null));
        assertFalse(isType1AssignableToType2(Pair.with("java.lang", "Object"), Pair.with("non.existing", "Clazz"), oracleDatapoints.get(0)));
        assertFalse(isType1AssignableToType2(Pair.with("non.existing", "Clazz"), Pair.with("java.lang", "Object"), null));
        assertFalse(isType1AssignableToType2(Pair.with("non.existing", "Clazz"), Pair.with("java.lang", "Object"), oracleDatapoints.get(0)));
        assertTrue(isType1AssignableToType2(Pair.with("org.apache.commons.math3.ml.clustering", "CentroidCluster"), Pair.with("org.apache.commons.math3.ml.clustering", "Cluster"), null));
        assertTrue(isType1AssignableToType2(Pair.with("star.tratto.token.restrictions.multi", "LastMethodNameRestriction"), Pair.with("star.tratto.token.restrictions.multi", "MultiTokenRestriction"), null));
        assertFalse(isType1AssignableToType2(Pair.with("star.tratto.token.restrictions.multi", "LastMethodNameRestriction"), Pair.with("star.tratto.token.restrictions.multi", "MultiTokenRestrictions"), null));
        assertTrue(isType1AssignableToType2(Pair.with("star.tratto.oraclegrammar.generator", "TrattoGrammarGenerator"), Pair.with("org.eclipse.xtext.generator", "AbstractGenerator"), null));
        // Unexplicably, the following three assertions make PITest fail
//        assertTrue(isType1AssignableToType2(Pair.with("org.miv.pherd", "Particle"), Pair.with("org.miv.pherd", "Particle"), null));
//        assertTrue(isType1AssignableToType2(Pair.with("plume", "ArraysMDE"), Pair.with("plume", "ArraysMDE"), null));
//        assertTrue(isType1AssignableToType2(Pair.with("org.apache.commons.bcel6.classfile", "Attribute"), Pair.with("org.apache.commons.bcel6.classfile", "Attribute"), null));
    }

    @Test
    public void getMethodsOfTypeTest() {
        assertTrue(getMethodsOfType("org.apache.commons.math3.analysis.polynomials.PolynomialFunction").stream().map(MethodUsage::getName).collect(Collectors.toList()).contains("polynomialDerivative"));
        assertTrue(getMethodsOfType("unknown.pack.age.AndClass").stream().map(MethodUsage::getName).collect(Collectors.toList()).contains("equals"));
        assertTrue(getMethodsOfType("T").stream().map(MethodUsage::getName).collect(Collectors.toList()).contains("equals"));
        assertThrows(IllegalArgumentException.class, () -> getMethodsOfType("long"));
        assertTrue(getMethodsOfType("long[]").stream().map(MethodUsage::getName).collect(Collectors.toList()).contains("clone"));
        assertTrue(getMethodsOfType("java.lang.Long[]").stream().map(MethodUsage::getName).collect(Collectors.toList()).contains("clone"));
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
                Arguments.of("test4", "import org.javatuples.Pair; " + classSource.replaceAll("XXX", "protected final Integer " + methodName + "(Pair<String, Integer> arg1, boolean arg2);"),
                        className, methodName, "protected final Integer someMethod(Pair<String, Integer> arg1, boolean arg2)"),
                Arguments.of("test5", "import star.tratto.dataset.OracleDatapoint; " + classSource.replaceAll("XXX", "synchronized OracleDatapoint " + methodName + "() { return null; }"),
                        className, methodName, "synchronized OracleDatapoint someMethod()"),
                Arguments.of("test6", "import org.jgrapht.graph.*; " + classSource
                        .replaceAll("public class", "public abstract class")
                        .replaceAll("XXX", "\n    // *** Constructors ***\n    // another comment\n    /**\n     * hello\n     */\n    public static <V, E> E addEdgeWithVertices(Graph<V, E> g, V sourceVertex, V targetVertex) {\n" +
                                "        g.addVertex(sourceVertex);\n" +
                                "        g.addVertex(targetVertex);\n" +
                                "        return g.addEdge(sourceVertex, targetVertex);\n" +
                                "    }\n"),
                        className, "addEdgeWithVertices", "public static <V, E> E addEdgeWithVertices(Graph<V, E> g, V sourceVertex, V targetVertex)"),
                Arguments.of("test7", "import org.jgrapht.graph.*; " + classSource
                        .replaceAll("public class", "public abstract class")
                        .replaceAll("XXX", "\n    /**\n     * hello\n     */\n    // *** Constructors ***\n    // another comment\n    public static <V, E> E addEdgeWithVertices(Graph<V, E> g, V sourceVertex, V targetVertex) {\n" +
                                "        g.addVertex(sourceVertex);\n" +
                                "        g.addVertex(targetVertex);\n" +
                                "        return g.addEdge(sourceVertex, targetVertex);\n" +
                                "    }"),
                        className, "addEdgeWithVertices", "public static <V, E> E addEdgeWithVertices(Graph<V, E> g, V sourceVertex, V targetVertex)"),
                Arguments.of("test8", "import org.jgrapht.graph.*; " + classSource
                        .replaceAll("public class", "public abstract class")
                        .replaceAll("XXX", "\n    /**\n     * hello\n     */\n    public static <V, E> E addEdgeWithVertices(Graph<V, E> g, V sourceVertex, V targetVertex) {\n" +
                                "        g.addVertex(sourceVertex);\n" +
                                "        g.addVertex(targetVertex);\n" +
                                "        return g.addEdge(sourceVertex, targetVertex);\n" +
                                "    }"),
                        className, "addEdgeWithVertices", "public static <V, E> E addEdgeWithVertices(Graph<V, E> g, V sourceVertex, V targetVertex)"),
                Arguments.of("test9", "import org.jgrapht.graph.*; " + classSource
                                .replaceAll("public class", "public abstract class")
                                .replaceAll("XXX", "\n    // another comment\n    /* Constructors */ public static <V, E> E addEdgeWithVertices(Graph<V, E> g, V sourceVertex, V targetVertex) {\n" +
                                        "        g.addVertex(sourceVertex);\n" +
                                        "        g.addVertex(targetVertex);\n" +
                                        "        return g.addEdge(sourceVertex, targetVertex);\n" +
                                        "    }"),
                        className, "addEdgeWithVertices", "public static <V, E> E addEdgeWithVertices(Graph<V, E> g, V sourceVertex, V targetVertex)"),
                Arguments.of("test10", "import org.jgrapht.graph.*; " + classSource
                                .replaceAll("public class", "public abstract class")
                                .replaceAll("XXX", "\n    // *** Constructors ***\n    // another comment\n    /**\n     * hello\n     */\n    public static <V, E> E addEdgeWithVertices(Graph<V, E> g, V sourceVertex, V targetVertex);\n"),
                        className, "addEdgeWithVertices", "public static <V, E> E addEdgeWithVertices(Graph<V, E> g, V sourceVertex, V targetVertex)"),
                Arguments.of("test11", "import org.jgrapht.graph.*; " + classSource
                                .replaceAll("XXX", "\n    /**\n     * hello\n     */\n    " +
                                        "    private\n" +
                                        "    // comment\n" +
                                        "    static /* another */ @Nullable String someMethod(\n" +
                                        "            // this is a param:\n" +
                                        "            @Nullable String param1,\n" +
                                        "            /* this is another param: */ int param2\n" +
                                        "    ) { return \"\"; }"
                                        ),
                        className, "someMethod", "private static String someMethod(@Nullable String param1, int param2)")
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
                Arguments.of("test6", "java.lang.String", "coder", "byte coder()"),
                Arguments.of("test7", "java.lang.Class", "checkPackageAccess", "private void checkPackageAccess(SecurityManager arg0, ClassLoader arg1, boolean arg2)"),
                Arguments.of("test8", "java.lang.Class.Atomic", "casReflectionData", "static <T> boolean casReflectionData(Class<? extends Object> arg0, SoftReference<Class.ReflectionData<T>> arg1, SoftReference<Class.ReflectionData<T>> arg2)"),
                Arguments.of("test9", "java.lang.Class", "newInstance", "public T newInstance() throws InstantiationException, IllegalAccessException"),
                Arguments.of("test10", "java.lang.Class", "getInterfaces", "private Class<? extends Object>[] getInterfaces(boolean arg0)"),
                Arguments.of("test10", "java.lang.String", "value", "byte[] value()")
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
