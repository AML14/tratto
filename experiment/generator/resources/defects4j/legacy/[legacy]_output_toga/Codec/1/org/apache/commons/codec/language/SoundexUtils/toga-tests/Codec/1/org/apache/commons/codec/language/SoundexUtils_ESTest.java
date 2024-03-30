/*
 * This file was automatically generated by EvoSuite
 * Mon Nov 20 00:53:07 GMT 2023
 */
package org.apache.commons.codec.language;

import org.junit.Test;
import static org.junit.Assert.*;
import org.apache.commons.codec.language.DoubleMetaphone;
import org.apache.commons.codec.language.SoundexUtils;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class SoundexUtils_ESTest extends SoundexUtils_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test10() throws Throwable {
        DoubleMetaphone doubleMetaphone0 = new DoubleMetaphone();
        int int0 = SoundexUtils.difference(doubleMetaphone0, "_}OLcxW09Vw<b:K~`|", "");
        assertEquals(0, int0);
    }

    @Test(timeout = 4000)
    public void test21() throws Throwable {
        String string0 = SoundexUtils.clean((String) null);
        assertNotNull(string0);
    }

    @Test(timeout = 4000)
    public void test32() throws Throwable {
        String string0 = SoundexUtils.clean("");
    }

    @Test(timeout = 4000)
    public void test43() throws Throwable {
        String string0 = SoundexUtils.clean(")Rl|!:5");
    }

    @Test(timeout = 4000)
    public void test54() throws Throwable {
        String string0 = SoundexUtils.clean("H");
    }

    @Test(timeout = 4000)
    public void test65() throws Throwable {
        DoubleMetaphone doubleMetaphone0 = new DoubleMetaphone();
        int int0 = SoundexUtils.difference(doubleMetaphone0, "", (String) null);
        assertEquals(0, int0);
    }

    @Test(timeout = 4000)
    public void test76() throws Throwable {
        DoubleMetaphone doubleMetaphone0 = new DoubleMetaphone();
        int int0 = SoundexUtils.difference(doubleMetaphone0, "ILLO", "pS{q6zGQ?+REZy+?Js");
    }

    @Test(timeout = 4000)
    public void test87() throws Throwable {
        int int0 = SoundexUtils.differenceEncoded("L", "L");
        assertEquals(0, int0);
    }
}
