package star.tratto.oraclegrammar.custom;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Injector;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.util.StringInputStream;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import star.tratto.identifiers.file.FileFormat;
import star.tratto.identifiers.file.FileName;
import star.tratto.identifiers.path.Path;
import star.tratto.oraclegrammar.TrattoGrammarStandaloneSetup;
import star.tratto.oraclegrammar.trattoGrammar.*;
import star.tratto.util.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static star.tratto.oraclegrammar.custom.Splitter.split;
import static star.tratto.util.StringUtils.compactExpression;

/**
 * This class provides the functionality to parse a string oracle written in TrattoGrammar
 * and convert it into an Oracle object. It leverages the Xtext libraries to do so. It also
 * includes helper functions to extract elements from oracles (e.g., variables and classes).
 */
public class Parser {
    private static Parser parser = getInstance();
    private final XtextResourceSet xtextResourceSet;
    private final Resource resource; // Provides useful methods for handling the oracle expressed in TrattoGrammar
    private static final String DUMMY_URI = "dummy:/dummy.tr";

    // Constants for handling conflictive partial oracles:
    private static final String BIT_CONFLICTIVE_TOKENS_REGEX = ".*(\\||\\^|&|>>>|>>|<<)~?$";
    private static final String BIT_THIS_CONFLICTIVE_TOKENS_REGEX = ".*(\\||\\^|&|>>>|>>|<<)~?this$";
    private static final String THIS_ERROR_MESSAGE_REGEX = "no viable alternative at input '(<EOF>|this)'";
    private static final String PERIOD_ERROR_MESSAGE_REGEX = "(no viable alternative at input '<EOF>'|mismatched input '<EOF>' expecting 'stream')";
    private static final String AUX_VAR = "auxVarForConflictivePartialOracle";

    private static final Logger logger = LoggerFactory.getLogger(Parser.class);

    private Parser() {
        Injector injector = new TrattoGrammarStandaloneSetup().createInjectorAndDoEMFRegistration();
        xtextResourceSet = injector.getInstance(XtextResourceSet.class);
        resource = xtextResourceSet.createResource(URI.createURI(DUMMY_URI));
    }

    public static Parser getInstance() {
        if (parser == null) {
            parser = new Parser();
        }
        return parser;
    }

    public Resource getResource() {
        return resource;
    }

    private Resource loadOracle(String oracle) {
        resource.unload();
        InputStream stringOracle = new StringInputStream(oracle);
        try {
            resource.load(stringOracle, xtextResourceSet.getLoadOptions());
        } catch (IOException e) {
            logger.error("Impossible to parse oracle string: {}", stringOracle);
            return null;
        }
        return resource;
    }

    /**
     * There are some partial oracles that cannot be correctly parsed due to limitations of the underlying
     * grammar (TrattoGrammar) and the Xtext libraries. This method handles those cases by adding up to two
     * specific tokens so that it can be parsed correctly. If this is not a conflictive partial oracle, it
     * is parsed normally (see {@link #loadOracle}).
     */
    private Resource loadPartialOracle(String partialOracle) {
        String compactOracle = compactExpression(partialOracle);
        String auxOracleSuffix = "";

        if (compactOracle.matches(BIT_CONFLICTIVE_TOKENS_REGEX)) {
            auxOracleSuffix = AUX_VAR; // If ends with bit shift/logical operator, always add aux var to handle
        } else if (compactOracle.matches(BIT_THIS_CONFLICTIVE_TOKENS_REGEX)) {
            auxOracleSuffix = "." + AUX_VAR; // If ends with bit shift/logical operator and "this", add period and aux var
        } else if (compactOracle.endsWith(".") || compactOracle.endsWith("this")) {
            List<Resource.Diagnostic> parseErrors = loadOracle(partialOracle).getErrors();
            String firstParseErrorMessage = parseErrors.get(0).getMessage(); // Can safely assume there is at least one error
            if (compactOracle.endsWith("this") && parseErrors.size() == 1 && firstParseErrorMessage.matches(THIS_ERROR_MESSAGE_REGEX)) {
                auxOracleSuffix = "." + AUX_VAR; // If ends with "this" and parse error matches, add period and aux var
            } else if (compactOracle.endsWith(".") && (parseErrors.size() != 1 || !firstParseErrorMessage.matches(PERIOD_ERROR_MESSAGE_REGEX))) {
                auxOracleSuffix = AUX_VAR; // If ends with ".", add aux var except if more than one error or error message does not match
            }
        }

        List<EObject> auxOracleContents = getAllOracleContents(compactOracle + auxOracleSuffix);

        // If partialOracle is actually conflictive, modify resource to remove elements added in the auxOracle
        if (!auxOracleSuffix.equals("")) {
            EObject auxOracleElement = auxOracleContents.get(auxOracleContents.size() - 1); // Auxiliary suffix is ALWAYS last element
            EList<EStructuralFeature> auxElementFeatures = auxOracleElement.eClass().getEAllStructuralFeatures();
            for (EStructuralFeature feature : auxElementFeatures) { // If feature is "." or AUX_VAR, and it was included in suffix, remove from auxOracle
                Object featureValue = auxOracleElement.eGet(feature);
                if (featureValue instanceof String && (featureValue.equals(AUX_VAR) || (featureValue.equals(".") && auxOracleSuffix.contains(".")))) {
                    auxOracleElement.eUnset(feature);
                }
            }
        }

        return resource;
    }

