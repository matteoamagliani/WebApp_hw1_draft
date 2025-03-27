package it.unipd.dei.webapp.resource;

import java.util.UUID;

public class Has3 {
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