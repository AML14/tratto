/*
 * This file was automatically generated by EvoSuite
 * Fri Dec 15 00:05:03 GMT 2023
 */
package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.io.PrintWriter;
import java.util.Comparator;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.evosuite.runtime.mock.java.io.MockPrintWriter;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class HelpFormatter_ESTest extends HelpFormatter_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        int int0 = helpFormatter0.getWidth();
    }

    @Test(timeout = 4000)
    public void test001() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        int int0 = helpFormatter0.getWidth();
        helpFormatter0.getOptPrefix();
    }

    @Test(timeout = 4000)
    public void test002() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        int int0 = helpFormatter0.getWidth();
    }

    @Test(timeout = 4000)
    public void test003() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        int int0 = helpFormatter0.getWidth();
        helpFormatter0.getSyntaxPrefix();
        assertNotNull(helpFormatter0.getSyntaxPrefix());
    }

    @Test(timeout = 4000)
    public void test004() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        int int0 = helpFormatter0.getWidth();
        helpFormatter0.getLongOptSeparator();
        assertNotNull(helpFormatter0.getLongOptSeparator());
    }

    @Test(timeout = 4000)
    public void test005() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        int int0 = helpFormatter0.getWidth();
        helpFormatter0.getArgName();
        assertNotNull(helpFormatter0.getArgName());
    }

    @Test(timeout = 4000)
    public void test006() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        int int0 = helpFormatter0.getWidth();
    }

    @Test(timeout = 4000)
    public void test007() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        int int0 = helpFormatter0.getWidth();
        helpFormatter0.getLongOptPrefix();
    }

    @Test(timeout = 4000)
    public void test018() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        helpFormatter0.setLongOptPrefix("The option '");
        helpFormatter0.getLongOptPrefix();
        assertNotNull(helpFormatter0.getLongOptPrefix());
    }

    @Test(timeout = 4000)
    public void test029() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        int int0 = helpFormatter0.getDescPadding();
        helpFormatter0.getOptPrefix();
    }

    @Test(timeout = 4000)
    public void test0210() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        int int0 = helpFormatter0.getDescPadding();
    }

    @Test(timeout = 4000)
    public void test0211() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        int int0 = helpFormatter0.getDescPadding();
        assertEquals(0, int0);
    }

    @Test(timeout = 4000)
    public void test0212() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        int int0 = helpFormatter0.getDescPadding();
    }

    @Test(timeout = 4000)
    public void test0213() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        int int0 = helpFormatter0.getDescPadding();
        helpFormatter0.getLongOptPrefix();
        assertNotNull(helpFormatter0.getLongOptPrefix());
    }

    @Test(timeout = 4000)
    public void test0214() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        int int0 = helpFormatter0.getDescPadding();
        helpFormatter0.getArgName();
    }

    @Test(timeout = 4000)
    public void test0215() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        int int0 = helpFormatter0.getDescPadding();
        helpFormatter0.getSyntaxPrefix();
    }

    @Test(timeout = 4000)
    public void test0216() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        int int0 = helpFormatter0.getDescPadding();
        helpFormatter0.getLongOptSeparator();
        assertNotNull(helpFormatter0.getLongOptSeparator());
    }

    @Test(timeout = 4000)
    public void test0317() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        helpFormatter0.setLongOptSeparator("}x6OyW~+K|5,$kZ");
        helpFormatter0.getLongOptSeparator();
    }

    @Test(timeout = 4000)
    public void test0418() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        helpFormatter0.setNewLine("--");
        helpFormatter0.getNewLine();
    }

    @Test(timeout = 4000)
    public void test0519() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        helpFormatter0.setLeftPadding(32);
    }

    @Test(timeout = 4000)
    public void test0620() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        helpFormatter0.setArgName("usage: ");
        helpFormatter0.getArgName();
        assertNotNull(helpFormatter0.getArgName());
    }

    @Test(timeout = 4000)
    public void test0821() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        helpFormatter0.setSyntaxPrefix("G/o");
        helpFormatter0.getSyntaxPrefix();
        assertNotNull(helpFormatter0.getSyntaxPrefix());
    }

    @Test(timeout = 4000)
    public void test0922() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getLongOptSeparator();
        helpFormatter0.getSyntaxPrefix();
    }

    @Test(timeout = 4000)
    public void test0923() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getLongOptSeparator();
    }

    @Test(timeout = 4000)
    public void test0924() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getLongOptSeparator();
    }

    @Test(timeout = 4000)
    public void test0925() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getLongOptSeparator();
        helpFormatter0.getOptPrefix();
    }

    @Test(timeout = 4000)
    public void test0926() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getLongOptSeparator();
    }

    @Test(timeout = 4000)
    public void test0927() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getLongOptSeparator();
        helpFormatter0.getArgName();
    }

    @Test(timeout = 4000)
    public void test0928() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getLongOptSeparator();
    }

    @Test(timeout = 4000)
    public void test0929() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getLongOptSeparator();
        helpFormatter0.getLongOptPrefix();
    }

    @Test(timeout = 4000)
    public void test1030() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        helpFormatter0.setWidth(2703);
    }

    @Test(timeout = 4000)
    public void test1131() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        helpFormatter0.setOptPrefix("-");
        helpFormatter0.getArgName();
        assertNotNull(helpFormatter0.getArgName());
    }

    @Test(timeout = 4000)
    public void test1132() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        helpFormatter0.setOptPrefix("-");
    }

    @Test(timeout = 4000)
    public void test1133() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        helpFormatter0.setOptPrefix("-");
        helpFormatter0.getLongOptPrefix();
        assertNotNull(helpFormatter0.getLongOptPrefix());
    }

    @Test(timeout = 4000)
    public void test1134() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        helpFormatter0.setOptPrefix("-");
        helpFormatter0.getLongOptSeparator();
        assertNotNull(helpFormatter0.getLongOptSeparator());
    }

    @Test(timeout = 4000)
    public void test1135() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        helpFormatter0.setOptPrefix("-");
        helpFormatter0.getSyntaxPrefix();
        assertNotNull(helpFormatter0.getSyntaxPrefix());
    }

    @Test(timeout = 4000)
    public void test1136() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        helpFormatter0.setOptPrefix("-");
    }

    @Test(timeout = 4000)
    public void test1137() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        helpFormatter0.setOptPrefix("-");
        helpFormatter0.getOptPrefix();
        assertNotNull(helpFormatter0.getOptPrefix());
    }

    @Test(timeout = 4000)
    public void test1138() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        helpFormatter0.setOptPrefix("-");
    }

    @Test(timeout = 4000)
    public void test1239() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        int int0 = helpFormatter0.getLeftPadding();
        helpFormatter0.getOptPrefix();
        assertNotNull(helpFormatter0.getOptPrefix());
    }

    @Test(timeout = 4000)
    public void test1240() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        int int0 = helpFormatter0.getLeftPadding();
    }

    @Test(timeout = 4000)
    public void test1241() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        int int0 = helpFormatter0.getLeftPadding();
    }

    @Test(timeout = 4000)
    public void test1242() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        int int0 = helpFormatter0.getLeftPadding();
    }

    @Test(timeout = 4000)
    public void test1243() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        int int0 = helpFormatter0.getLeftPadding();
        helpFormatter0.getLongOptPrefix();
        assertNotNull(helpFormatter0.getLongOptPrefix());
    }

    @Test(timeout = 4000)
    public void test1244() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        int int0 = helpFormatter0.getLeftPadding();
        helpFormatter0.getSyntaxPrefix();
        assertNotNull(helpFormatter0.getSyntaxPrefix());
    }

    @Test(timeout = 4000)
    public void test1245() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        int int0 = helpFormatter0.getLeftPadding();
        helpFormatter0.getArgName();
        assertNotNull(helpFormatter0.getArgName());
    }

    @Test(timeout = 4000)
    public void test1246() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        int int0 = helpFormatter0.getLeftPadding();
        helpFormatter0.getLongOptSeparator();
        assertNotNull(helpFormatter0.getLongOptSeparator());
    }

    @Test(timeout = 4000)
    public void test1347() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        MockPrintWriter mockPrintWriter0 = new MockPrintWriter("usage: ");
        Options options0 = new Options();
        helpFormatter0.printHelp((PrintWriter) mockPrintWriter0, 3, " ", "arg", options0, 3, (-454), ", ");
    }

    @Test(timeout = 4000)
    public void test1448() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        helpFormatter0.setDescPadding(1202);
    }

    @Test(timeout = 4000)
    public void test1549() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
    }

    @Test(timeout = 4000)
    public void test1550() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        Options options0 = new Options();
        helpFormatter0.printHelp(" ", " ", options0, " ");
        helpFormatter0.getLongOptSeparator();
        assertNotNull(helpFormatter0.getLongOptSeparator());
    }

    @Test(timeout = 4000)
    public void test1651() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getNewLine();
        helpFormatter0.getLongOptSeparator();
        assertNotNull(helpFormatter0.getLongOptSeparator());
    }

    @Test(timeout = 4000)
    public void test1652() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getNewLine();
        helpFormatter0.getSyntaxPrefix();
    }

    @Test(timeout = 4000)
    public void test1653() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getNewLine();
    }

    @Test(timeout = 4000)
    public void test1654() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getNewLine();
    }

    @Test(timeout = 4000)
    public void test1655() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getNewLine();
    }

    @Test(timeout = 4000)
    public void test1656() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getNewLine();
    }

    @Test(timeout = 4000)
    public void test1657() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getNewLine();
        helpFormatter0.getLongOptPrefix();
        assertNotNull(helpFormatter0.getLongOptPrefix());
    }

    @Test(timeout = 4000)
    public void test1658() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getNewLine();
        helpFormatter0.getArgName();
    }

    @Test(timeout = 4000)
    public void test1659() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getNewLine();
        helpFormatter0.getOptPrefix();
    }

    @Test(timeout = 4000)
    public void test1760() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getLongOptPrefix();
    }

    @Test(timeout = 4000)
    public void test1761() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getLongOptPrefix();
    }

    @Test(timeout = 4000)
    public void test1762() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getLongOptPrefix();
    }

    @Test(timeout = 4000)
    public void test1763() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getLongOptPrefix();
        helpFormatter0.getLongOptSeparator();
    }

    @Test(timeout = 4000)
    public void test1764() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getLongOptPrefix();
        helpFormatter0.getSyntaxPrefix();
    }

    @Test(timeout = 4000)
    public void test1765() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getLongOptPrefix();
        helpFormatter0.getArgName();
        assertNotNull(helpFormatter0.getArgName());
    }

    @Test(timeout = 4000)
    public void test1766() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getLongOptPrefix();
        helpFormatter0.getOptPrefix();
        assertNotNull(helpFormatter0.getOptPrefix());
    }

    @Test(timeout = 4000)
    public void test1767() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getLongOptPrefix();
    }

    @Test(timeout = 4000)
    public void test1868() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getSyntaxPrefix();
    }

    @Test(timeout = 4000)
    public void test1869() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getSyntaxPrefix();
        helpFormatter0.getOptPrefix();
    }

    @Test(timeout = 4000)
    public void test1870() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getSyntaxPrefix();
    }

    @Test(timeout = 4000)
    public void test1871() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getSyntaxPrefix();
        helpFormatter0.getLongOptSeparator();
        assertNotNull(helpFormatter0.getLongOptSeparator());
    }

    @Test(timeout = 4000)
    public void test1872() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getSyntaxPrefix();
        helpFormatter0.getArgName();
        assertNotNull(helpFormatter0.getArgName());
    }

    @Test(timeout = 4000)
    public void test1873() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getSyntaxPrefix();
    }

    @Test(timeout = 4000)
    public void test1874() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getSyntaxPrefix();
    }

    @Test(timeout = 4000)
    public void test1875() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getSyntaxPrefix();
        helpFormatter0.getLongOptPrefix();
    }

    @Test(timeout = 4000)
    public void test1976() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getOptPrefix();
    }

    @Test(timeout = 4000)
    public void test1977() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getOptPrefix();
        helpFormatter0.getLongOptPrefix();
    }

    @Test(timeout = 4000)
    public void test1978() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getOptPrefix();
        helpFormatter0.getArgName();
    }

    @Test(timeout = 4000)
    public void test1979() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getOptPrefix();
    }

    @Test(timeout = 4000)
    public void test1980() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getOptPrefix();
        helpFormatter0.getLongOptSeparator();
        assertNotNull(helpFormatter0.getLongOptSeparator());
    }

    @Test(timeout = 4000)
    public void test1981() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getOptPrefix();
    }

    @Test(timeout = 4000)
    public void test1982() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getOptPrefix();
        helpFormatter0.getSyntaxPrefix();
    }

    @Test(timeout = 4000)
    public void test1983() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getOptPrefix();
    }

    @Test(timeout = 4000)
    public void test2084() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        StringBuffer stringBuffer0 = new StringBuffer(77);
        Options options0 = new Options();
        Options options1 = options0.addOption("arg", false, "");
        Options options2 = options1.addOption("", "", false, "-");
        helpFormatter0.renderOptions(stringBuffer0, 77, options2, 64, 1902);
        stringBuffer0.length();
    }

    @Test(timeout = 4000)
    public void test2185() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        Comparator<Integer> comparator0 = (Comparator<Integer>) mock(Comparator.class, new ViolatedAssumptionAnswer());
        helpFormatter0.setOptionComparator(comparator0);
        helpFormatter0.getOptPrefix();
    }

    @Test(timeout = 4000)
    public void test2186() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        Comparator<Integer> comparator0 = (Comparator<Integer>) mock(Comparator.class, new ViolatedAssumptionAnswer());
        helpFormatter0.setOptionComparator(comparator0);
    }

    @Test(timeout = 4000)
    public void test2187() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        Comparator<Integer> comparator0 = (Comparator<Integer>) mock(Comparator.class, new ViolatedAssumptionAnswer());
        helpFormatter0.setOptionComparator(comparator0);
    }

    @Test(timeout = 4000)
    public void test2188() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        Comparator<Integer> comparator0 = (Comparator<Integer>) mock(Comparator.class, new ViolatedAssumptionAnswer());
        helpFormatter0.setOptionComparator(comparator0);
        helpFormatter0.getLongOptSeparator();
        assertNotNull(helpFormatter0.getLongOptSeparator());
    }

    @Test(timeout = 4000)
    public void test2189() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        Comparator<Integer> comparator0 = (Comparator<Integer>) mock(Comparator.class, new ViolatedAssumptionAnswer());
        helpFormatter0.setOptionComparator(comparator0);
        helpFormatter0.getArgName();
    }

    @Test(timeout = 4000)
    public void test2190() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        Comparator<Integer> comparator0 = (Comparator<Integer>) mock(Comparator.class, new ViolatedAssumptionAnswer());
        helpFormatter0.setOptionComparator(comparator0);
    }

    @Test(timeout = 4000)
    public void test2191() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        Comparator<Integer> comparator0 = (Comparator<Integer>) mock(Comparator.class, new ViolatedAssumptionAnswer());
        helpFormatter0.setOptionComparator(comparator0);
        helpFormatter0.getLongOptPrefix();
        assertNotNull(helpFormatter0.getLongOptPrefix());
    }

    @Test(timeout = 4000)
    public void test2192() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        Comparator<Integer> comparator0 = (Comparator<Integer>) mock(Comparator.class, new ViolatedAssumptionAnswer());
        helpFormatter0.setOptionComparator(comparator0);
        helpFormatter0.getSyntaxPrefix();
        assertNotNull(helpFormatter0.getSyntaxPrefix());
    }

    @Test(timeout = 4000)
    public void test2293() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        helpFormatter0.setOptionComparator((Comparator) null);
        helpFormatter0.getSyntaxPrefix();
        assertNotNull(helpFormatter0.getSyntaxPrefix());
    }

    @Test(timeout = 4000)
    public void test2294() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        helpFormatter0.setOptionComparator((Comparator) null);
    }

    @Test(timeout = 4000)
    public void test2295() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        helpFormatter0.setOptionComparator((Comparator) null);
    }

    @Test(timeout = 4000)
    public void test2296() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        helpFormatter0.setOptionComparator((Comparator) null);
        helpFormatter0.getLongOptSeparator();
        assertNotNull(helpFormatter0.getLongOptSeparator());
    }

    @Test(timeout = 4000)
    public void test2297() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        helpFormatter0.setOptionComparator((Comparator) null);
    }

    @Test(timeout = 4000)
    public void test2298() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        helpFormatter0.setOptionComparator((Comparator) null);
        helpFormatter0.getLongOptPrefix();
        assertNotNull(helpFormatter0.getLongOptPrefix());
    }

    @Test(timeout = 4000)
    public void test2299() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        helpFormatter0.setOptionComparator((Comparator) null);
        helpFormatter0.getArgName();
        assertNotNull(helpFormatter0.getArgName());
    }

    @Test(timeout = 4000)
    public void test22100() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        helpFormatter0.setOptionComparator((Comparator) null);
        helpFormatter0.getOptPrefix();
        assertNotNull(helpFormatter0.getOptPrefix());
    }

    @Test(timeout = 4000)
    public void test23101() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        Options options0 = new Options();
        helpFormatter0.printHelp((String) null, options0);
    }

    @Test(timeout = 4000)
    public void test24102() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        Options options0 = new Options();
        helpFormatter0.printHelp("", options0);
    }

    @Test(timeout = 4000)
    public void test25103() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        Option option0 = new Option("arg", "\n", true, "-");
        Options options0 = new Options();
        OptionGroup optionGroup0 = new OptionGroup();
        OptionGroup optionGroup1 = optionGroup0.addOption(option0);
        Option option1 = new Option("", "NO_ARGS_ALLOWED");
        optionGroup1.addOption(option1);
        Options options1 = options0.addOptionGroup(optionGroup1);
        helpFormatter0.printHelp("--", "--", options1, "arg", true);
        helpFormatter0.getLongOptPrefix();
        assertNotNull(helpFormatter0.getLongOptPrefix());
    }

    @Test(timeout = 4000)
    public void test26104() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
    }

    @Test(timeout = 4000)
    public void test26105() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        Option option0 = new Option("arg", "\n", true, "-");
        Options options0 = new Options();
        OptionGroup optionGroup0 = new OptionGroup();
        OptionGroup optionGroup1 = optionGroup0.addOption(option0);
        optionGroup1.setRequired(true);
        Options options1 = options0.addOptionGroup(optionGroup1);
        helpFormatter0.printHelp("--", "--", options1, "arg", true);
        helpFormatter0.getLongOptSeparator();
        assertNotNull(helpFormatter0.getLongOptSeparator());
    }

    @Test(timeout = 4000)
    public void test26106() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        Option option0 = new Option("arg", "\n", true, "-");
        Options options0 = new Options();
        OptionGroup optionGroup0 = new OptionGroup();
        OptionGroup optionGroup1 = optionGroup0.addOption(option0);
        optionGroup1.setRequired(true);
        Options options1 = options0.addOptionGroup(optionGroup1);
        helpFormatter0.printHelp("--", "--", options1, "arg", true);
        helpFormatter0.getArgName();
        assertNotNull(helpFormatter0.getArgName());
    }

    @Test(timeout = 4000)
    public void test26107() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        Option option0 = new Option("arg", "\n", true, "-");
        Options options0 = new Options();
        OptionGroup optionGroup0 = new OptionGroup();
        OptionGroup optionGroup1 = optionGroup0.addOption(option0);
        optionGroup1.setRequired(true);
        Options options1 = options0.addOptionGroup(optionGroup1);
        helpFormatter0.printHelp("--", "--", options1, "arg", true);
        helpFormatter0.getOptPrefix();
        assertNotNull(helpFormatter0.getOptPrefix());
    }

    @Test(timeout = 4000)
    public void test27108() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        Options options0 = new Options();
        Option option0 = new Option((String) null, "-");
        Options options1 = options0.addOption(option0);
        helpFormatter0.printHelp(helpFormatter0.defaultOptPrefix, helpFormatter0.defaultOptPrefix, options1, helpFormatter0.defaultOptPrefix, true);
    }

    @Test(timeout = 4000)
    public void test28109() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        Option option0 = new Option("", true, "");
        Options options0 = new Options();
        Options options1 = options0.addOption(option0);
        option0.setArgName("-");
        helpFormatter0.printHelp("arg", "", options1, "", true);
    }

    @Test(timeout = 4000)
    public void test28110() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        Option option0 = new Option("", true, "");
        Options options0 = new Options();
        Options options1 = options0.addOption(option0);
        option0.setArgName("-");
        helpFormatter0.printHelp("arg", "", options1, "", true);
    }

    @Test(timeout = 4000)
    public void test29111() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        Option option0 = new Option("", true, "");
        Options options0 = new Options();
        options0.addOption(option0);
        option0.setArgName("");
        helpFormatter0.printHelp("H-A.{cE0ms:r5`O,", "", options0, "", true);
        helpFormatter0.getLongOptPrefix();
        assertNotNull(helpFormatter0.getLongOptPrefix());
    }

    @Test(timeout = 4000)
    public void test30112() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        MockPrintWriter mockPrintWriter0 = new MockPrintWriter("usage: ");
        Options options0 = new Options();
        Options options1 = options0.addOption((String) null, "usage: ", true, ":mi2k**mQ\"bJc}Y");
        helpFormatter0.printUsage((PrintWriter) mockPrintWriter0, 74, "[ARG...]", options1);
    }

    @Test(timeout = 4000)
    public void test30113() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        MockPrintWriter mockPrintWriter0 = new MockPrintWriter("usage: ");
        Options options0 = new Options();
        Options options1 = options0.addOption((String) null, "usage: ", true, ":mi2k**mQ\"bJc}Y");
        helpFormatter0.printUsage((PrintWriter) mockPrintWriter0, 74, "[ARG...]", options1);
    }

    @Test(timeout = 4000)
    public void test30114() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        MockPrintWriter mockPrintWriter0 = new MockPrintWriter("usage: ");
        Options options0 = new Options();
        Options options1 = options0.addOption((String) null, "usage: ", true, ":mi2k**mQ\"bJc}Y");
        helpFormatter0.printUsage((PrintWriter) mockPrintWriter0, 74, "[ARG...]", options1);
        helpFormatter0.getArgName();
        assertNotNull(helpFormatter0.getArgName());
    }

    @Test(timeout = 4000)
    public void test31115() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        Options options0 = new Options();
        Option option0 = new Option("", (String) null);
        Options options1 = options0.addOption(option0);
        helpFormatter0.printHelp(" ", options1, false);
    }

    @Test(timeout = 4000)
    public void test31116() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        Options options0 = new Options();
        Option option0 = new Option("", (String) null);
        Options options1 = options0.addOption(option0);
        helpFormatter0.printHelp(" ", options1, false);
        helpFormatter0.getOptPrefix();
        assertNotNull(helpFormatter0.getOptPrefix());
    }

    @Test(timeout = 4000)
    public void test31117() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        Options options0 = new Options();
        Option option0 = new Option("", (String) null);
        Options options1 = options0.addOption(option0);
        helpFormatter0.printHelp(" ", options1, false);
        helpFormatter0.getSyntaxPrefix();
        assertNotNull(helpFormatter0.getSyntaxPrefix());
    }

    @Test(timeout = 4000)
    public void test32118() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
    }

    @Test(timeout = 4000)
    public void test32119() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        Options options0 = new Options();
        Options options1 = options0.addOption("", true, "\n");
        helpFormatter0.printHelp("s'zn", "usage: ", options1, "s'zn", true);
        helpFormatter0.getArgName();
        assertNotNull(helpFormatter0.getArgName());
    }

    @Test(timeout = 4000)
    public void test33120() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        StringBuffer stringBuffer0 = new StringBuffer();
        helpFormatter0.renderWrappedText(stringBuffer0, 3, 3, "cmdLineSyntax not provided");
    }

    @Test(timeout = 4000)
    public void test34121() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        StringBuffer stringBuffer0 = new StringBuffer(74);
        helpFormatter0.renderWrappedText(stringBuffer0, (-2302), (-2302), "\n");
    }

    @Test(timeout = 4000)
    public void test35122() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        int int0 = helpFormatter0.findWrapPos("\n", (-1), 1);
        helpFormatter0.getArgName();
    }

    @Test(timeout = 4000)
    public void test35123() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        int int0 = helpFormatter0.findWrapPos("\n", (-1), 1);
    }

    @Test(timeout = 4000)
    public void test35124() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        int int0 = helpFormatter0.findWrapPos("\n", (-1), 1);
    }

    @Test(timeout = 4000)
    public void test35125() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        int int0 = helpFormatter0.findWrapPos("\n", (-1), 1);
    }

    @Test(timeout = 4000)
    public void test35126() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        int int0 = helpFormatter0.findWrapPos("\n", (-1), 1);
        helpFormatter0.getOptPrefix();
    }

    @Test(timeout = 4000)
    public void test35127() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        int int0 = helpFormatter0.findWrapPos("\n", (-1), 1);
        helpFormatter0.getLongOptPrefix();
    }

    @Test(timeout = 4000)
    public void test36128() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.rtrim((String) null);
    }

    @Test(timeout = 4000)
    public void test36129() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.rtrim((String) null);
    }

    @Test(timeout = 4000)
    public void test36130() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.rtrim((String) null);
        helpFormatter0.getSyntaxPrefix();
    }

    @Test(timeout = 4000)
    public void test36131() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.rtrim((String) null);
    }

    @Test(timeout = 4000)
    public void test36132() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.rtrim((String) null);
        helpFormatter0.getLongOptSeparator();
    }

    @Test(timeout = 4000)
    public void test36133() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.rtrim((String) null);
    }

    @Test(timeout = 4000)
    public void test36134() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.rtrim((String) null);
        helpFormatter0.getLongOptPrefix();
    }

    @Test(timeout = 4000)
    public void test36135() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.rtrim((String) null);
        helpFormatter0.getArgName();
        assertNotNull(helpFormatter0.getArgName());
    }

    @Test(timeout = 4000)
    public void test36136() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.rtrim((String) null);
        helpFormatter0.getOptPrefix();
    }
}