    /**
     * Obtain list of EObjects from oracle. The oracle may be partial, but in that case,
     * the list of contents returned may be wrong. Regardless, this method is useful to
     * analyze the error columns returned by the parser, for instance.
     * @param oracle String oracle as generated by Jdoctor.
     * @return null if the oracle could not be parsed
     */
    public EList<EObject> parseOracle(String oracle) {
        return parseOracle(oracle, false);
    }

    /**
     * Obtain list of EObjects from oracle
     * @param oracle String oracle as generated by Jdoctor.
     * @param partial true if the oracle is partial, false otherwise. If true, this will
     *                allow the parser to make sure that the list of contents returned
     *                is correct (see {@link #loadPartialOracle}).
     * @return null if the oracle could not be parsed
     */
    private EList<EObject> parseOracle(String oracle, boolean partial) {
        Resource res = partial ? loadPartialOracle(oracle) : loadOracle(oracle);
        if (res == null) {
            return null;
        }
        return res.getContents();
    }

    /**
     * Obtain Oracle object from oracle
     * @param oracle String oracle as generated by Jdoctor. If it is partial, this method
     *               may return a wrong oracle. To make sure that it is parsed correctly,
     *               use {@link #getPartialOracle} instead.
     * @return null if the oracle could not be parsed or if it is an empty string
     */
    public Oracle getOracle(String oracle) {
        return getOracle(oracle, false);
    }

    public Oracle getPartialOracle(String oracle) {
        return getOracle(oracle, true);
    }

    private Oracle getOracle(String oracle, boolean partial) {
        EList<EObject> allOraclesContents = parseOracle(oracle, partial);
        if (allOraclesContents == null || allOraclesContents.isEmpty()) {
            return null;
        }
        return (Oracle) allOraclesContents.get(0).eContents()
                .stream()
                .filter(eObject -> eObject instanceof Oracle)
                .collect(Collectors.toList()).get(0);
    }

    /**
     * Decompose an oracle into all sub-elements it is composed of, and return it as a list.
     * @param oracle String oracle as generated by Jdoctor. If it is partial, this method
     *               may return a wrong list of elements. To make sure that it is parsed
     *               correctly, use {@link #getAllPartialOracleContents} instead.
     * @return null if the oracle could not be parsed or if it is an empty string
     */
    private List<EObject> getAllOracleContents(String oracle) {
        return getAllOracleContents(oracle, false);
    }

    private List<EObject> getAllPartialOracleContents(String oracle) {
        return getAllOracleContents(oracle, true);
    }

    private List<EObject> getAllOracleContents(String oracle, boolean partial) {
        Resource res = partial ? loadPartialOracle(oracle) : loadOracle(oracle);
        if (res == null) {
            return null;
        }
        return IteratorExtensions.toList(resource.getAllContents());
    }

