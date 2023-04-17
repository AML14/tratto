package data.collection.enums;

public enum OracleDPAttrKey {
    ID("id"),
    ORACLE("oracle"),
    ORACLE_TYPE("oracleType"),
    PROJECT_NAME("projectName"),
    PACKAGE_NAME("packageName"),
    CLASS_NAME("className"),
    JAVADOC_TAG("javadocTag"),
    METHOD_JAVADOC("methodJavadoc"),
    METHOD_SOURCE_CODE("methodSourceCode"),
    CLASS_JAVADOC("classJavadoc"),
    CLASS_SOURCE_CODE("classSourceCode"),
    TOKENS_GENERAL_GRAMMAR("tokensGeneralGrammar"),
    TOKENS_GENERAL_VALUES_GLOBAL_DICTIONARY("tokensGeneralValuesGlobalDictionary"),
    TOKENS_PROJECT_CLASSES("tokensProjectClasses"),
    TOKENS_PROJECT_CLASSES_NON_PRIVATE_STATIC_NON_VOID_METHODS("tokensProjectClassesNonPrivateStaticNonVoidMethods"),
    TOKENS_PROJECT_CLASSES_NON_PRIVATE_STATIC_ATTRIBUTES("tokensProjectClassesNonPrivateStaticAttributes"),
    TOKENS_METHOD_JAVADOC_VALUES("tokensMethodJavadocValues"),
    TOKENS_METHOD_ARGUMENTS("tokensMethodArguments"),
    TOKENS_METHOD_VARIABLES_NON_PRIVATE_NON_STATIC_NON_VOID_METHODS("tokensMethodVariablesNonPrivateNonStaticNonVoidMethods"),
    TOKENS_METHOD_VARIABLES_NON_PRIVATE_NON_STATIC_ATTRIBUTES("tokensMethodVariablesNonPrivateNonStaticAttributes"),
    TOKENS_ORACLE_VARIABLES_NON_PRIVATE_NON_STATIC_NON_VOID_METHODS("tokensOracleVariablesNonPrivateNonStaticNonVoidMethods"),
    TOKENS_ORACLE_VARIABLES_NON_PRIVATE_NON_STATIC_ATTRIBUTES("tokensOracleVariablesNonPrivateNonStaticAttributes");



    private final String attr;

    private OracleDPAttrKey(String name) {
        this.attr = name;
    }

    public String getValue() {
        return this.attr;
    }
}
