package data.collection.enums;

public enum Javadoc {
    CLASS_PREFIX("/**"),
    CLASS_SUFFIX("*/"),
    METHOD_PREFIX("\t/**"),
    METHOD_SUFFIX("*/");

    private final String value;

    private Javadoc(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
