package star.tratto.input;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.CallableDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.javadoc.Javadoc;
import com.github.javaparser.javadoc.JavadocBlockTag;
import com.github.javaparser.resolution.UnsolvedSymbolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import star.tratto.data.OracleDatapoint;
import star.tratto.data.OracleType;
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
    private final OracleDatapointBuilder oracleDPBuilder;

    private ClassAnalyzer() {
        this.projectPath = null;
        this.className = null;
        this.classSourceCode = null;
        this.classCu = null;
        this.classTd = null;
        oracleDPBuilder = new OracleDatapointBuilder();
        oracleDPBuilder.resetWithDefaults();
    }

    public static ClassAnalyzer getInstance() {
        if (instance == null) {
            instance = new ClassAnalyzer();
        }
        return instance;
    }

    /**
     * Updating the project path also means resetting the class features, since
     * the latter depend on the former.
     */
    public void setProjectPath(String projectPath) {
        if (projectPath != null && !projectPath.equals(this.projectPath)) {
            reset();
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
        oracleDPBuilder.reset("project", true);
    }

    private void updateOracleDPBuilderWithNewClassFeatures() {
        oracleDPBuilder.setClassName(className);
        oracleDPBuilder.setClassSourceCode(classSourceCode);
        oracleDPBuilder.setClassJavadoc(DatasetUtils.getClassJavadoc(classTd));
        oracleDPBuilder.setPackageName(DatasetUtils.getClassPackage(classCu));
        oracleDPBuilder.reset("class", true);
    }

    private void updateOracleDPBuilderWithNewMethodFeatures(CallableDeclaration<?> methodOrConstructor) {
        String methodJavadoc = DatasetUtils.getCallableJavadoc(methodOrConstructor);
        oracleDPBuilder.setMethodSourceCode(DatasetUtils.getCallableSourceCode(methodOrConstructor));
        oracleDPBuilder.setMethodJavadoc(methodJavadoc);
        oracleDPBuilder.setTokensMethodJavadocValues(DatasetUtils.getJavadocValues(methodJavadoc));
        oracleDPBuilder.setTokensMethodArguments(DatasetUtils.getTokensMethodArguments(classTd, methodOrConstructor));
        try {
            oracleDPBuilder.setTokensMethodVariablesNonPrivateNonStaticNonVoidMethods(DatasetUtils.getTokensMethodVariablesNonPrivateNonStaticNonVoidMethods(classTd, methodOrConstructor));
            oracleDPBuilder.setTokensMethodVariablesNonPrivateNonStaticAttributes(DatasetUtils.getTokensMethodVariablesNonPrivateNonStaticAttributes(classTd, methodOrConstructor));
        } catch (JPClassNotFoundException e) {
            logger.warn("Class {} not found while trying to get variable tokens from method {}", className, methodOrConstructor.getNameAsString());
        }
        oracleDPBuilder.reset("method", true);
    }

    /**
     * Generates oracle datapoints based on currently set project path and class
     * features.
     * @throws IllegalStateException if {@link #setProjectPath} or {@link #setClassFeatures}
     * have not been called before this method, i.e., if the project path or class features
     * are null.
     * @throws UnsolvedSymbolException if some symbol within the class source code
     * could not be solved.
     */
    public List<OracleDatapoint> getOracleDatapointsFromClass() {
        if (projectPath == null || className == null) {
            throw new IllegalStateException("Project path or class features are null. You need to call setProjectPath and setClassFeatures before this method.");
        }

        List<OracleDatapoint> oracleDatapoints = new ArrayList<>();
        List<CallableDeclaration<?>> methodsAndConstructors = new ArrayList<>();

        methodsAndConstructors.addAll(classTd.getMethods());
        methodsAndConstructors.addAll(classTd.getConstructors());

        for (CallableDeclaration<?> methodOrConstructor : methodsAndConstructors) {
            oracleDatapoints.addAll(getOracleDatapointsFromMethod(methodOrConstructor));
        }

        return oracleDatapoints;
    }

    /**
     * Generates oracle datapoints based on currently set project path and class
     * features, and passed method.
     * @throws IllegalStateException if {@link #setProjectPath} or {@link #setClassFeatures}
     * have not been called before this method, i.e., if the project path or class features
     * are null.
     */
    public List<OracleDatapoint> getOracleDatapointsFromMethod(CallableDeclaration<?> methodOrConstructor) {
        if (projectPath == null || className == null) {
            throw new IllegalStateException("Project path or class features are null. You need to call setProjectPath and setClassFeatures before this method.");
        }

        // At this point we can already update the OracleDatapointBuilder with the new method features
        updateOracleDPBuilderWithNewMethodFeatures(methodOrConstructor);

        List<OracleDatapoint> oracleDatapoints = new ArrayList<>();

        Optional<Javadoc> optionalJavadoc = methodOrConstructor.getJavadoc();
        if (optionalJavadoc.isPresent()) {
            return getOracleDatapointsFromJavadoc(optionalJavadoc.get());
        }

        // If no Javadoc is present, we generate one OracleDatapoint for each type, with empty javadocTag and methodJavadoc
        addOracleToList(oracleDatapoints, OracleType.PRE, "");
        addOracleToList(oracleDatapoints, OracleType.NORMAL_POST, "");
        addOracleToList(oracleDatapoints, OracleType.EXCEPT_POST, "");

        return oracleDatapoints;
    }

    /**
     * Generates oracle datapoints based on currently set project path and class
     * features, and passed Javadoc.
     * @throws IllegalStateException if {@link #setProjectPath} or {@link #setClassFeatures}
     * have not been called before this method, i.e., if the project path or class features
     * are null.
     */
    public List<OracleDatapoint> getOracleDatapointsFromJavadoc(Javadoc javadoc) {
        if (projectPath == null || className == null) {
            throw new IllegalStateException("Project path or class features are null. You need to call setProjectPath and setClassFeatures before this method.");
        }

        List<OracleDatapoint> oracleDatapoints = new ArrayList<>();
        boolean hasParamTag = false;
        boolean hasReturnTag = false;
        boolean hasThrowsOrExceptionTag = false;

        List<JavadocBlockTag> javadocTags = javadoc.getBlockTags();
        for (JavadocBlockTag javadocTag : javadocTags) {
            if (javadocTag.getType().equals(JavadocBlockTag.Type.PARAM)) {
                addOracleToList(oracleDatapoints, OracleType.PRE, javadocTag.toText());
                hasParamTag = true;
            } else if (javadocTag.getType().equals(JavadocBlockTag.Type.RETURN)) {
                addOracleToList(oracleDatapoints, OracleType.NORMAL_POST, javadocTag.toText());
                hasReturnTag = true;
            } else if (javadocTag.getType().equals(JavadocBlockTag.Type.THROWS) || javadocTag.getType().equals(JavadocBlockTag.Type.EXCEPTION)) {
                addOracleToList(oracleDatapoints, OracleType.EXCEPT_POST, javadocTag.toText());
                hasThrowsOrExceptionTag = true;
            }
        }

        // If there's some missing tag, generate OracleDatapoint without associated tag
        if (!hasParamTag) {
            addOracleToList(oracleDatapoints, OracleType.PRE, "");
        }
        if (!hasReturnTag) {
            addOracleToList(oracleDatapoints, OracleType.NORMAL_POST, "");
        }
        if (!hasThrowsOrExceptionTag) {
            addOracleToList(oracleDatapoints, OracleType.EXCEPT_POST, "");
        }

        return oracleDatapoints;
    }

    private void addOracleToList(List<OracleDatapoint> oracleDatapoints, OracleType oracleType, String javadocTag) {
        oracleDPBuilder.setOracleType(oracleType);
        oracleDPBuilder.setJavadocTag(javadocTag);
        oracleDatapoints.add(oracleDPBuilder.build("method", true));
    }

    /** For testing purposes */
    void setMethodFeatures(CallableDeclaration<?> methodOrConstructor) {
        updateOracleDPBuilderWithNewMethodFeatures(methodOrConstructor);
    }

    /** For testing purposes */
    void reset() {
        oracleDPBuilder.resetWithDefaults();
        projectPath = null;
        className = null;
        classSourceCode = null;
        classCu = null;
        classTd = null;
    }

    /** For testing purposes */
    OracleDatapointBuilder getOracleDPBuilder() {
        return oracleDPBuilder;
    }
}
