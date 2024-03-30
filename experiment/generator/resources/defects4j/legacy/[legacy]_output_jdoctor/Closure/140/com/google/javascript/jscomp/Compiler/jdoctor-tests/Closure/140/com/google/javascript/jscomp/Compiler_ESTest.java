/*
 * This file was automatically generated by EvoSuite
 * Sat Nov 04 06:47:29 GMT 2023
 */
package com.google.javascript.jscomp;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.google.common.base.Supplier;
import com.google.javascript.jscomp.CheckAccessControls;
import com.google.javascript.jscomp.CodeChangeHandler;
import com.google.javascript.jscomp.Compiler;
import com.google.javascript.jscomp.CompilerInput;
import com.google.javascript.jscomp.CompilerOptions;
import com.google.javascript.jscomp.CssRenamingMap;
import com.google.javascript.jscomp.ErrorManager;
import com.google.javascript.jscomp.FunctionInformationMap;
import com.google.javascript.jscomp.JSError;
import com.google.javascript.jscomp.JSModule;
import com.google.javascript.jscomp.JSSourceFile;
import com.google.javascript.jscomp.JsAst;
import com.google.javascript.jscomp.LoggerErrorManager;
import com.google.javascript.jscomp.PassConfig;
import com.google.javascript.jscomp.PropertyRenamingPolicy;
import com.google.javascript.jscomp.Region;
import com.google.javascript.jscomp.Result;
import com.google.javascript.jscomp.ReverseAbstractInterpreter;
import com.google.javascript.jscomp.Scope;
import com.google.javascript.jscomp.ScopeCreator;
import com.google.javascript.jscomp.SourceFile;
import com.google.javascript.jscomp.SourceMap;
import com.google.javascript.jscomp.Tracer;
import com.google.javascript.jscomp.VariableMap;
import com.google.javascript.rhino.Node;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.evosuite.runtime.mock.java.io.MockPrintStream;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class Compiler_ESTest extends Compiler_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        Compiler compiler0 = new Compiler();
        JSSourceFile[] jSSourceFileArray0 = new JSSourceFile[0];
        CompilerOptions compilerOptions0 = new CompilerOptions();
        compiler0.compile(jSSourceFileArray0, jSSourceFileArray0, compilerOptions0);
        compiler0.compile(jSSourceFileArray0, jSSourceFileArray0, compilerOptions0);
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        Compiler compiler0 = new Compiler();
        JSModule jSModule0 = new JSModule("ul2>|E*M!Z&&");
        compiler0.toSource(jSModule0);
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        Compiler compiler0 = new Compiler();
        compiler0.toSourceArray();
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        Compiler compiler0 = new Compiler();
        compiler0.toSource();
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        Compiler compiler0 = new Compiler();
        Compiler.CodeBuilder compiler_CodeBuilder0 = new Compiler.CodeBuilder();
        Node node0 = new Node((-53));
        compiler0.toSource(compiler_CodeBuilder0, (-53), node0);
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        Compiler compiler0 = new Compiler();
        JSModule jSModule0 = new JSModule("b`(r|;PI");
        compiler0.toSourceArray(jSModule0);
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        Compiler compiler0 = new Compiler();
        compiler0.normalize();
    }

    @Test(timeout = 4000)
    public void test077() throws Throwable {
        Compiler compiler0 = new Compiler();
        CompilerOptions compilerOptions0 = new CompilerOptions();
        compiler0.stripCode(compilerOptions0.aliasableStrings, compilerOptions0.stripTypes, compilerOptions0.stripNamePrefixes, compilerOptions0.stripTypePrefixes);
    }

    @Test(timeout = 4000)
    public void test108() throws Throwable {
        Compiler compiler0 = new Compiler();
        JSSourceFile jSSourceFile0 = JSSourceFile.fromFile("r@tfHZG; $d6");
        JSSourceFile[] jSSourceFileArray0 = new JSSourceFile[0];
        JSSourceFile[] jSSourceFileArray1 = new JSSourceFile[2];
        jSSourceFileArray1[0] = jSSourceFile0;
        jSSourceFileArray1[1] = jSSourceFile0;
        CompilerOptions compilerOptions0 = new CompilerOptions();
        compiler0.compile(jSSourceFileArray0, jSSourceFileArray1, compilerOptions0);
        compiler0.parse();
        compiler0.getErrorCount();
    }

    @Test(timeout = 4000)
    public void test109() throws Throwable {
        Compiler compiler0 = new Compiler();
        JSSourceFile jSSourceFile0 = JSSourceFile.fromFile("r@tfHZG; $d6");
        JSSourceFile[] jSSourceFileArray0 = new JSSourceFile[0];
        JSSourceFile[] jSSourceFileArray1 = new JSSourceFile[2];
        jSSourceFileArray1[0] = jSSourceFile0;
        jSSourceFileArray1[1] = jSSourceFile0;
        CompilerOptions compilerOptions0 = new CompilerOptions();
        compiler0.compile(jSSourceFileArray0, jSSourceFileArray1, compilerOptions0);
        compiler0.parse();
        compiler0.hasErrors();
    }

    @Test(timeout = 4000)
    public void test1110() throws Throwable {
        Level level0 = Level.SEVERE;
        Compiler.setLoggingLevel(level0);
        level0.getName();
    }

    @Test(timeout = 4000)
    public void test1211() throws Throwable {
        Compiler compiler0 = new Compiler();
        compiler0.getCssRenamingMap();
    }

    @Test(timeout = 4000)
    public void test1312() throws Throwable {
        Compiler compiler0 = new Compiler();
        compiler0.parseSyntheticCode("7}", "7}");
        compiler0.getErrorCount();
    }

    @Test(timeout = 4000)
    public void test1613() throws Throwable {
        Compiler compiler0 = new Compiler((PrintStream) null);
        ScopeCreator scopeCreator0 = compiler0.getScopeCreator();
    }

    @Test(timeout = 4000)
    public void test1714() throws Throwable {
        Compiler compiler0 = new Compiler();
        compiler0.rebuildInputsFromModules();
    }

    @Test(timeout = 4000)
    public void test1815() throws Throwable {
        Compiler compiler0 = new Compiler();
        boolean boolean0 = compiler0.precheck();
    }

    @Test(timeout = 4000)
    public void test1916() throws Throwable {
        Compiler compiler0 = new Compiler();
        compiler0.computeCFG();
    }

    @Test(timeout = 4000)
    public void test2017() throws Throwable {
        Compiler compiler0 = new Compiler();
        boolean boolean0 = compiler0.isNormalized();
    }

    @Test(timeout = 4000)
    public void test2118() throws Throwable {
        Compiler compiler0 = new Compiler();
        VariableMap variableMap0 = compiler0.getPropertyMap();
    }

    @Test(timeout = 4000)
    public void test2219() throws Throwable {
        Compiler compiler0 = new Compiler();
        compiler0.parseSyntheticCode((String) null);
    }

    @Test(timeout = 4000)
    public void test2320() throws Throwable {
        Compiler compiler0 = new Compiler();
        compiler0.processDefines();
    }

    @Test(timeout = 4000)
    public void test2421() throws Throwable {
        Compiler compiler0 = new Compiler();
        compiler0.getErrorManager();
        compiler0.optimize();
    }

    @Test(timeout = 4000)
    public void test2622() throws Throwable {
        Compiler compiler0 = new Compiler();
        compiler0.setCssRenamingMap((CssRenamingMap) null);
    }

    @Test(timeout = 4000)
    public void test2723() throws Throwable {
        Compiler compiler0 = new Compiler();
        compiler0.getWarningCount();
    }

    @Test(timeout = 4000)
    public void test2824() throws Throwable {
        Compiler compiler0 = new Compiler();
        VariableMap variableMap0 = compiler0.getVariableMap();
    }

    @Test(timeout = 4000)
    public void test2925() throws Throwable {
        Compiler compiler0 = new Compiler((PrintStream) null);
        FunctionInformationMap functionInformationMap0 = compiler0.getFunctionalInformationMap();
    }

    @Test(timeout = 4000)
    public void test3026() throws Throwable {
        Compiler compiler0 = new Compiler();
        Supplier<String> supplier0 = compiler0.getUniqueNameIdSupplier();
    }

    @Test(timeout = 4000)
    public void test3127() throws Throwable {
        Compiler compiler0 = new Compiler();
        compiler0.recordFunctionInformation();
    }

    @Test(timeout = 4000)
    public void test3228() throws Throwable {
        Compiler compiler0 = new Compiler();
        compiler0.toSource((Node) null);
    }

    @Test(timeout = 4000)
    public void test3329() throws Throwable {
        Compiler compiler0 = new Compiler();
        SourceMap sourceMap0 = compiler0.getSourceMap();
    }

    @Test(timeout = 4000)
    public void test3430() throws Throwable {
        Compiler compiler0 = new Compiler();
        compiler0.isTypeCheckingEnabled();
    }

    @Test(timeout = 4000)
    public void test3531() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.getRoot();
    }

    @Test(timeout = 4000)
    public void test3732() throws Throwable {
        Logger logger0 = Tracer.logger;
        LoggerErrorManager loggerErrorManager0 = new LoggerErrorManager(logger0);
        Compiler compiler0 = new Compiler(loggerErrorManager0);
        compiler0.getErrorCount();
    }

    @Test(timeout = 4000)
    public void test3833() throws Throwable {
        Compiler compiler0 = new Compiler();
        JSSourceFile jSSourceFile0 = JSSourceFile.fromCode("r@t<HZG;!$d6", "}s");
        compiler0.compile(jSSourceFile0, (JSModule[]) null, (CompilerOptions) null);
    }

    @Test(timeout = 4000)
    public void test3934() throws Throwable {
        Compiler compiler0 = new Compiler();
        compiler0.getMessages();
    }

    @Test(timeout = 4000)
    public void test4035() throws Throwable {
        Compiler compiler0 = new Compiler();
        Scope scope0 = compiler0.getTopScope();
    }

    @Test(timeout = 4000)
    public void test4136() throws Throwable {
        Compiler.CodeBuilder compiler_CodeBuilder0 = new Compiler.CodeBuilder();
        int int0 = compiler_CodeBuilder0.getLength();
    }

    @Test(timeout = 4000)
    public void test4237() throws Throwable {
        Compiler.CodeBuilder compiler_CodeBuilder0 = new Compiler.CodeBuilder();
        String string0 = compiler_CodeBuilder0.toString();
    }

    @Test(timeout = 4000)
    public void test4338() throws Throwable {
        Compiler.CodeBuilder compiler_CodeBuilder0 = new Compiler.CodeBuilder();
        int int0 = compiler_CodeBuilder0.getLineIndex();
    }

    @Test(timeout = 4000)
    public void test4439() throws Throwable {
        Compiler.CodeBuilder compiler_CodeBuilder0 = new Compiler.CodeBuilder();
        compiler_CodeBuilder0.reset();
        compiler_CodeBuilder0.toString();
    }

    @Test(timeout = 4000)
    public void test4540() throws Throwable {
        Compiler compiler0 = new Compiler();
        compiler0.acquireSymbolTable();
        compiler0.acquireSymbolTable();
    }

    @Test(timeout = 4000)
    public void test4641() throws Throwable {
        MockPrintStream mockPrintStream0 = new MockPrintStream("value");
        Compiler compiler0 = new Compiler(mockPrintStream0);
        compiler0.initCompilerOptionsIfTesting();
        compiler0.getErrorCount();
    }

    @Test(timeout = 4000)
    public void test4742() throws Throwable {
        Compiler compiler0 = new Compiler();
        CompilerOptions compilerOptions0 = new CompilerOptions();
        compilerOptions0.sourceMapOutputPath = "M,a8X>)j8";
        JSSourceFile jSSourceFile0 = JSSourceFile.fromFile("Creating extern file for exports", (Charset) null);
        compiler0.compile(jSSourceFile0, jSSourceFile0, compilerOptions0);
        compiler0.getErrorCount();
    }

    @Test(timeout = 4000)
    public void test4843() throws Throwable {
        Compiler compiler0 = new Compiler();
        CompilerOptions compilerOptions0 = new CompilerOptions();
        JSSourceFile[] jSSourceFileArray0 = new JSSourceFile[0];
        JSModule[] jSModuleArray0 = new JSModule[0];
        compiler0.compile(jSSourceFileArray0, jSModuleArray0, compilerOptions0);
        compiler0.hasErrors();
    }

    @Test(timeout = 4000)
    public void test4944() throws Throwable {
        Compiler compiler0 = new Compiler();
        compiler0.parseTestCode("u?>t]k");
        JSModule jSModule0 = new JSModule("u?>t]k");
        CompilerOptions compilerOptions0 = compiler0.getOptions();
        JSSourceFile jSSourceFile0 = JSSourceFile.fromCode("", "_?.u)>y!P&@MZ");
        JsAst jsAst0 = new JsAst(jSSourceFile0);
        CompilerInput compilerInput0 = new CompilerInput(jsAst0);
        jSModule0.addFirst(compilerInput0);
        JSSourceFile[] jSSourceFileArray0 = new JSSourceFile[5];
        JSModule[] jSModuleArray0 = new JSModule[7];
        jSModuleArray0[0] = jSModule0;
        compiler0.compile(jSSourceFileArray0, jSModuleArray0, compilerOptions0);
    }

    @Test(timeout = 4000)
    public void test5045() throws Throwable {
        Compiler compiler0 = new Compiler();
        JSSourceFile[] jSSourceFileArray0 = new JSSourceFile[0];
        CompilerOptions compilerOptions0 = new CompilerOptions();
        JSModule jSModule0 = new JSModule((String) null);
        JSModule[] jSModuleArray0 = new JSModule[1];
        jSModuleArray0[0] = jSModule0;
        compiler0.compile(jSSourceFileArray0, jSModuleArray0, compilerOptions0);
        compiler0.hasErrors();
    }

    @Test(timeout = 4000)
    public void test5146() throws Throwable {
        Compiler compiler0 = new Compiler();
        compiler0.parseTestCode("u?>t]k");
        JSModule jSModule0 = new JSModule("u?>t]k");
        CompilerOptions compilerOptions0 = compiler0.getOptions();
        JSSourceFile jSSourceFile0 = JSSourceFile.fromCode("", "_?.u)>y!P&@MZ");
        JsAst jsAst0 = new JsAst(jSSourceFile0);
        CompilerInput compilerInput0 = new CompilerInput(jsAst0);
        jSModule0.addFirst(compilerInput0);
        JSSourceFile[] jSSourceFileArray0 = new JSSourceFile[5];
        jSSourceFileArray0[0] = jSSourceFile0;
        jSSourceFileArray0[1] = jSSourceFile0;
        jSSourceFileArray0[2] = jSSourceFile0;
        jSSourceFileArray0[3] = jSSourceFile0;
        jSSourceFileArray0[4] = jSSourceFile0;
        JSModule[] jSModuleArray0 = new JSModule[7];
        jSModuleArray0[0] = jSModule0;
        jSModuleArray0[1] = jSModule0;
        jSModuleArray0[2] = jSModule0;
        jSModuleArray0[3] = jSModule0;
        jSModuleArray0[4] = jSModule0;
        jSModuleArray0[5] = jSModule0;
        jSModuleArray0[6] = jSModule0;
        compiler0.compile(jSSourceFileArray0, jSModuleArray0, compilerOptions0);
        compiler0.getErrorCount();
    }

    @Test(timeout = 4000)
    public void test5247() throws Throwable {
        Compiler compiler0 = new Compiler();
        JSSourceFile[] jSSourceFileArray0 = new JSSourceFile[0];
        CompilerOptions compilerOptions0 = new CompilerOptions();
        JSModule jSModule0 = new JSModule((String) null);
        JSSourceFile jSSourceFile0 = JSSourceFile.fromCode("]!eUC4{EZ4o", (String) null);
        JsAst jsAst0 = new JsAst(jSSourceFile0);
        CompilerInput compilerInput0 = new CompilerInput(jsAst0);
        jSModule0.addFirst(compilerInput0);
        JSModule[] jSModuleArray0 = new JSModule[1];
        jSModuleArray0[0] = jSModule0;
        compiler0.compile(jSSourceFileArray0, jSModuleArray0, compilerOptions0);
    }

    @Test(timeout = 4000)
    public void test5348() throws Throwable {
        Compiler compiler0 = new Compiler();
        JSSourceFile[] jSSourceFileArray0 = new JSSourceFile[0];
        CompilerOptions compilerOptions0 = new CompilerOptions();
        compiler0.compile(jSSourceFileArray0, jSSourceFileArray0, compilerOptions0);
        JSModule[] jSModuleArray0 = new JSModule[0];
        compiler0.compile(jSSourceFileArray0, jSModuleArray0, compilerOptions0);
    }

    @Test(timeout = 4000)
    public void test5549() throws Throwable {
        Compiler compiler0 = new Compiler();
        PassConfig passConfig0 = compiler0.getPassConfig();
        compiler0.setPassConfig(passConfig0);
    }

    @Test(timeout = 4000)
    public void test5650() throws Throwable {
        Compiler compiler0 = new Compiler();
        compiler0.getErrorManager();
        compiler0.check();
    }

    @Test(timeout = 4000)
    public void test5751() throws Throwable {
        Compiler compiler0 = new Compiler();
        compiler0.initCompilerOptionsIfTesting();
        compiler0.startPass((String) null);
        compiler0.startPass((String) null);
    }

    @Test(timeout = 4000)
    public void test5852() throws Throwable {
        Compiler compiler0 = new Compiler();
        compiler0.endPass();
    }

    @Test(timeout = 4000)
    public void test5953() throws Throwable {
        Compiler compiler0 = new Compiler();
        compiler0.reportCodeChange();
        compiler0.getErrorManager();
        compiler0.parse();
    }

    @Test(timeout = 4000)
    public void test6054() throws Throwable {
        Compiler compiler0 = new Compiler();
        JSSourceFile[] jSSourceFileArray0 = new JSSourceFile[0];
        CompilerOptions compilerOptions0 = new CompilerOptions();
        CompilerOptions.TracerMode compilerOptions_TracerMode0 = CompilerOptions.TracerMode.FAST;
        compilerOptions0.tracer = compilerOptions_TracerMode0;
        Result result0 = compiler0.compile(jSSourceFileArray0, jSSourceFileArray0, compilerOptions0);
    }

    @Test(timeout = 4000)
    public void test6155() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.parseTestCode("com.google.common.collect.Iterators$3");
        boolean boolean0 = compiler0.areNodesEqualForInlining(node0, node0);
    }

    @Test(timeout = 4000)
    public void test6256() throws Throwable {
        Compiler compiler0 = new Compiler();
        JSSourceFile[] jSSourceFileArray0 = new JSSourceFile[0];
        CompilerOptions compilerOptions0 = new CompilerOptions();
        compiler0.compile(jSSourceFileArray0, jSSourceFileArray0, compilerOptions0);
        CompilerInput compilerInput0 = compiler0.newExternInput((String) null);
        compilerInput0.isExtern();
    }

    @Test(timeout = 4000)
    public void test6357() throws Throwable {
        Compiler compiler0 = new Compiler();
        CompilerOptions compilerOptions0 = new CompilerOptions();
        JSSourceFile[] jSSourceFileArray0 = new JSSourceFile[4];
        JSSourceFile jSSourceFile0 = JSSourceFile.fromCode("// Input %num%", (String) null);
        jSSourceFileArray0[0] = jSSourceFile0;
        JSSourceFile jSSourceFile1 = JSSourceFile.fromCode((String) null, (String) null);
        jSSourceFileArray0[1] = jSSourceFile1;
        jSSourceFileArray0[2] = jSSourceFile0;
        jSSourceFileArray0[3] = jSSourceFileArray0[2];
        compiler0.compile(jSSourceFileArray0, jSSourceFileArray0, compilerOptions0);
        compiler0.newExternInput((String) null);
    }

    @Test(timeout = 4000)
    public void test6458() throws Throwable {
        Compiler compiler0 = new Compiler();
        JSSourceFile jSSourceFile0 = JSSourceFile.fromCode("u?>t]k", "'?0l.vA$cU?Vki&K");
        CompilerOptions compilerOptions0 = new CompilerOptions();
        compiler0.compile(jSSourceFile0, jSSourceFile0, compilerOptions0);
        JsAst jsAst0 = new JsAst(jSSourceFile0);
        compiler0.addIncrementalSourceAst(jsAst0);
    }

    @Test(timeout = 4000)
    public void test6559() throws Throwable {
        Compiler compiler0 = new Compiler();
        JSSourceFile[] jSSourceFileArray0 = new JSSourceFile[0];
        CompilerOptions compilerOptions0 = new CompilerOptions();
        compiler0.compile(jSSourceFileArray0, jSSourceFileArray0, compilerOptions0);
        SourceFile sourceFile0 = SourceFile.fromCode((String) null, "");
        JsAst jsAst0 = new JsAst(sourceFile0);
        compiler0.addIncrementalSourceAst(jsAst0);
        compiler0.getWarningCount();
    }

    @Test(timeout = 4000)
    public void test6660() throws Throwable {
        Compiler compiler0 = new Compiler();
        compiler0.getErrorManager();
        ReverseAbstractInterpreter reverseAbstractInterpreter0 = compiler0.getReverseAbstractInterpreter();
        ReverseAbstractInterpreter reverseAbstractInterpreter1 = compiler0.getReverseAbstractInterpreter();
    }

    @Test(timeout = 4000)
    public void test6761() throws Throwable {
        Compiler compiler0 = new Compiler();
        JSSourceFile[] jSSourceFileArray0 = new JSSourceFile[0];
        CompilerOptions compilerOptions0 = new CompilerOptions();
        compiler0.compile(jSSourceFileArray0, jSSourceFileArray0, compilerOptions0);
        compiler0.parse();
        compiler0.isIdeMode();
    }

    @Test(timeout = 4000)
    public void test6862() throws Throwable {
        Compiler compiler0 = new Compiler();
        CompilerOptions compilerOptions0 = new CompilerOptions();
        JSSourceFile jSSourceFile0 = JSSourceFile.fromFile("Creating extern file for exports", (Charset) null);
        compiler0.compile(jSSourceFile0, jSSourceFile0, compilerOptions0);
        compiler0.parse();
        compiler0.getErrorCount();
    }

    @Test(timeout = 4000)
    public void test6963() throws Throwable {
        Compiler.CodeBuilder compiler_CodeBuilder0 = new Compiler.CodeBuilder();
        Compiler.CodeBuilder compiler_CodeBuilder1 = compiler_CodeBuilder0.append("Normalize constraints violated:\n");
        int int0 = compiler_CodeBuilder1.getColumnIndex();
    }

    @Test(timeout = 4000)
    public void test7064() throws Throwable {
        Compiler.CodeBuilder compiler_CodeBuilder0 = new Compiler.CodeBuilder();
        int int0 = compiler_CodeBuilder0.getColumnIndex();
    }

    @Test(timeout = 4000)
    public void test7165() throws Throwable {
        Compiler.CodeBuilder compiler_CodeBuilder0 = new Compiler.CodeBuilder();
        boolean boolean0 = compiler_CodeBuilder0.endsWith("w**AwnWa8Qk+|>2");
    }

    @Test(timeout = 4000)
    public void test7266() throws Throwable {
        Compiler.CodeBuilder compiler_CodeBuilder0 = new Compiler.CodeBuilder();
        Compiler.CodeBuilder compiler_CodeBuilder1 = compiler_CodeBuilder0.append("r9ri.j6RkjxK8]E");
        Compiler.CodeBuilder compiler_CodeBuilder2 = compiler_CodeBuilder1.append("\"");
        boolean boolean0 = compiler_CodeBuilder2.endsWith("r9ri.j6RkjxK8]E");
    }

    @Test(timeout = 4000)
    public void test7367() throws Throwable {
        Compiler.CodeBuilder compiler_CodeBuilder0 = new Compiler.CodeBuilder();
        compiler_CodeBuilder0.append("d{/X");
        boolean boolean0 = compiler_CodeBuilder0.endsWith("");
    }

    @Test(timeout = 4000)
    public void test7468() throws Throwable {
        Compiler compiler0 = new Compiler();
        JSSourceFile[] jSSourceFileArray0 = new JSSourceFile[0];
        CompilerOptions compilerOptions0 = new CompilerOptions();
        PropertyRenamingPolicy propertyRenamingPolicy0 = PropertyRenamingPolicy.HEURISTIC;
        compilerOptions0.propertyRenaming = propertyRenamingPolicy0;
        compiler0.compile(jSSourceFileArray0, jSSourceFileArray0, compilerOptions0);
        boolean boolean0 = compiler0.isInliningForbidden();
    }

    @Test(timeout = 4000)
    public void test7569() throws Throwable {
        Compiler compiler0 = new Compiler();
        compiler0.initCompilerOptionsIfTesting();
        boolean boolean0 = compiler0.isInliningForbidden();
    }

    @Test(timeout = 4000)
    public void test7670() throws Throwable {
        Compiler compiler0 = new Compiler();
        compiler0.parseTestCode("com.google.common.collect.Iterators$3");
        Node node0 = compiler0.parseTestCode("com.google.common.collect.Iterators$3");
        node0.getCharno();
    }

    @Test(timeout = 4000)
    public void test7771() throws Throwable {
        Compiler compiler0 = new Compiler();
        JSSourceFile[] jSSourceFileArray0 = new JSSourceFile[0];
        CompilerOptions compilerOptions0 = new CompilerOptions();
        compiler0.compile(jSSourceFileArray0, jSSourceFileArray0, compilerOptions0);
        JSError jSError0 = JSError.make((String) null, (-1305), (-1305), compilerOptions0.checkMethods, compiler0.OPTIMIZE_LOOP_ERROR, (String[]) null);
        compiler0.report(jSError0);
        compiler0.isIdeMode();
    }

    @Test(timeout = 4000)
    public void test7872() throws Throwable {
        Compiler compiler0 = new Compiler();
        compiler0.throwInternalError("Duplicate input: {0}", (Exception) null);
    }

    @Test(timeout = 4000)
    public void test7973() throws Throwable {
        Compiler compiler0 = new Compiler();
        CompilerOptions compilerOptions0 = new CompilerOptions();
        JSSourceFile[] jSSourceFileArray0 = new JSSourceFile[0];
        compilerOptions0.ideMode = true;
        Result result0 = compiler0.compile(jSSourceFileArray0, jSSourceFileArray0, compilerOptions0);
    }

    @Test(timeout = 4000)
    public void test8074() throws Throwable {
        Compiler compiler0 = new Compiler();
        Region region0 = compiler0.getSourceRegion("/Users/elliottzackrone/IdeaProjects/defects4jprefix/Recording function information/Recording function information", (-1645));
    }

    @Test(timeout = 4000)
    public void test8175() throws Throwable {
        Compiler compiler0 = new Compiler();
        JSSourceFile[] jSSourceFileArray0 = new JSSourceFile[0];
        CompilerOptions compilerOptions0 = new CompilerOptions();
        compiler0.compile(jSSourceFileArray0, jSSourceFileArray0, compilerOptions0);
        Region region0 = compiler0.getSourceRegion("", 301);
    }

    @Test(timeout = 4000)
    public void test8276() throws Throwable {
        Compiler compiler0 = new Compiler();
        CompilerOptions compilerOptions0 = new CompilerOptions();
        JSSourceFile[] jSSourceFileArray0 = new JSSourceFile[1];
        SourceFile.Generator sourceFile_Generator0 = mock(SourceFile.Generator.class, new ViolatedAssumptionAnswer());
        doReturn((String) null).when(sourceFile_Generator0).getCode();
        JSSourceFile jSSourceFile0 = JSSourceFile.fromGenerator((String) null, sourceFile_Generator0);
        jSSourceFileArray0[0] = jSSourceFile0;
        compiler0.compile(jSSourceFileArray0, jSSourceFileArray0, compilerOptions0);
        compiler0.getSourceRegion((String) null, 7);
    }

    @Test(timeout = 4000)
    public void test8377() throws Throwable {
        Compiler compiler0 = new Compiler();
        JSModule jSModule0 = new JSModule((String) null);
        compiler0.getNodeForCodeInsertion(jSModule0);
    }

    @Test(timeout = 4000)
    public void test8478() throws Throwable {
        Compiler compiler0 = new Compiler();
        CompilerOptions compilerOptions0 = new CompilerOptions();
        JSSourceFile jSSourceFile0 = JSSourceFile.fromFile("", (Charset) null);
        JSSourceFile[] jSSourceFileArray0 = new JSSourceFile[1];
        jSSourceFileArray0[0] = jSSourceFile0;
        compiler0.compile(jSSourceFileArray0, jSSourceFileArray0, compilerOptions0);
        compiler0.getNodeForCodeInsertion((JSModule) null);
        compiler0.getErrorCount();
    }

    @Test(timeout = 4000)
    public void test8579() throws Throwable {
        Compiler compiler0 = new Compiler();
        JSSourceFile[] jSSourceFileArray0 = new JSSourceFile[0];
        CompilerOptions compilerOptions0 = new CompilerOptions();
        compiler0.compile(jSSourceFileArray0, jSSourceFileArray0, compilerOptions0);
        compiler0.getNodeForCodeInsertion((JSModule) null);
    }

    @Test(timeout = 4000)
    public void test8680() throws Throwable {
        Compiler compiler0 = new Compiler();
        JSModule jSModule0 = new JSModule((String) null);
        JSSourceFile jSSourceFile0 = JSSourceFile.fromFile("n");
        jSModule0.addFirst(jSSourceFile0);
        compiler0.getNodeForCodeInsertion(jSModule0);
    }

    @Test(timeout = 4000)
    public void test8781() throws Throwable {
        Compiler compiler0 = new Compiler();
        String string0 = compiler0.getAstDotGraph();
    }

    @Test(timeout = 4000)
    public void test8882() throws Throwable {
        Compiler compiler0 = new Compiler();
        JSSourceFile[] jSSourceFileArray0 = new JSSourceFile[0];
        CompilerOptions compilerOptions0 = new CompilerOptions();
        compiler0.compile(jSSourceFileArray0, jSSourceFileArray0, compilerOptions0);
        String string0 = compiler0.getAstDotGraph();
    }

    @Test(timeout = 4000)
    public void test8983() throws Throwable {
        Compiler compiler0 = new Compiler();
        compiler0.initCompilerOptionsIfTesting();
        ErrorManager errorManager0 = compiler0.getErrorManager();
        errorManager0.getWarningCount();
    }
}
