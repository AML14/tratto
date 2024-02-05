package io.lettuce.core;

import io.lettuce.core.LettuceVersion;
import io.lettuce.core.RedisCredentialsProvider;
import io.lettuce.core.RedisURI;
import io.lettuce.core.SslVerifyMode;
import io.lettuce.core.internal.LettuceAssert;
import io.lettuce.core.internal.LettuceStrings;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.lettuce.core.RedisURI.DEFAULT_REDIS_PORT;
import static io.lettuce.core.RedisURI.DEFAULT_SENTINEL_PORT;
import static io.lettuce.core.RedisURI.DEFAULT_TIMEOUT_DURATION;

/**
 * Builder for Redis URI.
 */
public class RedisURIBuilder {

    private String host;

    private String socket;

    private String sentinelMasterId;

    private int port = DEFAULT_REDIS_PORT;

    private int database;

    private String clientName;

    private String libraryName = LettuceVersion.getName();

    private String libraryVersion = LettuceVersion.getVersion();

    private String username;

    private char[] password;

    private RedisCredentialsProvider credentialsProvider;

    private char[] sentinelPassword;

    private boolean ssl = false;

    private SslVerifyMode verifyMode = SslVerifyMode.FULL;

    private boolean startTls = false;

    private Duration timeout = DEFAULT_TIMEOUT_DURATION;

    private final List<RedisURI> sentinels = new ArrayList<>();

    public RedisURIBuilder() {
    }

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    public String getHost() {
        return host;
    }

    public List<RedisURI> getSentinels() {
        return sentinels;
    }

    public String getSentinelMasterId() {
        return sentinelMasterId;
    }

    /**
     * Set Redis socket. Creates a new builder.
     *
     * @param socket the host name
     * @return new builder with Redis socket.
     */
    public static RedisURIBuilder socket(String socket) {

        LettuceAssert.notNull(socket, "Socket must not be null");

        RedisURIBuilder builder = RedisURI.builder();
        builder.socket = socket;
        return builder;
    }

    /**
     * Set Redis host. Creates a new builder.
     *
     * @param host the host name
     * @return new builder with Redis host/port.
     */
    public static RedisURIBuilder redis(String host) {
        return redis(host, DEFAULT_REDIS_PORT);
    }

    /**
     * Set Redis host and port. Creates a new builder
     *
     * @param host the host name
     * @param port the port
     * @return new builder with Redis host/port.
     */
    public static RedisURIBuilder redis(String host, int port) {

        LettuceAssert.notEmpty(host, "Host must not be empty");
        LettuceAssert.isTrue(isValidPort(port), () -> String.format("Port out of range: %s", port));

        RedisURIBuilder builder = RedisURI.builder();
        return builder.withHost(host).withPort(port);
    }

    /**
     * Set Sentinel host. Creates a new builder.
     *
     * @param host the host name
     * @return new builder with Sentinel host/port.
     */
    public static RedisURIBuilder sentinel(String host) {

        LettuceAssert.notEmpty(host, "Host must not be empty");

        RedisURIBuilder builder = RedisURI.builder();
        return builder.withSentinel(host);
    }

    /**
     * Set Sentinel host and port. Creates a new builder.
     *
     * @param host the host name
     * @param port the port
     * @return new builder with Sentinel host/port.
     */
    public static RedisURIBuilder sentinel(String host, int port) {

        LettuceAssert.notEmpty(host, "Host must not be empty");
        LettuceAssert.isTrue(isValidPort(port), () -> String.format("Port out of range: %s", port));

        RedisURIBuilder builder = RedisURI.builder();
        return builder.withSentinel(host, port);
    }

    /**
     * Set Sentinel host and master id. Creates a new builder.
     *
     * @param host the host name
     * @param masterId sentinel master id
     * @return new builder with Sentinel host/port.
     */
    public static RedisURIBuilder sentinel(String host, String masterId) {
        return sentinel(host, DEFAULT_SENTINEL_PORT, masterId);
    }

    /**
     * Set Sentinel host, port and master id. Creates a new builder.
     *
     * @param host the host name
     * @param port the port
     * @param masterId sentinel master id
     * @return new builder with Sentinel host/port.
     */
    public static RedisURIBuilder sentinel(String host, int port, String masterId) {
        return sentinel(host, port, masterId, null);
    }