    /**
     * Given a (possibly partial) oracle as a string, parse it and return the last element with modifiers,
     * i.e., VarOrClassWithModifiers, IsolableVarOrClassWithModifiers, or ThisWithMandatoryModifiers.
     * IMPORTANT NOTE: Such element is only returned if it contains a period. For example, given a partial
     * oracle like "this.field && arg1.method(", this method will return the EObject corresponding to
     * "arg1.method(". As another example, given the partial oracle "methodResultID.field && this", this
     * method will return the EObject corresponding to "methodResultID.field".
     * @return null if no element with modifiers was found
     */
    public EObject findLastElementWithModifiers(String oracle) {
        List<EObject> allOracleContents = getAllPartialOracleContents(oracle);
        List<EObject> varOrClassModifiers = allOracleContents.stream().filter(eo -> eo instanceof VarOrClassModifier && (
                (((VarOrClassModifier) eo).getClassFieldWithPeriod() != null && ((VarOrClassModifier) eo).getClassFieldWithPeriod().getPeriod() != null) ||
                (((VarOrClassModifier) eo).getMethodCallWithPeriod() != null && ((VarOrClassModifier) eo).getMethodCallWithPeriod().getPeriod() != null)
        )).collect(Collectors.toList());
        if (varOrClassModifiers.isEmpty()) {
            return null;
        }
        return varOrClassModifiers.get(varOrClassModifiers.size() - 1).eContainer();
    }

    /**
     * This function is similar to {@link #findLastElementWithModifiers}, but with two differences:
     * 1) it considers only elements that contain method calls, not class fields; and 2) it considers
     * only elements that contain unfinished method calls, i.e., missing the closing parenthesis.
     * @return null if no element with an unfinished method call was found
     */
    public EObject findLastElementWithUnfinishedMethodModifiers(String oracle) {
        List<EObject> allOracleContents = getAllPartialOracleContents(oracle);
        List<EObject> methodModifiers = allOracleContents
                .stream()
                .filter(
                    eo -> eo instanceof VarOrClassModifier &&
                    (((VarOrClassModifier) eo).getMethodCallWithPeriod() != null && ((VarOrClassModifier) eo).getMethodCallWithPeriod().getPeriod() != null) &&
                    (((VarOrClassModifier) eo).getMethodCallWithPeriod().getMethodCall().getClosingParenthesis() == null))
                .collect(Collectors.toList());
        if (methodModifiers.isEmpty()) {
            return null;
        }
        return methodModifiers.get(methodModifiers.size() - 1).eContainer();
    }

    public EObject getLastEObject(String oracle) {
        List<EObject> allOracleContents = getAllPartialOracleContents(oracle);
        return allOracleContents.get(allOracleContents.size() - 1);
    }

    /**
     * @return null if the oracle contains no "instanceof" token; "this" if that's the preceding
     * element; or the preceding element as an EObject (CanEvaluateToPrimitive) otherwise.
     */
    public Object findElementPrecedingLastInstanceOf(String oracle) {
        List<Object> elementsPrecedingInstanceOf = getAllPartialOracleContents(oracle)
                .stream()
                .filter(eo -> eo instanceof ClauseWithVars && (
                        ((ClauseWithVars) eo).getFirstInstanceOfOperator() != null ||
                        ((ClauseWithVars) eo).getSecondInstanceOfOperator() != null)
                ).map(eo -> ((ClauseWithVars) eo).getFirstInstanceOfOperator() != null ? "this" : ((ClauseWithVars) eo).getCanEvaluateToPrimitive())
                .collect(Collectors.toList());
        if (elementsPrecedingInstanceOf.isEmpty()) {
            return null;
        }
        return elementsPrecedingInstanceOf.get(elementsPrecedingInstanceOf.size() - 1);
    }

    /**
     * @return null if the oracle contains no NonEqIneqOp token or the preceding
     * CanEvaluateToPrimitive element otherwise.
     */
    public CanEvaluateToPrimitive findElementPrecedingLastNonEqIneqOp(String oracle) {
        List<CanEvaluateToPrimitive> elementsPrecedingNonEqIneqOp = getAllCanEvalToPrimInEObject(oracle, ClauseWithVars.class)
                .stream()
                .filter(eo -> ((ClauseWithVars) eo.eContainer()).getNonEqIneqOperator() != null)
                .collect(Collectors.toList());
        if (elementsPrecedingNonEqIneqOp.isEmpty()) {
            return null;
        }
        return elementsPrecedingNonEqIneqOp.get(elementsPrecedingNonEqIneqOp.size() - 1);
    }

    /**
     * @return null if the oracle contains no CanEvaluateToPrimitive contained within a ClauseWithVars
     */
    public CanEvaluateToPrimitive findLastCanEvalToPrimInClauseWithVars(String oracle) {
        List<CanEvaluateToPrimitive> allCanEvalToPrimInClauseWithVars = getAllCanEvalToPrimInEObject(oracle, ClauseWithVars.class);
        if (allCanEvalToPrimInClauseWithVars.isEmpty()) {
            return null;
        }
        return allCanEvalToPrimInClauseWithVars.get(allCanEvalToPrimInClauseWithVars.size() - 1);
    }

