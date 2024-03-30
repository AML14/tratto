/*
 * This file was automatically generated by EvoSuite
 * Sat Nov 04 02:02:51 GMT 2023
 */
package com.google.javascript.jscomp;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.google.javascript.jscomp.Compiler;
import com.google.javascript.jscomp.NameAnalyzer;
import com.google.javascript.jscomp.Normalize;
import com.google.javascript.rhino.Node;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class NameAnalyzer_ESTest extends NameAnalyzer_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        Compiler compiler0 = new Compiler();
        NameAnalyzer nameAnalyzer0 = new NameAnalyzer(compiler0, true);
        Node node0 = Normalize.parseAndNormalizeTestCode(compiler0, "var t;", "var t;");
        Node node1 = new Node(16, node0);
        nameAnalyzer0.process(node0, node1);
        String string0 = nameAnalyzer0.getHtmlReport();
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = Normalize.parseAndNormalizeTestCode(compiler0, "FFr=QM", "FFr=QM");
        NameAnalyzer nameAnalyzer0 = new NameAnalyzer(compiler0, false);
        nameAnalyzer0.process(node0, node0);
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        Compiler compiler0 = new Compiler();
        NameAnalyzer nameAnalyzer0 = new NameAnalyzer(compiler0, true);
        Node node0 = Normalize.parseAndNormalizeTestCode(compiler0, "var t", "var t");
        Node node1 = new Node(16, node0);
        nameAnalyzer0.process(node1, node0);
        node0.hasOneChild();
        assertFalse(node0.hasOneChild());
    }

    @Test(timeout = 4000)
    public void test023() throws Throwable {
        Compiler compiler0 = new Compiler();
        NameAnalyzer nameAnalyzer0 = new NameAnalyzer(compiler0, true);
        Node node0 = Normalize.parseAndNormalizeTestCode(compiler0, "var t", "var t");
        Node node1 = new Node(16, node0);
        nameAnalyzer0.process(node1, node0);
        node0.hasChildren();
    }

    @Test(timeout = 4000)
    public void test034() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.parseSyntheticCode("_jq$o^L?v_OW", "function JSCompiler_returnArg(JSCompiler_returnArg_value) {  return function() {return JSCompiler_returnArg_value}}");
        NameAnalyzer nameAnalyzer0 = new NameAnalyzer(compiler0, true);
        nameAnalyzer0.process(node0, node0);
    }

    @Test(timeout = 4000)
    public void test045() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = new Node(130, 130, 130);
        Node node1 = new Node(37);
        node0.addChildToBack(node1);
        NameAnalyzer nameAnalyzer0 = new NameAnalyzer(compiler0, true);
        nameAnalyzer0.process(node0, node0);
    }

    @Test(timeout = 4000)
    public void test056() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = Node.newString(148, "B");
        Node node1 = new Node(0, node0, node0, node0);
        Node node2 = new Node(16, node1);
        NameAnalyzer nameAnalyzer0 = new NameAnalyzer(compiler0, true);
        nameAnalyzer0.process(node2, node1);
        node2.isRegExp();
    }

    @Test(timeout = 4000)
    public void test067() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.parseSyntheticCode("com.<oogle.javascript.jscomp.CompilerOptions$TracerMode", "com.<oogle.javascript.jscomp.CompilerOptions$TracerMode");
        Node node1 = new Node(37, 48, 1);
        node1.addChildToBack(node0);
        NameAnalyzer nameAnalyzer0 = new NameAnalyzer(compiler0, false);
        nameAnalyzer0.process(node1, node1);
    }

    @Test(timeout = 4000)
    public void test078() throws Throwable {
        Compiler compiler0 = new Compiler();
        NameAnalyzer nameAnalyzer0 = new NameAnalyzer(compiler0, true);
        Node node0 = Normalize.parseAndNormalizeTestCode(compiler0, "window", "window");
        nameAnalyzer0.process(node0, node0);
    }

    @Test(timeout = 4000)
    public void test089() throws Throwable {
        Compiler compiler0 = new Compiler();
        NameAnalyzer nameAnalyzer0 = new NameAnalyzer(compiler0, true);
        Node node0 = Normalize.parseAndNormalizeTestCode(compiler0, "com.google.javascript.jscomp.NameAnalyzer$ReferencePropagationCallback", "com.google.javascript.jscomp.NameAnalyzer$ReferencePropagationCallback");
        nameAnalyzer0.process(node0, node0);
        node0.hasChildren();
        assertFalse(node0.hasChildren());
    }

    @Test(timeout = 4000)
    public void test0910() throws Throwable {
        Compiler compiler0 = new Compiler();
        NameAnalyzer nameAnalyzer0 = new NameAnalyzer(compiler0, true);
        Node node0 = new Node(118);
        Node node1 = compiler0.parseSyntheticCode("Pq0", "Pq0");
        node0.addChildToBack(node1);
        nameAnalyzer0.process(node0, node0);
    }

    @Test(timeout = 4000)
    public void test1011() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = new Node(114, 114, 114);
        Node node1 = new Node(115);
        node0.addChildToBack(node1);
        NameAnalyzer nameAnalyzer0 = new NameAnalyzer(compiler0, false);
        nameAnalyzer0.process(node1, node0);
    }

    @Test(timeout = 4000)
    public void test1112() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = new Node((-1791368267));
        Node node1 = new Node(4);
        node1.addChildToBack(node0);
        NameAnalyzer nameAnalyzer0 = new NameAnalyzer(compiler0, true);
        nameAnalyzer0.process(node0, node1);
        node0.isLabelName();
        assertTrue(node0.isLabelName());
    }

    @Test(timeout = 4000)
    public void test1213() throws Throwable {
        Compiler compiler0 = new Compiler();
        NameAnalyzer nameAnalyzer0 = new NameAnalyzer(compiler0, false);
        Node node0 = new Node(103, 103, 103);
        Node node1 = new Node(49, node0, node0, 8, 42);
        nameAnalyzer0.process(node1, node1);
    }

    @Test(timeout = 4000)
    public void test1314() throws Throwable {
        Compiler compiler0 = new Compiler();
        NameAnalyzer nameAnalyzer0 = new NameAnalyzer(compiler0, true);
        Node node0 = new Node(108, 108, 108);
        Node node1 = Node.newNumber((double) 16, 0, 49);
        node0.addChildToBack(node1);
        nameAnalyzer0.process(node1, node0);
    }

    @Test(timeout = 4000)
    public void test1415() throws Throwable {
        Compiler compiler0 = new Compiler();
        NameAnalyzer nameAnalyzer0 = new NameAnalyzer(compiler0, true);
        Node node0 = new Node(113);
        Node node1 = new Node(113);
        node1.addChildToBack(node0);
        nameAnalyzer0.process(node1, node1);
    }

    @Test(timeout = 4000)
    public void test1516() throws Throwable {
        Compiler compiler0 = new Compiler();
        NameAnalyzer nameAnalyzer0 = new NameAnalyzer(compiler0, false);
        Node node0 = new Node(119);
        Node node1 = new Node(8);
        node0.addChildToBack(node1);
        nameAnalyzer0.process(node0, node0);
    }

    @Test(timeout = 4000)
    public void test1617() throws Throwable {
        Compiler compiler0 = new Compiler();
        NameAnalyzer nameAnalyzer0 = new NameAnalyzer(compiler0, true);
        Node node0 = new Node(111);
        Node node1 = Node.newNumber((double) 30, 4, 46);
        node0.addChildToBack(node1);
        nameAnalyzer0.process(node0, node0);
        node0.isString();
        assertTrue(node0.isString());
    }

    @Test(timeout = 4000)
    public void test1718() throws Throwable {
        Compiler compiler0 = new Compiler();
        NameAnalyzer nameAnalyzer0 = new NameAnalyzer(compiler0, true);
        Node node0 = new Node(114);
        Node node1 = new Node(114);
        Node node2 = new Node(110, node0, node1, node0, node1, 12, 54);
        nameAnalyzer0.process(node0, node2);
        node2.hasOneChild();
        assertFalse(node2.hasOneChild());
    }

    @Test(timeout = 4000)
    public void test1819() throws Throwable {
        Compiler compiler0 = new Compiler();
        NameAnalyzer nameAnalyzer0 = new NameAnalyzer(compiler0, true);
        Node node0 = new Node(114);
        Node node1 = new Node(35);
        node0.addChildToBack(node1);
        nameAnalyzer0.process(node0, node0);
    }

    @Test(timeout = 4000)
    public void test1920() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = new Node(97);
        Node node1 = new Node(0);
        NameAnalyzer nameAnalyzer0 = new NameAnalyzer(compiler0, true);
        Node node2 = new Node(114, node0, node0, node0, node1, 37, 42);
        nameAnalyzer0.process(node2, node2);
        node2.isThrow();
    }

    @Test(timeout = 4000)
    public void test2021() throws Throwable {
        Compiler compiler0 = new Compiler();
        NameAnalyzer nameAnalyzer0 = new NameAnalyzer(compiler0, true);
        Node node0 = Normalize.parseAndNormalizeTestCode(compiler0, "var t", "var t");
        Node node1 = new Node(16, node0);
        nameAnalyzer0.process(node0, node1);
        nameAnalyzer0.process(node1, node1);
        node1.isRegExp();
        assertTrue(node1.isRegExp());
    }

    @Test(timeout = 4000)
    public void test2122() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = Node.newString(148, "FB");
        Node node1 = new Node(16, node0);
        Node node2 = new Node(38, node1);
        NameAnalyzer nameAnalyzer0 = new NameAnalyzer(compiler0, false);
        nameAnalyzer0.process(node0, node0);
    }

    @Test(timeout = 4000)
    public void test2223() throws Throwable {
        Compiler compiler0 = new Compiler();
        NameAnalyzer nameAnalyzer0 = new NameAnalyzer(compiler0, true);
        Node node0 = Normalize.parseAndNormalizeTestCode(compiler0, "var t;", "var t;");
        nameAnalyzer0.process(node0, node0);
        node0.getChildCount();
        assertEquals(1, node0.getChildCount());
    }

    @Test(timeout = 4000)
    public void test2224() throws Throwable {
        Compiler compiler0 = new Compiler();
        NameAnalyzer nameAnalyzer0 = new NameAnalyzer(compiler0, true);
        Node node0 = Normalize.parseAndNormalizeTestCode(compiler0, "var t;", "var t;");
        nameAnalyzer0.process(node0, node0);
        String string0 = nameAnalyzer0.getHtmlReport();
    }
}
