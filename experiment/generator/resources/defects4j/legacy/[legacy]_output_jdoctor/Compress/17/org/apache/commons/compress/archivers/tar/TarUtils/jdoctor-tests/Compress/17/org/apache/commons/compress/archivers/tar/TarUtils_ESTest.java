/*
 * This file was automatically generated by EvoSuite
 * Sun Oct 15 08:38:25 GMT 2023
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
    public void test000() throws Throwable {
        byte[] byteArray0 = new byte[1];
        byteArray0[0] = (byte) (-15);
        boolean boolean0 = TarUtils.verifyCheckSum(byteArray0);
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        byte[] byteArray0 = new byte[3];
        TarUtils.formatLongOctalOrBinaryBytes(0L, byteArray0, 1407, 0);
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        byte[] byteArray0 = new byte[9];
        byteArray0[0] = (byte) 116;
        byteArray0[1] = (byte) (-1);
        TarUtils.parseOctal(byteArray0, 0, (byte) 2);
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        byte[] byteArray0 = new byte[9];
        byteArray0[8] = (byte) (-86);
        TarUtils.parseOctal(byteArray0, 8, 5396);
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        byte[] byteArray0 = new byte[4];
        TarUtils.formatOctalBytes(0L, byteArray0, (byte) 3, (byte) 48);
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        byte[] byteArray0 = new byte[5];
        TarUtils.formatLongOctalBytes(0L, byteArray0, (byte) 32, (byte) 3);
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        byte[] byteArray0 = new byte[1];
        ZipEncoding zipEncoding0 = TarUtils.DEFAULT_ENCODING;
        int int0 = TarUtils.formatNameBytes("AEp]<", byteArray0, (int) (byte) 0, (int) (byte) 0, zipEncoding0);
    }

    @Test(timeout = 4000)
    public void test067() throws Throwable {
        byte[] byteArray0 = new byte[1];
        ZipEncoding zipEncoding0 = TarUtils.DEFAULT_ENCODING;
        int int0 = TarUtils.formatNameBytes("AEp]<", byteArray0, (int) (byte) 0, (int) (byte) 0, zipEncoding0);
    }

    @Test(timeout = 4000)
    public void test078() throws Throwable {
        byte[] byteArray0 = new byte[3];
        int int0;
        int0 = TarUtils.formatNameBytes("IBM850", byteArray0, (int) (byte) 0, 0);
        assertTrue(true ? int0 == (int) (byte) 0 + 0 : true);
    }

    @Test(timeout = 4000)
    public void test089() throws Throwable {
        byte[] byteArray0 = new byte[1];
        int int0;
        int0 = TarUtils.formatNameBytes(" is too large for ", byteArray0, (int) (byte) 1, 0);
        assertTrue(true ? int0 == (int) (byte) 1 + 0 : true);
    }

    @Test(timeout = 4000)
    public void test0910() throws Throwable {
        byte[] byteArray0 = new byte[7];
        int int0;
        int0 = TarUtils.formatNameBytes("Jx0DNRLj<alwcB8)!l", byteArray0, 0, (int) (byte) (-7));
        assertTrue(true ? int0 == 0 + (int) (byte) (-7) : true);
    }

    @Test(timeout = 4000)
    public void test1011() throws Throwable {
        byte[] byteArray0 = new byte[2];
        byteArray0[1] = (byte) (-2);
        long long0 = TarUtils.computeCheckSum(byteArray0);
    }

    @Test(timeout = 4000)
    public void test1112() throws Throwable {
        TarUtils.verifyCheckSum((byte[]) null);
    }

    @Test(timeout = 4000)
    public void test1213() throws Throwable {
        TarUtils.parseOctalOrBinary((byte[]) null, (-1010), 2270);
    }

    @Test(timeout = 4000)
    public void test1314() throws Throwable {
        byte[] byteArray0 = new byte[0];
        TarUtils.parseOctalOrBinary(byteArray0, 846, 846);
    }

    @Test(timeout = 4000)
    public void test1415() throws Throwable {
        TarUtils.parseOctal((byte[]) null, 9557, 9557);
    }

    @Test(timeout = 4000)
    public void test1516() throws Throwable {
        ZipEncoding zipEncoding0 = TarUtils.DEFAULT_ENCODING;
        TarUtils.parseName((byte[]) null, (-4302), 253, zipEncoding0);
    }

    @Test(timeout = 4000)
    public void test1617() throws Throwable {
        byte[] byteArray0 = new byte[4];
        TarUtils.parseName(byteArray0, (-590), 9);
    }

    @Test(timeout = 4000)
    public void test1718() throws Throwable {
        TarUtils.parseBoolean((byte[]) null, (-49));
    }

    @Test(timeout = 4000)
    public void test1819() throws Throwable {
        byte[] byteArray0 = new byte[8];
        TarUtils.parseBoolean(byteArray0, 55);
    }

    @Test(timeout = 4000)
    public void test1920() throws Throwable {
        TarUtils.formatUnsignedOctalString((-1L), (byte[]) null, 97, 156);
    }

    @Test(timeout = 4000)
    public void test2021() throws Throwable {
        int default0;
        default0 = TarUtils.formatOctalBytes(0L, (byte[]) null, 3293, 3293);
        assertTrue(true ? default0 == 3293 + 3293 : true);
    }

    @Test(timeout = 4000)
    public void test2122() throws Throwable {
        byte[] byteArray0 = new byte[7];
        int default1;
        default1 = TarUtils.formatOctalBytes(929L, byteArray0, 225, (-3730));
        assertTrue(true ? default1 == 225 + (-3730) : true);
    }

    @Test(timeout = 4000)
    public void test2223() throws Throwable {
        byte[] byteArray0 = new byte[3];
        int default2;
        default2 = TarUtils.formatNameBytes("\"jpGIED/po-SHzQnU", byteArray0, 0, (int) (byte) 106, (ZipEncoding) null);
        assertTrue(true ? default2 == 0 + (int) (byte) 106 : true);
    }

    @Test(timeout = 4000)
    public void test2324() throws Throwable {
        byte[] byteArray0 = new byte[0];
        int default3;
        default3 = TarUtils.formatNameBytes("", byteArray0, 1846, 566);
        assertTrue(true ? default3 == 1846 + 566 : true);
    }

    @Test(timeout = 4000)
    public void test2425() throws Throwable {
        TarUtils.formatLongOctalBytes(0L, (byte[]) null, 9, 2);
    }

    @Test(timeout = 4000)
    public void test2526() throws Throwable {
        byte[] byteArray0 = new byte[5];
        TarUtils.formatLongOctalBytes((-217L), byteArray0, 170, 0);
    }

    @Test(timeout = 4000)
    public void test2627() throws Throwable {
        int default4;
        default4 = TarUtils.formatCheckSumOctalBytes(0L, (byte[]) null, 789, 789);
        assertTrue(true ? default4 == 789 + 789 : true);
    }

    @Test(timeout = 4000)
    public void test2728() throws Throwable {
        byte[] byteArray0 = new byte[1];
        TarUtils.formatCheckSumOctalBytes((-5965L), byteArray0, (byte) 0, 787);
    }

    @Test(timeout = 4000)
    public void test2829() throws Throwable {
        TarUtils.computeCheckSum((byte[]) null);
    }

    @Test(timeout = 4000)
    public void test2930() throws Throwable {
        byte[] byteArray0 = new byte[1];
        TarUtils.formatLongOctalOrBinaryBytes((-1259L), byteArray0, 68, (-1715));
    }

    @Test(timeout = 4000)
    public void test3031() throws Throwable {
        byte[] byteArray0 = new byte[5];
        TarUtils.formatUnsignedOctalString((-570L), byteArray0, (byte) (-63), (byte) 0);
    }

    @Test(timeout = 4000)
    public void test3132() throws Throwable {
        byte[] byteArray0 = new byte[0];
        TarUtils.formatUnsignedOctalString(0L, byteArray0, 4572, 913);
    }

    @Test(timeout = 4000)
    public void test3233() throws Throwable {
        byte[] byteArray0 = new byte[4];
        byteArray0[2] = (byte) 3;
        ZipEncoding zipEncoding0 = TarUtils.DEFAULT_ENCODING;
        String string0 = TarUtils.parseName(byteArray0, 0, (int) (byte) 3, zipEncoding0);
    }

    @Test(timeout = 4000)
    public void test3334() throws Throwable {
        byte[] byteArray0 = new byte[0];
        ZipEncoding zipEncoding0 = TarUtils.FALLBACK_ENCODING;
        String string0 = TarUtils.parseName(byteArray0, (-1173), (-1173), zipEncoding0);
    }

    @Test(timeout = 4000)
    public void test3435() throws Throwable {
        byte[] byteArray0 = new byte[3];
        ZipEncoding zipEncoding0 = TarUtils.DEFAULT_ENCODING;
        TarUtils.parseName(byteArray0, (int) (byte) (-101), 226, zipEncoding0);
    }

    @Test(timeout = 4000)
    public void test3536() throws Throwable {
        byte[] byteArray0 = new byte[3];
        TarUtils.parseOctal(byteArray0, (byte) 0, 0);
    }

    @Test(timeout = 4000)
    public void test3637() throws Throwable {
        byte[] byteArray0 = new byte[8];
        byteArray0[0] = (byte) 97;
        boolean boolean0 = TarUtils.verifyCheckSum(byteArray0);
    }

    @Test(timeout = 4000)
    public void test3738() throws Throwable {
        byte[] byteArray0 = new byte[8];
        boolean boolean0 = TarUtils.verifyCheckSum(byteArray0);
    }

    @Test(timeout = 4000)
    public void test3839() throws Throwable {
        byte[] byteArray0 = new byte[8];
        long long0 = TarUtils.computeCheckSum(byteArray0);
    }

    @Test(timeout = 4000)
    public void test3940() throws Throwable {
        byte[] byteArray0 = new byte[3];
        TarUtils.formatLongOctalOrBinaryBytes((-4136L), byteArray0, 0, (byte) (-63));
    }

    @Test(timeout = 4000)
    public void test4041() throws Throwable {
        byte[] byteArray0 = new byte[5];
        TarUtils.formatLongOctalOrBinaryBytes((-646L), byteArray0, 2, (byte) 80);
    }

    @Test(timeout = 4000)
    public void test4142() throws Throwable {
        TarUtils.formatLongOctalOrBinaryBytes(8589934591L, (byte[]) null, 1, 1);
    }

    @Test(timeout = 4000)
    public void test4243() throws Throwable {
        byte[] byteArray0 = new byte[7];
        TarUtils.formatLongOctalOrBinaryBytes((-1510L), byteArray0, (byte) 26, 3);
    }

    @Test(timeout = 4000)
    public void test4344() throws Throwable {
        byte[] byteArray0 = new byte[8];
        byteArray0[4] = (byte) (-94);
        String string0 = TarUtils.parseName(byteArray0, (int) (byte) 0, 8);
    }

    @Test(timeout = 4000)
    public void test4445() throws Throwable {
        byte[] byteArray0 = new byte[8];
        String string0 = TarUtils.parseName(byteArray0, (int) (byte) 0, 8);
    }

    @Test(timeout = 4000)
    public void test4546() throws Throwable {
        byte[] byteArray0 = new byte[8];
        byteArray0[1] = (byte) 1;
        boolean boolean0 = TarUtils.parseBoolean(byteArray0, (byte) 1);
    }

    @Test(timeout = 4000)
    public void test4647() throws Throwable {
        byte[] byteArray0 = new byte[5];
        boolean boolean0 = TarUtils.parseBoolean(byteArray0, 0);
    }

    @Test(timeout = 4000)
    public void test4748() throws Throwable {
        byte[] byteArray0 = new byte[1];
        byteArray0[0] = (byte) (-5);
        long long0 = TarUtils.parseOctalOrBinary(byteArray0, 0, (byte) (-5));
    }

    @Test(timeout = 4000)
    public void test4849() throws Throwable {
        byte[] byteArray0 = new byte[9];
        TarUtils.formatUnsignedOctalString(540L, byteArray0, 0, 9);
        TarUtils.parseOctal(byteArray0, 0, (byte) 2);
    }

    @Test(timeout = 4000)
    public void test4950() throws Throwable {
        byte[] byteArray0 = new byte[4];
        long long0 = TarUtils.parseOctal(byteArray0, (byte) 0, (byte) 67);
    }

    @Test(timeout = 4000)
    public void test5051() throws Throwable {
        byte[] byteArray0 = new byte[1];
        TarUtils.parseOctalOrBinary(byteArray0, 0, 0);
    }

    @Test(timeout = 4000)
    public void test5152() throws Throwable {
        byte[] byteArray0 = new byte[1];
        ZipEncoding zipEncoding0 = TarUtils.FALLBACK_ENCODING;
        int int0 = TarUtils.formatNameBytes("dv@VnX%", byteArray0, 0, (-668), zipEncoding0);
    }

    @Test(timeout = 4000)
    public void test5153() throws Throwable {
        byte[] byteArray0 = new byte[1];
        ZipEncoding zipEncoding0 = TarUtils.FALLBACK_ENCODING;
        int int0 = TarUtils.formatNameBytes("dv@VnX%", byteArray0, 0, (-668), zipEncoding0);
    }

    @Test(timeout = 4000)
    public void test5254() throws Throwable {
        byte[] byteArray0 = new byte[2];
        ZipEncoding zipEncoding0 = TarUtils.FALLBACK_ENCODING;
        TarUtils.formatNameBytes("", byteArray0, 227, (int) (byte) 0, zipEncoding0);
    }

    @Test(timeout = 4000)
    public void test5355() throws Throwable {
        byte[] byteArray0 = new byte[9];
        byteArray0[0] = (byte) 116;
        TarUtils.parseOctal(byteArray0, 0, (byte) 2);
    }

    @Test(timeout = 4000)
    public void test5456() throws Throwable {
        byte[] byteArray0 = new byte[0];
        int default5;
        default5 = TarUtils.formatCheckSumOctalBytes((-1260L), byteArray0, 55, (-3132));
        assertTrue(true ? default5 == 55 + (-3132) : true);
    }

    @Test(timeout = 4000)
    public void test5557() throws Throwable {
        byte[] byteArray0 = new byte[7];
        int default6;
        default6 = TarUtils.formatNameBytes((String) null, byteArray0, (int) (byte) 0, (int) (byte) (-128));
        assertTrue(true ? default6 == (int) (byte) 0 + (int) (byte) (-128) : true);
    }
}
