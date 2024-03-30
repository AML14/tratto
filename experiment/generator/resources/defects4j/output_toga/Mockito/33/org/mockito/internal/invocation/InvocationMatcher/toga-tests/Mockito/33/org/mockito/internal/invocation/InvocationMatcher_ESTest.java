/*
 * This file was automatically generated by EvoSuite
 * Sun Nov 05 05:28:00 GMT 2023
 */
package org.mockito.internal.invocation;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.hamcrest.Matcher;
import org.hamcrest.text.StringContainsInOrder;
import org.junit.runner.RunWith;
import org.mockito.internal.debugging.Location;
import org.mockito.internal.invocation.Invocation;
import org.mockito.internal.invocation.InvocationMatcher;
import org.mockito.internal.invocation.MockitoMethod;
import org.mockito.internal.invocation.realmethod.RealMethod;
import org.mockito.internal.reporting.PrintSettings;
import org.mockito.internal.stubbing.StubbedInvocationMatcher;
import org.mockito.stubbing.Answer;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class InvocationMatcher_ESTest extends InvocationMatcher_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        Invocation invocation0 = mock(Invocation.class, new ViolatedAssumptionAnswer());
        doReturn((List) null).when(invocation0).argumentsToMatchers();
        doReturn((Method) null).when(invocation0).getMethod();
        InvocationMatcher invocationMatcher0 = new InvocationMatcher(invocation0);
        invocationMatcher0.hasSimilarMethod(invocation0);
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        Integer integer0 = new Integer(19);
        Invocation invocation0 = mock(Invocation.class, new ViolatedAssumptionAnswer());
        doReturn((List) null).when(invocation0).argumentsToMatchers();
        doReturn((Method) null, (Method) null).when(invocation0).getMethod();
        doReturn(integer0, integer0).when(invocation0).getMock();
        InvocationMatcher invocationMatcher0 = new InvocationMatcher(invocation0);
        invocationMatcher0.matches(invocation0);
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        Invocation invocation0 = mock(Invocation.class, new ViolatedAssumptionAnswer());
        doReturn((List) null).when(invocation0).argumentsToMatchers();
        doReturn((String) null).when(invocation0).toString(anyList(), any(org.mockito.internal.reporting.PrintSettings.class));
        InvocationMatcher invocationMatcher0 = new InvocationMatcher(invocation0);
        PrintSettings printSettings0 = new PrintSettings();
        String string0 = invocationMatcher0.toString(printSettings0);
        assertNotNull(string0);
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        Invocation invocation0 = mock(Invocation.class, new ViolatedAssumptionAnswer());
        doReturn((List) null, (List) null).when(invocation0).argumentsToMatchers();
        doReturn((String) null).when(invocation0).toString();
        InvocationMatcher invocationMatcher0 = new InvocationMatcher(invocation0);
        StubbedInvocationMatcher stubbedInvocationMatcher0 = null;
        stubbedInvocationMatcher0 = new StubbedInvocationMatcher(invocationMatcher0, (Answer) null);
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        Invocation invocation0 = mock(Invocation.class, new ViolatedAssumptionAnswer());
        doReturn((List) null).when(invocation0).argumentsToMatchers();
        doReturn((String) null).when(invocation0).toString(anyList(), any(org.mockito.internal.reporting.PrintSettings.class));
        InvocationMatcher invocationMatcher0 = new InvocationMatcher(invocation0);
        String string0 = invocationMatcher0.toString();
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        Invocation invocation0 = mock(Invocation.class, new ViolatedAssumptionAnswer());
        doReturn((List) null).when(invocation0).argumentsToMatchers();
        doReturn((Location) null).when(invocation0).getLocation();
        InvocationMatcher invocationMatcher0 = new InvocationMatcher(invocation0);
        Location location0 = invocationMatcher0.getLocation();
        assertNotNull(location0);
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        MockitoMethod mockitoMethod0 = mock(MockitoMethod.class, new ViolatedAssumptionAnswer());
        doReturn(true).when(mockitoMethod0).isVarArgs();
        Object[] objectArray0 = new Object[7];
        Invocation invocation0 = new Invocation((Object) null, mockitoMethod0, objectArray0, 4078, (RealMethod) null);
        List<Matcher> list0 = (List<Matcher>) invocation0.argumentsToMatchers();
        InvocationMatcher invocationMatcher0 = new InvocationMatcher(invocation0, list0);
    }

    @Test(timeout = 4000)
    public void test077() throws Throwable {
        Integer integer0 = new Integer(61);
        Invocation invocation0 = mock(Invocation.class, new ViolatedAssumptionAnswer());
        doReturn(integer0).when(invocation0).getMock();
        Charset charset0 = Charset.defaultCharset();
        Set<String> set0 = charset0.aliases();
        StringContainsInOrder stringContainsInOrder0 = new StringContainsInOrder(set0);
        Invocation invocation1 = mock(Invocation.class, new ViolatedAssumptionAnswer());
        doReturn((List) null).when(invocation1).argumentsToMatchers();
        doReturn(stringContainsInOrder0).when(invocation1).getMock();
        InvocationMatcher invocationMatcher0 = new InvocationMatcher(invocation1);
        boolean boolean0 = invocationMatcher0.matches(invocation0);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test088() throws Throwable {
        MockitoMethod mockitoMethod0 = mock(MockitoMethod.class, new ViolatedAssumptionAnswer());
        doReturn(true).when(mockitoMethod0).isVarArgs();
        Object[] objectArray0 = new Object[7];
        Invocation invocation0 = new Invocation(mockitoMethod0, mockitoMethod0, objectArray0, 4070, (RealMethod) null);
        InvocationMatcher invocationMatcher0 = new InvocationMatcher(invocation0);
        invocationMatcher0.captureArgumentsFrom(invocation0);
        invocation0.isVerified();
        assertTrue(invocation0.isVerified());
    }

    @Test(timeout = 4000)
    public void test099() throws Throwable {
        Vector<Invocation> vector0 = new Vector<Invocation>();
        List<InvocationMatcher> list0 = InvocationMatcher.createFrom(vector0);
        list0.isEmpty();
    }

    @Test(timeout = 4000)
    public void test1010() throws Throwable {
        Vector<Invocation> vector0 = new Vector<Invocation>();
        vector0.add((Invocation) null);
        InvocationMatcher.createFrom(vector0);
    }
}
