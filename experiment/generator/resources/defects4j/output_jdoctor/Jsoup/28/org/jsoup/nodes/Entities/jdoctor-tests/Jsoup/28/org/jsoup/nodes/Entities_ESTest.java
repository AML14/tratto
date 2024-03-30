/*
 * This file was automatically generated by EvoSuite
 * Sun Oct 15 21:12:21 GMT 2023
 */
package org.jsoup.nodes;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Map;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Entities;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class Entities_ESTest extends Entities_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        String string0 = Entities.unescape("", true);
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        String string0 = Entities.unescape("");
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        Character character0 = Entities.getCharacterByName("DoubleLongRightArrow");
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        Document.OutputSettings document_OutputSettings0 = new Document.OutputSettings();
        String string0 = Entities.escape("", document_OutputSettings0);
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        Document.OutputSettings document_OutputSettings0 = new Document.OutputSettings();
        CharsetEncoder charsetEncoder0 = document_OutputSettings0.encoder();
        Entities.EscapeMode entities_EscapeMode0 = Entities.EscapeMode.extended;
        String string0 = Entities.escape("org.jsoup.nodes.Entities", charsetEncoder0, entities_EscapeMode0);
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        Charset charset0 = Charset.defaultCharset();
        CharsetEncoder charsetEncoder0 = charset0.newEncoder();
        Entities.EscapeMode entities_EscapeMode0 = Entities.EscapeMode.base;
        String string0 = Entities.escape("", charsetEncoder0, entities_EscapeMode0);
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        Entities.unescape((String) null, true);
    }

    @Test(timeout = 4000)
    public void test077() throws Throwable {
        Entities.unescape((String) null);
    }

    @Test(timeout = 4000)
    public void test088() throws Throwable {
        Entities.escape("bFVKr<;C", (Document.OutputSettings) null);
    }

    @Test(timeout = 4000)
    public void test099() throws Throwable {
        Entities.EscapeMode entities_EscapeMode0 = Entities.EscapeMode.xhtml;
        Entities.escape("updownarrow", (CharsetEncoder) null, entities_EscapeMode0);
    }

    @Test(timeout = 4000)
    public void test1010() throws Throwable {
        Entities.EscapeMode entities_EscapeMode0 = Entities.EscapeMode.base;
        Map<Character, String> map0 = entities_EscapeMode0.getMap();
        map0.size();
    }

    @Test(timeout = 4000)
    public void test1111() throws Throwable {
        String string0 = Entities.unescape("b`!x_\"M|&Un#at;.");
    }

    @Test(timeout = 4000)
    public void test1212() throws Throwable {
        String string0 = Entities.unescape(" &ryIIwqzkHhW", true);
    }

    @Test(timeout = 4000)
    public void test1313() throws Throwable {
        boolean boolean0 = Entities.isNamedEntity("SuchThat");
    }

    @Test(timeout = 4000)
    public void test1414() throws Throwable {
        boolean boolean0 = Entities.isNamedEntity(" &ryIIwqzkHhW");
    }

    @Test(timeout = 4000)
    public void test1515() throws Throwable {
        Document.OutputSettings document_OutputSettings0 = new Document.OutputSettings();
        String string0 = Entities.escape("s8+)&5AMT~.rI+YPE", document_OutputSettings0);
    }

    @Test(timeout = 4000)
    public void test1616() throws Throwable {
        String string0 = Entities.unescape("s8+)&amp;5AMT~.rI+YPE");
    }

    @Test(timeout = 4000)
    public void test1717() throws Throwable {
        Character character0 = Entities.getCharacterByName("Pmch<hat");
    }
}
