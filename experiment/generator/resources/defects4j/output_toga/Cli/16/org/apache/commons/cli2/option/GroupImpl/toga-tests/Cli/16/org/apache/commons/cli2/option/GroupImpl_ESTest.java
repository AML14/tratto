/*
 * This file was automatically generated by EvoSuite
 * Thu Dec 14 23:15:54 GMT 2023
 */
package org.apache.commons.cli2.option;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Set;
import org.apache.commons.cli2.DisplaySetting;
import org.apache.commons.cli2.Option;
import org.apache.commons.cli2.WriteableCommandLine;
import org.apache.commons.cli2.commandline.WriteableCommandLineImpl;
import org.apache.commons.cli2.option.ArgumentImpl;
import org.apache.commons.cli2.option.Command;
import org.apache.commons.cli2.option.DefaultOption;
import org.apache.commons.cli2.option.GroupImpl;
import org.apache.commons.cli2.option.PropertyOption;
import org.apache.commons.cli2.option.SourceDestArgument;
import org.apache.commons.cli2.validation.FileValidator;
import org.apache.commons.cli2.validation.NumberValidator;
import org.apache.commons.cli2.validation.UrlValidator;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class GroupImpl_ESTest extends GroupImpl_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        LinkedList<GroupImpl> linkedList0 = new LinkedList<GroupImpl>();
        GroupImpl groupImpl0 = new GroupImpl(linkedList0, "", "", 60, 60);
        groupImpl0.getAnonymous();
        groupImpl0.getMaximum();
        assertEquals(0, groupImpl0.getMaximum());
    }

    @Test(timeout = 4000)
    public void test001() throws Throwable {
        LinkedList<GroupImpl> linkedList0 = new LinkedList<GroupImpl>();
        GroupImpl groupImpl0 = new GroupImpl(linkedList0, "", "", 60, 60);
        groupImpl0.getAnonymous();
        groupImpl0.getMinimum();
        assertEquals(0, groupImpl0.getMinimum());
    }

    @Test(timeout = 4000)
    public void test012() throws Throwable {
        LinkedList<GroupImpl> linkedList0 = new LinkedList<GroupImpl>();
        GroupImpl groupImpl0 = new GroupImpl(linkedList0, "6fH~_%MC", "6fH~_%MC", 3, 3);
        int int0 = groupImpl0.getMaximum();
    }

    @Test(timeout = 4000)
    public void test013() throws Throwable {
        LinkedList<GroupImpl> linkedList0 = new LinkedList<GroupImpl>();
        GroupImpl groupImpl0 = new GroupImpl(linkedList0, "6fH~_%MC", "6fH~_%MC", 3, 3);
        int int0 = groupImpl0.getMaximum();
        groupImpl0.getMinimum();
    }

    @Test(timeout = 4000)
    public void test024() throws Throwable {
        LinkedList<PropertyOption> linkedList0 = new LinkedList<PropertyOption>();
        PropertyOption propertyOption0 = PropertyOption.INSTANCE;
        linkedList0.add(propertyOption0);
        GroupImpl groupImpl0 = new GroupImpl(linkedList0, "Passes properties and values to the application", "Passes properties and values to the application", 0, 0);
        groupImpl0.findOption("Passes properties and values to the application");
        linkedList0.size();
    }

    @Test(timeout = 4000)
    public void test025() throws Throwable {
        LinkedList<PropertyOption> linkedList0 = new LinkedList<PropertyOption>();
        PropertyOption propertyOption0 = PropertyOption.INSTANCE;
        linkedList0.add(propertyOption0);
        GroupImpl groupImpl0 = new GroupImpl(linkedList0, "Passes properties and values to the application", "Passes properties and values to the application", 0, 0);
        groupImpl0.findOption("Passes properties and values to the application");
        groupImpl0.getMinimum();
        assertEquals(0, groupImpl0.getMinimum());
    }

    @Test(timeout = 4000)
    public void test036() throws Throwable {
        LinkedList<Object> linkedList0 = new LinkedList<Object>();
        UrlValidator urlValidator0 = new UrlValidator("");
        ArgumentImpl argumentImpl0 = new ArgumentImpl("", "", 217, 217, '~', '~', urlValidator0, (String) null, linkedList0, '~');
        SourceDestArgument sourceDestArgument0 = new SourceDestArgument(argumentImpl0, argumentImpl0);
        linkedList0.add((Object) sourceDestArgument0);
        GroupImpl groupImpl0 = new GroupImpl(linkedList0, "EZfj7W|", "--", 217, '\u0000');
        groupImpl0.toString();
        linkedList0.size();
    }

    @Test(timeout = 4000)
    public void test037() throws Throwable {
        LinkedList<Object> linkedList0 = new LinkedList<Object>();
        UrlValidator urlValidator0 = new UrlValidator("");
        ArgumentImpl argumentImpl0 = new ArgumentImpl("", "", 217, 217, '~', '~', urlValidator0, (String) null, linkedList0, '~');
        SourceDestArgument sourceDestArgument0 = new SourceDestArgument(argumentImpl0, argumentImpl0);
        linkedList0.add((Object) sourceDestArgument0);
        GroupImpl groupImpl0 = new GroupImpl(linkedList0, "EZfj7W|", "--", 217, '\u0000');
        groupImpl0.toString();
        groupImpl0.getMaximum();
    }

    @Test(timeout = 4000)
    public void test048() throws Throwable {
        LinkedList<GroupImpl> linkedList0 = new LinkedList<GroupImpl>();
        GroupImpl groupImpl0 = new GroupImpl(linkedList0, "org.apache.commons.cli2.option.SourceDestArgument", "org.apache.commons.cli2.option.SourceDestArgument", 0, 0);
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(groupImpl0, linkedList0);
        boolean boolean0 = groupImpl0.canProcess((WriteableCommandLine) writeableCommandLineImpl0, (String) null);
        groupImpl0.getMinimum();
        assertEquals(0, groupImpl0.getMinimum());
    }

    @Test(timeout = 4000)
    public void test049() throws Throwable {
        LinkedList<GroupImpl> linkedList0 = new LinkedList<GroupImpl>();
        GroupImpl groupImpl0 = new GroupImpl(linkedList0, "org.apache.commons.cli2.option.SourceDestArgument", "org.apache.commons.cli2.option.SourceDestArgument", 0, 0);
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(groupImpl0, linkedList0);
        boolean boolean0 = groupImpl0.canProcess((WriteableCommandLine) writeableCommandLineImpl0, (String) null);
        groupImpl0.getMaximum();
    }

    @Test(timeout = 4000)
    public void test0410() throws Throwable {
        LinkedList<GroupImpl> linkedList0 = new LinkedList<GroupImpl>();
        GroupImpl groupImpl0 = new GroupImpl(linkedList0, "org.apache.commons.cli2.option.SourceDestArgument", "org.apache.commons.cli2.option.SourceDestArgument", 0, 0);
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(groupImpl0, linkedList0);
        boolean boolean0 = groupImpl0.canProcess((WriteableCommandLine) writeableCommandLineImpl0, (String) null);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test0511() throws Throwable {
        LinkedList<PropertyOption> linkedList0 = new LinkedList<PropertyOption>();
        PropertyOption propertyOption0 = PropertyOption.INSTANCE;
        linkedList0.add(propertyOption0);
        GroupImpl groupImpl0 = new GroupImpl(linkedList0, "", "-D", (-784), (-784));
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(groupImpl0, linkedList0);
        boolean boolean0 = groupImpl0.canProcess((WriteableCommandLine) writeableCommandLineImpl0, "-D");
        linkedList0.size();
    }

    @Test(timeout = 4000)
    public void test0512() throws Throwable {
        LinkedList<PropertyOption> linkedList0 = new LinkedList<PropertyOption>();
        PropertyOption propertyOption0 = PropertyOption.INSTANCE;
        linkedList0.add(propertyOption0);
        GroupImpl groupImpl0 = new GroupImpl(linkedList0, "", "-D", (-784), (-784));
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(groupImpl0, linkedList0);
        boolean boolean0 = groupImpl0.canProcess((WriteableCommandLine) writeableCommandLineImpl0, "-D");
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test0613() throws Throwable {
        LinkedList<PropertyOption> linkedList0 = new LinkedList<PropertyOption>();
        PropertyOption propertyOption0 = PropertyOption.INSTANCE;
        linkedList0.add(propertyOption0);
        GroupImpl groupImpl0 = new GroupImpl(linkedList0, "w71WR", "w71WR", 0, 0);
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(groupImpl0, linkedList0);
        boolean boolean0 = groupImpl0.canProcess((WriteableCommandLine) writeableCommandLineImpl0, "w71WR");
        linkedList0.size();
    }

    @Test(timeout = 4000)
    public void test0614() throws Throwable {
        LinkedList<PropertyOption> linkedList0 = new LinkedList<PropertyOption>();
        PropertyOption propertyOption0 = PropertyOption.INSTANCE;
        linkedList0.add(propertyOption0);
        GroupImpl groupImpl0 = new GroupImpl(linkedList0, "w71WR", "w71WR", 0, 0);
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(groupImpl0, linkedList0);
        boolean boolean0 = groupImpl0.canProcess((WriteableCommandLine) writeableCommandLineImpl0, "w71WR");
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test0715() throws Throwable {
        LinkedList<PropertyOption> linkedList0 = new LinkedList<PropertyOption>();
        PropertyOption propertyOption0 = new PropertyOption("", "", 2676);
        linkedList0.add(propertyOption0);
        GroupImpl groupImpl0 = new GroupImpl(linkedList0, "", "", (-1968), (-1968));
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(groupImpl0, linkedList0);
        boolean boolean0 = groupImpl0.canProcess((WriteableCommandLine) writeableCommandLineImpl0, "-D");
        linkedList0.size();
    }

    @Test(timeout = 4000)
    public void test0716() throws Throwable {
        LinkedList<PropertyOption> linkedList0 = new LinkedList<PropertyOption>();
        PropertyOption propertyOption0 = new PropertyOption("", "", 2676);
        linkedList0.add(propertyOption0);
        GroupImpl groupImpl0 = new GroupImpl(linkedList0, "", "", (-1968), (-1968));
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(groupImpl0, linkedList0);
        boolean boolean0 = groupImpl0.canProcess((WriteableCommandLine) writeableCommandLineImpl0, "-D");
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test0817() throws Throwable {
        LinkedList<PropertyOption> linkedList0 = new LinkedList<PropertyOption>();
        PropertyOption propertyOption0 = new PropertyOption();
        GroupImpl groupImpl0 = new GroupImpl(linkedList0, "", (String) null, (-283), (-283));
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(propertyOption0, linkedList0);
        boolean boolean0 = groupImpl0.canProcess((WriteableCommandLine) writeableCommandLineImpl0, "-D");
        groupImpl0.getMinimum();
        assertEquals(0, groupImpl0.getMinimum());
    }

    @Test(timeout = 4000)
    public void test0818() throws Throwable {
        LinkedList<PropertyOption> linkedList0 = new LinkedList<PropertyOption>();
        PropertyOption propertyOption0 = new PropertyOption();
        GroupImpl groupImpl0 = new GroupImpl(linkedList0, "", (String) null, (-283), (-283));
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(propertyOption0, linkedList0);
        boolean boolean0 = groupImpl0.canProcess((WriteableCommandLine) writeableCommandLineImpl0, "-D");
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test0819() throws Throwable {
        LinkedList<PropertyOption> linkedList0 = new LinkedList<PropertyOption>();
        PropertyOption propertyOption0 = new PropertyOption();
        GroupImpl groupImpl0 = new GroupImpl(linkedList0, "", (String) null, (-283), (-283));
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(propertyOption0, linkedList0);
        boolean boolean0 = groupImpl0.canProcess((WriteableCommandLine) writeableCommandLineImpl0, "-D");
        groupImpl0.getMaximum();
        assertEquals(0, groupImpl0.getMaximum());
    }

    @Test(timeout = 4000)
    public void test0920() throws Throwable {
        LinkedList<SourceDestArgument> linkedList0 = new LinkedList<SourceDestArgument>();
        FileValidator fileValidator0 = FileValidator.getExistingDirectoryInstance();
        ArgumentImpl argumentImpl0 = new ArgumentImpl("x:zZJ-", "x:zZJ-", 61, 61, '7', '7', fileValidator0, "x:zZJ-", linkedList0, 61);
        LinkedList<DefaultOption> linkedList1 = new LinkedList<DefaultOption>();
        SourceDestArgument sourceDestArgument0 = new SourceDestArgument(argumentImpl0, argumentImpl0, 'j', 'j', "--", linkedList1);
        linkedList0.add(sourceDestArgument0);
        GroupImpl groupImpl0 = new GroupImpl(linkedList0, "\"1ez(U #n_t", "\"1ez(U #n_t", '7', '7');
        LinkedList<GroupImpl> linkedList2 = new LinkedList<GroupImpl>();
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(sourceDestArgument0, linkedList2);
        boolean boolean0 = groupImpl0.canProcess((WriteableCommandLine) writeableCommandLineImpl0, "--");
        linkedList0.contains(sourceDestArgument0);
    }

    @Test(timeout = 4000)
    public void test0921() throws Throwable {
        LinkedList<SourceDestArgument> linkedList0 = new LinkedList<SourceDestArgument>();
        FileValidator fileValidator0 = FileValidator.getExistingDirectoryInstance();
        ArgumentImpl argumentImpl0 = new ArgumentImpl("x:zZJ-", "x:zZJ-", 61, 61, '7', '7', fileValidator0, "x:zZJ-", linkedList0, 61);
        LinkedList<DefaultOption> linkedList1 = new LinkedList<DefaultOption>();
        SourceDestArgument sourceDestArgument0 = new SourceDestArgument(argumentImpl0, argumentImpl0, 'j', 'j', "--", linkedList1);
        linkedList0.add(sourceDestArgument0);
        GroupImpl groupImpl0 = new GroupImpl(linkedList0, "\"1ez(U #n_t", "\"1ez(U #n_t", '7', '7');
        LinkedList<GroupImpl> linkedList2 = new LinkedList<GroupImpl>();
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(sourceDestArgument0, linkedList2);
        boolean boolean0 = groupImpl0.canProcess((WriteableCommandLine) writeableCommandLineImpl0, "--");
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test1022() throws Throwable {
        LinkedList<GroupImpl> linkedList0 = new LinkedList<GroupImpl>();
        GroupImpl groupImpl0 = new GroupImpl(linkedList0, "DISPLAY_GRUP_OUT/ER", "DISPLAY_GRUP_OUT/ER", 0, 0);
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(groupImpl0, linkedList0);
        ListIterator<GroupImpl> listIterator0 = linkedList0.listIterator();
        groupImpl0.process(writeableCommandLineImpl0, listIterator0);
        groupImpl0.getMaximum();
    }

    @Test(timeout = 4000)
    public void test1023() throws Throwable {
        LinkedList<GroupImpl> linkedList0 = new LinkedList<GroupImpl>();
        GroupImpl groupImpl0 = new GroupImpl(linkedList0, "DISPLAY_GRUP_OUT/ER", "DISPLAY_GRUP_OUT/ER", 0, 0);
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(groupImpl0, linkedList0);
        ListIterator<GroupImpl> listIterator0 = linkedList0.listIterator();
        groupImpl0.process(writeableCommandLineImpl0, listIterator0);
        groupImpl0.getMinimum();
        assertEquals(1, groupImpl0.getMinimum());
    }

    @Test(timeout = 4000)
    public void test1124() throws Throwable {
        LinkedList<PropertyOption> linkedList0 = new LinkedList<PropertyOption>();
        GroupImpl groupImpl0 = new GroupImpl(linkedList0, "Passes properties and values to the application", "-D", (-2163), (-2163));
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(groupImpl0, linkedList0);
        Set<String> set0 = ZoneId.getAvailableZoneIds();
        LinkedList<String> linkedList1 = new LinkedList<String>(set0);
        ListIterator<String> listIterator0 = linkedList1.listIterator();
        groupImpl0.process(writeableCommandLineImpl0, listIterator0);
        groupImpl0.getPreferredName();
        assertNotNull(groupImpl0.getPreferredName());
    }

    @Test(timeout = 4000)
    public void test1125() throws Throwable {
        LinkedList<PropertyOption> linkedList0 = new LinkedList<PropertyOption>();
        GroupImpl groupImpl0 = new GroupImpl(linkedList0, "Passes properties and values to the application", "-D", (-2163), (-2163));
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(groupImpl0, linkedList0);
        Set<String> set0 = ZoneId.getAvailableZoneIds();
        LinkedList<String> linkedList1 = new LinkedList<String>(set0);
        ListIterator<String> listIterator0 = linkedList1.listIterator();
        groupImpl0.process(writeableCommandLineImpl0, listIterator0);
        groupImpl0.getMinimum();
        assertEquals(1, groupImpl0.getMinimum());
    }

    @Test(timeout = 4000)
    public void test1126() throws Throwable {
        LinkedList<PropertyOption> linkedList0 = new LinkedList<PropertyOption>();
        GroupImpl groupImpl0 = new GroupImpl(linkedList0, "Passes properties and values to the application", "-D", (-2163), (-2163));
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(groupImpl0, linkedList0);
        Set<String> set0 = ZoneId.getAvailableZoneIds();
        LinkedList<String> linkedList1 = new LinkedList<String>(set0);
        ListIterator<String> listIterator0 = linkedList1.listIterator();
        groupImpl0.process(writeableCommandLineImpl0, listIterator0);
        groupImpl0.getMaximum();
    }

    @Test(timeout = 4000)
    public void test1127() throws Throwable {
        LinkedList<PropertyOption> linkedList0 = new LinkedList<PropertyOption>();
        GroupImpl groupImpl0 = new GroupImpl(linkedList0, "Passes properties and values to the application", "-D", (-2163), (-2163));
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(groupImpl0, linkedList0);
        Set<String> set0 = ZoneId.getAvailableZoneIds();
        LinkedList<String> linkedList1 = new LinkedList<String>(set0);
        ListIterator<String> listIterator0 = linkedList1.listIterator();
        groupImpl0.process(writeableCommandLineImpl0, listIterator0);
        groupImpl0.getDescription();
        assertNotNull(groupImpl0.getDescription());
    }

    @Test(timeout = 4000)
    public void test1228() throws Throwable {
        LinkedList<PropertyOption> linkedList0 = new LinkedList<PropertyOption>();
        PropertyOption propertyOption0 = new PropertyOption("", "", 2676);
        linkedList0.add(propertyOption0);
        GroupImpl groupImpl0 = new GroupImpl(linkedList0, "", "-D", 2676, 2676);
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(groupImpl0, linkedList0);
        linkedList0.size();
    }

    @Test(timeout = 4000)
    public void test1229() throws Throwable {
        LinkedList<PropertyOption> linkedList0 = new LinkedList<PropertyOption>();
        PropertyOption propertyOption0 = new PropertyOption("", "", 2676);
        linkedList0.add(propertyOption0);
        GroupImpl groupImpl0 = new GroupImpl(linkedList0, "", "-D", 2676, 2676);
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(groupImpl0, linkedList0);
        ListIterator<String> listIterator0 = (ListIterator<String>) mock(ListIterator.class, new ViolatedAssumptionAnswer());
        doReturn(true, false).when(listIterator0).hasNext();
        doReturn("Passes properties and values to the application", (Object) null, (Object) null).when(listIterator0).next();
        doReturn((Object) null, (Object) null).when(listIterator0).previous();
        groupImpl0.process(writeableCommandLineImpl0, listIterator0);
        groupImpl0.getMinimum();
        assertEquals(0, groupImpl0.getMinimum());
    }

    @Test(timeout = 4000)
    public void test1330() throws Throwable {
        LinkedList<PropertyOption> linkedList0 = new LinkedList<PropertyOption>();
        PropertyOption propertyOption0 = PropertyOption.INSTANCE;
        linkedList0.add(propertyOption0);
        PropertyOption propertyOption1 = new PropertyOption("", "", 2676);
        linkedList0.add(propertyOption1);
        GroupImpl groupImpl0 = new GroupImpl(linkedList0, "", "-D", 2676, 2676);
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(groupImpl0, linkedList0);
        ListIterator<String> listIterator0 = (ListIterator<String>) mock(ListIterator.class, new ViolatedAssumptionAnswer());
        doReturn(true, false).when(listIterator0).hasNext();
        doReturn("Passes properties and values to the application", (Object) null, (Object) null).when(listIterator0).next();
        doReturn("-D", (Object) null).when(listIterator0).previous();
        groupImpl0.process(writeableCommandLineImpl0, listIterator0);
    }

    @Test(timeout = 4000)
    public void test1431() throws Throwable {
        LinkedList<PropertyOption> linkedList0 = new LinkedList<PropertyOption>();
        PropertyOption propertyOption0 = PropertyOption.INSTANCE;
        GroupImpl groupImpl0 = new GroupImpl(linkedList0, "Passes properties and values to the application", "Passes properties and values to the application", (-2343), (-2343));
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(propertyOption0, linkedList0);
        LinkedList<String> linkedList1 = new LinkedList<String>();
        linkedList1.add("-D");
        ListIterator<String> listIterator0 = linkedList1.listIterator();
        groupImpl0.process(writeableCommandLineImpl0, listIterator0);
        groupImpl0.getMaximum();
    }

    @Test(timeout = 4000)
    public void test1432() throws Throwable {
        LinkedList<PropertyOption> linkedList0 = new LinkedList<PropertyOption>();
        PropertyOption propertyOption0 = PropertyOption.INSTANCE;
        GroupImpl groupImpl0 = new GroupImpl(linkedList0, "Passes properties and values to the application", "Passes properties and values to the application", (-2343), (-2343));
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(propertyOption0, linkedList0);
        LinkedList<String> linkedList1 = new LinkedList<String>();
        linkedList1.add("-D");
        ListIterator<String> listIterator0 = linkedList1.listIterator();
        groupImpl0.process(writeableCommandLineImpl0, listIterator0);
        listIterator0.hasNext();
    }

    @Test(timeout = 4000)
    public void test1433() throws Throwable {
        LinkedList<PropertyOption> linkedList0 = new LinkedList<PropertyOption>();
        PropertyOption propertyOption0 = PropertyOption.INSTANCE;
        GroupImpl groupImpl0 = new GroupImpl(linkedList0, "Passes properties and values to the application", "Passes properties and values to the application", (-2343), (-2343));
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(propertyOption0, linkedList0);
        LinkedList<String> linkedList1 = new LinkedList<String>();
        linkedList1.add("-D");
        ListIterator<String> listIterator0 = linkedList1.listIterator();
        groupImpl0.process(writeableCommandLineImpl0, listIterator0);
        groupImpl0.getMinimum();
        assertEquals(0, groupImpl0.getMinimum());
    }

    @Test(timeout = 4000)
    public void test1534() throws Throwable {
        LinkedList<PropertyOption> linkedList0 = new LinkedList<PropertyOption>();
        GroupImpl groupImpl0 = new GroupImpl(linkedList0, (String) null, (String) null, 45, 45);
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(groupImpl0, linkedList0);
        groupImpl0.validate(writeableCommandLineImpl0);
    }

    @Test(timeout = 4000)
    public void test1635() throws Throwable {
        LinkedList<GroupImpl> linkedList0 = new LinkedList<GroupImpl>();
        GroupImpl groupImpl0 = new GroupImpl(linkedList0, "", "", 60, 60);
        linkedList0.add(groupImpl0);
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(groupImpl0, linkedList0);
        groupImpl0.validate(writeableCommandLineImpl0);
    }

    @Test(timeout = 4000)
    public void test1736() throws Throwable {
        LinkedList<PropertyOption> linkedList0 = new LinkedList<PropertyOption>();
        PropertyOption propertyOption0 = new PropertyOption();
        linkedList0.offerLast(propertyOption0);
        GroupImpl groupImpl0 = new GroupImpl(linkedList0, "eAP^/,L}F1laI:", "eAP^/,L}F1laI:", 0, 0);
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(groupImpl0, linkedList0);
        groupImpl0.validate(writeableCommandLineImpl0);
        groupImpl0.getMaximum();
        assertEquals(0, groupImpl0.getMaximum());
    }

    @Test(timeout = 4000)
    public void test1737() throws Throwable {
        LinkedList<PropertyOption> linkedList0 = new LinkedList<PropertyOption>();
        PropertyOption propertyOption0 = new PropertyOption();
        linkedList0.offerLast(propertyOption0);
        GroupImpl groupImpl0 = new GroupImpl(linkedList0, "eAP^/,L}F1laI:", "eAP^/,L}F1laI:", 0, 0);
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(groupImpl0, linkedList0);
        groupImpl0.validate(writeableCommandLineImpl0);
        groupImpl0.getMinimum();
        assertEquals(0, groupImpl0.getMinimum());
    }

    @Test(timeout = 4000)
    public void test1838() throws Throwable {
        LinkedList<GroupImpl> linkedList0 = new LinkedList<GroupImpl>();
        GroupImpl groupImpl0 = new GroupImpl(linkedList0, "3S-TqUp'", "3S-TqUp'", (-386), (-386));
        linkedList0.add(groupImpl0);
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(groupImpl0, linkedList0);
        writeableCommandLineImpl0.addOption(groupImpl0);
        GroupImpl groupImpl1 = new GroupImpl(linkedList0, "3S-TqUp'", "3S-TqUp'", 3, 3);
        groupImpl1.validate(writeableCommandLineImpl0);
    }

    @Test(timeout = 4000)
    public void test1939() throws Throwable {
        LinkedList<SourceDestArgument> linkedList0 = new LinkedList<SourceDestArgument>();
        NumberValidator numberValidator0 = NumberValidator.getNumberInstance();
        ArgumentImpl argumentImpl0 = new ArgumentImpl("so", "so", (-1), (-1), 'f', 'f', numberValidator0, "cdl/0m\"T4T}e|y)x0", linkedList0, (-1));
        SourceDestArgument sourceDestArgument0 = new SourceDestArgument(argumentImpl0, argumentImpl0);
        linkedList0.add(sourceDestArgument0);
        GroupImpl groupImpl0 = new GroupImpl(linkedList0, "@U", "|", (-1), (-1));
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(groupImpl0, linkedList0);
        groupImpl0.validate(writeableCommandLineImpl0);
    }

    @Test(timeout = 4000)
    public void test2040() throws Throwable {
        LinkedList<GroupImpl> linkedList0 = new LinkedList<GroupImpl>();
        GroupImpl groupImpl0 = new GroupImpl(linkedList0, (String) null, (String) null, 0, 0);
        LinkedHashSet<Object> linkedHashSet0 = new LinkedHashSet<Object>();
        Comparator<Command> comparator0 = (Comparator<Command>) mock(Comparator.class, new ViolatedAssumptionAnswer());
        groupImpl0.appendUsage((StringBuffer) null, (Set) linkedHashSet0, (Comparator) comparator0, (String) null);
        groupImpl0.getMaximum();
        assertEquals(0, groupImpl0.getMaximum());
    }

    @Test(timeout = 4000)
    public void test2041() throws Throwable {
        LinkedList<GroupImpl> linkedList0 = new LinkedList<GroupImpl>();
        GroupImpl groupImpl0 = new GroupImpl(linkedList0, (String) null, (String) null, 0, 0);
        LinkedHashSet<Object> linkedHashSet0 = new LinkedHashSet<Object>();
        Comparator<Command> comparator0 = (Comparator<Command>) mock(Comparator.class, new ViolatedAssumptionAnswer());
        groupImpl0.appendUsage((StringBuffer) null, (Set) linkedHashSet0, (Comparator) comparator0, (String) null);
        groupImpl0.getMinimum();
        assertEquals(0, groupImpl0.getMinimum());
    }

    @Test(timeout = 4000)
    public void test2142() throws Throwable {
        LinkedList<PropertyOption> linkedList0 = new LinkedList<PropertyOption>();
        PropertyOption propertyOption0 = PropertyOption.INSTANCE;
        linkedList0.offerLast(propertyOption0);
        linkedList0.add(propertyOption0);
        GroupImpl groupImpl0 = new GroupImpl(linkedList0, "Ix", "Ix", 0, 0);
        String string0 = groupImpl0.toString();
        groupImpl0.getMaximum();
        assertEquals(0, groupImpl0.getMaximum());
    }

    @Test(timeout = 4000)
    public void test2143() throws Throwable {
        LinkedList<PropertyOption> linkedList0 = new LinkedList<PropertyOption>();
        PropertyOption propertyOption0 = PropertyOption.INSTANCE;
        linkedList0.offerLast(propertyOption0);
        linkedList0.add(propertyOption0);
        GroupImpl groupImpl0 = new GroupImpl(linkedList0, "Ix", "Ix", 0, 0);
        String string0 = groupImpl0.toString();
        assertNotNull(string0);
    }

    @Test(timeout = 4000)
    public void test2244() throws Throwable {
        LinkedList<GroupImpl> linkedList0 = new LinkedList<GroupImpl>();
        GroupImpl groupImpl0 = new GroupImpl(linkedList0, "org.apache.commons.cli2.option.SourceDestArgument", "org.apache.commons.cli2.option.SourceDestArgument", 0, 0);
        LinkedHashSet<GroupImpl> linkedHashSet0 = new LinkedHashSet<GroupImpl>();
        List list0 = groupImpl0.helpLines(0, linkedHashSet0, (Comparator) null);
        groupImpl0.getMaximum();
        assertEquals(1, groupImpl0.getMaximum());
    }

    @Test(timeout = 4000)
    public void test2245() throws Throwable {
        LinkedList<GroupImpl> linkedList0 = new LinkedList<GroupImpl>();
        GroupImpl groupImpl0 = new GroupImpl(linkedList0, "org.apache.commons.cli2.option.SourceDestArgument", "org.apache.commons.cli2.option.SourceDestArgument", 0, 0);
        LinkedHashSet<GroupImpl> linkedHashSet0 = new LinkedHashSet<GroupImpl>();
        List list0 = groupImpl0.helpLines(0, linkedHashSet0, (Comparator) null);
        list0.size();
    }

    @Test(timeout = 4000)
    public void test2246() throws Throwable {
        LinkedList<GroupImpl> linkedList0 = new LinkedList<GroupImpl>();
        GroupImpl groupImpl0 = new GroupImpl(linkedList0, "org.apache.commons.cli2.option.SourceDestArgument", "org.apache.commons.cli2.option.SourceDestArgument", 0, 0);
        LinkedHashSet<GroupImpl> linkedHashSet0 = new LinkedHashSet<GroupImpl>();
        List list0 = groupImpl0.helpLines(0, linkedHashSet0, (Comparator) null);
        groupImpl0.getMinimum();
    }

    @Test(timeout = 4000)
    public void test2347() throws Throwable {
        LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
        DisplaySetting displaySetting0 = mock(DisplaySetting.class, new ViolatedAssumptionAnswer());
        GroupImpl groupImpl0 = new GroupImpl(linkedList0, "DIS9LAY_ARxUMENT_BRACKETED", "DIS9LAY_ARxUMENT_BRACKETED", 2865, 2865);
        List list0 = groupImpl0.helpLines(2865, displaySetting0.ALL, (Comparator) null);
        list0.size();
    }

    @Test(timeout = 4000)
    public void test2348() throws Throwable {
        LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
        DisplaySetting displaySetting0 = mock(DisplaySetting.class, new ViolatedAssumptionAnswer());
        GroupImpl groupImpl0 = new GroupImpl(linkedList0, "DIS9LAY_ARxUMENT_BRACKETED", "DIS9LAY_ARxUMENT_BRACKETED", 2865, 2865);
        List list0 = groupImpl0.helpLines(2865, displaySetting0.ALL, (Comparator) null);
        groupImpl0.getMaximum();
    }

    @Test(timeout = 4000)
    public void test2349() throws Throwable {
        LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
        DisplaySetting displaySetting0 = mock(DisplaySetting.class, new ViolatedAssumptionAnswer());
        GroupImpl groupImpl0 = new GroupImpl(linkedList0, "DIS9LAY_ARxUMENT_BRACKETED", "DIS9LAY_ARxUMENT_BRACKETED", 2865, 2865);
        List list0 = groupImpl0.helpLines(2865, displaySetting0.ALL, (Comparator) null);
        groupImpl0.getMinimum();
    }

    @Test(timeout = 4000)
    public void test2450() throws Throwable {
        LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
        DisplaySetting displaySetting0 = mock(DisplaySetting.class, new ViolatedAssumptionAnswer());
        Comparator<Command> comparator0 = (Comparator<Command>) mock(Comparator.class, new ViolatedAssumptionAnswer());
        GroupImpl groupImpl0 = new GroupImpl(linkedList0, "", "", 1325, 1325);
        List list0 = groupImpl0.helpLines(1325, displaySetting0.ALL, comparator0);
        groupImpl0.getMaximum();
    }

    @Test(timeout = 4000)
    public void test2451() throws Throwable {
        LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
        DisplaySetting displaySetting0 = mock(DisplaySetting.class, new ViolatedAssumptionAnswer());
        Comparator<Command> comparator0 = (Comparator<Command>) mock(Comparator.class, new ViolatedAssumptionAnswer());
        GroupImpl groupImpl0 = new GroupImpl(linkedList0, "", "", 1325, 1325);
        List list0 = groupImpl0.helpLines(1325, displaySetting0.ALL, comparator0);
        groupImpl0.getMinimum();
    }

    @Test(timeout = 4000)
    public void test2452() throws Throwable {
        LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
        DisplaySetting displaySetting0 = mock(DisplaySetting.class, new ViolatedAssumptionAnswer());
        Comparator<Command> comparator0 = (Comparator<Command>) mock(Comparator.class, new ViolatedAssumptionAnswer());
        GroupImpl groupImpl0 = new GroupImpl(linkedList0, "", "", 1325, 1325);
        List list0 = groupImpl0.helpLines(1325, displaySetting0.ALL, comparator0);
        list0.isEmpty();
    }

    @Test(timeout = 4000)
    public void test2553() throws Throwable {
        LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
        DisplaySetting displaySetting0 = mock(DisplaySetting.class, new ViolatedAssumptionAnswer());
        GroupImpl groupImpl0 = new GroupImpl(linkedList0, "\"p", "\"p", (-1929), (-1929));
        Integer integer0 = new Integer((-1929));
        linkedList0.add(integer0);
        Comparator<GroupImpl> comparator0 = (Comparator<GroupImpl>) mock(Comparator.class, new ViolatedAssumptionAnswer());
        groupImpl0.helpLines((-1929), displaySetting0.ALL, comparator0);
    }

    @Test(timeout = 4000)
    public void test2654() throws Throwable {
        LinkedList<PropertyOption> linkedList0 = new LinkedList<PropertyOption>();
        PropertyOption propertyOption0 = new PropertyOption("", "", 2676);
        linkedList0.add(propertyOption0);
        GroupImpl groupImpl0 = new GroupImpl(linkedList0, "-D", "-D", 2676, 2676);
        Option option0 = groupImpl0.findOption("");
        assertNotNull(option0);
    }

    @Test(timeout = 4000)
    public void test2655() throws Throwable {
        LinkedList<PropertyOption> linkedList0 = new LinkedList<PropertyOption>();
        PropertyOption propertyOption0 = new PropertyOption("", "", 2676);
        linkedList0.add(propertyOption0);
        GroupImpl groupImpl0 = new GroupImpl(linkedList0, "-D", "-D", 2676, 2676);
        Option option0 = groupImpl0.findOption("");
        groupImpl0.getMinimum();
        assertEquals(0, groupImpl0.getMinimum());
    }

    @Test(timeout = 4000)
    public void test2656() throws Throwable {
        LinkedList<PropertyOption> linkedList0 = new LinkedList<PropertyOption>();
        PropertyOption propertyOption0 = new PropertyOption("", "", 2676);
        linkedList0.add(propertyOption0);
        GroupImpl groupImpl0 = new GroupImpl(linkedList0, "-D", "-D", 2676, 2676);
        Option option0 = groupImpl0.findOption("");
        groupImpl0.getMaximum();
    }

    @Test(timeout = 4000)
    public void test2757() throws Throwable {
        LinkedList<GroupImpl> linkedList0 = new LinkedList<GroupImpl>();
        GroupImpl groupImpl0 = new GroupImpl(linkedList0, "6fH~_%MC", "6fH~_%MC", 3, 3);
        linkedList0.offer(groupImpl0);
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(groupImpl0, linkedList0);
        groupImpl0.defaults(writeableCommandLineImpl0);
    }

    @Test(timeout = 4000)
    public void test2858() throws Throwable {
        LinkedList<SourceDestArgument> linkedList0 = new LinkedList<SourceDestArgument>();
        FileValidator fileValidator0 = FileValidator.getExistingDirectoryInstance();
        ArgumentImpl argumentImpl0 = new ArgumentImpl("x:zZJ-", "x:zZJ-", 61, 61, '7', 'G', fileValidator0, "joE%RX$7", linkedList0, 61);
        LinkedList<DefaultOption> linkedList1 = new LinkedList<DefaultOption>();
        SourceDestArgument sourceDestArgument0 = new SourceDestArgument(argumentImpl0, argumentImpl0, 'j', 'j', "--", linkedList1);
        linkedList0.add(sourceDestArgument0);
        GroupImpl groupImpl0 = new GroupImpl(linkedList0, "\"1ez(U #n_t", "\"1ez(U #n_t", '\u0000', '\u0000');
        LinkedList<GroupImpl> linkedList2 = new LinkedList<GroupImpl>();
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(sourceDestArgument0, linkedList2);
        groupImpl0.defaults(writeableCommandLineImpl0);
        linkedList0.contains(sourceDestArgument0);
    }

    @Test(timeout = 4000)
    public void test2859() throws Throwable {
        LinkedList<SourceDestArgument> linkedList0 = new LinkedList<SourceDestArgument>();
        FileValidator fileValidator0 = FileValidator.getExistingDirectoryInstance();
        ArgumentImpl argumentImpl0 = new ArgumentImpl("x:zZJ-", "x:zZJ-", 61, 61, '7', 'G', fileValidator0, "joE%RX$7", linkedList0, 61);
        LinkedList<DefaultOption> linkedList1 = new LinkedList<DefaultOption>();
        SourceDestArgument sourceDestArgument0 = new SourceDestArgument(argumentImpl0, argumentImpl0, 'j', 'j', "--", linkedList1);
        linkedList0.add(sourceDestArgument0);
        GroupImpl groupImpl0 = new GroupImpl(linkedList0, "\"1ez(U #n_t", "\"1ez(U #n_t", '\u0000', '\u0000');
        LinkedList<GroupImpl> linkedList2 = new LinkedList<GroupImpl>();
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(sourceDestArgument0, linkedList2);
        groupImpl0.defaults(writeableCommandLineImpl0);
        groupImpl0.getMaximum();
    }
}
