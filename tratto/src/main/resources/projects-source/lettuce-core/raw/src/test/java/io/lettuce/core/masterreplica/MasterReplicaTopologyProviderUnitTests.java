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

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.models.role.RedisInstance;
import io.lettuce.core.models.role.RedisNodeDescription;
import io.lettuce.test.settings.TestSettings;

/**
 * Unit tests for {@link ReplicaTopologyProvider}.
 *
 * @author Mark Paluch
 */
class MasterReplicaTopologyProviderUnitTests {

    private StatefulRedisConnection<String, String> connectionMock = mock(StatefulRedisConnection.class);

    private ReplicaTopologyProvider sut = new ReplicaTopologyProvider(connectionMock,
            RedisURI.Builder.redis(TestSettings.host(), TestSettings.port()).build());

    @Test
    void shouldParseMaster() {

        String info = "# Replication\r\n" + "role:master\r\n" + "connected_slaves:1\r\n" + "master_repl_offset:56276\r\n"
                + "repl_backlog_active:1\r\n";

        List<RedisNodeDescription> result = sut.getNodesFromInfo(info);
        assertThat(result).hasSize(1);

        RedisNodeDescription redisNodeDescription = result.get(0);

        assertThat(redisNodeDescription.getRole().isUpstream()).isTrue();
        assertThat(redisNodeDescription.getUri().getHost()).isEqualTo(TestSettings.host());
        assertThat(redisNodeDescription.getUri().getPort()).isEqualTo(TestSettings.port());
    }

    @Test
    void shouldParseMasterAndReplica() {

        String info = "# Replication\r\n" + "role:slave\r\n" + "connected_slaves:1\r\n" + "master_host:127.0.0.1\r\n"
                + "master_port:1234\r\n" + "master_repl_offset:56276\r\n" + "repl_backlog_active:1\r\n";

        List<RedisNodeDescription> result = sut.getNodesFromInfo(info);
        assertThat(result).hasSize(2);

        RedisNodeDescription replica = result.get(0);
        assertThat(replica.getRole().isReplica()).isTrue();

        RedisNodeDescription upstream = result.get(1);
        assertThat(upstream.getRole().isUpstream()).isTrue();
        assertThat(upstream.getUri().getHost()).isEqualTo("127.0.0.1");
    }

    @Test
    void shouldParseMasterHostname() {

        String info = "# Replication\r\n" + "role:slave\r\n" + "connected_slaves:1\r\n" + "master_host:my.Host-name.COM\r\n"
                + "master_port:1234\r\n" + "master_repl_offset:56276\r\n" + "repl_backlog_active:1\r\n";

        List<RedisNodeDescription> result = sut.getNodesFromInfo(info);
        assertThat(result).hasSize(2);

        RedisNodeDescription replica = result.get(0);
        assertThat(replica.getRole().isReplica()).isTrue();

        RedisNodeDescription upstream = result.get(1);
        assertThat(upstream.getRole().isUpstream()).isTrue();
        assertThat(upstream.getUri().getHost()).isEqualTo("my.Host-name.COM");
    }

    @Test
    void shouldParseIPv6UpstreamAddress() {

        String info = "# Replication\r\n" + "role:slave\r\n" + "connected_slaves:1\r\n" + "master_host:::20f8:1400:0:0\r\n"
                + "master_port:1234\r\n" + "master_repl_offset:56276\r\n" + "repl_backlog_active:1\r\n";

        List<RedisNodeDescription> result = sut.getNodesFromInfo(info);
        assertThat(result).hasSize(2);


        RedisNodeDescription replica = result.get(0);
        assertThat(replica.getRole().isReplica()).isTrue();

        RedisNodeDescription upstream = result.get(1);
        assertThat(upstream.getRole().isUpstream()).isTrue();
        assertThat(upstream.getUri().getHost()).isEqualTo("::20f8:1400:0:0");
    }

    @Test
    void shouldFailWithoutRole() {

        String info = "# Replication\r\n" + "connected_slaves:1\r\n" + "master_repl_offset:56276\r\n"
                + "repl_backlog_active:1\r\n";

        assertThatThrownBy(() -> sut.getNodesFromInfo(info)).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void shouldFailWithInvalidRole() {

        String info = "# Replication\r\n" + "role:abc\r\n" + "master_repl_offset:56276\r\n" + "repl_backlog_active:1\r\n";

        assertThatThrownBy(() -> sut.getNodesFromInfo(info)).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void shouldParseReplicas() {

        String info = "# Replication\r\n" + "role:master\r\n"
                + "slave0:ip=127.0.0.1,port=6483,state=online,offset=56276,lag=0\r\n"
                + "slave1:ip=127.0.0.1,port=6484,state=online,offset=56276,lag=0\r\n"
                + "slave2:ip=redis-replica-2.,port=6484,state=online,offset=56276,lag=0\r\n" + "master_repl_offset:56276\r\n"
                + "repl_backlog_active:1\r\n";

        List<RedisNodeDescription> result = sut.getNodesFromInfo(info);
        assertThat(result).hasSize(4);

        RedisNodeDescription replica1 = result.get(1);

        assertThat(replica1.getRole()).isEqualTo(RedisInstance.Role.SLAVE);
        assertThat(replica1.getUri().getHost()).isEqualTo("127.0.0.1");
        assertThat(replica1.getUri().getPort()).isEqualTo(6483);

        RedisNodeDescription replica2 = result.get(2);

        assertThat(replica2.getRole()).isEqualTo(RedisInstance.Role.SLAVE);
        assertThat(replica2.getUri().getHost()).isEqualTo("127.0.0.1");
        assertThat(replica2.getUri().getPort()).isEqualTo(6484);

        RedisNodeDescription replica3 = result.get(3);

        assertThat(replica3.getRole()).isEqualTo(RedisInstance.Role.SLAVE);
        assertThat(replica3.getUri().getHost()).isEqualTo("redis-replica-2.");
        assertThat(replica3.getUri().getPort()).isEqualTo(6484);
    }

    @Test
    void shouldParseIPv6SlaveAddress() {

        String info = "# Replication\r\n" + "role:master\r\n"
                + "slave0:ip=::20f8:1400:0:0,port=6483,state=online,offset=56276,lag=0\r\n"
                + "master_repl_offset:56276\r\n"
                + "repl_backlog_active:1\r\n";

        List<RedisNodeDescription> result = sut.getNodesFromInfo(info);
        assertThat(result).hasSize(2);

        RedisNodeDescription replica1 = result.get(1);

        assertThat(replica1.getRole()).isEqualTo(RedisInstance.Role.SLAVE);
        assertThat(replica1.getUri().getHost()).isEqualTo("::20f8:1400:0:0");
        assertThat(replica1.getUri().getPort()).isEqualTo(6483);
    }
}
