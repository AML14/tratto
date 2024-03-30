/*
 * This file was automatically generated by EvoSuite
 * Mon Oct 16 03:10:57 GMT 2023
 */
package org.apache.commons.lang;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import org.apache.commons.lang.WordUtils;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class WordUtils_ESTest extends WordUtils_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        String string0 = WordUtils.abbreviate("bOn0n39 ", 0, 2, ";<OWv#9lJIb");
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        String string0 = WordUtils.abbreviate(" ", (-741), 0, "j");
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        String string0 = WordUtils.abbreviate("T", 1, 1, "W wh~RG");
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        char[] charArray0 = new char[1];
        String string0 = WordUtils.capitalizeFully("4.0", charArray0);
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        String string0 = WordUtils.wrap("[f}Ht~ ;Vp;b", (-1328), "", true);
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        String string0 = WordUtils.uncapitalize((String) null);
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        String string0 = WordUtils.uncapitalize("");
    }

    @Test(timeout = 4000)
    public void test077() throws Throwable {
        String string0 = WordUtils.initials("bV,\"h", (char[]) null);
    }

    @Test(timeout = 4000)
    public void test088() throws Throwable {
        char[] charArray0 = new char[5];
        String string0 = WordUtils.initials((String) null, charArray0);
    }

    @Test(timeout = 4000)
    public void test099() throws Throwable {
        String string0 = WordUtils.initials("", (char[]) null);
    }

    @Test(timeout = 4000)
    public void test1010() throws Throwable {
        char[] charArray0 = new char[1];
        charArray0[0] = '9';
        String string0 = WordUtils.uncapitalize(" dPt8Aey9|^GZ~", charArray0);
    }

    @Test(timeout = 4000)
    public void test1111() throws Throwable {
        String string0 = WordUtils.uncapitalize("org.apache.commons.lang.WordUtils", (char[]) null);
    }

    @Test(timeout = 4000)
    public void test1212() throws Throwable {
        String string0 = WordUtils.capitalizeFully("", (char[]) null);
    }

    @Test(timeout = 4000)
    public void test1313() throws Throwable {
        char[] charArray0 = new char[3];
        String string0 = WordUtils.capitalizeFully((String) null, charArray0);
    }

    @Test(timeout = 4000)
    public void test1414() throws Throwable {
        String string0 = WordUtils.capitalizeFully(";", (char[]) null);
    }

    @Test(timeout = 4000)
    public void test1515() throws Throwable {
        char[] charArray0 = new char[2];
        String string0 = WordUtils.capitalize((String) null, charArray0);
    }

    @Test(timeout = 4000)
    public void test1616() throws Throwable {
        char[] charArray0 = new char[0];
        String string0 = WordUtils.capitalize("", charArray0);
    }

    @Test(timeout = 4000)
    public void test1717() throws Throwable {
        String string0 = WordUtils.capitalize(";", (char[]) null);
    }

    @Test(timeout = 4000)
    public void test1818() throws Throwable {
        String string0 = WordUtils.wrap("", (-2993), (String) null, true);
    }

    @Test(timeout = 4000)
    public void test1919() throws Throwable {
        String string0 = WordUtils.wrap((String) null, (-127), (String) null, false);
    }

    @Test(timeout = 4000)
    public void test2020() throws Throwable {
        String string0 = WordUtils.abbreviate("[#K.lmr7[", (-1), 0, "java.vendor");
    }

    @Test(timeout = 4000)
    public void test2121() throws Throwable {
        WordUtils.abbreviate("[d}`mD^S", 3961, (-2782), (String) null);
    }

    @Test(timeout = 4000)
    public void test2222() throws Throwable {
        String string0 = WordUtils.abbreviate("/dF*jrziFn8)mr", (-1), (-1), "/dF*jrziFn8)mr");
    }

    @Test(timeout = 4000)
    public void test2323() throws Throwable {
        String string0 = WordUtils.abbreviate("", (-127), (-127), "c-JXi_XW#n9X[eL1");
    }

    @Test(timeout = 4000)
    public void test2424() throws Throwable {
        String string0 = WordUtils.abbreviate((String) null, (-1), (-1), "");
    }

    @Test(timeout = 4000)
    public void test2525() throws Throwable {
        String string0 = WordUtils.abbreviate(" erOn.E", (-2850), 1520, "n");
    }

    @Test(timeout = 4000)
    public void test2627() throws Throwable {
        char[] charArray0 = new char[5];
        charArray0[1] = 'y';
        String string0 = WordUtils.initials("zy^?*=BxLN+>^&S\"@", charArray0);
    }

    @Test(timeout = 4000)
    public void test2728() throws Throwable {
        String string0 = WordUtils.initials("Teq#r\")5hk");
    }

    @Test(timeout = 4000)
    public void test2829() throws Throwable {
        char[] charArray0 = new char[0];
        String string0 = WordUtils.initials("TEq#r\")5HK", charArray0);
    }

    @Test(timeout = 4000)
    public void test2930() throws Throwable {
        String string0 = WordUtils.initials((String) null);
    }

    @Test(timeout = 4000)
    public void test3031() throws Throwable {
        String string0 = WordUtils.swapCase("v");
    }

    @Test(timeout = 4000)
    public void test3132() throws Throwable {
        String string0 = WordUtils.swapCase(" DpT8aEY9|^gz~");
    }

    @Test(timeout = 4000)
    public void test3233() throws Throwable {
        String string0 = WordUtils.swapCase("");
    }

    @Test(timeout = 4000)
    public void test3334() throws Throwable {
        String string0 = WordUtils.swapCase((String) null);
    }

    @Test(timeout = 4000)
    public void test3435() throws Throwable {
        String string0 = WordUtils.uncapitalize("\"4xGtwS -:oE=e_~");
    }

    @Test(timeout = 4000)
    public void test3536() throws Throwable {
        char[] charArray0 = new char[0];
        String string0 = WordUtils.uncapitalize("d_/iF(v", charArray0);
    }

    @Test(timeout = 4000)
    public void test3637() throws Throwable {
        char[] charArray0 = new char[1];
        String string0 = WordUtils.uncapitalize("", charArray0);
    }

    @Test(timeout = 4000)
    public void test3738() throws Throwable {
        char[] charArray0 = new char[1];
        String string0 = WordUtils.uncapitalize((String) null, charArray0);
    }

    @Test(timeout = 4000)
    public void test3839() throws Throwable {
        String string0 = WordUtils.capitalizeFully("");
    }

    @Test(timeout = 4000)
    public void test3940() throws Throwable {
        String string0 = WordUtils.capitalizeFully((String) null);
    }

    @Test(timeout = 4000)
    public void test4041() throws Throwable {
        char[] charArray0 = new char[0];
        String string0 = WordUtils.capitalizeFully("4.0", charArray0);
    }

    @Test(timeout = 4000)
    public void test4142() throws Throwable {
        char[] charArray0 = new char[2];
        charArray0[0] = 'O';
        String string0 = WordUtils.capitalize("Org.apache.commons.lang.wordutils", charArray0);
    }

    @Test(timeout = 4000)
    public void test4243() throws Throwable {
        String string0 = WordUtils.capitalize("");
    }

    @Test(timeout = 4000)
    public void test4344() throws Throwable {
        String string0 = WordUtils.capitalize((String) null);
    }

    @Test(timeout = 4000)
    public void test4445() throws Throwable {
        char[] charArray0 = new char[0];
        String string0 = WordUtils.capitalize("d_/iF(v", charArray0);
    }

    @Test(timeout = 4000)
    public void test4546() throws Throwable {
        String string0 = WordUtils.wrap("TVM xv", 2, "4.0", true);
    }

    @Test(timeout = 4000)
    public void test4647() throws Throwable {
        String string0 = WordUtils.wrap(" a", 0, "", true);
    }

    @Test(timeout = 4000)
    public void test4748() throws Throwable {
        String string0 = WordUtils.wrap("*O4cY!6-apl%[&-s", 2);
    }

    @Test(timeout = 4000)
    public void test4850() throws Throwable {
        String string0 = WordUtils.wrap("lOuDBz&e lv:\"W", 0, "fs6)>fSx", false);
    }

    @Test(timeout = 4000)
    public void test4951() throws Throwable {
        String string0 = WordUtils.wrap((String) null, (-127));
    }

    @Test(timeout = 4000)
    public void test5052() throws Throwable {
        WordUtils wordUtils0 = new WordUtils();
    }

    @Test(timeout = 4000)
    public void test5153() throws Throwable {
        String string0 = WordUtils.wrap("", (-2030));
    }

    @Test(timeout = 4000)
    public void test5254() throws Throwable {
        String string0 = WordUtils.initials("");
    }

    @Test(timeout = 4000)
    public void test5355() throws Throwable {
        String string0 = WordUtils.capitalizeFully("N})Ip4*^{6]PkL{6");
    }

    @Test(timeout = 4000)
    public void test5456() throws Throwable {
        String string0 = WordUtils.capitalize("}-)Y`s:");
    }
}
