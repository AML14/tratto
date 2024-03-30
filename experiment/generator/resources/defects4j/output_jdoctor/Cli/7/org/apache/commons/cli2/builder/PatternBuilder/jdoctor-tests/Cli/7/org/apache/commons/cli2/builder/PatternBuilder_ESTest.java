/*
 * This file was automatically generated by EvoSuite
 * Thu Dec 14 22:49:56 GMT 2023
 */
package org.apache.commons.cli2.builder;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import org.apache.commons.cli2.Option;
import org.apache.commons.cli2.builder.PatternBuilder;
import org.apache.commons.cli2.option.GroupImpl;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class PatternBuilder_ESTest extends PatternBuilder_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        PatternBuilder patternBuilder0 = new PatternBuilder();
        patternBuilder0.withPattern("_");
        Option option0 = patternBuilder0.create();
        option0.getPreferredName();
    }

    @Test(timeout = 4000)
    public void test001() throws Throwable {
        PatternBuilder patternBuilder0 = new PatternBuilder();
        patternBuilder0.withPattern("_");
        Option option0 = patternBuilder0.create();
        option0.isRequired();
    }

    @Test(timeout = 4000)
    public void test012() throws Throwable {
        PatternBuilder patternBuilder0 = new PatternBuilder();
        patternBuilder0.withPattern("u+B54O&?sj$}");
        GroupImpl groupImpl0 = (GroupImpl) patternBuilder0.create();
        groupImpl0.getMinimum();
    }

    @Test(timeout = 4000)
    public void test023() throws Throwable {
        PatternBuilder patternBuilder0 = new PatternBuilder();
        patternBuilder0.withPattern("zy^?*!BxLN+>^&S\"@");
    }

    @Test(timeout = 4000)
    public void test034() throws Throwable {
        PatternBuilder patternBuilder0 = new PatternBuilder();
        patternBuilder0.withPattern("@kp+)3l#w");
    }

    @Test(timeout = 4000)
    public void test045() throws Throwable {
        PatternBuilder patternBuilder0 = new PatternBuilder();
        patternBuilder0.withPattern("-cW9xb-)%'HS`eL/");
    }

    @Test(timeout = 4000)
    public void test056() throws Throwable {
        PatternBuilder patternBuilder0 = new PatternBuilder();
        patternBuilder0.withPattern("T\"|0KFIn/-2(");
    }

    @Test(timeout = 4000)
    public void test067() throws Throwable {
        PatternBuilder patternBuilder0 = new PatternBuilder();
        patternBuilder0.withPattern("tT;(< ,vN9~{TwrG");
    }

    @Test(timeout = 4000)
    public void test078() throws Throwable {
        PatternBuilder patternBuilder0 = new PatternBuilder();
        patternBuilder0.withPattern("org.apache.commons.cli2.commandline.CommandLineImpl");
    }

    @Test(timeout = 4000)
    public void test089() throws Throwable {
        PatternBuilder patternBuilder0 = new PatternBuilder();
        patternBuilder0.withPattern("v6C#A]`q2A!I3>1Im3");
    }

    @Test(timeout = 4000)
    public void test0910() throws Throwable {
        PatternBuilder patternBuilder0 = new PatternBuilder();
        patternBuilder0.withPattern("WX!E*''e:7l8 /%S'");
    }

    @Test(timeout = 4000)
    public void test1011() throws Throwable {
        PatternBuilder patternBuilder0 = new PatternBuilder();
        patternBuilder0.withPattern("zy^?*=BxLN+>^&S\"8");
    }

    @Test(timeout = 4000)
    public void test1112() throws Throwable {
        PatternBuilder patternBuilder0 = new PatternBuilder();
        patternBuilder0.withPattern("");
    }
}
