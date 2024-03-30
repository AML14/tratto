/*
 * This file was automatically generated by EvoSuite
 * Mon Oct 16 04:37:01 GMT 2023
 */
package org.apache.commons.math3.fraction;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.fraction.BigFractionField;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class BigFraction_ESTest extends BigFraction_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test0480() throws Throwable {
        BigFraction bigFraction0 = new BigFraction((-717.224843262248));
        BigFraction bigFraction1 = bigFraction0.pow(1245);
        BigFraction bigFraction2 = bigFraction1.divide(3);
        bigFraction2.shortValue();
    }

    @Test(timeout = 4000)
    public void test0491() throws Throwable {
        BigFraction bigFraction0 = new BigFraction((-858));
        int int0 = bigFraction0.compareTo(bigFraction0);
    }

    @Test(timeout = 4000)
    public void test0492() throws Throwable {
        BigFraction bigFraction0 = new BigFraction((-858));
        int int0 = bigFraction0.compareTo(bigFraction0);
        bigFraction0.byteValue();
    }

    @Test(timeout = 4000)
    public void test0503() throws Throwable {
        BigFraction bigFraction0 = BigFraction.ZERO;
        BigFraction bigFraction1 = bigFraction0.ONE_FIFTH.pow((-608L));
        int int0 = bigFraction0.compareTo(bigFraction1);
        assertEquals(0, int0);
    }

    @Test(timeout = 4000)
    public void test0504() throws Throwable {
        BigFraction bigFraction0 = BigFraction.ZERO;
        BigFraction bigFraction1 = bigFraction0.ONE_FIFTH.pow((-608L));
        int int0 = bigFraction0.compareTo(bigFraction1);
        bigFraction1.shortValue();
    }

    @Test(timeout = 4000)
    public void test0515() throws Throwable {
        BigFraction bigFraction0 = BigFraction.ONE_FIFTH;
        BigDecimal bigDecimal0 = bigFraction0.TWO_QUARTERS.bigDecimalValue(0, 0);
        bigDecimal0.shortValue();
    }

    @Test(timeout = 4000)
    public void test0526() throws Throwable {
        BigFraction bigFraction0 = new BigFraction(1239);
        BigDecimal bigDecimal0 = bigFraction0.bigDecimalValue(1670, 0);
        bigDecimal0.byteValue();
    }

    @Test(timeout = 4000)
    public void test0537() throws Throwable {
        BigFraction bigFraction0 = BigFraction.ONE_FIFTH;
        BigDecimal bigDecimal0 = bigFraction0.bigDecimalValue(1);
        bigDecimal0.shortValue();
    }

    @Test(timeout = 4000)
    public void test0548() throws Throwable {
        BigFraction bigFraction0 = BigFraction.TWO_QUARTERS;
        BigDecimal bigDecimal0 = bigFraction0.bigDecimalValue(2);
        bigDecimal0.shortValue();
    }

    @Test(timeout = 4000)
    public void test0559() throws Throwable {
        BigFraction bigFraction0 = BigFraction.ONE;
        BigDecimal bigDecimal0 = bigFraction0.bigDecimalValue();
        bigDecimal0.shortValue();
    }

    @Test(timeout = 4000)
    public void test05610() throws Throwable {
        BigFraction bigFraction0 = BigFraction.TWO;
        BigDecimal bigDecimal0 = bigFraction0.MINUS_ONE.bigDecimalValue();
        bigDecimal0.shortValue();
    }

    @Test(timeout = 4000)
    public void test05711() throws Throwable {
        BigFraction bigFraction0 = BigFraction.TWO;
        BigFraction bigFraction1 = bigFraction0.multiply((-1));
        BigFraction bigFraction2 = bigFraction1.subtract(bigFraction0);
        BigFraction bigFraction3 = bigFraction0.add(bigFraction2);
        bigFraction3.byteValue();
    }

    @Test(timeout = 4000)
    public void test05712() throws Throwable {
        BigFraction bigFraction0 = BigFraction.TWO;
        BigFraction bigFraction1 = bigFraction0.multiply((-1));
        BigFraction bigFraction2 = bigFraction1.subtract(bigFraction0);
        BigFraction bigFraction3 = bigFraction0.add(bigFraction2);
        bigFraction3.equals((Object) bigFraction1);
    }

    @Test(timeout = 4000)
    public void test05813() throws Throwable {
        BigFraction bigFraction0 = BigFraction.FOUR_FIFTHS;
        BigInteger bigInteger0 = BigInteger.ONE;
        BigFraction bigFraction1 = bigFraction0.ONE.add(bigInteger0);
        bigFraction1.shortValue();
    }

    @Test(timeout = 4000)
    public void test05914() throws Throwable {
        BigFraction bigFraction0 = BigFraction.MINUS_ONE;
        byte[] byteArray0 = new byte[3];
        BigInteger bigInteger0 = new BigInteger(byteArray0);
        BigFraction bigFraction1 = bigFraction0.add(bigInteger0);
        bigFraction1.equals((Object) bigFraction0);
    }

    @Test(timeout = 4000)
    public void test05915() throws Throwable {
        BigFraction bigFraction0 = BigFraction.MINUS_ONE;
        byte[] byteArray0 = new byte[3];
        BigInteger bigInteger0 = new BigInteger(byteArray0);
        BigFraction bigFraction1 = bigFraction0.add(bigInteger0);
        bigFraction1.byteValue();
    }

    @Test(timeout = 4000)
    public void test06016() throws Throwable {
        BigFraction bigFraction0 = BigFraction.FOUR_FIFTHS;
        BigFraction bigFraction1 = bigFraction0.TWO_THIRDS.add((-692));
        BigFraction bigFraction2 = bigFraction1.add((long) (-692));
        bigFraction2.shortValue();
    }

    @Test(timeout = 4000)
    public void test06117() throws Throwable {
        BigFraction bigFraction0 = BigFraction.ONE_FIFTH;
        BigFraction bigFraction1 = bigFraction0.THREE_FIFTHS.add(0);
        bigFraction1.shortValue();
    }

    @Test(timeout = 4000)
    public void test06118() throws Throwable {
        BigFraction bigFraction0 = BigFraction.ONE_FIFTH;
        BigFraction bigFraction1 = bigFraction0.THREE_FIFTHS.add(0);
        bigFraction1.equals((Object) bigFraction0);
    }

    @Test(timeout = 4000)
    public void test06219() throws Throwable {
        BigFraction bigFraction0 = BigFraction.ONE_FIFTH;
        BigFraction bigFraction1 = bigFraction0.add(2448);
        bigFraction1.shortValue();
    }

    @Test(timeout = 4000)
    public void test06320() throws Throwable {
        BigFraction bigFraction0 = BigFraction.ONE_FIFTH;
        BigFraction bigFraction1 = bigFraction0.ONE_FIFTH.subtract(406);
        BigFraction bigFraction2 = bigFraction1.abs();
        bigFraction2.byteValue();
    }

    @Test(timeout = 4000)
    public void test06421() throws Throwable {
        BigFraction bigFraction0 = new BigFraction((-974L));
        bigFraction0.TWO.pow((BigInteger) null);
    }

    @Test(timeout = 4000)
    public void test06522() throws Throwable {
        BigFraction bigFraction0 = BigFraction.ONE_FIFTH;
        bigFraction0.pow(Integer.MAX_VALUE);
    }

    @Test(timeout = 4000)
    public void test06623() throws Throwable {
        BigFraction.getReducedFraction(3630, 0);
    }

    @Test(timeout = 4000)
    public void test06724() throws Throwable {
        BigFraction bigFraction0 = BigFraction.ONE;
        bigFraction0.ONE_QUARTER.divide(0L);
    }

    @Test(timeout = 4000)
    public void test06825() throws Throwable {
        BigFraction bigFraction0 = new BigFraction((-467.49498417), 59);
        bigFraction0.compareTo((BigFraction) null);
    }

    @Test(timeout = 4000)
    public void test06926() throws Throwable {
        BigFraction bigFraction0 = BigFraction.ONE;
        bigFraction0.bigDecimalValue((-719), 1043);
    }

    @Test(timeout = 4000)
    public void test07027() throws Throwable {
        BigFraction bigFraction0 = BigFraction.MINUS_ONE;
        bigFraction0.ONE_THIRD.bigDecimalValue();
    }

    @Test(timeout = 4000)
    public void test07128() throws Throwable {
        BigFraction bigFraction0 = BigFraction.TWO;
        bigFraction0.add((BigInteger) null);
    }

    @Test(timeout = 4000)
    public void test07229() throws Throwable {
        BigFraction bigFraction0 = null;
        bigFraction0 = new BigFraction((BigInteger) null, (BigInteger) null);
    }

    @Test(timeout = 4000)
    public void test07330() throws Throwable {
        BigFraction bigFraction0 = null;
        bigFraction0 = new BigFraction((BigInteger) null);
    }

    @Test(timeout = 4000)
    public void test07431() throws Throwable {
        BigFraction bigFraction0 = null;
        bigFraction0 = new BigFraction(0L, 0L);
    }

    @Test(timeout = 4000)
    public void test07532() throws Throwable {
        BigFraction bigFraction0 = null;
        bigFraction0 = new BigFraction(0.0, (-1));
    }

    @Test(timeout = 4000)
    public void test07633() throws Throwable {
        BigFraction bigFraction0 = BigFraction.ONE;
        BigInteger bigInteger0 = BigInteger.ONE;
        BigFraction bigFraction1 = bigFraction0.subtract(bigInteger0);
        BigFraction bigFraction2 = bigFraction1.multiply(bigFraction0);
        bigFraction2.equals((Object) bigFraction0);
    }

    @Test(timeout = 4000)
    public void test07634() throws Throwable {
        BigFraction bigFraction0 = BigFraction.ONE;
        BigInteger bigInteger0 = BigInteger.ONE;
        BigFraction bigFraction1 = bigFraction0.subtract(bigInteger0);
        BigFraction bigFraction2 = bigFraction1.multiply(bigFraction0);
        bigFraction2.equals((Object) bigFraction1);
    }

    @Test(timeout = 4000)
    public void test07735() throws Throwable {
        BigFraction bigFraction0 = BigFraction.ONE_QUARTER;
        BigInteger bigInteger0 = BigInteger.TEN;
        BigFraction bigFraction1 = bigFraction0.ONE_FIFTH.multiply(bigInteger0);
        bigFraction1.shortValue();
    }

    @Test(timeout = 4000)
    public void test07836() throws Throwable {
        BigFraction bigFraction0 = BigFraction.TWO_THIRDS;
        boolean boolean0 = bigFraction0.equals(bigFraction0);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test07937() throws Throwable {
        BigFraction bigFraction0 = BigFraction.ONE_FIFTH;
        double double0 = bigFraction0.doubleValue();
    }

    @Test(timeout = 4000)
    public void test08038() throws Throwable {
        BigFraction bigFraction0 = BigFraction.ONE_QUARTER;
        BigInteger bigInteger0 = BigInteger.ZERO;
        bigFraction0.TWO_QUARTERS.divide(bigInteger0);
    }

    @Test(timeout = 4000)
    public void test08139() throws Throwable {
        BigFraction bigFraction0 = BigFraction.FOUR_FIFTHS;
        BigInteger bigInteger0 = BigInteger.ONE;
        BigFraction bigFraction1 = bigFraction0.divide(bigInteger0);
        bigFraction1.byteValue();
    }

    @Test(timeout = 4000)
    public void test08140() throws Throwable {
        BigFraction bigFraction0 = BigFraction.FOUR_FIFTHS;
        BigInteger bigInteger0 = BigInteger.ONE;
        BigFraction bigFraction1 = bigFraction0.divide(bigInteger0);
        bigFraction1.equals((Object) bigFraction0);
    }

    @Test(timeout = 4000)
    public void test08241() throws Throwable {
        byte[] byteArray0 = new byte[8];
        byteArray0[0] = (byte) (-116);
        BigInteger bigInteger0 = new BigInteger(byteArray0);
        BigFraction bigFraction0 = new BigFraction(bigInteger0, bigInteger0);
        bigFraction0.byteValue();
    }

    @Test(timeout = 4000)
    public void test08342() throws Throwable {
        BigInteger bigInteger0 = BigInteger.ONE;
        BigFraction bigFraction0 = new BigFraction(bigInteger0, bigInteger0);
        bigFraction0.byteValue();
    }

    @Test(timeout = 4000)
    public void test08443() throws Throwable {
        BigInteger bigInteger0 = BigInteger.ZERO;
        BigFraction bigFraction0 = null;
        bigFraction0 = new BigFraction(bigInteger0, bigInteger0);
    }

    @Test(timeout = 4000)
    public void test08544() throws Throwable {
        BigFraction bigFraction0 = new BigFraction((-858));
        BigFraction bigFraction1 = bigFraction0.ONE_FIFTH.reduce();
        bigFraction0.shortValue();
    }

    @Test(timeout = 4000)
    public void test08545() throws Throwable {
        BigFraction bigFraction0 = new BigFraction((-858));
        BigFraction bigFraction1 = bigFraction0.ONE_FIFTH.reduce();
        bigFraction1.byteValue();
    }

    @Test(timeout = 4000)
    public void test08646() throws Throwable {
        BigFraction bigFraction0 = BigFraction.ZERO;
        BigInteger bigInteger0 = BigInteger.ZERO;
        BigFraction bigFraction1 = bigFraction0.FOUR_FIFTHS.add(bigInteger0);
        String string0 = bigFraction1.toString();
    }

    @Test(timeout = 4000)
    public void test08747() throws Throwable {
        BigFraction bigFraction0 = BigFraction.ZERO;
        BigInteger bigInteger0 = BigInteger.ZERO;
        BigFraction bigFraction1 = bigFraction0.FOUR_FIFTHS.add(bigInteger0);
        BigFraction bigFraction2 = bigFraction1.ONE.subtract(bigFraction1);
        bigFraction2.equals((Object) bigFraction0);
    }

    @Test(timeout = 4000)
    public void test08748() throws Throwable {
        BigFraction bigFraction0 = BigFraction.ZERO;
        BigInteger bigInteger0 = BigInteger.ZERO;
        BigFraction bigFraction1 = bigFraction0.FOUR_FIFTHS.add(bigInteger0);
        BigFraction bigFraction2 = bigFraction1.ONE.subtract(bigFraction1);
        bigFraction2.equals((Object) bigFraction1);
    }

    @Test(timeout = 4000)
    public void test08749() throws Throwable {
        BigFraction bigFraction0 = BigFraction.ZERO;
        BigInteger bigInteger0 = BigInteger.ZERO;
        BigFraction bigFraction1 = bigFraction0.FOUR_FIFTHS.add(bigInteger0);
        BigFraction bigFraction2 = bigFraction1.ONE.subtract(bigFraction1);
        bigFraction2.byteValue();
    }

    @Test(timeout = 4000)
    public void test08850() throws Throwable {
        BigFraction bigFraction0 = new BigFraction(1075);
        bigFraction0.subtract((BigFraction) null);
    }

    @Test(timeout = 4000)
    public void test08951() throws Throwable {
        BigFraction bigFraction0 = BigFraction.ZERO;
        BigFraction bigFraction1 = bigFraction0.ONE.subtract(bigFraction0);
        bigFraction1.equals((Object) bigFraction0);
    }

    @Test(timeout = 4000)
    public void test09052() throws Throwable {
        byte[] byteArray0 = new byte[7];
        BigInteger bigInteger0 = new BigInteger(byteArray0);
        BigFraction bigFraction0 = new BigFraction(bigInteger0);
        bigFraction0.subtract((BigInteger) null);
    }

    @Test(timeout = 4000)
    public void test09153() throws Throwable {
        BigFraction bigFraction0 = BigFraction.TWO;
        byte[] byteArray0 = new byte[3];
        byteArray0[0] = (byte) (-1);
        BigInteger bigInteger0 = new BigInteger(byteArray0);
        BigFraction bigFraction1 = bigFraction0.pow(bigInteger0);
        bigFraction1.byteValue();
    }

    @Test(timeout = 4000)
    public void test09154() throws Throwable {
        BigFraction bigFraction0 = BigFraction.TWO;
        byte[] byteArray0 = new byte[3];
        byteArray0[0] = (byte) (-1);
        BigInteger bigInteger0 = new BigInteger(byteArray0);
        BigFraction bigFraction1 = bigFraction0.pow(bigInteger0);
        bigFraction1.equals((Object) bigFraction0);
    }

    @Test(timeout = 4000)
    public void test09255() throws Throwable {
        BigFraction bigFraction0 = BigFraction.ZERO;
        BigFraction bigFraction1 = bigFraction0.ONE_FIFTH.pow((-608L));
        BigFraction bigFraction2 = bigFraction1.ONE.subtract(bigFraction1);
        bigFraction2.equals((Object) bigFraction0);
    }

    @Test(timeout = 4000)
    public void test09256() throws Throwable {
        BigFraction bigFraction0 = BigFraction.ZERO;
        BigFraction bigFraction1 = bigFraction0.ONE_FIFTH.pow((-608L));
        BigFraction bigFraction2 = bigFraction1.ONE.subtract(bigFraction1);
        bigFraction2.shortValue();
    }

    @Test(timeout = 4000)
    public void test09357() throws Throwable {
        BigInteger bigInteger0 = BigInteger.ZERO;
        BigFraction bigFraction0 = new BigFraction(bigInteger0);
        BigFraction bigFraction1 = bigFraction0.pow(0L);
        bigFraction1.shortValue();
    }

    @Test(timeout = 4000)
    public void test09458() throws Throwable {
        BigFraction bigFraction0 = BigFraction.ZERO;
        BigFraction bigFraction1 = bigFraction0.TWO.multiply(bigFraction0);
    }

    @Test(timeout = 4000)
    public void test09559() throws Throwable {
        BigFraction bigFraction0 = BigFraction.FOUR_FIFTHS;
        float float0 = bigFraction0.floatValue();
    }

    @Test(timeout = 4000)
    public void test09660() throws Throwable {
        BigFraction bigFraction0 = BigFraction.TWO;
        BigFraction bigFraction1 = BigFraction.ONE_HALF;
        BigFraction bigFraction2 = bigFraction0.multiply(bigFraction1);
        boolean boolean0 = bigFraction1.TWO_QUARTERS.equals(bigFraction2);
        bigFraction2.equals((Object) bigFraction0);
    }

    @Test(timeout = 4000)
    public void test09661() throws Throwable {
        BigFraction bigFraction0 = BigFraction.TWO;
        BigFraction bigFraction1 = BigFraction.ONE_HALF;
        BigFraction bigFraction2 = bigFraction0.multiply(bigFraction1);
        boolean boolean0 = bigFraction1.TWO_QUARTERS.equals(bigFraction2);
        bigFraction0.equals((Object) bigFraction2);
    }

    @Test(timeout = 4000)
    public void test09662() throws Throwable {
        BigFraction bigFraction0 = BigFraction.TWO;
        BigFraction bigFraction1 = BigFraction.ONE_HALF;
        BigFraction bigFraction2 = bigFraction0.multiply(bigFraction1);
        boolean boolean0 = bigFraction1.TWO_QUARTERS.equals(bigFraction2);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test09663() throws Throwable {
        BigFraction bigFraction0 = BigFraction.TWO;
        BigFraction bigFraction1 = BigFraction.ONE_HALF;
        BigFraction bigFraction2 = bigFraction0.multiply(bigFraction1);
        boolean boolean0 = bigFraction1.TWO_QUARTERS.equals(bigFraction2);
        bigFraction2.equals((Object) bigFraction1);
    }

    @Test(timeout = 4000)
    public void test09664() throws Throwable {
        BigFraction bigFraction0 = BigFraction.TWO;
        BigFraction bigFraction1 = BigFraction.ONE_HALF;
        BigFraction bigFraction2 = bigFraction0.multiply(bigFraction1);
        boolean boolean0 = bigFraction1.TWO_QUARTERS.equals(bigFraction2);
        bigFraction2.shortValue();
    }

    @Test(timeout = 4000)
    public void test09765() throws Throwable {
        BigFraction bigFraction0 = BigFraction.TWO;
        Object object0 = new Object();
        boolean boolean0 = bigFraction0.equals(object0);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test09866() throws Throwable {
        BigFraction bigFraction0 = BigFraction.ONE_HALF;
        bigFraction0.TWO_THIRDS.divide((BigFraction) null);
    }

    @Test(timeout = 4000)
    public void test09967() throws Throwable {
        BigFraction bigFraction0 = BigFraction.TWO_QUARTERS;
        bigFraction0.divide(0);
    }

    @Test(timeout = 4000)
    public void test10068() throws Throwable {
        BigFraction bigFraction0 = BigFraction.ONE_FIFTH;
        bigFraction0.divide((BigInteger) null);
    }

    @Test(timeout = 4000)
    public void test10169() throws Throwable {
        BigFraction bigFraction0 = BigFraction.FOUR_FIFTHS;
        BigFraction bigFraction1 = bigFraction0.TWO_THIRDS.add((-692));
        BigFraction bigFraction2 = bigFraction0.add(bigFraction1);
        bigFraction2.byteValue();
    }

    @Test(timeout = 4000)
    public void test10270() throws Throwable {
        BigFraction bigFraction0 = BigFraction.FOUR_FIFTHS;
        BigFraction bigFraction1 = new BigFraction(0.8F, 2322.37, 1);
        BigFraction bigFraction2 = bigFraction0.add(bigFraction1);
        bigFraction2.equals((Object) bigFraction1);
    }

    @Test(timeout = 4000)
    public void test10271() throws Throwable {
        BigFraction bigFraction0 = BigFraction.FOUR_FIFTHS;
        BigFraction bigFraction1 = new BigFraction(0.8F, 2322.37, 1);
        BigFraction bigFraction2 = bigFraction0.add(bigFraction1);
    }

    @Test(timeout = 4000)
    public void test10372() throws Throwable {
        BigFraction bigFraction0 = BigFraction.ONE_QUARTER;
        bigFraction0.FOUR_FIFTHS.add((BigFraction) null);
    }

    @Test(timeout = 4000)
    public void test10473() throws Throwable {
        BigFraction bigFraction0 = BigFraction.FOUR_FIFTHS;
        BigFraction bigFraction1 = bigFraction0.add(bigFraction0);
        bigFraction1.equals((Object) bigFraction0);
    }

    @Test(timeout = 4000)
    public void test10474() throws Throwable {
        BigFraction bigFraction0 = BigFraction.FOUR_FIFTHS;
        BigFraction bigFraction1 = bigFraction0.add(bigFraction0);
        bigFraction1.byteValue();
    }

    @Test(timeout = 4000)
    public void test10575() throws Throwable {
        BigFraction bigFraction0 = BigFraction.ZERO;
        BigFraction bigFraction1 = bigFraction0.TWO_THIRDS.abs();
    }

    @Test(timeout = 4000)
    public void test10676() throws Throwable {
        BigFraction bigFraction0 = BigFraction.getReducedFraction(0, 0);
        bigFraction0.divide(bigFraction0);
    }

    @Test(timeout = 4000)
    public void test10777() throws Throwable {
        BigFraction bigFraction0 = new BigFraction(2421.19, 629);
        bigFraction0.shortValue();
    }

    @Test(timeout = 4000)
    public void test10878() throws Throwable {
        BigFraction bigFraction0 = null;
        bigFraction0 = new BigFraction(0.028507909084964922, 0.028507909084964922, (-1846));
    }

    @Test(timeout = 4000)
    public void test10979() throws Throwable {
        BigFraction bigFraction0 = null;
        bigFraction0 = new BigFraction(1.7976931348623157E308, 1.7976931348623157E308, 382);
    }

    @Test(timeout = 4000)
    public void test11080() throws Throwable {
        BigFraction bigFraction0 = new BigFraction((double) (-692));
        String string0 = bigFraction0.toString();
    }

    @Test(timeout = 4000)
    public void test11081() throws Throwable {
        BigFraction bigFraction0 = new BigFraction((double) (-692));
        String string0 = bigFraction0.toString();
        bigFraction0.shortValue();
    }

    @Test(timeout = 4000)
    public void test11182() throws Throwable {
        BigFraction bigFraction0 = new BigFraction((-717.224843262248));
        bigFraction0.multiply((BigInteger) null);
    }

    @Test(timeout = 4000)
    public void test11283() throws Throwable {
        BigFraction bigFraction0 = new BigFraction(0.0);
        int int0 = bigFraction0.getDenominatorAsInt();
        assertEquals(0, int0);
    }

    @Test(timeout = 4000)
    public void test11384() throws Throwable {
        BigFraction bigFraction0 = new BigFraction(1362.6);
        BigFraction bigFraction1 = bigFraction0.ZERO.divide(bigFraction0);
        bigFraction1.byteValue();
    }

    @Test(timeout = 4000)
    public void test11385() throws Throwable {
        BigFraction bigFraction0 = new BigFraction(1362.6);
        BigFraction bigFraction1 = bigFraction0.ZERO.divide(bigFraction0);
        bigFraction0.shortValue();
    }

    @Test(timeout = 4000)
    public void test11486() throws Throwable {
        BigFraction bigFraction0 = BigFraction.TWO_QUARTERS;
        BigFraction bigFraction1 = bigFraction0.multiply((long) 0);
        bigFraction1.reciprocal();
    }

    @Test(timeout = 4000)
    public void test11587() throws Throwable {
        BigFraction bigFraction0 = BigFraction.TWO;
        double double0 = bigFraction0.ONE_QUARTER.pow((-1.0));
    }

    @Test(timeout = 4000)
    public void test11688() throws Throwable {
        BigFraction bigFraction0 = BigFraction.ONE_FIFTH;
        long long0 = bigFraction0.getNumeratorAsLong();
        assertEquals(1, long0);
    }

    @Test(timeout = 4000)
    public void test11789() throws Throwable {
        BigFraction bigFraction0 = BigFraction.TWO;
        boolean boolean0 = bigFraction0.TWO_QUARTERS.equals(bigFraction0);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test11890() throws Throwable {
        BigFraction bigFraction0 = new BigFraction((-1192L), (-3512299194304650054L));
        BigFraction bigFraction1 = bigFraction0.multiply((-270L));
        int int0 = bigFraction1.getDenominatorAsInt();
        assertEquals(0, int0);
    }

    @Test(timeout = 4000)
    public void test11991() throws Throwable {
        BigFraction bigFraction0 = BigFraction.TWO;
        int int0 = bigFraction0.getDenominatorAsInt();
        assertEquals(1, int0);
    }

    @Test(timeout = 4000)
    public void test12092() throws Throwable {
        BigFraction bigFraction0 = BigFraction.TWO;
        bigFraction0.bigDecimalValue(69);
    }

    @Test(timeout = 4000)
    public void test12193() throws Throwable {
        BigFraction bigFraction0 = BigFraction.TWO_QUARTERS;
        BigDecimal bigDecimal0 = bigFraction0.ONE_FIFTH.bigDecimalValue(1770, 0);
        bigDecimal0.byteValue();
    }

    @Test(timeout = 4000)
    public void test12294() throws Throwable {
        BigFraction bigFraction0 = BigFraction.TWO;
        BigFraction bigFraction1 = bigFraction0.ONE_FIFTH.multiply(2);
        bigFraction1.equals((Object) bigFraction0);
    }

    @Test(timeout = 4000)
    public void test12295() throws Throwable {
        BigFraction bigFraction0 = BigFraction.TWO;
        BigFraction bigFraction1 = bigFraction0.ONE_FIFTH.multiply(2);
        bigFraction1.byteValue();
    }

    @Test(timeout = 4000)
    public void test12396() throws Throwable {
        BigFraction bigFraction0 = BigFraction.ONE_FIFTH;
        long long0 = bigFraction0.longValue();
        assertEquals(1, long0);
    }

    @Test(timeout = 4000)
    public void test12497() throws Throwable {
        BigFraction bigFraction0 = BigFraction.ZERO;
        BigInteger bigInteger0 = BigInteger.TEN;
        BigFraction bigFraction1 = bigFraction0.subtract(bigInteger0);
        BigFraction bigFraction2 = bigFraction1.abs();
        bigFraction2.byteValue();
    }

    @Test(timeout = 4000)
    public void test12598() throws Throwable {
        BigFraction bigFraction0 = BigFraction.TWO;
        BigFraction bigFraction1 = bigFraction0.subtract(2);
        bigFraction1.byteValue();
    }

    @Test(timeout = 4000)
    public void test12599() throws Throwable {
        BigFraction bigFraction0 = BigFraction.TWO;
        BigFraction bigFraction1 = bigFraction0.subtract(2);
        bigFraction1.equals((Object) bigFraction0);
    }

    @Test(timeout = 4000)
    public void test127100() throws Throwable {
        BigFraction bigFraction0 = BigFraction.ZERO;
        long long0 = bigFraction0.ONE.getDenominatorAsLong();
        assertEquals(1, long0);
    }

    @Test(timeout = 4000)
    public void test128101() throws Throwable {
        BigFraction bigFraction0 = BigFraction.ONE_FIFTH;
        double double0 = bigFraction0.percentageValue();
    }

    @Test(timeout = 4000)
    public void test129102() throws Throwable {
        BigFraction bigFraction0 = BigFraction.getReducedFraction(1, 1);
        BigFraction bigFraction1 = bigFraction0.reduce();
        bigFraction1.equals((Object) bigFraction0);
    }

    @Test(timeout = 4000)
    public void test129103() throws Throwable {
        BigFraction bigFraction0 = BigFraction.getReducedFraction(1, 1);
        BigFraction bigFraction1 = bigFraction0.reduce();
        bigFraction1.shortValue();
    }

    @Test(timeout = 4000)
    public void test130104() throws Throwable {
        BigFraction bigFraction0 = BigFraction.ONE_FIFTH;
        BigDecimal bigDecimal0 = bigFraction0.bigDecimalValue();
        bigDecimal0.shortValue();
    }

    @Test(timeout = 4000)
    public void test131105() throws Throwable {
        BigFraction bigFraction0 = BigFraction.ONE_FIFTH;
        BigFraction bigFraction1 = bigFraction0.add(4503599627370496L);
        bigFraction1.shortValue();
    }

    @Test(timeout = 4000)
    public void test131106() throws Throwable {
        BigFraction bigFraction0 = BigFraction.ONE_FIFTH;
        BigFraction bigFraction1 = bigFraction0.add(4503599627370496L);
        bigFraction1.equals((Object) bigFraction0);
    }

    @Test(timeout = 4000)
    public void test132107() throws Throwable {
        BigFraction bigFraction0 = BigFraction.ZERO;
        int int0 = bigFraction0.THREE_QUARTERS.compareTo(bigFraction0);
        assertEquals(1, int0);
    }

    @Test(timeout = 4000)
    public void test133108() throws Throwable {
        BigFraction bigFraction0 = new BigFraction((-974L));
        BigFraction bigFraction1 = bigFraction0.subtract((-974L));
        bigFraction0.shortValue();
    }

    @Test(timeout = 4000)
    public void test133109() throws Throwable {
        BigFraction bigFraction0 = new BigFraction((-974L));
        BigFraction bigFraction1 = bigFraction0.subtract((-974L));
        bigFraction1.shortValue();
    }

    @Test(timeout = 4000)
    public void test134110() throws Throwable {
        BigFraction bigFraction0 = BigFraction.ONE_FIFTH;
        BigFraction bigFraction1 = bigFraction0.TWO_FIFTHS.divide(1951);
        bigFraction1.equals((Object) bigFraction0);
    }

    @Test(timeout = 4000)
    public void test134111() throws Throwable {
        BigFraction bigFraction0 = BigFraction.ONE_FIFTH;
        BigFraction bigFraction1 = bigFraction0.TWO_FIFTHS.divide(1951);
        bigFraction1.shortValue();
    }

    @Test(timeout = 4000)
    public void test135112() throws Throwable {
        BigFraction bigFraction0 = BigFraction.FOUR_FIFTHS;
        int int0 = bigFraction0.ONE.getNumeratorAsInt();
        assertEquals(1, int0);
    }

    @Test(timeout = 4000)
    public void test136113() throws Throwable {
        BigFraction bigFraction0 = BigFraction.TWO_QUARTERS;
        BigFractionField bigFractionField0 = bigFraction0.ONE_FIFTH.getField();
        assertNotNull(bigFractionField0);
    }

    @Test(timeout = 4000)
    public void test137114() throws Throwable {
        BigFraction bigFraction0 = BigFraction.ZERO;
        BigInteger bigInteger0 = bigFraction0.getNumerator();
        bigInteger0.shortValue();
    }

    @Test(timeout = 4000)
    public void test138115() throws Throwable {
        BigFraction bigFraction0 = BigFraction.FOUR_FIFTHS;
        int int0 = bigFraction0.ONE_FIFTH.intValue();
        assertEquals(1, int0);
    }
}
