/*
 * This file was automatically generated by EvoSuite
 * Mon Oct 16 15:22:03 GMT 2023
 */
package org.apache.commons.math.complex;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import org.apache.commons.math.complex.Complex;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class Complex_ESTest extends Complex_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test010() throws Throwable {
        Complex complex0 = Complex.NaN;
        Complex complex1 = Complex.NaN;
        Complex complex2 = complex0.subtract(complex1);
        Complex complex3 = complex2.tan();
        complex3.subtract(complex2);
        double double0 = complex3.abs();
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        Complex complex0 = Complex.NaN;
        Complex complex1 = Complex.NaN;
        Complex complex2 = complex0.subtract(complex1);
        Complex complex3 = complex2.tan();
        complex3.subtract(complex2);
        double double0 = complex3.abs();
        Complex complex4 = complex3.tanh();
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        Complex complex0 = Complex.INF;
        Complex complex1 = complex0.I.cosh();
        complex1.isInfinite();
    }

    @Test(timeout = 4000)
    public void test023() throws Throwable {
        Complex complex0 = Complex.INF;
        Complex complex1 = complex0.I.cosh();
        complex1.getReal();
    }

    @Test(timeout = 4000)
    public void test024() throws Throwable {
        Complex complex0 = Complex.INF;
        Complex complex1 = complex0.I.cosh();
        complex1.getImaginary();
    }

    @Test(timeout = 4000)
    public void test025() throws Throwable {
        Complex complex0 = Complex.INF;
        Complex complex1 = complex0.I.cosh();
        Complex complex2 = complex0.NaN.tan();
        Complex complex3 = complex0.add(1.0);
        Complex complex4 = complex0.subtract(0.0);
        complex4.INF.cosh();
        Complex complex5 = complex4.cos();
        complex4.equals((Object) complex3);
    }

    @Test(timeout = 4000)
    public void test026() throws Throwable {
        Complex complex0 = Complex.INF;
        Complex complex1 = complex0.I.cosh();
        Complex complex2 = complex0.NaN.tan();
        Complex complex3 = complex0.add(1.0);
        Complex complex4 = complex0.subtract(0.0);
        complex4.INF.cosh();
        Complex complex5 = complex4.cos();
    }

    @Test(timeout = 4000)
    public void test027() throws Throwable {
        Complex complex0 = Complex.INF;
        Complex complex1 = complex0.I.cosh();
        Complex complex2 = complex0.NaN.tan();
        Complex complex3 = complex0.add(1.0);
        Complex complex4 = complex0.subtract(0.0);
        complex4.INF.cosh();
        Complex complex5 = complex4.cos();
        complex0.equals((Object) complex4);
    }

    @Test(timeout = 4000)
    public void test028() throws Throwable {
        Complex complex0 = Complex.INF;
        Complex complex1 = complex0.I.cosh();
        Complex complex2 = complex0.NaN.tan();
        Complex complex3 = complex0.add(1.0);
        Complex complex4 = complex0.subtract(0.0);
        complex4.INF.cosh();
        Complex complex5 = complex4.cos();
        complex4.isInfinite();
    }

    @Test(timeout = 4000)
    public void test029() throws Throwable {
        Complex complex0 = Complex.INF;
        Complex complex1 = complex0.I.cosh();
        Complex complex2 = complex0.NaN.tan();
        Complex complex3 = complex0.add(1.0);
        Complex complex4 = complex0.subtract(0.0);
        complex4.INF.cosh();
        Complex complex5 = complex4.cos();
        complex5.equals((Object) complex2);
    }

    @Test(timeout = 4000)
    public void test0310() throws Throwable {
        Complex complex0 = new Complex(1.0, 1.0);
        Complex complex1 = complex0.multiply(0.0);
        Complex complex2 = complex1.ZERO.conjugate();
        complex2.I.createComplex((-618.01996014), 0.0);
        Complex complex3 = complex0.NaN.exp();
        Complex complex4 = complex1.INF.divide(0.0);
        Complex complex5 = complex1.divide(0.0);
        Complex complex6 = complex0.log();
        complex6.getReal();
    }

    @Test(timeout = 4000)
    public void test0311() throws Throwable {
        Complex complex0 = new Complex(1.0, 1.0);
        Complex complex1 = complex0.multiply(0.0);
        Complex complex2 = complex1.ZERO.conjugate();
        complex2.I.createComplex((-618.01996014), 0.0);
        Complex complex3 = complex0.NaN.exp();
        Complex complex4 = complex1.INF.divide(0.0);
        Complex complex5 = complex1.divide(0.0);
        Complex complex6 = complex0.log();
        Complex complex7 = complex1.sin();
        Complex complex8 = complex0.INF.sqrt();
        Complex complex9 = complex0.NaN.tan();
        complex7.I.getArgument();
        complex5.atan();
        Complex complex10 = complex5.conjugate();
        Complex complex11 = complex0.INF.multiply(complex3);
        Complex complex12 = complex10.INF.cosh();
        Complex complex13 = complex12.I.pow(1.0);
        Complex complex14 = complex7.negate();
        complex14.getImaginary();
    }

    @Test(timeout = 4000)
    public void test0312() throws Throwable {
        Complex complex0 = new Complex(1.0, 1.0);
        Complex complex1 = complex0.multiply(0.0);
        Complex complex2 = complex1.ZERO.conjugate();
        complex2.I.createComplex((-618.01996014), 0.0);
        Complex complex3 = complex0.NaN.exp();
        Complex complex4 = complex1.INF.divide(0.0);
        Complex complex5 = complex1.divide(0.0);
        Complex complex6 = complex0.log();
        Complex complex7 = complex1.sin();
        Complex complex8 = complex0.INF.sqrt();
        Complex complex9 = complex0.NaN.tan();
        complex7.I.getArgument();
        complex5.atan();
        Complex complex10 = complex5.conjugate();
        Complex complex11 = complex0.INF.multiply(complex3);
        Complex complex12 = complex10.INF.cosh();
        Complex complex13 = complex12.I.pow(1.0);
        Complex complex14 = complex7.negate();
        complex14.getReal();
    }

    @Test(timeout = 4000)
    public void test0313() throws Throwable {
        Complex complex0 = new Complex(1.0, 1.0);
        Complex complex1 = complex0.multiply(0.0);
        Complex complex2 = complex1.ZERO.conjugate();
        complex2.I.createComplex((-618.01996014), 0.0);
        Complex complex3 = complex0.NaN.exp();
        Complex complex4 = complex1.INF.divide(0.0);
        Complex complex5 = complex1.divide(0.0);
        Complex complex6 = complex0.log();
        Complex complex7 = complex1.sin();
        Complex complex8 = complex0.INF.sqrt();
        Complex complex9 = complex0.NaN.tan();
        complex7.I.getArgument();
        complex5.atan();
        Complex complex10 = complex5.conjugate();
        Complex complex11 = complex0.INF.multiply(complex3);
        Complex complex12 = complex10.INF.cosh();
        Complex complex13 = complex12.I.pow(1.0);
        Complex complex14 = complex7.negate();
        complex7.getImaginary();
        Complex complex15 = complex7.add(complex0);
        Complex complex16 = complex1.log();
        Complex complex17 = complex12.multiply(0.0);
        Complex complex18 = complex17.I.pow(1.5707963267948966);
        Complex complex19 = Complex.valueOf(4734.7381062603);
        complex19.INF.exp();
        Complex complex20 = complex9.divide(1.0);
        Complex complex21 = complex19.multiply(1.0);
        complex20.equals(complex21);
        complex9.multiply(complex13);
    }

    @Test(timeout = 4000)
    public void test0314() throws Throwable {
        Complex complex0 = new Complex(1.0, 1.0);
        Complex complex1 = complex0.multiply(0.0);
        Complex complex2 = complex1.ZERO.conjugate();
        complex2.I.createComplex((-618.01996014), 0.0);
        Complex complex3 = complex0.NaN.exp();
        Complex complex4 = complex1.INF.divide(0.0);
        Complex complex5 = complex1.divide(0.0);
        Complex complex6 = complex0.log();
        Complex complex7 = complex1.sin();
        Complex complex8 = complex0.INF.sqrt();
        Complex complex9 = complex0.NaN.tan();
        complex7.I.getArgument();
        complex5.atan();
        Complex complex10 = complex5.conjugate();
        Complex complex11 = complex0.INF.multiply(complex3);
        Complex complex12 = complex10.INF.cosh();
        Complex complex13 = complex12.I.pow(1.0);
        Complex complex14 = complex7.negate();
        complex7.getImaginary();
        Complex complex15 = complex7.add(complex0);
        Complex complex16 = complex1.log();
        Complex complex17 = complex12.multiply(0.0);
        Complex complex18 = complex17.I.pow(1.5707963267948966);
        Complex complex19 = Complex.valueOf(4734.7381062603);
        complex19.INF.exp();
        Complex complex20 = complex9.divide(1.0);
        Complex complex21 = complex19.multiply(1.0);
        complex20.equals(complex21);
        complex9.multiply(complex13);
        Complex complex22 = complex21.tanh();
        Complex complex23 = complex22.INF.pow(complex16);
        complex22.equals((Object) complex11);
    }

    @Test(timeout = 4000)
    public void test0315() throws Throwable {
        Complex complex0 = new Complex(1.0, 1.0);
        Complex complex1 = complex0.multiply(0.0);
        Complex complex2 = complex1.ZERO.conjugate();
        complex2.I.createComplex((-618.01996014), 0.0);
        Complex complex3 = complex0.NaN.exp();
        Complex complex4 = complex1.INF.divide(0.0);
        Complex complex5 = complex1.divide(0.0);
        Complex complex6 = complex0.log();
        Complex complex7 = complex1.sin();
        Complex complex8 = complex0.INF.sqrt();
        Complex complex9 = complex0.NaN.tan();
        complex7.I.getArgument();
        complex5.atan();
        Complex complex10 = complex5.conjugate();
        Complex complex11 = complex0.INF.multiply(complex3);
        Complex complex12 = complex10.INF.cosh();
        Complex complex13 = complex12.I.pow(1.0);
        Complex complex14 = complex7.negate();
        complex7.getImaginary();
        Complex complex15 = complex7.add(complex0);
        Complex complex16 = complex1.log();
        Complex complex17 = complex12.multiply(0.0);
        Complex complex18 = complex17.I.pow(1.5707963267948966);
        Complex complex19 = Complex.valueOf(4734.7381062603);
        complex19.INF.exp();
        Complex complex20 = complex9.divide(1.0);
        Complex complex21 = complex19.multiply(1.0);
        complex20.equals(complex21);
        complex9.multiply(complex13);
        Complex complex22 = complex21.tanh();
        Complex complex23 = complex22.INF.pow(complex16);
        complex22.getImaginary();
    }

    @Test(timeout = 4000)
    public void test0316() throws Throwable {
        Complex complex0 = new Complex(1.0, 1.0);
        Complex complex1 = complex0.multiply(0.0);
        Complex complex2 = complex1.ZERO.conjugate();
        complex2.I.createComplex((-618.01996014), 0.0);
        Complex complex3 = complex0.NaN.exp();
        Complex complex4 = complex1.INF.divide(0.0);
        Complex complex5 = complex1.divide(0.0);
        Complex complex6 = complex0.log();
        Complex complex7 = complex1.sin();
        Complex complex8 = complex0.INF.sqrt();
        Complex complex9 = complex0.NaN.tan();
        complex7.I.getArgument();
        complex5.atan();
        Complex complex10 = complex5.conjugate();
        Complex complex11 = complex0.INF.multiply(complex3);
        Complex complex12 = complex10.INF.cosh();
        Complex complex13 = complex12.I.pow(1.0);
        Complex complex14 = complex7.negate();
        complex7.getImaginary();
        Complex complex15 = complex7.add(complex0);
        Complex complex16 = complex1.log();
        Complex complex17 = complex12.multiply(0.0);
        Complex complex18 = complex17.I.pow(1.5707963267948966);
        Complex complex19 = Complex.valueOf(4734.7381062603);
        complex19.INF.exp();
        Complex complex20 = complex9.divide(1.0);
        Complex complex21 = complex19.multiply(1.0);
        complex20.equals(complex21);
        complex9.multiply(complex13);
        Complex complex22 = complex21.tanh();
        Complex complex23 = complex22.INF.pow(complex16);
    }

    @Test(timeout = 4000)
    public void test0317() throws Throwable {
        Complex complex0 = new Complex(1.0, 1.0);
        Complex complex1 = complex0.multiply(0.0);
        Complex complex2 = complex1.ZERO.conjugate();
        complex2.I.createComplex((-618.01996014), 0.0);
        Complex complex3 = complex0.NaN.exp();
        Complex complex4 = complex1.INF.divide(0.0);
        Complex complex5 = complex1.divide(0.0);
        Complex complex6 = complex0.log();
        Complex complex7 = complex1.sin();
        Complex complex8 = complex0.INF.sqrt();
        Complex complex9 = complex0.NaN.tan();
        complex7.I.getArgument();
        complex5.atan();
        Complex complex10 = complex5.conjugate();
        Complex complex11 = complex0.INF.multiply(complex3);
        Complex complex12 = complex10.INF.cosh();
        Complex complex13 = complex12.I.pow(1.0);
        Complex complex14 = complex7.negate();
        complex7.getImaginary();
        Complex complex15 = complex7.add(complex0);
        Complex complex16 = complex1.log();
        Complex complex17 = complex12.multiply(0.0);
        Complex complex18 = complex17.I.pow(1.5707963267948966);
        Complex complex19 = Complex.valueOf(4734.7381062603);
        complex19.INF.exp();
        Complex complex20 = complex9.divide(1.0);
        Complex complex21 = complex19.multiply(1.0);
        complex20.equals(complex21);
        complex9.multiply(complex13);
        Complex complex22 = complex21.tanh();
        Complex complex23 = complex22.INF.pow(complex16);
        String string0 = complex8.toString();
        complex8.equals((Object) complex4);
    }

    @Test(timeout = 4000)
    public void test0318() throws Throwable {
        Complex complex0 = new Complex(1.0, 1.0);
        Complex complex1 = complex0.multiply(0.0);
        Complex complex2 = complex1.ZERO.conjugate();
        complex2.I.createComplex((-618.01996014), 0.0);
        Complex complex3 = complex0.NaN.exp();
        Complex complex4 = complex1.INF.divide(0.0);
        Complex complex5 = complex1.divide(0.0);
        Complex complex6 = complex0.log();
        Complex complex7 = complex1.sin();
        Complex complex8 = complex0.INF.sqrt();
        Complex complex9 = complex0.NaN.tan();
        complex7.I.getArgument();
        complex5.atan();
        Complex complex10 = complex5.conjugate();
        Complex complex11 = complex0.INF.multiply(complex3);
        Complex complex12 = complex10.INF.cosh();
        Complex complex13 = complex12.I.pow(1.0);
        Complex complex14 = complex7.negate();
        complex7.getImaginary();
        Complex complex15 = complex7.add(complex0);
        Complex complex16 = complex1.log();
        Complex complex17 = complex12.multiply(0.0);
        Complex complex18 = complex17.I.pow(1.5707963267948966);
        Complex complex19 = Complex.valueOf(4734.7381062603);
        complex19.INF.exp();
        Complex complex20 = complex9.divide(1.0);
        Complex complex21 = complex19.multiply(1.0);
        complex20.equals(complex21);
        complex9.multiply(complex13);
        Complex complex22 = complex21.tanh();
        Complex complex23 = complex22.INF.pow(complex16);
        String string0 = complex8.toString();
        complex0.equals((Object) complex15);
    }

    @Test(timeout = 4000)
    public void test0319() throws Throwable {
        Complex complex0 = new Complex(1.0, 1.0);
        Complex complex1 = complex0.multiply(0.0);
        Complex complex2 = complex1.ZERO.conjugate();
        complex2.I.createComplex((-618.01996014), 0.0);
        Complex complex3 = complex0.NaN.exp();
        Complex complex4 = complex1.INF.divide(0.0);
        Complex complex5 = complex1.divide(0.0);
        Complex complex6 = complex0.log();
        Complex complex7 = complex1.sin();
        Complex complex8 = complex0.INF.sqrt();
        Complex complex9 = complex0.NaN.tan();
        complex7.I.getArgument();
        complex5.atan();
        Complex complex10 = complex5.conjugate();
        Complex complex11 = complex0.INF.multiply(complex3);
        Complex complex12 = complex10.INF.cosh();
        Complex complex13 = complex12.I.pow(1.0);
        Complex complex14 = complex7.negate();
        complex7.getImaginary();
        Complex complex15 = complex7.add(complex0);
        Complex complex16 = complex1.log();
        Complex complex17 = complex12.multiply(0.0);
        Complex complex18 = complex17.I.pow(1.5707963267948966);
        Complex complex19 = Complex.valueOf(4734.7381062603);
        complex19.INF.exp();
        Complex complex20 = complex9.divide(1.0);
        Complex complex21 = complex19.multiply(1.0);
        complex20.equals(complex21);
        complex9.multiply(complex13);
        Complex complex22 = complex21.tanh();
        Complex complex23 = complex22.INF.pow(complex16);
        String string0 = complex8.toString();
        complex8.equals((Object) complex18);
    }

    @Test(timeout = 4000)
    public void test0320() throws Throwable {
        Complex complex0 = new Complex(1.0, 1.0);
        Complex complex1 = complex0.multiply(0.0);
        Complex complex2 = complex1.ZERO.conjugate();
        complex2.I.createComplex((-618.01996014), 0.0);
        Complex complex3 = complex0.NaN.exp();
        Complex complex4 = complex1.INF.divide(0.0);
        Complex complex5 = complex1.divide(0.0);
        Complex complex6 = complex0.log();
        Complex complex7 = complex1.sin();
        Complex complex8 = complex0.INF.sqrt();
        Complex complex9 = complex0.NaN.tan();
        complex7.I.getArgument();
        complex5.atan();
        Complex complex10 = complex5.conjugate();
        Complex complex11 = complex0.INF.multiply(complex3);
        Complex complex12 = complex10.INF.cosh();
        Complex complex13 = complex12.I.pow(1.0);
        Complex complex14 = complex7.negate();
        complex7.getImaginary();
        Complex complex15 = complex7.add(complex0);
        Complex complex16 = complex1.log();
        Complex complex17 = complex12.multiply(0.0);
        Complex complex18 = complex17.I.pow(1.5707963267948966);
        Complex complex19 = Complex.valueOf(4734.7381062603);
        complex19.INF.exp();
        Complex complex20 = complex9.divide(1.0);
        Complex complex21 = complex19.multiply(1.0);
        complex20.equals(complex21);
        complex9.multiply(complex13);
        Complex complex22 = complex21.tanh();
        Complex complex23 = complex22.INF.pow(complex16);
        String string0 = complex8.toString();
    }

    @Test(timeout = 4000)
    public void test0321() throws Throwable {
        Complex complex0 = new Complex(1.0, 1.0);
        Complex complex1 = complex0.multiply(0.0);
        Complex complex2 = complex1.ZERO.conjugate();
        complex2.I.createComplex((-618.01996014), 0.0);
        Complex complex3 = complex0.NaN.exp();
        Complex complex4 = complex1.INF.divide(0.0);
        Complex complex5 = complex1.divide(0.0);
        Complex complex6 = complex0.log();
        Complex complex7 = complex1.sin();
        Complex complex8 = complex0.INF.sqrt();
        Complex complex9 = complex0.NaN.tan();
        complex7.I.getArgument();
        complex5.atan();
        Complex complex10 = complex5.conjugate();
        Complex complex11 = complex0.INF.multiply(complex3);
        Complex complex12 = complex10.INF.cosh();
        Complex complex13 = complex12.I.pow(1.0);
        Complex complex14 = complex7.negate();
        complex7.getImaginary();
        Complex complex15 = complex7.add(complex0);
        Complex complex16 = complex1.log();
        Complex complex17 = complex12.multiply(0.0);
        Complex complex18 = complex17.I.pow(1.5707963267948966);
        Complex complex19 = Complex.valueOf(4734.7381062603);
        complex19.INF.exp();
        Complex complex20 = complex9.divide(1.0);
        Complex complex21 = complex19.multiply(1.0);
        complex20.equals(complex21);
        complex9.multiply(complex13);
        Complex complex22 = complex21.tanh();
        Complex complex23 = complex22.INF.pow(complex16);
        String string0 = complex8.toString();
        complex8.equals((Object) complex17);
    }

    @Test(timeout = 4000)
    public void test1722() throws Throwable {
        Complex complex0 = Complex.ZERO;
        org.apache.commons.math.complex.Complex default0;
        if ((Complex) null == null) {
            try {
                default0 = complex0.INF.subtract((Complex) null);
                fail();
            } catch (org.apache.commons.math.exception.NullArgumentException e) {
                // Successfully thrown exception
            }
        } else {
            default0 = complex0.INF.subtract((Complex) null);
        }
    }
}
