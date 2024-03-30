/*
 * This file was automatically generated by EvoSuite
 * Sun Oct 15 20:47:20 GMT 2023
 */
package org.jsoup.nodes;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class DocumentType_ESTest extends DocumentType_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test00() throws Throwable {
        DocumentType documentType0 = new DocumentType(" PUBLIC \"", " PUBLIC \"", " PUBLIC \"", "");
        StringBuilder stringBuilder0 = new StringBuilder(0);
        Document.OutputSettings document_OutputSettings0 = new Document.OutputSettings();
        documentType0.outerHtmlHead(stringBuilder0, 4, document_OutputSettings0);
        stringBuilder0.toString();
    }

    @Test(timeout = 4000)
    public void test11() throws Throwable {
        DocumentType documentType0 = new DocumentType("?_p[gQ[p", "`#pMDw.`n~_g|U=[{<D", "name", "t!@`h#:}<V@U4^S");
        StringBuilder stringBuilder0 = new StringBuilder((CharSequence) "?_p[gQ[p");
        documentType0.outerHtmlTail(stringBuilder0, 0, (Document.OutputSettings) null);
        documentType0.baseUri();
        assertNotNull(documentType0.baseUri());
    }

    @Test(timeout = 4000)
    public void test22() throws Throwable {
        DocumentType documentType0 = new DocumentType("1h", "", "PekqQ2W~", "1h");
        Document.OutputSettings document_OutputSettings0 = new Document.OutputSettings();
        documentType0.outerHtmlHead((StringBuilder) null, 982, document_OutputSettings0);
    }

    @Test(timeout = 4000)
    public void test33() throws Throwable {
        DocumentType documentType0 = null;
        documentType0 = new DocumentType("(G3~{dE,6nC", (String) null, "", "(G3~{dE,6nC");
    }

    @Test(timeout = 4000)
    public void test44() throws Throwable {
        DocumentType documentType0 = new DocumentType("", "", "rho", "rho");
        String string0 = documentType0.nodeName();
        assertNotNull(string0);
    }

    @Test(timeout = 4000)
    public void test55() throws Throwable {
        DocumentType documentType0 = new DocumentType("", "", "", "");
        String string0 = documentType0.toString();
        documentType0.nodeName();
        assertNotNull(documentType0.nodeName());
    }

    @Test(timeout = 4000)
    public void test56() throws Throwable {
        DocumentType documentType0 = new DocumentType("", "", "", "");
        String string0 = documentType0.toString();
        assertNotNull(string0);
    }
}
