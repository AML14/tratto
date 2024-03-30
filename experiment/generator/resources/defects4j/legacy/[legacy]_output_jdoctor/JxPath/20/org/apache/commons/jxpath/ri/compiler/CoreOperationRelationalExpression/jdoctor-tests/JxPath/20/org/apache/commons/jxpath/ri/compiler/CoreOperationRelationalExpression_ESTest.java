/*
 * This file was automatically generated by EvoSuite
 * Mon Oct 16 00:43:10 GMT 2023
 */
package org.apache.commons.jxpath.ri.compiler;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.util.Iterator;
import org.apache.commons.jxpath.BasicVariables;
import org.apache.commons.jxpath.JXPathContext;
import org.apache.commons.jxpath.ri.EvalContext;
import org.apache.commons.jxpath.ri.JXPathContextReferenceImpl;
import org.apache.commons.jxpath.ri.QName;
import org.apache.commons.jxpath.ri.axes.AncestorContext;
import org.apache.commons.jxpath.ri.axes.InitialContext;
import org.apache.commons.jxpath.ri.axes.RootContext;
import org.apache.commons.jxpath.ri.axes.SelfContext;
import org.apache.commons.jxpath.ri.compiler.Constant;
import org.apache.commons.jxpath.ri.compiler.CoreFunction;
import org.apache.commons.jxpath.ri.compiler.CoreOperationAdd;
import org.apache.commons.jxpath.ri.compiler.CoreOperationAnd;
import org.apache.commons.jxpath.ri.compiler.CoreOperationDivide;
import org.apache.commons.jxpath.ri.compiler.CoreOperationGreaterThan;
import org.apache.commons.jxpath.ri.compiler.CoreOperationGreaterThanOrEqual;
import org.apache.commons.jxpath.ri.compiler.CoreOperationLessThan;
import org.apache.commons.jxpath.ri.compiler.CoreOperationLessThanOrEqual;
import org.apache.commons.jxpath.ri.compiler.CoreOperationMod;
import org.apache.commons.jxpath.ri.compiler.CoreOperationMultiply;
import org.apache.commons.jxpath.ri.compiler.CoreOperationNotEqual;
import org.apache.commons.jxpath.ri.compiler.CoreOperationOr;
import org.apache.commons.jxpath.ri.compiler.CoreOperationSubtract;
import org.apache.commons.jxpath.ri.compiler.Expression;
import org.apache.commons.jxpath.ri.compiler.NodeNameTest;
import org.apache.commons.jxpath.ri.compiler.NodeTest;
import org.apache.commons.jxpath.ri.compiler.NodeTypeTest;
import org.apache.commons.jxpath.ri.compiler.VariableReference;
import org.apache.commons.jxpath.ri.model.NodePointer;
import org.apache.commons.jxpath.ri.model.VariablePointer;
import org.apache.commons.jxpath.ri.model.beans.BeanPointer;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class CoreOperationRelationalExpression_ESTest extends CoreOperationRelationalExpression_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        CoreOperationNotEqual coreOperationNotEqual0 = new CoreOperationNotEqual((Expression) null, (Expression) null);
        CoreOperationGreaterThanOrEqual coreOperationGreaterThanOrEqual0 = new CoreOperationGreaterThanOrEqual(coreOperationNotEqual0, coreOperationNotEqual0);
        boolean boolean0 = coreOperationGreaterThanOrEqual0.isSymmetric();
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        Constant constant0 = new Constant((Number) null);
        CoreOperationSubtract coreOperationSubtract0 = new CoreOperationSubtract(constant0, constant0);
        CoreOperationMultiply coreOperationMultiply0 = new CoreOperationMultiply(coreOperationSubtract0, coreOperationSubtract0);
        CoreOperationGreaterThan coreOperationGreaterThan0 = new CoreOperationGreaterThan(coreOperationMultiply0, coreOperationMultiply0);
        int int0 = coreOperationGreaterThan0.getPrecedence();
    }

    @Test(timeout = 4000)
    public void test032() throws Throwable {
        JXPathContextReferenceImpl jXPathContextReferenceImpl0 = (JXPathContextReferenceImpl) JXPathContext.newContext((JXPathContext) null, (Object) ".1Ak)=pVPJ;z|U+dod");
        BasicVariables basicVariables0 = new BasicVariables();
        Expression[] expressionArray0 = new Expression[3];
        CoreFunction coreFunction0 = new CoreFunction(22, expressionArray0);
        CoreOperationMultiply coreOperationMultiply0 = new CoreOperationMultiply(coreFunction0, coreFunction0);
        expressionArray0[0] = (Expression) coreOperationMultiply0;
        NodeTypeTest nodeTypeTest0 = new NodeTypeTest(22);
        QName qName0 = new QName("jsO\u0002X209)EB");
        CoreOperationOr coreOperationOr0 = new CoreOperationOr(expressionArray0);
        CoreOperationLessThan coreOperationLessThan0 = new CoreOperationLessThan(coreOperationOr0, expressionArray0[1]);
        VariablePointer variablePointer0 = new VariablePointer(basicVariables0, qName0);
        BeanPointer beanPointer0 = (BeanPointer) NodePointer.newChildNodePointer(variablePointer0, qName0, expressionArray0[0]);
        RootContext rootContext0 = new RootContext(jXPathContextReferenceImpl0, beanPointer0);
        SelfContext selfContext0 = new SelfContext(rootContext0, nodeTypeTest0);
        coreOperationLessThan0.computeValue(selfContext0);
    }

    @Test(timeout = 4000)
    public void test043() throws Throwable {
        CoreOperationGreaterThanOrEqual coreOperationGreaterThanOrEqual0 = new CoreOperationGreaterThanOrEqual((Expression) null, (Expression) null);
        QName qName0 = new QName("W");
        NodeNameTest nodeNameTest0 = new NodeNameTest(qName0);
        AncestorContext ancestorContext0 = new AncestorContext((EvalContext) null, true, nodeNameTest0);
        coreOperationGreaterThanOrEqual0.computeValue(ancestorContext0);
    }

    @Test(timeout = 4000)
    public void test054() throws Throwable {
        Expression[] expressionArray0 = new Expression[0];
        CoreOperationAdd coreOperationAdd0 = new CoreOperationAdd(expressionArray0);
        CoreOperationGreaterThanOrEqual coreOperationGreaterThanOrEqual0 = new CoreOperationGreaterThanOrEqual(coreOperationAdd0, coreOperationAdd0);
        coreOperationGreaterThanOrEqual0.args = expressionArray0;
        CoreOperationGreaterThan coreOperationGreaterThan0 = new CoreOperationGreaterThan(coreOperationGreaterThanOrEqual0, coreOperationGreaterThanOrEqual0);
        coreOperationGreaterThan0.computeValue((EvalContext) null);
    }

    @Test(timeout = 4000)
    public void test065() throws Throwable {
        Constant constant0 = new Constant("org.apache.commons.jxpath.ri.compiler.CoreOperationRelationalExpression");
        CoreOperationMod coreOperationMod0 = new CoreOperationMod(constant0, constant0);
        CoreOperationNotEqual coreOperationNotEqual0 = new CoreOperationNotEqual(coreOperationMod0, coreOperationMod0);
        CoreOperationLessThanOrEqual coreOperationLessThanOrEqual0 = new CoreOperationLessThanOrEqual(coreOperationNotEqual0, coreOperationMod0);
        coreOperationLessThanOrEqual0.computeValue((EvalContext) null);
    }

    @Test(timeout = 4000)
    public void test076() throws Throwable {
        QName qName0 = new QName("org.apache.commons.jxpath.ri.compiler.CoreOperationRelationalExpression");
        VariableReference variableReference0 = new VariableReference(qName0);
        CoreOperationLessThan coreOperationLessThan0 = new CoreOperationLessThan(variableReference0, variableReference0);
        SelfContext selfContext0 = new SelfContext((EvalContext) null, (NodeTest) null);
        JXPathContextReferenceImpl jXPathContextReferenceImpl0 = (JXPathContextReferenceImpl) JXPathContext.newContext((Object) selfContext0);
        InitialContext initialContext0 = (InitialContext) jXPathContextReferenceImpl0.getAbsoluteRootContext();
        RootContext rootContext0 = initialContext0.getRootContext();
        Boolean boolean0 = (Boolean) coreOperationLessThan0.computeValue(rootContext0);
    }

    @Test(timeout = 4000)
    public void test087() throws Throwable {
        JXPathContext jXPathContext0 = JXPathContext.newContext((JXPathContext) null, (Object) ".1Ak)=pVPJ;z|U+dod");
        Object object0 = jXPathContext0.selectSingleNode("/>g");
    }

    @Test(timeout = 4000)
    public void test098() throws Throwable {
        Expression[] expressionArray0 = new Expression[0];
        CoreOperationAdd coreOperationAdd0 = new CoreOperationAdd(expressionArray0);
        CoreOperationLessThanOrEqual coreOperationLessThanOrEqual0 = new CoreOperationLessThanOrEqual(coreOperationAdd0, coreOperationAdd0);
        Object object0 = coreOperationLessThanOrEqual0.computeValue((EvalContext) null);
    }

    @Test(timeout = 4000)
    public void test109() throws Throwable {
        Expression[] expressionArray0 = new Expression[0];
        CoreOperationAnd coreOperationAnd0 = new CoreOperationAnd(expressionArray0);
        CoreOperationDivide coreOperationDivide0 = new CoreOperationDivide(coreOperationAnd0, coreOperationAnd0);
        CoreOperationMultiply coreOperationMultiply0 = new CoreOperationMultiply(coreOperationDivide0, coreOperationAnd0);
        CoreOperationLessThan coreOperationLessThan0 = new CoreOperationLessThan(coreOperationMultiply0, coreOperationDivide0);
        CoreOperationGreaterThan coreOperationGreaterThan0 = new CoreOperationGreaterThan(coreOperationAnd0, coreOperationLessThan0);
        CoreOperationGreaterThanOrEqual coreOperationGreaterThanOrEqual0 = new CoreOperationGreaterThanOrEqual(coreOperationGreaterThan0, coreOperationDivide0);
        Iterator iterator0 = coreOperationGreaterThanOrEqual0.iterate((EvalContext) null);
    }
}
