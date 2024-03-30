/*
 * This file was automatically generated by EvoSuite
 * Thu Mar 28 01:04:31 GMT 2024
 */
package com.fasterxml.jackson.databind.deser.std;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.cfg.ContextAttributes;
import com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig;
import com.fasterxml.jackson.databind.deser.BeanDeserializerFactory;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.mock.java.io.MockFileInputStream;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class NumberDeserializers_ESTest extends NumberDeserializers_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        Class<Long> class0 = Long.class;
        Long long0 = new Long(0L);
        NumberDeserializers.LongDeserializer numberDeserializers_LongDeserializer0 = new NumberDeserializers.LongDeserializer(class0, long0);
        boolean boolean0 = numberDeserializers_LongDeserializer0.isCachable();
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        NumberDeserializers.BigDecimalDeserializer numberDeserializers_BigDecimalDeserializer0 = new NumberDeserializers.BigDecimalDeserializer();
        numberDeserializers_BigDecimalDeserializer0.isCachable();
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        NumberDeserializers.IntegerDeserializer numberDeserializers_IntegerDeserializer0 = NumberDeserializers.IntegerDeserializer.wrapperInstance;
        boolean boolean0 = numberDeserializers_IntegerDeserializer0.isCachable();
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        Class<Integer> class0 = Integer.class;
        NumberDeserializers.IntegerDeserializer numberDeserializers_IntegerDeserializer0 = new NumberDeserializers.IntegerDeserializer(class0, (Integer) null);
        numberDeserializers_IntegerDeserializer0.isCachable();
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        NumberDeserializers numberDeserializers0 = new NumberDeserializers();
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        Class<Character> class0 = Character.class;
        Character character0 = Character.valueOf('f');
        NumberDeserializers.CharacterDeserializer numberDeserializers_CharacterDeserializer0 = new NumberDeserializers.CharacterDeserializer(class0, character0);
        numberDeserializers_CharacterDeserializer0.isCachable();
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        Class<Short> class0 = Short.class;
        Short short0 = new Short((short) (-3647));
        NumberDeserializers.ShortDeserializer numberDeserializers_ShortDeserializer0 = new NumberDeserializers.ShortDeserializer(class0, short0);
        numberDeserializers_ShortDeserializer0.isCachable();
    }

    @Test(timeout = 4000)
    public void test077() throws Throwable {
        NumberDeserializers.ShortDeserializer numberDeserializers_ShortDeserializer0 = NumberDeserializers.ShortDeserializer.primitiveInstance;
        JsonFactory jsonFactory0 = new JsonFactory();
        char[] charArray0 = new char[2];
        JsonParser jsonParser0 = jsonFactory0.createParser(charArray0, (int) 'a', (-3));
        BeanDeserializerFactory beanDeserializerFactory0 = BeanDeserializerFactory.instance;
        DefaultDeserializationContext.Impl defaultDeserializationContext_Impl0 = new DefaultDeserializationContext.Impl(beanDeserializerFactory0);
        numberDeserializers_ShortDeserializer0.deserialize(jsonParser0, (DeserializationContext) defaultDeserializationContext_Impl0);
    }

    @Test(timeout = 4000)
    public void test088() throws Throwable {
        Class<Byte> class0 = Byte.class;
        Byte byte0 = new Byte((byte) 125);
        NumberDeserializers.ByteDeserializer numberDeserializers_ByteDeserializer0 = new NumberDeserializers.ByteDeserializer(class0, byte0);
        numberDeserializers_ByteDeserializer0.isCachable();
    }

    @Test(timeout = 4000)
    public void test099() throws Throwable {
        NumberDeserializers.ByteDeserializer numberDeserializers_ByteDeserializer0 = NumberDeserializers.ByteDeserializer.wrapperInstance;
        DeserializerFactoryConfig deserializerFactoryConfig0 = new DeserializerFactoryConfig();
        BeanDeserializerFactory beanDeserializerFactory0 = new BeanDeserializerFactory(deserializerFactoryConfig0);
        DefaultDeserializationContext.Impl defaultDeserializationContext_Impl0 = new DefaultDeserializationContext.Impl(beanDeserializerFactory0);
        numberDeserializers_ByteDeserializer0.deserialize((JsonParser) null, (DeserializationContext) defaultDeserializationContext_Impl0);
    }

    @Test(timeout = 4000)
    public void test1010() throws Throwable {
        NumberDeserializers.BooleanDeserializer numberDeserializers_BooleanDeserializer0 = NumberDeserializers.BooleanDeserializer.primitiveInstance;
        JsonNodeFactory jsonNodeFactory0 = JsonNodeFactory.withExactBigDecimals(false);
        ArrayNode arrayNode0 = jsonNodeFactory0.arrayNode();
        JsonFactory jsonFactory0 = new JsonFactory();
        ObjectMapper objectMapper0 = new ObjectMapper(jsonFactory0);
        ContextAttributes contextAttributes0 = ContextAttributes.getEmpty();
        ObjectReader objectReader0 = objectMapper0.reader(contextAttributes0);
        JsonParser jsonParser0 = arrayNode0.traverse((ObjectCodec) objectReader0);
        BeanDeserializerFactory beanDeserializerFactory0 = BeanDeserializerFactory.instance;
        DefaultDeserializationContext.Impl defaultDeserializationContext_Impl0 = new DefaultDeserializationContext.Impl(beanDeserializerFactory0);
        numberDeserializers_BooleanDeserializer0.deserializeWithType(jsonParser0, defaultDeserializationContext_Impl0, (TypeDeserializer) null);
    }

    @Test(timeout = 4000)
    public void test1111() throws Throwable {
        Class<Boolean> class0 = Boolean.TYPE;
        Boolean boolean0 = Boolean.valueOf("com.fasterxml.jackson.databind.deser.std.NumberDeserializers$CharacterDeserializer");
        NumberDeserializers.BooleanDeserializer numberDeserializers_BooleanDeserializer0 = new NumberDeserializers.BooleanDeserializer(class0, boolean0);
        numberDeserializers_BooleanDeserializer0.isCachable();
    }

    @Test(timeout = 4000)
    public void test1212() throws Throwable {
        NumberDeserializers.BooleanDeserializer numberDeserializers_BooleanDeserializer0 = NumberDeserializers.BooleanDeserializer.primitiveInstance;
        JsonFactory jsonFactory0 = new JsonFactory();
        char[] charArray0 = new char[7];
        JsonParser jsonParser0 = jsonFactory0.createParser(charArray0, 1624, 0);
        numberDeserializers_BooleanDeserializer0.deserialize(jsonParser0, (DeserializationContext) null);
    }

    @Test(timeout = 4000)
    public void test1313() throws Throwable {
        Class<Float> class0 = Float.TYPE;
        Float float0 = new Float(0.0);
        NumberDeserializers.FloatDeserializer numberDeserializers_FloatDeserializer0 = new NumberDeserializers.FloatDeserializer(class0, float0);
        numberDeserializers_FloatDeserializer0.isCachable();
    }

    @Test(timeout = 4000)
    public void test1414() throws Throwable {
        NumberDeserializers.FloatDeserializer numberDeserializers_FloatDeserializer0 = NumberDeserializers.FloatDeserializer.wrapperInstance;
        JsonFactory jsonFactory0 = new JsonFactory();
        char[] charArray0 = new char[5];
        JsonParser jsonParser0 = jsonFactory0.createParser(charArray0, (-978), 7);
        ObjectMapper objectMapper0 = new ObjectMapper();
        DeserializationContext deserializationContext0 = objectMapper0.getDeserializationContext();
        numberDeserializers_FloatDeserializer0.deserialize(jsonParser0, deserializationContext0);
    }

    @Test(timeout = 4000)
    public void test1515() throws Throwable {
        NumberDeserializers.DoubleDeserializer numberDeserializers_DoubleDeserializer0 = NumberDeserializers.DoubleDeserializer.primitiveInstance;
        JsonNodeFactory jsonNodeFactory0 = JsonNodeFactory.withExactBigDecimals(true);
        ArrayNode arrayNode0 = new ArrayNode(jsonNodeFactory0);
        JsonParser jsonParser0 = arrayNode0.traverse();
        DeserializerFactoryConfig deserializerFactoryConfig0 = new DeserializerFactoryConfig();
        BeanDeserializerFactory beanDeserializerFactory0 = new BeanDeserializerFactory(deserializerFactoryConfig0);
        DefaultDeserializationContext.Impl defaultDeserializationContext_Impl0 = new DefaultDeserializationContext.Impl(beanDeserializerFactory0);
        numberDeserializers_DoubleDeserializer0.deserialize(jsonParser0, (DeserializationContext) defaultDeserializationContext_Impl0);
    }

    @Test(timeout = 4000)
    public void test1616() throws Throwable {
        BeanDeserializerFactory beanDeserializerFactory0 = BeanDeserializerFactory.instance;
        DefaultDeserializationContext.Impl defaultDeserializationContext_Impl0 = new DefaultDeserializationContext.Impl(beanDeserializerFactory0);
        Class<Double> class0 = Double.class;
        Double double0 = new Double(999.0);
        NumberDeserializers.DoubleDeserializer numberDeserializers_DoubleDeserializer0 = new NumberDeserializers.DoubleDeserializer(class0, double0);
        numberDeserializers_DoubleDeserializer0.deserializeWithType((JsonParser) null, defaultDeserializationContext_Impl0, (TypeDeserializer) null);
    }

    @Test(timeout = 4000)
    public void test1717() throws Throwable {
        JsonFactory jsonFactory0 = new JsonFactory();
        NumberDeserializers.IntegerDeserializer numberDeserializers_IntegerDeserializer0 = NumberDeserializers.IntegerDeserializer.wrapperInstance;
        ObjectMapper objectMapper0 = new ObjectMapper(jsonFactory0);
        ObjectReader objectReader0 = objectMapper0.readerForUpdating(numberDeserializers_IntegerDeserializer0);
    }

    @Test(timeout = 4000)
    public void test1818() throws Throwable {
        Class<Float> class0 = Float.TYPE;
        JsonDeserializer<?> jsonDeserializer0 = NumberDeserializers.find(class0, "x");
    }

    @Test(timeout = 4000)
    public void test1819() throws Throwable {
        Class<Float> class0 = Float.TYPE;
        JsonDeserializer<?> jsonDeserializer0 = NumberDeserializers.find(class0, "x");
        jsonDeserializer0.isCachable();
    }

    @Test(timeout = 4000)
    public void test1920() throws Throwable {
        Class<Integer> class0 = Integer.TYPE;
        JsonDeserializer<?> jsonDeserializer0 = NumberDeserializers.find(class0, "java.math.BigInteger");
        jsonDeserializer0.isCachable();
    }

    @Test(timeout = 4000)
    public void test2021() throws Throwable {
        Class<Boolean> class0 = Boolean.TYPE;
        JsonDeserializer<?> jsonDeserializer0 = NumberDeserializers.find(class0, "java.math.BigInteger");
        jsonDeserializer0.isCachable();
    }

    @Test(timeout = 4000)
    public void test2122() throws Throwable {
        Class<Long> class0 = Long.TYPE;
        JsonDeserializer<?> jsonDeserializer0 = NumberDeserializers.find(class0, "java.mth.BigInteger");
        jsonDeserializer0.isCachable();
    }

    @Test(timeout = 4000)
    public void test2223() throws Throwable {
        Class<Double> class0 = Double.TYPE;
        JsonDeserializer<?> jsonDeserializer0 = NumberDeserializers.find(class0, "java.|ath.BigInteger");
        jsonDeserializer0.isCachable();
    }

    @Test(timeout = 4000)
    public void test2324() throws Throwable {
        Class<Character> class0 = Character.TYPE;
        JsonDeserializer<?> jsonDeserializer0 = NumberDeserializers.find(class0, (String) null);
        jsonDeserializer0.isCachable();
    }

    @Test(timeout = 4000)
    public void test2425() throws Throwable {
        Class<Byte> class0 = Byte.TYPE;
        JsonDeserializer<?> jsonDeserializer0 = NumberDeserializers.find(class0, "java.math.BigInteger");
        jsonDeserializer0.isCachable();
    }

    @Test(timeout = 4000)
    public void test2526() throws Throwable {
        Class<Short> class0 = Short.TYPE;
        JsonDeserializer<?> jsonDeserializer0 = NumberDeserializers.find(class0, (String) null);
        jsonDeserializer0.isCachable();
    }

    @Test(timeout = 4000)
    public void test2627() throws Throwable {
        JsonFactory jsonFactory0 = new JsonFactory();
        ObjectMapper objectMapper0 = new ObjectMapper(jsonFactory0);
        ContextAttributes contextAttributes0 = ContextAttributes.getEmpty();
        ObjectReader objectReader0 = objectMapper0.reader(contextAttributes0);
        Class<MockFileInputStream> class0 = MockFileInputStream.class;
        ObjectReader objectReader1 = objectReader0.forType(class0);
    }

    @Test(timeout = 4000)
    public void test2728() throws Throwable {
        Class<Integer> class0 = Integer.class;
        JsonDeserializer<?> jsonDeserializer0 = NumberDeserializers.find(class0, "java.math.BigInteger");
        jsonDeserializer0.isCachable();
    }

    @Test(timeout = 4000)
    public void test2829() throws Throwable {
        Class<Boolean> class0 = Boolean.class;
        JsonDeserializer<?> jsonDeserializer0 = NumberDeserializers.find(class0, "java.math.BigInteger");
        jsonDeserializer0.isCachable();
    }

    @Test(timeout = 4000)
    public void test2930() throws Throwable {
        Class<Long> class0 = Long.class;
        JsonDeserializer<?> jsonDeserializer0 = NumberDeserializers.find(class0, "java.math.BigInteger");
        jsonDeserializer0.isCachable();
    }

    @Test(timeout = 4000)
    public void test3031() throws Throwable {
        Class<Double> class0 = Double.class;
        JsonDeserializer<?> jsonDeserializer0 = NumberDeserializers.find(class0, "java.math.BigInteger");
        jsonDeserializer0.isCachable();
    }

    @Test(timeout = 4000)
    public void test3132() throws Throwable {
        Class<Character> class0 = Character.class;
        JsonDeserializer<?> jsonDeserializer0 = NumberDeserializers.find(class0, "java.math.BigInteger");
        jsonDeserializer0.isCachable();
    }

    @Test(timeout = 4000)
    public void test3233() throws Throwable {
        Class<Byte> class0 = Byte.class;
        JsonDeserializer<?> jsonDeserializer0 = NumberDeserializers.find(class0, "java.math.BigInteger");
        jsonDeserializer0.isCachable();
    }

    @Test(timeout = 4000)
    public void test3334() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        Class<Short> class0 = Short.class;
        ObjectReader objectReader0 = objectMapper0.readerFor(class0);
    }

    @Test(timeout = 4000)
    public void test3435() throws Throwable {
        Class<Float> class0 = Float.class;
        JsonDeserializer<?> jsonDeserializer0 = NumberDeserializers.find(class0, "java.math.BigInteger");
        jsonDeserializer0.isCachable();
    }

    @Test(timeout = 4000)
    public void test3536() throws Throwable {
        Class<BigInteger> class0 = BigInteger.class;
        JsonDeserializer<?> jsonDeserializer0 = NumberDeserializers.find(class0, "java.math.BigInteger");
        jsonDeserializer0.isCachable();
    }

    @Test(timeout = 4000)
    public void test3637() throws Throwable {
        Class<BigDecimal> class0 = BigDecimal.class;
        JsonDeserializer<?> jsonDeserializer0 = NumberDeserializers.find(class0, "java.math.BigInteger");
        jsonDeserializer0.isCachable();
    }

    @Test(timeout = 4000)
    public void test3738() throws Throwable {
        Class<String> class0 = String.class;
        NumberDeserializers.find(class0, "java.math.BigInteger");
    }

    @Test(timeout = 4000)
    public void test3839() throws Throwable {
        NumberDeserializers.CharacterDeserializer numberDeserializers_CharacterDeserializer0 = NumberDeserializers.CharacterDeserializer.wrapperInstance;
        Character character0 = numberDeserializers_CharacterDeserializer0.getNullValue((DeserializationContext) null);
    }

    @Test(timeout = 4000)
    public void test3940() throws Throwable {
        BeanDeserializerFactory beanDeserializerFactory0 = BeanDeserializerFactory.instance;
        DefaultDeserializationContext.Impl defaultDeserializationContext_Impl0 = new DefaultDeserializationContext.Impl(beanDeserializerFactory0);
        NumberDeserializers.CharacterDeserializer numberDeserializers_CharacterDeserializer0 = NumberDeserializers.CharacterDeserializer.primitiveInstance;
        Character character0 = numberDeserializers_CharacterDeserializer0.getNullValue((DeserializationContext) defaultDeserializationContext_Impl0);
    }

    @Test(timeout = 4000)
    public void test4041() throws Throwable {
        JsonFactory jsonFactory0 = new JsonFactory();
        JsonParser jsonParser0 = jsonFactory0.createParser("JSON");
        NumberDeserializers.CharacterDeserializer numberDeserializers_CharacterDeserializer0 = NumberDeserializers.CharacterDeserializer.primitiveInstance;
        numberDeserializers_CharacterDeserializer0.deserialize(jsonParser0, (DeserializationContext) null);
    }

    @Test(timeout = 4000)
    public void test4142() throws Throwable {
        JsonFactory jsonFactory0 = new JsonFactory();
        JsonParser jsonParser0 = jsonFactory0.createParser("JSON");
        NumberDeserializers.IntegerDeserializer numberDeserializers_IntegerDeserializer0 = NumberDeserializers.IntegerDeserializer.primitiveInstance;
        numberDeserializers_IntegerDeserializer0.deserialize(jsonParser0, (DeserializationContext) null);
    }

    @Test(timeout = 4000)
    public void test4243() throws Throwable {
        JsonFactory jsonFactory0 = new JsonFactory();
        JsonParser jsonParser0 = jsonFactory0.createParser("JSON");
        NumberDeserializers.IntegerDeserializer numberDeserializers_IntegerDeserializer0 = NumberDeserializers.IntegerDeserializer.primitiveInstance;
        numberDeserializers_IntegerDeserializer0.deserializeWithType(jsonParser0, (DeserializationContext) null, (TypeDeserializer) null);
    }

    @Test(timeout = 4000)
    public void test4344() throws Throwable {
        JsonFactory jsonFactory0 = new JsonFactory();
        JsonParser jsonParser0 = jsonFactory0.createParser("JSON");
        BeanDeserializerFactory beanDeserializerFactory0 = BeanDeserializerFactory.instance;
        DefaultDeserializationContext.Impl defaultDeserializationContext_Impl0 = new DefaultDeserializationContext.Impl(beanDeserializerFactory0);
        NumberDeserializers.LongDeserializer numberDeserializers_LongDeserializer0 = NumberDeserializers.LongDeserializer.wrapperInstance;
        numberDeserializers_LongDeserializer0.deserialize(jsonParser0, (DeserializationContext) defaultDeserializationContext_Impl0);
    }

    @Test(timeout = 4000)
    public void test4445() throws Throwable {
        JsonFactory jsonFactory0 = new JsonFactory();
        JsonParser jsonParser0 = jsonFactory0.createParser("JSON");
        NumberDeserializers.NumberDeserializer numberDeserializers_NumberDeserializer0 = NumberDeserializers.NumberDeserializer.instance;
        numberDeserializers_NumberDeserializer0.deserialize(jsonParser0, (DeserializationContext) null);
    }

    @Test(timeout = 4000)
    public void test4546() throws Throwable {
        JsonFactory jsonFactory0 = new JsonFactory();
        JsonParser jsonParser0 = jsonFactory0.createParser("JSON");
        NumberDeserializers.NumberDeserializer numberDeserializers_NumberDeserializer0 = new NumberDeserializers.NumberDeserializer();
        numberDeserializers_NumberDeserializer0.deserializeWithType(jsonParser0, (DeserializationContext) null, (TypeDeserializer) null);
    }

    @Test(timeout = 4000)
    public void test4647() throws Throwable {
        JsonFactory jsonFactory0 = new JsonFactory();
        JsonParser jsonParser0 = jsonFactory0.createParser("JSON");
        NumberDeserializers.BigIntegerDeserializer numberDeserializers_BigIntegerDeserializer0 = new NumberDeserializers.BigIntegerDeserializer();
        numberDeserializers_BigIntegerDeserializer0.deserialize(jsonParser0, (DeserializationContext) null);
    }

    @Test(timeout = 4000)
    public void test4748() throws Throwable {
        JsonFactory jsonFactory0 = new JsonFactory();
        JsonParser jsonParser0 = jsonFactory0.createParser("JSON");
        NumberDeserializers.BigDecimalDeserializer numberDeserializers_BigDecimalDeserializer0 = NumberDeserializers.BigDecimalDeserializer.instance;
        numberDeserializers_BigDecimalDeserializer0.deserialize(jsonParser0, (DeserializationContext) null);
    }
}