    /**
     * @return null if the oracle contains no CanEvaluateToPrimitive contained within an OtherComparisonElement
     */
    public CanEvaluateToPrimitive findLastCanEvalToPrimInOtherCompElem(String oracle) {
        List<CanEvaluateToPrimitive> allCanEvalToPrimInClauseWithVars = getAllCanEvalToPrimInEObject(oracle, OtherComparisonElement.class);
        if (allCanEvalToPrimInClauseWithVars.isEmpty()) {
            return null;
        }
        return allCanEvalToPrimInClauseWithVars.get(allCanEvalToPrimInClauseWithVars.size() - 1);
    }

    private List<CanEvaluateToPrimitive> getAllCanEvalToPrimInEObject(String oracle, Class eObjectClass) {
        return getAllPartialOracleContents(oracle)
                .stream()
                .filter(eo -> eo instanceof CanEvaluateToPrimitive && eObjectClass.isInstance(eo.eContainer()))
                .map(eo -> (CanEvaluateToPrimitive) eo)
                .collect(Collectors.toList());
    }

    /**
     * @return null if the oracle contains no ClauseWithVars
     */
    public ClauseWithVars findLastClauseWithVars(String oracle) {
        List<ClauseWithVars> allClausesWithVars = getAllPartialOracleContents(oracle)
                .stream()
                .filter(eo -> eo instanceof ClauseWithVars)
                .map(eo -> (ClauseWithVars) eo)
                .collect(Collectors.toList());
        if (allClausesWithVars.isEmpty()) {
            return null;
        }
        return allClausesWithVars.get(allClausesWithVars.size() - 1);
    }

    /**
     * Given an expression containing the token "jdVar" and an oracle within which the expression is contained,
     * this method returns a String representing an element of the same type as jdVar. For example, given the
     * expression <code>jdVar.field</code> and the oracle
     * <code>Arrays.stream(arg1).anyMatch(jdVar -> jdVar.field == null);</code>, this method will return the
     * String "arg1[0]". As another example, given the expression <code>jdVar</code> and the oracle
     * <code>methodResultID.stream().allMatch(jdVar -> jdVar.isValid());</code>, this method will return the
     * String "methodResultID.get(0)". NOTE: if there are multiple matches, this method returns the last
     * occurrence of jdVar.
     *
     * @param oracle the oracle within which the expression is contained
     * @param jdVarExpression the expression containing the token "jdVar"
     * @return null if the expression doesn't contain the token "jdVar", if the oracle doesn't contain the token
     * "jdVar", or if there are no matches for the token "jdVar" in the oracle. Otherwise, it returns a String
     * representing an element of the same type as jdVar.
     */
    public String getLastJdVarArrayElement(String oracle, String jdVarExpression) {
        return getLastJdVarArrayElementInternal(oracle, jdVarExpression);
    }

    public String getLastJdVarArrayElement(String oracle) {
        return getLastJdVarArrayElementInternal(oracle, null);
    }

    private String getLastJdVarArrayElementInternal(String oracle, String jdVarExpression) {
        if (!oracle.contains("jdVar") || (jdVarExpression != null && !jdVarExpression.contains("jdVar"))) {
            return null;
        }

        List<ClauseTrue> clauseTruesWithJdVars = getAllPartialOracleContents(oracle)
                .stream()
                .filter(eo -> eo instanceof ClauseTrue
                        && ((ClauseTrue)eo).getArrayStreamClauseContinuation() != null
                        && (jdVarExpression == null || compactExpression(split(eo)).contains(compactExpression(jdVarExpression))))
                .map(eo -> (ClauseTrue) eo)
                .collect(Collectors.toList());

        if (clauseTruesWithJdVars.isEmpty()) {
            return null;
        } else {
            ClauseTrue lastClauseTrueWithJdVar = clauseTruesWithJdVars.get(clauseTruesWithJdVars.size() - 1);
            if (lastClauseTrueWithJdVar.getArrayStreamClauseFromClass() != null) {
                return compactExpression(split(lastClauseTrueWithJdVar.getArrayStreamClauseFromClass().getIsolableVarOrClass())) + "[0]";
            } else if (lastClauseTrueWithJdVar.getArrayStreamClauseFromVar() != null) {
                return compactExpression(split(lastClauseTrueWithJdVar.getArrayStreamClauseFromVar().getGeneralVarOrClass())) + ".get(0)";
            } else {
                throw new IllegalStateException("Unexpected ClauseTrue with jdVar: " + compactExpression(split(lastClauseTrueWithJdVar)));
            }
        }
    }

