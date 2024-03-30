/*
 * This file was automatically generated by EvoSuite
 * Tue Oct 17 02:09:56 GMT 2023
 */
package org.joda.time;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Locale;
import java.util.Set;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.joda.time.Chronology;
import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Instant;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.ReadableInstant;
import org.joda.time.chrono.BuddhistChronology;
import org.joda.time.chrono.GJChronology;
import org.joda.time.chrono.GregorianChronology;
import org.joda.time.tz.CachedDateTimeZone;
import org.joda.time.tz.DefaultNameProvider;
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
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetHours(1);
        assertTrue(true == true);
        long default0;
        default0 = dateTimeZone0.adjustOffset(9223372036854775807L, true);
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.getDefault();
        long long0 = dateTimeZone0.convertLocalToUTC((-21477600001L), true);
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        ZoneOffset zoneOffset0 = ZoneOffset.MIN;
        TimeZone timeZone0 = TimeZone.getTimeZone((ZoneId) zoneOffset0);
        DateTimeZone dateTimeZone0 = DateTimeZone.forTimeZone(timeZone0);
        dateTimeZone0.toString();
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.getDefault();
        TimeZone timeZone0 = dateTimeZone0.toTimeZone();
        DateTimeZone dateTimeZone1 = DateTimeZone.forTimeZone(timeZone0);
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetHoursMinutes(0, 59);
        dateTimeZone0.getID();
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        DateTimeZone.forOffsetHoursMinutes(0, (-2002));
    }

    @Test(timeout = 4000)
    public void test076() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forTimeZone((TimeZone) null);
        TimeZone timeZone0 = dateTimeZone0.UTC.toTimeZone();
        timeZone0.useDaylightTime();
    }

    @Test(timeout = 4000)
    public void test087() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis((-2988));
        dateTimeZone0.UTC.previousTransition(0L);
        dateTimeZone0.getID();
    }

    @Test(timeout = 4000)
    public void test088() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis((-2988));
        dateTimeZone0.UTC.previousTransition(0L);
        dateTimeZone0.toString();
    }

    @Test(timeout = 4000)
    public void test099() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.getDefault();
        long long0 = dateTimeZone0.UTC.previousTransition(1);
    }

    @Test(timeout = 4000)
    public void test1010() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.getDefault();
        long long0 = dateTimeZone0.previousTransition(10800000L);
    }

    @Test(timeout = 4000)
    public void test1111() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetHoursMinutes(572, 1);
        dateTimeZone0.nextTransition(0L);
        dateTimeZone0.toString();
    }

    @Test(timeout = 4000)
    public void test1212() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.getDefault();
        long long0 = dateTimeZone0.nextTransition(0L);
    }

    @Test(timeout = 4000)
    public void test1313() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.getDefault();
        long long0 = dateTimeZone0.UTC.nextTransition((-1714L));
    }

    @Test(timeout = 4000)
    public void test1414() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.getDefault();
        boolean boolean0 = dateTimeZone0.isFixed();
    }

    @Test(timeout = 4000)
    public void test1515() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetHoursMinutes(572, 1);
        int int0 = dateTimeZone0.getStandardOffset(0L);
        dateTimeZone0.toString();
    }

    @Test(timeout = 4000)
    public void test1516() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetHoursMinutes(572, 1);
        int int0 = dateTimeZone0.getStandardOffset(0L);
    }

    @Test(timeout = 4000)
    public void test1617() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forTimeZone((TimeZone) null);
        int int0 = dateTimeZone0.getStandardOffset((-324L));
    }

    @Test(timeout = 4000)
    public void test1718() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetHours(550);
        dateTimeZone0.UTC.getOffsetFromLocal(0L);
        dateTimeZone0.toString();
    }

    @Test(timeout = 4000)
    public void test1719() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetHours(550);
        dateTimeZone0.UTC.getOffsetFromLocal(0L);
        dateTimeZone0.getID();
    }

    @Test(timeout = 4000)
    public void test1820() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis(3);
        int int0 = dateTimeZone0.getOffsetFromLocal((-622L));
    }

    @Test(timeout = 4000)
    public void test1821() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis(3);
        int int0 = dateTimeZone0.getOffsetFromLocal((-622L));
        dateTimeZone0.getID();
    }

    @Test(timeout = 4000)
    public void test1922() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis(2555);
        GJChronology gJChronology0 = GJChronology.getInstanceUTC();
        LocalTime localTime0 = new LocalTime((Object) null, gJChronology0);
        DateTime dateTime0 = localTime0.toDateTimeToday(dateTimeZone0);
        int int0 = dateTimeZone0.getOffset((ReadableInstant) dateTime0);
        dateTimeZone0.toString();
    }

    @Test(timeout = 4000)
    public void test1923() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis(2555);
        GJChronology gJChronology0 = GJChronology.getInstanceUTC();
        LocalTime localTime0 = new LocalTime((Object) null, gJChronology0);
        DateTime dateTime0 = localTime0.toDateTimeToday(dateTimeZone0);
        int int0 = dateTimeZone0.getOffset((ReadableInstant) dateTime0);
    }

    @Test(timeout = 4000)
    public void test2024() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.getDefault();
        Instant instant0 = new Instant();
        int int0 = dateTimeZone0.getOffset((ReadableInstant) instant0);
    }

    @Test(timeout = 4000)
    public void test2125() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis(3);
        dateTimeZone0.UTC.getOffset(3608960L);
        dateTimeZone0.toString();
    }

    @Test(timeout = 4000)
    public void test2126() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis(3);
        dateTimeZone0.UTC.getOffset(3608960L);
        dateTimeZone0.getID();
    }

    @Test(timeout = 4000)
    public void test2227() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis(682);
        int int0 = dateTimeZone0.getOffset((long) 682);
        dateTimeZone0.toString();
    }

    @Test(timeout = 4000)
    public void test2228() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis(682);
        int int0 = dateTimeZone0.getOffset((long) 682);
    }

    @Test(timeout = 4000)
    public void test2329() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.getDefault();
        int int0 = dateTimeZone0.getOffset(1173L);
    }

    @Test(timeout = 4000)
    public void test2430() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetHours(1);
        dateTimeZone0.getNameKey(60000L);
        dateTimeZone0.getID();
    }

    @Test(timeout = 4000)
    public void test2531() throws Throwable {
        BuddhistChronology buddhistChronology0 = BuddhistChronology.getInstanceUTC();
        DateTimeZone dateTimeZone0 = buddhistChronology0.getZone();
        String string0 = dateTimeZone0.getNameKey((-575L));
    }

    @Test(timeout = 4000)
    public void test2632() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.getDefault();
        long long0 = dateTimeZone0.getMillisKeepLocal(dateTimeZone0, 0L);
    }

    @Test(timeout = 4000)
    public void test2733() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.getDefault();
        DateTimeZone dateTimeZone1 = DateTimeZone.forOffsetMillis(1);
        long long0 = dateTimeZone0.UTC.getMillisKeepLocal(dateTimeZone1, 1483L);
    }

    @Test(timeout = 4000)
    public void test2734() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.getDefault();
        DateTimeZone dateTimeZone1 = DateTimeZone.forOffsetMillis(1);
        long long0 = dateTimeZone0.UTC.getMillisKeepLocal(dateTimeZone1, 1483L);
        dateTimeZone1.toString();
    }

    @Test(timeout = 4000)
    public void test2835() throws Throwable {
        FixedDateTimeZone fixedDateTimeZone0 = (FixedDateTimeZone) DateTimeZone.UTC;
        boolean boolean0 = fixedDateTimeZone0.equals(fixedDateTimeZone0);
    }

    @Test(timeout = 4000)
    public void test2936() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis(2442);
        DefaultNameProvider defaultNameProvider0 = new DefaultNameProvider();
        dateTimeZone0.UTC.equals(defaultNameProvider0);
        dateTimeZone0.toString();
    }

    @Test(timeout = 4000)
    public void test2937() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis(2442);
        DefaultNameProvider defaultNameProvider0 = new DefaultNameProvider();
        dateTimeZone0.UTC.equals(defaultNameProvider0);
        dateTimeZone0.getID();
    }

    @Test(timeout = 4000)
    public void test3038() throws Throwable {
        SimpleTimeZone simpleTimeZone0 = new SimpleTimeZone(0, "`HAljM6#");
        DateTimeZone dateTimeZone0 = DateTimeZone.forTimeZone(simpleTimeZone0);
    }

    @Test(timeout = 4000)
    public void test3039() throws Throwable {
        SimpleTimeZone simpleTimeZone0 = new SimpleTimeZone(0, "`HAljM6#");
        DateTimeZone dateTimeZone0 = DateTimeZone.forTimeZone(simpleTimeZone0);
        long long0 = dateTimeZone0.convertUTCToLocal(0);
    }

    @Test(timeout = 4000)
    public void test3140() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis((-3870));
        long long0 = dateTimeZone0.convertLocalToUTC((long) (-3870), true, (long) (-3870));
    }

    @Test(timeout = 4000)
    public void test3141() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis((-3870));
        long long0 = dateTimeZone0.convertLocalToUTC((long) (-3870), true, (long) (-3870));
        dateTimeZone0.toString();
    }

    @Test(timeout = 4000)
    public void test3242() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forID((String) null);
        long long0 = dateTimeZone0.UTC.convertLocalToUTC((-9223372036826397809L), false, (long) 0);
    }

    @Test(timeout = 4000)
    public void test3343() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.getDefault();
        long long0 = dateTimeZone0.convertLocalToUTC((-4415L), true);
    }

    @Test(timeout = 4000)
    public void test3444() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.getDefault();
        assertTrue(true == true);
        long long0;
        long0 = dateTimeZone0.adjustOffset(599L, true);
    }

    @Test(timeout = 4000)
    public void test3545() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.getDefault();
        assertTrue(((LocalDateTime) null == null) == false);
        boolean default1;
        default1 = dateTimeZone0.isLocalDateTimeGap((LocalDateTime) null);
    }

    @Test(timeout = 4000)
    public void test3646() throws Throwable {
        DateTimeZone.forOffsetHours(60000);
    }

    @Test(timeout = 4000)
    public void test3747() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.getDefault();
        dateTimeZone0.convertLocalToUTC(9223372036854775807L, false);
    }

    @Test(timeout = 4000)
    public void test3848() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.getDefault();
        long long0 = dateTimeZone0.convertLocalToUTC((-806L), true, (-806L));
    }

    @Test(timeout = 4000)
    public void test3949() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.getDefault();
        dateTimeZone0.convertLocalToUTC(9223372036854775807L, false, 9223372036854775807L);
    }

    @Test(timeout = 4000)
    public void test4050() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.getDefault();
        long long0 = dateTimeZone0.UTC.convertUTCToLocal(1L);
    }

    @Test(timeout = 4000)
    public void test4151() throws Throwable {
        BuddhistChronology buddhistChronology0 = BuddhistChronology.getInstance();
        DateTimeZone dateTimeZone0 = buddhistChronology0.getZone();
        int int0 = dateTimeZone0.getOffsetFromLocal(1);
    }

    @Test(timeout = 4000)
    public void test4252() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.getDefault();
        Locale locale0 = Locale.CHINESE;
        String string0 = dateTimeZone0.getName(158L, locale0);
    }

    @Test(timeout = 4000)
    public void test4253() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.getDefault();
        Locale locale0 = Locale.CHINESE;
        String string0 = dateTimeZone0.getName(158L, locale0);
    }

    @Test(timeout = 4000)
    public void test4354() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetHours(57);
        String string0 = dateTimeZone0.getName(3347L, (Locale) null);
    }

    @Test(timeout = 4000)
    public void test4455() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis(2555);
        Locale locale0 = Locale.CANADA;
        String string0 = dateTimeZone0.getShortName((long) 2555, locale0);
    }

    @Test(timeout = 4000)
    public void test4556() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.getDefault();
        String string0 = dateTimeZone0.getShortName(0L, (Locale) null);
    }

    @Test(timeout = 4000)
    public void test4657() throws Throwable {
        TimeZone timeZone0 = TimeZone.getDefault();
        DateTimeZone dateTimeZone0 = DateTimeZone.forTimeZone(timeZone0);
        dateTimeZone0.toString();
    }

    @Test(timeout = 4000)
    public void test4758() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetHoursMinutes((-357), 1);
        dateTimeZone0.toString();
    }

    @Test(timeout = 4000)
    public void test4859() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetHoursMinutes(0, 0);
        dateTimeZone0.UTC.isFixed();
        dateTimeZone0.toString();
    }

    @Test(timeout = 4000)
    public void test4860() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetHoursMinutes(0, 0);
        dateTimeZone0.UTC.isFixed();
        dateTimeZone0.getID();
    }

    @Test(timeout = 4000)
    public void test4961() throws Throwable {
        DateTimeZone.forID("hh!E!^m5qP");
    }

    @Test(timeout = 4000)
    public void test5062() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forID("WET");
        int int0 = dateTimeZone0.getStandardOffset(1804L);
    }

    @Test(timeout = 4000)
    public void test5163() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.getDefault();
        String string0 = dateTimeZone0.getID();
    }

    @Test(timeout = 4000)
    public void test5264() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetHours(57);
        long long0 = dateTimeZone0.UTC.adjustOffset(0, true);
        dateTimeZone0.toString();
    }

    @Test(timeout = 4000)
    public void test5265() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetHours(57);
        long long0 = dateTimeZone0.UTC.adjustOffset(0, true);
    }

    @Test(timeout = 4000)
    public void test5366() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetHoursMinutes(7, 57);
        DateMidnight dateMidnight0 = DateMidnight.now(dateTimeZone0);
        GJChronology gJChronology0 = GJChronology.getInstance(dateTimeZone0, (ReadableInstant) dateMidnight0, 7);
        LocalDateTime localDateTime0 = new LocalDateTime((Chronology) gJChronology0);
        dateTimeZone0.isLocalDateTimeGap(localDateTime0);
        dateMidnight0.getMillis();
    }

    @Test(timeout = 4000)
    public void test5367() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetHoursMinutes(7, 57);
        DateMidnight dateMidnight0 = DateMidnight.now(dateTimeZone0);
        GJChronology gJChronology0 = GJChronology.getInstance(dateTimeZone0, (ReadableInstant) dateMidnight0, 7);
        LocalDateTime localDateTime0 = new LocalDateTime((Chronology) gJChronology0);
        dateTimeZone0.isLocalDateTimeGap(localDateTime0);
        dateTimeZone0.toString();
    }

    @Test(timeout = 4000)
    public void test5468() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis(1168);
        DateTimeZone dateTimeZone1 = DateTimeZone.getDefault();
        GregorianChronology gregorianChronology0 = GregorianChronology.getInstance(dateTimeZone0);
        LocalDateTime localDateTime0 = new LocalDateTime((Chronology) gregorianChronology0);
        boolean boolean0 = dateTimeZone1.isLocalDateTimeGap(localDateTime0);
        dateTimeZone0.toString();
    }

    @Test(timeout = 4000)
    public void test5469() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis(1168);
        DateTimeZone dateTimeZone1 = DateTimeZone.getDefault();
        GregorianChronology gregorianChronology0 = GregorianChronology.getInstance(dateTimeZone0);
        LocalDateTime localDateTime0 = new LocalDateTime((Chronology) gregorianChronology0);
        boolean boolean0 = dateTimeZone1.isLocalDateTimeGap(localDateTime0);
    }

    @Test(timeout = 4000)
    public void test5570() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.getDefault();
        long long0 = dateTimeZone0.getMillisKeepLocal((DateTimeZone) null, (-806L));
    }

    @Test(timeout = 4000)
    public void test5671() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis(1168);
        long long0 = dateTimeZone0.UTC.convertLocalToUTC(0L, false);
        dateTimeZone0.toString();
    }

    @Test(timeout = 4000)
    public void test5672() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis(1168);
        long long0 = dateTimeZone0.UTC.convertLocalToUTC(0L, false);
    }

    @Test(timeout = 4000)
    public void test5773() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.getDefault();
        dateTimeZone0.UTC.getMillisKeepLocal(dateTimeZone0, 9223372036854775807L);
    }

    @Test(timeout = 4000)
    public void test5874() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis(1);
        dateTimeZone0.convertUTCToLocal(9223372036854775807L);
    }

    @Test(timeout = 4000)
    public void test5975() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forID((String) null);
        long long0 = dateTimeZone0.convertUTCToLocal(4478L);
    }

    @Test(timeout = 4000)
    public void test6076() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.getDefault();
        int int0 = dateTimeZone0.getOffsetFromLocal(9223372036854775807L);
    }

    @Test(timeout = 4000)
    public void test6177() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forID((String) null);
        boolean boolean0 = dateTimeZone0.isStandardOffset(30128928L);
    }

    @Test(timeout = 4000)
    public void test6278() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.getDefault();
        boolean boolean0 = dateTimeZone0.isStandardOffset(9223372036854775807L);
    }

    @Test(timeout = 4000)
    public void test6379() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetHours((-356));
        dateTimeZone0.UTC.getOffset((ReadableInstant) null);
        dateTimeZone0.getID();
    }

    @Test(timeout = 4000)
    public void test6380() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetHours((-356));
        dateTimeZone0.UTC.getOffset((ReadableInstant) null);
        dateTimeZone0.toString();
    }

    @Test(timeout = 4000)
    public void test6481() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.getDefault();
        String string0 = dateTimeZone0.getName(9223372036854775807L);
    }

    @Test(timeout = 4000)
    public void test6482() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.getDefault();
        String string0 = dateTimeZone0.getName(9223372036854775807L);
    }

    @Test(timeout = 4000)
    public void test6583() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetHours((-356));
        String string0 = dateTimeZone0.getShortName((long) (-356));
    }

    @Test(timeout = 4000)
    public void test6684() throws Throwable {
        SimpleTimeZone simpleTimeZone0 = new SimpleTimeZone((-1585), "user.timezone");
        DateTimeZone.forTimeZone(simpleTimeZone0);
        DateTimeZone dateTimeZone0 = DateTimeZone.forTimeZone(simpleTimeZone0);
        dateTimeZone0.toString();
    }

    @Test(timeout = 4000)
    public void test6685() throws Throwable {
        SimpleTimeZone simpleTimeZone0 = new SimpleTimeZone((-1585), "user.timezone");
        DateTimeZone.forTimeZone(simpleTimeZone0);
        DateTimeZone dateTimeZone0 = DateTimeZone.forTimeZone(simpleTimeZone0);
    }

    @Test(timeout = 4000)
    public void test6786() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis((-28378000));
        dateTimeZone0.getID();
    }

    @Test(timeout = 4000)
    public void test7087() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis(1168);
        dateTimeZone0.toString();
    }

    @Test(timeout = 4000)
    public void test7088() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis(1168);
        DateTimeZone dateTimeZone1 = DateTimeZone.forOffsetMillis(1168);
    }

    @Test(timeout = 4000)
    public void test7189() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis(0);
        dateTimeZone0.toString();
    }

    @Test(timeout = 4000)
    public void test7290() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis(12740625);
        TimeZone timeZone0 = dateTimeZone0.toTimeZone();
        DateTimeZone dateTimeZone1 = DateTimeZone.forTimeZone(timeZone0);
        dateTimeZone1.getID();
    }

    @Test(timeout = 4000)
    public void test7291() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis(12740625);
        TimeZone timeZone0 = dateTimeZone0.toTimeZone();
        DateTimeZone dateTimeZone1 = DateTimeZone.forTimeZone(timeZone0);
    }

    @Test(timeout = 4000)
    public void test7292() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetMillis(12740625);
        TimeZone timeZone0 = dateTimeZone0.toTimeZone();
        DateTimeZone dateTimeZone1 = DateTimeZone.forTimeZone(timeZone0);
        timeZone0.toString();
    }

    @Test(timeout = 4000)
    public void test7493() throws Throwable {
        TimeZone timeZone0 = TimeZone.getTimeZone("UTC");
        DateTimeZone dateTimeZone0 = DateTimeZone.forTimeZone(timeZone0);
        dateTimeZone0.isFixed();
    }

    @Test(timeout = 4000)
    public void test7594() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forTimeZone((TimeZone) null);
        assertTrue(false == true);
        long long0;
        long0 = dateTimeZone0.UTC.adjustOffset((-324L), false);
    }

    @Test(timeout = 4000)
    public void test7695() throws Throwable {
        DateTimeZone.forOffsetHoursMinutes(0, 12720000);
    }

    @Test(timeout = 4000)
    public void test7796() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forID("+00:00");
    }

    @Test(timeout = 4000)
    public void test7797() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forID("+00:00");
        dateTimeZone0.toString();
    }

    @Test(timeout = 4000)
    public void test7898() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forID("-07:00");
        dateTimeZone0.toString();
    }

    @Test(timeout = 4000)
    public void test7899() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forID("-07:00");
    }

    @Test(timeout = 4000)
    public void test79100() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forID("+07:57");
        dateTimeZone0.toString();
    }

    @Test(timeout = 4000)
    public void test79101() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forID("+07:57");
    }

    @Test(timeout = 4000)
    public void test80102() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forID("UTC");
        dateTimeZone0.getID();
    }

    @Test(timeout = 4000)
    public void test81103() throws Throwable {
        assertTrue(((DateTimeZone) null == null) == false);
        if ((DateTimeZone) null == null) {
            try {
                DateTimeZone.setDefault((DateTimeZone) null);
                fail();
            } catch (java.lang.IllegalArgumentException e) {
                // Successfully thrown exception
            }
        } else {
            DateTimeZone.setDefault((DateTimeZone) null);
        }
    }

    @Test(timeout = 4000)
    public void test82104() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.getDefault();
        DateTimeZone.setDefault(dateTimeZone0);
        dateTimeZone0.getID();
    }

    @Test(timeout = 4000)
    public void test83105() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.getDefault();
        DateTimeZone dateTimeZone1 = DateTimeZone.getDefault();
    }

    @Test(timeout = 4000)
    public void test84106() throws Throwable {
        Provider provider0 = DateTimeZone.getProvider();
    }

    @Test(timeout = 4000)
    public void test86107() throws Throwable {
        Set<String> set0 = DateTimeZone.getAvailableIDs();
        set0.size();
    }

    @Test(timeout = 4000)
    public void test87108() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetHoursMinutes(7, 57);
        String string0 = dateTimeZone0.toString();
    }

    @Test(timeout = 4000)
    public void test88109() throws Throwable {
        DateTimeZone dateTimeZone0 = DateTimeZone.forOffsetHours((-356));
        TimeZone timeZone0 = dateTimeZone0.toTimeZone();
        DateTimeZone.forTimeZone(timeZone0);
    }

    @Test(timeout = 4000)
    public void test89110() throws Throwable {
        CachedDateTimeZone cachedDateTimeZone0 = (CachedDateTimeZone) DateTimeZone.getDefault();
        Object object0 = ((DateTimeZone) cachedDateTimeZone0).writeReplace();
    }
}
