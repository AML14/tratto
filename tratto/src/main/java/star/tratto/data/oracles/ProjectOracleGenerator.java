package star.tratto.data.oracles;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.CallableDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.resolution.UnsolvedSymbolException;
import star.tratto.data.OracleDatapoint;
import star.tratto.data.OracleType;
import star.tratto.data.JPClassNotFoundException;
import star.tratto.data.records.AttributeTokens;
import star.tratto.data.records.ClassTokens;
import star.tratto.data.records.JDoctorCondition;
import star.tratto.data.records.JDoctorCondition.Operation;
import star.tratto.data.records.JDoctorCondition.PostCondition;
import star.tratto.data.records.JDoctorCondition.PreCondition;
import star.tratto.data.records.JDoctorCondition.ThrowsCondition;
import star.tratto.data.records.JavadocTag;
import star.tratto.data.records.MethodTokens;
import star.tratto.data.records.Project;
import star.tratto.util.StringUtils;
import star.tratto.util.javaparser.DatasetUtils;
import star.tratto.util.javaparser.TypeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.nio.file.Path;
import java.util.regex.Pattern;

/**
 * ProjectOracleGenerator generates all {@link OracleDatapoint}'s in a project
 * using a list of JDoctor conditions as a ground truth dataset to train the
 * Tratto neural modules.
 */
public class ProjectOracleGenerator {
    /** Unique identifier for an OracleDatapoint. */
    private int idCounter = 0;
    /** The project being analyzed. */
    private Project project;
    /** All JDoctor conditions of the current project. */
    private List<JDoctorCondition> jDoctorConditions;
    /** All classes in the current project. */
    private List<ClassTokens> classes;
    /** All non-private, static methods in the current project. */
    private List<MethodTokens> methods;
    /**
     * All non-private, static fields (denoted as "attributes" in the dataset)
     * in the current project.
     */
    private List<AttributeTokens> fields;
    /** All Javadoc tags in the current project. */
    private List<JavadocTag> tags;

    /**
     * Sets the project under analysis and project-level features. This avoids
     * repeatedly reloading shared features.
     *
     * @param project the project under analysis
     * @param jDoctorConditions the JDoctor conditions associated with the
     *                          project under analysis
     */
    private void loadProject(
            Project project,
            List<JDoctorCondition> jDoctorConditions
    ) {
        this.project = project;
        this.jDoctorConditions = jDoctorConditions;
        this.classes = DatasetUtils.getProjectClassTokens(this.project.srcPath());
        this.methods = DatasetUtils.getProjectNonPrivateStaticNonVoidMethodsTokens(this.project.srcPath());
        this.fields = DatasetUtils.getProjectNonPrivateStaticAttributesTokens(this.project.srcPath());
        this.tags = DatasetUtils.getProjectTagsTokens(this.project.srcPath());
        System.out.printf("Identified %s total JavaDoc tags.%n", this.tags.size());
    }

