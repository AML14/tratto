package star.tratto.util.javaparser;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.resolution.MethodUsage;
import com.github.javaparser.resolution.UnsolvedSymbolException;
import com.github.javaparser.resolution.declarations.ResolvedDeclaration;
import com.github.javaparser.resolution.types.ResolvedArrayType;
import com.github.javaparser.resolution.types.ResolvedPrimitiveType;
import com.github.javaparser.resolution.types.ResolvedType;
import org.javatuples.Pair;
import org.javatuples.Triplet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import star.tratto.data.OracleDatapoint;
import star.tratto.data.OracleDatapointTest;
import star.tratto.data.JPClassNotFoundException;
import star.tratto.data.PackageDeclarationNotFoundException;
import star.tratto.data.ResolvedTypeNotFound;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static star.tratto.TestUtilities.readOracleDatapointsFromOraclesDataset;
import static star.tratto.util.javaparser.JavaParserUtils.*;

public class JavaParserUtilsTest {

    private static final List<OracleDatapoint> oracleDatapoints = readOracleDatapointsFromOraclesDataset();
    private static final JavaParser javaParser = JavaParserUtils.getJavaParser();

    @Test
    public void getResolvedTypeOfExpressionPrimitiveTest() {
        OracleDatapoint oracleDatapoint = oracleDatapoints.get(0);
        TypeDeclaration<?> jpClass = getClassOrInterface(oracleDatapoint.getClassSourceCode(), oracleDatapoint.getClassName());
        CallableDeclaration<?> jpCallable = getMethodDeclaration(oracleDatapoint.getMethodSourceCode());
        List<Triplet<String, String, String>> methodArgs = oracleDatapoint.getTokensMethodArguments();
        String subExpression = oracleDatapoint.getOracle().substring(0, 20);
        try {
            ResolvedType resolvedType = getResolvedTypeOfExpression(jpClass, jpCallable, methodArgs, subExpression);
            String typeName = resolvedType.describe();
            assertEquals("boolean", typeName);
        } catch (ResolvedTypeNotFound e) {
            fail();
        }
    }

