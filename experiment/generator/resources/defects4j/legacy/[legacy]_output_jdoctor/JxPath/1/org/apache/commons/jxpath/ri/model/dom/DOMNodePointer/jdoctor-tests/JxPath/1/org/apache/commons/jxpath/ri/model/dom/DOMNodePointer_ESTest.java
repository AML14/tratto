/*
 * This file was automatically generated by EvoSuite
 * Sun Oct 15 23:57:56 GMT 2023
 */
package org.apache.commons.jxpath.ri.model.dom;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.util.Locale;
import javax.imageio.metadata.IIOMetadataNode;
import org.apache.commons.jxpath.BasicVariables;
import org.apache.commons.jxpath.JXPathContext;
import org.apache.commons.jxpath.Variables;
import org.apache.commons.jxpath.ri.QName;
import org.apache.commons.jxpath.ri.compiler.NodeNameTest;
import org.apache.commons.jxpath.ri.compiler.NodeTest;
import org.apache.commons.jxpath.ri.compiler.NodeTypeTest;
import org.apache.commons.jxpath.ri.compiler.ProcessingInstructionTest;
import org.apache.commons.jxpath.ri.model.NodeIterator;
import org.apache.commons.jxpath.ri.model.NodePointer;
import org.apache.commons.jxpath.ri.model.VariablePointer;
import org.apache.commons.jxpath.ri.model.dom.DOMNodePointer;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;
import org.w3c.dom.Attr;
import org.w3c.dom.Node;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class DOMNodePointer_ESTest extends DOMNodePointer_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test090() throws Throwable {
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode();
        Locale locale0 = Locale.ITALY;
        iIOMetadataNode0.insertBefore(iIOMetadataNode0, iIOMetadataNode0);
        DOMNodePointer dOMNodePointer0 = new DOMNodePointer(iIOMetadataNode0, locale0, "fD^#WCp'^SA@FDKx%");
        dOMNodePointer0.setValue(iIOMetadataNode0);
    }

    @Test(timeout = 4000)
    public void test101() throws Throwable {
        BasicVariables basicVariables0 = new BasicVariables();
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode("/text()");
        Locale locale0 = Locale.GERMAN;
        DOMNodePointer dOMNodePointer0 = new DOMNodePointer(iIOMetadataNode0, locale0);
        QName qName0 = dOMNodePointer0.getName();
        VariablePointer variablePointer0 = new VariablePointer(basicVariables0, qName0);
        dOMNodePointer0.setValue(variablePointer0);
    }

    @Test(timeout = 4000)
    public void test112() throws Throwable {
        Locale locale0 = Locale.FRANCE;
        DOMNodePointer dOMNodePointer0 = new DOMNodePointer((Node) null, locale0, "");
        dOMNodePointer0.remove();
    }

    @Test(timeout = 4000)
    public void test123() throws Throwable {
        Locale locale0 = Locale.GERMAN;
        DOMNodePointer dOMNodePointer0 = new DOMNodePointer((Node) null, locale0);
        dOMNodePointer0.namespaceIterator();
    }

    @Test(timeout = 4000)
    public void test134() throws Throwable {
        QName qName0 = new QName("org.apache.commons.jxpath.ri.model.dom.DOMNodePointer");
        VariablePointer variablePointer0 = new VariablePointer(qName0);
        DOMNodePointer dOMNodePointer0 = new DOMNodePointer(variablePointer0, (Node) null);
        dOMNodePointer0.isLeaf();
    }

    @Test(timeout = 4000)
    public void test145() throws Throwable {
        QName qName0 = new QName("");
        VariablePointer variablePointer0 = new VariablePointer(qName0);
        DOMNodePointer dOMNodePointer0 = new DOMNodePointer(variablePointer0, (Node) null);
        dOMNodePointer0.isLanguage("<<unknown namespace>>");
    }

    @Test(timeout = 4000)
    public void test156() throws Throwable {
        Locale locale0 = Locale.JAPAN;
        DOMNodePointer dOMNodePointer0 = new DOMNodePointer((Node) null, locale0);
        dOMNodePointer0.getValue();
    }

    @Test(timeout = 4000)
    public void test167() throws Throwable {
        DOMNodePointer.getNamespaceURI((Node) null);
    }

    @Test(timeout = 4000)
    public void test188() throws Throwable {
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode();
        Locale locale0 = new Locale("http");
        DOMNodePointer dOMNodePointer0 = new DOMNodePointer(iIOMetadataNode0, locale0);
        dOMNodePointer0.getNamespaceURI();
    }

    @Test(timeout = 4000)
    public void test199() throws Throwable {
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode();
        Locale locale0 = Locale.SIMPLIFIED_CHINESE;
        DOMNodePointer dOMNodePointer0 = new DOMNodePointer(iIOMetadataNode0, locale0);
        dOMNodePointer0.getName();
    }

    @Test(timeout = 4000)
    public void test2010() throws Throwable {
        BasicVariables basicVariables0 = new BasicVariables();
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode("The specified collection element does not exist: ");
        Locale locale0 = Locale.KOREAN;
        DOMNodePointer dOMNodePointer0 = new DOMNodePointer(iIOMetadataNode0, locale0);
        QName qName0 = dOMNodePointer0.getName();
        VariablePointer variablePointer0 = new VariablePointer(basicVariables0, qName0);
        DOMNodePointer dOMNodePointer1 = new DOMNodePointer(variablePointer0, (Node) null);
        NodeTypeTest nodeTypeTest0 = new NodeTypeTest(1024);
        JXPathContext jXPathContext0 = JXPathContext.newContext((Object) nodeTypeTest0);
        dOMNodePointer1.createChild(jXPathContext0, qName0, 0, (Object) nodeTypeTest0);
    }

    @Test(timeout = 4000)
    public void test2111() throws Throwable {
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode("http");
        Locale locale0 = Locale.ITALIAN;
        DOMNodePointer dOMNodePointer0 = new DOMNodePointer(iIOMetadataNode0, locale0, "nW9KLvL");
        QName qName0 = dOMNodePointer0.getName();
        dOMNodePointer0.createChild((JXPathContext) null, qName0, 8);
    }

    @Test(timeout = 4000)
    public void test2212() throws Throwable {
        QName qName0 = new QName("M{L_5dM%+A%RMx~", "JXPath: found java.home property ");
        VariablePointer variablePointer0 = new VariablePointer(qName0);
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode("");
        DOMNodePointer dOMNodePointer0 = new DOMNodePointer(variablePointer0, iIOMetadataNode0);
        dOMNodePointer0.compareChildNodePointers(variablePointer0, dOMNodePointer0);
    }

    @Test(timeout = 4000)
    public void test2313() throws Throwable {
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode();
        Locale locale0 = Locale.GERMAN;
        DOMNodePointer dOMNodePointer0 = new DOMNodePointer(iIOMetadataNode0, locale0, "");
        QName qName0 = new QName("");
        VariablePointer variablePointer0 = new VariablePointer(qName0);
        dOMNodePointer0.compareChildNodePointers((NodePointer) null, variablePointer0);
    }

    @Test(timeout = 4000)
    public void test2414() throws Throwable {
        Locale locale0 = Locale.CANADA;
        DOMNodePointer dOMNodePointer0 = new DOMNodePointer((Node) null, locale0, "(mn");
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode();
        JXPathContext jXPathContext0 = JXPathContext.newContext((Object) iIOMetadataNode0);
        Variables variables0 = jXPathContext0.getVariables();
        QName qName0 = new QName("http://www.w3.org/2000/xmlns/");
        VariablePointer variablePointer0 = new VariablePointer(variables0, qName0);
        dOMNodePointer0.compareChildNodePointers(variablePointer0, variablePointer0);
    }

    @Test(timeout = 4000)
    public void test2515() throws Throwable {
        BasicVariables basicVariables0 = new BasicVariables();
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode("/text()");
        Locale locale0 = Locale.GERMAN;
        DOMNodePointer dOMNodePointer0 = new DOMNodePointer(iIOMetadataNode0, locale0);
        QName qName0 = dOMNodePointer0.getName();
        VariablePointer variablePointer0 = new VariablePointer(basicVariables0, qName0);
        JXPathContext jXPathContext0 = JXPathContext.newContext((Object) "wc{y` Y]H~$Hn");
        NodePointer nodePointer0 = variablePointer0.createPath(jXPathContext0, (Object) dOMNodePointer0);
        dOMNodePointer0.compareChildNodePointers(nodePointer0, dOMNodePointer0);
    }

    @Test(timeout = 4000)
    public void test2616() throws Throwable {
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode();
        Locale locale0 = Locale.forLanguageTag("");
        DOMNodePointer dOMNodePointer0 = new DOMNodePointer(iIOMetadataNode0, locale0, "");
        QName qName0 = new QName((String) null, "s3+V''gb6");
        NodeTypeTest nodeTypeTest0 = new NodeTypeTest(Integer.MIN_VALUE);
        VariablePointer variablePointer0 = new VariablePointer(qName0);
        dOMNodePointer0.childIterator(nodeTypeTest0, true, variablePointer0);
    }

    @Test(timeout = 4000)
    public void test2717() throws Throwable {
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode();
        QName qName0 = new QName("sum", "B$.o3mHv|n");
        Locale locale0 = Locale.GERMANY;
        DOMNodePointer dOMNodePointer0 = new DOMNodePointer(iIOMetadataNode0, locale0);
        NodeTypeTest nodeTypeTest0 = new NodeTypeTest(111);
        NodePointer nodePointer0 = NodePointer.newChildNodePointer(dOMNodePointer0, qName0, "sum");
        dOMNodePointer0.childIterator(nodeTypeTest0, true, nodePointer0);
    }

    @Test(timeout = 4000)
    public void test2918() throws Throwable {
        Locale locale0 = Locale.KOREAN;
        DOMNodePointer dOMNodePointer0 = new DOMNodePointer((Node) null, locale0, "zTtz=e,hwM^k$mQCi.");
        QName qName0 = new QName("wC/'G|ItgL7", "wC/'G|ItgL7");
        dOMNodePointer0.attributeIterator(qName0);
    }

    @Test(timeout = 4000)
    public void test3219() throws Throwable {
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode();
        DOMNodePointer.getLocalName(iIOMetadataNode0);
    }

    @Test(timeout = 4000)
    public void test3520() throws Throwable {
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode();
        DOMNodePointer.getPrefix(iIOMetadataNode0);
    }

    @Test(timeout = 4000)
    public void test3821() throws Throwable {
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode("|tE=\"CvJ=Xn/!");
        Locale locale0 = Locale.TRADITIONAL_CHINESE;
        DOMNodePointer dOMNodePointer0 = new DOMNodePointer(iIOMetadataNode0, locale0, "|tE=\"CvJ=Xn/!");
        QName qName0 = dOMNodePointer0.getName();
        JXPathContext jXPathContext0 = JXPathContext.newContext((Object) "=eY/o,E1");
        dOMNodePointer0.createChild(jXPathContext0, qName0, Integer.MIN_VALUE);
    }

    @Test(timeout = 4000)
    public void test4222() throws Throwable {
        NodeTypeTest nodeTypeTest0 = new NodeTypeTest(1696);
        DOMNodePointer.testNode((Node) null, (NodeTest) nodeTypeTest0);
    }

    @Test(timeout = 4000)
    public void test4523() throws Throwable {
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode("Yri0");
        Locale locale0 = new Locale("Yri0");
        DOMNodePointer dOMNodePointer0 = new DOMNodePointer(iIOMetadataNode0, locale0);
        dOMNodePointer0.getPointerByID((JXPathContext) null, "xml:lang");
    }

    @Test(timeout = 4000)
    public void test4724() throws Throwable {
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode("DROH|+{t':<iZ*n0cI");
        String string0 = DOMNodePointer.getNamespaceURI((Node) iIOMetadataNode0);
    }

    @Test(timeout = 4000)
    public void test4825() throws Throwable {
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode("e`{~FZWdI");
        DOMNodePointer dOMNodePointer0 = new DOMNodePointer((NodePointer) null, (Node) null);
        Locale locale0 = Locale.JAPAN;
        DOMNodePointer dOMNodePointer1 = new DOMNodePointer(iIOMetadataNode0, locale0);
        boolean boolean0 = dOMNodePointer0.equals(dOMNodePointer1);
    }

    @Test(timeout = 4000)
    public void test4926() throws Throwable {
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode("/text()");
        Locale locale0 = Locale.GERMAN;
        DOMNodePointer dOMNodePointer0 = new DOMNodePointer(iIOMetadataNode0, locale0);
        boolean boolean0 = dOMNodePointer0.equals(dOMNodePointer0);
    }

    @Test(timeout = 4000)
    public void test5027() throws Throwable {
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode("/text()");
        Locale locale0 = Locale.GERMAN;
        DOMNodePointer dOMNodePointer0 = new DOMNodePointer(iIOMetadataNode0, locale0);
        boolean boolean0 = dOMNodePointer0.equals((Object) null);
    }

    @Test(timeout = 4000)
    public void test5128() throws Throwable {
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode("|tE=\"CvJ=Xn/!");
        Locale locale0 = Locale.TRADITIONAL_CHINESE;
        DOMNodePointer dOMNodePointer0 = new DOMNodePointer(iIOMetadataNode0, locale0, "|tE=\"CvJ=Xn/!");
        QName qName0 = dOMNodePointer0.getName();
        JXPathContext jXPathContext0 = JXPathContext.newContext((Object) "=eY/o,E1");
        DOMNodePointer dOMNodePointer1 = new DOMNodePointer(iIOMetadataNode0, locale0, ";@;3]'6eV)|\".ruG`_9");
        dOMNodePointer1.createChild(jXPathContext0, qName0, 1, (Object) null);
    }

    @Test(timeout = 4000)
    public void test5229() throws Throwable {
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode();
        Locale locale0 = Locale.GERMAN;
        DOMNodePointer dOMNodePointer0 = new DOMNodePointer(iIOMetadataNode0, locale0);
        DOMNodePointer dOMNodePointer1 = new DOMNodePointer(dOMNodePointer0, iIOMetadataNode0);
        dOMNodePointer1.asPath();
    }

    @Test(timeout = 4000)
    public void test5330() throws Throwable {
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode();
        Locale locale0 = Locale.GERMAN;
        DOMNodePointer dOMNodePointer0 = new DOMNodePointer((Node) null, locale0, "Btf(jg8 ");
        DOMNodePointer dOMNodePointer1 = new DOMNodePointer(dOMNodePointer0, iIOMetadataNode0);
        dOMNodePointer1.asPath();
    }

    @Test(timeout = 4000)
    public void test5431() throws Throwable {
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode();
        Locale locale0 = new Locale("http");
        DOMNodePointer dOMNodePointer0 = new DOMNodePointer(iIOMetadataNode0, locale0);
        dOMNodePointer0.remove();
    }

    @Test(timeout = 4000)
    public void test5532() throws Throwable {
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode();
        Node node0 = iIOMetadataNode0.insertBefore(iIOMetadataNode0, iIOMetadataNode0);
        Locale locale0 = Locale.CHINESE;
        DOMNodePointer dOMNodePointer0 = new DOMNodePointer(node0, locale0);
        dOMNodePointer0.remove();
        dOMNodePointer0.getIndex();
    }

    @Test(timeout = 4000)
    public void test5633() throws Throwable {
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode("|tE=\"CvJ=Xn/!");
        Locale locale0 = Locale.TRADITIONAL_CHINESE;
        DOMNodePointer dOMNodePointer0 = new DOMNodePointer(iIOMetadataNode0, locale0, "|tE=\"CvJ=Xn/!");
        QName qName0 = dOMNodePointer0.getName();
        JXPathContext jXPathContext0 = JXPathContext.newContext((Object) "=eY/o,E1");
        dOMNodePointer0.createAttribute(jXPathContext0, qName0);
        NodeIterator nodeIterator0 = dOMNodePointer0.namespaceIterator();
        nodeIterator0.getPosition();
    }

    @Test(timeout = 4000)
    public void test5734() throws Throwable {
        Locale locale0 = Locale.CANADA;
        DOMNodePointer dOMNodePointer0 = new DOMNodePointer((Node) null, locale0);
        JXPathContext jXPathContext0 = JXPathContext.newContext((Object) null);
        QName qName0 = new QName("c^x4S_Fl$q", "anglais (Canada)");
        dOMNodePointer0.createAttribute(jXPathContext0, qName0);
    }

    @Test(timeout = 4000)
    public void test5835() throws Throwable {
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode();
        Locale locale0 = Locale.GERMANY;
        DOMNodePointer dOMNodePointer0 = new DOMNodePointer(iIOMetadataNode0, locale0);
        dOMNodePointer0.setValue((Object) null);
        dOMNodePointer0.isNode();
    }

    @Test(timeout = 4000)
    public void test5936() throws Throwable {
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode("NfpaXq6vRyz*mn;Z\"Up");
        DOMNodePointer dOMNodePointer0 = new DOMNodePointer(iIOMetadataNode0, (Locale) null);
        dOMNodePointer0.setValue(iIOMetadataNode0);
        dOMNodePointer0.isNode();
    }

    @Test(timeout = 4000)
    public void test6037() throws Throwable {
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode();
        iIOMetadataNode0.appendChild(iIOMetadataNode0);
        Locale locale0 = Locale.US;
        DOMNodePointer dOMNodePointer0 = new DOMNodePointer(iIOMetadataNode0, locale0);
        dOMNodePointer0.setValue("C*vME~%hs0YI{&S&w\"Y");
    }

    @Test(timeout = 4000)
    public void test6138() throws Throwable {
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode();
        Locale locale0 = new Locale("http");
        DOMNodePointer dOMNodePointer0 = new DOMNodePointer(iIOMetadataNode0, locale0);
        boolean boolean0 = dOMNodePointer0.isLanguage("{_l`-[jN");
    }

    @Test(timeout = 4000)
    public void test6239() throws Throwable {
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode();
        Locale locale0 = new Locale("http");
        DOMNodePointer dOMNodePointer0 = new DOMNodePointer(iIOMetadataNode0, locale0);
        boolean boolean0 = dOMNodePointer0.isLeaf();
    }

    @Test(timeout = 4000)
    public void test6340() throws Throwable {
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode("|tE=\"CvJ=Xn/!");
        Locale locale0 = Locale.TRADITIONAL_CHINESE;
        DOMNodePointer dOMNodePointer0 = new DOMNodePointer(iIOMetadataNode0, locale0, "|tE=\"CvJ=Xn/!");
        iIOMetadataNode0.insertBefore(iIOMetadataNode0, iIOMetadataNode0);
        boolean boolean0 = dOMNodePointer0.isLeaf();
    }

    @Test(timeout = 4000)
    public void test6441() throws Throwable {
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode("[");
        iIOMetadataNode0.setAttribute("[", "[");
        Attr attr0 = iIOMetadataNode0.getAttributeNode("[");
        Locale locale0 = Locale.FRENCH;
        DOMNodePointer dOMNodePointer0 = new DOMNodePointer(attr0, locale0, "[");
        String string0 = dOMNodePointer0.getDefaultNamespaceURI();
    }

    @Test(timeout = 4000)
    public void test6542() throws Throwable {
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode();
        Locale locale0 = Locale.forLanguageTag("");
        DOMNodePointer dOMNodePointer0 = new DOMNodePointer(iIOMetadataNode0, locale0, "");
        dOMNodePointer0.getNamespaceURI("");
        String string0 = dOMNodePointer0.getDefaultNamespaceURI();
    }

    @Test(timeout = 4000)
    public void test6643() throws Throwable {
        Locale locale0 = Locale.FRANCE;
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode();
        DOMNodePointer dOMNodePointer0 = new DOMNodePointer(iIOMetadataNode0, locale0, "http://www.w3.org/2000/xmlns/");
        QName qName0 = new QName("http://www.w3.org/2000/xmlns/", "<<unknown namespace>>");
        dOMNodePointer0.attributeIterator(qName0);
        JXPathContext jXPathContext0 = JXPathContext.newContext((Object) locale0);
        dOMNodePointer0.createAttribute(jXPathContext0, qName0);
    }

    @Test(timeout = 4000)
    public void test6744() throws Throwable {
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode();
        Locale locale0 = Locale.US;
        DOMNodePointer dOMNodePointer0 = new DOMNodePointer(iIOMetadataNode0, locale0);
        String string0 = dOMNodePointer0.getNamespaceURI("xml");
    }

    @Test(timeout = 4000)
    public void test6745() throws Throwable {
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode();
        Locale locale0 = Locale.US;
        DOMNodePointer dOMNodePointer0 = new DOMNodePointer(iIOMetadataNode0, locale0);
        String string0 = dOMNodePointer0.getNamespaceURI("xml");
    }

    @Test(timeout = 4000)
    public void test6846() throws Throwable {
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode();
        Locale locale0 = Locale.GERMANY;
        DOMNodePointer dOMNodePointer0 = new DOMNodePointer(iIOMetadataNode0, locale0);
        String string0 = dOMNodePointer0.getNamespaceURI((String) null);
    }

    @Test(timeout = 4000)
    public void test6947() throws Throwable {
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode("|tE=\"CvJ=Xn/!");
        Locale locale0 = Locale.TRADITIONAL_CHINESE;
        DOMNodePointer dOMNodePointer0 = new DOMNodePointer(iIOMetadataNode0, locale0, "|tE=\"CvJ=Xn/!");
        QName qName0 = dOMNodePointer0.getName();
        NodeNameTest nodeNameTest0 = new NodeNameTest(qName0);
        boolean boolean0 = dOMNodePointer0.testNode((NodeTest) nodeNameTest0);
        qName0.getName();
    }

    @Test(timeout = 4000)
    public void test6948() throws Throwable {
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode("|tE=\"CvJ=Xn/!");
        Locale locale0 = Locale.TRADITIONAL_CHINESE;
        DOMNodePointer dOMNodePointer0 = new DOMNodePointer(iIOMetadataNode0, locale0, "|tE=\"CvJ=Xn/!");
        QName qName0 = dOMNodePointer0.getName();
        NodeNameTest nodeNameTest0 = new NodeNameTest(qName0);
        boolean boolean0 = dOMNodePointer0.testNode((NodeTest) nodeNameTest0);
    }

    @Test(timeout = 4000)
    public void test7049() throws Throwable {
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode();
        ProcessingInstructionTest processingInstructionTest0 = new ProcessingInstructionTest("http");
        boolean boolean0 = DOMNodePointer.testNode((Node) iIOMetadataNode0, (NodeTest) processingInstructionTest0);
    }

    @Test(timeout = 4000)
    public void test7150() throws Throwable {
        DOMNodePointer dOMNodePointer0 = new DOMNodePointer((Node) null, (Locale) null);
        ProcessingInstructionTest processingInstructionTest0 = new ProcessingInstructionTest("<<unknown namespace>>");
        dOMNodePointer0.testNode((NodeTest) processingInstructionTest0);
    }

    @Test(timeout = 4000)
    public void test7251() throws Throwable {
        QName qName0 = new QName("(mn");
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode("<<unknown namespace>>");
        NodeNameTest nodeNameTest0 = new NodeNameTest(qName0);
        boolean boolean0 = DOMNodePointer.testNode((Node) iIOMetadataNode0, (NodeTest) nodeNameTest0);
    }

    @Test(timeout = 4000)
    public void test7352() throws Throwable {
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode();
        Locale locale0 = Locale.GERMANY;
        DOMNodePointer dOMNodePointer0 = new DOMNodePointer(iIOMetadataNode0, locale0);
        NodeTypeTest nodeTypeTest0 = new NodeTypeTest(111);
        boolean boolean0 = dOMNodePointer0.testNode((NodeTest) nodeTypeTest0);
    }

    @Test(timeout = 4000)
    public void test7453() throws Throwable {
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode("|tE=\"CvJ=Xn/!");
        boolean boolean0 = DOMNodePointer.testNode((Node) iIOMetadataNode0, (NodeTest) null);
    }

    @Test(timeout = 4000)
    public void test7554() throws Throwable {
        BasicVariables basicVariables0 = new BasicVariables();
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode("/text()");
        Locale locale0 = Locale.GERMAN;
        DOMNodePointer dOMNodePointer0 = new DOMNodePointer(iIOMetadataNode0, locale0);
        QName qName0 = dOMNodePointer0.getName();
        VariablePointer variablePointer0 = new VariablePointer(basicVariables0, qName0);
        DOMNodePointer dOMNodePointer1 = new DOMNodePointer(variablePointer0, iIOMetadataNode0);
        boolean boolean0 = dOMNodePointer1.equals(dOMNodePointer0);
    }

    @Test(timeout = 4000)
    public void test7555() throws Throwable {
        BasicVariables basicVariables0 = new BasicVariables();
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode("/text()");
        Locale locale0 = Locale.GERMAN;
        DOMNodePointer dOMNodePointer0 = new DOMNodePointer(iIOMetadataNode0, locale0);
        QName qName0 = dOMNodePointer0.getName();
        VariablePointer variablePointer0 = new VariablePointer(basicVariables0, qName0);
        DOMNodePointer dOMNodePointer1 = new DOMNodePointer(variablePointer0, iIOMetadataNode0);
        boolean boolean0 = dOMNodePointer1.equals(dOMNodePointer0);
        qName0.getName();
    }

    @Test(timeout = 4000)
    public void test7656() throws Throwable {
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode("|tE=\"CvJ=Xn/!");
        Locale locale0 = Locale.TRADITIONAL_CHINESE;
        DOMNodePointer dOMNodePointer0 = new DOMNodePointer(iIOMetadataNode0, locale0, "|tE=\"CvJ=Xn/!");
        boolean boolean0 = dOMNodePointer0.isActual();
    }

    @Test(timeout = 4000)
    public void test7757() throws Throwable {
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode("[");
        iIOMetadataNode0.setAttribute("[", "[");
        Attr attr0 = iIOMetadataNode0.getAttributeNode("[");
        Locale locale0 = Locale.FRENCH;
        DOMNodePointer dOMNodePointer0 = new DOMNodePointer(attr0, locale0, "[");
        QName qName0 = dOMNodePointer0.getName();
        NodePointer nodePointer0 = dOMNodePointer0.namespacePointer("[");
        NodeNameTest nodeNameTest0 = new NodeNameTest(qName0, "http://www.w3.org/XML/1998/namespace");
        NodeIterator nodeIterator0 = dOMNodePointer0.childIterator(nodeNameTest0, false, nodePointer0);
        nodeIterator0.getPosition();
    }

    @Test(timeout = 4000)
    public void test7958() throws Throwable {
        Locale locale0 = Locale.KOREA;
        DOMNodePointer dOMNodePointer0 = new DOMNodePointer((Node) null, locale0);
        int int0 = dOMNodePointer0.getLength();
    }

    @Test(timeout = 4000)
    public void test8059() throws Throwable {
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode("NfpaXq6vRyz*mn;Z\"Up");
        DOMNodePointer dOMNodePointer0 = new DOMNodePointer(iIOMetadataNode0, (Locale) null);
        String string0 = dOMNodePointer0.getNamespaceURI();
    }

    @Test(timeout = 4000)
    public void test8160() throws Throwable {
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode("/text()");
        Locale locale0 = Locale.GERMAN;
        DOMNodePointer dOMNodePointer0 = new DOMNodePointer(iIOMetadataNode0, locale0);
        boolean boolean0 = dOMNodePointer0.isCollection();
    }

    @Test(timeout = 4000)
    public void test8261() throws Throwable {
        BasicVariables basicVariables0 = new BasicVariables();
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode("/text()");
        Locale locale0 = Locale.GERMAN;
        DOMNodePointer dOMNodePointer0 = new DOMNodePointer(iIOMetadataNode0, locale0);
        QName qName0 = dOMNodePointer0.getName();
        VariablePointer variablePointer0 = new VariablePointer(basicVariables0, qName0);
        JXPathContext jXPathContext0 = JXPathContext.newContext((Object) "wc{y` Y]H~$Hn");
        variablePointer0.createPath(jXPathContext0, (Object) dOMNodePointer0);
        dOMNodePointer0.setValue(variablePointer0);
        qName0.getName();
    }
}
