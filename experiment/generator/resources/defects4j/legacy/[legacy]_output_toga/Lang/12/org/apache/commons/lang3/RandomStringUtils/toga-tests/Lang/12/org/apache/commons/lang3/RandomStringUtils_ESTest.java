/*
 * This file was automatically generated by EvoSuite
 * Mon Oct 16 01:12:53 GMT 2023
 */
package org.apache.commons.lang3;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.util.Random;
import org.apache.commons.lang3.RandomStringUtils;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.mock.java.util.MockRandom;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class RandomStringUtils_ESTest extends RandomStringUtils_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        int int0 = 123;
        char[] charArray0 = new char[0];
        RandomStringUtils.random(123, charArray0);
    }

    @Test(timeout = 4000)
    public void test021() throws Throwable {
        RandomStringUtils.randomAscii(0);
        RandomStringUtils.randomAlphanumeric(0);
        RandomStringUtils.randomAlphabetic(0);
        RandomStringUtils.randomAscii(0);
        char[] charArray0 = new char[6];
        charArray0[0] = '@';
        charArray0[1] = 't';
        charArray0[2] = 'l';
        charArray0[3] = 'f';
        charArray0[4] = '$';
        charArray0[5] = '1';
        RandomStringUtils.random(1, charArray0);
        RandomStringUtils.randomAlphanumeric(0);
        RandomStringUtils.random(0, (-1), 0, false, false, charArray0, (Random) null);
        RandomStringUtils.random(1, 0, 3375, false, true, charArray0, (Random) null);
    }

    @Test(timeout = 4000)
    public void test032() throws Throwable {
        int int0 = 822;
        int int1 = 1;
        int int2 = (-943);
        boolean boolean0 = true;
        char[] charArray0 = new char[6];
        charArray0[0] = 'a';
        charArray0[1] = '3';
        charArray0[2] = 'i';
        charArray0[3] = '%';
        charArray0[4] = 'l';
        charArray0[5] = 'C';
        RandomStringUtils.random(822, 1, (-943), true, true, charArray0);
        int int3 = (-222);
        RandomStringUtils.randomAscii((-222));
    }

    @Test(timeout = 4000)
    public void test043() throws Throwable {
        RandomStringUtils randomStringUtils0 = new RandomStringUtils();
        char[] charArray0 = new char[8];
        charArray0[0] = ' ';
        charArray0[1] = 'i';
        charArray0[2] = 'I';
        charArray0[3] = '\'';
        charArray0[4] = '(';
        charArray0[5] = 'B';
        charArray0[6] = ')';
        charArray0[7] = 'E';
        RandomStringUtils.random(128, (-1745), 0, true, true, charArray0, (Random) null);
    }

    @Test(timeout = 4000)
    public void test054() throws Throwable {
        RandomStringUtils.random(21, "pf1?dV6");
        RandomStringUtils.randomAscii(0);
        RandomStringUtils.random(21, "");
    }

    @Test(timeout = 4000)
    public void test065() throws Throwable {
        int int0 = 1059;
        boolean boolean0 = false;
        RandomStringUtils.random(1059, 1059, 1059, false, false);
    }

    @Test(timeout = 4000)
    public void test076() throws Throwable {
        char[] charArray0 = new char[6];
        charArray0[0] = '2';
        charArray0[1] = '&';
        charArray0[2] = '}';
        charArray0[3] = 'j';
        charArray0[4] = '>';
        charArray0[5] = '4';
        MockRandom mockRandom0 = new MockRandom((-4759));
        RandomStringUtils.random((-4759), 937, 0, true, true, charArray0, (Random) mockRandom0);
    }

    @Test(timeout = 4000)
    public void test097() throws Throwable {
        MockRandom mockRandom0 = new MockRandom();
        RandomStringUtils.random(57343, 57343, 57343, false, false, (char[]) null, (Random) mockRandom0);
    }

    @Test(timeout = 4000)
    public void test158() throws Throwable {
        int int0 = (-2531);
        RandomStringUtils.randomNumeric((-2531));
    }

    @Test(timeout = 4000)
    public void test169() throws Throwable {
        int int0 = 0;
        RandomStringUtils.random(0, 2528, (-266), false, false);
        RandomStringUtils.random(2528, "");
    }

    @Test(timeout = 4000)
    public void test1710() throws Throwable {
        RandomStringUtils.random((-190), "-o'u91%9~:ny|");
    }

    @Test(timeout = 4000)
    public void test1811() throws Throwable {
        char[] charArray0 = new char[1];
        charArray0[0] = '6';
        RandomStringUtils.random(1935, 1935, 0, true, true, charArray0);
    }

    @Test(timeout = 4000)
    public void test2112() throws Throwable {
        char[] charArray0 = new char[4];
        charArray0[0] = '6';
        charArray0[1] = 'u';
        charArray0[2] = 'j';
        charArray0[3] = 'e';
        RandomStringUtils.random(1, 1, 1, false, true, charArray0);
    }

    @Test(timeout = 4000)
    public void test2213() throws Throwable {
        int int0 = 0;
        boolean boolean0 = false;
        char[] charArray0 = new char[5];
        charArray0[0] = '(';
        charArray0[1] = '3';
        charArray0[2] = '9';
        charArray0[3] = '*';
        charArray0[4] = 'u';
        RandomStringUtils.random((-2237), 0, (-2237), false, false, charArray0);
    }

    @Test(timeout = 4000)
    public void test2414() throws Throwable {
        int int0 = 0;
        RandomStringUtils.random((-938), 0, (-938), true, true);
    }

    @Test(timeout = 4000)
    public void test2515() throws Throwable {
        int int0 = (-1);
        RandomStringUtils.random((-1));
    }

    @Test(timeout = 4000)
    public void test2616() throws Throwable {
        char[] charArray0 = new char[9];
        charArray0[0] = '~';
        charArray0[1] = '.';
        charArray0[2] = 'E';
        charArray0[3] = 's';
        charArray0[4] = 'J';
        charArray0[5] = '~';
        charArray0[6] = 'k';
        charArray0[7] = '[';
        charArray0[8] = 'q';
        RandomStringUtils.random((-4002), charArray0);
    }

    @Test(timeout = 4000)
    public void test2917() throws Throwable {
        int int0 = 0;
        RandomStringUtils.randomAlphanumeric(0);
        RandomStringUtils randomStringUtils0 = new RandomStringUtils();
        RandomStringUtils.randomAscii((-1207));
    }

    @Test(timeout = 4000)
    public void test3218() throws Throwable {
        char[] charArray0 = new char[7];
        charArray0[0] = '{';
        charArray0[1] = 'c';
        charArray0[2] = ' ';
        charArray0[3] = 'f';
        charArray0[4] = '7';
        charArray0[5] = '1';
        charArray0[6] = 'I';
        MockRandom mockRandom0 = new MockRandom(2807L);
        RandomStringUtils.random(713, 713, 254, false, false, charArray0, (Random) mockRandom0);
    }

    @Test(timeout = 4000)
    public void test3319() throws Throwable {
        int int0 = (-1134);
        RandomStringUtils.randomAlphanumeric((-1134));
    }

    @Test(timeout = 4000)
    public void test3520() throws Throwable {
        int int0 = (-563);
        boolean boolean0 = false;
        RandomStringUtils.random((-563), false, false);
    }

    @Test(timeout = 4000)
    public void test3721() throws Throwable {
        RandomStringUtils.randomAlphabetic((-417));
    }

    @Test(timeout = 4000)
    public void test4022() throws Throwable {
        boolean boolean0 = true;
        RandomStringUtils.random((-738), (-738), (-738), true, true);
    }

    @Test(timeout = 4000)
    public void test4123() throws Throwable {
        char[] charArray0 = new char[1];
        charArray0[0] = 'f';
        MockRandom mockRandom0 = new MockRandom((-1L));
        RandomStringUtils.random(1, 1, 1, true, false, charArray0, (Random) mockRandom0);
    }

    @Test(timeout = 4000)
    public void test4324() throws Throwable {
        RandomStringUtils.random((-1333), (String) null);
    }

    @Test(timeout = 4000)
    public void test4625() throws Throwable {
        int int0 = 0;
        boolean boolean0 = false;
        RandomStringUtils.random(0, (-2681), (-2426), false, false);
        int int1 = (-3004);
        RandomStringUtils.randomNumeric((-3004));
    }
}
