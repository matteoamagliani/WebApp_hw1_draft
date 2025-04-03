package it.unipd.dei.webapp.resource;

import java.util.UUID;

public class Has2 {

    public static final String TABLE_NAME = "Has2";
    public static final String TAG_NAME_NAME = "tagName";
    public static final String EVENT_ID_NAME = "eventId"; 

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