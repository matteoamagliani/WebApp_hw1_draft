import java.util.UUID;
import java.time.LocalDate;

public class Reviews1 {
    private final UUID clientUserId;
    private final UUID eventId;
    private final short score;
    private final LocalDate reviewDate;

    public Reviews1(UUID clientUserId, UUID eventId, short score, LocalDate reviewDate) {
        this.clientUserId = clientUserId;
        this.eventId = eventId;
        this.score = score;
        this.reviewDate = reviewDate;
    }

    public UUID getClientUserId() {
        return clientUserId;
    }

    public UUID getEventId() {
        return eventId;
    }

    public short getScore() {
        return score;
    }

    public LocalDate getReviewDate() {
        return reviewDate;
    }
}