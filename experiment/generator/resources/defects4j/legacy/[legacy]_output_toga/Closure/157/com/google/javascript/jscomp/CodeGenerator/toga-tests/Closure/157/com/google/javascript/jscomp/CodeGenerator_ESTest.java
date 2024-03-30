/*
 * This file was automatically generated by EvoSuite
 * Sat Nov 04 07:34:56 GMT 2023
 */
package com.google.javascript.jscomp;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.google.javascript.jscomp.CodeConsumer;
import com.google.javascript.jscomp.CodeGenerator;
import com.google.javascript.rhino.Node;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class CodeGenerator_ESTest extends CodeGenerator_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        CodeGenerator codeGenerator0 = new CodeGenerator((CodeConsumer) null);
        Node node0 = new Node(51, 51, 51);
        CodeGenerator.Context codeGenerator_Context0 = CodeGenerator.Context.IN_FOR_INIT_CLAUSE;
        codeGenerator0.addList(node0, false, codeGenerator_Context0);
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        CodeGenerator codeGenerator0 = new CodeGenerator((CodeConsumer) null);
        Node node0 = Node.newString("-^LmUek7'&^Q$,qPju");
        codeGenerator0.addArrayList(node0);
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        String string0 = CodeGenerator.regexpEscape("G@tT@r:=]>9N `'K(");
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        String string0 = CodeGenerator.jsString("E_DOUBLE\u0010\u0001\u0012\u000E\n\nTYPE_FLOATC\u0002v\u000E?\n#YE_INT64\u0010\u0003\u0012\u000F\u000BTYP_UINT64\u0010:\u0012\u000E\nTPE_I_T32{9\u0012\u0010\n\fTYPE_FIXED64\u0010\u0006\u0012\u0010\n\fTYPE_FIXED32\u0010\u0007\u0012\r\n\tTPE_BOOL\u0010\b\u0012\n\u000BTYP_TRING\u0010\t\u0012\u000E\n\nTYPE_GOUP\u0010\n\u0012\u0010\fYPE_MESSAE\u0010\u000B\u0012\u000E\n\n/YPE_YTS\u0010\f\u0012\u000F\n\u000BTYPE_IT32\u0010\r\u0012\r\n\tTYPE_ENUM\u0010\u000E\u0012\u0011J\rTYPE_SFIXED32\u0010\u000F\u0012\u0011\n\rTYPE_SFIXED64\u0010\u0010\u0012\u000F\n\u000BTYPE_SINT32S\u0012.\n\u000BTYPE_SIT64\u0010\u0012\"C\n\u0005Labl..)\u000ELABEL:OPTIONAL\u0010\u0012\u0012\n\u000ELABEL_REQUIRD\u0010\u0002\u0012\u0012\u000ERBEREPEATED\u0010\u0003\"\u008C\n\u0013EnumDes(riptorProto\u0012\f\n\u0004namI\u0018\u0001", (CharsetEncoder) null);
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        Node node0 = Node.newNumber(0.0);
        CodeGenerator codeGenerator0 = new CodeGenerator((CodeConsumer) null);
        codeGenerator0.addList(node0);
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        CodeGenerator codeGenerator0 = new CodeGenerator((CodeConsumer) null);
        codeGenerator0.tagAsStrict();
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        String string0 = CodeGenerator.escapeToDoubleQuotedJsString(":J]]>");
        assertNotNull(string0);
    }

    @Test(timeout = 4000)
    public void test077() throws Throwable {
        CodeGenerator codeGenerator0 = new CodeGenerator((CodeConsumer) null);
        codeGenerator0.addCaseBody((Node) null);
    }

    @Test(timeout = 4000)
    public void test088() throws Throwable {
        Node node0 = Node.newString(",[k#o");
        CodeGenerator codeGenerator0 = new CodeGenerator((CodeConsumer) null);
        codeGenerator0.addAllSiblings(node0);
    }

    @Test(timeout = 4000)
    public void test099() throws Throwable {
        CodeGenerator codeGenerator0 = new CodeGenerator((CodeConsumer) null);
        Node node0 = new Node(107, 107, 107);
        codeGenerator0.addList(node0, false);
    }

    @Test(timeout = 4000)
    public void test1210() throws Throwable {
        Node node0 = Node.newNumber((-32.092457627454955));
        CodeGenerator codeGenerator0 = new CodeGenerator((CodeConsumer) null);
        codeGenerator0.addExpr(node0, 47);
    }

    @Test(timeout = 4000)
    public void test1311() throws Throwable {
        CodeGenerator codeGenerator0 = new CodeGenerator((CodeConsumer) null);
        Node node0 = Node.newString("-^LmUek7'&^Q$,qPju");
        CodeGenerator.Context codeGenerator_Context0 = CodeGenerator.Context.IN_FOR_INIT_CLAUSE;
        codeGenerator0.addList(node0, false, codeGenerator_Context0);
    }

    @Test(timeout = 4000)
    public void test1612() throws Throwable {
        String string0 = CodeGenerator.jsString("Mei[&'=f^.k", (CharsetEncoder) null);
    }

    @Test(timeout = 4000)
    public void test1713() throws Throwable {
        Charset charset0 = Charset.defaultCharset();
        CharsetEncoder charsetEncoder0 = charset0.newEncoder();
        String string0 = CodeGenerator.strEscape("\n\ndeprecated\u0018\u0003 \u0001(\b:\u0005false\u0012\u001C\n\u0014experimental_map_key\u0018\t \u0001(\t\u0012C\n\u0014uninterpreted_option\u0018\u00E7\u0007 \u0003(\u000B2$.google.protobuf.UninterpretedOption\"/\n\u0005CType\u0012\n\n\u0006STRING\u0010\u0000\u0012\b\n\u0004CORD\u0010\u0001\u0012\u0010\n\fSTRING_PIECE\u0010\u0002*\t\b\u00E8\u0007\u0010\u0080\u0080\u0080\u0080\u0002\"]\n\u000BEnumOptions\u0012C\n\u0014uninterpreted_option\u0018\u00E7\u0007 \u0003(\u000B2$.google.protobuf.UninterpretedOption*\t\b\u00E8\u0007\u0010\u0080\u0080\u0080\u0080\u0002\"b\n\u0010EnumValueOptions\u0012C\n\u0014uninterpreted_option\u0018\u00E7\u0007 \u0003(\u000B2$.google.protobuf.UninterpretedOption*\t\b\u00E8\u0007\u0010\u0080\u0080\u0080\u0080\u0002\"`\n\u000EServiceOptions\u0012C\n", 'x', "\n\ndeprecated\u0018\u0003 \u0001(\b:\u0005false\u0012\u001C\n\u0014experimental_map_key\u0018\t \u0001(\t\u0012C\n\u0014uninterpreted_option\u0018\u00E7\u0007 \u0003(\u000B2$.google.protobuf.UninterpretedOption\"/\n\u0005CType\u0012\n\n\u0006STRING\u0010\u0000\u0012\b\n\u0004CORD\u0010\u0001\u0012\u0010\n\fSTRING_PIECE\u0010\u0002*\t\b\u00E8\u0007\u0010\u0080\u0080\u0080\u0080\u0002\"]\n\u000BEnumOptions\u0012C\n\u0014uninterpreted_option\u0018\u00E7\u0007 \u0003(\u000B2$.google.protobuf.UninterpretedOption*\t\b\u00E8\u0007\u0010\u0080\u0080\u0080\u0080\u0002\"b\n\u0010EnumValueOptions\u0012C\n\u0014uninterpreted_option\u0018\u00E7\u0007 \u0003(\u000B2$.google.protobuf.UninterpretedOption*\t\b\u00E8\u0007\u0010\u0080\u0080\u0080\u0080\u0002\"`\n\u000EServiceOptions\u0012C\n", "\n\ndeprecated\u0018\u0003 \u0001(\b:\u0005false\u0012\u001C\n\u0014experimental_map_key\u0018\t \u0001(\t\u0012C\n\u0014uninterpreted_option\u0018\u00E7\u0007 \u0003(\u000B2$.google.protobuf.UninterpretedOption\"/\n\u0005CType\u0012\n\n\u0006STRING\u0010\u0000\u0012\b\n\u0004CORD\u0010\u0001\u0012\u0010\n\fSTRING_PIECE\u0010\u0002*\t\b\u00E8\u0007\u0010\u0080\u0080\u0080\u0080\u0002\"]\n\u000BEnumOptions\u0012C\n\u0014uninterpreted_option\u0018\u00E7\u0007 \u0003(\u000B2$.google.protobuf.UninterpretedOption*\t\b\u00E8\u0007\u0010\u0080\u0080\u0080\u0080\u0002\"b\n\u0010EnumValueOptions\u0012C\n\u0014uninterpreted_option\u0018\u00E7\u0007 \u0003(\u000B2$.google.protobuf.UninterpretedOption*\t\b\u00E8\u0007\u0010\u0080\u0080\u0080\u0080\u0002\"`\n\u000EServiceOptions\u0012C\n", "\n\ndeprecated\u0018\u0003 \u0001(\b:\u0005false\u0012\u001C\n\u0014experimental_map_key\u0018\t \u0001(\t\u0012C\n\u0014uninterpreted_option\u0018\u00E7\u0007 \u0003(\u000B2$.google.protobuf.UninterpretedOption\"/\n\u0005CType\u0012\n\n\u0006STRING\u0010\u0000\u0012\b\n\u0004CORD\u0010\u0001\u0012\u0010\n\fSTRING_PIECE\u0010\u0002*\t\b\u00E8\u0007\u0010\u0080\u0080\u0080\u0080\u0002\"]\n\u000BEnumOptions\u0012C\n\u0014uninterpreted_option\u0018\u00E7\u0007 \u0003(\u000B2$.google.protobuf.UninterpretedOption*\t\b\u00E8\u0007\u0010\u0080\u0080\u0080\u0080\u0002\"b\n\u0010EnumValueOptions\u0012C\n\u0014uninterpreted_option\u0018\u00E7\u0007 \u0003(\u000B2$.google.protobuf.UninterpretedOption*\t\b\u00E8\u0007\u0010\u0080\u0080\u0080\u0080\u0002\"`\n\u000EServiceOptions\u0012C\n", charsetEncoder0);
    }

    @Test(timeout = 4000)
    public void test1814() throws Throwable {
        String string0 = CodeGenerator.escapeToDoubleQuotedJsString(":a&<!-->");
        assertNotNull(string0);
    }

    @Test(timeout = 4000)
    public void test1915() throws Throwable {
        String string0 = CodeGenerator.escapeToDoubleQuotedJsString("*>$b/l)A}m*qS");
        assertNotNull(string0);
    }

    @Test(timeout = 4000)
    public void test2016() throws Throwable {
        String string0 = CodeGenerator.regexpEscape(":a&!-4qn->");
    }

    @Test(timeout = 4000)
    public void test2117() throws Throwable {
        String string0 = CodeGenerator.escapeToDoubleQuotedJsString("&m_3</scriptf}");
        assertNotNull(string0);
    }

    @Test(timeout = 4000)
    public void test2218() throws Throwable {
        String string0 = CodeGenerator.escapeToDoubleQuotedJsString("`<sRFfLs");
        assertNotNull(string0);
    }

    @Test(timeout = 4000)
    public void test2319() throws Throwable {
        String string0 = CodeGenerator.identifierEscape("E_DOUBLE\u0010\u0001\u0012\u000E\n\nTYPE_FLOAT\u0010\u0002\u0012\u000E\n\nTYPE_INT64\u0010\u0003\u0012\u000F\n\u000BTYPE_UINT64\u0010\u0004\u0012\u000E\n\nTYPE_INT32\u0010\u0005\u0012\u0010\n\fTYPE_FIXED64\u0010\u0006\u0012\u0010\n\fTYPE_FIXED32\u0010\u0007\u0012\r\n\tTYPE_BOOL\u0010\b\u0012\u000F\n\u000BTYPE_STRING\u0010\t\u0012\u000E\n\nTYPE_GROUP\u0010\n\u0012\u0010\n\fYPE_MESSAGE\u0010\u000B\u0012\u000E\n\nTYPE_BYTES\u0010\f\u0012\u000F\n\u000BTYPE_UINT32\u0010\r\u0012\r\n\tTYPE_ENUM\u0010\u000E\u0012\u0011\n\rTYPE_SFIXED32\u0010\u000F\u0012\u0011\n\rTYPE_SFIXED64\u0010\u0010\u0012\u000F\n\u000BTYPE_SINT32\u0010\u0011\u0012\u000F\n\u000BTYPE_SINT64\u0010\u0012\"C\n\u0005Label\u0012\u0012\n\u000ELABEL_OPTIONAL\u0010\u0001\u0012\u0012\n\u000ELABEL_REQUIRED\u0010\u0002\u0012\u0012\n\u000ELABEL_REPEATED\u0010\u0003\"\u008C\u0001\n\u0013EnumDescriptorProto\u0012\f\n\u0004name\u0018\u0001");
    }

    @Test(timeout = 4000)
    public void test2420() throws Throwable {
        String string0 = CodeGenerator.identifierEscape("OTHER");
    }
}
