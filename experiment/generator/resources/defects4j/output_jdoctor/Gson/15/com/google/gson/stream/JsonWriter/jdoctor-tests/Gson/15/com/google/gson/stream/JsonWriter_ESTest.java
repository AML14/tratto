/*
 * This file was automatically generated by EvoSuite
 * Wed Oct 11 16:41:12 GMT 2023
 */
package com.google.gson.stream;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class JsonWriter_ESTest extends JsonWriter_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        StringWriter stringWriter0 = new StringWriter();
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        JsonWriter jsonWriter1 = jsonWriter0.beginArray();
        assertTrue(("Xvw&Uz};,bZFt!42" == null) == false);
        com.google.gson.stream.JsonWriter jsonWriter2;
        jsonWriter2 = jsonWriter1.name("Xvw&Uz};,bZFt!42");
        jsonWriter2.beginArray();
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        StringWriter stringWriter0 = new StringWriter();
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        jsonWriter0.beginArray();
        jsonWriter0.endObject();
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        StringWriter stringWriter0 = new StringWriter();
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        jsonWriter0.value("pUiZ2e5Aj< ");
        stringWriter0.toString();
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        StringWriter stringWriter0 = new StringWriter();
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        jsonWriter0.setLenient(true);
        jsonWriter0.value(true);
        stringWriter0.toString();
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        StringWriter stringWriter0 = new StringWriter(0);
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        jsonWriter0.setHtmlSafe(true);
        jsonWriter0.value(true);
        jsonWriter0.isHtmlSafe();
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        StringWriter stringWriter0 = new StringWriter();
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        jsonWriter0.setSerializeNulls(false);
        jsonWriter0.value(true);
        stringWriter0.toString();
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        StringWriter stringWriter0 = new StringWriter();
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        jsonWriter0.setSerializeNulls(false);
        jsonWriter0.value("=1A5/g7.E^");
        stringWriter0.toString();
    }

    @Test(timeout = 4000)
    public void test077() throws Throwable {
        StringWriter stringWriter0 = new StringWriter();
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        Double double0 = new Double((-2415.298894));
        jsonWriter0.setHtmlSafe(true);
        jsonWriter0.value((Number) double0);
        jsonWriter0.isHtmlSafe();
    }

    @Test(timeout = 4000)
    public void test088() throws Throwable {
        StringWriter stringWriter0 = new StringWriter();
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        Double double0 = new Double((-2415.298894));
        jsonWriter0.setSerializeNulls(false);
        JsonWriter jsonWriter1 = jsonWriter0.value((Number) double0);
        jsonWriter1.isHtmlSafe();
    }

    @Test(timeout = 4000)
    public void test099() throws Throwable {
        StringWriter stringWriter0 = new StringWriter();
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        jsonWriter0.setLenient(true);
        jsonWriter0.value((Boolean) null);
        stringWriter0.toString();
    }

    @Test(timeout = 4000)
    public void test1010() throws Throwable {
        StringWriter stringWriter0 = new StringWriter();
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        Boolean boolean0 = Boolean.FALSE;
        jsonWriter0.setHtmlSafe(true);
        jsonWriter0.value(boolean0);
        jsonWriter0.isHtmlSafe();
    }

    @Test(timeout = 4000)
    public void test1111() throws Throwable {
        StringWriter stringWriter0 = new StringWriter();
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        Boolean boolean0 = Boolean.FALSE;
        jsonWriter0.setSerializeNulls(false);
        jsonWriter0.value(boolean0);
        stringWriter0.toString();
    }

    @Test(timeout = 4000)
    public void test1212() throws Throwable {
        StringWriter stringWriter0 = new StringWriter(0);
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        jsonWriter0.setLenient(true);
        JsonWriter jsonWriter1 = jsonWriter0.value((long) 0);
        jsonWriter1.getSerializeNulls();
    }

    @Test(timeout = 4000)
    public void test1313() throws Throwable {
        StringWriter stringWriter0 = new StringWriter(0);
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        jsonWriter0.setHtmlSafe(true);
        jsonWriter0.value((long) 'p');
        jsonWriter0.isHtmlSafe();
    }

    @Test(timeout = 4000)
    public void test1414() throws Throwable {
        StringWriter stringWriter0 = new StringWriter(1348);
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        jsonWriter0.setSerializeNulls(false);
        jsonWriter0.value((-1766L));
        stringWriter0.toString();
    }

    @Test(timeout = 4000)
    public void test1515() throws Throwable {
        StringWriter stringWriter0 = new StringWriter(0);
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        jsonWriter0.setLenient(true);
        jsonWriter0.setHtmlSafe(true);
        jsonWriter0.beginObject();
        jsonWriter0.endObject();
        jsonWriter0.value(0.0);
        jsonWriter0.isHtmlSafe();
    }

    @Test(timeout = 4000)
    public void test1616() throws Throwable {
        StringWriter stringWriter0 = new StringWriter(1348);
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        jsonWriter0.setSerializeNulls(false);
        jsonWriter0.value(0.0);
        stringWriter0.toString();
    }

    @Test(timeout = 4000)
    public void test1717() throws Throwable {
        StringWriter stringWriter0 = new StringWriter(0);
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        jsonWriter0.setLenient(true);
        jsonWriter0.nullValue();
        stringWriter0.toString();
    }

    @Test(timeout = 4000)
    public void test1718() throws Throwable {
        StringWriter stringWriter0 = new StringWriter(0);
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        jsonWriter0.setLenient(true);
        jsonWriter0.nullValue();
        jsonWriter0.getSerializeNulls();
    }

    @Test(timeout = 4000)
    public void test1819() throws Throwable {
        StringWriter stringWriter0 = new StringWriter();
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        jsonWriter0.setHtmlSafe(true);
        jsonWriter0.nullValue();
        stringWriter0.toString();
    }

    @Test(timeout = 4000)
    public void test1820() throws Throwable {
        StringWriter stringWriter0 = new StringWriter();
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        jsonWriter0.setHtmlSafe(true);
        jsonWriter0.nullValue();
        jsonWriter0.isHtmlSafe();
    }

    @Test(timeout = 4000)
    public void test1921() throws Throwable {
        StringWriter stringWriter0 = new StringWriter(0);
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        jsonWriter0.setLenient(true);
        assertTrue(("&" == null) == false);
        com.google.gson.stream.JsonWriter jsonWriter1;
        jsonWriter1 = jsonWriter0.name("&");
        jsonWriter1.getSerializeNulls();
    }

    @Test(timeout = 4000)
    public void test2022() throws Throwable {
        StringWriter stringWriter0 = new StringWriter(0);
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        jsonWriter0.setHtmlSafe(true);
        assertTrue(("&" == null) == false);
        com.google.gson.stream.JsonWriter default0;
        default0 = jsonWriter0.name("&");
        jsonWriter0.isHtmlSafe();
    }

    @Test(timeout = 4000)
    public void test2123() throws Throwable {
        StringWriter stringWriter0 = new StringWriter(0);
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        jsonWriter0.setSerializeNulls(false);
        assertTrue(("" == null) == false);
        com.google.gson.stream.JsonWriter jsonWriter1;
        jsonWriter1 = jsonWriter0.name("");
        jsonWriter1.getSerializeNulls();
    }

    @Test(timeout = 4000)
    public void test2224() throws Throwable {
        StringWriter stringWriter0 = new StringWriter();
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        jsonWriter0.setLenient(true);
        jsonWriter0.jsonValue("=1A5/g7.E^");
        stringWriter0.toString();
    }

    @Test(timeout = 4000)
    public void test2325() throws Throwable {
        StringWriter stringWriter0 = new StringWriter();
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        jsonWriter0.setHtmlSafe(true);
        jsonWriter0.jsonValue("=1A5/g7.E^");
        stringWriter0.toString();
    }

    @Test(timeout = 4000)
    public void test2326() throws Throwable {
        StringWriter stringWriter0 = new StringWriter();
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        jsonWriter0.setHtmlSafe(true);
        jsonWriter0.jsonValue("=1A5/g7.E^");
        jsonWriter0.isHtmlSafe();
    }

    @Test(timeout = 4000)
    public void test2427() throws Throwable {
        StringWriter stringWriter0 = new StringWriter();
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        jsonWriter0.setSerializeNulls(false);
        jsonWriter0.jsonValue("=1A5/g7.E^");
        stringWriter0.toString();
    }

    @Test(timeout = 4000)
    public void test2528() throws Throwable {
        StringWriter stringWriter0 = new StringWriter(0);
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        jsonWriter0.setLenient(true);
        jsonWriter0.isLenient();
        jsonWriter0.getSerializeNulls();
    }

    @Test(timeout = 4000)
    public void test2629() throws Throwable {
        StringWriter stringWriter0 = new StringWriter();
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        jsonWriter0.setHtmlSafe(true);
        boolean boolean0 = jsonWriter0.isHtmlSafe();
    }

    @Test(timeout = 4000)
    public void test2730() throws Throwable {
        StringWriter stringWriter0 = new StringWriter(1348);
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        jsonWriter0.setSerializeNulls(false);
        boolean boolean0 = jsonWriter0.getSerializeNulls();
    }

    @Test(timeout = 4000)
    public void test2831() throws Throwable {
        StringWriter stringWriter0 = new StringWriter(0);
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        jsonWriter0.setLenient(true);
        jsonWriter0.beginObject();
        jsonWriter0.endObject();
        stringWriter0.toString();
    }

    @Test(timeout = 4000)
    public void test2932() throws Throwable {
        StringWriter stringWriter0 = new StringWriter(0);
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        JsonWriter jsonWriter1 = jsonWriter0.beginObject();
        jsonWriter1.setHtmlSafe(true);
        jsonWriter0.endObject();
        jsonWriter0.isHtmlSafe();
    }

    @Test(timeout = 4000)
    public void test3033() throws Throwable {
        StringWriter stringWriter0 = new StringWriter(0);
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        jsonWriter0.setLenient(true);
        JsonWriter jsonWriter1 = jsonWriter0.beginArray();
        jsonWriter1.endArray();
        stringWriter0.toString();
    }

    @Test(timeout = 4000)
    public void test3134() throws Throwable {
        StringWriter stringWriter0 = new StringWriter();
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        JsonWriter jsonWriter1 = jsonWriter0.beginObject();
        assertTrue(("" == null) == false);
        com.google.gson.stream.JsonWriter jsonWriter2;
        jsonWriter2 = jsonWriter1.name("");
        jsonWriter2.setHtmlSafe(true);
        jsonWriter0.beginArray();
        jsonWriter2.endArray();
        stringWriter0.toString();
    }

    @Test(timeout = 4000)
    public void test3235() throws Throwable {
        StringWriter stringWriter0 = new StringWriter(0);
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        jsonWriter0.setSerializeNulls(false);
        JsonWriter jsonWriter1 = jsonWriter0.beginArray();
        jsonWriter1.beginObject();
        JsonWriter jsonWriter2 = jsonWriter1.endObject();
        jsonWriter2.endArray();
        stringWriter0.toString();
    }

    @Test(timeout = 4000)
    public void test3336() throws Throwable {
        StringWriter stringWriter0 = new StringWriter();
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        jsonWriter0.beginObject();
        jsonWriter0.value(true);
    }

    @Test(timeout = 4000)
    public void test3437() throws Throwable {
        StringWriter stringWriter0 = new StringWriter(1);
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        JsonWriter jsonWriter1 = jsonWriter0.value(false);
        Boolean boolean0 = Boolean.valueOf(false);
        jsonWriter1.value(boolean0);
    }

    @Test(timeout = 4000)
    public void test3538() throws Throwable {
        StringWriter stringWriter0 = new StringWriter();
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        Byte byte0 = new Byte((byte) (-27));
        JsonWriter jsonWriter1 = jsonWriter0.value((Number) byte0);
        jsonWriter1.value((-1L));
    }

    @Test(timeout = 4000)
    public void test3639() throws Throwable {
        StringWriter stringWriter0 = new StringWriter();
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        jsonWriter0.beginArray();
        JsonWriter jsonWriter1 = jsonWriter0.endArray();
        jsonWriter1.value((-1.0));
    }

    @Test(timeout = 4000)
    public void test3740() throws Throwable {
        StringWriter stringWriter0 = new StringWriter(1348);
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        jsonWriter0.setIndent((String) null);
    }

    @Test(timeout = 4000)
    public void test3841() throws Throwable {
        StringWriter stringWriter0 = new StringWriter(0);
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        JsonWriter jsonWriter1 = jsonWriter0.value((-1.0));
        jsonWriter1.setLenient(true);
        jsonWriter1.value("");
        stringWriter0.toString();
    }

    @Test(timeout = 4000)
    public void test3942() throws Throwable {
        StringWriter stringWriter0 = new StringWriter(0);
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        JsonWriter jsonWriter1 = jsonWriter0.beginObject();
        assertTrue(("" == null) == false);
        com.google.gson.stream.JsonWriter default1;
        default1 = jsonWriter0.name("");
        Short short0 = new Short((short) 10);
        jsonWriter1.value((Number) short0);
        Float float0 = new Float((float) 0);
        jsonWriter1.value((Number) float0);
    }

    @Test(timeout = 4000)
    public void test4043() throws Throwable {
        StringWriter stringWriter0 = new StringWriter(1348);
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        jsonWriter0.beginObject();
        jsonWriter0.jsonValue("=1A5/g7.E^");
    }

    @Test(timeout = 4000)
    public void test4144() throws Throwable {
        StringWriter stringWriter0 = new StringWriter(1348);
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        JsonWriter jsonWriter1 = jsonWriter0.beginArray();
        jsonWriter1.jsonValue(":V");
        stringWriter0.toString();
    }

    @Test(timeout = 4000)
    public void test4145() throws Throwable {
        StringWriter stringWriter0 = new StringWriter(1348);
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        JsonWriter jsonWriter1 = jsonWriter0.beginArray();
        jsonWriter1.jsonValue(":V");
        Double double0 = new Double((-2415.298894));
        jsonWriter1.value((Number) double0);
        jsonWriter0.getSerializeNulls();
    }

    @Test(timeout = 4000)
    public void test4246() throws Throwable {
        StringWriter stringWriter0 = new StringWriter();
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        JsonWriter jsonWriter1 = jsonWriter0.beginObject();
        assertTrue(("b" == null) == false);
        com.google.gson.stream.JsonWriter jsonWriter2;
        jsonWriter2 = jsonWriter1.name("b");
        JsonWriter jsonWriter3 = jsonWriter2.value(true);
        assertTrue((">V{+[ex" == null) == false);
        com.google.gson.stream.JsonWriter default2;
        default2 = jsonWriter3.name(">V{+[ex");
        jsonWriter1.value("b");
        stringWriter0.toString();
    }

    @Test(timeout = 4000)
    public void test4347() throws Throwable {
        StringWriter stringWriter0 = new StringWriter(0);
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        JsonWriter jsonWriter1 = jsonWriter0.beginArray();
        jsonWriter0.setIndent("X,8]k-K?ut{o0C6o)");
        jsonWriter1.value(2441L);
        stringWriter0.toString();
    }

    @Test(timeout = 4000)
    public void test4448() throws Throwable {
        StringWriter stringWriter0 = new StringWriter(0);
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        jsonWriter0.close();
    }

    @Test(timeout = 4000)
    public void test4549() throws Throwable {
        StringWriter stringWriter0 = new StringWriter(0);
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        jsonWriter0.beginArray();
        jsonWriter0.close();
    }

    @Test(timeout = 4000)
    public void test4650() throws Throwable {
        StringWriter stringWriter0 = new StringWriter(38);
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        JsonWriter jsonWriter1 = jsonWriter0.value(0L);
        jsonWriter1.close();
        jsonWriter0.flush();
    }

    @Test(timeout = 4000)
    public void test4751() throws Throwable {
        StringWriter stringWriter0 = new StringWriter(1348);
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        jsonWriter0.flush();
        jsonWriter0.getSerializeNulls();
    }

    @Test(timeout = 4000)
    public void test4852() throws Throwable {
        StringWriter stringWriter0 = new StringWriter();
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        Double double0 = new Double((-2415.298894));
        jsonWriter0.setLenient(true);
        jsonWriter0.value((Number) double0);
        jsonWriter0.isLenient();
    }

    @Test(timeout = 4000)
    public void test4953() throws Throwable {
        StringWriter stringWriter0 = new StringWriter();
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        JsonWriter jsonWriter1 = jsonWriter0.value((Number) null);
        jsonWriter1.beginObject();
    }

    @Test(timeout = 4000)
    public void test5054() throws Throwable {
        StringWriter stringWriter0 = new StringWriter(0);
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        JsonWriter jsonWriter1 = jsonWriter0.value((-1.0));
        jsonWriter1.value("");
    }

    @Test(timeout = 4000)
    public void test5155() throws Throwable {
        StringWriter stringWriter0 = new StringWriter();
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        Boolean boolean0 = Boolean.TRUE;
        jsonWriter0.value(boolean0);
        stringWriter0.toString();
    }

    @Test(timeout = 4000)
    public void test5256() throws Throwable {
        StringWriter stringWriter0 = new StringWriter(0);
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        assertTrue(("w" == null) == false);
        com.google.gson.stream.JsonWriter jsonWriter1;
        jsonWriter1 = jsonWriter0.name("w");
        jsonWriter1.getSerializeNulls();
    }

    @Test(timeout = 4000)
    public void test5257() throws Throwable {
        StringWriter stringWriter0 = new StringWriter(0);
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        assertTrue(("w" == null) == false);
        com.google.gson.stream.JsonWriter jsonWriter1;
        jsonWriter1 = jsonWriter0.name("w");
        jsonWriter1.setSerializeNulls(false);
        jsonWriter0.getSerializeNulls();
    }

    @Test(timeout = 4000)
    public void test5258() throws Throwable {
        StringWriter stringWriter0 = new StringWriter(0);
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        assertTrue(("w" == null) == false);
        com.google.gson.stream.JsonWriter jsonWriter1;
        jsonWriter1 = jsonWriter0.name("w");
        jsonWriter1.setSerializeNulls(false);
        jsonWriter0.nullValue();
        stringWriter0.toString();
    }

    @Test(timeout = 4000)
    public void test5359() throws Throwable {
        StringWriter stringWriter0 = new StringWriter(0);
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        assertTrue(("w" == null) == false);
        com.google.gson.stream.JsonWriter default3;
        default3 = jsonWriter0.name("w");
        jsonWriter0.nullValue();
    }

    @Test(timeout = 4000)
    public void test5460() throws Throwable {
        StringWriter stringWriter0 = new StringWriter(1);
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        jsonWriter0.jsonValue((String) null);
        stringWriter0.toString();
    }

    @Test(timeout = 4000)
    public void test5561() throws Throwable {
        StringWriter stringWriter0 = new StringWriter(1);
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        jsonWriter0.value((String) null);
        stringWriter0.toString();
    }

    @Test(timeout = 4000)
    public void test5562() throws Throwable {
        StringWriter stringWriter0 = new StringWriter(1);
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        jsonWriter0.value((String) null);
        jsonWriter0.getSerializeNulls();
    }

    @Test(timeout = 4000)
    public void test5663() throws Throwable {
        StringWriter stringWriter0 = new StringWriter();
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        jsonWriter0.jsonValue("");
        jsonWriter0.close();
        assertTrue(("YhZIkLSDJ /X7,}r" == null) == false);
        com.google.gson.stream.JsonWriter default4;
        default4 = jsonWriter0.name("YhZIkLSDJ /X7,}r");
    }

    @Test(timeout = 4000)
    public void test5764() throws Throwable {
        StringWriter stringWriter0 = new StringWriter();
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        assertTrue(("YhZIkLSDJ /X7,}r" == null) == false);
        com.google.gson.stream.JsonWriter jsonWriter1;
        jsonWriter1 = jsonWriter0.name("YhZIkLSDJ /X7,}r");
        assertTrue(("YhZIkLSDJ /X7,}r" == null) == false);
        com.google.gson.stream.JsonWriter default5;
        default5 = jsonWriter1.name("YhZIkLSDJ /X7,}r");
    }

    @Test(timeout = 4000)
    public void test5865() throws Throwable {
        StringWriter stringWriter0 = new StringWriter();
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        assertTrue(((String) null == null) == false);
        com.google.gson.stream.JsonWriter default6;
        default6 = jsonWriter0.name((String) null);
    }

    @Test(timeout = 4000)
    public void test5966() throws Throwable {
        StringWriter stringWriter0 = new StringWriter(38);
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        JsonWriter jsonWriter1 = jsonWriter0.value(0L);
        jsonWriter1.close();
        jsonWriter1.endArray();
    }

    @Test(timeout = 4000)
    public void test6067() throws Throwable {
        StringWriter stringWriter0 = new StringWriter(0);
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        jsonWriter0.setIndent("");
        jsonWriter0.getSerializeNulls();
    }

    @Test(timeout = 4000)
    public void test6168() throws Throwable {
        JsonWriter jsonWriter0 = null;
        jsonWriter0 = new JsonWriter((Writer) null);
    }

    @Test(timeout = 4000)
    public void test6269() throws Throwable {
        StringWriter stringWriter0 = new StringWriter();
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        JsonWriter jsonWriter1 = jsonWriter0.beginObject();
        assertTrue(("" == null) == false);
        com.google.gson.stream.JsonWriter jsonWriter2;
        jsonWriter2 = jsonWriter1.name("");
        JsonWriter jsonWriter3 = jsonWriter0.beginArray();
        assertTrue((">V{+[ex" == null) == false);
        com.google.gson.stream.JsonWriter default7;
        default7 = jsonWriter3.name(">V{+[ex");
        jsonWriter2.endArray();
    }

    @Test(timeout = 4000)
    public void test6370() throws Throwable {
        StringWriter stringWriter0 = new StringWriter(0);
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        jsonWriter0.endObject();
    }

    @Test(timeout = 4000)
    public void test6471() throws Throwable {
        StringWriter stringWriter0 = new StringWriter(1348);
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        boolean boolean0 = jsonWriter0.getSerializeNulls();
    }

    @Test(timeout = 4000)
    public void test6572() throws Throwable {
        StringWriter stringWriter0 = new StringWriter(0);
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        jsonWriter0.isLenient();
        jsonWriter0.getSerializeNulls();
    }

    @Test(timeout = 4000)
    public void test6673() throws Throwable {
        StringWriter stringWriter0 = new StringWriter();
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        JsonWriter jsonWriter1 = jsonWriter0.beginArray();
        stringWriter0.toString();
    }

    @Test(timeout = 4000)
    public void test6674() throws Throwable {
        StringWriter stringWriter0 = new StringWriter();
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        JsonWriter jsonWriter1 = jsonWriter0.beginArray();
        Double double0 = new Double((-2415.298894));
        jsonWriter1.value((Number) double0);
        jsonWriter1.jsonValue("=1A5/g7.E^");
        jsonWriter0.getSerializeNulls();
    }

    @Test(timeout = 4000)
    public void test6775() throws Throwable {
        StringWriter stringWriter0 = new StringWriter();
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        jsonWriter0.isHtmlSafe();
        jsonWriter0.getSerializeNulls();
    }

    @Test(timeout = 4000)
    public void test6876() throws Throwable {
        StringWriter stringWriter0 = new StringWriter();
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        jsonWriter0.setHtmlSafe(true);
        jsonWriter0.value("=1A5/g7.E^");
        stringWriter0.toString();
    }
}
