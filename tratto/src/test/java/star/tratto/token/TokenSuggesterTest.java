package star.tratto.token;

import com.github.javaparser.resolution.UnsolvedSymbolException;
import org.javatuples.Triplet;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedStatic;
import star.tratto.dataset.oracles.OracleDatapoint;
import star.tratto.dataset.oracles.OracleDatapointTest;
import star.tratto.oracle.OracleType;
import star.tratto.oraclegrammar.custom.Parser;
import star.tratto.oraclegrammar.trattoGrammar.Oracle;
import star.tratto.token.restrictions.multi.*;
import star.tratto.token.restrictions.single.SingleTokenRestrictions;
import star.tratto.token.restrictions.single.StandardSingleTokenRestriction;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;
import static star.tratto.TestUtilities.readOracleDatapointsFromOraclesDataset;
import static star.tratto.TestUtilities.readOraclesFromExternalFiles;
import static star.tratto.dataset.oracles.OracleDatapointTest.getEmptyOracleDatapoint;
import static star.tratto.oraclegrammar.custom.Splitter.split;
import static star.tratto.token.TokenSuggester.*;

/**
 * This class contains tests not only for the TokenSuggester class, but also integration
 * tests involving all the implemented Context Restrictions (both Single and Multi).
 */
public class TokenSuggesterTest {

    private static final Parser parser = Parser.getInstance();
    private static final List<OracleDatapoint> oracleDatapoints = readOracleDatapointsFromOraclesDataset();


    @Test
    public void isTokenLegalBasedOnContextRestrictionsNonConflictiveTokenTest() {
        String token = "&&";
        String partialExpression = "arg1.someMethod()";
        List<String> partialExpressionTokens = split(parser.getPartialOracle(partialExpression));
        OracleDatapoint oracleDatapoint = getEmptyOracleDatapoint();
        oracleDatapoint.setOracleType(OracleType.PRE);

        // Preconditions
        assertTrue(getNextLegalTokensAccordingToGrammar(partialExpressionTokens).contains(token));
        assertFalse(SingleTokenRestrictions.RESTRICTED_TOKENS.get(OracleType.PRE).contains(token));

        // Test
        boolean legal = isTokenLegalBasedOnSingleTokenRestrictions(token, partialExpressionTokens, oracleDatapoint);
        assertTrue(legal);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("getNextLegalTokensAccordingToGrammarSpecialCasesParameterizedTestData")
    public void getNextLegalTokensAccordingToGrammarSpecialCasesTest(String testName, String partialExpression, List<String> someExpectedTokens, List<String> someNotExpectedTokens) {
        List<String> partialExpressionTokens = split(parser.getPartialOracle(partialExpression));
        List<String> nextLegalTokens = getNextLegalTokensAccordingToGrammar(partialExpressionTokens);
        assertTrue(nextLegalTokens.containsAll(someExpectedTokens));
        assertTrue(nextLegalTokens.stream().noneMatch(someNotExpectedTokens::contains));
    }

    private static Stream<Arguments> getNextLegalTokensAccordingToGrammarSpecialCasesParameterizedTestData() {
        return Stream.of(
                Arguments.of("conflictBitwiseOperatorThis", "arg1>=arg2>>this", List.of("."), Tokens.TOKENS.stream().filter(t -> !t.equals(".")).collect(Collectors.toList())),
                Arguments.of("conflictTwoBitwiseOperatorsThis", "arg1>=arg2|~this", List.of("."), Tokens.TOKENS.stream().filter(t -> !t.equals(".")).collect(Collectors.toList())),
                Arguments.of("conflictBitwiseOperator", "arg1>=arg2<<", List.of("this", "1", "methodResultID", "~"), List.of("|", "1.0", "true", "null")),
                Arguments.of("conflictTwoBitwiseOperators", "arg1>=arg2^~", List.of("this", "1", "methodResultID"), List.of("|", "1.0", "true", "null", "~"))
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("tokenLegalContextRestrictionsStandardParameterizedTestData")
    public void isTokenLegalBasedOnContextRestrictionsStandard_ORACLE_TYPE_CONTEXT_RESTRICTION_Case_Legality(String testName, String token, String partialExpression, OracleType oracleType, boolean expected) {
        List<String> partialExpressionTokens = split(parser.getPartialOracle(partialExpression));
        OracleDatapoint oracleDatapoint = getEmptyOracleDatapoint();
        oracleDatapoint.setOracleType(oracleType);

        // Preconditions
        assertTrue(getNextLegalTokensAccordingToGrammar(partialExpressionTokens).contains(token));
        assertTrue(SingleTokenRestrictions.RESTRICTED_TOKENS.get(oracleType).contains(token));

        // Test
        boolean legal = isTokenLegalBasedOnSingleTokenRestrictions(token, partialExpressionTokens, oracleDatapoint);
        assertEquals(expected, legal);
    }

    private static Stream<Arguments> tokenLegalContextRestrictionsStandardParameterizedTestData() {
        return Stream.of(
                Arguments.of("isTokenLegalBasedOnContextRestrictions_PRE_NO_QUESTIONMARK_Default_Illegal", "?", "arg1==null", OracleType.PRE, false),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_PRE_NO_TRUE_Default_Illegal", "true", "", OracleType.PRE, false),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_PRE_NO_TRUE_Disabled_Legal", "true", "arg1==", OracleType.PRE, true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_PRE_NO_METHODRESULTID_Default_Illegal", "methodResultID", "arg1==null && ", OracleType.PRE, false),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NORMAL_POST_YES_QUESTIONMARK_Default_Legal", ";", "", OracleType.NORMAL_POST, true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NORMAL_POST_YES_QUESTIONMARK_Enabled_Illegal", ";", "arg1", OracleType.NORMAL_POST, false),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NORMAL_POST_YES_QUESTIONMARK_Enabled_Longer_Illegal", ";", "arg1==null || arg2.equals(arg1)", OracleType.NORMAL_POST, false),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NORMAL_POST_YES_QUESTIONMARK_EnabledDisabled_Legal", ";", "arg1 ? methodResultID != null : arg2", OracleType.NORMAL_POST, true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NORMAL_POST_YES_QUESTIONMARK_EnabledDisabled_Longer_Legal", ";", "arg1 ? methodResultID != null : arg2==null", OracleType.NORMAL_POST, true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NORMAL_POST_NO_TRUE_Default_EmptyPartialExpression_Legal", "true", "", OracleType.NORMAL_POST, true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NORMAL_POST_NO_TRUE_Default_NonEmptyPartialExpression_Legal", "true", "methodResultID==", OracleType.NORMAL_POST, true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NORMAL_POST_NO_TRUE_Enabled_Illegal", "true", "methodResultID==null ? ", OracleType.NORMAL_POST, false),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NORMAL_POST_NO_TRUE_EnabledDisabled_Legal", "true", "methodResultID==null ? this.isValid()==", OracleType.NORMAL_POST, true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NORMAL_POST_NO_METHODRESULTID_Default_EmptyPartialExpression_Illegal", "methodResultID", "", OracleType.NORMAL_POST, false),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NORMAL_POST_NO_METHODRESULTID_Default_NonEmptyPartialExpression_Illegal", "methodResultID", "arg1.equals(", OracleType.NORMAL_POST, false),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NORMAL_POST_NO_METHODRESULTID_Disabled_Legal", "methodResultID", "arg1.equals(arg2) ? this.equals(", OracleType.NORMAL_POST, true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NORMAL_POST_YES_METHODRESULTID_AND_OR_THIS_Default_Illegal", ":", "arg1.field==null ? arg2 == false ", OracleType.NORMAL_POST, false),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NORMAL_POST_YES_METHODRESULTID_AND_OR_THIS_Disabled_this_Legal", ":", "arg1.field==null ? arg2==this", OracleType.NORMAL_POST, true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NORMAL_POST_YES_METHODRESULTID_AND_OR_THIS_Disabled_methodResultID_Legal", ":", "arg1.field==null ? methodResultID", OracleType.NORMAL_POST, true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NORMAL_POST_YES_METHODRESULTID_AND_OR_THIS_Disabled_this_2_Legal", ":", "arg1.field==null ? this==arg2", OracleType.NORMAL_POST, true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_EXCEPT_POST_NO_QUESTIONMARK_Default_Illegal", "?", "arg1==null", OracleType.EXCEPT_POST, false),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_EXCEPT_POST_NO_METHODRESULTID_Default_Illegal", "methodResultID", "arg1==null && ", OracleType.EXCEPT_POST, false)
        );
    }

    /**
     * Auxiliary method to change visibility of StandardSingleTokenRestriction constructor so that we can mock a fictitious instance.
     */
    private Constructor<StandardSingleTokenRestriction> getStandardSingleTokenRestrictionConstructor() throws NoSuchMethodException {
        Constructor<StandardSingleTokenRestriction> constructor = StandardSingleTokenRestriction.class.getDeclaredConstructor(OracleType.class, String.class, List.class, List.class, Boolean.class);
        constructor.setAccessible(true);
        return constructor;
    }

    /**
     * This test with mocks is necessary to check that, if adding new standard ContextRestrictions following the
     * conventions detailed in ContextRestriction, the method isTokenLegalBasedOnStandardContextRestriction
     * from {@link TokenSuggester} will work as expected. Also, it checks that a ContextRestriction can be disabled
     * just once.
     */
    @Test
    public void isTokenLegalBasedOnContextRestrictionsStandard_Fictitious_EnabledDisabledEnabled_Legal() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // Value to be returned by mock
        // ContextRestrictionType is irrelevant, but cannot be NO_THIS or NO_JDVAR, or YES_ARGUMENT_OR_THIS + PRE as OracleType
        StandardSingleTokenRestriction mockedStandardRestriction = getStandardSingleTokenRestrictionConstructor().newInstance(OracleType.PRE, ";", List.of(), List.of("this"), false);

