/*
 * This file was automatically generated by EvoSuite
 * Sun Oct 15 23:33:00 GMT 2023
 */
package org.jsoup.nodes;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.io.OutputStreamWriter;
import java.io.PipedOutputStream;
import java.io.PipedWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.BufferOverflowException;
import java.nio.CharBuffer;
import java.nio.ReadOnlyBufferException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Pattern;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.CDataNode;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.PseudoTextElement;
import org.jsoup.nodes.TextNode;
import org.jsoup.parser.ParseSettings;
import org.jsoup.parser.Parser;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;
import org.jsoup.select.Evaluator;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class Element_ESTest extends Element_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test0010() throws Throwable {
        Element element0 = new Element("qQn<6$94fs6)");
        Element element1 = element0.toggleClass("<qQn<6$94fs6)>\n LM{u}E3,4vQN,%yK&lt;\n</qQn<6$94fs6)>");
        element1.hasClass((String) null);
    }

    @Test(timeout = 4000)
    public void test0051() throws Throwable {
        Document document0 = Parser.parse("summary", "!x@}:d~k7M}nQ'_5E1");
        Element element0 = document0.body();
        Node[] nodeArray0 = new Node[3];
        element0.insertChildren(0, nodeArray0);
    }

    @Test(timeout = 4000)
    public void test0062() throws Throwable {
        Document document0 = Parser.parse("summary", "!x@}:d~k7M}nQ'_5E1");
        Element element0 = document0.empty();
        Node[] nodeArray0 = new Node[3];
        element0.insertChildren(0, nodeArray0);
    }

    @Test(timeout = 4000)
    public void test0073() throws Throwable {
        Parser parser0 = Parser.xmlParser();
        Document document0 = parser0.parseInput("}", "}");
        Node[] nodeArray0 = new Node[3];
        document0.insertChildren((-2460), nodeArray0);
    }

    @Test(timeout = 4000)
    public void test0084() throws Throwable {
        Document document0 = Document.createShell("org.jsoup.nodes.Element$1");
        LinkedHashSet<Document> linkedHashSet0 = new LinkedHashSet<Document>();
        document0.insertChildren((-598), (Collection<? extends Node>) linkedHashSet0);
    }

    @Test(timeout = 4000)
    public void test0655() throws Throwable {
        Document document0 = Document.createShell("Split offset must be not be negative");
        document0.toggleClass((String) null);
    }

    @Test(timeout = 4000)
    public void test0666() throws Throwable {
        Document document0 = new Document("Dx1HW;:~(ZBeT");
        document0.text("J^bT=H0;uiY");
    }

    @Test(timeout = 4000)
    public void test0687() throws Throwable {
        Document document0 = Parser.parse("", "");
        document0.tagName("");
    }

    @Test(timeout = 4000)
    public void test0698() throws Throwable {
        Document document0 = Document.createShell("E[d7^n");
        document0.selectFirst("<Yw])&|XWe?!'w");
    }

    @Test(timeout = 4000)
    public void test0709() throws Throwable {
        Parser parser0 = Parser.xmlParser();
        StringReader stringReader0 = new StringReader("");
        Document document0 = parser0.parseInput((Reader) stringReader0, "");
        document0.selectFirst((String) null);
    }

    @Test(timeout = 4000)
    public void test07110() throws Throwable {
        Tag tag0 = Tag.valueOf("bJ&g%s:dSe");
        Attributes attributes0 = new Attributes();
        Element element0 = new Element(tag0, "X@GA&__\"zL`<\"UIlq,*", attributes0);
        element0.select("X@GA&__\"zL`<\"UIlq,*");
    }

    @Test(timeout = 4000)
    public void test07211() throws Throwable {
        Document document0 = Parser.parseBodyFragmentRelaxed("", "~n");
        document0.select("");
    }

    @Test(timeout = 4000)
    public void test07312() throws Throwable {
        Document document0 = Parser.parse("summary", "");
        document0.removeClass((String) null);
    }

    @Test(timeout = 4000)
    public void test07413() throws Throwable {
        Document document0 = new Document("");
        document0.prependElement("");
    }

    @Test(timeout = 4000)
    public void test07514() throws Throwable {
        Document document0 = Parser.parseBodyFragment("JG#sgA$$!", "");
        document0.setParentNode(document0);
        document0.empty();
        document0.prependChild(document0);
    }

    @Test(timeout = 4000)
    public void test07615() throws Throwable {
        Document document0 = Parser.parseBodyFragment("org.jsoup.nodes.Element", "org.jsoup.nodes.Element");
        CharBuffer charBuffer0 = CharBuffer.wrap((CharSequence) " />");
        Document.OutputSettings document_OutputSettings0 = new Document.OutputSettings();
        document0.outerHtmlTail(charBuffer0, 0, document_OutputSettings0);
    }

    @Test(timeout = 4000)
    public void test07716() throws Throwable {
        Element element0 = new Element("LT");
        char[] charArray0 = new char[2];
        CharBuffer charBuffer0 = CharBuffer.wrap(charArray0);
        Document.OutputSettings document_OutputSettings0 = new Document.OutputSettings();
        element0.outerHtmlTail(charBuffer0, 0, document_OutputSettings0);
    }

    @Test(timeout = 4000)
    public void test07817() throws Throwable {
        Document document0 = Document.createShell("org.jsoup.nodes.Element$1");
        Document.OutputSettings document_OutputSettings0 = new Document.OutputSettings();
        document0.outerHtmlTail((Appendable) null, (-1432352094), document_OutputSettings0);
    }

    @Test(timeout = 4000)
    public void test07918() throws Throwable {
        Document document0 = Document.createShell("8_ETU<]{!@.9<");
        StringBuffer stringBuffer0 = new StringBuffer((CharSequence) "8_ETU<]{!@.9<");
        Document.OutputSettings document_OutputSettings0 = new Document.OutputSettings();
        document0.outerHtmlTail(stringBuffer0, (-1107), document_OutputSettings0);
    }

    @Test(timeout = 4000)
    public void test08019() throws Throwable {
        Document document0 = Document.createShell("org.jsoup.nodes.Element$1");
        document0.lastElementSibling();
    }

    @Test(timeout = 4000)
    public void test08120() throws Throwable {
        Document document0 = new Document("");
        document0.is((Evaluator) null);
    }

    @Test(timeout = 4000)
    public void test08221() throws Throwable {
        Document document0 = Document.createShell("2J");
        document0.is("tL%oKup3Pz=w@I]*O");
    }

    @Test(timeout = 4000)
    public void test08322() throws Throwable {
        Document document0 = Parser.parse("summary", "!x@}:d~k7M}nQ'_5E1");
        Node[] nodeArray0 = new Node[3];
        nodeArray0[0] = (Node) document0;
        nodeArray0[1] = (Node) document0;
        nodeArray0[2] = (Node) document0;
        document0.insertChildren(0, nodeArray0);
    }

    @Test(timeout = 4000)
    public void test08523() throws Throwable {
        Document document0 = new Document("eD'*bNhe8Fl*m}32");
        document0.html((String) null);
    }

    @Test(timeout = 4000)
    public void test08624() throws Throwable {
        Document document0 = new Document("=Bo9qZ`J}ywF");
        Document.OutputSettings document_OutputSettings0 = new Document.OutputSettings();
        Document document1 = document0.outputSettings(document_OutputSettings0);
        document_OutputSettings0.charset((Charset) null);
        document1.appendElement("AfterDoctypeName");
        StringWriter stringWriter0 = new StringWriter(54);
        document1.html(stringWriter0);
    }

    @Test(timeout = 4000)
    public void test08725() throws Throwable {
        Document document0 = Document.createShell("org.jsoup.nodes.Element$1");
        document0.childNodes = null;
        document0.hasText();
    }

    @Test(timeout = 4000)
    public void test08826() throws Throwable {
        Document document0 = Document.createShell("Unexpected count of entities loaded");
        document0.getElementsMatchingText((Pattern) null);
    }

    @Test(timeout = 4000)
    public void test08927() throws Throwable {
        Parser parser0 = Parser.xmlParser();
        Document document0 = parser0.parseInput("mE", "mE");
        document0.getElementsMatchingText("IT]bUjnNE,o[*.xh3y");
    }

    @Test(timeout = 4000)
    public void test09028() throws Throwable {
        Document document0 = Parser.parse(".oqJf(4SF(-", ".oqJf(4SF(-");
        document0.getElementsMatchingOwnText((Pattern) null);
    }

    @Test(timeout = 4000)
    public void test09129() throws Throwable {
        Document document0 = Document.createShell("org.jsoup.nodes.Element$1");
        document0.getElementsMatchingOwnText(")p(lx%q%6");
    }

    @Test(timeout = 4000)
    public void test09230() throws Throwable {
        Tag tag0 = Tag.valueOf("Ay@Z^kD:[0gtH");
        Attributes attributes0 = new Attributes();
        Element element0 = new Element(tag0, "Ay@Z^kD:[0gtH", attributes0);
        element0.getElementsByTag("");
    }

    @Test(timeout = 4000)
    public void test09331() throws Throwable {
        Parser parser0 = Parser.xmlParser();
        Document document0 = parser0.parseInput("ScriptDataEscapedDash", "body");
        DocumentType documentType0 = new DocumentType("body", "body", "html", "", "html");
        Element element0 = document0.doClone(documentType0);
        element0.getElementsByIndexEquals(4);
    }

    @Test(timeout = 4000)
    public void test09432() throws Throwable {
        Parser parser0 = Parser.htmlParser();
        StringReader stringReader0 = new StringReader("X#\"D6r5qdy<$'b");
        Document document0 = parser0.parseInput((Reader) stringReader0, "_J>8xxG");
        document0.getElementsByClass("");
    }

    @Test(timeout = 4000)
    public void test09533() throws Throwable {
        ParseSettings parseSettings0 = ParseSettings.preserveCase;
        Tag tag0 = Tag.valueOf("C-^Yd0t&y ~o(}I;[u", parseSettings0);
        CDataNode cDataNode0 = new CDataNode("");
        Attributes attributes0 = cDataNode0.attributes();
        PseudoTextElement pseudoTextElement0 = new PseudoTextElement(tag0, "C-^Yd0t&y ~o(}I;[u", attributes0);
        pseudoTextElement0.getElementsByAttributeValueNot((String) null, "bgsound");
    }

    @Test(timeout = 4000)
    public void test09634() throws Throwable {
        Document document0 = Parser.parse("textarea", "textarea");
        document0.getElementsByAttributeValue("", "<yhYGe.:UVj");
    }

    @Test(timeout = 4000)
    public void test09735() throws Throwable {
        Tag tag0 = Tag.valueOf(":matches(%s)");
        Element element0 = new Element(tag0, "");
        element0.getElementById("");
    }

    @Test(timeout = 4000)
    public void test09836() throws Throwable {
        Parser parser0 = Parser.xmlParser();
        Document document0 = parser0.parseInput("{ZI3k9(tZo", "{ZI3k9(tZo");
        document0.firstElementSibling();
    }

    @Test(timeout = 4000)
    public void test09937() throws Throwable {
        Document document0 = new Document("");
        document0.classNames((Set<String>) null);
    }

    @Test(timeout = 4000)
    public void test10138() throws Throwable {
        Document document0 = new Document("`?)vQk9T<h9");
        document0.attr((String) null, "#doctype");
    }

    @Test(timeout = 4000)
    public void test10239() throws Throwable {
        Element element0 = new Element("7HWI B(^;]V8^*%yD=");
        element0.appendElement("");
    }

    @Test(timeout = 4000)
    public void test10340() throws Throwable {
        Tag tag0 = Tag.valueOf("dd");
        Element element0 = new Element(tag0, "dd");
        element0.appendChild((Node) null);
    }

    @Test(timeout = 4000)
    public void test10541() throws Throwable {
        Document document0 = new Document("org.jsoup.nodes.DataNode");
        Element element0 = document0.appendElement("c:$]:,sV");
        Element element1 = element0.before((Node) document0);
        element1.after((Node) document0);
    }

    @Test(timeout = 4000)
    public void test10642() throws Throwable {
        Element element0 = null;
        element0 = new Element((Tag) null, (String) null);
    }

    @Test(timeout = 4000)
    public void test10743() throws Throwable {
        Element element0 = null;
        element0 = new Element((String) null);
    }

    @Test(timeout = 4000)
    public void test12744() throws Throwable {
        Document document0 = Document.createShell("E[d7^n");
        document0.prependChild((Node) null);
    }

    @Test(timeout = 4000)
    public void test12845() throws Throwable {
        CDataNode cDataNode0 = new CDataNode("A!W21O");
        Attributes attributes0 = cDataNode0.attributes();
        Element element0 = null;
        element0 = new Element((Tag) null, "A!W21O", attributes0);
    }

    @Test(timeout = 4000)
    public void test15846() throws Throwable {
        Document document0 = Document.createShell("org.jsoup.nodes.Element$1");
        Element element0 = document0.body();
    }

    @Test(timeout = 4000)
    public void test15847() throws Throwable {
        Document document0 = Document.createShell("org.jsoup.nodes.Element$1");
        Element element0 = document0.body();
        Elements elements0 = element0.nextElementSiblings();
        element0.baseUri();
    }

    @Test(timeout = 4000)
    public void test15848() throws Throwable {
        Document document0 = Document.createShell("org.jsoup.nodes.Element$1");
        Element element0 = document0.body();
        Elements elements0 = element0.nextElementSiblings();
        elements0.isEmpty();
    }

    @Test(timeout = 4000)
    public void test15949() throws Throwable {
        Document document0 = new Document("L;8l\"?PN");
        document0.siblingElements();
        document0.baseUri();
    }

    @Test(timeout = 4000)
    public void test16050() throws Throwable {
        Document document0 = new Document("org.jsoup.nodes.DataNode");
        Element element0 = document0.appendElement("c:$]:,sV");
        element0.before((Node) document0);
        document0.cssSelector();
        document0.childNodeSize();
    }

    @Test(timeout = 4000)
    public void test16151() throws Throwable {
        Document document0 = Parser.parseBodyFragment("}", "}");
        Node[] nodeArray0 = new Node[8];
        document0.insertChildren(7, nodeArray0);
    }

    @Test(timeout = 4000)
    public void test16252() throws Throwable {
        Document document0 = new Document("");
        Elements elements0 = document0.parents();
        Element element0 = document0.insertChildren(0, (Collection<? extends Node>) elements0);
        element0.childNodeSize();
    }

    @Test(timeout = 4000)
    public void test16353() throws Throwable {
        Document document0 = new Document("mark");
        Elements elements0 = document0.getElementsByIndexGreaterThan((-1203186048));
        document0.insertChildren((-1203186048), (Collection<? extends Node>) elements0);
    }

    @Test(timeout = 4000)
    public void test16454() throws Throwable {
        Document document0 = Parser.parseBodyFragment("B:[Ih@_g", "");
        Elements elements0 = document0.previousElementSiblings();
        document0.insertChildren(1621, (Collection<? extends Node>) elements0);
    }

    @Test(timeout = 4000)
    public void test16555() throws Throwable {
        Document document0 = Parser.parseBodyFragmentRelaxed("org.jsoupgnodes._lement", "2aU(8*wN9L");
        List<DataNode> list0 = document0.dataNodes();
        list0.size();
    }

    @Test(timeout = 4000)
    public void test16556() throws Throwable {
        Document document0 = Parser.parseBodyFragmentRelaxed("org.jsoupgnodes._lement", "2aU(8*wN9L");
        List<DataNode> list0 = document0.dataNodes();
        document0.childNodeSize();
    }

    @Test(timeout = 4000)
    public void test16557() throws Throwable {
        Document document0 = Parser.parseBodyFragmentRelaxed("org.jsoupgnodes._lement", "2aU(8*wN9L");
        List<DataNode> list0 = document0.dataNodes();
        document0.baseUri();
    }

    @Test(timeout = 4000)
    public void test16658() throws Throwable {
        Document document0 = Document.createShell("E[d7^n");
        List<TextNode> list0 = document0.textNodes();
        document0.childNodeSize();
    }

    @Test(timeout = 4000)
    public void test16659() throws Throwable {
        Document document0 = Document.createShell("E[d7^n");
        List<TextNode> list0 = document0.textNodes();
        document0.baseUri();
    }

    @Test(timeout = 4000)
    public void test16660() throws Throwable {
        Document document0 = Document.createShell("E[d7^n");
        List<TextNode> list0 = document0.textNodes();
        list0.isEmpty();
    }

    @Test(timeout = 4000)
    public void test16761() throws Throwable {
        Document document0 = Parser.parse("LS~9WRP0h`9{", "LS~9WRP0h`9{");
        Element element0 = document0.prependText("value");
        element0.getElementsByIndexLessThan(4);
        document0.childNodeSize();
    }

    @Test(timeout = 4000)
    public void test16762() throws Throwable {
        Document document0 = Parser.parse("LS~9WRP0h`9{", "LS~9WRP0h`9{");
        Element element0 = document0.prependText("value");
        element0.getElementsByIndexLessThan(4);
        document0.baseUri();
    }

    @Test(timeout = 4000)
    public void test16863() throws Throwable {
        Document document0 = Document.createShell("head");
        document0.selectFirst("br");
        document0.childNodeSize();
    }

    @Test(timeout = 4000)
    public void test16864() throws Throwable {
        Document document0 = Document.createShell("head");
        document0.selectFirst("br");
        document0.baseUri();
    }

    @Test(timeout = 4000)
    public void test16965() throws Throwable {
        Document document0 = Document.createShell("org.jsoup.nodes.Element$1");
        document0.is("dt");
        document0.baseUri();
    }

    @Test(timeout = 4000)
    public void test16966() throws Throwable {
        Document document0 = Document.createShell("org.jsoup.nodes.Element$1");
        document0.is("dt");
        document0.childNodeSize();
    }

    @Test(timeout = 4000)
    public void test17067() throws Throwable {
        Document document0 = new Document("+J{L0$Y-}]");
        document0.getAllElements();
        document0.baseUri();
    }

    @Test(timeout = 4000)
    public void test17168() throws Throwable {
        Document document0 = Parser.parseBodyFragmentRelaxed("org.jsoupgnodes._lement", "2aU(8*wN9L");
        Element element0 = document0.removeClass("org.jsoupgnodes._lement");
        element0.baseUri();
    }

    @Test(timeout = 4000)
    public void test17169() throws Throwable {
        Document document0 = Parser.parseBodyFragmentRelaxed("org.jsoupgnodes._lement", "2aU(8*wN9L");
        Element element0 = document0.removeClass("org.jsoupgnodes._lement");
        element0.childNodeSize();
    }

    @Test(timeout = 4000)
    public void test17270() throws Throwable {
        Element element0 = new Element("~2a)IqaIp");
        element0.before(".>a");
    }

    @Test(timeout = 4000)
    public void test17371() throws Throwable {
        Document document0 = Parser.parse("summary", "");
        document0.getElementsByAttribute("");
    }

    @Test(timeout = 4000)
    public void test17472() throws Throwable {
        Document document0 = Parser.parseBodyFragmentRelaxed("org.jsoupgnodes._lement", "2aU(8*wN9L");
        Elements elements0 = document0.getElementsByAttributeValueNot("org.jsoupgnodes._lement", "2aU(8*wN9L");
        elements0.size();
    }

    @Test(timeout = 4000)
    public void test17473() throws Throwable {
        Document document0 = Parser.parseBodyFragmentRelaxed("org.jsoupgnodes._lement", "2aU(8*wN9L");
        Elements elements0 = document0.getElementsByAttributeValueNot("org.jsoupgnodes._lement", "2aU(8*wN9L");
        document0.baseUri();
    }

    @Test(timeout = 4000)
    public void test17574() throws Throwable {
        Tag tag0 = Tag.valueOf("{>p\"4k43#oE/");
        Attributes attributes0 = new Attributes();
        PseudoTextElement pseudoTextElement0 = new PseudoTextElement(tag0, "#root", attributes0);
        pseudoTextElement0.after((Node) pseudoTextElement0);
    }

    @Test(timeout = 4000)
    public void test17675() throws Throwable {
        Parser parser0 = Parser.xmlParser();
        Document document0 = parser0.parseInput("{ZI3k9(tZo", "{ZI3k9(tZo");
        document0.getElementsByAttributeValueContaining("", "i3b|e\"s>4cqA.");
    }

    @Test(timeout = 4000)
    public void test17776() throws Throwable {
        Document document0 = Parser.parseBodyFragmentRelaxed("org.jsoupgnodes._lement", "2aU(8*wN9L");
        Elements elements0 = document0.getElementsContainingText("org.jsoupgnodes._lement");
        elements0.size();
    }

    @Test(timeout = 4000)
    public void test17777() throws Throwable {
        Document document0 = Parser.parseBodyFragmentRelaxed("org.jsoupgnodes._lement", "2aU(8*wN9L");
        Elements elements0 = document0.getElementsContainingText("org.jsoupgnodes._lement");
        document0.baseUri();
    }

    @Test(timeout = 4000)
    public void test17878() throws Throwable {
        Document document0 = Document.createShell("Unexpected count of entities loaded");
        Elements elements0 = document0.getElementsByClass("o|Lbl");
        document0.childNodeSize();
    }

    @Test(timeout = 4000)
    public void test17879() throws Throwable {
        Document document0 = Document.createShell("Unexpected count of entities loaded");
        Elements elements0 = document0.getElementsByClass("o|Lbl");
        elements0.isEmpty();
    }

    @Test(timeout = 4000)
    public void test17880() throws Throwable {
        Document document0 = Document.createShell("Unexpected count of entities loaded");
        Elements elements0 = document0.getElementsByClass("o|Lbl");
        document0.baseUri();
    }

    @Test(timeout = 4000)
    public void test17981() throws Throwable {
        Document document0 = Parser.parseBodyFragmentRelaxed("org.jsoupgnodes._lement", "2aU(8*wN9L");
        Evaluator.IsOnlyOfType evaluator_IsOnlyOfType0 = new Evaluator.IsOnlyOfType();
        document0.is((Evaluator) evaluator_IsOnlyOfType0);
        document0.childNodeSize();
    }

    @Test(timeout = 4000)
    public void test17982() throws Throwable {
        Document document0 = Parser.parseBodyFragmentRelaxed("org.jsoupgnodes._lement", "2aU(8*wN9L");
        Evaluator.IsOnlyOfType evaluator_IsOnlyOfType0 = new Evaluator.IsOnlyOfType();
        document0.is((Evaluator) evaluator_IsOnlyOfType0);
        document0.baseUri();
    }

    @Test(timeout = 4000)
    public void test18083() throws Throwable {
        Document document0 = Document.createShell("");
        document0.getElementsByAttributeValueMatching(">gZ]aGLQ|YyQgo$_\"/0", (String) null);
    }

    @Test(timeout = 4000)
    public void test18184() throws Throwable {
        Document document0 = Parser.parse("summary", "");
        Elements elements0 = document0.getElementsByIndexEquals(0);
        elements0.size();
    }

    @Test(timeout = 4000)
    public void test18285() throws Throwable {
        Document document0 = Document.createShell("Split offset must be not be negative");
        document0.getElementsByAttributeStarting("%xL!h?;FT|<lx_!~");
        document0.baseUri();
    }

    @Test(timeout = 4000)
    public void test18286() throws Throwable {
        Document document0 = Document.createShell("Split offset must be not be negative");
        document0.getElementsByAttributeStarting("%xL!h?;FT|<lx_!~");
        document0.childNodeSize();
    }

    @Test(timeout = 4000)
    public void test18387() throws Throwable {
        Document document0 = new Document("dfn");
        document0.after("dfn");
    }

    @Test(timeout = 4000)
    public void test18488() throws Throwable {
        Document document0 = Parser.parseBodyFragment("org.jsoup.nodes.Element", "org.jsoup.nodes.Element");
        document0.prepend("org.jsoup.nodes.Element");
        document0.childNodeSize();
    }

    @Test(timeout = 4000)
    public void test18589() throws Throwable {
        Document document0 = Document.createShell("org.jsoup.nodes.Element$1");
        document0.select("eKe");
        document0.baseUri();
    }

    @Test(timeout = 4000)
    public void test18590() throws Throwable {
        Document document0 = Document.createShell("org.jsoup.nodes.Element$1");
        document0.select("eKe");
        document0.childNodeSize();
    }

    @Test(timeout = 4000)
    public void test18691() throws Throwable {
        Document document0 = Document.createShell("Split offset must be not be negative");
        document0.getElementsByAttributeValueStarting("", "%xL!h?;FT|<lx_!~");
    }

    @Test(timeout = 4000)
    public void test18792() throws Throwable {
        Document document0 = new Document("org.jsoup.nodes.DataNode");
        document0.getElementsByAttributeValue(" />", " />");
        document0.baseUri();
    }

    @Test(timeout = 4000)
    public void test18893() throws Throwable {
        Document document0 = new Document("L;8l\"?PN");
        Element element0 = document0.attr("L;8l\"?PN", false);
        element0.baseUri();
    }

    @Test(timeout = 4000)
    public void test18994() throws Throwable {
        Document document0 = new Document("org.jsoup.nodes.DataNode");
        document0.html("KKQ~>s~mAUwWzEH");
        document0.childNodeSize();
    }

    @Test(timeout = 4000)
    public void test19095() throws Throwable {
        Parser parser0 = Parser.xmlParser();
        Document document0 = parser0.parseInput("{ZI3k9(tZo", "{ZI3k9(tZo");
        String string0 = document0.wholeText();
    }

    @Test(timeout = 4000)
    public void test19096() throws Throwable {
        Parser parser0 = Parser.xmlParser();
        Document document0 = parser0.parseInput("{ZI3k9(tZo", "{ZI3k9(tZo");
        String string0 = document0.wholeText();
        document0.baseUri();
    }

    @Test(timeout = 4000)
    public void test19197() throws Throwable {
        Document document0 = new Document("org.jsoup.nodes.DataNode");
        document0.before((Node) document0);
    }

    @Test(timeout = 4000)
    public void test19298() throws Throwable {
        Element element0 = new Element("LF_$>C%V6[t6<)");
        element0.doSetBaseUri("LF_$>C%V6[t6<)");
        element0.baseUri();
    }

    @Test(timeout = 4000)
    public void test19399() throws Throwable {
        Document document0 = new Document("L;8l\"?PN");
        document0.addClass("<iOZ :U6l^}(QHCFk ");
        document0.cssSelector();
        document0.baseUri();
    }

    @Test(timeout = 4000)
    public void test194100() throws Throwable {
        Document document0 = Parser.parseBodyFragment("}", "}");
        Elements elements0 = document0.getElementsContainingOwnText("}");
        document0.childNodeSize();
    }

    @Test(timeout = 4000)
    public void test194101() throws Throwable {
        Document document0 = Parser.parseBodyFragment("}", "}");
        Elements elements0 = document0.getElementsContainingOwnText("}");
        elements0.size();
    }

    @Test(timeout = 4000)
    public void test194102() throws Throwable {
        Document document0 = Parser.parseBodyFragment("}", "}");
        Elements elements0 = document0.getElementsContainingOwnText("}");
        document0.baseUri();
    }

    @Test(timeout = 4000)
    public void test195103() throws Throwable {
        Document document0 = new Document("L;8l\"?PN");
        document0.dataset();
        document0.baseUri();
    }

    @Test(timeout = 4000)
    public void test196104() throws Throwable {
        Document document0 = Parser.parseBodyFragmentRelaxed("org.jsoup.nodes.Element", "2aU(8*wN9L");
        document0.child(1448);
    }

    @Test(timeout = 4000)
    public void test197105() throws Throwable {
        Document document0 = new Document("L;8l\"?PN");
        document0.getElementsMatchingText("");
        document0.baseUri();
    }

    @Test(timeout = 4000)
    public void test198106() throws Throwable {
        Document document0 = Document.createShell("nowrap");
        document0.wrap(":matchText");
    }
}
