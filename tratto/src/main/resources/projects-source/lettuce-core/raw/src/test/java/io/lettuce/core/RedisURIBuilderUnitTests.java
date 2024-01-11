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

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;

/**
 * Unit tests for {@link RedisURI.Builder}.
 *
 * @author Mark Paluch
 * @author Guy Korland
 */
class RedisURIBuilderUnitTests {

    @Test
    void sentinel() {
        RedisURI result = RedisURI.Builder.sentinel("localhost").withTimeout(Duration.ofHours(2)).build();
        assertThat(result.getSentinels()).hasSize(1);
        assertThat(result.getTimeout()).isEqualTo(Duration.ofHours(2));
    }

    @Test
    void sentinelWithHostShouldFail() {
        assertThatThrownBy(() -> RedisURI.Builder.sentinel("localhost").withHost("localhost"))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void sentinelWithPort() {
        RedisURI result = RedisURI.Builder.sentinel("localhost", 1).withTimeout(Duration.ofHours(2)).build();
        assertThat(result.getSentinels()).hasSize(1);
        assertThat(result.getTimeout()).isEqualTo(Duration.ofHours(2));
    }

    @Test
    void shouldFailIfBuilderIsEmpty() {
        assertThatThrownBy(() -> RedisURI.builder().build()).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void redisWithHostAndPort() {
        RedisURI result = RedisURI.builder().withHost("localhost").withPort(1234).build();

        assertThat(result.getSentinels()).isEmpty();
        assertThat(result.getHost()).isEqualTo("localhost");
        assertThat(result.getPort()).isEqualTo(1234);
    }

    @Test
    void redisWithPort() {
        RedisURI result = RedisURI.Builder.redis("localhost").withPort(1234).build();

        assertThat(result.getSentinels()).isEmpty();
        assertThat(result.getHost()).isEqualTo("localhost");
        assertThat(result.getPort()).isEqualTo(1234);
    }

    @Test
    void redisWithClientName() {
        RedisURI result = RedisURI.Builder.redis("localhost").withClientName("hello").build();

        assertThat(result.getHost()).isEqualTo("localhost");
        assertThat(result.getClientName()).isEqualTo("hello");
    }

    @Test
    void redisWithLibrary() {
        RedisURI result = RedisURI.Builder.redis("localhost").withLibraryName("name").withLibraryVersion("1.foo").build();

        assertThat(result.getLibraryName()).isEqualTo("name");
        assertThat(result.getLibraryVersion()).isEqualTo("1.foo");
    }

    @Test
    void redisHostAndPortWithInvalidPort() {
        assertThatThrownBy(() -> RedisURI.Builder.redis("localhost", -1)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void redisWithInvalidPort() {
        assertThatThrownBy(() -> RedisURI.Builder.redis("localhost").withPort(65536))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void redisFromUrl() {
        RedisURI result = RedisURI.create(RedisURI.URI_SCHEME_REDIS + "://password@localhost/21");

        assertThat(result.getSentinels()).isEmpty();
        assertThat(result.getHost()).isEqualTo("localhost");
        assertThat(result.getPort()).isEqualTo(RedisURI.DEFAULT_REDIS_PORT);
        assertThat(result.getPassword()).isEqualTo("password".toCharArray());
        assertThat(result.getDatabase()).isEqualTo(21);
        assertThat(result.isSsl()).isFalse();
    }

    @Test
    void redisFromUrlNoPassword() {
        RedisURI redisURI = RedisURI.create("redis://localhost:1234/5");
        assertThat(redisURI.getPassword()).isNull();
        assertThat(redisURI.getUsername()).isNull();

        redisURI = RedisURI.create("redis://h:@localhost.com:14589");
        assertThat(redisURI.getPassword()).isNull();
        assertThat(redisURI.getUsername()).isNull();
    }

    @Test
    void redisFromUrlPassword() {
        RedisURI redisURI = RedisURI.create("redis://h:password@localhost.com:14589");
        assertThat(redisURI.getPassword()).isEqualTo("password".toCharArray());
        assertThat(redisURI.getUsername()).isEqualTo("h");
    }

    @Test
    void redisWithSSL() {
        RedisURI result = RedisURI.Builder.redis("localhost").withSsl(true).withStartTls(true).build();

        assertThat(result.getSentinels()).isEmpty();
        assertThat(result.getHost()).isEqualTo("localhost");
        assertThat(result.isSsl()).isTrue();
        assertThat(result.isStartTls()).isTrue();
    }

    @Test
    void redisWithSSLFromUri() {
        RedisURI template = RedisURI.Builder.redis("localhost").withSsl(true).withVerifyPeer(SslVerifyMode.CA)
                .withStartTls(true).build();
        RedisURI result = RedisURI.builder().withHost("localhost").withSsl(template).build();

        assertThat(result.isSsl()).isTrue();
        assertThat(result.getVerifyMode()).isEqualTo(SslVerifyMode.CA);
        assertThat(result.isStartTls()).isTrue();
    }

    @Test
    void redisSslFromUrl() {
        RedisURI result = RedisURI.create(RedisURI.URI_SCHEME_REDIS_SECURE + "://:password@localhost/1");

        assertThat(result.getSentinels()).isEmpty();
        assertThat(result.getHost()).isEqualTo("localhost");
        assertThat(result.getPort()).isEqualTo(RedisURI.DEFAULT_REDIS_PORT);
        assertThat(result.getPassword()).isEqualTo("password".toCharArray());
        assertThat(result.getUsername()).isNull();
        assertThat(result.isSsl()).isTrue();
    }

    @Test
    void redisSentinelFromUrl() {
        RedisURI result = RedisURI.create(RedisURI.URI_SCHEME_REDIS_SENTINEL + "://password@localhost/1#master");

        assertThat(result.getSentinels()).hasSize(1);
        assertThat(result.getHost()).isNull();
        assertThat(result.getPort()).isEqualTo(RedisURI.DEFAULT_REDIS_PORT);
        assertThat(result.getPassword()).isEqualTo("password".toCharArray());
        assertThat(result.getSentinelMasterId()).isEqualTo("master");
        assertThat(result.toString()).contains("master");

        result = RedisURI.create(RedisURI.URI_SCHEME_REDIS_SENTINEL + "://password@host1:1,host2:3423,host3/1#master");

        assertThat(result.getSentinels()).hasSize(3);
        assertThat(result.getHost()).isNull();
        assertThat(result.getPort()).isEqualTo(RedisURI.DEFAULT_REDIS_PORT);
        assertThat(result.getPassword()).isEqualTo("password".toCharArray());
        assertThat(result.getSentinelMasterId()).isEqualTo("master");

        RedisURI sentinel1 = result.getSentinels().get(0);
        assertThat(sentinel1.getPort()).isEqualTo(1);
        assertThat(sentinel1.getHost()).isEqualTo("host1");

        RedisURI sentinel2 = result.getSentinels().get(1);
        assertThat(sentinel2.getPort()).isEqualTo(3423);
        assertThat(sentinel2.getHost()).isEqualTo("host2");

        RedisURI sentinel3 = result.getSentinels().get(2);
        assertThat(sentinel3.getPort()).isEqualTo(RedisURI.DEFAULT_SENTINEL_PORT);
        assertThat(sentinel3.getHost()).isEqualTo("host3");
    }

    @Test
    void withAuthenticatedSentinel() {

        RedisURI result = RedisURI.Builder.sentinel("host", 1234, "master", "foo").build();

        RedisURI sentinel = result.getSentinels().get(0);
        assertThat(new String(sentinel.getPassword())).isEqualTo("foo");
    }

    @Test
    void withTlsSentinel() {

        RedisURI result = RedisURI.Builder.sentinel("host", 1234, "master", "foo").withSsl(true).withStartTls(true)
                .withVerifyPeer(false).build();

        RedisURI sentinel = result.getSentinels().get(0);
        assertThat(new String(sentinel.getPassword())).isEqualTo("foo");
        assertThat(sentinel.isSsl()).isTrue();
        assertThat(sentinel.isStartTls()).isTrue();
        assertThat(sentinel.isVerifyPeer()).isFalse();
    }

    @Test
    void withAuthenticatedSentinelUri() {

        RedisURI sentinel = new RedisURI("host", 1234, Duration.ZERO);
        sentinel.setPassword("bar");
        RedisURI result = RedisURI.Builder.sentinel("host", 1234, "master").withSentinel(sentinel).build();

        assertThat(result.getSentinels().get(0).getPassword()).isNull();
        assertThat(new String(result.getSentinels().get(1).getPassword())).isEqualTo("bar");
    }

    @Test
    void withAuthenticatedSentinelWithSentinel() {

        RedisURI result = RedisURI.Builder.sentinel("host", 1234, "master", "foo").withSentinel("bar").build();

        assertThat(new String(result.getSentinels().get(0).getPassword())).isEqualTo("foo");
        assertThat(new String(result.getSentinels().get(1).getPassword())).isEqualTo("foo");

        result = RedisURI.Builder.sentinel("host", 1234, "master", "foo").withSentinel("bar", 1234, "baz").build();

        assertThat(new String(result.getSentinels().get(0).getPassword())).isEqualTo("foo");
        assertThat(new String(result.getSentinels().get(1).getPassword())).isEqualTo("baz");
    }

    @Test
    void redisSentinelWithInvalidPort() {
        assertThatThrownBy(() -> RedisURI.Builder.sentinel("a", 65536)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void redisSentinelWithMasterIdAndInvalidPort() {
        assertThatThrownBy(() -> RedisURI.Builder.sentinel("a", 65536, "")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void redisSentinelWithNullMasterId() {
        assertThatThrownBy(() -> RedisURI.Builder.sentinel("a", 1, null)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void invalidScheme() {
        assertThatThrownBy(() -> RedisURI.create("http://www.web.de")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisabledOnOs(OS.WINDOWS)
    void redisSocket() throws IOException {
        File file = new File("work/socket-6479").getCanonicalFile();
        RedisURI result = RedisURI.create(RedisURI.URI_SCHEME_REDIS_SOCKET + "://" + file.getCanonicalPath());

        assertThat(result.getSocket()).isEqualTo(file.getCanonicalPath());
        assertThat(result.getSentinels()).isEmpty();
        assertThat(result.getPassword()).isNull();
        assertThat(result.getHost()).isNull();
        assertThat(result.getPort()).isEqualTo(RedisURI.DEFAULT_REDIS_PORT);
        assertThat(result.isSsl()).isFalse();
    }

    @Test
    @DisabledOnOs(OS.WINDOWS)
    void redisSocketWithPassword() throws IOException {
        File file = new File("work/socket-6479").getCanonicalFile();
        RedisURI result = RedisURI.create(RedisURI.URI_SCHEME_REDIS_SOCKET + "://password@" + file.getCanonicalPath());

        assertThat(result.getSocket()).isEqualTo(file.getCanonicalPath());
        assertThat(result.getSentinels()).isEmpty();
        assertThat(result.getPassword()).isEqualTo("password".toCharArray());
        assertThat(result.getHost()).isNull();
        assertThat(result.getPort()).isEqualTo(RedisURI.DEFAULT_REDIS_PORT);
        assertThat(result.isSsl()).isFalse();
    }

    @Test
    void shouldApplySslSettings() {

        RedisURI source = new RedisURI();
        source.setSsl(true);
        source.setVerifyPeer(false);
        source.setStartTls(true);

        RedisURI target = RedisURI.builder().withHost("localhost").withSsl(source).build();

        assertThat(target.isSsl()).isTrue();
        assertThat(target.isVerifyPeer()).isFalse();
        assertThat(target.isStartTls()).isTrue();
    }

    @Test
    void shouldApplyAuthentication() {

        RedisURI source = new RedisURI();
        source.setUsername("foo");
        source.setPassword("bar");

        RedisURI target = RedisURI.builder().withHost("localhost").withAuthentication(source).build();

        assertThat(target.getUsername()).isEqualTo("foo");
        assertThat(target.getPassword()).isEqualTo("bar".toCharArray());

        RedisCredentialsProvider provider = RedisCredentialsProvider
                .from(() -> RedisCredentials.just("suppliedUsername", "suppliedPassword".toCharArray()));

        RedisURI sourceCp = new RedisURI();
        sourceCp.setCredentialsProvider(provider);

        RedisURI targetCp = RedisURI.builder().withHost("localhost").withAuthentication(sourceCp).build();

        assertThat(targetCp.getUsername()).isNull();
        assertThat(targetCp.getPassword()).isNull();
        assertThat(sourceCp.getCredentialsProvider()).isEqualTo(targetCp.getCredentialsProvider());
    }

    @Test
    void shouldInitializeBuilder() {

        RedisURI source = new RedisURI();
        source.setHost("localhost");
        source.setPort(1234);
        source.setTimeout(Duration.ofSeconds(2));
        source.setClientName("foo");
        source.setLibraryName("lib");
        source.setLibraryVersion("libver");
        source.setUsername("foo");
        source.setPassword("bar");
        source.setDatabase(4);
        source.setSsl(true);
        source.setVerifyPeer(false);
        source.setStartTls(true);

        RedisURI target = RedisURI.builder(source).build();

        source.setPassword("baz");

        assertThat(target.getHost()).isEqualTo(source.getHost());
        assertThat(target.getPort()).isEqualTo(source.getPort());
        assertThat(target.getUsername()).isEqualTo(source.getUsername());
        assertThat(target.getPassword()).isEqualTo("bar".toCharArray());
        assertThat(target.getTimeout()).isEqualTo(source.getTimeout());
        assertThat(target.getClientName()).isEqualTo(source.getClientName());
        assertThat(target.getLibraryName()).isEqualTo(source.getLibraryName());
        assertThat(target.getLibraryVersion()).isEqualTo(source.getLibraryVersion());
        assertThat(target.getSocket()).isEqualTo(source.getSocket());
        assertThat(target.getDatabase()).isEqualTo(source.getDatabase());
        assertThat(target.isStartTls()).isEqualTo(source.isStartTls());
        assertThat(target.isSsl()).isEqualTo(source.isSsl());
        assertThat(target.isVerifyPeer()).isEqualTo(source.isVerifyPeer());
    }

    @Test
    void shouldInitializeBuilderUsingSocket() {

        RedisURI source = new RedisURI();
        source.setSocket("localhost");

        RedisURI target = RedisURI.builder(source).build();

        assertThat(target.getSocket()).isEqualTo(source.getSocket());
    }

}
