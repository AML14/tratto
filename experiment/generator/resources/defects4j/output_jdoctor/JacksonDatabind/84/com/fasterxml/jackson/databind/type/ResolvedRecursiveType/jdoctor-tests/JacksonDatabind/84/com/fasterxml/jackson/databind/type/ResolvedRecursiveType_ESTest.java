/*
 * This file was automatically generated by EvoSuite
 * Thu Mar 28 07:07:28 GMT 2024
 */
package com.fasterxml.jackson.databind.type;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.ResolvedRecursiveType;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.fasterxml.jackson.databind.type.TypeBindings;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.util.List;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class ResolvedRecursiveType_ESTest extends ResolvedRecursiveType_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        Class<ResolvedRecursiveType> class0 = ResolvedRecursiveType.class;
        TypeBindings typeBindings0 = TypeBindings.create(class0, (List<JavaType>) null);
        Class<String> class1 = String.class;
        ResolvedRecursiveType resolvedRecursiveType0 = new ResolvedRecursiveType(class1, typeBindings0);
        JavaType javaType0 = resolvedRecursiveType0.withTypeHandler("");
        javaType0.isMapLikeType();
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        Class<Integer> class0 = Integer.class;
        TypeBindings typeBindings0 = TypeBindings.createIfNeeded(class0, (JavaType[]) null);
        ResolvedRecursiveType resolvedRecursiveType0 = new ResolvedRecursiveType(class0, typeBindings0);
        StringBuilder stringBuilder0 = new StringBuilder(1413);
        resolvedRecursiveType0.getGenericSignature(stringBuilder0);
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        Class<ResolvedRecursiveType> class0 = ResolvedRecursiveType.class;
        TypeBindings typeBindings0 = TypeFactory.EMPTY_BINDINGS;
        ResolvedRecursiveType resolvedRecursiveType0 = new ResolvedRecursiveType(class0, typeBindings0);
        ResolvedRecursiveType resolvedRecursiveType1 = new ResolvedRecursiveType(class0, typeBindings0);
        resolvedRecursiveType1.setReference(resolvedRecursiveType0);
        boolean boolean0 = resolvedRecursiveType1.equals(resolvedRecursiveType0);
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        Class<ResolvedRecursiveType> class0 = ResolvedRecursiveType.class;
        Class<String> class1 = String.class;
        TypeFactory typeFactory0 = TypeFactory.defaultInstance();
        SimpleType simpleType0 = new SimpleType(class1);
        ArrayType arrayType0 = ArrayType.construct((JavaType) simpleType0, (TypeBindings) null);
        CollectionLikeType collectionLikeType0 = typeFactory0.constructCollectionLikeType((Class<?>) class0, (JavaType) arrayType0);
        TypeBindings typeBindings0 = TypeBindings.createIfNeeded((Class<?>) class1, (JavaType) collectionLikeType0);
        ResolvedRecursiveType resolvedRecursiveType0 = new ResolvedRecursiveType(class0, typeBindings0);
        JavaType javaType0 = resolvedRecursiveType0.withContentType(collectionLikeType0);
        javaType0.isMapLikeType();
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        Class<Object> class0 = Object.class;
        TypeBindings typeBindings0 = TypeBindings.emptyBindings();
        ResolvedRecursiveType resolvedRecursiveType0 = new ResolvedRecursiveType(class0, typeBindings0);
        boolean boolean0 = resolvedRecursiveType0.isContainerType();
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        Class<Integer> class0 = Integer.class;
        TypeBindings typeBindings0 = TypeBindings.createIfNeeded(class0, (JavaType) null);
        ResolvedRecursiveType resolvedRecursiveType0 = new ResolvedRecursiveType(class0, typeBindings0);
        JavaType javaType0 = resolvedRecursiveType0.withContentTypeHandler(typeBindings0);
        javaType0.isInterface();
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        Class<ResolvedRecursiveType> class0 = ResolvedRecursiveType.class;
        TypeBindings typeBindings0 = TypeFactory.EMPTY_BINDINGS;
        ResolvedRecursiveType resolvedRecursiveType0 = new ResolvedRecursiveType(class0, typeBindings0);
        resolvedRecursiveType0.getErasedSignature();
    }

    @Test(timeout = 4000)
    public void test077() throws Throwable {
        Class<Integer> class0 = Integer.class;
        TypeBindings typeBindings0 = TypeBindings.createIfNeeded(class0, (JavaType[]) null);
        ResolvedRecursiveType resolvedRecursiveType0 = new ResolvedRecursiveType(class0, typeBindings0);
        JavaType javaType0 = resolvedRecursiveType0.withValueHandler("Can not add mapping from class ");
        javaType0.useStaticType();
    }

    @Test(timeout = 4000)
    public void test088() throws Throwable {
        Class<Integer> class0 = Integer.class;
        TypeBindings typeBindings0 = TypeBindings.createIfNeeded(class0, (JavaType[]) null);
        ResolvedRecursiveType resolvedRecursiveType0 = new ResolvedRecursiveType(class0, typeBindings0);
        JavaType[] javaTypeArray0 = new JavaType[7];
        JavaType javaType0 = resolvedRecursiveType0.refine(class0, typeBindings0, (JavaType) null, javaTypeArray0);
    }

    @Test(timeout = 4000)
    public void test099() throws Throwable {
        Class<Integer> class0 = Integer.class;
        TypeBindings typeBindings0 = TypeBindings.createIfNeeded(class0, (JavaType[]) null);
        ResolvedRecursiveType resolvedRecursiveType0 = new ResolvedRecursiveType(class0, typeBindings0);
        JavaType[] javaTypeArray0 = new JavaType[7];
        javaTypeArray0[4] = (JavaType) resolvedRecursiveType0;
        CollectionType collectionType0 = CollectionType.construct(class0, typeBindings0, javaTypeArray0[3], javaTypeArray0, javaTypeArray0[4]);
        CollectionType collectionType1 = collectionType0.withStaticTyping();
        collectionType1.isCollectionLikeType();
    }

    @Test(timeout = 4000)
    public void test1010() throws Throwable {
        Class<Integer> class0 = Integer.class;
        TypeBindings typeBindings0 = TypeBindings.createIfNeeded(class0, (JavaType[]) null);
        ResolvedRecursiveType resolvedRecursiveType0 = new ResolvedRecursiveType(class0, typeBindings0);
        JavaType javaType0 = resolvedRecursiveType0.withContentValueHandler((Object) null);
        javaType0.isCollectionLikeType();
    }

    @Test(timeout = 4000)
    public void test1111() throws Throwable {
        Class<ResolvedRecursiveType> class0 = ResolvedRecursiveType.class;
        TypeBindings typeBindings0 = TypeFactory.EMPTY_BINDINGS;
        ResolvedRecursiveType resolvedRecursiveType0 = new ResolvedRecursiveType(class0, typeBindings0);
        JavaType javaType0 = resolvedRecursiveType0._narrow(class0);
        javaType0.hasHandlers();
    }

    @Test(timeout = 4000)
    public void test1212() throws Throwable {
        Class<ResolvedRecursiveType> class0 = ResolvedRecursiveType.class;
        TypeBindings typeBindings0 = TypeFactory.EMPTY_BINDINGS;
        ResolvedRecursiveType resolvedRecursiveType0 = new ResolvedRecursiveType(class0, typeBindings0);
        resolvedRecursiveType0.setReference(resolvedRecursiveType0);
        ResolvedRecursiveType resolvedRecursiveType1 = new ResolvedRecursiveType(class0, typeBindings0);
        resolvedRecursiveType0.setReference(resolvedRecursiveType1);
    }

    @Test(timeout = 4000)
    public void test1313() throws Throwable {
        Class<ResolvedRecursiveType> class0 = ResolvedRecursiveType.class;
        TypeBindings typeBindings0 = TypeFactory.EMPTY_BINDINGS;
        ResolvedRecursiveType resolvedRecursiveType0 = new ResolvedRecursiveType(class0, typeBindings0);
        boolean boolean0 = resolvedRecursiveType0.equals(typeBindings0);
    }

    @Test(timeout = 4000)
    public void test1414() throws Throwable {
        Class<ResolvedRecursiveType> class0 = ResolvedRecursiveType.class;
        TypeBindings typeBindings0 = TypeBindings.create(class0, (List<JavaType>) null);
        Class<String> class1 = String.class;
        ResolvedRecursiveType resolvedRecursiveType0 = new ResolvedRecursiveType(class1, typeBindings0);
        resolvedRecursiveType0.setReference(resolvedRecursiveType0);
        boolean boolean0 = resolvedRecursiveType0.equals("UNRESOLVED");
    }

    @Test(timeout = 4000)
    public void test1515() throws Throwable {
        Class<ResolvedRecursiveType> class0 = ResolvedRecursiveType.class;
        TypeBindings typeBindings0 = TypeFactory.EMPTY_BINDINGS;
        ResolvedRecursiveType resolvedRecursiveType0 = new ResolvedRecursiveType(class0, typeBindings0);
        resolvedRecursiveType0.setReference(resolvedRecursiveType0);
        ResolvedRecursiveType resolvedRecursiveType1 = new ResolvedRecursiveType(class0, typeBindings0);
        resolvedRecursiveType1.equals((Object) resolvedRecursiveType0);
    }

    @Test(timeout = 4000)
    public void test1516() throws Throwable {
        Class<ResolvedRecursiveType> class0 = ResolvedRecursiveType.class;
        TypeBindings typeBindings0 = TypeFactory.EMPTY_BINDINGS;
        ResolvedRecursiveType resolvedRecursiveType0 = new ResolvedRecursiveType(class0, typeBindings0);
        resolvedRecursiveType0.setReference(resolvedRecursiveType0);
        ResolvedRecursiveType resolvedRecursiveType1 = new ResolvedRecursiveType(class0, typeBindings0);
        resolvedRecursiveType1.setReference(resolvedRecursiveType0);
        boolean boolean0 = resolvedRecursiveType1.equals(resolvedRecursiveType0);
        resolvedRecursiveType1.equals((Object) resolvedRecursiveType0);
    }

    @Test(timeout = 4000)
    public void test1517() throws Throwable {
        Class<ResolvedRecursiveType> class0 = ResolvedRecursiveType.class;
        TypeBindings typeBindings0 = TypeFactory.EMPTY_BINDINGS;
        ResolvedRecursiveType resolvedRecursiveType0 = new ResolvedRecursiveType(class0, typeBindings0);
        resolvedRecursiveType0.setReference(resolvedRecursiveType0);
        ResolvedRecursiveType resolvedRecursiveType1 = new ResolvedRecursiveType(class0, typeBindings0);
        resolvedRecursiveType1.setReference(resolvedRecursiveType0);
        boolean boolean0 = resolvedRecursiveType1.equals(resolvedRecursiveType0);
    }
}
