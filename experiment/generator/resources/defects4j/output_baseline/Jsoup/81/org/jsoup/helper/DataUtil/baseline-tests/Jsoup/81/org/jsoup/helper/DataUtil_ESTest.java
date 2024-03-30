/*
 * This file was automatically generated by EvoSuite
 * Sun Oct 15 23:20:34 GMT 2023
 */
package org.jsoup.helper;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PushbackInputStream;
import java.io.SequenceInputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Enumeration;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.evosuite.runtime.mock.java.io.MockFile;
import org.evosuite.runtime.mock.java.io.MockPrintStream;
import org.evosuite.runtime.testdata.FileSystemHandling;
import org.jsoup.helper.DataUtil;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class DataUtil_ESTest extends DataUtil_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        byte[] byteArray0 = new byte[9];
        byteArray0[2] = (byte) (-69);
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0);
        PushbackInputStream pushbackInputStream0 = new PushbackInputStream(byteArrayInputStream0);
        Parser parser0 = Parser.htmlParser();
        DataUtil.load((InputStream) pushbackInputStream0, "[EHkIT", "org.jsoup.helper.DataUtil", parser0);
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        byte[] byteArray0 = new byte[8];
        byteArray0[0] = (byte) 112;
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0);
        DataUtil.load((InputStream) byteArrayInputStream0, "%_clm,4}]Jv;K9zqq", "@1vv1[=Jo#+;");
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        byte[] byteArray0 = new byte[3];
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0, 1055, (byte) 0);
        ByteBuffer byteBuffer0 = DataUtil.readToByteBuffer((InputStream) byteArrayInputStream0);
        byteBuffer0.hasArray();
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        Parser parser0 = Parser.xmlParser();
        Document document0 = DataUtil.parseInputStream((InputStream) null, "", "#i:]hA:a#%yP", parser0);
        document0.location();
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        Document document0 = DataUtil.load((InputStream) null, "(?i)\bcharset=s*(?:[\"'])?([^s,;\"']*)", "(?i)\bcharset=s*(?:[\"'])?([^s,;\"']*)");
        document0.location();
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        Enumeration<InputStream> enumeration0 = (Enumeration<InputStream>) mock(Enumeration.class, new ViolatedAssumptionAnswer());
        doReturn(false, false).when(enumeration0).hasMoreElements();
        SequenceInputStream sequenceInputStream0 = new SequenceInputStream(enumeration0);
        Document document0 = DataUtil.load((InputStream) sequenceInputStream0, (String) null, "--------------------------------");
        document0.location();
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        DataUtil.readToByteBuffer((InputStream) null, 0);
    }

    @Test(timeout = 4000)
    public void test077() throws Throwable {
        MockFile mockFile0 = new MockFile("r)AwX|D", "meta[http-equiv=content-type], meta[charset]");
        DataUtil.readFileToByteBuffer(mockFile0);
    }

    @Test(timeout = 4000)
    public void test088() throws Throwable {
        byte[] byteArray0 = new byte[3];
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0, 1055, (byte) 0);
        Parser parser0 = Parser.xmlParser();
        DataUtil.parseInputStream(byteArrayInputStream0, "", "<", parser0);
    }

    @Test(timeout = 4000)
    public void test099() throws Throwable {
        Enumeration<SequenceInputStream> enumeration0 = (Enumeration<SequenceInputStream>) mock(Enumeration.class, new ViolatedAssumptionAnswer());
        doReturn(false).when(enumeration0).hasMoreElements();
        SequenceInputStream sequenceInputStream0 = new SequenceInputStream(enumeration0);
        Parser parser0 = Parser.xmlParser();
        DataUtil.parseInputStream(sequenceInputStream0, "A=<;<sy1]3)K X_D~)i", (String) null, parser0);
    }

    @Test(timeout = 4000)
    public void test1010() throws Throwable {
        PipedInputStream pipedInputStream0 = new PipedInputStream();
        Parser parser0 = Parser.htmlParser();
        DataUtil.parseInputStream(pipedInputStream0, "+S!kJb\".lQy^5q;$Fb", "", parser0);
    }

    @Test(timeout = 4000)
    public void test1111() throws Throwable {
        Parser parser0 = Parser.xmlParser();
        DataUtil.load((InputStream) null, "(?i)\bcharset=s*(?:[\"'])?([^s,;\"']*)", (String) null, parser0);
    }

    @Test(timeout = 4000)
    public void test1212() throws Throwable {
        PipedInputStream pipedInputStream0 = new PipedInputStream();
        DataUtil.load((InputStream) pipedInputStream0, "[a-zA-Z_:][-a-zA-Z0-9_:.]*", "data-");
    }

    @Test(timeout = 4000)
    public void test1313() throws Throwable {
        DataUtil.load((File) null, "94>fy;nV'Dg", "]5#a-w:_'P#");
    }

    @Test(timeout = 4000)
    public void test1414() throws Throwable {
        File file0 = MockFile.createTempFile("G-.tO$:e", "");
        DataUtil.load(file0, "G-.tO$:e", "");
    }

    @Test(timeout = 4000)
    public void test1515() throws Throwable {
        MockFile mockFile0 = new MockFile("");
        File file0 = MockFile.createTempFile("org.jsoup.helper.DataUtil$BomCharset", "", (File) mockFile0);
        FileSystemHandling.shouldAllThrowIOExceptions();
        DataUtil.load(file0, "", "");
    }

    @Test(timeout = 4000)
    public void test1616() throws Throwable {
        MockFile mockFile0 = new MockFile("");
        DataUtil.load((File) mockFile0, "", "");
    }

    @Test(timeout = 4000)
    public void test1717() throws Throwable {
        byte[] byteArray0 = new byte[2];
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0, (-2), (byte) 59);
        MockPrintStream mockPrintStream0 = new MockPrintStream("small");
        DataUtil.crossStreams(byteArrayInputStream0, mockPrintStream0);
    }

    @Test(timeout = 4000)
    public void test1818() throws Throwable {
        Enumeration<InputStream> enumeration0 = (Enumeration<InputStream>) mock(Enumeration.class, new ViolatedAssumptionAnswer());
        doReturn(false).when(enumeration0).hasMoreElements();
        SequenceInputStream sequenceInputStream0 = new SequenceInputStream(enumeration0);
        ByteBuffer byteBuffer0 = DataUtil.readToByteBuffer(sequenceInputStream0, 839);
        byteBuffer0.limit();
    }

    @Test(timeout = 4000)
    public void test1919() throws Throwable {
        byte[] byteArray0 = new byte[7];
        byteArray0[1] = (byte) 4;
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0);
        DataUtil.load((InputStream) byteArrayInputStream0, "f:KS`9Mq|~h#YN", "f:KS`9Mq|~h#YN");
    }

    @Test(timeout = 4000)
    public void test2020() throws Throwable {
        byte[] byteArray0 = new byte[8];
        byteArray0[0] = (byte) (-76);
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0);
        DataUtil.load((InputStream) byteArrayInputStream0, "charset=", "charset=");
    }

    @Test(timeout = 4000)
    public void test2121() throws Throwable {
        String string0 = DataUtil.mimeBoundary();
    }

    @Test(timeout = 4000)
    public void test2222() throws Throwable {
        String string0 = DataUtil.getCharsetFromContentType((String) null);
    }

    @Test(timeout = 4000)
    public void test2323() throws Throwable {
        String string0 = DataUtil.getCharsetFromContentType("itemscope");
    }

    @Test(timeout = 4000)
    public void test2424() throws Throwable {
        MockFile mockFile0 = new MockFile("\"R^k6^2aU(8*wN9L|W@", "\"R^k6^2aU(8*wN9L|W@");
        MockFile.createTempFile("\"R^k6^2aU(8*wN9L|W@", "big", (File) mockFile0);
        DataUtil.readFileToByteBuffer(mockFile0);
    }

    @Test(timeout = 4000)
    public void test2525() throws Throwable {
        DataUtil.readFileToByteBuffer((File) null);
    }

    @Test(timeout = 4000)
    public void test2626() throws Throwable {
        File file0 = MockFile.createTempFile(":dn$8", ":dn$8");
        ByteBuffer byteBuffer0 = DataUtil.readFileToByteBuffer(file0);
        byteBuffer0.capacity();
    }

    @Test(timeout = 4000)
    public void test2727() throws Throwable {
        DataUtil.readToByteBuffer((InputStream) null, (-1805));
    }

    @Test(timeout = 4000)
    public void test2828() throws Throwable {
        File file0 = MockFile.createTempFile("[+{'k", "");
        Document document0 = DataUtil.load(file0, "UTF-32", "UTF-32");
        document0.location();
    }

    @Test(timeout = 4000)
    public void test2929() throws Throwable {
        PipedInputStream pipedInputStream0 = new PipedInputStream();
        PipedOutputStream pipedOutputStream0 = new PipedOutputStream(pipedInputStream0);
        byte[] byteArray0 = new byte[5];
        pipedOutputStream0.write(byteArray0);
        // Undeclared exception!
        DataUtil.crossStreams(pipedInputStream0, pipedOutputStream0);
    }

    @Test(timeout = 4000)
    public void test3030() throws Throwable {
        byte[] byteArray0 = new byte[3];
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0, 1055, (byte) 0);
        MockFile mockFile0 = new MockFile("", "=");
        MockPrintStream mockPrintStream0 = new MockPrintStream(mockFile0);
        DataUtil.crossStreams(byteArrayInputStream0, mockPrintStream0);
        byteArrayInputStream0.available();
    }

    @Test(timeout = 4000)
    public void test3131() throws Throwable {
        Parser parser0 = Parser.xmlParser();
        Document document0 = DataUtil.load((InputStream) null, (String) null, "", parser0);
        document0.location();
    }

    @Test(timeout = 4000)
    public void test3232() throws Throwable {
        File file0 = MockFile.createTempFile("charset=", "charset=");
        DataUtil.load(file0, "", "#i:]hA:a#%yP");
    }

    @Test(timeout = 4000)
    public void test3333() throws Throwable {
        ByteBuffer byteBuffer0 = DataUtil.emptyByteBuffer();
        byteBuffer0.remaining();
    }

    @Test(timeout = 4000)
    public void test3434() throws Throwable {
        Enumeration<SequenceInputStream> enumeration0 = (Enumeration<SequenceInputStream>) mock(Enumeration.class, new ViolatedAssumptionAnswer());
        doReturn(false).when(enumeration0).hasMoreElements();
        SequenceInputStream sequenceInputStream0 = new SequenceInputStream(enumeration0);
        BufferedInputStream bufferedInputStream0 = new BufferedInputStream(sequenceInputStream0);
        PushbackInputStream pushbackInputStream0 = new PushbackInputStream(bufferedInputStream0);
        DataUtil.load((InputStream) pushbackInputStream0, (String) null, (String) null);
    }

    @Test(timeout = 4000)
    public void test3535() throws Throwable {
        DataUtil.readToByteBuffer((InputStream) null);
    }
}