    /**
     * Set Sentinel host, port, master id and Sentinel authentication. Creates a new builder.
     *
     * @param host the host name
     * @param port the port
     * @param masterId sentinel master id
     * @param password the Sentinel password (supported since Redis 5.0.1)
     * @return new builder with Sentinel host/port.
     * @deprecated since 6.0, use {@link #sentinel(String, int, String)} and
     *             {@link #withAuthentication(String, CharSequence)} instead.
     */
    @Deprecated
    public static RedisURIBuilder sentinel(String host, int port, String masterId, CharSequence password) {

        LettuceAssert.notEmpty(host, "Host must not be empty");
        LettuceAssert.isTrue(isValidPort(port), () -> String.format("Port out of range: %s", port));

        RedisURIBuilder builder = RedisURI.builder();
        if (password != null) {
            builder.sentinelPassword = password.toString().toCharArray();
        }
        return builder.withSentinelMasterId(masterId).withSentinel(host, port);
    }

    /**
     * Add a withSentinel host to the existing builder.
     *
     * @param host the host name
     * @return the builder
     */
    public RedisURIBuilder withSentinel(String host) {
        return withSentinel(host, DEFAULT_SENTINEL_PORT);
    }

    /**
     * Add a withSentinel host/port to the existing builder.
     *
     * @param host the host name
     * @param port the port
     * @return the builder
     */
    public RedisURIBuilder withSentinel(String host, int port) {

        if (this.sentinelPassword != null) {
            return withSentinel(host, port, new String(this.sentinelPassword));
        }

        return withSentinel(host, port, null);
    }

    /**
     * Add a withSentinel host/port and Sentinel authentication to the existing builder.
     *
     * @param host the host name
     * @param port the port
     * @param password the Sentinel password (supported since Redis 5.0.1)
     * @return the builder
     * @since 5.2
     */
    public RedisURIBuilder withSentinel(String host, int port, CharSequence password) {

        LettuceAssert.assertState(this.host == null, "Cannot use with Redis mode.");
        LettuceAssert.notEmpty(host, "Host must not be empty");
        LettuceAssert.isTrue(isValidPort(port), () -> String.format("Port out of range: %s", port));

        RedisURI redisURI = RedisURI.create(host, port);

        if (password != null) {
            redisURI.setPassword(password);
        }

        return withSentinel(redisURI);
    }

    /**
     * Add a withSentinel RedisURI to the existing builder.
     *
     * @param redisURI the sentinel URI
     * @return the builder
     * @since 5.2
     */
    public RedisURIBuilder withSentinel(RedisURI redisURI) {

        LettuceAssert.notNull(redisURI, "Redis URI must not be null");

        sentinels.add(redisURI);
        return this;
    }

    /**
     * Adds host information to the builder. Does only affect Redis URI, cannot be used with Sentinel connections.
     *
     * @param host the port
     * @return the builder
     */
    public RedisURIBuilder withHost(String host) {

        LettuceAssert.assertState(this.sentinels.isEmpty(), "Sentinels are non-empty. Cannot use in Sentinel mode.");
        LettuceAssert.notEmpty(host, "Host must not be empty");

        this.host = host;
        return this;
    }

    /**
     * Adds port information to the builder. Does only affect Redis URI, cannot be used with Sentinel connections.
     *
     * @param port the port
     * @return the builder
     */
    public RedisURIBuilder withPort(int port) {

        LettuceAssert.assertState(this.host != null, "Host is null. Cannot use in Sentinel mode.");
        LettuceAssert.isTrue(isValidPort(port), () -> String.format("Port out of range: %s", port));

        this.port = port;
        return this;
    }

    /**
     * Apply authentication from another {@link RedisURI}. The SSL settings of the {@code source} URI will be applied to
     * this URI. That is in particular SSL usage, peer verification and StartTLS.
     *
     * @param source must not be {@code null}.
     * @since 6.0
     * @return the builder
     */
    public RedisURIBuilder withSsl(RedisURI source) {

        LettuceAssert.notNull(source, "Source RedisURI must not be null");

        withSsl(source.isSsl());
        withVerifyPeer(source.getVerifyMode());
        withStartTls(source.isStartTls());

        return this;
    }

    /**
     * Adds ssl information to the builder. Sets SSL also for already configured Redis Sentinel nodes.
     *
     * @param ssl {@code true} if use SSL
     * @return the builder
     */
    public RedisURIBuilder withSsl(boolean ssl) {

        this.ssl = ssl;
        this.sentinels.forEach(it -> it.setSsl(ssl));
        return this;
    }

