/*
 * This file was automatically generated by EvoSuite
 * Thu Mar 28 01:26:12 GMT 2024
 */
package com.fasterxml.jackson.databind.deser.impl;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import com.fasterxml.jackson.annotation.SimpleObjectIdResolver;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.deser.BeanDeserializerFactory;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdValueProperty;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.node.MissingNode;
import java.lang.annotation.Annotation;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class ObjectIdValueProperty_ESTest extends ObjectIdValueProperty_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test00() throws Throwable {
        JsonDeserializer<String> jsonDeserializer0 = (JsonDeserializer<String>) mock(JsonDeserializer.class, new ViolatedAssumptionAnswer());
        PropertyMetadata propertyMetadata0 = PropertyMetadata.STD_OPTIONAL;
        PropertyName propertyName0 = PropertyName.construct("", "");
        ObjectIdGenerator<Object> objectIdGenerator0 = (ObjectIdGenerator<Object>) mock(ObjectIdGenerator.class, new ViolatedAssumptionAnswer());
        SimpleObjectIdResolver simpleObjectIdResolver0 = new SimpleObjectIdResolver();
        ObjectIdReader objectIdReader0 = ObjectIdReader.construct((JavaType) null, propertyName0, (ObjectIdGenerator<?>) objectIdGenerator0, (JsonDeserializer<?>) jsonDeserializer0, (SettableBeanProperty) null, (ObjectIdResolver) simpleObjectIdResolver0);
        ObjectIdValueProperty objectIdValueProperty0 = new ObjectIdValueProperty(objectIdReader0, propertyMetadata0);
        AnnotatedMember annotatedMember0 = objectIdValueProperty0.getMember();
    }

    @Test(timeout = 4000)
    public void test11() throws Throwable {
        PropertyMetadata propertyMetadata0 = PropertyMetadata.STD_OPTIONAL;
        PropertyName propertyName0 = PropertyName.USE_DEFAULT;
        SimpleObjectIdResolver simpleObjectIdResolver0 = new SimpleObjectIdResolver();
        ObjectIdGenerator<MissingNode> objectIdGenerator0 = (ObjectIdGenerator<MissingNode>) mock(ObjectIdGenerator.class, new ViolatedAssumptionAnswer());
        JsonDeserializer<ObjectIdResolver> jsonDeserializer0 = (JsonDeserializer<ObjectIdResolver>) mock(JsonDeserializer.class, new ViolatedAssumptionAnswer());
        ObjectIdReader objectIdReader0 = new ObjectIdReader((JavaType) null, propertyName0, objectIdGenerator0, jsonDeserializer0, (SettableBeanProperty) null, simpleObjectIdResolver0);
        ObjectIdValueProperty objectIdValueProperty0 = new ObjectIdValueProperty(objectIdReader0, propertyMetadata0);
        SettableBeanProperty settableBeanProperty0 = objectIdValueProperty0.withSimpleName("7hn");
        settableBeanProperty0.hasValueDeserializer();
    }

    @Test(timeout = 4000)
    public void test22() throws Throwable {
        PropertyName propertyName0 = PropertyName.construct("H6FY?B}nFINSF", "J");
        SimpleObjectIdResolver simpleObjectIdResolver0 = new SimpleObjectIdResolver();
        ObjectIdReader objectIdReader0 = ObjectIdReader.construct((JavaType) null, propertyName0, (ObjectIdGenerator<?>) null, (JsonDeserializer<?>) null, (SettableBeanProperty) null, (ObjectIdResolver) simpleObjectIdResolver0);
        PropertyMetadata propertyMetadata0 = PropertyMetadata.STD_REQUIRED_OR_OPTIONAL;
        ObjectIdValueProperty objectIdValueProperty0 = new ObjectIdValueProperty(objectIdReader0, propertyMetadata0);
        objectIdValueProperty0.set((Object) null, "J");
    }

    @Test(timeout = 4000)
    public void test33() throws Throwable {
        PropertyMetadata propertyMetadata0 = PropertyMetadata.STD_OPTIONAL;
        PropertyName propertyName0 = PropertyName.USE_DEFAULT;
        SimpleObjectIdResolver simpleObjectIdResolver0 = new SimpleObjectIdResolver();
        ObjectIdGenerator<MissingNode> objectIdGenerator0 = (ObjectIdGenerator<MissingNode>) mock(ObjectIdGenerator.class, new ViolatedAssumptionAnswer());
        JsonDeserializer<ObjectIdResolver> jsonDeserializer0 = (JsonDeserializer<ObjectIdResolver>) mock(JsonDeserializer.class, new ViolatedAssumptionAnswer());
        ObjectIdReader objectIdReader0 = ObjectIdReader.construct((JavaType) null, propertyName0, (ObjectIdGenerator<?>) objectIdGenerator0, (JsonDeserializer<?>) jsonDeserializer0, (SettableBeanProperty) null, (ObjectIdResolver) simpleObjectIdResolver0);
        ObjectIdValueProperty objectIdValueProperty0 = new ObjectIdValueProperty(objectIdReader0, propertyMetadata0);
        Class<Annotation> class0 = Annotation.class;
        Annotation annotation0 = objectIdValueProperty0.getAnnotation(class0);
    }

    @Test(timeout = 4000)
    public void test44() throws Throwable {
        PropertyMetadata propertyMetadata0 = PropertyMetadata.STD_OPTIONAL;
        PropertyName propertyName0 = PropertyName.construct("J", "J");
        SimpleObjectIdResolver simpleObjectIdResolver0 = new SimpleObjectIdResolver();
        ObjectIdReader objectIdReader0 = ObjectIdReader.construct((JavaType) null, propertyName0, (ObjectIdGenerator<?>) null, (JsonDeserializer<?>) null, (SettableBeanProperty) null, (ObjectIdResolver) simpleObjectIdResolver0);
        ObjectIdValueProperty objectIdValueProperty0 = new ObjectIdValueProperty(objectIdReader0, propertyMetadata0);
        ObjectIdValueProperty objectIdValueProperty1 = objectIdValueProperty0.withValueDeserializer((JsonDeserializer<?>) null);
        objectIdValueProperty1.hasValueDeserializer();
    }

    @Test(timeout = 4000)
    public void test55() throws Throwable {
        PropertyMetadata propertyMetadata0 = PropertyMetadata.STD_OPTIONAL;
        PropertyName propertyName0 = PropertyName.USE_DEFAULT;
        SimpleObjectIdResolver simpleObjectIdResolver0 = new SimpleObjectIdResolver();
        ObjectIdGenerator.IdKey objectIdGenerator_IdKey0 = mock(ObjectIdGenerator.IdKey.class, new ViolatedAssumptionAnswer());
        ObjectIdGenerator<MissingNode> objectIdGenerator0 = (ObjectIdGenerator<MissingNode>) mock(ObjectIdGenerator.class, new ViolatedAssumptionAnswer());
        doReturn(objectIdGenerator_IdKey0).when(objectIdGenerator0).key(any());
        JsonDeserializer<ObjectIdResolver> jsonDeserializer0 = (JsonDeserializer<ObjectIdResolver>) mock(JsonDeserializer.class, new ViolatedAssumptionAnswer());
        doReturn(simpleObjectIdResolver0).when(jsonDeserializer0).deserialize(any(com.fasterxml.jackson.core.JsonParser.class), any(com.fasterxml.jackson.databind.DeserializationContext.class));
        ObjectIdReader objectIdReader0 = new ObjectIdReader((JavaType) null, propertyName0, objectIdGenerator0, jsonDeserializer0, (SettableBeanProperty) null, simpleObjectIdResolver0);
        ObjectIdValueProperty objectIdValueProperty0 = new ObjectIdValueProperty(objectIdReader0, propertyMetadata0);
        BeanDeserializerFactory beanDeserializerFactory0 = BeanDeserializerFactory.instance;
        DefaultDeserializationContext.Impl defaultDeserializationContext_Impl0 = new DefaultDeserializationContext.Impl(beanDeserializerFactory0);
        objectIdValueProperty0.deserializeAndSet((JsonParser) null, defaultDeserializationContext_Impl0, objectIdValueProperty0);
        objectIdValueProperty0.hasValueDeserializer();
    }

    @Test(timeout = 4000)
    public void test66() throws Throwable {
        PropertyMetadata propertyMetadata0 = PropertyMetadata.STD_OPTIONAL;
        PropertyName propertyName0 = PropertyName.USE_DEFAULT;
        SimpleObjectIdResolver simpleObjectIdResolver0 = new SimpleObjectIdResolver();
        ObjectIdGenerator<Object> objectIdGenerator0 = (ObjectIdGenerator<Object>) mock(ObjectIdGenerator.class, new ViolatedAssumptionAnswer());
        JsonDeserializer<ObjectIdResolver> jsonDeserializer0 = (JsonDeserializer<ObjectIdResolver>) mock(JsonDeserializer.class, new ViolatedAssumptionAnswer());
        doReturn((Object) null).when(jsonDeserializer0).deserialize(any(com.fasterxml.jackson.core.JsonParser.class), any(com.fasterxml.jackson.databind.DeserializationContext.class));
        ObjectIdReader objectIdReader0 = ObjectIdReader.construct((JavaType) null, propertyName0, (ObjectIdGenerator<?>) objectIdGenerator0, (JsonDeserializer<?>) jsonDeserializer0, (SettableBeanProperty) null, (ObjectIdResolver) simpleObjectIdResolver0);
        ObjectIdValueProperty objectIdValueProperty0 = new ObjectIdValueProperty(objectIdReader0, propertyMetadata0);
        BeanDeserializerFactory beanDeserializerFactory0 = BeanDeserializerFactory.instance;
        DefaultDeserializationContext.Impl defaultDeserializationContext_Impl0 = new DefaultDeserializationContext.Impl(beanDeserializerFactory0);
        objectIdValueProperty0.deserializeAndSet((JsonParser) null, defaultDeserializationContext_Impl0, (Object) null);
        objectIdValueProperty0.isRequired();
    }

    @Test(timeout = 4000)
    public void test77() throws Throwable {
        PropertyMetadata propertyMetadata0 = PropertyMetadata.STD_OPTIONAL;
        PropertyName propertyName0 = PropertyName.USE_DEFAULT;
        SimpleObjectIdResolver simpleObjectIdResolver0 = new SimpleObjectIdResolver();
        ObjectIdGenerator.IdKey objectIdGenerator_IdKey0 = mock(ObjectIdGenerator.IdKey.class, new ViolatedAssumptionAnswer());
        ObjectIdGenerator<MissingNode> objectIdGenerator0 = (ObjectIdGenerator<MissingNode>) mock(ObjectIdGenerator.class, new ViolatedAssumptionAnswer());
        doReturn(objectIdGenerator_IdKey0).when(objectIdGenerator0).key(any());
        JsonDeserializer<ObjectIdResolver> jsonDeserializer0 = (JsonDeserializer<ObjectIdResolver>) mock(JsonDeserializer.class, new ViolatedAssumptionAnswer());
        doReturn(simpleObjectIdResolver0).when(jsonDeserializer0).deserialize(any(com.fasterxml.jackson.core.JsonParser.class), any(com.fasterxml.jackson.databind.DeserializationContext.class));
        ObjectIdReader objectIdReader0 = new ObjectIdReader((JavaType) null, propertyName0, objectIdGenerator0, jsonDeserializer0, (SettableBeanProperty) null, simpleObjectIdResolver0);
        ObjectIdValueProperty objectIdValueProperty0 = new ObjectIdValueProperty(objectIdReader0, propertyMetadata0);
        ObjectIdReader objectIdReader1 = ObjectIdReader.construct((JavaType) null, propertyName0, (ObjectIdGenerator<?>) objectIdGenerator0, (JsonDeserializer<?>) jsonDeserializer0, (SettableBeanProperty) objectIdValueProperty0, objectIdReader0.resolver);
        BeanDeserializerFactory beanDeserializerFactory0 = BeanDeserializerFactory.instance;
        DefaultDeserializationContext.Impl defaultDeserializationContext_Impl0 = new DefaultDeserializationContext.Impl(beanDeserializerFactory0);
        ObjectIdValueProperty objectIdValueProperty1 = new ObjectIdValueProperty(objectIdReader1, propertyMetadata0);
        objectIdValueProperty1.deserializeAndSet((JsonParser) null, defaultDeserializationContext_Impl0, objectIdValueProperty0);
    }

    @Test(timeout = 4000)
    public void test88() throws Throwable {
        JsonDeserializer<String> jsonDeserializer0 = (JsonDeserializer<String>) mock(JsonDeserializer.class, new ViolatedAssumptionAnswer());
        PropertyMetadata propertyMetadata0 = PropertyMetadata.STD_OPTIONAL;
        PropertyName propertyName0 = PropertyName.construct("", "");
        ObjectIdGenerator<Object> objectIdGenerator0 = (ObjectIdGenerator<Object>) mock(ObjectIdGenerator.class, new ViolatedAssumptionAnswer());
        SimpleObjectIdResolver simpleObjectIdResolver0 = new SimpleObjectIdResolver();
        ObjectIdReader objectIdReader0 = ObjectIdReader.construct((JavaType) null, propertyName0, (ObjectIdGenerator<?>) objectIdGenerator0, (JsonDeserializer<?>) jsonDeserializer0, (SettableBeanProperty) null, (ObjectIdResolver) simpleObjectIdResolver0);
        ObjectIdValueProperty objectIdValueProperty0 = new ObjectIdValueProperty(objectIdReader0, propertyMetadata0);
        ObjectMapper objectMapper0 = new ObjectMapper();
        JsonFactory jsonFactory0 = new JsonFactory(objectMapper0);
        JsonDeserializer<MissingNode> jsonDeserializer1 = (JsonDeserializer<MissingNode>) mock(JsonDeserializer.class, new ViolatedAssumptionAnswer());
        ObjectIdReader objectIdReader1 = ObjectIdReader.construct((JavaType) null, propertyName0, objectIdReader0.generator, (JsonDeserializer<?>) jsonDeserializer1, (SettableBeanProperty) objectIdValueProperty0, (ObjectIdResolver) simpleObjectIdResolver0);
        ObjectIdValueProperty objectIdValueProperty1 = new ObjectIdValueProperty(objectIdReader1, propertyMetadata0);
        ObjectIdGenerators.IntSequenceGenerator objectIdGenerators_IntSequenceGenerator0 = new ObjectIdGenerators.IntSequenceGenerator((Class<?>) null, (-1743));
        objectIdValueProperty1.setAndReturn(objectIdGenerators_IntSequenceGenerator0, jsonFactory0);
    }
}
