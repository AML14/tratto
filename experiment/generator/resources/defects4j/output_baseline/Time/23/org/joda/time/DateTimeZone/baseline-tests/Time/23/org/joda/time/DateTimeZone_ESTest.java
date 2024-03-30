/*
 * This file was automatically generated by EvoSuite
 * Tue Oct 17 02:16:45 GMT 2023
 */
package org.joda.time;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Locale;
import java.util.Set;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.evosuite.runtime.mock.java.util.MockGregorianCalendar;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.Instant;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.MutableDateTime;
import org.joda.time.ReadableInstant;
import org.joda.time.YearMonthDay;
import org.joda.time.chrono.BuddhistChronology;
import org.joda.time.chrono.GJChronology;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeParser;
import org.joda.time.format.DateTimePrinter;
import org.joda.time.tz.FixedDateTimeZone;
import org.joda.time.tz.NameProvider;
import org.joda.time.tz.Provider;
import org.joda.time.tz.UTCProvider;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class DateTimeZone_ESTest extends DateTimeZone_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        FixedDateTimeZone fixedDateTimeZone0 = (FixedDateTimeZone) DateTimeZone.UTC;
        DateTimeUtils.setCurrentMillisFixed(9223372036854775807L);
        BuddhistChronology buddhistChronology0 = BuddhistChronology.getInstance((DateTimeZone) fixedDateTimeZone0);
        YearMonthDay yearMonthDay0 = new YearMonthDay((Object) null, buddhistChronology0);
        int[] intArray0 = new int[1];
        YearMonthDay yearMonthDay1 = new YearMonthDay(yearMonthDay0, intArray0);
        yearMonthDay1.toDateTimeAtCurrentTime();
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forID("Europe/Paris");
        int int0 = dateTimeZone0.getOffsetFromLocal(1L);
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis(Integer.MIN_VALUE);
        dateTimeZone0.getID();
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        SimpleTimeZone simpleTimeZone0 = new SimpleTimeZone((-28378000), "-08:00");
        DateTimeZone dateTimeZone0 = DateTimeZone.forTimeZone(simpleTimeZone0);
        dateTimeZone0.toString();
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetHoursMinutes(0, 1);
        dateTimeZone0.toString();
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetHoursMinutes(60, 59);
        dateTimeZone0.toString();
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        DateTimeZone.forOffsetHoursMinutes(0, (-1972));
    }

    @Test(timeout = 4000)
    public void test077() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forID("-00:00:02.895");
        dateTimeZone0.getID();
    }

    @Test(timeout = 4000)
    public void test088() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.getDefault();
        DateTimePrinter dateTimePrinter0 = mock(DateTimePrinter.class, new ViolatedAssumptionAnswer());
        DateTimeParser dateTimeParser0 = mock(DateTimeParser.class, new ViolatedAssumptionAnswer());
        doReturn(267).when(dateTimeParser0).parseInto(any(org.joda.time.format.DateTimeParserBucket.class), anyString(), anyInt());
        DateTimeFormatter dateTimeFormatter0 = new DateTimeFormatter(dateTimePrinter0, dateTimeParser0);
        LocalDateTime localDateTime0 = LocalDateTime.parse("UTC", dateTimeFormatter0);
        boolean boolean0 = dateTimeZone0.isLocalDateTimeGap(localDateTime0);
    }

    @Test(timeout = 4000)
    public void test099() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis((-2364));
        TimeZone timeZone0 = dateTimeZone0.toTimeZone();
        dateTimeZone0.toString();
    }

    @Test(timeout = 4000)
    public void test0910() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis((-2364));
        TimeZone timeZone0 = dateTimeZone0.toTimeZone();
        timeZone0.getRawOffset();
    }

    @Test(timeout = 4000)
    public void test1011() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis((-1));
        dateTimeZone0.UTC.previousTransition(0L);
        dateTimeZone0.toString();
    }

    @Test(timeout = 4000)
    public void test1012() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis((-1));
        dateTimeZone0.UTC.previousTransition(0L);
        dateTimeZone0.getID();
    }

    @Test(timeout = 4000)
    public void test1113() throws Throwable {
        BuddhistChronology buddhistChronology0 = BuddhistChronology.getInstanceUTC();
        DateTimeZone dateTimeZone0 = buddhistChronology0.getZone();
        long long0 = dateTimeZone0.previousTransition(1);
    }

    @Test(timeout = 4000)
    public void test1214() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.getDefault();
        long long0 = dateTimeZone0.previousTransition((-838L));
    }

    @Test(timeout = 4000)
    public void test1315() throws Throwable {
        BuddhistChronology buddhistChronology0 = BuddhistChronology.getInstanceUTC();
        DateTimeZone dateTimeZone0 = buddhistChronology0.getZone();
        long long0 = dateTimeZone0.nextTransition(0L);
    }

    @Test(timeout = 4000)
    public void test1416() throws Throwable {
        BuddhistChronology buddhistChronology0 = BuddhistChronology.getInstanceUTC();
        DateTimeZone dateTimeZone0 = buddhistChronology0.getZone();
        long long0 = dateTimeZone0.UTC.nextTransition(10800000L);
    }

    @Test(timeout = 4000)
    public void test1517() throws Throwable {
        FixedDateTimeZone fixedDateTimeZone0 = (FixedDateTimeZone) DateTimeZone.UTC;
        long long0 = fixedDateTimeZone0.UTC.nextTransition((-103L));
    }

    @Test(timeout = 4000)
    public void test1618() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetHoursMinutes(1, 0);
        dateTimeZone0.isFixed();
        dateTimeZone0.toString();
    }

    @Test(timeout = 4000)
    public void test1719() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.getDefault();
        boolean boolean0 = dateTimeZone0.isFixed();
    }

    @Test(timeout = 4000)
    public void test1820() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis(0);
        dateTimeZone0.UTC.getStandardOffset(1047L);
        dateTimeZone0.getID();
    }

    @Test(timeout = 4000)
    public void test1821() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis(0);
        dateTimeZone0.UTC.getStandardOffset(1047L);
        dateTimeZone0.toString();
    }

    @Test(timeout = 4000)
    public void test1922() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis(1);
        int int0 = dateTimeZone0.getStandardOffset(0L);
    }

    @Test(timeout = 4000)
    public void test1923() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis(1);
        int int0 = dateTimeZone0.getStandardOffset(0L);
        dateTimeZone0.toString();
    }

    @Test(timeout = 4000)
    public void test2024() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.getDefault();
        int int0 = dateTimeZone0.getStandardOffset((-1619L));
    }

    @Test(timeout = 4000)
    public void test2125() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis(3072);
        dateTimeZone0.UTC.getOffsetFromLocal((-3L));
        dateTimeZone0.toString();
    }

    @Test(timeout = 4000)
    public void test2126() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis(3072);
        dateTimeZone0.UTC.getOffsetFromLocal((-3L));
        dateTimeZone0.getID();
    }

    @Test(timeout = 4000)
    public void test2227() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.getDefault();
        Instant instant0 = new Instant(1223L);
        int int0 = dateTimeZone0.UTC.getOffset((ReadableInstant) instant0);
    }

    @Test(timeout = 4000)
    public void test2328() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetHours(1);
        GJChronology gJChronology0 = GJChronology.getInstance(dateTimeZone0, 1738L, 1);
        Instant instant0 = gJChronology0.getGregorianCutover();
        int int0 = dateTimeZone0.getOffset((ReadableInstant) instant0);
        dateTimeZone0.toString();
    }

    @Test(timeout = 4000)
    public void test2329() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetHours(1);
        GJChronology gJChronology0 = GJChronology.getInstance(dateTimeZone0, 1738L, 1);
        Instant instant0 = gJChronology0.getGregorianCutover();
        int int0 = dateTimeZone0.getOffset((ReadableInstant) instant0);
    }

    @Test(timeout = 4000)
    public void test2430() throws Throwable {
        BuddhistChronology buddhistChronology0 = BuddhistChronology.getInstanceUTC();
        DateTimeZone dateTimeZone0 = buddhistChronology0.getZone();
        int int0 = dateTimeZone0.UTC.getOffset(2332L);
    }

    @Test(timeout = 4000)
    public void test2531() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetHours(1);
        int int0 = dateTimeZone0.getOffset(0L);
    }

    @Test(timeout = 4000)
    public void test2532() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetHours(1);
        int int0 = dateTimeZone0.getOffset(0L);
        dateTimeZone0.toString();
    }

    @Test(timeout = 4000)
    public void test2633() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.getDefault();
        int int0 = dateTimeZone0.getOffset((-2461L));
    }

    @Test(timeout = 4000)
    public void test2734() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetHours(1);
        dateTimeZone0.getNameKey(1738L);
        dateTimeZone0.toString();
    }

    @Test(timeout = 4000)
    public void test2835() throws Throwable {
        FixedDateTimeZone fixedDateTimeZone0 = (FixedDateTimeZone) DateTimeZone.UTC;
        String string0 = fixedDateTimeZone0.getNameKey(2442L);
    }

    @Test(timeout = 4000)
    public void test2936() throws Throwable {
        FixedDateTimeZone fixedDateTimeZone0 = (FixedDateTimeZone) DateTimeZone.UTC;
        long long0 = fixedDateTimeZone0.UTC.getMillisKeepLocal(fixedDateTimeZone0, 0L);
    }

    @Test(timeout = 4000)
    public void test3037() throws Throwable {
        BuddhistChronology buddhistChronology0 = BuddhistChronology.getInstance();
        DateTimeZone dateTimeZone0 = buddhistChronology0.getZone();
        DateTimeZone dateTimeZone1 = DateTimeZone.forOffsetHours(1);
        long long0 = dateTimeZone0.getMillisKeepLocal(dateTimeZone1, 0L);
    }

    @Test(timeout = 4000)
    public void test3038() throws Throwable {
        BuddhistChronology buddhistChronology0 = BuddhistChronology.getInstance();
        DateTimeZone dateTimeZone0 = buddhistChronology0.getZone();
        DateTimeZone dateTimeZone1 = DateTimeZone.forOffsetHours(1);
        long long0 = dateTimeZone0.getMillisKeepLocal(dateTimeZone1, 0L);
        dateTimeZone1.getID();
    }

    @Test(timeout = 4000)
    public void test3139() throws Throwable {
        FixedDateTimeZone fixedDateTimeZone0 = (FixedDateTimeZone) DateTimeZone.UTC;
        boolean boolean0 = fixedDateTimeZone0.equals(fixedDateTimeZone0);
    }

    @Test(timeout = 4000)
    public void test3240() throws Throwable {
        BuddhistChronology buddhistChronology0 = BuddhistChronology.getInstance();
        DateTimeZone dateTimeZone0 = buddhistChronology0.getZone();
        boolean boolean0 = dateTimeZone0.equals(buddhistChronology0);
    }

    @Test(timeout = 4000)
    public void test3341() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis(0);
        long long0 = dateTimeZone0.convertUTCToLocal(0L);
    }

    @Test(timeout = 4000)
    public void test3342() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis(0);
        long long0 = dateTimeZone0.convertUTCToLocal(0L);
        dateTimeZone0.toString();
    }

    @Test(timeout = 4000)
    public void test3443() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis(1129);
        long long0 = dateTimeZone0.convertLocalToUTC((long) 1129, true, 465L);
        dateTimeZone0.toString();
    }

    @Test(timeout = 4000)
    public void test3444() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis(1129);
        long long0 = dateTimeZone0.convertLocalToUTC((long) 1129, true, 465L);
    }

    @Test(timeout = 4000)
    public void test3545() throws Throwable {
        GJChronology gJChronology0 = GJChronology.getInstanceUTC();
        DateTimeZone dateTimeZone0 = gJChronology0.getZone();
        long long0 = dateTimeZone0.convertLocalToUTC((-9223372036854775808L), false, (-9223372036854775808L));
    }

    @Test(timeout = 4000)
    public void test3646() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forID("Africa/Harare");
        long long0 = dateTimeZone0.convertLocalToUTC((long) 7200000, false);
    }

    @Test(timeout = 4000)
    public void test3747() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis(1295);
        long long0 = dateTimeZone0.adjustOffset(1295, true);
    }

    @Test(timeout = 4000)
    public void test3748() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis(1295);
        long long0 = dateTimeZone0.adjustOffset(1295, true);
        dateTimeZone0.toString();
    }

    @Test(timeout = 4000)
    public void test3849() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forID("Europe/Paris");
        long long0 = dateTimeZone0.adjustOffset((-1331L), false);
    }

    @Test(timeout = 4000)
    public void test3950() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetHoursMinutes(177, 0);
        TimeZone timeZone0 = dateTimeZone0.toTimeZone();
        DateTimeZone.forTimeZone(timeZone0);
    }

    @Test(timeout = 4000)
    public void test4051() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.getDefault();
        long long0 = dateTimeZone0.getMillisKeepLocal(dateTimeZone0, 1061L);
    }

    @Test(timeout = 4000)
    public void test4152() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.getDefault();
        long long0 = dateTimeZone0.convertLocalToUTC(2779L, true);
    }

    @Test(timeout = 4000)
    public void test4253() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis(1);
        long long0 = dateTimeZone0.convertLocalToUTC(10800000L, true, 0L);
        dateTimeZone0.toString();
    }

    @Test(timeout = 4000)
    public void test4254() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis(1);
        long long0 = dateTimeZone0.convertLocalToUTC(10800000L, true, 0L);
    }

    @Test(timeout = 4000)
    public void test4355() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis((-1));
        Locale locale0 = Locale.JAPANESE;
        String string0 = dateTimeZone0.getName(0L, locale0);
    }

    @Test(timeout = 4000)
    public void test4456() throws Throwable {
        BuddhistChronology buddhistChronology0 = BuddhistChronology.getInstanceUTC();
        DateTimeZone dateTimeZone0 = buddhistChronology0.getZone();
        String string0 = dateTimeZone0.getName((long) 1, (Locale) null);
    }

    @Test(timeout = 4000)
    public void test4558() throws Throwable {
        UTCProvider uTCProvider0 = new UTCProvider();
        DateTimeZone dateTimeZone0 = uTCProvider0.getZone("UTC");
        String string0 = dateTimeZone0.getShortName((long) (-363), (Locale) null);
    }

    @Test(timeout = 4000)
    public void test4660() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis(3072);
        TimeZone timeZone0 = dateTimeZone0.toTimeZone();
        DateTimeZone.forTimeZone(timeZone0);
        DateTimeZone dateTimeZone1 = DateTimeZone.forTimeZone(timeZone0);
    }

    @Test(timeout = 4000)
    public void test4661() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis(3072);
        TimeZone timeZone0 = dateTimeZone0.toTimeZone();
        DateTimeZone.forTimeZone(timeZone0);
        DateTimeZone dateTimeZone1 = DateTimeZone.forTimeZone(timeZone0);
        timeZone0.getRawOffset();
    }

    @Test(timeout = 4000)
    public void test4662() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis(3072);
        TimeZone timeZone0 = dateTimeZone0.toTimeZone();
        DateTimeZone.forTimeZone(timeZone0);
        DateTimeZone dateTimeZone1 = DateTimeZone.forTimeZone(timeZone0);
        dateTimeZone0.toString();
    }

    @Test(timeout = 4000)
    public void test4663() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis(3072);
        TimeZone timeZone0 = dateTimeZone0.toTimeZone();
        DateTimeZone.forTimeZone(timeZone0);
        DateTimeZone dateTimeZone1 = DateTimeZone.forTimeZone(timeZone0);
        dateTimeZone1.toString();
    }

    @Test(timeout = 4000)
    public void test4764() throws Throwable {
        TimeZone timeZone0 = TimeZone.getTimeZone("VST");
        DateTimeZone dateTimeZone0 = DateTimeZone.forTimeZone(timeZone0);
        dateTimeZone0.toString();
    }

    @Test(timeout = 4000)
    public void test4865() throws Throwable {
        DateTimeZone.forOffsetHoursMinutes((-1655), 44);
    }

    @Test(timeout = 4000)
    public void test4966() throws Throwable {
        Integer integer0 = new Integer(0);
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetHoursMinutes((int) integer0, 0);
        dateTimeZone0.getID();
    }

    @Test(timeout = 4000)
    public void test5067() throws Throwable {
        DateTimeZone.forID(")*EX&Y5xR5N:H@oh9");
    }

    @Test(timeout = 4000)
    public void test5168() throws Throwable {
        UTCProvider uTCProvider0 = new UTCProvider();
        DateTimeZone.setProvider(uTCProvider0);
        DateTimeZone dateTimeZone0 = DateTimeZone.getDefault();
        //  // Unstable assertion: assertEquals("UTC", dateTimeZone0.getID());
    }

    @Test(timeout = 4000)
    public void test5269() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.getDefault();
        DateTimeZone dateTimeZone1 = DateTimeZone.getDefault();
    }

    @Test(timeout = 4000)
    public void test5370() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forID("Africa/Harare");
        dateTimeZone0.hashCode();
    }

    @Test(timeout = 4000)
    public void test5471() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis(1);
        String string0 = dateTimeZone0.getID();
    }

    @Test(timeout = 4000)
    public void test5572() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.getDefault();
        long long0 = dateTimeZone0.adjustOffset(0L, true);
    }

    @Test(timeout = 4000)
    public void test5673() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetHours(1);
        MockGregorianCalendar mockGregorianCalendar0 = new MockGregorianCalendar(690562340, 377, 1, 43, 1);
        LocalDate localDate0 = LocalDate.fromCalendarFields(mockGregorianCalendar0);
        LocalTime localTime0 = new LocalTime((-1019L));
        LocalDateTime localDateTime0 = localDate0.toLocalDateTime(localTime0);
        boolean boolean0 = dateTimeZone0.isLocalDateTimeGap(localDateTime0);
    }

    @Test(timeout = 4000)
    public void test5674() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetHours(1);
        MockGregorianCalendar mockGregorianCalendar0 = new MockGregorianCalendar(690562340, 377, 1, 43, 1);
        LocalDate localDate0 = LocalDate.fromCalendarFields(mockGregorianCalendar0);
        LocalTime localTime0 = new LocalTime((-1019L));
        LocalDateTime localDateTime0 = localDate0.toLocalDateTime(localTime0);
        boolean boolean0 = dateTimeZone0.isLocalDateTimeGap(localDateTime0);
        dateTimeZone0.toString();
    }

    @Test(timeout = 4000)
    public void test5775() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.getDefault();
        dateTimeZone0.isLocalDateTimeGap((LocalDateTime) null);
    }

    @Test(timeout = 4000)
    public void test5876() throws Throwable {
        BuddhistChronology buddhistChronology0 = BuddhistChronology.getInstanceUTC();
        DateTimeZone dateTimeZone0 = buddhistChronology0.getZone();
        long long0 = dateTimeZone0.getMillisKeepLocal((DateTimeZone) null, 51L);
    }

    @Test(timeout = 4000)
    public void test5977() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetHours(567);
        long long0 = dateTimeZone0.convertLocalToUTC(4998L, false);
    }

    @Test(timeout = 4000)
    public void test5978() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetHours(567);
        long long0 = dateTimeZone0.convertLocalToUTC(4998L, false);
        dateTimeZone0.toString();
    }

    @Test(timeout = 4000)
    public void test6079() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.getDefault();
        dateTimeZone0.convertLocalToUTC(9223372036854775807L, true);
    }

    @Test(timeout = 4000)
    public void test6180() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.getDefault();
        dateTimeZone0.convertLocalToUTC(9223372036854775807L, false);
    }

    @Test(timeout = 4000)
    public void test6281() throws Throwable {
        BuddhistChronology buddhistChronology0 = BuddhistChronology.getInstance();
        MutableDateTime mutableDateTime0 = null;
        mutableDateTime0 = new MutableDateTime(1, 0, 1, 0, 567, 1, 567, buddhistChronology0);
    }

    @Test(timeout = 4000)
    public void test6382() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetHours(1);
        dateTimeZone0.adjustOffset(9223372036854775807L, false);
    }

    @Test(timeout = 4000)
    public void test6483() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.getDefault();
        long long0 = dateTimeZone0.convertUTCToLocal(0L);
    }

    @Test(timeout = 4000)
    public void test6584() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetHours(1);
        long long0 = dateTimeZone0.convertUTCToLocal(0L);
    }

    @Test(timeout = 4000)
    public void test6585() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetHours(1);
        long long0 = dateTimeZone0.convertUTCToLocal(0L);
        dateTimeZone0.toString();
    }

    @Test(timeout = 4000)
    public void test6686() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forID("Africa/Harare");
        int int0 = dateTimeZone0.getOffsetFromLocal((-217L));
    }

    @Test(timeout = 4000)
    public void test6787() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.getDefault();
        int int0 = dateTimeZone0.getOffsetFromLocal(9223372036854775807L);
    }

    @Test(timeout = 4000)
    public void test6888() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.getDefault();
        int int0 = dateTimeZone0.getOffsetFromLocal(1654L);
    }

    @Test(timeout = 4000)
    public void test6989() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.getDefault();
        boolean boolean0 = dateTimeZone0.isStandardOffset(0L);
    }

    @Test(timeout = 4000)
    public void test7090() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis((-1));
        int int0 = dateTimeZone0.getOffset((ReadableInstant) null);
    }

    @Test(timeout = 4000)
    public void test7091() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis((-1));
        int int0 = dateTimeZone0.getOffset((ReadableInstant) null);
        dateTimeZone0.toString();
    }

    @Test(timeout = 4000)
    public void test7192() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetHours(1);
        Locale locale0 = new Locale("{%#:[8[Nb'1[RYD&d", "{%#:[8[Nb'1[RYD&d");
        String string0 = dateTimeZone0.getShortName(0L, locale0);
    }

    @Test(timeout = 4000)
    public void test7293() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis(1000);
        dateTimeZone0.getID();
    }

    @Test(timeout = 4000)
    public void test7394() throws Throwable {
        DateTimeZone.setNameProvider((NameProvider) null);
    }

    @Test(timeout = 4000)
    public void test7495() throws Throwable {
        DateTimeZone.setProvider((Provider) null);
    }

    @Test(timeout = 4000)
    public void test7596() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis(3072);
        TimeZone timeZone0 = dateTimeZone0.toTimeZone();
        DateTimeZone dateTimeZone1 = DateTimeZone.forTimeZone(timeZone0);
        timeZone0.getRawOffset();
    }

    @Test(timeout = 4000)
    public void test7597() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis(3072);
        TimeZone timeZone0 = dateTimeZone0.toTimeZone();
        DateTimeZone dateTimeZone1 = DateTimeZone.forTimeZone(timeZone0);
        dateTimeZone0.toString();
    }

    @Test(timeout = 4000)
    public void test7598() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis(3072);
        TimeZone timeZone0 = dateTimeZone0.toTimeZone();
        DateTimeZone dateTimeZone1 = DateTimeZone.forTimeZone(timeZone0);
    }

    @Test(timeout = 4000)
    public void test7599() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis(3072);
        TimeZone timeZone0 = dateTimeZone0.toTimeZone();
        DateTimeZone dateTimeZone1 = DateTimeZone.forTimeZone(timeZone0);
        DateTimeZone dateTimeZone2 = DateTimeZone.forID("+00:00");
    }

    @Test(timeout = 4000)
    public void test76101() throws Throwable {
        ZoneOffset zoneOffset0 = ZoneOffset.MAX;
        TimeZone timeZone0 = TimeZone.getTimeZone((ZoneId) zoneOffset0);
        DateTimeZone dateTimeZone0 = DateTimeZone.forTimeZone(timeZone0);
        dateTimeZone0.getID();
    }

    @Test(timeout = 4000)
    public void test77102() throws Throwable {
        FixedDateTimeZone fixedDateTimeZone0 = (FixedDateTimeZone) DateTimeZone.UTC;
        TimeZone timeZone0 = fixedDateTimeZone0.toTimeZone();
        DateTimeZone dateTimeZone0 = DateTimeZone.forTimeZone(timeZone0);
    }

    @Test(timeout = 4000)
    public void test78103() throws Throwable {
        DateTimeZone.forOffsetHours((-4432));
    }

    @Test(timeout = 4000)
    public void test79104() throws Throwable {
        DateTimeZone.forOffsetHoursMinutes(0, 12720000);
    }

    @Test(timeout = 4000)
    public void test80105() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forID("+00:00:01.350");
    }

    @Test(timeout = 4000)
    public void test80106() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forID("+00:00:01.350");
        dateTimeZone0.getID();
    }

    @Test(timeout = 4000)
    public void test81107() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forID("UTC");
        dateTimeZone0.toString();
    }

    @Test(timeout = 4000)
    public void test82108() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forID((String) null);
        dateTimeZone0.toString();
    }

    @Test(timeout = 4000)
    public void test83109() throws Throwable {
        DateTimeZone.setDefault((DateTimeZone) null);
    }

    @Test(timeout = 4000)
    public void test84110() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.getDefault();
        DateTimeZone.setDefault(dateTimeZone0);
        dateTimeZone0.toString();
    }

    @Test(timeout = 4000)
    public void test85111() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.getDefault();
        DateTimeZone dateTimeZone1 = DateTimeZone.forTimeZone((TimeZone) null);
    }

    @Test(timeout = 4000)
    public void test86112() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.getDefault();
        TimeZone timeZone0 = dateTimeZone0.toTimeZone();
        timeZone0.getID();
    }

    @Test(timeout = 4000)
    public void test87113() throws Throwable {
        Provider provider0 = DateTimeZone.getProvider();
    }

    @Test(timeout = 4000)
    public void test88114() throws Throwable {
        NameProvider nameProvider0 = DateTimeZone.getNameProvider();
        DateTimeZone.setNameProvider(nameProvider0);
    }

    @Test(timeout = 4000)
    public void test89115() throws Throwable {
        Set<String> set0 = DateTimeZone.getAvailableIDs();
        set0.size();
    }

    @Test(timeout = 4000)
    public void test90116() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.getDefault();
    }

    @Test(timeout = 4000)
    public void test90117() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.getDefault();
        String string0 = dateTimeZone0.toString();
    }

    @Test(timeout = 4000)
    public void test91118() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis(12740625);
        String string0 = dateTimeZone0.getName((long) 12740625);
    }

    @Test(timeout = 4000)
    public void test92119() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetHours(1);
        DateTimeZone dateTimeZone1 = DateTimeZone.forOffsetHours(1);
    }

    @Test(timeout = 4000)
    public void test92120() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetHours(1);
        DateTimeZone dateTimeZone1 = DateTimeZone.forOffsetHours(1);
        dateTimeZone1.toString();
    }

    @Test(timeout = 4000)
    public void test93121() throws Throwable {
        FixedDateTimeZone fixedDateTimeZone0 = (FixedDateTimeZone) DateTimeZone.forOffsetMillis(12740625);
        ((DateTimeZone) fixedDateTimeZone0).writeReplace();
        fixedDateTimeZone0.toString();
    }

    @Test(timeout = 4000)
    public void test94123() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.getDefault();
        String string0 = dateTimeZone0.getShortName(0L);
    }
}
