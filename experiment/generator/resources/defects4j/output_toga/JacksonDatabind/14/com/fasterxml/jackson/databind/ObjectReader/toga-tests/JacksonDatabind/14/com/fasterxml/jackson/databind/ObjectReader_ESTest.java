/*
 * This file was automatically generated by EvoSuite
 * Wed Mar 27 23:33:32 GMT 2024
 */
package com.fasterxml.jackson.databind;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.FormatSchema;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.json.ReaderBasedJsonParser;
import com.fasterxml.jackson.core.json.UTF8JsonGenerator;
import com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer;
import com.fasterxml.jackson.core.type.ResolvedType;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.BufferRecycler;
import com.fasterxml.jackson.core.util.JsonParserSequence;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.cfg.ContextAttributes;
import com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig;
import com.fasterxml.jackson.databind.deser.AbstractDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializerFactory;
import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.fasterxml.jackson.databind.ext.CoreXMLDeserializers;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.DecimalNode;
import com.fasterxml.jackson.databind.node.DoubleNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Stack;
import java.util.TimeZone;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.evosuite.runtime.mock.java.io.MockFile;
import org.evosuite.runtime.mock.java.io.MockPrintStream;
import org.evosuite.runtime.mock.java.net.MockURL;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class ObjectReader_ESTest extends ObjectReader_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        ObjectReader objectReader0 = objectMapper0.reader();
        MapperFeature mapperFeature0 = MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES;
        boolean boolean0 = objectReader0.isEnabled(mapperFeature0);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        ObjectReader objectReader0 = objectMapper0.reader((FormatSchema) null);
        DeserializationConfig deserializationConfig0 = objectReader0.getConfig();
        deserializationConfig0.getRootName();
        assertNotNull(deserializationConfig0.getRootName());
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        DeserializationFeature deserializationFeature0 = DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS;
        ObjectReader objectReader0 = objectMapper0.reader(deserializationFeature0);
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        MissingNode missingNode0 = MissingNode.getInstance();
        IOContext iOContext0 = new IOContext(bufferRecycler0, missingNode0, false);
        MockFile mockFile0 = new MockFile("", ".n:UoBD2T\"p8");
        MockPrintStream mockPrintStream0 = new MockPrintStream(mockFile0);
        UTF8JsonGenerator uTF8JsonGenerator0 = new UTF8JsonGenerator(iOContext0, 0, objectReader0, mockPrintStream0);
        objectReader0.writeTree(uTF8JsonGenerator0, missingNode0);
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        ObjectReader objectReader0 = objectMapper0.reader();
        DeserializationFeature[] deserializationFeatureArray0 = new DeserializationFeature[0];
        ObjectReader objectReader1 = objectReader0.withoutFeatures(deserializationFeatureArray0);
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        ObjectReader objectReader0 = objectMapper0.reader();
        Class<TextNode> class0 = TextNode.class;
        Iterator<TextNode> iterator0 = objectReader0.readValues((JsonParser) null, class0);
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        ObjectReader objectReader0 = objectMapper0.reader();
        JsonFactory jsonFactory0 = new JsonFactory();
        ObjectReader objectReader1 = objectReader0.with(jsonFactory0);
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        ObjectReader objectReader0 = objectMapper0.reader();
        Class<DecimalNode> class0 = DecimalNode.class;
        ObjectReader objectReader1 = objectReader0.withView(class0);
    }

    @Test(timeout = 4000)
    public void test077() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        ObjectReader objectReader0 = objectMapper0.reader();
        HashMap<Object, Object> hashMap0 = new HashMap<Object, Object>();
        ObjectReader objectReader1 = objectReader0.withAttributes(hashMap0);
    }

    @Test(timeout = 4000)
    public void test088() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        ObjectReader objectReader0 = objectMapper0.reader();
        byte[] byteArray0 = new byte[0];
        objectReader0.readValues(byteArray0);
    }

    @Test(timeout = 4000)
    public void test099() throws Throwable {
        JsonFactory jsonFactory0 = new JsonFactory();
        ObjectMapper objectMapper0 = new ObjectMapper(jsonFactory0);
        ObjectReader objectReader0 = objectMapper0.readerForUpdating((Object) jsonFactory0);
        objectMapper0.writeValueAsBytes(objectReader0);
    }

    @Test(timeout = 4000)
    public void test1010() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        DeserializationFeature deserializationFeature0 = DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY;
        ObjectReader objectReader0 = objectMapper0.reader(deserializationFeature0);
        JsonInclude.Include jsonInclude_Include0 = JsonInclude.Include.ALWAYS;
        objectReader0._reportUndetectableSource(jsonInclude_Include0);
    }

    @Test(timeout = 4000)
    public void test1111() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        DeserializationFeature deserializationFeature0 = DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY;
        ObjectReader objectReader0 = objectMapper0.reader(deserializationFeature0);
        JsonParser.Feature jsonParser_Feature0 = JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS;
        ObjectReader objectReader1 = objectReader0.with(jsonParser_Feature0);
    }

    @Test(timeout = 4000)
    public void test1212() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        ObjectReader objectReader0 = objectMapper0.reader();
        JsonParser.Feature jsonParser_Feature0 = JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS;
        ObjectReader objectReader1 = objectReader0.without(jsonParser_Feature0);
    }

    @Test(timeout = 4000)
    public void test1313() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        ObjectReader objectReader0 = objectMapper0.reader();
        File file0 = MockFile.createTempFile("com.fasterxml.jackson.databind.annotation.JsonSerialize$Typing", "com.fasterxml.jackson.databind.annotation.JsonSerialize$Typing");
        InputStream inputStream0 = objectReader0._inputStream(file0);
        inputStream0.available();
    }

    @Test(timeout = 4000)
    public void test1414() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        ObjectReader objectReader0 = objectMapper0.reader();
        DeserializationFeature deserializationFeature0 = DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS;
        ObjectReader objectReader1 = objectReader0.with(deserializationFeature0);
    }

    @Test(timeout = 4000)
    public void test1515() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        ObjectReader objectReader0 = objectMapper0.reader();
        Vector<ObjectReader> vector0 = new Vector<ObjectReader>(1);
        DataFormatReaders dataFormatReaders0 = new DataFormatReaders(vector0);
        DataFormatReaders.Match dataFormatReaders_Match0 = dataFormatReaders0.findFormat((byte[]) null, 64, 64);
        objectReader0._detectBindAndClose(dataFormatReaders_Match0, true);
    }

    @Test(timeout = 4000)
    public void test1616() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        ObjectReader objectReader0 = objectMapper0.reader();
        JsonParser.Feature[] jsonParser_FeatureArray0 = new JsonParser.Feature[4];
        objectReader0.withFeatures(jsonParser_FeatureArray0);
    }

    @Test(timeout = 4000)
    public void test1717() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        ObjectReader objectReader0 = objectMapper0.reader();
        ObjectReader objectReader1 = objectReader0.withRootName("");
    }

    @Test(timeout = 4000)
    public void test1818() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        ObjectReader objectReader0 = objectMapper0.reader();
        ObjectReader objectReader1 = objectReader0.withHandler((DeserializationProblemHandler) null);
    }

    @Test(timeout = 4000)
    public void test1919() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, "IN=zJhe\"N(OMS7M", false);
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, 252, (Reader) null, objectMapper0, charsToNameCanonicalizer0);
        ObjectReader objectReader0 = objectMapper0.reader((FormatSchema) null);
        objectReader0.readValue((JsonParser) readerBasedJsonParser0, (ResolvedType) null);
    }

    @Test(timeout = 4000)
    public void test2020() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        DeserializationFeature deserializationFeature0 = DeserializationFeature.READ_ENUMS_USING_TO_STRING;
        ObjectReader objectReader0 = objectMapper0.reader(deserializationFeature0);
        JsonFactory jsonFactory0 = new JsonFactory();
        char[] charArray0 = new char[1];
        JsonParser jsonParser0 = jsonFactory0.createParser(charArray0, (-808), (-808));
        objectReader0.readTree(jsonParser0);
    }

    @Test(timeout = 4000)
    public void test2121() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        ObjectReader objectReader0 = objectMapper0.reader();
        Locale locale0 = Locale.ITALIAN;
        ObjectReader objectReader1 = objectReader0.with(locale0);
    }

    @Test(timeout = 4000)
    public void test2222() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        ObjectReader objectReader0 = objectMapper0.reader();
        TypeFactory typeFactory0 = TypeFactory.defaultInstance();
        Class<ConcurrentHashMap> class0 = ConcurrentHashMap.class;
        MapType mapType0 = typeFactory0.constructRawMapType(class0);
        Iterator<AbstractDeserializer> iterator0 = objectReader0.readValues((JsonParser) null, (ResolvedType) mapType0);
        assertNotNull(iterator0);
    }

    @Test(timeout = 4000)
    public void test2323() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        Class<CoreXMLDeserializers.Std> class0 = CoreXMLDeserializers.Std.class;
        ObjectReader objectReader0 = objectMapper0.reader((Class<?>) class0);
        MissingNode missingNode0 = MissingNode.getInstance();
        objectReader0.readValue((JsonNode) missingNode0);
    }

    @Test(timeout = 4000)
    public void test2424() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        DeserializationFeature deserializationFeature0 = DeserializationFeature.FAIL_ON_INVALID_SUBTYPE;
        ObjectReader objectReader0 = objectMapper0.reader(deserializationFeature0);
        JsonParser.Feature[] jsonParser_FeatureArray0 = new JsonParser.Feature[3];
        objectReader0.withoutFeatures(jsonParser_FeatureArray0);
    }

    @Test(timeout = 4000)
    public void test2525() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        ObjectReader objectReader0 = objectMapper0.reader();
        ObjectReader[] objectReaderArray0 = new ObjectReader[2];
        ObjectReader objectReader1 = objectReader0.withFormatDetection(objectReaderArray0);
    }

    @Test(timeout = 4000)
    public void test2626() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        objectMapper0.reader();
        JsonNodeFactory jsonNodeFactory0 = JsonNodeFactory.instance;
        ObjectNode objectNode0 = new ObjectNode(jsonNodeFactory0);
        Class<MissingNode> class0 = MissingNode.class;
    }

    @Test(timeout = 4000)
    public void test2727() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        ObjectReader objectReader0 = objectMapper0.reader();
        Class<DoubleNode> class0 = DoubleNode.class;
        ObjectReader objectReader1 = objectReader0.forType(class0);
    }

    @Test(timeout = 4000)
    public void test2828() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        DeserializationFeature deserializationFeature0 = DeserializationFeature.USE_BIG_INTEGER_FOR_INTS;
        ObjectReader objectReader0 = objectMapper0.reader(deserializationFeature0);
        objectReader0.with((DeserializationConfig) null);
    }

    @Test(timeout = 4000)
    public void test2929() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        DeserializationFeature deserializationFeature0 = DeserializationFeature.FAIL_ON_INVALID_SUBTYPE;
        ObjectReader objectReader0 = objectMapper0.reader(deserializationFeature0);
        boolean boolean0 = objectReader0.isEnabled(deserializationFeature0);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test3030() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        Class<BooleanNode> class0 = BooleanNode.class;
        ObjectReader objectReader0 = objectMapper0.reader((Class<?>) class0);
        ContextAttributes contextAttributes0 = ContextAttributes.getEmpty();
        ObjectReader objectReader1 = objectReader0.with(contextAttributes0);
    }

    @Test(timeout = 4000)
    public void test3131() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        ObjectReader objectReader0 = objectMapper0.reader();
        DeserializationFeature deserializationFeature0 = DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES;
        DeserializationFeature[] deserializationFeatureArray0 = new DeserializationFeature[4];
        objectReader0.without(deserializationFeature0, deserializationFeatureArray0);
    }

    @Test(timeout = 4000)
    public void test3232() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        DeserializationFeature deserializationFeature0 = DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS;
        ObjectReader objectReader0 = objectMapper0.reader(deserializationFeature0);
        TypeReference<BeanDeserializer> typeReference0 = (TypeReference<BeanDeserializer>) mock(TypeReference.class, new ViolatedAssumptionAnswer());
        doReturn((Type) null).when(typeReference0).getType();
        objectReader0.readValue((JsonParser) null, (TypeReference<?>) typeReference0);
    }

    @Test(timeout = 4000)
    public void test3333() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        DeserializationFeature deserializationFeature0 = DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY;
        ObjectReader objectReader0 = objectMapper0.reader(deserializationFeature0);
        TypeFactory typeFactory0 = objectReader0.getTypeFactory();
        assertNotNull(typeFactory0);
    }

    @Test(timeout = 4000)
    public void test3434() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        ObjectReader objectReader0 = objectMapper0.reader();
        JsonParserSequence jsonParserSequence0 = JsonParserSequence.createFlattened((JsonParser) null, (JsonParser) null);
        TypeReference<Object> typeReference0 = (TypeReference<Object>) mock(TypeReference.class, new ViolatedAssumptionAnswer());
        doReturn((Type) null).when(typeReference0).getType();
        objectReader0.readValues((JsonParser) jsonParserSequence0, (TypeReference<?>) typeReference0);
    }

    @Test(timeout = 4000)
    public void test3535() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        DeserializationFeature deserializationFeature0 = DeserializationFeature.READ_ENUMS_USING_TO_STRING;
        ObjectReader objectReader0 = objectMapper0.reader(deserializationFeature0);
        URL uRL0 = MockURL.getFileExample();
        objectReader0._inputStream(uRL0);
    }

    @Test(timeout = 4000)
    public void test3636() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        Class<Object> class0 = Object.class;
        ObjectReader objectReader0 = objectMapper0.reader((Class<?>) class0);
        ArrayNode arrayNode0 = objectMapper0.createArrayNode();
        JsonParser jsonParser0 = objectMapper0.treeAsTokens(arrayNode0);
        objectReader0.readValue(jsonParser0, (JavaType) null);
    }

    @Test(timeout = 4000)
    public void test3737() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        ObjectReader objectReader0 = objectMapper0.reader();
        objectReader0.withFeatures((DeserializationFeature[]) null);
    }

    @Test(timeout = 4000)
    public void test3838() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        Class<String> class0 = String.class;
        ObjectReader objectReader0 = objectMapper0.reader((Class<?>) class0);
        JsonFactory jsonFactory0 = objectReader0.getJsonFactory();
        jsonFactory0.requiresCustomCodec();
    }

    @Test(timeout = 4000)
    public void test3939() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        HashMap<String, Object> hashMap0 = new HashMap<String, Object>();
        InjectableValues.Std injectableValues_Std0 = new InjectableValues.Std(hashMap0);
        ObjectReader objectReader0 = objectMapper0.reader((InjectableValues) injectableValues_Std0);
        JsonParser.Feature jsonParser_Feature0 = JsonParser.Feature.AUTO_CLOSE_SOURCE;
        boolean boolean0 = objectReader0.isEnabled(jsonParser_Feature0);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test4040() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        ObjectReader objectReader0 = objectMapper0.reader();
        JsonNode jsonNode0 = objectReader0.createArrayNode();
        jsonNode0.isInt();
        assertFalse(jsonNode0.isInt());
    }

    @Test(timeout = 4000)
    public void test4141() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        DeserializationFeature deserializationFeature0 = DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT;
        DeserializationFeature[] deserializationFeatureArray0 = new DeserializationFeature[1];
        deserializationFeatureArray0[0] = deserializationFeature0;
        ObjectReader objectReader0 = objectMapper0.reader(deserializationFeature0, deserializationFeatureArray0);
        ObjectReader objectReader1 = objectReader0.with((Base64Variant) null);
    }

    @Test(timeout = 4000)
    public void test4242() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        ObjectReader objectReader0 = objectMapper0.reader();
        DeserializationFeature deserializationFeature0 = DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE;
        ObjectReader objectReader1 = objectReader0.without(deserializationFeature0);
    }

    @Test(timeout = 4000)
    public void test4343() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        ObjectReader objectReader0 = objectMapper0.reader();
        MissingNode missingNode0 = MissingNode.getInstance();
        ObjectReader objectReader1 = objectReader0.withAttribute(objectMapper0, missingNode0);
    }

    @Test(timeout = 4000)
    public void test4444() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        Class<BooleanNode> class0 = BooleanNode.class;
        ObjectReader objectReader0 = objectMapper0.reader((Class<?>) class0);
        ObjectReader objectReader1 = objectReader0.withType((Type) class0);
    }

    @Test(timeout = 4000)
    public void test4545() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        ObjectReader objectReader0 = objectMapper0.reader((JsonNodeFactory) null);
        objectReader0.readValue((InputStream) null);
    }

    @Test(timeout = 4000)
    public void test4646() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        ObjectReader objectReader0 = objectMapper0.reader();
        ObjectReader objectReader1 = objectReader0.withoutAttribute("Can not use source of type ");
    }

    @Test(timeout = 4000)
    public void test4747() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        DeserializationFeature deserializationFeature0 = DeserializationFeature.FAIL_ON_INVALID_SUBTYPE;
        ObjectReader objectReader0 = objectMapper0.reader(deserializationFeature0);
        TimeZone timeZone0 = TimeZone.getDefault();
        ObjectReader objectReader1 = objectReader0.with(timeZone0);
    }

    @Test(timeout = 4000)
    public void test4848() throws Throwable {
        DefaultSerializerProvider.Impl defaultSerializerProvider_Impl0 = new DefaultSerializerProvider.Impl();
        DeserializerFactoryConfig deserializerFactoryConfig0 = new DeserializerFactoryConfig();
        BeanDeserializerFactory beanDeserializerFactory0 = new BeanDeserializerFactory(deserializerFactoryConfig0);
        DefaultDeserializationContext.Impl defaultDeserializationContext_Impl0 = new DefaultDeserializationContext.Impl(beanDeserializerFactory0);
        ObjectMapper objectMapper0 = new ObjectMapper((JsonFactory) null, defaultSerializerProvider_Impl0, defaultDeserializationContext_Impl0);
        ContextAttributes contextAttributes0 = ContextAttributes.Impl.getEmpty();
        ObjectReader objectReader0 = objectMapper0.reader(contextAttributes0);
        DeserializationFeature deserializationFeature0 = DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES;
        DeserializationFeature[] deserializationFeatureArray0 = new DeserializationFeature[2];
        objectReader0.with(deserializationFeature0, deserializationFeatureArray0);
    }

    @Test(timeout = 4000)
    public void test4949() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        DeserializationFeature deserializationFeature0 = DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT;
        DeserializationFeature[] deserializationFeatureArray0 = new DeserializationFeature[1];
        deserializationFeatureArray0[0] = deserializationFeature0;
        ObjectReader objectReader0 = objectMapper0.reader(deserializationFeature0, deserializationFeatureArray0);
        JsonNode jsonNode0 = objectReader0.readTree("0");
        jsonNode0.isDouble();
        assertTrue(jsonNode0.isDouble());
    }

    @Test(timeout = 4000)
    public void test5050() throws Throwable {
        JsonFactory jsonFactory0 = new JsonFactory();
        ObjectMapper objectMapper0 = new ObjectMapper(jsonFactory0);
        ObjectReader objectReader0 = objectMapper0.readerForUpdating((Object) jsonFactory0);
        Version version0 = objectReader0.version();
        version0.isSnapshot();
    }

    @Test(timeout = 4000)
    public void test5151() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        ObjectReader objectReader0 = objectMapper0.reader();
        ContextAttributes contextAttributes0 = objectReader0.getAttributes();
    }

    @Test(timeout = 4000)
    public void test5252() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        ObjectReader objectReader0 = objectMapper0.reader();
        objectReader0.forType((TypeReference<?>) null);
    }

    @Test(timeout = 4000)
    public void test5353() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        DeserializationFeature deserializationFeature0 = DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY;
        ObjectReader objectReader0 = objectMapper0.reader(deserializationFeature0);
        IOContext iOContext0 = new IOContext(bufferRecycler0, deserializationFeature0, true);
        MockPrintStream mockPrintStream0 = new MockPrintStream("f7T>");
        UTF8JsonGenerator uTF8JsonGenerator0 = new UTF8JsonGenerator(iOContext0, (-1030), objectReader0, mockPrintStream0);
        objectReader0.writeValue(uTF8JsonGenerator0, (Object) null);
    }

    @Test(timeout = 4000)
    public void test5454() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        ObjectReader objectReader0 = objectMapper0.reader();
        ObjectReader objectReader1 = objectMapper0.readerForUpdating((Object) objectReader0);
        JsonNodeFactory jsonNodeFactory0 = JsonNodeFactory.instance;
        ObjectNode objectNode0 = new ObjectNode(jsonNodeFactory0);
        Class<MissingNode> class0 = MissingNode.class;
        objectReader1.treeToValue((TreeNode) objectNode0, class0);
    }

    @Test(timeout = 4000)
    public void test5555() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        ObjectReader objectReader0 = objectMapper0.reader();
        InjectableValues.Std injectableValues_Std0 = new InjectableValues.Std();
        ObjectReader objectReader1 = objectReader0.with((InjectableValues) injectableValues_Std0);
    }

    @Test(timeout = 4000)
    public void test5656() throws Throwable {
        JsonFactory jsonFactory0 = new JsonFactory();
        ObjectMapper objectMapper0 = new ObjectMapper(jsonFactory0);
        ObjectReader objectReader0 = objectMapper0.readerForUpdating((Object) jsonFactory0);
        ObjectReader objectReader1 = objectReader0.with(jsonFactory0);
    }

    @Test(timeout = 4000)
    public void test5757() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        ObjectReader objectReader0 = objectMapper0.reader();
        JsonFactory jsonFactory0 = new JsonFactory();
        ObjectMapper objectMapper1 = new ObjectMapper(jsonFactory0);
        ObjectReader objectReader1 = objectReader0.with(jsonFactory0);
    }

    @Test(timeout = 4000)
    public void test5858() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        ObjectReader objectReader0 = objectMapper0.reader();
        ObjectReader objectReader1 = objectReader0.with((FormatSchema) null);
    }

    @Test(timeout = 4000)
    public void test5959() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        ObjectReader objectReader0 = objectMapper0.reader();
        ObjectReader objectReader1 = objectReader0.withValueToUpdate(objectMapper0);
    }

    @Test(timeout = 4000)
    public void test6060() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        Class<DecimalNode> class0 = DecimalNode.class;
        ObjectReader objectReader0 = objectMapper0.readerWithView((Class<?>) class0);
        ObjectReader objectReader1 = objectReader0.withValueToUpdate((Object) null);
    }

    @Test(timeout = 4000)
    public void test6161() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        Class<SimpleType> class0 = SimpleType.class;
        SimpleType simpleType0 = SimpleType.constructUnsafe(class0);
        ObjectReader objectReader0 = objectMapper0.reader((JavaType) simpleType0);
        ObjectReader objectReader1 = objectReader0.withValueToUpdate(class0);
    }

    @Test(timeout = 4000)
    public void test6262() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        ObjectReader objectReader0 = objectMapper0.reader();
        PipedReader pipedReader0 = new PipedReader();
        objectReader0.readValue((Reader) pipedReader0);
    }

    @Test(timeout = 4000)
    public void test6363() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        ObjectReader objectReader0 = objectMapper0.reader();
        objectReader0.readValue("'), but ");
    }

    @Test(timeout = 4000)
    public void test6464() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        DeserializationFeature deserializationFeature0 = DeserializationFeature.FAIL_ON_INVALID_SUBTYPE;
        ObjectReader objectReader0 = objectMapper0.reader(deserializationFeature0);
        byte[] byteArray0 = new byte[1];
        objectReader0.readValue(byteArray0);
    }

    @Test(timeout = 4000)
    public void test6565() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        ObjectReader objectReader0 = objectMapper0.reader();
        objectReader0.readValue((byte[]) null, 1, 1);
    }

    @Test(timeout = 4000)
    public void test6666() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        ObjectReader objectReader0 = objectMapper0.reader();
        objectReader0.readValue((File) null);
    }

    @Test(timeout = 4000)
    public void test6767() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        ObjectReader objectReader0 = objectMapper0.reader();
        URL uRL0 = MockURL.getFileExample();
        objectReader0.readValue(uRL0);
    }

    @Test(timeout = 4000)
    public void test6868() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        ObjectReader objectReader0 = objectMapper0.reader();
        objectReader0.readTree((InputStream) null);
    }

    @Test(timeout = 4000)
    public void test6969() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        ObjectReader objectReader0 = objectMapper0.reader();
        PipedReader pipedReader0 = new PipedReader();
        objectReader0.readTree((Reader) pipedReader0);
    }

    @Test(timeout = 4000)
    public void test7070() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        ObjectReader objectReader0 = objectMapper0.reader();
        objectReader0.readValues((InputStream) null);
    }

    @Test(timeout = 4000)
    public void test7171() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        ObjectReader objectReader0 = objectMapper0.reader();
        PipedReader pipedReader0 = new PipedReader();
        objectReader0.readValues((Reader) pipedReader0);
    }

    @Test(timeout = 4000)
    public void test7272() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        ObjectReader objectReader0 = objectMapper0.reader();
        objectReader0.readValues("");
    }

    @Test(timeout = 4000)
    public void test7373() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        ObjectReader objectReader0 = objectMapper0.reader();
        objectReader0.readValues((File) null);
    }

    @Test(timeout = 4000)
    public void test7474() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        ObjectReader objectReader0 = objectMapper0.reader();
        NullNode nullNode0 = NullNode.instance;
        objectReader0.readValue((JsonNode) nullNode0);
    }

    @Test(timeout = 4000)
    public void test7575() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        DeserializationFeature deserializationFeature0 = DeserializationFeature.UNWRAP_ROOT_VALUE;
        DeserializationFeature[] deserializationFeatureArray0 = new DeserializationFeature[1];
        deserializationFeatureArray0[0] = deserializationFeature0;
        ObjectReader objectReader0 = objectMapper0.reader(deserializationFeature0, deserializationFeatureArray0);
        objectReader0.readTree("0");
    }

    @Test(timeout = 4000)
    public void test7676() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        Class<BooleanNode> class0 = BooleanNode.class;
        objectMapper0.reader((Class<?>) class0);
        DeserializationFeature deserializationFeature0 = DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY;
        ObjectReader objectReader0 = objectMapper0.reader(deserializationFeature0);
        ObjectReader objectReader1 = objectReader0.withType((Type) class0);
    }

    @Test(timeout = 4000)
    public void test7777() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        ObjectReader objectReader0 = objectMapper0.reader();
        byte[] byteArray0 = new byte[7];
        Stack<ObjectReader> stack0 = new Stack<ObjectReader>();
        DataFormatReaders dataFormatReaders0 = new DataFormatReaders(stack0);
        DataFormatReaders.Match dataFormatReaders_Match0 = dataFormatReaders0.findFormat(byteArray0);
        objectReader0._detectBindAndReadValues(dataFormatReaders_Match0, false);
    }

    @Test(timeout = 4000)
    public void test7878() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        ObjectReader objectReader0 = objectMapper0.reader();
        objectReader0._verifySchemaType((FormatSchema) null);
    }
}
