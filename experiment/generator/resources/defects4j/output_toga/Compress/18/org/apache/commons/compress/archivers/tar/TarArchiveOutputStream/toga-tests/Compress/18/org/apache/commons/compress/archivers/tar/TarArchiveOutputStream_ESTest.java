/*
 * This file was automatically generated by EvoSuite
 * Sun Oct 15 08:51:09 GMT 2023
 */
package org.apache.commons.compress.archivers.tar;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.charset.IllegalCharsetNameException;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.utils.CountingOutputStream;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.mock.java.io.MockFile;
import org.evosuite.runtime.mock.java.io.MockFileOutputStream;
import org.evosuite.runtime.mock.java.io.MockPrintStream;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class TarArchiveOutputStream_ESTest extends TarArchiveOutputStream_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        TarArchiveOutputStream tarArchiveOutputStream0 = new TarArchiveOutputStream((OutputStream) null, 3);
        byte[] byteArray0 = new byte[6];
        tarArchiveOutputStream0.write(byteArray0, (int) (byte) (-1), (int) (byte) (-1));
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream();
        TarArchiveOutputStream tarArchiveOutputStream0 = new TarArchiveOutputStream(byteArrayOutputStream0, 2958, "org.apache.commons.compress.archivers.zip.Zip64ExtendedInformationExtraField");
        tarArchiveOutputStream0.setLongFileMode(898);
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream();
        TarArchiveOutputStream tarArchiveOutputStream0 = new TarArchiveOutputStream(byteArrayOutputStream0, 2958, "org.apache.commons.compress.archivers.zip.Zip64ExtendedInformationExtraField");
        tarArchiveOutputStream0.finish();
        tarArchiveOutputStream0.getCount();
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        File file0 = MockFile.createTempFile("XJ_", "XJ_");
        MockFileOutputStream mockFileOutputStream0 = new MockFileOutputStream(file0, false);
        TarArchiveOutputStream tarArchiveOutputStream0 = new TarArchiveOutputStream(mockFileOutputStream0, "XJ_");
        tarArchiveOutputStream0.createArchiveEntry(file0, "");
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        File file0 = MockFile.createTempFile("XJ_", "XJ_");
        MockFileOutputStream mockFileOutputStream0 = new MockFileOutputStream(file0, false);
        TarArchiveOutputStream tarArchiveOutputStream0 = new TarArchiveOutputStream(mockFileOutputStream0, "XJ_");
        Map<String, String> map0 = ZoneId.SHORT_IDS;
        tarArchiveOutputStream0.writePaxHeaders((String) null, map0);
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        MockFile mockFile0 = new MockFile("wIp{s2qfiJD2");
        MockPrintStream mockPrintStream0 = new MockPrintStream(mockFile0);
        TarArchiveOutputStream tarArchiveOutputStream0 = new TarArchiveOutputStream(mockPrintStream0, (byte) 48, 193);
        Map<String, String> map0 = ZoneId.SHORT_IDS;
        tarArchiveOutputStream0.writePaxHeaders("", map0);
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        TarArchiveOutputStream tarArchiveOutputStream0 = new TarArchiveOutputStream((OutputStream) null, 249);
        tarArchiveOutputStream0.flush();
    }

    @Test(timeout = 4000)
    public void test077() throws Throwable {
        TarArchiveOutputStream tarArchiveOutputStream0 = new TarArchiveOutputStream((OutputStream) null, 2241);
        tarArchiveOutputStream0.finish();
    }

    @Test(timeout = 4000)
    public void test088() throws Throwable {
        MockPrintStream mockPrintStream0 = new MockPrintStream("Ejd5R|eeb^5z4*");
        TarArchiveOutputStream tarArchiveOutputStream0 = new TarArchiveOutputStream(mockPrintStream0, 57);
        tarArchiveOutputStream0.finish();
    }

    @Test(timeout = 4000)
    public void test099() throws Throwable {
        PipedOutputStream pipedOutputStream0 = new PipedOutputStream();
        TarArchiveOutputStream tarArchiveOutputStream0 = new TarArchiveOutputStream(pipedOutputStream0, 468);
        tarArchiveOutputStream0.finish();
    }

    @Test(timeout = 4000)
    public void test1010() throws Throwable {
        TarArchiveOutputStream tarArchiveOutputStream0 = new TarArchiveOutputStream((OutputStream) null, 2241);
        tarArchiveOutputStream0.close();
    }

    @Test(timeout = 4000)
    public void test1111() throws Throwable {
        TarArchiveOutputStream tarArchiveOutputStream0 = null;
        tarArchiveOutputStream0 = new TarArchiveOutputStream((OutputStream) null, "1*vz+F`<>Ky&");
    }

    @Test(timeout = 4000)
    public void test1212() throws Throwable {
        TarArchiveOutputStream tarArchiveOutputStream0 = null;
        tarArchiveOutputStream0 = new TarArchiveOutputStream((OutputStream) null, 2028, "#");
    }

    @Test(timeout = 4000)
    public void test1313() throws Throwable {
        MockFileOutputStream mockFileOutputStream0 = new MockFileOutputStream(".m7{au(E", true);
        TarArchiveOutputStream tarArchiveOutputStream0 = null;
        tarArchiveOutputStream0 = new TarArchiveOutputStream(mockFileOutputStream0, 3, 0, "");
    }

    @Test(timeout = 4000)
    public void test1414() throws Throwable {
        MockFileOutputStream mockFileOutputStream0 = new MockFileOutputStream("size");
        TarArchiveOutputStream tarArchiveOutputStream0 = null;
        tarArchiveOutputStream0 = new TarArchiveOutputStream(mockFileOutputStream0, (-1823), (-192), "size");
    }

    @Test(timeout = 4000)
    public void test1515() throws Throwable {
        File file0 = MockFile.createTempFile("])u3J8n5D`", "])u3J8n5D`");
        MockFileOutputStream mockFileOutputStream0 = new MockFileOutputStream(file0, true);
        TarArchiveOutputStream tarArchiveOutputStream0 = null;
        tarArchiveOutputStream0 = new TarArchiveOutputStream(mockFileOutputStream0, 0, 0, (String) null);
    }

    @Test(timeout = 4000)
    public void test1616() throws Throwable {
        MockFile mockFile0 = new MockFile("DIRECTORY");
        MockPrintStream mockPrintStream0 = new MockPrintStream(mockFile0);
        TarArchiveOutputStream tarArchiveOutputStream0 = null;
        tarArchiveOutputStream0 = new TarArchiveOutputStream(mockPrintStream0, (-2977), (-2977));
    }

    @Test(timeout = 4000)
    public void test1717() throws Throwable {
        MockFile mockFile0 = new MockFile("!~.");
        MockPrintStream mockPrintStream0 = new MockPrintStream(mockFile0);
        TarArchiveOutputStream tarArchiveOutputStream0 = null;
        tarArchiveOutputStream0 = new TarArchiveOutputStream(mockPrintStream0, 0, 0);
    }

    @Test(timeout = 4000)
    public void test1818() throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream(2);
        BufferedOutputStream bufferedOutputStream0 = new BufferedOutputStream(byteArrayOutputStream0);
        TarArchiveOutputStream tarArchiveOutputStream0 = null;
        tarArchiveOutputStream0 = new TarArchiveOutputStream(bufferedOutputStream0, (-295));
    }

    @Test(timeout = 4000)
    public void test1919() throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream();
        TarArchiveOutputStream tarArchiveOutputStream0 = new TarArchiveOutputStream(byteArrayOutputStream0, 2958, "org.apache.commons.compress.archivers.zip.Zip64ExtendedInformationExtraField");
        tarArchiveOutputStream0.write((byte[]) null, 0, 0);
    }

    @Test(timeout = 4000)
    public void test2020() throws Throwable {
        PipedInputStream pipedInputStream0 = new PipedInputStream();
        PipedOutputStream pipedOutputStream0 = new PipedOutputStream(pipedInputStream0);
        MockPrintStream mockPrintStream0 = new MockPrintStream(pipedOutputStream0, false);
        CountingOutputStream countingOutputStream0 = new CountingOutputStream(mockPrintStream0);
        TarArchiveOutputStream tarArchiveOutputStream0 = new TarArchiveOutputStream(countingOutputStream0, 76, 9617, (String) null);
    }

    @Test(timeout = 4000)
    public void test2121() throws Throwable {
        TarArchiveOutputStream tarArchiveOutputStream0 = new TarArchiveOutputStream((OutputStream) null, 3825, 3825);
        tarArchiveOutputStream0.getBytesWritten();
    }

    @Test(timeout = 4000)
    public void test2222() throws Throwable {
        TarArchiveOutputStream tarArchiveOutputStream0 = new TarArchiveOutputStream((OutputStream) null, 3825, 3825);
        MockFile mockFile0 = new MockFile((File) null, "");
        tarArchiveOutputStream0.createArchiveEntry(mockFile0, "uid");
    }

    @Test(timeout = 4000)
    public void test2323() throws Throwable {
        PipedOutputStream pipedOutputStream0 = new PipedOutputStream();
        FilterOutputStream filterOutputStream0 = new FilterOutputStream(pipedOutputStream0);
        TarArchiveOutputStream tarArchiveOutputStream0 = new TarArchiveOutputStream(filterOutputStream0);
        Map<String, String> map0 = ZoneId.SHORT_IDS;
        tarArchiveOutputStream0.writePaxHeaders(">mytEE(@T>JeW", map0);
    }

    @Test(timeout = 4000)
    public void test2424() throws Throwable {
        TarArchiveOutputStream tarArchiveOutputStream0 = new TarArchiveOutputStream((OutputStream) null, 1);
        byte[] byteArray0 = new byte[9];
        tarArchiveOutputStream0.write(byteArray0, (int) (byte) 76, (int) (byte) 97);
    }

    @Test(timeout = 4000)
    public void test2525() throws Throwable {
        TarArchiveOutputStream tarArchiveOutputStream0 = new TarArchiveOutputStream((OutputStream) null, 2241);
        HashMap<String, String> hashMap0 = new HashMap<String, String>(2, 1);
        hashMap0.put("org.apache.commons.compress.archivers.zip.ZipShort", "org.apache.commons.compress.archivers.zip.ZipShort");
        tarArchiveOutputStream0.writePaxHeaders("org.apache.commons.compress.archivers.zip.ZipShort", hashMap0);
    }

    @Test(timeout = 4000)
    public void test2626() throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream();
        TarArchiveOutputStream tarArchiveOutputStream0 = new TarArchiveOutputStream(byteArrayOutputStream0);
        tarArchiveOutputStream0.closeArchiveEntry();
    }

    @Test(timeout = 4000)
    public void test2727() throws Throwable {
        MockFile mockFile0 = new MockFile("");
        MockFile mockFile1 = new MockFile(mockFile0, "gid");
        MockPrintStream mockPrintStream0 = new MockPrintStream(mockFile1);
        TarArchiveOutputStream tarArchiveOutputStream0 = new TarArchiveOutputStream(mockPrintStream0);
        tarArchiveOutputStream0.close();
        tarArchiveOutputStream0.closeArchiveEntry();
    }

    @Test(timeout = 4000)
    public void test2828() throws Throwable {
        TarArchiveOutputStream tarArchiveOutputStream0 = new TarArchiveOutputStream((OutputStream) null, 2241);
        HashMap<String, String> hashMap0 = new HashMap<String, String>(2, 1);
        tarArchiveOutputStream0.writePaxHeaders("org.apache.commons.compress.archivers.zip.ZipShort", hashMap0);
    }

    @Test(timeout = 4000)
    public void test2929() throws Throwable {
        File file0 = MockFile.createTempFile("HLg^m']W't D%} ou", (String) null);
        MockFileOutputStream mockFileOutputStream0 = new MockFileOutputStream(file0);
        TarArchiveOutputStream tarArchiveOutputStream0 = new TarArchiveOutputStream(mockFileOutputStream0);
        tarArchiveOutputStream0.close();
        tarArchiveOutputStream0.getBytesWritten();
    }

    @Test(timeout = 4000)
    public void test3030() throws Throwable {
        MockPrintStream mockPrintStream0 = new MockPrintStream("minor device number");
        TarArchiveOutputStream tarArchiveOutputStream0 = new TarArchiveOutputStream(mockPrintStream0, 100);
        TarArchiveEntry tarArchiveEntry0 = new TarArchiveEntry("minor device number", (byte) 87);
        tarArchiveOutputStream0.putArchiveEntry(tarArchiveEntry0);
    }

    @Test(timeout = 4000)
    public void test3131() throws Throwable {
        File file0 = MockFile.createTempFile("HLg^m']W't D%} ou", (String) null);
        MockFileOutputStream mockFileOutputStream0 = new MockFileOutputStream(file0);
        TarArchiveOutputStream tarArchiveOutputStream0 = new TarArchiveOutputStream(mockFileOutputStream0);
        tarArchiveOutputStream0.setAddPaxHeadersForNonAsciiNames(true);
    }

    @Test(timeout = 4000)
    public void test3232() throws Throwable {
        File file0 = MockFile.createTempFile("HLg^m']W't D%} ou", (String) null);
        MockFileOutputStream mockFileOutputStream0 = new MockFileOutputStream(file0);
        TarArchiveOutputStream tarArchiveOutputStream0 = new TarArchiveOutputStream(mockFileOutputStream0);
        tarArchiveOutputStream0.setBigNumberMode(977);
    }

    @Test(timeout = 4000)
    public void test3333() throws Throwable {
        File file0 = MockFile.createTempFile("XJ_", "XJ_");
        MockFileOutputStream mockFileOutputStream0 = new MockFileOutputStream(file0, false);
        TarArchiveOutputStream tarArchiveOutputStream0 = new TarArchiveOutputStream(mockFileOutputStream0, "XJ_");
        tarArchiveOutputStream0.flush();
    }

    @Test(timeout = 4000)
    public void test3434() throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream();
        TarArchiveOutputStream tarArchiveOutputStream0 = new TarArchiveOutputStream(byteArrayOutputStream0, 2958, "org.apache.commons.compress.archivers.zip.Zip64ExtendedInformationExtraField");
        tarArchiveOutputStream0.finish();
        MockFile mockFile0 = new MockFile("org.apache.commons.compress.archivers.zip.Zip64ExtendedInformationExtraField", "org.apache.commons.compress.archivers.zip.Zip64ExtendedInformationExtraField");
        tarArchiveOutputStream0.createArchiveEntry(mockFile0, "org.apache.commons.compress.archivers.zip.Zip64ExtendedInformationExtraField");
    }

    @Test(timeout = 4000)
    public void test3535() throws Throwable {
        File file0 = MockFile.createTempFile("HLg^m']W't D%} ou", (String) null);
        MockFileOutputStream mockFileOutputStream0 = new MockFileOutputStream(file0);
        TarArchiveOutputStream tarArchiveOutputStream0 = new TarArchiveOutputStream(mockFileOutputStream0);
        int int0 = tarArchiveOutputStream0.getRecordSize();
        assertEquals(0, int0);
    }

    @Test(timeout = 4000)
    public void test3636() throws Throwable {
        File file0 = MockFile.createTempFile("iG+${/kZ*G4fl", "iG+${/kZ*G4fl");
        MockFileOutputStream mockFileOutputStream0 = new MockFileOutputStream(file0, false);
        TarArchiveOutputStream tarArchiveOutputStream0 = new TarArchiveOutputStream(mockFileOutputStream0, 479);
        tarArchiveOutputStream0.close();
    }

    @Test(timeout = 4000)
    public void test3737() throws Throwable {
        File file0 = MockFile.createTempFile("HLg^m']W't D%} ou", (String) null);
        MockFileOutputStream mockFileOutputStream0 = new MockFileOutputStream(file0);
        TarArchiveOutputStream tarArchiveOutputStream0 = new TarArchiveOutputStream(mockFileOutputStream0);
        int int0 = tarArchiveOutputStream0.getCount();
        assertEquals(0, int0);
    }

    @Test(timeout = 4000)
    public void test3838() throws Throwable {
        TarArchiveOutputStream tarArchiveOutputStream0 = new TarArchiveOutputStream((OutputStream) null, 474, 88);
        ZipArchiveEntry zipArchiveEntry0 = new ZipArchiveEntry("gu{f4#3*aBo5{");
        tarArchiveOutputStream0.putArchiveEntry(zipArchiveEntry0);
    }
}
