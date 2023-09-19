package star.tratto.data.oracles;

/**
 * The scope of a feature in an {@link star.tratto.data.OracleDatapoint}. The
 * levels are defined as:
 * <ul>
 *     <li>DEFAULT: tokensGeneralGrammar,
 *     tokensGeneralValuesGlobalDictionary</li>
 *     <li>PROJECT: tokensProjectClasses,
 *     tokensProjectClassesNonPrivateStaticNonVoidMethods,
 *     tokensProjectClassesNonPrivateStaticAttributes</li>
 *     <li>CLASS: className, classSourceCode, classJavadoc, packageName</li>
 *     <li>METHOD: methodSourceCode, methodJavadoc, tokensMethodArguments
 *     tokensMethodVariablesNonPrivateNonStaticNonVoidMethods,
 *     tokensMethodVariablesNonPrivateNonStaticAttributes</li>
 *     <li>ORACLE: tokensOracleVariablesNonPrivateNonStaticNonVoidMethods,
 *     tokensOracleVariablesNonPrivateNonStaticAttributes</li>
 * </ul>
 */
public enum FeatureLevel {
    /** The feature is equal across all datapoints. */
    DEFAULT,
    /** The feature is equal across all datapoints in the same project. */
    PROJECT,
    /** The feature is equal across all datapoints in the same class. */
    CLASS,
    /** The feature is equal across all datapoints in the same method. */
    METHOD,
    /** The feature is specific to its oracle (possibly not unique).  */
    ORACLE
}
