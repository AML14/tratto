/*
 * This file was automatically generated by EvoSuite
 * Thu Mar 28 07:48:33 GMT 2024
 */
package com.fasterxml.jackson.databind.deser.impl;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.SimpleObjectIdResolver;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.deser.BeanDeserializerFactory;
import com.fasterxml.jackson.databind.deser.CreatorProperty;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.impl.BeanPropertyMap;
import com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.fasterxml.jackson.databind.deser.impl.PropertyBasedCreator;
import com.fasterxml.jackson.databind.deser.impl.PropertyValueBuffer;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.fasterxml.jackson.databind.introspect.AnnotationCollector;
import com.fasterxml.jackson.databind.jsontype.impl.AsExternalTypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.impl.AsPropertyTypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.impl.ClassNameIdResolver;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.PlaceholderForType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.BufferedInputStream;
import java.io.FileDescriptor;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.io.Reader;
import java.lang.annotation.Annotation;
import java.time.chrono.ChronoLocalDate;
import java.time.format.ResolverStyle;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.mock.java.io.MockFileInputStream;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class ExternalTypeHandler_ESTest extends ExternalTypeHandler_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        ExternalTypeHandler.Builder externalTypeHandler_Builder0 = new ExternalTypeHandler.Builder((JavaType) null);
        ExternalTypeHandler externalTypeHandler0 = externalTypeHandler_Builder0.build((BeanPropertyMap) null);
        ExternalTypeHandler externalTypeHandler1 = externalTypeHandler0.start();
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        ExternalTypeHandler.Builder externalTypeHandler_Builder0 = ExternalTypeHandler.builder((JavaType) null);
        ExternalTypeHandler externalTypeHandler0 = externalTypeHandler_Builder0.build((BeanPropertyMap) null);
        boolean boolean0 = externalTypeHandler0.handleTypePropertyValue((JsonParser) null, (DeserializationContext) null, "H&", "H&");
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        ExternalTypeHandler.Builder externalTypeHandler_Builder0 = ExternalTypeHandler.builder((JavaType) null);
        PropertyName propertyName0 = PropertyName.USE_DEFAULT;
        TypeFactory typeFactory0 = TypeFactory.defaultInstance();
        ClassNameIdResolver classNameIdResolver0 = new ClassNameIdResolver((JavaType) null, typeFactory0);
        Class<HashMap> class0 = HashMap.class;
        Class<List> class1 = List.class;
        MapType mapType0 = typeFactory0.constructMapType(class0, class0, class1);
        JsonTypeInfo.As jsonTypeInfo_As0 = JsonTypeInfo.As.EXTERNAL_PROPERTY;
        AsPropertyTypeDeserializer asPropertyTypeDeserializer0 = new AsPropertyTypeDeserializer((JavaType) null, classNameIdResolver0, "S", false, mapType0, jsonTypeInfo_As0);
        Class<Annotation> class2 = Annotation.class;
        Class<ResolverStyle> class3 = ResolverStyle.class;
        AnnotationCollector.TwoAnnotations annotationCollector_TwoAnnotations0 = new AnnotationCollector.TwoAnnotations(class2, (Annotation) null, class3, (Annotation) null);
        PropertyMetadata propertyMetadata0 = PropertyMetadata.STD_REQUIRED_OR_OPTIONAL;
        CreatorProperty creatorProperty0 = new CreatorProperty(propertyName0, mapType0, propertyName0, asPropertyTypeDeserializer0, annotationCollector_TwoAnnotations0, (AnnotatedParameter) null, 853, class1, propertyMetadata0);
        externalTypeHandler_Builder0.addExternal(creatorProperty0, asPropertyTypeDeserializer0);
        HashMap<String, List<PropertyName>> hashMap0 = new HashMap<String, List<PropertyName>>();
        externalTypeHandler_Builder0.addExternal(creatorProperty0, asPropertyTypeDeserializer0);
        TreeSet<SettableBeanProperty> treeSet0 = new TreeSet<SettableBeanProperty>();
        BeanPropertyMap beanPropertyMap0 = BeanPropertyMap.construct((Collection<SettableBeanProperty>) treeSet0, false, (Map<String, List<PropertyName>>) hashMap0);
        ExternalTypeHandler externalTypeHandler0 = externalTypeHandler_Builder0.build(beanPropertyMap0);
        JsonFactory jsonFactory0 = new JsonFactory();
        PipedWriter pipedWriter0 = new PipedWriter();
        PipedReader pipedReader0 = new PipedReader(pipedWriter0);
        JsonParser jsonParser0 = jsonFactory0.createParser((Reader) pipedReader0);
        PlaceholderForType placeholderForType0 = new PlaceholderForType(3);
        boolean boolean0 = externalTypeHandler0.handleTypePropertyValue(jsonParser0, (DeserializationContext) null, "", placeholderForType0);
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        ExternalTypeHandler.Builder externalTypeHandler_Builder0 = new ExternalTypeHandler.Builder((JavaType) null);
        PropertyName propertyName0 = PropertyName.USE_DEFAULT;
        TypeFactory typeFactory0 = TypeFactory.defaultInstance();
        ClassNameIdResolver classNameIdResolver0 = new ClassNameIdResolver((JavaType) null, typeFactory0);
        Class<HashMap> class0 = HashMap.class;
        Class<List> class1 = List.class;
        MapType mapType0 = typeFactory0.constructMapType(class0, class0, class1);
        JsonTypeInfo.As jsonTypeInfo_As0 = JsonTypeInfo.As.WRAPPER_ARRAY;
        AsPropertyTypeDeserializer asPropertyTypeDeserializer0 = new AsPropertyTypeDeserializer((JavaType) null, classNameIdResolver0, "Cannot find a deserializer for non-concrete Collection type ", true, mapType0, jsonTypeInfo_As0);
        Class<Annotation> class2 = Annotation.class;
        Class<ResolverStyle> class3 = ResolverStyle.class;
        AnnotationCollector.TwoAnnotations annotationCollector_TwoAnnotations0 = new AnnotationCollector.TwoAnnotations(class2, (Annotation) null, class3, (Annotation) null);
        PropertyMetadata propertyMetadata0 = PropertyMetadata.STD_OPTIONAL;
        CreatorProperty creatorProperty0 = new CreatorProperty(propertyName0, mapType0, propertyName0, asPropertyTypeDeserializer0, annotationCollector_TwoAnnotations0, (AnnotatedParameter) null, (-33), class1, propertyMetadata0);
        externalTypeHandler_Builder0.addExternal(creatorProperty0, asPropertyTypeDeserializer0);
        externalTypeHandler_Builder0.addExternal(creatorProperty0, asPropertyTypeDeserializer0);
        HashMap<String, List<PropertyName>> hashMap0 = new HashMap<String, List<PropertyName>>();
        TreeSet<SettableBeanProperty> treeSet0 = new TreeSet<SettableBeanProperty>();
        BeanPropertyMap beanPropertyMap0 = BeanPropertyMap.construct((Collection<SettableBeanProperty>) treeSet0, true, (Map<String, List<PropertyName>>) hashMap0);
        ExternalTypeHandler externalTypeHandler0 = externalTypeHandler_Builder0.build(beanPropertyMap0);
        JsonFactory jsonFactory0 = new JsonFactory();
        JsonParser jsonParser0 = jsonFactory0.createParser("Cannot find a deserializer for non-concrete Collection type ");
        BeanDeserializerFactory beanDeserializerFactory0 = BeanDeserializerFactory.instance;
        DefaultDeserializationContext.Impl defaultDeserializationContext_Impl0 = new DefaultDeserializationContext.Impl(beanDeserializerFactory0);
        ExternalTypeHandler externalTypeHandler1 = new ExternalTypeHandler(externalTypeHandler0);
        FileDescriptor fileDescriptor0 = new FileDescriptor();
        MockFileInputStream mockFileInputStream0 = new MockFileInputStream(fileDescriptor0);
        BufferedInputStream bufferedInputStream0 = new BufferedInputStream(mockFileInputStream0);
        boolean boolean0 = externalTypeHandler1.handleTypePropertyValue(jsonParser0, defaultDeserializationContext_Impl0, "Cannot find a deserializer for non-concrete Collection type ", bufferedInputStream0);
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        ExternalTypeHandler.Builder externalTypeHandler_Builder0 = ExternalTypeHandler.builder((JavaType) null);
        BeanProperty.Bogus beanProperty_Bogus0 = new BeanProperty.Bogus();
        PropertyName propertyName0 = PropertyName.USE_DEFAULT;
        TypeFactory typeFactory0 = TypeFactory.defaultInstance();
        ClassNameIdResolver classNameIdResolver0 = new ClassNameIdResolver((JavaType) null, typeFactory0);
        Class<HashMap> class0 = HashMap.class;
        Class<List> class1 = List.class;
        MapType mapType0 = typeFactory0.constructMapType(class0, class0, class1);
        JsonTypeInfo.As jsonTypeInfo_As0 = JsonTypeInfo.As.WRAPPER_OBJECT;
        AsPropertyTypeDeserializer asPropertyTypeDeserializer0 = new AsPropertyTypeDeserializer((JavaType) null, classNameIdResolver0, "com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler$Builder", true, mapType0, jsonTypeInfo_As0);
        Class<Annotation> class2 = Annotation.class;
        Class<ResolverStyle> class3 = ResolverStyle.class;
        AnnotationCollector.TwoAnnotations annotationCollector_TwoAnnotations0 = new AnnotationCollector.TwoAnnotations(class2, (Annotation) null, class3, (Annotation) null);
        PropertyMetadata propertyMetadata0 = beanProperty_Bogus0.getMetadata();
        CreatorProperty creatorProperty0 = new CreatorProperty(propertyName0, mapType0, propertyName0, asPropertyTypeDeserializer0, annotationCollector_TwoAnnotations0, (AnnotatedParameter) null, (-2671), class1, propertyMetadata0);
        externalTypeHandler_Builder0.addExternal(creatorProperty0, asPropertyTypeDeserializer0);
        HashMap<String, List<PropertyName>> hashMap0 = new HashMap<String, List<PropertyName>>();
        TreeSet<SettableBeanProperty> treeSet0 = new TreeSet<SettableBeanProperty>();
        BeanPropertyMap beanPropertyMap0 = BeanPropertyMap.construct((Collection<SettableBeanProperty>) treeSet0, true, (Map<String, List<PropertyName>>) hashMap0);
        ExternalTypeHandler externalTypeHandler0 = externalTypeHandler_Builder0.build(beanPropertyMap0);
        JsonFactory jsonFactory0 = new JsonFactory();
        JsonParser jsonParser0 = jsonFactory0.createParser("com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler$Builder");
        BeanDeserializerFactory beanDeserializerFactory0 = BeanDeserializerFactory.instance;
        DefaultDeserializationContext.Impl defaultDeserializationContext_Impl0 = new DefaultDeserializationContext.Impl(beanDeserializerFactory0);
        externalTypeHandler0.handleTypePropertyValue(jsonParser0, defaultDeserializationContext_Impl0, "com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler$Builder", (Object) null);
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        ExternalTypeHandler.Builder externalTypeHandler_Builder0 = ExternalTypeHandler.builder((JavaType) null);
        ExternalTypeHandler externalTypeHandler0 = externalTypeHandler_Builder0.build((BeanPropertyMap) null);
        boolean boolean0 = externalTypeHandler0.handlePropertyValue((JsonParser) null, (DeserializationContext) null, "com.fasterxml.jacksondatabind.1ontype.impl.AsExternalTypeSerializer", (Object) null);
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        ExternalTypeHandler.Builder externalTypeHandler_Builder0 = ExternalTypeHandler.builder((JavaType) null);
        PropertyName propertyName0 = PropertyName.USE_DEFAULT;
        TypeFactory typeFactory0 = TypeFactory.defaultInstance();
        ClassNameIdResolver classNameIdResolver0 = new ClassNameIdResolver((JavaType) null, typeFactory0);
        Class<HashMap> class0 = HashMap.class;
        Class<List> class1 = List.class;
        MapType mapType0 = typeFactory0.constructMapType(class0, class0, class1);
        JsonTypeInfo.As jsonTypeInfo_As0 = JsonTypeInfo.As.EXTERNAL_PROPERTY;
        AsPropertyTypeDeserializer asPropertyTypeDeserializer0 = new AsPropertyTypeDeserializer((JavaType) null, classNameIdResolver0, "S", false, mapType0, jsonTypeInfo_As0);
        Class<Annotation> class2 = Annotation.class;
        Class<ResolverStyle> class3 = ResolverStyle.class;
        AnnotationCollector.TwoAnnotations annotationCollector_TwoAnnotations0 = new AnnotationCollector.TwoAnnotations(class2, (Annotation) null, class3, (Annotation) null);
        PropertyMetadata propertyMetadata0 = PropertyMetadata.STD_REQUIRED_OR_OPTIONAL;
        CreatorProperty creatorProperty0 = new CreatorProperty(propertyName0, mapType0, propertyName0, asPropertyTypeDeserializer0, annotationCollector_TwoAnnotations0, (AnnotatedParameter) null, 863, class1, propertyMetadata0);
        externalTypeHandler_Builder0.addExternal(creatorProperty0, asPropertyTypeDeserializer0);
        HashMap<String, List<PropertyName>> hashMap0 = new HashMap<String, List<PropertyName>>();
        externalTypeHandler_Builder0.addExternal(creatorProperty0, asPropertyTypeDeserializer0);
        TreeSet<SettableBeanProperty> treeSet0 = new TreeSet<SettableBeanProperty>();
        BeanPropertyMap beanPropertyMap0 = BeanPropertyMap.construct((Collection<SettableBeanProperty>) treeSet0, false, (Map<String, List<PropertyName>>) hashMap0);
        ExternalTypeHandler externalTypeHandler0 = externalTypeHandler_Builder0.build(beanPropertyMap0);
        externalTypeHandler0.handlePropertyValue((JsonParser) null, (DeserializationContext) null, "", asPropertyTypeDeserializer0);
    }

    @Test(timeout = 4000)
    public void test077() throws Throwable {
        ExternalTypeHandler.Builder externalTypeHandler_Builder0 = ExternalTypeHandler.builder((JavaType) null);
        BeanProperty.Bogus beanProperty_Bogus0 = new BeanProperty.Bogus();
        PropertyName propertyName0 = beanProperty_Bogus0.getFullName();
        TypeFactory typeFactory0 = TypeFactory.defaultInstance();
        ClassNameIdResolver classNameIdResolver0 = new ClassNameIdResolver((JavaType) null, typeFactory0);
        Class<HashMap> class0 = HashMap.class;
        Class<List> class1 = List.class;
        MapType mapType0 = typeFactory0.constructMapType(class0, class0, class1);
        JsonTypeInfo.As jsonTypeInfo_As0 = JsonTypeInfo.As.WRAPPER_ARRAY;
        AsPropertyTypeDeserializer asPropertyTypeDeserializer0 = new AsPropertyTypeDeserializer((JavaType) null, classNameIdResolver0, "", true, mapType0, jsonTypeInfo_As0);
        Class<Annotation> class2 = Annotation.class;
        Class<ResolverStyle> class3 = ResolverStyle.class;
        AnnotationCollector.TwoAnnotations annotationCollector_TwoAnnotations0 = new AnnotationCollector.TwoAnnotations(class2, (Annotation) null, class3, (Annotation) null);
        PropertyMetadata propertyMetadata0 = PropertyMetadata.STD_REQUIRED_OR_OPTIONAL;
        CreatorProperty creatorProperty0 = new CreatorProperty(propertyName0, mapType0, propertyName0, asPropertyTypeDeserializer0, annotationCollector_TwoAnnotations0, (AnnotatedParameter) null, 44, class1, propertyMetadata0);
        externalTypeHandler_Builder0.addExternal(creatorProperty0, asPropertyTypeDeserializer0);
        HashMap<String, List<PropertyName>> hashMap0 = new HashMap<String, List<PropertyName>>();
        TreeSet<SettableBeanProperty> treeSet0 = new TreeSet<SettableBeanProperty>();
        BeanPropertyMap beanPropertyMap0 = BeanPropertyMap.construct((Collection<SettableBeanProperty>) treeSet0, true, (Map<String, List<PropertyName>>) hashMap0);
        ExternalTypeHandler externalTypeHandler0 = externalTypeHandler_Builder0.build(beanPropertyMap0);
        JsonFactory jsonFactory0 = new JsonFactory();
        JsonParser jsonParser0 = jsonFactory0.createParser("");
        BeanDeserializerFactory beanDeserializerFactory0 = BeanDeserializerFactory.instance;
        DefaultDeserializationContext.Impl defaultDeserializationContext_Impl0 = new DefaultDeserializationContext.Impl(beanDeserializerFactory0);
        ExternalTypeHandler externalTypeHandler1 = new ExternalTypeHandler(externalTypeHandler0);
        boolean boolean0 = externalTypeHandler1.handlePropertyValue(jsonParser0, defaultDeserializationContext_Impl0, "", creatorProperty0);
    }

    @Test(timeout = 4000)
    public void test088() throws Throwable {
        ExternalTypeHandler.Builder externalTypeHandler_Builder0 = new ExternalTypeHandler.Builder((JavaType) null);
        PropertyName propertyName0 = PropertyName.NO_NAME;
        TypeFactory typeFactory0 = TypeFactory.defaultInstance();
        ClassNameIdResolver classNameIdResolver0 = new ClassNameIdResolver((JavaType) null, typeFactory0);
        JsonTypeInfo.As jsonTypeInfo_As0 = JsonTypeInfo.As.EXISTING_PROPERTY;
        AsPropertyTypeDeserializer asPropertyTypeDeserializer0 = new AsPropertyTypeDeserializer((JavaType) null, classNameIdResolver0, " IXH0wcL,`", false, (JavaType) null, jsonTypeInfo_As0);
        Class<Annotation> class0 = Annotation.class;
        AnnotationCollector.TwoAnnotations annotationCollector_TwoAnnotations0 = new AnnotationCollector.TwoAnnotations(class0, (Annotation) null, class0, (Annotation) null);
        PropertyMetadata propertyMetadata0 = PropertyMetadata.STD_REQUIRED;
        CreatorProperty creatorProperty0 = new CreatorProperty(propertyName0, (JavaType) null, propertyName0, asPropertyTypeDeserializer0, annotationCollector_TwoAnnotations0, (AnnotatedParameter) null, 1145, classNameIdResolver0, propertyMetadata0);
        externalTypeHandler_Builder0.addExternal(creatorProperty0, asPropertyTypeDeserializer0);
        HashMap<String, List<PropertyName>> hashMap0 = new HashMap<String, List<PropertyName>>();
        TreeSet<SettableBeanProperty> treeSet0 = new TreeSet<SettableBeanProperty>();
        BeanPropertyMap beanPropertyMap0 = BeanPropertyMap.construct((Collection<SettableBeanProperty>) treeSet0, false, (Map<String, List<PropertyName>>) hashMap0);
        BeanDeserializerFactory beanDeserializerFactory0 = BeanDeserializerFactory.instance;
        ExternalTypeHandler externalTypeHandler0 = externalTypeHandler_Builder0.build(beanPropertyMap0);
        DefaultDeserializationContext.Impl defaultDeserializationContext_Impl0 = new DefaultDeserializationContext.Impl(beanDeserializerFactory0);
        externalTypeHandler0.handlePropertyValue((JsonParser) null, defaultDeserializationContext_Impl0, "", treeSet0);
    }

    @Test(timeout = 4000)
    public void test099() throws Throwable {
        ExternalTypeHandler.Builder externalTypeHandler_Builder0 = new ExternalTypeHandler.Builder((JavaType) null);
        BeanProperty.Bogus beanProperty_Bogus0 = new BeanProperty.Bogus();
        PropertyName propertyName0 = beanProperty_Bogus0.getFullName();
        TypeFactory typeFactory0 = TypeFactory.defaultInstance();
        ClassNameIdResolver classNameIdResolver0 = new ClassNameIdResolver((JavaType) null, typeFactory0);
        Class<HashMap> class0 = HashMap.class;
        Class<List> class1 = List.class;
        MapType mapType0 = typeFactory0.constructMapType(class0, class0, class1);
        JsonTypeInfo.As jsonTypeInfo_As0 = JsonTypeInfo.As.WRAPPER_OBJECT;
        AsPropertyTypeDeserializer asPropertyTypeDeserializer0 = new AsPropertyTypeDeserializer((JavaType) null, classNameIdResolver0, "Cannot fnd a deserializer for non-conreteCollection type ", true, mapType0, jsonTypeInfo_As0);
        Class<Annotation> class2 = Annotation.class;
        Class<ResolverStyle> class3 = ResolverStyle.class;
        AnnotationCollector.TwoAnnotations annotationCollector_TwoAnnotations0 = new AnnotationCollector.TwoAnnotations(class2, (Annotation) null, class3, (Annotation) null);
        PropertyMetadata propertyMetadata0 = PropertyMetadata.STD_OPTIONAL;
        CreatorProperty creatorProperty0 = new CreatorProperty(propertyName0, mapType0, propertyName0, asPropertyTypeDeserializer0, annotationCollector_TwoAnnotations0, (AnnotatedParameter) null, 2, class1, propertyMetadata0);
        externalTypeHandler_Builder0.addExternal(creatorProperty0, asPropertyTypeDeserializer0);
        HashMap<String, List<PropertyName>> hashMap0 = new HashMap<String, List<PropertyName>>();
        TreeSet<SettableBeanProperty> treeSet0 = new TreeSet<SettableBeanProperty>();
        BeanPropertyMap beanPropertyMap0 = BeanPropertyMap.construct((Collection<SettableBeanProperty>) treeSet0, true, (Map<String, List<PropertyName>>) hashMap0);
        ExternalTypeHandler externalTypeHandler0 = externalTypeHandler_Builder0.build(beanPropertyMap0);
        JsonFactory jsonFactory0 = new JsonFactory();
        JsonParser jsonParser0 = jsonFactory0.createParser("Cannot fnd a deserializer for non-conreteCollection type ");
        BeanDeserializerFactory beanDeserializerFactory0 = BeanDeserializerFactory.instance;
        DefaultDeserializationContext.Impl defaultDeserializationContext_Impl0 = new DefaultDeserializationContext.Impl(beanDeserializerFactory0);
        ExternalTypeHandler externalTypeHandler1 = new ExternalTypeHandler(externalTypeHandler0);
        boolean boolean0 = externalTypeHandler1.handlePropertyValue(jsonParser0, defaultDeserializationContext_Impl0, "Cannot fnd a deserializer for non-conreteCollection type ", (Object) null);
    }

    @Test(timeout = 4000)
    public void test1010() throws Throwable {
        ExternalTypeHandler.Builder externalTypeHandler_Builder0 = new ExternalTypeHandler.Builder((JavaType) null);
        BeanProperty.Bogus beanProperty_Bogus0 = new BeanProperty.Bogus();
        PropertyName propertyName0 = PropertyName.USE_DEFAULT;
        TypeFactory typeFactory0 = TypeFactory.defaultInstance();
        ClassNameIdResolver classNameIdResolver0 = new ClassNameIdResolver((JavaType) null, typeFactory0);
        Class<HashMap> class0 = HashMap.class;
        Class<List> class1 = List.class;
        MapType mapType0 = typeFactory0.constructMapType(class0, class0, class1);
        JsonTypeInfo.As jsonTypeInfo_As0 = JsonTypeInfo.As.WRAPPER_OBJECT;
        AsPropertyTypeDeserializer asPropertyTypeDeserializer0 = new AsPropertyTypeDeserializer((JavaType) null, classNameIdResolver0, "via me5d. ", true, mapType0, jsonTypeInfo_As0);
        Class<Annotation> class2 = Annotation.class;
        Class<ResolverStyle> class3 = ResolverStyle.class;
        AnnotationCollector.TwoAnnotations annotationCollector_TwoAnnotations0 = new AnnotationCollector.TwoAnnotations(class2, (Annotation) null, class3, (Annotation) null);
        PropertyMetadata propertyMetadata0 = beanProperty_Bogus0.getMetadata();
        CreatorProperty creatorProperty0 = new CreatorProperty(propertyName0, mapType0, propertyName0, asPropertyTypeDeserializer0, annotationCollector_TwoAnnotations0, (AnnotatedParameter) null, (-2669), class1, propertyMetadata0);
        externalTypeHandler_Builder0.addExternal(creatorProperty0, asPropertyTypeDeserializer0);
        HashMap<String, List<PropertyName>> hashMap0 = new HashMap<String, List<PropertyName>>();
        TreeSet<SettableBeanProperty> treeSet0 = new TreeSet<SettableBeanProperty>();
        BeanPropertyMap beanPropertyMap0 = BeanPropertyMap.construct((Collection<SettableBeanProperty>) treeSet0, true, (Map<String, List<PropertyName>>) hashMap0);
        ExternalTypeHandler externalTypeHandler0 = externalTypeHandler_Builder0.build(beanPropertyMap0);
        JsonFactory jsonFactory0 = new JsonFactory();
        JsonParser jsonParser0 = jsonFactory0.createParser("via me5d. ");
        BeanDeserializerFactory beanDeserializerFactory0 = BeanDeserializerFactory.instance;
        DefaultDeserializationContext.Impl defaultDeserializationContext_Impl0 = new DefaultDeserializationContext.Impl(beanDeserializerFactory0);
        ExternalTypeHandler externalTypeHandler1 = new ExternalTypeHandler(externalTypeHandler0);
        boolean boolean0 = externalTypeHandler1.handlePropertyValue(jsonParser0, defaultDeserializationContext_Impl0, "via me5d. ", classNameIdResolver0);
    }

    @Test(timeout = 4000)
    public void test1111() throws Throwable {
        ExternalTypeHandler.Builder externalTypeHandler_Builder0 = new ExternalTypeHandler.Builder((JavaType) null);
        BeanProperty.Bogus beanProperty_Bogus0 = new BeanProperty.Bogus();
        PropertyName propertyName0 = beanProperty_Bogus0.getFullName();
        TypeFactory typeFactory0 = TypeFactory.defaultInstance();
        ClassNameIdResolver classNameIdResolver0 = new ClassNameIdResolver((JavaType) null, typeFactory0);
        JsonTypeInfo.As jsonTypeInfo_As0 = JsonTypeInfo.As.EXISTING_PROPERTY;
        AsPropertyTypeDeserializer asPropertyTypeDeserializer0 = new AsPropertyTypeDeserializer((JavaType) null, classNameIdResolver0, " gIXH0wcL", false, (JavaType) null, jsonTypeInfo_As0);
        Class<Annotation> class0 = Annotation.class;
        Class<SimpleObjectIdResolver> class1 = SimpleObjectIdResolver.class;
        AnnotationCollector.TwoAnnotations annotationCollector_TwoAnnotations0 = new AnnotationCollector.TwoAnnotations(class1, (Annotation) null, class0, (Annotation) null);
        PropertyMetadata propertyMetadata0 = PropertyMetadata.STD_REQUIRED;
        CreatorProperty creatorProperty0 = new CreatorProperty(propertyName0, (JavaType) null, propertyName0, asPropertyTypeDeserializer0, annotationCollector_TwoAnnotations0, (AnnotatedParameter) null, 0, (Object) null, propertyMetadata0);
        externalTypeHandler_Builder0.addExternal(creatorProperty0, asPropertyTypeDeserializer0);
        HashMap<String, List<PropertyName>> hashMap0 = new HashMap<String, List<PropertyName>>();
        TreeSet<SettableBeanProperty> treeSet0 = new TreeSet<SettableBeanProperty>();
        BeanPropertyMap beanPropertyMap0 = BeanPropertyMap.construct((Collection<SettableBeanProperty>) treeSet0, false, (Map<String, List<PropertyName>>) hashMap0);
        ExternalTypeHandler externalTypeHandler0 = externalTypeHandler_Builder0.build(beanPropertyMap0);
        ExternalTypeHandler externalTypeHandler1 = new ExternalTypeHandler(externalTypeHandler0);
        BeanDeserializerFactory beanDeserializerFactory0 = BeanDeserializerFactory.instance;
        DefaultDeserializationContext.Impl defaultDeserializationContext_Impl0 = new DefaultDeserializationContext.Impl(beanDeserializerFactory0);
        AnnotationCollector.TwoAnnotations annotationCollector_TwoAnnotations1 = (AnnotationCollector.TwoAnnotations) externalTypeHandler1.complete((JsonParser) null, (DeserializationContext) defaultDeserializationContext_Impl0, (Object) annotationCollector_TwoAnnotations0);
        annotationCollector_TwoAnnotations1.size();
    }

    @Test(timeout = 4000)
    public void test1212() throws Throwable {
        ExternalTypeHandler.Builder externalTypeHandler_Builder0 = new ExternalTypeHandler.Builder((JavaType) null);
        PropertyName propertyName0 = PropertyName.NO_NAME;
        TypeFactory typeFactory0 = TypeFactory.defaultInstance();
        ClassNameIdResolver classNameIdResolver0 = new ClassNameIdResolver((JavaType) null, typeFactory0);
        JsonTypeInfo.As jsonTypeInfo_As0 = JsonTypeInfo.As.EXISTING_PROPERTY;
        AsPropertyTypeDeserializer asPropertyTypeDeserializer0 = new AsPropertyTypeDeserializer((JavaType) null, classNameIdResolver0, " gIXH0wcL,`", false, (JavaType) null, jsonTypeInfo_As0);
        Class<Annotation> class0 = Annotation.class;
        AnnotationCollector.TwoAnnotations annotationCollector_TwoAnnotations0 = new AnnotationCollector.TwoAnnotations(class0, (Annotation) null, class0, (Annotation) null);
        PropertyMetadata propertyMetadata0 = PropertyMetadata.STD_REQUIRED;
        CreatorProperty creatorProperty0 = new CreatorProperty(propertyName0, (JavaType) null, propertyName0, asPropertyTypeDeserializer0, annotationCollector_TwoAnnotations0, (AnnotatedParameter) null, 1145, classNameIdResolver0, propertyMetadata0);
        externalTypeHandler_Builder0.addExternal(creatorProperty0, asPropertyTypeDeserializer0);
        HashMap<String, List<PropertyName>> hashMap0 = new HashMap<String, List<PropertyName>>();
        TreeSet<SettableBeanProperty> treeSet0 = new TreeSet<SettableBeanProperty>();
        BeanPropertyMap beanPropertyMap0 = BeanPropertyMap.construct((Collection<SettableBeanProperty>) treeSet0, false, (Map<String, List<PropertyName>>) hashMap0);
        ExternalTypeHandler externalTypeHandler0 = externalTypeHandler_Builder0.build(beanPropertyMap0);
        ExternalTypeHandler externalTypeHandler1 = new ExternalTypeHandler(externalTypeHandler0);
        BeanDeserializerFactory beanDeserializerFactory0 = BeanDeserializerFactory.instance;
        DefaultDeserializationContext.Impl defaultDeserializationContext_Impl0 = new DefaultDeserializationContext.Impl(beanDeserializerFactory0);
        PropertyValueBuffer propertyValueBuffer0 = new PropertyValueBuffer((JsonParser) null, defaultDeserializationContext_Impl0, 1145, (ObjectIdReader) null);
        externalTypeHandler1.complete((JsonParser) null, (DeserializationContext) defaultDeserializationContext_Impl0, propertyValueBuffer0, (PropertyBasedCreator) null);
    }

    @Test(timeout = 4000)
    public void test1313() throws Throwable {
        ExternalTypeHandler.Builder externalTypeHandler_Builder0 = ExternalTypeHandler.builder((JavaType) null);
        Class<ChronoLocalDate> class0 = ChronoLocalDate.class;
        AnnotationCollector.TwoAnnotations annotationCollector_TwoAnnotations0 = new AnnotationCollector.TwoAnnotations(class0, (Annotation) null, class0, (Annotation) null);
        PropertyMetadata propertyMetadata0 = PropertyMetadata.STD_OPTIONAL;
        BeanProperty.Bogus beanProperty_Bogus0 = new BeanProperty.Bogus();
        PropertyName propertyName0 = beanProperty_Bogus0.getFullName();
        TypeFactory typeFactory0 = TypeFactory.defaultInstance();
        ClassNameIdResolver classNameIdResolver0 = new ClassNameIdResolver((JavaType) null, typeFactory0);
        AsExternalTypeDeserializer asExternalTypeDeserializer0 = new AsExternalTypeDeserializer((JavaType) null, classNameIdResolver0, (String) null, false, (JavaType) null);
        CreatorProperty creatorProperty0 = new CreatorProperty(propertyName0, (JavaType) null, propertyName0, asExternalTypeDeserializer0, annotationCollector_TwoAnnotations0, (AnnotatedParameter) null, 0, annotationCollector_TwoAnnotations0, propertyMetadata0);
        externalTypeHandler_Builder0.addExternal(creatorProperty0, asExternalTypeDeserializer0);
        externalTypeHandler_Builder0.addExternal(creatorProperty0, asExternalTypeDeserializer0);
        creatorProperty0.getPropertyIndex();
    }
}
