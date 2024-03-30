/*
 * This file was automatically generated by EvoSuite
 * Mon Nov 20 02:29:53 GMT 2023
 */
package com.google.gson.internal.bind;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.internal.bind.TreeTypeAdapter;
import com.google.gson.internal.bind.TypeAdapterRuntimeTypeWrapper;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Currency;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.evosuite.runtime.mock.java.net.MockInetAddress;
import org.evosuite.runtime.mock.java.net.MockURI;
import org.evosuite.runtime.mock.java.net.MockURL;
import org.evosuite.runtime.mock.java.util.MockGregorianCalendar;
import org.evosuite.runtime.mock.java.util.MockUUID;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class TypeAdapters_ESTest extends TypeAdapters_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        Gson gson0 = new Gson();
        ZoneOffset zoneOffset0 = ZoneOffset.MAX;
        TimeZone timeZone0 = TimeZone.getTimeZone((ZoneId) zoneOffset0);
        Locale locale0 = Locale.ITALIAN;
        MockGregorianCalendar mockGregorianCalendar0 = new MockGregorianCalendar(timeZone0, locale0);
        JsonElement jsonElement0 = gson0.toJsonTree((Object) mockGregorianCalendar0);
        String string0 = gson0.toJson(jsonElement0);
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        Gson gson0 = new Gson();
        String string0 = gson0.toString();
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        Class<Boolean> class0 = Boolean.class;
        Gson gson0 = new Gson();
        TypeAdapter<Boolean> typeAdapter0 = TypeAdapters.BOOLEAN_AS_STRING;
        TypeAdapterRuntimeTypeWrapper<Boolean> typeAdapterRuntimeTypeWrapper0 = new TypeAdapterRuntimeTypeWrapper<Boolean>(gson0, typeAdapter0, class0);
        TypeAdapterFactory typeAdapterFactory0 = TypeAdapters.newFactoryForMultipleTypes(class0, (Class<? extends Boolean>) class0, (TypeAdapter<? super Boolean>) typeAdapterRuntimeTypeWrapper0);
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        Class<Character> class0 = Character.class;
        TypeAdapterFactory typeAdapterFactory0 = TypeAdapters.newTypeHierarchyFactory(class0, (TypeAdapter<Character>) null);
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        Class<Object> class0 = Object.class;
        TypeToken<Object> typeToken0 = TypeToken.get(class0);
        Gson gson0 = new Gson();
        TypeAdapter<Object> typeAdapter0 = gson0.getAdapter(typeToken0);
        TypeAdapterFactory typeAdapterFactory0 = TypeAdapters.newFactory(typeToken0, typeAdapter0);
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        Gson gson0 = new Gson();
        Short short0 = new Short((short) 1);
        JsonPrimitive jsonPrimitive0 = (JsonPrimitive) gson0.toJsonTree((Object) short0);
        jsonPrimitive0.isString();
        assertTrue(jsonPrimitive0.isString());
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        Gson gson0 = new Gson();
        InetAddress inetAddress0 = MockInetAddress.getByName("BEGIN_OBJECT");
        JsonElement jsonElement0 = gson0.toJsonTree((Object) inetAddress0);
        jsonElement0.isJsonArray();
    }

    @Test(timeout = 4000)
    public void test077() throws Throwable {
        Gson gson0 = new Gson();
        Byte byte0 = new Byte((byte) 9);
        JsonPrimitive jsonPrimitive0 = (JsonPrimitive) gson0.toJsonTree((Object) byte0);
        jsonPrimitive0.isBoolean();
    }

    @Test(timeout = 4000)
    public void test088() throws Throwable {
        Gson gson0 = new Gson();
        AtomicBoolean atomicBoolean0 = new AtomicBoolean(false);
        JsonElement jsonElement0 = gson0.toJsonTree((Object) atomicBoolean0);
        Class<JsonPrimitive> class0 = JsonPrimitive.class;
        JsonPrimitive jsonPrimitive0 = gson0.fromJson(jsonElement0, class0);
    }

    @Test(timeout = 4000)
    public void test099() throws Throwable {
        Gson gson0 = new Gson();
        JsonArray jsonArray0 = new JsonArray();
        Class<AtomicInteger> class0 = AtomicInteger.class;
        gson0.fromJson((JsonElement) jsonArray0, class0);
    }

    @Test(timeout = 4000)
    public void test1010() throws Throwable {
        Gson gson0 = new Gson();
        AtomicInteger atomicInteger0 = new AtomicInteger(267);
        JsonElement jsonElement0 = gson0.toJsonTree((Object) atomicInteger0);
        jsonElement0.isJsonObject();
        assertTrue(jsonElement0.isJsonObject());
    }

    @Test(timeout = 4000)
    public void test1111() throws Throwable {
        Gson gson0 = new Gson();
        Locale locale0 = Locale.CHINA;
        Currency currency0 = Currency.getInstance(locale0);
        JsonElement jsonElement0 = gson0.toJsonTree((Object) currency0);
        jsonElement0.isJsonNull();
        assertTrue(jsonElement0.isJsonNull());
    }

    @Test(timeout = 4000)
    public void test1212() throws Throwable {
        Gson gson0 = new Gson();
        Class<Currency> class0 = Currency.class;
        TypeToken<Currency> typeToken0 = TypeToken.get(class0);
        Type type0 = typeToken0.getType();
        gson0.fromJson("duplicate", type0);
    }

    @Test(timeout = 4000)
    public void test1313() throws Throwable {
        Class<BitSet> class0 = BitSet.class;
        Gson gson0 = new Gson();
        gson0.fromJson("[hMu]=", (Type) class0);
    }

    @Test(timeout = 4000)
    public void test1414() throws Throwable {
        Gson gson0 = new Gson();
        Class<BitSet> class0 = BitSet.class;
        TypeAdapter<BitSet> typeAdapter0 = gson0.getAdapter(class0);
        TypeAdapterRuntimeTypeWrapper<BitSet> typeAdapterRuntimeTypeWrapper0 = new TypeAdapterRuntimeTypeWrapper<BitSet>(gson0, typeAdapter0, class0);
        BitSet bitSet0 = typeAdapterRuntimeTypeWrapper0.fromJson("null");
    }

    @Test(timeout = 4000)
    public void test1515() throws Throwable {
        Gson gson0 = new Gson();
        Class<BitSet> class0 = BitSet.class;
        String string0 = gson0.toJson((Object) null, (Type) class0);
        assertNotNull(string0);
    }

    @Test(timeout = 4000)
    public void test1616() throws Throwable {
        Gson gson0 = new Gson();
        byte[] byteArray0 = new byte[2];
        byteArray0[0] = (byte) 57;
        BitSet bitSet0 = BitSet.valueOf(byteArray0);
        JsonArray jsonArray0 = (JsonArray) gson0.toJsonTree((Object) bitSet0);
        jsonArray0.size();
        assertEquals(1, jsonArray0.size());
    }

    @Test(timeout = 4000)
    public void test1717() throws Throwable {
        Gson gson0 = new Gson();
        JsonArray jsonArray0 = new JsonArray();
        Class<Boolean> class0 = Boolean.TYPE;
        gson0.fromJson((JsonElement) jsonArray0, (Type) class0);
    }

    @Test(timeout = 4000)
    public void test1818() throws Throwable {
        Gson gson0 = new Gson();
        Class<Boolean> class0 = Boolean.TYPE;
        Long long0 = gson0.fromJson("null", (Type) class0);
    }

    @Test(timeout = 4000)
    public void test1919() throws Throwable {
        Gson gson0 = new Gson();
        Class<Boolean> class0 = Boolean.TYPE;
        gson0.fromJson("Error: Expecting: bitset number value (1, 0), Found: ", (Type) class0);
    }

    @Test(timeout = 4000)
    public void test2020() throws Throwable {
        Gson gson0 = new Gson();
        Class<Boolean> class0 = Boolean.TYPE;
        String string0 = gson0.toJson((Object) null, (Type) class0);
    }

    @Test(timeout = 4000)
    public void test2121() throws Throwable {
        Gson gson0 = new Gson();
        Class<Byte> class0 = Byte.TYPE;
        gson0.fromJson("'wDTS'Xdd", (Type) class0);
    }

    @Test(timeout = 4000)
    public void test2222() throws Throwable {
        Gson gson0 = new Gson();
        Class<Short> class0 = Short.TYPE;
        gson0.fromJson("com.google.gson.internal.LinkedTreeMap$Node", (Type) class0);
    }

    @Test(timeout = 4000)
    public void test2323() throws Throwable {
        Gson gson0 = new Gson();
        Class<Integer> class0 = Integer.TYPE;
        gson0.fromJson("Error", (Type) class0);
    }

    @Test(timeout = 4000)
    public void test2424() throws Throwable {
        Gson gson0 = new Gson();
        int[] intArray0 = new int[1];
        AtomicIntegerArray atomicIntegerArray0 = new AtomicIntegerArray(intArray0);
        JsonArray jsonArray0 = (JsonArray) gson0.toJsonTree((Object) atomicIntegerArray0);
        jsonArray0.size();
    }

    @Test(timeout = 4000)
    public void test2525() throws Throwable {
        Gson gson0 = new Gson();
        Class<Long> class0 = Long.TYPE;
        gson0.fromJson("Error: Expecting: bitset number value (1, 0), Found: ", (Type) class0);
    }

    @Test(timeout = 4000)
    public void test2626() throws Throwable {
        Class<Long> class0 = Long.class;
        Gson gson0 = new Gson();
        StringReader stringReader0 = new StringReader("null");
        JsonReader jsonReader0 = new JsonReader(stringReader0);
        GregorianCalendar gregorianCalendar0 = gson0.fromJson(jsonReader0, (Type) class0);
    }

    @Test(timeout = 4000)
    public void test2727() throws Throwable {
        Gson gson0 = new Gson();
        Class<Character> class0 = Character.TYPE;
        gson0.fromJson("H#L[a|Yce5}9{$~+j", (Type) class0);
    }

    @Test(timeout = 4000)
    public void test2828() throws Throwable {
        Gson gson0 = new Gson();
        Class<Character> class0 = Character.TYPE;
        Long long0 = gson0.fromJson("null", (Type) class0);
    }

    @Test(timeout = 4000)
    public void test2929() throws Throwable {
        Gson gson0 = new Gson();
        Class<Character> class0 = Character.TYPE;
        gson0.fromJson("yOvN cannTthn:udle", (Type) class0);
    }

    @Test(timeout = 4000)
    public void test3030() throws Throwable {
        Gson gson0 = new Gson();
        Character character0 = Character.valueOf('2');
        JsonElement jsonElement0 = gson0.toJsonTree((Object) character0);
        Class<GregorianCalendar> class0 = GregorianCalendar.class;
        gson0.fromJson(jsonElement0, class0);
    }

    @Test(timeout = 4000)
    public void test3131() throws Throwable {
        Gson gson0 = new Gson();
        Class<Character> class0 = Character.TYPE;
        JsonElement jsonElement0 = gson0.toJsonTree((Object) null, (Type) class0);
        jsonElement0.isJsonNull();
        assertTrue(jsonElement0.isJsonNull());
    }

    @Test(timeout = 4000)
    public void test3232() throws Throwable {
        Gson gson0 = new Gson();
        StringReader stringReader0 = new StringReader("wz4v");
        Class<String> class0 = String.class;
        String string0 = gson0.fromJson((Reader) stringReader0, class0);
    }

    @Test(timeout = 4000)
    public void test3333() throws Throwable {
        Gson gson0 = new Gson();
        StringReader stringReader0 = new StringReader("null");
        Class<String> class0 = String.class;
        String string0 = gson0.fromJson((Reader) stringReader0, class0);
    }

    @Test(timeout = 4000)
    public void test3434() throws Throwable {
        Gson gson0 = new Gson();
        StringBuilder stringBuilder0 = new StringBuilder();
        JsonElement jsonElement0 = gson0.toJsonTree((Object) stringBuilder0);
        jsonElement0.isJsonPrimitive();
        assertTrue(jsonElement0.isJsonPrimitive());
    }

    @Test(timeout = 4000)
    public void test3535() throws Throwable {
        Gson gson0 = new Gson();
        StringBuffer stringBuffer0 = new StringBuffer();
        JsonPrimitive jsonPrimitive0 = (JsonPrimitive) gson0.toJsonTree((Object) stringBuffer0);
        jsonPrimitive0.isString();
        assertTrue(jsonPrimitive0.isString());
    }

    @Test(timeout = 4000)
    public void test3636() throws Throwable {
        Gson gson0 = new Gson();
        Class<URL> class0 = URL.class;
        URL uRL0 = gson0.fromJson("null", class0);
    }

    @Test(timeout = 4000)
    public void test3737() throws Throwable {
        Gson gson0 = new Gson();
        URL uRL0 = MockURL.getFileExample();
        JsonPrimitive jsonPrimitive0 = (JsonPrimitive) gson0.toJsonTree((Object) uRL0);
        jsonPrimitive0.isBoolean();
        assertTrue(jsonPrimitive0.isBoolean());
    }

    @Test(timeout = 4000)
    public void test3838() throws Throwable {
        Gson gson0 = new Gson();
        Class<URI> class0 = URI.class;
        gson0.fromJson(">X", class0);
    }

    @Test(timeout = 4000)
    public void test3939() throws Throwable {
        Gson gson0 = new Gson();
        Class<URI> class0 = URI.class;
        URI uRI0 = gson0.fromJson("null", class0);
    }

    @Test(timeout = 4000)
    public void test4040() throws Throwable {
        Gson gson0 = new Gson();
        URI uRI0 = MockURI.aHttpURI;
        JsonElement jsonElement0 = gson0.toJsonTree((Object) uRI0);
        jsonElement0.isJsonObject();
    }

    @Test(timeout = 4000)
    public void test4141() throws Throwable {
        Gson gson0 = new Gson();
        Class<InetAddress> class0 = InetAddress.class;
        gson0.fromJson("yOvN cannTthn:udle", class0);
    }

    @Test(timeout = 4000)
    public void test4242() throws Throwable {
        Gson gson0 = new Gson();
        Class<InetAddress> class0 = InetAddress.class;
        InetAddress inetAddress0 = gson0.fromJson("null", class0);
    }

    @Test(timeout = 4000)
    public void test4343() throws Throwable {
        Gson gson0 = new Gson();
        UUID uUID0 = MockUUID.randomUUID();
        JsonPrimitive jsonPrimitive0 = (JsonPrimitive) gson0.toJsonTree((Object) uUID0);
        jsonPrimitive0.isNumber();
    }

    @Test(timeout = 4000)
    public void test4444() throws Throwable {
        Gson gson0 = new Gson();
        Class<GregorianCalendar> class0 = GregorianCalendar.class;
        Long long0 = gson0.fromJson("null", (Type) class0);
    }

    @Test(timeout = 4000)
    public void test4545() throws Throwable {
        Gson gson0 = new Gson();
        Class<GregorianCalendar> class0 = GregorianCalendar.class;
        TypeToken<GregorianCalendar> typeToken0 = TypeToken.get(class0);
        Class<? super GregorianCalendar> class1 = typeToken0.getRawType();
        gson0.fromJson("{NM%K}.T\"]", (Type) class1);
    }

    @Test(timeout = 4000)
    public void test4646() throws Throwable {
        Gson gson0 = new Gson();
        MockGregorianCalendar mockGregorianCalendar0 = new MockGregorianCalendar(23, 23, 316, (-1833), (-1833), 316);
        Class<GregorianCalendar> class0 = GregorianCalendar.class;
        gson0.toJsonTree((Object) mockGregorianCalendar0, (Type) class0);
        mockGregorianCalendar0.toString();
    }

    @Test(timeout = 4000)
    public void test4747() throws Throwable {
        Gson gson0 = new Gson();
        Class<GregorianCalendar> class0 = GregorianCalendar.class;
        TypeToken<GregorianCalendar> typeToken0 = TypeToken.get(class0);
        Class<? super GregorianCalendar> class1 = typeToken0.getRawType();
        JsonElement jsonElement0 = gson0.toJsonTree((Object) null, (Type) class1);
        jsonElement0.isJsonNull();
        assertTrue(jsonElement0.isJsonNull());
    }

    @Test(timeout = 4000)
    public void test4848() throws Throwable {
        Gson gson0 = new Gson();
        Class<Locale> class0 = Locale.class;
        gson0.fromJson("(#U[\"@6Z~.2", class0);
    }

    @Test(timeout = 4000)
    public void test4949() throws Throwable {
        Class<Locale> class0 = Locale.class;
        Gson gson0 = new Gson();
        gson0.fromJson("_", class0);
    }

    @Test(timeout = 4000)
    public void test5050() throws Throwable {
        Gson gson0 = new Gson();
        Locale locale0 = Locale.UK;
        JsonPrimitive jsonPrimitive0 = (JsonPrimitive) gson0.toJsonTree((Object) locale0);
        jsonPrimitive0.isString();
        assertTrue(jsonPrimitive0.isString());
    }

    @Test(timeout = 4000)
    public void test5151() throws Throwable {
        Gson gson0 = new Gson();
        Class<BitSet> class0 = BitSet.class;
        TypeToken<BitSet> typeToken0 = TypeToken.get(class0);
        JsonDeserializer<BitSet> jsonDeserializer0 = (JsonDeserializer<BitSet>) mock(JsonDeserializer.class, new ViolatedAssumptionAnswer());
        doReturn((Object) null).when(jsonDeserializer0).deserialize(any(com.google.gson.JsonElement.class), any(java.lang.reflect.Type.class), any(com.google.gson.JsonDeserializationContext.class));
        TreeTypeAdapter<BitSet> treeTypeAdapter0 = new TreeTypeAdapter<BitSet>((JsonSerializer<BitSet>) null, jsonDeserializer0, gson0, typeToken0, (TypeAdapterFactory) null);
        TypeAdapterRuntimeTypeWrapper<BitSet> typeAdapterRuntimeTypeWrapper0 = new TypeAdapterRuntimeTypeWrapper<BitSet>(gson0, treeTypeAdapter0, class0);
        BitSet bitSet0 = typeAdapterRuntimeTypeWrapper0.fromJson("6}UK%^^[\"Ku");
    }

    @Test(timeout = 4000)
    public void test5252() throws Throwable {
        Gson gson0 = new Gson();
        JsonObject jsonObject0 = new JsonObject();
        Class<JsonPrimitive> class0 = JsonPrimitive.class;
        gson0.fromJson((JsonElement) jsonObject0, class0);
    }

    @Test(timeout = 4000)
    public void test5353() throws Throwable {
        Gson gson0 = new Gson();
        Character character0 = Character.valueOf('_');
        JsonPrimitive jsonPrimitive0 = new JsonPrimitive(character0);
        Class<JsonPrimitive> class0 = JsonPrimitive.class;
        JsonPrimitive jsonPrimitive1 = gson0.fromJson((JsonElement) jsonPrimitive0, class0);
        jsonPrimitive1.isBoolean();
        assertTrue(jsonPrimitive1.isBoolean());
    }

    @Test(timeout = 4000)
    public void test5454() throws Throwable {
        Gson gson0 = new Gson();
        Class<GregorianCalendar> class0 = GregorianCalendar.class;
        Class<BitSet> class1 = BitSet.class;
        TypeToken<BitSet> typeToken0 = TypeToken.get(class1);
        JsonDeserializer<BitSet> jsonDeserializer0 = (JsonDeserializer<BitSet>) mock(JsonDeserializer.class, new ViolatedAssumptionAnswer());
        TreeTypeAdapter<BitSet> treeTypeAdapter0 = new TreeTypeAdapter<BitSet>((JsonSerializer<BitSet>) null, jsonDeserializer0, gson0, typeToken0, (TypeAdapterFactory) null);
        TypeAdapterRuntimeTypeWrapper<BitSet> typeAdapterRuntimeTypeWrapper0 = new TypeAdapterRuntimeTypeWrapper<BitSet>(gson0, treeTypeAdapter0, class0);
        BitSet bitSet0 = typeAdapterRuntimeTypeWrapper0.fromJson("null");
    }

    @Test(timeout = 4000)
    public void test5555() throws Throwable {
        Gson gson0 = new Gson();
        String string0 = gson0.toJson((JsonElement) null);
    }

    @Test(timeout = 4000)
    public void test5656() throws Throwable {
        Gson gson0 = new Gson();
        JsonNull jsonNull0 = JsonNull.INSTANCE;
        JsonElement jsonElement0 = gson0.toJsonTree((Object) jsonNull0);
        jsonElement0.isJsonNull();
        assertTrue(jsonElement0.isJsonNull());
    }

    @Test(timeout = 4000)
    public void test5757() throws Throwable {
        Gson gson0 = new Gson();
        JsonElement jsonElement0 = gson0.toJsonTree((Object) gson0);
        jsonElement0.isJsonPrimitive();
        assertFalse(jsonElement0.isJsonPrimitive());
    }

    @Test(timeout = 4000)
    public void test5858() throws Throwable {
        ObjectConstructor<Short> objectConstructor0 = (ObjectConstructor<Short>) mock(ObjectConstructor.class, new ViolatedAssumptionAnswer());
        Gson gson0 = new Gson();
        gson0.toJsonTree((Object) objectConstructor0);
    }

    @Test(timeout = 4000)
    public void test5959() throws Throwable {
        Gson gson0 = new Gson();
        Class<Calendar> class0 = Calendar.class;
        TypeAdapter<Calendar> typeAdapter0 = gson0.getAdapter(class0);
    }
}
