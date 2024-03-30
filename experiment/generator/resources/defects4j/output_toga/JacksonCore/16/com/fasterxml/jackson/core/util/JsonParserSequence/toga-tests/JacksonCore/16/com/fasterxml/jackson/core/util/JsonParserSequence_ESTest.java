/*
 * This file was automatically generated by EvoSuite
 * Sun Oct 15 19:04:01 GMT 2023
 */
package com.fasterxml.jackson.core.util;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.filter.FilteringParserDelegate;
import com.fasterxml.jackson.core.filter.TokenFilter;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.json.ReaderBasedJsonParser;
import com.fasterxml.jackson.core.json.UTF8DataInputJsonParser;
import com.fasterxml.jackson.core.json.UTF8StreamJsonParser;
import com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer;
import com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer;
import com.fasterxml.jackson.core.util.BufferRecycler;
import com.fasterxml.jackson.core.util.JsonParserDelegate;
import com.fasterxml.jackson.core.util.JsonParserSequence;
import com.fasterxml.jackson.core.util.RequestPayload;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.Reader;
import java.io.SequenceInputStream;
import java.io.StringReader;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.System;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.evosuite.runtime.testdata.EvoSuiteFile;
import org.evosuite.runtime.testdata.FileSystemHandling;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class JsonParserSequence_ESTest extends JsonParserSequence_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        byte[] byteArray0 = new byte[8];
        byteArray0[0] = (byte) 63;
        byteArray0[1] = (byte) (-103);
        byteArray0[2] = (byte) 111;
        byteArray0[3] = (byte) 116;
        byteArray0[4] = (byte) (-88);
        byteArray0[5] = (byte) 0;
        byteArray0[6] = (byte) 69;
        byteArray0[7] = (byte) 118;
        FileSystemHandling.appendDataToFile((EvoSuiteFile) null, byteArray0);
        System.setCurrentTimeMillis((-980L));
        System.setCurrentTimeMillis((-980L));
        System.setCurrentTimeMillis(25L);
        System.setCurrentTimeMillis((byte) (-88));
        System.setCurrentTimeMillis(1L);
        System.setCurrentTimeMillis((byte) 118);
        JsonParser[] jsonParserArray0 = new JsonParser[6];
        BufferRecycler bufferRecycler0 = new BufferRecycler((byte) 0, 46);
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, true);
        JsonEncoding jsonEncoding0 = JsonEncoding.UTF16_LE;
        IOContext iOContext1 = iOContext0.withEncoding(jsonEncoding0);
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0, (-2232), 3);
        DataInputStream dataInputStream0 = new DataInputStream(byteArrayInputStream0);
        StringReader stringReader0 = new StringReader("j0~<eDz");
        ObjectCodec objectCodec0 = mock(ObjectCodec.class, new ViolatedAssumptionAnswer());
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, (byte) (-88), stringReader0, objectCodec0, charsToNameCanonicalizer0);
        JsonParserSequence jsonParserSequence0 = JsonParserSequence.createFlattened(readerBasedJsonParser0, readerBasedJsonParser0);
        ObjectCodec objectCodec1 = jsonParserSequence0.getCodec();
        ByteQuadsCanonicalizer byteQuadsCanonicalizer0 = ByteQuadsCanonicalizer.createRoot();
        UTF8DataInputJsonParser uTF8DataInputJsonParser0 = new UTF8DataInputJsonParser(iOContext1, (byte) (-88), dataInputStream0, objectCodec1, byteQuadsCanonicalizer0, 224);
        jsonParserArray0[0] = (JsonParser) uTF8DataInputJsonParser0;
        JsonParser.Feature jsonParser_Feature0 = JsonParser.Feature.ALLOW_MISSING_VALUES;
        JsonParser jsonParser0 = jsonParserSequence0.configure(jsonParser_Feature0, false);
        jsonParserArray0[1] = jsonParser0;
        JsonParser jsonParser1 = jsonParserSequence0.overrideStdFeatures((byte) 69, 4096);
        jsonParserArray0[2] = jsonParser1;
        JsonParser jsonParser2 = readerBasedJsonParser0.skipChildren();
        jsonParserArray0[3] = jsonParser2;
        JsonParser jsonParser3 = jsonParserSequence0.enable(jsonParser_Feature0);
        jsonParserArray0[4] = jsonParser3;
        DataInputStream dataInputStream1 = new DataInputStream(dataInputStream0);
        UTF8DataInputJsonParser uTF8DataInputJsonParser1 = new UTF8DataInputJsonParser(iOContext0, (byte) 118, dataInputStream1, objectCodec1, byteQuadsCanonicalizer0, 1);
        jsonParserArray0[5] = (JsonParser) uTF8DataInputJsonParser1;
        JsonParserSequence jsonParserSequence1 = new JsonParserSequence(jsonParserArray0);
        jsonParserSequence1.nextToken();
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, true);
        StringReader stringReader0 = new StringReader("/");
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        char[] charArray0 = new char[1];
        charArray0[0] = '3';
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, 3, stringReader0, (ObjectCodec) null, charsToNameCanonicalizer0, charArray0, 3, 3, true);
        JsonParser.Feature jsonParser_Feature0 = JsonParser.Feature.STRICT_DUPLICATE_DETECTION;
        JsonParser jsonParser0 = readerBasedJsonParser0.enable(jsonParser_Feature0);
        JsonParserDelegate jsonParserDelegate0 = new JsonParserDelegate(jsonParser0);
        JsonParserSequence jsonParserSequence0 = JsonParserSequence.createFlattened(jsonParserDelegate0, readerBasedJsonParser0);
        jsonParserSequence0.nextToken();
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        JsonParser[] jsonParserArray0 = new JsonParser[1];
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, (Object) null, false);
        StringReader stringReader0 = new StringReader("Qm_^`doOo");
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, 0, stringReader0, (ObjectCodec) null, charsToNameCanonicalizer0);
        JsonParserSequence jsonParserSequence0 = JsonParserSequence.createFlattened(readerBasedJsonParser0, readerBasedJsonParser0);
        JsonParserSequence jsonParserSequence1 = JsonParserSequence.createFlattened(jsonParserSequence0, jsonParserSequence0);
        jsonParserSequence1._nextParser = (-1559);
        jsonParserArray0[0] = (JsonParser) jsonParserSequence1;
        JsonParserSequence jsonParserSequence2 = new JsonParserSequence(jsonParserArray0);
        jsonParserSequence2.close();
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        JsonParser[] jsonParserArray0 = new JsonParser[1];
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, (Object) null, false);
        StringReader stringReader0 = new StringReader("Qm_^`doOo");
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, 0, stringReader0, (ObjectCodec) null, charsToNameCanonicalizer0);
        JsonParserSequence jsonParserSequence0 = JsonParserSequence.createFlattened(readerBasedJsonParser0, readerBasedJsonParser0);
        JsonParserSequence jsonParserSequence1 = JsonParserSequence.createFlattened(jsonParserSequence0, jsonParserSequence0);
        jsonParserArray0[0] = (JsonParser) jsonParserSequence1;
        stringReader0.reset();
        jsonParserSequence1._nextParser = 81;
        JsonParserSequence jsonParserSequence2 = new JsonParserSequence(jsonParserArray0);
        jsonParserSequence2.close();
        jsonParserSequence0.nextToken();
        JsonParserSequence.createFlattened(jsonParserSequence2, jsonParserArray0[0]);
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        byte[] byteArray0 = new byte[7];
        byteArray0[0] = (byte) (-86);
        byteArray0[1] = (byte) 1;
        byteArray0[2] = (byte) 75;
        byteArray0[3] = (byte) 25;
        byteArray0[4] = (byte) 50;
        byteArray0[5] = (byte) (-1);
        byteArray0[6] = (byte) (-122);
        FileSystemHandling.appendDataToFile((EvoSuiteFile) null, byteArray0);
        JsonParser[] jsonParserArray0 = new JsonParser[1];
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, (Object) null, false);
        StringReader stringReader0 = new StringReader("Qm_^`doOo");
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, 0, stringReader0, (ObjectCodec) null, charsToNameCanonicalizer0);
        JsonParserSequence jsonParserSequence0 = JsonParserSequence.createFlattened(readerBasedJsonParser0, readerBasedJsonParser0);
        JsonParserSequence jsonParserSequence1 = (JsonParserSequence) jsonParserSequence0.skipChildren();
        jsonParserSequence1._nextParser = (-2023);
        jsonParserArray0[0] = (JsonParser) jsonParserSequence1;
        JsonParserSequence.createFlattened(jsonParserSequence0, jsonParserArray0[0]);
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        System.setCurrentTimeMillis((-2288L));
        TokenFilter tokenFilter0 = TokenFilter.INCLUDE_ALL;
        FilteringParserDelegate filteringParserDelegate0 = new FilteringParserDelegate((JsonParser) null, tokenFilter0, false, false);
        JsonParserSequence jsonParserSequence0 = JsonParserSequence.createFlattened(filteringParserDelegate0, (JsonParser) null);
        jsonParserSequence0._nextParser = 4710;
        JsonParserSequence jsonParserSequence1 = JsonParserSequence.createFlattened(jsonParserSequence0, filteringParserDelegate0);
        JsonParserSequence jsonParserSequence2 = JsonParserSequence.createFlattened(filteringParserDelegate0, jsonParserSequence1);
        jsonParserSequence1.containedParsersCount();
    }

    @Test(timeout = 4000)
    public void test056() throws Throwable {
        System.setCurrentTimeMillis((-2288L));
        TokenFilter tokenFilter0 = TokenFilter.INCLUDE_ALL;
        FilteringParserDelegate filteringParserDelegate0 = new FilteringParserDelegate((JsonParser) null, tokenFilter0, false, false);
        JsonParserSequence jsonParserSequence0 = JsonParserSequence.createFlattened(filteringParserDelegate0, (JsonParser) null);
        jsonParserSequence0._nextParser = 4710;
        JsonParserSequence jsonParserSequence1 = JsonParserSequence.createFlattened(jsonParserSequence0, filteringParserDelegate0);
        JsonParserSequence jsonParserSequence2 = JsonParserSequence.createFlattened(filteringParserDelegate0, jsonParserSequence1);
        jsonParserSequence2.containedParsersCount();
    }

    @Test(timeout = 4000)
    public void test067() throws Throwable {
        JsonParser[] jsonParserArray0 = new JsonParser[1];
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, (Object) null, false);
        StringReader stringReader0 = new StringReader("Qm_^`doOo");
        stringReader0.reset();
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, 0, stringReader0, (ObjectCodec) null, charsToNameCanonicalizer0);
        JsonParserSequence jsonParserSequence0 = JsonParserSequence.createFlattened(readerBasedJsonParser0, readerBasedJsonParser0);
        JsonParser jsonParser0 = jsonParserSequence0.skipChildren();
        jsonParserArray0[0] = jsonParser0;
        JsonParserSequence jsonParserSequence1 = JsonParserSequence.createFlattened(jsonParserSequence0, jsonParserArray0[0]);
        jsonParserSequence1.close();
        jsonParserSequence0.nextToken();
        JsonParserSequence jsonParserSequence2 = JsonParserSequence.createFlattened(jsonParserSequence0, jsonParserSequence1);
        jsonParserSequence1.containedParsersCount();
    }

    @Test(timeout = 4000)
    public void test068() throws Throwable {
        JsonParser[] jsonParserArray0 = new JsonParser[1];
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, (Object) null, false);
        StringReader stringReader0 = new StringReader("Qm_^`doOo");
        stringReader0.reset();
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, 0, stringReader0, (ObjectCodec) null, charsToNameCanonicalizer0);
        JsonParserSequence jsonParserSequence0 = JsonParserSequence.createFlattened(readerBasedJsonParser0, readerBasedJsonParser0);
        JsonParser jsonParser0 = jsonParserSequence0.skipChildren();
        jsonParserArray0[0] = jsonParser0;
        JsonParserSequence jsonParserSequence1 = JsonParserSequence.createFlattened(jsonParserSequence0, jsonParserArray0[0]);
        jsonParserSequence1.close();
        jsonParserSequence0.nextToken();
        JsonParserSequence jsonParserSequence2 = JsonParserSequence.createFlattened(jsonParserSequence0, jsonParserSequence1);
        jsonParserSequence2.containedParsersCount();
    }

    @Test(timeout = 4000)
    public void test079() throws Throwable {
        JsonParser[] jsonParserArray0 = new JsonParser[1];
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, (Object) null, false);
        StringReader stringReader0 = new StringReader("Qm_^`doOo");
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, 0, stringReader0, (ObjectCodec) null, charsToNameCanonicalizer0);
        JsonParserSequence jsonParserSequence0 = JsonParserSequence.createFlattened(readerBasedJsonParser0, readerBasedJsonParser0);
        JsonParser jsonParser0 = jsonParserSequence0.skipChildren();
        jsonParserArray0[0] = jsonParser0;
        JsonParserSequence jsonParserSequence1 = new JsonParserSequence(jsonParserArray0);
        jsonParserSequence1.close();
        jsonParserSequence1.getFormatFeatures();
        assertEquals(0, jsonParserSequence1.getFormatFeatures());
    }

    @Test(timeout = 4000)
    public void test0810() throws Throwable {
        TokenFilter tokenFilter0 = TokenFilter.INCLUDE_ALL;
        TokenFilter tokenFilter1 = tokenFilter0.filterStartArray();
        FilteringParserDelegate filteringParserDelegate0 = new FilteringParserDelegate((JsonParser) null, tokenFilter1, false, false);
        JsonParserSequence jsonParserSequence0 = JsonParserSequence.createFlattened(filteringParserDelegate0, filteringParserDelegate0);
        boolean boolean0 = jsonParserSequence0.switchToNext();
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test0811() throws Throwable {
        TokenFilter tokenFilter0 = TokenFilter.INCLUDE_ALL;
        TokenFilter tokenFilter1 = tokenFilter0.filterStartArray();
        FilteringParserDelegate filteringParserDelegate0 = new FilteringParserDelegate((JsonParser) null, tokenFilter1, false, false);
        JsonParserSequence jsonParserSequence0 = JsonParserSequence.createFlattened(filteringParserDelegate0, filteringParserDelegate0);
        boolean boolean0 = jsonParserSequence0.switchToNext();
        boolean boolean1 = jsonParserSequence0.switchToNext();
    }

    @Test(timeout = 4000)
    public void test0912() throws Throwable {
        LinkedList<JsonParser> linkedList0 = new LinkedList<JsonParser>();
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, (Object) null, false);
        StringReader stringReader0 = new StringReader("k-T<%");
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        CharsToNameCanonicalizer charsToNameCanonicalizer1 = charsToNameCanonicalizer0.makeChild(20);
        char[] charArray0 = new char[0];
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, (-1658), stringReader0, (ObjectCodec) null, charsToNameCanonicalizer1, charArray0, 1, 1, false);
        JsonParserSequence jsonParserSequence0 = JsonParserSequence.createFlattened(readerBasedJsonParser0, (JsonParser) null);
        jsonParserSequence0.nextToken();
    }

    @Test(timeout = 4000)
    public void test1013() throws Throwable {
        System.setCurrentTimeMillis(1663L);
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, true);
        Enumeration<InputStream> enumeration0 = (Enumeration<InputStream>) mock(Enumeration.class, new ViolatedAssumptionAnswer());
        doReturn(false, false).when(enumeration0).hasMoreElements();
        SequenceInputStream sequenceInputStream0 = new SequenceInputStream(enumeration0);
        BufferedInputStream bufferedInputStream0 = new BufferedInputStream(sequenceInputStream0);
        byte[] byteArray0 = new byte[4];
        byteArray0[0] = (byte) (-1);
        byteArray0[1] = (byte) 80;
        byteArray0[2] = (byte) 1;
        byteArray0[3] = (byte) 25;
        bufferedInputStream0.read(byteArray0);
        ByteQuadsCanonicalizer byteQuadsCanonicalizer0 = ByteQuadsCanonicalizer.createRoot();
        byte[] byteArray1 = new byte[0];
        UTF8StreamJsonParser uTF8StreamJsonParser0 = new UTF8StreamJsonParser(iOContext0, (-791), bufferedInputStream0, (ObjectCodec) null, byteQuadsCanonicalizer0, byteArray1, 0, 2, true);
        JsonParserSequence jsonParserSequence0 = JsonParserSequence.createFlattened(uTF8StreamJsonParser0, uTF8StreamJsonParser0);
        jsonParserSequence0.switchToNext();
        jsonParserSequence0.containedParsersCount();
        jsonParserSequence0.containedParsersCount();
        jsonParserSequence0.getCurrentToken();
        LinkedList<JsonParser> linkedList0 = new LinkedList<JsonParser>();
        linkedList0.offer(jsonParserSequence0);
        linkedList0.add((JsonParser) uTF8StreamJsonParser0);
        jsonParserSequence0.addFlattenedActiveParsers(linkedList0);
        jsonParserSequence0.containedParsersCount();
        System.setCurrentTimeMillis(2);
        jsonParserSequence0.close();
    }

    @Test(timeout = 4000)
    public void test1114() throws Throwable {
        OutputStream outputStream0 = null;
        JsonParser[] jsonParserArray0 = new JsonParser[4];
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, (Object) null, false);
        StringReader stringReader0 = new StringReader("com.fasterxml.jackson.core.util.JsonParserSequence");
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        char[] charArray0 = new char[8];
        charArray0[0] = '}';
        charArray0[1] = '9';
        charArray0[2] = 'T';
        charArray0[3] = 'i';
        charArray0[4] = '3';
        charArray0[5] = '+';
        charArray0[6] = '';
        charArray0[7] = 'R';
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, 0, stringReader0, (ObjectCodec) null, charsToNameCanonicalizer0, charArray0, 1, 1, false);
        JsonParser.Feature jsonParser_Feature0 = JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS;
        JsonParser jsonParser0 = readerBasedJsonParser0.enable(jsonParser_Feature0);
        jsonParserArray0[0] = jsonParser0;
        TokenFilter tokenFilter0 = TokenFilter.INCLUDE_ALL;
        TokenFilter tokenFilter1 = tokenFilter0.filterStartObject();
        TokenFilter tokenFilter2 = tokenFilter1.includeRootValue(0);
        FilteringParserDelegate filteringParserDelegate0 = new FilteringParserDelegate(jsonParser0, tokenFilter2, false, false);
        jsonParserArray0[1] = (JsonParser) filteringParserDelegate0;
        JsonParser.Feature jsonParser_Feature1 = JsonParser.Feature.AUTO_CLOSE_SOURCE;
        JsonParser jsonParser1 = readerBasedJsonParser0.disable(jsonParser_Feature1);
        jsonParserArray0[2] = jsonParser1;
        JsonParser jsonParser2 = readerBasedJsonParser0.overrideStdFeatures((-2326), 50);
        jsonParserArray0[3] = jsonParser2;
        JsonParserSequence jsonParserSequence0 = new JsonParserSequence(jsonParserArray0);
        jsonParserSequence0.nextToken();
    }

    @Test(timeout = 4000)
    public void test1215() throws Throwable {
        JsonParser[] jsonParserArray0 = new JsonParser[1];
        JsonParserSequence jsonParserSequence0 = new JsonParserSequence(jsonParserArray0);
        LinkedList<JsonParser> linkedList0 = new LinkedList<JsonParser>();
        jsonParserSequence0.nextToken();
    }

    @Test(timeout = 4000)
    public void test1316() throws Throwable {
        JsonParserSequence jsonParserSequence0 = JsonParserSequence.createFlattened((JsonParser) null, (JsonParser) null);
        Class<ReaderBasedJsonParser> class0 = ReaderBasedJsonParser.class;
        RequestPayload requestPayload0 = null;
        jsonParserSequence0.nextIntValue((-1134));
    }

    @Test(timeout = 4000)
    public void test1417() throws Throwable {
        JsonParser jsonParser0 = null;
        JsonParserSequence jsonParserSequence0 = JsonParserSequence.createFlattened((JsonParser) null, (JsonParser) null);
        Class<FilteringParserDelegate> class0 = FilteringParserDelegate.class;
        jsonParserSequence0.addFlattenedActiveParsers((List<JsonParser>) null);
    }

    @Test(timeout = 4000)
    public void test1518() throws Throwable {
        JsonParser jsonParser0 = null;
        JsonParserSequence jsonParserSequence0 = JsonParserSequence.createFlattened((JsonParser) null, (JsonParser) null);
        jsonParserSequence0.close();
    }

    @Test(timeout = 4000)
    public void test1619() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, "guL5pb10+^?Ov]u\">", true);
        JsonEncoding jsonEncoding0 = JsonEncoding.UTF32_LE;
        IOContext iOContext1 = iOContext0.withEncoding(jsonEncoding0);
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        CharsToNameCanonicalizer.createRoot();
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext1, 266, (Reader) null, (ObjectCodec) null, charsToNameCanonicalizer0);
        JsonParserSequence jsonParserSequence0 = JsonParserSequence.createFlattened(readerBasedJsonParser0, readerBasedJsonParser0);
        LinkedList<JsonParser> linkedList0 = new LinkedList<JsonParser>();
        FilteringParserDelegate[] filteringParserDelegateArray0 = new FilteringParserDelegate[6];
        TokenFilter tokenFilter0 = TokenFilter.INCLUDE_ALL;
        TokenFilter tokenFilter1 = tokenFilter0.includeElement(1162);
        TokenFilter tokenFilter2 = tokenFilter1.filterStartArray();
        TokenFilter tokenFilter3 = tokenFilter2.includeRootValue(1569);
        FilteringParserDelegate filteringParserDelegate0 = new FilteringParserDelegate(readerBasedJsonParser0, tokenFilter3, false, true);
        filteringParserDelegateArray0[0] = filteringParserDelegate0;
        FilteringParserDelegate filteringParserDelegate1 = new FilteringParserDelegate(readerBasedJsonParser0, tokenFilter1, true, false);
        filteringParserDelegateArray0[1] = filteringParserDelegate1;
        FilteringParserDelegate filteringParserDelegate2 = new FilteringParserDelegate(filteringParserDelegate0, tokenFilter3, true, false);
        filteringParserDelegateArray0[2] = filteringParserDelegate2;
        FilteringParserDelegate filteringParserDelegate3 = new FilteringParserDelegate(filteringParserDelegate0, tokenFilter3, true, true);
        filteringParserDelegateArray0[3] = filteringParserDelegate3;
        FilteringParserDelegate filteringParserDelegate4 = new FilteringParserDelegate(filteringParserDelegate2, tokenFilter3, false, true);
        filteringParserDelegateArray0[4] = filteringParserDelegate4;
        FilteringParserDelegate filteringParserDelegate5 = new FilteringParserDelegate(filteringParserDelegate0, tokenFilter1, false, false);
        filteringParserDelegateArray0[5] = filteringParserDelegate5;
        linkedList0.toArray(filteringParserDelegateArray0);
        jsonParserSequence0.addFlattenedActiveParsers(linkedList0);
        linkedList0.size();
    }

    @Test(timeout = 4000)
    public void test1720() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler(106, 93);
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, false);
        PipedInputStream pipedInputStream0 = new PipedInputStream();
        JsonParser[] jsonParserArray0 = new JsonParser[1];
        ObjectCodec objectCodec0 = mock(ObjectCodec.class, new ViolatedAssumptionAnswer());
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        CharsToNameCanonicalizer charsToNameCanonicalizer1 = charsToNameCanonicalizer0.makeChild(3044);
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, 0, (Reader) null, objectCodec0, charsToNameCanonicalizer1);
        JsonParser jsonParser0 = readerBasedJsonParser0.overrideStdFeatures((-3247), 0);
        jsonParserArray0[0] = jsonParser0;
        JsonParserSequence jsonParserSequence0 = new JsonParserSequence(jsonParserArray0);
        ObjectCodec objectCodec1 = jsonParserSequence0.getCodec();
        ByteQuadsCanonicalizer byteQuadsCanonicalizer0 = ByteQuadsCanonicalizer.createRoot();
        byte[] byteArray0 = new byte[9];
        byteArray0[0] = (byte) 2;
        byteArray0[1] = (byte) 0;
        byteArray0[2] = (byte) (-88);
        byteArray0[3] = (byte) 45;
        byteArray0[4] = (byte) 0;
        byteArray0[5] = (byte) (-27);
        byteArray0[6] = (byte) 0;
        byteArray0[7] = (byte) 63;
        byteArray0[8] = (byte) (-1);
        UTF8StreamJsonParser uTF8StreamJsonParser0 = new UTF8StreamJsonParser(iOContext0, 0, pipedInputStream0, objectCodec1, byteQuadsCanonicalizer0, byteArray0, 106, 106, false);
        JsonParserSequence jsonParserSequence1 = JsonParserSequence.createFlattened(uTF8StreamJsonParser0, uTF8StreamJsonParser0);
        LinkedList<JsonParser> linkedList0 = new LinkedList<JsonParser>();
        jsonParserSequence1.addFlattenedActiveParsers(linkedList0);
        linkedList0.size();
    }

    @Test(timeout = 4000)
    public void test1821() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, true);
        StringReader stringReader0 = new StringReader("Js#");
        JsonParser[] jsonParserArray0 = new JsonParser[3];
        ObjectCodec objectCodec0 = mock(ObjectCodec.class, new ViolatedAssumptionAnswer());
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        CharsToNameCanonicalizer charsToNameCanonicalizer1 = charsToNameCanonicalizer0.makeChild(0);
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, (-1391), stringReader0, objectCodec0, charsToNameCanonicalizer1, (char[]) null, 33, 53, true);
        TokenFilter tokenFilter0 = TokenFilter.INCLUDE_ALL;
        TokenFilter tokenFilter1 = tokenFilter0.filterStartArray();
        FilteringParserDelegate filteringParserDelegate0 = new FilteringParserDelegate(readerBasedJsonParser0, tokenFilter1, true, true);
        jsonParserArray0[0] = (JsonParser) filteringParserDelegate0;
        ObjectCodec objectCodec1 = mock(ObjectCodec.class, new ViolatedAssumptionAnswer());
        ReaderBasedJsonParser readerBasedJsonParser1 = new ReaderBasedJsonParser(iOContext0, 3, stringReader0, objectCodec1, charsToNameCanonicalizer0, (char[]) null, 45, 53, true);
        jsonParserArray0[1] = (JsonParser) readerBasedJsonParser1;
        byte[] byteArray0 = new byte[3];
        byteArray0[0] = (byte) 45;
        byteArray0[1] = (byte) 0;
        byteArray0[2] = (byte) 0;
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0);
        DataInputStream dataInputStream0 = new DataInputStream(byteArrayInputStream0);
        ObjectCodec objectCodec2 = mock(ObjectCodec.class, new ViolatedAssumptionAnswer());
        ByteQuadsCanonicalizer byteQuadsCanonicalizer0 = ByteQuadsCanonicalizer.createRoot();
        ByteQuadsCanonicalizer byteQuadsCanonicalizer1 = byteQuadsCanonicalizer0.makeChild(1);
        UTF8DataInputJsonParser uTF8DataInputJsonParser0 = new UTF8DataInputJsonParser(iOContext0, 0, dataInputStream0, objectCodec2, byteQuadsCanonicalizer1, 3);
        jsonParserArray0[2] = (JsonParser) uTF8DataInputJsonParser0;
        JsonParserSequence jsonParserSequence0 = new JsonParserSequence(jsonParserArray0);
        ObjectCodec objectCodec3 = jsonParserSequence0.getCodec();
        CharsToNameCanonicalizer charsToNameCanonicalizer2 = CharsToNameCanonicalizer.createRoot();
        ReaderBasedJsonParser readerBasedJsonParser2 = new ReaderBasedJsonParser(iOContext0, (-2810), stringReader0, objectCodec3, charsToNameCanonicalizer2);
        JsonParser.Feature jsonParser_Feature0 = JsonParser.Feature.ALLOW_YAML_COMMENTS;
        JsonParser jsonParser0 = readerBasedJsonParser2.disable(jsonParser_Feature0);
        JsonParserSequence jsonParserSequence1 = JsonParserSequence.createFlattened(jsonParser0, readerBasedJsonParser0);
        JsonParserSequence jsonParserSequence2 = JsonParserSequence.createFlattened(jsonParserSequence1, readerBasedJsonParser1);
        jsonParserSequence1.getFeatureMask();
    }

    @Test(timeout = 4000)
    public void test1822() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, true);
        StringReader stringReader0 = new StringReader("Js#");
        JsonParser[] jsonParserArray0 = new JsonParser[3];
        ObjectCodec objectCodec0 = mock(ObjectCodec.class, new ViolatedAssumptionAnswer());
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        CharsToNameCanonicalizer charsToNameCanonicalizer1 = charsToNameCanonicalizer0.makeChild(0);
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, (-1391), stringReader0, objectCodec0, charsToNameCanonicalizer1, (char[]) null, 33, 53, true);
        TokenFilter tokenFilter0 = TokenFilter.INCLUDE_ALL;
        TokenFilter tokenFilter1 = tokenFilter0.filterStartArray();
        FilteringParserDelegate filteringParserDelegate0 = new FilteringParserDelegate(readerBasedJsonParser0, tokenFilter1, true, true);
        jsonParserArray0[0] = (JsonParser) filteringParserDelegate0;
        ObjectCodec objectCodec1 = mock(ObjectCodec.class, new ViolatedAssumptionAnswer());
        ReaderBasedJsonParser readerBasedJsonParser1 = new ReaderBasedJsonParser(iOContext0, 3, stringReader0, objectCodec1, charsToNameCanonicalizer0, (char[]) null, 45, 53, true);
        jsonParserArray0[1] = (JsonParser) readerBasedJsonParser1;
        byte[] byteArray0 = new byte[3];
        byteArray0[0] = (byte) 45;
        byteArray0[1] = (byte) 0;
        byteArray0[2] = (byte) 0;
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0);
        DataInputStream dataInputStream0 = new DataInputStream(byteArrayInputStream0);
        ObjectCodec objectCodec2 = mock(ObjectCodec.class, new ViolatedAssumptionAnswer());
        ByteQuadsCanonicalizer byteQuadsCanonicalizer0 = ByteQuadsCanonicalizer.createRoot();
        ByteQuadsCanonicalizer byteQuadsCanonicalizer1 = byteQuadsCanonicalizer0.makeChild(1);
        UTF8DataInputJsonParser uTF8DataInputJsonParser0 = new UTF8DataInputJsonParser(iOContext0, 0, dataInputStream0, objectCodec2, byteQuadsCanonicalizer1, 3);
        jsonParserArray0[2] = (JsonParser) uTF8DataInputJsonParser0;
        JsonParserSequence jsonParserSequence0 = new JsonParserSequence(jsonParserArray0);
        ObjectCodec objectCodec3 = jsonParserSequence0.getCodec();
        CharsToNameCanonicalizer charsToNameCanonicalizer2 = CharsToNameCanonicalizer.createRoot();
        ReaderBasedJsonParser readerBasedJsonParser2 = new ReaderBasedJsonParser(iOContext0, (-2810), stringReader0, objectCodec3, charsToNameCanonicalizer2);
        JsonParser.Feature jsonParser_Feature0 = JsonParser.Feature.ALLOW_YAML_COMMENTS;
        JsonParser jsonParser0 = readerBasedJsonParser2.disable(jsonParser_Feature0);
        JsonParserSequence jsonParserSequence1 = JsonParserSequence.createFlattened(jsonParser0, readerBasedJsonParser0);
        JsonParserSequence jsonParserSequence2 = JsonParserSequence.createFlattened(jsonParserSequence1, readerBasedJsonParser1);
        jsonParserSequence2.containedParsersCount();
    }

    @Test(timeout = 4000)
    public void test1923() throws Throwable {
        JsonParser[] jsonParserArray0 = new JsonParser[5];
        int int0 = 0;
        BufferRecycler bufferRecycler0 = new BufferRecycler(0, 0);
        boolean boolean0 = true;
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, true);
        JsonEncoding jsonEncoding0 = JsonEncoding.UTF16_BE;
        iOContext0.withEncoding(jsonEncoding0);
        Reader reader0 = null;
        JsonParserSequence jsonParserSequence0 = new JsonParserSequence(jsonParserArray0);
        JsonParserSequence jsonParserSequence1 = JsonParserSequence.createFlattened(jsonParserSequence0, jsonParserSequence0);
        jsonParserSequence1.getCodec();
    }

    @Test(timeout = 4000)
    public void test2024() throws Throwable {
        JsonParser[] jsonParserArray0 = new JsonParser[1];
        JsonParserSequence jsonParserSequence0 = new JsonParserSequence(jsonParserArray0);
        FilteringParserDelegate filteringParserDelegate0 = new FilteringParserDelegate(jsonParserSequence0, (TokenFilter) null, false, false);
        JsonParserSequence jsonParserSequence1 = JsonParserSequence.createFlattened(filteringParserDelegate0, jsonParserSequence0);
        JsonParser jsonParser0 = jsonParserSequence1.skipChildren();
        jsonParserArray0[0] = jsonParser0;
        JsonParserSequence jsonParserSequence2 = new JsonParserSequence(jsonParserArray0);
        jsonParserSequence2.equals((Object) jsonParserSequence0);
    }

    @Test(timeout = 4000)
    public void test2125() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, false);
        int int0 = 500;
        StringReader stringReader0 = new StringReader("i_~,!i");
        JsonParserSequence jsonParserSequence0 = null;
        jsonParserSequence0 = new JsonParserSequence((JsonParser[]) null);
    }

    @Test(timeout = 4000)
    public void test2226() throws Throwable {
        JsonParser[] jsonParserArray0 = new JsonParser[0];
        JsonParserSequence jsonParserSequence0 = null;
        jsonParserSequence0 = new JsonParserSequence(jsonParserArray0);
    }

    @Test(timeout = 4000)
    public void test2327() throws Throwable {
        JsonParserSequence jsonParserSequence0 = JsonParserSequence.createFlattened((JsonParser) null, (JsonParser) null);
        int int0 = jsonParserSequence0.containedParsersCount();
    }

    @Test(timeout = 4000)
    public void test2428() throws Throwable {
        JsonParser[] jsonParserArray0 = new JsonParser[1];
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, (Object) null, false);
        StringReader stringReader0 = new StringReader("Qm_^`doOo");
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, 9, stringReader0, (ObjectCodec) null, charsToNameCanonicalizer0);
        JsonParserSequence jsonParserSequence0 = JsonParserSequence.createFlattened(readerBasedJsonParser0, readerBasedJsonParser0);
        JsonParserSequence jsonParserSequence1 = JsonParserSequence.createFlattened(jsonParserSequence0, jsonParserSequence0);
        jsonParserArray0[0] = (JsonParser) jsonParserSequence0;
        JsonParserSequence jsonParserSequence2 = new JsonParserSequence(jsonParserArray0);
        jsonParserSequence2.close();
        jsonParserSequence0.nextToken();
        JsonParserSequence.createFlattened(jsonParserSequence2, readerBasedJsonParser0);
        JsonParserSequence jsonParserSequence3 = JsonParserSequence.createFlattened(readerBasedJsonParser0, jsonParserSequence1);
        jsonParserSequence3.nextToken();
        int int0 = jsonParserSequence2.containedParsersCount();
    }
}
