package star.tratto.data;

import star.tratto.data.OracleDatapoint;
import star.tratto.oracle.OracleType;

import java.util.List;

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
    private List<String> tokenClassesSoFar;
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
        this.tokenClassesSoFar = null;
        this.token = token;
        this.tokenClass = tokenClass;
        this.tokenInfo = tokenInfo;
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

    public List<String> getTokenClassesSoFar() {
        return tokenClassesSoFar;
    }

    public void setTokenClassesSoFar(List<String> tokenClassesSoFar) {
        this.tokenClassesSoFar = tokenClassesSoFar;
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
