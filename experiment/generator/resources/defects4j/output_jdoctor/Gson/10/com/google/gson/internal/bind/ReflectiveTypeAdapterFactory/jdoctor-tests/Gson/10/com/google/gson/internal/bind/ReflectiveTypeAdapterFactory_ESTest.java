/*
 * This file was automatically generated by EvoSuite
 * Wed Oct 11 16:33:09 GMT 2023
 */
package com.google.gson.internal.bind;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.InstanceCreator;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashMap;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.mock.java.io.MockPrintWriter;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class ReflectiveTypeAdapterFactory_ESTest extends ReflectiveTypeAdapterFactory_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test00() throws Throwable {
        HashMap<String, ReflectiveTypeAdapterFactory.BoundField> hashMap0 = new HashMap<String, ReflectiveTypeAdapterFactory.BoundField>();
        ReflectiveTypeAdapterFactory.Adapter<Integer> reflectiveTypeAdapterFactory_Adapter0 = new ReflectiveTypeAdapterFactory.Adapter<Integer>((ObjectConstructor<Integer>) null, hashMap0);
        Gson gson0 = new Gson();
        ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream(0);
        BufferedOutputStream bufferedOutputStream0 = new BufferedOutputStream(byteArrayOutputStream0);
        MockPrintWriter mockPrintWriter0 = new MockPrintWriter(bufferedOutputStream0, false);
        JsonWriter jsonWriter0 = gson0.newJsonWriter(mockPrintWriter0);
        Integer integer0 = new Integer(1985);
        reflectiveTypeAdapterFactory_Adapter0.write(jsonWriter0, integer0);
        jsonWriter0.isHtmlSafe();
    }

    @Test(timeout = 4000)
    public void test11() throws Throwable {
        HashMap<String, ReflectiveTypeAdapterFactory.BoundField> hashMap0 = new HashMap<String, ReflectiveTypeAdapterFactory.BoundField>();
        ReflectiveTypeAdapterFactory.Adapter<Object> reflectiveTypeAdapterFactory_Adapter0 = new ReflectiveTypeAdapterFactory.Adapter<Object>((ObjectConstructor<Object>) null, hashMap0);
        Gson gson0 = new Gson();
        PipedReader pipedReader0 = new PipedReader();
        JsonReader jsonReader0 = gson0.newJsonReader(pipedReader0);
        reflectiveTypeAdapterFactory_Adapter0.read(jsonReader0);
    }

    @Test(timeout = 4000)
    public void test22() throws Throwable {
        Excluder excluder0 = new Excluder();
        ReflectiveTypeAdapterFactory.excludeField((Field) null, true, excluder0);
    }

    @Test(timeout = 4000)
    public void test33() throws Throwable {
        FieldNamingPolicy fieldNamingPolicy0 = FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES;
        Gson gson0 = new Gson();
        Excluder excluder0 = gson0.excluder();
        ReflectiveTypeAdapterFactory reflectiveTypeAdapterFactory0 = new ReflectiveTypeAdapterFactory((ConstructorConstructor) null, fieldNamingPolicy0, excluder0);
        Class<Object> class0 = Object.class;
        TypeToken<Object> typeToken0 = TypeToken.get(class0);
        reflectiveTypeAdapterFactory0.create(gson0, typeToken0);
    }

    @Test(timeout = 4000)
    public void test44() throws Throwable {
        FieldNamingPolicy fieldNamingPolicy0 = FieldNamingPolicy.UPPER_CAMEL_CASE_WITH_SPACES;
        Gson gson0 = new Gson();
        Excluder excluder0 = gson0.excluder();
        ReflectiveTypeAdapterFactory reflectiveTypeAdapterFactory0 = new ReflectiveTypeAdapterFactory((ConstructorConstructor) null, fieldNamingPolicy0, excluder0);
        reflectiveTypeAdapterFactory0.excludeField((Field) null, true);
    }

    @Test(timeout = 4000)
    public void test55() throws Throwable {
        Gson gson0 = new Gson();
        Boolean boolean0 = new Boolean(false);
        JsonPrimitive jsonPrimitive0 = new JsonPrimitive(boolean0);
        Class<Type> class0 = Type.class;
        gson0.fromJson((JsonElement) jsonPrimitive0, class0);
    }

    @Test(timeout = 4000)
    public void test66() throws Throwable {
        HashMap<String, ReflectiveTypeAdapterFactory.BoundField> hashMap0 = new HashMap<String, ReflectiveTypeAdapterFactory.BoundField>();
        ReflectiveTypeAdapterFactory.Adapter<InstanceCreator<Integer>> reflectiveTypeAdapterFactory_Adapter0 = new ReflectiveTypeAdapterFactory.Adapter<InstanceCreator<Integer>>((ObjectConstructor<InstanceCreator<Integer>>) null, hashMap0);
        Gson gson0 = new Gson();
        PipedReader pipedReader0 = new PipedReader();
        PipedWriter pipedWriter0 = new PipedWriter(pipedReader0);
        gson0.toJson((Object) reflectiveTypeAdapterFactory_Adapter0, (Appendable) pipedWriter0);
        gson0.serializeNulls();
    }
}
