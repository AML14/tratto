/*
 * This file was automatically generated by EvoSuite
 * Sun Oct 15 07:26:54 GMT 2023
 */
package org.apache.commons.compress.archivers.cpio;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import org.apache.commons.compress.archivers.cpio.CpioArchiveEntry;
import org.apache.commons.compress.archivers.cpio.CpioArchiveOutputStream;
import org.apache.commons.compress.archivers.jar.JarArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.mock.java.io.MockFile;
import org.evosuite.runtime.mock.java.io.MockFileOutputStream;
import org.evosuite.runtime.mock.java.io.MockPrintStream;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class CpioArchiveOutputStream_ESTest extends CpioArchiveOutputStream_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        CpioArchiveOutputStream cpioArchiveOutputStream0 = new CpioArchiveOutputStream((OutputStream) null);
        byte[] byteArray0 = new byte[3];
        cpioArchiveOutputStream0.write(byteArray0, 0, 0);
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        MockFileOutputStream mockFileOutputStream0 = new MockFileOutputStream("YhNE?");
        CpioArchiveOutputStream cpioArchiveOutputStream0 = new CpioArchiveOutputStream(mockFileOutputStream0, (short) 4);
        CpioArchiveEntry cpioArchiveEntry0 = new CpioArchiveEntry("YhNE?");
        cpioArchiveOutputStream0.putArchiveEntry(cpioArchiveEntry0);
        cpioArchiveEntry0.isPipe();
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        MockPrintStream mockPrintStream0 = new MockPrintStream("F/'-[TRfq&Hg?");
        CpioArchiveOutputStream cpioArchiveOutputStream0 = new CpioArchiveOutputStream(mockPrintStream0);
        cpioArchiveOutputStream0.write((byte[]) null, 1440, 850);
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        CpioArchiveOutputStream cpioArchiveOutputStream0 = new CpioArchiveOutputStream((OutputStream) null);
        byte[] byteArray0 = new byte[0];
        cpioArchiveOutputStream0.close();
        cpioArchiveOutputStream0.write(byteArray0, 3, 0);
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        CpioArchiveOutputStream cpioArchiveOutputStream0 = new CpioArchiveOutputStream((OutputStream) null);
        cpioArchiveOutputStream0.write(0);
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        MockFileOutputStream mockFileOutputStream0 = new MockFileOutputStream("no current CPIO entry");
        mockFileOutputStream0.close();
        CpioArchiveOutputStream cpioArchiveOutputStream0 = new CpioArchiveOutputStream(mockFileOutputStream0);
        cpioArchiveOutputStream0.write(1309);
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream();
        CpioArchiveOutputStream cpioArchiveOutputStream0 = new CpioArchiveOutputStream(byteArrayOutputStream0);
        CpioArchiveEntry cpioArchiveEntry0 = new CpioArchiveEntry((String) null, 1130L);
        cpioArchiveOutputStream0.putNextEntry(cpioArchiveEntry0);
    }

    @Test(timeout = 4000)
    public void test077() throws Throwable {
        CpioArchiveOutputStream cpioArchiveOutputStream0 = new CpioArchiveOutputStream((OutputStream) null);
        ZipArchiveEntry zipArchiveEntry0 = new ZipArchiveEntry("'s no-arg constructor is not public");
        JarArchiveEntry jarArchiveEntry0 = new JarArchiveEntry((ZipEntry) zipArchiveEntry0);
        cpioArchiveOutputStream0.putArchiveEntry(jarArchiveEntry0);
    }

    @Test(timeout = 4000)
    public void test088() throws Throwable {
        CpioArchiveOutputStream cpioArchiveOutputStream0 = new CpioArchiveOutputStream((OutputStream) null);
        cpioArchiveOutputStream0.finish();
    }

    @Test(timeout = 4000)
    public void test099() throws Throwable {
        MockFile mockFile0 = new MockFile("Q_H ^I:?pEqpZb${bE", "Q_H ^I:?pEqpZb${bE");
        MockPrintStream mockPrintStream0 = new MockPrintStream(mockFile0);
        CpioArchiveOutputStream cpioArchiveOutputStream0 = new CpioArchiveOutputStream(mockPrintStream0);
        CpioArchiveEntry cpioArchiveEntry0 = new CpioArchiveEntry("070707");
        cpioArchiveEntry0.setSize(83L);
        cpioArchiveOutputStream0.putNextEntry(cpioArchiveEntry0);
        cpioArchiveOutputStream0.finish();
    }

    @Test(timeout = 4000)
    public void test1010() throws Throwable {
        MockFile mockFile0 = new MockFile("CRC Error", " bytes)");
        MockFileOutputStream mockFileOutputStream0 = new MockFileOutputStream(mockFile0);
        CpioArchiveOutputStream cpioArchiveOutputStream0 = new CpioArchiveOutputStream(mockFileOutputStream0, (short) 2);
        cpioArchiveOutputStream0.closeArchiveEntry();
    }

    @Test(timeout = 4000)
    public void test1211() throws Throwable {
        MockFile mockFile0 = new MockFile("Q_H ^I:?pEqpZb${bE", "Q_H ^I:?pEqpZb${bE");
        MockPrintStream mockPrintStream0 = new MockPrintStream(mockFile0);
        CpioArchiveOutputStream cpioArchiveOutputStream0 = new CpioArchiveOutputStream(mockPrintStream0);
        CpioArchiveEntry cpioArchiveEntry0 = new CpioArchiveEntry("Q_H ^I:?pEqpZb${bE");
        cpioArchiveOutputStream0.putNextEntry(cpioArchiveEntry0);
        cpioArchiveOutputStream0.finish();
        mockFile0.length();
    }

    @Test(timeout = 4000)
    public void test1312() throws Throwable {
        MockFile mockFile0 = new MockFile("Q_H ^I:?pEqpZb${bE", "Q_H ^I:?pEqpZb${bE");
        MockPrintStream mockPrintStream0 = new MockPrintStream(mockFile0);
        CpioArchiveOutputStream cpioArchiveOutputStream0 = new CpioArchiveOutputStream(mockPrintStream0);
        CpioArchiveEntry cpioArchiveEntry0 = new CpioArchiveEntry("Q_H ^I:?pEqpZb${bE");
        cpioArchiveOutputStream0.putNextEntry(cpioArchiveEntry0);
        byte[] byteArray0 = new byte[1];
        cpioArchiveOutputStream0.write(byteArray0);
    }

    @Test(timeout = 4000)
    public void test1413() throws Throwable {
        MockFileOutputStream mockFileOutputStream0 = new MockFileOutputStream("YhNE?");
        byte[] byteArray0 = new byte[3];
        CpioArchiveOutputStream cpioArchiveOutputStream0 = new CpioArchiveOutputStream(mockFileOutputStream0);
        cpioArchiveOutputStream0.write(byteArray0, (int) (byte) 124, (int) (short) 4);
    }

    @Test(timeout = 4000)
    public void test1514() throws Throwable {
        CpioArchiveOutputStream cpioArchiveOutputStream0 = new CpioArchiveOutputStream((OutputStream) null);
        byte[] byteArray0 = new byte[1];
        cpioArchiveOutputStream0.write(byteArray0, 409, (int) (byte) (-115));
    }

    @Test(timeout = 4000)
    public void test1615() throws Throwable {
        MockFile mockFile0 = new MockFile("Q_H ^I:?pEqpZb${bE", "Q_H ^I:?pEqpZb${bE");
        MockPrintStream mockPrintStream0 = new MockPrintStream(mockFile0);
        CpioArchiveOutputStream cpioArchiveOutputStream0 = new CpioArchiveOutputStream(mockPrintStream0);
        byte[] byteArray0 = new byte[1];
        cpioArchiveOutputStream0.write(byteArray0);
    }

    @Test(timeout = 4000)
    public void test1716() throws Throwable {
        MockPrintStream mockPrintStream0 = new MockPrintStream("c@T");
        CpioArchiveOutputStream cpioArchiveOutputStream0 = new CpioArchiveOutputStream(mockPrintStream0, (short) 8);
        byte[] byteArray0 = new byte[5];
        cpioArchiveOutputStream0.write(byteArray0, (-3345), (int) (byte) 0);
    }

    @Test(timeout = 4000)
    public void test1817() throws Throwable {
        MockFile mockFile0 = new MockFile("Q_H ^I:?pEqpZb${bE", "Q_H ^I:?pEqpZb${bE");
        MockPrintStream mockPrintStream0 = new MockPrintStream(mockFile0);
        CpioArchiveOutputStream cpioArchiveOutputStream0 = new CpioArchiveOutputStream(mockPrintStream0);
        CpioArchiveEntry cpioArchiveEntry0 = new CpioArchiveEntry("070707");
        cpioArchiveEntry0.setSize(83L);
        cpioArchiveOutputStream0.putNextEntry(cpioArchiveEntry0);
        cpioArchiveOutputStream0.closeArchiveEntry();
    }

    @Test(timeout = 4000)
    public void test1918() throws Throwable {
        MockFile mockFile0 = new MockFile("Q_H ^I:?pEqpZb${bE", "Q_H ^I:?pEqpZb${bE");
        MockPrintStream mockPrintStream0 = new MockPrintStream(mockFile0);
        CpioArchiveOutputStream cpioArchiveOutputStream0 = new CpioArchiveOutputStream(mockPrintStream0);
        CpioArchiveEntry cpioArchiveEntry0 = new CpioArchiveEntry("070707");
        cpioArchiveOutputStream0.putNextEntry(cpioArchiveEntry0);
        cpioArchiveOutputStream0.putNextEntry(cpioArchiveEntry0);
    }

    @Test(timeout = 4000)
    public void test2019() throws Throwable {
        CpioArchiveOutputStream cpioArchiveOutputStream0 = null;
        cpioArchiveOutputStream0 = new CpioArchiveOutputStream((OutputStream) null, (short) (-1));
    }

    @Test(timeout = 4000)
    public void test2120() throws Throwable {
        File file0 = MockFile.createTempFile("Stream closed", "Stream closed");
        MockPrintStream mockPrintStream0 = new MockPrintStream(file0);
        CpioArchiveOutputStream cpioArchiveOutputStream0 = null;
        cpioArchiveOutputStream0 = new CpioArchiveOutputStream(mockPrintStream0, (short) 7);
    }

    @Test(timeout = 4000)
    public void test2221() throws Throwable {
        MockFileOutputStream mockFileOutputStream0 = new MockFileOutputStream("duplicate entry: ", true);
        FilterOutputStream filterOutputStream0 = new FilterOutputStream(mockFileOutputStream0);
        CpioArchiveOutputStream cpioArchiveOutputStream0 = null;
        cpioArchiveOutputStream0 = new CpioArchiveOutputStream(filterOutputStream0, (short) 6);
    }

    @Test(timeout = 4000)
    public void test2322() throws Throwable {
        CpioArchiveOutputStream cpioArchiveOutputStream0 = null;
        cpioArchiveOutputStream0 = new CpioArchiveOutputStream((OutputStream) null, (short) 5);
    }

    @Test(timeout = 4000)
    public void test2423() throws Throwable {
        CpioArchiveOutputStream cpioArchiveOutputStream0 = null;
        cpioArchiveOutputStream0 = new CpioArchiveOutputStream((OutputStream) null, (short) 3);
    }

    @Test(timeout = 4000)
    public void test2524() throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream((short) 2);
        CpioArchiveOutputStream cpioArchiveOutputStream0 = new CpioArchiveOutputStream(byteArrayOutputStream0, (short) 2);
        cpioArchiveOutputStream0.finish();
        byteArrayOutputStream0.toString();
    }

    @Test(timeout = 4000)
    public void test2525() throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream((short) 2);
        CpioArchiveOutputStream cpioArchiveOutputStream0 = new CpioArchiveOutputStream(byteArrayOutputStream0, (short) 2);
        cpioArchiveOutputStream0.finish();
        byteArrayOutputStream0.size();
    }

    @Test(timeout = 4000)
    public void test2626() throws Throwable {
        CpioArchiveOutputStream cpioArchiveOutputStream0 = new CpioArchiveOutputStream((OutputStream) null);
        CpioArchiveEntry cpioArchiveEntry0 = new CpioArchiveEntry("org.apache.commons.compress.archivers.zip.JarMarker");
        cpioArchiveOutputStream0.close();
        cpioArchiveOutputStream0.putArchiveEntry(cpioArchiveEntry0);
    }

    @Test(timeout = 4000)
    public void test3127() throws Throwable {
        CpioArchiveOutputStream cpioArchiveOutputStream0 = new CpioArchiveOutputStream((OutputStream) null);
        CpioArchiveEntry cpioArchiveEntry0 = new CpioArchiveEntry(" instead of ");
        cpioArchiveOutputStream0.putArchiveEntry(cpioArchiveEntry0);
    }
}
