/*
 * This file was automatically generated by EvoSuite
 * Sat Nov 04 05:02:40 GMT 2023
 */
package com.google.javascript.rhino.jstype;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.google.javascript.rhino.ErrorReporter;
import com.google.javascript.rhino.SimpleErrorReporter;
import com.google.javascript.rhino.jstype.AllType;
import com.google.javascript.rhino.jstype.BooleanLiteralSet;
import com.google.javascript.rhino.jstype.BooleanType;
import com.google.javascript.rhino.jstype.EnumElementType;
import com.google.javascript.rhino.jstype.EnumType;
import com.google.javascript.rhino.jstype.ErrorFunctionType;
import com.google.javascript.rhino.jstype.InstanceObjectType;
import com.google.javascript.rhino.jstype.JSType;
import com.google.javascript.rhino.jstype.JSTypeRegistry;
import com.google.javascript.rhino.jstype.NamedType;
import com.google.javascript.rhino.jstype.NoObjectType;
import com.google.javascript.rhino.jstype.NoType;
import com.google.javascript.rhino.jstype.NullType;
import com.google.javascript.rhino.jstype.NumberType;
import com.google.javascript.rhino.jstype.ObjectType;
import com.google.javascript.rhino.jstype.PrototypeObjectType;
import com.google.javascript.rhino.jstype.RecordType;
import com.google.javascript.rhino.jstype.StaticScope;
import com.google.javascript.rhino.jstype.StringType;
import com.google.javascript.rhino.jstype.UnionType;
import com.google.javascript.rhino.jstype.UnknownType;
import com.google.javascript.rhino.jstype.VoidType;
import java.util.HashMap;
import java.util.LinkedHashSet;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class UnionType_ESTest extends UnionType_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry((ErrorReporter) null);
        LinkedHashSet<JSType> linkedHashSet0 = new LinkedHashSet<JSType>();
        UnionType unionType0 = new UnionType(jSTypeRegistry0, linkedHashSet0);
        NoObjectType noObjectType0 = new NoObjectType(jSTypeRegistry0);
        JSType jSType0 = noObjectType0.getLeastSupertype(unionType0);
        jSType0.isObject();
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        LinkedHashSet<JSType> linkedHashSet0 = new LinkedHashSet<JSType>();
        UnionType unionType0 = new UnionType(jSTypeRegistry0, linkedHashSet0);
        unionType0.forgiveUnknownNames();
        unionType0.isOrdinaryFunction();
        assertFalse(unionType0.isOrdinaryFunction());
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        LinkedHashSet<JSType> linkedHashSet0 = new LinkedHashSet<JSType>();
        UnionType unionType0 = new UnionType((JSTypeRegistry) null, linkedHashSet0);
        linkedHashSet0.add(unionType0);
        unionType0.forgiveUnknownNames();
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        LinkedHashSet<JSType> linkedHashSet0 = new LinkedHashSet<JSType>();
        VoidType voidType0 = new VoidType((JSTypeRegistry) null);
        linkedHashSet0.add(voidType0);
        UnionType unionType0 = new UnionType((JSTypeRegistry) null, linkedHashSet0);
        boolean boolean0 = unionType0.matchesUint32Context();
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        LinkedHashSet<JSType> linkedHashSet0 = new LinkedHashSet<JSType>();
        UnknownType unknownType0 = new UnknownType((JSTypeRegistry) null, false);
        linkedHashSet0.add(unknownType0);
        UnionType unionType0 = new UnionType((JSTypeRegistry) null, linkedHashSet0);
        EnumElementType enumElementType0 = new EnumElementType((JSTypeRegistry) null, unionType0, "Named type with empty name component");
        boolean boolean0 = enumElementType0.matchesInt32Context();
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        LinkedHashSet<JSType> linkedHashSet0 = new LinkedHashSet<JSType>();
        PrototypeObjectType prototypeObjectType0 = new PrototypeObjectType(jSTypeRegistry0, "shne", (ObjectType) null);
        linkedHashSet0.add(prototypeObjectType0);
        UnionType unionType0 = new UnionType(jSTypeRegistry0, linkedHashSet0);
        boolean boolean0 = unionType0.matchesStringContext();
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        LinkedHashSet<JSType> linkedHashSet0 = new LinkedHashSet<JSType>();
        NumberType numberType0 = new NumberType(jSTypeRegistry0);
        JSType jSType0 = numberType0.autoboxesTo();
        linkedHashSet0.add(jSType0);
        UnionType unionType0 = new UnionType(jSTypeRegistry0, linkedHashSet0);
        boolean boolean0 = unionType0.matchesStringContext();
    }

    @Test(timeout = 4000)
    public void test077() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        LinkedHashSet<JSType> linkedHashSet0 = new LinkedHashSet<JSType>();
        UnionType unionType0 = new UnionType(jSTypeRegistry0, linkedHashSet0);
        JSType jSType0 = jSTypeRegistry0.createOptionalNullableType(unionType0);
        boolean boolean0 = jSType0.matchesObjectContext();
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test088() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        LinkedHashSet<JSType> linkedHashSet0 = new LinkedHashSet<JSType>();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        UnionType unionType0 = new UnionType(jSTypeRegistry0, linkedHashSet0);
        ObjectType objectType0 = unionType0.dereference();
        linkedHashSet0.add(objectType0);
        boolean boolean0 = unionType0.matchesObjectContext();
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test099() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        LinkedHashSet<JSType> linkedHashSet0 = new LinkedHashSet<JSType>();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        NumberType numberType0 = new NumberType(jSTypeRegistry0);
        JSType jSType0 = numberType0.autoboxesTo();
        linkedHashSet0.add(jSType0);
        UnionType unionType0 = new UnionType(jSTypeRegistry0, linkedHashSet0);
        JSType jSType1 = unionType0.findPropertyType("Named type with empty name component");
    }

    @Test(timeout = 4000)
    public void test1010() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        LinkedHashSet<JSType> linkedHashSet0 = new LinkedHashSet<JSType>();
        NullType nullType0 = new NullType(jSTypeRegistry0);
        linkedHashSet0.add(nullType0);
        UnionType unionType0 = new UnionType(jSTypeRegistry0, linkedHashSet0);
        JSType jSType0 = unionType0.findPropertyType("Not declared as a type name");
    }

    @Test(timeout = 4000)
    public void test1111() throws Throwable {
        LinkedHashSet<JSType> linkedHashSet0 = new LinkedHashSet<JSType>();
        VoidType voidType0 = new VoidType((JSTypeRegistry) null);
        linkedHashSet0.add(voidType0);
        UnionType unionType0 = new UnionType((JSTypeRegistry) null, linkedHashSet0);
        JSType jSType0 = unionType0.findPropertyType("Unknown class name");
    }

    @Test(timeout = 4000)
    public void test1212() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        LinkedHashSet<JSType> linkedHashSet0 = new LinkedHashSet<JSType>(1190);
        UnknownType unknownType0 = new UnknownType(jSTypeRegistry0, true);
        NoType noType0 = new NoType(jSTypeRegistry0);
        linkedHashSet0.add(unknownType0);
        linkedHashSet0.add(noType0);
        UnionType unionType0 = new UnionType(jSTypeRegistry0, linkedHashSet0);
        JSType jSType0 = unionType0.findPropertyType("Not declared as a constructor");
        jSType0.isVoidType();
        assertTrue(jSType0.isVoidType());
    }

    @Test(timeout = 4000)
    public void test1313() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        LinkedHashSet<JSType> linkedHashSet0 = new LinkedHashSet<JSType>();
        HashMap<String, JSType> hashMap0 = new HashMap<String, JSType>();
        RecordType recordType0 = new RecordType(jSTypeRegistry0, hashMap0);
        linkedHashSet0.add(recordType0);
        UnionType unionType0 = new UnionType(jSTypeRegistry0, linkedHashSet0);
        boolean boolean0 = unionType0.canAssignTo(recordType0);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test1414() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        LinkedHashSet<JSType> linkedHashSet0 = new LinkedHashSet<JSType>();
        UnknownType unknownType0 = new UnknownType(jSTypeRegistry0, false);
        linkedHashSet0.add(unknownType0);
        UnionType unionType0 = new UnionType(jSTypeRegistry0, linkedHashSet0);
        boolean boolean0 = unionType0.canAssignTo(unionType0);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test1515() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        LinkedHashSet<JSType> linkedHashSet0 = new LinkedHashSet<JSType>();
        UnionType unionType0 = new UnionType(jSTypeRegistry0, linkedHashSet0);
        JSType jSType0 = jSTypeRegistry0.createOptionalNullableType(unionType0);
        boolean boolean0 = jSType0.canBeCalled();
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test1616() throws Throwable {
        LinkedHashSet<JSType> linkedHashSet0 = new LinkedHashSet<JSType>();
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        NoType noType0 = new NoType(jSTypeRegistry0);
        linkedHashSet0.add(noType0);
        UnionType unionType0 = new UnionType(jSTypeRegistry0, linkedHashSet0);
        boolean boolean0 = unionType0.canBeCalled();
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test1717() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        LinkedHashSet<JSType> linkedHashSet0 = new LinkedHashSet<JSType>();
        UnionType unionType0 = new UnionType(jSTypeRegistry0, linkedHashSet0);
        linkedHashSet0.add(unionType0);
        unionType0.restrictByNotNullOrUndefined();
    }

    @Test(timeout = 4000)
    public void test1818() throws Throwable {
        LinkedHashSet<JSType> linkedHashSet0 = new LinkedHashSet<JSType>();
        UnionType unionType0 = new UnionType((JSTypeRegistry) null, linkedHashSet0);
        EnumElementType enumElementType0 = new EnumElementType((JSTypeRegistry) null, unionType0, "");
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        JSType jSType0 = jSTypeRegistry0.createOptionalNullableType(enumElementType0);
        JSType.TypePair jSType_TypePair0 = enumElementType0.getTypesUnderEquality(jSType0);
        assertNotNull(jSType_TypePair0);
    }

    @Test(timeout = 4000)
    public void test2119() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        LinkedHashSet<JSType> linkedHashSet0 = new LinkedHashSet<JSType>();
        UnionType unionType0 = new UnionType(jSTypeRegistry0, linkedHashSet0);
        JSType jSType0 = jSTypeRegistry0.createOptionalNullableType(unionType0);
        boolean boolean0 = jSType0.isNullable();
    }

    @Test(timeout = 4000)
    public void test2220() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        LinkedHashSet<JSType> linkedHashSet0 = new LinkedHashSet<JSType>();
        ErrorFunctionType errorFunctionType0 = new ErrorFunctionType(jSTypeRegistry0, "8j");
        InstanceObjectType instanceObjectType0 = new InstanceObjectType(jSTypeRegistry0, errorFunctionType0);
        linkedHashSet0.add(instanceObjectType0);
        UnionType unionType0 = new UnionType(jSTypeRegistry0, linkedHashSet0);
        boolean boolean0 = unionType0.isNullable();
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test2321() throws Throwable {
        LinkedHashSet<JSType> linkedHashSet0 = new LinkedHashSet<JSType>();
        UnknownType unknownType0 = new UnknownType((JSTypeRegistry) null, false);
        linkedHashSet0.add(unknownType0);
        UnionType unionType0 = new UnionType((JSTypeRegistry) null, linkedHashSet0);
        EnumElementType enumElementType0 = new EnumElementType((JSTypeRegistry) null, unionType0, "Named type with empty name component");
        unionType0.meet(enumElementType0);
    }

    @Test(timeout = 4000)
    public void test2422() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        LinkedHashSet<JSType> linkedHashSet0 = new LinkedHashSet<JSType>();
        UnionType unionType0 = new UnionType(jSTypeRegistry0, linkedHashSet0);
        NamedType namedType0 = new NamedType(jSTypeRegistry0, "Named type with empty name component", "Not declared as a type name", 0, 1);
        JSType jSType0 = unionType0.getLeastSupertype(namedType0);
    }

    @Test(timeout = 4000)
    public void test2523() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        LinkedHashSet<JSType> linkedHashSet0 = new LinkedHashSet<JSType>();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        NumberType numberType0 = new NumberType(jSTypeRegistry0);
        JSType jSType0 = numberType0.autoboxesTo();
        linkedHashSet0.add(jSType0);
        UnionType unionType0 = new UnionType(jSTypeRegistry0, linkedHashSet0);
        JSType jSType1 = unionType0.getLeastSupertype(unionType0);
    }

    @Test(timeout = 4000)
    public void test2624() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        LinkedHashSet<JSType> linkedHashSet0 = new LinkedHashSet<JSType>();
        UnknownType unknownType0 = new UnknownType(jSTypeRegistry0, false);
        UnionType unionType0 = new UnionType(jSTypeRegistry0, linkedHashSet0);
        linkedHashSet0.add(unknownType0);
        BooleanType booleanType0 = new BooleanType(jSTypeRegistry0);
        booleanType0.getLeastSupertype(unionType0);
        unionType0.isUnionType();
    }

    @Test(timeout = 4000)
    public void test2725() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        LinkedHashSet<JSType> linkedHashSet0 = new LinkedHashSet<JSType>();
        StringType stringType0 = new StringType(jSTypeRegistry0);
        linkedHashSet0.add(stringType0);
        UnionType unionType0 = new UnionType(jSTypeRegistry0, linkedHashSet0);
        BooleanType booleanType0 = new BooleanType(jSTypeRegistry0);
        JSType jSType0 = booleanType0.getLeastSupertype(unionType0);
    }

    @Test(timeout = 4000)
    public void test2726() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        LinkedHashSet<JSType> linkedHashSet0 = new LinkedHashSet<JSType>();
        StringType stringType0 = new StringType(jSTypeRegistry0);
        linkedHashSet0.add(stringType0);
        UnionType unionType0 = new UnionType(jSTypeRegistry0, linkedHashSet0);
        BooleanType booleanType0 = new BooleanType(jSTypeRegistry0);
        JSType jSType0 = booleanType0.getLeastSupertype(unionType0);
        jSType0.isUnionType();
    }

    @Test(timeout = 4000)
    public void test2827() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        LinkedHashSet<JSType> linkedHashSet0 = new LinkedHashSet<JSType>();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        NumberType numberType0 = new NumberType(jSTypeRegistry0);
        JSType jSType0 = numberType0.autoboxesTo();
        linkedHashSet0.add(jSType0);
        UnionType unionType0 = new UnionType(jSTypeRegistry0, linkedHashSet0);
        unionType0.getGreatestSubtype(numberType0);
        unionType0.isUnionType();
        assertTrue(unionType0.isUnionType());
    }

    @Test(timeout = 4000)
    public void test2928() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        LinkedHashSet<JSType> linkedHashSet0 = new LinkedHashSet<JSType>();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        NumberType numberType0 = new NumberType(jSTypeRegistry0);
        JSType jSType0 = numberType0.autoboxesTo();
        linkedHashSet0.add(jSType0);
        UnionType unionType0 = new UnionType(jSTypeRegistry0, linkedHashSet0);
        JSType jSType1 = unionType0.meet(unionType0);
    }

    @Test(timeout = 4000)
    public void test3029() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        LinkedHashSet<JSType> linkedHashSet0 = new LinkedHashSet<JSType>();
        UnionType unionType0 = new UnionType(jSTypeRegistry0, linkedHashSet0);
        JSType jSType0 = jSTypeRegistry0.createOptionalNullableType(unionType0);
        JSType jSType1 = unionType0.meet(jSType0);
        jSType1.isNoType();
        assertTrue(jSType1.isNoType());
    }

    @Test(timeout = 4000)
    public void test3130() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        LinkedHashSet<JSType> linkedHashSet0 = new LinkedHashSet<JSType>();
        UnionType unionType0 = new UnionType(jSTypeRegistry0, linkedHashSet0);
        EnumType enumType0 = jSTypeRegistry0.createEnumType("Not declared as a constructor", unionType0);
        JSType jSType0 = unionType0.meet(enumType0);
        jSType0.isNamedType();
    }

    @Test(timeout = 4000)
    public void test3231() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        LinkedHashSet<JSType> linkedHashSet0 = new LinkedHashSet<JSType>();
        UnionType unionType0 = new UnionType(jSTypeRegistry0, linkedHashSet0);
        boolean boolean0 = JSType.isSubtype((JSType) unionType0, (JSType) unionType0);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test3332() throws Throwable {
        LinkedHashSet<JSType> linkedHashSet0 = new LinkedHashSet<JSType>();
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        NoObjectType noObjectType0 = new NoObjectType(jSTypeRegistry0);
        linkedHashSet0.add(noObjectType0);
        UnionType unionType0 = new UnionType(jSTypeRegistry0, linkedHashSet0);
        EnumElementType enumElementType0 = new EnumElementType(jSTypeRegistry0, unionType0, "Named type with empty name component");
        NoObjectType noObjectType1 = (NoObjectType) unionType0.meet(enumElementType0);
        enumElementType0.isObject();
        assertTrue(enumElementType0.isObject());
    }

    @Test(timeout = 4000)
    public void test3333() throws Throwable {
        LinkedHashSet<JSType> linkedHashSet0 = new LinkedHashSet<JSType>();
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        NoObjectType noObjectType0 = new NoObjectType(jSTypeRegistry0);
        linkedHashSet0.add(noObjectType0);
        UnionType unionType0 = new UnionType(jSTypeRegistry0, linkedHashSet0);
        EnumElementType enumElementType0 = new EnumElementType(jSTypeRegistry0, unionType0, "Named type with empty name component");
        NoObjectType noObjectType1 = (NoObjectType) unionType0.meet(enumElementType0);
        noObjectType1.hasCachedValues();
        assertFalse(noObjectType1.hasCachedValues());
    }

    @Test(timeout = 4000)
    public void test3434() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        NoObjectType noObjectType0 = new NoObjectType(jSTypeRegistry0);
        NumberType numberType0 = new NumberType(jSTypeRegistry0);
        UnionType unionType0 = (UnionType) noObjectType0.getLeastSupertype(numberType0);
        EnumElementType enumElementType0 = new EnumElementType((JSTypeRegistry) null, unionType0, "Named type with empty name component");
        EnumElementType enumElementType1 = (EnumElementType) unionType0.meet(enumElementType0);
        enumElementType1.isObject();
        assertTrue(enumElementType1.isObject());
    }

    @Test(timeout = 4000)
    public void test3535() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        LinkedHashSet<JSType> linkedHashSet0 = new LinkedHashSet<JSType>();
        NumberType numberType0 = new NumberType(jSTypeRegistry0);
        JSType jSType0 = numberType0.autoboxesTo();
        UnionType unionType0 = new UnionType(jSTypeRegistry0, linkedHashSet0);
        boolean boolean0 = unionType0.contains(jSType0);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test3636() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        LinkedHashSet<JSType> linkedHashSet0 = new LinkedHashSet<JSType>();
        NumberType numberType0 = new NumberType(jSTypeRegistry0);
        JSType jSType0 = numberType0.autoboxesTo();
        linkedHashSet0.add(jSType0);
        UnionType unionType0 = new UnionType(jSTypeRegistry0, linkedHashSet0);
        boolean boolean0 = unionType0.contains(jSType0);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test3737() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        LinkedHashSet<JSType> linkedHashSet0 = new LinkedHashSet<JSType>();
        AllType allType0 = new AllType(jSTypeRegistry0);
        linkedHashSet0.add(allType0);
        UnionType unionType0 = new UnionType(jSTypeRegistry0, linkedHashSet0);
        JSType jSType0 = unionType0.getRestrictedUnion(allType0);
        jSType0.isUnionType();
        assertFalse(jSType0.isUnionType());
    }

    @Test(timeout = 4000)
    public void test3838() throws Throwable {
        LinkedHashSet<JSType> linkedHashSet0 = new LinkedHashSet<JSType>();
        UnknownType unknownType0 = new UnknownType((JSTypeRegistry) null, false);
        linkedHashSet0.add(unknownType0);
        UnionType unionType0 = new UnionType((JSTypeRegistry) null, linkedHashSet0);
        unionType0.getRestrictedUnion(unknownType0);
    }

    @Test(timeout = 4000)
    public void test3939() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        LinkedHashSet<JSType> linkedHashSet0 = new LinkedHashSet<JSType>();
        NumberType numberType0 = new NumberType(jSTypeRegistry0);
        JSType jSType0 = numberType0.autoboxesTo();
        linkedHashSet0.add(jSType0);
        UnionType unionType0 = new UnionType(jSTypeRegistry0, linkedHashSet0);
        JSType jSType1 = unionType0.getRestrictedUnion(numberType0);
        jSType1.isNamedType();
    }

    @Test(timeout = 4000)
    public void test4040() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        LinkedHashSet<JSType> linkedHashSet0 = new LinkedHashSet<JSType>();
        AllType allType0 = new AllType(jSTypeRegistry0);
        linkedHashSet0.add(allType0);
        UnionType unionType0 = new UnionType(jSTypeRegistry0, linkedHashSet0);
        linkedHashSet0.add(unionType0);
        unionType0.toString();
        linkedHashSet0.size();
    }

    @Test(timeout = 4000)
    public void test4141() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        LinkedHashSet<JSType> linkedHashSet0 = new LinkedHashSet<JSType>();
        UnionType unionType0 = new UnionType(jSTypeRegistry0, linkedHashSet0);
        JSType jSType0 = unionType0.getRestrictedTypeGivenToBooleanOutcome(false);
        jSType0.isFunctionType();
        assertFalse(jSType0.isFunctionType());
    }

    @Test(timeout = 4000)
    public void test4242() throws Throwable {
        LinkedHashSet<JSType> linkedHashSet0 = new LinkedHashSet<JSType>();
        UnionType unionType0 = new UnionType((JSTypeRegistry) null, linkedHashSet0);
        linkedHashSet0.add(unionType0);
        unionType0.getRestrictedTypeGivenToBooleanOutcome(true);
    }

    @Test(timeout = 4000)
    public void test4343() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        LinkedHashSet<JSType> linkedHashSet0 = new LinkedHashSet<JSType>();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        NumberType numberType0 = new NumberType(jSTypeRegistry0);
        JSType jSType0 = numberType0.autoboxesTo();
        linkedHashSet0.add(jSType0);
        UnionType unionType0 = new UnionType(jSTypeRegistry0, linkedHashSet0);
        BooleanLiteralSet booleanLiteralSet0 = unionType0.getPossibleToBooleanOutcomes();
    }

    @Test(timeout = 4000)
    public void test4444() throws Throwable {
        LinkedHashSet<JSType> linkedHashSet0 = new LinkedHashSet<JSType>();
        UnionType unionType0 = new UnionType((JSTypeRegistry) null, linkedHashSet0);
        NumberType numberType0 = new NumberType((JSTypeRegistry) null);
        linkedHashSet0.add(numberType0);
        BooleanLiteralSet booleanLiteralSet0 = unionType0.getPossibleToBooleanOutcomes();
    }

    @Test(timeout = 4000)
    public void test4545() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        LinkedHashSet<JSType> linkedHashSet0 = new LinkedHashSet<JSType>();
        BooleanType booleanType0 = new BooleanType(jSTypeRegistry0);
        linkedHashSet0.add(booleanType0);
        UnionType unionType0 = new UnionType(jSTypeRegistry0, linkedHashSet0);
        JSType.TypePair jSType_TypePair0 = unionType0.getTypesUnderInequality(booleanType0);
        assertNotNull(jSType_TypePair0);
    }

    @Test(timeout = 4000)
    public void test4646() throws Throwable {
        LinkedHashSet<JSType> linkedHashSet0 = new LinkedHashSet<JSType>();
        VoidType voidType0 = new VoidType((JSTypeRegistry) null);
        linkedHashSet0.add(voidType0);
        UnionType unionType0 = new UnionType((JSTypeRegistry) null, linkedHashSet0);
        EnumElementType enumElementType0 = new EnumElementType((JSTypeRegistry) null, unionType0, "Named type with empty name component");
        enumElementType0.getTypesUnderInequality(unionType0);
    }

    @Test(timeout = 4000)
    public void test4747() throws Throwable {
        LinkedHashSet<JSType> linkedHashSet0 = new LinkedHashSet<JSType>();
        VoidType voidType0 = new VoidType((JSTypeRegistry) null);
        linkedHashSet0.add(voidType0);
        UnionType unionType0 = new UnionType((JSTypeRegistry) null, linkedHashSet0);
        unionType0.getTypesUnderShallowInequality(voidType0);
    }

    @Test(timeout = 4000)
    public void test4848() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        LinkedHashSet<JSType> linkedHashSet0 = new LinkedHashSet<JSType>();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        NumberType numberType0 = new NumberType(jSTypeRegistry0);
        JSType jSType0 = numberType0.autoboxesTo();
        linkedHashSet0.add(jSType0);
        UnionType unionType0 = new UnionType(jSTypeRegistry0, linkedHashSet0);
        JSType jSType1 = unionType0.resolveInternal(simpleErrorReporter0, (StaticScope<JSType>) null);
        jSType1.isConstructor();
        assertFalse(jSType1.isConstructor());
    }

    @Test(timeout = 4000)
    public void test4949() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        LinkedHashSet<JSType> linkedHashSet0 = new LinkedHashSet<JSType>();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0);
        NumberType numberType0 = new NumberType(jSTypeRegistry0);
        InstanceObjectType instanceObjectType0 = (InstanceObjectType) numberType0.autoboxesTo();
        linkedHashSet0.add(instanceObjectType0);
        UnionType unionType0 = new UnionType(jSTypeRegistry0, linkedHashSet0);
        instanceObjectType0.setResolvedTypeInternal(unionType0);
        UnionType unionType1 = (UnionType) unionType0.resolveInternal(simpleErrorReporter0, (StaticScope<JSType>) null);
        unionType1.isNullType();
        assertTrue(unionType1.isNullType());
    }
}
