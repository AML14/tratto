/*
 * This file was automatically generated by EvoSuite
 * Mon Oct 16 15:50:34 GMT 2023
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
    public void test040() throws Throwable {
        Complex complex0 = Complex.I;
        complex0.add((Complex) null);
    }

    @Test(timeout = 4000)
    public void test051() throws Throwable {
        Complex complex0 = Complex.ZERO;
        Complex complex1 = complex0.INF.log();
        Complex complex2 = complex1.ZERO.negate();
        Complex complex3 = complex2.I.add(complex1);
        Complex complex4 = complex1.INF.atan();
        Complex complex5 = complex1.ONE.log();
        Complex complex6 = complex4.I.createComplex(1.979872122629245E8, 1.979872122629245E8);
        complex6.ONE.hashCode();
        complex5.toString();
        complex3.I.sqrt1z();
        complex0.abs();
        Complex complex7 = complex0.asin();
        Complex complex8 = complex4.divide(complex5);
        Object object0 = new Object();
        complex7.equals(object0);
        complex7.ZERO.atan();
        Complex complex9 = complex7.sqrt1z();
        Complex complex10 = complex5.sinh();
        complex9.cosh();
        Complex complex11 = complex7.subtract(complex5);
        complex11.ONE.cosh();
        complex3.readResolve();
        Complex complex12 = complex7.divide(complex9);
        complex12.NaN.hashCode();
        complex12.ZERO.sinh();
        complex11.ONE.asin();
        complex12.ONE.hashCode();
        complex5.getField();
        complex8.abs();
        complex11.sqrt1z();
        complex3.negate();
        complex10.getField();
        complex6.sin();
        complex11.nthRoot((-341));
    }

    @Test(timeout = 4000)
    public void test072() throws Throwable {
        Complex complex0 = Complex.I;
        Complex complex1 = complex0.I.cosh();
        Complex complex2 = complex1.ONE.add(complex0);
        Complex complex3 = complex0.cos();
        Complex complex4 = complex3.negate();
        complex3.ZERO.tanh();
        complex0.getField();
        complex3.NaN.getArgument();
        complex4.nthRoot(1);
        complex0.getField();
        complex2.ZERO.hashCode();
        Complex complex5 = complex0.subtract(complex3);
        Complex complex6 = complex5.I.sqrt();
        Complex complex7 = complex4.pow(complex0);
        complex7.getReal();
        complex0.hashCode();
        complex2.sqrt1z();
        complex6.log();
        complex0.createComplex(2122.161, 2122.161);
        Complex complex8 = complex4.tanh();
        complex8.ZERO.nthRoot(0);
    }

    @Test(timeout = 4000)
    public void test113() throws Throwable {
        Complex complex0 = new Complex(2.0, 2.0);
        complex0.ONE.divide((Complex) null);
    }

    @Test(timeout = 4000)
    public void test184() throws Throwable {
        Complex complex0 = Complex.I;
        complex0.conjugate();
        complex0.multiply((Complex) null);
    }

    @Test(timeout = 4000)
    public void test265() throws Throwable {
        Complex complex0 = Complex.ONE;
        Complex complex1 = complex0.ONE.sinh();
        complex0.NaN.getArgument();
        Complex complex2 = complex0.ONE.cos();
        complex2.NaN.sin();
        complex0.equals("{yd!<`NuF\"oaX$(y3HW");
        complex1.getReal();
        Complex complex3 = complex1.sqrt1z();
        Complex complex4 = complex3.INF.asin();
        complex3.INF.asin();
        complex0.ZERO.tanh();
        Complex complex5 = complex4.I.sqrt1z();
        complex4.NaN.hashCode();
        Complex complex6 = complex3.atan();
        complex5.ZERO.log();
        complex1.ONE.abs();
        Complex complex7 = complex6.NaN.exp();
        complex7.INF.multiply(complex1);
        complex4.I.tan();
        Complex complex8 = complex3.asin();
        complex8.NaN.tanh();
        Complex complex9 = complex6.negate();
        complex9.ONE.pow(complex8);
        Complex complex10 = complex9.tanh();
        complex10.I.divide(complex9);
        complex0.nthRoot((-1143));
    }

    @Test(timeout = 4000)
    public void test346() throws Throwable {
        Complex complex0 = Complex.ONE;
        Complex complex1 = complex0.ONE.log();
        complex1.cos();
        Complex complex2 = complex0.sqrt();
        Complex complex3 = complex1.asin();
        complex3.INF.toString();
        complex2.subtract(complex0);
        complex3.nthRoot((-106));
    }

    @Test(timeout = 4000)
    public void test437() throws Throwable {
        Complex complex0 = Complex.INF;
        complex0.INF.abs();
        complex0.I.sin();
        complex0.ONE.createComplex(6.283185307179586, 0.0);
        complex0.tan();
        complex0.subtract((Complex) null);
    }

    @Test(timeout = 4000)
    public void test608() throws Throwable {
        Complex complex0 = Complex.INF;
        complex0.ZERO.tanh();
        Complex complex1 = Complex.ZERO;
        Complex complex2 = complex0.add(complex1);
        complex2.NaN.readResolve();
        Complex complex3 = complex0.sinh();
        complex3.INF.nthRoot(0);
    }

    @Test(timeout = 4000)
    public void test649() throws Throwable {
        Complex complex0 = Complex.I;
        complex0.ZERO.nthRoot((-888));
    }

    @Test(timeout = 4000)
    public void test6510() throws Throwable {
        Complex complex0 = Complex.I;
        Complex complex1 = complex0.sqrt();
        Complex complex2 = complex0.I.add(complex1);
        complex2.INF.atan();
        complex0.getField();
        complex1.NaN.divide(complex0);
        Complex complex3 = complex1.cos();
        Complex complex4 = complex3.divide(complex1);
        complex4.NaN.pow((Complex) null);
    }

    @Test(timeout = 4000)
    public void test7511() throws Throwable {
        Complex complex0 = Complex.INF;
        Complex complex1 = new Complex((-3044.0), 0.0);
        Complex complex2 = complex0.add(complex1);
        Complex complex3 = complex0.log();
        Complex complex4 = complex0.INF.cosh();
        Complex complex5 = complex4.conjugate();
        complex5.createComplex((-3044.0), 0.0);
        Complex complex6 = complex2.log();
        Complex complex7 = complex2.multiply(10.0);
        complex7.hashCode();
        complex2.equals(complex0);
        complex3.abs();
        complex2.isNaN();
        Complex complex8 = complex6.exp();
        Complex complex9 = complex8.I.tan();
        complex8.asin();
        complex9.ONE.nthRoot((-2229));
    }

    @Test(timeout = 4000)
    public void test7712() throws Throwable {
        Complex complex0 = new Complex(1.0, 1.0);
        Complex complex1 = new Complex(331.594212, 0.0);
        Complex complex2 = complex1.ONE.add(complex0);
        complex2.getReal();
    }

    @Test(timeout = 4000)
    public void test7713() throws Throwable {
        Complex complex0 = new Complex(1.0, 1.0);
        Complex complex1 = new Complex(331.594212, 0.0);
        Complex complex2 = complex1.ONE.add(complex0);
        Complex complex3 = complex0.I.multiply(complex1);
        Complex complex4 = complex0.NaN.log();
        Complex complex5 = complex4.ONE.tan();
        String string0 = complex0.NaN.toString();
    }

    @Test(timeout = 4000)
    public void test7714() throws Throwable {
        Complex complex0 = new Complex(1.0, 1.0);
        Complex complex1 = new Complex(331.594212, 0.0);
        Complex complex2 = complex1.ONE.add(complex0);
        Complex complex3 = complex0.I.multiply(complex1);
        Complex complex4 = complex0.NaN.log();
        Complex complex5 = complex4.ONE.tan();
        String string0 = complex0.NaN.toString();
        Complex complex6 = complex0.sqrt();
        complex0.createComplex(0.0, 1.0);
        List<Complex> list0 = complex0.nthRoot(2645);
        list0.size();
    }

    @Test(timeout = 4000)
    public void test7715() throws Throwable {
        Complex complex0 = new Complex(1.0, 1.0);
        Complex complex1 = new Complex(331.594212, 0.0);
        Complex complex2 = complex1.ONE.add(complex0);
        Complex complex3 = complex0.I.multiply(complex1);
        Complex complex4 = complex0.NaN.log();
        Complex complex5 = complex4.ONE.tan();
        String string0 = complex0.NaN.toString();
        Complex complex6 = complex0.sqrt();
        complex0.createComplex(0.0, 1.0);
        List<Complex> list0 = complex0.nthRoot(2645);
        Complex complex7 = complex4.asin();
        Complex complex8 = complex7.tanh();
        complex7.abs();
        Complex complex9 = complex0.subtract(complex7);
        complex9.ONE.sqrt();
        Complex complex10 = complex9.negate();
        complex10.I.createComplex((-978.246), (-978.246));
        Complex complex11 = complex7.divide(complex6);
        complex6.getImaginary();
    }

    @Test(timeout = 4000)
    public void test7716() throws Throwable {
        Complex complex0 = new Complex(1.0, 1.0);
        Complex complex1 = new Complex(331.594212, 0.0);
        Complex complex2 = complex1.ONE.add(complex0);
        Complex complex3 = complex0.I.multiply(complex1);
        Complex complex4 = complex0.NaN.log();
        Complex complex5 = complex4.ONE.tan();
        String string0 = complex0.NaN.toString();
        Complex complex6 = complex0.sqrt();
        complex0.createComplex(0.0, 1.0);
        List<Complex> list0 = complex0.nthRoot(2645);
        Complex complex7 = complex4.asin();
        Complex complex8 = complex7.tanh();
        complex7.abs();
        Complex complex9 = complex0.subtract(complex7);
        complex9.ONE.sqrt();
        Complex complex10 = complex9.negate();
        complex10.I.createComplex((-978.246), (-978.246));
        Complex complex11 = complex7.divide(complex6);
    }

    @Test(timeout = 4000)
    public void test7717() throws Throwable {
        Complex complex0 = new Complex(1.0, 1.0);
        Complex complex1 = new Complex(331.594212, 0.0);
        Complex complex2 = complex1.ONE.add(complex0);
        Complex complex3 = complex0.I.multiply(complex1);
        Complex complex4 = complex0.NaN.log();
        Complex complex5 = complex4.ONE.tan();
        String string0 = complex0.NaN.toString();
        Complex complex6 = complex0.sqrt();
        complex0.createComplex(0.0, 1.0);
        List<Complex> list0 = complex0.nthRoot(2645);
        Complex complex7 = complex4.asin();
        Complex complex8 = complex7.tanh();
        complex7.abs();
        Complex complex9 = complex0.subtract(complex7);
        complex9.ONE.sqrt();
        Complex complex10 = complex9.negate();
        complex10.I.createComplex((-978.246), (-978.246));
        Complex complex11 = complex7.divide(complex6);
        Complex complex12 = complex3.sqrt1z();
        complex12.getReal();
    }

    @Test(timeout = 4000)
    public void test7718() throws Throwable {
        Complex complex0 = new Complex(1.0, 1.0);
        Complex complex1 = new Complex(331.594212, 0.0);
        Complex complex2 = complex1.ONE.add(complex0);
        Complex complex3 = complex0.I.multiply(complex1);
        Complex complex4 = complex0.NaN.log();
        Complex complex5 = complex4.ONE.tan();
        String string0 = complex0.NaN.toString();
        Complex complex6 = complex0.sqrt();
        complex0.createComplex(0.0, 1.0);
        List<Complex> list0 = complex0.nthRoot(2645);
        Complex complex7 = complex4.asin();
        Complex complex8 = complex7.tanh();
        complex7.abs();
        Complex complex9 = complex0.subtract(complex7);
        complex9.ONE.sqrt();
        Complex complex10 = complex9.negate();
        complex10.I.createComplex((-978.246), (-978.246));
        Complex complex11 = complex7.divide(complex6);
        Complex complex12 = complex3.sqrt1z();
        Complex complex13 = complex5.exp();
        Complex complex14 = complex3.log();
        complex14.getImaginary();
    }

    @Test(timeout = 4000)
    public void test7719() throws Throwable {
        Complex complex0 = new Complex(1.0, 1.0);
        Complex complex1 = new Complex(331.594212, 0.0);
        Complex complex2 = complex1.ONE.add(complex0);
        Complex complex3 = complex0.I.multiply(complex1);
        Complex complex4 = complex0.NaN.log();
        Complex complex5 = complex4.ONE.tan();
        String string0 = complex0.NaN.toString();
        Complex complex6 = complex0.sqrt();
        complex0.createComplex(0.0, 1.0);
        List<Complex> list0 = complex0.nthRoot(2645);
        Complex complex7 = complex4.asin();
        Complex complex8 = complex7.tanh();
        complex7.abs();
        Complex complex9 = complex0.subtract(complex7);
        complex9.ONE.sqrt();
        Complex complex10 = complex9.negate();
        complex10.I.createComplex((-978.246), (-978.246));
        Complex complex11 = complex7.divide(complex6);
        Complex complex12 = complex3.sqrt1z();
        Complex complex13 = complex5.exp();
        Complex complex14 = complex3.log();
        complex3.getReal();
    }

    @Test(timeout = 4000)
    public void test7720() throws Throwable {
        Complex complex0 = new Complex(1.0, 1.0);
        Complex complex1 = new Complex(331.594212, 0.0);
        Complex complex2 = complex1.ONE.add(complex0);
        Complex complex3 = complex0.I.multiply(complex1);
        Complex complex4 = complex0.NaN.log();
        Complex complex5 = complex4.ONE.tan();
        String string0 = complex0.NaN.toString();
        Complex complex6 = complex0.sqrt();
        complex0.createComplex(0.0, 1.0);
        List<Complex> list0 = complex0.nthRoot(2645);
        Complex complex7 = complex4.asin();
        Complex complex8 = complex7.tanh();
        complex7.abs();
        Complex complex9 = complex0.subtract(complex7);
        complex9.ONE.sqrt();
        Complex complex10 = complex9.negate();
        complex10.I.createComplex((-978.246), (-978.246));
        Complex complex11 = complex7.divide(complex6);
        Complex complex12 = complex3.sqrt1z();
        Complex complex13 = complex5.exp();
        Complex complex14 = complex3.log();
        complex14.getReal();
    }

    @Test(timeout = 4000)
    public void test7721() throws Throwable {
        Complex complex0 = new Complex(1.0, 1.0);
        Complex complex1 = new Complex(331.594212, 0.0);
        Complex complex2 = complex1.ONE.add(complex0);
        Complex complex3 = complex0.I.multiply(complex1);
        Complex complex4 = complex0.NaN.log();
        Complex complex5 = complex4.ONE.tan();
        String string0 = complex0.NaN.toString();
        Complex complex6 = complex0.sqrt();
        complex0.createComplex(0.0, 1.0);
        List<Complex> list0 = complex0.nthRoot(2645);
        Complex complex7 = complex4.asin();
        Complex complex8 = complex7.tanh();
        complex7.abs();
        Complex complex9 = complex0.subtract(complex7);
        complex9.ONE.sqrt();
        Complex complex10 = complex9.negate();
        complex10.I.createComplex((-978.246), (-978.246));
        Complex complex11 = complex7.divide(complex6);
        Complex complex12 = complex3.sqrt1z();
        Complex complex13 = complex5.exp();
        Complex complex14 = complex3.log();
        complex1.createComplex(3908.994616, 331.594212);
        Complex complex15 = complex13.pow(complex8);
    }

    @Test(timeout = 4000)
    public void test7722() throws Throwable {
        Complex complex0 = new Complex(1.0, 1.0);
        Complex complex1 = new Complex(331.594212, 0.0);
        Complex complex2 = complex1.ONE.add(complex0);
        Complex complex3 = complex0.I.multiply(complex1);
        Complex complex4 = complex0.NaN.log();
        Complex complex5 = complex4.ONE.tan();
        String string0 = complex0.NaN.toString();
        Complex complex6 = complex0.sqrt();
        complex0.createComplex(0.0, 1.0);
        List<Complex> list0 = complex0.nthRoot(2645);
        Complex complex7 = complex4.asin();
        Complex complex8 = complex7.tanh();
        complex7.abs();
        Complex complex9 = complex0.subtract(complex7);
        complex9.ONE.sqrt();
        Complex complex10 = complex9.negate();
        complex10.I.createComplex((-978.246), (-978.246));
        Complex complex11 = complex7.divide(complex6);
        Complex complex12 = complex3.sqrt1z();
        Complex complex13 = complex5.exp();
        Complex complex14 = complex3.log();
        complex1.createComplex(3908.994616, 331.594212);
        Complex complex15 = complex13.pow(complex8);
        complex13.getImaginary();
    }

    @Test(timeout = 4000)
    public void test7723() throws Throwable {
        Complex complex0 = new Complex(1.0, 1.0);
        Complex complex1 = new Complex(331.594212, 0.0);
        Complex complex2 = complex1.ONE.add(complex0);
        Complex complex3 = complex0.I.multiply(complex1);
        Complex complex4 = complex0.NaN.log();
        Complex complex5 = complex4.ONE.tan();
        String string0 = complex0.NaN.toString();
        Complex complex6 = complex0.sqrt();
        complex0.createComplex(0.0, 1.0);
        List<Complex> list0 = complex0.nthRoot(2645);
        Complex complex7 = complex4.asin();
        Complex complex8 = complex7.tanh();
        complex7.abs();
        Complex complex9 = complex0.subtract(complex7);
        complex9.ONE.sqrt();
        Complex complex10 = complex9.negate();
        complex10.I.createComplex((-978.246), (-978.246));
        Complex complex11 = complex7.divide(complex6);
        Complex complex12 = complex3.sqrt1z();
        Complex complex13 = complex5.exp();
        Complex complex14 = complex3.log();
        complex1.createComplex(3908.994616, 331.594212);
        Complex complex15 = complex13.pow(complex8);
        complex13.getReal();
    }

    @Test(timeout = 4000)
    public void test7824() throws Throwable {
        Complex complex0 = Complex.ZERO;
        Complex complex1 = complex0.tanh();
        Complex complex2 = complex1.exp();
        Complex complex3 = complex2.INF.exp();
        complex2.getArgument();
        Complex complex4 = (Complex) complex1.INF.readResolve();
        Complex complex5 = complex1.INF.subtract(complex3);
        Complex complex6 = complex2.cos();
        Complex complex7 = complex3.ONE.atan();
        Complex complex8 = complex0.cos();
        Complex complex9 = complex6.I.multiply((-2753.17));
        complex3.NaN.asin();
        Complex complex10 = complex9.NaN.pow(complex2);
        Complex complex11 = complex8.I.subtract(complex2);
        Complex complex12 = complex11.INF.negate();
        complex6.conjugate();
        complex1.INF.tanh();
        double double0 = complex1.abs();
    }

    @Test(timeout = 4000)
    public void test7825() throws Throwable {
        Complex complex0 = Complex.ZERO;
        Complex complex1 = complex0.tanh();
        Complex complex2 = complex1.exp();
        Complex complex3 = complex2.INF.exp();
        complex2.getArgument();
        Complex complex4 = (Complex) complex1.INF.readResolve();
        Complex complex5 = complex1.INF.subtract(complex3);
        Complex complex6 = complex2.cos();
        Complex complex7 = complex3.ONE.atan();
        Complex complex8 = complex0.cos();
        Complex complex9 = complex6.I.multiply((-2753.17));
        complex3.NaN.asin();
        Complex complex10 = complex9.NaN.pow(complex2);
        Complex complex11 = complex8.I.subtract(complex2);
        Complex complex12 = complex11.INF.negate();
        complex6.conjugate();
        complex1.INF.tanh();
        double double0 = complex1.abs();
        double double1 = complex8.abs();
    }

    @Test(timeout = 4000)
    public void test7826() throws Throwable {
        Complex complex0 = Complex.ZERO;
        Complex complex1 = complex0.tanh();
        Complex complex2 = complex1.exp();
        Complex complex3 = complex2.INF.exp();
        complex2.getArgument();
        Complex complex4 = (Complex) complex1.INF.readResolve();
        Complex complex5 = complex1.INF.subtract(complex3);
        Complex complex6 = complex2.cos();
        Complex complex7 = complex3.ONE.atan();
        Complex complex8 = complex0.cos();
        Complex complex9 = complex6.I.multiply((-2753.17));
        complex3.NaN.asin();
        Complex complex10 = complex9.NaN.pow(complex2);
        Complex complex11 = complex8.I.subtract(complex2);
        Complex complex12 = complex11.INF.negate();
        complex6.conjugate();
        complex1.INF.tanh();
        double double0 = complex1.abs();
        double double1 = complex8.abs();
        Complex complex13 = complex0.asin();
        complex13.getImaginary();
    }

    @Test(timeout = 4000)
    public void test7827() throws Throwable {
        Complex complex0 = Complex.ZERO;
        Complex complex1 = complex0.tanh();
        Complex complex2 = complex1.exp();
        Complex complex3 = complex2.INF.exp();
        complex2.getArgument();
        Complex complex4 = (Complex) complex1.INF.readResolve();
        Complex complex5 = complex1.INF.subtract(complex3);
        Complex complex6 = complex2.cos();
        Complex complex7 = complex3.ONE.atan();
        Complex complex8 = complex0.cos();
        Complex complex9 = complex6.I.multiply((-2753.17));
        complex3.NaN.asin();
        Complex complex10 = complex9.NaN.pow(complex2);
        Complex complex11 = complex8.I.subtract(complex2);
        Complex complex12 = complex11.INF.negate();
        complex6.conjugate();
        complex1.INF.tanh();
        double double0 = complex1.abs();
        double double1 = complex8.abs();
        Complex complex13 = complex0.asin();
        complex8.equals((Object) null);
        complex3.subtract(complex8);
        complex3.log();
        double double2 = complex1.getArgument();
    }

    @Test(timeout = 4000)
    public void test7828() throws Throwable {
        Complex complex0 = Complex.ZERO;
        Complex complex1 = complex0.tanh();
        Complex complex2 = complex1.exp();
        Complex complex3 = complex2.INF.exp();
        complex2.getArgument();
        Complex complex4 = (Complex) complex1.INF.readResolve();
        Complex complex5 = complex1.INF.subtract(complex3);
        Complex complex6 = complex2.cos();
        Complex complex7 = complex3.ONE.atan();
        Complex complex8 = complex0.cos();
        Complex complex9 = complex6.I.multiply((-2753.17));
        complex3.NaN.asin();
        Complex complex10 = complex9.NaN.pow(complex2);
        Complex complex11 = complex8.I.subtract(complex2);
        Complex complex12 = complex11.INF.negate();
        complex6.conjugate();
        complex1.INF.tanh();
        double double0 = complex1.abs();
        double double1 = complex8.abs();
        Complex complex13 = complex0.asin();
        complex8.equals((Object) null);
        complex3.subtract(complex8);
        complex3.log();
        double double2 = complex1.getArgument();
        complex12.divide(complex10);
    }

    @Test(timeout = 4000)
    public void test7829() throws Throwable {
        Complex complex0 = Complex.ZERO;
        Complex complex1 = complex0.tanh();
        Complex complex2 = complex1.exp();
        Complex complex3 = complex2.INF.exp();
        complex2.getArgument();
        Complex complex4 = (Complex) complex1.INF.readResolve();
        Complex complex5 = complex1.INF.subtract(complex3);
        Complex complex6 = complex2.cos();
        Complex complex7 = complex3.ONE.atan();
        Complex complex8 = complex0.cos();
        Complex complex9 = complex6.I.multiply((-2753.17));
        complex3.NaN.asin();
        Complex complex10 = complex9.NaN.pow(complex2);
        Complex complex11 = complex8.I.subtract(complex2);
        Complex complex12 = complex11.INF.negate();
        complex6.conjugate();
        complex1.INF.tanh();
        double double0 = complex1.abs();
        double double1 = complex8.abs();
        Complex complex13 = complex0.asin();
        complex8.equals((Object) null);
        complex3.subtract(complex8);
        complex3.log();
        double double2 = complex1.getArgument();
        complex12.divide(complex10);
        Complex complex14 = complex7.atan();
        complex14.getImaginary();
    }

    @Test(timeout = 4000)
    public void test7830() throws Throwable {
        Complex complex0 = Complex.ZERO;
        Complex complex1 = complex0.tanh();
        Complex complex2 = complex1.exp();
        Complex complex3 = complex2.INF.exp();
        complex2.getArgument();
        Complex complex4 = (Complex) complex1.INF.readResolve();
        Complex complex5 = complex1.INF.subtract(complex3);
        Complex complex6 = complex2.cos();
        Complex complex7 = complex3.ONE.atan();
        Complex complex8 = complex0.cos();
        Complex complex9 = complex6.I.multiply((-2753.17));
        complex3.NaN.asin();
        Complex complex10 = complex9.NaN.pow(complex2);
        Complex complex11 = complex8.I.subtract(complex2);
        Complex complex12 = complex11.INF.negate();
        complex6.conjugate();
        complex1.INF.tanh();
        double double0 = complex1.abs();
        double double1 = complex8.abs();
        Complex complex13 = complex0.asin();
        complex8.equals((Object) null);
        complex3.subtract(complex8);
        complex3.log();
        double double2 = complex1.getArgument();
        complex12.divide(complex10);
        Complex complex14 = complex7.atan();
        Complex complex15 = complex9.pow(complex4);
        complex9.getReal();
    }

    @Test(timeout = 4000)
    public void test7831() throws Throwable {
        Complex complex0 = Complex.ZERO;
        Complex complex1 = complex0.tanh();
        Complex complex2 = complex1.exp();
        Complex complex3 = complex2.INF.exp();
        complex2.getArgument();
        Complex complex4 = (Complex) complex1.INF.readResolve();
        Complex complex5 = complex1.INF.subtract(complex3);
        Complex complex6 = complex2.cos();
        Complex complex7 = complex3.ONE.atan();
        Complex complex8 = complex0.cos();
        Complex complex9 = complex6.I.multiply((-2753.17));
        complex3.NaN.asin();
        Complex complex10 = complex9.NaN.pow(complex2);
        Complex complex11 = complex8.I.subtract(complex2);
        Complex complex12 = complex11.INF.negate();
        complex6.conjugate();
        complex1.INF.tanh();
        double double0 = complex1.abs();
        double double1 = complex8.abs();
        Complex complex13 = complex0.asin();
        complex8.equals((Object) null);
        complex3.subtract(complex8);
        complex3.log();
        double double2 = complex1.getArgument();
        complex12.divide(complex10);
        Complex complex14 = complex7.atan();
        Complex complex15 = complex9.pow(complex4);
        complex1.equals((Object) complex13);
    }

    @Test(timeout = 4000)
    public void test7832() throws Throwable {
        Complex complex0 = Complex.ZERO;
        Complex complex1 = complex0.tanh();
        Complex complex2 = complex1.exp();
        Complex complex3 = complex2.INF.exp();
        complex2.getArgument();
        Complex complex4 = (Complex) complex1.INF.readResolve();
        Complex complex5 = complex1.INF.subtract(complex3);
        Complex complex6 = complex2.cos();
        Complex complex7 = complex3.ONE.atan();
        Complex complex8 = complex0.cos();
        Complex complex9 = complex6.I.multiply((-2753.17));
        complex3.NaN.asin();
        Complex complex10 = complex9.NaN.pow(complex2);
        Complex complex11 = complex8.I.subtract(complex2);
        Complex complex12 = complex11.INF.negate();
        complex6.conjugate();
        complex1.INF.tanh();
        double double0 = complex1.abs();
        double double1 = complex8.abs();
        Complex complex13 = complex0.asin();
        complex8.equals((Object) null);
        complex3.subtract(complex8);
        complex3.log();
        double double2 = complex1.getArgument();
        complex12.divide(complex10);
        Complex complex14 = complex7.atan();
        Complex complex15 = complex9.pow(complex4);
        complex15.isNaN();
        assertTrue(complex15.isNaN());
    }

    @Test(timeout = 4000)
    public void test7933() throws Throwable {
        Complex complex0 = Complex.INF;
        Complex complex1 = complex0.INF.atan();
        complex0.readResolve();
        Complex complex2 = complex0.INF.sqrt1z();
        complex2.getImaginary();
    }

    @Test(timeout = 4000)
    public void test7934() throws Throwable {
        Complex complex0 = Complex.INF;
        Complex complex1 = complex0.INF.atan();
        complex0.readResolve();
        Complex complex2 = complex0.INF.sqrt1z();
        complex1.toString();
        String string0 = complex0.INF.toString();
    }

    @Test(timeout = 4000)
    public void test7935() throws Throwable {
        Complex complex0 = Complex.INF;
        Complex complex1 = complex0.INF.atan();
        complex0.readResolve();
        Complex complex2 = complex0.INF.sqrt1z();
        complex1.toString();
        String string0 = complex0.INF.toString();
        List<Complex> list0 = complex1.INF.nthRoot(17);
        list0.contains(complex1);
    }

    @Test(timeout = 4000)
    public void test7936() throws Throwable {
        Complex complex0 = Complex.INF;
        Complex complex1 = complex0.INF.atan();
        complex0.readResolve();
        Complex complex2 = complex0.INF.sqrt1z();
        complex1.toString();
        String string0 = complex0.INF.toString();
        List<Complex> list0 = complex1.INF.nthRoot(17);
        Complex complex3 = complex0.INF.acos();
        Complex complex4 = complex3.NaN.sqrt();
        Complex complex5 = complex1.INF.multiply((-869.3549889687644));
        complex5.INF.exp();
        Complex complex6 = complex0.ONE.multiply(0.7853981633974483);
        Complex complex7 = complex5.I.createComplex(0.7853981633974483, 0.0);
        complex7.equals((Object) complex6);
    }

    @Test(timeout = 4000)
    public void test7937() throws Throwable {
        Complex complex0 = Complex.INF;
        Complex complex1 = complex0.INF.atan();
        complex0.readResolve();
        Complex complex2 = complex0.INF.sqrt1z();
        complex1.toString();
        String string0 = complex0.INF.toString();
        List<Complex> list0 = complex1.INF.nthRoot(17);
        Complex complex3 = complex0.INF.acos();
        Complex complex4 = complex3.NaN.sqrt();
        Complex complex5 = complex1.INF.multiply((-869.3549889687644));
        complex5.INF.exp();
        Complex complex6 = complex0.ONE.multiply(0.7853981633974483);
        Complex complex7 = complex5.I.createComplex(0.7853981633974483, 0.0);
        Complex complex8 = complex0.negate();
        Complex complex9 = complex8.ZERO.sinh();
        complex8.getField();
        complex9.hashCode();
        complex9.isInfinite();
        assertTrue(complex9.isInfinite());
    }

    @Test(timeout = 4000)
    public void test7938() throws Throwable {
        Complex complex0 = Complex.INF;
        Complex complex1 = complex0.INF.atan();
        complex0.readResolve();
        Complex complex2 = complex0.INF.sqrt1z();
        complex1.toString();
        String string0 = complex0.INF.toString();
        List<Complex> list0 = complex1.INF.nthRoot(17);
        Complex complex3 = complex0.INF.acos();
        Complex complex4 = complex3.NaN.sqrt();
        Complex complex5 = complex1.INF.multiply((-869.3549889687644));
        complex5.INF.exp();
        Complex complex6 = complex0.ONE.multiply(0.7853981633974483);
        Complex complex7 = complex5.I.createComplex(0.7853981633974483, 0.0);
        Complex complex8 = complex0.negate();
        Complex complex9 = complex8.ZERO.sinh();
        complex8.getField();
        complex9.hashCode();
        complex8.getReal();
    }

    @Test(timeout = 4000)
    public void test7939() throws Throwable {
        Complex complex0 = Complex.INF;
        Complex complex1 = complex0.INF.atan();
        complex0.readResolve();
        Complex complex2 = complex0.INF.sqrt1z();
        complex1.toString();
        String string0 = complex0.INF.toString();
        List<Complex> list0 = complex1.INF.nthRoot(17);
        Complex complex3 = complex0.INF.acos();
        Complex complex4 = complex3.NaN.sqrt();
        Complex complex5 = complex1.INF.multiply((-869.3549889687644));
        complex5.INF.exp();
        Complex complex6 = complex0.ONE.multiply(0.7853981633974483);
        Complex complex7 = complex5.I.createComplex(0.7853981633974483, 0.0);
        Complex complex8 = complex0.negate();
        Complex complex9 = complex8.ZERO.sinh();
        complex8.getField();
        complex9.hashCode();
        complex9.getImaginary();
    }

    @Test(timeout = 4000)
    public void test7940() throws Throwable {
        Complex complex0 = Complex.INF;
        Complex complex1 = complex0.INF.atan();
        complex0.readResolve();
        Complex complex2 = complex0.INF.sqrt1z();
        complex1.toString();
        String string0 = complex0.INF.toString();
        List<Complex> list0 = complex1.INF.nthRoot(17);
        Complex complex3 = complex0.INF.acos();
        Complex complex4 = complex3.NaN.sqrt();
        Complex complex5 = complex1.INF.multiply((-869.3549889687644));
        complex5.INF.exp();
        Complex complex6 = complex0.ONE.multiply(0.7853981633974483);
        Complex complex7 = complex5.I.createComplex(0.7853981633974483, 0.0);
        Complex complex8 = complex0.negate();
        Complex complex9 = complex8.ZERO.sinh();
        complex8.getField();
        complex9.hashCode();
        Complex complex10 = complex0.sqrt();
        complex4.multiply((double) 17);
    }

    @Test(timeout = 4000)
    public void test7941() throws Throwable {
        Complex complex0 = Complex.INF;
        Complex complex1 = complex0.INF.atan();
        complex0.readResolve();
        Complex complex2 = complex0.INF.sqrt1z();
        complex1.toString();
        String string0 = complex0.INF.toString();
        List<Complex> list0 = complex1.INF.nthRoot(17);
        Complex complex3 = complex0.INF.acos();
        Complex complex4 = complex3.NaN.sqrt();
        Complex complex5 = complex1.INF.multiply((-869.3549889687644));
        complex5.INF.exp();
        Complex complex6 = complex0.ONE.multiply(0.7853981633974483);
        Complex complex7 = complex5.I.createComplex(0.7853981633974483, 0.0);
        Complex complex8 = complex0.negate();
        Complex complex9 = complex8.ZERO.sinh();
        complex8.getField();
        complex9.hashCode();
        Complex complex10 = complex0.sqrt();
        complex4.multiply((double) 17);
    }

    @Test(timeout = 4000)
    public void test7942() throws Throwable {
        Complex complex0 = Complex.INF;
        Complex complex1 = complex0.INF.atan();
        complex0.readResolve();
        Complex complex2 = complex0.INF.sqrt1z();
        complex1.toString();
        String string0 = complex0.INF.toString();
        List<Complex> list0 = complex1.INF.nthRoot(17);
        Complex complex3 = complex0.INF.acos();
        Complex complex4 = complex3.NaN.sqrt();
        Complex complex5 = complex1.INF.multiply((-869.3549889687644));
        complex5.INF.exp();
        Complex complex6 = complex0.ONE.multiply(0.7853981633974483);
        Complex complex7 = complex5.I.createComplex(0.7853981633974483, 0.0);
        Complex complex8 = complex0.negate();
        Complex complex9 = complex8.ZERO.sinh();
        complex8.getField();
        complex9.hashCode();
        Complex complex10 = complex0.sqrt();
        complex4.multiply((double) 17);
        complex0.equals((Object) complex10);
    }

    @Test(timeout = 4000)
    public void test8043() throws Throwable {
        Complex complex0 = Complex.I;
        complex0.getArgument();
        complex0.equals((Object) null);
        complex0.getImaginary();
        Complex complex1 = complex0.log();
        complex1.INF.conjugate();
        complex1.ONE.toString();
        complex1.NaN.acos();
        Complex complex2 = complex0.I.exp();
        Complex complex3 = complex1.NaN.exp();
        Complex complex4 = complex3.INF.cosh();
        complex4.ZERO.toString();
        complex4.log();
        Complex complex5 = complex3.ONE.cosh();
        complex5.INF.multiply(complex0);
        complex1.ONE.createComplex(1.0, 1.5707963267948966);
        complex0.createComplex(1.5707963267948966, 1.0);
        complex2.nthRoot((-982));
    }
}
