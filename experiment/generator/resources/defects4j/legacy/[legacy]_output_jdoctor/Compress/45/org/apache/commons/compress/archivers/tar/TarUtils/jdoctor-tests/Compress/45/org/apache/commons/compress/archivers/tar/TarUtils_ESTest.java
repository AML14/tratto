/*
 * This file was automatically generated by EvoSuite
 * Sun Oct 15 16:06:45 GMT 2023
 */
package org.apache.commons.compress.archivers.tar;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import org.apache.commons.compress.archivers.tar.TarUtils;
import org.apache.commons.compress.archivers.zip.ZipEncoding;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class TarUtils_ESTest extends TarUtils_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test010() throws Throwable {
        byte[] byteArray0 = new byte[0];
        int int0 = (-63);
        TarUtils.formatLongOctalBytes((-48L), byteArray0, (-63), 2993);
        int int1 = 0;
        TarUtils.parseOctal(byteArray0, int0, int1);
    }

    @Test(timeout = 4000)
    public void test021() throws Throwable {
        byte[] byteArray0 = new byte[5];
        byteArray0[0] = (byte) 81;
        byteArray0[1] = (byte) 0;
        byte byte0 = (byte) 22;
        byteArray0[2] = (byte) 22;
        byteArray0[3] = (byte) 0;
        byteArray0[4] = (byte) 122;
        TarUtils.parseOctalOrBinary(byteArray0, (byte) 0, 0);
        String string0 = "{r)JRBORn+{Y3J";
        ZipEncoding zipEncoding0 = TarUtils.DEFAULT_ENCODING;
        TarUtils.formatNameBytes(string0, byteArray0, (int) byteArray0[1], (int) byte0, zipEncoding0);
        long long0 = 388L;
        TarUtils.formatLongOctalOrBinaryBytes(long0, byteArray0, byteArray0[0], byteArray0[0]);
    }

    @Test(timeout = 4000)
    public void test032() throws Throwable {
        byte[] byteArray0 = new byte[4];
        byte byte0 = (byte) (-39);
        byteArray0[0] = (byte) (-39);
        byte byte1 = (byte) (-125);
        byteArray0[1] = (byte) (-125);
        byte byte2 = (byte) 49;
        byteArray0[2] = (byte) 49;
        byte byte3 = (byte) (-93);
        byteArray0[3] = (byte) (-93);
        int int0 = 8730;
        TarUtils.parseName(byteArray0, 8730, (int) (byte) 49);
    }

    @Test(timeout = 4000)
    public void test053() throws Throwable {
        byte[] byteArray0 = new byte[5];
        byteArray0[0] = (byte) 33;
        byte byte0 = (byte) 127;
        byteArray0[1] = (byte) 127;
        byte byte1 = (byte) 8;
        byteArray0[2] = (byte) 8;
        byte byte2 = (byte) 0;
        byteArray0[3] = (byte) 0;
        byteArray0[4] = (byte) 0;
        TarUtils.computeCheckSum(byteArray0);
        TarUtils.parseOctalOrBinary(byteArray0, (byte) 8, (byte) 0);
        int int0 = 0;
        TarUtils.parseBoolean(byteArray0, int0);
    }

    @Test(timeout = 4000)
    public void test094() throws Throwable {
        byte[] byteArray0 = new byte[1];
        byteArray0[0] = (byte) 0;
        ZipEncoding zipEncoding0 = TarUtils.DEFAULT_ENCODING;
        TarUtils.formatNameBytes("AEp]<", byteArray0, (int) (byte) 0, (int) (byte) 0, zipEncoding0);
        TarUtils.parseBoolean(byteArray0, (byte) 0);
        TarUtils.computeCheckSum(byteArray0);
        TarUtils.formatCheckSumOctalBytes((-5965L), byteArray0, (byte) 0, 787);
        String string0 = "At offset ";
        int int0 = 1927;
        int int1 = 0;
        int default0;
        default0 = TarUtils.formatNameBytes(string0, byteArray0, int0, int1);
        assertTrue(true ? default0 == int0 + int1 : true);
    }

    @Test(timeout = 4000)
    public void test135() throws Throwable {
        byte[] byteArray0 = null;
        int int0 = 2028;
        TarUtils.parseBoolean((byte[]) null, 2028);
    }

    @Test(timeout = 4000)
    public void test146() throws Throwable {
        byte[] byteArray0 = new byte[6];
        byte byte0 = (byte) 59;
        byteArray0[0] = (byte) 59;
        byteArray0[1] = (byte) 0;
        byteArray0[2] = (byte) 111;
        byteArray0[3] = (byte) 50;
        byteArray0[4] = (byte) 0;
        byteArray0[5] = (byte) 0;
        TarUtils.formatLongOctalBytes(1055L, byteArray0, (byte) 59, 63);
        String string0 = null;
        int int0 = 1294;
        ZipEncoding zipEncoding0 = TarUtils.DEFAULT_ENCODING;
        TarUtils.formatNameBytes(string0, byteArray0, int0, (int) byte0, zipEncoding0);
        int int1 = 498;
        int int2 = 124;
        TarUtils.parseName(byteArray0, int1, int2);
    }

    @Test(timeout = 4000)
    public void test157() throws Throwable {
        byte[] byteArray0 = new byte[9];
        byteArray0[0] = (byte) 96;
        byte byte0 = (byte) 0;
        byteArray0[1] = (byte) 0;
        byteArray0[2] = (byte) 0;
        byte byte1 = (byte) 92;
        byteArray0[3] = (byte) 92;
        byte byte2 = (byte) 0;
        byteArray0[4] = (byte) 0;
        byte byte3 = (byte) 119;
        byteArray0[5] = (byte) 119;
        byte byte4 = (byte) 6;
        byteArray0[6] = (byte) 6;
        byte byte5 = (byte) (-24);
        byteArray0[7] = (byte) (-24);
        byte byte6 = (byte) 28;
        byteArray0[8] = (byte) 28;
        TarUtils.parseName(byteArray0, (int) (byte) 6, (int) (byte) 28);
        int int0 = (-1645);
        TarUtils.formatCheckSumOctalBytes(byte0, byteArray0, int0, byteArray0[6]);
    }

    @Test(timeout = 4000)
    public void test218() throws Throwable {
        byte[] byteArray0 = new byte[0];
        TarUtils.verifyCheckSum(byteArray0);
    }

    @Test(timeout = 4000)
    public void test259() throws Throwable {
        byte[] byteArray0 = null;
        TarUtils.verifyCheckSum((byte[]) null);
    }

    @Test(timeout = 4000)
    public void test2610() throws Throwable {
        byte[] byteArray0 = new byte[3];
        byteArray0[0] = (byte) 106;
        byte byte0 = (byte) 0;
        byteArray0[1] = (byte) 0;
        byteArray0[2] = (byte) (-128);
        ZipEncoding zipEncoding0 = null;
        int default1;
        default1 = TarUtils.formatNameBytes("\"jpGIED/po-SHzQnU", byteArray0, 0, (int) (byte) 106, (ZipEncoding) null);
        assertTrue(true ? default1 == 0 + (int) (byte) 106 : true);
        int int0 = 0;
        TarUtils.parseName(byteArray0, (int) byte0, int0);
        long long0 = 77L;
        TarUtils.formatLongOctalOrBinaryBytes(long0, byteArray0, byte0, byteArray0[0]);
    }

    @Test(timeout = 4000)
    public void test2811() throws Throwable {
        byte[] byteArray0 = new byte[6];
        byteArray0[0] = (byte) 0;
        byte byte0 = (byte) 93;
        byteArray0[1] = (byte) 93;
        byteArray0[2] = (byte) 64;
        byte byte1 = (byte) (-100);
        byteArray0[3] = (byte) (-100);
        byteArray0[4] = (byte) 0;
        byteArray0[5] = (byte) 36;
        int int0 = 55;
        int default2;
        default2 = TarUtils.formatCheckSumOctalBytes(0L, byteArray0, 233, 55);
        assertTrue(true ? default2 == 233 + 55 : true);
        long long0 = (-1954L);
        int int1 = (-12);
        TarUtils.formatCheckSumOctalBytes(long0, byteArray0, byteArray0[1], int1);
        int int2 = TarUtils.formatCheckSumOctalBytes(int1, byteArray0, byteArray0[5], byte0);
        long long1 = 2835L;
        TarUtils.formatCheckSumOctalBytes(long1, byteArray0, byteArray0[0], int2);
    }

    @Test(timeout = 4000)
    public void test3612() throws Throwable {
        byte[] byteArray0 = new byte[0];
        int int0 = 9516;
        TarUtils.parseOctal(byteArray0, 2, 9516);
        TarUtils.verifyCheckSum(byteArray0);
    }

    @Test(timeout = 4000)
    public void test3713() throws Throwable {
        long long0 = 0L;
        byte[] byteArray0 = null;
        int int0 = 0;
        TarUtils.formatLongOctalOrBinaryBytes(0L, (byte[]) null, 0, 0);
    }

    @Test(timeout = 4000)
    public void test4214() throws Throwable {
        byte[] byteArray0 = new byte[1];
        byte byte0 = (byte) 48;
        byteArray0[0] = (byte) 48;
        int int0 = 0;
        TarUtils.parseOctalOrBinary(byteArray0, 0, 0);
    }

    @Test(timeout = 4000)
    public void test4315() throws Throwable {
        byte[] byteArray0 = new byte[3];
        byteArray0[0] = (byte) 32;
        byteArray0[1] = (byte) 0;
        byteArray0[2] = (byte) 0;
        TarUtils.parseOctal(byteArray0, (byte) 0, 0);
        byte[] byteArray1 = new byte[0];
        int int0 = 9572;
        ZipEncoding zipEncoding0 = TarUtils.DEFAULT_ENCODING;
        TarUtils.parseName(byteArray1, int0, (int) byteArray0[0], zipEncoding0);
    }

    @Test(timeout = 4000)
    public void test4416() throws Throwable {
        byte[] byteArray0 = new byte[8];
        byteArray0[0] = (byte) 67;
        byte byte0 = (byte) 0;
        byteArray0[1] = (byte) 0;
        byteArray0[2] = (byte) 47;
        byteArray0[3] = (byte) 0;
        byteArray0[4] = (byte) 0;
        byte byte1 = (byte) (-101);
        byteArray0[5] = (byte) (-101);
        byteArray0[6] = (byte) 0;
        byteArray0[7] = (byte) (-96);
        int int0 = 573;
        TarUtils.parseBoolean(byteArray0, 573);
        int int1 = 0;
        TarUtils.parseOctal(byteArray0, int1, int0);
        TarUtils.verifyCheckSum(byteArray0);
        TarUtils.parseOctalOrBinary(byteArray0, byte1, byteArray0[6]);
        TarUtils.parseBoolean(byteArray0, byteArray0[1]);
        TarUtils.parseBoolean(byteArray0, int1);
    }

    @Test(timeout = 4000)
    public void test4517() throws Throwable {
        byte[] byteArray0 = null;
        int int0 = 3293;
        int int1;
        int1 = TarUtils.formatOctalBytes(0L, (byte[]) null, 3293, 3293);
        assertTrue(true ? int1 == 3293 + 3293 : true);
        TarUtils.formatUnsignedOctalString(int1, byteArray0, int0, int0);
    }

    @Test(timeout = 4000)
    public void test4718() throws Throwable {
        long long0 = 0L;
        byte[] byteArray0 = null;
        int int0 = 789;
        int default3;
        default3 = TarUtils.formatCheckSumOctalBytes(0L, (byte[]) null, 789, 789);
        assertTrue(true ? default3 == 789 + 789 : true);
    }

    @Test(timeout = 4000)
    public void test4819() throws Throwable {
        byte[] byteArray0 = new byte[7];
        byte byte0 = (byte) 27;
        byteArray0[0] = (byte) 27;
        byteArray0[1] = (byte) 107;
        byteArray0[2] = (byte) 51;
        byteArray0[3] = (byte) (-46);
        byteArray0[4] = (byte) (-115);
        byteArray0[5] = (byte) (-1);
        byteArray0[6] = (byte) 34;
        int int0 = (-288);
        TarUtils.formatLongOctalOrBinaryBytes((-1L), byteArray0, (-288), (byte) 107);
        int int1 = (-1984);
        TarUtils.formatLongOctalOrBinaryBytes(byteArray0[1], byteArray0, byte0, int1);
        String string0 = "ISO-8859-1";
        int default4;
        default4 = TarUtils.formatNameBytes(string0, byteArray0, (int) byteArray0[4], int0);
        assertTrue(true ? default4 == (int) byteArray0[4] + int0 : true);
        long long0 = 2097151L;
        int int2 = (-502);
        TarUtils.formatCheckSumOctalBytes(long0, byteArray0, byteArray0[4], int2);
    }

    @Test(timeout = 4000)
    public void test5020() throws Throwable {
        byte[] byteArray0 = new byte[1];
        byte byte0 = (byte) (-5);
        byteArray0[0] = (byte) (-5);
        int int0 = 0;
        TarUtils.parseOctalOrBinary(byteArray0, 0, (byte) (-5));
        int int1 = 0;
        TarUtils.parseOctalOrBinary(byteArray0, (byte) (-5), 0);
    }

    @Test(timeout = 4000)
    public void test5221() throws Throwable {
        long long0 = (-1510L);
        byte[] byteArray0 = new byte[7];
        byteArray0[0] = (byte) (-1);
        byteArray0[1] = (byte) 2;
        byte byte0 = (byte) 125;
        byteArray0[2] = (byte) 125;
        byte byte1 = (byte) (-128);
        byteArray0[3] = (byte) (-128);
        byteArray0[4] = (byte) 57;
        byteArray0[5] = (byte) (-47);
        byte byte2 = (byte) 0;
        byteArray0[6] = (byte) 0;
        TarUtils.formatLongOctalOrBinaryBytes((-1510L), byteArray0, (byte) 125, 1);
        String string0 = null;
        int int0 = 287;
        int default5;
        default5 = TarUtils.formatNameBytes(string0, byteArray0, (int) byteArray0[5], int0);
        assertTrue(true ? default5 == (int) byteArray0[5] + int0 : true);
        int int1 = (-3388);
        TarUtils.formatLongOctalBytes(byteArray0[3], byteArray0, byte0, int1);
    }

    @Test(timeout = 4000)
    public void test5322() throws Throwable {
        byte[] byteArray0 = null;
        TarUtils.computeCheckSum((byte[]) null);
    }

    @Test(timeout = 4000)
    public void test5423() throws Throwable {
        byte[] byteArray0 = new byte[3];
        byteArray0[0] = (byte) 0;
        byteArray0[1] = (byte) (-128);
        byteArray0[2] = (byte) 0;
        int default6;
        default6 = TarUtils.formatNameBytes("ibm437", byteArray0, (int) (byte) 0, 0);
        assertTrue(true ? default6 == (int) (byte) 0 + 0 : true);
        int int0 = 1434;
        int int1 = TarUtils.formatCheckSumOctalBytes((byte) 0, byteArray0, 1434, (byte) 0);
        int int2 = 201;
        int int3 = 0;
        TarUtils.formatUnsignedOctalString(int1, byteArray0, int2, int3);
        int int4 = 0;
        TarUtils.formatOctalBytes(int2, byteArray0, int0, int4);
    }

    @Test(timeout = 4000)
    public void test5524() throws Throwable {
        byte[] byteArray0 = new byte[1];
        byteArray0[0] = (byte) 0;
        ZipEncoding zipEncoding0 = TarUtils.DEFAULT_ENCODING;
        TarUtils.formatNameBytes("AEp]<", byteArray0, (int) (byte) 0, (int) (byte) 0, zipEncoding0);
        TarUtils.parseBoolean(byteArray0, (byte) 0);
        TarUtils.computeCheckSum(byteArray0);
        TarUtils.formatCheckSumOctalBytes((-5965L), byteArray0, (byte) 0, 787);
        String string0 = "At offset ";
        int int0 = 1927;
        int int1 = 0;
        int default7;
        default7 = TarUtils.formatNameBytes(string0, byteArray0, int0, int1);
        assertTrue(true ? default7 == int0 + int1 : true);
    }

    @Test(timeout = 4000)
    public void test5825() throws Throwable {
        byte[] byteArray0 = new byte[8];
        byteArray0[0] = (byte) 32;
        byteArray0[1] = (byte) (-60);
        byteArray0[2] = (byte) 0;
        byteArray0[3] = (byte) 101;
        byteArray0[4] = (byte) 5;
        byte byte0 = (byte) 0;
        byteArray0[5] = (byte) 0;
        byteArray0[6] = (byte) 55;
        byte byte1 = (byte) 108;
        byteArray0[7] = (byte) 108;
        TarUtils.parseOctalOrBinary(byteArray0, (byte) 0, (byte) 108);
        int int0 = (-128);
        TarUtils.parseName(byteArray0, (int) byte1, int0);
        TarUtils.computeCheckSum(byteArray0);
        TarUtils.computeCheckSum(byteArray0);
        int int1 = 4;
        ZipEncoding zipEncoding0 = null;
        zipEncoding0 = TarUtils.DEFAULT_ENCODING;
    }

    @Test(timeout = 4000)
    public void test5926() throws Throwable {
        String string0 = null;
        byte[] byteArray0 = new byte[7];
        byteArray0[0] = (byte) 124;
        byteArray0[1] = (byte) 48;
        byteArray0[2] = (byte) 0;
        byteArray0[3] = (byte) (-128);
        byteArray0[4] = (byte) 75;
        byteArray0[5] = (byte) (-24);
        byte byte0 = (byte) 0;
        byteArray0[6] = (byte) 0;
        int default8;
        default8 = TarUtils.formatNameBytes((String) null, byteArray0, (int) (byte) 0, (int) (byte) (-128));
        assertTrue(true ? default8 == (int) (byte) 0 + (int) (byte) (-128) : true);
        int int0 = (-957);
        int default9;
        default9 = TarUtils.formatNameBytes(string0, byteArray0, (int) byteArray0[6], int0);
        assertTrue(true ? default9 == (int) byteArray0[6] + int0 : true);
        String string1 = null;
        int int1 = 3358;
        int int2 = 0;
        int default10;
        default10 = TarUtils.formatNameBytes(string1, byteArray0, int1, int2);
        assertTrue(true ? default10 == int1 + int2 : true);
    }

    @Test(timeout = 4000)
    public void test6027() throws Throwable {
        byte[] byteArray0 = new byte[2];
        byte byte0 = (byte) (-18);
        byteArray0[0] = (byte) (-18);
        byte byte1 = (byte) 0;
        byteArray0[1] = (byte) 0;
        int int0 = 2;
        TarUtils.parseOctal(byteArray0, (byte) (-18), 2);
    }
}
