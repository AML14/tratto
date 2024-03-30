/*
 * This file was automatically generated by EvoSuite
 * Mon Oct 16 23:56:58 GMT 2023
 */
package org.apache.commons.math.fraction;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.text.ChoiceFormat;
import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Locale;
import org.apache.commons.math.fraction.Fraction;
import org.apache.commons.math.fraction.FractionFormat;
import org.apache.commons.math.fraction.ProperFractionFormat;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class ProperFractionFormat_ESTest extends ProperFractionFormat_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        ChoiceFormat choiceFormat0 = new ChoiceFormat("Cannot convert given object to a fraction.");
        ProperFractionFormat properFractionFormat0 = new ProperFractionFormat(choiceFormat0);
        ParsePosition parsePosition0 = new ParsePosition(32);
        properFractionFormat0.parse("Cannot convert given object to a fraction.", parsePosition0);
        parsePosition0.toString();
    }

    @Test(timeout = 4000)
    public void test001() throws Throwable {
        ChoiceFormat choiceFormat0 = new ChoiceFormat("Cannot convert given object to a fraction.");
        ProperFractionFormat properFractionFormat0 = new ProperFractionFormat(choiceFormat0);
        ParsePosition parsePosition0 = new ParsePosition(32);
        properFractionFormat0.parse("Cannot convert given object to a fraction.", parsePosition0);
        parsePosition0.getErrorIndex();
    }

    @Test(timeout = 4000)
    public void test012() throws Throwable {
        Locale locale0 = Locale.PRC;
        FractionFormat fractionFormat0 = FractionFormat.getProperInstance(locale0);
        StringBuffer stringBuffer0 = new StringBuffer((CharSequence) "");
        Format.Field format_Field0 = mock(Format.Field.class, new ViolatedAssumptionAnswer());
        FieldPosition fieldPosition0 = new FieldPosition(format_Field0, 0);
        Fraction fraction0 = new Fraction(0);
        fractionFormat0.format(fraction0, stringBuffer0, fieldPosition0);
        stringBuffer0.toString();
    }

    @Test(timeout = 4000)
    public void test013() throws Throwable {
        Locale locale0 = Locale.PRC;
        FractionFormat fractionFormat0 = FractionFormat.getProperInstance(locale0);
        StringBuffer stringBuffer0 = new StringBuffer((CharSequence) "");
        Format.Field format_Field0 = mock(Format.Field.class, new ViolatedAssumptionAnswer());
        FieldPosition fieldPosition0 = new FieldPosition(format_Field0, 0);
        Fraction fraction0 = new Fraction(0);
        fractionFormat0.format(fraction0, stringBuffer0, fieldPosition0);
        stringBuffer0.length();
    }

    @Test(timeout = 4000)
    public void test024() throws Throwable {
        Locale locale0 = Locale.CANADA_FRENCH;
        NumberFormat numberFormat0 = NumberFormat.getIntegerInstance(locale0);
        ProperFractionFormat properFractionFormat0 = new ProperFractionFormat(numberFormat0, numberFormat0, numberFormat0);
        Fraction fraction0 = new Fraction((-4438.5648678));
        Format.Field format_Field0 = mock(Format.Field.class, new ViolatedAssumptionAnswer());
        FieldPosition fieldPosition0 = new FieldPosition(format_Field0, 1902);
        properFractionFormat0.format(fraction0, (StringBuffer) null, fieldPosition0);
    }

    @Test(timeout = 4000)
    public void test035() throws Throwable {
        DecimalFormat decimalFormat0 = new DecimalFormat();
        ProperFractionFormat properFractionFormat0 = new ProperFractionFormat(decimalFormat0);
        ParsePosition parsePosition0 = new ParsePosition(0);
        Fraction fraction0 = properFractionFormat0.parse("-2", parsePosition0);
        fraction0.floatValue();
    }

    @Test(timeout = 4000)
    public void test046() throws Throwable {
        NumberFormat numberFormat0 = NumberFormat.getInstance();
        ProperFractionFormat properFractionFormat0 = new ProperFractionFormat(numberFormat0);
        ParsePosition parsePosition0 = new ParsePosition(1);
        Fraction fraction0 = properFractionFormat0.parse("-686", parsePosition0);
        fraction0.longValue();
    }

    @Test(timeout = 4000)
    public void test057() throws Throwable {
        ProperFractionFormat properFractionFormat0 = new ProperFractionFormat();
        DecimalFormat decimalFormat0 = new DecimalFormat("X]Z\u0005");
        decimalFormat0.setMaximumIntegerDigits((-1));
        properFractionFormat0.setWholeFormat(decimalFormat0);
        NumberFormat numberFormat0 = properFractionFormat0.getWholeFormat();
        numberFormat0.getMinimumIntegerDigits();
    }

    @Test(timeout = 4000)
    public void test068() throws Throwable {
        NumberFormat numberFormat0 = NumberFormat.getCurrencyInstance();
        ProperFractionFormat properFractionFormat0 = new ProperFractionFormat(numberFormat0);
        NumberFormat numberFormat1 = properFractionFormat0.getWholeFormat();
    }

    @Test(timeout = 4000)
    public void test079() throws Throwable {
        NumberFormat numberFormat0 = NumberFormat.getIntegerInstance();
        ProperFractionFormat properFractionFormat0 = new ProperFractionFormat(numberFormat0);
        ParsePosition parsePosition0 = new ParsePosition(Integer.MIN_VALUE);
        properFractionFormat0.parse("]p~+@BV1@", parsePosition0);
    }

    @Test(timeout = 4000)
    public void test0810() throws Throwable {
        Locale locale0 = Locale.GERMAN;
        NumberFormat numberFormat0 = NumberFormat.getNumberInstance(locale0);
        ProperFractionFormat properFractionFormat0 = new ProperFractionFormat(numberFormat0, numberFormat0, numberFormat0);
        ParsePosition parsePosition0 = new ParsePosition(0);
        properFractionFormat0.parse((String) null, parsePosition0);
    }

    @Test(timeout = 4000)
    public void test0911() throws Throwable {
        ProperFractionFormat properFractionFormat0 = null;
        properFractionFormat0 = new ProperFractionFormat((NumberFormat) null, (NumberFormat) null, (NumberFormat) null);
    }

    @Test(timeout = 4000)
    public void test1012() throws Throwable {
        ProperFractionFormat properFractionFormat0 = null;
        properFractionFormat0 = new ProperFractionFormat((NumberFormat) null);
    }

    @Test(timeout = 4000)
    public void test1113() throws Throwable {
        NumberFormat numberFormat0 = NumberFormat.getIntegerInstance();
        ProperFractionFormat properFractionFormat0 = new ProperFractionFormat(numberFormat0);
        NumberFormat numberFormat1 = properFractionFormat0.getWholeFormat();
    }

    @Test(timeout = 4000)
    public void test1214() throws Throwable {
        NumberFormat numberFormat0 = NumberFormat.getNumberInstance();
        ProperFractionFormat properFractionFormat0 = new ProperFractionFormat(numberFormat0, numberFormat0, numberFormat0);
        if ((NumberFormat) null == null) {
            try {
                properFractionFormat0.setWholeFormat((NumberFormat) null);
                fail();
            } catch (java.lang.IllegalArgumentException e) {
                // Successfully thrown exception
            }
        } else {
            properFractionFormat0.setWholeFormat((NumberFormat) null);
        }
    }

    @Test(timeout = 4000)
    public void test1315() throws Throwable {
        ProperFractionFormat properFractionFormat0 = new ProperFractionFormat();
        ParsePosition parsePosition0 = new ParsePosition(0);
        properFractionFormat0.parse("1", parsePosition0);
        properFractionFormat0.parse("#0=A,~N]C", parsePosition0);
        parsePosition0.toString();
    }

    @Test(timeout = 4000)
    public void test1316() throws Throwable {
        ProperFractionFormat properFractionFormat0 = new ProperFractionFormat();
        ParsePosition parsePosition0 = new ParsePosition(0);
        properFractionFormat0.parse("1", parsePosition0);
        properFractionFormat0.parse("#0=A,~N]C", parsePosition0);
        parsePosition0.getIndex();
    }

    @Test(timeout = 4000)
    public void test1417() throws Throwable {
        ProperFractionFormat properFractionFormat0 = new ProperFractionFormat();
        ParsePosition parsePosition0 = new ParsePosition(0);
        Fraction fraction0 = properFractionFormat0.parse("1", parsePosition0);
        StringBuffer stringBuffer0 = new StringBuffer((CharSequence) "#0=A,~N]C");
        FieldPosition fieldPosition0 = new FieldPosition((-4438));
        properFractionFormat0.format(fraction0, stringBuffer0, fieldPosition0);
        stringBuffer0.length();
    }

    @Test(timeout = 4000)
    public void test1418() throws Throwable {
        ProperFractionFormat properFractionFormat0 = new ProperFractionFormat();
        ParsePosition parsePosition0 = new ParsePosition(0);
        Fraction fraction0 = properFractionFormat0.parse("1", parsePosition0);
        StringBuffer stringBuffer0 = new StringBuffer((CharSequence) "#0=A,~N]C");
        FieldPosition fieldPosition0 = new FieldPosition((-4438));
        properFractionFormat0.format(fraction0, stringBuffer0, fieldPosition0);
        stringBuffer0.toString();
    }

    @Test(timeout = 4000)
    public void test1519() throws Throwable {
        ChoiceFormat choiceFormat0 = new ChoiceFormat("");
        ProperFractionFormat properFractionFormat0 = new ProperFractionFormat(choiceFormat0, choiceFormat0, choiceFormat0);
        ParsePosition parsePosition0 = new ParsePosition(3023);
        Fraction fraction0 = properFractionFormat0.parse("R", parsePosition0);
        StringBuffer stringBuffer0 = new StringBuffer((CharSequence) "");
        Format.Field format_Field0 = mock(Format.Field.class, new ViolatedAssumptionAnswer());
        FieldPosition fieldPosition0 = new FieldPosition(format_Field0, 3023);
        properFractionFormat0.format(fraction0, stringBuffer0, fieldPosition0);
    }

    @Test(timeout = 4000)
    public void test1620() throws Throwable {
        ProperFractionFormat properFractionFormat0 = new ProperFractionFormat();
        ParsePosition parsePosition0 = new ParsePosition(0);
        properFractionFormat0.parse("j", parsePosition0);
        parsePosition0.toString();
    }

    @Test(timeout = 4000)
    public void test1621() throws Throwable {
        ProperFractionFormat properFractionFormat0 = new ProperFractionFormat();
        ParsePosition parsePosition0 = new ParsePosition(0);
        properFractionFormat0.parse("j", parsePosition0);
        parsePosition0.getIndex();
    }
}
