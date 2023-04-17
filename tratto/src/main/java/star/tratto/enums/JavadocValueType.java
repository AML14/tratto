package data.collection.enums;

public enum JavadocValueType {
    INTEGER("int"),
    REAL("float"),
    STRING("String");

    private final String type;

    private JavadocValueType(String type) {
        this.type = type;
    }

    public String getValue() {
        return this.type;
    }
}
