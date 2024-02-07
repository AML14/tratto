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

import static org.assertj.core.api.Assertions.*;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import javax.inject.Inject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.internal.LettuceStrings;
import io.lettuce.core.protocol.Command;
import io.lettuce.core.protocol.ProtocolVersion;
import io.lettuce.test.CliParser;
import io.lettuce.test.Delay;
import io.lettuce.test.LettuceExtension;
import io.lettuce.test.TestFutures;
import io.lettuce.test.Wait;
import io.lettuce.test.WithPassword;
import io.lettuce.test.condition.EnabledOnCommand;
import io.lettuce.test.settings.TestSettings;

/**
 * @author Will Glozer
 * @author Mark Paluch
 * @author Tugdual Grall
 */
@ExtendWith(LettuceExtension.class)
class ConnectionCommandIntegrationTests extends TestSupport {

    private final RedisClient client;

    private final RedisCommands<String, String> redis;

    @Inject
    ConnectionCommandIntegrationTests(RedisClient client, StatefulRedisConnection<String, String> connection) {
        this.client = client;
        this.redis = connection.sync();
    }

    @BeforeEach
    void setUp() {
        redis.flushall();
    }

    @Test
    void auth() {

        WithPassword.run(client, () -> {
            client.setOptions(
                    ClientOptions.builder().pingBeforeActivateConnection(false).protocolVersion(ProtocolVersion.RESP2).build());
            RedisCommands<String, String> connection = client.connect().sync();

            assertThatThrownBy(connection::ping).isInstanceOf(RedisException.class).hasMessageContaining("NOAUTH");

            assertThat(connection.auth(passwd)).isEqualTo("OK");
            assertThat(connection.set(key, value)).isEqualTo("OK");

            RedisURI redisURI = RedisURIBuilder.redis(host, port).withDatabase(2).withPassword(passwd).build();
            RedisCommands<String, String> authConnection = client.connect(redisURI).sync();
            authConnection.ping();
            authConnection.getStatefulConnection().close();
        });
    }

    @Test
    @EnabledOnCommand("ACL")
    void authWithUsername() {

        WithPassword.run(client, () -> {
            client.setOptions(
                    ClientOptions.builder().pingBeforeActivateConnection(false).protocolVersion(ProtocolVersion.RESP2).build());
            RedisCommands<String, String> connection = client.connect().sync();

            assertThatThrownBy(connection::ping).isInstanceOf(RedisException.class).hasMessageContaining("NOAUTH");

            assertThat(connection.auth(passwd)).isEqualTo("OK");
            assertThat(connection.set(key, value)).isEqualTo("OK");

            // Aut with the same user & password (default)
            assertThat(connection.auth(username, passwd)).isEqualTo("OK");
            assertThat(connection.set(key, value)).isEqualTo("OK");

            // Switch to another user
            assertThat(connection.auth(aclUsername, aclPasswd)).isEqualTo("OK");
            assertThat(connection.set("cached:demo", value)).isEqualTo("OK");
            assertThatThrownBy(() -> connection.get(key)).isInstanceOf(RedisCommandExecutionException.class);
            assertThat(connection.del("cached:demo")).isEqualTo(1);

            RedisURI redisURI = RedisURIBuilder.redis(host, port).withDatabase(2).withPassword(passwd).build();
            RedisCommands<String, String> authConnection = client.connect(redisURI).sync();
            authConnection.ping();
            authConnection.getStatefulConnection().close();
        });
    }

    @Test
    @EnabledOnCommand("ACL")
    void changeAclPasswordWhileAuthenticated() {

        WithPassword.run(client, () -> {
            client.setOptions(
                    ClientOptions.builder().pingBeforeActivateConnection(false).protocolVersion(ProtocolVersion.RESP2).build());

            RedisURI redisURI = RedisURIBuilder.redis(host, port).withDatabase(2)
                    .withAuthentication(TestSettings.aclUsername(), TestSettings.aclPassword()).build();
            StatefulRedisConnection<String, String> connection = client.connect(redisURI);

            Command<String, String, List<Object>> command = CliParser.parse("ACL SETUSER " + TestSettings.aclUsername()
                    + " on <" + TestSettings.aclPassword() + " >another-password ~cached:* +@all");
            connection.sync().dispatch(command.getType(), command.getOutput(), command.getArgs());

            connection.sync().ping();
            connection.close();
        });
    }

