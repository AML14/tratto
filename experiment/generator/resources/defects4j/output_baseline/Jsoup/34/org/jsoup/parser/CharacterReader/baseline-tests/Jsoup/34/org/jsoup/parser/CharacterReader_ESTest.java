/*
 * This file was automatically generated by EvoSuite
 * Sun Oct 15 21:28:15 GMT 2023
 */
package org.jsoup.parser;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.nio.CharBuffer;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.jsoup.parser.CharacterReader;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class CharacterReader_ESTest extends CharacterReader_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test0000() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("0}nq");
        boolean boolean0 = characterReader0.matchesDigit();
    }

    @Test(timeout = 4000)
    public void test0011() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("Et(z(Rnu");
        String string0 = characterReader0.consumeLetterThenDigitSequence();
    }

    @Test(timeout = 4000)
    public void test0012() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("Et(z(Rnu");
        String string0 = characterReader0.consumeLetterThenDigitSequence();
        String string1 = characterReader0.consumeAsString();
    }

    @Test(timeout = 4000)
    public void test0013() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("Et(z(Rnu");
        String string0 = characterReader0.consumeLetterThenDigitSequence();
        String string1 = characterReader0.consumeAsString();
        boolean boolean0 = characterReader0.matchesLetter();
    }

    @Test(timeout = 4000)
    public void test0024() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("org.jsoup.parser.CharacterReader");
        char[] charArray0 = new char[3];
        charArray0[0] = 'a';
        String string0 = characterReader0.consumeToAny(charArray0);
    }

    @Test(timeout = 4000)
    public void test0025() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("org.jsoup.parser.CharacterReader");
        char[] charArray0 = new char[3];
        charArray0[0] = 'a';
        String string0 = characterReader0.consumeToAny(charArray0);
        boolean boolean0 = characterReader0.matchesLetter();
    }

    @Test(timeout = 4000)
    public void test0036() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("Ze)xJ~");
        boolean boolean0 = characterReader0.matchesLetter();
    }

    @Test(timeout = 4000)
    public void test0047() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("EI.Twzz%y<+s,,Q");
        char[] charArray0 = new char[7];
        charArray0[2] = 's';
        boolean boolean0 = characterReader0.matchesAny(charArray0);
    }

    @Test(timeout = 4000)
    public void test0058() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("org.jsoup.parser.CharacterReader");
        String string0 = characterReader0.consumeLetterSequence();
    }

    @Test(timeout = 4000)
    public void test0059() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("org.jsoup.parser.CharacterReader");
        String string0 = characterReader0.consumeLetterSequence();
        boolean boolean0 = characterReader0.matchConsumeIgnoreCase("6IfE:SX");
    }

    @Test(timeout = 4000)
    public void test00610() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("|ds69`ej2YZtm_``");
        boolean boolean0 = characterReader0.matchConsume("");
    }

    @Test(timeout = 4000)
    public void test00711() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("KHD,HZm?C");
        String string0 = characterReader0.consumeLetterSequence();
    }

    @Test(timeout = 4000)
    public void test00712() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("KHD,HZm?C");
        String string0 = characterReader0.consumeLetterSequence();
        boolean boolean0 = characterReader0.matches('D');
    }

    @Test(timeout = 4000)
    public void test00813() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("9%=$k@-(Q");
        String string0 = characterReader0.consumeDigitSequence();
    }

    @Test(timeout = 4000)
    public void test00914() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("0&?F`N,5");
        String string0 = characterReader0.consumeHexSequence();
    }

    @Test(timeout = 4000)
    public void test00915() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("0&?F`N,5");
        String string0 = characterReader0.consumeHexSequence();
        characterReader0.unconsume();
        String string1 = characterReader0.consumeDigitSequence();
    }

    @Test(timeout = 4000)
    public void test01016() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader(")S8f(:");
        characterReader0.consume();
        char char0 = characterReader0.consume();
    }

    @Test(timeout = 4000)
    public void test01017() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader(")S8f(:");
        characterReader0.consume();
        char char0 = characterReader0.consume();
        String string0 = characterReader0.consumeHexSequence();
    }

    @Test(timeout = 4000)
    public void test01118() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("C7E1F4:37*xE]Cd\u0000");
        String string0 = characterReader0.consumeHexSequence();
    }

    @Test(timeout = 4000)
    public void test01219() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("9ZJsKVI<3NND;U%w");
        String string0 = characterReader0.consumeHexSequence();
    }

    @Test(timeout = 4000)
    public void test01320() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("lK&=alfR,Eu90~w`");
        String string0 = characterReader0.consumeTo('9');
    }

    @Test(timeout = 4000)
    public void test01321() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("lK&=alfR,Eu90~w`");
        String string0 = characterReader0.consumeTo('9');
        String string1 = characterReader0.consumeLetterThenDigitSequence();
    }

    @Test(timeout = 4000)
    public void test01422() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("zep");
        String string0 = characterReader0.consumeLetterThenDigitSequence();
    }

    @Test(timeout = 4000)
    public void test01523() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("org.jsoup.parser.CharacterReader");
        String string0 = characterReader0.consumeTo('a');
    }

    @Test(timeout = 4000)
    public void test01524() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("org.jsoup.parser.CharacterReader");
        String string0 = characterReader0.consumeTo('a');
        String string1 = characterReader0.consumeLetterThenDigitSequence();
    }

    @Test(timeout = 4000)
    public void test01625() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("KHD.,HZm?C");
        characterReader0.consumeLetterSequence();
        characterReader0.advance();
        char char0 = characterReader0.consume();
    }

    @Test(timeout = 4000)
    public void test01626() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("KHD.,HZm?C");
        characterReader0.consumeLetterSequence();
        characterReader0.advance();
        char char0 = characterReader0.consume();
        String string0 = characterReader0.consumeLetterThenDigitSequence();
    }

    @Test(timeout = 4000)
    public void test01727() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("qJA1:iAK");
        String string0 = characterReader0.consumeLetterThenDigitSequence();
    }

    @Test(timeout = 4000)
    public void test01828() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("EI.Twzz%y<+s,,Q");
        char[] charArray0 = new char[7];
        charArray0[1] = '.';
        String string0 = characterReader0.consumeToAny(charArray0);
    }

    @Test(timeout = 4000)
    public void test01829() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("EI.Twzz%y<+s,,Q");
        char[] charArray0 = new char[7];
        charArray0[1] = '.';
        String string0 = characterReader0.consumeToAny(charArray0);
        characterReader0.consume();
        char char0 = characterReader0.consume();
    }

    @Test(timeout = 4000)
    public void test01830() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("EI.Twzz%y<+s,,Q");
        char[] charArray0 = new char[7];
        charArray0[1] = '.';
        String string0 = characterReader0.consumeToAny(charArray0);
        characterReader0.consume();
        char char0 = characterReader0.consume();
        String string1 = characterReader0.consumeLetterSequence();
    }

    @Test(timeout = 4000)
    public void test01931() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("ZC78@@uY");
        String string0 = characterReader0.consumeLetterSequence();
    }

    @Test(timeout = 4000)
    public void test02032() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("");
        char[] charArray0 = new char[4];
        characterReader0.consume();
        String string0 = characterReader0.consumeToAny(charArray0);
    }

    @Test(timeout = 4000)
    public void test02133() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("Et(z(Rnu");
        String string0 = characterReader0.consumeTo("(");
    }

    @Test(timeout = 4000)
    public void test02234() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("0}nq");
        String string0 = characterReader0.consumeHexSequence();
    }

    @Test(timeout = 4000)
    public void test02235() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("0}nq");
        String string0 = characterReader0.consumeHexSequence();
        characterReader0.advance();
        String string1 = characterReader0.consumeLetterThenDigitSequence();
    }

    @Test(timeout = 4000)
    public void test02236() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("0}nq");
        String string0 = characterReader0.consumeHexSequence();
        characterReader0.advance();
        String string1 = characterReader0.consumeLetterThenDigitSequence();
        boolean boolean0 = characterReader0.containsIgnoreCase("6+<SO]1CEx]vC4+W#$");
    }

    @Test(timeout = 4000)
    public void test02337() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("EI.Twzz%y<+s,,Q");
        String string0 = characterReader0.toString();
    }

    @Test(timeout = 4000)
    public void test02438() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("0}nq");
        characterReader0.consumeHexSequence();
        int int0 = characterReader0.pos();
    }

    @Test(timeout = 4000)
    public void test02539() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("");
        characterReader0.unconsume();
        int int0 = characterReader0.pos();
    }

    @Test(timeout = 4000)
    public void test02640() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("org.jsoup.parser.CharacterReader");
        int int0 = characterReader0.nextIndexOf("org.jsoup.parser.CharacterReader");
    }

    @Test(timeout = 4000)
    public void test02741() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("org.jsoup.parser.CharacterReader");
        char char0 = characterReader0.consume();
    }

    @Test(timeout = 4000)
    public void test02742() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("org.jsoup.parser.CharacterReader");
        char char0 = characterReader0.consume();
        String string0 = characterReader0.consumeAsString();
    }

    @Test(timeout = 4000)
    public void test02743() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("org.jsoup.parser.CharacterReader");
        char char0 = characterReader0.consume();
        String string0 = characterReader0.consumeAsString();
        int int0 = characterReader0.nextIndexOf("r");
    }

    @Test(timeout = 4000)
    public void test02844() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("org.jsoup.parser.CharacterReader");
        characterReader0.consumeLetterSequence();
        int int0 = characterReader0.nextIndexOf('.');
    }

    @Test(timeout = 4000)
    public void test02945() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("lK&=alfR,Eu90~w`");
        int int0 = characterReader0.nextIndexOf('9');
    }

    @Test(timeout = 4000)
    public void test03047() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("Et(z(Rnu");
        String string0 = characterReader0.consumeLetterThenDigitSequence();
        int int0 = characterReader0.nextIndexOf('E');
    }

    @Test(timeout = 4000)
    public void test03148() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("Et(z(Rnu");
        boolean boolean0 = characterReader0.matchesIgnoreCase("");
    }

    @Test(timeout = 4000)
    public void test03249() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("PbI");
        boolean boolean0 = characterReader0.matchesIgnoreCase("org.jsoup.parser.CharacterReader");
    }

    @Test(timeout = 4000)
    public void test03350() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("*&7Dapd?]");
        characterReader0.consumeTo('X');
        boolean boolean0 = characterReader0.isEmpty();
    }

    @Test(timeout = 4000)
    public void test03451() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("7Q @O|WEc");
        characterReader0.advance();
        characterReader0.unconsume();
        char char0 = characterReader0.current();
    }

    @Test(timeout = 4000)
    public void test03552() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("Et(z(Rnu");
        char char0 = characterReader0.current();
    }

    @Test(timeout = 4000)
    public void test03653() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("Array must not contain any null objects");
        String string0 = characterReader0.consumeToEnd();
    }

    @Test(timeout = 4000)
    public void test03754() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("Array must not contain any null objects");
        char[] charArray0 = new char[0];
        String string0 = characterReader0.consumeToAny(charArray0);
    }

    @Test(timeout = 4000)
    public void test03755() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("Array must not contain any null objects");
        char[] charArray0 = new char[0];
        String string0 = characterReader0.consumeToAny(charArray0);
        String string1 = characterReader0.consumeToEnd();
    }

    @Test(timeout = 4000)
    public void test03856() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("50>3?97~c)sY>=FLQ]");
        char char0 = characterReader0.consume();
    }

    @Test(timeout = 4000)
    public void test03957() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("Et(z(Rnu");
        characterReader0.nextIndexOf("");
    }

    @Test(timeout = 4000)
    public void test04058() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("org.jsoup.parser.CharacterReader");
        characterReader0.nextIndexOf((CharSequence) null);
    }

    @Test(timeout = 4000)
    public void test04159() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("Array must not contain any null objects");
        char[] charArray0 = new char[0];
        CharBuffer charBuffer0 = CharBuffer.wrap(charArray0);
        characterReader0.nextIndexOf(charBuffer0);
    }

    @Test(timeout = 4000)
    public void test04260() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("org.jsoup.parser.CharacterReader");
        characterReader0.nextIndexOf("r/SO{");
    }

    @Test(timeout = 4000)
    public void test04361() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("");
        characterReader0.unconsume();
        characterReader0.matchesLetter();
    }

    @Test(timeout = 4000)
    public void test04462() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("");
        characterReader0.matchesIgnoreCase((String) null);
    }

    @Test(timeout = 4000)
    public void test04563() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("<");
        characterReader0.unconsume();
        characterReader0.matchesIgnoreCase("<");
    }

    @Test(timeout = 4000)
    public void test04664() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("2$\"4\"!NJW,?j f;");
        characterReader0.unconsume();
        characterReader0.matchesDigit();
    }

    @Test(timeout = 4000)
    public void test04765() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("uC_^N/{u$i8{P!u");
        characterReader0.matchesAny((char[]) null);
    }

    @Test(timeout = 4000)
    public void test04866() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("");
        char[] charArray0 = new char[9];
        characterReader0.unconsume();
        characterReader0.matchesAny(charArray0);
    }

    @Test(timeout = 4000)
    public void test04967() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("Object must not be null");
        characterReader0.matches((String) null);
    }

    @Test(timeout = 4000)
    public void test05068() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("cB$AApvF ");
        characterReader0.unconsume();
        characterReader0.matches(",#");
    }

    @Test(timeout = 4000)
    public void test05169() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("");
        characterReader0.unconsume();
        characterReader0.matches('i');
    }

    @Test(timeout = 4000)
    public void test05270() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("zb,~JuC>pm");
        characterReader0.matchConsumeIgnoreCase((String) null);
    }

    @Test(timeout = 4000)
    public void test05371() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("eEs`5kFdI~^pqIY}s");
        characterReader0.unconsume();
        characterReader0.matchConsumeIgnoreCase("}Z");
    }

    @Test(timeout = 4000)
    public void test05472() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("");
        characterReader0.matchConsume((String) null);
    }

    @Test(timeout = 4000)
    public void test05573() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("org.jsoup.parser.CharacterReader");
        characterReader0.unconsume();
        characterReader0.matchConsume("r/SO{");
    }

    @Test(timeout = 4000)
    public void test05674() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("");
        characterReader0.unconsume();
        characterReader0.current();
    }

    @Test(timeout = 4000)
    public void test05775() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("1h{x");
        characterReader0.containsIgnoreCase("");
    }

    @Test(timeout = 4000)
    public void test05876() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("Tw?L]Vo");
        characterReader0.containsIgnoreCase((String) null);
    }

    @Test(timeout = 4000)
    public void test05977() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("eEs`5kFdI~^pqIY}s");
        characterReader0.unconsume();
        characterReader0.containsIgnoreCase("eEs`5kFdI~^pqIY}s");
    }

    @Test(timeout = 4000)
    public void test06078() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("50>3?97~c)sY>=FLQ]");
        characterReader0.consumeTo('q');
        characterReader0.consume();
        characterReader0.consumeToEnd();
    }

    @Test(timeout = 4000)
    public void test06179() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("2$\"4\"!NJW,?j f;");
        characterReader0.unconsume();
        char[] charArray0 = new char[0];
        characterReader0.consumeToAny(charArray0);
    }

    @Test(timeout = 4000)
    public void test06280() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("F gax#WClg8&##;48H");
        characterReader0.consumeToAny((char[]) null);
    }

    @Test(timeout = 4000)
    public void test06381() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("zb,~JuC>pm");
        char[] charArray0 = new char[3];
        characterReader0.unconsume();
        characterReader0.consumeToAny(charArray0);
    }

    @Test(timeout = 4000)
    public void test06482() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("7Q @O|WEc");
        characterReader0.consumeTo("");
    }

    @Test(timeout = 4000)
    public void test06583() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("|ds69`ej2YZtm_``");
        characterReader0.consumeTo((String) null);
    }

    @Test(timeout = 4000)
    public void test06684() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("");
        characterReader0.advance();
        characterReader0.consumeTo('2');
    }

    @Test(timeout = 4000)
    public void test06785() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("");
        characterReader0.unconsume();
        characterReader0.consumeTo('!');
    }

    @Test(timeout = 4000)
    public void test06886() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader(";OJj}O7WvUdg'D*T<\"n");
        characterReader0.matchConsume(";OJj}O7WvUdg'D*T<\"n");
        characterReader0.consume();
        characterReader0.consumeLetterThenDigitSequence();
    }

    @Test(timeout = 4000)
    public void test06987() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("");
        characterReader0.unconsume();
        characterReader0.consumeLetterThenDigitSequence();
    }

    @Test(timeout = 4000)
    public void test07088() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("");
        characterReader0.advance();
        characterReader0.consumeLetterSequence();
    }

    @Test(timeout = 4000)
    public void test07189() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("");
        characterReader0.unconsume();
        characterReader0.consumeLetterSequence();
    }

    @Test(timeout = 4000)
    public void test07290() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("}");
        characterReader0.unconsume();
        characterReader0.consumeHexSequence();
    }

    @Test(timeout = 4000)
    public void test07391() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("*&7Dapd?]");
        characterReader0.consumeTo('X');
        characterReader0.consume();
        characterReader0.consumeDigitSequence();
    }

    @Test(timeout = 4000)
    public void test07492() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("<.b,KILVa");
        characterReader0.unconsume();
        characterReader0.consumeDigitSequence();
    }

    @Test(timeout = 4000)
    public void test07593() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("");
        characterReader0.consumeAsString();
    }

    @Test(timeout = 4000)
    public void test07694() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("");
        characterReader0.unconsume();
        characterReader0.consume();
    }

    @Test(timeout = 4000)
    public void test07795() throws Throwable {
        CharacterReader characterReader0 = null;
        characterReader0 = new CharacterReader((String) null);
    }

    @Test(timeout = 4000)
    public void test07896() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("2\"$\"4\"!NJW,?j f;");
        boolean boolean0 = characterReader0.containsIgnoreCase("N");
    }

    @Test(timeout = 4000)
    public void test07997() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("7Q @O|WEc");
        characterReader0.advance();
        boolean boolean0 = characterReader0.matchesDigit();
    }

    @Test(timeout = 4000)
    public void test08099() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("Array must not contain any null objects");
        char[] charArray0 = new char[0];
        String string0 = characterReader0.consumeToAny(charArray0);
        boolean boolean0 = characterReader0.matchesDigit();
    }

    @Test(timeout = 4000)
    public void test081100() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("Array must not contain any null objects");
        boolean boolean0 = characterReader0.matchesLetter();
    }

    @Test(timeout = 4000)
    public void test082101() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("EI.Twzz%y<+s,,Q");
        char[] charArray0 = new char[7];
        charArray0[1] = '.';
        characterReader0.consume();
        char char0 = characterReader0.consume();
    }

    @Test(timeout = 4000)
    public void test082102() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("EI.Twzz%y<+s,,Q");
        char[] charArray0 = new char[7];
        charArray0[1] = '.';
        characterReader0.consume();
        char char0 = characterReader0.consume();
        boolean boolean0 = characterReader0.matchesAny(charArray0);
    }

    @Test(timeout = 4000)
    public void test083103() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("KHD,HZm?C");
        characterReader0.consumeLetterSequence();
        boolean boolean0 = characterReader0.matchConsumeIgnoreCase(",HZm?C");
    }

    @Test(timeout = 4000)
    public void test084104() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("Array must not contain any null objects");
        String string0 = characterReader0.consumeHexSequence();
    }

    @Test(timeout = 4000)
    public void test084105() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("Array must not contain any null objects");
        String string0 = characterReader0.consumeHexSequence();
        boolean boolean0 = characterReader0.matchConsumeIgnoreCase("Array must not contain any null objects");
    }

    @Test(timeout = 4000)
    public void test085106() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("KHD,HZm?C");
        boolean boolean0 = characterReader0.matchConsumeIgnoreCase(",HZm?C");
    }

    @Test(timeout = 4000)
    public void test086107() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("org.jsoup.parser.CharacterReader");
        boolean boolean0 = characterReader0.matchConsume("r/SO{");
    }

    @Test(timeout = 4000)
    public void test087109() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("Array must not contain any null objects");
        char[] charArray0 = new char[0];
        String string0 = characterReader0.consumeToAny(charArray0);
        boolean boolean0 = characterReader0.matches("Must be true");
    }

    @Test(timeout = 4000)
    public void test088110() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("Array must not contain any null objects");
        boolean boolean0 = characterReader0.matches("Array must not contain any null objects");
    }

    @Test(timeout = 4000)
    public void test089112() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("org.jsoup.parser.CharacterReader");
        String string0 = characterReader0.consumeTo('a');
        characterReader0.unconsume();
        characterReader0.advance();
        char char0 = characterReader0.consume();
    }

    @Test(timeout = 4000)
    public void test089113() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("org.jsoup.parser.CharacterReader");
        String string0 = characterReader0.consumeTo('a');
        characterReader0.unconsume();
        characterReader0.advance();
        char char0 = characterReader0.consume();
        characterReader0.unconsume();
        boolean boolean0 = characterReader0.matches('a');
    }

    @Test(timeout = 4000)
    public void test090114() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("org.jsoup.parser.CharacterReader");
        boolean boolean0 = characterReader0.matches('a');
    }

    @Test(timeout = 4000)
    public void test091116() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("Array must not contain any null objects");
        char[] charArray0 = new char[0];
        String string0 = characterReader0.consumeToAny(charArray0);
        boolean boolean0 = characterReader0.matches('m');
    }

    @Test(timeout = 4000)
    public void test092117() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("2\"$\"4\"!NJW,?j f;");
        String string0 = characterReader0.consumeDigitSequence();
    }

    @Test(timeout = 4000)
    public void test092118() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("2\"$\"4\"!NJW,?j f;");
        String string0 = characterReader0.consumeDigitSequence();
        boolean boolean0 = characterReader0.matchesDigit();
    }

    @Test(timeout = 4000)
    public void test093120() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("EI.Twzz%y<+s,,Q");
        char[] charArray0 = new char[7];
        charArray0[1] = '.';
        String string0 = characterReader0.consumeToAny(charArray0);
        String string1 = characterReader0.consumeDigitSequence();
    }

    @Test(timeout = 4000)
    public void test094121() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("Array must not contain any null objects");
        String string0 = characterReader0.consumeDigitSequence();
    }

    @Test(timeout = 4000)
    public void test095123() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("org.jsoup.parser.CharacterReader");
        String string0 = characterReader0.consumeTo('a');
        String string1 = characterReader0.consumeTo("org.jsoup.p");
    }

    @Test(timeout = 4000)
    public void test095124() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("org.jsoup.parser.CharacterReader");
        String string0 = characterReader0.consumeTo('a');
        String string1 = characterReader0.consumeTo("org.jsoup.p");
        String string2 = characterReader0.consumeDigitSequence();
        string2.equals((Object) string1);
    }

    @Test(timeout = 4000)
    public void test096125() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("org.jsoup.parser.CharacterReader");
        char[] charArray0 = new char[8];
        charArray0[5] = 'a';
        String string0 = characterReader0.consumeToAny(charArray0);
    }

    @Test(timeout = 4000)
    public void test096126() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("org.jsoup.parser.CharacterReader");
        char[] charArray0 = new char[8];
        charArray0[5] = 'a';
        String string0 = characterReader0.consumeToAny(charArray0);
        String string1 = characterReader0.consumeHexSequence();
    }

    @Test(timeout = 4000)
    public void test097128() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("0}nq");
        String string0 = characterReader0.consumeHexSequence();
        boolean boolean0 = characterReader0.matchesLetter();
    }

    @Test(timeout = 4000)
    public void test098130() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("KHD,HZm?C");
        String string0 = characterReader0.consumeLetterSequence();
        String string1 = characterReader0.consumeHexSequence();
    }

    @Test(timeout = 4000)
    public void test099132() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("Array must not contain any null objects");
        char[] charArray0 = new char[0];
        String string0 = characterReader0.consumeToAny(charArray0);
        String string1 = characterReader0.consumeHexSequence();
    }

    @Test(timeout = 4000)
    public void test100133() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("xv|Kd&WY&}H~");
        String string0 = characterReader0.consumeLetterThenDigitSequence();
    }

    @Test(timeout = 4000)
    public void test101135() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("Array must not contain any null objects");
        char[] charArray0 = new char[0];
        String string0 = characterReader0.consumeToAny(charArray0);
        String string1 = characterReader0.consumeLetterThenDigitSequence();
    }

    @Test(timeout = 4000)
    public void test102136() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("xv|Kd&WY&}H~");
        String string0 = characterReader0.consumeLetterSequence();
    }

    @Test(timeout = 4000)
    public void test103137() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("Array must not contain any null objects");
        String string0 = characterReader0.consumeLetterSequence();
    }

    @Test(timeout = 4000)
    public void test103138() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("Array must not contain any null objects");
        String string0 = characterReader0.consumeLetterSequence();
        boolean boolean0 = characterReader0.matchesLetter();
    }

    @Test(timeout = 4000)
    public void test104140() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("Array must not contain any null objects");
        char[] charArray0 = new char[0];
        String string0 = characterReader0.consumeToAny(charArray0);
        String string1 = characterReader0.consumeLetterSequence();
    }

    @Test(timeout = 4000)
    public void test105142() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("Et(z(Rnu");
        String string0 = characterReader0.consumeLetterThenDigitSequence();
        String string1 = characterReader0.consumeTo("Et");
    }

    @Test(timeout = 4000)
    public void test105143() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("Et(z(Rnu");
        String string0 = characterReader0.consumeLetterThenDigitSequence();
        String string1 = characterReader0.consumeTo("Et");
        char[] charArray0 = new char[2];
        boolean boolean0 = characterReader0.matchesAny(charArray0);
    }

    @Test(timeout = 4000)
    public void test106144() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("org.jsou!.parser.CharacteReader");
        characterReader0.consumeLetterThenDigitSequence();
        characterReader0.consumeTo("rg");
    }

    @Test(timeout = 4000)
    public void test107145() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("Array must not contain any null objects");
        boolean boolean0 = characterReader0.containsIgnoreCase("A");
    }

    @Test(timeout = 4000)
    public void test108146() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("*&7Dapd?]");
        String string0 = characterReader0.consumeTo("*&7Dapd?]");
    }

    @Test(timeout = 4000)
    public void test109148() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("Array must not contain any null objects");
        String string0 = characterReader0.consumeHexSequence();
        int int0 = characterReader0.nextIndexOf("Array must not contain any null objects");
    }

    @Test(timeout = 4000)
    public void test110149() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("");
        characterReader0.consume();
        characterReader0.toString();
    }

    @Test(timeout = 4000)
    public void test111151() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("Array must not contain any null objects");
        char[] charArray0 = new char[0];
        String string0 = characterReader0.consumeToAny(charArray0);
        char char0 = characterReader0.current();
    }

    @Test(timeout = 4000)
    public void test112152() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("");
        boolean boolean0 = characterReader0.matchesLetter();
    }

    @Test(timeout = 4000)
    public void test113153() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("0}nq");
        int int0 = characterReader0.pos();
    }

    @Test(timeout = 4000)
    public void test114154() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("");
        String string0 = characterReader0.consumeTo('F');
    }

    @Test(timeout = 4000)
    public void test115155() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("");
        String string0 = characterReader0.toString();
    }

    @Test(timeout = 4000)
    public void test116156() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("");
        characterReader0.unconsume();
        boolean boolean0 = characterReader0.isEmpty();
    }

    @Test(timeout = 4000)
    public void test117157() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("");
        characterReader0.advance();
        characterReader0.consumeHexSequence();
    }

    @Test(timeout = 4000)
    public void test118158() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("");
        characterReader0.mark();
    }

    @Test(timeout = 4000)
    public void test119159() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("");
        characterReader0.rewindToMark();
    }
}
