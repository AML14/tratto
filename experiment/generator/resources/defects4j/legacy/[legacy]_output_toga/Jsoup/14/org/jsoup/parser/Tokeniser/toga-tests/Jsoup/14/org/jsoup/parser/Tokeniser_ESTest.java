/*
 * This file was automatically generated by EvoSuite
 * Sun Oct 15 20:43:41 GMT 2023
 */
package org.jsoup.parser;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.jsoup.parser.CharacterReader;
import org.jsoup.parser.Token;
import org.jsoup.parser.Tokeniser;
import org.jsoup.parser.TokeniserState;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class Tokeniser_ESTest extends Tokeniser_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("#");
        Tokeniser tokeniser0 = new Tokeniser(characterReader0);
        Character character0 = new Character('W');
        Character character1 = tokeniser0.consumeCharacterReference(character0, true);
    }

    @Test(timeout = 4000)
    public void test001() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("#");
        Tokeniser tokeniser0 = new Tokeniser(characterReader0);
        Character character0 = new Character('W');
        Character character1 = tokeniser0.consumeCharacterReference(character0, true);
        characterReader0.toString();
        assertNotNull(characterReader0.toString());
    }

    @Test(timeout = 4000)
    public void test042() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("");
        Tokeniser tokeniser0 = new Tokeniser(characterReader0);
        tokeniser0.setTrackErrors(false);
        boolean boolean0 = tokeniser0.isTrackErrors();
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test053() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("#");
        Tokeniser tokeniser0 = new Tokeniser(characterReader0);
        Token.EndTag token_EndTag0 = new Token.EndTag("gamma");
        tokeniser0.tagPending = (Token.Tag) token_EndTag0;
        Token.StartTag token_StartTag0 = new Token.StartTag();
        tokeniser0.emit(token_StartTag0);
        boolean boolean0 = tokeniser0.isAppropriateEndTagToken();
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test074() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("&Dg;V@q.");
        characterReader0.unconsume();
        Tokeniser tokeniser0 = new Tokeniser(characterReader0);
        tokeniser0.read();
    }

    @Test(timeout = 4000)
    public void test085() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("mRb9w(\"4xGtwS ");
        Tokeniser tokeniser0 = new Tokeniser(characterReader0);
        TokeniserState tokeniserState0 = TokeniserState.DoctypeSystemIdentifier_doubleQuoted;
        tokeniser0.advanceTransition(tokeniserState0);
        tokeniser0.read();
    }

    @Test(timeout = 4000)
    public void test096() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("5[H0K");
        characterReader0.unconsume();
        Tokeniser tokeniser0 = new Tokeniser(characterReader0);
        TokeniserState tokeniserState0 = TokeniserState.AfterDoctypeSystemIdentifier;
        tokeniser0.error(tokeniserState0);
    }

    @Test(timeout = 4000)
    public void test107() throws Throwable {
        Tokeniser tokeniser0 = new Tokeniser((CharacterReader) null);
        TokeniserState tokeniserState0 = TokeniserState.RawtextEndTagOpen;
        tokeniser0.error(tokeniserState0);
    }

    @Test(timeout = 4000)
    public void test118() throws Throwable {
        Tokeniser tokeniser0 = new Tokeniser((CharacterReader) null);
        TokeniserState tokeniserState0 = TokeniserState.RawtextEndTagOpen;
        tokeniser0.eofError(tokeniserState0);
    }

    @Test(timeout = 4000)
    public void test129() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("m>g");
        Tokeniser tokeniser0 = new Tokeniser(characterReader0);
        tokeniser0.createTagPending(false);
        tokeniser0.emitTagPending();
        tokeniser0.emitTagPending();
    }

    @Test(timeout = 4000)
    public void test1310() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("");
        Tokeniser tokeniser0 = new Tokeniser(characterReader0);
        Token.EndTag token_EndTag0 = new Token.EndTag();
        tokeniser0.emit(token_EndTag0);
        tokeniser0.emitDoctypePending();
    }

    @Test(timeout = 4000)
    public void test1411() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("cM(Y");
        Tokeniser tokeniser0 = new Tokeniser(characterReader0);
        Token.EndTag token_EndTag0 = new Token.EndTag();
        tokeniser0.emit(token_EndTag0);
        tokeniser0.emitCommentPending();
    }

    @Test(timeout = 4000)
    public void test1512() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("8jfmDD");
        Tokeniser tokeniser0 = new Tokeniser(characterReader0);
        tokeniser0.emit((Token) null);
    }

    @Test(timeout = 4000)
    public void test1613() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("]");
        Tokeniser tokeniser0 = new Tokeniser(characterReader0);
        Token.Comment token_Comment0 = new Token.Comment();
        Token.TokenType token_TokenType0 = Token.TokenType.EndTag;
        token_Comment0.type = token_TokenType0;
        tokeniser0.emit(token_Comment0);
    }

    @Test(timeout = 4000)
    public void test1714() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("");
        characterReader0.unconsume();
        Tokeniser tokeniser0 = new Tokeniser(characterReader0);
        Character character0 = new Character('N');
        tokeniser0.consumeCharacterReference(character0, false);
    }

    @Test(timeout = 4000)
    public void test1815() throws Throwable {
        Tokeniser tokeniser0 = new Tokeniser((CharacterReader) null);
        Character character0 = Character.valueOf('J');
        tokeniser0.consumeCharacterReference(character0, true);
    }

    @Test(timeout = 4000)
    public void test1916() throws Throwable {
        Tokeniser tokeniser0 = new Tokeniser((CharacterReader) null);
        TokeniserState tokeniserState0 = TokeniserState.CharacterReferenceInData;
        tokeniser0.advanceTransition(tokeniserState0);
    }

    @Test(timeout = 4000)
    public void test2117() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("org.jsoup.parser.Tokeniser");
        Tokeniser tokeniser0 = new Tokeniser(characterReader0);
        tokeniser0.setTrackErrors(false);
        tokeniser0.consumeCharacterReference((Character) null, false);
        characterReader0.toString();
    }

    @Test(timeout = 4000)
    public void test2518() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("#");
        Tokeniser tokeniser0 = new Tokeniser(characterReader0);
        Token.Tag token_Tag0 = tokeniser0.createTagPending(false);
        tokeniser0.emit(token_Tag0);
        tokeniser0.emit(token_Tag0);
    }

    @Test(timeout = 4000)
    public void test2619() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("kcedil");
        Tokeniser tokeniser0 = new Tokeniser(characterReader0);
        Character character0 = new Character('O');
        tokeniser0.consumeCharacterReference(character0, true);
        characterReader0.toString();
    }

    @Test(timeout = 4000)
    public void test2720() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("org.jsoup.parser.Tokeniser");
        Tokeniser tokeniser0 = new Tokeniser(characterReader0);
        Character character0 = Character.valueOf('V');
        Character character1 = tokeniser0.consumeCharacterReference(character0, true);
    }

    @Test(timeout = 4000)
    public void test2821() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("^Xc");
        Tokeniser tokeniser0 = new Tokeniser(characterReader0);
        Character character0 = tokeniser0.consumeCharacterReference((Character) null, true);
    }

    @Test(timeout = 4000)
    public void test2922() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("^Xc");
        Tokeniser tokeniser0 = new Tokeniser(characterReader0);
        Character character0 = Character.valueOf('^');
        Character character1 = tokeniser0.consumeCharacterReference(character0, true);
    }

    @Test(timeout = 4000)
    public void test3023() throws Throwable {
        Character character0 = new Character('@');
        CharacterReader characterReader0 = new CharacterReader("sup1");
        Tokeniser tokeniser0 = new Tokeniser(characterReader0);
        Character character1 = tokeniser0.consumeCharacterReference(character0, true);
    }

    @Test(timeout = 4000)
    public void test3124() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("");
        Tokeniser tokeniser0 = new Tokeniser(characterReader0);
        Character character0 = new Character('{');
        Character character1 = tokeniser0.consumeCharacterReference(character0, true);
    }

    @Test(timeout = 4000)
    public void test3225() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("&Dg;V@q.");
        Tokeniser tokeniser0 = new Tokeniser(characterReader0);
        Token token0 = tokeniser0.read();
        token0.toString();
    }

    @Test(timeout = 4000)
    public void test3526() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("#");
        Tokeniser tokeniser0 = new Tokeniser(characterReader0);
        Token.StartTag token_StartTag0 = new Token.StartTag("#");
        token_StartTag0.selfClosing = true;
        tokeniser0.emit(token_StartTag0);
        Token token0 = tokeniser0.read();
    }

    @Test(timeout = 4000)
    public void test3827() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("");
        Tokeniser tokeniser0 = new Tokeniser(characterReader0);
        boolean boolean0 = tokeniser0.currentNodeInHtmlNS();
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test4028() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("cM(Y");
        Tokeniser tokeniser0 = new Tokeniser(characterReader0);
        tokeniser0.emitCommentPending();
    }

    @Test(timeout = 4000)
    public void test4229() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("");
        Tokeniser tokeniser0 = new Tokeniser(characterReader0);
        boolean boolean0 = tokeniser0.isTrackErrors();
    }

    @Test(timeout = 4000)
    public void test4330() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("m>g");
        Tokeniser tokeniser0 = new Tokeniser(characterReader0);
        tokeniser0.emitTagPending();
    }

    @Test(timeout = 4000)
    public void test4431() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("&Dg;V@q.");
        Tokeniser tokeniser0 = new Tokeniser(characterReader0);
        TokeniserState tokeniserState0 = TokeniserState.CharacterReferenceInData;
        tokeniser0.transition(tokeniserState0);
        tokeniser0.read();
        characterReader0.toString();
        assertNotNull(characterReader0.toString());
    }

    @Test(timeout = 4000)
    public void test4532() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("2$\"4\"!NJW,?j f;");
        Tokeniser tokeniser0 = new Tokeniser(characterReader0);
        tokeniser0.isAppropriateEndTagToken();
    }

    @Test(timeout = 4000)
    public void test4733() throws Throwable {
        CharacterReader characterReader0 = new CharacterReader("");
        Tokeniser tokeniser0 = new Tokeniser(characterReader0);
        tokeniser0.emitDoctypePending();
    }
}
