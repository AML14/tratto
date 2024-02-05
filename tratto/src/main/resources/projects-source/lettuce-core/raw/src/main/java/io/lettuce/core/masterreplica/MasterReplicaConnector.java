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

import java.util.concurrent.CompletableFuture;

import io.lettuce.core.codec.RedisCodec;

/**
 * Interface declaring an asynchronous connect method to connect a Master/Replica setup.
 *
 * @author Mark Paluch
 * @since 5.1
 */
interface MasterReplicaConnector<K, V> {

    /**
     * Asynchronously connect to a Master/Replica setup given {@link RedisCodec}.
     *
     * @return Future that is notified about the connection progress.
     */
    CompletableFuture<StatefulRedisMasterReplicaConnection<K, V>> connectAsync();

}
