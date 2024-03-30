/*
 * This file was automatically generated by EvoSuite
 * Thu Mar 28 05:07:50 GMT 2024
 */
package com.fasterxml.jackson.databind.ser.std;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.ser.std.JsonValueSerializer;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.testdata.EvoSuiteFile;
import org.evosuite.runtime.testdata.FileSystemHandling;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class JsonValueSerializer_ESTest extends JsonValueSerializer_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test00() throws Throwable {
        byte[] byteArray0 = new byte[3];
        byteArray0[0] = (byte) 40;
        byteArray0[1] = (byte) 0;
        byteArray0[2] = (byte) 104;
        FileSystemHandling.appendDataToFile((EvoSuiteFile) null, byteArray0);
        JsonSerializer<JsonSerializer<TypeIdResolver>> jsonSerializer0 = null;
        JsonValueSerializer jsonValueSerializer0 = new JsonValueSerializer((AnnotatedMethod) null, (JsonSerializer<?>) null);
        Class<TypeIdResolver> class0 = TypeIdResolver.class;
        jsonValueSerializer0.isNaturalTypeWithStdHandling(class0, jsonSerializer0);
    }
}
