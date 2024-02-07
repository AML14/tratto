/*
 * Copyright 2015 Austin Keener, Michael Ritter, Florian Spieß, and the JDA contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.dv8tion.jda.api;

import net.dv8tion.jda.annotations.ForRemoval;
import net.dv8tion.jda.annotations.ReplaceWith;
import net.dv8tion.jda.internal.utils.Checks;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Represents the bit offsets used by Discord for Permissions.
 */
public class Permission
{
    // General Server / Channel Permissions
    public static Permission MANAGE_CHANNEL = new Permission(                      4, true,  true,  "Manage Channels");
    public static Permission MANAGE_SERVER = new Permission(                       5, true,  false, "Manage Server");
    public static Permission VIEW_AUDIT_LOGS = new Permission(                     7, true,  false, "View Audit Logs");
    public static Permission VIEW_CHANNEL = new Permission(                       10, true,  true,  "View Channel(s)");
    public static Permission VIEW_GUILD_INSIGHTS = new Permission(                19, true,  false, "View Server Insights");
    public static Permission MANAGE_ROLES = new Permission(                       28, true,  false, "Manage Roles");
    public static Permission MANAGE_PERMISSIONS = new Permission(                 28, false, true,  "Manage Permissions");
    public static Permission MANAGE_WEBHOOKS = new Permission(                    29, true,  true,  "Manage Webhooks");
    @Deprecated
    @ForRemoval(deadline = "5.0.0")
    @ReplaceWith("MANAGE_GUILD_EXPRESSIONS")
    public static Permission MANAGE_EMOJIS_AND_STICKERS = new Permission(         30, true,  false, "Manage Emojis and Stickers");
    public static Permission MANAGE_GUILD_EXPRESSIONS = new Permission(           30, true,  false, "Manage Emojis, Stickers, and Soundboards");
    public static Permission MANAGE_EVENTS = new Permission(                      33, true,  true,  "Manage Events");
    public static Permission VIEW_CREATOR_MONETIZATION_ANALYTICS = new Permission(41, true,  false, "View Creator Analytics");

    // Membership Permissions
    public static Permission CREATE_INSTANT_INVITE = new Permission(0, true, true,  "Create Instant Invite");
    public static Permission KICK_MEMBERS = new Permission(         1, true, false, "Kick Members");
    public static Permission BAN_MEMBERS = new Permission(          2, true, false, "Ban Members");
    public static Permission NICKNAME_CHANGE = new Permission(     26, true, false, "Change Nickname");
    public static Permission NICKNAME_MANAGE = new Permission(     27, true, false, "Manage Nicknames");
    public static Permission MODERATE_MEMBERS = new Permission(    40, true, false, "Timeout Members");

    // Text Permissions
    public static Permission MESSAGE_ADD_REACTION = new Permission(         6, true, true, "Add Reactions");
    public static Permission MESSAGE_SEND = new Permission(                11, true, true, "Send Messages");
    public static Permission MESSAGE_TTS = new Permission(                 12, true, true, "Send TTS Messages");
    public static Permission MESSAGE_MANAGE = new Permission(              13, true, true, "Manage Messages");
    public static Permission MESSAGE_EMBED_LINKS = new Permission(         14, true, true, "Embed Links");
    public static Permission MESSAGE_ATTACH_FILES = new Permission(        15, true, true, "Attach Files");
    public static Permission MESSAGE_HISTORY = new Permission(             16, true, true, "Read History");
    public static Permission MESSAGE_MENTION_EVERYONE = new Permission(    17, true, true, "Mention Everyone");
    public static Permission MESSAGE_EXT_EMOJI = new Permission(           18, true, true, "Use External Emojis");
    public static Permission USE_APPLICATION_COMMANDS = new Permission(    31, true, true, "Use Application Commands");
    public static Permission MESSAGE_EXT_STICKER = new Permission(         37, true, true, "Use External Stickers");
    public static Permission MESSAGE_ATTACH_VOICE_MESSAGE = new Permission(46, true, true, "Send Voice Messages");

