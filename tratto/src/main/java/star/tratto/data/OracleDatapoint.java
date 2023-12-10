package star.tratto.data;

import star.tratto.data.records.AttributeTokens;
import star.tratto.data.records.ClassTokens;
import star.tratto.data.records.ValueTokens;
import star.tratto.data.records.MethodArgumentTokens;
import star.tratto.data.records.MethodTokens;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This class represents an individual datapoint in the Oracles Dataset. Each
 * OracleDatapoint corresponds to an assertion that must be true (the oracle)
 * based on a JavaDoc tag. Each JavaDoc has a corresponding method, with a
 * declaring class, in a corresponding project.
 */
public class OracleDatapoint {
    /** A unique identifier for each oracle datapoint. */
    private Integer id;
    /** A boolean expression corresponding to a test assertion. */
    private String oracle;
    /** The type of oracle (e.g. pre-condition, post-condition, exceptional condition). */
    private OracleType oracleType;
    /** The name of the project being analyzed. */
    private String projectName;
    /** The package name of the class under analysis. */
    private String packageName;
    /** The name of the class under analysis. */
    private String className;
    /** The Javadoc tag corresponding to the oracle expression. */
    private String javadocTag;
    /** The Javadoc of the method under analysis. */
    private String methodJavadoc;
    /** The source code of the method under analysis. */
    private String methodSourceCode;
    /** The Javadoc of the class under analysis. */
    private String classJavadoc;
    /** The source code of the class under analysis. */
    private String classSourceCode;
    /** Default tokens for the XText grammar (see "./repos/tokens_grammar.json"). */
    private List<String> tokensGeneralGrammar;
    /** Default (token, type) pairs for the XText grammar. */
    private List<ValueTokens> tokensGeneralValuesGlobalDictionary;
    /** All classes in the Java project (className, packageName). */
    private List<ClassTokens> tokensProjectClasses;
    /** All non-private, static methods in the Java project (methodName, packageName, className, methodSignature). */
    private List<MethodTokens> tokensProjectClassesNonPrivateStaticNonVoidMethods;
    /** All non-private, static attributes in the Java project (attributeName, packageName, className, attributeDeclaration). */
    private List<AttributeTokens> tokensProjectClassesNonPrivateStaticAttributes;
    /** All values in the corresponding method Javadoc (value, type). */
    private List<ValueTokens> tokensMethodJavadocValues;
    /** All arguments of the corresponding method (argumentName, packageName, className). */
    private List<MethodArgumentTokens> tokensMethodArguments;
    /** All non-private, non-static, non-void methods from:
     *  (1) the declaring class,
     *  (2) each method argument,
     *  (3) the method return type
     *  (methodName, packageName, className, methodSignature)
     */
    private List<MethodTokens> tokensMethodVariablesNonPrivateNonStaticNonVoidMethods;
    /** All non-private, non-static, attributes from:
     *  (1) the declaring class,
     *  (2) each method argument,
     *  (3) the method return type
     * (attributeName, packageName, className, attributeDeclaration)
     */
    private List<AttributeTokens> tokensMethodVariablesNonPrivateNonStaticAttributes;
    /** All non-private, non-static, non-void method from the return type of each possible oracle sub-expression. */
    private List<MethodTokens> tokensOracleVariablesNonPrivateNonStaticNonVoidMethods;
    /** All non-private, non-static attributes from the return type of each possible oracle sub-expression. */
    private List<AttributeTokens> tokensOracleVariablesNonPrivateNonStaticAttributes;

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
        this.tokensGeneralValuesGlobalDictionary = ((List<List<String>>) oracleDatapointMap.get("tokensGeneralValuesGlobalDictionary")).stream().map(ValueTokens::new).toList();
        this.tokensProjectClasses = ((List<List<String>>) oracleDatapointMap.get("tokensProjectClasses")).stream().map(ClassTokens::new).toList();
        this.tokensProjectClassesNonPrivateStaticNonVoidMethods = ((List<List<String>>) oracleDatapointMap.get("tokensProjectClassesNonPrivateStaticNonVoidMethods")).stream().map(MethodTokens::new).toList();
        this.tokensProjectClassesNonPrivateStaticAttributes = ((List<List<String>>) oracleDatapointMap.get("tokensProjectClassesNonPrivateStaticAttributes")).stream().map(AttributeTokens::new).toList();
        this.tokensMethodJavadocValues = ((List<List<String>>) oracleDatapointMap.get("tokensMethodJavadocValues")).stream().map(ValueTokens::new).toList();
        this.tokensMethodArguments = ((List<List<String>>) oracleDatapointMap.get("tokensMethodArguments")).stream().map(MethodArgumentTokens::new).toList();
        this.tokensMethodVariablesNonPrivateNonStaticNonVoidMethods = ((List<List<String>>) oracleDatapointMap.get("tokensMethodVariablesNonPrivateNonStaticNonVoidMethods")).stream().map(MethodTokens::new).toList();
        this.tokensMethodVariablesNonPrivateNonStaticAttributes = ((List<List<String>>) oracleDatapointMap.get("tokensMethodVariablesNonPrivateNonStaticAttributes")).stream().map(AttributeTokens::new).toList();
        this.tokensOracleVariablesNonPrivateNonStaticNonVoidMethods = ((List<List<String>>) oracleDatapointMap.get("tokensOracleVariablesNonPrivateNonStaticNonVoidMethods")).stream().map(MethodTokens::new).toList();
        this.tokensOracleVariablesNonPrivateNonStaticAttributes = ((List<List<String>>) oracleDatapointMap.get("tokensOracleVariablesNonPrivateNonStaticAttributes")).stream().map(AttributeTokens::new).toList();
    }

    public OracleDatapoint(
            Integer id,
            String oracle,
            OracleType oracleType,
            String projectName,
            String packageName,
            String className,
            String javadocTag,
            String methodJavadoc,
            String methodSourceCode,
            String classJavadoc,
            String classSourceCode,
            List<String> tokensGeneralGrammar,
            List<ValueTokens> tokensGeneralValuesGlobalDictionary,
            List<ClassTokens> tokensProjectClasses,
            List<MethodTokens> tokensProjectClassesNonPrivateStaticNonVoidMethods,
            List<AttributeTokens> tokensProjectClassesNonPrivateStaticAttributes,
            List<ValueTokens> tokensMethodJavadocValues,
            List<MethodArgumentTokens> tokensMethodArguments,
            List<MethodTokens> tokensMethodVariablesNonPrivateNonStaticNonVoidMethods,
            List<AttributeTokens> tokensMethodVariablesNonPrivateNonStaticAttributes,
            List<MethodTokens> tokensOracleVariablesNonPrivateNonStaticNonVoidMethods,
            List<AttributeTokens> tokensOracleVariablesNonPrivateNonStaticAttributes
    ) {
        this.id = id;
        this.oracle = oracle;
        this.oracleType = oracleType;
        this.projectName = projectName;
        this.packageName = packageName;
        this.className = className;
        this.javadocTag = javadocTag;
        this.methodJavadoc = methodJavadoc;
        this.methodSourceCode = methodSourceCode;
        this.classJavadoc = classJavadoc;
        this.classSourceCode = classSourceCode;
        this.tokensGeneralGrammar = tokensGeneralGrammar;
        this.tokensGeneralValuesGlobalDictionary = tokensGeneralValuesGlobalDictionary;
        this.tokensProjectClasses = tokensProjectClasses;
        this.tokensProjectClassesNonPrivateStaticNonVoidMethods = tokensProjectClassesNonPrivateStaticNonVoidMethods;
        this.tokensProjectClassesNonPrivateStaticAttributes = tokensProjectClassesNonPrivateStaticAttributes;
        this.tokensMethodJavadocValues = tokensMethodJavadocValues;
        this.tokensMethodArguments = tokensMethodArguments;
        this.tokensMethodVariablesNonPrivateNonStaticNonVoidMethods = tokensMethodVariablesNonPrivateNonStaticNonVoidMethods;
        this.tokensMethodVariablesNonPrivateNonStaticAttributes = tokensMethodVariablesNonPrivateNonStaticAttributes;
        this.tokensOracleVariablesNonPrivateNonStaticNonVoidMethods = tokensOracleVariablesNonPrivateNonStaticNonVoidMethods;
        this.tokensOracleVariablesNonPrivateNonStaticAttributes = tokensOracleVariablesNonPrivateNonStaticAttributes;
    }

    /**
     * Converts the OracleDatapoint to a Map with Lists. This is useful for later
     * conversion to JSON, as the records must be exported as lists.
     */
    public Map toMapAndLists() {
        Map oracleDatapointMap = new LinkedHashMap();
        oracleDatapointMap.put("id", id);
        oracleDatapointMap.put("oracle", oracle);
        oracleDatapointMap.put("oracleType", oracleType);
        oracleDatapointMap.put("projectName", projectName);
        oracleDatapointMap.put("packageName", packageName);
        oracleDatapointMap.put("className", className);
        oracleDatapointMap.put("javadocTag", javadocTag);
        oracleDatapointMap.put("methodJavadoc", methodJavadoc);
        oracleDatapointMap.put("methodSourceCode", methodSourceCode);
        oracleDatapointMap.put("classJavadoc", classJavadoc);
        oracleDatapointMap.put("classSourceCode", classSourceCode);
        oracleDatapointMap.put("tokensGeneralGrammar", tokensGeneralGrammar);
        oracleDatapointMap.put("tokensGeneralValuesGlobalDictionary", tokensGeneralValuesGlobalDictionary.stream().map(ValueTokens::toList).collect(Collectors.toList()));
        oracleDatapointMap.put("tokensProjectClasses", tokensProjectClasses.stream().map(ClassTokens::toList).collect(Collectors.toList()));
        oracleDatapointMap.put("tokensProjectClassesNonPrivateStaticNonVoidMethods", tokensProjectClassesNonPrivateStaticNonVoidMethods.stream().map(MethodTokens::toList).collect(Collectors.toList()));
        oracleDatapointMap.put("tokensProjectClassesNonPrivateStaticAttributes", tokensProjectClassesNonPrivateStaticAttributes.stream().map(AttributeTokens::toList).collect(Collectors.toList()));
        oracleDatapointMap.put("tokensMethodJavadocValues", tokensMethodJavadocValues.stream().map(ValueTokens::toList).collect(Collectors.toList()));
        oracleDatapointMap.put("tokensMethodArguments", tokensMethodArguments.stream().map(MethodArgumentTokens::toList).collect(Collectors.toList()));
        oracleDatapointMap.put("tokensMethodVariablesNonPrivateNonStaticNonVoidMethods", tokensMethodVariablesNonPrivateNonStaticNonVoidMethods.stream().map(MethodTokens::toList).collect(Collectors.toList()));
        oracleDatapointMap.put("tokensMethodVariablesNonPrivateNonStaticAttributes", tokensMethodVariablesNonPrivateNonStaticAttributes.stream().map(AttributeTokens::toList).collect(Collectors.toList()));
        oracleDatapointMap.put("tokensOracleVariablesNonPrivateNonStaticNonVoidMethods", tokensOracleVariablesNonPrivateNonStaticNonVoidMethods.stream().map(MethodTokens::toList).collect(Collectors.toList()));
        oracleDatapointMap.put("tokensOracleVariablesNonPrivateNonStaticAttributes", tokensOracleVariablesNonPrivateNonStaticAttributes.stream().map(AttributeTokens::toList).collect(Collectors.toList()));
        return oracleDatapointMap;
    }

    public OracleDatapoint() {
    }

    public boolean isProjectClass(String clazz) {
        return tokensProjectClasses.stream().anyMatch(projectClass -> projectClass.className().equals(clazz));
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

    public List<ValueTokens> getTokensGeneralValuesGlobalDictionary() {
        return tokensGeneralValuesGlobalDictionary;
    }

    public void setTokensGeneralValuesGlobalDictionary(List<ValueTokens> tokensGeneralValuesGlobalDictionary) {
        this.tokensGeneralValuesGlobalDictionary = tokensGeneralValuesGlobalDictionary;
    }

    public List<ClassTokens> getTokensProjectClasses() {
        return tokensProjectClasses;
    }

    public void setTokensProjectClasses(List<ClassTokens> tokensProjectClasses) {
        this.tokensProjectClasses = tokensProjectClasses;
    }

    public List<MethodTokens> getTokensProjectClassesNonPrivateStaticNonVoidMethods() {
        return tokensProjectClassesNonPrivateStaticNonVoidMethods;
    }

    public void setTokensProjectClassesNonPrivateStaticNonVoidMethods(List<MethodTokens> tokensProjectClassesNonPrivateStaticNonVoidMethods) {
        this.tokensProjectClassesNonPrivateStaticNonVoidMethods = tokensProjectClassesNonPrivateStaticNonVoidMethods;
    }

    public List<AttributeTokens> getTokensProjectClassesNonPrivateStaticAttributes() {
        return tokensProjectClassesNonPrivateStaticAttributes;
    }

    public void setTokensProjectClassesNonPrivateStaticAttributes(List<AttributeTokens> tokensProjectClassesNonPrivateStaticAttributes) {
        this.tokensProjectClassesNonPrivateStaticAttributes = tokensProjectClassesNonPrivateStaticAttributes;
    }

    public List<ValueTokens> getTokensMethodJavadocValues() {
        return tokensMethodJavadocValues;
    }

    public void setTokensMethodJavadocValues(List<ValueTokens> tokensMethodJavadocValues) {
        this.tokensMethodJavadocValues = tokensMethodJavadocValues;
    }

    public List<MethodArgumentTokens> getTokensMethodArguments() {
        return tokensMethodArguments;
    }

    public void setTokensMethodArguments(List<MethodArgumentTokens> tokensMethodArguments) {
        this.tokensMethodArguments = tokensMethodArguments;
    }

    public List<MethodTokens> getTokensMethodVariablesNonPrivateNonStaticNonVoidMethods() {
        return tokensMethodVariablesNonPrivateNonStaticNonVoidMethods;
    }

    public void setTokensMethodVariablesNonPrivateNonStaticNonVoidMethods(List<MethodTokens> tokensMethodVariablesNonPrivateNonStaticNonVoidMethods) {
        this.tokensMethodVariablesNonPrivateNonStaticNonVoidMethods = tokensMethodVariablesNonPrivateNonStaticNonVoidMethods;
    }

    public List<AttributeTokens> getTokensMethodVariablesNonPrivateNonStaticAttributes() {
        return tokensMethodVariablesNonPrivateNonStaticAttributes;
    }

    public void setTokensMethodVariablesNonPrivateNonStaticAttributes(List<AttributeTokens> tokensMethodVariablesNonPrivateNonStaticAttributes) {
        this.tokensMethodVariablesNonPrivateNonStaticAttributes = tokensMethodVariablesNonPrivateNonStaticAttributes;
    }

    public List<MethodTokens> getTokensOracleVariablesNonPrivateNonStaticNonVoidMethods() {
        return tokensOracleVariablesNonPrivateNonStaticNonVoidMethods;
    }

    public void setTokensOracleVariablesNonPrivateNonStaticNonVoidMethods(List<MethodTokens> tokensOracleVariablesNonPrivateNonStaticNonVoidMethods) {
        this.tokensOracleVariablesNonPrivateNonStaticNonVoidMethods = tokensOracleVariablesNonPrivateNonStaticNonVoidMethods;
    }

    public List<AttributeTokens> getTokensOracleVariablesNonPrivateNonStaticAttributes() {
        return tokensOracleVariablesNonPrivateNonStaticAttributes;
    }

    public void setTokensOracleVariablesNonPrivateNonStaticAttributes(List<AttributeTokens> tokensOracleVariablesNonPrivateNonStaticAttributes) {
        this.tokensOracleVariablesNonPrivateNonStaticAttributes = tokensOracleVariablesNonPrivateNonStaticAttributes;
    }
}
