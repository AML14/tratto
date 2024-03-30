/*
 * This file was automatically generated by EvoSuite
 * Tue Mar 26 02:59:49 GMT 2024
 */
package org.jfree.data;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.util.List;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.jfree.chart.util.SortOrder;
import org.jfree.data.DefaultKeyedValues;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class DefaultKeyedValues_ESTest extends DefaultKeyedValues_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        DefaultKeyedValues defaultKeyedValues1 = new DefaultKeyedValues();
        defaultKeyedValues1.equals((Object) defaultKeyedValues0);
    }

    @Test(timeout = 4000)
    public void test001() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        DefaultKeyedValues defaultKeyedValues1 = new DefaultKeyedValues();
        Double double0 = Double.valueOf((-418.7609164));
        defaultKeyedValues0.addValue((Comparable) double0, (Number) null);
        defaultKeyedValues1.setValue((Comparable) double0, (double) 1);
        boolean boolean0 = defaultKeyedValues0.equals(defaultKeyedValues1);
        defaultKeyedValues1.equals((Object) defaultKeyedValues0);
    }

    @Test(timeout = 4000)
    public void test002() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        DefaultKeyedValues defaultKeyedValues1 = new DefaultKeyedValues();
        Double double0 = Double.valueOf((-418.7609164));
        defaultKeyedValues0.addValue((Comparable) double0, (Number) null);
        defaultKeyedValues1.setValue((Comparable) double0, (double) 1);
        boolean boolean0 = defaultKeyedValues0.equals(defaultKeyedValues1);
    }

    @Test(timeout = 4000)
    public void test013() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        defaultKeyedValues0.insertValue(0, (Comparable) null, (double) 0);
    }

    @Test(timeout = 4000)
    public void test024() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        List list0 = defaultKeyedValues0.getKeys();
        list0.isEmpty();
    }

    @Test(timeout = 4000)
    public void test035() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        defaultKeyedValues0.getValue((Comparable) null);
    }

    @Test(timeout = 4000)
    public void test046() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        Float float0 = new Float((float) 1);
        defaultKeyedValues0.addValue((Comparable) float0, (Number) float0);
        defaultKeyedValues0.insertValue(1, (Comparable) float0, (Number) float0);
    }

    @Test(timeout = 4000)
    public void test057() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        Integer integer0 = new Integer(0);
        defaultKeyedValues0.setValue((Comparable) integer0, (Number) integer0);
        Number number0 = defaultKeyedValues0.getValue((Comparable) integer0);
    }

    @Test(timeout = 4000)
    public void test068() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        Double double0 = new Double(3778.1961882888086);
        defaultKeyedValues0.getValue((Comparable) double0);
    }

    @Test(timeout = 4000)
    public void test079() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        defaultKeyedValues0.setValue((Comparable) null, (-2740.4470689125));
    }

    @Test(timeout = 4000)
    public void test0810() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        Integer integer0 = Integer.valueOf(0);
        defaultKeyedValues0.setValue((Comparable) integer0, (Number) integer0);
        defaultKeyedValues0.addValue((Comparable) integer0, (double) 0);
        defaultKeyedValues0.getItemCount();
    }

    @Test(timeout = 4000)
    public void test0911() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        Short short0 = new Short((short) (-18));
        defaultKeyedValues0.insertValue((int) (short) (-18), (Comparable) short0, (double) (short) (-18));
    }

    @Test(timeout = 4000)
    public void test1012() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        Short short0 = new Short((short) 12);
        defaultKeyedValues0.insertValue((int) (short) 12, (Comparable) short0, (double) (short) 12);
    }

    @Test(timeout = 4000)
    public void test1113() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        Short short0 = new Short((short) 0);
        defaultKeyedValues0.insertValue((int) (short) 0, (Comparable) short0, (double) (short) 0);
        defaultKeyedValues0.insertValue((int) (short) 0, (Comparable) short0, (double) (short) 0);
        defaultKeyedValues0.getItemCount();
    }

    @Test(timeout = 4000)
    public void test1214() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        Short short0 = new Short((short) 0);
        defaultKeyedValues0.insertValue((int) (short) 0, (Comparable) short0, (double) (short) 0);
        if ((Comparable) short0 == null) {
            try {
                defaultKeyedValues0.removeValue((Comparable) short0);
                fail();
            } catch (java.lang.IllegalArgumentException e) {
                // Successfully thrown exception
            }
        } else {
            defaultKeyedValues0.removeValue((Comparable) short0);
        }
        defaultKeyedValues0.getItemCount();
    }

    @Test(timeout = 4000)
    public void test1315() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        defaultKeyedValues0.setValue((Comparable) 1, 343.5962409665);
        Integer integer0 = Integer.valueOf(0);
        defaultKeyedValues0.setValue((Comparable) integer0, (Number) integer0);
        defaultKeyedValues0.removeValue(0);
        defaultKeyedValues0.getItemCount();
    }

    @Test(timeout = 4000)
    public void test1416() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        Float float0 = new Float(4501.0F);
        if ((Comparable) float0 == null) {
            try {
                defaultKeyedValues0.removeValue((Comparable) float0);
                fail();
            } catch (java.lang.IllegalArgumentException e) {
                // Successfully thrown exception
            }
        } else {
            defaultKeyedValues0.removeValue((Comparable) float0);
        }
        defaultKeyedValues0.getItemCount();
    }

    @Test(timeout = 4000)
    public void test1517() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        defaultKeyedValues0.setValue((Comparable) 0, (double) 0);
        SortOrder sortOrder0 = SortOrder.ASCENDING;
        defaultKeyedValues0.sortByKeys(sortOrder0);
        defaultKeyedValues0.getItemCount();
    }

    @Test(timeout = 4000)
    public void test1618() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        defaultKeyedValues0.setValue((Comparable) 0, (double) 0);
        SortOrder sortOrder0 = SortOrder.ASCENDING;
        defaultKeyedValues0.sortByValues(sortOrder0);
        defaultKeyedValues0.getItemCount();
    }

    @Test(timeout = 4000)
    public void test1719() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        boolean boolean0 = defaultKeyedValues0.equals(defaultKeyedValues0);
    }

    @Test(timeout = 4000)
    public void test1820() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        boolean boolean0 = defaultKeyedValues0.equals("org.jfree.data.DefaultKeyedValue");
    }

    @Test(timeout = 4000)
    public void test1921() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        defaultKeyedValues0.setValue((Comparable) 0, (double) 0);
        DefaultKeyedValues defaultKeyedValues1 = new DefaultKeyedValues();
        boolean boolean0 = defaultKeyedValues0.equals(defaultKeyedValues1);
    }

    @Test(timeout = 4000)
    public void test1922() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        defaultKeyedValues0.setValue((Comparable) 0, (double) 0);
        DefaultKeyedValues defaultKeyedValues1 = new DefaultKeyedValues();
        boolean boolean0 = defaultKeyedValues0.equals(defaultKeyedValues1);
        defaultKeyedValues1.equals((Object) defaultKeyedValues0);
    }

    @Test(timeout = 4000)
    public void test2023() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        Double double0 = Double.valueOf(1647.2540407);
        defaultKeyedValues0.setValue((Comparable) double0, (Number) double0);
        Integer integer0 = Integer.valueOf(0);
        DefaultKeyedValues defaultKeyedValues1 = new DefaultKeyedValues();
        defaultKeyedValues1.setValue((Comparable) integer0, (double) 0);
        boolean boolean0 = defaultKeyedValues0.equals(defaultKeyedValues1);
        defaultKeyedValues1.equals((Object) defaultKeyedValues0);
    }

    @Test(timeout = 4000)
    public void test2024() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        Double double0 = Double.valueOf(1647.2540407);
        defaultKeyedValues0.setValue((Comparable) double0, (Number) double0);
        Integer integer0 = Integer.valueOf(0);
        DefaultKeyedValues defaultKeyedValues1 = new DefaultKeyedValues();
        defaultKeyedValues1.setValue((Comparable) integer0, (double) 0);
        boolean boolean0 = defaultKeyedValues0.equals(defaultKeyedValues1);
    }

    @Test(timeout = 4000)
    public void test2125() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        defaultKeyedValues0.setValue((Comparable) 0, (double) 0);
        Object object0 = defaultKeyedValues0.clone();
        boolean boolean0 = defaultKeyedValues0.equals(object0);
    }

    @Test(timeout = 4000)
    public void test2227() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        Byte byte0 = Byte.valueOf((byte) (-112));
        defaultKeyedValues0.addValue((Comparable) byte0, (Number) null);
        Object object0 = defaultKeyedValues0.clone();
        boolean boolean0 = defaultKeyedValues0.equals(object0);
    }

    @Test(timeout = 4000)
    public void test2329() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        Integer integer0 = Integer.valueOf(1578);
        defaultKeyedValues0.setValue((Comparable) integer0, (double) 1578);
        DefaultKeyedValues defaultKeyedValues1 = new DefaultKeyedValues();
        defaultKeyedValues1.setValue((Comparable) integer0, (Number) integer0);
        boolean boolean0 = defaultKeyedValues0.equals(defaultKeyedValues1);
        defaultKeyedValues1.equals((Object) defaultKeyedValues0);
    }

    @Test(timeout = 4000)
    public void test2330() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        Integer integer0 = Integer.valueOf(1578);
        defaultKeyedValues0.setValue((Comparable) integer0, (double) 1578);
        DefaultKeyedValues defaultKeyedValues1 = new DefaultKeyedValues();
        defaultKeyedValues1.setValue((Comparable) integer0, (Number) integer0);
        boolean boolean0 = defaultKeyedValues0.equals(defaultKeyedValues1);
    }
}
