/*
 * This file was automatically generated by EvoSuite
 * Sat Nov 04 05:30:04 GMT 2023
 */
package com.google.javascript.jscomp;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.google.javascript.jscomp.CheckLevel;
import com.google.javascript.jscomp.Compiler;
import com.google.javascript.jscomp.CompilerOptions;
import com.google.javascript.jscomp.NodeTraversal;
import com.google.javascript.jscomp.Normalize;
import com.google.javascript.jscomp.PreprocessorSymbolTable;
import com.google.javascript.jscomp.ProcessClosurePrimitives;
import com.google.javascript.jscomp.SyntheticAst;
import com.google.javascript.rhino.Node;
import java.util.Set;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class ProcessClosurePrimitives_ESTest extends ProcessClosurePrimitives_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test00() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = Normalize.parseAndNormalizeTestCode(compiler0, "goog.base");
        CheckLevel checkLevel0 = CheckLevel.WARNING;
        PreprocessorSymbolTable preprocessorSymbolTable0 = new PreprocessorSymbolTable(node0);
        ProcessClosurePrimitives processClosurePrimitives0 = new ProcessClosurePrimitives(compiler0, preprocessorSymbolTable0, checkLevel0);
        processClosurePrimitives0.process(node0, node0);
        compiler0.getErrorCount();
    }

    @Test(timeout = 4000)
    public void test01() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = Normalize.parseAndNormalizeTestCode(compiler0, "goog.base");
        CheckLevel checkLevel0 = CheckLevel.WARNING;
        PreprocessorSymbolTable preprocessorSymbolTable0 = new PreprocessorSymbolTable(node0);
        ProcessClosurePrimitives processClosurePrimitives0 = new ProcessClosurePrimitives(compiler0, preprocessorSymbolTable0, checkLevel0);
        processClosurePrimitives0.process(node0, node0);
        compiler0.hasErrors();
    }

    @Test(timeout = 4000)
    public void test12() throws Throwable {
        Compiler compiler0 = new Compiler();
        compiler0.newCompilerOptions();
        Normalize.parseAndNormalizeTestCode(compiler0, "// Input %num%");
    }

    @Test(timeout = 4000)
    public void test23() throws Throwable {
        Compiler compiler0 = new Compiler();
        SyntheticAst syntheticAst0 = new SyntheticAst("setCssNameMapping");
        Node node0 = syntheticAst0.getAstRoot(compiler0);
        PreprocessorSymbolTable preprocessorSymbolTable0 = new PreprocessorSymbolTable(node0);
        CheckLevel checkLevel0 = CheckLevel.WARNING;
        ProcessClosurePrimitives processClosurePrimitives0 = new ProcessClosurePrimitives(compiler0, preprocessorSymbolTable0, checkLevel0);
        Set<String> set0 = processClosurePrimitives0.getExportedVariableNames();
        set0.isEmpty();
    }

    @Test(timeout = 4000)
    public void test34() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = Normalize.parseAndNormalizeTestCode(compiler0, "JSC_NON_STRING_PASSED_TO_SET_CSS_NAME_MAPPING_ERROR");
        CheckLevel checkLevel0 = CheckLevel.OFF;
        ProcessClosurePrimitives processClosurePrimitives0 = new ProcessClosurePrimitives(compiler0, (PreprocessorSymbolTable) null, checkLevel0);
        processClosurePrimitives0.process(node0, node0);
        node0.getLineno();
    }

    @Test(timeout = 4000)
    public void test45() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = new Node(37, 37, 37);
        Node node1 = new Node(37, node0, node0, node0);
        PreprocessorSymbolTable preprocessorSymbolTable0 = new PreprocessorSymbolTable(node0);
        CheckLevel checkLevel0 = CheckLevel.ERROR;
        ProcessClosurePrimitives processClosurePrimitives0 = new ProcessClosurePrimitives(compiler0, preprocessorSymbolTable0, checkLevel0);
        NodeTraversal nodeTraversal0 = new NodeTraversal(compiler0, processClosurePrimitives0);
        processClosurePrimitives0.visit(nodeTraversal0, node1, node0);
        node1.hasMoreThanOneChild();
    }

    @Test(timeout = 4000)
    public void test56() throws Throwable {
        Compiler compiler0 = new Compiler();
        CompilerOptions compilerOptions0 = compiler0.newCompilerOptions();
        Node node0 = new Node(105);
        ProcessClosurePrimitives processClosurePrimitives0 = new ProcessClosurePrimitives(compiler0, (PreprocessorSymbolTable) null, compilerOptions0.brokenClosureRequiresLevel);
        NodeTraversal nodeTraversal0 = new NodeTraversal(compiler0, processClosurePrimitives0);
        Node node1 = new Node(43, node0, node0, node0, 0, 1);
        processClosurePrimitives0.visit(nodeTraversal0, node0, node0);
        node0.getChildCount();
    }

    @Test(timeout = 4000)
    public void test67() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = Normalize.parseAndNormalizeTestCode(compiler0, "msg.invalid.iterator");
        CheckLevel checkLevel0 = CheckLevel.ERROR;
        PreprocessorSymbolTable preprocessorSymbolTable0 = new PreprocessorSymbolTable(node0);
        ProcessClosurePrimitives processClosurePrimitives0 = new ProcessClosurePrimitives(compiler0, preprocessorSymbolTable0, checkLevel0);
        processClosurePrimitives0.process(node0, node0);
        node0.isGetElem();
    }

    @Test(timeout = 4000)
    public void test78() throws Throwable {
        Compiler compiler0 = new Compiler();
        CompilerOptions compilerOptions0 = compiler0.newCompilerOptions();
        Node node0 = new Node(86);
        ProcessClosurePrimitives processClosurePrimitives0 = new ProcessClosurePrimitives(compiler0, (PreprocessorSymbolTable) null, compilerOptions0.checkMissingReturn);
        NodeTraversal nodeTraversal0 = new NodeTraversal(compiler0, processClosurePrimitives0);
        processClosurePrimitives0.visit(nodeTraversal0, node0, node0);
    }
}
