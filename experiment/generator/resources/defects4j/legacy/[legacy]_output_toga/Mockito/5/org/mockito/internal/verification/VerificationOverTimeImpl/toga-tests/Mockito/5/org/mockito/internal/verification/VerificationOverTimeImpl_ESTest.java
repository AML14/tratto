/*
 * This file was automatically generated by EvoSuite
 * Sun Nov 05 01:05:12 GMT 2023
 */
package org.mockito.internal.verification;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;
import org.mockito.internal.util.Timer;
import org.mockito.internal.verification.AtMost;
import org.mockito.internal.verification.NoMoreInteractions;
import org.mockito.internal.verification.VerificationOverTimeImpl;
import org.mockito.internal.verification.api.VerificationData;
import org.mockito.verification.After;
import org.mockito.verification.VerificationMode;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class VerificationOverTimeImpl_ESTest extends VerificationOverTimeImpl_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test00() throws Throwable {
        VerificationOverTimeImpl verificationOverTimeImpl0 = new VerificationOverTimeImpl(2093L, 2093L, (VerificationMode) null, true);
        long long0 = verificationOverTimeImpl0.getDuration();
        assertEquals(0, long0);
    }

    @Test(timeout = 4000)
    public void test01() throws Throwable {
        VerificationOverTimeImpl verificationOverTimeImpl0 = new VerificationOverTimeImpl(2093L, 2093L, (VerificationMode) null, true);
        long long0 = verificationOverTimeImpl0.getDuration();
        verificationOverTimeImpl0.getPollingPeriod();
    }

    @Test(timeout = 4000)
    public void test12() throws Throwable {
        VerificationOverTimeImpl verificationOverTimeImpl0 = new VerificationOverTimeImpl((-1L), (-1L), (VerificationMode) null, false);
        verificationOverTimeImpl0.getDelegate();
        verificationOverTimeImpl0.getPollingPeriod();
        assertEquals(0, verificationOverTimeImpl0.getPollingPeriod());
    }

    @Test(timeout = 4000)
    public void test13() throws Throwable {
        VerificationOverTimeImpl verificationOverTimeImpl0 = new VerificationOverTimeImpl((-1L), (-1L), (VerificationMode) null, false);
        verificationOverTimeImpl0.getDelegate();
        verificationOverTimeImpl0.getDuration();
        assertEquals(0, verificationOverTimeImpl0.getDuration());
    }

    @Test(timeout = 4000)
    public void test24() throws Throwable {
        VerificationOverTimeImpl verificationOverTimeImpl0 = new VerificationOverTimeImpl((-968L), (-968L), (VerificationMode) null, false);
        long long0 = verificationOverTimeImpl0.getPollingPeriod();
        assertEquals(0, long0);
    }

    @Test(timeout = 4000)
    public void test25() throws Throwable {
        VerificationOverTimeImpl verificationOverTimeImpl0 = new VerificationOverTimeImpl((-968L), (-968L), (VerificationMode) null, false);
        long long0 = verificationOverTimeImpl0.getPollingPeriod();
        verificationOverTimeImpl0.getDuration();
        assertEquals(0, verificationOverTimeImpl0.getDuration());
    }

    @Test(timeout = 4000)
    public void test46() throws Throwable {
        VerificationOverTimeImpl verificationOverTimeImpl0 = new VerificationOverTimeImpl(360L, 360L, (VerificationMode) null, true);
        verificationOverTimeImpl0.verify((VerificationData) null);
    }

    @Test(timeout = 4000)
    public void test57() throws Throwable {
        AtMost atMost0 = new AtMost(37);
        Timer timer0 = new Timer(37);
        VerificationOverTimeImpl verificationOverTimeImpl0 = new VerificationOverTimeImpl(0L, 0L, atMost0, false, timer0);
        boolean boolean0 = verificationOverTimeImpl0.canRecoverFromFailure(atMost0);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test68() throws Throwable {
        Timer timer0 = new Timer(0L);
        VerificationOverTimeImpl verificationOverTimeImpl0 = new VerificationOverTimeImpl(0L, 0L, (VerificationMode) null, false, timer0);
        boolean boolean0 = verificationOverTimeImpl0.canRecoverFromFailure((VerificationMode) null);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test79() throws Throwable {
        NoMoreInteractions noMoreInteractions0 = new NoMoreInteractions();
        Timer timer0 = new Timer(0L);
        VerificationOverTimeImpl verificationOverTimeImpl0 = new VerificationOverTimeImpl(0L, 0L, noMoreInteractions0, true, timer0);
        boolean boolean0 = verificationOverTimeImpl0.canRecoverFromFailure(noMoreInteractions0);
        assertTrue(boolean0);
    }
}
