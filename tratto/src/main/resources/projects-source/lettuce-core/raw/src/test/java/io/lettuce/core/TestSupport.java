/*
 * Copyright 2011-2024 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.lettuce.core;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import io.lettuce.core.internal.LettuceSets;
import io.lettuce.test.settings.TestSettings;

/**
 * @author Mark Paluch
 * @author Tugdual Grall
 */
public abstract class TestSupport {

    public static final String host = TestSettings.hostAddr();
    public static final int port = TestSettings.port();
    public static final String username = TestSettings.username();

    public static final CharSequence passwd = TestSettings.password();

    public static final String aclUsername = TestSettings.aclUsername();

    public static final CharSequence aclPasswd = TestSettings.aclPassword();

    public static final String key = "key";
    public static final String value = "value";

    protected static List<String> list(String... args) {
        return Arrays.asList(args);
    }

    protected static List<Object> list(Object... args) {
        return Arrays.asList(args);
    }

    protected static List<ScoredValue<String>> svlist(ScoredValue<String>... args) {
        return Arrays.asList(args);
    }

    protected static <K, V> KeyValue<K, V> kv(K key, V value) {
        return KeyValue.fromNullable(key, value);
    }

    protected static ScoredValue<String> sv(double score, String value) {
        return ScoredValue.just(score, value);
    }

    protected static ScoredValue<Long> sv(double score, long value) {
        return ScoredValue.just(score, value);
    }

    protected static Set<String> set(String... args) {
        return LettuceSets.newHashSet(args);
    }
}
