package star.tratto.data.oracles;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.CallableDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.resolution.UnsolvedSymbolException;
import org.javatuples.Pair;
import org.javatuples.Quartet;
import org.javatuples.Sextet;
import star.tratto.data.OracleDatapoint;
import star.tratto.data.OracleType;
import star.tratto.exceptions.JPClassNotFoundException;
import star.tratto.util.StringUtils;
import star.tratto.util.javaparser.DatasetUtils;
import star.tratto.util.javaparser.JDoctorUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ProjectOracleGenerator generates all oracle data points of a project using
 * a list of JDoctor conditions associated with its classes and methods.
 */
public class ProjectOracleGenerator {
    // generator fields.
    // unique identifier of the generated OracleDatapoint objects.
    private int idCounter;
    // the number of oracles generated between projects.
    private int checkpoint;
    // project-level fields.
    private Project project;
    private List<JDoctorCondition> jDoctorConditions;
    private List<Pair<String, String>> projectClassesTokens;
    private List<Quartet<String, String, String, String>> projectMethodsTokens;
    private List<Quartet<String, String, String, String>> projectAttributesTokens;
    private List<Sextet<String, TypeDeclaration<?>, CallableDeclaration<?>, OracleType, String, String>> projectTagsTokens;

    /**
     * Creates a new instance of ProjectOracleGenerator.
     */
    public ProjectOracleGenerator() {
        this.idCounter = 0;
        this.checkpoint = 0;
    }

    /**
     * Creates references to the Project and corresponding JDoctor conditions,
     * and gets project-level token information.
     *
     * @param project the project under analysis
     * @param jDoctorConditions a list of JDoctor conditions
     */
    public void loadProject(
            Project project,
            List<JDoctorCondition> jDoctorConditions
    ) {
        this.project = project;
        this.jDoctorConditions = jDoctorConditions;
        this.projectClassesTokens = DatasetUtils.getProjectClassesTokens(this.project.getSrcPath());
        this.projectMethodsTokens = DatasetUtils.getProjectNonPrivateStaticNonVoidMethodsTokens(this.project.getSrcPath());
        this.projectAttributesTokens = DatasetUtils.getProjectNonPrivateStaticAttributesTokens(this.project.getSrcPath());
        this.projectTagsTokens = DatasetUtils.getProjectTagsTokens(this.project.getSrcPath());
    }

    /**
     * Gets all OracleDatapoint objects for the loaded project from a list
     * of JDoctor conditions. Use "loadProject()" before "generate()".
     *
     * @return a list of data points {@link OracleDatapoint}
     */
    public List<OracleDatapoint> generate() {
        System.out.printf("Identified %s total JavaDoc tags.%n", this.projectTagsTokens.size());
        List<OracleDatapoint> oracleDPs = new ArrayList<>();
        // Generate an OracleDatapoint for each JDoctor condition.
        for (JDoctorCondition jDoctorCondition : this.jDoctorConditions) {
            JDoctorCondition.Operation operation = jDoctorCondition.getOperation();
            // Add all ThrowsCondition oracles to dataset.
            List<JDoctorCondition.ThrowsCondition> throwsConditions = jDoctorCondition.getThrowsConditions();
            for (JDoctorCondition.ThrowsCondition condition : throwsConditions) {
                OracleDatapoint nextDatapoint = getNextDatapoint(operation, condition);
                if (nextDatapoint != null) oracleDPs.add(nextDatapoint);
                removeProjectClassesTag(operation, OracleType.EXCEPT_POST, condition.getDescription());
            }
            // Add all PreCondition oracles to dataset.
            List<JDoctorCondition.PreCondition> preConditions = jDoctorCondition.getPreCondition();
            for (JDoctorCondition.PreCondition condition : preConditions) {
                OracleDatapoint nextDatapoint = getNextDatapoint(operation, condition);
                if (nextDatapoint != null) oracleDPs.add(nextDatapoint);
                removeProjectClassesTag(operation, OracleType.PRE, condition.getDescription());
            }
            // Add all PostCondition oracles to dataset.
            List<JDoctorCondition.PostCondition> postConditions = jDoctorCondition.getPostConditions();
            if (postConditions.size() > 0) {
                OracleDatapoint nextDatapoint = getNextDatapoint(operation, postConditions);
                if (nextDatapoint != null) oracleDPs.add(nextDatapoint);
                removeProjectClassesTag(operation, OracleType.NORMAL_POST, postConditions.get(0).getDescription());
            }
        }
        int numNonEmptyOracles = oracleDPs.size();
        // Generate an OracleDatapoint for each remaining JavaDoc tag.
        for (Sextet<String, TypeDeclaration<?>, CallableDeclaration<?>, OracleType, String, String> jpTag : this.projectTagsTokens) {
            OracleDatapoint nextDatapoint = getEmptyDatapoint(jpTag);
            if (nextDatapoint != null) oracleDPs.add(nextDatapoint);
        }
        int numEmptyOracles = oracleDPs.size() - numNonEmptyOracles;
        // log information.
        System.out.printf("Processed %s non-empty oracles.%n", numNonEmptyOracles);
        System.out.printf("Processed %s empty oracles.%n", numEmptyOracles);
        System.out.printf("Processed %s total conditions.%n", this.idCounter - this.checkpoint);
        this.checkpoint = this.idCounter;
        return oracleDPs;
    }

