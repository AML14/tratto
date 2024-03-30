/*
 * This file was automatically generated by EvoSuite
 * Thu Mar 28 07:29:14 GMT 2024
 */
package com.fasterxml.jackson.databind.deser;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.cfg.BaseSettings;
import com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.deser.BeanDeserializerBuilder;
import com.fasterxml.jackson.databind.deser.BeanDeserializerFactory;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.deser.BuilderBasedDeserializer;
import com.fasterxml.jackson.databind.deser.CreatorProperty;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.deser.DeserializerFactory;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.BasicBeanDescription;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.introspect.POJOPropertiesCollector;
import com.fasterxml.jackson.databind.introspect.SimpleMixInResolver;
import com.fasterxml.jackson.databind.jsontype.impl.StdSubtypeResolver;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.RootNameLookup;
import java.util.List;
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
        DeserializerFactoryConfig deserializerFactoryConfig0 = new DeserializerFactoryConfig();
        BeanDeserializerFactory beanDeserializerFactory0 = new BeanDeserializerFactory(deserializerFactoryConfig0);
        DeserializerFactory deserializerFactory0 = beanDeserializerFactory0.withConfig(deserializerFactoryConfig0);
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        JavaType javaType0 = TypeFactory.unknownType();
        BeanDeserializerFactory beanDeserializerFactory0 = BeanDeserializerFactory.instance;
        Class<String> class0 = String.class;
        beanDeserializerFactory0.instance.createBuilderBasedDeserializer((DeserializationContext) null, javaType0, (BeanDescription) null, class0);
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        JsonFactory jsonFactory0 = new JsonFactory();
        ObjectMapper objectMapper0 = new ObjectMapper(jsonFactory0);
        Version version0 = Version.unknownVersion();
        SimpleModule simpleModule0 = new SimpleModule("JSON", version0);
        Class<AnnotatedField> class0 = AnnotatedField.class;
        JsonDeserializer<AnnotatedField> jsonDeserializer0 = (JsonDeserializer<AnnotatedField>) mock(JsonDeserializer.class, new ViolatedAssumptionAnswer());
        doReturn(false).when(jsonDeserializer0).isCachable();
        doReturn((String) null).when(jsonDeserializer0).toString();
        simpleModule0.addDeserializer(class0, (JsonDeserializer<? extends AnnotatedField>) jsonDeserializer0);
        objectMapper0.registerModule(simpleModule0);
        ObjectReader objectReader0 = objectMapper0.readerFor(class0);
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        JsonFactory jsonFactory0 = new JsonFactory();
        ObjectMapper objectMapper0 = new ObjectMapper(jsonFactory0);
        Class<Throwable> class0 = Throwable.class;
        ObjectReader objectReader0 = objectMapper0.readerFor(class0);
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        PropertyAccessor propertyAccessor0 = PropertyAccessor.FIELD;
        JsonAutoDetect.Visibility jsonAutoDetect_Visibility0 = JsonAutoDetect.Visibility.PROTECTED_AND_PUBLIC;
        objectMapper0.setVisibility(propertyAccessor0, jsonAutoDetect_Visibility0);
        Class<BuilderBasedDeserializer> class0 = BuilderBasedDeserializer.class;
        ObjectReader objectReader0 = objectMapper0.readerFor(class0);
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        BeanDeserializerFactory beanDeserializerFactory0 = BeanDeserializerFactory.instance;
        ObjectMapper objectMapper0 = new ObjectMapper();
        DeserializationFeature deserializationFeature0 = DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES;
        DeserializationFeature[] deserializationFeatureArray0 = new DeserializationFeature[3];
        deserializationFeatureArray0[0] = deserializationFeature0;
        deserializationFeatureArray0[1] = deserializationFeature0;
        deserializationFeatureArray0[2] = deserializationFeature0;
        ObjectMapper objectMapper1 = objectMapper0.disable(deserializationFeature0, deserializationFeatureArray0);
        PropertyNamingStrategy.KebabCaseStrategy propertyNamingStrategy_KebabCaseStrategy0 = (PropertyNamingStrategy.KebabCaseStrategy) PropertyNamingStrategy.KEBAB_CASE;
        ObjectMapper objectMapper2 = objectMapper1.setPropertyNamingStrategy(propertyNamingStrategy_KebabCaseStrategy0);
        ObjectMapper.DefaultTyping objectMapper_DefaultTyping0 = ObjectMapper.DefaultTyping.NON_CONCRETE_AND_ARRAYS;
        ObjectMapper objectMapper3 = objectMapper2.enableDefaultTypingAsProperty(objectMapper_DefaultTyping0, "L");
        Version version0 = Version.unknownVersion();
        SimpleModule simpleModule0 = new SimpleModule(version0);
        BeanDeserializerModifier beanDeserializerModifier0 = mock(BeanDeserializerModifier.class, new ViolatedAssumptionAnswer());
        doReturn((JsonDeserializer) null).when(beanDeserializerModifier0).modifyDeserializer(any(com.fasterxml.jackson.databind.DeserializationConfig.class), any(com.fasterxml.jackson.databind.BeanDescription.class), any(com.fasterxml.jackson.databind.JsonDeserializer.class));
        doReturn((BeanDeserializerBuilder) null).when(beanDeserializerModifier0).updateBuilder(any(com.fasterxml.jackson.databind.DeserializationConfig.class), any(com.fasterxml.jackson.databind.BeanDescription.class), any(com.fasterxml.jackson.databind.deser.BeanDeserializerBuilder.class));
        doReturn((List) null).when(beanDeserializerModifier0).updateProperties(any(com.fasterxml.jackson.databind.DeserializationConfig.class), any(com.fasterxml.jackson.databind.BeanDescription.class), anyList());
        SimpleModule simpleModule1 = simpleModule0.setDeserializerModifier(beanDeserializerModifier0);
        objectMapper3.registerModule(simpleModule1);
        Class<Object> class0 = Object.class;
        objectMapper0.readerFor(class0);
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        Class<CreatorProperty> class0 = CreatorProperty.class;
        JsonFactory jsonFactory0 = new JsonFactory();
        ObjectMapper objectMapper0 = new ObjectMapper(jsonFactory0);
        MapperFeature mapperFeature0 = MapperFeature.AUTO_DETECT_GETTERS;
        objectMapper0.configure(mapperFeature0, false);
        ObjectReader objectReader0 = objectMapper0.readerFor(class0);
    }

    @Test(timeout = 4000)
    public void test077() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        MapperFeature[] mapperFeatureArray0 = new MapperFeature[4];
        MapperFeature mapperFeature0 = MapperFeature.IGNORE_DUPLICATE_MODULE_REGISTRATIONS;
        mapperFeatureArray0[0] = mapperFeature0;
        mapperFeatureArray0[1] = mapperFeature0;
        MapperFeature mapperFeature1 = MapperFeature.DEFAULT_VIEW_INCLUSION;
        mapperFeatureArray0[2] = mapperFeature1;
        mapperFeatureArray0[3] = mapperFeature0;
        objectMapper0.disable(mapperFeatureArray0);
        Class<CreatorProperty> class0 = CreatorProperty.class;
        ObjectReader objectReader0 = objectMapper0.readerFor(class0);
    }

    @Test(timeout = 4000)
    public void test088() throws Throwable {
        BeanDeserializerFactory beanDeserializerFactory0 = BeanDeserializerFactory.instance;
        POJOPropertiesCollector pOJOPropertiesCollector0 = mock(POJOPropertiesCollector.class, new ViolatedAssumptionAnswer());
        doReturn((AnnotatedClass) null).when(pOJOPropertiesCollector0).getClassDef();
        doReturn((MapperConfig) null).when(pOJOPropertiesCollector0).getConfig();
        doReturn((Map) null).when(pOJOPropertiesCollector0).getInjectables();
        doReturn((ObjectIdInfo) null).when(pOJOPropertiesCollector0).getObjectIdInfo();
        doReturn((JavaType) null).when(pOJOPropertiesCollector0).getType();
        BasicBeanDescription basicBeanDescription0 = BasicBeanDescription.forSerialization(pOJOPropertiesCollector0);
        beanDeserializerFactory0.addInjectables((DeserializationContext) null, basicBeanDescription0, (BeanDeserializerBuilder) null);
        basicBeanDescription0.findClassDescription();
        assertNotNull(basicBeanDescription0.findClassDescription());
    }

    @Test(timeout = 4000)
    public void test099() throws Throwable {
        BeanDeserializerFactory beanDeserializerFactory0 = BeanDeserializerFactory.instance;
        DefaultDeserializationContext.Impl defaultDeserializationContext_Impl0 = new DefaultDeserializationContext.Impl(beanDeserializerFactory0);
        StdSubtypeResolver stdSubtypeResolver0 = new StdSubtypeResolver();
        RootNameLookup rootNameLookup0 = new RootNameLookup();
        DeserializationConfig deserializationConfig0 = new DeserializationConfig((BaseSettings) null, stdSubtypeResolver0, (SimpleMixInResolver) null, rootNameLookup0);
        JsonFactory jsonFactory0 = new JsonFactory();
        byte[] byteArray0 = new byte[21];
        JsonParser jsonParser0 = jsonFactory0.createParser(byteArray0, (int) (byte) 0, (int) (byte) 0);
        InjectableValues.Std injectableValues_Std0 = new InjectableValues.Std();
        DefaultDeserializationContext.Impl defaultDeserializationContext_Impl1 = new DefaultDeserializationContext.Impl(defaultDeserializationContext_Impl0, deserializationConfig0, jsonParser0, injectableValues_Std0);
        beanDeserializerFactory0.constructAnySetter(defaultDeserializationContext_Impl1, (BeanDescription) null, (AnnotatedMethod) null);
    }

    @Test(timeout = 4000)
    public void test1010() throws Throwable {
        JsonFactory jsonFactory0 = new JsonFactory();
        ObjectMapper objectMapper0 = new ObjectMapper(jsonFactory0);
        MapperFeature[] mapperFeatureArray0 = new MapperFeature[2];
        MapperFeature mapperFeature0 = MapperFeature.CAN_OVERRIDE_ACCESS_MODIFIERS;
        mapperFeatureArray0[0] = mapperFeature0;
        mapperFeatureArray0[1] = mapperFeature0;
        ObjectMapper objectMapper1 = objectMapper0.disable(mapperFeatureArray0);
        Class<Throwable> class0 = Throwable.class;
        ObjectReader objectReader0 = objectMapper1.readerFor(class0);
    }

    @Test(timeout = 4000)
    public void test1111() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        objectMapper0.enableDefaultTyping();
        Class<CreatorProperty> class0 = CreatorProperty.class;
        ObjectReader objectReader0 = objectMapper0.readerFor(class0);
    }

    @Test(timeout = 4000)
    public void test1212() throws Throwable {
        BeanDeserializerFactory beanDeserializerFactory0 = BeanDeserializerFactory.instance;
        Class<Integer> class0 = Integer.TYPE;
        beanDeserializerFactory0.isPotentialBeanType(class0);
    }

    @Test(timeout = 4000)
    public void test1313() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        Class<CreatorProperty> class0 = CreatorProperty.class;
        ObjectReader objectReader0 = objectMapper0.readerFor(class0);
    }
}
