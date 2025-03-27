import java.util.UUID;

public class Has2 {
    private final String tagName;
    private final UUID eventId;

    public Has2(final String tagName, final UUID eventId) {
        this.tagName = tagName;
        this.eventId = eventId;
    }

    public String getTagName() {
        return tagName;
    }

    public UUID getEventId() {
        return eventId;
    }
}