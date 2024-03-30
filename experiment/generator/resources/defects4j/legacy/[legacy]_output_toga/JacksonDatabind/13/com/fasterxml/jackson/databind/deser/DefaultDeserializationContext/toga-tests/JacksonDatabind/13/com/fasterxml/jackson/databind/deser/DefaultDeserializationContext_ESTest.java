/*
 * This file was automatically generated by EvoSuite
 * Sat Nov 04 16:57:52 GMT 2023
 */
package com.fasterxml.jackson.databind.deser;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import com.fasterxml.jackson.annotation.SimpleObjectIdResolver;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig;
import com.fasterxml.jackson.databind.deser.BeanDeserializerFactory;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdKeyDeserializer;
import com.fasterxml.jackson.databind.ext.CoreXMLDeserializers;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.SimpleType;
import java.time.Month;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class DefaultDeserializationContext_ESTest extends DefaultDeserializationContext_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        BeanDeserializerFactory beanDeserializerFactory0 = BeanDeserializerFactory.instance;
        DefaultDeserializationContext.Impl defaultDeserializationContext_Impl0 = new DefaultDeserializationContext.Impl(beanDeserializerFactory0);
        JsonFactory jsonFactory0 = new JsonFactory();
        DefaultSerializerProvider.Impl defaultSerializerProvider_Impl0 = new DefaultSerializerProvider.Impl();
        ObjectMapper objectMapper0 = new ObjectMapper(jsonFactory0, defaultSerializerProvider_Impl0, defaultDeserializationContext_Impl0);
        Class<CollectionType> class0 = CollectionType.class;
        SimpleType simpleType0 = SimpleType.constructUnsafe(class0);
        ObjectReader objectReader0 = objectMapper0.reader((JavaType) simpleType0);
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        BeanDeserializerFactory beanDeserializerFactory0 = BeanDeserializerFactory.instance;
        DefaultDeserializationContext.Impl defaultDeserializationContext_Impl0 = new DefaultDeserializationContext.Impl(beanDeserializerFactory0);
        DefaultDeserializationContext defaultDeserializationContext0 = defaultDeserializationContext_Impl0.with(beanDeserializerFactory0);
        defaultDeserializationContext0.equals((Object) defaultDeserializationContext_Impl0);
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        BeanDeserializerFactory beanDeserializerFactory0 = BeanDeserializerFactory.instance;
        DefaultDeserializationContext.Impl defaultDeserializationContext_Impl0 = new DefaultDeserializationContext.Impl(beanDeserializerFactory0);
        defaultDeserializationContext_Impl0.findObjectId((Object) beanDeserializerFactory0, (ObjectIdGenerator<?>) null);
    }

    @Test(timeout = 4000)
    public void test053() throws Throwable {
        BeanDeserializerFactory beanDeserializerFactory0 = BeanDeserializerFactory.instance;
        DefaultDeserializationContext.Impl defaultDeserializationContext_Impl0 = new DefaultDeserializationContext.Impl(beanDeserializerFactory0);
        Class<Integer> class0 = Integer.class;
        CoreXMLDeserializers.Std coreXMLDeserializers_Std0 = new CoreXMLDeserializers.Std(class0, 8);
        JsonDeserializer<Object> jsonDeserializer0 = defaultDeserializationContext_Impl0.deserializerInstance((Annotated) null, coreXMLDeserializers_Std0);
    }

    @Test(timeout = 4000)
    public void test064() throws Throwable {
        BeanDeserializerFactory beanDeserializerFactory0 = BeanDeserializerFactory.instance;
        DefaultDeserializationContext.Impl defaultDeserializationContext_Impl0 = new DefaultDeserializationContext.Impl(beanDeserializerFactory0);
        JsonDeserializer<Object> jsonDeserializer0 = defaultDeserializationContext_Impl0.deserializerInstance((Annotated) null, (Object) null);
    }

    @Test(timeout = 4000)
    public void test075() throws Throwable {
        DeserializerFactoryConfig deserializerFactoryConfig0 = new DeserializerFactoryConfig();
        BeanDeserializerFactory beanDeserializerFactory0 = new BeanDeserializerFactory(deserializerFactoryConfig0);
        DefaultDeserializationContext.Impl defaultDeserializationContext_Impl0 = new DefaultDeserializationContext.Impl(beanDeserializerFactory0);
        defaultDeserializationContext_Impl0.deserializerInstance((Annotated) null, beanDeserializerFactory0);
    }

    @Test(timeout = 4000)
    public void test086() throws Throwable {
        DeserializerFactoryConfig deserializerFactoryConfig0 = new DeserializerFactoryConfig();
        BeanDeserializerFactory beanDeserializerFactory0 = new BeanDeserializerFactory(deserializerFactoryConfig0);
        DefaultDeserializationContext.Impl defaultDeserializationContext_Impl0 = new DefaultDeserializationContext.Impl(beanDeserializerFactory0);
        Class<Month> class0 = Month.class;
        defaultDeserializationContext_Impl0.deserializerInstance((Annotated) null, class0);
    }

    @Test(timeout = 4000)
    public void test097() throws Throwable {
        BeanDeserializerFactory beanDeserializerFactory0 = BeanDeserializerFactory.instance;
        DefaultDeserializationContext.Impl defaultDeserializationContext_Impl0 = new DefaultDeserializationContext.Impl(beanDeserializerFactory0);
        defaultDeserializationContext_Impl0.keyDeserializerInstance((Annotated) null, beanDeserializerFactory0);
    }

    @Test(timeout = 4000)
    public void test108() throws Throwable {
        BeanDeserializerFactory beanDeserializerFactory0 = BeanDeserializerFactory.instance;
        DefaultDeserializationContext.Impl defaultDeserializationContext_Impl0 = new DefaultDeserializationContext.Impl(beanDeserializerFactory0);
        KeyDeserializer keyDeserializer0 = defaultDeserializationContext_Impl0.keyDeserializerInstance((Annotated) null, (Object) null);
    }

    @Test(timeout = 4000)
    public void test119() throws Throwable {
        BeanDeserializerFactory beanDeserializerFactory0 = BeanDeserializerFactory.instance;
        DefaultDeserializationContext.Impl defaultDeserializationContext_Impl0 = new DefaultDeserializationContext.Impl(beanDeserializerFactory0);
        DefaultDeserializationContext defaultDeserializationContext0 = defaultDeserializationContext_Impl0.copy();
        defaultDeserializationContext0.equals((Object) defaultDeserializationContext_Impl0);
    }
}