    // Thread Permissions
    public static Permission MANAGE_THREADS = new Permission(          34, true, true, "Manage Threads");
    public static Permission CREATE_PUBLIC_THREADS = new Permission(   35, true, true, "Create Public Threads");
    public static Permission CREATE_PRIVATE_THREADS = new Permission(  36, true, true, "Create Private Threads");
    public static Permission MESSAGE_SEND_IN_THREADS = new Permission( 38, true, true, "Send Messages in Threads");

    // Voice Permissions
    public static Permission PRIORITY_SPEAKER = new Permission(          8, true, true, "Priority Speaker");
    public static Permission VOICE_STREAM = new Permission(              9, true, true, "Video");
    public static Permission VOICE_CONNECT = new Permission(            20, true, true, "Connect");
    public static Permission VOICE_SPEAK = new Permission(              21, true, true, "Speak");
    public static Permission VOICE_MUTE_OTHERS = new Permission(        22, true, true, "Mute Members");
    public static Permission VOICE_DEAF_OTHERS = new Permission(        23, true, true, "Deafen Members");
    public static Permission VOICE_MOVE_OTHERS = new Permission(        24, true, true, "Move Members");
    public static Permission VOICE_USE_VAD = new Permission(            25, true, true, "Use Voice Activity");
    public static Permission VOICE_START_ACTIVITIES = new Permission(   39, true, true, "Use Activities");
    public static Permission VOICE_USE_SOUNDBOARD = new Permission(     42, true, true, "Use Soundboard");
    public static Permission VOICE_USE_EXTERNAL_SOUNDS = new Permission(45, true, true, "Use External Sounds");
    public static Permission VOICE_SET_STATUS = new Permission(         48, true, true, "Set Voice Channel Status");

    // Stage Channel Permissions
    public static Permission REQUEST_TO_SPEAK = new Permission(      32, true, true, "Request to Speak");

    // Advanced Permissions
    public static Permission ADMINISTRATOR = new Permission(3, true, false, "Administrator");


    public static Permission UNKNOWN = new Permission(-1, false, false, "Unknown");

    public static Permission[] values() {
        return new Permission[]{
                MANAGE_CHANNEL,
                MANAGE_SERVER,
                VIEW_AUDIT_LOGS,
                VIEW_CHANNEL,
                VIEW_GUILD_INSIGHTS,
                MANAGE_ROLES,
                MANAGE_PERMISSIONS,
                MANAGE_WEBHOOKS,
                MANAGE_EMOJIS_AND_STICKERS,
                MANAGE_GUILD_EXPRESSIONS,
                MANAGE_EVENTS,
                VIEW_CREATOR_MONETIZATION_ANALYTICS,
                CREATE_INSTANT_INVITE,
                KICK_MEMBERS,
                BAN_MEMBERS,
                NICKNAME_CHANGE,
                NICKNAME_MANAGE,
                MODERATE_MEMBERS,
                MESSAGE_ADD_REACTION,
                MESSAGE_SEND,
                MESSAGE_TTS,
                MESSAGE_MANAGE,
                MESSAGE_EMBED_LINKS,
                MESSAGE_ATTACH_FILES,
                MESSAGE_HISTORY,
                MESSAGE_MENTION_EVERYONE,
                MESSAGE_EXT_EMOJI,
                USE_APPLICATION_COMMANDS,
                MESSAGE_EXT_STICKER,
                MESSAGE_ATTACH_VOICE_MESSAGE,
                MANAGE_THREADS,
                CREATE_PUBLIC_THREADS,
                CREATE_PRIVATE_THREADS,
                MESSAGE_SEND_IN_THREADS,
                PRIORITY_SPEAKER,
                VOICE_STREAM,
                VOICE_CONNECT,
                VOICE_SPEAK,
                VOICE_MUTE_OTHERS,
                VOICE_DEAF_OTHERS,
                VOICE_MOVE_OTHERS,
                VOICE_USE_VAD,
                VOICE_START_ACTIVITIES,
                VOICE_USE_SOUNDBOARD,
                VOICE_USE_EXTERNAL_SOUNDS,
                VOICE_SET_STATUS,
                REQUEST_TO_SPEAK,
                ADMINISTRATOR,
                UNKNOWN
        };
    }

