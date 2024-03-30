/*
 * This file was automatically generated by EvoSuite
 * Sat Nov 04 06:55:55 GMT 2023
 */
package com.google.javascript.jscomp;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.google.javascript.jscomp.AbstractCommandLineRunner;
import com.google.javascript.jscomp.CommandLineRunner;
import com.google.javascript.jscomp.Compiler;
import com.google.javascript.jscomp.CompilerOptions;
import com.google.javascript.jscomp.FunctionInformationMap;
import com.google.javascript.jscomp.JSModule;
import com.google.javascript.jscomp.JSSourceFile;
import com.google.javascript.jscomp.PhaseOptimizer;
import com.google.javascript.jscomp.Result;
import com.google.javascript.jscomp.SourceMap;
import com.google.javascript.jscomp.VariableMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Stack;
import java.util.Vector;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.System;
import org.evosuite.runtime.mock.java.io.MockPrintStream;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class AbstractCommandLineRunner_ESTest extends AbstractCommandLineRunner_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        String[] stringArray0 = new String[0];
        CommandLineRunner commandLineRunner0 = new CommandLineRunner(stringArray0);
        Compiler compiler0 = commandLineRunner0.createCompiler();
        JSSourceFile jSSourceFile0 = JSSourceFile.fromCode("", "");
        CompilerOptions compilerOptions0 = new CompilerOptions();
        Result result0 = compiler0.compile(jSSourceFile0, jSSourceFile0, compilerOptions0);
        JSModule[] jSModuleArray0 = new JSModule[0];
        Result result1 = new Result(result0.warnings, result0.warnings, (String) null, (VariableMap) null, (VariableMap) null, (VariableMap) null, (FunctionInformationMap) null, (SourceMap) null, (String) null, (Map<String, Integer>) null);
        int int0 = commandLineRunner0.processResults(result1, jSModuleArray0, compilerOptions0);
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        String[] stringArray0 = new String[0];
        CommandLineRunner commandLineRunner0 = new CommandLineRunner(stringArray0);
        CompilerOptions compilerOptions0 = commandLineRunner0.createOptions();
        commandLineRunner0.setRunOptions(compilerOptions0);
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        String[] stringArray0 = new String[0];
        CommandLineRunner commandLineRunner0 = new CommandLineRunner(stringArray0);
        Compiler compiler0 = commandLineRunner0.getCompiler();
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        String[] stringArray0 = new String[0];
        CommandLineRunner.main(stringArray0);
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        List<String> list0 = PhaseOptimizer.OPTIMAL_ORDER;
        AbstractCommandLineRunner.createJsModules(list0, list0);
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        AbstractCommandLineRunner.createJsModules((List<String>) null, (List<String>) null);
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        Stack<String> stack0 = new Stack<String>();
        AbstractCommandLineRunner.createJsModules(stack0, stack0);
    }

    @Test(timeout = 4000)
    public void test077() throws Throwable {
        List<String> list0 = ResourceBundle.Control.FORMAT_DEFAULT;
        AbstractCommandLineRunner.createJsModules(list0, (List<String>) null);
    }

    @Test(timeout = 4000)
    public void test088() throws Throwable {
        Stack<String> stack0 = new Stack<String>();
        stack0.add("--deine flag syntax invalid: ");
        AbstractCommandLineRunner.createJsModules(stack0, stack0);
    }

    @Test(timeout = 4000)
    public void test099() throws Throwable {
        Vector<String> vector0 = new Vector<String>();
        vector0.add("Bax:otR^F'J");
        AbstractCommandLineRunner.createJsModules(vector0, vector0);
    }

    @Test(timeout = 4000)
    public void test1010() throws Throwable {
        AbstractCommandLineRunner.parseModuleWrappers((List<String>) null, (JSModule[]) null);
    }

    @Test(timeout = 4000)
    public void test1111() throws Throwable {
        Stack<String> stack0 = new Stack<String>();
        JSModule[] jSModuleArray0 = new JSModule[10];
        AbstractCommandLineRunner.parseModuleWrappers(stack0, jSModuleArray0);
    }

    @Test(timeout = 4000)
    public void test1212() throws Throwable {
        List<String> list0 = PhaseOptimizer.OPTIMAL_ORDER;
        JSModule[] jSModuleArray0 = new JSModule[0];
        AbstractCommandLineRunner.parseModuleWrappers(list0, jSModuleArray0);
    }

    @Test(timeout = 4000)
    public void test1313() throws Throwable {
        Stack<String> stack0 = new Stack<String>();
        stack0.add("ugQ:a|:XP<");
        JSModule[] jSModuleArray0 = new JSModule[0];
        AbstractCommandLineRunner.parseModuleWrappers(stack0, jSModuleArray0);
    }

    @Test(timeout = 4000)
    public void test1414() throws Throwable {
        MockPrintStream mockPrintStream0 = new MockPrintStream("W;)sq!`2Ob[\"");
        Compiler compiler0 = new Compiler();
        AbstractCommandLineRunner.writeOutput(mockPrintStream0, compiler0, "W;)sq!`2Ob[\"", "W;)sq!`2Ob[\"", "3R}\"#GIfQ.");
    }

    @Test(timeout = 4000)
    public void test1515() throws Throwable {
        MockPrintStream mockPrintStream0 = new MockPrintStream("u;");
        Compiler compiler0 = new Compiler();
        AbstractCommandLineRunner.writeOutput(mockPrintStream0, compiler0, "u;", "Bad --externs flag. ", ".");
    }

    @Test(timeout = 4000)
    public void test1616() throws Throwable {
        MockPrintStream mockPrintStream0 = new MockPrintStream("{");
        Compiler compiler0 = new Compiler();
        AbstractCommandLineRunner.writeOutput(mockPrintStream0, compiler0, "{", "{", "{");
    }

    @Test(timeout = 4000)
    public void test1717() throws Throwable {
        MockPrintStream mockPrintStream0 = new MockPrintStream("TYPE_BOOL");
        AbstractCommandLineRunner.writeOutput(mockPrintStream0, (Compiler) null, "TYPE_BOOL", "TYPE_BOOL", "TYPE_BOOL");
    }

    @Test(timeout = 4000)
    public void test1818() throws Throwable {
        String[] stringArray0 = new String[0];
        CommandLineRunner commandLineRunner0 = new CommandLineRunner(stringArray0);
        Compiler compiler0 = new Compiler();
        JSSourceFile jSSourceFile0 = JSSourceFile.fromCode("", "");
        CompilerOptions compilerOptions0 = new CompilerOptions();
        Result result0 = compiler0.compile(jSSourceFile0, jSSourceFile0, compilerOptions0);
        JSModule[] jSModuleArray0 = new JSModule[0];
        int int0 = commandLineRunner0.processResults(result0, jSModuleArray0, compilerOptions0);
    }

    @Test(timeout = 4000)
    public void test1919() throws Throwable {
        String[] stringArray0 = new String[0];
        CommandLineRunner commandLineRunner0 = new CommandLineRunner(stringArray0);
        Compiler compiler0 = new Compiler();
        JSSourceFile jSSourceFile0 = JSSourceFile.fromCode("p", "p");
        CompilerOptions compilerOptions0 = new CompilerOptions();
        Result result0 = compiler0.compile(jSSourceFile0, jSSourceFile0, compilerOptions0);
        Result result1 = new Result(result0.warnings, result0.errors, (String) null, (VariableMap) null, (VariableMap) null, (VariableMap) null, (FunctionInformationMap) null, (SourceMap) null, "", (Map<String, Integer>) null);
        commandLineRunner0.processResults(result1, (JSModule[]) null, compilerOptions0);
    }

    @Test(timeout = 4000)
    public void test2020() throws Throwable {
        Vector<String> vector0 = new Vector<String>();
        vector0.add("");
        CompilerOptions compilerOptions0 = new CompilerOptions();
        AbstractCommandLineRunner.createDefineReplacements(vector0, compilerOptions0);
    }

    @Test(timeout = 4000)
    public void test2121() throws Throwable {
        List<String> list0 = ResourceBundle.Control.FORMAT_CLASS;
        CompilerOptions compilerOptions0 = new CompilerOptions();
        AbstractCommandLineRunner.createDefineReplacements(list0, compilerOptions0);
    }
}
