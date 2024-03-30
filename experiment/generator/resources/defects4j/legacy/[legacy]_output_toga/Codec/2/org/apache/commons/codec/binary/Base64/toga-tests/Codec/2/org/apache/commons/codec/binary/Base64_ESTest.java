/*
 * This file was automatically generated by EvoSuite
 * Wed Oct 11 03:18:53 GMT 2023
 */
package org.apache.commons.codec.binary;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.math.BigInteger;
import org.apache.commons.codec.binary.Base64;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class Base64_ESTest extends Base64_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        byte[] byteArray0 = new byte[8];
        byteArray0[2] = (byte) (-30);
        BigInteger bigInteger0 = new BigInteger(byteArray0);
        Base64.encodeInteger(bigInteger0);
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        byte[] byteArray0 = new byte[4];
        byteArray0[2] = (byte) 54;
        byte[] byteArray1 = Base64.discardNonBase64(byteArray0);
    }

    @Test(timeout = 4000)
    public void test012() throws Throwable {
        byte[] byteArray0 = new byte[4];
        byteArray0[2] = (byte) 54;
        byte[] byteArray1 = Base64.discardNonBase64(byteArray0);
    }

    @Test(timeout = 4000)
    public void test023() throws Throwable {
        byte[] byteArray0 = new byte[2];
        byteArray0[1] = (byte) 123;
        Base64.decodeBase64(byteArray0);
    }

    @Test(timeout = 4000)
    public void test034() throws Throwable {
        byte[] byteArray0 = new byte[3];
        Base64.encodeBase64Chunked(byteArray0);
    }

    @Test(timeout = 4000)
    public void test045() throws Throwable {
        Base64 base64_0 = null;
        base64_0 = new Base64(1);
    }

    @Test(timeout = 4000)
    public void test056() throws Throwable {
        byte[] byteArray0 = new byte[4];
        byteArray0[0] = (byte) (-67);
        BigInteger bigInteger0 = new BigInteger(byteArray0);
        Base64.encodeInteger(bigInteger0);
    }

    @Test(timeout = 4000)
    public void test067() throws Throwable {
        Base64 base64_0 = null;
        base64_0 = new Base64();
    }

    @Test(timeout = 4000)
    public void test078() throws Throwable {
        BigInteger bigInteger0 = BigInteger.TEN;
        byte[] byteArray0 = Base64.toIntegerBytes(bigInteger0);
    }

    @Test(timeout = 4000)
    public void test089() throws Throwable {
        BigInteger bigInteger0 = BigInteger.ZERO;
        byte[] byteArray0 = Base64.toIntegerBytes(bigInteger0);
    }

    @Test(timeout = 4000)
    public void test0910() throws Throwable {
        byte[] byteArray0 = Base64.encodeBase64URLSafe((byte[]) null);
        assertNotNull(byteArray0);
    }

    @Test(timeout = 4000)
    public void test1011() throws Throwable {
        BigInteger bigInteger0 = BigInteger.ZERO;
        byte[] byteArray0 = Base64.encodeInteger(bigInteger0);
        byte[] byteArray1 = Base64.encodeBase64(byteArray0);
        byte[] byteArray2 = Base64.encodeBase64URLSafe(byteArray1);
    }

    @Test(timeout = 4000)
    public void test1112() throws Throwable {
        byte[] byteArray0 = Base64.encodeBase64((byte[]) null, true);
    }

    @Test(timeout = 4000)
    public void test1213() throws Throwable {
        byte[] byteArray0 = new byte[0];
        byte[] byteArray1 = Base64.encodeBase64(byteArray0, false);
    }

    @Test(timeout = 4000)
    public void test1314() throws Throwable {
        byte[] byteArray0 = Base64.encodeBase64((byte[]) null);
    }

    @Test(timeout = 4000)
    public void test1415() throws Throwable {
        Base64 base64_0 = null;
        base64_0 = new Base64(87);
    }

    @Test(timeout = 4000)
    public void test1516() throws Throwable {
        BigInteger bigInteger0 = BigInteger.ZERO;
        byte[] byteArray0 = Base64.encodeInteger(bigInteger0);
        byte[] byteArray1 = Base64.discardWhitespace(byteArray0);
    }

    @Test(timeout = 4000)
    public void test1617() throws Throwable {
        byte[] byteArray0 = new byte[3];
        byteArray0[0] = (byte) 95;
        byteArray0[1] = (byte) 95;
        byteArray0[2] = (byte) 95;
        Base64.decodeInteger(byteArray0);
    }

    @Test(timeout = 4000)
    public void test1718() throws Throwable {
        byte[] byteArray0 = new byte[4];
        byteArray0[0] = (byte) 80;
        byteArray0[1] = (byte) 72;
        Base64.decodeInteger(byteArray0);
    }

    @Test(timeout = 4000)
    public void test1819() throws Throwable {
        byte[] byteArray0 = new byte[4];
        Base64.encodeBase64(byteArray0, true);
    }

    @Test(timeout = 4000)
    public void test1920() throws Throwable {
        Base64 base64_0 = null;
        base64_0 = new Base64(false);
    }

    @Test(timeout = 4000)
    public void test2021() throws Throwable {
        Base64.toIntegerBytes((BigInteger) null);
    }

    @Test(timeout = 4000)
    public void test2122() throws Throwable {
        byte[] byteArray0 = new byte[4];
        Base64 base64_0 = null;
        base64_0 = new Base64(0);
    }

    @Test(timeout = 4000)
    public void test2223() throws Throwable {
        Base64.isArrayByteBase64((byte[]) null);
    }

    @Test(timeout = 4000)
    public void test2324() throws Throwable {
        Base64 base64_0 = null;
        base64_0 = new Base64(true);
    }

    @Test(timeout = 4000)
    public void test2425() throws Throwable {
        Base64.discardWhitespace((byte[]) null);
    }

    @Test(timeout = 4000)
    public void test2526() throws Throwable {
        Base64.discardNonBase64((byte[]) null);
    }

    @Test(timeout = 4000)
    public void test2627() throws Throwable {
        Base64.decodeInteger((byte[]) null);
    }

    @Test(timeout = 4000)
    public void test2728() throws Throwable {
        Base64 base64_0 = null;
        base64_0 = new Base64(0, (byte[]) null, false);
    }

    @Test(timeout = 4000)
    public void test2829() throws Throwable {
        Base64 base64_0 = null;
        base64_0 = new Base64(0, (byte[]) null);
    }

    @Test(timeout = 4000)
    public void test2930() throws Throwable {
        BigInteger bigInteger0 = BigInteger.ZERO;
        byte[] byteArray0 = Base64.encodeInteger(bigInteger0);
        byte[] byteArray1 = Base64.decodeBase64(byteArray0);
    }

    @Test(timeout = 4000)
    public void test3031() throws Throwable {
        byte[] byteArray0 = Base64.decodeBase64((byte[]) null);
    }

    @Test(timeout = 4000)
    public void test3132() throws Throwable {
        byte[] byteArray0 = new byte[3];
        Base64.encodeBase64(byteArray0, true, true);
    }

    @Test(timeout = 4000)
    public void test3233() throws Throwable {
        byte[] byteArray0 = Base64.encodeBase64((byte[]) null, false, false);
    }

    @Test(timeout = 4000)
    public void test3334() throws Throwable {
        BigInteger bigInteger0 = BigInteger.ZERO;
        byte[] byteArray0 = Base64.encodeInteger(bigInteger0);
        byte[] byteArray1 = Base64.encodeBase64(byteArray0, false, false);
    }

    @Test(timeout = 4000)
    public void test3435() throws Throwable {
        boolean boolean0 = Base64.isBase64((byte) 117);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test3536() throws Throwable {
        boolean boolean0 = Base64.isBase64((byte) (-63));
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test3637() throws Throwable {
        boolean boolean0 = Base64.isBase64((byte) 123);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test3738() throws Throwable {
        boolean boolean0 = Base64.isBase64((byte) 4);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test3839() throws Throwable {
        byte[] byteArray0 = new byte[7];
        Base64 base64_0 = new Base64((-160), byteArray0, true);
        base64_0.encode(byteArray0, (int) (byte) 0, (int) (byte) 1);
        base64_0.decode(byteArray0, (int) (byte) 102, (-160));
        base64_0.isUrlSafe();
        assertTrue(base64_0.isUrlSafe());
    }

    @Test(timeout = 4000)
    public void test3940() throws Throwable {
        byte[] byteArray0 = new byte[7];
        Base64.encodeBase64(byteArray0, true);
    }

    @Test(timeout = 4000)
    public void test4041() throws Throwable {
        byte[] byteArray0 = new byte[26];
        byteArray0[4] = (byte) (-10);
        Base64 base64_0 = null;
        base64_0 = new Base64(true);
    }

    @Test(timeout = 4000)
    public void test4142() throws Throwable {
        byte[] byteArray0 = new byte[7];
        byteArray0[0] = (byte) 124;
        Base64 base64_0 = new Base64((-160), byteArray0, true);
        base64_0.decode(byteArray0, 0, (int) (byte) 124);
    }

    @Test(timeout = 4000)
    public void test4243() throws Throwable {
        Base64 base64_0 = null;
        base64_0 = new Base64(234);
    }

    @Test(timeout = 4000)
    public void test4344() throws Throwable {
        byte[] byteArray0 = new byte[1];
        Base64 base64_0 = new Base64((-2), byteArray0, true);
        Base64.encodeBase64(byteArray0, true, true);
    }

    @Test(timeout = 4000)
    public void test4445() throws Throwable {
        byte[] byteArray0 = new byte[2];
        Base64 base64_0 = null;
        base64_0 = new Base64((byte) 0);
    }

    @Test(timeout = 4000)
    public void test4546() throws Throwable {
        byte[] byteArray0 = new byte[1];
        Base64.encodeBase64(byteArray0, true, false);
    }

    @Test(timeout = 4000)
    public void test4647() throws Throwable {
        byte[] byteArray0 = new byte[3];
        Base64 base64_0 = new Base64(2445, byteArray0, true);
        boolean boolean0 = base64_0.isUrlSafe();
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test4748() throws Throwable {
        Base64 base64_0 = null;
        base64_0 = new Base64(1471);
    }

    @Test(timeout = 4000)
    public void test4849() throws Throwable {
        byte[] byteArray0 = new byte[0];
        Base64 base64_0 = new Base64((-1), byteArray0, false);
        base64_0.isUrlSafe();
        assertTrue(base64_0.isUrlSafe());
    }

    @Test(timeout = 4000)
    public void test4950() throws Throwable {
        byte[] byteArray0 = new byte[3];
        Base64.encodeBase64(byteArray0, false, false);
    }

    @Test(timeout = 4000)
    public void test5051() throws Throwable {
        byte[] byteArray0 = new byte[3];
        Base64 base64_0 = new Base64((byte) 0, byteArray0);
        base64_0.isUrlSafe();
        assertFalse(base64_0.isUrlSafe());
    }

    @Test(timeout = 4000)
    public void test5152() throws Throwable {
        Base64.encodeInteger((BigInteger) null);
    }

    @Test(timeout = 4000)
    public void test5253() throws Throwable {
        byte[] byteArray0 = new byte[2];
        byteArray0[0] = (byte) 32;
        boolean boolean0 = Base64.isArrayByteBase64(byteArray0);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test5354() throws Throwable {
        byte[] byteArray0 = new byte[7];
        byteArray0[0] = (byte) 9;
        boolean boolean0 = Base64.isArrayByteBase64(byteArray0);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test5455() throws Throwable {
        byte[] byteArray0 = new byte[3];
        byteArray0[1] = (byte) 32;
        byte[] byteArray1 = Base64.discardWhitespace(byteArray0);
    }

    @Test(timeout = 4000)
    public void test5456() throws Throwable {
        byte[] byteArray0 = new byte[3];
        byteArray0[1] = (byte) 32;
        byte[] byteArray1 = Base64.discardWhitespace(byteArray0);
    }

    @Test(timeout = 4000)
    public void test5457() throws Throwable {
        byte[] byteArray0 = new byte[3];
        byteArray0[1] = (byte) 32;
        byte[] byteArray1 = Base64.discardWhitespace(byteArray0);
    }

    @Test(timeout = 4000)
    public void test5558() throws Throwable {
        byte[] byteArray0 = new byte[2];
        Base64.encodeBase64Chunked(byteArray0);
    }

    @Test(timeout = 4000)
    public void test5659() throws Throwable {
        byte[] byteArray0 = new byte[7];
        byteArray0[3] = (byte) 9;
        byte[] byteArray1 = Base64.discardWhitespace(byteArray0);
    }

    @Test(timeout = 4000)
    public void test5660() throws Throwable {
        byte[] byteArray0 = new byte[7];
        byteArray0[3] = (byte) 9;
        byte[] byteArray1 = Base64.discardWhitespace(byteArray0);
    }

    @Test(timeout = 4000)
    public void test5661() throws Throwable {
        byte[] byteArray0 = new byte[7];
        byteArray0[3] = (byte) 9;
        byte[] byteArray1 = Base64.discardWhitespace(byteArray0);
    }

    @Test(timeout = 4000)
    public void test5762() throws Throwable {
        Base64 base64_0 = null;
        base64_0 = new Base64((-46));
    }

    @Test(timeout = 4000)
    public void test5863() throws Throwable {
        byte[] byteArray0 = new byte[7];
        Base64 base64_0 = new Base64((-160), byteArray0, true);
        Base64.encodeBase64(byteArray0, true, true);
    }

    @Test(timeout = 4000)
    public void test5964() throws Throwable {
        byte[] byteArray0 = Base64.encodeBase64Chunked((byte[]) null);
        assertNotNull(byteArray0);
    }

    @Test(timeout = 4000)
    public void test6065() throws Throwable {
        Base64 base64_0 = null;
        base64_0 = new Base64(97);
    }

    @Test(timeout = 4000)
    public void test6166() throws Throwable {
        byte[] byteArray0 = new byte[26];
        Base64.encodeBase64Chunked(byteArray0);
    }

    @Test(timeout = 4000)
    public void test6267() throws Throwable {
        byte[] byteArray0 = new byte[5];
        byteArray0[3] = (byte) 127;
        byte[] byteArray1 = Base64.discardNonBase64(byteArray0);
    }

    @Test(timeout = 4000)
    public void test6368() throws Throwable {
        byte[] byteArray0 = new byte[8];
        byteArray0[4] = (byte) 105;
        Base64.decodeInteger(byteArray0);
    }

    @Test(timeout = 4000)
    public void test6469() throws Throwable {
        BigInteger bigInteger0 = BigInteger.ZERO;
        Base64.encodeInteger(bigInteger0);
        Base64 base64_0 = null;
        base64_0 = new Base64(2247);
    }

    @Test(timeout = 4000)
    public void test6570() throws Throwable {
        Base64 base64_0 = null;
        base64_0 = new Base64(0);
    }

    @Test(timeout = 4000)
    public void test6671() throws Throwable {
        Base64 base64_0 = null;
        base64_0 = new Base64((-3580));
    }

    @Test(timeout = 4000)
    public void test6772() throws Throwable {
        byte[] byteArray0 = new byte[9];
        byteArray0[6] = (byte) 101;
        Base64 base64_0 = null;
        base64_0 = new Base64(716, byteArray0);
    }

    @Test(timeout = 4000)
    public void test6873() throws Throwable {
        byte[] byteArray0 = new byte[4];
        Base64.encodeBase64(byteArray0);
    }

    @Test(timeout = 4000)
    public void test6974() throws Throwable {
        byte[] byteArray0 = new byte[1];
        Base64.encodeBase64URLSafe(byteArray0);
    }
}
