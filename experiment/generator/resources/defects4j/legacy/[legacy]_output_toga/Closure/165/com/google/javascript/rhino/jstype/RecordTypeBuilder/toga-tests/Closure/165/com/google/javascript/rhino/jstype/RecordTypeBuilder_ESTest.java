/*
 * This file was automatically generated by EvoSuite
 * Sun Nov 19 23:40:12 GMT 2023
 */
package com.google.javascript.rhino.jstype;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.SimpleErrorReporter;
import com.google.javascript.rhino.jstype.ErrorFunctionType;
import com.google.javascript.rhino.jstype.JSType;
import com.google.javascript.rhino.jstype.JSTypeRegistry;
import com.google.javascript.rhino.jstype.RecordTypeBuilder;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class RecordTypeBuilder_ESTest extends RecordTypeBuilder_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test00() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0, false);
        RecordTypeBuilder recordTypeBuilder0 = new RecordTypeBuilder(jSTypeRegistry0);
        Node node0 = new Node(0);
        recordTypeBuilder0.addProperty("", (JSType) null, node0);
        JSType jSType0 = recordTypeBuilder0.build();
        jSType0.isNoObjectType();
        assertTrue(jSType0.isNoObjectType());
    }

    @Test(timeout = 4000)
    public void test11() throws Throwable {
        SimpleErrorReporter simpleErrorReporter0 = new SimpleErrorReporter();
        JSTypeRegistry jSTypeRegistry0 = new JSTypeRegistry(simpleErrorReporter0, false);
        RecordTypeBuilder recordTypeBuilder0 = new RecordTypeBuilder(jSTypeRegistry0);
        ErrorFunctionType errorFunctionType0 = new ErrorFunctionType(jSTypeRegistry0, "");
        RecordTypeBuilder recordTypeBuilder1 = recordTypeBuilder0.addProperty("", errorFunctionType0, (Node) null);
        RecordTypeBuilder recordTypeBuilder2 = recordTypeBuilder1.addProperty("", errorFunctionType0, (Node) null);
    }

    @Test(timeout = 4000)
    public void test22() throws Throwable {
        RecordTypeBuilder recordTypeBuilder0 = new RecordTypeBuilder((JSTypeRegistry) null);
        recordTypeBuilder0.build();
    }
}
