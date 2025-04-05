package it.unipd.dei.webapp.resource;

import java.util.UUID;
/**
 * Represents an image associated with an event.
 * <p>
 * The {@link EventImage} class encapsulates information about an image related to an event, including
 * the event's ID, the image index, the image data, and the file extension of the image.
 * </p>
 *
 */

public class EventImage {
    
    public static final String TABLE_NAME = "EventImage";
    public static final String EVENT_ID_NAME = "eventId";
    public static final String IMAGE_INDEX_NAME = "imageIndex";
    public static final String IMAGE_DATA_NAME = "imageData";
    public static final String EXTENSION_NAME = "extension";

    private final UUID eventId;
    private final int imageIndex;
    private final byte[] imageData;
    private final String extension;

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