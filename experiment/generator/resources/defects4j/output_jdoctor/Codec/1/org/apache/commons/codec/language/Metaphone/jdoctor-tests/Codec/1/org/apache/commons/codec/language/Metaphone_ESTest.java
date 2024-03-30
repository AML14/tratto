/*
 * This file was automatically generated by EvoSuite
 * Mon Nov 20 00:53:02 GMT 2023
 */
package org.apache.commons.codec.language;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import org.apache.commons.codec.language.Metaphone;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class Metaphone_ESTest extends Metaphone_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        Metaphone metaphone0 = new Metaphone();
        metaphone0.isMetaphoneEqual("V?R o|=cMEo\n`", "V?R o|=cMEo\n`");
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        Metaphone metaphone0 = new Metaphone();
        metaphone0.setMaxCodeLen((-1));
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        Metaphone metaphone0 = new Metaphone();
        metaphone0.encode("");
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        Metaphone metaphone0 = new Metaphone();
        metaphone0.metaphone("oF'lp");
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        Metaphone metaphone0 = new Metaphone();
        Object object0 = new Object();
        metaphone0.encode(object0);
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        Metaphone metaphone0 = new Metaphone();
        metaphone0.metaphone("90PU9");
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        Metaphone metaphone0 = new Metaphone();
        metaphone0.isMetaphoneEqual("h\"Xsf", "h\"Xsf");
    }

    @Test(timeout = 4000)
    public void test077() throws Throwable {
        Metaphone metaphone0 = new Metaphone();
        metaphone0.metaphone("pFB]r[E&mk[h");
    }

    @Test(timeout = 4000)
    public void test088() throws Throwable {
        Metaphone metaphone0 = new Metaphone();
        metaphone0.metaphone("KJ&%XJyh,HQm7");
    }

    @Test(timeout = 4000)
    public void test099() throws Throwable {
        Metaphone metaphone0 = new Metaphone();
        metaphone0.metaphone("og.apache.commons.codec.EncodrException");
    }

    @Test(timeout = 4000)
    public void test1010() throws Throwable {
        Metaphone metaphone0 = new Metaphone();
        metaphone0.metaphone("zt TSkQjV+/ 8qf%s!");
    }

    @Test(timeout = 4000)
    public void test1111() throws Throwable {
        Metaphone metaphone0 = new Metaphone();
        metaphone0.metaphone("SP");
    }

    @Test(timeout = 4000)
    public void test1212() throws Throwable {
        Metaphone metaphone0 = new Metaphone();
        metaphone0.encode("iXT,");
    }

    @Test(timeout = 4000)
    public void test1313() throws Throwable {
        Metaphone metaphone0 = new Metaphone();
        metaphone0.encode("Parameter supplied to Metaphone encode is not of type java.lang.String");
    }

    @Test(timeout = 4000)
    public void test1414() throws Throwable {
        Metaphone metaphone0 = new Metaphone();
        metaphone0.metaphone((String) null);
    }

    @Test(timeout = 4000)
    public void test1515() throws Throwable {
        Metaphone metaphone0 = new Metaphone();
        metaphone0.metaphone("");
    }

    @Test(timeout = 4000)
    public void test1616() throws Throwable {
        Metaphone metaphone0 = new Metaphone();
        metaphone0.encode((Object) "\"|ckUNd(G8sM!YGONw");
    }

    @Test(timeout = 4000)
    public void test1717() throws Throwable {
        Metaphone metaphone0 = new Metaphone();
        metaphone0.isMetaphoneEqual("TH", "TH");
    }

    @Test(timeout = 4000)
    public void test1818() throws Throwable {
        Metaphone metaphone0 = new Metaphone();
        metaphone0.isMetaphoneEqual(" {,'DqH>", " {,'DqH>");
    }

    @Test(timeout = 4000)
    public void test1919() throws Throwable {
        Metaphone metaphone0 = new Metaphone();
        metaphone0.encode("SC");
    }

    @Test(timeout = 4000)
    public void test2020() throws Throwable {
        Metaphone metaphone0 = new Metaphone();
        metaphone0.metaphone("T");
    }

    @Test(timeout = 4000)
    public void test2121() throws Throwable {
        Metaphone metaphone0 = new Metaphone();
        metaphone0.isMetaphoneEqual("mBw@+c_3gmD", "mBw@+c_3gmD");
    }

    @Test(timeout = 4000)
    public void test2222() throws Throwable {
        Metaphone metaphone0 = new Metaphone();
        metaphone0.metaphone("y._D#`");
    }

    @Test(timeout = 4000)
    public void test2323() throws Throwable {
        Metaphone metaphone0 = new Metaphone();
        metaphone0.isMetaphoneEqual("p)>zyU\"ESfN7nW", "p)>zyU\"ESfN7nW");
    }

    @Test(timeout = 4000)
    public void test2424() throws Throwable {
        Metaphone metaphone0 = new Metaphone();
        metaphone0.metaphone("XKSR");
    }

    @Test(timeout = 4000)
    public void test2525() throws Throwable {
        Metaphone metaphone0 = new Metaphone();
        metaphone0.isMetaphoneEqual("EIY", "org.apache.commons.codec.EncoderException");
    }

    @Test(timeout = 4000)
    public void test2626() throws Throwable {
        Metaphone metaphone0 = new Metaphone();
        metaphone0.metaphone("SCQ");
    }

    @Test(timeout = 4000)
    public void test2727() throws Throwable {
        Metaphone metaphone0 = new Metaphone();
        metaphone0.isMetaphoneEqual(".|SH5's[aW|j:;K.", ".|SH5's[aW|j:;K.");
    }

    @Test(timeout = 4000)
    public void test2828() throws Throwable {
        Metaphone metaphone0 = new Metaphone();
        metaphone0.isMetaphoneEqual("Y`Y1go", "Y`Y1go");
    }

    @Test(timeout = 4000)
    public void test2929() throws Throwable {
        Metaphone metaphone0 = new Metaphone();
        metaphone0.isMetaphoneEqual("ph`/O{&dOS<V", "ph`/O{&dOS<V");
    }

    @Test(timeout = 4000)
    public void test3030() throws Throwable {
        Metaphone metaphone0 = new Metaphone();
        metaphone0.metaphone("hcU8nbb88]MP");
    }

    @Test(timeout = 4000)
    public void test3131() throws Throwable {
        Metaphone metaphone0 = new Metaphone();
        metaphone0.encode("T;lc'BG(OO<A+(");
    }

    @Test(timeout = 4000)
    public void test3232() throws Throwable {
        Metaphone metaphone0 = new Metaphone();
        metaphone0.isMetaphoneEqual("_dS", "_dS");
    }

    @Test(timeout = 4000)
    public void test3333() throws Throwable {
        Metaphone metaphone0 = new Metaphone();
        metaphone0.isMetaphoneEqual("wSaUWn", " 9v(u/Bfg");
    }

    @Test(timeout = 4000)
    public void test3434() throws Throwable {
        Metaphone metaphone0 = new Metaphone();
        metaphone0.isMetaphoneEqual("wSa6UWn", "wSa6UWn");
    }

    @Test(timeout = 4000)
    public void test3535() throws Throwable {
        Metaphone metaphone0 = new Metaphone();
        metaphone0.isMetaphoneEqual("GN", "GN");
    }

    @Test(timeout = 4000)
    public void test3636() throws Throwable {
        Metaphone metaphone0 = new Metaphone();
        Metaphone metaphone1 = new Metaphone();
        metaphone1.metaphone("nRlUW)_}-39xb-/z^gU");
    }

    @Test(timeout = 4000)
    public void test3737() throws Throwable {
        Metaphone metaphone0 = new Metaphone();
        metaphone0.isMetaphoneEqual(";2j9H@s& my2he^]", ";2j9H@s& my2he^]");
    }

    @Test(timeout = 4000)
    public void test3838() throws Throwable {
        Metaphone metaphone0 = new Metaphone();
        metaphone0.isMetaphoneEqual("GIEB", "GIEB");
    }

    @Test(timeout = 4000)
    public void test3939() throws Throwable {
        Metaphone metaphone0 = new Metaphone();
        metaphone0.encode("AEIOU");
    }

    @Test(timeout = 4000)
    public void test4040() throws Throwable {
        Metaphone metaphone0 = new Metaphone();
        metaphone0.metaphone("6L0`IO7tezCe");
        metaphone0.metaphone("");
        metaphone0.metaphone("C]G^m$b");
    }

    @Test(timeout = 4000)
    public void test4141() throws Throwable {
        Metaphone metaphone0 = new Metaphone();
        metaphone0.isMetaphoneEqual("CHE", "CHE");
    }

    @Test(timeout = 4000)
    public void test4242() throws Throwable {
        Metaphone metaphone0 = new Metaphone();
        metaphone0.setMaxCodeLen(595);
        metaphone0.encode("7HSCqb)rF(1<ghv");
        metaphone0.isMetaphoneEqual("7HSCqb)rF(1<ghv", "XXKBRFF");
    }

    @Test(timeout = 4000)
    public void test4343() throws Throwable {
        Metaphone metaphone0 = new Metaphone();
        metaphone0.isMetaphoneEqual("AZm[miAtF$[", (String) null);
    }

    @Test(timeout = 4000)
    public void test4444() throws Throwable {
        Metaphone metaphone0 = new Metaphone();
        metaphone0.isMetaphoneEqual("5FDg,NZmLx84\"usQ", "5FDg,NZmLx84\"usQ");
    }

    @Test(timeout = 4000)
    public void test4545() throws Throwable {
        Metaphone metaphone0 = new Metaphone();
        metaphone0.isMetaphoneEqual("CI,", (String) null);
        metaphone0.isMetaphoneEqual("~N>gM", "~N>gM");
        metaphone0.encode((Object) "org.apache.commons.codec.EncoderExc7ption");
        metaphone0.isMetaphoneEqual("CI,", "WCR!");
        Metaphone metaphone1 = new Metaphone();
        metaphone1.isMetaphoneEqual("O:['Scyd>cl", "~N>gM");
        metaphone0.metaphone("O:['Scyd>cl");
        metaphone0.metaphone("nf4}]mqzm");
    }

    @Test(timeout = 4000)
    public void test4646() throws Throwable {
        Metaphone metaphone0 = new Metaphone();
        metaphone0.encode("6QnMbs");
        metaphone0.encode("6QnMbs");
        metaphone0.isMetaphoneEqual("k,'Hi'>Xci=", "KNMB");
    }
}
