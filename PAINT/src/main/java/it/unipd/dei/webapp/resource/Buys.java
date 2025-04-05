package it.unipd.dei.webapp.resource;

import java.util.UUID;

/**
 * Represents a purchase made by a client for an art piece.
 * <p>
 * The {@link Buys} class holds information about the purchase, including the client user ID,
 * the order ID, and the art piece ID.
 * </p>
 *
 */
 public class Buys {

    public static final String TABLE_NAME = "Buys";
    public static final String CLIENT_USER_ID_NAME = "clientUserId";
    public static final String ORDER_ID_NAME = "orderId";
    public static final String ART_PIECE_ID_NAME = "artPieceId";

    private final UUID clientUserId;
    private final UUID orderId;
    private final UUID artPieceId;

    public Buys(final UUID clientUserId, final UUID orderId, final UUID artPieceId) {
        this.clientUserId = clientUserId;
        this.orderId = orderId;
        this.artPieceId = artPieceId;
    }

    public UUID getClientUserId() {
        return clientUserId;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public UUID getArtPieceId() {
        return artPieceId;
    }
}