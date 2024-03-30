/*
 * This file was automatically generated by EvoSuite
 * Thu Mar 28 07:23:45 GMT 2024
 */
package com.fasterxml.jackson.databind.deser.std;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.deser.std.StdValueInstantiator;
import com.fasterxml.jackson.databind.introspect.BasicBeanDescription;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.impl.MinimalClassNameIdResolver;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.ReferenceType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.lang.reflect.InvocationTargetException;
import java.sql.ClientInfoStatus;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.SQLNonTransientConnectionException;
import java.sql.SQLTimeoutException;
import java.sql.SQLTransactionRollbackException;
import java.util.HashMap;
import java.util.HashSet;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class StdValueInstantiator_ESTest extends StdValueInstantiator_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        JavaType javaType0 = TypeFactory.unknownType();
        ReferenceType referenceType0 = ReferenceType.upgradeFrom(javaType0, javaType0);
        StdValueInstantiator stdValueInstantiator0 = new StdValueInstantiator((DeserializationConfig) null, referenceType0);
        stdValueInstantiator0.getDelegateType((DeserializationConfig) null);
        stdValueInstantiator0.getValueTypeDesc();
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        Class<ObjectMapper.DefaultTypeResolverBuilder> class0 = ObjectMapper.DefaultTypeResolverBuilder.class;
        StdValueInstantiator stdValueInstantiator0 = new StdValueInstantiator((DeserializationConfig) null, class0);
        stdValueInstantiator0.canCreateUsingArrayDelegate();
        assertFalse(stdValueInstantiator0.canCreateUsingArrayDelegate());
    }

    @Test(timeout = 4000)
    public void test012() throws Throwable {
        Class<ObjectMapper.DefaultTypeResolverBuilder> class0 = ObjectMapper.DefaultTypeResolverBuilder.class;
        StdValueInstantiator stdValueInstantiator0 = new StdValueInstantiator((DeserializationConfig) null, class0);
        ObjectMapper objectMapper0 = new ObjectMapper();
        objectMapper0.readerForUpdating(stdValueInstantiator0);
        stdValueInstantiator0.getValueTypeDesc();
        assertNotNull(stdValueInstantiator0.getValueTypeDesc());
    }

    @Test(timeout = 4000)
    public void test013() throws Throwable {
        Class<ObjectMapper.DefaultTypeResolverBuilder> class0 = ObjectMapper.DefaultTypeResolverBuilder.class;
        StdValueInstantiator stdValueInstantiator0 = new StdValueInstantiator((DeserializationConfig) null, class0);
        ObjectMapper objectMapper0 = new ObjectMapper();
        objectMapper0.readerForUpdating(stdValueInstantiator0);
        stdValueInstantiator0.canInstantiate();
    }

    @Test(timeout = 4000)
    public void test024() throws Throwable {
        Class<String> class0 = String.class;
        StdValueInstantiator stdValueInstantiator0 = new StdValueInstantiator((DeserializationConfig) null, class0);
        stdValueInstantiator0.createUsingArrayDelegate((DeserializationContext) null, (Object) null);
    }

    @Test(timeout = 4000)
    public void test035() throws Throwable {
        Class<Module> class0 = Module.class;
        StdValueInstantiator stdValueInstantiator0 = new StdValueInstantiator((DeserializationConfig) null, class0);
        stdValueInstantiator0.getDelegateCreator();
        stdValueInstantiator0.getValueTypeDesc();
        assertNotNull(stdValueInstantiator0.getValueTypeDesc());
    }

    @Test(timeout = 4000)
    public void test046() throws Throwable {
        Class<Integer> class0 = Integer.TYPE;
        StdValueInstantiator stdValueInstantiator0 = new StdValueInstantiator((DeserializationConfig) null, class0);
        stdValueInstantiator0.getArrayDelegateCreator();
        stdValueInstantiator0.getValueTypeDesc();
        assertNotNull(stdValueInstantiator0.getValueTypeDesc());
    }

    @Test(timeout = 4000)
    public void test057() throws Throwable {
        Class<MinimalClassNameIdResolver> class0 = MinimalClassNameIdResolver.class;
        StdValueInstantiator stdValueInstantiator0 = new StdValueInstantiator((DeserializationConfig) null, class0);
        stdValueInstantiator0.createUsingDefault((DeserializationContext) null);
    }

    @Test(timeout = 4000)
    public void test068() throws Throwable {
        StdValueInstantiator stdValueInstantiator0 = null;
        stdValueInstantiator0 = new StdValueInstantiator((StdValueInstantiator) null);
    }

    @Test(timeout = 4000)
    public void test079() throws Throwable {
        JavaType javaType0 = TypeFactory.unknownType();
        ReferenceType referenceType0 = ReferenceType.upgradeFrom(javaType0, javaType0);
        StdValueInstantiator stdValueInstantiator0 = new StdValueInstantiator((DeserializationConfig) null, referenceType0);
        stdValueInstantiator0.getArrayDelegateType((DeserializationConfig) null);
        stdValueInstantiator0.getValueTypeDesc();
    }

    @Test(timeout = 4000)
    public void test0810() throws Throwable {
        Class<JsonDeserializer> class0 = JsonDeserializer.class;
        StdValueInstantiator stdValueInstantiator0 = new StdValueInstantiator((DeserializationConfig) null, class0);
        stdValueInstantiator0.getDefaultCreator();
        stdValueInstantiator0.getValueTypeDesc();
        assertNotNull(stdValueInstantiator0.getValueTypeDesc());
    }

    @Test(timeout = 4000)
    public void test0911() throws Throwable {
        Class<Module> class0 = Module.class;
        StdValueInstantiator stdValueInstantiator0 = new StdValueInstantiator((DeserializationConfig) null, class0);
        stdValueInstantiator0.getWithArgsCreator();
        stdValueInstantiator0.getValueTypeDesc();
        assertNotNull(stdValueInstantiator0.getValueTypeDesc());
    }

    @Test(timeout = 4000)
    public void test1012() throws Throwable {
        StdValueInstantiator stdValueInstantiator0 = new StdValueInstantiator((DeserializationConfig) null, (Class<?>) null);
        stdValueInstantiator0.getValueTypeDesc();
        assertNotNull(stdValueInstantiator0.getValueTypeDesc());
    }

    @Test(timeout = 4000)
    public void test1113() throws Throwable {
        StdValueInstantiator stdValueInstantiator0 = new StdValueInstantiator((DeserializationConfig) null, (JavaType) null);
        stdValueInstantiator0.getValueTypeDesc();
        assertNotNull(stdValueInstantiator0.getValueTypeDesc());
    }

    @Test(timeout = 4000)
    public void test1214() throws Throwable {
        JavaType javaType0 = TypeFactory.unknownType();
        ObjectMapper objectMapper0 = new ObjectMapper();
        ObjectReader objectReader0 = objectMapper0.readerFor(javaType0);
        assertNotNull(objectReader0);
    }

    @Test(timeout = 4000)
    public void test1315() throws Throwable {
        Class<ClientInfoStatus> class0 = ClientInfoStatus.class;
        StdValueInstantiator stdValueInstantiator0 = new StdValueInstantiator((DeserializationConfig) null, class0);
        TypeFactory typeFactory0 = TypeFactory.defaultInstance();
        Class<HashSet> class1 = HashSet.class;
        CollectionType collectionType0 = typeFactory0.constructRawCollectionType(class1);
        stdValueInstantiator0._delegateType = (JavaType) collectionType0;
        boolean boolean0 = stdValueInstantiator0.canInstantiate();
        stdValueInstantiator0.canCreateUsingDelegate();
        assertTrue(stdValueInstantiator0.canCreateUsingDelegate());
    }

    @Test(timeout = 4000)
    public void test1316() throws Throwable {
        Class<ClientInfoStatus> class0 = ClientInfoStatus.class;
        StdValueInstantiator stdValueInstantiator0 = new StdValueInstantiator((DeserializationConfig) null, class0);
        TypeFactory typeFactory0 = TypeFactory.defaultInstance();
        Class<HashSet> class1 = HashSet.class;
        CollectionType collectionType0 = typeFactory0.constructRawCollectionType(class1);
        stdValueInstantiator0._delegateType = (JavaType) collectionType0;
        boolean boolean0 = stdValueInstantiator0.canInstantiate();
    }

    @Test(timeout = 4000)
    public void test1417() throws Throwable {
        Class<JsonMappingException> class0 = JsonMappingException.class;
        StdValueInstantiator stdValueInstantiator0 = new StdValueInstantiator((DeserializationConfig) null, class0);
        TypeFactory typeFactory0 = TypeFactory.defaultInstance();
        Class<HashMap> class1 = HashMap.class;
        MapType mapType0 = typeFactory0.constructMapType(class1, class0, class0);
        stdValueInstantiator0._arrayDelegateType = (JavaType) mapType0;
        boolean boolean0 = stdValueInstantiator0.canCreateUsingArrayDelegate();
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test1518() throws Throwable {
        Class<Integer> class0 = Integer.TYPE;
        StdValueInstantiator stdValueInstantiator0 = new StdValueInstantiator((DeserializationConfig) null, class0);
        stdValueInstantiator0.createFromObjectWith((DeserializationContext) null, (Object[]) null);
    }

    @Test(timeout = 4000)
    public void test1619() throws Throwable {
        Class<Integer> class0 = Integer.TYPE;
        StdValueInstantiator stdValueInstantiator0 = new StdValueInstantiator((DeserializationConfig) null, class0);
        stdValueInstantiator0.createUsingDelegate((DeserializationContext) null, class0);
    }

    @Test(timeout = 4000)
    public void test1720() throws Throwable {
        Class<Integer> class0 = Integer.TYPE;
        StdValueInstantiator stdValueInstantiator0 = new StdValueInstantiator((DeserializationConfig) null, class0);
        stdValueInstantiator0.createFromString((DeserializationContext) null, "49?9O$u;qv@%");
    }

    @Test(timeout = 4000)
    public void test1821() throws Throwable {
        Class<Integer> class0 = Integer.TYPE;
        StdValueInstantiator stdValueInstantiator0 = new StdValueInstantiator((DeserializationConfig) null, class0);
        stdValueInstantiator0.createFromInt((DeserializationContext) null, (-941));
    }

    @Test(timeout = 4000)
    public void test1922() throws Throwable {
        Class<Integer> class0 = Integer.TYPE;
        StdValueInstantiator stdValueInstantiator0 = new StdValueInstantiator((DeserializationConfig) null, class0);
        stdValueInstantiator0.createFromLong((DeserializationContext) null, 0L);
    }

    @Test(timeout = 4000)
    public void test2023() throws Throwable {
        Class<Integer> class0 = Integer.TYPE;
        StdValueInstantiator stdValueInstantiator0 = new StdValueInstantiator((DeserializationConfig) null, class0);
        stdValueInstantiator0.createFromDouble((DeserializationContext) null, (-504.571942));
    }

    @Test(timeout = 4000)
    public void test2124() throws Throwable {
        Class<Integer> class0 = Integer.TYPE;
        StdValueInstantiator stdValueInstantiator0 = new StdValueInstantiator((DeserializationConfig) null, class0);
        stdValueInstantiator0.createFromBoolean((DeserializationContext) null, false);
    }

    @Test(timeout = 4000)
    public void test2225() throws Throwable {
        Class<MinimalClassNameIdResolver> class0 = MinimalClassNameIdResolver.class;
        StdValueInstantiator stdValueInstantiator0 = new StdValueInstantiator((DeserializationConfig) null, class0);
        SQLTimeoutException sQLTimeoutException0 = new SQLTimeoutException("@[Ct@:3V85X?&oEQ", "");
        JsonMappingException jsonMappingException0 = stdValueInstantiator0.wrapException(sQLTimeoutException0);
        stdValueInstantiator0.unwrapAndWrapException((DeserializationContext) null, jsonMappingException0);
        stdValueInstantiator0.getValueTypeDesc();
        assertNotNull(stdValueInstantiator0.getValueTypeDesc());
    }

    @Test(timeout = 4000)
    public void test2326() throws Throwable {
        Class<Integer> class0 = Integer.TYPE;
        StdValueInstantiator stdValueInstantiator0 = new StdValueInstantiator((DeserializationConfig) null, class0);
        SQLFeatureNotSupportedException sQLFeatureNotSupportedException0 = new SQLFeatureNotSupportedException("");
        JsonMappingException.Reference jsonMappingException_Reference0 = new JsonMappingException.Reference(stdValueInstantiator0, "");
        JsonMappingException jsonMappingException0 = JsonMappingException.wrapWithPath((Throwable) sQLFeatureNotSupportedException0, jsonMappingException_Reference0);
        JsonMappingException jsonMappingException1 = stdValueInstantiator0.wrapException(jsonMappingException0);
        stdValueInstantiator0.getValueTypeDesc();
        assertNotNull(stdValueInstantiator0.getValueTypeDesc());
    }

    @Test(timeout = 4000)
    public void test2327() throws Throwable {
        Class<Integer> class0 = Integer.TYPE;
        StdValueInstantiator stdValueInstantiator0 = new StdValueInstantiator((DeserializationConfig) null, class0);
        SQLFeatureNotSupportedException sQLFeatureNotSupportedException0 = new SQLFeatureNotSupportedException("");
        JsonMappingException.Reference jsonMappingException_Reference0 = new JsonMappingException.Reference(stdValueInstantiator0, "");
        JsonMappingException jsonMappingException0 = JsonMappingException.wrapWithPath((Throwable) sQLFeatureNotSupportedException0, jsonMappingException_Reference0);
        JsonMappingException jsonMappingException1 = stdValueInstantiator0.wrapException(jsonMappingException0);
    }

    @Test(timeout = 4000)
    public void test2428() throws Throwable {
        Class<JsonMappingException> class0 = JsonMappingException.class;
        StdValueInstantiator stdValueInstantiator0 = new StdValueInstantiator((DeserializationConfig) null, class0);
        SQLTransactionRollbackException sQLTransactionRollbackException0 = new SQLTransactionRollbackException();
        stdValueInstantiator0.unwrapAndWrapException((DeserializationContext) null, sQLTransactionRollbackException0);
    }

    @Test(timeout = 4000)
    public void test2529() throws Throwable {
        Class<NamedType> class0 = NamedType.class;
        StdValueInstantiator stdValueInstantiator0 = new StdValueInstantiator((DeserializationConfig) null, class0);
        SQLNonTransientConnectionException sQLNonTransientConnectionException0 = new SQLNonTransientConnectionException("b><EK^rFo^BKLB-P", "b><EK^rFo^BKLB-P");
        InvocationTargetException invocationTargetException0 = new InvocationTargetException(sQLNonTransientConnectionException0);
        stdValueInstantiator0.rewrapCtorProblem((DeserializationContext) null, invocationTargetException0);
    }

    @Test(timeout = 4000)
    public void test2630() throws Throwable {
        Class<BasicBeanDescription> class0 = BasicBeanDescription.class;
        StdValueInstantiator stdValueInstantiator0 = new StdValueInstantiator((DeserializationConfig) null, class0);
        SQLTransactionRollbackException sQLTransactionRollbackException0 = new SQLTransactionRollbackException();
        JsonMappingException.Reference jsonMappingException_Reference0 = new JsonMappingException.Reference(stdValueInstantiator0, 2901);
        JsonMappingException jsonMappingException0 = JsonMappingException.wrapWithPath((Throwable) sQLTransactionRollbackException0, jsonMappingException_Reference0);
        stdValueInstantiator0.rewrapCtorProblem((DeserializationContext) null, jsonMappingException0);
        stdValueInstantiator0.getValueTypeDesc();
        assertNotNull(stdValueInstantiator0.getValueTypeDesc());
    }

    @Test(timeout = 4000)
    public void test2731() throws Throwable {
        Class<Module> class0 = Module.class;
        StdValueInstantiator stdValueInstantiator0 = new StdValueInstantiator((DeserializationConfig) null, class0);
        ExceptionInInitializerError exceptionInInitializerError0 = new ExceptionInInitializerError();
        stdValueInstantiator0.rewrapCtorProblem((DeserializationContext) null, exceptionInInitializerError0);
    }
}
