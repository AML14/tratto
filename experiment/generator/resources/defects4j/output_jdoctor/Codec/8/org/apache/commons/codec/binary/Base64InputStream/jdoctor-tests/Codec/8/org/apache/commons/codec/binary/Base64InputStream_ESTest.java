/*
 * This file was automatically generated by EvoSuite
 * Sun Dec 31 04:28:39 GMT 2023
 */
package org.apache.commons.codec.binary;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.apache.commons.codec.binary.Base64InputStream;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class Base64InputStream_ESTest extends Base64InputStream_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        byte[] byteArray0 = new byte[3];
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0, 2142, 2142);
        Base64InputStream base64InputStream0 = new Base64InputStream(byteArrayInputStream0, true, 2142, byteArray0);
        boolean default0;
        default0 = base64InputStream0.markSupported();
        assertTrue(true ? default0 == false : true);
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        byte[] byteArray0 = new byte[9];
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0);
        Base64InputStream base64InputStream0 = new Base64InputStream(byteArrayInputStream0);
        int int0 = base64InputStream0.read();
        byteArrayInputStream0.available();
    }

    @Test(timeout = 4000)
    public void test012() throws Throwable {
        byte[] byteArray0 = new byte[9];
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0);
        Base64InputStream base64InputStream0 = new Base64InputStream(byteArrayInputStream0);
        int int0 = base64InputStream0.read();
    }

    @Test(timeout = 4000)
    public void test023() throws Throwable {
        Base64InputStream base64InputStream0 = new Base64InputStream((InputStream) null);
        boolean boolean0;
        boolean0 = base64InputStream0.markSupported();
        assertTrue(true ? boolean0 == false : true);
    }

    @Test(timeout = 4000)
    public void test034() throws Throwable {
        byte[] byteArray0 = new byte[3];
        byteArray0[1] = (byte) (-68);
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0, 1, (byte) 22);
        Base64InputStream base64InputStream0 = new Base64InputStream(byteArrayInputStream0, true);
        Base64InputStream base64InputStream1 = new Base64InputStream(base64InputStream0);
        int int0 = base64InputStream1.read();
        byteArrayInputStream0.available();
    }

    @Test(timeout = 4000)
    public void test035() throws Throwable {
        byte[] byteArray0 = new byte[3];
        byteArray0[1] = (byte) (-68);
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0, 1, (byte) 22);
        Base64InputStream base64InputStream0 = new Base64InputStream(byteArrayInputStream0, true);
        Base64InputStream base64InputStream1 = new Base64InputStream(base64InputStream0);
        int int0 = base64InputStream1.read();
    }

    @Test(timeout = 4000)
    public void test046() throws Throwable {
        byte[] byteArray0 = new byte[9];
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0);
        Base64InputStream base64InputStream0 = new Base64InputStream(byteArrayInputStream0, false);
        int default1;
        if ((byte[]) null == null) {
            try {
                default1 = base64InputStream0.read((byte[]) null, 256, 256);
                fail();
            } catch (java.lang.NullPointerException e) {
                // Successfully thrown exception
            }
        } else {
            default1 = base64InputStream0.read((byte[]) null, 256, 256);
        }
    }

    @Test(timeout = 4000)
    public void test057() throws Throwable {
        byte[] byteArray0 = new byte[3];
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0);
        Base64InputStream base64InputStream0 = new Base64InputStream(byteArrayInputStream0);
        int default2;
        if (byteArray0 == null) {
            try {
                default2 = base64InputStream0.read(byteArray0, (-39), (-39));
                fail();
            } catch (java.lang.NullPointerException e) {
                // Successfully thrown exception
            }
        } else {
            default2 = base64InputStream0.read(byteArray0, (-39), (-39));
        }
    }

    @Test(timeout = 4000)
    public void test068() throws Throwable {
        byte[] byteArray0 = new byte[9];
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0);
        Base64InputStream base64InputStream0 = new Base64InputStream(byteArrayInputStream0);
        int default3;
        if (byteArray0 == null) {
            try {
                default3 = base64InputStream0.read(byteArray0, 4, (-1));
                fail();
            } catch (java.lang.NullPointerException e) {
                // Successfully thrown exception
            }
        } else {
            default3 = base64InputStream0.read(byteArray0, 4, (-1));
        }
    }

    @Test(timeout = 4000)
    public void test079() throws Throwable {
        byte[] byteArray0 = new byte[6];
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0);
        Base64InputStream base64InputStream0 = new Base64InputStream(byteArrayInputStream0);
        int default4;
        if (byteArray0 == null) {
            try {
                default4 = base64InputStream0.read(byteArray0, 115, 115);
                fail();
            } catch (java.lang.NullPointerException e) {
                // Successfully thrown exception
            }
        } else {
            default4 = base64InputStream0.read(byteArray0, 115, 115);
        }
    }

    @Test(timeout = 4000)
    public void test0810() throws Throwable {
        byte[] byteArray0 = new byte[9];
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0);
        Base64InputStream base64InputStream0 = new Base64InputStream(byteArrayInputStream0);
        int default5;
        if (byteArray0 == null) {
            try {
                default5 = base64InputStream0.read(byteArray0, 5, 5);
                fail();
            } catch (java.lang.NullPointerException e) {
                // Successfully thrown exception
            }
        } else {
            default5 = base64InputStream0.read(byteArray0, 5, 5);
        }
    }

    @Test(timeout = 4000)
    public void test0911() throws Throwable {
        byte[] byteArray0 = new byte[3];
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0);
        Base64InputStream base64InputStream0 = new Base64InputStream(byteArrayInputStream0, true);
        int int0;
        if (byteArray0 == null) {
            try {
                int0 = base64InputStream0.read(byteArray0, (int) (byte) 0, (int) (byte) 0);
                fail();
            } catch (java.lang.NullPointerException e) {
                // Successfully thrown exception
            }
        } else {
            int0 = base64InputStream0.read(byteArray0, (int) (byte) 0, (int) (byte) 0);
        }
    }

    @Test(timeout = 4000)
    public void test1012() throws Throwable {
        byte[] byteArray0 = new byte[3];
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0);
        Base64InputStream base64InputStream0 = new Base64InputStream(byteArrayInputStream0, true);
        base64InputStream0.read();
        int int0 = base64InputStream0.read();
        byteArrayInputStream0.available();
    }

    @Test(timeout = 4000)
    public void test1013() throws Throwable {
        byte[] byteArray0 = new byte[3];
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0);
        Base64InputStream base64InputStream0 = new Base64InputStream(byteArrayInputStream0, true);
        base64InputStream0.read();
        int int0 = base64InputStream0.read();
    }

    @Test(timeout = 4000)
    public void test1114() throws Throwable {
        byte[] byteArray0 = new byte[3];
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0);
        Base64InputStream base64InputStream0 = new Base64InputStream(byteArrayInputStream0);
        int int0;
        if (byteArray0 == null) {
            try {
                int0 = base64InputStream0.read(byteArray0, 1, 1);
                fail();
            } catch (java.lang.NullPointerException e) {
                // Successfully thrown exception
            }
        } else {
            int0 = base64InputStream0.read(byteArray0, 1, 1);
        }
        byteArrayInputStream0.available();
    }

    @Test(timeout = 4000)
    public void test1115() throws Throwable {
        byte[] byteArray0 = new byte[3];
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0);
        Base64InputStream base64InputStream0 = new Base64InputStream(byteArrayInputStream0);
        int int0;
        if (byteArray0 == null) {
            try {
                int0 = base64InputStream0.read(byteArray0, 1, 1);
                fail();
            } catch (java.lang.NullPointerException e) {
                // Successfully thrown exception
            }
        } else {
            int0 = base64InputStream0.read(byteArray0, 1, 1);
        }
    }
}
