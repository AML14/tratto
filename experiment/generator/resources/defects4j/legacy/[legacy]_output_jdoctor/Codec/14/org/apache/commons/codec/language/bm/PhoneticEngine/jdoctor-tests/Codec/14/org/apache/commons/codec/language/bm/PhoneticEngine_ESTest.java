/*
 * This file was automatically generated by EvoSuite
 * Mon Nov 20 01:01:47 GMT 2023
 */
package org.apache.commons.codec.language.bm;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;
import org.apache.commons.codec.language.bm.Languages;
import org.apache.commons.codec.language.bm.NameType;
import org.apache.commons.codec.language.bm.PhoneticEngine;
import org.apache.commons.codec.language.bm.Rule;
import org.apache.commons.codec.language.bm.RuleType;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true)
public class PhoneticEngine_ESTest extends PhoneticEngine_ESTest_scaffolding {

    @Test(timeout = 4000)
    public void test00() throws Throwable {
        LinkedHashSet<String> linkedHashSet0 = new LinkedHashSet<String>();
        linkedHashSet0.add("r");
        Languages.LanguageSet languages_LanguageSet0 = Languages.LanguageSet.from(linkedHashSet0);
        PhoneticEngine.PhonemeBuilder phoneticEngine_PhonemeBuilder0 = PhoneticEngine.PhonemeBuilder.empty(languages_LanguageSet0);
        Set<Rule.Phoneme> set0 = phoneticEngine_PhonemeBuilder0.getPhonemes();
        set0.isEmpty();
    }

    @Test(timeout = 4000)
    public void test11() throws Throwable {
        NameType nameType0 = NameType.ASHKENAZI;
        RuleType ruleType0 = RuleType.APPROX;
        PhoneticEngine phoneticEngine0 = null;
        phoneticEngine0 = new PhoneticEngine(nameType0, ruleType0, true);
    }

    @Test(timeout = 4000)
    public void test42() throws Throwable {
        PhoneticEngine.PhonemeBuilder phoneticEngine_PhonemeBuilder0 = PhoneticEngine.PhonemeBuilder.empty((Languages.LanguageSet) null);
        Rule.Phoneme rule_Phoneme0 = new Rule.Phoneme("", (Languages.LanguageSet) null);
        phoneticEngine_PhonemeBuilder0.apply(rule_Phoneme0, 2);
    }

    @Test(timeout = 4000)
    public void test53() throws Throwable {
        PhoneticEngine.PhonemeBuilder phoneticEngine_PhonemeBuilder0 = PhoneticEngine.PhonemeBuilder.empty((Languages.LanguageSet) null);
        String string0 = phoneticEngine_PhonemeBuilder0.makeString();
    }

    @Test(timeout = 4000)
    public void test64() throws Throwable {
        NameType nameType0 = NameType.ASHKENAZI;
        RuleType ruleType0 = RuleType.RULES;
        PhoneticEngine phoneticEngine0 = null;
        phoneticEngine0 = new PhoneticEngine(nameType0, ruleType0, true);
    }
}