        try (MockedStatic<SingleTokenRestrictions> mockedContextRestrictionContainer = mockStatic(SingleTokenRestrictions.class)) {
            // Regular test begins
            String token = ";";
            String partialExpression1Legal = "";
            List<String> partialExpression1LegalTokens = split(parser.getPartialOracle(partialExpression1Legal));
            String partialExpression2Illegal = "arg1";
            List<String> partialExpression2IllegalTokens = split(parser.getPartialOracle(partialExpression2Illegal));
            String partialExpression3Legal = "arg1==this";
            List<String> partialExpression3LegalTokens = split(parser.getPartialOracle(partialExpression3Legal));
            String partialExpression4Legal = "arg1==this.add(1)";
            List<String> partialExpression4LegalTokens = split(parser.getPartialOracle(partialExpression4Legal));
            String partialExpression5Legal = "arg1==this.add(1) && this.prop";
            List<String> partialExpression5LegalTokens = split(parser.getPartialOracle(partialExpression5Legal));
            OracleDatapoint oracleDatapoint = getEmptyOracleDatapoint();
            oracleDatapoint.setOracleType(OracleType.PRE);

            // Preconditions
            assertTrue(getNextLegalTokensAccordingToGrammar(partialExpression1LegalTokens).contains(token));
            assertTrue(getNextLegalTokensAccordingToGrammar(partialExpression2IllegalTokens).contains(token));
            assertTrue(getNextLegalTokensAccordingToGrammar(partialExpression3LegalTokens).contains(token));
            assertTrue(getNextLegalTokensAccordingToGrammar(partialExpression4LegalTokens).contains(token));
            assertTrue(getNextLegalTokensAccordingToGrammar(partialExpression5LegalTokens).contains(token));
            assertTrue(SingleTokenRestrictions.RESTRICTED_TOKENS.get(OracleType.PRE).contains(token));

            // Mocking
            mockedContextRestrictionContainer.when(() -> SingleTokenRestrictions.getSingleTokenRestriction(OracleType.PRE, ";")).thenReturn(mockedStandardRestriction);

            // Tests
            boolean legalExpression1 = isTokenLegalBasedOnSingleTokenRestrictions(token, partialExpression1LegalTokens, oracleDatapoint);
            assertTrue(legalExpression1);
            boolean legalExpression2 = isTokenLegalBasedOnSingleTokenRestrictions(token, partialExpression2IllegalTokens, oracleDatapoint);
            assertFalse(legalExpression2);
            boolean legalExpression3 = isTokenLegalBasedOnSingleTokenRestrictions(token, partialExpression3LegalTokens, oracleDatapoint);
            assertTrue(legalExpression3);
            boolean legalExpression4 = isTokenLegalBasedOnSingleTokenRestrictions(token, partialExpression4LegalTokens, oracleDatapoint);
            assertTrue(legalExpression4);
            boolean legalExpression5 = isTokenLegalBasedOnSingleTokenRestrictions(token, partialExpression5LegalTokens, oracleDatapoint);
            assertTrue(legalExpression5);
        }
    }

    /**
     * This test with mocks checks the case of a standard fictitious ContextRestriction which is disabled by default,
     * any token enables it, and a particular token disables it. The partial expression being checked has the first
     * token as the disabler token, and then such token is not repeated anywhere else in the expression. The fact that
     * it is the first token makes the ContextRestriction go through the following process: disabledByDefault ->
     * enabled by first token -> disabled also by first token. There was a bug causing the ContextRestriction to be
     * considered as enabled after this, and so the restricted token could not be used subsequently at any point in
     * the partial expression.
     */
    @Test
    public void isTokenLegalBasedOnContextRestrictionsStandard_Fictitious_DisabledByDefaultAnyTokenEnablesFirstTokenDisables() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // Value to be returned by mock
        StandardSingleTokenRestriction mockedStandardRestriction = getStandardSingleTokenRestrictionConstructor().newInstance(OracleType.NORMAL_POST, ":", List.of(), List.of("methodResultID", "this"), false);

        try (MockedStatic<SingleTokenRestrictions> mockedContextRestrictionContainer = mockStatic(SingleTokenRestrictions.class)) {
            // Regular test begins
            String token = ":";
            String partialExpression = "this.field==null ? arg10";
            List<String> partialExpressionTokens = split(parser.getPartialOracle(partialExpression));
            OracleDatapoint oracleDatapoint = getEmptyOracleDatapoint();
            oracleDatapoint.setOracleType(OracleType.NORMAL_POST);

            // Preconditions
            assertTrue(getNextLegalTokensAccordingToGrammar(partialExpressionTokens).contains(token));
            assertTrue(SingleTokenRestrictions.RESTRICTED_TOKENS.get(OracleType.NORMAL_POST).contains(token));

            // Mocking
            mockedContextRestrictionContainer.when(() -> SingleTokenRestrictions.getSingleTokenRestriction(OracleType.NORMAL_POST, ":")).thenReturn(mockedStandardRestriction);

            // Tests
            boolean legal = isTokenLegalBasedOnSingleTokenRestrictions(token, partialExpressionTokens, oracleDatapoint);
            assertTrue(legal);
        }
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("tokenLegalContextRestrictionsNoThisParameterizedTestData")
    public void isTokenLegalBasedOnContextRestrictions_ORACLE_TYPE_NO_THIS_Case_Legality(String testName, String partialExpression, OracleType oracleType, String methodSourceCode, boolean expected) {
        String token = "this";
        List<String> partialExpressionTokens = split(parser.getPartialOracle(partialExpression));
        OracleDatapoint oracleDatapoint = getEmptyOracleDatapoint();
        oracleDatapoint.setOracleType(oracleType);
        oracleDatapoint.setMethodSourceCode(methodSourceCode);

        // Preconditions
        assertTrue(getNextLegalTokensAccordingToGrammar(partialExpressionTokens).contains(token));
        assertTrue(SingleTokenRestrictions.RESTRICTED_TOKENS.get(oracleType).contains(token));

        // Test
        boolean legal = isTokenLegalBasedOnSingleTokenRestrictions(token, partialExpressionTokens, oracleDatapoint);
        assertEquals(expected, legal);
    }

    private static Stream<Arguments> tokenLegalContextRestrictionsNoThisParameterizedTestData() {
        return Stream.of(
                Arguments.of("isTokenLegalBasedOnContextRestrictions_PRE_NO_THIS_StaticMethod_Illegal", "", OracleType.PRE,
                        "public static int someStaticMethod() {\n" +
                        "    return 1;\n" +
                        "}\n",
                        false),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NORMAL_POST_NO_THIS_NonStaticMethod_Legal", "true ? ", OracleType.NORMAL_POST,
                        "String someNonStaticMethod();\n",
                        true)
        );
    }

    @Test
    public void isTokenLegalBasedOnContextRestrictions_EXCEPT_POST_NO_THIS_UnparseableMethodSourceCodeThrowException() {
        String token = "this";
        String partialExpression = "arg1 && ";
        List<String> partialExpressionTokens = split(parser.getPartialOracle(partialExpression));
        OracleDatapoint oracleDatapoint = getEmptyOracleDatapoint();
        oracleDatapoint.setOracleType(OracleType.EXCEPT_POST);
        oracleDatapoint.setMethodSourceCode(
                "unparseableMethodSourceCode"
        );

        // Preconditions
        assertTrue(getNextLegalTokensAccordingToGrammar(partialExpressionTokens).contains(token));
        assertTrue(SingleTokenRestrictions.RESTRICTED_TOKENS.get(OracleType.EXCEPT_POST).contains(token));

        // Test
        try {
            isTokenLegalBasedOnSingleTokenRestrictions(token, partialExpressionTokens, oracleDatapoint);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("The provided methodSourceCode cannot be parsed by JavaParser. Method source code:\n\n" + oracleDatapoint.getMethodSourceCode(), e.getMessage());
        }
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("tokenLegalContextRestrictionsNoJdVarParameterizedTestData")
    public void isTokenLegalBasedOnContextRestrictions_ORACLE_TYPE_NO_JDVAR_Case_Legality(String testName, String partialExpression, OracleType oracleType, boolean expected) {
        String token = "jdVar";
        List<String> partialExpressionTokens = split(parser.getPartialOracle(partialExpression));
        OracleDatapoint oracleDatapoint = getEmptyOracleDatapoint();
        oracleDatapoint.setOracleType(oracleType);

        // Preconditions
        assertTrue(getNextLegalTokensAccordingToGrammar(partialExpressionTokens).contains(token));
        assertTrue(SingleTokenRestrictions.RESTRICTED_TOKENS.get(oracleType).contains(token));

        // Test
        boolean legal = isTokenLegalBasedOnSingleTokenRestrictions(token, partialExpressionTokens, oracleDatapoint);
        assertEquals(expected, legal);
    }

    private static Stream<Arguments> tokenLegalContextRestrictionsNoJdVarParameterizedTestData() {
        return Stream.of(
                Arguments.of("isTokenLegalBasedOnContextRestrictions_PRE_NO_JDVAR_Default_Illegal", "", OracleType.PRE, false),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NORMAL_POST_NO_JDVAR_Disabled_Legal", "someArg.stream().anyMatch(", OracleType.NORMAL_POST, true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_EXCEPT_POST_NO_JDVAR_Disabled2_Legal", "someArg.stream().anyMatch(jdVar ->", OracleType.EXCEPT_POST, true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_PRE_NO_JDVAR_DisabledEnabled_Illegal", "someArg.stream().anyMatch(jdVar -> jdVar) &&", OracleType.PRE, false),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_PRE_NO_JDVAR_Disabled3_Legal", "someArg.stream().anyMatch(jdVar -> arg1.equals(", OracleType.PRE, true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_PRE_NO_JDVAR_Disabled4_Legal", "someArg.stream().anyMatch(jdVar -> SomeClass.staticMethod(jdVar).anotherMethod(", OracleType.PRE, true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_PRE_NO_JDVAR_DisabledEnabledDisabled_Legal", "Arrays.stream(someArg).anyMatch(jdVar -> A.b(jdVar)) || c.stream().noneMatch(", OracleType.PRE, true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_PRE_NO_JDVAR_DisabledEnabledDisabled2_Legal", "Arrays.stream(someArg).anyMatch(jdVar -> A.b(jdVar)) || c.stream().noneMatch(jdVar -> arg3>", OracleType.PRE, true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_PRE_NO_JDVAR_DisabledEnabledDisabled3_Legal", "Arrays.stream(someArg).anyMatch(jdVar -> A.b(jdVar)) || c.stream().noneMatch(jdVar -> D.e(", OracleType.PRE, true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_PRE_NO_JDVAR_DisabledEnabledDisabledEnabled_Illegal", "Arrays.stream(someArg).anyMatch(jdVar -> A.b(jdVar)) || c.stream().noneMatch(jdVar -> D.e()) &&", OracleType.PRE, false)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("tokenLegalContextRestrictionsPreYesArgumentOrThisParameterizedTestData")
    public void isTokenLegalBasedOnContextRestrictions_PRE_YES_ARGUMENT_OR_THIS_Case_Legality(String testName, String partialExpression, String javadocTag, boolean expected) {
        String token = ";";
        List<String> partialExpressionTokens = split(parser.getPartialOracle(partialExpression));
        OracleDatapoint oracleDatapoint = getEmptyOracleDatapoint();
        oracleDatapoint.setOracleType(OracleType.PRE);
        oracleDatapoint.setJavadocTag(javadocTag);

        // Preconditions
        assertTrue(getNextLegalTokensAccordingToGrammar(partialExpressionTokens).contains(token));
        assertTrue(SingleTokenRestrictions.RESTRICTED_TOKENS.get(OracleType.PRE).contains(token));

        // Test
        boolean legal = isTokenLegalBasedOnSingleTokenRestrictions(token, partialExpressionTokens, oracleDatapoint);
        assertEquals(expected, legal);
    }

    private static Stream<Arguments> tokenLegalContextRestrictionsPreYesArgumentOrThisParameterizedTestData() {
        return Stream.of(
                Arguments.of("isTokenLegalBasedOnContextRestrictions_PRE_YES_ARGUMENT_OR_THIS_Default_Legal", "  ", "@param arg1", true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_PRE_YES_ARGUMENT_OR_THIS_Enabled_Illegal", " arg2 ", "   @param  arg1 This is the parameter", false),
//                Arguments.of("isTokenLegalBasedOnContextRestrictions_PRE_YES_ARGUMENT_OR_THIS_Disabled_this_Legal", "this", "@param arg1 This is the parameter", true), // "this" can never evaluate to boolean
                Arguments.of("isTokenLegalBasedOnContextRestrictions_PRE_YES_ARGUMENT_OR_THIS_Disabled_argument_Legal", "arg1", "@param arg1 This is the parameter", true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_PRE_YES_ARGUMENT_OR_THIS_Disabled_argument_this_Legal", "arg1.process(this)", "@param arg1 This is the parameter", true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_PRE_YES_ARGUMENT_OR_THIS_Disabled_this_argument_Legal", "this.booleanProp && arg1", "@param arg1 This is the parameter", true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_PRE_YES_ARGUMENT_OR_THIS_Disabled_argument_somethingElse_Legal", "arg1!=null", "@param arg1 This is the parameter", true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_PRE_YES_ARGUMENT_OR_THIS_Disabled_this_somethingElse_Legal", "this.equals(arg2)", "@param arg1 This is the parameter", true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_PRE_YES_ARGUMENT_OR_THIS_Disabled_argument_somethingElseLonger_Legal", "arg1!=null && arg2", "@param arg1 This is the parameter", true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_PRE_YES_ARGUMENT_OR_THIS_Disabled_this_somethingElseLonger_Legal", "this.equals(arg3) || arg2", "@param arg1 This is the parameter", true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_PRE_YES_ARGUMENT_OR_THIS_EnabledDisabled_this_Legal", "arg2&&this.prop", "@param otherParamName @param annotation ", true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_PRE_YES_ARGUMENT_OR_THIS_EnabledDisabled_argument_Legal", "arg2&&arg1==null", "@param arg1 Javadoc tag.", true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_PRE_YES_ARGUMENT_OR_THIS_EnabledDisabled_argument_this_Legal", "arg2&&arg1==null||this.prop", "@param arg1 Javadoc tag.", true)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("tokenLegalContextRestrictionsNoPeriodParameterizedTestData")
    public void isTokenLegalBasedOnContextRestrictions_NO_PERIOD_Case_Legality(String testName, String partialExpression, OracleDatapoint oracleDatapoint, boolean expected) {
        String token = ".";
        List<String> partialExpressionTokens = split(parser.getPartialOracle(partialExpression));

        // Preconditions
        assertTrue(getNextLegalTokensAccordingToGrammar(partialExpressionTokens).contains(token));
        assertTrue(SingleTokenRestrictions.RESTRICTED_TOKENS.get(oracleDatapoint.getOracleType()).contains(token));

        // Test
        boolean legal = isTokenLegalBasedOnSingleTokenRestrictions(token, partialExpressionTokens, oracleDatapoint);
        assertEquals(expected, legal);
    }

    private static Stream<Arguments> tokenLegalContextRestrictionsNoPeriodParameterizedTestData() {
        return Stream.of(
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_PERIOD_Argument_Legal", "divisor", oracleDatapoints.get(3), true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_PERIOD_SomeContentAndArgument_Legal", "methodResultID == null && divisor", oracleDatapoints.get(3), true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_PERIOD_EvaluatesToPrimitive_Illegal", "divisor.isInfinite() && (this.isInfinite())==false ? (methodResultID.isNaN", oracleDatapoints.get(3), false),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_PERIOD_EvaluatesToComplexJdVar_Legal", "true ? methodResultID.stream().noneMatch(jdVar -> jdVar", oracleDatapoints.get(4), true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_PERIOD_EvaluatesToPrimitiveJdVar_Illegal", "true ? methodResultID.stream().noneMatch(jdVar -> jdVar.getArgument()", oracleDatapoints.get(4), false),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_PERIOD_EvaluatesToObject_Legal", "divisor.isInfinite() && (this.isInfinite())==false ? (methodResultID.isNaN())==false && methodResultID == Complex.ZERO", oracleDatapoints.get(3), true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_PERIOD_Arrays_Legal", "someVar && Arrays", oracleDatapoints.get(3), true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_PERIOD_ArraysStream_Legal", "someVar && Arrays.stream(anotherVar)", oracleDatapoints.get(3), true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_PERIOD_Stream_Legal", "someVar && anotherVar.stream()", oracleDatapoints.get(3), true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_PERIOD_Class_Legal", "someVar && PolynomialSplineFunction", oracleDatapoints.get(3), true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_PERIOD_ArrayOfPrimitives_Legal", "this.getCoefficients()", oracleDatapoints.get(1), true)

        );
    }

    /**
     * These invocations of this ContextRestriction should throw an exception since the last token is a method name.
     */
    @Test
    public void isTokenLegalBasedOnContextRestrictions_NO_PERIOD_Exception() {
        String token = ".";
        List<List<String>> partialExpressionTokensList = List.of(
                split(parser.getPartialOracle("divisor.equals")),
                split(parser.getPartialOracle("this.negate"))
        );
        OracleDatapoint oracleDatapoint = oracleDatapoints.get(3);
        for (List<String> partialExpressionTokens : partialExpressionTokensList) {
            // Preconditions
            assertTrue(getNextLegalTokensAccordingToGrammar(partialExpressionTokens).contains(token));
            assertTrue(SingleTokenRestrictions.RESTRICTED_TOKENS.get(oracleDatapoint.getOracleType()).contains(token));

            // Test
            assertThrows(UnsolvedSymbolException.class, () -> isTokenLegalBasedOnSingleTokenRestrictions(token, partialExpressionTokens, oracleDatapoint));
        }
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("tokenLegalContextRestrictionsNoInstanceofParameterizedTestData")
    public void isTokenLegalBasedOnContextRestrictions_NO_INSTANCEOF_Case_Legality(String testName, String partialExpression, boolean expected) {
        String token = "instanceof";
        List<String> partialExpressionTokens = split(parser.getPartialOracle(partialExpression));
        OracleDatapoint oracleDatapoint = oracleDatapoints.get(3);

        // Preconditions
        assertTrue(getNextLegalTokensAccordingToGrammar(partialExpressionTokens).contains(token));
        assertTrue(SingleTokenRestrictions.RESTRICTED_TOKENS.get(oracleDatapoint.getOracleType()).contains(token));

        // Test
        boolean legal = isTokenLegalBasedOnSingleTokenRestrictions(token, partialExpressionTokens, oracleDatapoint);
        assertEquals(expected, legal);
    }

    private static Stream<Arguments> tokenLegalContextRestrictionsNoInstanceofParameterizedTestData() {
        return Stream.of(
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_INSTANCEOF_Class_Illegal", "Complex", false),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_INSTANCEOF_this_Legal", "this", true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_INSTANCEOF_ClassReturnType_Legal", "divisor", true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_INSTANCEOF_SomeContentClassReturnType_Legal", "methodResultID == null && divisor", true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_INSTANCEOF_PrimitiveReturnTypeMethod_Legal", "this.isInfinite()", false),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_INSTANCEOF_PrimitiveReturnTypeAttribute_Illegal", "divisor.isInfinite() && (this.isInfinite())==false ? (methodResultID.isNaN", false),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_INSTANCEOF_SomeContentClassReturnTypeClassField_Legal", "divisor.isInfinite() && (this.isInfinite())==false ? (methodResultID.isNaN())==false && Complex.ZERO", true)
        );
    }

    /**
     * These invocations of this ContextRestriction should throw an exception since the last token is a method name.
     */
    @Test
    public void isTokenLegalBasedOnContextRestrictions_NO_INSTANCEOF_Exception() {
        String token = "instanceof";
        List<List<String>> partialExpressionTokensList = List.of(
                split(parser.getPartialOracle("divisor.equals")),
                split(parser.getPartialOracle("this.negate"))
        );
        OracleDatapoint oracleDatapoint = oracleDatapoints.get(3);
        for (List<String> partialExpressionTokens : partialExpressionTokensList) {
            // Preconditions
            assertTrue(getNextLegalTokensAccordingToGrammar(partialExpressionTokens).contains(token));
            assertTrue(SingleTokenRestrictions.RESTRICTED_TOKENS.get(oracleDatapoint.getOracleType()).contains(token));

            // Test
            assertThrows(UnsolvedSymbolException.class, () -> isTokenLegalBasedOnSingleTokenRestrictions(token, partialExpressionTokens, oracleDatapoint));
        }
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("tokenLegalContextRestrictionsNoBitwiseNegateOperatorParameterizedTestData")
    public void isTokenLegalBasedOnContextRestrictions_NO_BITWISE_NEGATE_OPERATOR_Case_Legality(String testName, String partialExpression, OracleDatapoint oracleDatapoint, boolean expected) {
        String token = "~";
        List<String> partialExpressionTokens = split(parser.getPartialOracle(partialExpression));

        // Preconditions
        assertTrue(getNextLegalTokensAccordingToGrammar(partialExpressionTokens).contains(token));
        assertTrue(SingleTokenRestrictions.RESTRICTED_TOKENS.get(oracleDatapoint.getOracleType()).contains(token));

        // Test
        boolean legal = isTokenLegalBasedOnSingleTokenRestrictions(token, partialExpressionTokens, oracleDatapoint);
        assertEquals(expected, legal);
    }

    private static Stream<Arguments> tokenLegalContextRestrictionsNoBitwiseNegateOperatorParameterizedTestData() {
        return Stream.of(
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_BITWISE_NEGATE_OPERATOR_ObjectNonIntegral_Illegal", "((o1==", oracleDatapoints.get(0), false),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_BITWISE_NEGATE_OPERATOR_BooleanNonIntegral_Illegal", "methodResultID==", oracleDatapoints.get(0), false),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_BITWISE_NEGATE_OPERATOR_doubleArrayReturnType_Illegal", "this.getCoefficients()==", oracleDatapoints.get(1), false),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_BITWISE_NEGATE_OPERATOR_intReturnType_Legal", "this.getCoefficients().length>=", oracleDatapoints.get(1), true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_BITWISE_NEGATE_OPERATOR_PreviousBitOperator1_Legal", "this.getCoefficients().length==invented^", oracleDatapoints.get(1), true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_BITWISE_NEGATE_OPERATOR_PreviousBitOperator2_Legal", "this.getCoefficients().length==~invented^", oracleDatapoints.get(1), true)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("tokenLegalContextRestrictionsNoOpeningParenthesisParameterizedTestData")
    public void isTokenLegalBasedOnContextRestrictions_NO_OPENING_PARENTHESIS_Case_Legality(String testName, String partialExpression, OracleDatapoint oracleDatapoint, boolean expected) {
        String token = "(";
        List<String> partialExpressionTokens = split(parser.getPartialOracle(partialExpression));

        // Preconditions
        assertTrue(getNextLegalTokensAccordingToGrammar(partialExpressionTokens).contains(token));
        assertTrue(SingleTokenRestrictions.RESTRICTED_TOKENS.get(oracleDatapoint.getOracleType()).contains(token));

        // Test
        boolean legal = isTokenLegalBasedOnSingleTokenRestrictions(token, partialExpressionTokens, oracleDatapoint);
        assertEquals(expected, legal);
    }

    private static Stream<Arguments> tokenLegalContextRestrictionsNoOpeningParenthesisParameterizedTestData() {
        return Stream.of(
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_OPENING_PARENTHESIS_EmptyOracle_Legal", "", oracleDatapoints.get(0), true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_OPENING_PARENTHESIS_StartingPredicate_Legal", "o1.equals(o2) || ", oracleDatapoints.get(0), true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_OPENING_PARENTHESIS_MethodName_Legal", "this.getCoefficients", oracleDatapoints.get(1), true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_OPENING_PARENTHESIS_AttributeNameFromArray_Illegal", "this.getCoefficients().length", oracleDatapoints.get(1), false),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_OPENING_PARENTHESIS_AttributeNameFromObject_Illegal", "methodResultID==Complex.ZERO", oracleDatapoints.get(3), false),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_OPENING_PARENTHESIS_BothMethodAndAttributeName_Legal", "divisor.isInfinite() && (this.isInfinite())==false ? (methodResultID.isNaN", oracleDatapoints.get(3), true)

                );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("tokenLegalContextRestrictionsNoCommaParameterizedTestData")
    public void isTokenLegalBasedOnContextRestrictions_NO_COMMA_Case_Legality(String testName, String partialExpression, OracleDatapoint oracleDatapoint, boolean expected) {
        String token = ",";
        List<String> partialExpressionTokens = split(parser.getPartialOracle(partialExpression));

        // Preconditions
        assertTrue(getNextLegalTokensAccordingToGrammar(partialExpressionTokens).contains(token));
        assertTrue(SingleTokenRestrictions.RESTRICTED_TOKENS.get(oracleDatapoint.getOracleType()).contains(token));

        // Test
        boolean legal = isTokenLegalBasedOnSingleTokenRestrictions(token, partialExpressionTokens, oracleDatapoint);
        assertEquals(expected, legal);
    }

    private static Stream<Arguments> tokenLegalContextRestrictionsNoCommaParameterizedTestData() {
        return Stream.of(
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_COMMA_NotEnoughArguments_Illegal", "o1.equals(o2", oracleDatapoints.get(0), false),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_COMMA_NotEnoughArguments2_Illegal", "methodResultID.atan2(this", oracleDatapoints.get(1), false),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_COMMA_InvalidType_Illegal", "DerivativeStructure.atan2(this", oracleDatapoints.get(1), false),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_COMMA_ValidType_Legal", "DerivativeStructure.atan2(methodResultID", oracleDatapoints.get(1), true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_COMMA_Null_Legal", "DerivativeStructure.atan2(null", oracleDatapoints.get(1), true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_COMMA_ValidType2_Legal", "methodResultID.linearCombination(null", oracleDatapoints.get(1), true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_COMMA_InvalidType2_Illegal", "DerivativeStructure.pow(this", oracleDatapoints.get(1), false),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_COMMA_ValidType3_Legal", "DerivativeStructure.pow(1", oracleDatapoints.get(1), true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_COMMA_ValidType4_Legal", "DerivativeStructure.pow(1.1", oracleDatapoints.get(1), true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_COMMA_InvalidType3_Illegal", "DerivativeStructure.pow(this.getCoefficients()", oracleDatapoints.get(1), false),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_COMMA_ValidType6_Legal", "DerivativeStructure.pow(this.getCoefficients().length", oracleDatapoints.get(1), true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_COMMA_InvalidType4_Illegal", "PolynomialFunction.evaluate(this", oracleDatapoints.get(1), false),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_COMMA_ValidType7_Legal", "PolynomialFunction.evaluate(this.getCoefficients()", oracleDatapoints.get(1), true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_COMMA_ValidType8_Legal", "methodResultID.createComplex(1", oracleDatapoints.get(3), true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_COMMA_ValidType9_Legal", "methodResultID.createComplex(1.1", oracleDatapoints.get(3), true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_COMMA_ValidType10_Legal", "methodResultID.createComplex(this.getArgument()", oracleDatapoints.get(3), true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_COMMA_InvalidType5_Illegal", "methodResultID.createComplex(this.getArgument(), 5.1", oracleDatapoints.get(3), false),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_COMMA_ValidType11_Legal", "this.equate(BagUtils.EMPTY_BAG", oracleDatapoints.get(0), true)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("tokenLegalContextRestrictionsNoMethodResultIDParameterizedTestData")
    public void isTokenLegalBasedOnContextRestrictions_NO_METHOD_RESULT_ID_Case_Legality(String testName, String partialExpression, OracleDatapoint oracleDatapoint, boolean expected) {
        String token = "methodResultID";
        List<String> partialExpressionTokens = split(parser.getPartialOracle(partialExpression));

        // Preconditions
        assertTrue(getNextLegalTokensAccordingToGrammar(partialExpressionTokens).contains(token));
        assertTrue(SingleTokenRestrictions.RESTRICTED_TOKENS.get(oracleDatapoint.getOracleType()).contains(token));

        // Test
        boolean legal = isTokenLegalBasedOnSingleTokenRestrictions(token, partialExpressionTokens, oracleDatapoint);
        assertEquals(expected, legal);
    }

    private static Stream<Arguments> tokenLegalContextRestrictionsNoMethodResultIDParameterizedTestData() {
        OracleDatapoint mockedOracleDatapoint = OracleDatapointTest.getEmptyOracleDatapoint();
        mockedOracleDatapoint.setClassName("SomeClass");
        mockedOracleDatapoint.setMethodSourceCode("public void someMethod() { return; }");
        mockedOracleDatapoint.setClassSourceCode("public class SomeClass { " + mockedOracleDatapoint.getMethodSourceCode() + " }");
        mockedOracleDatapoint.setTokensMethodArguments(List.of());
        mockedOracleDatapoint.setOracleType(OracleType.NORMAL_POST);
        mockedOracleDatapoint.setTokensProjectClasses(List.of());
        return Stream.of(
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_METHOD_RESULT_ID_ArraysNotApplicable_Illegal", "true ? Arrays.stream(", oracleDatapoints.get(4), false),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_METHOD_RESULT_ID_Empty_Illegal", "", oracleDatapoints.get(4), false),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_METHOD_RESULT_ID_ArraysApplicable_Legal", "true ? Arrays.stream(", oracleDatapoints.get(5), true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_METHOD_RESULT_ID_PostApplicable_Legal", "true ? ", oracleDatapoints.get(5), true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_METHOD_RESULT_ID_Empty_Illegal", "", oracleDatapoints.get(5), false),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_METHOD_RESULT_ID_Guard_Illegal", "Arrays.stream(", oracleDatapoints.get(5), false),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_METHOD_RESULT_ID_VoidMethod_Illegal", "true ? ", mockedOracleDatapoint, false)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("tokenLegalContextRestrictionsNoArraysParameterizedTestData")
    public void isTokenLegalBasedOnContextRestrictions_NO_ARRAYS_Case_Legality(String testName, String partialExpression, OracleDatapoint oracleDatapoint, boolean expected) {
        String token = "Arrays";
        List<String> partialExpressionTokens = split(parser.getPartialOracle(partialExpression));

        // Preconditions
        assertTrue(getNextLegalTokensAccordingToGrammar(partialExpressionTokens).contains(token));
        assertTrue(SingleTokenRestrictions.RESTRICTED_TOKENS.get(oracleDatapoint.getOracleType()).contains(token));

        // Test
        boolean legal = isTokenLegalBasedOnSingleTokenRestrictions(token, partialExpressionTokens, oracleDatapoint);
        assertEquals(expected, legal);
    }

    private static Stream<Arguments> tokenLegalContextRestrictionsNoArraysParameterizedTestData() {
        // Return type: array
        OracleDatapoint mockedOracleDatapoint1 = OracleDatapointTest.getEmptyOracleDatapoint();
        mockedOracleDatapoint1.setClassName("SomeClass");
        mockedOracleDatapoint1.setMethodSourceCode("public int[] someMethod(ArrayList<Integer> arg1, Integer arg2) { return null; }");
        mockedOracleDatapoint1.setClassSourceCode("import java.util.ArrayList; public class SomeClass { " + mockedOracleDatapoint1.getMethodSourceCode() + " }");
        mockedOracleDatapoint1.setTokensMethodArguments(List.of(Triplet.with("arg1", "java.util", "ArrayList"), Triplet.with("arg2", "java.lang", "Integer")));
        mockedOracleDatapoint1.setOracleType(OracleType.NORMAL_POST);
        mockedOracleDatapoint1.setTokensProjectClasses(List.of());

        // Return type: void
        OracleDatapoint mockedOracleDatapoint2 = OracleDatapointTest.getEmptyOracleDatapoint();
        mockedOracleDatapoint2.setClassName("SomeClass");
        mockedOracleDatapoint2.setMethodSourceCode("public void someMethod(ArrayList<Integer> arg1, Integer arg2) { return; }");
        mockedOracleDatapoint2.setClassSourceCode("import java.util.ArrayList; public class SomeClass { " + mockedOracleDatapoint2.getMethodSourceCode() + " }");
        mockedOracleDatapoint2.setTokensMethodArguments(List.of(Triplet.with("arg1", "java.util", "ArrayList"), Triplet.with("arg2", "java.lang", "Integer")));
        mockedOracleDatapoint2.setOracleType(OracleType.NORMAL_POST);
        mockedOracleDatapoint2.setTokensProjectClasses(List.of());

        return Stream.of(
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_ARRAYS_Empty_Illegal", "", oracleDatapoints.get(0), false),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_ARRAYS_ArraysMethodResultID_Legal", "", mockedOracleDatapoint1, true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_ARRAYS_ArraysMethodResultID_Legal", "", mockedOracleDatapoint2, false)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("tokenLegalContextRestrictionsNoStreamParameterizedTestData")
    public void isTokenLegalBasedOnContextRestrictions_NO_STREAM_Case_Legality(String testName, String partialExpression, OracleDatapoint oracleDatapoint, boolean expected) {
        String token = "stream";
        List<String> partialExpressionTokens = split(parser.getPartialOracle(partialExpression));

        // Preconditions
        assertTrue(getNextLegalTokensAccordingToGrammar(partialExpressionTokens).contains(token));
        assertTrue(SingleTokenRestrictions.RESTRICTED_TOKENS.get(oracleDatapoint.getOracleType()).contains(token));

        // Test
        boolean legal = isTokenLegalBasedOnSingleTokenRestrictions(token, partialExpressionTokens, oracleDatapoint);
        assertEquals(expected, legal);
    }

    private static Stream<Arguments> tokenLegalContextRestrictionsNoStreamParameterizedTestData() {
        return Stream.of(
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_STREAM_ObjectType_Illegal", "o1.", oracleDatapoints.get(0), false),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_STREAM_CollectionType_Legal", "true ? methodResultID.", oracleDatapoints.get(4), true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_STREAM_NonCollectionTypeButSomeIsCollection_Illegal", "this.", oracleDatapoints.get(4), false)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("tokenLegalContextRestrictionsNoClassModifierParameterizedTestData")
    public void isTokenLegalBasedOnContextRestrictions_NO_CLASS_MODIFIER_Case_Legality(String testName, String partialExpression, OracleDatapoint oracleDatapoint, boolean expected) {
        String token = "class";
        List<String> partialExpressionTokens = split(parser.getPartialOracle(partialExpression));

        // Preconditions
        assertTrue(getNextLegalTokensAccordingToGrammar(partialExpressionTokens).contains(token));
        assertTrue(SingleTokenRestrictions.RESTRICTED_TOKENS.get(oracleDatapoint.getOracleType()).contains(token));

        // Test
        boolean legal = isTokenLegalBasedOnSingleTokenRestrictions(token, partialExpressionTokens, oracleDatapoint);
        assertEquals(expected, legal);
    }

    private static Stream<Arguments> tokenLegalContextRestrictionsNoClassModifierParameterizedTestData() {
        return Stream.of(
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_CLASS_MODIFIER_Variable_Illegal", "this.equals(divisor.", oracleDatapoints.get(3), false),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_CLASS_MODIFIER_Class_Legal", "divisor.equals(Complex.", oracleDatapoints.get(3), true)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("tokenLegalContextRestrictionsNoClosingParenthesisParameterizedTestData")
    public void isTokenLegalBasedOnContextRestrictions_NO_CLOSING_PARENTHESIS_Case_Legality(String testName, String partialExpression, OracleDatapoint oracleDatapoint, boolean expected) {
        String token = ")";
        List<String> partialExpressionTokens = split(parser.getPartialOracle(partialExpression));

        // Preconditions
        assertTrue(getNextLegalTokensAccordingToGrammar(partialExpressionTokens).contains(token));
        assertTrue(SingleTokenRestrictions.RESTRICTED_TOKENS.get(oracleDatapoint.getOracleType()).contains(token));

        // Test
        boolean legal = isTokenLegalBasedOnSingleTokenRestrictions(token, partialExpressionTokens, oracleDatapoint);
        assertEquals(expected, legal);
    }

    private static Stream<Arguments> tokenLegalContextRestrictionsNoClosingParenthesisParameterizedTestData() {
        return Stream.of(
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_CLOSING_PARENTHESIS_NotApplicable_Legal", "(o1==null", oracleDatapoints.get(0), true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_CLOSING_PARENTHESIS_NoArgsSoFarNoMethodWithoutArgs_Illegal", "o1.equals(", oracleDatapoints.get(0), false),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_CLOSING_PARENTHESIS_Equals_Legal", "o1.equals(o2", oracleDatapoints.get(0), true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_CLOSING_PARENTHESIS_ValidType_Legal", "methodResultID.atan2(this", oracleDatapoints.get(1), true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_CLOSING_PARENTHESIS_InvalidType_Illegal", "methodResultID.atan2(this.degree()", oracleDatapoints.get(1), false),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_CLOSING_PARENTHESIS_InvalidType2_Illegal", "DerivativeStructure.atan2(this.value(methodResultID), this", oracleDatapoints.get(1), false),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_CLOSING_PARENTHESIS_ValidType2_Legal", "DerivativeStructure.atan2(methodResultID, methodResultID", oracleDatapoints.get(1), true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_CLOSING_PARENTHESIS_Null_Legal", "DerivativeStructure.atan2(null, null", oracleDatapoints.get(1), true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_CLOSING_PARENTHESIS_ValidType3_Legal", "methodResultID.linearCombination(null, null", oracleDatapoints.get(1), true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_CLOSING_PARENTHESIS_InvalidType3_Illegal", "DerivativeStructure.pow(this", oracleDatapoints.get(1), false),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_CLOSING_PARENTHESIS_ValidType4_Legal", "methodResultID.linearCombination(this.value(methodResultID", oracleDatapoints.get(1), true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_CLOSING_PARENTHESIS_ValidType5_Legal", "methodResultID.pow(1", oracleDatapoints.get(1), true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_CLOSING_PARENTHESIS_ValidType6_Legal", "methodResultID.pow(1.1", oracleDatapoints.get(1), true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_CLOSING_PARENTHESIS_InvalidType4_Illegal", "methodResultID.pow(this.getCoefficients()", oracleDatapoints.get(1), true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_CLOSING_PARENTHESIS_ValidType7_Legal", "methodResultID.pow(this.getCoefficients().length", oracleDatapoints.get(1), true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_CLOSING_PARENTHESIS_InvalidType5_Illegal", "PolynomialFunction.evaluate(this.getCoefficients(), this.getCoefficients()", oracleDatapoints.get(1), false),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_CLOSING_PARENTHESIS_ValidType8_Legal", "PolynomialFunction.evaluate(this.getCoefficients(), this.getCoefficients().length", oracleDatapoints.get(1), true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_CLOSING_PARENTHESIS_ValidType9_Legal", "methodResultID.createComplex(1, 1", oracleDatapoints.get(3), true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_CLOSING_PARENTHESIS_ValidType10_Legal", "methodResultID.createComplex(1, 1.1", oracleDatapoints.get(3), true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_CLOSING_PARENTHESIS_ValidType11_Legal", "methodResultID.createComplex(1, this.getArgument()", oracleDatapoints.get(3), true),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_CLOSING_PARENTHESIS_InvalidType6_Illegal", "methodResultID.createComplex(this.getArgument()", oracleDatapoints.get(3), false),
                Arguments.of("isTokenLegalBasedOnContextRestrictions_NO_CLOSING_PARENTHESIS_ValidType12_Legal", "this.equate(BagUtils.EMPTY_BAG, BagUtils.EMPTY_BAG", oracleDatapoints.get(0), true)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("restrictionEnabledLastMethodNameParameterizedTestData")
    public void isRestrictionEnabled_LAST_METHOD_NAME_enabledCase(String testName, String partialExpression, OracleDatapoint oracleDatapoint, boolean expected) {
        LastMethodNameRestriction restriction = LastMethodNameRestrictionTest.getLastMethodNameRestriction();
        List<String> partialExpressionTokens = split(parser.getPartialOracle(partialExpression));
        List<String> nextLegalTokens = getNextLegalTokensAccordingToGrammar(partialExpressionTokens);

        // Preconditions
        assertTrue(nextLegalTokens.stream().anyMatch(token -> restriction.getRestrictedTokens().contains(token)));

        boolean enabled = restriction.isEnabled(nextLegalTokens, split(parser.getPartialOracle(partialExpression)), oracleDatapoint);
        assertEquals(expected, enabled);
    }

    private static Stream<Arguments> restrictionEnabledLastMethodNameParameterizedTestData() {
        return Stream.of(
                Arguments.of("disabledEmpty", "", oracleDatapoints.get(0), false),
                Arguments.of("disabledClassWithClassModifier", "o1.equals(CollectionBag.class", oracleDatapoints.get(0), false),
                Arguments.of("disabledForbiddenByGrammar", "Equator", oracleDatapoints.get(0), false),
                Arguments.of("disabledLessThan3Tokens", "o1.", oracleDatapoints.get(0), false),
                Arguments.of("disabledLastTokenNotMethodName", "o1==null ? methodResultID == null &&", oracleDatapoints.get(0), false),
                Arguments.of("disabledSecondToLastTokenNotPeriod", "o1==null ? methodResultID == null && o2", oracleDatapoints.get(0), false),
                Arguments.of("enabledThirdToLastTokenVar", "o1.equals", oracleDatapoints.get(0), true),
                Arguments.of("enabledThirdToLastTokenThis", "this.getCoefficients", oracleDatapoints.get(1), true),
                Arguments.of("enabledEqualsMethodNonObject", "this instanceof PolynomialFunction && (t.equals", oracleDatapoints.get(2), true),
                Arguments.of("enabledComplexFirst", "divisor.isInfinite", oracleDatapoints.get(3), true),
                Arguments.of("enabledComplexSecond", "divisor.isInfinite && (this.isInfinite", oracleDatapoints.get(3), true),
                Arguments.of("disabledComplexThirdSameNameMethodAttribute", "divisor.isInfinite() && (this.isInfinite())==false ? (methodResultID.isNaN", oracleDatapoints.get(3), false),
                Arguments.of("disabledComplexFourth", "divisor.isInfinite() && (this.isInfinite())==false ? (methodResultID.isNaN())==false && methodResultID == Complex.ZERO", oracleDatapoints.get(3), false)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("restrictionEnabledMethodWithoutArgumentsParameterizedTestData")
    public void isRestrictionEnabled_METHOD_WITHOUT_ARGUMENTS_enabledCase(String testName, String partialExpression, OracleDatapoint oracleDatapoint, boolean expected) {
        MethodWithoutArgumentsRestriction restriction = MethodWithoutArgumentsRestrictionTest.getMethodWithoutArgumentsRestriction();
        List<String> partialExpressionTokens = split(parser.getPartialOracle(partialExpression));
        List<String> nextLegalTokens = getNextLegalTokensAccordingToGrammar(partialExpressionTokens);

        // Preconditions
        assertTrue(nextLegalTokens.stream().anyMatch(token -> restriction.getRestrictedTokens().contains(token)));

        boolean enabled = restriction.isEnabled(nextLegalTokens, split(parser.getPartialOracle(partialExpression)), oracleDatapoint);
        assertEquals(expected, enabled);
    }

    private static Stream<Arguments> restrictionEnabledMethodWithoutArgumentsParameterizedTestData() {
        return Stream.of(
                Arguments.of("disabledBadFormat1", " o1!=null && (o2", oracleDatapoints.get(0), false),
                Arguments.of("disabledBadFormat2", "o1.equals(CollectionBag.class", oracleDatapoints.get(0), false),
                Arguments.of("disabledBadFormat3", "Equator", oracleDatapoints.get(0), false),
                Arguments.of("disabledBadFormat4", "SomeClass.someField && (", oracleDatapoints.get(0), false),
                Arguments.of("disabledMethodHasArguments", "o1.equals(", oracleDatapoints.get(0), false),
                Arguments.of("enabledMethodWithoutArguments1", "this.getCoefficients(", oracleDatapoints.get(1), true),
                Arguments.of("disabledMethodWithAndWithoutArguments", "List.of(", oracleDatapoints.get(0), false),
                Arguments.of("enabledMethodWithoutArguments2", "divisor.isInfinite && (this.isInfinite(", oracleDatapoints.get(3), true),
                Arguments.of("enabledMethodWithoutArguments3", "divisor.isInfinite() && (this.isInfinite())==false ? (methodResultID.isNaN(", oracleDatapoints.get(3), true)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("restrictionEnabledForbidEndExpressionParameterizedTestData")
    public void isRestrictionEnabled_FORBID_END_EXPRESSION_enabledCase(String testName, String partialExpression, OracleDatapoint oracleDatapoint, boolean expected, List<String> allowedTokens, List<String> forbiddenTokens) {
        ForbidEndExpressionRestriction restriction = ForbidEndExpressionRestrictionTest.getForbidEndExpressionRestriction();
        List<String> partialExpressionTokens = split(parser.getPartialOracle(partialExpression));
        List<String> nextLegalTokens = getNextLegalTokensAccordingToGrammar(partialExpressionTokens);

        // Preconditions
        assertTrue(nextLegalTokens.contains("&&"));

        boolean enabled = restriction.isEnabled(nextLegalTokens, split(parser.getPartialOracle(partialExpression)), oracleDatapoint);
        assertEquals(expected, enabled);
        if (allowedTokens != null && forbiddenTokens != null) {
            assertTrue(restriction.getRestrictedTokens().containsAll(forbiddenTokens));
            assertTrue(restriction.getRestrictedTokens().stream().noneMatch(allowedTokens::contains));
        }
    }

    private static Stream<Arguments> restrictionEnabledForbidEndExpressionParameterizedTestData() {
        OracleDatapoint mockedOracleDatapoint = OracleDatapointTest.getEmptyOracleDatapoint();
        mockedOracleDatapoint.setClassName("SomeClass");
        mockedOracleDatapoint.setMethodSourceCode("public List<Integer> someMethod(ArrayList<Integer> arg1, Integer arg2) { return List.of(1, 2, 3); }");
        mockedOracleDatapoint.setClassSourceCode("import java.util.List; import java.util.ArrayList; public class SomeClass { " + mockedOracleDatapoint.getMethodSourceCode() + " }");
        mockedOracleDatapoint.setTokensMethodArguments(List.of(Triplet.with("arg1", "java.util", "ArrayList"), Triplet.with("arg2", "java.lang", "Integer")));
        mockedOracleDatapoint.setOracleType(OracleType.NORMAL_POST);
        mockedOracleDatapoint.setTokensProjectClasses(List.of());

        return Stream.of(
                Arguments.of("enabledForbidEndExpressionTest1", "o1", oracleDatapoints.get(0), true, List.of("==", "!="), List.of("&&", "?", ":", ";", ")")),
                Arguments.of("enabledForbidEndExpressionTest2", "(o1", oracleDatapoints.get(0), true, List.of("==", "!="), List.of("&&", "?", ":", ";", ")")),
                Arguments.of("disabledForbidEndExpressionTest3", "methodResultID", oracleDatapoints.get(0), false, null, null),
                Arguments.of("disabledForbidEndExpressionTest4", "methodResultID==true", oracleDatapoints.get(0), false, null, null),
                Arguments.of("disabledForbidEndExpressionTest5", "(methodResultID", oracleDatapoints.get(0), false, null, null),
                Arguments.of("disabledForbidEndExpressionTest6", "o1.equals(o2)", oracleDatapoints.get(0), false, null, null),
                Arguments.of("disabledForbidEndExpressionTest7", "(o1.equals(o2)", oracleDatapoints.get(0), false, null, null),
                Arguments.of("disabledForbidEndExpressionTest8", "(o1.equals(o2))", oracleDatapoints.get(0), false, null, null),
                Arguments.of("enabledForbidEndExpressionTest9", "o1.equals(o2) == o2", oracleDatapoints.get(0), true, List.of("."), List.of("|", "<<", "+", "&&", "?", ":", ";", ")")),
                Arguments.of("enabledForbidEndExpressionTest10", "(o1.equals(o2) == o2", oracleDatapoints.get(0), true, List.of("."), List.of("|", "<<", "+", "&&", "?", ":", ";", ")")),
                Arguments.of("disabledForbidEndExpressionTest11", "o1.equals(o2) == o2.equals(o1)", oracleDatapoints.get(0), false, null, null),
                Arguments.of("disabledForbidEndExpressionTest12", "this.getCoefficients().length >= this.value(3.1)", oracleDatapoints.get(1), false, null, null),
                Arguments.of("enabledForbidEndExpressionTest13", "this.getCoefficients().length >= methodResultID", oracleDatapoints.get(1), true, List.of("."), List.of("|", "<<", "+", "&&", "?", ":", ";", ")")),
                Arguments.of("enabledForbidEndExpressionTest14", "(this.getCoefficients().length >= this.value(3.1) + methodResultID", oracleDatapoints.get(1), true, List.of("."), List.of("&&", "?", ":", ";", ")")),
                Arguments.of("enabledForbidEndExpressionTest15", "this==methodResultID", oracleDatapoints.get(1), true, List.of("."), List.of("&&", "?", ":", ";", ")")),
                Arguments.of("disabledForbidEndExpressionTest16", "this instanceof PolynomialFunction", oracleDatapoints.get(2), false, null, null),
                Arguments.of("disabledForbidEndExpressionTest17", "methodResultID==null", oracleDatapoints.get(2), false, null, null),
                Arguments.of("disabledForbidEndExpressionTest18", "arg1==methodResultID", mockedOracleDatapoint, false, null, null),
                Arguments.of("disabledForbidEndExpressionTest19", "methodResultID==arg1", mockedOracleDatapoint, false, null, null),
                Arguments.of("enabledForbidEndExpressionTest20", "(arg2<arg2^arg1", mockedOracleDatapoint, true, List.of("."), List.of("&&", "?", ":", ";", ")"))
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("restrictionEnabledLastClassWithoutInstanceofParameterizedTestData")
    public void isRestrictionEnabled_LAST_CLASS_WITHOUT_INSTANCEOF_enabledCase(String testName, String partialExpression, OracleDatapoint oracleDatapoint, boolean expected) {
        LastClassWithoutInstanceofRestriction restriction = LastClassWithoutInstanceofRestrictionTest.getLastClassWithoutInstanceofRestriction();
        List<String> partialExpressionTokens = split(parser.getPartialOracle(partialExpression));
        List<String> nextLegalTokens = getNextLegalTokensAccordingToGrammar(partialExpressionTokens);

        // Preconditions
        assertTrue(nextLegalTokens.stream().anyMatch(token -> restriction.getRestrictedTokens().contains(token)));

        boolean enabled = restriction.isEnabled(nextLegalTokens, split(parser.getPartialOracle(partialExpression)), oracleDatapoint);
        assertEquals(expected, enabled);
    }

    private static Stream<Arguments> restrictionEnabledLastClassWithoutInstanceofParameterizedTestData() {
        return Stream.of(
                Arguments.of("disabledEmpty", "", oracleDatapoints.get(0), false),
                Arguments.of("disabledInstanceof", "this instanceof PolynomialFunction", oracleDatapoints.get(1), false),
                Arguments.of("disabledForbiddenByGrammar", "o1==null ? methodResultID == null &&", oracleDatapoints.get(0), false),
                Arguments.of("disabledNotClass", "o1==null ? methodResultID == null && o2", oracleDatapoints.get(0), false),
                Arguments.of("enabledClass", "Complex", oracleDatapoints.get(3), true),
                Arguments.of("disabledThis", "this", oracleDatapoints.get(3), false),
                Arguments.of("disabledArgument", "divisor", oracleDatapoints.get(3), false),
                Arguments.of("enabledArgumentAndClass", "divisor.isNaN() && DSCompiler", oracleDatapoints.get(3), true),
                Arguments.of("disabledLastMethodName", "divisor.equals", oracleDatapoints.get(3), false)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("restrictionEnabledNoArithmeticalOperatorParameterizedTestData")
    public void isRestrictionEnabled_NO_ARITHMETICAL_OPERATOR_enabledCase(String testName, String partialExpression, OracleDatapoint oracleDatapoint, boolean expected) {
        NoArithmeticalOperatorRestriction restriction = NoArithmeticalOperatorRestrictionTest.getNoArithmeticalOperatorRestriction();
        List<String> partialExpressionTokens = split(parser.getPartialOracle(partialExpression));
        List<String> nextLegalTokens = getNextLegalTokensAccordingToGrammar(partialExpressionTokens);

        // Preconditions
        assertTrue(nextLegalTokens.stream().anyMatch(token -> restriction.getRestrictedTokens().contains(token)));

        boolean enabled = restriction.isEnabled(nextLegalTokens, split(parser.getPartialOracle(partialExpression)), oracleDatapoint);
        assertEquals(expected, enabled);
    }

    private static Stream<Arguments> restrictionEnabledNoArithmeticalOperatorParameterizedTestData() {
        return Stream.of(
                Arguments.of("enabledObject", "((o1==o2", oracleDatapoints.get(0), true),
                Arguments.of("enabledArray", "this.getCoefficients().length>=this.getCoefficients()", oracleDatapoints.get(1), true),
                Arguments.of("disabledInt", "this.getCoefficients().length>=this.getCoefficients().length", oracleDatapoints.get(1), false)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("restrictionEnabledNoBitwiseOperatorParameterizedTestData")
    public void isRestrictionEnabled_NO_BITWISE_OPERATOR_enabledCase(String testName, String partialExpression, OracleDatapoint oracleDatapoint, boolean expected) {
        NoBitwiseOperatorRestriction restriction = NoBitwiseOperatorRestrictionTest.getNoBitwiseOperatorRestriction();
        List<String> partialExpressionTokens = split(parser.getPartialOracle(partialExpression));
        List<String> nextLegalTokens = getNextLegalTokensAccordingToGrammar(partialExpressionTokens);

        // Preconditions
        assertTrue(nextLegalTokens.stream().anyMatch(token -> restriction.getRestrictedTokens().contains(token)));

        boolean enabled = restriction.isEnabled(nextLegalTokens, split(parser.getPartialOracle(partialExpression)), oracleDatapoint);
        assertEquals(expected, enabled);
    }

    private static Stream<Arguments> restrictionEnabledNoBitwiseOperatorParameterizedTestData() {
        return Stream.of(
                Arguments.of("enabledObject", "((o1==o2", oracleDatapoints.get(0), true),
                Arguments.of("enabledArray", "this.getCoefficients().length>=this.getCoefficients()", oracleDatapoints.get(1), true),
                Arguments.of("disabledInt", "this.getCoefficients().length>=this.getCoefficients().length", oracleDatapoints.get(1), false)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("restrictionEnabledLiteralValuesParameterizedTestData")
    public void isRestrictionEnabled_LITERAL_VALUES_enabledCase(String testName, String partialExpression, OracleDatapoint oracleDatapoint, boolean expected, List<String> allowedTokens, List<String> forbiddenTokens) {
        LiteralValuesRestriction restriction = LiteralValuesRestrictionTest.getLiteralValuesRestriction();
        List<String> partialExpressionTokens = split(parser.getPartialOracle(partialExpression));
        List<String> nextLegalTokens = getNextLegalTokensAccordingToGrammar(partialExpressionTokens);

        // Preconditions
        assertTrue(nextLegalTokens.stream().anyMatch(token -> restriction.getPossiblyRestrictedTokens().contains(token)));

        boolean enabled = restriction.isEnabled(nextLegalTokens, split(parser.getPartialOracle(partialExpression)), oracleDatapoint);
        assertEquals(expected, enabled);
        if (allowedTokens != null && forbiddenTokens != null) {
            assertTrue(restriction.getRestrictedTokens().containsAll(forbiddenTokens));
            assertTrue(restriction.getRestrictedTokens().stream().noneMatch(allowedTokens::contains));
        }
    }

    private static Stream<Arguments> restrictionEnabledLiteralValuesParameterizedTestData() {
        return Stream.of(
                Arguments.of("disabledEqualsFalse", "(o1==o2)==", oracleDatapoints.get(0), false, null, null),
                Arguments.of("disabledTruePredicate", "", oracleDatapoints.get(0), false, null, null),
                Arguments.of("enabledComparisonNull", "o1==", oracleDatapoints.get(0), true, List.of("null"), List.of("true", "false", "1", "1.0", "\"someString\"")),
                Arguments.of("enabledComparisonBoolean", "o1.equals(o2)!=", oracleDatapoints.get(0), true, List.of("true", "false"), List.of("null", "1", "1.0", "\"someString\"")),
                Arguments.of("enabledComparisonInt1", "this.getCoefficients().length==", oracleDatapoints.get(1), true, List.of("1"), List.of("null", "true", "false", "1.0", "\"someString\"")),
                Arguments.of("enabledComparisonInt2", "this.getCoefficients().length!=", oracleDatapoints.get(1), true, List.of("1"), List.of("null", "true", "false", "1.0", "\"someString\"")),
                Arguments.of("enabledComparisonInt3", "this.getCoefficients().length>=", oracleDatapoints.get(1), true, List.of("1"), List.of("null", "true", "false", "1.0", "\"someString\"")),
                Arguments.of("enabledComparisonDouble1", "methodResultID.getReal()<", oracleDatapoints.get(1), true, List.of("1", "1.0"), List.of("null", "true", "false", "\"someString\"")),
                Arguments.of("enabledComparisonIntWrapper1", "this.toString().chars().boxed().findFirst().get()==", oracleDatapoints.get(1), true, List.of("1", "null"), List.of("true", "false", "1.0", "\"someString\"")),
                Arguments.of("enabledOperationInt1", "this.getCoefficients().length==this.getCoefficients().length^", oracleDatapoints.get(1), true, List.of("1"), List.of("null", "true", "false", "1.0", "\"someString\"")),
                Arguments.of("enabledOperationInt2", "this.getCoefficients().length!=this.getCoefficients().length+", oracleDatapoints.get(1), true, List.of("1"), List.of("null", "true", "false", "1.0", "\"someString\"")),
                Arguments.of("enabledOperationInt3", "this.getCoefficients().length>=this.getCoefficients().length>>~", oracleDatapoints.get(1), true, List.of("1"), List.of("null", "true", "false", "1.0", "\"someString\"")),
                Arguments.of("enabledOperationDouble1", "methodResultID.getReal()<methodResultID.getValue()*", oracleDatapoints.get(1), true, List.of("1", "1.0"), List.of("null", "true", "false", "\"someString\"")),
                Arguments.of("enabledOperationIntWrapper1", "this.toString().chars().boxed().findFirst().get()==this.getCoefficients().length-", oracleDatapoints.get(1), true, List.of("1", "null"), List.of("true", "false", "1.0", "\"someString\"")),
                Arguments.of("enabledMethodArgObject", "o1.equals(", oracleDatapoints.get(0), true, List.of("null", "\"someString\""), List.of("true", "false", "1", "1.0")),
                Arguments.of("disabledMethodArgAllAllowed", "String.valueOf(", oracleDatapoints.get(0), false, null, null),
                Arguments.of("enabledMethodArgBooleanAndString", "Boolean.valueOf(", oracleDatapoints.get(0), true, List.of("null", "\"someString\"", "true", "false"), List.of("1", "1.0")),
                Arguments.of("enabledMethodArgDouble1", "PolynomialFunction.evaluate(null, ", oracleDatapoints.get(1), true, List.of("1", "1.0"), List.of("null", "true", "false", "\"someString\"")),
                Arguments.of("enabledMethodArgInt1", "BagUtils.EMPTY_BAG.add(null, ", oracleDatapoints.get(0), true, List.of("1"), List.of("null", "true", "false", "1.0", "\"someString\""))
        );
    }
    @ParameterizedTest(name = "{0}")
    @MethodSource("restrictionEnabledNoNonEqIneqOperatorParameterizedTestData")
    public void isRestrictionEnabled_NO_NON_EQ_INEQ_OPERATOR_enabledCase(String testName, String partialExpression, boolean expected) {
        NoNonEqIneqOperatorRestriction restriction = NoNonEqIneqOperatorRestrictionTest.getNoNonEqIneqOperatorRestriction();
        List<String> partialExpressionTokens = split(parser.getPartialOracle(partialExpression));
        OracleDatapoint oracleDatapoint = oracleDatapoints.get(3);
        List<String> nextLegalTokens = getNextLegalTokensAccordingToGrammar(partialExpressionTokens);

        // Preconditions
        assertTrue(nextLegalTokens.stream().anyMatch(token -> restriction.getRestrictedTokens().contains(token)));

        boolean enabled = restriction.isEnabled(nextLegalTokens, split(parser.getPartialOracle(partialExpression)), oracleDatapoint);
        assertEquals(expected, enabled);
    }

    private static Stream<Arguments> restrictionEnabledNoNonEqIneqOperatorParameterizedTestData() {
        return Stream.of(
                Arguments.of("enabledClass", "Complex", true),
                Arguments.of("enabledNonNumberReturnType", "divisor", true),
                Arguments.of("disabledDoubleReturnType", "divisor.abs()", false),
                Arguments.of("disabledIntReturnType", "divisor.nthRoot(0).size()", false),
                Arguments.of("enabledArgumentAndClass", "divisor.isNaN() && DSCompiler", true)
        );
    }

    /**
     * These invocations of this ContextRestriction should throw an exception since the last token is a method name.
     * The third case is a class name that doesn't belong to the project. This should never happen, so the exception
     * is correctly thrown.
     */
    @Test
    public void isRestrictionEnabled_NO_NON_EQ_INEQ_OPERATOR_Exception() {
        NoNonEqIneqOperatorRestriction restriction = NoNonEqIneqOperatorRestrictionTest.getNoNonEqIneqOperatorRestriction();
        List<String> restrictedTokens = restriction.getRestrictedTokens();
        List<List<String>> partialExpressionTokensList = List.of(
                split(parser.getPartialOracle("divisor.equals")),
                split(parser.getPartialOracle("this.negate")),
                split(parser.getPartialOracle("divisor.abs() == 0 && Equator"))
        );
        OracleDatapoint oracleDatapoint = oracleDatapoints.get(3);
        for (List<String> partialExpressionTokens : partialExpressionTokensList) {
            List<String> nextLegalTokens = getNextLegalTokensAccordingToGrammar(partialExpressionTokens);
            // Preconditions
            assertTrue(nextLegalTokens.stream().anyMatch(restrictedTokens::contains));

            // Test
            assertThrows(UnsolvedSymbolException.class, () -> restriction.isEnabled(nextLegalTokens, partialExpressionTokens, oracleDatapoint));
        }
    }

    /**
     * This test is to check that, for all TrattoGrammar expressions contained in
     * the resources files, the TokenSuggester always returns as next legal tokens
     * the tokens that actually go next in the partial TrattoGrammar expression.
     * For instance, given the expression "occurrences<0;", this test checks that
     * after the partialExpression "", the token "someVarOrClassOrFieldOrMethod"
     * is returned, after "occurrences", the token "<" is returned, after
     * "occurrences<", the token "0" is returned, and after "occurrences<0", the
     * token ";" is returned.
     */
    public void getNextLegalTokensAuxTest(List<String> stringOracles) {
        // Instantiate Parser
        Parser parser = Parser.getInstance();

        // For each oracle, parse and split
        for (String stringOracle : stringOracles) {
            Oracle oracle = parser.getPartialOracle(stringOracle);
            List<String> oracleTokens = split(oracle);
            List<String> oracleSoFarTokens = new ArrayList<>();

            // For each token, add it to the oracle so far and
            for (String token : oracleTokens) {
                List<String> nextLegalTokens = TokenSuggester.getNextLegalTokensAccordingToGrammar(oracleSoFarTokens);
                assertTrue(nextLegalTokens.contains(getToken(token)));
                oracleSoFarTokens.add(token);
            }
        }
    }
    @Test
    @Disabled("Test too slow. Run once in a while to check that getNextLegalTokens method works for ALL oracles in the resources folder.")
    public void getNextLegalTokensTest() {
        List<String> stringOracles = readOraclesFromExternalFiles();
        getNextLegalTokensAuxTest(stringOracles);
    }
    @Test
    public void getNextLegalTokensOneSelectedOracleTest() {
        List<String> stringOracles = readOraclesFromExternalFiles();
        int last = stringOracles.size()-1;
        getNextLegalTokensAuxTest(List.of(stringOracles.get(last-31)));
    }

    /**
     * Auxiliary method to handle tokens that refer to strings, numbers or variable names
     */
    private String getToken(String token) {
        if (Tokens.TOKENS.contains(token)) {
            return token;
        }
        if (token.charAt(0) == '"') {
            return "\"someString\"";
        }
        try {
            Integer.parseInt(token);
            return "1";
        } catch (NumberFormatException ignored) {}
        try {
            Double.parseDouble(token);
            return "1.0";
        } catch (NumberFormatException ignored) {}
        return "someVarOrClassOrFieldOrMethod";
    }

    @Test
    public void tokenSuggesterComplexOracleValidAndInvalidTokensTest() {
        String oracle = "this.var.method() >= arg1+3 && Arrays.stream(arg2).anyMatch(jdVar -> jdVar < 5.1) || ((arg3.stream().allMatch(jdVar -> jdVar.equals(\"hello\", 3)))) ? (methodResultID instanceof SomeClass)==false : someVar.someMethod(SomeClass.class, null);";
        List<String> oracleTokens = split(parser.getOracle(oracle));
        // TODO: At each position, check that the suggested tokens match exactly the ones that are legal
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("restrictionEnabledLastMethodNameParameterizedTestData")
    public void getNextLegalTokensWithContextPlusInfo_LastMethodNameRestrictionTest(String testName, String partialExpression, OracleDatapoint oracleDatapoint, boolean expected) {
        List<Triplet<String, String, List<String>>> nextLegalTokensWithContextPlusInfo = getNextLegalTokensWithContextPlusInfo(split(parser.getPartialOracle(partialExpression)), oracleDatapoint);
        if (expected) {
            assertEquals(1, nextLegalTokensWithContextPlusInfo.size());
            assertEquals("(", nextLegalTokensWithContextPlusInfo.get(0).getValue0());
        } else {
            assertTrue(nextLegalTokensWithContextPlusInfo.size() > 1 || !nextLegalTokensWithContextPlusInfo.get(0).getValue0().equals(")") || !nextLegalTokensWithContextPlusInfo.stream().map(Triplet::getValue0).collect(Collectors.toList()).contains("("));
        }
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("restrictionEnabledLastClassWithoutInstanceofParameterizedTestData")
    public void getNextLegalTokensWithContextPlusInfo_LastClassWithoutInstanceofRestrictionTest(String testName, String partialExpression, OracleDatapoint oracleDatapoint, boolean expected) {
        List<Triplet<String, String, List<String>>> nextLegalTokensWithContextPlusInfo = getNextLegalTokensWithContextPlusInfo(split(parser.getPartialOracle(partialExpression)), oracleDatapoint);
        if (expected) {
            assertEquals(1, nextLegalTokensWithContextPlusInfo.size());
            assertEquals(".", nextLegalTokensWithContextPlusInfo.get(0).getValue0());
        } else {
            assertTrue(nextLegalTokensWithContextPlusInfo.size() > 1 || !nextLegalTokensWithContextPlusInfo.get(0).getValue0().equals(")"));
        }
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("restrictionEnabledNoArithmeticalOperatorParameterizedTestData")
    public void getNextLegalTokensWithContextPlusInfo_NoArithmeticalOperatorRestrictionTest(String testName, String partialExpression, OracleDatapoint oracleDatapoint, boolean expected) {
        List<String> restrictedTokens = NoArithmeticalOperatorRestrictionTest.getNoArithmeticalOperatorRestriction().getRestrictedTokens();
        List<Triplet<String, String, List<String>>> nextLegalTokensWithContextPlusInfo = getNextLegalTokensWithContextPlusInfo(split(parser.getPartialOracle(partialExpression)), oracleDatapoint);
        if (expected) {
            assertTrue(nextLegalTokensWithContextPlusInfo.stream().map(Triplet::getValue0).noneMatch(restrictedTokens::contains));
        } else {
            assertTrue(nextLegalTokensWithContextPlusInfo.stream().map(Triplet::getValue0).anyMatch(restrictedTokens::contains));
        }
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("restrictionEnabledNoBitwiseOperatorParameterizedTestData")
    public void getNextLegalTokensWithContextPlusInfo_NoBitwiseOperatorRestrictionTest(String testName, String partialExpression, OracleDatapoint oracleDatapoint, boolean expected) {
        List<String> restrictedTokens = NoBitwiseOperatorRestrictionTest.getNoBitwiseOperatorRestriction().getRestrictedTokens();
        List<Triplet<String, String, List<String>>> nextLegalTokensWithContextPlusInfo = getNextLegalTokensWithContextPlusInfo(split(parser.getPartialOracle(partialExpression)), oracleDatapoint);
        if (expected) {
            assertTrue(nextLegalTokensWithContextPlusInfo.stream().map(Triplet::getValue0).noneMatch(restrictedTokens::contains));
        } else {
            assertTrue(nextLegalTokensWithContextPlusInfo.stream().map(Triplet::getValue0).anyMatch(restrictedTokens::contains));
        }
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("restrictionEnabledNoNonEqIneqOperatorParameterizedTestData")
    public void getNextLegalTokensWithContextPlusInfo_NoNonEqIneqOperatorRestrictionTest(String testName, String partialExpression, boolean expected) {
        OracleDatapoint oracleDatapoint = oracleDatapoints.get(3);
        List<String> restrictedTokens = NoNonEqIneqOperatorRestrictionTest.getNoNonEqIneqOperatorRestriction().getRestrictedTokens();
        List<Triplet<String, String, List<String>>> nextLegalTokensWithContextPlusInfo = getNextLegalTokensWithContextPlusInfo(split(parser.getPartialOracle(partialExpression)), oracleDatapoint);
        if (expected) {
            assertTrue(nextLegalTokensWithContextPlusInfo.stream().map(Triplet::getValue0).noneMatch(restrictedTokens::contains));
        } else {
            assertTrue(nextLegalTokensWithContextPlusInfo.stream().map(Triplet::getValue0).anyMatch(restrictedTokens::contains));
        }
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("restrictionEnabledForbidEndExpressionParameterizedTestData")
    public void getNextLegalTokensWithContextPlusInfo_ForbidEndExpressionRestrictionTest(String testName, String partialExpression, OracleDatapoint oracleDatapoint, boolean expected, List<String> allowedTokens, List<String> forbiddenTokens) {
        List<Triplet<String, String, List<String>>> nextLegalTokensWithContextPlusInfo = getNextLegalTokensWithContextPlusInfo(split(parser.getPartialOracle(partialExpression)), oracleDatapoint);
        List<String> restrictedTokens = ForbidEndExpressionRestrictionTest.getForbidEndExpressionRestriction().getRestrictedTokens();
        if (expected) {
            assertTrue(nextLegalTokensWithContextPlusInfo.stream().map(Triplet::getValue0).noneMatch(restrictedTokens::contains));
            assertTrue(nextLegalTokensWithContextPlusInfo.stream().map(Triplet::getValue0).noneMatch(forbiddenTokens::contains));
            assertTrue(nextLegalTokensWithContextPlusInfo.stream().map(Triplet::getValue0).anyMatch(allowedTokens::contains));
        } else {
            assertTrue(nextLegalTokensWithContextPlusInfo.stream().map(Triplet::getValue0).collect(Collectors.toList()).contains("&&"));
        }
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("restrictionEnabledLiteralValuesParameterizedTestData")
    public void getNextLegalTokensWithContextPlusInfo_LiteralValuesRestrictionTest(String testName, String partialExpression, OracleDatapoint oracleDatapoint, boolean expected, List<String> allowedTokens, List<String> forbiddenTokens) {
        List<Triplet<String, String, List<String>>> nextLegalTokensWithContextPlusInfo = getNextLegalTokensWithContextPlusInfo(split(parser.getPartialOracle(partialExpression)), oracleDatapoint);
        List<String> restrictedTokens = LiteralValuesRestrictionTest.getLiteralValuesRestriction().getRestrictedTokens();
        List<String> possiblyRestrictedTokens = LiteralValuesRestrictionTest.getLiteralValuesRestriction().getPossiblyRestrictedTokens();
        if (expected) {
            assertTrue(nextLegalTokensWithContextPlusInfo.stream().map(Triplet::getValue0).noneMatch(restrictedTokens::contains));
            assertTrue(nextLegalTokensWithContextPlusInfo.stream().map(Triplet::getValue0).noneMatch(forbiddenTokens::contains));
            assertTrue(nextLegalTokensWithContextPlusInfo.stream().map(Triplet::getValue0).anyMatch(allowedTokens::contains));
        } else {
            assertTrue(nextLegalTokensWithContextPlusInfo.stream().map(Triplet::getValue0).anyMatch(possiblyRestrictedTokens::contains));
        }
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("restrictionEnabledMethodWithoutArgumentsParameterizedTestData")
    public void getNextLegalTokensWithContextPlusInfo_MethodWithoutArgumentsRestrictionTest(String testName, String partialExpression, OracleDatapoint oracleDatapoint, boolean expected) {
        List<Triplet<String, String, List<String>>> nextLegalTokensWithContextPlusInfo = getNextLegalTokensWithContextPlusInfo(split(parser.getPartialOracle(partialExpression)), oracleDatapoint);
        if (expected) {
            assertEquals(1, nextLegalTokensWithContextPlusInfo.size());
            assertEquals(")", nextLegalTokensWithContextPlusInfo.get(0).getValue0());
        } else {
            // nextLegalTokensGrammar is to handle case where this restriction is not enabled but ")" is actually the only possible token next
            List<String> nextLegalTokensGrammar = getNextLegalTokensAccordingToGrammar(split(parser.getPartialOracle(partialExpression)));
            assertTrue(nextLegalTokensWithContextPlusInfo.size() > 1 || List.of("(", ".").contains(nextLegalTokensWithContextPlusInfo.get(0).getValue0()) || (nextLegalTokensGrammar.size() == 2 && nextLegalTokensGrammar.containsAll(List.of(",", ")"))));
        }
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("getNextLegalTokensWithContextPlusInfoTestParameterizedTestData")
    public void getNextLegalTokensWithContextPlusInfo_Restriction_CaseTest(String testName, String partialExpression, OracleDatapoint oracleDatapoint, List<String> someExpectedTokens, List<String> someNotExpectedTokens) {
        List<String> nextLegalTokensWithContextPlusInfo = getNextLegalTokensWithContextPlusInfo(split(parser.getPartialOracle(partialExpression)), oracleDatapoint).stream().map(Triplet::getValue0).collect(Collectors.toList());

        assertTrue(nextLegalTokensWithContextPlusInfo.containsAll(someExpectedTokens));
        assertTrue(nextLegalTokensWithContextPlusInfo.stream().noneMatch(someNotExpectedTokens::contains));
    }

    private static Stream<Arguments> getNextLegalTokensWithContextPlusInfoTestParameterizedTestData() {
        return Stream.of(
                Arguments.of("ClassName", "Complex", oracleDatapoints.get(3), List.of("."), List.of("instanceof", "<", "==")),
                Arguments.of("MethodArgumentClassReturnType", "divisor", oracleDatapoints.get(3), List.of("instanceof", ".", "=="), List.of("<", "&&")),
                Arguments.of("MethodName", "divisor.equals", oracleDatapoints.get(3), List.of("("), List.of("instanceof", "<", ".", "==")),
                Arguments.of("MethodNameOpen", "divisor.equals(", oracleDatapoints.get(3), List.of("this", "divisor"), List.of(")")),
                Arguments.of("ClassReturnType", "divisor.conjugate()", oracleDatapoints.get(3), List.of("instanceof", ".", "==", "!="), List.of("<", "&&", "?")),
                Arguments.of("SomeContentAndClassName", "divisor.conjugate()==null && PolynomialSplineFunction", oracleDatapoints.get(3), List.of("."), List.of("instanceof", "<", "!=")),
                Arguments.of("PrimitiveReturnType", "divisor.nthRoot(0).size()", oracleDatapoints.get(3), List.of("<"), List.of("instanceof", ".")),
                Arguments.of("PrimitiveReturnTypeEquals", "divisor.nthRoot(0).size()==", oracleDatapoints.get(3), List.of("0", "~", "divisor"), List.of("1.0", "true", "false", "null")),
                Arguments.of("NonIntegralEquals", "divisor.nthRoot(0)==", oracleDatapoints.get(3), List.of("divisor", "null"), List.of("~", "0", "1.0", "true", "false")),
                Arguments.of("NonIntegralEqualsElement", "(divisor.nthRoot(0)==divisor", oracleDatapoints.get(3), List.of("."), List.of("&&", "+", "^", ">>", "instanceof", ")")),
                Arguments.of("PredicateWithParenthesis", "(divisor.isNaN())", oracleDatapoints.get(3), List.of("==", "&&", "?"), List.of("instanceof", ".", ">", "!=", ":", ";")),
                Arguments.of("ComparisonNoIntegral", "methodResultID==Complex.ZERO", oracleDatapoints.get(3), List.of(".", "&&", "?"), List.of("+", "^", ">>", "(")),
                Arguments.of("ComparisonNoIntegralClass", "methodResultID==Complex", oracleDatapoints.get(3), List.of("."), List.of("+", "^", ">>", "(", "&&", "?")),
                Arguments.of("ThisInstanceof", "this instanceof PolynomialFunction", oracleDatapoints.get(2), List.of("&&", ";"), List.of(".", "(", "==", "!=", "<")),
                Arguments.of("OpenMethod", "methodResultID.createComplex(this.getReal()", oracleDatapoints.get(3), List.of(","), Tokens.TOKENS.stream().filter(t -> !t.equals(",")).collect(Collectors.toList())),
                Arguments.of("OpenMethodOnlyClosingParenthesis", "methodResultID.createComplex(this.getReal(), this.getImaginary()", oracleDatapoints.get(3), List.of(")"), Tokens.TOKENS.stream().filter(t -> !t.equals(")")).collect(Collectors.toList())),
                Arguments.of("OpenMethodMultipleSignatures", "Complex.valueOf(this.getReal()", oracleDatapoints.get(3), List.of(",", ")"), Tokens.TOKENS.stream().filter(t -> !List.of(",", ")").contains(t)).collect(Collectors.toList())),
                Arguments.of("OpenMethodArgumentPeriod", "Complex.equals(divisor.", oracleDatapoints.get(3), List.of("isNaN", "isInfinite"), List.of("class")),
                Arguments.of("OpenMethodArgumentMethodPeriodDeadEnd", "Complex.equals(divisor.isInfinite()", oracleDatapoints.get(3), List.of(), Tokens.TOKENS),
                Arguments.of("OpenMethodArgumentPeriodClassDeadEnd", "Complex.equals(Complex.class", oracleDatapoints.get(3), List.of(), Tokens.TOKENS),
                Arguments.of("OpenMethodNoArguments", "this.getReal(", oracleDatapoints.get(3), List.of(")"), Tokens.TOKENS.stream().filter(t -> !t.equals(")")).collect(Collectors.toList())),
                Arguments.of("OpenMethodWithAndWithoutArguments", "List.of(", oracleDatapoints.get(0), List.of(")", "o1", "\"stringValue\"", "\"alsoThis\"", "null"), List.of("&&", "methodResultID", "0", "1.0", "42", "true", "false")),
                Arguments.of("OperationInt", "this.getCoefficients().length>=this.getCoefficients().length>>~", oracleDatapoints.get(1), List.of("1", "this", "PolynomialFunction"), List.of("null", "true", "false", "1.0", "\"someString\"")),
                Arguments.of("MethodArgumentAllAllowed", "String.valueOf(", oracleDatapoints.get(0), List.of("0", "1", "true", "false", "null", "Bag"), List.of("methodResultID", "1.0")),
                Arguments.of("MethodArgumentDoubleAllowed", "PolynomialFunction.evaluate(null, ", oracleDatapoints.get(1), List.of("1", "3.1", "PolynomialFunction"), List.of("null", "true", "false", "\"someString\"")),
                Arguments.of("JdVarComplexType", "true ? methodResultID.stream().noneMatch(jdVar -> jdVar.", oracleDatapoints.get(4), List.of("isInfinite", "equals", "isNaN"), List.of("nonExistingMethod", "class", "==", "&&")),
                Arguments.of("Empty", "", oracleDatapoints.get(0), List.of("Equator", "o1", "(", "true", "this", ";"), List.of("Arrays", "methodResultID", "jdVar", "1")),
                Arguments.of("ArraysStreamDeadEnd", "true ? Arrays.stream(", oracleDatapoints.get(0), List.of(), Tokens.TOKENS),
                Arguments.of("StreamDeadEnd", "true?methodResultID.stream().noneMatch(jdVar->jdVar==n", oracleDatapoints.get(4), List.of(), Tokens.TOKENS),
                Arguments.of("ArraysStream", "true ? Arrays.stream(", oracleDatapoints.get(5), List.of("methodResultID", "coefficients"), Tokens.TOKENS.stream().filter(t -> !t.equals("methodResultID")).collect(Collectors.toList()))
        );
    }
}
