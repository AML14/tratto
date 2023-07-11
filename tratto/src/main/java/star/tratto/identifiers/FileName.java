package star.tratto.identifiers;

public enum FileName {
    ARRAY_METHODS("arrayMethods"),
    IGNORE_FILE("ignoreFile"),
    INPUT_PROJECTS("inputProjects"),
    NON_VARIABLE_TOKENS("nonVariableTokens"),
    TOKENS_GENERAL_VALUES("tokensGeneralValues"),
    TOKENS_GRAMMAR("tokensGrammar"),
    SUPERCLASSES_NOT_FOUND("superclasses_not_found"),
    ORACLE_DATAPOINTS("oracle_datapoints");

    private final String name;

    FileName(String name) {
        this.name = name;
    }

    public String getValue() {
        return this.name;
    }
}