    /**
     * This method is necessary to populate the last two columns of the oracles dataset, since it allows to retrieve
     * all oracle-specific variables that are not "this", "methodResultID" or method arguments. In other words, it
     * retrieves all sub-elements containing one or more method and/or attribute. For example, given the oracle
     * <code>SomeClass.someMethod()==1 && a.b.c(d.e.f, g.h, i).equals(this.j())</code>, this method will return the
     * following sub-elements:
     * <code>
     * <ul>
     *     <li>SomeClass.someMethod()</li>
     *     <li>a.b</li>
     *     <li>a.b.c(d.e.f, g.h, i)</li>
     *     <li>a.b.c(d.e.f, g.h, i).equals(this.j())</li>
     *     <li>d.e</li>
     *     <li>d.e.f</li>
     *     <li>g.h</li>
     *     <li>this.j()</li>
     * </ul>
     * </code>
     * As illustrated, all returned elements contain at least one period.
     * @param oracle String oracle as generated by Jdoctor. CANNOT be partial.
     */
    public List<EObject> getAllMethodsAndAttributes(String oracle) {
        List<EObject> allMethodsAndAttributes = new ArrayList<>();

        // Get all VarOrClassModifiers
        List<VarOrClassModifier> varOrClassModifiers = getAllOracleContents(oracle)
                .stream()
                .filter(eo -> eo instanceof VarOrClassModifier)
                .map(eo -> (VarOrClassModifier) eo)
                .collect(Collectors.toList());

        // For each VarOrClassModifier, get its parent and keep only the modifiers up to the current one
        for (VarOrClassModifier varOrClassModifier : varOrClassModifiers) {
            EObject parent = varOrClassModifier.eContainer();
            EList<VarOrClassModifier> parentModifiers = getVarOrClassModifiers(parent);
            int currentModifierIndex = parentModifiers.indexOf(varOrClassModifier);
            // In order not to modify original element, clone it and remove all modifiers after the current one
            EObject parentClone = EcoreUtil.copy(parent);
            EList<VarOrClassModifier> parentModifiersClone = getVarOrClassModifiers(parentClone);
            parentModifiersClone.removeIf(e -> parentModifiersClone.indexOf(e) > currentModifierIndex);

            allMethodsAndAttributes.add(parentClone);
        }

        return allMethodsAndAttributes;
    }

    private static EList<VarOrClassModifier> getVarOrClassModifiers(EObject eObject) {
        if (eObject instanceof VarOrClassWithModifiers) {
            return ((VarOrClassWithModifiers) eObject).getVarOrClassModifiers();
        } else if (eObject instanceof IsolableVarOrClassWithModifiers) {
            return ((IsolableVarOrClassWithModifiers) eObject).getVarOrClassModifiers();
        } else if (eObject instanceof ThisWithMandatoryModifiers) {
            return ((ThisWithMandatoryModifiers) eObject).getVarOrClassModifiers();
        } else {
            throw new IllegalArgumentException("Unexpected element type: " + (eObject == null ? null : eObject.getClass().getName()));
        }
    }

    public static Object getRightElementOfOperation(OtherComparisonElement otherCompElem) {
        if (otherCompElem.getBitCompatibleElement() != null) {
            return getSetElementInBitCompatibleElement(otherCompElem.getBitCompatibleElement());
        } else if (otherCompElem.getFirstDoubleCompatibleElement() != null) {
            return getSetElementInDoubleCompatibleElement(otherCompElem.getFirstDoubleCompatibleElement());
        } else if (otherCompElem.getSecondDoubleCompatibleElement() != null) {
            return getSetElementInDoubleCompatibleElement(otherCompElem.getSecondDoubleCompatibleElement());
        } else {
            return null;
        }
    }

    private static Object getSetElementInBitCompatibleElement(BitCompatibleElement bitCompatibleElement) {
        if (bitCompatibleElement.getSInt() != null) {
            return bitCompatibleElement.getSInt();
        } else if (bitCompatibleElement.getCanEvaluateToPrimitive() != null) {
            return bitCompatibleElement.getCanEvaluateToPrimitive();
        } else {
            return null;
        }
    }

