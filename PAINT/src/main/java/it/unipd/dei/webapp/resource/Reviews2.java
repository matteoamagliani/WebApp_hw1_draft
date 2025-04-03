package it.unipd.dei.webapp.resource;

import java.util.UUID;
import java.time.LocalDate;

public class Reviews2 {

    public static final String TABLE_NAME = "Reviews2";
    public static final String CLIENT_USER_ID_NAME = "clientUserId";
    public static final String ART_PIECE_ID_NAME = "artPieceId";
    public static final String SCORE_NAME = "score"; 
    public static final String REVIEW_DATE_NAME = "reviewDate"; 

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