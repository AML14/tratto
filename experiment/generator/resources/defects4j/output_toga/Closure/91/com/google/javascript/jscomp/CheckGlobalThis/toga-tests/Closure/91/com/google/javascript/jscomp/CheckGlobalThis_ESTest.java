/*
 * This file was automatically generated by EvoSuite
 * Sat Nov 04 04:28:29 GMT 2023
 */
package com.google.javascript.jscomp;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.google.javascript.jscomp.CheckGlobalThis;
import com.google.javascript.jscomp.CheckLevel;
import com.google.javascript.jscomp.Compiler;
import com.google.javascript.jscomp.NodeTraversal;
import com.google.javascript.rhino.Node;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class CheckGlobalThis_ESTest extends CheckGlobalThis_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        Compiler compiler0 = new Compiler();
        CheckLevel checkLevel0 = CheckLevel.WARNING;
        CheckGlobalThis checkGlobalThis0 = new CheckGlobalThis(compiler0, checkLevel0);
        Node node0 = new Node(105, 105, 105);
        Node node1 = new Node(86, node0, node0, 21, 105);
        boolean boolean0 = checkGlobalThis0.shouldTraverse((NodeTraversal) null, node0, node1);
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = new Node(105);
        node0.addSuppression("<");
        CheckLevel checkLevel0 = CheckLevel.OFF;
        CheckGlobalThis checkGlobalThis0 = new CheckGlobalThis(compiler0, checkLevel0);
        NodeTraversal nodeTraversal0 = new NodeTraversal(compiler0, checkGlobalThis0);
        Node node1 = compiler0.parseSyntheticCode("<", "<");
        boolean boolean0 = checkGlobalThis0.shouldTraverse(nodeTraversal0, node0, node1);
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        Compiler compiler0 = new Compiler();
        CheckLevel checkLevel0 = CheckLevel.ERROR;
        CheckGlobalThis checkGlobalThis0 = new CheckGlobalThis(compiler0, checkLevel0);
        Node node0 = new Node(105, 105, 132);
        Node node1 = new Node(132, node0, node0, 8, 1);
        boolean boolean0 = checkGlobalThis0.shouldTraverse((NodeTraversal) null, node0, node1);
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        Compiler compiler0 = new Compiler();
        CheckLevel checkLevel0 = CheckLevel.WARNING;
        CheckGlobalThis checkGlobalThis0 = new CheckGlobalThis(compiler0, checkLevel0);
        Node node0 = new Node(105);
        Node node1 = new Node(38, node0, node0, 1, 28);
        node0.addChildToFront(node1);
        NodeTraversal nodeTraversal0 = new NodeTraversal(compiler0, checkGlobalThis0);
        boolean boolean0 = checkGlobalThis0.shouldTraverse(nodeTraversal0, node0, node1);
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        Compiler compiler0 = new Compiler();
        CheckLevel checkLevel0 = CheckLevel.OFF;
        CheckGlobalThis checkGlobalThis0 = new CheckGlobalThis(compiler0, checkLevel0);
        Node node0 = new Node(105);
        Node node1 = new Node(17, node0, node0, 49, 19);
        Node node2 = Node.newString("<K");
        boolean boolean0 = checkGlobalThis0.shouldTraverse((NodeTraversal) null, node0, node2);
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        Compiler compiler0 = new Compiler();
        CheckLevel checkLevel0 = CheckLevel.OFF;
        CheckGlobalThis checkGlobalThis0 = new CheckGlobalThis(compiler0, checkLevel0);
        Node node0 = new Node(105, 105, 105);
        Node node1 = new Node(39, node0, node0, 27, 20);
        boolean boolean0 = checkGlobalThis0.shouldTraverse((NodeTraversal) null, node0, node1);
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        Compiler compiler0 = new Compiler();
        CheckLevel checkLevel0 = CheckLevel.ERROR;
        Node node0 = new Node(42, 42, 42);
        CheckGlobalThis checkGlobalThis0 = new CheckGlobalThis(compiler0, checkLevel0);
        NodeTraversal nodeTraversal0 = new NodeTraversal(compiler0, checkGlobalThis0);
        boolean boolean0 = checkGlobalThis0.shouldTraverse(nodeTraversal0, node0, (Node) null);
    }

    @Test(timeout = 4000)
    public void test077() throws Throwable {
        Compiler compiler0 = new Compiler();
        CheckLevel checkLevel0 = CheckLevel.WARNING;
        CheckGlobalThis checkGlobalThis0 = new CheckGlobalThis(compiler0, checkLevel0);
        Node node0 = new Node(33, 33, 33);
        Node node1 = new Node(86, node0, node0, 14, 15);
        boolean boolean0 = checkGlobalThis0.shouldTraverse((NodeTraversal) null, node0, node1);
        boolean boolean1 = checkGlobalThis0.shouldTraverse((NodeTraversal) null, node0, node1);
    }

    @Test(timeout = 4000)
    public void test088() throws Throwable {
        Compiler compiler0 = new Compiler();
        CheckLevel checkLevel0 = CheckLevel.ERROR;
        CheckGlobalThis checkGlobalThis0 = new CheckGlobalThis(compiler0, checkLevel0);
        Node node0 = new Node(16);
        Node node1 = new Node(86, node0, node0, 14, 12);
        boolean boolean0 = checkGlobalThis0.shouldTraverse((NodeTraversal) null, node1, node1);
    }

    @Test(timeout = 4000)
    public void test099() throws Throwable {
        Compiler compiler0 = new Compiler();
        CheckLevel checkLevel0 = CheckLevel.ERROR;
        CheckGlobalThis checkGlobalThis0 = new CheckGlobalThis(compiler0, checkLevel0);
        Node node0 = new Node(33);
        Node node1 = new Node(86, node0, node0, 14, 12);
        checkGlobalThis0.shouldTraverse((NodeTraversal) null, node1, node1);
    }

    @Test(timeout = 4000)
    public void test1010() throws Throwable {
        Compiler compiler0 = new Compiler();
        CheckLevel checkLevel0 = CheckLevel.WARNING;
        CheckGlobalThis checkGlobalThis0 = new CheckGlobalThis(compiler0, checkLevel0);
        Node node0 = new Node(35);
        Node node1 = new Node(86, node0, node0, 14, 15);
        node0.addChildToFront(node1);
        boolean boolean0 = checkGlobalThis0.shouldTraverse((NodeTraversal) null, node1, node1);
    }

    @Test(timeout = 4000)
    public void test1111() throws Throwable {
        Compiler compiler0 = new Compiler();
        CheckLevel checkLevel0 = CheckLevel.WARNING;
        CheckGlobalThis checkGlobalThis0 = new CheckGlobalThis(compiler0, checkLevel0);
        Node node0 = new Node(42);
        checkGlobalThis0.visit((NodeTraversal) null, node0, node0);
    }

    @Test(timeout = 4000)
    public void test1212() throws Throwable {
        Compiler compiler0 = new Compiler();
        CheckLevel checkLevel0 = CheckLevel.WARNING;
        CheckGlobalThis checkGlobalThis0 = new CheckGlobalThis(compiler0, checkLevel0);
        Node node0 = new Node(42);
        Node node1 = new Node(86, node0, node0, 14, 1846);
        checkGlobalThis0.shouldTraverse((NodeTraversal) null, node0, node1);
        checkGlobalThis0.visit((NodeTraversal) null, node0, node0);
    }

    @Test(timeout = 4000)
    public void test1313() throws Throwable {
        Compiler compiler0 = new Compiler();
        CheckLevel checkLevel0 = CheckLevel.WARNING;
        CheckGlobalThis checkGlobalThis0 = new CheckGlobalThis(compiler0, checkLevel0);
        Node node0 = new Node(35);
        Node node1 = new Node(86, node0, node0, 14, 15);
        checkGlobalThis0.shouldTraverse((NodeTraversal) null, node0, node1);
        checkGlobalThis0.visit((NodeTraversal) null, node0, node0);
    }

    @Test(timeout = 4000)
    public void test1414() throws Throwable {
        Compiler compiler0 = new Compiler();
        CheckLevel checkLevel0 = CheckLevel.ERROR;
        CheckGlobalThis checkGlobalThis0 = new CheckGlobalThis(compiler0, checkLevel0);
        Node node0 = new Node(42);
        NodeTraversal nodeTraversal0 = new NodeTraversal(compiler0, checkGlobalThis0);
        checkGlobalThis0.visit(nodeTraversal0, node0, (Node) null);
        node0.hasChildren();
        assertFalse(node0.hasChildren());
    }

    @Test(timeout = 4000)
    public void test1515() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = new Node(42, 42, 42);
        CheckLevel checkLevel0 = CheckLevel.WARNING;
        CheckGlobalThis checkGlobalThis0 = new CheckGlobalThis(compiler0, checkLevel0);
        NodeTraversal nodeTraversal0 = new NodeTraversal(compiler0, checkGlobalThis0);
        Node node1 = new Node(35, node0, node0, node0, (-3130), 37);
        checkGlobalThis0.visit(nodeTraversal0, node0, node1);
    }

    @Test(timeout = 4000)
    public void test1616() throws Throwable {
        Compiler compiler0 = new Compiler();
        CheckLevel checkLevel0 = CheckLevel.WARNING;
        CheckGlobalThis checkGlobalThis0 = new CheckGlobalThis(compiler0, checkLevel0);
        Node node0 = new Node(105);
        Node node1 = new Node(38, node0, node0, 1, 28);
        node1.addSuppression("/7G\"3mc]AA63");
        NodeTraversal nodeTraversal0 = new NodeTraversal(compiler0, checkGlobalThis0);
        boolean boolean0 = checkGlobalThis0.shouldTraverse(nodeTraversal0, node0, node0);
    }

    @Test(timeout = 4000)
    public void test1717() throws Throwable {
        Compiler compiler0 = new Compiler();
        CheckLevel checkLevel0 = CheckLevel.OFF;
        CheckGlobalThis checkGlobalThis0 = new CheckGlobalThis(compiler0, checkLevel0);
        Node node0 = new Node(105);
        Node node1 = new Node(38, node0, node0, 1, 28);
        Node node2 = new Node(118, node1, node1, 1, 41);
        NodeTraversal nodeTraversal0 = new NodeTraversal(compiler0, checkGlobalThis0);
        boolean boolean0 = checkGlobalThis0.shouldTraverse(nodeTraversal0, node0, node0);
    }
}
