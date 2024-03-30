/*
 * This file was automatically generated by EvoSuite
 * Sat Nov 04 06:24:22 GMT 2023
 */
package com.google.javascript.jscomp;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.google.javascript.jscomp.Compiler;
import com.google.javascript.jscomp.Normalize;
import com.google.javascript.jscomp.PeepholeSubstituteAlternateSyntax;
import com.google.javascript.rhino.Node;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class PeepholeSubstituteAlternateSyntax_ESTest extends PeepholeSubstituteAlternateSyntax_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        PeepholeSubstituteAlternateSyntax peepholeSubstituteAlternateSyntax0 = new PeepholeSubstituteAlternateSyntax(true);
        Node node0 = new Node((-1474), (-1474), (-1474));
        Node node1 = peepholeSubstituteAlternateSyntax0.optimizeSubtree(node0);
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        Node node0 = new Node(49);
        PeepholeSubstituteAlternateSyntax peepholeSubstituteAlternateSyntax0 = new PeepholeSubstituteAlternateSyntax(false);
        peepholeSubstituteAlternateSyntax0.areMatchingExits(node0, node0);
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        Node node0 = new Node(4, 55, 40);
        PeepholeSubstituteAlternateSyntax peepholeSubstituteAlternateSyntax0 = new PeepholeSubstituteAlternateSyntax(true);
        peepholeSubstituteAlternateSyntax0.optimizeSubtree(node0);
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        Node node0 = Node.newNumber(479.7);
        PeepholeSubstituteAlternateSyntax peepholeSubstituteAlternateSyntax0 = new PeepholeSubstituteAlternateSyntax(false);
        Node node1 = new Node(26, node0, node0, node0, node0, 49, 40);
        peepholeSubstituteAlternateSyntax0.optimizeSubtree(node1);
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        Node node0 = new Node(43);
        PeepholeSubstituteAlternateSyntax peepholeSubstituteAlternateSyntax0 = new PeepholeSubstituteAlternateSyntax(true);
        peepholeSubstituteAlternateSyntax0.optimizeSubtree(node0);
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        Node node0 = new Node(44, 44, 44);
        PeepholeSubstituteAlternateSyntax peepholeSubstituteAlternateSyntax0 = new PeepholeSubstituteAlternateSyntax(false);
        Node node1 = peepholeSubstituteAlternateSyntax0.optimizeSubtree(node0);
        node1.isAssignAdd();
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        Node node0 = new Node(49);
        PeepholeSubstituteAlternateSyntax peepholeSubstituteAlternateSyntax0 = new PeepholeSubstituteAlternateSyntax(true);
        peepholeSubstituteAlternateSyntax0.optimizeSubtree(node0);
    }

    @Test(timeout = 4000)
    public void test077() throws Throwable {
        Node node0 = new Node(63);
        PeepholeSubstituteAlternateSyntax peepholeSubstituteAlternateSyntax0 = new PeepholeSubstituteAlternateSyntax(false);
        Node node1 = peepholeSubstituteAlternateSyntax0.optimizeSubtree(node0);
        node1.isEmpty();
    }

    @Test(timeout = 4000)
    public void test088() throws Throwable {
        PeepholeSubstituteAlternateSyntax peepholeSubstituteAlternateSyntax0 = new PeepholeSubstituteAlternateSyntax(true);
        Node node0 = new Node(85, 85, 85);
        Node node1 = peepholeSubstituteAlternateSyntax0.optimizeSubtree(node0);
        node1.isThrow();
    }

    @Test(timeout = 4000)
    public void test099() throws Throwable {
        PeepholeSubstituteAlternateSyntax peepholeSubstituteAlternateSyntax0 = new PeepholeSubstituteAlternateSyntax(true);
        Node node0 = new Node((-1474), (-1474), (-1474));
        Node node1 = new Node(108, node0, node0, node0, node0, 39, 46);
        peepholeSubstituteAlternateSyntax0.optimizeSubtree(node1);
    }

    @Test(timeout = 4000)
    public void test1010() throws Throwable {
        Node node0 = new Node(113, 113, 113);
        PeepholeSubstituteAlternateSyntax peepholeSubstituteAlternateSyntax0 = new PeepholeSubstituteAlternateSyntax(true);
        peepholeSubstituteAlternateSyntax0.optimizeSubtree(node0);
    }

    @Test(timeout = 4000)
    public void test1111() throws Throwable {
        Node node0 = new Node(114);
        PeepholeSubstituteAlternateSyntax peepholeSubstituteAlternateSyntax0 = new PeepholeSubstituteAlternateSyntax(true);
        peepholeSubstituteAlternateSyntax0.optimizeSubtree(node0);
    }

    @Test(timeout = 4000)
    public void test1212() throws Throwable {
        Node node0 = new Node(130, 130, 130);
        PeepholeSubstituteAlternateSyntax peepholeSubstituteAlternateSyntax0 = new PeepholeSubstituteAlternateSyntax(true);
        peepholeSubstituteAlternateSyntax0.optimizeSubtree(node0);
    }

    @Test(timeout = 4000)
    public void test1313() throws Throwable {
        Node node0 = Node.newNumber((-4193.0));
        Node node1 = new Node(4, 55, 40);
        Node node2 = new Node(8, node1, node0, 15, 53);
        PeepholeSubstituteAlternateSyntax peepholeSubstituteAlternateSyntax0 = new PeepholeSubstituteAlternateSyntax(true);
        peepholeSubstituteAlternateSyntax0.optimizeSubtree(node1);
    }

    @Test(timeout = 4000)
    public void test1414() throws Throwable {
        Node node0 = new Node(30, 30, 30);
        Compiler compiler0 = new Compiler();
        PeepholeSubstituteAlternateSyntax peepholeSubstituteAlternateSyntax0 = new PeepholeSubstituteAlternateSyntax(false);
        peepholeSubstituteAlternateSyntax0.beginTraversal(compiler0);
        Node node1 = peepholeSubstituteAlternateSyntax0.optimizeSubtree(node0);
        node1.isAnd();
    }

    @Test(timeout = 4000)
    public void test1515() throws Throwable {
        Node node0 = Node.newNumber(1626.0);
        Node node1 = new Node(37, node0, node0, node0, node0, 53, 37);
        Compiler compiler0 = new Compiler();
        PeepholeSubstituteAlternateSyntax peepholeSubstituteAlternateSyntax0 = new PeepholeSubstituteAlternateSyntax(false);
        peepholeSubstituteAlternateSyntax0.beginTraversal(compiler0);
        peepholeSubstituteAlternateSyntax0.optimizeSubtree(node1);
    }

    @Test(timeout = 4000)
    public void test1616() throws Throwable {
        Node node0 = new Node(115, 115, 115);
        PeepholeSubstituteAlternateSyntax peepholeSubstituteAlternateSyntax0 = new PeepholeSubstituteAlternateSyntax(false);
        peepholeSubstituteAlternateSyntax0.optimizeSubtree(node0);
    }

    @Test(timeout = 4000)
    public void test1717() throws Throwable {
        Node node0 = new Node(43, 43, 43);
        PeepholeSubstituteAlternateSyntax peepholeSubstituteAlternateSyntax0 = new PeepholeSubstituteAlternateSyntax(true);
        Node node1 = new Node(115, node0, node0, node0, node0, 52, 55);
        peepholeSubstituteAlternateSyntax0.optimizeSubtree(node1);
    }

    @Test(timeout = 4000)
    public void test1818() throws Throwable {
        Node node0 = new Node(115, 115, 115);
        node0.addChildToFront(node0);
        PeepholeSubstituteAlternateSyntax peepholeSubstituteAlternateSyntax0 = new PeepholeSubstituteAlternateSyntax(true);
        peepholeSubstituteAlternateSyntax0.optimizeSubtree(node0);
    }

    @Test(timeout = 4000)
    public void test1919() throws Throwable {
        Node node0 = new Node(37, 53, 100);
        Compiler compiler0 = new Compiler();
        PeepholeSubstituteAlternateSyntax peepholeSubstituteAlternateSyntax0 = new PeepholeSubstituteAlternateSyntax(false);
        peepholeSubstituteAlternateSyntax0.beginTraversal(compiler0);
        peepholeSubstituteAlternateSyntax0.optimizeSubtree(node0);
    }

    @Test(timeout = 4000)
    public void test2020() throws Throwable {
        PeepholeSubstituteAlternateSyntax peepholeSubstituteAlternateSyntax0 = new PeepholeSubstituteAlternateSyntax(false);
        Node node0 = new Node(85, 85, 85);
        Node node1 = new Node(85, node0, node0, node0, node0, 55, 40);
        Node node2 = peepholeSubstituteAlternateSyntax0.optimizeSubtree(node0);
    }

    @Test(timeout = 4000)
    public void test2021() throws Throwable {
        PeepholeSubstituteAlternateSyntax peepholeSubstituteAlternateSyntax0 = new PeepholeSubstituteAlternateSyntax(false);
        Node node0 = new Node(85, 85, 85);
        Node node1 = new Node(85, node0, node0, node0, node0, 55, 40);
        Node node2 = peepholeSubstituteAlternateSyntax0.optimizeSubtree(node0);
        node2.hasOneChild();
    }

    @Test(timeout = 4000)
    public void test2122() throws Throwable {
        Node node0 = Node.newNumber(0.0);
        Node node1 = new Node(125, node0, node0, node0, node0, 53, 125);
        PeepholeSubstituteAlternateSyntax peepholeSubstituteAlternateSyntax0 = new PeepholeSubstituteAlternateSyntax(true);
        Node node2 = peepholeSubstituteAlternateSyntax0.optimizeSubtree(node1);
        node2.getType();
    }

    @Test(timeout = 4000)
    public void test2223() throws Throwable {
        Node node0 = new Node(38, 38, 38);
        Compiler compiler0 = new Compiler();
        PeepholeSubstituteAlternateSyntax peepholeSubstituteAlternateSyntax0 = new PeepholeSubstituteAlternateSyntax(false);
        peepholeSubstituteAlternateSyntax0.beginTraversal(compiler0);
        Node node1 = peepholeSubstituteAlternateSyntax0.optimizeSubtree(node0);
        node1.isBlock();
    }

    @Test(timeout = 4000)
    public void test2324() throws Throwable {
        Node node0 = Node.newNumber(0.0);
        PeepholeSubstituteAlternateSyntax peepholeSubstituteAlternateSyntax0 = new PeepholeSubstituteAlternateSyntax(false);
        Node node1 = new Node(4, node0, node0, node0, node0, 54, 30);
        peepholeSubstituteAlternateSyntax0.optimizeSubtree(node1);
    }

    @Test(timeout = 4000)
    public void test2425() throws Throwable {
        PeepholeSubstituteAlternateSyntax peepholeSubstituteAlternateSyntax0 = new PeepholeSubstituteAlternateSyntax(true);
        boolean boolean0 = peepholeSubstituteAlternateSyntax0.isPure((Node) null);
    }

    @Test(timeout = 4000)
    public void test2526() throws Throwable {
        Node node0 = new Node(115, 115, 115);
        PeepholeSubstituteAlternateSyntax peepholeSubstituteAlternateSyntax0 = new PeepholeSubstituteAlternateSyntax(true);
        boolean boolean0 = peepholeSubstituteAlternateSyntax0.isPure(node0);
    }

    @Test(timeout = 4000)
    public void test2627() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = Normalize.parseAndNormalizeTestCode(compiler0, "Y");
        PeepholeSubstituteAlternateSyntax peepholeSubstituteAlternateSyntax0 = new PeepholeSubstituteAlternateSyntax(false);
        peepholeSubstituteAlternateSyntax0.isPure(node0);
    }

    @Test(timeout = 4000)
    public void test2728() throws Throwable {
        PeepholeSubstituteAlternateSyntax peepholeSubstituteAlternateSyntax0 = new PeepholeSubstituteAlternateSyntax(false);
        Node node0 = Node.newString("\u2028");
        boolean boolean0 = peepholeSubstituteAlternateSyntax0.isPure(node0);
    }

    @Test(timeout = 4000)
    public void test2829() throws Throwable {
        Node node0 = Node.newNumber(0.0);
        PeepholeSubstituteAlternateSyntax peepholeSubstituteAlternateSyntax0 = new PeepholeSubstituteAlternateSyntax(true);
        Node node1 = new Node(4, node0, node0, node0, node0, 54, 30);
        boolean boolean0 = peepholeSubstituteAlternateSyntax0.areMatchingExits(node1, node1);
    }

    @Test(timeout = 4000)
    public void test2930() throws Throwable {
        PeepholeSubstituteAlternateSyntax peepholeSubstituteAlternateSyntax0 = new PeepholeSubstituteAlternateSyntax(true);
        Node node0 = Node.newNumber(0.0);
        peepholeSubstituteAlternateSyntax0.areMatchingExits(node0, node0);
    }

    @Test(timeout = 4000)
    public void test3031() throws Throwable {
        Node node0 = new Node(4);
        PeepholeSubstituteAlternateSyntax peepholeSubstituteAlternateSyntax0 = new PeepholeSubstituteAlternateSyntax(false);
        boolean boolean0 = peepholeSubstituteAlternateSyntax0.isExceptionPossible(node0);
    }

    @Test(timeout = 4000)
    public void test3132() throws Throwable {
        Node node0 = new Node(115, 115, 115);
        PeepholeSubstituteAlternateSyntax peepholeSubstituteAlternateSyntax0 = new PeepholeSubstituteAlternateSyntax(false);
        Node node1 = new Node(4, node0, node0, node0, node0, 16, 441);
        boolean boolean0 = peepholeSubstituteAlternateSyntax0.isExceptionPossible(node1);
    }

    @Test(timeout = 4000)
    public void test3233() throws Throwable {
        Node node0 = Node.newNumber(479.7);
        PeepholeSubstituteAlternateSyntax peepholeSubstituteAlternateSyntax0 = new PeepholeSubstituteAlternateSyntax(false);
        node0.setType(46);
        Node node1 = new Node(26, node0, node0, node0, node0, 49, 40);
        peepholeSubstituteAlternateSyntax0.optimizeSubtree(node1);
    }

    @Test(timeout = 4000)
    public void test3334() throws Throwable {
        Node node0 = Node.newNumber((-2584.919495491));
        PeepholeSubstituteAlternateSyntax peepholeSubstituteAlternateSyntax0 = new PeepholeSubstituteAlternateSyntax(true);
        node0.setType(45);
        Node node1 = new Node(26, node0, node0, node0, node0, 49, 40);
        peepholeSubstituteAlternateSyntax0.optimizeSubtree(node1);
    }

    @Test(timeout = 4000)
    public void test3435() throws Throwable {
        Node node0 = Node.newNumber(0.0);
        PeepholeSubstituteAlternateSyntax peepholeSubstituteAlternateSyntax0 = new PeepholeSubstituteAlternateSyntax(true);
        Node node1 = new Node(26, node0, node0, node0, node0, 0, 40);
        Node node2 = peepholeSubstituteAlternateSyntax0.optimizeSubtree(node1);
    }

    @Test(timeout = 4000)
    public void test3436() throws Throwable {
        Node node0 = Node.newNumber(0.0);
        PeepholeSubstituteAlternateSyntax peepholeSubstituteAlternateSyntax0 = new PeepholeSubstituteAlternateSyntax(true);
        Node node1 = new Node(26, node0, node0, node0, node0, 0, 40);
        Node node2 = peepholeSubstituteAlternateSyntax0.optimizeSubtree(node1);
        node2.getCharno();
    }

    @Test(timeout = 4000)
    public void test3537() throws Throwable {
        Node node0 = Node.newNumber(0.0);
        PeepholeSubstituteAlternateSyntax peepholeSubstituteAlternateSyntax0 = new PeepholeSubstituteAlternateSyntax(false);
        Node node1 = new Node(108, node0, node0, node0, node0, 12, 4095);
        Node node2 = peepholeSubstituteAlternateSyntax0.optimizeSubtree(node1);
    }

    @Test(timeout = 4000)
    public void test3538() throws Throwable {
        Node node0 = Node.newNumber(0.0);
        PeepholeSubstituteAlternateSyntax peepholeSubstituteAlternateSyntax0 = new PeepholeSubstituteAlternateSyntax(false);
        Node node1 = new Node(108, node0, node0, node0, node0, 12, 4095);
        Node node2 = peepholeSubstituteAlternateSyntax0.optimizeSubtree(node1);
        node2.hasOneChild();
    }

    @Test(timeout = 4000)
    public void test3639() throws Throwable {
        Node node0 = Node.newNumber((-1.0));
        PeepholeSubstituteAlternateSyntax peepholeSubstituteAlternateSyntax0 = new PeepholeSubstituteAlternateSyntax(false);
        node0.setType(26);
        Node node1 = new Node(26, node0, node0, node0, node0, 16, 2);
        peepholeSubstituteAlternateSyntax0.optimizeSubtree(node1);
    }

    @Test(timeout = 4000)
    public void test3740() throws Throwable {
        PeepholeSubstituteAlternateSyntax peepholeSubstituteAlternateSyntax0 = new PeepholeSubstituteAlternateSyntax(true);
        Node node0 = new Node(98);
        Node node1 = new Node(98, node0, node0, node0, node0, 55, 55);
        peepholeSubstituteAlternateSyntax0.optimizeSubtree(node1);
    }

    @Test(timeout = 4000)
    public void test3841() throws Throwable {
        Node node0 = new Node(44);
        PeepholeSubstituteAlternateSyntax peepholeSubstituteAlternateSyntax0 = new PeepholeSubstituteAlternateSyntax(true);
        peepholeSubstituteAlternateSyntax0.optimizeSubtree(node0);
    }

    @Test(timeout = 4000)
    public void test3942() throws Throwable {
        Node node0 = new Node(63);
        Node node1 = new Node(63, node0, node0, node0, node0, 4095, 39);
        PeepholeSubstituteAlternateSyntax peepholeSubstituteAlternateSyntax0 = new PeepholeSubstituteAlternateSyntax(false);
        Node node2 = peepholeSubstituteAlternateSyntax0.optimizeSubtree(node1);
    }

    @Test(timeout = 4000)
    public void test4043() throws Throwable {
        Node node0 = new Node(63);
        PeepholeSubstituteAlternateSyntax peepholeSubstituteAlternateSyntax0 = new PeepholeSubstituteAlternateSyntax(true);
        Node node1 = peepholeSubstituteAlternateSyntax0.optimizeSubtree(node0);
        node1.isFor();
    }

    @Test(timeout = 4000)
    public void test4144() throws Throwable {
        boolean boolean0 = PeepholeSubstituteAlternateSyntax.containsUnicodeEscape("bcR4Qsho-Mo(G?");
    }
}
