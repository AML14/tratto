package star.tratto.dataset.oracles;

import org.javatuples.Pair;
import org.javatuples.Quartet;
import org.javatuples.Triplet;
import star.tratto.oracle.OracleType;

import java.util.List;

public class OracleDatapointBuilder {
    private OracleDatapoint datapoint;

    public OracleDatapointBuilder() {
        this.reset();
    }

    public void reset() {
        this.datapoint = new OracleDatapoint();
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

    public void setTokensMethodVariablesNonPrivateNonStaticAttrributes(List<Quartet<String, String, String, String>> tokensMethodVariablesNonPrivateNonStaticAttrributes) {
        this.datapoint.setTokensMethodVariablesNonPrivateNonStaticAttrributes(tokensMethodVariablesNonPrivateNonStaticAttrributes);
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
