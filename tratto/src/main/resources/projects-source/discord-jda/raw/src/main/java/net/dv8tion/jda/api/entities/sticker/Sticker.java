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

package net.dv8tion.jda.api.entities.sticker;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.utils.ImageProxy;
import net.dv8tion.jda.api.utils.MiscUtil;
import net.dv8tion.jda.internal.utils.Helpers;

import javax.annotation.Nonnull;

/**
 * Abstract representation of all sticker types.
 *
 * <p>This is specialized in {@link StandardSticker} and {@link GuildSticker}.
 */
public interface Sticker extends StickerSnowflake
{
    /** Template for {@link #getIconUrl()} */
    String ICON_URL = "https://cdn.discordapp.com/stickers/%s.%s";

    /**
     * Creates a sticker snowflake instance which only wraps an ID.
     *
     * <p>This is primarily used for message sending purposes.
     *
     * @param  id
     *         The sticker id
     *
     * @return A sticker snowflake instance
     *
     * @see    JDA#retrieveSticker(StickerSnowflake)
     */
    @Nonnull
    static StickerSnowflake fromId(long id)
    {
        return StickerSnowflake.fromId(id);
    }

    /**
     * Creates a sticker snowflake instance which only wraps an ID.
     *
     * <p>This is primarily used for message sending purposes.
     *
     * @param  id
     *         The sticker id
     *
     * @throws IllegalArgumentException
     *         If the provided ID is not a valid snowflake
     *
     * @return A sticker snowflake instance
     *
     * @see    JDA#retrieveSticker(StickerSnowflake)
     */
    @Nonnull
    static StickerSnowflake fromId(@Nonnull String id)
    {
        return fromId(MiscUtil.parseSnowflake(id));
    }

    /**
     * The format type of this sticker, used for {@link #getIconUrl()}.
     * <br>Note that stickers can be of type {@link StickerFormat#LOTTIE LOTTIE}, which don't have simple image icons,
     * but instead rely on client-side rendering.
     *
     * <p>Future stickers might have format {@link StickerFormat#UNKNOWN UNKNOWN}, which cannot be converted to a URL.
     *
     * @return The {@link StickerFormat} of this sticker
     */
    @Nonnull
    StickerFormat getFormatType();

    /**
     * The name of the sticker.
     *
     * @return the name of the sticker
     */
    @Nonnull
    String getName();

    /**
     * The url of the sticker image.
     * <br>Note that {@link StickerFormat#LOTTIE LOTTIE} stickers don't provide an image, but a JSON format.
     *
     * @throws java.lang.IllegalStateException
     *         If the {@link StickerFormat StickerFormat} of this sticker is {@link StickerFormat#UNKNOWN UNKNOWN}
     *
     * @return The image url of the sticker
     */
    @Nonnull
    default String getIconUrl()
    {
        return Helpers.format(ICON_URL, getId(), getFormatType().getExtension());
    }

    /**
     * Returns an {@link ImageProxy} for this sticker's image.
     *
     * <p>The size parameter for {@link ImageProxy} is not supported for stickers of type {@link StickerFormat#LOTTIE LOTTIE}.
     *
     * @throws java.lang.IllegalStateException
     *         If the {@link StickerFormat} of this sticker is {@link StickerFormat#UNKNOWN UNKNOWN}
     *
     * @return Never-null {@link ImageProxy} of this sticker's image
     *
     * @see    #getIconUrl()
     */
    @Nonnull
    default ImageProxy getIcon()
    {
        return new ImageProxy(getIconUrl());
    }

    /**
     * The various formats used for stickers and the respective file extensions.
     */
    enum StickerFormat
    {
        /**
         * The PNG format.
         */
        PNG(1, "png"),
        /**
         * The APNG format.
         */
        APNG(2, "png"),
        /**
         * The LOTTIE format.
         * <br>Lottie isn't a standard renderable image. It is a JSON with data that can be rendered using the lottie library.
         *
         * @see <a href="https://airbnb.io/lottie/">Lottie website</a>
         */
        LOTTIE(3, "json"),
        /**
         * The GIF format.
         */
        GIF(4, "gif"),
        /**
         * Represents any unknown or unsupported format types.
         */
        UNKNOWN(-1, null);

        private final int id;
        private final String extension;

        StickerFormat(final int id, final String extension)
        {
            this.id = id;
            this.extension = extension;
        }

        /**
         * The file extension used for the sticker asset.
         *
         * @throws java.lang.IllegalStateException
         *         If the {@link StickerFormat StickerFormat} is {@link StickerFormat#UNKNOWN UNKNOWN}
         *
         * @return The file extension for this format
         */
        @Nonnull
        public String getExtension()
        {
            if (this == UNKNOWN)
                throw new IllegalStateException("Cannot get file extension for StickerFormat.UNKNOWN");
            return extension;
        }

        /**
         * Resolves the specified format identifier to the StickerFormat enum constant.
         *
         * @param  id
         *         The id for this format
         *
         * @return The representative StickerFormat or UNKNOWN if it can't be resolved
         */
        @Nonnull
        public static StickerFormat fromId(int id)
        {
            for (StickerFormat stickerFormat : values())
            {
                if (stickerFormat.id == id)
                    return stickerFormat;
            }
            return UNKNOWN;
        }
    }

}
