/*
 * This file was automatically generated by EvoSuite
 * Mon Oct 16 00:22:59 GMT 2023
 */
package org.apache.commons.jxpath.ri.model.dom;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.util.Locale;
import javax.imageio.metadata.IIOMetadataNode;
import org.apache.commons.jxpath.BasicVariables;
import org.apache.commons.jxpath.JXPathContext;
import org.apache.commons.jxpath.ri.QName;
import org.apache.commons.jxpath.ri.model.NodePointer;
import org.apache.commons.jxpath.ri.model.VariablePointer;
import org.apache.commons.jxpath.ri.model.dom.DOMAttributeIterator;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class DOMAttributeIterator_ESTest extends DOMAttributeIterator_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode("*");
        QName qName0 = new QName("", "*");
        iIOMetadataNode0.setAttribute("*", "*");
        iIOMetadataNode0.setAttribute(":\"", "xmlns");
        Locale locale0 = Locale.KOREAN;
        NodePointer nodePointer0 = NodePointer.newNodePointer(qName0, iIOMetadataNode0, locale0);
        DOMAttributeIterator dOMAttributeIterator0 = new DOMAttributeIterator(nodePointer0, qName0);
        NodePointer nodePointer1 = dOMAttributeIterator0.getNodePointer();
    }

    @Test(timeout = 4000)
    public void test001() throws Throwable {
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode("*");
        QName qName0 = new QName("", "*");
        iIOMetadataNode0.setAttribute("*", "*");
        iIOMetadataNode0.setAttribute(":\"", "xmlns");
        Locale locale0 = Locale.KOREAN;
        NodePointer nodePointer0 = NodePointer.newNodePointer(qName0, iIOMetadataNode0, locale0);
        DOMAttributeIterator dOMAttributeIterator0 = new DOMAttributeIterator(nodePointer0, qName0);
        NodePointer nodePointer1 = dOMAttributeIterator0.getNodePointer();
        dOMAttributeIterator0.getPosition();
    }

    @Test(timeout = 4000)
    public void test012() throws Throwable {
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode("*");
        QName qName0 = new QName("xmlns", "*");
        iIOMetadataNode0.setAttribute("*", "*");
        Locale locale0 = Locale.KOREAN;
        NodePointer nodePointer0 = NodePointer.newNodePointer(qName0, iIOMetadataNode0, locale0);
        DOMAttributeIterator dOMAttributeIterator0 = new DOMAttributeIterator(nodePointer0, qName0);
        dOMAttributeIterator0.getPosition();
    }

    @Test(timeout = 4000)
    public void test023() throws Throwable {
        BasicVariables basicVariables0 = new BasicVariables();
        QName qName0 = new QName(">.Y{GXqZPc3'h>0", ">.Y{GXqZPc3'h>0");
        VariablePointer variablePointer0 = new VariablePointer(basicVariables0, qName0);
        JXPathContext jXPathContext0 = JXPathContext.newContext((Object) ">.Y{GXqZPc3'h>0");
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode((String) null);
        NodePointer nodePointer0 = variablePointer0.createPath(jXPathContext0, (Object) iIOMetadataNode0);
        NodePointer nodePointer1 = nodePointer0.getImmediateValuePointer();
        DOMAttributeIterator dOMAttributeIterator0 = new DOMAttributeIterator(nodePointer1, qName0);
        dOMAttributeIterator0.setPosition(Integer.MIN_VALUE);
        int int0 = dOMAttributeIterator0.getPosition();
    }

    @Test(timeout = 4000)
    public void test034() throws Throwable {
        QName qName0 = new QName("\"u8");
        VariablePointer variablePointer0 = new VariablePointer(qName0);
        DOMAttributeIterator dOMAttributeIterator0 = null;
        dOMAttributeIterator0 = new DOMAttributeIterator(variablePointer0, qName0);
    }

    @Test(timeout = 4000)
    public void test045() throws Throwable {
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode();
        QName qName0 = new QName("*", "*");
        iIOMetadataNode0.appendChild(iIOMetadataNode0);
        iIOMetadataNode0.setAttribute(":\"", ":\"");
        Locale locale0 = Locale.KOREAN;
        NodePointer nodePointer0 = NodePointer.newNodePointer(qName0, iIOMetadataNode0, locale0);
        DOMAttributeIterator dOMAttributeIterator0 = new DOMAttributeIterator(nodePointer0, qName0);
    }

    @Test(timeout = 4000)
    public void test056() throws Throwable {
        DOMAttributeIterator dOMAttributeIterator0 = null;
        dOMAttributeIterator0 = new DOMAttributeIterator((NodePointer) null, (QName) null);
    }

    @Test(timeout = 4000)
    public void test067() throws Throwable {
        BasicVariables basicVariables0 = new BasicVariables();
        QName qName0 = new QName("^|E");
        VariablePointer variablePointer0 = new VariablePointer(basicVariables0, qName0);
        DOMAttributeIterator dOMAttributeIterator0 = null;
        dOMAttributeIterator0 = new DOMAttributeIterator(variablePointer0, qName0);
    }

    @Test(timeout = 4000)
    public void test078() throws Throwable {
        QName qName0 = new QName("&3QFSsY$M\nn`(");
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode();
        Locale locale0 = Locale.US;
        NodePointer nodePointer0 = NodePointer.newNodePointer(qName0, iIOMetadataNode0, locale0);
        NodePointer nodePointer1 = NodePointer.newNodePointer(qName0, nodePointer0, locale0);
        DOMAttributeIterator dOMAttributeIterator0 = null;
        dOMAttributeIterator0 = new DOMAttributeIterator(nodePointer1, qName0);
    }

    @Test(timeout = 4000)
    public void test089() throws Throwable {
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode("*");
        QName qName0 = new QName("xmlns", "xmlns");
        Locale locale0 = Locale.KOREAN;
        NodePointer nodePointer0 = NodePointer.newNodePointer(qName0, iIOMetadataNode0, locale0);
        DOMAttributeIterator dOMAttributeIterator0 = new DOMAttributeIterator(nodePointer0, qName0);
        dOMAttributeIterator0.setPosition(66);
        int int0 = dOMAttributeIterator0.getPosition();
    }

    @Test(timeout = 4000)
    public void test0910() throws Throwable {
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode("*");
        QName qName0 = new QName("xmlns", "xmlns");
        Locale locale0 = Locale.KOREAN;
        NodePointer nodePointer0 = NodePointer.newNodePointer(qName0, iIOMetadataNode0, locale0);
        DOMAttributeIterator dOMAttributeIterator0 = new DOMAttributeIterator(nodePointer0, qName0);
        dOMAttributeIterator0.setPosition(Integer.MIN_VALUE);
        dOMAttributeIterator0.getNodePointer();
    }

    @Test(timeout = 4000)
    public void test1011() throws Throwable {
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode("xmlns");
        QName qName0 = new QName("xmlns", "xmlns");
        iIOMetadataNode0.setAttribute("xmlns", "xmlns");
        Locale locale0 = Locale.ITALIAN;
        NodePointer nodePointer0 = NodePointer.newNodePointer(qName0, iIOMetadataNode0, locale0);
        DOMAttributeIterator dOMAttributeIterator0 = new DOMAttributeIterator(nodePointer0, qName0);
        dOMAttributeIterator0.getPosition();
    }

    @Test(timeout = 4000)
    public void test1112() throws Throwable {
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode();
        QName qName0 = new QName("", "*");
        iIOMetadataNode0.setAttribute(":\"", "");
        Locale locale0 = Locale.KOREAN;
        NodePointer nodePointer0 = NodePointer.newNodePointer(qName0, iIOMetadataNode0, locale0);
        DOMAttributeIterator dOMAttributeIterator0 = new DOMAttributeIterator(nodePointer0, qName0);
        NodePointer nodePointer1 = dOMAttributeIterator0.getNodePointer();
        dOMAttributeIterator0.getPosition();
    }

    @Test(timeout = 4000)
    public void test1113() throws Throwable {
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode();
        QName qName0 = new QName("", "*");
        iIOMetadataNode0.setAttribute(":\"", "");
        Locale locale0 = Locale.KOREAN;
        NodePointer nodePointer0 = NodePointer.newNodePointer(qName0, iIOMetadataNode0, locale0);
        DOMAttributeIterator dOMAttributeIterator0 = new DOMAttributeIterator(nodePointer0, qName0);
        NodePointer nodePointer1 = dOMAttributeIterator0.getNodePointer();
    }

    @Test(timeout = 4000)
    public void test1214() throws Throwable {
        QName qName0 = new QName("xmlns", "xmlns");
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode();
        Locale locale0 = Locale.UK;
        NodePointer nodePointer0 = NodePointer.newNodePointer(qName0, iIOMetadataNode0, locale0);
        Object object0 = new Object();
        iIOMetadataNode0.setAttributeNS("http://www.w3.org/XML/1998/namespace", "^jCqp", "http://www.w3.org/XML/1998/namespace");
        JXPathContext jXPathContext0 = JXPathContext.newContext(object0);
        nodePointer0.createAttribute(jXPathContext0, qName0);
    }

    @Test(timeout = 4000)
    public void test1315() throws Throwable {
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode("*");
        QName qName0 = new QName("*");
        iIOMetadataNode0.setAttribute(":\"", ":\"");
        Locale locale0 = Locale.KOREAN;
        NodePointer nodePointer0 = NodePointer.newNodePointer(qName0, iIOMetadataNode0, locale0);
        DOMAttributeIterator dOMAttributeIterator0 = new DOMAttributeIterator(nodePointer0, qName0);
        dOMAttributeIterator0.getPosition();
    }

    @Test(timeout = 4000)
    public void test1416() throws Throwable {
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode();
        QName qName0 = new QName("*");
        iIOMetadataNode0.setAttribute("xmlns", "xmlns");
        Locale locale0 = Locale.KOREAN;
        NodePointer nodePointer0 = NodePointer.newNodePointer(qName0, iIOMetadataNode0, locale0);
        DOMAttributeIterator dOMAttributeIterator0 = new DOMAttributeIterator(nodePointer0, qName0);
        dOMAttributeIterator0.getPosition();
    }

    @Test(timeout = 4000)
    public void test1517() throws Throwable {
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode("*");
        QName qName0 = new QName("*");
        iIOMetadataNode0.setAttribute("*", "*");
        Locale locale0 = Locale.KOREAN;
        NodePointer nodePointer0 = NodePointer.newNodePointer(qName0, iIOMetadataNode0, locale0);
        DOMAttributeIterator dOMAttributeIterator0 = new DOMAttributeIterator(nodePointer0, qName0);
        boolean boolean0 = dOMAttributeIterator0.setPosition(1);
        dOMAttributeIterator0.getPosition();
    }

    @Test(timeout = 4000)
    public void test1518() throws Throwable {
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode("*");
        QName qName0 = new QName("*");
        iIOMetadataNode0.setAttribute("*", "*");
        Locale locale0 = Locale.KOREAN;
        NodePointer nodePointer0 = NodePointer.newNodePointer(qName0, iIOMetadataNode0, locale0);
        DOMAttributeIterator dOMAttributeIterator0 = new DOMAttributeIterator(nodePointer0, qName0);
        boolean boolean0 = dOMAttributeIterator0.setPosition(1);
    }

    @Test(timeout = 4000)
    public void test1619() throws Throwable {
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode("*");
        QName qName0 = new QName("xmlns", ",");
        Locale locale0 = Locale.UK;
        NodePointer nodePointer0 = NodePointer.newNodePointer(qName0, iIOMetadataNode0, locale0);
        DOMAttributeIterator dOMAttributeIterator0 = new DOMAttributeIterator(nodePointer0, qName0);
        dOMAttributeIterator0.getPosition();
    }

    @Test(timeout = 4000)
    public void test1620() throws Throwable {
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode("*");
        QName qName0 = new QName("xmlns", ",");
        Locale locale0 = Locale.UK;
        NodePointer nodePointer0 = NodePointer.newNodePointer(qName0, iIOMetadataNode0, locale0);
        DOMAttributeIterator dOMAttributeIterator0 = new DOMAttributeIterator(nodePointer0, qName0);
        dOMAttributeIterator0.getNodePointer();
        dOMAttributeIterator0.getPosition();
    }

    @Test(timeout = 4000)
    public void test1721() throws Throwable {
        QName qName0 = new QName("&3QFSsY$M\nn`(");
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode();
        Locale locale0 = Locale.US;
        NodePointer nodePointer0 = NodePointer.newNodePointer(qName0, iIOMetadataNode0, locale0);
        JXPathContext jXPathContext0 = JXPathContext.newContext((Object) iIOMetadataNode0);
        NodePointer nodePointer1 = nodePointer0.createAttribute(jXPathContext0, qName0);
    }

    @Test(timeout = 4000)
    public void test1722() throws Throwable {
        QName qName0 = new QName("&3QFSsY$M\nn`(");
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode();
        Locale locale0 = Locale.US;
        NodePointer nodePointer0 = NodePointer.newNodePointer(qName0, iIOMetadataNode0, locale0);
        JXPathContext jXPathContext0 = JXPathContext.newContext((Object) iIOMetadataNode0);
        NodePointer nodePointer1 = nodePointer0.createAttribute(jXPathContext0, qName0);
        DOMAttributeIterator dOMAttributeIterator0 = new DOMAttributeIterator(nodePointer1, qName0);
        dOMAttributeIterator0.getPosition();
    }

    @Test(timeout = 4000)
    public void test1823() throws Throwable {
        IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode("*");
        QName qName0 = new QName("*");
        Locale locale0 = Locale.KOREAN;
        NodePointer nodePointer0 = NodePointer.newNodePointer(qName0, iIOMetadataNode0, locale0);
        DOMAttributeIterator dOMAttributeIterator0 = new DOMAttributeIterator(nodePointer0, qName0);
        int int0 = dOMAttributeIterator0.getPosition();
    }
}
