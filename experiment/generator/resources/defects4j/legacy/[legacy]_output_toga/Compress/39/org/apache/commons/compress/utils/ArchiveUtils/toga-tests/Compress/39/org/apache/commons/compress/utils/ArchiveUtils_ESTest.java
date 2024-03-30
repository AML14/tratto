/*
 * This file was automatically generated by EvoSuite
 * Sun Oct 15 12:30:33 GMT 2023
 */
package org.apache.commons.compress.utils;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.io.File;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.jar.JarEntry;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ar.ArArchiveEntry;
import org.apache.commons.compress.archivers.arj.ArjArchiveEntry;
import org.apache.commons.compress.archivers.cpio.CpioArchiveEntry;
import org.apache.commons.compress.archivers.dump.DumpArchiveEntry;
import org.apache.commons.compress.archivers.jar.JarArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.zip.X0014_X509Certificates;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipShort;
import org.apache.commons.compress.utils.ArchiveUtils;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.System;
import org.evosuite.runtime.mock.java.io.MockFile;
import org.evosuite.runtime.mock.java.time.MockInstant;
import org.evosuite.runtime.mock.java.time.MockLocalDateTime;
import org.evosuite.runtime.testdata.EvoSuiteFile;
import org.evosuite.runtime.testdata.FileSystemHandling;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class ArchiveUtils_ESTest extends ArchiveUtils_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        byte[] byteArray0 = new byte[3];
        byte byte0 = (byte) 1;
        byteArray0[2] = (byte) 0;
        ArchiveUtils.toAsciiString(byteArray0);
        ArchiveUtils.isArrayZero(byteArray0, (byte) 1);
        ArchiveUtils.toAsciiString(byteArray0);
        ArchiveUtils.isEqual(byteArray0, byteArray0);
        ArchiveUtils.isEqualWithNull(byteArray0, (byte) 0, (byte) 1, byteArray0, (byte) 0, (byte) 1);
        ArchiveUtils.sanitize("\u0000\u0000\u0000");
        ArchiveUtils.isEqualWithNull(byteArray0, (-123), (byte) 0, byteArray0, (-1455), 10);
        byte[] byteArray1 = new byte[0];
        ArchiveUtils.isEqual(byteArray0, byteArray1);
        ArchiveUtils.isEqual(byteArray0, byteArray1);
        ArchiveUtils.isEqual(byteArray0, byteArray0);
    }

    @Test(timeout = 4000)
    public void test041() throws Throwable {
        byte[] byteArray0 = new byte[6];
        byte byte0 = (byte) (-4);
        byteArray0[0] = (byte) (-4);
        byteArray0[1] = (byte) (-4);
        byteArray0[2] = (byte) (-5);
        byteArray0[5] = (byte) (-4);
        ArchiveUtils.isEqual(byteArray0, (int) (byte) 0, (int) (byte) (-4), byteArray0, (-2722), (int) (byte) (-5), true);
        String string0 = "p[K";
        ArchiveUtils.sanitize(string0);
        int int0 = 409;
        boolean boolean0 = true;
        ArchiveUtils.isEqual(byteArray0, (int) byteArray0[0], (int) byte0, byteArray0, (int) byte0, int0, boolean0);
    }

    @Test(timeout = 4000)
    public void test152() throws Throwable {
        byte[] byteArray0 = null;
        ArchiveUtils.toAsciiString((byte[]) null);
    }

    @Test(timeout = 4000)
    public void test163() throws Throwable {
        byte[] byteArray0 = null;
        ArchiveUtils.isEqual((byte[]) null, (byte[]) null);
    }

    @Test(timeout = 4000)
    public void test194() throws Throwable {
        byte[] byteArray0 = new byte[7];
        byteArray0[0] = (byte) (-105);
        byteArray0[1] = (byte) 68;
        byteArray0[2] = (byte) (-53);
        byte byte0 = (byte) (-88);
        byteArray0[3] = (byte) (-88);
        byteArray0[4] = (byte) (-48);
        byteArray0[5] = (byte) 121;
        byteArray0[6] = (byte) 100;
        ArchiveUtils.toAsciiString(byteArray0);
        String string0 = "<fn1Z[";
        int int0 = 141;
        ArchiveUtils.matchAsciiBuffer("<fn1Z[", byteArray0, 141, (int) (byte) (-105));
        ArchiveUtils.toString((ArchiveEntry) null);
        int int1 = 30062;
        ArchiveUtils.isArrayZero(byteArray0, int1);
        boolean boolean0 = true;
        boolean boolean1 = ArchiveUtils.isEqual(byteArray0, byteArray0, boolean0);
        ArchiveUtils.isEqual(byteArray0, byteArray0, boolean1);
        ArchiveUtils.toAsciiBytes(string0);
        ArchiveUtils.isArrayZero(byteArray0, byte0);
    }

    @Test(timeout = 4000)
    public void test265() throws Throwable {
        byte[] byteArray0 = ArchiveUtils.toAsciiBytes("F~+?x1bgjG2'0");
        ArchiveUtils.isEqual(byteArray0, byteArray0);
        byte[] byteArray1 = ArchiveUtils.toAsciiBytes("F~+?x1bgjG2'0");
        ArchiveUtils.isEqual(byteArray1, (byte[]) null, true);
        String string0 = "org.apache.commons.compress.utils.ArchiveUtils";
        ArchiveUtils.matchAsciiBuffer(string0, byteArray1);
        SevenZArchiveEntry sevenZArchiveEntry0 = null;
        sevenZArchiveEntry0 = new SevenZArchiveEntry();
    }

    @Test(timeout = 4000)
    public void test276() throws Throwable {
        byte[] byteArray0 = new byte[1];
        byte byte0 = (byte) 45;
        byteArray0[0] = (byte) 45;
        ArchiveUtils.toAsciiString(byteArray0, 28789, (int) (byte) 45);
        ArchiveUtils.isEqual(byteArray0, byteArray0);
    }

    @Test(timeout = 4000)
    public void test437() throws Throwable {
        byte[] byteArray0 = new byte[1];
        byte byte0 = (byte) 0;
        byteArray0[0] = (byte) 0;
        ArchiveUtils.isArrayZero(byteArray0, (byte) 0);
        ArchiveUtils.isEqual(byteArray0, byteArray0, false);
        int int0 = 702;
        ArchiveUtils.isEqualWithNull(byteArray0, (byte) 0, 702, byteArray0, 0, (byte) 0);
        String string0 = "RIPEND160";
        ArchiveUtils.matchAsciiBuffer(string0, byteArray0);
    }

    @Test(timeout = 4000)
    public void test458() throws Throwable {
        byte[] byteArray0 = null;
        ArchiveUtils.isEqual((byte[]) null, (-2141), (-2141), (byte[]) null, (-2141), (-2141), false);
        String string0 = "";
        TarArchiveEntry tarArchiveEntry0 = new TarArchiveEntry("", (byte) 9);
        DumpArchiveEntry dumpArchiveEntry0 = new DumpArchiveEntry("", "");
        Date date0 = tarArchiveEntry0.getLastModifiedDate();
        dumpArchiveEntry0.setAccessTime(date0);
        Date date1 = dumpArchiveEntry0.getAccessTime();
        tarArchiveEntry0.setModTime(date1);
        String string1 = ArchiveUtils.toString((ArchiveEntry) tarArchiveEntry0);
        ArchiveUtils.isEqual((byte[]) null, (byte[]) null, true);
        int int0 = (-1065);
        int int1 = 0;
        ArchiveUtils.matchAsciiBuffer(string0, byteArray0, int0, int1);
        ArchiveUtils.sanitize(string1);
    }

    @Test(timeout = 4000)
    public void test479() throws Throwable {
        byte[] byteArray0 = new byte[3];
        byteArray0[0] = (byte) (-8);
        byteArray0[1] = (byte) 100;
        byte byte0 = (byte) 83;
        byteArray0[2] = (byte) 83;
        ArchiveUtils.isArrayZero(byteArray0, (byte) 83);
        String string0 = "o]]Ec";
        ArchiveUtils.matchAsciiBuffer("o]]Ec", byteArray0, (int) (byte) 100, (int) (byte) 100);
        int int0 = (-53);
        ArchiveUtils.isArrayZero(byteArray0, int0);
    }

    @Test(timeout = 4000)
    public void test5010() throws Throwable {
        String string0 = " but is ";
        byte[] byteArray0 = new byte[1];
        byteArray0[0] = (byte) 125;
        ArchiveUtils.matchAsciiBuffer(" but is ", byteArray0);
        ArchiveUtils.isEqual(byteArray0, 32, 0, byteArray0, 0, (int) (byte) 125, false);
        ArchiveUtils.toAsciiString(byteArray0, (int) (byte) 125, 4050);
        byte[] byteArray1 = null;
        ArchiveUtils.isEqual(byteArray0, byteArray1);
        ArchiveUtils.sanitize(string0);
        ArchiveUtils.matchAsciiBuffer(string0, byteArray0);
    }

    @Test(timeout = 4000)
    public void test5811() throws Throwable {
        byte[] byteArray0 = new byte[1];
        byteArray0[0] = (byte) 0;
        ArchiveUtils.isEqual(byteArray0, byteArray0);
        ArchiveUtils.isEqual(byteArray0, byteArray0);
        int int0 = 7;
        int int1 = 110;
        ArchiveUtils.isEqual(byteArray0, (int) (byte) 0, 7, byteArray0, (int) (byte) 0, 110, true);
        ArchiveUtils.toAsciiString(byteArray0);
    }

    @Test(timeout = 4000)
    public void test6112() throws Throwable {
        byte[] byteArray0 = new byte[6];
        byte byte0 = (byte) 0;
        byteArray0[1] = (byte) 0;
        byteArray0[2] = (byte) (-5);
        byte byte1 = (byte) 32;
        byteArray0[3] = (byte) 32;
        byte byte2 = (byte) 0;
        byte byte3 = (byte) 21;
        byteArray0[5] = (byte) 21;
        int int0 = 0;
        ArchiveUtils.isEqual(byteArray0, (int) (byte) 0, (int) (byte) 21, byteArray0, (-2722), 0, true);
        int int1 = (-4592);
        ArchiveUtils.toAsciiString(byteArray0, (-4592), (int) (byte) 0);
        int int2 = 63;
        String string0 = "C<uY43s";
        CpioArchiveEntry cpioArchiveEntry0 = null;
        cpioArchiveEntry0 = new CpioArchiveEntry(string0);
    }

    @Test(timeout = 4000)
    public void test6613() throws Throwable {
        byte[] byteArray0 = new byte[3];
        byteArray0[0] = (byte) 1;
        byteArray0[0] = (byte) (-8);
        byte byte0 = (byte) 0;
        byteArray0[2] = (byte) 0;
        ArchiveUtils.toAsciiString(byteArray0);
        ArchiveUtils.isArrayZero(byteArray0, (byte) 0);
        ArchiveUtils.toAsciiString(byteArray0);
        ArchiveUtils.isEqual(byteArray0, byteArray0);
        ArchiveUtils.toAsciiString(byteArray0, 0, (int) (byte) 1);
        ArchiveUtils.isEqualWithNull(byteArray0, (-1455), (byte) (-8), byteArray0, (byte) (-8), (byte) (-8));
        ArchiveUtils.isEqualWithNull(byteArray0, (byte) 0, (byte) 1, byteArray0, (byte) 0, (byte) 1);
        ArchiveUtils.sanitize("\uFFFD\u0000\u0000");
        byte[] byteArray1 = null;
        ArchiveUtils.isEqualWithNull(byteArray0, (-123), (byte) (-8), (byte[]) null, (-802), (-1850));
        byte[] byteArray2 = new byte[0];
        ArchiveUtils.isEqual(byteArray0, byteArray2);
        ArchiveUtils.isEqual(byteArray1, byteArray2);
        ArchiveUtils.isEqual(byteArray1, byteArray1);
        int int0 = 380;
        ArchiveUtils.isArrayZero(byteArray1, int0);
        ArchiveUtils.isArrayZero(byteArray1, byte0);
    }
}
