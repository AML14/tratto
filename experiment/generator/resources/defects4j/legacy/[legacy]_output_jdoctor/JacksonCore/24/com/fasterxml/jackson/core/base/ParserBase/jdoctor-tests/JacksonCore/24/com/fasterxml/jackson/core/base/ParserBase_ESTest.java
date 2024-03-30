/*
 * This file was automatically generated by EvoSuite
 * Sun Oct 15 19:26:56 GMT 2023
 */
package com.fasterxml.jackson.core.base;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.base.ParserBase;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.json.ReaderBasedJsonParser;
import com.fasterxml.jackson.core.json.UTF8DataInputJsonParser;
import com.fasterxml.jackson.core.json.UTF8StreamJsonParser;
import com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer;
import com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer;
import com.fasterxml.jackson.core.util.BufferRecycler;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PushbackInputStream;
import java.io.Reader;
import java.io.SequenceInputStream;
import java.io.StringReader;
import java.util.Enumeration;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.evosuite.runtime.mock.java.io.MockFileInputStream;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class ParserBase_ESTest extends ParserBase_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, true);
        PipedInputStream pipedInputStream0 = new PipedInputStream();
        ByteQuadsCanonicalizer byteQuadsCanonicalizer0 = ByteQuadsCanonicalizer.createRoot();
        byte[] byteArray0 = new byte[6];
        UTF8StreamJsonParser uTF8StreamJsonParser0 = new UTF8StreamJsonParser(iOContext0, 19, pipedInputStream0, (ObjectCodec) null, byteQuadsCanonicalizer0, byteArray0, 9, (byte) (-30), true);
        uTF8StreamJsonParser0.setFeatureMask((-1207));
        uTF8StreamJsonParser0.getFeatureMask();
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        byte[] byteArray0 = new byte[6];
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0);
        PushbackInputStream pushbackInputStream0 = new PushbackInputStream(byteArrayInputStream0);
        IOContext iOContext0 = new IOContext(bufferRecycler0, pushbackInputStream0, false);
        StringReader stringReader0 = new StringReader(">JaJ0Y3tB=>&|\"\"");
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, (byte) 50, stringReader0, (ObjectCodec) null, charsToNameCanonicalizer0);
        JsonParser.Feature jsonParser_Feature0 = JsonParser.Feature.ALLOW_COMMENTS;
        ReaderBasedJsonParser readerBasedJsonParser1 = (ReaderBasedJsonParser) readerBasedJsonParser0.enable(jsonParser_Feature0);
        readerBasedJsonParser1.getTokenLineNr();
    }

    @Test(timeout = 4000)
    public void test012() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        byte[] byteArray0 = new byte[6];
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0);
        PushbackInputStream pushbackInputStream0 = new PushbackInputStream(byteArrayInputStream0);
        IOContext iOContext0 = new IOContext(bufferRecycler0, pushbackInputStream0, false);
        StringReader stringReader0 = new StringReader(">JaJ0Y3tB=>&|\"\"");
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, (byte) 50, stringReader0, (ObjectCodec) null, charsToNameCanonicalizer0);
        JsonParser.Feature jsonParser_Feature0 = JsonParser.Feature.ALLOW_COMMENTS;
        ReaderBasedJsonParser readerBasedJsonParser1 = (ReaderBasedJsonParser) readerBasedJsonParser0.enable(jsonParser_Feature0);
        readerBasedJsonParser1.getFeatureMask();
    }

    @Test(timeout = 4000)
    public void test023() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        byte[] byteArray0 = new byte[6];
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0);
        PushbackInputStream pushbackInputStream0 = new PushbackInputStream(byteArrayInputStream0);
        IOContext iOContext0 = new IOContext(bufferRecycler0, pushbackInputStream0, false);
        StringReader stringReader0 = new StringReader(">JaJ0Y3tB=>&|\"\"");
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, 3, stringReader0, (ObjectCodec) null, charsToNameCanonicalizer0);
        readerBasedJsonParser0.setFeatureMask((byte) 0);
        readerBasedJsonParser0.getFeatureMask();
    }

    @Test(timeout = 4000)
    public void test034() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, true);
        PipedInputStream pipedInputStream0 = new PipedInputStream();
        ByteQuadsCanonicalizer byteQuadsCanonicalizer0 = ByteQuadsCanonicalizer.createRoot();
        byte[] byteArray0 = new byte[6];
        UTF8StreamJsonParser uTF8StreamJsonParser0 = new UTF8StreamJsonParser(iOContext0, 0, pipedInputStream0, (ObjectCodec) null, byteQuadsCanonicalizer0, byteArray0, 9, (byte) (-30), true);
        uTF8StreamJsonParser0.setFeatureMask((byte) 94);
        uTF8StreamJsonParser0.getFeatureMask();
    }

    @Test(timeout = 4000)
    public void test045() throws Throwable {
        IOContext iOContext0 = new IOContext((BufferRecycler) null, (Object) null, true);
        StringReader stringReader0 = new StringReader("Ck");
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        char[] charArray0 = new char[4];
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, 18, stringReader0, (ObjectCodec) null, charsToNameCanonicalizer0, charArray0, 0, 0, true);
        readerBasedJsonParser0.overrideStdFeatures(0, 18);
        readerBasedJsonParser0.getFeatureMask();
    }

    @Test(timeout = 4000)
    public void test056() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, true);
        PipedInputStream pipedInputStream0 = new PipedInputStream();
        ByteQuadsCanonicalizer byteQuadsCanonicalizer0 = ByteQuadsCanonicalizer.createRoot();
        byte[] byteArray0 = new byte[6];
        UTF8StreamJsonParser uTF8StreamJsonParser0 = new UTF8StreamJsonParser(iOContext0, 0, pipedInputStream0, (ObjectCodec) null, byteQuadsCanonicalizer0, byteArray0, 9, (byte) (-30), true);
        JsonLocation jsonLocation0 = uTF8StreamJsonParser0.getCurrentLocation();
        jsonLocation0.getLineNr();
    }

    @Test(timeout = 4000)
    public void test057() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, true);
        PipedInputStream pipedInputStream0 = new PipedInputStream();
        ByteQuadsCanonicalizer byteQuadsCanonicalizer0 = ByteQuadsCanonicalizer.createRoot();
        byte[] byteArray0 = new byte[6];
        UTF8StreamJsonParser uTF8StreamJsonParser0 = new UTF8StreamJsonParser(iOContext0, 0, pipedInputStream0, (ObjectCodec) null, byteQuadsCanonicalizer0, byteArray0, 9, (byte) (-30), true);
        JsonLocation jsonLocation0 = uTF8StreamJsonParser0.getCurrentLocation();
        uTF8StreamJsonParser0.getTokenLineNr();
    }

    @Test(timeout = 4000)
    public void test068() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, false);
        StringReader stringReader0 = new StringReader("trE(NN%oC[S;");
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, 2, stringReader0, (ObjectCodec) null, charsToNameCanonicalizer0);
        JsonLocation jsonLocation0 = readerBasedJsonParser0.getCurrentLocation();
        jsonLocation0.getLineNr();
    }

    @Test(timeout = 4000)
    public void test069() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, false);
        StringReader stringReader0 = new StringReader("trE(NN%oC[S;");
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, 2, stringReader0, (ObjectCodec) null, charsToNameCanonicalizer0);
        JsonLocation jsonLocation0 = readerBasedJsonParser0.getCurrentLocation();
        readerBasedJsonParser0.getTokenLineNr();
    }

    @Test(timeout = 4000)
    public void test0710() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        PipedInputStream pipedInputStream0 = new PipedInputStream(1394);
        DataInputStream dataInputStream0 = new DataInputStream(pipedInputStream0);
        IOContext iOContext0 = new IOContext(bufferRecycler0, dataInputStream0, false);
        ByteQuadsCanonicalizer byteQuadsCanonicalizer0 = ByteQuadsCanonicalizer.createRoot();
        UTF8DataInputJsonParser uTF8DataInputJsonParser0 = new UTF8DataInputJsonParser(iOContext0, 0, dataInputStream0, (ObjectCodec) null, byteQuadsCanonicalizer0, 192);
        JsonParser.Feature jsonParser_Feature0 = JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS;
        UTF8DataInputJsonParser uTF8DataInputJsonParser1 = (UTF8DataInputJsonParser) uTF8DataInputJsonParser0.disable(jsonParser_Feature0);
        uTF8DataInputJsonParser1.getFeatureMask();
    }

    @Test(timeout = 4000)
    public void test0711() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        PipedInputStream pipedInputStream0 = new PipedInputStream(1394);
        DataInputStream dataInputStream0 = new DataInputStream(pipedInputStream0);
        IOContext iOContext0 = new IOContext(bufferRecycler0, dataInputStream0, false);
        ByteQuadsCanonicalizer byteQuadsCanonicalizer0 = ByteQuadsCanonicalizer.createRoot();
        UTF8DataInputJsonParser uTF8DataInputJsonParser0 = new UTF8DataInputJsonParser(iOContext0, 0, dataInputStream0, (ObjectCodec) null, byteQuadsCanonicalizer0, 192);
        JsonParser.Feature jsonParser_Feature0 = JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS;
        UTF8DataInputJsonParser uTF8DataInputJsonParser1 = (UTF8DataInputJsonParser) uTF8DataInputJsonParser0.disable(jsonParser_Feature0);
        uTF8DataInputJsonParser1.getTokenLineNr();
    }

    @Test(timeout = 4000)
    public void test0812() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, true);
        PipedInputStream pipedInputStream0 = new PipedInputStream(1);
        ByteQuadsCanonicalizer byteQuadsCanonicalizer0 = ByteQuadsCanonicalizer.createRoot();
        byte[] byteArray0 = new byte[6];
        UTF8StreamJsonParser uTF8StreamJsonParser0 = new UTF8StreamJsonParser(iOContext0, 0, pipedInputStream0, (ObjectCodec) null, byteQuadsCanonicalizer0, byteArray0, 9, (byte) (-30), true);
        uTF8StreamJsonParser0.setFeatureMask((-1207));
        JsonParser.Feature jsonParser_Feature0 = JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER;
        uTF8StreamJsonParser0.disable(jsonParser_Feature0);
        uTF8StreamJsonParser0.getFeatureMask();
    }

    @Test(timeout = 4000)
    public void test0913() throws Throwable {
        int[] intArray0 = new int[0];
        ParserBase.growArrayBy(intArray0, (-763));
    }

    @Test(timeout = 4000)
    public void test1014() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        PipedInputStream pipedInputStream0 = new PipedInputStream();
        IOContext iOContext0 = new IOContext(bufferRecycler0, pipedInputStream0, false);
        StringReader stringReader0 = new StringReader("(");
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        char[] charArray0 = new char[2];
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, 1, stringReader0, (ObjectCodec) null, charsToNameCanonicalizer0, charArray0, 0, 3, true);
        readerBasedJsonParser0.close();
    }

    @Test(timeout = 4000)
    public void test1115() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, true);
        StringReader stringReader0 = new StringReader("Illegal white space character (code 0x%s) as character #%d of 4-char base64 unit: can only used between units");
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, 2222, stringReader0, (ObjectCodec) null, charsToNameCanonicalizer0);
        char[] charArray0 = new char[8];
        ReaderBasedJsonParser readerBasedJsonParser1 = new ReaderBasedJsonParser(iOContext0, 1165, stringReader0, (ObjectCodec) null, charsToNameCanonicalizer0, charArray0, 55296, 206, true);
        readerBasedJsonParser1.close();
    }

    @Test(timeout = 4000)
    public void test1216() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, (Object) null, false);
        StringReader stringReader0 = new StringReader("odRuSp");
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        char[] charArray0 = new char[2];
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, 2, stringReader0, (ObjectCodec) null, charsToNameCanonicalizer0, charArray0, 33, 1, false);
        readerBasedJsonParser0.close();
        readerBasedJsonParser0.isClosed();
    }

    @Test(timeout = 4000)
    public void test1317() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, false);
        byte[] byteArray0 = new byte[0];
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0);
        DataInputStream dataInputStream0 = new DataInputStream(byteArrayInputStream0);
        ByteQuadsCanonicalizer byteQuadsCanonicalizer0 = ByteQuadsCanonicalizer.createRoot();
        UTF8DataInputJsonParser uTF8DataInputJsonParser0 = new UTF8DataInputJsonParser(iOContext0, 32, dataInputStream0, (ObjectCodec) null, byteQuadsCanonicalizer0, 0);
        JsonParser.Feature jsonParser_Feature0 = JsonParser.Feature.ALLOW_SINGLE_QUOTES;
        UTF8DataInputJsonParser uTF8DataInputJsonParser1 = (UTF8DataInputJsonParser) uTF8DataInputJsonParser0.disable(jsonParser_Feature0);
        uTF8DataInputJsonParser1.getFeatureMask();
    }

    @Test(timeout = 4000)
    public void test1318() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, false);
        byte[] byteArray0 = new byte[0];
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0);
        DataInputStream dataInputStream0 = new DataInputStream(byteArrayInputStream0);
        ByteQuadsCanonicalizer byteQuadsCanonicalizer0 = ByteQuadsCanonicalizer.createRoot();
        UTF8DataInputJsonParser uTF8DataInputJsonParser0 = new UTF8DataInputJsonParser(iOContext0, 32, dataInputStream0, (ObjectCodec) null, byteQuadsCanonicalizer0, 0);
        JsonParser.Feature jsonParser_Feature0 = JsonParser.Feature.ALLOW_SINGLE_QUOTES;
        UTF8DataInputJsonParser uTF8DataInputJsonParser1 = (UTF8DataInputJsonParser) uTF8DataInputJsonParser0.disable(jsonParser_Feature0);
        uTF8DataInputJsonParser1.getTokenLineNr();
    }

    @Test(timeout = 4000)
    public void test1419() throws Throwable {
        int[] intArray0 = ParserBase.growArrayBy((int[]) null, 0);
    }

    @Test(timeout = 4000)
    public void test1520() throws Throwable {
        int[] intArray0 = new int[3];
        int[] intArray1 = ParserBase.growArrayBy(intArray0, 2048);
    }

    @Test(timeout = 4000)
    public void test1621() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        byte[] byteArray0 = new byte[6];
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0);
        PushbackInputStream pushbackInputStream0 = new PushbackInputStream(byteArrayInputStream0);
        IOContext iOContext0 = new IOContext(bufferRecycler0, pushbackInputStream0, false);
        StringReader stringReader0 = new StringReader(">JaJ0Y3tB=>&|\"\"");
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, 3, stringReader0, (ObjectCodec) null, charsToNameCanonicalizer0);
        readerBasedJsonParser0.getDecimalValue();
    }

    @Test(timeout = 4000)
    public void test1722() throws Throwable {
        IOContext iOContext0 = new IOContext((BufferRecycler) null, (Object) null, false);
        FileDescriptor fileDescriptor0 = new FileDescriptor();
        MockFileInputStream mockFileInputStream0 = new MockFileInputStream(fileDescriptor0);
        DataInputStream dataInputStream0 = new DataInputStream(mockFileInputStream0);
        ByteQuadsCanonicalizer byteQuadsCanonicalizer0 = ByteQuadsCanonicalizer.createRoot();
        UTF8DataInputJsonParser uTF8DataInputJsonParser0 = new UTF8DataInputJsonParser(iOContext0, 4242, dataInputStream0, (ObjectCodec) null, byteQuadsCanonicalizer0, (-1194));
        uTF8DataInputJsonParser0.getBigIntegerValue();
    }

    @Test(timeout = 4000)
    public void test1823() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, true);
        ByteQuadsCanonicalizer byteQuadsCanonicalizer0 = ByteQuadsCanonicalizer.createRoot();
        UTF8StreamJsonParser uTF8StreamJsonParser0 = new UTF8StreamJsonParser(iOContext0, 3, (InputStream) null, (ObjectCodec) null, byteQuadsCanonicalizer0, (byte[]) null, 3490, 0, true);
        uTF8StreamJsonParser0.getLongValue();
    }

    @Test(timeout = 4000)
    public void test1924() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, true);
        Enumeration<InputStream> enumeration0 = (Enumeration<InputStream>) mock(Enumeration.class, new ViolatedAssumptionAnswer());
        doReturn(false).when(enumeration0).hasMoreElements();
        SequenceInputStream sequenceInputStream0 = new SequenceInputStream(enumeration0);
        ByteQuadsCanonicalizer byteQuadsCanonicalizer0 = ByteQuadsCanonicalizer.createRoot();
        byte[] byteArray0 = new byte[6];
        UTF8StreamJsonParser uTF8StreamJsonParser0 = new UTF8StreamJsonParser(iOContext0, 3, sequenceInputStream0, (ObjectCodec) null, byteQuadsCanonicalizer0, byteArray0, (byte) 0, (byte) 0, true);
        uTF8StreamJsonParser0.getIntValue();
    }

    @Test(timeout = 4000)
    public void test2025() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, true);
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        char[] charArray0 = new char[4];
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, 2, (Reader) null, (ObjectCodec) null, charsToNameCanonicalizer0, charArray0, (-155), 1, true);
        readerBasedJsonParser0.getNumberType();
    }

    @Test(timeout = 4000)
    public void test2126() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, true);
        Enumeration<InputStream> enumeration0 = (Enumeration<InputStream>) mock(Enumeration.class, new ViolatedAssumptionAnswer());
        doReturn(false).when(enumeration0).hasMoreElements();
        SequenceInputStream sequenceInputStream0 = new SequenceInputStream(enumeration0);
        ByteQuadsCanonicalizer byteQuadsCanonicalizer0 = ByteQuadsCanonicalizer.createRoot();
        byte[] byteArray0 = new byte[6];
        UTF8StreamJsonParser uTF8StreamJsonParser0 = new UTF8StreamJsonParser(iOContext0, 3, sequenceInputStream0, (ObjectCodec) null, byteQuadsCanonicalizer0, byteArray0, (byte) 0, (byte) 0, true);
        uTF8StreamJsonParser0.getNumberValue();
    }

    @Test(timeout = 4000)
    public void test2227() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, (Object) null, false);
        PipedInputStream pipedInputStream0 = new PipedInputStream(1);
        ByteQuadsCanonicalizer byteQuadsCanonicalizer0 = ByteQuadsCanonicalizer.createRoot();
        byte[] byteArray0 = new byte[1];
        UTF8StreamJsonParser uTF8StreamJsonParser0 = new UTF8StreamJsonParser(iOContext0, (-590), pipedInputStream0, (ObjectCodec) null, byteQuadsCanonicalizer0, byteArray0, (-1344), 3, true);
        boolean boolean0 = uTF8StreamJsonParser0.isNaN();
        uTF8StreamJsonParser0.getTokenLineNr();
    }

    @Test(timeout = 4000)
    public void test2228() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, (Object) null, false);
        PipedInputStream pipedInputStream0 = new PipedInputStream(1);
        ByteQuadsCanonicalizer byteQuadsCanonicalizer0 = ByteQuadsCanonicalizer.createRoot();
        byte[] byteArray0 = new byte[1];
        UTF8StreamJsonParser uTF8StreamJsonParser0 = new UTF8StreamJsonParser(iOContext0, (-590), pipedInputStream0, (ObjectCodec) null, byteQuadsCanonicalizer0, byteArray0, (-1344), 3, true);
        boolean boolean0 = uTF8StreamJsonParser0.isNaN();
    }

    @Test(timeout = 4000)
    public void test2329() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, false);
        StringReader stringReader0 = new StringReader("Illegal character '");
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, (-1585), stringReader0, (ObjectCodec) null, charsToNameCanonicalizer0);
        readerBasedJsonParser0._getByteArrayBuilder();
        readerBasedJsonParser0.getTokenLineNr();
    }

    @Test(timeout = 4000)
    public void test2430() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, true);
        StringReader stringReader0 = new StringReader("[%s: size=%d, hashSize=%d, %d/%d/%d/%d pri/sec/ter/spill (=%s), total:%d]");
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        char[] charArray0 = new char[1];
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, 0, stringReader0, (ObjectCodec) null, charsToNameCanonicalizer0, charArray0, 18, (-6416), true);
        int int0 = readerBasedJsonParser0.getTokenColumnNr();
    }

    @Test(timeout = 4000)
    public void test2431() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, true);
        StringReader stringReader0 = new StringReader("[%s: size=%d, hashSize=%d, %d/%d/%d/%d pri/sec/ter/spill (=%s), total:%d]");
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        char[] charArray0 = new char[1];
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, 0, stringReader0, (ObjectCodec) null, charsToNameCanonicalizer0, charArray0, 18, (-6416), true);
        int int0 = readerBasedJsonParser0.getTokenColumnNr();
        readerBasedJsonParser0.getTokenLineNr();
    }

    @Test(timeout = 4000)
    public void test2532() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, true);
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        char[] charArray0 = new char[4];
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, 2, (Reader) null, (ObjectCodec) null, charsToNameCanonicalizer0, charArray0, (-155), 1, true);
        boolean boolean0 = readerBasedJsonParser0.hasTextCharacters();
    }

    @Test(timeout = 4000)
    public void test2533() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, true);
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        char[] charArray0 = new char[4];
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, 2, (Reader) null, (ObjectCodec) null, charsToNameCanonicalizer0, charArray0, (-155), 1, true);
        boolean boolean0 = readerBasedJsonParser0.hasTextCharacters();
        readerBasedJsonParser0.getTokenLineNr();
    }

    @Test(timeout = 4000)
    public void test2634() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, true);
        ByteQuadsCanonicalizer byteQuadsCanonicalizer0 = ByteQuadsCanonicalizer.createRoot();
        UTF8StreamJsonParser uTF8StreamJsonParser0 = new UTF8StreamJsonParser(iOContext0, 3, (InputStream) null, (ObjectCodec) null, byteQuadsCanonicalizer0, (byte[]) null, 3490, 0, true);
        uTF8StreamJsonParser0.overrideCurrentName("[R=&6I oTi^~e_~");
        uTF8StreamJsonParser0.getTokenLineNr();
    }

    @Test(timeout = 4000)
    public void test2735() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, (Object) null, false);
        PipedInputStream pipedInputStream0 = new PipedInputStream(1);
        ByteQuadsCanonicalizer byteQuadsCanonicalizer0 = ByteQuadsCanonicalizer.createRoot();
        byte[] byteArray0 = new byte[1];
        UTF8StreamJsonParser uTF8StreamJsonParser0 = new UTF8StreamJsonParser(iOContext0, (-590), pipedInputStream0, (ObjectCodec) null, byteQuadsCanonicalizer0, byteArray0, (-1344), 3, true);
        uTF8StreamJsonParser0.getCurrentName();
        uTF8StreamJsonParser0.getTokenLineNr();
    }

    @Test(timeout = 4000)
    public void test2836() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, true);
        Enumeration<InputStream> enumeration0 = (Enumeration<InputStream>) mock(Enumeration.class, new ViolatedAssumptionAnswer());
        doReturn(false).when(enumeration0).hasMoreElements();
        SequenceInputStream sequenceInputStream0 = new SequenceInputStream(enumeration0);
        ByteQuadsCanonicalizer byteQuadsCanonicalizer0 = ByteQuadsCanonicalizer.createRoot();
        byte[] byteArray0 = new byte[6];
        UTF8StreamJsonParser uTF8StreamJsonParser0 = new UTF8StreamJsonParser(iOContext0, 3, sequenceInputStream0, (ObjectCodec) null, byteQuadsCanonicalizer0, byteArray0, (byte) 0, (byte) 0, true);
        uTF8StreamJsonParser0.overrideStdFeatures((byte) 0, 2105);
        uTF8StreamJsonParser0.getFeatureMask();
    }

    @Test(timeout = 4000)
    public void test2937() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, true);
        Enumeration<InputStream> enumeration0 = (Enumeration<InputStream>) mock(Enumeration.class, new ViolatedAssumptionAnswer());
        doReturn(false).when(enumeration0).hasMoreElements();
        SequenceInputStream sequenceInputStream0 = new SequenceInputStream(enumeration0);
        ByteQuadsCanonicalizer byteQuadsCanonicalizer0 = ByteQuadsCanonicalizer.createRoot();
        byte[] byteArray0 = new byte[6];
        UTF8StreamJsonParser uTF8StreamJsonParser0 = new UTF8StreamJsonParser(iOContext0, 3, sequenceInputStream0, (ObjectCodec) null, byteQuadsCanonicalizer0, byteArray0, (byte) 0, (byte) 0, true);
        JsonParser jsonParser0 = uTF8StreamJsonParser0.overrideStdFeatures((byte) 0, 2136);
        jsonParser0.getFeatureMask();
    }

    @Test(timeout = 4000)
    public void test3038() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, true);
        PipedInputStream pipedInputStream0 = new PipedInputStream();
        ByteQuadsCanonicalizer byteQuadsCanonicalizer0 = ByteQuadsCanonicalizer.createRoot();
        byte[] byteArray0 = new byte[6];
        UTF8StreamJsonParser uTF8StreamJsonParser0 = new UTF8StreamJsonParser(iOContext0, 0, pipedInputStream0, (ObjectCodec) null, byteQuadsCanonicalizer0, byteArray0, 9, (byte) (-30), true);
        JsonParser jsonParser0 = uTF8StreamJsonParser0.setFeatureMask((-1207));
        jsonParser0.getDoubleValue();
    }

    @Test(timeout = 4000)
    public void test3139() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, ")GF", true);
        StringReader stringReader0 = new StringReader("");
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, 1, stringReader0, (ObjectCodec) null, charsToNameCanonicalizer0);
        readerBasedJsonParser0.getFloatValue();
    }

    @Test(timeout = 4000)
    public void test3240() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        PipedInputStream pipedInputStream0 = new PipedInputStream();
        IOContext iOContext0 = new IOContext(bufferRecycler0, pipedInputStream0, false);
        StringReader stringReader0 = new StringReader("(");
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        char[] charArray0 = new char[2];
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, 1, stringReader0, (ObjectCodec) null, charsToNameCanonicalizer0, charArray0, 0, 3, true);
        readerBasedJsonParser0.setCurrentValue(iOContext0);
        readerBasedJsonParser0.getTokenLineNr();
    }

    @Test(timeout = 4000)
    public void test3341() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, (Object) null, false);
        PipedInputStream pipedInputStream0 = new PipedInputStream(1);
        ByteQuadsCanonicalizer byteQuadsCanonicalizer0 = ByteQuadsCanonicalizer.createRoot();
        byte[] byteArray0 = new byte[1];
        UTF8StreamJsonParser uTF8StreamJsonParser0 = new UTF8StreamJsonParser(iOContext0, (-590), pipedInputStream0, (ObjectCodec) null, byteQuadsCanonicalizer0, byteArray0, (-1344), 3, true);
        uTF8StreamJsonParser0.version();
        uTF8StreamJsonParser0.getTokenLineNr();
    }

    @Test(timeout = 4000)
    public void test3442() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, true);
        ByteQuadsCanonicalizer byteQuadsCanonicalizer0 = ByteQuadsCanonicalizer.createRoot();
        UTF8StreamJsonParser uTF8StreamJsonParser0 = new UTF8StreamJsonParser(iOContext0, 3, (InputStream) null, (ObjectCodec) null, byteQuadsCanonicalizer0, (byte[]) null, 3490, 0, true);
        long long0 = uTF8StreamJsonParser0.getTokenCharacterOffset();
        uTF8StreamJsonParser0.getTokenLineNr();
    }

    @Test(timeout = 4000)
    public void test3443() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, true);
        ByteQuadsCanonicalizer byteQuadsCanonicalizer0 = ByteQuadsCanonicalizer.createRoot();
        UTF8StreamJsonParser uTF8StreamJsonParser0 = new UTF8StreamJsonParser(iOContext0, 3, (InputStream) null, (ObjectCodec) null, byteQuadsCanonicalizer0, (byte[]) null, 3490, 0, true);
        long long0 = uTF8StreamJsonParser0.getTokenCharacterOffset();
    }

    @Test(timeout = 4000)
    public void test3544() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, true);
        Enumeration<InputStream> enumeration0 = (Enumeration<InputStream>) mock(Enumeration.class, new ViolatedAssumptionAnswer());
        doReturn(false).when(enumeration0).hasMoreElements();
        SequenceInputStream sequenceInputStream0 = new SequenceInputStream(enumeration0);
        ByteQuadsCanonicalizer byteQuadsCanonicalizer0 = ByteQuadsCanonicalizer.createRoot();
        byte[] byteArray0 = new byte[6];
        UTF8StreamJsonParser uTF8StreamJsonParser0 = new UTF8StreamJsonParser(iOContext0, 3, sequenceInputStream0, (ObjectCodec) null, byteQuadsCanonicalizer0, byteArray0, (byte) 0, (byte) 0, true);
        uTF8StreamJsonParser0.getCurrentValue();
        uTF8StreamJsonParser0.getTokenLineNr();
    }

    @Test(timeout = 4000)
    public void test3645() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, true);
        ByteQuadsCanonicalizer byteQuadsCanonicalizer0 = ByteQuadsCanonicalizer.createRoot();
        UTF8StreamJsonParser uTF8StreamJsonParser0 = new UTF8StreamJsonParser(iOContext0, 3, (InputStream) null, (ObjectCodec) null, byteQuadsCanonicalizer0, (byte[]) null, 3490, 0, true);
        uTF8StreamJsonParser0.nextFieldName();
        uTF8StreamJsonParser0.isClosed();
    }

    @Test(timeout = 4000)
    public void test3746() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, true);
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, (-5676), (Reader) null, (ObjectCodec) null, charsToNameCanonicalizer0);
        int int0 = readerBasedJsonParser0.getTokenLineNr();
    }

    @Test(timeout = 4000)
    public void test3847() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, false);
        StringReader stringReader0 = new StringReader("");
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, 2, stringReader0, (ObjectCodec) null, charsToNameCanonicalizer0);
        readerBasedJsonParser0.isClosed();
        readerBasedJsonParser0.getTokenLineNr();
    }

    @Test(timeout = 4000)
    public void test3948() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, false);
        StringReader stringReader0 = new StringReader("trE(NN%oC[S;");
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, 2, stringReader0, (ObjectCodec) null, charsToNameCanonicalizer0);
        readerBasedJsonParser0.getParsingContext();
        readerBasedJsonParser0.getTokenLineNr();
    }
}