    @Test
    @EnabledOnCommand("ACL")
    void changeAclPasswordDuringDisconnect() {

        WithPassword.run(client, () -> {
            client.setOptions(
                    ClientOptions.builder().pingBeforeActivateConnection(false).protocolVersion(ProtocolVersion.RESP2).build());

            AtomicReference<CharSequence> passwd = new AtomicReference<>(TestSettings.aclPassword());

            RedisCredentialsProvider.ImmediateRedisCredentialsProvider rcp = () -> RedisCredentials
                    .just(TestSettings.aclUsername(), passwd.get());

            RedisURI redisURI = RedisURIBuilder.redis(host, port).withDatabase(2).withAuthentication(rcp).build();
            StatefulRedisConnection<String, String> connection = client.connect(redisURI);

            Command<String, String, List<Object>> command = CliParser.parse("ACL SETUSER " + TestSettings.aclUsername()
                    + " on <" + TestSettings.aclPassword() + " >another-password ~cached:* +@all");
            connection.sync().dispatch(command.getType(), command.getOutput(), command.getArgs());

            connection.async().quit().await(100, TimeUnit.MILLISECONDS);

            passwd.set("another-password");

            connection.sync().ping();
            connection.close();
        });
    }

    @Test
    @EnabledOnCommand("ACL")
    void resp2HandShakeWithUsernamePassword() {

        RedisURI redisURI = RedisURIBuilder.redis(host, port).withAuthentication(username, passwd).build();
        RedisClient clientResp2 = RedisClient.create(redisURI);
        clientResp2.setOptions(
                ClientOptions.builder().pingBeforeActivateConnection(false).protocolVersion(ProtocolVersion.RESP2).build());
        RedisCommands<String, String> connTestResp2 = null;

        try {
            connTestResp2 = clientResp2.connect().sync();
            assertThat(redis.ping()).isEqualTo("PONG");
        } catch (Exception e) {
        } finally {
            assertThat(connTestResp2).isNotNull();
            if (connTestResp2 != null) {
                connTestResp2.getStatefulConnection().close();
            }
        }
        clientResp2.shutdown();
    }

    @Test
    void echo() {
        assertThat(redis.echo("hello")).isEqualTo("hello");
    }

    @Test
    void ping() {
        assertThat(redis.ping()).isEqualTo("PONG");
    }

    @Test
    void select() {
        redis.set(key, value);
        assertThat(redis.select(1)).isEqualTo("OK");
        assertThat(redis.get(key)).isNull();
    }

