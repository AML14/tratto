/*
 * This file was automatically generated by EvoSuite
 * Sun Dec 31 05:12:24 GMT 2023
 */
package org.mockito;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoDebugger;
import org.mockito.internal.verification.api.VerificationMode;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.Stubber;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class Mockito_ESTest extends Mockito_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        Class<String> class0 = String.class;
        Mockito.mock(class0);
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        Stubber stubber0 = Mockito.doNothing();
        assertNotNull(stubber0);
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        VerificationMode verificationMode0 = Mockito.only();
        Mockito.verify(";XQ", verificationMode0);
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        Mockito.when("TH<$5{*%#x64% ");
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        Object[] objectArray0 = new Object[0];
        Mockito.verifyZeroInteractions(objectArray0);
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        Object[] objectArray0 = new Object[0];
        Mockito.reset(objectArray0);
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        Stubber stubber0 = Mockito.doCallRealMethod();
    }

    @Test(timeout = 4000)
    public void test077() throws Throwable {
        Class<Object> class0 = Object.class;
        Mockito.mock(class0, "\"YWG@lYX?RbomF/kLU");
    }

    @Test(timeout = 4000)
    public void test088() throws Throwable {
        MockitoDebugger mockitoDebugger0 = Mockito.debug();
        assertNotNull(mockitoDebugger0);
    }

    @Test(timeout = 4000)
    public void test099() throws Throwable {
        VerificationMode verificationMode0 = Mockito.never();
        assertNotNull(verificationMode0);
    }

    @Test(timeout = 4000)
    public void test1010() throws Throwable {
        Mockito.inOrder((Object[]) null);
    }

    @Test(timeout = 4000)
    public void test1111() throws Throwable {
        Mockito.spy((Object) "uS");
    }

    @Test(timeout = 4000)
    public void test1212() throws Throwable {
        Class<String> class0 = String.class;
        Answer<Object> answer0 = Mockito.RETURNS_DEFAULTS;
        Mockito.mock(class0, (Answer) answer0);
    }

    @Test(timeout = 4000)
    public void test1413() throws Throwable {
        Stubber stubber0 = Mockito.doAnswer((Answer) null);
        assertNotNull(stubber0);
    }

    @Test(timeout = 4000)
    public void test1514() throws Throwable {
        VerificationMode verificationMode0 = Mockito.atMost(0);
    }

    @Test(timeout = 4000)
    public void test1615() throws Throwable {
        Mockito.stubVoid("Zk_mhRIBa<tWv-i");
    }

    @Test(timeout = 4000)
    public void test1716() throws Throwable {
        Mockito.stub("");
    }

    @Test(timeout = 4000)
    public void test1817() throws Throwable {
        String[] stringArray0 = new String[1];
        Mockito.verifyNoMoreInteractions(stringArray0);
    }

    @Test(timeout = 4000)
    public void test1918() throws Throwable {
        VerificationMode verificationMode0 = Mockito.atLeastOnce();
    }

    @Test(timeout = 4000)
    public void test2019() throws Throwable {
        Mockito.atLeast((-5));
    }

    @Test(timeout = 4000)
    public void test2120() throws Throwable {
        VerificationMode verificationMode0 = Mockito.only();
        Stubber stubber0 = Mockito.doReturn(verificationMode0);
        assertNotNull(stubber0);
    }

    @Test(timeout = 4000)
    public void test2221() throws Throwable {
        Mockito.verify((Object) "'oL.~H?Uxt#r=W.P@&");
    }

    @Test(timeout = 4000)
    public void test2322() throws Throwable {
        Mockito.validateMockitoUsage();
    }

    @Test(timeout = 4000)
    public void test2423() throws Throwable {
        Stubber stubber0 = Mockito.doThrow((Throwable) null);
        assertNotNull(stubber0);
    }
}