    @Test
    public void getResolvedTypeOfExpressionObjectTest() {
        OracleDatapoint oracleDatapoint = oracleDatapoints.get(1);
        TypeDeclaration<?> jpClass = getClassOrInterface(oracleDatapoint.getClassSourceCode(), oracleDatapoint.getClassName());
        CallableDeclaration<?> jpCallable = getMethodDeclaration(oracleDatapoint.getMethodSourceCode());
        List<Triplet<String, String, String>> methodArgs = oracleDatapoint.getTokensMethodArguments();
        String subExpression = oracleDatapoint.getOracle().substring(0, 4);
        try {
            ResolvedType resolvedType = getResolvedTypeOfExpression(jpClass, jpCallable, methodArgs, subExpression);
            String typeName = resolvedType.describe();
            assertEquals("org.apache.commons.math3.analysis.polynomials.PolynomialFunction", typeName);
        } catch (ResolvedTypeNotFound e) {
            fail();
        }
    }


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
    public void isInstanceOfTest() {
        assertTrue(isInstanceOf("String", "String", null));
        assertTrue(isInstanceOf("String", "java.lang.String", null));
        assertTrue(isInstanceOf("java.lang.String", "String", null));
        assertTrue(isInstanceOf("java.lang.String", "java.lang.String", null));
        assertTrue(isInstanceOf("String", "Object", null));
        assertTrue(isInstanceOf("String", "java.lang.Object", null));
        assertFalse(isInstanceOf("Object", "String", null));
        assertTrue(isInstanceOf("java.util.List", "Object", null));
        assertFalse(isInstanceOf("String", "StringBuilder", null));
        assertFalse(isInstanceOf("StringBuilder", "String", null));
        assertFalse(isInstanceOf("boolean", "boolean", null));
        assertTrue(isInstanceOf("Boolean", "Boolean", null));
        assertFalse(isInstanceOf("boolean", "Boolean", null));
        assertFalse(isInstanceOf("Boolean", "boolean", null));
        assertFalse(isInstanceOf("boolean", "java.lang.Boolean", null));
        assertFalse(isInstanceOf("java.lang.Boolean", "boolean", null));
        assertFalse(isInstanceOf("Integer", "int", null));
        assertFalse(isInstanceOf("int", "Integer", null));
        assertFalse(isInstanceOf("int", "Long", null));
        assertFalse(isInstanceOf("java.lang.Long", "int", null));
        assertFalse(isInstanceOf("long", "Long", null));
        assertFalse(isInstanceOf("java.lang.Long", "long", null));
        assertFalse(isInstanceOf("Float", "float", null));
        assertFalse(isInstanceOf("float", "Float", null));
        assertFalse(isInstanceOf("float", "Double", null));
        assertFalse(isInstanceOf("java.lang.Double", "float", null));
        assertFalse(isInstanceOf("Double", "double", null));
        assertFalse(isInstanceOf("double", "Double", null));
        assertTrue(isInstanceOf("T", "T", null));
        assertFalse(isInstanceOf("T", "java.lang.Object", null));
        assertTrue(isInstanceOf("T", "java.lang.Object", oracleDatapoints.get(0)));
        assertFalse(isInstanceOf("java.lang.Object", "T", null));
        assertFalse(isInstanceOf("java.lang.Object", "T", oracleDatapoints.get(0)));
        assertFalse(isInstanceOf("java.lang.Object", "non.existing.Clazz", null));
        assertFalse(isInstanceOf("java.lang.Object", "non.existing.Clazz", oracleDatapoints.get(0)));
        assertFalse(isInstanceOf("non.existing.Clazz", "java.lang.Object", null));
        assertFalse(isInstanceOf("non.existing.Clazz", "java.lang.Object", oracleDatapoints.get(0)));
        assertTrue(isInstanceOf("org.apache.commons.math3.ml.clustering.CentroidCluster", "org.apache.commons.math3.ml.clustering.Cluster", null));
        assertTrue(isInstanceOf("star.tratto.token.restrictions.multi.LastMethodNameRestriction", "star.tratto.token.restrictions.multi.MultiTokenRestriction", null));
        assertFalse(isInstanceOf("star.tratto.token.restrictions.multi.LastMethodNameRestriction", "star.tratto.token.restrictions.multi.MultiTokenRestrictions", null));
        assertTrue(isInstanceOf("star.tratto.oraclegrammar.generator.TrattoGrammarGenerator", "org.eclipse.xtext.generator.AbstractGenerator", null));
        // Unexplicably, the following three assertions make PITest fail
//        assertTrue(isInstanceOf("org.miv.pherd.Particle", "org.miv.pherd.Particle", null));
//        assertTrue(isInstanceOf("plume.ArraysMDE", "plume.ArraysMDE", null));
//        assertTrue(isInstanceOf("org.apache.commons.bcel6.classfile.Attribute", "org.apache.commons.bcel6.classfile.Attribute", null));
    }

