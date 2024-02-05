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
package io.lettuce.core.cluster;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.regex.Pattern;

import javax.inject.Inject;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.lettuce.core.ReadFrom;
import io.lettuce.core.TestSupport;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.cluster.api.sync.RedisAdvancedClusterCommands;
import io.lettuce.test.LettuceExtension;

/**
 * @author Mark Paluch
 * @author Yohei Ueki
 */
@SuppressWarnings("unchecked")
@ExtendWith(LettuceExtension.class)
class RedisClusterReadFromIntegrationTests extends TestSupport {

    private final RedisClusterClient clusterClient;
    private StatefulRedisClusterConnection<String, String> connection;
    private RedisAdvancedClusterCommands<String, String> sync;

    @Inject
    RedisClusterReadFromIntegrationTests(RedisClusterClient clusterClient) {
        this.clusterClient = clusterClient;
    }

    @BeforeEach
    void before() {
        connection = clusterClient.connect();
        sync = connection.sync();
    }

    @AfterEach
    void after() {
        connection.close();
    }

    @Test
    void defaultTest() {
        assertThat(connection.getReadFrom()).isEqualTo(ReadFrom.UPSTREAM);
    }

    @Test
    void readWriteMaster() {

        connection.setReadFrom(ReadFrom.UPSTREAM);

        sync.set(key, value);
        assertThat(sync.get(key)).isEqualTo(value);
    }

    @Test
    void readWriteMasterPreferred() {

        connection.setReadFrom(ReadFrom.UPSTREAM_PREFERRED);

        sync.set(key, value);
        assertThat(sync.get(key)).isEqualTo(value);
    }

    @Test
    void readWriteReplica() {

        connection.setReadFrom(ReadFrom.REPLICA);

        sync.set(key, "value1");

        connection.getConnection(ClusterTestSettings.host, ClusterTestSettings.port2).sync().waitForReplication(1, 1000);
        assertThat(sync.get(key)).isEqualTo("value1");
    }

    @Test
    void readWriteReplicaPreferred() {

        connection.setReadFrom(ReadFrom.REPLICA_PREFERRED);

        sync.set(key, "value1");

        connection.getConnection(ClusterTestSettings.host, ClusterTestSettings.port2).sync().waitForReplication(1, 1000);
        assertThat(sync.get(key)).isEqualTo("value1");
    }

    @Test
    void readWriteNearest() {

        connection.setReadFrom(ReadFrom.NEAREST);

        sync.set(key, "value1");

        connection.getConnection(ClusterTestSettings.host, ClusterTestSettings.port2).sync().waitForReplication(1, 1000);
        assertThat(sync.get(key)).isEqualTo("value1");
    }

    @Test
    void readWriteSubnet() {

        connection.setReadFrom(ReadFrom.subnet("0.0.0.0/0", "::/0"));

        sync.set(key, "value1");

        connection.getConnection(ClusterTestSettings.host, ClusterTestSettings.port2).sync().waitForReplication(1, 1000);
        assertThat(sync.get(key)).isEqualTo("value1");
    }

    @Test
    void readWriteRegex() {

        connection.setReadFrom(ReadFrom.regex(Pattern.compile(".*")));

        sync.set(key, "value1");

        connection.getConnection(ClusterTestSettings.host, ClusterTestSettings.port2).sync().waitForReplication(1, 1000);
        assertThat(sync.get(key)).isEqualTo("value1");
    }

}
