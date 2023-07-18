package star.tratto.identifiers;

import java.nio.file.Paths;

public enum IOPath {
    OUTPUT(Paths.get("target", "output").toString()),
    OUTPUT_DATASET(Paths.get("target", "output", "dataset").toString()),
    RESOURCES(Paths.get("src", "main", "resources", "projects-source").toString()),
    REPOS(Paths.get("src", "main", "java", "star", "tratto", "data", "repos").toString()),
    ORACLES_DATASET(Paths.get("src", "main", "resources", "oracles-dataset").toString());

    private final String path;

    IOPath(String path) {
        this.path = Paths.get(
                System.getProperty("user.dir"),
                path
        ).toString();
    }

    public String getValue() {
        return this.path;
    }
}
