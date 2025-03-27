import java.util.UUID;

public class Buys {
    private final UUID clientUserId;
    private final UUID orderId;
    private final UUID artPieceId;

    public Buys(UUID clientUserId, UUID orderId, UUID artPieceId) {
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