package star.tratto.identifiers;

/**
 * An enum to manage and compare various types of files used in I/O.
 */
public enum FileFormat {
    // types of extensions.
    JAVA(".java"),
    JSON(".json"),
    TXT(".txt"),
    CSV(".csv");

    private final String extension;

    FileFormat(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return this.extension;
    }
}