    /**
     * Finds the most similar source code tag to a target JDoctor tag. JDoctor
     * simplifies JavaDoc tags from source code, such that we rely on cosine
     * similarity to identify the source code tag that is most similar to the
     * given JDoctor tag. Only considers tags for a specific target method in
     * a specific target class.
     *
     * @param tagList list of source code tags
     * @param targetClass target class
     * @param targetCallable target method/constructor
     * @param targetOracleType target oracle type (e.g. pre-condition)
     * @param targetTag target JDoctor tag
     * @return source code tag with the greatest similarity to targetTag
     */g
    private Sextet<String, TypeDeclaration<?>, CallableDeclaration<?>, OracleType, String, String> findMaximumSimilarityTag(
            List<Sextet<String, TypeDeclaration<?>, CallableDeclaration<?>, OracleType, String, String>> tagList,
            TypeDeclaration<?> targetClass,
            CallableDeclaration<?> targetCallable,
            OracleType targetOracleType,
            String targetTag
    ) {
        // filter tags by TypeDeclaration, CallableDeclaration, OracleType, and Pattern matching.
        List<Sextet<String, TypeDeclaration<?>, CallableDeclaration<?>, OracleType, String, String>> filteredTags = tagList
                .stream()
                .filter(tagInfo -> {
                    if (!tagInfo.getValue1().equals(targetClass) ||
                        !tagInfo.getValue2().equals(targetCallable) ||
                        !tagInfo.getValue3().equals(targetOracleType)) return false;
                    boolean tagHasNoName = !targetTag.contains("@param") && !targetTag.contains("@throws") && tagInfo.getValue4().length() == 0;
                    Pattern pattern = Pattern.compile(String.format("@(param|return|throws)\\s+(.*\\.)*%s\\b", tagInfo.getValue4()));
                    Matcher matcher = pattern.matcher(targetTag);
                    return matcher.find() || tagHasNoName;
                })
                .toList();
        // find index of most semantically similar tag (cosine similarity).
        Sextet<String, TypeDeclaration<?>, CallableDeclaration<?>, OracleType, String, String> mostSimilarTag = null;
        double maxSimilarity = -1.0;
        for (Sextet<String, TypeDeclaration<?>, CallableDeclaration<?>, OracleType, String, String> tag : filteredTags) {
            String simpleTargetTag = targetTag.replaceAll(String.format("@(param|return|throws)\\s+(.*\\.)*%s\\b", tag.getValue4()),"").replaceAll("<[^>]*>|@code|@link|\\{|\\}|\\n|\\r|\\t", " ");
            String simpleActualTag = tag.getValue5().replaceAll("<[^>]*>|@code|@link|\\{|\\}|\\n|\\r|\\t", " ");
            double currentSimilarity = StringUtils.semanticSimilarity(simpleTargetTag, simpleActualTag);
            if (currentSimilarity > maxSimilarity) {
                maxSimilarity = currentSimilarity;
                mostSimilarTag = tag;
            }
        }
        assert mostSimilarTag != null;
        return mostSimilarTag;
    }

