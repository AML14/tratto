package star.tratto.data.json.parsers;

import star.tratto.data.oracles.json.parsers.JDoctorConditionParser;
import star.tratto.data.oracles.json.parsers.ProjectParser;
import star.tratto.identifiers.file.FileName;
import star.tratto.identifiers.file.FileFormat;
import star.tratto.identifiers.path.Path;
import star.tratto.data.oracles.JDoctorCondition;
import star.tratto.data.oracles.Project;
import star.tratto.util.FileUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JDoctorConditionParserTest {
    @Test
    public void parseJDocConditionsTest() {
        // get actual conditions from
        List<Project> projects = ProjectParser.initialize(FileUtils.getAbsolutePathToFile(
                Path.REPOS.getValue(),
                FileName.INPUT_PROJECTS,
                FileFormat.JSON
        ));
        JDoctorConditionParser parser = new JDoctorConditionParser();
        Project gsCoreProject = projects.get(1);
        List<JDoctorCondition> actual = parser.parseJDoctorConditions(gsCoreProject);
        // test that the correct number of conditions is acquired.
        assertEquals(36, actual.size());
        // test subset of conditions.
        for (JDoctorCondition condition : actual) {
            if (condition.getOperation().getClassName().equals("org.graphstream.graph.implementations.AbstractElement")) {
                assertEquals("oldValue==null", condition.getPreCondition().get(0).getGuard().getCondition());
            } else if (condition.getOperation().getClassName().equals("org.graphstream.graph.implementations.AdjacencyListGraph")) {
                List<Integer> expected = new ArrayList<>(List.of(3, 5));
                assertTrue(expected.contains(condition.getIdentifiers().getParameters().size()));
            }
        }
    }
}