    /**
     * Gets all OracleDatapoint objects for the project under analysis.
     * Generates empty oracle datapoints for tags without a JDoctor condition,
     * representing tags which cannot have a corresponding oracle.
     *
     * @return a list of oracle datapoints
     */
    public List<OracleDatapoint> generate(
            Project project,
            List<JDoctorCondition> jDoctorConditions
    ) {
        this.loadProject(project, jDoctorConditions);
        List<OracleDatapoint> oracleDPs = new ArrayList<>();
        // Generate an OracleDatapoint for each JDoctor condition.
        // Removes corresponding Javadoc tag from list of tags.
        for (JDoctorCondition jDoctorCondition : this.jDoctorConditions) {
            Operation operation = jDoctorCondition.operation();
            // Add all ThrowsCondition oracles to dataset.
            List<ThrowsCondition> throwsConditions = jDoctorCondition.throwsConditions();
            for (ThrowsCondition throwsCondition : throwsConditions) {
                OracleDatapoint nextDatapoint = getDatapoint(operation, throwsCondition);
                if (nextDatapoint != null) oracleDPs.add(nextDatapoint);
                removeProjectClassesTag(operation, OracleType.EXCEPT_POST, throwsCondition.description());
            }
            // Add all PreCondition oracles to dataset.
            List<PreCondition> preConditions = jDoctorCondition.preConditions();
            for (PreCondition preCondition : preConditions) {
                OracleDatapoint nextDatapoint = getDatapoint(operation, preCondition);
                if (nextDatapoint != null) oracleDPs.add(nextDatapoint);
                removeProjectClassesTag(operation, OracleType.PRE, preCondition.description());
            }
            // Add all PostCondition oracles to dataset.
            List<PostCondition> postConditions = jDoctorCondition.postConditions();
            if (!postConditions.isEmpty()) {
                OracleDatapoint nextDatapoint = getDatapoint(operation, postConditions);
                if (nextDatapoint != null) oracleDPs.add(nextDatapoint);
                // first description corresponds to source tag.
                removeProjectClassesTag(operation, OracleType.NORMAL_POST, postConditions.get(0).description());
            }
        }
        int numNonEmptyOracles = oracleDPs.size();
        // Generate an OracleDatapoint for each remaining JavaDoc tag.
        for (JavadocTag jpTag : this.tags) {
            OracleDatapoint nextDatapoint = getEmptyDatapoint(jpTag);
            if (nextDatapoint != null) oracleDPs.add(nextDatapoint);
        }
        int numEmptyOracles = oracleDPs.size() - numNonEmptyOracles;
        // log information.
        System.out.printf("Processed %s non-empty oracles.%n", numNonEmptyOracles);
        System.out.printf("Processed %s empty oracles.%n", numEmptyOracles);
        System.out.printf("Processed %s total oracles.%n", numNonEmptyOracles + numEmptyOracles);
        return oracleDPs;
    }

    /**
     * Checks if a preprocessed JDoctor Javadoc tag matches a given name.
     *
     * @param targetTag a preprocessed JDoctor javadoc tag
     * @param name a Javadoc tag name (parameter name or exception type)
     * @return true if the target tag matches the given name or if the JDoctor
     * tag has no name (and {@code name} is empty)
     */
    private boolean tagHasName(
            String targetTag,
            String name
    ) {
        // check if target tag has no name
        if (!targetTag.startsWith("@param") && !targetTag.startsWith("@throws")) {
            if (name.isEmpty()) {
                return true;
            }
        }
        // check if target tag matches given name
        Pattern tagNamePattern = Pattern.compile("@(param|return|throws)\\s+(.*\\.)*" + name + "\\b");
        return tagNamePattern.matcher(targetTag).find();
    }

    /**
     * Removes all special characters from a Javadoc tag. For example,
     *     "removes all {&#64;code links} from a document" &rarr;
     *     "removes all links from a document"
     *
     * @param unprocessedTag a Javadoc tag
     * @return a Javadoc tag without "&#64;code", "&#64;link", "{", "}", "\n",
     * "\r", "\t", HTML tags (angle brackets), or type parameter
     */
    private String removeTagSpecialCharacters(String unprocessedTag) {
        String previous;
        String current = unprocessedTag;
        do {
            previous = current;
            current = previous.replaceAll("<[^>]*>|@code|@link|\\{|}|\\n|\\r|\\t", " ");
        } while (!current.equals(previous));
        return current;
    }

    /** The regex to match an arbitrary tag type ("@param", "@return", "@throws"). */
    private static final String tagTypeRegex = "@(param|return|throws)\\s+(.*\\.)*";

    /**
     * Removes a tag prefix from a Javadoc tag (includes tag type and name).
     * For example,
     *     "@param name the student name" &rarr; "the student name"
     *
     * @param unprocessedTag a Javadoc tag
     * @param tagName the name of the Javadoc tag
     * @return a Javadoc tag without the tag type or tag name
     */
    private String removeTagPrefix(String unprocessedTag, String tagName) {
        return unprocessedTag.replaceAll(tagTypeRegex + tagName + "\\b", "");
    }

