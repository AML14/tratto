/*
 * This file was automatically generated by EvoSuite
 * Wed Sep 20 22:47:25 GMT 2023
 */

package tutorial;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.util.EmptyStackException;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;
import tutorial.Stack;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class Stack_ESTest extends Stack_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test0()  throws Throwable  {
        Stack<String> stack0 = new Stack<String>();
    }

    @Test(timeout = 4000)
    public void test1()  throws Throwable  {
        Stack<String> stack0 = new Stack<String>();
        stack0.push("i.%");
        stack0.pop();
        boolean boolean0 = stack0.isEmpty();
    }

    @Test(timeout = 4000)
    public void test2()  throws Throwable  {
        Stack<Object> stack0 = new Stack<Object>();
        stack0.push((Object) null);
    }
}
