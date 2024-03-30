/*
 * This file was automatically generated by EvoSuite
 * Sat Nov 04 02:47:57 GMT 2023
 */
package com.google.javascript.jscomp;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.google.javascript.jscomp.Region;
import com.google.javascript.jscomp.SourceFile;
import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.Charset;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.evosuite.runtime.mock.java.io.MockFile;
import org.evosuite.runtime.testdata.EvoSuiteFile;
import org.evosuite.runtime.testdata.FileSystemHandling;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class SourceFile_ESTest extends SourceFile_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        SourceFile.Generator sourceFile_Generator0 = mock(SourceFile.Generator.class, new ViolatedAssumptionAnswer());
        SourceFile.Generated sourceFile_Generated0 = new SourceFile.Generated("com.google.javascript.jscomp.SourceFile$OnDisk", sourceFile_Generator0);
        sourceFile_Generated0.getCodeNoCache();
        sourceFile_Generated0.isExtern();
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        SourceFile.Generated sourceFile_Generated0 = new SourceFile.Generated("com.google.javascript.jscomp.SourceFile$Preloaded", (SourceFile.Generator) null);
        sourceFile_Generated0.clearCachedSource();
        sourceFile_Generated0.isExtern();
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        MockFile mockFile0 = new MockFile("", "");
        SourceFile.OnDisk sourceFile_OnDisk0 = new SourceFile.OnDisk(mockFile0);
        sourceFile_OnDisk0.clearCachedSource();
        sourceFile_OnDisk0.isExtern();
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        SourceFile.fromGenerator((String) null, (SourceFile.Generator) null);
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        SourceFile sourceFile0 = SourceFile.fromFile("com.google.javascript.jscomp.SourceFile", (Charset) null);
        sourceFile0.isExtern();
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        SourceFile.Preloaded sourceFile_Preloaded0 = (SourceFile.Preloaded) SourceFile.fromCode("com.google.common.io.Files", "", "\nActual: ");
        int int0 = sourceFile_Preloaded0.getNumLines();
        sourceFile_Preloaded0.isExtern();
    }

    @Test(timeout = 4000)
    public void test056() throws Throwable {
        SourceFile.Preloaded sourceFile_Preloaded0 = (SourceFile.Preloaded) SourceFile.fromCode("com.google.common.io.Files", "", "\nActual: ");
        int int0 = sourceFile_Preloaded0.getNumLines();
        sourceFile_Preloaded0.toString();
    }

    @Test(timeout = 4000)
    public void test057() throws Throwable {
        SourceFile.Preloaded sourceFile_Preloaded0 = (SourceFile.Preloaded) SourceFile.fromCode("com.google.common.io.Files", "", "\nActual: ");
        int int0 = sourceFile_Preloaded0.getNumLines();
    }

    @Test(timeout = 4000)
    public void test058() throws Throwable {
        SourceFile.Preloaded sourceFile_Preloaded0 = (SourceFile.Preloaded) SourceFile.fromCode("com.google.common.io.Files", "", "\nActual: ");
        int int0 = sourceFile_Preloaded0.getNumLines();
        sourceFile_Preloaded0.getOriginalPath();
    }

    @Test(timeout = 4000)
    public void test069() throws Throwable {
        File file0 = MockFile.createTempFile("\"x5BI7V", "");
        SourceFile.OnDisk sourceFile_OnDisk0 = new SourceFile.OnDisk(file0);
        Reader reader0 = sourceFile_OnDisk0.getCodeReader();
        SourceFile.fromReader("", reader0);
    }

    @Test(timeout = 4000)
    public void test0710() throws Throwable {
        File file0 = MockFile.createTempFile("\"x5BI7V", "");
        SourceFile.OnDisk sourceFile_OnDisk0 = new SourceFile.OnDisk(file0);
        assertTrue(1181 == 1);
        com.google.javascript.jscomp.Region region0;
        region0 = sourceFile_OnDisk0.getRegion(1181);
    }

    @Test(timeout = 4000)
    public void test0711() throws Throwable {
        File file0 = MockFile.createTempFile("\"x5BI7V", "");
        SourceFile.OnDisk sourceFile_OnDisk0 = new SourceFile.OnDisk(file0);
        assertTrue(1181 == 1);
        com.google.javascript.jscomp.Region region0;
        region0 = sourceFile_OnDisk0.getRegion(1181);
        sourceFile_OnDisk0.getCodeReader();
        sourceFile_OnDisk0.isExtern();
    }

    @Test(timeout = 4000)
    public void test0812() throws Throwable {
        SourceFile.Preloaded sourceFile_Preloaded0 = new SourceFile.Preloaded("\n", "\n");
        sourceFile_Preloaded0.clearCachedSource();
        sourceFile_Preloaded0.isExtern();
    }

    @Test(timeout = 4000)
    public void test0913() throws Throwable {
        SourceFile sourceFile0 = SourceFile.fromCode("<5f-.U>^", "com.google.javascript.jscomp.SourceFile$OnDisk", "com.google.javascript.jscomp.SourceFile$OnDisk");
        String string0 = sourceFile0.getName();
        sourceFile0.isExtern();
    }

    @Test(timeout = 4000)
    public void test0914() throws Throwable {
        SourceFile sourceFile0 = SourceFile.fromCode("<5f-.U>^", "com.google.javascript.jscomp.SourceFile$OnDisk", "com.google.javascript.jscomp.SourceFile$OnDisk");
        String string0 = sourceFile0.getName();
        sourceFile0.getOriginalPath();
    }

    @Test(timeout = 4000)
    public void test0915() throws Throwable {
        SourceFile sourceFile0 = SourceFile.fromCode("<5f-.U>^", "com.google.javascript.jscomp.SourceFile$OnDisk", "com.google.javascript.jscomp.SourceFile$OnDisk");
        String string0 = sourceFile0.getName();
    }

    @Test(timeout = 4000)
    public void test1016() throws Throwable {
        SourceFile.Preloaded sourceFile_Preloaded0 = new SourceFile.Preloaded("\n", "\n");
        sourceFile_Preloaded0.isExtern();
    }

    @Test(timeout = 4000)
    public void test1017() throws Throwable {
        SourceFile.Preloaded sourceFile_Preloaded0 = new SourceFile.Preloaded("\n", "\n");
        sourceFile_Preloaded0.setIsExtern(true);
        sourceFile_Preloaded0.isExtern();
    }

    @Test(timeout = 4000)
    public void test1118() throws Throwable {
        SourceFile sourceFile0 = SourceFile.fromFile("[!Xro._$kM");
        sourceFile0.toString();
        sourceFile0.isExtern();
    }

    @Test(timeout = 4000)
    public void test1219() throws Throwable {
        SourceFile.fromInputStream("|g3@%8]M2", "|g3@%8]M2", (InputStream) null);
    }

    @Test(timeout = 4000)
    public void test1320() throws Throwable {
        SourceFile.fromInputStream("|g3@%8]M2", (InputStream) null);
    }

    @Test(timeout = 4000)
    public void test1421() throws Throwable {
        StringReader stringReader0 = new StringReader("F?Y9)jj5&$|$SewjSdp");
        SourceFile sourceFile0 = SourceFile.fromReader("UTF-16LE", stringReader0);
        boolean boolean0 = sourceFile0.isExtern();
    }

    @Test(timeout = 4000)
    public void test1422() throws Throwable {
        StringReader stringReader0 = new StringReader("F?Y9)jj5&$|$SewjSdp");
        SourceFile sourceFile0 = SourceFile.fromReader("UTF-16LE", stringReader0);
        boolean boolean0 = sourceFile0.isExtern();
        sourceFile0.getOriginalPath();
    }

    @Test(timeout = 4000)
    public void test1523() throws Throwable {
        SourceFile.Preloaded sourceFile_Preloaded0 = new SourceFile.Preloaded("\n", "\n");
        sourceFile_Preloaded0.getNumLines();
        sourceFile_Preloaded0.getLineOffset(1);
    }

    @Test(timeout = 4000)
    public void test1624() throws Throwable {
        SourceFile.Preloaded sourceFile_Preloaded0 = new SourceFile.Preloaded("\n", "com.google.common.io.PatternFilenameFilter");
        sourceFile_Preloaded0.getLineOffset(1);
        sourceFile_Preloaded0.isExtern();
    }

    @Test(timeout = 4000)
    public void test1625() throws Throwable {
        SourceFile.Preloaded sourceFile_Preloaded0 = new SourceFile.Preloaded("\n", "com.google.common.io.PatternFilenameFilter");
        sourceFile_Preloaded0.getLineOffset(1);
        sourceFile_Preloaded0.getOriginalPath();
    }

    @Test(timeout = 4000)
    public void test1726() throws Throwable {
        SourceFile.Preloaded sourceFile_Preloaded0 = new SourceFile.Preloaded("*$P", "*$P");
        sourceFile_Preloaded0.getLineOffset((-5));
    }

    @Test(timeout = 4000)
    public void test1827() throws Throwable {
        SourceFile.Preloaded sourceFile_Preloaded0 = new SourceFile.Preloaded("\n", "\n", "\n");
        sourceFile_Preloaded0.getNumLines();
        int int0 = sourceFile_Preloaded0.getNumLines();
        sourceFile_Preloaded0.isExtern();
    }

    @Test(timeout = 4000)
    public void test1828() throws Throwable {
        SourceFile.Preloaded sourceFile_Preloaded0 = new SourceFile.Preloaded("\n", "\n", "\n");
        sourceFile_Preloaded0.getNumLines();
        int int0 = sourceFile_Preloaded0.getNumLines();
    }

    @Test(timeout = 4000)
    public void test1929() throws Throwable {
        SourceFile.Generator sourceFile_Generator0 = mock(SourceFile.Generator.class, new ViolatedAssumptionAnswer());
        SourceFile sourceFile0 = SourceFile.fromGenerator(")&5AMT~.rI", sourceFile_Generator0);
        String string0 = sourceFile0.getOriginalPath();
    }

    @Test(timeout = 4000)
    public void test1930() throws Throwable {
        SourceFile.Generator sourceFile_Generator0 = mock(SourceFile.Generator.class, new ViolatedAssumptionAnswer());
        SourceFile sourceFile0 = SourceFile.fromGenerator(")&5AMT~.rI", sourceFile_Generator0);
        String string0 = sourceFile0.getOriginalPath();
        sourceFile0.isExtern();
    }

    @Test(timeout = 4000)
    public void test2031() throws Throwable {
        SourceFile sourceFile0 = SourceFile.fromCode("KDf<fn1Z[L54", "KDf<fn1Z[L54", "KDf<fn1Z[L54");
        sourceFile0.getOriginalPath();
        sourceFile0.isExtern();
    }

    @Test(timeout = 4000)
    public void test2132() throws Throwable {
        SourceFile.Preloaded sourceFile_Preloaded0 = new SourceFile.Preloaded("W6I7!xGh}qw{}5'hF", "W6I7!xGh}qw{}5'hF");
        assertTrue((-2487) == 1);
        java.lang.String default0;
        default0 = sourceFile_Preloaded0.getLine((-2487));
        sourceFile_Preloaded0.isExtern();
    }

    @Test(timeout = 4000)
    public void test2233() throws Throwable {
        File file0 = MockFile.createTempFile("\nActual: ", "\nActual: ");
        EvoSuiteFile evoSuiteFile0 = new EvoSuiteFile("/var/folders/vv/c7q6vm8920vc7d5p_87011w40000gn/T/\nActual: 0\nActual: ");
        FileSystemHandling.appendLineToFile(evoSuiteFile0, "\nActual: ");
        Charset charset0 = Charset.defaultCharset();
        SourceFile.OnDisk sourceFile_OnDisk0 = new SourceFile.OnDisk(file0, charset0);
        assertTrue(2 == 1);
        java.lang.String string0;
        string0 = sourceFile_OnDisk0.getLine(2);
    }

    @Test(timeout = 4000)
    public void test2234() throws Throwable {
        File file0 = MockFile.createTempFile("\nActual: ", "\nActual: ");
        EvoSuiteFile evoSuiteFile0 = new EvoSuiteFile("/var/folders/vv/c7q6vm8920vc7d5p_87011w40000gn/T/\nActual: 0\nActual: ");
        FileSystemHandling.appendLineToFile(evoSuiteFile0, "\nActual: ");
        Charset charset0 = Charset.defaultCharset();
        SourceFile.OnDisk sourceFile_OnDisk0 = new SourceFile.OnDisk(file0, charset0);
        assertTrue(2 == 1);
        java.lang.String string0;
        string0 = sourceFile_OnDisk0.getLine(2);
    }

    @Test(timeout = 4000)
    public void test2335() throws Throwable {
        File file0 = MockFile.createTempFile("\nActual: ", "\nActual: ");
        EvoSuiteFile evoSuiteFile0 = new EvoSuiteFile("/var/folders/vv/c7q6vm8920vc7d5p_87011w40000gn/T/\nActual: 0\nActual: ");
        FileSystemHandling.appendLineToFile(evoSuiteFile0, "\nActual: ");
        Charset charset0 = Charset.defaultCharset();
        SourceFile.OnDisk sourceFile_OnDisk0 = new SourceFile.OnDisk(file0, charset0);
        assertTrue(380 == 1);
        com.google.javascript.jscomp.Region region0;
        region0 = sourceFile_OnDisk0.getRegion(380);
    }

    @Test(timeout = 4000)
    public void test2436() throws Throwable {
        File file0 = MockFile.createTempFile("\nActual: ", "\nActual: ");
        EvoSuiteFile evoSuiteFile0 = new EvoSuiteFile("/var/folders/vv/c7q6vm8920vc7d5p_87011w40000gn/T/\nActual: 0\nActual: ");
        FileSystemHandling.appendLineToFile(evoSuiteFile0, "\nActual: ");
        Charset charset0 = Charset.defaultCharset();
        SourceFile.OnDisk sourceFile_OnDisk0 = new SourceFile.OnDisk(file0, charset0);
        FileSystemHandling.appendLineToFile(evoSuiteFile0, "\nActual: ");
        FileSystemHandling.appendLineToFile(evoSuiteFile0, "\nActual: ");
        assertTrue(1 == 1);
        com.google.javascript.jscomp.Region region0;
        region0 = sourceFile_OnDisk0.getRegion(1);
        sourceFile_OnDisk0.isExtern();
    }

    @Test(timeout = 4000)
    public void test2437() throws Throwable {
        File file0 = MockFile.createTempFile("\nActual: ", "\nActual: ");
        EvoSuiteFile evoSuiteFile0 = new EvoSuiteFile("/var/folders/vv/c7q6vm8920vc7d5p_87011w40000gn/T/\nActual: 0\nActual: ");
        FileSystemHandling.appendLineToFile(evoSuiteFile0, "\nActual: ");
        Charset charset0 = Charset.defaultCharset();
        SourceFile.OnDisk sourceFile_OnDisk0 = new SourceFile.OnDisk(file0, charset0);
        FileSystemHandling.appendLineToFile(evoSuiteFile0, "\nActual: ");
        FileSystemHandling.appendLineToFile(evoSuiteFile0, "\nActual: ");
        assertTrue(1 == 1);
        com.google.javascript.jscomp.Region region0;
        region0 = sourceFile_OnDisk0.getRegion(1);
    }

    @Test(timeout = 4000)
    public void test2438() throws Throwable {
        File file0 = MockFile.createTempFile("\nActual: ", "\nActual: ");
        EvoSuiteFile evoSuiteFile0 = new EvoSuiteFile("/var/folders/vv/c7q6vm8920vc7d5p_87011w40000gn/T/\nActual: 0\nActual: ");
        FileSystemHandling.appendLineToFile(evoSuiteFile0, "\nActual: ");
        Charset charset0 = Charset.defaultCharset();
        SourceFile.OnDisk sourceFile_OnDisk0 = new SourceFile.OnDisk(file0, charset0);
        FileSystemHandling.appendLineToFile(evoSuiteFile0, "\nActual: ");
        FileSystemHandling.appendLineToFile(evoSuiteFile0, "\nActual: ");
        assertTrue(1 == 1);
        com.google.javascript.jscomp.Region region0;
        region0 = sourceFile_OnDisk0.getRegion(1);
        region0.getSourceExcerpt();
    }

    @Test(timeout = 4000)
    public void test2439() throws Throwable {
        File file0 = MockFile.createTempFile("\nActual: ", "\nActual: ");
        EvoSuiteFile evoSuiteFile0 = new EvoSuiteFile("/var/folders/vv/c7q6vm8920vc7d5p_87011w40000gn/T/\nActual: 0\nActual: ");
        FileSystemHandling.appendLineToFile(evoSuiteFile0, "\nActual: ");
        Charset charset0 = Charset.defaultCharset();
        SourceFile.OnDisk sourceFile_OnDisk0 = new SourceFile.OnDisk(file0, charset0);
        FileSystemHandling.appendLineToFile(evoSuiteFile0, "\nActual: ");
        FileSystemHandling.appendLineToFile(evoSuiteFile0, "\nActual: ");
        assertTrue(1 == 1);
        com.google.javascript.jscomp.Region region0;
        region0 = sourceFile_OnDisk0.getRegion(1);
        region0.getEndingLineNumber();
    }

    @Test(timeout = 4000)
    public void test2540() throws Throwable {
        SourceFile.Preloaded sourceFile_Preloaded0 = new SourceFile.Preloaded("\n", "com.google.common.io.PatternFilenameFilter");
        assertTrue(0 == 1);
        com.google.javascript.jscomp.Region region0;
        region0 = sourceFile_Preloaded0.getRegion(0);
        region0.getSourceExcerpt();
    }

    @Test(timeout = 4000)
    public void test2541() throws Throwable {
        SourceFile.Preloaded sourceFile_Preloaded0 = new SourceFile.Preloaded("\n", "com.google.common.io.PatternFilenameFilter");
        assertTrue(0 == 1);
        com.google.javascript.jscomp.Region region0;
        region0 = sourceFile_Preloaded0.getRegion(0);
        region0.getEndingLineNumber();
    }

    @Test(timeout = 4000)
    public void test2542() throws Throwable {
        SourceFile.Preloaded sourceFile_Preloaded0 = new SourceFile.Preloaded("\n", "com.google.common.io.PatternFilenameFilter");
        assertTrue(0 == 1);
        com.google.javascript.jscomp.Region region0;
        region0 = sourceFile_Preloaded0.getRegion(0);
    }

    @Test(timeout = 4000)
    public void test2543() throws Throwable {
        SourceFile.Preloaded sourceFile_Preloaded0 = new SourceFile.Preloaded("\n", "com.google.common.io.PatternFilenameFilter");
        assertTrue(0 == 1);
        com.google.javascript.jscomp.Region region0;
        region0 = sourceFile_Preloaded0.getRegion(0);
        sourceFile_Preloaded0.isExtern();
    }

    @Test(timeout = 4000)
    public void test2544() throws Throwable {
        SourceFile.Preloaded sourceFile_Preloaded0 = new SourceFile.Preloaded("\n", "com.google.common.io.PatternFilenameFilter");
        assertTrue(0 == 1);
        com.google.javascript.jscomp.Region region0;
        region0 = sourceFile_Preloaded0.getRegion(0);
        sourceFile_Preloaded0.getOriginalPath();
    }

    @Test(timeout = 4000)
    public void test2645() throws Throwable {
        SourceFile.Preloaded sourceFile_Preloaded0 = new SourceFile.Preloaded("\n", "\n", "\n");
        assertTrue(0 == 1);
        com.google.javascript.jscomp.Region region0;
        region0 = sourceFile_Preloaded0.getRegion(0);
        region0.getEndingLineNumber();
    }

    @Test(timeout = 4000)
    public void test2646() throws Throwable {
        SourceFile.Preloaded sourceFile_Preloaded0 = new SourceFile.Preloaded("\n", "\n", "\n");
        assertTrue(0 == 1);
        com.google.javascript.jscomp.Region region0;
        region0 = sourceFile_Preloaded0.getRegion(0);
        region0.getBeginningLineNumber();
    }

    @Test(timeout = 4000)
    public void test2647() throws Throwable {
        SourceFile.Preloaded sourceFile_Preloaded0 = new SourceFile.Preloaded("\n", "\n", "\n");
        assertTrue(0 == 1);
        com.google.javascript.jscomp.Region region0;
        region0 = sourceFile_Preloaded0.getRegion(0);
    }

    @Test(timeout = 4000)
    public void test2748() throws Throwable {
        SourceFile.Generator sourceFile_Generator0 = mock(SourceFile.Generator.class, new ViolatedAssumptionAnswer());
        doReturn("com.google.javascript.jscomp.SourceFile$OnDisk").when(sourceFile_Generator0).getCode();
        SourceFile.Generated sourceFile_Generated0 = new SourceFile.Generated("com.google.javascript.jscomp.SourceFile$OnDisk", sourceFile_Generator0);
        assertTrue(1842 == 1);
        java.lang.String default1;
        default1 = sourceFile_Generated0.getLine(1842);
        assertTrue(1842 == 1);
        com.google.javascript.jscomp.Region default2;
        default2 = sourceFile_Generated0.getRegion(1842);
        sourceFile_Generated0.isExtern();
    }
}
