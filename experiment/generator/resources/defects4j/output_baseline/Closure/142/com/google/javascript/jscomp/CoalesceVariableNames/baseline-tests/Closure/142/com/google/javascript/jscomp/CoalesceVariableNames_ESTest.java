/*
 * This file was automatically generated by EvoSuite
 * Sat Nov 04 06:53:12 GMT 2023
 */
package com.google.javascript.jscomp;

import org.junit.Test;
import static org.junit.Assert.*;
import com.google.javascript.jscomp.CoalesceVariableNames;
import com.google.javascript.jscomp.Compiler;
import com.google.javascript.rhino.Node;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class CoalesceVariableNames_ESTest extends CoalesceVariableNames_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test00() throws Throwable {
        Compiler compiler0 = new Compiler();
        CoalesceVariableNames coalesceVariableNames0 = new CoalesceVariableNames(compiler0, true);
        Node node0 = compiler0.parseSyntheticCode("", "");
        coalesceVariableNames0.process(node0, node0);
        node0.hasMoreThanOneChild();
    }
}
