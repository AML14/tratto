/*
 * Copyright 2016-2024 the original author or authors.
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
package io.lettuce.core.cluster;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import io.lettuce.core.AbstractRedisClient;
import io.lettuce.core.ClientOptions;
import io.lettuce.core.RedisChannelWriter;
import io.lettuce.core.RedisException;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.RedisURI;
import io.lettuce.core.cluster.api.push.RedisClusterPushListener;
import io.lettuce.core.cluster.models.partitions.Partitions;
import io.lettuce.core.cluster.models.partitions.RedisClusterNode;
import io.lettuce.core.cluster.pubsub.RedisClusterPubSubListener;
import io.lettuce.core.cluster.pubsub.StatefulRedisClusterPubSubConnection;
import io.lettuce.core.cluster.pubsub.api.async.RedisClusterPubSubAsyncCommands;
import io.lettuce.core.cluster.pubsub.api.reactive.RedisClusterPubSubReactiveCommands;
import io.lettuce.core.cluster.pubsub.api.sync.NodeSelectionPubSubCommands;
import io.lettuce.core.cluster.pubsub.api.sync.PubSubNodeSelection;
import io.lettuce.core.cluster.pubsub.api.sync.RedisClusterPubSubCommands;
import io.lettuce.core.codec.RedisCodec;
import io.lettuce.core.internal.LettuceAssert;
import io.lettuce.core.protocol.ConnectionIntent;
import io.lettuce.core.pubsub.RedisPubSubAsyncCommandsImpl;
import io.lettuce.core.pubsub.RedisPubSubReactiveCommandsImpl;
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;
import io.lettuce.core.pubsub.StatefulRedisPubSubConnectionImpl;
import io.lettuce.core.pubsub.api.async.RedisPubSubAsyncCommands;
import io.lettuce.core.pubsub.api.sync.RedisPubSubCommands;

/**
 * @author Mark Paluch
 */
