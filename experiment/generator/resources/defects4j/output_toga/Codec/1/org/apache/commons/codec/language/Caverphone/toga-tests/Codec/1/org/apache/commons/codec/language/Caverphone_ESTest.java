/*
 * This file was automatically generated by EvoSuite
 * Wed Oct 11 03:12:38 GMT 2023
 */
package org.apache.commons.codec.language;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import org.apache.commons.codec.language.Caverphone;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class Caverphone_ESTest extends Caverphone_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test00() throws Throwable {
        Caverphone caverphone0 = new Caverphone();
        boolean boolean0 = caverphone0.isCaverphoneEqual("1111111111", (String) null);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test11() throws Throwable {
        Caverphone caverphone0 = new Caverphone();
        String string0 = caverphone0.caverphone((String) null);
    }

    @Test(timeout = 4000)
    public void test22() throws Throwable {
        Caverphone caverphone0 = new Caverphone();
        String string0 = caverphone0.caverphone("");
    }

    @Test(timeout = 4000)
    public void test33() throws Throwable {
        Caverphone caverphone0 = new Caverphone();
        String string0 = caverphone0.caverphone("1111111111");
    }

    @Test(timeout = 4000)
    public void test44() throws Throwable {
        Caverphone caverphone0 = new Caverphone();
        Object object0 = new Object();
        caverphone0.encode(object0);
    }

    @Test(timeout = 4000)
    public void test55() throws Throwable {
        Caverphone caverphone0 = new Caverphone();
        Object object0 = caverphone0.encode((Object) "%8+~$W-j&`;+'ks");
        assertNotNull(object0);
    }

    @Test(timeout = 4000)
    public void test66() throws Throwable {
        Caverphone caverphone0 = new Caverphone();
        boolean boolean0 = caverphone0.isCaverphoneEqual((String) null, "%8+~$W-j&`;+'ks");
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test77() throws Throwable {
        Caverphone caverphone0 = new Caverphone();
        String string0 = caverphone0.encode("");
    }
}
