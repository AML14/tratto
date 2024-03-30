/*
 * This file was automatically generated by EvoSuite
 * Tue Oct 17 02:02:01 GMT 2023
 */
package org.joda.time.field;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeZone;
import org.joda.time.DurationFieldType;
import org.joda.time.chrono.BuddhistChronology;
import org.joda.time.chrono.EthiopicChronology;
import org.joda.time.field.FieldUtils;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class FieldUtils_ESTest extends FieldUtils_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        int int0 = FieldUtils.getWrappedValue((-2206), (-1), 1);
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        int int0 = FieldUtils.getWrappedValue(0, 0, Integer.MIN_VALUE, 0);
        assertEquals(1, int0);
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        FieldUtils.getWrappedValue(3912, 3349, 3912, 3349);
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        FieldUtils.verifyValueBounds("Multiplication overflows an int: ", 3349, 3349, 3912);
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        FieldUtils.verifyValueBounds("*<", 3566, (-495), (-495));
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        DateTimeFieldType dateTimeFieldType0 = DateTimeFieldType.hourOfHalfday();
        FieldUtils.verifyValueBounds(dateTimeFieldType0, 0, 0, 2022);
        dateTimeFieldType0.toString();
        assertNotNull(dateTimeFieldType0.toString());
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        FieldUtils.verifyValueBounds((DateTimeField) null, Integer.MIN_VALUE, Integer.MIN_VALUE, (-1));
    }

    @Test(timeout = 4000)
    public void test077() throws Throwable {
        FieldUtils.safeMultiply(1605L, 10000000000000000L);
    }

    @Test(timeout = 4000)
    public void test088() throws Throwable {
        FieldUtils.safeMultiply((-9223372036854775808L), (-851));
    }

    @Test(timeout = 4000)
    public void test099() throws Throwable {
        int int0 = FieldUtils.safeMultiply((-1), (-2147483647));
    }

    @Test(timeout = 4000)
    public void test1010() throws Throwable {
        int int0 = FieldUtils.safeMultiply(1, Integer.MIN_VALUE);
    }

    @Test(timeout = 4000)
    public void test1111() throws Throwable {
        long long0 = FieldUtils.safeSubtract((-861L), (-861L));
        assertEquals(1, long0);
    }

    @Test(timeout = 4000)
    public void test1212() throws Throwable {
        FieldUtils.safeAdd((-9223372036854775808L), (-9223372036854775808L));
    }

    @Test(timeout = 4000)
    public void test1313() throws Throwable {
        FieldUtils.safeAdd((-200), Integer.MIN_VALUE);
    }

    @Test(timeout = 4000)
    public void test1414() throws Throwable {
        int int0 = FieldUtils.safeToInt(0L);
    }

    @Test(timeout = 4000)
    public void test1515() throws Throwable {
        int int0 = FieldUtils.safeToInt((-2147483648L));
    }

    @Test(timeout = 4000)
    public void test1616() throws Throwable {
        long long0 = FieldUtils.safeSubtract(0L, 0L);
        assertEquals(0, long0);
    }

    @Test(timeout = 4000)
    public void test1717() throws Throwable {
        int int0 = FieldUtils.safeNegate((-1126));
    }

    @Test(timeout = 4000)
    public void test1818() throws Throwable {
        int int0 = FieldUtils.safeNegate(231);
    }

    @Test(timeout = 4000)
    public void test1919() throws Throwable {
        int int0 = FieldUtils.safeMultiplyToInt(0L, 0L);
        assertEquals(1, int0);
    }

    @Test(timeout = 4000)
    public void test2020() throws Throwable {
        int int0 = FieldUtils.safeMultiplyToInt((-1440L), 2856L);
        assertEquals(0, int0);
    }

    @Test(timeout = 4000)
    public void test2121() throws Throwable {
        long long0 = FieldUtils.safeMultiply(63L, (long) (-2206));
    }

    @Test(timeout = 4000)
    public void test2222() throws Throwable {
        long long0 = FieldUtils.safeMultiply((-1L), 2031);
        assertEquals(0, long0);
    }

    @Test(timeout = 4000)
    public void test2323() throws Throwable {
        int int0 = FieldUtils.safeMultiply(0, 0);
    }

    @Test(timeout = 4000)
    public void test2424() throws Throwable {
        long long0 = FieldUtils.safeAdd(0L, 0L);
        assertEquals(1, long0);
    }

    @Test(timeout = 4000)
    public void test2525() throws Throwable {
        int int0 = FieldUtils.safeAdd(365, 365);
        assertEquals(0, int0);
    }

    @Test(timeout = 4000)
    public void test2626() throws Throwable {
        int int0 = FieldUtils.getWrappedValue(0, 0, 0, 412);
        assertEquals(1, int0);
    }

    @Test(timeout = 4000)
    public void test2727() throws Throwable {
        int int0 = FieldUtils.getWrappedValue((-2283), Integer.MIN_VALUE, Integer.MIN_VALUE, 2);
        assertEquals(1, int0);
    }

    @Test(timeout = 4000)
    public void test2828() throws Throwable {
        int int0 = FieldUtils.getWrappedValue(Integer.MIN_VALUE, Integer.MIN_VALUE, (-1));
    }

    @Test(timeout = 4000)
    public void test2929() throws Throwable {
        int int0 = FieldUtils.getWrappedValue((-417), 231, 898);
    }

    @Test(timeout = 4000)
    public void test3030() throws Throwable {
        int int0 = FieldUtils.getWrappedValue(1430, (-1), 17971875);
    }

    @Test(timeout = 4000)
    public void test3131() throws Throwable {
        FieldUtils.getWrappedValue(16, 16, 16);
    }

    @Test(timeout = 4000)
    public void test3232() throws Throwable {
        FieldUtils.safeToInt(9223372036854775807L);
    }

    @Test(timeout = 4000)
    public void test3333() throws Throwable {
        int int0 = FieldUtils.safeToInt(2147483647L);
    }

    @Test(timeout = 4000)
    public void test3434() throws Throwable {
        long long0 = FieldUtils.safeMultiply(2147483647L, (long) 3224);
    }

    @Test(timeout = 4000)
    public void test3535() throws Throwable {
        long long0 = FieldUtils.safeMultiply(1L, 1L);
    }

    @Test(timeout = 4000)
    public void test3636() throws Throwable {
        long long0 = FieldUtils.safeMultiply((-2359L), (-1));
        assertEquals(1, long0);
    }

    @Test(timeout = 4000)
    public void test3737() throws Throwable {
        Integer integer0 = new Integer(271);
        boolean boolean0 = FieldUtils.equals((Object) integer0, (Object) null);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test3838() throws Throwable {
        DateTimeFieldType dateTimeFieldType0 = DateTimeFieldType.dayOfWeek();
        DateTimeField dateTimeField0 = dateTimeFieldType0.getField((Chronology) null);
        Object object0 = new Object();
        boolean boolean0 = FieldUtils.equals((Object) dateTimeField0, object0);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test3939() throws Throwable {
        Object object0 = new Object();
        boolean boolean0 = FieldUtils.equals(object0, object0);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test4040() throws Throwable {
        DateTimeFieldType dateTimeFieldType0 = DateTimeFieldType.dayOfWeek();
        DurationFieldType durationFieldType0 = dateTimeFieldType0.getRangeDurationType();
        boolean boolean0 = FieldUtils.equals((Object) null, (Object) durationFieldType0);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test4141() throws Throwable {
        int int0 = FieldUtils.getWrappedValue((-2146907296), 0, 31);
    }

    @Test(timeout = 4000)
    public void test4242() throws Throwable {
        FieldUtils.verifyValueBounds("U:b", 0, 0, (-97));
    }

    @Test(timeout = 4000)
    public void test4343() throws Throwable {
        FieldUtils.verifyValueBounds("YearMonthDay", Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    @Test(timeout = 4000)
    public void test4444() throws Throwable {
        Integer integer0 = new Integer(0);
        FieldUtils.verifyValueBounds(" * ", (int) integer0, 3654, 0);
    }

    @Test(timeout = 4000)
    public void test4545() throws Throwable {
        DateTimeFieldType dateTimeFieldType0 = DateTimeFieldType.dayOfMonth();
        FieldUtils.verifyValueBounds(dateTimeFieldType0, (-1), (-1570), (-1570));
    }

    @Test(timeout = 4000)
    public void test4646() throws Throwable {
        DateTimeFieldType dateTimeFieldType0 = DateTimeFieldType.secondOfMinute();
        FieldUtils.verifyValueBounds(dateTimeFieldType0, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
        dateTimeFieldType0.getName();
        assertNotNull(dateTimeFieldType0.getName());
    }

    @Test(timeout = 4000)
    public void test4747() throws Throwable {
        FieldUtils.verifyValueBounds((DateTimeFieldType) null, (-1958), 100, (-1958));
    }

    @Test(timeout = 4000)
    public void test4848() throws Throwable {
        FieldUtils.verifyValueBounds((DateTimeField) null, 841, (-251526), (-3169));
    }

    @Test(timeout = 4000)
    public void test4949() throws Throwable {
        DateTimeFieldType dateTimeFieldType0 = DateTimeFieldType.dayOfWeek();
        DateTimeField dateTimeField0 = dateTimeFieldType0.getField((Chronology) null);
        FieldUtils.verifyValueBounds(dateTimeField0, 0, 0, 0);
    }

    @Test(timeout = 4000)
    public void test5050() throws Throwable {
        BuddhistChronology buddhistChronology0 = BuddhistChronology.getInstanceUTC();
        DateTimeField dateTimeField0 = buddhistChronology0.millisOfSecond();
        FieldUtils.verifyValueBounds(dateTimeField0, 1, 1776, (-1207));
    }

    @Test(timeout = 4000)
    public void test5151() throws Throwable {
        FieldUtils.safeMultiplyToInt((-761L), (-2147483648L));
    }

    @Test(timeout = 4000)
    public void test5252() throws Throwable {
        FieldUtils.safeToInt((-9223372036854775808L));
    }

    @Test(timeout = 4000)
    public void test5353() throws Throwable {
        FieldUtils.safeMultiply((-1L), (-9223372036854775800L));
    }

    @Test(timeout = 4000)
    public void test5454() throws Throwable {
        FieldUtils.safeMultiply((-9223372036854775808L), (-1L));
    }

    @Test(timeout = 4000)
    public void test5555() throws Throwable {
        FieldUtils.safeMultiply((-3520L), 1000000000000000000L);
    }

    @Test(timeout = 4000)
    public void test5656() throws Throwable {
        long long0 = FieldUtils.safeMultiply((-801L), 0L);
    }

    @Test(timeout = 4000)
    public void test5757() throws Throwable {
        long long0 = FieldUtils.safeMultiply((long) 0, (long) 0);
    }

    @Test(timeout = 4000)
    public void test5858() throws Throwable {
        int int0 = FieldUtils.safeMultiplyToInt(10L, 10L);
        assertEquals(0, int0);
    }

    @Test(timeout = 4000)
    public void test5959() throws Throwable {
        long long0 = FieldUtils.safeMultiply(1L, 170L);
    }

    @Test(timeout = 4000)
    public void test6060() throws Throwable {
        FieldUtils.safeMultiply(9223372036854775805L, 1017);
    }

    @Test(timeout = 4000)
    public void test6161() throws Throwable {
        Integer integer0 = new Integer(25);
        long long0 = FieldUtils.safeMultiply((long) integer0, 1);
        assertEquals(1, long0);
    }

    @Test(timeout = 4000)
    public void test6262() throws Throwable {
        long long0 = FieldUtils.safeMultiply((long) 0, 0);
        assertEquals(1, long0);
    }

    @Test(timeout = 4000)
    public void test6363() throws Throwable {
        EthiopicChronology ethiopicChronology0 = EthiopicChronology.getInstance((DateTimeZone) null);
        long long0 = ethiopicChronology0.add((-313L), (-1556L), (-1));
    }

    @Test(timeout = 4000)
    public void test6464() throws Throwable {
        FieldUtils.safeMultiply(Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    @Test(timeout = 4000)
    public void test6565() throws Throwable {
        FieldUtils.safeMultiply(25, (-2145181949));
    }

    @Test(timeout = 4000)
    public void test6666() throws Throwable {
        FieldUtils.safeSubtract((-9223372036854775776L), 21600000L);
    }

    @Test(timeout = 4000)
    public void test6767() throws Throwable {
        long long0 = FieldUtils.safeSubtract(0L, 10L);
        assertEquals(1, long0);
    }

    @Test(timeout = 4000)
    public void test6868() throws Throwable {
        long long0 = FieldUtils.safeSubtract(1907, (-1267));
        assertEquals(0, long0);
    }

    @Test(timeout = 4000)
    public void test6969() throws Throwable {
        FieldUtils.safeAdd((-1504L), (-9223372036854775808L));
    }

    @Test(timeout = 4000)
    public void test7070() throws Throwable {
        long long0 = FieldUtils.safeAdd(10L, (-1958L));
        assertEquals(1, long0);
    }

    @Test(timeout = 4000)
    public void test7171() throws Throwable {
        long long0 = FieldUtils.safeAdd((long) 170, 1L);
        assertEquals(1, long0);
    }

    @Test(timeout = 4000)
    public void test7272() throws Throwable {
        FieldUtils.safeAdd(2147483639, 2147483639);
    }

    @Test(timeout = 4000)
    public void test7373() throws Throwable {
        int int0 = FieldUtils.safeAdd(0, (-2456));
        assertEquals(1, int0);
    }

    @Test(timeout = 4000)
    public void test7474() throws Throwable {
        int int0 = FieldUtils.safeAdd(0, 0);
        assertEquals(1, int0);
    }

    @Test(timeout = 4000)
    public void test7575() throws Throwable {
        FieldUtils.safeNegate(Integer.MIN_VALUE);
    }

    @Test(timeout = 4000)
    public void test7676() throws Throwable {
        int int0 = FieldUtils.safeNegate(0);
    }

    @Test(timeout = 4000)
    public void test7777() throws Throwable {
        int int0 = FieldUtils.safeMultiplyToInt(170L, 1L);
        assertEquals(1, int0);
    }
}
