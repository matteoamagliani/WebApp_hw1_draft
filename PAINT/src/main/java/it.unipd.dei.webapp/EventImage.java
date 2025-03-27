import java.util.UUID;

public class EventImage {
    private final UUID eventId;
    private final int imageIndex;
    private final byte[] imageData;
    private final String extension;

    public EventImage(UUID eventId, int imageIndex, byte[] imageData, String extension) {
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