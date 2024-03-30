/*
 * This file was automatically generated by EvoSuite
 * Sun Oct 15 23:11:26 GMT 2023
 */
package org.jsoup.nodes;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PipedWriter;
import java.io.StringWriter;
import java.nio.BufferOverflowException;
import java.nio.CharBuffer;
import java.nio.ReadOnlyBufferException;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Map;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class Attributes_ESTest extends Attributes_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attribute attribute0 = Attribute.createFromEncoded("org.jsoup.nodes.Attributes", "org.jsoup.nodes.Attributes");
        Attributes attributes1 = attributes0.put(attribute0);
        attributes1.hashCode();
        attributes0.size();
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attributes attributes1 = attributes0.clone();
        attributes1.size();
    }

    @Test(timeout = 4000)
    public void test012() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attributes attributes1 = attributes0.clone();
        Attributes attributes2 = attributes1.put("=\"", "");
        boolean boolean0 = attributes2.equals(attributes0);
        attributes1.size();
    }

    @Test(timeout = 4000)
    public void test013() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attributes attributes1 = attributes0.clone();
        Attributes attributes2 = attributes1.put("=\"", "");
        boolean boolean0 = attributes2.equals(attributes0);
    }

    @Test(timeout = 4000)
    public void test024() throws Throwable {
        Attributes attributes0 = new Attributes();
        attributes0.put("meta[charset]", "pS{q6zGQ?+REZy+?Js");
        attributes0.putIgnoreCase("", "");
        attributes0.remove("");
        attributes0.size();
    }

    @Test(timeout = 4000)
    public void test035() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attribute attribute0 = new Attribute("b.2NM", "9WZhldeAMNnU", attributes0);
        Attributes attributes1 = attributes0.put(attribute0);
        Attributes attributes2 = attributes1.put("LV?0B<|imMs _P3uz", true);
        attributes2.putIgnoreCase("9~h7&;@\"ukvksw+", "(eDMN-lh&2oXV");
        Attributes attributes3 = attributes2.put("mMkaS)^f1kh<8FK@", "9WZhldeAMNnU");
        attributes2.put("qK%e4xM@jmRp*(vCu{v", true);
        attributes3.addAll(attributes1);
        attributes2.size();
    }

    @Test(timeout = 4000)
    public void test036() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attribute attribute0 = new Attribute("b.2NM", "9WZhldeAMNnU", attributes0);
        Attributes attributes1 = attributes0.put(attribute0);
        Attributes attributes2 = attributes1.put("LV?0B<|imMs _P3uz", true);
        attributes2.putIgnoreCase("9~h7&;@\"ukvksw+", "(eDMN-lh&2oXV");
        Attributes attributes3 = attributes2.put("mMkaS)^f1kh<8FK@", "9WZhldeAMNnU");
        attributes2.put("qK%e4xM@jmRp*(vCu{v", true);
        attributes3.addAll(attributes1);
        attributes0.size();
    }

    @Test(timeout = 4000)
    public void test047() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attributes attributes1 = attributes0.clone();
        Attributes attributes2 = attributes1.put("`\"Mxiv?X8|=rS#", "wlq8hR*hLL`$^%O");
        Attribute attribute0 = new Attribute("wlq8hR*hLL`$^%O", "4%'Z\"", attributes2);
        attributes2.put(attribute0);
        String[] stringArray0 = new String[3];
        attributes1.keys = stringArray0;
        attributes2.put("", "4%'Z\"");
        attributes1.size();
    }

    @Test(timeout = 4000)
    public void test048() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attributes attributes1 = attributes0.clone();
        Attributes attributes2 = attributes1.put("`\"Mxiv?X8|=rS#", "wlq8hR*hLL`$^%O");
        Attribute attribute0 = new Attribute("wlq8hR*hLL`$^%O", "4%'Z\"", attributes2);
        attributes2.put(attribute0);
        String[] stringArray0 = new String[3];
        attributes1.keys = stringArray0;
        attributes2.put("", "4%'Z\"");
    }

    @Test(timeout = 4000)
    public void test059() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attribute attribute0 = new Attribute("org.jsoup.nodes.Attributes$Dataset", "org.jsoup.nodes.Attributes$Dataset", attributes0);
        Attributes attributes1 = attributes0.put(attribute0);
        int int0 = attributes1.size();
    }

    @Test(timeout = 4000)
    public void test0610() throws Throwable {
        Attributes attributes0 = new Attributes();
        Iterator<Attribute> iterator0 = attributes0.iterator();
    }

    @Test(timeout = 4000)
    public void test0711() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attributes attributes1 = attributes0.clone();
        Attributes attributes2 = attributes1.put("=\"", false);
        attributes2.put("=\"", "");
        String[] stringArray0 = new String[5];
        stringArray0[0] = "";
        attributes1.keys = stringArray0;
        int int0 = attributes2.indexOfKey("");
        attributes2.size();
    }

    @Test(timeout = 4000)
    public void test0712() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attributes attributes1 = attributes0.clone();
        Attributes attributes2 = attributes1.put("=\"", false);
        attributes2.put("=\"", "");
        String[] stringArray0 = new String[5];
        stringArray0[0] = "";
        attributes1.keys = stringArray0;
        int int0 = attributes2.indexOfKey("");
    }

    @Test(timeout = 4000)
    public void test0813() throws Throwable {
        Attributes attributes0 = new Attributes();
        attributes0.putIgnoreCase("`EEuNYv:nJ+J{L", (String) null);
        Attributes attributes1 = attributes0.put("b, s&(&qS", true);
        int int0 = attributes1.indexOfKey("b, s&(&qS");
        attributes0.size();
    }

    @Test(timeout = 4000)
    public void test0814() throws Throwable {
        Attributes attributes0 = new Attributes();
        attributes0.putIgnoreCase("`EEuNYv:nJ+J{L", (String) null);
        Attributes attributes1 = attributes0.put("b, s&(&qS", true);
        int int0 = attributes1.indexOfKey("b, s&(&qS");
    }

    @Test(timeout = 4000)
    public void test0915() throws Throwable {
        Attributes attributes0 = new Attributes();
        int int0 = attributes0.indexOfKey("VMdmBBfd");
    }

    @Test(timeout = 4000)
    public void test1016() throws Throwable {
        Attributes attributes0 = new Attributes();
        String string0 = attributes0.html();
    }

    @Test(timeout = 4000)
    public void test1117() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attributes attributes1 = attributes0.put("5L-%=Hl*7I3]8g2k1", "5L-%=Hl*7I3]8g2k1");
        String string0 = attributes1.getIgnoreCase("5L-%=Hl*7I3]8g2k1");
        attributes0.size();
    }

    @Test(timeout = 4000)
    public void test1118() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attributes attributes1 = attributes0.put("5L-%=Hl*7I3]8g2k1", "5L-%=Hl*7I3]8g2k1");
        String string0 = attributes1.getIgnoreCase("5L-%=Hl*7I3]8g2k1");
    }

    @Test(timeout = 4000)
    public void test1219() throws Throwable {
        String string0 = Attributes.checkNotNull("");
    }

    @Test(timeout = 4000)
    public void test1320() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attributes attributes1 = attributes0.clone();
        Attribute attribute0 = new Attribute("RCDATAEndTagOpen", "RCDATAEndTagOpen");
        attributes1.put(attribute0);
        attributes1.keys = null;
        attributes1.toString();
    }

    @Test(timeout = 4000)
    public void test1421() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attributes attributes1 = attributes0.put("data-", "SHeyv0VWK;r*");
        attributes1.keys = null;
        attributes0.removeIgnoreCase("");
    }

    @Test(timeout = 4000)
    public void test1522() throws Throwable {
        Attributes attributes0 = new Attributes();
        attributes0.removeIgnoreCase((String) null);
    }

    @Test(timeout = 4000)
    public void test1623() throws Throwable {
        Attributes attributes0 = new Attributes();
        attributes0.putIgnoreCase("", "data-");
        Attributes attributes1 = attributes0.put("data-", "SHeyv0VWK;r*");
        String[] stringArray0 = new String[1];
        attributes1.vals = stringArray0;
        attributes0.removeIgnoreCase("");
    }

    @Test(timeout = 4000)
    public void test1724() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attributes attributes1 = attributes0.put("Should not be reachable", "org.jsoup.nodes.Attributes$Dataset$DatasetIterator");
        Attributes attributes2 = attributes1.clone();
        attributes2.keys = null;
        attributes2.remove("org.jsoup.nodes.Attributes$Dataset$DatasetIterator");
    }

    @Test(timeout = 4000)
    public void test1825() throws Throwable {
        Attributes attributes0 = new Attributes();
        attributes0.remove((String) null);
    }

    @Test(timeout = 4000)
    public void test1926() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attribute attribute0 = new Attribute("I", "I");
        Attributes attributes1 = attributes0.put(attribute0);
        attributes1.put(" i=\"I\"", "I");
        Attributes attributes2 = attributes1.put(">", ">");
        String[] stringArray0 = new String[2];
        attributes2.keys = stringArray0;
        attributes0.remove("O*tr^");
    }

    @Test(timeout = 4000)
    public void test2027() throws Throwable {
        Attributes attributes0 = new Attributes();
        attributes0.putIgnoreCase((String) null, "org.jsoup.nodes.Attributes$1");
    }

    @Test(timeout = 4000)
    public void test2128() throws Throwable {
        Attributes attributes0 = new Attributes();
        String[] stringArray0 = new String[2];
        attributes0.keys = stringArray0;
        attributes0.putIgnoreCase("reversed", "");
    }

    @Test(timeout = 4000)
    public void test2229() throws Throwable {
        Attributes attributes0 = new Attributes();
        attributes0.vals = null;
        Attribute attribute0 = Attribute.createFromEncoded("}CBA}@k%:m(lc7d,$", "0 #1,unlyI\"F<d (");
        attributes0.put(attribute0);
    }

    @Test(timeout = 4000)
    public void test2330() throws Throwable {
        Attributes attributes0 = new Attributes();
        attributes0.put((Attribute) null);
    }

    @Test(timeout = 4000)
    public void test2431() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attribute attribute0 = Attribute.createFromEncoded("&quot;", "t8}n(Ukq@WGX");
        Attributes attributes1 = attributes0.put(attribute0);
        String[] stringArray0 = new String[0];
        attributes1.keys = stringArray0;
        attributes1.put(attribute0);
    }

    @Test(timeout = 4000)
    public void test2532() throws Throwable {
        Attributes attributes0 = new Attributes();
        attributes0.vals = null;
        attributes0.put("org.jsoup.nodes.Attributes$1", true);
    }

    @Test(timeout = 4000)
    public void test2633() throws Throwable {
        Attributes attributes0 = new Attributes();
        attributes0.put((String) null, true);
    }

    @Test(timeout = 4000)
    public void test2734() throws Throwable {
        Attributes attributes0 = new Attributes();
        String[] stringArray0 = new String[6];
        attributes0.keys = stringArray0;
        attributes0.put("", true);
    }

    @Test(timeout = 4000)
    public void test2835() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attributes attributes1 = attributes0.clone();
        attributes1.keys = null;
        attributes1.put("tik7i", "h");
    }

    @Test(timeout = 4000)
    public void test2936() throws Throwable {
        Attributes attributes0 = new Attributes();
        attributes0.put((String) null, (String) null);
    }

    @Test(timeout = 4000)
    public void test3037() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attributes attributes1 = attributes0.clone();
        String[] stringArray0 = new String[6];
        attributes1.keys = stringArray0;
        attributes1.put("PBXi|eE-", "");
    }

    @Test(timeout = 4000)
    public void test3138() throws Throwable {
        Attributes attributes0 = new Attributes();
        attributes0.indexOfKey((String) null);
    }

    @Test(timeout = 4000)
    public void test3239() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attribute attribute0 = new Attribute("org.jsoup.nodes.Attributes$Dataset", "org.jsoup.nodes.Attributes$Dataset", attributes0);
        Attributes attributes1 = attributes0.put(attribute0);
        CharBuffer charBuffer0 = CharBuffer.wrap((CharSequence) "org.jsoup.parser.Token$Tag");
        Document.OutputSettings document_OutputSettings0 = new Document.OutputSettings();
        attributes1.html((Appendable) charBuffer0, document_OutputSettings0);
    }

    @Test(timeout = 4000)
    public void test3340() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attributes attributes1 = attributes0.put("", true);
        CharBuffer charBuffer0 = CharBuffer.allocate(0);
        Document.OutputSettings document_OutputSettings0 = new Document.OutputSettings();
        attributes1.html((Appendable) charBuffer0, document_OutputSettings0);
    }

    @Test(timeout = 4000)
    public void test3441() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attribute attribute0 = new Attribute("RCDATAEndTagOpen", "RCDATAEndTagOpen");
        attributes0.put(attribute0);
        StringWriter stringWriter0 = new StringWriter();
        Document.OutputSettings document_OutputSettings0 = new Document.OutputSettings();
        document_OutputSettings0.charset((Charset) null);
        attributes0.html((Appendable) stringWriter0, document_OutputSettings0);
    }

    @Test(timeout = 4000)
    public void test3542() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attribute attribute0 = new Attribute("b.2NM", "9WZhldeAMNnU", attributes0);
        Attributes attributes1 = attributes0.put(attribute0);
        Attributes attributes2 = attributes1.put("LV?0B<|imMs _P3uz", true);
        attributes2.put("mMkaS)^f1kh<8FK@", "9WZhldeAMNnU");
        Attributes attributes3 = attributes1.clone();
        String[] stringArray0 = new String[2];
        attributes3.vals = stringArray0;
        PipedInputStream pipedInputStream0 = new PipedInputStream();
        PipedOutputStream pipedOutputStream0 = new PipedOutputStream(pipedInputStream0);
        OutputStreamWriter outputStreamWriter0 = new OutputStreamWriter(pipedOutputStream0);
        Document.OutputSettings document_OutputSettings0 = new Document.OutputSettings();
        attributes3.html((Appendable) outputStreamWriter0, document_OutputSettings0);
    }

    @Test(timeout = 4000)
    public void test3643() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attributes attributes1 = attributes0.put("z|b:q)cv6rpgml", "z|b:q)cv6rpgml");
        PipedWriter pipedWriter0 = new PipedWriter();
        Document.OutputSettings document_OutputSettings0 = new Document.OutputSettings();
        attributes1.html((Appendable) pipedWriter0, document_OutputSettings0);
    }

    @Test(timeout = 4000)
    public void test3744() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attributes attributes1 = attributes0.put("data-", "X^XL_Gwy9 8GsB");
        attributes1.vals = null;
        attributes0.html();
    }

    @Test(timeout = 4000)
    public void test3845() throws Throwable {
        Attributes attributes0 = new Attributes();
        attributes0.hasKeyIgnoreCase((String) null);
    }

    @Test(timeout = 4000)
    public void test3946() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attributes attributes1 = attributes0.put("&=ykP@l1G)", "&=ykP@l1G)");
        String[] stringArray0 = new String[0];
        attributes1.keys = stringArray0;
        attributes0.hasKeyIgnoreCase("%vAi");
    }

    @Test(timeout = 4000)
    public void test4047() throws Throwable {
        Attributes attributes0 = new Attributes();
        attributes0.hasKey((String) null);
    }

    @Test(timeout = 4000)
    public void test4148() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attribute attribute0 = new Attribute("c{>}MhF", "V{;JIf<qV8ex5MC#", attributes0);
        Attributes attributes1 = attributes0.put(attribute0);
        attributes1.vals = null;
        attributes0.getIgnoreCase("c{>}MhF");
    }

    @Test(timeout = 4000)
    public void test4249() throws Throwable {
        Attributes attributes0 = new Attributes();
        attributes0.getIgnoreCase((String) null);
    }

    @Test(timeout = 4000)
    public void test4350() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attributes attributes1 = attributes0.put("org.jsoup.nodes.XmlDeclaration", "QAEXrY-H:?Q:KL9");
        Attributes attributes2 = attributes1.put("QAEXrY-H:?Q:KL9", false);
        attributes2.keys = null;
        attributes2.get("org.jsoup.nodes.XmlDeclaration");
    }

    @Test(timeout = 4000)
    public void test4451() throws Throwable {
        Attributes attributes0 = new Attributes();
        attributes0.get((String) null);
    }

    @Test(timeout = 4000)
    public void test4552() throws Throwable {
        Attributes attributes0 = new Attributes();
        attributes0.vals = null;
        attributes0.clone();
    }

    @Test(timeout = 4000)
    public void test4653() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attributes attributes1 = attributes0.put("data-", true);
        attributes1.vals = null;
        attributes1.asList();
    }

    @Test(timeout = 4000)
    public void test4754() throws Throwable {
        Attributes attributes0 = new Attributes();
        attributes0.addAll((Attributes) null);
    }

    @Test(timeout = 4000)
    public void test4855() throws Throwable {
        Attributes attributes0 = new Attributes();
        attributes0.put("", "");
        attributes0.addAll(attributes0);
    }

    @Test(timeout = 4000)
    public void test4956() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attribute attribute0 = Attribute.createFromEncoded("=\"", "=\"");
        Attributes attributes1 = attributes0.put(attribute0);
        Attributes attributes2 = attributes0.put("Tz5kUs>*N:>gWj~^uN", false);
        String[] stringArray0 = new String[0];
        attributes2.vals = stringArray0;
        attributes2.addAll(attributes1);
    }

    @Test(timeout = 4000)
    public void test5057() throws Throwable {
        Attributes attributes0 = new Attributes();
        attributes0.put("c- 12< @/fo6k-s.ZFx", "c- 12< @/fo6k-s.ZFx");
        String[] stringArray0 = new String[8];
        attributes0.keys = stringArray0;
        PipedOutputStream pipedOutputStream0 = new PipedOutputStream();
        Charset charset0 = Charset.defaultCharset();
        OutputStreamWriter outputStreamWriter0 = new OutputStreamWriter(pipedOutputStream0, charset0);
        Document.OutputSettings document_OutputSettings0 = new Document.OutputSettings();
        attributes0.html((Appendable) outputStreamWriter0, document_OutputSettings0);
        attributes0.size();
    }

    @Test(timeout = 4000)
    public void test5158() throws Throwable {
        Attributes attributes0 = new Attributes();
        attributes0.remove("-.nWAJ>%fpJ7FB[TH_");
        attributes0.size();
    }

    @Test(timeout = 4000)
    public void test5259() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attribute attribute0 = Attribute.createFromEncoded("D", "dnc4Tc?E");
        Attributes attributes1 = attributes0.put(attribute0);
        attributes1.put("D", "D");
        attributes0.size();
    }

    @Test(timeout = 4000)
    public void test5360() throws Throwable {
        Attributes attributes0 = new Attributes();
        int int0 = attributes0.size();
    }

    @Test(timeout = 4000)
    public void test5461() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attributes attributes1 = attributes0.put("org.jsoup.nodes.Attributes", true);
        Attributes attributes2 = attributes0.clone();
        boolean boolean0 = attributes1.equals(attributes2);
        attributes0.size();
    }

    @Test(timeout = 4000)
    public void test5462() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attributes attributes1 = attributes0.put("org.jsoup.nodes.Attributes", true);
        Attributes attributes2 = attributes0.clone();
        boolean boolean0 = attributes1.equals(attributes2);
    }

    @Test(timeout = 4000)
    public void test5563() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attributes attributes1 = new Attributes();
        attributes0.put("", "");
        boolean boolean0 = attributes1.equals(attributes0);
        attributes0.size();
    }

    @Test(timeout = 4000)
    public void test5564() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attributes attributes1 = new Attributes();
        attributes0.put("", "");
        boolean boolean0 = attributes1.equals(attributes0);
    }

    @Test(timeout = 4000)
    public void test5665() throws Throwable {
        Attributes attributes0 = new Attributes();
        boolean boolean0 = attributes0.equals((Object) null);
    }

    @Test(timeout = 4000)
    public void test5766() throws Throwable {
        Attributes attributes0 = new Attributes();
        boolean boolean0 = attributes0.equals(attributes0);
    }

    @Test(timeout = 4000)
    public void test5867() throws Throwable {
        Attributes attributes0 = new Attributes();
        boolean boolean0 = attributes0.equals("org.jsoup.nodes.Attributes");
    }

    @Test(timeout = 4000)
    public void test5968() throws Throwable {
        Attributes attributes0 = new Attributes();
        attributes0.put("formnovalidate", "formnovalidate");
        attributes0.toString();
        attributes0.size();
    }

    @Test(timeout = 4000)
    public void test6069() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attribute attribute0 = Attribute.createFromEncoded("org.jsoup.nodes.Attributes", "org.jsoup.nodes.Attributes");
        attributes0.put(attribute0);
        attributes0.toString();
        attributes0.size();
    }

    @Test(timeout = 4000)
    public void test6170() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attributes attributes1 = attributes0.put("", true);
        String string0 = attributes1.html();
        attributes0.size();
    }

    @Test(timeout = 4000)
    public void test6171() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attributes attributes1 = attributes0.put("", true);
        String string0 = attributes1.html();
    }

    @Test(timeout = 4000)
    public void test6272() throws Throwable {
        Attributes attributes0 = new Attributes();
        attributes0.put("1)0Gd3R@_DXgfy)mM", true);
        CharArrayWriter charArrayWriter0 = new CharArrayWriter(0);
        Document.OutputSettings document_OutputSettings0 = new Document.OutputSettings();
        Document.OutputSettings.Syntax document_OutputSettings_Syntax0 = Document.OutputSettings.Syntax.xml;
        Document.OutputSettings document_OutputSettings1 = document_OutputSettings0.syntax(document_OutputSettings_Syntax0);
        attributes0.html((Appendable) charArrayWriter0, document_OutputSettings1);
        attributes0.size();
    }

    @Test(timeout = 4000)
    public void test6373() throws Throwable {
        Attributes attributes0 = new Attributes();
        attributes0.put("", true);
        attributes0.asList();
    }

    @Test(timeout = 4000)
    public void test6474() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attributes attributes1 = attributes0.put("SHeyv0VWK;r*", "m?j49`a");
        attributes1.asList();
        attributes0.size();
    }

    @Test(timeout = 4000)
    public void test6575() throws Throwable {
        Attributes attributes0 = new Attributes();
        attributes0.addAll(attributes0);
        attributes0.size();
    }

    @Test(timeout = 4000)
    public void test6676() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attributes attributes1 = attributes0.put("SHeyv0VWK;r*", "m?j49`a");
        boolean boolean0 = attributes1.hasKeyIgnoreCase("SHeyv0VWK;r*");
        attributes0.size();
    }

    @Test(timeout = 4000)
    public void test6677() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attributes attributes1 = attributes0.put("SHeyv0VWK;r*", "m?j49`a");
        boolean boolean0 = attributes1.hasKeyIgnoreCase("SHeyv0VWK;r*");
    }

    @Test(timeout = 4000)
    public void test6778() throws Throwable {
        Attributes attributes0 = new Attributes();
        boolean boolean0 = attributes0.hasKeyIgnoreCase("1)0Gd3R@_DXgfy)mM");
    }

    @Test(timeout = 4000)
    public void test6879() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attribute attribute0 = Attribute.createFromEncoded("j", "j");
        Attributes attributes1 = attributes0.put(attribute0);
        boolean boolean0 = attributes1.hasKey("j");
        attributes0.size();
    }

    @Test(timeout = 4000)
    public void test6880() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attribute attribute0 = Attribute.createFromEncoded("j", "j");
        Attributes attributes1 = attributes0.put(attribute0);
        boolean boolean0 = attributes1.hasKey("j");
    }

    @Test(timeout = 4000)
    public void test6981() throws Throwable {
        Attributes attributes0 = new Attributes();
        boolean boolean0 = attributes0.hasKey("org.jsoup.parser.Token$Tag");
    }

    @Test(timeout = 4000)
    public void test7082() throws Throwable {
        Attributes attributes0 = new Attributes();
        attributes0.removeIgnoreCase("org.jsoup.nodes.Attributes");
        attributes0.size();
    }

    @Test(timeout = 4000)
    public void test7183() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attribute attribute0 = new Attribute("I", "I");
        Attributes attributes1 = attributes0.put(attribute0);
        attributes1.normalize();
        attributes1.put("I", true);
        attributes0.size();
    }

    @Test(timeout = 4000)
    public void test7284() throws Throwable {
        Attributes attributes0 = new Attributes();
        attributes0.put("1)0Gd3R@_DXgfy)mM", true);
        attributes0.put("1)0Gd3R@_DXgfy)mM", true);
        attributes0.size();
    }

    @Test(timeout = 4000)
    public void test7385() throws Throwable {
        Attributes attributes0 = new Attributes();
        String string0 = attributes0.getIgnoreCase("org.jsoup.nodes.Attributes$Dataset");
    }

    @Test(timeout = 4000)
    public void test7486() throws Throwable {
        Attributes attributes0 = new Attributes();
        String string0 = attributes0.get("40W+k;$");
    }

    @Test(timeout = 4000)
    public void test7587() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attribute attribute0 = Attribute.createFromEncoded("org.jsoup.nodes.Attributes", "org.jsoup.nodes.Attributes");
        Attributes attributes1 = attributes0.put(attribute0);
        String string0 = attributes1.get("org.jsoup.nodes.Attributes");
        attributes0.size();
    }

    @Test(timeout = 4000)
    public void test7588() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attribute attribute0 = Attribute.createFromEncoded("org.jsoup.nodes.Attributes", "org.jsoup.nodes.Attributes");
        Attributes attributes1 = attributes0.put(attribute0);
        String string0 = attributes1.get("org.jsoup.nodes.Attributes");
    }

    @Test(timeout = 4000)
    public void test7689() throws Throwable {
        String string0 = Attributes.checkNotNull("S^+er=_dEQFhCB,5");
    }

    @Test(timeout = 4000)
    public void test7790() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attributes attributes1 = attributes0.put("x:=:@", true);
        String string0 = attributes1.getIgnoreCase("x:=:@");
        attributes0.size();
    }

    @Test(timeout = 4000)
    public void test7791() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attributes attributes1 = attributes0.put("x:=:@", true);
        String string0 = attributes1.getIgnoreCase("x:=:@");
    }

    @Test(timeout = 4000)
    public void test7892() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attribute attribute0 = new Attribute("b.2NM", "9WZhldeAMNnU", attributes0);
        Attributes attributes1 = attributes0.put(attribute0);
        Attributes attributes2 = attributes1.put("LV?0B<|imMs _P3uz", true);
        attributes2.putIgnoreCase("9~h7&;@\"ukvksw+", "(eDMN-lh&2oXV");
        Attributes attributes3 = attributes1.clone();
        attributes2.addAll(attributes3);
        attributes0.size();
    }

    @Test(timeout = 4000)
    public void test7993() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attributes attributes1 = attributes0.clone();
        boolean boolean0 = attributes0.equals(attributes1);
    }

    @Test(timeout = 4000)
    public void test7994() throws Throwable {
        Attributes attributes0 = new Attributes();
        Attributes attributes1 = attributes0.clone();
        boolean boolean0 = attributes0.equals(attributes1);
    }

    @Test(timeout = 4000)
    public void test8195() throws Throwable {
        Attributes attributes0 = new Attributes();
        Map<String, String> map0 = attributes0.dataset();
        map0.isEmpty();
    }

    @Test(timeout = 4000)
    public void test8296() throws Throwable {
        Attributes attributes0 = new Attributes();
        String string0 = attributes0.toString();
    }
}
