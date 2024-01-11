
/*
 * Copyright 2020-2024 the original author or authors.
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
package io.lettuce.core.metrics;

import java.net.SocketAddress;

import io.lettuce.core.protocol.ProtocolKeyword;
import io.lettuce.core.protocol.RedisCommand;

/**
 * Interface defining a method to collect command latency metrics based upon command completion. Command latencies are collected
 * per connection (identified by local/remote tuples of {@link SocketAddress}es) and {@link ProtocolKeyword command type}.
 *
 * @author Mark Paluch
 * @since 6.0
 */
@FunctionalInterface
public interface CommandLatencyRecorder {

    /**
     * Returns a disabled no-op {@link CommandLatencyRecorder}.
     *
     * @return a disabled {@link CommandLatencyRecorder}.
     */
    static CommandLatencyRecorder disabled() {

        return new CommandLatencyRecorder() {

            @Override
            public void recordCommandLatency(SocketAddress local, SocketAddress remote, ProtocolKeyword commandType,
                    long firstResponseLatency, long completionLatency) {
            }

            @Override
            public boolean isEnabled() {
                return false;
            }

        };
    }

    /**
     * Record the command latency per {@code connectionPoint} and {@code commandType}.
     *
     * @param local the local address
     * @param remote the remote address
     * @param command the command
     * @param firstResponseLatency latency value in {@link java.util.concurrent.TimeUnit#NANOSECONDS} from send to the first
     *        response
     * @param completionLatency latency value in {@link java.util.concurrent.TimeUnit#NANOSECONDS} from send to the command
     *        completion
     * @since 6.3
     */
    default void recordCommandLatency(SocketAddress local, SocketAddress remote, RedisCommand<?, ?, ?> command,
            long firstResponseLatency, long completionLatency) {
        recordCommandLatency(local, remote, command.getType(), firstResponseLatency, completionLatency);
    }

    /**
     * Record the command latency per {@code connectionPoint} and {@code commandType}.
     *
     * @param local the local address
     * @param remote the remote address
     * @param commandType the command type
     * @param firstResponseLatency latency value in {@link java.util.concurrent.TimeUnit#NANOSECONDS} from send to the first
     *        response
     * @param completionLatency latency value in {@link java.util.concurrent.TimeUnit#NANOSECONDS} from send to the command
     *        completion
     */
    void recordCommandLatency(SocketAddress local, SocketAddress remote, ProtocolKeyword commandType, long firstResponseLatency,
            long completionLatency);

    /**
     * Returns {@code true} if the metric collector is enabled.
     *
     * @return {@code true} if the metric collector is enabled
     */
    default boolean isEnabled() {
        return true;
    }

}
