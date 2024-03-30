/*
 * This file was automatically generated by EvoSuite
 * Sun Nov 05 05:30:55 GMT 2023
 */
package org.mockito;

import org.junit.Test;
import static org.junit.Assert.*;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.hamcrest.Matcher;
import org.hamcrest.SelfDescribing;
import org.hamcrest.beans.HasProperty;
import org.hamcrest.core.AllOf;
import org.hamcrest.core.CombinableMatcher;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsAnything;
import org.hamcrest.core.IsNull;
import org.hamcrest.core.IsSame;
import org.hamcrest.number.OrderingComparison;
import org.hamcrest.object.HasToString;
import org.junit.runner.RunWith;
import org.mockito.Matchers;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class Matchers_ESTest extends Matchers_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test000() throws Throwable {
        Matcher<Float> matcher0 = HasToString.hasToString("org.mockito.Matchers");
        float float0 = Matchers.floatThat(matcher0);
    }

    @Test(timeout = 4000)
    public void test011() throws Throwable {
        Matcher<Object> matcher0 = Is.is((Object) "x");
        Matcher<Character> matcher1 = AllOf.allOf((Matcher<? super Character>) matcher0, (Matcher<? super Character>) matcher0, (Matcher<? super Character>) matcher0, (Matcher<? super Character>) matcher0);
        Matcher<SelfDescribing> matcher2 = IsSame.sameInstance((SelfDescribing) matcher1);
        SelfDescribing selfDescribing0 = Matchers.argThat(matcher2);
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        Object object0 = Matchers.isNotNull();
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        Class<Byte> class0 = Byte.class;
        Set<Byte> set0 = Matchers.anySetOf(class0);
        set0.isEmpty();
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        char char0 = Matchers.eq('\u0000');
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        IsAnything<Boolean> isAnything0 = new IsAnything<Boolean>();
        boolean boolean0 = Matchers.booleanThat(isAnything0);
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        HasProperty<Object> hasProperty0 = new HasProperty<Object>("");
        CombinableMatcher<Double> combinableMatcher0 = new CombinableMatcher<Double>(hasProperty0);
        double double0 = Matchers.doubleThat(combinableMatcher0);
    }

    @Test(timeout = 4000)
    public void test077() throws Throwable {
        int int0 = Matchers.eq(0);
    }

    @Test(timeout = 4000)
    public void test088() throws Throwable {
        int int0 = Matchers.intThat((Matcher<Integer>) null);
    }

    @Test(timeout = 4000)
    public void test099() throws Throwable {
        Byte byte0 = new Byte((byte) (-95));
        Matcher<Byte> matcher0 = OrderingComparison.lessThanOrEqualTo(byte0);
        byte byte1 = Matchers.byteThat(matcher0);
    }

    @Test(timeout = 4000)
    public void test1010() throws Throwable {
        Long long0 = new Long(0L);
        Matcher<Long> matcher0 = IsSame.theInstance(long0);
        long long1 = Matchers.longThat(matcher0);
    }

    @Test(timeout = 4000)
    public void test1111() throws Throwable {
        Object object0 = Matchers.isNull();
    }

    @Test(timeout = 4000)
    public void test1212() throws Throwable {
        char char0 = Matchers.anyChar();
    }

    @Test(timeout = 4000)
    public void test1313() throws Throwable {
        Class<Object> class0 = Object.class;
        Object object0 = Matchers.any(class0);
    }

    @Test(timeout = 4000)
    public void test1414() throws Throwable {
        String string0 = Matchers.endsWith("kyF;97;");
    }

    @Test(timeout = 4000)
    public void test1515() throws Throwable {
        Class<Byte> class0 = Byte.class;
        Collection<Byte> collection0 = Matchers.anyCollectionOf(class0);
    }

    @Test(timeout = 4000)
    public void test1616() throws Throwable {
        String string0 = Matchers.contains("");
    }

    @Test(timeout = 4000)
    public void test1717() throws Throwable {
        Collection collection0 = Matchers.anyCollection();
    }

    @Test(timeout = 4000)
    public void test1818() throws Throwable {
        Map map0 = Matchers.anyMap();
        map0.size();
    }

    @Test(timeout = 4000)
    public void test1919() throws Throwable {
        String string0 = Matchers.startsWith("");
    }

    @Test(timeout = 4000)
    public void test2020() throws Throwable {
        String string0 = Matchers.anyString();
    }

    @Test(timeout = 4000)
    public void test2121() throws Throwable {
        short short0 = Matchers.eq((short) 13);
    }

    @Test(timeout = 4000)
    public void test2222() throws Throwable {
        Character character0 = new Character('\"');
        Character character1 = Matchers.refEq(character0, (String[]) null);
    }

    @Test(timeout = 4000)
    public void test2323() throws Throwable {
        Boolean boolean0 = Matchers.anyVararg();
    }

    @Test(timeout = 4000)
    public void test2424() throws Throwable {
        IsNull<Short> isNull0 = new IsNull<Short>();
        CombinableMatcher<Short> combinableMatcher0 = new CombinableMatcher<Short>(isNull0);
        short short0 = Matchers.shortThat(combinableMatcher0);
    }

    @Test(timeout = 4000)
    public void test2525() throws Throwable {
        byte byte0 = Matchers.eq((byte) 0);
    }

    @Test(timeout = 4000)
    public void test2626() throws Throwable {
        int int0 = Matchers.anyInt();
    }

    @Test(timeout = 4000)
    public void test2727() throws Throwable {
        Class<Object> class0 = Object.class;
        Object object0 = Matchers.isA(class0);
    }

    @Test(timeout = 4000)
    public void test2828() throws Throwable {
        char char0 = Matchers.charThat((Matcher<Character>) null);
    }

    @Test(timeout = 4000)
    public void test2929() throws Throwable {
        byte byte0 = Matchers.anyByte();
    }

    @Test(timeout = 4000)
    public void test3130() throws Throwable {
        String string0 = Matchers.matches("");
    }

    @Test(timeout = 4000)
    public void test3231() throws Throwable {
        float float0 = Matchers.eq(0.0F);
    }

    @Test(timeout = 4000)
    public void test3332() throws Throwable {
        IsAnything<Boolean> isAnything0 = new IsAnything<Boolean>();
        Object object0 = Matchers.eq((Object) isAnything0);
    }

    @Test(timeout = 4000)
    public void test3433() throws Throwable {
        long long0 = Matchers.anyLong();
    }

    @Test(timeout = 4000)
    public void test3534() throws Throwable {
        double double0 = Matchers.eq((-1.0));
    }

    @Test(timeout = 4000)
    public void test3635() throws Throwable {
        short short0 = Matchers.anyShort();
    }

    @Test(timeout = 4000)
    public void test3736() throws Throwable {
        boolean boolean0;
        boolean0 = Matchers.anyBoolean();
        assertTrue(true ? boolean0 == false : true);
    }

    @Test(timeout = 4000)
    public void test3837() throws Throwable {
        float float0 = Matchers.anyFloat();
    }

    @Test(timeout = 4000)
    public void test3938() throws Throwable {
        double double0 = Matchers.anyDouble();
    }

    @Test(timeout = 4000)
    public void test4039() throws Throwable {
        long long0 = Matchers.eq((long) 0);
    }

    @Test(timeout = 4000)
    public void test4140() throws Throwable {
        Set set0 = Matchers.anySet();
        set0.size();
    }

    @Test(timeout = 4000)
    public void test4241() throws Throwable {
        Class<Double> class0 = Double.class;
        List<Double> list0 = Matchers.anyListOf(class0);
        list0.isEmpty();
    }

    @Test(timeout = 4000)
    public void test4342() throws Throwable {
        List list0 = Matchers.anyList();
        list0.isEmpty();
    }

    @Test(timeout = 4000)
    public void test4443() throws Throwable {
        Byte byte0 = Matchers.any();
    }

    @Test(timeout = 4000)
    public void test4644() throws Throwable {
        boolean boolean0 = Matchers.eq(false);
    }
}
