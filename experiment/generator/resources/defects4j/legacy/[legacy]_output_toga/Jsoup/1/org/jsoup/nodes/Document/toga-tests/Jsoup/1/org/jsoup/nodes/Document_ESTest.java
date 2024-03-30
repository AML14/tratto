/*
 * This file was automatically generated by EvoSuite
 * Sun Nov 05 18:34:43 GMT 2023
 */
package org.jsoup.nodes;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class Document_ESTest extends Document_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test00() throws Throwable {
        Document document0 = new Document("Um5");
        String string0 = document0.toString();
        assertNotNull(string0);
    }

    @Test(timeout = 4000)
    public void test11() throws Throwable {
        Document document0 = new Document("Um5");
        Element element0 = document0.append("Um5");
        Document document1 = document0.normalise();
    }

    @Test(timeout = 4000)
    public void test22() throws Throwable {
        Document document0 = new Document("Um5");
        document0.text("");
    }

    @Test(timeout = 4000)
    public void test33() throws Throwable {
        Document document0 = Document.createShell(" )#[FUgfDGNgzrpP$1");
        String string0 = document0.nodeName();
        assertNotNull(string0);
    }

    @Test(timeout = 4000)
    public void test44() throws Throwable {
        Document document0 = Document.createShell("'I\"Bbb]/YzOf9");
        Element element0 = document0.createElement(")->lf?z1% sH:jREt");
        element0.nodeName();
        assertNotNull(element0.nodeName());
    }

    @Test(timeout = 4000)
    public void test55() throws Throwable {
        Document document0 = Document.createShell("{D<");
        String string0 = document0.title();
        assertNotNull(string0);
    }

    @Test(timeout = 4000)
    public void test66() throws Throwable {
        Document document0 = Document.createShell("'I\"Bbb]/YzOf9");
        document0.title("'I\"Bbb]/YzOf9");
        String string0 = document0.title();
    }

    @Test(timeout = 4000)
    public void test77() throws Throwable {
        Document document0 = Document.createShell("'I\"Bbb]/YzOf9");
        document0.title("'I\"Bbb]/YzOf9");
        document0.title("xp#&U");
        document0.nodeName();
        assertNotNull(document0.nodeName());
    }

    @Test(timeout = 4000)
    public void test88() throws Throwable {
        Document document0 = new Document("Um5");
        document0.appendText("");
        Document document1 = document0.normalise();
        document1.tagName();
        assertNotNull(document1.tagName());
    }
}
