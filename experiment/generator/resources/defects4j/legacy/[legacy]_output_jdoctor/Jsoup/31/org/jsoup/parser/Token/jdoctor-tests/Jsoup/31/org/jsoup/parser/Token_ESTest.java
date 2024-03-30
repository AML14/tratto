/*
 * This file was automatically generated by EvoSuite
 * Sun Oct 15 21:19:23 GMT 2023
 */
package org.jsoup.parser;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.jsoup.nodes.Attributes;
import org.jsoup.parser.Token;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class Token_ESTest extends Token_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        Attributes attributes0 = new Attributes();
        Token.StartTag token_StartTag0 = new Token.StartTag(" />", attributes0);
        String string0 = token_StartTag0.name();
    }

    @Test(timeout = 4000)
    public void test041() throws Throwable {
        Token.Comment token_Comment0 = new Token.Comment();
        String string0 = token_Comment0.getData();
    }

    @Test(timeout = 4000)
    public void test052() throws Throwable {
        Token.Character token_Character0 = new Token.Character("|#8%_F&&?:?[Cv).[{");
        String string0 = token_Character0.getData();
    }

    @Test(timeout = 4000)
    public void test063() throws Throwable {
        Attributes attributes0 = new Attributes();
        Token.StartTag token_StartTag0 = new Token.StartTag("", attributes0);
        Token.StartTag token_StartTag1 = token_StartTag0.asStartTag();
    }

    @Test(timeout = 4000)
    public void test074() throws Throwable {
        Token.Doctype token_Doctype0 = new Token.Doctype();
        token_Doctype0.forceQuirks = true;
        Token.Doctype token_Doctype1 = token_Doctype0.asDoctype();
        token_Doctype1.isForceQuirks();
    }

    @Test(timeout = 4000)
    public void test085() throws Throwable {
        Token.Comment token_Comment0 = new Token.Comment();
        Token.Comment token_Comment1 = token_Comment0.asComment();
    }

    @Test(timeout = 4000)
    public void test096() throws Throwable {
        Token.Character token_Character0 = new Token.Character((String) null);
        Token.Character token_Character1 = token_Character0.asCharacter();
    }

    @Test(timeout = 4000)
    public void test107() throws Throwable {
        Token.EndTag token_EndTag0 = new Token.EndTag("");
        token_EndTag0.asDoctype();
    }

    @Test(timeout = 4000)
    public void test118() throws Throwable {
        Token.EndTag token_EndTag0 = new Token.EndTag();
        boolean boolean0 = token_EndTag0.isEOF();
    }

    @Test(timeout = 4000)
    public void test129() throws Throwable {
        Token.Character token_Character0 = new Token.Character("c@te");
        boolean boolean0 = token_Character0.isCharacter();
    }

    @Test(timeout = 4000)
    public void test1310() throws Throwable {
        Token.Doctype token_Doctype0 = new Token.Doctype();
        boolean boolean0 = token_Doctype0.isCharacter();
        token_Doctype0.isForceQuirks();
    }

    @Test(timeout = 4000)
    public void test1311() throws Throwable {
        Token.Doctype token_Doctype0 = new Token.Doctype();
        boolean boolean0 = token_Doctype0.isCharacter();
    }

    @Test(timeout = 4000)
    public void test1412() throws Throwable {
        Token.Comment token_Comment0 = new Token.Comment();
        boolean boolean0 = token_Comment0.isComment();
    }

    @Test(timeout = 4000)
    public void test1513() throws Throwable {
        Token.EndTag token_EndTag0 = new Token.EndTag();
        boolean boolean0 = token_EndTag0.isComment();
    }

    @Test(timeout = 4000)
    public void test1614() throws Throwable {
        Token.EndTag token_EndTag0 = new Token.EndTag();
        boolean boolean0 = token_EndTag0.isEndTag();
    }

    @Test(timeout = 4000)
    public void test1715() throws Throwable {
        Token.Doctype token_Doctype0 = new Token.Doctype();
        boolean boolean0 = token_Doctype0.isEndTag();
        token_Doctype0.isForceQuirks();
    }

    @Test(timeout = 4000)
    public void test1716() throws Throwable {
        Token.Doctype token_Doctype0 = new Token.Doctype();
        boolean boolean0 = token_Doctype0.isEndTag();
    }

    @Test(timeout = 4000)
    public void test1817() throws Throwable {
        Token.EndTag token_EndTag0 = new Token.EndTag();
        Token.EndTag token_EndTag1 = token_EndTag0.asEndTag();
        Token.TokenType token_TokenType0 = Token.TokenType.StartTag;
        token_EndTag1.type = token_TokenType0;
        boolean boolean0 = token_EndTag1.isStartTag();
    }

    @Test(timeout = 4000)
    public void test1918() throws Throwable {
        Token.EndTag token_EndTag0 = new Token.EndTag();
        boolean boolean0 = token_EndTag0.isStartTag();
    }

    @Test(timeout = 4000)
    public void test2019() throws Throwable {
        Token.EndTag token_EndTag0 = new Token.EndTag();
        Token.TokenType token_TokenType0 = Token.TokenType.Doctype;
        token_EndTag0.type = token_TokenType0;
        boolean boolean0 = token_EndTag0.isDoctype();
    }

    @Test(timeout = 4000)
    public void test2120() throws Throwable {
        Token.EndTag token_EndTag0 = new Token.EndTag();
        boolean boolean0 = token_EndTag0.isDoctype();
    }

    @Test(timeout = 4000)
    public void test2221() throws Throwable {
        Token.StartTag token_StartTag0 = new Token.StartTag("org.j^oup.parser.Token$TokenT]pe");
        String string0 = token_StartTag0.toString();
    }

    @Test(timeout = 4000)
    public void test2522() throws Throwable {
        Token.EndTag token_EndTag0 = new Token.EndTag();
        token_EndTag0.appendTagName('J');
        String string0 = token_EndTag0.toString();
    }

    @Test(timeout = 4000)
    public void test2623() throws Throwable {
        Token.StartTag token_StartTag0 = new Token.StartTag("", (Attributes) null);
        token_StartTag0.toString();
    }

    @Test(timeout = 4000)
    public void test2824() throws Throwable {
        Token.Character token_Character0 = new Token.Character("Doctype");
        String string0 = token_Character0.toString();
    }

    @Test(timeout = 4000)
    public void test2925() throws Throwable {
        Token.Doctype token_Doctype0 = new Token.Doctype();
        boolean boolean0 = token_Doctype0.isForceQuirks();
    }

    @Test(timeout = 4000)
    public void test3026() throws Throwable {
        Token.Doctype token_Doctype0 = new Token.Doctype();
        token_Doctype0.getPublicIdentifier();
        token_Doctype0.isForceQuirks();
    }

    @Test(timeout = 4000)
    public void test3127() throws Throwable {
        Token.Doctype token_Doctype0 = new Token.Doctype();
        token_Doctype0.getName();
        token_Doctype0.isForceQuirks();
    }

    @Test(timeout = 4000)
    public void test3228() throws Throwable {
        Token.Doctype token_Doctype0 = new Token.Doctype();
        token_Doctype0.getSystemIdentifier();
        token_Doctype0.isForceQuirks();
    }

    @Test(timeout = 4000)
    public void test3329() throws Throwable {
        Token.Comment token_Comment0 = new Token.Comment();
        String string0 = token_Comment0.toString();
    }

    @Test(timeout = 4000)
    public void test3430() throws Throwable {
        Token.EndTag token_EndTag0 = new Token.EndTag();
        boolean boolean0 = token_EndTag0.isSelfClosing();
    }

    @Test(timeout = 4000)
    public void test3531() throws Throwable {
        Token.StartTag token_StartTag0 = new Token.StartTag("L8N5Xye})SGoac`r&", (Attributes) null);
        token_StartTag0.appendAttributeName('O');
        token_StartTag0.finaliseTag();
        String string0 = token_StartTag0.toString();
    }

    @Test(timeout = 4000)
    public void test3632() throws Throwable {
        Token.EndTag token_EndTag0 = new Token.EndTag();
        Token.Tag token_Tag0 = token_EndTag0.name("");
    }

    @Test(timeout = 4000)
    public void test3733() throws Throwable {
        Token.EndTag token_EndTag0 = new Token.EndTag();
        Attributes attributes0 = token_EndTag0.getAttributes();
    }

    @Test(timeout = 4000)
    public void test3834() throws Throwable {
        Token.Doctype token_Doctype0 = new Token.Doctype();
        token_Doctype0.asComment();
    }

    @Test(timeout = 4000)
    public void test3935() throws Throwable {
        Token.Doctype token_Doctype0 = new Token.Doctype();
        Token.Doctype token_Doctype1 = token_Doctype0.asDoctype();
        Token.TokenType token_TokenType0 = Token.TokenType.EOF;
        token_Doctype1.type = token_TokenType0;
        boolean boolean0 = token_Doctype1.isEOF();
        token_Doctype1.isForceQuirks();
    }

    @Test(timeout = 4000)
    public void test3936() throws Throwable {
        Token.Doctype token_Doctype0 = new Token.Doctype();
        Token.Doctype token_Doctype1 = token_Doctype0.asDoctype();
        Token.TokenType token_TokenType0 = Token.TokenType.EOF;
        token_Doctype1.type = token_TokenType0;
        boolean boolean0 = token_Doctype1.isEOF();
    }

    @Test(timeout = 4000)
    public void test4037() throws Throwable {
        Token.Comment token_Comment0 = new Token.Comment();
        token_Comment0.asStartTag();
    }

    @Test(timeout = 4000)
    public void test4138() throws Throwable {
        Token.Doctype token_Doctype0 = new Token.Doctype();
        token_Doctype0.asCharacter();
    }

    @Test(timeout = 4000)
    public void test4239() throws Throwable {
        Token.Doctype token_Doctype0 = new Token.Doctype();
        token_Doctype0.asEndTag();
    }

    @Test(timeout = 4000)
    public void test4340() throws Throwable {
        Token.Doctype token_Doctype0 = new Token.Doctype();
        token_Doctype0.tokenType();
        token_Doctype0.isForceQuirks();
    }
}
