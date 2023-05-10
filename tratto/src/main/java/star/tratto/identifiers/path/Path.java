package star.tratto.identifiers.path;

import java.nio.file.Paths;

public enum Path {
    JAVA_PARSER(Paths.get("src", "main", "java", "data", "collection", "javaparser").toString()),
    OUTPUT(Paths.get("target", "output").toString()),
    RESOURCES(Paths.get("src", "main", "resources").toString()),
    REPOS(Paths.get("src", "main", "java", "star", "tratto", "dataset", "repos").toString());

    private final String path;

    Path(String path) {
        this.path = Paths.get(
                System.getProperty("user.dir"),
                path
        ).toString();
    }

    public String getValue() {
        return this.path;
    }
}