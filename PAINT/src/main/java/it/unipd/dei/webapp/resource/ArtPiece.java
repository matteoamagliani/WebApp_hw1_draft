package it.unipd.dei.webapp.resource;

import java.util.UUID;
import java.time.LocalDate;
/**
 * Represents an art piece uploaded by an artist.
 * <p>
 * The {@link ArtPiece} class holds details about an art piece, including its ID, artistic user ID,
 * description, weight, title, creation date, dimensions, and upload date.
 * </p>
 *
 */
 public class ArtPiece {

    public static final String TABLE_NAME = "ArtPiece";
    public static final String ID_NAME = "id";
    public static final String ARTISTIC_USER_ID_NAME = "artisticUserId";
    public static final String DESCRIPTION_NAME = "description";
    public static final String WEIGHT_NAME = "weight";
    public static final String TITLE_NAME = "title";
    public static final String CREATION_DATE_NAME = "creationDate";
    public static final String DIMENSION_WIDTH_NAME = "dimensionWidth";
    public static final String DIMENSION_HEIGHT_NAME = "dimensionHeight";
    public static final String DIMENSION_LENGTH_NAME = "dimensionLength";
    public static final String UPLOAD_DATE_NAME = "uploadDate";
    

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