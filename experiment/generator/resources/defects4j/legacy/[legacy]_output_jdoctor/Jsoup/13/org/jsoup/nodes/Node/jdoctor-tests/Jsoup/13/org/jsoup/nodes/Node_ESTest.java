/*
 * This file was automatically generated by EvoSuite
 * Sun Oct 15 20:42:21 GMT 2023
 */
package org.jsoup.nodes;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.nodes.XmlDeclaration;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class Node_ESTest extends Node_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        Document document0 = Document.createShell("`711./t<5S");
        Document document1 = document0.clone();
        document1.setParentNode(document0);
        Element element0 = document1.addClass("mZJ!:+~\"y|k\"vv");
        Element element1 = element0.text("`711./t<5S");
        Element element2 = element1.prepend("org.jsoup.nodes.Document$QuirksMode");
        element1.siblingIndex = 600;
        Elements elements0 = element2.getElementsContainingText("#,7n8{Oq");
        elements0.size();
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        Document document0 = Document.createShell("K>(-qM(Mw1J9j");
        Element element0 = document0.appendElement("publ");
        Element element1 = document0.clone();
        element0.after((Node) element1);
        element1.siblingIndex();
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        Document document0 = new Document("!M%\"icZur");
        document0.prependChild(document0);
        List<Node> list0 = document0.siblingNodes();
        list0.size();
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        Document document0 = Document.createShell("^n44$sv");
        Element element0 = document0.appendElement("^n44$sv");
        document0.removeChild(element0);
        element0.siblingIndex();
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        TextNode textNode0 = TextNode.createFromEncoded("org.jsoup.nodes.Node", "org.jsoup.nodes.Node");
        StringBuilder stringBuilder0 = new StringBuilder((CharSequence) "");
        Document.OutputSettings document_OutputSettings0 = new Document.OutputSettings();
        textNode0.indent(stringBuilder0, 0, document_OutputSettings0);
        stringBuilder0.toString();
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        Comment comment0 = new Comment("", "");
        Node[] nodeArray0 = new Node[0];
        comment0.addChildren(nodeArray0);
        comment0.nodeName();
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        Document document0 = new Document("YM\"%^ftzn$'j?>|VI@");
        Node[] nodeArray0 = new Node[8];
        nodeArray0[0] = (Node) document0;
        nodeArray0[1] = (Node) document0;
        nodeArray0[2] = (Node) document0;
        nodeArray0[3] = (Node) document0;
        nodeArray0[4] = (Node) document0;
        nodeArray0[5] = (Node) document0;
        nodeArray0[6] = (Node) document0;
        nodeArray0[7] = (Node) document0;
        document0.addChildren(0, nodeArray0);
        document0.isBlock();
    }

    @Test(timeout = 4000)
    public void test077() throws Throwable {
        Document document0 = new Document("wS -");
        document0.setSiblingIndex((-2065));
        document0.siblingIndex();
    }

    @Test(timeout = 4000)
    public void test088() throws Throwable {
        Document document0 = new Document("TPE*=");
        document0.setBaseUri("TPE*=");
        document0.siblingIndex();
    }

    @Test(timeout = 4000)
    public void test099() throws Throwable {
        Document document0 = new Document("5f9}");
        Element element0 = document0.appendElement("5f9}");
        element0.remove();
        element0.isBlock();
    }

    @Test(timeout = 4000)
    public void test1010() throws Throwable {
        XmlDeclaration xmlDeclaration0 = new XmlDeclaration("", "", false);
        String string0 = xmlDeclaration0.toString();
    }

    @Test(timeout = 4000)
    public void test1111() throws Throwable {
        Document document0 = new Document("iQ[D=n&^*_N,]XwD[Z0");
        Node node0 = document0.removeAttr("yWHRhjXTKtYoV");
        node0.nodeName();
    }

    @Test(timeout = 4000)
    public void test1212() throws Throwable {
        Document document0 = Document.createShell("Pfr");
        document0.siblingIndex = (-2406);
        Document document1 = document0.ownerDocument();
        document1.siblingIndex();
    }

    @Test(timeout = 4000)
    public void test1313() throws Throwable {
        Document document0 = new Document("iQ[D=n&^*_N,]XwD[Z0");
        String string0 = document0.outerHtml();
    }

    @Test(timeout = 4000)
    public void test1414() throws Throwable {
        TextNode textNode0 = TextNode.createFromEncoded("?", "?");
        String string0 = textNode0.nodeName();
    }

    @Test(timeout = 4000)
    public void test1515() throws Throwable {
        Tag tag0 = Tag.valueOf("?");
        Element element0 = new Element(tag0, "?");
        Document document0 = new Document("CommentEndDash");
        boolean boolean0 = element0.equals(document0);
    }

    @Test(timeout = 4000)
    public void test1616() throws Throwable {
        Document document0 = Document.createShell("`711./t<5S");
        Node[] nodeArray0 = document0.childNodesAsArray();
    }

    @Test(timeout = 4000)
    public void test1717() throws Throwable {
        Document document0 = new Document("abs:succapprox");
        TextNode textNode0 = new TextNode((String) null, "#tex");
        document0.prependChild(textNode0);
        textNode0.before((Node) document0);
        textNode0.siblingIndex();
    }

    @Test(timeout = 4000)
    public void test1718() throws Throwable {
        Document document0 = new Document("abs:succapprox");
        TextNode textNode0 = new TextNode((String) null, "#tex");
        document0.prependChild(textNode0);
        textNode0.before((Node) document0);
        document0.siblingIndex();
    }

    @Test(timeout = 4000)
    public void test1819() throws Throwable {
        Document document0 = new Document("(.s>");
        String string0 = document0.baseUri();
    }

    @Test(timeout = 4000)
    public void test1920() throws Throwable {
        TextNode textNode0 = new TextNode("9.3YfzTtz=e", "9.3YfzTtz=e");
        textNode0.wrap("");
    }

    @Test(timeout = 4000)
    public void test2021() throws Throwable {
        Document document0 = new Document("nvdash");
        DocumentType documentType0 = new DocumentType("nvdash", "_e+\"7r~{()clj!&K'2", "", "");
        documentType0.parentNode = (Node) document0;
        document0.removeChild(documentType0);
    }

    @Test(timeout = 4000)
    public void test2122() throws Throwable {
        Document document0 = Document.createShell("org.jsoup.nodes.Node");
        document0.previousSibling();
    }

    @Test(timeout = 4000)
    public void test2223() throws Throwable {
        TextNode textNode0 = new TextNode("?", "?");
        textNode0.outerHtml((StringBuilder) null);
    }

    @Test(timeout = 4000)
    public void test2424() throws Throwable {
        Document document0 = new Document(";p%NHpo40W+k;$J4L");
        Document.OutputSettings document_OutputSettings0 = new Document.OutputSettings();
        document0.indent((StringBuilder) null, 0, document_OutputSettings0);
    }

    @Test(timeout = 4000)
    public void test2525() throws Throwable {
        Document document0 = new Document("");
        document0.childNode(677);
    }

    @Test(timeout = 4000)
    public void test2626() throws Throwable {
        TextNode textNode0 = TextNode.createFromEncoded("gv.`hUM_V?f9", "*J,?T^9a");
        textNode0.attr("", "org.jsoup.nodes.Node$OuterHtmlVisitor");
    }

    @Test(timeout = 4000)
    public void test2727() throws Throwable {
        Document document0 = new Document("TPE*=");
        document0.attr((String) null);
    }

    @Test(timeout = 4000)
    public void test2828() throws Throwable {
        Tag tag0 = Tag.valueOf("?");
        Element element0 = new Element(tag0, "varepsilon");
        DataNode dataNode0 = DataNode.createFromEncoded("?", "org.jsoup.nodes.Node$OuterHtmlVisitor");
        element0.setParentNode(dataNode0);
        element0.after((Node) dataNode0);
    }

    @Test(timeout = 4000)
    public void test2929() throws Throwable {
        TextNode textNode0 = TextNode.createFromEncoded("Fv%#VPEt/2h|qXI,", "Fv%#VPEt/2h|qXI,");
        Node[] nodeArray0 = new Node[2];
        nodeArray0[0] = (Node) textNode0;
        textNode0.addChildren(nodeArray0);
    }

    @Test(timeout = 4000)
    public void test3030() throws Throwable {
        Document document0 = new Document("");
        Node[] nodeArray0 = new Node[3];
        nodeArray0[0] = (Node) document0;
        nodeArray0[1] = (Node) document0;
        nodeArray0[2] = (Node) document0;
        document0.addChildren(2361, nodeArray0);
    }

    @Test(timeout = 4000)
    public void test3131() throws Throwable {
        Comment comment0 = new Comment("", "");
        comment0.absUrl("");
    }

    @Test(timeout = 4000)
    public void test3332() throws Throwable {
        Document document0 = new Document("");
        Node node0 = document0.nextSibling();
    }

    @Test(timeout = 4000)
    public void test3433() throws Throwable {
        Document document0 = Document.createShell("(Ou]rYY'S");
        Node[] nodeArray0 = new Node[4];
        nodeArray0[0] = (Node) document0;
        nodeArray0[1] = (Node) document0;
        document0.addChildren(nodeArray0);
    }

    @Test(timeout = 4000)
    public void test3534() throws Throwable {
        TextNode textNode0 = TextNode.createFromEncoded("gv.`hUM_V?f9", "*J,?T^9a");
        Document document0 = textNode0.ownerDocument();
    }

    @Test(timeout = 4000)
    public void test3635() throws Throwable {
        Document document0 = Document.createShell("prnsim");
        Element element0 = document0.prependElement("AMP");
        Document document1 = element0.ownerDocument();
    }

    @Test(timeout = 4000)
    public void test3636() throws Throwable {
        Document document0 = Document.createShell("prnsim");
        Element element0 = document0.prependElement("AMP");
        Document document1 = element0.ownerDocument();
        element0.siblingIndex();
    }

    @Test(timeout = 4000)
    public void test3737() throws Throwable {
        Document document0 = Document.createShell("t#T");
        String string0 = document0.attr("y>0m-0L0K)");
    }

    @Test(timeout = 4000)
    public void test3838() throws Throwable {
        Document document0 = Document.createShell("$~BGq1gBP");
        List<Node> list0 = document0.childNodes();
        list0.isEmpty();
    }

    @Test(timeout = 4000)
    public void test3939() throws Throwable {
        TextNode textNode0 = new TextNode("^xYza6M*RD\u0005'ET$Qj(", (String) null);
        int int0 = textNode0.siblingIndex();
    }

    @Test(timeout = 4000)
    public void test4040() throws Throwable {
        TextNode textNode0 = TextNode.createFromEncoded("", "\u0006}-BeBAV&OK");
        Document document0 = Document.createShell("nequiv");
        textNode0.after((Node) document0);
    }

    @Test(timeout = 4000)
    public void test4141() throws Throwable {
        Document document0 = Document.createShell("");
        Element element0 = document0.head();
    }

    @Test(timeout = 4000)
    public void test4142() throws Throwable {
        Document document0 = Document.createShell("");
        Element element0 = document0.head();
        element0.outerHtml();
        element0.siblingIndex();
    }

    @Test(timeout = 4000)
    public void test4243() throws Throwable {
        TextNode textNode0 = TextNode.createFromEncoded("nRWVc_ysz", "nRWVc_ysz");
        StringBuilder stringBuilder0 = new StringBuilder((CharSequence) "nRWVc_ysz");
        textNode0.outerHtml(stringBuilder0);
        textNode0.siblingIndex();
    }

    @Test(timeout = 4000)
    public void test4344() throws Throwable {
        DataNode dataNode0 = new DataNode("LA|OV1$Kj>Z]1 OWk^", "");
        Node node0 = dataNode0.clone();
        node0.siblingIndex();
    }

    @Test(timeout = 4000)
    public void test4345() throws Throwable {
        DataNode dataNode0 = new DataNode("LA|OV1$Kj>Z]1 OWk^", "");
        Node node0 = dataNode0.clone();
    }

    @Test(timeout = 4000)
    public void test4446() throws Throwable {
        Document document0 = new Document("");
        String string0 = document0.baseUri();
    }

    @Test(timeout = 4000)
    public void test4547() throws Throwable {
        XmlDeclaration xmlDeclaration0 = new XmlDeclaration("", "", false);
        Node node0 = xmlDeclaration0.parent();
    }

    @Test(timeout = 4000)
    public void test4648() throws Throwable {
        Document document0 = Document.createShell("{T#");
        document0.childNode((-1522));
    }

    @Test(timeout = 4000)
    public void test4749() throws Throwable {
        Document document0 = Document.createShell("uIL&1\"");
        Attributes attributes0 = document0.attributes();
    }

    @Test(timeout = 4000)
    public void test4850() throws Throwable {
        Comment comment0 = new Comment("", "");
        Node node0 = comment0.attr("7f0y", "7f0y");
    }

    @Test(timeout = 4000)
    public void test4951() throws Throwable {
        Document document0 = new Document(" wd$D");
        boolean boolean0 = document0.hasAttr(",{J=fV");
    }

    @Test(timeout = 4000)
    public void test5052() throws Throwable {
        Document document0 = new Document("abs:succapprox");
        TextNode textNode0 = new TextNode((String) null, "#tex");
        textNode0.before((Node) document0);
    }

    @Test(timeout = 4000)
    public void test5153() throws Throwable {
        Comment comment0 = new Comment("}uTFEjqb/", "}uTFEjqb/");
        comment0.before(":IeT9%M.4T");
    }

    @Test(timeout = 4000)
    public void test5254() throws Throwable {
        Comment comment0 = new Comment("", "");
        TextNode textNode0 = new TextNode("rightleftharpoons", "\n<!---->");
        Node node0 = textNode0.doClone(comment0);
        node0.hashCode();
    }

    @Test(timeout = 4000)
    public void test5255() throws Throwable {
        Comment comment0 = new Comment("", "");
        TextNode textNode0 = new TextNode("rightleftharpoons", "\n<!---->");
        Node node0 = textNode0.doClone(comment0);
        node0.hashCode();
        node0.siblingIndex();
    }

    @Test(timeout = 4000)
    public void test5356() throws Throwable {
        Document document0 = new Document(">5~");
        document0.removeChild(document0);
    }

    @Test(timeout = 4000)
    public void test5457() throws Throwable {
        Comment comment0 = new Comment("", "");
        TextNode textNode0 = new TextNode("rightleftharpoons", "\n<!---->");
        Node node0 = textNode0.doClone(comment0);
        node0.replaceWith(comment0);
    }

    @Test(timeout = 4000)
    public void test5558() throws Throwable {
        Document document0 = Document.createShell("lbrace");
        document0.replaceChild(document0, (Node) null);
    }

    @Test(timeout = 4000)
    public void test5659() throws Throwable {
        Document document0 = new Document("!M%\"icZur");
        document0.prependChild(document0);
        Element element0 = document0.wrap("!M%\"icZur");
    }

    @Test(timeout = 4000)
    public void test5760() throws Throwable {
        TextNode textNode0 = new TextNode("rightleftharpoons", "\n<!---->");
        textNode0.wrap("_hve%K.B_");
    }

    @Test(timeout = 4000)
    public void test5861() throws Throwable {
        Document document0 = new Document("!M%\"icZur");
        document0.prependChild(document0);
        document0.after("!M%\"icZur");
        document0.siblingIndex();
    }

    @Test(timeout = 4000)
    public void test5962() throws Throwable {
        DataNode dataNode0 = new DataNode("[[*=A,X", "7");
        String string0 = dataNode0.absUrl("[[*=A,X");
    }

    @Test(timeout = 4000)
    public void test6063() throws Throwable {
        Document document0 = new Document("org.jsoup.nodes.Node");
        document0.getElementsByAttributeValueNot("abs:", "org.jsoup.nodes.Node");
    }

    @Test(timeout = 4000)
    public void test6164() throws Throwable {
        Document document0 = Document.createShell("lbrace");
        LinkedHashSet<String> linkedHashSet0 = new LinkedHashSet<String>();
        Element element0 = document0.classNames((Set<String>) linkedHashSet0);
        Element element1 = document0.clone();
        element1.siblingIndex();
    }

    @Test(timeout = 4000)
    public void test6165() throws Throwable {
        Document document0 = Document.createShell("lbrace");
        LinkedHashSet<String> linkedHashSet0 = new LinkedHashSet<String>();
        Element element0 = document0.classNames((Set<String>) linkedHashSet0);
        Element element1 = document0.clone();
    }

    @Test(timeout = 4000)
    public void test6266() throws Throwable {
        Document document0 = new Document("!M%\"icZur");
        document0.siblingNodes();
    }

    @Test(timeout = 4000)
    public void test6367() throws Throwable {
        Comment comment0 = new Comment("", "");
        TextNode textNode0 = new TextNode("rightleftharpoons", "\n<!---->");
        textNode0.replaceWith(comment0);
    }

    @Test(timeout = 4000)
    public void test6468() throws Throwable {
        Document document0 = new Document("c?E1wKu0iW&P");
        document0.removeAttr("");
    }

    @Test(timeout = 4000)
    public void test6569() throws Throwable {
        TextNode textNode0 = TextNode.createFromEncoded("Fv%#VPEt/2h|qXI,", "Fv%#VPEt/2h|qXI,");
        textNode0.remove();
    }

    @Test(timeout = 4000)
    public void test6670() throws Throwable {
        Document document0 = new Document("!M%\"icZur");
        Node[] nodeArray0 = document0.childNodesAsArray();
    }

    @Test(timeout = 4000)
    public void test6771() throws Throwable {
        Comment comment0 = new Comment("", "");
        comment0.after("");
    }

    @Test(timeout = 4000)
    public void test6872() throws Throwable {
        Document document0 = new Document("Rnu{");
        document0.setBaseUri((String) null);
    }
}
