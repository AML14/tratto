/*
 * This file was automatically generated by EvoSuite
 * Mon Nov 20 05:18:59 GMT 2023
 */
package com.fasterxml.jackson.databind.deser.impl;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.deser.impl.SetterlessProperty;
import com.fasterxml.jackson.databind.introspect.BasicBeanDescription;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class SetterlessProperty_ESTest extends SetterlessProperty_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test00() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        Nulls nulls0 = Nulls.SKIP;
        JsonSetter.Value jsonSetter_Value0 = JsonSetter.Value.forValueNulls(nulls0);
        objectMapper0.setDefaultSetterInfo(jsonSetter_Value0);
        Class<BasicBeanDescription> class0 = BasicBeanDescription.class;
        ObjectReader objectReader0 = objectMapper0.readerFor(class0);
    }

    @Test(timeout = 4000)
    public void test11() throws Throwable {
        PropertyName propertyName0 = PropertyName.NO_NAME;
        SetterlessProperty setterlessProperty0 = null;
        setterlessProperty0 = new SetterlessProperty((SetterlessProperty) null, propertyName0);
    }

    @Test(timeout = 4000)
    public void test22() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        Boolean boolean0 = Boolean.TRUE;
        objectMapper0.setDefaultMergeable(boolean0);
        Class<BasicBeanDescription> class0 = BasicBeanDescription.class;
        ObjectReader objectReader0 = objectMapper0.readerFor(class0);
    }
}
