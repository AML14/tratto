package star.tratto.enums;

public enum JPCallableType {
    CONSTRUCTOR("CONSTRUCTOR"),
    METHOD("METHOD");

    private final String type;

    private JPCallableType(String type) {
        this.type = type;
    }

    public String getValue() {
        return this.type;
    }
}
