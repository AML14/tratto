/*
 * This file was automatically generated by EvoSuite
 * Mon Nov 20 03:10:18 GMT 2023
 */
package com.fasterxml.jackson.core.base;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.base.ParserMinimalBase;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.json.ReaderBasedJsonParser;
import com.fasterxml.jackson.core.json.UTF8DataInputJsonParser;
import com.fasterxml.jackson.core.json.UTF8StreamJsonParser;
import com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer;
import com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer;
import com.fasterxml.jackson.core.util.BufferRecycler;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.StringReader;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class ParserMinimalBase_ESTest extends ParserMinimalBase_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, true);
        StringReader stringReader0 = new StringReader("-03b|_l5qb");
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, 33, stringReader0, (ObjectCodec) null, charsToNameCanonicalizer0);
        readerBasedJsonParser0.nextValue();
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, true);
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        StringReader stringReader0 = new StringReader("3noDLD3NO`::Yb.nOd");
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, 1, stringReader0, (ObjectCodec) null, charsToNameCanonicalizer0);
        readerBasedJsonParser0.nextValue();
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, false);
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        StringReader stringReader0 = new StringReader("");
        char[] charArray0 = new char[0];
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, 1, stringReader0, (ObjectCodec) null, charsToNameCanonicalizer0, charArray0, 3399, (-2678), false);
        readerBasedJsonParser0.getLastClearedToken();
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, true);
        StringReader stringReader0 = new StringReader("u3|2|");
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, 0, stringReader0, (ObjectCodec) null, charsToNameCanonicalizer0);
        readerBasedJsonParser0.getBooleanValue();
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, true);
        byte[] byteArray0 = iOContext0.allocReadIOBuffer();
        ByteQuadsCanonicalizer byteQuadsCanonicalizer0 = ByteQuadsCanonicalizer.createRoot();
        UTF8StreamJsonParser uTF8StreamJsonParser0 = new UTF8StreamJsonParser(iOContext0, 0, (InputStream) null, (ObjectCodec) null, byteQuadsCanonicalizer0, byteArray0, 15, 3930, true);
        uTF8StreamJsonParser0.nextFieldName();
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, false);
        StringReader stringReader0 = new StringReader("Broken surrogate pair: first char 0x");
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, 2, stringReader0, (ObjectCodec) null, charsToNameCanonicalizer0);
        readerBasedJsonParser0.nextValue();
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        Float float0 = new Float((float) 1);
        IOContext iOContext0 = new IOContext(bufferRecycler0, float0, false);
        PipedInputStream pipedInputStream0 = new PipedInputStream();
        ByteQuadsCanonicalizer byteQuadsCanonicalizer0 = ByteQuadsCanonicalizer.createRoot();
        UTF8StreamJsonParser uTF8StreamJsonParser0 = new UTF8StreamJsonParser(iOContext0, 2, pipedInputStream0, (ObjectCodec) null, byteQuadsCanonicalizer0, (byte[]) null, 3, 2, false);
        uTF8StreamJsonParser0.getShortValue();
    }

    @Test(timeout = 4000)
    public void test077() throws Throwable {
        byte[] byteArray0 = new byte[0];
        String string0 = ParserMinimalBase._ascii(byteArray0);
    }

    @Test(timeout = 4000)
    public void test088() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, false);
        ObjectCodec objectCodec0 = mock(ObjectCodec.class, new ViolatedAssumptionAnswer());
        UTF8DataInputJsonParser uTF8DataInputJsonParser0 = new UTF8DataInputJsonParser(iOContext0, 3, (DataInput) null, objectCodec0, (ByteQuadsCanonicalizer) null, 2);
        uTF8DataInputJsonParser0.getCurrentToken();
    }

    @Test(timeout = 4000)
    public void test099() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, false);
        StringReader stringReader0 = new StringReader("+");
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, 3, stringReader0, (ObjectCodec) null, charsToNameCanonicalizer0);
        readerBasedJsonParser0.nextValue();
    }

    @Test(timeout = 4000)
    public void test1010() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        Float float0 = new Float((float) 1);
        IOContext iOContext0 = new IOContext(bufferRecycler0, float0, false);
        PipedInputStream pipedInputStream0 = new PipedInputStream();
        ByteQuadsCanonicalizer byteQuadsCanonicalizer0 = ByteQuadsCanonicalizer.createRoot();
        UTF8StreamJsonParser uTF8StreamJsonParser0 = new UTF8StreamJsonParser(iOContext0, 2, pipedInputStream0, (ObjectCodec) null, byteQuadsCanonicalizer0, (byte[]) null, 3, 2, false);
        int int0 = uTF8StreamJsonParser0.currentTokenId();
    }

    @Test(timeout = 4000)
    public void test1111() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, true);
        StringReader stringReader0 = new StringReader("u3|2|");
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, 0, stringReader0, (ObjectCodec) null, charsToNameCanonicalizer0);
        int int0 = readerBasedJsonParser0.getCurrentTokenId();
    }

    @Test(timeout = 4000)
    public void test1212() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, false);
        PipedInputStream pipedInputStream0 = new PipedInputStream();
        DataInputStream dataInputStream0 = new DataInputStream(pipedInputStream0);
        ByteQuadsCanonicalizer byteQuadsCanonicalizer0 = ByteQuadsCanonicalizer.createRoot();
        UTF8DataInputJsonParser uTF8DataInputJsonParser0 = new UTF8DataInputJsonParser(iOContext0, 0, dataInputStream0, (ObjectCodec) null, byteQuadsCanonicalizer0, 0);
        boolean boolean0 = uTF8DataInputJsonParser0.hasCurrentToken();
    }

    @Test(timeout = 4000)
    public void test1313() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, true);
        StringReader stringReader0 = new StringReader("7");
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, 0, stringReader0, (ObjectCodec) null, charsToNameCanonicalizer0);
        readerBasedJsonParser0.nextIntValue(33);
        boolean boolean0 = readerBasedJsonParser0.hasCurrentToken();
    }

    @Test(timeout = 4000)
    public void test1414() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, true);
        StringReader stringReader0 = new StringReader("7");
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, 0, stringReader0, (ObjectCodec) null, charsToNameCanonicalizer0);
        readerBasedJsonParser0.nextIntValue(33);
        boolean boolean0 = readerBasedJsonParser0.hasTokenId(7);
    }

    @Test(timeout = 4000)
    public void test1515() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, false);
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        StringReader stringReader0 = new StringReader("");
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, 33, stringReader0, (ObjectCodec) null, charsToNameCanonicalizer0);
        boolean boolean0 = readerBasedJsonParser0.hasTokenId(0);
    }

    @Test(timeout = 4000)
    public void test1616() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, false);
        StringReader stringReader0 = new StringReader("");
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, 1, stringReader0, (ObjectCodec) null, charsToNameCanonicalizer0);
        boolean boolean0 = readerBasedJsonParser0.hasTokenId(3);
    }

    @Test(timeout = 4000)
    public void test1717() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        StringReader stringReader0 = new StringReader("7");
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        IOContext iOContext0 = new IOContext(bufferRecycler0, stringReader0, false);
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, 33, stringReader0, (ObjectCodec) null, charsToNameCanonicalizer0);
        readerBasedJsonParser0.nextIntValue((byte) 66);
        boolean boolean0 = readerBasedJsonParser0.hasTokenId(0);
    }

    @Test(timeout = 4000)
    public void test1818() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, false);
        StringReader stringReader0 = new StringReader("");
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, 3, stringReader0, (ObjectCodec) null, charsToNameCanonicalizer0);
        JsonToken jsonToken0 = JsonToken.FIELD_NAME;
        boolean boolean0 = readerBasedJsonParser0.hasToken(jsonToken0);
    }

    @Test(timeout = 4000)
    public void test1919() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, false);
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        StringReader stringReader0 = new StringReader("");
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, (-774), stringReader0, (ObjectCodec) null, charsToNameCanonicalizer0);
        boolean boolean0 = readerBasedJsonParser0.hasToken((JsonToken) null);
    }

    @Test(timeout = 4000)
    public void test2020() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        Float float0 = new Float((float) 1);
        IOContext iOContext0 = new IOContext(bufferRecycler0, float0, false);
        PipedInputStream pipedInputStream0 = new PipedInputStream();
        ByteQuadsCanonicalizer byteQuadsCanonicalizer0 = ByteQuadsCanonicalizer.createRoot();
        UTF8StreamJsonParser uTF8StreamJsonParser0 = new UTF8StreamJsonParser(iOContext0, 2, pipedInputStream0, (ObjectCodec) null, byteQuadsCanonicalizer0, (byte[]) null, 3, 2, false);
        boolean boolean0 = uTF8StreamJsonParser0.isExpectedStartArrayToken();
    }

    @Test(timeout = 4000)
    public void test2121() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        Float float0 = new Float((float) 1);
        IOContext iOContext0 = new IOContext(bufferRecycler0, float0, false);
        PipedInputStream pipedInputStream0 = new PipedInputStream();
        ByteQuadsCanonicalizer byteQuadsCanonicalizer0 = ByteQuadsCanonicalizer.createRoot();
        UTF8StreamJsonParser uTF8StreamJsonParser0 = new UTF8StreamJsonParser(iOContext0, 2, pipedInputStream0, (ObjectCodec) null, byteQuadsCanonicalizer0, (byte[]) null, 3, 2, false);
        boolean boolean0 = uTF8StreamJsonParser0.isExpectedStartObjectToken();
    }

    @Test(timeout = 4000)
    public void test2222() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, false);
        StringReader stringReader0 = new StringReader("Broken surrogate pair: first char 0x");
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, 2, stringReader0, (ObjectCodec) null, charsToNameCanonicalizer0);
        JsonParser jsonParser0 = readerBasedJsonParser0.skipChildren();
        jsonParser0.isExpectedStartObjectToken();
    }

    @Test(timeout = 4000)
    public void test2323() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, false);
        StringReader stringReader0 = new StringReader("");
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, 1, stringReader0, (ObjectCodec) null, charsToNameCanonicalizer0);
        readerBasedJsonParser0.clearCurrentToken();
        readerBasedJsonParser0.getLastClearedToken();
    }

    @Test(timeout = 4000)
    public void test2424() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        StringReader stringReader0 = new StringReader("7");
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0.CHAR_TOKEN_BUFFER, true);
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, (byte) 4, stringReader0, (ObjectCodec) null, charsToNameCanonicalizer0);
        readerBasedJsonParser0.nextIntValue(1);
        readerBasedJsonParser0.clearCurrentToken();
        readerBasedJsonParser0.hasCurrentToken();
    }

    @Test(timeout = 4000)
    public void test2525() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, true);
        StringReader stringReader0 = new StringReader("u3|2|");
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, 0, stringReader0, (ObjectCodec) null, charsToNameCanonicalizer0);
        boolean boolean0 = readerBasedJsonParser0.getValueAsBoolean(false);
    }

    @Test(timeout = 4000)
    public void test2626() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, true);
        StringReader stringReader0 = new StringReader("7");
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, 0, stringReader0, (ObjectCodec) null, charsToNameCanonicalizer0);
        readerBasedJsonParser0.nextIntValue(33);
        boolean boolean0 = readerBasedJsonParser0.getValueAsBoolean(false);
    }

    @Test(timeout = 4000)
    public void test2727() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        Float float0 = new Float((float) 1);
        IOContext iOContext0 = new IOContext(bufferRecycler0, float0, false);
        StringReader stringReader0 = new StringReader("7");
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, 0, stringReader0, (ObjectCodec) null, charsToNameCanonicalizer0);
        readerBasedJsonParser0.nextValue();
        int int0 = readerBasedJsonParser0.getValueAsInt();
        readerBasedJsonParser0.currentTokenId();
    }

    @Test(timeout = 4000)
    public void test2728() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        Float float0 = new Float((float) 1);
        IOContext iOContext0 = new IOContext(bufferRecycler0, float0, false);
        StringReader stringReader0 = new StringReader("7");
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, 0, stringReader0, (ObjectCodec) null, charsToNameCanonicalizer0);
        readerBasedJsonParser0.nextValue();
        int int0 = readerBasedJsonParser0.getValueAsInt();
    }

    @Test(timeout = 4000)
    public void test2829() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, false);
        StringReader stringReader0 = new StringReader("");
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, 1, stringReader0, (ObjectCodec) null, charsToNameCanonicalizer0);
        int int0 = readerBasedJsonParser0.getValueAsInt();
    }

    @Test(timeout = 4000)
    public void test2930() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        StringReader stringReader0 = new StringReader("7");
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        IOContext iOContext0 = new IOContext(bufferRecycler0, stringReader0, false);
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, 33, stringReader0, (ObjectCodec) null, charsToNameCanonicalizer0);
        readerBasedJsonParser0.nextIntValue((byte) 66);
        long long0 = readerBasedJsonParser0.getValueAsLong();
    }

    @Test(timeout = 4000)
    public void test3031() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, false);
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        StringReader stringReader0 = new StringReader("");
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, 33, stringReader0, (ObjectCodec) null, charsToNameCanonicalizer0);
        long long0 = readerBasedJsonParser0.getValueAsLong();
    }

    @Test(timeout = 4000)
    public void test3132() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        Float float0 = new Float((float) 1);
        IOContext iOContext0 = new IOContext(bufferRecycler0, float0, false);
        PipedInputStream pipedInputStream0 = new PipedInputStream();
        ByteQuadsCanonicalizer byteQuadsCanonicalizer0 = ByteQuadsCanonicalizer.createRoot();
        UTF8StreamJsonParser uTF8StreamJsonParser0 = new UTF8StreamJsonParser(iOContext0, 2, pipedInputStream0, (ObjectCodec) null, byteQuadsCanonicalizer0, (byte[]) null, 3, 2, false);
        double double0 = uTF8StreamJsonParser0.getValueAsDouble((double) 0);
    }

    @Test(timeout = 4000)
    public void test3233() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        StringReader stringReader0 = new StringReader("7");
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0.CHAR_CONCAT_BUFFER, true);
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, (byte) 4, stringReader0, (ObjectCodec) null, charsToNameCanonicalizer0);
        readerBasedJsonParser0.nextIntValue(1);
        double double0 = readerBasedJsonParser0.getValueAsDouble((double) (byte) (-8));
    }

    @Test(timeout = 4000)
    public void test3334() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, false);
        StringReader stringReader0 = new StringReader("");
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, 1, stringReader0, (ObjectCodec) null, charsToNameCanonicalizer0);
        String string0 = readerBasedJsonParser0.getValueAsString();
    }

    @Test(timeout = 4000)
    public void test3435() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, false);
        StringReader stringReader0 = new StringReader("+`t!F");
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, 2, stringReader0, (ObjectCodec) null, charsToNameCanonicalizer0);
        readerBasedJsonParser0.nextValue();
    }

    @Test(timeout = 4000)
    public void test3536() throws Throwable {
        String string0 = ParserMinimalBase._getCharDesc(3684);
    }

    @Test(timeout = 4000)
    public void test3637() throws Throwable {
        byte[] byteArray0 = ParserMinimalBase._asciiBytes("q");
    }
}
