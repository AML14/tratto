/*
 * This file was automatically generated by EvoSuite
 * Sun Nov 05 05:13:09 GMT 2023
 */
package org.mockito.internal.util;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;
import org.mockito.internal.creation.MockSettingsImpl;
import org.mockito.internal.util.MockUtil;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class MockUtil_ESTest extends MockUtil_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test00() throws Throwable {
        MockUtil mockUtil0 = new MockUtil();
        Class<Object> class0 = Object.class;
        MockSettingsImpl mockSettingsImpl0 = new MockSettingsImpl();
        mockUtil0.createMock(class0, mockSettingsImpl0);
    }

    @Test(timeout = 4000)
    public void test11() throws Throwable {
        MockUtil mockUtil0 = new MockUtil();
        mockUtil0.getMockName(mockUtil0);
    }

    @Test(timeout = 4000)
    public void test22() throws Throwable {
        MockUtil mockUtil0 = new MockUtil();
        MockSettingsImpl mockSettingsImpl0 = new MockSettingsImpl();
        mockUtil0.resetMock(mockSettingsImpl0);
    }

    @Test(timeout = 4000)
    public void test33() throws Throwable {
        MockUtil mockUtil0 = new MockUtil();
        Class<Object> class0 = Object.class;
        MockSettingsImpl mockSettingsImpl0 = new MockSettingsImpl();
        mockSettingsImpl0.serializable();
        mockUtil0.createMock(class0, mockSettingsImpl0);
    }

    @Test(timeout = 4000)
    public void test44() throws Throwable {
        MockUtil mockUtil0 = new MockUtil();
        Class<Object> class0 = Object.class;
        MockSettingsImpl mockSettingsImpl0 = new MockSettingsImpl();
        Class<Annotation>[] classArray0 = (Class<Annotation>[]) Array.newInstance(Class.class, 1);
        mockSettingsImpl0.serializable();
        Class<Annotation> class1 = Annotation.class;
        classArray0[0] = class1;
        mockSettingsImpl0.extraInterfaces(classArray0);
        mockUtil0.createMock(class0, mockSettingsImpl0);
    }

    @Test(timeout = 4000)
    public void test55() throws Throwable {
        MockUtil mockUtil0 = new MockUtil();
        Class<Object> class0 = Object.class;
        MockSettingsImpl mockSettingsImpl0 = new MockSettingsImpl();
        Class<Annotation>[] classArray0 = (Class<Annotation>[]) Array.newInstance(Class.class, 1);
        Class<Annotation> class1 = Annotation.class;
        classArray0[0] = class1;
        mockSettingsImpl0.extraInterfaces(classArray0);
        mockUtil0.createMock(class0, mockSettingsImpl0);
    }

    @Test(timeout = 4000)
    public void test66() throws Throwable {
        MockUtil mockUtil0 = new MockUtil();
        mockUtil0.resetMock((MockSettingsImpl) null);
    }

    @Test(timeout = 4000)
    public void test77() throws Throwable {
        MockUtil mockUtil0 = new MockUtil();
        boolean boolean0 = mockUtil0.isMock((Object) null);
    }

    @Test(timeout = 4000)
    public void test88() throws Throwable {
        MockUtil mockUtil0 = new MockUtil();
        boolean boolean0 = mockUtil0.isMock(mockUtil0);
    }
}
