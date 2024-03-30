/*
 * This file was automatically generated by EvoSuite
 * Sat Nov 04 04:31:11 GMT 2023
 */
package com.google.javascript.jscomp;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.google.javascript.jscomp.CheckLevel;
import com.google.javascript.jscomp.Compiler;
import com.google.javascript.jscomp.NodeTraversal;
import com.google.javascript.jscomp.ProcessClosurePrimitives;
import com.google.javascript.jscomp.StrictModeCheck;
import com.google.javascript.rhino.Node;
import java.util.Set;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class ProcessClosurePrimitives_ESTest extends ProcessClosurePrimitives_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.parseTestCode("goog.base");
        CheckLevel checkLevel0 = CheckLevel.ERROR;
        ProcessClosurePrimitives processClosurePrimitives0 = new ProcessClosurePrimitives(compiler0, checkLevel0, true);
        processClosurePrimitives0.process(node0, node0);
        compiler0.hasErrors();
    }

    @Test(timeout = 4000)
    public void test001() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.parseTestCode("goog.base");
        CheckLevel checkLevel0 = CheckLevel.ERROR;
        ProcessClosurePrimitives processClosurePrimitives0 = new ProcessClosurePrimitives(compiler0, checkLevel0, true);
        processClosurePrimitives0.process(node0, node0);
        compiler0.getErrorCount();
    }

    @Test(timeout = 4000)
    public void test012() throws Throwable {
        Compiler compiler0 = new Compiler();
        CheckLevel checkLevel0 = CheckLevel.WARNING;
        ProcessClosurePrimitives processClosurePrimitives0 = new ProcessClosurePrimitives(compiler0, checkLevel0, false);
        Set<String> set0 = processClosurePrimitives0.getExportedVariableNames();
        set0.isEmpty();
    }

    @Test(timeout = 4000)
    public void test023() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = new Node(30);
        CheckLevel checkLevel0 = CheckLevel.OFF;
        ProcessClosurePrimitives processClosurePrimitives0 = new ProcessClosurePrimitives(compiler0, checkLevel0, false);
        processClosurePrimitives0.process(node0, node0);
    }

    @Test(timeout = 4000)
    public void test034() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.parseTestCode("msgno.brac=et");
        CheckLevel checkLevel0 = CheckLevel.WARNING;
        Node node1 = new Node(37, node0, node0, 1, 0);
        ProcessClosurePrimitives processClosurePrimitives0 = new ProcessClosurePrimitives(compiler0, checkLevel0, true);
        processClosurePrimitives0.process((Node) null, node1);
    }

    @Test(timeout = 4000)
    public void test045() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = new Node(37, 37, 37);
        node0.addChildToBack(node0);
        CheckLevel checkLevel0 = CheckLevel.OFF;
        ProcessClosurePrimitives processClosurePrimitives0 = new ProcessClosurePrimitives(compiler0, checkLevel0, true);
        NodeTraversal nodeTraversal0 = new NodeTraversal(compiler0, processClosurePrimitives0);
        processClosurePrimitives0.visit(nodeTraversal0, node0, node0);
    }

    @Test(timeout = 4000)
    public void test056() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = new Node(37);
        Node node1 = new Node(34, node0, node0, node0, node0);
        node0.addChildToBack(node1);
        CheckLevel checkLevel0 = CheckLevel.ERROR;
        node1.setType(33);
        ProcessClosurePrimitives processClosurePrimitives0 = new ProcessClosurePrimitives(compiler0, checkLevel0, true);
        NodeTraversal nodeTraversal0 = new NodeTraversal(compiler0, processClosurePrimitives0);
        processClosurePrimitives0.visit(nodeTraversal0, node0, node1);
        node1.isSyntheticBlock();
    }

    @Test(timeout = 4000)
    public void test067() throws Throwable {
        Node node0 = new Node(105);
        Compiler compiler0 = new Compiler();
        CheckLevel checkLevel0 = CheckLevel.ERROR;
        ProcessClosurePrimitives processClosurePrimitives0 = new ProcessClosurePrimitives(compiler0, checkLevel0, false);
        NodeTraversal nodeTraversal0 = new NodeTraversal(compiler0, processClosurePrimitives0);
        Node node1 = new Node(15, node0, node0, node0, 18, 13);
        processClosurePrimitives0.visit(nodeTraversal0, node0, node1);
    }

    @Test(timeout = 4000)
    public void test078() throws Throwable {
        Node node0 = new Node(105, 105, 105);
        Node node1 = Node.newString(6, "JSC_TOO_MANY_ARGUMENTS_ERROR");
        node0.addChildrenToFront(node1);
        Compiler compiler0 = new Compiler();
        StrictModeCheck strictModeCheck0 = new StrictModeCheck(compiler0, true, true);
        NodeTraversal nodeTraversal0 = new NodeTraversal(compiler0, strictModeCheck0);
        CheckLevel checkLevel0 = CheckLevel.OFF;
        ProcessClosurePrimitives processClosurePrimitives0 = new ProcessClosurePrimitives(compiler0, checkLevel0, true);
        Node node2 = new Node(125, node0, node0, 38, 43);
        processClosurePrimitives0.visit(nodeTraversal0, node0, node2);
    }

    @Test(timeout = 4000)
    public void test089() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.parseTestCode("msg.cant.convert");
        CheckLevel checkLevel0 = CheckLevel.ERROR;
        ProcessClosurePrimitives processClosurePrimitives0 = new ProcessClosurePrimitives(compiler0, checkLevel0, false);
        processClosurePrimitives0.process(node0, node0);
    }

    @Test(timeout = 4000)
    public void test0910() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = new Node(86);
        Node node1 = new Node(46, node0, node0);
        CheckLevel checkLevel0 = CheckLevel.OFF;
        ProcessClosurePrimitives processClosurePrimitives0 = new ProcessClosurePrimitives(compiler0, checkLevel0, false);
        processClosurePrimitives0.process(node1, node1);
        node1.isLocalResultCall();
    }

    @Test(timeout = 4000)
    public void test1011() throws Throwable {
        Node node0 = new Node(89, 89, 89);
        Compiler compiler0 = new Compiler();
        CheckLevel checkLevel0 = CheckLevel.ERROR;
        Node node1 = new Node(30, node0, node0, 12, 0);
        ProcessClosurePrimitives processClosurePrimitives0 = new ProcessClosurePrimitives(compiler0, checkLevel0, true);
        processClosurePrimitives0.process(node0, node1);
        node1.hasMoreThanOneChild();
    }
}
