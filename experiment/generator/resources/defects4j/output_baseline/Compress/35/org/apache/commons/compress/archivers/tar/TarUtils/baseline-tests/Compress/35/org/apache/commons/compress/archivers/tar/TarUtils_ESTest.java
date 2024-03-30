/*
 * This file was automatically generated by EvoSuite
 * Sun Oct 15 12:02:51 GMT 2023
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
        byte[] byteArray0 = new byte[4];
        byteArray0[2] = (byte) 3;
        boolean boolean0 = TarUtils.verifyCheckSum(byteArray0);
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        byte[] byteArray0 = new byte[3];
        TarUtils.formatLongOctalOrBinaryBytes((-1033L), byteArray0, 2146806635, 9);
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        TarUtils.formatLongOctalOrBinaryBytes(8589934591L, (byte[]) null, 1, 1);
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        byte[] byteArray0 = new byte[3];
        TarUtils.formatLongOctalOrBinaryBytes(0L, byteArray0, 1407, 0);
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        byte[] byteArray0 = new byte[9];
        TarUtils.formatLongOctalBytes((-556L), byteArray0, (byte) 0, (byte) 2);
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        byte[] byteArray0 = new byte[6];
        byteArray0[5] = (byte) (-107);
        TarUtils.parseOctal(byteArray0, 5, 7);
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        byte[] byteArray0 = new byte[5];
        TarUtils.formatLongOctalBytes(0L, byteArray0, (byte) 32, (byte) 3);
    }

    @Test(timeout = 4000)
    public void test077() throws Throwable {
        byte[] byteArray0 = new byte[3];
        TarUtils.formatCheckSumOctalBytes((byte) 61, byteArray0, (byte) 0, 4);
    }

    @Test(timeout = 4000)
    public void test088() throws Throwable {
        byte[] byteArray0 = new byte[3];
        int int0 = TarUtils.formatNameBytes("ibm437", byteArray0, (int) (byte) 0, 0);
    }

    @Test(timeout = 4000)
    public void test099() throws Throwable {
        byte[] byteArray0 = new byte[1];
        int int0 = TarUtils.formatNameBytes(" is too large for ", byteArray0, (int) (byte) 1, 0);
    }

    @Test(timeout = 4000)
    public void test1010() throws Throwable {
        byte[] byteArray0 = new byte[7];
        int int0 = TarUtils.formatNameBytes("Jx0DNRLj<alwcB8)!l", byteArray0, 0, (int) (byte) (-7));
    }

    @Test(timeout = 4000)
    public void test1111() throws Throwable {
        byte[] byteArray0 = new byte[5];
        byteArray0[0] = (byte) (-100);
        long long0 = TarUtils.computeCheckSum(byteArray0);
    }

    @Test(timeout = 4000)
    public void test1212() throws Throwable {
        TarUtils.verifyCheckSum((byte[]) null);
    }

    @Test(timeout = 4000)
    public void test1313() throws Throwable {
        TarUtils.parseOctalOrBinary((byte[]) null, (-1010), 2270);
    }

    @Test(timeout = 4000)
    public void test1414() throws Throwable {
        ZipEncoding zipEncoding0 = TarUtils.DEFAULT_ENCODING;
        TarUtils.parseName((byte[]) null, (-4302), 253, zipEncoding0);
    }

    @Test(timeout = 4000)
    public void test1515() throws Throwable {
        byte[] byteArray0 = new byte[3];
        ZipEncoding zipEncoding0 = TarUtils.DEFAULT_ENCODING;
        TarUtils.parseName(byteArray0, (int) (byte) (-101), 226, zipEncoding0);
    }

    @Test(timeout = 4000)
    public void test1616() throws Throwable {
        TarUtils.parseName((byte[]) null, 2901, 2901);
    }

    @Test(timeout = 4000)
    public void test1717() throws Throwable {
        TarUtils.parseBoolean((byte[]) null, 2028);
    }

    @Test(timeout = 4000)
    public void test1818() throws Throwable {
        byte[] byteArray0 = new byte[8];
        TarUtils.parseBoolean(byteArray0, 55);
    }

    @Test(timeout = 4000)
    public void test1919() throws Throwable {
        TarUtils.formatOctalBytes(0L, (byte[]) null, 3293, 3293);
    }

    @Test(timeout = 4000)
    public void test2020() throws Throwable {
        byte[] byteArray0 = new byte[7];
        TarUtils.formatOctalBytes(929L, byteArray0, 225, (-3730));
    }

    @Test(timeout = 4000)
    public void test2121() throws Throwable {
        byte[] byteArray0 = new byte[3];
        TarUtils.formatNameBytes("\"jpGIED/po-SHzQnU", byteArray0, 0, (int) (byte) 106, (ZipEncoding) null);
    }

    @Test(timeout = 4000)
    public void test2222() throws Throwable {
        byte[] byteArray0 = new byte[9];
        ZipEncoding zipEncoding0 = TarUtils.DEFAULT_ENCODING;
        TarUtils.formatNameBytes("L=uZ", byteArray0, 684, 0, zipEncoding0);
    }

    @Test(timeout = 4000)
    public void test2323() throws Throwable {
        TarUtils.formatLongOctalOrBinaryBytes((-408L), (byte[]) null, (-845), 13);
    }

    @Test(timeout = 4000)
    public void test2424() throws Throwable {
        TarUtils.formatLongOctalBytes(0L, (byte[]) null, 9, 2);
    }

    @Test(timeout = 4000)
    public void test2525() throws Throwable {
        byte[] byteArray0 = new byte[5];
        TarUtils.formatLongOctalBytes((-217L), byteArray0, 170, 0);
    }

    @Test(timeout = 4000)
    public void test2626() throws Throwable {
        TarUtils.formatCheckSumOctalBytes(0L, (byte[]) null, 789, 789);
    }

    @Test(timeout = 4000)
    public void test2727() throws Throwable {
        TarUtils.computeCheckSum((byte[]) null);
    }

    @Test(timeout = 4000)
    public void test2828() throws Throwable {
        byte[] byteArray0 = new byte[1];
        TarUtils.formatLongOctalOrBinaryBytes((-1259L), byteArray0, 68, (-1715));
    }

    @Test(timeout = 4000)
    public void test2929() throws Throwable {
        byte[] byteArray0 = new byte[3];
        TarUtils.formatUnsignedOctalString((-395L), byteArray0, (byte) (-118), (byte) (-118));
    }

    @Test(timeout = 4000)
    public void test3030() throws Throwable {
        byte[] byteArray0 = new byte[0];
        TarUtils.formatUnsignedOctalString(0L, byteArray0, 4572, 913);
    }

    @Test(timeout = 4000)
    public void test3131() throws Throwable {
        byte[] byteArray0 = new byte[1];
        ZipEncoding zipEncoding0 = TarUtils.DEFAULT_ENCODING;
        int int0 = TarUtils.formatNameBytes("AEp]<", byteArray0, (int) (byte) 0, (int) (byte) 0, zipEncoding0);
    }

    @Test(timeout = 4000)
    public void test3233() throws Throwable {
        byte[] byteArray0 = new byte[0];
        ZipEncoding zipEncoding0 = TarUtils.FALLBACK_ENCODING;
        String string0 = TarUtils.parseName(byteArray0, (-1173), (-1173), zipEncoding0);
    }

    @Test(timeout = 4000)
    public void test3334() throws Throwable {
        byte[] byteArray0 = new byte[3];
        TarUtils.parseOctal(byteArray0, (byte) 0, 0);
    }

    @Test(timeout = 4000)
    public void test3435() throws Throwable {
        byte[] byteArray0 = new byte[6];
        byteArray0[0] = (byte) (-26);
        byteArray0[1] = (byte) 3;
        byteArray0[2] = (byte) 7;
        byteArray0[3] = (byte) 8;
        byteArray0[4] = (byte) 115;
        byteArray0[5] = (byte) (-107);
        boolean boolean0 = TarUtils.verifyCheckSum(byteArray0);
    }

    @Test(timeout = 4000)
    public void test3536() throws Throwable {
        byte[] byteArray0 = new byte[6];
        byteArray0[0] = (byte) (-26);
        boolean boolean0 = TarUtils.verifyCheckSum(byteArray0);
    }

    @Test(timeout = 4000)
    public void test3637() throws Throwable {
        byte[] byteArray0 = new byte[6];
        boolean boolean0 = TarUtils.verifyCheckSum(byteArray0);
    }

    @Test(timeout = 4000)
    public void test3738() throws Throwable {
        byte[] byteArray0 = new byte[8];
        long long0 = TarUtils.computeCheckSum(byteArray0);
    }

    @Test(timeout = 4000)
    public void test3839() throws Throwable {
        byte[] byteArray0 = new byte[3];
        TarUtils.formatLongOctalOrBinaryBytes((-4136L), byteArray0, 0, (byte) (-63));
    }

    @Test(timeout = 4000)
    public void test3940() throws Throwable {
        byte[] byteArray0 = new byte[7];
        TarUtils.formatLongOctalOrBinaryBytes((-1510L), byteArray0, (byte) 26, 3);
    }

    @Test(timeout = 4000)
    public void test4041() throws Throwable {
        byte[] byteArray0 = new byte[0];
        TarUtils.formatNameBytes("", byteArray0, 0, 163);
    }

    @Test(timeout = 4000)
    public void test4142() throws Throwable {
        byte[] byteArray0 = new byte[4];
        byteArray0[2] = (byte) 3;
        ZipEncoding zipEncoding0 = TarUtils.DEFAULT_ENCODING;
        String string0 = TarUtils.parseName(byteArray0, 0, (int) (byte) 3, zipEncoding0);
    }

    @Test(timeout = 4000)
    public void test4243() throws Throwable {
        byte[] byteArray0 = new byte[9];
        TarUtils.parseName(byteArray0, (int) (byte) 6, (int) (byte) 28);
    }

    @Test(timeout = 4000)
    public void test4344() throws Throwable {
        byte[] byteArray0 = new byte[8];
        byteArray0[1] = (byte) 1;
        boolean boolean0 = TarUtils.parseBoolean(byteArray0, (byte) 1);
    }

    @Test(timeout = 4000)
    public void test4445() throws Throwable {
        byte[] byteArray0 = new byte[9];
        boolean boolean0 = TarUtils.parseBoolean(byteArray0, (byte) 0);
    }

    @Test(timeout = 4000)
    public void test4546() throws Throwable {
        byte[] byteArray0 = new byte[8];
        byteArray0[0] = (byte) (-60);
        TarUtils.parseOctalOrBinary(byteArray0, (byte) 0, (byte) 108);
    }

    @Test(timeout = 4000)
    public void test4647() throws Throwable {
        byte[] byteArray0 = new byte[10];
        byteArray0[0] = (byte) (-5);
        long long0 = TarUtils.parseOctalOrBinary(byteArray0, 0, (byte) (-5));
    }

    @Test(timeout = 4000)
    public void test4748() throws Throwable {
        byte[] byteArray0 = new byte[9];
        TarUtils.formatUnsignedOctalString(540L, byteArray0, 0, 9);
        long long0 = TarUtils.parseOctal(byteArray0, 0, (byte) 2);
    }

    @Test(timeout = 4000)
    public void test4850() throws Throwable {
        byte[] byteArray0 = new byte[4];
        long long0 = TarUtils.parseOctal(byteArray0, (byte) 0, (byte) 67);
    }

    @Test(timeout = 4000)
    public void test4951() throws Throwable {
        byte[] byteArray0 = new byte[1];
        TarUtils.parseOctalOrBinary(byteArray0, 0, (byte) (-16));
    }

    @Test(timeout = 4000)
    public void test5052() throws Throwable {
        byte[] byteArray0 = new byte[1];
        ZipEncoding zipEncoding0 = TarUtils.FALLBACK_ENCODING;
        int int0 = TarUtils.formatNameBytes("dv@VnX%", byteArray0, 0, (-668), zipEncoding0);
    }

    @Test(timeout = 4000)
    public void test5154() throws Throwable {
        byte[] byteArray0 = new byte[0];
        String string0 = TarUtils.parseName(byteArray0, 0, 0);
    }

    @Test(timeout = 4000)
    public void test5255() throws Throwable {
        byte[] byteArray0 = new byte[1];
        TarUtils.formatOctalBytes(0L, byteArray0, (byte) 74, 1);
    }

    @Test(timeout = 4000)
    public void test5356() throws Throwable {
        byte[] byteArray0 = new byte[9];
        byteArray0[0] = (byte) 116;
        TarUtils.parseOctal(byteArray0, 0, (byte) 2);
    }

    @Test(timeout = 4000)
    public void test5457() throws Throwable {
        byte[] byteArray0 = new byte[0];
        TarUtils.formatCheckSumOctalBytes((-1260L), byteArray0, 55, (-3132));
    }

    @Test(timeout = 4000)
    public void test5558() throws Throwable {
        byte[] byteArray0 = new byte[7];
        TarUtils.formatNameBytes((String) null, byteArray0, (int) (byte) 0, (int) (byte) (-128));
    }

    @Test(timeout = 4000)
    public void test5659() throws Throwable {
        byte[] byteArray0 = new byte[4];
        TarUtils.formatLongOctalOrBinaryBytes(67L, byteArray0, 194, (byte) 8);
    }
}
