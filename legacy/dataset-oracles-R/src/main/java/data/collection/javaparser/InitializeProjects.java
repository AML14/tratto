package data.collection.javaparser;

import java.io.File;
import java.util.ArrayList;

/**
 * This class initialize all input projects (ISSTA'18 & jdk-11)
 */
public class InitializeProjects {
    public ArrayList<InputProject> inputProjects;

    public void InitializeProjects() {
        // resourcesDir is used to point out the directory where we keep input projects
        // dataDir is used to keep the output data files
        String resourcesDir = System.getProperty("user.dir") + "/src/main/resources/";
        File dataDir = new File(System.getProperty("user.dir") + "/data/javaparserOutput/");
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }

        inputProjects = new ArrayList<>();

        // we don't collect field-accesses, method-calls, and variable declaration expressions anymore
        inputProjects.add(new InputProject(
                "commons-collections4-4.1",
                new File(resourcesDir + "/ISSTA18_Projects/commons-collections4-4.1/"),
                new File(resourcesDir + "/ISSTA18_Projects/commons-collections4-4.1/src/main/"),
                new File(dataDir + ""),
                Boolean.FALSE, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE, Boolean.TRUE
        ));
        inputProjects.add(new InputProject(
                "commons-math3-3.6.1",
                new File(resourcesDir + "/ISSTA18_Projects/commons-math3-3.6.1"),
                new File(resourcesDir + "/ISSTA18_Projects/commons-math3-3.6.1/src/main/java/"),
                new File(dataDir + ""),
                Boolean.FALSE, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE, Boolean.TRUE
        ));
        inputProjects.add(new InputProject(
                "gs-core-1.3",
                new File(resourcesDir + "/ISSTA18_Projects/gs-core-1.3/"),
                new File(resourcesDir + "/ISSTA18_Projects/gs-core-1.3/src/"),
                new File(dataDir + ""),
                Boolean.FALSE, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE, Boolean.TRUE
        ));
        inputProjects.add(new InputProject(
                "guava-19.0",
                new File(resourcesDir + "/ISSTA18_Projects/guava-19.0/"),
                new File(resourcesDir + "/ISSTA18_Projects/guava-19.0/guava/src/"),
                new File(dataDir + ""),
                Boolean.FALSE, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE, Boolean.TRUE
        ));
        inputProjects.add(new InputProject(
                "jgrapht-core-0.9.2",
                new File(resourcesDir + "/ISSTA18_Projects/jgrapht-core-0.9.2"),
                new File(resourcesDir + "/ISSTA18_Projects/jgrapht-core-0.9.2/"),
                new File(dataDir + ""),
                Boolean.FALSE, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE, Boolean.TRUE
        ));
        inputProjects.add(new InputProject(
                "plume-lib-1.1.0",
                new File(resourcesDir + "/ISSTA18_Projects/plume-lib-1.1.0/"),
                new File(resourcesDir + "/ISSTA18_Projects/plume-lib-1.1.0/java/src/"),
                new File(dataDir + ""),
                Boolean.FALSE, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE, Boolean.TRUE
        ));
        inputProjects.add(new InputProject(
                "openjdk-jdk11",
                new File(resourcesDir + "/openjdk-jdk11/"),
                new File(resourcesDir + "/openjdk-jdk11/src/"),
                new File(dataDir + ""),
                Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.TRUE
        ));
    }
}
