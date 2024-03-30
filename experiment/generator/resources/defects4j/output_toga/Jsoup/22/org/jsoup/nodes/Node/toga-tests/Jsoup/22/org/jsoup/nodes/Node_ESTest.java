/*
 * This file was automatically generated by EvoSuite
 * Mon Nov 20 05:34:45 GMT 2023
 */
package org.jsoup.nodes;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.nodes.XmlDeclaration;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class Node_ESTest extends Node_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        Document document0 = new Document(">z;+@");
        document0.before((Node) document0);
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        DataNode dataNode0 = new DataNode("abs:!m%\"iczur", "abs:!m%\"iczur");
        dataNode0.setBaseUri("abs:!m%\"iczur");
        dataNode0.siblingIndex();
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        Document document0 = new Document("abs:!m%\"icz9u$");
        document0.after((Node) document0);
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        Document document0 = Document.createShell("ohbMr");
        document0.parentNode = (Node) document0;
        document0.parentNode.after("ohbMr");
        document0.siblingIndex();
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        Document document0 = Document.createShell("#text");
        document0.parentNode = (Node) document0;
        document0.parentNode.siblingIndex();
    }

    @Test(timeout = 4000)
    public void test045() throws Throwable {
        Document document0 = Document.createShell("#text");
        document0.parentNode = (Node) document0;
        Node node0 = document0.unwrap();
        node0.siblingIndex();
    }

    @Test(timeout = 4000)
    public void test046() throws Throwable {
        Document document0 = Document.createShell("#text");
        document0.parentNode = (Node) document0;
        Node node0 = document0.unwrap();
    }

    @Test(timeout = 4000)
    public void test057() throws Throwable {
        StringBuilder stringBuilder0 = new StringBuilder("abs:!m\"c5");
        DocumentType documentType0 = new DocumentType("abs:!m\"c5", "abs:!m\"c5", "abs:!m\"c5", "");
        stringBuilder0.append((Object) documentType0);
        stringBuilder0.toString();
    }

    @Test(timeout = 4000)
    public void test068() throws Throwable {
        DataNode dataNode0 = new DataNode("abs:m%icz9u$", "abs:m%icz9u$");
        Node node0 = dataNode0.removeAttr("abs:m%icz9u$");
        node0.siblingIndex();
    }

    @Test(timeout = 4000)
    public void test079() throws Throwable {
        TextNode textNode0 = new TextNode("@GyjTv2s", "@GyjTv2s");
        String string0 = textNode0.toString();
        assertNotNull(string0);
    }

    @Test(timeout = 4000)
    public void test0810() throws Throwable {
        Document document0 = Document.createShell("abs:!m%\"icz9u$");
        String string0 = document0.outerHtml();
        assertNotNull(string0);
    }

    @Test(timeout = 4000)
    public void test0911() throws Throwable {
        DataNode dataNode0 = new DataNode("abs:!m%\"iczur", "abs:!m%\"iczur");
        dataNode0.siblingNodes();
    }

    @Test(timeout = 4000)
    public void test1012() throws Throwable {
        DataNode dataNode0 = new DataNode("abs:!m%\"iczur", "abs:!m%\"iczur");
        Attributes attributes0 = dataNode0.attributes();
        assertNotNull(attributes0);
    }

    @Test(timeout = 4000)
    public void test1113() throws Throwable {
        TextNode textNode0 = TextNode.createFromEncoded("h", "h");
        String string0 = textNode0.absUrl("abs:h");
        assertNotNull(string0);
    }

    @Test(timeout = 4000)
    public void test1214() throws Throwable {
        TextNode textNode0 = TextNode.createFromEncoded("h", "h");
        Node node0 = textNode0.attr("h", "h");
        String string0 = node0.absUrl("abs:h");
    }

    @Test(timeout = 4000)
    public void test1315() throws Throwable {
        TextNode textNode0 = new TextNode("h", "h");
        textNode0.setParentNode(textNode0);
        textNode0.before("h");
    }

    @Test(timeout = 4000)
    public void test1416() throws Throwable {
        Document document0 = new Document("abs:!m%\"iczur");
        document0.wrap("abs:!m%\"iczur");
    }

    @Test(timeout = 4000)
    public void test1517() throws Throwable {
        Document document0 = Document.createShell("Y");
        Element element0 = document0.head();
    }

    @Test(timeout = 4000)
    public void test1518() throws Throwable {
        Document document0 = Document.createShell("Y");
        Element element0 = document0.head();
        Element element1 = element0.wrap("Y");
        element1.siblingIndex();
    }

    @Test(timeout = 4000)
    public void test1519() throws Throwable {
        Document document0 = Document.createShell("Y");
        Element element0 = document0.head();
        Element element1 = element0.wrap("Y");
    }

    @Test(timeout = 4000)
    public void test1620() throws Throwable {
        Document document0 = Document.createShell("Y");
        document0.setParentNode(document0);
        Element element0 = document0.wrap("Y");
        assertNotNull(element0);
    }

    @Test(timeout = 4000)
    public void test1721() throws Throwable {
        Document document0 = new Document("txt");
        document0.parentNode = (Node) document0;
        document0.unwrap();
    }

    @Test(timeout = 4000)
    public void test1822() throws Throwable {
        Document document0 = Document.createShell("Y");
        Element element0 = document0.head();
        element0.setParentNode(document0);
        document0.nodeName();
    }

    @Test(timeout = 4000)
    public void test1923() throws Throwable {
        DataNode dataNode0 = new DataNode("v2@S", "v2@S");
        dataNode0.replaceChild(dataNode0, dataNode0);
    }

    @Test(timeout = 4000)
    public void test2024() throws Throwable {
        Document document0 = Document.createShell("Y");
        Element element0 = document0.head();
        element0.replaceWith(document0);
        document0.siblingIndex();
        assertEquals(0, document0.siblingIndex());
    }

    @Test(timeout = 4000)
    public void test2125() throws Throwable {
        DataNode dataNode0 = new DataNode("abs:!m%\"icz9u$", "abs:!m%\"icz9u$");
        dataNode0.removeChild(dataNode0);
    }

    @Test(timeout = 4000)
    public void test2226() throws Throwable {
        Document document0 = Document.createShell(">+@");
        document0.parentNode = (Node) document0;
        Node node0 = document0.parentNode.previousSibling();
    }

    @Test(timeout = 4000)
    public void test2327() throws Throwable {
        DataNode dataNode0 = new DataNode("v2@S", "v2@S");
        DataNode dataNode1 = dataNode0.setWholeData("v2@S");
        dataNode0.setSiblingIndex(607);
        dataNode1.parentNode = (Node) dataNode0;
        dataNode1.previousSibling();
    }

    @Test(timeout = 4000)
    public void test2428() throws Throwable {
        DataNode dataNode0 = new DataNode("abs:!m%\"iczur", "abs:!m%\"iczur");
        boolean boolean0 = dataNode0.equals("#data");
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test2529() throws Throwable {
        DataNode dataNode0 = DataNode.createFromEncoded(">cz@9R", ">cz@9R");
        boolean boolean0 = dataNode0.equals(dataNode0);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test2630() throws Throwable {
        TextNode textNode0 = new TextNode("succJneqq", "succJneqq");
        textNode0.hashCode();
    }

    @Test(timeout = 4000)
    public void test2731() throws Throwable {
        Document document0 = Document.createShell("lqV?8Gyj");
        document0.parentNode = (Node) document0;
        // Undeclared exception!
        document0.parentNode.hashCode();
    }

    @Test(timeout = 4000)
    public void test2832() throws Throwable {
        XmlDeclaration xmlDeclaration0 = new XmlDeclaration(">cz@9R", ">cz@9R", false);
        xmlDeclaration0.hashCode();
    }

    @Test(timeout = 4000)
    public void test2933() throws Throwable {
        Document document0 = Document.createShell("abs:!m%iczr");
        Element element0 = document0.prependText("abs:!m%iczr");
        Document document1 = document0.clone();
    }
}
