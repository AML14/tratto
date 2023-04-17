package star.tratto.enums;

public enum TagType {
    PARAM("@param"),
    RETURN("@return"),
    THROWS("@throws"),
    EXCEPTION("@exception");

    private final String type;

    private TagType(String type) {
        this.type = type;
    }

    public String getValue() {
        return this.type;
    }
}

