/*
 * This file was automatically generated by EvoSuite
 * Thu Mar 28 06:59:10 GMT 2024
 */
package com.fasterxml.jackson.databind.jsontype.impl;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.impl.StdSubtypeResolver;
import java.lang.reflect.Array;
import java.util.HashMap;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class StdSubtypeResolver_ESTest extends StdSubtypeResolver_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test00() throws Throwable {
        StdSubtypeResolver stdSubtypeResolver0 = new StdSubtypeResolver();
        Class<NamedType>[] classArray0 = (Class<NamedType>[]) Array.newInstance(Class.class, 0);
        stdSubtypeResolver0.registerSubtypes(classArray0);
        NamedType[] namedTypeArray0 = new NamedType[1];
        stdSubtypeResolver0.registerSubtypes(namedTypeArray0);
    }

    @Test(timeout = 4000)
    public void test11() throws Throwable {
        StdSubtypeResolver stdSubtypeResolver0 = new StdSubtypeResolver();
        Class<NamedType>[] classArray0 = (Class<NamedType>[]) Array.newInstance(Class.class, 1);
        stdSubtypeResolver0.registerSubtypes(classArray0);
    }

    @Test(timeout = 4000)
    public void test22() throws Throwable {
        HashMap<NamedType, NamedType> hashMap0 = new HashMap<NamedType, NamedType>();
        StdSubtypeResolver stdSubtypeResolver0 = new StdSubtypeResolver();
        AnnotationIntrospector annotationIntrospector0 = AnnotationIntrospector.nopInstance();
        Class<Integer> class0 = Integer.class;
        NamedType namedType0 = new NamedType(class0, "u}0f_qu:k;>>sz<Gf");
        stdSubtypeResolver0._collectAndResolve((AnnotatedClass) null, namedType0, (MapperConfig<?>) null, annotationIntrospector0, hashMap0);
        stdSubtypeResolver0._collectAndResolve((AnnotatedClass) null, namedType0, (MapperConfig<?>) null, annotationIntrospector0, hashMap0);
        namedType0.getName();
    }

    @Test(timeout = 4000)
    public void test33() throws Throwable {
        StdSubtypeResolver stdSubtypeResolver0 = new StdSubtypeResolver();
        HashMap<NamedType, NamedType> hashMap0 = new HashMap<NamedType, NamedType>();
        Class<Integer> class0 = Integer.TYPE;
        NamedType namedType0 = new NamedType(class0);
        hashMap0.putIfAbsent(namedType0, namedType0);
        AnnotationIntrospector annotationIntrospector0 = AnnotationIntrospector.nopInstance();
        stdSubtypeResolver0._collectAndResolve((AnnotatedClass) null, namedType0, (MapperConfig<?>) null, annotationIntrospector0, hashMap0);
        namedType0.getName();
    }

    @Test(timeout = 4000)
    public void test44() throws Throwable {
        StdSubtypeResolver stdSubtypeResolver0 = new StdSubtypeResolver();
        AnnotationIntrospector annotationIntrospector0 = AnnotationIntrospector.nopInstance();
        Class<JavaType> class0 = JavaType.class;
        NamedType namedType0 = new NamedType(class0);
        HashMap<NamedType, NamedType> hashMap0 = new HashMap<NamedType, NamedType>();
        hashMap0.put(namedType0, namedType0);
        NamedType namedType1 = new NamedType(class0, "QW");
        stdSubtypeResolver0._collectAndResolve((AnnotatedClass) null, namedType1, (MapperConfig<?>) null, annotationIntrospector0, hashMap0);
        namedType1.equals((Object) namedType0);
    }
}
