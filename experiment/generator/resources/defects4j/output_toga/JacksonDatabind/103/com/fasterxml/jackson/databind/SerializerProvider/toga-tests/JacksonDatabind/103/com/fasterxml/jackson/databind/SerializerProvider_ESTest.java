/*
 * This file was automatically generated by EvoSuite
 * Thu Mar 28 15:30:00 GMT 2024
 */
package com.fasterxml.jackson.databind;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.BasicBeanDescription;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.introspect.POJOPropertiesCollector;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializer;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import java.io.IOException;
import java.sql.ClientInfoStatus;
import java.sql.SQLNonTransientConnectionException;
import java.sql.SQLTransientConnectionException;
import java.sql.SQLTransientException;
import java.sql.SQLWarning;
import java.text.DateFormat;
import java.time.chrono.ChronoLocalDate;
import java.util.Date;
import java.util.UnknownFormatConversionException;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.evosuite.runtime.mock.java.text.MockDateFormat;
import org.evosuite.runtime.mock.java.text.MockSimpleDateFormat;
import org.evosuite.runtime.mock.java.util.MockDate;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class SerializerProvider_ESTest extends SerializerProvider_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        SerializerProvider serializerProvider0 = objectMapper0.getSerializerProviderInstance();
        serializerProvider0.findValueSerializer((JavaType) null, (BeanProperty) null);
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        DefaultSerializerProvider.Impl defaultSerializerProvider_Impl0 = new DefaultSerializerProvider.Impl();
        defaultSerializerProvider_Impl0.getLocale();
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        SerializerProvider serializerProvider0 = objectMapper0.getSerializerProviderInstance();
        serializerProvider0.findTypeSerializer((JavaType) null);
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        DefaultSerializerProvider.Impl defaultSerializerProvider_Impl0 = new DefaultSerializerProvider.Impl();
        MockDateFormat mockDateFormat0 = new MockDateFormat();
        defaultSerializerProvider_Impl0.setAttribute(mockDateFormat0, mockDateFormat0);
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        DefaultSerializerProvider.Impl defaultSerializerProvider_Impl0 = (DefaultSerializerProvider.Impl) objectMapper0.getSerializerProviderInstance();
        SQLTransientException sQLTransientException0 = new SQLTransientException();
        Object[] objectArray0 = new Object[9];
        ((SerializerProvider) defaultSerializerProvider_Impl0).mappingException((Throwable) sQLTransientException0, (String) null, objectArray0);
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        SerializerProvider serializerProvider0 = objectMapper0.getSerializerProviderInstance();
        Class<String> class0 = String.class;
        JsonInclude.Value jsonInclude_Value0 = serializerProvider0.getDefaultPropertyInclusion(class0);
        jsonInclude_Value0.getContentInclusion();
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        SerializerProvider serializerProvider0 = objectMapper0.getSerializerProviderInstance();
        boolean boolean0 = serializerProvider0.hasSerializationFeatures(1968);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test077() throws Throwable {
        DefaultSerializerProvider.Impl defaultSerializerProvider_Impl0 = new DefaultSerializerProvider.Impl();
        Object object0 = new Object();
        defaultSerializerProvider_Impl0.getAttribute(object0);
    }

    @Test(timeout = 4000)
    public void test088() throws Throwable {
        DefaultSerializerProvider.Impl defaultSerializerProvider_Impl0 = new DefaultSerializerProvider.Impl();
        MapperFeature mapperFeature0 = MapperFeature.SORT_PROPERTIES_ALPHABETICALLY;
        defaultSerializerProvider_Impl0.isEnabled(mapperFeature0);
    }

    @Test(timeout = 4000)
    public void test099() throws Throwable {
        DefaultSerializerProvider.Impl defaultSerializerProvider_Impl0 = new DefaultSerializerProvider.Impl();
        Class<BeanSerializer> class0 = BeanSerializer.class;
        SQLWarning sQLWarning0 = new SQLWarning();
        defaultSerializerProvider_Impl0.reportBadDefinition((Class<?>) class0, "Null passed for `valueType` of `findValueSerializer()`", (Throwable) sQLWarning0);
    }

    @Test(timeout = 4000)
    public void test1010() throws Throwable {
        DefaultSerializerProvider.Impl defaultSerializerProvider_Impl0 = new DefaultSerializerProvider.Impl();
        defaultSerializerProvider_Impl0.resolveSubType((JavaType) null, "-4");
    }

    @Test(timeout = 4000)
    public void test1111() throws Throwable {
        DefaultSerializerProvider.Impl defaultSerializerProvider_Impl0 = new DefaultSerializerProvider.Impl();
        SQLTransientException sQLTransientException0 = new SQLTransientException("");
        SQLTransientConnectionException sQLTransientConnectionException0 = new SQLTransientConnectionException("? w#)8Lk..:qLn@X_#<", sQLTransientException0);
        defaultSerializerProvider_Impl0.reportBadDefinition((JavaType) null, "? w#)8Lk..:qLn@X_#<", (Throwable) sQLTransientConnectionException0);
    }

    @Test(timeout = 4000)
    public void test1212() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        SerializerProvider serializerProvider0 = objectMapper0.getSerializerProviderInstance();
        Class<DateFormat> class0 = DateFormat.class;
        BeanProperty.Bogus beanProperty_Bogus0 = new BeanProperty.Bogus();
        JsonSerializer<Object> jsonSerializer0 = serializerProvider0.findKeySerializer((Class<?>) class0, (BeanProperty) beanProperty_Bogus0);
        jsonSerializer0.usesObjectId();
        assertFalse(jsonSerializer0.usesObjectId());
    }

    @Test(timeout = 4000)
    public void test1313() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        DefaultSerializerProvider.Impl defaultSerializerProvider_Impl0 = (DefaultSerializerProvider.Impl) objectMapper0.getSerializerProviderInstance();
        DefaultSerializerProvider.Impl defaultSerializerProvider_Impl1 = new DefaultSerializerProvider.Impl(defaultSerializerProvider_Impl0);
        defaultSerializerProvider_Impl1.cachedSerializersCount();
        assertEquals(1, defaultSerializerProvider_Impl1.cachedSerializersCount());
    }

    @Test(timeout = 4000)
    public void test1414() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        SerializerProvider serializerProvider0 = objectMapper0.getSerializerProviderInstance();
        Class<Object> class0 = Object.class;
        SimpleType simpleType0 = SimpleType.constructUnsafe(class0);
        BeanProperty.Bogus beanProperty_Bogus0 = new BeanProperty.Bogus();
        JsonSerializer<Object> jsonSerializer0 = serializerProvider0.findNullKeySerializer(simpleType0, beanProperty_Bogus0);
        jsonSerializer0.isUnwrappingSerializer();
        assertFalse(jsonSerializer0.isUnwrappingSerializer());
    }

    @Test(timeout = 4000)
    public void test1515() throws Throwable {
        DefaultSerializerProvider.Impl defaultSerializerProvider_Impl0 = new DefaultSerializerProvider.Impl();
        Class<?> class0 = defaultSerializerProvider_Impl0.getSerializationView();
        assertNotNull(class0);
    }

    @Test(timeout = 4000)
    public void test1616() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        SerializerProvider serializerProvider0 = objectMapper0.getSerializerProviderInstance();
        Class<?> class0 = serializerProvider0.getActiveView();
        assertNotNull(class0);
    }

    @Test(timeout = 4000)
    public void test1717() throws Throwable {
        DefaultSerializerProvider.Impl defaultSerializerProvider_Impl0 = new DefaultSerializerProvider.Impl();
        JsonSerializer<Object> jsonSerializer0 = defaultSerializerProvider_Impl0.getDefaultNullValueSerializer();
        jsonSerializer0.usesObjectId();
    }

    @Test(timeout = 4000)
    public void test1818() throws Throwable {
        DefaultSerializerProvider.Impl defaultSerializerProvider_Impl0 = new DefaultSerializerProvider.Impl();
        defaultSerializerProvider_Impl0.canOverrideAccessModifiers();
    }

    @Test(timeout = 4000)
    public void test1919() throws Throwable {
        DefaultSerializerProvider.Impl defaultSerializerProvider_Impl0 = new DefaultSerializerProvider.Impl();
        JsonSerializer<Object> jsonSerializer0 = defaultSerializerProvider_Impl0.getDefaultNullKeySerializer();
        jsonSerializer0.usesObjectId();
    }

    @Test(timeout = 4000)
    public void test2020() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        SerializerProvider serializerProvider0 = objectMapper0.getSerializerProviderInstance();
        SQLNonTransientConnectionException sQLNonTransientConnectionException0 = new SQLNonTransientConnectionException();
        Object[] objectArray0 = new Object[0];
        serializerProvider0.reportMappingProblem((Throwable) sQLNonTransientConnectionException0, "", objectArray0);
    }

    @Test(timeout = 4000)
    public void test2121() throws Throwable {
        DefaultSerializerProvider.Impl defaultSerializerProvider_Impl0 = new DefaultSerializerProvider.Impl();
        defaultSerializerProvider_Impl0.getTimeZone();
    }

    @Test(timeout = 4000)
    public void test2222() throws Throwable {
        DefaultSerializerProvider.Impl defaultSerializerProvider_Impl0 = new DefaultSerializerProvider.Impl();
        defaultSerializerProvider_Impl0.getFilterProvider();
    }

    @Test(timeout = 4000)
    public void test2323() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        SerializerProvider serializerProvider0 = objectMapper0.getSerializerProviderInstance();
        serializerProvider0.setDefaultKeySerializer(serializerProvider0.DEFAULT_NULL_KEY_SERIALIZER);
        serializerProvider0.canOverrideAccessModifiers();
        assertTrue(serializerProvider0.canOverrideAccessModifiers());
    }

    @Test(timeout = 4000)
    public void test2424() throws Throwable {
        DefaultSerializerProvider.Impl defaultSerializerProvider_Impl0 = new DefaultSerializerProvider.Impl();
        defaultSerializerProvider_Impl0.setDefaultKeySerializer((JsonSerializer<Object>) null);
    }

    @Test(timeout = 4000)
    public void test2525() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        SerializerProvider serializerProvider0 = objectMapper0.getSerializerProviderInstance();
        serializerProvider0.setNullValueSerializer(serializerProvider0.DEFAULT_NULL_KEY_SERIALIZER);
        serializerProvider0.canOverrideAccessModifiers();
        assertTrue(serializerProvider0.canOverrideAccessModifiers());
    }

    @Test(timeout = 4000)
    public void test2626() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        SerializerProvider serializerProvider0 = objectMapper0.getSerializerProviderInstance();
        serializerProvider0.setNullValueSerializer((JsonSerializer<Object>) null);
    }

    @Test(timeout = 4000)
    public void test2727() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        SerializerProvider serializerProvider0 = objectMapper0.getSerializerProviderInstance();
        serializerProvider0.setNullKeySerializer(serializerProvider0.DEFAULT_NULL_KEY_SERIALIZER);
        serializerProvider0.canOverrideAccessModifiers();
        assertTrue(serializerProvider0.canOverrideAccessModifiers());
    }

    @Test(timeout = 4000)
    public void test2828() throws Throwable {
        DefaultSerializerProvider.Impl defaultSerializerProvider_Impl0 = new DefaultSerializerProvider.Impl();
        defaultSerializerProvider_Impl0.setNullKeySerializer((JsonSerializer<Object>) null);
    }

    @Test(timeout = 4000)
    public void test2929() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        DefaultSerializerProvider.Impl defaultSerializerProvider_Impl0 = (DefaultSerializerProvider.Impl) objectMapper0.getSerializerProviderInstance();
        Class<ObjectIdGenerators.UUIDGenerator> class0 = ObjectIdGenerators.UUIDGenerator.class;
        defaultSerializerProvider_Impl0.findValueSerializer(class0);
        defaultSerializerProvider_Impl0.findTypedValueSerializer(class0, false, (BeanProperty) null);
        defaultSerializerProvider_Impl0.cachedSerializersCount();
        assertEquals(1, defaultSerializerProvider_Impl0.cachedSerializersCount());
    }

    @Test(timeout = 4000)
    public void test3030() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        DefaultSerializerProvider.Impl defaultSerializerProvider_Impl0 = (DefaultSerializerProvider.Impl) objectMapper0.getSerializerProviderInstance();
        Class<BeanSerializer> class0 = BeanSerializer.class;
        SimpleType simpleType0 = SimpleType.constructUnsafe(class0);
        BeanProperty.Bogus beanProperty_Bogus0 = new BeanProperty.Bogus();
        defaultSerializerProvider_Impl0.findPrimaryPropertySerializer((JavaType) simpleType0, (BeanProperty) beanProperty_Bogus0);
        defaultSerializerProvider_Impl0.findValueSerializer((Class<?>) class0, (BeanProperty) beanProperty_Bogus0);
        defaultSerializerProvider_Impl0.cachedSerializersCount();
        assertEquals(1, defaultSerializerProvider_Impl0.cachedSerializersCount());
    }

    @Test(timeout = 4000)
    public void test3131() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        SerializerProvider serializerProvider0 = objectMapper0.getSerializerProviderInstance();
        Class<SettableBeanProperty> class0 = SettableBeanProperty.class;
        serializerProvider0.findValueSerializer(class0);
        objectMapper0.updateValue((Object) serializerProvider0, (Object) serializerProvider0);
    }

    @Test(timeout = 4000)
    public void test3232() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        Class<PropertyName> class0 = PropertyName.class;
        boolean boolean0 = objectMapper0.canSerialize(class0);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test3233() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        Class<PropertyName> class0 = PropertyName.class;
        boolean boolean0 = objectMapper0.canSerialize(class0);
        DefaultSerializerProvider.Impl defaultSerializerProvider_Impl0 = (DefaultSerializerProvider.Impl) objectMapper0.getSerializerProviderInstance();
        defaultSerializerProvider_Impl0.findValueSerializer(class0);
        defaultSerializerProvider_Impl0.cachedSerializersCount();
        assertEquals(1, defaultSerializerProvider_Impl0.cachedSerializersCount());
    }

    @Test(timeout = 4000)
    public void test3334() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        DefaultSerializerProvider.Impl defaultSerializerProvider_Impl0 = (DefaultSerializerProvider.Impl) objectMapper0.getSerializerProviderInstance();
        Class<TypeIdResolver> class0 = TypeIdResolver.class;
        defaultSerializerProvider_Impl0.findValueSerializer(class0);
        defaultSerializerProvider_Impl0.findValueSerializer(class0);
        defaultSerializerProvider_Impl0.cachedSerializersCount();
        assertEquals(1, defaultSerializerProvider_Impl0.cachedSerializersCount());
    }

    @Test(timeout = 4000)
    public void test3435() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        DefaultSerializerProvider.Impl defaultSerializerProvider_Impl0 = (DefaultSerializerProvider.Impl) objectMapper0.getSerializerProviderInstance();
        Class<MockSimpleDateFormat> class0 = MockSimpleDateFormat.class;
        SimpleType simpleType0 = SimpleType.constructUnsafe(class0);
        defaultSerializerProvider_Impl0.findValueSerializer((JavaType) simpleType0);
        defaultSerializerProvider_Impl0.findValueSerializer(class0);
        defaultSerializerProvider_Impl0.cachedSerializersCount();
        assertEquals(1, defaultSerializerProvider_Impl0.cachedSerializersCount());
    }

    @Test(timeout = 4000)
    public void test3536() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        DefaultSerializerProvider.Impl defaultSerializerProvider_Impl0 = (DefaultSerializerProvider.Impl) objectMapper0.getSerializerProviderInstance();
        SimpleType simpleType0 = (SimpleType) TypeFactory.unknownType();
        defaultSerializerProvider_Impl0.findValueSerializer((JavaType) simpleType0);
        defaultSerializerProvider_Impl0.findValueSerializer((JavaType) simpleType0);
        defaultSerializerProvider_Impl0.cachedSerializersCount();
        assertEquals(1, defaultSerializerProvider_Impl0.cachedSerializersCount());
    }

    @Test(timeout = 4000)
    public void test3637() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        DefaultSerializerProvider.Impl defaultSerializerProvider_Impl0 = (DefaultSerializerProvider.Impl) objectMapper0.getSerializerProviderInstance();
        SimpleType simpleType0 = (SimpleType) TypeFactory.unknownType();
        defaultSerializerProvider_Impl0.findPrimaryPropertySerializer((JavaType) simpleType0, (BeanProperty) null);
        defaultSerializerProvider_Impl0.findPrimaryPropertySerializer((JavaType) simpleType0, (BeanProperty) null);
        defaultSerializerProvider_Impl0.cachedSerializersCount();
        assertEquals(1, defaultSerializerProvider_Impl0.cachedSerializersCount());
    }

    @Test(timeout = 4000)
    public void test3739() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        Class<PropertyName> class0 = PropertyName.class;
        boolean boolean0 = objectMapper0.canSerialize(class0);
        DefaultSerializerProvider.Impl defaultSerializerProvider_Impl0 = (DefaultSerializerProvider.Impl) objectMapper0.getSerializerProviderInstance();
        defaultSerializerProvider_Impl0.findPrimaryPropertySerializer(class0, (BeanProperty) null);
        defaultSerializerProvider_Impl0.cachedSerializersCount();
    }

    @Test(timeout = 4000)
    public void test3840() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        DefaultSerializerProvider.Impl defaultSerializerProvider_Impl0 = (DefaultSerializerProvider.Impl) objectMapper0.getSerializerProviderInstance();
        Class<SettableBeanProperty> class0 = SettableBeanProperty.class;
        ((SerializerProvider) defaultSerializerProvider_Impl0)._findExplicitUntypedSerializer(class0);
        BeanProperty.Bogus beanProperty_Bogus0 = new BeanProperty.Bogus();
        defaultSerializerProvider_Impl0.findPrimaryPropertySerializer((Class<?>) class0, (BeanProperty) beanProperty_Bogus0);
        defaultSerializerProvider_Impl0.cachedSerializersCount();
        assertEquals(1, defaultSerializerProvider_Impl0.cachedSerializersCount());
    }

    @Test(timeout = 4000)
    public void test3941() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        DefaultSerializerProvider.Impl defaultSerializerProvider_Impl0 = (DefaultSerializerProvider.Impl) objectMapper0.getSerializerProviderInstance();
        Class<Object> class0 = Object.class;
        SimpleType simpleType0 = SimpleType.constructUnsafe(class0);
        BeanProperty.Bogus beanProperty_Bogus0 = new BeanProperty.Bogus();
        defaultSerializerProvider_Impl0.findValueSerializer((JavaType) simpleType0);
        defaultSerializerProvider_Impl0.findPrimaryPropertySerializer((Class<?>) class0, (BeanProperty) beanProperty_Bogus0);
        defaultSerializerProvider_Impl0.cachedSerializersCount();
        assertEquals(1, defaultSerializerProvider_Impl0.cachedSerializersCount());
    }

    @Test(timeout = 4000)
    public void test4042() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        DefaultSerializerProvider.Impl defaultSerializerProvider_Impl0 = (DefaultSerializerProvider.Impl) objectMapper0.getSerializerProviderInstance();
        BeanProperty.Bogus beanProperty_Bogus0 = new BeanProperty.Bogus();
        Class<ClientInfoStatus> class0 = ClientInfoStatus.class;
        defaultSerializerProvider_Impl0.findTypedValueSerializer((Class<?>) class0, true, (BeanProperty) beanProperty_Bogus0);
        defaultSerializerProvider_Impl0.findTypedValueSerializer((Class<?>) class0, true, (BeanProperty) beanProperty_Bogus0);
        defaultSerializerProvider_Impl0.cachedSerializersCount();
        assertEquals(1, defaultSerializerProvider_Impl0.cachedSerializersCount());
    }

    @Test(timeout = 4000)
    public void test4143() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        DefaultSerializerProvider.Impl defaultSerializerProvider_Impl0 = (DefaultSerializerProvider.Impl) objectMapper0.getSerializerProviderInstance();
        BeanProperty.Bogus beanProperty_Bogus0 = new BeanProperty.Bogus();
        SimpleType simpleType0 = (SimpleType) beanProperty_Bogus0.getType();
        defaultSerializerProvider_Impl0.findTypedValueSerializer((JavaType) simpleType0, true, (BeanProperty) beanProperty_Bogus0);
        defaultSerializerProvider_Impl0.findTypedValueSerializer((JavaType) simpleType0, true, (BeanProperty) beanProperty_Bogus0);
        defaultSerializerProvider_Impl0.cachedSerializersCount();
        assertEquals(1, defaultSerializerProvider_Impl0.cachedSerializersCount());
    }

    @Test(timeout = 4000)
    public void test4244() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        JavaType javaType0 = TypeFactory.unknownType();
        objectMapper0.enableDefaultTyping();
        DefaultSerializerProvider.Impl defaultSerializerProvider_Impl0 = (DefaultSerializerProvider.Impl) objectMapper0.getSerializerProviderInstance();
        defaultSerializerProvider_Impl0.findTypedValueSerializer(javaType0, true, (BeanProperty) null);
        defaultSerializerProvider_Impl0.cachedSerializersCount();
        assertEquals(1, defaultSerializerProvider_Impl0.cachedSerializersCount());
    }

    @Test(timeout = 4000)
    public void test4345() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        DefaultSerializerProvider.Impl defaultSerializerProvider_Impl0 = (DefaultSerializerProvider.Impl) objectMapper0.getSerializerProviderInstance();
        BeanProperty.Bogus beanProperty_Bogus0 = new BeanProperty.Bogus();
        SimpleType simpleType0 = (SimpleType) beanProperty_Bogus0.getType();
        defaultSerializerProvider_Impl0.findTypedValueSerializer((JavaType) simpleType0, false, (BeanProperty) beanProperty_Bogus0);
        defaultSerializerProvider_Impl0.cachedSerializersCount();
        assertEquals(1, defaultSerializerProvider_Impl0.cachedSerializersCount());
    }

    @Test(timeout = 4000)
    public void test4446() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        DefaultSerializerProvider.Impl defaultSerializerProvider_Impl0 = (DefaultSerializerProvider.Impl) objectMapper0.getSerializerProviderInstance();
        Class<Object> class0 = Object.class;
        ((SerializerProvider) defaultSerializerProvider_Impl0)._findExplicitUntypedSerializer(class0);
        defaultSerializerProvider_Impl0.cachedSerializersCount();
        assertEquals(1, defaultSerializerProvider_Impl0.cachedSerializersCount());
    }

    @Test(timeout = 4000)
    public void test4547() throws Throwable {
        DefaultSerializerProvider.Impl defaultSerializerProvider_Impl0 = new DefaultSerializerProvider.Impl();
        boolean boolean0 = defaultSerializerProvider_Impl0.isUnknownTypeSerializer((JsonSerializer<?>) null);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test4648() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        DefaultSerializerProvider.Impl defaultSerializerProvider_Impl0 = (DefaultSerializerProvider.Impl) objectMapper0.getSerializerProviderInstance();
        Class<ObjectIdResolver> class0 = ObjectIdResolver.class;
        ((SerializerProvider) defaultSerializerProvider_Impl0)._findExplicitUntypedSerializer(class0);
        defaultSerializerProvider_Impl0.cachedSerializersCount();
        assertEquals(1, defaultSerializerProvider_Impl0.cachedSerializersCount());
    }

    @Test(timeout = 4000)
    public void test4749() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        SerializerProvider serializerProvider0 = objectMapper0.getSerializerProviderInstance();
        BeanProperty.Bogus beanProperty_Bogus0 = new BeanProperty.Bogus();
        JsonSerializer<?> jsonSerializer0 = serializerProvider0.handleSecondaryContextualization((JsonSerializer<?>) null, beanProperty_Bogus0);
    }

    @Test(timeout = 4000)
    public void test4850() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        SerializerProvider serializerProvider0 = objectMapper0.getSerializerProviderInstance();
        serializerProvider0.defaultSerializeValue(serializerProvider0, (JsonGenerator) null);
    }

    @Test(timeout = 4000)
    public void test4951() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        SerializerProvider serializerProvider0 = objectMapper0.getSerializerProviderInstance();
        serializerProvider0.defaultSerializeValue((Object) null, (JsonGenerator) null);
    }

    @Test(timeout = 4000)
    public void test5052() throws Throwable {
        DefaultSerializerProvider.Impl defaultSerializerProvider_Impl0 = new DefaultSerializerProvider.Impl();
        defaultSerializerProvider_Impl0.defaultSerializeValue((Object) null, (JsonGenerator) null);
    }

    @Test(timeout = 4000)
    public void test5153() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        SerializationFeature serializationFeature0 = SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;
        SerializationFeature[] serializationFeatureArray0 = new SerializationFeature[7];
        serializationFeatureArray0[0] = serializationFeature0;
        serializationFeatureArray0[1] = serializationFeature0;
        serializationFeatureArray0[2] = serializationFeature0;
        serializationFeatureArray0[3] = serializationFeature0;
        serializationFeatureArray0[4] = serializationFeature0;
        serializationFeatureArray0[5] = serializationFeature0;
        serializationFeatureArray0[6] = serializationFeature0;
        objectMapper0.disable(serializationFeature0, serializationFeatureArray0);
        SerializerProvider serializerProvider0 = objectMapper0.getSerializerProviderInstance();
        serializerProvider0.defaultSerializeDateValue((-18L), (JsonGenerator) null);
    }

    @Test(timeout = 4000)
    public void test5254() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        SerializerProvider serializerProvider0 = objectMapper0.getSerializerProviderInstance();
        serializerProvider0.defaultSerializeDateValue((-18L), (JsonGenerator) null);
    }

    @Test(timeout = 4000)
    public void test5355() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        SerializerProvider serializerProvider0 = objectMapper0.getSerializerProviderInstance();
        MockDate mockDate0 = new MockDate();
        serializerProvider0.defaultSerializeDateValue((Date) mockDate0, (JsonGenerator) null);
    }

    @Test(timeout = 4000)
    public void test5456() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        SerializerProvider serializerProvider0 = objectMapper0.getSerializerProviderInstance();
        MockDate mockDate0 = new MockDate();
        serializerProvider0.defaultSerializeDateKey((Date) mockDate0, (JsonGenerator) null);
    }

    @Test(timeout = 4000)
    public void test5557() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        SerializerProvider serializerProvider0 = objectMapper0.getSerializerProviderInstance();
        serializerProvider0.defaultSerializeNull((JsonGenerator) null);
    }

    @Test(timeout = 4000)
    public void test5658() throws Throwable {
        DefaultSerializerProvider.Impl defaultSerializerProvider_Impl0 = new DefaultSerializerProvider.Impl();
        defaultSerializerProvider_Impl0.defaultSerializeNull((JsonGenerator) null);
    }

    @Test(timeout = 4000)
    public void test5759() throws Throwable {
        DefaultSerializerProvider.Impl defaultSerializerProvider_Impl0 = new DefaultSerializerProvider.Impl();
        Object[] objectArray0 = new Object[9];
        defaultSerializerProvider_Impl0.reportBadTypeDefinition((BeanDescription) null, "%Q>/-FWo", objectArray0);
    }

    @Test(timeout = 4000)
    public void test5860() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        SerializerProvider serializerProvider0 = objectMapper0.getSerializerProviderInstance();
        POJOPropertiesCollector pOJOPropertiesCollector0 = mock(POJOPropertiesCollector.class, new ViolatedAssumptionAnswer());
        doReturn((AnnotatedClass) null).when(pOJOPropertiesCollector0).getClassDef();
        doReturn((MapperConfig) null).when(pOJOPropertiesCollector0).getConfig();
        doReturn((ObjectIdInfo) null).when(pOJOPropertiesCollector0).getObjectIdInfo();
        doReturn((JavaType) null).when(pOJOPropertiesCollector0).getType();
        BasicBeanDescription basicBeanDescription0 = BasicBeanDescription.forSerialization(pOJOPropertiesCollector0);
        Object[] objectArray0 = new Object[2];
        serializerProvider0.reportBadTypeDefinition((BeanDescription) basicBeanDescription0, (String) null, objectArray0);
    }

    @Test(timeout = 4000)
    public void test5961() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        SerializerProvider serializerProvider0 = objectMapper0.getSerializerProviderInstance();
        POJOPropertiesCollector pOJOPropertiesCollector0 = mock(POJOPropertiesCollector.class, new ViolatedAssumptionAnswer());
        doReturn((AnnotatedClass) null).when(pOJOPropertiesCollector0).getClassDef();
        doReturn((MapperConfig) null).when(pOJOPropertiesCollector0).getConfig();
        doReturn((ObjectIdInfo) null).when(pOJOPropertiesCollector0).getObjectIdInfo();
        doReturn((JavaType) null).when(pOJOPropertiesCollector0).getType();
        BasicBeanDescription basicBeanDescription0 = BasicBeanDescription.forDeserialization(pOJOPropertiesCollector0);
        Object[] objectArray0 = new Object[0];
        serializerProvider0.reportBadPropertyDefinition((BeanDescription) basicBeanDescription0, (BeanPropertyDefinition) null, "", objectArray0);
    }

    @Test(timeout = 4000)
    public void test6062() throws Throwable {
        DefaultSerializerProvider.Impl defaultSerializerProvider_Impl0 = new DefaultSerializerProvider.Impl();
        Object[] objectArray0 = new Object[2];
        defaultSerializerProvider_Impl0.reportBadPropertyDefinition((BeanDescription) null, (BeanPropertyDefinition) null, "avMKyX^h|i", objectArray0);
    }

    @Test(timeout = 4000)
    public void test6163() throws Throwable {
        DefaultSerializerProvider.Impl defaultSerializerProvider_Impl0 = new DefaultSerializerProvider.Impl();
        Class<BeanPropertyWriter> class0 = BeanPropertyWriter.class;
        SimpleType simpleType0 = SimpleType.constructUnsafe(class0);
        ((SerializerProvider) defaultSerializerProvider_Impl0)._reportIncompatibleRootType(class0, simpleType0);
    }

    @Test(timeout = 4000)
    public void test6264() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        DefaultSerializerProvider.Impl defaultSerializerProvider_Impl0 = (DefaultSerializerProvider.Impl) objectMapper0.getSerializerProviderInstance();
        Class<MockSimpleDateFormat> class0 = MockSimpleDateFormat.class;
        defaultSerializerProvider_Impl0.findTypedValueSerializer(class0, true, (BeanProperty) null);
        ((SerializerProvider) defaultSerializerProvider_Impl0)._findExplicitUntypedSerializer(class0);
        defaultSerializerProvider_Impl0.cachedSerializersCount();
        assertEquals(1, defaultSerializerProvider_Impl0.cachedSerializersCount());
    }

    @Test(timeout = 4000)
    public void test6365() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        DefaultSerializerProvider.Impl defaultSerializerProvider_Impl0 = (DefaultSerializerProvider.Impl) objectMapper0.getSerializerProviderInstance();
        Class<BeanSerializer> class0 = BeanSerializer.class;
        JsonSerializer<Object> jsonSerializer0 = ((SerializerProvider) defaultSerializerProvider_Impl0)._findExplicitUntypedSerializer(class0);
        ((SerializerProvider) defaultSerializerProvider_Impl0)._handleContextualResolvable(jsonSerializer0, (BeanProperty) null);
        defaultSerializerProvider_Impl0.cachedSerializersCount();
        assertEquals(0, defaultSerializerProvider_Impl0.cachedSerializersCount());
    }

    @Test(timeout = 4000)
    public void test6466() throws Throwable {
        DefaultSerializerProvider.Impl defaultSerializerProvider_Impl0 = new DefaultSerializerProvider.Impl();
        JsonSerializer<Object> jsonSerializer0 = ((SerializerProvider) defaultSerializerProvider_Impl0)._handleResolvable(defaultSerializerProvider_Impl0.DEFAULT_NULL_KEY_SERIALIZER);
        jsonSerializer0.isUnwrappingSerializer();
    }

    @Test(timeout = 4000)
    public void test6567() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        DefaultSerializerProvider.Impl defaultSerializerProvider_Impl0 = (DefaultSerializerProvider.Impl) objectMapper0.getSerializerProviderInstance();
        TypeFactory typeFactory0 = TypeFactory.defaultInstance();
        Class<ChronoLocalDate> class0 = ChronoLocalDate.class;
        CollectionLikeType collectionLikeType0 = typeFactory0.constructRawCollectionLikeType(class0);
        JsonSerializer<Object> jsonSerializer0 = defaultSerializerProvider_Impl0.findValueSerializer((JavaType) collectionLikeType0);
        defaultSerializerProvider_Impl0.serializerInstance((Annotated) null, jsonSerializer0);
        defaultSerializerProvider_Impl0.cachedSerializersCount();
        assertEquals(0, defaultSerializerProvider_Impl0.cachedSerializersCount());
    }

    @Test(timeout = 4000)
    public void test6668() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        DefaultSerializerProvider.Impl defaultSerializerProvider_Impl0 = (DefaultSerializerProvider.Impl) objectMapper0.getSerializerProviderInstance();
        StdDateFormat stdDateFormat0 = (StdDateFormat) ((SerializerProvider) defaultSerializerProvider_Impl0)._dateFormat();
        StdDateFormat stdDateFormat1 = (StdDateFormat) ((SerializerProvider) defaultSerializerProvider_Impl0)._dateFormat();
    }
}
