/*
 * This file was automatically generated by EvoSuite
 * Mon Oct 16 03:55:55 GMT 2023
 */
package org.apache.commons.lang;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import org.apache.commons.lang.BooleanUtils;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class BooleanUtils_ESTest extends BooleanUtils_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test0000() throws Throwable {
        boolean boolean0 = BooleanUtils.toBoolean(", '");
    }

    @Test(timeout = 4000)
    public void test0011() throws Throwable {
        boolean boolean0 = BooleanUtils.toBoolean("yQx");
    }

    @Test(timeout = 4000)
    public void test0022() throws Throwable {
        boolean boolean0 = BooleanUtils.toBoolean("6u");
    }

    @Test(timeout = 4000)
    public void test0033() throws Throwable {
        boolean boolean0 = BooleanUtils.toBoolean("{}");
    }

    @Test(timeout = 4000)
    public void test0044() throws Throwable {
        Boolean boolean0 = Boolean.TRUE;
        Integer integer0 = new Integer(523);
        Integer integer1 = new Integer(1290);
        Integer integer2 = BooleanUtils.toIntegerObject(boolean0, integer0, integer0, integer1);
    }

    @Test(timeout = 4000)
    public void test0055() throws Throwable {
        Integer integer0 = new Integer(0);
        Integer integer1 = new Integer(89);
        Integer integer2 = BooleanUtils.toIntegerObject(false, integer0, integer1);
    }

    @Test(timeout = 4000)
    public void test0066() throws Throwable {
        Integer integer0 = new Integer((-1244));
        Integer integer1 = new Integer((-1244));
        Integer integer2 = BooleanUtils.toIntegerObject(true, integer0, integer1);
        integer2.equals((Object) integer1);
    }

    @Test(timeout = 4000)
    public void test0077() throws Throwable {
        Boolean boolean0 = Boolean.FALSE;
        int int0 = BooleanUtils.toInteger(boolean0, 2552, (-2003), (-1495));
    }

    @Test(timeout = 4000)
    public void test0088() throws Throwable {
        int int0 = BooleanUtils.toInteger((Boolean) null, (-968), (-968), (-464));
    }

    @Test(timeout = 4000)
    public void test0099() throws Throwable {
        int int0 = BooleanUtils.toInteger(false, 618, 0);
    }

    @Test(timeout = 4000)
    public void test01010() throws Throwable {
        BooleanUtils.toBooleanObject(1024, 0, 707, 43);
    }

    @Test(timeout = 4000)
    public void test01111() throws Throwable {
        Boolean boolean0 = BooleanUtils.toBooleanObject(0, (-123), 0, (-1316));
    }

    @Test(timeout = 4000)
    public void test01212() throws Throwable {
        BooleanUtils.toBoolean(102, 2029, 2);
    }

    @Test(timeout = 4000)
    public void test01313() throws Throwable {
        Integer integer0 = new Integer((-1025));
        Boolean boolean0 = BooleanUtils.toBooleanObject(integer0);
    }

    @Test(timeout = 4000)
    public void test01414() throws Throwable {
        Boolean boolean0 = BooleanUtils.toBooleanObject(2);
    }

    @Test(timeout = 4000)
    public void test01515() throws Throwable {
        boolean boolean0 = BooleanUtils.toBoolean(116);
    }

    @Test(timeout = 4000)
    public void test01616() throws Throwable {
        Boolean boolean0 = Boolean.FALSE;
        String string0 = BooleanUtils.toStringYesNo(boolean0);
    }

    @Test(timeout = 4000)
    public void test01718() throws Throwable {
        String string0 = BooleanUtils.toStringTrueFalse((Boolean) null);
    }

    @Test(timeout = 4000)
    public void test01819() throws Throwable {
        String string0 = BooleanUtils.toStringOnOff((Boolean) null);
    }

    @Test(timeout = 4000)
    public void test01920() throws Throwable {
        String string0 = BooleanUtils.toString(true, (String) null, "OT]\u0002qT+]g]C3zIRR");
    }

    @Test(timeout = 4000)
    public void test02021() throws Throwable {
        String string0 = BooleanUtils.toString(true, "", "");
    }

    @Test(timeout = 4000)
    public void test02122() throws Throwable {
        Boolean boolean0 = new Boolean(false);
        String string0 = BooleanUtils.toString(boolean0, "The String did not match any specified value", "", "");
    }

    @Test(timeout = 4000)
    public void test02223() throws Throwable {
        Integer integer0 = new Integer(939);
        Integer integer1 = BooleanUtils.toIntegerObject(true, integer0, integer0);
    }

    @Test(timeout = 4000)
    public void test02324() throws Throwable {
        Boolean boolean0 = Boolean.FALSE;
        Integer integer0 = new Integer(0);
        Integer integer1 = BooleanUtils.toIntegerObject(boolean0, integer0, integer0, integer0);
    }

    @Test(timeout = 4000)
    public void test02425() throws Throwable {
        int int0 = BooleanUtils.toInteger(true, 117, (-2057));
    }

    @Test(timeout = 4000)
    public void test02526() throws Throwable {
        String string0 = BooleanUtils.toString(true, "yes", "true");
    }

    @Test(timeout = 4000)
    public void test02627() throws Throwable {
        Boolean boolean0 = new Boolean(false);
        String string0 = BooleanUtils.toString((boolean) boolean0, "", "no");
    }

    @Test(timeout = 4000)
    public void test02728() throws Throwable {
        Integer integer0 = new Integer(2244);
        Boolean boolean0 = BooleanUtils.toBooleanObject(integer0);
    }

    @Test(timeout = 4000)
    public void test02729() throws Throwable {
        Integer integer0 = new Integer(2244);
        Boolean boolean0 = BooleanUtils.toBooleanObject(integer0);
        String string0 = BooleanUtils.toString(boolean0, "W+Lz+O_j{lD'o", "W+Lz+O_j{lD'o", "off");
    }

    @Test(timeout = 4000)
    public void test02830() throws Throwable {
        String string0 = BooleanUtils.toString((Boolean) null, (String) null, "Index: ", (String) null);
    }

    @Test(timeout = 4000)
    public void test02931() throws Throwable {
        Boolean boolean0 = new Boolean(false);
        assertTrue(boolean0 == false);
        boolean boolean1;
        boolean1 = BooleanUtils.isFalse(boolean0);
    }

    @Test(timeout = 4000)
    public void test03032() throws Throwable {
        Boolean boolean0 = BooleanUtils.toBooleanObject("", "", "", "");
        assertTrue(boolean0 == false);
        boolean boolean1;
        boolean1 = BooleanUtils.isTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test03133() throws Throwable {
        Boolean[] booleanArray0 = new Boolean[4];
        Boolean boolean0 = Boolean.TRUE;
        booleanArray0[0] = boolean0;
        Boolean boolean1 = Boolean.FALSE;
        booleanArray0[1] = boolean1;
        booleanArray0[2] = boolean1;
        booleanArray0[3] = boolean1;
        java.lang.Boolean boolean2;
        if (booleanArray0 == null) {
            try {
                boolean2 = BooleanUtils.xor(booleanArray0);
                fail();
            } catch (java.lang.IllegalArgumentException e) {
                // Successfully thrown exception
            }
        } else if (booleanArray0.length == 0) {
            try {
                boolean2 = BooleanUtils.xor(booleanArray0);
                fail();
            } catch (java.lang.IllegalArgumentException e) {
                // Successfully thrown exception
            }
        } else {
            boolean2 = BooleanUtils.xor(booleanArray0);
        }
    }

    @Test(timeout = 4000)
    public void test03234() throws Throwable {
        Boolean boolean0 = new Boolean(false);
        Boolean[] booleanArray0 = new Boolean[5];
        booleanArray0[0] = boolean0;
        booleanArray0[1] = boolean0;
        booleanArray0[2] = boolean0;
        booleanArray0[3] = boolean0;
        booleanArray0[4] = boolean0;
        java.lang.Boolean boolean1;
        if (booleanArray0 == null) {
            try {
                boolean1 = BooleanUtils.xor(booleanArray0);
                fail();
            } catch (java.lang.IllegalArgumentException e) {
                // Successfully thrown exception
            }
        } else if (booleanArray0.length == 0) {
            try {
                boolean1 = BooleanUtils.xor(booleanArray0);
                fail();
            } catch (java.lang.IllegalArgumentException e) {
                // Successfully thrown exception
            }
        } else {
            boolean1 = BooleanUtils.xor(booleanArray0);
        }
    }

    @Test(timeout = 4000)
    public void test03335() throws Throwable {
        Boolean[] booleanArray0 = new Boolean[0];
        java.lang.Boolean default0;
        if (booleanArray0 == null) {
            try {
                default0 = BooleanUtils.xor(booleanArray0);
                fail();
            } catch (java.lang.IllegalArgumentException e) {
                // Successfully thrown exception
            }
        } else if (booleanArray0.length == 0) {
            try {
                default0 = BooleanUtils.xor(booleanArray0);
                fail();
            } catch (java.lang.IllegalArgumentException e) {
                // Successfully thrown exception
            }
        } else {
            default0 = BooleanUtils.xor(booleanArray0);
        }
    }

    @Test(timeout = 4000)
    public void test03436() throws Throwable {
        boolean default1;
        if ((Boolean[]) null == null) {
            try {
                default1 = BooleanUtils.xor((Boolean[]) null);
                fail();
            } catch (java.lang.IllegalArgumentException e) {
                // Successfully thrown exception
            }
        } else if ((Boolean[]) null.length == 0) {
            try {
                default1 = BooleanUtils.xor((Boolean[]) null);
                fail();
            } catch (java.lang.IllegalArgumentException e) {
                // Successfully thrown exception
            }
        } else {
            default1 = BooleanUtils.xor((Boolean[]) null);
        }
    }

    @Test(timeout = 4000)
    public void test03537() throws Throwable {
        Boolean[] booleanArray0 = new Boolean[5];
        java.lang.Boolean default2;
        if (booleanArray0 == null) {
            try {
                default2 = BooleanUtils.xor(booleanArray0);
                fail();
            } catch (java.lang.IllegalArgumentException e) {
                // Successfully thrown exception
            }
        } else if (booleanArray0.length == 0) {
            try {
                default2 = BooleanUtils.xor(booleanArray0);
                fail();
            } catch (java.lang.IllegalArgumentException e) {
                // Successfully thrown exception
            }
        } else {
            default2 = BooleanUtils.xor(booleanArray0);
        }
    }

    @Test(timeout = 4000)
    public void test03638() throws Throwable {
        boolean[] booleanArray0 = new boolean[7];
        booleanArray0[0] = true;
        booleanArray0[2] = true;
        boolean boolean0;
        if (booleanArray0 == null) {
            try {
                boolean0 = BooleanUtils.xor(booleanArray0);
                fail();
            } catch (java.lang.IllegalArgumentException e) {
                // Successfully thrown exception
            }
        } else if (booleanArray0.length == 0) {
            try {
                boolean0 = BooleanUtils.xor(booleanArray0);
                fail();
            } catch (java.lang.IllegalArgumentException e) {
                // Successfully thrown exception
            }
        } else {
            boolean0 = BooleanUtils.xor(booleanArray0);
        }
    }

    @Test(timeout = 4000)
    public void test03739() throws Throwable {
        boolean[] booleanArray0 = new boolean[7];
        booleanArray0[0] = true;
        boolean boolean0;
        if (booleanArray0 == null) {
            try {
                boolean0 = BooleanUtils.xor(booleanArray0);
                fail();
            } catch (java.lang.IllegalArgumentException e) {
                // Successfully thrown exception
            }
        } else if (booleanArray0.length == 0) {
            try {
                boolean0 = BooleanUtils.xor(booleanArray0);
                fail();
            } catch (java.lang.IllegalArgumentException e) {
                // Successfully thrown exception
            }
        } else {
            boolean0 = BooleanUtils.xor(booleanArray0);
        }
    }

    @Test(timeout = 4000)
    public void test03840() throws Throwable {
        boolean[] booleanArray0 = new boolean[0];
        boolean default3;
        if (booleanArray0 == null) {
            try {
                default3 = BooleanUtils.xor(booleanArray0);
                fail();
            } catch (java.lang.IllegalArgumentException e) {
                // Successfully thrown exception
            }
        } else if (booleanArray0.length == 0) {
            try {
                default3 = BooleanUtils.xor(booleanArray0);
                fail();
            } catch (java.lang.IllegalArgumentException e) {
                // Successfully thrown exception
            }
        } else {
            default3 = BooleanUtils.xor(booleanArray0);
        }
    }

    @Test(timeout = 4000)
    public void test03941() throws Throwable {
        boolean default4;
        if ((boolean[]) null == null) {
            try {
                default4 = BooleanUtils.xor((boolean[]) null);
                fail();
            } catch (java.lang.IllegalArgumentException e) {
                // Successfully thrown exception
            }
        } else if ((boolean[]) null.length == 0) {
            try {
                default4 = BooleanUtils.xor((boolean[]) null);
                fail();
            } catch (java.lang.IllegalArgumentException e) {
                // Successfully thrown exception
            }
        } else {
            default4 = BooleanUtils.xor((boolean[]) null);
        }
    }

    @Test(timeout = 4000)
    public void test04042() throws Throwable {
        boolean[] booleanArray0 = new boolean[7];
        boolean boolean0;
        if (booleanArray0 == null) {
            try {
                boolean0 = BooleanUtils.xor(booleanArray0);
                fail();
            } catch (java.lang.IllegalArgumentException e) {
                // Successfully thrown exception
            }
        } else if (booleanArray0.length == 0) {
            try {
                boolean0 = BooleanUtils.xor(booleanArray0);
                fail();
            } catch (java.lang.IllegalArgumentException e) {
                // Successfully thrown exception
            }
        } else {
            boolean0 = BooleanUtils.xor(booleanArray0);
        }
    }

    @Test(timeout = 4000)
    public void test04143() throws Throwable {
        String string0 = BooleanUtils.toStringTrueFalse(true);
    }

    @Test(timeout = 4000)
    public void test04244() throws Throwable {
        boolean boolean0 = BooleanUtils.toBoolean("%o%", "", "%o%");
    }

    @Test(timeout = 4000)
    public void test04345() throws Throwable {
        boolean boolean0 = BooleanUtils.toBoolean("Zbi'FMa8yGYYu3*Df", "Zbi'FMa8yGYYu3*Df", "Zbi'FMa8yGYYu3*Df");
    }

    @Test(timeout = 4000)
    public void test04446() throws Throwable {
        boolean boolean0 = BooleanUtils.toBoolean((String) null, "", (String) null);
    }

    @Test(timeout = 4000)
    public void test04547() throws Throwable {
        boolean boolean0 = BooleanUtils.toBoolean((String) null, (String) null, (String) null);
    }

    @Test(timeout = 4000)
    public void test04648() throws Throwable {
        BooleanUtils.toBoolean((String) null, "on", "true");
    }

    @Test(timeout = 4000)
    public void test04749() throws Throwable {
        BooleanUtils.toBoolean("Y]", "yes", "Index: ");
    }

    @Test(timeout = 4000)
    public void test04850() throws Throwable {
        boolean boolean0 = BooleanUtils.toBoolean("yes");
    }

    @Test(timeout = 4000)
    public void test04951() throws Throwable {
        boolean boolean0 = BooleanUtils.toBoolean("y/x");
    }

    @Test(timeout = 4000)
    public void test05052() throws Throwable {
        boolean boolean0 = BooleanUtils.toBoolean("yex");
    }

    @Test(timeout = 4000)
    public void test05153() throws Throwable {
        boolean boolean0 = BooleanUtils.toBoolean("on");
    }

    @Test(timeout = 4000)
    public void test05254() throws Throwable {
        boolean boolean0 = BooleanUtils.toBoolean("|lI8+Nwyp");
    }

    @Test(timeout = 4000)
    public void test05355() throws Throwable {
        boolean boolean0 = BooleanUtils.toBoolean("d `");
    }

    @Test(timeout = 4000)
    public void test05456() throws Throwable {
        boolean boolean0 = BooleanUtils.toBoolean((String) null);
    }

    @Test(timeout = 4000)
    public void test05557() throws Throwable {
        boolean boolean0 = BooleanUtils.toBoolean("true");
    }

    @Test(timeout = 4000)
    public void test05658() throws Throwable {
        boolean boolean0 = BooleanUtils.toBoolean("t;uO");
    }

    @Test(timeout = 4000)
    public void test05759() throws Throwable {
        Boolean boolean0 = BooleanUtils.toBooleanObject("false", "--", "--", "false");
    }

    @Test(timeout = 4000)
    public void test05860() throws Throwable {
        Boolean boolean0 = BooleanUtils.toBooleanObject("^J`9Y8g*u~FxrJK", "6FM;$]l&-\"U|$XA", "^J`9Y8g*u~FxrJK", "6FM;$]l&-\"U|$XA");
    }

    @Test(timeout = 4000)
    public void test05961() throws Throwable {
        BooleanUtils.toBooleanObject("aO}9", ".iMpqJ#2w)Fh.*of(h", "no", "W`\"v'Bo:1o");
    }

    @Test(timeout = 4000)
    public void test06062() throws Throwable {
        Boolean boolean0 = BooleanUtils.toBooleanObject((String) null, "true", "@P{F%koDgqJh=", (String) null);
    }

    @Test(timeout = 4000)
    public void test06163() throws Throwable {
        BooleanUtils.toBooleanObject((String) null, "aO}9", ")<", "java.lang.Object");
    }

    @Test(timeout = 4000)
    public void test06264() throws Throwable {
        Boolean boolean0 = BooleanUtils.toBooleanObject((String) null, (String) null, (String) null, "uSJS3rSTSZ=%sH(zxH");
    }

    @Test(timeout = 4000)
    public void test06365() throws Throwable {
        Boolean boolean0 = BooleanUtils.toBooleanObject((String) null, "u'>s+;.][", (String) null, "false");
    }

    @Test(timeout = 4000)
    public void test06466() throws Throwable {
        Boolean boolean0 = BooleanUtils.toBooleanObject("no");
        String string0 = BooleanUtils.toStringTrueFalse(boolean0);
    }

    @Test(timeout = 4000)
    public void test06568() throws Throwable {
        Boolean boolean0 = BooleanUtils.toBooleanObject("yes");
    }

    @Test(timeout = 4000)
    public void test06670() throws Throwable {
        Boolean boolean0 = BooleanUtils.toBooleanObject("off");
    }

    @Test(timeout = 4000)
    public void test06772() throws Throwable {
        Boolean boolean0 = BooleanUtils.toBooleanObject("on");
    }

    @Test(timeout = 4000)
    public void test06874() throws Throwable {
        Boolean boolean0 = BooleanUtils.toBooleanObject("false");
    }

    @Test(timeout = 4000)
    public void test06976() throws Throwable {
        Boolean boolean0 = BooleanUtils.toBooleanObject("true");
    }

    @Test(timeout = 4000)
    public void test07077() throws Throwable {
        Boolean boolean0 = BooleanUtils.toBooleanObject("", "", (String) null, "");
        Integer integer0 = new Integer((-3683));
        Integer integer1 = BooleanUtils.toIntegerObject(boolean0, integer0, (Integer) null, integer0);
    }

    @Test(timeout = 4000)
    public void test07178() throws Throwable {
        Integer integer0 = new Integer((-287));
        Integer integer1 = BooleanUtils.toIntegerObject((Boolean) null, integer0, integer0, (Integer) null);
    }

    @Test(timeout = 4000)
    public void test07279() throws Throwable {
        boolean[] booleanArray0 = new boolean[9];
        Integer integer0 = new Integer((-3683));
        Integer integer1 = BooleanUtils.toIntegerObject((Boolean) booleanArray0[2], integer0, (Integer) null, integer0);
    }

    @Test(timeout = 4000)
    public void test07380() throws Throwable {
        Integer integer0 = BooleanUtils.toIntegerObject(true, (Integer) null, (Integer) null);
    }

    @Test(timeout = 4000)
    public void test07481() throws Throwable {
        Integer integer0 = new Integer(0);
        Integer integer1 = BooleanUtils.toIntegerObject(false, integer0, integer0);
    }

    @Test(timeout = 4000)
    public void test07582() throws Throwable {
        int int0 = BooleanUtils.toInteger((Boolean) false, 81, 81, 360);
    }

    @Test(timeout = 4000)
    public void test07683() throws Throwable {
        Boolean boolean0 = BooleanUtils.toBooleanObject((-1668), (-1668), (-1668), 0);
        int int0 = BooleanUtils.toInteger(boolean0, 0, 1236, (-53));
    }

    @Test(timeout = 4000)
    public void test07784() throws Throwable {
        int int0 = BooleanUtils.toInteger(false, (-2417), (-2417));
    }

    @Test(timeout = 4000)
    public void test07885() throws Throwable {
        Boolean boolean0 = new Boolean("-0x");
        Integer integer0 = BooleanUtils.toIntegerObject(boolean0);
    }

    @Test(timeout = 4000)
    public void test07986() throws Throwable {
        Integer integer0 = BooleanUtils.toIntegerObject((Boolean) null);
    }

    @Test(timeout = 4000)
    public void test08087() throws Throwable {
        Integer integer0 = BooleanUtils.toIntegerObject(true);
    }

    @Test(timeout = 4000)
    public void test08188() throws Throwable {
        Integer integer0 = BooleanUtils.toIntegerObject(false);
    }

    @Test(timeout = 4000)
    public void test08289() throws Throwable {
        int int0 = BooleanUtils.toInteger(true);
    }

    @Test(timeout = 4000)
    public void test08390() throws Throwable {
        int int0 = BooleanUtils.toInteger(false);
    }

    @Test(timeout = 4000)
    public void test08491() throws Throwable {
        Integer integer0 = new Integer(1373);
        Integer integer1 = new Integer(3436);
        BooleanUtils.toBooleanObject(integer0, integer1, integer1, integer1);
    }

    @Test(timeout = 4000)
    public void test08592() throws Throwable {
        Boolean boolean0 = BooleanUtils.toBooleanObject((-1668), (-1668), (-1668), 0);
        Integer integer0 = BooleanUtils.toIntegerObject(boolean0);
        Integer integer1 = new Integer((-28));
        Boolean boolean1 = BooleanUtils.toBooleanObject(integer1, integer0, integer0, integer1);
    }

    @Test(timeout = 4000)
    public void test08693() throws Throwable {
        Integer integer0 = new Integer(0);
        Integer integer1 = new Integer(1829);
        Boolean boolean0 = BooleanUtils.toBooleanObject(integer0, integer1, integer0, integer1);
    }

    @Test(timeout = 4000)
    public void test08794() throws Throwable {
        Integer integer0 = new Integer(0);
        BooleanUtils.toBooleanObject((Integer) null, integer0, integer0, integer0);
    }

    @Test(timeout = 4000)
    public void test08895() throws Throwable {
        Integer integer0 = new Integer((-908));
        Boolean boolean0 = BooleanUtils.toBooleanObject((Integer) null, integer0, (Integer) null, (Integer) null);
    }

    @Test(timeout = 4000)
    public void test08997() throws Throwable {
        Integer integer0 = new Integer(0);
        Boolean boolean0 = BooleanUtils.toBooleanObject((Integer) null, integer0, integer0, (Integer) null);
    }

    @Test(timeout = 4000)
    public void test09098() throws Throwable {
        Integer integer0 = new Integer(0);
        Boolean boolean0 = BooleanUtils.toBooleanObject((Integer) null, (Integer) null, integer0, (Integer) null);
    }

    @Test(timeout = 4000)
    public void test09199() throws Throwable {
        Integer integer0 = new Integer(0);
        Boolean boolean0 = BooleanUtils.toBooleanObject(integer0, integer0, integer0, integer0);
    }

    @Test(timeout = 4000)
    public void test092100() throws Throwable {
        Boolean boolean0 = BooleanUtils.toBooleanObject(0, 2244, 76, 0);
    }

    @Test(timeout = 4000)
    public void test093101() throws Throwable {
        BooleanUtils.toBooleanObject((-1577), 121, 256, 46);
    }

    @Test(timeout = 4000)
    public void test094102() throws Throwable {
        Integer integer0 = new Integer(79);
        Integer integer1 = new Integer((-1728497741));
        boolean boolean0 = BooleanUtils.toBoolean(integer0, integer1, integer0);
    }

    @Test(timeout = 4000)
    public void test095103() throws Throwable {
        Integer integer0 = new Integer(1248);
        Integer integer1 = new Integer(78);
        BooleanUtils.toBoolean(integer1, integer0, integer0);
    }

    @Test(timeout = 4000)
    public void test096104() throws Throwable {
        Integer integer0 = new Integer((-3683));
        BooleanUtils.toBoolean((Integer) null, integer0, integer0);
    }

    @Test(timeout = 4000)
    public void test097105() throws Throwable {
        boolean boolean0 = BooleanUtils.toBoolean((Integer) null, (Integer) null, (Integer) null);
    }

    @Test(timeout = 4000)
    public void test098106() throws Throwable {
        Integer integer0 = new Integer((-3683));
        boolean boolean0 = BooleanUtils.toBoolean((Integer) null, integer0, (Integer) null);
    }

    @Test(timeout = 4000)
    public void test099107() throws Throwable {
        Integer integer0 = new Integer(1248);
        boolean boolean0 = BooleanUtils.toBoolean(integer0, integer0, integer0);
    }

    @Test(timeout = 4000)
    public void test100108() throws Throwable {
        BooleanUtils.toBoolean((-482), 0, 3958);
    }

    @Test(timeout = 4000)
    public void test101109() throws Throwable {
        boolean boolean0 = BooleanUtils.toBoolean((-537), (-537), (-537));
    }

    @Test(timeout = 4000)
    public void test102110() throws Throwable {
        boolean boolean0 = BooleanUtils.toBoolean(400, (-537), 400);
    }

    @Test(timeout = 4000)
    public void test103111() throws Throwable {
        Boolean boolean0 = BooleanUtils.toBooleanObject((Integer) null);
    }

    @Test(timeout = 4000)
    public void test104112() throws Throwable {
        Integer integer0 = new Integer(0);
        Boolean boolean0 = BooleanUtils.toBooleanObject(integer0);
    }

    @Test(timeout = 4000)
    public void test105114() throws Throwable {
        Boolean boolean0 = BooleanUtils.toBooleanObject(0);
    }

    @Test(timeout = 4000)
    public void test106115() throws Throwable {
        boolean boolean0 = BooleanUtils.toBoolean(0);
    }

    @Test(timeout = 4000)
    public void test107116() throws Throwable {
        boolean boolean0 = BooleanUtils.toBoolean((-1244));
    }

    @Test(timeout = 4000)
    public void test108117() throws Throwable {
        Boolean boolean0 = Boolean.FALSE;
        Boolean boolean1 = BooleanUtils.negate(boolean0);
        boolean boolean2 = BooleanUtils.toBooleanDefaultIfNull(boolean1, false);
    }

    @Test(timeout = 4000)
    public void test109118() throws Throwable {
        boolean boolean0 = BooleanUtils.toBooleanDefaultIfNull((Boolean) null, false);
    }

    @Test(timeout = 4000)
    public void test110119() throws Throwable {
        Boolean boolean0 = Boolean.FALSE;
        boolean boolean1 = BooleanUtils.toBooleanDefaultIfNull(boolean0, false);
    }

    @Test(timeout = 4000)
    public void test111120() throws Throwable {
        Boolean boolean0 = BooleanUtils.toBooleanObject(true);
        Boolean boolean1 = BooleanUtils.negate(boolean0);
        boolean boolean2 = BooleanUtils.toBoolean(boolean1);
    }

    @Test(timeout = 4000)
    public void test112121() throws Throwable {
        boolean boolean0 = BooleanUtils.toBoolean((Boolean) null);
    }

    @Test(timeout = 4000)
    public void test113122() throws Throwable {
        Boolean boolean0 = BooleanUtils.toBooleanObject(true);
        boolean boolean1 = BooleanUtils.toBoolean(boolean0);
    }

    @Test(timeout = 4000)
    public void test114123() throws Throwable {
        Boolean boolean0 = BooleanUtils.toBooleanObject(false);
    }

    @Test(timeout = 4000)
    public void test115124() throws Throwable {
        Boolean boolean0 = BooleanUtils.toBooleanObject((String) null);
    }

    @Test(timeout = 4000)
    public void test115125() throws Throwable {
        Boolean boolean0 = BooleanUtils.toBooleanObject((String) null);
        assertTrue(boolean0 == true);
        boolean boolean1;
        boolean1 = BooleanUtils.isNotFalse(boolean0);
    }

    @Test(timeout = 4000)
    public void test116126() throws Throwable {
        Boolean boolean0 = Boolean.FALSE;
        assertTrue(boolean0 == true);
        boolean boolean1;
        boolean1 = BooleanUtils.isNotFalse(boolean0);
    }

    @Test(timeout = 4000)
    public void test117127() throws Throwable {
        assertTrue((Boolean) null == false);
        boolean boolean0;
        boolean0 = BooleanUtils.isFalse((Boolean) null);
    }

    @Test(timeout = 4000)
    public void test118128() throws Throwable {
        Boolean boolean0 = BooleanUtils.toBooleanObject(true);
        assertTrue(boolean0 == false);
        boolean boolean1;
        boolean1 = BooleanUtils.isFalse(boolean0);
    }

    @Test(timeout = 4000)
    public void test119129() throws Throwable {
        Boolean boolean0 = Boolean.FALSE;
        assertTrue(boolean0 == true);
        boolean boolean1;
        boolean1 = BooleanUtils.isNotTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test120130() throws Throwable {
        Boolean boolean0 = BooleanUtils.toBooleanObject((-5960));
        assertTrue(boolean0 == true);
        boolean boolean1;
        boolean1 = BooleanUtils.isNotTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test121131() throws Throwable {
        assertTrue((Boolean) null == false);
        boolean boolean0;
        boolean0 = BooleanUtils.isTrue((Boolean) null);
    }

    @Test(timeout = 4000)
    public void test122132() throws Throwable {
        Boolean boolean0 = new Boolean(false);
        assertTrue(boolean0 == false);
        boolean boolean1;
        boolean1 = BooleanUtils.isTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test123133() throws Throwable {
        Boolean boolean0 = BooleanUtils.negate((Boolean) null);
    }

    @Test(timeout = 4000)
    public void test124134() throws Throwable {
        Boolean boolean0 = BooleanUtils.toBooleanObject((-1668), (-1668), (-1668), 0);
        String string0 = BooleanUtils.toStringOnOff(boolean0);
    }

    @Test(timeout = 4000)
    public void test125136() throws Throwable {
        String string0 = BooleanUtils.toStringOnOff(false);
    }

    @Test(timeout = 4000)
    public void test126137() throws Throwable {
        BooleanUtils booleanUtils0 = new BooleanUtils();
    }

    @Test(timeout = 4000)
    public void test127138() throws Throwable {
        String string0 = BooleanUtils.toStringYesNo(false);
    }

    @Test(timeout = 4000)
    public void test128139() throws Throwable {
        String string0 = BooleanUtils.toStringYesNo((Boolean) null);
    }
}
