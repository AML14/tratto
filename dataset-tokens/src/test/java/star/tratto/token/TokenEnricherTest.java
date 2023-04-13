package star.tratto.token;

import org.javatuples.Triplet;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import star.tratto.dataset.OracleDatapoint;
import star.tratto.oraclegrammar.custom.Parser;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static star.tratto.TestUtilities.readOracleDatapointsFromOraclesDataset;
import static star.tratto.oraclegrammar.custom.Splitter.split;
import static star.tratto.token.TokenEnricher.*;
import static star.tratto.token.TokenSuggester.getNextLegalTokensAccordingToGrammar;

public class TokenEnricherTest {

    private static final Parser parser = Parser.getInstance();
    private static final List<OracleDatapoint> oracleDatapoints = readOracleDatapointsFromOraclesDataset();

    @ParameterizedTest(name = "{0}")
    @MethodSource("getEnrichedTokensPlusInfoParameterizedTestData")
    public void getEnrichedTokensPlusInfoTest(String testName, String partialExpression, OracleDatapoint oracleDatapoint, List<Triplet<String, String, List<String>>> expectedTokens) {
        List<String> partialExpressionTokens = split(parser.getPartialOracle(partialExpression));

        // Preconditions
        assertTrue(getNextLegalTokensAccordingToGrammar(partialExpressionTokens).contains("someVarOrClassOrFieldOrMethod"));

        // Test
        List<Triplet<String, String, List<String>>> enrichedTokens = getEnrichedTokensPlusInfo(partialExpressionTokens, oracleDatapoint);
        assertEquals(expectedTokens.size(), enrichedTokens.size());
        assertTrue(expectedTokens.containsAll(enrichedTokens) && enrichedTokens.containsAll(expectedTokens));
    }

