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
package io.lettuce.core.commands;

import static io.lettuce.core.ScriptOutputType.*;
import static io.lettuce.core.ScriptOutputType.BOOLEAN;
import static io.lettuce.core.ScriptOutputType.INTEGER;
import static org.assertj.core.api.Assertions.*;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;

import io.lettuce.core.FlushMode;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisException;
import io.lettuce.core.RedisNoScriptException;
import io.lettuce.core.TestSupport;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.test.LettuceExtension;
import io.lettuce.test.Wait;
import io.lettuce.test.condition.EnabledOnCommand;

/**
 * Integration tests for scripting commands.
 *
 * @author Will Glozer
 * @author Mark Paluch
 * @author dengliming
 */
@ExtendWith(LettuceExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ScriptingCommandIntegrationTests extends TestSupport {

    private final RedisClient client;

    private final RedisCommands<String, String> redis;

    @Inject
    protected ScriptingCommandIntegrationTests(RedisClient client, RedisCommands<String, String> redis) {
        this.client = client;
        this.redis = redis;
    }

    @BeforeEach
    void setUp() {
        this.redis.flushall();
    }

    @AfterEach
    void tearDown() {

        Wait.untilNoException(() -> {
            try {
                redis.scriptKill();
            } catch (RedisException e) {
                // ignore
            }
            redis.ping();
        }).waitOrTimeout();
    }

    @Test
    void eval() {

        redis.set(key, "true");
        assertThat((Boolean) redis.eval("return 1 + 1 == 4", BOOLEAN)).isEqualTo(false);
        assertThat((Boolean) redis.eval("return redis.call('GET', KEYS[1])", BOOLEAN, key)).isEqualTo(true);

        redis.set(key, "1");

        assertThat((Boolean) redis.eval("return 1 + 1 == 4", BOOLEAN)).isEqualTo(false);
        assertThat((Number) redis.eval("return 1 + 1", INTEGER)).isEqualTo(2L);
        assertThat((String) redis.eval("return redis.call('GET', KEYS[1])", VALUE, key)).isEqualTo("1");
        assertThat((Long) redis.eval("return redis.call('GET', KEYS[1])", INTEGER, key)).isEqualTo(1L);
        assertThat((String) redis.eval("return {ok='status'}", STATUS)).isEqualTo("status");
        assertThat((String) redis.eval("return 'one'", VALUE)).isEqualTo("one");
        assertThat((List<?>) redis.eval("return {1, 'one', {2}}", MULTI)).isEqualTo(list(1L, "one", list(2L)));

        assertThatThrownBy(() -> redis.eval("return {err='Oops!'}", STATUS)).hasMessageContaining("Oops!");
    }

    @Test
    void evalWithSingleKey() {
        assertThat((List<?>) redis.eval("return KEYS[1]", MULTI, "one")).isEqualTo(list("one"));
    }

    @Test
    void evalWithNonAsciiChar() {
        assertThat((Object) redis.eval("return 'füö'", VALUE, "one")).isEqualTo("füö");
    }

    @Test
    void evalReturningNullInMulti() {
        assertThat((List<?>) redis.eval("return nil", MULTI, "one")).isEqualTo(Collections.singletonList(null));
    }

    @Test
    void evalWithKeys() {
        assertThat((List<?>) redis.eval("return {KEYS[1], KEYS[2]}", MULTI, "one", "two")).isEqualTo(list("one", "two"));
    }

    @Test
    void evalWithArgs() {
        String[] keys = new String[0];
        assertThat((List<?>) redis.eval("return {ARGV[1], ARGV[2]}", MULTI, keys, "a", "b")).isEqualTo(list("a", "b"));
    }

    @Test
    @EnabledOnCommand("EVAL_RO") // Redis 7.0
    void evalReadOnly() {
        String[] keys = new String[] { "key1" };
        assertThat((String) redis.evalReadOnly("return KEYS[1]".getBytes(), STATUS, keys, "a")).isEqualTo("key1");
    }

    @Test
    void evalsha() {
        redis.scriptFlush();
        String script = "return 1 + 1";
        String digest = redis.digest(script);
        assertThat((Number) redis.eval(script, INTEGER)).isEqualTo(2L);
        assertThat((Number) redis.evalsha(digest, INTEGER)).isEqualTo(2L);

        assertThatThrownBy(() -> redis.evalsha(redis.digest("return 1 + 1 == 4"), INTEGER))
                .isInstanceOf(RedisNoScriptException.class)
                .hasMessageContaining("NOSCRIPT No matching script. Please use EVAL.");
    }

    @Test
    void evalshaWithMulti() {
        redis.scriptFlush();
        String digest = redis.digest("return {1234, 5678}");

        assertThatThrownBy(() -> redis.evalsha(digest, MULTI)).isInstanceOf(RedisNoScriptException.class)
                .hasMessageContaining("NOSCRIPT No matching script. Please use EVAL.");
    }

    @Test
    void evalshaWithKeys() {
        redis.scriptFlush();
        String digest = redis.scriptLoad("return {KEYS[1], KEYS[2]}");
        assertThat((Object) redis.evalsha(digest, MULTI, "one", "two")).isEqualTo(list("one", "two"));
    }

    @Test
    void evalshaWithArgs() {
        redis.scriptFlush();
        String digest = redis.scriptLoad("return {ARGV[1], ARGV[2]}");
        String[] keys = new String[0];
        assertThat((Object) redis.evalsha(digest, MULTI, keys, "a", "b")).isEqualTo(list("a", "b"));
    }

    @Test
    @EnabledOnCommand("EVALSHA_RO") // Redis 7.0
    void evalshaReadOnly() {
        redis.scriptFlush();
        redis.set("foo", "bar");

        String digest = redis.scriptLoad("return redis.call('get','foo')");
        String[] keys = new String[0];
        assertThat((String) redis.evalshaReadOnly(digest, STATUS, keys)).isEqualTo("bar");
    }

    @Test
    void script() throws InterruptedException {
        assertThat(redis.scriptFlush()).isEqualTo("OK");

        String script1 = "return 1 + 1";
        String digest1 = redis.digest(script1);
        String script2 = "return 1 + 1 == 4";
        String digest2 = redis.digest(script2);

        assertThat(redis.scriptExists(digest1, digest2)).isEqualTo(list(false, false));
        assertThat(redis.scriptLoad(script1)).isEqualTo(digest1);
        assertThat((Object) redis.evalsha(digest1, INTEGER)).isEqualTo(2L);
        assertThat(redis.scriptExists(digest1, digest2)).isEqualTo(list(true, false));

        assertThat(redis.scriptFlush()).isEqualTo("OK");
        assertThat(redis.scriptExists(digest1, digest2)).isEqualTo(list(false, false));
    }

    @Test
    @EnabledOnCommand("XAUTOCLAIM") // Redis 6.2
    void scriptFlushAsync() {
        assertThat(redis.scriptFlush()).isEqualTo("OK");

        String script1 = "return 1 + 1";
        String digest1 = redis.digest(script1);

        assertThat(redis.scriptExists(digest1)).isEqualTo(list(false));
        assertThat(redis.scriptLoad(script1)).isEqualTo(digest1);
        assertThat(redis.scriptExists(digest1)).isEqualTo(list(true));
        assertThat(redis.scriptFlush(FlushMode.ASYNC)).isEqualTo("OK");
        assertThat(redis.scriptExists(digest1)).isEqualTo(list(false));
    }

    @Test
    @EnabledOnCommand("XAUTOCLAIM") // Redis 6.2
    void scriptFlushSync() {
        assertThat(redis.scriptFlush()).isEqualTo("OK");

        String script1 = "return 1 + 1";
        String digest1 = redis.digest(script1);

        assertThat(redis.scriptExists(digest1)).isEqualTo(list(false));
        assertThat(redis.scriptLoad(script1)).isEqualTo(digest1);
        assertThat(redis.scriptExists(digest1)).isEqualTo(list(true));
        assertThat(redis.scriptFlush(FlushMode.SYNC)).isEqualTo("OK");
        assertThat(redis.scriptExists(digest1)).isEqualTo(list(false));
    }

}
