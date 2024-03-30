/*
 * This file was automatically generated by EvoSuite
 * Sun Oct 15 22:52:04 GMT 2023
 */
package org.jsoup.nodes;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.util.List;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.jsoup.Connection;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;
import org.jsoup.parser.ParseSettings;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class FormElement_ESTest extends FormElement_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        Tag tag0 = Tag.valueOf("$cl%YBXo.~5P");
        Attributes attributes0 = new Attributes();
        FormElement formElement0 = new FormElement(tag0, "$cl%YBXo.~5P", attributes0);
        Document document0 = new Document("$cl%YBXo.~5P");
        FormElement formElement1 = formElement0.addElement(document0);
        Elements elements0 = formElement1.elements();
        elements0.size();
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        Tag tag0 = Tag.valueOf("U#Zw`MA6,h6;,6f:`");
        Attributes attributes0 = new Attributes();
        FormElement formElement0 = new FormElement(tag0, "U#Zw`MA6,h6;,6f:`", attributes0);
        Document document0 = Document.createShell("PANxl2s/a;}Z|{sl$x");
        formElement0.siblingIndex = 57;
        FormElement formElement1 = formElement0.addElement(document0);
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        ParseSettings parseSettings0 = ParseSettings.preserveCase;
        Tag tag0 = Tag.valueOf("; filename=\"", parseSettings0);
        Attributes attributes0 = new Attributes();
        FormElement formElement0 = new FormElement(tag0, "MULDZOY}GV}R&V?48B", attributes0);
        Document document0 = new Document(";#\"-jQ4Pyd`");
        FormElement formElement1 = formElement0.addElement(document0);
        formElement1.siblingIndex = (-1);
        FormElement formElement2 = formElement0.addElement(document0);
        formElement2.nodeName();
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        Tag tag0 = Tag.valueOf("h3");
        Attributes attributes0 = new Attributes();
        FormElement formElement0 = new FormElement(tag0, "link", attributes0);
        FormElement formElement1 = formElement0.addElement(formElement0);
        formElement1.baseUri();
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        ParseSettings parseSettings0 = ParseSettings.preserveCase;
        Tag tag0 = Tag.valueOf("keygen", parseSettings0);
        Comment comment0 = new Comment("keygen");
        Attributes attributes0 = comment0.attributes();
        FormElement formElement0 = new FormElement(tag0, ";1L@YWx", attributes0);
        Document document0 = new Document(";1L@YWx");
        formElement0.reparentChild(formElement0);
        FormElement formElement1 = formElement0.addElement(document0);
        formElement1.hasParent();
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        Tag tag0 = Tag.valueOf("Q");
        FormElement formElement0 = new FormElement(tag0, "Q", (Attributes) null);
        formElement0.prependElement("Q");
        Document document0 = Document.createShell("");
        FormElement formElement1 = formElement0.addElement(document0);
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        Tag tag0 = Tag.valueOf("rrUWn");
        Attributes attributes0 = new Attributes();
        Attributes attributes1 = attributes0.put("checked", "checked");
        FormElement formElement0 = new FormElement(tag0, "rrUWn", attributes1);
        attributes1.keys = null;
        formElement0.submit();
    }

    @Test(timeout = 4000)
    public void test077() throws Throwable {
        Tag tag0 = Tag.valueOf("U#Zw`MA6,h6;,6f:`");
        Attributes attributes0 = new Attributes();
        Attributes attributes1 = attributes0.put("PANxl2s/a;}Z|{sl$x", true);
        Attributes attributes2 = attributes1.clone();
        FormElement formElement0 = new FormElement(tag0, "U#Zw`MA6,h6;,6f:`", attributes2);
        String[] stringArray0 = new String[0];
        attributes2.keys = stringArray0;
        formElement0.submit();
    }

    @Test(timeout = 4000)
    public void test088() throws Throwable {
        Tag tag0 = Tag.valueOf("TPJ><bl[d^LLv.`");
        Attributes attributes0 = new Attributes();
        FormElement formElement0 = new FormElement(tag0, "TPJ><bl[d^LLv.`", attributes0);
        FormElement formElement1 = formElement0.addElement((Element) null);
        formElement1.formData();
    }

    @Test(timeout = 4000)
    public void test099() throws Throwable {
        Tag tag0 = Tag.valueOf("x|R_`nc;UpJ1kedNg");
        Attributes attributes0 = new Attributes();
        FormElement formElement0 = new FormElement(tag0, "x|R_`nc;UpJ1kedNg", attributes0);
        Element element0 = new Element("select");
        formElement0.addElement(element0);
        List<Connection.KeyVal> list0 = formElement0.formData();
        list0.size();
    }

    @Test(timeout = 4000)
    public void test1010() throws Throwable {
        Tag tag0 = Tag.valueOf("rrUWn");
        Attributes attributes0 = new Attributes();
        FormElement formElement0 = new FormElement(tag0, "rrUWn", attributes0);
        FormElement formElement1 = formElement0.addElement(formElement0);
        List<Connection.KeyVal> list0 = formElement1.formData();
        list0.isEmpty();
    }

    @Test(timeout = 4000)
    public void test1111() throws Throwable {
        Tag tag0 = Tag.valueOf("POST");
        Attributes attributes0 = new Attributes();
        Attributes attributes1 = attributes0.put("method", "POST");
        FormElement formElement0 = new FormElement(tag0, "POST", attributes1);
        formElement0.submit();
    }

    @Test(timeout = 4000)
    public void test1212() throws Throwable {
        Tag tag0 = Tag.valueOf("lYT");
        Attributes attributes0 = new Attributes();
        attributes0.put("action", "action");
        FormElement formElement0 = new FormElement(tag0, "action", attributes0);
        formElement0.submit();
    }

    @Test(timeout = 4000)
    public void test1313() throws Throwable {
        Tag tag0 = Tag.valueOf("PORT");
        Attributes attributes0 = new Attributes();
        FormElement formElement0 = new FormElement(tag0, "PORT", attributes0);
        formElement0.submit();
    }

    @Test(timeout = 4000)
    public void test1414() throws Throwable {
        Tag tag0 = Tag.valueOf("$cl%YBXo.~5P");
        Attributes attributes0 = new Attributes();
        FormElement formElement0 = new FormElement(tag0, "$cl%YBXo.~5P", attributes0);
        Elements elements0 = formElement0.elements();
        elements0.size();
    }

    @Test(timeout = 4000)
    public void test1515() throws Throwable {
        Comment comment0 = new Comment("Could not determine a form action URL for submit. Ensure you set a base URI when parsing.", "nQ^XMJ;~^mGo");
        Attributes attributes0 = comment0.attributes();
        FormElement formElement0 = null;
        formElement0 = new FormElement((Tag) null, "_e5_`>{1@n4d;", attributes0);
    }
}
