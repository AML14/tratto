/*
 * Copyright 2017-2024 the original author or authors.
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
package io.lettuce.core.cluster.api.async;

import java.util.List;
import java.util.Map;

import io.lettuce.core.BitFieldArgs;
import io.lettuce.core.GetExArgs;
import io.lettuce.core.KeyValue;
import io.lettuce.core.SetArgs;
import io.lettuce.core.StrAlgoArgs;
import io.lettuce.core.StringMatchResult;
import io.lettuce.core.output.KeyValueStreamingChannel;

/**
 * Asynchronous executed commands on a node selection for Strings.
 *
 * @param <K> Key type.
 * @param <V> Value type.
 * @author Mark Paluch
 * @since 4.0
 * @generated by io.lettuce.apigenerator.CreateAsyncNodeSelectionClusterApi
 */
public interface NodeSelectionStringAsyncCommands<K, V> {

    /**
     * Append a value to a key.
     *
     * @param key the key.
     * @param value the value.
     * @return Long integer-reply the length of the string after the append operation.
     */
    AsyncExecutions<Long> append(K key, V value);

    /**
     * Count set bits in a string.
     *
     * @param key the key.
     * @return Long integer-reply The number of bits set to 1.
     */
    AsyncExecutions<Long> bitcount(K key);

    /**
     * Count set bits in a string.
     *
     * @param key the key.
     * @param start the start.
     * @param end the end.
     * @return Long integer-reply The number of bits set to 1.
     */
    AsyncExecutions<Long> bitcount(K key, long start, long end);

    /**
     * Execute {@code BITFIELD} with its subcommands.
     *
     * @param key the key.
     * @param bitFieldArgs the args containing subcommands, must not be {@code null}.
     * @return Long bulk-reply the results from the bitfield commands.
     */
    AsyncExecutions<List<Long>> bitfield(K key, BitFieldArgs bitFieldArgs);

    /**
     * Find first bit set or clear in a string.
     *
     * @param key the key.
     * @param state the state.
     * @return Long integer-reply The command returns the position of the first bit set to 1 or 0 according to the request.
     *
     *         If we look for set bits (the bit argument is 1) and the string is empty or composed of just zero bytes, -1 is
     *         returned.
     *
     *         If we look for clear bits (the bit argument is 0) and the string only contains bit set to 1, the function returns
     *         the first bit not part of the string on the right. So if the string is three bytes set to the value 0xff the
     *         command {@code BITPOS key 0} will return 24, since up to bit 23 all the bits are 1.
     *
     *         Basically the function consider the right of the string as padded with zeros if you look for clear bits and
     *         specify no range or the <em>start</em> argument <strong>only</strong>.
     */
    AsyncExecutions<Long> bitpos(K key, boolean state);

    /**
     * Find first bit set or clear in a string.
     *
     * @param key the key.
     * @param state the bit type: long.
     * @param start the start type: long.
     * @return Long integer-reply The command returns the position of the first bit set to 1 or 0 according to the request.
     *
     *         If we look for set bits (the bit argument is 1) and the string is empty or composed of just zero bytes, -1 is
     *         returned.
     *
     *         If we look for clear bits (the bit argument is 0) and the string only contains bit set to 1, the function returns
     *         the first bit not part of the string on the right. So if the string is three bytes set to the value 0xff the
     *         command {@code BITPOS key 0} will return 24, since up to bit 23 all the bits are 1.
     *
     *         Basically the function consider the right of the string as padded with zeros if you look for clear bits and
     *         specify no range or the <em>start</em> argument <strong>only</strong>.
     * @since 5.0.1
     */
    AsyncExecutions<Long> bitpos(K key, boolean state, long start);

    /**
     * Find first bit set or clear in a string.
     *
     * @param key the key.
     * @param state the bit type: long.
     * @param start the start type: long.
     * @param end the end type: long.
     * @return Long integer-reply The command returns the position of the first bit set to 1 or 0 according to the request.
     *
     *         If we look for set bits (the bit argument is 1) and the string is empty or composed of just zero bytes, -1 is
     *         returned.
     *
     *         If we look for clear bits (the bit argument is 0) and the string only contains bit set to 1, the function returns
     *         the first bit not part of the string on the right. So if the string is three bytes set to the value 0xff the
     *         command {@code BITPOS key 0} will return 24, since up to bit 23 all the bits are 1.
     *
     *         Basically the function consider the right of the string as padded with zeros if you look for clear bits and
     *         specify no range or the <em>start</em> argument <strong>only</strong>.
     *
     *         However this behavior changes if you are looking for clear bits and specify a range with both
     *         <strong>start</strong> and <strong>end</strong>. If no clear bit is found in the specified range, the function
     *         returns -1 as the user specified a clear range and there are no 0 bits in that range.
     */
    AsyncExecutions<Long> bitpos(K key, boolean state, long start, long end);

