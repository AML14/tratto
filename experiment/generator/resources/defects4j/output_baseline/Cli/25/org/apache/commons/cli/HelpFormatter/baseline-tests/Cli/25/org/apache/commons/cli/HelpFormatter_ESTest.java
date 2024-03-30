/*
 * This file was automatically generated by EvoSuite
 * Thu Dec 14 23:46:13 GMT 2023
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
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class HelpFormatter_ESTest extends HelpFormatter_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        int int0 = helpFormatter0.getWidth();
        helpFormatter0.getLongOptPrefix();
    }

    @Test(timeout = 4000)
    public void test001() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        int int0 = helpFormatter0.getWidth();
    }

    @Test(timeout = 4000)
    public void test002() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        int int0 = helpFormatter0.getWidth();
        helpFormatter0.getArgName();
    }

    @Test(timeout = 4000)
    public void test003() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        int int0 = helpFormatter0.getWidth();
        helpFormatter0.getOptPrefix();
    }

    @Test(timeout = 4000)
    public void test006() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        int int0 = helpFormatter0.getWidth();
        helpFormatter0.getSyntaxPrefix();
    }

    @Test(timeout = 4000)
    public void test017() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        helpFormatter0.setLongOptPrefix("usage: ");
        helpFormatter0.getLongOptPrefix();
    }

    @Test(timeout = 4000)
    public void test028() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        int int0 = helpFormatter0.getDescPadding();
        helpFormatter0.getLongOptPrefix();
    }

    @Test(timeout = 4000)
    public void test029() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        int int0 = helpFormatter0.getDescPadding();
    }

    @Test(timeout = 4000)
    public void test0210() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        int int0 = helpFormatter0.getDescPadding();
        helpFormatter0.getArgName();
    }

    @Test(timeout = 4000)
    public void test0211() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        int int0 = helpFormatter0.getDescPadding();
        helpFormatter0.getOptPrefix();
    }

    @Test(timeout = 4000)
    public void test0214() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        int int0 = helpFormatter0.getDescPadding();
        helpFormatter0.getSyntaxPrefix();
    }

    @Test(timeout = 4000)
    public void test0315() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        helpFormatter0.setNewLine("arg");
        helpFormatter0.getNewLine();
    }

    @Test(timeout = 4000)
    public void test0416() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        helpFormatter0.setArgName("-");
        helpFormatter0.getArgName();
    }

    @Test(timeout = 4000)
    public void test0517() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        helpFormatter0.printHelp((PrintWriter) null, 3, "arg", "arg", (Options) null, 3, 3, "arg");
    }

    @Test(timeout = 4000)
    public void test0618() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        helpFormatter0.setSyntaxPrefix("--");
        helpFormatter0.getSyntaxPrefix();
    }

    @Test(timeout = 4000)
    public void test0719() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        helpFormatter0.printHelp(1, "--", "--", (Options) null, "--");
    }

    @Test(timeout = 4000)
    public void test0820() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        helpFormatter0.setWidth(3);
    }

    @Test(timeout = 4000)
    public void test0921() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        helpFormatter0.setOptPrefix("--");
        helpFormatter0.getOptPrefix();
    }

    @Test(timeout = 4000)
    public void test1022() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        int int0 = helpFormatter0.getLeftPadding();
        helpFormatter0.getSyntaxPrefix();
    }

    @Test(timeout = 4000)
    public void test1023() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        int int0 = helpFormatter0.getLeftPadding();
    }

    @Test(timeout = 4000)
    public void test1024() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        int int0 = helpFormatter0.getLeftPadding();
        helpFormatter0.getLongOptPrefix();
    }

    @Test(timeout = 4000)
    public void test1026() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        int int0 = helpFormatter0.getLeftPadding();
        helpFormatter0.getOptPrefix();
    }

    @Test(timeout = 4000)
    public void test1028() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        int int0 = helpFormatter0.getLeftPadding();
        helpFormatter0.getArgName();
    }

    @Test(timeout = 4000)
    public void test1129() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getArgName();
    }

    @Test(timeout = 4000)
    public void test1131() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getArgName();
        helpFormatter0.getLongOptPrefix();
    }

    @Test(timeout = 4000)
    public void test1132() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getArgName();
        helpFormatter0.getSyntaxPrefix();
    }

    @Test(timeout = 4000)
    public void test1135() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getArgName();
        helpFormatter0.getOptPrefix();
    }

    @Test(timeout = 4000)
    public void test1236() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        helpFormatter0.setDescPadding(3);
    }

    @Test(timeout = 4000)
    public void test1237() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        helpFormatter0.setDescPadding(3);
        helpFormatter0.getSyntaxPrefix();
    }

    @Test(timeout = 4000)
    public void test1238() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        helpFormatter0.setDescPadding(3);
        helpFormatter0.getLongOptPrefix();
    }

    @Test(timeout = 4000)
    public void test1239() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        helpFormatter0.setDescPadding(3);
        helpFormatter0.getArgName();
    }

    @Test(timeout = 4000)
    public void test1240() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        helpFormatter0.setDescPadding(3);
        helpFormatter0.getOptPrefix();
    }

    @Test(timeout = 4000)
    public void test1343() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        Options options0 = new Options();
        helpFormatter0.printHelp("arg", "arg", options0, "arg");
    }

    @Test(timeout = 4000)
    public void test1445() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getNewLine();
    }

    @Test(timeout = 4000)
    public void test1446() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getNewLine();
        helpFormatter0.getSyntaxPrefix();
    }

    @Test(timeout = 4000)
    public void test1447() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getNewLine();
        helpFormatter0.getOptPrefix();
    }

    @Test(timeout = 4000)
    public void test1449() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getNewLine();
        helpFormatter0.getArgName();
    }

    @Test(timeout = 4000)
    public void test1451() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getNewLine();
        helpFormatter0.getLongOptPrefix();
    }

    @Test(timeout = 4000)
    public void test1553() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getLongOptPrefix();
        helpFormatter0.getOptPrefix();
    }

    @Test(timeout = 4000)
    public void test1554() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getLongOptPrefix();
    }

    @Test(timeout = 4000)
    public void test1555() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getLongOptPrefix();
        helpFormatter0.getSyntaxPrefix();
    }

    @Test(timeout = 4000)
    public void test1557() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getLongOptPrefix();
        helpFormatter0.getArgName();
    }

    @Test(timeout = 4000)
    public void test1660() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getSyntaxPrefix();
    }

    @Test(timeout = 4000)
    public void test1661() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getSyntaxPrefix();
        helpFormatter0.getLongOptPrefix();
    }

    @Test(timeout = 4000)
    public void test1662() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getSyntaxPrefix();
        helpFormatter0.getOptPrefix();
    }

    @Test(timeout = 4000)
    public void test1664() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getSyntaxPrefix();
        helpFormatter0.getArgName();
    }

    @Test(timeout = 4000)
    public void test1767() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getOptPrefix();
        helpFormatter0.getArgName();
    }

    @Test(timeout = 4000)
    public void test1768() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getOptPrefix();
    }

    @Test(timeout = 4000)
    public void test1771() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getOptPrefix();
        helpFormatter0.getSyntaxPrefix();
    }

    @Test(timeout = 4000)
    public void test1772() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.getOptPrefix();
        helpFormatter0.getLongOptPrefix();
    }

    @Test(timeout = 4000)
    public void test1874() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
    }

    @Test(timeout = 4000)
    public void test1875() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        Options options0 = new Options();
        OptionGroup optionGroup0 = new OptionGroup();
        Option option0 = new Option("", "");
        optionGroup0.addOption(option0);
        Option option1 = new Option("arg", "--");
        optionGroup0.addOption(option1);
        Options options1 = options0.addOptionGroup(optionGroup0);
        helpFormatter0.printHelp("-", "-", options1, "-", true);
    }

    @Test(timeout = 4000)
    public void test1976() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        Comparator<String> comparator0 = (Comparator<String>) mock(Comparator.class, new ViolatedAssumptionAnswer());
        helpFormatter0.setOptionComparator(comparator0);
    }

    @Test(timeout = 4000)
    public void test1977() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        Comparator<String> comparator0 = (Comparator<String>) mock(Comparator.class, new ViolatedAssumptionAnswer());
        helpFormatter0.setOptionComparator(comparator0);
        helpFormatter0.getOptPrefix();
    }

    @Test(timeout = 4000)
    public void test1978() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        Comparator<String> comparator0 = (Comparator<String>) mock(Comparator.class, new ViolatedAssumptionAnswer());
        helpFormatter0.setOptionComparator(comparator0);
        helpFormatter0.getLongOptPrefix();
    }

    @Test(timeout = 4000)
    public void test1980() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        Comparator<String> comparator0 = (Comparator<String>) mock(Comparator.class, new ViolatedAssumptionAnswer());
        helpFormatter0.setOptionComparator(comparator0);
        helpFormatter0.getSyntaxPrefix();
    }

    @Test(timeout = 4000)
    public void test1981() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        Comparator<String> comparator0 = (Comparator<String>) mock(Comparator.class, new ViolatedAssumptionAnswer());
        helpFormatter0.setOptionComparator(comparator0);
        helpFormatter0.getArgName();
    }

    @Test(timeout = 4000)
    public void test2083() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        helpFormatter0.setOptionComparator((Comparator) null);
    }

    @Test(timeout = 4000)
    public void test2084() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        helpFormatter0.setOptionComparator((Comparator) null);
        helpFormatter0.getArgName();
    }

    @Test(timeout = 4000)
    public void test2085() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        helpFormatter0.setOptionComparator((Comparator) null);
        helpFormatter0.getLongOptPrefix();
    }

    @Test(timeout = 4000)
    public void test2087() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        helpFormatter0.setOptionComparator((Comparator) null);
        helpFormatter0.getSyntaxPrefix();
    }

    @Test(timeout = 4000)
    public void test2089() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        helpFormatter0.setOptionComparator((Comparator) null);
        helpFormatter0.getOptPrefix();
    }

    @Test(timeout = 4000)
    public void test2190() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        Options options0 = new Options();
        helpFormatter0.printHelp((String) null, options0);
    }

    @Test(timeout = 4000)
    public void test2291() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        Options options0 = new Options();
        helpFormatter0.printHelp("", "", options0, "", false);
    }

    @Test(timeout = 4000)
    public void test2392() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        Options options0 = new Options();
        helpFormatter0.printHelp("\n", "\n", options0, "\n", false);
        helpFormatter0.getArgName();
    }

    @Test(timeout = 4000)
    public void test2493() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        Options options0 = new Options();
        OptionGroup optionGroup0 = new OptionGroup();
        Option option0 = new Option("arg", "--");
        optionGroup0.setRequired(true);
        optionGroup0.addOption(option0);
        Options options1 = options0.addOptionGroup(optionGroup0);
        helpFormatter0.printHelp("-", "-", options1, "-", true);
        helpFormatter0.getSyntaxPrefix();
    }

    @Test(timeout = 4000)
    public void test2594() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        Options options0 = new Options();
        OptionGroup optionGroup0 = new OptionGroup();
        Option option0 = new Option("arg", true, "usage: ");
        option0.setArgName("");
        OptionGroup optionGroup1 = optionGroup0.addOption(option0);
        options0.addOptionGroup(optionGroup1);
        helpFormatter0.printHelp("PNy3", "-", options0, "", true);
    }

    @Test(timeout = 4000)
    public void test2695() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        Options options0 = new Options();
        OptionGroup optionGroup0 = new OptionGroup();
        Option option0 = new Option("arg", "-", true, "usage: ");
        optionGroup0.addOption(option0);
        Options options1 = options0.addOptionGroup(optionGroup0);
        helpFormatter0.printHelp("-", "-", options1, "-", true);
        helpFormatter0.getArgName();
    }

    @Test(timeout = 4000)
    public void test2796() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        Options options0 = new Options();
        Options options1 = options0.addOption("", true, "-");
        Option option0 = new Option("arg", "A6gNYx$5F:av`z}z!5");
        Options options2 = options1.addOption(option0);
        helpFormatter0.printHelp("A6gNYx$5F:av`z}z!5", "--", options2, "A6gNYx$5F:av`z}z!5", true);
        helpFormatter0.getArgName();
    }

    @Test(timeout = 4000)
    public void test2897() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        Options options0 = new Options();
        helpFormatter0.setLeftPadding(74);
        Options options1 = options0.addOption((String) null, true, "usage: ");
        helpFormatter0.printHelp("-", options1, true);
        helpFormatter0.getOptPrefix();
    }

    @Test(timeout = 4000)
    public void test2898() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        Options options0 = new Options();
        helpFormatter0.setLeftPadding(74);
        Options options1 = options0.addOption((String) null, true, "usage: ");
        helpFormatter0.printHelp("-", options1, true);
    }

    @Test(timeout = 4000)
    public void test2999() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        Options options0 = new Options();
        Option option0 = new Option("", false, (String) null);
        Options options1 = options0.addOption(option0);
        helpFormatter0.printHelp("--", "--", options1, "--", false);
    }

    @Test(timeout = 4000)
    public void test30100() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        helpFormatter0.printUsage((PrintWriter) null, 3, "bLCaJ|E .");
    }

    @Test(timeout = 4000)
    public void test31101() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        helpFormatter0.findWrapPos("\n", (-896), (-896));
    }

    @Test(timeout = 4000)
    public void test32102() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        int int0 = helpFormatter0.findWrapPos("\n", (-1), 1);
        helpFormatter0.getLongOptPrefix();
    }

    @Test(timeout = 4000)
    public void test32103() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        int int0 = helpFormatter0.findWrapPos("\n", (-1), 1);
    }

    @Test(timeout = 4000)
    public void test33106() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.rtrim((String) null);
        helpFormatter0.getLongOptPrefix();
    }

    @Test(timeout = 4000)
    public void test33107() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.rtrim((String) null);
        helpFormatter0.getOptPrefix();
    }

    @Test(timeout = 4000)
    public void test33108() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.rtrim((String) null);
    }

    @Test(timeout = 4000)
    public void test33110() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.rtrim((String) null);
        helpFormatter0.getSyntaxPrefix();
    }

    @Test(timeout = 4000)
    public void test33111() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.rtrim((String) null);
        helpFormatter0.getArgName();
    }

    @Test(timeout = 4000)
    public void test34114() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        StringBuffer stringBuffer0 = new StringBuffer((CharSequence) "--");
        StringBuffer stringBuffer1 = helpFormatter0.renderWrappedText(stringBuffer0, 3, 3, "");
    }

    @Test(timeout = 4000)
    public void test34116() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        StringBuffer stringBuffer0 = new StringBuffer((CharSequence) "--");
        StringBuffer stringBuffer1 = helpFormatter0.renderWrappedText(stringBuffer0, 3, 3, "");
        stringBuffer1.length();
    }

    @Test(timeout = 4000)
    public void test34117() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        StringBuffer stringBuffer0 = new StringBuffer((CharSequence) "--");
        StringBuffer stringBuffer1 = helpFormatter0.renderWrappedText(stringBuffer0, 3, 3, "");
        helpFormatter0.getArgName();
    }

    @Test(timeout = 4000)
    public void test35119() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.rtrim("\n");
    }

    @Test(timeout = 4000)
    public void test35120() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.rtrim("\n");
        helpFormatter0.getArgName();
    }

    @Test(timeout = 4000)
    public void test35122() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.rtrim("\n");
        helpFormatter0.getOptPrefix();
    }

    @Test(timeout = 4000)
    public void test35124() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.rtrim("\n");
        helpFormatter0.getSyntaxPrefix();
    }

    @Test(timeout = 4000)
    public void test35125() throws Throwable {
        HelpFormatter helpFormatter0 = new HelpFormatter();
        String string0 = helpFormatter0.rtrim("\n");
        helpFormatter0.getLongOptPrefix();
    }
}
