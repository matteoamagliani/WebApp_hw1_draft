package it.unipd.dei.webapp.resource;

import java.util.UUID;
/**
 * Represents the relationship between an event and an artistic user who participates in it.
 * <p>
 * This class is used to store information about which artistic user is participating in which event.
 * </p>
 */

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