    /**
     * Perform bitwise AND between strings.
     *
     * @param destination result key of the operation.
     * @param keys operation input key names.
     * @return Long integer-reply The size of the string stored in the destination key, that is equal to the size of the longest
     *         input string.
     */
    AsyncExecutions<Long> bitopAnd(K destination, K... keys);

    /**
     * Perform bitwise NOT between strings.
     *
     * @param destination result key of the operation.
     * @param source operation input key names.
     * @return Long integer-reply The size of the string stored in the destination key, that is equal to the size of the longest
     *         input string.
     */
    AsyncExecutions<Long> bitopNot(K destination, K source);

    /**
     * Perform bitwise OR between strings.
     *
     * @param destination result key of the operation.
     * @param keys operation input key names.
     * @return Long integer-reply The size of the string stored in the destination key, that is equal to the size of the longest
     *         input string.
     */
    AsyncExecutions<Long> bitopOr(K destination, K... keys);

    /**
     * Perform bitwise XOR between strings.
     *
     * @param destination result key of the operation.
     * @param keys operation input key names.
     * @return Long integer-reply The size of the string stored in the destination key, that is equal to the size of the longest
     *         input string.
     */
    AsyncExecutions<Long> bitopXor(K destination, K... keys);

    /**
     * Decrement the integer value of a key by one.
     *
     * @param key the key.
     * @return Long integer-reply the value of {@code key} after the decrement.
     */
    AsyncExecutions<Long> decr(K key);

    /**
     * Decrement the integer value of a key by the given number.
     *
     * @param key the key.
     * @param amount the decrement type: long.
     * @return Long integer-reply the value of {@code key} after the decrement.
     */
    AsyncExecutions<Long> decrby(K key, long amount);

    /**
     * Get the value of a key.
     *
     * @param key the key.
     * @return V bulk-string-reply the value of {@code key}, or {@code null} when {@code key} does not exist.
     */
    AsyncExecutions<V> get(K key);

    /**
     * Returns the bit value at offset in the string value stored at key.
     *
     * @param key the key.
     * @param offset the offset type: long.
     * @return Long integer-reply the bit value stored at <em>offset</em>.
     */
    AsyncExecutions<Long> getbit(K key, long offset);

    /**
     * Get the value of key and delete the key.
     *
     * @param key the key.
     * @return V bulk-string-reply the value of {@code key}, or {@code null} when {@code key} does not exist.
     * @since 6.1
     */
    AsyncExecutions<V> getdel(K key);

    /**
     * Get the value of key and optionally set its expiration.
     *
     * @param key the key.
     * @param args the arguments for {@code GETEX}.
     * @return V bulk-string-reply the value of {@code key}, or {@code null} when {@code key} does not exist.
     * @since 6.1
     */
    AsyncExecutions<V> getex(K key, GetExArgs args);

    /**
     * Get a substring of the string stored at a key.
     *
     * @param key the key.
     * @param start the start type: long.
     * @param end the end type: long.
     * @return V bulk-string-reply.
     */
    AsyncExecutions<V> getrange(K key, long start, long end);

    /**
     * Set the string value of a key and return its old value.
     *
     * @param key the key.
     * @param value the value.
     * @return V bulk-string-reply the old value stored at {@code key}, or {@code null} when {@code key} did not exist.
     */
    AsyncExecutions<V> getset(K key, V value);

    /**
     * Increment the integer value of a key by one.
     *
     * @param key the key.
     * @return Long integer-reply the value of {@code key} after the increment.
     */
    AsyncExecutions<Long> incr(K key);

    /**
     * Increment the integer value of a key by the given amount.
     *
     * @param key the key.
     * @param amount the increment type: long.
     * @return Long integer-reply the value of {@code key} after the increment.
     */
    AsyncExecutions<Long> incrby(K key, long amount);

    /**
     * Increment the float value of a key by the given amount.
     *
     * @param key the key.
     * @param amount the increment type: double.
     * @return Double bulk-string-reply the value of {@code key} after the increment.
     */
    AsyncExecutions<Double> incrbyfloat(K key, double amount);

    /**
     * Get the values of all the given keys.
     *
     * @param keys the key.
     * @return List&lt;V&gt; array-reply list of values at the specified keys.
     */
    AsyncExecutions<List<KeyValue<K, V>>> mget(K... keys);

