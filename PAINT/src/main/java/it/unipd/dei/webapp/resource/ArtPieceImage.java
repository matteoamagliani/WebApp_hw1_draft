package it.unipd.dei.webapp.resource;

import java.util.UUID;

/**
 * Represents an image associated with an art piece.
 * <p>
 * The {@link ArtPieceImage} class holds the details of an image, including the art piece ID,
 * image index, image data, and the file extension of the image.
 * </p>
 *
 */

 public class ArtPieceImage {

    public static final String TABLE_NAME = "ArtPieceImage";
    public static final String ART_PIECE_ID_NAME = "artPieceId";
    public static final String IMAGE_INDEX_NAME = "imageIndex";
    public static final String IMAGE_DATA_NAME = "imageData";
    public static final String EXTENSION_NAME = "extension";

    private final UUID artPieceId;
    private final int imageIndex;
    private final byte[] imageData;
    private final String extension;

    public ArtPieceImage(final UUID artPieceId, final int imageIndex, final byte[] imageData, final String extension) {
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