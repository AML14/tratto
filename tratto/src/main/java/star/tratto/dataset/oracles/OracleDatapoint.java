package star.tratto.dataset.oracles;

import org.apache.poi.ss.usermodel.Row;
import org.javatuples.Pair;
import org.javatuples.Quartet;
import org.javatuples.Triplet;
import star.tratto.oracle.OracleType;

import java.util.List;
import java.util.stream.Collectors;

import static star.tratto.dataset.ExcelManager.*;

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

    public OracleDatapoint(Row oraclesDatasetRow) {
        this.id = (int) parseNumericCell(oraclesDatasetRow, 0);
        this.oracle = parseStringCell(oraclesDatasetRow, 1);
        this.oracleType = OracleType.valueOf(parseStringCell(oraclesDatasetRow, 2));
        this.projectName = parseStringCell(oraclesDatasetRow, 3);
        this.packageName = parseStringCell(oraclesDatasetRow, 4);
        this.className = parseStringCell(oraclesDatasetRow, 5);
        this.javadocTag = parseStringCell(oraclesDatasetRow, 6);
        this.methodJavadoc = parseStringCell(oraclesDatasetRow, 7);
        this.methodSourceCode = parseStringCell(oraclesDatasetRow, 8);
        this.classJavadoc = parseStringCell(oraclesDatasetRow, 9);
        this.classSourceCode = parseStringCell(oraclesDatasetRow, 10);
        this.tokensGeneralGrammar = parseMultiDataCell(oraclesDatasetRow, 11).stream().map(tuple -> (String) tuple.getValue(0)).collect(Collectors.toList());
        this.tokensGeneralValuesGlobalDictionary = (List<Pair<String, String>>) parseMultiDataCell(oraclesDatasetRow, 12);
        this.tokensProjectClasses = (List<Pair<String, String>>) parseMultiDataCell(oraclesDatasetRow, 13);
        this.tokensProjectClassesNonPrivateStaticNonVoidMethods = (List<Quartet<String, String, String, String>>) parseMultiDataCell(oraclesDatasetRow, 14);
        this.tokensProjectClassesNonPrivateStaticAttributes = (List<Quartet<String, String, String, String>>) parseMultiDataCell(oraclesDatasetRow, 15);
        this.tokensMethodJavadocValues = (List<Pair<String, String>>) parseMultiDataCell(oraclesDatasetRow, 16);
        this.tokensMethodArguments = (List<Triplet<String, String, String>>) parseMultiDataCell(oraclesDatasetRow, 17);
        this.tokensMethodVariablesNonPrivateNonStaticNonVoidMethods = (List<Quartet<String, String, String, String>>) parseMultiDataCell(oraclesDatasetRow, 18);
        this.tokensMethodVariablesNonPrivateNonStaticAttributes = (List<Quartet<String, String, String, String>>) parseMultiDataCell(oraclesDatasetRow, 19);
        this.tokensOracleVariablesNonPrivateNonStaticNonVoidMethods = (List<Quartet<String, String, String, String>>) parseMultiDataCell(oraclesDatasetRow, 20);
        this.tokensOracleVariablesNonPrivateNonStaticAttributes = (List<Quartet<String, String, String, String>>) parseMultiDataCell(oraclesDatasetRow, 21);
    }

    public OracleDatapoint() {
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

    public void setTokensMethodVariablesNonPrivateNonStaticAttributes(List<Quartet<String, String, String, String>> tokensMethodVariablesNonPrivateNonStaticAttrributes) {
        this.tokensMethodVariablesNonPrivateNonStaticAttributes = tokensMethodVariablesNonPrivateNonStaticAttrributes;
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