    /**
     * Gets the most similar tag from {@link ProjectOracleGenerator#tags} to a
     * target preprocessed JDoctor tag. Only considers tags of a given type
     * from a given method in a given class. Among the remaining tags, uses
     * semantic similarity to find the most similar source code tag to the
     * preprocessed JDoctor tag.
     *
     * @param targetClass target class
     * @param targetCallable target method/constructor
     * @param targetOracleType target oracle type (e.g. pre-condition)
     * @param targetTag the preprocessed JDoctor Javadoc tag
     * @return tag with the greatest similarity to {@code targetTag}
     * @see StringUtils#semanticSimilarity(String, String)
     */
    private JavadocTag findMaximumSimilarityTag(
            TypeDeclaration<?> targetClass,
            CallableDeclaration<?> targetCallable,
            OracleType targetOracleType,
            String targetTag
    ) {
        // filter tags by TypeDeclaration, CallableDeclaration, OracleType, and Pattern matching.
        List<JavadocTag> filteredTags = this.tags
                .stream()
                .filter(tagInfo -> tagInfo.jpClass().equals(targetClass) &&
                        tagInfo.jpCallable().equals(targetCallable) &&
                        tagInfo.oracleType().equals(targetOracleType) &&
                        tagHasName(targetTag, tagInfo.tagName()))
                .toList();
        if (filteredTags.size() == 1) {
            return filteredTags.get(0);
        }
        // find index of most semantically similar tag (cosine similarity).
        JavadocTag mostSimilarTag = null;
        double maxSimilaritySoFar = -1.0;
        for (JavadocTag tag : filteredTags) {
            String simpleTargetBody = removeTagSpecialCharacters(removeTagPrefix(targetTag, tag.tagName()));
            String simpleActualBody = removeTagSpecialCharacters(tag.tagBody());
            double currentSimilarity = StringUtils.semanticSimilarity(simpleTargetBody, simpleActualBody);
            if (currentSimilarity > maxSimilaritySoFar) {
                maxSimilaritySoFar = currentSimilarity;
                mostSimilarTag = tag;
            }
        }
        if (mostSimilarTag == null) {
            throw new Error("Unable to find a tag corresponding to " + targetTag);
        }
        return mostSimilarTag;
    }

    /**
     * Removes a Javadoc tag of a JDoctor condition from
     * {@link ProjectOracleGenerator#tags}.
     *
     * @param operation the operation of the JDoctor condition to remove a
     *                  Javadoc tag from
     * @param oracleType the type of oracle to be removed (PRE, NORMAL_POST,
     *                   EXCEPT_POST)
     * @param javaDocTag the (JDoctor simplified) JavaDoc tag to be removed
     */
    private void removeProjectClassesTag(
            Operation operation,
            OracleType oracleType,
            String javaDocTag
    ) {
        Path sourcePath = this.project.srcPath();
        String className = TypeUtils.getInnermostClassNameFromClassGetName(operation.className());
        String callableName = DatasetUtils.getOperationCallableName(operation);
        List<String> parameterTypes = TypeUtils.classGetNameToClassGetSimpleName(operation.parameterTypes());
        Optional<CompilationUnit> cuOptional = DatasetUtils.getOperationCompilationUnit(operation, sourcePath);
        if (cuOptional.isPresent()) {
            TypeDeclaration<?> jpClass = DatasetUtils.getTypeDeclaration(cuOptional.get(), className);
            assert jpClass != null;
            CallableDeclaration<?> jpCallable = DatasetUtils.getCallableDeclaration(jpClass, callableName, parameterTypes);
            assert jpCallable != null;
            // remove tag with maximum similarity.
            this.tags.remove(findMaximumSimilarityTag(
                    jpClass,
                    jpCallable,
                    oracleType,
                    javaDocTag
            ));
        }
    }

