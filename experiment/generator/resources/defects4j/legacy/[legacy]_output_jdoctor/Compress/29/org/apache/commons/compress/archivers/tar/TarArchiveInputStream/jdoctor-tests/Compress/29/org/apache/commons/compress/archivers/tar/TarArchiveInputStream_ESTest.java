/*
 * This file was automatically generated by EvoSuite
 * Mon Nov 20 01:50:18 GMT 2023
 */
package org.apache.commons.compress.archivers.tar;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class TarArchiveInputStream_ESTest extends TarArchiveInputStream_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        byte[] byteArray0 = new byte[8];
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0);
        TarArchiveInputStream tarArchiveInputStream0 = new TarArchiveInputStream(byteArrayInputStream0);
        tarArchiveInputStream0.close();
        tarArchiveInputStream0.getRecordSize();
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        byte[] byteArray0 = new byte[6];
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0, (byte) 10, (byte) 10);
        TarArchiveInputStream tarArchiveInputStream0 = new TarArchiveInputStream(byteArrayInputStream0, (byte) 10, (byte) 0);
        byte[] byteArray1 = tarArchiveInputStream0.getLongNameData();
        tarArchiveInputStream0.getRecordSize();
    }

    @Test(timeout = 4000)
    public void test012() throws Throwable {
        byte[] byteArray0 = new byte[6];
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0, (byte) 10, (byte) 10);
        TarArchiveInputStream tarArchiveInputStream0 = new TarArchiveInputStream(byteArrayInputStream0, (byte) 10, (byte) 0);
        byte[] byteArray1 = tarArchiveInputStream0.getLongNameData();
        tarArchiveInputStream0.getBytesRead();
    }

    @Test(timeout = 4000)
    public void test013() throws Throwable {
        byte[] byteArray0 = new byte[6];
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0, (byte) 10, (byte) 10);
        TarArchiveInputStream tarArchiveInputStream0 = new TarArchiveInputStream(byteArrayInputStream0, (byte) 10, (byte) 0);
        byte[] byteArray1 = tarArchiveInputStream0.getLongNameData();
    }

    @Test(timeout = 4000)
    public void test024() throws Throwable {
        byte[] byteArray0 = new byte[6];
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0);
        TarArchiveInputStream tarArchiveInputStream0 = new TarArchiveInputStream(byteArrayInputStream0);
        tarArchiveInputStream0.setAtEOF(true);
        tarArchiveInputStream0.getRecordSize();
    }

    @Test(timeout = 4000)
    public void test035() throws Throwable {
        byte[] byteArray0 = new byte[0];
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0);
        TarArchiveInputStream tarArchiveInputStream0 = new TarArchiveInputStream(byteArrayInputStream0);
        boolean boolean0 = tarArchiveInputStream0.isAtEOF();
    }

    @Test(timeout = 4000)
    public void test036() throws Throwable {
        byte[] byteArray0 = new byte[0];
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0);
        TarArchiveInputStream tarArchiveInputStream0 = new TarArchiveInputStream(byteArrayInputStream0);
        boolean boolean0 = tarArchiveInputStream0.isAtEOF();
        tarArchiveInputStream0.getRecordSize();
    }

    @Test(timeout = 4000)
    public void test047() throws Throwable {
        byte[] byteArray0 = new byte[6];
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0, (byte) 10, (byte) 0);
        TarArchiveInputStream tarArchiveInputStream0 = new TarArchiveInputStream(byteArrayInputStream0, 524, (String) null);
        tarArchiveInputStream0.reset();
        tarArchiveInputStream0.getRecordSize();
    }

    @Test(timeout = 4000)
    public void test058() throws Throwable {
        TarArchiveInputStream tarArchiveInputStream0 = new TarArchiveInputStream((InputStream) null, (-1104));
        int int0 = tarArchiveInputStream0.getRecordSize();
    }

    @Test(timeout = 4000)
    public void test069() throws Throwable {
        TarArchiveEntry tarArchiveEntry0 = new TarArchiveEntry("CP437");
        PipedInputStream pipedInputStream0 = new PipedInputStream();
        TarArchiveInputStream tarArchiveInputStream0 = new TarArchiveInputStream(pipedInputStream0, 16877);
        tarArchiveInputStream0.setCurrentEntry(tarArchiveEntry0);
        tarArchiveInputStream0.getLongNameData();
    }

    @Test(timeout = 4000)
    public void test0710() throws Throwable {
        byte[] byteArray0 = new byte[0];
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0);
        TarArchiveInputStream tarArchiveInputStream0 = new TarArchiveInputStream(byteArrayInputStream0, "WORLD_READ");
        tarArchiveInputStream0.getRecordSize();
    }

    @Test(timeout = 4000)
    public void test0811() throws Throwable {
        TarArchiveInputStream tarArchiveInputStream0 = new TarArchiveInputStream((InputStream) null, (-1104));
        tarArchiveInputStream0.getCurrentEntry();
        tarArchiveInputStream0.getRecordSize();
    }

    @Test(timeout = 4000)
    public void test0912() throws Throwable {
        byte[] byteArray0 = new byte[6];
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0, (byte) 10, (byte) 10);
        TarArchiveInputStream tarArchiveInputStream0 = new TarArchiveInputStream(byteArrayInputStream0, (byte) 0, (String) null);
        TarArchiveInputStream tarArchiveInputStream1 = new TarArchiveInputStream(tarArchiveInputStream0, (byte) 0, (byte) 0);
        tarArchiveInputStream1.getLongNameData();
    }

    @Test(timeout = 4000)
    public void test1013() throws Throwable {
        TarArchiveInputStream tarArchiveInputStream0 = new TarArchiveInputStream((InputStream) null, (-1108));
        tarArchiveInputStream0.mark(3483);
        tarArchiveInputStream0.getRecordSize();
    }

    @Test(timeout = 4000)
    public void test1114() throws Throwable {
        byte[] byteArray0 = new byte[0];
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0);
        TarArchiveInputStream tarArchiveInputStream0 = new TarArchiveInputStream(byteArrayInputStream0);
        int int0 = tarArchiveInputStream0.available();
        tarArchiveInputStream0.getRecordSize();
    }

    @Test(timeout = 4000)
    public void test1115() throws Throwable {
        byte[] byteArray0 = new byte[0];
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0);
        TarArchiveInputStream tarArchiveInputStream0 = new TarArchiveInputStream(byteArrayInputStream0);
        int int0 = tarArchiveInputStream0.available();
    }

    @Test(timeout = 4000)
    public void test1216() throws Throwable {
        TarArchiveInputStream tarArchiveInputStream0 = new TarArchiveInputStream((InputStream) null, (-1104));
        long long0 = tarArchiveInputStream0.skip((-1104));
        tarArchiveInputStream0.getRecordSize();
    }

    @Test(timeout = 4000)
    public void test1217() throws Throwable {
        TarArchiveInputStream tarArchiveInputStream0 = new TarArchiveInputStream((InputStream) null, (-1104));
        long long0 = tarArchiveInputStream0.skip((-1104));
    }

    @Test(timeout = 4000)
    public void test1318() throws Throwable {
        byte[] byteArray0 = new byte[0];
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0);
        TarArchiveInputStream tarArchiveInputStream0 = new TarArchiveInputStream(byteArrayInputStream0);
        byte[] byteArray1 = tarArchiveInputStream0.getLongNameData();
    }

    @Test(timeout = 4000)
    public void test1319() throws Throwable {
        byte[] byteArray0 = new byte[0];
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0);
        TarArchiveInputStream tarArchiveInputStream0 = new TarArchiveInputStream(byteArrayInputStream0);
        byte[] byteArray1 = tarArchiveInputStream0.getLongNameData();
        tarArchiveInputStream0.getNextTarEntry();
        tarArchiveInputStream0.getRecordSize();
    }

    @Test(timeout = 4000)
    public void test1320() throws Throwable {
        byte[] byteArray0 = new byte[0];
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0);
        TarArchiveInputStream tarArchiveInputStream0 = new TarArchiveInputStream(byteArrayInputStream0);
        byte[] byteArray1 = tarArchiveInputStream0.getLongNameData();
        tarArchiveInputStream0.getNextTarEntry();
        tarArchiveInputStream0.getCount();
    }

    @Test(timeout = 4000)
    public void test1421() throws Throwable {
        byte[] byteArray0 = new byte[6];
        byteArray0[0] = (byte) 10;
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0, (byte) 10, (byte) 10);
        TarArchiveInputStream tarArchiveInputStream0 = new TarArchiveInputStream(byteArrayInputStream0, (byte) 0, (String) null);
        boolean boolean0 = tarArchiveInputStream0.isEOFRecord(byteArray0);
    }

    @Test(timeout = 4000)
    public void test1422() throws Throwable {
        byte[] byteArray0 = new byte[6];
        byteArray0[0] = (byte) 10;
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0, (byte) 10, (byte) 10);
        TarArchiveInputStream tarArchiveInputStream0 = new TarArchiveInputStream(byteArrayInputStream0, (byte) 0, (String) null);
        boolean boolean0 = tarArchiveInputStream0.isEOFRecord(byteArray0);
        tarArchiveInputStream0.getRecordSize();
    }

    @Test(timeout = 4000)
    public void test1523() throws Throwable {
        byte[] byteArray0 = new byte[6];
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0);
        TarArchiveInputStream tarArchiveInputStream0 = new TarArchiveInputStream(byteArrayInputStream0);
        tarArchiveInputStream0.parsePaxHeaders(byteArrayInputStream0);
        tarArchiveInputStream0.getRecordSize();
    }

    @Test(timeout = 4000)
    public void test1624() throws Throwable {
        byte[] byteArray0 = new byte[0];
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0);
        TarArchiveInputStream tarArchiveInputStream0 = new TarArchiveInputStream(byteArrayInputStream0);
        byte[] byteArray1 = tarArchiveInputStream0.getLongNameData();
    }

    @Test(timeout = 4000)
    public void test1625() throws Throwable {
        byte[] byteArray0 = new byte[0];
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0);
        TarArchiveInputStream tarArchiveInputStream0 = new TarArchiveInputStream(byteArrayInputStream0);
        byte[] byteArray1 = tarArchiveInputStream0.getLongNameData();
        int int0 = tarArchiveInputStream0.read(byteArray0, (-1), (-1));
        tarArchiveInputStream0.getRecordSize();
    }

    @Test(timeout = 4000)
    public void test1626() throws Throwable {
        byte[] byteArray0 = new byte[0];
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0);
        TarArchiveInputStream tarArchiveInputStream0 = new TarArchiveInputStream(byteArrayInputStream0);
        byte[] byteArray1 = tarArchiveInputStream0.getLongNameData();
        int int0 = tarArchiveInputStream0.read(byteArray0, (-1), (-1));
        tarArchiveInputStream0.getCount();
    }

    @Test(timeout = 4000)
    public void test1627() throws Throwable {
        byte[] byteArray0 = new byte[0];
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0);
        TarArchiveInputStream tarArchiveInputStream0 = new TarArchiveInputStream(byteArrayInputStream0);
        byte[] byteArray1 = tarArchiveInputStream0.getLongNameData();
        int int0 = tarArchiveInputStream0.read(byteArray0, (-1), (-1));
    }

    @Test(timeout = 4000)
    public void test1728() throws Throwable {
        byte[] byteArray0 = new byte[5];
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0, 1439, 512);
        TarArchiveInputStream tarArchiveInputStream0 = new TarArchiveInputStream(byteArrayInputStream0, 1439, 1439);
        tarArchiveInputStream0.skip(4013L);
        tarArchiveInputStream0.read(byteArray0, 1556, 1439);
    }

    @Test(timeout = 4000)
    public void test1829() throws Throwable {
        TarArchiveInputStream tarArchiveInputStream0 = new TarArchiveInputStream((InputStream) null);
        boolean boolean0 = tarArchiveInputStream0.canReadEntryData((ArchiveEntry) null);
        tarArchiveInputStream0.getRecordSize();
    }

    @Test(timeout = 4000)
    public void test1830() throws Throwable {
        TarArchiveInputStream tarArchiveInputStream0 = new TarArchiveInputStream((InputStream) null);
        boolean boolean0 = tarArchiveInputStream0.canReadEntryData((ArchiveEntry) null);
    }

    @Test(timeout = 4000)
    public void test1931() throws Throwable {
        TarArchiveEntry tarArchiveEntry0 = new TarArchiveEntry("CP437");
        PipedInputStream pipedInputStream0 = new PipedInputStream();
        TarArchiveInputStream tarArchiveInputStream0 = new TarArchiveInputStream(pipedInputStream0, 16877);
        boolean boolean0 = tarArchiveInputStream0.canReadEntryData(tarArchiveEntry0);
        tarArchiveInputStream0.getRecordSize();
    }

    @Test(timeout = 4000)
    public void test1932() throws Throwable {
        TarArchiveEntry tarArchiveEntry0 = new TarArchiveEntry("CP437");
        PipedInputStream pipedInputStream0 = new PipedInputStream();
        TarArchiveInputStream tarArchiveInputStream0 = new TarArchiveInputStream(pipedInputStream0, 16877);
        boolean boolean0 = tarArchiveInputStream0.canReadEntryData(tarArchiveEntry0);
    }

    @Test(timeout = 4000)
    public void test2033() throws Throwable {
        byte[] byteArray0 = new byte[13];
        TarArchiveInputStream.matches(byteArray0, 1329);
    }

    @Test(timeout = 4000)
    public void test2134() throws Throwable {
        byte[] byteArray0 = new byte[6];
        boolean boolean0 = TarArchiveInputStream.matches(byteArray0, (byte) 10);
    }
}
