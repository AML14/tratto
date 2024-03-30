/*
 * This file was automatically generated by EvoSuite
 * Thu Mar 28 00:15:41 GMT 2024
 */
package com.fasterxml.jackson.databind.deser;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.deser.AbstractDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializerFactory;
import com.fasterxml.jackson.databind.deser.BuilderBasedDeserializer;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.deser.DeserializerCache;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.ShortNode;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.fasterxml.jackson.databind.type.TypeBindings;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class DeserializerCache_ESTest extends DeserializerCache_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        DeserializerCache deserializerCache0 = new DeserializerCache();
        int int0 = deserializerCache0.cachedDeserializersCount();
        assertEquals(0, int0);
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        DeserializerCache deserializerCache0 = new DeserializerCache();
        Object object0 = deserializerCache0.writeReplace();
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        DeserializerCache deserializerCache0 = new DeserializerCache();
        deserializerCache0.flushCachedDeserializers();
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        TypeFactory typeFactory0 = TypeFactory.defaultInstance();
        Class<ConcurrentHashMap> class0 = ConcurrentHashMap.class;
        MapType mapType0 = typeFactory0.constructMapType(class0, class0, class0);
        ObjectReader objectReader0 = objectMapper0.readerFor((JavaType) mapType0);
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        Class<AbstractDeserializer> class0 = AbstractDeserializer.class;
        ObjectReader objectReader0 = objectMapper0.readerWithView(class0);
        TypeFactory typeFactory0 = objectReader0.getTypeFactory();
        TypeBindings typeBindings0 = new TypeBindings(typeFactory0, class0);
        JavaType javaType0 = typeFactory0.constructType((Type) typeBindings0.UNBOUND, typeBindings0);
        objectMapper0.canDeserialize(typeBindings0.UNBOUND);
        ObjectReader objectReader1 = objectMapper0.readerFor(javaType0);
        objectReader1.equals((Object) objectReader0);
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        TypeFactory typeFactory0 = TypeFactory.defaultInstance();
        Class<MapType> class0 = MapType.class;
        CollectionLikeType collectionLikeType0 = typeFactory0.constructRawCollectionLikeType(class0);
        ObjectReader objectReader0 = objectMapper0.readerFor((JavaType) collectionLikeType0);
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        TypeFactory typeFactory0 = TypeFactory.defaultInstance();
        Class<ConcurrentHashMap> class0 = ConcurrentHashMap.class;
        Class<Object> class1 = Object.class;
        MapType mapType0 = typeFactory0.constructMapType(class0, class1, class0);
        ObjectReader objectReader0 = objectMapper0.readerFor((JavaType) mapType0);
    }

    @Test(timeout = 4000)
    public void test077() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        JavaType javaType0 = TypeFactory.unknownType();
        objectMapper0.readerFor(javaType0);
        boolean boolean0 = objectMapper0.canDeserialize(javaType0);
    }

    @Test(timeout = 4000)
    public void test088() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        TypeFactory typeFactory0 = TypeFactory.defaultInstance();
        Class<ShortNode> class0 = ShortNode.class;
        TypeBindings typeBindings0 = new TypeBindings(typeFactory0, class0);
        Class<CollectionType> class1 = CollectionType.class;
        MapLikeType mapLikeType0 = typeFactory0.constructMapLikeType(class1, typeBindings0.UNBOUND, typeBindings0.UNBOUND);
        boolean boolean0 = objectMapper0.canDeserialize((JavaType) mapLikeType0);
    }

    @Test(timeout = 4000)
    public void test099() throws Throwable {
        DeserializerCache deserializerCache0 = new DeserializerCache();
        BeanDeserializerFactory beanDeserializerFactory0 = BeanDeserializerFactory.instance;
        DefaultDeserializationContext.Impl defaultDeserializationContext_Impl0 = new DefaultDeserializationContext.Impl(beanDeserializerFactory0);
        deserializerCache0.hasValueDeserializerFor(defaultDeserializationContext_Impl0, beanDeserializerFactory0, (JavaType) null);
    }

    @Test(timeout = 4000)
    public void test1010() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        Class<BuilderBasedDeserializer> class0 = BuilderBasedDeserializer.class;
        SimpleType simpleType0 = SimpleType.construct(class0);
        ObjectMapper objectMapper1 = objectMapper0.enableDefaultTyping();
        ObjectReader objectReader0 = objectMapper1.readerFor((JavaType) simpleType0);
        assertNotNull(objectReader0);
    }

    @Test(timeout = 4000)
    public void test1111() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        MapperFeature mapperFeature0 = MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES;
        objectMapper0.configure(mapperFeature0, true);
        Class<BuilderBasedDeserializer> class0 = BuilderBasedDeserializer.class;
        SimpleType simpleType0 = SimpleType.construct(class0);
        objectMapper0.readerFor((JavaType) simpleType0);
    }

    @Test(timeout = 4000)
    public void test1212() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        TypeFactory typeFactory0 = TypeFactory.defaultInstance();
        Class<ConcurrentHashMap> class0 = ConcurrentHashMap.class;
        Class<Object> class1 = Object.class;
        MapType mapType0 = typeFactory0.constructMapType(class0, class0, class1);
        ArrayType arrayType0 = ArrayType.construct(mapType0, mapType0, class1);
        MapLikeType mapLikeType0 = mapType0.withContentValueHandler(arrayType0);
        objectMapper0.readerFor((JavaType) mapLikeType0);
    }

    @Test(timeout = 4000)
    public void test1313() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        Class<SerializationFeature> class0 = SerializationFeature.class;
        SimpleType simpleType0 = SimpleType.construct(class0);
        ObjectReader objectReader0 = objectMapper0.readerFor((JavaType) simpleType0);
        assertNotNull(objectReader0);
    }

    @Test(timeout = 4000)
    public void test1414() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        TypeFactory typeFactory0 = TypeFactory.defaultInstance();
        Class<NullNode> class0 = NullNode.class;
        ArrayType arrayType0 = typeFactory0.constructArrayType(class0);
        ObjectReader objectReader0 = objectMapper0.readerFor((JavaType) arrayType0);
        assertNotNull(objectReader0);
    }

    @Test(timeout = 4000)
    public void test1515() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        TypeFactory typeFactory0 = TypeFactory.defaultInstance();
        Class<ConcurrentHashMap> class0 = ConcurrentHashMap.class;
        Class<MissingNode> class1 = MissingNode.class;
        MapType mapType0 = typeFactory0.constructMapType(class0, class0, class1);
        MapLikeType mapLikeType0 = mapType0.withKeyValueHandler(class0);
        objectMapper0.readerFor((JavaType) mapLikeType0);
    }

    @Test(timeout = 4000)
    public void test1616() throws Throwable {
        DeserializerCache deserializerCache0 = new DeserializerCache();
        Class<Module> class0 = Module.class;
        SimpleType simpleType0 = SimpleType.constructUnsafe(class0);
        deserializerCache0._handleUnknownValueDeserializer(simpleType0);
    }
}