    /**
     * Generates an OracleDatapoint for a given Javadoc tag that does not
     * have a corresponding JDoctor condition.
     *
     * @param jpTag a Javadoc tag
     * @return a fully populated OracleDatapoint. The "oracle" field is set
     * to a semicolon (";"). Returns null if an error occurs during
     * information collection.
     */
    private OracleDatapoint getEmptyDatapoint(
            JavadocTag jpTag
    ) {
        OracleDatapointBuilder builder = new OracleDatapointBuilder();
        // get basic information of jpTag.
        TypeDeclaration<?> jpClass = jpTag.jpClass();
        CallableDeclaration<?> jpCallable = jpTag.jpCallable();
        OracleType oracleType = jpTag.oracleType();
        String tagName = jpTag.tagName();
        String tagContent = jpTag.tagBody();
        // get artificial condition information.
        String tagType = switch (oracleType) {
            case PRE -> "@param ";
            case NORMAL_POST -> "@return ";
            case EXCEPT_POST -> "@throws ";
        };
        builder.setOracleType(oracleType);
        builder.setJavadocTag(String.format("%s%s%s", tagType, !tagName.equals("") ? tagName + " " : "", tagContent));
        builder.setOracle(";");
        // set project-level information.
        builder.setProjectName(this.project.projectName());
        builder.setClassSourceCode(jpTag.fileContent());
        builder.setPackageName(jpClass.resolve().getPackageName());
        builder.setClassName(jpClass.getNameAsString());
        builder.setClassJavadoc(DatasetUtils.getClassJavadoc(jpClass));
        builder.setTokensProjectClasses(this.classes);
        builder.setTokensProjectClassesNonPrivateStaticNonVoidMethods(this.methods);
        builder.setTokensProjectClassesNonPrivateStaticAttributes(this.fields);
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
     * @param condition a JDoctor condition (i.e. ThrowsCondition,
     *                  PreCondition, or a list of PostCondition's)
     * @return a fully populated OracleDatapoint. Returns null if an error
     * occurs during information collection.
     */
    private OracleDatapoint getDatapoint(Operation operation, Object condition) {
        OracleDatapointBuilder builder = new OracleDatapointBuilder();
        // get basic information of operation.
        Path sourcePath = this.project.srcPath();
        String projectName = this.project.projectName();
        String packageName = TypeUtils.getPackageNameFromClassGetName(operation.className());
        String className = TypeUtils.getInnermostClassNameFromClassGetName(operation.className());
        String callableName = DatasetUtils.getOperationCallableName(operation);
        List<String> parameterTypes = TypeUtils.classGetNameToClassGetSimpleName(operation.parameterTypes());
        // get CompilationUnit of operation class.
        Optional<CompilationUnit> cuOptional = DatasetUtils.getOperationCompilationUnit(operation, sourcePath);
        if (cuOptional.isEmpty()) {
          return null;
        }
        CompilationUnit cu = cuOptional.get();
        String classSourceCode = DatasetUtils.getOperationClassSource(operation, sourcePath).orElseThrow();
        // get TypeDeclaration of class in CompilationUnit.
        TypeDeclaration<?> jpClass = DatasetUtils.getTypeDeclaration(cu, className);
        assert jpClass != null;
        // get CallableDeclaration of method in TypeDeclaration.
        CallableDeclaration<?> jpCallable = DatasetUtils.getCallableDeclaration(jpClass, callableName, parameterTypes);
        assert jpCallable != null;
        // set data point information.
        builder.setConditionInfo(condition);
        // override JavaDoc tag with tag from source code.
        builder.setJavadocTag(DatasetUtils.getTagAsString(findMaximumSimilarityTag(
                jpClass,
                jpCallable,
                builder.copy().getOracleType(),
                builder.copy().getJavadocTag()
        )));
        builder.setProjectName(projectName);
        builder.setClassSourceCode(classSourceCode);
        builder.setPackageName(packageName);
        builder.setClassName(className);
        builder.setClassJavadoc(DatasetUtils.getClassJavadoc(jpClass));
        builder.setTokensProjectClasses(this.classes);
        builder.setTokensProjectClassesNonPrivateStaticNonVoidMethods(this.methods);
        builder.setTokensProjectClassesNonPrivateStaticAttributes(this.fields);
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