    /**
     * Removes the JavaDoc tag of a given JDoctor condition from the list
     * of total project JavaDoc tags.
     *
     * @param operation the operation of the JDoctor condition
     * @param oracleType the type of oracle (e.g. PRE/NORMAL_POST/EXCEPT_POST)
     * @param javaDocTag the (JDoctor simplified) JavaDoc tag to be removed
     */
    private void removeProjectClassesTag(
            JDoctorCondition.Operation operation,
            OracleType oracleType,
            String javaDocTag
    ) {
        String sourcePath = this.project.getSrcPath();
        String className = DatasetUtils.getOperationClassName(operation);
        String callableName = DatasetUtils.getOperationCallableName(operation);
        List<String> parameterTypes = JDoctorUtils.convertJDoctorConditionTypeNames2JavaParserTypeNames(operation.getParameterTypes());
        Optional<CompilationUnit> cuOptional = DatasetUtils.getOperationCompilationUnit(operation, sourcePath);
        if (cuOptional.isPresent()) {
            TypeDeclaration<?> jpClass = DatasetUtils.getTypeDeclaration(cuOptional.get(), className);
            assert jpClass != null;
            CallableDeclaration<?> jpCallable = DatasetUtils.getCallableDeclaration(jpClass, callableName, parameterTypes);
            assert jpCallable != null;
            // remove tag with maximum similarity.
            this.projectTagsTokens.remove(findMaximumSimilarityTag(
                    this.projectTagsTokens,
                    jpClass,
                    jpCallable,
                    oracleType,
                    javaDocTag
            ));
        }
    }

    /**
     * Generates an OracleDatapoint from a given source code tag. Used for
     * empty oracles (e.g. JavaDoc tags in a project without a corresponding
     * JDoctor condition).
     *
     * @param jpTag a sextet of tag information, including the declaring
     *              class string, type declaration, method, type of tag,
     *              tag name, and tag content
     * @return a fully populated OracleDatapoint. The "oracle" field is set
     * to a semicolon (";"). Returns null if an error occurs during
     * information collection.
     */
    private OracleDatapoint getEmptyDatapoint(
            Sextet<String, TypeDeclaration<?>, CallableDeclaration<?>, OracleType, String, String> jpTag
    ) {
        OracleDatapointBuilder builder = new OracleDatapointBuilder();
        // get basic information of jpTag.
        TypeDeclaration<?> jpClass = jpTag.getValue1();
        CallableDeclaration<?> jpCallable = jpTag.getValue2();
        OracleType oracleType = jpTag.getValue3();
        String tagName = jpTag.getValue4();
        String tagContent = jpTag.getValue5();
        // get artificial condition information.
        String tagType = switch (oracleType) {
            case PRE -> "@param ";
            case NORMAL_POST -> "";
            case EXCEPT_POST -> "@throws ";
        };
        builder.setOracleType(oracleType);
        builder.setJavadocTag(String.format("%s%s %s", tagType, tagName, tagContent));
        builder.setOracle(";");
        // set project-level information.
        builder.setProjectName(this.project.getProjectName());
        builder.setClassSourceCode(jpTag.getValue0());
        builder.setPackageName(jpClass.resolve().getPackageName());
        builder.setClassName(jpClass.getNameAsString());
        builder.setClassJavadoc(DatasetUtils.getClassJavadoc(jpClass));
        builder.setTokensProjectClasses(this.projectClassesTokens);
        builder.setTokensProjectClassesNonPrivateStaticNonVoidMethods(this.projectMethodsTokens);
        builder.setTokensProjectClassesNonPrivateStaticAttributes(this.projectAttributesTokens);
        builder.setMethodSourceCode(DatasetUtils.getCallableSourceCode(jpCallable));
        builder.setMethodJavadoc(DatasetUtils.getCallableJavadoc(jpCallable));
        builder.setTokensMethodJavadocValues(DatasetUtils.getJavadocValues(builder.copy().getMethodJavadoc()));
        builder.setTokensMethodArguments(DatasetUtils.getTokensMethodArguments(jpClass, jpCallable));
        // get method variable tokens (no oracle variable tokens).
        try {
            builder.setTokensMethodVariablesNonPrivateNonStaticNonVoidMethods(
                    DatasetUtils.getTokensMethodVariablesNonPrivateNonStaticNonVoidMethods(
                            jpClass,
                            jpCallable
                    )
            );
            builder.setTokensMethodVariablesNonPrivateNonStaticAttributes(
                    DatasetUtils.getTokensMethodVariablesNonPrivateNonStaticAttributes(
                            jpClass,
                            jpCallable
                    )
            );
        } catch (JPClassNotFoundException | UnsolvedSymbolException e) {
            e.printStackTrace();
            return null;
        }
        // return new datapoint.
        builder.setId(this.idCounter);
        this.idCounter++;
        return builder.build();
    }

