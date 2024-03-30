/*
 * This file was automatically generated by EvoSuite
 * Thu Mar 28 00:04:56 GMT 2024
 */
package com.fasterxml.jackson.databind.ser;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.cfg.SerializerFactoryConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.BasicBeanDescription;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.introspect.POJOPropertiesCollector;
import com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder;
import com.fasterxml.jackson.databind.jsontype.impl.MinimalClassNameIdResolver;
import com.fasterxml.jackson.databind.module.SimpleSerializers;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerBuilder;
import com.fasterxml.jackson.databind.ser.BeanSerializerFactory;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.fasterxml.jackson.databind.ser.SerializerFactory;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.fasterxml.jackson.databind.type.TypeBindings;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.List;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class BeanSerializerFactory_ESTest extends BeanSerializerFactory_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        BeanSerializerFactory beanSerializerFactory0 = BeanSerializerFactory.instance;
        BeanPropertyWriter beanPropertyWriter0 = new BeanPropertyWriter();
        Class<Object>[] classArray0 = (Class<Object>[]) Array.newInstance(Class.class, 0);
        BeanPropertyWriter beanPropertyWriter1 = beanSerializerFactory0.instance.constructFilteredBeanWriter(beanPropertyWriter0, classArray0);
        beanPropertyWriter1.isUnwrapping();
        assertFalse(beanPropertyWriter1.isUnwrapping());
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        SerializerFactoryConfig serializerFactoryConfig0 = new SerializerFactoryConfig();
        BeanSerializerFactory beanSerializerFactory0 = new BeanSerializerFactory(serializerFactoryConfig0);
        SerializerFactory serializerFactory0 = beanSerializerFactory0.withConfig(serializerFactoryConfig0);
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        Class<MapType> class0 = MapType.class;
        boolean boolean0 = objectMapper0.canSerialize(class0);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        BeanSerializerFactory beanSerializerFactory0 = BeanSerializerFactory.instance;
        POJOPropertiesCollector pOJOPropertiesCollector0 = mock(POJOPropertiesCollector.class, new ViolatedAssumptionAnswer());
        doReturn((AnnotatedClass) null).when(pOJOPropertiesCollector0).getClassDef();
        doReturn((MapperConfig) null).when(pOJOPropertiesCollector0).getConfig();
        doReturn((AnnotatedMethod) null).when(pOJOPropertiesCollector0).getJsonValueMethod();
        doReturn((ObjectIdInfo) null).when(pOJOPropertiesCollector0).getObjectIdInfo();
        doReturn((JavaType) null).when(pOJOPropertiesCollector0).getType();
        BasicBeanDescription basicBeanDescription0 = BasicBeanDescription.forDeserialization(pOJOPropertiesCollector0);
        DefaultSerializerProvider.Impl defaultSerializerProvider_Impl0 = new DefaultSerializerProvider.Impl();
        TypeFactory typeFactory0 = TypeFactory.defaultInstance();
        Class<MinimalClassNameIdResolver> class0 = MinimalClassNameIdResolver.class;
        ArrayType arrayType0 = typeFactory0.constructArrayType(class0);
        beanSerializerFactory0._createSerializer2(defaultSerializerProvider_Impl0, arrayType0, basicBeanDescription0, true);
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        Class<InputStream> class0 = InputStream.class;
        SimpleType simpleType0 = (SimpleType) TypeBindings.UNBOUND;
        CollectionType collectionType0 = CollectionType.construct(class0, simpleType0);
        ObjectWriter objectWriter0 = objectMapper0.writerFor((JavaType) collectionType0);
        objectWriter0.hasPrefetchedSerializer();
        assertFalse(objectWriter0.hasPrefetchedSerializer());
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        SimpleSerializers simpleSerializers0 = new SimpleSerializers();
        BeanSerializerFactory beanSerializerFactory0 = BeanSerializerFactory.instance;
        SerializerFactory serializerFactory0 = beanSerializerFactory0.withAdditionalSerializers(simpleSerializers0);
        ObjectMapper objectMapper1 = objectMapper0.setSerializerFactory(serializerFactory0);
        Class<POJOPropertyBuilder> class0 = POJOPropertyBuilder.class;
        boolean boolean0 = objectMapper1.canSerialize(class0);
    }

    @Test(timeout = 4000)
    public void test067() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        Class<JsonParser.Feature> class0 = JsonParser.Feature.class;
        boolean boolean0 = objectMapper0.canSerialize(class0);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test078() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        SerializerFactoryConfig serializerFactoryConfig0 = new SerializerFactoryConfig();
        BeanSerializerModifier beanSerializerModifier0 = mock(BeanSerializerModifier.class, new ViolatedAssumptionAnswer());
        doReturn((JsonSerializer) null).when(beanSerializerModifier0).modifySerializer(any(com.fasterxml.jackson.databind.SerializationConfig.class), any(com.fasterxml.jackson.databind.BeanDescription.class), any(com.fasterxml.jackson.databind.JsonSerializer.class));
        SerializerFactoryConfig serializerFactoryConfig1 = serializerFactoryConfig0.withSerializerModifier(beanSerializerModifier0);
        BeanSerializerFactory beanSerializerFactory0 = new BeanSerializerFactory(serializerFactoryConfig1);
        objectMapper0.setSerializerFactory(beanSerializerFactory0);
        Class<Integer> class0 = Integer.class;
        boolean boolean0 = objectMapper0.canSerialize(class0);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test089() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        BeanSerializerFactory beanSerializerFactory0 = BeanSerializerFactory.instance;
        BeanSerializerModifier beanSerializerModifier0 = mock(BeanSerializerModifier.class, new ViolatedAssumptionAnswer());
        doReturn((List) null).when(beanSerializerModifier0).changeProperties(any(com.fasterxml.jackson.databind.SerializationConfig.class), any(com.fasterxml.jackson.databind.BeanDescription.class), anyList());
        doReturn((JsonSerializer) null).when(beanSerializerModifier0).modifyEnumSerializer(any(com.fasterxml.jackson.databind.SerializationConfig.class), any(com.fasterxml.jackson.databind.JavaType.class), any(com.fasterxml.jackson.databind.BeanDescription.class), any(com.fasterxml.jackson.databind.JsonSerializer.class));
        doReturn((List) null).when(beanSerializerModifier0).orderProperties(any(com.fasterxml.jackson.databind.SerializationConfig.class), any(com.fasterxml.jackson.databind.BeanDescription.class), anyList());
        doReturn((BeanSerializerBuilder) null).when(beanSerializerModifier0).updateBuilder(any(com.fasterxml.jackson.databind.SerializationConfig.class), any(com.fasterxml.jackson.databind.BeanDescription.class), any(com.fasterxml.jackson.databind.ser.BeanSerializerBuilder.class));
        SerializerFactory serializerFactory0 = beanSerializerFactory0.withSerializerModifier(beanSerializerModifier0);
        ObjectMapper objectMapper1 = objectMapper0.setSerializerFactory(serializerFactory0);
        Class<JsonParser.Feature> class0 = JsonParser.Feature.class;
        objectMapper1.canSerialize(class0);
    }

    @Test(timeout = 4000)
    public void test0910() throws Throwable {
        BeanSerializerFactory beanSerializerFactory0 = BeanSerializerFactory.instance;
        POJOPropertiesCollector pOJOPropertiesCollector0 = mock(POJOPropertiesCollector.class, new ViolatedAssumptionAnswer());
        doReturn((AnnotatedClass) null).when(pOJOPropertiesCollector0).getClassDef();
        doReturn((MapperConfig) null).when(pOJOPropertiesCollector0).getConfig();
        doReturn((ObjectIdInfo) null).when(pOJOPropertiesCollector0).getObjectIdInfo();
        doReturn((JavaType) null).when(pOJOPropertiesCollector0).getType();
        BasicBeanDescription basicBeanDescription0 = BasicBeanDescription.forDeserialization(pOJOPropertiesCollector0);
        DefaultSerializerProvider.Impl defaultSerializerProvider_Impl0 = new DefaultSerializerProvider.Impl();
        TypeFactory typeFactory0 = TypeFactory.defaultInstance();
        Class<MinimalClassNameIdResolver> class0 = MinimalClassNameIdResolver.class;
        ArrayType arrayType0 = typeFactory0.constructArrayType(class0);
        JsonSerializer<Object> jsonSerializer0 = beanSerializerFactory0.findBeanSerializer(defaultSerializerProvider_Impl0, arrayType0, basicBeanDescription0);
        assertNotNull(jsonSerializer0);
    }

    @Test(timeout = 4000)
    public void test1011() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        Class<Object> class0 = Object.class;
        boolean boolean0 = objectMapper0.canSerialize(class0);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test1112() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        MapperFeature[] mapperFeatureArray0 = new MapperFeature[1];
        MapperFeature mapperFeature0 = MapperFeature.REQUIRE_SETTERS_FOR_GETTERS;
        mapperFeatureArray0[0] = mapperFeature0;
        ObjectMapper objectMapper1 = objectMapper0.enable(mapperFeatureArray0);
        Class<BeanPropertyWriter> class0 = BeanPropertyWriter.class;
        boolean boolean0 = objectMapper1.canSerialize(class0);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test1213() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        PropertyAccessor propertyAccessor0 = PropertyAccessor.ALL;
        JsonAutoDetect.Visibility jsonAutoDetect_Visibility0 = JsonAutoDetect.Visibility.PROTECTED_AND_PUBLIC;
        objectMapper0.setVisibility(propertyAccessor0, jsonAutoDetect_Visibility0);
        Class<BeanSerializerFactory> class0 = BeanSerializerFactory.class;
        boolean boolean0 = objectMapper0.canSerialize(class0);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test1314() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        MapperFeature mapperFeature0 = MapperFeature.DEFAULT_VIEW_INCLUSION;
        objectMapper0.configure(mapperFeature0, false);
        Class<MinimalClassNameIdResolver> class0 = MinimalClassNameIdResolver.class;
        boolean boolean0 = objectMapper0.canSerialize(class0);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test1415() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        MapperFeature[] mapperFeatureArray0 = new MapperFeature[1];
        MapperFeature mapperFeature0 = MapperFeature.REQUIRE_SETTERS_FOR_GETTERS;
        mapperFeatureArray0[0] = mapperFeature0;
        ObjectMapper objectMapper1 = objectMapper0.enable(mapperFeatureArray0);
        objectMapper1.writeValueAsBytes(objectMapper0);
    }

    @Test(timeout = 4000)
    public void test1516() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        objectMapper0.enableDefaultTyping();
        Class<AnnotatedMethod> class0 = AnnotatedMethod.class;
        boolean boolean0 = objectMapper0.canSerialize(class0);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test1617() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        MapperFeature[] mapperFeatureArray0 = new MapperFeature[6];
        MapperFeature mapperFeature0 = MapperFeature.REQUIRE_SETTERS_FOR_GETTERS;
        mapperFeatureArray0[0] = mapperFeature0;
        mapperFeatureArray0[1] = mapperFeature0;
        mapperFeatureArray0[2] = mapperFeature0;
        mapperFeatureArray0[3] = mapperFeatureArray0[2];
        mapperFeatureArray0[4] = mapperFeatureArray0[0];
        MapperFeature mapperFeature1 = MapperFeature.CAN_OVERRIDE_ACCESS_MODIFIERS;
        mapperFeatureArray0[5] = mapperFeature1;
        objectMapper0.disable(mapperFeatureArray0);
        Class<AnnotatedMethod> class0 = AnnotatedMethod.class;
        boolean boolean0 = objectMapper0.canSerialize(class0);
        assertTrue(boolean0);
    }
}
