package data.collection.parsers;

import data.collection.enums.FileFormat;
import data.collection.enums.FileName;
import data.collection.enums.Path;
import data.collection.models.JDocCondition;
import data.collection.models.Project;
import data.collection.utils.FileUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JDocConditionParserTest {
    @Test
    public void testParseJDocConditions() {
        // get actual conditions from
        List<Project> projects = ProjectParser.initialize(FileUtils.getAbsolutePath(
                Path.REPOS.getValue(),
                FileName.INPUT_PROJECTS,
                FileFormat.JSON
        ));
        JDocConditionParser parser = new JDocConditionParser();
        Project gsCoreProject = projects.get(0);
        List<JDocCondition> actual = new ArrayList<>(List.of(parser.parseJDocConditions(gsCoreProject)));
        // test that the correct number of conditions is acquired.
        assertEquals(36, actual.size());
        // test subset of conditions.
        for (JDocCondition condition: actual) {
            if (condition.operation().classname().equals("org.graphstream.graph.implementations.AbstractElement")) {
                assertEquals("oldValue==null", condition.preConditions().get(0).guard().condition());
            } else if (condition.operation().classname().equals("org.graphstream.graph.implementations.AdjacencyListGraph")) {
                List<Integer> expected = new ArrayList<>(List.of(3, 5));
                assertTrue(expected.contains(condition.identifiers().parameters().size()));
            }
        }
    }
}
