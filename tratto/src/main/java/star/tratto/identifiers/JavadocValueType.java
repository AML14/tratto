package star.tratto.identifiers;

public enum JavadocValueType {
    INTEGER("int"),
    DOUBLE("double"),
    STRING("String");

    private final String type;

    JavadocValueType(String type) {
        this.type = type;
    }

    public String getValue() {
        return this.type;
    }
}
