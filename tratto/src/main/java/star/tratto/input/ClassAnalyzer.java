package star.tratto.input;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.CallableDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.javadoc.Javadoc;
import com.github.javaparser.javadoc.JavadocBlockTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import star.tratto.data.OracleDatapoint;
import star.tratto.data.oracles.OracleDatapointBuilder;
import star.tratto.exceptions.JPClassNotFoundException;
import star.tratto.util.javaparser.DatasetUtils;

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
 */
public class ClassAnalyzer {

    private static final Logger logger = LoggerFactory.getLogger(ClassAnalyzer.class);

    private static ClassAnalyzer instance;
    private String projectPath;
    private String className;
    private String classSourceCode;
    private CompilationUnit classCu;
    private TypeDeclaration<?> classTd;
    private OracleDatapointBuilder oracleDPBuilder;

    private ClassAnalyzer() {
        oracleDPBuilder = new OracleDatapointBuilder();
        oracleDPBuilder.setId(0);
        oracleDPBuilder.setOracle("");
        oracleDPBuilder.setProjectName("");
    }

    public static ClassAnalyzer getInstance() {
        if (instance == null) {
            instance = new ClassAnalyzer();
        }
        return instance;
    }

    public void setProjectPath(String projectPath) {
        if (projectPath != null && !projectPath.equals(this.projectPath)) {
            this.projectPath = projectPath;
            updateOracleDPBuilderWithNewProject();
        }
    }

    public void setClassFeatures(String className, String classSourceCode, CompilationUnit classCu, TypeDeclaration<?> classTd) {
        if (classSourceCode != null && !classSourceCode.equals(this.classSourceCode)) {
            this.className = className;
            this.classSourceCode = classSourceCode;
            this.classCu = classCu;
            this.classTd = classTd;
            updateOracleDPBuilderWithNewClassFeatures();
        }
    }

    private void updateOracleDPBuilderWithNewProject() {
        oracleDPBuilder.setTokensProjectClasses(DatasetUtils.getProjectClassesTokens(projectPath));
        oracleDPBuilder.setTokensProjectClassesNonPrivateStaticNonVoidMethods(DatasetUtils.getProjectNonPrivateStaticNonVoidMethodsTokens(projectPath));
        oracleDPBuilder.setTokensProjectClassesNonPrivateStaticAttributes(DatasetUtils.getProjectNonPrivateStaticAttributesTokens(projectPath));
    }

    private void updateOracleDPBuilderWithNewClassFeatures() {
        oracleDPBuilder.setClassName(className);
        oracleDPBuilder.setClassSourceCode(classSourceCode);
        oracleDPBuilder.setClassJavadoc(DatasetUtils.getClassJavadoc(classTd));
        oracleDPBuilder.setPackageName(DatasetUtils.getClassPackage(classCu));
    }

    private void updateOracleDPBuilderWithNewMethodFeatures(CallableDeclaration<?> methodOrConstructor) {
        oracleDPBuilder.setMethodSourceCode(DatasetUtils.getCallableSourceCode(methodOrConstructor));
        oracleDPBuilder.setMethodJavadoc(DatasetUtils.getCallableJavadoc(methodOrConstructor));
        oracleDPBuilder.setTokensMethodArguments(DatasetUtils.getTokensMethodArguments(classTd, methodOrConstructor));
        try {
            oracleDPBuilder.setTokensMethodVariablesNonPrivateNonStaticNonVoidMethods(DatasetUtils.getTokensMethodVariablesNonPrivateNonStaticNonVoidMethods(classTd, methodOrConstructor));
            oracleDPBuilder.setTokensMethodVariablesNonPrivateNonStaticAttributes(DatasetUtils.getTokensMethodVariablesNonPrivateNonStaticAttributes(classTd, methodOrConstructor));
        } catch (JPClassNotFoundException e) {
            logger.warn("Class {} not found while trying to get variable tokens from method {}", className, methodOrConstructor.getNameAsString());
        }
    }

    /**
     * Generates oracle datapoints based on currently set class features. You need
     * to call {@link ClassAnalyzer#setClassFeatures} before this method.
     */
    public List<OracleDatapoint> getOracleDatapointsFromClass() {
        List<OracleDatapoint> oracleDatapoints = new ArrayList<>();
        List<CallableDeclaration<?>> methodsAndConstructors = new ArrayList<>();

        methodsAndConstructors.addAll(classTd.getMethods());
        methodsAndConstructors.addAll(classTd.getConstructors());

        for (CallableDeclaration<?> methodOrConstructor : methodsAndConstructors) {
            oracleDatapoints.addAll(getOracleDatapointsFromMethod(methodOrConstructor));
        }

        return oracleDatapoints;
    }

    public List<OracleDatapoint> getOracleDatapointsFromMethod(CallableDeclaration<?> methodOrConstructor) {
        List<OracleDatapoint> oracleDatapoints = new ArrayList<>();

        Optional<Javadoc> optionalJavadoc = methodOrConstructor.getJavadoc();
        if (optionalJavadoc.isPresent()) {
            return getOracleDatapointsFromJavadoc(methodOrConstructor);
        }

        // If no Javadoc is present, we generate one OracleDatapoint for each type, with empty javadocTag and methodJavadoc
        // TODO: Generate the three OracleDatapoints

        return oracleDatapoints;
    }

    /**
     * This method assumes that the passed method or constructor has a Javadoc.
     */
    public List<OracleDatapoint> getOracleDatapointsFromJavadoc(CallableDeclaration<?> methodOrConstructor) {
        List<OracleDatapoint> oracleDatapoints = new ArrayList<>();
        boolean hasParamTag = false;
        boolean hasReturnTag = false;
        boolean hasThrowsOrExceptionTag = false;

        List<JavadocBlockTag> javadocTags = methodOrConstructor.getJavadoc().get().getBlockTags(); // TODO: Test with multiple example Javadocs
        for (JavadocBlockTag javadocTag : javadocTags) {
            if (javadocTag.getType().equals(JavadocBlockTag.Type.PARAM)) {
                // TODO: Generate precondition
                hasParamTag = true;
            } else if (javadocTag.getType().equals(JavadocBlockTag.Type.RETURN)) {
                // TODO: Generate postcondition
                hasReturnTag = true;
            } else if (javadocTag.getType().equals(JavadocBlockTag.Type.THROWS) || javadocTag.getType().equals(JavadocBlockTag.Type.EXCEPTION)) {
                // TODO: Generate exceptional behavior
                hasThrowsOrExceptionTag = true;
            }
        }

        // If there's some missing tag, generate OracleDatapoint without associated tag
        if (!hasParamTag) {
            // TODO: Generate precondition
        }
        if (!hasReturnTag) {
            // TODO: Generate postcondition
        }
        if (!hasThrowsOrExceptionTag) {
            // TODO: Generate exceptional behavior
        }

        return oracleDatapoints;
    }
}
