/*
 * This file was automatically generated by EvoSuite
 * Mon Oct 16 17:54:49 GMT 2023
 */
package org.apache.commons.math.stat.correlation;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import org.apache.commons.math.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math.linear.Array2DRowRealMatrix;
import org.apache.commons.math.linear.ArrayRealVector;
import org.apache.commons.math.linear.BlockRealMatrix;
import org.apache.commons.math.linear.DefaultRealMatrixPreservingVisitor;
import org.apache.commons.math.linear.OpenMapRealMatrix;
import org.apache.commons.math.linear.OpenMapRealVector;
import org.apache.commons.math.linear.RealMatrix;
import org.apache.commons.math.linear.RealMatrixPreservingVisitor;
import org.apache.commons.math.linear.RealVector;
import org.apache.commons.math.stat.correlation.Covariance;
import org.apache.commons.math.stat.correlation.PearsonsCorrelation;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class PearsonsCorrelation_ESTest extends PearsonsCorrelation_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        PearsonsCorrelation pearsonsCorrelation0 = new PearsonsCorrelation();
        double[] doubleArray0 = new double[1];
        pearsonsCorrelation0.correlation(doubleArray0, doubleArray0);
        pearsonsCorrelation0.getCorrelationMatrix();
    }

    @Test(timeout = 4000)
    public void test021() throws Throwable {
        double[] doubleArray0 = new double[1];
        doubleArray0[0] = 1576.970357826;
        Array2DRowRealMatrix array2DRowRealMatrix0 = new Array2DRowRealMatrix(doubleArray0);
        RealMatrix realMatrix0 = array2DRowRealMatrix0.copy();
        PearsonsCorrelation pearsonsCorrelation0 = new PearsonsCorrelation(realMatrix0);
        pearsonsCorrelation0.covarianceToCorrelation(realMatrix0);
    }

    @Test(timeout = 4000)
    public void test052() throws Throwable {
        double[][] doubleArray0 = new double[0][9];
        PearsonsCorrelation pearsonsCorrelation0 = null;
        pearsonsCorrelation0 = new PearsonsCorrelation(doubleArray0);
    }

    @Test(timeout = 4000)
    public void test083() throws Throwable {
        Covariance covariance0 = new Covariance();
        PearsonsCorrelation pearsonsCorrelation0 = null;
        pearsonsCorrelation0 = new PearsonsCorrelation(covariance0);
    }

    @Test(timeout = 4000)
    public void test104() throws Throwable {
        Covariance covariance0 = new Covariance();
        PearsonsCorrelation pearsonsCorrelation0 = null;
        pearsonsCorrelation0 = new PearsonsCorrelation(covariance0);
    }

    @Test(timeout = 4000)
    public void test125() throws Throwable {
        double[][] doubleArray0 = new double[0][8];
        PearsonsCorrelation pearsonsCorrelation0 = null;
        pearsonsCorrelation0 = new PearsonsCorrelation(doubleArray0);
    }

    @Test(timeout = 4000)
    public void test146() throws Throwable {
        RealMatrix realMatrix0 = null;
        PearsonsCorrelation pearsonsCorrelation0 = null;
        pearsonsCorrelation0 = new PearsonsCorrelation((RealMatrix) null);
    }

    @Test(timeout = 4000)
    public void test157() throws Throwable {
        RealMatrix realMatrix0 = null;
        int int0 = 1404;
        PearsonsCorrelation pearsonsCorrelation0 = null;
        pearsonsCorrelation0 = new PearsonsCorrelation((RealMatrix) null, 1404);
    }

    @Test(timeout = 4000)
    public void test178() throws Throwable {
        Array2DRowRealMatrix array2DRowRealMatrix0 = new Array2DRowRealMatrix();
        int int0 = 162;
        PearsonsCorrelation pearsonsCorrelation0 = null;
        pearsonsCorrelation0 = new PearsonsCorrelation(array2DRowRealMatrix0, 162);
    }

    @Test(timeout = 4000)
    public void test239() throws Throwable {
        PearsonsCorrelation pearsonsCorrelation0 = new PearsonsCorrelation();
        pearsonsCorrelation0.getCorrelationStandardErrors();
    }

    @Test(timeout = 4000)
    public void test2810() throws Throwable {
        RealMatrix realMatrix0 = null;
        PearsonsCorrelation pearsonsCorrelation0 = new PearsonsCorrelation((RealMatrix) null, 1667);
        pearsonsCorrelation0.getCorrelationStandardErrors();
    }

    @Test(timeout = 4000)
    public void test2911() throws Throwable {
        PearsonsCorrelation pearsonsCorrelation0 = new PearsonsCorrelation();
        pearsonsCorrelation0.getCorrelationPValues();
    }

    @Test(timeout = 4000)
    public void test3112() throws Throwable {
        double[][] doubleArrayArray0 = null;
        PearsonsCorrelation pearsonsCorrelation0 = null;
        pearsonsCorrelation0 = new PearsonsCorrelation((double[][]) null);
    }

    @Test(timeout = 4000)
    public void test3213() throws Throwable {
        PearsonsCorrelation pearsonsCorrelation0 = new PearsonsCorrelation();
        double[] doubleArray0 = new double[0];
        Array2DRowRealMatrix array2DRowRealMatrix0 = new Array2DRowRealMatrix(doubleArray0);
        int int0 = 2892;
        pearsonsCorrelation0.correlation(doubleArray0, doubleArray0);
    }

    @Test(timeout = 4000)
    public void test3414() throws Throwable {
        PearsonsCorrelation pearsonsCorrelation0 = new PearsonsCorrelation();
        pearsonsCorrelation0.getCorrelationMatrix();
        pearsonsCorrelation0.computeCorrelationMatrix((RealMatrix) null);
    }

    @Test(timeout = 4000)
    public void test3615() throws Throwable {
        double[] doubleArray0 = new double[9];
        doubleArray0[0] = 1576.970357826;
        Array2DRowRealMatrix array2DRowRealMatrix0 = new Array2DRowRealMatrix(doubleArray0);
        RealMatrix realMatrix0 = array2DRowRealMatrix0.copy();
        PearsonsCorrelation pearsonsCorrelation0 = new PearsonsCorrelation(realMatrix0);
        pearsonsCorrelation0.covarianceToCorrelation(realMatrix0);
    }

    @Test(timeout = 4000)
    public void test3716() throws Throwable {
        double[] doubleArray0 = new double[2];
        doubleArray0[0] = 1576.970357826;
        Array2DRowRealMatrix array2DRowRealMatrix0 = new Array2DRowRealMatrix(doubleArray0);
        Array2DRowRealMatrix array2DRowRealMatrix1 = (Array2DRowRealMatrix) array2DRowRealMatrix0.copy();
        PearsonsCorrelation pearsonsCorrelation0 = new PearsonsCorrelation(array2DRowRealMatrix1);
        pearsonsCorrelation0.correlation(doubleArray0, doubleArray0);
    }
}
