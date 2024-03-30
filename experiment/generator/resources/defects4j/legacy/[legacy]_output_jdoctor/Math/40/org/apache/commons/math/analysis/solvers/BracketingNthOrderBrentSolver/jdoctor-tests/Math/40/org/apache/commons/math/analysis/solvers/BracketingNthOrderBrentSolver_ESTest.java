/*
 * This file was automatically generated by EvoSuite
 * Mon Oct 16 14:46:57 GMT 2023
 */
package org.apache.commons.math.analysis.solvers;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import org.apache.commons.math.analysis.UnivariateFunction;
import org.apache.commons.math.analysis.function.Asinh;
import org.apache.commons.math.analysis.function.Atanh;
import org.apache.commons.math.analysis.function.Gaussian;
import org.apache.commons.math.analysis.function.Inverse;
import org.apache.commons.math.analysis.function.Log10;
import org.apache.commons.math.analysis.function.Minus;
import org.apache.commons.math.analysis.function.Sigmoid;
import org.apache.commons.math.analysis.function.Sinc;
import org.apache.commons.math.analysis.function.Sqrt;
import org.apache.commons.math.analysis.solvers.AllowedSolution;
import org.apache.commons.math.analysis.solvers.BracketingNthOrderBrentSolver;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class BracketingNthOrderBrentSolver_ESTest extends BracketingNthOrderBrentSolver_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test030() throws Throwable {
        BracketingNthOrderBrentSolver bracketingNthOrderBrentSolver0 = new BracketingNthOrderBrentSolver(0.5, 0.5, 2);
        Sigmoid sigmoid0 = new Sigmoid(2, 0);
        AllowedSolution allowedSolution0 = AllowedSolution.ABOVE_SIDE;
        bracketingNthOrderBrentSolver0.solve(0, (UnivariateFunction) sigmoid0, (double) 0, 4524.565, allowedSolution0);
    }

    @Test(timeout = 4000)
    public void test041() throws Throwable {
        BracketingNthOrderBrentSolver bracketingNthOrderBrentSolver0 = new BracketingNthOrderBrentSolver((-336.37036033754146), (-336.37036033754146), 870);
        Gaussian gaussian0 = new Gaussian();
        AllowedSolution allowedSolution0 = AllowedSolution.ABOVE_SIDE;
        bracketingNthOrderBrentSolver0.solve(870, (UnivariateFunction) gaussian0, 1.0E-15, (-1.0), allowedSolution0);
    }

    @Test(timeout = 4000)
    public void test052() throws Throwable {
        BracketingNthOrderBrentSolver bracketingNthOrderBrentSolver0 = new BracketingNthOrderBrentSolver();
        Minus minus0 = new Minus();
        AllowedSolution allowedSolution0 = AllowedSolution.LEFT_SIDE;
        bracketingNthOrderBrentSolver0.solve(0, (UnivariateFunction) minus0, (-2921.193336343551), 3.910525597437617E-5, (-1387.77948), allowedSolution0);
    }

    @Test(timeout = 4000)
    public void test063() throws Throwable {
        BracketingNthOrderBrentSolver bracketingNthOrderBrentSolver0 = new BracketingNthOrderBrentSolver();
        Sinc sinc0 = new Sinc(true);
        AllowedSolution allowedSolution0 = AllowedSolution.RIGHT_SIDE;
        bracketingNthOrderBrentSolver0.solve(5, (UnivariateFunction) sinc0, 5595.40184475436, 4190.499975821219, 0.0, allowedSolution0);
    }

    @Test(timeout = 4000)
    public void test074() throws Throwable {
        BracketingNthOrderBrentSolver bracketingNthOrderBrentSolver0 = new BracketingNthOrderBrentSolver();
        AllowedSolution allowedSolution0 = AllowedSolution.RIGHT_SIDE;
        bracketingNthOrderBrentSolver0.solve(5, (UnivariateFunction) null, (double) 5, 1.6390891075134277, (double) 80212818, allowedSolution0);
    }

    @Test(timeout = 4000)
    public void test085() throws Throwable {
        BracketingNthOrderBrentSolver bracketingNthOrderBrentSolver0 = new BracketingNthOrderBrentSolver();
        bracketingNthOrderBrentSolver0.doSolve();
    }

    @Test(timeout = 4000)
    public void test096() throws Throwable {
        BracketingNthOrderBrentSolver bracketingNthOrderBrentSolver0 = new BracketingNthOrderBrentSolver(0.0, 386.18255718, (-3862.0), 1707);
        Sqrt sqrt0 = new Sqrt();
        bracketingNthOrderBrentSolver0.setup(5, sqrt0, 1162.05718041954, 386.18255718, Double.NaN);
        bracketingNthOrderBrentSolver0.doSolve();
    }

    @Test(timeout = 4000)
    public void test107() throws Throwable {
        BracketingNthOrderBrentSolver bracketingNthOrderBrentSolver0 = new BracketingNthOrderBrentSolver((-1.0), 1485);
        Asinh asinh0 = new Asinh();
        AllowedSolution allowedSolution0 = AllowedSolution.BELOW_SIDE;
        double double0 = bracketingNthOrderBrentSolver0.solve(1485, (UnivariateFunction) asinh0, (-1.0), 2125.439409557961, allowedSolution0);
        bracketingNthOrderBrentSolver0.getMaximalOrder();
    }

    @Test(timeout = 4000)
    public void test108() throws Throwable {
        BracketingNthOrderBrentSolver bracketingNthOrderBrentSolver0 = new BracketingNthOrderBrentSolver((-1.0), 1485);
        Asinh asinh0 = new Asinh();
        AllowedSolution allowedSolution0 = AllowedSolution.BELOW_SIDE;
        double double0 = bracketingNthOrderBrentSolver0.solve(1485, (UnivariateFunction) asinh0, (-1.0), 2125.439409557961, allowedSolution0);
    }

    @Test(timeout = 4000)
    public void test119() throws Throwable {
        BracketingNthOrderBrentSolver bracketingNthOrderBrentSolver0 = new BracketingNthOrderBrentSolver();
        Log10 log10_0 = new Log10();
        AllowedSolution allowedSolution0 = AllowedSolution.BELOW_SIDE;
        double double0 = bracketingNthOrderBrentSolver0.solve(1759, (UnivariateFunction) log10_0, 0.0, (double) 1759, allowedSolution0);
    }

    @Test(timeout = 4000)
    public void test1210() throws Throwable {
        BracketingNthOrderBrentSolver bracketingNthOrderBrentSolver0 = new BracketingNthOrderBrentSolver((-336.37036033754146), (-336.37036033754146), 870);
        Atanh atanh0 = new Atanh();
        AllowedSolution allowedSolution0 = AllowedSolution.ABOVE_SIDE;
        double double0 = bracketingNthOrderBrentSolver0.solve(1939, (UnivariateFunction) atanh0, (-1.0), 1.0E-15, allowedSolution0);
    }

    @Test(timeout = 4000)
    public void test1211() throws Throwable {
        BracketingNthOrderBrentSolver bracketingNthOrderBrentSolver0 = new BracketingNthOrderBrentSolver((-336.37036033754146), (-336.37036033754146), 870);
        Atanh atanh0 = new Atanh();
        AllowedSolution allowedSolution0 = AllowedSolution.ABOVE_SIDE;
        double double0 = bracketingNthOrderBrentSolver0.solve(1939, (UnivariateFunction) atanh0, (-1.0), 1.0E-15, allowedSolution0);
        bracketingNthOrderBrentSolver0.getMaximalOrder();
    }

    @Test(timeout = 4000)
    public void test1312() throws Throwable {
        BracketingNthOrderBrentSolver bracketingNthOrderBrentSolver0 = new BracketingNthOrderBrentSolver((-336.4448868689607), (-336.4448868689607), 870, 870);
        Gaussian gaussian0 = new Gaussian();
        UnivariateFunction univariateFunction0 = gaussian0.derivative();
        AllowedSolution allowedSolution0 = AllowedSolution.BELOW_SIDE;
        double double0 = bracketingNthOrderBrentSolver0.solve(870, univariateFunction0, (-1.0), 1.0E-15, allowedSolution0);
    }

    @Test(timeout = 4000)
    public void test1313() throws Throwable {
        BracketingNthOrderBrentSolver bracketingNthOrderBrentSolver0 = new BracketingNthOrderBrentSolver((-336.4448868689607), (-336.4448868689607), 870, 870);
        Gaussian gaussian0 = new Gaussian();
        UnivariateFunction univariateFunction0 = gaussian0.derivative();
        AllowedSolution allowedSolution0 = AllowedSolution.BELOW_SIDE;
        double double0 = bracketingNthOrderBrentSolver0.solve(870, univariateFunction0, (-1.0), 1.0E-15, allowedSolution0);
        bracketingNthOrderBrentSolver0.getMaximalOrder();
    }

    @Test(timeout = 4000)
    public void test1414() throws Throwable {
        BracketingNthOrderBrentSolver bracketingNthOrderBrentSolver0 = new BracketingNthOrderBrentSolver();
        Sinc sinc0 = new Sinc(true);
        bracketingNthOrderBrentSolver0.solve(3065, (UnivariateFunction) sinc0, (double) 3065, 5595.40184475436);
        double double0 = bracketingNthOrderBrentSolver0.doSolve();
        bracketingNthOrderBrentSolver0.getMaximalOrder();
    }

    @Test(timeout = 4000)
    public void test1415() throws Throwable {
        BracketingNthOrderBrentSolver bracketingNthOrderBrentSolver0 = new BracketingNthOrderBrentSolver();
        Sinc sinc0 = new Sinc(true);
        bracketingNthOrderBrentSolver0.solve(3065, (UnivariateFunction) sinc0, (double) 3065, 5595.40184475436);
        double double0 = bracketingNthOrderBrentSolver0.doSolve();
    }

    @Test(timeout = 4000)
    public void test1516() throws Throwable {
        BracketingNthOrderBrentSolver bracketingNthOrderBrentSolver0 = new BracketingNthOrderBrentSolver();
        Sinc sinc0 = new Sinc(true);
        AllowedSolution allowedSolution0 = AllowedSolution.LEFT_SIDE;
        double double0 = bracketingNthOrderBrentSolver0.solve(80212818, (UnivariateFunction) sinc0, (-3136.0), (double) 5, (-2042.9861249047), allowedSolution0);
    }

    @Test(timeout = 4000)
    public void test1517() throws Throwable {
        BracketingNthOrderBrentSolver bracketingNthOrderBrentSolver0 = new BracketingNthOrderBrentSolver();
        Sinc sinc0 = new Sinc(true);
        AllowedSolution allowedSolution0 = AllowedSolution.LEFT_SIDE;
        double double0 = bracketingNthOrderBrentSolver0.solve(80212818, (UnivariateFunction) sinc0, (-3136.0), (double) 5, (-2042.9861249047), allowedSolution0);
        bracketingNthOrderBrentSolver0.getMaximalOrder();
    }

    @Test(timeout = 4000)
    public void test1618() throws Throwable {
        BracketingNthOrderBrentSolver bracketingNthOrderBrentSolver0 = new BracketingNthOrderBrentSolver();
        Sinc sinc0 = new Sinc(true);
        double double0 = bracketingNthOrderBrentSolver0.solve(3068, (UnivariateFunction) sinc0, (double) 3068, 5595.40184475436);
    }

    @Test(timeout = 4000)
    public void test1619() throws Throwable {
        BracketingNthOrderBrentSolver bracketingNthOrderBrentSolver0 = new BracketingNthOrderBrentSolver();
        Sinc sinc0 = new Sinc(true);
        double double0 = bracketingNthOrderBrentSolver0.solve(3068, (UnivariateFunction) sinc0, (double) 3068, 5595.40184475436);
        bracketingNthOrderBrentSolver0.getMaximalOrder();
    }

    @Test(timeout = 4000)
    public void test1720() throws Throwable {
        BracketingNthOrderBrentSolver bracketingNthOrderBrentSolver0 = new BracketingNthOrderBrentSolver((-336.37036033754146), (-336.37036033754146), 870);
        Gaussian gaussian0 = new Gaussian();
        UnivariateFunction univariateFunction0 = gaussian0.derivative();
        AllowedSolution allowedSolution0 = AllowedSolution.ABOVE_SIDE;
        double double0 = bracketingNthOrderBrentSolver0.solve(870, univariateFunction0, (-1.0), 1.0E-15, allowedSolution0);
    }

    @Test(timeout = 4000)
    public void test1821() throws Throwable {
        Log10 log10_0 = new Log10();
        AllowedSolution allowedSolution0 = AllowedSolution.BELOW_SIDE;
        BracketingNthOrderBrentSolver bracketingNthOrderBrentSolver0 = new BracketingNthOrderBrentSolver(948.8898, 1759, 5);
        bracketingNthOrderBrentSolver0.solve(5, (UnivariateFunction) log10_0, (-2.185293416871393E-12), (double) 5, 1.0E-15, allowedSolution0);
        bracketingNthOrderBrentSolver0.doSolve();
    }

    @Test(timeout = 4000)
    public void test1922() throws Throwable {
        BracketingNthOrderBrentSolver bracketingNthOrderBrentSolver0 = new BracketingNthOrderBrentSolver((-336.4448868689607), (-336.4448868689607), 870);
        Gaussian gaussian0 = new Gaussian();
        UnivariateFunction univariateFunction0 = gaussian0.derivative();
        AllowedSolution allowedSolution0 = AllowedSolution.BELOW_SIDE;
        double double0 = bracketingNthOrderBrentSolver0.solve(870, univariateFunction0, (-1.0), 0.0, allowedSolution0);
    }

    @Test(timeout = 4000)
    public void test1923() throws Throwable {
        BracketingNthOrderBrentSolver bracketingNthOrderBrentSolver0 = new BracketingNthOrderBrentSolver((-336.4448868689607), (-336.4448868689607), 870);
        Gaussian gaussian0 = new Gaussian();
        UnivariateFunction univariateFunction0 = gaussian0.derivative();
        AllowedSolution allowedSolution0 = AllowedSolution.BELOW_SIDE;
        double double0 = bracketingNthOrderBrentSolver0.solve(870, univariateFunction0, (-1.0), 0.0, allowedSolution0);
        bracketingNthOrderBrentSolver0.getMaximalOrder();
    }

    @Test(timeout = 4000)
    public void test2024() throws Throwable {
        BracketingNthOrderBrentSolver bracketingNthOrderBrentSolver0 = new BracketingNthOrderBrentSolver(0.0, 0.0, 0.0, 1707);
        Sqrt sqrt0 = new Sqrt();
        bracketingNthOrderBrentSolver0.setup(5, sqrt0, 0, 0.0, Double.NaN);
        double double0 = bracketingNthOrderBrentSolver0.doSolve();
        bracketingNthOrderBrentSolver0.getMaximalOrder();
    }

    @Test(timeout = 4000)
    public void test2025() throws Throwable {
        BracketingNthOrderBrentSolver bracketingNthOrderBrentSolver0 = new BracketingNthOrderBrentSolver(0.0, 0.0, 0.0, 1707);
        Sqrt sqrt0 = new Sqrt();
        bracketingNthOrderBrentSolver0.setup(5, sqrt0, 0, 0.0, Double.NaN);
        double double0 = bracketingNthOrderBrentSolver0.doSolve();
    }

    @Test(timeout = 4000)
    public void test2126() throws Throwable {
        BracketingNthOrderBrentSolver bracketingNthOrderBrentSolver0 = new BracketingNthOrderBrentSolver((-336.4448868689607), (-336.4448868689607), 870);
        Gaussian gaussian0 = new Gaussian();
        AllowedSolution allowedSolution0 = AllowedSolution.BELOW_SIDE;
        double double0 = bracketingNthOrderBrentSolver0.solve(870, (UnivariateFunction) gaussian0, (-336.4448868689607), (double) 870, allowedSolution0);
        bracketingNthOrderBrentSolver0.getMin();
    }

    @Test(timeout = 4000)
    public void test2127() throws Throwable {
        BracketingNthOrderBrentSolver bracketingNthOrderBrentSolver0 = new BracketingNthOrderBrentSolver((-336.4448868689607), (-336.4448868689607), 870);
        Gaussian gaussian0 = new Gaussian();
        AllowedSolution allowedSolution0 = AllowedSolution.BELOW_SIDE;
        double double0 = bracketingNthOrderBrentSolver0.solve(870, (UnivariateFunction) gaussian0, (-336.4448868689607), (double) 870, allowedSolution0);
    }

    @Test(timeout = 4000)
    public void test2228() throws Throwable {
        BracketingNthOrderBrentSolver bracketingNthOrderBrentSolver0 = null;
        bracketingNthOrderBrentSolver0 = new BracketingNthOrderBrentSolver(0.0, (-176), 2553.8937608706196, (-176));
    }

    @Test(timeout = 4000)
    public void test2329() throws Throwable {
        BracketingNthOrderBrentSolver bracketingNthOrderBrentSolver0 = null;
        bracketingNthOrderBrentSolver0 = new BracketingNthOrderBrentSolver(733.043805, (-713.8371), 0);
    }

    @Test(timeout = 4000)
    public void test2430() throws Throwable {
        BracketingNthOrderBrentSolver bracketingNthOrderBrentSolver0 = null;
        bracketingNthOrderBrentSolver0 = new BracketingNthOrderBrentSolver((-792), (-792));
    }

    @Test(timeout = 4000)
    public void test2531() throws Throwable {
        BracketingNthOrderBrentSolver bracketingNthOrderBrentSolver0 = new BracketingNthOrderBrentSolver();
        Sinc sinc0 = new Sinc(true);
        AllowedSolution allowedSolution0 = AllowedSolution.RIGHT_SIDE;
        double double0 = bracketingNthOrderBrentSolver0.solve(80212818, (UnivariateFunction) sinc0, (-3136.0), (double) 5, (-2042.9861249047), allowedSolution0);
    }

    @Test(timeout = 4000)
    public void test2632() throws Throwable {
        BracketingNthOrderBrentSolver bracketingNthOrderBrentSolver0 = new BracketingNthOrderBrentSolver(0.0, 0.0, 0.0, 1707);
        Sqrt sqrt0 = new Sqrt();
        AllowedSolution allowedSolution0 = AllowedSolution.ANY_SIDE;
        bracketingNthOrderBrentSolver0.solve(3, (UnivariateFunction) sqrt0, 1567.703073, 2791.57857089207, allowedSolution0);
    }

    @Test(timeout = 4000)
    public void test2733() throws Throwable {
        BracketingNthOrderBrentSolver bracketingNthOrderBrentSolver0 = new BracketingNthOrderBrentSolver();
        int int0 = bracketingNthOrderBrentSolver0.getMaximalOrder();
    }
}
