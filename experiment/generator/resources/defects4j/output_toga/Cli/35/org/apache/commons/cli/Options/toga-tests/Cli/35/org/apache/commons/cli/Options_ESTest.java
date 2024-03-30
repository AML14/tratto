/*
 * This file was automatically generated by EvoSuite
 * Fri Dec 15 00:12:49 GMT 2023
 */
package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.util.Collection;
import java.util.List;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class Options_ESTest extends Options_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        Options options0 = new Options();
        options0.addOption("j;", true, "=\"wF?Y");
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        Options options0 = new Options();
        Options options1 = options0.addOption("", "");
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        Options options0 = new Options();
        Collection<Option> collection0 = options0.getOptions();
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        Options options0 = new Options();
        List list0 = options0.getRequiredOptions();
        list0.isEmpty();
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        Options options0 = new Options();
        String string0 = options0.toString();
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        Options options0 = new Options();
        options0.getOptionGroup((Option) null);
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        Options options0 = new Options();
        Collection<OptionGroup> collection0 = options0.getOptionGroups();
    }

    @Test(timeout = 4000)
    public void test077() throws Throwable {
        Options options0 = new Options();
        OptionGroup optionGroup0 = new OptionGroup();
        optionGroup0.setRequired(true);
        Options options1 = options0.addOptionGroup(optionGroup0);
    }

    @Test(timeout = 4000)
    public void test088() throws Throwable {
        Options options0 = new Options();
        OptionGroup optionGroup0 = new OptionGroup();
        Option option0 = new Option("Vw", "org.apache.commons.cli.Options", false, "OJ,R!zRP");
        OptionGroup optionGroup1 = optionGroup0.addOption(option0);
        options0.addOptionGroup(optionGroup1);
        option0.isRequired();
        assertTrue(option0.isRequired());
    }

    @Test(timeout = 4000)
    public void test089() throws Throwable {
        Options options0 = new Options();
        OptionGroup optionGroup0 = new OptionGroup();
        Option option0 = new Option("Vw", "org.apache.commons.cli.Options", false, "OJ,R!zRP");
        OptionGroup optionGroup1 = optionGroup0.addOption(option0);
        options0.addOptionGroup(optionGroup1);
        boolean boolean0 = options0.hasOption("Vw");
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test0910() throws Throwable {
        Options options0 = new Options();
        Option option0 = new Option("u", "?B&B~");
        option0.setRequired(true);
        options0.addOption(option0);
        Options options1 = options0.addOption(option0);
    }

    @Test(timeout = 4000)
    public void test1011() throws Throwable {
        Options options0 = new Options();
        Option option0 = options0.getOption("The addValue method is not intended for client use. Subclasses should use the addValueForProcessing method instead. ");
        assertNotNull(option0);
    }

    @Test(timeout = 4000)
    public void test1112() throws Throwable {
        Options options0 = new Options();
        Option option0 = new Option("", "");
        Options options1 = options0.addOption(option0);
        Option option1 = options1.getOption("");
    }

    @Test(timeout = 4000)
    public void test1213() throws Throwable {
        Options options0 = new Options();
        Options options1 = options0.addOption("", "i9Jw42+:oi(", false, "");
        List<String> list0 = options1.getMatchingOptions("");
        list0.isEmpty();
    }

    @Test(timeout = 4000)
    public void test1214() throws Throwable {
        Options options0 = new Options();
        Options options1 = options0.addOption("", "i9Jw42+:oi(", false, "");
        List<String> list0 = options1.getMatchingOptions("");
        list0.contains("");
    }

    @Test(timeout = 4000)
    public void test1315() throws Throwable {
        Options options0 = new Options();
        options0.addOption("", "9BlR(W$", false, "[]");
        List<String> list0 = options0.getMatchingOptions("' was specified but an option from this group ");
        list0.isEmpty();
    }

    @Test(timeout = 4000)
    public void test1416() throws Throwable {
        Options options0 = new Options();
        boolean boolean0 = options0.hasOption("org.apache.commons.cli.Options");
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test1518() throws Throwable {
        Options options0 = new Options();
        OptionGroup optionGroup0 = new OptionGroup();
        Option option0 = new Option("Vw", "org.apache.commons.cli.Options", false, "OJ,R!zRP");
        OptionGroup optionGroup1 = optionGroup0.addOption(option0);
        options0.addOptionGroup(optionGroup1);
        boolean boolean0 = options0.hasOption("org.apache.commons.cli.Options");
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test1619() throws Throwable {
        Options options0 = new Options();
        boolean boolean0 = options0.hasLongOption("7");
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test1720() throws Throwable {
        Options options0 = new Options();
        options0.addOption("dI", "", true, (String) null);
        boolean boolean0 = options0.hasLongOption("");
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test1821() throws Throwable {
        Options options0 = new Options();
        boolean boolean0 = options0.hasShortOption("OV{8Rx'de");
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test1922() throws Throwable {
        Options options0 = new Options();
        Option option0 = new Option("", (String) null);
        Options options1 = options0.addOption(option0);
        boolean boolean0 = options1.hasShortOption("");
        assertTrue(boolean0);
    }
}
