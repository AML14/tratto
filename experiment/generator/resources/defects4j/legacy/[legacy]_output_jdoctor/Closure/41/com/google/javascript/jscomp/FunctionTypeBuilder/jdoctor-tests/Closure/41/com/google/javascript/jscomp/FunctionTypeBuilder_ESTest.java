/*
 * This file was automatically generated by EvoSuite
 * Sun Dec 31 06:15:23 GMT 2023
 */
package com.google.javascript.jscomp;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.google.javascript.jscomp.Compiler;
import com.google.javascript.jscomp.FunctionTypeBuilder;
import com.google.javascript.jscomp.GlobalNamespace;
import com.google.javascript.jscomp.Normalize;
import com.google.javascript.jscomp.Scope;
import com.google.javascript.rhino.JSDocInfo;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.jstype.FunctionType;
import com.google.javascript.rhino.jstype.JSType;
import com.google.javascript.rhino.jstype.JSTypeNative;
import com.google.javascript.rhino.jstype.JSTypeRegistry;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class FunctionTypeBuilder_ESTest extends FunctionTypeBuilder_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.parseSyntheticCode("com.google.javascript.jscomp.FunctionTypeBuilder$ImplementedTypeVXlidator", "com.google.javascript.jscomp.FunctionTypeBuilder$ImplementedTypeVXlidator");
        JSDocInfo jSDocInfo0 = new JSDocInfo();
        FunctionTypeBuilder functionTypeBuilder0 = new FunctionTypeBuilder("com.google.javascript.jscomp.FunctionTypeBuilder$ImplementedTypeVXlidator", compiler0, node0, "com.google.javascript.jscomp.FunctionTypeBuilder$ImplementedTypeVXlidator", (Scope) null);
        JSTypeRegistry jSTypeRegistry0 = compiler0.getTypeRegistry();
        JSType[] jSTypeArray0 = new JSType[7];
        JSTypeNative jSTypeNative0 = JSTypeNative.REFERENCE_ERROR_FUNCTION_TYPE;
        FunctionType functionType0 = jSTypeRegistry0.getNativeFunctionType(jSTypeNative0);
        functionTypeBuilder0.inferFromOverriddenFunction(functionType0, node0);
        Node node1 = jSTypeRegistry0.createParameters(jSTypeArray0);
        functionTypeBuilder0.inferParameterTypes(node1, jSDocInfo0);
        compiler0.getWarningCount();
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = Normalize.parseAndNormalizeTestCode(compiler0, "JSC_OPTIONAL>ARG_AT_END", "JSC_OPTIONAL>ARG_AT_END");
        FunctionTypeBuilder functionTypeBuilder0 = new FunctionTypeBuilder("JSC_OPTIONAL>ARG_AT_END", compiler0, node0, "JSC_OPTIONAL>ARG_AT_END", (Scope) null);
        FunctionTypeBuilder.AstFunctionContents functionTypeBuilder_AstFunctionContents0 = new FunctionTypeBuilder.AstFunctionContents(node0);
        FunctionTypeBuilder functionTypeBuilder1 = functionTypeBuilder0.setContents(functionTypeBuilder_AstFunctionContents0);
        JSDocInfo jSDocInfo0 = new JSDocInfo();
        functionTypeBuilder1.inferParameterTypes(jSDocInfo0);
        functionTypeBuilder1.buildAndRegister();
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = Normalize.parseAndNormalizeTestCode(compiler0, "[d/", "[d/");
        FunctionTypeBuilder.AstFunctionContents functionTypeBuilder_AstFunctionContents0 = new FunctionTypeBuilder.AstFunctionContents(node0);
        functionTypeBuilder_AstFunctionContents0.mayHaveNonEmptyReturns();
    }

    @Test(timeout = 4000)
    public void test023() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = Normalize.parseAndNormalizeTestCode(compiler0, "[d/", "[d/");
        FunctionTypeBuilder.AstFunctionContents functionTypeBuilder_AstFunctionContents0 = new FunctionTypeBuilder.AstFunctionContents(node0);
        functionTypeBuilder_AstFunctionContents0.recordNonEmptyReturn();
        functionTypeBuilder_AstFunctionContents0.mayHaveNonEmptyReturns();
    }

    @Test(timeout = 4000)
    public void test034() throws Throwable {
        FunctionTypeBuilder.UnknownFunctionContents functionTypeBuilder_UnknownFunctionContents0 = new FunctionTypeBuilder.UnknownFunctionContents();
        Iterable<String> iterable0 = functionTypeBuilder_UnknownFunctionContents0.getEscapedVarNames();
    }

    @Test(timeout = 4000)
    public void test045() throws Throwable {
        FunctionTypeBuilder.UnknownFunctionContents functionTypeBuilder_UnknownFunctionContents0 = new FunctionTypeBuilder.UnknownFunctionContents();
        boolean boolean0 = functionTypeBuilder_UnknownFunctionContents0.mayBeFromExterns();
    }

    @Test(timeout = 4000)
    public void test056() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = Normalize.parseAndNormalizeTestCode(compiler0, "d", "d");
        JSDocInfo jSDocInfo0 = new JSDocInfo();
        FunctionTypeBuilder functionTypeBuilder0 = new FunctionTypeBuilder("d", compiler0, node0, "d", (Scope) null);
        FunctionTypeBuilder functionTypeBuilder1 = functionTypeBuilder0.inferParameterTypes(jSDocInfo0);
        FunctionType functionType0 = functionTypeBuilder1.buildAndRegister();
        functionTypeBuilder0.inferFromOverriddenFunction(functionType0, node0);
        compiler0.getWarningCount();
    }

    @Test(timeout = 4000)
    public void test057() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = Normalize.parseAndNormalizeTestCode(compiler0, "d", "d");
        JSDocInfo jSDocInfo0 = new JSDocInfo();
        FunctionTypeBuilder functionTypeBuilder0 = new FunctionTypeBuilder("d", compiler0, node0, "d", (Scope) null);
        FunctionTypeBuilder functionTypeBuilder1 = functionTypeBuilder0.inferParameterTypes(jSDocInfo0);
        FunctionType functionType0 = functionTypeBuilder1.buildAndRegister();
        functionTypeBuilder0.inferFromOverriddenFunction(functionType0, node0);
        functionType0.isReturnTypeInferred();
    }

    @Test(timeout = 4000)
    public void test078() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.parseSyntheticCode("q3\"5}\"", "q3\"5}\"");
        FunctionTypeBuilder functionTypeBuilder0 = new FunctionTypeBuilder("q3\"5}\"", compiler0, node0, "q3\"5}\"", (Scope) null);
        FunctionTypeBuilder functionTypeBuilder1 = functionTypeBuilder0.setContents((FunctionTypeBuilder.FunctionContents) null);
    }

    @Test(timeout = 4000)
    public void test089() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.parseTestCode("@implemeots used without const:uctor or @intIrfce 4or `0}");
        FunctionTypeBuilder functionTypeBuilder0 = new FunctionTypeBuilder("@implemeots used without const:uctor or @intIrfce 4or `0}", compiler0, node0, "@implemeots used without const:uctor or @intIrfce 4or `0}", (Scope) null);
        FunctionTypeBuilder functionTypeBuilder1 = functionTypeBuilder0.inferFromOverriddenFunction((FunctionType) null, node0);
    }

    @Test(timeout = 4000)
    public void test0910() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.parseSyntheticCode("J;Cs9PINAjoARGYdEN_", "J;Cs9PINAjoARGYdEN_");
        FunctionTypeBuilder functionTypeBuilder0 = new FunctionTypeBuilder("J;Cs9PINAjoARGYdEN_", compiler0, node0, "J;Cs9PINAjoARGYdEN_", (Scope) null);
        JSTypeRegistry jSTypeRegistry0 = compiler0.getTypeRegistry();
        FunctionType functionType0 = jSTypeRegistry0.createInterfaceType("J;Cs9PINAjoARGYdEN_", (Node) null);
        FunctionTypeBuilder functionTypeBuilder1 = functionTypeBuilder0.inferFromOverriddenFunction(functionType0, (Node) null);
    }

    @Test(timeout = 4000)
    public void test1011() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.parseTestCode("J;C_OPTIONAL_ARG_ATEND");
        FunctionTypeBuilder functionTypeBuilder0 = new FunctionTypeBuilder("J;C_OPTIONAL_ARG_ATEND", compiler0, node0, "J;C_OPTIONAL_ARG_ATEND", (Scope) null);
        JSTypeRegistry jSTypeRegistry0 = compiler0.getTypeRegistry();
        JSTypeNative jSTypeNative0 = JSTypeNative.REGEXP_FUNCTION_TYPE;
        FunctionType functionType0 = jSTypeRegistry0.getNativeFunctionType(jSTypeNative0);
        FunctionTypeBuilder functionTypeBuilder1 = functionTypeBuilder0.inferFromOverriddenFunction(functionType0, node0);
    }

    @Test(timeout = 4000)
    public void test1112() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.parseTestCode("com.google.javascript.jscomp.FunctinTypeBuilder$ImplementedTypeVXlidator");
        FunctionTypeBuilder functionTypeBuilder0 = new FunctionTypeBuilder("com.google.javascript.jscomp.FunctinTypeBuilder$ImplementedTypeVXlidator", compiler0, node0, "com.google.javascript.jscomp.FunctinTypeBuilder$ImplementedTypeVXlidator", (Scope) null);
        JSTypeRegistry jSTypeRegistry0 = compiler0.getTypeRegistry();
        JSTypeNative jSTypeNative0 = JSTypeNative.FUNCTION_FUNCTION_TYPE;
        FunctionType functionType0 = jSTypeRegistry0.getNativeFunctionType(jSTypeNative0);
        functionTypeBuilder0.inferFromOverriddenFunction(functionType0, node0);
        JSType[] jSTypeArray0 = new JSType[9];
        Node node1 = jSTypeRegistry0.createParameters(jSTypeArray0);
        functionTypeBuilder0.inferParameterTypes(node1, (JSDocInfo) null);
        compiler0.getWarningCount();
    }

    @Test(timeout = 4000)
    public void test1213() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.parseSyntheticCode("J;C_OPTINAL_ARG_ATEND", "J;C_OPTINAL_ARG_ATEND");
        FunctionTypeBuilder functionTypeBuilder0 = new FunctionTypeBuilder("J;C_OPTINAL_ARG_ATEND", compiler0, node0, "J;C_OPTINAL_ARG_ATEND", (Scope) null);
        JSTypeRegistry jSTypeRegistry0 = compiler0.getTypeRegistry();
        FunctionType functionType0 = jSTypeRegistry0.createFunctionType((JSType) null, node0);
        FunctionTypeBuilder functionTypeBuilder1 = functionTypeBuilder0.inferFromOverriddenFunction(functionType0, node0);
    }

    @Test(timeout = 4000)
    public void test1314() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.parseTestCode("J;C_OPTIONAL_ARG_ATEND");
        FunctionTypeBuilder functionTypeBuilder0 = new FunctionTypeBuilder("J;C_OPTIONAL_ARG_ATEND", compiler0, node0, "J;C_OPTIONAL_ARG_ATEND", (Scope) null);
        JSTypeRegistry jSTypeRegistry0 = compiler0.getTypeRegistry();
        JSTypeNative jSTypeNative0 = JSTypeNative.NO_RESOLVED_TYPE;
        FunctionType functionType0 = jSTypeRegistry0.getNativeFunctionType(jSTypeNative0);
        functionTypeBuilder0.inferFromOverriddenFunction(functionType0, node0);
        compiler0.getWarningCount();
    }

    @Test(timeout = 4000)
    public void test1415() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = Normalize.parseAndNormalizeTestCode(compiler0, "SHRFu2Ur+qWU*w.9.~G", "SHRFu2Ur+qWU*w.9.~G");
        FunctionTypeBuilder functionTypeBuilder0 = new FunctionTypeBuilder("SHRFu2Ur+qWU*w.9.~G", compiler0, node0, "SHRFu2Ur+qWU*w.9.~G", (Scope) null);
        FunctionTypeBuilder functionTypeBuilder1 = functionTypeBuilder0.inferReturnType((JSDocInfo) null);
    }

    @Test(timeout = 4000)
    public void test1516() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = Normalize.parseAndNormalizeTestCode(compiler0, "SHRFu2Ur+qWU*w.9.~G", "SHRFu2Ur+qWU*w.9.~G");
        JSDocInfo jSDocInfo0 = new JSDocInfo();
        FunctionTypeBuilder functionTypeBuilder0 = new FunctionTypeBuilder("SHRFu2Ur+qWU*w.9.~G", compiler0, node0, "SHRFu2Ur+qWU*w.9.~G", (Scope) null);
        FunctionTypeBuilder functionTypeBuilder1 = functionTypeBuilder0.inferReturnType(jSDocInfo0);
    }

    @Test(timeout = 4000)
    public void test1617() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = Normalize.parseAndNormalizeTestCode(compiler0, "", "");
        FunctionTypeBuilder functionTypeBuilder0 = new FunctionTypeBuilder("", compiler0, node0, "", (Scope) null);
        FunctionTypeBuilder functionTypeBuilder1 = functionTypeBuilder0.inferInheritance((JSDocInfo) null);
    }

    @Test(timeout = 4000)
    public void test1718() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = Normalize.parseAndNormalizeTestCode(compiler0, "d", "d");
        JSDocInfo jSDocInfo0 = new JSDocInfo();
        FunctionTypeBuilder functionTypeBuilder0 = new FunctionTypeBuilder("d", compiler0, node0, "d", (Scope) null);
        functionTypeBuilder0.inferInheritance(jSDocInfo0);
        compiler0.getWarningCount();
    }

    @Test(timeout = 4000)
    public void test1819() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.parseTestCode("JSC_OPTIONAL_ARG_AT_END");
        JSDocInfo jSDocInfo0 = new JSDocInfo();
        FunctionTypeBuilder functionTypeBuilder0 = new FunctionTypeBuilder("JSC_OPTIONAL_ARG_AT_END", compiler0, node0, "JSC_OPTIONAL_ARG_AT_END", (Scope) null);
        FunctionTypeBuilder functionTypeBuilder1 = functionTypeBuilder0.inferParameterTypes(jSDocInfo0);
        FunctionType functionType0 = functionTypeBuilder0.buildAndRegister();
        functionTypeBuilder1.inferThisType(jSDocInfo0, functionType0);
        functionTypeBuilder1.inferThisType(jSDocInfo0, functionType0);
        functionType0.isReturnTypeInferred();
    }

    @Test(timeout = 4000)
    public void test1920() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = Normalize.parseAndNormalizeTestCode(compiler0, "SHRFu2Ur+qWU*w.9.~G", "SHRFu2Ur+qWU*w.9.~G");
        FunctionTypeBuilder functionTypeBuilder0 = new FunctionTypeBuilder("SHRFu2Ur+qWU*w.9.~G", compiler0, node0, "SHRFu2Ur+qWU*w.9.~G", (Scope) null);
        FunctionTypeBuilder functionTypeBuilder1 = functionTypeBuilder0.inferThisType((JSDocInfo) null, (JSType) null);
    }

    @Test(timeout = 4000)
    public void test2021() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.parseTestCode("J;C_OPTINAL_ARG_AdEND");
        FunctionTypeBuilder functionTypeBuilder0 = new FunctionTypeBuilder("J;C_OPTINAL_ARG_AdEND", compiler0, node0, "J;C_OPTINAL_ARG_AdEND", (Scope) null);
        GlobalNamespace globalNamespace0 = new GlobalNamespace(compiler0, node0, node0);
        JSType jSType0 = globalNamespace0.getTypeOfThis();
        FunctionTypeBuilder functionTypeBuilder1 = functionTypeBuilder0.inferThisType((JSDocInfo) null, jSType0);
    }

    @Test(timeout = 4000)
    public void test2122() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = Normalize.parseAndNormalizeTestCode(compiler0, "JSC_OPTIONAL_ARG_AT_END", "JSC_OPTIONAL_ARG_AT_END");
        JSDocInfo jSDocInfo0 = new JSDocInfo();
        FunctionTypeBuilder functionTypeBuilder0 = new FunctionTypeBuilder("JSC_OPTIONAL_ARG_AT_END", compiler0, node0, "JSC_OPTIONAL_ARG_AT_END", (Scope) null);
        FunctionTypeBuilder functionTypeBuilder1 = functionTypeBuilder0.inferParameterTypes((Node) null, jSDocInfo0);
    }

    @Test(timeout = 4000)
    public void test2223() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = Normalize.parseAndNormalizeTestCode(compiler0, "JSC_OPTIONAL_ARG_AT_END", "JSC_OPTIONAL_ARG_AT_END");
        FunctionTypeBuilder functionTypeBuilder0 = new FunctionTypeBuilder("JSC_OPTIONAL_ARG_AT_END", compiler0, node0, "JSC_OPTIONAL_ARG_AT_END", (Scope) null);
        FunctionTypeBuilder functionTypeBuilder1 = functionTypeBuilder0.inferParameterTypes((Node) null, (JSDocInfo) null);
    }

    @Test(timeout = 4000)
    public void test2324() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = compiler0.parseTestCode("com.google.javascript.jscomp.FunctinTypeBuilder$ImplementedTypeVXlidator");
        FunctionTypeBuilder functionTypeBuilder0 = new FunctionTypeBuilder("com.google.javascript.jscomp.FunctinTypeBuilder$ImplementedTypeVXlidator", compiler0, node0, "com.google.javascript.jscomp.FunctinTypeBuilder$ImplementedTypeVXlidator", (Scope) null);
        JSTypeRegistry jSTypeRegistry0 = compiler0.getTypeRegistry();
        JSType[] jSTypeArray0 = new JSType[9];
        Node node1 = jSTypeRegistry0.createParameters(jSTypeArray0);
        functionTypeBuilder0.inferParameterTypes(node1, (JSDocInfo) null);
        compiler0.getWarningCount();
    }

    @Test(timeout = 4000)
    public void test2425() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = Normalize.parseAndNormalizeTestCode(compiler0, "JSC_OPTIONAL>ARG_AT_END", "JSC_OPTIONAL>ARG_AT_END");
        FunctionTypeBuilder functionTypeBuilder0 = new FunctionTypeBuilder("JSC_OPTIONAL>ARG_AT_END", compiler0, node0, "JSC_OPTIONAL>ARG_AT_END", (Scope) null);
        FunctionTypeBuilder functionTypeBuilder1 = functionTypeBuilder0.inferTemplateTypeName((JSDocInfo) null);
    }

    @Test(timeout = 4000)
    public void test2526() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = Normalize.parseAndNormalizeTestCode(compiler0, "", "");
        JSDocInfo jSDocInfo0 = new JSDocInfo();
        FunctionTypeBuilder functionTypeBuilder0 = new FunctionTypeBuilder("", compiler0, node0, "", (Scope) null);
        FunctionTypeBuilder functionTypeBuilder1 = functionTypeBuilder0.inferTemplateTypeName(jSDocInfo0);
    }

    @Test(timeout = 4000)
    public void test2627() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = Normalize.parseAndNormalizeTestCode(compiler0, "JSC_OPTIONjL_ARG_AT_END", "JSC_OPTIONjL_ARG_AT_END");
        JSDocInfo jSDocInfo0 = new JSDocInfo();
        FunctionTypeBuilder functionTypeBuilder0 = new FunctionTypeBuilder("JSC_OPTIONjL_ARG_AT_END", compiler0, node0, "JSC_OPTIONjL_ARG_AT_END", (Scope) null);
        FunctionTypeBuilder functionTypeBuilder1 = functionTypeBuilder0.inferParameterTypes(jSDocInfo0);
        functionTypeBuilder1.buildAndRegister();
        FunctionType functionType0 = functionTypeBuilder1.buildAndRegister();
        functionType0.isReturnTypeInferred();
    }

    @Test(timeout = 4000)
    public void test2728() throws Throwable {
        Compiler compiler0 = new Compiler();
        Node node0 = Normalize.parseAndNormalizeTestCode(compiler0, "q3\"5}\"", "q3\"5}\"");
        FunctionTypeBuilder functionTypeBuilder0 = new FunctionTypeBuilder("q3\"5}\"", compiler0, node0, "q3\"5}\"", (Scope) null);
        functionTypeBuilder0.buildAndRegister();
    }

    @Test(timeout = 4000)
    public void test2829() throws Throwable {
        JSDocInfo jSDocInfo0 = new JSDocInfo();
        boolean boolean0 = FunctionTypeBuilder.isFunctionTypeDeclaration(jSDocInfo0);
    }

    @Test(timeout = 4000)
    public void test2930() throws Throwable {
        FunctionTypeBuilder.AstFunctionContents functionTypeBuilder_AstFunctionContents0 = new FunctionTypeBuilder.AstFunctionContents((Node) null);
        functionTypeBuilder_AstFunctionContents0.recordEscapedVarName("com.google.javascript.jscomp.FunctionTypeBuilder$ExtendedTypeValidator");
        Iterable<String> iterable0 = functionTypeBuilder_AstFunctionContents0.getEscapedVarNames();
        functionTypeBuilder_AstFunctionContents0.mayHaveNonEmptyReturns();
    }

    @Test(timeout = 4000)
    public void test3031() throws Throwable {
        FunctionTypeBuilder.AstFunctionContents functionTypeBuilder_AstFunctionContents0 = new FunctionTypeBuilder.AstFunctionContents((Node) null);
        Iterable<String> iterable0 = functionTypeBuilder_AstFunctionContents0.getEscapedVarNames();
    }

    @Test(timeout = 4000)
    public void test3032() throws Throwable {
        FunctionTypeBuilder.AstFunctionContents functionTypeBuilder_AstFunctionContents0 = new FunctionTypeBuilder.AstFunctionContents((Node) null);
        Iterable<String> iterable0 = functionTypeBuilder_AstFunctionContents0.getEscapedVarNames();
        functionTypeBuilder_AstFunctionContents0.mayHaveNonEmptyReturns();
    }

    @Test(timeout = 4000)
    public void test3133() throws Throwable {
        FunctionTypeBuilder.AstFunctionContents functionTypeBuilder_AstFunctionContents0 = new FunctionTypeBuilder.AstFunctionContents((Node) null);
        functionTypeBuilder_AstFunctionContents0.recordEscapedVarName("B#p/>yftG\"8Q{AI");
        functionTypeBuilder_AstFunctionContents0.recordEscapedVarName("B#p/>yftG\"8Q{AI");
        functionTypeBuilder_AstFunctionContents0.mayHaveNonEmptyReturns();
    }
}
