/*
 * This file was automatically generated by EvoSuite
 * Mon Oct 16 02:33:40 GMT 2023
 */
package org.apache.commons.lang;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import org.apache.commons.lang.ClassUtils;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class ClassUtils_ESTest extends ClassUtils_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test010() throws Throwable {
        ClassUtils classUtils0 = new ClassUtils();
        LinkedList<String> linkedList0 = new LinkedList<String>();
        linkedList0.pollLast();
        ClassUtils.convertClassNamesToClasses(linkedList0);
        Class<Long> class0 = Long.class;
        Class<String> class1 = String.class;
        boolean boolean0 = true;
        Class<String>[] classArray0 = (Class<String>[]) Array.newInstance(Class.class, 5);
        classArray0[0] = class1;
        classArray0[1] = class1;
        classArray0[2] = class1;
        Class<String> class2 = String.class;
        classArray0[3] = class2;
        classArray0[4] = class1;
        Predicate<Object> predicate0 = Predicate.isEqual((Object) null);
        Predicate<Object> predicate1 = predicate0.and(predicate0);
        Predicate<Object> predicate2 = predicate1.or(predicate0);
        Predicate<Object> predicate3 = predicate2.negate();
        linkedList0.removeIf(predicate3);
        ClassUtils.convertClassNamesToClasses(linkedList0);
        ClassUtils.isAssignable(classArray0, classArray0);
        ClassUtils.isAssignable(class0, class1, true);
        ClassUtils.getShortCanonicalName("$");
        ClassLoader classLoader0 = ClassLoader.getSystemClassLoader();
        ClassUtils.getClass(classLoader0, ";", true);
        Class<ClassUtils> class3 = ClassUtils.class;
        ClassUtils.getPackageCanonicalName(class3);
    }

    @Test(timeout = 4000)
    public void test041() throws Throwable {
        ClassUtils.getShortCanonicalName("");
        Class<Double> class0 = Double.class;
        ClassUtils.getShortCanonicalName(class0);
        ClassUtils.getPackageName((Object) "", "Double");
        ClassUtils.getShortClassName(class0);
        Class<Integer>[] classArray0 = (Class<Integer>[]) Array.newInstance(Class.class, 8);
        Class<Integer> class1 = Integer.class;
        classArray0[0] = class1;
        Class<Integer> class2 = Integer.class;
        classArray0[1] = class2;
        Class<Integer> class3 = Integer.class;
        classArray0[2] = class3;
        Class<Integer> class4 = Integer.class;
        classArray0[3] = class4;
        Class<Integer> class5 = Integer.class;
        classArray0[4] = class5;
        Class<Integer> class6 = Integer.class;
        classArray0[5] = class6;
        Class<Integer> class7 = Integer.class;
        classArray0[6] = class7;
        Class<Integer> class8 = Integer.class;
        classArray0[7] = class8;
        ClassUtils.isAssignable(classArray0, classArray0);
        Class<Boolean> class9 = Boolean.class;
        ClassUtils.primitiveToWrapper(class9);
        ClassUtils.getClass("", false);
        ClassUtils.primitivesToWrappers(classArray0);
        byte byte0 = (byte) 0;
        Byte byte1 = null;
        byte1 = new Byte(byte0);
    }

    @Test(timeout = 4000)
    public void test172() throws Throwable {
        Class<?> class0 = ClassUtils.getClass("QrF>JH`");
        ClassUtils.getShortClassName(class0);
    }

    @Test(timeout = 4000)
    public void test213() throws Throwable {
        Class<Object> class0 = Object.class;
        ClassUtils.getPackageName(class0);
        Class<Double> class1 = Double.class;
        Class<Object>[] classArray0 = (Class<Object>[]) Array.newInstance(Class.class, 0);
        ClassUtils.getPublicMethod(class1, "java.lang", classArray0);
    }

    @Test(timeout = 4000)
    public void test254() throws Throwable {
        Class<String> class0 = String.class;
        ClassUtils.getShortClassName(class0);
        ClassUtils.getShortCanonicalName(class0);
        Class<ClassUtils> class1 = ClassUtils.class;
        ClassUtils.getAllInterfaces(class1);
        ClassUtils.getPackageCanonicalName(class1);
        Class<Long> class2 = Long.class;
        ClassUtils.getPackageName(class2);
        Class<Float> class3 = Float.class;
        ClassUtils.wrapperToPrimitive(class3);
        ClassLoader classLoader0 = ClassLoader.getSystemClassLoader();
        ClassUtils.getClass(classLoader0, "org.apache.commons.lang");
    }

    @Test(timeout = 4000)
    public void test315() throws Throwable {
        ClassUtils classUtils0 = new ClassUtils();
        ClassLoader classLoader0 = ClassLoader.getSystemClassLoader();
        ClassUtils.getClass(classLoader0, ".");
    }

    @Test(timeout = 4000)
    public void test326() throws Throwable {
        Class<Object> class0 = Object.class;
        String string0 = ClassUtils.getPackageCanonicalName(class0);
        ClassUtils.getClass((ClassLoader) null, "java.lang");
        ClassUtils.getPackageCanonicalName(string0);
    }

    @Test(timeout = 4000)
    public void test417() throws Throwable {
        String string0 = "5(LWR(*MX";
        ClassUtils.getShortCanonicalName((Object) null, "5(LWR(*MX");
        ClassLoader classLoader0 = ClassLoader.getSystemClassLoader();
        classLoader0.clearAssertionStatus();
        ClassLoader classLoader1 = classLoader0.getParent();
        boolean boolean0 = true;
        classLoader0.setPackageAssertionStatus("5(LWR(*MX", true);
        classLoader0.setClassAssertionStatus("", true);
        ClassLoader.getSystemClassLoader();
        classLoader1.setPackageAssertionStatus("5(LWR(*MX", true);
        ClassUtils.getClass(classLoader1, "5(LWR(*MX");
        String string1 = "J";
        classLoader1.setClassAssertionStatus(string1, boolean0);
    }

    @Test(timeout = 4000)
    public void test428() throws Throwable {
        ClassUtils classUtils0 = new ClassUtils();
        ClassLoader classLoader0 = ClassLoader.getSystemClassLoader();
        ClassUtils.getClass(classLoader0, (String) null, true);
        Class<Double> class0 = Double.class;
        ClassUtils.getAllSuperclasses(class0);
    }

    @Test(timeout = 4000)
    public void test489() throws Throwable {
        Object[] objectArray0 = new Object[4];
        Object object0 = new Object();
        objectArray0[0] = object0;
        Object object1 = new Object();
        objectArray0[1] = object1;
        Object object2 = new Object();
        objectArray0[2] = object2;
        Object object3 = new Object();
        objectArray0[3] = object3;
        Class<?>[] classArray0 = ClassUtils.toClass(objectArray0);
        Class<Boolean> class0 = Boolean.class;
        ClassUtils.getAllSuperclasses(class0);
        Class<?>[] classArray1 = ClassUtils.toClass(classArray0);
        ClassUtils.getShortClassName("ENlz#w5Z");
        ClassUtils.getPublicMethod(class0, (String) null, classArray1);
        String string0 = "";
        ClassUtils.getShortClassName(string0);
    }

    @Test(timeout = 4000)
    public void test6010() throws Throwable {
        Class<Byte>[] classArray0 = (Class<Byte>[]) Array.newInstance(Class.class, 6);
        Class<Byte> class0 = Byte.class;
        classArray0[0] = class0;
        Class<Byte> class1 = Byte.class;
        classArray0[1] = class1;
        Class<Byte> class2 = Byte.class;
        classArray0[2] = class2;
        Class<Byte> class3 = Byte.class;
        classArray0[3] = class3;
        Class<Byte> class4 = Byte.class;
        classArray0[4] = class4;
        Class<Byte> class5 = Byte.class;
        classArray0[5] = class5;
        ClassUtils.isAssignable(classArray0, classArray0, true);
        Class<Long> class6 = Long.class;
        ClassUtils.isAssignable(class0, class6);
        Class<ClassUtils> class7 = ClassUtils.class;
        ClassUtils.isAssignable(class0, class7);
        ClassUtils.getAllSuperclasses(class0);
        String string0 = null;
        ClassUtils.getShortClassName((String) null);
        ClassUtils.isInnerClass(class2);
        ClassUtils.getClass("", true);
        String string1 = "]$;)";
        ClassUtils.getShortCanonicalName(string1);
    }

    @Test(timeout = 4000)
    public void test6111() throws Throwable {
        ClassUtils classUtils0 = new ClassUtils();
        Class<Object> class0 = Object.class;
        List<Class<?>> list0 = ClassUtils.getAllSuperclasses(class0);
        ClassUtils.getPackageCanonicalName((Object) classUtils0, "$");
        Class<String> class1 = String.class;
        boolean boolean0 = true;
        List<String> list1 = ClassUtils.convertClassesToClassNames(list0);
        ClassUtils.convertClassNamesToClasses(list1);
        ClassLoader classLoader0 = null;
        ClassUtils.getClass((ClassLoader) null, ".", true);
    }

    @Test(timeout = 4000)
    public void test6412() throws Throwable {
        Object object0 = new Object();
        Object[] objectArray0 = new Object[8];
        objectArray0[3] = object0;
        objectArray0[4] = object0;
        objectArray0[7] = object0;
        ClassUtils.toClass(objectArray0);
    }

    @Test(timeout = 4000)
    public void test6713() throws Throwable {
        Class<Object> class0 = Object.class;
        ClassUtils.isInnerClass(class0);
        LinkedList<String> linkedList0 = new LinkedList<String>();
        linkedList0.add((String) null);
        LinkedList<String> linkedList1 = new LinkedList<String>(linkedList0);
        ClassUtils.convertClassNamesToClasses(linkedList1);
        ClassUtils.convertClassNamesToClasses((List<String>) null);
        ClassUtils.getShortClassName((Object) null, (String) null);
        Object[] objectArray0 = new Object[4];
        Object object0 = new Object();
        objectArray0[0] = object0;
        objectArray0[1] = (Object) null;
        objectArray0[2] = (Object) null;
        objectArray0[3] = (Object) null;
        ClassUtils.toClass(objectArray0);
        ClassUtils.getPackageCanonicalName(class0);
        ClassLoader classLoader0 = ClassLoader.getSystemClassLoader();
        classLoader0.getParent();
    }

    @Test(timeout = 4000)
    public void test7414() throws Throwable {
        Class<Byte>[] classArray0 = (Class<Byte>[]) Array.newInstance(Class.class, 6);
        Class<Byte> class0 = Byte.class;
        Class<Byte> class1 = Byte.class;
        classArray0[1] = class1;
        Class<Byte> class2 = Byte.class;
        classArray0[2] = class2;
        Class<Byte> class3 = Byte.class;
        classArray0[3] = class3;
        Class<Byte> class4 = Byte.class;
        classArray0[4] = class4;
        Class<Byte> class5 = Byte.class;
        classArray0[5] = class5;
        ClassUtils.isAssignable(classArray0, classArray0, true);
        Class<Long> class6 = Long.class;
        ClassUtils.isAssignable(class0, class6);
        Class<ClassUtils> class7 = ClassUtils.class;
        ClassUtils.isAssignable(classArray0[0], class7);
        ClassUtils.getAllSuperclasses(class0);
        String string0 = null;
        ClassUtils.getShortClassName((String) null);
        ClassUtils.isInnerClass(class2);
        ClassUtils.getClass("", true);
        String string1 = "]$;)";
        ClassUtils.getShortCanonicalName(string1);
    }

    @Test(timeout = 4000)
    public void test7915() throws Throwable {
        Class<Object> class0 = Object.class;
        ClassUtils.wrapperToPrimitive(class0);
        Class<Object> class1 = Object.class;
        ClassUtils.isInnerClass(class1);
        ClassUtils.getShortCanonicalName((Class<?>) null);
        ClassUtils.isInnerClass((Class<?>) null);
        ClassLoader classLoader0 = ClassLoader.getSystemClassLoader();
        ClassUtils.getClass(classLoader0, "");
    }
}
