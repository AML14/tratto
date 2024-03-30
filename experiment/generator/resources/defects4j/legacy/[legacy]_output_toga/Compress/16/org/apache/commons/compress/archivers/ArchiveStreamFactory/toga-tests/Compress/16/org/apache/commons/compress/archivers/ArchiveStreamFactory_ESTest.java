/*
 * This file was automatically generated by EvoSuite
 * Sun Oct 15 08:33:53 GMT 2023
 */
package org.apache.commons.compress.archivers;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.io.ByteArrayInputStream;
import java.io.FileDescriptor;
import java.io.InputStream;
import java.io.PipedInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.mock.java.io.MockFile;
import org.evosuite.runtime.mock.java.io.MockFileInputStream;
import org.evosuite.runtime.mock.java.io.MockFileOutputStream;
import org.evosuite.runtime.mock.java.io.MockPrintStream;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class ArchiveStreamFactory_ESTest extends ArchiveStreamFactory_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test00() throws Throwable {
        ArchiveStreamFactory archiveStreamFactory0 = new ArchiveStreamFactory();
        FileDescriptor fileDescriptor0 = new FileDescriptor();
        MockFileInputStream mockFileInputStream0 = new MockFileInputStream(fileDescriptor0);
        archiveStreamFactory0.createArchiveInputStream((InputStream) mockFileInputStream0);
    }

    @Test(timeout = 4000)
    public void test11() throws Throwable {
        ArchiveStreamFactory archiveStreamFactory0 = new ArchiveStreamFactory();
        archiveStreamFactory0.createArchiveInputStream((InputStream) null);
    }

    @Test(timeout = 4000)
    public void test22() throws Throwable {
        ArchiveStreamFactory archiveStreamFactory0 = new ArchiveStreamFactory();
        byte[] byteArray0 = new byte[0];
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0);
        archiveStreamFactory0.createArchiveInputStream((InputStream) byteArrayInputStream0);
    }

    @Test(timeout = 4000)
    public void test33() throws Throwable {
        ArchiveStreamFactory archiveStreamFactory0 = new ArchiveStreamFactory();
        MockFile mockFile0 = new MockFile("cpio", "L_?Qx<(wwxMQxd~k}&");
        MockFileOutputStream mockFileOutputStream0 = new MockFileOutputStream(mockFile0, false);
        MockPrintStream mockPrintStream0 = new MockPrintStream(mockFileOutputStream0, false);
        archiveStreamFactory0.createArchiveOutputStream(";Sb^XM}", mockPrintStream0);
    }

    @Test(timeout = 4000)
    public void test44() throws Throwable {
        ArchiveStreamFactory archiveStreamFactory0 = new ArchiveStreamFactory();
        PipedInputStream pipedInputStream0 = new PipedInputStream();
        archiveStreamFactory0.createArchiveInputStream("org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream$UnicodeExtraFieldPolicy", (InputStream) pipedInputStream0);
    }
}
