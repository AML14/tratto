package star.tratto.oraclegrammar.custom;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import star.tratto.oraclegrammar.trattoGrammar.ClauseWithVars;
import star.tratto.oraclegrammar.trattoGrammar.MethodCall;
import star.tratto.oraclegrammar.trattoGrammar.Oracle;
import star.tratto.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static star.tratto.oraclegrammar.custom.Parser.*;
import static star.tratto.oraclegrammar.custom.Splitter.split;
import static star.tratto.TestUtilities.readOraclesFromExternalFiles;
import static star.tratto.util.StringUtils.compactExpression;

public class ParserTest {

    private static final Parser parser = Parser.getInstance();

    /**
     * This test is to check that, for all TrattoGrammar expressions contained in
     * the resources files, there is no partial expression such that the parser
     * will report an error in the same column five or more times.
     */
    @Test
    public void detectPersistentErrorColumnsTest() {
        // Read oracles from external files
        List<String> stringOracles = readOraclesFromExternalFiles();

        // For each oracle, parse and split
        for (String stringOracle : stringOracles) {
            List<List<Integer>> errorColumns = new ArrayList<>();
            Oracle oracle = parser.getOracle(stringOracle);
            List<String> oracleTokens = split(oracle);
            StringBuilder oracleSoFar = new StringBuilder();
            parser.parseOracle(oracleSoFar.toString());

            // For each token, add it to the oracle so far and save errors when parsing partial oracle
            for (String token : oracleTokens) {
                oracleSoFar.append(" " + token);
                parser.parseOracle(oracleSoFar.toString());

                errorColumns.add(new ArrayList<>());
                for (Resource.Diagnostic error : parser.getResource().getErrors()) {
                    errorColumns.get(errorColumns.size()-1).add(error.getColumn());
                }
            }

            List<Integer> flattenedErrorColumns = errorColumns
                    .stream()
                    .flatMap(List::stream)
                    .collect(Collectors.toList());

            TreeMap<Integer, Integer> columnFrequencies = new TreeMap<>(frequencyMap(flattenedErrorColumns));

            TreeMap<Integer, Integer> columnsToCheck = new TreeMap<>(columnFrequencies
                    .entrySet()
                    .stream()
                    .filter(entry -> entry.getValue() >= 5)
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));

            if (!columnsToCheck.isEmpty()) {
                fail("The following oracle contains a sequence of tokens such that the same column is marked with an error five or more times:\n"
                        + stringOracle + "\n\n" + "Columns marked with errors three or more times:\n" + columnsToCheck);
            }
        }
    }

    private static Map<Integer, Integer> frequencyMap(List<Integer> list) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int number : list) {
            frequencyMap.put(number, frequencyMap.getOrDefault(number, 0) + 1);
        }
        return frequencyMap;
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("findLastElementWithModifiersParameterizedTestData")
    public void findLastElementWithModifiersTest(String testName, String partialOracle, String expectedLastElementWithModifiers) {
        EObject lastElementWithModifiers = parser.findLastElementWithModifiers(partialOracle);
        assertEquals(compactExpression(expectedLastElementWithModifiers), compactExpression(split(lastElementWithModifiers)));
    }

    private static Stream<Arguments> findLastElementWithModifiersParameterizedTestData() {
        return Stream.of(
                Arguments.of("findLastElementWithModifiersEmptyOracleTest", "", ""),
                Arguments.of("findLastElementWithModifiersNoElementTest", "arg2 && arg1 >= arg2+3", ""),
                Arguments.of("findLastElementWithModifiersOneDotTest", "arg2 && arg1.", "arg1."),
                Arguments.of("findLastElementWithModifiersOneDotAndFieldTest", "arg2 && this.field", "this.field"),
                Arguments.of("findLastElementWithModifiersOneDotAndMethodTest", "arg2 && methodResultID.method() ?", "methodResultID.method()"),
                Arguments.of("findLastElementWithModifiersOneDotAndMethodNotFinishedTest", "arg2 && methodResultID.method(someVar, secondVar", "methodResultID.method(someVar,secondVar"),
                Arguments.of("findLastElementWithModifiersOneDotAndMethodAndFieldThenThisTest", "arg2 && this.method().field ? this", "this.method().field"),
                Arguments.of("findLastElementWithModifiersOneDotAndMethodAndFieldThenMethodResultIDTest", "arg2 && this.method().field ? methodResultID", "this.method().field"),
                Arguments.of("findLastElementWithModifiersOneDotAndMethodAndFieldThenArgTest", "arg2 && this.method().field ? arg3", "this.method().field"),
                Arguments.of("findLastElementWithModifiersSecondOneDotTest", "arg2 && arg1.method().field ? methodResultID.", "methodResultID."),
                Arguments.of("findLastElementWithModifiersSecondOneDot2Test", "arg2 && arg1.method().field ? arg3.", "arg3."),
                Arguments.of("findLastElementWithModifiersSecondOneDotAndMethodNameTest", "arg2 && arg1.method().field ? methodResultID.methodName", "methodResultID.methodName"),
                Arguments.of("findLastElementWithModifiersSecondFullTest", "arg2 && arg1.method() ? hey.someField : true;", "hey.someField")
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("findLastElementWithMethodModifiersParameterizedTestData")
    public void findLastElementWithMethodModifiersTest(String testName, String partialOracle, String expectedLastElementWithMethodModifiers) {
        EObject lastElementWithMethodModifiers = parser.findLastElementWithUnfinishedMethodModifiers(partialOracle);
        assertEquals(compactExpression(expectedLastElementWithMethodModifiers), compactExpression(split(lastElementWithMethodModifiers)));
    }

    private static Stream<Arguments> findLastElementWithMethodModifiersParameterizedTestData() {
        return Stream.of(
                Arguments.of("findLastElementWithMethodModifiersTest1", "", ""),
                Arguments.of("findLastElementWithMethodModifiersTest2", "true", ""),
                Arguments.of("findLastElementWithMethodModifiersTest3", "this", ""),
                Arguments.of("findLastElementWithMethodModifiersTest4", "methodResultID.someProperty", ""),
                Arguments.of("findLastElementWithMethodModifiersTest5", "arg1 || Arrays.stream(arg2)", ""),
                Arguments.of("findLastElementWithMethodModifiersTest6", "arg1 || Arrays.stream(arg2).", ""),
                Arguments.of("findLastElementWithMethodModifiersTest7", "arg1 || Arrays.stream(arg2).anyMatch(jdVar ->", ""),
                Arguments.of("findLastElementWithMethodModifiersTest8", "SomeClass.someMethod(", "SomeClass.someMethod("),
                Arguments.of("findLastElementWithMethodModifiersTest9", "arg1 && arg2.someMethod() ? arg3.field.method(a).secondMethod(b, c.d", "arg3.field.method(a).secondMethod(b, c.d"),
                Arguments.of("findLastElementWithMethodModifiersTest10", "arg1.method() && SomeClass.someField.someMethod(arg3, arg4) ? arg5.method(a).methodB(b, c.d(", "c.d("),
                Arguments.of("findLastElementWithMethodModifiersTest11", "SomeClass.someMethod().someField", "")
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("findElementPrecedingLastInstanceOfParameterizedTestData")
    public void findElementPrecedingLastInstanceOfTest(String testName, String partialOracle, String expectedElementPrecedingLastInstanceOf) {
        Object elementPrecedingLastInstanceOf = parser.findElementPrecedingLastInstanceOf(partialOracle);
        assertEquals(compactExpression(expectedElementPrecedingLastInstanceOf), compactExpression(split(elementPrecedingLastInstanceOf)));
    }

    private static Stream<Arguments> findElementPrecedingLastInstanceOfParameterizedTestData() {
        return Stream.of(
                Arguments.of("findElementPrecedingLastInstanceOfEmptyOracleTest", "", ""),
                Arguments.of("findElementPrecedingLastInstanceOfThisInstanceOfTest", "this instanceof", "this"),
                Arguments.of("findElementPrecedingLastInstanceOfSecondInstanceOfTest", "this instanceof SomeClass && someArg instanceof", "someArg"),
                Arguments.of("findElementPrecedingLastInstanceOfThisInstanceOfSomethingElseTest", "this instanceof SomeClass", "this"),
                Arguments.of("findElementPrecedingLastInstanceOfThisInstanceOfSomethingElseLongerTest", "this instanceof SomeClass && someArg", "this"),
                Arguments.of("findElementPrecedingLastInstanceOfNoInstanceOfTest", "arg1", ""),
                Arguments.of("findElementPrecedingLastInstanceOfMethodResultIDTest", "methodResultID instanceof", "methodResultID"),
                Arguments.of("findElementPrecedingLastInstanceOfArgPlusModifiersTest", "someArg.someMethod() instanceof", "someArg.someMethod()"),
                Arguments.of("findElementPrecedingLastInstanceOfArgMoreModifiersTest", "someArg.someField.someMethod(anotherArg,secondArg.modifier) instanceof", "someArg.someField.someMethod(anotherArg,secondArg.modifier)")
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("findElementPrecedingLastNonEqIneqOpParameterizedTestData")
    public void findElementPrecedingLastNonEqIneqOpTest(String testName, String partialOracle, String expectedElementPrecedingLastNonEqIneqOp) {
        Object elementPrecedingLastNonEqIneqOp = parser.findElementPrecedingLastNonEqIneqOp(partialOracle);
        assertEquals(compactExpression(expectedElementPrecedingLastNonEqIneqOp), compactExpression(split(elementPrecedingLastNonEqIneqOp)));
    }

    private static Stream<Arguments> findElementPrecedingLastNonEqIneqOpParameterizedTestData() {
        return Stream.of(
                Arguments.of("findElementPrecedingLastNonEqIneqOpEmptyOracleTest", "", ""),
                Arguments.of("findElementPrecedingLastNonEqIneqOpMethodResultIDTest", "methodResultID >", "methodResultID"),
                Arguments.of("findElementPrecedingLastNonEqIneqOpSecondOpTest", "arg1 <= 3.0 && someArg >=", "someArg"),
                Arguments.of("findElementPrecedingLastNonEqIneqOpArg1OpArg2SomethingElseTest", "arg1 < arg2", "arg1"),
                Arguments.of("findElementPrecedingLastNonEqIneqOpArg1OpSomethingElseLongerTest", "arg1 > arg2.someVar && someArg", "arg1"),
                Arguments.of("findElementPrecedingLastNonEqIneqOpNoOpTest", "arg1", ""),
                Arguments.of("findElementPrecedingLastNonEqIneqOpArgPlusModifiersTest", "someArg.someMethod() >=", "someArg.someMethod()"),
                Arguments.of("findElementPrecedingLastNonEqIneqOpArgMoreModifiersTest", "someArg.someField.someMethod(anotherArg,secondArg.modifier) <", "someArg.someField.someMethod(anotherArg,secondArg.modifier)")
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("findLastCanEvalToPrimInClauseWithVarsParameterizedTestData")
    public void findLastCanEvalToPrimInClauseWithVarsTest(String testName, String partialOracle, String expectedLastCanEvalToPrimInClauseWithVars) {
        Object lastCanEvalToPrimInClauseWithVars = parser.findLastCanEvalToPrimInClauseWithVars(partialOracle);
        assertEquals(compactExpression(expectedLastCanEvalToPrimInClauseWithVars), compactExpression(split(lastCanEvalToPrimInClauseWithVars)));
    }

    private static Stream<Arguments> findLastCanEvalToPrimInClauseWithVarsParameterizedTestData() {
        return Stream.of(
                Arguments.of("findLastCanEvalToPrimInClauseWithVarsEmptyOracleTest", "", ""),
                Arguments.of("findLastCanEvalToPrimInClauseWithVars1Test", "((o1==", "o1"),
                Arguments.of("findLastCanEvalToPrimInClauseWithVars2Test", "this.someVar==arg1 && methodResultID==", "methodResultID"),
                Arguments.of("findLastCanEvalToPrimInClauseWithVars3Test", "this.getCoefficients()==", "this.getCoefficients()"),
                Arguments.of("findLastCanEvalToPrimInClauseWithVars4Test", "this.getCoefficients().length>=", "this.getCoefficients().length"),
                Arguments.of("findLastCanEvalToPrimInClauseWithVars5Test", "this.getCoefficients().length==invented^", "this.getCoefficients().length"),
                Arguments.of("findLastCanEvalToPrimInClauseWithVars6Test", "this.getCoefficients().length==~invented^", "this.getCoefficients().length"),
                Arguments.of("findLastCanEvalToPrimInClauseWithVars6Test", "Arrays.stream(someArg).anyMatch(jdVar -> jdVar.someAttr==", "jdVar.someAttr")
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("findLastCanEvalToPrimInOtherCompElemParameterizedTestData")
    public void findLastCanEvalToPrimInOtherCompElemTest(String testName, String partialOracle, String expectedLastCanEvalToPrimInOtherCompElem) {
        Object lastCanEvalToPrimInOtherCompElem = parser.findLastCanEvalToPrimInOtherCompElem(partialOracle);
        assertEquals(compactExpression(expectedLastCanEvalToPrimInOtherCompElem), compactExpression(split(lastCanEvalToPrimInOtherCompElem)));
    }

    private static Stream<Arguments> findLastCanEvalToPrimInOtherCompElemParameterizedTestData() {
        return Stream.of(
                Arguments.of("findLastCanEvalToPrimInOtherCompElemEmptyOracleTest", "", ""),
                Arguments.of("findLastCanEvalToPrimInOtherCompElem1Test", "((o1==", ""),
                Arguments.of("findLastCanEvalToPrimInOtherCompElem2Test", "this.someVar==arg1 && methodResultID==", "arg1"),
                Arguments.of("findLastCanEvalToPrimInOtherCompElem3Test", "this.getCoefficients()==", ""),
                Arguments.of("findLastCanEvalToPrimInOtherCompElem4Test", "this.getCoefficients()>=~someBitVar.someMethod()", "someBitVar.someMethod()"),
                Arguments.of("findLastCanEvalToPrimInOtherCompElem5Test", "something && (otherThing.varr || arg2!=arg3+4", "arg3"),
                Arguments.of("findLastCanEvalToPrimInOtherCompElem6Test", "this.getCoefficients().length==~invented^", "invented"),
                Arguments.of("findLastCanEvalToPrimInOtherCompElem7Test", "Arrays.stream(someArg).anyMatch(jdVar -> jdVar.someAttr==methodResultID", "methodResultID")
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("findLastClauseWithVarsParameterizedTestData")
    public void findLastClauseWithVarsTest(String testName, String partialOracle, String expectedLastClauseWithVars) {
        ClauseWithVars lastClauseWithVars = parser.findLastClauseWithVars(partialOracle);
        assertEquals(compactExpression(expectedLastClauseWithVars), compactExpression(split(lastClauseWithVars)));
    }

    private static Stream<Arguments> findLastClauseWithVarsParameterizedTestData() {
        return Stream.of(
                Arguments.of("findLastClauseWithVarsTest1", "", ""),
                Arguments.of("findLastClauseWithVarsTest2", "true", ""),
                Arguments.of("findLastClauseWithVarsTest3", "this", "this"),
                Arguments.of("findLastClauseWithVarsTest4", "methodResultID.someProperty", "methodResultID.someProperty"),
                Arguments.of("findLastClauseWithVarsTest5", "arg1 || Arrays.stream(arg2)", "arg1"),
                Arguments.of("findLastClauseWithVarsTest6", "arg1 || Arrays.stream(arg2).", "arg1"),
                Arguments.of("findLastClauseWithVarsTest7", "arg1 || Arrays.stream(arg2).anyMatch(jdVar ->", "arg1"),
                Arguments.of("findLastClauseWithVarsTest8", "arg1 || Arrays.stream(arg2).anyMatch(jdVar -> jdVar", "jdVar"),
                Arguments.of("findLastClauseWithVarsTest9", "arg1 || Arrays.stream(arg2).anyMatch(jdVar -> jdVar==null", "jdVar==null"),
                Arguments.of("findLastClauseWithVarsTest10", "arg1.stream", "")

                );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("findLastMethodCallParameterizedTestData")
    public void findLastMethodCallTest(String testName, String partialOracle, String expectedLastMethodCall) {
        EObject elementWithModifiers = parser.findLastElementWithModifiers(partialOracle);
        MethodCall lastMethodCall = findLastMethodCall(elementWithModifiers);
        assertEquals(compactExpression(expectedLastMethodCall), compactExpression(split(lastMethodCall)));
    }

    private static Stream<Arguments> findLastMethodCallParameterizedTestData() {
        return Stream.of(
                Arguments.of("findLastMethodNameTest1", "methodResultID.someProperty", ""),
                Arguments.of("findLastMethodNameTest2", "SomeClass.someMethod(", "someMethod("),
                Arguments.of("findLastMethodNameTest3", "arg1 && arg2.someMethod() ? arg3.field.method(a).secondMethod(b, c.d", ""),
                Arguments.of("findLastMethodNameTest4", "arg1 && arg2.someMethod() ? arg3.field.method(a).secondMethod(b, c", "secondMethod(b, c")
        );
    }

    @Test
    public void findLastMethodCallExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> findLastMethodCall(parser.findLastElementWithModifiers("arg1 || Arrays.stream(arg2)")));
        assertThrows(IllegalArgumentException.class, () -> findLastMethodCall(parser.findLastElementWithModifiers("")));
        assertThrows(IllegalArgumentException.class, () -> findLastMethodCall(parser.findLastElementWithModifiers("this")));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("getNArgumentsSoFarParameterizedTestData")
    public void getNArgumentsSoFarTest(String testName, String partialOracle, int expectedNArguments) {
        int nArguments = getNArgumentsSoFar(findLastMethodCall(parser.findLastElementWithUnfinishedMethodModifiers(partialOracle)));
        assertEquals(expectedNArguments, nArguments);
    }

    private static Stream<Arguments> getNArgumentsSoFarParameterizedTestData() {
        return Stream.of(
                Arguments.of("getNArgumentsSoFarTest1", "methodResultID.someMethod(", 0),
                Arguments.of("getNArgumentsSoFarTest2", "SomeClass.someMethod(someArg", 1),
                Arguments.of("getNArgumentsSoFarTest3", "SomeClass.someMethod(someArg, secondArg,", 3),
                Arguments.of("getNArgumentsSoFarTest4", "arg1 && arg2.someMethod() ? arg3.field.method(a).secondMethod(b, c.d", 2)
        );
    }

    @Test
    public void getNArgumentsSoFarExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> getNArgumentsSoFar(findLastMethodCall(parser.findLastElementWithUnfinishedMethodModifiers("arg1 || Arrays.stream(arg2)"))));
        assertThrows(IllegalArgumentException.class, () -> getNArgumentsSoFar(findLastMethodCall(parser.findLastElementWithUnfinishedMethodModifiers(""))));
        assertThrows(IllegalArgumentException.class, () -> getNArgumentsSoFar(findLastMethodCall(parser.findLastElementWithUnfinishedMethodModifiers("this"))));
        assertThrows(IllegalArgumentException.class, () -> getNArgumentsSoFar(findLastMethodCall(parser.findLastElementWithUnfinishedMethodModifiers("methodResultID.someField"))));
        assertThrows(IllegalArgumentException.class, () -> getNArgumentsSoFar(findLastMethodCall(parser.findLastElementWithUnfinishedMethodModifiers("arg1 && arg2.someMethod() ? arg3.field.method(a).secondField"))));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("getLastArgumentParameterizedTestData")
    public void getLastArgumentTest(String testName, String partialOracle, String expectedLastArgument) {
        String lastArgument = compactExpression(split(getLastArgument(findLastMethodCall(parser.findLastElementWithUnfinishedMethodModifiers(partialOracle)))));
        assertEquals(expectedLastArgument, lastArgument);
    }

    private static Stream<Arguments> getLastArgumentParameterizedTestData() {
        return Stream.of(
                Arguments.of("getLastArgumentTest1", "methodResultID.someMethod(", ""),
                Arguments.of("getLastArgumentTest2", "SomeClass.someMethod(someArg", "someArg"),
                Arguments.of("getLastArgumentTest3", "SomeClass.someMethod(someArg, secondArg,", ""),
                Arguments.of("getLastArgumentTest4", "arg1 && arg2.someMethod() ? arg3.field.method(a).secondMethod(b, c.d", "c.d")
        );
    }

    @Test
    public void getLastArgumentExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> getLastArgument(findLastMethodCall(parser.findLastElementWithUnfinishedMethodModifiers("arg1 || Arrays.stream(arg2)"))));
        assertThrows(IllegalArgumentException.class, () -> getLastArgument(findLastMethodCall(parser.findLastElementWithUnfinishedMethodModifiers(""))));
        assertThrows(IllegalArgumentException.class, () -> getLastArgument(findLastMethodCall(parser.findLastElementWithUnfinishedMethodModifiers("this"))));
        assertThrows(IllegalArgumentException.class, () -> getLastArgument(findLastMethodCall(parser.findLastElementWithUnfinishedMethodModifiers("methodResultID.someField"))));
        assertThrows(IllegalArgumentException.class, () -> getLastArgument(findLastMethodCall(parser.findLastElementWithUnfinishedMethodModifiers("arg1 && arg2.someMethod() ? arg3.field.method(a).secondField"))));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("removeLastVarOrClassModifierParameterizedTestData")
    public void removeLastVarOrClassModifierTest(String testName, String partialOracle, String expectedElementWithoutLastModifier) {
        EObject elementWithModifiers = parser.findLastElementWithModifiers(partialOracle);
        EObject elementWithoutLastModifier = removeLastVarOrClassModifier(elementWithModifiers);
        assertEquals(compactExpression(split(expectedElementWithoutLastModifier)), compactExpression(split(elementWithoutLastModifier)));
    }

    private static Stream<Arguments> removeLastVarOrClassModifierParameterizedTestData() {
        return Stream.of(
                Arguments.of("removeLastVarOrClassModifierTest1", "someArg.someField", "someArg"),
                Arguments.of("removeLastVarOrClassModifierTest2", "arg1.var2 && arg2.var1", "arg2"),
                Arguments.of("removeLastVarOrClassModifierTest3", "SomeClass.someField.someMethod(arg1, arg2, arg3", "SomeClass.someField"),
                Arguments.of("removeLastVarOrClassModifierTest4", "SomeClass.someField.someMethod(arg1, arg2, arg3.a", "arg3"),
                Arguments.of("removeLastVarOrClassModifierTest5", "SomeClass.someField.someMethod(arg1, arg2, arg3.someMethod(", "arg3"),
                Arguments.of("removeLastVarOrClassModifierTest6", "this.someField", "this"),
                Arguments.of("removeLastVarOrClassModifierTest7", "methodResultID.", "methodResultID")
        );
    }

    @Test
    public void removeLastVarOrClassModifierExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> removeLastVarOrClassModifier(parser.findLastElementWithModifiers("arg1 || Arrays.stream(arg2)")));
        assertThrows(IllegalArgumentException.class, () -> removeLastVarOrClassModifier(parser.findLastElementWithModifiers("")));
        assertThrows(IllegalArgumentException.class, () -> removeLastVarOrClassModifier(parser.findLastElementWithModifiers("this")));

    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("getLastJdVarArrayElementParameterizedTestData")
    public void getLastJdVarArrayElementTest(String testName, String jdVarExpression, String oracle, String expectedJdVarArrayElement) {
        String jdVarArrayElement = parser.getLastJdVarArrayElement(oracle, jdVarExpression);
        assertEquals(expectedJdVarArrayElement, jdVarArrayElement);
    }

    private static Stream<Arguments> getLastJdVarArrayElementParameterizedTestData() {
        return Stream.of(
                Arguments.of("getLastJdVarArrayElementTest1", "", "", null),
                Arguments.of("getLastJdVarArrayElementTest2", "jdVar", "this.someMethod();", null),
                Arguments.of("getLastJdVarArrayElementTest3", "jdVar", "Arrays.stream(someVar).anyMatch(jdVar -> jdVar.field==null);", "someVar[0]"),
                Arguments.of("getLastJdVarArrayElementTest4", "jdVar.field", "this.stream().allMatch(jdVar -> jdVar.field==null);", "this.get(0)"),
                Arguments.of("getLastJdVarArrayElementTest5", "jdVar==1", "methodResultID.stream().allMatch(jdVar -> jdVar == 1) && Arrays.stream(a).anyMatch(jdVar -> jdVar==null);", "methodResultID.get(0)"),
                Arguments.of("getLastJdVarArrayElementTest6", "jdVar!=null", "methodResultID.stream().allMatch(jdVar -> jdVar!=null) && this.stream().anyMatch(jdVar -> jdVar!=null);", "this.get(0)"),
                Arguments.of("getLastJdVarArrayElementTest7", "jdVar!=null && jdVar > 1", "Arrays.stream(a).allMatch(jdVar -> jdVar!=null && jdVar > 1) && Arrays.stream(b).anyMatch(jdVar -> jdVar!=null&&jdVar>1);", "b[0]"),
                Arguments.of("getLastJdVarArrayElementTest8", "jdVar.field.method()", "c.stream().allMatch(jdVar -> jdVar.field.method()", "c.get(0)"),
                Arguments.of("getLastJdVarArrayElementTest9", null, "", null),
                Arguments.of("getLastJdVarArrayElementTest10", null, "this.someMethod();", null),
                Arguments.of("getLastJdVarArrayElementTest11", null, "Arrays.stream(someVar).anyMatch(jdVar -> jdVar.field==null);", "someVar[0]"),
                Arguments.of("getLastJdVarArrayElementTest12", null, "this.stream().allMatch(jdVar -> jdVar.field==null);", "this.get(0)"),
                Arguments.of("getLastJdVarArrayElementTest13", null, "methodResultID.stream().allMatch(jdVar -> jdVar == 1) && Arrays.stream(a).anyMatch(jdVar -> jdVar==null);", "a[0]"),
                Arguments.of("getLastJdVarArrayElementTest14", null, "methodResultID.stream().allMatch(jdVar -> jdVar!=null) && this.stream().anyMatch(jdVar -> jdVar!=null);", "this.get(0)"),
                Arguments.of("getLastJdVarArrayElementTest15", null, "Arrays.stream(a).allMatch(jdVar -> jdVar!=null && jdVar > 1) && Arrays.stream(b).anyMatch(jdVar -> jdVar!=null&&jdVar>1);", "b[0]"),
                Arguments.of("getLastJdVarArrayElementTest16", null, "c.stream().allMatch(jdVar -> jdVar.field.method()", "c.get(0)")
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("getAllMethodsAndAttributesParameterizedTestData")
    public void getAllMethodsAndAttributesTest(String testName, String oracle, List<String> expectedMethodsAndAttributes) {
        List<EObject> methodsAndAttributes = parser.getAllMethodsAndAttributes(oracle);
        assertEquals(expectedMethodsAndAttributes.size(), methodsAndAttributes.size());
        assertTrue(
                expectedMethodsAndAttributes.stream().map(StringUtils::compactExpression).collect(Collectors.toList()).containsAll(
                        methodsAndAttributes.stream().map(eo -> compactExpression(split(eo))).collect(Collectors.toList()))
        );
    }

    private static Stream<Arguments> getAllMethodsAndAttributesParameterizedTestData() {
        return Stream.of(
                Arguments.of("getAllMethodsAndAttributesNoOracleTest", "", List.of()),
                Arguments.of("getAllMethodsAndAttributesArraysClassTest", "a.b.c() && Arrays.stream(methodResultID).anyMatch(jdVar -> jdVar.someField != null)", List.of(
                        "a.b",
                        "a.b.c()",
                        "jdVar.someField"
                )),
                Arguments.of("getAllMethodsAndAttributesArraysVarTest", "a.stream().noneMatch(jdVar -> jdVar == null)", List.of()),
                Arguments.of("getAllMethodsAndAttributesFieldsAndMethodsTest", "SomeClass.method(a.b, c, d.e(), AnotherClass.f, ThirdClass.g())", List.of(
                        "SomeClass.method(a.b, c, d.e(), AnotherClass.f, ThirdClass.g())",
                        "a.b",
                        "d.e()",
                        "AnotherClass.f",
                        "ThirdClass.g()"
                )),
                Arguments.of("getAllMethodsAndAttributesClassModifierTest", "a.b.c() && d.e(f, g, SomeClass.class, AnotherClass.h)", List.of(
                        "a.b",
                        "a.b.c()",
                        "d.e(f, g, SomeClass.class, AnotherClass.h)",
                        "AnotherClass.h"
                )),
                Arguments.of("getAllMethodsAndAttributesComplexOracleTest", "someVar.someAttr.anotherAttr.someMethod().anotherMethod(someArg.someArgAttr.someArgMethod(this.someThisVar));", List.of(
                        "someVar.someAttr",
                        "someVar.someAttr.anotherAttr",
                        "someVar.someAttr.anotherAttr.someMethod()",
                        "someVar.someAttr.anotherAttr.someMethod().anotherMethod(someArg.someArgAttr.someArgMethod(this.someThisVar))",
                        "someArg.someArgAttr",
                        "someArg.someArgAttr.someArgMethod(this.someThisVar)",
                        "this.someThisVar"
                ))
        );
    }
}
