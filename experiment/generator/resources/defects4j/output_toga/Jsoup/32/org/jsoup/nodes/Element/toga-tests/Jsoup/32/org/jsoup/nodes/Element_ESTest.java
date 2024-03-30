/*
 * This file was automatically generated by EvoSuite
 * Sun Oct 15 21:24:27 GMT 2023
 */
package org.jsoup.nodes;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.regex.Pattern;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.nodes.XmlDeclaration;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class Element_ESTest extends Element_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        Tag tag0 = Tag.valueOf("i5BtR6IA[g?Gz];(j#4");
        Element element0 = new Element(tag0, "");
        Elements elements0 = element0.getElementsByAttributeValueMatching(".h?T2z*^,Pl%KuX'", "");
        elements0.isEmpty();
        assertTrue(elements0.isEmpty());
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        Tag tag0 = Tag.valueOf("{sT*zNV");
        Attributes attributes0 = new Attributes();
        Element element0 = new Element(tag0, "{sT*zNV", attributes0);
        Elements elements0 = element0.getElementsByTag("~Ob6,y4V$z_5+E<<W x");
        elements0.isEmpty();
        assertTrue(elements0.isEmpty());
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        Document document0 = new Document("Z\"x%iJ&`M[0Y_* ");
        String string0 = document0.toString();
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        Document document0 = new Document("");
        Element element0 = document0.prependText("=OM");
        element0.baseUri();
        assertNotNull(element0.baseUri());
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        Document document0 = new Document("s+");
        Element element0 = document0.prepend("");
        element0.childNodeSize();
        assertEquals(0, element0.childNodeSize());
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        Document document0 = new Document("s+");
        String string0 = document0.nodeName();
        assertNotNull(string0);
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        Tag tag0 = Tag.valueOf("MqLI;\"");
        Element element0 = new Element(tag0, "MqLI;\"");
        Element element1 = element0.prependElement("MqLI;\"");
        element1.childNodeSize();
    }

    @Test(timeout = 4000)
    public void test067() throws Throwable {
        Tag tag0 = Tag.valueOf("MqLI;\"");
        Element element0 = new Element(tag0, "MqLI;\"");
        Element element1 = element0.prependElement("MqLI;\"");
        Element element2 = element1.after((Node) element0);
        Element element3 = element2.nextElementSibling();
    }

    @Test(timeout = 4000)
    public void test068() throws Throwable {
        Tag tag0 = Tag.valueOf("MqLI;\"");
        Element element0 = new Element(tag0, "MqLI;\"");
        Element element1 = element0.prependElement("MqLI;\"");
        Element element2 = element1.after((Node) element0);
        Element element3 = element2.nextElementSibling();
        element3.childNodeSize();
    }

    @Test(timeout = 4000)
    public void test079() throws Throwable {
        Document document0 = new Document("textarea");
        LinkedHashSet<XmlDeclaration> linkedHashSet0 = new LinkedHashSet<XmlDeclaration>();
        Element element0 = document0.insertChildren((-1), linkedHashSet0);
        element0.isBlock();
    }

    @Test(timeout = 4000)
    public void test0810() throws Throwable {
        Tag tag0 = Tag.valueOf("u.DPmaXvo'P");
        Attributes attributes0 = new Attributes();
        Element element0 = new Element(tag0, "yu/]", attributes0);
        String string0 = element0.id();
        assertNotNull(string0);
    }

    @Test(timeout = 4000)
    public void test0911() throws Throwable {
        Document document0 = new Document("o");
        String string0 = document0.html();
        assertNotNull(string0);
    }

    @Test(timeout = 4000)
    public void test1012() throws Throwable {
        Document document0 = new Document("kbd");
        Elements elements0 = document0.getElementsMatchingText("kbd");
        elements0.isEmpty();
        assertFalse(elements0.isEmpty());
    }

    @Test(timeout = 4000)
    public void test1113() throws Throwable {
        Document document0 = new Document("#-^vIDi/");
        Elements elements0 = document0.getElementsMatchingText("");
        elements0.isEmpty();
        assertTrue(elements0.isEmpty());
    }

    @Test(timeout = 4000)
    public void test1214() throws Throwable {
        Document document0 = new Document("#-^vIDi/");
        Pattern pattern0 = Pattern.compile("", (-1));
        Elements elements0 = document0.getElementsMatchingOwnText(pattern0);
        elements0.size();
        assertEquals(1, elements0.size());
    }

    @Test(timeout = 4000)
    public void test1315() throws Throwable {
        Document document0 = new Document("p^X8qT");
        Element element0 = document0.appendText("p^X8qT");
        Elements elements0 = element0.getElementsContainingOwnText("p^X8qT");
        elements0.size();
        assertEquals(1, elements0.size());
    }

    @Test(timeout = 4000)
    public void test1416() throws Throwable {
        Tag tag0 = Tag.valueOf("P>");
        Attributes attributes0 = new Attributes();
        Element element0 = new Element(tag0, "z@9R#W8%Q&dA}l\"", attributes0);
        Elements elements0 = element0.getElementsByIndexLessThan(41);
        elements0.size();
        assertEquals(1, elements0.size());
    }

    @Test(timeout = 4000)
    public void test1517() throws Throwable {
        Document document0 = new Document("");
        Pattern pattern0 = Pattern.compile("br");
        Elements elements0 = document0.getElementsByAttributeValueMatching("=_~59Q4Y6Kv", pattern0);
        elements0.size();
        assertEquals(1, elements0.size());
    }

    @Test(timeout = 4000)
    public void test1618() throws Throwable {
        Tag tag0 = Tag.valueOf("9");
        Element element0 = new Element(tag0, "");
        Elements elements0 = element0.getElementsByAttribute("9");
        elements0.size();
        assertEquals(0, elements0.size());
    }

    @Test(timeout = 4000)
    public void test1719() throws Throwable {
        Document document0 = new Document("s>");
        boolean boolean0 = document0.equals("s>");
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test1820() throws Throwable {
        Document document0 = new Document("@sNz]");
        LinkedHashSet<String> linkedHashSet0 = new LinkedHashSet<String>();
        Element element0 = document0.classNames((Set<String>) linkedHashSet0);
        element0.childNodeSize();
        assertEquals(0, element0.childNodeSize());
    }

    @Test(timeout = 4000)
    public void test1921() throws Throwable {
        Document document0 = new Document("=6+\"|M&Us4");
        String string0 = document0.className();
    }

    @Test(timeout = 4000)
    public void test2022() throws Throwable {
        Document document0 = new Document("#-^vIDi/");
        Elements elements0 = document0.children();
        elements0.isEmpty();
        assertTrue(elements0.isEmpty());
    }

    @Test(timeout = 4000)
    public void test2123() throws Throwable {
        Tag tag0 = Tag.valueOf("[");
        Element element0 = new Element(tag0, "[");
        Element element1 = element0.appendText("[");
        Element element2 = element1.appendElement("Children collection to be inserted must not be null.");
        element2.nodeName();
        assertNotNull(element2.nodeName());
    }

    @Test(timeout = 4000)
    public void test2124() throws Throwable {
        Tag tag0 = Tag.valueOf("[");
        Element element0 = new Element(tag0, "[");
        Element element1 = element0.appendText("[");
        Element element2 = element1.appendElement("Children collection to be inserted must not be null.");
        element2.siblingIndex();
    }

    @Test(timeout = 4000)
    public void test2225() throws Throwable {
        Document document0 = new Document("@sNz]");
        Element element0 = document0.appendElement("id");
        element0.nodeName();
        assertNotNull(element0.nodeName());
    }

    @Test(timeout = 4000)
    public void test2326() throws Throwable {
        Document document0 = new Document("#-^vIDi/");
        Document document1 = (Document) document0.addClass("");
        document1.quirksMode();
        assertNotNull(document1.quirksMode());
    }

    @Test(timeout = 4000)
    public void test2427() throws Throwable {
        Tag tag0 = Tag.valueOf("R}&RQ^\"rV");
        Element element0 = new Element(tag0, "R}&RQ^\"rV");
        element0.wrap("");
    }

    @Test(timeout = 4000)
    public void test2528() throws Throwable {
        Tag tag0 = Tag.valueOf("org.jsoup.select.Elements");
        Element element0 = new Element(tag0, "Object must not be null");
        element0.removeClass((String) null);
    }

    @Test(timeout = 4000)
    public void test2629() throws Throwable {
        Tag tag0 = Tag.valueOf("pattern syntax error: ");
        Element element0 = new Element(tag0, "pattern syntax error: ");
        element0.prependChild((Node) null);
    }

    @Test(timeout = 4000)
    public void test2730() throws Throwable {
        Document document0 = new Document("org.jsoup.nodes.Attributes$Dataset$EntrySet");
        document0.prepend((String) null);
    }

    @Test(timeout = 4000)
    public void test2831() throws Throwable {
        Document document0 = new Document("s+");
        StringBuilder stringBuilder0 = new StringBuilder("&5f5''UE");
        Document.OutputSettings document_OutputSettings0 = new Document.OutputSettings();
        document0.outerHtmlHead(stringBuilder0, (-1433506051), document_OutputSettings0);
    }

    @Test(timeout = 4000)
    public void test2932() throws Throwable {
        Document document0 = new Document("F>f9~V");
        document0.lastElementSibling();
    }

    @Test(timeout = 4000)
    public void test3033() throws Throwable {
        Tag tag0 = Tag.valueOf("P>");
        Attributes attributes0 = new Attributes();
        Element element0 = new Element(tag0, "z@9R#W8%Q&dA}l\"", attributes0);
        element0.getElementsMatchingText((Pattern) null);
    }

    @Test(timeout = 4000)
    public void test3134() throws Throwable {
        Document document0 = new Document("");
        document0.getElementsByClass("");
    }

    @Test(timeout = 4000)
    public void test3235() throws Throwable {
        Tag tag0 = Tag.valueOf("i5BtR6IA[g?Gz];(j#4");
        Element element0 = new Element(tag0, "");
        element0.getElementsByAttributeValueNot("", "");
    }

    @Test(timeout = 4000)
    public void test3336() throws Throwable {
        Document document0 = new Document("k0D>");
        document0.getElementsByAttributeValueMatching("VW1@_W)gAFiG68", (String) null);
    }

    @Test(timeout = 4000)
    public void test3437() throws Throwable {
        Document document0 = new Document("$");
        document0.getElementsByAttributeValueMatching("%(\"jTc:_pQX^", "%(\"jTc:_pQX^");
    }

    @Test(timeout = 4000)
    public void test3538() throws Throwable {
        Tag tag0 = Tag.valueOf("00}nq%<");
        Element element0 = new Element(tag0, "m~");
        element0.getElementsByAttributeValueEnding((String) null, (String) null);
    }

    @Test(timeout = 4000)
    public void test3639() throws Throwable {
        Document document0 = new Document("");
        document0.firstElementSibling();
    }

    @Test(timeout = 4000)
    public void test3740() throws Throwable {
        Document document0 = new Document("");
        document0.classNames((Set<String>) null);
    }

    @Test(timeout = 4000)
    public void test3841() throws Throwable {
        Document document0 = new Document("p>");
        document0.child(1);
    }

    @Test(timeout = 4000)
    public void test3942() throws Throwable {
        Document document0 = new Document("#-^vIDi/");
        document0.before((Node) document0);
    }

    @Test(timeout = 4000)
    public void test4043() throws Throwable {
        Tag tag0 = Tag.valueOf("}&6?W3q");
        Attributes attributes0 = new Attributes();
        Element element0 = new Element(tag0, "}&6?W3q", attributes0);
        Element element1 = element0.prependChild(element0);
        element1.after((Node) element0);
    }

    @Test(timeout = 4000)
    public void test4144() throws Throwable {
        Tag tag0 = Tag.valueOf("k^$vCI(V,^=m,v\"6@");
        Attributes attributes0 = new Attributes();
        Element element0 = new Element(tag0, "k^$vCI(V,^=m,v\"6@", attributes0);
        DataNode dataNode0 = new DataNode("*W", "*W");
        element0.after((Node) dataNode0);
    }

    @Test(timeout = 4000)
    public void test4245() throws Throwable {
        Document document0 = new Document("");
        document0.after("org.jsoup.select.Evaluator$Matches");
    }

    @Test(timeout = 4000)
    public void test4346() throws Throwable {
        Attributes attributes0 = new Attributes();
        Element element0 = null;
        element0 = new Element((Tag) null, (String) null, attributes0);
    }

    @Test(timeout = 4000)
    public void test4447() throws Throwable {
        Element element0 = null;
        element0 = new Element((Tag) null, "g0*hk7HSCqb");
    }

    @Test(timeout = 4000)
    public void test4548() throws Throwable {
        Tag tag0 = Tag.valueOf("00}nq%<");
        Element element0 = new Element(tag0, "m~");
        boolean boolean0 = element0.hasClass("w9Bg@h*?}=2|L");
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test4649() throws Throwable {
        Tag tag0 = Tag.valueOf("}3ad\"bN(x]");
        Element element0 = new Element(tag0, "}3ad\"bN(x]");
        Set<String> set0 = element0.classNames();
        set0.isEmpty();
    }

    @Test(timeout = 4000)
    public void test4750() throws Throwable {
        Document document0 = new Document("");
        boolean boolean0 = document0.hasText();
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test4851() throws Throwable {
        Tag tag0 = Tag.valueOf("R}&RQ^\"rV");
        Element element0 = new Element(tag0, "R}&RQ^\"rV");
        Element element1 = element0.getElementById("R}&RQ^\"rV");
    }

    @Test(timeout = 4000)
    public void test4952() throws Throwable {
        Document document0 = new Document("kbd");
        Integer integer0 = document0.elementSiblingIndex();
    }

    @Test(timeout = 4000)
    public void test5053() throws Throwable {
        Document document0 = new Document("p^X8qT");
        Element element0 = document0.previousElementSibling();
        assertNotNull(element0);
    }

    @Test(timeout = 4000)
    public void test5154() throws Throwable {
        Document document0 = new Document("p^X8qT");
        document0.appendText("p^X8qT");
        List<TextNode> list0 = document0.textNodes();
        list0.isEmpty();
    }

    @Test(timeout = 4000)
    public void test5255() throws Throwable {
        Document document0 = new Document("p>");
        Elements elements0 = document0.getElementsByAttributeStarting("p>");
        elements0.size();
        assertEquals(0, elements0.size());
    }

    @Test(timeout = 4000)
    public void test5356() throws Throwable {
        Document document0 = new Document("");
        Elements elements0 = document0.getElementsContainingText("=_~59Q4Y6Kv");
        elements0.isEmpty();
        assertTrue(elements0.isEmpty());
    }

    @Test(timeout = 4000)
    public void test5457() throws Throwable {
        Document document0 = new Document("form");
        document0.getElementsByTag("");
    }

    @Test(timeout = 4000)
    public void test5558() throws Throwable {
        Document document0 = new Document("form");
        Elements elements0 = document0.getElementsByAttributeValueEnding("org.jsoup.select.Collector$Accumulator", "form");
        elements0.size();
        assertEquals(1, elements0.size());
    }

    @Test(timeout = 4000)
    public void test5659() throws Throwable {
        Document document0 = new Document("");
        Elements elements0 = document0.getElementsByIndexLessThan((-1070));
        elements0.isEmpty();
        assertTrue(elements0.isEmpty());
    }

    @Test(timeout = 4000)
    public void test5760() throws Throwable {
        Document document0 = new Document("#-^vIDi/");
        Elements elements0 = document0.parents();
        elements0.size();
        assertEquals(1, elements0.size());
    }

    @Test(timeout = 4000)
    public void test5861() throws Throwable {
        Document document0 = new Document("s+");
        document0.prependChild(document0);
        // Undeclared exception!
        document0.getElementsByAttributeValueMatching("", "");
    }

    @Test(timeout = 4000)
    public void test5962() throws Throwable {
        Document document0 = new Document("p^X8qT");
        Elements elements0 = document0.getElementsContainingOwnText("p^X8qT");
        elements0.size();
        assertEquals(0, elements0.size());
    }

    @Test(timeout = 4000)
    public void test6063() throws Throwable {
        Tag tag0 = Tag.valueOf("{sT*zNV");
        Attributes attributes0 = new Attributes();
        Element element0 = new Element(tag0, "{sT*zNV", attributes0);
        Tag tag1 = element0.tag();
    }

    @Test(timeout = 4000)
    public void test6164() throws Throwable {
        Tag tag0 = Tag.valueOf("[");
        Element element0 = new Element(tag0, "[");
        Element element1 = element0.clone();
    }

    @Test(timeout = 4000)
    public void test6265() throws Throwable {
        Document document0 = new Document("=6+\"|M&Us4");
        Elements elements0 = document0.getElementsByAttributeValueStarting("=6+\"|M&Us4", "=6+\"|M&Us4");
        elements0.isEmpty();
        assertTrue(elements0.isEmpty());
    }

    @Test(timeout = 4000)
    public void test6366() throws Throwable {
        Document document0 = new Document("|W.V'qlW*S");
        document0.before((String) null);
    }

    @Test(timeout = 4000)
    public void test6467() throws Throwable {
        Document document0 = new Document("p^X8qT");
        document0.hashCode();
    }

    @Test(timeout = 4000)
    public void test6568() throws Throwable {
        Document document0 = new Document("Pattern syntax error: ");
        Element element0 = document0.prependElement("Pattern syntax error: ");
        Element element1 = element0.nextElementSibling();
    }

    @Test(timeout = 4000)
    public void test6669() throws Throwable {
        Tag tag0 = Tag.valueOf("00}nq%<");
        Element element0 = new Element(tag0, "m~");
        Element element1 = element0.nextElementSibling();
    }

    @Test(timeout = 4000)
    public void test6770() throws Throwable {
        Document document0 = new Document("p^X8qT");
        List<TextNode> list0 = document0.textNodes();
        document0.insertChildren((-1535), list0);
    }

    @Test(timeout = 4000)
    public void test6871() throws Throwable {
        Tag tag0 = Tag.valueOf("Tf?<%");
        Element element0 = new Element(tag0, "Tf?<%");
        LinkedList<Comment> linkedList0 = new LinkedList<Comment>();
        element0.insertChildren(51, linkedList0);
    }

    @Test(timeout = 4000)
    public void test6972() throws Throwable {
        Tag tag0 = Tag.valueOf("9");
        Element element0 = new Element(tag0, "");
        element0.wrap("uVq7c!VhQ ");
    }

    @Test(timeout = 4000)
    public void test7073() throws Throwable {
        Document document0 = new Document("J<");
        document0.normalise();
    }

    @Test(timeout = 4000)
    public void test7174() throws Throwable {
        Tag tag0 = Tag.valueOf("oJf>2G,`c=WEV p");
        Element element0 = new Element(tag0, "u=XqjtMlP-[");
        element0.html("oJf>2G,`c=WEV p");
    }

    @Test(timeout = 4000)
    public void test7275() throws Throwable {
        Document.createShell(" />");
    }
}
