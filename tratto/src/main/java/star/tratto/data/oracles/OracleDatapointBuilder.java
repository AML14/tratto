package star.tratto.data.oracles;

import org.javatuples.Pair;
import org.javatuples.Quartet;
import org.javatuples.Triplet;
import star.tratto.data.OracleDatapoint;
import star.tratto.data.OracleType;
import star.tratto.identifiers.path.Path;
import star.tratto.identifiers.file.*;
import star.tratto.util.FileUtils;

import java.util.ArrayList;
import java.util.List;

public class OracleDatapointBuilder {
    private OracleDatapoint datapoint;

    public OracleDatapointBuilder() {
        this.reset();
    }

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

    private void setDefaultGrammarTokens() {
        String tokensGrammarPath = FileUtils.getAbsolutePathToFile(
                Path.REPOS.getValue(),
                FileName.TOKENS_GRAMMAR,
                FileFormat.JSON
        );
        List<String> tokenGrammar = FileUtils.readJSONList(tokensGrammarPath)
                .stream()
                .map(e -> (String) e)
                .toList();
        this.setTokensGeneralGrammar(tokenGrammar);
    }

    private void setDefaultGeneralValues() {
        String tokenGeneralValuesPath = FileUtils.getAbsolutePathToFile(
                Path.REPOS.getValue(),
                FileName.TOKENS_GENERAL_VALUES,
                FileFormat.JSON
        );
        List<Pair<String, String>> tokenGeneralValues = FileUtils.readJSONList(tokenGeneralValuesPath)
                .stream()
                .map(e -> ((List<?>) e)
                        .stream()
                        .map(o -> (String) o)
                        .toList())
                .toList()
                .stream()
                .map(tokenList -> new Pair<>(tokenList.get(0), tokenList.get(1)))
                .toList();
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

    public void setConditionInfo(Object condition) {
        if (condition instanceof JDoctorCondition.ThrowsCondition) {
            this.setThrowsConditionInfo((JDoctorCondition.ThrowsCondition) condition);
        } else if (condition instanceof JDoctorCondition.PreCondition) {
            this.setPreConditionInfo((JDoctorCondition.PreCondition) condition);
        } else if (condition instanceof List<?>) {
            List<JDoctorCondition.PostCondition> conditionList = ((List<?>) condition)
                    .stream()
                    .map(e -> (JDoctorCondition.PostCondition) e)
                    .toList();
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
        return this.datapoint;
    }

    public OracleDatapoint build() {
        OracleDatapoint oracleDP = this.datapoint;
        this.reset();
        return oracleDP;
    }
}
