package net.dv8tion.jda.api.entities.sticker;

import javax.annotation.Nonnull;

/**
 * The specific types of stickers
 */
public class StickerType
{
    /**
     * A sticker provided by nitro sticker packs. Such as wumpus or doggo stickers.
     * <br>These are also used for the wave buttons on welcome messages.
     */
    public static StickerType STANDARD = new StickerType(1);
    /**
     * A custom sticker created for a {@link net.dv8tion.jda.api.entities.Guild Guild}.
     */
    public static StickerType GUILD = new StickerType(2);
    /**
     * Placeholder for future stickers which are currently unsupported.
     */
    public static StickerType UNKNOWN = new StickerType(-1);

    private final int id;

    StickerType(int id)
    {
        this.id = id;
    }

    /**
     * Gets the sticker type related to the provided id.
     * <br>If an unknown id is provided, this returns {@link #UNKNOWN}.
     *
     * @param  id
     *         The raw id for the type
     *
     * @return The Type that has the key provided, or {@link #UNKNOWN}
     */
    @Nonnull
    public static StickerType fromId(int id)
    {
        for (StickerType type : values())
        {
            if (type.id == id)
                return type;
        }
        return UNKNOWN;
    }

    /**
     * The Discord defined id key for this sticker type.
     *
     * @return the id key
     */
    public int getId()
    {
        return id;
    }

    public static StickerType[] values() {
        return new StickerType[]{
            STANDARD,
            GUILD,
            UNKNOWN
        };
    }
}
