package it.unipd.dei.webapp.resource;

import java.util.UUID;
import java.time.LocalDate;

public class Event {

    public static final String TABLE_NAME = "Event";
    public static final String ID_NAME = "id";
    public static final String ARTISTIC_USER_ID_ORGANIZER_NAME = "artisticUserIdOrganizer";
    public static final String START_DATE_NAME = "startDate";
    public static final String END_DATE_NAME = "endDate";
    public static final String TITLE_NAME = "title";
    public static final String DESCRIPTION_NAME = "description";
    public static final String UPLOAD_DATE_NAME = "uploadDate";    

    private final UUID id;
    private final UUID artisticUserIdOrganizer;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String title;
    private final String description;
    private final LocalDate uploadDate;

    public Event(final UUID id, final UUID artisticUserIdOrganizer, final LocalDate startDate, final LocalDate endDate,
                 final String title, final String description, final LocalDate uploadDate) {
        this.id = id;
        this.artisticUserIdOrganizer = artisticUserIdOrganizer;
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.description = description;
        this.uploadDate = uploadDate;
    }

    public UUID getId() {
        return id;
    }

    public UUID getArtisticUserIdOrganizer() {
        return artisticUserIdOrganizer;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getUploadDate() {
        return uploadDate;
    }
}