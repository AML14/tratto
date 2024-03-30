/*
 * This file was automatically generated by EvoSuite
 * Sun Nov 05 18:10:56 GMT 2023
 */
package com.fasterxml.jackson.dataformat.xml.ser;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.ctc.wstx.api.WriterConfig;
import com.ctc.wstx.sw.AsciiXmlWriter;
import com.ctc.wstx.sw.ISOLatin1XmlWriter;
import com.ctc.wstx.sw.SimpleNsStreamWriter;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.util.BufferRecycler;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.cfg.BaseSettings;
import com.fasterxml.jackson.databind.cfg.ConfigOverrides;
import com.fasterxml.jackson.databind.introspect.ClassIntrospector;
import com.fasterxml.jackson.databind.introspect.SimpleMixInResolver;
import com.fasterxml.jackson.databind.jsontype.impl.StdSubtypeResolver;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.ser.BeanSerializerFactory;
import com.fasterxml.jackson.databind.util.RootNameLookup;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.fasterxml.jackson.dataformat.xml.ser.XmlSerializerProvider;
import com.fasterxml.jackson.dataformat.xml.util.XmlRootNameLookup;
import java.io.IOException;
import java.io.PipedOutputStream;
import javax.xml.namespace.QName;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class XmlSerializerProvider_ESTest extends XmlSerializerProvider_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        XmlRootNameLookup xmlRootNameLookup0 = new XmlRootNameLookup();
        XmlSerializerProvider xmlSerializerProvider0 = new XmlSerializerProvider(xmlRootNameLookup0);
        QName qName0 = new QName("");
        xmlSerializerProvider0._startRootArray((ToXmlGenerator) null, qName0);
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        XmlRootNameLookup xmlRootNameLookup0 = new XmlRootNameLookup();
        XmlSerializerProvider xmlSerializerProvider0 = new XmlSerializerProvider(xmlRootNameLookup0);
        BeanSerializerFactory beanSerializerFactory0 = BeanSerializerFactory.instance;
        xmlSerializerProvider0.createInstance((SerializationConfig) null, beanSerializerFactory0);
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        XmlRootNameLookup xmlRootNameLookup0 = new XmlRootNameLookup();
        XmlSerializerProvider xmlSerializerProvider0 = new XmlSerializerProvider(xmlRootNameLookup0);
        xmlSerializerProvider0.serializeValue((JsonGenerator) null, (Object) null);
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        XmlRootNameLookup xmlRootNameLookup0 = new XmlRootNameLookup();
        JsonFactory jsonFactory0 = new JsonFactory();
        JsonParser jsonParser0 = jsonFactory0.createParser("JSON");
        TokenBuffer tokenBuffer0 = new TokenBuffer(jsonParser0);
        XmlSerializerProvider xmlSerializerProvider0 = new XmlSerializerProvider(xmlRootNameLookup0);
        xmlSerializerProvider0.serializeValue((JsonGenerator) tokenBuffer0, (Object) tokenBuffer0);
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        XmlRootNameLookup xmlRootNameLookup0 = new XmlRootNameLookup();
        PipedOutputStream pipedOutputStream0 = new PipedOutputStream();
        WriterConfig writerConfig0 = WriterConfig.createFullDefaults();
        AsciiXmlWriter asciiXmlWriter0 = new AsciiXmlWriter(pipedOutputStream0, writerConfig0, true);
        SimpleNsStreamWriter simpleNsStreamWriter0 = new SimpleNsStreamWriter(asciiXmlWriter0, (String) null, writerConfig0);
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, xmlRootNameLookup0, true);
        ObjectMapper objectMapper0 = new ObjectMapper();
        ToXmlGenerator toXmlGenerator0 = new ToXmlGenerator(iOContext0, 1, 2, objectMapper0, simpleNsStreamWriter0);
        XmlSerializerProvider xmlSerializerProvider0 = new XmlSerializerProvider(xmlRootNameLookup0);
        StdSubtypeResolver stdSubtypeResolver0 = new StdSubtypeResolver();
        RootNameLookup rootNameLookup0 = new RootNameLookup();
        ConfigOverrides configOverrides0 = new ConfigOverrides();
        SerializationConfig serializationConfig0 = new SerializationConfig((BaseSettings) null, stdSubtypeResolver0, (SimpleMixInResolver) null, rootNameLookup0, configOverrides0);
        BeanSerializerFactory beanSerializerFactory0 = BeanSerializerFactory.instance;
        PropertyName propertyName0 = new PropertyName((String) null, (String) null);
        SerializationConfig serializationConfig1 = serializationConfig0.withRootName(propertyName0);
        XmlSerializerProvider xmlSerializerProvider1 = new XmlSerializerProvider(xmlSerializerProvider0, serializationConfig1, beanSerializerFactory0);
        xmlSerializerProvider1.serializeValue((JsonGenerator) toXmlGenerator0, (Object) xmlSerializerProvider0);
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        XmlRootNameLookup xmlRootNameLookup0 = new XmlRootNameLookup();
        PipedOutputStream pipedOutputStream0 = new PipedOutputStream();
        WriterConfig writerConfig0 = WriterConfig.createFullDefaults();
        AsciiXmlWriter asciiXmlWriter0 = new AsciiXmlWriter(pipedOutputStream0, writerConfig0, true);
        SimpleNsStreamWriter simpleNsStreamWriter0 = new SimpleNsStreamWriter(asciiXmlWriter0, (String) null, writerConfig0);
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, true);
        ObjectMapper objectMapper0 = new ObjectMapper();
        ToXmlGenerator toXmlGenerator0 = new ToXmlGenerator(iOContext0, 0, 3, objectMapper0, simpleNsStreamWriter0);
        XmlSerializerProvider xmlSerializerProvider0 = new XmlSerializerProvider(xmlRootNameLookup0);
        StdSubtypeResolver stdSubtypeResolver0 = new StdSubtypeResolver();
        RootNameLookup rootNameLookup0 = new RootNameLookup();
        ConfigOverrides configOverrides0 = new ConfigOverrides();
        SimpleMixInResolver simpleMixInResolver0 = new SimpleMixInResolver((ClassIntrospector.MixInResolver) null);
        SerializationConfig serializationConfig0 = new SerializationConfig((BaseSettings) null, stdSubtypeResolver0, simpleMixInResolver0, rootNameLookup0, configOverrides0);
        BeanSerializerFactory beanSerializerFactory0 = BeanSerializerFactory.instance;
        XmlSerializerProvider xmlSerializerProvider1 = new XmlSerializerProvider(xmlSerializerProvider0, serializationConfig0, beanSerializerFactory0);
        xmlSerializerProvider1.serializeValue((JsonGenerator) toXmlGenerator0, (Object) xmlRootNameLookup0);
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        XmlRootNameLookup xmlRootNameLookup0 = new XmlRootNameLookup();
        XmlSerializerProvider xmlSerializerProvider0 = new XmlSerializerProvider(xmlRootNameLookup0);
        ObjectMapper objectMapper0 = new ObjectMapper();
        JsonFactory jsonFactory0 = new JsonFactory();
        PipedOutputStream pipedOutputStream0 = new PipedOutputStream();
        WriterConfig writerConfig0 = WriterConfig.createFullDefaults();
        AsciiXmlWriter asciiXmlWriter0 = new AsciiXmlWriter(pipedOutputStream0, writerConfig0, true);
        SimpleNsStreamWriter simpleNsStreamWriter0 = new SimpleNsStreamWriter(asciiXmlWriter0, "JSON", writerConfig0);
        BufferRecycler bufferRecycler0 = jsonFactory0._getBufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, jsonFactory0, true);
        ToXmlGenerator toXmlGenerator0 = new ToXmlGenerator(iOContext0, 3, 3, objectMapper0, simpleNsStreamWriter0);
        xmlSerializerProvider0.serializeValue((JsonGenerator) toXmlGenerator0, (Object) null, (JavaType) null);
        toXmlGenerator0.inRoot();
    }

    @Test(timeout = 4000)
    public void test077() throws Throwable {
        XmlRootNameLookup xmlRootNameLookup0 = new XmlRootNameLookup();
        XmlSerializerProvider xmlSerializerProvider0 = new XmlSerializerProvider(xmlRootNameLookup0);
        JsonFactory jsonFactory0 = new JsonFactory();
        JsonParser jsonParser0 = jsonFactory0.createParser("JSON");
        TokenBuffer tokenBuffer0 = new TokenBuffer(jsonParser0);
        xmlSerializerProvider0.serializeValue((JsonGenerator) tokenBuffer0, (Object) xmlRootNameLookup0, (JavaType) null);
    }

    @Test(timeout = 4000)
    public void test088() throws Throwable {
        XmlRootNameLookup xmlRootNameLookup0 = new XmlRootNameLookup();
        PipedOutputStream pipedOutputStream0 = new PipedOutputStream();
        WriterConfig writerConfig0 = WriterConfig.createFullDefaults();
        AsciiXmlWriter asciiXmlWriter0 = new AsciiXmlWriter(pipedOutputStream0, writerConfig0, false);
        SimpleNsStreamWriter simpleNsStreamWriter0 = new SimpleNsStreamWriter(asciiXmlWriter0, (String) null, writerConfig0);
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, xmlRootNameLookup0, false);
        ObjectMapper objectMapper0 = new ObjectMapper();
        ToXmlGenerator toXmlGenerator0 = new ToXmlGenerator(iOContext0, 0, 1, objectMapper0, simpleNsStreamWriter0);
        XmlSerializerProvider xmlSerializerProvider0 = new XmlSerializerProvider(xmlRootNameLookup0);
        StdSubtypeResolver stdSubtypeResolver0 = new StdSubtypeResolver();
        RootNameLookup rootNameLookup0 = new RootNameLookup();
        ConfigOverrides configOverrides0 = new ConfigOverrides();
        SerializationConfig serializationConfig0 = new SerializationConfig((BaseSettings) null, stdSubtypeResolver0, (SimpleMixInResolver) null, rootNameLookup0, configOverrides0);
        BeanSerializerFactory beanSerializerFactory0 = BeanSerializerFactory.instance;
        XmlSerializerProvider xmlSerializerProvider1 = new XmlSerializerProvider(xmlSerializerProvider0, serializationConfig0, beanSerializerFactory0);
        xmlSerializerProvider1.serializeValue((JsonGenerator) toXmlGenerator0, (Object) writerConfig0, (JavaType) null);
    }

    @Test(timeout = 4000)
    public void test099() throws Throwable {
        XmlRootNameLookup xmlRootNameLookup0 = new XmlRootNameLookup();
        XmlSerializerProvider xmlSerializerProvider0 = new XmlSerializerProvider(xmlRootNameLookup0);
        xmlSerializerProvider0.serializeValue((JsonGenerator) null, (Object) null, (JavaType) null, xmlSerializerProvider0.DEFAULT_NULL_KEY_SERIALIZER);
    }

    @Test(timeout = 4000)
    public void test1010() throws Throwable {
        XmlRootNameLookup xmlRootNameLookup0 = new XmlRootNameLookup();
        PipedOutputStream pipedOutputStream0 = new PipedOutputStream();
        WriterConfig writerConfig0 = WriterConfig.createFullDefaults();
        AsciiXmlWriter asciiXmlWriter0 = new AsciiXmlWriter(pipedOutputStream0, writerConfig0, true);
        SimpleNsStreamWriter simpleNsStreamWriter0 = new SimpleNsStreamWriter(asciiXmlWriter0, (String) null, writerConfig0);
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, true);
        ObjectMapper objectMapper0 = new ObjectMapper();
        ToXmlGenerator toXmlGenerator0 = new ToXmlGenerator(iOContext0, 0, 3, objectMapper0, simpleNsStreamWriter0);
        XmlSerializerProvider xmlSerializerProvider0 = new XmlSerializerProvider(xmlRootNameLookup0);
        StdSubtypeResolver stdSubtypeResolver0 = new StdSubtypeResolver();
        RootNameLookup rootNameLookup0 = new RootNameLookup();
        ConfigOverrides configOverrides0 = new ConfigOverrides();
        SerializationConfig serializationConfig0 = new SerializationConfig((BaseSettings) null, stdSubtypeResolver0, (SimpleMixInResolver) null, rootNameLookup0, configOverrides0);
        BeanSerializerFactory beanSerializerFactory0 = BeanSerializerFactory.instance;
        XmlSerializerProvider xmlSerializerProvider1 = new XmlSerializerProvider(xmlSerializerProvider0, serializationConfig0, beanSerializerFactory0);
        xmlSerializerProvider1.serializeValue((JsonGenerator) toXmlGenerator0, (Object) bufferRecycler0, (JavaType) null, xmlSerializerProvider0.DEFAULT_NULL_KEY_SERIALIZER);
    }

    @Test(timeout = 4000)
    public void test1111() throws Throwable {
        XmlRootNameLookup xmlRootNameLookup0 = new XmlRootNameLookup();
        XmlSerializerProvider xmlSerializerProvider0 = new XmlSerializerProvider(xmlRootNameLookup0);
        JsonFactory jsonFactory0 = new JsonFactory();
        JsonParser jsonParser0 = jsonFactory0.createParser("JSON");
        TokenBuffer tokenBuffer0 = new TokenBuffer(jsonParser0);
        xmlSerializerProvider0.serializeValue((JsonGenerator) tokenBuffer0, (Object) jsonFactory0, (JavaType) null, (JsonSerializer<Object>) null);
    }

    @Test(timeout = 4000)
    public void test1212() throws Throwable {
        XmlRootNameLookup xmlRootNameLookup0 = new XmlRootNameLookup();
        XmlSerializerProvider xmlSerializerProvider0 = new XmlSerializerProvider(xmlRootNameLookup0);
        JsonFactory jsonFactory0 = new JsonFactory();
        JsonParser jsonParser0 = jsonFactory0.createParser("JSON");
        TokenBuffer tokenBuffer0 = new TokenBuffer(jsonParser0);
        Class<ToXmlGenerator> class0 = ToXmlGenerator.class;
        JsonSerializer<Object> jsonSerializer0 = xmlSerializerProvider0.getUnknownTypeSerializer(class0);
        xmlSerializerProvider0.serializeValue((JsonGenerator) tokenBuffer0, (Object) class0, (JavaType) null, jsonSerializer0);
    }

    @Test(timeout = 4000)
    public void test1313() throws Throwable {
        XmlRootNameLookup xmlRootNameLookup0 = new XmlRootNameLookup();
        XmlSerializerProvider xmlSerializerProvider0 = new XmlSerializerProvider(xmlRootNameLookup0);
        JsonFactory jsonFactory0 = new JsonFactory();
        JsonParser jsonParser0 = jsonFactory0.createParser("JSON");
        TokenBuffer tokenBuffer0 = new TokenBuffer(jsonParser0);
        JsonSerializer<Object> jsonSerializer0 = xmlSerializerProvider0.getDefaultNullValueSerializer();
        xmlSerializerProvider0.serializeValue((JsonGenerator) tokenBuffer0, (Object) jsonParser0, (JavaType) null, jsonSerializer0);
        jsonParser0.hasCurrentToken();
    }

    @Test(timeout = 4000)
    public void test1414() throws Throwable {
        XmlRootNameLookup xmlRootNameLookup0 = new XmlRootNameLookup();
        XmlSerializerProvider xmlSerializerProvider0 = new XmlSerializerProvider(xmlRootNameLookup0);
        QName qName0 = XmlSerializerProvider.ROOT_NAME_FOR_NULL;
        ObjectMapper objectMapper0 = new ObjectMapper();
        JsonFactory jsonFactory0 = new JsonFactory();
        PipedOutputStream pipedOutputStream0 = new PipedOutputStream();
        WriterConfig writerConfig0 = WriterConfig.createFullDefaults();
        AsciiXmlWriter asciiXmlWriter0 = new AsciiXmlWriter(pipedOutputStream0, writerConfig0, false);
        SimpleNsStreamWriter simpleNsStreamWriter0 = new SimpleNsStreamWriter(asciiXmlWriter0, "JSON", writerConfig0);
        BufferRecycler bufferRecycler0 = jsonFactory0._getBufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, jsonFactory0, false);
        ToXmlGenerator toXmlGenerator0 = new ToXmlGenerator(iOContext0, 1, 0, objectMapper0, simpleNsStreamWriter0);
        xmlSerializerProvider0._initWithRootName(toXmlGenerator0, qName0);
        xmlSerializerProvider0._initWithRootName(toXmlGenerator0, qName0);
        toXmlGenerator0.inRoot();
    }

    @Test(timeout = 4000)
    public void test1515() throws Throwable {
        XmlRootNameLookup xmlRootNameLookup0 = new XmlRootNameLookup();
        PipedOutputStream pipedOutputStream0 = new PipedOutputStream();
        WriterConfig writerConfig0 = WriterConfig.createFullDefaults();
        AsciiXmlWriter asciiXmlWriter0 = new AsciiXmlWriter(pipedOutputStream0, writerConfig0, true);
        SimpleNsStreamWriter simpleNsStreamWriter0 = new SimpleNsStreamWriter(asciiXmlWriter0, (String) null, writerConfig0);
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, xmlRootNameLookup0, false);
        ObjectMapper objectMapper0 = new ObjectMapper();
        ToXmlGenerator toXmlGenerator0 = new ToXmlGenerator(iOContext0, 3, 0, objectMapper0, simpleNsStreamWriter0);
        XmlSerializerProvider xmlSerializerProvider0 = new XmlSerializerProvider(xmlRootNameLookup0);
        StdSubtypeResolver stdSubtypeResolver0 = new StdSubtypeResolver();
        RootNameLookup rootNameLookup0 = new RootNameLookup();
        ConfigOverrides configOverrides0 = new ConfigOverrides();
        SerializationConfig serializationConfig0 = new SerializationConfig((BaseSettings) null, stdSubtypeResolver0, (SimpleMixInResolver) null, rootNameLookup0, configOverrides0);
        BeanSerializerFactory beanSerializerFactory0 = BeanSerializerFactory.instance;
        PropertyName propertyName0 = new PropertyName((String) null, "[no message for ");
        SerializationConfig serializationConfig1 = serializationConfig0.withRootName(propertyName0);
        XmlSerializerProvider xmlSerializerProvider1 = new XmlSerializerProvider(xmlSerializerProvider0, serializationConfig1, beanSerializerFactory0);
        DeserializationFeature deserializationFeature0 = DeserializationFeature.ACCEPT_FLOAT_AS_INT;
        xmlSerializerProvider1.serializeValue((JsonGenerator) toXmlGenerator0, (Object) deserializationFeature0);
    }

    @Test(timeout = 4000)
    public void test1616() throws Throwable {
        XmlRootNameLookup xmlRootNameLookup0 = new XmlRootNameLookup();
        PipedOutputStream pipedOutputStream0 = new PipedOutputStream();
        WriterConfig writerConfig0 = WriterConfig.createFullDefaults();
        ISOLatin1XmlWriter iSOLatin1XmlWriter0 = new ISOLatin1XmlWriter(pipedOutputStream0, writerConfig0, false);
        SimpleNsStreamWriter simpleNsStreamWriter0 = new SimpleNsStreamWriter(iSOLatin1XmlWriter0, (String) null, writerConfig0);
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, pipedOutputStream0, false);
        ObjectMapper objectMapper0 = new ObjectMapper();
        ToXmlGenerator toXmlGenerator0 = new ToXmlGenerator(iOContext0, (-3477), 0, objectMapper0, simpleNsStreamWriter0);
        XmlSerializerProvider xmlSerializerProvider0 = new XmlSerializerProvider(xmlRootNameLookup0);
        StdSubtypeResolver stdSubtypeResolver0 = new StdSubtypeResolver();
        RootNameLookup rootNameLookup0 = new RootNameLookup();
        SerializationConfig serializationConfig0 = new SerializationConfig((BaseSettings) null, stdSubtypeResolver0, (SimpleMixInResolver) null, rootNameLookup0, (ConfigOverrides) null);
        BeanSerializerFactory beanSerializerFactory0 = BeanSerializerFactory.instance;
        PropertyName propertyName0 = new PropertyName((String) null, "");
        SerializationConfig serializationConfig1 = serializationConfig0.withRootName(propertyName0);
        XmlSerializerProvider xmlSerializerProvider1 = new XmlSerializerProvider(xmlSerializerProvider0, serializationConfig1, beanSerializerFactory0);
        ObjectMapper.DefaultTyping objectMapper_DefaultTyping0 = ObjectMapper.DefaultTyping.NON_FINAL;
        ObjectMapper.DefaultTypeResolverBuilder objectMapper_DefaultTypeResolverBuilder0 = new ObjectMapper.DefaultTypeResolverBuilder(objectMapper_DefaultTyping0);
        xmlSerializerProvider1.serializeValue((JsonGenerator) toXmlGenerator0, (Object) objectMapper_DefaultTypeResolverBuilder0);
    }

    @Test(timeout = 4000)
    public void test1717() throws Throwable {
        XmlRootNameLookup xmlRootNameLookup0 = new XmlRootNameLookup();
        XmlSerializerProvider xmlSerializerProvider0 = new XmlSerializerProvider(xmlRootNameLookup0);
        Class<NullNode> class0 = NullNode.class;
        xmlSerializerProvider0.serializeValue((JsonGenerator) null, (Object) class0, (JavaType) null, xmlSerializerProvider0.DEFAULT_NULL_KEY_SERIALIZER);
    }
}
