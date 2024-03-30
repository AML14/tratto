/*
 * This file was automatically generated by EvoSuite
 * Thu Dec 14 23:10:28 GMT 2023
 */
package org.apache.commons.cli2.commandline;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.apache.commons.cli2.Option;
import org.apache.commons.cli2.commandline.WriteableCommandLineImpl;
import org.apache.commons.cli2.option.ArgumentImpl;
import org.apache.commons.cli2.option.Command;
import org.apache.commons.cli2.option.DefaultOption;
import org.apache.commons.cli2.option.PropertyOption;
import org.apache.commons.cli2.validation.ClassValidator;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class WriteableCommandLineImpl_ESTest extends WriteableCommandLineImpl_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        PropertyOption propertyOption0 = PropertyOption.INSTANCE;
        LinkedList<Command> linkedList0 = new LinkedList<Command>();
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(propertyOption0, linkedList0);
        String string0 = writeableCommandLineImpl0.getProperty("Passes properties and values to the application");
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        PropertyOption propertyOption0 = PropertyOption.INSTANCE;
        LinkedList<Command> linkedList0 = new LinkedList<Command>();
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(propertyOption0, linkedList0);
        Set set0 = writeableCommandLineImpl0.getOptionTriggers();
        set0.isEmpty();
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        PropertyOption propertyOption0 = PropertyOption.INSTANCE;
        LinkedList<Command> linkedList0 = new LinkedList<Command>();
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(propertyOption0, linkedList0);
        writeableCommandLineImpl0.addProperty("Passes properties and values to the application", "Passes properties and values to the application");
        Set set0 = writeableCommandLineImpl0.getProperties((Option) propertyOption0);
        set0.isEmpty();
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        PropertyOption propertyOption0 = new PropertyOption();
        LinkedList<Command> linkedList0 = new LinkedList<Command>();
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(propertyOption0, linkedList0);
        Set set0 = writeableCommandLineImpl0.getProperties();
        set0.isEmpty();
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        PropertyOption propertyOption0 = new PropertyOption();
        LinkedList<Command> linkedList0 = new LinkedList<Command>();
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(propertyOption0, linkedList0);
        List list0 = writeableCommandLineImpl0.getOptions();
        list0.size();
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        LinkedList<Object> linkedList0 = new LinkedList<Object>();
        PropertyOption propertyOption0 = new PropertyOption();
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(propertyOption0, linkedList0);
        List list0 = writeableCommandLineImpl0.getNormalised();
        list0.size();
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        PropertyOption propertyOption0 = PropertyOption.INSTANCE;
        LinkedList<Command> linkedList0 = new LinkedList<Command>();
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(propertyOption0, linkedList0);
        List list0 = writeableCommandLineImpl0.getValues("-D");
        list0.isEmpty();
    }

    @Test(timeout = 4000)
    public void test077() throws Throwable {
        PropertyOption propertyOption0 = PropertyOption.INSTANCE;
        LinkedList<Command> linkedList0 = new LinkedList<Command>();
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(propertyOption0, linkedList0);
        writeableCommandLineImpl0.addOption(propertyOption0);
        boolean boolean0 = writeableCommandLineImpl0.hasOption("-D");
    }

    @Test(timeout = 4000)
    public void test088() throws Throwable {
        PropertyOption propertyOption0 = PropertyOption.INSTANCE;
        LinkedList<Command> linkedList0 = new LinkedList<Command>();
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(propertyOption0, linkedList0);
        writeableCommandLineImpl0.addValue((Option) null, "-D");
        Long long0 = new Long((-1L));
        writeableCommandLineImpl0.addValue((Option) null, long0);
        List list0 = writeableCommandLineImpl0.getValues("-D");
        WriteableCommandLineImpl writeableCommandLineImpl1 = new WriteableCommandLineImpl(propertyOption0, list0);
        writeableCommandLineImpl1.toString();
    }

    @Test(timeout = 4000)
    public void test099() throws Throwable {
        PropertyOption propertyOption0 = PropertyOption.INSTANCE;
        LinkedList<Command> linkedList0 = new LinkedList<Command>();
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(propertyOption0, linkedList0);
        ClassValidator classValidator0 = new ClassValidator();
        ArgumentImpl argumentImpl0 = new ArgumentImpl("Passes properties and values to the application", "Passes properties and values to the application", (-2143), (-2143), ':', ':', classValidator0, "Passes properties and values to the application", linkedList0, (-2143));
        Boolean boolean0 = Boolean.FALSE;
        writeableCommandLineImpl0.addValue(argumentImpl0, boolean0);
        argumentImpl0.getMinimum();
    }

    @Test(timeout = 4000)
    public void test1010() throws Throwable {
        PropertyOption propertyOption0 = new PropertyOption();
        LinkedList<Command> linkedList0 = new LinkedList<Command>();
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(propertyOption0, linkedList0);
        writeableCommandLineImpl0.addSwitch(propertyOption0, false);
        Boolean boolean0 = writeableCommandLineImpl0.getSwitch((Option) propertyOption0);
        assertNotNull(boolean0);
    }

    @Test(timeout = 4000)
    public void test1011() throws Throwable {
        PropertyOption propertyOption0 = new PropertyOption();
        LinkedList<Command> linkedList0 = new LinkedList<Command>();
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(propertyOption0, linkedList0);
        writeableCommandLineImpl0.addSwitch(propertyOption0, false);
        Boolean boolean0 = writeableCommandLineImpl0.getSwitch((Option) propertyOption0);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test1112() throws Throwable {
        LinkedList<Object> linkedList0 = new LinkedList<Object>();
        PropertyOption propertyOption0 = new PropertyOption();
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(propertyOption0, linkedList0);
        writeableCommandLineImpl0.addSwitch(propertyOption0, false);
        writeableCommandLineImpl0.addSwitch(propertyOption0, false);
    }

    @Test(timeout = 4000)
    public void test1213() throws Throwable {
        LinkedList<Object> linkedList0 = new LinkedList<Object>();
        PropertyOption propertyOption0 = new PropertyOption();
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(propertyOption0, linkedList0);
        writeableCommandLineImpl0.addSwitch(propertyOption0, true);
        propertyOption0.getDescription();
        assertNotNull(propertyOption0.getDescription());
    }

    @Test(timeout = 4000)
    public void test1314() throws Throwable {
        PropertyOption propertyOption0 = PropertyOption.INSTANCE;
        LinkedList<Command> linkedList0 = new LinkedList<Command>();
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(propertyOption0, linkedList0);
        boolean boolean0 = writeableCommandLineImpl0.hasOption("-D");
    }

    @Test(timeout = 4000)
    public void test1415() throws Throwable {
        PropertyOption propertyOption0 = PropertyOption.INSTANCE;
        LinkedList<Command> linkedList0 = new LinkedList<Command>();
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(propertyOption0, linkedList0);
        writeableCommandLineImpl0.addValue((Option) null, "-D");
        List list0 = writeableCommandLineImpl0.getValues("-D");
        WriteableCommandLineImpl writeableCommandLineImpl1 = new WriteableCommandLineImpl(propertyOption0, list0);
        String string0 = writeableCommandLineImpl1.toString();
    }

    @Test(timeout = 4000)
    public void test1516() throws Throwable {
        PropertyOption propertyOption0 = new PropertyOption();
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(propertyOption0, (List) null);
        List list0 = writeableCommandLineImpl0.getValues((Option) propertyOption0, (List) null);
        list0.isEmpty();
    }

    @Test(timeout = 4000)
    public void test1617() throws Throwable {
        LinkedList<Object> linkedList0 = new LinkedList<Object>();
        PropertyOption propertyOption0 = new PropertyOption();
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(propertyOption0, linkedList0);
        writeableCommandLineImpl0.addValue(propertyOption0, linkedList0);
        List list0 = writeableCommandLineImpl0.getUndefaultedValues(propertyOption0);
        list0.size();
    }

    @Test(timeout = 4000)
    public void test1718() throws Throwable {
        LinkedList<Object> linkedList0 = new LinkedList<Object>();
        PropertyOption propertyOption0 = new PropertyOption();
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(propertyOption0, linkedList0);
        List list0 = writeableCommandLineImpl0.getUndefaultedValues(propertyOption0);
        list0.size();
    }

    @Test(timeout = 4000)
    public void test1819() throws Throwable {
        PropertyOption propertyOption0 = PropertyOption.INSTANCE;
        LinkedList<Command> linkedList0 = new LinkedList<Command>();
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(propertyOption0, linkedList0);
        Boolean boolean0 = writeableCommandLineImpl0.getSwitch("Passes properties and values to the application");
    }

    @Test(timeout = 4000)
    public void test1920() throws Throwable {
        PropertyOption propertyOption0 = new PropertyOption();
        LinkedList<Command> linkedList0 = new LinkedList<Command>();
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(propertyOption0, linkedList0);
        writeableCommandLineImpl0.addProperty((Option) propertyOption0, "Passes properties and values to the application", "-D");
        writeableCommandLineImpl0.addProperty((Option) propertyOption0, "-D", "-D");
        propertyOption0.getId();
    }

    @Test(timeout = 4000)
    public void test2021() throws Throwable {
        PropertyOption propertyOption0 = new PropertyOption();
        LinkedList<Command> linkedList0 = new LinkedList<Command>();
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(propertyOption0, linkedList0);
        writeableCommandLineImpl0.addProperty((Option) propertyOption0, "Passes properties and values to the application", "-D");
        String string0 = writeableCommandLineImpl0.getProperty((Option) propertyOption0, "Passes properties and values to the application", "-D");
    }

    @Test(timeout = 4000)
    public void test2122() throws Throwable {
        PropertyOption propertyOption0 = PropertyOption.INSTANCE;
        LinkedList<Command> linkedList0 = new LinkedList<Command>();
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(propertyOption0, linkedList0);
        boolean boolean0 = writeableCommandLineImpl0.looksLikeOption("Passes properties and values to the application");
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test2223() throws Throwable {
        PropertyOption propertyOption0 = PropertyOption.INSTANCE;
        LinkedList<DefaultOption> linkedList0 = new LinkedList<DefaultOption>();
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(propertyOption0, linkedList0);
        boolean boolean0 = writeableCommandLineImpl0.looksLikeOption("-D");
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test2324() throws Throwable {
        PropertyOption propertyOption0 = new PropertyOption("RV7H", "RV7H", 192);
        LinkedList<Command> linkedList0 = new LinkedList<Command>();
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(propertyOption0, linkedList0);
        writeableCommandLineImpl0.addValue((Option) null, "Passes properties and values to the application");
        List list0 = writeableCommandLineImpl0.getValues("Passes properties and values to the application");
        WriteableCommandLineImpl writeableCommandLineImpl1 = new WriteableCommandLineImpl(propertyOption0, list0);
        String string0 = writeableCommandLineImpl1.toString();
    }

    @Test(timeout = 4000)
    public void test2425() throws Throwable {
        LinkedList<Object> linkedList0 = new LinkedList<Object>();
        PropertyOption propertyOption0 = new PropertyOption();
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(propertyOption0, linkedList0);
        writeableCommandLineImpl0.setDefaultValues(propertyOption0, linkedList0);
        propertyOption0.getDescription();
        assertNotNull(propertyOption0.getDescription());
    }

    @Test(timeout = 4000)
    public void test2526() throws Throwable {
        PropertyOption propertyOption0 = new PropertyOption();
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(propertyOption0, (List) null);
        writeableCommandLineImpl0.setDefaultValues(propertyOption0, (List) null);
        propertyOption0.getDescription();
        assertNotNull(propertyOption0.getDescription());
    }

    @Test(timeout = 4000)
    public void test2627() throws Throwable {
        PropertyOption propertyOption0 = PropertyOption.INSTANCE;
        LinkedList<Command> linkedList0 = new LinkedList<Command>();
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(propertyOption0, linkedList0);
        Boolean boolean0 = Boolean.TRUE;
        writeableCommandLineImpl0.setDefaultSwitch(propertyOption0, boolean0);
        propertyOption0.getDescription();
        assertNotNull(propertyOption0.getDescription());
    }

    @Test(timeout = 4000)
    public void test2728() throws Throwable {
        PropertyOption propertyOption0 = new PropertyOption();
        LinkedList<Command> linkedList0 = new LinkedList<Command>();
        WriteableCommandLineImpl writeableCommandLineImpl0 = new WriteableCommandLineImpl(propertyOption0, linkedList0);
        writeableCommandLineImpl0.setDefaultSwitch(propertyOption0, (Boolean) null);
        propertyOption0.getPreferredName();
        assertNotNull(propertyOption0.getPreferredName());
    }
}
