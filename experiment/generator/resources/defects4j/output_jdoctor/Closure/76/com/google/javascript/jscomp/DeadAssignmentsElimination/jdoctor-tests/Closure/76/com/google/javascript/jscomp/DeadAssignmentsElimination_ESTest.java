/*
 * This file was automatically generated by EvoSuite
 * Sat Nov 04 03:43:58 GMT 2023
 */
package com.google.javascript.jscomp;

import org.junit.Test;
import static org.junit.Assert.*;
import com.google.javascript.jscomp.Compiler;
import com.google.javascript.jscomp.DeadAssignmentsElimination;
import com.google.javascript.rhino.Node;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class DeadAssignmentsElimination_ESTest extends DeadAssignmentsElimination_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test00() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.parseTestCode("var JSCompiler_stubMap = [];function JSCompiler_stubMethod(JSCompiler_stubMethod_id) {  return function() {    return JSCompiler_stubMap[JSCompiler_stubMethod_id].apply(        this, arguments);  };}function JSCompiler_unstubMethod(    JSCompiler_unstubMethod_id, JSCompiler_unstubMethod_body) {  return JSCompiler_st=bMap[JSCompiler_unstubMetho_id] =       JSCompiler_unstubMehod_body;}");
        DeadAssignmentsElimination deadAssignmentsElimination0 = new DeadAssignmentsElimination(compiler0);
        deadAssignmentsElimination0.process(node0, node0);
    }

    @Test(timeout = 4000)
    public void test11() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.parseTestCode("var JSCompiler_stubMap = [];function JSCompiler_stubMethod(JSCompiler_stubMethod_id) {  return function() {    return JSCompiler_stubMap[JSCompiler_stubMethod_id].apply(        this, arguments);  };}function JSCompiler_unstubMethod(    JSCompiler_unstubMethod_id, JSCompiler_unstubMethod_body) {  return JSCompiler_stubMap[JSCompiler_unstubMethod_id] =       JSCompiler_unstubMethod_body;}");
        DeadAssignmentsElimination deadAssignmentsElimination0 = new DeadAssignmentsElimination(compiler0);
        deadAssignmentsElimination0.process(node0, node0);
        node0.hasChildren();
    }
}
