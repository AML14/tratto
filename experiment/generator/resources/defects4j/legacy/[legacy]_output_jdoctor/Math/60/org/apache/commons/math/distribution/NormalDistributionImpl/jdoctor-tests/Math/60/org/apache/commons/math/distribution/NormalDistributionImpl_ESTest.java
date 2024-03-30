/*
 * This file was automatically generated by EvoSuite
 * Mon Oct 16 16:27:34 GMT 2023
 */
package org.apache.commons.math.distribution;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import org.apache.commons.math.distribution.NormalDistributionImpl;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class NormalDistributionImpl_ESTest extends NormalDistributionImpl_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        double double0 = normalDistributionImpl0.getInitialDomain(Double.NaN);
        normalDistributionImpl0.getStandardDeviation();
    }

    @Test(timeout = 4000)
    public void test001() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        double double0 = normalDistributionImpl0.getInitialDomain(Double.NaN);
    }

    @Test(timeout = 4000)
    public void test012() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl();
        double double0 = normalDistributionImpl0.getDomainLowerBound(0.5);
        normalDistributionImpl0.getStandardDeviation();
    }

    @Test(timeout = 4000)
    public void test013() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl();
        double double0 = normalDistributionImpl0.getDomainLowerBound(0.5);
    }

    @Test(timeout = 4000)
    public void test024() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl();
        normalDistributionImpl0.inverseCumulativeProbability(901.9696468329073);
    }

    @Test(timeout = 4000)
    public void test035() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl();
        double double0 = normalDistributionImpl0.sample();
        normalDistributionImpl0.getStandardDeviation();
    }

    @Test(timeout = 4000)
    public void test036() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl();
        double double0 = normalDistributionImpl0.sample();
    }

    @Test(timeout = 4000)
    public void test037() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl();
        double double0 = normalDistributionImpl0.sample();
        normalDistributionImpl0.getMean();
    }

    @Test(timeout = 4000)
    public void test048() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl((-1273.944844), 6.283185307179586, (-2317.66173462341));
        double double0 = normalDistributionImpl0.sample();
    }

    @Test(timeout = 4000)
    public void test049() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl((-1273.944844), 6.283185307179586, (-2317.66173462341));
        double double0 = normalDistributionImpl0.sample();
        normalDistributionImpl0.getStandardDeviation();
    }

    @Test(timeout = 4000)
    public void test0510() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl();
        double double0 = normalDistributionImpl0.inverseCumulativeProbability(0.5000000000042687);
    }

    @Test(timeout = 4000)
    public void test0511() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl();
        double double0 = normalDistributionImpl0.inverseCumulativeProbability(0.5000000000042687);
        normalDistributionImpl0.getStandardDeviation();
    }

    @Test(timeout = 4000)
    public void test0612() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl((-1914.57745749), 782.268, 0.0);
        double double0 = normalDistributionImpl0.getSolverAbsoluteAccuracy();
        normalDistributionImpl0.getStandardDeviation();
    }

    @Test(timeout = 4000)
    public void test0613() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl((-1914.57745749), 782.268, 0.0);
        double double0 = normalDistributionImpl0.getSolverAbsoluteAccuracy();
    }

    @Test(timeout = 4000)
    public void test0614() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl((-1914.57745749), 782.268, 0.0);
        double double0 = normalDistributionImpl0.getSolverAbsoluteAccuracy();
        normalDistributionImpl0.getMean();
    }

    @Test(timeout = 4000)
    public void test0715() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl((-2186.7531), 1.0E-9);
        double double0 = normalDistributionImpl0.getSolverAbsoluteAccuracy();
    }

    @Test(timeout = 4000)
    public void test0716() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl((-2186.7531), 1.0E-9);
        double double0 = normalDistributionImpl0.getSolverAbsoluteAccuracy();
        normalDistributionImpl0.getMean();
    }

    @Test(timeout = 4000)
    public void test0817() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl((-2.0), Double.POSITIVE_INFINITY, (-1.7976931348623157E308));
        double double0 = normalDistributionImpl0.getSolverAbsoluteAccuracy();
    }

    @Test(timeout = 4000)
    public void test0818() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl((-2.0), Double.POSITIVE_INFINITY, (-1.7976931348623157E308));
        double double0 = normalDistributionImpl0.getSolverAbsoluteAccuracy();
        normalDistributionImpl0.getStandardDeviation();
    }

    @Test(timeout = 4000)
    public void test0819() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl((-2.0), Double.POSITIVE_INFINITY, (-1.7976931348623157E308));
        double double0 = normalDistributionImpl0.getSolverAbsoluteAccuracy();
        normalDistributionImpl0.getMean();
    }

    @Test(timeout = 4000)
    public void test0920() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl(2776.689, 2776.689, 1.2393345855018391E-8);
        double double0 = normalDistributionImpl0.getMean();
        normalDistributionImpl0.getStandardDeviation();
    }

    @Test(timeout = 4000)
    public void test0921() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl(2776.689, 2776.689, 1.2393345855018391E-8);
        double double0 = normalDistributionImpl0.getMean();
    }

    @Test(timeout = 4000)
    public void test1022() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl((-1914.57745749), 782.268, 0.0);
        double double0 = normalDistributionImpl0.getMean();
    }

    @Test(timeout = 4000)
    public void test1023() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl((-1914.57745749), 782.268, 0.0);
        double double0 = normalDistributionImpl0.getMean();
        normalDistributionImpl0.getStandardDeviation();
    }

    @Test(timeout = 4000)
    public void test1124() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl((-1914.57745749), 782.268, 0.0);
        double double0 = normalDistributionImpl0.getInitialDomain(0.0);
    }

    @Test(timeout = 4000)
    public void test1225() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl();
        double double0 = normalDistributionImpl0.getDomainUpperBound(0.0);
    }

    @Test(timeout = 4000)
    public void test1226() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl();
        double double0 = normalDistributionImpl0.getDomainUpperBound(0.0);
        normalDistributionImpl0.getStandardDeviation();
    }

    @Test(timeout = 4000)
    public void test1327() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl(0.75, 0.5, 0.75);
        double double0 = normalDistributionImpl0.getDomainUpperBound(0.5);
        normalDistributionImpl0.getMean();
    }

    @Test(timeout = 4000)
    public void test1328() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl(0.75, 0.5, 0.75);
        double double0 = normalDistributionImpl0.getDomainUpperBound(0.5);
        normalDistributionImpl0.getStandardDeviation();
    }

    @Test(timeout = 4000)
    public void test1329() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl(0.75, 0.5, 0.75);
        double double0 = normalDistributionImpl0.getDomainUpperBound(0.5);
    }

    @Test(timeout = 4000)
    public void test1430() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl((-2186.7531), 1.0E-9);
        double double0 = normalDistributionImpl0.getDomainUpperBound((-7736.567));
    }

    @Test(timeout = 4000)
    public void test1531() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl(4.7421875, 4.7421875);
        double double0 = normalDistributionImpl0.getDomainLowerBound(2.0);
    }

    @Test(timeout = 4000)
    public void test1532() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl(4.7421875, 4.7421875);
        double double0 = normalDistributionImpl0.getDomainLowerBound(2.0);
        normalDistributionImpl0.getStandardDeviation();
    }

    @Test(timeout = 4000)
    public void test1633() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl((-1.7976931348623157E308), 209.088917880791, 209.088917880791);
        normalDistributionImpl0.getDomainLowerBound((-1.7976931348623157E308));
        normalDistributionImpl0.getMean();
    }

    @Test(timeout = 4000)
    public void test1634() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl((-1.7976931348623157E308), 209.088917880791, 209.088917880791);
        normalDistributionImpl0.getDomainLowerBound((-1.7976931348623157E308));
        normalDistributionImpl0.getStandardDeviation();
    }

    @Test(timeout = 4000)
    public void test1735() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl((-2186.7531), 1.0E-9);
        double double0 = normalDistributionImpl0.density((-1484.7644277));
        normalDistributionImpl0.getMean();
    }

    @Test(timeout = 4000)
    public void test1736() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl((-2186.7531), 1.0E-9);
        double double0 = normalDistributionImpl0.density((-1484.7644277));
    }

    @Test(timeout = 4000)
    public void test1837() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl();
        double double0 = normalDistributionImpl0.cumulativeProbability((-1806.763993368636));
        normalDistributionImpl0.getMean();
    }

    @Test(timeout = 4000)
    public void test1838() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl();
        double double0 = normalDistributionImpl0.cumulativeProbability((-1806.763993368636));
        normalDistributionImpl0.getStandardDeviation();
    }

    @Test(timeout = 4000)
    public void test1839() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl();
        double double0 = normalDistributionImpl0.cumulativeProbability((-1806.763993368636));
    }

    @Test(timeout = 4000)
    public void test1940() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl((-1273.944844), 6.283185307179586, (-2317.66173462341));
        double double0 = normalDistributionImpl0.cumulativeProbability(0.0);
        normalDistributionImpl0.getStandardDeviation();
    }

    @Test(timeout = 4000)
    public void test1941() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl((-1273.944844), 6.283185307179586, (-2317.66173462341));
        double double0 = normalDistributionImpl0.cumulativeProbability(0.0);
        normalDistributionImpl0.getMean();
    }

    @Test(timeout = 4000)
    public void test1942() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl((-1273.944844), 6.283185307179586, (-2317.66173462341));
        double double0 = normalDistributionImpl0.cumulativeProbability(0.0);
    }

    @Test(timeout = 4000)
    public void test2043() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl((-2606.330267894), 1.4456468917292502E-16);
        normalDistributionImpl0.inverseCumulativeProbability(1.0E-9);
    }

    @Test(timeout = 4000)
    public void test2244() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl((-1914.57745749), 782.268, 0.0);
        normalDistributionImpl0.inverseCumulativeProbability((-1914.57745749));
    }

    @Test(timeout = 4000)
    public void test2345() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl((-1.7976931348623157E308), 209.088917880791, 209.088917880791);
        normalDistributionImpl0.cumulativeProbability(0.0);
    }

    @Test(timeout = 4000)
    public void test2446() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = null;
        normalDistributionImpl0 = new NormalDistributionImpl((-2289.0505632029985), (-36.04));
    }

    @Test(timeout = 4000)
    public void test2547() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl();
        double double0 = normalDistributionImpl0.getInitialDomain(0.5);
    }

    @Test(timeout = 4000)
    public void test2548() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl();
        double double0 = normalDistributionImpl0.getInitialDomain(0.5);
        normalDistributionImpl0.getStandardDeviation();
    }

    @Test(timeout = 4000)
    public void test2649() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl();
        double double0 = normalDistributionImpl0.inverseCumulativeProbability(1.0E-9);
    }

    @Test(timeout = 4000)
    public void test2750() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl(0.0, 0.008333333333333333);
        double double0 = normalDistributionImpl0.inverseCumulativeProbability(1.0);
        normalDistributionImpl0.getStandardDeviation();
    }

    @Test(timeout = 4000)
    public void test2751() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl(0.0, 0.008333333333333333);
        double double0 = normalDistributionImpl0.inverseCumulativeProbability(1.0);
        normalDistributionImpl0.getMean();
    }

    @Test(timeout = 4000)
    public void test2752() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl(0.0, 0.008333333333333333);
        double double0 = normalDistributionImpl0.inverseCumulativeProbability(1.0);
    }

    @Test(timeout = 4000)
    public void test2853() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl((-1914.57745749), 782.268, 0.0);
        double double0 = normalDistributionImpl0.inverseCumulativeProbability(0.0);
    }

    @Test(timeout = 4000)
    public void test2854() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl((-1914.57745749), 782.268, 0.0);
        double double0 = normalDistributionImpl0.inverseCumulativeProbability(0.0);
        normalDistributionImpl0.getMean();
    }

    @Test(timeout = 4000)
    public void test2855() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl((-1914.57745749), 782.268, 0.0);
        double double0 = normalDistributionImpl0.inverseCumulativeProbability(0.0);
        normalDistributionImpl0.getStandardDeviation();
    }

    @Test(timeout = 4000)
    public void test2956() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = null;
        normalDistributionImpl0 = new NormalDistributionImpl((-1507.8600820797808), (-1507.8600820797808), (-1507.8600820797808));
    }

    @Test(timeout = 4000)
    public void test3057() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl((-3298.3076802071), Double.POSITIVE_INFINITY);
        normalDistributionImpl0.inverseCumulativeProbability(0.9999997615814209);
    }

    @Test(timeout = 4000)
    public void test3158() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl();
        double double0 = normalDistributionImpl0.getStandardDeviation();
        normalDistributionImpl0.getMean();
    }

    @Test(timeout = 4000)
    public void test3159() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl();
        double double0 = normalDistributionImpl0.getStandardDeviation();
    }

    @Test(timeout = 4000)
    public void test3260() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl((-3298.3076802071), Double.POSITIVE_INFINITY);
        normalDistributionImpl0.sample();
        double double0 = normalDistributionImpl0.sample();
        normalDistributionImpl0.getMean();
    }

    @Test(timeout = 4000)
    public void test3261() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl((-3298.3076802071), Double.POSITIVE_INFINITY);
        normalDistributionImpl0.sample();
        double double0 = normalDistributionImpl0.sample();
        normalDistributionImpl0.getStandardDeviation();
    }

    @Test(timeout = 4000)
    public void test3262() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl((-3298.3076802071), Double.POSITIVE_INFINITY);
        normalDistributionImpl0.sample();
        double double0 = normalDistributionImpl0.sample();
    }

    @Test(timeout = 4000)
    public void test3363() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl();
        double double0 = normalDistributionImpl0.density(0.5000000003989423);
        normalDistributionImpl0.getMean();
    }

    @Test(timeout = 4000)
    public void test3364() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl();
        double double0 = normalDistributionImpl0.density(0.5000000003989423);
    }

    @Test(timeout = 4000)
    public void test3465() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl();
        double double0 = normalDistributionImpl0.getMean();
    }

    @Test(timeout = 4000)
    public void test3466() throws Throwable {
        NormalDistributionImpl normalDistributionImpl0 = new NormalDistributionImpl();
        double double0 = normalDistributionImpl0.getMean();
        normalDistributionImpl0.getStandardDeviation();
    }
}
