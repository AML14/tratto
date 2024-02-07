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
package io.lettuce.core.cluster.topology;

import static org.assertj.core.api.Assertions.*;

import java.nio.ByteBuffer;

import org.junit.jupiter.api.Test;

import io.lettuce.core.RedisURI;
import io.lettuce.core.codec.StringCodec;
import io.lettuce.core.output.StatusOutput;
import io.lettuce.core.protocol.Command;
import io.lettuce.core.protocol.CommandType;

/**
 * @author Mark Paluch
 * @author Xujs
 */
class RequestsUnitTests {

    @Test
    void shouldCreateTopologyView() throws Exception {

        RedisURI redisURI = RedisURI.create("localhost", 6379);

        Requests clusterNodesRequests = new Requests();
        String clusterNodesOutput = "1 127.0.0.1:7380 master,myself - 0 1401258245007 2 disconnected 8000-11999\n";
        clusterNodesRequests.addRequest(redisURI, getCommand(clusterNodesOutput));

        Requests infoClientRequests = new Requests();
        String infoClientOutput = "# Clients\r\nconnected_clients:100\r\nclient_longest_output_list:0\r\nclient_biggest_input_buf:0\r\nblocked_clients:0";
        infoClientRequests.addRequest(redisURI, getCommand(infoClientOutput));

        NodeTopologyView nodeTopologyView = NodeTopologyView.from(redisURI, clusterNodesRequests, infoClientRequests);

        assertThat(nodeTopologyView.isAvailable()).isTrue();
        assertThat(nodeTopologyView.getConnectedClients()).isEqualTo(100);
        assertThat(nodeTopologyView.getPartitions()).hasSize(1);
        assertThat(nodeTopologyView.getClusterNodes()).isEqualTo(clusterNodesOutput);
        assertThat(nodeTopologyView.getInfo()).isEqualTo(infoClientOutput);
    }

    @Test
    void shouldCreateTopologyViewWithoutClientCount() throws Exception {

        RedisURI redisURI = RedisURI.create("localhost", 6379);

        Requests clusterNodesRequests = new Requests();
        String clusterNodesOutput = "1 127.0.0.1:7380 master,myself - 0 1401258245007 2 disconnected 8000-11999\n";
        clusterNodesRequests.addRequest(redisURI, getCommand(clusterNodesOutput));

        Requests clientListRequests = new Requests();

        NodeTopologyView nodeTopologyView = NodeTopologyView.from(redisURI, clusterNodesRequests, clientListRequests);

        assertThat(nodeTopologyView.isAvailable()).isFalse();
        assertThat(nodeTopologyView.getConnectedClients()).isEqualTo(0);
        assertThat(nodeTopologyView.getPartitions()).isEmpty();
        assertThat(nodeTopologyView.getClusterNodes()).isNull();
    }

    private TimedAsyncCommand getCommand(String response) {
        Command<String, String, String> command = new Command<>(CommandType.TYPE, new StatusOutput<>(StringCodec.UTF8));
        TimedAsyncCommand timedAsyncCommand = new TimedAsyncCommand(command);

        command.getOutput().set(ByteBuffer.wrap(response.getBytes()));
        timedAsyncCommand.complete();
        return timedAsyncCommand;
    }
}
