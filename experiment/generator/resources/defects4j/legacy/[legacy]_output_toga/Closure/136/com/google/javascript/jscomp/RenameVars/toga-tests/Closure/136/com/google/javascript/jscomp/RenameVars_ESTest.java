/*
 * This file was automatically generated by EvoSuite
 * Sun Nov 19 22:36:35 GMT 2023
 */
package com.google.javascript.jscomp;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.google.javascript.jscomp.AbstractCompiler;
import com.google.javascript.jscomp.Compiler;
import com.google.javascript.jscomp.CompilerInput;
import com.google.javascript.jscomp.CompilerOptions;
import com.google.javascript.jscomp.InlineGetters;
import com.google.javascript.jscomp.JSSourceFile;
import com.google.javascript.jscomp.JsAst;
import com.google.javascript.jscomp.LightweightMessageFormatter;
import com.google.javascript.jscomp.LoggerErrorManager;
import com.google.javascript.jscomp.NameAnonymousFunctionsMapped;
import com.google.javascript.jscomp.RenameVars;
import com.google.javascript.jscomp.SourceExcerptProvider;
import com.google.javascript.jscomp.SourceFile;
import com.google.javascript.jscomp.VariableMap;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.ScriptOrFnNode;
import com.google.javascript.rhino.jstype.JSType;
import com.google.javascript.rhino.jstype.JSTypeRegistry;
import java.io.File;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.logging.Logger;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.evosuite.runtime.mock.java.io.MockFile;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class RenameVars_ESTest extends RenameVars_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        Compiler compiler0 = new Compiler();
        CompilerOptions compilerOptions0 = compiler0.getOptions();
        char[] charArray0 = new char[11];
        NameAnonymousFunctionsMapped nameAnonymousFunctionsMapped0 = new NameAnonymousFunctionsMapped(compiler0);
        VariableMap variableMap0 = nameAnonymousFunctionsMapped0.getFunctionMap();
        RenameVars renameVars0 = new RenameVars(compiler0, (String) null, false, true, false, variableMap0, charArray0, compilerOptions0.aliasableStrings);
        JSSourceFile jSSourceFile0 = JSSourceFile.fromFile("", (Charset) null);
        RenameVars.ProcessVars renameVars_ProcessVars0 = renameVars0.new ProcessVars(false);
        CompilerInput compilerInput0 = new CompilerInput(jSSourceFile0);
        renameVars_ProcessVars0.incCount("", compilerInput0);
        Node node0 = Node.newString("wT8-q64:02hn!B!%");
        Node node1 = compiler0.parseTestCode("Q<Uz^QD8");
        renameVars0.process(node0, node1);
        renameVars0.process(node1, node1);
        node1.getChildCount();
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        Compiler compiler0 = new Compiler();
        CompilerOptions compilerOptions0 = compiler0.getOptions();
        char[] charArray0 = new char[40];
        RenameVars renameVars0 = new RenameVars(compiler0, (String) null, false, false, false, (VariableMap) null, charArray0, compilerOptions0.stripNamePrefixes);
        VariableMap variableMap0 = renameVars0.getVariableMap();
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        Compiler compiler0 = new Compiler();
        char[] charArray0 = new char[3];
        Locale locale0 = Locale.KOREA;
        Set<String> set0 = locale0.getUnicodeLocaleAttributes();
        RenameVars renameVars0 = new RenameVars(compiler0, "i", false, false, false, (VariableMap) null, charArray0, set0);
        MockFile mockFile0 = new MockFile(",2$80wZmRb9w", "`D,8Ez2tO#1\" j:");
        JSSourceFile jSSourceFile0 = JSSourceFile.fromFile((File) mockFile0);
        CompilerInput compilerInput0 = new CompilerInput(jSSourceFile0, false);
        RenameVars.Assignment renameVars_Assignment0 = renameVars0.new Assignment("i", compilerInput0);
        renameVars_Assignment0.setNewName("getParamCount");
        renameVars_Assignment0.setNewName(")=eJBK;9R");
    }

    @Test(timeout = 4000)
    public void test043() throws Throwable {
        Compiler compiler0 = new Compiler();
        CompilerOptions compilerOptions0 = compiler0.getOptions();
        char[] charArray0 = new char[30];
        RenameVars renameVars0 = new RenameVars(compiler0, (String) null, false, false, false, (VariableMap) null, charArray0, compilerOptions0.stripNamePrefixes);
        JSTypeRegistry jSTypeRegistry0 = compiler0.getTypeRegistry();
        JSType[] jSTypeArray0 = new JSType[1];
        Node node0 = jSTypeRegistry0.createParameters(jSTypeArray0);
        renameVars0.process(node0, node0);
    }

    @Test(timeout = 4000)
    public void test054() throws Throwable {
        Compiler compiler0 = new Compiler();
        CompilerOptions compilerOptions0 = new CompilerOptions();
        char[] charArray0 = new char[3];
        SourceFile.Preloaded sourceFile_Preloaded0 = new SourceFile.Preloaded("", (String) null, "is_dispatcher");
        JsAst jsAst0 = new JsAst(sourceFile_Preloaded0);
        RenameVars renameVars0 = new RenameVars(compiler0, (String) null, true, false, false, (VariableMap) null, charArray0, compilerOptions0.stripNameSuffixes);
        ScriptOrFnNode scriptOrFnNode0 = (ScriptOrFnNode) jsAst0.getAstRoot(compiler0);
        renameVars0.process(scriptOrFnNode0, scriptOrFnNode0);
        scriptOrFnNode0.getEndLineno();
        assertEquals(0, scriptOrFnNode0.getEndLineno());
    }

    @Test(timeout = 4000)
    public void test065() throws Throwable {
        Compiler compiler0 = new Compiler();
        CompilerOptions compilerOptions0 = compiler0.options_;
        char[] charArray0 = new char[6];
        SourceFile.Preloaded sourceFile_Preloaded0 = new SourceFile.Preloaded("", (String) null, "_R");
        JsAst jsAst0 = new JsAst(sourceFile_Preloaded0);
        RenameVars renameVars0 = new RenameVars(compiler0, "// Input %num%", false, false, false, (VariableMap) null, charArray0, compilerOptions0.stripNameSuffixes);
        Node node0 = jsAst0.getAstRoot(compiler0);
        renameVars0.process(node0, node0);
    }

    @Test(timeout = 4000)
    public void test076() throws Throwable {
        Compiler compiler0 = new Compiler();
        CompilerOptions compilerOptions0 = compiler0.getOptions();
        char[] charArray0 = new char[11];
        NameAnonymousFunctionsMapped nameAnonymousFunctionsMapped0 = new NameAnonymousFunctionsMapped(compiler0);
        VariableMap variableMap0 = nameAnonymousFunctionsMapped0.getFunctionMap();
        RenameVars renameVars0 = new RenameVars(compiler0, (String) null, false, true, false, variableMap0, charArray0, compilerOptions0.aliasableStrings);
        JSSourceFile jSSourceFile0 = JSSourceFile.fromFile("", (Charset) null);
        RenameVars.ProcessVars renameVars_ProcessVars0 = renameVars0.new ProcessVars(false);
        CompilerInput compilerInput0 = new CompilerInput(jSSourceFile0);
        renameVars_ProcessVars0.incCount("", compilerInput0);
        renameVars_ProcessVars0.incCount("", compilerInput0);
        Node node0 = Node.newString("wT8-q64:02hn!B!%");
        Node node1 = compiler0.parseTestCode("Q<Uz^QD8");
        renameVars_ProcessVars0.incCount(" =", compilerInput0);
        renameVars0.process(node1, node0);
        node1.getChildCount();
    }

    @Test(timeout = 4000)
    public void test087() throws Throwable {
        Compiler compiler0 = new Compiler();
        char[] charArray0 = new char[1];
        CompilerOptions compilerOptions0 = compiler0.getOptions();
        RenameVars renameVars0 = new RenameVars(compiler0, (String) null, false, true, false, (VariableMap) null, charArray0, compilerOptions0.stripTypePrefixes);
        JSSourceFile jSSourceFile0 = JSSourceFile.fromCode((String) null, (String) null);
        RenameVars.ProcessVars renameVars_ProcessVars0 = renameVars0.new ProcessVars(false);
        CompilerInput compilerInput0 = new CompilerInput(jSSourceFile0);
        renameVars_ProcessVars0.incCount("\uC911\uAD6D", compilerInput0);
        Node node0 = compiler0.parseTestCode("\uC911\uAD6D");
        renameVars0.process(node0, node0);
    }

    @Test(timeout = 4000)
    public void test098() throws Throwable {
        Compiler compiler0 = new Compiler();
        CompilerOptions compilerOptions0 = compiler0.createDefaultOptions();
        char[] charArray0 = new char[4];
        NameAnonymousFunctionsMapped nameAnonymousFunctionsMapped0 = new NameAnonymousFunctionsMapped(compiler0);
        VariableMap variableMap0 = nameAnonymousFunctionsMapped0.getFunctionMap();
        RenameVars renameVars0 = new RenameVars(compiler0, (String) null, false, false, false, variableMap0, charArray0, compilerOptions0.aliasableStrings);
        JSSourceFile jSSourceFile0 = JSSourceFile.fromFile("", (Charset) null);
        RenameVars.ProcessVars renameVars_ProcessVars0 = renameVars0.new ProcessVars(false);
        CompilerInput compilerInput0 = new CompilerInput(jSSourceFile0);
        renameVars_ProcessVars0.incCount("L Ncgcm>eFoPL+&>o%Ew", compilerInput0);
        ScriptOrFnNode scriptOrFnNode0 = (ScriptOrFnNode) compiler0.parseTestCode("");
        renameVars0.process(scriptOrFnNode0, scriptOrFnNode0);
        scriptOrFnNode0.getSourceName();
        assertNotNull(scriptOrFnNode0.getSourceName());
    }

    @Test(timeout = 4000)
    public void test109() throws Throwable {
        NameAnonymousFunctionsMapped nameAnonymousFunctionsMapped0 = new NameAnonymousFunctionsMapped((AbstractCompiler) null);
        VariableMap variableMap0 = nameAnonymousFunctionsMapped0.getFunctionMap();
        char[] charArray0 = new char[5];
        LinkedList<JSType> linkedList0 = new LinkedList<JSType>();
        LightweightMessageFormatter lightweightMessageFormatter0 = new LightweightMessageFormatter((SourceExcerptProvider) null);
        LoggerErrorManager loggerErrorManager0 = new LoggerErrorManager(lightweightMessageFormatter0, (Logger) null);
        Compiler compiler0 = new Compiler(loggerErrorManager0);
        JSTypeRegistry jSTypeRegistry0 = compiler0.getTypeRegistry();
        Node node0 = jSTypeRegistry0.createParametersWithVarArgs((List<JSType>) linkedList0);
        InlineGetters inlineGetters0 = new InlineGetters((AbstractCompiler) null);
        Set<String> set0 = inlineGetters0.externMethods;
        RenameVars renameVars0 = new RenameVars(compiler0, "DEFAULT", false, false, true, variableMap0, charArray0, set0);
        RenameVars.ProcessVars renameVars_ProcessVars0 = renameVars0.new ProcessVars(true);
        SourceFile.Generator sourceFile_Generator0 = mock(SourceFile.Generator.class, new ViolatedAssumptionAnswer());
        JSSourceFile jSSourceFile0 = JSSourceFile.fromGenerator("com.google.javascript.jscomp.RenameVars$Assignment", sourceFile_Generator0);
        CompilerInput compilerInput0 = new CompilerInput(jSSourceFile0);
        renameVars_ProcessVars0.incCount("DEFAULT", compilerInput0);
        renameVars_ProcessVars0.incCount("e w`ow!|v=\"n95", compilerInput0);
        renameVars0.process(node0, node0);
    }
}
