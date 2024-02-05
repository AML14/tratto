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

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;

import io.lettuce.core.LMPopArgs;
import io.lettuce.core.LMoveArgs;
import io.lettuce.core.LPosArgs;
import io.lettuce.core.TestSupport;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.test.LettuceExtension;
import io.lettuce.test.ListStreamingAdapter;
import io.lettuce.test.condition.EnabledOnCommand;
import io.lettuce.test.condition.RedisConditions;

/**
 * Integration tests for {@link io.lettuce.core.api.sync.RedisListCommands}.
 *
 * @author Will Glozer
 * @author Mark Paluch
 * @author Mikhael Sokolov
 * @author M Sazzadul Hoque
 */
@ExtendWith(LettuceExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ListCommandIntegrationTests extends TestSupport {

    private final RedisCommands<String, String> redis;

    @Inject
    protected ListCommandIntegrationTests(RedisCommands<String, String> redis) {
        this.redis = redis;
    }

    @BeforeEach
    void setUp() {
        this.redis.flushall();
    }

    @Test
    void blpop() {
        redis.rpush("two", "2", "3");
        assertThat(redis.blpop(1, "one", "two")).isEqualTo(kv("two", "2"));
    }

    @Test
    @EnabledOnCommand("BLMOVE") // Redis 6.2
    void blpopDoubleTimeout() {
        redis.rpush("two", "2", "3");
        assertThat(redis.blpop(0.1, "one", "two")).isEqualTo(kv("two", "2"));
        assertThat(redis.blpop(0.1, "one", "two")).isEqualTo(kv("two", "3"));
        assertThat(redis.blpop(0.1, "one", "two")).isNull();
    }

    @Test
    @EnabledOnCommand("BLMPOP") // Redis 7.0
    void blmpop() {
        redis.rpush("{0}two", "1", "2", "3");
        LMPopArgs args = LMPopArgs.Builder.left().count(2);
        assertThat(redis.blmpop(0.1, args, "{0}one", "{0}two")).isEqualTo(kv("{0}two", Arrays.asList("1", "2")));
        assertThat(redis.blmpop(0.1, args, "{0}one", "{0}two")).isEqualTo(kv("{0}two", Collections.singletonList("3")));
        assertThat(redis.blmpop(0.1, args, "{0}one", "{0}two")).isNull();
    }

    @Test
    void blpopTimeout() {
        redis.setTimeout(Duration.ofSeconds(10));
        assertThat(redis.blpop(1, key)).isNull();
    }

    @Test
    void brpop() {
        redis.rpush("two", "2", "3");
        assertThat(redis.brpop(1, "one", "two")).isEqualTo(kv("two", "3"));
    }

    @Test
    @EnabledOnCommand("BLMOVE") // Redis 6.2
    void brpopDoubleTimeout() {
        redis.rpush("two", "2", "3");
        assertThat(redis.brpop(0.5, "one", "two")).isEqualTo(kv("two", "3"));
    }

    @Test
    void brpoplpush() {
        redis.rpush("one", "1", "2");
        redis.rpush("two", "3", "4");
        assertThat(redis.brpoplpush(1, "one", "two")).isEqualTo("2");
        assertThat(redis.lrange("one", 0, -1)).isEqualTo(list("1"));
        assertThat(redis.lrange("two", 0, -1)).isEqualTo(list("2", "3", "4"));
    }

    @Test
    @EnabledOnCommand("BLMOVE") // Redis 6.2
    void brpoplpushDoubleTimeout() {
        redis.rpush("one", "1", "2");
        redis.rpush("two", "3", "4");
        assertThat(redis.brpoplpush(0.5, "one", "two")).isEqualTo("2");
        assertThat(redis.lrange("one", 0, -1)).isEqualTo(list("1"));
        assertThat(redis.lrange("two", 0, -1)).isEqualTo(list("2", "3", "4"));
    }

    @Test
    void lindex() {
        assertThat(redis.lindex(key, 0)).isNull();
        redis.rpush(key, "one");
        assertThat(redis.lindex(key, 0)).isEqualTo("one");
    }

    @Test
    void linsert() {
        assertThat(redis.linsert(key, false, "one", "two")).isEqualTo(0);
        redis.rpush(key, "one");
        redis.rpush(key, "three");
        assertThat(redis.linsert(key, true, "three", "two")).isEqualTo(3);
        assertThat(redis.lrange(key, 0, -1)).isEqualTo(list("one", "two", "three"));
    }

    @Test
    void llen() {
        assertThat((long) redis.llen(key)).isEqualTo(0);
        redis.lpush(key, "one");
        assertThat((long) redis.llen(key)).isEqualTo(1);
    }

    @Test
    @EnabledOnCommand("LMPOP") // Redis 7.0
    void lmpop() {
        redis.rpush("{0}two", "1", "2", "3", "4");
        redis.rpush("{0}one", "1");
        assertThat(redis.lmpop(LMPopArgs.Builder.left().count(2), "{0}two")).isEqualTo(kv("{0}two", Arrays.asList("1", "2")));
        assertThat(redis.lmpop(LMPopArgs.Builder.right().count(1), "{0}two"))
                .isEqualTo(kv("{0}two", Collections.singletonList("4")));
        assertThat(redis.lmpop(LMPopArgs.Builder.right().count(2), "{0}two", "{0}one"))
                .isEqualTo(kv("{0}two", Collections.singletonList("3")));
        assertThat(redis.lmpop(LMPopArgs.Builder.right().count(1), "{0}two")).isNull();
    }

    @Test
    void lpop() {
        assertThat(redis.lpop(key)).isNull();
        redis.rpush(key, "one", "two");
        assertThat(redis.lpop(key)).isEqualTo("one");
        assertThat(redis.lrange(key, 0, -1)).isEqualTo(list("two"));
    }

    @Test
    @EnabledOnCommand("BLMOVE") // Redis 6.2
    void lpopCount() {
        assertThat(redis.lpop(key, 1)).isEqualTo(list());
        redis.rpush(key, "one", "two");
        assertThat(redis.lpop(key, 3)).isEqualTo(list("one", "two"));
    }

    @Test
    @EnabledOnCommand("LPOS")
    void lpos() {

        redis.rpush(key, "a", "b", "c", "1", "2", "3", "c", "c");

        assertThat(redis.lpos(key, "a")).isEqualTo(0);
        assertThat(redis.lpos(key, "c")).isEqualTo(2);
        assertThat(redis.lpos(key, "c", LPosArgs.Builder.rank(1))).isEqualTo(2);
        assertThat(redis.lpos(key, "c", LPosArgs.Builder.rank(2))).isEqualTo(6);
        assertThat(redis.lpos(key, "c", LPosArgs.Builder.rank(4))).isNull();

        assertThat(redis.lpos(key, "c", 0)).contains(2L, 6L, 7L);
        assertThat(redis.lpos(key, "c", 0, LPosArgs.Builder.maxlen(1))).isEmpty();
    }

    @Test
    void lpush() {
        assertThat((long) redis.lpush(key, "two")).isEqualTo(1);
        assertThat((long) redis.lpush(key, "one")).isEqualTo(2);
        assertThat(redis.lrange(key, 0, -1)).isEqualTo(list("one", "two"));
        assertThat((long) redis.lpush(key, "three", "four")).isEqualTo(4);
        assertThat(redis.lrange(key, 0, -1)).isEqualTo(list("four", "three", "one", "two"));
    }

    @Test
    void lpushx() {
        assertThat((long) redis.lpushx(key, "two")).isEqualTo(0);
        redis.lpush(key, "two");
        assertThat((long) redis.lpushx(key, "one")).isEqualTo(2);
        assertThat(redis.lrange(key, 0, -1)).isEqualTo(list("one", "two"));
    }

    @Test
    void lpushxVariadic() {

        assumeTrue(RedisConditions.of(redis).hasCommandArity("LPUSHX", -3));

        assertThat((long) redis.lpushx(key, "one", "two")).isEqualTo(0);
        redis.lpush(key, "two");
        assertThat((long) redis.lpushx(key, "one", "zero")).isEqualTo(3);
        assertThat(redis.lrange(key, 0, -1)).isEqualTo(list("zero", "one", "two"));
    }

    @Test
    void lrange() {
        assertThat(redis.lrange(key, 0, 10).isEmpty()).isTrue();
        redis.rpush(key, "one", "two", "three");
        List<String> range = redis.lrange(key, 0, 1);
        assertThat(range).hasSize(2);
        assertThat(range.get(0)).isEqualTo("one");
        assertThat(range.get(1)).isEqualTo("two");
        assertThat(redis.lrange(key, 0, -1)).hasSize(3);
    }

    @Test
    void lrangeStreaming() {
        assertThat(redis.lrange(key, 0, 10).isEmpty()).isTrue();
        redis.rpush(key, "one", "two", "three");

        ListStreamingAdapter<String> adapter = new ListStreamingAdapter<>();

        Long count = redis.lrange(adapter, key, 0, 1);
        assertThat(count.longValue()).isEqualTo(2);

        List<String> range = adapter.getList();

        assertThat(range).hasSize(2);
        assertThat(range.get(0)).isEqualTo("one");
        assertThat(range.get(1)).isEqualTo("two");
        assertThat(redis.lrange(key, 0, -1)).hasSize(3);
    }

    @Test
    void lrem() {
        assertThat(redis.lrem(key, 0, value)).isEqualTo(0);

        redis.rpush(key, "1", "2", "1", "2", "1");
        assertThat((long) redis.lrem(key, 1, "1")).isEqualTo(1);
        assertThat(redis.lrange(key, 0, -1)).isEqualTo(list("2", "1", "2", "1"));

        redis.lpush(key, "1");
        assertThat((long) redis.lrem(key, -1, "1")).isEqualTo(1);
        assertThat(redis.lrange(key, 0, -1)).isEqualTo(list("1", "2", "1", "2"));

        redis.lpush(key, "1");
        assertThat((long) redis.lrem(key, 0, "1")).isEqualTo(3);
        assertThat(redis.lrange(key, 0, -1)).isEqualTo(list("2", "2"));
    }

    @Test
    void lset() {
        redis.rpush(key, "one", "two", "three");
        assertThat(redis.lset(key, 2, "san")).isEqualTo("OK");
        assertThat(redis.lrange(key, 0, -1)).isEqualTo(list("one", "two", "san"));
    }

    @Test
    void ltrim() {
        redis.rpush(key, "1", "2", "3", "4", "5", "6");
        assertThat(redis.ltrim(key, 0, 3)).isEqualTo("OK");
        assertThat(redis.lrange(key, 0, -1)).isEqualTo(list("1", "2", "3", "4"));
        assertThat(redis.ltrim(key, -2, -1)).isEqualTo("OK");
        assertThat(redis.lrange(key, 0, -1)).isEqualTo(list("3", "4"));
    }

    @Test
    void rpop() {
        assertThat(redis.rpop(key)).isNull();
        redis.rpush(key, "one", "two");
        assertThat(redis.rpop(key)).isEqualTo("two");
        assertThat(redis.lrange(key, 0, -1)).isEqualTo(list("one"));
    }

    @Test
    @EnabledOnCommand("BLMOVE") // Redis 6.2
    void rpopCount() {
        assertThat(redis.rpop(key, 1)).isEqualTo(list());
        redis.rpush(key, "one", "two");
        assertThat(redis.rpop(key, 3)).isEqualTo(list("two", "one"));
    }

    @Test
    void rpoplpush() {
        assertThat(redis.rpoplpush("one", "two")).isNull();
        redis.rpush("one", "1", "2");
        redis.rpush("two", "3", "4");
        assertThat(redis.rpoplpush("one", "two")).isEqualTo("2");
        assertThat(redis.lrange("one", 0, -1)).isEqualTo(list("1"));
        assertThat(redis.lrange("two", 0, -1)).isEqualTo(list("2", "3", "4"));
    }

    @Test
    void rpush() {
        assertThat((long) redis.rpush(key, "one")).isEqualTo(1);
        assertThat((long) redis.rpush(key, "two")).isEqualTo(2);
        assertThat(redis.lrange(key, 0, -1)).isEqualTo(list("one", "two"));
        assertThat((long) redis.rpush(key, "three", "four")).isEqualTo(4);
        assertThat(redis.lrange(key, 0, -1)).isEqualTo(list("one", "two", "three", "four"));
    }

    @Test
    void rpushx() {
        assertThat((long) redis.rpushx(key, "one")).isEqualTo(0);
        redis.rpush(key, "one");
        assertThat((long) redis.rpushx(key, "two")).isEqualTo(2);
        assertThat(redis.lrange(key, 0, -1)).isEqualTo(list("one", "two"));
    }

    @Test
    void rpushxVariadic() {

        assumeTrue(RedisConditions.of(redis).hasCommandArity("RPUSHX", -3));

        assertThat((long) redis.rpushx(key, "two", "three")).isEqualTo(0);
        redis.rpush(key, "one");
        assertThat((long) redis.rpushx(key, "two", "three")).isEqualTo(3);
        assertThat(redis.lrange(key, 0, -1)).isEqualTo(list("one", "two", "three"));
    }

    @Test
    @EnabledOnCommand("LMOVE") // Redis 6.2
    void lmove() {
        String list1 = key;
        String list2 = "38o54"; // yields in same slot as "key"

        redis.rpush(list1, "one", "two", "three");
        redis.lmove(list1, list2, LMoveArgs.Builder.rightLeft());

        assertThat(redis.lrange(list1, 0, -1)).containsExactly("one", "two");
        assertThat(redis.lrange(list2, 0, -1)).containsOnly("three");
    }

    @Test
    @EnabledOnCommand("BLMOVE") // Redis 6.2
    void blmove() {
        String list1 = key;
        String list2 = "38o54"; // yields in same slot as "key"

        redis.rpush(list1, "one", "two", "three");
        redis.blmove(list1, list2, LMoveArgs.Builder.leftRight(), 1000);

        assertThat(redis.lrange(list1, 0, -1)).containsExactly("two", "three");
        assertThat(redis.lrange(list2, 0, -1)).containsOnly("one");
    }

    @Test
    @EnabledOnCommand("BLMOVE") // Redis 6.2
    void blmoveDoubleTimeout() {
        String list1 = key;
        String list2 = "38o54"; // yields in same slot as "key"

        redis.rpush(list1, "one", "two", "three");
        redis.blmove(list1, list2, LMoveArgs.Builder.leftRight(), 1.5);

        assertThat(redis.lrange(list1, 0, -1)).containsExactly("two", "three");
        assertThat(redis.lrange(list2, 0, -1)).containsOnly("one");
    }

}
