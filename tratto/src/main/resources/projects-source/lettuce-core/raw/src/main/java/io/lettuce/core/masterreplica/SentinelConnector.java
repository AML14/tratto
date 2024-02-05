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
package io.lettuce.core.masterreplica;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

import reactor.core.publisher.Mono;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisException;
import io.lettuce.core.RedisURI;
import io.lettuce.core.codec.RedisCodec;
import io.lettuce.core.event.jfr.EventRecorder;
import io.lettuce.core.models.role.RedisNodeDescription;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

/**
 * {@link MasterReplicaConnector} to connect a Sentinel-managed Master/Replica setup using a Sentinel {@link RedisURI}.
 *
 * @author Mark Paluch
 * @author Jim Brunner
 * @since 5.1
 */
class SentinelConnector<K, V> implements MasterReplicaConnector<K, V> {

    private static final InternalLogger LOG = InternalLoggerFactory.getInstance(SentinelConnector.class);

    private final RedisClient redisClient;

    private final RedisCodec<K, V> codec;

    private final RedisURI redisURI;

    SentinelConnector(RedisClient redisClient, RedisCodec<K, V> codec, RedisURI redisURI) {
        this.redisClient = redisClient;
        this.codec = codec;
        this.redisURI = redisURI;
    }

    @Override
    public CompletableFuture<StatefulRedisMasterReplicaConnection<K, V>> connectAsync() {

        TopologyProvider topologyProvider = new SentinelTopologyProvider(redisURI.getSentinelMasterId(), redisClient, redisURI);
        SentinelTopologyRefresh sentinelTopologyRefresh = new SentinelTopologyRefresh(redisClient,
                redisURI.getSentinelMasterId(), redisURI.getSentinels());

        MasterReplicaTopologyRefresh refresh = new MasterReplicaTopologyRefresh(redisClient, topologyProvider);
        MasterReplicaConnectionProvider<K, V> connectionProvider = new MasterReplicaConnectionProvider<>(redisClient, codec,
                redisURI, Collections.emptyMap());

        Runnable runnable = getTopologyRefreshRunnable(refresh, connectionProvider);

        return refresh.getNodes(redisURI).flatMap(nodes -> {

            if (nodes.isEmpty()) {
                return Mono.error(new RedisException(String.format("Cannot determine topology from %s", redisURI)));
            }

            return initializeConnection(codec, sentinelTopologyRefresh, connectionProvider, runnable, nodes);
        }).onErrorMap(ExecutionException.class, Throwable::getCause).toFuture();
    }

    private Mono<StatefulRedisMasterReplicaConnection<K, V>> initializeConnection(RedisCodec<K, V> codec,
            SentinelTopologyRefresh sentinelTopologyRefresh, MasterReplicaConnectionProvider<K, V> connectionProvider,
            Runnable runnable, List<RedisNodeDescription> nodes) {

        connectionProvider.setKnownNodes(nodes);

        MasterReplicaChannelWriter channelWriter = new MasterReplicaChannelWriter(connectionProvider,
                redisClient.getResources(), redisClient.getOptions()) {

            @Override
            public CompletableFuture<Void> closeAsync() {
                return CompletableFuture.allOf(super.closeAsync(), sentinelTopologyRefresh.closeAsync());
            }
        };

        StatefulRedisMasterReplicaConnectionImpl<K, V> connection = new StatefulRedisMasterReplicaConnectionImpl<>(
                channelWriter, codec, redisURI.getTimeout());
        connection.setOptions(redisClient.getOptions());

        CompletionStage<Void> bind = sentinelTopologyRefresh.bind(runnable);

        return Mono.fromCompletionStage(bind).onErrorResume(t -> {
            return ResumeAfter.close(connection).thenError(t);
        }).then(Mono.just(connection));
    }

    private Runnable getTopologyRefreshRunnable(MasterReplicaTopologyRefresh refresh,
            MasterReplicaConnectionProvider<K, V> connectionProvider) {

        return () -> {
            try {

                LOG.debug("Refreshing topology");
                refresh.getNodes(redisURI).subscribe(nodes -> {

                    EventRecorder.getInstance().record(new MasterReplicaTopologyChangedEvent(redisURI, nodes));

                    if (nodes.isEmpty()) {
                        LOG.warn("Topology refresh returned no nodes from {}", redisURI);
                    }

                    LOG.debug("New topology: {}", nodes);
                    connectionProvider.setKnownNodes(nodes);

                }, t -> LOG.error("Error during background refresh", t));

            } catch (Exception e) {
                LOG.error("Error during background refresh", e);
            }
        };
    }

}
