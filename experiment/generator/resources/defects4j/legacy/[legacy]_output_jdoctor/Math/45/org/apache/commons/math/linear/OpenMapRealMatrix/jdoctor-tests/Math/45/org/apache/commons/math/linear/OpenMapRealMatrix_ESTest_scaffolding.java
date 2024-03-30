/**
 * Scaffolding file used to store all the setups needed to run 
 * tests automatically generated by EvoSuite
 * Mon Oct 16 15:11:17 GMT 2023
 */

package org.apache.commons.math.linear;

import org.evosuite.runtime.annotation.EvoSuiteClassExclude;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.After;
import org.junit.AfterClass;
import org.evosuite.runtime.sandbox.Sandbox;
import org.evosuite.runtime.sandbox.Sandbox.SandboxMode;

@EvoSuiteClassExclude
public class OpenMapRealMatrix_ESTest_scaffolding {

  @org.junit.Rule 
  public org.evosuite.runtime.vnet.NonFunctionalRequirementRule nfr = new org.evosuite.runtime.vnet.NonFunctionalRequirementRule();

  private static final java.util.Properties defaultProperties = (java.util.Properties) java.lang.System.getProperties().clone(); 

  private org.evosuite.runtime.thread.ThreadStopper threadStopper =  new org.evosuite.runtime.thread.ThreadStopper (org.evosuite.runtime.thread.KillSwitchHandler.getInstance(), 3000);


  @BeforeClass 
  public static void initEvoSuiteFramework() { 
    org.evosuite.runtime.RuntimeSettings.className = "org.apache.commons.math.linear.OpenMapRealMatrix"; 
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
    org.evosuite.runtime.classhandling.ClassStateSupport.initializeClasses(OpenMapRealMatrix_ESTest_scaffolding.class.getClassLoader() ,
      "org.apache.commons.math.exception.MathIllegalStateException",
      "org.apache.commons.math.linear.BlockFieldMatrix",
      "org.apache.commons.math.exception.NumberIsTooSmallException",
      "org.apache.commons.math.exception.NullArgumentException",
      "org.apache.commons.math.exception.util.ExceptionContext",
      "org.apache.commons.math.util.FastMath",
      "org.apache.commons.math.util.MathUtils",
      "org.apache.commons.math.util.OpenIntToDoubleHashMap$1",
      "org.apache.commons.math.linear.AbstractRealMatrix$5",
      "org.apache.commons.math.linear.AbstractRealMatrix$2",
      "org.apache.commons.math.linear.MatrixUtils",
      "org.apache.commons.math.Field",
      "org.apache.commons.math.exception.NonMonotonicSequenceException",
      "org.apache.commons.math.exception.NotStrictlyPositiveException",
      "org.apache.commons.math.util.OpenIntToDoubleHashMap",
      "org.apache.commons.math.exception.NotFiniteNumberException",
      "org.apache.commons.math.linear.FieldMatrixChangingVisitor",
      "org.apache.commons.math.analysis.UnivariateRealFunction",
      "org.apache.commons.math.linear.RealMatrix",
      "org.apache.commons.math.linear.DefaultRealMatrixChangingVisitor",
      "org.apache.commons.math.exception.NotPositiveException",
      "org.apache.commons.math.linear.MatrixDimensionMismatchException",
      "org.apache.commons.math.linear.RealLinearOperator",
      "org.apache.commons.math.exception.MathIllegalArgumentException",
      "org.apache.commons.math.util.OpenIntToDoubleHashMap$Iterator",
      "org.apache.commons.math.linear.AbstractFieldMatrix",
      "org.apache.commons.math.util.CompositeFormat",
      "org.apache.commons.math.exception.MultiDimensionMismatchException",
      "org.apache.commons.math.exception.MathParseException",
      "org.apache.commons.math.linear.SparseRealVector",
      "org.apache.commons.math.exception.DimensionMismatchException",
      "org.apache.commons.math.exception.MathIllegalNumberException",
      "org.apache.commons.math.linear.ArrayRealVector",
      "org.apache.commons.math.analysis.BivariateRealFunction",
      "org.apache.commons.math.linear.RealVectorFormat",
      "org.apache.commons.math.exception.OutOfRangeException",
      "org.apache.commons.math.linear.AnyMatrix",
      "org.apache.commons.math.exception.NoDataException",
      "org.apache.commons.math.linear.RealVector$2",
      "org.apache.commons.math.exception.NumberIsTooLargeException",
      "org.apache.commons.math.FieldElement",
      "org.apache.commons.math.exception.MathInternalError",
      "org.apache.commons.math.exception.ZeroException",
      "org.apache.commons.math.linear.RealMatrixPreservingVisitor",
      "org.apache.commons.math.linear.Array2DRowRealMatrix",
      "org.apache.commons.math.linear.SparseRealMatrix",
      "org.apache.commons.math.linear.FieldMatrixPreservingVisitor",
      "org.apache.commons.math.linear.NonSquareMatrixException",
      "org.apache.commons.math.exception.util.Localizable",
      "org.apache.commons.math.linear.AbstractRealMatrix",
      "org.apache.commons.math.linear.DefaultRealMatrixPreservingVisitor",
      "org.apache.commons.math.linear.FieldVector",
      "org.apache.commons.math.exception.MathArithmeticException",
      "org.apache.commons.math.linear.Array2DRowFieldMatrix",
      "org.apache.commons.math.linear.BlockRealMatrix",
      "org.apache.commons.math.linear.OpenMapRealVector",
      "org.apache.commons.math.exception.util.LocalizedFormats",
      "org.apache.commons.math.util.MathUtils$OrderDirection",
      "org.apache.commons.math.linear.RealVector",
      "org.apache.commons.math.linear.RealMatrixChangingVisitor",
      "org.apache.commons.math.linear.FieldMatrix",
      "org.apache.commons.math.exception.util.ExceptionContextProvider",
      "org.apache.commons.math.linear.OpenMapRealMatrix",
      "org.apache.commons.math.exception.MathUnsupportedOperationException",
      "org.apache.commons.math.exception.util.ArgUtils"
    );
  } 

