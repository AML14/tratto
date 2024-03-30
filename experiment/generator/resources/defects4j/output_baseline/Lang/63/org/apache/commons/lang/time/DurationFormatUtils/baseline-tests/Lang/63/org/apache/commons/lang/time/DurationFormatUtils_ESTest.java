/*
 * This file was automatically generated by EvoSuite
 * Sun Dec 31 04:59:46 GMT 2023
 */
package org.apache.commons.lang.time;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.TimeZone;
import org.apache.commons.lang.time.DurationFormatUtils;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.mock.java.util.MockGregorianCalendar;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class DurationFormatUtils_ESTest extends DurationFormatUtils_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        DurationFormatUtils.Token durationFormatUtils_Token0 = new DurationFormatUtils.Token("s", 886);
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        TimeZone timeZone0 = TimeZone.getDefault();
        String string0 = DurationFormatUtils.formatPeriod((-553L), (-553L), "0java.lang.StringBuffer@00000000090java.lang.StringBuffer@00000000100java.lang.StringBuffer@00000000110java.lang.StringBuffer@0000000012", false, timeZone0);
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        DurationFormatUtils.Token durationFormatUtils_Token0 = new DurationFormatUtils.Token("M");
        String string0 = durationFormatUtils_Token0.toString();
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        String string0 = DurationFormatUtils.formatDurationISO((-712L));
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        String string0 = DurationFormatUtils.formatPeriodISO((-3266L), 2419200527L);
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        DurationFormatUtils durationFormatUtils0 = new DurationFormatUtils();
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        String string0 = DurationFormatUtils.formatDurationHMS((-712L));
    }

    @Test(timeout = 4000)
    public void test077() throws Throwable {
        String string0 = DurationFormatUtils.formatPeriod(297L, 297L, "Jw!");
    }

    @Test(timeout = 4000)
    public void test088() throws Throwable {
        String string0 = DurationFormatUtils.formatDurationWords((-553L), false, false);
    }

    @Test(timeout = 4000)
    public void test099() throws Throwable {
        String string0 = DurationFormatUtils.formatDurationWords(0L, true, true);
    }

    @Test(timeout = 4000)
    public void test1010() throws Throwable {
        TimeZone timeZone0 = TimeZone.getTimeZone(")t{v^g");
        String string0 = DurationFormatUtils.formatPeriod((-1989L), 2419199989L, ")t{v^g", true, timeZone0);
    }

    @Test(timeout = 4000)
    public void test1111() throws Throwable {
        TimeZone timeZone0 = TimeZone.getDefault();
        String string0 = DurationFormatUtils.formatPeriod((-2278L), 2419200033L, "Minimum abbreviation width wih offset is 7", false, timeZone0);
    }

    @Test(timeout = 4000)
    public void test1212() throws Throwable {
        String string0 = DurationFormatUtils.formatDuration(11286L, "'P'yyyy'Y'M'M'd'DT'H'H'm'M's.S'S'");
    }

    @Test(timeout = 4000)
    public void test1313() throws Throwable {
        Object object0 = new Object();
        DurationFormatUtils.Token durationFormatUtils_Token0 = new DurationFormatUtils.Token(object0);
        DurationFormatUtils.Token[] durationFormatUtils_TokenArray0 = new DurationFormatUtils.Token[1];
        durationFormatUtils_TokenArray0[0] = durationFormatUtils_Token0;
        String string0 = DurationFormatUtils.format(durationFormatUtils_TokenArray0, 0, 0, 0, 0, 0, 0, 0, false);
    }

    @Test(timeout = 4000)
    public void test1414() throws Throwable {
        String string0 = DurationFormatUtils.formatDuration(1000L, "FWX)(MS>cJ}");
    }

    @Test(timeout = 4000)
    public void test1515() throws Throwable {
        MockGregorianCalendar mockGregorianCalendar0 = new MockGregorianCalendar();
        mockGregorianCalendar0.toString();
    }

    @Test(timeout = 4000)
    public void test1616() throws Throwable {
        DurationFormatUtils.Token durationFormatUtils_Token0 = new DurationFormatUtils.Token("M");
        boolean boolean0 = durationFormatUtils_Token0.equals("M");
    }

    @Test(timeout = 4000)
    public void test1717() throws Throwable {
        DurationFormatUtils.Token durationFormatUtils_Token0 = new DurationFormatUtils.Token("java.lang.StringBuffer@00000000160java.lang.StringBuffer@00000000170java.lang.StringBuffer@00000000180java.lang.StringBuffer@00000000190java.lang.StringBuffer@00000000200java.lang.StringBuffer@00000000210java.lang.StringBuffer@0000000022000java.lang.StringBuffer@0000000023");
        boolean boolean0 = durationFormatUtils_Token0.equals(durationFormatUtils_Token0);
    }

    @Test(timeout = 4000)
    public void test1818() throws Throwable {
        DurationFormatUtils.Token durationFormatUtils_Token0 = new DurationFormatUtils.Token("java.lang.StringBuffer@00000000160java.lang.StringBuffer@00000000170java.lang.StringBuffer@00000000180java.lang.StringBuffer@00000000190java.lang.StringBuffer@00000000200java.lang.StringBuffer@00000000210java.lang.StringBuffer@0000000022000java.lang.StringBuffer@0000000023");
        DurationFormatUtils.Token durationFormatUtils_Token1 = new DurationFormatUtils.Token(durationFormatUtils_Token0);
        boolean boolean0 = durationFormatUtils_Token1.equals(durationFormatUtils_Token0);
    }

    @Test(timeout = 4000)
    public void test1919() throws Throwable {
        DurationFormatUtils.Token durationFormatUtils_Token0 = new DurationFormatUtils.Token("0java.lang.StringBuffer@000000000800java.lang.StringBuffer@000000000900java.lang.StringBuffer@000000001088");
        durationFormatUtils_Token0.increment();
        DurationFormatUtils.Token durationFormatUtils_Token1 = new DurationFormatUtils.Token("0java.lang.StringBuffer@000000000800java.lang.StringBuffer@000000000900java.lang.StringBuffer@000000001088");
        boolean boolean0 = durationFormatUtils_Token0.equals(durationFormatUtils_Token1);
    }

    @Test(timeout = 4000)
    public void test2020() throws Throwable {
        StringBuffer stringBuffer0 = new StringBuffer();
        DurationFormatUtils.Token durationFormatUtils_Token0 = new DurationFormatUtils.Token(stringBuffer0);
        boolean boolean0 = durationFormatUtils_Token0.equals(durationFormatUtils_Token0);
    }

    @Test(timeout = 4000)
    public void test2121() throws Throwable {
        Long long0 = new Long((-743L));
        DurationFormatUtils.Token durationFormatUtils_Token0 = new DurationFormatUtils.Token(long0);
        boolean boolean0 = durationFormatUtils_Token0.equals(durationFormatUtils_Token0);
    }

    @Test(timeout = 4000)
    public void test2222() throws Throwable {
        DurationFormatUtils.Token durationFormatUtils_Token0 = new DurationFormatUtils.Token("java.lang.StringBuffer@00000000160java.lang.StringBuffer@00000000170java.lang.StringBuffer@00000000180java.lang.StringBuffer@00000000190java.lang.StringBuffer@00000000200java.lang.StringBuffer@00000000210java.lang.StringBuffer@000000002288java.lang.StringBuffer@0000000023");
        DurationFormatUtils.Token durationFormatUtils_Token1 = new DurationFormatUtils.Token("java.lang.StringBuffer@00000000400java.lang.StringBuffer@00000000410java.lang.StringBuffer@00000000420java.lang.StringBuffer@00000000430java.lang.StringBuffer@00000000440java.lang.StringBuffer@00000000450java.lang.StringBuffer@000000004688java.lang.StringBuffer@0000000047");
        boolean boolean0 = durationFormatUtils_Token0.equals(durationFormatUtils_Token1);
    }
}
