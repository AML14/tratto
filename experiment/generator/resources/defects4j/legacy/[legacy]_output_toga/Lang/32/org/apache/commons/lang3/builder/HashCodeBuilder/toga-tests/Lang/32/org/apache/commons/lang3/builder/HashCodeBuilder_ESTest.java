/*
 * This file was automatically generated by EvoSuite
 * Mon Oct 16 01:58:08 GMT 2023
 */
package org.apache.commons.lang3.builder;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.IDKey;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class HashCodeBuilder_ESTest extends HashCodeBuilder_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test010() throws Throwable {
        HashCodeBuilder hashCodeBuilder0 = new HashCodeBuilder();
        HashCodeBuilder hashCodeBuilder1 = hashCodeBuilder0.append(0L);
        long[] longArray0 = new long[3];
        HashCodeBuilder hashCodeBuilder2 = hashCodeBuilder1.append(longArray0);
        hashCodeBuilder2.append(1.5);
        int int0 = hashCodeBuilder2.toHashCode();
    }

    @Test(timeout = 4000)
    public void test021() throws Throwable {
        HashCodeBuilder hashCodeBuilder0 = new HashCodeBuilder();
        String[] stringArray0 = new String[5];
        int int0 = HashCodeBuilder.reflectionHashCode((Object) hashCodeBuilder0, stringArray0);
    }

    @Test(timeout = 4000)
    public void test022() throws Throwable {
        HashCodeBuilder hashCodeBuilder0 = new HashCodeBuilder();
        String[] stringArray0 = new String[5];
        int int0 = HashCodeBuilder.reflectionHashCode((Object) hashCodeBuilder0, stringArray0);
        hashCodeBuilder0.toHashCode();
        assertEquals(0, hashCodeBuilder0.toHashCode());
    }

    @Test(timeout = 4000)
    public void test033() throws Throwable {
        int int0 = HashCodeBuilder.reflectionHashCode((Object) "nhHYnbZ]a0&$2C", true);
    }

    @Test(timeout = 4000)
    public void test044() throws Throwable {
        LinkedHashSet<String> linkedHashSet0 = new LinkedHashSet<String>();
        int int0 = HashCodeBuilder.reflectionHashCode((Object) "Mx!2Tjcwc.MP^5nK", (Collection<String>) linkedHashSet0);
        assertEquals(0, int0);
    }

    @Test(timeout = 4000)
    public void test055() throws Throwable {
        int int0 = HashCodeBuilder.reflectionHashCode((Object) ";|$n06_hN");
    }

    @Test(timeout = 4000)
    public void test066() throws Throwable {
        HashCodeBuilder hashCodeBuilder0 = new HashCodeBuilder();
        Class<Object> class0 = Object.class;
        int int0 = HashCodeBuilder.reflectionHashCode((-1), (-1), (Object) hashCodeBuilder0, false, (Class<? super Object>) class0);
        hashCodeBuilder0.toHashCode();
        assertEquals(0, hashCodeBuilder0.toHashCode());
    }

    @Test(timeout = 4000)
    public void test067() throws Throwable {
        HashCodeBuilder hashCodeBuilder0 = new HashCodeBuilder();
        Class<Object> class0 = Object.class;
        int int0 = HashCodeBuilder.reflectionHashCode((-1), (-1), (Object) hashCodeBuilder0, false, (Class<? super Object>) class0);
        assertEquals(1, int0);
    }

    @Test(timeout = 4000)
    public void test078() throws Throwable {
        HashCodeBuilder hashCodeBuilder0 = new HashCodeBuilder();
        int int0 = HashCodeBuilder.reflectionHashCode((int) (byte) (-1), (int) (byte) (-1), (Object) hashCodeBuilder0, false);
        hashCodeBuilder0.toHashCode();
        assertEquals(0, hashCodeBuilder0.toHashCode());
    }

    @Test(timeout = 4000)
    public void test079() throws Throwable {
        HashCodeBuilder hashCodeBuilder0 = new HashCodeBuilder();
        int int0 = HashCodeBuilder.reflectionHashCode((int) (byte) (-1), (int) (byte) (-1), (Object) hashCodeBuilder0, false);
        assertEquals(1, int0);
    }

    @Test(timeout = 4000)
    public void test0810() throws Throwable {
        Integer integer0 = new Integer((-1965));
        int int0 = HashCodeBuilder.reflectionHashCode((-1965), (-1965), (Object) integer0, false);
        assertEquals(0, int0);
    }

    @Test(timeout = 4000)
    public void test0911() throws Throwable {
        HashCodeBuilder hashCodeBuilder0 = new HashCodeBuilder();
        int int0 = HashCodeBuilder.reflectionHashCode((-1933), 1, (Object) hashCodeBuilder0, true);
        assertEquals(0, int0);
    }

    @Test(timeout = 4000)
    public void test0912() throws Throwable {
        HashCodeBuilder hashCodeBuilder0 = new HashCodeBuilder();
        int int0 = HashCodeBuilder.reflectionHashCode((-1933), 1, (Object) hashCodeBuilder0, true);
        hashCodeBuilder0.toHashCode();
        assertEquals(0, hashCodeBuilder0.toHashCode());
    }

    @Test(timeout = 4000)
    public void test1013() throws Throwable {
        HashCodeBuilder hashCodeBuilder0 = new HashCodeBuilder();
        int int0 = HashCodeBuilder.reflectionHashCode((int) (byte) (-1), (int) (byte) (-1), (Object) hashCodeBuilder0);
        assertEquals(1, int0);
    }

    @Test(timeout = 4000)
    public void test1014() throws Throwable {
        HashCodeBuilder hashCodeBuilder0 = new HashCodeBuilder();
        int int0 = HashCodeBuilder.reflectionHashCode((int) (byte) (-1), (int) (byte) (-1), (Object) hashCodeBuilder0);
        hashCodeBuilder0.toHashCode();
        assertEquals(0, hashCodeBuilder0.toHashCode());
    }

    @Test(timeout = 4000)
    public void test1115() throws Throwable {
        HashCodeBuilder hashCodeBuilder0 = new HashCodeBuilder();
        int int0 = HashCodeBuilder.reflectionHashCode(7, 37, (Object) hashCodeBuilder0);
    }

    @Test(timeout = 4000)
    public void test1116() throws Throwable {
        HashCodeBuilder hashCodeBuilder0 = new HashCodeBuilder();
        int int0 = HashCodeBuilder.reflectionHashCode(7, 37, (Object) hashCodeBuilder0);
        hashCodeBuilder0.toHashCode();
    }

    @Test(timeout = 4000)
    public void test1217() throws Throwable {
        Integer integer0 = new Integer(2379);
        int int0 = HashCodeBuilder.reflectionHashCode(17, (-1933), (Object) integer0);
        assertEquals(1, int0);
    }

    @Test(timeout = 4000)
    public void test1318() throws Throwable {
        boolean boolean0 = HashCodeBuilder.isRegistered((Object) null);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test1419() throws Throwable {
        HashCodeBuilder hashCodeBuilder0 = new HashCodeBuilder();
        boolean boolean0 = HashCodeBuilder.isRegistered(hashCodeBuilder0);
        hashCodeBuilder0.toHashCode();
        assertEquals(0, hashCodeBuilder0.toHashCode());
    }

    @Test(timeout = 4000)
    public void test1420() throws Throwable {
        HashCodeBuilder hashCodeBuilder0 = new HashCodeBuilder();
        boolean boolean0 = HashCodeBuilder.isRegistered(hashCodeBuilder0);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test1521() throws Throwable {
        HashCodeBuilder hashCodeBuilder0 = new HashCodeBuilder((-1), (-1));
        hashCodeBuilder0.appendSuper((-1));
        hashCodeBuilder0.toHashCode();
        assertEquals(0, hashCodeBuilder0.toHashCode());
    }

    @Test(timeout = 4000)
    public void test1622() throws Throwable {
        HashCodeBuilder hashCodeBuilder0 = new HashCodeBuilder();
        HashCodeBuilder hashCodeBuilder1 = hashCodeBuilder0.append(2175.596F);
        HashCodeBuilder hashCodeBuilder2 = hashCodeBuilder1.append((-2218));
        hashCodeBuilder2.appendSuper((-2218));
        hashCodeBuilder2.toHashCode();
    }

    @Test(timeout = 4000)
    public void test1723() throws Throwable {
        HashCodeBuilder hashCodeBuilder0 = new HashCodeBuilder();
        HashCodeBuilder hashCodeBuilder1 = hashCodeBuilder0.append((double) (byte) (-1));
        hashCodeBuilder1.append((boolean[]) null);
        hashCodeBuilder1.toHashCode();
        assertEquals(0, hashCodeBuilder1.toHashCode());
    }

    @Test(timeout = 4000)
    public void test1824() throws Throwable {
        HashCodeBuilder hashCodeBuilder0 = new HashCodeBuilder();
        short[] shortArray0 = new short[7];
        hashCodeBuilder0.append(shortArray0);
        hashCodeBuilder0.toHashCode();
        assertEquals(0, hashCodeBuilder0.toHashCode());
    }

    @Test(timeout = 4000)
    public void test1925() throws Throwable {
        HashCodeBuilder hashCodeBuilder0 = new HashCodeBuilder();
        HashCodeBuilder hashCodeBuilder1 = hashCodeBuilder0.append(3415.51215627);
        int[] intArray0 = new int[4];
        hashCodeBuilder1.append(intArray0);
        hashCodeBuilder1.toHashCode();
        assertEquals(1, hashCodeBuilder1.toHashCode());
    }

    @Test(timeout = 4000)
    public void test2026() throws Throwable {
        HashCodeBuilder hashCodeBuilder0 = new HashCodeBuilder();
        HashCodeBuilder hashCodeBuilder1 = hashCodeBuilder0.append((byte) (-1));
        float[] floatArray0 = new float[5];
        floatArray0[1] = (float) (byte) (-1);
        hashCodeBuilder1.append(floatArray0);
        hashCodeBuilder1.toHashCode();
        assertEquals(0, hashCodeBuilder1.toHashCode());
    }

    @Test(timeout = 4000)
    public void test2127() throws Throwable {
        HashCodeBuilder hashCodeBuilder0 = new HashCodeBuilder((-1933), (-1933));
        double[] doubleArray0 = new double[2];
        doubleArray0[0] = (double) (-1933L);
        hashCodeBuilder0.append(doubleArray0);
        hashCodeBuilder0.toHashCode();
        assertEquals(0, hashCodeBuilder0.toHashCode());
    }

    @Test(timeout = 4000)
    public void test2228() throws Throwable {
        HashCodeBuilder hashCodeBuilder0 = new HashCodeBuilder();
        char[] charArray0 = new char[7];
        charArray0[0] = 'H';
        charArray0[2] = '*';
        HashCodeBuilder hashCodeBuilder1 = hashCodeBuilder0.append(charArray0);
        hashCodeBuilder1.append(true);
        hashCodeBuilder1.toHashCode();
        assertEquals(0, hashCodeBuilder1.toHashCode());
    }

    @Test(timeout = 4000)
    public void test2329() throws Throwable {
        HashCodeBuilder hashCodeBuilder0 = new HashCodeBuilder();
        hashCodeBuilder0.append(0.0F);
        hashCodeBuilder0.append((short) (-1413));
        hashCodeBuilder0.toHashCode();
        assertEquals(0, hashCodeBuilder0.toHashCode());
    }

    @Test(timeout = 4000)
    public void test2430() throws Throwable {
        HashCodeBuilder hashCodeBuilder0 = new HashCodeBuilder();
        hashCodeBuilder0.append(919.2571F);
        hashCodeBuilder0.append((Object) null);
        hashCodeBuilder0.toHashCode();
        assertEquals(0, hashCodeBuilder0.toHashCode());
    }

    @Test(timeout = 4000)
    public void test2531() throws Throwable {
        HashCodeBuilder hashCodeBuilder0 = new HashCodeBuilder();
        HashCodeBuilder hashCodeBuilder1 = hashCodeBuilder0.append(98.38F);
        hashCodeBuilder1.append(0L);
        hashCodeBuilder1.toHashCode();
        assertEquals(1, hashCodeBuilder1.toHashCode());
    }

    @Test(timeout = 4000)
    public void test2632() throws Throwable {
        HashCodeBuilder hashCodeBuilder0 = new HashCodeBuilder((-1), (-1));
        hashCodeBuilder0.append((float) (-1));
        hashCodeBuilder0.append((double) (-1));
        hashCodeBuilder0.toHashCode();
        assertEquals(0, hashCodeBuilder0.toHashCode());
    }

    @Test(timeout = 4000)
    public void test2733() throws Throwable {
        HashCodeBuilder hashCodeBuilder0 = new HashCodeBuilder();
        hashCodeBuilder0.append((-1.0));
        hashCodeBuilder0.append('s');
        hashCodeBuilder0.toHashCode();
        assertEquals(1, hashCodeBuilder0.toHashCode());
    }

    @Test(timeout = 4000)
    public void test2834() throws Throwable {
        HashCodeBuilder hashCodeBuilder0 = new HashCodeBuilder();
        HashCodeBuilder hashCodeBuilder1 = hashCodeBuilder0.append((-1.0F));
        hashCodeBuilder1.append((byte) 0);
        hashCodeBuilder1.toHashCode();
        assertEquals(1, hashCodeBuilder1.toHashCode());
    }

    @Test(timeout = 4000)
    public void test2935() throws Throwable {
        String[] stringArray0 = new String[2];
        HashCodeBuilder.reflectionHashCode((Object) null, stringArray0);
    }

    @Test(timeout = 4000)
    public void test3036() throws Throwable {
        HashCodeBuilder.reflectionHashCode((Object) null, false);
    }

    @Test(timeout = 4000)
    public void test3137() throws Throwable {
        LinkedHashSet<String> linkedHashSet0 = new LinkedHashSet<String>();
        HashCodeBuilder.reflectionHashCode((Object) null, (Collection<String>) linkedHashSet0);
    }

    @Test(timeout = 4000)
    public void test3238() throws Throwable {
        LinkedHashSet<String> linkedHashSet0 = new LinkedHashSet<String>();
        Class<Object> class0 = Object.class;
        HashCodeBuilder.reflectionHashCode((-974), 2, linkedHashSet0, true, (Class<? super LinkedHashSet<String>>) class0);
    }

    @Test(timeout = 4000)
    public void test3339() throws Throwable {
        HashCodeBuilder hashCodeBuilder0 = new HashCodeBuilder();
        hashCodeBuilder0.append((Object) hashCodeBuilder0);
        hashCodeBuilder0.toHashCode();
        assertEquals(0, hashCodeBuilder0.toHashCode());
    }

    @Test(timeout = 4000)
    public void test3440() throws Throwable {
        HashCodeBuilder hashCodeBuilder0 = new HashCodeBuilder();
        char[] charArray0 = new char[5];
        hashCodeBuilder0.append(charArray0);
        hashCodeBuilder0.toHashCode();
        assertEquals(0, hashCodeBuilder0.toHashCode());
    }

    @Test(timeout = 4000)
    public void test3541() throws Throwable {
        HashCodeBuilder hashCodeBuilder0 = null;
        hashCodeBuilder0 = new HashCodeBuilder(2049, (-686));
    }

    @Test(timeout = 4000)
    public void test3642() throws Throwable {
        HashCodeBuilder hashCodeBuilder0 = null;
        hashCodeBuilder0 = new HashCodeBuilder((-461), 0);
    }

    @Test(timeout = 4000)
    public void test3743() throws Throwable {
        HashCodeBuilder hashCodeBuilder0 = null;
        hashCodeBuilder0 = new HashCodeBuilder(32, 1250);
    }

    @Test(timeout = 4000)
    public void test3844() throws Throwable {
        Class<Object> class0 = Object.class;
        int int0 = HashCodeBuilder.reflectionHashCode((int) (-1), (int) (-1), "Mv:HWr-n)Q-)Nrl+0e", false, (Class<? super String>) class0, (String[]) null);
        assertEquals(0, int0);
    }

    @Test(timeout = 4000)
    public void test3945() throws Throwable {
        Class<Object> class0 = Object.class;
        String[] stringArray0 = new String[5];
        HashCodeBuilder.reflectionHashCode(57, 57, (IDKey) null, false, (Class<? super IDKey>) class0, stringArray0);
    }

    @Test(timeout = 4000)
    public void test4046() throws Throwable {
        HashCodeBuilder hashCodeBuilder0 = new HashCodeBuilder();
        hashCodeBuilder0.append((short) (-3652));
        hashCodeBuilder0.toHashCode();
        assertEquals(0, hashCodeBuilder0.toHashCode());
    }

    @Test(timeout = 4000)
    public void test4147() throws Throwable {
        HashCodeBuilder hashCodeBuilder0 = new HashCodeBuilder();
        hashCodeBuilder0.append('|');
        hashCodeBuilder0.toHashCode();
        assertEquals(0, hashCodeBuilder0.toHashCode());
    }

    @Test(timeout = 4000)
    public void test4248() throws Throwable {
        String[] stringArray0 = new String[2];
        int int0 = HashCodeBuilder.reflectionHashCode((Object) "HashCodeBuilder requires an odd initial value", stringArray0);
    }

    @Test(timeout = 4000)
    public void test4349() throws Throwable {
        HashCodeBuilder hashCodeBuilder0 = new HashCodeBuilder();
        int int0 = hashCodeBuilder0.toHashCode();
        assertEquals(0, int0);
    }

    @Test(timeout = 4000)
    public void test4450() throws Throwable {
        HashCodeBuilder hashCodeBuilder0 = new HashCodeBuilder();
        HashCodeBuilder hashCodeBuilder1 = hashCodeBuilder0.append(0L);
        long[] longArray0 = new long[3];
        HashCodeBuilder hashCodeBuilder2 = hashCodeBuilder1.append(longArray0);
        hashCodeBuilder2.append(longArray0);
        hashCodeBuilder0.toHashCode();
        assertEquals(1, hashCodeBuilder0.toHashCode());
    }

    @Test(timeout = 4000)
    public void test4551() throws Throwable {
        HashCodeBuilder hashCodeBuilder0 = new HashCodeBuilder(17, 17);
        hashCodeBuilder0.append((short[]) null);
        hashCodeBuilder0.toHashCode();
        assertEquals(0, hashCodeBuilder0.toHashCode());
    }

    @Test(timeout = 4000)
    public void test4652() throws Throwable {
        HashCodeBuilder hashCodeBuilder0 = new HashCodeBuilder();
        hashCodeBuilder0.append((Object[]) null);
        hashCodeBuilder0.toHashCode();
        assertEquals(0, hashCodeBuilder0.toHashCode());
    }

    @Test(timeout = 4000)
    public void test4753() throws Throwable {
        HashCodeBuilder hashCodeBuilder0 = new HashCodeBuilder();
        Object[] objectArray0 = new Object[20];
        hashCodeBuilder0.append(objectArray0);
        hashCodeBuilder0.toHashCode();
        assertEquals(0, hashCodeBuilder0.toHashCode());
    }

    @Test(timeout = 4000)
    public void test4854() throws Throwable {
        HashCodeBuilder hashCodeBuilder0 = new HashCodeBuilder(17, 17);
        hashCodeBuilder0.append((long[]) null);
        hashCodeBuilder0.toHashCode();
        assertEquals(0, hashCodeBuilder0.toHashCode());
    }

    @Test(timeout = 4000)
    public void test4955() throws Throwable {
        HashCodeBuilder hashCodeBuilder0 = new HashCodeBuilder();
        int[] intArray0 = new int[1];
        hashCodeBuilder0.append(intArray0);
        hashCodeBuilder0.toHashCode();
        assertEquals(0, hashCodeBuilder0.toHashCode());
    }

    @Test(timeout = 4000)
    public void test5056() throws Throwable {
        HashCodeBuilder hashCodeBuilder0 = new HashCodeBuilder();
        hashCodeBuilder0.append((int[]) null);
        hashCodeBuilder0.toHashCode();
        assertEquals(0, hashCodeBuilder0.toHashCode());
    }

    @Test(timeout = 4000)
    public void test5157() throws Throwable {
        HashCodeBuilder hashCodeBuilder0 = new HashCodeBuilder();
        float[] floatArray0 = new float[4];
        hashCodeBuilder0.append(floatArray0);
        hashCodeBuilder0.toHashCode();
        assertEquals(1, hashCodeBuilder0.toHashCode());
    }

    @Test(timeout = 4000)
    public void test5258() throws Throwable {
        HashCodeBuilder hashCodeBuilder0 = new HashCodeBuilder(17, 17);
        hashCodeBuilder0.append((float[]) null);
        hashCodeBuilder0.toHashCode();
        assertEquals(0, hashCodeBuilder0.toHashCode());
    }

    @Test(timeout = 4000)
    public void test5359() throws Throwable {
        HashCodeBuilder hashCodeBuilder0 = new HashCodeBuilder();
        hashCodeBuilder0.append((double[]) null);
        hashCodeBuilder0.toHashCode();
        assertEquals(0, hashCodeBuilder0.toHashCode());
    }

    @Test(timeout = 4000)
    public void test5460() throws Throwable {
        HashCodeBuilder hashCodeBuilder0 = new HashCodeBuilder();
        hashCodeBuilder0.append((char[]) null);
        hashCodeBuilder0.toHashCode();
        assertEquals(0, hashCodeBuilder0.toHashCode());
    }

    @Test(timeout = 4000)
    public void test5561() throws Throwable {
        HashCodeBuilder hashCodeBuilder0 = new HashCodeBuilder(17, 17);
        hashCodeBuilder0.append((byte[]) null);
        hashCodeBuilder0.toHashCode();
        assertEquals(0, hashCodeBuilder0.toHashCode());
    }

    @Test(timeout = 4000)
    public void test5662() throws Throwable {
        HashCodeBuilder hashCodeBuilder0 = new HashCodeBuilder();
        hashCodeBuilder0.append((boolean[]) null);
        hashCodeBuilder0.append((-3865));
        hashCodeBuilder0.toHashCode();
        assertEquals(0, hashCodeBuilder0.toHashCode());
    }

    @Test(timeout = 4000)
    public void test5763() throws Throwable {
        HashCodeBuilder hashCodeBuilder0 = new HashCodeBuilder();
        boolean[] booleanArray0 = new boolean[2];
        hashCodeBuilder0.append(booleanArray0);
        hashCodeBuilder0.toHashCode();
        assertEquals(0, hashCodeBuilder0.toHashCode());
    }

    @Test(timeout = 4000)
    public void test5864() throws Throwable {
        HashCodeBuilder hashCodeBuilder0 = new HashCodeBuilder(17, 17);
        hashCodeBuilder0.append(true);
        hashCodeBuilder0.toHashCode();
        assertEquals(0, hashCodeBuilder0.toHashCode());
    }

    @Test(timeout = 4000)
    public void test5965() throws Throwable {
        HashCodeBuilder hashCodeBuilder0 = new HashCodeBuilder();
        HashCodeBuilder hashCodeBuilder1 = hashCodeBuilder0.append(false);
        hashCodeBuilder1.append((byte) (-37));
        byte[] byteArray0 = new byte[8];
        hashCodeBuilder1.append(byteArray0);
        hashCodeBuilder1.toHashCode();
        assertEquals(0, hashCodeBuilder1.toHashCode());
    }

    @Test(timeout = 4000)
    public void test6066() throws Throwable {
        HashCodeBuilder hashCodeBuilder0 = new HashCodeBuilder();
        HashCodeBuilder.reflectionHashCode(37, 1152, (Object) hashCodeBuilder0);
    }

    @Test(timeout = 4000)
    public void test6167() throws Throwable {
        HashCodeBuilder hashCodeBuilder0 = null;
        hashCodeBuilder0 = new HashCodeBuilder(0, 0);
    }

    @Test(timeout = 4000)
    public void test6268() throws Throwable {
        HashCodeBuilder.reflectionHashCode((Object) null);
    }

    @Test(timeout = 4000)
    public void test6369() throws Throwable {
        HashCodeBuilder hashCodeBuilder0 = new HashCodeBuilder();
        int int0 = HashCodeBuilder.reflectionHashCode((Object) hashCodeBuilder0, true);
        hashCodeBuilder0.toHashCode();
        assertEquals(0, hashCodeBuilder0.toHashCode());
    }

    @Test(timeout = 4000)
    public void test6370() throws Throwable {
        HashCodeBuilder hashCodeBuilder0 = new HashCodeBuilder();
        int int0 = HashCodeBuilder.reflectionHashCode((Object) hashCodeBuilder0, true);
        assertEquals(0, int0);
    }

    @Test(timeout = 4000)
    public void test6471() throws Throwable {
        Object[] objectArray0 = new Object[5];
        objectArray0[0] = (Object) 'q';
        int int0 = HashCodeBuilder.reflectionHashCode(objectArray0[0]);
        assertEquals(0, int0);
    }

    @Test(timeout = 4000)
    public void test6572() throws Throwable {
        Integer integer0 = new Integer((-11));
        HashCodeBuilder.register(integer0);
        LinkedList<String> linkedList0 = new LinkedList<String>();
        int int0 = HashCodeBuilder.reflectionHashCode((Object) integer0, (Collection<String>) linkedList0);
        assertEquals(0, int0);
    }

    @Test(timeout = 4000)
    public void test6673() throws Throwable {
        Class<Object> class0 = Object.class;
        int int0 = HashCodeBuilder.reflectionHashCode((-2541), (-2541), "+\nSkS5rv", false, (Class<? super String>) class0);
        assertEquals(0, int0);
    }

    @Test(timeout = 4000)
    public void test6774() throws Throwable {
        HashCodeBuilder hashCodeBuilder0 = new HashCodeBuilder();
        HashCodeBuilder.reflectionHashCode((int) '2', (int) (short) 36, (Object) hashCodeBuilder0, true);
    }

    @Test(timeout = 4000)
    public void test6875() throws Throwable {
        Set<IDKey> set0 = HashCodeBuilder.getRegistry();
        int int0 = HashCodeBuilder.reflectionHashCode((Object) set0, false);
    }

    @Test(timeout = 4000)
    public void test6976() throws Throwable {
        Class<String> class0 = String.class;
        String[] stringArray0 = new String[9];
        int int0 = HashCodeBuilder.reflectionHashCode((-3865), (-3865), "<HK@rCR`,*++SI15_+", false, (Class<? super String>) class0, stringArray0);
        assertEquals(1, int0);
    }

    @Test(timeout = 4000)
    public void test7077() throws Throwable {
        HashCodeBuilder hashCodeBuilder0 = new HashCodeBuilder();
        hashCodeBuilder0.hashCode();
        hashCodeBuilder0.toHashCode();
        assertEquals(0, hashCodeBuilder0.toHashCode());
    }

    @Test(timeout = 4000)
    public void test7178() throws Throwable {
        HashCodeBuilder hashCodeBuilder0 = new HashCodeBuilder();
        hashCodeBuilder0.appendSuper(Integer.MAX_VALUE);
        hashCodeBuilder0.toHashCode();
        assertEquals(0, hashCodeBuilder0.toHashCode());
    }
}