    @Test
    void authNull() {
        assertThatThrownBy(() -> redis.auth(null)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> redis.auth(null, "x")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void authReconnect() {
        WithPassword.run(client, () -> {

            client.setOptions(
                    ClientOptions.builder().protocolVersion(ProtocolVersion.RESP2).pingBeforeActivateConnection(false).build());
            RedisCommands<String, String> connection = client.connect().sync();
            assertThat(connection.auth(passwd)).isEqualTo("OK");
            assertThat(connection.set(key, value)).isEqualTo("OK");
            connection.quit();

            Delay.delay(Duration.ofMillis(100));
            assertThat(connection.get(key)).isEqualTo(value);

            connection.getStatefulConnection().close();
        });
    }

    @Test
    @EnabledOnCommand("ACL")
    void authReconnectRedis6() {
        WithPassword.run(client, () -> {

            client.setOptions(
                    ClientOptions.builder().protocolVersion(ProtocolVersion.RESP2).pingBeforeActivateConnection(false).build());
            RedisCommands<String, String> connection = client.connect().sync();
            assertThat(connection.auth(passwd)).isEqualTo("OK");
            assertThat(connection.set(key, value)).isEqualTo("OK");
            connection.quit();

            Delay.delay(Duration.ofMillis(100));
            assertThat(connection.get(key)).isEqualTo(value);

            // reconnect with username/password
            assertThat(connection.auth(username, passwd)).isEqualTo("OK");
            assertThat(connection.set(key, value)).isEqualTo("OK");
            connection.quit();

            Delay.delay(Duration.ofMillis(100));
            assertThat(connection.get(key)).isEqualTo(value);

            connection.getStatefulConnection().close();
        });
    }

    @Test
    void selectReconnect() {
        redis.select(1);
        redis.set(key, value);
        redis.quit();

        Wait.untilTrue(redis::isOpen).waitOrTimeout();
        assertThat(redis.get(key)).isEqualTo(value);
    }

    @Test
    void getSetReconnect() {
        redis.set(key, value);
        redis.quit();
        Wait.untilTrue(redis::isOpen).waitOrTimeout();
        assertThat(redis.get(key)).isEqualTo(value);
    }

    @Test
    void authInvalidPassword() {
        RedisAsyncCommands<String, String> async = client.connect().async();
        try {
            TestFutures.awaitOrTimeout(async.auth("invalid"));
            fail("Authenticated with invalid password");
        } catch (RedisException e) {
            assertThat(e.getMessage()).startsWith("ERR").contains("AUTH");
            StatefulRedisConnectionImpl<String, String> statefulRedisCommands = (StatefulRedisConnectionImpl) async
                    .getStatefulConnection();
            assertThat(statefulRedisCommands.getConnectionState().getCredentialsProvider().resolveCredentials().block()
                    .getPassword()).isNull();
        } finally {
            async.getStatefulConnection().close();
        }
    }

    @Test
    @EnabledOnCommand("ACL")
    void authInvalidUsernamePassword() {

        WithPassword.run(client, () -> {
            client.setOptions(
                    ClientOptions.builder().protocolVersion(ProtocolVersion.RESP2).pingBeforeActivateConnection(false).build());
            RedisCommands<String, String> connection = client.connect().sync();

            assertThat(connection.auth(username, passwd)).isEqualTo("OK");

            assertThatThrownBy(() -> connection.auth(username, "invalid"))
                    .hasMessageContaining("WRONGPASS invalid username-password pair");

            assertThat(connection.auth(aclUsername, aclPasswd)).isEqualTo("OK");

            assertThatThrownBy(() -> connection.auth(aclUsername, "invalid"))
                    .hasMessageContaining("WRONGPASS invalid username-password pair");

            connection.getStatefulConnection().close();
        });
    }

    @Test
    @EnabledOnCommand("ACL")
    void authInvalidDefaultPasswordNoACL() {
        RedisAsyncCommands<String, String> async = client.connect().async();
        // When the database is not secured the AUTH default invalid command returns OK
        try {
            Future<String> auth = async.auth(username, "invalid");
            assertThat(TestFutures.getOrTimeout(auth)).isEqualTo("OK");
        } finally {
            async.getStatefulConnection().close();
        }
    }

    @Test
    void authInvalidUsernamePasswordNoACL() {
        RedisAsyncCommands<String, String> async = client.connect().async();
        try {
            TestFutures.awaitOrTimeout(async.select(1024));
            fail("Selected invalid db index");
        } catch (RedisException e) {
            assertThat(e.getMessage()).startsWith("ERR");
            StatefulRedisConnectionImpl<String, String> statefulRedisCommands = (StatefulRedisConnectionImpl) async
                    .getStatefulConnection();
            assertThat(statefulRedisCommands.getConnectionState()).extracting("db").isEqualTo(0);
        } finally {
            async.getStatefulConnection().close();
        }
    }

    @Test
    void testDoubleToString() {

        assertThat(LettuceStrings.string(1.1)).isEqualTo("1.1");
        assertThat(LettuceStrings.string(Double.POSITIVE_INFINITY)).isEqualTo("+inf");
        assertThat(LettuceStrings.string(Double.NEGATIVE_INFINITY)).isEqualTo("-inf");
    }

}
