/*
 * This file was automatically generated by EvoSuite
 * Mon Oct 16 05:54:39 GMT 2023
 */
package org.apache.commons.math3.optim.nonlinear.vector.jacobian;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import org.apache.commons.math3.exception.MathUnsupportedOperationException;
import org.apache.commons.math3.linear.DiagonalMatrix;
import org.apache.commons.math3.optim.OptimizationData;
import org.apache.commons.math3.optim.PointVectorValuePair;
import org.apache.commons.math3.optim.SimplePointChecker;
import org.apache.commons.math3.optim.SimpleVectorValueChecker;
import org.apache.commons.math3.optim.nonlinear.vector.Weight;
import org.apache.commons.math3.optim.nonlinear.vector.jacobian.GaussNewtonOptimizer;
import org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class AbstractLeastSquaresOptimizer_ESTest extends AbstractLeastSquaresOptimizer_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        SimpleVectorValueChecker simpleVectorValueChecker0 = new SimpleVectorValueChecker(0.0, 0.0);
        LevenbergMarquardtOptimizer levenbergMarquardtOptimizer0 = new LevenbergMarquardtOptimizer(simpleVectorValueChecker0);
        OptimizationData[] optimizationDataArray0 = new OptimizationData[7];
        double[] doubleArray0 = new double[3];
        doubleArray0[0] = (double) 1;
        doubleArray0[1] = (double) 11;
        doubleArray0[2] = (double) 32;
        DiagonalMatrix diagonalMatrix0 = new DiagonalMatrix(doubleArray0);
        Weight weight0 = new Weight(diagonalMatrix0);
        optimizationDataArray0[6] = (OptimizationData) weight0;
        levenbergMarquardtOptimizer0.optimize(optimizationDataArray0);
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        LevenbergMarquardtOptimizer levenbergMarquardtOptimizer0 = new LevenbergMarquardtOptimizer(0.0, 0.0, 1390.983231656, 0.0, 0.0);
        double[] doubleArray0 = new double[4];
        levenbergMarquardtOptimizer0.computeCost(doubleArray0);
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        LevenbergMarquardtOptimizer levenbergMarquardtOptimizer0 = new LevenbergMarquardtOptimizer((-453.9606066), (-453.9606066), (-453.9606066), (-453.9606066), (-453.9606066));
        levenbergMarquardtOptimizer0.setCost(74.817);
        double double0 = levenbergMarquardtOptimizer0.getChiSquare();
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        LevenbergMarquardtOptimizer levenbergMarquardtOptimizer0 = new LevenbergMarquardtOptimizer();
        OptimizationData[] optimizationDataArray0 = new OptimizationData[6];
        DiagonalMatrix diagonalMatrix0 = new DiagonalMatrix(719);
        Weight weight0 = new Weight(diagonalMatrix0);
        optimizationDataArray0[1] = (OptimizationData) weight0;
        // Undeclared exception!
        levenbergMarquardtOptimizer0.optimize(optimizationDataArray0);
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        double[] doubleArray0 = new double[0];
        LevenbergMarquardtOptimizer levenbergMarquardtOptimizer0 = new LevenbergMarquardtOptimizer();
        OptimizationData[] optimizationDataArray0 = new OptimizationData[8];
        DiagonalMatrix diagonalMatrix0 = new DiagonalMatrix(doubleArray0);
        Weight weight0 = new Weight(diagonalMatrix0);
        optimizationDataArray0[4] = (OptimizationData) weight0;
        levenbergMarquardtOptimizer0.optimize(optimizationDataArray0);
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        SimplePointChecker<PointVectorValuePair> simplePointChecker0 = new SimplePointChecker<PointVectorValuePair>((-1.0), (-1.0));
        GaussNewtonOptimizer gaussNewtonOptimizer0 = new GaussNewtonOptimizer(true, simplePointChecker0);
        double[] doubleArray0 = new double[5];
        gaussNewtonOptimizer0.computeWeightedJacobian(doubleArray0);
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        LevenbergMarquardtOptimizer levenbergMarquardtOptimizer0 = new LevenbergMarquardtOptimizer();
        double[] doubleArray0 = new double[0];
        levenbergMarquardtOptimizer0.computeSigma(doubleArray0, 0.0);
    }

    @Test(timeout = 4000)
    public void test077() throws Throwable {
        LevenbergMarquardtOptimizer levenbergMarquardtOptimizer0 = new LevenbergMarquardtOptimizer();
        double[] doubleArray0 = new double[8];
        levenbergMarquardtOptimizer0.computeResiduals(doubleArray0);
    }

    @Test(timeout = 4000)
    public void test088() throws Throwable {
        LevenbergMarquardtOptimizer levenbergMarquardtOptimizer0 = new LevenbergMarquardtOptimizer((-453.9606066), (-453.9606066), (-453.9606066), (-453.9606066), (-453.9606066));
        double double0 = levenbergMarquardtOptimizer0.getChiSquare();
    }

    @Test(timeout = 4000)
    public void test099() throws Throwable {
        LevenbergMarquardtOptimizer levenbergMarquardtOptimizer0 = new LevenbergMarquardtOptimizer();
        levenbergMarquardtOptimizer0.getWeightSquareRoot();
    }

    @Test(timeout = 4000)
    public void test1010() throws Throwable {
        LevenbergMarquardtOptimizer levenbergMarquardtOptimizer0 = new LevenbergMarquardtOptimizer();
        OptimizationData[] optimizationDataArray0 = new OptimizationData[8];
        double[] doubleArray0 = new double[6];
        DiagonalMatrix diagonalMatrix0 = new DiagonalMatrix(doubleArray0);
        Weight weight0 = new Weight(diagonalMatrix0);
        optimizationDataArray0[1] = (OptimizationData) weight0;
        levenbergMarquardtOptimizer0.optimize(optimizationDataArray0);
    }

    @Test(timeout = 4000)
    public void test1111() throws Throwable {
        LevenbergMarquardtOptimizer levenbergMarquardtOptimizer0 = new LevenbergMarquardtOptimizer(9.394561, 9.394561, 9.394561, 9.394561, 9.394561);
        levenbergMarquardtOptimizer0.getRMS();
    }

    @Test(timeout = 4000)
    public void test1212() throws Throwable {
        LevenbergMarquardtOptimizer levenbergMarquardtOptimizer0 = new LevenbergMarquardtOptimizer();
        OptimizationData[] optimizationDataArray0 = new OptimizationData[2];
        levenbergMarquardtOptimizer0.optimize(optimizationDataArray0);
    }

    @Test(timeout = 4000)
    public void test1313() throws Throwable {
        LevenbergMarquardtOptimizer levenbergMarquardtOptimizer0 = new LevenbergMarquardtOptimizer();
        double[] doubleArray0 = new double[7];
        levenbergMarquardtOptimizer0.computeCovariances(doubleArray0, 0.0);
    }
}
