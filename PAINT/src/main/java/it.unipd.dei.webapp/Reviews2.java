import java.util.UUID;
import java.time.LocalDate;

public class Reviews2 {
    private final UUID clientUserId;
    private final UUID artPieceId;
    private final short score;
    private final LocalDate reviewDate;

    public Reviews2(final UUID clientUserId, final UUID artPieceId, final short score, final LocalDate reviewDate) {
        this.clientUserId = clientUserId;
        this.artPieceId = artPieceId;
        this.score = score;
        this.reviewDate = reviewDate;
    }

    public UUID getClientUserId() {
        return clientUserId;
    }

    public UUID getArtPieceId() {
        return artPieceId;
    }

    public short getScore() {
        return score;
    }

    public LocalDate getReviewDate() {
        return reviewDate;
    }
}