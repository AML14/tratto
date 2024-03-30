/*
 * This file was automatically generated by EvoSuite
 * Sat Nov 04 07:54:36 GMT 2023
 */
package com.google.javascript.rhino.jstype;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.google.javascript.rhino.ErrorReporter;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.SimpleErrorReporter;
import com.google.javascript.rhino.jstype.ArrowType;
import com.google.javascript.rhino.jstype.ErrorFunctionType;
import com.google.javascript.rhino.jstype.IndexedType;
import com.google.javascript.rhino.jstype.JSType;
import com.google.javascript.rhino.jstype.JSTypeRegistry;
import com.google.javascript.rhino.jstype.NoResolvedType;
import com.google.javascript.rhino.jstype.NoType;
import com.google.javascript.rhino.jstype.Visitor;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class ArrowType_ESTest extends ArrowType_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry((ErrorReporter) null, true);
        ErrorFunctionType errorFunctionType0 = new ErrorFunctionType(jSTypeRegistry0, "ASSIGN_URSH");
        ArrowType arrowType0 = errorFunctionType0.getInternalArrowType();
        arrowType0.returnType = null;
        boolean boolean0 = arrowType0.hasUnknownParamsOrReturn();
        errorFunctionType0.isReturnTypeInferred();
        assertTrue(errorFunctionType0.isReturnTypeInferred());
    }

    @Test(timeout = 4000)
    public void test001() throws Throwable {
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry((ErrorReporter) null, true);
        ErrorFunctionType errorFunctionType0 = new ErrorFunctionType(jSTypeRegistry0, "ASSIGN_URSH");
        ArrowType arrowType0 = errorFunctionType0.getInternalArrowType();
        arrowType0.returnType = null;
        boolean boolean0 = arrowType0.hasUnknownParamsOrReturn();
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test012() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0, true);
        ErrorFunctionType errorFunctionType0 = new ErrorFunctionType(jSTypeRegistry0, "9?:g:zS~,yOWV<_");
        ArrowType arrowType0 = errorFunctionType0.getInternalArrowType();
        arrowType0.testForEquality(errorFunctionType0);
    }

    @Test(timeout = 4000)
    public void test023() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        ErrorFunctionType errorFunctionType0 = new ErrorFunctionType(jSTypeRegistry0, "");
        ArrowType arrowType0 = errorFunctionType0.getInternalArrowType();
        arrowType0.getTypesUnderShallowEquality(errorFunctionType0);
    }

    @Test(timeout = 4000)
    public void test034() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        ErrorFunctionType errorFunctionType0 = new ErrorFunctionType(jSTypeRegistry0, "GG:s`");
        ArrowType arrowType0 = errorFunctionType0.getInternalArrowType();
        arrowType0.getLeastSupertype(errorFunctionType0);
    }

    @Test(timeout = 4000)
    public void test045() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        ArrowType arrowType0 = jSTypeRegistry0.createArrowType((Node) null);
        arrowType0.toString();
    }

    @Test(timeout = 4000)
    public void test056() throws Throwable {
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry((ErrorReporter) null);
        ArrowType arrowType0 = jSTypeRegistry0.createArrowType((Node) null);
        arrowType0.visit((Visitor<IndexedType>) null);
    }

    @Test(timeout = 4000)
    public void test067() throws Throwable {
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry((ErrorReporter) null);
        ArrowType arrowType0 = jSTypeRegistry0.createArrowType((Node) null);
        NoType noType0 = (NoType) arrowType0.getRestrictedTypeGivenToBooleanOutcome(false);
        noType0.isReturnTypeInferred();
    }

    @Test(timeout = 4000)
    public void test078() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        JSType[] jSTypeArray0 = new JSType[1];
        Node node0 = jSTypeRegistry0.createParameters(jSTypeArray0);
        ArrowType arrowType0 = jSTypeRegistry0.createArrowType(node0);
        boolean boolean0 = arrowType0.isSubtype(arrowType0);
    }

    @Test(timeout = 4000)
    public void test089() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        ArrowType arrowType0 = jSTypeRegistry0.createArrowType((Node) null);
        ArrowType arrowType1 = new ArrowType(jSTypeRegistry0, (Node) null, arrowType0);
        boolean boolean0 = arrowType1.isSubtype(arrowType0);
    }

    @Test(timeout = 4000)
    public void test0910() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        ArrowType arrowType0 = jSTypeRegistry0.createArrowType((Node) null);
        ErrorFunctionType errorFunctionType0 = new ErrorFunctionType(jSTypeRegistry0, "Not declared as a constructor");
        ArrowType arrowType1 = errorFunctionType0.getInternalArrowType();
        JSType jSType0 = JSType.getLeastSupertype((JSType) arrowType1, (JSType) arrowType0);
        errorFunctionType0.isReturnTypeInferred();
        assertTrue(errorFunctionType0.isReturnTypeInferred());
    }

    @Test(timeout = 4000)
    public void test0911() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        ArrowType arrowType0 = jSTypeRegistry0.createArrowType((Node) null);
        ErrorFunctionType errorFunctionType0 = new ErrorFunctionType(jSTypeRegistry0, "Not declared as a constructor");
        ArrowType arrowType1 = errorFunctionType0.getInternalArrowType();
        JSType jSType0 = JSType.getLeastSupertype((JSType) arrowType1, (JSType) arrowType0);
        jSType0.equals((Object) arrowType0);
    }

    @Test(timeout = 4000)
    public void test0912() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        ArrowType arrowType0 = jSTypeRegistry0.createArrowType((Node) null);
        ErrorFunctionType errorFunctionType0 = new ErrorFunctionType(jSTypeRegistry0, "Not declared as a constructor");
        ArrowType arrowType1 = errorFunctionType0.getInternalArrowType();
        JSType jSType0 = JSType.getLeastSupertype((JSType) arrowType1, (JSType) arrowType0);
    }

    @Test(timeout = 4000)
    public void test1013() throws Throwable {
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry((ErrorReporter) null);
        ErrorFunctionType errorFunctionType0 = new ErrorFunctionType(jSTypeRegistry0, "already specified useForNull");
        ArrowType arrowType0 = errorFunctionType0.getInternalArrowType();
        Node node0 = Node.newString(1, "Unknown class name");
        Node node1 = new Node(1, node0, node0, node0, node0);
        ArrowType arrowType1 = jSTypeRegistry0.createArrowType(node1);
        boolean boolean0 = arrowType0.isSubtype(arrowType1);
    }

    @Test(timeout = 4000)
    public void test1014() throws Throwable {
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry((ErrorReporter) null);
        ErrorFunctionType errorFunctionType0 = new ErrorFunctionType(jSTypeRegistry0, "already specified useForNull");
        ArrowType arrowType0 = errorFunctionType0.getInternalArrowType();
        Node node0 = Node.newString(1, "Unknown class name");
        Node node1 = new Node(1, node0, node0, node0, node0);
        ArrowType arrowType1 = jSTypeRegistry0.createArrowType(node1);
        boolean boolean0 = arrowType0.isSubtype(arrowType1);
        errorFunctionType0.hasCachedValues();
        assertFalse(errorFunctionType0.hasCachedValues());
    }

    @Test(timeout = 4000)
    public void test1015() throws Throwable {
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry((ErrorReporter) null);
        ErrorFunctionType errorFunctionType0 = new ErrorFunctionType(jSTypeRegistry0, "already specified useForNull");
        ArrowType arrowType0 = errorFunctionType0.getInternalArrowType();
        Node node0 = Node.newString(1, "Unknown class name");
        Node node1 = new Node(1, node0, node0, node0, node0);
        ArrowType arrowType1 = jSTypeRegistry0.createArrowType(node1);
        boolean boolean0 = arrowType0.isSubtype(arrowType1);
        errorFunctionType0.isReturnTypeInferred();
    }

    @Test(timeout = 4000)
    public void test1116() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        ErrorFunctionType errorFunctionType0 = new ErrorFunctionType(jSTypeRegistry0, "W2JoTp4|YTwH");
        JSType[] jSTypeArray0 = new JSType[1];
        jSTypeArray0[0] = (JSType) errorFunctionType0;
        Node node0 = jSTypeRegistry0.createParameters(jSTypeArray0);
        ArrowType arrowType0 = jSTypeRegistry0.createArrowType(node0);
        ArrowType arrowType1 = errorFunctionType0.getInternalArrowType();
        boolean boolean0 = arrowType0.isSubtype(arrowType1);
        errorFunctionType0.hasCachedValues();
        assertFalse(errorFunctionType0.hasCachedValues());
    }

    @Test(timeout = 4000)
    public void test1117() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        ErrorFunctionType errorFunctionType0 = new ErrorFunctionType(jSTypeRegistry0, "W2JoTp4|YTwH");
        JSType[] jSTypeArray0 = new JSType[1];
        jSTypeArray0[0] = (JSType) errorFunctionType0;
        Node node0 = jSTypeRegistry0.createParameters(jSTypeArray0);
        ArrowType arrowType0 = jSTypeRegistry0.createArrowType(node0);
        ArrowType arrowType1 = errorFunctionType0.getInternalArrowType();
        boolean boolean0 = arrowType0.isSubtype(arrowType1);
    }

    @Test(timeout = 4000)
    public void test1218() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        ArrowType arrowType0 = jSTypeRegistry0.createArrowType((Node) null);
        boolean boolean0 = arrowType0.isSubtype(arrowType0);
    }

    @Test(timeout = 4000)
    public void test1319() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        ArrowType arrowType0 = jSTypeRegistry0.createArrowType((Node) null);
        JSType jSType0 = JSType.getLeastSupertype((JSType) arrowType0, (JSType) arrowType0);
        jSType0.isNoType();
        assertTrue(jSType0.isNoType());
    }

    @Test(timeout = 4000)
    public void test1420() throws Throwable {
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry((ErrorReporter) null);
        NoResolvedType noResolvedType0 = new NoResolvedType(jSTypeRegistry0);
        Node node0 = Node.newString("Not declared as a constructor", 0, 1);
        Node node1 = new Node(0, node0);
        ArrowType arrowType0 = jSTypeRegistry0.createArrowType(node1, noResolvedType0);
        boolean boolean0 = arrowType0.hasEqualParameters(arrowType0);
        noResolvedType0.isReturnTypeInferred();
    }

    @Test(timeout = 4000)
    public void test1421() throws Throwable {
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry((ErrorReporter) null);
        NoResolvedType noResolvedType0 = new NoResolvedType(jSTypeRegistry0);
        Node node0 = Node.newString("Not declared as a constructor", 0, 1);
        Node node1 = new Node(0, node0);
        ArrowType arrowType0 = jSTypeRegistry0.createArrowType(node1, noResolvedType0);
        boolean boolean0 = arrowType0.hasEqualParameters(arrowType0);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test1522() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0, true);
        ErrorFunctionType errorFunctionType0 = new ErrorFunctionType(jSTypeRegistry0, "S#AI-o'u91%9~:");
        ArrowType arrowType0 = errorFunctionType0.getInternalArrowType();
        Node node0 = Node.newString("Unknown class name", 0, 1);
        Node node1 = new Node(0, node0, node0, node0, node0, 4095, 50);
        ArrowType arrowType1 = jSTypeRegistry0.createArrowType(node1);
        boolean boolean0 = arrowType0.hasEqualParameters(arrowType1);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test1523() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0, true);
        ErrorFunctionType errorFunctionType0 = new ErrorFunctionType(jSTypeRegistry0, "S#AI-o'u91%9~:");
        ArrowType arrowType0 = errorFunctionType0.getInternalArrowType();
        Node node0 = Node.newString("Unknown class name", 0, 1);
        Node node1 = new Node(0, node0, node0, node0, node0, 4095, 50);
        ArrowType arrowType1 = jSTypeRegistry0.createArrowType(node1);
        boolean boolean0 = arrowType0.hasEqualParameters(arrowType1);
        errorFunctionType0.isReturnTypeInferred();
        assertTrue(errorFunctionType0.isReturnTypeInferred());
    }

    @Test(timeout = 4000)
    public void test1624() throws Throwable {
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry((ErrorReporter) null);
        ArrowType arrowType0 = jSTypeRegistry0.createArrowType((Node) null);
        JSTypeRegistry jSTypeRegistry1 = new JSTypeRegistry((ErrorReporter) null, true);
        ArrowType arrowType1 = jSTypeRegistry1.createArrowType((Node) null);
        boolean boolean0 = arrowType0.hasEqualParameters(arrowType1);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test1725() throws Throwable {
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry((ErrorReporter) null);
        NoResolvedType noResolvedType0 = new NoResolvedType(jSTypeRegistry0);
        Node node0 = Node.newString("Not declared as a constructor", 0, 1);
        Node node1 = new Node(0, node0);
        ArrowType arrowType0 = jSTypeRegistry0.createArrowType(node1, noResolvedType0);
        ErrorFunctionType errorFunctionType0 = new ErrorFunctionType(jSTypeRegistry0, "Not declared as a type name");
        ArrowType arrowType1 = errorFunctionType0.getInternalArrowType();
        boolean boolean0 = arrowType0.hasEqualParameters(arrowType1);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test1726() throws Throwable {
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry((ErrorReporter) null);
        NoResolvedType noResolvedType0 = new NoResolvedType(jSTypeRegistry0);
        Node node0 = Node.newString("Not declared as a constructor", 0, 1);
        Node node1 = new Node(0, node0);
        ArrowType arrowType0 = jSTypeRegistry0.createArrowType(node1, noResolvedType0);
        ErrorFunctionType errorFunctionType0 = new ErrorFunctionType(jSTypeRegistry0, "Not declared as a type name");
        ArrowType arrowType1 = errorFunctionType0.getInternalArrowType();
        boolean boolean0 = arrowType0.hasEqualParameters(arrowType1);
        errorFunctionType0.isReturnTypeInferred();
        assertTrue(errorFunctionType0.isReturnTypeInferred());
    }

    @Test(timeout = 4000)
    public void test1827() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        ArrowType arrowType0 = jSTypeRegistry0.createArrowType((Node) null);
        ArrowType arrowType1 = new ArrowType(jSTypeRegistry0, (Node) null, arrowType0);
        boolean boolean0 = arrowType1.equals(arrowType0);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test2028() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        ArrowType arrowType0 = jSTypeRegistry0.createArrowType((Node) null);
        ArrowType arrowType1 = new ArrowType(jSTypeRegistry0, (Node) null, arrowType0, true);
        arrowType1.hashCode();
        arrowType1.equals((Object) arrowType0);
    }

    @Test(timeout = 4000)
    public void test2129() throws Throwable {
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry((ErrorReporter) null);
        NoResolvedType noResolvedType0 = new NoResolvedType(jSTypeRegistry0);
        Node node0 = Node.newString("Not declared as a constructor", 0, 1);
        Node node1 = new Node(0, node0);
        ArrowType arrowType0 = jSTypeRegistry0.createArrowType(node1, noResolvedType0);
        arrowType0.hashCode();
        noResolvedType0.isReturnTypeInferred();
        assertTrue(noResolvedType0.isReturnTypeInferred());
    }

    @Test(timeout = 4000)
    public void test2230() throws Throwable {
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry((ErrorReporter) null);
        NoResolvedType noResolvedType0 = new NoResolvedType(jSTypeRegistry0);
        Node node0 = Node.newString("Not declared as a constructor", 0, 1);
        ArrowType arrowType0 = jSTypeRegistry0.createArrowType(node0, noResolvedType0);
        ErrorFunctionType errorFunctionType0 = new ErrorFunctionType(jSTypeRegistry0, "Not declared as a type name");
        arrowType0.resolveInternal((ErrorReporter) null, errorFunctionType0);
        noResolvedType0.isResolved();
        assertTrue(noResolvedType0.isResolved());
    }

    @Test(timeout = 4000)
    public void test2331() throws Throwable {
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry((ErrorReporter) null);
        NoResolvedType noResolvedType0 = new NoResolvedType(jSTypeRegistry0);
        Node node0 = Node.newString("Not declared as a constructor", 0, 1);
        Node node1 = new Node(0, node0);
        ArrowType arrowType0 = jSTypeRegistry0.createArrowType(node1, noResolvedType0);
        ErrorFunctionType errorFunctionType0 = new ErrorFunctionType(jSTypeRegistry0, "Not declared as a type name");
        arrowType0.resolveInternal((ErrorReporter) null, errorFunctionType0);
    }

    @Test(timeout = 4000)
    public void test2432() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        JSType[] jSTypeArray0 = new JSType[1];
        Node node0 = jSTypeRegistry0.createParameters(jSTypeArray0);
        ArrowType arrowType0 = jSTypeRegistry0.createArrowType(node0);
        boolean boolean0 = arrowType0.hasUnknownParamsOrReturn();
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test2533() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        ErrorFunctionType errorFunctionType0 = new ErrorFunctionType(jSTypeRegistry0, "W2JoTp4|YTwH");
        errorFunctionType0.isReturnTypeInferred();
        assertTrue(errorFunctionType0.isReturnTypeInferred());
    }

    @Test(timeout = 4000)
    public void test2534() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        ErrorFunctionType errorFunctionType0 = new ErrorFunctionType(jSTypeRegistry0, "W2JoTp4|YTwH");
        JSType[] jSTypeArray0 = new JSType[1];
        jSTypeArray0[0] = (JSType) errorFunctionType0;
        Node node0 = jSTypeRegistry0.createParameters(jSTypeArray0);
        ArrowType arrowType0 = jSTypeRegistry0.createArrowType(node0);
        boolean boolean0 = arrowType0.hasUnknownParamsOrReturn();
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test2635() throws Throwable {
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry((ErrorReporter) null);
        ArrowType arrowType0 = jSTypeRegistry0.createArrowType((Node) null);
        boolean boolean0 = arrowType0.hasUnknownParamsOrReturn();
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test2736() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        ErrorFunctionType errorFunctionType0 = new ErrorFunctionType(jSTypeRegistry0, "W2JoTp4|YTwH");
        ArrowType arrowType0 = errorFunctionType0.getInternalArrowType();
        boolean boolean0 = arrowType0.hasUnknownParamsOrReturn();
        errorFunctionType0.hasCachedValues();
        assertTrue(errorFunctionType0.hasCachedValues());
    }

    @Test(timeout = 4000)
    public void test2737() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        ErrorFunctionType errorFunctionType0 = new ErrorFunctionType(jSTypeRegistry0, "W2JoTp4|YTwH");
        ArrowType arrowType0 = errorFunctionType0.getInternalArrowType();
        boolean boolean0 = arrowType0.hasUnknownParamsOrReturn();
        assertTrue(boolean0);
    }
}
