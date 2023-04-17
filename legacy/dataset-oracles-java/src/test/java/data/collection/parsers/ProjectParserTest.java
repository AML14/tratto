package data.collection.parsers;

import data.collection.enums.FileFormat;
import data.collection.enums.FileName;
import data.collection.enums.Path;
import data.collection.models.Project;
import data.collection.utils.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectParserTest {
    @Test
    public void testInitialize() {
        // parse all projects from provided path.
        List<Project> actual = ProjectParser.initialize(FileUtils.getAbsolutePathToFile(
                Path.REPOS.getValue(),
                FileName.INPUT_PROJECTS,
                FileFormat.JSON
        ));
        // get expected output of projects.
        List<Project> expected = new ArrayList<>();
        expected.add(new Project(
                "gs-core-1.3",
                new File("src/main/resources/gs-core-1.3").getAbsolutePath(),
                new File("src/main/resources/gs-core-1.3/jar").getAbsolutePath(),
                new File("src/main/resources/gs-core-1.3/conditions").getAbsolutePath(),
                new File("src/main/resources/gs-core-1.3/raw/src").getAbsolutePath()
        ));
        expected.add(new Project(
                "commons-collections4-4.1",
                new File("src/main/resources/commons-collections4-4.1").getAbsolutePath(),
                new File("src/main/resources/commons-collections4-4.1/jar").getAbsolutePath(),
                new File("src/main/resources/commons-collections4-4.1/conditions").getAbsolutePath(),
                new File("src/main/resources/commons-collections4-4.1/raw/src/main/java").getAbsolutePath()
        ));
        expected.add(new Project(
                "guava-19.0",
                new File("src/main/resources/guava-19.0").getAbsolutePath(),
                new File("src/main/resources/guava-19.0/jar").getAbsolutePath(),
                new File("src/main/resources/guava-19.0/conditions").getAbsolutePath(),
                new File("src/main/resources/guava-19.0/raw/guava/src").getAbsolutePath()
        ));
        expected.add(new Project(
                "plume-lib-1.1.0",
                new File("src/main/resources/plume-lib-1.1.0").getAbsolutePath(),
                new File("src/main/resources/plume-lib-1.1.0/jar").getAbsolutePath(),
                new File("src/main/resources/plume-lib-1.1.0/conditions").getAbsolutePath(),
                new File("src/main/resources/plume-lib-1.1.0/raw/java/src").getAbsolutePath()
        ));
        expected.add(new Project(
                "commons-math3-3.6.1",
                new File("src/main/resources/commons-math3-3.6.1").getAbsolutePath(),
                new File("src/main/resources/commons-math3-3.6.1/jar").getAbsolutePath(),
                new File("src/main/resources/commons-math3-3.6.1/conditions").getAbsolutePath(),
                new File("src/main/resources/commons-math3-3.6.1/raw/src/main/java").getAbsolutePath()
        ));
        expected.add(new Project(
                "jgrapht-core-0.9.2",
                new File("src/main/resources/jgrapht-core-0.9.2").getAbsolutePath(),
                new File("src/main/resources/jgrapht-core-0.9.2/jar").getAbsolutePath(),
                new File("src/main/resources/jgrapht-core-0.9.2/conditions").getAbsolutePath(),
                new File("src/main/resources/jgrapht-core-0.9.2/raw").getAbsolutePath()
        ));
        // check equality.
        assertEquals(expected, actual);
    }
}
