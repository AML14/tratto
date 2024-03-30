/*
 * This file was automatically generated by EvoSuite
 * Mon Oct 16 18:49:37 GMT 2023
 */
package org.apache.commons.math.stat;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.util.Comparator;
import java.util.Iterator;
import org.apache.commons.math.stat.Frequency;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class Frequency_ESTest extends Frequency_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test010() throws Throwable {
        Comparator<Integer> comparator0 = (Comparator<Integer>) mock(Comparator.class, new ViolatedAssumptionAnswer());
        doReturn(0, 0, 0, 0, 0).when(comparator0).compare(anyInt(), anyInt());
        Frequency frequency0 = new Frequency(comparator0);
        frequency0.addValue((-2007));
        frequency0.addValue((-2007));
        String string0 = frequency0.toString();
    }

    @Test(timeout = 4000)
    public void test031() throws Throwable {
        Frequency frequency0 = new Frequency();
        frequency0.addValue('l');
        double double0 = frequency0.getPct((Object) "");
    }

    @Test(timeout = 4000)
    public void test042() throws Throwable {
        Comparator<Integer> comparator0 = (Comparator<Integer>) mock(Comparator.class, new ViolatedAssumptionAnswer());
        doReturn(0, 0, 0, 0).when(comparator0).compare(anyInt(), anyInt());
        Frequency frequency0 = new Frequency(comparator0);
        frequency0.addValue(0);
        double double0 = frequency0.getPct((Object) "");
    }

    @Test(timeout = 4000)
    public void test053() throws Throwable {
        Frequency frequency0 = new Frequency();
        frequency0.addValue(0L);
        double double0 = frequency0.getPct((Comparable<?>) 33);
    }

    @Test(timeout = 4000)
    public void test064() throws Throwable {
        Frequency frequency0 = new Frequency();
        frequency0.addValue('');
        double double0;
        double0 = frequency0.getPct(796L);
        assertTrue(true ? double0 == 796L : true);
    }

    @Test(timeout = 4000)
    public void test075() throws Throwable {
        Comparator<Integer> comparator0 = (Comparator<Integer>) mock(Comparator.class, new ViolatedAssumptionAnswer());
        doReturn(0, 0).when(comparator0).compare(anyInt(), anyInt());
        Frequency frequency0 = new Frequency(comparator0);
        frequency0.addValue(0);
        double double0;
        double0 = frequency0.getPct(1L);
        assertTrue(true ? double0 == 1L : true);
    }

    @Test(timeout = 4000)
    public void test086() throws Throwable {
        Comparator<Frequency> comparator0 = (Comparator<Frequency>) mock(Comparator.class, new ViolatedAssumptionAnswer());
        doReturn(0, 0).when(comparator0).compare(any(org.apache.commons.math.stat.Frequency.class), any(org.apache.commons.math.stat.Frequency.class));
        Frequency frequency0 = new Frequency(comparator0);
        frequency0.addValue((long) (-797));
        double double0;
        double0 = frequency0.getPct((-797));
        assertTrue(true ? double0 == (-797) : true);
    }

    @Test(timeout = 4000)
    public void test097() throws Throwable {
        Frequency frequency0 = new Frequency();
        frequency0.addValue(770L);
        double double0;
        double0 = frequency0.getPct('Y');
        assertTrue(true ? double0 == 'Y' : true);
    }

    @Test(timeout = 4000)
    public void test108() throws Throwable {
        Comparator<Integer> comparator0 = (Comparator<Integer>) mock(Comparator.class, new ViolatedAssumptionAnswer());
        doReturn(0, 0).when(comparator0).compare(anyInt(), anyInt());
        Frequency frequency0 = new Frequency(comparator0);
        frequency0.addValue((-2007));
        double double0;
        double0 = frequency0.getPct(',');
        assertTrue(true ? double0 == ',' : true);
    }

    @Test(timeout = 4000)
    public void test119() throws Throwable {
        Frequency frequency0 = new Frequency();
        frequency0.addValue('a');
        Integer integer0 = new Integer(1961);
        double double0 = frequency0.getCumPct((Object) integer0);
    }

    @Test(timeout = 4000)
    public void test1210() throws Throwable {
        Frequency frequency0 = new Frequency();
        frequency0.addValue(1);
        Long long0 = Long.getLong("QiSON~eFIdi]WTwsEb", (-1388L));
        double double0 = frequency0.getCumPct((Comparable<?>) long0);
    }

    @Test(timeout = 4000)
    public void test1311() throws Throwable {
        Frequency frequency0 = new Frequency();
        Comparable<Object> comparable0 = (Comparable<Object>) mock(Comparable.class, new ViolatedAssumptionAnswer());
        doReturn(0).when(comparable0).compareTo(any());
        frequency0.addValue(comparable0);
        double double0;
        double0 = frequency0.getCumPct(2L);
        assertTrue(true ? double0 <= 2L : true);
    }

    @Test(timeout = 4000)
    public void test1412() throws Throwable {
        Comparator<Frequency> comparator0 = (Comparator<Frequency>) mock(Comparator.class, new ViolatedAssumptionAnswer());
        doReturn(0, 0, 0, 0).when(comparator0).compare(any(org.apache.commons.math.stat.Frequency.class), any(org.apache.commons.math.stat.Frequency.class));
        Frequency frequency0 = new Frequency(comparator0);
        frequency0.addValue((long) (-797));
        double double0;
        double0 = frequency0.getCumPct((long) (-797));
        assertTrue(true ? double0 <= (long) (-797) : true);
    }

    @Test(timeout = 4000)
    public void test1513() throws Throwable {
        Frequency frequency0 = new Frequency();
        frequency0.addValue(770L);
        double double0;
        double0 = frequency0.getCumPct((-2288));
        assertTrue(true ? double0 <= (-2288) : true);
    }

    @Test(timeout = 4000)
    public void test1614() throws Throwable {
        Comparator<Integer> comparator0 = (Comparator<Integer>) mock(Comparator.class, new ViolatedAssumptionAnswer());
        doReturn(0, 0, 0, 0).when(comparator0).compare(anyInt(), anyInt());
        Frequency frequency0 = new Frequency(comparator0);
        frequency0.addValue((-2715L));
        double double0;
        double0 = frequency0.getCumPct(520);
        assertTrue(true ? double0 <= 520 : true);
    }

    @Test(timeout = 4000)
    public void test1715() throws Throwable {
        Frequency frequency0 = new Frequency();
        frequency0.addValue(1);
        double double0;
        double0 = frequency0.getCumPct('+');
        assertTrue(true ? double0 <= '+' : true);
    }

    @Test(timeout = 4000)
    public void test1816() throws Throwable {
        Comparator<Frequency> comparator0 = (Comparator<Frequency>) mock(Comparator.class, new ViolatedAssumptionAnswer());
        doReturn(0, 0, 0, 0).when(comparator0).compare(any(org.apache.commons.math.stat.Frequency.class), any(org.apache.commons.math.stat.Frequency.class));
        Frequency frequency0 = new Frequency(comparator0);
        frequency0.addValue((long) (-797));
        double double0;
        double0 = frequency0.getCumPct('>');
        assertTrue(true ? double0 <= '>' : true);
    }

    @Test(timeout = 4000)
    public void test1917() throws Throwable {
        Comparator<String> comparator0 = (Comparator<String>) mock(Comparator.class, new ViolatedAssumptionAnswer());
        doReturn(0, 0, 0, 0).when(comparator0).compare(anyString(), anyString());
        Frequency frequency0 = new Frequency(comparator0);
        frequency0.addValue((Object) "[H0Ka");
        long long0 = frequency0.getCumFreq((Object) "[H0Ka");
    }

    @Test(timeout = 4000)
    public void test2018() throws Throwable {
        Frequency frequency0 = new Frequency();
        Integer integer0 = new Integer((-3471));
        frequency0.addValue(integer0);
        long long0;
        long0 = frequency0.getCumFreq(1);
        assertTrue(true ? long0 == 1 : true);
    }

    @Test(timeout = 4000)
    public void test2119() throws Throwable {
        Comparator<String> comparator0 = (Comparator<String>) mock(Comparator.class, new ViolatedAssumptionAnswer());
        doReturn(0, 0, 0, 0).when(comparator0).compare(anyString(), anyString());
        Frequency frequency0 = new Frequency(comparator0);
        frequency0.addValue((Object) "[H0Ka");
        long long0;
        long0 = frequency0.getCumFreq('/');
        assertTrue(true ? long0 == '/' : true);
    }

    @Test(timeout = 4000)
    public void test2220() throws Throwable {
        Frequency frequency0 = new Frequency();
        long long0 = frequency0.getCount((Object) "org.apace.ommons.math.MathRntimeException2");
    }

    @Test(timeout = 4000)
    public void test2321() throws Throwable {
        Comparator<Integer> comparator0 = (Comparator<Integer>) mock(Comparator.class, new ViolatedAssumptionAnswer());
        doReturn(0, 0).when(comparator0).compare(anyInt(), anyInt());
        Frequency frequency0 = new Frequency(comparator0);
        frequency0.addValue(0);
        long long0 = frequency0.getCount((Object) "");
    }

    @Test(timeout = 4000)
    public void test2422() throws Throwable {
        Comparator<Integer> comparator0 = (Comparator<Integer>) mock(Comparator.class, new ViolatedAssumptionAnswer());
        doReturn(0, 0).when(comparator0).compare(anyInt(), anyInt());
        Frequency frequency0 = new Frequency(comparator0);
        frequency0.addValue((-2007));
        long long0 = frequency0.getCount((long) (-2007));
    }

    @Test(timeout = 4000)
    public void test2523() throws Throwable {
        Comparator<Frequency> comparator0 = (Comparator<Frequency>) mock(Comparator.class, new ViolatedAssumptionAnswer());
        doReturn(0, 0).when(comparator0).compare(any(org.apache.commons.math.stat.Frequency.class), any(org.apache.commons.math.stat.Frequency.class));
        Frequency frequency0 = new Frequency(comparator0);
        frequency0.addValue((long) (-797));
        long long0 = frequency0.getCount(3306);
    }

    @Test(timeout = 4000)
    public void test2624() throws Throwable {
        Comparator<Integer> comparator0 = (Comparator<Integer>) mock(Comparator.class, new ViolatedAssumptionAnswer());
        doReturn(0, 0).when(comparator0).compare(anyInt(), anyInt());
        Frequency frequency0 = new Frequency(comparator0);
        frequency0.addValue((-2007));
        long long0 = frequency0.getCount('e');
    }

    @Test(timeout = 4000)
    public void test2725() throws Throwable {
        Frequency frequency0 = new Frequency();
        frequency0.addValue(9);
        frequency0.getPct((Object) null);
    }

    @Test(timeout = 4000)
    public void test2826() throws Throwable {
        Comparator<Frequency> comparator0 = (Comparator<Frequency>) mock(Comparator.class, new ViolatedAssumptionAnswer());
        Frequency frequency0 = new Frequency(comparator0);
        Object object0 = new Object();
        frequency0.getPct(object0);
    }

    @Test(timeout = 4000)
    public void test2927() throws Throwable {
        Frequency frequency0 = new Frequency();
        Integer integer0 = new Integer((-571));
        frequency0.addValue(integer0);
        frequency0.getPct((Comparable<?>) null);
    }

    @Test(timeout = 4000)
    public void test3028() throws Throwable {
        Frequency frequency0 = new Frequency();
        frequency0.addValue((Object) "Value \t Freq. \t Pct. \t Cum Pct. \n");
        frequency0.getCumPct((Object) null);
    }

    @Test(timeout = 4000)
    public void test3129() throws Throwable {
        Comparator<Object> comparator0 = (Comparator<Object>) mock(Comparator.class, new ViolatedAssumptionAnswer());
        Frequency frequency0 = new Frequency(comparator0);
        Iterator<Comparable<?>> iterator0 = frequency0.valuesIterator();
        frequency0.getCumPct((Object) iterator0);
    }

    @Test(timeout = 4000)
    public void test3230() throws Throwable {
        Frequency frequency0 = new Frequency();
        frequency0.addValue(13);
        frequency0.getCumPct((Comparable<?>) null);
    }

    @Test(timeout = 4000)
    public void test3331() throws Throwable {
        Frequency frequency0 = new Frequency();
        frequency0.addValue((-450));
        frequency0.getCumFreq((Object) null);
    }

    @Test(timeout = 4000)
    public void test3432() throws Throwable {
        Frequency frequency0 = new Frequency();
        Object object0 = new Object();
        frequency0.getCumFreq(object0);
    }

    @Test(timeout = 4000)
    public void test3533() throws Throwable {
        Frequency frequency0 = new Frequency();
        Comparable<Object> comparable0 = (Comparable<Object>) mock(Comparable.class, new ViolatedAssumptionAnswer());
        doReturn(0).when(comparable0).compareTo(any());
        frequency0.addValue(comparable0);
        frequency0.getCumFreq((Comparable<?>) null);
    }

    @Test(timeout = 4000)
    public void test3634() throws Throwable {
        Frequency frequency0 = new Frequency();
        Comparable<Object> comparable0 = (Comparable<Object>) mock(Comparable.class, new ViolatedAssumptionAnswer());
        doReturn(1431655765, 1431655765).when(comparable0).compareTo(any());
        Integer integer0 = new Integer((-8));
        frequency0.addValue(integer0);
        frequency0.addValue(comparable0);
        long default0;
        default0 = frequency0.getCumFreq((-8));
        assertTrue(true ? default0 == (-8) : true);
    }

    @Test(timeout = 4000)
    public void test3735() throws Throwable {
        Frequency frequency0 = new Frequency();
        frequency0.getCount((Object) null);
    }

    @Test(timeout = 4000)
    public void test3836() throws Throwable {
        Frequency frequency0 = new Frequency();
        frequency0.getCount((Comparable<?>) null);
    }

    @Test(timeout = 4000)
    public void test3937() throws Throwable {
        Comparator<Integer> comparator0 = (Comparator<Integer>) mock(Comparator.class, new ViolatedAssumptionAnswer());
        Frequency frequency0 = new Frequency(comparator0);
        frequency0.addValue((Object) null);
    }

    @Test(timeout = 4000)
    public void test4038() throws Throwable {
        Frequency frequency0 = new Frequency();
        frequency0.addValue((Object) "Value \t Freq. \t Pct. \t Cum Pct. \n");
        Integer integer0 = new Integer(237);
        frequency0.addValue(integer0);
    }

    @Test(timeout = 4000)
    public void test4139() throws Throwable {
        Comparator<Integer> comparator0 = (Comparator<Integer>) mock(Comparator.class, new ViolatedAssumptionAnswer());
        Frequency frequency0 = new Frequency(comparator0);
        frequency0.addValue((Integer) null);
    }

    @Test(timeout = 4000)
    public void test4240() throws Throwable {
        Frequency frequency0 = new Frequency();
        frequency0.addValue('a');
        Integer integer0 = new Integer(1961);
        frequency0.addValue((Comparable<?>) integer0);
    }

    @Test(timeout = 4000)
    public void test4341() throws Throwable {
        Frequency frequency0 = new Frequency();
        frequency0.addValue((Comparable<?>) null);
    }

    @Test(timeout = 4000)
    public void test4442() throws Throwable {
        Frequency frequency0 = new Frequency();
        frequency0.addValue('\'');
        frequency0.addValue((-148));
    }

    @Test(timeout = 4000)
    public void test4543() throws Throwable {
        Frequency frequency0 = new Frequency();
        frequency0.addValue(770L);
        frequency0.addValue('Y');
    }

    @Test(timeout = 4000)
    public void test4644() throws Throwable {
        Comparator<Integer> comparator0 = (Comparator<Integer>) mock(Comparator.class, new ViolatedAssumptionAnswer());
        doReturn(0, 0, 0, 0).when(comparator0).compare(anyInt(), anyInt());
        Frequency frequency0 = new Frequency(comparator0);
        frequency0.addValue((-2007));
        double double0 = frequency0.getCumPct((Comparable<?>) "Value \t Freq. \t Pct. \t Cum Pct. \n-2007\t1\t100%\t100%\n");
    }

    @Test(timeout = 4000)
    public void test4745() throws Throwable {
        Comparator<Integer> comparator0 = (Comparator<Integer>) mock(Comparator.class, new ViolatedAssumptionAnswer());
        Frequency frequency0 = new Frequency(comparator0);
        Comparable<Frequency> comparable0 = (Comparable<Frequency>) mock(Comparable.class, new ViolatedAssumptionAnswer());
        double double0 = frequency0.getCumPct(comparable0);
    }

    @Test(timeout = 4000)
    public void test4846() throws Throwable {
        Frequency frequency0 = new Frequency();
        frequency0.addValue(1);
        Comparable<Object> comparable0 = (Comparable<Object>) mock(Comparable.class, new ViolatedAssumptionAnswer());
        doReturn(1431655765, 1, (-594), 0).when(comparable0).compareTo(any());
        long long0 = frequency0.getCumFreq(comparable0);
    }

    @Test(timeout = 4000)
    public void test4947() throws Throwable {
        Frequency frequency0 = new Frequency();
        frequency0.addValue(1);
        Comparable<Object> comparable0 = (Comparable<Object>) mock(Comparable.class, new ViolatedAssumptionAnswer());
        doReturn(1610612736, (-687)).when(comparable0).compareTo(any());
        long long0 = frequency0.getCumFreq(comparable0);
    }

    @Test(timeout = 4000)
    public void test5048() throws Throwable {
        Comparator<Frequency> comparator0 = (Comparator<Frequency>) mock(Comparator.class, new ViolatedAssumptionAnswer());
        doReturn(0, 0, 0, 0).when(comparator0).compare(any(org.apache.commons.math.stat.Frequency.class), any(org.apache.commons.math.stat.Frequency.class));
        Frequency frequency0 = new Frequency(comparator0);
        Long long0 = new Long((-875L));
        frequency0.addValue('0');
        long long1 = frequency0.getCumFreq((Comparable<?>) long0);
    }

    @Test(timeout = 4000)
    public void test5149() throws Throwable {
        Comparator<Integer> comparator0 = (Comparator<Integer>) mock(Comparator.class, new ViolatedAssumptionAnswer());
        doReturn(0, 0, 0, 0).when(comparator0).compare(anyInt(), anyInt());
        Frequency frequency0 = new Frequency(comparator0);
        frequency0.addValue((-2007));
        Integer integer0 = new Integer((-617));
        long long0 = frequency0.getCumFreq((Comparable<?>) integer0);
    }

    @Test(timeout = 4000)
    public void test5250() throws Throwable {
        Frequency frequency0 = new Frequency();
        long long0 = frequency0.getCumFreq((Comparable<?>) "Value \t Freq. \t Pct. \t Cum Pct. \n");
    }

    @Test(timeout = 4000)
    public void test5351() throws Throwable {
        Frequency frequency0 = new Frequency();
        double double0 = frequency0.getPct((Comparable<?>) "Value \t Freq. \t Pct. \t Cum Pct. \n");
    }

    @Test(timeout = 4000)
    public void test5452() throws Throwable {
        Frequency frequency0 = new Frequency();
        frequency0.addValue((Object) "Value \t Freq. \t Pct. \t Cum Pct. \n");
        double double0 = frequency0.getPct((Comparable<?>) "Value \t Freq. \t Pct. \t Cum Pct. \n");
    }

    @Test(timeout = 4000)
    public void test5553() throws Throwable {
        Frequency frequency0 = new Frequency();
        Comparable<Object> comparable0 = (Comparable<Object>) mock(Comparable.class, new ViolatedAssumptionAnswer());
        doReturn(0).when(comparable0).compareTo(any());
        frequency0.addValue(comparable0);
        Comparable<Object> comparable1 = (Comparable<Object>) mock(Comparable.class, new ViolatedAssumptionAnswer());
        doReturn(0).when(comparable1).compareTo(any());
        long long0 = frequency0.getCount(comparable1);
    }

    @Test(timeout = 4000)
    public void test5654() throws Throwable {
        Frequency frequency0 = new Frequency();
        long long0 = frequency0.getCount((Comparable<?>) "Value \t Freq. \t Pct. \t Cum Pct. \n");
    }

    @Test(timeout = 4000)
    public void test5755() throws Throwable {
        Frequency frequency0 = new Frequency();
        frequency0.addValue('a');
        long long0 = frequency0.getSumFreq();
    }

    @Test(timeout = 4000)
    public void test5856() throws Throwable {
        Frequency frequency0 = new Frequency();
        long long0 = frequency0.getSumFreq();
    }

    @Test(timeout = 4000)
    public void test6057() throws Throwable {
        Comparator<Integer> comparator0 = (Comparator<Integer>) mock(Comparator.class, new ViolatedAssumptionAnswer());
        Frequency frequency0 = new Frequency(comparator0);
        long long0 = frequency0.getCount((long) (-2007));
    }

    @Test(timeout = 4000)
    public void test6158() throws Throwable {
        Frequency frequency0 = new Frequency();
        frequency0.addValue((Object) "instance of class {0} not comparableto exiting values");
        Frequency frequency1 = new Frequency();
        boolean boolean0 = frequency0.equals(frequency1);
    }

    @Test(timeout = 4000)
    public void test6259() throws Throwable {
        Frequency frequency0 = new Frequency();
        Frequency frequency1 = new Frequency();
        boolean boolean0 = frequency0.equals(frequency1);
    }

    @Test(timeout = 4000)
    public void test6360() throws Throwable {
        Frequency frequency0 = new Frequency();
        boolean boolean0 = frequency0.equals((Object) null);
    }

    @Test(timeout = 4000)
    public void test6461() throws Throwable {
        Frequency frequency0 = new Frequency();
        boolean boolean0 = frequency0.equals(frequency0);
    }

    @Test(timeout = 4000)
    public void test6562() throws Throwable {
        Frequency frequency0 = new Frequency();
        boolean boolean0 = frequency0.equals("Value \t Freq. \t Pct. \t Cum Pct. \n");
    }

    @Test(timeout = 4000)
    public void test6663() throws Throwable {
        Frequency frequency0 = new Frequency();
        Integer integer0 = new Integer((-571));
        frequency0.addValue(integer0);
        frequency0.addValue(9);
        long long0;
        long0 = frequency0.getCumFreq(1L);
        assertTrue(true ? long0 == 1L : true);
    }

    @Test(timeout = 4000)
    public void test6764() throws Throwable {
        Frequency frequency0 = new Frequency();
        frequency0.addValue(9);
        long long0;
        long0 = frequency0.getCumFreq(1L);
        assertTrue(true ? long0 == 1L : true);
    }

    @Test(timeout = 4000)
    public void test6865() throws Throwable {
        Comparator<String> comparator0 = (Comparator<String>) mock(Comparator.class, new ViolatedAssumptionAnswer());
        doReturn(10, 10, 10, (-872), 10).when(comparator0).compare(anyString(), anyString());
        Frequency frequency0 = new Frequency(comparator0);
        frequency0.addValue((Comparable<?>) "Value \t Freq. \t Pct. \t Cum Pct. \n10\t1\t100%\t100%\n");
        long long0 = frequency0.getCumFreq((Object) "Value \t Freq. \t Pct. \t Cum Pct. \n10\t1\t100%\t100%\n");
    }

    @Test(timeout = 4000)
    public void test7066() throws Throwable {
        Comparator<Object> comparator0 = (Comparator<Object>) mock(Comparator.class, new ViolatedAssumptionAnswer());
        Frequency frequency0 = new Frequency(comparator0);
        frequency0.addValue((Object) frequency0);
    }

    @Test(timeout = 4000)
    public void test7167() throws Throwable {
        Comparator<Integer> comparator0 = (Comparator<Integer>) mock(Comparator.class, new ViolatedAssumptionAnswer());
        Frequency frequency0 = new Frequency(comparator0);
        Integer integer0 = new Integer((-617));
        long long0 = frequency0.getCount((Comparable<?>) integer0);
    }

    @Test(timeout = 4000)
    public void test7268() throws Throwable {
        Frequency frequency0 = new Frequency();
        Integer integer0 = new Integer((-542));
        frequency0.addValue(integer0);
        double double0;
        double0 = frequency0.getPct(0);
        assertTrue(true ? double0 == 0 : true);
    }

    @Test(timeout = 4000)
    public void test7369() throws Throwable {
        Comparator<Integer> comparator0 = (Comparator<Integer>) mock(Comparator.class, new ViolatedAssumptionAnswer());
        Frequency frequency0 = new Frequency(comparator0);
        long long0 = frequency0.getCount((-2007));
    }

    @Test(timeout = 4000)
    public void test7470() throws Throwable {
        Comparator<Integer> comparator0 = (Comparator<Integer>) mock(Comparator.class, new ViolatedAssumptionAnswer());
        Frequency frequency0 = new Frequency(comparator0);
        Iterator<Comparable<?>> iterator0 = frequency0.valuesIterator();
        frequency0.getCount((Object) iterator0);
    }

    @Test(timeout = 4000)
    public void test7571() throws Throwable {
        Frequency frequency0 = new Frequency();
        frequency0.addValue('#');
        frequency0.addValue(69L);
    }

    @Test(timeout = 4000)
    public void test7672() throws Throwable {
        Frequency frequency0 = new Frequency();
        long long0;
        long0 = frequency0.getCumFreq(':');
        assertTrue(true ? long0 == ':' : true);
    }

    @Test(timeout = 4000)
    public void test7773() throws Throwable {
        Frequency frequency0 = new Frequency();
        long long0 = frequency0.getCount('b');
    }

    @Test(timeout = 4000)
    public void test7874() throws Throwable {
        Comparator<Integer> comparator0 = (Comparator<Integer>) mock(Comparator.class, new ViolatedAssumptionAnswer());
        doReturn(0, 0, 0, 0).when(comparator0).compare(anyInt(), anyInt());
        Frequency frequency0 = new Frequency(comparator0);
        frequency0.addValue((-2007));
        Integer integer0 = new Integer((-617));
        double double0 = frequency0.getCumPct((Object) integer0);
    }

    @Test(timeout = 4000)
    public void test8075() throws Throwable {
        Frequency frequency0 = new Frequency();
        long long0;
        long0 = frequency0.getCumFreq((-8));
        assertTrue(true ? long0 == (-8) : true);
    }

    @Test(timeout = 4000)
    public void test8176() throws Throwable {
        Frequency frequency0 = new Frequency();
        frequency0.addValue(1);
        Comparable<Object> comparable0 = (Comparable<Object>) mock(Comparable.class, new ViolatedAssumptionAnswer());
        doReturn(1, 582, (-1234), 73).when(comparable0).compareTo(any());
        long long0 = frequency0.getCumFreq(comparable0);
    }
}
