/*
 * This file was automatically generated by EvoSuite
 * Thu Dec 14 22:55:26 GMT 2023
 */
package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Properties;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.PosixParser;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class Parser_ESTest extends Parser_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        PosixParser posixParser0 = new PosixParser();
        Options options0 = new Options();
        CommandLine commandLine0 = posixParser0.parse(options0, (String[]) null);
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        Options options0 = new Options();
        GnuParser gnuParser0 = new GnuParser();
        Properties properties0 = new Properties();
        String[] stringArray0 = new String[1];
        stringArray0[0] = "-";
        CommandLine commandLine0 = gnuParser0.parse(options0, stringArray0, properties0, true);
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        PosixParser posixParser0 = new PosixParser();
        Options options0 = new Options();
        String[] stringArray0 = new String[7];
        stringArray0[0] = "YTc*cX :#5_XKki4L-A";
        stringArray0[1] = "yes";
        stringArray0[2] = "7";
        stringArray0[3] = "7";
        stringArray0[4] = "--";
        stringArray0[5] = "YTc*cX :#5_XKki4L-A";
        stringArray0[6] = "--";
        posixParser0.parse(options0, stringArray0, (Properties) null);
        options0.addOption("7", "7", true, "7");
        Option option0 = new Option("7", true, "yes");
        ListIterator<String> listIterator0 = (ListIterator<String>) mock(ListIterator.class, new ViolatedAssumptionAnswer());
        doReturn(true, true).when(listIterator0).hasNext();
        doReturn("7", "-7").when(listIterator0).next();
        doReturn("--").when(listIterator0).previous();
        posixParser0.processArgs(option0, listIterator0);
        option0.getLongOpt();
        assertNotNull(option0.getLongOpt());
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        Options options0 = new Options();
        GnuParser gnuParser0 = new GnuParser();
        String[] stringArray0 = new String[1];
        stringArray0[0] = "-";
        Properties properties0 = new Properties();
        CommandLine commandLine0 = gnuParser0.parse(options0, stringArray0, properties0);
        assertNotNull(commandLine0);
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        Options options0 = new Options();
        GnuParser gnuParser0 = new GnuParser();
        Properties properties0 = new Properties();
        String[] stringArray0 = new String[1];
        stringArray0[0] = "-}cc";
        gnuParser0.parse(options0, stringArray0, properties0);
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        Options options0 = new Options();
        GnuParser gnuParser0 = new GnuParser();
        Properties properties0 = new Properties();
        String[] stringArray0 = new String[1];
        stringArray0[0] = "u7a}[2=V";
        CommandLine commandLine0 = gnuParser0.parse(options0, stringArray0, properties0, true);
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        PosixParser posixParser0 = new PosixParser();
        Options options0 = new Options();
        String[] stringArray0 = new String[0];
        Properties properties0 = new Properties();
        properties0.put("", posixParser0);
        posixParser0.parse(options0, stringArray0, properties0);
    }

    @Test(timeout = 4000)
    public void test077() throws Throwable {
        Options options0 = new Options();
        OptionGroup optionGroup0 = new OptionGroup();
        optionGroup0.setRequired(true);
        BasicParser basicParser0 = new BasicParser();
        options0.addOptionGroup(optionGroup0);
        basicParser0.parse(options0, (String[]) null, true);
    }

    @Test(timeout = 4000)
    public void test088() throws Throwable {
        Options options0 = new Options();
        OptionGroup optionGroup0 = new OptionGroup();
        optionGroup0.setRequired(true);
        Options options1 = options0.addOptionGroup(optionGroup0);
        BasicParser basicParser0 = new BasicParser();
        options1.addOptionGroup(optionGroup0);
        basicParser0.parse(options1, (String[]) null, false);
    }

    @Test(timeout = 4000)
    public void test099() throws Throwable {
        Options options0 = new Options();
        Options options1 = options0.addOption((String) null, true, "true");
        String[] stringArray0 = new String[0];
        GnuParser gnuParser0 = new GnuParser();
        Properties properties0 = new Properties();
        gnuParser0.parse(options1, stringArray0, properties0, true);
        LinkedList<Object> linkedList0 = new LinkedList<Object>();
        ListIterator<Object> listIterator0 = linkedList0.listIterator();
        gnuParser0.processOption((String) null, listIterator0);
    }

    @Test(timeout = 4000)
    public void test1010() throws Throwable {
        PosixParser posixParser0 = new PosixParser();
        Options options0 = new Options();
        String[] stringArray0 = new String[0];
        Properties properties0 = new Properties();
        posixParser0.parse(options0, stringArray0, properties0);
        Option option0 = new Option((String) null, (String) null);
        ListIterator<String> listIterator0 = (ListIterator<String>) mock(ListIterator.class, new ViolatedAssumptionAnswer());
        doReturn(true).when(listIterator0).hasNext();
        doReturn((String) null).when(listIterator0).next();
        doReturn((Object) null).when(listIterator0).previous();
        posixParser0.processArgs(option0, listIterator0);
    }

    @Test(timeout = 4000)
    public void test1111() throws Throwable {
        PosixParser posixParser0 = new PosixParser();
        Options options0 = new Options();
        String[] stringArray0 = new String[7];
        stringArray0[0] = "YTc*cX :#5_XKki4CXA";
        stringArray0[1] = "yes";
        stringArray0[2] = "7";
        stringArray0[3] = "7";
        stringArray0[4] = "YTc*cX :#5_XKki4CXA";
        stringArray0[5] = "YTc*cX :#5_XKki4CXA";
        stringArray0[6] = "YTc*cX :#5_XKki4CXA";
        posixParser0.parse(options0, stringArray0, (Properties) null);
        options0.addOption("7", "7", true, "7");
        Option option0 = new Option("yes", "yes");
        ListIterator<String> listIterator0 = (ListIterator<String>) mock(ListIterator.class, new ViolatedAssumptionAnswer());
        doReturn(true).when(listIterator0).hasNext();
        doReturn("7").when(listIterator0).next();
        doReturn("YTc*cX :#5_XKki4CXA").when(listIterator0).previous();
        posixParser0.processArgs(option0, listIterator0);
    }

    @Test(timeout = 4000)
    public void test1212() throws Throwable {
        PosixParser posixParser0 = new PosixParser();
        Option option0 = new Option("", "}uOe;`_98Lf");
        option0.setOptionalArg(true);
        ListIterator<String> listIterator0 = (ListIterator<String>) mock(ListIterator.class, new ViolatedAssumptionAnswer());
        doReturn(false).when(listIterator0).hasNext();
        posixParser0.processArgs(option0, listIterator0);
        option0.getArgName();
        assertNotNull(option0.getArgName());
    }

    @Test(timeout = 4000)
    public void test1313() throws Throwable {
        Options options0 = new Options();
        OptionGroup optionGroup0 = new OptionGroup();
        Option option0 = new Option("LwnQ", true, "LwnQ");
        OptionGroup optionGroup1 = optionGroup0.addOption(option0);
        Options options1 = options0.addOptionGroup(optionGroup1);
        Properties properties0 = new Properties();
        GnuParser gnuParser0 = new GnuParser();
        String[] stringArray0 = new String[5];
        stringArray0[0] = "-7a}[2cV";
        option0.setRequired(true);
        gnuParser0.parse(options1, stringArray0, properties0, true);
        LinkedList<Object> linkedList0 = new LinkedList<Object>();
        ListIterator<Object> listIterator0 = linkedList0.listIterator();
        gnuParser0.processOption("LwnQ", listIterator0);
    }

    @Test(timeout = 4000)
    public void test1414() throws Throwable {
        Options options0 = new Options();
        OptionGroup optionGroup0 = new OptionGroup();
        Option option0 = new Option("LwnQ", true, "LwnQ");
        OptionGroup optionGroup1 = optionGroup0.addOption(option0);
        Options options1 = options0.addOptionGroup(optionGroup1);
        Properties properties0 = new Properties();
        optionGroup0.setRequired(true);
        GnuParser gnuParser0 = new GnuParser();
        String[] stringArray0 = new String[5];
        stringArray0[0] = "-7a}[2cV";
        gnuParser0.parse(options1, stringArray0, properties0, true);
        LinkedList<Object> linkedList0 = new LinkedList<Object>();
        ListIterator<Object> listIterator0 = linkedList0.listIterator();
        gnuParser0.processOption("LwnQ", listIterator0);
    }

    @Test(timeout = 4000)
    public void test1515() throws Throwable {
        Options options0 = new Options();
        String[] stringArray0 = new String[0];
        GnuParser gnuParser0 = new GnuParser();
        OptionGroup optionGroup0 = new OptionGroup();
        Option option0 = new Option((String) null, false, (String) null);
        OptionGroup optionGroup1 = optionGroup0.addOption(option0);
        Options options1 = options0.addOptionGroup(optionGroup1);
        Properties properties0 = new Properties();
        gnuParser0.parse(options1, stringArray0, properties0, false);
        LinkedList<Object> linkedList0 = new LinkedList<Object>();
        ListIterator<Object> listIterator0 = linkedList0.listIterator();
        gnuParser0.processOption((String) null, listIterator0);
        listIterator0.hasNext();
    }
}
