package star.tratto.data.oracles;

import star.tratto.data.OracleDatapoint;

/**
 * The scope of a feature in an {@link OracleDatapoint}.
 */
public enum FeatureLevel {
    /**
     * The feature is equal across all datapoints. Includes features:
     * tokensGeneralGrammar, tokensGeneralValuesGlobalDictionary.
     */
    DEFAULT,
    /**
     * The feature is equal across all datapoints in the same project.
     * Includes features: tokensProjectClasses,
     * tokensProjectClassesNonPrivateStaticNonVoidMethods,
     * tokensProjectClassesNonPrivateStaticAttributes.
     */
    PROJECT,
    /**
     * The feature is equal across all datapoints in the same class. Includes
     * features: className, classSourceCode, classJavadoc, packageName.
     */
    CLASS,
    /**
     * The feature is equal across all datapoints in the same method. Includes
     * features: methodSourceCode, methodJavadoc, tokensMethodArguments,
     * tokensMethodVariablesNonPrivateNonStaticNonVoidMethods,
     * tokensMethodVariablesNonPrivateNonStaticAttributes.
     */
    METHOD,
    /**
     * The feature is specific to its oracle. Includes features:
     * tokensOracleVariablesNonPrivateNonStaticNonVoidMethods,
     * tokensOracleVariablesNonPrivateNonStaticAttributes.
     */
    ORACLE
}