    /**
     * Generates an OracleDatapoint from a given JDoctor condition and
     * operation.
     *
     * @param operation a JDoctor operation
     * @param condition a JDoctor condition (e.g. ThrowsCondition,
     *                  PreCondition, or a list of PostCondition's).
     * @return a fully populated OracleDatapoint. Returns null if error
     * occurs during information collection.
     */
    private OracleDatapoint getNextDatapoint(JDoctorCondition.Operation operation, Object condition) {
        OracleDatapointBuilder builder = new OracleDatapointBuilder();
        // get basic information of operation.
        String sourcePath = this.project.getSrcPath();
        String projectName = this.project.getProjectName();
        String packageName = DatasetUtils.getOperationPackageName(operation);
        String className = DatasetUtils.getOperationClassName(operation);
        String callableName = DatasetUtils.getOperationCallableName(operation);
        List<String> parameterTypes = JDoctorUtils.convertJDoctorConditionTypeNames2JavaParserTypeNames(operation.getParameterTypes());
        // get CompilationUnit of operation class.
        Optional<CompilationUnit> cuOptional = DatasetUtils.getOperationCompilationUnit(operation, sourcePath);
        if (cuOptional.isEmpty()) {
          return null;
        }
        CompilationUnit cu = cuOptional.get();
        String classSourceCode = DatasetUtils.getOperationClassSource(operation, sourcePath).get(); // Can assume is not empty.
        // get TypeDeclaration of class in CompilationUnit.
        TypeDeclaration<?> jpClass = DatasetUtils.getTypeDeclaration(cu, className);
        assert jpClass != null;
        // get CallableDeclaration of method in TypeDeclaration.
        CallableDeclaration<?> jpCallable = DatasetUtils.getCallableDeclaration(jpClass, callableName, parameterTypes);
        assert jpCallable != null;
        // set data point information.
        builder.setConditionInfo(condition);
        builder.setProjectName(projectName);
        builder.setClassSourceCode(classSourceCode);
        builder.setPackageName(packageName);
        builder.setClassName(className);
        builder.setClassJavadoc(DatasetUtils.getClassJavadoc(jpClass));
        builder.setTokensProjectClasses(this.projectClassesTokens);
        builder.setTokensProjectClassesNonPrivateStaticNonVoidMethods(this.projectMethodsTokens);
        builder.setTokensProjectClassesNonPrivateStaticAttributes(this.projectAttributesTokens);
        builder.setMethodSourceCode(DatasetUtils.getCallableSourceCode(jpCallable));
        builder.setMethodJavadoc(DatasetUtils.getCallableJavadoc(jpCallable));
        builder.setTokensMethodJavadocValues(DatasetUtils.getJavadocValues(builder.copy().getMethodJavadoc()));
        builder.setTokensMethodArguments(DatasetUtils.getTokensMethodArguments(jpClass, jpCallable));
        // get method variable and oracle variable tokens.
        try {
            builder.setTokensMethodVariablesNonPrivateNonStaticNonVoidMethods(
                    DatasetUtils.getTokensMethodVariablesNonPrivateNonStaticNonVoidMethods(
                            jpClass,
                            jpCallable
                    )
            );
            builder.setTokensMethodVariablesNonPrivateNonStaticAttributes(
                    DatasetUtils.getTokensMethodVariablesNonPrivateNonStaticAttributes(
                            jpClass,
                            jpCallable
                    )
            );
            builder.setTokensOracleVariablesNonPrivateNonStaticNonVoidMethods(
                    DatasetUtils.getTokensOracleVariablesNonPrivateNonStaticNonVoidMethods(
                            jpClass,
                            jpCallable,
                            builder.copy().getTokensMethodArguments(),
                            builder.copy().getOracle()
                    )
            );
            builder.setTokensOracleVariablesNonPrivateNonStaticAttributes(
                    DatasetUtils.getTokensOracleVariablesNonPrivateNonStaticAttributes(
                            jpClass,
                            jpCallable,
                            builder.copy().getTokensMethodArguments(),
                            builder.copy().getOracle()
                    )
            );
        } catch (JPClassNotFoundException | UnsolvedSymbolException e) {
            e.printStackTrace();
            return null;
        }
        // return new datapoint.
        builder.setId(this.idCounter);
        this.idCounter++;
        return builder.build();
    }
}
