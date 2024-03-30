/*
 * This file was automatically generated by EvoSuite
 * Sat Nov 04 17:09:33 GMT 2023
 */
package com.fasterxml.jackson.databind;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.fasterxml.jackson.databind.type.TypeBindings;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Stack;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class JavaType_ESTest extends JavaType_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        JsonFactory jsonFactory0 = new JsonFactory();
        ObjectMapper objectMapper0 = new ObjectMapper(jsonFactory0);
        Class<Throwable> class0 = Throwable.class;
        ObjectReader objectReader0 = objectMapper0.reader((Class<?>) class0);
        TypeFactory typeFactory0 = objectReader0.getTypeFactory();
        Class<Object> class1 = Object.class;
        JavaType javaType0 = TypeFactory.unknownType();
        CollectionType collectionType0 = CollectionType.construct(class1, javaType0);
        MapType mapType0 = MapType.construct(class0, collectionType0, collectionType0);
        MapLikeType mapLikeType0 = mapType0.withKeyValueHandler(typeFactory0);
        Class<String> class2 = String.class;
        JavaType javaType1 = mapLikeType0.narrowKey(class2);
        javaType1.useStaticType();
    }

    @Test(timeout = 4000)
    public void test001() throws Throwable {
        JsonFactory jsonFactory0 = new JsonFactory();
        ObjectMapper objectMapper0 = new ObjectMapper(jsonFactory0);
        Class<Throwable> class0 = Throwable.class;
        ObjectReader objectReader0 = objectMapper0.reader((Class<?>) class0);
        TypeFactory typeFactory0 = objectReader0.getTypeFactory();
        Class<Object> class1 = Object.class;
        JavaType javaType0 = TypeFactory.unknownType();
        CollectionType collectionType0 = CollectionType.construct(class1, javaType0);
        MapType mapType0 = MapType.construct(class0, collectionType0, collectionType0);
        MapLikeType mapLikeType0 = mapType0.withKeyValueHandler(typeFactory0);
        Class<String> class2 = String.class;
        JavaType javaType1 = mapLikeType0.narrowKey(class2);
        javaType1.isConcrete();
    }

    @Test(timeout = 4000)
    public void test002() throws Throwable {
        JsonFactory jsonFactory0 = new JsonFactory();
        ObjectMapper objectMapper0 = new ObjectMapper(jsonFactory0);
        Class<Throwable> class0 = Throwable.class;
        ObjectReader objectReader0 = objectMapper0.reader((Class<?>) class0);
        TypeFactory typeFactory0 = objectReader0.getTypeFactory();
        Class<Object> class1 = Object.class;
        JavaType javaType0 = TypeFactory.unknownType();
        CollectionType collectionType0 = CollectionType.construct(class1, javaType0);
        MapType mapType0 = MapType.construct(class0, collectionType0, collectionType0);
        MapLikeType mapLikeType0 = mapType0.withKeyValueHandler(typeFactory0);
        Class<String> class2 = String.class;
        JavaType javaType1 = mapLikeType0.narrowKey(class2);
        javaType1.isCollectionLikeType();
    }

    @Test(timeout = 4000)
    public void test003() throws Throwable {
        JsonFactory jsonFactory0 = new JsonFactory();
        ObjectMapper objectMapper0 = new ObjectMapper(jsonFactory0);
        Class<Throwable> class0 = Throwable.class;
        ObjectReader objectReader0 = objectMapper0.reader((Class<?>) class0);
        TypeFactory typeFactory0 = objectReader0.getTypeFactory();
        Class<Object> class1 = Object.class;
        JavaType javaType0 = TypeFactory.unknownType();
        CollectionType collectionType0 = CollectionType.construct(class1, javaType0);
        MapType mapType0 = MapType.construct(class0, collectionType0, collectionType0);
        MapLikeType mapLikeType0 = mapType0.withKeyValueHandler(typeFactory0);
        Class<String> class2 = String.class;
        JavaType javaType1 = mapLikeType0.narrowKey(class2);
        collectionType0.isMapLikeType();
    }

    @Test(timeout = 4000)
    public void test004() throws Throwable {
        JsonFactory jsonFactory0 = new JsonFactory();
        ObjectMapper objectMapper0 = new ObjectMapper(jsonFactory0);
        Class<Throwable> class0 = Throwable.class;
        ObjectReader objectReader0 = objectMapper0.reader((Class<?>) class0);
        TypeFactory typeFactory0 = objectReader0.getTypeFactory();
        Class<Object> class1 = Object.class;
        JavaType javaType0 = TypeFactory.unknownType();
        CollectionType collectionType0 = CollectionType.construct(class1, javaType0);
        MapType mapType0 = MapType.construct(class0, collectionType0, collectionType0);
        MapLikeType mapLikeType0 = mapType0.withKeyValueHandler(typeFactory0);
        Class<String> class2 = String.class;
        JavaType javaType1 = mapLikeType0.narrowKey(class2);
        javaType1.equals((Object) mapLikeType0);
    }

    @Test(timeout = 4000)
    public void test015() throws Throwable {
        Class<Object> class0 = Object.class;
        SimpleType simpleType0 = SimpleType.constructUnsafe(class0);
        simpleType0.isArrayType();
    }

    @Test(timeout = 4000)
    public void test016() throws Throwable {
        Class<Object> class0 = Object.class;
        SimpleType simpleType0 = SimpleType.constructUnsafe(class0);
        simpleType0.isMapLikeType();
    }

    @Test(timeout = 4000)
    public void test017() throws Throwable {
        Class<Object> class0 = Object.class;
        SimpleType simpleType0 = SimpleType.constructUnsafe(class0);
        ObjectMapper objectMapper0 = new ObjectMapper();
        objectMapper0.reader((JavaType) simpleType0);
        simpleType0.useStaticType();
    }

    @Test(timeout = 4000)
    public void test018() throws Throwable {
        Class<Object> class0 = Object.class;
        SimpleType simpleType0 = SimpleType.constructUnsafe(class0);
        ObjectMapper objectMapper0 = new ObjectMapper();
        objectMapper0.reader((JavaType) simpleType0);
        simpleType0.isCollectionLikeType();
    }

    @Test(timeout = 4000)
    public void test019() throws Throwable {
        Class<Object> class0 = Object.class;
        SimpleType simpleType0 = SimpleType.constructUnsafe(class0);
        ObjectMapper objectMapper0 = new ObjectMapper();
        objectMapper0.reader((JavaType) simpleType0);
        simpleType0.isConcrete();
    }

    @Test(timeout = 4000)
    public void test0210() throws Throwable {
        Class<Object> class0 = Object.class;
        SimpleType simpleType0 = SimpleType.constructUnsafe(class0);
        simpleType0.getGenericSignature();
        simpleType0.useStaticType();
    }

    @Test(timeout = 4000)
    public void test0311() throws Throwable {
        Class<Integer> class0 = Integer.TYPE;
        SimpleType simpleType0 = SimpleType.construct(class0);
        simpleType0.getErasedSignature();
        simpleType0.useStaticType();
    }

    @Test(timeout = 4000)
    public void test0412() throws Throwable {
        Class<Object> class0 = Object.class;
        SimpleType simpleType0 = SimpleType.constructUnsafe(class0);
        Class<Throwable> class1 = Throwable.class;
        JavaType javaType0 = simpleType0.widenBy(class1);
        simpleType0.useStaticType();
    }

    @Test(timeout = 4000)
    public void test0413() throws Throwable {
        Class<Object> class0 = Object.class;
        SimpleType simpleType0 = SimpleType.constructUnsafe(class0);
        Class<Throwable> class1 = Throwable.class;
        JavaType javaType0 = simpleType0.widenBy(class1);
        javaType0.equals((Object) simpleType0);
    }

    @Test(timeout = 4000)
    public void test0414() throws Throwable {
        Class<Object> class0 = Object.class;
        SimpleType simpleType0 = SimpleType.constructUnsafe(class0);
        Class<Throwable> class1 = Throwable.class;
        JavaType javaType0 = simpleType0.widenBy(class1);
    }

    @Test(timeout = 4000)
    public void test0515() throws Throwable {
        Class<Integer> class0 = Integer.TYPE;
        SimpleType simpleType0 = SimpleType.construct(class0);
        Class<Throwable> class1 = Throwable.class;
        CollectionType collectionType0 = CollectionType.construct(class1, simpleType0);
        ObjectMapper objectMapper0 = new ObjectMapper();
        ObjectWriter objectWriter0 = objectMapper0.writerFor((JavaType) collectionType0);
        objectWriter0.hasPrefetchedSerializer();
    }

    @Test(timeout = 4000)
    public void test0516() throws Throwable {
        Class<Integer> class0 = Integer.TYPE;
        SimpleType simpleType0 = SimpleType.construct(class0);
        Class<Throwable> class1 = Throwable.class;
        CollectionType collectionType0 = CollectionType.construct(class1, simpleType0);
        ObjectMapper objectMapper0 = new ObjectMapper();
        ObjectWriter objectWriter0 = objectMapper0.writerFor((JavaType) collectionType0);
        collectionType0.useStaticType();
    }

    @Test(timeout = 4000)
    public void test0617() throws Throwable {
        SimpleType simpleType0 = (SimpleType) TypeBindings.UNBOUND;
        JavaType javaType0 = simpleType0.getContentType();
    }

    @Test(timeout = 4000)
    public void test0718() throws Throwable {
        Class<Object> class0 = Object.class;
        SimpleType simpleType0 = SimpleType.constructUnsafe(class0);
        JavaType javaType0 = simpleType0.narrowBy(class0);
    }

    @Test(timeout = 4000)
    public void test0719() throws Throwable {
        Class<Object> class0 = Object.class;
        SimpleType simpleType0 = SimpleType.constructUnsafe(class0);
        JavaType javaType0 = simpleType0.narrowBy(class0);
        javaType0.useStaticType();
    }

    @Test(timeout = 4000)
    public void test0820() throws Throwable {
        JsonFactory jsonFactory0 = new JsonFactory();
        Class<Throwable> class0 = Throwable.class;
        Class<Object> class1 = Object.class;
        JavaType javaType0 = TypeFactory.unknownType();
        CollectionType collectionType0 = CollectionType.construct(class1, javaType0);
        CollectionType collectionType1 = collectionType0.withTypeHandler(jsonFactory0);
        MapType mapType0 = MapType.construct(class0, collectionType1, collectionType0);
        Class<String> class2 = String.class;
        JavaType javaType1 = mapType0.narrowKey(class2);
        mapType0.useStaticType();
    }

    @Test(timeout = 4000)
    public void test0821() throws Throwable {
        JsonFactory jsonFactory0 = new JsonFactory();
        Class<Throwable> class0 = Throwable.class;
        Class<Object> class1 = Object.class;
        JavaType javaType0 = TypeFactory.unknownType();
        CollectionType collectionType0 = CollectionType.construct(class1, javaType0);
        CollectionType collectionType1 = collectionType0.withTypeHandler(jsonFactory0);
        MapType mapType0 = MapType.construct(class0, collectionType1, collectionType0);
        Class<String> class2 = String.class;
        JavaType javaType1 = mapType0.narrowKey(class2);
        javaType1.equals((Object) mapType0);
    }

    @Test(timeout = 4000)
    public void test0922() throws Throwable {
        Class<Integer> class0 = Integer.TYPE;
        SimpleType simpleType0 = SimpleType.construct(class0);
        JavaType javaType0 = simpleType0.forcedNarrowBy(class0);
    }

    @Test(timeout = 4000)
    public void test0923() throws Throwable {
        Class<Integer> class0 = Integer.TYPE;
        SimpleType simpleType0 = SimpleType.construct(class0);
        JavaType javaType0 = simpleType0.forcedNarrowBy(class0);
        javaType0.useStaticType();
    }

    @Test(timeout = 4000)
    public void test1024() throws Throwable {
        TypeFactory typeFactory0 = TypeFactory.defaultInstance();
        Class<Throwable> class0 = Throwable.class;
        Class<Stack> class1 = Stack.class;
        CollectionType collectionType0 = typeFactory0.constructCollectionType(class1, class0);
        Integer integer0 = new Integer(48);
        CollectionType collectionType1 = collectionType0.withValueHandler(integer0);
        Class<JsonGenerator.Feature> class2 = JsonGenerator.Feature.class;
        JavaType javaType0 = collectionType1.forcedNarrowBy(class2);
        javaType0.isEnumType();
    }

    @Test(timeout = 4000)
    public void test1025() throws Throwable {
        TypeFactory typeFactory0 = TypeFactory.defaultInstance();
        Class<Throwable> class0 = Throwable.class;
        Class<Stack> class1 = Stack.class;
        CollectionType collectionType0 = typeFactory0.constructCollectionType(class1, class0);
        Integer integer0 = new Integer(48);
        CollectionType collectionType1 = collectionType0.withValueHandler(integer0);
        Class<JsonGenerator.Feature> class2 = JsonGenerator.Feature.class;
        JavaType javaType0 = collectionType1.forcedNarrowBy(class2);
        collectionType0.useStaticType();
    }

    @Test(timeout = 4000)
    public void test1126() throws Throwable {
        TypeFactory typeFactory0 = TypeFactory.defaultInstance();
        Class<Throwable> class0 = Throwable.class;
        Class<Stack> class1 = Stack.class;
        CollectionType collectionType0 = typeFactory0.constructCollectionType(class1, class0);
        Object object0 = new Object();
        CollectionType collectionType1 = collectionType0.withTypeHandler(object0);
        Class<JsonGenerator.Feature> class2 = JsonGenerator.Feature.class;
        JavaType javaType0 = collectionType1.forcedNarrowBy(class2);
        javaType0.isEnumType();
    }

    @Test(timeout = 4000)
    public void test1127() throws Throwable {
        TypeFactory typeFactory0 = TypeFactory.defaultInstance();
        Class<Throwable> class0 = Throwable.class;
        Class<Stack> class1 = Stack.class;
        CollectionType collectionType0 = typeFactory0.constructCollectionType(class1, class0);
        Object object0 = new Object();
        CollectionType collectionType1 = collectionType0.withTypeHandler(object0);
        Class<JsonGenerator.Feature> class2 = JsonGenerator.Feature.class;
        JavaType javaType0 = collectionType1.forcedNarrowBy(class2);
        collectionType0.useStaticType();
    }

    @Test(timeout = 4000)
    public void test1228() throws Throwable {
        Class<Integer> class0 = Integer.TYPE;
        SimpleType simpleType0 = SimpleType.construct(class0);
        JavaType javaType0 = simpleType0.widenBy(class0);
        javaType0.useStaticType();
    }

    @Test(timeout = 4000)
    public void test1229() throws Throwable {
        Class<Integer> class0 = Integer.TYPE;
        SimpleType simpleType0 = SimpleType.construct(class0);
        JavaType javaType0 = simpleType0.widenBy(class0);
    }

    @Test(timeout = 4000)
    public void test1330() throws Throwable {
        Class<Integer> class0 = Integer.TYPE;
        SimpleType simpleType0 = SimpleType.construct(class0);
        boolean boolean0 = simpleType0.hasRawClass(class0);
    }

    @Test(timeout = 4000)
    public void test1331() throws Throwable {
        Class<Integer> class0 = Integer.TYPE;
        SimpleType simpleType0 = SimpleType.construct(class0);
        boolean boolean0 = simpleType0.hasRawClass(class0);
        simpleType0.useStaticType();
    }

    @Test(timeout = 4000)
    public void test1432() throws Throwable {
        TypeFactory typeFactory0 = TypeFactory.defaultInstance();
        Class<Map> class0 = Map.class;
        Class<ObjectReader> class1 = ObjectReader.class;
        MapType mapType0 = typeFactory0.constructMapType(class0, class1, class1);
        boolean boolean0 = mapType0.isConcrete();
    }

    @Test(timeout = 4000)
    public void test1433() throws Throwable {
        TypeFactory typeFactory0 = TypeFactory.defaultInstance();
        Class<Map> class0 = Map.class;
        Class<ObjectReader> class1 = ObjectReader.class;
        MapType mapType0 = typeFactory0.constructMapType(class0, class1, class1);
        boolean boolean0 = mapType0.isConcrete();
        mapType0.useStaticType();
    }

    @Test(timeout = 4000)
    public void test1534() throws Throwable {
        Class<Integer> class0 = Integer.TYPE;
        SimpleType simpleType0 = SimpleType.construct(class0);
        ArrayType arrayType0 = ArrayType.construct(simpleType0, class0, class0);
        boolean boolean0 = arrayType0.hasGenericTypes();
    }

    @Test(timeout = 4000)
    public void test1535() throws Throwable {
        Class<Integer> class0 = Integer.TYPE;
        SimpleType simpleType0 = SimpleType.construct(class0);
        ArrayType arrayType0 = ArrayType.construct(simpleType0, class0, class0);
        boolean boolean0 = arrayType0.hasGenericTypes();
        arrayType0.useStaticType();
    }

    @Test(timeout = 4000)
    public void test1636() throws Throwable {
        TypeFactory typeFactory0 = TypeFactory.defaultInstance();
        Class<Map> class0 = Map.class;
        MapType mapType0 = typeFactory0.constructRawMapType(class0);
        ArrayType arrayType0 = typeFactory0.constructArrayType((JavaType) mapType0);
        boolean boolean0 = arrayType0.hasGenericTypes();
    }

    @Test(timeout = 4000)
    public void test1637() throws Throwable {
        TypeFactory typeFactory0 = TypeFactory.defaultInstance();
        Class<Map> class0 = Map.class;
        MapType mapType0 = typeFactory0.constructRawMapType(class0);
        ArrayType arrayType0 = typeFactory0.constructArrayType((JavaType) mapType0);
        boolean boolean0 = arrayType0.hasGenericTypes();
        arrayType0.useStaticType();
    }

    @Test(timeout = 4000)
    public void test1738() throws Throwable {
        TypeFactory typeFactory0 = TypeFactory.defaultInstance();
        Class<Map> class0 = Map.class;
        JavaType javaType0 = typeFactory0.constructType((Type) class0);
        JavaType javaType1 = javaType0.containedTypeOrUnknown(0);
        javaType1.isFinal();
    }

    @Test(timeout = 4000)
    public void test1839() throws Throwable {
        Class<Integer> class0 = Integer.TYPE;
        SimpleType simpleType0 = SimpleType.construct(class0);
        JavaType javaType0 = simpleType0.containedTypeOrUnknown(48);
    }

    @Test(timeout = 4000)
    public void test1840() throws Throwable {
        Class<Integer> class0 = Integer.TYPE;
        SimpleType simpleType0 = SimpleType.construct(class0);
        JavaType javaType0 = simpleType0.containedTypeOrUnknown(48);
        javaType0.useStaticType();
    }

    @Test(timeout = 4000)
    public void test1941() throws Throwable {
        Class<Integer> class0 = Integer.TYPE;
        SimpleType simpleType0 = (SimpleType) TypeBindings.UNBOUND;
        simpleType0.narrowBy(class0);
    }
}
