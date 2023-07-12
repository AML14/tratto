package data.collection.enums;

import java.nio.file.Paths;

public enum IOPath {
    JAVA_PARSER(Paths.get("src", "main", "java", "data", "collection", "javaparser").toString()),
    OUTPUT(Paths.get("target", "output").toString()),
    RESOURCES(Paths.get("src", "main", "resources").toString()),
    REPOS(Paths.get("src", "main", "repos").toString());

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