    /**
     * Enables/disables StartTLS when using SSL. Sets StartTLS also for already configured Redis Sentinel nodes.
     *
     * @param startTls {@code true} if use StartTLS
     * @return the builder
     */
    public RedisURIBuilder withStartTls(boolean startTls) {

        this.startTls = startTls;
        this.sentinels.forEach(it -> it.setStartTls(startTls));
        return this;
    }

    /**
     * Enables/disables peer verification. Sets peer verification also for already configured Redis Sentinel nodes.
     *
     * @param verifyPeer {@code true} to verify hosts when using SSL
     * @return the builder
     */
    public RedisURIBuilder withVerifyPeer(boolean verifyPeer) {
        return withVerifyPeer(verifyPeer ? SslVerifyMode.FULL : SslVerifyMode.NONE);
    }

    /**
     * Configures peer verification mode. Sets peer verification also for already configured Redis Sentinel nodes.
     *
     * @param verifyMode the mode to verify hosts when using SSL
     * @return the builder
     * @since 6.1
     */
    public RedisURIBuilder withVerifyPeer(SslVerifyMode verifyMode) {

        LettuceAssert.notNull(verifyMode, "VerifyMode must not be null");

        this.verifyMode = verifyMode;
        this.sentinels.forEach(it -> it.setVerifyPeer(verifyMode));
        return this;
    }

    /**
     * Configures the database number.
     *
     * @param database the database number
     * @return the builder
     */
    public RedisURIBuilder withDatabase(int database) {

        LettuceAssert.isTrue(database >= 0, () -> "Invalid database number: " + database);

        this.database = database;
        return this;
    }

    /**
     * Configures a client name. Sets client name also for already configured Redis Sentinel nodes.
     *
     * @param clientName the client name
     * @return the builder
     */
    public RedisURIBuilder withClientName(String clientName) {

        LettuceAssert.notNull(clientName, "Client name must not be null");

        this.clientName = clientName;
        this.sentinels.forEach(it -> it.setClientName(clientName));
        return this;
    }

    /**
     * Configures a library name. Sets library name also for already configured Redis Sentinel nodes.
     *
     * @param libraryName the library name
     * @return the builder
     * @since 6.3
     */
    public RedisURIBuilder withLibraryName(String libraryName) {

        LettuceAssert.notNull(libraryName, "Library name must not be null");

        if (libraryName.indexOf(' ') != -1) {
            throw new IllegalArgumentException("Library name must not contain spaces");
        }

        this.libraryName = libraryName;
        this.sentinels.forEach(it -> it.setLibraryName(libraryName));
        return this;
    }

    /**
     * Configures a library version. Sets library version also for already configured Redis Sentinel nodes.
     *
     * @param libraryVersion the library version
     * @return the builder
     * @since 6.3
     */
    public RedisURIBuilder withLibraryVersion(String libraryVersion) {

        LettuceAssert.notNull(libraryVersion, "Library version must not be null");

        if (libraryVersion.indexOf(' ') != -1) {
            throw new IllegalArgumentException("Library version must not contain spaces");
        }

        this.libraryVersion = libraryVersion;
        this.sentinels.forEach(it -> it.setLibraryVersion(libraryVersion));
        return this;
    }

    /**
     * Configures authentication.
     *
     * @param username the user name
     * @param password the password name
     * @return the builder
     * @since 6.0
     */
    public RedisURIBuilder withAuthentication(String username, CharSequence password) {

        LettuceAssert.notNull(username, "User name must not be null");
        LettuceAssert.notNull(password, "Password must not be null");

        this.username = username;
        return withPassword(password);
    }

    /**
     * Apply authentication from another {@link RedisURI}. The authentication settings of the {@code source} URI will be
     * applied to this builder.
     *
     * @param source must not be {@code null}.
     * @since 6.0
     */
    public RedisURIBuilder withAuthentication(RedisURI source) {

        LettuceAssert.notNull(source, "Source RedisURI must not be null");

        if (source.getCredentialsProvider() != null) {
            return withAuthentication(source.getCredentialsProvider());
        }

        this.username = source.getUsername();

        return withPassword(source.getPassword());
    }

    /**
     * Configures authentication. Empty password is supported (although not recommended for security reasons).
     *
     * @param username the user name
     * @param password the password name
     * @return the builder
     * @since 6.0
     */
    public RedisURIBuilder withAuthentication(String username, char[] password) {

        LettuceAssert.notNull(username, "User name must not be null");
        LettuceAssert.notNull(password, "Password must not be null");

        this.username = username;
        return withPassword(password);
    }

