/*
 * This file was automatically generated by EvoSuite
 * Sun Oct 15 21:09:48 GMT 2023
 */
package org.jsoup.safety;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Cleaner;
import org.jsoup.safety.Whitelist;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class Cleaner_ESTest extends Cleaner_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test00() throws Throwable {
        Whitelist whitelist0 = Whitelist.basicWithImages();
        Cleaner cleaner0 = new Cleaner(whitelist0);
        Document document0 = new Document("");
        cleaner0.isValid(document0);
    }

    @Test(timeout = 4000)
    public void test11() throws Throwable {
        Whitelist whitelist0 = Whitelist.basic();
        Cleaner cleaner0 = new Cleaner(whitelist0);
        cleaner0.isValid((Document) null);
    }

    @Test(timeout = 4000)
    public void test22() throws Throwable {
        Whitelist whitelist0 = Whitelist.relaxed();
        Cleaner cleaner0 = new Cleaner(whitelist0);
        Document document0 = new Document("lf.>\"");
        cleaner0.clean(document0);
    }

    @Test(timeout = 4000)
    public void test33() throws Throwable {
        Whitelist whitelist0 = new Whitelist();
        Cleaner cleaner0 = new Cleaner(whitelist0);
        cleaner0.clean((Document) null);
    }

    @Test(timeout = 4000)
    public void test44() throws Throwable {
        Whitelist whitelist0 = Whitelist.basic();
        Cleaner cleaner0 = new Cleaner(whitelist0);
        Document document0 = Document.createShell("hT");
        document0.text("hT");
        boolean boolean0 = cleaner0.isValid(document0);
    }

    @Test(timeout = 4000)
    public void test55() throws Throwable {
        Whitelist whitelist0 = Whitelist.basic();
        Cleaner cleaner0 = new Cleaner(whitelist0);
        Document document0 = Document.createShell("F~+?x1bgjG2'0");
        Document document1 = cleaner0.clean(document0);
    }

    @Test(timeout = 4000)
    public void test66() throws Throwable {
        Cleaner cleaner0 = null;
        cleaner0 = new Cleaner((Whitelist) null);
    }
}
