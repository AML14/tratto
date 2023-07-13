package data.collection.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Injector;
import data.collection.enums.FileFormat;
import data.collection.enums.FileName;
import data.collection.enums.Path;
import data.collection.models.Project;
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
import star.tratto.oraclegrammar.TrattoGrammarStandaloneSetup;
import star.tratto.oraclegrammar.trattoGrammar.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class OracleUtils {
    private static OracleUtils parser = getInstance();
    private final XtextResourceSet xtextResourceSet;
    private final Resource resource; // Provides useful methods for handling the oracle expressed in TrattoGrammar
    private static final String DUMMY_URI = "dummy:/dummy.tr";

    // Constants for handling conflictive partial oracles:
    private static final String BIT_CONFLICTIVE_TOKENS_REGEX = ".*(\\||\\^|&|>>>|>>|<<)$";
    private static final String THIS_ERROR_MESSAGE_REGEX = "no viable alternative at input '(<EOF>|this)'";
    private static final String PERIOD_ERROR_MESSAGE_REGEX = "(no viable alternative at input '<EOF>'|mismatched input '<EOF>' expecting 'stream')";
    private static final String AUX_VAR = "auxVarForConflictivePartialOracle";

    private static final Logger logger = LoggerFactory.getLogger(OracleUtils.class);

    private OracleUtils() {
        Injector injector = new TrattoGrammarStandaloneSetup().createInjectorAndDoEMFRegistration();
        xtextResourceSet = injector.getInstance(XtextResourceSet.class);
        resource = xtextResourceSet.createResource(URI.createURI(DUMMY_URI));
    }

    public static OracleUtils getInstance() {
        if (parser == null) {
            parser = new OracleUtils();
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
        String compactOracle = StringUtils.compactExpression(partialOracle);
        String auxOracleSuffix = "";

        if (compactOracle.matches(BIT_CONFLICTIVE_TOKENS_REGEX)) {
            auxOracleSuffix = AUX_VAR; // If ends with bit shift/logical operator, always add aux var to handle
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
        List<CanEvaluateToPrimitive> elementsPrecedingNonEqIneqOp = getAllPartialOracleContents(oracle)
                .stream()
                .filter(eo -> eo instanceof ClauseWithVars && (
                        ((ClauseWithVars) eo).getNonEqIneqOperator() != null)
                ).map(eo -> ((ClauseWithVars) eo).getCanEvaluateToPrimitive())
                .collect(Collectors.toList());
        if (elementsPrecedingNonEqIneqOp.isEmpty()) {
            return null;
        }
        return elementsPrecedingNonEqIneqOp.get(elementsPrecedingNonEqIneqOp.size() - 1);
    }

    /**
     * This method is necessary to populate the last two columns of the oracles dataset, since it allows to retrieve
     * all oracle-specific variables that are not "this", "methodResultID" or method arguments. In other words, it
     * retrieves all sub-elements containing one or more method and/or attribute. For example, given the oracle
     * <code>SomeClass.someMethod()==1 && a.b.c(d.e.f, g.h, i).equals(this.j())</code>, this method will return the following
     * sub-elements:
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
            throw new IllegalArgumentException("Unexpected element type: " + eObject.getClass().getName());
        }
    }

    public static String removeNonVariableTokens(String oracle) {
        // Define the path to the json file with the list of non-variable tokens
        String jsonFilePath = FileUtils.getAbsolutePath(Path.REPOS.getValue(), FileName.NON_VARIABLE_TOKENS, FileFormat.JSON);
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
        String oracleWithoutParenthesis = oracle.replaceAll("(?:\\(|\\[).*?(?:\\)|\\])", "");
        return oracleWithoutParenthesis;
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
