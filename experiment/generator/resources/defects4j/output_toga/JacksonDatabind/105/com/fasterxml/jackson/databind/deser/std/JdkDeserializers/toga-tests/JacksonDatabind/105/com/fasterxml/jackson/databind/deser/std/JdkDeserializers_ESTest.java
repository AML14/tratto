/*
 * This file was automatically generated by EvoSuite
 * Thu Mar 28 08:46:36 GMT 2024
 */
package com.fasterxml.jackson.databind.deser.std;

import org.junit.Test;
import static org.junit.Assert.*;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.std.JdkDeserializers;
import com.fasterxml.jackson.databind.util.AccessPattern;
import java.nio.ByteBuffer;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class JdkDeserializers_ESTest extends JdkDeserializers_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test00() throws Throwable {
        JdkDeserializers jdkDeserializers0 = new JdkDeserializers();
    }

    @Test(timeout = 4000)
    public void test11() throws Throwable {
        Class<ByteBuffer> class0 = ByteBuffer.class;
        JsonDeserializer<?> jsonDeserializer0 = JdkDeserializers.find(class0, "/I0}z?G $J53C)}>z");
    }

    @Test(timeout = 4000)
    public void test22() throws Throwable {
        Class<AtomicBoolean> class0 = AtomicBoolean.class;
        JsonDeserializer<?> jsonDeserializer0 = JdkDeserializers.find(class0, "com.fasterxml.jackson.databind.JavaType");
        jsonDeserializer0.getEmptyAccessPattern();
        assertNotNull(jsonDeserializer0.getEmptyAccessPattern());
    }

    @Test(timeout = 4000)
    public void test33() throws Throwable {
        Class<UUID> class0 = UUID.class;
        JsonDeserializer<?> jsonDeserializer0 = JdkDeserializers.find(class0, "com.fasterxml.jackson.databind.JavaType");
        jsonDeserializer0.getNullAccessPattern();
        assertNotNull(jsonDeserializer0.getNullAccessPattern());
    }

    @Test(timeout = 4000)
    public void test44() throws Throwable {
        Class<StackTraceElement> class0 = StackTraceElement.class;
        JsonDeserializer<?> jsonDeserializer0 = JdkDeserializers.find(class0, "com.fasterxml.jackson.databind.JavaType");
        jsonDeserializer0.isCachable();
        assertFalse(jsonDeserializer0.isCachable());
    }

    @Test(timeout = 4000)
    public void test55() throws Throwable {
        Class<ByteBuffer> class0 = ByteBuffer.class;
        JsonDeserializer<?> jsonDeserializer0 = JdkDeserializers.find(class0, "com.fasterxml.jackson.databind.JavaType");
        jsonDeserializer0.getNullAccessPattern();
        assertNotNull(jsonDeserializer0.getNullAccessPattern());
    }

    @Test(timeout = 4000)
    public void test66() throws Throwable {
        Class<Object> class0 = Object.class;
        JsonDeserializer<?> jsonDeserializer0 = JdkDeserializers.find(class0, "com.fasterxml.jackson.databind.JavaType");
    }
}
