/*
 * This file was automatically generated by EvoSuite
 * Mon Oct 16 18:45:35 GMT 2023
 */
package org.apache.commons.math.analysis.solvers;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import org.apache.commons.math.analysis.UnivariateRealFunction;
import org.apache.commons.math.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math.analysis.polynomials.PolynomialFunctionLagrangeForm;
import org.apache.commons.math.analysis.solvers.BrentSolver;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class BrentSolver_ESTest extends BrentSolver_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        double[] doubleArray0 = new double[4];
        doubleArray0[1] = 1.0E-15;
        doubleArray0[2] = (-1776.17128565465);
        doubleArray0[3] = (-4984.530424982496);
        PolynomialFunctionLagrangeForm polynomialFunctionLagrangeForm0 = new PolynomialFunctionLagrangeForm(doubleArray0, doubleArray0);
        BrentSolver brentSolver0 = new BrentSolver(polynomialFunctionLagrangeForm0);
        double double0 = brentSolver0.solve((-1776.17128565465), 1.0E-15);
        brentSolver0.getIterationCount();
    }

    @Test(timeout = 4000)
    public void test001() throws Throwable {
        double[] doubleArray0 = new double[4];
        doubleArray0[1] = 1.0E-15;
        doubleArray0[2] = (-1776.17128565465);
        doubleArray0[3] = (-4984.530424982496);
        PolynomialFunctionLagrangeForm polynomialFunctionLagrangeForm0 = new PolynomialFunctionLagrangeForm(doubleArray0, doubleArray0);
        BrentSolver brentSolver0 = new BrentSolver(polynomialFunctionLagrangeForm0);
        double double0 = brentSolver0.solve((-1776.17128565465), 1.0E-15);
    }

    @Test(timeout = 4000)
    public void test012() throws Throwable {
        BrentSolver brentSolver0 = new BrentSolver();
        double[] doubleArray0 = new double[5];
        doubleArray0[1] = (-1.0);
        PolynomialFunction polynomialFunction0 = new PolynomialFunction(doubleArray0);
        double double0 = brentSolver0.solve((UnivariateRealFunction) polynomialFunction0, (-11.339119596985), 538.987941);
        brentSolver0.getIterationCount();
    }

    @Test(timeout = 4000)
    public void test013() throws Throwable {
        BrentSolver brentSolver0 = new BrentSolver();
        double[] doubleArray0 = new double[5];
        doubleArray0[1] = (-1.0);
        PolynomialFunction polynomialFunction0 = new PolynomialFunction(doubleArray0);
        double double0 = brentSolver0.solve((UnivariateRealFunction) polynomialFunction0, (-11.339119596985), 538.987941);
    }

    @Test(timeout = 4000)
    public void test024() throws Throwable {
        double[] doubleArray0 = new double[7];
        doubleArray0[0] = 3456.91561965;
        doubleArray0[1] = 240.31172;
        doubleArray0[2] = 2861.2919661;
        doubleArray0[3] = 420.084401;
        doubleArray0[4] = Double.NaN;
        doubleArray0[5] = (-858.862131555278);
        PolynomialFunctionLagrangeForm polynomialFunctionLagrangeForm0 = new PolynomialFunctionLagrangeForm(doubleArray0, doubleArray0);
        BrentSolver brentSolver0 = new BrentSolver(polynomialFunctionLagrangeForm0);
        double double0 = brentSolver0.solve((-817.9897647781), 0.0);
    }

    @Test(timeout = 4000)
    public void test035() throws Throwable {
        double[] doubleArray0 = new double[6];
        doubleArray0[2] = 1.0;
        PolynomialFunction polynomialFunction0 = new PolynomialFunction(doubleArray0);
        BrentSolver brentSolver0 = new BrentSolver(polynomialFunction0);
        brentSolver0.setFunctionValueAccuracy(1.0);
        double double0 = brentSolver0.solve((UnivariateRealFunction) polynomialFunction0, (-1250.749072), 1.0);
        brentSolver0.getIterationCount();
    }

    @Test(timeout = 4000)
    public void test036() throws Throwable {
        double[] doubleArray0 = new double[6];
        doubleArray0[2] = 1.0;
        PolynomialFunction polynomialFunction0 = new PolynomialFunction(doubleArray0);
        BrentSolver brentSolver0 = new BrentSolver(polynomialFunction0);
        brentSolver0.setFunctionValueAccuracy(1.0);
        double double0 = brentSolver0.solve((UnivariateRealFunction) polynomialFunction0, (-1250.749072), 1.0);
    }

    @Test(timeout = 4000)
    public void test047() throws Throwable {
        double[] doubleArray0 = new double[3];
        doubleArray0[0] = (-1896.7903696425149);
        PolynomialFunction polynomialFunction0 = new PolynomialFunction(doubleArray0);
        BrentSolver brentSolver0 = new BrentSolver(polynomialFunction0);
        brentSolver0.setFunctionValueAccuracy((-1896.7903696425149));
        brentSolver0.solve((-1.0), 26.3059117);
    }

    @Test(timeout = 4000)
    public void test058() throws Throwable {
        double[] doubleArray0 = new double[6];
        doubleArray0[2] = 47.0381435;
        PolynomialFunction polynomialFunction0 = new PolynomialFunction(doubleArray0);
        BrentSolver brentSolver0 = new BrentSolver(polynomialFunction0);
        double double0 = brentSolver0.solve((UnivariateRealFunction) polynomialFunction0, 5.283422943691111E-14, 0.5);
        brentSolver0.getIterationCount();
    }

    @Test(timeout = 4000)
    public void test059() throws Throwable {
        double[] doubleArray0 = new double[6];
        doubleArray0[2] = 47.0381435;
        PolynomialFunction polynomialFunction0 = new PolynomialFunction(doubleArray0);
        BrentSolver brentSolver0 = new BrentSolver(polynomialFunction0);
        double double0 = brentSolver0.solve((UnivariateRealFunction) polynomialFunction0, 5.283422943691111E-14, 0.5);
    }

    @Test(timeout = 4000)
    public void test0610() throws Throwable {
        double[] doubleArray0 = new double[4];
        doubleArray0[1] = 1447.70931;
        doubleArray0[2] = (-1776.17128565465);
        doubleArray0[3] = (-4984.530424982496);
        PolynomialFunctionLagrangeForm polynomialFunctionLagrangeForm0 = new PolynomialFunctionLagrangeForm(doubleArray0, doubleArray0);
        BrentSolver brentSolver0 = new BrentSolver(polynomialFunctionLagrangeForm0);
        double double0 = brentSolver0.solve((UnivariateRealFunction) polynomialFunctionLagrangeForm0, 1.0E-15, 1447.70931);
    }

    @Test(timeout = 4000)
    public void test0611() throws Throwable {
        double[] doubleArray0 = new double[4];
        doubleArray0[1] = 1447.70931;
        doubleArray0[2] = (-1776.17128565465);
        doubleArray0[3] = (-4984.530424982496);
        PolynomialFunctionLagrangeForm polynomialFunctionLagrangeForm0 = new PolynomialFunctionLagrangeForm(doubleArray0, doubleArray0);
        BrentSolver brentSolver0 = new BrentSolver(polynomialFunctionLagrangeForm0);
        double double0 = brentSolver0.solve((UnivariateRealFunction) polynomialFunctionLagrangeForm0, 1.0E-15, 1447.70931);
        brentSolver0.getIterationCount();
    }

    @Test(timeout = 4000)
    public void test0712() throws Throwable {
        BrentSolver brentSolver0 = new BrentSolver();
        double[] doubleArray0 = new double[5];
        PolynomialFunction polynomialFunction0 = new PolynomialFunction(doubleArray0);
        double double0 = brentSolver0.solve((UnivariateRealFunction) polynomialFunction0, (-11.339119596985), 538.987941);
    }

    @Test(timeout = 4000)
    public void test0813() throws Throwable {
        double[] doubleArray0 = new double[4];
        doubleArray0[0] = 1.0206918886729237;
        doubleArray0[1] = 1447.70931;
        doubleArray0[2] = (-1776.17128565465);
        doubleArray0[3] = (-4983.0756048);
        PolynomialFunctionLagrangeForm polynomialFunctionLagrangeForm0 = new PolynomialFunctionLagrangeForm(doubleArray0, doubleArray0);
        BrentSolver brentSolver0 = new BrentSolver(polynomialFunctionLagrangeForm0);
        brentSolver0.functionValueAccuracy = 1.5;
        double double0 = brentSolver0.solve((-4983.0756048), 1.0E-15, (-383.43450018));
    }

    @Test(timeout = 4000)
    public void test0914() throws Throwable {
        double[] doubleArray0 = new double[9];
        PolynomialFunction polynomialFunction0 = new PolynomialFunction(doubleArray0);
        BrentSolver brentSolver0 = new BrentSolver();
        brentSolver0.functionValueAccuracy = (-1369.72552435);
        double double0 = brentSolver0.solve((UnivariateRealFunction) polynomialFunction0, 0.0, 1484.8902623212687, 0.5);
        brentSolver0.getIterationCount();
    }

    @Test(timeout = 4000)
    public void test0915() throws Throwable {
        double[] doubleArray0 = new double[9];
        PolynomialFunction polynomialFunction0 = new PolynomialFunction(doubleArray0);
        BrentSolver brentSolver0 = new BrentSolver();
        brentSolver0.functionValueAccuracy = (-1369.72552435);
        double double0 = brentSolver0.solve((UnivariateRealFunction) polynomialFunction0, 0.0, 1484.8902623212687, 0.5);
    }

    @Test(timeout = 4000)
    public void test1016() throws Throwable {
        double[] doubleArray0 = new double[9];
        doubleArray0[2] = 1484.8902623212687;
        PolynomialFunction polynomialFunction0 = new PolynomialFunction(doubleArray0);
        BrentSolver brentSolver0 = new BrentSolver(polynomialFunction0);
        double double0 = brentSolver0.solve((UnivariateRealFunction) polynomialFunction0, 1.0E-14, 732.8342572626508, 0.5);
        brentSolver0.getIterationCount();
    }

    @Test(timeout = 4000)
    public void test1017() throws Throwable {
        double[] doubleArray0 = new double[9];
        doubleArray0[2] = 1484.8902623212687;
        PolynomialFunction polynomialFunction0 = new PolynomialFunction(doubleArray0);
        BrentSolver brentSolver0 = new BrentSolver(polynomialFunction0);
        double double0 = brentSolver0.solve((UnivariateRealFunction) polynomialFunction0, 1.0E-14, 732.8342572626508, 0.5);
    }

    @Test(timeout = 4000)
    public void test1118() throws Throwable {
        double[] doubleArray0 = new double[9];
        doubleArray0[1] = 1150.6156343157513;
        PolynomialFunction polynomialFunction0 = new PolynomialFunction(doubleArray0);
        BrentSolver brentSolver0 = new BrentSolver(polynomialFunction0);
        brentSolver0.result = (-694.4);
        double double0 = brentSolver0.solve((UnivariateRealFunction) polynomialFunction0, 0.0, 657.0789712432, 1.0E-15);
        brentSolver0.getIterationCount();
    }

    @Test(timeout = 4000)
    public void test1119() throws Throwable {
        double[] doubleArray0 = new double[9];
        doubleArray0[1] = 1150.6156343157513;
        PolynomialFunction polynomialFunction0 = new PolynomialFunction(doubleArray0);
        BrentSolver brentSolver0 = new BrentSolver(polynomialFunction0);
        brentSolver0.result = (-694.4);
        double double0 = brentSolver0.solve((UnivariateRealFunction) polynomialFunction0, 0.0, 657.0789712432, 1.0E-15);
    }

    @Test(timeout = 4000)
    public void test1220() throws Throwable {
        double[] doubleArray0 = new double[9];
        PolynomialFunction polynomialFunction0 = new PolynomialFunction(doubleArray0);
        BrentSolver brentSolver0 = new BrentSolver(polynomialFunction0);
        double double0 = brentSolver0.solve((UnivariateRealFunction) polynomialFunction0, (-694.4), 657.0789712432, 1.0E-15);
    }

    @Test(timeout = 4000)
    public void test1221() throws Throwable {
        double[] doubleArray0 = new double[9];
        PolynomialFunction polynomialFunction0 = new PolynomialFunction(doubleArray0);
        BrentSolver brentSolver0 = new BrentSolver(polynomialFunction0);
        double double0 = brentSolver0.solve((UnivariateRealFunction) polynomialFunction0, (-694.4), 657.0789712432, 1.0E-15);
        brentSolver0.getIterationCount();
    }

    @Test(timeout = 4000)
    public void test1322() throws Throwable {
        double[] doubleArray0 = new double[4];
        doubleArray0[1] = 1447.70931;
        doubleArray0[2] = (-1776.17128565465);
        doubleArray0[3] = (-4983.0756048);
        PolynomialFunctionLagrangeForm polynomialFunctionLagrangeForm0 = new PolynomialFunctionLagrangeForm(doubleArray0, doubleArray0);
        BrentSolver brentSolver0 = new BrentSolver(polynomialFunctionLagrangeForm0);
        brentSolver0.functionValueAccuracy = 1.5;
        double double0 = brentSolver0.solve((UnivariateRealFunction) polynomialFunctionLagrangeForm0, (-1776.17128565465), 3531.77585, 1.5);
    }

    @Test(timeout = 4000)
    public void test1323() throws Throwable {
        double[] doubleArray0 = new double[4];
        doubleArray0[1] = 1447.70931;
        doubleArray0[2] = (-1776.17128565465);
        doubleArray0[3] = (-4983.0756048);
        PolynomialFunctionLagrangeForm polynomialFunctionLagrangeForm0 = new PolynomialFunctionLagrangeForm(doubleArray0, doubleArray0);
        BrentSolver brentSolver0 = new BrentSolver(polynomialFunctionLagrangeForm0);
        brentSolver0.functionValueAccuracy = 1.5;
        double double0 = brentSolver0.solve((UnivariateRealFunction) polynomialFunctionLagrangeForm0, (-1776.17128565465), 3531.77585, 1.5);
        brentSolver0.getIterationCount();
    }

    @Test(timeout = 4000)
    public void test1424() throws Throwable {
        double[] doubleArray0 = new double[9];
        PolynomialFunction polynomialFunction0 = new PolynomialFunction(doubleArray0);
        BrentSolver brentSolver0 = new BrentSolver(polynomialFunction0);
        double double0 = brentSolver0.solve((-2442.5273), 365.53219607698);
    }

    @Test(timeout = 4000)
    public void test1525() throws Throwable {
        BrentSolver brentSolver0 = new BrentSolver();
        double[] doubleArray0 = new double[5];
        PolynomialFunction polynomialFunction0 = new PolynomialFunction(doubleArray0);
        brentSolver0.solve((UnivariateRealFunction) polynomialFunction0, (-1328.9411705406), 0.0, 2.2909552562);
    }

    @Test(timeout = 4000)
    public void test1626() throws Throwable {
        double[] doubleArray0 = new double[3];
        PolynomialFunction polynomialFunction0 = new PolynomialFunction(doubleArray0);
        BrentSolver brentSolver0 = new BrentSolver(polynomialFunction0);
        PolynomialFunctionLagrangeForm polynomialFunctionLagrangeForm0 = new PolynomialFunctionLagrangeForm(doubleArray0, doubleArray0);
        brentSolver0.solve((UnivariateRealFunction) polynomialFunctionLagrangeForm0, (-1896.8), 2003.52601, 939.48922067086);
    }

    @Test(timeout = 4000)
    public void test1727() throws Throwable {
        BrentSolver brentSolver0 = new BrentSolver();
        double default0;
        default0 = brentSolver0.solve((UnivariateRealFunction) null, 2.337741124863609E-8, 0.5, 1.0E-6);
        assertTrue(true ? default0 == 0 : true);
    }

    @Test(timeout = 4000)
    public void test1828() throws Throwable {
        BrentSolver brentSolver0 = new BrentSolver();
        double[] doubleArray0 = new double[7];
        PolynomialFunctionLagrangeForm polynomialFunctionLagrangeForm0 = new PolynomialFunctionLagrangeForm(doubleArray0, doubleArray0);
        brentSolver0.solve((UnivariateRealFunction) polynomialFunctionLagrangeForm0, Double.NaN, (-1260.7));
    }

    @Test(timeout = 4000)
    public void test1929() throws Throwable {
        double[] doubleArray0 = new double[9];
        doubleArray0[1] = 1150.6156343157513;
        PolynomialFunction polynomialFunction0 = new PolynomialFunction(doubleArray0);
        BrentSolver brentSolver0 = new BrentSolver(polynomialFunction0);
        brentSolver0.solve((UnivariateRealFunction) polynomialFunction0, (-1208.307122), Double.POSITIVE_INFINITY);
    }

    @Test(timeout = 4000)
    public void test2030() throws Throwable {
        BrentSolver brentSolver0 = new BrentSolver();
        double default1;
        if ((1.0E-15 < 1738.1128364) == false) {
            try {
                default1 = brentSolver0.solve((UnivariateRealFunction) null, 1.0E-15, 1738.1128364);
                fail();
            } catch (java.lang.IllegalArgumentException e) {
                // Successfully thrown exception
            }
        } else {
            default1 = brentSolver0.solve((UnivariateRealFunction) null, 1.0E-15, 1738.1128364);
            assertTrue(true ? default1 == 0 : true);
        }
    }

    @Test(timeout = 4000)
    public void test2131() throws Throwable {
        BrentSolver brentSolver0 = new BrentSolver();
        brentSolver0.solve(540.52516062492, 540.52516062492, 0.0);
    }

    @Test(timeout = 4000)
    public void test2232() throws Throwable {
        BrentSolver brentSolver0 = new BrentSolver();
        brentSolver0.solve((-2969.5739674942), 2671.25822396, 1.0);
    }

    @Test(timeout = 4000)
    public void test2333() throws Throwable {
        double[] doubleArray0 = new double[4];
        PolynomialFunctionLagrangeForm polynomialFunctionLagrangeForm0 = new PolynomialFunctionLagrangeForm(doubleArray0, doubleArray0);
        BrentSolver brentSolver0 = new BrentSolver(polynomialFunctionLagrangeForm0);
        brentSolver0.solve((-1776.17128565465), 1447.70931);
    }

    @Test(timeout = 4000)
    public void test2434() throws Throwable {
        double[] doubleArray0 = new double[6];
        doubleArray0[0] = (-807.6822);
        doubleArray0[4] = 1.0;
        PolynomialFunction polynomialFunction0 = new PolynomialFunction(doubleArray0);
        BrentSolver brentSolver0 = new BrentSolver(polynomialFunction0);
        brentSolver0.setAbsoluteAccuracy(0.0);
        brentSolver0.setRelativeAccuracy((-3062.38271152931));
        brentSolver0.solve(0.3143942336683395, 776.6959017385);
    }

    @Test(timeout = 4000)
    public void test2535() throws Throwable {
        double[] doubleArray0 = new double[6];
        doubleArray0[1] = 0.5;
        PolynomialFunction[] polynomialFunctionArray0 = new PolynomialFunction[5];
        PolynomialFunction polynomialFunction0 = new PolynomialFunction(doubleArray0);
        polynomialFunctionArray0[1] = polynomialFunction0;
        BrentSolver brentSolver0 = new BrentSolver(polynomialFunctionArray0[1]);
        double double0 = brentSolver0.solve((UnivariateRealFunction) polynomialFunction0, (-151.6142086510729), 0.0, (-135.2346082));
    }

    @Test(timeout = 4000)
    public void test2536() throws Throwable {
        double[] doubleArray0 = new double[6];
        doubleArray0[1] = 0.5;
        PolynomialFunction[] polynomialFunctionArray0 = new PolynomialFunction[5];
        PolynomialFunction polynomialFunction0 = new PolynomialFunction(doubleArray0);
        polynomialFunctionArray0[1] = polynomialFunction0;
        BrentSolver brentSolver0 = new BrentSolver(polynomialFunctionArray0[1]);
        double double0 = brentSolver0.solve((UnivariateRealFunction) polynomialFunction0, (-151.6142086510729), 0.0, (-135.2346082));
        brentSolver0.getIterationCount();
    }

    @Test(timeout = 4000)
    public void test2637() throws Throwable {
        double[] doubleArray0 = new double[4];
        doubleArray0[1] = 1447.70931;
        doubleArray0[2] = (-1776.17128565465);
        doubleArray0[3] = (-4983.0756048);
        PolynomialFunctionLagrangeForm polynomialFunctionLagrangeForm0 = new PolynomialFunctionLagrangeForm(doubleArray0, doubleArray0);
        BrentSolver brentSolver0 = new BrentSolver(polynomialFunctionLagrangeForm0);
        brentSolver0.functionValueAccuracy = 1.5;
        double double0 = brentSolver0.solve((UnivariateRealFunction) polynomialFunctionLagrangeForm0, (-4983.0756048), 1447.70931, 0.00694436728871962);
    }

    @Test(timeout = 4000)
    public void test2638() throws Throwable {
        double[] doubleArray0 = new double[4];
        doubleArray0[1] = 1447.70931;
        doubleArray0[2] = (-1776.17128565465);
        doubleArray0[3] = (-4983.0756048);
        PolynomialFunctionLagrangeForm polynomialFunctionLagrangeForm0 = new PolynomialFunctionLagrangeForm(doubleArray0, doubleArray0);
        BrentSolver brentSolver0 = new BrentSolver(polynomialFunctionLagrangeForm0);
        brentSolver0.functionValueAccuracy = 1.5;
        double double0 = brentSolver0.solve((UnivariateRealFunction) polynomialFunctionLagrangeForm0, (-4983.0756048), 1447.70931, 0.00694436728871962);
        brentSolver0.getIterationCount();
    }

    @Test(timeout = 4000)
    public void test2739() throws Throwable {
        BrentSolver brentSolver0 = new BrentSolver();
        double[] doubleArray0 = new double[5];
        brentSolver0.setRelativeAccuracy((-0.2515151255895118));
        doubleArray0[3] = Double.POSITIVE_INFINITY;
        PolynomialFunction polynomialFunction0 = new PolynomialFunction(doubleArray0);
        brentSolver0.solve((UnivariateRealFunction) polynomialFunction0, (-11.339119596985), Double.POSITIVE_INFINITY, (-0.2515151255895118));
    }

    @Test(timeout = 4000)
    public void test2840() throws Throwable {
        double[] doubleArray0 = new double[9];
        doubleArray0[1] = 1150.6156343157513;
        doubleArray0[2] = 1484.8902623212687;
        doubleArray0[4] = 1484.8902623212687;
        doubleArray0[5] = 657.0789712432;
        PolynomialFunction polynomialFunction0 = new PolynomialFunction(doubleArray0);
        BrentSolver brentSolver0 = new BrentSolver(polynomialFunction0);
        double double0 = brentSolver0.solve((UnivariateRealFunction) polynomialFunction0, (-1208.307122), 1150.6156343157513);
        brentSolver0.getIterationCount();
    }

    @Test(timeout = 4000)
    public void test2841() throws Throwable {
        double[] doubleArray0 = new double[9];
        doubleArray0[1] = 1150.6156343157513;
        doubleArray0[2] = 1484.8902623212687;
        doubleArray0[4] = 1484.8902623212687;
        doubleArray0[5] = 657.0789712432;
        PolynomialFunction polynomialFunction0 = new PolynomialFunction(doubleArray0);
        BrentSolver brentSolver0 = new BrentSolver(polynomialFunction0);
        double double0 = brentSolver0.solve((UnivariateRealFunction) polynomialFunction0, (-1208.307122), 1150.6156343157513);
    }

    @Test(timeout = 4000)
    public void test2942() throws Throwable {
        double[] doubleArray0 = new double[4];
        doubleArray0[1] = 1447.70931;
        doubleArray0[2] = (-1776.17128565465);
        doubleArray0[3] = (-4984.530424982496);
        PolynomialFunctionLagrangeForm polynomialFunctionLagrangeForm0 = new PolynomialFunctionLagrangeForm(doubleArray0, doubleArray0);
        BrentSolver brentSolver0 = new BrentSolver(polynomialFunctionLagrangeForm0);
        brentSolver0.setFunctionValueAccuracy((-4984.530424982496));
        brentSolver0.setMaximalIterationCount((-4281));
        brentSolver0.solve((-4984.530424982496), 1.0E-15, (-399.7863215958072));
    }

    @Test(timeout = 4000)
    public void test3043() throws Throwable {
        double[] doubleArray0 = new double[9];
        doubleArray0[1] = 1150.6156343157513;
        PolynomialFunction polynomialFunction0 = new PolynomialFunction(doubleArray0);
        BrentSolver brentSolver0 = new BrentSolver(polynomialFunction0);
        double double0 = brentSolver0.solve((UnivariateRealFunction) polynomialFunction0, (-2962.7116094), 0.0);
        brentSolver0.getIterationCount();
    }

    @Test(timeout = 4000)
    public void test3044() throws Throwable {
        double[] doubleArray0 = new double[9];
        doubleArray0[1] = 1150.6156343157513;
        PolynomialFunction polynomialFunction0 = new PolynomialFunction(doubleArray0);
        BrentSolver brentSolver0 = new BrentSolver(polynomialFunction0);
        double double0 = brentSolver0.solve((UnivariateRealFunction) polynomialFunction0, (-2962.7116094), 0.0);
    }

    @Test(timeout = 4000)
    public void test3145() throws Throwable {
        double[] doubleArray0 = new double[6];
        doubleArray0[2] = 47.0381435;
        PolynomialFunction polynomialFunction0 = new PolynomialFunction(doubleArray0);
        BrentSolver brentSolver0 = new BrentSolver(polynomialFunction0);
        double double0 = brentSolver0.solve((UnivariateRealFunction) polynomialFunction0, (-1205.0), 5.283422943691111E-14);
    }

    @Test(timeout = 4000)
    public void test3146() throws Throwable {
        double[] doubleArray0 = new double[6];
        doubleArray0[2] = 47.0381435;
        PolynomialFunction polynomialFunction0 = new PolynomialFunction(doubleArray0);
        BrentSolver brentSolver0 = new BrentSolver(polynomialFunction0);
        double double0 = brentSolver0.solve((UnivariateRealFunction) polynomialFunction0, (-1205.0), 5.283422943691111E-14);
        brentSolver0.getIterationCount();
    }

    @Test(timeout = 4000)
    public void test3247() throws Throwable {
        double[] doubleArray0 = new double[4];
        doubleArray0[1] = 1447.70931;
        doubleArray0[2] = (-1776.17128565465);
        doubleArray0[3] = (-4984.530424982496);
        PolynomialFunctionLagrangeForm polynomialFunctionLagrangeForm0 = new PolynomialFunctionLagrangeForm(doubleArray0, doubleArray0);
        BrentSolver brentSolver0 = new BrentSolver(polynomialFunctionLagrangeForm0);
        brentSolver0.solve((UnivariateRealFunction) polynomialFunctionLagrangeForm0, (-1953.0), (-1776.17128565465));
    }

    @Test(timeout = 4000)
    public void test3348() throws Throwable {
        double[] doubleArray0 = new double[4];
        doubleArray0[1] = 1447.70931;
        doubleArray0[2] = (-1776.17128565465);
        doubleArray0[3] = (-4984.530424982496);
        PolynomialFunctionLagrangeForm polynomialFunctionLagrangeForm0 = new PolynomialFunctionLagrangeForm(doubleArray0, doubleArray0);
        BrentSolver brentSolver0 = new BrentSolver(polynomialFunctionLagrangeForm0);
        double double0 = brentSolver0.solve((-1776.17128565465), 1447.70931);
        brentSolver0.getIterationCount();
    }

    @Test(timeout = 4000)
    public void test3349() throws Throwable {
        double[] doubleArray0 = new double[4];
        doubleArray0[1] = 1447.70931;
        doubleArray0[2] = (-1776.17128565465);
        doubleArray0[3] = (-4984.530424982496);
        PolynomialFunctionLagrangeForm polynomialFunctionLagrangeForm0 = new PolynomialFunctionLagrangeForm(doubleArray0, doubleArray0);
        BrentSolver brentSolver0 = new BrentSolver(polynomialFunctionLagrangeForm0);
        double double0 = brentSolver0.solve((-1776.17128565465), 1447.70931);
    }

    @Test(timeout = 4000)
    public void test3450() throws Throwable {
        BrentSolver brentSolver0 = new BrentSolver();
        double[] doubleArray0 = new double[5];
        doubleArray0[3] = Double.POSITIVE_INFINITY;
        PolynomialFunction polynomialFunction0 = new PolynomialFunction(doubleArray0);
        UnivariateRealFunction univariateRealFunction0 = polynomialFunction0.derivative();
        double double0 = brentSolver0.solve(univariateRealFunction0, (-11.339119596985), Double.POSITIVE_INFINITY, (-0.2515151255895118));
        brentSolver0.getIterationCount();
    }

    @Test(timeout = 4000)
    public void test3451() throws Throwable {
        BrentSolver brentSolver0 = new BrentSolver();
        double[] doubleArray0 = new double[5];
        doubleArray0[3] = Double.POSITIVE_INFINITY;
        PolynomialFunction polynomialFunction0 = new PolynomialFunction(doubleArray0);
        UnivariateRealFunction univariateRealFunction0 = polynomialFunction0.derivative();
        double double0 = brentSolver0.solve(univariateRealFunction0, (-11.339119596985), Double.POSITIVE_INFINITY, (-0.2515151255895118));
    }

    @Test(timeout = 4000)
    public void test3552() throws Throwable {
        double[] doubleArray0 = new double[4];
        doubleArray0[1] = 1447.70931;
        doubleArray0[2] = (-1776.17128565465);
        doubleArray0[3] = (-4984.530424982496);
        PolynomialFunctionLagrangeForm polynomialFunctionLagrangeForm0 = new PolynomialFunctionLagrangeForm(doubleArray0, doubleArray0);
        BrentSolver brentSolver0 = new BrentSolver(polynomialFunctionLagrangeForm0);
        double double0 = brentSolver0.solve((-4984.530424982496), 1.0E-15, (-383.43450018));
    }

    @Test(timeout = 4000)
    public void test3553() throws Throwable {
        double[] doubleArray0 = new double[4];
        doubleArray0[1] = 1447.70931;
        doubleArray0[2] = (-1776.17128565465);
        doubleArray0[3] = (-4984.530424982496);
        PolynomialFunctionLagrangeForm polynomialFunctionLagrangeForm0 = new PolynomialFunctionLagrangeForm(doubleArray0, doubleArray0);
        BrentSolver brentSolver0 = new BrentSolver(polynomialFunctionLagrangeForm0);
        double double0 = brentSolver0.solve((-4984.530424982496), 1.0E-15, (-383.43450018));
        brentSolver0.getIterationCount();
    }

    @Test(timeout = 4000)
    public void test3654() throws Throwable {
        double[] doubleArray0 = new double[9];
        doubleArray0[5] = 1484.8902623212687;
        PolynomialFunction polynomialFunction0 = new PolynomialFunction(doubleArray0);
        BrentSolver brentSolver0 = new BrentSolver();
        double double0 = brentSolver0.solve((UnivariateRealFunction) polynomialFunction0, (-4934.254939725), 1484.8902623212687, 2.4726727335746608);
        brentSolver0.getIterationCount();
    }

    @Test(timeout = 4000)
    public void test3655() throws Throwable {
        double[] doubleArray0 = new double[9];
        doubleArray0[5] = 1484.8902623212687;
        PolynomialFunction polynomialFunction0 = new PolynomialFunction(doubleArray0);
        BrentSolver brentSolver0 = new BrentSolver();
        double double0 = brentSolver0.solve((UnivariateRealFunction) polynomialFunction0, (-4934.254939725), 1484.8902623212687, 2.4726727335746608);
    }

    @Test(timeout = 4000)
    public void test3756() throws Throwable {
        double[] doubleArray0 = new double[4];
        doubleArray0[1] = 1447.70931;
        doubleArray0[2] = (-1776.17128565465);
        doubleArray0[3] = (-4983.0756048);
        PolynomialFunctionLagrangeForm polynomialFunctionLagrangeForm0 = new PolynomialFunctionLagrangeForm(doubleArray0, doubleArray0);
        BrentSolver brentSolver0 = new BrentSolver(polynomialFunctionLagrangeForm0);
        brentSolver0.setFunctionValueAccuracy(Double.POSITIVE_INFINITY);
        double double0 = brentSolver0.solve((-4983.0756048), 1.0E-15, (-383.43450018));
    }

    @Test(timeout = 4000)
    public void test3757() throws Throwable {
        double[] doubleArray0 = new double[4];
        doubleArray0[1] = 1447.70931;
        doubleArray0[2] = (-1776.17128565465);
        doubleArray0[3] = (-4983.0756048);
        PolynomialFunctionLagrangeForm polynomialFunctionLagrangeForm0 = new PolynomialFunctionLagrangeForm(doubleArray0, doubleArray0);
        BrentSolver brentSolver0 = new BrentSolver(polynomialFunctionLagrangeForm0);
        brentSolver0.setFunctionValueAccuracy(Double.POSITIVE_INFINITY);
        double double0 = brentSolver0.solve((-4983.0756048), 1.0E-15, (-383.43450018));
        brentSolver0.getIterationCount();
    }

    @Test(timeout = 4000)
    public void test3858() throws Throwable {
        double[] doubleArray0 = new double[4];
        doubleArray0[1] = 1447.70931;
        doubleArray0[2] = (-1776.17128565465);
        doubleArray0[3] = (-4983.0756048);
        PolynomialFunctionLagrangeForm polynomialFunctionLagrangeForm0 = new PolynomialFunctionLagrangeForm(doubleArray0, doubleArray0);
        BrentSolver brentSolver0 = new BrentSolver(polynomialFunctionLagrangeForm0);
        double double0 = brentSolver0.solve((-4983.0756048), 9.998970790920781, (-383.43450018));
        brentSolver0.getIterationCount();
    }

    @Test(timeout = 4000)
    public void test3859() throws Throwable {
        double[] doubleArray0 = new double[4];
        doubleArray0[1] = 1447.70931;
        doubleArray0[2] = (-1776.17128565465);
        doubleArray0[3] = (-4983.0756048);
        PolynomialFunctionLagrangeForm polynomialFunctionLagrangeForm0 = new PolynomialFunctionLagrangeForm(doubleArray0, doubleArray0);
        BrentSolver brentSolver0 = new BrentSolver(polynomialFunctionLagrangeForm0);
        double double0 = brentSolver0.solve((-4983.0756048), 9.998970790920781, (-383.43450018));
    }

    @Test(timeout = 4000)
    public void test3960() throws Throwable {
        double[] doubleArray0 = new double[4];
        PolynomialFunctionLagrangeForm polynomialFunctionLagrangeForm0 = new PolynomialFunctionLagrangeForm(doubleArray0, doubleArray0);
        BrentSolver brentSolver0 = new BrentSolver(polynomialFunctionLagrangeForm0);
        brentSolver0.solve((-4983.0756048), 9.998970790920781, (-383.43450018));
    }

    @Test(timeout = 4000)
    public void test4061() throws Throwable {
        BrentSolver brentSolver0 = new BrentSolver();
        brentSolver0.solve((-1.0), 1.0);
    }

    @Test(timeout = 4000)
    public void test4162() throws Throwable {
        BrentSolver brentSolver0 = null;
        brentSolver0 = new BrentSolver((UnivariateRealFunction) null);
    }
}
