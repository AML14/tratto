/*
 * This file was automatically generated by EvoSuite
 * Sun Oct 15 10:03:10 GMT 2023
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
        byte[] byteArray0 = new byte[5];
        TarUtils.formatLongOctalOrBinaryBytes((-1L), byteArray0, (byte) 14, (byte) 9);
    }

    @Test(timeout = 4000)
    public void test021() throws Throwable {
        TarUtils.formatLongOctalOrBinaryBytes(8589934591L, (byte[]) null, 1, 1);
    }

    @Test(timeout = 4000)
    public void test032() throws Throwable {
        byte[] byteArray0 = new byte[3];
        TarUtils.formatLongOctalOrBinaryBytes(0L, byteArray0, 1407, 0);
    }

    @Test(timeout = 4000)
    public void test043() throws Throwable {
        byte[] byteArray0 = new byte[17];
        byteArray0[0] = (byte) 38;
        byteArray0[1] = (byte) (-111);
        TarUtils.parseOctalOrBinary(byteArray0, 0, 2);
    }

    @Test(timeout = 4000)
    public void test054() throws Throwable {
        byte[] byteArray0 = new byte[9];
        byteArray0[8] = (byte) (-86);
        TarUtils.parseOctal(byteArray0, 8, 5396);
    }

    @Test(timeout = 4000)
    public void test065() throws Throwable {
        byte[] byteArray0 = new byte[5];
        TarUtils.formatLongOctalBytes(0L, byteArray0, (byte) 32, (byte) 3);
    }

    @Test(timeout = 4000)
    public void test156() throws Throwable {
        TarUtils.verifyCheckSum((byte[]) null);
    }

    @Test(timeout = 4000)
    public void test167() throws Throwable {
        TarUtils.parseOctalOrBinary((byte[]) null, (-1010), 2270);
    }

    @Test(timeout = 4000)
    public void test178() throws Throwable {
        TarUtils.parseOctal((byte[]) null, (-2971), 9);
    }

    @Test(timeout = 4000)
    public void test189() throws Throwable {
        ZipEncoding zipEncoding0 = TarUtils.DEFAULT_ENCODING;
        TarUtils.parseName((byte[]) null, (-4302), 253, zipEncoding0);
    }

    @Test(timeout = 4000)
    public void test1910() throws Throwable {
        byte[] byteArray0 = new byte[4];
        ZipEncoding zipEncoding0 = TarUtils.DEFAULT_ENCODING;
        TarUtils.parseName(byteArray0, (int) (byte) 0, 7, zipEncoding0);
    }

    @Test(timeout = 4000)
    public void test2011() throws Throwable {
        byte[] byteArray0 = new byte[7];
        TarUtils.parseName(byteArray0, (-3843), 1663);
    }

    @Test(timeout = 4000)
    public void test2112() throws Throwable {
        TarUtils.parseBoolean((byte[]) null, (-49));
    }

    @Test(timeout = 4000)
    public void test2213() throws Throwable {
        byte[] byteArray0 = new byte[8];
        TarUtils.parseBoolean(byteArray0, 55);
    }

    @Test(timeout = 4000)
    public void test2314() throws Throwable {
        int default0;
        default0 = TarUtils.formatOctalBytes(0L, (byte[]) null, 3293, 3293);
        assertTrue(true ? default0 == 3293 + 3293 : true);
    }

    @Test(timeout = 4000)
    public void test2415() throws Throwable {
        byte[] byteArray0 = new byte[7];
        int default1;
        default1 = TarUtils.formatOctalBytes(929L, byteArray0, 225, (-3730));
        assertTrue(true ? default1 == 225 + (-3730) : true);
    }

    @Test(timeout = 4000)
    public void test2516() throws Throwable {
        byte[] byteArray0 = new byte[3];
        int default2;
        default2 = TarUtils.formatNameBytes("\"jpGIED/po-SHzQnU", byteArray0, 0, (int) (byte) 106, (ZipEncoding) null);
        assertTrue(true ? default2 == 0 + (int) (byte) 106 : true);
    }

    @Test(timeout = 4000)
    public void test2617() throws Throwable {
        byte[] byteArray0 = new byte[0];
        int default3;
        default3 = TarUtils.formatNameBytes("", byteArray0, 1846, 566);
        assertTrue(true ? default3 == 1846 + 566 : true);
    }

    @Test(timeout = 4000)
    public void test2718() throws Throwable {
        TarUtils.formatLongOctalOrBinaryBytes(818L, (byte[]) null, 8730, 8730);
    }

    @Test(timeout = 4000)
    public void test2819() throws Throwable {
        TarUtils.formatLongOctalBytes(0L, (byte[]) null, 9, 2);
    }

    @Test(timeout = 4000)
    public void test2920() throws Throwable {
        byte[] byteArray0 = new byte[5];
        TarUtils.formatLongOctalBytes((-20L), byteArray0, (byte) 1, (byte) 3);
    }

    @Test(timeout = 4000)
    public void test3021() throws Throwable {
        int default4;
        default4 = TarUtils.formatCheckSumOctalBytes(0L, (byte[]) null, 789, 789);
        assertTrue(true ? default4 == 789 + 789 : true);
    }

    @Test(timeout = 4000)
    public void test3122() throws Throwable {
        byte[] byteArray0 = new byte[1];
        int default5;
        default5 = TarUtils.formatCheckSumOctalBytes((-1L), byteArray0, 168, 6);
        assertTrue(true ? default5 == 168 + 6 : true);
    }

    @Test(timeout = 4000)
    public void test3223() throws Throwable {
        TarUtils.computeCheckSum((byte[]) null);
    }

    @Test(timeout = 4000)
    public void test3324() throws Throwable {
        byte[] byteArray0 = new byte[5];
        TarUtils.formatUnsignedOctalString((-570L), byteArray0, (byte) (-63), (byte) 0);
    }

    @Test(timeout = 4000)
    public void test3425() throws Throwable {
        byte[] byteArray0 = new byte[0];
        TarUtils.formatUnsignedOctalString(0L, byteArray0, 4572, 913);
    }

    @Test(timeout = 4000)
    public void test3626() throws Throwable {
        byte[] byteArray0 = new byte[9];
        byteArray0[2] = (byte) 24;
        TarUtils.parseOctal(byteArray0, (byte) 2, (byte) 2);
    }

    @Test(timeout = 4000)
    public void test3727() throws Throwable {
        byte[] byteArray0 = new byte[9];
        byteArray0[0] = (byte) 116;
        TarUtils.parseOctal(byteArray0, 0, (byte) 9);
    }

    @Test(timeout = 4000)
    public void test3828() throws Throwable {
        byte[] byteArray0 = new byte[1];
        byteArray0[0] = (byte) 33;
        boolean boolean0 = TarUtils.verifyCheckSum(byteArray0);
    }

    @Test(timeout = 4000)
    public void test3929() throws Throwable {
        byte[] byteArray0 = new byte[1];
        boolean boolean0 = TarUtils.verifyCheckSum(byteArray0);
    }

    @Test(timeout = 4000)
    public void test4030() throws Throwable {
        byte[] byteArray0 = new byte[14];
        long long0 = TarUtils.computeCheckSum(byteArray0);
    }

    @Test(timeout = 4000)
    public void test4131() throws Throwable {
        byte[] byteArray0 = new byte[9];
        TarUtils.formatLongOctalOrBinaryBytes((-939L), byteArray0, 799, (-786));
    }

    @Test(timeout = 4000)
    public void test4232() throws Throwable {
        byte[] byteArray0 = new byte[9];
        TarUtils.formatLongOctalOrBinaryBytes((-4136L), byteArray0, 0, (byte) (-63));
    }

    @Test(timeout = 4000)
    public void test4333() throws Throwable {
        byte[] byteArray0 = new byte[9];
        TarUtils.formatLongOctalOrBinaryBytes(2305843009213693949L, byteArray0, (byte) (-86), 228);
    }

    @Test(timeout = 4000)
    public void test4434() throws Throwable {
        byte[] byteArray0 = new byte[2];
        TarUtils.formatLongOctalOrBinaryBytes((-808L), byteArray0, (byte) 45, 8);
    }

    @Test(timeout = 4000)
    public void test4535() throws Throwable {
        byte[] byteArray0 = new byte[14];
        ZipEncoding zipEncoding0 = TarUtils.FALLBACK_ENCODING;
        TarUtils.formatNameBytes("{NUL}", byteArray0, (int) (byte) 1, 162, zipEncoding0);
    }

    @Test(timeout = 4000)
    public void test4636() throws Throwable {
        byte[] byteArray0 = new byte[14];
        byteArray0[1] = (byte) 1;
        boolean boolean0 = TarUtils.parseBoolean(byteArray0, (byte) 1);
    }

    @Test(timeout = 4000)
    public void test4737() throws Throwable {
        byte[] byteArray0 = new byte[14];
        boolean boolean0 = TarUtils.parseBoolean(byteArray0, (byte) 1);
    }

    @Test(timeout = 4000)
    public void test4838() throws Throwable {
        byte[] byteArray0 = new byte[3];
        byteArray0[1] = (byte) (-71);
        long long0 = TarUtils.parseOctalOrBinary(byteArray0, 1, (byte) (-71));
    }

    @Test(timeout = 4000)
    public void test4939() throws Throwable {
        byte[] byteArray0 = new byte[5];
        byteArray0[0] = (byte) (-1);
        long long0 = TarUtils.parseOctalOrBinary(byteArray0, 0, (byte) (-1));
    }

    @Test(timeout = 4000)
    public void test5040() throws Throwable {
        byte[] byteArray0 = new byte[3];
        byteArray0[1] = (byte) (-7);
        TarUtils.parseOctalOrBinary(byteArray0, 1, (byte) 113);
    }

    @Test(timeout = 4000)
    public void test5141() throws Throwable {
        byte[] byteArray0 = new byte[9];
        TarUtils.formatUnsignedOctalString(540L, byteArray0, 0, 9);
        long long0 = TarUtils.parseOctal(byteArray0, 0, (byte) 2);
    }

    @Test(timeout = 4000)
    public void test5142() throws Throwable {
        byte[] byteArray0 = new byte[9];
        TarUtils.formatUnsignedOctalString(540L, byteArray0, 0, 9);
        long long0 = TarUtils.parseOctal(byteArray0, 0, (byte) 2);
    }

    @Test(timeout = 4000)
    public void test5243() throws Throwable {
        byte[] byteArray0 = new byte[9];
        long long0 = TarUtils.parseOctal(byteArray0, 8, 5396);
    }

    @Test(timeout = 4000)
    public void test5344() throws Throwable {
        TarUtils.parseOctal((byte[]) null, (-2971), (-2971));
    }

    @Test(timeout = 4000)
    public void test5445() throws Throwable {
        byte[] byteArray0 = new byte[4];
        byteArray0[2] = (byte) 3;
        ZipEncoding zipEncoding0 = TarUtils.FALLBACK_ENCODING;
        String string0 = TarUtils.parseName(byteArray0, 0, (int) (byte) 3, zipEncoding0);
    }

    @Test(timeout = 4000)
    public void test5546() throws Throwable {
        byte[] byteArray0 = new byte[4];
        byteArray0[0] = (byte) 3;
        ZipEncoding zipEncoding0 = TarUtils.FALLBACK_ENCODING;
        String string0 = TarUtils.parseName(byteArray0, 0, (int) (byte) 3, zipEncoding0);
    }

    @Test(timeout = 4000)
    public void test5647() throws Throwable {
        byte[] byteArray0 = new byte[14];
        ZipEncoding zipEncoding0 = TarUtils.FALLBACK_ENCODING;
        int int0 = TarUtils.formatNameBytes("{NUL}", byteArray0, (int) (byte) 1, (-668), zipEncoding0);
    }

    @Test(timeout = 4000)
    public void test5748() throws Throwable {
        byte[] byteArray0 = new byte[0];
        String string0 = TarUtils.parseName(byteArray0, 0, 0);
    }

    @Test(timeout = 4000)
    public void test5849() throws Throwable {
        byte[] byteArray0 = new byte[4];
        TarUtils.formatOctalBytes(0L, byteArray0, (byte) 3, (byte) 48);
    }

    @Test(timeout = 4000)
    public void test5950() throws Throwable {
        byte[] byteArray0 = new byte[0];
        int default6;
        default6 = TarUtils.formatCheckSumOctalBytes((-1260L), byteArray0, 55, (-3132));
        assertTrue(true ? default6 == 55 + (-3132) : true);
    }

    @Test(timeout = 4000)
    public void test6051() throws Throwable {
        byte[] byteArray0 = new byte[7];
        int default7;
        default7 = TarUtils.formatNameBytes((String) null, byteArray0, (int) (byte) 0, (int) (byte) (-128));
        assertTrue(true ? default7 == (int) (byte) 0 + (int) (byte) (-128) : true);
    }
}
