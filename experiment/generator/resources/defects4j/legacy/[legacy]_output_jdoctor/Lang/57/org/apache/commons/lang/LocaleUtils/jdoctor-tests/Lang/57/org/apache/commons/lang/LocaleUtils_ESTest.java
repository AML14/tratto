/*
 * This file was automatically generated by EvoSuite
 * Mon Oct 16 04:06:09 GMT 2023
 */
package org.apache.commons.lang;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import org.apache.commons.lang.LocaleUtils;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class LocaleUtils_ESTest extends LocaleUtils_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        assertTrue("sz}\":;`\"JEfu-J" == null);
        java.util.Locale default0;
        default0 = LocaleUtils.toLocale("sz}\":;`\"JEfu-J");
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        assertTrue("ga" == null);
        java.util.Locale locale0;
        locale0 = LocaleUtils.toLocale("ga");
        locale0.getVariant();
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        assertTrue("zh-CN" == null);
        java.util.Locale default1;
        default1 = LocaleUtils.toLocale("zh-CN");
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        assertTrue("ae|Wpu)I8n" == null);
        java.util.Locale default2;
        default2 = LocaleUtils.toLocale("ae|Wpu)I8n");
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        Locale locale0 = Locale.FRENCH;
        Locale locale1 = Locale.US;
        List list0 = LocaleUtils.localeLookupList(locale1, locale0);
        list0.size();
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        Locale locale0 = Locale.FRENCH;
        List list0 = LocaleUtils.localeLookupList(locale0, locale0);
        list0.size();
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        assertTrue("es" == null);
        java.util.Locale locale0;
        locale0 = LocaleUtils.toLocale("es");
        LinkedList<Locale.LanguageRange> linkedList0 = new LinkedList<Locale.LanguageRange>();
        LinkedList<Locale> linkedList1 = new LinkedList<Locale>();
        List<Locale> list0 = Locale.filter((List<Locale.LanguageRange>) linkedList0, (Collection<Locale>) linkedList1);
        List<Locale> list1 = Locale.filter((List<Locale.LanguageRange>) linkedList0, (Collection<Locale>) list0);
        Locale.FilteringMode locale_FilteringMode0 = Locale.FilteringMode.IGNORE_EXTENDED_RANGES;
        List<Locale> list2 = Locale.filter((List<Locale.LanguageRange>) linkedList0, (Collection<Locale>) list1, locale_FilteringMode0);
        Locale.filter((List<Locale.LanguageRange>) linkedList0, (Collection<Locale>) list2, locale_FilteringMode0);
        Locale locale1 = Locale.SIMPLIFIED_CHINESE;
        locale0.getDisplayCountry(locale1);
        locale0.getUnicodeLocaleKeys();
        locale0.getExtensionKeys();
        Locale locale2 = Locale.JAPAN;
        locale2.getUnicodeLocaleAttributes();
        locale0.getDisplayName(locale2);
        assertTrue("es".isEmpty());
        java.util.List list3;
        list3 = LocaleUtils.countriesByLanguage("es");
        list3.size();
    }

    @Test(timeout = 4000)
    public void test077() throws Throwable {
        assertTrue("sz}\":;`\"JEfu-J".isEmpty());
        java.util.List list0;
        list0 = LocaleUtils.countriesByLanguage("sz}\":;`\"JEfu-J");
        list0.isEmpty();
    }

    @Test(timeout = 4000)
    public void test088() throws Throwable {
        Locale.LanguageRange.parse("TH");
        Object object0 = new Object();
        assertTrue("TH".isEmpty());
        java.util.List default3;
        default3 = LocaleUtils.languagesByCountry("TH");
        assertTrue("=;7pDB_F,!,_,".isEmpty());
        java.util.List default4;
        default4 = LocaleUtils.languagesByCountry("=;7pDB_F,!,_,");
        List list0 = LocaleUtils.availableLocaleList();
        list0.isEmpty();
    }

    @Test(timeout = 4000)
    public void test099() throws Throwable {
        assertTrue("lb" == null);
        java.util.Locale default5;
        default5 = LocaleUtils.toLocale("lb");
        assertTrue("lb".isEmpty());
        java.util.List default6;
        default6 = LocaleUtils.languagesByCountry("lb");
        assertTrue("lb".isEmpty());
        java.util.List default7;
        default7 = LocaleUtils.languagesByCountry("lb");
        LocaleUtils localeUtils0 = new LocaleUtils();
        assertTrue("q=2".isEmpty());
        java.util.List default8;
        default8 = LocaleUtils.languagesByCountry("q=2");
        assertTrue("".isEmpty());
        java.util.List default9;
        default9 = LocaleUtils.countriesByLanguage("");
        assertTrue("".isEmpty());
        java.util.List list0;
        list0 = LocaleUtils.languagesByCountry("");
        list0.isEmpty();
    }

    @Test(timeout = 4000)
    public void test1010() throws Throwable {
        assertTrue((String) null.isEmpty());
        java.util.List default10;
        default10 = LocaleUtils.countriesByLanguage((String) null);
        assertTrue((String) null.isEmpty());
        java.util.List default11;
        default11 = LocaleUtils.languagesByCountry((String) null);
        assertTrue((String) null.isEmpty());
        java.util.List default12;
        default12 = LocaleUtils.languagesByCountry((String) null);
        LocaleUtils localeUtils0 = new LocaleUtils();
        assertTrue("W8%Q&dA}l\"6)L}yU1}(".isEmpty());
        java.util.List default13;
        default13 = LocaleUtils.languagesByCountry("W8%Q&dA}l\"6)L}yU1}(");
        assertTrue((String) null == null);
        java.util.Locale default14;
        default14 = LocaleUtils.toLocale((String) null);
        LocaleUtils.isAvailableLocale((Locale) null);
    }

    @Test(timeout = 4000)
    public void test1111() throws Throwable {
        assertTrue("es" == null);
        java.util.Locale locale0;
        locale0 = LocaleUtils.toLocale("es");
        LocaleUtils.availableLocaleSet();
        boolean boolean0 = LocaleUtils.isAvailableLocale(locale0);
    }

    @Test(timeout = 4000)
    public void test1212() throws Throwable {
        Set set0 = LocaleUtils.availableLocaleSet();
    }

    @Test(timeout = 4000)
    public void test1213() throws Throwable {
        Set set0 = LocaleUtils.availableLocaleSet();
        boolean boolean0 = LocaleUtils.isAvailableLocale((Locale) null);
    }

    @Test(timeout = 4000)
    public void test1314() throws Throwable {
        LocaleUtils.availableLocaleSet();
        Set set0 = LocaleUtils.availableLocaleSet();
        set0.size();
    }

    @Test(timeout = 4000)
    public void test1415() throws Throwable {
        Locale locale0 = Locale.FRENCH;
        Locale locale1 = new Locale("", "", "uC");
        List list0 = LocaleUtils.localeLookupList(locale1, locale0);
        list0.size();
    }

    @Test(timeout = 4000)
    public void test1516() throws Throwable {
        List list0 = LocaleUtils.localeLookupList((Locale) null, (Locale) null);
        list0.isEmpty();
    }

    @Test(timeout = 4000)
    public void test1617() throws Throwable {
        assertTrue("wrp8S!^Uid]%,U" == null);
        java.util.Locale default15;
        default15 = LocaleUtils.toLocale("wrp8S!^Uid]%,U");
    }

    @Test(timeout = 4000)
    public void test1718() throws Throwable {
        assertTrue("i!<{nUY" == null);
        java.util.Locale default16;
        default16 = LocaleUtils.toLocale("i!<{nUY");
    }

    @Test(timeout = 4000)
    public void test1819() throws Throwable {
        assertTrue("|{G,c6}UK%I^^[" == null);
        java.util.Locale default17;
        default17 = LocaleUtils.toLocale("|{G,c6}UK%I^^[");
    }

    @Test(timeout = 4000)
    public void test1920() throws Throwable {
        assertTrue("7744;[8lT" == null);
        java.util.Locale default18;
        default18 = LocaleUtils.toLocale("7744;[8lT");
    }

    @Test(timeout = 4000)
    public void test2021() throws Throwable {
        assertTrue("" == null);
        java.util.Locale default19;
        default19 = LocaleUtils.toLocale("");
    }

    @Test(timeout = 4000)
    public void test2122() throws Throwable {
        Locale locale0 = Locale.UK;
        List list0 = LocaleUtils.localeLookupList(locale0);
        list0.size();
    }

    @Test(timeout = 4000)
    public void test2223() throws Throwable {
        LocaleUtils.availableLocaleList();
        assertTrue("th".isEmpty());
        java.util.List list0;
        list0 = LocaleUtils.countriesByLanguage("th");
        list0.isEmpty();
    }
}
