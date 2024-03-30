/*
 * This file was automatically generated by EvoSuite
 * Sun Nov 05 18:38:31 GMT 2023
 */
package org.jsoup.parser;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class Parser_ESTest extends Parser_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        Document document0 = Parser.parseBodyFragment("<BasE", "<BasE");
        document0.baseUri();
        assertNotNull(document0.baseUri());
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        Document document0 = Parser.parse("<!-", "<!-");
        document0.isBlock();
        assertTrue(document0.isBlock());
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        Document document0 = Parser.parse("<![CDATA[", "<![CDATA[");
        document0.isBlock();
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        Document document0 = Parser.parseBodyFragment("=mS/d(jJ'>|<4{mL/>+", "=mS/d(jJ'>|<4{mL/>+");
        document0.nodeName();
        assertNotNull(document0.nodeName());
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        Document document0 = Parser.parseBodyFragment("<!--%s-->", "<!--%s-->");
        document0.baseUri();
        assertNotNull(document0.baseUri());
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        Document document0 = Parser.parse("<?", "<?");
        document0.tagName();
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        Document document0 = Parser.parse("<!--", "<!--");
        document0.nodeName();
    }

    @Test(timeout = 4000)
    public void test077() throws Throwable {
        Document document0 = Parser.parse("</", "</");
        document0.isBlock();
        assertFalse(document0.isBlock());
    }

    @Test(timeout = 4000)
    public void test088() throws Throwable {
        Document document0 = Parser.parse("htx<_", "htx<_");
        document0.baseUri();
        assertNotNull(document0.baseUri());
    }

    @Test(timeout = 4000)
    public void test099() throws Throwable {
        Document document0 = Parser.parse("<html>\n<head>\n</head>\n<body>\n</body>\n</html>\n<noframes></noframes>", "{K<BBrE");
        document0.baseUri();
        assertNotNull(document0.baseUri());
    }

    @Test(timeout = 4000)
    public void test1010() throws Throwable {
        Document document0 = Parser.parse("<title></title>\n<html>\n<head>\n</head>\n<body> {-QEx?AV]87\n</body>\n</html>", "<title></title>\n<html>\n<head>\n</head>\n<body> {-QEx?AV]87\n</body>\n</html>");
        document0.nodeName();
        assertNotNull(document0.nodeName());
    }

    @Test(timeout = 4000)
    public void test1111() throws Throwable {
        Document document0 = Parser.parse("<html>\n<head>\n</head>\n<body>\n</body>\n</html><textarea></textarea>", "{K<BBrE");
        document0.baseUri();
        assertNotNull(document0.baseUri());
    }

    @Test(timeout = 4000)
    public void test1212() throws Throwable {
        Document document0 = Parser.parse("#C{<GWgL-=izCK", "#C{<GWgL-=izCK");
        document0.isBlock();
    }

    @Test(timeout = 4000)
    public void test1313() throws Throwable {
        Document document0 = Parser.parseBodyFragment("yFF5j='13a4s", "hcue");
        document0.wrap("<yff5j='13a4s>\n</yff5j='13a4s>\n<html>\n<head>\n</head>\n<body>\n yFF5j='13a4s\n</body>\n</html>");
    }

    @Test(timeout = 4000)
    public void test1414() throws Throwable {
        Document document0 = Parser.parse("<html>\n<head>\n <title>#C{&lt;GWgL-=izCK</title>\n</head>\n<body> #C{<gwgl -=\"izCK\">\n </gwgl>\n</body>\n</html>", "<html>\n<head>\n <title>#C{&lt;GWgL-=izCK</title>\n</head>\n<body> #C{<gwgl -=\"izCK\">\n </gwgl>\n</body>\n</html>");
        document0.nodeName();
        assertNotNull(document0.nodeName());
    }

    @Test(timeout = 4000)
    public void test1515() throws Throwable {
        Document document0 = Parser.parse("<PS{eoDT9=>$4K", "<PS{eoDT9=>$4K");
        document0.isBlock();
    }

    @Test(timeout = 4000)
    public void test1616() throws Throwable {
        Document document0 = Parser.parse("-<n]@]=M J-.y", "-<n]@]=M J-.y");
        document0.baseUri();
    }

    @Test(timeout = 4000)
    public void test1717() throws Throwable {
        Document document0 = Parser.parse("<Br", "<Br");
        document0.nodeName();
        assertNotNull(document0.nodeName());
    }

    @Test(timeout = 4000)
    public void test1818() throws Throwable {
        Document document0 = Parser.parse("]qG}x</InP3", "]qG}x</InP3");
        document0.isBlock();
        assertFalse(document0.isBlock());
    }

    @Test(timeout = 4000)
    public void test1919() throws Throwable {
        Document document0 = Parser.parse("<html>\n</html>\n<html>\n<head>\n</head>\n<body> a:;hpGDx1.c(|MX\n</body>\n</html>", "<html>\n</html>\n<html>\n<head>\n</head>\n<body> a:;hpGDx1.c(|MX\n</body>\n</html>");
        document0.baseUri();
        assertNotNull(document0.baseUri());
    }
}
