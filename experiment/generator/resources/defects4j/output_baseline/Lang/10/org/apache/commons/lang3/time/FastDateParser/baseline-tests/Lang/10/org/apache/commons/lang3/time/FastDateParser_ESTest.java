/*
 * This file was automatically generated by EvoSuite
 * Mon Oct 16 01:03:42 GMT 2023
 */
package org.apache.commons.lang3.time;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.text.ParseException;
import java.text.ParsePosition;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import java.util.regex.Pattern;
import org.apache.commons.lang3.time.FastDateParser;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class FastDateParser_ESTest extends FastDateParser_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        TimeZone timeZone0 = TimeZone.getDefault();
        Locale locale0 = Locale.CHINA;
        FastDateParser fastDateParser0 = new FastDateParser("K$jW8s?#", timeZone0, locale0);
        fastDateParser0.getPattern();
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        SimpleTimeZone simpleTimeZone0 = new SimpleTimeZone(2327, "0VrUXQ*z", 2327, 0, 0, 2327, 4, 0, 3533, (-2280), 3533);
        Locale locale0 = Locale.PRC;
        FastDateParser fastDateParser0 = new FastDateParser("0VrUXQ*z", simpleTimeZone0, locale0);
        ParsePosition parsePosition0 = new ParsePosition(4);
        Object object0 = fastDateParser0.parseObject("\" does not match ", parsePosition0);
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        TimeZone timeZone0 = TimeZone.getDefault();
        Locale locale0 = Locale.TAIWAN;
        FastDateParser fastDateParser0 = new FastDateParser("<#L';fdTB&OKlfSwF ", timeZone0, locale0);
        ParsePosition parsePosition0 = new ParsePosition(0);
        fastDateParser0.parseObject("<#L';fdTB&OKlfSwF ", parsePosition0);
        parsePosition0.getIndex();
    }

    @Test(timeout = 4000)
    public void test023() throws Throwable {
        TimeZone timeZone0 = TimeZone.getDefault();
        Locale locale0 = Locale.TAIWAN;
        FastDateParser fastDateParser0 = new FastDateParser("<#L';fdTB&OKlfSwF ", timeZone0, locale0);
        ParsePosition parsePosition0 = new ParsePosition(0);
        fastDateParser0.parseObject("<#L';fdTB&OKlfSwF ", parsePosition0);
        parsePosition0.toString();
    }

    @Test(timeout = 4000)
    public void test034() throws Throwable {
        TimeZone timeZone0 = TimeZone.getDefault();
        Locale locale0 = Locale.GERMANY;
        FastDateParser fastDateParser0 = new FastDateParser("0#MOIST", timeZone0, locale0);
        boolean boolean0 = fastDateParser0.isNextNumber();
    }

    @Test(timeout = 4000)
    public void test045() throws Throwable {
        SimpleTimeZone simpleTimeZone0 = new SimpleTimeZone(0, "GMTCNT");
        Locale locale0 = Locale.JAPANESE;
        FastDateParser fastDateParser0 = new FastDateParser("GMTCNT", simpleTimeZone0, locale0);
        TimeZone timeZone0 = fastDateParser0.getTimeZone();
    }

    @Test(timeout = 4000)
    public void test056() throws Throwable {
        SimpleTimeZone simpleTimeZone0 = new SimpleTimeZone(108, "");
        Locale locale0 = Locale.forLanguageTag("");
        FastDateParser fastDateParser0 = new FastDateParser("9micCGWVp6hJi3:", simpleTimeZone0, locale0);
        fastDateParser0.parse("9micCGWVp6hJi3:", (ParsePosition) null);
    }

    @Test(timeout = 4000)
    public void test067() throws Throwable {
        TimeZone timeZone0 = TimeZone.getDefault();
        Locale locale0 = Locale.JAPAN;
        FastDateParser fastDateParser0 = new FastDateParser("$L.iK6,KS@0uqa/", timeZone0, locale0);
        fastDateParser0.parse((String) null);
    }

    @Test(timeout = 4000)
    public void test078() throws Throwable {
        Locale locale0 = Locale.CHINA;
        TimeZone timeZone0 = TimeZone.getDefault();
        FastDateParser fastDateParser0 = new FastDateParser("_BD1'(V@~3", timeZone0, locale0);
        fastDateParser0.getFieldWidth();
    }

    @Test(timeout = 4000)
    public void test089() throws Throwable {
        Locale locale0 = Locale.PRC;
        FastDateParser fastDateParser0 = null;
        fastDateParser0 = new FastDateParser("", (TimeZone) null, locale0);
    }

    @Test(timeout = 4000)
    public void test0910() throws Throwable {
        TimeZone timeZone0 = TimeZone.getDefault();
        Locale locale0 = Locale.ROOT;
        FastDateParser fastDateParser0 = new FastDateParser("zkf+qpgno/ RW", timeZone0, locale0);
        fastDateParser0.getPattern();
    }

    @Test(timeout = 4000)
    public void test1011() throws Throwable {
        TimeZone timeZone0 = TimeZone.getTimeZone("+OJq9i?");
        Locale locale0 = Locale.PRC;
        FastDateParser fastDateParser0 = new FastDateParser("];fCcOt", timeZone0, locale0);
        fastDateParser0.getPattern();
    }

    @Test(timeout = 4000)
    public void test1112() throws Throwable {
        TimeZone timeZone0 = TimeZone.getDefault();
        Locale locale0 = Locale.US;
        FastDateParser fastDateParser0 = new FastDateParser("$*9vp+j", timeZone0, locale0);
        fastDateParser0.getPattern();
    }

    @Test(timeout = 4000)
    public void test1213() throws Throwable {
        ZoneOffset zoneOffset0 = ZoneOffset.ofTotalSeconds((-164));
        TimeZone timeZone0 = TimeZone.getTimeZone((ZoneId) zoneOffset0);
        Locale locale0 = Locale.TRADITIONAL_CHINESE;
        FastDateParser fastDateParser0 = new FastDateParser("6Mm|t6AO&", timeZone0, locale0);
        fastDateParser0.getPattern();
    }

    @Test(timeout = 4000)
    public void test1314() throws Throwable {
        TimeZone timeZone0 = TimeZone.getDefault();
        Locale locale0 = Locale.TAIWAN;
        FastDateParser fastDateParser0 = new FastDateParser("[!2?B", timeZone0, locale0);
        fastDateParser0.getPattern();
    }

    @Test(timeout = 4000)
    public void test1415() throws Throwable {
        SimpleTimeZone simpleTimeZone0 = new SimpleTimeZone(2327, "0VrUXQ*z", 2327, 0, 0, 2327, 4, 0, 3533, (-2280), 3533);
        Locale locale0 = Locale.PRC;
        FastDateParser fastDateParser0 = new FastDateParser("0VrUXQ*z", simpleTimeZone0, locale0);
        ParsePosition parsePosition0 = new ParsePosition(4);
        Date date0 = fastDateParser0.parse("0VrUXQ*z", parsePosition0);
    }

    @Test(timeout = 4000)
    public void test1516() throws Throwable {
        SimpleTimeZone simpleTimeZone0 = new SimpleTimeZone(0, "");
        Locale locale0 = Locale.JAPAN;
        FastDateParser fastDateParser0 = new FastDateParser("1xB{r[zmHb.(", simpleTimeZone0, locale0);
        ParsePosition parsePosition0 = new ParsePosition(0);
        fastDateParser0.parse("1xB{r[zmHb.(", parsePosition0);
        parsePosition0.getIndex();
    }

    @Test(timeout = 4000)
    public void test1517() throws Throwable {
        SimpleTimeZone simpleTimeZone0 = new SimpleTimeZone(0, "");
        Locale locale0 = Locale.JAPAN;
        FastDateParser fastDateParser0 = new FastDateParser("1xB{r[zmHb.(", simpleTimeZone0, locale0);
        ParsePosition parsePosition0 = new ParsePosition(0);
        fastDateParser0.parse("1xB{r[zmHb.(", parsePosition0);
        parsePosition0.toString();
    }

    @Test(timeout = 4000)
    public void test1618() throws Throwable {
        TimeZone timeZone0 = TimeZone.getDefault();
        Locale locale0 = Locale.GERMANY;
        FastDateParser fastDateParser0 = new FastDateParser("0#MOIST", timeZone0, locale0);
        fastDateParser0.parse("0#MOIST");
    }

    @Test(timeout = 4000)
    public void test1719() throws Throwable {
        TimeZone timeZone0 = TimeZone.getTimeZone("`b{Fp1epV!Dih");
        Locale locale0 = Locale.CHINESE;
        FastDateParser fastDateParser0 = new FastDateParser("`b{Fp1epV!Dih", timeZone0, locale0);
        fastDateParser0.getPattern();
    }

    @Test(timeout = 4000)
    public void test1820() throws Throwable {
        TimeZone timeZone0 = TimeZone.getDefault();
        Locale locale0 = Locale.FRANCE;
        FastDateParser fastDateParser0 = new FastDateParser("\"K>=/L(ei:8HpA.;e", timeZone0, locale0);
        fastDateParser0.getPattern();
    }

    @Test(timeout = 4000)
    public void test1921() throws Throwable {
        SimpleTimeZone simpleTimeZone0 = new SimpleTimeZone(2517, "D;0%RsvvZ+v");
        Locale locale0 = Locale.GERMANY;
        FastDateParser fastDateParser0 = new FastDateParser("D;0%RsvvZ+v", simpleTimeZone0, locale0);
        fastDateParser0.getPattern();
    }

    @Test(timeout = 4000)
    public void test2022() throws Throwable {
        TimeZone timeZone0 = TimeZone.getTimeZone("-huR");
        Locale locale0 = Locale.TRADITIONAL_CHINESE;
        FastDateParser fastDateParser0 = new FastDateParser("8 <Ao08wp9B)", timeZone0, locale0);
        ParsePosition parsePosition0 = new ParsePosition(116);
        fastDateParser0.parse("8 <Ao08wp9B)", parsePosition0);
    }

    @Test(timeout = 4000)
    public void test2123() throws Throwable {
        Locale locale0 = Locale.PRC;
        TimeZone timeZone0 = TimeZone.getDefault();
        FastDateParser fastDateParser0 = new FastDateParser("a7", timeZone0, locale0);
        fastDateParser0.getPattern();
    }

    @Test(timeout = 4000)
    public void test2224() throws Throwable {
        ZoneOffset zoneOffset0 = ZoneOffset.ofHoursMinutes(0, 0);
        TimeZone timeZone0 = TimeZone.getTimeZone((ZoneId) zoneOffset0);
        Locale locale0 = Locale.ROOT;
        FastDateParser fastDateParser0 = new FastDateParser(":W4tH7$Ghf;D$g", timeZone0, locale0);
        fastDateParser0.parseObject((String) null);
    }

    @Test(timeout = 4000)
    public void test2325() throws Throwable {
        SimpleTimeZone simpleTimeZone0 = new SimpleTimeZone(0, "");
        Locale locale0 = Locale.JAPAN;
        FastDateParser fastDateParser0 = new FastDateParser("1xB{r[zmHb.(", simpleTimeZone0, locale0);
        Date date0 = fastDateParser0.parse("1xB{r[zmHb.(");
        date0.toString();
    }

    @Test(timeout = 4000)
    public void test2426() throws Throwable {
        TimeZone timeZone0 = TimeZone.getTimeZone("+OJq9i?");
        Locale locale0 = Locale.PRC;
        FastDateParser fastDateParser0 = new FastDateParser("s.P8I]5lA", timeZone0, locale0);
        fastDateParser0.getPattern();
    }

    @Test(timeout = 4000)
    public void test2527() throws Throwable {
        SimpleTimeZone simpleTimeZone0 = new SimpleTimeZone(1101, ",yJc<i^P7.4%9B{J");
        Locale locale0 = Locale.TRADITIONAL_CHINESE;
        FastDateParser fastDateParser0 = new FastDateParser(",yJc<i^P7.4%9B{J", simpleTimeZone0, locale0);
        ParsePosition parsePosition0 = new ParsePosition(1101);
        fastDateParser0.parseObject(",yJc<i^P7.4%9B{J", parsePosition0);
    }

    @Test(timeout = 4000)
    public void test2628() throws Throwable {
        TimeZone timeZone0 = TimeZone.getTimeZone("+OJq9i?");
        Locale locale0 = Locale.PRC;
        FastDateParser fastDateParser0 = new FastDateParser("z }p1UzA61<RLqPYX", timeZone0, locale0);
        int int0 = fastDateParser0.adjustYear((-2011));
    }

    @Test(timeout = 4000)
    public void test2729() throws Throwable {
        TimeZone timeZone0 = TimeZone.getTimeZone("+OJq9i?");
        Locale locale0 = Locale.PRC;
        FastDateParser fastDateParser0 = new FastDateParser("z p1^UzA61<RL)qPYXL", timeZone0, locale0);
        Object object0 = fastDateParser0.parseObject("GMTJST");
        object0.toString();
    }

    @Test(timeout = 4000)
    public void test2830() throws Throwable {
        TimeZone timeZone0 = TimeZone.getTimeZone("");
        Locale locale0 = FastDateParser.JAPANESE_IMPERIAL;
        FastDateParser fastDateParser0 = new FastDateParser("(p{IsNd}++)", timeZone0, locale0);
        fastDateParser0.parse("\" does not match ");
    }

    @Test(timeout = 4000)
    public void test2931() throws Throwable {
        ZoneOffset zoneOffset0 = ZoneOffset.ofTotalSeconds(3359);
        TimeZone timeZone0 = TimeZone.getTimeZone((ZoneId) zoneOffset0);
        Locale locale0 = Locale.KOREA;
        FastDateParser fastDateParser0 = new FastDateParser(":EW{h5z :fAa", timeZone0, locale0);
        fastDateParser0.parseObject(":EW{h5z :fAa");
    }

    @Test(timeout = 4000)
    public void test3032() throws Throwable {
        TimeZone timeZone0 = TimeZone.getDefault();
        Locale locale0 = Locale.JAPAN;
        FastDateParser fastDateParser0 = new FastDateParser("$L.iK6,KS@0uqa/", timeZone0, locale0);
        boolean boolean0 = fastDateParser0.equals(timeZone0);
    }

    @Test(timeout = 4000)
    public void test3133() throws Throwable {
        TimeZone timeZone0 = TimeZone.getDefault();
        Locale locale0 = Locale.KOREAN;
        FastDateParser fastDateParser0 = null;
        fastDateParser0 = new FastDateParser("", timeZone0, locale0);
    }

    @Test(timeout = 4000)
    public void test3234() throws Throwable {
        TimeZone timeZone0 = TimeZone.getDefault();
        Locale locale0 = Locale.GERMAN;
        FastDateParser fastDateParser0 = new FastDateParser("9nr$`", timeZone0, locale0);
        Pattern pattern0 = fastDateParser0.getParsePattern();
        pattern0.pattern();
    }

    @Test(timeout = 4000)
    public void test3335() throws Throwable {
        TimeZone timeZone0 = TimeZone.getDefault();
        Locale locale0 = Locale.JAPAN;
        FastDateParser fastDateParser0 = new FastDateParser("$L.iK6,KS@0uqa/", timeZone0, locale0);
        Locale locale1 = fastDateParser0.getLocale();
        locale1.getVariant();
    }

    @Test(timeout = 4000)
    public void test3436() throws Throwable {
        TimeZone timeZone0 = TimeZone.getDefault();
        Locale locale0 = new Locale(" ", " ", " ");
        FastDateParser fastDateParser0 = new FastDateParser(" ", timeZone0, locale0);
        String string0 = fastDateParser0.getPattern();
    }

    @Test(timeout = 4000)
    public void test3537() throws Throwable {
        ZoneOffset zoneOffset0 = ZoneOffset.ofTotalSeconds(3359);
        TimeZone timeZone0 = TimeZone.getTimeZone((ZoneId) zoneOffset0);
        Locale locale0 = Locale.KOREA;
        FastDateParser fastDateParser0 = new FastDateParser(":EW{hTz :fAa", timeZone0, locale0);
        fastDateParser0.parseObject("", (ParsePosition) null);
    }

    @Test(timeout = 4000)
    public void test3638() throws Throwable {
        TimeZone timeZone0 = TimeZone.getTimeZone("7LZTCNw`0NKgw*");
        Locale locale0 = Locale.ITALY;
        FastDateParser fastDateParser0 = new FastDateParser("D+|E+|F+|G+|H+|K+|M+|S+|W+|Z+|a+|d+|h+|k+|m+|s+|w+|y+|z+|''|'[^']++(''[^']*+)*+'|[^'A-Za-z]++", timeZone0, locale0);
        String string0 = fastDateParser0.toString();
    }

    @Test(timeout = 4000)
    public void test3739() throws Throwable {
        ZoneOffset zoneOffset0 = ZoneOffset.ofTotalSeconds(3359);
        TimeZone timeZone0 = TimeZone.getTimeZone((ZoneId) zoneOffset0);
        Locale locale0 = Locale.KOREA;
        FastDateParser fastDateParser0 = new FastDateParser(":EW{h5z :fAa", timeZone0, locale0);
        fastDateParser0.hashCode();
    }

    @Test(timeout = 4000)
    public void test3840() throws Throwable {
        ZoneId zoneId0 = ZoneId.systemDefault();
        TimeZone timeZone0 = TimeZone.getTimeZone(zoneId0);
        Locale locale0 = Locale.ROOT;
        FastDateParser fastDateParser0 = new FastDateParser("?w?yKGM<B=9%9IL)CL", timeZone0, locale0);
        int int0 = fastDateParser0.adjustYear(825);
    }

    @Test(timeout = 4000)
    public void test3941() throws Throwable {
        TimeZone timeZone0 = TimeZone.getDefault();
        Locale locale0 = new Locale("36", "y>0m-0L0K)", "3sUSw_PJd2]a#wEY\f");
        FastDateParser fastDateParser0 = new FastDateParser("FZ=5?Et#%bJ]<D/", timeZone0, locale0);
        fastDateParser0.getPattern();
    }

    @Test(timeout = 4000)
    public void test4042() throws Throwable {
        TimeZone timeZone0 = TimeZone.getDefault();
        Locale locale0 = Locale.ITALIAN;
        FastDateParser fastDateParser0 = new FastDateParser("k/Zp#v;7", timeZone0, locale0);
        fastDateParser0.getPattern();
    }
}
