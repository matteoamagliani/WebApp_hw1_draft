package it.unipd.dei.webapp.resource;

import java.util.UUID;

public class Has1 {
    private final String tagName;
    private final UUID artPieceId;

    public Has1(final String tagName, final UUID artPieceId) {
        this.tagName = tagName;
        this.artPieceId = artPieceId;
    }

    public String getTagName() {
        return tagName;
    }

    public UUID getArtPieceId() {
        return artPieceId;
    }
}