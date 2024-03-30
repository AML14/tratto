/*
 * This file was automatically generated by EvoSuite
 * Mon Oct 16 21:37:29 GMT 2023
 */
package org.apache.commons.math.estimation;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import org.apache.commons.math.estimation.EstimatedParameter;
import org.apache.commons.math.estimation.EstimationProblem;
import org.apache.commons.math.estimation.GaussNewtonEstimator;
import org.apache.commons.math.estimation.LevenbergMarquardtEstimator;
import org.apache.commons.math.estimation.SimpleEstimationProblem;
import org.apache.commons.math.estimation.WeightedMeasurement;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class AbstractEstimator_ESTest extends AbstractEstimator_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        SimpleEstimationProblem simpleEstimationProblem0 = new SimpleEstimationProblem();
        WeightedMeasurement weightedMeasurement0 = mock(WeightedMeasurement.class, new ViolatedAssumptionAnswer());
        doReturn(0.0).when(weightedMeasurement0).getPartial(any(org.apache.commons.math.estimation.EstimatedParameter.class));
        doReturn(0.0).when(weightedMeasurement0).getResidual();
        doReturn(0.0, (-1.0)).when(weightedMeasurement0).getWeight();
        EstimatedParameter estimatedParameter0 = new EstimatedParameter("LU decomposition requires that the matrix be square.", 1.0);
        simpleEstimationProblem0.addParameter(estimatedParameter0);
        simpleEstimationProblem0.addMeasurement(weightedMeasurement0);
        WeightedMeasurement weightedMeasurement1 = mock(WeightedMeasurement.class, new ViolatedAssumptionAnswer());
        doReturn(0.0).when(weightedMeasurement1).getPartial(any(org.apache.commons.math.estimation.EstimatedParameter.class));
        doReturn(0.0).when(weightedMeasurement1).getResidual();
        doReturn(1590.261196097777, Double.POSITIVE_INFINITY).when(weightedMeasurement1).getWeight();
        simpleEstimationProblem0.addMeasurement(weightedMeasurement1);
        LevenbergMarquardtEstimator levenbergMarquardtEstimator0 = new LevenbergMarquardtEstimator();
        levenbergMarquardtEstimator0.initializeEstimate(simpleEstimationProblem0);
        levenbergMarquardtEstimator0.guessParametersErrors(simpleEstimationProblem0);
        levenbergMarquardtEstimator0.getJacobianEvaluations();
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        LevenbergMarquardtEstimator levenbergMarquardtEstimator0 = new LevenbergMarquardtEstimator();
        SimpleEstimationProblem simpleEstimationProblem0 = new SimpleEstimationProblem();
        EstimatedParameter estimatedParameter0 = new EstimatedParameter("unable to compute covariances: singular problem", 0.0, true);
        estimatedParameter0.setBound(false);
        simpleEstimationProblem0.addParameter(estimatedParameter0);
        levenbergMarquardtEstimator0.initializeEstimate(simpleEstimationProblem0);
        levenbergMarquardtEstimator0.guessParametersErrors(simpleEstimationProblem0);
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        LevenbergMarquardtEstimator levenbergMarquardtEstimator0 = new LevenbergMarquardtEstimator();
        SimpleEstimationProblem simpleEstimationProblem0 = new SimpleEstimationProblem();
        levenbergMarquardtEstimator0.rows = (-108);
        levenbergMarquardtEstimator0.guessParametersErrors(simpleEstimationProblem0);
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        GaussNewtonEstimator gaussNewtonEstimator0 = new GaussNewtonEstimator((-2672), (-2672), (-2672));
        SimpleEstimationProblem simpleEstimationProblem0 = new SimpleEstimationProblem();
        simpleEstimationProblem0.addParameter((EstimatedParameter) null);
        gaussNewtonEstimator0.guessParametersErrors(simpleEstimationProblem0);
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        LevenbergMarquardtEstimator levenbergMarquardtEstimator0 = new LevenbergMarquardtEstimator();
        SimpleEstimationProblem simpleEstimationProblem0 = new SimpleEstimationProblem();
        WeightedMeasurement weightedMeasurement0 = mock(WeightedMeasurement.class, new ViolatedAssumptionAnswer());
        doReturn(0.0, 0.0, 0.0, 0.0).when(weightedMeasurement0).getPartial(any(org.apache.commons.math.estimation.EstimatedParameter.class));
        doReturn(0.0).when(weightedMeasurement0).getResidual();
        doReturn(0.0, 0.0, 0.0, 0.0).when(weightedMeasurement0).getWeight();
        simpleEstimationProblem0.addMeasurement(weightedMeasurement0);
        EstimatedParameter estimatedParameter0 = new EstimatedParameter("vX~uBD,J#n/L2^QZ8z", (-2246.12086661453));
        simpleEstimationProblem0.addParameter(estimatedParameter0);
        simpleEstimationProblem0.addParameter(estimatedParameter0);
        levenbergMarquardtEstimator0.estimate(simpleEstimationProblem0);
        levenbergMarquardtEstimator0.getCovariances(simpleEstimationProblem0);
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        LevenbergMarquardtEstimator levenbergMarquardtEstimator0 = new LevenbergMarquardtEstimator();
        SimpleEstimationProblem simpleEstimationProblem0 = new SimpleEstimationProblem();
        WeightedMeasurement weightedMeasurement0 = mock(WeightedMeasurement.class, new ViolatedAssumptionAnswer());
        doReturn(Double.NEGATIVE_INFINITY).when(weightedMeasurement0).getResidual();
        doReturn(0.0).when(weightedMeasurement0).getWeight();
        simpleEstimationProblem0.addMeasurement(weightedMeasurement0);
        double double0 = levenbergMarquardtEstimator0.getRMS(simpleEstimationProblem0);
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        LevenbergMarquardtEstimator levenbergMarquardtEstimator0 = new LevenbergMarquardtEstimator();
        SimpleEstimationProblem simpleEstimationProblem0 = new SimpleEstimationProblem();
        WeightedMeasurement weightedMeasurement0 = mock(WeightedMeasurement.class, new ViolatedAssumptionAnswer());
        doReturn(0.0).when(weightedMeasurement0).getResidual();
        doReturn(0.0).when(weightedMeasurement0).getWeight();
        simpleEstimationProblem0.addMeasurement(weightedMeasurement0);
        levenbergMarquardtEstimator0.initializeEstimate(simpleEstimationProblem0);
        double double0 = levenbergMarquardtEstimator0.getRMS(simpleEstimationProblem0);
        levenbergMarquardtEstimator0.getCostEvaluations();
    }

    @Test(timeout = 4000)
    public void test067() throws Throwable {
        LevenbergMarquardtEstimator levenbergMarquardtEstimator0 = new LevenbergMarquardtEstimator();
        SimpleEstimationProblem simpleEstimationProblem0 = new SimpleEstimationProblem();
        WeightedMeasurement weightedMeasurement0 = mock(WeightedMeasurement.class, new ViolatedAssumptionAnswer());
        doReturn(0.0).when(weightedMeasurement0).getResidual();
        doReturn(0.0).when(weightedMeasurement0).getWeight();
        simpleEstimationProblem0.addMeasurement(weightedMeasurement0);
        levenbergMarquardtEstimator0.initializeEstimate(simpleEstimationProblem0);
        double double0 = levenbergMarquardtEstimator0.getRMS(simpleEstimationProblem0);
    }

    @Test(timeout = 4000)
    public void test068() throws Throwable {
        LevenbergMarquardtEstimator levenbergMarquardtEstimator0 = new LevenbergMarquardtEstimator();
        SimpleEstimationProblem simpleEstimationProblem0 = new SimpleEstimationProblem();
        WeightedMeasurement weightedMeasurement0 = mock(WeightedMeasurement.class, new ViolatedAssumptionAnswer());
        doReturn(0.0).when(weightedMeasurement0).getResidual();
        doReturn(0.0).when(weightedMeasurement0).getWeight();
        simpleEstimationProblem0.addMeasurement(weightedMeasurement0);
        levenbergMarquardtEstimator0.initializeEstimate(simpleEstimationProblem0);
        double double0 = levenbergMarquardtEstimator0.getRMS(simpleEstimationProblem0);
        levenbergMarquardtEstimator0.getJacobianEvaluations();
    }

    @Test(timeout = 4000)
    public void test079() throws Throwable {
        LevenbergMarquardtEstimator levenbergMarquardtEstimator0 = new LevenbergMarquardtEstimator();
        SimpleEstimationProblem simpleEstimationProblem0 = new SimpleEstimationProblem();
        WeightedMeasurement weightedMeasurement0 = mock(WeightedMeasurement.class, new ViolatedAssumptionAnswer());
        doReturn(0.0).when(weightedMeasurement0).getResidual();
        doReturn(0.0).when(weightedMeasurement0).getWeight();
        simpleEstimationProblem0.addMeasurement(weightedMeasurement0);
        WeightedMeasurement weightedMeasurement1 = mock(WeightedMeasurement.class, new ViolatedAssumptionAnswer());
        doReturn(0.0).when(weightedMeasurement1).getResidual();
        doReturn(0.0).when(weightedMeasurement1).getWeight();
        simpleEstimationProblem0.addMeasurement(weightedMeasurement1);
        double double0 = levenbergMarquardtEstimator0.getRMS(simpleEstimationProblem0);
    }

    @Test(timeout = 4000)
    public void test0810() throws Throwable {
        LevenbergMarquardtEstimator levenbergMarquardtEstimator0 = new LevenbergMarquardtEstimator();
        levenbergMarquardtEstimator0.rows = (-2563);
        levenbergMarquardtEstimator0.updateResidualsAndCost();
        levenbergMarquardtEstimator0.getCostEvaluations();
    }

    @Test(timeout = 4000)
    public void test0911() throws Throwable {
        LevenbergMarquardtEstimator levenbergMarquardtEstimator0 = new LevenbergMarquardtEstimator();
        levenbergMarquardtEstimator0.setMaxCostEval(1);
        levenbergMarquardtEstimator0.updateResidualsAndCost();
        levenbergMarquardtEstimator0.getCostEvaluations();
    }

    @Test(timeout = 4000)
    public void test1012() throws Throwable {
        LevenbergMarquardtEstimator levenbergMarquardtEstimator0 = new LevenbergMarquardtEstimator();
        SimpleEstimationProblem simpleEstimationProblem0 = new SimpleEstimationProblem();
        WeightedMeasurement weightedMeasurement0 = mock(WeightedMeasurement.class, new ViolatedAssumptionAnswer());
        doReturn(0.001, Double.POSITIVE_INFINITY).when(weightedMeasurement0).getResidual();
        doReturn(0.0, 0.001, 0.001, 0.001, 0.0).when(weightedMeasurement0).getWeight();
        simpleEstimationProblem0.addMeasurement(weightedMeasurement0);
        levenbergMarquardtEstimator0.estimate(simpleEstimationProblem0);
        levenbergMarquardtEstimator0.cols = (-3648);
        levenbergMarquardtEstimator0.guessParametersErrors(simpleEstimationProblem0);
    }

    @Test(timeout = 4000)
    public void test1113() throws Throwable {
        LevenbergMarquardtEstimator levenbergMarquardtEstimator0 = new LevenbergMarquardtEstimator();
        SimpleEstimationProblem simpleEstimationProblem0 = new SimpleEstimationProblem();
        WeightedMeasurement weightedMeasurement0 = mock(WeightedMeasurement.class, new ViolatedAssumptionAnswer());
        doReturn(0.0).when(weightedMeasurement0).getResidual();
        doReturn(0.0, 0.0, 0.0).when(weightedMeasurement0).getWeight();
        simpleEstimationProblem0.addMeasurement(weightedMeasurement0);
        WeightedMeasurement weightedMeasurement1 = mock(WeightedMeasurement.class, new ViolatedAssumptionAnswer());
        doReturn(0.0).when(weightedMeasurement1).getResidual();
        doReturn(0.0, 0.0, 0.0).when(weightedMeasurement1).getWeight();
        simpleEstimationProblem0.addMeasurement(weightedMeasurement1);
        levenbergMarquardtEstimator0.estimate(simpleEstimationProblem0);
        levenbergMarquardtEstimator0.getJacobianEvaluations();
    }

    @Test(timeout = 4000)
    public void test1214() throws Throwable {
        LevenbergMarquardtEstimator levenbergMarquardtEstimator0 = new LevenbergMarquardtEstimator();
        SimpleEstimationProblem simpleEstimationProblem0 = new SimpleEstimationProblem();
        WeightedMeasurement weightedMeasurement0 = mock(WeightedMeasurement.class, new ViolatedAssumptionAnswer());
        doReturn(0.0).when(weightedMeasurement0).getResidual();
        doReturn(0.0).when(weightedMeasurement0).getWeight();
        simpleEstimationProblem0.addMeasurement(weightedMeasurement0);
        levenbergMarquardtEstimator0.initializeEstimate(simpleEstimationProblem0);
        levenbergMarquardtEstimator0.rows = (-1);
        levenbergMarquardtEstimator0.guessParametersErrors(simpleEstimationProblem0);
    }

    @Test(timeout = 4000)
    public void test1315() throws Throwable {
        LevenbergMarquardtEstimator levenbergMarquardtEstimator0 = new LevenbergMarquardtEstimator();
        SimpleEstimationProblem simpleEstimationProblem0 = new SimpleEstimationProblem();
        levenbergMarquardtEstimator0.estimate(simpleEstimationProblem0);
        levenbergMarquardtEstimator0.getJacobianEvaluations();
    }

    @Test(timeout = 4000)
    public void test1416() throws Throwable {
        LevenbergMarquardtEstimator levenbergMarquardtEstimator0 = new LevenbergMarquardtEstimator();
        SimpleEstimationProblem simpleEstimationProblem0 = new SimpleEstimationProblem();
        WeightedMeasurement weightedMeasurement0 = mock(WeightedMeasurement.class, new ViolatedAssumptionAnswer());
        doReturn(0.0, 480.34893).when(weightedMeasurement0).getResidual();
        doReturn(0.0, 0.0, 0.0, 1.0E-11).when(weightedMeasurement0).getWeight();
        simpleEstimationProblem0.addMeasurement(weightedMeasurement0);
        levenbergMarquardtEstimator0.estimate(simpleEstimationProblem0);
        double double0 = levenbergMarquardtEstimator0.getRMS(simpleEstimationProblem0);
        levenbergMarquardtEstimator0.getCostEvaluations();
    }

    @Test(timeout = 4000)
    public void test1417() throws Throwable {
        LevenbergMarquardtEstimator levenbergMarquardtEstimator0 = new LevenbergMarquardtEstimator();
        SimpleEstimationProblem simpleEstimationProblem0 = new SimpleEstimationProblem();
        WeightedMeasurement weightedMeasurement0 = mock(WeightedMeasurement.class, new ViolatedAssumptionAnswer());
        doReturn(0.0, 480.34893).when(weightedMeasurement0).getResidual();
        doReturn(0.0, 0.0, 0.0, 1.0E-11).when(weightedMeasurement0).getWeight();
        simpleEstimationProblem0.addMeasurement(weightedMeasurement0);
        levenbergMarquardtEstimator0.estimate(simpleEstimationProblem0);
        double double0 = levenbergMarquardtEstimator0.getRMS(simpleEstimationProblem0);
    }

    @Test(timeout = 4000)
    public void test1518() throws Throwable {
        LevenbergMarquardtEstimator levenbergMarquardtEstimator0 = new LevenbergMarquardtEstimator();
        levenbergMarquardtEstimator0.incrementJacobianEvaluationsCounter();
        int int0 = levenbergMarquardtEstimator0.getJacobianEvaluations();
    }

    @Test(timeout = 4000)
    public void test1619() throws Throwable {
        SimpleEstimationProblem simpleEstimationProblem0 = new SimpleEstimationProblem();
        EstimatedParameter estimatedParameter0 = new EstimatedParameter("LU decomposition requires that the matrix be square.", 1.0);
        simpleEstimationProblem0.addParameter(estimatedParameter0);
        WeightedMeasurement weightedMeasurement0 = mock(WeightedMeasurement.class, new ViolatedAssumptionAnswer());
        doReturn((-1502.78691445)).when(weightedMeasurement0).getPartial(any(org.apache.commons.math.estimation.EstimatedParameter.class));
        doReturn((-1.0)).when(weightedMeasurement0).getWeight();
        simpleEstimationProblem0.addMeasurement(weightedMeasurement0);
        LevenbergMarquardtEstimator levenbergMarquardtEstimator0 = new LevenbergMarquardtEstimator();
        levenbergMarquardtEstimator0.initializeEstimate(simpleEstimationProblem0);
        levenbergMarquardtEstimator0.getCovariances(simpleEstimationProblem0);
        levenbergMarquardtEstimator0.getJacobianEvaluations();
    }

    @Test(timeout = 4000)
    public void test1720() throws Throwable {
        LevenbergMarquardtEstimator levenbergMarquardtEstimator0 = new LevenbergMarquardtEstimator();
        levenbergMarquardtEstimator0.updateResidualsAndCost();
        int int0 = levenbergMarquardtEstimator0.getCostEvaluations();
    }

    @Test(timeout = 4000)
    public void test1821() throws Throwable {
        GaussNewtonEstimator gaussNewtonEstimator0 = new GaussNewtonEstimator(0, 584.6735773400344, 0);
        SimpleEstimationProblem simpleEstimationProblem0 = new SimpleEstimationProblem();
        WeightedMeasurement weightedMeasurement0 = mock(WeightedMeasurement.class, new ViolatedAssumptionAnswer());
        doReturn((-1250.2)).when(weightedMeasurement0).getResidual();
        doReturn(584.6735773400344).when(weightedMeasurement0).getWeight();
        simpleEstimationProblem0.addMeasurement(weightedMeasurement0);
        double double0 = gaussNewtonEstimator0.getChiSquare(simpleEstimationProblem0);
    }

    @Test(timeout = 4000)
    public void test2022() throws Throwable {
        LevenbergMarquardtEstimator levenbergMarquardtEstimator0 = new LevenbergMarquardtEstimator();
        levenbergMarquardtEstimator0.rows = 1278;
        levenbergMarquardtEstimator0.updateResidualsAndCost();
    }

    @Test(timeout = 4000)
    public void test2123() throws Throwable {
        LevenbergMarquardtEstimator levenbergMarquardtEstimator0 = new LevenbergMarquardtEstimator();
        SimpleEstimationProblem simpleEstimationProblem0 = new SimpleEstimationProblem();
        levenbergMarquardtEstimator0.initializeEstimate(simpleEstimationProblem0);
        levenbergMarquardtEstimator0.rows = 2786;
        levenbergMarquardtEstimator0.updateResidualsAndCost();
    }

    @Test(timeout = 4000)
    public void test2224() throws Throwable {
        GaussNewtonEstimator gaussNewtonEstimator0 = new GaussNewtonEstimator(0, 4984.32166029148, (-1721.36667));
        gaussNewtonEstimator0.updateJacobian();
    }

    @Test(timeout = 4000)
    public void test2325() throws Throwable {
        LevenbergMarquardtEstimator levenbergMarquardtEstimator0 = new LevenbergMarquardtEstimator();
        levenbergMarquardtEstimator0.initializeEstimate((EstimationProblem) null);
    }

    @Test(timeout = 4000)
    public void test2426() throws Throwable {
        LevenbergMarquardtEstimator levenbergMarquardtEstimator0 = new LevenbergMarquardtEstimator();
        SimpleEstimationProblem simpleEstimationProblem0 = new SimpleEstimationProblem();
        WeightedMeasurement weightedMeasurement0 = mock(WeightedMeasurement.class, new ViolatedAssumptionAnswer());
        doReturn(0.0).when(weightedMeasurement0).getResidual();
        doReturn(0.0).when(weightedMeasurement0).getWeight();
        simpleEstimationProblem0.addMeasurement(weightedMeasurement0);
        levenbergMarquardtEstimator0.guessParametersErrors(simpleEstimationProblem0);
    }

    @Test(timeout = 4000)
    public void test2527() throws Throwable {
        LevenbergMarquardtEstimator levenbergMarquardtEstimator0 = new LevenbergMarquardtEstimator();
        SimpleEstimationProblem simpleEstimationProblem0 = new SimpleEstimationProblem();
        simpleEstimationProblem0.addMeasurement((WeightedMeasurement) null);
        levenbergMarquardtEstimator0.getRMS(simpleEstimationProblem0);
    }

    @Test(timeout = 4000)
    public void test2628() throws Throwable {
        LevenbergMarquardtEstimator levenbergMarquardtEstimator0 = new LevenbergMarquardtEstimator();
        SimpleEstimationProblem simpleEstimationProblem0 = new SimpleEstimationProblem();
        levenbergMarquardtEstimator0.getCovariances(simpleEstimationProblem0);
    }

    @Test(timeout = 4000)
    public void test2729() throws Throwable {
        GaussNewtonEstimator gaussNewtonEstimator0 = new GaussNewtonEstimator(938, 938, 938);
        SimpleEstimationProblem simpleEstimationProblem0 = new SimpleEstimationProblem();
        gaussNewtonEstimator0.initializeEstimate(simpleEstimationProblem0);
        gaussNewtonEstimator0.getCovariances(simpleEstimationProblem0);
    }

    @Test(timeout = 4000)
    public void test2830() throws Throwable {
        LevenbergMarquardtEstimator levenbergMarquardtEstimator0 = new LevenbergMarquardtEstimator();
        SimpleEstimationProblem simpleEstimationProblem0 = new SimpleEstimationProblem();
        levenbergMarquardtEstimator0.initializeEstimate(simpleEstimationProblem0);
        EstimatedParameter estimatedParameter0 = new EstimatedParameter("Caused by: ", 285.0);
        simpleEstimationProblem0.addParameter(estimatedParameter0);
        simpleEstimationProblem0.addMeasurement((WeightedMeasurement) null);
        levenbergMarquardtEstimator0.getCovariances(simpleEstimationProblem0);
    }

    @Test(timeout = 4000)
    public void test2931() throws Throwable {
        LevenbergMarquardtEstimator levenbergMarquardtEstimator0 = new LevenbergMarquardtEstimator();
        levenbergMarquardtEstimator0.getChiSquare((EstimationProblem) null);
    }

    @Test(timeout = 4000)
    public void test3032() throws Throwable {
        LevenbergMarquardtEstimator levenbergMarquardtEstimator0 = new LevenbergMarquardtEstimator();
        SimpleEstimationProblem simpleEstimationProblem0 = new SimpleEstimationProblem();
        EstimatedParameter estimatedParameter0 = new EstimatedParameter("-$C#:Xv%H9/d", (-2025.2));
        simpleEstimationProblem0.addParameter(estimatedParameter0);
        WeightedMeasurement weightedMeasurement0 = mock(WeightedMeasurement.class, new ViolatedAssumptionAnswer());
        doReturn(0.0).when(weightedMeasurement0).getPartial(any(org.apache.commons.math.estimation.EstimatedParameter.class));
        doReturn(Double.POSITIVE_INFINITY).when(weightedMeasurement0).getResidual();
        doReturn(Double.POSITIVE_INFINITY, (-2025.2), (-2025.2)).when(weightedMeasurement0).getWeight();
        simpleEstimationProblem0.addMeasurement(weightedMeasurement0);
        levenbergMarquardtEstimator0.estimate(simpleEstimationProblem0);
    }

    @Test(timeout = 4000)
    public void test3133() throws Throwable {
        GaussNewtonEstimator gaussNewtonEstimator0 = new GaussNewtonEstimator(0, 0, 0);
        gaussNewtonEstimator0.estimate((EstimationProblem) null);
    }

    @Test(timeout = 4000)
    public void test3234() throws Throwable {
        GaussNewtonEstimator gaussNewtonEstimator0 = new GaussNewtonEstimator((-584), (-584), (-584));
        SimpleEstimationProblem simpleEstimationProblem0 = new SimpleEstimationProblem();
        gaussNewtonEstimator0.estimate(simpleEstimationProblem0);
    }

    @Test(timeout = 4000)
    public void test3335() throws Throwable {
        LevenbergMarquardtEstimator levenbergMarquardtEstimator0 = new LevenbergMarquardtEstimator();
        SimpleEstimationProblem simpleEstimationProblem0 = new SimpleEstimationProblem();
        WeightedMeasurement weightedMeasurement0 = mock(WeightedMeasurement.class, new ViolatedAssumptionAnswer());
        doReturn(0.0).when(weightedMeasurement0).getResidual();
        doReturn(0.0).when(weightedMeasurement0).getWeight();
        EstimatedParameter estimatedParameter0 = new EstimatedParameter("LU decomposition requires that the matrix be square.", 1.0);
        simpleEstimationProblem0.addParameter(estimatedParameter0);
        simpleEstimationProblem0.addMeasurement(weightedMeasurement0);
        WeightedMeasurement weightedMeasurement1 = mock(WeightedMeasurement.class, new ViolatedAssumptionAnswer());
        doReturn(0.0).when(weightedMeasurement1).getResidual();
        doReturn(0.0).when(weightedMeasurement1).getWeight();
        simpleEstimationProblem0.addMeasurement(weightedMeasurement1);
        double[] doubleArray0 = new double[8];
        levenbergMarquardtEstimator0.jacobian = doubleArray0;
        levenbergMarquardtEstimator0.guessParametersErrors(simpleEstimationProblem0);
    }

    @Test(timeout = 4000)
    public void test3436() throws Throwable {
        LevenbergMarquardtEstimator levenbergMarquardtEstimator0 = new LevenbergMarquardtEstimator();
        SimpleEstimationProblem simpleEstimationProblem0 = new SimpleEstimationProblem();
        WeightedMeasurement weightedMeasurement0 = mock(WeightedMeasurement.class, new ViolatedAssumptionAnswer());
        doReturn(0.0, 0.0).when(weightedMeasurement0).getPartial(any(org.apache.commons.math.estimation.EstimatedParameter.class));
        doReturn(0.0, 0.0).when(weightedMeasurement0).getResidual();
        doReturn(0.0, 0.0, 0.0, 0.0, 0.0).when(weightedMeasurement0).getWeight();
        EstimatedParameter estimatedParameter0 = new EstimatedParameter("LU decomposition requires that the matrix be square.", 1.0);
        simpleEstimationProblem0.addParameter(estimatedParameter0);
        simpleEstimationProblem0.addMeasurement(weightedMeasurement0);
        levenbergMarquardtEstimator0.estimate(simpleEstimationProblem0);
        WeightedMeasurement weightedMeasurement1 = mock(WeightedMeasurement.class, new ViolatedAssumptionAnswer());
        doReturn(0.0).when(weightedMeasurement1).getResidual();
        doReturn(0.0).when(weightedMeasurement1).getWeight();
        simpleEstimationProblem0.addMeasurement(weightedMeasurement1);
        levenbergMarquardtEstimator0.guessParametersErrors(simpleEstimationProblem0);
    }

    @Test(timeout = 4000)
    public void test3537() throws Throwable {
        LevenbergMarquardtEstimator levenbergMarquardtEstimator0 = new LevenbergMarquardtEstimator();
        SimpleEstimationProblem simpleEstimationProblem0 = new SimpleEstimationProblem();
        double double0 = levenbergMarquardtEstimator0.getChiSquare(simpleEstimationProblem0);
    }

    @Test(timeout = 4000)
    public void test3638() throws Throwable {
        GaussNewtonEstimator gaussNewtonEstimator0 = new GaussNewtonEstimator((-817), (-817), (-817));
        gaussNewtonEstimator0.updateResidualsAndCost();
    }

    @Test(timeout = 4000)
    public void test3739() throws Throwable {
        LevenbergMarquardtEstimator levenbergMarquardtEstimator0 = new LevenbergMarquardtEstimator();
        SimpleEstimationProblem simpleEstimationProblem0 = new SimpleEstimationProblem();
        levenbergMarquardtEstimator0.estimate(simpleEstimationProblem0);
        levenbergMarquardtEstimator0.updateJacobian();
        levenbergMarquardtEstimator0.getJacobianEvaluations();
    }

    @Test(timeout = 4000)
    public void test3840() throws Throwable {
        LevenbergMarquardtEstimator levenbergMarquardtEstimator0 = new LevenbergMarquardtEstimator();
        int int0 = levenbergMarquardtEstimator0.getJacobianEvaluations();
    }

    @Test(timeout = 4000)
    public void test3941() throws Throwable {
        LevenbergMarquardtEstimator levenbergMarquardtEstimator0 = new LevenbergMarquardtEstimator();
        int int0 = levenbergMarquardtEstimator0.getCostEvaluations();
    }
}
