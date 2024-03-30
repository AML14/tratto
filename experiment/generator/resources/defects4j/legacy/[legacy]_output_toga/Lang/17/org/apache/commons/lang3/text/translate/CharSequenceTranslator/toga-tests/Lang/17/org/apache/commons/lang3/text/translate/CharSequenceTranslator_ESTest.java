/*
 * This file was automatically generated by EvoSuite
 * Mon Oct 16 01:24:12 GMT 2023
 */
package org.apache.commons.lang3.text.translate;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.CharBuffer;
import org.apache.commons.lang3.text.translate.AggregateTranslator;
import org.apache.commons.lang3.text.translate.CharSequenceTranslator;
import org.apache.commons.lang3.text.translate.LookupTranslator;
import org.apache.commons.lang3.text.translate.NumericEntityEscaper;
import org.apache.commons.lang3.text.translate.NumericEntityUnescaper;
import org.apache.commons.lang3.text.translate.OctalUnescaper;
import org.apache.commons.lang3.text.translate.UnicodeEscaper;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class CharSequenceTranslator_ESTest extends CharSequenceTranslator_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        CharSequence[][] charSequenceArray0 = new CharSequence[0][3];
        LookupTranslator lookupTranslator0 = new LookupTranslator(charSequenceArray0);
        CharBuffer charBuffer0 = CharBuffer.allocate(0);
        StringWriter stringWriter0 = new StringWriter();
        int int0 = lookupTranslator0.translate((CharSequence) charBuffer0, (-999), (Writer) stringWriter0);
        assertEquals(0, int0);
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        UnicodeEscaper unicodeEscaper0 = new UnicodeEscaper();
        char[] charArray0 = new char[7];
        CharBuffer charBuffer0 = CharBuffer.wrap(charArray0);
        StringWriter stringWriter0 = new StringWriter();
        unicodeEscaper0.translate((CharSequence) charBuffer0, 6, (Writer) stringWriter0);
        stringWriter0.toString();
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        NumericEntityEscaper numericEntityEscaper0 = NumericEntityEscaper.outsideOf((-1886), (-1057));
        char[] charArray0 = new char[2];
        CharBuffer charBuffer0 = CharBuffer.wrap(charArray0);
        CharBuffer charBuffer1 = CharBuffer.allocate(2915);
        charBuffer0.read(charBuffer1);
        String string0 = numericEntityEscaper0.translate((CharSequence) charBuffer0);
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        OctalUnescaper octalUnescaper0 = new OctalUnescaper();
        octalUnescaper0.with((CharSequenceTranslator[]) null);
    }

    @Test(timeout = 4000)
    public void test054() throws Throwable {
        CharSequenceTranslator[] charSequenceTranslatorArray0 = new CharSequenceTranslator[3];
        AggregateTranslator aggregateTranslator0 = new AggregateTranslator(charSequenceTranslatorArray0);
        StringWriter stringWriter0 = new StringWriter(43);
        aggregateTranslator0.translate((CharSequence) "2B", (Writer) stringWriter0);
    }

    @Test(timeout = 4000)
    public void test065() throws Throwable {
        NumericEntityEscaper numericEntityEscaper0 = NumericEntityEscaper.outsideOf((-478), (-478));
        CharBuffer charBuffer0 = CharBuffer.allocate(65);
        CharBuffer charBuffer1 = CharBuffer.wrap((CharSequence) charBuffer0);
        charBuffer0.compact();
        CharSequenceTranslator[] charSequenceTranslatorArray0 = new CharSequenceTranslator[6];
        CharSequenceTranslator charSequenceTranslator0 = numericEntityEscaper0.with(charSequenceTranslatorArray0);
        StringWriter stringWriter0 = new StringWriter();
        charSequenceTranslator0.translate((CharSequence) charBuffer1, (Writer) stringWriter0);
    }

    @Test(timeout = 4000)
    public void test076() throws Throwable {
        CharSequenceTranslator[] charSequenceTranslatorArray0 = new CharSequenceTranslator[3];
        NumericEntityEscaper numericEntityEscaper0 = NumericEntityEscaper.above(43);
        charSequenceTranslatorArray0[2] = (CharSequenceTranslator) numericEntityEscaper0;
        StringWriter stringWriter0 = new StringWriter(43);
        charSequenceTranslatorArray0[2].translate((CharSequence) "2B", 43, (Writer) stringWriter0);
    }

    @Test(timeout = 4000)
    public void test087() throws Throwable {
        NumericEntityEscaper numericEntityEscaper0 = NumericEntityEscaper.above(428);
        numericEntityEscaper0.translate((CharSequence) null, 0, (Writer) null);
    }

    @Test(timeout = 4000)
    public void test098() throws Throwable {
        UnicodeEscaper unicodeEscaper0 = UnicodeEscaper.between(0, 0);
        char[] charArray0 = new char[3];
        CharBuffer charBuffer0 = CharBuffer.wrap(charArray0, 0, 0);
        unicodeEscaper0.translate((CharSequence) charBuffer0, 0, (Writer) null);
    }

    @Test(timeout = 4000)
    public void test109() throws Throwable {
        AggregateTranslator aggregateTranslator0 = new AggregateTranslator((CharSequenceTranslator[]) null);
        CharBuffer charBuffer0 = CharBuffer.allocate(852);
        aggregateTranslator0.translate((CharSequence) charBuffer0);
    }

    @Test(timeout = 4000)
    public void test1110() throws Throwable {
        NumericEntityEscaper numericEntityEscaper0 = NumericEntityEscaper.between(255, 1004);
        CharBuffer charBuffer0 = CharBuffer.allocate(65);
        StringWriter stringWriter0 = new StringWriter();
        numericEntityEscaper0.translate((CharSequence) charBuffer0, (Writer) stringWriter0);
        stringWriter0.toString();
    }

    @Test(timeout = 4000)
    public void test1211() throws Throwable {
        UnicodeEscaper unicodeEscaper0 = UnicodeEscaper.outsideOf(3457, 3457);
        CharSequenceTranslator[] charSequenceTranslatorArray0 = new CharSequenceTranslator[1];
        CharSequenceTranslator charSequenceTranslator0 = unicodeEscaper0.with(charSequenceTranslatorArray0);
        CharBuffer charBuffer0 = CharBuffer.allocate(3457);
        StringWriter stringWriter0 = new StringWriter(3457);
        charSequenceTranslator0.translate((CharSequence) charBuffer0, (Writer) stringWriter0);
        NumericEntityUnescaper.OPTION[] numericEntityUnescaper_OPTIONArray0 = new NumericEntityUnescaper.OPTION[0];
        NumericEntityUnescaper numericEntityUnescaper0 = new NumericEntityUnescaper(numericEntityUnescaper_OPTIONArray0);
        String string0 = numericEntityUnescaper0.translate((CharSequence) charBuffer0);
    }

    @Test(timeout = 4000)
    public void test1312() throws Throwable {
        UnicodeEscaper unicodeEscaper0 = new UnicodeEscaper();
        StringWriter stringWriter0 = new StringWriter();
        unicodeEscaper0.translate((CharSequence) null, (Writer) stringWriter0);
        stringWriter0.toString();
    }

    @Test(timeout = 4000)
    public void test1413() throws Throwable {
        OctalUnescaper octalUnescaper0 = new OctalUnescaper();
        String string0 = octalUnescaper0.translate((CharSequence) null);
        assertNotNull(string0);
    }

    @Test(timeout = 4000)
    public void test1514() throws Throwable {
        OctalUnescaper octalUnescaper0 = new OctalUnescaper();
        CharSequenceTranslator[] charSequenceTranslatorArray0 = new CharSequenceTranslator[1];
        CharSequenceTranslator charSequenceTranslator0 = octalUnescaper0.with(charSequenceTranslatorArray0);
        charSequenceTranslator0.translate((CharSequence) "FFFFFF5B", (Writer) null);
    }

    @Test(timeout = 4000)
    public void test1615() throws Throwable {
        String string0 = CharSequenceTranslator.hex(1157);
        assertNotNull(string0);
    }
}
