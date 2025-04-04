package it.unipd.dei.webapp.resource;

import java.time.LocalDate;
import java.util.UUID;

public final class Content {
    private final String type;
    private final UUID id;
    private final String title;
    private final String description;
    private final LocalDate uploadDate;
    private final byte[] imageData;
    private final String extension;

    public Content(String type, UUID id, String title, String description, LocalDate uploadDate, byte[] imageData, String extension) {
        this.type = type;
        this.id = id;
        this.title = title;
        this.description = description;
        this.uploadDate = uploadDate;
        this.imageData = imageData;
        this.extension = extension;
    }

    // eventuali getter
    public String getType() { return type; }
    public UUID getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public LocalDate getUploadDate() { return uploadDate; }
    public byte[] getImageData() { return imageData; }
    public String getExtension() { return extension; }
}