    private static Stream<Arguments> getEnrichedTokensPlusInfoParameterizedTestData() {
        return Stream.of(
                Arguments.of("getEnrichedTokensPlusInfo_EmptyOracleTest", "", oracleDatapoints.get(1), List.of(
                        Triplet.with("t", "MethodArgument", List.of("org.apache.commons.math3.analysis.differentiation", "DerivativeStructure")),
                        Triplet.with("RealFieldElement", "Class", List.of("org.apache.commons.math3", "RealFieldElement")),
                        Triplet.with("Primes", "Class", List.of("org.apache.commons.math3.primes", "Primes")),
                        Triplet.with("DifferentiableUnivariateFunction", "Class", List.of("org.apache.commons.math3.analysis", "DifferentiableUnivariateFunction")),
                        Triplet.with("UnivariateFunction", "Class", List.of("org.apache.commons.math3.analysis", "UnivariateFunction")),
                        Triplet.with("TrivariateFunction", "Class", List.of("org.apache.commons.math3.analysis", "TrivariateFunction")),
                        Triplet.with("MultivariateMatrixFunction", "Class", List.of("org.apache.commons.math3.analysis", "MultivariateMatrixFunction")),
                        Triplet.with("ParametricUnivariateFunction", "Class", List.of("org.apache.commons.math3.analysis", "ParametricUnivariateFunction")),
                        Triplet.with("UnivariateDifferentiableVectorFunction", "Class", List.of("org.apache.commons.math3.analysis.differentiation", "UnivariateDifferentiableVectorFunction")),
                        Triplet.with("UnivariateDifferentiableMatrixFunction", "Class", List.of("org.apache.commons.math3.analysis.differentiation", "UnivariateDifferentiableMatrixFunction")),
                        Triplet.with("DSCompiler", "Class", List.of("org.apache.commons.math3.analysis.differentiation", "DSCompiler")),
                        Triplet.with("SparseGradient", "Class", List.of("org.apache.commons.math3.analysis.differentiation", "SparseGradient")),
                        Triplet.with("UnivariateFunctionDifferentiator", "Class", List.of("org.apache.commons.math3.analysis.differentiation", "UnivariateFunctionDifferentiator")),
                        Triplet.with("UnivariateDifferentiableFunction", "Class", List.of("org.apache.commons.math3.analysis.differentiation", "UnivariateDifferentiableFunction")),
                        Triplet.with("MultivariateVectorFunction", "Class", List.of("org.apache.commons.math3.analysis", "MultivariateVectorFunction")),
                        Triplet.with("DifferentiableMultivariateVectorFunction", "Class", List.of("org.apache.commons.math3.analysis", "DifferentiableMultivariateVectorFunction")),
                        Triplet.with("PolynomialFunctionNewtonForm", "Class", List.of("org.apache.commons.math3.analysis.polynomials", "PolynomialFunctionNewtonForm")),
                        Triplet.with("PolynomialFunctionLagrangeForm", "Class", List.of("org.apache.commons.math3.analysis.polynomials", "PolynomialFunctionLagrangeForm")),
                        Triplet.with("PolynomialSplineFunction", "Class", List.of("org.apache.commons.math3.analysis.polynomials", "PolynomialSplineFunction")),
                        Triplet.with("PolynomialsUtils", "Class", List.of("org.apache.commons.math3.analysis.polynomials", "PolynomialsUtils")),
                        Triplet.with("PolynomialFunction", "Class", List.of("org.apache.commons.math3.analysis.polynomials", "PolynomialFunction")),
                        Triplet.with("Complex", "Class", List.of("org.apache.commons.math3.complex", "Complex")),
                        Triplet.with("Field", "Class", List.of("org.apache.commons.math3", "Field")),
                        Triplet.with("FieldElement", "Class", List.of("org.apache.commons.math3", "FieldElement")),
                        Triplet.with("DerivativeStructure", "Class", List.of("org.apache.commons.math3.analysis.differentiation", "DerivativeStructure"))
                )),
                Arguments.of("getEnrichedTokensPlusInfo_NoInstanceOfTest", "divisor.", oracleDatapoints.get(3), List.of(
                        Triplet.with("isInfinite", "MethodName", List.of("org.apache.commons.math3.complex", "Complex", "public boolean isInfinite()")),
                        Triplet.with("isNaN", "MethodName", List.of("org.apache.commons.math3.complex", "Complex", "public boolean isNaN()")),
                        Triplet.with("equals", "MethodName", List.of("java.lang", "Object", "public boolean equals(Object obj)")),
                        Triplet.with("toString", "MethodName", List.of("java.lang", "Object", "public String toString()")),
                        Triplet.with("getClass", "MethodName", List.of("java.lang", "Object", "public final native Class<?> getClass()")),
                        Triplet.with("hashCode", "MethodName", List.of("java.lang", "Object", "public native int hashCode()")),
                        Triplet.with("isNaN", "ClassField", List.of("org.apache.commons.math3.complex", "Complex", "protected final transient boolean isNaN"))
                )),
                Arguments.of("getEnrichedTokensPlusInfo_InstanceOfDerivativeStructureTest", "this instanceof PolynomialFunction && (t instanceof", oracleDatapoints.get(2), List.of(
                        Triplet.with("DerivativeStructure", "Class", List.of("org.apache.commons.math3.analysis.differentiation", "DerivativeStructure")),
                        Triplet.with("RealFieldElement", "Class", List.of("org.apache.commons.math3", "RealFieldElement")),
                        Triplet.with("FieldElement", "Class", List.of("org.apache.commons.math3", "FieldElement"))
                )),
                Arguments.of("getStaticAttributesAndMethods_AbstractBagDecoratorClassTest", "AbstractBagDecorator.", oracleDatapoints.get(0), List.of()),
                Arguments.of("getStaticAttributesAndMethods_CollectionBagClassTest", "CollectionBag.", oracleDatapoints.get(0), List.of(
                        Triplet.with("collectionBag", "MethodName", List.of("org.apache.commons.collections4.bag", "CollectionBag", "public static <E> Bag<E> collectionBag(final Bag<E> bag)"))
                )),
                Arguments.of("getStaticAttributesAndMethods_BagUtilsClassTest", "BagUtils.", oracleDatapoints.get(0), List.of(
                        Triplet.with("EMPTY_BAG", "ClassField", List.of("org.apache.commons.collections4", "BagUtils", "public static final Bag EMPTY_BAG = UnmodifiableBag.unmodifiableBag(new HashBag<>())"))
                ))

        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("getClassNamesAfterInstanceOfParameterizedTestData")
    public void getClassNamesAfterInstanceOfTest(String testName, String partialExpression, OracleDatapoint oracleDatapoint, List<Triplet<String, String, List<String>>> expectedTokens) {
        List<String> partialExpressionTokens = split(parser.getPartialOracle(partialExpression));

        // Preconditions
        assertTrue(getNextLegalTokensAccordingToGrammar(partialExpressionTokens).contains("someVarOrClassOrFieldOrMethod"));

        // Test
        List<Triplet<String, String, List<String>>> enrichedTokens = getClassNamesAfterInstanceOf(partialExpressionTokens, oracleDatapoint);
        assertEquals(expectedTokens.size(), enrichedTokens.size());
        assertTrue(expectedTokens.containsAll(enrichedTokens) && enrichedTokens.containsAll(expectedTokens));
    }

    private static Stream<Arguments> getClassNamesAfterInstanceOfParameterizedTestData() {
        return Stream.of(
                Arguments.of("getClassNamesAfterInstanceOf_NoInstanceOfTest", "divisor.", oracleDatapoints.get(3), List.of()),
                Arguments.of("getClassNamesAfterInstanceOf_ComplexTest", "divisor instanceof", oracleDatapoints.get(3), List.of(
                        Triplet.with("Complex", "Class", List.of("org.apache.commons.math3.complex", "Complex")),
                        Triplet.with("FieldElement", "Class", List.of("org.apache.commons.math3", "FieldElement"))
                )),
                Arguments.of("getClassNamesAfterInstanceOf_PolynomialFunctionTest", "this instanceof", oracleDatapoints.get(2), List.of(
                        Triplet.with("PolynomialFunction", "Class", List.of("org.apache.commons.math3.analysis.polynomials", "PolynomialFunction")),
                        Triplet.with("UnivariateDifferentiableFunction", "Class", List.of("org.apache.commons.math3.analysis.differentiation", "UnivariateDifferentiableFunction")),
                        Triplet.with("DifferentiableUnivariateFunction", "Class", List.of("org.apache.commons.math3.analysis", "DifferentiableUnivariateFunction")),
                        Triplet.with("UnivariateFunction", "Class", List.of("org.apache.commons.math3.analysis", "UnivariateFunction"))
                )),
                Arguments.of("getClassNamesAfterInstanceOf_doubleArrayTest", "this.getCoefficients() instanceof", oracleDatapoints.get(1), List.of()),
                Arguments.of("getClassNamesAfterInstanceOf_intTest", "this.getCoefficients().length instanceof", oracleDatapoints.get(1), List.of()),
                Arguments.of("getClassNamesAfterInstanceOf_DerivativeStructureTest", "this instanceof PolynomialFunction && (t instanceof", oracleDatapoints.get(2), List.of(
                        Triplet.with("DerivativeStructure", "Class", List.of("org.apache.commons.math3.analysis.differentiation", "DerivativeStructure")),
                        Triplet.with("RealFieldElement", "Class", List.of("org.apache.commons.math3", "RealFieldElement")),
                        Triplet.with("FieldElement", "Class", List.of("org.apache.commons.math3", "FieldElement"))
                ))
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("getStaticAttributesAndMethodsParameterizedTestData")
    public void getStaticAttributesAndMethodsTest(String testName, String partialExpression, OracleDatapoint oracleDatapoint, List<Triplet<String, String, List<String>>> expectedTokens) {
        List<String> partialExpressionTokens = split(parser.getPartialOracle(partialExpression));

        // Preconditions
        assertTrue(getNextLegalTokensAccordingToGrammar(partialExpressionTokens).contains("someVarOrClassOrFieldOrMethod"));

        // Test
        List<Triplet<String, String, List<String>>> enrichedTokens = getStaticAttributesAndMethods(partialExpressionTokens, oracleDatapoint);
        assertEquals(expectedTokens.size(), enrichedTokens.size());
        assertTrue(expectedTokens.containsAll(enrichedTokens) && enrichedTokens.containsAll(expectedTokens));
    }

    private static Stream<Arguments> getStaticAttributesAndMethodsParameterizedTestData() {
        return Stream.of(
                Arguments.of("getStaticAttributesAndMethods_EmptyOracleTest", "", oracleDatapoints.get(0), List.of()),
                Arguments.of("getStaticAttributesAndMethods_OneTokenTest", "(", oracleDatapoints.get(0), List.of()),
                Arguments.of("getStaticAttributesAndMethods_TwoTokensWrongFormatTest", "methodResultID &&", oracleDatapoints.get(0), List.of()),
                Arguments.of("getStaticAttributesAndMethods_TwoTokensFirstTokenNotClassTest", "o1.", oracleDatapoints.get(0), List.of()),
                Arguments.of("getStaticAttributesAndMethods_AbstractBagDecoratorClassTest", "AbstractBagDecorator.", oracleDatapoints.get(0), List.of()),
                Arguments.of("getStaticAttributesAndMethods_CollectionBagClassTest", "CollectionBag.", oracleDatapoints.get(0), List.of(
                        Triplet.with("collectionBag", "MethodName", List.of("org.apache.commons.collections4.bag", "CollectionBag", "public static <E> Bag<E> collectionBag(final Bag<E> bag)"))
                )),
                Arguments.of("getStaticAttributesAndMethods_BagUtilsClassTest", "BagUtils.", oracleDatapoints.get(0), List.of(
                        Triplet.with("EMPTY_BAG", "ClassField", List.of("org.apache.commons.collections4", "BagUtils", "public static final Bag EMPTY_BAG = UnmodifiableBag.unmodifiableBag(new HashBag<>())"))
                )),
                Arguments.of("getStaticAttributesAndMethods_2ndToLastTokenNotClassTest", "divisor.isInfinite() && (this.", oracleDatapoints.get(3), List.of()),
                Arguments.of("getStaticAttributesAndMethods_ComplexClassTest", "divisor.isInfinite() && (this.isInfinite())==false ? (methodResultID.isNaN())==false && methodResultID == Complex.", oracleDatapoints.get(3), List.of(
                        Triplet.with("I", "ClassField", List.of("org.apache.commons.math3.complex", "Complex", "public static final Complex I = new Complex(0.0, 1.0)")),
                        Triplet.with("NaN", "ClassField", List.of("org.apache.commons.math3.complex", "Complex", "public static final Complex NaN = new Complex(Double.NaN, Double.NaN)")),
                        Triplet.with("INF", "ClassField", List.of("org.apache.commons.math3.complex", "Complex", "public static final Complex INF = new Complex(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY)")),
                        Triplet.with("ONE", "ClassField", List.of("org.apache.commons.math3.complex", "Complex", "public static final Complex ONE = new Complex(1.0, 0.0)")),
                        Triplet.with("ZERO", "ClassField", List.of("org.apache.commons.math3.complex", "Complex", "public static final Complex ZERO = new Complex(0.0, 0.0)"))
                ))
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("getNonStaticAttributesAndMethodsParameterizedTestData")
    public void getNonStaticAttributesAndMethodsTest(String testName, String partialExpression, OracleDatapoint oracleDatapoint, List<Triplet<String, String, List<String>>> expectedTokens) {
        List<String> partialExpressionTokens = split(parser.getPartialOracle(partialExpression));

        // Preconditions
        assertTrue(getNextLegalTokensAccordingToGrammar(partialExpressionTokens).contains("someVarOrClassOrFieldOrMethod"));

        // Test
        List<Triplet<String, String, List<String>>> enrichedTokens = getNonStaticAttributesAndMethods(partialExpressionTokens, oracleDatapoint);
        assertEquals(expectedTokens.size(), enrichedTokens.size());
        assertTrue(expectedTokens.containsAll(enrichedTokens) && enrichedTokens.containsAll(expectedTokens));
    }

    private static Stream<Arguments> getNonStaticAttributesAndMethodsParameterizedTestData() {
        return Stream.of(
                Arguments.of("getNonStaticAttributesAndMethods_EmptyOracleTest", "", oracleDatapoints.get(0), List.of()),
                Arguments.of("getNonStaticAttributesAndMethods_OneTokenTest", "(", oracleDatapoints.get(0), List.of()),
                Arguments.of("getNonStaticAttributesAndMethods_TwoTokensWrongFormatTest", "methodResultID &&", oracleDatapoints.get(0), List.of()),
                Arguments.of("getNonStaticAttributesAndMethods_TwoTokensFirstTokenClassTest", "CollectionBag.", oracleDatapoints.get(0), List.of()),
                Arguments.of("getNonStaticAttributesAndMethods_booleanReturnTypeTest", "methodResultID.", oracleDatapoints.get(0), List.of()),
                Arguments.of("getNonStaticAttributesAndMethods_ObjectReturnTypeTest", "((o1==null)==false) && (o1.", oracleDatapoints.get(0), List.of(
                        Triplet.with("equals", "MethodName", List.of("java.lang", "Object", "public boolean equals(Object obj)")),
                        Triplet.with("toString", "MethodName", List.of("java.lang", "Object", "public String toString()")),
                        Triplet.with("getClass", "MethodName", List.of("java.lang", "Object", "public final native Class<?> getClass()")),
                        Triplet.with("hashCode", "MethodName", List.of("java.lang", "Object", "public native int hashCode()"))
                )),
                Arguments.of("getNonStaticAttributesAndMethods_thisPolynomialFunctionClassTest", "this.", oracleDatapoints.get(1), List.of(
                        Triplet.with("getCoefficients", "MethodName", List.of("org.apache.commons.math3.analysis.polynomials", "PolynomialFunction", "public double[] getCoefficients()")),
                        Triplet.with("value", "MethodName", List.of("org.apache.commons.math3.analysis.polynomials", "PolynomialFunction", "public double value(double x)")),
                        Triplet.with("clone", "MethodName", List.of("java.lang", "Object", "protected native Object clone() throws CloneNotSupportedException"))
                )),
                Arguments.of("getNonStaticAttributesAndMethods_thisPolynomialFunctionClassTest", "this.getCoefficients().", oracleDatapoints.get(1), List.of(
                        Triplet.with("clone", "MethodName", List.of("java.lang", "Object", "protected native Object clone() throws CloneNotSupportedException")),
                        Triplet.with("length", "ClassField", List.of("", "double[]", ""))
                )),
                Arguments.of("getNonStaticAttributesAndMethods_methodResultIDDerivativeStructureClassTest", "someVar.someMethod() && methodResultID.", oracleDatapoints.get(1), List.of(
                        Triplet.with("getFreeParameters", "MethodName", List.of("org.apache.commons.math3.analysis.differentiation", "DerivativeStructure", "public int getFreeParameters()")),
                        Triplet.with("clone", "MethodName", List.of("java.lang", "Object", "protected native Object clone() throws CloneNotSupportedException"))
                )),
                Arguments.of("getNonStaticAttributesAndMethods_methodArgumentDerivativeStructureClassTest", "someVar.someMethod() && t.", oracleDatapoints.get(1), List.of(
                        Triplet.with("getFreeParameters", "MethodName", List.of("org.apache.commons.math3.analysis.differentiation", "DerivativeStructure", "public int getFreeParameters()")),
                        Triplet.with("clone", "MethodName", List.of("java.lang", "Object", "protected native Object clone() throws CloneNotSupportedException"))
                )),
                Arguments.of("getNonStaticAttributesAndMethods_ComplexClassTest", "divisor.isInfinite() && (this.isInfinite())==false ? (methodResultID.", oracleDatapoints.get(3), List.of(
                        Triplet.with("isInfinite", "MethodName", List.of("org.apache.commons.math3.complex", "Complex", "public boolean isInfinite()")),
                        Triplet.with("isNaN", "MethodName", List.of("org.apache.commons.math3.complex", "Complex", "public boolean isNaN()")),
                        Triplet.with("equals", "MethodName", List.of("java.lang", "Object", "public boolean equals(Object obj)")),
                        Triplet.with("toString", "MethodName", List.of("java.lang", "Object", "public String toString()")),
                        Triplet.with("getClass", "MethodName", List.of("java.lang", "Object", "public final native Class<?> getClass()")),
                        Triplet.with("hashCode", "MethodName", List.of("java.lang", "Object", "public native int hashCode()")),
                        Triplet.with("isNaN", "ClassField", List.of("org.apache.commons.math3.complex", "Complex", "protected final transient boolean isNaN"))
                ))
        );
    }
}
