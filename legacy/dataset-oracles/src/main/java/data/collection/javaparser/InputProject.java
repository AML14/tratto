package data.collection.javaparser;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.LiteralExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;

/**
 * This class keeps the project related information and data collection settings.
 * Project related information: project name, project directory, project's source files path, and output data directory.
 * Data collection settings:
 * Collection of some information in class i.e., class name, fields and methods is default for each project.
 * Collection of some information such as class imports, field accesses, method calls, literal expressions,
 * and variable declaration expressions is optional.
 * The reason for making these items optional is we don't need to collect them for jdk libraries.
 */
public class InputProject {
    String projectName;
    File projectDir;
    File sourceFilesDir;
    File outputDataDir;

    Boolean collectFieldAccesses;
    Boolean collectMethodCalls;
    Boolean collectLiteralExprs;
    Boolean collectVariableDeclarationExprs;
    Boolean collectImportsOfSourceFiles;

    public InputProject(String projectName, File projectDir, File sourceFilesDir, File outputDataDir,
                        Boolean collectFieldAccesses, Boolean collectMethodCalls, Boolean collectLiteralExprs,
                        Boolean collectVariableDeclarationExprs, Boolean collectImportsOfSourceFiles) {
        setProjectName(projectName);
        setProjectDir(projectDir);
        setSourceFilesDir(sourceFilesDir);
        setOutputDataDir(outputDataDir);
        setCollectFieldAccesses(collectFieldAccesses);
        setCollectMethodCalls(collectMethodCalls);
        setCollectLiteralExprs(collectLiteralExprs);
        setCollectVariableDeclarationExprs(collectVariableDeclarationExprs);
        setCollectImportsOfSourceFiles(collectImportsOfSourceFiles);
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectDir(File projectDir) {
        this.projectDir = projectDir;
    }

    public File getProjectDir() {
        return projectDir;
    }

    public void setSourceFilesDir(File sourceFilesDir) {
        this.sourceFilesDir = sourceFilesDir;
    }

    public File getSourceFilesDir() {
        return sourceFilesDir;
    }

    public File getOutputDataDir() {
        return outputDataDir;
    }

    public void setOutputDataDir(File outputDataDir) {
        this.outputDataDir = outputDataDir;
    }

    public void setCollectFieldAccesses(Boolean collectFieldAccesses) {
        this.collectFieldAccesses = collectFieldAccesses;
    }

    public Boolean getCollectFieldAccesses() {
        return collectFieldAccesses;
    }

    public void setCollectMethodCalls(Boolean collectMethodCalls) {
        this.collectMethodCalls = collectMethodCalls;
    }

    public Boolean getCollectMethodCalls() {
        return collectMethodCalls;
    }

    public void setCollectLiteralExprs(Boolean collectLiteralExprs) {
        this.collectLiteralExprs = collectLiteralExprs;
    }

    public Boolean getCollectLiteralExprs() {
        return collectLiteralExprs;
    }

    public void setCollectVariableDeclarationExprs(Boolean collectVariableDeclarationExprs) {
        this.collectVariableDeclarationExprs = collectVariableDeclarationExprs;
    }

    public Boolean getCollectVariableDeclarationExprs() {
        return collectVariableDeclarationExprs;
    }

    public void setCollectImportsOfSourceFiles(Boolean collectImportsOfSourceFiles) {
        this.collectImportsOfSourceFiles = collectImportsOfSourceFiles;
    }

    public Boolean getCollectImportsOfSourceFiles() {
        return collectImportsOfSourceFiles;
    }

}
