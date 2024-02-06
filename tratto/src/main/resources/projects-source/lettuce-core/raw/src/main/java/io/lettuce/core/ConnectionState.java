/*
 * Copyright 2019-2024 the original author or authors.
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

import java.util.List;

import io.lettuce.core.protocol.ProtocolVersion;

/**
 * Internal connection state representing the negotiated {@link ProtocolVersion} and other options for connection initialization
 * and connection state restoration. This class is part of the internal API.
 *
 * @author Mark Paluch
 * @author Jon Iantosca
 * @since 6.0
 */
public class ConnectionState {

    private volatile HandshakeResponse handshakeResponse;

    private volatile RedisCredentialsProvider credentialsProvider;

    private volatile int db;

    private volatile boolean readOnly;

    private volatile ConnectionMetadata connectionMetadata = new ConnectionMetadata();

    /**
     * Applies settings from {@link RedisURI}.
     *
     * @param redisURI the URI to apply the client name and authentication.
     */
    public void apply(RedisURI redisURI) {

        connectionMetadata.apply(redisURI);
        setCredentialsProvider(redisURI.getCredentialsProvider());
    }

    void apply(ConnectionMetadata metadata) {
        this.connectionMetadata.apply(metadata);
    }

    ConnectionMetadata getConnectionMetadata() {
        return connectionMetadata;
    }

    /**
     * Returns the negotiated {@link ProtocolVersion}.
     *
     * @return the negotiated {@link ProtocolVersion} once the connection is established.
     */
    public ProtocolVersion getNegotiatedProtocolVersion() {
        return handshakeResponse != null ? handshakeResponse.getNegotiatedProtocolVersion() : null;
    }

    /**
     * Returns the client connection id. Only available when using {@link ProtocolVersion#RESP3}.
     *
     * @return the client connection id. Can be {@code null} if Redis uses RESP2.
     */
    public Long getConnectionId() {
        return handshakeResponse != null ? handshakeResponse.getConnectionId() : null;
    }

    /**
     * Returns the Redis server version. Only available when using {@link ProtocolVersion#RESP3}.
     *
     * @return the Redis server version.
     */
    public String getRedisVersion() {
        return handshakeResponse != null ? handshakeResponse.getRedisVersion() : null;
    }

    /**
     * Returns the Redis server mode. Only available when using {@link ProtocolVersion#RESP3}.
     *
     * @return the Redis server mode.
     */
    public String getMode() {
        return handshakeResponse != null ? handshakeResponse.getMode() : null;
    }

    /**
     * Returns the Redis server role. Only available when using {@link ProtocolVersion#RESP3}.
     *
     * @return the Redis server role.
     */
    public String getRole() {
        return handshakeResponse != null ? handshakeResponse.getRole() : null;
    }

    void setHandshakeResponse(HandshakeResponse handshakeResponse) {
        this.handshakeResponse = handshakeResponse;
    }

    /**
     * Sets username/password state based on the argument count from an {@code AUTH} command.
     *
     * @param args
     */
    protected void setUserNamePassword(List<char[]> args) {

        if (args.isEmpty()) {
            return;
        }

        if (args.size() > 1) {
            this.credentialsProvider = new StaticCredentialsProvider(new String(args.get(0)), args.get(1));
        } else {
            this.credentialsProvider = new StaticCredentialsProvider(null, args.get(0));
        }
    }

    protected void setCredentialsProvider(RedisCredentialsProvider credentialsProvider) {
        this.credentialsProvider = credentialsProvider;
    }

    public RedisCredentialsProvider getCredentialsProvider() {
        return credentialsProvider;
    }

    protected void setDb(int db) {
        this.db = db;
    }

    int getDb() {
        return db;
    }

    protected void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    boolean isReadOnly() {
        return readOnly;
    }

    protected void setClientName(String clientName) {
        this.connectionMetadata.setClientName(clientName);
    }

    String getClientName() {
        return connectionMetadata.getClientName();
    }

    /**
     * HELLO Handshake response.
     */
    static class HandshakeResponse {

        private final ProtocolVersion negotiatedProtocolVersion;

        private final Long connectionId;

        private final String redisVersion;

        private final String mode;

        private final String role;

        public HandshakeResponse(ProtocolVersion negotiatedProtocolVersion, Long connectionId, String redisVersion, String mode,
                String role) {
            this.negotiatedProtocolVersion = negotiatedProtocolVersion;
            this.connectionId = connectionId;
            this.redisVersion = redisVersion;
            this.role = role;
            this.mode = mode;
        }

        public ProtocolVersion getNegotiatedProtocolVersion() {
            return negotiatedProtocolVersion;
        }

        public Long getConnectionId() {
            return connectionId;
        }

        public String getRedisVersion() {
            return redisVersion;
        }

        public String getMode() {
            return mode;
        }

        public String getRole() {
            return role;
        }

    }

}
