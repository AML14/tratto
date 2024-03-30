/*
 * This file was automatically generated by EvoSuite
 * Sun Oct 15 20:40:18 GMT 2023
 */
package org.jsoup.select;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.regex.Pattern;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;
import org.jsoup.select.Selector;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class Selector_ESTest extends Selector_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        Document document0 = new Document("PARAM");
        Element element0 = document0.createElement("PARAM");
        Elements elements0 = Selector.select("PARAM", element0);
        elements0.isEmpty();
        assertTrue(elements0.isEmpty());
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        Tag tag0 = Tag.valueOf("A9");
        Element element0 = new Element(tag0, "A9");
        Pattern pattern0 = Pattern.compile("^", 32);
        Elements elements0 = element0.getElementsMatchingText(pattern0);
        Elements elements1 = Selector.select("A9", (Iterable<Element>) elements0);
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        Document document0 = Document.createShell("MENU");
        Elements elements0 = document0.getElementsByAttributeValueNot("1V}\"VycY", "1V}\"VycY");
        Elements elements1 = Selector.select("MENU", (Iterable<Element>) elements0);
        Elements elements2 = Selector.filterOut(elements0, elements1);
        elements2.size();
        assertEquals(0, elements2.size());
    }

    @Test(timeout = 4000)
    public void test043() throws Throwable {
        Document document0 = new Document("?+b");
        Elements elements0 = document0.getAllElements();
        Selector.select("?+b", (Iterable<Element>) elements0);
    }

    @Test(timeout = 4000)
    public void test054() throws Throwable {
        Document document0 = Document.createShell(":M.");
        Element element0 = document0.text(",!iyHx;LGjoGE");
        Pattern pattern0 = Pattern.compile(",!iyHx;LGjoGE", 856);
        Elements elements0 = element0.getElementsMatchingText(pattern0);
        LinkedList<Element> linkedList0 = new LinkedList<Element>(elements0);
        Selector.select(",!iyHx;LGjoGE", (Iterable<Element>) linkedList0);
    }

    @Test(timeout = 4000)
    public void test065() throws Throwable {
        Selector.filterOut((Collection<Element>) null, (Collection<Element>) null);
    }

    @Test(timeout = 4000)
    public void test086() throws Throwable {
        Document document0 = Document.createShell("O=(3KH.9.)(>0P*");
        Elements elements0 = document0.getAllElements();
        Elements elements1 = Selector.filterOut(elements0, elements0);
        elements1.size();
        assertEquals(0, elements1.size());
    }

    @Test(timeout = 4000)
    public void test097() throws Throwable {
        Document document0 = Document.createShell("O=(3KH.9.)(>0P*");
        Elements elements0 = Selector.select("N~SCRIPT", (Element) document0);
        elements0.size();
        assertEquals(1, elements0.size());
    }

    @Test(timeout = 4000)
    public void test108() throws Throwable {
        Document document0 = Document.createShell(":has(el) subselect must not be empty");
        Selector.select("wu[sx=", (Element) document0);
    }

    @Test(timeout = 4000)
    public void test119() throws Throwable {
        Document document0 = Document.createShell("MENU");
        Selector.select("F7|(2,|H>|~Rnbe!9`", (Element) document0);
    }

    @Test(timeout = 4000)
    public void test1210() throws Throwable {
        Document document0 = new Document("Kq.)gNG^9HEwi/w7LcC");
        Selector.select(":matchesOwn(", (Element) document0);
    }

    @Test(timeout = 4000)
    public void test1311() throws Throwable {
        Document document0 = Document.createShell("gX8b[tzFGEbA,#.*%Qe");
        Elements elements0 = Selector.select(":matches(regex) query must not be empty", (Element) document0);
        elements0.size();
        assertEquals(1, elements0.size());
    }

    @Test(timeout = 4000)
    public void test1412() throws Throwable {
        Document document0 = Document.createShell("O=(3KH.9.)(>0P*");
        Elements elements0 = document0.getAllElements();
        Selector.select(":contains(", (Iterable<Element>) elements0);
    }

    @Test(timeout = 4000)
    public void test1513() throws Throwable {
        Document document0 = new Document("?+b");
        Elements elements0 = Selector.select("BAS[5", (Element) document0);
        elements0.size();
        assertEquals(1, elements0.size());
    }

    @Test(timeout = 4000)
    public void test1614() throws Throwable {
        Document document0 = new Document("*EG>~.");
        Selector.select(",", (Element) document0);
    }

    @Test(timeout = 4000)
    public void test1715() throws Throwable {
        Document document0 = Document.createShell("pxNv!/^A}@6hHb");
        Comparator<Object> comparator0 = (Comparator<Object>) mock(Comparator.class, new ViolatedAssumptionAnswer());
        PriorityQueue<Element> priorityQueue0 = new PriorityQueue<Element>(8770, comparator0);
        priorityQueue0.add(document0);
        Elements elements0 = Selector.select("+DfL", (Iterable<Element>) priorityQueue0);
        elements0.isEmpty();
        assertTrue(elements0.isEmpty());
    }

    @Test(timeout = 4000)
    public void test1816() throws Throwable {
        Document document0 = Document.createShell("O=(3KH.9.)(>0P*");
        Elements elements0 = Selector.select("G,M>XM Lx0]Dj%3Fy", (Element) document0);
        elements0.isEmpty();
        assertTrue(elements0.isEmpty());
    }

    @Test(timeout = 4000)
    public void test1917() throws Throwable {
        Document document0 = Document.createShell("MENU");
        Elements elements0 = document0.getElementsByAttributeValueNot("1V}\"VycY", "ObiSSF:$%RX*");
        Selector.select(":gt(%d)", (Iterable<Element>) elements0);
    }

    @Test(timeout = 4000)
    public void test2018() throws Throwable {
        Tag tag0 = Tag.valueOf("`Y2");
        Attributes attributes0 = new Attributes();
        Element element0 = new Element(tag0, "^(z,C2REO^.q8>\"G", attributes0);
        Selector.select(":lt(", element0);
    }

    @Test(timeout = 4000)
    public void test2119() throws Throwable {
        Document document0 = new Document("?+b");
        Selector.select("~.y6V@Ai4w].gnH|M8", (Element) document0);
    }

    @Test(timeout = 4000)
    public void test2220() throws Throwable {
        Document document0 = new Document(":not(selector) subselect must not be empty");
        Elements elements0 = Selector.select(":not(selector) subselect must not be empty", (Element) document0);
        elements0.size();
        assertEquals(1, elements0.size());
    }

    @Test(timeout = 4000)
    public void test2321() throws Throwable {
        Document document0 = Document.createShell(":has(el) subselect must not be empty");
        Elements elements0 = Selector.select(":has(el) subselect must not be empty", (Element) document0);
        elements0.size();
        assertEquals(1, elements0.size());
    }

    @Test(timeout = 4000)
    public void test2422() throws Throwable {
        Document document0 = Document.createShell("MENU");
        Selector.select("J*B#4v:'[~\"M_* <F", (Element) document0);
    }
}