  private static void resetClasses() {
    org.evosuite.runtime.classhandling.ClassResetter.getInstance().setClassLoader(OpenMapRealMatrix_ESTest_scaffolding.class.getClassLoader()); 

    org.evosuite.runtime.classhandling.ClassStateSupport.resetClasses(
      "org.apache.commons.math.linear.RealLinearOperator",
      "org.apache.commons.math.linear.AbstractRealMatrix",
      "org.apache.commons.math.linear.OpenMapRealMatrix",
      "org.apache.commons.math.exception.util.LocalizedFormats",
      "org.apache.commons.math.util.OpenIntToDoubleHashMap",
      "org.apache.commons.math.util.FastMath",
      "org.apache.commons.math.linear.MatrixUtils",
      "org.apache.commons.math.exception.MathIllegalArgumentException",
      "org.apache.commons.math.exception.MathIllegalNumberException",
      "org.apache.commons.math.exception.OutOfRangeException",
      "org.apache.commons.math.exception.util.ExceptionContext",
      "org.apache.commons.math.exception.util.ArgUtils",
      "org.apache.commons.math.exception.NumberIsTooSmallException",
      "org.apache.commons.math.exception.NotStrictlyPositiveException",
      "org.apache.commons.math.util.OpenIntToDoubleHashMap$Iterator",
      "org.apache.commons.math.linear.Array2DRowRealMatrix",
      "org.apache.commons.math.util.MathUtils",
      "org.apache.commons.math.exception.DimensionMismatchException",
      "org.apache.commons.math.linear.RealVector",
      "org.apache.commons.math.linear.RealVectorFormat",
      "org.apache.commons.math.util.CompositeFormat",
      "org.apache.commons.math.linear.ArrayRealVector",
      "org.apache.commons.math.linear.DefaultRealMatrixChangingVisitor",
      "org.apache.commons.math.linear.DefaultRealMatrixPreservingVisitor",
      "org.apache.commons.math.linear.SparseRealVector",
      "org.apache.commons.math.linear.OpenMapRealVector",
      "org.apache.commons.math.exception.NoDataException",
      "org.apache.commons.math.linear.AbstractRealMatrix$2",
      "org.apache.commons.math.linear.AbstractRealMatrix$5"
    );
  }
}
