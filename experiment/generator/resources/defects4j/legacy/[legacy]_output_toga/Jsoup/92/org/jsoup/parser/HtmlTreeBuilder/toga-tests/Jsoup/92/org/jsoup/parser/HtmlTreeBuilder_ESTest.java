/*
 * This file was automatically generated by EvoSuite
 * Sun Dec 31 04:02:38 GMT 2023
 */
package org.jsoup.parser;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.util.ArrayList;
import java.util.List;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;
import org.jsoup.nodes.Node;
import org.jsoup.parser.HtmlTreeBuilder;
import org.jsoup.parser.ParseSettings;
import org.jsoup.parser.Parser;
import org.jsoup.parser.Tag;
import org.jsoup.parser.Token;
import org.jsoup.parser.Tokeniser;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class HtmlTreeBuilder_ESTest extends HtmlTreeBuilder_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Parser parser0 = new Parser(htmlTreeBuilder0);
        Document document0 = parser0.parseInput("u^Ra0", "u^Ra0");
        htmlTreeBuilder0.aboveOnStack(document0);
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Parser parser0 = new Parser(htmlTreeBuilder0);
        Document document0 = parser0.parseInput("SAI&q!x8f@uk", "SAI&q!x8f@uk");
        htmlTreeBuilder0.replaceOnStack(document0, document0);
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Element element0 = htmlTreeBuilder0.getHeadElement();
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Parser parser0 = new Parser(htmlTreeBuilder0);
        Document document0 = parser0.parseInput("8ode", "8ode");
        document0.childNodeSize();
    }

    @Test(timeout = 4000)
    public void test034() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Parser parser0 = new Parser(htmlTreeBuilder0);
        Document document0 = parser0.parseInput("8ode", "8ode");
        htmlTreeBuilder0.processStartTag("table");
        htmlTreeBuilder0.processStartTag("th");
        boolean boolean0 = htmlTreeBuilder0.processStartTag("th");
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test045() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        htmlTreeBuilder0.inListItemScope("center");
    }

    @Test(timeout = 4000)
    public void test056() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        htmlTreeBuilder0.toString();
    }

    @Test(timeout = 4000)
    public void test067() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Parser parser0 = new Parser(htmlTreeBuilder0);
        parser0.parseInput("isindex", "isindex");
        boolean boolean0 = htmlTreeBuilder0.processStartTag("table");
        boolean boolean1 = htmlTreeBuilder0.processStartTag("isindex");
    }

    @Test(timeout = 4000)
    public void test078() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Parser parser0 = new Parser(htmlTreeBuilder0);
        parser0.parseInput("select", "select");
        boolean boolean0 = htmlTreeBuilder0.processStartTag("select");
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test089() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        String[] stringArray0 = new String[0];
        htmlTreeBuilder0.inScope(stringArray0);
    }

    @Test(timeout = 4000)
    public void test0910() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        String string0 = htmlTreeBuilder0.getBaseUri();
        assertNotNull(string0);
    }

    @Test(timeout = 4000)
    public void test1011() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        htmlTreeBuilder0.isInActiveFormattingElements((Element) null);
    }

    @Test(timeout = 4000)
    public void test1112() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        boolean boolean0 = htmlTreeBuilder0.isFragmentParsing();
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test1213() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        htmlTreeBuilder0.push((Element) null);
    }

    @Test(timeout = 4000)
    public void test1314() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        htmlTreeBuilder0.isSpecial((Element) null);
    }

    @Test(timeout = 4000)
    public void test1415() throws Throwable {
        Document document0 = Parser.parse("<!x^CjLnl>$&XJmxu", "<!x^CjLnl>$&XJmxu");
        document0.childNodeSize();
        assertEquals(1, document0.childNodeSize());
    }

    @Test(timeout = 4000)
    public void test1516() throws Throwable {
        Document document0 = Parser.parseBodyFragment(")(vKxcfn|Jc 2_[.]", ")(vKxcfn|Jc 2_[.]");
        document0.wrap(")(vKxcfn|Jc 2_[.]");
    }

    @Test(timeout = 4000)
    public void test1617() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Parser parser0 = new Parser(htmlTreeBuilder0);
        Element element0 = new Element("table");
        List<Node> list0 = htmlTreeBuilder0.parseFragment("table", element0, "table", parser0);
        list0.isEmpty();
    }

    @Test(timeout = 4000)
    public void test1718() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Tag tag0 = Tag.valueOf("title");
        Attributes attributes0 = new Attributes();
        FormElement formElement0 = new FormElement(tag0, "table", attributes0);
        Parser parser0 = Parser.htmlParser();
        List<Node> list0 = htmlTreeBuilder0.parseFragment("th", formElement0, "title", parser0);
        list0.size();
    }

    @Test(timeout = 4000)
    public void test1819() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Tag tag0 = Tag.valueOf("xmp");
        Attributes attributes0 = new Attributes();
        FormElement formElement0 = new FormElement(tag0, "xmp", attributes0);
        Parser parser0 = Parser.htmlParser();
        List<Node> list0 = htmlTreeBuilder0.parseFragment("xmp", formElement0, "xmp", parser0);
        list0.size();
    }

    @Test(timeout = 4000)
    public void test1920() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Parser parser0 = new Parser(htmlTreeBuilder0);
        Tag tag0 = Tag.valueOf("script", (ParseSettings) null);
        Attributes attributes0 = new Attributes();
        FormElement formElement0 = new FormElement(tag0, "script", attributes0);
        List<Node> list0 = htmlTreeBuilder0.parseFragment("script", formElement0, "script", parser0);
        list0.size();
    }

    @Test(timeout = 4000)
    public void test2021() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Parser parser0 = new Parser(htmlTreeBuilder0);
        Attributes attributes0 = new Attributes();
        ParseSettings parseSettings0 = htmlTreeBuilder0.defaultSettings();
        Tag tag0 = Tag.valueOf("noscript", parseSettings0);
        FormElement formElement0 = new FormElement(tag0, "AfterFrameset", attributes0);
        List<Node> list0 = htmlTreeBuilder0.parseFragment("noscript", formElement0, "noscript", parser0);
        list0.size();
    }

    @Test(timeout = 4000)
    public void test2122() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Tag tag0 = Tag.valueOf("plaintext");
        Attributes attributes0 = new Attributes();
        FormElement formElement0 = new FormElement(tag0, "plaintext", attributes0);
        Parser parser0 = Parser.htmlParser();
        List<Node> list0 = htmlTreeBuilder0.parseFragment("th", formElement0, "^FcS/5\"Hr]wJOs>Of", parser0);
        list0.size();
    }

    @Test(timeout = 4000)
    public void test2223() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Attributes attributes0 = new Attributes();
        Tag tag0 = Tag.valueOf("th");
        FormElement formElement0 = new FormElement(tag0, "th", attributes0);
        htmlTreeBuilder0.maybeSetBaseUri(formElement0);
        formElement0.hasParent();
        assertTrue(formElement0.hasParent());
    }

    @Test(timeout = 4000)
    public void test2324() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Parser parser0 = new Parser(htmlTreeBuilder0);
        parser0.setTrackErrors(100);
        parser0.parseInput("isindex", "isindex");
        boolean boolean0 = htmlTreeBuilder0.processStartTag("isindex");
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test2425() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Parser parser0 = new Parser(htmlTreeBuilder0);
        parser0.parseInput("!<d1", "!<d1");
        Token.StartTag token_StartTag0 = new Token.StartTag();
        Attributes attributes0 = new Attributes();
        Token.StartTag token_StartTag1 = token_StartTag0.nameAttr("noframes", attributes0);
        token_StartTag1.selfClosing = true;
        Element element0 = htmlTreeBuilder0.insert(token_StartTag0);
        element0.siblingIndex();
        assertEquals(0, element0.siblingIndex());
    }

    @Test(timeout = 4000)
    public void test2526() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Parser parser0 = new Parser(htmlTreeBuilder0);
        parser0.parseInput("9q+!r3c|!}ncgx~p4", "9q+!r3c|!}ncgx~p4");
        Token.StartTag token_StartTag0 = new Token.StartTag();
        Attributes attributes0 = new Attributes();
        Token.StartTag token_StartTag1 = token_StartTag0.nameAttr("9q+!r3c|!}ncgx~p4", attributes0);
        token_StartTag1.selfClosing = true;
        Element element0 = htmlTreeBuilder0.insertEmpty(token_StartTag0);
        element0.siblingIndex();
        assertEquals(0, element0.siblingIndex());
    }

    @Test(timeout = 4000)
    public void test2627() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Parser parser0 = new Parser(htmlTreeBuilder0);
        parser0.parseInput("script", "script");
        Token.CData token_CData0 = new Token.CData("script");
        htmlTreeBuilder0.insert(token_CData0);
    }

    @Test(timeout = 4000)
    public void test2728() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Parser parser0 = new Parser(htmlTreeBuilder0);
        parser0.parseInput("tr", "br");
        htmlTreeBuilder0.processStartTag("script");
        Tokeniser tokeniser0 = htmlTreeBuilder0.tokeniser;
        Token.Character token_Character0 = tokeniser0.charPending;
        htmlTreeBuilder0.insert(token_Character0);
    }

    @Test(timeout = 4000)
    public void test2829() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Parser parser0 = new Parser(htmlTreeBuilder0);
        parser0.parseInput("center", "center");
        htmlTreeBuilder0.processStartTag("style");
        Token.Character token_Character0 = new Token.Character();
        htmlTreeBuilder0.insert(token_Character0);
    }

    @Test(timeout = 4000)
    public void test2930() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Attributes attributes0 = new Attributes();
        Tag tag0 = Tag.valueOf("th");
        FormElement formElement0 = new FormElement(tag0, "th", attributes0);
        Parser parser0 = Parser.xmlParser();
        htmlTreeBuilder0.parseFragment("th", formElement0, "th", parser0);
        htmlTreeBuilder0.insertInFosterParent(formElement0);
        formElement0.siblingIndex();
        assertEquals(0, formElement0.siblingIndex());
    }

    @Test(timeout = 4000)
    public void test3031() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Parser parser0 = new Parser(htmlTreeBuilder0);
        Document document0 = parser0.parseInput("invalid named reference '%s'", "invalid named reference '%s'");
        boolean boolean0 = htmlTreeBuilder0.removeFromStack(document0);
        document0.childNodeSize();
        assertEquals(1, document0.childNodeSize());
    }

    @Test(timeout = 4000)
    public void test3032() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Parser parser0 = new Parser(htmlTreeBuilder0);
        Document document0 = parser0.parseInput("invalid named reference '%s'", "invalid named reference '%s'");
        boolean boolean0 = htmlTreeBuilder0.removeFromStack(document0);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test3133() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Parser parser0 = new Parser(htmlTreeBuilder0);
        Document document0 = parser0.parseInput("/plLM]{jN*c}YT;kvZB", "/plLM]{jN*c}YT;kvZB");
        document0.childNodeSize();
        assertEquals(0, document0.childNodeSize());
    }

    @Test(timeout = 4000)
    public void test3134() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Parser parser0 = new Parser(htmlTreeBuilder0);
        Document document0 = parser0.parseInput("/plLM]{jN*c}YT;kvZB", "/plLM]{jN*c}YT;kvZB");
        htmlTreeBuilder0.popStackToClose("/plLM]{jN*c}YT;kvZB");
        boolean boolean0 = htmlTreeBuilder0.inButtonScope("/plLM]{jN*c}YT;kvZB");
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test3235() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Parser parser0 = new Parser(htmlTreeBuilder0);
        parser0.parseInput("plaintext", "TreeBuilder{currentToken=");
        String[] stringArray0 = new String[4];
        stringArray0[0] = "plaintext";
        stringArray0[1] = "plaintext";
        htmlTreeBuilder0.popStackToClose(stringArray0);
    }

    @Test(timeout = 4000)
    public void test3336() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Parser parser0 = new Parser(htmlTreeBuilder0);
        parser0.parseInput("tab{le", "tab{le");
        htmlTreeBuilder0.processStartTag("tab{le");
        String[] stringArray0 = new String[2];
        stringArray0[0] = "tab{le";
        htmlTreeBuilder0.popStackToClose(stringArray0);
    }

    @Test(timeout = 4000)
    public void test3437() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Parser parser0 = new Parser(htmlTreeBuilder0);
        parser0.parseInput("AfterFrXmeset", "AfterFrXmeset");
        htmlTreeBuilder0.popStackToBefore("org.jsoup.select.StructuralEvaluator$ImmediateParent");
    }

    @Test(timeout = 4000)
    public void test3538() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Parser parser0 = new Parser(htmlTreeBuilder0);
        parser0.parseInput("P:k+AGjra:e", "P:k+AGjra:e");
        htmlTreeBuilder0.processStartTag("table");
        htmlTreeBuilder0.popStackToBefore("table");
    }

    @Test(timeout = 4000)
    public void test3639() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Parser parser0 = new Parser(htmlTreeBuilder0);
        parser0.parseInput("U=vU", "U=vU");
        htmlTreeBuilder0.popStackToClose("U=vU");
        htmlTreeBuilder0.clearStackToTableContext();
    }

    @Test(timeout = 4000)
    public void test3740() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Parser parser0 = new Parser(htmlTreeBuilder0);
        parser0.parseInput("fy,s)2J|O~;sJY#Yy", "fy,s)2J|O~;sJY#Yy");
        htmlTreeBuilder0.clearStackToTableContext();
    }

    @Test(timeout = 4000)
    public void test3841() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Parser parser0 = new Parser(htmlTreeBuilder0);
        Document document0 = parser0.parseInput("script", "script");
        htmlTreeBuilder0.insert(document0);
        Element element0 = htmlTreeBuilder0.aboveOnStack(document0);
        htmlTreeBuilder0.aboveOnStack(element0);
        document0.siblingIndex();
        assertEquals(0, document0.siblingIndex());
    }

    @Test(timeout = 4000)
    public void test3842() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Parser parser0 = new Parser(htmlTreeBuilder0);
        Document document0 = parser0.parseInput("script", "script");
        htmlTreeBuilder0.insert(document0);
        Element element0 = htmlTreeBuilder0.aboveOnStack(document0);
        htmlTreeBuilder0.aboveOnStack(element0);
        element0.siblingIndex();
        assertEquals(0, element0.siblingIndex());
    }

    @Test(timeout = 4000)
    public void test3943() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Parser parser0 = Parser.htmlParser();
        Parser parser1 = parser0.setTreeBuilder(htmlTreeBuilder0);
        Document document0 = parser1.parseInput("", "");
        htmlTreeBuilder0.insertOnStackAfter(document0, document0);
    }

    @Test(timeout = 4000)
    public void test4044() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Parser parser0 = new Parser(htmlTreeBuilder0);
        Document document0 = parser0.parseInput("eteWU", "eteWU");
        htmlTreeBuilder0.insert(document0);
        htmlTreeBuilder0.insertOnStackAfter(document0, document0);
        document0.siblingIndex();
        assertEquals(0, document0.siblingIndex());
    }

    @Test(timeout = 4000)
    public void test4145() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Parser parser0 = Parser.htmlParser();
        Parser parser1 = parser0.setTreeBuilder(htmlTreeBuilder0);
        Document document0 = parser1.parseInput("plaintext", "plaintext");
        htmlTreeBuilder0.pushActiveFormattingElements(document0);
        htmlTreeBuilder0.replaceActiveFormattingElement(document0, document0);
        document0.childNodeSize();
        assertEquals(0, document0.childNodeSize());
    }

    @Test(timeout = 4000)
    public void test4246() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Parser parser0 = new Parser(htmlTreeBuilder0);
        parser0.parseInput("Kofm,Mw${PTXwP6", "Kofm,Mw${PTXwP6");
        htmlTreeBuilder0.popStackToClose("Kofm,Mw${PTXwP6");
        htmlTreeBuilder0.resetInsertionMode();
    }

    @Test(timeout = 4000)
    public void test4347() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Attributes attributes0 = new Attributes();
        Tag tag0 = Tag.valueOf("th");
        FormElement formElement0 = new FormElement(tag0, "th", attributes0);
        Parser parser0 = Parser.xmlParser();
        htmlTreeBuilder0.parseFragment("th", formElement0, "th", parser0);
        htmlTreeBuilder0.insert(formElement0);
        htmlTreeBuilder0.resetInsertionMode();
    }

    @Test(timeout = 4000)
    public void test4448() throws Throwable {
        Element element0 = new Element("select");
        List<Node> list0 = Parser.parseFragment("select", element0, "select");
        list0.size();
    }

    @Test(timeout = 4000)
    public void test4549() throws Throwable {
        Parser parser0 = Parser.xmlParser();
        Tag tag0 = Tag.valueOf("td");
        Attributes attributes0 = new Attributes();
        FormElement formElement0 = new FormElement(tag0, "td", attributes0);
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        List<Node> list0 = htmlTreeBuilder0.parseFragment("AfterFraeset", formElement0, "td", parser0);
        list0.size();
    }

    @Test(timeout = 4000)
    public void test4650() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Tag tag0 = Tag.valueOf("tr");
        Attributes attributes0 = new Attributes();
        FormElement formElement0 = new FormElement(tag0, "progress", attributes0);
        Parser parser0 = new Parser(htmlTreeBuilder0);
        List<Node> list0 = htmlTreeBuilder0.parseFragment("progress", formElement0, "progress", parser0);
        list0.size();
    }

    @Test(timeout = 4000)
    public void test4751() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Parser parser0 = new Parser(htmlTreeBuilder0);
        Tag tag0 = Tag.valueOf("tbody");
        FormElement formElement0 = new FormElement(tag0, "tbody", (Attributes) null);
        List<Node> list0 = htmlTreeBuilder0.parseFragment("", formElement0, "", parser0);
        list0.size();
    }

    @Test(timeout = 4000)
    public void test4852() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Parser parser0 = new Parser(htmlTreeBuilder0);
        Attributes attributes0 = new Attributes();
        Tag tag0 = Tag.valueOf("thead", (ParseSettings) null);
        FormElement formElement0 = new FormElement(tag0, "thead", attributes0);
        List<Node> list0 = htmlTreeBuilder0.parseFragment("thead", formElement0, "thead", parser0);
        list0.size();
    }

    @Test(timeout = 4000)
    public void test4953() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Parser parser0 = Parser.xmlParser();
        Tag tag0 = Tag.valueOf("tfoot");
        Attributes attributes0 = new Attributes();
        FormElement formElement0 = new FormElement(tag0, "tfoot", attributes0);
        List<Node> list0 = htmlTreeBuilder0.parseFragment("progress", formElement0, "=+U", parser0);
        list0.size();
    }

    @Test(timeout = 4000)
    public void test5054() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Parser parser0 = Parser.htmlParser();
        Attributes attributes0 = new Attributes();
        ParseSettings parseSettings0 = ParseSettings.preserveCase;
        Tag tag0 = Tag.valueOf("caption", parseSettings0);
        FormElement formElement0 = new FormElement(tag0, "caption", attributes0);
        List<Node> list0 = htmlTreeBuilder0.parseFragment("", formElement0, "", parser0);
        list0.size();
    }

    @Test(timeout = 4000)
    public void test5155() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Attributes attributes0 = new Attributes();
        Tag tag0 = Tag.valueOf("colgroup");
        FormElement formElement0 = new FormElement(tag0, "colgroup", attributes0);
        Parser parser0 = Parser.htmlParser();
        List<Node> list0 = htmlTreeBuilder0.parseFragment("colgroup", formElement0, "colgroup", parser0);
        list0.size();
    }

    @Test(timeout = 4000)
    public void test5256() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Attributes attributes0 = new Attributes();
        ParseSettings parseSettings0 = ParseSettings.preserveCase;
        Tag tag0 = Tag.valueOf("head", parseSettings0);
        FormElement formElement0 = new FormElement(tag0, "head", attributes0);
        Parser parser0 = Parser.xmlParser();
        List<Node> list0 = htmlTreeBuilder0.parseFragment("video", formElement0, "video", parser0);
        list0.size();
    }

    @Test(timeout = 4000)
    public void test5357() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Tag tag0 = Tag.valueOf("frameset");
        Attributes attributes0 = new Attributes();
        FormElement formElement0 = new FormElement(tag0, "pSograss", attributes0);
        Parser parser0 = new Parser(htmlTreeBuilder0);
        List<Node> list0 = htmlTreeBuilder0.parseFragment("pSograss", formElement0, "pSograss", parser0);
        list0.isEmpty();
    }

    @Test(timeout = 4000)
    public void test5458() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Parser parser0 = new Parser(htmlTreeBuilder0);
        Tag tag0 = Tag.valueOf("html");
        Attributes attributes0 = new Attributes();
        FormElement formElement0 = new FormElement(tag0, "html", attributes0);
        List<Node> list0 = htmlTreeBuilder0.parseFragment("html", formElement0, "html", parser0);
        list0.size();
    }

    @Test(timeout = 4000)
    public void test5559() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Parser parser0 = new Parser(htmlTreeBuilder0);
        Document document0 = parser0.parseInput("ersion", "ersion");
        htmlTreeBuilder0.insert(document0);
        htmlTreeBuilder0.resetInsertionMode();
    }

    @Test(timeout = 4000)
    public void test5660() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Parser parser0 = new Parser(htmlTreeBuilder0);
        parser0.parseInput("table", "0:r}f");
        String[] stringArray0 = new String[6];
        stringArray0[2] = "0:r}f";
        stringArray0[4] = "YQea~E";
        stringArray0[5] = "body";
        boolean boolean0 = htmlTreeBuilder0.inScope("YQea~E", stringArray0);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test5761() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        ArrayList<Element> arrayList0 = new ArrayList<Element>();
        htmlTreeBuilder0.stack = arrayList0;
        htmlTreeBuilder0.inSelectScope((String) null);
    }

    @Test(timeout = 4000)
    public void test5862() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Attributes attributes0 = new Attributes();
        Tag tag0 = Tag.valueOf("th");
        FormElement formElement0 = new FormElement(tag0, "th", attributes0);
        Parser parser0 = Parser.xmlParser();
        List<Node> list0 = htmlTreeBuilder0.parseFragment("th", formElement0, "th", parser0);
        list0.size();
    }

    @Test(timeout = 4000)
    public void test5863() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Attributes attributes0 = new Attributes();
        Tag tag0 = Tag.valueOf("th");
        FormElement formElement0 = new FormElement(tag0, "th", attributes0);
        Parser parser0 = Parser.xmlParser();
        List<Node> list0 = htmlTreeBuilder0.parseFragment("th", formElement0, "th", parser0);
        boolean boolean0 = htmlTreeBuilder0.inSelectScope("th");
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test5964() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Attributes attributes0 = new Attributes();
        Tag tag0 = Tag.valueOf("th");
        FormElement formElement0 = new FormElement(tag0, "th", attributes0);
        Parser parser0 = Parser.xmlParser();
        List<Node> list0 = htmlTreeBuilder0.parseFragment("th", formElement0, "th", parser0);
        list0.size();
    }

    @Test(timeout = 4000)
    public void test5965() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Attributes attributes0 = new Attributes();
        Tag tag0 = Tag.valueOf("th");
        FormElement formElement0 = new FormElement(tag0, "th", attributes0);
        Parser parser0 = Parser.xmlParser();
        List<Node> list0 = htmlTreeBuilder0.parseFragment("th", formElement0, "th", parser0);
        htmlTreeBuilder0.insert(formElement0);
        boolean boolean0 = htmlTreeBuilder0.inSelectScope("th");
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test6066() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Parser parser0 = new Parser(htmlTreeBuilder0);
        parser0.parseInput("u^Ra0", "u^Ra0");
        htmlTreeBuilder0.processStartTag("p");
        htmlTreeBuilder0.generateImpliedEndTags("06yqFZ+");
    }

    @Test(timeout = 4000)
    public void test6167() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Parser parser0 = new Parser(htmlTreeBuilder0);
        parser0.parseInput("scr(pt", "scr(pt");
        Element element0 = htmlTreeBuilder0.removeLastFormattingElement();
        assertNotNull(element0);
    }

    @Test(timeout = 4000)
    public void test6268() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Parser parser0 = new Parser(htmlTreeBuilder0);
        Document document0 = parser0.parseInput("S", "S");
        htmlTreeBuilder0.processStartTag("S");
        htmlTreeBuilder0.pushActiveFormattingElements(document0);
        boolean boolean0 = htmlTreeBuilder0.processStartTag("yNZ?$7;Vm~h");
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test6369() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Parser parser0 = new Parser(htmlTreeBuilder0);
        parser0.parseInput("code", "code");
        htmlTreeBuilder0.processStartTag("code");
        htmlTreeBuilder0.processStartTag("code");
        htmlTreeBuilder0.processStartTag("code");
        boolean boolean0 = htmlTreeBuilder0.processStartTag("code");
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test6470() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Parser parser0 = new Parser(htmlTreeBuilder0);
        Document document0 = parser0.parseInput("&;%@*u81vc", "&;%@*u81vc");
        Document document1 = parser0.parseInput("&;%@*u81vc", ":not6");
        document1.addClass("h6");
        htmlTreeBuilder0.pushActiveFormattingElements(document1);
        htmlTreeBuilder0.pushActiveFormattingElements(document0);
        document0.childNodeSize();
        assertEquals(0, document0.childNodeSize());
    }

    @Test(timeout = 4000)
    public void test6571() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Parser parser0 = Parser.htmlParser();
        parser0.setTreeBuilder(htmlTreeBuilder0);
        Document document0 = parser0.parseInput("X", "7jA}Go");
        htmlTreeBuilder0.pushActiveFormattingElements((Element) null);
        htmlTreeBuilder0.pushActiveFormattingElements(document0);
        htmlTreeBuilder0.reconstructFormattingElements();
    }

    @Test(timeout = 4000)
    public void test6672() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Parser parser0 = Parser.htmlParser();
        Parser parser1 = parser0.setTreeBuilder(htmlTreeBuilder0);
        Document document0 = parser1.parseInput("]", "]");
        htmlTreeBuilder0.pushActiveFormattingElements(document0);
        htmlTreeBuilder0.pushActiveFormattingElements(document0);
        htmlTreeBuilder0.reconstructFormattingElements();
    }

    @Test(timeout = 4000)
    public void test6773() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Parser parser0 = Parser.htmlParser();
        Parser parser1 = parser0.setTreeBuilder(htmlTreeBuilder0);
        Document document0 = parser1.parseInput("", "");
        htmlTreeBuilder0.pushActiveFormattingElements(document0);
        htmlTreeBuilder0.clearFormattingElementsToLastMarker();
    }

    @Test(timeout = 4000)
    public void test6874() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Parser parser0 = Parser.htmlParser();
        Parser parser1 = parser0.setTreeBuilder(htmlTreeBuilder0);
        Document document0 = parser1.parseInput("", "");
        htmlTreeBuilder0.pushActiveFormattingElements(document0);
        htmlTreeBuilder0.removeFromActiveFormattingElements(document0);
        document0.childNodeSize();
        assertEquals(1, document0.childNodeSize());
    }

    @Test(timeout = 4000)
    public void test6975() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Parser parser0 = new Parser(htmlTreeBuilder0);
        Document document0 = parser0.parseInput("param", "param");
        Document document1 = Document.createShell("aside");
        htmlTreeBuilder0.pushActiveFormattingElements(document1);
        htmlTreeBuilder0.removeFromActiveFormattingElements(document0);
        document0.childNodeSize();
        assertEquals(0, document0.childNodeSize());
    }

    @Test(timeout = 4000)
    public void test7076() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Parser parser0 = Parser.htmlParser();
        Parser parser1 = parser0.setTreeBuilder(htmlTreeBuilder0);
        Document document0 = parser1.parseInput("plaintext", "plaintext");
        htmlTreeBuilder0.pushActiveFormattingElements(document0);
        Element element0 = htmlTreeBuilder0.getActiveFormattingElement("plaintext");
    }

    @Test(timeout = 4000)
    public void test7177() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Parser parser0 = new Parser(htmlTreeBuilder0);
        parser0.parseInput("embed", "embed");
        htmlTreeBuilder0.processStartTag("object");
        Element element0 = htmlTreeBuilder0.getActiveFormattingElement("embed");
        assertNotNull(element0);
    }

    @Test(timeout = 4000)
    public void test7278() throws Throwable {
        HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
        Parser parser0 = new Parser(htmlTreeBuilder0);
        Document document0 = parser0.parseInput("7a^(?d@9fr{g2=", "7a^(?d@9fr{g2=");
        Element element0 = document0.createElement("7a^(?d@9fr{g2=");
        htmlTreeBuilder0.pushActiveFormattingElements(element0);
        Element element1 = htmlTreeBuilder0.getActiveFormattingElement("7a^(?d@9fr{g2=");
    }
}
