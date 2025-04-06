package it.unipd.dei.webapp.resource;

import java.util.UUID;
import java.time.LocalDate;
/**
 * Represents an advertisement for an art piece.
 * <p>
 * This class contains the information related to an advertisement, including the art piece's ID,
 * the price for the advertisement, and the publication date of the advertisement.
 * </p>
 *
 */
public class Advertisement {

    public static final String TABLE_NAME = "Advertisement";
    public static final String ART_PIECE_ID_NAME = "artPieceId";
    public static final String PRICE_NAME = "price";
    public static final String PUBLICATION_DATE_NAME = "publicationDate";

    private final UUID artPieceId;
    private final double price;
    private final LocalDate publicationDate;

    public Advertisement(final UUID artPieceId, final double price, final LocalDate publicationDate) {
        this.artPieceId = artPieceId;
        this.price = price;
        this.publicationDate = publicationDate;
    }

    public UUID getArtPieceId() {
        return artPieceId;
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }
}