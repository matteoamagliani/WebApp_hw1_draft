package it.unipd.dei.webapp.resource;

import java.util.UUID;

public class Buys {
    private final UUID clientUserId;
    private final UUID orderId;
    private final UUID artPieceId;

    public Buys(){
        this.clientUserId = null;
        this.orderId = null;
        this.artPieceId = null;
    }

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