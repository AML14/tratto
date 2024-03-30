/*
 * This file was automatically generated by EvoSuite
 * Thu Dec 14 23:37:47 GMT 2023
 */
package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.PosixParser;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class PosixParser_ESTest extends PosixParser_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test00() throws Throwable {
        PosixParser posixParser0 = new PosixParser();
        Options options0 = new Options();
        String[] stringArray0 = new String[1];
        stringArray0[0] = "--";
        String[] stringArray1 = posixParser0.flatten(options0, stringArray0, false);
    }

    @Test(timeout = 4000)
    public void test11() throws Throwable {
        Options options0 = new Options();
        options0.addOption("", "--=#-W}we{Z/xxe<#QZ", false, "--=#-W}we{Z/xxe<#QZ");
        PosixParser posixParser0 = new PosixParser();
        String[] stringArray0 = new String[7];
        stringArray0[0] = "--=#-W}we{Z/xxe<#QZ";
        posixParser0.flatten(options0, stringArray0, false);
    }

    @Test(timeout = 4000)
    public void test22() throws Throwable {
        PosixParser posixParser0 = new PosixParser();
        Options options0 = new Options();
        Options options1 = options0.addOption("", "", false, "");
        String[] stringArray0 = new String[1];
        stringArray0[0] = "--";
        String[] stringArray1 = posixParser0.flatten(options1, stringArray0, false);
    }

    @Test(timeout = 4000)
    public void test33() throws Throwable {
        Options options0 = new Options();
        PosixParser posixParser0 = new PosixParser();
        String[] stringArray0 = new String[1];
        stringArray0[0] = "-";
        CommandLine commandLine0 = posixParser0.parse(options0, stringArray0);
    }

    @Test(timeout = 4000)
    public void test44() throws Throwable {
        Options options0 = new Options();
        String[] stringArray0 = new String[7];
        stringArray0[0] = "-(";
        PosixParser posixParser0 = new PosixParser();
        posixParser0.parse(options0, stringArray0);
    }

    @Test(timeout = 4000)
    public void test55() throws Throwable {
        PosixParser posixParser0 = new PosixParser();
        Options options0 = new Options();
        Options options1 = options0.addOption("za", true, "za");
        String[] stringArray0 = new String[2];
        stringArray0[0] = "-za";
        posixParser0.flatten(options1, stringArray0, true);
    }

    @Test(timeout = 4000)
    public void test66() throws Throwable {
        Options options0 = new Options();
        String[] stringArray0 = new String[6];
        stringArray0[0] = "-h";
        PosixParser posixParser0 = new PosixParser();
        String[] stringArray1 = posixParser0.flatten(options0, stringArray0, true);
    }
}
