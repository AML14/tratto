/*
 * This file was automatically generated by EvoSuite
 * Sun Dec 31 06:52:20 GMT 2023
 */
package com.google.javascript.rhino.jstype;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.javascript.rhino.SimpleErrorReporter;
import com.google.javascript.rhino.jstype.AllType;
import com.google.javascript.rhino.jstype.BooleanLiteralSet;
import com.google.javascript.rhino.jstype.ErrorFunctionType;
import com.google.javascript.rhino.jstype.FunctionType;
import com.google.javascript.rhino.jstype.JSType;
import com.google.javascript.rhino.jstype.JSTypeRegistry;
import com.google.javascript.rhino.jstype.ModificationVisitor;
import com.google.javascript.rhino.jstype.NamedType;
import com.google.javascript.rhino.jstype.NoObjectType;
import com.google.javascript.rhino.jstype.NoResolvedType;
import com.google.javascript.rhino.jstype.NoType;
import com.google.javascript.rhino.jstype.NullType;
import com.google.javascript.rhino.jstype.NumberType;
import com.google.javascript.rhino.jstype.ParameterizedType;
import com.google.javascript.rhino.jstype.StaticScope;
import com.google.javascript.rhino.jstype.StringType;
import com.google.javascript.rhino.jstype.TemplateType;
import com.google.javascript.rhino.jstype.UnionType;
import com.google.javascript.rhino.jstype.VoidType;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Stack;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class UnionType_ESTest extends UnionType_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0, true);
        NoResolvedType noResolvedType0 = new NoResolvedType(jSTypeRegistry0);
        JSType jSType0 = jSTypeRegistry0.createOptionalType(noResolvedType0);
        ModificationVisitor modificationVisitor0 = new ModificationVisitor(jSTypeRegistry0);
        ParameterizedType parameterizedType0 = jSTypeRegistry0.createParameterizedType(noResolvedType0, jSType0);
        JSType jSType1 = modificationVisitor0.caseParameterizedType(parameterizedType0);
        jSType1.isEnumElementType();
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        NoObjectType noObjectType0 = new NoObjectType(jSTypeRegistry0);
        JSType jSType0 = jSTypeRegistry0.createOptionalType(noObjectType0);
        ImmutableList<JSType> immutableList0 = ImmutableList.of((JSType) noObjectType0, (JSType) noObjectType0, jSType0, (JSType) noObjectType0, (JSType) noObjectType0, (JSType) noObjectType0);
        UnionType unionType0 = new UnionType(jSTypeRegistry0, immutableList0);
        JSType jSType1 = unionType0.collapseUnion();
        JSType.TypePair jSType_TypePair0 = unionType0.getTypesUnderInequality(jSType1);
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        LinkedHashSet<JSType> linkedHashSet0 = new LinkedHashSet<JSType>();
        UnionType unionType0 = new UnionType((JSTypeRegistry) null, linkedHashSet0);
        boolean boolean0 = linkedHashSet0.add(unionType0);
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        ArrayDeque<JSType> arrayDeque0 = new ArrayDeque<JSType>();
        AllType allType0 = new AllType((JSTypeRegistry) null);
        arrayDeque0.add(allType0);
        UnionType unionType0 = new UnionType((JSTypeRegistry) null, arrayDeque0);
        boolean boolean0 = unionType0.matchesNumberContext();
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        StringType stringType0 = new StringType((JSTypeRegistry) null);
        ImmutableList<JSType> immutableList0 = ImmutableList.of((JSType) stringType0, (JSType) stringType0, (JSType) stringType0);
        UnionType unionType0 = new UnionType((JSTypeRegistry) null, immutableList0);
        boolean boolean0 = unionType0.matchesInt32Context();
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        ArrayDeque<JSType> arrayDeque0 = new ArrayDeque<JSType>();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        UnionType unionType0 = new UnionType(jSTypeRegistry0, arrayDeque0);
        NoResolvedType noResolvedType0 = new NoResolvedType(jSTypeRegistry0);
        ImmutableList<JSType> immutableList0 = ImmutableList.of((JSType) unionType0, (JSType) noResolvedType0, (JSType) noResolvedType0, (JSType) noResolvedType0, (JSType) unionType0, (JSType) noResolvedType0, (JSType) noResolvedType0);
        UnionType unionType1 = new UnionType(jSTypeRegistry0, immutableList0);
        boolean boolean0 = unionType1.matchesStringContext();
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0, false);
        VoidType voidType0 = new VoidType(jSTypeRegistry0);
        ImmutableList<VoidType> immutableList0 = ImmutableList.of(voidType0, voidType0, voidType0, voidType0, voidType0, voidType0, voidType0, voidType0, voidType0, voidType0, voidType0);
        ImmutableList<JSType> immutableList1 = ImmutableList.copyOf((Collection<? extends JSType>) immutableList0);
        UnionType unionType0 = new UnionType(jSTypeRegistry0, immutableList1);
        boolean boolean0;
        boolean0 = unionType0.matchesObjectContext();
        assertTrue((unionType0.isNullType()) == false ? boolean0 == true : boolean0 == false);
    }

    @Test(timeout = 4000)
    public void test077() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        NoObjectType noObjectType0 = new NoObjectType(jSTypeRegistry0);
        JSType jSType0 = jSTypeRegistry0.createOptionalType(noObjectType0);
        boolean boolean0;
        boolean0 = jSType0.matchesObjectContext();
        assertTrue((jSType0.isNullType()) == false ? boolean0 == true : boolean0 == false);
    }

    @Test(timeout = 4000)
    public void test088() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0, false);
        NullType nullType0 = new NullType(jSTypeRegistry0);
        ImmutableList<JSType> immutableList0 = ImmutableList.of((JSType) nullType0, (JSType) nullType0, (JSType) nullType0);
        UnionType unionType0 = new UnionType(jSTypeRegistry0, immutableList0);
        JSType jSType0 = unionType0.findPropertyType("Not declared as a type name");
    }

    @Test(timeout = 4000)
    public void test099() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0, false);
        NoResolvedType noResolvedType0 = new NoResolvedType(jSTypeRegistry0);
        JSType jSType0 = jSTypeRegistry0.createOptionalType(noResolvedType0);
        JSType jSType1 = jSType0.findPropertyType("Not declared as a type name");
        jSType1.isConstructor();
    }

    @Test(timeout = 4000)
    public void test1010() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        ArrayDeque<JSType> arrayDeque0 = new ArrayDeque<JSType>();
        StringType stringType0 = new StringType(jSTypeRegistry0);
        arrayDeque0.add(stringType0);
        UnionType unionType0 = new UnionType(jSTypeRegistry0, arrayDeque0);
        JSType jSType0 = unionType0.findPropertyType("Named type with empty name component");
    }

    @Test(timeout = 4000)
    public void test1111() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        TemplateType templateType0 = new TemplateType(jSTypeRegistry0, "eM&");
        ImmutableList<JSType> immutableList0 = ImmutableList.of((JSType) templateType0, (JSType) templateType0, (JSType) templateType0, (JSType) templateType0);
        UnionType unionType0 = new UnionType(jSTypeRegistry0, immutableList0);
        JSType jSType0 = unionType0.findPropertyType("Not declared as a constructor");
        jSType0.isParameterizedType();
    }

    @Test(timeout = 4000)
    public void test1212() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0, false);
        VoidType voidType0 = new VoidType(jSTypeRegistry0);
        ImmutableList<VoidType> immutableList0 = ImmutableList.of(voidType0, voidType0, voidType0, voidType0, voidType0, voidType0, voidType0, voidType0, voidType0, voidType0, voidType0);
        ImmutableList<JSType> immutableList1 = ImmutableList.copyOf((Collection<? extends JSType>) immutableList0);
        UnionType unionType0 = new UnionType(jSTypeRegistry0, immutableList1);
        NoType noType0 = (NoType) unionType0.getRestrictedTypeGivenToBooleanOutcome(true);
        boolean boolean0 = unionType0.canAssignTo(noType0);
        noType0.hasCachedValues();
    }

    @Test(timeout = 4000)
    public void test1213() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0, false);
        VoidType voidType0 = new VoidType(jSTypeRegistry0);
        ImmutableList<VoidType> immutableList0 = ImmutableList.of(voidType0, voidType0, voidType0, voidType0, voidType0, voidType0, voidType0, voidType0, voidType0, voidType0, voidType0);
        ImmutableList<JSType> immutableList1 = ImmutableList.copyOf((Collection<? extends JSType>) immutableList0);
        UnionType unionType0 = new UnionType(jSTypeRegistry0, immutableList1);
        NoType noType0 = (NoType) unionType0.getRestrictedTypeGivenToBooleanOutcome(true);
        boolean boolean0 = unionType0.canAssignTo(noType0);
    }

    @Test(timeout = 4000)
    public void test1314() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        TemplateType templateType0 = new TemplateType(jSTypeRegistry0, "eM&");
        ImmutableList<JSType> immutableList0 = ImmutableList.of((JSType) templateType0, (JSType) templateType0, (JSType) templateType0, (JSType) templateType0);
        UnionType unionType0 = new UnionType(jSTypeRegistry0, immutableList0);
        boolean boolean0 = unionType0.canAssignTo(templateType0);
    }

    @Test(timeout = 4000)
    public void test1415() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        ArrayDeque<JSType> arrayDeque0 = new ArrayDeque<JSType>();
        UnionType unionType0 = new UnionType(jSTypeRegistry0, arrayDeque0);
        boolean boolean0 = unionType0.canBeCalled();
    }

    @Test(timeout = 4000)
    public void test1516() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0, false);
        NoResolvedType noResolvedType0 = new NoResolvedType(jSTypeRegistry0);
        JSType jSType0 = jSTypeRegistry0.createOptionalType(noResolvedType0);
        boolean boolean0 = jSType0.canBeCalled();
    }

    @Test(timeout = 4000)
    public void test1617() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0, true);
        NoResolvedType noResolvedType0 = new NoResolvedType(jSTypeRegistry0);
        JSType jSType0 = jSTypeRegistry0.createOptionalType(noResolvedType0);
        JSType jSType1 = jSType0.restrictByNotNullOrUndefined();
        jSType1.isParameterizedType();
    }

    @Test(timeout = 4000)
    public void test1918() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0, true);
        NoObjectType noObjectType0 = new NoObjectType(jSTypeRegistry0);
        JSType jSType0 = jSTypeRegistry0.createOptionalType(noObjectType0);
        boolean boolean0 = jSType0.isNullable();
    }

    @Test(timeout = 4000)
    public void test2019() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0, false);
        NoResolvedType noResolvedType0 = new NoResolvedType(jSTypeRegistry0);
        JSType jSType0 = jSTypeRegistry0.createOptionalType(noResolvedType0);
        boolean boolean0 = jSType0.isNullable();
    }

    @Test(timeout = 4000)
    public void test2120() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        TemplateType templateType0 = new TemplateType(jSTypeRegistry0, "eM&");
        ImmutableList<JSType> immutableList0 = ImmutableList.of((JSType) templateType0, (JSType) templateType0, (JSType) templateType0, (JSType) templateType0);
        UnionType unionType0 = new UnionType(jSTypeRegistry0, immutableList0);
        JSType jSType0 = unionType0.getLeastSupertype(templateType0);
        jSType0.isNullType();
    }

    @Test(timeout = 4000)
    public void test2221() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0, false);
        VoidType voidType0 = new VoidType(jSTypeRegistry0);
        ImmutableList<VoidType> immutableList0 = ImmutableList.of(voidType0, voidType0, voidType0, voidType0, voidType0, voidType0, voidType0, voidType0, voidType0, voidType0, voidType0);
        ImmutableList<JSType> immutableList1 = ImmutableList.copyOf((Collection<? extends JSType>) immutableList0);
        UnionType unionType0 = new UnionType(jSTypeRegistry0, immutableList1);
        boolean boolean0 = unionType0.isStruct();
    }

    @Test(timeout = 4000)
    public void test2322() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        NoObjectType noObjectType0 = new NoObjectType(jSTypeRegistry0);
        JSType jSType0 = jSTypeRegistry0.createOptionalType(noObjectType0);
        boolean boolean0 = jSType0.isDict();
    }

    @Test(timeout = 4000)
    public void test2423() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0, true);
        Stack<JSType> stack0 = new Stack<JSType>();
        UnionType unionType0 = new UnionType(jSTypeRegistry0, stack0);
        JSType jSType0 = unionType0.getLeastSupertype(unionType0);
        jSType0.isStringValueType();
    }

    @Test(timeout = 4000)
    public void test2524() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0, true);
        NoObjectType noObjectType0 = new NoObjectType(jSTypeRegistry0);
        JSType jSType0 = jSTypeRegistry0.createOptionalType(noObjectType0);
        JSType jSType1 = jSType0.collapseUnion();
        JSType jSType2 = jSType0.getLeastSupertype(jSType1);
    }

    @Test(timeout = 4000)
    public void test2625() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        TemplateType templateType0 = new TemplateType(jSTypeRegistry0, ",");
        VoidType voidType0 = new VoidType(jSTypeRegistry0);
        ImmutableList<JSType> immutableList0 = ImmutableList.of((JSType) templateType0, (JSType) voidType0, (JSType) voidType0, (JSType) voidType0);
        UnionType unionType0 = new UnionType(jSTypeRegistry0, immutableList0);
        JSType.TypePair jSType_TypePair0 = unionType0.getTypesUnderInequality(voidType0);
        JSType jSType0 = unionType0.getLeastSupertype(jSType_TypePair0.typeB);
    }

    @Test(timeout = 4000)
    public void test2726() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        ArrayDeque<JSType> arrayDeque0 = new ArrayDeque<JSType>();
        StringType stringType0 = new StringType(jSTypeRegistry0);
        UnionType unionType0 = new UnionType(jSTypeRegistry0, arrayDeque0);
        JSType jSType0 = unionType0.meet(stringType0);
        jSType0.isFunctionType();
    }

    @Test(timeout = 4000)
    public void test2827() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0, true);
        NoResolvedType noResolvedType0 = new NoResolvedType(jSTypeRegistry0);
        UnionType unionType0 = (UnionType) jSTypeRegistry0.createOptionalType(noResolvedType0);
        NoResolvedType noResolvedType1 = (NoResolvedType) unionType0.meet(noResolvedType0);
        noResolvedType1.getPropertiesCount();
    }

    @Test(timeout = 4000)
    public void test2928() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0, false);
        NoObjectType noObjectType0 = new NoObjectType(jSTypeRegistry0);
        UnionType unionType0 = (UnionType) jSTypeRegistry0.createOptionalType(noObjectType0);
        NumberType numberType0 = new NumberType(jSTypeRegistry0);
        ImmutableList<JSType> immutableList0 = ImmutableList.of((JSType) numberType0, (JSType) numberType0, (JSType) unionType0, (JSType) unionType0, (JSType) unionType0, (JSType) numberType0, (JSType) noObjectType0);
        UnionType unionType1 = new UnionType(jSTypeRegistry0, immutableList0);
        UnionType unionType2 = (UnionType) unionType0.meet(unionType1);
        unionType2.equals((Object) unionType0);
    }

    @Test(timeout = 4000)
    public void test3029() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        ArrayDeque<JSType> arrayDeque0 = new ArrayDeque<JSType>();
        StringType stringType0 = new StringType(jSTypeRegistry0);
        arrayDeque0.add(stringType0);
        UnionType unionType0 = new UnionType(jSTypeRegistry0, arrayDeque0);
        JSType jSType0 = unionType0.autobox();
        NoType noType0 = (NoType) unionType0.meet(jSType0);
        noType0.hasCachedValues();
    }

    @Test(timeout = 4000)
    public void test3130() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        ArrayDeque<JSType> arrayDeque0 = new ArrayDeque<JSType>();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        UnionType unionType0 = new UnionType(jSTypeRegistry0, arrayDeque0);
        JSType jSType0 = unionType0.meet(unionType0);
        jSType0.canBeCalled();
    }

    @Test(timeout = 4000)
    public void test3531() throws Throwable {
        StringType stringType0 = new StringType((JSTypeRegistry) null);
        ImmutableList<JSType> immutableList0 = ImmutableList.of((JSType) stringType0, (JSType) stringType0, (JSType) stringType0);
        UnionType unionType0 = new UnionType((JSTypeRegistry) null, immutableList0);
        boolean boolean0 = unionType0.hasProperty("Not declared as a type name");
    }

    @Test(timeout = 4000)
    public void test3632() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0, true);
        NoResolvedType noResolvedType0 = new NoResolvedType(jSTypeRegistry0);
        JSType jSType0 = jSTypeRegistry0.createOptionalType(noResolvedType0);
        boolean boolean0 = jSType0.hasProperty("Named type with empty name component");
    }

    @Test(timeout = 4000)
    public void test3733() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0, false);
        NoResolvedType noResolvedType0 = new NoResolvedType(jSTypeRegistry0);
        JSType jSType0 = jSTypeRegistry0.createOptionalType(noResolvedType0);
        boolean boolean0 = jSType0.isObject();
    }

    @Test(timeout = 4000)
    public void test3834() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0, true);
        NoResolvedType noResolvedType0 = new NoResolvedType(jSTypeRegistry0);
        UnionType unionType0 = (UnionType) jSTypeRegistry0.createOptionalType(noResolvedType0);
        ErrorFunctionType errorFunctionType0 = new ErrorFunctionType(jSTypeRegistry0, "Not declared as a type name");
        boolean boolean0 = unionType0.contains(errorFunctionType0);
        errorFunctionType0.hasCachedValues();
    }

    @Test(timeout = 4000)
    public void test3835() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0, true);
        NoResolvedType noResolvedType0 = new NoResolvedType(jSTypeRegistry0);
        UnionType unionType0 = (UnionType) jSTypeRegistry0.createOptionalType(noResolvedType0);
        ErrorFunctionType errorFunctionType0 = new ErrorFunctionType(jSTypeRegistry0, "Not declared as a type name");
        boolean boolean0 = unionType0.contains(errorFunctionType0);
    }

    @Test(timeout = 4000)
    public void test3936() throws Throwable {
        StringType stringType0 = new StringType((JSTypeRegistry) null);
        ImmutableList<JSType> immutableList0 = ImmutableList.of((JSType) stringType0, (JSType) stringType0, (JSType) stringType0);
        UnionType unionType0 = new UnionType((JSTypeRegistry) null, immutableList0);
        boolean boolean0 = unionType0.contains(stringType0);
    }

    @Test(timeout = 4000)
    public void test4037() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0, true);
        NoResolvedType noResolvedType0 = new NoResolvedType(jSTypeRegistry0);
        UnionType unionType0 = (UnionType) jSTypeRegistry0.createOptionalType(noResolvedType0);
        VoidType voidType0 = (VoidType) unionType0.getRestrictedUnion(noResolvedType0);
        voidType0.matchesStringContext();
    }

    @Test(timeout = 4000)
    public void test4138() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        TemplateType templateType0 = new TemplateType(jSTypeRegistry0, "eM&");
        ImmutableList<JSType> immutableList0 = ImmutableList.of((JSType) templateType0, (JSType) templateType0, (JSType) templateType0, (JSType) templateType0);
        UnionType unionType0 = new UnionType(jSTypeRegistry0, immutableList0);
        JSType jSType0 = unionType0.getRestrictedUnion(templateType0);
        jSType0.isTemplateType();
    }

    @Test(timeout = 4000)
    public void test4239() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0, true);
        NoResolvedType noResolvedType0 = new NoResolvedType(jSTypeRegistry0);
        JSType jSType0 = jSTypeRegistry0.createOptionalType(noResolvedType0);
        String string0 = jSType0.toAnnotationString();
    }

    @Test(timeout = 4000)
    public void test4340() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        TemplateType templateType0 = new TemplateType(jSTypeRegistry0, (String) null);
        ImmutableList<JSType> immutableList0 = ImmutableList.of((JSType) templateType0, (JSType) templateType0, (JSType) templateType0, (JSType) templateType0);
        UnionType unionType0 = new UnionType(jSTypeRegistry0, immutableList0);
        boolean boolean0 = unionType0.isSubtype(templateType0);
    }

    @Test(timeout = 4000)
    public void test4441() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0, false);
        NoResolvedType noResolvedType0 = new NoResolvedType(jSTypeRegistry0);
        JSType jSType0 = jSTypeRegistry0.createOptionalType(noResolvedType0);
        AllType allType0 = new AllType(jSTypeRegistry0);
        JSType jSType1 = allType0.getGreatestSubtype(jSType0);
        jSType1.isInterface();
    }

    @Test(timeout = 4000)
    public void test4542() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0, false);
        NoObjectType noObjectType0 = new NoObjectType(jSTypeRegistry0);
        JSType jSType0 = jSTypeRegistry0.createOptionalType(noObjectType0);
        boolean boolean0 = jSType0.isNumber();
    }

    @Test(timeout = 4000)
    public void test4643() throws Throwable {
        StringType stringType0 = new StringType((JSTypeRegistry) null);
        ImmutableList<JSType> immutableList0 = ImmutableList.of((JSType) stringType0, (JSType) stringType0, (JSType) stringType0);
        UnionType unionType0 = new UnionType((JSTypeRegistry) null, immutableList0);
        BooleanLiteralSet booleanLiteralSet0 = unionType0.getPossibleToBooleanOutcomes();
    }

    @Test(timeout = 4000)
    public void test4744() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0, false);
        NoResolvedType noResolvedType0 = new NoResolvedType(jSTypeRegistry0);
        JSType jSType0 = jSTypeRegistry0.createOptionalType(noResolvedType0);
        BooleanLiteralSet booleanLiteralSet0 = jSType0.getPossibleToBooleanOutcomes();
    }

    @Test(timeout = 4000)
    public void test4845() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        StringType stringType0 = new StringType(jSTypeRegistry0);
        ArrayDeque<JSType> arrayDeque0 = new ArrayDeque<JSType>();
        arrayDeque0.add(stringType0);
        UnionType unionType0 = new UnionType(jSTypeRegistry0, arrayDeque0);
        JSType.TypePair jSType_TypePair0 = unionType0.getTypesUnderEquality(stringType0);
    }

    @Test(timeout = 4000)
    public void test4946() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0, false);
        NoResolvedType noResolvedType0 = new NoResolvedType(jSTypeRegistry0);
        JSType jSType0 = jSTypeRegistry0.createOptionalType(noResolvedType0);
        JSType.TypePair jSType_TypePair0 = jSType0.getTypesUnderEquality(noResolvedType0);
    }

    @Test(timeout = 4000)
    public void test5047() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0, false);
        NoResolvedType noResolvedType0 = new NoResolvedType(jSTypeRegistry0);
        JSType jSType0 = jSTypeRegistry0.createOptionalType(noResolvedType0);
        JSType.TypePair jSType_TypePair0 = jSType0.getTypesUnderShallowInequality(jSType0);
    }

    @Test(timeout = 4000)
    public void test5148() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0, false);
        NoResolvedType noResolvedType0 = new NoResolvedType(jSTypeRegistry0);
        NamedType namedType0 = new NamedType(jSTypeRegistry0, "Unknown class name", "Not declared as a constructor", 1, 0);
        ImmutableList<JSType> immutableList0 = ImmutableList.of((JSType) noResolvedType0, (JSType) noResolvedType0, (JSType) namedType0, (JSType) namedType0, (JSType) namedType0, (JSType) namedType0, (JSType) namedType0);
        UnionType unionType0 = new UnionType(jSTypeRegistry0, immutableList0);
        unionType0.resolveInternal(simpleErrorReporter0, noResolvedType0);
        namedType0.isResolved();
    }

    @Test(timeout = 4000)
    public void test5149() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0, false);
        NoResolvedType noResolvedType0 = new NoResolvedType(jSTypeRegistry0);
        NamedType namedType0 = new NamedType(jSTypeRegistry0, "Unknown class name", "Not declared as a constructor", 1, 0);
        ImmutableList<JSType> immutableList0 = ImmutableList.of((JSType) noResolvedType0, (JSType) noResolvedType0, (JSType) namedType0, (JSType) namedType0, (JSType) namedType0, (JSType) namedType0, (JSType) namedType0);
        UnionType unionType0 = new UnionType(jSTypeRegistry0, immutableList0);
        unionType0.resolveInternal(simpleErrorReporter0, noResolvedType0);
        noResolvedType0.isResolved();
    }

    @Test(timeout = 4000)
    public void test5250() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0, false);
        NoResolvedType noResolvedType0 = new NoResolvedType(jSTypeRegistry0);
        UnionType unionType0 = (UnionType) jSTypeRegistry0.createOptionalType(noResolvedType0);
        unionType0.resolveInternal(simpleErrorReporter0, noResolvedType0);
        noResolvedType0.isResolved();
    }

    @Test(timeout = 4000)
    public void test5351() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        ArrayDeque<JSType> arrayDeque0 = new ArrayDeque<JSType>();
        UnionType unionType0 = new UnionType(jSTypeRegistry0, arrayDeque0);
        JSType[] jSTypeArray0 = new JSType[1];
        jSTypeArray0[0] = (JSType) unionType0;
        FunctionType functionType0 = jSTypeRegistry0.createFunctionType((JSType) null, jSTypeArray0);
        ParameterizedType parameterizedType0 = new ParameterizedType(jSTypeRegistry0, functionType0, unionType0);
        arrayDeque0.add(parameterizedType0);
        UnionType unionType1 = new UnionType(jSTypeRegistry0, arrayDeque0);
        unionType1.resolveInternal(simpleErrorReporter0, (StaticScope<JSType>) null);
    }

    @Test(timeout = 4000)
    public void test5452() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0, false);
        NoResolvedType noResolvedType0 = new NoResolvedType(jSTypeRegistry0);
        JSType jSType0 = jSTypeRegistry0.createOptionalType(noResolvedType0);
        String string0 = jSType0.toDebugHashCodeString();
    }

    @Test(timeout = 4000)
    public void test5553() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        ArrayDeque<JSType> arrayDeque0 = new ArrayDeque<JSType>();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        UnionType unionType0 = new UnionType(jSTypeRegistry0, arrayDeque0);
        boolean boolean0 = unionType0.setValidator((Predicate<JSType>) null);
    }

    @Test(timeout = 4000)
    public void test5654() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0, true);
        NoResolvedType noResolvedType0 = new NoResolvedType(jSTypeRegistry0);
        JSType jSType0 = jSTypeRegistry0.createOptionalType(noResolvedType0);
        jSType0.setValidator((Predicate<JSType>) null);
    }

    @Test(timeout = 4000)
    public void test5755() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        ArrayDeque<JSType> arrayDeque0 = new ArrayDeque<JSType>();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        UnionType unionType0 = new UnionType(jSTypeRegistry0, arrayDeque0);
        JSType jSType0 = unionType0.collapseUnion();
    }

    @Test(timeout = 4000)
    public void test5856() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        TemplateType templateType0 = new TemplateType(jSTypeRegistry0, "eM&");
        ImmutableList<JSType> immutableList0 = ImmutableList.of((JSType) templateType0, (JSType) templateType0, (JSType) templateType0, (JSType) templateType0);
        UnionType unionType0 = new UnionType(jSTypeRegistry0, immutableList0);
        JSType jSType0 = unionType0.collapseUnion();
        jSType0.isFunctionType();
    }

    @Test(timeout = 4000)
    public void test5957() throws Throwable {
        StringType stringType0 = new StringType((JSTypeRegistry) null);
        ImmutableList<JSType> immutableList0 = ImmutableList.of((JSType) stringType0, (JSType) stringType0, (JSType) stringType0);
        UnionType unionType0 = new UnionType((JSTypeRegistry) null, immutableList0);
        unionType0.collapseUnion();
    }

    @Test(timeout = 4000)
    public void test6058() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        NoObjectType noObjectType0 = new NoObjectType(jSTypeRegistry0);
        JSType jSType0 = jSTypeRegistry0.createOptionalType(noObjectType0);
        ImmutableList<JSType> immutableList0 = ImmutableList.of(jSType0, (JSType) noObjectType0, jSType0);
        UnionType unionType0 = new UnionType(jSTypeRegistry0, immutableList0);
        JSType jSType1 = unionType0.collapseUnion();
        jSType1.isConstructor();
    }

    @Test(timeout = 4000)
    public void test6159() throws Throwable {
        StringType stringType0 = new StringType((JSTypeRegistry) null);
        ImmutableList<JSType> immutableList0 = ImmutableList.of((JSType) stringType0, (JSType) stringType0, (JSType) stringType0);
        UnionType unionType0 = new UnionType((JSTypeRegistry) null, immutableList0);
        unionType0.matchConstraint(stringType0);
        unionType0.isConstructor();
    }

    @Test(timeout = 4000)
    public void test6260() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0, false);
        NoObjectType noObjectType0 = new NoObjectType(jSTypeRegistry0);
        UnionType unionType0 = (UnionType) jSTypeRegistry0.createOptionalType(noObjectType0);
        boolean boolean0 = unionType0.hasAnyTemplateInternal();
    }

    @Test(timeout = 4000)
    public void test6361() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        TemplateType templateType0 = new TemplateType(jSTypeRegistry0, (String) null);
        ImmutableList<JSType> immutableList0 = ImmutableList.of((JSType) templateType0, (JSType) templateType0, (JSType) templateType0, (JSType) templateType0);
        UnionType unionType0 = new UnionType(jSTypeRegistry0, immutableList0);
        boolean boolean0 = unionType0.hasAnyTemplateInternal();
    }
}
