package star.tratto.data.oracles;

import org.junit.jupiter.api.Test;
import star.tratto.data.OracleDatapoint;
import star.tratto.data.records.MethodArgumentTokens;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OracleDatapointBuilderTest {
    @Test
    public void buildSimpleTest() {
        OracleDatapointBuilder builder = new OracleDatapointBuilder();
        builder.setClassName("myClass");
        OracleDatapoint oracleDP = builder.build();
        assertEquals("myClass", oracleDP.getClassName());
        assertNull(builder.copy().getClassName());
    }

    @Test
    public void buildLevelsTest() {
        OracleDatapointBuilder builder = new OracleDatapointBuilder();
        // test project-level build.
        builder.setProjectName("myProject");
        builder.setClassName("myClass");
        builder.build(FeatureLevel.PROJECT, false);
        assertEquals("myProject", builder.copy().getProjectName());
        assertNull(builder.copy().getClassName());
        // test class-level build.
        builder.setClassName("myClass");
        builder.setTokensMethodArguments(List.of(new MethodArgumentTokens("o1", "java.lang", "Object")));
        builder.build(FeatureLevel.CLASS, false);
        assertEquals("myProject", builder.copy().getProjectName());
        assertEquals("myClass", builder.copy().getClassName());
        assertNull(builder.copy().getTokensMethodArguments());
        // test method-level build.
        builder.setTokensMethodArguments(List.of(new MethodArgumentTokens("o1", "java.lang", "Object")));
        builder.setId(7);
        builder.build(FeatureLevel.METHOD, false);
        assertEquals("myProject", builder.copy().getProjectName());
        assertEquals("myClass", builder.copy().getClassName());
        assertEquals(List.of(new MethodArgumentTokens("o1", "java.lang", "Object")), builder.copy().getTokensMethodArguments());
        assertNull(builder.copy().getId());
    }

    @Test
    public void defaultsTest() {
        OracleDatapointBuilder builder = new OracleDatapointBuilder();
        builder.build();
        assertNull(builder.copy().getOracle());
        assertNull(builder.copy().getId());
        builder.resetWithDefaults();
        assertEquals("", builder.copy().getOracle());
        assertEquals(0, builder.copy().getId());
        builder.reset();
        assertNull(builder.copy().getOracle());
        assertNull(builder.copy().getId());
    }
}
