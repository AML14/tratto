/*
 * This file was automatically generated by EvoSuite
 * Mon Nov 20 07:23:05 GMT 2023
 */
package org.apache.commons.jxpath.ri.compiler;

import org.junit.Test;
import static org.junit.Assert.*;
import org.apache.commons.jxpath.ri.EvalContext;
import org.apache.commons.jxpath.ri.QName;
import org.apache.commons.jxpath.ri.axes.NamespaceContext;
import org.apache.commons.jxpath.ri.compiler.Constant;
import org.apache.commons.jxpath.ri.compiler.CoreOperationEqual;
import org.apache.commons.jxpath.ri.compiler.CoreOperationSubtract;
import org.apache.commons.jxpath.ri.compiler.Expression;
import org.apache.commons.jxpath.ri.compiler.NameAttributeTest;
import org.apache.commons.jxpath.ri.compiler.NodeNameTest;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class CoreOperationEqual_ESTest extends CoreOperationEqual_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test00() throws Throwable {
        NameAttributeTest nameAttributeTest0 = new NameAttributeTest((Expression) null, (Expression) null);
        String string0 = nameAttributeTest0.getSymbol();
    }

    @Test(timeout = 4000)
    public void test11() throws Throwable {
        Integer integer0 = new Integer((-1492));
        Constant constant0 = new Constant(integer0);
        NameAttributeTest nameAttributeTest0 = new NameAttributeTest(constant0, constant0);
        CoreOperationSubtract coreOperationSubtract0 = new CoreOperationSubtract(nameAttributeTest0, nameAttributeTest0);
        CoreOperationEqual coreOperationEqual0 = new CoreOperationEqual(coreOperationSubtract0, constant0);
        QName qName0 = new QName("=", "");
        NodeNameTest nodeNameTest0 = new NodeNameTest(qName0);
        NamespaceContext namespaceContext0 = new NamespaceContext((EvalContext) null, nodeNameTest0);
        Object object0 = coreOperationEqual0.computeValue(namespaceContext0);
        assertTrue(object0);
    }
}
