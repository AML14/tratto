/**
 * Scaffolding file used to store all the setups needed to run 
 * tests automatically generated by EvoSuite
 * Sun Oct 15 19:04:01 GMT 2023
 */

package com.fasterxml.jackson.core.util;

import org.evosuite.runtime.annotation.EvoSuiteClassExclude;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.After;
import org.junit.AfterClass;
import org.evosuite.runtime.sandbox.Sandbox;
import org.evosuite.runtime.sandbox.Sandbox.SandboxMode;

import static org.evosuite.shaded.org.mockito.Mockito.*;
@EvoSuiteClassExclude
public class JsonParserSequence_ESTest_scaffolding {

  @org.junit.Rule 
  public org.evosuite.runtime.vnet.NonFunctionalRequirementRule nfr = new org.evosuite.runtime.vnet.NonFunctionalRequirementRule();

  private static final java.util.Properties defaultProperties = (java.util.Properties) java.lang.System.getProperties().clone(); 

  private org.evosuite.runtime.thread.ThreadStopper threadStopper =  new org.evosuite.runtime.thread.ThreadStopper (org.evosuite.runtime.thread.KillSwitchHandler.getInstance(), 3000);


  @BeforeClass 
  public static void initEvoSuiteFramework() { 
    org.evosuite.runtime.RuntimeSettings.className = "com.fasterxml.jackson.core.util.JsonParserSequence"; 
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
    try { initMocksToAvoidTimeoutsInTheTests(); } catch(ClassNotFoundException e) {} 
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
    java.lang.System.setProperty("file.encoding", "UTF-8"); 
    java.lang.System.setProperty("java.awt.headless", "true"); 
    java.lang.System.setProperty("java.io.tmpdir", "/var/folders/vv/c7q6vm8920vc7d5p_87011w40000gn/T/"); 
    java.lang.System.setProperty("user.country", "US"); 
    java.lang.System.setProperty("user.dir", "/Users/elliottzackrone/IdeaProjects/defects4jprefix"); 
    java.lang.System.setProperty("user.home", "/Users/elliottzackrone"); 
    java.lang.System.setProperty("user.language", "en"); 
    java.lang.System.setProperty("user.name", "elliottzackrone"); 
    java.lang.System.setProperty("user.timezone", "America/Los_Angeles"); 
  }

  private static void initializeClasses() {
    org.evosuite.runtime.classhandling.ClassStateSupport.initializeClasses(JsonParserSequence_ESTest_scaffolding.class.getClassLoader() ,
      "com.fasterxml.jackson.core.util.JsonParserSequence",
      "com.fasterxml.jackson.core.base.GeneratorBase",
      "com.fasterxml.jackson.core.filter.TokenFilter",
      "com.fasterxml.jackson.core.JsonParser$NumberType",
      "com.fasterxml.jackson.core.io.CharTypes",
      "com.fasterxml.jackson.core.json.WriterBasedJsonGenerator",
      "com.fasterxml.jackson.core.filter.TokenFilterContext",
      "com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer",
      "com.fasterxml.jackson.core.SerializableString",
      "com.fasterxml.jackson.core.Versioned",
      "com.fasterxml.jackson.core.util.RequestPayload",
      "com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer$TableInfo",
      "com.fasterxml.jackson.core.json.DupDetector",
      "com.fasterxml.jackson.core.type.TypeReference",
      "com.fasterxml.jackson.core.JsonParseException",
      "com.fasterxml.jackson.core.json.JsonWriteContext",
      "com.fasterxml.jackson.core.util.JsonParserDelegate",
      "com.fasterxml.jackson.core.io.UTF8Writer",
      "com.fasterxml.jackson.core.Version",
      "com.fasterxml.jackson.core.util.VersionUtil",
      "com.fasterxml.jackson.core.format.InputAccessor",
      "com.fasterxml.jackson.core.util.Instantiatable",
      "com.fasterxml.jackson.core.Base64Variant",
      "com.fasterxml.jackson.core.io.InputDecorator",
      "com.fasterxml.jackson.core.filter.FilteringParserDelegate",
      "com.fasterxml.jackson.core.base.ParserBase",
      "com.fasterxml.jackson.core.io.OutputDecorator",
      "com.fasterxml.jackson.core.json.PackageVersion",
      "com.fasterxml.jackson.core.json.JsonGeneratorImpl",
      "com.fasterxml.jackson.core.json.UTF8DataInputJsonParser",
      "com.fasterxml.jackson.core.io.JsonEOFException",
      "com.fasterxml.jackson.core.json.ReaderBasedJsonParser",
      "com.fasterxml.jackson.core.JsonLocation",
      "com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer",
      "com.fasterxml.jackson.core.util.DefaultPrettyPrinter$Indenter",
      "com.fasterxml.jackson.core.JsonGenerator",
      "com.fasterxml.jackson.core.io.CharacterEscapes",
      "com.fasterxml.jackson.core.ObjectCodec",
      "com.fasterxml.jackson.core.json.JsonReadContext",
      "com.fasterxml.jackson.core.JsonFactory$Feature",
      "com.fasterxml.jackson.core.type.ResolvedType",
      "com.fasterxml.jackson.core.util.InternCache",
      "com.fasterxml.jackson.core.JsonParser$Feature",
      "com.fasterxml.jackson.core.JsonPointer",
      "com.fasterxml.jackson.core.io.DataOutputAsStream",
      "com.fasterxml.jackson.core.io.SerializedString",
      "com.fasterxml.jackson.core.JsonEncoding",
      "com.fasterxml.jackson.core.util.DefaultPrettyPrinter",
      "com.fasterxml.jackson.core.JsonGenerationException",
      "com.fasterxml.jackson.core.JsonToken",
      "com.fasterxml.jackson.core.util.BufferRecycler",
      "com.fasterxml.jackson.core.TreeNode",
      "com.fasterxml.jackson.core.TreeCodec",
      "com.fasterxml.jackson.core.JsonParser",
      "com.fasterxml.jackson.core.JsonGenerator$Feature",
      "com.fasterxml.jackson.core.json.UTF8JsonGenerator",
      "com.fasterxml.jackson.core.JsonProcessingException",
      "com.fasterxml.jackson.core.JsonStreamContext",
      "com.fasterxml.jackson.core.util.TextBuffer",
      "com.fasterxml.jackson.core.format.MatchStrength",
      "com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer$Bucket",
      "com.fasterxml.jackson.core.Base64Variants",
      "com.fasterxml.jackson.core.JsonFactory",
      "com.fasterxml.jackson.core.base.ParserMinimalBase",
      "com.fasterxml.jackson.core.util.ByteArrayBuilder",
      "com.fasterxml.jackson.core.json.UTF8StreamJsonParser",
      "com.fasterxml.jackson.core.io.IOContext",
      "com.fasterxml.jackson.core.FormatSchema",
      "com.fasterxml.jackson.core.PrettyPrinter"
    );
  } 
  private static void initMocksToAvoidTimeoutsInTheTests() throws ClassNotFoundException { 
    mock(Class.forName("com.fasterxml.jackson.core.ObjectCodec", false, JsonParserSequence_ESTest_scaffolding.class.getClassLoader()));
    mock(Class.forName("java.util.Enumeration", false, JsonParserSequence_ESTest_scaffolding.class.getClassLoader()));
  }

