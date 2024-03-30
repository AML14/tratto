/*
 * This file was automatically generated by EvoSuite
 * Sun Nov 05 18:25:30 GMT 2023
 */
package org.apache.commons.compress.archivers.sevenz;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.util.zip.ZipException;
import org.apache.commons.compress.archivers.sevenz.Coder;
import org.apache.commons.compress.archivers.sevenz.Coders;
import org.apache.commons.compress.archivers.sevenz.SevenZMethod;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class Coders_ESTest extends Coders_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test010() throws Throwable {
        Coder coder0 = new Coder();
        Coders.LZMADecoder coders_LZMADecoder0 = new Coders.LZMADecoder();
        byte[] byteArray0 = new byte[6];
        coder0.properties = byteArray0;
        Coders.AES256SHA256Decoder coders_AES256SHA256Decoder0 = new Coders.AES256SHA256Decoder();
        InputStream inputStream0 = coders_AES256SHA256Decoder0.decode((InputStream) null, coder0, coder0.properties);
        InputStream inputStream1 = coders_AES256SHA256Decoder0.decode(inputStream0, coder0, byteArray0);
        coders_LZMADecoder0.decode(inputStream1, coder0, coder0.properties);
    }

    @Test(timeout = 4000)
    public void test021() throws Throwable {
        byte[] byteArray0 = new byte[1];
        SevenZMethod sevenZMethod0 = SevenZMethod.BZIP2;
        Coders.addEncoder((OutputStream) null, sevenZMethod0, byteArray0);
    }

    @Test(timeout = 4000)
    public void test032() throws Throwable {
        SevenZMethod sevenZMethod0 = SevenZMethod.DEFLATE;
        byte[] byteArray0 = new byte[1];
        Coders.addEncoder((OutputStream) null, sevenZMethod0, byteArray0);
    }

    @Test(timeout = 4000)
    public void test043() throws Throwable {
        byte[] byteArray0 = new byte[1];
        SevenZMethod sevenZMethod0 = SevenZMethod.COPY;
        OutputStream outputStream0 = Coders.addEncoder((OutputStream) null, sevenZMethod0, byteArray0);
    }

    @Test(timeout = 4000)
    public void test054() throws Throwable {
        Coder coder0 = new Coder();
        byte[] byteArray0 = new byte[1];
        coder0.decompressionMethodId = byteArray0;
        InputStream inputStream0 = Coders.addDecoder((InputStream) null, coder0, byteArray0);
    }

    @Test(timeout = 4000)
    public void test075() throws Throwable {
        SevenZMethod sevenZMethod0 = SevenZMethod.AES256SHA256;
        byte[] byteArray0 = new byte[0];
        Coders.addEncoder((OutputStream) null, sevenZMethod0, byteArray0);
    }

    @Test(timeout = 4000)
    public void test086() throws Throwable {
        byte[] byteArray0 = new byte[1];
        Coder coder0 = new Coder();
        Coders.addDecoder((InputStream) null, coder0, byteArray0);
    }

    @Test(timeout = 4000)
    public void test097() throws Throwable {
        byte[] byteArray0 = new byte[10];
        Coders.addEncoder((OutputStream) null, (SevenZMethod) null, byteArray0);
    }

    @Test(timeout = 4000)
    public void test108() throws Throwable {
        Coder coder0 = new Coder();
        byte[] byteArray0 = new byte[6];
        byteArray0[1] = (byte) (-40);
        coder0.properties = byteArray0;
        Coders.AES256SHA256Decoder coders_AES256SHA256Decoder0 = new Coders.AES256SHA256Decoder();
        InputStream inputStream0 = coders_AES256SHA256Decoder0.decode((InputStream) null, coder0, byteArray0);
        Coders.BZIP2Decoder coders_BZIP2Decoder0 = new Coders.BZIP2Decoder();
        coders_BZIP2Decoder0.decode(inputStream0, coder0, byteArray0);
    }

    @Test(timeout = 4000)
    public void test119() throws Throwable {
        Coder coder0 = new Coder();
        Coders.LZMADecoder coders_LZMADecoder0 = new Coders.LZMADecoder();
        byte[] byteArray0 = new byte[7];
        coder0.properties = byteArray0;
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(coder0.properties);
        Coders.DeflateDecoder coders_DeflateDecoder0 = new Coders.DeflateDecoder();
        InputStream inputStream0 = coders_DeflateDecoder0.decode(byteArrayInputStream0, coder0, byteArray0);
        coders_LZMADecoder0.decode(inputStream0, coder0, coder0.properties);
    }

    @Test(timeout = 4000)
    public void test1210() throws Throwable {
        byte[] byteArray0 = new byte[1];
        Coders.BZIP2Decoder coders_BZIP2Decoder0 = new Coders.BZIP2Decoder();
        Coders.DeflateDecoder coders_DeflateDecoder0 = new Coders.DeflateDecoder();
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0, 3, 3);
        PushbackInputStream pushbackInputStream0 = new PushbackInputStream(byteArrayInputStream0);
        Coder coder0 = new Coder();
        InputStream inputStream0 = coders_DeflateDecoder0.decode(pushbackInputStream0, coder0, byteArray0);
        coders_BZIP2Decoder0.decode(inputStream0, coder0, byteArray0);
    }
}
