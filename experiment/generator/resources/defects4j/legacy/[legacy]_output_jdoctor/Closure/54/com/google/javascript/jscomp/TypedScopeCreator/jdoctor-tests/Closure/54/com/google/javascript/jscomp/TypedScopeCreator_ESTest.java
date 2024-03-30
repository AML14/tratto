/*
 * This file was automatically generated by EvoSuite
 * Sat Nov 04 02:42:27 GMT 2023
 */
package com.google.javascript.jscomp;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.google.javascript.jscomp.CodingConvention;
import com.google.javascript.jscomp.Compiler;
import com.google.javascript.jscomp.CompilerOptions;
import com.google.javascript.jscomp.JSSourceFile;
import com.google.javascript.jscomp.Scope;
import com.google.javascript.jscomp.TypedScopeCreator;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.jstype.JSType;
import com.google.javascript.rhino.jstype.JSTypeRegistry;
import java.util.LinkedList;
import java.util.List;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class TypedScopeCreator_ESTest extends TypedScopeCreator_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.parseSyntheticCode("p$>b", "p$>b");
        Node node1 = new Node(37, node0, node0, node0);
        TypedScopeCreator typedScopeCreator0 = new TypedScopeCreator(compiler0);
        Scope scope0 = typedScopeCreator0.createScope(node1, (Scope) null);
        scope0.isLocal();
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        Compiler compiler0 = new Compiler();
        compiler0.parseTestCode("Y");
        Node node0 = new Node(120);
        TypedScopeCreator typedScopeCreator0 = new TypedScopeCreator(compiler0);
        typedScopeCreator0.createScope(node0, (Scope) null);
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.parseTestCode("mg.unde.wit");
        Node node1 = new Node(64);
        Node node2 = new Node(4, node0, node0, node1);
        TypedScopeCreator typedScopeCreator0 = new TypedScopeCreator(compiler0);
        Scope scope0 = typedScopeCreator0.createScope(node2, (Scope) null);
        scope0.getVarCount();
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.parseTestCode("icj=OiO/Zrzc&-~UIaz");
        Node node1 = new Node(1, node0, node0, node0);
        TypedScopeCreator typedScopeCreator0 = new TypedScopeCreator(compiler0);
        typedScopeCreator0.patchGlobalScope((Scope) null, node1);
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.parseTestCode("7c3hN");
        TypedScopeCreator typedScopeCreator0 = new TypedScopeCreator(compiler0);
        Scope scope0 = typedScopeCreator0.createInitialScope(node0);
        typedScopeCreator0.patchGlobalScope(scope0, node0);
        scope0.getVarCount();
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        Compiler compiler0 = new Compiler();
        JSSourceFile jSSourceFile0 = JSSourceFile.fromFile("<non-file>");
        Node node0 = compiler0.parse(jSSourceFile0);
        TypedScopeCreator typedScopeCreator0 = new TypedScopeCreator(compiler0, (CodingConvention) null);
        Scope scope0 = typedScopeCreator0.createInitialScope(node0);
        typedScopeCreator0.patchGlobalScope(scope0, node0);
        scope0.getVarCount();
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.parseTestCode("pmag.unef=vt");
        TypedScopeCreator typedScopeCreator0 = new TypedScopeCreator(compiler0);
        Scope scope0 = typedScopeCreator0.createScope(node0, (Scope) null);
        scope0.getVarCount();
    }

    @Test(timeout = 4000)
    public void test077() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.parseTestCode("1#zQ d'r~-&Z7jj^>1{");
        Node node1 = new Node(105, node0, node0, node0);
        TypedScopeCreator typedScopeCreator0 = new TypedScopeCreator(compiler0);
        Scope scope0 = typedScopeCreator0.createInitialScope(node0);
        typedScopeCreator0.createScope(node1, scope0);
    }

    @Test(timeout = 4000)
    public void test088() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.parseTestCode("icj=OiO/Zrzc&-~UIaz");
        TypedScopeCreator typedScopeCreator0 = new TypedScopeCreator(compiler0);
        Scope scope0 = typedScopeCreator0.createScope(node0, (Scope) null);
        scope0.isLocal();
    }

    @Test(timeout = 4000)
    public void test099() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.parseSyntheticCode("p$GLbi[", "p$GLbi[");
        Node node1 = new Node(41, node0, node0, node0);
        TypedScopeCreator typedScopeCreator0 = new TypedScopeCreator(compiler0);
        Scope scope0 = typedScopeCreator0.createScope(node1, (Scope) null);
        scope0.isGlobal();
    }

    @Test(timeout = 4000)
    public void test1010() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.parseTestCode(":hcku>@6J$%4lLm");
        Node node1 = new Node(43, node0, node0, node0);
        TypedScopeCreator typedScopeCreator0 = new TypedScopeCreator(compiler0);
        Scope scope0 = typedScopeCreator0.createScope(node1, (Scope) null);
        scope0.isGlobal();
    }

    @Test(timeout = 4000)
    public void test1111() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.parseTestCode(" ");
        Node node1 = new Node(44, node0, node0, node0);
        TypedScopeCreator typedScopeCreator0 = new TypedScopeCreator(compiler0);
        Scope scope0 = typedScopeCreator0.createScope(node1, (Scope) null);
        scope0.getVarCount();
    }

    @Test(timeout = 4000)
    public void test1212() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.parseTestCode(":hcku>@c<6J$%4VLm");
        Node node1 = new Node(47, node0, node0, node0);
        TypedScopeCreator typedScopeCreator0 = new TypedScopeCreator(compiler0);
        Scope scope0 = typedScopeCreator0.createScope(node1, (Scope) null);
        scope0.getVarCount();
    }

    @Test(timeout = 4000)
    public void test1313() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.parseTestCode("com.googA.comYn.io.Betreams");
        Node node1 = new Node(69, node0, node0, node0);
        TypedScopeCreator typedScopeCreator0 = new TypedScopeCreator(compiler0);
        Scope scope0 = typedScopeCreator0.createScope(node1, (Scope) null);
        scope0.getVarCount();
    }

    @Test(timeout = 4000)
    public void test1414() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.parseTestCode("SUPPRESS_DOC");
        Node node1 = new Node(122, node0, node0, node0);
        TypedScopeCreator typedScopeCreator0 = new TypedScopeCreator(compiler0);
        Scope scope0 = typedScopeCreator0.createScope(node1, (Scope) null);
        scope0.isLocal();
    }

    @Test(timeout = 4000)
    public void test1515() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.parseSyntheticCode("5&r", "5&r");
        node0.addSuppression("5&r");
        Node node1 = new Node(118, node0, node0, node0);
        TypedScopeCreator typedScopeCreator0 = new TypedScopeCreator(compiler0);
        typedScopeCreator0.createScope(node1, (Scope) null);
    }

    @Test(timeout = 4000)
    public void test1616() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.parseTestCode("lI@NSM");
        Node node1 = compiler0.parseSyntheticCode("lI@NSM");
        Node node2 = new Node(118, node0, node0, node1);
        TypedScopeCreator typedScopeCreator0 = new TypedScopeCreator(compiler0);
        typedScopeCreator0.createScope(node2, (Scope) null);
    }

    @Test(timeout = 4000)
    public void test1717() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.parseTestCode("lI@NSM");
        Node node1 = compiler0.parseSyntheticCode("lI@NSM");
        Node node2 = new Node(118, node0, node0, node1);
        node2.addSuppression("lI@NSM");
        TypedScopeCreator typedScopeCreator0 = new TypedScopeCreator(compiler0);
        typedScopeCreator0.createScope(node2, (Scope) null);
    }

    @Test(timeout = 4000)
    public void test1818() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.parseSyntheticCode("5&r", "5&r");
        node0.addSuppression("5&r");
        Node node1 = new Node(118, node0, node0, node0);
        TypedScopeCreator typedScopeCreator0 = new TypedScopeCreator(compiler0);
        node1.clonePropsFrom(node0);
        typedScopeCreator0.createScope(node1, (Scope) null);
    }

    @Test(timeout = 4000)
    public void test1919() throws Throwable {
        Compiler compiler0 = new Compiler();
        compiler0.parseTestCode("com.google.javascript.jscomp.mozilla.rhino.ast.ErrorNode");
        Node node0 = compiler0.parseSyntheticCode("com.google.javascript.jscomp.mozilla.rhino.ast.ErrorNode", "com.google.javascript.jscomp.mozilla.rhino.ast.ErrorNode");
        TypedScopeCreator typedScopeCreator0 = new TypedScopeCreator(compiler0);
        Scope scope0 = typedScopeCreator0.createScope(node0, (Scope) null);
        scope0.getVarCount();
    }

    @Test(timeout = 4000)
    public void test2020() throws Throwable {
        Compiler compiler0 = new Compiler();
        JSSourceFile jSSourceFile0 = JSSourceFile.fromCode("com.google.common.io.ByteStreams", "com.google.common.io.ByteStreams");
        Node node0 = compiler0.parse(jSSourceFile0);
        LinkedList<JSSourceFile> linkedList0 = new LinkedList<JSSourceFile>();
        linkedList0.add(jSSourceFile0);
        CompilerOptions compilerOptions0 = new CompilerOptions();
        compiler0.init((List<JSSourceFile>) linkedList0, (List<JSSourceFile>) linkedList0, compilerOptions0);
        TypedScopeCreator typedScopeCreator0 = new TypedScopeCreator(compiler0, (CodingConvention) null);
        typedScopeCreator0.createScope(node0, (Scope) null);
    }

    @Test(timeout = 4000)
    public void test2121() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.parseTestCode("com.google.javascript.jscomp.TypedScopeCreator$AbstractScopeBulder$CollectProperti;s");
        Node node1 = compiler0.parseTestCode("com.google.javascript.jscomp.TypedScopeCreator$AbstractScopeBulder$CollectProperti;s");
        Node node2 = new Node(104, node0, node0, node1);
        TypedScopeCreator typedScopeCreator0 = new TypedScopeCreator(compiler0);
        Scope scope0 = typedScopeCreator0.createScope(node2, (Scope) null);
        scope0.getVarCount();
    }

    @Test(timeout = 4000)
    public void test2222() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.parseTestCode("com.google.javascript.jscomp.InlineFunctions$FindCandidatesReferences");
        JSTypeRegistry jSTypeRegistry0 = compiler0.getTypeRegistry();
        LinkedList<JSType> linkedList0 = new LinkedList<JSType>();
        Node node1 = jSTypeRegistry0.createParameters((List<JSType>) linkedList0);
        Node node2 = new Node(4, node1, node0);
        TypedScopeCreator typedScopeCreator0 = new TypedScopeCreator(compiler0);
        Scope scope0 = typedScopeCreator0.createInitialScope(node0);
        typedScopeCreator0.createScope(node2, scope0);
    }

    @Test(timeout = 4000)
    public void test2323() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.parseTestCode("com.google.javascript.jscomp.InlineFunctions$FindCandidatesReferences");
        JSTypeRegistry jSTypeRegistry0 = compiler0.getTypeRegistry();
        LinkedList<JSType> linkedList0 = new LinkedList<JSType>();
        Node node1 = jSTypeRegistry0.createParameters((List<JSType>) linkedList0);
        Node node2 = new Node(4, node1, node0);
        TypedScopeCreator typedScopeCreator0 = new TypedScopeCreator(compiler0);
        Scope scope0 = typedScopeCreator0.createInitialScope(node0);
        Node node3 = new Node(14, node2, 2, 4095);
        Scope scope1 = typedScopeCreator0.createScope(node3, scope0);
        scope1.getVarCount();
    }
}
