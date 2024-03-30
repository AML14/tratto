/*
 * This file was automatically generated by EvoSuite
 * Sun Oct 15 09:11:23 GMT 2023
 */
package org.apache.commons.compress.archivers.cpio;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.io.ByteArrayInputStream;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PushbackInputStream;
import java.io.SequenceInputStream;
import java.util.Enumeration;
import org.apache.commons.compress.archivers.cpio.CpioArchiveInputStream;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.evosuite.runtime.mock.java.io.MockFileInputStream;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class CpioArchiveInputStream_ESTest extends CpioArchiveInputStream_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        CpioArchiveInputStream cpioArchiveInputStream0 = new CpioArchiveInputStream((InputStream) null);
        cpioArchiveInputStream0.getNextCPIOEntry();
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        Enumeration<InputStream> enumeration0 = (Enumeration<InputStream>) mock(Enumeration.class, new ViolatedAssumptionAnswer());
        doReturn(false).when(enumeration0).hasMoreElements();
        SequenceInputStream sequenceInputStream0 = new SequenceInputStream(enumeration0);
        CpioArchiveInputStream cpioArchiveInputStream0 = new CpioArchiveInputStream(sequenceInputStream0, 1680);
        int int0 = cpioArchiveInputStream0.available();
        assertEquals(1, int0);
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        Enumeration<InputStream> enumeration0 = (Enumeration<InputStream>) mock(Enumeration.class, new ViolatedAssumptionAnswer());
        doReturn(false, false).when(enumeration0).hasMoreElements();
        SequenceInputStream sequenceInputStream0 = new SequenceInputStream(enumeration0);
        CpioArchiveInputStream cpioArchiveInputStream0 = new CpioArchiveInputStream(sequenceInputStream0, 1680);
        cpioArchiveInputStream0.close();
        cpioArchiveInputStream0.available();
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        byte[] byteArray0 = new byte[9];
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0);
        PushbackInputStream pushbackInputStream0 = new PushbackInputStream(byteArrayInputStream0, 1489);
        CpioArchiveInputStream cpioArchiveInputStream0 = new CpioArchiveInputStream(pushbackInputStream0);
        cpioArchiveInputStream0.getNextCPIOEntry();
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        byte[] byteArray0 = new byte[6];
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0, (byte) (-108), (byte) 0);
        CpioArchiveInputStream cpioArchiveInputStream0 = new CpioArchiveInputStream(byteArrayInputStream0);
        cpioArchiveInputStream0.read(byteArray0, (int) (byte) (-128), 0);
    }

    @Test(timeout = 4000)
    public void test065() throws Throwable {
        FileDescriptor fileDescriptor0 = new FileDescriptor();
        MockFileInputStream mockFileInputStream0 = new MockFileInputStream(fileDescriptor0);
        CpioArchiveInputStream cpioArchiveInputStream0 = new CpioArchiveInputStream(mockFileInputStream0, 1429);
        byte[] byteArray0 = new byte[7];
        cpioArchiveInputStream0.read(byteArray0, (int) (byte) 80, (-457));
    }

    @Test(timeout = 4000)
    public void test086() throws Throwable {
        CpioArchiveInputStream cpioArchiveInputStream0 = new CpioArchiveInputStream((InputStream) null);
        cpioArchiveInputStream0.skip((-163L));
    }

    @Test(timeout = 4000)
    public void test137() throws Throwable {
        byte[] byteArray0 = new byte[9];
        byteArray0[0] = (byte) (-20);
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0);
        PushbackInputStream pushbackInputStream0 = new PushbackInputStream(byteArrayInputStream0, 1489);
        CpioArchiveInputStream cpioArchiveInputStream0 = new CpioArchiveInputStream(pushbackInputStream0);
        cpioArchiveInputStream0.getNextCPIOEntry();
    }
}
