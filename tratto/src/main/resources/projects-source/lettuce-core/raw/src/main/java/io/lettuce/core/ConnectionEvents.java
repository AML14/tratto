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

import java.net.SocketAddress;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Close Events Facility. Can register/unregister CloseListener and fire a closed event to all registered listeners. This class
 * is part of the internal API and may change without further notice.
 *
 * @author Mark Paluch
 * @since 3.0
 */
public class ConnectionEvents {

    private final Set<RedisConnectionStateListener> listeners = ConcurrentHashMap.newKeySet();

    void fireEventRedisConnected(RedisChannelHandler<?, ?> connection, SocketAddress socketAddress) {
        for (RedisConnectionStateListener listener : listeners) {
            listener.onRedisConnected(connection, socketAddress);
        }
    }

    void fireEventRedisDisconnected(RedisChannelHandler<?, ?> connection) {
        for (RedisConnectionStateListener listener : listeners) {
            listener.onRedisDisconnected(connection);
        }
    }

    void fireEventRedisExceptionCaught(RedisChannelHandler<?, ?> connection, Throwable cause) {
        for (RedisConnectionStateListener listener : listeners) {
            listener.onRedisExceptionCaught(connection, cause);
        }
    }

    public void addListener(RedisConnectionStateListener listener) {
        listeners.add(listener);
    }

    public void removeListener(RedisConnectionStateListener listener) {
        listeners.remove(listener);
    }

    /**
     * Internal event when a channel is closed.
     */
    public static class Reset {
    }

    /**
     * Internal event when a reconnect is initiated.
     */
    public static class Reconnect {

        private final int attempt;

        public Reconnect(int attempt) {
            this.attempt = attempt;
        }

        public int getAttempt() {
            return attempt;
        }

    }

    static ConnectionEvents of(ConnectionEvents... delegates) {
        return delegates.length == 1 ? delegates[0] : new MergedConnectionEvents(delegates);
    }

    static class MergedConnectionEvents extends ConnectionEvents {

        private final ConnectionEvents[] delegates;

        MergedConnectionEvents(ConnectionEvents[] delegates) {
            this.delegates = delegates;
        }

        @Override
        void fireEventRedisConnected(RedisChannelHandler<?, ?> connection, SocketAddress socketAddress) {

            for (ConnectionEvents delegate : delegates) {
                delegate.fireEventRedisConnected(connection, socketAddress);
            }
        }

        @Override
        void fireEventRedisDisconnected(RedisChannelHandler<?, ?> connection) {

            for (ConnectionEvents delegate : delegates) {
                delegate.fireEventRedisDisconnected(connection);
            }
        }

        @Override
        void fireEventRedisExceptionCaught(RedisChannelHandler<?, ?> connection, Throwable cause) {

            for (ConnectionEvents delegate : delegates) {
                delegate.fireEventRedisExceptionCaught(connection, cause);
            }
        }

        @Override
        public void addListener(RedisConnectionStateListener listener) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void removeListener(RedisConnectionStateListener listener) {
            throw new UnsupportedOperationException();
        }

    }

}
