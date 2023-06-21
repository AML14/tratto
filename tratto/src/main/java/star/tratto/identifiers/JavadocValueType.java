package star.tratto.identifiers;

public enum JavadocValueType {
    INTEGER("int"),
    REAL("double"),
    STRING("String");

    private final String type;

    private JavadocValueType(String type) {
        this.type = type;
    }

    public String getValue() {
        return this.type;
    }
}
