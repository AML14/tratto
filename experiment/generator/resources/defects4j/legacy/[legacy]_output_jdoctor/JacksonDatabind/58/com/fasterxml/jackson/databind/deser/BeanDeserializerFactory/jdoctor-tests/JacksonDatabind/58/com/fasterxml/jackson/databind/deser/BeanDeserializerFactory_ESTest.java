/*
 * This file was automatically generated by EvoSuite
 * Sat Nov 04 20:23:18 GMT 2023
 */
package com.fasterxml.jackson.databind.deser;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.deser.BeanDeserializerBuilder;
import com.fasterxml.jackson.databind.deser.BeanDeserializerFactory;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.deser.CreatorProperty;
import com.fasterxml.jackson.databind.deser.std.StdKeyDeserializers;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.BasicBeanDescription;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.introspect.POJOPropertiesCollector;
import com.fasterxml.jackson.databind.module.SimpleValueInstantiators;
import java.util.Locale;
import java.util.Map;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class BeanDeserializerFactory_ESTest extends BeanDeserializerFactory_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test20() throws Throwable {
        BeanDeserializerFactory beanDeserializerFactory0 = BeanDeserializerFactory.instance;
        DeserializerFactoryConfig deserializerFactoryConfig0 = new DeserializerFactoryConfig();
        DeserializationContext deserializationContext0 = null;
        BeanDescription beanDescription0 = null;
        beanDeserializerFactory0.constructBeanDeserializerBuilder((DeserializationContext) null, (BeanDescription) null);
    }

    @Test(timeout = 4000)
    public void test41() throws Throwable {
        BeanDeserializerFactory beanDeserializerFactory0 = BeanDeserializerFactory.instance;
        DeserializerFactoryConfig deserializerFactoryConfig0 = new DeserializerFactoryConfig();
        Class<Locale.Category> class0 = Locale.Category.class;
        beanDeserializerFactory0.isPotentialBeanType(class0);
    }

    @Test(timeout = 4000)
    public void test52() throws Throwable {
        BeanDeserializerFactory beanDeserializerFactory0 = BeanDeserializerFactory.instance;
        DeserializationContext deserializationContext0 = null;
        BeanDescription beanDescription0 = null;
        BeanDeserializerBuilder beanDeserializerBuilder0 = null;
        DeserializerFactoryConfig deserializerFactoryConfig0 = new DeserializerFactoryConfig();
        SimpleValueInstantiators simpleValueInstantiators0 = new SimpleValueInstantiators();
        deserializerFactoryConfig0.withValueInstantiators(simpleValueInstantiators0);
        StdKeyDeserializers stdKeyDeserializers0 = new StdKeyDeserializers();
        DeserializerFactoryConfig deserializerFactoryConfig1 = new DeserializerFactoryConfig();
        BeanDeserializerModifier beanDeserializerModifier0 = mock(BeanDeserializerModifier.class, new ViolatedAssumptionAnswer());
        DeserializerFactoryConfig deserializerFactoryConfig2 = deserializerFactoryConfig1.withDeserializerModifier(beanDeserializerModifier0);
        BeanDeserializerFactory beanDeserializerFactory1 = new BeanDeserializerFactory(deserializerFactoryConfig2);
        POJOPropertiesCollector pOJOPropertiesCollector0 = mock(POJOPropertiesCollector.class, new ViolatedAssumptionAnswer());
        doReturn((AnnotatedClass) null).when(pOJOPropertiesCollector0).getClassDef();
        doReturn((MapperConfig) null).when(pOJOPropertiesCollector0).getConfig();
        doReturn((Map) null).when(pOJOPropertiesCollector0).getInjectables();
        doReturn((ObjectIdInfo) null).when(pOJOPropertiesCollector0).getObjectIdInfo();
        doReturn((JavaType) null).when(pOJOPropertiesCollector0).getType();
        BasicBeanDescription basicBeanDescription0 = BasicBeanDescription.forSerialization(pOJOPropertiesCollector0);
        beanDeserializerFactory1.addInjectables((DeserializationContext) null, basicBeanDescription0, (BeanDeserializerBuilder) null);
    }
}
