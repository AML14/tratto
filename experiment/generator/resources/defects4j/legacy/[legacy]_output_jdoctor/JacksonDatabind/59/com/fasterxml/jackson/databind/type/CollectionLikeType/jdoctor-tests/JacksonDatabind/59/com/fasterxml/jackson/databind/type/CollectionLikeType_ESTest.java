/*
 * This file was automatically generated by EvoSuite
 * Mon Nov 20 03:52:14 GMT 2023
 */
package com.fasterxml.jackson.databind.type;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.ResolvedRecursiveType;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.fasterxml.jackson.databind.type.TypeBindings;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.LRUMap;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class CollectionLikeType_ESTest extends CollectionLikeType_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        Class<ArrayType> class0 = ArrayType.class;
        ObjectMapper objectMapper0 = new ObjectMapper();
        ObjectReader objectReader0 = objectMapper0.readerFor(class0);
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        SimpleType simpleType0 = TypeFactory.CORE_TYPE_BOOL;
        CollectionLikeType collectionLikeType0 = new CollectionLikeType(simpleType0, simpleType0);
        String string0 = collectionLikeType0.getErasedSignature();
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        SimpleType simpleType0 = TypeFactory.CORE_TYPE_OBJECT;
        CollectionLikeType collectionLikeType0 = new CollectionLikeType(simpleType0, simpleType0);
        String string0 = collectionLikeType0.toString();
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        SimpleType simpleType0 = TypeFactory.CORE_TYPE_BOOL;
        CollectionLikeType collectionLikeType0 = new CollectionLikeType(simpleType0, simpleType0);
        CollectionLikeType collectionLikeType1 = collectionLikeType0.withContentTypeHandler(simpleType0);
        collectionLikeType1.isAbstract();
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        SimpleType simpleType0 = TypeFactory.CORE_TYPE_BOOL;
        CollectionLikeType collectionLikeType0 = new CollectionLikeType(simpleType0, simpleType0);
        Object object0 = collectionLikeType0.getContentValueHandler();
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        Class<CollectionLikeType> class0 = CollectionLikeType.class;
        SimpleType simpleType0 = SimpleType.constructUnsafe(class0);
        CollectionType collectionType0 = new CollectionType(simpleType0, simpleType0);
        Object object0 = collectionType0.getContentTypeHandler();
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        SimpleType simpleType0 = TypeFactory.CORE_TYPE_ENUM;
        Class<Integer> class0 = Integer.TYPE;
        CollectionLikeType collectionLikeType0 = CollectionLikeType.construct((Class<?>) class0, (JavaType) simpleType0);
        TypeBindings typeBindings0 = TypeBindings.emptyBindings();
        JavaType[] javaTypeArray0 = new JavaType[0];
        JavaType javaType0 = collectionLikeType0.refine(class0, typeBindings0, simpleType0, javaTypeArray0);
        javaType0.useStaticType();
    }

    @Test(timeout = 4000)
    public void test077() throws Throwable {
        SimpleType simpleType0 = TypeFactory.CORE_TYPE_BOOL;
        CollectionLikeType collectionLikeType0 = new CollectionLikeType(simpleType0, simpleType0);
        String string0 = collectionLikeType0.getGenericSignature();
    }

    @Test(timeout = 4000)
    public void test088() throws Throwable {
        SimpleType simpleType0 = TypeFactory.CORE_TYPE_OBJECT;
        Class<CollectionType> class0 = CollectionType.class;
        CollectionLikeType collectionLikeType0 = CollectionLikeType.construct((Class<?>) class0, (JavaType) simpleType0);
        CollectionLikeType collectionLikeType1 = collectionLikeType0.withTypeHandler(simpleType0);
        collectionLikeType1.useStaticType();
    }

    @Test(timeout = 4000)
    public void test099() throws Throwable {
        SimpleType simpleType0 = TypeFactory.CORE_TYPE_BOOL;
        CollectionLikeType collectionLikeType0 = new CollectionLikeType(simpleType0, simpleType0);
        Class<ArrayType> class0 = ArrayType.class;
        CollectionLikeType collectionLikeType1 = collectionLikeType0.withValueHandler(class0);
    }

    @Test(timeout = 4000)
    public void test1010() throws Throwable {
        SimpleType simpleType0 = TypeFactory.CORE_TYPE_LONG;
        Class<MapType> class0 = MapType.class;
        CollectionLikeType collectionLikeType0 = CollectionLikeType.construct((Class<?>) class0, (JavaType) simpleType0);
        TypeBindings typeBindings0 = TypeBindings.createIfNeeded((Class<?>) class0, (JavaType) collectionLikeType0);
        JavaType[] javaTypeArray0 = new JavaType[0];
        CollectionLikeType collectionLikeType1 = CollectionLikeType.construct((Class<?>) class0, typeBindings0, (JavaType) simpleType0, javaTypeArray0, (JavaType) collectionLikeType0);
        collectionLikeType1.useStaticType();
    }

    @Test(timeout = 4000)
    public void test1011() throws Throwable {
        SimpleType simpleType0 = TypeFactory.CORE_TYPE_LONG;
        Class<MapType> class0 = MapType.class;
        CollectionLikeType collectionLikeType0 = CollectionLikeType.construct((Class<?>) class0, (JavaType) simpleType0);
        TypeBindings typeBindings0 = TypeBindings.createIfNeeded((Class<?>) class0, (JavaType) collectionLikeType0);
        JavaType[] javaTypeArray0 = new JavaType[0];
        CollectionLikeType collectionLikeType1 = CollectionLikeType.construct((Class<?>) class0, typeBindings0, (JavaType) simpleType0, javaTypeArray0, (JavaType) collectionLikeType0);
        collectionLikeType1.equals((Object) collectionLikeType0);
    }

    @Test(timeout = 4000)
    public void test1012() throws Throwable {
        SimpleType simpleType0 = TypeFactory.CORE_TYPE_LONG;
        Class<MapType> class0 = MapType.class;
        CollectionLikeType collectionLikeType0 = CollectionLikeType.construct((Class<?>) class0, (JavaType) simpleType0);
        TypeBindings typeBindings0 = TypeBindings.createIfNeeded((Class<?>) class0, (JavaType) collectionLikeType0);
        JavaType[] javaTypeArray0 = new JavaType[0];
        CollectionLikeType collectionLikeType1 = CollectionLikeType.construct((Class<?>) class0, typeBindings0, (JavaType) simpleType0, javaTypeArray0, (JavaType) collectionLikeType0);
        collectionLikeType0.useStaticType();
    }

    @Test(timeout = 4000)
    public void test1113() throws Throwable {
        SimpleType simpleType0 = TypeFactory.CORE_TYPE_BOOL;
        CollectionLikeType collectionLikeType0 = new CollectionLikeType(simpleType0, simpleType0);
        CollectionLikeType collectionLikeType1 = collectionLikeType0.withContentValueHandler(simpleType0);
        collectionLikeType1.isAbstract();
    }

    @Test(timeout = 4000)
    public void test1214() throws Throwable {
        SimpleType simpleType0 = TypeFactory.CORE_TYPE_BOOL;
        CollectionLikeType collectionLikeType0 = new CollectionLikeType(simpleType0, simpleType0);
        Class<ResolvedRecursiveType> class0 = ResolvedRecursiveType.class;
        JavaType javaType0 = collectionLikeType0._narrow(class0);
        javaType0.isAbstract();
    }

    @Test(timeout = 4000)
    public void test1315() throws Throwable {
        SimpleType simpleType0 = TypeFactory.CORE_TYPE_BOOL;
        Class<JsonDeserializer> class0 = JsonDeserializer.class;
        CollectionLikeType collectionLikeType0 = CollectionLikeType.construct((Class<?>) class0, (JavaType) simpleType0);
        collectionLikeType0.containedTypeCount();
    }

    @Test(timeout = 4000)
    public void test1316() throws Throwable {
        SimpleType simpleType0 = TypeFactory.CORE_TYPE_BOOL;
        Class<JsonDeserializer> class0 = JsonDeserializer.class;
        CollectionLikeType collectionLikeType0 = CollectionLikeType.construct((Class<?>) class0, (JavaType) simpleType0);
        collectionLikeType0.useStaticType();
    }

    @Test(timeout = 4000)
    public void test1417() throws Throwable {
        CollectionLikeType.upgradeFrom((JavaType) null, (JavaType) null);
    }

    @Test(timeout = 4000)
    public void test1518() throws Throwable {
        SimpleType simpleType0 = TypeFactory.CORE_TYPE_LONG;
        CollectionLikeType collectionLikeType0 = CollectionLikeType.upgradeFrom(simpleType0, simpleType0);
        collectionLikeType0.isPrimitive();
    }

    @Test(timeout = 4000)
    public void test1619() throws Throwable {
        SimpleType simpleType0 = TypeFactory.CORE_TYPE_BOOL;
        CollectionLikeType collectionLikeType0 = new CollectionLikeType(simpleType0, simpleType0);
        JavaType javaType0 = collectionLikeType0.withContentType(collectionLikeType0);
    }

    @Test(timeout = 4000)
    public void test1620() throws Throwable {
        SimpleType simpleType0 = TypeFactory.CORE_TYPE_BOOL;
        CollectionLikeType collectionLikeType0 = new CollectionLikeType(simpleType0, simpleType0);
        JavaType javaType0 = collectionLikeType0.withContentType(collectionLikeType0);
        javaType0.equals((Object) collectionLikeType0);
    }

    @Test(timeout = 4000)
    public void test1721() throws Throwable {
        SimpleType simpleType0 = TypeFactory.CORE_TYPE_BOOL;
        CollectionLikeType collectionLikeType0 = new CollectionLikeType(simpleType0, simpleType0);
        JavaType javaType0 = collectionLikeType0.withContentType(simpleType0);
    }

    @Test(timeout = 4000)
    public void test1822() throws Throwable {
        SimpleType simpleType0 = TypeFactory.CORE_TYPE_BOOL;
        CollectionLikeType collectionLikeType0 = new CollectionLikeType(simpleType0, simpleType0);
        CollectionLikeType collectionLikeType1 = collectionLikeType0.withStaticTyping();
        CollectionLikeType collectionLikeType2 = collectionLikeType1.withStaticTyping();
        collectionLikeType2.useStaticType();
    }

    @Test(timeout = 4000)
    public void test1923() throws Throwable {
        SimpleType simpleType0 = TypeFactory.CORE_TYPE_COMPARABLE;
        Class<ArrayType> class0 = ArrayType.class;
        CollectionLikeType collectionLikeType0 = CollectionLikeType.construct((Class<?>) class0, (JavaType) simpleType0);
        CollectionType collectionType0 = new CollectionType(collectionLikeType0, simpleType0);
        CollectionLikeType collectionLikeType1 = collectionType0.withValueHandler(collectionType0);
        LRUMap<Object, JavaType> lRUMap0 = new LRUMap<Object, JavaType>(75, 75);
        TypeFactory typeFactory0 = new TypeFactory(lRUMap0);
        Class<HashMap> class1 = HashMap.class;
        MapType mapType0 = typeFactory0.constructMapType((Class<? extends Map>) class1, (JavaType) simpleType0, (JavaType) collectionLikeType1);
        collectionLikeType1.useStaticType();
    }

    @Test(timeout = 4000)
    public void test1924() throws Throwable {
        SimpleType simpleType0 = TypeFactory.CORE_TYPE_COMPARABLE;
        Class<ArrayType> class0 = ArrayType.class;
        CollectionLikeType collectionLikeType0 = CollectionLikeType.construct((Class<?>) class0, (JavaType) simpleType0);
        CollectionType collectionType0 = new CollectionType(collectionLikeType0, simpleType0);
        CollectionLikeType collectionLikeType1 = collectionType0.withValueHandler(collectionType0);
        LRUMap<Object, JavaType> lRUMap0 = new LRUMap<Object, JavaType>(75, 75);
        TypeFactory typeFactory0 = new TypeFactory(lRUMap0);
        Class<HashMap> class1 = HashMap.class;
        MapType mapType0 = typeFactory0.constructMapType((Class<? extends Map>) class1, (JavaType) simpleType0, (JavaType) collectionLikeType1);
        mapType0.hasHandlers();
    }

    @Test(timeout = 4000)
    public void test2025() throws Throwable {
        TypeFactory typeFactory0 = TypeFactory.instance;
        Class<LinkedList> class0 = LinkedList.class;
        CollectionType collectionType0 = typeFactory0.constructRawCollectionType(class0);
        collectionType0.hasHandlers();
    }

    @Test(timeout = 4000)
    public void test2126() throws Throwable {
        Class<Module> class0 = Module.class;
        TypeFactory typeFactory0 = TypeFactory.defaultInstance();
        Class<LinkedList> class1 = LinkedList.class;
        SimpleType simpleType0 = TypeFactory.CORE_TYPE_STRING;
        SimpleType simpleType1 = simpleType0.withTypeHandler(class0);
        CollectionType collectionType0 = typeFactory0.constructCollectionType((Class<? extends Collection>) class1, (JavaType) simpleType1);
        collectionType0.hasHandlers();
    }

    @Test(timeout = 4000)
    public void test2227() throws Throwable {
        SimpleType simpleType0 = TypeFactory.CORE_TYPE_BOOL;
        CollectionLikeType collectionLikeType0 = new CollectionLikeType(simpleType0, (JavaType) null);
        String string0 = collectionLikeType0.toCanonical();
    }

    @Test(timeout = 4000)
    public void test2328() throws Throwable {
        SimpleType simpleType0 = TypeFactory.CORE_TYPE_BOOL;
        CollectionLikeType collectionLikeType0 = new CollectionLikeType(simpleType0, simpleType0);
        String string0 = collectionLikeType0.toCanonical();
    }

    @Test(timeout = 4000)
    public void test2429() throws Throwable {
        SimpleType simpleType0 = TypeFactory.CORE_TYPE_CLASS;
        Class<Object> class0 = Object.class;
        CollectionLikeType collectionLikeType0 = CollectionLikeType.construct((Class<?>) class0, (JavaType) simpleType0);
        boolean boolean0 = collectionLikeType0.equals((Object) null);
    }

    @Test(timeout = 4000)
    public void test2430() throws Throwable {
        SimpleType simpleType0 = TypeFactory.CORE_TYPE_CLASS;
        Class<Object> class0 = Object.class;
        CollectionLikeType collectionLikeType0 = CollectionLikeType.construct((Class<?>) class0, (JavaType) simpleType0);
        boolean boolean0 = collectionLikeType0.equals((Object) null);
        collectionLikeType0.useStaticType();
    }

    @Test(timeout = 4000)
    public void test2531() throws Throwable {
        SimpleType simpleType0 = TypeFactory.CORE_TYPE_OBJECT;
        Class<ArrayType> class0 = ArrayType.class;
        CollectionLikeType collectionLikeType0 = CollectionLikeType.construct((Class<?>) class0, (JavaType) simpleType0);
        CollectionLikeType collectionLikeType1 = CollectionLikeType.construct((Class<?>) class0, (JavaType) collectionLikeType0);
        boolean boolean0 = collectionLikeType0.equals(collectionLikeType1);
    }

    @Test(timeout = 4000)
    public void test2532() throws Throwable {
        SimpleType simpleType0 = TypeFactory.CORE_TYPE_OBJECT;
        Class<ArrayType> class0 = ArrayType.class;
        CollectionLikeType collectionLikeType0 = CollectionLikeType.construct((Class<?>) class0, (JavaType) simpleType0);
        CollectionLikeType collectionLikeType1 = CollectionLikeType.construct((Class<?>) class0, (JavaType) collectionLikeType0);
        boolean boolean0 = collectionLikeType0.equals(collectionLikeType1);
        collectionLikeType1.useStaticType();
    }

    @Test(timeout = 4000)
    public void test2533() throws Throwable {
        SimpleType simpleType0 = TypeFactory.CORE_TYPE_OBJECT;
        Class<ArrayType> class0 = ArrayType.class;
        CollectionLikeType collectionLikeType0 = CollectionLikeType.construct((Class<?>) class0, (JavaType) simpleType0);
        CollectionLikeType collectionLikeType1 = CollectionLikeType.construct((Class<?>) class0, (JavaType) collectionLikeType0);
        boolean boolean0 = collectionLikeType0.equals(collectionLikeType1);
        collectionLikeType1.equals((Object) collectionLikeType0);
    }

    @Test(timeout = 4000)
    public void test2634() throws Throwable {
        SimpleType simpleType0 = TypeFactory.CORE_TYPE_BOOL;
        CollectionLikeType collectionLikeType0 = new CollectionLikeType(simpleType0, simpleType0);
        CollectionLikeType collectionLikeType1 = collectionLikeType0.withStaticTyping();
        boolean boolean0 = collectionLikeType1.equals(collectionLikeType0);
    }

    @Test(timeout = 4000)
    public void test2635() throws Throwable {
        SimpleType simpleType0 = TypeFactory.CORE_TYPE_BOOL;
        CollectionLikeType collectionLikeType0 = new CollectionLikeType(simpleType0, simpleType0);
        CollectionLikeType collectionLikeType1 = collectionLikeType0.withStaticTyping();
        boolean boolean0 = collectionLikeType1.equals(collectionLikeType0);
        collectionLikeType1.useStaticType();
    }
}
