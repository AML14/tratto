/*
 * This file was automatically generated by EvoSuite
 * Thu Mar 28 00:59:06 GMT 2024
 */
package com.fasterxml.jackson.databind.type;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.jsontype.impl.TypeNameIdResolver;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.ReferenceType;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.time.chrono.IsoEra;
import java.util.HashMap;
import java.util.LinkedList;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class SimpleType_ESTest extends SimpleType_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        SimpleType simpleType0 = TypeFactory.CORE_TYPE_ENUM;
        Object object0 = new Object();
        simpleType0.withContentValueHandler(object0);
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        StringBuilder stringBuilder0 = new StringBuilder();
        SimpleType simpleType0 = TypeFactory.CORE_TYPE_OBJECT;
        simpleType0.withContentTypeHandler(stringBuilder0);
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        SimpleType simpleType0 = TypeFactory.CORE_TYPE_COMPARABLE;
        String string0 = simpleType0.toString();
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        SimpleType simpleType0 = TypeFactory.CORE_TYPE_CLASS;
        boolean boolean0 = simpleType0.isContainerType();
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        SimpleType simpleType0 = TypeFactory.CORE_TYPE_ENUM;
        simpleType0.withContentType(simpleType0);
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        SimpleType simpleType0 = TypeFactory.CORE_TYPE_OBJECT;
        ReferenceType referenceType0 = new ReferenceType(simpleType0, simpleType0);
        ReferenceType referenceType1 = referenceType0.withStaticTyping();
        referenceType1.isPrimitive();
        assertTrue(referenceType1.isPrimitive());
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        Class<SimpleType> class0 = SimpleType.class;
        SimpleType simpleType0 = SimpleType.constructUnsafe(class0);
        simpleType0.useStaticType();
        assertFalse(simpleType0.useStaticType());
    }

    @Test(timeout = 4000)
    public void test077() throws Throwable {
        SimpleType simpleType0 = TypeFactory.CORE_TYPE_BOOL;
        String string0 = simpleType0.getErasedSignature();
        assertNotNull(string0);
    }

    @Test(timeout = 4000)
    public void test088() throws Throwable {
        Class<Object> class0 = Object.class;
        SimpleType simpleType0 = new SimpleType(class0);
        simpleType0.isPrimitive();
    }

    @Test(timeout = 4000)
    public void test099() throws Throwable {
        Class<LinkedList> class0 = LinkedList.class;
        SimpleType.construct(class0);
    }

    @Test(timeout = 4000)
    public void test1010() throws Throwable {
        Class<HashMap> class0 = HashMap.class;
        SimpleType.construct(class0);
    }

    @Test(timeout = 4000)
    public void test1111() throws Throwable {
        Class<IsoEra> class0 = IsoEra.class;
        SimpleType simpleType0 = SimpleType.construct(class0);
        SimpleType simpleType1 = simpleType0.withValueHandler((Object) null);
    }

    @Test(timeout = 4000)
    public void test1112() throws Throwable {
        Class<IsoEra> class0 = IsoEra.class;
        SimpleType simpleType0 = SimpleType.construct(class0);
        SimpleType simpleType1 = simpleType0.withValueHandler((Object) null);
        simpleType1.useStaticType();
        assertFalse(simpleType1.useStaticType());
    }

    @Test(timeout = 4000)
    public void test1213() throws Throwable {
        SimpleType simpleType0 = TypeFactory.CORE_TYPE_LONG;
        Class<Object> class0 = Object.class;
        JavaType javaType0 = simpleType0._narrow(class0);
        javaType0.isJavaLangObject();
        assertTrue(javaType0.isJavaLangObject());
    }

    @Test(timeout = 4000)
    public void test1314() throws Throwable {
        SimpleType simpleType0 = TypeFactory.CORE_TYPE_OBJECT;
        Class<Object> class0 = Object.class;
        JavaType javaType0 = simpleType0._narrow(class0);
    }

    @Test(timeout = 4000)
    public void test1415() throws Throwable {
        SimpleType simpleType0 = TypeFactory.CORE_TYPE_ENUM;
        SimpleType simpleType1 = simpleType0.withTypeHandler("=yoZ2ZQ_+a2{S9\"LC{|");
    }

    @Test(timeout = 4000)
    public void test1516() throws Throwable {
        SimpleType simpleType0 = TypeFactory.CORE_TYPE_BOOL;
        SimpleType simpleType1 = simpleType0.withTypeHandler((Object) null);
    }

    @Test(timeout = 4000)
    public void test1617() throws Throwable {
        SimpleType simpleType0 = TypeFactory.CORE_TYPE_ENUM;
        SimpleType simpleType1 = simpleType0.withValueHandler(simpleType0);
        simpleType1.hasValueHandler();
        assertTrue(simpleType1.hasValueHandler());
    }

    @Test(timeout = 4000)
    public void test1718() throws Throwable {
        SimpleType simpleType0 = TypeFactory.CORE_TYPE_ENUM;
        SimpleType simpleType1 = simpleType0.withStaticTyping();
        SimpleType simpleType2 = simpleType1.withStaticTyping();
        simpleType2.useStaticType();
        assertTrue(simpleType2.useStaticType());
    }

    @Test(timeout = 4000)
    public void test1819() throws Throwable {
        Class<Object> class0 = Object.class;
        TypeFactory typeFactory0 = TypeFactory.instance;
        Class<HashMap> class1 = HashMap.class;
        Class<TypeNameIdResolver> class2 = TypeNameIdResolver.class;
        MapType mapType0 = typeFactory0.constructMapType(class1, class2, class0);
        SimpleType simpleType0 = new SimpleType(mapType0);
        String string0 = simpleType0.toCanonical();
    }

    @Test(timeout = 4000)
    public void test1920() throws Throwable {
        TypeFactory typeFactory0 = TypeFactory.instance;
        Class<LinkedList> class0 = LinkedList.class;
        CollectionType collectionType0 = typeFactory0.constructRawCollectionType(class0);
        StringBuilder stringBuilder0 = new StringBuilder(2);
        SimpleType simpleType0 = new SimpleType(collectionType0);
        simpleType0.getGenericSignature(stringBuilder0);
        stringBuilder0.toString();
    }

    @Test(timeout = 4000)
    public void test2021() throws Throwable {
        SimpleType simpleType0 = TypeFactory.CORE_TYPE_OBJECT;
        SimpleType simpleType1 = new SimpleType(simpleType0);
        boolean boolean0 = simpleType1.equals(simpleType0);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test2122() throws Throwable {
        SimpleType simpleType0 = TypeFactory.CORE_TYPE_OBJECT;
        boolean boolean0 = simpleType0.equals(simpleType0);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test2223() throws Throwable {
        SimpleType simpleType0 = TypeFactory.CORE_TYPE_OBJECT;
        boolean boolean0 = simpleType0.equals((Object) null);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test2324() throws Throwable {
        SimpleType simpleType0 = TypeFactory.CORE_TYPE_COMPARABLE;
        boolean boolean0 = simpleType0.equals("Ljava/lang/Comparable;");
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test2425() throws Throwable {
        SimpleType simpleType0 = TypeFactory.CORE_TYPE_OBJECT;
        SimpleType simpleType1 = TypeFactory.CORE_TYPE_CLASS;
        boolean boolean0 = simpleType1.equals(simpleType0);
        assertTrue(boolean0);
    }
}
