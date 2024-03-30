/*
 * This file was automatically generated by EvoSuite
 * Sun Oct 15 23:28:34 GMT 2023
 */
package org.jsoup.nodes;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.CharBuffer;
import java.nio.ReadOnlyBufferException;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.mock.java.io.MockFile;
import org.evosuite.runtime.mock.java.io.MockFileWriter;
import org.evosuite.runtime.mock.java.io.MockPrintWriter;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class Attribute_ESTest extends Attribute_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        boolean boolean0 = Attribute.isBooleanAttribute("allowfullscreen");
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attribute attribute0 = new Attribute("allowfullscreen", "allowfullscreen", attributes0);
        boolean boolean0 = attribute0.isBooleanAttribute();
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        Attribute attribute0 = Attribute.createFromEncoded("disabled", "disabled");
        Document.OutputSettings document_OutputSettings0 = new Document.OutputSettings();
        boolean boolean0 = attribute0.shouldCollapseAttribute(document_OutputSettings0);
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        Document.OutputSettings document_OutputSettings0 = new Document.OutputSettings();
        boolean boolean0 = Attribute.shouldCollapseAttribute("", (String) null, document_OutputSettings0);
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        boolean boolean0 = Attribute.isDataAttribute("data-org.jsoup.nodes.Entities$1=\"data-org.jsoup.nodes.Entities$1\"");
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        Attribute attribute0 = Attribute.createFromEncoded("base", "base");
        boolean boolean0 = attribute0.isDataAttribute();
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        boolean boolean0 = Attribute.isBooleanAttribute("Oz[4");
    }

    @Test(timeout = 4000)
    public void test077() throws Throwable {
        Attribute attribute0 = Attribute.createFromEncoded("base", "base");
        String string0 = attribute0.getValue();
    }

    @Test(timeout = 4000)
    public void test088() throws Throwable {
        Attribute attribute0 = Attribute.createFromEncoded("#root", "");
        String string0 = attribute0.getValue();
    }

    @Test(timeout = 4000)
    public void test099() throws Throwable {
        Attribute attribute0 = new Attribute("                ", "ot(''6~bug%:");
        attribute0.getKey();
        attribute0.getValue();
    }

    @Test(timeout = 4000)
    public void test1010() throws Throwable {
        Attribute attribute0 = new Attribute("e'E,YoM(JP{m&", "e'E,YoM(JP{m&");
        attribute0.shouldCollapseAttribute((Document.OutputSettings) null);
    }

    @Test(timeout = 4000)
    public void test1111() throws Throwable {
        Attribute.shouldCollapseAttribute("|w2kb2{.\"W90", "+8>TZ", (Document.OutputSettings) null);
    }

    @Test(timeout = 4000)
    public void test1212() throws Throwable {
        Attribute attribute0 = Attribute.createFromEncoded("#root", "");
        attribute0.setValue(")");
    }

    @Test(timeout = 4000)
    public void test1313() throws Throwable {
        Attribute attribute0 = Attribute.createFromEncoded("RiLoHH<Ej_AmK-", "RiLoHH<Ej_AmK-");
        Attributes attributes0 = new Attributes();
        Attributes attributes1 = attributes0.clone();
        Attributes attributes2 = attributes1.put("RiLoHH<Ej_AmK-", true);
        attribute0.parent = attributes2;
        String[] stringArray0 = new String[0];
        attributes1.keys = stringArray0;
        attribute0.setValue("RiLoHH<Ej_AmK-");
    }

    @Test(timeout = 4000)
    public void test1414() throws Throwable {
        Attribute attribute0 = Attribute.createFromEncoded("L?#", "MAx;9kK5=$j:6P1A[");
        attribute0.setKey("");
    }

    @Test(timeout = 4000)
    public void test1515() throws Throwable {
        Attribute attribute0 = Attribute.createFromEncoded("Oz[4", "Oz[4");
        Attributes attributes0 = new Attributes();
        Attributes attributes1 = attributes0.put(attribute0);
        Attributes attributes2 = attributes1.clone();
        String[] stringArray0 = new String[0];
        attributes2.keys = stringArray0;
        attribute0.parent = attributes2;
        attribute0.setKey("[sb[T<s]Qn%");
    }

    @Test(timeout = 4000)
    public void test1616() throws Throwable {
        Attribute.isDataAttribute((String) null);
    }

    @Test(timeout = 4000)
    public void test1717() throws Throwable {
        Attribute.isBooleanAttribute((String) null);
    }

    @Test(timeout = 4000)
    public void test1818() throws Throwable {
        CharBuffer charBuffer0 = CharBuffer.wrap((CharSequence) "declare");
        Document.OutputSettings document_OutputSettings0 = new Document.OutputSettings();
        Attribute.html("*c7BNKA}abGu(cxUj", "*c7BNKA}abGu(cxUj", (Appendable) charBuffer0, document_OutputSettings0);
    }

    @Test(timeout = 4000)
    public void test1919() throws Throwable {
        CharBuffer charBuffer0 = CharBuffer.allocate(2);
        Document.OutputSettings document_OutputSettings0 = new Document.OutputSettings();
        Attribute.html("N=gxE[6rvT[~6CN#", "reado", (Appendable) charBuffer0, document_OutputSettings0);
    }

    @Test(timeout = 4000)
    public void test2020() throws Throwable {
        Document.OutputSettings document_OutputSettings0 = new Document.OutputSettings();
        Attribute.html("", "", (Appendable) null, document_OutputSettings0);
    }

    @Test(timeout = 4000)
    public void test2121() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attribute attribute0 = new Attribute("YhjH X&BodRuSpBVc", "YhjH X&BodRuSpBVc", attributes0);
        CharBuffer charBuffer0 = CharBuffer.wrap((CharSequence) "1");
        Document.OutputSettings document_OutputSettings0 = new Document.OutputSettings();
        attribute0.html((Appendable) charBuffer0, document_OutputSettings0);
    }

    @Test(timeout = 4000)
    public void test2222() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attribute attribute0 = new Attribute("?sW,9,", "t_IH22J_RVbg<", attributes0);
        char[] charArray0 = new char[9];
        CharBuffer charBuffer0 = CharBuffer.wrap(charArray0);
        Document.OutputSettings document_OutputSettings0 = new Document.OutputSettings();
        attribute0.html((Appendable) charBuffer0, document_OutputSettings0);
    }

    @Test(timeout = 4000)
    public void test2323() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attribute attribute0 = new Attribute("          ", "xml", attributes0);
        Document.OutputSettings document_OutputSettings0 = new Document.OutputSettings();
        attribute0.html((Appendable) null, document_OutputSettings0);
    }

    @Test(timeout = 4000)
    public void test2424() throws Throwable {
        Attribute attribute0 = new Attribute("DoctypeName", (String) null);
        MockFileWriter mockFileWriter0 = new MockFileWriter("z", true);
        mockFileWriter0.close();
        Document.OutputSettings document_OutputSettings0 = new Document.OutputSettings();
        attribute0.html((Appendable) mockFileWriter0, document_OutputSettings0);
    }

    @Test(timeout = 4000)
    public void test2525() throws Throwable {
        Attribute.createFromEncoded("", (String) null);
    }

    @Test(timeout = 4000)
    public void test2626() throws Throwable {
        Attribute.createFromEncoded("", "compact");
    }

    @Test(timeout = 4000)
    public void test2727() throws Throwable {
        Attribute attribute0 = null;
        attribute0 = new Attribute("", (String) null, (Attributes) null);
    }

    @Test(timeout = 4000)
    public void test2828() throws Throwable {
        Attribute attribute0 = null;
        attribute0 = new Attribute("", "");
    }

    @Test(timeout = 4000)
    public void test2929() throws Throwable {
        CharArrayWriter charArrayWriter0 = new CharArrayWriter(144);
        Document.OutputSettings document_OutputSettings0 = new Document.OutputSettings();
        Attribute.html("inert", "inert", (Appendable) charArrayWriter0, document_OutputSettings0);
        charArrayWriter0.toString();
    }

    @Test(timeout = 4000)
    public void test2930() throws Throwable {
        CharArrayWriter charArrayWriter0 = new CharArrayWriter(144);
        Document.OutputSettings document_OutputSettings0 = new Document.OutputSettings();
        Attribute.html("inert", "inert", (Appendable) charArrayWriter0, document_OutputSettings0);
        charArrayWriter0.size();
    }

    @Test(timeout = 4000)
    public void test3031() throws Throwable {
        MockPrintWriter mockPrintWriter0 = new MockPrintWriter("& nvO'");
        Document.OutputSettings document_OutputSettings0 = new Document.OutputSettings();
        Attribute.html("disabled", "mLGE1%D", (Appendable) mockPrintWriter0, document_OutputSettings0);
        document_OutputSettings0.outline();
    }

    @Test(timeout = 4000)
    public void test3132() throws Throwable {
        Attribute attribute0 = new Attribute("t'Q:, L\"`AZ,}#", "org.jsoup.nodes.Entities$1");
        MockFile mockFile0 = new MockFile("t'Q:, L\"`AZ,}#", "t'Q:, L\"`AZ,}#");
        MockPrintWriter mockPrintWriter0 = new MockPrintWriter(mockFile0);
        Document.OutputSettings document_OutputSettings0 = new Document.OutputSettings();
        attribute0.html((Appendable) mockPrintWriter0, document_OutputSettings0);
        attribute0.getValue();
    }

    @Test(timeout = 4000)
    public void test3233() throws Throwable {
        Attribute attribute0 = new Attribute("u9/o po>@6G", (String) null);
        String string0 = attribute0.getValue();
    }

    @Test(timeout = 4000)
    public void test3334() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attribute attribute0 = new Attribute("n", "n", attributes0);
        String string0 = attribute0.getKey();
    }

    @Test(timeout = 4000)
    public void test3435() throws Throwable {
        Attribute attribute0 = Attribute.createFromEncoded("disabled", "disabled");
        attribute0.hashCode();
    }

    @Test(timeout = 4000)
    public void test3536() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attribute attribute0 = new Attribute("bT@JuZR.:Vi", (String) null, attributes0);
        attribute0.hashCode();
    }

    @Test(timeout = 4000)
    public void test3637() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attribute attribute0 = new Attribute("bT@JuZR.:Vi", (String) null, attributes0);
        Attribute attribute1 = new Attribute("bT@JuZR.:Vi", "", attributes0);
        boolean boolean0 = attribute0.equals(attribute1);
    }

    @Test(timeout = 4000)
    public void test3738() throws Throwable {
        Attribute attribute0 = Attribute.createFromEncoded("data-readonly", "declare");
        Attribute attribute1 = Attribute.createFromEncoded("data-readonly", "data-readonly");
        boolean boolean0 = attribute1.equals(attribute0);
    }

    @Test(timeout = 4000)
    public void test3839() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attribute attribute0 = new Attribute("bT@JuZR.:Vi", (String) null, attributes0);
        Attribute attribute1 = attribute0.clone();
        boolean boolean0 = attribute0.equals(attribute1);
    }

    @Test(timeout = 4000)
    public void test3940() throws Throwable {
        Attribute attribute0 = new Attribute("readonly", "noframes", (Attributes) null);
        Attribute attribute1 = new Attribute("noframes", "", (Attributes) null);
        boolean boolean0 = attribute0.equals(attribute1);
    }

    @Test(timeout = 4000)
    public void test3941() throws Throwable {
        Attribute attribute0 = new Attribute("readonly", "noframes", (Attributes) null);
        Attribute attribute1 = new Attribute("noframes", "", (Attributes) null);
        boolean boolean0 = attribute0.equals(attribute1);
        attribute1.getValue();
    }

    @Test(timeout = 4000)
    public void test4042() throws Throwable {
        Attribute attribute0 = new Attribute("YhjH X&BodRuSpBVc", "YhjH X&BodRuSpBVc");
        boolean boolean0 = attribute0.equals((Object) null);
    }

    @Test(timeout = 4000)
    public void test4143() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attribute attribute0 = new Attribute("bT@JuZR.:Vi", (String) null, attributes0);
        boolean boolean0 = attribute0.equals(attribute0);
    }

    @Test(timeout = 4000)
    public void test4244() throws Throwable {
        Attribute attribute0 = Attribute.createFromEncoded("Oz[4", "Oz[4");
        boolean boolean0 = attribute0.equals("Oz[4=\"Oz[4\"");
    }

    @Test(timeout = 4000)
    public void test4345() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attribute attribute0 = new Attribute("bT@JuZR.:Vi", (String) null, attributes0);
        boolean boolean0 = attribute0.isBooleanAttribute();
    }

    @Test(timeout = 4000)
    public void test4446() throws Throwable {
        Attribute attribute0 = Attribute.createFromEncoded("Oz[4", "Oz[4");
        boolean boolean0 = attribute0.isBooleanAttribute();
    }

    @Test(timeout = 4000)
    public void test4547() throws Throwable {
        Attribute attribute0 = new Attribute("readonly", "noframes", (Attributes) null);
        boolean boolean0 = attribute0.isBooleanAttribute();
    }

    @Test(timeout = 4000)
    public void test4548() throws Throwable {
        Attribute attribute0 = new Attribute("readonly", "noframes", (Attributes) null);
        boolean boolean0 = attribute0.isBooleanAttribute();
        attribute0.getValue();
    }

    @Test(timeout = 4000)
    public void test4649() throws Throwable {
        Attribute attribute0 = Attribute.createFromEncoded("Oz[4", "Oz[4");
        attribute0.setKey("value");
        String string0 = attribute0.html();
    }

    @Test(timeout = 4000)
    public void test4750() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attribute attribute0 = new Attribute("T", "", attributes0);
        String string0 = attribute0.toString();
    }

    @Test(timeout = 4000)
    public void test4851() throws Throwable {
        Document.OutputSettings document_OutputSettings0 = new Document.OutputSettings();
        Document.OutputSettings.Syntax document_OutputSettings_Syntax0 = Document.OutputSettings.Syntax.xml;
        document_OutputSettings0.syntax(document_OutputSettings_Syntax0);
        boolean boolean0 = Attribute.shouldCollapseAttribute("nowrap", "l{{a7;~eiyx`ftLu", document_OutputSettings0);
    }

    @Test(timeout = 4000)
    public void test4952() throws Throwable {
        boolean boolean0 = Attribute.isDataAttribute("data-");
    }

    @Test(timeout = 4000)
    public void test5053() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attribute attribute0 = new Attribute("n", "n", attributes0);
        String string0 = attribute0.setValue("n");
        attribute0.getValue();
    }

    @Test(timeout = 4000)
    public void test5054() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attribute attribute0 = new Attribute("n", "n", attributes0);
        String string0 = attribute0.setValue("n");
    }

    @Test(timeout = 4000)
    public void test5155() throws Throwable {
        Attribute attribute0 = Attribute.createFromEncoded("Oz[4", "Oz[4");
        Attributes attributes0 = new Attributes();
        attributes0.put(attribute0);
        attribute0.setValue("value=\"Oz[4\"");
        attribute0.getValue();
    }

    @Test(timeout = 4000)
    public void test5256() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attribute attribute0 = new Attribute("n", "n", attributes0);
        attribute0.setKey("M");
        attribute0.getKey();
    }

    @Test(timeout = 4000)
    public void test5357() throws Throwable {
        Attribute attribute0 = new Attribute("e'E,YoM(JP{m&", "e'E,YoM(JP{m&");
        Document.OutputSettings document_OutputSettings0 = new Document.OutputSettings();
        boolean boolean0 = attribute0.shouldCollapseAttribute(document_OutputSettings0);
    }

    @Test(timeout = 4000)
    public void test5458() throws Throwable {
        Attribute attribute0 = Attribute.createFromEncoded("data-readonly", "declare");
        boolean boolean0 = attribute0.isDataAttribute();
    }

    @Test(timeout = 4000)
    public void test5459() throws Throwable {
        Attribute attribute0 = Attribute.createFromEncoded("data-readonly", "declare");
        boolean boolean0 = attribute0.isDataAttribute();
        attribute0.getValue();
    }

    @Test(timeout = 4000)
    public void test5560() throws Throwable {
        Attribute attribute0 = Attribute.createFromEncoded("Oz[4", "Oz[4");
        Attributes attributes0 = new Attributes();
        attributes0.put(attribute0);
        attribute0.setKey("value");
    }
}
