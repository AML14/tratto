package star.tratto.oraclegrammar.custom;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains the functionality related to splitting a whole oracle
 * into separate tokens. For example, given the following oracle:
 * <pre>
 * (iterators==null) == false || iterators.length==0;
 * </pre>
 * We should be able to obtain the following list of tokens:
 * <pre>
 * [(], [iterators], [==], [null], [)], [==], [false],
 * [||], [iterators], [.], [length], [==], [0], [;]
 * </pre>
 */
public class Splitter {

    /**
     * Splits a fragment of the TrattoGrammar DSL (e.g., already parsed by the {@link Parser}),
     * which may be a complex object (EObject), a list of objects (List) or a single token
     * (String) into a list of all the tokens contained in it, in order as they appear in the
     * fragment, each token as a String. This method is primarily used to split an Oracle
     * object into a list of tokens, but it can also be used to split a partial TrattoGrammar
     * expression into the list of tokens that compose it.
     * @param dslFragment The Object to split into tokens. Must be an instance of EObject,
     *                    String, or List. If it's a List, it may contain EObjects, Strings,
     *                    or other Lists.
     */
    public static List<String> split(Object dslFragment) {
        List<String> tokens = new ArrayList<>();

        if (dslFragment instanceof EObject) {
            EObject eObject = (EObject) dslFragment;
            // Get all features (i.e., attributes) of the current object:
            EList<EStructuralFeature> eObjectFeatures = eObject.eClass().getEAllStructuralFeatures();
            for (EStructuralFeature feature : eObjectFeatures) { // Process each feature
                Object objectFeature = eObject.eGet(feature);
                tokens.addAll(split(objectFeature));
            }
        } else if (dslFragment instanceof List) {
            for (Object listObjectElement : (List<?>) dslFragment) { // Process each element of the list
                tokens.addAll(split(listObjectElement));
            }
        } else if (dslFragment instanceof String) { // Simply add string to the list of tokens
            tokens.add((String) dslFragment);
        } else if (dslFragment != null) {
            throw new IllegalArgumentException("Object must be an instance of EObject, String, or List. " +
                    "Type of the object: " + dslFragment.getClass() + ". Object: " + dslFragment);
        }

        return tokens;
    }
}
