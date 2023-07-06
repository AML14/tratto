package star.tratto.data;

import org.javatuples.Pair;
import org.javatuples.Quartet;
import org.javatuples.Triplet;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Oracle containing the information as it is extracted from the oracles dataset
 */
public class OracleDatapoint {
    private Integer id;
    private String oracle;
    private OracleType oracleType;
    private String projectName;
    private String packageName;
    private String className;
    private String javadocTag;
    private String methodJavadoc;
    private String methodSourceCode;
    private String classJavadoc;
    private String classSourceCode;
    private List<String> tokensGeneralGrammar;
    private List<Pair<String, String>> tokensGeneralValuesGlobalDictionary; // <token, type>
    private List<Pair<String, String>> tokensProjectClasses; // <token, package>
    private List<Quartet<String, String, String, String>> tokensProjectClassesNonPrivateStaticNonVoidMethods; // <token, package, class, signature>
    private List<Quartet<String, String, String, String>> tokensProjectClassesNonPrivateStaticAttributes; // <token, package, class, declaration>
    private List<Pair<String, String>> tokensMethodJavadocValues; // <token, type>
    private List<Triplet<String, String, String>> tokensMethodArguments; // <token, package, class>
    private List<Quartet<String, String, String, String>> tokensMethodVariablesNonPrivateNonStaticNonVoidMethods; // <token, package, class, signature>
    private List<Quartet<String, String, String, String>> tokensMethodVariablesNonPrivateNonStaticAttributes; // <token, package, class, declaration>
    private List<Quartet<String, String, String, String>> tokensOracleVariablesNonPrivateNonStaticNonVoidMethods; // <token, package, class, signature>
    private List<Quartet<String, String, String, String>> tokensOracleVariablesNonPrivateNonStaticAttributes; // <token, package, class, declaration>

    public OracleDatapoint(Map oracleDatapointMap) {
        this.id = (Integer) oracleDatapointMap.get("id");
        this.oracle = (String) oracleDatapointMap.get("oracle");
        this.oracleType = OracleType.valueOf((String) oracleDatapointMap.get("oracleType"));
        this.projectName = (String) oracleDatapointMap.get("projectName");
        this.packageName = (String) oracleDatapointMap.get("packageName");
        this.className = (String) oracleDatapointMap.get("className");
        this.javadocTag = (String) oracleDatapointMap.get("javadocTag");
        this.methodJavadoc = (String) oracleDatapointMap.get("methodJavadoc");
        this.methodSourceCode = (String) oracleDatapointMap.get("methodSourceCode");
        this.classJavadoc = (String) oracleDatapointMap.get("classJavadoc");
        this.classSourceCode = (String) oracleDatapointMap.get("classSourceCode");
        this.tokensGeneralGrammar = (List<String>) oracleDatapointMap.get("tokensGeneralGrammar");
        this.tokensGeneralValuesGlobalDictionary = ((List<List<String>>) oracleDatapointMap.get("tokensGeneralValuesGlobalDictionary")).stream().map(Pair::fromCollection).collect(Collectors.toList());
        this.tokensProjectClasses = ((List<List<String>>) oracleDatapointMap.get("tokensProjectClasses")).stream().map(Pair::fromCollection).collect(Collectors.toList());
        this.tokensProjectClassesNonPrivateStaticNonVoidMethods = ((List<List<String>>) oracleDatapointMap.get("tokensProjectClassesNonPrivateStaticNonVoidMethods")).stream().map(Quartet::fromCollection).collect(Collectors.toList());
        this.tokensProjectClassesNonPrivateStaticAttributes = ((List<List<String>>) oracleDatapointMap.get("tokensProjectClassesNonPrivateStaticAttributes")).stream().map(Quartet::fromCollection).collect(Collectors.toList());
        this.tokensMethodJavadocValues = ((List<List<String>>) oracleDatapointMap.get("tokensMethodJavadocValues")).stream().map(Pair::fromCollection).collect(Collectors.toList());
        this.tokensMethodArguments = ((List<List<String>>) oracleDatapointMap.get("tokensMethodArguments")).stream().map(Triplet::fromCollection).collect(Collectors.toList());
        this.tokensMethodVariablesNonPrivateNonStaticNonVoidMethods = ((List<List<String>>) oracleDatapointMap.get("tokensMethodVariablesNonPrivateNonStaticNonVoidMethods")).stream().map(Quartet::fromCollection).collect(Collectors.toList());
        this.tokensMethodVariablesNonPrivateNonStaticAttributes = ((List<List<String>>) oracleDatapointMap.get("tokensMethodVariablesNonPrivateNonStaticAttributes")).stream().map(Quartet::fromCollection).collect(Collectors.toList());
        this.tokensOracleVariablesNonPrivateNonStaticNonVoidMethods = ((List<List<String>>) oracleDatapointMap.get("tokensOracleVariablesNonPrivateNonStaticNonVoidMethods")).stream().map(Quartet::fromCollection).collect(Collectors.toList());
        this.tokensOracleVariablesNonPrivateNonStaticAttributes = ((List<List<String>>) oracleDatapointMap.get("tokensOracleVariablesNonPrivateNonStaticAttributes")).stream().map(Quartet::fromCollection).collect(Collectors.toList());
    }

    // For testing purposes
    OracleDatapoint() {
    }

