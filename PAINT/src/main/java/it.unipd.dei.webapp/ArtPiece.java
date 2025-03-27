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

    public ArtPiece(UUID id, UUID artisticUserId, String description, double weight, String title,
                    LocalDate creationDate, double dimensionWidth, double dimensionHeight,
                    double dimensionLength, LocalDate uploadDate) {
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