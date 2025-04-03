package it.unipd.dei.webapp.resource;

import java.util.UUID;
import java.time.LocalDate;

public class ArtisticProfile {

    public static final String TABLE_NAME = "ArtisticProfile";
    public static final String USER_ID_NAME = "userId";
    public static final String ROLE_NAME = "role";
    public static final String BIOGRAPHY_NAME = "biography";
    public static final String FOLLOWER_COUNT_NAME = "followerCount";
    public static final String NUM_PUBLISHED_ART_PIECES_NAME = "numPublishedArtPieces";
    public static final String NUM_SOLD_ART_PIECES_NAME = "numSoldArtPieces";
    public static final String LAST_PUB_DATE_NAME = "lastPubDate"; 
    
    private final UUID userId;
    private final String role;
    private final String biography;
    private final int followerCount;
    private final int numPublishedArtPieces;
    private final int numSoldArtPieces;
    private final LocalDate lastPubDate;

    public ArtisticProfile(final UUID userId, final String role, final String biography, final int followerCount,
                           final int numPublishedArtPieces, final int numSoldArtPieces, final LocalDate lastPubDate) {
        this.userId = userId;
        this.role = role;
        this.biography = biography;
        this.followerCount = followerCount;
        this.numPublishedArtPieces = numPublishedArtPieces;
        this.numSoldArtPieces = numSoldArtPieces;
        this.lastPubDate = lastPubDate;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getRole() {
        return role;
    }

    public String getBiography() {
        return biography;
    }

    public int getFollowerCount() {
        return followerCount;
    }

    public int getNumPublishedArtPieces() {
        return numPublishedArtPieces;
    }

    public int getNumSoldArtPieces() {
        return numSoldArtPieces;
    }

    public LocalDate getLastPubDate() {
        return lastPubDate;
    }
}