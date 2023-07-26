package star.tratto.data.oracles;

import org.javatuples.Pair;
import org.javatuples.Quartet;
import org.javatuples.Triplet;
import star.tratto.data.OracleDatapoint;
import star.tratto.data.OracleType;
import star.tratto.data.IOPath;
import star.tratto.util.FileUtils;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OracleDatapointBuilder {
    private OracleDatapoint datapoint;
    private static final List<String> featureLevels = List.of("default", "project", "class", "method");

    public OracleDatapointBuilder() {
        this.reset();
    }

    /**
     * Resets all fields in the current datapoint {@link OracleDatapoint}.
     * Sets default values.
     */
    public void reset() {
        this.datapoint = new OracleDatapoint();
        // set default oracle datapoint values.
        this.setDefaultGrammarTokens();
        this.setDefaultGeneralValues();
        // set non-null values for fields which may throw errors when retrieving information.
        this.setTokensMethodVariablesNonPrivateNonStaticNonVoidMethods(new ArrayList<>());
        this.setTokensMethodVariablesNonPrivateNonStaticAttributes(new ArrayList<>());
        this.setTokensOracleVariablesNonPrivateNonStaticNonVoidMethods(new ArrayList<>());
        this.setTokensOracleVariablesNonPrivateNonStaticAttributes(new ArrayList<>());
    }

    public void resetWithDefaults() {
        this.reset();
        this.setId(0);
        this.setOracle("");
        this.setProjectName("");
    }

    public void reset(String level, boolean defaults) {
        OracleDatapoint oracleDP = this.copy();
        if (defaults) {
            this.resetWithDefaults();
        } else {
            this.reset();
        }
        // copy fields based on `level`.
        assert featureLevels.contains(level) : String.format("Given level must be one of: %s.%n", featureLevels);
        switch (level) {
            case "method":
                this.setMethodSourceCode(oracleDP.getMethodSourceCode());
                this.setMethodJavadoc(oracleDP.getMethodJavadoc());
                this.setTokensMethodJavadocValues(oracleDP.getTokensMethodJavadocValues());
                this.setTokensMethodArguments(oracleDP.getTokensMethodArguments());
                this.setTokensMethodVariablesNonPrivateNonStaticNonVoidMethods(oracleDP.getTokensMethodVariablesNonPrivateNonStaticNonVoidMethods());
                this.setTokensMethodVariablesNonPrivateNonStaticAttributes(oracleDP.getTokensMethodVariablesNonPrivateNonStaticAttributes());
            case "class":
                this.setClassName(oracleDP.getClassName());
                this.setClassSourceCode(oracleDP.getClassSourceCode());
                this.setClassJavadoc(oracleDP.getClassJavadoc());
                this.setPackageName(oracleDP.getPackageName());
            case "project":
                this.setTokensProjectClasses(oracleDP.getTokensProjectClasses());
                this.setTokensProjectClassesNonPrivateStaticNonVoidMethods(oracleDP.getTokensProjectClassesNonPrivateStaticNonVoidMethods());
                this.setTokensProjectClassesNonPrivateStaticAttributes(oracleDP.getTokensProjectClassesNonPrivateStaticAttributes());
                this.setProjectName(oracleDP.getProjectName());
            case "default":
                break;
        }
    }

    /**
     * Sets default general tokens for symbolic grammar.
     */
    private void setDefaultGrammarTokens() {
        Path tokensGrammarPath = IOPath.TOKENS_GRAMMAR.getPath();
        List<String> tokenGrammar = FileUtils.readJSONList(tokensGrammarPath)
                .stream()
                .map(e -> (String) e)
                .collect(Collectors.toList());
        this.setTokensGeneralGrammar(tokenGrammar);
    }

    /**
     * Sets default global values for tokens.
     */
    private void setDefaultGeneralValues() {
        Path tokensGeneralValuesPath = IOPath.TOKENS_GENERAL_VALUES.getPath();
        List<Pair<String, String>> tokenGeneralValues = FileUtils.readJSONList(tokensGeneralValuesPath)
                .stream()
                .map(e -> ((List<?>) e)
                        .stream()
                        .map(o -> (String) o)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList())
                .stream()
                .map(tokenList -> new Pair<>(tokenList.get(0), tokenList.get(1)))
                .collect(Collectors.toList());
        this.setTokensGeneralValuesGlobalDictionary(tokenGeneralValues);
    }

    private void setThrowsConditionInfo(JDoctorCondition.ThrowsCondition condition) {
        this.setOracleType(OracleType.EXCEPT_POST);
        this.setJavadocTag(condition.getDescription());
        this.setOracle(String.format("%s;", condition.getGuard().getCondition()).replaceAll("receiverObjectID", "this"));
    }

    private void setPreConditionInfo(JDoctorCondition.PreCondition condition) {
        this.setOracleType(OracleType.PRE);
        this.setJavadocTag(condition.getDescription());
        this.setOracle(String.format("%s;", condition.getGuard().getCondition()).replaceAll("receiverObjectID", "this"));
    }

    private void setPostConditionInfo(List<JDoctorCondition.PostCondition> conditionList) {
        assert conditionList.size() <= 2;
        // get base information from first post-condition.
        JDoctorCondition.PostCondition mainCondition = conditionList.get(0);
        String mainTag = mainCondition.getDescription();
        JDoctorCondition.Guard mainGuard = mainCondition.getGuard();
        JDoctorCondition.Property mainProperty = mainCondition.getProperty();
        // start building oracle.
        String oracle = String.format("%s ? %s : ", mainGuard.getCondition(), mainProperty.getCondition());
        // add to oracle.
        if (conditionList.size() == 2) {
            JDoctorCondition.PostCondition secondCondition = conditionList.get(1);
            String secondTag = secondCondition.getDescription();
            assert mainTag.equals(secondTag);
            JDoctorCondition.Property secondProperty = secondCondition.getProperty();
            oracle += String.format("%s;", secondProperty.getCondition());
        } else {
            oracle += "true;";
        }
        oracle = oracle.replaceAll("receiverObjectID", "this");
        // add information to datapoint.
        this.setOracleType(OracleType.NORMAL_POST);
        this.setJavadocTag(mainTag);
        this.setOracle(oracle);
    }

    /**
     * Gets all information from a JDoctor condition (e.g. ThrowsCondition,
     * PreCondition, or a list of PostCondition's). Sets the oracle type,
     * JavaDoc tag, and oracle.
     *
     * @param condition a JDoctor condition
     */
    public void setConditionInfo(Object condition) {
        if (condition instanceof JDoctorCondition.ThrowsCondition) {
            this.setThrowsConditionInfo((JDoctorCondition.ThrowsCondition) condition);
        } else if (condition instanceof JDoctorCondition.PreCondition) {
            this.setPreConditionInfo((JDoctorCondition.PreCondition) condition);
        } else if (condition instanceof List<?>) {
            List<JDoctorCondition.PostCondition> conditionList = ((List<?>) condition)
                    .stream()
                    .map(e -> (JDoctorCondition.PostCondition) e)
                    .collect(Collectors.toList());
            if (conditionList.size() > 0) {
                this.setPostConditionInfo(conditionList);
            }
        }
    }

    public void setId(Integer id) {
        this.datapoint.setId(id);
    }

    public void setOracle(String oracle) {
        this.datapoint.setOracle(oracle);
    }

    public void setOracleType(OracleType oracleType) {
        this.datapoint.setOracleType(oracleType);
    }

    public void setProjectName(String projectName) {
        this.datapoint.setProjectName(projectName);
    }

    public void setPackageName(String packageName) {
        this.datapoint.setPackageName(packageName);
    }

    public void setClassName(String className) {
        this.datapoint.setClassName(className);
    }

    public void setJavadocTag(String javadocTag) {
        this.datapoint.setJavadocTag(javadocTag);
    }

    public void setMethodJavadoc(String methodJavadoc) {
        this.datapoint.setMethodJavadoc(methodJavadoc);
    }

    public void setMethodSourceCode(String methodSourceCode) {
        this.datapoint.setMethodSourceCode(methodSourceCode);
    }

    public void setClassJavadoc(String classJavadoc) {
        this.datapoint.setClassJavadoc(classJavadoc);
    }

    public void setClassSourceCode(String classSourceCode) {
        this.datapoint.setClassSourceCode(classSourceCode);
    }

    public void setTokensGeneralGrammar(List<String> tokensGeneralGrammar) {
        this.datapoint.setTokensGeneralGrammar(tokensGeneralGrammar);
    }

    public void setTokensGeneralValuesGlobalDictionary(List<Pair<String, String>> tokensGeneralValuesGlobalDictionary) {
        this.datapoint.setTokensGeneralValuesGlobalDictionary(tokensGeneralValuesGlobalDictionary);
    }

    public void setTokensProjectClasses(List<Pair<String, String>> tokensProjectClasses) {
        this.datapoint.setTokensProjectClasses(tokensProjectClasses);
    }

    public void setTokensProjectClassesNonPrivateStaticNonVoidMethods(List<Quartet<String, String, String, String>> tokensProjectClassesNonPrivateStaticNonVoidMethods) {
        this.datapoint.setTokensProjectClassesNonPrivateStaticNonVoidMethods(tokensProjectClassesNonPrivateStaticNonVoidMethods);
    }

    public void setTokensProjectClassesNonPrivateStaticAttributes(List<Quartet<String, String, String, String>> tokensProjectClassesNonPrivateStaticAttributes) {
        this.datapoint.setTokensProjectClassesNonPrivateStaticAttributes(tokensProjectClassesNonPrivateStaticAttributes);
    }

    public void setTokensMethodJavadocValues(List<Pair<String, String>> tokensMethodJavadocValues) {
        this.datapoint.setTokensMethodJavadocValues(tokensMethodJavadocValues);
    }

    public void setTokensMethodArguments(List<Triplet<String, String, String>> tokensMethodArguments) {
        this.datapoint.setTokensMethodArguments(tokensMethodArguments);
    }

    public void setTokensMethodVariablesNonPrivateNonStaticNonVoidMethods(List<Quartet<String, String, String, String>> tokensMethodVariablesNonPrivateNonStaticNonVoidMethods) {
        this.datapoint.setTokensMethodVariablesNonPrivateNonStaticNonVoidMethods(tokensMethodVariablesNonPrivateNonStaticNonVoidMethods);
    }

    public void setTokensMethodVariablesNonPrivateNonStaticAttributes(List<Quartet<String, String, String, String>> tokensMethodVariablesNonPrivateNonStaticAttributes) {
        this.datapoint.setTokensMethodVariablesNonPrivateNonStaticAttributes(tokensMethodVariablesNonPrivateNonStaticAttributes);
    }

    public void setTokensOracleVariablesNonPrivateNonStaticNonVoidMethods(List<Quartet<String, String, String, String>> tokensOracleVariablesNonPrivateNonStaticNonVoidMethods) {
        this.datapoint.setTokensOracleVariablesNonPrivateNonStaticNonVoidMethods(tokensOracleVariablesNonPrivateNonStaticNonVoidMethods);
    }

    public void setTokensOracleVariablesNonPrivateNonStaticAttributes(List<Quartet<String, String, String, String>> tokensOracleVariablesNonPrivateNonStaticAttributes) {
        this.datapoint.setTokensOracleVariablesNonPrivateNonStaticAttributes(tokensOracleVariablesNonPrivateNonStaticAttributes);
    }

    public OracleDatapoint copy() {
        return new OracleDatapoint(
                this.datapoint.getId(),
                this.datapoint.getOracle(),
                this.datapoint.getOracleType(),
                this.datapoint.getProjectName(),
                this.datapoint.getPackageName(),
                this.datapoint.getClassName(),
                this.datapoint.getJavadocTag(),
                this.datapoint.getMethodJavadoc(),
                this.datapoint.getMethodSourceCode(),
                this.datapoint.getClassJavadoc(),
                this.datapoint.getClassSourceCode(),
                this.datapoint.getTokensGeneralGrammar(),
                this.datapoint.getTokensGeneralValuesGlobalDictionary(),
                this.datapoint.getTokensProjectClasses(),
                this.datapoint.getTokensProjectClassesNonPrivateStaticNonVoidMethods(),
                this.datapoint.getTokensProjectClassesNonPrivateStaticAttributes(),
                this.datapoint.getTokensMethodJavadocValues(),
                this.datapoint.getTokensMethodArguments(),
                this.datapoint.getTokensMethodVariablesNonPrivateNonStaticNonVoidMethods(),
                this.datapoint.getTokensMethodVariablesNonPrivateNonStaticAttributes(),
                this.datapoint.getTokensOracleVariablesNonPrivateNonStaticNonVoidMethods(),
                this.datapoint.getTokensOracleVariablesNonPrivateNonStaticAttributes()
        );
    }

    /**
     * Returns a new datapoint, but carries over specific columns from
     * current datapoint based on the given `level`. This avoids re-running
     * code between each generated oracle. The columns kept for each level are:
     * - "default": tokensGeneralGrammar, tokensGeneralValuesGlobalDictionary.
     * - "project": tokensProjectClasses,
     *      tokensProjectClassesNonPrivateStaticNonVoidMethods,
     *      tokensProjectClassesNonPrivateStaticAttributes.
     * - "class": className, classSourceCode, classJavadoc, packageName.
     * - "method": methodSourceCode, methodJavadoc, tokensMethodArguments,
     *      tokensMethodVariablesNonPrivateNonStaticNonVoidMethods,
     *      tokensMethodVariablesNonPrivateNonStaticAttributes.
     * The ordering is hierarchical, such that each level keeps columns from
     * the previous level (e.g. "project" also keeps "default" features).
     *
     * @param level the depth of the reset. Must be one of: "default",
     *              "project", "class", or "method".
     * @param defaults set to false to reset the oracle normally ({@link #reset} method).
     *                 Set to true to keep default values for some properties
     *                 ({@link #resetWithDefaults} method).
     * @return a new datapoint
     */
    public OracleDatapoint build(String level, boolean defaults) {
        OracleDatapoint oracleDP = this.copy();
        reset(level, defaults);
        return oracleDP;
    }

    /**
     * @return a new datapoint {@link OracleDatapoint}. Resets builder.
     */
    public OracleDatapoint build() {
        OracleDatapoint oracleDP = this.copy();
        this.reset();
        return oracleDP;
    }
}
