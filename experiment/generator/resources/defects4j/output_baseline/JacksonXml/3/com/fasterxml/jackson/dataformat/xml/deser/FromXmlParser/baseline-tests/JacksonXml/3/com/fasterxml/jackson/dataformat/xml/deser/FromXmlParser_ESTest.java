/*
 * This file was automatically generated by EvoSuite
 * Sun Nov 05 18:08:17 GMT 2023
 */
package com.fasterxml.jackson.dataformat.xml.deser;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import javax.xml.stream.util.StreamReaderDelegate;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class FromXmlParser_ESTest extends FromXmlParser_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test00() throws Throwable {
        StreamReaderDelegate streamReaderDelegate0 = new StreamReaderDelegate();
        FromXmlParser fromXmlParser0 = null;
        fromXmlParser0 = new FromXmlParser((IOContext) null, 0, 0, (ObjectCodec) null, streamReaderDelegate0);
    }

    @Test(timeout = 4000)
    public void test11() throws Throwable {
        int int0 = FromXmlParser.Feature.collectDefaults();
    }
}
