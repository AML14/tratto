/*
 * This file was automatically generated by EvoSuite
 * Sun Oct 15 18:33:47 GMT 2023
 */
package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.fasterxml.jackson.core.JsonPointer;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class JsonPointer_ESTest extends JsonPointer_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseQuotedTail("UxRS~L`&zjDqX/", 0);
        jsonPointer0.toString();
    }

    @Test(timeout = 4000)
    public void test001() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseQuotedTail("UxRS~L`&zjDqX/", 0);
        jsonPointer0.getMatchingProperty();
    }

    @Test(timeout = 4000)
    public void test002() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseQuotedTail("UxRS~L`&zjDqX/", 0);
        jsonPointer0.getMatchingIndex();
    }

    @Test(timeout = 4000)
    public void test013() throws Throwable {
        JsonPointer._parseQuotedTail("", 2);
    }

    @Test(timeout = 4000)
    public void test024() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseTail("RC!b/9VHCI");
        jsonPointer0.getMatchingIndex();
    }

    @Test(timeout = 4000)
    public void test025() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseTail("RC!b/9VHCI");
        jsonPointer0.toString();
    }

    @Test(timeout = 4000)
    public void test026() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseTail("RC!b/9VHCI");
        jsonPointer0.getMatchingProperty();
    }

    @Test(timeout = 4000)
    public void test027() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseTail("RC!b/9VHCI");
        jsonPointer0.matches();
    }

    @Test(timeout = 4000)
    public void test038() throws Throwable {
        JsonPointer jsonPointer0 = new JsonPointer();
        JsonPointer jsonPointer1 = JsonPointer._parseTail("~vmEE%uY5`b{F");
        boolean boolean0 = jsonPointer1.equals(jsonPointer0);
        jsonPointer1.getMatchingProperty();
    }

    @Test(timeout = 4000)
    public void test039() throws Throwable {
        JsonPointer jsonPointer0 = new JsonPointer();
        JsonPointer jsonPointer1 = JsonPointer._parseTail("~vmEE%uY5`b{F");
        boolean boolean0 = jsonPointer1.equals(jsonPointer0);
        jsonPointer0.equals((Object) jsonPointer1);
    }

    @Test(timeout = 4000)
    public void test0310() throws Throwable {
        JsonPointer jsonPointer0 = new JsonPointer();
        JsonPointer jsonPointer1 = JsonPointer._parseTail("~vmEE%uY5`b{F");
        boolean boolean0 = jsonPointer1.equals(jsonPointer0);
        jsonPointer0.getMatchingIndex();
    }

    @Test(timeout = 4000)
    public void test0311() throws Throwable {
        JsonPointer jsonPointer0 = new JsonPointer();
        JsonPointer jsonPointer1 = JsonPointer._parseTail("~vmEE%uY5`b{F");
        boolean boolean0 = jsonPointer1.equals(jsonPointer0);
        jsonPointer1.getMatchingIndex();
    }

    @Test(timeout = 4000)
    public void test0312() throws Throwable {
        JsonPointer jsonPointer0 = new JsonPointer();
        JsonPointer jsonPointer1 = JsonPointer._parseTail("~vmEE%uY5`b{F");
        boolean boolean0 = jsonPointer1.equals(jsonPointer0);
        jsonPointer1.toString();
    }

    @Test(timeout = 4000)
    public void test0313() throws Throwable {
        JsonPointer jsonPointer0 = new JsonPointer();
        JsonPointer jsonPointer1 = JsonPointer._parseTail("~vmEE%uY5`b{F");
        boolean boolean0 = jsonPointer1.equals(jsonPointer0);
        jsonPointer1.matches();
    }

    @Test(timeout = 4000)
    public void test0314() throws Throwable {
        JsonPointer jsonPointer0 = new JsonPointer();
        JsonPointer jsonPointer1 = JsonPointer._parseTail("~vmEE%uY5`b{F");
        boolean boolean0 = jsonPointer1.equals(jsonPointer0);
    }

    @Test(timeout = 4000)
    public void test0415() throws Throwable {
        JsonPointer jsonPointer0 = new JsonPointer();
        jsonPointer0.matchElement(0);
        jsonPointer0.getMatchingIndex();
    }

    @Test(timeout = 4000)
    public void test0516() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseTail("a578");
        boolean boolean0 = jsonPointer0.mayMatchElement();
        jsonPointer0.matches();
    }

    @Test(timeout = 4000)
    public void test0517() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseTail("a578");
        boolean boolean0 = jsonPointer0.mayMatchElement();
    }

    @Test(timeout = 4000)
    public void test0518() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseTail("a578");
        boolean boolean0 = jsonPointer0.mayMatchElement();
        jsonPointer0.toString();
    }

    @Test(timeout = 4000)
    public void test0619() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseQuotedTail("WMx}CI%oE,}E", 0);
        boolean boolean0 = jsonPointer0.mayMatchProperty();
        jsonPointer0.toString();
    }

    @Test(timeout = 4000)
    public void test0620() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseQuotedTail("WMx}CI%oE,}E", 0);
        boolean boolean0 = jsonPointer0.mayMatchProperty();
        jsonPointer0.matches();
    }

    @Test(timeout = 4000)
    public void test0621() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseQuotedTail("WMx}CI%oE,}E", 0);
        boolean boolean0 = jsonPointer0.mayMatchProperty();
        jsonPointer0.getMatchingIndex();
    }

    @Test(timeout = 4000)
    public void test0622() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseQuotedTail("WMx}CI%oE,}E", 0);
        boolean boolean0 = jsonPointer0.mayMatchProperty();
    }

    @Test(timeout = 4000)
    public void test0623() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseQuotedTail("WMx}CI%oE,}E", 0);
        boolean boolean0 = jsonPointer0.mayMatchProperty();
        jsonPointer0.getMatchingProperty();
    }

    @Test(timeout = 4000)
    public void test0724() throws Throwable {
        JsonPointer.valueOf(")");
    }

    @Test(timeout = 4000)
    public void test0825() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer.EMPTY;
        JsonPointer jsonPointer1 = new JsonPointer((String) null, "%3.rfo^0AJV", jsonPointer0);
        String string0 = jsonPointer1.toString();
        jsonPointer1.matches();
    }

    @Test(timeout = 4000)
    public void test0826() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer.EMPTY;
        JsonPointer jsonPointer1 = new JsonPointer((String) null, "%3.rfo^0AJV", jsonPointer0);
        String string0 = jsonPointer1.toString();
    }

    @Test(timeout = 4000)
    public void test0827() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer.EMPTY;
        JsonPointer jsonPointer1 = new JsonPointer((String) null, "%3.rfo^0AJV", jsonPointer0);
        String string0 = jsonPointer1.toString();
        jsonPointer1.getMatchingIndex();
    }

    @Test(timeout = 4000)
    public void test0828() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer.EMPTY;
        JsonPointer jsonPointer1 = new JsonPointer((String) null, "%3.rfo^0AJV", jsonPointer0);
        String string0 = jsonPointer1.toString();
        jsonPointer1.mayMatchProperty();
    }

    @Test(timeout = 4000)
    public void test0929() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseQuotedTail("$mL", 0);
        String string0 = jsonPointer0.toString();
        jsonPointer0.getMatchingProperty();
    }

    @Test(timeout = 4000)
    public void test0930() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseQuotedTail("$mL", 0);
        String string0 = jsonPointer0.toString();
        jsonPointer0.matches();
    }

    @Test(timeout = 4000)
    public void test0931() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseQuotedTail("$mL", 0);
        String string0 = jsonPointer0.toString();
    }

    @Test(timeout = 4000)
    public void test0932() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseQuotedTail("$mL", 0);
        String string0 = jsonPointer0.toString();
    }

    @Test(timeout = 4000)
    public void test0933() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseQuotedTail("$mL", 0);
        String string0 = jsonPointer0.toString();
        jsonPointer0.getMatchingIndex();
    }

    @Test(timeout = 4000)
    public void test1034() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseQuotedTail("'", 0);
        JsonPointer jsonPointer1 = new JsonPointer("'", "'", jsonPointer0);
        JsonPointer jsonPointer2 = jsonPointer1.tail();
        jsonPointer0.toString();
    }

    @Test(timeout = 4000)
    public void test1035() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseQuotedTail("'", 0);
        JsonPointer jsonPointer1 = new JsonPointer("'", "'", jsonPointer0);
        JsonPointer jsonPointer2 = jsonPointer1.tail();
    }

    @Test(timeout = 4000)
    public void test1036() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseQuotedTail("'", 0);
        JsonPointer jsonPointer1 = new JsonPointer("'", "'", jsonPointer0);
        JsonPointer jsonPointer2 = jsonPointer1.tail();
        jsonPointer2.getMatchingIndex();
    }

    @Test(timeout = 4000)
    public void test1037() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseQuotedTail("'", 0);
        JsonPointer jsonPointer1 = new JsonPointer("'", "'", jsonPointer0);
        JsonPointer jsonPointer2 = jsonPointer1.tail();
        jsonPointer2.getMatchingProperty();
    }

    @Test(timeout = 4000)
    public void test1138() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseQuotedTail("'", 0);
        JsonPointer jsonPointer1 = jsonPointer0.tail();
    }

    @Test(timeout = 4000)
    public void test1139() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseQuotedTail("'", 0);
        JsonPointer jsonPointer1 = jsonPointer0.tail();
        jsonPointer0.toString();
    }

    @Test(timeout = 4000)
    public void test1140() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseQuotedTail("'", 0);
        JsonPointer jsonPointer1 = jsonPointer0.tail();
        jsonPointer1.toString();
    }

    @Test(timeout = 4000)
    public void test1141() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseQuotedTail("'", 0);
        JsonPointer jsonPointer1 = jsonPointer0.tail();
        jsonPointer0.getMatchingIndex();
    }

    @Test(timeout = 4000)
    public void test1142() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseQuotedTail("'", 0);
        JsonPointer jsonPointer1 = jsonPointer0.tail();
        jsonPointer0.getMatchingProperty();
    }

    @Test(timeout = 4000)
    public void test1243() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer.EMPTY;
        JsonPointer jsonPointer1 = new JsonPointer("x=}k[A8G+cO|;1V$", "R=?8y!fJygomi]u0", jsonPointer0);
        JsonPointer jsonPointer2 = jsonPointer1.matchProperty("R=?8y!fJygomi]u0");
    }

    @Test(timeout = 4000)
    public void test1244() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer.EMPTY;
        JsonPointer jsonPointer1 = new JsonPointer("x=}k[A8G+cO|;1V$", "R=?8y!fJygomi]u0", jsonPointer0);
        JsonPointer jsonPointer2 = jsonPointer1.matchProperty("R=?8y!fJygomi]u0");
        jsonPointer1.getMatchingIndex();
    }

    @Test(timeout = 4000)
    public void test1245() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer.EMPTY;
        JsonPointer jsonPointer1 = new JsonPointer("x=}k[A8G+cO|;1V$", "R=?8y!fJygomi]u0", jsonPointer0);
        JsonPointer jsonPointer2 = jsonPointer1.matchProperty("R=?8y!fJygomi]u0");
        jsonPointer2.getMatchingProperty();
    }

    @Test(timeout = 4000)
    public void test1246() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer.EMPTY;
        JsonPointer jsonPointer1 = new JsonPointer("x=}k[A8G+cO|;1V$", "R=?8y!fJygomi]u0", jsonPointer0);
        JsonPointer jsonPointer2 = jsonPointer1.matchProperty("R=?8y!fJygomi]u0");
        jsonPointer1.toString();
    }

    @Test(timeout = 4000)
    public void test1347() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer.compile((String) null);
        JsonPointer jsonPointer1 = new JsonPointer((String) null, "~)kx^$<MFG", jsonPointer0);
        String string0 = jsonPointer1.getMatchingProperty();
        jsonPointer1.getMatchingIndex();
    }

    @Test(timeout = 4000)
    public void test1348() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer.compile((String) null);
        JsonPointer jsonPointer1 = new JsonPointer((String) null, "~)kx^$<MFG", jsonPointer0);
        String string0 = jsonPointer1.getMatchingProperty();
    }

    @Test(timeout = 4000)
    public void test1349() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer.compile((String) null);
        JsonPointer jsonPointer1 = new JsonPointer((String) null, "~)kx^$<MFG", jsonPointer0);
        String string0 = jsonPointer1.getMatchingProperty();
        jsonPointer1.matches();
    }

    @Test(timeout = 4000)
    public void test1450() throws Throwable {
        JsonPointer jsonPointer0 = new JsonPointer();
        JsonPointer jsonPointer1 = new JsonPointer("0", "0", jsonPointer0);
        int int0 = jsonPointer1.getMatchingIndex();
    }

    @Test(timeout = 4000)
    public void test1451() throws Throwable {
        JsonPointer jsonPointer0 = new JsonPointer();
        JsonPointer jsonPointer1 = new JsonPointer("0", "0", jsonPointer0);
        int int0 = jsonPointer1.getMatchingIndex();
        jsonPointer0.getMatchingIndex();
    }

    @Test(timeout = 4000)
    public void test1452() throws Throwable {
        JsonPointer jsonPointer0 = new JsonPointer();
        JsonPointer jsonPointer1 = new JsonPointer("0", "0", jsonPointer0);
        int int0 = jsonPointer1.getMatchingIndex();
        jsonPointer1.matches();
    }

    @Test(timeout = 4000)
    public void test1553() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseTail("a578");
        int int0 = jsonPointer0.getMatchingIndex();
        jsonPointer0.toString();
    }

    @Test(timeout = 4000)
    public void test1554() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseTail("a578");
        int int0 = jsonPointer0.getMatchingIndex();
        jsonPointer0.getMatchingProperty();
    }

    @Test(timeout = 4000)
    public void test1555() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseTail("a578");
        int int0 = jsonPointer0.getMatchingIndex();
        jsonPointer0.matches();
    }

    @Test(timeout = 4000)
    public void test1556() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseTail("a578");
        int int0 = jsonPointer0.getMatchingIndex();
    }

    @Test(timeout = 4000)
    public void test1657() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseTail("w0");
        jsonPointer0.matches();
    }

    @Test(timeout = 4000)
    public void test1658() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseTail("w0");
        jsonPointer0.getMatchingProperty();
    }

    @Test(timeout = 4000)
    public void test1659() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseTail("w0");
        jsonPointer0.mayMatchElement();
    }

    @Test(timeout = 4000)
    public void test1660() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseTail("w0");
        jsonPointer0.toString();
    }

    @Test(timeout = 4000)
    public void test1761() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer.compile((String) null);
        JsonPointer jsonPointer1 = new JsonPointer((String) null, "~)kx^$<MFG", jsonPointer0);
        jsonPointer1.hashCode();
    }

    @Test(timeout = 4000)
    public void test1862() throws Throwable {
        JsonPointer._parseTail("");
    }

    @Test(timeout = 4000)
    public void test1963() throws Throwable {
        JsonPointer._parseTail((String) null);
    }

    @Test(timeout = 4000)
    public void test2064() throws Throwable {
        JsonPointer._parseQuotedTail((String) null, 578);
    }

    @Test(timeout = 4000)
    public void test2165() throws Throwable {
        JsonPointer._parseQuotedTail("c/aUf", 2023);
    }

    @Test(timeout = 4000)
    public void test2266() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer.EMPTY;
        JsonPointer jsonPointer1 = null;
        jsonPointer1 = new JsonPointer((String) null, (String) null, jsonPointer0);
    }

    @Test(timeout = 4000)
    public void test2367() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseTail("v");
        jsonPointer0.matches();
    }

    @Test(timeout = 4000)
    public void test2368() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseTail("v");
        jsonPointer0.getMatchingProperty();
    }

    @Test(timeout = 4000)
    public void test2369() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseTail("v");
        jsonPointer0.toString();
    }

    @Test(timeout = 4000)
    public void test2370() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseTail("v");
        jsonPointer0.getMatchingIndex();
    }

    @Test(timeout = 4000)
    public void test2471() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer.compile("/D_&QiCiab]`~8");
        jsonPointer0.getMatchingProperty();
    }

    @Test(timeout = 4000)
    public void test2472() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer.compile("/D_&QiCiab]`~8");
        jsonPointer0.toString();
    }

    @Test(timeout = 4000)
    public void test2473() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer.compile("/D_&QiCiab]`~8");
        jsonPointer0.matches();
    }

    @Test(timeout = 4000)
    public void test2474() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer.compile("/D_&QiCiab]`~8");
        jsonPointer0.getMatchingIndex();
    }

    @Test(timeout = 4000)
    public void test2575() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer.compile("");
        jsonPointer0.getMatchingProperty();
    }

    @Test(timeout = 4000)
    public void test2676() throws Throwable {
        JsonPointer.compile("com.fasterxml.jackson.core.io.NumberInput");
    }

    @Test(timeout = 4000)
    public void test2777() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseQuotedTail("0", 0);
        jsonPointer0.toString();
    }

    @Test(timeout = 4000)
    public void test2778() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseQuotedTail("0", 0);
        jsonPointer0.matches();
    }

    @Test(timeout = 4000)
    public void test2779() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseQuotedTail("0", 0);
        jsonPointer0.getMatchingIndex();
    }

    @Test(timeout = 4000)
    public void test2780() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseQuotedTail("0", 0);
        jsonPointer0.getMatchingProperty();
    }

    @Test(timeout = 4000)
    public void test2881() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseQuotedTail("/D_A&QiCab]`~", 0);
        jsonPointer0.getMatchingIndex();
    }

    @Test(timeout = 4000)
    public void test2882() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseQuotedTail("/D_A&QiCab]`~", 0);
        jsonPointer0.getMatchingProperty();
    }

    @Test(timeout = 4000)
    public void test2883() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseQuotedTail("/D_A&QiCab]`~", 0);
        jsonPointer0.matches();
    }

    @Test(timeout = 4000)
    public void test2984() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseQuotedTail("u1uNgb?~*uLkwN$", 1);
        jsonPointer0.getMatchingIndex();
    }

    @Test(timeout = 4000)
    public void test2985() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseQuotedTail("u1uNgb?~*uLkwN$", 1);
        jsonPointer0.toString();
    }

    @Test(timeout = 4000)
    public void test2986() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseQuotedTail("u1uNgb?~*uLkwN$", 1);
        jsonPointer0.getMatchingProperty();
    }

    @Test(timeout = 4000)
    public void test3087() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseTail("~)kx^$<MFG");
        jsonPointer0.toString();
    }

    @Test(timeout = 4000)
    public void test3088() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseTail("~)kx^$<MFG");
        jsonPointer0.getMatchingProperty();
    }

    @Test(timeout = 4000)
    public void test3089() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseTail("~)kx^$<MFG");
        jsonPointer0.matches();
    }

    @Test(timeout = 4000)
    public void test3090() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseTail("~)kx^$<MFG");
        jsonPointer0.getMatchingIndex();
    }

    @Test(timeout = 4000)
    public void test3191() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseTail("5s'YsR4");
        boolean boolean0 = jsonPointer0.equals("5s'YsR4");
        jsonPointer0.getMatchingIndex();
    }

    @Test(timeout = 4000)
    public void test3192() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseTail("5s'YsR4");
        boolean boolean0 = jsonPointer0.equals("5s'YsR4");
        jsonPointer0.getMatchingProperty();
    }

    @Test(timeout = 4000)
    public void test3193() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseTail("5s'YsR4");
        boolean boolean0 = jsonPointer0.equals("5s'YsR4");
        jsonPointer0.matches();
    }

    @Test(timeout = 4000)
    public void test3194() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseTail("5s'YsR4");
        boolean boolean0 = jsonPointer0.equals("5s'YsR4");
    }

    @Test(timeout = 4000)
    public void test3195() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseTail("5s'YsR4");
        boolean boolean0 = jsonPointer0.equals("5s'YsR4");
        jsonPointer0.toString();
    }

    @Test(timeout = 4000)
    public void test3296() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer.EMPTY;
        JsonPointer jsonPointer1 = new JsonPointer();
        boolean boolean0 = jsonPointer0.equals(jsonPointer1);
        jsonPointer1.getMatchingIndex();
    }

    @Test(timeout = 4000)
    public void test3297() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer.EMPTY;
        JsonPointer jsonPointer1 = new JsonPointer();
        boolean boolean0 = jsonPointer0.equals(jsonPointer1);
    }

    @Test(timeout = 4000)
    public void test3398() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer.compile((String) null);
        boolean boolean0 = jsonPointer0.equals(jsonPointer0);
    }

    @Test(timeout = 4000)
    public void test3499() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer.compile((String) null);
        boolean boolean0 = jsonPointer0.equals((Object) null);
    }

    @Test(timeout = 4000)
    public void test35100() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer.EMPTY;
        JsonPointer jsonPointer1 = jsonPointer0.matchElement((-1));
    }

    @Test(timeout = 4000)
    public void test36101() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseTail("a578");
        JsonPointer jsonPointer1 = jsonPointer0.matchElement(578);
    }

    @Test(timeout = 4000)
    public void test36102() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseTail("a578");
        JsonPointer jsonPointer1 = jsonPointer0.matchElement(578);
        jsonPointer0.getMatchingProperty();
    }

    @Test(timeout = 4000)
    public void test36103() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseTail("a578");
        JsonPointer jsonPointer1 = jsonPointer0.matchElement(578);
        jsonPointer1.getMatchingProperty();
    }

    @Test(timeout = 4000)
    public void test37104() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer.EMPTY;
        JsonPointer jsonPointer1 = jsonPointer0.matchElement((-1378));
    }

    @Test(timeout = 4000)
    public void test38105() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseTail("x=}k[A8G+cO|;1V$");
        JsonPointer jsonPointer1 = new JsonPointer("x=}k[A8G+cO|;1V$", "x=}k[A8G+cO|;1V$", jsonPointer0);
        JsonPointer jsonPointer2 = jsonPointer1.matchProperty("x=}k[A8G+cO|;1V$");
    }

    @Test(timeout = 4000)
    public void test38106() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseTail("x=}k[A8G+cO|;1V$");
        JsonPointer jsonPointer1 = new JsonPointer("x=}k[A8G+cO|;1V$", "x=}k[A8G+cO|;1V$", jsonPointer0);
        JsonPointer jsonPointer2 = jsonPointer1.matchProperty("x=}k[A8G+cO|;1V$");
        jsonPointer2.getMatchingIndex();
    }

    @Test(timeout = 4000)
    public void test38107() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseTail("x=}k[A8G+cO|;1V$");
        JsonPointer jsonPointer1 = new JsonPointer("x=}k[A8G+cO|;1V$", "x=}k[A8G+cO|;1V$", jsonPointer0);
        JsonPointer jsonPointer2 = jsonPointer1.matchProperty("x=}k[A8G+cO|;1V$");
        jsonPointer2.getMatchingProperty();
    }

    @Test(timeout = 4000)
    public void test38108() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseTail("x=}k[A8G+cO|;1V$");
        JsonPointer jsonPointer1 = new JsonPointer("x=}k[A8G+cO|;1V$", "x=}k[A8G+cO|;1V$", jsonPointer0);
        JsonPointer jsonPointer2 = jsonPointer1.matchProperty("x=}k[A8G+cO|;1V$");
        jsonPointer2.toString();
    }

    @Test(timeout = 4000)
    public void test39109() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseTail("x=}k[A8G+cO|;1V$");
        jsonPointer0.mayMatchProperty();
    }

    @Test(timeout = 4000)
    public void test39110() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseTail("x=}k[A8G+cO|;1V$");
        JsonPointer jsonPointer1 = jsonPointer0.matchProperty("x=}k[A8G+cO|;1V$");
    }

    @Test(timeout = 4000)
    public void test39111() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseTail("x=}k[A8G+cO|;1V$");
        JsonPointer jsonPointer1 = jsonPointer0.matchProperty("x=}k[A8G+cO|;1V$");
        jsonPointer0.getMatchingProperty();
    }

    @Test(timeout = 4000)
    public void test39112() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseTail("x=}k[A8G+cO|;1V$");
        JsonPointer jsonPointer1 = jsonPointer0.matchProperty("x=}k[A8G+cO|;1V$");
        jsonPointer0.toString();
    }

    @Test(timeout = 4000)
    public void test39113() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseTail("x=}k[A8G+cO|;1V$");
        JsonPointer jsonPointer1 = jsonPointer0.matchProperty("x=}k[A8G+cO|;1V$");
        jsonPointer0.matches();
    }

    @Test(timeout = 4000)
    public void test39114() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer._parseTail("x=}k[A8G+cO|;1V$");
        JsonPointer jsonPointer1 = jsonPointer0.matchProperty("x=}k[A8G+cO|;1V$");
        jsonPointer0.getMatchingIndex();
    }

    @Test(timeout = 4000)
    public void test40115() throws Throwable {
        JsonPointer jsonPointer0 = new JsonPointer();
        jsonPointer0.matchProperty("");
        jsonPointer0.getMatchingIndex();
    }

    @Test(timeout = 4000)
    public void test41116() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer.EMPTY;
        JsonPointer jsonPointer1 = new JsonPointer("0", "0", jsonPointer0);
        boolean boolean0 = jsonPointer1.mayMatchElement();
        jsonPointer1.matches();
    }

    @Test(timeout = 4000)
    public void test41117() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer.EMPTY;
        JsonPointer jsonPointer1 = new JsonPointer("0", "0", jsonPointer0);
        boolean boolean0 = jsonPointer1.mayMatchElement();
        jsonPointer0.mayMatchElement();
    }

    @Test(timeout = 4000)
    public void test41118() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer.EMPTY;
        JsonPointer jsonPointer1 = new JsonPointer("0", "0", jsonPointer0);
        boolean boolean0 = jsonPointer1.mayMatchElement();
    }

    @Test(timeout = 4000)
    public void test41119() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer.EMPTY;
        JsonPointer jsonPointer1 = new JsonPointer("0", "0", jsonPointer0);
        boolean boolean0 = jsonPointer1.mayMatchElement();
        jsonPointer1.mayMatchProperty();
    }

    @Test(timeout = 4000)
    public void test42120() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer.compile((String) null);
        boolean boolean0 = jsonPointer0.matches();
    }

    @Test(timeout = 4000)
    public void test43121() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer.valueOf("/D_A&QiCab]`~");
        jsonPointer0.matches();
    }

    @Test(timeout = 4000)
    public void test43122() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer.valueOf("/D_A&QiCab]`~");
        jsonPointer0.getMatchingIndex();
    }

    @Test(timeout = 4000)
    public void test43123() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer.valueOf("/D_A&QiCab]`~");
        jsonPointer0.mayMatchProperty();
    }

    @Test(timeout = 4000)
    public void test43124() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer.valueOf("/D_A&QiCab]`~");
        jsonPointer0.getMatchingProperty();
    }

    @Test(timeout = 4000)
    public void test43125() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer.valueOf("/D_A&QiCab]`~");
        jsonPointer0.toString();
    }

    @Test(timeout = 4000)
    public void test44126() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer.compile((String) null);
        int int0 = jsonPointer0.getMatchingIndex();
    }

    @Test(timeout = 4000)
    public void test45127() throws Throwable {
        JsonPointer jsonPointer0 = new JsonPointer();
        boolean boolean0 = jsonPointer0.mayMatchElement();
        jsonPointer0.getMatchingIndex();
    }

    @Test(timeout = 4000)
    public void test45128() throws Throwable {
        JsonPointer jsonPointer0 = new JsonPointer();
        boolean boolean0 = jsonPointer0.mayMatchElement();
    }

    @Test(timeout = 4000)
    public void test46129() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer.valueOf("");
        jsonPointer0.mayMatchProperty();
    }

    @Test(timeout = 4000)
    public void test47130() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer.compile((String) null);
        String string0 = jsonPointer0.toString();
    }

    @Test(timeout = 4000)
    public void test48131() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer.compile((String) null);
        JsonPointer jsonPointer1 = new JsonPointer((String) null, "~)kx^$<MFG", jsonPointer0);
        boolean boolean0 = jsonPointer1.matches();
        jsonPointer1.getMatchingIndex();
    }

    @Test(timeout = 4000)
    public void test48132() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer.compile((String) null);
        JsonPointer jsonPointer1 = new JsonPointer((String) null, "~)kx^$<MFG", jsonPointer0);
        boolean boolean0 = jsonPointer1.matches();
        jsonPointer1.mayMatchProperty();
    }

    @Test(timeout = 4000)
    public void test48133() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer.compile((String) null);
        JsonPointer jsonPointer1 = new JsonPointer((String) null, "~)kx^$<MFG", jsonPointer0);
        boolean boolean0 = jsonPointer1.matches();
        jsonPointer0.matches();
    }

    @Test(timeout = 4000)
    public void test48134() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer.compile((String) null);
        JsonPointer jsonPointer1 = new JsonPointer((String) null, "~)kx^$<MFG", jsonPointer0);
        boolean boolean0 = jsonPointer1.matches();
    }

    @Test(timeout = 4000)
    public void test49135() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer.compile((String) null);
        JsonPointer jsonPointer1 = jsonPointer0.tail();
    }

    @Test(timeout = 4000)
    public void test50136() throws Throwable {
        JsonPointer jsonPointer0 = JsonPointer.compile((String) null);
        String string0 = jsonPointer0.getMatchingProperty();
    }
}
