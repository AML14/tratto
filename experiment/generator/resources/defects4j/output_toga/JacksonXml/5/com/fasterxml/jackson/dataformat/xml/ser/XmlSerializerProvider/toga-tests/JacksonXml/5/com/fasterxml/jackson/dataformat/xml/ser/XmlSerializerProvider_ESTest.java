/*
 * This file was automatically generated by EvoSuite
 * Sun Nov 05 18:13:36 GMT 2023
 */
package com.fasterxml.jackson.dataformat.xml.ser;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.ctc.wstx.api.WriterConfig;
import com.ctc.wstx.sw.SimpleNsStreamWriter;
import com.ctc.wstx.sw.XmlWriter;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.base.GeneratorBase;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.cfg.BaseSettings;
import com.fasterxml.jackson.databind.cfg.ConfigOverrides;
import com.fasterxml.jackson.databind.deser.BeanDeserializerFactory;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.introspect.SimpleMixInResolver;
import com.fasterxml.jackson.databind.jsontype.impl.StdSubtypeResolver;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.ser.BeanSerializer;
import com.fasterxml.jackson.databind.ser.BeanSerializerFactory;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.fasterxml.jackson.databind.type.ResolvedRecursiveType;
import com.fasterxml.jackson.databind.type.TypeBindings;
import com.fasterxml.jackson.databind.util.RootNameLookup;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.fasterxml.jackson.dataformat.xml.ser.XmlSerializerProvider;
import com.fasterxml.jackson.dataformat.xml.util.XmlRootNameLookup;
import java.io.IOException;
import java.sql.SQLDataException;
import java.sql.SQLNonTransientConnectionException;
import java.sql.SQLRecoverableException;
import java.sql.SQLTransientConnectionException;
import javax.xml.namespace.QName;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class XmlSerializerProvider_ESTest extends XmlSerializerProvider_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        XmlSerializerProvider xmlSerializerProvider0 = new XmlSerializerProvider((XmlRootNameLookup) null);
        QName qName0 = new QName(", new = ", "x#z11#'7p1&DEi ?[U", ", new = ");
        xmlSerializerProvider0._startRootArray((ToXmlGenerator) null, qName0);
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        XmlRootNameLookup xmlRootNameLookup0 = new XmlRootNameLookup();
        XmlSerializerProvider xmlSerializerProvider0 = new XmlSerializerProvider(xmlRootNameLookup0);
        DefaultSerializerProvider defaultSerializerProvider0 = xmlSerializerProvider0.copy();
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        XmlRootNameLookup xmlRootNameLookup0 = new XmlRootNameLookup();
        XmlSerializerProvider xmlSerializerProvider0 = new XmlSerializerProvider(xmlRootNameLookup0);
        StdSubtypeResolver stdSubtypeResolver0 = new StdSubtypeResolver();
        RootNameLookup rootNameLookup0 = new RootNameLookup();
        ConfigOverrides configOverrides0 = new ConfigOverrides();
        SerializationConfig serializationConfig0 = new SerializationConfig((BaseSettings) null, stdSubtypeResolver0, (SimpleMixInResolver) null, rootNameLookup0, configOverrides0);
        BeanSerializerFactory beanSerializerFactory0 = BeanSerializerFactory.instance;
        XmlSerializerProvider xmlSerializerProvider1 = new XmlSerializerProvider(xmlSerializerProvider0, serializationConfig0, beanSerializerFactory0);
        JsonFactory jsonFactory0 = new JsonFactory();
        WriterConfig writerConfig0 = WriterConfig.createJ2MEDefaults();
        SimpleNsStreamWriter simpleNsStreamWriter0 = new SimpleNsStreamWriter((XmlWriter) null, "JSON", writerConfig0);
        ToXmlGenerator toXmlGenerator0 = new ToXmlGenerator((IOContext) null, (-3443), (-3443), (ObjectCodec) null, simpleNsStreamWriter0);
        xmlSerializerProvider1.serializeValue((JsonGenerator) toXmlGenerator0, (Object) jsonFactory0);
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        XmlRootNameLookup xmlRootNameLookup0 = new XmlRootNameLookup();
        XmlSerializerProvider xmlSerializerProvider0 = new XmlSerializerProvider(xmlRootNameLookup0);
        BeanSerializerFactory beanSerializerFactory0 = BeanSerializerFactory.instance;
        xmlSerializerProvider0.createInstance((SerializationConfig) null, beanSerializerFactory0);
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        XmlRootNameLookup xmlRootNameLookup0 = new XmlRootNameLookup();
        XmlSerializerProvider xmlSerializerProvider0 = new XmlSerializerProvider(xmlRootNameLookup0);
        ObjectMapper objectMapper0 = new ObjectMapper();
        WriterConfig writerConfig0 = WriterConfig.createFullDefaults();
        SimpleNsStreamWriter simpleNsStreamWriter0 = new SimpleNsStreamWriter((XmlWriter) null, "[no message for ", writerConfig0);
        ToXmlGenerator toXmlGenerator0 = new ToXmlGenerator((IOContext) null, 744, 744, objectMapper0, simpleNsStreamWriter0);
        xmlSerializerProvider0.serializeValue((JsonGenerator) toXmlGenerator0, (Object) "[no message for ");
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        XmlSerializerProvider xmlSerializerProvider0 = new XmlSerializerProvider((XmlRootNameLookup) null);
        xmlSerializerProvider0.serializeValue((JsonGenerator) null, (Object) null);
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        XmlRootNameLookup xmlRootNameLookup0 = new XmlRootNameLookup();
        XmlSerializerProvider xmlSerializerProvider0 = new XmlSerializerProvider(xmlRootNameLookup0);
        JsonFactory jsonFactory0 = new JsonFactory();
        JsonParser jsonParser0 = jsonFactory0.createParser("JSON");
        TokenBuffer tokenBuffer0 = new TokenBuffer(jsonParser0);
        xmlSerializerProvider0.serializeValue((JsonGenerator) tokenBuffer0, (Object) jsonFactory0);
    }

    @Test(timeout = 4000)
    public void test077() throws Throwable {
        XmlRootNameLookup xmlRootNameLookup0 = new XmlRootNameLookup();
        XmlSerializerProvider xmlSerializerProvider0 = new XmlSerializerProvider(xmlRootNameLookup0);
        xmlSerializerProvider0.serializeValue((JsonGenerator) null, (Object) null, (JavaType) null, xmlSerializerProvider0.DEFAULT_NULL_KEY_SERIALIZER);
    }

    @Test(timeout = 4000)
    public void test088() throws Throwable {
        XmlRootNameLookup xmlRootNameLookup0 = new XmlRootNameLookup();
        XmlSerializerProvider xmlSerializerProvider0 = new XmlSerializerProvider(xmlRootNameLookup0);
        JsonFactory jsonFactory0 = new JsonFactory();
        JsonParser jsonParser0 = jsonFactory0.createParser("JSON");
        TokenBuffer tokenBuffer0 = new TokenBuffer(jsonParser0);
        Class<JavaType> class0 = JavaType.class;
        ResolvedRecursiveType resolvedRecursiveType0 = new ResolvedRecursiveType(class0, (TypeBindings) null);
        BeanSerializer beanSerializer0 = BeanSerializer.createDummy(resolvedRecursiveType0);
        xmlSerializerProvider0.serializeValue((JsonGenerator) tokenBuffer0, (Object) xmlRootNameLookup0, (JavaType) resolvedRecursiveType0, (JsonSerializer<Object>) beanSerializer0);
        resolvedRecursiveType0.isAbstract();
    }

    @Test(timeout = 4000)
    public void test099() throws Throwable {
        XmlRootNameLookup xmlRootNameLookup0 = new XmlRootNameLookup();
        XmlSerializerProvider xmlSerializerProvider0 = new XmlSerializerProvider(xmlRootNameLookup0);
        StdSubtypeResolver stdSubtypeResolver0 = new StdSubtypeResolver();
        RootNameLookup rootNameLookup0 = new RootNameLookup();
        ConfigOverrides configOverrides0 = new ConfigOverrides();
        SerializationConfig serializationConfig0 = new SerializationConfig((BaseSettings) null, stdSubtypeResolver0, (SimpleMixInResolver) null, rootNameLookup0, configOverrides0);
        BeanSerializerFactory beanSerializerFactory0 = BeanSerializerFactory.instance;
        WriterConfig writerConfig0 = WriterConfig.createFullDefaults();
        SimpleNsStreamWriter simpleNsStreamWriter0 = new SimpleNsStreamWriter((XmlWriter) null, "JSON", writerConfig0);
        ToXmlGenerator toXmlGenerator0 = new ToXmlGenerator((IOContext) null, (-3443), (-3443), (ObjectCodec) null, simpleNsStreamWriter0);
        XmlSerializerProvider xmlSerializerProvider1 = new XmlSerializerProvider(xmlSerializerProvider0, serializationConfig0, beanSerializerFactory0);
        xmlSerializerProvider1.serializeValue((JsonGenerator) toXmlGenerator0, (Object) rootNameLookup0, (JavaType) null, xmlSerializerProvider0.DEFAULT_NULL_KEY_SERIALIZER);
    }

    @Test(timeout = 4000)
    public void test1010() throws Throwable {
        XmlRootNameLookup xmlRootNameLookup0 = new XmlRootNameLookup();
        JsonFactory jsonFactory0 = new JsonFactory();
        JsonParser jsonParser0 = jsonFactory0.createParser("JSON");
        TokenBuffer tokenBuffer0 = new TokenBuffer(jsonParser0);
        Class<JavaType> class0 = JavaType.class;
        ResolvedRecursiveType resolvedRecursiveType0 = new ResolvedRecursiveType(class0, (TypeBindings) null);
        XmlSerializerProvider xmlSerializerProvider0 = new XmlSerializerProvider(xmlRootNameLookup0);
        xmlSerializerProvider0.serializeValue((JsonGenerator) tokenBuffer0, (Object) xmlRootNameLookup0, (JavaType) resolvedRecursiveType0, (JsonSerializer<Object>) null);
    }

    @Test(timeout = 4000)
    public void test1111() throws Throwable {
        XmlRootNameLookup xmlRootNameLookup0 = new XmlRootNameLookup();
        XmlSerializerProvider xmlSerializerProvider0 = new XmlSerializerProvider(xmlRootNameLookup0);
        StdSubtypeResolver stdSubtypeResolver0 = new StdSubtypeResolver();
        RootNameLookup rootNameLookup0 = new RootNameLookup();
        ConfigOverrides configOverrides0 = new ConfigOverrides();
        SerializationConfig serializationConfig0 = new SerializationConfig((BaseSettings) null, stdSubtypeResolver0, (SimpleMixInResolver) null, rootNameLookup0, configOverrides0);
        BeanSerializerFactory beanSerializerFactory0 = BeanSerializerFactory.instance;
        XmlSerializerProvider xmlSerializerProvider1 = new XmlSerializerProvider(xmlSerializerProvider0, serializationConfig0, beanSerializerFactory0);
        WriterConfig writerConfig0 = WriterConfig.createJ2MEDefaults();
        SimpleNsStreamWriter simpleNsStreamWriter0 = new SimpleNsStreamWriter((XmlWriter) null, "JSON", writerConfig0);
        ToXmlGenerator toXmlGenerator0 = new ToXmlGenerator((IOContext) null, (-3443), (-3443), (ObjectCodec) null, simpleNsStreamWriter0);
        xmlSerializerProvider1._serializeXmlNull(toXmlGenerator0);
    }

    @Test(timeout = 4000)
    public void test1212() throws Throwable {
        XmlRootNameLookup xmlRootNameLookup0 = new XmlRootNameLookup();
        XmlSerializerProvider xmlSerializerProvider0 = new XmlSerializerProvider(xmlRootNameLookup0);
        StdSubtypeResolver stdSubtypeResolver0 = new StdSubtypeResolver();
        RootNameLookup rootNameLookup0 = new RootNameLookup();
        ConfigOverrides configOverrides0 = new ConfigOverrides();
        SerializationConfig serializationConfig0 = new SerializationConfig((BaseSettings) null, stdSubtypeResolver0, (SimpleMixInResolver) null, rootNameLookup0, configOverrides0);
        BeanSerializerFactory beanSerializerFactory0 = BeanSerializerFactory.instance;
        XmlSerializerProvider xmlSerializerProvider1 = new XmlSerializerProvider(xmlSerializerProvider0, serializationConfig0, beanSerializerFactory0);
        xmlSerializerProvider1._serializeXmlNull((JsonGenerator) null);
    }

    @Test(timeout = 4000)
    public void test1313() throws Throwable {
        XmlRootNameLookup xmlRootNameLookup0 = new XmlRootNameLookup();
        XmlSerializerProvider xmlSerializerProvider0 = new XmlSerializerProvider(xmlRootNameLookup0);
        ObjectMapper objectMapper0 = new ObjectMapper();
        WriterConfig writerConfig0 = WriterConfig.createJ2MEDefaults();
        SimpleNsStreamWriter simpleNsStreamWriter0 = new SimpleNsStreamWriter((XmlWriter) null, "i{em", writerConfig0);
        ToXmlGenerator toXmlGenerator0 = new ToXmlGenerator((IOContext) null, (-216), (-216), objectMapper0, simpleNsStreamWriter0);
        QName qName0 = XmlSerializerProvider.ROOT_NAME_FOR_NULL;
        xmlSerializerProvider0._initWithRootName(toXmlGenerator0, qName0);
        xmlSerializerProvider0._initWithRootName(toXmlGenerator0, qName0);
        toXmlGenerator0.isClosed();
    }

    @Test(timeout = 4000)
    public void test1414() throws Throwable {
        XmlRootNameLookup xmlRootNameLookup0 = new XmlRootNameLookup();
        XmlSerializerProvider xmlSerializerProvider0 = new XmlSerializerProvider(xmlRootNameLookup0);
        ObjectMapper objectMapper0 = new ObjectMapper();
        WriterConfig writerConfig0 = WriterConfig.createJ2MEDefaults();
        SimpleNsStreamWriter simpleNsStreamWriter0 = new SimpleNsStreamWriter((XmlWriter) null, "i{em", writerConfig0);
        ToXmlGenerator toXmlGenerator0 = new ToXmlGenerator((IOContext) null, (-216), (-216), objectMapper0, simpleNsStreamWriter0);
        QName qName0 = XmlSerializerProvider.ROOT_NAME_FOR_NULL;
        toXmlGenerator0.writeStartArray();
        xmlSerializerProvider0._initWithRootName(toXmlGenerator0, qName0);
        xmlSerializerProvider0._initWithRootName(toXmlGenerator0, qName0);
    }

    @Test(timeout = 4000)
    public void test1515() throws Throwable {
        XmlRootNameLookup xmlRootNameLookup0 = new XmlRootNameLookup();
        XmlSerializerProvider xmlSerializerProvider0 = new XmlSerializerProvider(xmlRootNameLookup0);
        ObjectMapper objectMapper0 = new ObjectMapper();
        WriterConfig writerConfig0 = WriterConfig.createJ2MEDefaults();
        SimpleNsStreamWriter simpleNsStreamWriter0 = new SimpleNsStreamWriter((XmlWriter) null, "#HTdD_f*@I=9vDb9:", writerConfig0);
        ToXmlGenerator toXmlGenerator0 = new ToXmlGenerator((IOContext) null, (-216), (-216), objectMapper0, simpleNsStreamWriter0);
        QName qName0 = new QName("#HTdD_f*@I=9vDb9:", "#HTdD_f*@I=9vDb9:");
        xmlSerializerProvider0._initWithRootName(toXmlGenerator0, qName0);
        toXmlGenerator0.canWriteFormattedNumbers();
        assertTrue(toXmlGenerator0.canWriteFormattedNumbers());
    }

    @Test(timeout = 4000)
    public void test1616() throws Throwable {
        XmlRootNameLookup xmlRootNameLookup0 = new XmlRootNameLookup();
        XmlSerializerProvider xmlSerializerProvider0 = new XmlSerializerProvider(xmlRootNameLookup0);
        xmlSerializerProvider0.serializeValue((JsonGenerator) null, (Object) xmlRootNameLookup0);
    }

    @Test(timeout = 4000)
    public void test1717() throws Throwable {
        XmlSerializerProvider xmlSerializerProvider0 = new XmlSerializerProvider((XmlRootNameLookup) null);
        SQLRecoverableException sQLRecoverableException0 = new SQLRecoverableException();
        IOException iOException0 = xmlSerializerProvider0._wrapAsIOE((JsonGenerator) null, sQLRecoverableException0);
    }

    @Test(timeout = 4000)
    public void test1818() throws Throwable {
        XmlRootNameLookup xmlRootNameLookup0 = new XmlRootNameLookup();
        XmlSerializerProvider xmlSerializerProvider0 = new XmlSerializerProvider(xmlRootNameLookup0);
        ObjectMapper objectMapper0 = new ObjectMapper();
        NullNode nullNode0 = NullNode.getInstance();
        JsonParser jsonParser0 = objectMapper0.treeAsTokens(nullNode0);
        BeanDeserializerFactory beanDeserializerFactory0 = BeanDeserializerFactory.instance;
        DefaultDeserializationContext.Impl defaultDeserializationContext_Impl0 = new DefaultDeserializationContext.Impl(beanDeserializerFactory0);
        TokenBuffer tokenBuffer0 = new TokenBuffer(jsonParser0, defaultDeserializationContext_Impl0);
        xmlSerializerProvider0.serializeValue((JsonGenerator) tokenBuffer0, (Object) xmlRootNameLookup0, (JavaType) null, xmlSerializerProvider0.DEFAULT_NULL_KEY_SERIALIZER);
    }

    @Test(timeout = 4000)
    public void test1919() throws Throwable {
        XmlSerializerProvider xmlSerializerProvider0 = new XmlSerializerProvider((XmlRootNameLookup) null);
        SQLDataException sQLDataException0 = new SQLDataException("S[%OJ+1sw", "S[%OJ+1sw");
        SQLRecoverableException sQLRecoverableException0 = new SQLRecoverableException("S[%OJ+1sw", sQLDataException0);
        SQLTransientConnectionException sQLTransientConnectionException0 = new SQLTransientConnectionException("S[%OJ+1sw", "S[%OJ+1sw", (-642), sQLRecoverableException0);
        SQLNonTransientConnectionException sQLNonTransientConnectionException0 = new SQLNonTransientConnectionException("S[%OJ+1sw", "S[%OJ+1sw", (-642), sQLTransientConnectionException0);
        IOException iOException0 = xmlSerializerProvider0._wrapAsIOE((JsonGenerator) null, sQLNonTransientConnectionException0);
    }
}