    public boolean isProjectClass(String clazz) {
        return tokensProjectClasses.stream().anyMatch(projectClass -> projectClass.getValue0().equals(clazz));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOracle() {
        return oracle;
    }

    public void setOracle(String oracle) {
        this.oracle = oracle;
    }

    public OracleType getOracleType() {
        return oracleType;
    }

    public void setOracleType(OracleType oracleType) {
        this.oracleType = oracleType;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getJavadocTag() {
        return javadocTag;
    }

    public void setJavadocTag(String javadocTag) {
        this.javadocTag = javadocTag;
    }

    public String getMethodJavadoc() {
        return methodJavadoc;
    }

    public void setMethodJavadoc(String methodJavadoc) {
        this.methodJavadoc = methodJavadoc;
    }

    public String getMethodSourceCode() {
        return methodSourceCode;
    }

    public void setMethodSourceCode(String methodSourceCode) {
        this.methodSourceCode = methodSourceCode;
    }

    public String getClassJavadoc() {
        return classJavadoc;
    }

    public void setClassJavadoc(String classJavadoc) {
        this.classJavadoc = classJavadoc;
    }

    public String getClassSourceCode() {
        return classSourceCode;
    }

    public void setClassSourceCode(String classSourceCode) {
        this.classSourceCode = classSourceCode;
    }

    public List<String> getTokensGeneralGrammar() {
        return tokensGeneralGrammar;
    }

    public void setTokensGeneralGrammar(List<String> tokensGeneralGrammar) {
        this.tokensGeneralGrammar = tokensGeneralGrammar;
    }

    public List<Pair<String, String>> getTokensGeneralValuesGlobalDictionary() {
        return tokensGeneralValuesGlobalDictionary;
    }

    public void setTokensGeneralValuesGlobalDictionary(List<Pair<String, String>> tokensGeneralValuesGlobalDictionary) {
        this.tokensGeneralValuesGlobalDictionary = tokensGeneralValuesGlobalDictionary;
    }

    public List<Pair<String, String>> getTokensProjectClasses() {
        return tokensProjectClasses;
    }

    public void setTokensProjectClasses(List<Pair<String, String>> tokensProjectClasses) {
        this.tokensProjectClasses = tokensProjectClasses;
    }

    public List<Quartet<String, String, String, String>> getTokensProjectClassesNonPrivateStaticNonVoidMethods() {
        return tokensProjectClassesNonPrivateStaticNonVoidMethods;
    }

    public void setTokensProjectClassesNonPrivateStaticNonVoidMethods(List<Quartet<String, String, String, String>> tokensProjectClassesNonPrivateStaticNonVoidMethods) {
        this.tokensProjectClassesNonPrivateStaticNonVoidMethods = tokensProjectClassesNonPrivateStaticNonVoidMethods;
    }

    public List<Quartet<String, String, String, String>> getTokensProjectClassesNonPrivateStaticAttributes() {
        return tokensProjectClassesNonPrivateStaticAttributes;
    }

    public void setTokensProjectClassesNonPrivateStaticAttributes(List<Quartet<String, String, String, String>> tokensProjectClassesNonPrivateStaticAttributes) {
        this.tokensProjectClassesNonPrivateStaticAttributes = tokensProjectClassesNonPrivateStaticAttributes;
    }

    public List<Pair<String, String>> getTokensMethodJavadocValues() {
        return tokensMethodJavadocValues;
    }

    public void setTokensMethodJavadocValues(List<Pair<String, String>> tokensMethodJavadocValues) {
        this.tokensMethodJavadocValues = tokensMethodJavadocValues;
    }

    public List<Triplet<String, String, String>> getTokensMethodArguments() {
        return tokensMethodArguments;
    }

    public void setTokensMethodArguments(List<Triplet<String, String, String>> tokensMethodArguments) {
        this.tokensMethodArguments = tokensMethodArguments;
    }

    public List<Quartet<String, String, String, String>> getTokensMethodVariablesNonPrivateNonStaticNonVoidMethods() {
        return tokensMethodVariablesNonPrivateNonStaticNonVoidMethods;
    }

    public void setTokensMethodVariablesNonPrivateNonStaticNonVoidMethods(List<Quartet<String, String, String, String>> tokensMethodVariablesNonPrivateNonStaticNonVoidMethods) {
        this.tokensMethodVariablesNonPrivateNonStaticNonVoidMethods = tokensMethodVariablesNonPrivateNonStaticNonVoidMethods;
    }

    public List<Quartet<String, String, String, String>> getTokensMethodVariablesNonPrivateNonStaticAttributes() {
        return tokensMethodVariablesNonPrivateNonStaticAttributes;
    }

    public void setTokensMethodVariablesNonPrivateNonStaticAttributes(List<Quartet<String, String, String, String>> tokensMethodVariablesNonPrivateNonStaticAttributes) {
        this.tokensMethodVariablesNonPrivateNonStaticAttributes = tokensMethodVariablesNonPrivateNonStaticAttributes;
    }

    public List<Quartet<String, String, String, String>> getTokensOracleVariablesNonPrivateNonStaticNonVoidMethods() {
        return tokensOracleVariablesNonPrivateNonStaticNonVoidMethods;
    }

    public void setTokensOracleVariablesNonPrivateNonStaticNonVoidMethods(List<Quartet<String, String, String, String>> tokensOracleVariablesNonPrivateNonStaticNonVoidMethods) {
        this.tokensOracleVariablesNonPrivateNonStaticNonVoidMethods = tokensOracleVariablesNonPrivateNonStaticNonVoidMethods;
    }

    public List<Quartet<String, String, String, String>> getTokensOracleVariablesNonPrivateNonStaticAttributes() {
        return tokensOracleVariablesNonPrivateNonStaticAttributes;
    }

    public void setTokensOracleVariablesNonPrivateNonStaticAttributes(List<Quartet<String, String, String, String>> tokensOracleVariablesNonPrivateNonStaticAttributes) {
        this.tokensOracleVariablesNonPrivateNonStaticAttributes = tokensOracleVariablesNonPrivateNonStaticAttributes;
    }
}