    private static Object getSetElementInDoubleCompatibleElement(DoubleCompatibleElement doubleCompatibleElement) {
        if (doubleCompatibleElement.getDouble() != null) {
            return doubleCompatibleElement.getDouble();
        } else if (doubleCompatibleElement.getBitCompatibleElement() != null) {
            return getSetElementInBitCompatibleElement(doubleCompatibleElement.getBitCompatibleElement());
        } else {
            return null;
        }
    }

    public static String getOperationOperator(OtherComparisonElement otherCompElem) {
        if (otherCompElem.getBitwiseLogicalOperator() != null) {
            return otherCompElem.getBitwiseLogicalOperator();
        } else if (otherCompElem.getBitwiseShiftOperator() != null) {
            return otherCompElem.getBitwiseShiftOperator();
        } else if (otherCompElem.getFirstArithmeticalOperator() != null) {
            return otherCompElem.getFirstArithmeticalOperator();
        } else if (otherCompElem.getSecondArithmeticalOperator() != null) {
            return otherCompElem.getSecondArithmeticalOperator();
        } else {
            return null;
        }
    }

    public static Object getLeftVar(ClauseWithVars clauseWithVars) {
        if (clauseWithVars.getFirstThis() != null) {
            return clauseWithVars.getFirstThis();
        } else if (clauseWithVars.getSecondThis() != null) {
            return clauseWithVars.getSecondThis();
        } else if (clauseWithVars.getCanEvaluateToPrimitive() != null) {
            return clauseWithVars.getCanEvaluateToPrimitive();
        } else {
            return null;
        }
    }

    /**
     * Note: This method returns the right element of a ClauseWithVars only if the instanceof operator
     * is not used (in that case, it returns null).
     */
    public static Object getRightElementOfComparison(ClauseWithVars clauseWithVars) {
        if (clauseWithVars.getVarOrClassWithModifiers() != null) {
            return clauseWithVars.getVarOrClassWithModifiers();
        } else if (clauseWithVars.getThirdThis() != null) {
            return clauseWithVars.getThirdThis();
        } else if (clauseWithVars.getFirstEqIneqComparisonElement() != null) {
            return clauseWithVars.getFirstEqIneqComparisonElement();
        } else if (clauseWithVars.getFirstOtherComparisonElement() != null) {
            return clauseWithVars.getFirstOtherComparisonElement();
        } else if (clauseWithVars.getSecondEqIneqComparisonElement() != null) {
            return clauseWithVars.getSecondEqIneqComparisonElement();
        } else if (clauseWithVars.getSecondOtherComparisonElement() != null) {
            return clauseWithVars.getSecondOtherComparisonElement();
        } else if (clauseWithVars.getSecondOtherComparisonElement() != null) {
            return clauseWithVars.getSecondOtherComparisonElement();
        } else if (clauseWithVars.getThirdOtherComparisonElement() != null) {
            return clauseWithVars.getThirdOtherComparisonElement();
        } else {
            return null;
        }
    }

    public static EObject removeLastVarOrClassModifier(EObject elementWithModifiers) {
        EObject elementClone = EcoreUtil.copy(elementWithModifiers);
        EList<VarOrClassModifier> modifiers = getVarOrClassModifiers(elementClone);
        modifiers.remove(modifiers.size() - 1);
        return elementClone;
    }

    /**
     * @param elementWithModifiers must be an element with modifiers, i.e., VarOrClassWithModifiers,
     *                             IsolableVarOrClassWithModifiers, or ThisWithMandatoryModifiers
     * @return null if the element has no method modifiers
     */
    public static MethodCall findLastMethodCall(EObject elementWithModifiers) {
        EList<VarOrClassModifier> modifiers = getVarOrClassModifiers(elementWithModifiers);
        List<MethodCallWithPeriod> methodModifiers = modifiers
                .stream()
                .map(VarOrClassModifier::getMethodCallWithPeriod)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        if (methodModifiers.isEmpty()) {
            return null;
        }
        return methodModifiers.get(methodModifiers.size() - 1).getMethodCall();
    }