    /**
     * Empty array of Permission enum, useful for optimized use in {@link java.util.Collection#toArray(Object[])}.
     */
    // This is an optimization suggested by Effective Java 3rd Edition - Item 54
    public static final Permission[] EMPTY_PERMISSIONS = new Permission[0];

    /**
     * Represents a raw set of all permissions
     */
    public static final long ALL_PERMISSIONS = Permission.getRaw(Permission.values());

    /**
     * All permissions that apply to a channel
     */
    public static final long ALL_CHANNEL_PERMISSIONS = Permission.getRaw(Arrays.stream(values())
            .filter(Permission::isChannel).collect(Collectors.toSet()));

    /**
     * All Guild specific permissions which are only available to roles
     */
    public static final long ALL_GUILD_PERMISSIONS = Permission.getRaw(Arrays.stream(values())
            .filter(Permission::isGuild).collect(Collectors.toSet()));

    /**
     * All text channel specific permissions which are only available in text channel permission overrides
     */
    public static final long ALL_TEXT_PERMISSIONS
            = Permission.getRaw(MESSAGE_ADD_REACTION, MESSAGE_SEND, MESSAGE_TTS, MESSAGE_MANAGE,
                                MESSAGE_EMBED_LINKS, MESSAGE_ATTACH_FILES, MESSAGE_EXT_EMOJI, MESSAGE_EXT_STICKER,
                                MESSAGE_HISTORY, MESSAGE_MENTION_EVERYONE, USE_APPLICATION_COMMANDS,
                                MANAGE_THREADS, CREATE_PUBLIC_THREADS, CREATE_PRIVATE_THREADS, MESSAGE_SEND_IN_THREADS, MESSAGE_ATTACH_VOICE_MESSAGE);

    /**
     * All voice channel specific permissions which are only available in voice channel permission overrides
     */
    public static final long ALL_VOICE_PERMISSIONS
            = Permission.getRaw(VOICE_STREAM, VOICE_CONNECT, VOICE_SPEAK, VOICE_MUTE_OTHERS,
                                VOICE_DEAF_OTHERS, VOICE_MOVE_OTHERS, VOICE_USE_VAD,
                                PRIORITY_SPEAKER, REQUEST_TO_SPEAK, VOICE_START_ACTIVITIES,
                                VOICE_USE_SOUNDBOARD, VOICE_USE_EXTERNAL_SOUNDS);

    private final int offset;
    private final long raw;
    private final boolean isGuild, isChannel;
    private final String name;

    Permission(int offset, boolean isGuild, boolean isChannel, @Nonnull String name)
    {
        this.offset = offset;
        this.raw = 1L << offset;
        this.isGuild = isGuild;
        this.isChannel = isChannel;
        this.name = name;
    }

    /**
     * The readable name as used in the Discord client.
     *
     * @return The readable name of this {@link net.dv8tion.jda.api.Permission Permission}.
     */
    @Nonnull
    public String getName()
    {
        return this.name;
    }

    /**
     * The binary offset of the permission.
     * <br>For more information about Discord's offset system refer to
     * <a href="https://discord.com/developers/docs/topics/permissions">Discord Permissions</a>.
     *
     * @return The offset that represents this {@link net.dv8tion.jda.api.Permission Permission}.
     */
    public int getOffset()
    {
        return offset;
    }

    /**
     * The value of this permission when viewed as a raw value.
     * <br>This is equivalent to: <code>1 {@literal <<} {@link #getOffset()}</code>
     *
     * @return The raw value of this specific permission.
     */
    public long getRawValue()
    {
        return raw;
    }

    /**
     * Returns whether or not this Permission is present at the Guild level
     * (configurable via {@link net.dv8tion.jda.api.entities.Role Roles})
     *
     * @return True if this permission is present at the Guild level.
     */
    public boolean isGuild()
    {
        return isGuild;
    }

