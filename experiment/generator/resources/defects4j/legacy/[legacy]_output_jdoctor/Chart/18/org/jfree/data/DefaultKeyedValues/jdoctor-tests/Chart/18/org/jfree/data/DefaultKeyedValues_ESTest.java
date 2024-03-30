/*
 * This file was automatically generated by EvoSuite
 * Wed Oct 11 02:22:41 GMT 2023
 */
package org.jfree.data;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.util.HashMap;
import java.util.List;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.jfree.chart.util.SortOrder;
import org.jfree.data.DefaultKeyedValues;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class DefaultKeyedValues_ESTest extends DefaultKeyedValues_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        Comparable<DefaultKeyedValues> comparable0 = (Comparable<DefaultKeyedValues>) mock(Comparable.class, new ViolatedAssumptionAnswer());
        Float float0 = new Float(2454.9F);
        defaultKeyedValues0.setValue((Comparable) comparable0, (Number) float0);
        DefaultKeyedValues defaultKeyedValues1 = new DefaultKeyedValues();
        boolean boolean0 = defaultKeyedValues0.equals(defaultKeyedValues1);
    }

    @Test(timeout = 4000)
    public void test001() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        Comparable<DefaultKeyedValues> comparable0 = (Comparable<DefaultKeyedValues>) mock(Comparable.class, new ViolatedAssumptionAnswer());
        Float float0 = new Float(2454.9F);
        defaultKeyedValues0.setValue((Comparable) comparable0, (Number) float0);
        DefaultKeyedValues defaultKeyedValues1 = new DefaultKeyedValues();
        boolean boolean0 = defaultKeyedValues0.equals(defaultKeyedValues1);
        defaultKeyedValues1.equals((Object) defaultKeyedValues0);
    }

    @Test(timeout = 4000)
    public void test012() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        Comparable<Object> comparable0 = (Comparable<Object>) mock(Comparable.class, new ViolatedAssumptionAnswer());
        Double double0 = Double.valueOf(0.0);
        defaultKeyedValues0.addValue((Comparable) comparable0, (Number) double0);
        defaultKeyedValues0.addValue((Comparable) double0, 0.0);
        if ((Comparable) double0 == null) {
            try {
                defaultKeyedValues0.removeValue((Comparable) double0);
                fail();
            } catch (java.lang.IllegalArgumentException e) {
                // Successfully thrown exception
            }
        } else {
            defaultKeyedValues0.removeValue((Comparable) double0);
        }
        defaultKeyedValues0.getItemCount();
    }

    @Test(timeout = 4000)
    public void test023() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        Comparable<Object> comparable0 = (Comparable<Object>) mock(Comparable.class, new ViolatedAssumptionAnswer());
        Float float0 = new Float(1870.9402F);
        defaultKeyedValues0.addValue((Comparable) comparable0, (Number) float0);
        defaultKeyedValues0.setValue((Comparable) float0, (Number) float0);
        defaultKeyedValues0.insertValue(0, (Comparable) float0, (Number) float0);
        defaultKeyedValues0.getItemCount();
    }

    @Test(timeout = 4000)
    public void test034() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        Double double0 = new Double(0.0);
        defaultKeyedValues0.insertValue(0, (Comparable) double0, 2096.5);
        Integer integer0 = new Integer(0);
        defaultKeyedValues0.addValue((Comparable) integer0, (-462.911));
        defaultKeyedValues0.addValue((Comparable) integer0, (Number) double0);
        defaultKeyedValues0.getItemCount();
    }

    @Test(timeout = 4000)
    public void test045() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        Comparable<Object> comparable0 = (Comparable<Object>) mock(Comparable.class, new ViolatedAssumptionAnswer());
        Float float0 = new Float(1870.9402F);
        defaultKeyedValues0.addValue((Comparable) comparable0, (Number) float0);
        defaultKeyedValues0.setValue((Comparable) float0, (Number) float0);
        Number number0 = defaultKeyedValues0.getValue((Comparable) float0);
    }

    @Test(timeout = 4000)
    public void test056() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        defaultKeyedValues0.setValue((Comparable) "", (Number) null);
        Number number0 = defaultKeyedValues0.getValue((Comparable) "");
    }

    @Test(timeout = 4000)
    public void test067() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        Long long0 = Long.valueOf((-1L));
        defaultKeyedValues0.insertValue(0, (Comparable) long0, (double) (-1L));
        defaultKeyedValues0.getItemCount();
    }

    @Test(timeout = 4000)
    public void test068() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        Long long0 = Long.valueOf((-1L));
        defaultKeyedValues0.insertValue(0, (Comparable) long0, (double) (-1L));
        Number number0 = defaultKeyedValues0.getValue((Comparable) long0);
    }

    @Test(timeout = 4000)
    public void test079() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        Float float0 = new Float((double) 0);
        defaultKeyedValues0.insertValue(0, (Comparable) float0, (Number) float0);
        defaultKeyedValues0.getValue(0);
        defaultKeyedValues0.getItemCount();
    }

    @Test(timeout = 4000)
    public void test0810() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        Comparable<Object> comparable0 = (Comparable<Object>) mock(Comparable.class, new ViolatedAssumptionAnswer());
        Integer integer0 = new Integer(2412);
        defaultKeyedValues0.setValue((Comparable) comparable0, (Number) integer0);
        Number number0 = defaultKeyedValues0.getValue(0);
    }

    @Test(timeout = 4000)
    public void test0911() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        Long long0 = Long.valueOf((-1L));
        defaultKeyedValues0.insertValue(0, (Comparable) long0, (double) (-1L));
        defaultKeyedValues0.getValue(0);
        defaultKeyedValues0.getItemCount();
    }

    @Test(timeout = 4000)
    public void test1012() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        Comparable<Object> comparable0 = (Comparable<Object>) mock(Comparable.class, new ViolatedAssumptionAnswer());
        doReturn((String) null).when(comparable0).toString();
        Integer integer0 = new Integer(2412);
        defaultKeyedValues0.setValue((Comparable) comparable0, (Number) integer0);
        List list0 = defaultKeyedValues0.getKeys();
        list0.isEmpty();
    }

    @Test(timeout = 4000)
    public void test1113() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        Float float0 = new Float((double) 0);
        defaultKeyedValues0.insertValue(0, (Comparable) float0, (Number) float0);
        defaultKeyedValues0.getKey(0);
        defaultKeyedValues0.getItemCount();
    }

    @Test(timeout = 4000)
    public void test1214() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        Comparable<String> comparable0 = (Comparable<String>) mock(Comparable.class, new ViolatedAssumptionAnswer());
        defaultKeyedValues0.addValue(comparable0, 0.0);
        int int0 = defaultKeyedValues0.getItemCount();
    }

    @Test(timeout = 4000)
    public void test1315() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        Float float0 = new Float((-119.2199290804578));
        defaultKeyedValues0.setValue((Comparable) (-1), (Number) float0);
        defaultKeyedValues0.setValue((Comparable) float0, 0.0);
        int int0 = defaultKeyedValues0.getIndex(float0);
    }

    @Test(timeout = 4000)
    public void test1416() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        defaultKeyedValues0.sortByValues((SortOrder) null);
    }

    @Test(timeout = 4000)
    public void test1517() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        defaultKeyedValues0.sortByKeys((SortOrder) null);
    }

    @Test(timeout = 4000)
    public void test1618() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        Comparable<Object> comparable0 = (Comparable<Object>) mock(Comparable.class, new ViolatedAssumptionAnswer());
        Float float0 = new Float(1870.9402F);
        defaultKeyedValues0.addValue((Comparable) comparable0, (Number) float0);
        SortOrder sortOrder0 = SortOrder.DESCENDING;
        defaultKeyedValues0.insertValue(0, (Comparable) float0, (Number) float0);
        defaultKeyedValues0.sortByKeys(sortOrder0);
    }

    @Test(timeout = 4000)
    public void test1719() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        Float float0 = new Float(0.0);
        defaultKeyedValues0.insertValue(0, (Comparable) float0, 0.0);
        defaultKeyedValues0.removeValue(0);
        defaultKeyedValues0.setValue((Comparable) float0, (Number) float0);
    }

    @Test(timeout = 4000)
    public void test1820() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        defaultKeyedValues0.setValue((Comparable) null, 0.0);
    }

    @Test(timeout = 4000)
    public void test1921() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        if ((Comparable) null == null) {
            try {
                defaultKeyedValues0.removeValue((Comparable) null);
                fail();
            } catch (java.lang.IllegalArgumentException e) {
                // Successfully thrown exception
            }
        } else {
            defaultKeyedValues0.removeValue((Comparable) null);
        }
    }

    @Test(timeout = 4000)
    public void test2022() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        defaultKeyedValues0.removeValue(1);
    }

    @Test(timeout = 4000)
    public void test2123() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        defaultKeyedValues0.removeValue((-1));
    }

    @Test(timeout = 4000)
    public void test2224() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        Byte byte0 = new Byte((byte) 0);
        defaultKeyedValues0.addValue((Comparable) byte0, 0.0);
        if ((Comparable) byte0 == null) {
            try {
                defaultKeyedValues0.removeValue((Comparable) byte0);
                fail();
            } catch (java.lang.IllegalArgumentException e) {
                // Successfully thrown exception
            }
        } else {
            defaultKeyedValues0.removeValue((Comparable) byte0);
        }
        defaultKeyedValues0.insertValue((int) (byte) 0, (Comparable) byte0, (double) (byte) 0);
    }

    @Test(timeout = 4000)
    public void test2325() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        Double double0 = Double.valueOf(0.0);
        defaultKeyedValues0.addValue((Comparable) double0, 0.0);
        if ((Comparable) double0 == null) {
            try {
                defaultKeyedValues0.removeValue((Comparable) double0);
                fail();
            } catch (java.lang.IllegalArgumentException e) {
                // Successfully thrown exception
            }
        } else {
            defaultKeyedValues0.removeValue((Comparable) double0);
        }
        defaultKeyedValues0.getValue((Comparable) double0);
    }

    @Test(timeout = 4000)
    public void test2426() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        defaultKeyedValues0.getValue((-804));
    }

    @Test(timeout = 4000)
    public void test2527() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        defaultKeyedValues0.getKey((-804));
    }

    @Test(timeout = 4000)
    public void test2628() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        Comparable<Object> comparable0 = (Comparable<Object>) mock(Comparable.class, new ViolatedAssumptionAnswer());
        defaultKeyedValues0.insertValue(0, comparable0, 0.0);
        DefaultKeyedValues defaultKeyedValues1 = (DefaultKeyedValues) defaultKeyedValues0.clone();
        defaultKeyedValues1.removeValue(0);
        defaultKeyedValues1.equals(defaultKeyedValues0);
    }

    @Test(timeout = 4000)
    public void test2729() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        Byte byte0 = new Byte((byte) 1);
        defaultKeyedValues0.addValue((Comparable) null, (Number) byte0);
    }

    @Test(timeout = 4000)
    public void test2830() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        Long long0 = Long.valueOf((-513L));
        defaultKeyedValues0.addValue((Comparable) long0, (-166.9503437));
        defaultKeyedValues0.removeValue(0);
        defaultKeyedValues0.addValue((Comparable) long0, 851.601);
    }

    @Test(timeout = 4000)
    public void test2931() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        Float float0 = new Float(0.0);
        defaultKeyedValues0.insertValue(0, (Comparable) float0, 0.0);
        defaultKeyedValues0.removeValue(0);
        defaultKeyedValues0.setValue((Comparable) float0, 6.216529566760622);
    }

    @Test(timeout = 4000)
    public void test3032() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        Byte byte0 = new Byte((byte) (-78));
        defaultKeyedValues0.insertValue((int) (byte) (-78), (Comparable) byte0, (Number) byte0);
    }

    @Test(timeout = 4000)
    public void test3133() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        Integer integer0 = Integer.getInteger("HS B", 0);
        defaultKeyedValues0.setValue((Comparable) null, (Number) integer0);
    }

    @Test(timeout = 4000)
    public void test3234() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        Long long0 = Long.valueOf((-513L));
        defaultKeyedValues0.addValue((Comparable) long0, (-166.9503437));
        int int0 = defaultKeyedValues0.getIndex(long0);
    }

    @Test(timeout = 4000)
    public void test3335() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        int default0;
        if ((Comparable) null == null) {
            try {
                default0 = defaultKeyedValues0.getIndex((Comparable) null);
                fail();
            } catch (java.lang.IllegalArgumentException e) {
                // Successfully thrown exception
            }
        } else {
            default0 = defaultKeyedValues0.getIndex((Comparable) null);
        }
    }

    @Test(timeout = 4000)
    public void test3436() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        Comparable<String> comparable0 = (Comparable<String>) mock(Comparable.class, new ViolatedAssumptionAnswer());
        int int0;
        if (comparable0 == null) {
            try {
                int0 = defaultKeyedValues0.getIndex(comparable0);
                fail();
            } catch (java.lang.IllegalArgumentException e) {
                // Successfully thrown exception
            }
        } else {
            int0 = defaultKeyedValues0.getIndex(comparable0);
        }
    }

    @Test(timeout = 4000)
    public void test3537() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        int int0 = defaultKeyedValues0.getItemCount();
    }

    @Test(timeout = 4000)
    public void test3638() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        defaultKeyedValues0.getKey(0);
    }

    @Test(timeout = 4000)
    public void test3739() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        defaultKeyedValues0.clear();
        defaultKeyedValues0.getItemCount();
    }

    @Test(timeout = 4000)
    public void test3840() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        defaultKeyedValues0.getValue(0);
    }

    @Test(timeout = 4000)
    public void test4041() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        DefaultKeyedValues defaultKeyedValues1 = new DefaultKeyedValues();
        defaultKeyedValues1.equals((Object) defaultKeyedValues0);
    }

    @Test(timeout = 4000)
    public void test4042() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        DefaultKeyedValues defaultKeyedValues1 = new DefaultKeyedValues();
        defaultKeyedValues0.setValue((Comparable) ">", (Number) null);
        Double double0 = new Double(0.0);
        defaultKeyedValues1.setValue((Comparable) ">", (Number) double0);
        boolean boolean0 = defaultKeyedValues1.equals(defaultKeyedValues0);
        defaultKeyedValues1.equals((Object) defaultKeyedValues0);
    }

    @Test(timeout = 4000)
    public void test4043() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        DefaultKeyedValues defaultKeyedValues1 = new DefaultKeyedValues();
        defaultKeyedValues0.setValue((Comparable) ">", (Number) null);
        Double double0 = new Double(0.0);
        defaultKeyedValues1.setValue((Comparable) ">", (Number) double0);
        boolean boolean0 = defaultKeyedValues1.equals(defaultKeyedValues0);
    }

    @Test(timeout = 4000)
    public void test4144() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        DefaultKeyedValues defaultKeyedValues1 = new DefaultKeyedValues();
        defaultKeyedValues1.equals((Object) defaultKeyedValues0);
    }

    @Test(timeout = 4000)
    public void test4145() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        DefaultKeyedValues defaultKeyedValues1 = new DefaultKeyedValues();
        defaultKeyedValues0.setValue((Comparable) ">", (Number) null);
        Double double0 = new Double(0.0);
        defaultKeyedValues1.setValue((Comparable) ">", (Number) double0);
        boolean boolean0 = defaultKeyedValues0.equals(defaultKeyedValues1);
        defaultKeyedValues1.equals((Object) defaultKeyedValues0);
    }

    @Test(timeout = 4000)
    public void test4146() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        DefaultKeyedValues defaultKeyedValues1 = new DefaultKeyedValues();
        defaultKeyedValues0.setValue((Comparable) ">", (Number) null);
        Double double0 = new Double(0.0);
        defaultKeyedValues1.setValue((Comparable) ">", (Number) double0);
        boolean boolean0 = defaultKeyedValues0.equals(defaultKeyedValues1);
    }

    @Test(timeout = 4000)
    public void test4247() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        Comparable<Object> comparable0 = (Comparable<Object>) mock(Comparable.class, new ViolatedAssumptionAnswer());
        doReturn((String) null, (String) null).when(comparable0).toString();
        defaultKeyedValues0.insertValue(0, (Comparable) comparable0, (double) 0);
        Object object0 = defaultKeyedValues0.clone();
        boolean boolean0 = object0.equals(defaultKeyedValues0);
        defaultKeyedValues0.getItemCount();
    }

    @Test(timeout = 4000)
    public void test4248() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        Comparable<Object> comparable0 = (Comparable<Object>) mock(Comparable.class, new ViolatedAssumptionAnswer());
        doReturn((String) null, (String) null).when(comparable0).toString();
        defaultKeyedValues0.insertValue(0, (Comparable) comparable0, (double) 0);
        Object object0 = defaultKeyedValues0.clone();
        boolean boolean0 = object0.equals(defaultKeyedValues0);
    }

    @Test(timeout = 4000)
    public void test4349() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        Float float0 = new Float(2437.1506F);
        DefaultKeyedValues defaultKeyedValues1 = (DefaultKeyedValues) defaultKeyedValues0.clone();
        defaultKeyedValues1.equals((Object) defaultKeyedValues0);
    }

    @Test(timeout = 4000)
    public void test4350() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        Float float0 = new Float(2437.1506F);
        DefaultKeyedValues defaultKeyedValues1 = (DefaultKeyedValues) defaultKeyedValues0.clone();
        defaultKeyedValues1.addValue((Comparable) "'position' out of bounds.", (Number) float0);
        boolean boolean0 = defaultKeyedValues0.equals(defaultKeyedValues1);
    }

    @Test(timeout = 4000)
    public void test4351() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        Float float0 = new Float(2437.1506F);
        DefaultKeyedValues defaultKeyedValues1 = (DefaultKeyedValues) defaultKeyedValues0.clone();
        defaultKeyedValues1.addValue((Comparable) "'position' out of bounds.", (Number) float0);
        boolean boolean0 = defaultKeyedValues0.equals(defaultKeyedValues1);
        defaultKeyedValues1.equals((Object) defaultKeyedValues0);
    }

    @Test(timeout = 4000)
    public void test4452() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        HashMap<DefaultKeyedValues, Long> hashMap0 = new HashMap<DefaultKeyedValues, Long>(0);
        boolean boolean0 = defaultKeyedValues0.equals(hashMap0);
    }

    @Test(timeout = 4000)
    public void test4553() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        boolean boolean0 = defaultKeyedValues0.equals(defaultKeyedValues0);
    }

    @Test(timeout = 4000)
    public void test4654() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        Comparable<DefaultKeyedValues> comparable0 = (Comparable<DefaultKeyedValues>) mock(Comparable.class, new ViolatedAssumptionAnswer());
        defaultKeyedValues0.insertValue(0, comparable0, (-1948.991));
        SortOrder sortOrder0 = SortOrder.ASCENDING;
        defaultKeyedValues0.sortByValues(sortOrder0);
        defaultKeyedValues0.getItemCount();
    }

    @Test(timeout = 4000)
    public void test4755() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        Integer integer0 = Integer.getInteger("Null 'key' argument.", 223);
        if ((Comparable) integer0 == null) {
            try {
                defaultKeyedValues0.removeValue((Comparable) integer0);
                fail();
            } catch (java.lang.IllegalArgumentException e) {
                // Successfully thrown exception
            }
        } else {
            defaultKeyedValues0.removeValue((Comparable) integer0);
        }
        defaultKeyedValues0.getItemCount();
    }

    @Test(timeout = 4000)
    public void test4856() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        Float float0 = new Float(0.0);
        Comparable<Object> comparable0 = (Comparable<Object>) mock(Comparable.class, new ViolatedAssumptionAnswer());
        defaultKeyedValues0.addValue(comparable0, 0.0);
        defaultKeyedValues0.insertValue(0, (Comparable) float0, 0.0);
        defaultKeyedValues0.removeValue(0);
        defaultKeyedValues0.getItemCount();
    }

    @Test(timeout = 4000)
    public void test4957() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        Float float0 = new Float(0.0);
        defaultKeyedValues0.insertValue(0, (Comparable) float0, 0.0);
        defaultKeyedValues0.insertValue(1, (Comparable) float0, (Number) float0);
    }

    @Test(timeout = 4000)
    public void test5058() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        Long long0 = Long.valueOf((-513L));
        defaultKeyedValues0.insertValue(0, (Comparable) long0, (Number) long0);
        defaultKeyedValues0.insertValue(0, (Comparable) long0, (Number) long0);
        defaultKeyedValues0.getItemCount();
    }

    @Test(timeout = 4000)
    public void test5159() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        defaultKeyedValues0.insertValue(0, (Comparable) null, (Number) null);
    }

    @Test(timeout = 4000)
    public void test5260() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        Comparable<String> comparable0 = (Comparable<String>) mock(Comparable.class, new ViolatedAssumptionAnswer());
        Integer integer0 = new Integer(1318);
        defaultKeyedValues0.insertValue(1, (Comparable) comparable0, (Number) integer0);
    }

    @Test(timeout = 4000)
    public void test5361() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        Long long0 = Long.valueOf((-1L));
        defaultKeyedValues0.insertValue((-1), (Comparable) long0, 1390.2829486);
    }

    @Test(timeout = 4000)
    public void test5462() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        defaultKeyedValues0.addValue((Comparable) null, 0.0);
    }

    @Test(timeout = 4000)
    public void test5563() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        Comparable<DefaultKeyedValues> comparable0 = (Comparable<DefaultKeyedValues>) mock(Comparable.class, new ViolatedAssumptionAnswer());
        doReturn((String) null).when(comparable0).toString();
        defaultKeyedValues0.getValue(comparable0);
    }

    @Test(timeout = 4000)
    public void test5664() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        defaultKeyedValues0.getValue((Comparable) null);
    }

    @Test(timeout = 4000)
    public void test5765() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        List list0 = defaultKeyedValues0.getKeys();
        list0.size();
    }

    @Test(timeout = 4000)
    public void test5866() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        SortOrder sortOrder0 = SortOrder.ASCENDING;
        defaultKeyedValues0.sortByKeys(sortOrder0);
        sortOrder0.toString();
    }

    @Test(timeout = 4000)
    public void test5967() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        Double double0 = Double.valueOf(0.0);
        defaultKeyedValues0.addValue((Comparable) double0, 0.0);
        Number number0 = defaultKeyedValues0.getValue((Comparable) double0);
    }

    @Test(timeout = 4000)
    public void test6068() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        Comparable<Object> comparable0 = (Comparable<Object>) mock(Comparable.class, new ViolatedAssumptionAnswer());
        Float float0 = new Float(235.58667F);
        defaultKeyedValues0.addValue((Comparable) comparable0, (Number) float0);
        SortOrder sortOrder0 = SortOrder.DESCENDING;
        defaultKeyedValues0.sortByKeys(sortOrder0);
        defaultKeyedValues0.getItemCount();
    }

    @Test(timeout = 4000)
    public void test6169() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        Comparable<Object> comparable0 = (Comparable<Object>) mock(Comparable.class, new ViolatedAssumptionAnswer());
        doReturn("").when(comparable0).toString();
        defaultKeyedValues0.insertValue(0, (Comparable) comparable0, (double) 0);
        DefaultKeyedValues defaultKeyedValues1 = new DefaultKeyedValues();
        Double double0 = new Double(0);
        defaultKeyedValues1.setValue((Comparable) "", (Number) double0);
        boolean boolean0 = defaultKeyedValues1.equals(defaultKeyedValues0);
        defaultKeyedValues0.getItemCount();
    }

    @Test(timeout = 4000)
    public void test6170() throws Throwable {
        DefaultKeyedValues defaultKeyedValues0 = new DefaultKeyedValues();
        Comparable<Object> comparable0 = (Comparable<Object>) mock(Comparable.class, new ViolatedAssumptionAnswer());
        doReturn("").when(comparable0).toString();
        defaultKeyedValues0.insertValue(0, (Comparable) comparable0, (double) 0);
        DefaultKeyedValues defaultKeyedValues1 = new DefaultKeyedValues();
        Double double0 = new Double(0);
        defaultKeyedValues1.setValue((Comparable) "", (Number) double0);
        boolean boolean0 = defaultKeyedValues1.equals(defaultKeyedValues0);
    }
}
