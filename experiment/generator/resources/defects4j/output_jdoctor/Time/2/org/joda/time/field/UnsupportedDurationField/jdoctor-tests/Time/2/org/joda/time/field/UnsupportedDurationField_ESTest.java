/*
 * This file was automatically generated by EvoSuite
 * Tue Oct 17 00:32:07 GMT 2023
 */
package org.joda.time.field;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;
import org.joda.time.Seconds;
import org.joda.time.field.UnsupportedDurationField;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class UnsupportedDurationField_ESTest extends UnsupportedDurationField_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        UnsupportedDurationField unsupportedDurationField0 = UnsupportedDurationField.getInstance((DurationFieldType) null);
        DurationFieldType durationFieldType0 = unsupportedDurationField0.getType();
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        DurationFieldType durationFieldType0 = DurationFieldType.centuries();
        UnsupportedDurationField unsupportedDurationField0 = UnsupportedDurationField.getInstance(durationFieldType0);
        String string0 = unsupportedDurationField0.getName();
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        UnsupportedDurationField unsupportedDurationField0 = UnsupportedDurationField.getInstance((DurationFieldType) null);
        unsupportedDurationField0.toString();
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        UnsupportedDurationField unsupportedDurationField0 = UnsupportedDurationField.getInstance((DurationFieldType) null);
        unsupportedDurationField0.hashCode();
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        DurationFieldType durationFieldType0 = DurationFieldType.hours();
        UnsupportedDurationField.getInstance(durationFieldType0);
        UnsupportedDurationField unsupportedDurationField0 = UnsupportedDurationField.getInstance(durationFieldType0);
        boolean default0;
        default0 = unsupportedDurationField0.isPrecise();
        assertTrue(true ? default0 == true : true);
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        DurationFieldType durationFieldType0 = DurationFieldType.halfdays();
        UnsupportedDurationField unsupportedDurationField0 = UnsupportedDurationField.getInstance(durationFieldType0);
        boolean boolean0;
        boolean0 = unsupportedDurationField0.isSupported();
        assertTrue(true ? boolean0 == false : true);
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        DurationFieldType durationFieldType0 = DurationFieldType.years();
        UnsupportedDurationField unsupportedDurationField0 = UnsupportedDurationField.getInstance(durationFieldType0);
        long long0 = unsupportedDurationField0.getUnitMillis();
    }

    @Test(timeout = 4000)
    public void test077() throws Throwable {
        UnsupportedDurationField unsupportedDurationField0 = UnsupportedDurationField.getInstance((DurationFieldType) null);
        unsupportedDurationField0.getName();
    }

    @Test(timeout = 4000)
    public void test088() throws Throwable {
        DurationFieldType durationFieldType0 = DurationFieldType.halfdays();
        UnsupportedDurationField unsupportedDurationField0 = UnsupportedDurationField.getInstance((DurationFieldType) null);
        UnsupportedDurationField unsupportedDurationField1 = UnsupportedDurationField.getInstance(durationFieldType0);
        unsupportedDurationField0.equals(unsupportedDurationField1);
    }

    @Test(timeout = 4000)
    public void test099() throws Throwable {
        UnsupportedDurationField unsupportedDurationField0 = UnsupportedDurationField.getInstance((DurationFieldType) null);
        boolean boolean0 = unsupportedDurationField0.equals(unsupportedDurationField0);
    }

    @Test(timeout = 4000)
    public void test1010() throws Throwable {
        DurationFieldType durationFieldType0 = DurationFieldType.years();
        UnsupportedDurationField unsupportedDurationField0 = UnsupportedDurationField.getInstance(durationFieldType0);
        boolean boolean0 = unsupportedDurationField0.equals(durationFieldType0);
    }

    @Test(timeout = 4000)
    public void test1111() throws Throwable {
        DurationFieldType durationFieldType0 = DurationFieldType.weeks();
        UnsupportedDurationField unsupportedDurationField0 = UnsupportedDurationField.getInstance(durationFieldType0);
        String string0 = unsupportedDurationField0.toString();
    }

    @Test(timeout = 4000)
    public void test1212() throws Throwable {
        DurationFieldType durationFieldType0 = DurationFieldType.centuries();
        UnsupportedDurationField unsupportedDurationField0 = UnsupportedDurationField.getInstance(durationFieldType0);
        unsupportedDurationField0.getValueAsLong(0L, (-247L));
    }

    @Test(timeout = 4000)
    public void test1313() throws Throwable {
        DurationFieldType durationFieldType0 = DurationFieldType.millis();
        UnsupportedDurationField unsupportedDurationField0 = UnsupportedDurationField.getInstance(durationFieldType0);
        unsupportedDurationField0.getMillis((int) (byte) 1);
    }

    @Test(timeout = 4000)
    public void test1414() throws Throwable {
        DurationFieldType durationFieldType0 = DurationFieldType.hours();
        UnsupportedDurationField unsupportedDurationField0 = UnsupportedDurationField.getInstance(durationFieldType0);
        unsupportedDurationField0.getMillis(0L);
    }

    @Test(timeout = 4000)
    public void test1515() throws Throwable {
        DurationFieldType durationFieldType0 = DurationFieldType.hours();
        UnsupportedDurationField unsupportedDurationField0 = UnsupportedDurationField.getInstance(durationFieldType0);
        boolean boolean0;
        boolean0 = unsupportedDurationField0.isPrecise();
        assertTrue(true ? boolean0 == true : true);
    }

    @Test(timeout = 4000)
    public void test1616() throws Throwable {
        UnsupportedDurationField unsupportedDurationField0 = UnsupportedDurationField.getInstance((DurationFieldType) null);
        unsupportedDurationField0.getValue(2785L, 2785L);
    }

    @Test(timeout = 4000)
    public void test1717() throws Throwable {
        DurationFieldType durationFieldType0 = DurationFieldType.centuries();
        UnsupportedDurationField unsupportedDurationField0 = UnsupportedDurationField.getInstance(durationFieldType0);
        DurationFieldType durationFieldType1 = unsupportedDurationField0.getType();
    }

    @Test(timeout = 4000)
    public void test1818() throws Throwable {
        UnsupportedDurationField unsupportedDurationField0 = UnsupportedDurationField.getInstance((DurationFieldType) null);
        unsupportedDurationField0.getDifference(15778800000L, 15778800000L);
    }

    @Test(timeout = 4000)
    public void test1919() throws Throwable {
        DurationFieldType durationFieldType0 = DurationFieldType.weeks();
        UnsupportedDurationField unsupportedDurationField0 = UnsupportedDurationField.getInstance(durationFieldType0);
        unsupportedDurationField0.hashCode();
    }

    @Test(timeout = 4000)
    public void test2020() throws Throwable {
        DurationFieldType durationFieldType0 = DurationFieldType.years();
        UnsupportedDurationField unsupportedDurationField0 = UnsupportedDurationField.getInstance(durationFieldType0);
    }

    @Test(timeout = 4000)
    public void test2021() throws Throwable {
        DurationFieldType durationFieldType0 = DurationFieldType.years();
        UnsupportedDurationField unsupportedDurationField0 = UnsupportedDurationField.getInstance(durationFieldType0);
        int int0 = unsupportedDurationField0.compareTo((DurationField) unsupportedDurationField0);
    }

    @Test(timeout = 4000)
    public void test2122() throws Throwable {
        DurationFieldType durationFieldType0 = DurationFieldType.weeks();
        UnsupportedDurationField unsupportedDurationField0 = UnsupportedDurationField.getInstance(durationFieldType0);
        unsupportedDurationField0.getMillis((long) 1, (long) 1);
    }

    @Test(timeout = 4000)
    public void test2223() throws Throwable {
        DurationFieldType durationFieldType0 = DurationFieldType.seconds();
        UnsupportedDurationField unsupportedDurationField0 = UnsupportedDurationField.getInstance(durationFieldType0);
        unsupportedDurationField0.getValueAsLong(1878L);
    }

    @Test(timeout = 4000)
    public void test2324() throws Throwable {
        DurationFieldType durationFieldType0 = DurationFieldType.years();
        UnsupportedDurationField unsupportedDurationField0 = UnsupportedDurationField.getInstance(durationFieldType0);
        unsupportedDurationField0.getValue((long) 0);
    }

    @Test(timeout = 4000)
    public void test2425() throws Throwable {
        Seconds seconds0 = Seconds.ONE;
        DurationFieldType durationFieldType0 = seconds0.getFieldType();
        UnsupportedDurationField unsupportedDurationField0 = UnsupportedDurationField.getInstance(durationFieldType0);
        unsupportedDurationField0.getDifferenceAsLong(2789L, 2789L);
    }

    @Test(timeout = 4000)
    public void test2526() throws Throwable {
        DurationFieldType durationFieldType0 = DurationFieldType.minutes();
        UnsupportedDurationField unsupportedDurationField0 = UnsupportedDurationField.getInstance(durationFieldType0);
        unsupportedDurationField0.add(2629800000L, (-1));
    }

    @Test(timeout = 4000)
    public void test2627() throws Throwable {
        DurationFieldType durationFieldType0 = DurationFieldType.centuries();
        UnsupportedDurationField unsupportedDurationField0 = UnsupportedDurationField.getInstance(durationFieldType0);
        unsupportedDurationField0.add(45L, 45L);
    }

    @Test(timeout = 4000)
    public void test2728() throws Throwable {
        DurationFieldType durationFieldType0 = DurationFieldType.weeks();
        UnsupportedDurationField unsupportedDurationField0 = UnsupportedDurationField.getInstance(durationFieldType0);
        unsupportedDurationField0.getMillis(1881, 0L);
    }
}
