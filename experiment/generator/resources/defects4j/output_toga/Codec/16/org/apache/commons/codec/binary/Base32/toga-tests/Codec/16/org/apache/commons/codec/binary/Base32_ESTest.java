/*
 * This file was automatically generated by EvoSuite
 * Wed Oct 11 03:46:09 GMT 2023
 */
package org.apache.commons.codec.binary;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.BaseNCodec;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class Base32_ESTest extends Base32_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        byte[] byteArray0 = new byte[2];
        byteArray0[0] = (byte) 91;
        Base32 base32_0 = new Base32(7, byteArray0, false);
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        Base32 base32_0 = new Base32(1633);
        Object object0 = base32_0.decode((Object) "JZ");
        Object object1 = base32_0.encode(object0);
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        Base32 base32_0 = new Base32(true, (byte) 0);
        BaseNCodec.Context baseNCodec_Context0 = new BaseNCodec.Context();
        baseNCodec_Context0.modulus = (int) (byte) 0;
        baseNCodec_Context0.modulus = (-1098);
        base32_0.encode((byte[]) null, (-1098), (-1803), baseNCodec_Context0);
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        Base32 base32_0 = new Base32((byte) 0);
        byte[] byteArray0 = new byte[2];
        BaseNCodec.Context baseNCodec_Context0 = new BaseNCodec.Context();
        base32_0.encode(byteArray0, (-457), (int) (byte) 0, baseNCodec_Context0);
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        Base32 base32_0 = new Base32(true, (byte) 0);
        byte[] byteArray0 = new byte[6];
        byte[] byteArray1 = base32_0.encode(byteArray0);
        BaseNCodec.Context baseNCodec_Context0 = new BaseNCodec.Context();
        baseNCodec_Context0.lbitWorkArea = (long) (-2);
        base32_0.decode(byteArray1, 0, 564, baseNCodec_Context0);
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        Base32 base32_0 = new Base32((byte) 0);
        Object object0 = base32_0.decode((Object) "Context[buffer=null, currentLinePos=0, eof=false, ibitWorkArea=0, lbitWorkArea=0, modulus=0, pos=0, readPos=0]");
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        Base32 base32_0 = new Base32(true, (byte) 0);
        byte[] byteArray0 = new byte[6];
        byte[] byteArray1 = base32_0.encode(byteArray0);
        BaseNCodec.Context baseNCodec_Context0 = new BaseNCodec.Context();
        baseNCodec_Context0.modulus = (-47);
        base32_0.decode(byteArray1, 0, 564, baseNCodec_Context0);
    }

    @Test(timeout = 4000)
    public void test077() throws Throwable {
        Base32 base32_0 = new Base32();
        byte[] byteArray0 = new byte[3];
        base32_0.encode(byteArray0, 84, (-23), (BaseNCodec.Context) null);
    }

    @Test(timeout = 4000)
    public void test088() throws Throwable {
        Base32 base32_0 = new Base32(true);
        BaseNCodec.Context baseNCodec_Context0 = new BaseNCodec.Context();
        base32_0.decode((byte[]) null, 32, 32, baseNCodec_Context0);
    }

    @Test(timeout = 4000)
    public void test099() throws Throwable {
        Base32 base32_0 = new Base32();
        byte[] byteArray0 = new byte[5];
        BaseNCodec.Context baseNCodec_Context0 = new BaseNCodec.Context();
        base32_0.decode(byteArray0, (-98), 2685, baseNCodec_Context0);
    }

    @Test(timeout = 4000)
    public void test1010() throws Throwable {
        Base32 base32_0 = null;
        base32_0 = new Base32(true, (byte) 67);
    }

    @Test(timeout = 4000)
    public void test1111() throws Throwable {
        byte[] byteArray0 = new byte[3];
        byteArray0[2] = (byte) 70;
        Base32 base32_0 = null;
        base32_0 = new Base32(7, byteArray0);
    }

    @Test(timeout = 4000)
    public void test1212() throws Throwable {
        Base32 base32_0 = new Base32();
        boolean boolean0 = base32_0.isInAlphabet((byte) 98);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test1313() throws Throwable {
        byte[] byteArray0 = new byte[0];
        Base32 base32_0 = new Base32(30, byteArray0, true, (byte) 37);
        boolean boolean0 = base32_0.isInAlphabet((byte) 37);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test1414() throws Throwable {
        Base32 base32_0 = new Base32((byte) (-102));
        boolean boolean0 = base32_0.isInAlphabet((byte) (-102));
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test1515() throws Throwable {
        Base32 base32_0 = new Base32((byte) 0);
        boolean boolean0 = base32_0.isInAlphabet((byte) 86);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test1616() throws Throwable {
        byte[] byteArray0 = new byte[1];
        Base32 base32_0 = null;
        base32_0 = new Base32((-154), byteArray0, true, (byte) 10);
    }

    @Test(timeout = 4000)
    public void test1717() throws Throwable {
        byte[] byteArray0 = new byte[4];
        Base32 base32_0 = null;
        base32_0 = new Base32(69, byteArray0, true, (byte) 0);
    }

    @Test(timeout = 4000)
    public void test1818() throws Throwable {
        Base32 base32_0 = null;
        base32_0 = new Base32(393, (byte[]) null, false, (byte) 0);
    }

    @Test(timeout = 4000)
    public void test1919() throws Throwable {
        Base32 base32_0 = new Base32(0, (byte[]) null, false, (byte) 2);
        BaseNCodec.Context baseNCodec_Context0 = new BaseNCodec.Context();
        base32_0.decode((byte[]) null, 0, 0, baseNCodec_Context0);
    }

    @Test(timeout = 4000)
    public void test2020() throws Throwable {
        byte[] byteArray0 = new byte[1];
        Base32 base32_0 = new Base32(1603, byteArray0);
        boolean boolean0 = base32_0.isInAlphabet("R7^Y");
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test2121() throws Throwable {
        Base32 base32_0 = new Base32(61);
        byte[] byteArray0 = new byte[7];
        BaseNCodec.Context baseNCodec_Context0 = new BaseNCodec.Context();
        baseNCodec_Context0.currentLinePos = 61;
        base32_0.encode(byteArray0, 0, (int) (byte) 16, baseNCodec_Context0);
    }

    @Test(timeout = 4000)
    public void test2222() throws Throwable {
        Base32 base32_0 = new Base32(99);
        byte[] byteArray0 = new byte[4];
        byteArray0[1] = (byte) (-1);
        String string0 = base32_0.encodeAsString(byteArray0);
        assertNotNull(string0);
    }

    @Test(timeout = 4000)
    public void test2323() throws Throwable {
        Base32 base32_0 = new Base32(99);
        byte[] byteArray0 = new byte[8];
        String string0 = base32_0.encodeAsString(byteArray0);
        assertNotNull(string0);
    }

    @Test(timeout = 4000)
    public void test2424() throws Throwable {
        Base32 base32_0 = new Base32();
        byte[] byteArray0 = new byte[7];
        byte[] byteArray1 = base32_0.encode(byteArray0);
    }

    @Test(timeout = 4000)
    public void test2525() throws Throwable {
        Base32 base32_0 = new Base32(true, (byte) 0);
        Object object0 = base32_0.decode((Object) "&Bwo=2rauUB?");
    }

    @Test(timeout = 4000)
    public void test2626() throws Throwable {
        Base32 base32_0 = new Base32(true, (byte) (-117));
        byte[] byteArray0 = base32_0.decode("%=F)H`0");
        BaseNCodec.Context baseNCodec_Context0 = new BaseNCodec.Context();
        base32_0.encode(byteArray0, (int) (byte) (-117), (-11), baseNCodec_Context0);
    }

    @Test(timeout = 4000)
    public void test2728() throws Throwable {
        Base32 base32_0 = new Base32(99);
        byte[] byteArray0 = new byte[4];
        byteArray0[1] = (byte) (-1);
        byte[] byteArray1 = base32_0.decode(byteArray0);
    }

    @Test(timeout = 4000)
    public void test2829() throws Throwable {
        Base32 base32_0 = new Base32(true, (byte) 0);
        byte[] byteArray0 = new byte[6];
        BaseNCodec.Context baseNCodec_Context0 = new BaseNCodec.Context();
        base32_0.decode(byteArray0, 0, 76, baseNCodec_Context0);
        base32_0.encode(byteArray0, 0, (int) (byte) 0, baseNCodec_Context0);
    }

    @Test(timeout = 4000)
    public void test2930() throws Throwable {
        Base32 base32_0 = new Base32(340);
        BaseNCodec.Context baseNCodec_Context0 = new BaseNCodec.Context();
        byte[] byteArray0 = new byte[23];
        base32_0.encode(byteArray0, 340, (-876), baseNCodec_Context0);
        base32_0.decode(byteArray0, 340, (int) (byte) 53, baseNCodec_Context0);
    }

    @Test(timeout = 4000)
    public void test3031() throws Throwable {
        Base32 base32_0 = null;
        base32_0 = new Base32((byte) 13);
    }

    @Test(timeout = 4000)
    public void test3132() throws Throwable {
        byte[] byteArray0 = new byte[1];
        Base32 base32_0 = null;
        base32_0 = new Base32(1106, byteArray0, true, (byte) 48);
    }

    @Test(timeout = 4000)
    public void test3233() throws Throwable {
        byte[] byteArray0 = new byte[2];
        byteArray0[0] = (byte) 73;
        Base32 base32_0 = null;
        base32_0 = new Base32(1659, byteArray0, true);
    }

    @Test(timeout = 4000)
    public void test3334() throws Throwable {
        Base32 base32_0 = new Base32(true);
        byte[] byteArray0 = base32_0.decode("UTF-16LE");
    }

    @Test(timeout = 4000)
    public void test3436() throws Throwable {
        Base32 base32_0 = new Base32();
        byte[] byteArray0 = new byte[5];
        BaseNCodec.Context baseNCodec_Context0 = new BaseNCodec.Context();
        baseNCodec_Context0.modulus = 76;
        base32_0.decode(byteArray0, (-20), (-20), baseNCodec_Context0);
    }

    @Test(timeout = 4000)
    public void test3537() throws Throwable {
        Base32 base32_0 = new Base32(340);
        byte[] byteArray0 = base32_0.decode("8eTUas[3] BT");
    }

    @Test(timeout = 4000)
    public void test3639() throws Throwable {
        Base32 base32_0 = new Base32((byte) (-7));
        byte[] byteArray0 = base32_0.decode("H$_VMWBde]]oUy");
    }
}
