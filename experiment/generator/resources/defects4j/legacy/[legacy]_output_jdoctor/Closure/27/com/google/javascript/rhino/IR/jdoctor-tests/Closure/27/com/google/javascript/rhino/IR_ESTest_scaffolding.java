/**
 * Scaffolding file used to store all the setups needed to run 
 * tests automatically generated by EvoSuite
 * Fri Nov 03 23:07:18 GMT 2023
 */

package com.google.javascript.rhino;

import org.evosuite.runtime.annotation.EvoSuiteClassExclude;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.After;
import org.junit.AfterClass;
import org.evosuite.runtime.sandbox.Sandbox;
import org.evosuite.runtime.sandbox.Sandbox.SandboxMode;

@EvoSuiteClassExclude
public class IR_ESTest_scaffolding {

  @org.junit.Rule 
  public org.evosuite.runtime.vnet.NonFunctionalRequirementRule nfr = new org.evosuite.runtime.vnet.NonFunctionalRequirementRule();

  private static final java.util.Properties defaultProperties = (java.util.Properties) java.lang.System.getProperties().clone(); 

  private org.evosuite.runtime.thread.ThreadStopper threadStopper =  new org.evosuite.runtime.thread.ThreadStopper (org.evosuite.runtime.thread.KillSwitchHandler.getInstance(), 3000);


  @BeforeClass 
  public static void initEvoSuiteFramework() { 
    org.evosuite.runtime.RuntimeSettings.className = "com.google.javascript.rhino.IR"; 
    org.evosuite.runtime.GuiSupport.initialize(); 
    org.evosuite.runtime.RuntimeSettings.maxNumberOfThreads = 100; 
    org.evosuite.runtime.RuntimeSettings.maxNumberOfIterationsPerLoop = 10000; 
    org.evosuite.runtime.RuntimeSettings.mockSystemIn = true; 
    org.evosuite.runtime.RuntimeSettings.sandboxMode = org.evosuite.runtime.sandbox.Sandbox.SandboxMode.RECOMMENDED; 
    org.evosuite.runtime.sandbox.Sandbox.initializeSecurityManagerForSUT(); 
    org.evosuite.runtime.classhandling.JDKClassResetter.init();
    setSystemProperties();
    initializeClasses();
    org.evosuite.runtime.Runtime.getInstance().resetRuntime(); 
  } 

  @AfterClass 
  public static void clearEvoSuiteFramework(){ 
    Sandbox.resetDefaultSecurityManager(); 
    java.lang.System.setProperties((java.util.Properties) defaultProperties.clone()); 
  } 

  @Before 
  public void initTestCase(){ 
    threadStopper.storeCurrentThreads();
    threadStopper.startRecordingTime();
    org.evosuite.runtime.jvm.ShutdownHookHandler.getInstance().initHandler(); 
    org.evosuite.runtime.sandbox.Sandbox.goingToExecuteSUTCode(); 
    setSystemProperties(); 
    org.evosuite.runtime.GuiSupport.setHeadless(); 
    org.evosuite.runtime.Runtime.getInstance().resetRuntime(); 
    org.evosuite.runtime.agent.InstrumentingAgent.activate(); 
  } 

  @After 
  public void doneWithTestCase(){ 
    threadStopper.killAndJoinClientThreads();
    org.evosuite.runtime.jvm.ShutdownHookHandler.getInstance().safeExecuteAddedHooks(); 
    org.evosuite.runtime.classhandling.JDKClassResetter.reset(); 
    resetClasses(); 
    org.evosuite.runtime.sandbox.Sandbox.doneWithExecutingSUTCode(); 
    org.evosuite.runtime.agent.InstrumentingAgent.deactivate(); 
    org.evosuite.runtime.GuiSupport.restoreHeadlessMode(); 
  } 

  public static void setSystemProperties() {
 
    java.lang.System.setProperties((java.util.Properties) defaultProperties.clone()); 
    java.lang.System.setProperty("user.dir", "/Users/elliottzackrone/IdeaProjects/defects4jprefix"); 
    java.lang.System.setProperty("java.io.tmpdir", "/var/folders/vv/c7q6vm8920vc7d5p_87011w40000gn/T/"); 
  }

  private static void initializeClasses() {
    org.evosuite.runtime.classhandling.ClassStateSupport.initializeClasses(IR_ESTest_scaffolding.class.getClassLoader() ,
      "com.google.javascript.rhino.Node$SiblingNodeIterable",
      "com.google.javascript.rhino.jstype.JSType",
      "com.google.javascript.rhino.Token",
      "com.google.javascript.rhino.Node$FileLevelJsDocBuilder",
      "com.google.javascript.rhino.Node$StringNode",
      "com.google.javascript.rhino.jstype.StaticSourceFile",
      "com.google.javascript.rhino.Node$NodeMismatch",
      "com.google.javascript.rhino.Node$AncestorIterable",
      "com.google.javascript.rhino.InputId",
      "com.google.javascript.rhino.Node$ObjectPropListItem",
      "com.google.javascript.rhino.Node$SideEffectFlags",
      "com.google.javascript.rhino.IR",
      "com.google.javascript.rhino.Node$AbstractPropListItem",
      "com.google.javascript.rhino.Node$PropListItem",
      "com.google.common.base.Preconditions",
      "com.google.javascript.rhino.JSDocInfo",
      "com.google.javascript.rhino.Node$IntPropListItem",
      "com.google.javascript.rhino.Node$NumberNode",
      "com.google.javascript.rhino.Node"
    );
  } 

