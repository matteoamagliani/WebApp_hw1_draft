package it.unipd.dei.webapp.resource;

import java.util.UUID;
import java.time.LocalDate;

import it.unipd.dei.webapp.validation.Validatable;
import it.unipd.dei.webapp.validation.ValidationHashMap;
import it.unipd.dei.webapp.validation.Validator;

public class ArtisticProfile implements Validatable {

    public static final String TABLE_NAME = "ArtisticProfile";
    public static final String USER_ID_NAME = "userId";
    public static final String ROLE_NAME = "\"Role\"";
    public static final String ROLE_NAME_CLEAN = "Role";
    public static final String BIOGRAPHY_NAME = "biography";
    public static final String FOLLOWER_COUNT_NAME = "followerCount";
    public static final String NUM_PUBLISHED_ART_PIECES_NAME = "numPublishedArtPieces";
    public static final String NUM_SOLD_ART_PIECES_NAME = "numSoldArtPieces";
    public static final String LAST_PUB_DATE_NAME = "lastPubDate"; 
    
    private final UUID userId;
    private final UserRole role;
    private final String biography;
    private final int followerCount;
    private final int numPublishedArtPieces;
    private final int numSoldArtPieces;
    private final LocalDate lastPubDate;

    public ArtisticProfile(final UUID userId, final UserRole role, final String biography, final int followerCount,
                           final int numPublishedArtPieces, final int numSoldArtPieces, final LocalDate lastPubDate) {
        this.userId = userId;
        this.role = role;
        this.biography = biography;
        this.followerCount = followerCount;
        this.numPublishedArtPieces = numPublishedArtPieces;
        this.numSoldArtPieces = numSoldArtPieces;
        this.lastPubDate = lastPubDate;
    }

    public ArtisticProfile(final UUID userId, final UserRole role) {
        this.userId = userId;
        this.role = role;
        this.biography = null;
        this.followerCount = 0;
        this.numPublishedArtPieces = 0;
        this.numSoldArtPieces = 0;
        this.lastPubDate = null;
    }

    public UUID getUserId() {
        return userId;
    }

    public UserRole getRole() {
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

    public ValidationHashMap validateFields() {
        ValidationHashMap output = new ValidationHashMap();

        // Role validation
        /*String result = Validator.validateUserRole(role);
        output.put(ROLE_NAME, result);*/

        // Biography validation
        String result = Validator.validateString(biography, 10000);
        output.put(BIOGRAPHY_NAME, result);

        // Follower count validation
        result = Validator.validateObject(followerCount);
        if (followerCount < 0) {
            result = "Follower count cannot be negative";
        }
        output.put(FOLLOWER_COUNT_NAME, result);

        // Number of published art pieces validation
        result = Validator.validateObject(numPublishedArtPieces);
        if (numPublishedArtPieces < 0) {
            result = "Number of published art pieces cannot be negative";
        }
        output.put(NUM_PUBLISHED_ART_PIECES_NAME, result);

        // Number of sold art pieces validation
        result = Validator.validateObject(numSoldArtPieces);
        if (numSoldArtPieces < 0) {
            result = "Number of sold art pieces cannot be negative";
        }
        output.put(NUM_SOLD_ART_PIECES_NAME, result);

        // Last published date validation, TODO: finire con check preciso sulla data
        result = Validator.validateObject(lastPubDate);
        if (lastPubDate != null && lastPubDate.isAfter(LocalDate.now())) {
            result = "Last published date cannot be in the future";
        }
        output.put(LAST_PUB_DATE_NAME, result);

        return output;
    }
}