    /**
     * Returns whether or not this Permission is present Channel level
     * (configurable via {@link net.dv8tion.jda.api.entities.PermissionOverride PermissionsOverrides})
     *
     * @return True if this permission is present at the Channel level.
     */
    public boolean isChannel()
    {
        return isChannel;
    }

    /**
     * Whether this permission is specifically for {@link net.dv8tion.jda.api.entities.channel.concrete.TextChannel TextChannels}
     *
     * @return True, if and only if this permission can only be applied to text channels
     */
    public boolean isText()
    {
        return (raw & ALL_TEXT_PERMISSIONS) == raw;
    }

    /**
     * Whether this permission is specifically for {@link net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel VoiceChannels}
     *
     * @return True, if and only if this permission can only be applied to voice channels
     */
    public boolean isVoice()
    {
        return (raw & ALL_VOICE_PERMISSIONS) == raw;
    }

    /**
     * Gets the first {@link net.dv8tion.jda.api.Permission Permission} relating to the provided offset.
     * <br>If there is no {@link net.dv8tion.jda.api.Permission Permssions} that matches the provided
     * offset, {@link net.dv8tion.jda.api.Permission#UNKNOWN Permission.UNKNOWN} is returned.
     *
     * @param  offset
     *         The offset to match a {@link net.dv8tion.jda.api.Permission Permission} to.
     *
     * @return {@link net.dv8tion.jda.api.Permission Permission} relating to the provided offset.
     */
    @Nonnull
    public static Permission getFromOffset(int offset)
    {
        for (Permission perm : values())
        {
            if (perm.offset == offset)
                return perm;
        }
        return UNKNOWN;
    }

    /**
     * A set of all {@link net.dv8tion.jda.api.Permission Permissions} that are specified by this raw long representation of
     * permissions. The is best used with the getRaw methods in {@link net.dv8tion.jda.api.entities.Role Role} or
     * {@link net.dv8tion.jda.api.entities.PermissionOverride PermissionOverride}.
     *
     * <p>Example: {@link net.dv8tion.jda.api.entities.Role#getPermissionsRaw() Role.getPermissionsRaw()}
     *
     * @param permissions The raw {@code long} representation of permissions.
     * @return Possibly-empty EnumSet of {@link net.dv8tion.jda.api.Permission Permissions}.
     */
    @Nonnull
    public static Set<Permission> getPermissions(long permissions)
    {
        if (permissions == 0)
            return new HashSet<>();
        Set<Permission> perms = new HashSet<>();
        for (Permission perm : Permission.values())
        {
            if (perm != UNKNOWN && (permissions & perm.raw) == perm.raw)
                perms.add(perm);
        }
        return perms;
    }

    /**
     * This is effectively the opposite of {@link #getPermissions(long)}, this takes 1 or more {@link net.dv8tion.jda.api.Permission Permissions}
     * and returns the raw offset {@code long} representation of the permissions.
     *
     * @param  permissions
     *         The array of permissions of which to form into the raw long representation.
     *
     * @return Unsigned long representing the provided permissions.
     */
    public static long getRaw(@Nonnull Permission... permissions)
    {
        long raw = 0;
        for (Permission perm : permissions)
        {
            if (perm != null && perm != UNKNOWN)
                raw |= perm.raw;
        }

        return raw;
    }

    /**
     * This is effectively the opposite of {@link #getPermissions(long)}, this takes a Collection of {@link net.dv8tion.jda.api.Permission Permissions}
     * and returns the raw offset {@code long} representation of the permissions.
     * <br>Example: {@code getRaw(EnumSet.of(Permission.VIEW_CHANNEL, Permission.MESSAGE_SEND))}
     *
     * @param  permissions
     *         The Collection of permissions of which to form into the raw long representation.
     *
     * @return Unsigned long representing the provided permissions.
     *
     * @see    java.util.EnumSet EnumSet
     */
    public static long getRaw(@Nonnull Collection<Permission> permissions)
    {
        Checks.notNull(permissions, "Permission Collection");

        return getRaw(permissions.toArray(EMPTY_PERMISSIONS));
    }
}
