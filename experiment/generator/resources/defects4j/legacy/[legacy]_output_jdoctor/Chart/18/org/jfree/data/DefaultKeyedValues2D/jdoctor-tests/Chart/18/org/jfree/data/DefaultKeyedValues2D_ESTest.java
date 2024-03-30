/*
 * This file was automatically generated by EvoSuite
 * Sun Nov 19 21:31:00 GMT 2023
 */
package org.jfree.data;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.jfree.data.DefaultKeyedValues2D;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class DefaultKeyedValues2D_ESTest extends DefaultKeyedValues2D_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        DefaultKeyedValues2D defaultKeyedValues2D0 = new DefaultKeyedValues2D();
        defaultKeyedValues2D0.removeRow((Comparable) null);
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        DefaultKeyedValues2D defaultKeyedValues2D0 = new DefaultKeyedValues2D();
        defaultKeyedValues2D0.removeColumn(0);
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        DefaultKeyedValues2D defaultKeyedValues2D0 = new DefaultKeyedValues2D();
        defaultKeyedValues2D0.removeRow((-139));
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        DefaultKeyedValues2D defaultKeyedValues2D0 = new DefaultKeyedValues2D();
        defaultKeyedValues2D0.clear();
        defaultKeyedValues2D0.getColumnCount();
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        DefaultKeyedValues2D defaultKeyedValues2D0 = new DefaultKeyedValues2D();
        defaultKeyedValues2D0.getRowKey((-1136));
    }

    @Test(timeout = 4000)
    public void test065() throws Throwable {
        DefaultKeyedValues2D defaultKeyedValues2D0 = new DefaultKeyedValues2D();
        Double double0 = new Double((-1.0F));
        defaultKeyedValues2D0.setValue(double0, double0, double0);
        Float float0 = new Float((-1.0F));
        defaultKeyedValues2D0.setValue(float0, float0, float0);
        Object object0 = defaultKeyedValues2D0.clone();
        boolean boolean0 = defaultKeyedValues2D0.equals(object0);
        defaultKeyedValues2D0.getColumnCount();
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        DefaultKeyedValues2D defaultKeyedValues2D0 = new DefaultKeyedValues2D();
        Double double0 = new Double((-1.0F));
        defaultKeyedValues2D0.setValue(double0, double0, double0);
        Float float0 = new Float((-1.0F));
        defaultKeyedValues2D0.setValue(float0, float0, float0);
        Object object0 = defaultKeyedValues2D0.clone();
        boolean boolean0 = defaultKeyedValues2D0.equals(object0);
    }

    @Test(timeout = 4000)
    public void test077() throws Throwable {
        Float float0 = new Float(0.0F);
        DefaultKeyedValues2D defaultKeyedValues2D0 = new DefaultKeyedValues2D(true);
        defaultKeyedValues2D0.setValue(float0, float0, float0);
        defaultKeyedValues2D0.getColumnCount();
    }

    @Test(timeout = 4000)
    public void test088() throws Throwable {
        DefaultKeyedValues2D defaultKeyedValues2D0 = new DefaultKeyedValues2D();
        Float float0 = new Float(0.0F);
        int int0 = defaultKeyedValues2D0.getColumnIndex(float0);
    }

    @Test(timeout = 4000)
    public void test099() throws Throwable {
        DefaultKeyedValues2D defaultKeyedValues2D0 = new DefaultKeyedValues2D();
        defaultKeyedValues2D0.getColumnIndex((Comparable) null);
    }

    @Test(timeout = 4000)
    public void test1010() throws Throwable {
        DefaultKeyedValues2D defaultKeyedValues2D0 = new DefaultKeyedValues2D();
        Double double0 = new Double((-1.0F));
        Float float0 = new Float((-1.0F));
        defaultKeyedValues2D0.getValue((Comparable) float0, (Comparable) double0);
    }

    @Test(timeout = 4000)
    public void test1111() throws Throwable {
        DefaultKeyedValues2D defaultKeyedValues2D0 = new DefaultKeyedValues2D();
        defaultKeyedValues2D0.getValue((Comparable) null, (Comparable) null);
    }

    @Test(timeout = 4000)
    public void test1212() throws Throwable {
        DefaultKeyedValues2D defaultKeyedValues2D0 = new DefaultKeyedValues2D();
        defaultKeyedValues2D0.getValue((Comparable) 871, (Comparable) null);
    }

    @Test(timeout = 4000)
    public void test1313() throws Throwable {
        DefaultKeyedValues2D defaultKeyedValues2D0 = new DefaultKeyedValues2D();
        Double double0 = new Double((-1.0F));
        defaultKeyedValues2D0.setValue(double0, double0, double0);
        Float float0 = new Float((-1.0F));
        defaultKeyedValues2D0.getValue((Comparable) float0, (Comparable) double0);
    }

    @Test(timeout = 4000)
    public void test1414() throws Throwable {
        DefaultKeyedValues2D defaultKeyedValues2D0 = new DefaultKeyedValues2D();
        Double double0 = new Double((-1.0F));
        defaultKeyedValues2D0.setValue(double0, double0, double0);
        Float float0 = new Float((-1.0F));
        defaultKeyedValues2D0.setValue(float0, float0, float0);
        Number number0 = defaultKeyedValues2D0.getValue((Comparable) float0, (Comparable) double0);
    }

    @Test(timeout = 4000)
    public void test1515() throws Throwable {
        DefaultKeyedValues2D defaultKeyedValues2D0 = new DefaultKeyedValues2D();
        Float float0 = new Float(0.0F);
        defaultKeyedValues2D0.setValue(float0, float0, float0);
        Number number0 = defaultKeyedValues2D0.getValue((Comparable) float0, (Comparable) float0);
    }

    @Test(timeout = 4000)
    public void test1616() throws Throwable {
        DefaultKeyedValues2D defaultKeyedValues2D0 = new DefaultKeyedValues2D();
        Long long0 = new Long((-2546L));
        defaultKeyedValues2D0.setValue(long0, long0, long0);
        Float float0 = new Float((float) (-2546L));
        defaultKeyedValues2D0.removeValue(long0, float0);
        defaultKeyedValues2D0.getColumnCount();
    }

    @Test(timeout = 4000)
    public void test1717() throws Throwable {
        DefaultKeyedValues2D defaultKeyedValues2D0 = new DefaultKeyedValues2D();
        Float float0 = new Float(2915.86213);
        defaultKeyedValues2D0.setValue(float0, float0, float0);
        Float float1 = Float.valueOf((-2643.3962F));
        defaultKeyedValues2D0.removeValue(float1, float1);
        defaultKeyedValues2D0.getColumnCount();
    }

    @Test(timeout = 4000)
    public void test1818() throws Throwable {
        DefaultKeyedValues2D defaultKeyedValues2D0 = new DefaultKeyedValues2D();
        Float float0 = new Float(8.42F);
        defaultKeyedValues2D0.setValue(float0, float0, float0);
        defaultKeyedValues2D0.removeValue(1, float0);
        defaultKeyedValues2D0.getRowCount();
    }

    @Test(timeout = 4000)
    public void test1919() throws Throwable {
        DefaultKeyedValues2D defaultKeyedValues2D0 = new DefaultKeyedValues2D();
        boolean boolean0 = defaultKeyedValues2D0.equals((Object) null);
    }

    @Test(timeout = 4000)
    public void test2020() throws Throwable {
        DefaultKeyedValues2D defaultKeyedValues2D0 = new DefaultKeyedValues2D();
        boolean boolean0 = defaultKeyedValues2D0.equals(defaultKeyedValues2D0);
    }

    @Test(timeout = 4000)
    public void test2121() throws Throwable {
        DefaultKeyedValues2D defaultKeyedValues2D0 = new DefaultKeyedValues2D();
        boolean boolean0 = defaultKeyedValues2D0.equals("YW*-M");
    }

    @Test(timeout = 4000)
    public void test2222() throws Throwable {
        DefaultKeyedValues2D defaultKeyedValues2D0 = new DefaultKeyedValues2D();
        DefaultKeyedValues2D defaultKeyedValues2D1 = (DefaultKeyedValues2D) defaultKeyedValues2D0.clone();
        defaultKeyedValues2D1.equals((Object) defaultKeyedValues2D0);
    }

    @Test(timeout = 4000)
    public void test2223() throws Throwable {
        DefaultKeyedValues2D defaultKeyedValues2D0 = new DefaultKeyedValues2D();
        DefaultKeyedValues2D defaultKeyedValues2D1 = (DefaultKeyedValues2D) defaultKeyedValues2D0.clone();
        Short short0 = new Short((short) 2440);
        defaultKeyedValues2D1.addValue(short0, short0, short0);
        boolean boolean0 = defaultKeyedValues2D1.equals(defaultKeyedValues2D0);
        defaultKeyedValues2D1.equals((Object) defaultKeyedValues2D0);
    }

    @Test(timeout = 4000)
    public void test2224() throws Throwable {
        DefaultKeyedValues2D defaultKeyedValues2D0 = new DefaultKeyedValues2D();
        DefaultKeyedValues2D defaultKeyedValues2D1 = (DefaultKeyedValues2D) defaultKeyedValues2D0.clone();
        Short short0 = new Short((short) 2440);
        defaultKeyedValues2D1.addValue(short0, short0, short0);
        boolean boolean0 = defaultKeyedValues2D1.equals(defaultKeyedValues2D0);
    }

    @Test(timeout = 4000)
    public void test2325() throws Throwable {
        DefaultKeyedValues2D defaultKeyedValues2D0 = new DefaultKeyedValues2D();
        Float float0 = new Float((-51.948048F));
        defaultKeyedValues2D0.setValue(float0, float0, float0);
        DefaultKeyedValues2D defaultKeyedValues2D1 = (DefaultKeyedValues2D) defaultKeyedValues2D0.clone();
        if ((Comparable) float0 == null) {
            try {
                defaultKeyedValues2D1.removeColumn((Comparable) float0);
                fail();
            } catch (java.lang.IllegalArgumentException e) {
                // Successfully thrown exception
            }
        } else {
            defaultKeyedValues2D1.removeColumn((Comparable) float0);
        }
        boolean boolean0 = defaultKeyedValues2D0.equals(defaultKeyedValues2D1);
        defaultKeyedValues2D0.getColumnCount();
    }

    @Test(timeout = 4000)
    public void test2326() throws Throwable {
        DefaultKeyedValues2D defaultKeyedValues2D0 = new DefaultKeyedValues2D();
        Float float0 = new Float((-51.948048F));
        defaultKeyedValues2D0.setValue(float0, float0, float0);
        DefaultKeyedValues2D defaultKeyedValues2D1 = (DefaultKeyedValues2D) defaultKeyedValues2D0.clone();
        if ((Comparable) float0 == null) {
            try {
                defaultKeyedValues2D1.removeColumn((Comparable) float0);
                fail();
            } catch (java.lang.IllegalArgumentException e) {
                // Successfully thrown exception
            }
        } else {
            defaultKeyedValues2D1.removeColumn((Comparable) float0);
        }
        boolean boolean0 = defaultKeyedValues2D0.equals(defaultKeyedValues2D1);
    }

    @Test(timeout = 4000)
    public void test2427() throws Throwable {
        DefaultKeyedValues2D defaultKeyedValues2D0 = new DefaultKeyedValues2D();
        Float float0 = new Float(0.0F);
        defaultKeyedValues2D0.setValue(float0, float0, float0);
        DefaultKeyedValues2D defaultKeyedValues2D1 = (DefaultKeyedValues2D) defaultKeyedValues2D0.clone();
        defaultKeyedValues2D1.equals((Object) defaultKeyedValues2D0);
    }

    @Test(timeout = 4000)
    public void test2428() throws Throwable {
        DefaultKeyedValues2D defaultKeyedValues2D0 = new DefaultKeyedValues2D();
        Float float0 = new Float(0.0F);
        defaultKeyedValues2D0.setValue(float0, float0, float0);
        DefaultKeyedValues2D defaultKeyedValues2D1 = (DefaultKeyedValues2D) defaultKeyedValues2D0.clone();
        defaultKeyedValues2D1.addValue((Number) null, float0, float0);
        boolean boolean0 = defaultKeyedValues2D1.equals(defaultKeyedValues2D0);
        defaultKeyedValues2D1.equals((Object) defaultKeyedValues2D0);
    }

    @Test(timeout = 4000)
    public void test2429() throws Throwable {
        DefaultKeyedValues2D defaultKeyedValues2D0 = new DefaultKeyedValues2D();
        Float float0 = new Float(0.0F);
        defaultKeyedValues2D0.setValue(float0, float0, float0);
        DefaultKeyedValues2D defaultKeyedValues2D1 = (DefaultKeyedValues2D) defaultKeyedValues2D0.clone();
        defaultKeyedValues2D1.addValue((Number) null, float0, float0);
        boolean boolean0 = defaultKeyedValues2D1.equals(defaultKeyedValues2D0);
    }

    @Test(timeout = 4000)
    public void test2530() throws Throwable {
        DefaultKeyedValues2D defaultKeyedValues2D0 = new DefaultKeyedValues2D();
        Float float0 = new Float(0.0F);
        defaultKeyedValues2D0.setValue(float0, float0, float0);
        DefaultKeyedValues2D defaultKeyedValues2D1 = (DefaultKeyedValues2D) defaultKeyedValues2D0.clone();
        defaultKeyedValues2D1.equals((Object) defaultKeyedValues2D0);
    }

    @Test(timeout = 4000)
    public void test2531() throws Throwable {
        DefaultKeyedValues2D defaultKeyedValues2D0 = new DefaultKeyedValues2D();
        Float float0 = new Float(0.0F);
        defaultKeyedValues2D0.setValue(float0, float0, float0);
        DefaultKeyedValues2D defaultKeyedValues2D1 = (DefaultKeyedValues2D) defaultKeyedValues2D0.clone();
        defaultKeyedValues2D1.addValue((Number) null, float0, float0);
        boolean boolean0 = defaultKeyedValues2D0.equals(defaultKeyedValues2D1);
    }

    @Test(timeout = 4000)
    public void test2532() throws Throwable {
        DefaultKeyedValues2D defaultKeyedValues2D0 = new DefaultKeyedValues2D();
        Float float0 = new Float(0.0F);
        defaultKeyedValues2D0.setValue(float0, float0, float0);
        DefaultKeyedValues2D defaultKeyedValues2D1 = (DefaultKeyedValues2D) defaultKeyedValues2D0.clone();
        defaultKeyedValues2D1.addValue((Number) null, float0, float0);
        boolean boolean0 = defaultKeyedValues2D0.equals(defaultKeyedValues2D1);
        defaultKeyedValues2D1.equals((Object) defaultKeyedValues2D0);
    }
}
