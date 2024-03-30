/*
 * This file was automatically generated by EvoSuite
 * Thu Mar 28 00:44:17 GMT 2024
 */
package com.fasterxml.jackson.databind.jsontype.impl;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.deser.CreatorProperty;
import com.fasterxml.jackson.databind.deser.std.JsonLocationInstantiator;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.fasterxml.jackson.databind.introspect.AnnotationMap;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.impl.AsWrapperTypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.impl.ClassNameIdResolver;
import com.fasterxml.jackson.databind.jsontype.impl.MinimalClassNameIdResolver;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.util.Map;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class AsWrapperTypeDeserializer_ESTest extends AsWrapperTypeDeserializer_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test00() throws Throwable {
        TypeFactory typeFactory0 = TypeFactory.defaultInstance();
        Class<Map> class0 = Map.class;
        MapType mapType0 = typeFactory0.constructMapType(class0, class0, class0);
        ClassNameIdResolver classNameIdResolver0 = new ClassNameIdResolver(mapType0, typeFactory0);
        AsWrapperTypeDeserializer asWrapperTypeDeserializer0 = new AsWrapperTypeDeserializer(mapType0, classNameIdResolver0, "+", true, class0);
        JsonFactory jsonFactory0 = new JsonFactory();
        JsonParser jsonParser0 = jsonFactory0.createParser("+");
        asWrapperTypeDeserializer0.deserializeTypedFromObject(jsonParser0, (DeserializationContext) null);
    }

    @Test(timeout = 4000)
    public void test11() throws Throwable {
        TypeFactory typeFactory0 = TypeFactory.defaultInstance();
        Class<Map> class0 = Map.class;
        MapType mapType0 = typeFactory0.constructMapType(class0, class0, class0);
        ClassNameIdResolver classNameIdResolver0 = new ClassNameIdResolver(mapType0, typeFactory0);
        AsWrapperTypeDeserializer asWrapperTypeDeserializer0 = new AsWrapperTypeDeserializer(mapType0, classNameIdResolver0, "", false, class0);
        asWrapperTypeDeserializer0.deserializeTypedFromScalar((JsonParser) null, (DeserializationContext) null);
    }

    @Test(timeout = 4000)
    public void test22() throws Throwable {
        TypeFactory typeFactory0 = TypeFactory.defaultInstance();
        Class<Map> class0 = Map.class;
        MapType mapType0 = typeFactory0.constructRawMapType(class0);
        Class<JsonGenerator.Feature> class1 = JsonGenerator.Feature.class;
        MinimalClassNameIdResolver minimalClassNameIdResolver0 = new MinimalClassNameIdResolver(mapType0, typeFactory0);
        AsWrapperTypeDeserializer asWrapperTypeDeserializer0 = new AsWrapperTypeDeserializer(mapType0, minimalClassNameIdResolver0, "Invalid delegate-creator definition for ", false, class1);
        JsonTypeInfo.As jsonTypeInfo_As0 = asWrapperTypeDeserializer0.getTypeInclusion();
    }

    @Test(timeout = 4000)
    public void test33() throws Throwable {
        TypeFactory typeFactory0 = TypeFactory.defaultInstance();
        Class<Map> class0 = Map.class;
        MapType mapType0 = typeFactory0.constructMapType(class0, class0, class0);
        ClassNameIdResolver classNameIdResolver0 = new ClassNameIdResolver(mapType0, typeFactory0);
        AsWrapperTypeDeserializer asWrapperTypeDeserializer0 = new AsWrapperTypeDeserializer(mapType0, classNameIdResolver0, "", false, class0);
        asWrapperTypeDeserializer0.deserializeTypedFromArray((JsonParser) null, (DeserializationContext) null);
    }

    @Test(timeout = 4000)
    public void test44() throws Throwable {
        TypeFactory typeFactory0 = TypeFactory.defaultInstance();
        Class<Map> class0 = Map.class;
        MapType mapType0 = typeFactory0.constructMapType(class0, class0, class0);
        ClassNameIdResolver classNameIdResolver0 = new ClassNameIdResolver(mapType0, typeFactory0);
        AsWrapperTypeDeserializer asWrapperTypeDeserializer0 = new AsWrapperTypeDeserializer(mapType0, classNameIdResolver0, "]", false, class0);
        asWrapperTypeDeserializer0.deserializeTypedFromAny((JsonParser) null, (DeserializationContext) null);
    }

    @Test(timeout = 4000)
    public void test55() throws Throwable {
        TypeFactory typeFactory0 = TypeFactory.defaultInstance();
        Class<Map> class0 = Map.class;
        MapType mapType0 = typeFactory0.constructMapType(class0, class0, class0);
        ClassNameIdResolver classNameIdResolver0 = new ClassNameIdResolver(mapType0, typeFactory0);
        AsWrapperTypeDeserializer asWrapperTypeDeserializer0 = new AsWrapperTypeDeserializer(mapType0, classNameIdResolver0, "i(Q5~0", true, class0);
        AnnotationMap annotationMap0 = new AnnotationMap();
        JsonLocationInstantiator jsonLocationInstantiator0 = new JsonLocationInstantiator();
        PropertyMetadata propertyMetadata0 = PropertyMetadata.STD_REQUIRED;
        CreatorProperty creatorProperty0 = new CreatorProperty((PropertyName) null, mapType0, (PropertyName) null, asWrapperTypeDeserializer0, annotationMap0, (AnnotatedParameter) null, (-1), jsonLocationInstantiator0, propertyMetadata0);
        creatorProperty0.hasValueDeserializer();
        assertFalse(creatorProperty0.hasValueDeserializer());
    }

    @Test(timeout = 4000)
    public void test66() throws Throwable {
        TypeFactory typeFactory0 = TypeFactory.defaultInstance();
        Class<Map> class0 = Map.class;
        MapType mapType0 = typeFactory0.constructMapType(class0, class0, class0);
        ClassNameIdResolver classNameIdResolver0 = new ClassNameIdResolver(mapType0, typeFactory0);
        AsWrapperTypeDeserializer asWrapperTypeDeserializer0 = new AsWrapperTypeDeserializer(mapType0, classNameIdResolver0, "u45zAjIXRYGJ", false, class0);
        TypeDeserializer typeDeserializer0 = asWrapperTypeDeserializer0.forProperty((BeanProperty) null);
    }
}
