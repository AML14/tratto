/*
 * This file was automatically generated by EvoSuite
 * Mon Nov 20 07:22:35 GMT 2023
 */
package org.apache.commons.jxpath.ri.model.jdom;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.util.Locale;
import org.apache.commons.jxpath.JXPathContext;
import org.apache.commons.jxpath.ri.NamespaceResolver;
import org.apache.commons.jxpath.ri.QName;
import org.apache.commons.jxpath.ri.compiler.NodeNameTest;
import org.apache.commons.jxpath.ri.compiler.NodeTest;
import org.apache.commons.jxpath.ri.compiler.NodeTypeTest;
import org.apache.commons.jxpath.ri.compiler.ProcessingInstructionTest;
import org.apache.commons.jxpath.ri.model.NodeIterator;
import org.apache.commons.jxpath.ri.model.NodePointer;
import org.apache.commons.jxpath.ri.model.jdom.JDOMNodePointer;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.jdom.Attribute;
import org.jdom.CDATA;
import org.jdom.Comment;
import org.jdom.Content;
import org.jdom.DocType;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.ProcessingInstruction;
import org.jdom.Text;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class JDOMNodePointer_ESTest extends JDOMNodePointer_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        Locale locale0 = Locale.US;
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(locale0, locale0);
        boolean boolean0 = jDOMNodePointer0.isCollection();
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        Locale locale0 = Locale.CANADA_FRENCH;
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(locale0, locale0);
        NodePointer nodePointer0 = jDOMNodePointer0.namespacePointer("http://www.w3.org/2000/xmlns/");
        nodePointer0.isRoot();
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        Element element0 = new Element("dogble", "dogble", "dogble");
        Locale locale0 = Locale.JAPANESE;
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(element0, locale0);
        QName qName0 = jDOMNodePointer0.getName();
        NodePointer nodePointer0 = jDOMNodePointer0.createAttribute((JXPathContext) null, qName0);
        int int0 = jDOMNodePointer0.compareChildNodePointers(nodePointer0, jDOMNodePointer0);
    }

    @Test(timeout = 4000)
    public void test023() throws Throwable {
        Element element0 = new Element("dogble", "dogble", "dogble");
        Locale locale0 = Locale.JAPANESE;
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(element0, locale0);
        QName qName0 = jDOMNodePointer0.getName();
        NodePointer nodePointer0 = jDOMNodePointer0.createAttribute((JXPathContext) null, qName0);
        int int0 = jDOMNodePointer0.compareChildNodePointers(nodePointer0, jDOMNodePointer0);
        qName0.toString();
    }

    @Test(timeout = 4000)
    public void test034() throws Throwable {
        Object object0 = new Object();
        Locale locale0 = Locale.ITALIAN;
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(object0, locale0);
        int int0 = jDOMNodePointer0.getLength();
    }

    @Test(timeout = 4000)
    public void test045() throws Throwable {
        NodeTypeTest nodeTypeTest0 = new NodeTypeTest(4);
        Locale locale0 = Locale.KOREA;
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(nodeTypeTest0, locale0);
        boolean boolean0 = jDOMNodePointer0.testNode((NodeTest) nodeTypeTest0);
    }

    @Test(timeout = 4000)
    public void test056() throws Throwable {
        Locale locale0 = Locale.FRANCE;
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(locale0, locale0);
        NodeTypeTest nodeTypeTest0 = new NodeTypeTest(Integer.MIN_VALUE);
        NodeIterator nodeIterator0 = jDOMNodePointer0.childIterator(nodeTypeTest0, true, jDOMNodePointer0);
        nodeIterator0.getPosition();
    }

    @Test(timeout = 4000)
    public void test067() throws Throwable {
        Element element0 = new Element("Wdogbl", "Wdogbl");
        Locale locale0 = Locale.UK;
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(element0, locale0);
        JDOMNodePointer jDOMNodePointer1 = new JDOMNodePointer(jDOMNodePointer0, element0);
        NamespaceResolver namespaceResolver0 = new NamespaceResolver((NamespaceResolver) null);
        namespaceResolver0.setNamespaceContextPointer(jDOMNodePointer1);
        jDOMNodePointer0.setNamespaceResolver(namespaceResolver0);
        String string0 = jDOMNodePointer1.asPath();
    }

    @Test(timeout = 4000)
    public void test078() throws Throwable {
        Element element0 = new Element("dogble", "dogble");
        Locale locale0 = Locale.ITALY;
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(element0, locale0);
        int int0 = jDOMNodePointer0.compareChildNodePointers(jDOMNodePointer0, jDOMNodePointer0);
    }

    @Test(timeout = 4000)
    public void test099() throws Throwable {
        Locale locale0 = Locale.UK;
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(locale0, locale0);
        JXPathContext jXPathContext0 = JXPathContext.newContext((Object) jDOMNodePointer0);
        jDOMNodePointer0.createChild(jXPathContext0, (QName) null, Integer.MIN_VALUE, (Object) null);
    }

    @Test(timeout = 4000)
    public void test1010() throws Throwable {
        Locale locale0 = Locale.JAPANESE;
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(locale0, locale0);
        String string0 = jDOMNodePointer0.getNamespaceURI();
    }

    @Test(timeout = 4000)
    public void test1111() throws Throwable {
        Element element0 = new Element("double");
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(element0, (Locale) null);
        String string0 = jDOMNodePointer0.getNamespaceURI("double");
    }

    @Test(timeout = 4000)
    public void test1212() throws Throwable {
        Element element0 = new Element("org.apache.commons.jxpath.JXPathAbsractFactoryException", "org.apache.commons.jxpath.JXPathAbsractFactoryException", "org.apache.commons.jxpath.JXPathAbsractFactoryException");
        Document document0 = new Document(element0);
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(document0, (Locale) null);
        String string0 = jDOMNodePointer0.getNamespaceURI("org.apache.commons.jxpath.JXPathAbsractFactoryException");
    }

    @Test(timeout = 4000)
    public void test1313() throws Throwable {
        Element element0 = new Element("Tu");
        Document document0 = new Document(element0);
        Locale locale0 = Locale.PRC;
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(document0, locale0);
        String string0 = jDOMNodePointer0.getNamespaceURI("Tu");
    }

    @Test(timeout = 4000)
    public void test1414() throws Throwable {
        Locale locale0 = Locale.ENGLISH;
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(locale0, locale0, (String) null);
        String string0 = jDOMNodePointer0.getNamespaceURI((String) null);
    }

    @Test(timeout = 4000)
    public void test1515() throws Throwable {
        Element element0 = new Element("double", "double", "double");
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(element0, (Locale) null);
        String string0 = jDOMNodePointer0.getNamespaceURI("double");
    }

    @Test(timeout = 4000)
    public void test1616() throws Throwable {
        Attribute attribute0 = new Attribute("gebe", "gebe");
        QName qName0 = new QName("gebe");
        NodePointer nodePointer0 = NodePointer.newNodePointer(qName0, qName0, (Locale) null);
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer("<<unknown namespace>>", (Locale) null);
        JDOMNodePointer jDOMNodePointer1 = new JDOMNodePointer(jDOMNodePointer0, attribute0);
        int int0 = jDOMNodePointer0.compareChildNodePointers(nodePointer0, jDOMNodePointer1);
    }

    @Test(timeout = 4000)
    public void test1717() throws Throwable {
        Locale locale0 = Locale.FRANCE;
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(locale0, locale0);
        QName qName0 = jDOMNodePointer0.getName();
        NodePointer nodePointer0 = NodePointer.newChildNodePointer(jDOMNodePointer0, qName0, jDOMNodePointer0);
        jDOMNodePointer0.compareChildNodePointers(nodePointer0, jDOMNodePointer0);
    }

    @Test(timeout = 4000)
    public void test1818() throws Throwable {
        Element element0 = new Element("dogble", "dogble");
        Locale locale0 = Locale.ITALY;
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(element0, locale0);
        ProcessingInstruction processingInstruction0 = new ProcessingInstruction("dogble", "http://www.w3.org/2000/xmlns/");
        JDOMNodePointer jDOMNodePointer1 = new JDOMNodePointer(jDOMNodePointer0, processingInstruction0);
        element0.addContent((Content) processingInstruction0);
        int int0 = jDOMNodePointer0.compareChildNodePointers(jDOMNodePointer0, jDOMNodePointer1);
    }

    @Test(timeout = 4000)
    public void test1919() throws Throwable {
        Element element0 = new Element("dogble");
        Locale locale0 = Locale.FRANCE;
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(element0, locale0);
        ProcessingInstruction processingInstruction0 = new ProcessingInstruction("dogble", "http://www.w3.org/2000/xmlns/");
        JDOMNodePointer jDOMNodePointer1 = new JDOMNodePointer(jDOMNodePointer0, processingInstruction0);
        element0.addContent((Content) processingInstruction0);
        int int0 = jDOMNodePointer0.compareChildNodePointers(jDOMNodePointer1, jDOMNodePointer0);
    }

    @Test(timeout = 4000)
    public void test2020() throws Throwable {
        Element element0 = new Element("Gl", "Gl");
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(element0, (Locale) null);
        jDOMNodePointer0.setValue("Gl");
        QName qName0 = jDOMNodePointer0.getName();
        NodePointer nodePointer0 = NodePointer.newNodePointer(qName0, jDOMNodePointer0, (Locale) null);
        int int0 = jDOMNodePointer0.compareChildNodePointers(jDOMNodePointer0, nodePointer0);
    }

    @Test(timeout = 4000)
    public void test2021() throws Throwable {
        Element element0 = new Element("Gl", "Gl");
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(element0, (Locale) null);
        jDOMNodePointer0.setValue("Gl");
        QName qName0 = jDOMNodePointer0.getName();
        NodePointer nodePointer0 = NodePointer.newNodePointer(qName0, jDOMNodePointer0, (Locale) null);
        int int0 = jDOMNodePointer0.compareChildNodePointers(jDOMNodePointer0, nodePointer0);
        qName0.toString();
    }

    @Test(timeout = 4000)
    public void test2122() throws Throwable {
        Locale locale0 = Locale.TAIWAN;
        Element element0 = new Element("ouble", "ouble");
        Document document0 = new Document(element0);
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(document0, locale0);
        boolean boolean0 = jDOMNodePointer0.isLeaf();
    }

    @Test(timeout = 4000)
    public void test2223() throws Throwable {
        Element element0 = new Element("G", "G");
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(element0, (Locale) null);
        boolean boolean0 = jDOMNodePointer0.isLeaf();
    }

    @Test(timeout = 4000)
    public void test2324() throws Throwable {
        Element element0 = new Element("G", "G");
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(element0, (Locale) null);
        jDOMNodePointer0.setValue("G");
        boolean boolean0 = jDOMNodePointer0.isLeaf();
    }

    @Test(timeout = 4000)
    public void test2425() throws Throwable {
        Locale locale0 = Locale.FRANCE;
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(locale0, locale0);
        boolean boolean0 = jDOMNodePointer0.isLeaf();
    }

    @Test(timeout = 4000)
    public void test2526() throws Throwable {
        ProcessingInstruction processingInstruction0 = new ProcessingInstruction("double", "double");
        Locale locale0 = Locale.KOREAN;
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(processingInstruction0, locale0);
        QName qName0 = jDOMNodePointer0.getName();
        qName0.toString();
    }

    @Test(timeout = 4000)
    public void test2627() throws Throwable {
        Locale locale0 = Locale.CHINESE;
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(locale0, locale0);
        CDATA cDATA0 = new CDATA("<<unknown namespace>>");
        JDOMNodePointer jDOMNodePointer1 = new JDOMNodePointer(cDATA0, locale0);
        jDOMNodePointer1.setValue(jDOMNodePointer0);
    }

    @Test(timeout = 4000)
    public void test2728() throws Throwable {
        Locale locale0 = Locale.UK;
        Comment comment0 = new Comment("(%cpP;zWgC/JC|]0");
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(comment0, locale0);
        Object object0 = jDOMNodePointer0.getValue();
    }

    @Test(timeout = 4000)
    public void test2729() throws Throwable {
        Locale locale0 = Locale.UK;
        Comment comment0 = new Comment("(%cpP;zWgC/JC|]0");
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(comment0, locale0);
        Object object0 = jDOMNodePointer0.getValue();
    }

    @Test(timeout = 4000)
    public void test2830() throws Throwable {
        CDATA cDATA0 = new CDATA("p:+>qFT|");
        Locale locale0 = Locale.FRENCH;
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(cDATA0, locale0);
        Object object0 = jDOMNodePointer0.getValue();
    }

    @Test(timeout = 4000)
    public void test2931() throws Throwable {
        ProcessingInstruction processingInstruction0 = new ProcessingInstruction("double", "double");
        Locale locale0 = Locale.GERMAN;
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(processingInstruction0, locale0);
        Object object0 = jDOMNodePointer0.getValue();
    }

    @Test(timeout = 4000)
    public void test2932() throws Throwable {
        ProcessingInstruction processingInstruction0 = new ProcessingInstruction("double", "double");
        Locale locale0 = Locale.GERMAN;
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(processingInstruction0, locale0);
        Object object0 = jDOMNodePointer0.getValue();
    }

    @Test(timeout = 4000)
    public void test3033() throws Throwable {
        CDATA cDATA0 = new CDATA("");
        Locale locale0 = Locale.PRC;
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(cDATA0, locale0);
        jDOMNodePointer0.setValue("");
    }

    @Test(timeout = 4000)
    public void test3134() throws Throwable {
        CDATA cDATA0 = new CDATA("double");
        Locale locale0 = Locale.ITALIAN;
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(cDATA0, locale0);
        jDOMNodePointer0.setValue("double");
        cDATA0.getValue();
    }

    @Test(timeout = 4000)
    public void test3135() throws Throwable {
        CDATA cDATA0 = new CDATA("double");
        Locale locale0 = Locale.ITALIAN;
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(cDATA0, locale0);
        jDOMNodePointer0.setValue("double");
        cDATA0.getText();
    }

    @Test(timeout = 4000)
    public void test3236() throws Throwable {
        Element element0 = new Element("orgpa_he.commonsPjxJath.JXPalhAbstractFactoryExcetion");
        Locale locale0 = Locale.CHINA;
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(element0, locale0);
        DocType docType0 = new DocType("orgpa_he.commonsPjxJath.JXPalhAbstractFactoryExcetion", "orgpa_he.commonsPjxJath.JXPalhAbstractFactoryExcetion");
        Document document0 = new Document(element0, docType0, "http://www.w3.org/XML/1998/namespace");
        jDOMNodePointer0.setValue(document0);
        element0.getContentSize();
    }

    @Test(timeout = 4000)
    public void test3337() throws Throwable {
        Element element0 = new Element("doble");
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(element0, (Locale) null);
        CDATA cDATA0 = new CDATA("<<unknown namespace>>");
        jDOMNodePointer0.setValue(cDATA0);
        jDOMNodePointer0.isContainer();
    }

    @Test(timeout = 4000)
    public void test3438() throws Throwable {
        Element element0 = new Element("dogbl", "dogbl", "dogbl");
        Locale locale0 = Locale.UK;
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(element0, locale0);
        ProcessingInstruction processingInstruction0 = new ProcessingInstruction("dogbl", "dogbl");
        jDOMNodePointer0.setValue(processingInstruction0);
        jDOMNodePointer0.isAttribute();
    }

    @Test(timeout = 4000)
    public void test3539() throws Throwable {
        Element element0 = new Element("org.apache.commons.jxpath.JXPathAbstractFactoryException", "org.apache.commons.jxpath.JXPathAbstractFactoryException");
        Locale locale0 = Locale.PRC;
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(element0, locale0);
        Comment comment0 = new Comment("org.apache.commons.jxpath.JXPathAbstractFactoryException");
        jDOMNodePointer0.setValue(comment0);
        comment0.getValue();
    }

    @Test(timeout = 4000)
    public void test3640() throws Throwable {
        Element element0 = new Element("doble", "doble");
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(element0, (Locale) null);
        jDOMNodePointer0.setValue((Object) null);
        jDOMNodePointer0.isNode();
    }

    @Test(timeout = 4000)
    public void test3741() throws Throwable {
        Element element0 = new Element("double");
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(element0, (Locale) null);
        jDOMNodePointer0.setValue(jDOMNodePointer0);
        jDOMNodePointer0.isAttribute();
    }

    @Test(timeout = 4000)
    public void test3842() throws Throwable {
        Element element0 = new Element("double", "double");
        Element element1 = new Element("double", "double", "double");
        Element element2 = element1.setText("double");
        Locale locale0 = Locale.GERMANY;
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(element0, locale0);
        jDOMNodePointer0.setValue(element2);
        element0.getContentSize();
    }

    @Test(timeout = 4000)
    public void test3943() throws Throwable {
        boolean boolean0 = JDOMNodePointer.testNode((NodePointer) null, (Object) null, (NodeTest) null);
    }

    @Test(timeout = 4000)
    public void test4044() throws Throwable {
        NodeNameTest nodeNameTest0 = new NodeNameTest((QName) null);
        boolean boolean0 = JDOMNodePointer.testNode((NodePointer) null, (Object) null, (NodeTest) nodeNameTest0);
    }

    @Test(timeout = 4000)
    public void test4145() throws Throwable {
        Element element0 = new Element("double");
        Locale locale0 = Locale.TAIWAN;
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(element0, locale0);
        QName qName0 = new QName("double", "<<unknown namespace>>");
        NodeNameTest nodeNameTest0 = new NodeNameTest(qName0);
        boolean boolean0 = jDOMNodePointer0.testNode((NodeTest) nodeNameTest0);
    }

    @Test(timeout = 4000)
    public void test4246() throws Throwable {
        ProcessingInstruction processingInstruction0 = new ProcessingInstruction("HPorrg", "HPorrg");
        ProcessingInstructionTest processingInstructionTest0 = new ProcessingInstructionTest("HPorrg");
        boolean boolean0 = JDOMNodePointer.testNode((NodePointer) null, (Object) processingInstruction0, (NodeTest) processingInstructionTest0);
    }

    @Test(timeout = 4000)
    public void test4347() throws Throwable {
        NodeTypeTest nodeTypeTest0 = new NodeTypeTest(1);
        boolean boolean0 = JDOMNodePointer.testNode((NodePointer) null, (Object) null, (NodeTest) nodeTypeTest0);
    }

    @Test(timeout = 4000)
    public void test4448() throws Throwable {
        NodeTypeTest nodeTypeTest0 = new NodeTypeTest(2);
        boolean boolean0 = JDOMNodePointer.testNode((NodePointer) null, (Object) null, (NodeTest) nodeTypeTest0);
    }

    @Test(timeout = 4000)
    public void test4549() throws Throwable {
        NodeTypeTest nodeTypeTest0 = new NodeTypeTest(3);
        boolean boolean0 = JDOMNodePointer.testNode((NodePointer) null, (Object) null, (NodeTest) nodeTypeTest0);
    }

    @Test(timeout = 4000)
    public void test4650() throws Throwable {
        Element element0 = new Element("dogble", "dogble");
        Locale locale0 = Locale.ITALY;
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(element0, locale0);
        NodeTypeTest nodeTypeTest0 = new NodeTypeTest(Integer.MIN_VALUE);
        boolean boolean0 = JDOMNodePointer.testNode((NodePointer) jDOMNodePointer0, (Object) jDOMNodePointer0, (NodeTest) nodeTypeTest0);
    }

    @Test(timeout = 4000)
    public void test4751() throws Throwable {
        Element element0 = new Element("double");
        Locale locale0 = Locale.CANADA_FRENCH;
        NodeTypeTest nodeTypeTest0 = new NodeTypeTest(1);
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(element0, locale0, "double");
        boolean boolean0 = jDOMNodePointer0.testNode((NodeTest) nodeTypeTest0);
    }

    @Test(timeout = 4000)
    public void test4852() throws Throwable {
        Document document0 = new Document();
        Locale locale0 = Locale.CANADA;
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(document0, locale0);
        NodeTypeTest nodeTypeTest0 = new NodeTypeTest(1);
        boolean boolean0 = jDOMNodePointer0.testNode((NodeTest) nodeTypeTest0);
    }

    @Test(timeout = 4000)
    public void test4953() throws Throwable {
        NodeTypeTest nodeTypeTest0 = new NodeTypeTest(2);
        Text text0 = new Text((String) null);
        boolean boolean0 = JDOMNodePointer.testNode((NodePointer) null, (Object) text0, (NodeTest) nodeTypeTest0);
    }

    @Test(timeout = 4000)
    public void test5054() throws Throwable {
        Locale locale0 = Locale.CANADA_FRENCH;
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(locale0, locale0);
        ProcessingInstructionTest processingInstructionTest0 = new ProcessingInstructionTest("http://www.w3.org/XML/1998/namespace");
        boolean boolean0 = jDOMNodePointer0.testNode((NodeTest) processingInstructionTest0);
    }

    @Test(timeout = 4000)
    public void test5155() throws Throwable {
        Element element0 = new Element("double", "double");
        QName qName0 = new QName("double", "double");
        NodeNameTest nodeNameTest0 = new NodeNameTest(qName0);
        boolean boolean0 = JDOMNodePointer.testNode((NodePointer) null, (Object) element0, (NodeTest) nodeNameTest0);
    }

    @Test(timeout = 4000)
    public void test5256() throws Throwable {
        Attribute attribute0 = new Attribute("ob", "ob");
        String string0 = JDOMNodePointer.getPrefix(attribute0);
    }

    @Test(timeout = 4000)
    public void test5357() throws Throwable {
        Element element0 = new Element("double", "double", "double");
        String string0 = JDOMNodePointer.getPrefix(element0);
    }

    @Test(timeout = 4000)
    public void test5358() throws Throwable {
        Element element0 = new Element("double", "double", "double");
        String string0 = JDOMNodePointer.getPrefix(element0);
    }

    @Test(timeout = 4000)
    public void test5459() throws Throwable {
        Element element0 = new Element("double");
        String string0 = JDOMNodePointer.getPrefix(element0);
    }

    @Test(timeout = 4000)
    public void test5560() throws Throwable {
        Locale locale0 = Locale.CANADA_FRENCH;
        String string0 = JDOMNodePointer.getPrefix(locale0);
    }

    @Test(timeout = 4000)
    public void test5661() throws Throwable {
        Attribute attribute0 = new Attribute("ob", "ob");
        Namespace namespace0 = Namespace.getNamespace("ob", "ob");
        attribute0.setNamespace(namespace0);
        String string0 = JDOMNodePointer.getPrefix(attribute0);
    }

    @Test(timeout = 4000)
    public void test5662() throws Throwable {
        Attribute attribute0 = new Attribute("ob", "ob");
        Namespace namespace0 = Namespace.getNamespace("ob", "ob");
        attribute0.setNamespace(namespace0);
        String string0 = JDOMNodePointer.getPrefix(attribute0);
    }

    @Test(timeout = 4000)
    public void test5763() throws Throwable {
        Attribute attribute0 = new Attribute("QidoHuble", "QidoHuble");
        String string0 = JDOMNodePointer.getLocalName(attribute0);
    }

    @Test(timeout = 4000)
    public void test5864() throws Throwable {
        Locale locale0 = Locale.US;
        String string0 = JDOMNodePointer.getLocalName(locale0);
    }

    @Test(timeout = 4000)
    public void test5965() throws Throwable {
        ProcessingInstruction processingInstruction0 = new ProcessingInstruction("double", "double");
        Locale locale0 = Locale.GERMAN;
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(processingInstruction0, locale0);
        boolean boolean0 = jDOMNodePointer0.isLanguage("double");
    }

    @Test(timeout = 4000)
    public void test6066() throws Throwable {
        Element element0 = new Element("double", "double", "double");
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(element0, (Locale) null);
        jDOMNodePointer0.isLanguage("double");
    }

    @Test(timeout = 4000)
    public void test6167() throws Throwable {
        Locale locale0 = Locale.CANADA_FRENCH;
        Comment comment0 = new Comment("(%cpP;zWgC/JC|]0");
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(comment0, locale0);
        String string0 = jDOMNodePointer0.getLanguage();
    }

    @Test(timeout = 4000)
    public void test6268() throws Throwable {
        Locale locale0 = Locale.SIMPLIFIED_CHINESE;
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(locale0, locale0);
        boolean boolean0 = jDOMNodePointer0.isLanguage("<<unknown namespace>>");
    }

    @Test(timeout = 4000)
    public void test6369() throws Throwable {
        Locale locale0 = Locale.CHINA;
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(locale0, locale0);
        QName qName0 = new QName("http://www.w3.org/XML/1998/namespace");
        JXPathContext jXPathContext0 = JXPathContext.newContext((Object) jDOMNodePointer0);
        jDOMNodePointer0.createChild(jXPathContext0, qName0, 10);
    }

    @Test(timeout = 4000)
    public void test6470() throws Throwable {
        Locale locale0 = Locale.GERMAN;
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(locale0, locale0);
        QName qName0 = new QName("http://www.w3.org/XML/1998/namespace", "<<unknown namespace>>");
        jDOMNodePointer0.createAttribute((JXPathContext) null, qName0);
    }

    @Test(timeout = 4000)
    public void test6571() throws Throwable {
        Element element0 = new Element("dUTNogl");
        Locale locale0 = Locale.UK;
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(element0, locale0);
        QName qName0 = jDOMNodePointer0.getName();
        JXPathContext jXPathContext0 = JXPathContext.newContext((Object) jDOMNodePointer0);
        jDOMNodePointer0.createAttribute(jXPathContext0, qName0);
        jDOMNodePointer0.createAttribute(jXPathContext0, qName0);
        qName0.toString();
    }

    @Test(timeout = 4000)
    public void test6672() throws Throwable {
        Element element0 = new Element("double");
        Locale locale0 = Locale.CANADA_FRENCH;
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(element0, locale0);
        QName qName0 = new QName("http://www.w3.org/2000/xmlns/", "http://www.w3.org/2000/xmlns/");
        jDOMNodePointer0.createAttribute((JXPathContext) null, qName0);
    }

    @Test(timeout = 4000)
    public void test6773() throws Throwable {
        Element element0 = new Element("dUTNogl", "dUTNogl", "dUTNogl");
        Locale locale0 = Locale.UK;
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(element0, locale0);
        QName qName0 = jDOMNodePointer0.getName();
        JXPathContext jXPathContext0 = JXPathContext.newContext((Object) jDOMNodePointer0);
        jDOMNodePointer0.createAttribute(jXPathContext0, qName0);
        jDOMNodePointer0.createAttribute(jXPathContext0, qName0);
        qName0.toString();
    }

    @Test(timeout = 4000)
    public void test6874() throws Throwable {
        Element element0 = new Element("Gta", "Gta", "Gta");
        Locale locale0 = Locale.PRC;
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer("Gta", locale0, "Gta");
        CDATA cDATA0 = new CDATA("<<unknown namespace>>");
        JDOMNodePointer jDOMNodePointer1 = new JDOMNodePointer(jDOMNodePointer0, cDATA0);
        element0.addContent((Content) cDATA0);
        jDOMNodePointer1.remove();
        jDOMNodePointer1.isCollection();
    }

    @Test(timeout = 4000)
    public void test6975() throws Throwable {
        Element element0 = new Element("double");
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(element0, (Locale) null);
        jDOMNodePointer0.remove();
    }

    @Test(timeout = 4000)
    public void test7076() throws Throwable {
        Element element0 = new Element("f");
        Locale locale0 = Locale.CANADA;
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer("f", locale0, "f");
        JDOMNodePointer jDOMNodePointer1 = new JDOMNodePointer(jDOMNodePointer0, element0);
        NamespaceResolver namespaceResolver0 = new NamespaceResolver((NamespaceResolver) null);
        jDOMNodePointer0.setNamespaceResolver(namespaceResolver0);
        String string0 = jDOMNodePointer1.asPath();
    }

    @Test(timeout = 4000)
    public void test7177() throws Throwable {
        Element element0 = new Element("t3X", "t3X", "t3X");
        Locale locale0 = Locale.JAPANESE;
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(element0, locale0);
        JDOMNodePointer jDOMNodePointer1 = new JDOMNodePointer(jDOMNodePointer0, element0);
        NamespaceResolver namespaceResolver0 = new NamespaceResolver((NamespaceResolver) null);
        namespaceResolver0.setNamespaceContextPointer(jDOMNodePointer1);
        jDOMNodePointer1.setNamespaceResolver(namespaceResolver0);
        String string0 = jDOMNodePointer1.asPath();
    }

    @Test(timeout = 4000)
    public void test7278() throws Throwable {
        CDATA cDATA0 = new CDATA("k3");
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer((NodePointer) null, cDATA0);
        String string0 = jDOMNodePointer0.asPath();
    }

    @Test(timeout = 4000)
    public void test7379() throws Throwable {
        Element element0 = new Element("dogble", "dogble");
        Locale locale0 = Locale.ITALY;
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(element0, locale0);
        ProcessingInstruction processingInstruction0 = new ProcessingInstruction("dogble", "http://www.w3.org/2000/xmlns/");
        JDOMNodePointer jDOMNodePointer1 = new JDOMNodePointer(jDOMNodePointer0, processingInstruction0);
        String string0 = jDOMNodePointer1.asPath();
    }

    @Test(timeout = 4000)
    public void test7480() throws Throwable {
        Locale locale0 = Locale.FRANCE;
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(locale0, locale0, "/processing-instruction('");
        String string0 = jDOMNodePointer0.asPath();
    }

    @Test(timeout = 4000)
    public void test7581() throws Throwable {
        Locale locale0 = Locale.FRANCE;
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(locale0, locale0, "\"lang\"");
        String string0 = jDOMNodePointer0.asPath();
    }

    @Test(timeout = 4000)
    public void test7682() throws Throwable {
        Element element0 = new Element("Wdogbl", "Wdogbl");
        Locale locale0 = Locale.UK;
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(element0, locale0);
        JDOMNodePointer jDOMNodePointer1 = new JDOMNodePointer(jDOMNodePointer0, element0);
        NamespaceResolver namespaceResolver0 = new NamespaceResolver((NamespaceResolver) null);
        namespaceResolver0.setNamespaceContextPointer(jDOMNodePointer1);
        jDOMNodePointer0.setNamespaceResolver(namespaceResolver0);
        Document document0 = new Document(element0);
        String string0 = jDOMNodePointer1.asPath();
    }

    @Test(timeout = 4000)
    public void test7783() throws Throwable {
        Element element0 = new Element("Gtha", "Gtha");
        element0.addContent("Gtha");
        Locale locale0 = Locale.PRC;
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer("Gtha", locale0, "Gtha");
        CDATA cDATA0 = new CDATA("Gtha");
        JDOMNodePointer jDOMNodePointer1 = new JDOMNodePointer(jDOMNodePointer0, cDATA0);
        element0.addContent((Content) cDATA0);
        String string0 = jDOMNodePointer1.asPath();
    }

    @Test(timeout = 4000)
    public void test7884() throws Throwable {
        Element element0 = new Element("dogble", "dogble");
        Locale locale0 = Locale.ITALY;
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(element0, locale0);
        ProcessingInstruction processingInstruction0 = new ProcessingInstruction("dogble", "http://www.w3.org/2000/xmlns/");
        JDOMNodePointer jDOMNodePointer1 = new JDOMNodePointer(jDOMNodePointer0, processingInstruction0);
        element0.addContent((Content) processingInstruction0);
        String string0 = jDOMNodePointer1.asPath();
    }

    @Test(timeout = 4000)
    public void test7985() throws Throwable {
        Element element0 = new Element("k3", "k3");
        element0.addContent("k3");
        ProcessingInstruction processingInstruction0 = new ProcessingInstruction("k3", "k3");
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer((NodePointer) null, processingInstruction0);
        element0.addContent((Content) processingInstruction0);
        String string0 = jDOMNodePointer0.asPath();
    }

    @Test(timeout = 4000)
    public void test8086() throws Throwable {
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer((Object) null, (Locale) null);
        boolean boolean0 = jDOMNodePointer0.equals((Object) null);
    }

    @Test(timeout = 4000)
    public void test8187() throws Throwable {
        Locale locale0 = Locale.GERMAN;
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(locale0, locale0);
        boolean boolean0 = jDOMNodePointer0.equals(jDOMNodePointer0);
    }

    @Test(timeout = 4000)
    public void test8288() throws Throwable {
        Locale locale0 = Locale.TAIWAN;
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(locale0, locale0);
        JDOMNodePointer jDOMNodePointer1 = new JDOMNodePointer(locale0, locale0);
        boolean boolean0 = jDOMNodePointer1.equals(jDOMNodePointer0);
    }

    @Test(timeout = 4000)
    public void test8389() throws Throwable {
        Object object0 = new Object();
        Locale locale0 = Locale.TAIWAN;
        JDOMNodePointer jDOMNodePointer0 = new JDOMNodePointer(object0, locale0);
        JDOMNodePointer jDOMNodePointer1 = new JDOMNodePointer(jDOMNodePointer0, locale0, "http://www.w3.org/2000/xmlns/");
        boolean boolean0 = jDOMNodePointer0.equals(jDOMNodePointer1);
    }
}
