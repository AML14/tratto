/*
 * This file was automatically generated by EvoSuite
 * Thu Mar 28 08:14:14 GMT 2024
 */
package com.fasterxml.jackson.databind.deser;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.deser.BeanDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializerBuilder;
import com.fasterxml.jackson.databind.deser.BeanDeserializerFactory;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.deser.DeserializerFactory;
import com.fasterxml.jackson.databind.ext.CoreXMLDeserializers;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotationMap;
import com.fasterxml.jackson.databind.introspect.BasicBeanDescription;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.introspect.POJOPropertiesCollector;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext;
import com.fasterxml.jackson.databind.module.SimpleAbstractTypeResolver;
import com.fasterxml.jackson.databind.type.ResolvedRecursiveType;
import com.fasterxml.jackson.databind.type.TypeBindings;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.lang.reflect.Field;
import java.time.format.ResolverStyle;
import java.util.LinkedHashMap;
import java.util.Map;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class BeanDeserializerFactory_ESTest extends BeanDeserializerFactory_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        Class<CoreXMLDeserializers.Std> class0 = CoreXMLDeserializers.Std.class;
        ObjectReader objectReader0 = objectMapper0.readerFor(class0);
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        BeanDeserializerFactory beanDeserializerFactory0 = BeanDeserializerFactory.instance;
        DefaultDeserializationContext.Impl defaultDeserializationContext_Impl0 = new DefaultDeserializationContext.Impl(beanDeserializerFactory0);
        Class<ResolverStyle> class0 = ResolverStyle.class;
        beanDeserializerFactory0.createBuilderBasedDeserializer(defaultDeserializationContext_Impl0, (JavaType) null, (BeanDescription) null, class0);
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        BeanDeserializerFactory beanDeserializerFactory0 = BeanDeserializerFactory.instance;
        DefaultDeserializationContext.Impl defaultDeserializationContext_Impl0 = new DefaultDeserializationContext.Impl(beanDeserializerFactory0);
        BeanDeserializerBuilder beanDeserializerBuilder0 = beanDeserializerFactory0.constructBeanDeserializerBuilder(defaultDeserializationContext_Impl0, (BeanDescription) null);
        beanDeserializerFactory0.addReferenceProperties(defaultDeserializationContext_Impl0, (BeanDescription) null, beanDeserializerBuilder0);
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        BeanDeserializerFactory beanDeserializerFactory0 = BeanDeserializerFactory.instance;
        DeserializerFactory deserializerFactory0 = beanDeserializerFactory0.withConfig((DeserializerFactoryConfig) null);
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        BeanDeserializerFactory beanDeserializerFactory0 = new BeanDeserializerFactory((DeserializerFactoryConfig) null);
        DeserializerFactory deserializerFactory0 = beanDeserializerFactory0.withConfig((DeserializerFactoryConfig) null);
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        Class<Throwable> class0 = Throwable.class;
        ObjectReader objectReader0 = objectMapper0.readerFor(class0);
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        Class<Boolean> class0 = Boolean.TYPE;
        ObjectReader objectReader0 = objectMapper0.readerFor(class0);
    }

    @Test(timeout = 4000)
    public void test077() throws Throwable {
        BeanDeserializerFactory beanDeserializerFactory0 = BeanDeserializerFactory.instance;
        DefaultDeserializationContext.Impl defaultDeserializationContext_Impl0 = new DefaultDeserializationContext.Impl(beanDeserializerFactory0);
        TypeBindings typeBindings0 = TypeBindings.emptyBindings();
        Class<Object> class0 = Object.class;
        ResolvedRecursiveType resolvedRecursiveType0 = new ResolvedRecursiveType(class0, typeBindings0);
        DeserializerFactoryConfig deserializerFactoryConfig0 = new DeserializerFactoryConfig();
        BeanDeserializerModifier beanDeserializerModifier0 = mock(BeanDeserializerModifier.class, new ViolatedAssumptionAnswer());
        doReturn((JsonDeserializer) null).when(beanDeserializerModifier0).modifyDeserializer(any(com.fasterxml.jackson.databind.DeserializationConfig.class), any(com.fasterxml.jackson.databind.BeanDescription.class), any(com.fasterxml.jackson.databind.JsonDeserializer.class));
        DeserializerFactoryConfig deserializerFactoryConfig1 = deserializerFactoryConfig0.withDeserializerModifier(beanDeserializerModifier0);
        BeanDeserializerFactory beanDeserializerFactory1 = new BeanDeserializerFactory(deserializerFactoryConfig1);
        beanDeserializerFactory1.createBeanDeserializer(defaultDeserializationContext_Impl0, resolvedRecursiveType0, (BeanDescription) null);
    }

    @Test(timeout = 4000)
    public void test088() throws Throwable {
        BeanDeserializerFactory beanDeserializerFactory0 = BeanDeserializerFactory.instance;
        DefaultDeserializationContext.Impl defaultDeserializationContext_Impl0 = new DefaultDeserializationContext.Impl(beanDeserializerFactory0);
        DeserializerFactoryConfig deserializerFactoryConfig0 = new DeserializerFactoryConfig();
        SimpleAbstractTypeResolver simpleAbstractTypeResolver0 = new SimpleAbstractTypeResolver();
        DeserializerFactoryConfig deserializerFactoryConfig1 = deserializerFactoryConfig0.withAbstractTypeResolver(simpleAbstractTypeResolver0);
        BeanDeserializerFactory beanDeserializerFactory1 = new BeanDeserializerFactory(deserializerFactoryConfig1);
        JavaType javaType0 = beanDeserializerFactory1.materializeAbstractType(defaultDeserializationContext_Impl0, (JavaType) null, (BeanDescription) null);
    }

    @Test(timeout = 4000)
    public void test099() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        Class<ObjectIdResolver> class0 = ObjectIdResolver.class;
        ObjectReader objectReader0 = objectMapper0.readerFor(class0);
    }

    @Test(timeout = 4000)
    public void test1010() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        MapperFeature[] mapperFeatureArray0 = new MapperFeature[4];
        MapperFeature mapperFeature0 = MapperFeature.USE_WRAPPER_NAME_AS_PROPERTY_NAME;
        mapperFeatureArray0[0] = mapperFeature0;
        mapperFeatureArray0[1] = mapperFeature0;
        MapperFeature mapperFeature1 = MapperFeature.USE_GETTERS_AS_SETTERS;
        mapperFeatureArray0[2] = mapperFeature1;
        mapperFeatureArray0[3] = mapperFeature1;
        objectMapper0.disable(mapperFeatureArray0);
        Class<ObjectIdResolver> class0 = ObjectIdResolver.class;
        ObjectReader objectReader0 = objectMapper0.readerFor(class0);
    }

    @Test(timeout = 4000)
    public void test1111() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        MapperFeature[] mapperFeatureArray0 = new MapperFeature[8];
        MapperFeature mapperFeature0 = MapperFeature.USE_ANNOTATIONS;
        mapperFeatureArray0[0] = mapperFeature0;
        MapperFeature mapperFeature1 = MapperFeature.AUTO_DETECT_GETTERS;
        mapperFeatureArray0[1] = mapperFeature1;
        mapperFeatureArray0[2] = mapperFeature0;
        mapperFeatureArray0[3] = mapperFeatureArray0[0];
        mapperFeatureArray0[4] = mapperFeatureArray0[1];
        mapperFeatureArray0[5] = mapperFeatureArray0[2];
        mapperFeatureArray0[6] = mapperFeature0;
        mapperFeatureArray0[7] = mapperFeature0;
        ObjectMapper objectMapper1 = objectMapper0.disable(mapperFeatureArray0);
        Class<BeanDeserializer> class0 = BeanDeserializer.class;
        ObjectReader objectReader0 = objectMapper1.readerFor(class0);
    }

    @Test(timeout = 4000)
    public void test1212() throws Throwable {
        BeanDeserializerFactory beanDeserializerFactory0 = BeanDeserializerFactory.instance;
        POJOPropertiesCollector pOJOPropertiesCollector0 = mock(POJOPropertiesCollector.class, new ViolatedAssumptionAnswer());
        doReturn((AnnotatedClass) null).when(pOJOPropertiesCollector0).getClassDef();
        doReturn((MapperConfig) null).when(pOJOPropertiesCollector0).getConfig();
        doReturn((Map) null).when(pOJOPropertiesCollector0).getInjectables();
        doReturn((ObjectIdInfo) null).when(pOJOPropertiesCollector0).getObjectIdInfo();
        doReturn((JavaType) null).when(pOJOPropertiesCollector0).getType();
        BasicBeanDescription basicBeanDescription0 = BasicBeanDescription.forDeserialization(pOJOPropertiesCollector0);
        DefaultDeserializationContext.Impl defaultDeserializationContext_Impl0 = new DefaultDeserializationContext.Impl(beanDeserializerFactory0);
        BeanDeserializerBuilder beanDeserializerBuilder0 = new BeanDeserializerBuilder(basicBeanDescription0, defaultDeserializationContext_Impl0);
        beanDeserializerFactory0.addInjectables(defaultDeserializationContext_Impl0, basicBeanDescription0, beanDeserializerBuilder0);
        defaultDeserializationContext_Impl0.getDeserializationFeatures();
    }

    @Test(timeout = 4000)
    public void test1313() throws Throwable {
        BeanDeserializerFactory beanDeserializerFactory0 = BeanDeserializerFactory.instance;
        LinkedHashMap<Object, AnnotatedMember> linkedHashMap0 = new LinkedHashMap<Object, AnnotatedMember>();
        POJOPropertiesCollector pOJOPropertiesCollector0 = mock(POJOPropertiesCollector.class, new ViolatedAssumptionAnswer());
        doReturn((AnnotatedClass) null).when(pOJOPropertiesCollector0).getClassDef();
        doReturn((MapperConfig) null).when(pOJOPropertiesCollector0).getConfig();
        doReturn(linkedHashMap0).when(pOJOPropertiesCollector0).getInjectables();
        doReturn((ObjectIdInfo) null).when(pOJOPropertiesCollector0).getObjectIdInfo();
        doReturn((JavaType) null).when(pOJOPropertiesCollector0).getType();
        BasicBeanDescription basicBeanDescription0 = BasicBeanDescription.forDeserialization(pOJOPropertiesCollector0);
        DefaultDeserializationContext.Impl defaultDeserializationContext_Impl0 = new DefaultDeserializationContext.Impl(beanDeserializerFactory0);
        Integer integer0 = new Integer(225);
        linkedHashMap0.put(integer0, (AnnotatedMember) null);
        BeanDeserializerBuilder beanDeserializerBuilder0 = new BeanDeserializerBuilder(basicBeanDescription0, defaultDeserializationContext_Impl0);
        beanDeserializerFactory0.addInjectables(defaultDeserializationContext_Impl0, basicBeanDescription0, beanDeserializerBuilder0);
    }

    @Test(timeout = 4000)
    public void test1414() throws Throwable {
        BeanDeserializerFactory beanDeserializerFactory0 = BeanDeserializerFactory.instance;
        DefaultDeserializationContext.Impl defaultDeserializationContext_Impl0 = new DefaultDeserializationContext.Impl(beanDeserializerFactory0);
        TypeFactory typeFactory0 = TypeFactory.defaultInstance();
        TypeBindings typeBindings0 = TypeBindings.emptyBindings();
        TypeResolutionContext.Basic typeResolutionContext_Basic0 = new TypeResolutionContext.Basic(typeFactory0, typeBindings0);
        AnnotationMap annotationMap0 = new AnnotationMap();
        AnnotatedField annotatedField0 = new AnnotatedField(typeResolutionContext_Basic0, (Field) null, annotationMap0);
        beanDeserializerFactory0.constructAnySetter(defaultDeserializationContext_Impl0, (BeanDescription) null, annotatedField0);
    }

    @Test(timeout = 4000)
    public void test1515() throws Throwable {
        BeanDeserializerFactory beanDeserializerFactory0 = BeanDeserializerFactory.instance;
        Class<Boolean> class0 = Boolean.TYPE;
        beanDeserializerFactory0.isPotentialBeanType(class0);
    }
}
