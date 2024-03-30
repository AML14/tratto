/*
 * This file was automatically generated by EvoSuite
 * Sat Nov 04 02:31:16 GMT 2023
 */
package com.google.javascript.jscomp;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.google.javascript.jscomp.AbstractCompiler;
import com.google.javascript.jscomp.Compiler;
import com.google.javascript.jscomp.NodeTraversal;
import com.google.javascript.jscomp.OperaCompoundAssignFix;
import com.google.javascript.jscomp.PeepholeReplaceKnownMethods;
import com.google.javascript.rhino.Node;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class PeepholeReplaceKnownMethods_ESTest extends PeepholeReplaceKnownMethods_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test00() throws Throwable {
        PeepholeReplaceKnownMethods peepholeReplaceKnownMethods0 = new PeepholeReplaceKnownMethods();
        Node node0 = Node.newString("");
        Node node1 = peepholeReplaceKnownMethods0.optimizeSubtree(node0);
    }

    @Test(timeout = 4000)
    public void test11() throws Throwable {
        PeepholeReplaceKnownMethods peepholeReplaceKnownMethods0 = new PeepholeReplaceKnownMethods();
        Node node0 = new Node(37);
        Node node1 = peepholeReplaceKnownMethods0.optimizeSubtree(node0);
    }

    @Test(timeout = 4000)
    public void test22() throws Throwable {
        PeepholeReplaceKnownMethods peepholeReplaceKnownMethods0 = new PeepholeReplaceKnownMethods();
        Node node0 = new Node(37);
        Node node1 = new Node(33, node0, node0, 47, 1);
        node0.addChildrenToFront(node1);
        Node node2 = peepholeReplaceKnownMethods0.optimizeSubtree(node0);
    }

    @Test(timeout = 4000)
    public void test23() throws Throwable {
        PeepholeReplaceKnownMethods peepholeReplaceKnownMethods0 = new PeepholeReplaceKnownMethods();
        Node node0 = new Node(37);
        Node node1 = new Node(33, node0, node0, 47, 1);
        node0.addChildrenToFront(node1);
        Node node2 = peepholeReplaceKnownMethods0.optimizeSubtree(node0);
        node2.getType();
    }

    @Test(timeout = 4000)
    public void test34() throws Throwable {
        PeepholeReplaceKnownMethods peepholeReplaceKnownMethods0 = new PeepholeReplaceKnownMethods();
        Node node0 = new Node(37);
        node0.addChildrenToFront(node0);
        Compiler compiler0 = new Compiler();
        OperaCompoundAssignFix operaCompoundAssignFix0 = new OperaCompoundAssignFix(compiler0);
        NodeTraversal nodeTraversal0 = new NodeTraversal(compiler0, operaCompoundAssignFix0);
        peepholeReplaceKnownMethods0.beginTraversal(nodeTraversal0);
        Node node1 = peepholeReplaceKnownMethods0.optimizeSubtree(node0);
    }

    @Test(timeout = 4000)
    public void test45() throws Throwable {
        Node node0 = new Node(37, 37, 37);
        node0.addChildrenToFront(node0);
        Compiler compiler0 = new Compiler();
        OperaCompoundAssignFix operaCompoundAssignFix0 = new OperaCompoundAssignFix(compiler0);
        AbstractCompiler.LifeCycleStage abstractCompiler_LifeCycleStage0 = AbstractCompiler.LifeCycleStage.NORMALIZED;
        compiler0.setLifeCycleStage(abstractCompiler_LifeCycleStage0);
        NodeTraversal nodeTraversal0 = new NodeTraversal(compiler0, operaCompoundAssignFix0);
        PeepholeReplaceKnownMethods peepholeReplaceKnownMethods0 = new PeepholeReplaceKnownMethods();
        peepholeReplaceKnownMethods0.beginTraversal(nodeTraversal0);
        Node node1 = peepholeReplaceKnownMethods0.optimizeSubtree(node0);
    }

    @Test(timeout = 4000)
    public void test56() throws Throwable {
        Node node0 = new Node(37, 37, 37);
        Node node1 = new Node(38, node0, node0, node0, 46, 31);
        node0.addChildrenToFront(node1);
        Compiler compiler0 = new Compiler();
        OperaCompoundAssignFix operaCompoundAssignFix0 = new OperaCompoundAssignFix(compiler0);
        AbstractCompiler.LifeCycleStage abstractCompiler_LifeCycleStage0 = AbstractCompiler.LifeCycleStage.NORMALIZED;
        compiler0.setLifeCycleStage(abstractCompiler_LifeCycleStage0);
        NodeTraversal nodeTraversal0 = new NodeTraversal(compiler0, operaCompoundAssignFix0);
        PeepholeReplaceKnownMethods peepholeReplaceKnownMethods0 = new PeepholeReplaceKnownMethods();
        peepholeReplaceKnownMethods0.beginTraversal(nodeTraversal0);
        peepholeReplaceKnownMethods0.optimizeSubtree(node0);
    }

    @Test(timeout = 4000)
    public void test67() throws Throwable {
        PeepholeReplaceKnownMethods peepholeReplaceKnownMethods0 = new PeepholeReplaceKnownMethods();
        Node node0 = new Node(37);
        Node node1 = new Node(43);
        node0.addChildrenToFront(node1);
        Node node2 = new Node(33, node0, node0, 47, 1);
        node0.addChildrenToFront(node2);
        Node node3 = peepholeReplaceKnownMethods0.optimizeSubtree(node0);
    }

    @Test(timeout = 4000)
    public void test68() throws Throwable {
        PeepholeReplaceKnownMethods peepholeReplaceKnownMethods0 = new PeepholeReplaceKnownMethods();
        Node node0 = new Node(37);
        Node node1 = new Node(43);
        node0.addChildrenToFront(node1);
        Node node2 = new Node(33, node0, node0, 47, 1);
        node0.addChildrenToFront(node2);
        Node node3 = peepholeReplaceKnownMethods0.optimizeSubtree(node0);
        node3.getCharno();
    }

    @Test(timeout = 4000)
    public void test79() throws Throwable {
        PeepholeReplaceKnownMethods peepholeReplaceKnownMethods0 = new PeepholeReplaceKnownMethods();
        Node node0 = new Node(37);
        Node node1 = new Node(53);
        node0.addChildrenToFront(node1);
        Node node2 = new Node(33, node0, node0, 47, 1);
        node0.addChildrenToFront(node2);
        Node node3 = peepholeReplaceKnownMethods0.optimizeSubtree(node0);
    }

    @Test(timeout = 4000)
    public void test710() throws Throwable {
        PeepholeReplaceKnownMethods peepholeReplaceKnownMethods0 = new PeepholeReplaceKnownMethods();
        Node node0 = new Node(37);
        Node node1 = new Node(53);
        node0.addChildrenToFront(node1);
        Node node2 = new Node(33, node0, node0, 47, 1);
        node0.addChildrenToFront(node2);
        Node node3 = peepholeReplaceKnownMethods0.optimizeSubtree(node0);
        node3.getChildCount();
    }
}
