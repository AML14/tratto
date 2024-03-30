/*
 * This file was automatically generated by EvoSuite
 * Wed Oct 11 03:38:58 GMT 2023
 */
package org.apache.commons.codec.binary;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import org.apache.commons.codec.binary.StringUtils;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class StringUtils_ESTest extends StringUtils_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        byte[] byteArray0 = new byte[1];
        String string0 = StringUtils.newStringUtf8(byteArray0);
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        byte[] byteArray0 = StringUtils.getBytesUtf16("");
        String string0 = StringUtils.newStringUtf16Le(byteArray0);
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        byte[] byteArray0 = new byte[2];
        String string0 = StringUtils.newStringUtf16(byteArray0);
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        byte[] byteArray0 = StringUtils.getBytesUtf16Be("");
        String string0 = StringUtils.newStringUtf16(byteArray0);
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        byte[] byteArray0 = StringUtils.getBytesUtf16("gg^l/MDsc]j:vl~'1d");
        String string0 = StringUtils.newStringUsAscii(byteArray0);
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        byte[] byteArray0 = new byte[1];
        String string0 = StringUtils.newStringIso8859_1(byteArray0);
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        byte[] byteArray0 = StringUtils.getBytesUtf8((String) null);
    }

    @Test(timeout = 4000)
    public void test077() throws Throwable {
        byte[] byteArray0 = StringUtils.getBytesUtf16Le("US-ASCII");
    }

    @Test(timeout = 4000)
    public void test088() throws Throwable {
        byte[] byteArray0 = StringUtils.getBytesUtf16Le("");
        String string0 = StringUtils.newStringUsAscii(byteArray0);
    }

    @Test(timeout = 4000)
    public void test099() throws Throwable {
        byte[] byteArray0 = StringUtils.getBytesUtf16Be((String) null);
    }

    @Test(timeout = 4000)
    public void test1010() throws Throwable {
        byte[] byteArray0 = StringUtils.getBytesUtf16Be("f}#dr6sV057A");
    }

    @Test(timeout = 4000)
    public void test1111() throws Throwable {
        byte[] byteArray0 = StringUtils.getBytesUtf16((String) null);
    }

    @Test(timeout = 4000)
    public void test1212() throws Throwable {
        byte[] byteArray0 = StringUtils.getBytesUtf16("");
        String string0 = StringUtils.newString(byteArray0, "UTF-8");
    }

    @Test(timeout = 4000)
    public void test1313() throws Throwable {
        byte[] byteArray0 = StringUtils.getBytesUsAscii((String) null);
    }

    @Test(timeout = 4000)
    public void test1414() throws Throwable {
        byte[] byteArray0 = StringUtils.getBytesUsAscii("");
    }

    @Test(timeout = 4000)
    public void test1515() throws Throwable {
        byte[] byteArray0 = StringUtils.getBytesUnchecked("US-ASCII", "US-ASCII");
    }

    @Test(timeout = 4000)
    public void test1616() throws Throwable {
        byte[] byteArray0 = StringUtils.getBytesUnchecked("", "US-ASCII");
    }

    @Test(timeout = 4000)
    public void test1717() throws Throwable {
        byte[] byteArray0 = StringUtils.getBytesIso8859_1("`BdD[J=D");
    }

    @Test(timeout = 4000)
    public void test1818() throws Throwable {
        byte[] byteArray0 = StringUtils.getBytesIso8859_1("");
        String string0 = StringUtils.newStringUtf16Be(byteArray0);
    }

    @Test(timeout = 4000)
    public void test1919() throws Throwable {
        StringUtils.newStringUtf16Le((byte[]) null);
    }

    @Test(timeout = 4000)
    public void test2020() throws Throwable {
        StringUtils.newStringUtf16Be((byte[]) null);
    }

    @Test(timeout = 4000)
    public void test2121() throws Throwable {
        StringUtils.newStringIso8859_1((byte[]) null);
    }

    @Test(timeout = 4000)
    public void test2222() throws Throwable {
        byte[] byteArray0 = new byte[7];
        StringUtils.newString(byteArray0, (String) null);
    }

    @Test(timeout = 4000)
    public void test2323() throws Throwable {
        byte[] byteArray0 = StringUtils.getBytesUsAscii("B{@C0i5[3H");
        StringUtils.newString(byteArray0, "P7f0y+_QYCVHDSHt5Ej");
    }

    @Test(timeout = 4000)
    public void test2424() throws Throwable {
        StringUtils.getBytesUnchecked("\u00C8\u5BCC\uFF4C\uFFFD", (String) null);
    }

    @Test(timeout = 4000)
    public void test2525() throws Throwable {
        String string0 = StringUtils.newString((byte[]) null, (String) null);
    }

    @Test(timeout = 4000)
    public void test2626() throws Throwable {
        byte[] byteArray0 = StringUtils.getBytesUtf8("UTF-16LE");
        String string0 = StringUtils.newString(byteArray0, "UTF-16LE");
    }

    @Test(timeout = 4000)
    public void test2727() throws Throwable {
        String string0 = StringUtils.newStringUtf8((byte[]) null);
    }

    @Test(timeout = 4000)
    public void test2828() throws Throwable {
        byte[] byteArray0 = StringUtils.getBytesUnchecked((String) null, (String) null);
    }

    @Test(timeout = 4000)
    public void test2929() throws Throwable {
        byte[] byteArray0 = StringUtils.getBytesUtf16Be("");
        String string0 = StringUtils.newStringUtf8(byteArray0);
    }

    @Test(timeout = 4000)
    public void test3030() throws Throwable {
        byte[] byteArray0 = StringUtils.getBytesUtf8("");
    }

    @Test(timeout = 4000)
    public void test3131() throws Throwable {
        byte[] byteArray0 = StringUtils.getBytesIso8859_1((String) null);
    }

    @Test(timeout = 4000)
    public void test3232() throws Throwable {
        StringUtils stringUtils0 = new StringUtils();
    }

    @Test(timeout = 4000)
    public void test3333() throws Throwable {
        StringUtils.newStringUtf16((byte[]) null);
    }

    @Test(timeout = 4000)
    public void test3434() throws Throwable {
        byte[] byteArray0 = StringUtils.getBytesUtf16Le((String) null);
    }

    @Test(timeout = 4000)
    public void test3535() throws Throwable {
        byte[] byteArray0 = new byte[7];
        String string0 = StringUtils.newStringUtf16Le(byteArray0);
    }

    @Test(timeout = 4000)
    public void test3636() throws Throwable {
        byte[] byteArray0 = StringUtils.getBytesUsAscii("o@kJusIi|n");
        String string0 = StringUtils.newStringUtf16Be(byteArray0);
    }

    @Test(timeout = 4000)
    public void test3737() throws Throwable {
        StringUtils.newStringUsAscii((byte[]) null);
    }

    @Test(timeout = 4000)
    public void test3838() throws Throwable {
        StringUtils.getBytesUnchecked("]*'L#oc,5:]B-V'bp", "");
    }

    @Test(timeout = 4000)
    public void test3939() throws Throwable {
        byte[] byteArray0 = StringUtils.getBytesUtf16Be("");
        String string0 = StringUtils.newStringIso8859_1(byteArray0);
    }
}
