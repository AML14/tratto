/*
 * This file was automatically generated by EvoSuite
 * Thu Mar 28 06:51:07 GMT 2024
 */
package com.fasterxml.jackson.databind.introspect;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.AnnotatedConstructor;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.fasterxml.jackson.databind.introspect.AnnotatedWithParams;
import com.fasterxml.jackson.databind.introspect.AnnotationMap;
import com.fasterxml.jackson.databind.introspect.BasicBeanDescription;
import com.fasterxml.jackson.databind.introspect.BasicClassIntrospector;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.impl.StdTypeResolverBuilder;
import com.fasterxml.jackson.databind.ser.BeanSerializerBuilder;
import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class JacksonAnnotationIntrospector_ESTest extends JacksonAnnotationIntrospector_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        JacksonAnnotationIntrospector jacksonAnnotationIntrospector0 = new JacksonAnnotationIntrospector();
        StdTypeResolverBuilder stdTypeResolverBuilder0 = jacksonAnnotationIntrospector0._constructNoTypeResolverBuilder();
        stdTypeResolverBuilder0.isTypeIdVisible();
        assertFalse(stdTypeResolverBuilder0.isTypeIdVisible());
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        JacksonAnnotationIntrospector jacksonAnnotationIntrospector0 = new JacksonAnnotationIntrospector();
        jacksonAnnotationIntrospector0.isTypeId((AnnotatedMember) null);
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        Class<AnnotatedClass> class0 = AnnotatedClass.class;
        ObjectReader objectReader0 = objectMapper0.readerFor(class0);
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        JacksonAnnotationIntrospector jacksonAnnotationIntrospector0 = new JacksonAnnotationIntrospector();
        Version version0 = jacksonAnnotationIntrospector0.version();
        version0.getPatchLevel();
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        JacksonAnnotationIntrospector jacksonAnnotationIntrospector0 = new JacksonAnnotationIntrospector();
        StdTypeResolverBuilder stdTypeResolverBuilder0 = jacksonAnnotationIntrospector0._constructStdTypeResolverBuilder();
        stdTypeResolverBuilder0.getTypeProperty();
        assertNotNull(stdTypeResolverBuilder0.getTypeProperty());
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        JacksonAnnotationIntrospector jacksonAnnotationIntrospector0 = new JacksonAnnotationIntrospector();
        Object object0 = jacksonAnnotationIntrospector0.readResolve();
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        JacksonAnnotationIntrospector jacksonAnnotationIntrospector0 = new JacksonAnnotationIntrospector();
        jacksonAnnotationIntrospector0._annotationsInside = null;
        Object object0 = jacksonAnnotationIntrospector0.readResolve();
    }

    @Test(timeout = 4000)
    public void test077() throws Throwable {
        JacksonAnnotationIntrospector jacksonAnnotationIntrospector0 = new JacksonAnnotationIntrospector();
        ObjectMapper objectMapper0 = new ObjectMapper();
        ObjectMapper objectMapper1 = objectMapper0.setAnnotationIntrospectors(jacksonAnnotationIntrospector0, jacksonAnnotationIntrospector0);
        ObjectReader objectReader0 = objectMapper1.readerForUpdating(jacksonAnnotationIntrospector0);
        assertNotNull(objectReader0);
    }

    @Test(timeout = 4000)
    public void test088() throws Throwable {
        JacksonAnnotationIntrospector jacksonAnnotationIntrospector0 = new JacksonAnnotationIntrospector();
        ChronoUnit chronoUnit0 = ChronoUnit.CENTURIES;
        String string0 = jacksonAnnotationIntrospector0.findEnumValue(chronoUnit0);
    }

    @Test(timeout = 4000)
    public void test099() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        SerializationFeature serializationFeature0 = SerializationFeature.USE_EQUALITY_FOR_OBJECT_ID;
        ObjectReader objectReader0 = objectMapper0.readerForUpdating(serializationFeature0);
        assertNotNull(objectReader0);
    }

    @Test(timeout = 4000)
    public void test1010() throws Throwable {
        JacksonAnnotationIntrospector jacksonAnnotationIntrospector0 = new JacksonAnnotationIntrospector();
        BasicBeanDescription basicBeanDescription0 = BasicClassIntrospector.INT_DESC;
        BeanSerializerBuilder beanSerializerBuilder0 = new BeanSerializerBuilder(basicBeanDescription0);
        AnnotatedClass annotatedClass0 = beanSerializerBuilder0.getClassInfo();
        PropertyName propertyName0 = jacksonAnnotationIntrospector0.findRootName(annotatedClass0);
        assertNotNull(propertyName0);
    }

    @Test(timeout = 4000)
    public void test1111() throws Throwable {
        JacksonAnnotationIntrospector jacksonAnnotationIntrospector0 = new JacksonAnnotationIntrospector();
        BasicBeanDescription basicBeanDescription0 = BasicClassIntrospector.STRING_DESC;
        AnnotatedConstructor annotatedConstructor0 = basicBeanDescription0.findDefaultConstructor();
        String[] stringArray0 = jacksonAnnotationIntrospector0.findPropertiesToIgnore((Annotated) annotatedConstructor0);
        assertNotNull(stringArray0);
    }

    @Test(timeout = 4000)
    public void test1212() throws Throwable {
        JacksonAnnotationIntrospector jacksonAnnotationIntrospector0 = new JacksonAnnotationIntrospector();
        ObjectMapper objectMapper0 = new ObjectMapper();
        objectMapper0.writeValueAsString(jacksonAnnotationIntrospector0);
    }

    @Test(timeout = 4000)
    public void test1313() throws Throwable {
        JacksonAnnotationIntrospector jacksonAnnotationIntrospector0 = new JacksonAnnotationIntrospector();
        BasicBeanDescription basicBeanDescription0 = BasicClassIntrospector.INT_DESC;
        BeanSerializerBuilder beanSerializerBuilder0 = new BeanSerializerBuilder(basicBeanDescription0);
        AnnotatedClass annotatedClass0 = beanSerializerBuilder0.getClassInfo();
        String string0 = jacksonAnnotationIntrospector0.findClassDescription(annotatedClass0);
    }

    @Test(timeout = 4000)
    public void test1414() throws Throwable {
        JsonFactory jsonFactory0 = new JsonFactory();
        ObjectMapper objectMapper0 = new ObjectMapper(jsonFactory0);
        ObjectReader objectReader0 = objectMapper0.readerForUpdating(objectMapper0);
        assertNotNull(objectReader0);
    }

    @Test(timeout = 4000)
    public void test1515() throws Throwable {
        JacksonAnnotationIntrospector jacksonAnnotationIntrospector0 = new JacksonAnnotationIntrospector();
        BasicBeanDescription basicBeanDescription0 = BasicClassIntrospector.STRING_DESC;
        AnnotatedConstructor annotatedConstructor0 = basicBeanDescription0.findDefaultConstructor();
        List<NamedType> list0 = jacksonAnnotationIntrospector0.findSubtypes(annotatedConstructor0);
    }

    @Test(timeout = 4000)
    public void test1616() throws Throwable {
        BasicBeanDescription basicBeanDescription0 = BasicClassIntrospector.LONG_DESC;
        BeanSerializerBuilder beanSerializerBuilder0 = new BeanSerializerBuilder(basicBeanDescription0);
        AnnotatedClass annotatedClass0 = beanSerializerBuilder0.getClassInfo();
        JacksonAnnotationIntrospector jacksonAnnotationIntrospector0 = new JacksonAnnotationIntrospector();
        String string0 = jacksonAnnotationIntrospector0.findTypeName(annotatedClass0);
        assertNotNull(string0);
    }

    @Test(timeout = 4000)
    public void test1717() throws Throwable {
        JacksonAnnotationIntrospector jacksonAnnotationIntrospector0 = new JacksonAnnotationIntrospector();
        BasicBeanDescription basicBeanDescription0 = BasicClassIntrospector.STRING_DESC;
        AnnotatedConstructor annotatedConstructor0 = basicBeanDescription0.findDefaultConstructor();
        ObjectIdInfo objectIdInfo0 = jacksonAnnotationIntrospector0.findObjectReferenceInfo(annotatedConstructor0, (ObjectIdInfo) null);
    }

    @Test(timeout = 4000)
    public void test1818() throws Throwable {
        JacksonAnnotationIntrospector jacksonAnnotationIntrospector0 = new JacksonAnnotationIntrospector();
        BasicBeanDescription basicBeanDescription0 = BasicClassIntrospector.STRING_DESC;
        AnnotatedConstructor annotatedConstructor0 = basicBeanDescription0.findDefaultConstructor();
        Object object0 = jacksonAnnotationIntrospector0.findKeySerializer(annotatedConstructor0);
        assertNotNull(object0);
    }

    @Test(timeout = 4000)
    public void test1919() throws Throwable {
        JacksonAnnotationIntrospector jacksonAnnotationIntrospector0 = new JacksonAnnotationIntrospector();
        BasicBeanDescription basicBeanDescription0 = BasicClassIntrospector.STRING_DESC;
        AnnotatedConstructor annotatedConstructor0 = basicBeanDescription0.findDefaultConstructor();
        Object object0 = jacksonAnnotationIntrospector0.findContentSerializer(annotatedConstructor0);
    }

    @Test(timeout = 4000)
    public void test2020() throws Throwable {
        JacksonAnnotationIntrospector jacksonAnnotationIntrospector0 = new JacksonAnnotationIntrospector();
        BasicBeanDescription basicBeanDescription0 = BasicClassIntrospector.STRING_DESC;
        AnnotatedConstructor annotatedConstructor0 = basicBeanDescription0.findDefaultConstructor();
        Object object0 = jacksonAnnotationIntrospector0.findNullSerializer(annotatedConstructor0);
        assertNotNull(object0);
    }

    @Test(timeout = 4000)
    public void test2121() throws Throwable {
        JacksonAnnotationIntrospector jacksonAnnotationIntrospector0 = new JacksonAnnotationIntrospector();
        BasicBeanDescription basicBeanDescription0 = BasicClassIntrospector.STRING_DESC;
        AnnotatedConstructor annotatedConstructor0 = basicBeanDescription0.findDefaultConstructor();
        JsonInclude.Include jsonInclude_Include0 = JsonInclude.Include.NON_EMPTY;
        JsonInclude.Include jsonInclude_Include1 = jacksonAnnotationIntrospector0.findSerializationInclusion(annotatedConstructor0, jsonInclude_Include0);
    }

    @Test(timeout = 4000)
    public void test2222() throws Throwable {
        JacksonAnnotationIntrospector jacksonAnnotationIntrospector0 = new JacksonAnnotationIntrospector();
        BasicBeanDescription basicBeanDescription0 = BasicClassIntrospector.STRING_DESC;
        AnnotatedConstructor annotatedConstructor0 = basicBeanDescription0.findDefaultConstructor();
        JsonInclude.Include jsonInclude_Include0 = JsonInclude.Include.NON_DEFAULT;
        JsonInclude.Include jsonInclude_Include1 = jacksonAnnotationIntrospector0.findSerializationInclusionForContent(annotatedConstructor0, jsonInclude_Include0);
    }

    @Test(timeout = 4000)
    public void test2323() throws Throwable {
        JacksonAnnotationIntrospector jacksonAnnotationIntrospector0 = new JacksonAnnotationIntrospector();
        BasicBeanDescription basicBeanDescription0 = BasicClassIntrospector.STRING_DESC;
        AnnotatedConstructor annotatedConstructor0 = basicBeanDescription0.findDefaultConstructor();
        JsonInclude.Value jsonInclude_Value0 = jacksonAnnotationIntrospector0.findPropertyInclusion(annotatedConstructor0);
        jsonInclude_Value0.getContentInclusion();
    }

    @Test(timeout = 4000)
    public void test2424() throws Throwable {
        JacksonAnnotationIntrospector jacksonAnnotationIntrospector0 = new JacksonAnnotationIntrospector();
        BasicBeanDescription basicBeanDescription0 = BasicClassIntrospector.STRING_DESC;
        AnnotatedConstructor annotatedConstructor0 = basicBeanDescription0.findDefaultConstructor();
        Class<?> class0 = jacksonAnnotationIntrospector0.findSerializationKeyType(annotatedConstructor0, (JavaType) null);
        assertNotNull(class0);
    }

    @Test(timeout = 4000)
    public void test2525() throws Throwable {
        JacksonAnnotationIntrospector jacksonAnnotationIntrospector0 = new JacksonAnnotationIntrospector();
        BasicBeanDescription basicBeanDescription0 = BasicClassIntrospector.STRING_DESC;
        AnnotatedConstructor annotatedConstructor0 = basicBeanDescription0.findDefaultConstructor();
        Class<?> class0 = jacksonAnnotationIntrospector0.findSerializationContentType(annotatedConstructor0, (JavaType) null);
        assertNotNull(class0);
    }

    @Test(timeout = 4000)
    public void test2626() throws Throwable {
        JacksonAnnotationIntrospector jacksonAnnotationIntrospector0 = new JacksonAnnotationIntrospector();
        BasicBeanDescription basicBeanDescription0 = BasicClassIntrospector.STRING_DESC;
        AnnotatedConstructor annotatedConstructor0 = basicBeanDescription0.findDefaultConstructor();
        jacksonAnnotationIntrospector0.findSerializationTyping(annotatedConstructor0);
    }

    @Test(timeout = 4000)
    public void test2727() throws Throwable {
        JacksonAnnotationIntrospector jacksonAnnotationIntrospector0 = new JacksonAnnotationIntrospector();
        BasicBeanDescription basicBeanDescription0 = BasicClassIntrospector.STRING_DESC;
        AnnotatedConstructor annotatedConstructor0 = basicBeanDescription0.findDefaultConstructor();
        Object object0 = jacksonAnnotationIntrospector0.findSerializationContentConverter(annotatedConstructor0);
        assertNotNull(object0);
    }

    @Test(timeout = 4000)
    public void test2828() throws Throwable {
        ObjectMapper objectMapper0 = new ObjectMapper();
        Class<Object> class0 = Object.class;
        ObjectReader objectReader0 = objectMapper0.readerFor(class0);
    }

    @Test(timeout = 4000)
    public void test2929() throws Throwable {
        JacksonAnnotationIntrospector jacksonAnnotationIntrospector0 = new JacksonAnnotationIntrospector();
        BasicBeanDescription basicBeanDescription0 = BasicClassIntrospector.INT_DESC;
        BeanSerializerBuilder beanSerializerBuilder0 = new BeanSerializerBuilder(basicBeanDescription0);
        AnnotatedClass annotatedClass0 = beanSerializerBuilder0.getClassInfo();
        JsonPOJOBuilder.Value jsonPOJOBuilder_Value0 = jacksonAnnotationIntrospector0.findPOJOBuilderConfig(annotatedClass0);
    }

    @Test(timeout = 4000)
    public void test3030() throws Throwable {
        BasicBeanDescription basicBeanDescription0 = BasicClassIntrospector.STRING_DESC;
        AnnotatedConstructor annotatedConstructor0 = basicBeanDescription0.findDefaultConstructor();
        JacksonAnnotationIntrospector jacksonAnnotationIntrospector0 = new JacksonAnnotationIntrospector();
        JacksonAnnotationIntrospector jacksonAnnotationIntrospector1 = jacksonAnnotationIntrospector0.setConstructorPropertiesImpliesCreator(false);
        boolean boolean0 = jacksonAnnotationIntrospector1.hasCreatorAnnotation(annotatedConstructor0);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test3131() throws Throwable {
        JacksonAnnotationIntrospector jacksonAnnotationIntrospector0 = new JacksonAnnotationIntrospector();
        Class<AnnotatedMethod> class0 = AnnotatedMethod.class;
        Class<?> class1 = jacksonAnnotationIntrospector0._classIfExplicit(class0, class0);
        assertEquals(class0, class1);
    }

    @Test(timeout = 4000)
    public void test3232() throws Throwable {
        JacksonAnnotationIntrospector jacksonAnnotationIntrospector0 = new JacksonAnnotationIntrospector();
        Class<?> class0 = jacksonAnnotationIntrospector0._classIfExplicit((Class<?>) null, (Class<?>) null);
    }

    @Test(timeout = 4000)
    public void test3333() throws Throwable {
        JacksonAnnotationIntrospector jacksonAnnotationIntrospector0 = new JacksonAnnotationIntrospector();
        Class<Boolean> class0 = Boolean.class;
        Class<AnnotatedMethod> class1 = AnnotatedMethod.class;
        Class<?> class2 = jacksonAnnotationIntrospector0._classIfExplicit(class0, class1);
        assertEquals(class1, class2);
    }

    @Test(timeout = 4000)
    public void test3334() throws Throwable {
        JacksonAnnotationIntrospector jacksonAnnotationIntrospector0 = new JacksonAnnotationIntrospector();
        Class<Boolean> class0 = Boolean.class;
        Class<AnnotatedMethod> class1 = AnnotatedMethod.class;
        Class<?> class2 = jacksonAnnotationIntrospector0._classIfExplicit(class0, class1);
        class2.toString();
    }

    @Test(timeout = 4000)
    public void test3435() throws Throwable {
        JacksonAnnotationIntrospector jacksonAnnotationIntrospector0 = new JacksonAnnotationIntrospector();
        PropertyName propertyName0 = jacksonAnnotationIntrospector0._propertyName("; expected Class<JsonDeserializer>", (String) null);
        propertyName0.isEmpty();
        assertTrue(propertyName0.isEmpty());
    }

    @Test(timeout = 4000)
    public void test3536() throws Throwable {
        JacksonAnnotationIntrospector jacksonAnnotationIntrospector0 = new JacksonAnnotationIntrospector();
        PropertyName propertyName0 = jacksonAnnotationIntrospector0._propertyName("", "");
        propertyName0.isEmpty();
        assertTrue(propertyName0.isEmpty());
    }

    @Test(timeout = 4000)
    public void test3637() throws Throwable {
        JacksonAnnotationIntrospector jacksonAnnotationIntrospector0 = new JacksonAnnotationIntrospector();
        PropertyName propertyName0 = jacksonAnnotationIntrospector0._propertyName(".", ".");
        propertyName0.hasNamespace();
        assertTrue(propertyName0.hasNamespace());
    }

    @Test(timeout = 4000)
    public void test3738() throws Throwable {
        JacksonAnnotationIntrospector jacksonAnnotationIntrospector0 = new JacksonAnnotationIntrospector();
        PropertyName propertyName0 = jacksonAnnotationIntrospector0._propertyName("Unable to load JDK7 annotation types; will have to skip", "");
        propertyName0.isEmpty();
        assertTrue(propertyName0.isEmpty());
    }

    @Test(timeout = 4000)
    public void test3739() throws Throwable {
        JacksonAnnotationIntrospector jacksonAnnotationIntrospector0 = new JacksonAnnotationIntrospector();
        PropertyName propertyName0 = jacksonAnnotationIntrospector0._propertyName("Unable to load JDK7 annotation types; will have to skip", "");
        propertyName0.hasNamespace();
        assertTrue(propertyName0.hasNamespace());
    }

    @Test(timeout = 4000)
    public void test3840() throws Throwable {
        JacksonAnnotationIntrospector jacksonAnnotationIntrospector0 = new JacksonAnnotationIntrospector();
        AnnotationMap annotationMap0 = new AnnotationMap();
        AnnotatedParameter annotatedParameter0 = new AnnotatedParameter((AnnotatedWithParams) null, (JavaType) null, annotationMap0, (-80));
        PropertyName propertyName0 = jacksonAnnotationIntrospector0._findConstructorName(annotatedParameter0);
        assertNotNull(propertyName0);
    }
}
