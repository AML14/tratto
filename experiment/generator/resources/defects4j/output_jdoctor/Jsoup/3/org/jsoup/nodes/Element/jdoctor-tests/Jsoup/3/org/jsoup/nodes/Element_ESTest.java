/*
 * This file was automatically generated by EvoSuite
 * Mon Nov 06 00:11:23 GMT 2023
 */
package org.jsoup.nodes;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.util.NoSuchElementException;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class Element_ESTest extends Element_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        Document document0 = new Document(")I-k+2/TyiSO");
        document0.html(")I-k+2/TyiSO");
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        Document document0 = new Document("script");
        assertTrue(document0.hasText());
        org.jsoup.nodes.Element default0;
        default0 = document0.appendText("script");
        document0.prependElement("script");
        assertTrue(document0.hasText());
        org.jsoup.nodes.Element default1;
        default1 = document0.prependText("script");
        String string0 = document0.text();
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        Document document0 = new Document("#*cl08)A");
        Element element0 = document0.attr("id", "#*cl08)A");
        Element element1 = element0.getElementById("#*cl08)A");
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        Document document0 = new Document("dB){nb");
        Element element0 = document0.prependElement("P");
        element0.nodeName();
    }

    @Test(timeout = 4000)
    public void test034() throws Throwable {
        Document document0 = new Document("dB){nb");
        Element element0 = document0.prependElement("P");
        assertTrue(document0.hasText());
        org.jsoup.nodes.Element default2;
        default2 = document0.prependText(" ");
        String string0 = document0.text();
    }

    @Test(timeout = 4000)
    public void test045() throws Throwable {
        Document document0 = new Document("Dq>se,A");
        Elements elements0 = document0.getElementsByAttributeValueEnding("Dq>se,A", "Dq>se,A");
        elements0.isEmpty();
    }

    @Test(timeout = 4000)
    public void test056() throws Throwable {
        Document document0 = new Document("L");
        Elements elements0 = document0.select("org.jsoup.nodes.Element");
        elements0.isEmpty();
    }

    @Test(timeout = 4000)
    public void test067() throws Throwable {
        Document document0 = new Document("3");
        document0.child((-1792631324));
    }

    @Test(timeout = 4000)
    public void test078() throws Throwable {
        Tag tag0 = Tag.valueOf("PARAM");
        Element element0 = new Element(tag0, "PARAM");
        Element element1 = element0.prependElement("PARAM");
        String string0 = element1.outerHtml();
    }

    @Test(timeout = 4000)
    public void test089() throws Throwable {
        Document document0 = new Document("[");
        Elements elements0 = document0.getElementsByAttributeValueContaining("[", "[");
        elements0.size();
    }

    @Test(timeout = 4000)
    public void test0910() throws Throwable {
        Document document0 = new Document("mM0V7i]N");
        Elements elements0 = document0.getElementsByAttributeValueNot("mM0V7i]N", "mM0V7i]N");
        elements0.isEmpty();
    }

    @Test(timeout = 4000)
    public void test1011() throws Throwable {
        Document document0 = new Document("4p");
        String string0 = document0.toString();
    }

    @Test(timeout = 4000)
    public void test1112() throws Throwable {
        Document document0 = new Document("<");
        Elements elements0 = document0.getElementsByAttribute("<");
        elements0.size();
    }

    @Test(timeout = 4000)
    public void test1213() throws Throwable {
        Document document0 = new Document("$");
        Element element0 = document0.addClass("$");
        element0.baseUri();
    }

    @Test(timeout = 4000)
    public void test1314() throws Throwable {
        Document document0 = new Document("4p");
        Elements elements0 = document0.getElementsByAttributeValue("4p", "4p");
        elements0.size();
    }

    @Test(timeout = 4000)
    public void test1415() throws Throwable {
        Document document0 = new Document(" />");
        document0.siblingElements();
    }

    @Test(timeout = 4000)
    public void test1516() throws Throwable {
        Document document0 = new Document("!p{uwkz<");
        Elements elements0 = document0.getElementsByAttributeValueStarting("!p{uwkz<", "!p{uwkz<");
        elements0.size();
    }

    @Test(timeout = 4000)
    public void test1617() throws Throwable {
        Tag tag0 = Tag.valueOf("4p");
        Element element0 = new Element(tag0, "4p");
        String string0 = element0.nodeName();
    }

    @Test(timeout = 4000)
    public void test1718() throws Throwable {
        Document document0 = new Document("4p");
        Element element0 = document0.removeClass("4p");
        element0.tagName();
    }

    @Test(timeout = 4000)
    public void test1819() throws Throwable {
        Document document0 = new Document("L");
        Elements elements0 = document0.getAllElements();
        elements0.isEmpty();
    }

    @Test(timeout = 4000)
    public void test1920() throws Throwable {
        Document document0 = new Document("?");
        assertTrue(3706 == 0);
        org.jsoup.select.Elements elements0;
        elements0 = document0.getElementsByIndexLessThan(3706);
        elements0.size();
    }

    @Test(timeout = 4000)
    public void test2021() throws Throwable {
        Document document0 = new Document("value");
        assertTrue((-2) == 0);
        org.jsoup.select.Elements elements0;
        elements0 = document0.getElementsByIndexGreaterThan((-2));
        elements0.isEmpty();
    }

    @Test(timeout = 4000)
    public void test2122() throws Throwable {
        Document document0 = new Document("$");
        assertTrue(612 == 0);
        org.jsoup.select.Elements elements0;
        elements0 = document0.getElementsByIndexEquals(612);
        elements0.size();
    }

    @Test(timeout = 4000)
    public void test2223() throws Throwable {
        Document document0 = new Document("#*cl08)A");
        Element element0 = document0.getElementById("#*cl08)A");
    }

    @Test(timeout = 4000)
    public void test2324() throws Throwable {
        Document document0 = new Document("?");
        Elements elements0 = document0.parents();
        elements0.size();
    }

    @Test(timeout = 4000)
    public void test2425() throws Throwable {
        Document document0 = new Document("script");
        Element element0 = document0.prependElement("script");
        element0.addChild(document0);
        Elements elements0 = document0.parents();
        elements0.size();
    }

    @Test(timeout = 4000)
    public void test2526() throws Throwable {
        Document document0 = new Document("}a3L|0+W~");
        document0.addChild(document0);
        Element element0 = document0.previousElementSibling();
    }

    @Test(timeout = 4000)
    public void test2627() throws Throwable {
        Document document0 = new Document("<");
        assertTrue(document0.hasText());
        org.jsoup.nodes.Element default3;
        default3 = document0.prependText("<");
        document0.normalise();
    }

    @Test(timeout = 4000)
    public void test2728() throws Throwable {
        Document document0 = new Document("$W");
        Element element0 = document0.prependElement("$W");
        Element element1 = element0.nextElementSibling();
    }

    @Test(timeout = 4000)
    public void test2829() throws Throwable {
        Document document0 = new Document("4p");
        Element element0 = document0.appendChild(document0);
        Element element1 = document0.prependElement("4p");
        Element element2 = element1.nextElementSibling();
    }

    @Test(timeout = 4000)
    public void test2930() throws Throwable {
        Document document0 = new Document("}a3L|0+W~");
        document0.addChild(document0);
        document0.prependElement("}a3L|0+W~");
        Element element0 = document0.previousElementSibling();
        element0.nodeName();
    }

    @Test(timeout = 4000)
    public void test3031() throws Throwable {
        Document document0 = new Document("982");
        Element element0 = document0.prependElement("982");
        Element element1 = element0.firstElementSibling();
    }

    @Test(timeout = 4000)
    public void test3132() throws Throwable {
        Tag tag0 = Tag.valueOf("4p");
        Element element0 = new Element(tag0, "4p");
        Element element1 = element0.appendChild(element0);
        Element element2 = element1.appendElement("4p");
        Element element3 = element2.firstElementSibling();
    }

    @Test(timeout = 4000)
    public void test3234() throws Throwable {
        Document document0 = new Document("L");
        Element element0 = document0.prependElement("L");
        Integer integer0 = element0.elementSiblingIndex();
    }

    @Test(timeout = 4000)
    public void test3335() throws Throwable {
        Document document0 = new Document("$");
        Element element0 = document0.prependElement("$");
        Element element1 = element0.lastElementSibling();
    }

    @Test(timeout = 4000)
    public void test3436() throws Throwable {
        Document document0 = new Document("964");
        document0.prependChild(document0);
        Element element0 = document0.prependElement("964");
        Element element1 = element0.lastElementSibling();
    }

    @Test(timeout = 4000)
    public void test3537() throws Throwable {
        Document document0 = new Document("script");
        Element element0 = document0.prependElement("script");
        element0.addChild(document0);
        assertTrue(document0.hasText());
        org.jsoup.nodes.Element default4;
        default4 = document0.prependText("script");
        document0.text();
    }

    @Test(timeout = 4000)
    public void test3638() throws Throwable {
        Document document0 = new Document("XBq\fJ-9m7]J5?@>");
        Comment comment0 = new Comment("XBq\fJ-9m7]J5?@>", "XBq\fJ-9m7]J5?@>");
        document0.appendChild(comment0);
        String string0 = document0.text();
    }

    @Test(timeout = 4000)
    public void test3739() throws Throwable {
        Document document0 = new Document("script");
        document0.prependElement("script");
        String string0 = document0.text();
    }

    @Test(timeout = 4000)
    public void test3840() throws Throwable {
        Document document0 = new Document("L");
        Element element0 = document0.prependElement("L");
        boolean boolean0 = element0.preserveWhitespace();
    }

    @Test(timeout = 4000)
    public void test3941() throws Throwable {
        Document document0 = new Document("c");
        DataNode dataNode0 = DataNode.createFromEncoded("c", "c");
        document0.addChild(dataNode0);
        boolean boolean0 = document0.hasText();
    }

    @Test(timeout = 4000)
    public void test4042() throws Throwable {
        Document document0 = new Document("");
        assertTrue(document0.hasText());
        org.jsoup.nodes.Element default5;
        default5 = document0.appendText("");
        boolean boolean0 = document0.hasText();
    }

    @Test(timeout = 4000)
    public void test4143() throws Throwable {
        Document document0 = new Document("O)@ofCBU");
        document0.prependElement("O)@ofCBU");
        boolean boolean0 = document0.hasText();
    }

    @Test(timeout = 4000)
    public void test4244() throws Throwable {
        Document document0 = new Document("O)@ofCBU");
        Element element0 = document0.prependElement("O)@ofCBU");
        assertTrue(element0.hasText());
        org.jsoup.nodes.Element default6;
        default6 = element0.text("O)@ofCBU");
        boolean boolean0 = document0.hasText();
    }

    @Test(timeout = 4000)
    public void test4345() throws Throwable {
        Document document0 = new Document("!o?`4_;LM#Z0DXM");
        DataNode dataNode0 = DataNode.createFromEncoded("!o?`4_;LM#Z0DXM", "!o?`4_;LM#Z0DXM");
        document0.addChild(dataNode0);
        String string0 = document0.data();
    }

    @Test(timeout = 4000)
    public void test4446() throws Throwable {
        Document document0 = new Document("$>");
        document0.prependElement("$>");
        String string0 = document0.data();
    }

    @Test(timeout = 4000)
    public void test4547() throws Throwable {
        Document document0 = new Document("W");
        assertTrue(document0.hasText());
        org.jsoup.nodes.Element element0;
        element0 = document0.appendText("W");
        String string0 = element0.data();
    }

    @Test(timeout = 4000)
    public void test4648() throws Throwable {
        Document document0 = new Document("$W");
        document0.toggleClass("$W");
        String string0 = document0.className();
    }

    @Test(timeout = 4000)
    public void test4749() throws Throwable {
        Document document0 = new Document("");
        boolean boolean0 = document0.hasClass("");
    }

    @Test(timeout = 4000)
    public void test4850() throws Throwable {
        Document document0 = new Document("");
        Element element0 = document0.toggleClass("");
        element0.isBlock();
    }

    @Test(timeout = 4000)
    public void test4951() throws Throwable {
        Document document0 = new Document("j#");
        String string0 = document0.val();
    }

    @Test(timeout = 4000)
    public void test5052() throws Throwable {
        Document document0 = new Document("4p");
        Element element0 = document0.createElement("textarea");
        String string0 = element0.val();
    }

    @Test(timeout = 4000)
    public void test5153() throws Throwable {
        Document document0 = new Document("W");
        Element element0 = document0.val("W");
        element0.isBlock();
    }

    @Test(timeout = 4000)
    public void test5254() throws Throwable {
        Tag tag0 = Tag.valueOf("textarea");
        Attributes attributes0 = new Attributes();
        Element element0 = new Element(tag0, "textarea", attributes0);
        Element element1 = element0.val("textarea");
        element1.isBlock();
    }

    @Test(timeout = 4000)
    public void test5355() throws Throwable {
        Document document0 = new Document("P");
        document0.prependElement("meta");
        String string0 = document0.outerHtml();
    }

    @Test(timeout = 4000)
    public void test5456() throws Throwable {
        Document document0 = new Document("e[");
        document0.prependElement("e[");
        String string0 = document0.outerHtml();
    }

    @Test(timeout = 4000)
    public void test5557() throws Throwable {
        Document document0 = new Document("e[");
        document0.prependElement("e[");
        assertTrue(document0.hasText());
        org.jsoup.nodes.Element element0;
        element0 = document0.prependText("e[");
        String string0 = element0.outerHtml();
    }

    @Test(timeout = 4000)
    public void test5658() throws Throwable {
        Document document0 = new Document("W");
        assertTrue(document0.hasText());
        org.jsoup.nodes.Element document1;
        document1 = document0.appendText("W");
        StringBuilder stringBuilder0 = new StringBuilder("W");
        document1.outerHtml(stringBuilder0);
        stringBuilder0.toString();
    }

    @Test(timeout = 4000)
    public void test5759() throws Throwable {
        Document document0 = new Document("script");
        document0.prependElement("script");
        String string0 = document0.outerHtml();
    }

    @Test(timeout = 4000)
    public void test5860() throws Throwable {
        Document document0 = new Document("J<dzT,U'~mWOg?*4[T");
        boolean boolean0 = document0.equals("J<dzT,U'~mWOg?*4[T");
    }
}
