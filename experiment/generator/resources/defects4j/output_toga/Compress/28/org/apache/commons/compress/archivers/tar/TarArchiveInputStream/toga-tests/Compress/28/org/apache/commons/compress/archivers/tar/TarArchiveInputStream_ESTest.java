/*
 * This file was automatically generated by EvoSuite
 * Sun Oct 15 10:13:14 GMT 2023
 */
package org.apache.commons.compress.archivers.tar;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PushbackInputStream;
import java.io.SequenceInputStream;
import java.net.URI;
import java.nio.charset.IllegalCharsetNameException;
import java.util.Enumeration;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipEncoding;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.evosuite.runtime.mock.java.io.MockFile;
import org.evosuite.runtime.mock.java.net.MockURI;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class TarArchiveInputStream_ESTest extends TarArchiveInputStream_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        PipedInputStream pipedInputStream0 = null;
        pipedInputStream0 = new PipedInputStream((-2007));
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        byte[] byteArray0 = new byte[5];
        byteArray0[0] = (byte) 0;
        byteArray0[1] = (byte) 0;
        byteArray0[2] = (byte) (-95);
        byteArray0[3] = (byte) 98;
        byteArray0[4] = (byte) 48;
        boolean boolean0 = TarArchiveInputStream.matches(byteArray0, 0);
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        Enumeration<ByteArrayInputStream> enumeration0 = (Enumeration<ByteArrayInputStream>) mock(Enumeration.class, new ViolatedAssumptionAnswer());
        doReturn(false).when(enumeration0).hasMoreElements();
        SequenceInputStream sequenceInputStream0 = new SequenceInputStream(enumeration0);
        sequenceInputStream0.available();
        int int0 = (-2350);
        TarArchiveInputStream tarArchiveInputStream0 = new TarArchiveInputStream(sequenceInputStream0, (-2350));
        String string0 = "C6<v;\"na3/E/)";
        byte byte0 = (byte) (-79);
        long long0 = 3L;
        byte[] byteArray0 = new byte[3];
        byteArray0[0] = (byte) (-79);
        byteArray0[1] = (byte) (-79);
        byteArray0[2] = (byte) (-79);
        TarArchiveEntry tarArchiveEntry0 = null;
        tarArchiveEntry0 = new TarArchiveEntry(byteArray0);
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        byte[] byteArray0 = new byte[2];
        byteArray0[0] = (byte) (-25);
        byteArray0[1] = (byte) (-78);
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0, 200, (byte) (-78));
        byteArrayInputStream0.skip((byte) (-25));
        byteArrayInputStream0.close();
        TarArchiveInputStream tarArchiveInputStream0 = new TarArchiveInputStream(byteArrayInputStream0, 0);
        tarArchiveInputStream0.getNextEntry();
        tarArchiveInputStream0.getNextEntry();
        byte[] byteArray1 = tarArchiveInputStream0.getLongNameData();
    }

    @Test(timeout = 4000)
    public void test034() throws Throwable {
        byte[] byteArray0 = new byte[2];
        byteArray0[0] = (byte) (-25);
        byteArray0[1] = (byte) (-78);
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0, 200, (byte) (-78));
        byteArrayInputStream0.skip((byte) (-25));
        byteArrayInputStream0.close();
        TarArchiveInputStream tarArchiveInputStream0 = new TarArchiveInputStream(byteArrayInputStream0, 0);
        tarArchiveInputStream0.getNextEntry();
        tarArchiveInputStream0.getNextEntry();
        byte[] byteArray1 = tarArchiveInputStream0.getLongNameData();
        tarArchiveInputStream0.getNextEntry();
        tarArchiveInputStream0.parsePaxHeaders(byteArrayInputStream0);
        tarArchiveInputStream0.markSupported();
        int int0 = tarArchiveInputStream0.read(byteArray0, 0, (int) (byte) (-78));
        assertEquals(1, int0);
    }

    @Test(timeout = 4000)
    public void test035() throws Throwable {
        byte[] byteArray0 = new byte[2];
        byteArray0[0] = (byte) (-25);
        byteArray0[1] = (byte) (-78);
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0, 200, (byte) (-78));
        byteArrayInputStream0.skip((byte) (-25));
        byteArrayInputStream0.close();
        TarArchiveInputStream tarArchiveInputStream0 = new TarArchiveInputStream(byteArrayInputStream0, 0);
        tarArchiveInputStream0.getNextEntry();
        tarArchiveInputStream0.getNextEntry();
        byte[] byteArray1 = tarArchiveInputStream0.getLongNameData();
        tarArchiveInputStream0.getNextEntry();
        tarArchiveInputStream0.parsePaxHeaders(byteArrayInputStream0);
        tarArchiveInputStream0.markSupported();
        int int0 = tarArchiveInputStream0.read(byteArray0, 0, (int) (byte) (-78));
        boolean boolean0 = tarArchiveInputStream0.isEOFRecord(byteArray0);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test036() throws Throwable {
        byte[] byteArray0 = new byte[2];
        byteArray0[0] = (byte) (-25);
        byteArray0[1] = (byte) (-78);
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0, 200, (byte) (-78));
        byteArrayInputStream0.skip((byte) (-25));
        byteArrayInputStream0.close();
        TarArchiveInputStream tarArchiveInputStream0 = new TarArchiveInputStream(byteArrayInputStream0, 0);
        tarArchiveInputStream0.getNextEntry();
        tarArchiveInputStream0.getNextEntry();
        byte[] byteArray1 = tarArchiveInputStream0.getLongNameData();
        tarArchiveInputStream0.getNextEntry();
        tarArchiveInputStream0.parsePaxHeaders(byteArrayInputStream0);
        tarArchiveInputStream0.markSupported();
        int int0 = tarArchiveInputStream0.read(byteArray0, 0, (int) (byte) (-78));
        boolean boolean0 = tarArchiveInputStream0.isEOFRecord(byteArray0);
        int int1 = tarArchiveInputStream0.getRecordSize();
        tarArchiveInputStream0.getBytesRead();
    }

    @Test(timeout = 4000)
    public void test037() throws Throwable {
        byte[] byteArray0 = new byte[2];
        byteArray0[0] = (byte) (-25);
        byteArray0[1] = (byte) (-78);
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0, 200, (byte) (-78));
        byteArrayInputStream0.skip((byte) (-25));
        byteArrayInputStream0.close();
        TarArchiveInputStream tarArchiveInputStream0 = new TarArchiveInputStream(byteArrayInputStream0, 0);
        tarArchiveInputStream0.getNextEntry();
        tarArchiveInputStream0.getNextEntry();
        byte[] byteArray1 = tarArchiveInputStream0.getLongNameData();
        tarArchiveInputStream0.getNextEntry();
        tarArchiveInputStream0.parsePaxHeaders(byteArrayInputStream0);
        tarArchiveInputStream0.markSupported();
        int int0 = tarArchiveInputStream0.read(byteArray0, 0, (int) (byte) (-78));
        boolean boolean0 = tarArchiveInputStream0.isEOFRecord(byteArray0);
        int int1 = tarArchiveInputStream0.getRecordSize();
        assertEquals(0, int1);
    }

    @Test(timeout = 4000)
    public void test048() throws Throwable {
        byte[] byteArray0 = new byte[4];
        byteArray0[0] = (byte) 1;
        byteArray0[1] = (byte) 0;
        byteArray0[2] = (byte) 112;
        byteArray0[3] = (byte) 0;
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0);
        byteArrayInputStream0.close();
        TarArchiveInputStream tarArchiveInputStream0 = new TarArchiveInputStream(byteArrayInputStream0);
        tarArchiveInputStream0.close();
        byteArrayInputStream0.skip((-2190L));
        URI uRI0 = MockURI.aFTPURI;
        MockFile mockFile0 = null;
        mockFile0 = new MockFile(uRI0);
    }

    @Test(timeout = 4000)
    public void test059() throws Throwable {
        TarArchiveInputStream tarArchiveInputStream0 = new TarArchiveInputStream((InputStream) null, 61, (String) null);
        TarArchiveInputStream tarArchiveInputStream1 = new TarArchiveInputStream(tarArchiveInputStream0, 48, 256, (String) null);
        tarArchiveInputStream1.close();
    }

    @Test(timeout = 4000)
    public void test0610() throws Throwable {
        TarArchiveInputStream tarArchiveInputStream0 = new TarArchiveInputStream((InputStream) null);
        tarArchiveInputStream0.parsePaxHeaders((InputStream) null);
    }

    @Test(timeout = 4000)
    public void test0711() throws Throwable {
        PipedOutputStream pipedOutputStream0 = new PipedOutputStream();
        PipedInputStream pipedInputStream0 = new PipedInputStream(pipedOutputStream0);
        pipedOutputStream0.flush();
        TarArchiveInputStream tarArchiveInputStream0 = new TarArchiveInputStream(pipedInputStream0, 10240, 10240, "org.apache.commons.compress.archivers.tar.TarArchiveInputStream");
        tarArchiveInputStream0.close();
        tarArchiveInputStream0.parsePaxHeaders(pipedInputStream0);
    }

    @Test(timeout = 4000)
    public void test0812() throws Throwable {
        byte[] byteArray0 = new byte[5];
        byteArray0[0] = (byte) 114;
        byteArray0[1] = (byte) 0;
        byteArray0[2] = (byte) 0;
        byteArray0[3] = (byte) 42;
        byteArray0[4] = (byte) 0;
        TarArchiveInputStream.matches(byteArray0, (byte) 0);
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0);
        DataInputStream dataInputStream0 = new DataInputStream(byteArrayInputStream0);
        byteArrayInputStream0.skip(0L);
        TarArchiveInputStream tarArchiveInputStream0 = null;
        tarArchiveInputStream0 = new TarArchiveInputStream(dataInputStream0, (byte) 0, "_?pID9k@[EV-SwopsG");
    }

    @Test(timeout = 4000)
    public void test0913() throws Throwable {
        byte[] byteArray0 = new byte[0];
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0);
        TarArchiveInputStream tarArchiveInputStream0 = new TarArchiveInputStream(byteArrayInputStream0, 0);
        TarArchiveInputStream tarArchiveInputStream1 = new TarArchiveInputStream(tarArchiveInputStream0, 0, 830);
        String string0 = "";
        TarArchiveInputStream tarArchiveInputStream2 = null;
        tarArchiveInputStream2 = new TarArchiveInputStream(tarArchiveInputStream1, 0, 0, "");
    }

    @Test(timeout = 4000)
    public void test1014() throws Throwable {
        TarArchiveInputStream tarArchiveInputStream0 = new TarArchiveInputStream((InputStream) null, 1902, 1902);
        PushbackInputStream pushbackInputStream0 = new PushbackInputStream(tarArchiveInputStream0, 1902);
        String string0 = "O";
        TarArchiveInputStream tarArchiveInputStream1 = new TarArchiveInputStream(pushbackInputStream0, "O");
        BufferedInputStream bufferedInputStream0 = new BufferedInputStream(tarArchiveInputStream1);
        TarArchiveInputStream tarArchiveInputStream2 = null;
        tarArchiveInputStream2 = new TarArchiveInputStream(bufferedInputStream0, 256, "");
    }

    @Test(timeout = 4000)
    public void test1115() throws Throwable {
        PipedOutputStream pipedOutputStream0 = new PipedOutputStream();
        PipedInputStream pipedInputStream0 = new PipedInputStream(pipedOutputStream0);
        byte[] byteArray0 = new byte[0];
        pipedOutputStream0.write(byteArray0);
        PushbackInputStream pushbackInputStream0 = new PushbackInputStream(pipedInputStream0);
        TarArchiveInputStream tarArchiveInputStream0 = new TarArchiveInputStream(pushbackInputStream0, 48, 23);
        tarArchiveInputStream0.reset();
        boolean boolean0 = tarArchiveInputStream0.isAtEOF();
        tarArchiveInputStream0.getRecordSize();
        assertEquals(1, tarArchiveInputStream0.getRecordSize());
    }

    @Test(timeout = 4000)
    public void test1116() throws Throwable {
        PipedOutputStream pipedOutputStream0 = new PipedOutputStream();
        PipedInputStream pipedInputStream0 = new PipedInputStream(pipedOutputStream0);
        byte[] byteArray0 = new byte[0];
        pipedOutputStream0.write(byteArray0);
        PushbackInputStream pushbackInputStream0 = new PushbackInputStream(pipedInputStream0);
        TarArchiveInputStream tarArchiveInputStream0 = new TarArchiveInputStream(pushbackInputStream0, 48, 23);
        tarArchiveInputStream0.reset();
        boolean boolean0 = tarArchiveInputStream0.isAtEOF();
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test1217() throws Throwable {
        Enumeration<InputStream> enumeration0 = (Enumeration<InputStream>) mock(Enumeration.class, new ViolatedAssumptionAnswer());
        doReturn(false).when(enumeration0).hasMoreElements();
        SequenceInputStream sequenceInputStream0 = new SequenceInputStream(enumeration0);
        TarArchiveInputStream tarArchiveInputStream0 = new TarArchiveInputStream(sequenceInputStream0);
        byte[] byteArray0 = new byte[9];
        byteArray0[0] = (byte) 98;
        byteArray0[1] = (byte) 0;
        byteArray0[2] = (byte) 122;
        byteArray0[3] = (byte) 20;
        byteArray0[4] = (byte) 42;
        byteArray0[5] = (byte) 3;
        byteArray0[6] = (byte) 0;
        byteArray0[7] = (byte) 0;
        byteArray0[8] = (byte) 11;
        int int0 = tarArchiveInputStream0.read(byteArray0);
    }

    @Test(timeout = 4000)
    public void test1218() throws Throwable {
        Enumeration<InputStream> enumeration0 = (Enumeration<InputStream>) mock(Enumeration.class, new ViolatedAssumptionAnswer());
        doReturn(false).when(enumeration0).hasMoreElements();
        SequenceInputStream sequenceInputStream0 = new SequenceInputStream(enumeration0);
        TarArchiveInputStream tarArchiveInputStream0 = new TarArchiveInputStream(sequenceInputStream0);
        byte[] byteArray0 = new byte[9];
        byteArray0[0] = (byte) 98;
        byteArray0[1] = (byte) 0;
        byteArray0[2] = (byte) 122;
        byteArray0[3] = (byte) 20;
        byteArray0[4] = (byte) 42;
        byteArray0[5] = (byte) 3;
        byteArray0[6] = (byte) 0;
        byteArray0[7] = (byte) 0;
        byteArray0[8] = (byte) 11;
        int int0 = tarArchiveInputStream0.read(byteArray0);
        tarArchiveInputStream0.getCurrentEntry();
        tarArchiveInputStream0.getRecordSize();
        assertEquals(1, tarArchiveInputStream0.getRecordSize());
    }
}
