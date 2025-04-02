package it.unipd.dei.webapp.resource;

import java.util.UUID;

public class EventImage {
    private final UUID eventId;
    private final int imageIndex;
    private final byte[] imageData;
    private final String extension;

    public EventImage() {
        this.eventId = null;
        this.imageIndex = 0;
        this.imageData = null;
        this.extension = null;
    }

    public EventImage(final UUID eventId, final int imageIndex, final byte[] imageData, final String extension) {
        this.eventId = eventId;
        this.imageIndex = imageIndex;
        this.imageData = imageData;
        this.extension = extension;
    }

    public UUID getEventId() {
        return eventId;
    }

    public int getImageIndex() {
        return imageIndex;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public String getExtension() {
        return extension;
    }
}