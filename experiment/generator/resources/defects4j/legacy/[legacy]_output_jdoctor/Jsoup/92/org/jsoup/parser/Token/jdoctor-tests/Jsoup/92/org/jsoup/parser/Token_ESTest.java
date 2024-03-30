/*
 * This file was automatically generated by EvoSuite
 * Sun Dec 31 04:03:05 GMT 2023
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
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class Token_ESTest extends Token_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        Token.StartTag token_StartTag0 = new Token.StartTag();
        Token.Tag token_Tag0 = token_StartTag0.reset();
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        Token.CData token_CData0 = new Token.CData("zq'fTy`U");
        boolean boolean0 = token_CData0.isCData();
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        Token.EndTag token_EndTag0 = new Token.EndTag();
        String string0 = token_EndTag0.tokenType();
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        Token.EndTag token_EndTag0 = new Token.EndTag();
        Token.EndTag token_EndTag1 = token_EndTag0.asEndTag();
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        Token.CData token_CData0 = new Token.CData("");
        Token.Character token_Character0 = token_CData0.asCharacter();
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        Token.StartTag token_StartTag0 = new Token.StartTag();
        Token.StartTag token_StartTag1 = token_StartTag0.asStartTag();
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        Token.EndTag token_EndTag0 = new Token.EndTag();
        token_EndTag0.asDoctype();
    }

    @Test(timeout = 4000)
    public void test077() throws Throwable {
        Token.StartTag token_StartTag0 = new Token.StartTag();
        token_StartTag0.asComment();
    }

    @Test(timeout = 4000)
    public void test088() throws Throwable {
        Token.EndTag token_EndTag0 = new Token.EndTag();
        Attributes attributes0 = token_EndTag0.getAttributes();
    }

    @Test(timeout = 4000)
    public void test099() throws Throwable {
        Token.EndTag token_EndTag0 = new Token.EndTag();
        String string0 = token_EndTag0.normalName();
    }

    @Test(timeout = 4000)
    public void test1010() throws Throwable {
        Token.EndTag token_EndTag0 = new Token.EndTag();
        char[] charArray0 = new char[1];
        token_EndTag0.appendAttributeValue(charArray0);
    }

    @Test(timeout = 4000)
    public void test1111() throws Throwable {
        Token.EndTag token_EndTag0 = new Token.EndTag();
        Token.Tag token_Tag0 = token_EndTag0.name("ee`2e");
    }

    @Test(timeout = 4000)
    public void test1212() throws Throwable {
        Token.EndTag token_EndTag0 = new Token.EndTag();
        boolean boolean0 = token_EndTag0.isSelfClosing();
    }

    @Test(timeout = 4000)
    public void test1313() throws Throwable {
        Token.EndTag token_EndTag0 = new Token.EndTag();
        token_EndTag0.toString();
    }

    @Test(timeout = 4000)
    public void test1414() throws Throwable {
        Token.EOF token_EOF0 = new Token.EOF();
        Token token0 = token_EOF0.reset();
    }

    @Test(timeout = 4000)
    public void test1515() throws Throwable {
        Token.Comment token_Comment0 = new Token.Comment();
        String string0 = token_Comment0.toString();
    }

    @Test(timeout = 4000)
    public void test1616() throws Throwable {
        Token.Comment token_Comment0 = new Token.Comment();
        Token token0 = token_Comment0.reset();
    }

    @Test(timeout = 4000)
    public void test1717() throws Throwable {
        Token.CData token_CData0 = new Token.CData("");
        String string0 = token_CData0.toString();
    }

    @Test(timeout = 4000)
    public void test1818() throws Throwable {
        Token.CData token_CData0 = new Token.CData("U");
        Token token0 = token_CData0.reset();
    }

    @Test(timeout = 4000)
    public void test1919() throws Throwable {
        Token.Character token_Character0 = new Token.Character();
        String string0 = token_Character0.toString();
    }

    @Test(timeout = 4000)
    public void test2020() throws Throwable {
        Token.Doctype token_Doctype0 = new Token.Doctype();
        token_Doctype0.getSystemIdentifier();
        token_Doctype0.isForceQuirks();
    }

    @Test(timeout = 4000)
    public void test2121() throws Throwable {
        Token.Doctype token_Doctype0 = new Token.Doctype();
        token_Doctype0.getName();
        token_Doctype0.isForceQuirks();
    }

    @Test(timeout = 4000)
    public void test2222() throws Throwable {
        Token.Doctype token_Doctype0 = new Token.Doctype();
        token_Doctype0.reset();
        token_Doctype0.isForceQuirks();
    }

    @Test(timeout = 4000)
    public void test2323() throws Throwable {
        Token.Doctype token_Doctype0 = new Token.Doctype();
        token_Doctype0.getPubSysKey();
        token_Doctype0.isForceQuirks();
    }

    @Test(timeout = 4000)
    public void test2424() throws Throwable {
        Token.Doctype token_Doctype0 = new Token.Doctype();
        token_Doctype0.getPublicIdentifier();
        token_Doctype0.isForceQuirks();
    }

    @Test(timeout = 4000)
    public void test2525() throws Throwable {
        Token.Doctype token_Doctype0 = new Token.Doctype();
        boolean boolean0 = token_Doctype0.isForceQuirks();
    }

    @Test(timeout = 4000)
    public void test3326() throws Throwable {
        Token.StartTag token_StartTag0 = new Token.StartTag();
        token_StartTag0.appendTagName("");
        token_StartTag0.toString();
    }

    @Test(timeout = 4000)
    public void test3727() throws Throwable {
        Token.EndTag token_EndTag0 = new Token.EndTag();
        int[] intArray0 = new int[1];
        token_EndTag0.appendAttributeValue(intArray0);
    }

    @Test(timeout = 4000)
    public void test3828() throws Throwable {
        Token.StartTag token_StartTag0 = new Token.StartTag();
        token_StartTag0.nameAttr("h", (Attributes) null);
        String string0 = token_StartTag0.toString();
    }

    @Test(timeout = 4000)
    public void test3929() throws Throwable {
        Token.StartTag token_StartTag0 = new Token.StartTag();
        token_StartTag0.appendAttributeName("zq'fTy`U");
        token_StartTag0.finaliseTag();
        token_StartTag0.toString();
    }

    @Test(timeout = 4000)
    public void test4030() throws Throwable {
        Token.StartTag token_StartTag0 = new Token.StartTag();
        boolean boolean0 = token_StartTag0.isDoctype();
    }

    @Test(timeout = 4000)
    public void test4131() throws Throwable {
        Token.Doctype token_Doctype0 = new Token.Doctype();
        boolean boolean0 = token_Doctype0.isDoctype();
        token_Doctype0.isForceQuirks();
    }

    @Test(timeout = 4000)
    public void test4132() throws Throwable {
        Token.Doctype token_Doctype0 = new Token.Doctype();
        boolean boolean0 = token_Doctype0.isDoctype();
    }

    @Test(timeout = 4000)
    public void test4233() throws Throwable {
        Token.EndTag token_EndTag0 = new Token.EndTag();
        boolean boolean0 = token_EndTag0.isStartTag();
    }

    @Test(timeout = 4000)
    public void test4334() throws Throwable {
        Token.StartTag token_StartTag0 = new Token.StartTag();
        boolean boolean0 = token_StartTag0.isStartTag();
    }

    @Test(timeout = 4000)
    public void test4435() throws Throwable {
        Token.StartTag token_StartTag0 = new Token.StartTag();
        boolean boolean0 = token_StartTag0.isEndTag();
    }

    @Test(timeout = 4000)
    public void test4536() throws Throwable {
        Token.EndTag token_EndTag0 = new Token.EndTag();
        boolean boolean0 = token_EndTag0.isEndTag();
    }

    @Test(timeout = 4000)
    public void test4637() throws Throwable {
        Token.EndTag token_EndTag0 = new Token.EndTag();
        boolean boolean0 = token_EndTag0.isComment();
    }

    @Test(timeout = 4000)
    public void test4738() throws Throwable {
        Token.Comment token_Comment0 = new Token.Comment();
        boolean boolean0 = token_Comment0.isComment();
    }

    @Test(timeout = 4000)
    public void test4839() throws Throwable {
        Token.EndTag token_EndTag0 = new Token.EndTag();
        boolean boolean0 = token_EndTag0.isCharacter();
    }

    @Test(timeout = 4000)
    public void test4940() throws Throwable {
        Token.CData token_CData0 = new Token.CData("Doctype");
        boolean boolean0 = token_CData0.isCharacter();
    }

    @Test(timeout = 4000)
    public void test5041() throws Throwable {
        Token.StartTag token_StartTag0 = new Token.StartTag();
        boolean boolean0 = token_StartTag0.isEOF();
    }

    @Test(timeout = 4000)
    public void test5142() throws Throwable {
        Token.EndTag token_EndTag0 = new Token.EndTag();
        Token.TokenType token_TokenType0 = Token.TokenType.EOF;
        token_EndTag0.type = token_TokenType0;
        boolean boolean0 = token_EndTag0.isEOF();
    }
}
