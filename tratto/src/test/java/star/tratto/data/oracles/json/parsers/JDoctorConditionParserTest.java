package star.tratto.data.oracles.json.parsers;

import org.junit.jupiter.api.Test;
import star.tratto.data.TrattoPath;
import star.tratto.data.oracles.Project;
import star.tratto.data.oracles.JDoctorConditionParser;
import star.tratto.data.oracles.ProjectParser;
import star.tratto.data.records.JDoctorCondition;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JDoctorConditionParserTest {
    @Test
    public void parseJDocConditionsTest() {
        // get actual conditions from
        List<Project> projects = ProjectParser.initialize(TrattoPath.INPUT_PROJECTS.getPath());
        JDoctorConditionParser parser = new JDoctorConditionParser();
        Project gsCoreProject = projects.get(1);
        List<JDoctorCondition> actual = parser.parseJDoctorConditions(gsCoreProject);
        // test that the correct number of conditions is acquired.
        assertEquals(36, actual.size());
        // test subset of conditions.
        for (JDoctorCondition condition : actual) {
            if (condition.operation().className().equals("org.graphstream.graph.implementations.AbstractElement")) {
                assertEquals("oldValue==null", condition.preConditions().get(0).guard().condition());
            } else if (condition.operation().className().equals("org.graphstream.graph.implementations.AdjacencyListGraph")) {
                List<Integer> expected = new ArrayList<>(List.of(3, 5));
                assertTrue(expected.contains(condition.identifiers().parameters().size()));
            }
        }
    }
}
