/*
 * This file was automatically generated by EvoSuite
 * Mon Oct 16 15:33:10 GMT 2023
 */
package org.apache.commons.math.complex;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.util.List;
import org.apache.commons.math.complex.Complex;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class Complex_ESTest extends Complex_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test0010() throws Throwable {
        Complex complex0 = new Complex((-2595.188104221), (-2595.188104221));
        Complex complex1 = (Complex) complex0.ZERO.readResolve();
        complex0.readResolve();
        Complex complex2 = Complex.valueOf(0.0, (-2595.188104221));
        Complex complex3 = Complex.valueOf(0.0, 0.0);
        Complex complex4 = complex3.ONE.cosh();
        Complex complex5 = complex3.add(0.0);
        complex5.acos();
        Complex complex6 = complex3.createComplex((-2595.188104221), (-1077.1));
        complex6.divide(complex3);
        complex0.getReal();
        Complex complex7 = complex0.sinh();
        complex7.INF.multiply((-2595.188104221));
        Complex complex8 = complex2.sqrt1z();
        complex2.ONE.pow((-2595.188104221));
        complex4.cos();
        complex3.NaN.abs();
        Complex.valueOf((-2595.188104221));
        Complex complex9 = complex8.subtract(complex3);
        complex4.cos();
        Complex complex10 = complex7.sinh();
        complex10.createComplex(0.041666666666621166, 892.28875252206);
        Complex complex11 = complex9.sinh();
        complex10.subtract(Double.NaN);
        Complex complex12 = new Complex(0.0);
        complex4.divide(complex12);
        Complex complex13 = complex7.divide(0.0);
        complex4.divide(complex13);
        complex12.acos();
        complex4.getField();
        complex11.isInfinite();
        org.apache.commons.math.complex.Complex default0;
        if ((Complex) null == null) {
            try {
                default0 = complex1.add((Complex) null);
                fail();
            } catch (org.apache.commons.math.exception.NullArgumentException e) {
                // Successfully thrown exception
            }
        } else {
            default0 = complex1.add((Complex) null);
        }
    }

    @Test(timeout = 4000)
    public void test0041() throws Throwable {
        Complex complex0 = Complex.valueOf(3154.786305001714);
        Complex complex1 = complex0.I.log();
        Complex complex2 = complex1.INF.sin();
        complex2.ONE.cosh();
        complex1.ONE.log();
        complex2.INF.cos();
        complex1.ZERO.sqrt1z();
        Complex complex3 = new Complex((-0.9993909506205958));
        complex1.ZERO.add(complex3);
        org.apache.commons.math.complex.Complex default1;
        if ((Complex) null == null) {
            try {
                default1 = complex0.divide((Complex) null);
                fail();
            } catch (org.apache.commons.math.exception.NullArgumentException e) {
                // Successfully thrown exception
            }
        } else {
            default1 = complex0.divide((Complex) null);
        }
    }

    @Test(timeout = 4000)
    public void test0052() throws Throwable {
        Complex complex0 = Complex.valueOf(0.0, 0.0);
        complex0.ONE.pow(0.0);
        complex0.toString();
        Complex complex1 = null;
        org.apache.commons.math.complex.Complex default2;
        if ((Complex) null == null) {
            try {
                default2 = complex0.multiply((Complex) null);
                fail();
            } catch (org.apache.commons.math.exception.NullArgumentException e) {
                // Successfully thrown exception
            }
        } else {
            default2 = complex0.multiply((Complex) null);
        }
    }

    @Test(timeout = 4000)
    public void test0073() throws Throwable {
        Complex complex0 = Complex.I;
        Complex complex1 = complex0.I.asin();
        Complex complex2 = complex0.ONE.multiply(complex1);
        Complex.valueOf(2.7553817452272217E-6);
        double double0 = 12.0;
        Complex complex3 = complex2.ZERO.createComplex(12.0, 12.0);
        complex3.sqrt();
        complex3.I.readResolve();
        Complex complex4 = Complex.valueOf(Double.POSITIVE_INFINITY);
        Complex complex5 = complex4.I.sin();
        Complex complex6 = new Complex(Double.POSITIVE_INFINITY, 12.0);
        complex4.NaN.subtract(complex6);
        complex4.subtract(complex5);
        complex5.NaN.pow(complex6);
        complex6.NaN.log();
        complex0.ONE.readResolve();
        complex0.ONE.readResolve();
        complex4.tan();
        complex0.abs();
        Complex complex7 = complex4.pow(1014.1582587727);
        org.apache.commons.math.complex.Complex default3;
        if ((Complex) null == null) {
            try {
                default3 = complex7.subtract((Complex) null);
                fail();
            } catch (org.apache.commons.math.exception.NullArgumentException e) {
                // Successfully thrown exception
            }
        } else {
            default3 = complex7.subtract((Complex) null);
        }
    }

    @Test(timeout = 4000)
    public void test0104() throws Throwable {
        Complex complex0 = Complex.ONE;
        complex0.isInfinite();
        org.apache.commons.math.complex.Complex default4;
        if ((Complex) null == null) {
            try {
                default4 = complex0.pow((Complex) null);
                fail();
            } catch (org.apache.commons.math.exception.NullArgumentException e) {
                // Successfully thrown exception
            }
        } else {
            default4 = complex0.pow((Complex) null);
        }
    }

    @Test(timeout = 4000)
    public void test0125() throws Throwable {
        Complex complex0 = Complex.INF;
        Complex complex1 = complex0.divide(Double.POSITIVE_INFINITY);
        Complex complex2 = complex1.exp();
        Complex complex3 = complex1.ONE.pow(Double.POSITIVE_INFINITY);
        Complex complex4 = complex3.pow(complex0);
        Complex complex5 = complex2.ZERO.cosh();
        Complex complex6 = complex3.I.tan();
        complex6.I.hashCode();
        Complex complex7 = complex0.ZERO.sin();
        complex2.createComplex(1870.5758085, 0.0);
        complex7.getArgument();
        complex1.toString();
        complex0.sin();
        Complex complex8 = complex0.multiply((-3240.151965));
        complex0.ONE.getField();
        Complex complex9 = complex1.NaN.pow(complex0);
        complex9.NaN.tanh();
        complex4.getField();
        Complex complex10 = complex7.log();
        complex10.INF.atan();
        Complex complex11 = complex8.atan();
        complex11.ZERO.atan();
        complex8.ZERO.getArgument();
        Complex complex12 = complex7.sin();
        complex12.NaN.divide(complex4);
        Complex.valueOf((-2345.9582633378));
        complex8.asin();
        Complex.valueOf((-3240.151965));
        complex12.hashCode();
        java.util.List default5;
        if ((-2148) <= 0) {
            try {
                default5 = complex5.nthRoot((-2148));
                fail();
            } catch (org.apache.commons.math.exception.NotPositiveException e) {
                // Successfully thrown exception
            }
        } else {
            default5 = complex5.nthRoot((-2148));
        }
    }

    @Test(timeout = 4000)
    public void test0206() throws Throwable {
        double double0 = 1015.7;
        double double1 = Double.POSITIVE_INFINITY;
        Complex complex0 = new Complex(1015.7, Double.POSITIVE_INFINITY);
        complex0.NaN.pow(0.16666666666666666);
        java.util.List default6;
        if ((-909) <= 0) {
            try {
                default6 = complex0.nthRoot((-909));
                fail();
            } catch (org.apache.commons.math.exception.NotPositiveException e) {
                // Successfully thrown exception
            }
        } else {
            default6 = complex0.nthRoot((-909));
        }
    }

    @Test(timeout = 4000)
    public void test0337() throws Throwable {
        Complex complex0 = Complex.valueOf((-2098.980137684341));
        Complex.valueOf(0.0, (-1884.806573799));
        Complex complex1 = complex0.createComplex(3160.071260027, (-2098.980137684341));
        complex1.NaN.tanh();
        Complex complex2 = complex1.ZERO.divide(3160.071260027);
        complex2.NaN.tanh();
        Complex complex3 = Complex.ONE;
        complex3.I.readResolve();
        Complex complex4 = complex1.NaN.add(complex3);
        complex4.sin();
        Complex complex5 = complex1.ZERO.acos();
        complex5.ZERO.log();
        complex0.getReal();
        java.util.List default7;
        if (0 <= 0) {
            try {
                default7 = complex2.INF.nthRoot(0);
                fail();
            } catch (org.apache.commons.math.exception.NotPositiveException e) {
                // Successfully thrown exception
            }
        } else {
            default7 = complex2.INF.nthRoot(0);
        }
    }

    @Test(timeout = 4000)
    public void test0398() throws Throwable {
        Complex complex0 = Complex.ONE;
        Complex complex1 = complex0.INF.negate();
        Complex complex2 = new Complex(125.255577);
        Complex complex3 = Complex.ONE;
        Complex.valueOf(125.255577, Double.NaN);
        complex0.INF.pow(complex3);
        Complex complex4 = complex1.pow(complex2);
        complex4.ZERO.add(complex2);
        Complex complex5 = complex0.ONE.subtract(complex1);
        complex5.divide(0.0);
        Complex.valueOf(0.0, 836.3305);
        complex0.ZERO.pow(0.0);
        Complex complex6 = Complex.I;
        complex6.NaN.tanh();
        Complex complex7 = complex0.multiply(complex6);
        complex0.INF.tanh();
        java.util.List default8;
        if ((-472) <= 0) {
            try {
                default8 = complex7.I.nthRoot((-472));
                fail();
            } catch (org.apache.commons.math.exception.NotPositiveException e) {
                // Successfully thrown exception
            }
        } else {
            default8 = complex7.I.nthRoot((-472));
        }
    }

    @Test(timeout = 4000)
    public void test0419() throws Throwable {
        Complex complex0 = Complex.I;
        complex0.I.hashCode();
        complex0.getReal();
        Complex complex1 = complex0.negate();
        Complex complex2 = complex1.tanh();
        complex0.INF.pow(complex1);
        Complex complex3 = complex1.createComplex(0.14982303977012634, 0.0);
        complex1.ONE.divide(0.14982303977012634);
        Complex complex4 = complex2.pow(0.0);
        Complex complex5 = complex4.ZERO.sqrt();
        complex2.hashCode();
        Complex complex6 = complex3.sin();
        Complex complex7 = complex6.sqrt();
        complex7.ONE.sinh();
        Complex complex8 = complex5.divide(2315.662);
        complex8.I.sinh();
        java.util.List default9;
        if (0 <= 0) {
            try {
                default9 = complex5.nthRoot(0);
                fail();
            } catch (org.apache.commons.math.exception.NotPositiveException e) {
                // Successfully thrown exception
            }
        } else {
            default9 = complex5.nthRoot(0);
        }
    }

    @Test(timeout = 4000)
    public void test04810() throws Throwable {
        double double0 = 2.718281828459045;
        Complex complex0 = new Complex(2.718281828459045, 2.718281828459045);
        Complex complex1 = Complex.valueOf(2.718281828459045, 0.0);
        Complex complex2 = complex0.pow(complex1);
        Complex complex3 = Complex.valueOf(2.718281828459045);
        java.util.List default10;
        if (1827 <= 0) {
            try {
                default10 = complex3.NaN.nthRoot(1827);
                fail();
            } catch (org.apache.commons.math.exception.NotPositiveException e) {
                // Successfully thrown exception
            }
        } else {
            default10 = complex3.NaN.nthRoot(1827);
        }
        complex0.toString();
        Complex complex4 = complex1.acos();
        complex2.ZERO.createComplex(0.0, 6.283185307179586);
        Complex.valueOf(2.718281828459045, 0.0);
        complex2.subtract(0.0);
        Complex complex5 = complex4.sinh();
        java.util.List default11;
        if ((-1) <= 0) {
            try {
                default11 = complex5.ONE.nthRoot((-1));
                fail();
            } catch (org.apache.commons.math.exception.NotPositiveException e) {
                // Successfully thrown exception
            }
        } else {
            default11 = complex5.ONE.nthRoot((-1));
        }
    }

    @Test(timeout = 4000)
    public void test08211() throws Throwable {
        Complex complex0 = Complex.I;
        Complex complex1 = complex0.NaN.multiply((-0.7853981633974483));
        complex0.ZERO.toString();
        complex0.sqrt1z();
        java.util.List default12;
        if (0 <= 0) {
            try {
                default12 = complex1.nthRoot(0);
                fail();
            } catch (org.apache.commons.math.exception.NotPositiveException e) {
                // Successfully thrown exception
            }
        } else {
            default12 = complex1.nthRoot(0);
        }
    }

    @Test(timeout = 4000)
    public void test09212() throws Throwable {
        Complex complex0 = Complex.ZERO;
        org.apache.commons.math.complex.Complex default13;
        if ((Complex) null == null) {
            try {
                default13 = complex0.INF.subtract((Complex) null);
                fail();
            } catch (org.apache.commons.math.exception.NullArgumentException e) {
                // Successfully thrown exception
            }
        } else {
            default13 = complex0.INF.subtract((Complex) null);
        }
    }

    @Test(timeout = 4000)
    public void test10513() throws Throwable {
        double double0 = (-3330.0);
        Complex complex0 = new Complex((-3330.0), 2597.88);
        Complex complex1 = Complex.INF;
        Complex complex2 = complex0.subtract(complex1);
        Complex complex3 = complex1.NaN.subtract(complex0);
        complex3.ONE.multiply(complex1);
        complex0.abs();
        Complex complex4 = Complex.valueOf((-3330.0));
        Complex complex5 = complex1.INF.divide(complex0);
        complex5.INF.multiply(complex1);
        complex1.divide(complex0);
        complex2.exp();
        complex3.I.tanh();
        Complex complex6 = complex0.acos();
        complex6.divide(complex1);
        complex1.ONE.tan();
        Complex complex7 = complex3.add(complex2);
        complex7.I.tanh();
        complex4.conjugate();
        complex6.ONE.log();
        double double1 = (-2.647648);
        Complex complex8 = Complex.valueOf((-2.647648), (-1645.2035586420611));
        complex8.I.negate();
        int int0 = (-1968);
        java.util.List default14;
        if ((-1968) <= 0) {
            try {
                default14 = complex2.nthRoot((-1968));
                fail();
            } catch (org.apache.commons.math.exception.NotPositiveException e) {
                // Successfully thrown exception
            }
        } else {
            default14 = complex2.nthRoot((-1968));
        }
    }

    @Test(timeout = 4000)
    public void test10714() throws Throwable {
        Complex complex0 = Complex.ONE;
        Complex complex1 = new Complex(125.255577);
        Complex complex2 = Complex.ONE;
        Complex.valueOf(125.255577, Double.NaN);
        complex0.INF.pow(complex2);
        complex0.pow(complex1);
        Complex complex3 = complex0.ONE.subtract(complex0);
        complex3.divide(0.0);
        Complex.valueOf(0.0, 836.3305);
        complex0.ZERO.pow(0.0);
        Complex complex4 = Complex.I;
        complex4.NaN.tanh();
        Complex complex5 = complex0.multiply(complex4);
        complex0.INF.tanh();
        java.util.List default15;
        if ((-472) <= 0) {
            try {
                default15 = complex5.I.nthRoot((-472));
                fail();
            } catch (org.apache.commons.math.exception.NotPositiveException e) {
                // Successfully thrown exception
            }
        } else {
            default15 = complex5.I.nthRoot((-472));
        }
    }

    @Test(timeout = 4000)
    public void test10815() throws Throwable {
        Complex complex0 = Complex.NaN;
        Complex complex1 = Complex.I;
        Complex complex2 = complex0.divide(complex1);
        complex2.INF.getField();
        Complex complex3 = complex0.multiply(1.3245471311735498E-8);
        Complex complex4 = Complex.valueOf(0.0);
        complex4.ZERO.negate();
        complex0.I.log();
        Complex complex5 = complex3.negate();
        complex1.ONE.abs();
        Complex complex6 = complex0.sin();
        Complex complex7 = Complex.valueOf(1.3245471311735498E-8, 1.3245471311735498E-8);
        complex0.equals(complex7);
        complex6.negate();
        complex2.hashCode();
        complex6.pow(1.3245471311735498E-8);
        complex5.sinh();
        java.util.List default16;
        if (0 <= 0) {
            try {
                default16 = complex3.nthRoot(0);
                fail();
            } catch (org.apache.commons.math.exception.NotPositiveException e) {
                // Successfully thrown exception
            }
        } else {
            default16 = complex3.nthRoot(0);
        }
    }

    @Test(timeout = 4000)
    public void test11016() throws Throwable {
        Complex complex0 = new Complex(1015.7, Double.POSITIVE_INFINITY);
        complex0.NaN.pow(0.16666666666666666);
        Complex complex1 = complex0.sin();
        Complex complex2 = complex0.exp();
        complex2.ZERO.cos();
        Complex complex3 = complex0.pow(complex1);
        complex0.INF.cosh();
        Complex complex4 = complex3.tan();
        complex4.NaN.getArgument();
        Complex complex5 = complex1.createComplex((-50.36), Double.POSITIVE_INFINITY);
        Complex complex6 = complex1.conjugate();
        Complex complex7 = complex3.multiply(1015.7);
        complex7.NaN.sqrt();
        Complex complex8 = complex7.INF.acos();
        complex3.abs();
        Complex complex9 = complex5.asin();
        complex9.NaN.cos();
        Complex.valueOf(Double.NaN, 0.0);
        complex0.ONE.asin();
        java.util.List default17;
        if (47 <= 0) {
            try {
                default17 = complex6.nthRoot(47);
                fail();
            } catch (org.apache.commons.math.exception.NotPositiveException e) {
                // Successfully thrown exception
            }
        } else {
            default17 = complex6.nthRoot(47);
        }
        complex0.toString();
        complex8.exp();
        complex1.conjugate();
        java.util.List default18;
        if ((-1) <= 0) {
            try {
                default18 = complex5.nthRoot((-1));
                fail();
            } catch (org.apache.commons.math.exception.NotPositiveException e) {
                // Successfully thrown exception
            }
        } else {
            default18 = complex5.nthRoot((-1));
        }
    }

    @Test(timeout = 4000)
    public void test11317() throws Throwable {
        Complex complex0 = Complex.ZERO;
        Complex complex1 = complex0.I.sin();
        complex1.INF.divide(complex0);
        complex1.I.abs();
        Complex complex2 = complex1.ONE.tan();
        complex2.toString();
        complex0.multiply(0.0);
        complex1.getArgument();
        Complex complex3 = complex1.createComplex(0.0, 1.5707963267948966);
        Complex complex4 = complex1.multiply((-1837.5053));
        complex1.getField();
        Complex complex5 = complex4.INF.cos();
        complex3.pow(0.0);
        Complex complex6 = complex2.negate();
        Complex complex7 = Complex.valueOf(0.0, 0.0);
        Complex complex8 = complex6.ONE.multiply(complex7);
        complex7.multiply((-1.0000003035640237));
        complex8.NaN.cosh();
        complex3.INF.pow(complex5);
        complex8.cos();
        java.util.List default19;
        if (0 <= 0) {
            try {
                default19 = complex6.nthRoot(0);
                fail();
            } catch (org.apache.commons.math.exception.NotPositiveException e) {
                // Successfully thrown exception
            }
        } else {
            default19 = complex6.nthRoot(0);
        }
    }

    @Test(timeout = 4000)
    public void test11518() throws Throwable {
        Complex complex0 = new Complex(3294198.0, 3294198.0);
        Complex complex1 = complex0.I.conjugate();
        Complex complex2 = complex0.ONE.divide(3294198.0);
        java.util.List list0;
        if (3814 <= 0) {
            try {
                list0 = complex2.ONE.nthRoot(3814);
                fail();
            } catch (org.apache.commons.math.exception.NotPositiveException e) {
                // Successfully thrown exception
            }
        } else {
            list0 = complex2.ONE.nthRoot(3814);
        }
        list0.size();
    }

    @Test(timeout = 4000)
    public void test11519() throws Throwable {
        Complex complex0 = new Complex(3294198.0, 3294198.0);
        Complex complex1 = complex0.I.conjugate();
        Complex complex2 = complex0.ONE.divide(3294198.0);
        java.util.List list0;
        if (3814 <= 0) {
            try {
                list0 = complex2.ONE.nthRoot(3814);
                fail();
            } catch (org.apache.commons.math.exception.NotPositiveException e) {
                // Successfully thrown exception
            }
        } else {
            list0 = complex2.ONE.nthRoot(3814);
        }
        Complex complex3 = Complex.NaN;
        Complex complex4 = complex2.cos();
        Complex complex5 = complex4.NaN.add(8.0E298);
        Complex complex6 = complex0.subtract(complex3);
        complex3.isNaN();
        complex2.isInfinite();
        complex6.abs();
        complex2.abs();
        Complex complex7 = complex1.negate();
        Complex complex8 = complex0.ONE.sqrt1z();
        String string0 = complex7.ZERO.toString();
    }

    @Test(timeout = 4000)
    public void test11520() throws Throwable {
        Complex complex0 = new Complex(3294198.0, 3294198.0);
        Complex complex1 = complex0.I.conjugate();
        Complex complex2 = complex0.ONE.divide(3294198.0);
        java.util.List list0;
        if (3814 <= 0) {
            try {
                list0 = complex2.ONE.nthRoot(3814);
                fail();
            } catch (org.apache.commons.math.exception.NotPositiveException e) {
                // Successfully thrown exception
            }
        } else {
            list0 = complex2.ONE.nthRoot(3814);
        }
        Complex complex3 = Complex.NaN;
        Complex complex4 = complex2.cos();
        Complex complex5 = complex4.NaN.add(8.0E298);
        Complex complex6 = complex0.subtract(complex3);
        complex3.isNaN();
        complex2.isInfinite();
        complex6.abs();
        complex2.abs();
        Complex complex7 = complex1.negate();
        Complex complex8 = complex0.ONE.sqrt1z();
        String string0 = complex7.ZERO.toString();
        java.util.List default20;
        if (3814 <= 0) {
            try {
                default20 = complex6.nthRoot(3814);
                fail();
            } catch (org.apache.commons.math.exception.NotPositiveException e) {
                // Successfully thrown exception
            }
        } else {
            default20 = complex6.nthRoot(3814);
        }
        Complex complex9 = Complex.I;
        complex3.ZERO.add(3294198.0);
        complex3.I.add(2.0);
        complex4.subtract(complex9);
        complex4.getImaginary();
    }

    @Test(timeout = 4000)
    public void test11521() throws Throwable {
        Complex complex0 = new Complex(3294198.0, 3294198.0);
        Complex complex1 = complex0.I.conjugate();
        Complex complex2 = complex0.ONE.divide(3294198.0);
        java.util.List list0;
        if (3814 <= 0) {
            try {
                list0 = complex2.ONE.nthRoot(3814);
                fail();
            } catch (org.apache.commons.math.exception.NotPositiveException e) {
                // Successfully thrown exception
            }
        } else {
            list0 = complex2.ONE.nthRoot(3814);
        }
        Complex complex3 = Complex.NaN;
        Complex complex4 = complex2.cos();
        Complex complex5 = complex4.NaN.add(8.0E298);
        Complex complex6 = complex0.subtract(complex3);
        complex3.isNaN();
        complex2.isInfinite();
        complex6.abs();
        complex2.abs();
        Complex complex7 = complex1.negate();
        Complex complex8 = complex0.ONE.sqrt1z();
        String string0 = complex7.ZERO.toString();
        java.util.List default21;
        if (3814 <= 0) {
            try {
                default21 = complex6.nthRoot(3814);
                fail();
            } catch (org.apache.commons.math.exception.NotPositiveException e) {
                // Successfully thrown exception
            }
        } else {
            default21 = complex6.nthRoot(3814);
        }
        Complex complex9 = Complex.I;
        complex3.ZERO.add(3294198.0);
        complex3.I.add(2.0);
        complex4.subtract(complex9);
        complex4.getReal();
    }

    @Test(timeout = 4000)
    public void test11522() throws Throwable {
        Complex complex0 = new Complex(3294198.0, 3294198.0);
        Complex complex1 = complex0.I.conjugate();
        Complex complex2 = complex0.ONE.divide(3294198.0);
        java.util.List list0;
        if (3814 <= 0) {
            try {
                list0 = complex2.ONE.nthRoot(3814);
                fail();
            } catch (org.apache.commons.math.exception.NotPositiveException e) {
                // Successfully thrown exception
            }
        } else {
            list0 = complex2.ONE.nthRoot(3814);
        }
        Complex complex3 = Complex.NaN;
        Complex complex4 = complex2.cos();
        Complex complex5 = complex4.NaN.add(8.0E298);
        Complex complex6 = complex0.subtract(complex3);
        complex3.isNaN();
        complex2.isInfinite();
        complex6.abs();
        complex2.abs();
        Complex complex7 = complex1.negate();
        Complex complex8 = complex0.ONE.sqrt1z();
        String string0 = complex7.ZERO.toString();
        java.util.List default22;
        if (3814 <= 0) {
            try {
                default22 = complex6.nthRoot(3814);
                fail();
            } catch (org.apache.commons.math.exception.NotPositiveException e) {
                // Successfully thrown exception
            }
        } else {
            default22 = complex6.nthRoot(3814);
        }
        Complex complex9 = Complex.I;
        complex3.ZERO.add(3294198.0);
        complex3.I.add(2.0);
        complex4.subtract(complex9);
        Complex complex10 = complex9.sqrt();
        complex10.getImaginary();
    }

    @Test(timeout = 4000)
    public void test11523() throws Throwable {
        Complex complex0 = new Complex(3294198.0, 3294198.0);
        Complex complex1 = complex0.I.conjugate();
        Complex complex2 = complex0.ONE.divide(3294198.0);
        java.util.List list0;
        if (3814 <= 0) {
            try {
                list0 = complex2.ONE.nthRoot(3814);
                fail();
            } catch (org.apache.commons.math.exception.NotPositiveException e) {
                // Successfully thrown exception
            }
        } else {
            list0 = complex2.ONE.nthRoot(3814);
        }
        Complex complex3 = Complex.NaN;
        Complex complex4 = complex2.cos();
        Complex complex5 = complex4.NaN.add(8.0E298);
        Complex complex6 = complex0.subtract(complex3);
        complex3.isNaN();
        complex2.isInfinite();
        complex6.abs();
        complex2.abs();
        Complex complex7 = complex1.negate();
        Complex complex8 = complex0.ONE.sqrt1z();
        String string0 = complex7.ZERO.toString();
        java.util.List default23;
        if (3814 <= 0) {
            try {
                default23 = complex6.nthRoot(3814);
                fail();
            } catch (org.apache.commons.math.exception.NotPositiveException e) {
                // Successfully thrown exception
            }
        } else {
            default23 = complex6.nthRoot(3814);
        }
        Complex complex9 = Complex.I;
        complex3.ZERO.add(3294198.0);
        complex3.I.add(2.0);
        complex4.subtract(complex9);
        Complex complex10 = complex9.sqrt();
        Complex complex11 = complex7.acos();
        complex11.getImaginary();
    }

    @Test(timeout = 4000)
    public void test11524() throws Throwable {
        Complex complex0 = new Complex(3294198.0, 3294198.0);
        Complex complex1 = complex0.I.conjugate();
        Complex complex2 = complex0.ONE.divide(3294198.0);
        java.util.List list0;
        if (3814 <= 0) {
            try {
                list0 = complex2.ONE.nthRoot(3814);
                fail();
            } catch (org.apache.commons.math.exception.NotPositiveException e) {
                // Successfully thrown exception
            }
        } else {
            list0 = complex2.ONE.nthRoot(3814);
        }
        Complex complex3 = Complex.NaN;
        Complex complex4 = complex2.cos();
        Complex complex5 = complex4.NaN.add(8.0E298);
        Complex complex6 = complex0.subtract(complex3);
        complex3.isNaN();
        complex2.isInfinite();
        complex6.abs();
        complex2.abs();
        Complex complex7 = complex1.negate();
        Complex complex8 = complex0.ONE.sqrt1z();
        String string0 = complex7.ZERO.toString();
        java.util.List default24;
        if (3814 <= 0) {
            try {
                default24 = complex6.nthRoot(3814);
                fail();
            } catch (org.apache.commons.math.exception.NotPositiveException e) {
                // Successfully thrown exception
            }
        } else {
            default24 = complex6.nthRoot(3814);
        }
        Complex complex9 = Complex.I;
        complex3.ZERO.add(3294198.0);
        complex3.I.add(2.0);
        complex4.subtract(complex9);
        Complex complex10 = complex9.sqrt();
        Complex complex11 = complex7.acos();
        Complex complex12 = complex8.atan();
        complex12.getImaginary();
    }

    @Test(timeout = 4000)
    public void test11525() throws Throwable {
        Complex complex0 = new Complex(3294198.0, 3294198.0);
        Complex complex1 = complex0.I.conjugate();
        Complex complex2 = complex0.ONE.divide(3294198.0);
        java.util.List list0;
        if (3814 <= 0) {
            try {
                list0 = complex2.ONE.nthRoot(3814);
                fail();
            } catch (org.apache.commons.math.exception.NotPositiveException e) {
                // Successfully thrown exception
            }
        } else {
            list0 = complex2.ONE.nthRoot(3814);
        }
        Complex complex3 = Complex.NaN;
        Complex complex4 = complex2.cos();
        Complex complex5 = complex4.NaN.add(8.0E298);
        Complex complex6 = complex0.subtract(complex3);
        complex3.isNaN();
        complex2.isInfinite();
        complex6.abs();
        complex2.abs();
        Complex complex7 = complex1.negate();
        Complex complex8 = complex0.ONE.sqrt1z();
        String string0 = complex7.ZERO.toString();
        java.util.List default25;
        if (3814 <= 0) {
            try {
                default25 = complex6.nthRoot(3814);
                fail();
            } catch (org.apache.commons.math.exception.NotPositiveException e) {
                // Successfully thrown exception
            }
        } else {
            default25 = complex6.nthRoot(3814);
        }
        Complex complex9 = Complex.I;
        complex3.ZERO.add(3294198.0);
        complex3.I.add(2.0);
        complex4.subtract(complex9);
        Complex complex10 = complex9.sqrt();
        Complex complex11 = complex7.acos();
        Complex complex12 = complex8.atan();
        complex8.conjugate();
        Complex complex13 = complex6.atan();
    }

    @Test(timeout = 4000)
    public void test11526() throws Throwable {
        Complex complex0 = new Complex(3294198.0, 3294198.0);
        Complex complex1 = complex0.I.conjugate();
        Complex complex2 = complex0.ONE.divide(3294198.0);
        java.util.List list0;
        if (3814 <= 0) {
            try {
                list0 = complex2.ONE.nthRoot(3814);
                fail();
            } catch (org.apache.commons.math.exception.NotPositiveException e) {
                // Successfully thrown exception
            }
        } else {
            list0 = complex2.ONE.nthRoot(3814);
        }
        Complex complex3 = Complex.NaN;
        Complex complex4 = complex2.cos();
        Complex complex5 = complex4.NaN.add(8.0E298);
        Complex complex6 = complex0.subtract(complex3);
        complex3.isNaN();
        complex2.isInfinite();
        complex6.abs();
        complex2.abs();
        Complex complex7 = complex1.negate();
        Complex complex8 = complex0.ONE.sqrt1z();
        String string0 = complex7.ZERO.toString();
        java.util.List default26;
        if (3814 <= 0) {
            try {
                default26 = complex6.nthRoot(3814);
                fail();
            } catch (org.apache.commons.math.exception.NotPositiveException e) {
                // Successfully thrown exception
            }
        } else {
            default26 = complex6.nthRoot(3814);
        }
        Complex complex9 = Complex.I;
        complex3.ZERO.add(3294198.0);
        complex3.I.add(2.0);
        complex4.subtract(complex9);
        Complex complex10 = complex9.sqrt();
        Complex complex11 = complex7.acos();
        Complex complex12 = complex8.atan();
        complex8.conjugate();
        Complex complex13 = complex6.atan();
        Complex complex14 = complex2.atan();
        complex14.getImaginary();
    }

    @Test(timeout = 4000)
    public void test11527() throws Throwable {
        Complex complex0 = new Complex(3294198.0, 3294198.0);
        Complex complex1 = complex0.I.conjugate();
        Complex complex2 = complex0.ONE.divide(3294198.0);
        java.util.List list0;
        if (3814 <= 0) {
            try {
                list0 = complex2.ONE.nthRoot(3814);
                fail();
            } catch (org.apache.commons.math.exception.NotPositiveException e) {
                // Successfully thrown exception
            }
        } else {
            list0 = complex2.ONE.nthRoot(3814);
        }
        Complex complex3 = Complex.NaN;
        Complex complex4 = complex2.cos();
        Complex complex5 = complex4.NaN.add(8.0E298);
        Complex complex6 = complex0.subtract(complex3);
        complex3.isNaN();
        complex2.isInfinite();
        complex6.abs();
        complex2.abs();
        Complex complex7 = complex1.negate();
        Complex complex8 = complex0.ONE.sqrt1z();
        String string0 = complex7.ZERO.toString();
        java.util.List default27;
        if (3814 <= 0) {
            try {
                default27 = complex6.nthRoot(3814);
                fail();
            } catch (org.apache.commons.math.exception.NotPositiveException e) {
                // Successfully thrown exception
            }
        } else {
            default27 = complex6.nthRoot(3814);
        }
        Complex complex9 = Complex.I;
        complex3.ZERO.add(3294198.0);
        complex3.I.add(2.0);
        complex4.subtract(complex9);
        Complex complex10 = complex9.sqrt();
        Complex complex11 = complex7.acos();
        Complex complex12 = complex8.atan();
        complex8.conjugate();
        Complex complex13 = complex6.atan();
        Complex complex14 = complex2.atan();
        complex14.getReal();
    }

    @Test(timeout = 4000)
    public void test11528() throws Throwable {
        Complex complex0 = new Complex(3294198.0, 3294198.0);
        Complex complex1 = complex0.I.conjugate();
        Complex complex2 = complex0.ONE.divide(3294198.0);
        java.util.List list0;
        if (3814 <= 0) {
            try {
                list0 = complex2.ONE.nthRoot(3814);
                fail();
            } catch (org.apache.commons.math.exception.NotPositiveException e) {
                // Successfully thrown exception
            }
        } else {
            list0 = complex2.ONE.nthRoot(3814);
        }
        Complex complex3 = Complex.NaN;
        Complex complex4 = complex2.cos();
        Complex complex5 = complex4.NaN.add(8.0E298);
        Complex complex6 = complex0.subtract(complex3);
        complex3.isNaN();
        complex2.isInfinite();
        complex6.abs();
        complex2.abs();
        Complex complex7 = complex1.negate();
        Complex complex8 = complex0.ONE.sqrt1z();
        String string0 = complex7.ZERO.toString();
        java.util.List default28;
        if (3814 <= 0) {
            try {
                default28 = complex6.nthRoot(3814);
                fail();
            } catch (org.apache.commons.math.exception.NotPositiveException e) {
                // Successfully thrown exception
            }
        } else {
            default28 = complex6.nthRoot(3814);
        }
        Complex complex9 = Complex.I;
        complex3.ZERO.add(3294198.0);
        complex3.I.add(2.0);
        complex4.subtract(complex9);
        Complex complex10 = complex9.sqrt();
        Complex complex11 = complex7.acos();
        Complex complex12 = complex8.atan();
        complex8.conjugate();
        Complex complex13 = complex6.atan();
        Complex complex14 = complex2.atan();
        complex6.createComplex(2.0, 5.861389442564046);
        complex3.equals((Object) complex8);
    }
}
