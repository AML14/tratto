/*
 * This file was automatically generated by EvoSuite
 * Mon Nov 20 08:01:52 GMT 2023
 */
package org.apache.commons.math3.fraction;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import org.apache.commons.math3.fraction.Fraction;
import org.apache.commons.math3.fraction.FractionField;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class Fraction_ESTest extends Fraction_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        Fraction fraction0 = Fraction.ONE_THIRD;
        Fraction fraction1 = fraction0.ONE_THIRD.multiply((-1517));
        fraction1.getNumerator();
    }

    @Test(timeout = 4000)
    public void test001() throws Throwable {
        Fraction fraction0 = Fraction.ONE_THIRD;
        Fraction fraction1 = fraction0.ONE_THIRD.multiply((-1517));
        fraction1.percentageValue();
    }

    @Test(timeout = 4000)
    public void test012() throws Throwable {
        Fraction fraction0 = Fraction.ONE_QUARTER;
        int int0 = fraction0.getDenominator();
    }

    @Test(timeout = 4000)
    public void test023() throws Throwable {
        Fraction fraction0 = new Fraction(423, 423);
        Fraction fraction1 = fraction0.add(Integer.MAX_VALUE);
        fraction1.negate();
    }

    @Test(timeout = 4000)
    public void test034() throws Throwable {
        Fraction fraction0 = Fraction.ONE_THIRD;
        Fraction fraction1 = fraction0.TWO_FIFTHS.divide(1399);
        fraction1.getDenominator();
    }

    @Test(timeout = 4000)
    public void test035() throws Throwable {
        Fraction fraction0 = Fraction.ONE_THIRD;
        Fraction fraction1 = fraction0.TWO_FIFTHS.divide(1399);
        fraction1.floatValue();
    }

    @Test(timeout = 4000)
    public void test046() throws Throwable {
        Fraction fraction0 = Fraction.MINUS_ONE;
        Fraction fraction1 = fraction0.subtract(fraction0);
        fraction1.percentageValue();
    }

    @Test(timeout = 4000)
    public void test057() throws Throwable {
        Fraction fraction0 = Fraction.THREE_QUARTERS;
        FractionField fractionField0 = fraction0.getField();
    }

    @Test(timeout = 4000)
    public void test068() throws Throwable {
        Fraction fraction0 = new Fraction((double) 102);
        fraction0.byteValue();
    }

    @Test(timeout = 4000)
    public void test079() throws Throwable {
        Fraction fraction0 = Fraction.TWO_QUARTERS;
        long long0 = fraction0.longValue();
        fraction0.percentageValue();
    }

    @Test(timeout = 4000)
    public void test0710() throws Throwable {
        Fraction fraction0 = Fraction.TWO_QUARTERS;
        long long0 = fraction0.longValue();
    }

    @Test(timeout = 4000)
    public void test0811() throws Throwable {
        Fraction fraction0 = Fraction.ONE_HALF;
        Fraction fraction1 = fraction0.TWO_THIRDS.subtract(4268);
        fraction1.doubleValue();
    }

    @Test(timeout = 4000)
    public void test0812() throws Throwable {
        Fraction fraction0 = Fraction.ONE_HALF;
        Fraction fraction1 = fraction0.TWO_THIRDS.subtract(4268);
        fraction1.getDenominator();
    }

    @Test(timeout = 4000)
    public void test0913() throws Throwable {
        Fraction fraction0 = new Fraction(4);
        int int0 = fraction0.intValue();
    }

    @Test(timeout = 4000)
    public void test0914() throws Throwable {
        Fraction fraction0 = new Fraction(4);
        int int0 = fraction0.intValue();
        fraction0.getDenominator();
    }

    @Test(timeout = 4000)
    public void test1115() throws Throwable {
        Fraction fraction0 = Fraction.THREE_QUARTERS;
        int int0 = fraction0.getNumerator();
    }

    @Test(timeout = 4000)
    public void test1216() throws Throwable {
        Fraction fraction0 = Fraction.TWO_QUARTERS;
        float float0 = fraction0.floatValue();
    }

    @Test(timeout = 4000)
    public void test1317() throws Throwable {
        Fraction fraction0 = Fraction.THREE_QUARTERS;
        double double0 = fraction0.percentageValue();
    }

    @Test(timeout = 4000)
    public void test1418() throws Throwable {
        Fraction fraction0 = null;
        fraction0 = new Fraction((double) 4, 4);
    }

    @Test(timeout = 4000)
    public void test1519() throws Throwable {
        Fraction fraction0 = null;
        fraction0 = new Fraction((double) Integer.MIN_VALUE);
    }

    @Test(timeout = 4000)
    public void test1620() throws Throwable {
        Fraction fraction0 = null;
        fraction0 = new Fraction((double) 9, 9);
    }

    @Test(timeout = 4000)
    public void test1721() throws Throwable {
        Fraction fraction0 = null;
        fraction0 = new Fraction(4.671341494509873E-4, 4.671341494509873E-4, (-87));
    }

    @Test(timeout = 4000)
    public void test1822() throws Throwable {
        Fraction fraction0 = new Fraction((-1044.2346729));
        fraction0.getDenominator();
    }

    @Test(timeout = 4000)
    public void test1923() throws Throwable {
        Fraction fraction0 = new Fraction(425.11148473894536, 2288);
        fraction0.percentageValue();
    }

    @Test(timeout = 4000)
    public void test2024() throws Throwable {
        Fraction fraction0 = null;
        fraction0 = new Fraction(0, 0);
    }

    @Test(timeout = 4000)
    public void test2125() throws Throwable {
        Fraction fraction0 = new Fraction((-2059), (-2059));
        fraction0.getNumerator();
    }

    @Test(timeout = 4000)
    public void test2126() throws Throwable {
        Fraction fraction0 = new Fraction((-2059), (-2059));
        fraction0.percentageValue();
    }

    @Test(timeout = 4000)
    public void test2227() throws Throwable {
        Fraction fraction0 = null;
        fraction0 = new Fraction(Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    @Test(timeout = 4000)
    public void test2328() throws Throwable {
        Fraction fraction0 = null;
        fraction0 = new Fraction(2, Integer.MIN_VALUE);
    }

    @Test(timeout = 4000)
    public void test2429() throws Throwable {
        Fraction fraction0 = Fraction.ONE_HALF;
        Fraction fraction1 = fraction0.MINUS_ONE.abs();
        fraction1.getDenominator();
    }

    @Test(timeout = 4000)
    public void test2430() throws Throwable {
        Fraction fraction0 = Fraction.ONE_HALF;
        Fraction fraction1 = fraction0.MINUS_ONE.abs();
        fraction1.shortValue();
    }

    @Test(timeout = 4000)
    public void test2531() throws Throwable {
        Fraction fraction0 = Fraction.ONE_FIFTH;
        Fraction fraction1 = fraction0.abs();
        fraction1.doubleValue();
    }

    @Test(timeout = 4000)
    public void test2632() throws Throwable {
        Fraction fraction0 = Fraction.ONE;
        int int0 = fraction0.compareTo(fraction0);
    }

    @Test(timeout = 4000)
    public void test2733() throws Throwable {
        Fraction fraction0 = Fraction.TWO_THIRDS;
        Fraction fraction1 = fraction0.MINUS_ONE.add(2);
        int int0 = fraction0.compareTo(fraction1);
    }

    @Test(timeout = 4000)
    public void test2734() throws Throwable {
        Fraction fraction0 = Fraction.TWO_THIRDS;
        Fraction fraction1 = fraction0.MINUS_ONE.add(2);
        int int0 = fraction0.compareTo(fraction1);
        fraction1.floatValue();
    }

    @Test(timeout = 4000)
    public void test2835() throws Throwable {
        Fraction fraction0 = Fraction.TWO_THIRDS;
        Fraction fraction1 = fraction0.MINUS_ONE.add((-2682));
        int int0 = fraction0.compareTo(fraction1);
    }

    @Test(timeout = 4000)
    public void test2836() throws Throwable {
        Fraction fraction0 = Fraction.TWO_THIRDS;
        Fraction fraction1 = fraction0.MINUS_ONE.add((-2682));
        int int0 = fraction0.compareTo(fraction1);
        fraction1.shortValue();
    }

    @Test(timeout = 4000)
    public void test2937() throws Throwable {
        Fraction fraction0 = Fraction.ONE_THIRD;
        Fraction fraction1 = Fraction.ONE_HALF;
        boolean boolean0 = fraction1.equals(fraction0);
        fraction0.equals((Object) fraction1);
    }

    @Test(timeout = 4000)
    public void test2938() throws Throwable {
        Fraction fraction0 = Fraction.ONE_THIRD;
        Fraction fraction1 = Fraction.ONE_HALF;
        boolean boolean0 = fraction1.equals(fraction0);
    }

    @Test(timeout = 4000)
    public void test3039() throws Throwable {
        Fraction fraction0 = Fraction.ONE_THIRD;
        boolean boolean0 = fraction0.equals(fraction0);
    }

    @Test(timeout = 4000)
    public void test3140() throws Throwable {
        Fraction fraction0 = Fraction.ONE_THIRD;
        Object object0 = new Object();
        boolean boolean0 = fraction0.equals(object0);
    }

    @Test(timeout = 4000)
    public void test3241() throws Throwable {
        Fraction fraction0 = Fraction.ONE_THIRD;
        Fraction fraction1 = Fraction.TWO_THIRDS;
        boolean boolean0 = fraction1.equals(fraction0);
    }

    @Test(timeout = 4000)
    public void test3242() throws Throwable {
        Fraction fraction0 = Fraction.ONE_THIRD;
        Fraction fraction1 = Fraction.TWO_THIRDS;
        boolean boolean0 = fraction1.equals(fraction0);
        fraction0.equals((Object) fraction1);
    }

    @Test(timeout = 4000)
    public void test3343() throws Throwable {
        Fraction fraction0 = Fraction.TWO_QUARTERS;
        Fraction fraction1 = Fraction.ONE_HALF;
        boolean boolean0 = fraction1.equals(fraction0);
    }

    @Test(timeout = 4000)
    public void test3444() throws Throwable {
        Fraction fraction0 = Fraction.ONE;
        assertTrue(((Fraction) null == null) == false);
        org.apache.commons.math3.fraction.Fraction default0;
        if ((Fraction) null == null) {
            try {
                default0 = fraction0.add((Fraction) null);
                fail();
            } catch (org.apache.commons.math3.exception.NullArgumentException e) {
                // Successfully thrown exception
            }
        } else {
            default0 = fraction0.add((Fraction) null);
        }
    }

    @Test(timeout = 4000)
    public void test3545() throws Throwable {
        Fraction fraction0 = Fraction.ONE_QUARTER;
        Fraction fraction1 = fraction0.ZERO.subtract(fraction0);
        fraction1.percentageValue();
    }

    @Test(timeout = 4000)
    public void test3546() throws Throwable {
        Fraction fraction0 = Fraction.ONE_QUARTER;
        Fraction fraction1 = fraction0.ZERO.subtract(fraction0);
        fraction1.getNumerator();
    }

    @Test(timeout = 4000)
    public void test3647() throws Throwable {
        Fraction fraction0 = Fraction.ONE_HALF;
        Fraction fraction1 = fraction0.ZERO.add(fraction0);
    }

    @Test(timeout = 4000)
    public void test3748() throws Throwable {
        Fraction fraction0 = Fraction.ONE_THIRD;
        Fraction fraction1 = Fraction.ZERO;
        Fraction fraction2 = fraction0.TWO_FIFTHS.add(fraction1);
        fraction2.percentageValue();
    }

    @Test(timeout = 4000)
    public void test3849() throws Throwable {
        Fraction fraction0 = Fraction.ONE_THIRD;
        Fraction fraction1 = fraction0.TWO_FIFTHS.add(fraction0);
        fraction1.getNumerator();
    }

    @Test(timeout = 4000)
    public void test3850() throws Throwable {
        Fraction fraction0 = Fraction.ONE_THIRD;
        Fraction fraction1 = fraction0.TWO_FIFTHS.add(fraction0);
        fraction1.percentageValue();
    }

    @Test(timeout = 4000)
    public void test3951() throws Throwable {
        Fraction fraction0 = Fraction.TWO_THIRDS;
        Fraction fraction1 = fraction0.subtract(fraction0);
        fraction1.percentageValue();
    }

    @Test(timeout = 4000)
    public void test3952() throws Throwable {
        Fraction fraction0 = Fraction.TWO_THIRDS;
        Fraction fraction1 = fraction0.subtract(fraction0);
        fraction1.getDenominator();
    }

    @Test(timeout = 4000)
    public void test4053() throws Throwable {
        Fraction fraction0 = Fraction.ONE_FIFTH;
        Fraction fraction1 = Fraction.getReducedFraction(Integer.MAX_VALUE, 2880);
        fraction1.add(fraction0);
    }

    @Test(timeout = 4000)
    public void test4154() throws Throwable {
        Fraction fraction0 = new Fraction(11, 11);
        Fraction fraction1 = fraction0.ZERO.divide(fraction0);
        fraction1.getNumerator();
    }

    @Test(timeout = 4000)
    public void test4155() throws Throwable {
        Fraction fraction0 = new Fraction(11, 11);
        Fraction fraction1 = fraction0.ZERO.divide(fraction0);
        fraction0.getDenominator();
    }

    @Test(timeout = 4000)
    public void test4156() throws Throwable {
        Fraction fraction0 = new Fraction(11, 11);
        Fraction fraction1 = fraction0.ZERO.divide(fraction0);
        fraction0.percentageValue();
    }

    @Test(timeout = 4000)
    public void test4257() throws Throwable {
        Fraction fraction0 = Fraction.TWO_QUARTERS;
        assertTrue(((Fraction) null == null) == false);
        org.apache.commons.math3.fraction.Fraction default1;
        if ((Fraction) null == null) {
            try {
                default1 = fraction0.multiply((Fraction) null);
                fail();
            } catch (org.apache.commons.math3.exception.NullArgumentException e) {
                // Successfully thrown exception
            }
        } else {
            default1 = fraction0.multiply((Fraction) null);
        }
    }

    @Test(timeout = 4000)
    public void test4358() throws Throwable {
        Fraction fraction0 = Fraction.TWO_QUARTERS;
        Fraction fraction1 = fraction0.FOUR_FIFTHS.multiply(fraction0);
        fraction1.doubleValue();
    }

    @Test(timeout = 4000)
    public void test4459() throws Throwable {
        Fraction fraction0 = Fraction.ZERO;
        Fraction fraction1 = fraction0.FOUR_FIFTHS.multiply(fraction0);
        fraction1.getNumerator();
    }

    @Test(timeout = 4000)
    public void test4560() throws Throwable {
        Fraction fraction0 = Fraction.ONE_QUARTER;
        assertTrue(((Fraction) null == null) == false);
        org.apache.commons.math3.fraction.Fraction default2;
        if ((Fraction) null == null) {
            try {
                default2 = fraction0.THREE_FIFTHS.divide((Fraction) null);
                fail();
            } catch (java.lang.IllegalArgumentException e) {
                // Successfully thrown exception
            }
        } else {
            default2 = fraction0.THREE_FIFTHS.divide((Fraction) null);
        }
    }

    @Test(timeout = 4000)
    public void test4661() throws Throwable {
        Fraction fraction0 = Fraction.ZERO;
        fraction0.ZERO.divide(fraction0);
    }

    @Test(timeout = 4000)
    public void test4762() throws Throwable {
        org.apache.commons.math3.fraction.Fraction default3;
        if (0 == 0) {
            try {
                default3 = Fraction.getReducedFraction(0, 0);
                fail();
            } catch (org.apache.commons.math3.exception.MathArithmeticException e) {
                // Successfully thrown exception
            }
        } else {
            default3 = Fraction.getReducedFraction(0, 0);
        }
    }

    @Test(timeout = 4000)
    public void test4863() throws Throwable {
        org.apache.commons.math3.fraction.Fraction fraction0;
        if ((-14612) == 0) {
            try {
                fraction0 = Fraction.getReducedFraction(0, (-14612));
                fail();
            } catch (org.apache.commons.math3.exception.MathArithmeticException e) {
                // Successfully thrown exception
            }
        } else {
            fraction0 = Fraction.getReducedFraction(0, (-14612));
        }
        fraction0.doubleValue();
    }

    @Test(timeout = 4000)
    public void test4964() throws Throwable {
        Fraction fraction0 = Fraction.getReducedFraction(Integer.MIN_VALUE, Integer.MIN_VALUE);
        fraction0.percentageValue();
    }

    @Test(timeout = 4000)
    public void test5065() throws Throwable {
        Fraction.getReducedFraction(4507, Integer.MIN_VALUE);
    }

    @Test(timeout = 4000)
    public void test5166() throws Throwable {
        Fraction.getReducedFraction(Integer.MIN_VALUE, (-2212));
    }

    @Test(timeout = 4000)
    public void test5267() throws Throwable {
        Fraction fraction0 = Fraction.ONE_FIFTH;
        String string0 = fraction0.toString();
    }

    @Test(timeout = 4000)
    public void test5368() throws Throwable {
        Fraction fraction0 = Fraction.MINUS_ONE;
        String string0 = fraction0.toString();
    }
}
