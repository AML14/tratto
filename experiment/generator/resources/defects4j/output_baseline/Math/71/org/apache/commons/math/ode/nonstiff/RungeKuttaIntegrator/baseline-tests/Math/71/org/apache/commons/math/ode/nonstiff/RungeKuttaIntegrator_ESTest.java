/*
 * This file was automatically generated by EvoSuite
 * Mon Nov 20 09:32:04 GMT 2023
 */
package org.apache.commons.math.ode.nonstiff;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import org.apache.commons.math.ode.FirstOrderConverter;
import org.apache.commons.math.ode.SecondOrderDifferentialEquations;
import org.apache.commons.math.ode.events.EventHandler;
import org.apache.commons.math.ode.nonstiff.ClassicalRungeKuttaIntegrator;
import org.apache.commons.math.ode.nonstiff.EulerIntegrator;
import org.apache.commons.math.ode.sampling.FixedStepHandler;
import org.apache.commons.math.ode.sampling.StepNormalizer;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class RungeKuttaIntegrator_ESTest extends RungeKuttaIntegrator_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test00() throws Throwable {
        double[] doubleArray0 = new double[0];
        SecondOrderDifferentialEquations secondOrderDifferentialEquations0 = mock(SecondOrderDifferentialEquations.class, new ViolatedAssumptionAnswer());
        doReturn(0).when(secondOrderDifferentialEquations0).getDimension();
        FirstOrderConverter firstOrderConverter0 = new FirstOrderConverter(secondOrderDifferentialEquations0);
        ClassicalRungeKuttaIntegrator classicalRungeKuttaIntegrator0 = new ClassicalRungeKuttaIntegrator((-2701.708662));
        classicalRungeKuttaIntegrator0.integrate(firstOrderConverter0, (-856.79967603), doubleArray0, 211.0, doubleArray0);
        classicalRungeKuttaIntegrator0.getEvaluations();
    }

    @Test(timeout = 4000)
    public void test11() throws Throwable {
        double[] doubleArray0 = new double[0];
        SecondOrderDifferentialEquations secondOrderDifferentialEquations0 = mock(SecondOrderDifferentialEquations.class, new ViolatedAssumptionAnswer());
        doReturn(0).when(secondOrderDifferentialEquations0).getDimension();
        FirstOrderConverter firstOrderConverter0 = new FirstOrderConverter(secondOrderDifferentialEquations0);
        ClassicalRungeKuttaIntegrator classicalRungeKuttaIntegrator0 = new ClassicalRungeKuttaIntegrator(0);
        double[] doubleArray1 = new double[0];
        // Undeclared exception!
        classicalRungeKuttaIntegrator0.integrate(firstOrderConverter0, 0, doubleArray0, (-0.25), doubleArray1);
    }

    @Test(timeout = 4000)
    public void test22() throws Throwable {
        double[] doubleArray0 = new double[0];
        SecondOrderDifferentialEquations secondOrderDifferentialEquations0 = mock(SecondOrderDifferentialEquations.class, new ViolatedAssumptionAnswer());
        doReturn(0).when(secondOrderDifferentialEquations0).getDimension();
        FirstOrderConverter firstOrderConverter0 = new FirstOrderConverter(secondOrderDifferentialEquations0);
        ClassicalRungeKuttaIntegrator classicalRungeKuttaIntegrator0 = new ClassicalRungeKuttaIntegrator(0);
        FixedStepHandler fixedStepHandler0 = mock(FixedStepHandler.class, new ViolatedAssumptionAnswer());
        StepNormalizer stepNormalizer0 = new StepNormalizer(15.0, fixedStepHandler0);
        classicalRungeKuttaIntegrator0.addStepHandler(stepNormalizer0);
        // Undeclared exception!
        classicalRungeKuttaIntegrator0.integrate(firstOrderConverter0, Double.NaN, doubleArray0, Double.NaN, doubleArray0);
    }

    @Test(timeout = 4000)
    public void test33() throws Throwable {
        double[] doubleArray0 = new double[0];
        SecondOrderDifferentialEquations secondOrderDifferentialEquations0 = mock(SecondOrderDifferentialEquations.class, new ViolatedAssumptionAnswer());
        doReturn(0).when(secondOrderDifferentialEquations0).getDimension();
        FirstOrderConverter firstOrderConverter0 = new FirstOrderConverter(secondOrderDifferentialEquations0);
        EulerIntegrator eulerIntegrator0 = new EulerIntegrator(0);
        EventHandler eventHandler0 = mock(EventHandler.class, new ViolatedAssumptionAnswer());
        doReturn(0.0, 0.0, 0.0, 0.0, 0.0).when(eventHandler0).g(anyDouble(), any(double[].class));
        eulerIntegrator0.addEventHandler(eventHandler0, 0, 0, 0);
        // Undeclared exception!
        eulerIntegrator0.integrate(firstOrderConverter0, 0, doubleArray0, (-43.53345659001114), doubleArray0);
    }
}
