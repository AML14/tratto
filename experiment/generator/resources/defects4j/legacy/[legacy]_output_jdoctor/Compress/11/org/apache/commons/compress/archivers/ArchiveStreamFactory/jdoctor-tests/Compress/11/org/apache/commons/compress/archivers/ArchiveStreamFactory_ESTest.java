/*
 * This file was automatically generated by EvoSuite
 * Sun Oct 15 07:46:26 GMT 2023
 */
package org.apache.commons.compress.archivers;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.FileDescriptor;
import java.io.FilterOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PushbackInputStream;
import java.io.SequenceInputStream;
import java.util.Enumeration;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.ar.ArArchiveOutputStream;
import org.apache.commons.compress.archivers.jar.JarArchiveOutputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.evosuite.runtime.mock.java.io.MockFile;
import org.evosuite.runtime.mock.java.io.MockFileInputStream;
import org.evosuite.runtime.mock.java.io.MockFileOutputStream;
import org.evosuite.runtime.mock.java.io.MockPrintStream;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class ArchiveStreamFactory_ESTest extends ArchiveStreamFactory_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        ArchiveStreamFactory archiveStreamFactory0 = new ArchiveStreamFactory();
        FileDescriptor fileDescriptor0 = new FileDescriptor();
        MockFileInputStream mockFileInputStream0 = new MockFileInputStream(fileDescriptor0);
        BufferedInputStream bufferedInputStream0 = new BufferedInputStream(mockFileInputStream0, 1881);
        archiveStreamFactory0.createArchiveInputStream((InputStream) bufferedInputStream0);
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        ArchiveStreamFactory archiveStreamFactory0 = new ArchiveStreamFactory();
        DataInputStream dataInputStream0 = new DataInputStream((InputStream) null);
        archiveStreamFactory0.createArchiveInputStream((InputStream) dataInputStream0);
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        ArchiveStreamFactory archiveStreamFactory0 = new ArchiveStreamFactory();
        byte[] byteArray0 = new byte[8];
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0, (byte) (-115), 1388);
        DataInputStream dataInputStream0 = new DataInputStream(byteArrayInputStream0);
        BufferedInputStream bufferedInputStream0 = new BufferedInputStream(dataInputStream0);
        archiveStreamFactory0.createArchiveInputStream((InputStream) bufferedInputStream0);
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        ArchiveStreamFactory archiveStreamFactory0 = new ArchiveStreamFactory();
        Enumeration<InputStream> enumeration0 = (Enumeration<InputStream>) mock(Enumeration.class, new ViolatedAssumptionAnswer());
        doReturn(false).when(enumeration0).hasMoreElements();
        SequenceInputStream sequenceInputStream0 = new SequenceInputStream(enumeration0);
        BufferedInputStream bufferedInputStream0 = new BufferedInputStream(sequenceInputStream0);
        PushbackInputStream pushbackInputStream0 = new PushbackInputStream(bufferedInputStream0, 12);
        archiveStreamFactory0.createArchiveInputStream((InputStream) pushbackInputStream0);
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        ArchiveStreamFactory archiveStreamFactory0 = new ArchiveStreamFactory();
        archiveStreamFactory0.createArchiveInputStream((InputStream) null);
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        ArchiveStreamFactory archiveStreamFactory0 = new ArchiveStreamFactory();
        ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream();
        archiveStreamFactory0.createArchiveOutputStream("dump", byteArrayOutputStream0);
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        ArchiveStreamFactory archiveStreamFactory0 = new ArchiveStreamFactory();
        PipedOutputStream pipedOutputStream0 = new PipedOutputStream();
        ArchiveOutputStream archiveOutputStream0 = archiveStreamFactory0.createArchiveOutputStream("cpio", pipedOutputStream0);
    }

    @Test(timeout = 4000)
    public void test077() throws Throwable {
        ArchiveStreamFactory archiveStreamFactory0 = new ArchiveStreamFactory();
        ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream();
        TarArchiveOutputStream tarArchiveOutputStream0 = (TarArchiveOutputStream) archiveStreamFactory0.createArchiveOutputStream("tar", byteArrayOutputStream0);
    }

    @Test(timeout = 4000)
    public void test088() throws Throwable {
        ArchiveStreamFactory archiveStreamFactory0 = new ArchiveStreamFactory();
        MockFile mockFile0 = new MockFile("cpio", "L_?Qx<(wwxMQxd~k}&");
        MockFileOutputStream mockFileOutputStream0 = new MockFileOutputStream(mockFile0, false);
        MockPrintStream mockPrintStream0 = new MockPrintStream(mockFileOutputStream0, false);
        JarArchiveOutputStream jarArchiveOutputStream0 = (JarArchiveOutputStream) archiveStreamFactory0.createArchiveOutputStream("jar", mockPrintStream0);
        jarArchiveOutputStream0.getEncoding();
    }

    @Test(timeout = 4000)
    public void test099() throws Throwable {
        ArchiveStreamFactory archiveStreamFactory0 = new ArchiveStreamFactory();
        ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream();
        FilterOutputStream filterOutputStream0 = new FilterOutputStream(byteArrayOutputStream0);
        ArArchiveOutputStream arArchiveOutputStream0 = (ArArchiveOutputStream) archiveStreamFactory0.createArchiveOutputStream("ar", filterOutputStream0);
    }

    @Test(timeout = 4000)
    public void test1010() throws Throwable {
        ArchiveStreamFactory archiveStreamFactory0 = new ArchiveStreamFactory();
        org.apache.commons.compress.archivers.ArchiveOutputStream default0;
        if ("BokyF;97;ZK9g9W%|~" == null) {
            try {
                default0 = archiveStreamFactory0.createArchiveOutputStream("BokyF;97;ZK9g9W%|~", (OutputStream) null);
                fail();
            } catch (java.lang.IllegalArgumentException e) {
                // Successfully thrown exception
            }
        } else {
            default0 = archiveStreamFactory0.createArchiveOutputStream("BokyF;97;ZK9g9W%|~", (OutputStream) null);
        }
    }

    @Test(timeout = 4000)
    public void test1111() throws Throwable {
        ArchiveStreamFactory archiveStreamFactory0 = new ArchiveStreamFactory();
        ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream(657);
        archiveStreamFactory0.createArchiveOutputStream((String) null, byteArrayOutputStream0);
    }

    @Test(timeout = 4000)
    public void test1212() throws Throwable {
        ArchiveStreamFactory archiveStreamFactory0 = new ArchiveStreamFactory();
        ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream();
        ZipArchiveOutputStream zipArchiveOutputStream0 = (ZipArchiveOutputStream) archiveStreamFactory0.createArchiveOutputStream("zip", byteArrayOutputStream0);
    }

    @Test(timeout = 4000)
    public void test1313() throws Throwable {
        ArchiveStreamFactory archiveStreamFactory0 = new ArchiveStreamFactory();
        byte[] byteArray0 = new byte[2];
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0, (byte) 24, (byte) 24);
        archiveStreamFactory0.createArchiveInputStream("dump", (InputStream) byteArrayInputStream0);
    }

    @Test(timeout = 4000)
    public void test1414() throws Throwable {
        ArchiveStreamFactory archiveStreamFactory0 = new ArchiveStreamFactory();
        PipedOutputStream pipedOutputStream0 = new PipedOutputStream();
        PipedInputStream pipedInputStream0 = new PipedInputStream(pipedOutputStream0);
        archiveStreamFactory0.createArchiveInputStream("]e", (InputStream) pipedInputStream0);
    }

    @Test(timeout = 4000)
    public void test1515() throws Throwable {
        ArchiveStreamFactory archiveStreamFactory0 = new ArchiveStreamFactory();
        byte[] byteArray0 = new byte[1];
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0);
        ArchiveInputStream archiveInputStream0 = archiveStreamFactory0.createArchiveInputStream((InputStream) byteArrayInputStream0);
        ArchiveInputStream archiveInputStream1 = archiveStreamFactory0.createArchiveInputStream("jar", (InputStream) archiveInputStream0);
    }

    @Test(timeout = 4000)
    public void test1616() throws Throwable {
        ArchiveStreamFactory archiveStreamFactory0 = new ArchiveStreamFactory();
        byte[] byteArray0 = new byte[4];
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0);
        BufferedInputStream bufferedInputStream0 = new BufferedInputStream(byteArrayInputStream0, 498);
        TarArchiveInputStream tarArchiveInputStream0 = (TarArchiveInputStream) archiveStreamFactory0.createArchiveInputStream("tar", (InputStream) bufferedInputStream0);
        tarArchiveInputStream0.getRecordSize();
    }

    @Test(timeout = 4000)
    public void test1717() throws Throwable {
        ArchiveStreamFactory archiveStreamFactory0 = new ArchiveStreamFactory();
        byte[] byteArray0 = new byte[1];
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0);
        ArchiveInputStream archiveInputStream0 = archiveStreamFactory0.createArchiveInputStream((InputStream) byteArrayInputStream0);
        ArchiveInputStream archiveInputStream1 = archiveStreamFactory0.createArchiveInputStream("zip", (InputStream) archiveInputStream0);
    }

    @Test(timeout = 4000)
    public void test1818() throws Throwable {
        ArchiveStreamFactory archiveStreamFactory0 = new ArchiveStreamFactory();
        PipedInputStream pipedInputStream0 = new PipedInputStream();
        ArchiveInputStream archiveInputStream0 = archiveStreamFactory0.createArchiveInputStream("ar", (InputStream) pipedInputStream0);
    }

    @Test(timeout = 4000)
    public void test1919() throws Throwable {
        ArchiveStreamFactory archiveStreamFactory0 = new ArchiveStreamFactory();
        org.apache.commons.compress.archivers.ArchiveInputStream default1;
        if ("=U.L$D" == null) {
            try {
                default1 = archiveStreamFactory0.createArchiveInputStream("=U.L$D", (InputStream) null);
                fail();
            } catch (java.lang.IllegalArgumentException e) {
                // Successfully thrown exception
            }
        } else {
            default1 = archiveStreamFactory0.createArchiveInputStream("=U.L$D", (InputStream) null);
        }
    }

    @Test(timeout = 4000)
    public void test2020() throws Throwable {
        ArchiveStreamFactory archiveStreamFactory0 = new ArchiveStreamFactory();
        byte[] byteArray0 = new byte[13];
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0, (byte) 14, (byte) 14);
        archiveStreamFactory0.createArchiveInputStream((String) null, (InputStream) byteArrayInputStream0);
    }

    @Test(timeout = 4000)
    public void test2121() throws Throwable {
        ArchiveStreamFactory archiveStreamFactory0 = new ArchiveStreamFactory();
        byte[] byteArray0 = new byte[1];
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0);
        ArchiveInputStream archiveInputStream0 = archiveStreamFactory0.createArchiveInputStream((InputStream) byteArrayInputStream0);
        ArchiveInputStream archiveInputStream1 = archiveStreamFactory0.createArchiveInputStream("cpio", (InputStream) archiveInputStream0);
    }
}