    @Test
    public void doesInstanceofCompileTest() {
        assertTrue(doesInstanceofCompile("String", "String", null));
        assertTrue(doesInstanceofCompile("String", "java.lang.String", null));
        assertTrue(doesInstanceofCompile("java.lang.String", "String", null));
        assertTrue(doesInstanceofCompile("java.lang.String", "java.lang.String", null));
        assertTrue(doesInstanceofCompile("String", "Object", null));
        assertTrue(doesInstanceofCompile("String", "java.lang.Object", null));
        assertTrue(doesInstanceofCompile("Object", "String", null));
        assertTrue(doesInstanceofCompile("java.util.List", "Object", null));
        assertFalse(doesInstanceofCompile("String", "StringBuilder", null));
        assertFalse(doesInstanceofCompile("StringBuilder", "String", null));
        assertFalse(doesInstanceofCompile("boolean", "boolean", null));
        assertTrue(doesInstanceofCompile("Boolean", "Boolean", null));
        assertFalse(doesInstanceofCompile("boolean", "Boolean", null));
        assertFalse(doesInstanceofCompile("Boolean", "boolean", null));
        assertFalse(doesInstanceofCompile("boolean", "java.lang.Boolean", null));
        assertFalse(doesInstanceofCompile("java.lang.Boolean", "boolean", null));
        assertFalse(doesInstanceofCompile("Integer", "int", null));
        assertFalse(doesInstanceofCompile("int", "Integer", null));
        assertFalse(doesInstanceofCompile("int", "Long", null));
        assertFalse(doesInstanceofCompile("java.lang.Long", "int", null));
        assertFalse(doesInstanceofCompile("long", "Long", null));
        assertFalse(doesInstanceofCompile("java.lang.Long", "long", null));
        assertFalse(doesInstanceofCompile("Float", "float", null));
        assertFalse(doesInstanceofCompile("float", "Float", null));
        assertFalse(doesInstanceofCompile("float", "Double", null));
        assertFalse(doesInstanceofCompile("java.lang.Double", "float", null));
        assertFalse(doesInstanceofCompile("Double", "double", null));
        assertFalse(doesInstanceofCompile("double", "Double", null));
        assertFalse(doesInstanceofCompile("T", "T", null));
        assertFalse(doesInstanceofCompile("T", "java.lang.Object", null));
        assertTrue(doesInstanceofCompile("T", "java.lang.Object", oracleDatapoints.get(0)));
        assertFalse(doesInstanceofCompile("java.lang.Object", "T", null));
        assertFalse(doesInstanceofCompile("java.lang.Object", "T", oracleDatapoints.get(0)));
        assertFalse(doesInstanceofCompile("java.lang.Object", "non.existing.Clazz", null));
        assertFalse(doesInstanceofCompile("java.lang.Object", "non.existing.Clazz", oracleDatapoints.get(0)));
        assertFalse(doesInstanceofCompile("non.existing.Clazz", "java.lang.Object", null));
        assertFalse(doesInstanceofCompile("non.existing.Clazz", "java.lang.Object", oracleDatapoints.get(0)));
        assertTrue(doesInstanceofCompile("org.apache.commons.math3.ml.clustering.CentroidCluster", "org.apache.commons.math3.ml.clustering.Cluster", null));
        assertTrue(doesInstanceofCompile("star.tratto.token.restrictions.multi.LastMethodNameRestriction", "star.tratto.token.restrictions.multi.MultiTokenRestriction", null));
        assertFalse(doesInstanceofCompile("star.tratto.token.restrictions.multi.LastMethodNameRestriction", "star.tratto.token.restrictions.multi.MultiTokenRestrictions", null));
        assertTrue(doesInstanceofCompile("star.tratto.oraclegrammar.generator.TrattoGrammarGenerator", "org.eclipse.xtext.generator.AbstractGenerator", null));
        // Unexplicably, the following three assertions make PITest fail
//        assertTrue(doesInstanceofCompile("org.miv.pherd.Particle", "org.miv.pherd.Particle", null));
//        assertTrue(doesInstanceofCompile("plume.ArraysMDE", "plume.ArraysMDE", null));
//        assertTrue(doesInstanceofCompile("org.apache.commons.bcel6.classfile.Attribute", "org.apache.commons.bcel6.classfile.Attribute", null));
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
    public void getGenericTypeTest() {
        ResolvedType genericType = getObjectType();
        assertEquals("java.lang.Object", genericType.describe());
    }

    @Test
    public void getMethodsOfTypeTest() {
        assertTrue(getMethodsOfType("org.apache.commons.math3.analysis.polynomials.PolynomialFunction").stream().map(MethodUsage::getName).collect(Collectors.toList()).contains("polynomialDerivative"));
        assertTrue(getMethodsOfType("org.apache.commons.math3.linear.RealMatrix").stream().map(MethodUsage::getName).collect(Collectors.toList()).contains("equals"));
        assertTrue(getMethodsOfType("org.apache.commons.math3.linear.AbstractRealMatrix").stream().map(MethodUsage::getName).collect(Collectors.toList()).contains("equals"));
        assertTrue(getMethodsOfType("java.util.List").stream().map(MethodUsage::getName).collect(Collectors.toList()).contains("wait"));
        assertTrue(getMethodsOfType("java.util.ArrayList").stream().map(MethodUsage::getName).collect(Collectors.toList()).contains("wait"));
        assertTrue(getMethodsOfType("unknown.pack.age.AndClass").stream().map(MethodUsage::getName).collect(Collectors.toList()).contains("equals"));
        assertTrue(getMethodsOfType("T").stream().map(MethodUsage::getName).collect(Collectors.toList()).contains("equals"));
        assertThrows(IllegalArgumentException.class, () -> getMethodsOfType("long"));
        assertTrue(getMethodsOfType("long[]").stream().map(MethodUsage::getName).collect(Collectors.toList()).contains("clone"));
        assertTrue(getMethodsOfType("java.lang.Long[]").stream().map(MethodUsage::getName).collect(Collectors.toList()).contains("clone"));
    }

    @Test
    public void getVariableSignatureTest() {
        OracleDatapoint oracleDatapoint = oracleDatapoints.get(1);
        TypeDeclaration<?> jpClass = getClassOrInterface(oracleDatapoint.getClassSourceCode(), oracleDatapoint.getClassName());
        FieldDeclaration jpField = jpClass.getFields().get(0);
        VariableDeclarator jpVariable = jpField.getVariable(0);
        String variableSignature = getVariableDeclaration(jpField, jpVariable);
        assertEquals("private static final long serialVersionUID = -7726511984200295583L;", variableSignature);
    }

    @Test
    public void getFieldSignatureTest() {
        OracleDatapoint oracleDatapoint = oracleDatapoints.get(1);
        TypeDeclaration<?> jpClass = getClassOrInterface(oracleDatapoint.getClassSourceCode(), oracleDatapoint.getClassName());
        FieldDeclaration jpField = jpClass.getFields().get(0);
        String fieldSignature = getFieldSignature(jpField.resolve());
        // not able to retrieve "final" keyword and assignment value from ResolvedFieldDeclaration.
        assertEquals("private static long serialVersionUID;", fieldSignature);
    }

    @Test
    public void getCallableSignatureTest() {
        OracleDatapoint oracleDatapoint = oracleDatapoints.get(1);
        CallableDeclaration<?> jpCallable = getMethodDeclaration(oracleDatapoint.getMethodSourceCode());
        assertNotNull(jpCallable);
        String actualSignature = getCallableSignature(jpCallable);
        String expectedSignature = "public DerivativeStructure value(final DerivativeStructure t) throws NullArgumentException, NoDataException";
        assertEquals(expectedSignature, actualSignature);
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
                .toList())
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

    @Test
    public void getPackageDeclarationFromCompilationUnitTest() {
        OracleDatapoint oracleDatapoint = oracleDatapoints.get(1);
        CompilationUnit cu = javaParser.parse(oracleDatapoint.getClassSourceCode()).getResult().get();
        try {
            PackageDeclaration packageDeclaration = getPackageDeclarationFromCompilationUnit(cu);
            assertEquals("org.apache.commons.math3.analysis.polynomials", packageDeclaration.getNameAsString());
        } catch (PackageDeclarationNotFoundException e) {
            fail();
        }
    }

    @Test
    public void removeArrayTest() {
        ResolvedType intType = ResolvedPrimitiveType.byName("int");
        ResolvedType arrayType = new ResolvedArrayType(intType);
        assertEquals(intType.toString(), removeArray(arrayType).toString());
    }

    @Test
    public void isGenericTest() {
        OracleDatapoint oracleDatapoint = oracleDatapoints.get(0);
        TypeDeclaration<?> jpClass = getClassOrInterface(oracleDatapoint.getClassSourceCode(), oracleDatapoint.getClassName());
        CallableDeclaration<?> jpCallable = getMethodDeclaration(oracleDatapoint.getMethodSourceCode());
        String typeName = "T";
        assertNotNull(jpCallable);
        assertTrue(isTypeParameter(typeName, jpCallable, jpClass));
    }

    @Test
    public void getAllAvailableMethodUsagesTest() {
        OracleDatapoint oracleDatapoint = oracleDatapoints.get(1);
        TypeDeclaration<?> jpClass = getClassOrInterface(oracleDatapoint.getClassSourceCode(), oracleDatapoint.getClassName());
        try {
            List<String> availableMethodList = getAllAvailableMethodUsages(jpClass)
                    .stream()
                    .map(MethodUsage::getName)
                    .collect(Collectors.toList());
            availableMethodList = new ArrayList<>(new LinkedHashSet<>(availableMethodList)).stream().sorted().collect(Collectors.toList());
            String expected = "[add, clone, degree, " +
                    "derivative, differentiate, equals, " +
                    "evaluate, finalize, getClass, " +
                    "getCoefficients, hashCode, multiply, " +
                    "negate, notify, notifyAll, " +
                    "polynomialDerivative, subtract, toString, " +
                    "value, wait]";
            String actual = availableMethodList.toString();
            assertEquals(expected, actual);
        } catch (JPClassNotFoundException e) {
            fail();
        }
    }

    @Test
    public void getAllAvailableResolvedFieldsTest() {
        OracleDatapoint oracleDatapoint = oracleDatapoints.get(1);
        TypeDeclaration<?> jpClass = getClassOrInterface(oracleDatapoint.getClassSourceCode(), oracleDatapoint.getClassName());
        try {
            List<String> availableFieldsList = getAllAvailableResolvedFields(jpClass)
                    .stream()
                    .map(ResolvedDeclaration::getName)
                    .collect(Collectors.toList());
            availableFieldsList = new ArrayList<>(new LinkedHashSet<>(availableFieldsList)).stream().sorted().collect(Collectors.toList());
            String expected = "[coefficients, serialVersionUID]";
            String actual = availableFieldsList.toString();
            assertEquals(expected, actual);
        } catch (JPClassNotFoundException e) {
            fail();
        }
    }

    @Test
    public void getCompilationUnitFromFileTest() {
        Path projectsPath = Paths.get("src", "main", "java", "star", "tratto", "data", "OracleType.java");
        Optional<CompilationUnit> optionalCu = getCompilationUnit(projectsPath);
        assertTrue(optionalCu.isPresent());
        CompilationUnit cu = optionalCu.get();
        try {
            PackageDeclaration packageDeclaration = getPackageDeclarationFromCompilationUnit(cu);
            String packageName = packageDeclaration.getNameAsString();
            assertEquals("star.tratto.data", packageName);
        } catch (PackageDeclarationNotFoundException e) {
            fail();
        }
    }

    @Test
    public void isNonPrivateNonStaticAttributeTest() {
        OracleDatapoint oracleDatapoint = oracleDatapoints.get(1);
        TypeDeclaration<?> jpClass = getClassOrInterface(oracleDatapoint.getClassSourceCode(), oracleDatapoint.getClassName());
        FieldDeclaration jpField = jpClass.getFields().get(0);
        assertFalse(isNonPrivateNonStaticAttribute(jpField.resolve()));
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
        assertEquals(expected, isNonPrivateNonStaticNonVoidMethod(methodUsage));
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
