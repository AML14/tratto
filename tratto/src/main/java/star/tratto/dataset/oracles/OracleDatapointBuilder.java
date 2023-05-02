package star.tratto.dataset.oracles;

import org.javatuples.Pair;
import org.javatuples.Quartet;
import org.javatuples.Triplet;
import star.tratto.dataset.oracles.JDoctorCondition.*;
import star.tratto.identifiers.path.Path;
import star.tratto.identifiers.file.*;
import star.tratto.oracle.OracleType;
import star.tratto.util.FileUtils;

import java.util.List;
import java.util.stream.Collectors;

public class OracleDatapointBuilder {
    private OracleDatapoint datapoint;

    public OracleDatapointBuilder() {
        this.reset();
    }

    public void reset() {
        this.datapoint = new OracleDatapoint();
        this.setDefaultGrammarTokens();
        this.setDefaultGeneralValues();
    }

    private void setDefaultGrammarTokens() {
        String tokensGrammarPath = FileUtils.getAbsolutePathToFile(
                Path.REPOS.getValue(),
                FileName.TOKENS_GRAMMAR,
                FileFormat.JSON
        );
        List<String> tokenGrammar = (List<String>) FileUtils.readJSONList(tokensGrammarPath);
        this.setTokensGeneralGrammar(tokenGrammar);
    }

    private void setDefaultGeneralValues() {
        String tokenGeneralValuesPath = FileUtils.getAbsolutePathToFile(
                Path.REPOS.getValue(),
                FileName.TOKENS_GENERAL_VALUES,
                FileFormat.JSON
        );
        List<Pair<String, String>> tokenGeneralValues = ((List<List<String>>) FileUtils.readJSONList(tokenGeneralValuesPath))
                .stream()
                .map(tokenList -> new Pair<>(tokenList.get(0),tokenList.get(1)))
                .collect(Collectors.toList());
        this.setTokensGeneralValuesGlobalDictionary(tokenGeneralValues);
    }

    private void setThrowsConditionInfo(ThrowsCondition condition) {
        this.setOracleType(OracleType.EXCEPT_POST);
        this.setJavadocTag(condition.getDescription());
        this.setOracle(condition.getGuard().getCondition());
    }

    private void setPreConditionInfo(PreCondition condition) {
        this.setOracleType(OracleType.PRE);
        this.setJavadocTag(condition.getDescription());
        this.setOracle(condition.getGuard().getCondition());
    }

    private void setPostConditionInfo(List<PostCondition> conditionList) {
        assert conditionList.size() >= 2;
        // get base information from first post-condition.
        PostCondition mainCondition = conditionList.get(0);
        String mainTag = mainCondition.getDescription();
        Guard mainGuard = mainCondition.getGuard();
        Property mainProperty = mainCondition.getProperty();
        // start building oracle.
        String oracle = String.format("%s ? %s : ", mainGuard.getCondition(), mainProperty.getCondition());
        // add to oracle.
        if (conditionList.size() == 2) {
            PostCondition secondCondition = conditionList.get(0);
            String secondTag = secondCondition.getDescription();
            assert mainTag.equals(secondTag);
            Property secondProperty = secondCondition.getProperty();
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
        if (condition instanceof ThrowsCondition) {
            this.setThrowsConditionInfo((ThrowsCondition) condition);
        } else if (condition instanceof PreCondition) {
            this.setPreConditionInfo((PreCondition) condition);
        } else if (condition instanceof List<?>) {
            this.setPostConditionInfo((List<PostCondition>) condition);
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

    public OracleDatapoint build() {
        OracleDatapoint oracleDP = this.datapoint;
        this.reset();
        return oracleDP;
    }
}
