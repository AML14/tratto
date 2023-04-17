package star.tratto.enums;

public enum JDocConditionMethodsVariables {
    RECEIVER_OBJECT_ID("receiverObjectID"),
    METHOD_RESULT_ID("methodResultID");

    private final String value;

    private JDocConditionMethodsVariables(String value) {
        this.value = value;
    }

    public String getValue() { return this.value; }
}
