package star.tratto.identifiers;

public enum OracleType {
    PRE("PRE"),
    NORMAL_POST("NORMAL_POST"),
    EXCEPT_POST("EXCEPT_POST");


    private final String type;

    private OracleType(String type) {
        this.type = type;
    }

    public String getValue() {
        return this.type;
    }
}
