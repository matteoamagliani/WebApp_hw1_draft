package it.unipd.dei.webapp.resource;

import java.util.UUID;
/**
 * Represents a relationship where a tag is associated with a specific artistic user.
 * <p>
 * The {@link Has3} class encapsulates the relationship between a tag and an artistic user.
 * Each tag is associated with a unique artistic user.
 * </p>
 */
public class Has3 {

    public static final String TABLE_NAME = "Has3";
    public static final String TAG_NAME_NAME = "tagName";
    public static final String ARTISTIC_USER_ID_NAME = "artisticUserId"; 

    private final String tagName;
    private final UUID artisticUserId;

    public Has3(final String tagName, final UUID artisticUserId) {
        this.tagName = tagName;
        this.artisticUserId = artisticUserId;
    }

    public String getTagName() {
        return tagName;
    }

    public UUID getArtisticUserId() {
        return artisticUserId;
    }
}