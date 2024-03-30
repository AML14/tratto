/*
 * This file was automatically generated by EvoSuite
 * Mon Oct 16 01:48:18 GMT 2023
 */
package org.apache.commons.lang3;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.io.File;
import org.apache.commons.lang3.SystemUtils;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.testdata.EvoSuiteFile;
import org.evosuite.runtime.testdata.FileSystemHandling;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class SystemUtils_ESTest extends SystemUtils_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        float float0 = SystemUtils.toJavaVersionInt("/Users/elliottzackrone/.sdkman/candidates/java/8.0.382-amzn/jre/lib/endorsed");
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        float float0 = SystemUtils.toJavaVersionInt("1.6");
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        boolean boolean0 = SystemUtils.isJavaVersionAtLeast((-648));
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        boolean boolean0 = SystemUtils.isJavaVersionAtLeast(1.8F);
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        int[] intArray0 = SystemUtils.toJavaVersionIntArray(".");
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        boolean boolean0 = SystemUtils.isOSNameMatch("1.2", "1.2");
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        EvoSuiteFile evoSuiteFile0 = new EvoSuiteFile("/Users/elliottzackrone");
        FileSystemHandling.setPermissions(evoSuiteFile0, false, false, false);
        File file0 = SystemUtils.getUserHome();
        file0.isDirectory();
    }

    @Test(timeout = 4000)
    public void test077() throws Throwable {
        EvoSuiteFile evoSuiteFile0 = new EvoSuiteFile("/Users/elliottzackrone/IdeaProjects/defects4jprefix");
        FileSystemHandling.setPermissions(evoSuiteFile0, true, false, false);
        File file0 = SystemUtils.getUserDir();
        file0.canRead();
    }

    @Test(timeout = 4000)
    public void test088() throws Throwable {
        EvoSuiteFile evoSuiteFile0 = new EvoSuiteFile("/var/folders/vv/c7q6vm8920vc7d5p_87011w40000gn/T");
        FileSystemHandling.setPermissions(evoSuiteFile0, false, false, false);
        File file0 = SystemUtils.getJavaIoTmpDir();
        file0.lastModified();
    }

    @Test(timeout = 4000)
    public void test099() throws Throwable {
        EvoSuiteFile evoSuiteFile0 = new EvoSuiteFile("/Users/elliottzackrone/.sdkman/candidates/java/8.0.382-amzn/jre");
        FileSystemHandling.appendLineToFile(evoSuiteFile0, "Mac OS XMac OS X");
        File file0 = SystemUtils.getJavaHome();
        file0.toString();
    }

    @Test(timeout = 4000)
    public void test1010() throws Throwable {
        EvoSuiteFile evoSuiteFile0 = new EvoSuiteFile("/Users/elliottzackrone/.sdkman/candidates/java/8.0.382-amzn/jre");
        FileSystemHandling.createFolder(evoSuiteFile0);
        File file0 = SystemUtils.getJavaHome();
        file0.getParent();
    }

    @Test(timeout = 4000)
    public void test1111() throws Throwable {
        SystemUtils.toJavaVersionIntArray("/var/folders/vv/c7q6vm8920vc7d5p_87011w40000gn/T/EvoSuite_pathingJar7249637649498281454.jar");
    }

    @Test(timeout = 4000)
    public void test1212() throws Throwable {
        SystemUtils.isOSNameMatch("Mac OS ", (String) null);
    }

    @Test(timeout = 4000)
    public void test1313() throws Throwable {
        SystemUtils.isOSMatch(".", ".", (String) null, "Array and element cannot both be null");
    }

    @Test(timeout = 4000)
    public void test1414() throws Throwable {
        SystemUtils.isJavaVersionMatch("OpenJDK Runtime Environment", (String) null);
    }

    @Test(timeout = 4000)
    public void test1515() throws Throwable {
        float float0 = SystemUtils.toJavaVersionInt("");
    }

    @Test(timeout = 4000)
    public void test1616() throws Throwable {
        float float0 = SystemUtils.toJavaVersionFloat("3D");
    }

    @Test(timeout = 4000)
    public void test1717() throws Throwable {
        float float0 = SystemUtils.toJavaVersionFloat("/var/folders/vv/c7q6vm8920vc7d5p_87011w40000gn/T/EvoSuite_pathingJar7249637649498281454.jar");
    }

    @Test(timeout = 4000)
    public void test1818() throws Throwable {
        float float0 = SystemUtils.toJavaVersionInt((String) null);
    }

    @Test(timeout = 4000)
    public void test1919() throws Throwable {
        boolean boolean0 = SystemUtils.isOSNameMatch((String) null, "Arguments cannot both be null");
    }

    @Test(timeout = 4000)
    public void test2020() throws Throwable {
        boolean boolean0 = SystemUtils.isOSMatch("1.7", "1.6America/Los_Angeles", "1.7", "1.7");
    }

    @Test(timeout = 4000)
    public void test2121() throws Throwable {
        boolean boolean0 = SystemUtils.isOSMatch("1.6America/Los_Angeles", "1.6America/Los_Angeles", "1.6America/Los_Angeles", "1.6America/Los_Angeles");
    }

    @Test(timeout = 4000)
    public void test2222() throws Throwable {
        boolean boolean0 = SystemUtils.isOSMatch("Oracle Corporation", (String) null, "Mac OS X", "Amazon.com Inc.");
    }

    @Test(timeout = 4000)
    public void test2323() throws Throwable {
        boolean boolean0 = SystemUtils.isOSMatch("aP?Ft1ZucjjT*w+\"W", "%R8Cs", ">CNg", ">CNg");
    }

    @Test(timeout = 4000)
    public void test2424() throws Throwable {
        boolean boolean0 = SystemUtils.isOSMatch((String) null, "Windows", (String) null, "Windows");
    }

    @Test(timeout = 4000)
    public void test2525() throws Throwable {
        boolean boolean0 = SystemUtils.isJavaVersionMatch((String) null, (String) null);
    }

    @Test(timeout = 4000)
    public void test2626() throws Throwable {
        boolean boolean0 = SystemUtils.isJavaVersionMatch("Windows", "Windows");
    }

    @Test(timeout = 4000)
    public void test2727() throws Throwable {
        boolean boolean0 = SystemUtils.isJavaVersionAtLeast(180);
    }

    @Test(timeout = 4000)
    public void test2828() throws Throwable {
        boolean boolean0 = SystemUtils.isJavaVersionAtLeast(1404);
    }

    @Test(timeout = 4000)
    public void test2929() throws Throwable {
        boolean boolean0 = SystemUtils.isJavaVersionAtLeast(0.0F);
    }

    @Test(timeout = 4000)
    public void test3030() throws Throwable {
        boolean boolean0 = SystemUtils.isJavaVersionAtLeast(3.0F);
    }

    @Test(timeout = 4000)
    public void test3131() throws Throwable {
        boolean boolean0 = SystemUtils.isJavaAwtHeadless();
    }

    @Test(timeout = 4000)
    public void test3232() throws Throwable {
        SystemUtils systemUtils0 = new SystemUtils();
    }

    @Test(timeout = 4000)
    public void test3333() throws Throwable {
        File file0 = SystemUtils.getUserHome();
        file0.lastModified();
    }

    @Test(timeout = 4000)
    public void test3434() throws Throwable {
        File file0 = SystemUtils.getUserDir();
        file0.isFile();
    }

    @Test(timeout = 4000)
    public void test3535() throws Throwable {
        File file0 = SystemUtils.getJavaHome();
        file0.canExecute();
    }

    @Test(timeout = 4000)
    public void test3636() throws Throwable {
        File file0 = SystemUtils.getJavaIoTmpDir();
        file0.exists();
    }

    @Test(timeout = 4000)
    public void test3737() throws Throwable {
        int[] intArray0 = SystemUtils.toJavaVersionIntArray("1.8.0_382");
    }

    @Test(timeout = 4000)
    public void test3738() throws Throwable {
        int[] intArray0 = SystemUtils.toJavaVersionIntArray("1.8.0_382");
    }

    @Test(timeout = 4000)
    public void test3839() throws Throwable {
        float float0 = SystemUtils.toJavaVersionFloat("_");
    }
}
