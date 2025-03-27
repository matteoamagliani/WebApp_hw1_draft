import java.util.UUID;

public class ArtPieceImage {
    private final UUID artPieceId;
    private final int imageIndex;
    private final byte[] imageData;
    private final String extension;

    public ArtPieceImage(UUID artPieceId, int imageIndex, byte[] imageData, String extension) {
        this.artPieceId = artPieceId;
        this.imageIndex = imageIndex;
        this.imageData = imageData;
        this.extension = extension;
    }

    public UUID getArtPieceId() {
        return artPieceId;
    }

    public int getImageIndex() {
        return imageIndex;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public String getExtension() {
        return extension;
    }
}