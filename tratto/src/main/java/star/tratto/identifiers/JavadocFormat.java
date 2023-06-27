package star.tratto.identifiers;

public enum JavadocFormat {
    CLASS_PREFIX("/**"),
    CLASS_SUFFIX("*/"),
    METHOD_PREFIX("\t/**"),
    METHOD_SUFFIX("*/");

    private final String value;

    JavadocFormat(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
