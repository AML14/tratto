/*
 * This file was automatically generated by EvoSuite
 * Mon Oct 16 21:36:04 GMT 2023
 */
package org.apache.commons.math.util;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import org.apache.commons.math.util.MathUtils;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class MathUtils_ESTest extends MathUtils_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test0000() throws Throwable {
        float float0 = MathUtils.round(Float.NaN, 0, (int) (byte) 1);
    }

    @Test(timeout = 4000)
    public void test0011() throws Throwable {
        double double0 = MathUtils.scalb((-1.0), (-2590));
    }

    @Test(timeout = 4000)
    public void test0022() throws Throwable {
        double double0 = MathUtils.nextAfter(4.9E-324, 4.9E-324);
    }

    @Test(timeout = 4000)
    public void test0033() throws Throwable {
        double double0 = MathUtils.nextAfter(0, 0);
    }

    @Test(timeout = 4000)
    public void test0044() throws Throwable {
        int int0 = MathUtils.lcm(471, 471);
    }

    @Test(timeout = 4000)
    public void test0055() throws Throwable {
        short short0 = MathUtils.indicator((short) 31);
        assertNotNull(short0);
    }

    @Test(timeout = 4000)
    public void test0066() throws Throwable {
        long long0 = MathUtils.indicator(0L);
        assertEquals(0, long0);
    }

    @Test(timeout = 4000)
    public void test0077() throws Throwable {
        int int0 = MathUtils.indicator(4036);
        assertEquals(0, int0);
    }

    @Test(timeout = 4000)
    public void test0088() throws Throwable {
        double double0 = MathUtils.indicator((double) (byte) 0);
    }

    @Test(timeout = 4000)
    public void test0099() throws Throwable {
        byte byte0 = MathUtils.indicator((byte) 98);
        assertNotNull(byte0);
    }

    @Test(timeout = 4000)
    public void test01010() throws Throwable {
        int int0 = MathUtils.gcd(0, (-1));
    }

    @Test(timeout = 4000)
    public void test01111() throws Throwable {
        double double0 = MathUtils.factorialDouble(21);
    }

    @Test(timeout = 4000)
    public void test01212() throws Throwable {
        double double0 = MathUtils.factorialDouble((byte) 0);
    }

    @Test(timeout = 4000)
    public void test01313() throws Throwable {
        long long0 = MathUtils.factorial(20);
    }

    @Test(timeout = 4000)
    public void test01414() throws Throwable {
        double[] doubleArray0 = new double[10];
        double[] doubleArray1 = new double[4];
        doubleArray1.equals((Object) doubleArray0);
    }

    @Test(timeout = 4000)
    public void test01415() throws Throwable {
        double[] doubleArray0 = new double[10];
        double[] doubleArray1 = new double[4];
        boolean boolean0 = MathUtils.equals(doubleArray0, doubleArray1);
    }

    @Test(timeout = 4000)
    public void test01417() throws Throwable {
        double[] doubleArray0 = new double[10];
        double[] doubleArray1 = new double[4];
        boolean boolean0 = MathUtils.equals(doubleArray0, doubleArray1);
        doubleArray0.equals((Object) doubleArray1);
    }

    @Test(timeout = 4000)
    public void test01418() throws Throwable {
        double[] doubleArray0 = new double[10];
        double[] doubleArray1 = new double[4];
        boolean boolean0 = MathUtils.equals(doubleArray0, doubleArray1);
        doubleArray1.equals((Object) doubleArray0);
    }

    @Test(timeout = 4000)
    public void test01524() throws Throwable {
        boolean boolean0 = MathUtils.equals(0.5, (-4.9E-324), 16.0);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test01625() throws Throwable {
        boolean boolean0 = MathUtils.equals((double) 0, (double) 1, 1.0);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test01726() throws Throwable {
        double double0 = MathUtils.binomialCoefficientDouble(67, (-2573));
    }

    @Test(timeout = 4000)
    public void test01827() throws Throwable {
        double double0 = MathUtils.binomialCoefficientDouble(0, 0);
    }

    @Test(timeout = 4000)
    public void test01928() throws Throwable {
        double double0 = MathUtils.binomialCoefficientLog(37, 19);
    }

    @Test(timeout = 4000)
    public void test02029() throws Throwable {
        long long0 = MathUtils.binomialCoefficient(0, 0);
    }

    @Test(timeout = 4000)
    public void test02130() throws Throwable {
        long long0 = MathUtils.subAndCheck(0L, 2432902008176640000L);
        assertEquals(0, long0);
    }

    @Test(timeout = 4000)
    public void test02231() throws Throwable {
        long long0 = MathUtils.subAndCheck(0L, (-2632L));
        assertEquals(1, long0);
    }

    @Test(timeout = 4000)
    public void test02332() throws Throwable {
        long long0 = MathUtils.subAndCheck((long) 471, 758L);
    }

    @Test(timeout = 4000)
    public void test02433() throws Throwable {
        int int0 = MathUtils.subAndCheck(0, 0);
    }

    @Test(timeout = 4000)
    public void test02534() throws Throwable {
        int int0 = MathUtils.subAndCheck(298, 0);
    }

    @Test(timeout = 4000)
    public void test02635() throws Throwable {
        double double0 = MathUtils.sinh(1.0);
    }

    @Test(timeout = 4000)
    public void test02736() throws Throwable {
        double double0 = MathUtils.sinh((-3341.17189361));
    }

    @Test(timeout = 4000)
    public void test02837() throws Throwable {
        float float0 = MathUtils.round((-3058.408F), 5, 5);
    }

    @Test(timeout = 4000)
    public void test02938() throws Throwable {
        float float0 = MathUtils.round((-3058.408F), 1);
    }

    @Test(timeout = 4000)
    public void test03039() throws Throwable {
        double double0 = MathUtils.round(0.0, 1, (int) (byte) 0);
    }

    @Test(timeout = 4000)
    public void test03140() throws Throwable {
        double double0 = MathUtils.round(1772.907094093, 31, 5);
    }

    @Test(timeout = 4000)
    public void test03241() throws Throwable {
        double double0 = MathUtils.round(0.0, 67);
    }

    @Test(timeout = 4000)
    public void test03342() throws Throwable {
        double double0 = MathUtils.round((-1780.47166578776), (int) (byte) 0);
    }

    @Test(timeout = 4000)
    public void test03443() throws Throwable {
        double double0 = MathUtils.normalizeAngle(0.0, (byte) 1);
    }

    @Test(timeout = 4000)
    public void test03544() throws Throwable {
        double double0 = MathUtils.normalizeAngle((short) 1, 1511);
    }

    @Test(timeout = 4000)
    public void test03645() throws Throwable {
        double double0 = MathUtils.nextAfter((-4.9E-324), 1375.0);
    }

    @Test(timeout = 4000)
    public void test03746() throws Throwable {
        int int0 = MathUtils.mulAndCheck(0, (-1656));
    }

    @Test(timeout = 4000)
    public void test03847() throws Throwable {
        int int0 = MathUtils.mulAndCheck((int) (short) 10, 20);
    }

    @Test(timeout = 4000)
    public void test03948() throws Throwable {
        double double0 = MathUtils.log(0.0, 758L);
    }

    @Test(timeout = 4000)
    public void test04049() throws Throwable {
        double double0 = MathUtils.log(39916800L, 1183.3316670651523);
    }

    @Test(timeout = 4000)
    public void test04150() throws Throwable {
        double double0 = MathUtils.log(1L, 0.0);
    }

    @Test(timeout = 4000)
    public void test04251() throws Throwable {
        int int0 = MathUtils.hash((double[]) null);
        assertEquals(0, int0);
    }

    @Test(timeout = 4000)
    public void test04352() throws Throwable {
        double[] doubleArray0 = new double[1];
        doubleArray0[0] = 963.140565;
        int int0 = MathUtils.hash(doubleArray0);
    }

    @Test(timeout = 4000)
    public void test04455() throws Throwable {
        int int0 = MathUtils.hash(0.0);
    }

    @Test(timeout = 4000)
    public void test04556() throws Throwable {
        int int0 = MathUtils.hash((-1534.27614016));
        assertEquals(0, int0);
    }

    @Test(timeout = 4000)
    public void test04657() throws Throwable {
        double double0 = MathUtils.cosh(0);
    }

    @Test(timeout = 4000)
    public void test04758() throws Throwable {
        long long0 = MathUtils.addAndCheck(0L, 0L);
        assertEquals(1, long0);
    }

    @Test(timeout = 4000)
    public void test04859() throws Throwable {
        int int0 = MathUtils.addAndCheck(0, 0);
    }

    @Test(timeout = 4000)
    public void test04960() throws Throwable {
        int int0 = MathUtils.addAndCheck(964, (-1774));
    }

    @Test(timeout = 4000)
    public void test05061() throws Throwable {
        MathUtils.round((double) 21, 0, 1072);
    }

    @Test(timeout = 4000)
    public void test05162() throws Throwable {
        MathUtils.round((-1881.0), (-2144658010), 1);
    }

    @Test(timeout = 4000)
    public void test05263() throws Throwable {
        MathUtils.lcm((-277), (-1074790400));
    }

    @Test(timeout = 4000)
    public void test05364() throws Throwable {
        // Undeclared exception!
        MathUtils.factorialLog(21316);
    }

    @Test(timeout = 4000)
    public void test05465() throws Throwable {
        double double0 = MathUtils.nextAfter(0, (-1032.258982));
    }

    @Test(timeout = 4000)
    public void test05566() throws Throwable {
        double double0 = MathUtils.nextAfter(0L, 10.0);
    }

    @Test(timeout = 4000)
    public void test05667() throws Throwable {
        double double0 = MathUtils.nextAfter(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
    }

    @Test(timeout = 4000)
    public void test05768() throws Throwable {
        double double0 = MathUtils.nextAfter(Double.NaN, 0.0);
    }

    @Test(timeout = 4000)
    public void test05869() throws Throwable {
        MathUtils.mulAndCheck(6402373705728000L, 6402373705728000L);
    }

    @Test(timeout = 4000)
    public void test05970() throws Throwable {
        long long0 = MathUtils.mulAndCheck((long) (short) 1, (long) (byte) 1);
    }

    @Test(timeout = 4000)
    public void test06071() throws Throwable {
        long long0 = MathUtils.mulAndCheck((-1133L), (-1133L));
    }

    @Test(timeout = 4000)
    public void test06172() throws Throwable {
        int int0 = MathUtils.mulAndCheck(1, (-654));
    }

    @Test(timeout = 4000)
    public void test06273() throws Throwable {
        float float0 = MathUtils.indicator((-1247.0F));
    }

    @Test(timeout = 4000)
    public void test06374() throws Throwable {
        float float0 = MathUtils.indicator(26.5108F);
    }

    @Test(timeout = 4000)
    public void test06475() throws Throwable {
        int int0 = MathUtils.gcd(5, 31);
    }

    @Test(timeout = 4000)
    public void test06576() throws Throwable {
        int int0 = MathUtils.gcd((-3914), (-3914));
    }

    @Test(timeout = 4000)
    public void test06677() throws Throwable {
        double double0 = MathUtils.factorialLog(899);
    }

    @Test(timeout = 4000)
    public void test06778() throws Throwable {
        boolean boolean0 = MathUtils.equals(Double.NaN, Double.NaN);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test06879() throws Throwable {
        boolean boolean0 = MathUtils.equals((double) 0, (double) 0);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test06980() throws Throwable {
        double double0 = MathUtils.binomialCoefficientLog(1810, 16);
    }

    @Test(timeout = 4000)
    public void test07081() throws Throwable {
        double double0 = MathUtils.binomialCoefficientDouble(435, 17);
    }

    @Test(timeout = 4000)
    public void test07182() throws Throwable {
        long long0 = MathUtils.binomialCoefficient(1, (-1259));
    }

    @Test(timeout = 4000)
    public void test07283() throws Throwable {
        int int0 = MathUtils.subAndCheck(5, 31);
    }

    @Test(timeout = 4000)
    public void test07384() throws Throwable {
        MathUtils.subAndCheck((-1105), 2147483643);
    }

    @Test(timeout = 4000)
    public void test07485() throws Throwable {
        short short0 = MathUtils.sign((short) (-1));
    }

    @Test(timeout = 4000)
    public void test07586() throws Throwable {
        short short0 = MathUtils.sign((short) 0);
        assertNotNull(short0);
    }

    @Test(timeout = 4000)
    public void test07687() throws Throwable {
        short short0 = MathUtils.sign((short) 705);
    }

    @Test(timeout = 4000)
    public void test07788() throws Throwable {
        long long0 = MathUtils.sign((-2147483648L));
        assertEquals(1, long0);
    }

    @Test(timeout = 4000)
    public void test07889() throws Throwable {
        long long0 = MathUtils.sign((long) (byte) 0);
        assertEquals(1, long0);
    }

    @Test(timeout = 4000)
    public void test07990() throws Throwable {
        long long0 = MathUtils.sign((long) (byte) 1);
        assertEquals(1, long0);
    }

    @Test(timeout = 4000)
    public void test08091() throws Throwable {
        int int0 = MathUtils.sign((-2671));
        assertEquals(1, int0);
    }

    @Test(timeout = 4000)
    public void test08192() throws Throwable {
        int int0 = MathUtils.sign(0);
        assertEquals(0, int0);
    }

    @Test(timeout = 4000)
    public void test08293() throws Throwable {
        int int0 = MathUtils.sign(4402);
        assertEquals(1, int0);
    }

    @Test(timeout = 4000)
    public void test08394() throws Throwable {
        float float0 = MathUtils.sign(1474.0F);
    }

    @Test(timeout = 4000)
    public void test08495() throws Throwable {
        float float0 = MathUtils.sign(0.0F);
    }

    @Test(timeout = 4000)
    public void test08596() throws Throwable {
        float float0 = MathUtils.sign(Float.NaN);
    }

    @Test(timeout = 4000)
    public void test08697() throws Throwable {
        float float0 = MathUtils.sign((-3141.9634F));
    }

    @Test(timeout = 4000)
    public void test08798() throws Throwable {
        double double0 = MathUtils.sign((-630.973542217));
    }

    @Test(timeout = 4000)
    public void test08899() throws Throwable {
        double double0 = MathUtils.sign(0.0);
    }

    @Test(timeout = 4000)
    public void test089100() throws Throwable {
        double double0 = MathUtils.sign(Double.NaN);
    }

    @Test(timeout = 4000)
    public void test090101() throws Throwable {
        double double0 = MathUtils.sign(69.6366507738);
    }

    @Test(timeout = 4000)
    public void test091102() throws Throwable {
        byte byte0 = MathUtils.sign((byte) (-96));
    }

    @Test(timeout = 4000)
    public void test092103() throws Throwable {
        byte byte0 = MathUtils.sign((byte) 0);
        assertNotNull(byte0);
    }

    @Test(timeout = 4000)
    public void test093104() throws Throwable {
        byte byte0 = MathUtils.sign((byte) 17);
    }

    @Test(timeout = 4000)
    public void test094105() throws Throwable {
        float float0 = MathUtils.round(1317.8057F, (int) (byte) 0);
    }

    @Test(timeout = 4000)
    public void test095106() throws Throwable {
        MathUtils.round((-1245.479F), (-885), 5319);
    }

    @Test(timeout = 4000)
    public void test096107() throws Throwable {
        float float0 = MathUtils.round((float) (byte) 1, (-2101), 2);
    }

    @Test(timeout = 4000)
    public void test097108() throws Throwable {
        float float0 = MathUtils.round(1398.7898F, 0, 0);
    }

    @Test(timeout = 4000)
    public void test098109() throws Throwable {
        double double0 = MathUtils.round(Double.NEGATIVE_INFINITY, (-608), (-1377));
    }

    @Test(timeout = 4000)
    public void test099110() throws Throwable {
        double double0 = MathUtils.round(Double.NaN, 684, 0);
    }

    @Test(timeout = 4000)
    public void test100111() throws Throwable {
        double double0 = MathUtils.scalb(Double.NEGATIVE_INFINITY, 887);
    }

    @Test(timeout = 4000)
    public void test101112() throws Throwable {
        double double0 = MathUtils.scalb(Double.NaN, (-2087));
    }

    @Test(timeout = 4000)
    public void test102113() throws Throwable {
        double double0 = MathUtils.scalb(1714.3074792154, 1);
    }

    @Test(timeout = 4000)
    public void test103114() throws Throwable {
        double double0 = MathUtils.scalb(0, 2973);
    }

    @Test(timeout = 4000)
    public void test104115() throws Throwable {
        double double0 = MathUtils.nextAfter(1.0, (-201.6));
    }

    @Test(timeout = 4000)
    public void test105116() throws Throwable {
        float float0 = MathUtils.round((float) (byte) 0, 0, 5);
    }

    @Test(timeout = 4000)
    public void test106117() throws Throwable {
        float float0 = MathUtils.round(1474.0F, 3637, 5);
    }

    @Test(timeout = 4000)
    public void test107118() throws Throwable {
        float float0 = MathUtils.round(0.0F, 0);
    }

    @Test(timeout = 4000)
    public void test108119() throws Throwable {
        MathUtils.binomialCoefficient(2855, 205);
    }

    @Test(timeout = 4000)
    public void test109120() throws Throwable {
        long long0 = MathUtils.mulAndCheck((long) 0, (long) 0);
    }

    @Test(timeout = 4000)
    public void test110121() throws Throwable {
        long long0 = MathUtils.mulAndCheck((-2174L), 0L);
    }

    @Test(timeout = 4000)
    public void test111122() throws Throwable {
        MathUtils.mulAndCheck((-9218868437227405313L), (-465L));
    }

    @Test(timeout = 4000)
    public void test112123() throws Throwable {
        long long0 = MathUtils.mulAndCheck((-1L), (-785L));
    }

    @Test(timeout = 4000)
    public void test113124() throws Throwable {
        long long0 = MathUtils.mulAndCheck((-2403L), 1301L);
    }

    @Test(timeout = 4000)
    public void test114125() throws Throwable {
        MathUtils.mulAndCheck(1259, 2134043785);
    }

    @Test(timeout = 4000)
    public void test115126() throws Throwable {
        MathUtils.mulAndCheck(2092419964, (-1429));
    }

    @Test(timeout = 4000)
    public void test116127() throws Throwable {
        int int0 = MathUtils.lcm(15, 0);
    }

    @Test(timeout = 4000)
    public void test117128() throws Throwable {
        int int0 = MathUtils.lcm(0, 0);
    }

    @Test(timeout = 4000)
    public void test118129() throws Throwable {
        short short0 = MathUtils.indicator((short) 0);
        assertNotNull(short0);
    }

    @Test(timeout = 4000)
    public void test119130() throws Throwable {
        short short0 = MathUtils.indicator((short) (-1));
        assertNotNull(short0);
    }

    @Test(timeout = 4000)
    public void test120131() throws Throwable {
        long long0 = MathUtils.indicator(1L);
        assertEquals(1, long0);
    }

    @Test(timeout = 4000)
    public void test121132() throws Throwable {
        long long0 = MathUtils.indicator((-1L));
        assertEquals(0, long0);
    }

    @Test(timeout = 4000)
    public void test122133() throws Throwable {
        int int0 = MathUtils.indicator(0);
    }

    @Test(timeout = 4000)
    public void test123134() throws Throwable {
        int int0 = MathUtils.indicator((-880));
        assertEquals(0, int0);
    }

    @Test(timeout = 4000)
    public void test124135() throws Throwable {
        float float0 = MathUtils.round((float) (short) (-1), (-3852), 7);
    }

    @Test(timeout = 4000)
    public void test125136() throws Throwable {
        float float0 = MathUtils.indicator(Float.NaN);
    }

    @Test(timeout = 4000)
    public void test126137() throws Throwable {
        double double0 = MathUtils.indicator((double) (-1.0F));
    }

    @Test(timeout = 4000)
    public void test127138() throws Throwable {
        double double0 = MathUtils.indicator(Double.NaN);
    }

    @Test(timeout = 4000)
    public void test128139() throws Throwable {
        double double0 = MathUtils.indicator(4.9E-324);
    }

    @Test(timeout = 4000)
    public void test129140() throws Throwable {
        byte byte0 = MathUtils.indicator((byte) 0);
        assertNotNull(byte0);
    }

    @Test(timeout = 4000)
    public void test130141() throws Throwable {
        byte byte0 = MathUtils.indicator((byte) (-101));
        assertNotNull(byte0);
    }

    @Test(timeout = 4000)
    public void test131142() throws Throwable {
        int int0 = MathUtils.gcd(272, (-2049));
    }

    @Test(timeout = 4000)
    public void test132143() throws Throwable {
        int int0 = MathUtils.gcd(6, (byte) 0);
    }

    @Test(timeout = 4000)
    public void test133144() throws Throwable {
        int int0 = MathUtils.gcd(0, 0);
    }

    @Test(timeout = 4000)
    public void test134145() throws Throwable {
        double double0 = MathUtils.factorialLog(0);
    }

    @Test(timeout = 4000)
    public void test135146() throws Throwable {
        MathUtils.factorialLog((-2146904029));
    }

    @Test(timeout = 4000)
    public void test136147() throws Throwable {
        MathUtils.factorialDouble((-1253));
    }

    @Test(timeout = 4000)
    public void test137148() throws Throwable {
        double double0 = MathUtils.factorialDouble(668);
    }

    @Test(timeout = 4000)
    public void test138149() throws Throwable {
        MathUtils.factorial(2649);
    }

    @Test(timeout = 4000)
    public void test139150() throws Throwable {
        MathUtils.factorial((-4128));
    }

    @Test(timeout = 4000)
    public void test140151() throws Throwable {
        double[] doubleArray0 = new double[4];
        doubleArray0[0] = 3675.144998324021;
        double[] doubleArray1 = new double[4];
        boolean boolean0 = MathUtils.equals(doubleArray0, doubleArray1);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test141152() throws Throwable {
        double[] doubleArray0 = new double[4];
        double[] doubleArray1 = new double[6];
        boolean boolean0 = MathUtils.equals(doubleArray0, doubleArray1);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test142153() throws Throwable {
        boolean boolean0 = MathUtils.equals((double[]) null, (double[]) null);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test143154() throws Throwable {
        double[] doubleArray0 = new double[4];
        boolean boolean0 = MathUtils.equals(doubleArray0, (double[]) null);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test144155() throws Throwable {
        double[] doubleArray0 = new double[4];
        boolean boolean0 = MathUtils.equals((double[]) null, doubleArray0);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test145156() throws Throwable {
        boolean boolean0 = MathUtils.equals((double) 0, (double) (-1), 1.0);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test146157() throws Throwable {
        boolean boolean0 = MathUtils.equals((double) (-34), 1918.67918793, 1.3164036458569648E64);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test147158() throws Throwable {
        boolean boolean0 = MathUtils.equals(2753.5, 0.0, 1676.0);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test148159() throws Throwable {
        boolean boolean0 = MathUtils.equals((double) (short) 1, (double) (short) 14, (-4.9E-324));
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test149160() throws Throwable {
        boolean boolean0 = MathUtils.equals(1.0, (double) 1L, (double) (byte) 0);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test150161() throws Throwable {
        boolean boolean0 = MathUtils.equals(Double.NaN, (-3526.0));
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test151162() throws Throwable {
        double[] doubleArray0 = new double[6];
        doubleArray0[1] = (double) Float.NaN;
        boolean boolean0 = MathUtils.equals(doubleArray0, doubleArray0);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test152163() throws Throwable {
        double double0 = MathUtils.binomialCoefficientLog(347, 272);
    }

    @Test(timeout = 4000)
    public void test153164() throws Throwable {
        double double0 = MathUtils.binomialCoefficientLog(1030, 964);
    }

    @Test(timeout = 4000)
    public void test154165() throws Throwable {
        double double0 = MathUtils.binomialCoefficientLog(0, (-1));
    }

    @Test(timeout = 4000)
    public void test155166() throws Throwable {
        double double0 = MathUtils.binomialCoefficientLog((byte) 17, (byte) 1);
    }

    @Test(timeout = 4000)
    public void test156167() throws Throwable {
        double double0 = MathUtils.binomialCoefficientLog((short) 705, 0);
    }

    @Test(timeout = 4000)
    public void test157168() throws Throwable {
        double double0 = MathUtils.binomialCoefficientLog(0, (short) 0);
    }

    @Test(timeout = 4000)
    public void test158169() throws Throwable {
        MathUtils.binomialCoefficientLog((byte) (-1), (byte) (-1));
    }

    @Test(timeout = 4000)
    public void test159170() throws Throwable {
        MathUtils.binomialCoefficientLog((-3349), 2860);
    }

    @Test(timeout = 4000)
    public void test160171() throws Throwable {
        double double0 = MathUtils.binomialCoefficientDouble(1265, 719);
    }

    @Test(timeout = 4000)
    public void test161172() throws Throwable {
        double double0 = MathUtils.binomialCoefficientDouble(668, 1);
    }

    @Test(timeout = 4000)
    public void test162173() throws Throwable {
        double double0 = MathUtils.binomialCoefficientDouble(787, 0);
    }

    @Test(timeout = 4000)
    public void test163174() throws Throwable {
        MathUtils.binomialCoefficientDouble((-1403), (-1403));
    }

    @Test(timeout = 4000)
    public void test164175() throws Throwable {
        MathUtils.binomialCoefficientDouble(0, 14);
    }

    @Test(timeout = 4000)
    public void test165176() throws Throwable {
        double double0 = MathUtils.binomialCoefficientDouble((short) 62, 27);
    }

    @Test(timeout = 4000)
    public void test166177() throws Throwable {
        long long0 = MathUtils.binomialCoefficient(381, 9);
    }

    @Test(timeout = 4000)
    public void test167178() throws Throwable {
        long long0 = MathUtils.binomialCoefficient(19, 13);
    }

    @Test(timeout = 4000)
    public void test168179() throws Throwable {
        long long0 = MathUtils.binomialCoefficient((short) 14, 13);
    }

    @Test(timeout = 4000)
    public void test169180() throws Throwable {
        long long0 = MathUtils.binomialCoefficient((byte) 5, (byte) 1);
    }

    @Test(timeout = 4000)
    public void test170181() throws Throwable {
        MathUtils.binomialCoefficient((-1513), (-2087));
    }

    @Test(timeout = 4000)
    public void test171182() throws Throwable {
        MathUtils.binomialCoefficient((-1774), 933);
    }

    @Test(timeout = 4000)
    public void test172183() throws Throwable {
        long long0 = MathUtils.binomialCoefficient(3197, 0);
    }

    @Test(timeout = 4000)
    public void test173184() throws Throwable {
        long long0 = MathUtils.subAndCheck((long) 19, (long) 19);
    }

    @Test(timeout = 4000)
    public void test174185() throws Throwable {
        long long0 = MathUtils.addAndCheck((long) 721, (long) 1);
        assertEquals(1, long0);
    }

    @Test(timeout = 4000)
    public void test175186() throws Throwable {
        MathUtils.addAndCheck(Integer.MAX_VALUE, (int) (short) 1);
    }

    @Test(timeout = 4000)
    public void test176187() throws Throwable {
        int int0 = MathUtils.addAndCheck(0, 1);
    }

    @Test(timeout = 4000)
    public void test177188() throws Throwable {
        MathUtils.addAndCheck((-2147483646), (-2147483646));
    }

    @Test(timeout = 4000)
    public void test178189() throws Throwable {
        double double0 = MathUtils.round(1772.907094093, 721);
    }

    @Test(timeout = 4000)
    public void test179190() throws Throwable {
        double double0 = MathUtils.normalizeAngle((-5636.940604490026), 4.9E-324);
    }

    @Test(timeout = 4000)
    public void test180191() throws Throwable {
        double double0 = MathUtils.sinh(0.0);
    }

    @Test(timeout = 4000)
    public void test181192() throws Throwable {
        long long0 = MathUtils.addAndCheck((-2147483648L), (-1L));
        assertEquals(1, long0);
    }

    @Test(timeout = 4000)
    public void test182193() throws Throwable {
        double[] doubleArray0 = new double[5];
        int int0 = MathUtils.hash(doubleArray0);
        assertEquals(1, int0);
    }

    @Test(timeout = 4000)
    public void test183194() throws Throwable {
        int int0 = MathUtils.hash((-2139.93606027));
        assertEquals(1, int0);
    }
}
