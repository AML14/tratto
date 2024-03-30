/*
 * This file was automatically generated by EvoSuite
 * Sun Oct 15 07:29:36 GMT 2023
 */
package org.apache.commons.compress.archivers.tar;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.Date;
import java.util.zip.ZipEntry;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.jar.JarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.mock.java.io.MockFile;
import org.evosuite.runtime.mock.java.io.MockFileOutputStream;
import org.evosuite.runtime.mock.java.io.MockPrintStream;
import org.evosuite.runtime.mock.java.net.MockURI;
import org.evosuite.runtime.mock.java.util.MockDate;
import org.evosuite.runtime.testdata.EvoSuiteFile;
import org.evosuite.runtime.testdata.FileSystemHandling;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class TarArchiveOutputStream_ESTest extends TarArchiveOutputStream_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream(2227);
        byte[] byteArray0 = new byte[2];
        TarArchiveOutputStream tarArchiveOutputStream0 = new TarArchiveOutputStream(byteArrayOutputStream0, 0, 1332);
        tarArchiveOutputStream0.write(byteArray0, 2227, 0);
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        MockPrintStream mockPrintStream0 = new MockPrintStream("org.apache.commons.compress.archivers.zip.AbstractUnicodeExtraField");
        TarArchiveOutputStream tarArchiveOutputStream0 = new TarArchiveOutputStream(mockPrintStream0, 512, 512);
        tarArchiveOutputStream0.setLongFileMode(512);
        MockFile mockFile0 = new MockFile("org.apache.commons.compress.archivers.zip.AbstractUnicodeExtraField", "x}b2[UXFW`1/#1${. ");
        TarArchiveEntry tarArchiveEntry0 = new TarArchiveEntry(mockFile0);
        tarArchiveOutputStream0.putArchiveEntry(tarArchiveEntry0);
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream();
        TarArchiveOutputStream tarArchiveOutputStream0 = new TarArchiveOutputStream(byteArrayOutputStream0, 1, 1);
        tarArchiveOutputStream0.putArchiveEntry((ArchiveEntry) null);
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        MockPrintStream mockPrintStream0 = new MockPrintStream("opsGjde");
        TarArchiveOutputStream tarArchiveOutputStream0 = new TarArchiveOutputStream(mockPrintStream0);
        ZipEntry zipEntry0 = new ZipEntry("opsGjde");
        JarArchiveEntry jarArchiveEntry0 = new JarArchiveEntry(zipEntry0);
        tarArchiveOutputStream0.putArchiveEntry(jarArchiveEntry0);
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        MockFileOutputStream mockFileOutputStream0 = new MockFileOutputStream("Size is out of range: ", false);
        TarArchiveOutputStream tarArchiveOutputStream0 = new TarArchiveOutputStream(mockFileOutputStream0, 2479, 100);
        MockFile mockFile0 = new MockFile("]", "Vj*zW|3Z{\"S%S:d");
        ArchiveEntry archiveEntry0 = tarArchiveOutputStream0.createArchiveEntry(mockFile0, "]");
        tarArchiveOutputStream0.putArchiveEntry(archiveEntry0);
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        TarArchiveOutputStream tarArchiveOutputStream0 = new TarArchiveOutputStream((OutputStream) null, 0, 8192);
        TarArchiveEntry tarArchiveEntry0 = new TarArchiveEntry("");
        tarArchiveOutputStream0.putArchiveEntry(tarArchiveEntry0);
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        TarArchiveOutputStream tarArchiveOutputStream0 = new TarArchiveOutputStream((OutputStream) null);
        tarArchiveOutputStream0.flush();
    }

    @Test(timeout = 4000)
    public void test077() throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream(1747);
        BufferedOutputStream bufferedOutputStream0 = new BufferedOutputStream(byteArrayOutputStream0, 37);
        ObjectOutputStream objectOutputStream0 = new ObjectOutputStream(bufferedOutputStream0);
        TarArchiveOutputStream tarArchiveOutputStream0 = new TarArchiveOutputStream(objectOutputStream0, 37, 728);
        tarArchiveOutputStream0.finish();
    }

    @Test(timeout = 4000)
    public void test088() throws Throwable {
        TarArchiveOutputStream tarArchiveOutputStream0 = new TarArchiveOutputStream((OutputStream) null, 215, 215);
        tarArchiveOutputStream0.finish();
    }

    @Test(timeout = 4000)
    public void test099() throws Throwable {
        MockPrintStream mockPrintStream0 = new MockPrintStream("5,CY cLcM(YN2");
        TarArchiveOutputStream tarArchiveOutputStream0 = new TarArchiveOutputStream(mockPrintStream0);
        MockFile mockFile0 = new MockFile((File) null, "");
        tarArchiveOutputStream0.createArchiveEntry(mockFile0, " bytes)");
    }

    @Test(timeout = 4000)
    public void test1010() throws Throwable {
        MockFileOutputStream mockFileOutputStream0 = new MockFileOutputStream("k*");
        BufferedOutputStream bufferedOutputStream0 = new BufferedOutputStream(mockFileOutputStream0);
        TarArchiveOutputStream tarArchiveOutputStream0 = new TarArchiveOutputStream(bufferedOutputStream0, 8192, 166);
        tarArchiveOutputStream0.createArchiveEntry((File) null, "k*");
    }

    @Test(timeout = 4000)
    public void test1111() throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream(2227);
        TarArchiveOutputStream tarArchiveOutputStream0 = new TarArchiveOutputStream(byteArrayOutputStream0, 0, 1332);
        tarArchiveOutputStream0.close();
    }

    @Test(timeout = 4000)
    public void test1212() throws Throwable {
        TarArchiveOutputStream tarArchiveOutputStream0 = new TarArchiveOutputStream((OutputStream) null);
        tarArchiveOutputStream0.close();
    }

    @Test(timeout = 4000)
    public void test1313() throws Throwable {
        MockFileOutputStream mockFileOutputStream0 = new MockFileOutputStream("././@LongLink");
        TarArchiveOutputStream tarArchiveOutputStream0 = null;
        tarArchiveOutputStream0 = new TarArchiveOutputStream(mockFileOutputStream0, (-2001), (-2001));
    }

    @Test(timeout = 4000)
    public void test1414() throws Throwable {
        MockPrintStream mockPrintStream0 = new MockPrintStream("Size is out of range: ");
        TarArchiveOutputStream tarArchiveOutputStream0 = new TarArchiveOutputStream(mockPrintStream0);
        TarArchiveOutputStream tarArchiveOutputStream1 = null;
        tarArchiveOutputStream1 = new TarArchiveOutputStream(tarArchiveOutputStream0, 512, 0);
    }

    @Test(timeout = 4000)
    public void test1515() throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream0 = new DataOutputStream(byteArrayOutputStream0);
        BufferedOutputStream bufferedOutputStream0 = new BufferedOutputStream(dataOutputStream0);
        TarArchiveOutputStream tarArchiveOutputStream0 = new TarArchiveOutputStream(bufferedOutputStream0);
        tarArchiveOutputStream0.write((byte[]) null, 2234, 2234);
    }

    @Test(timeout = 4000)
    public void test1616() throws Throwable {
        TarArchiveOutputStream tarArchiveOutputStream0 = new TarArchiveOutputStream((OutputStream) null, 10240);
        File file0 = MockFile.createTempFile(" 83:q[%/`", " 83:q[%/`");
        TarArchiveEntry tarArchiveEntry0 = (TarArchiveEntry) tarArchiveOutputStream0.createArchiveEntry(file0, " 83:q[%/`");
        MockDate mockDate0 = new MockDate((-2579), 1000, 2917, 10240, 0);
        tarArchiveEntry0.setModTime((Date) mockDate0);
        tarArchiveOutputStream0.putArchiveEntry(tarArchiveEntry0);
    }

    @Test(timeout = 4000)
    public void test1717() throws Throwable {
        URI uRI0 = MockURI.aFileURI;
        MockFile mockFile0 = new MockFile(uRI0);
        MockPrintStream mockPrintStream0 = new MockPrintStream(mockFile0);
        TarArchiveOutputStream tarArchiveOutputStream0 = new TarArchiveOutputStream(mockPrintStream0);
        tarArchiveOutputStream0.write(420);
    }

    @Test(timeout = 4000)
    public void test1818() throws Throwable {
        URI uRI0 = MockURI.aFileURI;
        MockFile mockFile0 = new MockFile(uRI0);
        MockPrintStream mockPrintStream0 = new MockPrintStream(mockFile0);
        TarArchiveOutputStream tarArchiveOutputStream0 = new TarArchiveOutputStream(mockPrintStream0);
        tarArchiveOutputStream0.write((byte[]) null, 1117, (-3384));
    }

    @Test(timeout = 4000)
    public void test1919() throws Throwable {
        URI uRI0 = MockURI.aFileURI;
        MockFile mockFile0 = new MockFile(uRI0);
        MockPrintStream mockPrintStream0 = new MockPrintStream(mockFile0);
        EvoSuiteFile evoSuiteFile0 = new EvoSuiteFile("/tmp/foo.bar");
        FileSystemHandling.appendStringToFile(evoSuiteFile0, "cqVGd$$m9}jPOr");
        TarArchiveOutputStream tarArchiveOutputStream0 = new TarArchiveOutputStream(mockPrintStream0);
        ArchiveEntry archiveEntry0 = tarArchiveOutputStream0.createArchiveEntry(mockFile0, "cqVGd$$m9}jPOr");
        tarArchiveOutputStream0.putArchiveEntry(archiveEntry0);
        tarArchiveOutputStream0.closeArchiveEntry();
    }

    @Test(timeout = 4000)
    public void test2020() throws Throwable {
        URI uRI0 = MockURI.aFileURI;
        MockFile mockFile0 = new MockFile(uRI0);
        MockPrintStream mockPrintStream0 = new MockPrintStream(mockFile0);
        TarArchiveOutputStream tarArchiveOutputStream0 = new TarArchiveOutputStream(mockPrintStream0);
        tarArchiveOutputStream0.closeArchiveEntry();
    }

    @Test(timeout = 4000)
    public void test2121() throws Throwable {
        MockPrintStream mockPrintStream0 = new MockPrintStream(".)@Xuhr_H;VxmdX@7y");
        TarArchiveOutputStream tarArchiveOutputStream0 = new TarArchiveOutputStream(mockPrintStream0);
        MockFile mockFile0 = new MockFile("");
        TarArchiveEntry tarArchiveEntry0 = (TarArchiveEntry) tarArchiveOutputStream0.createArchiveEntry(mockFile0, "");
        tarArchiveOutputStream0.putArchiveEntry(tarArchiveEntry0);
        tarArchiveEntry0.getUserName();
        assertNotNull(tarArchiveEntry0.getUserName());
    }

    @Test(timeout = 4000)
    public void test2222() throws Throwable {
        MockPrintStream mockPrintStream0 = new MockPrintStream("org.apache.commons.compress.archivers.zip.AbstractUnicodeExtraField");
        TarArchiveOutputStream tarArchiveOutputStream0 = new TarArchiveOutputStream(mockPrintStream0, 512, 512);
        MockFile mockFile0 = new MockFile("org.apache.commons.compress.archivers.zip.AbstractUnicodeExtraField", "x}b2[UXFW`1/#1${. ");
        TarArchiveEntry tarArchiveEntry0 = new TarArchiveEntry(mockFile0);
        tarArchiveOutputStream0.putArchiveEntry(tarArchiveEntry0);
    }

    @Test(timeout = 4000)
    public void test2323() throws Throwable {
        URI uRI0 = MockURI.aFileURI;
        MockFile mockFile0 = new MockFile(uRI0);
        MockPrintStream mockPrintStream0 = new MockPrintStream(mockFile0);
        TarArchiveOutputStream tarArchiveOutputStream0 = new TarArchiveOutputStream(mockPrintStream0);
        tarArchiveOutputStream0.close();
        tarArchiveOutputStream0.close();
        mockFile0.length();
    }

    @Test(timeout = 4000)
    public void test2424() throws Throwable {
        MockPrintStream mockPrintStream0 = new MockPrintStream("opsGjde");
        TarArchiveOutputStream tarArchiveOutputStream0 = new TarArchiveOutputStream(mockPrintStream0);
        int int0 = tarArchiveOutputStream0.getRecordSize();
        assertEquals(1, int0);
    }

    @Test(timeout = 4000)
    public void test2525() throws Throwable {
        MockFile mockFile0 = new MockFile("", ":");
        MockFileOutputStream mockFileOutputStream0 = new MockFileOutputStream(mockFile0, true);
        DataOutputStream dataOutputStream0 = new DataOutputStream(mockFileOutputStream0);
        TarArchiveOutputStream tarArchiveOutputStream0 = null;
        tarArchiveOutputStream0 = new TarArchiveOutputStream(dataOutputStream0, (-2448));
    }

    @Test(timeout = 4000)
    public void test2626() throws Throwable {
        MockPrintStream mockPrintStream0 = new MockPrintStream("L9kQ9,T2?fnVgtc");
        TarArchiveOutputStream tarArchiveOutputStream0 = new TarArchiveOutputStream(mockPrintStream0);
        tarArchiveOutputStream0.flush();
    }

    @Test(timeout = 4000)
    public void test2727() throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream0 = new DataOutputStream(byteArrayOutputStream0);
        BufferedOutputStream bufferedOutputStream0 = new BufferedOutputStream(dataOutputStream0);
        TarArchiveOutputStream tarArchiveOutputStream0 = new TarArchiveOutputStream(bufferedOutputStream0);
        tarArchiveOutputStream0.finish();
        byteArrayOutputStream0.size();
    }

    @Test(timeout = 4000)
    public void test2728() throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream0 = new DataOutputStream(byteArrayOutputStream0);
        BufferedOutputStream bufferedOutputStream0 = new BufferedOutputStream(dataOutputStream0);
        TarArchiveOutputStream tarArchiveOutputStream0 = new TarArchiveOutputStream(bufferedOutputStream0);
        tarArchiveOutputStream0.finish();
        byteArrayOutputStream0.toString();
    }
}