    /**
     * Configures authentication.
     *
     * @param credentialsProvider must not be {@code null}.
     * @since 6.2
     */
    public RedisURIBuilder withAuthentication(RedisCredentialsProvider credentialsProvider) {

        LettuceAssert.notNull(credentialsProvider, "RedisCredentialsProvider must not be null");

        this.credentialsProvider = credentialsProvider;
        return this;
    }

    /**
     * Configures authentication. Empty password is supported (although not recommended for security reasons).
     * <p>
     * This method is deprecated as of Lettuce 6.0. The reason is that {@link String} has a strong caching affinity and the
     * JVM cannot easily GC {@code String} instances. Therefore, we suggest using either {@code char[]} or a custom
     * {@link CharSequence} (e.g. {@link StringBuilder} or netty's {@link io.netty.util.AsciiString}).
     *
     * @param password the password
     * @return the builder
     * @deprecated since 6.0. Use {@link #withPassword(CharSequence)} or {@link #withPassword(char[])} to avoid String
     *             caching.
     */
    @Deprecated
    public RedisURIBuilder withPassword(String password) {

        LettuceAssert.notNull(password, "Password must not be null");

        return withPassword(password.toCharArray());
    }

    /**
     * Configures authentication. Empty password is supported (although not recommended for security reasons).
     *
     * @param password the password
     * @return the builder
     * @since 6.0
     */
    public RedisURIBuilder withPassword(CharSequence password) {

        LettuceAssert.notNull(password, "Password must not be null");

        char[] chars = new char[password.length()];
        for (int i = 0; i < password.length(); i++) {
            chars[i] = password.charAt(i);
        }

        return withPassword(chars);
    }

    /**
     * Configures authentication. Empty password is supported (although not recommended for security reasons).
     *
     * @param password the password
     * @return the builder
     * @since 4.4
     */
    public RedisURIBuilder withPassword(char[] password) {

        this.password = password == null ? null : Arrays.copyOf(password, password.length);
        return this;
    }

    /**
     * Configures a timeout.
     *
     * @param timeout must not be {@code null} or negative.
     * @return the builder
     */
    public RedisURIBuilder withTimeout(Duration timeout) {

        LettuceAssert.notNull(timeout, "Timeout must not be null");
        LettuceAssert.notNull(!timeout.isNegative(), "Timeout must be greater or equal 0");

        this.timeout = timeout;
        return this;
    }

    /**
     * Configures a sentinel master Id.
     *
     * @param sentinelMasterId sentinel master id, must not be empty or {@code null}
     * @return the builder
     */
    public RedisURIBuilder withSentinelMasterId(String sentinelMasterId) {

        LettuceAssert.notEmpty(sentinelMasterId, "Sentinel master id must not empty");

        this.sentinelMasterId = sentinelMasterId;
        return this;
    }

    /**
     * @return the RedisURI.
     */
    public RedisURI build() {

        if (sentinels.isEmpty() && LettuceStrings.isEmpty(host) && LettuceStrings.isEmpty(socket)) {
            throw new IllegalStateException(
                    "Cannot build a RedisURI. One of the following must be provided Host, Socket or Sentinel");
        }

        RedisURI redisURI = new RedisURI();
        redisURI.setHost(host);
        redisURI.setPort(port);

        if (credentialsProvider != null) {
            redisURI.setCredentialsProvider(credentialsProvider);
        } else {
            redisURI.setUsername(username);

            if (password != null) {
                redisURI.setPassword(password);
            }
        }

        redisURI.setDatabase(database);
        redisURI.setClientName(clientName);
        redisURI.setLibraryName(libraryName);
        redisURI.setLibraryVersion(libraryVersion);

        redisURI.setSentinelMasterId(sentinelMasterId);

        for (RedisURI sentinel : sentinels) {

            sentinel.setTimeout(timeout);
            redisURI.getSentinels().add(sentinel);
        }

        redisURI.setSocket(socket);
        redisURI.setSsl(ssl);
        redisURI.setStartTls(startTls);
        redisURI.setVerifyPeer(verifyMode);
        redisURI.setTimeout(timeout);

        return redisURI;
    }

    /** Return true for valid port numbers. */
    private static boolean isValidPort(int port) {
        return port >= 0 && port <= 65535;
    }

}