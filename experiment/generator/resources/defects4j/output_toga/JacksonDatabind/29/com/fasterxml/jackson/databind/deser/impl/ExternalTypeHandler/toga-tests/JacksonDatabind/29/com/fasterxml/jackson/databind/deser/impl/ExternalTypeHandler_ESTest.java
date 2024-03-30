/*
 * This file was automatically generated by EvoSuite
 * Thu Mar 28 00:29:38 GMT 2024
 */
package com.fasterxml.jackson.databind.deser.impl;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.deser.BeanDeserializerFactory;
import com.fasterxml.jackson.databind.deser.CreatorProperty;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.impl.CreatorCollector;
import com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler;
import com.fasterxml.jackson.databind.deser.impl.PropertyBasedCreator;
import com.fasterxml.jackson.databind.deser.impl.PropertyValueBuffer;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.fasterxml.jackson.databind.introspect.AnnotationMap;
import com.fasterxml.jackson.databind.jsontype.impl.AsArrayTypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.impl.ClassNameIdResolver;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.fasterxml.jackson.databind.type.TypeBindings;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.PipedReader;
import java.io.Reader;
import java.time.chrono.ChronoLocalDate;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class ExternalTypeHandler_ESTest extends ExternalTypeHandler_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        ExternalTypeHandler.Builder externalTypeHandler_Builder0 = new ExternalTypeHandler.Builder();
        PropertyName propertyName0 = new PropertyName("");
        SimpleType simpleType0 = (SimpleType) TypeBindings.UNBOUND;
        TypeFactory typeFactory0 = TypeFactory.defaultInstance();
        ClassNameIdResolver classNameIdResolver0 = new ClassNameIdResolver(simpleType0, typeFactory0);
        Class<Module> class0 = Module.class;
        AsArrayTypeDeserializer asArrayTypeDeserializer0 = new AsArrayTypeDeserializer(simpleType0, classNameIdResolver0, "", true, class0);
        AnnotationMap annotationMap0 = new AnnotationMap();
        PropertyMetadata propertyMetadata0 = PropertyMetadata.STD_REQUIRED_OR_OPTIONAL;
        CreatorProperty creatorProperty0 = new CreatorProperty(propertyName0, simpleType0, propertyName0, asArrayTypeDeserializer0, annotationMap0, (AnnotatedParameter) null, (-283), "", propertyMetadata0);
        externalTypeHandler_Builder0.addExternal(creatorProperty0, asArrayTypeDeserializer0);
        ExternalTypeHandler externalTypeHandler0 = externalTypeHandler_Builder0.build();
        ExternalTypeHandler externalTypeHandler1 = new ExternalTypeHandler(externalTypeHandler0);
        CreatorCollector.Vanilla creatorCollector_Vanilla0 = new CreatorCollector.Vanilla(123);
        SettableBeanProperty[] settableBeanPropertyArray0 = new SettableBeanProperty[3];
        settableBeanPropertyArray0[0] = (SettableBeanProperty) creatorProperty0;
        settableBeanPropertyArray0[1] = (SettableBeanProperty) creatorProperty0;
        settableBeanPropertyArray0[2] = (SettableBeanProperty) creatorProperty0;
        PropertyBasedCreator propertyBasedCreator0 = new PropertyBasedCreator(creatorCollector_Vanilla0, settableBeanPropertyArray0);
        externalTypeHandler1.complete((JsonParser) null, (DeserializationContext) null, (PropertyValueBuffer) null, propertyBasedCreator0);
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        ExternalTypeHandler.Builder externalTypeHandler_Builder0 = new ExternalTypeHandler.Builder();
        ExternalTypeHandler externalTypeHandler0 = externalTypeHandler_Builder0.build();
        ExternalTypeHandler externalTypeHandler1 = externalTypeHandler0.start();
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        ExternalTypeHandler.Builder externalTypeHandler_Builder0 = new ExternalTypeHandler.Builder();
        ExternalTypeHandler externalTypeHandler0 = externalTypeHandler_Builder0.build();
        externalTypeHandler0._deserialize((JsonParser) null, (DeserializationContext) null, (-108), "X5%");
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        ExternalTypeHandler.Builder externalTypeHandler_Builder0 = new ExternalTypeHandler.Builder();
        ExternalTypeHandler externalTypeHandler0 = externalTypeHandler_Builder0.build();
        externalTypeHandler0._deserializeAndSet((JsonParser) null, (DeserializationContext) null, externalTypeHandler_Builder0, 1579, "");
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        ExternalTypeHandler.Builder externalTypeHandler_Builder0 = new ExternalTypeHandler.Builder();
        ExternalTypeHandler externalTypeHandler0 = externalTypeHandler_Builder0.build();
        boolean boolean0 = externalTypeHandler0.handleTypePropertyValue((JsonParser) null, (DeserializationContext) null, "~+E", (Object) null);
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        ExternalTypeHandler.Builder externalTypeHandler_Builder0 = new ExternalTypeHandler.Builder();
        PropertyName propertyName0 = new PropertyName("com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler$ExtTypedProperty", "com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler$ExtTypedProperty");
        SimpleType simpleType0 = (SimpleType) TypeBindings.UNBOUND;
        TypeFactory typeFactory0 = TypeFactory.defaultInstance();
        ClassNameIdResolver classNameIdResolver0 = new ClassNameIdResolver(simpleType0, typeFactory0);
        Class<ChronoLocalDate> class0 = ChronoLocalDate.class;
        AsArrayTypeDeserializer asArrayTypeDeserializer0 = new AsArrayTypeDeserializer(simpleType0, classNameIdResolver0, "gC_p", true, class0);
        AnnotationMap annotationMap0 = new AnnotationMap();
        PropertyMetadata propertyMetadata0 = PropertyMetadata.STD_REQUIRED;
        CreatorProperty creatorProperty0 = new CreatorProperty(propertyName0, simpleType0, propertyName0, asArrayTypeDeserializer0, annotationMap0, (AnnotatedParameter) null, 7, externalTypeHandler_Builder0, propertyMetadata0);
        externalTypeHandler_Builder0.addExternal(creatorProperty0, asArrayTypeDeserializer0);
        ExternalTypeHandler externalTypeHandler0 = externalTypeHandler_Builder0.build();
        boolean boolean0 = externalTypeHandler0.handleTypePropertyValue((JsonParser) null, (DeserializationContext) null, "com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler$ExtTypedProperty", propertyMetadata0);
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        ExternalTypeHandler.Builder externalTypeHandler_Builder0 = new ExternalTypeHandler.Builder();
        PropertyName propertyName0 = PropertyName.construct("co.aster1ml.jacks{n.databind.deser.impl.ExternalTypeHavdler$ExtyedProperty");
        SimpleType simpleType0 = (SimpleType) TypeBindings.UNBOUND;
        TypeFactory typeFactory0 = TypeFactory.defaultInstance();
        ClassNameIdResolver classNameIdResolver0 = new ClassNameIdResolver(simpleType0, typeFactory0);
        Class<ChronoLocalDate> class0 = ChronoLocalDate.class;
        AsArrayTypeDeserializer asArrayTypeDeserializer0 = new AsArrayTypeDeserializer(simpleType0, classNameIdResolver0, "co.aster1ml.jacks{n.databind.deser.impl.ExternalTypeHavdler$ExtyedProperty", false, class0);
        AnnotationMap annotationMap0 = new AnnotationMap();
        PropertyMetadata propertyMetadata0 = PropertyMetadata.STD_OPTIONAL;
        CreatorProperty creatorProperty0 = new CreatorProperty(propertyName0, simpleType0, propertyName0, asArrayTypeDeserializer0, annotationMap0, (AnnotatedParameter) null, (-12), typeFactory0, propertyMetadata0);
        externalTypeHandler_Builder0.addExternal(creatorProperty0, asArrayTypeDeserializer0);
        ExternalTypeHandler externalTypeHandler0 = externalTypeHandler_Builder0.build();
        JsonFactory jsonFactory0 = new JsonFactory();
        PipedReader pipedReader0 = new PipedReader();
        JsonParser jsonParser0 = jsonFactory0.createParser((Reader) pipedReader0);
        externalTypeHandler0.handleTypePropertyValue(jsonParser0, (DeserializationContext) null, "co.aster1ml.jacks{n.databind.deser.impl.ExternalTypeHavdler$ExtyedProperty", (Object) null);
    }

    @Test(timeout = 4000)
    public void test077() throws Throwable {
        ExternalTypeHandler.Builder externalTypeHandler_Builder0 = new ExternalTypeHandler.Builder();
        PropertyName propertyName0 = new PropertyName("com.fasterxml.jackson.databind.deser.impl.ExternalTypeHavdler$ExtypedProperty");
        SimpleType simpleType0 = (SimpleType) TypeBindings.UNBOUND;
        TypeFactory typeFactory0 = TypeFactory.defaultInstance();
        ClassNameIdResolver classNameIdResolver0 = new ClassNameIdResolver(simpleType0, typeFactory0);
        Class<ChronoLocalDate> class0 = ChronoLocalDate.class;
        AsArrayTypeDeserializer asArrayTypeDeserializer0 = new AsArrayTypeDeserializer(simpleType0, classNameIdResolver0, "com.fasterxml.jackson.databind.deser.impl.ExternalTypeHavdler$ExtypedProperty", false, class0);
        AnnotationMap annotationMap0 = new AnnotationMap();
        PropertyMetadata propertyMetadata0 = PropertyMetadata.STD_OPTIONAL;
        CreatorProperty creatorProperty0 = new CreatorProperty(propertyName0, simpleType0, propertyName0, asArrayTypeDeserializer0, annotationMap0, (AnnotatedParameter) null, 17, typeFactory0, propertyMetadata0);
        externalTypeHandler_Builder0.addExternal(creatorProperty0, asArrayTypeDeserializer0);
        ExternalTypeHandler externalTypeHandler0 = externalTypeHandler_Builder0.build();
        ExternalTypeHandler externalTypeHandler1 = new ExternalTypeHandler(externalTypeHandler0);
        JsonFactory jsonFactory0 = new JsonFactory();
        PipedReader pipedReader0 = new PipedReader();
        JsonParser jsonParser0 = jsonFactory0.createParser((Reader) pipedReader0);
        BeanDeserializerFactory beanDeserializerFactory0 = BeanDeserializerFactory.instance;
        DefaultDeserializationContext.Impl defaultDeserializationContext_Impl0 = new DefaultDeserializationContext.Impl(beanDeserializerFactory0);
        boolean boolean0 = externalTypeHandler1.handleTypePropertyValue(jsonParser0, defaultDeserializationContext_Impl0, "com.fasterxml.jackson.databind.deser.impl.ExternalTypeHavdler$ExtypedProperty", typeFactory0);
    }

    @Test(timeout = 4000)
    public void test088() throws Throwable {
        ExternalTypeHandler.Builder externalTypeHandler_Builder0 = new ExternalTypeHandler.Builder();
        ExternalTypeHandler externalTypeHandler0 = externalTypeHandler_Builder0.build();
        boolean boolean0 = externalTypeHandler0.handlePropertyValue((JsonParser) null, (DeserializationContext) null, "com.fastKrxml.jackson.databind.ser.std.N8mberSerializer`$LongSerializer", externalTypeHandler_Builder0);
    }

    @Test(timeout = 4000)
    public void test099() throws Throwable {
        ExternalTypeHandler.Builder externalTypeHandler_Builder0 = new ExternalTypeHandler.Builder();
        PropertyName propertyName0 = PropertyName.construct("com.fasterxm.jackson.databinddser.std.dkDeserikWizers");
        SimpleType simpleType0 = (SimpleType) TypeBindings.UNBOUND;
        TypeFactory typeFactory0 = TypeFactory.defaultInstance();
        ClassNameIdResolver classNameIdResolver0 = new ClassNameIdResolver(simpleType0, typeFactory0);
        Class<ChronoLocalDate> class0 = ChronoLocalDate.class;
        AsArrayTypeDeserializer asArrayTypeDeserializer0 = new AsArrayTypeDeserializer(simpleType0, classNameIdResolver0, (String) null, true, class0);
        AnnotationMap annotationMap0 = new AnnotationMap();
        PropertyMetadata propertyMetadata0 = PropertyMetadata.STD_REQUIRED;
        CreatorProperty creatorProperty0 = new CreatorProperty(propertyName0, simpleType0, propertyName0, asArrayTypeDeserializer0, annotationMap0, (AnnotatedParameter) null, (-18), class0, propertyMetadata0);
        externalTypeHandler_Builder0.addExternal(creatorProperty0, asArrayTypeDeserializer0);
        ExternalTypeHandler externalTypeHandler0 = externalTypeHandler_Builder0.build();
        externalTypeHandler0.handlePropertyValue((JsonParser) null, (DeserializationContext) null, "com.fasterxm.jackson.databinddser.std.dkDeserikWizers", "com.fasterxm.jackson.databinddser.std.dkDeserikWizers");
    }

    @Test(timeout = 4000)
    public void test1010() throws Throwable {
        ExternalTypeHandler.Builder externalTypeHandler_Builder0 = new ExternalTypeHandler.Builder();
        PropertyName propertyName0 = PropertyName.NO_NAME;
        SimpleType simpleType0 = (SimpleType) TypeBindings.UNBOUND;
        TypeFactory typeFactory0 = TypeFactory.defaultInstance();
        ClassNameIdResolver classNameIdResolver0 = new ClassNameIdResolver(simpleType0, typeFactory0);
        Class<ChronoLocalDate> class0 = ChronoLocalDate.class;
        AsArrayTypeDeserializer asArrayTypeDeserializer0 = new AsArrayTypeDeserializer(simpleType0, classNameIdResolver0, "ZTpMX^%%$v", true, class0);
        AnnotationMap annotationMap0 = new AnnotationMap();
        PropertyMetadata propertyMetadata0 = PropertyMetadata.STD_OPTIONAL;
        CreatorProperty creatorProperty0 = new CreatorProperty(propertyName0, simpleType0, propertyName0, asArrayTypeDeserializer0, annotationMap0, (AnnotatedParameter) null, (-2912), classNameIdResolver0, propertyMetadata0);
        externalTypeHandler_Builder0.addExternal(creatorProperty0, asArrayTypeDeserializer0);
        ExternalTypeHandler externalTypeHandler0 = externalTypeHandler_Builder0.build();
        ExternalTypeHandler externalTypeHandler1 = new ExternalTypeHandler(externalTypeHandler0);
        JsonFactory jsonFactory0 = new JsonFactory();
        PipedReader pipedReader0 = new PipedReader();
        JsonParser jsonParser0 = jsonFactory0.createParser((Reader) pipedReader0);
        boolean boolean0 = externalTypeHandler1.handlePropertyValue(jsonParser0, (DeserializationContext) null, "ZTpMX^%%$v", (Object) null);
    }

    @Test(timeout = 4000)
    public void test1111() throws Throwable {
        ExternalTypeHandler.Builder externalTypeHandler_Builder0 = new ExternalTypeHandler.Builder();
        PropertyName propertyName0 = new PropertyName("com.fasterxml.jackson.databind.deser.impl.ExternalTypeHavdler$ExtypedProperty");
        SimpleType simpleType0 = (SimpleType) TypeBindings.UNBOUND;
        TypeFactory typeFactory0 = TypeFactory.defaultInstance();
        ClassNameIdResolver classNameIdResolver0 = new ClassNameIdResolver(simpleType0, typeFactory0);
        Class<ChronoLocalDate> class0 = ChronoLocalDate.class;
        AsArrayTypeDeserializer asArrayTypeDeserializer0 = new AsArrayTypeDeserializer(simpleType0, classNameIdResolver0, "com.fasterxml.jackson.databind.deser.impl.ExternalTypeHavdler$ExtypedProperty", false, class0);
        AnnotationMap annotationMap0 = new AnnotationMap();
        PropertyMetadata propertyMetadata0 = PropertyMetadata.STD_OPTIONAL;
        CreatorProperty creatorProperty0 = new CreatorProperty(propertyName0, simpleType0, propertyName0, asArrayTypeDeserializer0, annotationMap0, (AnnotatedParameter) null, (-2883), classNameIdResolver0, propertyMetadata0);
        externalTypeHandler_Builder0.addExternal(creatorProperty0, asArrayTypeDeserializer0);
        ExternalTypeHandler externalTypeHandler0 = externalTypeHandler_Builder0.build();
        ExternalTypeHandler externalTypeHandler1 = new ExternalTypeHandler(externalTypeHandler0);
        JsonFactory jsonFactory0 = new JsonFactory();
        PipedReader pipedReader0 = new PipedReader();
        JsonParser jsonParser0 = jsonFactory0.createParser((Reader) pipedReader0);
        boolean boolean0 = externalTypeHandler1.handlePropertyValue(jsonParser0, (DeserializationContext) null, "com.fasterxml.jackson.databind.deser.impl.ExternalTypeHavdler$ExtypedProperty", jsonParser0);
    }

    @Test(timeout = 4000)
    public void test1212() throws Throwable {
        ExternalTypeHandler.Builder externalTypeHandler_Builder0 = new ExternalTypeHandler.Builder();
        PropertyName propertyName0 = PropertyName.USE_DEFAULT;
        SimpleType simpleType0 = (SimpleType) TypeBindings.UNBOUND;
        TypeFactory typeFactory0 = TypeFactory.defaultInstance();
        ClassNameIdResolver classNameIdResolver0 = new ClassNameIdResolver(simpleType0, typeFactory0);
        Class<ChronoLocalDate> class0 = ChronoLocalDate.class;
        AsArrayTypeDeserializer asArrayTypeDeserializer0 = new AsArrayTypeDeserializer(simpleType0, classNameIdResolver0, "com.fasterxm.jackson.databinddeser.std.dkDeserializers", true, class0);
        AnnotationMap annotationMap0 = new AnnotationMap();
        PropertyMetadata propertyMetadata0 = PropertyMetadata.STD_OPTIONAL;
        CreatorProperty creatorProperty0 = new CreatorProperty(propertyName0, simpleType0, propertyName0, asArrayTypeDeserializer0, annotationMap0, (AnnotatedParameter) null, 1763, typeFactory0, propertyMetadata0);
        externalTypeHandler_Builder0.addExternal(creatorProperty0, asArrayTypeDeserializer0);
        ExternalTypeHandler externalTypeHandler0 = externalTypeHandler_Builder0.build();
        ExternalTypeHandler externalTypeHandler1 = new ExternalTypeHandler(externalTypeHandler0);
        AnnotationMap annotationMap1 = (AnnotationMap) externalTypeHandler1.complete((JsonParser) null, (DeserializationContext) null, (Object) annotationMap0);
        annotationMap1.size();
        assertEquals(0, annotationMap1.size());
    }

    @Test(timeout = 4000)
    public void test1313() throws Throwable {
        ExternalTypeHandler.Builder externalTypeHandler_Builder0 = new ExternalTypeHandler.Builder();
        PropertyName propertyName0 = new PropertyName("com.kasterxml.jacksondatabid.deser.impl.ExternalTypeHandler$ExtTJpePopTy");
        SimpleType simpleType0 = (SimpleType) TypeBindings.UNBOUND;
        TypeFactory typeFactory0 = TypeFactory.defaultInstance();
        ClassNameIdResolver classNameIdResolver0 = new ClassNameIdResolver(simpleType0, typeFactory0);
        Class<ChronoLocalDate> class0 = ChronoLocalDate.class;
        AsArrayTypeDeserializer asArrayTypeDeserializer0 = new AsArrayTypeDeserializer(simpleType0, classNameIdResolver0, "com.kasterxml.jacksondatabid.deser.impl.ExternalTypeHandler$ExtTJpePopTy", false, class0);
        AnnotationMap annotationMap0 = new AnnotationMap();
        PropertyMetadata propertyMetadata0 = PropertyMetadata.STD_REQUIRED;
        CreatorProperty creatorProperty0 = new CreatorProperty(propertyName0, simpleType0, propertyName0, asArrayTypeDeserializer0, annotationMap0, (AnnotatedParameter) null, 2, classNameIdResolver0, propertyMetadata0);
        externalTypeHandler_Builder0.addExternal(creatorProperty0, asArrayTypeDeserializer0);
        ExternalTypeHandler externalTypeHandler0 = externalTypeHandler_Builder0.build();
        ExternalTypeHandler externalTypeHandler1 = new ExternalTypeHandler(externalTypeHandler0);
        CreatorCollector.Vanilla creatorCollector_Vanilla0 = new CreatorCollector.Vanilla(2);
        SettableBeanProperty[] settableBeanPropertyArray0 = new SettableBeanProperty[0];
        PropertyBasedCreator propertyBasedCreator0 = PropertyBasedCreator.construct((DeserializationContext) null, creatorCollector_Vanilla0, settableBeanPropertyArray0);
        externalTypeHandler1.complete((JsonParser) null, (DeserializationContext) null, (PropertyValueBuffer) null, propertyBasedCreator0);
    }
}
