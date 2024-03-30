/*
 * This file was automatically generated by EvoSuite
 * Tue Oct 17 02:00:24 GMT 2023
 */
package org.joda.time.chrono;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.util.TimeZone;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.mock.java.util.MockDate;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeZone;
import org.joda.time.DurationField;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.MonthDay;
import org.joda.time.Months;
import org.joda.time.Partial;
import org.joda.time.ReadablePartial;
import org.joda.time.chrono.BasicChronology;
import org.joda.time.chrono.BasicMonthOfYearDateTimeField;
import org.joda.time.chrono.BuddhistChronology;
import org.joda.time.chrono.CopticChronology;
import org.joda.time.chrono.EthiopicChronology;
import org.joda.time.chrono.GJChronology;
import org.joda.time.chrono.GJMonthOfYearDateTimeField;
import org.joda.time.chrono.GregorianChronology;
import org.joda.time.chrono.IslamicChronology;
import org.joda.time.chrono.JulianChronology;
import org.joda.time.tz.FixedDateTimeZone;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class BasicMonthOfYearDateTimeField_ESTest extends BasicMonthOfYearDateTimeField_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        CopticChronology copticChronology0 = CopticChronology.getInstanceUTC();
        BasicMonthOfYearDateTimeField basicMonthOfYearDateTimeField0 = new BasicMonthOfYearDateTimeField(copticChronology0, 1);
        int int0 = basicMonthOfYearDateTimeField0.getLeapAmount(10000000000000L);
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        GregorianChronology gregorianChronology0 = GregorianChronology.getInstance();
        BasicMonthOfYearDateTimeField basicMonthOfYearDateTimeField0 = new BasicMonthOfYearDateTimeField(gregorianChronology0, 292272992);
        long long0 = basicMonthOfYearDateTimeField0.getDifferenceAsLong((byte) 63, 768603731673597461L);
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        CopticChronology copticChronology0 = CopticChronology.getInstanceUTC();
        GJMonthOfYearDateTimeField gJMonthOfYearDateTimeField0 = new GJMonthOfYearDateTimeField(copticChronology0);
        int int0 = gJMonthOfYearDateTimeField0.getDifference((-51322291200000L), (-85L));
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis(1);
        GregorianChronology gregorianChronology0 = GregorianChronology.getInstance(dateTimeZone0);
        GJMonthOfYearDateTimeField gJMonthOfYearDateTimeField0 = new GJMonthOfYearDateTimeField(gregorianChronology0);
        LocalDateTime localDateTime0 = LocalDateTime.now(dateTimeZone0);
        int[] intArray0 = new int[8];
        gJMonthOfYearDateTimeField0.add((ReadablePartial) localDateTime0, 1, intArray0, 1);
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        JulianChronology julianChronology0 = JulianChronology.getInstanceUTC();
        GJMonthOfYearDateTimeField gJMonthOfYearDateTimeField0 = new GJMonthOfYearDateTimeField(julianChronology0);
        gJMonthOfYearDateTimeField0.add(1555201218L, (-9223372036854775808L));
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        Months months0 = Months.MAX_VALUE;
        LocalDate localDate0 = LocalDate.now();
        localDate0.plus(months0);
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis(1);
        LocalDateTime localDateTime0 = LocalDateTime.now(dateTimeZone0);
        LocalDateTime localDateTime1 = localDateTime0.minusMonths(1);
    }

    @Test(timeout = 4000)
    public void test077() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis(1);
        GregorianChronology gregorianChronology0 = GregorianChronology.getInstance(dateTimeZone0);
        GJMonthOfYearDateTimeField gJMonthOfYearDateTimeField0 = new GJMonthOfYearDateTimeField(gregorianChronology0);
        long long0 = gJMonthOfYearDateTimeField0.set(0L, 1);
    }

    @Test(timeout = 4000)
    public void test088() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.getDefault();
        GregorianChronology gregorianChronology0 = GregorianChronology.getInstance(dateTimeZone0);
        GJMonthOfYearDateTimeField gJMonthOfYearDateTimeField0 = new GJMonthOfYearDateTimeField(gregorianChronology0);
        long long0 = gJMonthOfYearDateTimeField0.roundFloor(0L);
    }

    @Test(timeout = 4000)
    public void test099() throws Throwable {
        JulianChronology julianChronology0 = JulianChronology.getInstanceUTC();
        BasicMonthOfYearDateTimeField basicMonthOfYearDateTimeField0 = new BasicMonthOfYearDateTimeField(julianChronology0, 93);
        long long0 = basicMonthOfYearDateTimeField0.roundFloor(1555200012L);
    }

    @Test(timeout = 4000)
    public void test1010() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis(108);
        GregorianChronology gregorianChronology0 = GregorianChronology.getInstance(dateTimeZone0);
        BasicMonthOfYearDateTimeField basicMonthOfYearDateTimeField0 = new BasicMonthOfYearDateTimeField(gregorianChronology0, 1519);
        long long0 = basicMonthOfYearDateTimeField0.remainder(0L);
    }

    @Test(timeout = 4000)
    public void test1111() throws Throwable {
        IslamicChronology islamicChronology0 = IslamicChronology.getInstance();
        BasicMonthOfYearDateTimeField basicMonthOfYearDateTimeField0 = new BasicMonthOfYearDateTimeField(islamicChronology0, 1);
        long long0 = basicMonthOfYearDateTimeField0.remainder((-59231164377600000L));
    }

    @Test(timeout = 4000)
    public void test1212() throws Throwable {
        CopticChronology copticChronology0 = CopticChronology.getInstanceUTC();
        GJMonthOfYearDateTimeField gJMonthOfYearDateTimeField0 = new GJMonthOfYearDateTimeField(copticChronology0);
        long long0 = gJMonthOfYearDateTimeField0.getDifferenceAsLong(858L, 31083597720000L);
    }

    @Test(timeout = 4000)
    public void test1313() throws Throwable {
        BuddhistChronology buddhistChronology0 = BuddhistChronology.getInstanceUTC();
        DateTimeZone dateTimeZone0 = buddhistChronology0.getZone();
        IslamicChronology islamicChronology0 = IslamicChronology.getInstance(dateTimeZone0);
        BasicMonthOfYearDateTimeField basicMonthOfYearDateTimeField0 = new BasicMonthOfYearDateTimeField(islamicChronology0, 0);
        long long0 = basicMonthOfYearDateTimeField0.addWrapField((long) 0, 0);
    }

    @Test(timeout = 4000)
    public void test1414() throws Throwable {
        BuddhistChronology buddhistChronology0 = BuddhistChronology.getInstanceUTC();
        DateTimeZone dateTimeZone0 = buddhistChronology0.getZone();
        IslamicChronology islamicChronology0 = IslamicChronology.getInstance(dateTimeZone0);
        BasicMonthOfYearDateTimeField basicMonthOfYearDateTimeField0 = new BasicMonthOfYearDateTimeField(islamicChronology0, 0);
        long long0 = basicMonthOfYearDateTimeField0.addWrapField(10000000L, 1);
    }

    @Test(timeout = 4000)
    public void test1515() throws Throwable {
        FixedDateTimeZone fixedDateTimeZone0 = (FixedDateTimeZone) DateTimeZone.UTC;
        CopticChronology copticChronology0 = CopticChronology.getInstance((DateTimeZone) fixedDateTimeZone0);
        BasicMonthOfYearDateTimeField basicMonthOfYearDateTimeField0 = new BasicMonthOfYearDateTimeField(copticChronology0, (-1));
        MonthDay monthDay0 = new MonthDay(1, 1);
        basicMonthOfYearDateTimeField0.add((ReadablePartial) monthDay0, 1868, (int[]) null, 0);
        basicMonthOfYearDateTimeField0.getMinimumValue();
    }

    @Test(timeout = 4000)
    public void test1516() throws Throwable {
        FixedDateTimeZone fixedDateTimeZone0 = (FixedDateTimeZone) DateTimeZone.UTC;
        CopticChronology copticChronology0 = CopticChronology.getInstance((DateTimeZone) fixedDateTimeZone0);
        BasicMonthOfYearDateTimeField basicMonthOfYearDateTimeField0 = new BasicMonthOfYearDateTimeField(copticChronology0, (-1));
        MonthDay monthDay0 = new MonthDay(1, 1);
        basicMonthOfYearDateTimeField0.add((ReadablePartial) monthDay0, 1868, (int[]) null, 0);
        basicMonthOfYearDateTimeField0.getMaximumValue();
    }

    @Test(timeout = 4000)
    public void test1617() throws Throwable {
        BuddhistChronology buddhistChronology0 = BuddhistChronology.getInstanceUTC();
        DateTimeZone dateTimeZone0 = buddhistChronology0.getZone();
        CopticChronology copticChronology0 = CopticChronology.getInstance(dateTimeZone0, 1);
        GJMonthOfYearDateTimeField gJMonthOfYearDateTimeField0 = new GJMonthOfYearDateTimeField(copticChronology0);
        long long0 = gJMonthOfYearDateTimeField0.add(0L, 0L);
    }

    @Test(timeout = 4000)
    public void test1718() throws Throwable {
        JulianChronology julianChronology0 = JulianChronology.getInstanceUTC();
        BasicMonthOfYearDateTimeField basicMonthOfYearDateTimeField0 = new BasicMonthOfYearDateTimeField(julianChronology0, 0);
        long long0 = basicMonthOfYearDateTimeField0.add(0L, 0);
    }

    @Test(timeout = 4000)
    public void test1819() throws Throwable {
        CopticChronology copticChronology0 = CopticChronology.getInstance();
        BasicMonthOfYearDateTimeField basicMonthOfYearDateTimeField0 = new BasicMonthOfYearDateTimeField(copticChronology0, (-84));
        basicMonthOfYearDateTimeField0.set(0L, (-1));
    }

    @Test(timeout = 4000)
    public void test1920() throws Throwable {
        IslamicChronology islamicChronology0 = IslamicChronology.getInstance((DateTimeZone) null);
        GJMonthOfYearDateTimeField gJMonthOfYearDateTimeField0 = new GJMonthOfYearDateTimeField(islamicChronology0);
        gJMonthOfYearDateTimeField0.remainder((-9223372036854775808L));
    }

    @Test(timeout = 4000)
    public void test2021() throws Throwable {
        EthiopicChronology ethiopicChronology0 = EthiopicChronology.getInstanceUTC();
        IslamicChronology islamicChronology0 = new IslamicChronology(ethiopicChronology0, (Object) null, (IslamicChronology.LeapYearPatternType) null);
        BasicMonthOfYearDateTimeField basicMonthOfYearDateTimeField0 = new BasicMonthOfYearDateTimeField(islamicChronology0, 15356250);
        basicMonthOfYearDateTimeField0.getLeapAmount(423L);
    }

    @Test(timeout = 4000)
    public void test2122() throws Throwable {
        TimeZone timeZone0 = TimeZone.getTimeZone("");
        DateTimeZone dateTimeZone0 = DateTimeZone.forTimeZone(timeZone0);
        IslamicChronology islamicChronology0 = IslamicChronology.getInstance(dateTimeZone0);
        GJMonthOfYearDateTimeField gJMonthOfYearDateTimeField0 = new GJMonthOfYearDateTimeField(islamicChronology0);
        gJMonthOfYearDateTimeField0.getDifferenceAsLong((-9223372036854775808L), 1);
    }

    @Test(timeout = 4000)
    public void test2223() throws Throwable {
        GregorianChronology gregorianChronology0 = GregorianChronology.getInstance();
        IslamicChronology islamicChronology0 = new IslamicChronology(gregorianChronology0, gregorianChronology0, (IslamicChronology.LeapYearPatternType) null);
        BasicMonthOfYearDateTimeField basicMonthOfYearDateTimeField0 = new BasicMonthOfYearDateTimeField(islamicChronology0, 70);
        basicMonthOfYearDateTimeField0.get(1);
    }

    @Test(timeout = 4000)
    public void test2324() throws Throwable {
        BuddhistChronology buddhistChronology0 = BuddhistChronology.getInstance();
        DateTimeZone dateTimeZone0 = buddhistChronology0.getZone();
        GregorianChronology gregorianChronology0 = GregorianChronology.getInstance(dateTimeZone0);
        BasicMonthOfYearDateTimeField basicMonthOfYearDateTimeField0 = new BasicMonthOfYearDateTimeField(gregorianChronology0, 1);
        LocalTime localTime0 = new LocalTime((long) (-1657), dateTimeZone0);
        int[] intArray0 = new int[3];
        intArray0[0] = (-2982);
        basicMonthOfYearDateTimeField0.add((ReadablePartial) localTime0, 2491, intArray0, (-889));
    }

    @Test(timeout = 4000)
    public void test2425() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis((-2290));
        CopticChronology copticChronology0 = CopticChronology.getInstance(dateTimeZone0);
        GJMonthOfYearDateTimeField gJMonthOfYearDateTimeField0 = new GJMonthOfYearDateTimeField(copticChronology0);
        LocalDate localDate0 = LocalDate.now();
        gJMonthOfYearDateTimeField0.add((ReadablePartial) localDate0, 1, (int[]) null, (-2290));
    }

    @Test(timeout = 4000)
    public void test2526() throws Throwable {
        EthiopicChronology ethiopicChronology0 = EthiopicChronology.getInstanceUTC();
        GJMonthOfYearDateTimeField gJMonthOfYearDateTimeField0 = new GJMonthOfYearDateTimeField(ethiopicChronology0);
        int[] intArray0 = new int[3];
        gJMonthOfYearDateTimeField0.add((ReadablePartial) null, 2808, intArray0, 2808);
    }

    @Test(timeout = 4000)
    public void test2627() throws Throwable {
        EthiopicChronology ethiopicChronology0 = EthiopicChronology.getInstanceUTC();
        IslamicChronology islamicChronology0 = new IslamicChronology(ethiopicChronology0, (Object) null, (IslamicChronology.LeapYearPatternType) null);
        BasicMonthOfYearDateTimeField basicMonthOfYearDateTimeField0 = new BasicMonthOfYearDateTimeField(islamicChronology0, 15356250);
        basicMonthOfYearDateTimeField0.add((long) 1, 423L);
    }

    @Test(timeout = 4000)
    public void test2728() throws Throwable {
        GJChronology gJChronology0 = GJChronology.getInstanceUTC();
        Object object0 = new Object();
        IslamicChronology islamicChronology0 = new IslamicChronology(gJChronology0, object0, (IslamicChronology.LeapYearPatternType) null);
        BasicMonthOfYearDateTimeField basicMonthOfYearDateTimeField0 = new BasicMonthOfYearDateTimeField(islamicChronology0, 1);
        basicMonthOfYearDateTimeField0.add((long) 1, (-401));
    }

    @Test(timeout = 4000)
    public void test2829() throws Throwable {
        BasicMonthOfYearDateTimeField basicMonthOfYearDateTimeField0 = null;
        basicMonthOfYearDateTimeField0 = new BasicMonthOfYearDateTimeField((BasicChronology) null, 2028);
    }

    @Test(timeout = 4000)
    public void test2930() throws Throwable {
        CopticChronology copticChronology0 = CopticChronology.getInstanceUTC();
        BasicMonthOfYearDateTimeField basicMonthOfYearDateTimeField0 = new BasicMonthOfYearDateTimeField(copticChronology0, 1);
        boolean boolean0 = basicMonthOfYearDateTimeField0.isLeap((-2500847999999L));
    }

    @Test(timeout = 4000)
    public void test3031() throws Throwable {
        CopticChronology copticChronology0 = CopticChronology.getInstance();
        BasicMonthOfYearDateTimeField basicMonthOfYearDateTimeField0 = new BasicMonthOfYearDateTimeField(copticChronology0, 1);
        long long0 = basicMonthOfYearDateTimeField0.set((long) 1, 1);
    }

    @Test(timeout = 4000)
    public void test3132() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.getDefault();
        GregorianChronology gregorianChronology0 = GregorianChronology.getInstance(dateTimeZone0);
        GJMonthOfYearDateTimeField gJMonthOfYearDateTimeField0 = new GJMonthOfYearDateTimeField(gregorianChronology0);
        long long0 = gJMonthOfYearDateTimeField0.getDifferenceAsLong(1L, 1L);
    }

    @Test(timeout = 4000)
    public void test3233() throws Throwable {
        JulianChronology julianChronology0 = JulianChronology.getInstanceUTC();
        GJMonthOfYearDateTimeField gJMonthOfYearDateTimeField0 = new GJMonthOfYearDateTimeField(julianChronology0);
        long long0 = gJMonthOfYearDateTimeField0.add((-1326L), (-1043));
    }

    @Test(timeout = 4000)
    public void test3334() throws Throwable {
        GregorianChronology gregorianChronology0 = GregorianChronology.getInstanceUTC();
        BasicMonthOfYearDateTimeField basicMonthOfYearDateTimeField0 = new BasicMonthOfYearDateTimeField(gregorianChronology0, 352831696);
        long long0 = basicMonthOfYearDateTimeField0.add((long) 352831696, 2145871423);
    }

    @Test(timeout = 4000)
    public void test3435() throws Throwable {
        GregorianChronology gregorianChronology0 = GregorianChronology.getInstanceUTC();
        BasicMonthOfYearDateTimeField basicMonthOfYearDateTimeField0 = new BasicMonthOfYearDateTimeField(gregorianChronology0, 352831696);
        long long0 = basicMonthOfYearDateTimeField0.roundFloor((-1353L));
    }

    @Test(timeout = 4000)
    public void test3536() throws Throwable {
        GregorianChronology gregorianChronology0 = GregorianChronology.getInstanceUTC();
        BasicMonthOfYearDateTimeField basicMonthOfYearDateTimeField0 = new BasicMonthOfYearDateTimeField(gregorianChronology0, 352831696);
        int int0 = basicMonthOfYearDateTimeField0.get((-4442L));
    }

    @Test(timeout = 4000)
    public void test3637() throws Throwable {
        GregorianChronology gregorianChronology0 = GregorianChronology.getInstanceUTC();
        BasicMonthOfYearDateTimeField basicMonthOfYearDateTimeField0 = new BasicMonthOfYearDateTimeField(gregorianChronology0, 352831696);
        DurationField durationField0 = basicMonthOfYearDateTimeField0.getRangeDurationField();
    }

    @Test(timeout = 4000)
    public void test3738() throws Throwable {
        GregorianChronology gregorianChronology0 = GregorianChronology.getInstanceUTC();
        BasicMonthOfYearDateTimeField basicMonthOfYearDateTimeField0 = new BasicMonthOfYearDateTimeField(gregorianChronology0, 352831696);
        int int0 = basicMonthOfYearDateTimeField0.getLeapAmount(0L);
    }

    @Test(timeout = 4000)
    public void test3839() throws Throwable {
        JulianChronology julianChronology0 = JulianChronology.getInstanceUTC();
        BasicMonthOfYearDateTimeField basicMonthOfYearDateTimeField0 = new BasicMonthOfYearDateTimeField(julianChronology0, 93);
        boolean boolean0 = basicMonthOfYearDateTimeField0.isLeap((-59245776000000L));
    }

    @Test(timeout = 4000)
    public void test3940() throws Throwable {
        CopticChronology copticChronology0 = CopticChronology.getInstanceUTC();
        BasicMonthOfYearDateTimeField basicMonthOfYearDateTimeField0 = new BasicMonthOfYearDateTimeField(copticChronology0, 1);
        int int0 = basicMonthOfYearDateTimeField0.getLeapAmount((-2500847999999L));
    }

    @Test(timeout = 4000)
    public void test4041() throws Throwable {
        JulianChronology julianChronology0 = JulianChronology.getInstanceUTC();
        BasicMonthOfYearDateTimeField basicMonthOfYearDateTimeField0 = new BasicMonthOfYearDateTimeField(julianChronology0, 93);
        boolean boolean0 = basicMonthOfYearDateTimeField0.isLeap(93);
    }

    @Test(timeout = 4000)
    public void test4142() throws Throwable {
        EthiopicChronology ethiopicChronology0 = EthiopicChronology.getInstanceUTC();
        BasicMonthOfYearDateTimeField basicMonthOfYearDateTimeField0 = new BasicMonthOfYearDateTimeField(ethiopicChronology0, 1);
        long long0 = basicMonthOfYearDateTimeField0.set(21260793600000L, 13);
    }

    @Test(timeout = 4000)
    public void test4243() throws Throwable {
        GregorianChronology gregorianChronology0 = GregorianChronology.getInstanceUTC();
        BasicMonthOfYearDateTimeField basicMonthOfYearDateTimeField0 = new BasicMonthOfYearDateTimeField(gregorianChronology0, 1873);
        long long0 = basicMonthOfYearDateTimeField0.getDifferenceAsLong((-1878L), (-2282));
    }

    @Test(timeout = 4000)
    public void test4344() throws Throwable {
        EthiopicChronology ethiopicChronology0 = EthiopicChronology.getInstanceUTC();
        BasicMonthOfYearDateTimeField basicMonthOfYearDateTimeField0 = new BasicMonthOfYearDateTimeField(ethiopicChronology0, 1);
        long long0 = basicMonthOfYearDateTimeField0.getDifferenceAsLong(21260102400000L, 2235);
    }

    @Test(timeout = 4000)
    public void test4445() throws Throwable {
        CopticChronology copticChronology0 = CopticChronology.getInstance();
        BasicMonthOfYearDateTimeField basicMonthOfYearDateTimeField0 = new BasicMonthOfYearDateTimeField(copticChronology0, 1);
        Partial partial0 = new Partial();
        int[] intArray0 = new int[9];
        int[] intArray1 = basicMonthOfYearDateTimeField0.add((ReadablePartial) partial0, 2144031101, intArray0, 1);
    }

    @Test(timeout = 4000)
    public void test4546() throws Throwable {
        EthiopicChronology ethiopicChronology0 = EthiopicChronology.getInstanceUTC();
        GJMonthOfYearDateTimeField gJMonthOfYearDateTimeField0 = new GJMonthOfYearDateTimeField(ethiopicChronology0);
        DateTimeFieldType[] dateTimeFieldTypeArray0 = new DateTimeFieldType[2];
        DateTimeFieldType dateTimeFieldType0 = DateTimeFieldType.hourOfDay();
        dateTimeFieldTypeArray0[0] = dateTimeFieldType0;
        DateTimeFieldType dateTimeFieldType1 = DateTimeFieldType.secondOfDay();
        dateTimeFieldTypeArray0[1] = dateTimeFieldType1;
        int[] intArray0 = new int[2];
        Partial partial0 = new Partial(dateTimeFieldTypeArray0, intArray0);
        gJMonthOfYearDateTimeField0.add((ReadablePartial) partial0, 0, intArray0, 1);
    }

    @Test(timeout = 4000)
    public void test4647() throws Throwable {
        EthiopicChronology ethiopicChronology0 = EthiopicChronology.getInstance();
        BasicMonthOfYearDateTimeField basicMonthOfYearDateTimeField0 = new BasicMonthOfYearDateTimeField(ethiopicChronology0, 1);
        basicMonthOfYearDateTimeField0.add((long) (byte) 39, 1000000000000000L);
    }

    @Test(timeout = 4000)
    public void test4748() throws Throwable {
        JulianChronology julianChronology0 = JulianChronology.getInstance();
        BasicMonthOfYearDateTimeField basicMonthOfYearDateTimeField0 = new BasicMonthOfYearDateTimeField(julianChronology0, 93);
        basicMonthOfYearDateTimeField0.add((-59232556800000L), (-59232556800000L));
    }

    @Test(timeout = 4000)
    public void test4849() throws Throwable {
        JulianChronology julianChronology0 = JulianChronology.getInstanceUTC();
        BasicMonthOfYearDateTimeField basicMonthOfYearDateTimeField0 = new BasicMonthOfYearDateTimeField(julianChronology0, 1);
        long long0 = basicMonthOfYearDateTimeField0.add((long) (-2017), (-2591999999L));
    }

    @Test(timeout = 4000)
    public void test4950() throws Throwable {
        EthiopicChronology ethiopicChronology0 = EthiopicChronology.getInstance();
        BasicMonthOfYearDateTimeField basicMonthOfYearDateTimeField0 = new BasicMonthOfYearDateTimeField(ethiopicChronology0, 1);
        long long0 = basicMonthOfYearDateTimeField0.add(2678397925L, 2678397925L);
    }

    @Test(timeout = 4000)
    public void test5051() throws Throwable {
        EthiopicChronology ethiopicChronology0 = EthiopicChronology.getInstanceUTC();
        BasicMonthOfYearDateTimeField basicMonthOfYearDateTimeField0 = new BasicMonthOfYearDateTimeField(ethiopicChronology0, 1);
        long long0 = basicMonthOfYearDateTimeField0.add(21260793600000L, (-1));
    }

    @Test(timeout = 4000)
    public void test5152() throws Throwable {
        JulianChronology julianChronology0 = JulianChronology.getInstanceUTC();
        BasicMonthOfYearDateTimeField basicMonthOfYearDateTimeField0 = new BasicMonthOfYearDateTimeField(julianChronology0, 93);
        MockDate mockDate0 = new MockDate(2013265920, 93, 93);
        LocalDateTime localDateTime0 = LocalDateTime.fromDateFields(mockDate0);
        int[] intArray0 = new int[0];
        basicMonthOfYearDateTimeField0.add((ReadablePartial) localDateTime0, 2013265920, intArray0, 1048);
    }

    @Test(timeout = 4000)
    public void test5253() throws Throwable {
        JulianChronology julianChronology0 = JulianChronology.getInstanceUTC();
        BasicMonthOfYearDateTimeField basicMonthOfYearDateTimeField0 = new BasicMonthOfYearDateTimeField(julianChronology0, 93);
        long long0 = basicMonthOfYearDateTimeField0.addWrapField((-59232556800000L), 1123);
    }

    @Test(timeout = 4000)
    public void test5354() throws Throwable {
        JulianChronology julianChronology0 = JulianChronology.getInstanceUTC();
        BasicMonthOfYearDateTimeField basicMonthOfYearDateTimeField0 = new BasicMonthOfYearDateTimeField(julianChronology0, 93);
        int int0 = basicMonthOfYearDateTimeField0.getMaximumValue();
    }

    @Test(timeout = 4000)
    public void test5455() throws Throwable {
        JulianChronology julianChronology0 = JulianChronology.getInstanceUTC();
        BasicMonthOfYearDateTimeField basicMonthOfYearDateTimeField0 = new BasicMonthOfYearDateTimeField(julianChronology0, 93);
        boolean boolean0 = basicMonthOfYearDateTimeField0.isLenient();
    }

    @Test(timeout = 4000)
    public void test5556() throws Throwable {
        IslamicChronology islamicChronology0 = IslamicChronology.getInstance();
        GJMonthOfYearDateTimeField gJMonthOfYearDateTimeField0 = new GJMonthOfYearDateTimeField(islamicChronology0);
        int int0 = gJMonthOfYearDateTimeField0.getMinimumValue();
    }

    @Test(timeout = 4000)
    public void test5657() throws Throwable {
        JulianChronology julianChronology0 = JulianChronology.getInstanceUTC();
        BasicMonthOfYearDateTimeField basicMonthOfYearDateTimeField0 = new BasicMonthOfYearDateTimeField(julianChronology0, 93);
        DurationField durationField0 = basicMonthOfYearDateTimeField0.getLeapDurationField();
        durationField0.isSupported();
    }

    @Test(timeout = 4000)
    public void test5758() throws Throwable {
        EthiopicChronology ethiopicChronology0 = EthiopicChronology.getInstance();
        BasicMonthOfYearDateTimeField basicMonthOfYearDateTimeField0 = new BasicMonthOfYearDateTimeField(ethiopicChronology0, 1);
        long long0 = basicMonthOfYearDateTimeField0.remainder(1000000000000000L);
    }
}
