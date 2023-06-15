package star.tratto.input;

import com.github.javaparser.ast.body.CallableDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.javadoc.Javadoc;
import com.github.javaparser.javadoc.JavadocBlockTag;
import star.tratto.dataset.oracles.OracleDatapoint;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This class is responsible for analyzing the source code of a given Java class and generating
 * as many OracleDatapoints as possible from it. In particular, for each {@code @param},
 * {@code @return} and {@code @throws} or {@code @exception} Javadoc tag, a precondition,
 * postcondition or exceptional behavior will be generated, respectively. For those methods
 * without any of these tags (or without Javadoc at all), at least one oracle of each type will
 * be generated.
 *
 * TODO: Support option to provide project-specific columns of OracleDatapoints from a static file,
 *  without having to process the whole project each time that oracles are generated for a class.
 */
public class ClassAnalyzer {

    private static ClassAnalyzer instance;
    private String projectPath;
    private String classPath;
    private OracleDatapoint baseOracleDatapoint;

    private ClassAnalyzer() {
        // TODO: Initialize baseOracleDatapoint
    }

    public static ClassAnalyzer getInstance() {
        if (instance == null) {
            instance = new ClassAnalyzer();
        }
        return instance;
    }

    public String getProjectPath() {
        return projectPath;
    }

    public void setProjectPath(String projectPath) {
        if (projectPath != null && !projectPath.equals(this.projectPath)) {
            this.projectPath = projectPath;
            updateBaseOracleDatapointWithNewProject();
        }
    }

    public String getClassPath() {
        return classPath;
    }

    public void setClassPath(String classPath) {
        if (classPath != null && !classPath.equals(this.classPath)) {
            this.classPath = classPath;
            updateBaseOracleDatapointWithNewClass();
        }
    }

    private void updateBaseOracleDatapointWithNewProject() {
        // TODO
    }

    private void updateBaseOracleDatapointWithNewClass() {
        // TODO
    }

    public List<OracleDatapoint> getOracleDatapointsFromClass(TypeDeclaration<?> clazz) {
        List<OracleDatapoint> oracleDatapoints = new ArrayList<>();
        List<CallableDeclaration<?>> methodsAndConstructors = new ArrayList<>();

        methodsAndConstructors.addAll(clazz.getMethods());
        methodsAndConstructors.addAll(clazz.getConstructors());

        for (CallableDeclaration<?> methodOrConstructor : methodsAndConstructors) {
            oracleDatapoints.addAll(getOracleDatapointsFromMethod(methodOrConstructor));
        }

        return oracleDatapoints;
    }

    public List<OracleDatapoint> getOracleDatapointsFromMethod(CallableDeclaration<?> methodOrConstructor) {
        List<OracleDatapoint> oracleDatapoints = new ArrayList<>();

        Optional<Javadoc> optionalJavadoc = methodOrConstructor.getJavadoc();
        if (optionalJavadoc.isPresent()) {
            return getOracleDatapointsFromJavadoc(optionalJavadoc.get());
        }

        // If no Javadoc is present, we generate one OracleDatapoint for each type, with empty javadocTag and methodJavadoc
        // TODO: Generate the three OracleDatapoints

        return oracleDatapoints;
    }

    public List<OracleDatapoint> getOracleDatapointsFromJavadoc(Javadoc javadoc) {
        List<OracleDatapoint> oracleDatapoints = new ArrayList<>();

        List<JavadocBlockTag> javadocTags = javadoc.getBlockTags();
        for (JavadocBlockTag javadocTag : javadocTags) {
            if (javadocTag.getType().equals(JavadocBlockTag.Type.PARAM)) {
                // TODO: Generate precondition
            } else if (javadocTag.getType().equals(JavadocBlockTag.Type.RETURN)) {
                // TODO: Generate postcondition
            } else if (javadocTag.getType().equals(JavadocBlockTag.Type.THROWS) || javadocTag.getType().equals(JavadocBlockTag.Type.EXCEPTION)) {
                // TODO: Generate exceptional behavior
            }
        }

        return oracleDatapoints;
    }
}
