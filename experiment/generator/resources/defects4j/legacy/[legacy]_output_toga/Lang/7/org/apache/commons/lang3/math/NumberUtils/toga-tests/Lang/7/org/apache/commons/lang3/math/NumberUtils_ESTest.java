/*
 * This file was automatically generated by EvoSuite
 * Mon Oct 16 00:57:11 GMT 2023
 */
package org.apache.commons.lang3.math;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.apache.commons.lang3.math.NumberUtils;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class NumberUtils_ESTest extends NumberUtils_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test0000() throws Throwable {
        long long0 = NumberUtils.max((-3664L), (-3664L), (-3664L));
        assertEquals(0, long0);
    }

    @Test(timeout = 4000)
    public void test0011() throws Throwable {
        byte byte0 = NumberUtils.min((byte) (-114), (byte) (-72), (byte) (-112));
    }

    @Test(timeout = 4000)
    public void test0022() throws Throwable {
        short short0 = NumberUtils.min((short) 0, (short) 86, (short) 86);
    }

    @Test(timeout = 4000)
    public void test0033() throws Throwable {
        long long0 = NumberUtils.min((-1042L), (-1042L), (-1718L));
        assertEquals(1, long0);
    }

    @Test(timeout = 4000)
    public void test0044() throws Throwable {
        NumberUtils.createNumber("%.J^M#DI@yl\"o^");
    }

    @Test(timeout = 4000)
    public void test0055() throws Throwable {
        NumberUtils.createNumber("gF");
    }

    @Test(timeout = 4000)
    public void test0066() throws Throwable {
        short short0 = NumberUtils.toShort("%.J^M#DI@yl\"o^", (short) 0);
        assertNotNull(short0);
    }

    @Test(timeout = 4000)
    public void test0077() throws Throwable {
        short short0 = NumberUtils.toShort("5");
        assertNotNull(short0);
    }

    @Test(timeout = 4000)
    public void test0088() throws Throwable {
        long long0 = NumberUtils.toLong("'&;V0%Jd}BE$.w", (-1L));
    }

    @Test(timeout = 4000)
    public void test0099() throws Throwable {
        long long0 = NumberUtils.toLong("8");
        assertEquals(1, long0);
    }

    @Test(timeout = 4000)
    public void test01010() throws Throwable {
        int int0 = NumberUtils.toInt("-s?Nb;k=", 0);
    }

    @Test(timeout = 4000)
    public void test01111() throws Throwable {
        NumberUtils numberUtils0 = new NumberUtils();
        int int0 = NumberUtils.toInt("-s?Nb;k=", (int) numberUtils0.SHORT_MINUS_ONE);
    }

    @Test(timeout = 4000)
    public void test01212() throws Throwable {
        float float0 = NumberUtils.toFloat(">^(D`^eJPu@", (-3172.517F));
    }

    @Test(timeout = 4000)
    public void test01313() throws Throwable {
        double double0 = NumberUtils.toDouble(":/C}", (-2437.57933038));
    }

    @Test(timeout = 4000)
    public void test01414() throws Throwable {
        double double0 = NumberUtils.toDouble("6F");
    }

    @Test(timeout = 4000)
    public void test01515() throws Throwable {
        byte byte0 = NumberUtils.toByte("", (byte) (-33));
    }

    @Test(timeout = 4000)
    public void test01616() throws Throwable {
        short[] shortArray0 = new short[1];
        shortArray0[0] = (short) 803;
        short short0 = NumberUtils.min(shortArray0);
    }

    @Test(timeout = 4000)
    public void test01717() throws Throwable {
        long[] longArray0 = new long[3];
        longArray0[0] = (long) (byte) 39;
        longArray0[1] = (long) 39;
        longArray0[2] = (long) (byte) 39;
        long long0 = NumberUtils.min(longArray0);
        assertEquals(1, long0);
    }

    @Test(timeout = 4000)
    public void test01818() throws Throwable {
        long[] longArray0 = new long[9];
        longArray0[4] = (-947L);
        long long0 = NumberUtils.min(longArray0);
    }

    @Test(timeout = 4000)
    public void test01919() throws Throwable {
        int[] intArray0 = new int[7];
        intArray0[0] = (int) (short) 2022;
        intArray0[1] = (int) (short) 2022;
        intArray0[2] = (int) (short) 2022;
        intArray0[3] = (int) (short) 2022;
        intArray0[4] = (int) (short) 2022;
        intArray0[5] = (int) (short) 2022;
        intArray0[6] = (int) (short) 2022;
        int int0 = NumberUtils.min(intArray0);
        assertEquals(1, int0);
    }

    @Test(timeout = 4000)
    public void test02020() throws Throwable {
        NumberUtils numberUtils0 = new NumberUtils();
        int[] intArray0 = new int[3];
        intArray0[0] = (int) (short) numberUtils0.SHORT_MINUS_ONE;
        int int0 = NumberUtils.min(intArray0);
        assertEquals(1, int0);
    }

    @Test(timeout = 4000)
    public void test02121() throws Throwable {
        float[] floatArray0 = new float[1];
        floatArray0[0] = (float) 138L;
        float float0 = NumberUtils.min(floatArray0);
    }

    @Test(timeout = 4000)
    public void test02222() throws Throwable {
        NumberUtils numberUtils0 = new NumberUtils();
        float[] floatArray0 = new float[5];
        floatArray0[1] = (float) (long) numberUtils0.LONG_MINUS_ONE;
        float float0 = NumberUtils.min(floatArray0);
    }

    @Test(timeout = 4000)
    public void test02323() throws Throwable {
        double[] doubleArray0 = new double[1];
        doubleArray0[0] = (double) (byte) 36;
        double double0 = NumberUtils.min(doubleArray0);
    }

    @Test(timeout = 4000)
    public void test02424() throws Throwable {
        byte[] byteArray0 = new byte[4];
        byteArray0[0] = (byte) 85;
        byteArray0[1] = (byte) 27;
        byteArray0[2] = (byte) 68;
        byteArray0[3] = (byte) 117;
        byte byte0 = NumberUtils.min(byteArray0);
        assertEquals(byteArray0, byte0);
    }

    @Test(timeout = 4000)
    public void test02525() throws Throwable {
        short short0 = NumberUtils.min((short) 101, (short) 101, (short) 995);
    }

    @Test(timeout = 4000)
    public void test02626() throws Throwable {
        long long0 = NumberUtils.min(107L, 3488L, 3488L);
        assertEquals(0, long0);
    }

    @Test(timeout = 4000)
    public void test02727() throws Throwable {
        int int0 = NumberUtils.min(0, 0, 1191);
        assertEquals(1, int0);
    }

    @Test(timeout = 4000)
    public void test02828() throws Throwable {
        float float0 = NumberUtils.min(1349.4127F, 1349.4127F, 1349.4127F);
    }

    @Test(timeout = 4000)
    public void test02929() throws Throwable {
        float float0 = NumberUtils.min((-1827.8975F), (-1827.8975F), (-2771.039F));
    }

    @Test(timeout = 4000)
    public void test03030() throws Throwable {
        double double0 = NumberUtils.min((double) 1.0F, (double) 1.0F, (double) 1.0F);
    }

    @Test(timeout = 4000)
    public void test03131() throws Throwable {
        double double0 = NumberUtils.min(1831.9964283, (-234.46364), 1831.9964283);
    }

    @Test(timeout = 4000)
    public void test03232() throws Throwable {
        byte byte0 = NumberUtils.min((byte) 1, (byte) 1, (byte) 1);
        assertNotNull(byte0);
    }

    @Test(timeout = 4000)
    public void test03333() throws Throwable {
        short[] shortArray0 = new short[9];
        shortArray0[1] = (short) 4112;
        short short0 = NumberUtils.max(shortArray0);
    }

    @Test(timeout = 4000)
    public void test03434() throws Throwable {
        short[] shortArray0 = new short[1];
        shortArray0[0] = (short) (-635);
        short short0 = NumberUtils.max(shortArray0);
    }

    @Test(timeout = 4000)
    public void test03535() throws Throwable {
        long[] longArray0 = new long[6];
        longArray0[0] = (long) (byte) 117;
        long long0 = NumberUtils.max(longArray0);
        assertEquals(1, long0);
    }

    @Test(timeout = 4000)
    public void test03636() throws Throwable {
        long[] longArray0 = new long[1];
        longArray0[0] = (-691L);
        long long0 = NumberUtils.max(longArray0);
        assertEquals(1, long0);
    }

    @Test(timeout = 4000)
    public void test03737() throws Throwable {
        int[] intArray0 = new int[6];
        intArray0[1] = 69;
        int int0 = NumberUtils.max(intArray0);
    }

    @Test(timeout = 4000)
    public void test03838() throws Throwable {
        int[] intArray0 = new int[1];
        intArray0[0] = (-1);
        int int0 = NumberUtils.max(intArray0);
    }

    @Test(timeout = 4000)
    public void test03939() throws Throwable {
        NumberUtils numberUtils0 = new NumberUtils();
        float[] floatArray0 = new float[5];
        floatArray0[0] = (float) numberUtils0.FLOAT_ONE;
        float float0 = NumberUtils.max(floatArray0);
    }

    @Test(timeout = 4000)
    public void test04040() throws Throwable {
        float[] floatArray0 = new float[2];
        floatArray0[0] = (-1.0F);
        floatArray0[1] = (-1.0F);
        float float0 = NumberUtils.max(floatArray0);
    }

    @Test(timeout = 4000)
    public void test04141() throws Throwable {
        NumberUtils numberUtils0 = new NumberUtils();
        double[] doubleArray0 = new double[3];
        doubleArray0[0] = (double) (short) numberUtils0.SHORT_ONE;
        double double0 = NumberUtils.max(doubleArray0);
    }

    @Test(timeout = 4000)
    public void test04242() throws Throwable {
        double[] doubleArray0 = new double[2];
        doubleArray0[0] = (double) (short) (-925);
        doubleArray0[1] = (-1.0);
        double double0 = NumberUtils.max(doubleArray0);
    }

    @Test(timeout = 4000)
    public void test04343() throws Throwable {
        byte[] byteArray0 = new byte[9];
        byteArray0[0] = (byte) 36;
        byte byte0 = NumberUtils.max(byteArray0);
    }

    @Test(timeout = 4000)
    public void test04444() throws Throwable {
        byte[] byteArray0 = new byte[9];
        byteArray0[0] = (byte) (-93);
        byteArray0[1] = (byte) (-93);
        byteArray0[2] = (byte) (-93);
        byteArray0[3] = (byte) (-93);
        byteArray0[4] = (byte) (-93);
        byteArray0[5] = (byte) (-93);
        byteArray0[6] = (byte) (-56);
        byteArray0[7] = (byte) (-93);
        byteArray0[8] = (byte) (-93);
        byte byte0 = NumberUtils.max(byteArray0);
        assertEquals(byteArray0, byte0);
    }

    @Test(timeout = 4000)
    public void test04545() throws Throwable {
        short short0 = NumberUtils.max((short) (byte) (-119), (short) (byte) (-119), (short) (byte) (-119));
        assertNotNull(short0);
    }

    @Test(timeout = 4000)
    public void test04646() throws Throwable {
        long long0 = NumberUtils.max((-193L), (-193L), 0L);
        assertEquals(0, long0);
    }

    @Test(timeout = 4000)
    public void test04747() throws Throwable {
        int int0 = NumberUtils.max((int) (byte) (-69), 0, 0);
        assertEquals(0, int0);
    }

    @Test(timeout = 4000)
    public void test04848() throws Throwable {
        int int0 = NumberUtils.max((-2354), (-2354), (-1684));
        assertEquals(0, int0);
    }

    @Test(timeout = 4000)
    public void test04949() throws Throwable {
        float float0 = NumberUtils.max(0.0F, 0.0F, (-3004.806F));
    }

    @Test(timeout = 4000)
    public void test05050() throws Throwable {
        float float0 = NumberUtils.max((-1.0F), (-1.0F), (-1.0F));
    }

    @Test(timeout = 4000)
    public void test05151() throws Throwable {
        double double0 = NumberUtils.max(3834.953739427214, 0.0, 710.875217);
    }

    @Test(timeout = 4000)
    public void test05252() throws Throwable {
        double double0 = NumberUtils.max((double) (-1483L), (-5807.7), (-5807.7));
    }

    @Test(timeout = 4000)
    public void test05353() throws Throwable {
        byte byte0 = NumberUtils.max((byte) 47, (byte) 0, (byte) 0);
    }

    @Test(timeout = 4000)
    public void test05454() throws Throwable {
        byte byte0 = NumberUtils.max((byte) (-38), (byte) (-8), (byte) (-8));
        assertNotNull(byte0);
    }

    @Test(timeout = 4000)
    public void test05555() throws Throwable {
        Number number0 = NumberUtils.createNumber("-0");
    }

    @Test(timeout = 4000)
    public void test05656() throws Throwable {
        Long long0 = NumberUtils.createLong("-0");
    }

    @Test(timeout = 4000)
    public void test05757() throws Throwable {
        Integer integer0 = NumberUtils.createInteger("0");
    }

    @Test(timeout = 4000)
    public void test05858() throws Throwable {
        Integer integer0 = NumberUtils.createInteger("8");
    }

    @Test(timeout = 4000)
    public void test05959() throws Throwable {
        Float float0 = NumberUtils.createFloat("-0");
    }

    @Test(timeout = 4000)
    public void test06060() throws Throwable {
        Float float0 = NumberUtils.createFloat("6F");
    }

    @Test(timeout = 4000)
    public void test06161() throws Throwable {
        Double double0 = NumberUtils.createDouble("-0");
    }

    @Test(timeout = 4000)
    public void test06262() throws Throwable {
        Double double0 = NumberUtils.createDouble("6F");
    }

    @Test(timeout = 4000)
    public void test06363() throws Throwable {
        BigInteger bigInteger0 = NumberUtils.createBigInteger("-0");
        bigInteger0.shortValue();
    }

    @Test(timeout = 4000)
    public void test06464() throws Throwable {
        BigDecimal bigDecimal0 = NumberUtils.createBigDecimal("0");
        bigDecimal0.shortValue();
    }

    @Test(timeout = 4000)
    public void test06565() throws Throwable {
        BigDecimal bigDecimal0 = NumberUtils.createBigDecimal("3");
        bigDecimal0.byteValue();
    }

    @Test(timeout = 4000)
    public void test06666() throws Throwable {
        BigDecimal bigDecimal0 = NumberUtils.createBigDecimal("-04");
        bigDecimal0.byteValue();
    }

    @Test(timeout = 4000)
    public void test06767() throws Throwable {
        NumberUtils.createLong("");
    }

    @Test(timeout = 4000)
    public void test06868() throws Throwable {
        boolean boolean0 = NumberUtils.isDigits("0xmS:p0sP");
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test06969() throws Throwable {
        NumberUtils.createBigDecimal("1m`JpWR");
    }

    @Test(timeout = 4000)
    public void test07070() throws Throwable {
        NumberUtils.createBigInteger("d?8EUKCpjg1~95Bn");
    }

    @Test(timeout = 4000)
    public void test07171() throws Throwable {
        Long long0 = NumberUtils.createLong("0x6F");
    }

    @Test(timeout = 4000)
    public void test07272() throws Throwable {
        NumberUtils.createInteger("");
    }

    @Test(timeout = 4000)
    public void test07373() throws Throwable {
        NumberUtils.createDouble(";21W$eQ>{");
    }

    @Test(timeout = 4000)
    public void test07474() throws Throwable {
        NumberUtils.createFloat("e4Q*");
    }

    @Test(timeout = 4000)
    public void test07575() throws Throwable {
        short short0 = NumberUtils.toShort((String) null, (short) 2022);
    }

    @Test(timeout = 4000)
    public void test07676() throws Throwable {
        short short0 = NumberUtils.toShort("aW+na=fT}2ru86SS1", (short) (byte) (-114));
    }

    @Test(timeout = 4000)
    public void test07777() throws Throwable {
        byte byte0 = NumberUtils.toByte("", (byte) 0);
    }

    @Test(timeout = 4000)
    public void test07878() throws Throwable {
        byte byte0 = NumberUtils.toByte((String) null, (byte) 81);
    }

    @Test(timeout = 4000)
    public void test07979() throws Throwable {
        double double0 = NumberUtils.toDouble((String) null, (double) (short) 0);
    }

    @Test(timeout = 4000)
    public void test08080() throws Throwable {
        double double0 = NumberUtils.toDouble("|=cMEo\n`&r_a/aeEs`", 1.0);
    }

    @Test(timeout = 4000)
    public void test08181() throws Throwable {
        float float0 = NumberUtils.toFloat((String) null, 354.7F);
    }

    @Test(timeout = 4000)
    public void test08282() throws Throwable {
        float float0 = NumberUtils.toFloat("", (float) (byte) 0);
    }

    @Test(timeout = 4000)
    public void test08383() throws Throwable {
        long long0 = NumberUtils.toLong((String) null, 138L);
    }

    @Test(timeout = 4000)
    public void test08484() throws Throwable {
        long long0 = NumberUtils.toLong("kxuF%msTz5kUs", 0L);
    }

    @Test(timeout = 4000)
    public void test08585() throws Throwable {
        int int0 = NumberUtils.toInt((String) null, 2);
        assertEquals(0, int0);
    }

    @Test(timeout = 4000)
    public void test08686() throws Throwable {
        boolean boolean0 = NumberUtils.isNumber("-e");
    }

    @Test(timeout = 4000)
    public void test08787() throws Throwable {
        boolean boolean0 = NumberUtils.isNumber("F");
    }

    @Test(timeout = 4000)
    public void test08888() throws Throwable {
        boolean boolean0 = NumberUtils.isNumber("--0x");
    }

    @Test(timeout = 4000)
    public void test08989() throws Throwable {
        boolean boolean0 = NumberUtils.isNumber("+yCv[z%9^Cv");
    }

    @Test(timeout = 4000)
    public void test09090() throws Throwable {
        boolean boolean0 = NumberUtils.isNumber("?6$O!cK K`1]xbsQ8");
    }

    @Test(timeout = 4000)
    public void test09191() throws Throwable {
        boolean boolean0 = NumberUtils.isNumber("eYQ50O^e+=3pn_3O");
    }

    @Test(timeout = 4000)
    public void test09292() throws Throwable {
        boolean boolean0 = NumberUtils.isNumber("-");
    }

    @Test(timeout = 4000)
    public void test09393() throws Throwable {
        boolean boolean0 = NumberUtils.isNumber("0xAborting to protect against StackOverflowError - output of one loop is the input of another");
    }

    @Test(timeout = 4000)
    public void test09494() throws Throwable {
        boolean boolean0 = NumberUtils.isNumber("0x-0x");
    }

    @Test(timeout = 4000)
    public void test09595() throws Throwable {
        boolean boolean0 = NumberUtils.isNumber("-0x");
    }

    @Test(timeout = 4000)
    public void test09696() throws Throwable {
        boolean boolean0 = NumberUtils.isNumber("-04");
    }

    @Test(timeout = 4000)
    public void test09797() throws Throwable {
        boolean boolean0 = NumberUtils.isNumber("0x0x");
    }

    @Test(timeout = 4000)
    public void test09898() throws Throwable {
        boolean boolean0 = NumberUtils.isNumber("--");
    }

    @Test(timeout = 4000)
    public void test09999() throws Throwable {
        boolean boolean0 = NumberUtils.isNumber((String) null);
    }

    @Test(timeout = 4000)
    public void test100100() throws Throwable {
        boolean boolean0 = NumberUtils.isNumber("...");
    }

    @Test(timeout = 4000)
    public void test101101() throws Throwable {
        boolean boolean0 = NumberUtils.isDigits("0");
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test102102() throws Throwable {
        boolean boolean0 = NumberUtils.isDigits("");
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test103103() throws Throwable {
        byte byte0 = NumberUtils.max((byte) (-87), (byte) (-35), (byte) 0);
        assertNotNull(byte0);
    }

    @Test(timeout = 4000)
    public void test104104() throws Throwable {
        byte byte0 = NumberUtils.max((byte) 0, (byte) 0, (byte) (-46));
        assertNotNull(byte0);
    }

    @Test(timeout = 4000)
    public void test105105() throws Throwable {
        short short0 = NumberUtils.max((short) 0, (short) 1, (short) 17);
        assertNotNull(short0);
    }

    @Test(timeout = 4000)
    public void test106106() throws Throwable {
        short short0 = NumberUtils.max((short) 2, (short) 2022, (short) 0);
    }

    @Test(timeout = 4000)
    public void test107107() throws Throwable {
        short short0 = NumberUtils.max((short) (byte) 0, (short) (-267), (short) 0);
    }

    @Test(timeout = 4000)
    public void test108108() throws Throwable {
        int int0 = NumberUtils.max((-682), 70, (-4289));
        assertEquals(0, int0);
    }

    @Test(timeout = 4000)
    public void test109109() throws Throwable {
        int int0 = NumberUtils.max((int) (short) 0, (int) (short) (-75), (int) (short) 1);
        assertEquals(0, int0);
    }

    @Test(timeout = 4000)
    public void test110110() throws Throwable {
        long long0 = NumberUtils.max((-614L), 0L, 1208L);
    }

    @Test(timeout = 4000)
    public void test111111() throws Throwable {
        long long0 = NumberUtils.max((long) 45, (long) (byte) 0, (long) (short) 0);
        assertEquals(1, long0);
    }

    @Test(timeout = 4000)
    public void test112112() throws Throwable {
        byte byte0 = NumberUtils.min((byte) 86, (byte) 1, (byte) (-107));
        assertNotNull(byte0);
    }

    @Test(timeout = 4000)
    public void test113113() throws Throwable {
        byte byte0 = NumberUtils.min((byte) 0, (byte) 36, (byte) 0);
    }

    @Test(timeout = 4000)
    public void test114114() throws Throwable {
        short short0 = NumberUtils.min((short) 2243, (short) 2243, (short) (byte) 0);
    }

    @Test(timeout = 4000)
    public void test115115() throws Throwable {
        short short0 = NumberUtils.min((short) 977, (short) (-925), (short) 977);
    }

    @Test(timeout = 4000)
    public void test116116() throws Throwable {
        short short0 = NumberUtils.min((short) (-1), (short) (-1), (short) (-1));
        assertNotNull(short0);
    }

    @Test(timeout = 4000)
    public void test117117() throws Throwable {
        int int0 = NumberUtils.min(100, 76, 43);
        assertEquals(0, int0);
    }

    @Test(timeout = 4000)
    public void test118118() throws Throwable {
        int int0 = NumberUtils.min((-1), (-2761), (-1));
        assertEquals(0, int0);
    }

    @Test(timeout = 4000)
    public void test119119() throws Throwable {
        int int0 = NumberUtils.min((-1), 1833, (-1));
        assertEquals(0, int0);
    }

    @Test(timeout = 4000)
    public void test120120() throws Throwable {
        long long0 = NumberUtils.min((long) (byte) 36, (long) (byte) 0, (long) (byte) 0);
        assertEquals(1, long0);
    }

    @Test(timeout = 4000)
    public void test121121() throws Throwable {
        NumberUtils numberUtils0 = new NumberUtils();
        float[] floatArray0 = new float[7];
        floatArray0[0] = (float) (byte) numberUtils0.BYTE_MINUS_ONE;
        float float0 = NumberUtils.max(floatArray0);
    }

    @Test(timeout = 4000)
    public void test122122() throws Throwable {
        float[] floatArray0 = new float[6];
        floatArray0[3] = Float.NaN;
        float float0 = NumberUtils.max(floatArray0);
    }

    @Test(timeout = 4000)
    public void test123123() throws Throwable {
        float[] floatArray0 = new float[0];
        NumberUtils.max(floatArray0);
    }

    @Test(timeout = 4000)
    public void test124124() throws Throwable {
        NumberUtils.max((float[]) null);
    }

    @Test(timeout = 4000)
    public void test125125() throws Throwable {
        double[] doubleArray0 = new double[5];
        doubleArray0[0] = (double) (-1L);
        double double0 = NumberUtils.max(doubleArray0);
    }

    @Test(timeout = 4000)
    public void test126126() throws Throwable {
        double[] doubleArray0 = new double[9];
        doubleArray0[4] = (double) Float.NaN;
        double double0 = NumberUtils.max(doubleArray0);
    }

    @Test(timeout = 4000)
    public void test127127() throws Throwable {
        double[] doubleArray0 = new double[0];
        NumberUtils.max(doubleArray0);
    }

    @Test(timeout = 4000)
    public void test128128() throws Throwable {
        NumberUtils.max((double[]) null);
    }

    @Test(timeout = 4000)
    public void test129129() throws Throwable {
        byte[] byteArray0 = new byte[4];
        byteArray0[0] = (byte) (-99);
        byte byte0 = NumberUtils.max(byteArray0);
    }

    @Test(timeout = 4000)
    public void test130130() throws Throwable {
        byte[] byteArray0 = new byte[0];
        NumberUtils.max(byteArray0);
    }

    @Test(timeout = 4000)
    public void test131131() throws Throwable {
        NumberUtils.max((byte[]) null);
    }

    @Test(timeout = 4000)
    public void test132132() throws Throwable {
        short[] shortArray0 = new short[9];
        shortArray0[0] = (short) (-1662);
        short short0 = NumberUtils.max(shortArray0);
    }

    @Test(timeout = 4000)
    public void test133133() throws Throwable {
        short[] shortArray0 = new short[0];
        NumberUtils.max(shortArray0);
    }

    @Test(timeout = 4000)
    public void test134134() throws Throwable {
        NumberUtils.max((short[]) null);
    }

    @Test(timeout = 4000)
    public void test135135() throws Throwable {
        int[] intArray0 = new int[5];
        intArray0[0] = (int) (short) (-525);
        int int0 = NumberUtils.max(intArray0);
        assertEquals(1, int0);
    }

    @Test(timeout = 4000)
    public void test136136() throws Throwable {
        int[] intArray0 = new int[0];
        NumberUtils.max(intArray0);
    }

    @Test(timeout = 4000)
    public void test137137() throws Throwable {
        NumberUtils.max((int[]) null);
    }

    @Test(timeout = 4000)
    public void test138138() throws Throwable {
        long[] longArray0 = new long[8];
        longArray0[0] = (-1082L);
        long long0 = NumberUtils.max(longArray0);
        assertEquals(1, long0);
    }

    @Test(timeout = 4000)
    public void test139139() throws Throwable {
        long[] longArray0 = new long[0];
        NumberUtils.max(longArray0);
    }

    @Test(timeout = 4000)
    public void test140140() throws Throwable {
        NumberUtils.max((long[]) null);
    }

    @Test(timeout = 4000)
    public void test141141() throws Throwable {
        float[] floatArray0 = new float[6];
        floatArray0[0] = 1973.261F;
        float float0 = NumberUtils.min(floatArray0);
    }

    @Test(timeout = 4000)
    public void test142142() throws Throwable {
        float[] floatArray0 = new float[6];
        floatArray0[3] = Float.NaN;
        float float0 = NumberUtils.min(floatArray0);
    }

    @Test(timeout = 4000)
    public void test143143() throws Throwable {
        float[] floatArray0 = new float[0];
        NumberUtils.min(floatArray0);
    }

    @Test(timeout = 4000)
    public void test144144() throws Throwable {
        NumberUtils.min((float[]) null);
    }

    @Test(timeout = 4000)
    public void test145145() throws Throwable {
        double[] doubleArray0 = new double[8];
        doubleArray0[2] = (-230.3166676145615);
        double double0 = NumberUtils.min(doubleArray0);
    }

    @Test(timeout = 4000)
    public void test146146() throws Throwable {
        double[] doubleArray0 = new double[7];
        doubleArray0[2] = Double.NaN;
        double double0 = NumberUtils.min(doubleArray0);
    }

    @Test(timeout = 4000)
    public void test147147() throws Throwable {
        double[] doubleArray0 = new double[0];
        NumberUtils.min(doubleArray0);
    }

    @Test(timeout = 4000)
    public void test148148() throws Throwable {
        NumberUtils.min((double[]) null);
    }

    @Test(timeout = 4000)
    public void test149149() throws Throwable {
        double[] doubleArray0 = new double[8];
        double double0 = NumberUtils.min(doubleArray0);
    }

    @Test(timeout = 4000)
    public void test150150() throws Throwable {
        byte[] byteArray0 = new byte[7];
        byteArray0[2] = (byte) (-17);
        byte byte0 = NumberUtils.min(byteArray0);
        assertEquals(byteArray0, byte0);
    }

    @Test(timeout = 4000)
    public void test151151() throws Throwable {
        byte[] byteArray0 = new byte[0];
        NumberUtils.min(byteArray0);
    }

    @Test(timeout = 4000)
    public void test152152() throws Throwable {
        NumberUtils.min((byte[]) null);
    }

    @Test(timeout = 4000)
    public void test153153() throws Throwable {
        byte[] byteArray0 = new byte[5];
        byte byte0 = NumberUtils.min(byteArray0);
        assertEquals(byteArray0, byte0);
    }

    @Test(timeout = 4000)
    public void test154154() throws Throwable {
        short[] shortArray0 = new short[0];
        NumberUtils.min(shortArray0);
    }

    @Test(timeout = 4000)
    public void test155155() throws Throwable {
        NumberUtils.min((short[]) null);
    }

    @Test(timeout = 4000)
    public void test156156() throws Throwable {
        short[] shortArray0 = new short[4];
        short short0 = NumberUtils.min(shortArray0);
    }

    @Test(timeout = 4000)
    public void test157157() throws Throwable {
        int[] intArray0 = new int[2];
        intArray0[0] = 97;
        int int0 = NumberUtils.min(intArray0);
        assertEquals(1, int0);
    }

    @Test(timeout = 4000)
    public void test158158() throws Throwable {
        int[] intArray0 = new int[0];
        NumberUtils.min(intArray0);
    }

    @Test(timeout = 4000)
    public void test159159() throws Throwable {
        NumberUtils.min((int[]) null);
    }

    @Test(timeout = 4000)
    public void test160160() throws Throwable {
        long[] longArray0 = new long[3];
        longArray0[0] = 1L;
        long long0 = NumberUtils.min(longArray0);
        assertEquals(1, long0);
    }

    @Test(timeout = 4000)
    public void test161161() throws Throwable {
        long[] longArray0 = new long[0];
        NumberUtils.min(longArray0);
    }

    @Test(timeout = 4000)
    public void test162162() throws Throwable {
        NumberUtils.min((long[]) null);
    }

    @Test(timeout = 4000)
    public void test163163() throws Throwable {
        NumberUtils.createBigDecimal("");
    }

    @Test(timeout = 4000)
    public void test164164() throws Throwable {
        BigDecimal bigDecimal0 = NumberUtils.createBigDecimal((String) null);
    }

    @Test(timeout = 4000)
    public void test165165() throws Throwable {
        BigInteger bigInteger0 = NumberUtils.createBigInteger((String) null);
        assertNotNull(bigInteger0);
    }

    @Test(timeout = 4000)
    public void test166166() throws Throwable {
        Long long0 = NumberUtils.createLong((String) null);
    }

    @Test(timeout = 4000)
    public void test167167() throws Throwable {
        Integer integer0 = NumberUtils.createInteger((String) null);
    }

    @Test(timeout = 4000)
    public void test168168() throws Throwable {
        Double double0 = NumberUtils.createDouble((String) null);
    }

    @Test(timeout = 4000)
    public void test169169() throws Throwable {
        Float float0 = NumberUtils.createFloat((String) null);
        assertNotNull(float0);
    }

    @Test(timeout = 4000)
    public void test170170() throws Throwable {
        NumberUtils.createNumber("fiqivQG~Ob6,y4");
    }

    @Test(timeout = 4000)
    public void test171171() throws Throwable {
        NumberUtils.createNumber(".,NH[{~R}rt|d");
    }

    @Test(timeout = 4000)
    public void test172172() throws Throwable {
        NumberUtils.createNumber(">IL^qZ0L");
    }

    @Test(timeout = 4000)
    public void test173173() throws Throwable {
        NumberUtils.createNumber(".");
    }

    @Test(timeout = 4000)
    public void test174174() throws Throwable {
        NumberUtils.createNumber("heI?rG0");
    }

    @Test(timeout = 4000)
    public void test175175() throws Throwable {
        NumberUtils.createNumber("Strings must not be null");
    }

    @Test(timeout = 4000)
    public void test176176() throws Throwable {
        NumberUtils.createNumber("org.apache.commons.lang3.math.NumberUtils");
    }

    @Test(timeout = 4000)
    public void test177177() throws Throwable {
        NumberUtils.createNumber("Array cannot be empty.");
    }

    @Test(timeout = 4000)
    public void test178178() throws Throwable {
        Number number0 = NumberUtils.createNumber("6F");
    }

    @Test(timeout = 4000)
    public void test179179() throws Throwable {
        NumberUtils.createNumber("-0XAborting to protect against StackOverflowError - output of one loop is the input of another");
    }

    @Test(timeout = 4000)
    public void test180180() throws Throwable {
        NumberUtils.createNumber("-0X>IL^qZ0L");
    }

    @Test(timeout = 4000)
    public void test181181() throws Throwable {
        NumberUtils.createNumber("0X#c8c");
    }

    @Test(timeout = 4000)
    public void test182182() throws Throwable {
        NumberUtils.createNumber("-0x");
    }

    @Test(timeout = 4000)
    public void test183183() throws Throwable {
        NumberUtils.createNumber("0x");
    }

    @Test(timeout = 4000)
    public void test184184() throws Throwable {
        NumberUtils.createNumber("");
    }

    @Test(timeout = 4000)
    public void test185185() throws Throwable {
        Number number0 = NumberUtils.createNumber((String) null);
    }

    @Test(timeout = 4000)
    public void test186186() throws Throwable {
        Number number0 = NumberUtils.createNumber("--");
    }

    @Test(timeout = 4000)
    public void test187187() throws Throwable {
        short short0 = NumberUtils.toShort((String) null);
    }

    @Test(timeout = 4000)
    public void test188188() throws Throwable {
        byte byte0 = NumberUtils.toByte((String) null);
        assertNotNull(byte0);
    }

    @Test(timeout = 4000)
    public void test189189() throws Throwable {
        double double0 = NumberUtils.toDouble((String) null);
    }

    @Test(timeout = 4000)
    public void test190190() throws Throwable {
        float float0 = NumberUtils.toFloat(":sjK%[DT@.6XRZ=");
    }

    @Test(timeout = 4000)
    public void test191191() throws Throwable {
        long long0 = NumberUtils.toLong((String) null);
    }

    @Test(timeout = 4000)
    public void test192192() throws Throwable {
        int int0 = NumberUtils.toInt("--");
        assertEquals(0, int0);
    }

    @Test(timeout = 4000)
    public void test193193() throws Throwable {
        double double0 = NumberUtils.max((-84.9169040138), (double) (byte) 0, (double) (byte) 0);
    }

    @Test(timeout = 4000)
    public void test194194() throws Throwable {
        NumberUtils numberUtils0 = new NumberUtils();
        short[] shortArray0 = new short[4];
        shortArray0[2] = (short) numberUtils0.SHORT_MINUS_ONE;
        short short0 = NumberUtils.min(shortArray0);
        assertEquals(shortArray0, short0);
    }

    @Test(timeout = 4000)
    public void test195195() throws Throwable {
        double double0 = NumberUtils.min(0.0, 0.0, 1017.3642232835962);
    }

    @Test(timeout = 4000)
    public void test196196() throws Throwable {
        float float0 = NumberUtils.min(0.0F, 0.0F, (float) 0L);
    }

    @Test(timeout = 4000)
    public void test197197() throws Throwable {
        float float0 = NumberUtils.max(0.0F, 1.0F, 0.0F);
    }
}
