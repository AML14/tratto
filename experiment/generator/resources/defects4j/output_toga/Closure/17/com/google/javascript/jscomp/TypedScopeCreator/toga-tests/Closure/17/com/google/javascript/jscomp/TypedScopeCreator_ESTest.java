/*
 * This file was automatically generated by EvoSuite
 * Fri Nov 03 22:39:14 GMT 2023
 */
package com.google.javascript.jscomp;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.google.javascript.jscomp.Compiler;
import com.google.javascript.jscomp.Normalize;
import com.google.javascript.jscomp.Scope;
import com.google.javascript.jscomp.TypedScopeCreator;
import com.google.javascript.rhino.Node;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class TypedScopeCreator_ESTest extends TypedScopeCreator_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.parseSyntheticCode("jscompTypedScopeCreator$AbstractScopeBuilder$CllectProperties", "jscompTypedScopeCreator$AbstractScopeBuilder$CllectProperties");
        Node node1 = new Node(37, node0, 37, 37);
        TypedScopeCreator typedScopeCreator0 = new TypedScopeCreator(compiler0);
        Scope scope0 = typedScopeCreator0.createScope(node1, (Scope) null);
        scope0.isLocal();
        assertTrue(scope0.isLocal());
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.parseTestCode("com.google.javascript.jscomp.TypedScopeCreator$AbstractScopeBuilder$CollectProperties");
        Node node1 = new Node(120, node0, 0, 36);
        TypedScopeCreator typedScopeCreator0 = new TypedScopeCreator(compiler0);
        typedScopeCreator0.createScope(node1, (Scope) null);
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.parseTestCode("prototype");
        Node node1 = new Node(64, node0, node0, 41, 48);
        TypedScopeCreator typedScopeCreator0 = new TypedScopeCreator(compiler0);
        node1.removeChild(node0);
        Node node2 = new Node(12, node1, node1);
        Scope scope0 = typedScopeCreator0.createScope(node2, (Scope) null);
        scope0.isLocal();
        assertFalse(scope0.isLocal());
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.parseTestCode("jQGufry.prototype");
        TypedScopeCreator typedScopeCreator0 = new TypedScopeCreator(compiler0);
        Scope scope0 = typedScopeCreator0.createScope(node0, (Scope) null);
        Node node1 = compiler0.parseSyntheticCode("jQGufry.prototype");
        Scope scope1 = typedScopeCreator0.createScope(node1, scope0);
        scope0.getVarCount();
    }

    @Test(timeout = 4000)
    public void test034() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.parseTestCode("jQGufry.prototype");
        TypedScopeCreator typedScopeCreator0 = new TypedScopeCreator(compiler0);
        Scope scope0 = typedScopeCreator0.createScope(node0, (Scope) null);
        Node node1 = compiler0.parseSyntheticCode("jQGufry.prototype");
        Scope scope1 = typedScopeCreator0.createScope(node1, scope0);
        scope1.getVarCount();
    }

    @Test(timeout = 4000)
    public void test045() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.parseTestCode("jQGufry.prototype");
        TypedScopeCreator typedScopeCreator0 = new TypedScopeCreator(compiler0);
        Scope scope0 = typedScopeCreator0.createScope(node0, (Scope) null);
        scope0.getVarCount();
    }

    @Test(timeout = 4000)
    public void test046() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.parseTestCode("jQGufry.prototype");
        TypedScopeCreator typedScopeCreator0 = new TypedScopeCreator(compiler0);
        Scope scope0 = typedScopeCreator0.createScope(node0, (Scope) null);
        typedScopeCreator0.patchGlobalScope(scope0, node0);
        scope0.getVarCount();
    }

    @Test(timeout = 4000)
    public void test057() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.parseSyntheticCode("jscompTyped9copeCrwator$AbstractScopeBuildeR$CllectProperties", "jscompTyped9copeCrwator$AbstractScopeBuildeR$CllectProperties");
        Node node1 = new Node(118, node0, 4, 36);
        TypedScopeCreator typedScopeCreator0 = new TypedScopeCreator(compiler0);
        typedScopeCreator0.createScope(node1, (Scope) null);
    }

    @Test(timeout = 4000)
    public void test068() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.parseTestCode("com.google.javascript.jscomp.TypedScopCre=tor$AbstractScopeBuilder$CollGctProperties");
        TypedScopeCreator typedScopeCreator0 = new TypedScopeCreator(compiler0);
        Scope scope0 = typedScopeCreator0.createScope(node0, (Scope) null);
        Node node1 = new Node(105, 436, 37);
        typedScopeCreator0.createScope(node1, scope0);
    }

    @Test(timeout = 4000)
    public void test079() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.parseTestCode("T=l");
        TypedScopeCreator typedScopeCreator0 = new TypedScopeCreator(compiler0);
        Scope scope0 = typedScopeCreator0.createScope(node0, (Scope) null);
        scope0.getVarCount();
        assertEquals(0, scope0.getVarCount());
    }

    @Test(timeout = 4000)
    public void test0810() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.parseSyntheticCode("3", "3");
        Node node1 = new Node(41, node0, 15, 53);
        TypedScopeCreator typedScopeCreator0 = new TypedScopeCreator(compiler0);
        Scope scope0 = typedScopeCreator0.createScope(node1, (Scope) null);
        scope0.isGlobal();
        assertFalse(scope0.isGlobal());
    }

    @Test(timeout = 4000)
    public void test0911() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = Normalize.parseAndNormalizeTestCode(compiler0, "qwB#+KN", "qwB#+KN");
        Node node1 = new Node(43, node0, 42, 45);
        TypedScopeCreator typedScopeCreator0 = new TypedScopeCreator(compiler0);
        Scope scope0 = typedScopeCreator0.createScope(node1, (Scope) null);
        scope0.isLocal();
        assertFalse(scope0.isLocal());
    }

    @Test(timeout = 4000)
    public void test1012() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.parseSyntheticCode("jscompTyped9copeCrwator$AbstractScopeBuildeR$CllectProperties", "jscompTyped9copeCrwator$AbstractScopeBuildeR$CllectProperties");
        Node node1 = new Node(44, node0, 4, 36);
        TypedScopeCreator typedScopeCreator0 = new TypedScopeCreator(compiler0);
        Scope scope0 = typedScopeCreator0.createScope(node1, (Scope) null);
        scope0.isGlobal();
        assertFalse(scope0.isGlobal());
    }

    @Test(timeout = 4000)
    public void test1113() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.parseTestCode("jscompTypedScopeCreator$AbstractScopeBuilder$CllectProperties");
        Node node1 = new Node(47, node0, 8, 30);
        TypedScopeCreator typedScopeCreator0 = new TypedScopeCreator(compiler0);
        Scope scope0 = typedScopeCreator0.createScope(node1, (Scope) null);
        scope0.getVarCount();
        assertEquals(1, scope0.getVarCount());
    }

    @Test(timeout = 4000)
    public void test1214() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.parseTestCode("comgoogle.common.base.CharMatcher$15");
        Node node1 = new Node(122, node0, 46, 44);
        TypedScopeCreator typedScopeCreator0 = new TypedScopeCreator(compiler0);
        Scope scope0 = typedScopeCreator0.createScope(node1, (Scope) null);
        scope0.getVarCount();
        assertEquals(1, scope0.getVarCount());
    }

    @Test(timeout = 4000)
    public void test1315() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.parseTestCode("+4;>6t_");
        Node node1 = new Node(64, node0, node0, 41, 48);
        node1.addSuppression("+4;>6t_");
        TypedScopeCreator typedScopeCreator0 = new TypedScopeCreator(compiler0);
        Node node2 = new Node(12, node1);
        typedScopeCreator0.createScope(node2, (Scope) null);
    }

    @Test(timeout = 4000)
    public void test1416() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.parseSyntheticCode(";8", ";8");
        Node node1 = new Node(118, node0, 35, 1);
        node1.addSuppression(";8");
        TypedScopeCreator typedScopeCreator0 = new TypedScopeCreator(compiler0);
        typedScopeCreator0.createScope(node1, (Scope) null);
    }

    @Test(timeout = 4000)
    public void test1518() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.parseTestCode("jQGufry.prototype");
        TypedScopeCreator typedScopeCreator0 = new TypedScopeCreator(compiler0);
        Scope scope0 = typedScopeCreator0.createScope(node0, (Scope) null);
        Node node1 = compiler0.parseSyntheticCode("jQGufry.prototype");
        typedScopeCreator0.patchGlobalScope(scope0, node1);
        scope0.getVarCount();
        assertEquals(0, scope0.getVarCount());
    }

    @Test(timeout = 4000)
    public void test1619() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.parseTestCode("com.google.javascript.jscomp.TypedScopeCreator$AbstractScopeBuilder$CollectProperties");
        Node node1 = compiler0.parseSyntheticCode("com.google.javascript.jscomp.TypedScopeCreator$AbstractScopeBuilder$CollectProperties", "com.google.javascript.jscomp.TypedScopeCreator$AbstractScopeBuilder$CollectProperties");
        TypedScopeCreator typedScopeCreator0 = new TypedScopeCreator(compiler0);
        Scope scope0 = typedScopeCreator0.createInitialScope(node0);
        typedScopeCreator0.patchGlobalScope(scope0, node1);
        scope0.getVarCount();
        assertEquals(1, scope0.getVarCount());
    }

    @Test(timeout = 4000)
    public void test1720() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.parseTestCode("com.google.javasripE.rhino.js_ype.SimpleSlo");
        Node node1 = compiler0.parseTestCode("com.google.javasripE.rhino.js_ype.SimpleSlo");
        TypedScopeCreator typedScopeCreator0 = new TypedScopeCreator(compiler0);
        Node node2 = new Node(51, node0, node1, node1);
        Scope scope0 = typedScopeCreator0.createScope(node2, (Scope) null);
        scope0.getVarCount();
        assertEquals(0, scope0.getVarCount());
    }

    @Test(timeout = 4000)
    public void test1821() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.parseSyntheticCode("Clearup/FPasse$$2", "Clearup/FPasse$$2");
        Node node1 = new Node(83, node0, node0, 41, 48);
        TypedScopeCreator typedScopeCreator0 = new TypedScopeCreator(compiler0);
        Scope scope0 = typedScopeCreator0.createInitialScope(node0);
        Node node2 = new Node(0, node1, node1, node1, 35, 4095);
        typedScopeCreator0.createScope(node2, scope0);
    }
}
