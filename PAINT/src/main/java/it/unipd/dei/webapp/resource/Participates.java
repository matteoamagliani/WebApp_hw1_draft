package it.unipd.dei.webapp.resource;

import java.util.UUID;

public class Participates {
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