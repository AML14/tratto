package star.tratto.identifiers;

public enum FileName {
    ARRAY_METHODS("array_methods"),
    IGNORE_FILE("ignore_file"),
    INPUT_PROJECTS("input_projects"),
    NON_VARIABLE_TOKENS("non_variable_tokens"),
    TOKENS_GENERAL_VALUES("tokens_general_values"),
    TOKENS_GRAMMAR("tokens_grammar"),
    ORACLE_DATAPOINTS("oracle_datapoints");

    private final String name;

    FileName(String name) {
        this.name = name;
    }

    public String getValue() {
        return this.name;
    }
}