  private static void resetClasses() {
    org.evosuite.runtime.classhandling.ClassResetter.getInstance().setClassLoader(IR_ESTest_scaffolding.class.getClassLoader()); 

    org.evosuite.runtime.classhandling.ClassStateSupport.resetClasses(
      "com.google.javascript.rhino.IR",
      "com.google.javascript.rhino.Node",
      "com.google.javascript.rhino.Node$NumberNode",
      "com.google.common.base.Preconditions",
      "com.google.javascript.rhino.Node$StringNode",
      "com.google.javascript.rhino.JSDocInfo",
      "com.google.javascript.rhino.jstype.JSTypeRegistry",
      "com.google.common.base.Joiner",
      "com.google.common.collect.Collections2",
      "com.google.common.base.Joiner$MapJoiner",
      "com.google.common.collect.Maps",
      "com.google.common.collect.AbstractMultimap",
      "com.google.common.collect.AbstractSetMultimap",
      "com.google.common.collect.LinkedHashMultimap",
      "com.google.common.collect.Sets",
      "com.google.common.collect.AbstractListMultimap",
      "com.google.common.collect.ArrayListMultimap",
      "com.google.javascript.rhino.jstype.JSTypeRegistry$ResolveMode",
      "com.google.javascript.rhino.jstype.JSType$1",
      "com.google.javascript.rhino.jstype.JSType",
      "com.google.javascript.rhino.jstype.ValueType",
      "com.google.javascript.rhino.jstype.BooleanType",
      "com.google.javascript.rhino.jstype.NullType",
      "com.google.javascript.rhino.jstype.NumberType",
      "com.google.javascript.rhino.jstype.StringType",
      "com.google.javascript.rhino.jstype.ObjectType",
      "com.google.javascript.rhino.jstype.UnknownType",
      "com.google.javascript.rhino.jstype.VoidType",
      "com.google.javascript.rhino.jstype.AllType",
      "com.google.javascript.rhino.jstype.PrototypeObjectType",
      "com.google.javascript.rhino.jstype.FunctionType",
      "com.google.javascript.rhino.jstype.FunctionParamBuilder",
      "com.google.javascript.rhino.Node$AbstractPropListItem",
      "com.google.javascript.rhino.Node$IntPropListItem",
      "com.google.javascript.rhino.jstype.ArrowType",
      "com.google.common.collect.ImmutableCollection$EmptyImmutableCollection",
      "com.google.common.collect.ImmutableCollection",
      "com.google.common.collect.ImmutableList",
      "com.google.common.collect.UnmodifiableIterator",
      "com.google.common.collect.UnmodifiableListIterator",
      "com.google.common.collect.EmptyImmutableList$1",
      "com.google.common.collect.EmptyImmutableList",
      "com.google.javascript.rhino.jstype.FunctionType$Kind",
      "com.google.javascript.rhino.jstype.InstanceObjectType",
      "com.google.javascript.rhino.jstype.ObjectType$Property",
      "com.google.common.collect.Lists",
      "com.google.javascript.rhino.jstype.NoObjectType",
      "com.google.javascript.rhino.jstype.NoType",
      "com.google.javascript.rhino.jstype.NoResolvedType",
      "com.google.javascript.rhino.jstype.ErrorFunctionType",
      "com.google.javascript.rhino.jstype.UnionTypeBuilder",
      "com.google.common.collect.Iterators$1",
      "com.google.common.collect.Iterators$2",
      "com.google.common.collect.Iterators",
      "com.google.javascript.rhino.jstype.UnionType",
      "com.google.common.collect.RegularImmutableList",
      "com.google.javascript.rhino.jstype.FunctionBuilder",
      "com.google.javascript.rhino.jstype.JSTypeRegistry$1",
      "com.google.javascript.rhino.Node$AncestorIterable",
      "com.google.javascript.rhino.Token",
      "com.google.javascript.rhino.SimpleErrorReporter",
      "com.google.javascript.rhino.jstype.EnumType",
      "com.google.javascript.rhino.jstype.EnumElementType",
      "com.google.javascript.rhino.Node$FileLevelJsDocBuilder",
      "com.google.javascript.rhino.Node$ObjectPropListItem",
      "com.google.javascript.rhino.Node$SiblingNodeIterable",
      "com.google.javascript.rhino.jstype.SimpleSourceFile",
      "com.google.javascript.rhino.InputId",
      "com.google.javascript.rhino.Node$SideEffectFlags",
      "com.google.javascript.rhino.JSDocInfo$LazilyInitializedInfo",
      "com.google.javascript.rhino.Node$NodeMismatch",
      "com.google.javascript.rhino.jstype.ProxyObjectType",
      "com.google.javascript.rhino.jstype.NamedType",
      "com.google.common.collect.ImmutableSet",
      "com.google.common.collect.EmptyImmutableSet",
      "com.google.javascript.rhino.jstype.TemplateType",
      "com.google.javascript.rhino.JSTypeExpression",
      "com.google.common.collect.AbstractIndexedListIterator",
      "com.google.common.collect.Iterators$12",
      "com.google.common.collect.Multimaps$Values",
      "com.google.common.collect.AbstractMultimap$2",
      "com.google.common.collect.Multimaps$Entries",
      "com.google.common.collect.AbstractMultimap$4",
      "com.google.common.collect.AbstractMultimap$EntryIterator",
      "com.google.common.collect.Multimaps$Values$1",
      "com.google.javascript.rhino.jstype.TernaryValue",
      "com.google.javascript.rhino.jstype.JSType$2",
      "com.google.javascript.rhino.jstype.JSType$TypePair"
    );
  }
}
