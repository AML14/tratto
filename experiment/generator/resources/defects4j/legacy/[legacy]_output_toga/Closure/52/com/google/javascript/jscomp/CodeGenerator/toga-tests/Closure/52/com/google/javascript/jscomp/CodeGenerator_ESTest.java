/*
 * This file was automatically generated by EvoSuite
 * Sat Nov 04 02:36:46 GMT 2023
 */
package com.google.javascript.jscomp;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.google.javascript.jscomp.CodeConsumer;
import com.google.javascript.jscomp.CodeGenerator;
import com.google.javascript.rhino.Node;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class CodeGenerator_ESTest extends CodeGenerator_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        CodeGenerator codeGenerator0 = new CodeGenerator((CodeConsumer) null);
        codeGenerator0.addJsString("'7Xr]>,L_w6E3OVi");
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        String string0 = CodeGenerator.regexpEscape("#AlDd]`]]>0,*C");
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        String string0 = CodeGenerator.escapeToDoubleQuotedJsString("kWNS7_IJdJf]>+r$");
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        Node node0 = Node.newNumber(2447.0253);
        CodeGenerator codeGenerator0 = new CodeGenerator((CodeConsumer) null);
        codeGenerator0.addList(node0);
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        CodeGenerator codeGenerator0 = new CodeGenerator((CodeConsumer) null);
        codeGenerator0.tagAsStrict();
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        CodeGenerator codeGenerator0 = new CodeGenerator((CodeConsumer) null);
        codeGenerator0.addCaseBody((Node) null);
    }

    @Test(timeout = 4000)
    public void test096() throws Throwable {
        double double0 = CodeGenerator.getSimpleNumber("0");
    }

    @Test(timeout = 4000)
    public void test107() throws Throwable {
        boolean boolean0 = CodeGenerator.isSimpleNumber("/RLj<!--");
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test118() throws Throwable {
        boolean boolean0 = CodeGenerator.isSimpleNumber("9lNGx+3>BTlv4B");
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test129() throws Throwable {
        boolean boolean0 = CodeGenerator.isSimpleNumber("");
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test1310() throws Throwable {
        double double0 = CodeGenerator.getSimpleNumber("typeof");
    }

    @Test(timeout = 4000)
    public void test1411() throws Throwable {
        CodeGenerator codeGenerator0 = new CodeGenerator((CodeConsumer) null);
        Node node0 = new Node(85);
        codeGenerator0.addArrayList(node0);
    }

    @Test(timeout = 4000)
    public void test1512() throws Throwable {
        Node node0 = Node.newString("a1f</scriptueg");
        CodeGenerator.Context codeGenerator_Context0 = CodeGenerator.Context.IN_FOR_INIT_CLAUSE;
        CodeGenerator codeGenerator0 = new CodeGenerator((CodeConsumer) null);
        codeGenerator0.addList(node0, false, codeGenerator_Context0);
    }

    @Test(timeout = 4000)
    public void test1613() throws Throwable {
        CodeGenerator codeGenerator0 = new CodeGenerator((CodeConsumer) null);
        Node node0 = Node.newString(51, ".82?($G*r;|qOG");
        CodeGenerator.Context codeGenerator_Context0 = CodeGenerator.Context.IN_FOR_INIT_CLAUSE;
        codeGenerator0.addList(node0, true, codeGenerator_Context0);
    }

    @Test(timeout = 4000)
    public void test1914() throws Throwable {
        Node node0 = Node.newString("\"U<!--`7\"");
        CodeGenerator codeGenerator0 = new CodeGenerator((CodeConsumer) null);
        codeGenerator0.addAllSiblings(node0);
    }

    @Test(timeout = 4000)
    public void test2015() throws Throwable {
        CodeGenerator codeGenerator0 = new CodeGenerator((CodeConsumer) null);
        codeGenerator0.addJsString("O=^Q\"tn>(");
    }

    @Test(timeout = 4000)
    public void test2116() throws Throwable {
        String string0 = CodeGenerator.regexpEscape("\t\n\u000B\f\r \u0085\u1680\u2028\u2029\u205F\u3000\u00A0\u180E\u202F");
    }

    @Test(timeout = 4000)
    public void test2217() throws Throwable {
        String string0 = CodeGenerator.regexpEscape("!--><!-");
    }

    @Test(timeout = 4000)
    public void test2318() throws Throwable {
        String string0 = CodeGenerator.regexpEscape("q>I:1es(#F'>js");
    }

    @Test(timeout = 4000)
    public void test2419() throws Throwable {
        String string0 = CodeGenerator.regexpEscape("}Rb\"-6?->:ld&_");
    }

    @Test(timeout = 4000)
    public void test2520() throws Throwable {
        String string0 = CodeGenerator.regexpEscape("\"u</script\"");
    }

    @Test(timeout = 4000)
    public void test2621() throws Throwable {
        String string0 = CodeGenerator.escapeToDoubleQuotedJsString("<!--W tgyS");
        assertNotNull(string0);
    }

    @Test(timeout = 4000)
    public void test2722() throws Throwable {
        Charset charset0 = Charset.forName("default");
        CharsetEncoder charsetEncoder0 = charset0.newEncoder();
        String string0 = CodeGenerator.regexpEscape("\t\n\u000B} \u0085<!-~0\u00A0\u202F", charsetEncoder0);
    }

    @Test(timeout = 4000)
    public void test2823() throws Throwable {
        String string0 = CodeGenerator.identifierEscape("\t\n\u000B\f\r \u0085\u1680\u2028\u2029\u205F\u3000\u00A0\u180E\u202F");
    }

    @Test(timeout = 4000)
    public void test2924() throws Throwable {
        String string0 = CodeGenerator.identifierEscape("typeof");
    }
}
