/*
 * This file was automatically generated by EvoSuite
 * Sun Oct 15 19:25:33 GMT 2023
 */
package com.fasterxml.jackson.core.util;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.base.GeneratorBase;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.core.json.ReaderBasedJsonParser;
import com.fasterxml.jackson.core.json.UTF8JsonGenerator;
import com.fasterxml.jackson.core.json.WriterBasedJsonGenerator;
import com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer;
import com.fasterxml.jackson.core.util.BufferRecycler;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.core.util.JsonGeneratorDelegate;
import com.fasterxml.jackson.core.util.Separators;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.mock.java.io.MockFile;
import org.evosuite.runtime.mock.java.io.MockFileInputStream;
import org.evosuite.runtime.mock.java.io.MockFileOutputStream;
import org.evosuite.runtime.mock.java.io.MockPrintStream;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class DefaultPrettyPrinter_ESTest extends DefaultPrettyPrinter_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        DefaultPrettyPrinter defaultPrettyPrinter0 = new DefaultPrettyPrinter("com.fasterxml.jackson.core.util.DefaultPrettyPrinter$NopIndenter");
        Separators separators0 = Separators.createDefaultInstance();
        defaultPrettyPrinter0.withSeparators(separators0);
        BufferRecycler bufferRecycler0 = new BufferRecycler(123, 404);
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, true);
        StringReader stringReader0 = new StringReader("%7ZL?m q*");
        CharsToNameCanonicalizer charsToNameCanonicalizer0 = CharsToNameCanonicalizer.createRoot();
        ReaderBasedJsonParser readerBasedJsonParser0 = new ReaderBasedJsonParser(iOContext0, 2046, stringReader0, (ObjectCodec) null, charsToNameCanonicalizer0);
        ByteArrayBuilder byteArrayBuilder0 = readerBasedJsonParser0._getByteArrayBuilder();
        UTF8JsonGenerator uTF8JsonGenerator0 = new UTF8JsonGenerator(iOContext0, 3, (ObjectCodec) null, byteArrayBuilder0, byteArrayBuilder0.NO_BYTES, 2, true);
        defaultPrettyPrinter0.writeArrayValueSeparator(uTF8JsonGenerator0);
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        DefaultPrettyPrinter defaultPrettyPrinter0 = new DefaultPrettyPrinter();
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        Separators separators0 = Separators.createDefaultInstance();
        defaultPrettyPrinter0._separators = separators0;
        IOContext iOContext0 = new IOContext(bufferRecycler0, defaultPrettyPrinter0, false);
        StringWriter stringWriter0 = new StringWriter(3);
        WriterBasedJsonGenerator writerBasedJsonGenerator0 = new WriterBasedJsonGenerator(iOContext0, 983, (ObjectCodec) null, stringWriter0);
        defaultPrettyPrinter0.writeObjectEntrySeparator(writerBasedJsonGenerator0);
        writerBasedJsonGenerator0.getOutputBuffered();
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        DefaultPrettyPrinter defaultPrettyPrinter0 = new DefaultPrettyPrinter();
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, defaultPrettyPrinter0, false);
        StringWriter stringWriter0 = new StringWriter(3);
        WriterBasedJsonGenerator writerBasedJsonGenerator0 = new WriterBasedJsonGenerator(iOContext0, 983, (ObjectCodec) null, stringWriter0);
        DefaultPrettyPrinter defaultPrettyPrinter1 = defaultPrettyPrinter0.withoutSpacesInObjectEntries();
        Separators separators0 = Separators.createDefaultInstance();
        defaultPrettyPrinter1._separators = separators0;
        defaultPrettyPrinter1.writeObjectFieldValueSeparator(writerBasedJsonGenerator0);
        writerBasedJsonGenerator0.getOutputBuffered();
    }

    @Test(timeout = 4000)
    public void test023() throws Throwable {
        DefaultPrettyPrinter defaultPrettyPrinter0 = new DefaultPrettyPrinter();
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, defaultPrettyPrinter0, false);
        StringWriter stringWriter0 = new StringWriter(3);
        WriterBasedJsonGenerator writerBasedJsonGenerator0 = new WriterBasedJsonGenerator(iOContext0, 983, (ObjectCodec) null, stringWriter0);
        DefaultPrettyPrinter defaultPrettyPrinter1 = defaultPrettyPrinter0.withoutSpacesInObjectEntries();
        Separators separators0 = Separators.createDefaultInstance();
        defaultPrettyPrinter1._separators = separators0;
        defaultPrettyPrinter1.writeObjectFieldValueSeparator(writerBasedJsonGenerator0);
    }

    @Test(timeout = 4000)
    public void test034() throws Throwable {
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, "u>rYU6gS#r;rA-", true);
        UTF8JsonGenerator uTF8JsonGenerator0 = new UTF8JsonGenerator(iOContext0, 47, (ObjectCodec) null, (OutputStream) null);
        DefaultIndenter defaultIndenter0 = DefaultIndenter.SYSTEM_LINEFEED_INSTANCE;
        defaultIndenter0.writeIndentation(uTF8JsonGenerator0, 633);
        uTF8JsonGenerator0.getOutputBuffered();
    }

    @Test(timeout = 4000)
    public void test045() throws Throwable {
        DefaultIndenter defaultIndenter0 = new DefaultIndenter();
        boolean boolean0 = defaultIndenter0.isInline();
    }

    @Test(timeout = 4000)
    public void test056() throws Throwable {
        DefaultPrettyPrinter.FixedSpaceIndenter defaultPrettyPrinter_FixedSpaceIndenter0 = DefaultPrettyPrinter.FixedSpaceIndenter.instance;
        boolean boolean0 = defaultPrettyPrinter_FixedSpaceIndenter0.isInline();
    }

    @Test(timeout = 4000)
    public void test078() throws Throwable {
        DefaultPrettyPrinter defaultPrettyPrinter0 = new DefaultPrettyPrinter();
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, "u>rYU6gS#r;rA-", true);
        UTF8JsonGenerator uTF8JsonGenerator0 = new UTF8JsonGenerator(iOContext0, 1, (ObjectCodec) null, (OutputStream) null);
        defaultPrettyPrinter0.writeStartArray(uTF8JsonGenerator0);
        uTF8JsonGenerator0.getOutputBuffered();
    }

    @Test(timeout = 4000)
    public void test089() throws Throwable {
        DefaultPrettyPrinter defaultPrettyPrinter0 = new DefaultPrettyPrinter("");
        DefaultPrettyPrinter defaultPrettyPrinter1 = defaultPrettyPrinter0._withSpaces(true);
    }

    @Test(timeout = 4000)
    public void test0910() throws Throwable {
        DefaultPrettyPrinter defaultPrettyPrinter0 = new DefaultPrettyPrinter("com.fasterxml.jackson.core.util.DefaultPrettyPrinter$NopIndenter");
        defaultPrettyPrinter0.writeStartObject((JsonGenerator) null);
    }

    @Test(timeout = 4000)
    public void test1011() throws Throwable {
        DefaultPrettyPrinter defaultPrettyPrinter0 = new DefaultPrettyPrinter();
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        PipedInputStream pipedInputStream0 = new PipedInputStream();
        BufferedInputStream bufferedInputStream0 = new BufferedInputStream(pipedInputStream0, 1);
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferedInputStream0, false);
        byte[] byteArray0 = new byte[5];
        UTF8JsonGenerator uTF8JsonGenerator0 = new UTF8JsonGenerator(iOContext0, (-286), (ObjectCodec) null, (OutputStream) null, byteArray0, (-28), false);
        defaultPrettyPrinter0.writeStartObject(uTF8JsonGenerator0);
    }

    @Test(timeout = 4000)
    public void test1112() throws Throwable {
        DefaultPrettyPrinter defaultPrettyPrinter0 = new DefaultPrettyPrinter();
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        Object object0 = new Object();
        IOContext iOContext0 = new IOContext(bufferRecycler0, object0, false);
        ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream(3);
        byte[] byteArray0 = new byte[9];
        UTF8JsonGenerator uTF8JsonGenerator0 = new UTF8JsonGenerator(iOContext0, (-574), (ObjectCodec) null, byteArrayOutputStream0, byteArray0, 47, false);
        defaultPrettyPrinter0.writeStartArray(uTF8JsonGenerator0);
    }

    @Test(timeout = 4000)
    public void test1213() throws Throwable {
        DefaultPrettyPrinter defaultPrettyPrinter0 = new DefaultPrettyPrinter("O=(3KH.9.)(>0P*");
        defaultPrettyPrinter0.writeRootValueSeparator((JsonGenerator) null);
    }

    @Test(timeout = 4000)
    public void test1314() throws Throwable {
        DefaultPrettyPrinter defaultPrettyPrinter0 = new DefaultPrettyPrinter();
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, false);
        ByteArrayBuilder byteArrayBuilder0 = new ByteArrayBuilder(bufferRecycler0);
        UTF8JsonGenerator uTF8JsonGenerator0 = new UTF8JsonGenerator(iOContext0, (-1533), (ObjectCodec) null, byteArrayBuilder0, byteArrayBuilder0.NO_BYTES, (-2796), false);
        defaultPrettyPrinter0.writeRootValueSeparator(uTF8JsonGenerator0);
    }

    @Test(timeout = 4000)
    public void test1415() throws Throwable {
        DefaultPrettyPrinter defaultPrettyPrinter0 = new DefaultPrettyPrinter();
        defaultPrettyPrinter0.writeObjectFieldValueSeparator((JsonGenerator) null);
    }

    @Test(timeout = 4000)
    public void test1516() throws Throwable {
        DefaultPrettyPrinter defaultPrettyPrinter0 = new DefaultPrettyPrinter();
        BufferRecycler bufferRecycler0 = new BufferRecycler(27, 27);
        IOContext iOContext0 = new IOContext(bufferRecycler0, (Object) null, false);
        MockFileOutputStream mockFileOutputStream0 = new MockFileOutputStream("swd.", true);
        byte[] byteArray0 = new byte[8];
        UTF8JsonGenerator uTF8JsonGenerator0 = new UTF8JsonGenerator(iOContext0, 3, (ObjectCodec) null, mockFileOutputStream0, byteArray0, (byte) (-64), false);
        defaultPrettyPrinter0.writeObjectFieldValueSeparator(uTF8JsonGenerator0);
    }

    @Test(timeout = 4000)
    public void test1617() throws Throwable {
        DefaultPrettyPrinter defaultPrettyPrinter0 = new DefaultPrettyPrinter();
        defaultPrettyPrinter0.writeObjectEntrySeparator((JsonGenerator) null);
    }

    @Test(timeout = 4000)
    public void test1718() throws Throwable {
        DefaultPrettyPrinter defaultPrettyPrinter0 = new DefaultPrettyPrinter("");
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        Object object0 = new Object();
        IOContext iOContext0 = new IOContext(bufferRecycler0, object0, true);
        BufferedOutputStream bufferedOutputStream0 = new BufferedOutputStream((OutputStream) null, 2);
        FilterOutputStream filterOutputStream0 = new FilterOutputStream(bufferedOutputStream0);
        byte[] byteArray0 = new byte[8];
        UTF8JsonGenerator uTF8JsonGenerator0 = new UTF8JsonGenerator(iOContext0, 2, (ObjectCodec) null, filterOutputStream0, byteArray0, (byte) 103, true);
        defaultPrettyPrinter0.writeObjectEntrySeparator(uTF8JsonGenerator0);
    }

    @Test(timeout = 4000)
    public void test1819() throws Throwable {
        DefaultPrettyPrinter defaultPrettyPrinter0 = new DefaultPrettyPrinter();
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, false);
        ByteArrayBuilder byteArrayBuilder0 = new ByteArrayBuilder(bufferRecycler0);
        MockPrintStream mockPrintStream0 = new MockPrintStream(byteArrayBuilder0);
        UTF8JsonGenerator uTF8JsonGenerator0 = new UTF8JsonGenerator(iOContext0, 1, (ObjectCodec) null, mockPrintStream0, byteArrayBuilder0.NO_BYTES, 0, false);
        defaultPrettyPrinter0.writeObjectEntrySeparator(uTF8JsonGenerator0);
    }

    @Test(timeout = 4000)
    public void test1920() throws Throwable {
        DefaultPrettyPrinter defaultPrettyPrinter0 = new DefaultPrettyPrinter((SerializableString) null);
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, false);
        MockFile mockFile0 = new MockFile("y[SOo.`V'~");
        MockPrintStream mockPrintStream0 = new MockPrintStream(mockFile0);
        ObjectOutputStream objectOutputStream0 = new ObjectOutputStream(mockPrintStream0);
        byte[] byteArray0 = new byte[7];
        UTF8JsonGenerator uTF8JsonGenerator0 = new UTF8JsonGenerator(iOContext0, 0, (ObjectCodec) null, objectOutputStream0, byteArray0, (byte) 91, false);
        defaultPrettyPrinter0.writeEndObject(uTF8JsonGenerator0, (byte) (-36));
    }

    @Test(timeout = 4000)
    public void test2021() throws Throwable {
        DefaultPrettyPrinter defaultPrettyPrinter0 = new DefaultPrettyPrinter((SerializableString) null);
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, false);
        MockFile mockFile0 = new MockFile("y[SOo.`V'~");
        MockPrintStream mockPrintStream0 = new MockPrintStream(mockFile0);
        ObjectOutputStream objectOutputStream0 = new ObjectOutputStream(mockPrintStream0);
        byte[] byteArray0 = new byte[7];
        UTF8JsonGenerator uTF8JsonGenerator0 = new UTF8JsonGenerator(iOContext0, 0, (ObjectCodec) null, objectOutputStream0, byteArray0, (byte) (-57), false);
        defaultPrettyPrinter0.writeEndObject(uTF8JsonGenerator0, (byte) (-36));
    }

    @Test(timeout = 4000)
    public void test2122() throws Throwable {
        DefaultPrettyPrinter defaultPrettyPrinter0 = new DefaultPrettyPrinter();
        defaultPrettyPrinter0.writeEndArray((JsonGenerator) null, (-2795));
    }

    @Test(timeout = 4000)
    public void test2223() throws Throwable {
        DefaultPrettyPrinter defaultPrettyPrinter0 = new DefaultPrettyPrinter();
        BufferRecycler bufferRecycler0 = new BufferRecycler(146, 3198);
        IOContext iOContext0 = new IOContext(bufferRecycler0, bufferRecycler0, false);
        ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream();
        byte[] byteArray0 = new byte[1];
        UTF8JsonGenerator uTF8JsonGenerator0 = new UTF8JsonGenerator(iOContext0, (-1184), (ObjectCodec) null, byteArrayOutputStream0, byteArray0, 3198, false);
        defaultPrettyPrinter0.writeEndArray(uTF8JsonGenerator0, (-319));
    }

    @Test(timeout = 4000)
    public void test2324() throws Throwable {
        byte[] byteArray0 = new byte[5];
        DefaultPrettyPrinter defaultPrettyPrinter0 = new DefaultPrettyPrinter("D^TZMTP@[_GY");
        BufferRecycler bufferRecycler0 = new BufferRecycler((byte) 32, (byte) 32);
        IOContext iOContext0 = new IOContext(bufferRecycler0, defaultPrettyPrinter0, false);
        PipedOutputStream pipedOutputStream0 = new PipedOutputStream();
        UTF8JsonGenerator uTF8JsonGenerator0 = new UTF8JsonGenerator(iOContext0, (-2442), (ObjectCodec) null, pipedOutputStream0, byteArray0, 392, false);
        defaultPrettyPrinter0.writeEndArray(uTF8JsonGenerator0, 1);
    }

    @Test(timeout = 4000)
    public void test2425() throws Throwable {
        DefaultPrettyPrinter defaultPrettyPrinter0 = new DefaultPrettyPrinter(" ");
        defaultPrettyPrinter0.writeArrayValueSeparator((JsonGenerator) null);
    }

    @Test(timeout = 4000)
    public void test2526() throws Throwable {
        SerializedString serializedString0 = DefaultPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
        DefaultPrettyPrinter defaultPrettyPrinter0 = new DefaultPrettyPrinter(serializedString0);
        defaultPrettyPrinter0.withSeparators((Separators) null);
    }

    @Test(timeout = 4000)
    public void test2627() throws Throwable {
        DefaultPrettyPrinter defaultPrettyPrinter0 = new DefaultPrettyPrinter();
        defaultPrettyPrinter0.beforeObjectEntries((JsonGenerator) null);
    }

    @Test(timeout = 4000)
    public void test2728() throws Throwable {
        DefaultPrettyPrinter defaultPrettyPrinter0 = new DefaultPrettyPrinter();
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        Object object0 = new Object();
        IOContext iOContext0 = new IOContext(bufferRecycler0, object0, false);
        ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream(3);
        byte[] byteArray0 = new byte[9];
        UTF8JsonGenerator uTF8JsonGenerator0 = new UTF8JsonGenerator(iOContext0, (-1895), (ObjectCodec) null, byteArrayOutputStream0, byteArray0, 248, true);
        defaultPrettyPrinter0.beforeObjectEntries(uTF8JsonGenerator0);
    }

    @Test(timeout = 4000)
    public void test2829() throws Throwable {
        SerializedString serializedString0 = PrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
        DefaultPrettyPrinter defaultPrettyPrinter0 = new DefaultPrettyPrinter(serializedString0);
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        Object object0 = new Object();
        IOContext iOContext0 = new IOContext(bufferRecycler0, object0, true);
        ByteArrayBuilder byteArrayBuilder0 = new ByteArrayBuilder();
        UTF8JsonGenerator uTF8JsonGenerator0 = new UTF8JsonGenerator(iOContext0, 2, (ObjectCodec) null, byteArrayBuilder0, byteArrayBuilder0.NO_BYTES, 772, false);
        defaultPrettyPrinter0.beforeObjectEntries(uTF8JsonGenerator0);
    }

    @Test(timeout = 4000)
    public void test2930() throws Throwable {
        DefaultPrettyPrinter defaultPrettyPrinter0 = new DefaultPrettyPrinter();
        defaultPrettyPrinter0.beforeArrayValues((JsonGenerator) null);
    }

    @Test(timeout = 4000)
    public void test3031() throws Throwable {
        SerializedString serializedString0 = PrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
        DefaultPrettyPrinter defaultPrettyPrinter0 = new DefaultPrettyPrinter(serializedString0);
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, defaultPrettyPrinter0, false);
        ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream(68);
        ObjectOutputStream objectOutputStream0 = new ObjectOutputStream(byteArrayOutputStream0);
        byte[] byteArray0 = new byte[9];
        UTF8JsonGenerator uTF8JsonGenerator0 = new UTF8JsonGenerator(iOContext0, 1721, (ObjectCodec) null, objectOutputStream0, byteArray0, (byte) 20, false);
        defaultPrettyPrinter0.beforeArrayValues(uTF8JsonGenerator0);
    }

    @Test(timeout = 4000)
    public void test3132() throws Throwable {
        SerializedString serializedString0 = DefaultPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
        DefaultPrettyPrinter defaultPrettyPrinter0 = null;
        defaultPrettyPrinter0 = new DefaultPrettyPrinter((DefaultPrettyPrinter) null, serializedString0);
    }

    @Test(timeout = 4000)
    public void test3233() throws Throwable {
        DefaultPrettyPrinter defaultPrettyPrinter0 = null;
        defaultPrettyPrinter0 = new DefaultPrettyPrinter((DefaultPrettyPrinter) null);
    }

    @Test(timeout = 4000)
    public void test3334() throws Throwable {
        DefaultPrettyPrinter defaultPrettyPrinter0 = new DefaultPrettyPrinter();
        DefaultPrettyPrinter defaultPrettyPrinter1 = defaultPrettyPrinter0.withRootSeparator((SerializableString) null);
    }

    @Test(timeout = 4000)
    public void test3435() throws Throwable {
        DefaultPrettyPrinter defaultPrettyPrinter0 = new DefaultPrettyPrinter();
        SerializedString serializedString0 = new SerializedString("=hh'3C");
        DefaultPrettyPrinter defaultPrettyPrinter1 = defaultPrettyPrinter0.withRootSeparator((SerializableString) serializedString0);
    }

    @Test(timeout = 4000)
    public void test3536() throws Throwable {
        SerializedString serializedString0 = DefaultPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
        DefaultPrettyPrinter defaultPrettyPrinter0 = new DefaultPrettyPrinter(serializedString0);
        DefaultPrettyPrinter defaultPrettyPrinter1 = new DefaultPrettyPrinter(defaultPrettyPrinter0);
        defaultPrettyPrinter1.equals((Object) defaultPrettyPrinter0);
    }

    @Test(timeout = 4000)
    public void test3637() throws Throwable {
        SerializedString serializedString0 = DefaultPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
        DefaultPrettyPrinter defaultPrettyPrinter0 = new DefaultPrettyPrinter(serializedString0);
        DefaultPrettyPrinter defaultPrettyPrinter1 = new DefaultPrettyPrinter(defaultPrettyPrinter0, serializedString0);
        defaultPrettyPrinter1.equals((Object) defaultPrettyPrinter0);
    }

    @Test(timeout = 4000)
    public void test3738() throws Throwable {
        DefaultPrettyPrinter defaultPrettyPrinter0 = new DefaultPrettyPrinter((SerializableString) null);
        DefaultIndenter defaultIndenter0 = new DefaultIndenter("+6#9PeE73#&a)B.", "+6#9PeE73#&a)B.");
        defaultPrettyPrinter0.indentArraysWith(defaultIndenter0);
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        Object object0 = new Object();
        IOContext iOContext0 = new IOContext(bufferRecycler0, object0, true);
        UTF8JsonGenerator uTF8JsonGenerator0 = new UTF8JsonGenerator(iOContext0, 3, (ObjectCodec) null, (OutputStream) null);
        defaultPrettyPrinter0.writeEndArray(uTF8JsonGenerator0, 3);
        uTF8JsonGenerator0.getOutputBuffered();
    }

    @Test(timeout = 4000)
    public void test3839() throws Throwable {
        DefaultIndenter defaultIndenter0 = DefaultIndenter.SYSTEM_LINEFEED_INSTANCE;
        DefaultPrettyPrinter defaultPrettyPrinter0 = new DefaultPrettyPrinter();
        DefaultPrettyPrinter defaultPrettyPrinter1 = defaultPrettyPrinter0.withArrayIndenter(defaultIndenter0);
        defaultPrettyPrinter1.writeStartArray((JsonGenerator) null);
    }

    @Test(timeout = 4000)
    public void test3940() throws Throwable {
        DefaultPrettyPrinter defaultPrettyPrinter0 = new DefaultPrettyPrinter();
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        Object object0 = new Object();
        IOContext iOContext0 = new IOContext(bufferRecycler0, object0, false);
        MockPrintStream mockPrintStream0 = new MockPrintStream("B6:_fs4m'qTmJ,n");
        byte[] byteArray0 = new byte[4];
        UTF8JsonGenerator uTF8JsonGenerator0 = new UTF8JsonGenerator(iOContext0, 2, (ObjectCodec) null, mockPrintStream0, byteArray0, (-1), false);
        defaultPrettyPrinter0.writeStartArray(uTF8JsonGenerator0);
    }

    @Test(timeout = 4000)
    public void test4041() throws Throwable {
        DefaultPrettyPrinter defaultPrettyPrinter0 = new DefaultPrettyPrinter();
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, "=hhP'3C", false);
        UTF8JsonGenerator uTF8JsonGenerator0 = new UTF8JsonGenerator(iOContext0, 23, (ObjectCodec) null, (OutputStream) null);
        defaultPrettyPrinter0.writeEndObject(uTF8JsonGenerator0, 2);
        uTF8JsonGenerator0.getOutputBuffered();
    }

    @Test(timeout = 4000)
    public void test4142() throws Throwable {
        DefaultPrettyPrinter.NopIndenter defaultPrettyPrinter_NopIndenter0 = DefaultPrettyPrinter.NopIndenter.instance;
        SerializedString serializedString0 = new SerializedString("");
        DefaultPrettyPrinter defaultPrettyPrinter0 = new DefaultPrettyPrinter("");
        DefaultPrettyPrinter defaultPrettyPrinter1 = defaultPrettyPrinter0.withRootSeparator((SerializableString) serializedString0);
        defaultPrettyPrinter1._objectIndenter = (DefaultPrettyPrinter.Indenter) defaultPrettyPrinter_NopIndenter0;
        defaultPrettyPrinter0.writeEndObject((JsonGenerator) null, 0);
    }

    @Test(timeout = 4000)
    public void test4243() throws Throwable {
        DefaultPrettyPrinter defaultPrettyPrinter0 = new DefaultPrettyPrinter();
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, "u>rYU6gS#r;rA-", true);
        UTF8JsonGenerator uTF8JsonGenerator0 = new UTF8JsonGenerator(iOContext0, 47, (ObjectCodec) null, (OutputStream) null);
        defaultPrettyPrinter0.writeStartObject(uTF8JsonGenerator0);
        uTF8JsonGenerator0.canOmitFields();
    }

    @Test(timeout = 4000)
    public void test4344() throws Throwable {
        DefaultPrettyPrinter defaultPrettyPrinter0 = new DefaultPrettyPrinter();
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        Object object0 = new Object();
        IOContext iOContext0 = new IOContext(bufferRecycler0, object0, true);
        ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream(3);
        byte[] byteArray0 = new byte[9];
        UTF8JsonGenerator uTF8JsonGenerator0 = new UTF8JsonGenerator(iOContext0, 1, (ObjectCodec) null, byteArrayOutputStream0, byteArray0, 0, true);
        JsonGeneratorDelegate jsonGeneratorDelegate0 = new JsonGeneratorDelegate(uTF8JsonGenerator0, true);
        defaultPrettyPrinter0.writeRootValueSeparator(jsonGeneratorDelegate0);
    }

    @Test(timeout = 4000)
    public void test4345() throws Throwable {
        DefaultPrettyPrinter defaultPrettyPrinter0 = new DefaultPrettyPrinter();
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        Object object0 = new Object();
        IOContext iOContext0 = new IOContext(bufferRecycler0, object0, true);
        ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream(3);
        byte[] byteArray0 = new byte[9];
        UTF8JsonGenerator uTF8JsonGenerator0 = new UTF8JsonGenerator(iOContext0, 1, (ObjectCodec) null, byteArrayOutputStream0, byteArray0, 0, true);
        JsonGeneratorDelegate jsonGeneratorDelegate0 = new JsonGeneratorDelegate(uTF8JsonGenerator0, true);
        defaultPrettyPrinter0.writeRootValueSeparator(jsonGeneratorDelegate0);
        uTF8JsonGenerator0.getOutputBuffered();
    }

    @Test(timeout = 4000)
    public void test4446() throws Throwable {
        DefaultPrettyPrinter defaultPrettyPrinter0 = new DefaultPrettyPrinter("com.fasterxml.jackson.core.util.DefaultPrettyPrinter$FixedSpaceIndenter");
        DefaultPrettyPrinter.NopIndenter defaultPrettyPrinter_NopIndenter0 = DefaultPrettyPrinter.NopIndenter.instance;
        defaultPrettyPrinter0.indentObjectsWith(defaultPrettyPrinter_NopIndenter0);
        DefaultPrettyPrinter defaultPrettyPrinter1 = defaultPrettyPrinter0.withObjectIndenter(defaultPrettyPrinter_NopIndenter0);
    }

    @Test(timeout = 4000)
    public void test4547() throws Throwable {
        DefaultPrettyPrinter defaultPrettyPrinter0 = new DefaultPrettyPrinter();
        DefaultPrettyPrinter defaultPrettyPrinter1 = defaultPrettyPrinter0.withObjectIndenter((DefaultPrettyPrinter.Indenter) null);
    }

    @Test(timeout = 4000)
    public void test4648() throws Throwable {
        DefaultPrettyPrinter defaultPrettyPrinter0 = new DefaultPrettyPrinter();
        defaultPrettyPrinter0.indentArraysWith((DefaultPrettyPrinter.Indenter) null);
        DefaultPrettyPrinter defaultPrettyPrinter1 = defaultPrettyPrinter0.withArrayIndenter((DefaultPrettyPrinter.Indenter) null);
    }

    @Test(timeout = 4000)
    public void test4749() throws Throwable {
        DefaultPrettyPrinter defaultPrettyPrinter0 = new DefaultPrettyPrinter();
        DefaultPrettyPrinter defaultPrettyPrinter1 = defaultPrettyPrinter0.withArrayIndenter((DefaultPrettyPrinter.Indenter) null);
    }

    @Test(timeout = 4000)
    public void test4850() throws Throwable {
        DefaultPrettyPrinter defaultPrettyPrinter0 = new DefaultPrettyPrinter();
        defaultPrettyPrinter0.indentObjectsWith((DefaultPrettyPrinter.Indenter) null);
    }

    @Test(timeout = 4000)
    public void test4951() throws Throwable {
        DefaultPrettyPrinter defaultPrettyPrinter0 = new DefaultPrettyPrinter();
        DefaultPrettyPrinter.FixedSpaceIndenter defaultPrettyPrinter_FixedSpaceIndenter0 = new DefaultPrettyPrinter.FixedSpaceIndenter();
        defaultPrettyPrinter0.indentArraysWith(defaultPrettyPrinter_FixedSpaceIndenter0);
        defaultPrettyPrinter_FixedSpaceIndenter0.isInline();
    }

    @Test(timeout = 4000)
    public void test5052() throws Throwable {
        DefaultPrettyPrinter defaultPrettyPrinter0 = new DefaultPrettyPrinter("\n");
        DefaultPrettyPrinter defaultPrettyPrinter1 = defaultPrettyPrinter0.withRootSeparator((String) null);
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        Object object0 = new Object();
        IOContext iOContext0 = new IOContext(bufferRecycler0, object0, false);
        MockPrintStream mockPrintStream0 = new MockPrintStream("\n");
        UTF8JsonGenerator uTF8JsonGenerator0 = new UTF8JsonGenerator(iOContext0, 0, (ObjectCodec) null, mockPrintStream0);
        defaultPrettyPrinter1.writeRootValueSeparator(uTF8JsonGenerator0);
    }

    @Test(timeout = 4000)
    public void test5053() throws Throwable {
        DefaultPrettyPrinter defaultPrettyPrinter0 = new DefaultPrettyPrinter("\n");
        DefaultPrettyPrinter defaultPrettyPrinter1 = defaultPrettyPrinter0.withRootSeparator((String) null);
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        Object object0 = new Object();
        IOContext iOContext0 = new IOContext(bufferRecycler0, object0, false);
        MockPrintStream mockPrintStream0 = new MockPrintStream("\n");
        UTF8JsonGenerator uTF8JsonGenerator0 = new UTF8JsonGenerator(iOContext0, 0, (ObjectCodec) null, mockPrintStream0);
        defaultPrettyPrinter1.writeRootValueSeparator(uTF8JsonGenerator0);
        uTF8JsonGenerator0.getOutputBuffered();
    }

    @Test(timeout = 4000)
    public void test5154() throws Throwable {
        DefaultPrettyPrinter defaultPrettyPrinter0 = new DefaultPrettyPrinter((String) null);
    }

    @Test(timeout = 4000)
    public void test5255() throws Throwable {
        DefaultPrettyPrinter defaultPrettyPrinter0 = new DefaultPrettyPrinter("com.fasterxml.jackson.core.util.DefaultPrettyPrinter$NopIndenter");
        DefaultPrettyPrinter defaultPrettyPrinter1 = defaultPrettyPrinter0.withRootSeparator("");
    }

    @Test(timeout = 4000)
    public void test5356() throws Throwable {
        DefaultPrettyPrinter defaultPrettyPrinter0 = new DefaultPrettyPrinter();
        DefaultPrettyPrinter defaultPrettyPrinter1 = defaultPrettyPrinter0.withSpacesInObjectEntries();
    }

    @Test(timeout = 4000)
    public void test5457() throws Throwable {
        DefaultPrettyPrinter defaultPrettyPrinter0 = new DefaultPrettyPrinter();
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        Object object0 = new Object();
        IOContext iOContext0 = new IOContext(bufferRecycler0, object0, true);
        ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream(3);
        byte[] byteArray0 = new byte[9];
        UTF8JsonGenerator uTF8JsonGenerator0 = new UTF8JsonGenerator(iOContext0, 1, (ObjectCodec) null, byteArrayOutputStream0, byteArray0, 0, true);
        JsonGeneratorDelegate jsonGeneratorDelegate0 = new JsonGeneratorDelegate(uTF8JsonGenerator0, true);
        defaultPrettyPrinter0.beforeObjectEntries(jsonGeneratorDelegate0);
        jsonGeneratorDelegate0.getOutputBuffered();
    }

    @Test(timeout = 4000)
    public void test5458() throws Throwable {
        DefaultPrettyPrinter defaultPrettyPrinter0 = new DefaultPrettyPrinter();
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        Object object0 = new Object();
        IOContext iOContext0 = new IOContext(bufferRecycler0, object0, true);
        ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream(3);
        byte[] byteArray0 = new byte[9];
        UTF8JsonGenerator uTF8JsonGenerator0 = new UTF8JsonGenerator(iOContext0, 1, (ObjectCodec) null, byteArrayOutputStream0, byteArray0, 0, true);
        JsonGeneratorDelegate jsonGeneratorDelegate0 = new JsonGeneratorDelegate(uTF8JsonGenerator0, true);
        defaultPrettyPrinter0.beforeObjectEntries(jsonGeneratorDelegate0);
    }

    @Test(timeout = 4000)
    public void test5559() throws Throwable {
        DefaultPrettyPrinter defaultPrettyPrinter0 = new DefaultPrettyPrinter();
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, "=hhP'3C", false);
        UTF8JsonGenerator uTF8JsonGenerator0 = new UTF8JsonGenerator(iOContext0, 23, (ObjectCodec) null, (OutputStream) null);
        defaultPrettyPrinter0.beforeArrayValues(uTF8JsonGenerator0);
        uTF8JsonGenerator0.canWriteFormattedNumbers();
    }

    @Test(timeout = 4000)
    public void test5660() throws Throwable {
        DefaultPrettyPrinter defaultPrettyPrinter0 = new DefaultPrettyPrinter();
        DefaultPrettyPrinter defaultPrettyPrinter1 = defaultPrettyPrinter0.createInstance();
    }

    @Test(timeout = 4000)
    public void test5761() throws Throwable {
        DefaultPrettyPrinter defaultPrettyPrinter0 = new DefaultPrettyPrinter();
        DefaultPrettyPrinter.FixedSpaceIndenter defaultPrettyPrinter_FixedSpaceIndenter0 = new DefaultPrettyPrinter.FixedSpaceIndenter();
        BufferRecycler bufferRecycler0 = new BufferRecycler(47, 1144);
        IOContext iOContext0 = new IOContext(bufferRecycler0, defaultPrettyPrinter0, false);
        ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream(1144);
        defaultPrettyPrinter0.indentObjectsWith(defaultPrettyPrinter_FixedSpaceIndenter0);
        UTF8JsonGenerator uTF8JsonGenerator0 = new UTF8JsonGenerator(iOContext0, 1703, (ObjectCodec) null, byteArrayOutputStream0);
        defaultPrettyPrinter0.writeStartObject(uTF8JsonGenerator0);
        uTF8JsonGenerator0.canWriteObjectId();
    }

    @Test(timeout = 4000)
    public void test5862() throws Throwable {
        DefaultPrettyPrinter.FixedSpaceIndenter defaultPrettyPrinter_FixedSpaceIndenter0 = new DefaultPrettyPrinter.FixedSpaceIndenter();
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        Object object0 = new Object();
        IOContext iOContext0 = new IOContext(bufferRecycler0, object0, false);
        StringWriter stringWriter0 = new StringWriter();
        WriterBasedJsonGenerator writerBasedJsonGenerator0 = new WriterBasedJsonGenerator(iOContext0, 1, (ObjectCodec) null, stringWriter0);
        JsonGeneratorDelegate jsonGeneratorDelegate0 = new JsonGeneratorDelegate(writerBasedJsonGenerator0, false);
        defaultPrettyPrinter_FixedSpaceIndenter0.writeIndentation(jsonGeneratorDelegate0, 0);
        jsonGeneratorDelegate0.canWriteFormattedNumbers();
    }

    @Test(timeout = 4000)
    public void test5963() throws Throwable {
        DefaultPrettyPrinter defaultPrettyPrinter0 = new DefaultPrettyPrinter();
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        IOContext iOContext0 = new IOContext(bufferRecycler0, defaultPrettyPrinter0, false);
        StringWriter stringWriter0 = new StringWriter(3);
        WriterBasedJsonGenerator writerBasedJsonGenerator0 = new WriterBasedJsonGenerator(iOContext0, 0, (ObjectCodec) null, stringWriter0);
        defaultPrettyPrinter0.writeArrayValueSeparator(writerBasedJsonGenerator0);
    }

    @Test(timeout = 4000)
    public void test6064() throws Throwable {
        DefaultPrettyPrinter.NopIndenter defaultPrettyPrinter_NopIndenter0 = new DefaultPrettyPrinter.NopIndenter();
        defaultPrettyPrinter_NopIndenter0.writeIndentation((JsonGenerator) null, 3610);
        defaultPrettyPrinter_NopIndenter0.isInline();
    }

    @Test(timeout = 4000)
    public void test6165() throws Throwable {
        DefaultPrettyPrinter defaultPrettyPrinter0 = new DefaultPrettyPrinter();
        BufferRecycler bufferRecycler0 = new BufferRecycler();
        FileDescriptor fileDescriptor0 = new FileDescriptor();
        MockFileInputStream mockFileInputStream0 = new MockFileInputStream(fileDescriptor0);
        IOContext iOContext0 = new IOContext(bufferRecycler0, mockFileInputStream0, false);
        ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream();
        byte[] byteArray0 = new byte[4];
        defaultPrettyPrinter0.indentArraysWith((DefaultPrettyPrinter.Indenter) null);
        UTF8JsonGenerator uTF8JsonGenerator0 = new UTF8JsonGenerator(iOContext0, (-2295), (ObjectCodec) null, byteArrayOutputStream0, byteArray0, (-4221), true);
        defaultPrettyPrinter0.writeEndArray(uTF8JsonGenerator0, 0);
    }
}
