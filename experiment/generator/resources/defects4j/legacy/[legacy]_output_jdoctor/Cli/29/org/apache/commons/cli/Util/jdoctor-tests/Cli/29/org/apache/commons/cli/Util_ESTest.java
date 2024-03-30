/*
 * This file was automatically generated by EvoSuite
 * Thu Dec 14 23:49:14 GMT 2023
 */
package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;
import org.apache.commons.cli.Util;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class Util_ESTest extends Util_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test10() throws Throwable {
        String string0 = Util.stripLeadingHyphens("-");
    }

    @Test(timeout = 4000)
    public void test11() throws Throwable {
        String string0 = Util.stripLeadingHyphens("-");
    }

    @Test(timeout = 4000)
    public void test22() throws Throwable {
        String string0 = Util.stripLeadingHyphens((String) null);
    }

    @Test(timeout = 4000)
    public void test33() throws Throwable {
        String string0 = Util.stripLeadingHyphens("--");
    }

    @Test(timeout = 4000)
    public void test44() throws Throwable {
        String string0 = Util.stripLeadingHyphens("b*h{p} jzFE");
    }

    @Test(timeout = 4000)
    public void test55() throws Throwable {
        String string0 = Util.stripLeadingAndTrailingQuotes("\"ED.Cahr");
    }

    @Test(timeout = 4000)
    public void test66() throws Throwable {
        String string0 = Util.stripLeadingAndTrailingQuotes("~ejLg;=`mCF'-$5=e\"");
    }
}
