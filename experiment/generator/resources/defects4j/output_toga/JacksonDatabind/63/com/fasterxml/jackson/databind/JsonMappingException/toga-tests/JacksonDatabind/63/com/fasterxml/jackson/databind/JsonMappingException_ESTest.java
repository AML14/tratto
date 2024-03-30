/*
 * This file was automatically generated by EvoSuite
 * Thu Mar 28 05:35:57 GMT 2024
 */
package com.fasterxml.jackson.databind;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.json.ReaderBasedJsonParser;
import com.fasterxml.jackson.core.util.JsonParserSequence;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.BeanDeserializerFactory;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import java.io.PipedInputStream;
import java.sql.SQLDataException;
import java.sql.SQLNonTransientException;
import java.sql.SQLSyntaxErrorException;
import java.sql.SQLTransactionRollbackException;
import java.sql.SQLTransientConnectionException;
import java.util.List;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.mock.java.io.MockIOException;
import org.evosuite.runtime.mock.java.io.MockPrintWriter;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class JsonMappingException_ESTest extends JsonMappingException_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        JsonMappingException.Reference jsonMappingException_Reference0 = new JsonMappingException.Reference();
        jsonMappingException_Reference0.setIndex(1000);
        jsonMappingException_Reference0.getIndex();
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        JsonMappingException.Reference jsonMappingException_Reference0 = new JsonMappingException.Reference();
        JsonMappingException.Reference jsonMappingException_Reference1 = (JsonMappingException.Reference) jsonMappingException_Reference0.writeReplace();
        jsonMappingException_Reference1.getIndex();
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        JsonMappingException.Reference jsonMappingException_Reference0 = new JsonMappingException.Reference();
        jsonMappingException_Reference0.getFrom();
        jsonMappingException_Reference0.getIndex();
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        JsonMappingException.Reference jsonMappingException_Reference0 = new JsonMappingException.Reference();
        jsonMappingException_Reference0.setFieldName(" YRo>kT*");
        jsonMappingException_Reference0.getIndex();
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        PipedInputStream pipedInputStream0 = new PipedInputStream();
        JsonMappingException.Reference jsonMappingException_Reference0 = new JsonMappingException.Reference(pipedInputStream0);
        jsonMappingException_Reference0.getIndex();
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        SQLDataException sQLDataException0 = new SQLDataException();
        JsonMappingException jsonMappingException0 = JsonMappingException.wrapWithPath((Throwable) sQLDataException0, (Object) sQLDataException0, 87);
        String string0 = jsonMappingException0.toString();
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        JsonMappingException.Reference jsonMappingException_Reference0 = new JsonMappingException.Reference();
        jsonMappingException_Reference0.setDescription("qrx6}a\"Dp:rH@4)");
        jsonMappingException_Reference0.getIndex();
    }

    @Test(timeout = 4000)
    public void test077() throws Throwable {
        JsonMappingException.Reference jsonMappingException_Reference0 = new JsonMappingException.Reference("com.fasterxml.jackson.aaind.util.RootdameLookup", "QZN9yGcW7");
        String string0 = jsonMappingException_Reference0.getFieldName();
        jsonMappingException_Reference0.getIndex();
    }

    @Test(timeout = 4000)
    public void test078() throws Throwable {
        JsonMappingException.Reference jsonMappingException_Reference0 = new JsonMappingException.Reference("com.fasterxml.jackson.aaind.util.RootdameLookup", "QZN9yGcW7");
        String string0 = jsonMappingException_Reference0.getFieldName();
    }

    @Test(timeout = 4000)
    public void test089() throws Throwable {
        JsonMappingException.Reference jsonMappingException_Reference0 = new JsonMappingException.Reference();
        int int0 = jsonMappingException_Reference0.getIndex();
    }

    @Test(timeout = 4000)
    public void test0910() throws Throwable {
        JsonMappingException jsonMappingException0 = new JsonMappingException("FY");
        String string0 = jsonMappingException0.getPathReference();
        assertNotNull(string0);
    }

    @Test(timeout = 4000)
    public void test1011() throws Throwable {
        SQLDataException sQLDataException0 = new SQLDataException();
        JsonMappingException jsonMappingException0 = JsonMappingException.wrapWithPath((Throwable) sQLDataException0, (Object) sQLDataException0, 4088);
        jsonMappingException0.prependPath((Object) sQLDataException0, 4088);
        sQLDataException0.toString();
    }

    @Test(timeout = 4000)
    public void test1112() throws Throwable {
        JsonLocation jsonLocation0 = JsonLocation.NA;
        SQLTransientConnectionException sQLTransientConnectionException0 = new SQLTransientConnectionException();
        JsonMappingException jsonMappingException0 = new JsonMappingException("6Xmsi96ju8J5wDz", jsonLocation0, sQLTransientConnectionException0);
    }

    @Test(timeout = 4000)
    public void test1213() throws Throwable {
        DefaultSerializerProvider.Impl defaultSerializerProvider_Impl0 = new DefaultSerializerProvider.Impl();
        JsonMappingException jsonMappingException0 = defaultSerializerProvider_Impl0.mappingException("", (Object[]) null);
    }

    @Test(timeout = 4000)
    public void test1314() throws Throwable {
        SQLSyntaxErrorException sQLSyntaxErrorException0 = new SQLSyntaxErrorException("'h>JzHfY$F{");
        JsonMappingException.from((DeserializationContext) null, "'h>JzHfY$F{", (Throwable) sQLSyntaxErrorException0);
    }

    @Test(timeout = 4000)
    public void test1415() throws Throwable {
        BeanDeserializerFactory beanDeserializerFactory0 = BeanDeserializerFactory.instance;
        DefaultDeserializationContext.Impl defaultDeserializationContext_Impl0 = new DefaultDeserializationContext.Impl(beanDeserializerFactory0);
        JsonMappingException jsonMappingException0 = JsonMappingException.from((DeserializationContext) defaultDeserializationContext_Impl0, "bRb{m");
    }

    @Test(timeout = 4000)
    public void test1516() throws Throwable {
        SQLDataException sQLDataException0 = new SQLDataException();
        JsonMappingException jsonMappingException0 = JsonMappingException.wrapWithPath((Throwable) sQLDataException0, (Object) sQLDataException0, (-1));
        Object object0 = jsonMappingException0.getProcessor();
    }

    @Test(timeout = 4000)
    public void test1617() throws Throwable {
        SQLDataException sQLDataException0 = new SQLDataException();
        JsonMappingException jsonMappingException0 = JsonMappingException.from((JsonGenerator) null, "F", (Throwable) sQLDataException0);
    }

    @Test(timeout = 4000)
    public void test1718() throws Throwable {
        DefaultSerializerProvider.Impl defaultSerializerProvider_Impl0 = new DefaultSerializerProvider.Impl();
        JsonMappingException jsonMappingException0 = JsonMappingException.from((SerializerProvider) defaultSerializerProvider_Impl0, "Q");
    }

    @Test(timeout = 4000)
    public void test1819() throws Throwable {
        DefaultSerializerProvider.Impl defaultSerializerProvider_Impl0 = new DefaultSerializerProvider.Impl();
        SQLNonTransientException sQLNonTransientException0 = new SQLNonTransientException("~8Z");
        JsonMappingException jsonMappingException0 = JsonMappingException.from((SerializerProvider) defaultSerializerProvider_Impl0, "~8Z", (Throwable) sQLNonTransientException0);
    }

    @Test(timeout = 4000)
    public void test1920() throws Throwable {
        SQLDataException sQLDataException0 = new SQLDataException();
        JsonMappingException jsonMappingException0 = JsonMappingException.wrapWithPath((Throwable) sQLDataException0, (Object) sQLDataException0, "f!8");
        jsonMappingException0.prependPath((Object) "f!8", "f!8");
        String string0 = jsonMappingException0._buildMessage();
    }

    @Test(timeout = 4000)
    public void test2021() throws Throwable {
        SQLDataException sQLDataException0 = new SQLDataException();
        JsonMappingException jsonMappingException0 = new JsonMappingException("", sQLDataException0);
    }

    @Test(timeout = 4000)
    public void test2122() throws Throwable {
        JsonLocation jsonLocation0 = JsonLocation.NA;
        JsonMappingException jsonMappingException0 = new JsonMappingException("", jsonLocation0);
    }

    @Test(timeout = 4000)
    public void test2223() throws Throwable {
        MockPrintWriter mockPrintWriter0 = new MockPrintWriter("*s");
        JsonMappingException jsonMappingException0 = new JsonMappingException(mockPrintWriter0, "*s", (JsonLocation) null);
    }

    @Test(timeout = 4000)
    public void test2324() throws Throwable {
        JsonMappingException jsonMappingException0 = JsonMappingException.from((JsonParser) null, "");
    }

    @Test(timeout = 4000)
    public void test2425() throws Throwable {
        JsonMappingException jsonMappingException0 = new JsonMappingException("");
        String string0 = jsonMappingException0.getLocalizedMessage();
    }

    @Test(timeout = 4000)
    public void test2526() throws Throwable {
        JsonMappingException jsonMappingException0 = new JsonMappingException(")");
        JsonMappingException jsonMappingException1 = JsonMappingException.fromUnexpectedIOE(jsonMappingException0);
    }

    @Test(timeout = 4000)
    public void test2627() throws Throwable {
        SQLDataException sQLDataException0 = new SQLDataException();
        JsonMappingException.wrapWithPath((Throwable) sQLDataException0, (Object) sQLDataException0, (String) null);
    }

    @Test(timeout = 4000)
    public void test2728() throws Throwable {
        JsonMappingException.Reference jsonMappingException_Reference0 = new JsonMappingException.Reference();
        jsonMappingException_Reference0.getDescription();
        String string0 = jsonMappingException_Reference0.getDescription();
    }

    @Test(timeout = 4000)
    public void test2730() throws Throwable {
        JsonMappingException.Reference jsonMappingException_Reference0 = new JsonMappingException.Reference();
        jsonMappingException_Reference0.getDescription();
        String string0 = jsonMappingException_Reference0.getDescription();
        jsonMappingException_Reference0.getIndex();
    }

    @Test(timeout = 4000)
    public void test2831() throws Throwable {
        Class<ReaderBasedJsonParser> class0 = ReaderBasedJsonParser.class;
        SQLTransientConnectionException sQLTransientConnectionException0 = new SQLTransientConnectionException("J?[O\"xk!`!CQkr((?Jz");
        JsonMappingException jsonMappingException0 = JsonMappingException.wrapWithPath((Throwable) sQLTransientConnectionException0, (Object) class0, "J?[O\"xk!`!CQkr((?Jz");
        String string0 = jsonMappingException0._buildMessage();
    }

    @Test(timeout = 4000)
    public void test2932() throws Throwable {
        JsonFactory jsonFactory0 = new JsonFactory();
        JsonParser jsonParser0 = jsonFactory0.createParser("JSON");
        JsonMappingException jsonMappingException0 = new JsonMappingException(jsonParser0, "JSON");
    }

    @Test(timeout = 4000)
    public void test3033() throws Throwable {
        MockIOException mockIOException0 = new MockIOException();
        JsonParserSequence jsonParserSequence0 = JsonParserSequence.createFlattened(false, (JsonParser) null, (JsonParser) null);
        JsonMappingException.from((JsonParser) jsonParserSequence0, (String) null, (Throwable) mockIOException0);
    }

    @Test(timeout = 4000)
    public void test3134() throws Throwable {
        SQLDataException sQLDataException0 = new SQLDataException();
        JsonMappingException jsonMappingException0 = JsonMappingException.wrapWithPath((Throwable) sQLDataException0, (JsonMappingException.Reference) null);
        JsonMappingException jsonMappingException1 = JsonMappingException.wrapWithPath((Throwable) jsonMappingException0, (JsonMappingException.Reference) null);
    }

    @Test(timeout = 4000)
    public void test3235() throws Throwable {
        SQLTransactionRollbackException sQLTransactionRollbackException0 = new SQLTransactionRollbackException("", "");
        JsonMappingException jsonMappingException0 = JsonMappingException.wrapWithPath((Throwable) sQLTransactionRollbackException0, (Object) sQLTransactionRollbackException0, "");
        assertNotNull(jsonMappingException0);
    }

    @Test(timeout = 4000)
    public void test3336() throws Throwable {
        SQLDataException sQLDataException0 = new SQLDataException();
        JsonMappingException jsonMappingException0 = JsonMappingException.wrapWithPath((Throwable) sQLDataException0, (Object) sQLDataException0, 17);
        List<JsonMappingException.Reference> list0 = jsonMappingException0.getPath();
        list0.isEmpty();
    }

    @Test(timeout = 4000)
    public void test3437() throws Throwable {
        JsonMappingException jsonMappingException0 = new JsonMappingException((String) null);
        List<JsonMappingException.Reference> list0 = jsonMappingException0.getPath();
        list0.isEmpty();
    }
}