    /**
     * Stream over the values of all the given keys.
     *
     * @param channel the channel.
     * @param keys the keys.
     * @return Long array-reply list of values at the specified keys.
     */
    AsyncExecutions<Long> mget(KeyValueStreamingChannel<K, V> channel, K... keys);

    /**
     * Set multiple keys to multiple values.
     *
     * @param map the map.
     * @return String simple-string-reply always {@code OK} since {@code MSET} can't fail.
     */
    AsyncExecutions<String> mset(Map<K, V> map);

    /**
     * Set multiple keys to multiple values, only if none of the keys exist.
     *
     * @param map the map.
     * @return Boolean integer-reply specifically:
     *
     *         {@code 1} if the all the keys were set. {@code 0} if no key was set (at least one key already existed).
     */
    AsyncExecutions<Boolean> msetnx(Map<K, V> map);

    /**
     * Set the string value of a key.
     *
     * @param key the key.
     * @param value the value.
     * @return String simple-string-reply {@code OK} if {@code SET} was executed correctly.
     */
    AsyncExecutions<String> set(K key, V value);

    /**
     * Set the string value of a key.
     *
     * @param key the key.
     * @param value the value.
     * @param setArgs the setArgs.
     * @return String simple-string-reply {@code OK} if {@code SET} was executed correctly.
     */
    AsyncExecutions<String> set(K key, V value, SetArgs setArgs);

    /**
     * Set the string value of a key and return its old value.
     *
     * @param key the key.
     * @param value the value.
     * @return V bulk-string-reply the old value stored at {@code key}, or {@code null} when {@code key} did not exist.
     * @since 6.1
     */
    AsyncExecutions<V> setGet(K key, V value);

    /**
     * Set the string value of a key and return its old value.
     *
     * @param key the key.
     * @param value the value.
     * @param setArgs the command arguments.
     * @return V bulk-string-reply the old value stored at {@code key}, or {@code null} when {@code key} did not exist.
     * @since 6.1
     */
    AsyncExecutions<V> setGet(K key, V value, SetArgs setArgs);

    /**
     * Sets or clears the bit at offset in the string value stored at key.
     *
     * @param key the key.
     * @param offset the offset type: long.
     * @param value the value type: string.
     * @return Long integer-reply the original bit value stored at <em>offset</em>.
     */
    AsyncExecutions<Long> setbit(K key, long offset, int value);

    /**
     * Set the value and expiration of a key.
     *
     * @param key the key.
     * @param seconds the seconds type: long.
     * @param value the value.
     * @return String simple-string-reply.
     */
    AsyncExecutions<String> setex(K key, long seconds, V value);

    /**
     * Set the value and expiration in milliseconds of a key.
     *
     * @param key the key.
     * @param milliseconds the milliseconds type: long.
     * @param value the value.
     * @return String simple-string-reply.
     */
    AsyncExecutions<String> psetex(K key, long milliseconds, V value);

    /**
     * Set the value of a key, only if the key does not exist.
     *
     * @param key the key.
     * @param value the value.
     * @return Boolean integer-reply specifically:
     *
     *         {@code 1} if the key was set {@code 0} if the key was not set.
     */
    AsyncExecutions<Boolean> setnx(K key, V value);

    /**
     * Overwrite part of a string at key starting at the specified offset.
     *
     * @param key the key.
     * @param offset the offset type: long.
     * @param value the value.
     * @return Long integer-reply the length of the string after it was modified by the command.
     */
    AsyncExecutions<Long> setrange(K key, long offset, V value);

    /**
     * The STRALGO command implements complex algorithms that operate on strings. This method uses the LCS algorithm (longest
     * common substring).
     *
     * <ul>
     * <li>Without modifiers the string representing the longest common substring is returned.</li>
     * <li>When {@link StrAlgoArgs#justLen() LEN} is given the command returns the length of the longest common substring.</li>
     * <li>When {@link StrAlgoArgs#withIdx() IDX} is given the command returns an array with the LCS length and all the ranges
     * in both the strings, start and end offset for each string, where there are matches. When
     * {@link StrAlgoArgs#withMatchLen() WITHMATCHLEN} is given each array representing a match will also have the length of the
     * match.</li>
     * </ul>
     *
     * @param strAlgoArgs command arguments.
     * @return StringMatchResult.
     * @since 6.0
     */
    AsyncExecutions<StringMatchResult> stralgoLcs(StrAlgoArgs strAlgoArgs);

    /**
     * Get the length of the value stored in a key.
     *
     * @param key the key.
     * @return Long integer-reply the length of the string at {@code key}, or {@code 0} when {@code key} does not exist.
     */
    AsyncExecutions<Long> strlen(K key);
}