  private static void resetClasses() {
    org.evosuite.runtime.classhandling.ClassResetter.getInstance().setClassLoader(JsonParserSequence_ESTest_scaffolding.class.getClassLoader()); 

    org.evosuite.runtime.classhandling.ClassStateSupport.resetClasses(
      "com.fasterxml.jackson.core.JsonParser",
      "com.fasterxml.jackson.core.util.JsonParserDelegate",
      "com.fasterxml.jackson.core.util.JsonParserSequence",
      "com.fasterxml.jackson.core.util.BufferRecycler",
      "com.fasterxml.jackson.core.io.IOContext",
      "com.fasterxml.jackson.core.base.ParserMinimalBase",
      "com.fasterxml.jackson.core.base.ParserBase",
      "com.fasterxml.jackson.core.io.CharTypes",
      "com.fasterxml.jackson.core.json.ReaderBasedJsonParser",
      "com.fasterxml.jackson.core.util.TextBuffer",
      "com.fasterxml.jackson.core.JsonStreamContext",
      "com.fasterxml.jackson.core.json.JsonReadContext",
      "com.fasterxml.jackson.core.TreeCodec",
      "com.fasterxml.jackson.core.ObjectCodec",
      "com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer",
      "com.fasterxml.jackson.core.JsonFactory$Feature",
      "com.fasterxml.jackson.core.json.DupDetector",
      "com.fasterxml.jackson.core.filter.TokenFilter",
      "com.fasterxml.jackson.core.filter.FilteringParserDelegate",
      "com.fasterxml.jackson.core.filter.TokenFilterContext",
      "com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer",
      "com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer$TableInfo",
      "com.fasterxml.jackson.core.json.UTF8DataInputJsonParser",
      "com.fasterxml.jackson.core.json.UTF8StreamJsonParser",
      "com.fasterxml.jackson.core.type.TypeReference",
      "com.fasterxml.jackson.core.Base64Variant",
      "com.fasterxml.jackson.core.util.RequestPayload",
      "com.fasterxml.jackson.core.JsonProcessingException",
      "com.fasterxml.jackson.core.JsonParseException",
      "com.fasterxml.jackson.core.JsonLocation",
      "com.fasterxml.jackson.core.util.ByteArrayBuilder",
      "com.fasterxml.jackson.core.io.SerializedString",
      "com.fasterxml.jackson.core.util.InternCache",
      "com.fasterxml.jackson.core.Base64Variants",
      "com.fasterxml.jackson.core.util.VersionUtil",
      "com.fasterxml.jackson.core.Version",
      "com.fasterxml.jackson.core.json.PackageVersion",
      "com.fasterxml.jackson.core.io.JsonEOFException",
      "com.fasterxml.jackson.core.JsonToken"
    );
  }
}
