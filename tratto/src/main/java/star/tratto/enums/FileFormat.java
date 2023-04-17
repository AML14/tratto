package data.collection.enums;

public enum FileFormat {
    JAVA(".java"),
    JSON(".json"),
    TXT(".txt");

    private final String value;

    private FileFormat(String value) {
        this.value = value;
    }

    public String getValue() { return this.value; }
}
