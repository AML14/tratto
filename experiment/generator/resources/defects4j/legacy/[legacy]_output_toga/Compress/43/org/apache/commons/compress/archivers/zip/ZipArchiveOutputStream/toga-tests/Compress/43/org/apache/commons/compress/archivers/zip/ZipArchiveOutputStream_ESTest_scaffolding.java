/**
 * Scaffolding file used to store all the setups needed to run 
 * tests automatically generated by EvoSuite
 * Sun Oct 15 15:30:32 GMT 2023
 */

package org.apache.commons.compress.archivers.zip;

import org.evosuite.runtime.annotation.EvoSuiteClassExclude;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.After;
import org.evosuite.runtime.sandbox.Sandbox;
import org.evosuite.runtime.sandbox.Sandbox.SandboxMode;

@EvoSuiteClassExclude
public class ZipArchiveOutputStream_ESTest_scaffolding {

  @org.junit.Rule 
  public org.evosuite.runtime.vnet.NonFunctionalRequirementRule nfr = new org.evosuite.runtime.vnet.NonFunctionalRequirementRule();

  private org.evosuite.runtime.thread.ThreadStopper threadStopper =  new org.evosuite.runtime.thread.ThreadStopper (org.evosuite.runtime.thread.KillSwitchHandler.getInstance(), 3000);


