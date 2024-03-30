/*
 * This file was automatically generated by EvoSuite
 * Mon Oct 16 18:51:12 GMT 2023
 */
package org.apache.commons.math.linear;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import org.apache.commons.math.linear.Array2DRowRealMatrix;
import org.apache.commons.math.linear.ArrayRealVector;
import org.apache.commons.math.linear.BlockRealMatrix;
import org.apache.commons.math.linear.DecompositionSolver;
import org.apache.commons.math.linear.OpenMapRealMatrix;
import org.apache.commons.math.linear.OpenMapRealVector;
import org.apache.commons.math.linear.RealMatrix;
import org.apache.commons.math.linear.SingularValueDecompositionImpl;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class SingularValueDecompositionImpl_ESTest extends SingularValueDecompositionImpl_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        double[] doubleArray0 = new double[24];
        doubleArray0[0] = (-1469.24165998819);
        doubleArray0[5] = (-651.4657703215);
        ArrayRealVector arrayRealVector0 = new ArrayRealVector(doubleArray0);
        RealMatrix realMatrix0 = arrayRealVector0.outerProduct(doubleArray0);
        SingularValueDecompositionImpl singularValueDecompositionImpl0 = new SingularValueDecompositionImpl(realMatrix0, 5);
        double double0 = singularValueDecompositionImpl0.getConditionNumber();
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        double[][] doubleArray0 = new double[1][3];
        double[] doubleArray1 = new double[5];
        doubleArray1[2] = 1304.2990125721;
        doubleArray1[3] = 7175462.327908139;
        doubleArray1[4] = (-822.8113287);
        doubleArray0[0] = doubleArray1;
        Array2DRowRealMatrix array2DRowRealMatrix0 = new Array2DRowRealMatrix(doubleArray0);
        SingularValueDecompositionImpl singularValueDecompositionImpl0 = new SingularValueDecompositionImpl(array2DRowRealMatrix0, 2);
        RealMatrix realMatrix0 = singularValueDecompositionImpl0.getCovariance(7175462.493626685);
        realMatrix0.equals((Object) array2DRowRealMatrix0);
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        double[] doubleArray0 = new double[3];
        Array2DRowRealMatrix array2DRowRealMatrix0 = new Array2DRowRealMatrix(doubleArray0);
        SingularValueDecompositionImpl singularValueDecompositionImpl0 = new SingularValueDecompositionImpl(array2DRowRealMatrix0);
        singularValueDecompositionImpl0.getSolver();
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        double[] doubleArray0 = new double[14];
        doubleArray0[0] = (-119.0863408434962);
        ArrayRealVector arrayRealVector0 = new ArrayRealVector(doubleArray0, doubleArray0);
        RealMatrix realMatrix0 = arrayRealVector0.outerProduct(arrayRealVector0);
        SingularValueDecompositionImpl singularValueDecompositionImpl0 = new SingularValueDecompositionImpl(realMatrix0);
        double[] doubleArray1 = singularValueDecompositionImpl0.getSingularValues();
    }

    @Test(timeout = 4000)
    public void test034() throws Throwable {
        double[] doubleArray0 = new double[14];
        doubleArray0[0] = (-119.0863408434962);
        ArrayRealVector arrayRealVector0 = new ArrayRealVector(doubleArray0, doubleArray0);
        RealMatrix realMatrix0 = arrayRealVector0.outerProduct(arrayRealVector0);
        SingularValueDecompositionImpl singularValueDecompositionImpl0 = new SingularValueDecompositionImpl(realMatrix0);
        double[] doubleArray1 = singularValueDecompositionImpl0.getSingularValues();
    }

    @Test(timeout = 4000)
    public void test045() throws Throwable {
        double[] doubleArray0 = new double[6];
        doubleArray0[0] = 1657.6410258812;
        Array2DRowRealMatrix array2DRowRealMatrix0 = new Array2DRowRealMatrix(doubleArray0);
        SingularValueDecompositionImpl singularValueDecompositionImpl0 = new SingularValueDecompositionImpl(array2DRowRealMatrix0, 117);
        double double0 = singularValueDecompositionImpl0.getNorm();
    }

    @Test(timeout = 4000)
    public void test056() throws Throwable {
        double[][] doubleArray0 = new double[1][4];
        Array2DRowRealMatrix array2DRowRealMatrix0 = new Array2DRowRealMatrix(doubleArray0);
        SingularValueDecompositionImpl singularValueDecompositionImpl0 = new SingularValueDecompositionImpl(array2DRowRealMatrix0, 0);
        singularValueDecompositionImpl0.getUT();
    }

    @Test(timeout = 4000)
    public void test087() throws Throwable {
        Array2DRowRealMatrix array2DRowRealMatrix0 = new Array2DRowRealMatrix();
        double[][] doubleArray0 = new double[1][7];
        array2DRowRealMatrix0.data = doubleArray0;
        SingularValueDecompositionImpl singularValueDecompositionImpl0 = new SingularValueDecompositionImpl(array2DRowRealMatrix0, 0);
        singularValueDecompositionImpl0.getS();
    }

    @Test(timeout = 4000)
    public void test098() throws Throwable {
        OpenMapRealMatrix openMapRealMatrix0 = new OpenMapRealMatrix(7, 7);
        SingularValueDecompositionImpl singularValueDecompositionImpl0 = new SingularValueDecompositionImpl(openMapRealMatrix0);
        singularValueDecompositionImpl0.getRank();
    }

    @Test(timeout = 4000)
    public void test109() throws Throwable {
        double[] doubleArray0 = new double[3];
        doubleArray0[0] = (-4091.30585795025);
        Array2DRowRealMatrix array2DRowRealMatrix0 = new Array2DRowRealMatrix(doubleArray0);
        SingularValueDecompositionImpl singularValueDecompositionImpl0 = new SingularValueDecompositionImpl(array2DRowRealMatrix0);
        singularValueDecompositionImpl0.getCovariance(Double.POSITIVE_INFINITY);
    }

    @Test(timeout = 4000)
    public void test1210() throws Throwable {
        double[] doubleArray0 = new double[12];
        doubleArray0[0] = (-1469.24165998819);
        doubleArray0[3] = 0.10664378144610108;
        doubleArray0[5] = (-651.4657703215);
        ArrayRealVector arrayRealVector0 = new ArrayRealVector(doubleArray0);
        RealMatrix realMatrix0 = arrayRealVector0.outerProduct(doubleArray0);
        SingularValueDecompositionImpl singularValueDecompositionImpl0 = null;
        singularValueDecompositionImpl0 = new SingularValueDecompositionImpl(realMatrix0, 5);
    }

    @Test(timeout = 4000)
    public void test1311() throws Throwable {
        SingularValueDecompositionImpl singularValueDecompositionImpl0 = null;
        singularValueDecompositionImpl0 = new SingularValueDecompositionImpl((RealMatrix) null, (-2635));
    }

    @Test(timeout = 4000)
    public void test1412() throws Throwable {
        Array2DRowRealMatrix array2DRowRealMatrix0 = new Array2DRowRealMatrix();
        SingularValueDecompositionImpl singularValueDecompositionImpl0 = null;
        singularValueDecompositionImpl0 = new SingularValueDecompositionImpl(array2DRowRealMatrix0, 0);
    }

    @Test(timeout = 4000)
    public void test1513() throws Throwable {
        Array2DRowRealMatrix array2DRowRealMatrix0 = new Array2DRowRealMatrix(1, 1);
        double[][] doubleArray0 = new double[4][2];
        double[] doubleArray1 = new double[4];
        doubleArray0[2] = doubleArray1;
        array2DRowRealMatrix0.data = doubleArray0;
        SingularValueDecompositionImpl singularValueDecompositionImpl0 = null;
        singularValueDecompositionImpl0 = new SingularValueDecompositionImpl(array2DRowRealMatrix0, 46);
    }

    @Test(timeout = 4000)
    public void test1714() throws Throwable {
        double[] doubleArray0 = new double[5];
        doubleArray0[0] = (-119.0863408434962);
        doubleArray0[1] = 3341.3125;
        ArrayRealVector arrayRealVector0 = new ArrayRealVector(doubleArray0, doubleArray0);
        RealMatrix realMatrix0 = arrayRealVector0.outerProduct(arrayRealVector0);
        SingularValueDecompositionImpl singularValueDecompositionImpl0 = null;
        singularValueDecompositionImpl0 = new SingularValueDecompositionImpl(realMatrix0);
    }

    @Test(timeout = 4000)
    public void test1815() throws Throwable {
        SingularValueDecompositionImpl singularValueDecompositionImpl0 = null;
        singularValueDecompositionImpl0 = new SingularValueDecompositionImpl((RealMatrix) null);
    }

    @Test(timeout = 4000)
    public void test1916() throws Throwable {
        Array2DRowRealMatrix array2DRowRealMatrix0 = new Array2DRowRealMatrix();
        SingularValueDecompositionImpl singularValueDecompositionImpl0 = null;
        singularValueDecompositionImpl0 = new SingularValueDecompositionImpl(array2DRowRealMatrix0);
    }

    @Test(timeout = 4000)
    public void test2017() throws Throwable {
        double[] doubleArray0 = new double[3];
        Array2DRowRealMatrix array2DRowRealMatrix0 = new Array2DRowRealMatrix(doubleArray0);
        double[][] doubleArray1 = new double[8][2];
        doubleArray1[1] = doubleArray0;
        array2DRowRealMatrix0.data = doubleArray1;
        SingularValueDecompositionImpl singularValueDecompositionImpl0 = null;
        singularValueDecompositionImpl0 = new SingularValueDecompositionImpl(array2DRowRealMatrix0);
    }

    @Test(timeout = 4000)
    public void test2118() throws Throwable {
        double[] doubleArray0 = new double[5];
        doubleArray0[0] = (-119.0863408434962);
        ArrayRealVector arrayRealVector0 = new ArrayRealVector(doubleArray0, doubleArray0);
        RealMatrix realMatrix0 = arrayRealVector0.outerProduct(arrayRealVector0);
        SingularValueDecompositionImpl singularValueDecompositionImpl0 = new SingularValueDecompositionImpl(realMatrix0);
        int int0 = singularValueDecompositionImpl0.getRank();
    }

    @Test(timeout = 4000)
    public void test2119() throws Throwable {
        double[] doubleArray0 = new double[5];
        doubleArray0[0] = (-119.0863408434962);
        ArrayRealVector arrayRealVector0 = new ArrayRealVector(doubleArray0, doubleArray0);
        RealMatrix realMatrix0 = arrayRealVector0.outerProduct(arrayRealVector0);
        SingularValueDecompositionImpl singularValueDecompositionImpl0 = new SingularValueDecompositionImpl(realMatrix0);
        int int0 = singularValueDecompositionImpl0.getRank();
        singularValueDecompositionImpl0.getNorm();
    }

    @Test(timeout = 4000)
    public void test2220() throws Throwable {
        BlockRealMatrix blockRealMatrix0 = new BlockRealMatrix(9, 1);
        SingularValueDecompositionImpl singularValueDecompositionImpl0 = new SingularValueDecompositionImpl(blockRealMatrix0);
        singularValueDecompositionImpl0.getVT();
    }

    @Test(timeout = 4000)
    public void test2321() throws Throwable {
        double[] doubleArray0 = new double[5];
        doubleArray0[0] = (-119.0863408434962);
        ArrayRealVector arrayRealVector0 = new ArrayRealVector(doubleArray0, doubleArray0);
        RealMatrix realMatrix0 = arrayRealVector0.outerProduct(arrayRealVector0);
        SingularValueDecompositionImpl singularValueDecompositionImpl0 = new SingularValueDecompositionImpl(realMatrix0);
        RealMatrix realMatrix1 = singularValueDecompositionImpl0.getCovariance(0.0);
        realMatrix1.getColumnDimension();
    }

    @Test(timeout = 4000)
    public void test2322() throws Throwable {
        double[] doubleArray0 = new double[5];
        doubleArray0[0] = (-119.0863408434962);
        ArrayRealVector arrayRealVector0 = new ArrayRealVector(doubleArray0, doubleArray0);
        RealMatrix realMatrix0 = arrayRealVector0.outerProduct(arrayRealVector0);
        SingularValueDecompositionImpl singularValueDecompositionImpl0 = new SingularValueDecompositionImpl(realMatrix0);
        RealMatrix realMatrix1 = singularValueDecompositionImpl0.getCovariance(0.0);
        RealMatrix realMatrix2 = singularValueDecompositionImpl0.getVT();
        realMatrix2.getRowDimension();
    }

    @Test(timeout = 4000)
    public void test2323() throws Throwable {
        double[] doubleArray0 = new double[5];
        doubleArray0[0] = (-119.0863408434962);
        ArrayRealVector arrayRealVector0 = new ArrayRealVector(doubleArray0, doubleArray0);
        RealMatrix realMatrix0 = arrayRealVector0.outerProduct(arrayRealVector0);
        SingularValueDecompositionImpl singularValueDecompositionImpl0 = new SingularValueDecompositionImpl(realMatrix0);
        RealMatrix realMatrix1 = singularValueDecompositionImpl0.getCovariance(0.0);
        RealMatrix realMatrix2 = singularValueDecompositionImpl0.getVT();
        singularValueDecompositionImpl0.getNorm();
    }

    @Test(timeout = 4000)
    public void test2424() throws Throwable {
        double[][] doubleArray0 = new double[2][5];
        double[] doubleArray1 = new double[3];
        doubleArray1[1] = (-1.0);
        doubleArray0[0] = doubleArray1;
        double[] doubleArray2 = new double[3];
        doubleArray2[0] = 1.05;
        doubleArray0[1] = doubleArray2;
        Array2DRowRealMatrix array2DRowRealMatrix0 = new Array2DRowRealMatrix(doubleArray0, true);
        SingularValueDecompositionImpl singularValueDecompositionImpl0 = new SingularValueDecompositionImpl(array2DRowRealMatrix0, Integer.MAX_VALUE);
        RealMatrix realMatrix0 = singularValueDecompositionImpl0.getV();
        singularValueDecompositionImpl0.getConditionNumber();
    }

    @Test(timeout = 4000)
    public void test2425() throws Throwable {
        double[][] doubleArray0 = new double[2][5];
        double[] doubleArray1 = new double[3];
        doubleArray1[1] = (-1.0);
        doubleArray0[0] = doubleArray1;
        double[] doubleArray2 = new double[3];
        doubleArray2[0] = 1.05;
        doubleArray0[1] = doubleArray2;
        Array2DRowRealMatrix array2DRowRealMatrix0 = new Array2DRowRealMatrix(doubleArray0, true);
        SingularValueDecompositionImpl singularValueDecompositionImpl0 = new SingularValueDecompositionImpl(array2DRowRealMatrix0, Integer.MAX_VALUE);
        RealMatrix realMatrix0 = singularValueDecompositionImpl0.getV();
        realMatrix0.getColumnDimension();
    }

    @Test(timeout = 4000)
    public void test2526() throws Throwable {
        double[] doubleArray0 = new double[3];
        Array2DRowRealMatrix array2DRowRealMatrix0 = new Array2DRowRealMatrix(doubleArray0);
        SingularValueDecompositionImpl singularValueDecompositionImpl0 = new SingularValueDecompositionImpl(array2DRowRealMatrix0);
        singularValueDecompositionImpl0.getV();
    }

    @Test(timeout = 4000)
    public void test2627() throws Throwable {
        Array2DRowRealMatrix array2DRowRealMatrix0 = new Array2DRowRealMatrix();
        double[][] doubleArray0 = new double[1][7];
        array2DRowRealMatrix0.data = doubleArray0;
        SingularValueDecompositionImpl singularValueDecompositionImpl0 = new SingularValueDecompositionImpl(array2DRowRealMatrix0, 0);
        singularValueDecompositionImpl0.getU();
    }

    @Test(timeout = 4000)
    public void test2728() throws Throwable {
        double[] doubleArray0 = new double[3];
        doubleArray0[1] = (-1314.9147069);
        OpenMapRealVector openMapRealVector0 = new OpenMapRealVector(doubleArray0);
        RealMatrix realMatrix0 = openMapRealVector0.outerProduct(doubleArray0);
        SingularValueDecompositionImpl singularValueDecompositionImpl0 = new SingularValueDecompositionImpl(realMatrix0);
        singularValueDecompositionImpl0.getU();
        singularValueDecompositionImpl0.getU();
        singularValueDecompositionImpl0.getNorm();
    }

    @Test(timeout = 4000)
    public void test2829() throws Throwable {
        double[] doubleArray0 = new double[11];
        doubleArray0[0] = 0.5;
        Array2DRowRealMatrix array2DRowRealMatrix0 = new Array2DRowRealMatrix(doubleArray0);
        RealMatrix realMatrix0 = array2DRowRealMatrix0.getRowMatrix(0);
        SingularValueDecompositionImpl singularValueDecompositionImpl0 = new SingularValueDecompositionImpl(realMatrix0);
        DecompositionSolver decompositionSolver0 = singularValueDecompositionImpl0.getSolver();
        decompositionSolver0.isNonSingular();
    }

    @Test(timeout = 4000)
    public void test2830() throws Throwable {
        double[] doubleArray0 = new double[11];
        doubleArray0[0] = 0.5;
        Array2DRowRealMatrix array2DRowRealMatrix0 = new Array2DRowRealMatrix(doubleArray0);
        RealMatrix realMatrix0 = array2DRowRealMatrix0.getRowMatrix(0);
        SingularValueDecompositionImpl singularValueDecompositionImpl0 = new SingularValueDecompositionImpl(realMatrix0);
        DecompositionSolver decompositionSolver0 = singularValueDecompositionImpl0.getSolver();
        singularValueDecompositionImpl0.getNorm();
    }

    @Test(timeout = 4000)
    public void test2931() throws Throwable {
        double[] doubleArray0 = new double[3];
        OpenMapRealVector openMapRealVector0 = new OpenMapRealVector(doubleArray0);
        openMapRealVector0.mapLog10ToSelf();
        RealMatrix realMatrix0 = openMapRealVector0.outerProduct(doubleArray0);
        SingularValueDecompositionImpl singularValueDecompositionImpl0 = new SingularValueDecompositionImpl(realMatrix0);
        int int0 = singularValueDecompositionImpl0.getRank();
    }

    @Test(timeout = 4000)
    public void test3032() throws Throwable {
        double[] doubleArray0 = new double[3];
        OpenMapRealVector openMapRealVector0 = new OpenMapRealVector(doubleArray0);
        RealMatrix realMatrix0 = openMapRealVector0.outerProduct(doubleArray0);
        SingularValueDecompositionImpl singularValueDecompositionImpl0 = new SingularValueDecompositionImpl(realMatrix0);
        singularValueDecompositionImpl0.getCovariance(140.5950535212718);
    }

    @Test(timeout = 4000)
    public void test3133() throws Throwable {
        double[] doubleArray0 = new double[5];
        doubleArray0[0] = (-119.0863408434962);
        doubleArray0[2] = (-200.7204561);
        ArrayRealVector arrayRealVector0 = new ArrayRealVector(doubleArray0, doubleArray0);
        RealMatrix realMatrix0 = arrayRealVector0.outerProduct(arrayRealVector0);
        SingularValueDecompositionImpl singularValueDecompositionImpl0 = new SingularValueDecompositionImpl(realMatrix0);
        RealMatrix realMatrix1 = singularValueDecompositionImpl0.getCovariance(1190.52);
        singularValueDecompositionImpl0.getConditionNumber();
    }

    @Test(timeout = 4000)
    public void test3134() throws Throwable {
        double[] doubleArray0 = new double[5];
        doubleArray0[0] = (-119.0863408434962);
        doubleArray0[2] = (-200.7204561);
        ArrayRealVector arrayRealVector0 = new ArrayRealVector(doubleArray0, doubleArray0);
        RealMatrix realMatrix0 = arrayRealVector0.outerProduct(arrayRealVector0);
        SingularValueDecompositionImpl singularValueDecompositionImpl0 = new SingularValueDecompositionImpl(realMatrix0);
        RealMatrix realMatrix1 = singularValueDecompositionImpl0.getCovariance(1190.52);
        realMatrix1.getColumnDimension();
    }

    @Test(timeout = 4000)
    public void test3235() throws Throwable {
        double[][] doubleArray0 = new double[2][5];
        double[] doubleArray1 = new double[3];
        doubleArray1[1] = 1.05;
        doubleArray0[0] = doubleArray1;
        double[] doubleArray2 = new double[3];
        doubleArray2[0] = 1.05;
        doubleArray0[1] = doubleArray2;
        Array2DRowRealMatrix array2DRowRealMatrix0 = new Array2DRowRealMatrix(doubleArray0, true);
        SingularValueDecompositionImpl singularValueDecompositionImpl0 = new SingularValueDecompositionImpl(array2DRowRealMatrix0, Integer.MAX_VALUE);
        DecompositionSolver decompositionSolver0 = singularValueDecompositionImpl0.getSolver();
        singularValueDecompositionImpl0.getRank();
    }

    @Test(timeout = 4000)
    public void test3236() throws Throwable {
        double[][] doubleArray0 = new double[2][5];
        double[] doubleArray1 = new double[3];
        doubleArray1[1] = 1.05;
        doubleArray0[0] = doubleArray1;
        double[] doubleArray2 = new double[3];
        doubleArray2[0] = 1.05;
        doubleArray0[1] = doubleArray2;
        Array2DRowRealMatrix array2DRowRealMatrix0 = new Array2DRowRealMatrix(doubleArray0, true);
        SingularValueDecompositionImpl singularValueDecompositionImpl0 = new SingularValueDecompositionImpl(array2DRowRealMatrix0, Integer.MAX_VALUE);
        DecompositionSolver decompositionSolver0 = singularValueDecompositionImpl0.getSolver();
        decompositionSolver0.isNonSingular();
    }

    @Test(timeout = 4000)
    public void test3237() throws Throwable {
        double[][] doubleArray0 = new double[2][5];
        double[] doubleArray1 = new double[3];
        doubleArray1[1] = 1.05;
        doubleArray0[0] = doubleArray1;
        double[] doubleArray2 = new double[3];
        doubleArray2[0] = 1.05;
        doubleArray0[1] = doubleArray2;
        Array2DRowRealMatrix array2DRowRealMatrix0 = new Array2DRowRealMatrix(doubleArray0, true);
        SingularValueDecompositionImpl singularValueDecompositionImpl0 = new SingularValueDecompositionImpl(array2DRowRealMatrix0, Integer.MAX_VALUE);
        DecompositionSolver decompositionSolver0 = singularValueDecompositionImpl0.getSolver();
        singularValueDecompositionImpl0.getConditionNumber();
    }

    @Test(timeout = 4000)
    public void test3338() throws Throwable {
        double[] doubleArray0 = new double[5];
        doubleArray0[0] = (-119.0863408434962);
        ArrayRealVector arrayRealVector0 = new ArrayRealVector(doubleArray0, doubleArray0);
        RealMatrix realMatrix0 = arrayRealVector0.outerProduct(arrayRealVector0);
        SingularValueDecompositionImpl singularValueDecompositionImpl0 = new SingularValueDecompositionImpl(realMatrix0);
        RealMatrix realMatrix1 = singularValueDecompositionImpl0.getCovariance(0.0);
        realMatrix1.getRowDimension();
    }

    @Test(timeout = 4000)
    public void test3339() throws Throwable {
        double[] doubleArray0 = new double[5];
        doubleArray0[0] = (-119.0863408434962);
        ArrayRealVector arrayRealVector0 = new ArrayRealVector(doubleArray0, doubleArray0);
        RealMatrix realMatrix0 = arrayRealVector0.outerProduct(arrayRealVector0);
        SingularValueDecompositionImpl singularValueDecompositionImpl0 = new SingularValueDecompositionImpl(realMatrix0);
        RealMatrix realMatrix1 = singularValueDecompositionImpl0.getCovariance(0.0);
        RealMatrix realMatrix2 = singularValueDecompositionImpl0.getV();
        singularValueDecompositionImpl0.getNorm();
    }

    @Test(timeout = 4000)
    public void test3340() throws Throwable {
        double[] doubleArray0 = new double[5];
        doubleArray0[0] = (-119.0863408434962);
        ArrayRealVector arrayRealVector0 = new ArrayRealVector(doubleArray0, doubleArray0);
        RealMatrix realMatrix0 = arrayRealVector0.outerProduct(arrayRealVector0);
        SingularValueDecompositionImpl singularValueDecompositionImpl0 = new SingularValueDecompositionImpl(realMatrix0);
        RealMatrix realMatrix1 = singularValueDecompositionImpl0.getCovariance(0.0);
        RealMatrix realMatrix2 = singularValueDecompositionImpl0.getV();
        realMatrix2.getColumnDimension();
    }

    @Test(timeout = 4000)
    public void test3441() throws Throwable {
        double[] doubleArray0 = new double[5];
        doubleArray0[0] = (-119.0863408434962);
        ArrayRealVector arrayRealVector0 = new ArrayRealVector(doubleArray0, doubleArray0);
        RealMatrix realMatrix0 = arrayRealVector0.outerProduct(arrayRealVector0);
        SingularValueDecompositionImpl singularValueDecompositionImpl0 = new SingularValueDecompositionImpl(realMatrix0);
        singularValueDecompositionImpl0.getS();
        RealMatrix realMatrix1 = singularValueDecompositionImpl0.getS();
    }

    @Test(timeout = 4000)
    public void test3442() throws Throwable {
        double[] doubleArray0 = new double[5];
        doubleArray0[0] = (-119.0863408434962);
        ArrayRealVector arrayRealVector0 = new ArrayRealVector(doubleArray0, doubleArray0);
        RealMatrix realMatrix0 = arrayRealVector0.outerProduct(arrayRealVector0);
        SingularValueDecompositionImpl singularValueDecompositionImpl0 = new SingularValueDecompositionImpl(realMatrix0);
        singularValueDecompositionImpl0.getS();
        RealMatrix realMatrix1 = singularValueDecompositionImpl0.getS();
        realMatrix1.getRowDimension();
    }

    @Test(timeout = 4000)
    public void test3443() throws Throwable {
        double[] doubleArray0 = new double[5];
        doubleArray0[0] = (-119.0863408434962);
        ArrayRealVector arrayRealVector0 = new ArrayRealVector(doubleArray0, doubleArray0);
        RealMatrix realMatrix0 = arrayRealVector0.outerProduct(arrayRealVector0);
        SingularValueDecompositionImpl singularValueDecompositionImpl0 = new SingularValueDecompositionImpl(realMatrix0);
        singularValueDecompositionImpl0.getS();
        RealMatrix realMatrix1 = singularValueDecompositionImpl0.getS();
        singularValueDecompositionImpl0.getNorm();
    }

    @Test(timeout = 4000)
    public void test3544() throws Throwable {
        double[] doubleArray0 = new double[5];
        doubleArray0[0] = (-119.0863408434962);
        ArrayRealVector arrayRealVector0 = new ArrayRealVector(doubleArray0, doubleArray0);
        RealMatrix realMatrix0 = arrayRealVector0.outerProduct(arrayRealVector0);
        SingularValueDecompositionImpl singularValueDecompositionImpl0 = new SingularValueDecompositionImpl(realMatrix0);
        singularValueDecompositionImpl0.getUT();
        RealMatrix realMatrix1 = singularValueDecompositionImpl0.getUT();
        singularValueDecompositionImpl0.getConditionNumber();
    }

    @Test(timeout = 4000)
    public void test3545() throws Throwable {
        double[] doubleArray0 = new double[5];
        doubleArray0[0] = (-119.0863408434962);
        ArrayRealVector arrayRealVector0 = new ArrayRealVector(doubleArray0, doubleArray0);
        RealMatrix realMatrix0 = arrayRealVector0.outerProduct(arrayRealVector0);
        SingularValueDecompositionImpl singularValueDecompositionImpl0 = new SingularValueDecompositionImpl(realMatrix0);
        singularValueDecompositionImpl0.getUT();
        RealMatrix realMatrix1 = singularValueDecompositionImpl0.getUT();
        singularValueDecompositionImpl0.getNorm();
    }

    @Test(timeout = 4000)
    public void test3546() throws Throwable {
        double[] doubleArray0 = new double[5];
        doubleArray0[0] = (-119.0863408434962);
        ArrayRealVector arrayRealVector0 = new ArrayRealVector(doubleArray0, doubleArray0);
        RealMatrix realMatrix0 = arrayRealVector0.outerProduct(arrayRealVector0);
        SingularValueDecompositionImpl singularValueDecompositionImpl0 = new SingularValueDecompositionImpl(realMatrix0);
        singularValueDecompositionImpl0.getUT();
        RealMatrix realMatrix1 = singularValueDecompositionImpl0.getUT();
    }

    @Test(timeout = 4000)
    public void test3647() throws Throwable {
        double[][] doubleArray0 = new double[1][3];
        Array2DRowRealMatrix array2DRowRealMatrix0 = new Array2DRowRealMatrix(doubleArray0);
        SingularValueDecompositionImpl singularValueDecompositionImpl0 = new SingularValueDecompositionImpl(array2DRowRealMatrix0, 2);
        singularValueDecompositionImpl0.getNorm();
    }

    @Test(timeout = 4000)
    public void test3748() throws Throwable {
        double[] doubleArray0 = new double[5];
        ArrayRealVector arrayRealVector0 = new ArrayRealVector(doubleArray0, doubleArray0);
        RealMatrix realMatrix0 = arrayRealVector0.outerProduct(arrayRealVector0);
        SingularValueDecompositionImpl singularValueDecompositionImpl0 = new SingularValueDecompositionImpl(realMatrix0);
        double[] doubleArray1 = singularValueDecompositionImpl0.getSingularValues();
    }

    @Test(timeout = 4000)
    public void test3849() throws Throwable {
        Array2DRowRealMatrix array2DRowRealMatrix0 = new Array2DRowRealMatrix();
        double[][] doubleArray0 = new double[1][7];
        array2DRowRealMatrix0.data = doubleArray0;
        SingularValueDecompositionImpl singularValueDecompositionImpl0 = new SingularValueDecompositionImpl(array2DRowRealMatrix0);
        singularValueDecompositionImpl0.getConditionNumber();
    }
}
