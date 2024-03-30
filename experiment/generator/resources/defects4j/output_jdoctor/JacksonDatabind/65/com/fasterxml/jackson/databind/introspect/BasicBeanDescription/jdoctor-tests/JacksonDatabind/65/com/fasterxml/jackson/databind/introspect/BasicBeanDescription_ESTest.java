/*
 * This file was automatically generated by EvoSuite
 * Thu Mar 28 05:57:00 GMT 2024
 */
package com.fasterxml.jackson.databind.introspect;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.fasterxml.jackson.databind.introspect.BasicBeanDescription;
import com.fasterxml.jackson.databind.introspect.BasicClassIntrospector;
import com.fasterxml.jackson.databind.jsontype.impl.MinimalClassNameIdResolver;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.TypeBindings;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class BasicBeanDescription_ESTest extends BasicBeanDescription_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        BasicBeanDescription basicBeanDescription0 = BasicClassIntrospector.STRING_DESC;
        ObjectMapper objectMapper0 = new ObjectMapper();
        ObjectReader objectReader0 = objectMapper0.readerForUpdating(basicBeanDescription0);
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        BasicBeanDescription basicBeanDescription0 = BasicClassIntrospector.LONG_DESC;
        boolean boolean0 = basicBeanDescription0.hasKnownClassAnnotations();
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        BasicBeanDescription basicBeanDescription0 = BasicClassIntrospector.BOOLEAN_DESC;
        Class<RuntimeException>[] classArray0 = (Class<RuntimeException>[]) Array.newInstance(Class.class, 10);
        AnnotatedMethod annotatedMethod0 = basicBeanDescription0.findMethod("4S04j?z2", classArray0);
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        BasicBeanDescription basicBeanDescription0 = BasicClassIntrospector.LONG_DESC;
        TypeBindings typeBindings0 = basicBeanDescription0.bindingsForBeanType();
        typeBindings0.isEmpty();
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        Class<AnnotatedParameter> class0 = AnnotatedParameter.class;
        AtomicReference<Throwable> atomicReference0 = new AtomicReference<Throwable>();
        boolean boolean0 = objectMapper0.canSerialize(class0, atomicReference0);
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        BasicBeanDescription basicBeanDescription0 = BasicClassIntrospector.INT_DESC;
        boolean boolean0 = basicBeanDescription0.removeProperty("getScientificInstance");
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        BasicBeanDescription basicBeanDescription0 = BasicClassIntrospector.INT_DESC;
        PropertyName propertyName0 = PropertyName.USE_DEFAULT;
        boolean boolean0 = basicBeanDescription0.hasProperty(propertyName0);
    }

    @Test(timeout = 4000)
    public void test077() throws Throwable {
        BasicBeanDescription basicBeanDescription0 = BasicClassIntrospector.LONG_DESC;
        Set<String> set0 = basicBeanDescription0.getIgnoredPropertyNames();
        set0.isEmpty();
    }

    @Test(timeout = 4000)
    public void test088() throws Throwable {
        BasicBeanDescription basicBeanDescription0 = BasicClassIntrospector.STRING_DESC;
        Class<Long> class0 = Long.TYPE;
        basicBeanDescription0.resolveType(class0);
    }

    @Test(timeout = 4000)
    public void test099() throws Throwable {
        BasicBeanDescription basicBeanDescription0 = BasicClassIntrospector.BOOLEAN_DESC;
        JavaType javaType0 = basicBeanDescription0.resolveType((Type) null);
    }

    @Test(timeout = 4000)
    public void test1010() throws Throwable {
        BasicBeanDescription basicBeanDescription0 = BasicClassIntrospector.LONG_DESC;
        AnnotatedMethod annotatedMethod0 = basicBeanDescription0.findAnySetter();
    }

    @Test(timeout = 4000)
    public void test1111() throws Throwable {
        BasicBeanDescription basicBeanDescription0 = BasicClassIntrospector.STRING_DESC;
        Map<Object, AnnotatedMember> map0 = basicBeanDescription0.findInjectables();
        map0.isEmpty();
    }

    @Test(timeout = 4000)
    public void test1212() throws Throwable {
        BasicBeanDescription basicBeanDescription0 = BasicClassIntrospector.LONG_DESC;
        basicBeanDescription0.findExpectedFormat((JsonFormat.Value) null);
    }

    @Test(timeout = 4000)
    public void test1313() throws Throwable {
        BasicBeanDescription basicBeanDescription0 = BasicClassIntrospector.INT_DESC;
        JsonInclude.Value jsonInclude_Value0 = JsonInclude.Value.empty();
        JsonInclude.Value jsonInclude_Value1 = basicBeanDescription0.findPropertyInclusion(jsonInclude_Value0);
    }

    @Test(timeout = 4000)
    public void test1414() throws Throwable {
        BasicBeanDescription basicBeanDescription0 = BasicClassIntrospector.BOOLEAN_DESC;
        AnnotatedMember annotatedMember0 = basicBeanDescription0.findAnyGetter();
    }

    @Test(timeout = 4000)
    public void test1515() throws Throwable {
        BasicBeanDescription basicBeanDescription0 = BasicClassIntrospector.LONG_DESC;
        AnnotatedMember annotatedMember0 = basicBeanDescription0.findAnySetterField();
    }

    @Test(timeout = 4000)
    public void test1616() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        SimpleModule simpleModule0 = new SimpleModule();
        ObjectReader objectReader0 = objectMapper0.readerForUpdating(simpleModule0);
    }

    @Test(timeout = 4000)
    public void test1717() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        JsonFactory.Feature jsonFactory_Feature0 = JsonFactory.Feature.INTERN_FIELD_NAMES;
        ObjectReader objectReader0 = objectMapper0.readerForUpdating(jsonFactory_Feature0);
    }

    @Test(timeout = 4000)
    public void test1818() throws Throwable {
        BasicBeanDescription basicBeanDescription0 = BasicClassIntrospector.STRING_DESC;
        JsonPOJOBuilder.Value jsonPOJOBuilder_Value0 = basicBeanDescription0.findPOJOBuilderConfig();
    }

    @Test(timeout = 4000)
    public void test1919() throws Throwable {
        BasicBeanDescription basicBeanDescription0 = BasicClassIntrospector.INT_DESC;
        String string0 = basicBeanDescription0.findClassDescription();
    }

    @Test(timeout = 4000)
    public void test2020() throws Throwable {
        BasicBeanDescription basicBeanDescription0 = BasicClassIntrospector.LONG_DESC;
        LinkedHashSet<String> linkedHashSet0 = new LinkedHashSet<String>();
        LinkedHashMap<String, AnnotatedField> linkedHashMap0 = basicBeanDescription0._findPropertyFields(linkedHashSet0, false);
        linkedHashMap0.isEmpty();
    }

    @Test(timeout = 4000)
    public void test2121() throws Throwable {
        BasicBeanDescription basicBeanDescription0 = BasicClassIntrospector.INT_DESC;
        basicBeanDescription0._createConverter(basicBeanDescription0);
    }

    @Test(timeout = 4000)
    public void test2222() throws Throwable {
        BasicBeanDescription basicBeanDescription0 = BasicClassIntrospector.BOOLEAN_DESC;
        Class<MinimalClassNameIdResolver> class0 = MinimalClassNameIdResolver.class;
        basicBeanDescription0._createConverter(class0);
    }
}
