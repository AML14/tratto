/*
 * This file was automatically generated by EvoSuite
 * Thu Dec 14 23:54:36 GMT 2023
 */
package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;
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
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class Parser_ESTest extends Parser_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        BasicParser basicParser0 = new BasicParser();
        Option option0 = new Option((String) null, true, (String) null);
        LinkedList<String> linkedList0 = new LinkedList<String>();
        ListIterator<String> listIterator0 = linkedList0.listIterator();
        option0.setOptionalArg(true);
        basicParser0.processArgs(option0, listIterator0);
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        Options options0 = new Options();
        GnuParser gnuParser0 = new GnuParser();
        String[] stringArray0 = new String[1];
        gnuParser0.parse(options0, stringArray0, true);
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        Options options0 = new Options();
        Properties properties0 = new Properties();
        GnuParser gnuParser0 = new GnuParser();
        CommandLine commandLine0 = gnuParser0.parse(options0, (String[]) null, properties0);
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        GnuParser gnuParser0 = new GnuParser();
        Options options0 = new Options();
        String[] stringArray0 = new String[2];
        stringArray0[0] = "-~;t";
        PosixParser posixParser0 = new PosixParser();
        String[] stringArray1 = posixParser0.flatten(options0, stringArray0, true);
        CommandLine commandLine0 = gnuParser0.parse(options0, stringArray1);
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        Options options0 = new Options();
        Properties properties0 = new Properties();
        GnuParser gnuParser0 = new GnuParser();
        String[] stringArray0 = new String[1];
        stringArray0[0] = "-";
        CommandLine commandLine0 = gnuParser0.parse(options0, stringArray0, properties0, true);
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        Options options0 = new Options();
        GnuParser gnuParser0 = new GnuParser();
        String[] stringArray0 = new String[1];
        stringArray0[0] = "-";
        CommandLine commandLine0 = gnuParser0.parse(options0, stringArray0);
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        GnuParser gnuParser0 = new GnuParser();
        Options options0 = new Options();
        Properties properties0 = new Properties();
        String[] stringArray0 = new String[20];
        stringArray0[0] = "-tn'm{W$6H0r5{;w";
        CommandLine commandLine0 = gnuParser0.parse(options0, stringArray0, properties0, true);
    }

    @Test(timeout = 4000)
    public void test077() throws Throwable {
        Options options0 = new Options();
        GnuParser gnuParser0 = new GnuParser();
        String[] stringArray0 = new String[1];
        stringArray0[0] = "-~;t";
        gnuParser0.parse(options0, stringArray0);
    }

    @Test(timeout = 4000)
    public void test088() throws Throwable {
        Options options0 = new Options();
        BasicParser basicParser0 = new BasicParser();
        String[] stringArray0 = new String[2];
        stringArray0[0] = "org.apache.commons.cli.AmbiguousOptionException";
        stringArray0[1] = "--";
        Properties properties0 = new Properties();
        CommandLine commandLine0 = basicParser0.parse(options0, stringArray0, properties0, true);
    }

    @Test(timeout = 4000)
    public void test099() throws Throwable {
        GnuParser gnuParser0 = new GnuParser();
        Options options0 = new Options();
        Properties properties0 = new Properties();
        properties0.put("-tn'm{W$6H0r5{;w", "-tn'm{W$6H0r5{;w");
        gnuParser0.parse(options0, (String[]) null, properties0, true);
    }

    @Test(timeout = 4000)
    public void test1010() throws Throwable {
        GnuParser gnuParser0 = new GnuParser();
        Options options0 = new Options();
        Properties properties0 = new Properties();
        OptionGroup optionGroup0 = new OptionGroup();
        optionGroup0.setRequired(true);
        Options options1 = options0.addOptionGroup(optionGroup0);
        gnuParser0.parse(options1, (String[]) null, properties0, true);
    }

    @Test(timeout = 4000)
    public void test1111() throws Throwable {
        Option option0 = new Option("5", "5", true, (String) null);
        Options options0 = new Options();
        String[] stringArray0 = new String[3];
        stringArray0[0] = "5";
        OptionGroup optionGroup0 = new OptionGroup();
        OptionGroup optionGroup1 = optionGroup0.addOption(option0);
        Options options1 = options0.addOptionGroup(optionGroup1);
        Properties properties0 = new Properties();
        BasicParser basicParser0 = new BasicParser();
        basicParser0.parse(options1, stringArray0, properties0, true);
        LinkedList<String> linkedList0 = new LinkedList<String>();
        linkedList0.add("5");
        ListIterator<String> listIterator0 = linkedList0.listIterator();
        basicParser0.processOption("5", listIterator0);
        listIterator0.hasPrevious();
    }

    @Test(timeout = 4000)
    public void test1112() throws Throwable {
        Option option0 = new Option("5", "5", true, (String) null);
        Options options0 = new Options();
        String[] stringArray0 = new String[3];
        stringArray0[0] = "5";
        OptionGroup optionGroup0 = new OptionGroup();
        OptionGroup optionGroup1 = optionGroup0.addOption(option0);
        Options options1 = options0.addOptionGroup(optionGroup1);
        Properties properties0 = new Properties();
        BasicParser basicParser0 = new BasicParser();
        basicParser0.parse(options1, stringArray0, properties0, true);
        LinkedList<String> linkedList0 = new LinkedList<String>();
        linkedList0.add("5");
        ListIterator<String> listIterator0 = linkedList0.listIterator();
        basicParser0.processOption("5", listIterator0);
        listIterator0.hasNext();
    }

    @Test(timeout = 4000)
    public void test1213() throws Throwable {
        Options options0 = new Options();
        GnuParser gnuParser0 = new GnuParser();
        Properties properties0 = new Properties();
        LinkedList<String> linkedList0 = new LinkedList<String>();
        linkedList0.add(" to desired type: ");
        String[] stringArray0 = new String[1];
        stringArray0[0] = " to desired type: ";
        gnuParser0.parse(options0, stringArray0, properties0, true);
        ListIterator<String> listIterator0 = linkedList0.listIterator();
        gnuParser0.processArgs((Option) null, listIterator0);
    }

    @Test(timeout = 4000)
    public void test1314() throws Throwable {
        Option option0 = new Option("5", "5", true, "5");
        Options options0 = new Options();
        Options options1 = options0.addOption(option0);
        Properties properties0 = new Properties();
        BasicParser basicParser0 = new BasicParser();
        String[] stringArray0 = new String[2];
        stringArray0[0] = "5";
        stringArray0[1] = "5";
        basicParser0.parse(options1, stringArray0, properties0, false);
        LinkedList<String> linkedList0 = new LinkedList<String>();
        linkedList0.add("-5");
        ListIterator<String> listIterator0 = linkedList0.listIterator();
        basicParser0.processOption("-5", listIterator0);
    }

    @Test(timeout = 4000)
    public void test1515() throws Throwable {
        BasicParser basicParser0 = new BasicParser();
        Options options0 = new Options();
        Option option0 = new Option("es", "es", true, "es");
        Properties properties0 = new Properties();
        Options options1 = options0.addOption(option0);
        option0.setRequired(true);
        String[] stringArray0 = new String[0];
        basicParser0.parse(options1, stringArray0, properties0, true);
        basicParser0.processOption("es", (ListIterator) null);
    }

    @Test(timeout = 4000)
    public void test1616() throws Throwable {
        Option option0 = new Option("5", "5", true, (String) null);
        Options options0 = new Options();
        String[] stringArray0 = new String[3];
        stringArray0[0] = "5";
        OptionGroup optionGroup0 = new OptionGroup();
        OptionGroup optionGroup1 = optionGroup0.addOption(option0);
        Options options1 = options0.addOptionGroup(optionGroup1);
        Properties properties0 = new Properties();
        optionGroup0.setRequired(true);
        BasicParser basicParser0 = new BasicParser();
        basicParser0.parse(options1, stringArray0, properties0, true);
        LinkedList<String> linkedList0 = new LinkedList<String>();
        ListIterator<String> listIterator0 = linkedList0.listIterator();
        basicParser0.processOption("5", listIterator0);
    }
}
