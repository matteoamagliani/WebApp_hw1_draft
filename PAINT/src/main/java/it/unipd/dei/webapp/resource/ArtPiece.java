package it.unipd.dei.webapp.resource;

import java.util.UUID;
import java.time.LocalDate;

public class ArtPiece {
    private final UUID id;
    private final UUID artisticUserId;
    private final String description;
    private final double weight;
    private final String title;
    private final LocalDate creationDate;
    private final double dimensionWidth;
    private final double dimensionHeight;
    private final double dimensionLength;
    private final LocalDate uploadDate;

    public ArtPiece(final UUID id, final UUID artisticUserId, final String description, final double weight, final String title,
                    final LocalDate creationDate, final double dimensionWidth, final double dimensionHeight,
                    final double dimensionLength, final LocalDate uploadDate) {
        this.id = id;
        this.artisticUserId = artisticUserId;
        this.description = description;
        this.weight = weight;
        this.title = title;
        this.creationDate = creationDate;
        this.dimensionWidth = dimensionWidth;
        this.dimensionHeight = dimensionHeight;
        this.dimensionLength = dimensionLength;
        this.uploadDate = uploadDate;
    }

    public UUID getId() {
        return id;
    }

    public UUID getArtisticUserId() {
        return artisticUserId;
    }

    public String getDescription() {
        return description;
    }

    public double getWeight() {
        return weight;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public double getDimensionWidth() {
        return dimensionWidth;
    }

    public double getDimensionHeight() {
        return dimensionHeight;
    }

    public double getDimensionLength() {
        return dimensionLength;
    }

    public LocalDate getUploadDate() {
        return uploadDate;
    }
}