class StatefulRedisClusterPubSubConnectionImpl<K, V> extends StatefulRedisPubSubConnectionImpl<K, V>
        implements StatefulRedisClusterPubSubConnection<K, V> {

    private final PubSubClusterEndpoint<K, V> endpoint;

    private final ClusterPushHandler clusterPushHandler;

    private volatile Partitions partitions;

    private volatile String nodeId;

    /**
     * Initialize a new connection.
     *
     * @param endpoint the channel writer.
     * @param clusterPushHandler the cluster push message handler.
     * @param codec Codec used to encode/decode keys and values.
     * @param timeout Maximum time to wait for a response.
     */
    public StatefulRedisClusterPubSubConnectionImpl(PubSubClusterEndpoint<K, V> endpoint, ClusterPushHandler clusterPushHandler,
            RedisChannelWriter writer, RedisCodec<K, V> codec, Duration timeout) {

        super(endpoint, writer, codec, timeout);

        this.endpoint = endpoint;
        this.clusterPushHandler = clusterPushHandler;
    }

    @Override
    public RedisClusterPubSubAsyncCommands<K, V> async() {
        return (RedisClusterPubSubAsyncCommands<K, V>) super.async();
    }

    @Override
    protected RedisPubSubAsyncCommandsImpl<K, V> newRedisAsyncCommandsImpl() {
        return new RedisClusterPubSubAsyncCommandsImpl<>(this, codec);
    }

    @Override
    public RedisClusterPubSubCommands<K, V> sync() {
        return (RedisClusterPubSubCommands<K, V>) super.sync();
    }

    @SuppressWarnings("unchecked")
    @Override
    protected RedisPubSubCommands<K, V> newRedisSyncCommandsImpl() {

        return (RedisPubSubCommands) Proxy.newProxyInstance(AbstractRedisClient.class.getClassLoader(),
                new Class<?>[] { RedisClusterPubSubCommands.class, RedisPubSubCommands.class }, syncInvocationHandler());
    }

    private InvocationHandler syncInvocationHandler() {
        return new ClusterFutureSyncInvocationHandler<K, V>(this, RedisPubSubAsyncCommands.class, PubSubNodeSelection.class,
                NodeSelectionPubSubCommands.class, async());
    }

    @Override
    public RedisClusterPubSubReactiveCommands<K, V> reactive() {
        return (RedisClusterPubSubReactiveCommands<K, V>) super.reactive();
    }

    @Override
    protected RedisPubSubReactiveCommandsImpl<K, V> newRedisReactiveCommandsImpl() {
        return new RedisClusterPubSubReactiveCommandsImpl<K, V>(this, codec);
    }

    @Override
    protected List<RedisFuture<Void>> resubscribe() {

        async().clusterMyId().thenAccept(nodeId -> endpoint.setClusterNode(partitions.getPartitionByNodeId(nodeId)));

        return super.resubscribe();
    }

    @Override
    public StatefulRedisPubSubConnection<K, V> getConnection(String nodeId) {

        RedisURI redisURI = lookup(nodeId);

        if (redisURI == null) {
            throw new RedisException("NodeId " + nodeId + " does not belong to the cluster");
        }

        return (StatefulRedisPubSubConnection<K, V>) getClusterDistributionChannelWriter().getClusterConnectionProvider()
                .getConnection(ConnectionIntent.WRITE, nodeId);
    }

    @Override
    @SuppressWarnings("unchecked")
    public CompletableFuture<StatefulRedisPubSubConnection<K, V>> getConnectionAsync(String nodeId) {

        RedisURI redisURI = lookup(nodeId);

        if (redisURI == null) {
            throw new RedisException("NodeId " + nodeId + " does not belong to the cluster");
        }

        AsyncClusterConnectionProvider provider = (AsyncClusterConnectionProvider) getClusterDistributionChannelWriter()
                .getClusterConnectionProvider();
        return (CompletableFuture) provider.getConnectionAsync(ConnectionIntent.WRITE, nodeId);

    }

    @Override
    public StatefulRedisPubSubConnection<K, V> getConnection(String host, int port) {

        return (StatefulRedisPubSubConnection<K, V>) getClusterDistributionChannelWriter().getClusterConnectionProvider()
                .getConnection(ConnectionIntent.WRITE, host, port);
    }

    @Override
    public CompletableFuture<StatefulRedisPubSubConnection<K, V>> getConnectionAsync(String host, int port) {

        AsyncClusterConnectionProvider provider = (AsyncClusterConnectionProvider) getClusterDistributionChannelWriter()
                .getClusterConnectionProvider();

        return (CompletableFuture) provider.getConnectionAsync(ConnectionIntent.WRITE, host, port);
    }

    @Override
    public void activated() {

        if (!endpoint.isSubscribed()) {
            async.clusterMyId().thenAccept(this::setNodeId);
        }

        super.activated();
    }

    public void setPartitions(Partitions partitions) {

        LettuceAssert.notNull(partitions, "Partitions must not be null");

        this.partitions = partitions;

        String nodeId = getNodeId();
        if (nodeId != null && expireStaleConnections()) {

            if (partitions.getPartitionByNodeId(nodeId) == null) {
                endpoint.disconnect();
            }
        }

        getClusterDistributionChannelWriter().setPartitions(partitions);
    }

    private String getNodeId() {
        return this.nodeId;
    }

    private void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public Partitions getPartitions() {
        return partitions;
    }

    @Override
    public void setNodeMessagePropagation(boolean enabled) {
        this.endpoint.setNodeMessagePropagation(enabled);
    }

    /**
     * Add a new {@link RedisClusterPubSubListener listener}.
     *
     * @param listener the listener, must not be {@code null}.
     */
    @Override
    public void addListener(RedisClusterPubSubListener<K, V> listener) {
        endpoint.addListener(listener);
    }

    /**
     * Remove an existing {@link RedisClusterPubSubListener listener}.
     *
     * @param listener the listener, must not be {@code null}.
     */
    @Override
    public void removeListener(RedisClusterPubSubListener<K, V> listener) {
        endpoint.removeListener(listener);
    }

    @Override
    public void addListener(RedisClusterPushListener listener) {
        clusterPushHandler.addListener(listener);
    }

    @Override
    public void removeListener(RedisClusterPushListener listener) {
        clusterPushHandler.removeListener(listener);
    }

    protected ClusterDistributionChannelWriter getClusterDistributionChannelWriter() {
        return (ClusterDistributionChannelWriter) super.getChannelWriter();
    }

    private RedisURI lookup(String nodeId) {

        for (RedisClusterNode partition : partitions) {
            if (partition.getNodeId().equals(nodeId)) {
                return partition.getUri();
            }
        }
        return null;
    }

    private boolean expireStaleConnections() {

        ClusterClientOptions options = getClusterClientOptions();
        return options == null || options.isCloseStaleConnections();
    }

    private ClusterClientOptions getClusterClientOptions() {

        ClientOptions options = getOptions();
        return options instanceof ClusterClientOptions ? (ClusterClientOptions) options : null;
    }

}
