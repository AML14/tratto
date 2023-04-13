package star.tratto.dataset.tokens;

import org.apache.poi.ss.usermodel.Row;
import star.tratto.dataset.oracles.OracleDatapoint;
import star.tratto.oracle.OracleType;

import java.util.List;

import static star.tratto.dataset.ExcelManager.subElementListToCell;

/**
 * Token containing the information as it is written to the tokens dataset
 */
public class TokenDatapoint {

    private Integer id;
    private Boolean label;
    private Integer oracleId;
    private OracleType oracleType;
    private String projectName;
    private String packageName;
    private String className;
    private String javadocTag;
    private String methodJavadoc;
    private String methodSourceCode;
    private String classJavadoc;
    private String classSourceCode;
    private String oracleSoFar;
    private String token;
    private String tokenClass;
    private List<String> tokenInfo;

    public TokenDatapoint(Integer id, Boolean label, OracleDatapoint oracleDatapoint, String oracleSoFar, String token, String tokenClass, List<String> tokenInfo) {
        this.id = id;
        this.label = label;
        this.oracleId = oracleDatapoint.getId();
        this.oracleType = oracleDatapoint.getOracleType();
        this.projectName = oracleDatapoint.getProjectName();
        this.packageName = oracleDatapoint.getPackageName();
        this.className = oracleDatapoint.getClassName();
        this.javadocTag = oracleDatapoint.getJavadocTag();
        this.methodJavadoc = oracleDatapoint.getMethodJavadoc();
        this.methodSourceCode = oracleDatapoint.getMethodSourceCode();
        this.classJavadoc = oracleDatapoint.getClassJavadoc();
        this.classSourceCode = oracleDatapoint.getClassSourceCode();
        this.oracleSoFar = oracleSoFar;
        this.token = token;
        this.tokenClass = tokenClass;
        this.tokenInfo = tokenInfo;
    }

    public static final List<String> ATTRIBUTES = List.of(
            "id",
            "label",
            "oracleId",
            "oracleType",
            "projectName",
            "packageName",
            "className",
            "javadocTag",
            "methodJavadoc",
            "methodSourceCode",
            "classJavadoc",
            "classSourceCode",
            "oracleSoFar",
            "token",
            "tokenClass",
            "tokenInfo"
    );

//    /**
//     * The tokenClass of a TokenDatapoint is usually the same as the TrattoGrammar rule of that token
//     * (e.g., "Semicolon"). However, for tokens representing vars (i.e., "someVarOrClassOrFieldOrMethod"),
//     * we need to differentiate them. We do so based on the column they come from in the oracles dataset,
//     * which represents the type of token (class, method, argument, etc.).
//     */
//    public static final Map<String, String> CONTEXTUAL_TOKENS_CLASSES = Map.of(
//            "tokensGeneralValuesGlobalDictionary", "GlobalDictionaryValue",
//            "tokensProjectClasses", "Class",
//            "tokensProjectClassesNonPrivateStaticNonVoidMethods", "MethodName",
//            "tokensProjectClassesNonPrivateStaticAttributes", "ClassField",
//            "tokensMethodJavadocValues", "MethodJavadocValue",
//            "tokensMethodArguments", "MethodArgument",
//            "tokensMethodVariablesNonPrivateNonStaticNonVoidMethods", "MethodName",
//            "tokensMethodVariablesNonPrivateNonStaticAttributes", "ClassField",
//            "tokensOracleVariablesNonPrivateNonStaticNonVoidMethods", "MethodName",
//            "tokensOracleVariablesNonPrivateNonStaticAttributes", "ClassField"
//    );

    /**
     * Modify input row to reflect the information contained in this TokenDatapoint
     */
    public void updateRow(Row row) {
        row.createCell(0).setCellValue(id);
        row.createCell(1).setCellValue(label);
        row.createCell(2).setCellValue(oracleId);
        row.createCell(3).setCellValue(oracleType.toString());
        row.createCell(4).setCellValue(projectName);
        row.createCell(5).setCellValue(packageName);
        row.createCell(6).setCellValue(className);
        row.createCell(7).setCellValue(javadocTag);
        row.createCell(8).setCellValue(methodJavadoc);
        row.createCell(9).setCellValue(methodSourceCode);
        row.createCell(10).setCellValue(classJavadoc);
        row.createCell(11).setCellValue(classSourceCode);
        row.createCell(12).setCellValue(oracleSoFar);
        row.createCell(13).setCellValue(token);
        row.createCell(14).setCellValue(tokenClass);
        row.createCell(15).setCellValue(subElementListToCell(tokenInfo));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getLabel() {
        return label;
    }

    public void setLabel(Boolean label) {
        this.label = label;
    }

    public Integer getOracleId() {
        return oracleId;
    }

    public void setOracleId(Integer oracleId) {
        this.oracleId = oracleId;
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

    public String getOracleSoFar() {
        return oracleSoFar;
    }

    public void setOracleSoFar(String oracleSoFar) {
        this.oracleSoFar = oracleSoFar;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenClass() {
        return tokenClass;
    }

    public void setTokenClass(String tokenClass) {
        this.tokenClass = tokenClass;
    }

    public List<String> getTokenInfo() {
        return tokenInfo;
    }

    public void setTokenInfo(List<String> tokenInfo) {
        this.tokenInfo = tokenInfo;
    }
}
