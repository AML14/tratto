/*
 * This file was automatically generated by EvoSuite
 * Mon Oct 16 00:51:46 GMT 2023
 */
package org.apache.commons.lang3.text.translate;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.CharBuffer;
import org.apache.commons.lang3.text.translate.LookupTranslator;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class LookupTranslator_ESTest extends LookupTranslator_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test00() throws Throwable {
        CharSequence[][] charSequenceArray0 = new CharSequence[7][9];
        CharSequence[] charSequenceArray1 = new CharSequence[3];
        CharBuffer charBuffer0 = CharBuffer.allocate(2426);
        CharBuffer charBuffer1 = CharBuffer.wrap((CharSequence) charBuffer0, 16, 2426);
        charSequenceArray1[0] = (CharSequence) charBuffer1;
        charSequenceArray1[1] = (CharSequence) charBuffer0;
        charSequenceArray0[0] = charSequenceArray1;
        CharSequence[] charSequenceArray2 = new CharSequence[9];
        charSequenceArray2[0] = (CharSequence) charBuffer0;
        charSequenceArray0[1] = charSequenceArray2;
        charSequenceArray0[2] = charSequenceArray1;
        charSequenceArray0[3] = charSequenceArray0[2];
        charSequenceArray0[4] = charSequenceArray0[2];
        charSequenceArray0[5] = charSequenceArray0[0];
        charSequenceArray0[6] = charSequenceArray0[2];
        LookupTranslator lookupTranslator0 = new LookupTranslator(charSequenceArray0);
        StringWriter stringWriter0 = new StringWriter();
        int int0 = lookupTranslator0.translate((CharSequence) charBuffer0, 4, (Writer) stringWriter0);
    }

    @Test(timeout = 4000)
    public void test11() throws Throwable {
        CharSequence[][] charSequenceArray0 = new CharSequence[3][9];
        CharSequence[] charSequenceArray1 = new CharSequence[4];
        charSequenceArray1[0] = (CharSequence) "0";
        StringWriter stringWriter0 = new StringWriter();
        CharBuffer charBuffer0 = CharBuffer.wrap(charSequenceArray1[0], 0, 0);
        charSequenceArray1[2] = (CharSequence) charBuffer0;
        charSequenceArray0[0] = charSequenceArray1;
        charSequenceArray0[1] = charSequenceArray1;
        charSequenceArray0[2] = charSequenceArray0[0];
        LookupTranslator lookupTranslator0 = new LookupTranslator(charSequenceArray0);
        int int0 = lookupTranslator0.translate(charSequenceArray1[2], 1345, (Writer) stringWriter0);
        assertEquals(1, int0);
    }

    @Test(timeout = 4000)
    public void test22() throws Throwable {
        CharSequence[][] charSequenceArray0 = new CharSequence[3][9];
        CharSequence[] charSequenceArray1 = new CharSequence[4];
        charSequenceArray1[0] = (CharSequence) "0";
        StringWriter stringWriter0 = new StringWriter(0);
        charSequenceArray0[0] = charSequenceArray1;
        charSequenceArray0[1] = charSequenceArray0[0];
        charSequenceArray0[2] = charSequenceArray0[0];
        LookupTranslator lookupTranslator0 = new LookupTranslator(charSequenceArray0);
        lookupTranslator0.translate((CharSequence) "5E0", (-2505), (Writer) stringWriter0);
    }

    @Test(timeout = 4000)
    public void test33() throws Throwable {
        CharSequence[][] charSequenceArray0 = new CharSequence[3][9];
        CharSequence[] charSequenceArray1 = new CharSequence[4];
        charSequenceArray1[0] = (CharSequence) "0";
        StringWriter stringWriter0 = new StringWriter(0);
        charSequenceArray0[0] = charSequenceArray1;
        charSequenceArray0[1] = charSequenceArray0[0];
        charSequenceArray0[2] = charSequenceArray0[0];
        LookupTranslator lookupTranslator0 = new LookupTranslator(charSequenceArray0);
        lookupTranslator0.translate(charSequenceArray1[1], 1504, (Writer) stringWriter0);
    }

    @Test(timeout = 4000)
    public void test44() throws Throwable {
        CharSequence[] charSequenceArray0 = new CharSequence[4];
        StringWriter stringWriter0 = new StringWriter(0);
        StringBuffer stringBuffer0 = stringWriter0.getBuffer();
        charSequenceArray0[0] = (CharSequence) stringBuffer0;
        CharBuffer charBuffer0 = CharBuffer.wrap(charSequenceArray0[0], 0, 0);
        CharSequence[][] charSequenceArray1 = new CharSequence[5][4];
        charSequenceArray1[0] = charSequenceArray0;
        charSequenceArray1[1] = charSequenceArray0;
        charSequenceArray1[2] = charSequenceArray0;
        charSequenceArray1[3] = charSequenceArray0;
        charSequenceArray1[4] = charSequenceArray0;
        LookupTranslator lookupTranslator0 = new LookupTranslator(charSequenceArray1);
        lookupTranslator0.translate((CharSequence) charBuffer0, (-3585), (Writer) stringWriter0);
    }

    @Test(timeout = 4000)
    public void test55() throws Throwable {
        CharSequence[][] charSequenceArray0 = new CharSequence[9][3];
        LookupTranslator lookupTranslator0 = null;
        lookupTranslator0 = new LookupTranslator(charSequenceArray0);
    }

    @Test(timeout = 4000)
    public void test66() throws Throwable {
        CharSequence[][] charSequenceArray0 = new CharSequence[2][9];
        CharSequence[] charSequenceArray1 = new CharSequence[2];
        char[] charArray0 = new char[3];
        CharBuffer charBuffer0 = CharBuffer.wrap(charArray0);
        charSequenceArray1[0] = (CharSequence) charBuffer0;
        CharBuffer charBuffer1 = CharBuffer.wrap((CharSequence) charBuffer0);
        charBuffer0.get();
        charSequenceArray0[0] = charSequenceArray1;
        CharSequence[] charSequenceArray2 = new CharSequence[3];
        charSequenceArray2[0] = (CharSequence) charBuffer1;
        charSequenceArray0[1] = charSequenceArray2;
        LookupTranslator lookupTranslator0 = null;
        lookupTranslator0 = new LookupTranslator(charSequenceArray0);
    }

    @Test(timeout = 4000)
    public void test77() throws Throwable {
        CharSequence[] charSequenceArray0 = new CharSequence[13];
        StringWriter stringWriter0 = new StringWriter();
        StringBuffer stringBuffer0 = stringWriter0.getBuffer();
        charSequenceArray0[0] = (CharSequence) stringBuffer0;
        CharBuffer charBuffer0 = CharBuffer.allocate(0);
        CharSequence[] charSequenceArray1 = new CharSequence[30];
        charSequenceArray1[0] = (CharSequence) "0";
        charSequenceArray1[1] = (CharSequence) charBuffer0;
        charSequenceArray1[4] = (CharSequence) "0";
        CharSequence[][] charSequenceArray2 = new CharSequence[2][1];
        charSequenceArray2[0] = charSequenceArray0;
        charSequenceArray2[1] = charSequenceArray1;
        LookupTranslator lookupTranslator0 = new LookupTranslator(charSequenceArray2);
        int int0 = lookupTranslator0.translate(charSequenceArray1[4], 0, (Writer) stringWriter0);
        assertEquals(1, int0);
    }

    @Test(timeout = 4000)
    public void test78() throws Throwable {
        CharSequence[] charSequenceArray0 = new CharSequence[13];
        StringWriter stringWriter0 = new StringWriter();
        StringBuffer stringBuffer0 = stringWriter0.getBuffer();
        charSequenceArray0[0] = (CharSequence) stringBuffer0;
        CharBuffer charBuffer0 = CharBuffer.allocate(0);
        CharSequence[] charSequenceArray1 = new CharSequence[30];
        charSequenceArray1[0] = (CharSequence) "0";
        charSequenceArray1[1] = (CharSequence) charBuffer0;
        charSequenceArray1[4] = (CharSequence) "0";
        CharSequence[][] charSequenceArray2 = new CharSequence[2][1];
        charSequenceArray2[0] = charSequenceArray0;
        charSequenceArray2[1] = charSequenceArray1;
        LookupTranslator lookupTranslator0 = new LookupTranslator(charSequenceArray2);
        int int0 = lookupTranslator0.translate(charSequenceArray1[4], 0, (Writer) stringWriter0);
        stringWriter0.toString();
    }

    @Test(timeout = 4000)
    public void test89() throws Throwable {
        CharSequence[][] charSequenceArray0 = new CharSequence[2][1];
        LookupTranslator lookupTranslator0 = null;
        lookupTranslator0 = new LookupTranslator(charSequenceArray0);
    }

    @Test(timeout = 4000)
    public void test910() throws Throwable {
        LookupTranslator lookupTranslator0 = new LookupTranslator((CharSequence[][]) null);
    }
}
