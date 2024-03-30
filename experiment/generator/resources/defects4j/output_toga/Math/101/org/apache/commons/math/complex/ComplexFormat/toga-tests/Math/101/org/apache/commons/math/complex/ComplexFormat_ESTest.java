/*
 * This file was automatically generated by EvoSuite
 * Mon Oct 16 21:38:52 GMT 2023
 */
package org.apache.commons.math.complex;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.text.AttributedCharacterIterator;
import java.text.ChoiceFormat;
import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Locale;
import org.apache.commons.math.complex.Complex;
import org.apache.commons.math.complex.ComplexFormat;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class ComplexFormat_ESTest extends ComplexFormat_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        Locale locale0 = Locale.PRC;
        NumberFormat numberFormat0 = NumberFormat.getInstance(locale0);
        ComplexFormat complexFormat0 = new ComplexFormat("4;*g7VZ+41P/", numberFormat0);
        complexFormat0.parse("Ug>0X'ASH!O");
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        NumberFormat numberFormat0 = NumberFormat.getIntegerInstance();
        ComplexFormat complexFormat0 = new ComplexFormat(numberFormat0);
        ParsePosition parsePosition0 = new ParsePosition(3);
        complexFormat0.parseObject("0 + 1i", parsePosition0);
        parsePosition0.getIndex();
    }

    @Test(timeout = 4000)
    public void test012() throws Throwable {
        NumberFormat numberFormat0 = NumberFormat.getIntegerInstance();
        ComplexFormat complexFormat0 = new ComplexFormat(numberFormat0);
        ParsePosition parsePosition0 = new ParsePosition(3);
        complexFormat0.parseObject("0 + 1i", parsePosition0);
        parsePosition0.toString();
    }

    @Test(timeout = 4000)
    public void test023() throws Throwable {
        ComplexFormat complexFormat0 = ComplexFormat.getInstance();
        ParsePosition parsePosition0 = new ParsePosition(0);
        complexFormat0.parse("(-Infinity) + (Infinity)i", parsePosition0);
        parsePosition0.getErrorIndex();
    }

    @Test(timeout = 4000)
    public void test024() throws Throwable {
        ComplexFormat complexFormat0 = ComplexFormat.getInstance();
        ParsePosition parsePosition0 = new ParsePosition(0);
        complexFormat0.parse("(-Infinity) + (Infinity)i", parsePosition0);
        parsePosition0.toString();
    }

    @Test(timeout = 4000)
    public void test035() throws Throwable {
        ComplexFormat complexFormat0 = ComplexFormat.getInstance();
        Complex complex0 = complexFormat0.parse("(NaN) - 3,737.92i");
        complex0.getImaginary();
    }

    @Test(timeout = 4000)
    public void test036() throws Throwable {
        ComplexFormat complexFormat0 = ComplexFormat.getInstance();
        Complex complex0 = complexFormat0.parse("(NaN) - 3,737.92i");
    }

    @Test(timeout = 4000)
    public void test047() throws Throwable {
        ComplexFormat complexFormat0 = ComplexFormat.getInstance();
        Complex complex0 = complexFormat0.parse("-529.25");
        complex0.abs();
    }

    @Test(timeout = 4000)
    public void test048() throws Throwable {
        ComplexFormat complexFormat0 = ComplexFormat.getInstance();
        Complex complex0 = complexFormat0.parse("-529.25");
        complex0.getReal();
    }

    @Test(timeout = 4000)
    public void test059() throws Throwable {
        DecimalFormat decimalFormat0 = new DecimalFormat("gL%E");
        ComplexFormat complexFormat0 = new ComplexFormat(" ", decimalFormat0, decimalFormat0);
        NumberFormat numberFormat0 = complexFormat0.getRealFormat();
        numberFormat0.getMinimumFractionDigits();
    }

    @Test(timeout = 4000)
    public void test0610() throws Throwable {
        ComplexFormat complexFormat0 = ComplexFormat.getInstance();
        NumberFormat numberFormat0 = NumberFormat.getCurrencyInstance();
        complexFormat0.setRealFormat(numberFormat0);
        NumberFormat numberFormat1 = complexFormat0.getRealFormat();
        numberFormat1.getMinimumIntegerDigits();
    }

    @Test(timeout = 4000)
    public void test0711() throws Throwable {
        NumberFormat numberFormat0 = NumberFormat.getIntegerInstance();
        ComplexFormat complexFormat0 = new ComplexFormat(numberFormat0);
        NumberFormat numberFormat1 = complexFormat0.getRealFormat();
    }

    @Test(timeout = 4000)
    public void test0812() throws Throwable {
        NumberFormat numberFormat0 = NumberFormat.getPercentInstance();
        Locale locale0 = Locale.ITALIAN;
        NumberFormat numberFormat1 = NumberFormat.getCurrencyInstance(locale0);
        ComplexFormat complexFormat0 = new ComplexFormat(numberFormat0, numberFormat1);
        DecimalFormat decimalFormat0 = (DecimalFormat) complexFormat0.getImaginaryFormat();
        decimalFormat0.toLocalizedPattern();
    }

    @Test(timeout = 4000)
    public void test0913() throws Throwable {
        NumberFormat numberFormat0 = NumberFormat.getIntegerInstance();
        ComplexFormat complexFormat0 = new ComplexFormat(numberFormat0, numberFormat0);
        numberFormat0.setMaximumIntegerDigits((-3646));
        NumberFormat numberFormat1 = complexFormat0.getImaginaryFormat();
    }

    @Test(timeout = 4000)
    public void test1014() throws Throwable {
        ComplexFormat complexFormat0 = new ComplexFormat();
        Long long0 = new Long(519L);
        StringBuffer stringBuffer0 = new StringBuffer(":+~\"y|k\"vv&?kK:Me");
        FieldPosition fieldPosition0 = new FieldPosition((Format.Field) null);
        complexFormat0.format((Object) long0, stringBuffer0, fieldPosition0);
        stringBuffer0.toString();
    }

    @Test(timeout = 4000)
    public void test1015() throws Throwable {
        ComplexFormat complexFormat0 = new ComplexFormat();
        Long long0 = new Long(519L);
        StringBuffer stringBuffer0 = new StringBuffer(":+~\"y|k\"vv&?kK:Me");
        FieldPosition fieldPosition0 = new FieldPosition((Format.Field) null);
        complexFormat0.format((Object) long0, stringBuffer0, fieldPosition0);
        stringBuffer0.length();
    }

    @Test(timeout = 4000)
    public void test1116() throws Throwable {
        ComplexFormat complexFormat0 = ComplexFormat.getInstance();
        ParsePosition parsePosition0 = new ParsePosition((-859));
        complexFormat0.parseObject("org.apache.commons.math.complex.Complex", parsePosition0);
    }

    @Test(timeout = 4000)
    public void test1217() throws Throwable {
        DecimalFormat decimalFormat0 = new DecimalFormat();
        ComplexFormat complexFormat0 = new ComplexFormat(decimalFormat0);
        complexFormat0.parseObject("gh9~[{+lL", (ParsePosition) null);
    }

    @Test(timeout = 4000)
    public void test1318() throws Throwable {
        ComplexFormat complexFormat0 = ComplexFormat.getInstance();
        ParsePosition parsePosition0 = new ParsePosition((-3316));
        complexFormat0.parse("", parsePosition0);
    }

    @Test(timeout = 4000)
    public void test1419() throws Throwable {
        ComplexFormat complexFormat0 = new ComplexFormat();
        ParsePosition parsePosition0 = new ParsePosition((-3226));
        complexFormat0.parse((String) null, parsePosition0);
    }

    @Test(timeout = 4000)
    public void test1520() throws Throwable {
        NumberFormat numberFormat0 = NumberFormat.getCurrencyInstance();
        ComplexFormat complexFormat0 = new ComplexFormat("&5%nG*9%D1/z|4?}@s", numberFormat0, numberFormat0);
        complexFormat0.parse((String) null);
    }

    @Test(timeout = 4000)
    public void test1621() throws Throwable {
        ComplexFormat.getInstance((Locale) null);
    }

    @Test(timeout = 4000)
    public void test1722() throws Throwable {
        ComplexFormat complexFormat0 = new ComplexFormat();
        Complex complex0 = Complex.NaN;
        complexFormat0.format(complex0, (StringBuffer) null, (FieldPosition) null);
    }

    @Test(timeout = 4000)
    public void test1823() throws Throwable {
        ComplexFormat complexFormat0 = new ComplexFormat();
        ChoiceFormat choiceFormat0 = new ChoiceFormat("h?WLEFGe0z");
        Complex complex0 = new Complex(3136.2983512706714, 3136.2983512706714);
        StringBuffer stringBuffer0 = new StringBuffer();
        FieldPosition fieldPosition0 = new FieldPosition(0);
        complexFormat0.setImaginaryFormat(choiceFormat0);
        complexFormat0.format(complex0, stringBuffer0, fieldPosition0);
    }

    @Test(timeout = 4000)
    public void test1924() throws Throwable {
        ComplexFormat complexFormat0 = ComplexFormat.getInstance();
        Double double0 = new Double(1660.47863057913);
        StringBuffer stringBuffer0 = new StringBuffer((CharSequence) "O _^|oLX&p;8bwmno");
        complexFormat0.format((Object) double0, stringBuffer0, (FieldPosition) null);
    }

    @Test(timeout = 4000)
    public void test2025() throws Throwable {
        ComplexFormat complexFormat0 = new ComplexFormat();
        Locale locale0 = Locale.JAPAN;
        StringBuffer stringBuffer0 = new StringBuffer();
        FieldPosition fieldPosition0 = new FieldPosition((Format.Field) null);
        complexFormat0.format((Object) locale0, stringBuffer0, fieldPosition0);
    }

    @Test(timeout = 4000)
    public void test2126() throws Throwable {
        ComplexFormat complexFormat0 = null;
        complexFormat0 = new ComplexFormat((NumberFormat) null, (NumberFormat) null);
    }

    @Test(timeout = 4000)
    public void test2227() throws Throwable {
        ComplexFormat complexFormat0 = null;
        complexFormat0 = new ComplexFormat((NumberFormat) null);
    }

    @Test(timeout = 4000)
    public void test2328() throws Throwable {
        ComplexFormat complexFormat0 = null;
        complexFormat0 = new ComplexFormat("!e, 'qd$o6Ef/", (NumberFormat) null);
    }

    @Test(timeout = 4000)
    public void test2429() throws Throwable {
        NumberFormat numberFormat0 = NumberFormat.getCurrencyInstance();
        ComplexFormat complexFormat0 = null;
        complexFormat0 = new ComplexFormat("", numberFormat0);
    }

    @Test(timeout = 4000)
    public void test2530() throws Throwable {
        ComplexFormat complexFormat0 = null;
        complexFormat0 = new ComplexFormat("");
    }

    @Test(timeout = 4000)
    public void test2631() throws Throwable {
        ComplexFormat complexFormat0 = ComplexFormat.getInstance();
        complexFormat0.setImaginaryCharacter("imaginaryFormat can not be null.");
        complexFormat0.parse("(Infinity) + (Infinity)i");
    }

    @Test(timeout = 4000)
    public void test2732() throws Throwable {
        ComplexFormat complexFormat0 = ComplexFormat.getInstance();
        ParsePosition parsePosition0 = new ParsePosition(0);
        Complex complex0 = complexFormat0.parse("0", parsePosition0);
        complex0.abs();
    }

    @Test(timeout = 4000)
    public void test2833() throws Throwable {
        ComplexFormat complexFormat0 = ComplexFormat.getInstance();
        ParsePosition parsePosition0 = new ParsePosition(0);
        complexFormat0.parse("(NaN) - 3,737.92i", parsePosition0);
        parsePosition0.getErrorIndex();
    }

    @Test(timeout = 4000)
    public void test2834() throws Throwable {
        ComplexFormat complexFormat0 = ComplexFormat.getInstance();
        ParsePosition parsePosition0 = new ParsePosition(0);
        complexFormat0.parse("(NaN) - 3,737.92i", parsePosition0);
        parsePosition0.toString();
    }

    @Test(timeout = 4000)
    public void test2935() throws Throwable {
        DecimalFormat decimalFormat0 = new DecimalFormat();
        ComplexFormat complexFormat0 = new ComplexFormat("*(7LYW^}'-R#m4)a.$!", decimalFormat0);
        ParsePosition parsePosition0 = new ParsePosition(2);
        complexFormat0.parse("*(7LYW^}'-R#m4)a.$!", parsePosition0);
        parsePosition0.getErrorIndex();
    }

    @Test(timeout = 4000)
    public void test2936() throws Throwable {
        DecimalFormat decimalFormat0 = new DecimalFormat();
        ComplexFormat complexFormat0 = new ComplexFormat("*(7LYW^}'-R#m4)a.$!", decimalFormat0);
        ParsePosition parsePosition0 = new ParsePosition(2);
        complexFormat0.parse("*(7LYW^}'-R#m4)a.$!", parsePosition0);
        parsePosition0.getIndex();
    }

    @Test(timeout = 4000)
    public void test3037() throws Throwable {
        ComplexFormat complexFormat0 = new ComplexFormat();
        Complex complex0 = Complex.ONE;
        AttributedCharacterIterator attributedCharacterIterator0 = complexFormat0.formatToCharacterIterator(complex0);
        attributedCharacterIterator0.getRunLimit();
    }

    @Test(timeout = 4000)
    public void test3138() throws Throwable {
        Complex complex0 = Complex.NaN;
        ComplexFormat complexFormat0 = new ComplexFormat("imaginaryFormat can not be null.");
        StringBuffer stringBuffer0 = new StringBuffer();
        Format.Field format_Field0 = mock(Format.Field.class, new ViolatedAssumptionAnswer());
        FieldPosition fieldPosition0 = new FieldPosition(format_Field0);
        complexFormat0.format(complex0, stringBuffer0, fieldPosition0);
        stringBuffer0.length();
    }

    @Test(timeout = 4000)
    public void test3139() throws Throwable {
        Complex complex0 = Complex.NaN;
        ComplexFormat complexFormat0 = new ComplexFormat("imaginaryFormat can not be null.");
        StringBuffer stringBuffer0 = new StringBuffer();
        Format.Field format_Field0 = mock(Format.Field.class, new ViolatedAssumptionAnswer());
        FieldPosition fieldPosition0 = new FieldPosition(format_Field0);
        complexFormat0.format(complex0, stringBuffer0, fieldPosition0);
        stringBuffer0.toString();
    }

    @Test(timeout = 4000)
    public void test3240() throws Throwable {
        ComplexFormat complexFormat0 = ComplexFormat.getInstance();
        Complex complex0 = Complex.ZERO;
        StringBuffer stringBuffer0 = new StringBuffer((CharSequence) "0");
        Format.Field format_Field0 = mock(Format.Field.class, new ViolatedAssumptionAnswer());
        FieldPosition fieldPosition0 = new FieldPosition(format_Field0, (-1548));
        complexFormat0.format(complex0, stringBuffer0, fieldPosition0);
        stringBuffer0.length();
    }

    @Test(timeout = 4000)
    public void test3241() throws Throwable {
        ComplexFormat complexFormat0 = ComplexFormat.getInstance();
        Complex complex0 = Complex.ZERO;
        StringBuffer stringBuffer0 = new StringBuffer((CharSequence) "0");
        Format.Field format_Field0 = mock(Format.Field.class, new ViolatedAssumptionAnswer());
        FieldPosition fieldPosition0 = new FieldPosition(format_Field0, (-1548));
        complexFormat0.format(complex0, stringBuffer0, fieldPosition0);
        stringBuffer0.toString();
    }

    @Test(timeout = 4000)
    public void test3342() throws Throwable {
        ComplexFormat complexFormat0 = new ComplexFormat();
        Complex complex0 = new Complex((-2589.59), (-1561.60191019));
        StringBuffer stringBuffer0 = new StringBuffer();
        Format.Field format_Field0 = mock(Format.Field.class, new ViolatedAssumptionAnswer());
        FieldPosition fieldPosition0 = new FieldPosition(format_Field0);
        complexFormat0.format(complex0, stringBuffer0, fieldPosition0);
        stringBuffer0.toString();
    }

    @Test(timeout = 4000)
    public void test3343() throws Throwable {
        ComplexFormat complexFormat0 = new ComplexFormat();
        Complex complex0 = new Complex((-2589.59), (-1561.60191019));
        StringBuffer stringBuffer0 = new StringBuffer();
        Format.Field format_Field0 = mock(Format.Field.class, new ViolatedAssumptionAnswer());
        FieldPosition fieldPosition0 = new FieldPosition(format_Field0);
        complexFormat0.format(complex0, stringBuffer0, fieldPosition0);
        stringBuffer0.length();
    }

    @Test(timeout = 4000)
    public void test3444() throws Throwable {
        ComplexFormat complexFormat0 = ComplexFormat.getInstance();
        String string0 = complexFormat0.getImaginaryCharacter();
    }

    @Test(timeout = 4000)
    public void test3545() throws Throwable {
        ComplexFormat complexFormat0 = null;
        complexFormat0 = new ComplexFormat("org.apache.commons.math.complex.Complex", (NumberFormat) null, (NumberFormat) null);
    }

    @Test(timeout = 4000)
    public void test3646() throws Throwable {
        NumberFormat numberFormat0 = NumberFormat.getNumberInstance();
        ComplexFormat complexFormat0 = new ComplexFormat("0", numberFormat0);
        Complex complex0 = complexFormat0.parse("0");
        complex0.getImaginary();
    }

    @Test(timeout = 4000)
    public void test3747() throws Throwable {
        DecimalFormat decimalFormat0 = new DecimalFormat("imaginaryFormat can not be null.");
        ComplexFormat complexFormat0 = new ComplexFormat(decimalFormat0);
        NumberFormat numberFormat0 = complexFormat0.getImaginaryFormat();
    }

    @Test(timeout = 4000)
    public void test3848() throws Throwable {
        ComplexFormat complexFormat0 = new ComplexFormat("Inexact result from rounding");
        complexFormat0.setRealFormat((NumberFormat) null);
    }

    @Test(timeout = 4000)
    public void test3949() throws Throwable {
        ComplexFormat complexFormat0 = new ComplexFormat();
        complexFormat0.setImaginaryFormat((NumberFormat) null);
    }

    @Test(timeout = 4000)
    public void test4050() throws Throwable {
        ComplexFormat complexFormat0 = ComplexFormat.getInstance();
        complexFormat0.setImaginaryCharacter("");
    }

    @Test(timeout = 4000)
    public void test4151() throws Throwable {
        Locale locale0 = Locale.FRENCH;
        ComplexFormat complexFormat0 = ComplexFormat.getInstance(locale0);
        complexFormat0.setImaginaryCharacter((String) null);
    }

    @Test(timeout = 4000)
    public void test4252() throws Throwable {
        ComplexFormat complexFormat0 = ComplexFormat.getInstance();
        Complex complex0 = complexFormat0.parse("(Infinity) + (Infinity)i");
        complex0.getImaginary();
    }

    @Test(timeout = 4000)
    public void test4353() throws Throwable {
        ComplexFormat complexFormat0 = ComplexFormat.getInstance();
        complexFormat0.parseObject(" ");
    }

    @Test(timeout = 4000)
    public void test4454() throws Throwable {
        ComplexFormat complexFormat0 = ComplexFormat.getInstance();
        complexFormat0.parse("30-5 v7n=");
    }

    @Test(timeout = 4000)
    public void test4555() throws Throwable {
        Complex complex0 = Complex.INF;
        String string0 = ComplexFormat.formatComplex(complex0);
        assertNotNull(string0);
    }

    @Test(timeout = 4000)
    public void test4656() throws Throwable {
        ComplexFormat.formatComplex((Complex) null);
    }

    @Test(timeout = 4000)
    public void test4757() throws Throwable {
        Complex complex0 = Complex.NaN;
        ParsePosition parsePosition0 = new ParsePosition(1025);
        ComplexFormat complexFormat0 = new ComplexFormat();
        ComplexFormat.formatComplex(complex0);
        complexFormat0.parse("(NaN) - 1i", parsePosition0);
        parsePosition0.toString();
    }

    @Test(timeout = 4000)
    public void test4758() throws Throwable {
        Complex complex0 = Complex.NaN;
        ParsePosition parsePosition0 = new ParsePosition(1025);
        ComplexFormat complexFormat0 = new ComplexFormat();
        ComplexFormat.formatComplex(complex0);
        complexFormat0.parse("(NaN) - 1i", parsePosition0);
        parsePosition0.getErrorIndex();
    }

    @Test(timeout = 4000)
    public void test4859() throws Throwable {
        ComplexFormat complexFormat0 = ComplexFormat.getInstance();
        ParsePosition parsePosition0 = new ParsePosition(1240);
        complexFormat0.parseObject(".nU#v77@y?64", parsePosition0);
        parsePosition0.toString();
    }

    @Test(timeout = 4000)
    public void test4860() throws Throwable {
        ComplexFormat complexFormat0 = ComplexFormat.getInstance();
        ParsePosition parsePosition0 = new ParsePosition(1240);
        complexFormat0.parseObject(".nU#v77@y?64", parsePosition0);
        parsePosition0.getErrorIndex();
    }

    @Test(timeout = 4000)
    public void test4961() throws Throwable {
        ChoiceFormat choiceFormat0 = new ChoiceFormat("");
        ComplexFormat complexFormat0 = new ComplexFormat(choiceFormat0, choiceFormat0);
        Integer integer0 = new Integer(28);
        StringBuffer stringBuffer0 = new StringBuffer("(-Infinity) + (Infinity)i");
        Format.Field format_Field0 = mock(Format.Field.class, new ViolatedAssumptionAnswer());
        FieldPosition fieldPosition0 = new FieldPosition(format_Field0, 28);
        complexFormat0.format((Object) integer0, stringBuffer0, fieldPosition0);
    }

    @Test(timeout = 4000)
    public void test5062() throws Throwable {
        ComplexFormat complexFormat0 = new ComplexFormat();
        complexFormat0.parse("1+Bd[NGA]aV@tdN\"3");
    }
}
