/*
 * This file was automatically generated by EvoSuite
 * Wed Sep 20 22:47:25 GMT 2023
 */
package tutorial;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.EmptyStackException;
import org.junit.runner.RunWith;
import tutorial.Stack;

public class StackTest {

    @Test(timeout = 4000)
    public void test00() throws Throwable {
        Stack<String> stack0 = new Stack<String>();
        stack0.isEmpty();
    }

    @Test(timeout = 4000)
    public void test01() throws Throwable {
        Stack<String> stack0 = new Stack<String>();
        stack0.push("x$P bOeg1/`gL<|");
        stack0.push("fI3?H~2wGr~\"[9VZ");
        String string0 = stack0.pop();
    }

    @Test(timeout = 4000)
    public void test12() throws Throwable {
        Stack<String> stack0 = new Stack<String>();
        stack0.push("i.%");
        stack0.pop();
        boolean boolean0 = stack0.isEmpty();
    }

    @Test(timeout = 4000)
    public void test23() throws Throwable {
        Stack<Object> stack0 = new Stack<Object>();
        stack0.push((Object) null);
    }

    @Test(timeout = 4000)
    public void test34() throws Throwable {
        Stack<String> stack0 = new Stack<String>();
        stack0.push("x$P bOeg1/`gL<|");
        stack0.push("fI3?H~2wGr~\"[9VZ");
        stack0.push("x$P bOeg1/`gL<|");
        stack0.push("x$P bOeg1/`gL<|");
        stack0.push("");
        stack0.push("x$P bOeg1/`gL<|");
        stack0.push("");
        stack0.push("");
        stack0.push("");
        stack0.push("");
        stack0.push("");
    }
}
