package it.unipd.dei.webapp.resource;

import java.util.UUID;

public class Participates {

    public static final String TABLE_NAME = "Participates";
    public static final String EVENT_ID_NAME = "eventId";
    public static final String ARTISTIC_USER_ID_NAME = "artisticUserId"; 

    private final UUID eventId;
    private final UUID artisticUserId;

    public Participates(final UUID eventId, final UUID artisticUserId) {
        this.eventId = eventId;
        this.artisticUserId = artisticUserId;
    }

    public UUID getEventId() {
        return eventId;
    }

    public UUID getArtisticUserId() {
        return artisticUserId;
    }
}