    /**
     * Note: if the partial method call ends with a comma (e.g., "method(a,"), then the comma counts
     * as an argument (since one must necessarily follow), i.e., this method would return 2.
     */
    public static int getNArgumentsSoFar(MethodCall methodCall) {
        MethodContent methodContent = methodCall.getMethodContent();
        if (methodContent == null || methodContent.getMethodArgument() == null) {
            return 0;
        } else if (methodContent.getMethodContentContinuations() == null || methodContent.getMethodContentContinuations().isEmpty()) {
            return 1;
        } else {
            return methodContent.getMethodContentContinuations().size() + 1;
        }
    }

    /**
     * Note: if the partial method call ends with a comma (e.g., "method(a,"), then the comma counts
     * as an argument (since one must necessarily follow), i.e., this method would return an empty MethodArgument.
     */
    public static MethodArgument getLastArgument(MethodCall methodCall) {
        MethodContent methodContent = methodCall.getMethodContent();
        if (methodContent == null || methodContent.getMethodArgument() == null) {
            return null;
        } else if (methodContent.getMethodContentContinuations() == null || methodContent.getMethodContentContinuations().isEmpty()) {
            return methodContent.getMethodArgument();
        } else {
            return methodContent.getMethodContentContinuations().get(methodContent.getMethodContentContinuations().size() - 1).getMethodArgument();
        }
    }

    public static String removeNonVariableTokens(String oracle) {
        // Define the path to the json file with the list of non-variable tokens
        String jsonFilePath = FileUtils.getAbsolutePathToFile(Path.REPOS.getValue(), FileName.NON_VARIABLE_TOKENS, FileFormat.JSON);
        // Instantiate an object mapper to read the json file
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Read the list of non-variable tokens
            List<String> nonVariableTokenList = mapper.readValue(
                    new File(jsonFilePath),
                    new TypeReference<List<String>>(){}
            );
            // Iterate over the non-variable tokens
            for (String token : nonVariableTokenList) {
                // Replace the non-variable tokens with blank spaces
                oracle = oracle.replaceAll(token, " ");
            }
            return oracle;
        } catch (IOException e) {
            // Manage exception if the file is not found
            System.err.println("Unexpected error in processing the JSON file of the non variable tokens.");
            e.printStackTrace();
        }
        return oracle;
    }

    public static String removeParenthesisContentFromOracle(String oracle) {
        return oracle.replaceAll("(?:\\(|\\[).*?(?:\\)|\\])", "");
    }

    public static List<String> splitOracleInGroups(String oracle) {
        List<String> oracleGroups = new ArrayList<>();
        Pattern pattern = Pattern.compile("(?:\\(|\\[)(.*?)(?:\\)|\\])");
        Matcher matcher = pattern.matcher(oracle);
        boolean found = false;

        while (matcher.find()) {
            found = true;
            String contentWithinParenthesis = matcher.group(1);
            List<String> oracleSubGroups = splitOracleInGroups(contentWithinParenthesis);
            oracleGroups.addAll(splitOraclesRemovingSpaces(oracleSubGroups));
        }
        if (found) {
            String oracleWithoutParenthesis = removeParenthesisContentFromOracle(oracle);
            oracleGroups.addAll(splitOracleRemovingSpaces(oracleWithoutParenthesis));
            return oracleGroups;
        }
        // Return the whole oracle
        oracleGroups.add(oracle);
        return oracleGroups;
    }

    public static List<String> splitOracleByDot(String oracle) {
        String[] subgroups = oracle.split(".");
        return Arrays.asList(subgroups);
    }

    public static List<String> splitOraclesByDot(List<String> oracleList) {
        List<String> oracleGroups = new ArrayList<>();
        for (String oracle: oracleList) {
            oracleGroups.addAll(splitOracleByDot(oracle));
        }
        return oracleGroups;
    }

    public static List<String> splitOracleRemovingSpaces(String oracle) {
        String[] subgroups = oracle.split("\\s+");
        for (int i = 0; i < subgroups.length; i++) {
            subgroups[i] = subgroups[i].trim();
        }
        return Arrays.asList(subgroups);
    }

    public static List<String> splitOraclesRemovingSpaces(List<String> oraclesSubGroups) {
        List<String> oracleSubGroups = new ArrayList<>();
        for (String subOracle : oraclesSubGroups) {
            String[] subgroups = subOracle.split("\\s+");
            for (int i = 0; i < subgroups.length; i++) {
                subgroups[i] = subgroups[i].trim();
            }
            oraclesSubGroups.addAll(Arrays.asList(subgroups));
        }
        return oraclesSubGroups;
    }
}