  @BeforeClass 
  public static void initEvoSuiteFramework() { 
    org.evosuite.runtime.RuntimeSettings.className = "org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream"; 
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

  @Before 
  public void initTestCase(){ 
    threadStopper.storeCurrentThreads();
    threadStopper.startRecordingTime();
    org.evosuite.runtime.jvm.ShutdownHookHandler.getInstance().initHandler(); 
    org.evosuite.runtime.sandbox.Sandbox.goingToExecuteSUTCode(); 
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
 
    /*No java.lang.System property to set*/
  }

  private static void initializeClasses() {
    org.evosuite.runtime.classhandling.ClassStateSupport.initializeClasses(ZipArchiveOutputStream_ESTest_scaffolding.class.getClassLoader() ,
      "org.apache.commons.compress.archivers.zip.UnsupportedZipFeatureException$Feature",
      "org.apache.commons.compress.archivers.zip.ZipArchiveEntry",
      "org.apache.commons.compress.archivers.zip.StreamCompressor",
      "org.apache.commons.compress.archivers.zip.Zip64ExtendedInformationExtraField",
      "org.apache.commons.compress.archivers.zip.X0016_CertificateIdForCentralDirectory",
      "org.apache.commons.compress.archivers.zip.ZipEightByteInteger",
      "org.apache.commons.compress.archivers.zip.ZipUtil",
      "org.apache.commons.compress.archivers.zip.PKWareExtraHeader$HashAlgorithm",
      "org.apache.commons.compress.archivers.zip.UnsupportedZipFeatureException",
      "org.apache.commons.compress.archivers.zip.JarMarker",
      "org.apache.commons.compress.archivers.zip.X0019_EncryptionRecipientCertificateList",
      "org.apache.commons.compress.archivers.zip.PKWareExtraHeader",
      "org.apache.commons.compress.archivers.zip.FallbackZipEncoding",
      "org.apache.commons.compress.archivers.arj.ArjArchiveEntry",
      "org.apache.commons.compress.archivers.zip.AbstractUnicodeExtraField",
      "org.apache.commons.compress.archivers.cpio.CpioConstants",
      "org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream$CurrentEntry",
      "org.apache.commons.compress.archivers.zip.ZipLong",
      "org.apache.commons.compress.archivers.dump.DumpArchiveEntry$TapeSegmentHeader",
      "org.apache.commons.compress.archivers.zip.ZipEncoding",
      "org.apache.commons.compress.archivers.dump.DumpArchiveEntry",
      "org.apache.commons.compress.archivers.zip.ZipExtraField",
      "org.apache.commons.compress.archivers.zip.Zip64Mode",
      "org.apache.commons.compress.archivers.zip.ZipShort",
      "org.apache.commons.compress.archivers.zip.X7875_NewUnix",
      "org.apache.commons.compress.archivers.dump.DumpArchiveSummary",
      "org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry",
      "org.apache.commons.compress.archivers.zip.ZipMethod",
      "org.apache.commons.compress.archivers.cpio.CpioArchiveEntry",
      "org.apache.commons.compress.archivers.ArchiveOutputStream",
      "org.apache.commons.compress.archivers.dump.DumpArchiveConstants",
      "org.apache.commons.compress.archivers.zip.GeneralPurposeBit",
      "org.apache.commons.compress.archivers.zip.PKWareExtraHeader$EncryptionAlgorithm",
      "org.apache.commons.compress.utils.Charsets",
      "org.apache.commons.compress.archivers.zip.X5455_ExtendedTimestamp",
      "org.apache.commons.compress.archivers.zip.Zip64RequiredException",
      "org.apache.commons.compress.archivers.zip.Simple8BitZipEncoding",
      "org.apache.commons.compress.archivers.ArchiveEntry",
      "org.apache.commons.compress.archivers.dump.DumpArchiveEntry$PERMISSION",
      "org.apache.commons.compress.archivers.zip.UnicodePathExtraField",
      "org.apache.commons.compress.archivers.dump.DumpArchiveConstants$COMPRESSION_TYPE",
      "org.apache.commons.compress.archivers.zip.AsiExtraField",
      "org.apache.commons.compress.archivers.tar.TarConstants",
      "org.apache.commons.compress.archivers.tar.TarArchiveEntry",
      "org.apache.commons.compress.archivers.zip.ResourceAlignmentExtraField",
      "org.apache.commons.compress.utils.ByteUtils",
      "org.apache.commons.compress.archivers.arj.LocalFileHeader",
      "org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream",
      "org.apache.commons.compress.archivers.zip.UnicodeCommentExtraField",
      "org.apache.commons.compress.archivers.zip.X0014_X509Certificates",
      "org.apache.commons.compress.archivers.zip.NioZipEncoding",
      "org.apache.commons.compress.archivers.zip.Simple8BitZipEncoding$Simple8BitChar",
      "org.apache.commons.compress.archivers.zip.UnparseableExtraFieldData",
      "org.apache.commons.compress.archivers.zip.X0017_StrongEncryptionHeader",
      "org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream$UnicodeExtraFieldPolicy",
      "org.apache.commons.compress.archivers.sevenz.SevenZMethodConfiguration",
      "org.apache.commons.compress.archivers.zip.X0015_CertificateIdForFile",
      "org.apache.commons.compress.archivers.zip.ZipEncodingHelper$SimpleEncodingHolder",
      "org.apache.commons.compress.archivers.zip.X000A_NTFS",
      "org.apache.commons.compress.archivers.zip.ZipEncodingHelper",
      "org.apache.commons.compress.archivers.zip.UnrecognizedExtraField",
      "org.apache.commons.compress.archivers.dump.DumpArchiveConstants$SEGMENT_TYPE",
      "org.apache.commons.compress.archivers.jar.JarArchiveEntry",
      "org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream$EntryMetaData",
      "org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream$1",
      "org.apache.commons.compress.archivers.ar.ArArchiveEntry",
      "org.apache.commons.compress.archivers.dump.DumpArchiveEntry$TYPE",
      "org.apache.commons.compress.archivers.zip.UnixStat",
      "org.apache.commons.compress.archivers.EntryStreamOffsets",
      "org.apache.commons.compress.archivers.zip.ZipFile$Entry"
    );
  } 

  private static void resetClasses() {
    org.evosuite.runtime.classhandling.ClassResetter.getInstance().setClassLoader(ZipArchiveOutputStream_ESTest_scaffolding.class.getClassLoader()); 

    org.evosuite.runtime.classhandling.ClassStateSupport.resetClasses(
      "org.apache.commons.compress.archivers.ArchiveOutputStream",
      "org.apache.commons.compress.archivers.zip.ZipLong",
      "org.apache.commons.compress.utils.ByteUtils",
      "org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream",
      "org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream$EntryMetaData",
      "org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream$CurrentEntry",
      "org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream$UnicodeExtraFieldPolicy",
      "org.apache.commons.compress.utils.Charsets",
      "org.apache.commons.compress.archivers.zip.ZipEncodingHelper$SimpleEncodingHolder",
      "org.apache.commons.compress.archivers.zip.FallbackZipEncoding",
      "org.apache.commons.compress.archivers.zip.ZipEncodingHelper",
      "org.apache.commons.compress.archivers.zip.Zip64Mode",
      "org.apache.commons.compress.archivers.zip.ZipShort",
      "org.apache.commons.compress.archivers.zip.Zip64ExtendedInformationExtraField",
      "org.apache.commons.compress.archivers.zip.ZipEightByteInteger",
      "org.apache.commons.compress.archivers.zip.ZipMethod",
      "org.apache.commons.compress.archivers.zip.UnsupportedZipFeatureException$Feature",
      "org.apache.commons.compress.archivers.zip.ResourceAlignmentExtraField",
      "org.apache.commons.compress.archivers.zip.ZipUtil"
    );
  }
}
