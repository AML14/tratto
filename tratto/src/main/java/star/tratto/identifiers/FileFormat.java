package star.tratto.identifiers;

public enum FileFormat {
    JAVA(".java"),
    JSON(".json"),
    TXT(".txt"),
    CSV(".csv");

    private final String value;

    private FileFormat(String value) {
        this.value = value;
    }

    public String getValue() { return this.value; }
}
